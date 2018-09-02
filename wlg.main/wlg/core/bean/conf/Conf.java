package wlg.core.bean.conf;

public abstract class Conf {
	//伤害倍数
	public static final float s_rate = 2.1f;
	//防御攻击倍数 
	public static final float fg_rate = 0.015f;
	//单控损失基数
	public static final float dk_rate = 0.8f;
	//部队武将数
	public static final int WuJiang_Count = 3;
	//不计算武将普通攻击
	public static final boolean Calc_PuGong = false;
	//总兵力
	public static final float totalCount = 10000.0f;
	//每回合损失兵力
	public static final float SunShiCount = 1200.0f;
//	public static final float SunShiCount = 0.0f;
	
	//是否打印日志
	public static final boolean log = true;
	
	public static boolean getCalcPG() {
		return Calc_PuGong;
	}
	
	public static void log(String msg) {
		if(log)
			System.out.println("========" + msg);
	}
}
