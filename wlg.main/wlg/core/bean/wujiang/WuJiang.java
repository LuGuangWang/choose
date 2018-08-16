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
	
	public <T extends ZhanFa> WuJiang(String name,int speed,int defense,int attack,int strategy,T zhanfa) {
		this.name=name;
		this.attack=attack;
		this.defense=defense;
		this.strategy=strategy;
		this.speed=speed;
		zhanfaMap.put(1, zhanfa);
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
	public <T extends ZhanFa> WuJiang setSecondZhanFa(T z) {
		zhanfaMap.put(2, z);
		return this;
	}
	public <T extends ZhanFa> WuJiang setThreeZhanFa(T z) {
		zhanfaMap.put(3, z);
		return this;
	}
	@SuppressWarnings("unchecked")
	public <T extends ZhanFa> T[] getZhanfa() {
		return (T[]) zhanfaMap.values().toArray(new ZhanFa[2]);
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder(name);
		s.append(" ").append(zhanfaMap.get(2).getName());
		s.append(" ").append(zhanfaMap.get(3).getName());
		s.append(" 伤害值:");
		return s.toString();
	}
}
