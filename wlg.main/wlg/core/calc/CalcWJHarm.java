package wlg.core.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import wlg.core.CheckUtil;
import wlg.core.bean.HuiHe;
import wlg.core.bean.conf.Conf;
import wlg.core.bean.wujiang.WBType;
import wlg.core.bean.wujiang.WCType;
import wlg.core.bean.wujiang.WChengHao;
import wlg.core.bean.wujiang.WJChengHaoExVal;
import wlg.core.bean.wujiang.WZType;
import wlg.core.bean.wujiang.WuJiang;
import wlg.core.bean.zhanfa.BiYueZhanFa;
import wlg.core.bean.zhanfa.ConflictList;
import wlg.core.bean.zhanfa.GongJiZhanFa;
import wlg.core.bean.zhanfa.QiZuoGuiMou;
import wlg.core.bean.zhanfa.ShiJiZhanFa;
import wlg.core.bean.zhanfa.ShuaXinZhanFa;
import wlg.core.bean.zhanfa.WeiWuZhiShiZhanFa;
import wlg.core.bean.zhanfa.ZFType;
import wlg.core.bean.zhanfa.ZhanFa;

/**
 * 计算武将的伤害值
 * 
 * @author seven
 *
 */
public class CalcWJHarm {
	/**
	 * 计算武将组合的伤害值 TODO 是否增加控制基数
	 * 
	 * @param wujiang
	 * @return
	 */
	public static <T extends ZhanFa> float calcVal(WuJiang... wjs) {
		float sum = 0;
		// 重置称号
		WChengHao.reset();
		// 阵营 兵种加成 称号加成
		wjs = addExProp(wjs);
		// 先发指挥战法 补充额外属性
		wjs = addExPropByZhanFa(wjs);
		// 按速度排序
		wjs = sortedWuJiang(wjs);
		List<WuJiang> globalwujiang = new ArrayList<>(Arrays.asList(wjs));
		// 先发指挥战法 可影响其它战法的结果
		List<ZhanFa> kongzhi = hasXianFaKongZhi(globalwujiang);
		//可以生效的影响其他武将的战法集合
		List<ZhanFa> allKongZhi = new ArrayList<>(kongzhi);
		
		for (int i = 1; i < 9; i++) {
			//重置战法冲突
			ConflictList.$().reset();
			
			List<WuJiang> wujiang = globalwujiang;
			if (globalwujiang.size() == 0) {
				break;
			}
			float huiheVal = 0.0f;

			HuiHe huihe = new HuiHe();
			huihe.setId(i);
			Conf.log("===============第" + huihe.getId() + "回合开始=============");

			huihe.setWujiangs(wujiang);
			huihe.setWujiangCount(wujiang.size());

			for (int j = 0; j < wujiang.size(); j++) {
				float wjVal = 0.0f;// 武将战斗力
				float fsVal = 0.0f;//法术伤害值
				WuJiang wj = wujiang.get(j);
				Conf.log("===============武将" + wj.getName() + "开始战斗=============");
				
				// 补充额外属性 并且增加先发的控制战法
				buildExProp(huihe, wj, allKongZhi);

				// 主伤害
				if (huihe.isHasKongZhi()) {
					Conf.log("=============计算普通主伤害值==========");
					fsVal += CalcHarm.calcKongZhiHuiHe(huihe, true, allKongZhi, wj.getZhanfa());
					// 增益伤害
					if (huihe.isHasZengYi()) {
						Conf.log("=============计算增益伤害值==========");
						List<ZhanFa> zfList = new ArrayList<>();
						for (int m = j; m < wujiang.size(); m++) {
							zfList.addAll(Arrays.asList(wujiang.get(m).getZhanfa()));
						}
						fsVal += CalcHarm.calcKongZhiHuiHe(huihe, false, allKongZhi, wj.getZhanfa());
					}
				} else {
					Conf.log("=============计算普通主伤害值==========");
					fsVal += CalcHarm.calcCommHuiHe(huihe, wj.getZhanfa());
					// 增益伤害
					if (huihe.isHasZengYi()) {
						Conf.log("=============计算增益伤害值==========");
						List<ZhanFa> zfList = new ArrayList<>();
						for (int m = j; m < wujiang.size(); m++) {
							zfList.addAll(Arrays.asList(wujiang.get(m).getZhanfa()));
						}
						fsVal += CalcHarm.calcExVal(huihe, zfList.toArray(new ZhanFa[zfList.size()]));
					}
				}
				//法术伤害
				wjVal += fsVal;
				// 普通攻击伤害
				if (Conf.getCalcPG()) {
					float gongjiVal = 0.0f;
					boolean canGongJi = CalCDistance.calcDistance(wj.getDistance(), wj.getPosition()) > 0;
					if (!huihe.isHasBuGong() && canGongJi) {
						float solderRate = huihe.getSolderRate(null);
						gongjiVal = CalcDoRate.getAttackRate() * wj.getWJHarmVal() * solderRate * huihe.getUpGongJiVal();
						//免疫攻击 免疫规避
						gongjiVal *= wj.getMianyiGJVal() * wj.getMianyiGBVal();
						//攻击加成伤害
						float upval = 1.0f + huihe.getUpFaShaShangHaiVal() * 0.25f;
						gongjiVal *= upval;
						//降低敌军防御属性
						gongjiVal += huihe.getDownFangYuVal() * Conf.fg_rate;
					}
					wjVal += gongjiVal;
					Conf.log("=========武将" + wj.getName() + "普通攻击最终杀伤力：" + gongjiVal);
				}

				huiheVal += wjVal;
				Conf.log("===============武将" + wj.getName() + "结束战斗,最终杀伤力：" + wjVal);

				// 检查是否有武将损失
				boolean isSunShi = huihe.removeWujiang(huihe.getWj());
				if(isSunShi) {
					for(ZhanFa zf:huihe.getWj().getZhanfa()) {
						if(allKongZhi.contains(zf) && !CheckUtil.isXianFaKongZhi(zf)) {
							Conf.log("========去除损失武将的控制战法：" + zf.getName());
							allKongZhi.remove(zf);
						}
					}
				}
			}
			// 重新设置武将
			globalwujiang = huihe.getWujiangs();

			sum += huiheVal;
			Conf.log("===========第" + huihe.getId() + "回合结束，最终杀伤力：" + huiheVal);
		}
		return sum;
	}

