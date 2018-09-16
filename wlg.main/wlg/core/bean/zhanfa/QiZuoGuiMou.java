package wlg.core.bean.zhanfa;

public class QiZuoGuiMou extends ZhanFa {
	private float upVal = 0.0f;
	private int addPerson = 0;
	private int keephuihe = 0;
	
	public QiZuoGuiMou(String name, ZFType t, int ready, float doneRate, float upVal,int addPerson,int keephuihe, int distance, Person persons) {
		super(name, t, ready, doneRate, 0.0f, distance, persons);
		this.upVal = upVal;
		this.addPerson=addPerson;
		this.keephuihe=keephuihe;
	}

	public float getUpVal() {
		return upVal;
	}
	public int getAddPerson() {
		return addPerson;
	}
	public int getKeephuihe() {
		return keephuihe;
	}
	
}
