package wlg.core.bean.zhanfa;

import wlg.core.bean.conf.Conf;

public class LianJiZhanFa extends ZhanFa {
	
	private float upGongJiVal;//攻击力增加值
	private int lianjiCount = 1;//普通攻击连击次数

	public LianJiZhanFa(String name, ZFType t, int ready, float doneRate, float upGongJiVal, int distance, Person persons,int lianjiCount) {
		super(name, t, ready, doneRate, 0.0f, distance, persons);
		this.upGongJiVal = upGongJiVal;
		this.lianjiCount = lianjiCount;
	}

	public float getUpGongJiVal() {
		return upGongJiVal;
	}
	public int getLianjiCount() {
		return lianjiCount;
	}

	/**
	 * 当前战法伤害
	 * @return
	 */
	public float getHarmVal() {
		return getHarmVal(this.getHarmRate(),1.0f);
	}
	/**
	 * 当前战法伤害
	 * @return
	 */
	public float getHarmVal(float harmVal,float addStrategyVal) {
		float sum = 0.0f;
		
		
		float pval = this.getAttack() * Conf.gongji_rate * (lianjiCount-1);
		
		sum = pval;
		Conf.log("========战法"+this.getName()+" 单次杀伤力："+ sum + " 原始伤害值:"+ this.getHarmRate() + " 当前伤害值:"+ harmVal);
		
		return sum;
	}
}
