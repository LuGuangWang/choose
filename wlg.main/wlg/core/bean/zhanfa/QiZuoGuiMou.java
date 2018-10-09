package wlg.core.bean.zhanfa;

public class QiZuoGuiMou extends ZhanFa {
	private float upVal = 0.0f;
	private int keephuihe = 0;
	
	private float kongzhiRate = 0.0f;//发动控制的概率
	
	public QiZuoGuiMou(String name, ZFType t, int ready, float doneRate, float upVal,int keephuihe, int distance, Person persons) {
		super(name, t, ready, doneRate, 0.0f, distance, persons);
		this.upVal = upVal;
		this.keephuihe=keephuihe;
		kongzhiRate = 1.0f;
	}
	
	public QiZuoGuiMou(String name, ZFType t, int ready, float doneRate,int keephuihe, int distance, Person persons,float kongzhiRate) {
		super(name, t, ready, doneRate, 0.0f, distance, persons);
		this.keephuihe=keephuihe;
		this.kongzhiRate = kongzhiRate;
	}

	public float getUpVal() {
		return upVal;
	}
	public int getKeephuihe() {
		return keephuihe;
	}
	public float getKongzhiRate() {
		return kongzhiRate;
	}
	
}
