package wlg.core.bean.zhanfa;

import wlg.core.bean.conf.Conf;

public class JiJiaoZhiShiZhanFa extends ZhanFa {

	public JiJiaoZhiShiZhanFa(String name, ZFType t, int ready, float doneRate, float harmVal, int distance,Person persons,
			float exRate, float exHarmRate) {
		super(name, t, ready, doneRate, harmVal, distance,persons, exRate, exHarmRate);
	}

	@Override
	public float getHarmVal(float harmVal,UpVal upVal) {
		float sum = 0.0f;
		//伤害值
		harmVal += upVal.getAddAllWjVal();
		float newStrategy = this.getStrategy() * upVal.getAddStrategyVal();
		float harmval = (this.getDoneRate()+upVal.getDayingUpZFVal()) * harmVal  * newStrategy * Conf.fashu_rate;
		float exharmval = this.getExRate() * this.getExHarmVal() * this.getAttack() * Conf.gongji_rate;
		
		sum = harmval + exharmval;
		
		float initharmVal = this.getFinalExHarmVal() + this.getFinalHarmVal();
		float curharmVal  = harmVal + this.getExHarmVal();
		Conf.log("========战法"+this.getName()+" 单次杀伤力："+ sum + " 原始伤害值:"+ initharmVal + " 当前伤害值:"+curharmVal);
		return sum;
	}
}