	private static WuJiang[] addExPropByZhanFa(WuJiang... wjs) {
		float addVal = 1.0f;
		int addDis = 0;
		boolean hasAddProp = false;
		for (int i = 0; i < wjs.length; i++) {
			WuJiang wj = wjs[i];
			for (ZhanFa zf : wj.getZhanfa()) {
				if (zf.getT().equals(ZFType.ZhiHui_JiaShuXing_JiaJuLi)) {
					hasAddProp = true;
					WeiWuZhiShiZhanFa w = (WeiWuZhiShiZhanFa) zf;
					addVal = addVal < w.getAddVal() ? w.getAddVal() : addVal;
					addDis = addDis < w.getAddDis() ? w.getAddDis() : addDis;
				}
			}
		}
		if(hasAddProp) {
			for (int i = 0; i < wjs.length; i++) {
				WuJiang wj = wjs[i];
				int attack = Math.round(wj.getAttack() * addVal);
				int speed = Math.round(wj.getSpeed() * addVal);
				int strategy = Math.round(wj.getStrategy() * addVal);
				int defense = Math.round(wj.getDefense() * addVal);
				int distance = wj.getDistance() + addDis;
				WuJiang newWJ = wj.changeProp(attack, speed, strategy, defense, distance);
				wjs[i] = newWJ;
			}
		}
		return wjs;
	}

