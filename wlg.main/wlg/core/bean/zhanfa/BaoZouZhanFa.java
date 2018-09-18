package wlg.core.bean.zhanfa;

public class BaoZouZhanFa extends ZhanFa {

	private int keephuihe;
	
	public BaoZouZhanFa(String name, ZFType t, int ready, float doneRate, int keephuihe, int distance, Person persons) {
		super(name, t, ready, doneRate, 0.0f, distance, persons);
		this.keephuihe=keephuihe;
	}

	public int getKeephuihe() {
		return keephuihe;
	}
	
}
