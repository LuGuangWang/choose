package wlg.core.bean.zhanfa;

/**
 * 加成自身
 * @author seven
 *
 */
public class JiaChengZhanFa extends ZhanFa {
	/**
	 * 加成伤伤害率
	 */
	private float addRate;
	
	public JiaChengZhanFa(String name,ZFType t,int ready, float doneRate, float harmRate, int distance,Person persons,float addRate) {
		super(name,t,ready, doneRate, harmRate, distance,persons);
		this.addRate=addRate;
	}

	public float getAddRate() {
		return addRate;
	}
}
