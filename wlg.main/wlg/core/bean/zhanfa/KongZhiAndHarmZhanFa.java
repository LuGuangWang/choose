package wlg.core.bean.zhanfa;

public class KongZhiAndHarmZhanFa extends ZhanFa {

	private int keephuihe = 1;//控制持续几回合.
	private float wushiguibi = 0.0f;
	
	public KongZhiAndHarmZhanFa(String name, ZFType t, int keephuihe,float exHarmRate,float wushiguibi, int distance,Person persons) {
		super(name, t, 0, 1f, 0f, distance,persons,0.0f,exHarmRate);
		this.keephuihe = keephuihe;
		this.wushiguibi = wushiguibi;
	}
	
	public KongZhiAndHarmZhanFa(String name, ZFType t, int ready, float doneRate, float harmRate,int keephuihe,float exHarmRate, int distance,Person persons) {
		super(name, t, ready, doneRate, harmRate,distance, persons,0.0f,exHarmRate);
		this.keephuihe = keephuihe;
	}
	
	public int getKeephuihe() {
		return keephuihe;
	}
	public float getWushiguibi() {
		return wushiguibi;
	}
	
}
