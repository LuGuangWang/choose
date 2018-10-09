package wlg.core.bean.wujiang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wlg.core.CheckUtil;
import wlg.core.bean.conf.Conf;
import wlg.core.bean.zhanfa.ZFType;
import wlg.core.bean.zhanfa.ZhanFa;
import wlg.core.calc.CalcJiaCheng;

public class WuJiang implements Cloneable{
	private String name;
	private int speed;//速度
	private int defense;//防御
	private int attack;//攻击
	private int strategy;//谋略
	private final ZhanFa finalZf;//自身的战法
	private Map<Integer,ZhanFa> zhanfaMap = new HashMap<>();
	private int position = Conf.qianfeng;//武将位置  大营1 中军2 前锋3
	private int finalp = Conf.qianfeng;//原始武将位置  大营1 中军2 前锋3
	private int distance = 1;//攻击距离
	//武将总兵力
	private float totalCount = Conf.totalCount;//总兵力
	//本武将兵力损失值
	private float sunshiCount = 0.0f;
	//法术免疫控制的能力值
	private float mianyiFSVal = Conf.mianyi_fashu;
	//攻击免疫控制的能力值
	private float mianyiGJVal = Conf.mianyi_gongji;
	//免疫规避的能力值
	private float mianyiGBVal = Conf.mianyi_guibi;
	
	private WBType wbType;//兵种
	private WZType wzType;//阵营
	
	private float zishenlianjiVal = 0.0f;//武将自身连击值
	
	/**
	 * 
	 * @param name 名字
	 * @param wzType 阵营
	 * @param wbType 兵种
	 * @param speed 速度
	 * @param defense 防御
	 * @param attack 攻击
	 * @param strategy 策略
	 * @param distance 攻击距离
	 * @param zhanfa 战法
	 */
	@SuppressWarnings("unchecked")
	public <T extends ZhanFa> WuJiang(String name,WZType wzType,WBType wbType,int speed,int defense,int attack,int strategy,int distance,T zhanfa) {
		this.name=name;
		this.attack=attack;
		this.defense=defense;
		this.strategy=strategy;
		this.speed=speed;
		this.distance=distance;
		this.totalCount = Conf.totalCount;
		this.sunshiCount = 0.0f;
		this.wbType=wbType;
		this.wzType=wzType;
		T t = (T) zhanfa.clone();
		addWuJiangProp(t);
		zhanfaMap.clear();
		finalZf = t.clone();
		zhanfaMap.put(1, t);
	}
	
	public WBType getWbType() {
		return wbType;
	}
	public WZType getWzType() {
		return wzType;
	}
	public int getDistance() {
		return distance;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		Conf.log("=======武将 " + name + " 位置发生变化:" + this.position + " -> " + position);
		zhanfaMap.forEach((k,v)->{
			v.setPosition(position);
		});
		this.position = position;
	}
	public void setFinalp(int finalp) {
		this.finalp = finalp;
	}
	public int getFinalp() {
		return finalp;
	}
	public String getName() {
		return name;
	}
	public int getSpeed() {
		return speed;
	}
	public float getSunshiCount() {
		return sunshiCount;
	}
	public float getMianyiFSVal() {
		return mianyiFSVal;
	}
	public void setMianyiFSVal(float mianyiFSVal) {
		mianyiFSVal = mianyiFSVal>1.0f?1.0f:mianyiFSVal;
		if(this.mianyiFSVal<mianyiFSVal) {
			Conf.log("===========更新武将免疫法术的能力值：" + this.mianyiFSVal + " -> " + mianyiFSVal);
			this.mianyiFSVal = mianyiFSVal;
		}
	}
	public float getMianyiGJVal() {
		return mianyiGJVal;
	}
	public void setMianyiGJVal(float mianyiGJVal) {
		mianyiGJVal = mianyiGJVal>1.0f?1.0f:mianyiGJVal;
		if(this.mianyiGJVal < mianyiGJVal) {
			Conf.log("===========更新武将免疫攻击的能力值：" + this.mianyiGJVal + " -> " + mianyiGJVal);
			this.mianyiGJVal = mianyiGJVal;
		}
	}
	public float getMianyiGBVal() {
		return mianyiGBVal;
	}
	public void setMianyiGBVal(float mianyiGBVal) {
		mianyiGBVal = mianyiGBVal>1.0f?1.0f:mianyiGBVal;
		if(this.mianyiGBVal<mianyiGBVal) {
			Conf.log("===========更新武将免疫规避的能力值：" + this.mianyiGBVal + " -> " + mianyiGBVal);
			this.mianyiGBVal = mianyiGBVal;
		}
	}

