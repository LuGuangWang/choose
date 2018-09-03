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
	private ZhanFa finalZf;//自身的战法
	private Map<Integer,ZhanFa> zhanfaMap = new HashMap<>();
	private int position = 3;//武将位置  大营1 中军2 前锋3
	private int finalp = 3;//原始武将位置  大营1 中军2 前锋3
	
	//TODO 添加攻击距离
	
	@SuppressWarnings("unchecked")
	public <T extends ZhanFa> WuJiang(String name,int speed,int defense,int attack,int strategy,T zhanfa) {
		this.name=name;
		this.attack=attack;
		this.defense=defense;
		this.strategy=strategy;
		this.speed=speed;
		T t = (T) zhanfa.clone();
		finalZf = t;
		addWuJiangProp(t);
		zhanfaMap.clear();
		zhanfaMap.put(1, t);
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
	public void changeOrder(int order) {
		zhanfaMap.forEach((k,v)->{
			v.setSpeed(order);
		});
		Conf.log("武将：" + name + " 行动顺序：" + order + " 速度："+ speed);
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
		T t = (T) z.clone();
		wj.addWuJiangProp(t);
		wj.zhanfaMap.put(1, finalZf);
		wj.zhanfaMap.put(2, t);
		return wj;
	}
	@SuppressWarnings("unchecked")
	public <T extends ZhanFa> WuJiang setThreeZhanFa(T z) {
		WuJiang wj = this.clone();
		T t = (T) z.clone();
		wj.addWuJiangProp(t);
		wj.zhanfaMap.put(1, finalZf);
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
	
	public WuJiang clone() {
		WuJiang o = null;
		try {
			o = (WuJiang) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
}
