package wlg.core.calc;

import wlg.core.bean.HuiHe;
import wlg.core.bean.zhanfa.ShuaXinZhanFa;
import wlg.core.bean.zhanfa.ZengYiZhanFa;
import wlg.core.bean.zhanfa.ZhanFa;

public class CalcHarm {
	
	/**
	 * 8回合总伤害
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends ZhanFa> float calcVal(T... zhanfa) {
		float sum = 0;
		HuiHe huihe = new HuiHe();
		for(int i=1;i<9;i++) {
			huihe.setId(i);
			sum += calcPrimayVal(huihe, zhanfa);
			sum += calcExVal(huihe,zhanfa);
		}
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
							float rate = CalcDoRate.getShuaXinRate(huihe, z);
							float shuaxinRate = ((ShuaXinZhanFa)s).getBaseRate() * huihe.getId();
							z.setHarmRate(z.getHarmRate()+shuaxinRate);
							sum += rate * z.getHarmVal();
						}
					}
				}
				
				if(!isShuaXin) {
					float rate = CalcDoRate.getCommRate(huihe, z);
					sum += rate * z.getHarmVal();
				}
			}
		}
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
						if(j!= i && !(zhanfa[j] instanceof ShuaXinZhanFa)) {
							float rate = CalcDoRate.getCommRate(huihe, zhanfa[j]);
							sum += rate * b.getExVal(zhanfa[j]);
						}
					}
				}
			}
		}
		return sum;
	}
	
	
	
}
