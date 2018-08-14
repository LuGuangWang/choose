package wlg.core.calc;

import wlg.core.bean.HuiHe;
import wlg.core.bean.zhanfa.MaiLeiZhanFa;
import wlg.core.bean.zhanfa.ShuaXinZhanFa;
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
		float sum = calcPrimayVal(huihe, zhanfa);
		//增益伤害
		sum += calcExVal(huihe,zhanfa);
		return sum;
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends ZhanFa> float calcPrimayVal(HuiHe huihe, T... zhanfa) {
		float sum = 0;
		boolean isShuaXin = false;
		//主要伤害
		for(int i=0;i<zhanfa.length;i++) {
			T z = zhanfa[i];
			if(z instanceof ShuaXinZhanFa) {
				//刷新战法没有伤害
			}else {
				//是否含有刷新战法
				for(int j=0;j<zhanfa.length;j++) {
					if(j!= i) {
						T s = zhanfa[j];
						if(s instanceof ShuaXinZhanFa) {
							isShuaXin = true;
							float rate = getShuaXinRate(huihe, z);
							float shuaxinRate = ((ShuaXinZhanFa)s).getBaseRate() * huihe.getId();
							z.setHarmRate(z.getHarmRate()+shuaxinRate);
							sum += rate * z.getHarmVal();
						}
					}
				}
				
				if(!isShuaXin) {
					float rate = getReadyRate(huihe, z);
					sum += rate * z.getHarmVal();
				}
			}
		}
		return sum;
	}
	
	private static <T extends ZhanFa> float getShuaXinRate(HuiHe huihe, T zhanfa) {
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

	@SuppressWarnings("unchecked")
	private static <T extends ZhanFa> float calcExVal(HuiHe huihe,T... zhanfa) {
		float sum = 0;
		if(zhanfa.length>1) {
			for(int i=0;i<zhanfa.length;i++) {
				T b = zhanfa[i];
				if(b instanceof ZengYiZhanFa) {
					for(int j=0;j<zhanfa.length;j++) {
						if(j!= i && !(zhanfa[j] instanceof ShuaXinZhanFa)) {
							float rate = getReadyRate(huihe, zhanfa[j]);
							sum += rate * b.getExVal(zhanfa[j]);
						}
					}
				}
			}
		}
		return sum;
	}
	
	/**
	 * 准备回合
	 * @param huihe
	 * @param zhanfas
	 * @return
	 */
	private static <T extends ZhanFa> float getReadyRate(HuiHe huihe, T zhanfa) {
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
