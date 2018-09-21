package wlg.core.bean.zhanfa;


/**
 * 提高伤害的战法
 * @author seven
 *
 */
public class JiaShangZhanFa extends ZhanFa{

	private int keephuihe = 8;//战法持续回合
	private float upVal = 0.0f;//伤害提高
	
	public JiaShangZhanFa(String name, ZFType t, int ready, float doneRate,float upVal,int distance, Person persons,int keephuihe) {
		super(name, t, ready, doneRate, 0.0f,distance, persons);
		this.keephuihe = keephuihe;
		this.upVal=upVal;
	}

	public float getUpVal() {
		return upVal;
	}
	public int getKeephuihe() {
		return keephuihe;
	}
	
	
}
