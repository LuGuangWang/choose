package wlg.core.bean.zhanfa;

/**
 * 会刷新伤害的战法
 * @author seven
 *
 */
public class ShuaXinZhanFa extends ZhanFa {

	//可叠加基本伤害
	private float baseRate;

	public ShuaXinZhanFa(String name,ZFType t,float baseRate,int distance) {
		super(name,t,0, 0f, 0f, distance,new Person(0));
		this.baseRate = baseRate;
	}

	public float getBaseRate() {
		return baseRate;
	}
}
