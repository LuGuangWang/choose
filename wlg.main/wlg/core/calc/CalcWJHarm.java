package wlg.core.calc;

import wlg.core.bean.HuiHe;
import wlg.core.bean.wujiang.WuJiang;
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
		
		//单个武将的伤害
		
		//可与其他武将配合的二次伤害
		
		for(int i=1;i<9;i++) {
			huihe.setId(i);
			sum += CalcHarm.calcVal(wujiang[0].getZhanfa());
		}
		sum += CalcHarm.calcVal(wujiang[0].getZhanfa());
		return sum;
	}
	
	//按速度排序
	private static WuJiang[] sortedWuJiang(WuJiang... wujiang) {
		WuJiang tmp;
		for(int i=1;i<wujiang.length;i++) {
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
		return wujiang;
	}
}
