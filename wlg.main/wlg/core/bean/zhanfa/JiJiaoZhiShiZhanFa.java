package wlg.core.bean.zhanfa;

import wlg.core.bean.conf.Conf;

public class JiJiaoZhiShiZhanFa extends ZhanFa {

	public JiJiaoZhiShiZhanFa(String name, ZFType t, int ready, float doneRate, float harmVal, Person persons,
			float exRate, float exHarmRate) {
		super(name, t, ready, doneRate, harmVal, persons, exRate, exHarmRate);
	}

	@Override
	public float getHarmVal(float harmVal) {
		float sum = 0.0f;
		float harmval = this.getDoneRate() * harmVal  * this.getStrategy() * Conf.fashu_rate;
		float exharmval = this.getExRate() * this.getExHarmVal() * this.getAttack() * Conf.gongji_rate;
		
		sum = harmval + exharmval;
		
		float initharmVal = this.getFinalExHarmVal() + this.getFinalHarmVal();
		float curharmVal  = harmVal + this.getExHarmVal();
		Conf.log("========战法"+this.getName()+" 单次杀伤力："+ sum + " 原始伤害值:"+ initharmVal + " 当前伤害值:"+curharmVal);
		return sum;
	}
}
