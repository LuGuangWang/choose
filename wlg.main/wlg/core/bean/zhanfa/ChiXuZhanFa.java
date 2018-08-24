package wlg.core.bean.zhanfa;

public class ChiXuZhanFa extends ZhanFa {

	private int keephuihe;//持续几回合.
	//增益伤害率
	private float exHarmRate= 0;
	
	public ChiXuZhanFa(String name, ZFType t, int keephuihe,float exHarmRate, Person persons) {
		super(name, t, 0, 1f, 0f, persons);
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
