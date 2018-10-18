package wlg.core.bean.zhanfa;

public class ZhanBiZhanFa extends ZhanFa {

	private float konzhiVal;
	private int keephuihe;
	
	public ZhanBiZhanFa(String name, ZFType t, int ready, float doneRate, float konzhiVal, int distance, Person persons,int keephuihe) {
		super(name, t, ready, doneRate, 0.0f, distance, persons);
		this.konzhiVal = konzhiVal;
		this.keephuihe=keephuihe;
	}

	public float getKonzhiVal() {
		return konzhiVal;
	}
	public int getKeephuihe() {
		return keephuihe;
	}
	@Override
	public int getChiXuHuihe() {
		return keephuihe;
	}

}
