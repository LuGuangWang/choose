package wlg.core.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wlg.core.bean.HuiHe;
import wlg.core.bean.conf.Conf;
import wlg.core.bean.wujiang.WuJiang;
import wlg.core.bean.zhanfa.JiaChengZhanFa;
import wlg.core.bean.zhanfa.KongZhiZhanFa;
import wlg.core.bean.zhanfa.ShuaXinZhanFa;
import wlg.core.bean.zhanfa.ZFType;
import wlg.core.bean.zhanfa.ZengYiZhanFa;
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
	public static <T extends ZhanFa> float  calcVal(WuJiang... wujiang) {
		float sum = 0;
		HuiHe huihe = new HuiHe();
		//按速度排序
		wujiang = sortedWuJiang(wujiang);
		
		for(int i=1;i<9;i++) {
			huihe.setId(i);
			huihe.setWujiangCount(wujiang.length);
			boolean isCalc = true;
			
			for(int j=0;j<wujiang.length;j++) {
				WuJiang wj = wujiang[j];
				//武将行动的顺序
				wj.changeOrder(wujiang.length-1-j);

				buildExProp(huihe, wj);
				
				//主伤害
				if(huihe.isHasKongZhi()) {
					if(isCalc) {
						List<ZhanFa> zfList = new ArrayList<>();
						for(int m=j;m<wujiang.length;m++) {
							zfList.addAll(Arrays.asList(wujiang[m].getZhanfa()));
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
					for(int m=j;m<wujiang.length;m++) {
						zfList.addAll(Arrays.asList(wujiang[m].getZhanfa()));
					}
					sum += CalcHarm.calcExVal(huihe, zfList.toArray(new ZhanFa[zfList.size()]));
				}
				// 普通攻击伤害
				if(!huihe.isHasBuGong() && Conf.getCalcPG()) {
					sum += CalcDoRate.getAttackRate() * wj.getWJHarmVal() * huihe.getSolderRate(wj.getPosition());
				}
			}
		}
		return sum;
	}

	//补充回合属性
	private static void buildExProp(HuiHe huihe, WuJiang wj) {
		ZhanFa[] zfs = wj.getZhanfa();
		huihe.setHasZengYi(false);
		huihe.setShuaxinRate(0.0f);
		huihe.setHasKongZhi(false);
		huihe.setHasBuGong(false);
		for(ZhanFa zf:zfs) {
			//武将位置
			zf.setPosition(wj.getPosition());
			
			if(zf instanceof ZengYiZhanFa) {
				huihe.setHasZengYi(true);
			}
			if(zf instanceof ShuaXinZhanFa) {
				huihe.setShuaxinRate(((ShuaXinZhanFa) zf).getBaseRate());
			}
			if(zf instanceof KongZhiZhanFa 
					|| zf.getT().equals(ZFType.ZhiHui_KongZhiGongJi_FaShuShangHai)
					|| zf.getT().equals(ZFType.ZhuDong_FaShuShangHai_KongZhiGongji)) {
				huihe.setHasKongZhi(true);
			}
			if(zf instanceof JiaChengZhanFa) {
				huihe.setHasBuGong(true);
			}
		}
	}
	
	//按速度排序
	private static WuJiang[] sortedWuJiang(WuJiang... wujiang) {
		WuJiang tmp;
		for(int i=0;i<wujiang.length;i++) {
			//设置武将位置
			wujiang[i].setPosition(Conf.WuJiang_Count-wujiang.length+i+1);
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
