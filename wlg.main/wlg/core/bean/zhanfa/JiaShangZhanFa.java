package wlg.core.bean.zhanfa;


/**
 * 提高伤害的战法
 * @author seven
 *
 */
public class JiaShangZhanFa extends ZhanFa{

	private int keephuihe = 8;//战法持续回合
	
	public JiaShangZhanFa(String name, ZFType t, int ready, float doneRate,float harmVal,int distance, Person persons,int keephuihe) {
		super(name, t, ready, doneRate, harmVal,distance, persons);
		this.keephuihe = keephuihe;
	}

	public int getKeephuihe() {
		return keephuihe;
	}
	
	
}
