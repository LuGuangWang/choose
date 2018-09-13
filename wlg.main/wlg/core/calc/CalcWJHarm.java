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
import wlg.core.bean.zhanfa.ShuaXinZhanFa;
import wlg.core.bean.zhanfa.ZhanFa;

/**
 * 计算武将的伤害值
 * @author seven
 *
 */
public class CalcWJHarm {
	/**
	 * 计算武将组合的伤害值
	 * TODO 是否增加控制基数
	 * @param wujiang
	 * @return
	 */
	public static <T extends ZhanFa> float  calcVal(WuJiang... wjs) {
		float sum = 0;
		//重置称号
		WChengHao.reset();
		//阵营 兵种加成 称号加成
		wjs = addExProp(wjs);
		//按速度排序
		wjs = sortedWuJiang(wjs);
		List<WuJiang> globalwujiang = new ArrayList<>(Arrays.asList(wjs));
		
		for(int i=1;i<9;i++) {
			
			List<WuJiang> wujiang = globalwujiang;
			if(globalwujiang.size()==0) {
				break;
			}
			float huiheVal = 0.0f;
			
			HuiHe huihe = new HuiHe();
			huihe.setId(i);
			Conf.log("===============第"+ huihe.getId()+"回合开始=============");
			
			huihe.setWujiangs(wujiang);
			huihe.setWujiangCount(wujiang.size());
			
			//补充额外属性 并获取可影响其它战法的战法
			List<ZhanFa> kongzhi = hasXianFaKongZhi(huihe, wujiang);
			
			for(int j=0;j<wujiang.size();j++) {
				float wjVal = 0.0f;//武将战斗力
				WuJiang wj = wujiang.get(j);
				Conf.log("===============武将"+ wj.getName()+"开始战斗=============");
				//补充额外属性 并且增加先发的控制战法
				List<ZhanFa> allKongZhi = buildExProp(huihe,wj,kongzhi);
				//自身战法加成
				if(huihe.isHasZiShenJiaCheng()) {
					wj.addJiaCheng();
				}
				
				//主伤害
				if(huihe.isHasKongZhi()) {
					Conf.log("=============计算普通主伤害值==========");
					wjVal += CalcHarm.calcKongZhiHuiHe(huihe, true,allKongZhi,wj.getZhanfa());
					//增益伤害
					if(huihe.isHasZengYi()) {
						Conf.log("=============计算增益伤害值==========");
						List<ZhanFa> zfList = new ArrayList<>();
						for(int m=j;m<wujiang.size();m++) {
							zfList.addAll(Arrays.asList(wujiang.get(m).getZhanfa()));
						}
						wjVal += CalcHarm.calcKongZhiHuiHe(huihe, false,allKongZhi,wj.getZhanfa());
					}
				} else {
					Conf.log("=============计算普通主伤害值==========");
					wjVal += CalcHarm.calcCommHuiHe(huihe, wj.getZhanfa());
					//增益伤害
					if(huihe.isHasZengYi()) {
						Conf.log("=============计算增益伤害值==========");
						List<ZhanFa> zfList = new ArrayList<>();
						for(int m=j;m<wujiang.size();m++) {
							zfList.addAll(Arrays.asList(wujiang.get(m).getZhanfa()));
						}
						wjVal += CalcHarm.calcExVal(huihe, zfList.toArray(new ZhanFa[zfList.size()]));
					}
				}
				// 普通攻击伤害
				if(Conf.getCalcPG()) {
					float gongjiVal = 0.0f;
					if(!huihe.isHasBuGong() && CalCDistance.calcDistance(wj.getDistance(), wj.getPosition())>0){
						gongjiVal = CalcDoRate.getAttackRate() * wj.getWJHarmVal() * huihe.getSolderRate(wj.getPosition(), wj.getDefense()) * huihe.getUpGongJiVal();
					}
					wjVal += gongjiVal;
					Conf.log("=========武将"+ wj.getName() +"普通攻击最终杀伤力："+gongjiVal);
				}
				
				huiheVal += wjVal;
				Conf.log("===============武将"+ wj.getName()+"结束战斗,最终杀伤力：" + wjVal);
				
				//检查是否有武将损失
				huihe.removeWujiang(huihe.getWj());
			}
			//重新设置武将
			globalwujiang = huihe.getWujiangs();
			
			sum += huiheVal;
			Conf.log("===========第"+huihe.getId()+"回合结束，最终杀伤力："+huiheVal);
		}
		return sum;
	}
	/**
	 * 阵营 兵种加成 称号加成
	 * 
	 * @param wjs
	 * @return
	 */
	private static WuJiang[] addExProp(WuJiang... wjs) {
		//两个以上的武将才有加成
		if(wjs.length==1) {
			return wjs;
		}
		
		int speedVal = 0;//速度加成值
		int strategyVal = 0;//策略加成值
		int attackVal = 0;//攻击加成值
		int defenseVal = 0;//防御加成值
		
		//兵种
		Map<WBType,WJChengHaoExVal> bingzhong = new HashMap<>();
		//阵营
		Map<WZType,WJChengHaoExVal> zhenying = new HashMap<>();
		//称号
		WCType chenghao = WCType.none;
		for(WuJiang wj:wjs) {
			if(bingzhong.containsKey(wj.getWbType())) {
				bingzhong.get(wj.getWbType()).addName(wj.getName());
			}else {
				WJChengHaoExVal chh = new WJChengHaoExVal();
				chh.addName(wj.getName());
				bingzhong.put(wj.getWbType(),chh);
			}
			if(zhenying.containsKey(wj.getWzType())) {
				zhenying.get(wj.getWzType()).addName(wj.getName());
			}else {
				WJChengHaoExVal chh = new WJChengHaoExVal();
				chh.addName(wj.getName());
				zhenying.put(wj.getWzType(),chh);
			}
			chenghao = WChengHao.checkChengHao(wj.getName());
		}
		//阵营加成
		addZhenYingExProp(zhenying);
		
		//兵种加成
		addBingZhongExProp(bingzhong);
		
		//称号加成
		switch(chenghao) {
		case dongwudadudu:
			Conf.log("========该组合为称号【东吴大都督】");
			speedVal += 19;
			strategyVal += 20;
			break;
		default:
			Conf.log("========该组合没有称号。");
			break;
		}
		
		//添加属性值
		for(int i = 0;i<wjs.length;i++) {
			WuJiang wj = wjs[i];
			float attackRate = 1.0f;
			float speedRate = 1.0f;
			float strategyRate = 1.0f;
			float defenseRate = 1.0f;
			
			for(WBType k:bingzhong.keySet()) {
				WJChengHaoExVal v = bingzhong.get(k);
				if(v.getName().toString().contains(wj.getName())) {
					attackRate += v.getAttackRate();
					speedRate += v.getSpeedRate();
					strategyRate += v.getStrategyRate();
					defenseRate += v.getDefenseRate();
				}
			}
			for(WZType k:zhenying.keySet()) {
				WJChengHaoExVal v = zhenying.get(k);
				if(v.getName().toString().contains(wj.getName())) {
					attackRate += v.getAttackRate();
					speedRate += v.getSpeedRate();
					strategyRate += v.getStrategyRate();
					defenseRate += v.getDefenseRate();
				}
			}
			
			Conf.log("=====武将" + wj.getName() + " 攻击加成比：" + attackRate + " 防御加成比：" + defenseRate+ " 策略加成比：" + strategyRate+ " 速度加成比：" + speedRate);
			
			int attack = Math.round(wj.getAttack()*attackRate) + attackVal;
			int speed = Math.round(wj.getSpeed()*speedRate) + speedVal;
			int strategy = Math.round(wj.getStrategy()*strategyRate) + strategyVal;
			int defense = Math.round(wj.getDefense() * defenseRate) + defenseVal;
			WuJiang newWJ = wj.changeProp(attack,speed,strategy,defense);
			wjs[i] = newWJ;
		}
		
		return wjs;
	}
	private static void addBingZhongExProp(Map<WBType, WJChengHaoExVal> bingzhong) {
		Set<WBType> keys = new HashSet<>(bingzhong.keySet());
		for(WBType k:keys) {
			WJChengHaoExVal v = bingzhong.get(k);
			float bzval = 0.0f;
			if(v.size()==2) {
				bzval = 0.05f;
			}else if(v.size()==3) {
				bzval = 0.1f;
			}
			if(bzval>0) {
				switch(k) {
				case bu:
					Conf.log("===========该组合步兵兵种受益武将："+ v.getName() +" 加成值为：" + bzval);
					v.addDefenseRate(bzval);
					v.addAttackRate(bzval);
					break;
				case gong:
					Conf.log("===========该组合弓兵兵种受益武将："+ v.getName() +" 加成值为：" + bzval);
					v.addDefenseRate(bzval);
					v.addSpeedRate(bzval);
					break;
				case qi:
					Conf.log("===========该组合骑兵兵种受益武将："+ v.getName() +" 加成值为：" + bzval);
					v.addSpeedRate(bzval);
					v.addAttackRate(bzval);
					break;
				default:
					Conf.log("====兵种加成有问题。");
				}
			}else {
				bingzhong.remove(k);
			}
		}
	}
	private static void addZhenYingExProp(Map<WZType, WJChengHaoExVal> zhenying) {
		Set<WZType> keys = new HashSet<>(zhenying.keySet());
		for(WZType k:keys) {
			WJChengHaoExVal v = zhenying.get(k);
			float zyval = 0.0f;
			if(v.size()==3) {
				zyval = 0.1f;
			}else if(v.size()==2) {
				zyval = 0.08f;
			}
			if(zyval>0) {
				v.addSpeedRate(zyval);
				v.addAttackRate(zyval);
				v.addDefenseRate(zyval);
				v.addStrategyRate(zyval);
				Conf.log("===========该组合阵营受益武将："+ v.getName() +" 加成值为：" + zyval);
			}else {
				zhenying.remove(k);
			}
		}
	}

