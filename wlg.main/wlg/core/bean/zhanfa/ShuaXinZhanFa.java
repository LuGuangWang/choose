package wlg.core.bean.zhanfa;

/**
 * 会刷新伤害的战法
 * @author seven
 *
 */
public class ShuaXinZhanFa extends ZhanFa {

	public ShuaXinZhanFa(String name,int ready, float doneRate, float harmRate, Person persons) {
		super(name,ready, doneRate, harmRate, persons);
	}
	//可叠加基本伤害
	private float baseRate;

	public ShuaXinZhanFa(String name,float baseRate) {
		super(name,0, 0f, 0f, new Person(0));
		this.baseRate = baseRate;
	}

	public float getBaseRate() {
		return baseRate;
	}
}
