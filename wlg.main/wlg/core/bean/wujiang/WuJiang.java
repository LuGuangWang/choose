package wlg.core.bean.wujiang;

import java.util.HashMap;
import java.util.Map;

import wlg.core.bean.zhanfa.ZhanFa;

public class WuJiang {
	private String name;
	private int speed;//速度
	private int defense;//防御
	private int attack;//攻击
	private int strategy;//谋略
	private Map<Integer,ZhanFa> zhanfaMap = new HashMap<>();
	private int order = 0;//武将速度的顺序
	
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
	
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
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

	private <T extends ZhanFa> void addWuJiangProp(T z) {
		z.setSpeed(speed);
		z.setAttack(attack);
		z.setStrategy(strategy);
		z.setDefense(defense);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends ZhanFa> T[] getZhanfa() {
		return (T[]) zhanfaMap.values().toArray(new ZhanFa[zhanfaMap.size()]);
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
