package wlg.core.bean.zhanfa;

public class FanJiZhiCeZhanFa extends ZhanFa {

	private int keephuihe;
	
	public FanJiZhiCeZhanFa(String name, ZFType t, int ready, float doneRate, float harmVal,int distance, Person persons,int keephuihe) {
		super(name, t, ready, doneRate, harmVal,distance, persons);
		this.keephuihe = keephuihe;
	}

	public int getKeephuihe() {
		return keephuihe;
	}
	
}