package wlg.core.bean.zhanfa;

public class XueJianHuangSha extends ZhanFa {

	private float upGongJiRate;
	private boolean isNotFs;
	
	public XueJianHuangSha(String name, ZFType t, int ready, float doneRate, float upGongJiRate, int distance,
			Person persons,boolean isNotFs) {
		super(name, t, ready, doneRate, 0.0f, distance, persons);
		this.upGongJiRate=upGongJiRate;
		this.isNotFs = isNotFs;
	}
	public float getUpGongJiRate() {
		return upGongJiRate;
	}
	public boolean isNotFs() {
		return isNotFs;
	}

}
