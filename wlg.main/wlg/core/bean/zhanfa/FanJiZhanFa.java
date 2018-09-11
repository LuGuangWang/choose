package wlg.core.bean.zhanfa;

import wlg.core.bean.conf.Conf;
import wlg.core.calc.CalCDistance;

/**
 * 被动反击战法
 * @author seven
 *
 */
public class FanJiZhanFa extends ZhanFa {

	public FanJiZhanFa(String name, ZFType t, int ready, float doneRate, float harmVal, int distance, Person persons) {
		super(name, t, ready, doneRate, harmVal, distance, persons);
	}

	/**
	 * 当前战法伤害
	 * @return
	 */
	public float getHarmVal() {
		return getHarmVal(this.getHarmVal());
	}
	/**
	 * 当前战法伤害
	 * @return
	 */
	public float getHarmVal(float harmVal) {
		float sum = 0.0f;
		float pval = this.getDoneRate() * harmVal;
		pval = addShuXingVal(pval);
		int i = 1;
		int distance = CalCDistance.calcDistance(this.getDistance(), this.getPosition());
		i = Math.min(i, distance);
		sum = pval * i;
		Conf.log("========战法"+this.getName()+" 单次杀伤力："+ sum + " 原始伤害值:"+ this.getFinalHarmVal() + " 当前伤害值:"+harmVal);
		return sum;
	}
}
