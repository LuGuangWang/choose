package wlg.core.bean.zhanfa;

import wlg.core.bean.conf.Conf;

/**
 * 攻击的战法
 * @author seven
 *
 */
public class GongJiZhanFa extends ZhanFa {

	private float upGJVal = 1.0f;//提高攻击比
	private float otherAttackVal = 0.0f;//其他武将的平均攻击力
	private int lianjiCount;//普攻连击数
	
	public GongJiZhanFa(String name, ZFType t, int ready, float doneRate, float upGJVal, int distance, Person persons,int lianjiCount) {
		super(name, t, ready, doneRate, upGJVal, distance, persons);
		this.upGJVal = upGJVal;
		this.lianjiCount=lianjiCount;
	}

	public int getLianjiCount() {
		return lianjiCount;
	}
	public float getOtherAttackVal() {
		return otherAttackVal;
	}
	public void setOtherAttackVal(float otherAttackVal) {
		this.otherAttackVal = otherAttackVal;
	}
	public float getUpGJVal() {
		return upGJVal;
	}
	
	/**
	 * 当前战法伤害
	 * @return
	 */
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
		//受谋略影响
		float newUpGjVal = this.getStrategy() * upVal.getAddStrategyVal() / Conf.shuxing_suoxiao + this.upGJVal;
		
		harmVal = newUpGjVal * (this.getDoneRate()+upVal.getDayingUpZFVal()+ upVal.getAddAllWjVal());
		
		float pval = harmVal * this.getAttack() * Conf.gongji_rate;
		float otherval = harmVal * otherAttackVal * Conf.gongji_rate;
		
		sum = pval + otherval;
		Conf.log("========战法"+this.getName()+" 单次杀伤力："+ sum + " 原始伤害值:"+ harmVal + " 当前伤害值:"+ upGJVal);
		
		return sum;
	}

}
