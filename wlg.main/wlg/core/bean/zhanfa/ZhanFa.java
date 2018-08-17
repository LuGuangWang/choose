package wlg.core.bean.zhanfa;

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
	private float doneRate = 0;
	// 伤害率
	private float harmRate = 0;
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
		this.harmRate = harmRate;
		this.persons = persons;
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
		return harmRate;
	}

	public Person getPersons() {
		return persons;
	}

	public void setPersons(Person persons) {
		this.persons = persons;
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

	@Override
	public float getExVal(ZhanFa other) {
		return 0;
	}

	@Override
	public float getHarmVal() {
		return getShuaXinVal(this.harmRate);
	}

	public float getShuaXinVal(float harmRate) {
		float sum = 0;
		float pval = this.doneRate * harmRate;
		pval = calcTypeVal(pval);
		if (persons.getPersons().length > 0) {
			int len = getPersons().getPersons().length;
			float rate = 1.0f / len;
			for (int i : getPersons().getPersons()) {
				sum += pval * rate * i;
			}
		}
		return sum;
	}

	protected float calcTypeVal(float val) {
		switch (t) {
		case attack:
			val *= attack;
			break;
		case zd_strategy:
			val *= strategy;
			break;
		default:
			break;
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
