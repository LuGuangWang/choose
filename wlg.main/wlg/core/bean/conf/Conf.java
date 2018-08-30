package wlg.core.bean.conf;

public abstract class Conf {
	//伤害倍数
	public static final float s_rate = 2.1f;
	//不计算武将普通攻击
	public static final boolean Calc_PuGong = false;
	//总兵力
	public static final float totalCount = 30000.0f;
	
	
	public static boolean getCalcPG() {
		return Calc_PuGong;
	}
}
