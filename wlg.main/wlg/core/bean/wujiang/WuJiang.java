package wlg.core.bean.wujiang;

import wlg.core.bean.zhanfa.ZhanFa;

public class WuJiang<T1 extends ZhanFa,T2 extends ZhanFa,T3 extends ZhanFa> {
	private int speed;//速度
	private int defense;//防御
	private int attack;//攻击
	private int strategy;//谋略
	private T1 zhanfa1;//自带战法 
	private T2 zhanfa2;//战法 2 
	private T3 zhanfa3;//战法 3
	
	public WuJiang(int speed,int defense,int attack,int strategy,T1 zhanfa1) {
		this.attack=attack;
		this.defense=defense;
		this.strategy=strategy;
		this.speed=speed;
		this.zhanfa1=zhanfa1;
	}
	
	public T2 getZhanfa2() {
		return zhanfa2;
	}
	public void setZhanfa2(T2 zhanfa2) {
		this.zhanfa2 = zhanfa2;
	}
	public T3 getZhanfa3() {
		return zhanfa3;
	}
	public void setZhanfa3(T3 zhanfa3) {
		this.zhanfa3 = zhanfa3;
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
	public T1 getZhanfa1() {
		return zhanfa1;
	}
}
