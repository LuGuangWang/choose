package wlg.core.calc;

import wlg.core.bean.HuiHe;
import wlg.core.bean.zhanfa.MaiLeiZhanFa;
import wlg.core.bean.zhanfa.ZengYiZhanFa;
import wlg.core.bean.zhanfa.ZhanFa;

public class CalcOneHarm {
	
	/**
	 * 计算一回合的伤害值
	 * @param zhanfa
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends ZhanFa> float calcVal(HuiHe huihe,T... zhanfa) {
		float sum = 0;
		//主要伤害
		for(T z:zhanfa) {
			//当前回合战法发动成功的概率
			float rate = getSuccessRate(huihe, z);
			sum += rate * z.getHarmVal();
		}
		//增益伤害
		sum += calcExVal(zhanfa);
		return sum;
	}
	/**
	 * 成功发动战法的概率
	 * @param huihe
	 * @param z
	 * @return
	 */
	private static <T extends ZhanFa> float getSuccessRate(HuiHe huihe, T z) {
		float rate = 0;
		//第一次发动战法
		if(huihe.getId()== (z.getReady()+1)) {
			rate = 1;
			//埋雷战法
			if(z instanceof MaiLeiZhanFa) {
				rate = huihe.getMaileiRate();
			}
		}
		//可能已发动过战法 存在同等或更高程度,不会叠加战法	
		if(huihe.getId()> (z.getReady()+1)) {
			rate = 1 - z.getDoneRate();
		}
		return rate;
	}
	
	/**
	 * 计算一回合的伤害值
	 * @param zhanfa
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends ZhanFa> float calcVal(T... zhanfa) {
		float sum = 0;
		//主要伤害
		for(T z:zhanfa) {
			sum += z.getHarmVal();
		}
		//增益伤害
		sum += calcExVal(zhanfa);
		return sum;
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends ZhanFa> float calcExVal(T... zhanfa) {
		float sum = 0;
		if(zhanfa.length>1) {
			for(int i=0;i<zhanfa.length;i++) {
				T b = zhanfa[i];
				if(b instanceof ZengYiZhanFa) {
					for(int j=0;j<zhanfa.length;j++) {
						if(j!= i)
							sum += b.getExVal(zhanfa[j]);
					}
				}
			}
		}
		return sum;
	}
}
