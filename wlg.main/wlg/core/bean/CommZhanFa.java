package wlg.core.bean;
/**
 * 普通战法
 * @author seven
 *
 */
public class CommZhanFa {
	//发动概率
	private float doneRate = 0;
	//伤害率
	private float harmRate = 0;
	//打击队伍数  eg.声东击西
	private int[] persons = {0};
	
	//触发增益伤害率
	private float exHarmRate= 0;
	//增益发动概率
	private float exRate = 0;
	
	public CommZhanFa(float doneRate,float harmRate,int[] persons,float exHarmRate,float exRate) {
		this(doneRate,harmRate,persons);
		this.exHarmRate=exHarmRate;
		this.exRate = exRate;
	}
	
	public CommZhanFa(float doneRate,float harmRate,int[] persons) {
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
	public int[] getPersons() {
		return persons;
	}
	public float getExHarmRate() {
		return exHarmRate;
	}
	public float getExRate() {
		return exRate;
	}
	/**
	 * 当前战法的伤害
	 * @return
	 */
	public float getPrimaryVal() {
		float val = this.doneRate*this.harmRate;
		float sum = 0;
		if(persons.length>0) {
			int len = persons.length;
			float rate = 1/len;
			for(int i : persons) {
				sum += val * rate * i;
			}
		}
		return sum;
	}
	/**
	 * 当前战法增益伤害
	 * @return
	 */
	public float getExVal(float ) {
		
	}
}
