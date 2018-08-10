package wlg.core.bean;
/**
 * 带增益的战法
 * @author seven
 *
 */
public class ZengYiZhanFa extends ZhanFa{
	//增益伤害率
	private float exHarmRate= 0;
	//增益发动概率
	private float exRate = 0;
	/**
	 * @param doneRate	发动概率
	 * @param harmRate	伤害率
	 * @param persons	打击队伍数
	 * @param exRate	增益发动概率
	 * @param exHarmRate  增益伤害率
	 */
	public ZengYiZhanFa(float doneRate,float harmRate,Person persons,float exRate,float exHarmRate) {
		super(doneRate,harmRate,persons);
		this.exHarmRate=exHarmRate;
		this.exRate = exRate;
	}
	
	public float getExHarmRate() {
		return exHarmRate;
	}
	public float getExRate() {
		return exRate;
	}
	/**
	 * 是否由其它战法触发
	 * @return
	 */
	public boolean isByOther() {
		return exHarmRate!=0 && exRate==0;
	}
	/**
	 * 当前战法的伤害
	 * @return
	 */
	public float getHarmVal() {
		float sum = super.getHarmVal();
		//增益伤害
		if(!isByOther()) {
			float exVal = this.exHarmRate*this.exRate;
			if(getPersons().getPersons().length>0) {
				int len = getPersons().getPersons().length;
				float rate = 1.0f/len;
				for(int i : getPersons().getPersons()) {
					sum += exVal* rate * i;
				}
			}
		}
		return sum;
	}
	/**
	 * 当前战法增益伤害
	 * @return
	 */
	public float getExVal(ZhanFa other) {
		float sum = 0;
		if(isByOther()) {
			float val = this.exHarmRate*this.getDoneRate()*other.getDoneRate();
			int[] persons = other.getPersons().getPersons();
			
			int[] ps = this.getPersons().getPersons();
			
			float orate = 1.0f/persons.length;
			float prate = 1.0f/ps.length;
			
			for(int i : persons) {
				for(int p:ps) {
					//实际可伤害人数
					int realP = Math.min(i, p);
					//可全部命中
					if(realP<=p) {
						sum += prate * orate * val * realP;
					}else {
						int total = 3;//初始化总队伍数
						for(int m=realP;m>0;m--) {
							float realRate = m*1.0f/total;
							sum += prate * orate * val * realRate;
							total --; 
						}
					}
				}
			}
		}
		return sum;
	}
}
