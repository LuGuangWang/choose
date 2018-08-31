package wlg.core.calc;

import wlg.core.bean.HuiHe;
import wlg.core.bean.zhanfa.KongZhiAndHarmZhanFa;
import wlg.core.bean.zhanfa.KongZhiZhanFa;
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
	public static <T extends ZhanFa> float calcKongZhiHuiHe(HuiHe huihe, T... zhanfa) {
		float sum = 0;
		for(int i=0;i<zhanfa.length;i++) {
			if(zhanfa[i] instanceof KongZhiZhanFa) {
				KongZhiZhanFa b = (KongZhiZhanFa)zhanfa[i];
				for(int p:b.getPersons().getPersons()) {
					//不受伤害的概率
					float rate = CalcDoRate.getCommRate(huihe,b);
					float unHurt = p/1.0f/huihe.getWujiangCount();
					unHurt = unHurt>1 ? rate:rate*unHurt;
					float unHurtVal = unHurt * b.getDoneRate() * b.getKeep() * calcCommHuiHe(huihe.getAllFeng(unHurt),zhanfa);
					//受伤的概率
					float hurt = 1 - unHurt;
					float hurtVal = hurt *  calcCommHuiHe(huihe,zhanfa);
					
					sum += unHurtVal + hurtVal;
				}
			}
			if(zhanfa[i] instanceof KongZhiAndHarmZhanFa) {
				KongZhiAndHarmZhanFa b = (KongZhiAndHarmZhanFa)zhanfa[i];
				for(int p:b.getPersons().getPersons()) {
					//不受伤害的概率
					float rate = CalcDoRate.getCommRate(huihe,b);
					float unHurt = p/1.0f/huihe.getWujiangCount();
					unHurt = unHurt>1 ? rate:rate*unHurt;
					if(huihe.getId()>b.getKeephuihe()) {
						unHurt = 0;
					}
					float unHurtVal = unHurt * b.getDoneRate() * calcCommHuiHe(huihe.getFengGongji(unHurt),zhanfa);
					//受伤的概率
					float hurt = 1 - unHurt;
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
				float shuaxinRate = huihe.getShuaxinRate() * huihe.getId();
				if(z instanceof KongZhiAndHarmZhanFa) {
					KongZhiAndHarmZhanFa tmp = (KongZhiAndHarmZhanFa) z;
					if(tmp.getKeephuihe()+1 == huihe.getId()) {
						shuaxinRate += tmp.getExHarmRate();
					}else {
						shuaxinRate += z.getHarmRate();
					}
				}else {
					shuaxinRate += z.getHarmRate();
				}
				sum += rate * z.getHarmVal(shuaxinRate) * huihe.getSolderRate(z.getPosition());
			} else {
				float rate = CalcDoRate.getCommRate(huihe, z);
				if(z instanceof KongZhiAndHarmZhanFa) {
					KongZhiAndHarmZhanFa tmp = (KongZhiAndHarmZhanFa) z;
					if(tmp.getKeephuihe()+1 == huihe.getId()) {
						rate *= tmp.getHarmVal(tmp.getExHarmRate());
					}else {
						rate *= z.getHarmVal();
					}
				}else {
					rate *= z.getHarmVal();
				}
				sum += rate * huihe.getSolderRate(z.getPosition());
			}
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
						if(j!= i) {
							float rate = CalcDoRate.getCommRate(huihe, zhanfa[j]);
							if(zhanfa[j] instanceof KongZhiAndHarmZhanFa) {
								KongZhiAndHarmZhanFa tmp = (KongZhiAndHarmZhanFa) zhanfa[j];
								if(tmp.getKeephuihe()+1 == huihe.getId()) {
									sum += rate * b.getExVal(zhanfa[j]) * huihe.getSolderRate(b.getPosition());
								}
							}else {
								sum += rate * b.getExVal(zhanfa[j]) * huihe.getSolderRate(b.getPosition());
							}
						}
					}
				}
			}
		}
		return sum;
	}
	
	
	
}
