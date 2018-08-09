package wlg.core.bean;
/**
 * 普通战法
 * @author seven
 *
 */
public class CommZhanFa {
	//可战斗次数
	private int times = 8;
	//发动概率
	private float doneRate = 0;
	//伤害率
	private float harmRate = 0;
	//打击队伍数  eg.声东击西
	private Person persons = new Person(1);
	
	//触发增益伤害率
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
	public CommZhanFa(int times,float doneRate,float harmRate,Person persons,float exRate,float exHarmRate) {
		this(times,doneRate,harmRate,persons);
		this.exHarmRate=exHarmRate;
		this.exRate = exRate;
	}
	/**
	 * @param doneRate	发动概率
	 * @param harmRate	伤害率
	 * @param persons	打击队伍数
	 */
	public CommZhanFa(int times,float doneRate,float harmRate,Person persons) {
		this.times=times;
		this.doneRate = doneRate;
		this.harmRate = harmRate;
		this.persons=persons;
	}
	
	public float getDoneRate() {
		return doneRate;
	}
	public float getHarmRate() {
		return harmRate;
	}
	public Person getPersons() {
		return persons;
	}
	public void setPersons(Person persons) {
		this.persons = persons;
	}
	public float getExHarmRate() {
		return exHarmRate;
	}
	public float getExRate() {
		return exRate;
	}
	public int getTimes() {
		return times;
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
		float pval = this.doneRate*this.harmRate;
		float exVal = this.exHarmRate*this.exRate;
		float sum = 0;
		if(persons.getPersons().length>0) {
			int len = persons.getPersons().length;
			float rate = 1.0f/len;
			for(int i : persons.getPersons()) {
				sum += pval * rate * i;
				//增益伤害
				if(!isByOther()) {
					sum += exVal* rate * i;
				}
			}
		}
		return sum * times;
	}
	/**
	 * 当前战法增益伤害
	 * @return
	 */
	public float getExVal(CommZhanFa other) {
		float val = this.doneRate*other.getDoneRate();
		float sum = 0;
		int[] persons = other.getPersons().getPersons();
		if(persons.length>0) {
			int len = persons.length;
			float rate = 1/len;
			for(int i : persons) {
				sum += val * rate * i;
			}
		}
		return sum * times;
	}
}