	/**
	 * 阵营 兵种加成 称号加成
	 * 
	 * @param wjs
	 * @return
	 */
	private static WuJiang[] addExProp(WuJiang... wjs) {
		// 两个以上的武将才有加成
		if (wjs.length == 1) {
			return wjs;
		}

		int speedVal = 0;// 速度加成值
		int strategyVal = 0;// 策略加成值
		int attackVal = 0;// 攻击加成值
		int defenseVal = 0;// 防御加成值

		// 兵种
		Map<WBType, WJChengHaoExVal> bingzhong = new HashMap<>();
		// 阵营
		Map<WZType, WJChengHaoExVal> zhenying = new HashMap<>();
		// 称号
		WCType chenghao = WCType.none;
		for (WuJiang wj : wjs) {
			if (bingzhong.containsKey(wj.getWbType())) {
				bingzhong.get(wj.getWbType()).addName(wj.getName());
			} else {
				WJChengHaoExVal chh = new WJChengHaoExVal();
				chh.addName(wj.getName());
				bingzhong.put(wj.getWbType(), chh);
			}
			if (zhenying.containsKey(wj.getWzType())) {
				zhenying.get(wj.getWzType()).addName(wj.getName());
			} else {
				WJChengHaoExVal chh = new WJChengHaoExVal();
				chh.addName(wj.getName());
				zhenying.put(wj.getWzType(), chh);
			}
			chenghao = WChengHao.checkChengHao(wj.getName());
		}
		// 阵营加成
		addZhenYingExProp(zhenying);

		// 兵种加成
		addBingZhongExProp(bingzhong);

		// 称号加成
		switch (chenghao) {
		case dongwudadudu:
			Conf.log("========该组合为称号【东吴大都督】");
			speedVal += 19;
			strategyVal += 20;
			break;
		default:
			Conf.log("========该组合没有称号。");
			break;
		}

		// 添加属性值
		for (int i = 0; i < wjs.length; i++) {
			WuJiang wj = wjs[i];
			float attackRate = 1.0f;
			float speedRate = 1.0f;
			float strategyRate = 1.0f;
			float defenseRate = 1.0f;

			for (WBType k : bingzhong.keySet()) {
				WJChengHaoExVal v = bingzhong.get(k);
				if (v.getName().toString().contains(wj.getName())) {
					attackRate += v.getAttackRate();
					speedRate += v.getSpeedRate();
					strategyRate += v.getStrategyRate();
					defenseRate += v.getDefenseRate();
				}
			}
			for (WZType k : zhenying.keySet()) {
				WJChengHaoExVal v = zhenying.get(k);
				if (v.getName().toString().contains(wj.getName())) {
					attackRate += v.getAttackRate();
					speedRate += v.getSpeedRate();
					strategyRate += v.getStrategyRate();
					defenseRate += v.getDefenseRate();
				}
			}

			Conf.log("=====武将" + wj.getName() + " 攻击加成比：" + attackRate + " 防御加成比：" + defenseRate + " 策略加成比："
					+ strategyRate + " 速度加成比：" + speedRate);

			int attack = Math.round(wj.getAttack() * attackRate) + attackVal;
			int speed = Math.round(wj.getSpeed() * speedRate) + speedVal;
			int strategy = Math.round(wj.getStrategy() * strategyRate) + strategyVal;
			int defense = Math.round(wj.getDefense() * defenseRate) + defenseVal;
			WuJiang newWJ = wj.changeProp(attack, speed, strategy, defense);
			wjs[i] = newWJ;
		}

		return wjs;
	}

	private static void addBingZhongExProp(Map<WBType, WJChengHaoExVal> bingzhong) {
		Set<WBType> keys = new HashSet<>(bingzhong.keySet());
		for (WBType k : keys) {
			WJChengHaoExVal v = bingzhong.get(k);
			float bzval = 0.0f;
			if (v.size() == 2) {
				bzval = 0.05f;
			} else if (v.size() == 3) {
				bzval = 0.1f;
			}
			if (bzval > 0) {
				switch (k) {
				case bu:
					Conf.log("===========该组合步兵兵种受益武将：" + v.getName() + " 加成值为：" + bzval);
					v.addDefenseRate(bzval);
					v.addAttackRate(bzval);
					break;
				case gong:
					Conf.log("===========该组合弓兵兵种受益武将：" + v.getName() + " 加成值为：" + bzval);
					v.addDefenseRate(bzval);
					v.addSpeedRate(bzval);
					break;
				case qi:
					Conf.log("===========该组合骑兵兵种受益武将：" + v.getName() + " 加成值为：" + bzval);
					v.addSpeedRate(bzval);
					v.addAttackRate(bzval);
					break;
				default:
					Conf.log("====兵种加成有问题。");
				}
			} else {
				bingzhong.remove(k);
			}
		}
	}

