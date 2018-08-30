package wlg.core.calc;

import wlg.core.bean.HuiHe;
import wlg.core.bean.zhanfa.JianShangZhanFa;
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
			sum += calcCommHuiHe(huihe, zhanfa);
			sum += calcExVal(huihe,zhanfa);
		}
		return sum;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends ZhanFa> float calcJianShangHuiHe(HuiHe huihe, T... zhanfa) {
		float sum = 0;
		for(int i=0;i<zhanfa.length;i++) {
			if(zhanfa[i] instanceof JianShangZhanFa) {
				JianShangZhanFa b = (JianShangZhanFa)zhanfa[i];
				for(int p:b.getPersons().getPersons()) {
					//不受伤害的概率
					float rate = CalcDoRate.getCommRate(huihe,b);
					float unHurt = p/1.0f/huihe.getWujiangCount();
					unHurt = unHurt>1 ? 1:unHurt;
					float unHurtVal = unHurt * b.getDoneRate() * rate * b.getKeep() * calcCommHuiHe(huihe.getAllFeng(1.0f),zhanfa);
					//受伤的概率
					float hurt = 1 - rate * unHurt;
					float hurtVal = hurt *  calcCommHuiHe(huihe,zhanfa);
					
					sum += unHurtVal + hurtVal;
				}
			}
		}
		return sum;
	}

	@SuppressWarnings("unchecked")
	public static <T extends ZhanFa> float calcCommHuiHe(HuiHe huihe, T... zhanfa) {
		float sum = 0;
		//主要伤害
		for(int i=0;i<zhanfa.length;i++) {
			T z = zhanfa[i];
			if(huihe.getShuaxinRate() > 0) {
				float rate = CalcDoRate.getShuaXinRate(huihe, z);
				float shuaxinRate = huihe.getShuaxinRate() * huihe.getId() + z.getHarmRate();
				sum += rate * z.getHarmVal(shuaxinRate);
			}else {
				float rate = CalcDoRate.getCommRate(huihe, z);
				sum += rate * z.getHarmVal();
			}
		}
		return sum * huihe.getSolderRate();
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
				}
			}
		}
		return sum * huihe.getSolderRate();
	}
	
	
	
}
