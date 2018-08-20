package wlg.core.calc;

import wlg.core.bean.HuiHe;
import wlg.core.bean.zhanfa.ShuaXinZhanFa;
import wlg.core.bean.zhanfa.ZengYiZhanFa;
import wlg.core.bean.zhanfa.ZhanFa;

public class CalcHarm {
	
	/**
	 * 战法8回合总伤害
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
	public static <T extends ZhanFa> float calcPrimayVal(HuiHe huihe, T... zhanfa) {
		float sum = 0;
		//主要伤害
		for(int i=0;i<zhanfa.length;i++) {
			T z = zhanfa[i];
			if(huihe.getShuaxinRate() > 0) {
				float rate = CalcDoRate.getShuaXinRate(huihe, z);
				float shuaxinRate = huihe.getShuaxinRate() * huihe.getId() + z.getHarmRate();
				sum += rate * z.getShuaXinVal(shuaxinRate);
			}else {
				float rate = CalcDoRate.getCommRate(huihe, z);
				sum += rate * z.getHarmVal();
			}
			//减伤战法
//			sum *= huihe.getSolderRate();
		}
		return sum;
	}

	/**
	 * 增益伤害
	 * @param huihe
	 * @param zhanfa
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends ZhanFa> float calcExVal(HuiHe huihe,T... zhanfa) {
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
					
					//减伤战法
//					sum *= huihe.getSolderRate();
				}
			}
		}
		return sum;
	}
	
	
	
}
