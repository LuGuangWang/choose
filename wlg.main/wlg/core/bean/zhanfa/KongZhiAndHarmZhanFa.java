package wlg.core.bean.zhanfa;

public class KongZhiAndHarmZhanFa extends ZhanFa {

	private int keephuihe = 1;//控制持续几回合.
	
	public KongZhiAndHarmZhanFa(String name, ZFType t, int keephuihe,float exHarmRate, Person persons) {
		super(name, t, 0, 1f, 0f, persons,0.0f,exHarmRate);
		this.keephuihe = keephuihe;
	}
	
	public KongZhiAndHarmZhanFa(String name, ZFType t, int ready, float doneRate, float harmRate,int keephuihe,float exHarmRate, Person persons) {
		super(name, t, ready, doneRate, harmRate, persons,0.0f,exHarmRate);
		this.keephuihe = keephuihe;
	}
	
	public int getKeephuihe() {
		return keephuihe;
	}
}
