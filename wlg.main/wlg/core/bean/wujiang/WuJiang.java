package wlg.core.bean.wujiang;

import java.util.HashMap;
import java.util.Map;

import wlg.core.bean.conf.Conf;
import wlg.core.bean.zhanfa.KongZhiZhanFa;
import wlg.core.bean.zhanfa.ZhanFa;

public class WuJiang {
	private String name;
	private int speed;//速度
	private int defense;//防御
	private int attack;//攻击
	private int strategy;//谋略
	private Map<Integer,ZhanFa> zhanfaMap = new HashMap<>();
	private boolean hasJianshang = false;//是否携带减伤战法

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
		hasJianshang = t instanceof KongZhiZhanFa;
	}
	
	public void changeOrder(int order) {
		zhanfaMap.forEach((k,v)->{
			v.setSpeed(order);
		});
	}
	public boolean isHasJianshang() {
		return hasJianshang;
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
		hasJianshang = t instanceof KongZhiZhanFa;
		return this;
	}
	@SuppressWarnings("unchecked")
	public <T extends ZhanFa> WuJiang setThreeZhanFa(T z) {
		T t = (T) z.clone();
		addWuJiangProp(t);
		zhanfaMap.put(3, t);
		hasJianshang = t instanceof KongZhiZhanFa;
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
		StringBuilder s = new StringBuilder(name).append(":");
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
