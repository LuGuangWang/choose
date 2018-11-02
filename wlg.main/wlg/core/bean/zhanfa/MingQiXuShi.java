package wlg.core.bean.zhanfa;

public class MingQiXuShi extends ZhanFa {
	
	private float jianMouLue;
	private int kongzhichixuhuihe;//控制效果 持续多少回合

	public MingQiXuShi(String name, ZFType t, int ready, float doneRate, float jianMouLue, int distance, Person persons,int kongzhichixuhuihe) {
		super(name, t, ready, doneRate, 0.0f, distance, persons);
		this.jianMouLue = jianMouLue;
		this.kongzhichixuhuihe = kongzhichixuhuihe;
	}

	public float getJianMouLue() {
		return jianMouLue;
	}
	public int getKongzhichixuhuihe() {
		return kongzhichixuhuihe;
	}
	
}
