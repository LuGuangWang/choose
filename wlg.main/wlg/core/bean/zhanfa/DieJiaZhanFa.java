package wlg.core.bean.zhanfa;

import wlg.core.bean.conf.Conf;
import wlg.core.calc.CalCDistance;

/**
 * 叠加伤害
 * @author seven
 *
 */
public class DieJiaZhanFa extends ZhanFa {
	private float baseVal = 0.0f;
	private int keep = 0;
	
	public DieJiaZhanFa(String name, ZFType t, int ready, float doneRate, float harmVal,int keep,float baseVal, int distance, Person persons) {
		super(name, t, ready, doneRate, harmVal, distance, persons);
		this.keep = keep;
		this.baseVal = baseVal;
	}
	
	public float getHarmVal() {
		UpVal upVal = new UpVal();
		upVal.setAddStrategyVal(1.0f);
		return getHarmVal(this.getHarmRate(),upVal);
	}
	/**
	 * 当前战法伤害
	 * @return
	 */
	public float getHarmVal(float harmVal,UpVal upVal) {
		float sum = 0.0f;
		//伤害值
		harmVal += upVal.getAddAllWjVal();
		//发动概率
		float pval = this.getDoneRate() + upVal.getDayingUpZFVal();
		pval = addShuXingVal(pval,upVal);
		
		int p = 1;//实际伤害是单体伤害
		int distance = CalCDistance.calcDistance(this.getDistance(), this.getPosition());
		p = Math.min(p, distance);
		if(p>0) {
			float baseVal = harmVal * pval;
			for(int i=0;i<keep;i++) {
				sum += baseVal * (1.0f + i*this.baseVal);
			}
		}
		
		Conf.log("========战法"+this.getName()+" 单次杀伤力："+ sum + " 原始伤害值:"+ this.getFinalHarmVal() + " 当前伤害值:"+harmVal);
		return sum;
	}
}
