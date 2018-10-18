package wlg.core.bean.zhanfa;

public class GuoTu extends ZhanFa {

	private int chiXuHuihe;
	
	public GuoTu(String name, ZFType t, int ready, float doneRate, int distance, Person persons,int chiXuHuihe) {
		super(name, t, ready, doneRate, 0.0f, distance, persons);
		this.chiXuHuihe=chiXuHuihe;
	}

	public int getChiXuHuihe() {
		return chiXuHuihe;
	}

}