	//按最小伤害更新
	public void setSunshiCount(float sunshiCount) {
		if(this.sunshiCount==0 || sunshiCount<this.sunshiCount) {
			Conf.log("========更新本回合武将" + name + "损失人数：" + this.sunshiCount + " -> " + sunshiCount);
			this.sunshiCount = sunshiCount;
		}
	}
	public void resetSunshiCount() {
		this.sunshiCount = 0.0f;
	}
	public void resetmianyiVal() {
		//免疫法术配置
		float mianyiVal = 1.0f * this.position * this.speed/Conf.base_speed;
		this.mianyiFSVal = mianyiVal > Conf.mianyi_fashu?Conf.mianyi_fashu:mianyiVal;
		this.mianyiGJVal = this.mianyiFSVal;
		
		this.mianyiGBVal = Conf.mianyi_guibi;
	}
	public int getDefense() {
		return defense;
	}
	public float getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(float totalCount) {
		this.totalCount = totalCount;
	}
	public int getAttack() {
		return attack;
	}
	public int getStrategy() {
		return strategy;
	}
	public float getZishenlianjiVal() {
		return zishenlianjiVal;
	}
	public void setZishenlianjiVal(float zishenlianjiVal) {
		if(zishenlianjiVal>this.zishenlianjiVal) {
			Conf.log("====更新武将自身连击值：" + this.zishenlianjiVal + " -> " + zishenlianjiVal);
			this.zishenlianjiVal = zishenlianjiVal;
		}
	}
	//对自身战法加成
	public void addJiaCheng() {
		List<ZhanFa> fashujiacheng = new ArrayList<>();
		zhanfaMap.forEach((k,v)->{
			if(CheckUtil.isFaShuJiaCheng(v)) {
				fashujiacheng.add(v);
			}
		});
		float jiachengVal = CalcJiaCheng.getJiaChengVal(fashujiacheng);
		zhanfaMap.forEach((k,v)->{
			if(!fashujiacheng.contains(v) && CheckUtil.isStrategy(v)) {
				ZhanFa v1 = v.clone();
				Conf.log("==== 战法 "+v1.getName() + " 初始伤害值：" + v1.getFinalHarmVal()  + " 初始额外伤害值：" + v1.getFinalExHarmVal());
				if(!v1.getT().equals(ZFType.ZhuDong_FaShu_GongJi)) {
					if(v1.getFinalExHarmVal()>0)
						v1.setExHarmVal(v1.getFinalExHarmVal()+jiachengVal);
				}
				if(v1.getFinalHarmVal()>0)
					v1.setHarmRate(v1.getFinalHarmVal()+jiachengVal);
				Conf.log("==== 战法 "+v1.getName() + " 伤害值：" + v1.getHarmRate()  + " 额外伤害值：" + v1.getExHarmVal() + " 增加伤害" + jiachengVal);
				zhanfaMap.put(k, v1);
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public <T extends ZhanFa> WuJiang setSecondZhanFa(T z) {
		WuJiang wj = this.clone();
		wj.totalCount = Conf.totalCount;
		wj.sunshiCount = 0.0f;
		T t = (T) z.clone();
		wj.addWuJiangProp(t);
		wj.zhanfaMap.put(1, finalZf.clone());
		wj.zhanfaMap.put(2, t);
		return wj;
	}
	@SuppressWarnings("unchecked")
	public <T extends ZhanFa> WuJiang setThreeZhanFa(T z) {
		WuJiang wj = this.clone();
		wj.totalCount = Conf.totalCount;
		wj.sunshiCount = 0.0f;
		T t = (T) z.clone();
		wj.addWuJiangProp(t);
		wj.zhanfaMap.put(1, finalZf.clone());
		wj.zhanfaMap.put(3, t);
		return wj;
	}
	
	
	
	private <T extends ZhanFa> void addWuJiangProp(T z) {
		z.setSpeed(speed);
		z.setAttack(attack);
		z.setStrategy(strategy);
		z.setDefense(defense);
	}
	
	public float getWJHarmVal() {
		return attack * Conf.gongji_rate;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends ZhanFa> T[] getZhanfa() {
		return (T[]) zhanfaMap.values().toArray(new ZhanFa[zhanfaMap.size()]);
	}
	
	public String toKey() {
		StringBuilder s = new StringBuilder();
		switch(finalp) {
		case Conf.daying:
			s.append("大营 ");
			break;
		case Conf.zhongjun:
			s.append("中军 ");
			break;
		case Conf.qianfeng:
			s.append("前锋 ");
			break;
		}
		s.append(name).append(":");
		zhanfaMap.forEach((k,v)->{
			s.append(v.getName()).append(" ");
		});
		s.append("\n");
		return s.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder(name).append(":");
		zhanfaMap.forEach((k,v)->{
			s.append(v.getName()).append(" ");
		});
		s.append("伤害值:");
		return s.toString();
	}
	
	public WuJiang clone() {
		WuJiang o = null;
		try {
			o = (WuJiang) super.clone();
			Map<Integer,ZhanFa> zhanfaMap = new HashMap<>(this.zhanfaMap);
			o.zhanfaMap = zhanfaMap;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	public WuJiang reset() {
		this.zhanfaMap.clear();
		this.zhanfaMap.put(1, finalZf.clone());
		this.totalCount = Conf.totalCount;
		
		float mianyiVal = 1.0f * this.position * this.speed/Conf.base_speed;
		this.mianyiFSVal = mianyiVal > Conf.mianyi_fashu?Conf.mianyi_fashu:mianyiVal;
		this.mianyiGJVal = this.mianyiFSVal;
		this.mianyiGBVal = Conf.mianyi_guibi;
		
		this.sunshiCount = 0.0f;
		this.zishenlianjiVal = 0.0f;
		return this;
	}
	
	public WuJiang changeProp(int attack) {
		WuJiang wj = changeProp(attack,speed,strategy,defense);
		return wj;
	}
	
	public WuJiang changeProp(int attack, int speed, int strategy, int defense,int distance) {
		WuJiang wj = changeProp(attack,speed,strategy,defense);
		Conf.log("=====武将" + name + " 攻击距离发生变化：" + wj.distance + " -> " + distance);
		wj.distance = distance;
		return wj;
	}
	
	public String getZiDaiZFName() {
		return finalZf.getName();
	}
	
	//更新武将属性值
	public WuJiang changeProp(int attack, int speed, int strategy, int defense) {
		WuJiang wj = this.clone();
		Conf.log("=====武将" + name + " 攻击属性发生变化：" + wj.attack + " -> " + attack);
		Conf.log("=====武将" + name + " 速度属性发生变化：" + wj.speed + " -> " + speed);
		Conf.log("=====武将" + name + " 谋略属性发生变化：" + wj.strategy + " -> " + strategy);
		Conf.log("=====武将" + name + " 防御属性发生变化：" + wj.defense + " -> " + defense);
		
		wj.attack=attack;
		wj.speed = speed;
		wj.defense=defense;
		wj.strategy = strategy;
		
		wj.zhanfaMap.forEach((k,v)->{
			wj.addWuJiangProp(v);
		});
		
		return wj;
	}
}
