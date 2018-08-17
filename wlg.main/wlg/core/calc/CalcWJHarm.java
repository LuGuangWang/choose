package wlg.core.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wlg.core.bean.HuiHe;
import wlg.core.bean.wujiang.WuJiang;
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
			for(int j=0;j<wujiang.length;j++) {
				WuJiang wj = wujiang[j];
				
				//单个武将的主伤害
				sum += CalcHarm.calcPrimayVal(huihe, wj.getZhanfa());
				
				//单个武将的增益伤害
				boolean hasZenYi = false;
				List<ZhanFa> zfList = new ArrayList<>();
				ZhanFa[] zfs = wj.getZhanfa();
				for(ZhanFa zf:zfs) {
					if(zf instanceof ZengYiZhanFa) {
						hasZenYi = true;
					}
				}
				if(hasZenYi) {
					for(int m=j;j<wujiang.length;j++) {
						zfList.addAll(Arrays.asList(wujiang[m].getZhanfa()));
					}
					sum += CalcHarm.calcExVal(huihe, zfList.toArray(new ZhanFa[zfList.size()]));
				}
			}
		}
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
