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
	
	private float upQuanShuXing;//谋略增加值
	private int person;//受益人数
	
	public JiaChengZhanFa(String name,ZFType t,int ready, float doneRate, float harmRate, int distance,Person persons,float addRate) {
		super(name,t,ready, doneRate, harmRate, distance,persons);
		this.addRate=addRate;
	}
	
	public JiaChengZhanFa(String name,ZFType t,int ready, float doneRate, int distance,Person persons,float upQuanShuXing,int person) {
		super(name,t,ready, doneRate, 0.0f, distance,persons);
		this.upQuanShuXing=upQuanShuXing;
		this.person=person;
	}

	public float getAddRate() {
		return addRate;
	}
	public float getUpQuanShuXing() {
		return upQuanShuXing;
	}
	public int getPerson() {
		return person;
	}
}
