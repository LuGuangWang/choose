package wlg.core.bean.conf;

public abstract class Conf {
	//法术伤害倍数
	public static final float fashu_rate = 2.1f;
	//攻击伤害倍数
	public static final float gongji_rate = 2.1f;
	//恢复倍数
	public static final float huifu_rate = 4.0f;
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
	public static final float SunShiCount = 1200.0f;
	//速度基准值
	public static final float base_speed = 120.0f;
	//回合初始兵力基数
	public static final float binglijishu = 4.0f;
	//暴走造成敌军伤害的概率
	public static final float baozou_rate = 0.8f;
	//暴走伤害基数
	public static final float jiashanghai = SunShiCount * baozou_rate * 4.0f;
	//法术不被控制的概率
	public static final float max_mianyi_fashu = 0.8f; 
	//攻击被控制的概率
	public static final float max_mianyi_gongji = 0.8f;
	//法术不被控制的概率
	public static final float min_mianyi_fashu = 0.5f; 
	//攻击被控制的概率
	public static final float min_mianyi_gongji = 0.5f;
	//免疫规避的概率
	public static final float mianyi_guibi = 0.5f;
	//属性伤害比缩小倍数
	public static final float shuxing_suoxiao = 1000.0f;
	//属性值缩小倍数
	public static final float shuxing_val_suoxiao = 10.0f;
	//每回合兵力恢复基数
	public static final float bingli_huifu = 500.0f;
	
	
	public static final int daying = 1;
	public static final int zhongjun = 2;
	public static final int qianfeng = 3;
	
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
