package wlg.core.bean.zhanfa;

public class HuBuGuanYou extends ZhanFa {

	private  float upGJVal;
	
	public HuBuGuanYou(String name, ZFType t, int ready, float doneRate, float upGJVal, int distance, Person persons) {
		super(name, t, ready, doneRate, 0.0f, distance, persons);
		this.upGJVal = upGJVal;
	}
	public float getUpGJVal() {
		return upGJVal;
	}

	
}
