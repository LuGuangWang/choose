package wlg.core.bean.zhanfa;

public class FanJiZhiCeZhanFa extends ZhanFa {

	private int keephuihe;
	private float downVal;
	
	public FanJiZhiCeZhanFa(String name, ZFType t, int ready, float doneRate, float downVal,int distance, Person persons,int keephuihe) {
		super(name, t, ready, doneRate, 0.0f,distance, persons);
		this.keephuihe = keephuihe;
		this.downVal=downVal;
	}

	public float getDownVal() {
		return downVal;
	}
	public int getKeephuihe() {
		return keephuihe;
	}
	
}
