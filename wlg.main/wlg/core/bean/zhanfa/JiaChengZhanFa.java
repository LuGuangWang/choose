package wlg.core.bean.zhanfa;

public class JiaChengZhanFa extends ZhanFa {
	/**
	 * 加成伤伤害率
	 */
	private float addRate;
	/**
	 * 持续几回合
	 */
	private int keephuihe;
	
	public JiaChengZhanFa(String name,ZFType t,int ready, float doneRate, float harmRate, Person persons,float addRate,int keephuihe) {
		super(name,t,ready, doneRate, harmRate, persons);
		this.addRate=addRate;
		this.keephuihe=keephuihe;
	}
	
	public JiaChengZhanFa(String name,ZFType t,int ready, float doneRate, float harmRate, Person persons,float addRate) {
		super(name,t,ready, doneRate, harmRate, persons);
		this.addRate=addRate;
	}

	public float getAddRate() {
		return addRate;
	}
	public int getKeephuihe() {
		return keephuihe;
	}
	
}
