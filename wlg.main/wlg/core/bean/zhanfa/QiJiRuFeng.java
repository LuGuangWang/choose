package wlg.core.bean.zhanfa;

public class QiJiRuFeng extends ZhanFa {

	private float upSpeedVal;//增加速度属性值
	private int chiXuHuihe;
	private float gongjiRate;//执行两次攻击的概率
	
	public QiJiRuFeng(String name, ZFType t, int ready, float doneRate, float upSpeedVal, int distance, Person persons,int chiXuHuihe,float gongjiRate) {
		super(name, t, ready, doneRate, 0.0f, distance, persons);
		this.upSpeedVal = upSpeedVal;
		this.chiXuHuihe = chiXuHuihe;
		this.gongjiRate = gongjiRate;
	}

	public float getGongjiRate() {
		return gongjiRate;
	}
	public float getUpSpeedVal() {
		return upSpeedVal;
	}
	public int getChiXuHuihe() {
		return chiXuHuihe;
	}

}