	private static List<ZhanFa> hasXianFaKongZhi(HuiHe huihe, List<WuJiang> wujiang) {
		huihe.setHasKongZhi(false);
		List<ZhanFa> kongzhi = new ArrayList<>();
		wujiang.forEach(wj->{
			for(ZhanFa zf:wj.getZhanfa()) {
				//武将位置
				zf.setPosition(wj.getPosition());
				
				if(CheckUtil.isXianFaKongZhi(zf)) {
					Conf.log("=========添加控制战法"+zf.getName());
					kongzhi.add(zf);
					huihe.setHasKongZhi(true);
				}
			}
		});
		return Collections.unmodifiableList(kongzhi);
	}

	//补充额外属性
	private static List<ZhanFa> buildExProp(HuiHe huihe, WuJiang wj,List<ZhanFa> kongzhi) {
		//控制战法
		List<ZhanFa> allKongZhi = new ArrayList<>(kongzhi);
		//设置当前武将
		huihe.setWj(wj);
		//重新设置武将回合的损失兵力
		wj.resetSunshiCount();
		
		huihe.setHasZengYi(false);
		huihe.setHasBuGong(false);
		huihe.setHasZiShenJiaCheng(false);
		
		huihe.setShuaxinVal(0.0f);
		huihe.setUpGongJiVal(1.0f);
		huihe.setKongzhiVal(0.0f);
		
		//校验所有战法
		List<ZhanFa> allZfs = new ArrayList<>(Arrays.asList(wj.getZhanfa()));
		allZfs.addAll(kongzhi);
		
		for(ZhanFa zf:allZfs) {
			if(CheckUtil.isZengYi(zf)) huihe.setHasZengYi(true);
			if(CheckUtil.isBuGongJi(zf)) huihe.setHasBuGong(true);
			if(CheckUtil.isZiShenJiaCheng(zf)) huihe.setHasZiShenJiaCheng(true);
			
			if(CheckUtil.isKongZhi(zf)) {
				huihe.setHasKongZhi(true);
				if(!allKongZhi.contains(zf)) {
					Conf.log("=========添加控制战法"+zf.getName());
					allKongZhi.add(zf);
				}
			}
			
			
			if(zf instanceof ShuaXinZhanFa) {
				float oldVal = huihe.getShuaxinVal();
				float newVal = ((ShuaXinZhanFa) zf).getBaseRate();
				if(newVal>oldVal) {
					huihe.setShuaxinVal(newVal);
					Conf.log("=====刷新战法"+zf.getName()+" 刷新伤害基值："+oldVal+"->"+newVal);
				}
			}
			if(CheckUtil.isJiaShang(zf)) {
				float oldVal = huihe.getUpGongJiVal();
				float newVal = zf.getHarmRate() + 1.0f;
				if(newVal>oldVal) {
					huihe.setUpGongJiVal(newVal);
					Conf.log("=====加伤战法"+zf.getName()+" 刷新加伤基值："+oldVal+"->"+newVal);
				}
			}
		}
		return allKongZhi;
	}
	
	//按速度排序
	private static WuJiang[] sortedWuJiang(WuJiang... wujiang) {
		WuJiang tmp;
		for(int i=0;i<wujiang.length;i++) {
			//设置武将位置
			wujiang[i].setFinalp(Conf.WuJiang_Count-wujiang.length+i+1);
			wujiang[i].setPosition(wujiang[i].getFinalp());
			
			if(i>=1) {
				if(wujiang[i].getSpeed()>wujiang[i-1].getSpeed()) {
					tmp = wujiang[i];
					wujiang[i] = wujiang[i-1];
					wujiang[i-1] = tmp;
				}
				if(i==2) {
					if(wujiang[1].getSpeed()>wujiang[0].getSpeed()) {
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
