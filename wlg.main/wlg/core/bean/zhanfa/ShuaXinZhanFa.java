package wlg.core.bean.zhanfa;

/**
 * 会刷新伤害的战法
 * @author seven
 *
 */
public class ShuaXinZhanFa extends ZhanFa {

	public ShuaXinZhanFa(int ready, float doneRate, float harmRate, Person persons) {
		super(ready, doneRate, harmRate, persons);
	}
	//可叠加基本伤害
	private float baseRate;

	public ShuaXinZhanFa(float baseRate) {
		super(0, 0f, 0f, new Person(0));
		this.baseRate = baseRate;
	}

	public float getBaseRate() {
		return baseRate;
	}
}