	private static void addZhenYingExProp(Map<WZType, WJChengHaoExVal> zhenying) {
		Set<WZType> keys = new HashSet<>(zhenying.keySet());
		for (WZType k : keys) {
			WJChengHaoExVal v = zhenying.get(k);
			float zyval = 0.0f;
			if (v.size() == 3) {
				zyval = 0.1f;
			} else if (v.size() == 2) {
				zyval = 0.08f;
			}
			if (zyval > 0) {
				v.addSpeedRate(zyval);
				v.addAttackRate(zyval);
				v.addDefenseRate(zyval);
				v.addStrategyRate(zyval);
				Conf.log("===========该组合阵营受益武将：" + v.getName() + " 加成值为：" + zyval);
			} else {
				zhenying.remove(k);
			}
		}
	}
	//TODO 指挥战法的位置 后面不会变化
	private static List<ZhanFa> hasXianFaKongZhi(List<WuJiang> wjs) {
		List<ZhanFa> kongzhi = new ArrayList<>();
		for (WuJiang wj : wjs) {
			for (ZhanFa zf : wj.getZhanfa()) {
				if (CheckUtil.isXianFaKongZhi(zf)) {
					Conf.log("=========添加先手控制战法" + zf.getName());
					kongzhi.add(zf);
				}
			}
		}
		return Collections.unmodifiableList(kongzhi);
	}

	// 补充额外属性
	private static void buildExProp(HuiHe huihe, WuJiang wj, List<ZhanFa> kongzhi) {
		// 设置当前武将
		huihe.setWj(wj);
		// 重新设置武将回合的损失兵力
		wj.resetSunshiCount();
		// 重新设置武将免疫力
		wj.resetmianyiVal();

		//添加控制战法
		for(ZhanFa zf:wj.getZhanfa()) {
			if(!kongzhi.contains(zf)  && CheckUtil.isKongZhi(zf)) {
				Conf.log("=========添加控制战法" + zf.getName());
				kongzhi.add(zf);
			}
			if(zf.getT().equals(ZFType.ZhiHui_JiaFaShu_JianShang_MianYi)) {
				//TODO 免疫法术的概率
				float rate = 1 - wj.getSpeed()/Conf.base_speed;
				rate = rate>0?rate:0;
				wj.setMianyiFSVal(Conf.mianyi_fashu + rate);
				//免疫攻击的概率
				wj.setMianyiGJVal(Conf.mianyi_gongji + rate);
			}
		}
		
		huihe.setHasZengYi(false);
		huihe.setHasBuGong(false);

		huihe.setShuaxinVal(0.0f);
		huihe.setUpGongJiVal(1.0f);
		huihe.setKongzhiVal(0.0f);
		huihe.setHasKongZhi(false);
		huihe.setLianjiVal(1.0f);
		huihe.setUpFaShuVal(1.0f);
		huihe.setUpFaShaShangHaiVal(0.0f);
		
		// 校验所有战法
		Set<ZhanFa> allZfs = new HashSet<>(Arrays.asList(wj.getZhanfa()));
		// 含有先发控制
		if (kongzhi.size() > 0) {
			huihe.setHasKongZhi(true);
			allZfs.addAll(kongzhi);
		}
		//检查战法冲突
		for (ZhanFa zf : allZfs) {
			ConflictList.$().checkChongTu(zf);
		}

		for (ZhanFa zf : allZfs) {
			if (CheckUtil.isZengYi(zf))
				huihe.setHasZengYi(true);
			if (CheckUtil.isBuGongJi(zf))
				huihe.setHasBuGong(true);
			
			// 自身战法加成 不攻 & 大赏三军 & 始计
			if (CheckUtil.isZiShenJiaCheng(zf) && !ConflictList.$().isCeluechongtu()) {
				wj.addJiaCheng();
			}
			
			if(CheckUtil.isLianJi(zf)) {
				if(zf.getT().equals(ZFType.ZhuDong_JiaGongJi_LianJi)) {
					setWeiWuZhiZeLianJiVal(huihe, zf);
				}
			}
			if (zf instanceof ShuaXinZhanFa) {
				float oldVal = huihe.getShuaxinVal();
				float newVal = ((ShuaXinZhanFa) zf).getBaseRate();
				if (newVal > oldVal) {
					huihe.setShuaxinVal(newVal);
					Conf.log("=====刷新战法" + zf.getName() + " 刷新谋略伤害基值：" + oldVal + "->" + newVal);
				}
			}
			if (CheckUtil.isJiaShang(zf)) {
				float oldVal = huihe.getUpGongJiVal();
				//受谋略影响
				float val = zf.getStrategy()/Conf.shuxing_suoxiao;
				float newVal = zf.getHarmRate() + val + 1.0f;
				if (newVal > oldVal) {
					huihe.setUpGongJiVal(newVal);
					Conf.log("=====战法" + zf.getName() + " 刷新谋略伤害基值：" + oldVal + "->" + newVal);
				}
			}
			if(CheckUtil.isUpFashu(zf)) {
				QiZuoGuiMou tmp = (QiZuoGuiMou)zf;
				float oldVal = huihe.getUpFaShuVal();
				float newVal = tmp.getUpVal() + 1;
				if (newVal > oldVal) {
					huihe.setUpFaShuVal(newVal);
					Conf.log("=====战法" + zf.getName() + " 提高策略属性值：" + oldVal + "->" + newVal);
				}
			}
			if(CheckUtil.isDownFangYu(zf)) {
				BiYueZhanFa tmp = (BiYueZhanFa)zf;
				float oldVal = huihe.getDownFangYuVal();
				//受谋略影响
				float val = zf.getStrategy()/Conf.shuxing_suoxiao*100;
				float newVal = tmp.getDownFShuXingVal() + val;
				float rate = CalcDoRate.getCommRate(huihe, zf) * zf.getDoneRate();
				newVal *= rate;
				if (newVal > oldVal) {
					huihe.setDownFangYuVal(newVal);
					Conf.log("=====战法" + zf.getName() + " 降低防御属性值：" + oldVal + "->" + newVal);
				}
			}
			//始计
			if(zf.getT().equals(ZFType.ZhiHui_JiaFaShu_JianShang_MianYi)) {
				float oldVal = huihe.getUpFaShaShangHaiVal();
				//受谋略影响
				float val = zf.getStrategy()/Conf.shuxing_suoxiao;
				float newVal = ((ShiJiZhanFa)zf).getUpVal() + val;
				if (newVal > oldVal) {
					huihe.setUpFaShaShangHaiVal(newVal);
					Conf.log("=====战法" + zf.getName() + " 刷新谋略或攻击伤害基值：" + oldVal + "->" + newVal);
				}
			}
		}
		
	}

