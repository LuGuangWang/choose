package wlg.core.bean.zhanfa;

import wlg.core.CheckUtil;
import wlg.core.bean.conf.Conf;
import wlg.core.calc.CalCDistance;

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
	//攻击距离
	private int distance = 1;
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
	
	/**
	 * 
	 * @param name 名称
	 * @param t 战法类型
	 * @param ready 准备回合
	 * @param doneRate 发动概率
	 * @param harmVal 伤害值
	 * @param distance 攻击距离
	 * @param persons 攻击目标
	 */
	public ZhanFa(String name, ZFType t, int ready, float doneRate, float harmVal,int distance, Person persons) {
		this.t = t;
		this.name = name;
		this.ready = ready;
		this.doneRate = doneRate;
		this.harmVal = harmVal;
		this.persons = persons;
		this.distance = distance;
		
		this.finalHarmVal = this.harmVal;
	}
	
	public ZhanFa(String name, ZFType t, int ready, float doneRate, float harmVal, int distance,Person persons,float exRate,float exHarmRate) {
		this.t = t;
		this.name = name;
		this.ready = ready;
		this.doneRate = doneRate;
		this.harmVal = harmVal;
		this.persons = persons;
		this.exRate = exRate;
		this.exHarmVal=exHarmRate;
		this.distance = distance;
		
		this.finalHarmVal = this.harmVal;
		this.finalExHarmVal = this.exHarmVal;
	}

	public int getDistance() {
		return distance;
	}
	public float getExHarmVal() {
		return exHarmVal;
	}
	public float getExRate() {
		return exRate;
	}
	public void setExHarmVal(float exHarmVal) {
		this.exHarmVal = exHarmVal;
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
	public int getSpeed() {
		return speed;
	}
	public float getSpeedVal() {
		float val = this.speed*1.0f/Conf.base_speed;
		return val >1?1.0f:val;
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
		Conf.log("=======战法 " + name + " 位置发生变化:" + this.position + " -> " + position);
		this.position = position;
	}

	@Override
	public float getExVal(ZhanFa other) {
		return 0.0f;
	}

	@Override
	public float getHarmVal() {
		UpVal upVal = new UpVal();
		upVal.setAddStrategyVal(1.0f);
		return getHarmVal(this.harmVal,upVal);
	}
	
	/**
	 * 指定额外伤害值
	 * @param other
	 * @param exharmVal
	 * @return
	 */
	public float getExVal(ZhanFa other, float exharmVal,UpVal upVal) {
		float sum = 0.0f;
		if(other.getHarmRate()>0) {
			//伤害值
			exharmVal += upVal.getAddAllWjVal();
			//发动概率
			float val = exharmVal*(this.getDoneRate() + upVal.getDayingUpZFVal())*other.getDoneRate();
			val = addShuXingVal(val,upVal);
			int[] persons = other.getPersons().getPersons();
			
			int[] ps = this.getPersons().getPersons();
			
			float orate = 1.0f/persons.length;
			float prate = 1.0f/ps.length;
			
			for(int i : persons) {
				for(int p:ps) {
					int distance = CalCDistance.calcDistance(this.getDistance(), this.getPosition());
					if(distance<=0) {
						continue;
					}else {
						p = Math.min(p, distance);
					}
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
		Conf.log("========战法"+name+" 单次额外杀伤力："+ sum + " 原始额外伤害值:"+ finalExHarmVal + " 当前额外伤害值:"+exharmVal);
		return sum;
	}
	/**
	 * 指定伤害率的伤害值
	 * @param harmVal
	 * @return
	 */
	public float getHarmVal(float harmVal,UpVal upVal) {
		return getHarmVal(this.doneRate, harmVal, upVal);
	}
	
	public float getHarmVal(float doneRate,float harmVal,UpVal upVal) {
		float sum = 0.0f;
		//伤害值
		harmVal += upVal.getAddAllWjVal();
		//发动概率
		float done = doneRate + upVal.getDayingUpZFVal();
		done = done>1.0f?1.0f:done;
		float pval = done * harmVal;
		pval = addShuXingVal(pval,upVal);
		if (persons.getPersons().length > 0) {
			int len = getPersons().getPersons().length;
			float rate = 1.0f / len;
			for (int i : getPersons().getPersons()) {
				int distance = CalCDistance.calcDistance(this.getDistance(), this.getPosition());
				if(distance<=0) {
					continue;
				}else {
					i = Math.min(i, distance);
				}
				sum += pval * rate * i;
			}
		}
		Conf.log("========战法"+name+" 单次杀伤力："+ sum + " 原始伤害值:"+ finalHarmVal + " 当前伤害值:"+harmVal);
		return sum;
	}
	
	public boolean isZhuDong() {
		String zfType = this.t.toString().toLowerCase();
		return zfType.startsWith("zhudong_");
	}
	
	/**
	 * 不同属性的攻击
	 * @param val
	 * @return
	 */
	public float addShuXingVal(float val,UpVal upVal) {
		if(CheckUtil.isStrategy(this)) {
			float newStrategy1 = strategy * upVal.getAddStrategyVal();
			float newStrategy2 = strategy + upVal.getAddQuanShuXingVal();
			//数值替换
			float newStrategy = Math.max(newStrategy1, newStrategy2);
			
			val *= newStrategy * Conf.fashu_rate;
		}else if(CheckUtil.isAttack(this)) {
			val *= (attack + upVal.getAddQuanShuXingVal()) * Conf.gongji_rate;
		}else if(CheckUtil.isZeroHarm(this)) {
			val = 0.0f;
		}
		return val;
	}

	public boolean equals(Object obj) {
		return this.getName().equals(((ZhanFa)obj).getName());
	}
	
	public int hashCode() {
		return this.name.hashCode();
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

	@Override
	public int getChiXuHuihe() {
		return 10;
	}

	@Override
	public float getUpGongJiRate() {
		return 0.0f;
	}

	@Override
	public float getUpVal() {
		return 0;
	}

	@Override
	public int getKeephuihe() {
		return 1;
	}
}
