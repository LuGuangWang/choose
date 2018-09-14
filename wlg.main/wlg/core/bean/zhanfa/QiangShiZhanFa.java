package wlg.core.bean.zhanfa;

public class QiangShiZhanFa extends ZhanFa {

	private int keephuihe = 8;//战法持续回合
	private float jianshangVal = 0.0f;
	
	public QiangShiZhanFa(String name, ZFType t, int ready, float doneRate, float jianshangVal, int distance,
			Person persons,int keephuihe) {
		super(name, t, ready, doneRate, 0.0f, distance, persons);
		this.keephuihe = keephuihe;
		this.jianshangVal=jianshangVal;
	}

	public int getKeephuihe() {
		return keephuihe;
	}
	public float getJianshangVal() {
		return jianshangVal;
	}
}
