package wlg.core.bean.zhanfa;

import wlg.core.CheckUtil;
import wlg.core.bean.conf.Conf;

/**
 * 普通战法
 * 
 * @author seven
 *
 */
public class ZhanFa implements Harm, Cloneable {
	// 战法名称
	private String name;
	// 发动概率
	private float doneRate = 0.0f;
	// 伤害值
	private float harmVal = 0.0f;
	//不可改变的伤害值
	private float finalHarmVal = 0.0f;
	//增益伤害率
	private float exHarmVal= 0.0f;
	//不可变的额外伤害值
	private float finalExHarmVal = 0.0f;
	//增益发动概率
	private float exRate = 0.0f;
	// 打击队伍数
	private Person persons = new Person(1);
	// 战法准备回合数
	private int ready = 0;
	// 战法类型
	private ZFType t;

	/** ----- 武将的属性 ---- **/
	private int speed = 0;// 速度
	private int defense = 1;// 防御
	private int attack = 1;// 攻击
	private int strategy = 1;// 谋略
	private int position = 1;//武将位置武将位置  大营3 中军2 前锋1
	
	/** ------额外加成属性---- **/
	private float upStrategy = 0.0f;//提高策略伤害

	/**
	 * @param doneRate
	 *            发动概率
	 * @param harmRate
	 *            伤害率
	 * @param persons
	 *            打击队伍数
	 */
	public ZhanFa(String name, ZFType t, int ready, float doneRate, float harmRate, Person persons) {
		this.t = t;
		this.name = name;
		this.ready = ready;
		this.doneRate = doneRate;
		this.harmVal = harmRate;
		this.persons = persons;
		
		this.finalHarmVal = this.harmVal;
	}
	
	public ZhanFa(String name, ZFType t, int ready, float doneRate, float harmRate, Person persons,float exRate,float exHarmRate) {
		this.t = t;
		this.name = name;
		this.ready = ready;
		this.doneRate = doneRate;
		this.harmVal = harmRate;
		this.persons = persons;
		this.exRate = exRate;
		this.exHarmVal=exHarmRate;
		
		this.finalHarmVal = this.harmVal;
		this.finalExHarmVal = this.exHarmVal;
	}

	public float getExHarmRate() {
		return exHarmVal;
	}
	public float getExRate() {
		return exRate;
	}
	public void setExHarmRate(float exHarmRate) {
		this.exHarmVal = exHarmRate;
	}
	public void setExRate(float exRate) {
		this.exRate = exRate;
	}
	public float getFinalHarmVal() {
		return finalHarmVal;
	}
	public float getFinalExHarmVal() {
		return finalExHarmVal;
	}
	public String getName() {
		return name;
	}
	public ZFType getT() {
		return t;
	}
	public int getReady() {
		return ready;
	}
	public float getDoneRate() {
		return doneRate;
	}
	public float getHarmRate() {
		return harmVal;
	}
	public void setHarmRate(float harmRate) {
		this.harmVal = harmRate;
	}
	public Person getPersons() {
		return persons;
	}
	public void setPersons(Person persons) {
		this.persons = persons;
	}
	public float getUpStrategy() {
		return upStrategy;
	}
	public void setUpStrategy(float upStrategy) {
		this.upStrategy = upStrategy;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getStrategy() {
		return strategy;
	}
	public void setStrategy(int strategy) {
		this.strategy = strategy;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public float getExVal(ZhanFa other) {
		return 0.0f;
	}

	@Override
	public float getHarmVal() {
		return getHarmVal(this.harmVal);
	}
	/**
	 * 指定伤害率的伤害值
	 * @param harmVal
	 * @return
	 */
	public float getHarmVal(float harmVal) {
		float sum = 0.0f;
		float pval = this.doneRate * harmVal;
		pval = addShuXingVal(pval);
		if (persons.getPersons().length > 0) {
			int len = getPersons().getPersons().length;
			float rate = 1.0f / len;
			for (int i : getPersons().getPersons()) {
				sum += pval * rate * i;
			}
		}
		Conf.log("========战法"+name+"杀伤力："+ sum + "原始伤害值:"+ finalHarmVal + "伤害值:"+harmVal);
		return sum;
	}
	
	/**
	 * 不同属性的攻击
	 * @param val
	 * @return
	 */
	public float addShuXingVal(float val) {
		if(CheckUtil.isStrategy(this)) {
			val *= strategy * Conf.s_rate;
		}else if(CheckUtil.isZeroHarm(this)) {
			val = 0.0f;
		}
		return val;
	}

	public ZhanFa clone() {
		ZhanFa o = null;
		try {
			o = (ZhanFa) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
}
