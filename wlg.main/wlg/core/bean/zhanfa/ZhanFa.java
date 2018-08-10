package wlg.core.bean.zhanfa;

import wlg.core.calc.CalcHarm;

/**
 * 普通战法
 * @author seven
 *
 */
public class ZhanFa implements CalcHarm{
	// 发动概率
	private float doneRate = 0;
	// 伤害率
	private float harmRate = 0;
	// 打击队伍数 
	private Person persons = new Person(1);
	//战法准备回合数
	private int ready = 0;
	/**
	 * @param doneRate	发动概率
	 * @param harmRate	伤害率
	 * @param persons	打击队伍数
	 */
	public ZhanFa(int ready,float doneRate,float harmRate,Person persons) {
		this.ready=ready;
		this.doneRate = doneRate;
		this.harmRate = harmRate;
		this.persons=persons;
	}
	
	public int getReady() {
		return ready;
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

	@Override
	public float getExVal(ZhanFa other) {
		return 0;
	}

	@Override
	public float getHarmVal() {
		float pval = this.doneRate*this.harmRate;
		float sum = 0;
		if(persons.getPersons().length>0) {
			int len = getPersons().getPersons().length;
			float rate = 1.0f/len;
			for(int i : getPersons().getPersons()) {
				sum += pval * rate * i;
			}
		}
		return sum;
	}
}
