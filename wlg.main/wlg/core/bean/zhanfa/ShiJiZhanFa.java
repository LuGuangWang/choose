package wlg.core.bean.zhanfa;

public class ShiJiZhanFa extends ZhanFa {

	private int wjPosition;//加成大营
	private float upVal;//法术伤害加成值
	private float downVal;//减伤值
	private int keephuihe;
	
	public ShiJiZhanFa(String name, ZFType t, int ready,float doneRate, float upVal,float downVal, int wjPosition, int distance, Person persons,int keephuihe) {
		super(name, t, ready, doneRate, 0.0f, distance, persons);
		this.wjPosition = wjPosition;
		this.upVal = upVal;
		this.downVal= downVal;
		this.keephuihe=keephuihe;
	}

	public int getKeephuihe() {
		return keephuihe;
	}
	public int getWjPosition() {
		return wjPosition;
	}
	public float getUpVal() {
		return upVal;
	}
	public float getDownVal() {
		return downVal;
	}

}
