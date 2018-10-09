package wlg.core.bean.zhanfa;

public class ShengBingQiuZhan extends ZhanFa {

	private float skipRate;
	private float addShangHai;
	
	public ShengBingQiuZhan(String name, ZFType t, int ready, float doneRate, float skipRate, int distance,
			Person persons,float addShangHai) {
		super(name, t, ready, doneRate, 0.0f, distance, persons);
		this.addShangHai = addShangHai;
		this.skipRate = skipRate;
	}

	public float getSkipRate() {
		return skipRate;
	}
	public float getAddShangHai() {
		return addShangHai;
	}

}
