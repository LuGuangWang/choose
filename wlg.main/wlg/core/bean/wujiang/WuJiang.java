package wlg.core.bean.wujiang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wlg.core.CheckUtil;
import wlg.core.bean.conf.Conf;
import wlg.core.bean.zhanfa.ZhanFa;
import wlg.core.calc.CalcJiaCheng;

public class WuJiang {
	private String name;
	private int speed;//速度
	private int defense;//防御
	private int attack;//攻击
	private int strategy;//谋略
	private Map<Integer,ZhanFa> zhanfaMap = new HashMap<>();
	private int position = 3;//武将位置  大营1 中军2 前锋3
	
	//TODO 添加攻击距离
	
	@SuppressWarnings("unchecked")
	public <T extends ZhanFa> WuJiang(String name,int speed,int defense,int attack,int strategy,T zhanfa) {
		this.name=name;
		this.attack=attack;
		this.defense=defense;
		this.strategy=strategy;
		this.speed=speed;
		T t = (T) zhanfa.clone();
		addWuJiangProp(t);
		zhanfaMap.put(1, t);
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public void changeOrder(int order) {
		zhanfaMap.forEach((k,v)->{
			v.setSpeed(order);
		});
	}
	public String getName() {
		return name;
	}
	public int getSpeed() {
		return speed;
	}
	public int getDefense() {
		return defense;
	}
	public int getAttack() {
		return attack;
	}
	public int getStrategy() {
		return strategy;
	}
	
	public void addJiaCheng() {
		List<ZhanFa> fashujiacheng = new ArrayList<>();
		zhanfaMap.forEach((k,v)->{
			if(CheckUtil.isFaShuJiaCheng(v)) {
				fashujiacheng.add(v);
			}
		});
		float jiachengVal = CalcJiaCheng.getJiaChengVal(fashujiacheng);
		zhanfaMap.forEach((k,v)->{
			if(!fashujiacheng.contains(v)) {
				Conf.log("==== 战法 "+v.getName() + " 增加伤害" + jiachengVal);
				v.setExHarmRate(v.getExHarmRate()+jiachengVal);
				v.setHarmRate(v.getHarmRate()+jiachengVal);
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public <T extends ZhanFa> WuJiang setSecondZhanFa(T z) {
		T t = (T) z.clone();
		addWuJiangProp(t);
		zhanfaMap.put(2, t);
		return this;
	}
	@SuppressWarnings("unchecked")
	public <T extends ZhanFa> WuJiang setThreeZhanFa(T z) {
		T t = (T) z.clone();
		addWuJiangProp(t);
		zhanfaMap.put(3, t);
		return this;
	}
	/**
	 * 添加加成伤害
	 * @param z
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends ZhanFa> WuJiang addJiaCheng(T z) {
		T t = (T) z.clone();
		addWuJiangProp(t);
		zhanfaMap.put(3, t);
		return this;
	}

	private <T extends ZhanFa> void addWuJiangProp(T z) {
		z.setSpeed(speed);
		z.setAttack(attack);
		z.setStrategy(strategy);
		z.setDefense(defense);
	}
	
	public float getWJHarmVal() {
		return attack * Conf.s_rate;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends ZhanFa> T[] getZhanfa() {
		return (T[]) zhanfaMap.values().toArray(new ZhanFa[zhanfaMap.size()]);
	}
	
	public String toKey() {
		StringBuilder s = new StringBuilder();
		switch(position) {
		case 1:
			s.append("大营 ");
			break;
		case 2:
			s.append("中军 ");
			break;
		case 3:
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
}