	private static void setWeiWuZhiZeLianJiVal(HuiHe huihe, ZhanFa zf) {
		int person = zf.getPersons().getMaxPerson() - 1;
		int wjCount = huihe.getWujiangCount() - 1;
		person = person>wjCount?wjCount:person;
		int lianjiCount = ((GongJiZhanFa)zf).getLianjiCount();
		float lianjiVal = person / 1.0f / wjCount * zf.getDoneRate()*lianjiCount+ 1.0f;
		float oldVal = huihe.getLianjiVal();
		if(lianjiVal>oldVal) {
			huihe.setLianjiVal(lianjiVal);
			Conf.log("=====连击战法" + zf.getName() + " 刷新连击值：" + oldVal + "->" + lianjiVal);
		}
	}

	// 按速度排序
	private static WuJiang[] sortedWuJiang(WuJiang... wujiang) {
		WuJiang tmp;
		for (int i = 0; i < wujiang.length; i++) {
			// 设置武将位置
			wujiang[i].setFinalp(Conf.WuJiang_Count - wujiang.length + i + 1);
			wujiang[i].setPosition(wujiang[i].getFinalp());

			if (i >= 1) {
				if (wujiang[i].getSpeed() > wujiang[i - 1].getSpeed()) {
					tmp = wujiang[i];
					wujiang[i] = wujiang[i - 1];
					wujiang[i - 1] = tmp;
				}
				if (i == 2) {
					if (wujiang[1].getSpeed() > wujiang[0].getSpeed()) {
						tmp = wujiang[1];
						wujiang[1] = wujiang[0];
						wujiang[0] = tmp;
					}
				}
			}
		}
		return wujiang;
	}
}
