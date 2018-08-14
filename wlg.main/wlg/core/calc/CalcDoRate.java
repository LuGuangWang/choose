package wlg.core.calc;

import wlg.core.bean.HuiHe;
import wlg.core.bean.zhanfa.MaiLeiZhanFa;
import wlg.core.bean.zhanfa.ZhanFa;
/**
 * 计算发动战法成功概率
 * @author seven
 *
 */
public class CalcDoRate {
	/**
	 * 有刷新战法,发动战法成功概率
	 * @param huihe
	 * @param zhanfa
	 * @return
	 */
	public static <T extends ZhanFa> float getShuaXinRate(HuiHe huihe, T zhanfa) {
		float rate = 0;
		//可以发动战法
		if(huihe.getId() > zhanfa.getReady()) {
			rate = 1;
		} 
		//埋雷战法
		if(zhanfa instanceof MaiLeiZhanFa) {
			rate = 0;
			MaiLeiZhanFa mz = (MaiLeiZhanFa) zhanfa;
			int ready = zhanfa.getReady() + 1;
			
			if(huihe.getId() == ready) {
				rate = mz.getSpeed();
			//刷新后,战法可以叠加
			}else if(huihe.getId()> ready) {
				rate = 1;
			}
			//持续回合
			rate = rate * mz.getKeep();
		}
		
		return rate * huihe.getSolderRate();
	}
	
	/**
	 * 无刷新战法,发动成功的概率
	 * @param huihe
	 * @param zhanfas
	 * @return
	 */
	public static <T extends ZhanFa> float getCommRate(HuiHe huihe, T zhanfa) {
		float rate = 0;
		//可以发动战法
		if(huihe.getId() > zhanfa.getReady()) {
			rate = 1;
		} 
		//埋雷战法
		if(zhanfa instanceof MaiLeiZhanFa) {
			rate = 0;
			MaiLeiZhanFa mz = (MaiLeiZhanFa) zhanfa;
			int ready = zhanfa.getReady() + 1;
			if(huihe.getId() == ready) {
				rate = mz.getSpeed();
			//可能已发动过战法 存在同等或更高程度,不会叠加战法
			}else if(huihe.getId()> ready) {
				rate = 1 - zhanfa.getDoneRate();
			}
			//持续回合
			rate = rate * mz.getKeep();
		}
		
		return rate * huihe.getSolderRate();
	}
}