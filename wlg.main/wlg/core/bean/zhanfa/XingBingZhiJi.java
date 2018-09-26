package wlg.core.bean.zhanfa;

public class XingBingZhiJi extends ZhanFa {

	private float dayingGaiLv;//提高大营发动概率
	private float zhongjunJiaShang;//中军增加伤害
	private float qianfengJianShang;//前锋降低伤害
	
	public XingBingZhiJi(String name, ZFType t, int ready, float doneRate, int distance,Person persons,
			float dayingGaiLv,float zhongjunJiaShang,float qianfengJianShang) {
		super(name, t, ready, doneRate, 0.0f, distance, persons);
		this.dayingGaiLv = dayingGaiLv;
		this.zhongjunJiaShang = zhongjunJiaShang;
		this.qianfengJianShang = qianfengJianShang;
	}
	
	public float getDayingGaiLv() {
		return dayingGaiLv;
	}
	public float getZhongjunJiaShang() {
		return zhongjunJiaShang;
	}
	public float getQianfengJianShang() {
		return qianfengJianShang;
	}
	
}
