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
		sum += calcExVal(huihe,zhanfa);
		return sum;
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends ZhanFa> float calcExVal(HuiHe huihe,T... zhanfa) {
		float sum = 0;
		if(zhanfa.length>1) {
			for(int i=0;i<zhanfa.length;i++) {
				T b = zhanfa[i];
				if(b instanceof ZengYiZhanFa) {
					for(int j=0;j<zhanfa.length;j++) {
						if(j!= i) {
							float rate = getSuccessRate(huihe, zhanfa[j]);
							sum += rate * b.getExVal(zhanfa[j]);
						}
					}
				}
			}
		}
		return sum;
	}
	
	/**
	 * 成功发动战法的概率
	 * @param huihe
	 * @param zhanfas
	 * @return
	 */
	private static <T extends ZhanFa> float getSuccessRate(HuiHe huihe, T zhanfa) {
		float rate = 0;
		//可以发动战法
		if(huihe.getId() > zhanfa.getReady()) {
			rate = 1;
		} 
		//埋雷战法
		if(zhanfa instanceof MaiLeiZhanFa) {
			rate = 0;
			int ready = zhanfa.getReady()+1;
			if(huihe.getId() == ready) {
				rate = huihe.getMaileiRate();
			//可能已发动过战法 存在同等或更高程度,不会叠加战法
			}else if(huihe.getId()> ready) {
				rate = 1 - zhanfa.getDoneRate();
			}
			//持续回合
			rate = rate * ((MaiLeiZhanFa) zhanfa).getKeep();
		}
		
		return rate * huihe.getSolderRate();
	}
	
	/**
	 * 计算一回合的伤害值
	 * @param zhanfa
	 * @return
	 */
	@Deprecated
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
	
	@Deprecated
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
