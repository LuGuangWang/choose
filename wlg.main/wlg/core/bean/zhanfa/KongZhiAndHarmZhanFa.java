package wlg.core.bean.zhanfa;

public class KongZhiAndHarmZhanFa extends ZhanFa {

	private int keephuihe = 1;//持续几回合.
	//增益伤害率
	private float exHarmRate= 0;
	
	public KongZhiAndHarmZhanFa(String name, ZFType t, int keephuihe,float exHarmRate, Person persons) {
		super(name, t, 0, 1f, 0f, persons);
		this.exHarmRate = exHarmRate;
		this.keephuihe = keephuihe;
	}
	
	public KongZhiAndHarmZhanFa(String name, ZFType t, int ready, float doneRate, float harmRate,int keephuihe,float exHarmRate, Person persons) {
		super(name, t, ready, doneRate, harmRate, persons);
		this.exHarmRate = exHarmRate;
		this.keephuihe = keephuihe;
	}
	
	public float getExHarmRate() {
		return exHarmRate;
	}
	public int getKeephuihe() {
		return keephuihe;
	}
}
