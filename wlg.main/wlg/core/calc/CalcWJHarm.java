package wlg.core.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import wlg.core.CheckUtil;
import wlg.core.bean.HuiHe;
import wlg.core.bean.conf.Conf;
import wlg.core.bean.wujiang.WuJiang;
import wlg.core.bean.zhanfa.JiaShangZhanFa;
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
	 * @param wujiang
	 * @return
	 */
	public static <T extends ZhanFa> float  calcVal(WuJiang... wjs) {
		float sum = 0;
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
				//武将行动的顺序
				wj.changeOrder(wujiang.size()-1-j);
				//补充额外属性 并且增加先发的控制战法
				 List<ZhanFa> allKongZhi = buildExProp(huihe,wj,kongzhi);
				//加成战法
				if(huihe.isHasJiaCheng()) {
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
				if(!huihe.isHasBuGong() && Conf.getCalcPG() && (CalCDistance.calcDistance(wj.getDistance(), wj.getPosition())>0)) {
					float gongjiVal = CalcDoRate.getAttackRate() * wj.getWJHarmVal() * huihe.getSolderRate(wj.getPosition(), wj.getDefense()) * huihe.getUpGongJiVal();
					wjVal += gongjiVal;
					Conf.log("=========武将"+ wj.getName() +"普通攻击最终杀伤力："+gongjiVal);
				}
				//重新设置武将
				globalwujiang = huihe.getWujiangs();
				
				huiheVal += wjVal;
				Conf.log("===============武将"+ wj.getName()+"结束战斗,最终杀伤力：" + wjVal);
			}
			sum += huiheVal;
			Conf.log("===========第"+huihe.getId()+"回合结束，最终杀伤力："+huiheVal);
		}
		return sum;
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
		
		huihe.setHasZengYi(false);
		huihe.setHasBuGong(false);
		huihe.setHasJiaCheng(false);
		
		huihe.setShuaxinVal(0.0f);
		huihe.setUpGongJiVal(1.0f);
		huihe.setKongzhiVal(0.0f);
		
		ZhanFa[] zfs = wj.getZhanfa();
		for(ZhanFa zf:zfs) {
			if(CheckUtil.isZengYi(zf)) huihe.setHasZengYi(true);
			if(CheckUtil.isBuGongJi(zf)) huihe.setHasBuGong(true);
			if(CheckUtil.isJiaCheng(zf)) huihe.setHasJiaCheng(true);
			
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
			if(zf instanceof JiaShangZhanFa) {
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
