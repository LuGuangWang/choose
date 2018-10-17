package wlg.core.bean.zhanfa;

public class QingXiaWangWei extends ZhanFa {

	private int keephuihe;
	private float upGongJiRate;
	
	public QingXiaWangWei(String name, ZFType t, int ready, float doneRate, float upGongJiRate, int distance,
			Person persons,int keephuihe) {
		super(name, t, ready, doneRate, 0.0f, distance, persons);
		this.keephuihe=keephuihe;
		this.upGongJiRate = upGongJiRate;
	}

	public int getKeephuihe() {
		return keephuihe;
	}

	public float getUpGongJiRate() {
		return upGongJiRate;
	}

}
