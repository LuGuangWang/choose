package wlg.core.bean.conf;

public abstract class Conf {
	//伤害倍数
	public static final float fashu_rate = 2.1f;
	//伤害倍数
	public static final float gongji_rate = 1.95f;
	//防御攻击倍数 
	public static final float fg_rate = 1.5f;
	//大幅下降
	public static final float dafu_xiajiang = 0.65f;
	//含控制战法，士兵损失增加1.5倍
	public static final float kongzhi_avg_rate = 0.7f;
	//法术损失基数
	public static final float sf_s_rate = 0.6f;
	//攻击损失基数
	public static final float gj_s_rate = 0.4f;
	//部队武将数
	public static final int WuJiang_Count = 3;
	//不计算武将普通攻击
	public static final boolean Calc_PuGong = true;
	//总兵力
	public static final float totalCount = 10000.0f;
	//每回合损失兵力
	public static final float SunShiCount = 1000.0f;
	//速度基准值
	public static final float base_speed = 150.0f;
	//回合初始兵力基数
	public static final float binglijishu = 4.0f;
	
	//是否打印日志
	public static final boolean log = false;
	
	public static boolean getCalcPG() {
		return Calc_PuGong;
	}
	
	public static void log(String msg) {
		if(log)
			System.out.println("========" + msg);
	}
}
