package wlg.core.bean.zhanfa;

public class WeiWuZhiShiZhanFa extends ZhanFa {

	private float addVal = 1.0f;//属性增加比
	private int addDis;//增加距离
	
	public WeiWuZhiShiZhanFa(String name, ZFType t, int ready, float doneRate, float addVal,int addDis, int distance,
			Person persons) {
		super(name, t, ready, doneRate, 0.0f, distance, persons);
		this.addVal = addVal;
		this.addDis = addDis;
	}

	public float getAddVal() {
		return addVal;
	}
	public int getAddDis() {
		return addDis;
	}

}
