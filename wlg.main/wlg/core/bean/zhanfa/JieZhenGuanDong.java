package wlg.core.bean.zhanfa;

public class JieZhenGuanDong extends ZhanFa {
	//先手 无视规避 动摇
	private int keepHuihe = 0;
	private float xianshouVal = 0.0f;
	private float mianyiGBVal = 0.0f;
	
	public JieZhenGuanDong(String name, ZFType t, int ready, float doneRate, float harmVal, int distance,
			Person persons,int keepHuihe,float xianshouVal,float mianyiGBVal) {
		super(name, t, ready, doneRate, harmVal, distance, persons);
		this.keepHuihe = keepHuihe;
		this.xianshouVal = xianshouVal;
		this.mianyiGBVal = mianyiGBVal;
	}

	public int getKeepHuihe() {
		return keepHuihe;
	}
	public float getXianshouVal() {
		return xianshouVal;
	}
	public float getMianyiGBVal() {
		return mianyiGBVal;
	}
	@Override
	public int getChiXuHuihe() {
		return keepHuihe;
	}
}
