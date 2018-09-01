package wlg.core.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wlg.core.CheckUtil;
import wlg.core.bean.HuiHe;
import wlg.core.bean.conf.Conf;
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
	 * @param wujiang
	 * @return
	 */
	public static <T extends ZhanFa> float  calcVal(WuJiang... wjs) {
		float sum = 0;
		HuiHe huihe = new HuiHe();
		//按速度排序
		wjs = sortedWuJiang(wjs);
		
		List<WuJiang> globalwujiang = new ArrayList<>(Arrays.asList(wjs));
		
		for(int i=1;i<9;i++) {
			List<WuJiang> wujiang = globalwujiang;
			if(wujiang.size()==0) {
				break;
			}
			huihe.setId(i);
			huihe.setWujiangCount(wujiang.size());
			boolean isCalc = true;
			
			Conf.log("===============第"+ huihe.getId()+"回合=============");
			
			for(int j=0;j<wujiang.size();j++) {
				WuJiang wj = wujiang.get(j);
				//设置武将位置
				wj.setPosition(Conf.WuJiang_Count-huihe.getWujiangCount()+j+1);
				//武将行动的顺序
				wj.changeOrder(wujiang.size()-1-j);
				//补充额外属性
				buildExProp(huihe, wj);
				//加成战法
				if(huihe.isHasJiaCheng()) {
					wj.addJiaCheng();
				}
				//主伤害
				if(huihe.isHasKongZhi()) {
					if(isCalc) {
						List<ZhanFa> zfList = new ArrayList<>();
						for(int m=j;m<wujiang.size();m++) {
							zfList.addAll(Arrays.asList(wujiang.get(m).getZhanfa()));
						}
						sum += CalcHarm.calcKongZhiHuiHe(huihe, zfList.toArray(new ZhanFa[zfList.size()]));
						isCalc = false;
					}
				} else {
					sum += CalcHarm.calcCommHuiHe(huihe, wj.getZhanfa());
				}
				//增益伤害
				if(huihe.isHasZengYi()) {
					List<ZhanFa> zfList = new ArrayList<>();
					for(int m=j;m<wujiang.size();m++) {
						zfList.addAll(Arrays.asList(wujiang.get(m).getZhanfa()));
					}
					sum += CalcHarm.calcExVal(huihe, zfList.toArray(new ZhanFa[zfList.size()]));
				}
				// 普通攻击伤害
				if(!huihe.isHasBuGong() && Conf.getCalcPG()) {
					sum += CalcDoRate.getAttackRate() * wj.getWJHarmVal() * huihe.getSolderRate(wj.getPosition());
				}
				//有武将损失
				if(huihe.getSolderRate(wj.getPosition())<=0) {
					Conf.log("=========第"+ huihe.getId() +"回合损失武将: " + wj.getName());
					globalwujiang.remove(wj);
					if(wj.getFinalp()==1) {
						Conf.log("=========第"+ huihe.getId() +"回合损失大营: " + wj.getName());
						globalwujiang.clear();
					}
				}
			}
		}
		return sum;
	}

	//补充额外属性
	private static void buildExProp(HuiHe huihe, WuJiang wj) {
		ZhanFa[] zfs = wj.getZhanfa();
		huihe.setHasZengYi(false);
		huihe.setShuaxinRate(0.0f);
		huihe.setHasKongZhi(false);
		huihe.setHasBuGong(false);
		huihe.setHasJiaCheng(false);
		for(ZhanFa zf:zfs) {
			//武将位置
			zf.setPosition(wj.getPosition());
			huihe.setHasZengYi(CheckUtil.isZengYi(zf));
			huihe.setHasKongZhi(CheckUtil.isKongZhi(zf));
			huihe.setHasBuGong(CheckUtil.isBuGongJi(zf));
			huihe.setHasJiaCheng(CheckUtil.isJiaCheng(zf));
			
			if(zf instanceof ShuaXinZhanFa) {
				huihe.setShuaxinRate(((ShuaXinZhanFa) zf).getBaseRate());
			}
		}
	}
	
	//按速度排序
	private static WuJiang[] sortedWuJiang(WuJiang... wujiang) {
		WuJiang tmp;
		for(int i=0;i<wujiang.length;i++) {
			//设置武将位置
			wujiang[i].setFinalp(Conf.WuJiang_Count-wujiang.length+i+1);
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
