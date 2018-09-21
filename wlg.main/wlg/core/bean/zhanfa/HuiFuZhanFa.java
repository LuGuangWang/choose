package wlg.core.bean.zhanfa;

public class HuiFuZhanFa extends ZhanFa {

	private float huifuVal;
	
	public HuiFuZhanFa(String name, ZFType t, int ready, float doneRate, float huifuVal, int distance, Person persons) {
		super(name, t, ready, doneRate, 0.0f, distance, persons);
		this.huifuVal=huifuVal;
	}

	public float getHuifuVal() {
		return huifuVal;
	}

}
