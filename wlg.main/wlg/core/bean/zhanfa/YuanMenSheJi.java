package wlg.core.bean.zhanfa;

public class YuanMenSheJi extends ZhanFa {
	
	private float jianGJVal;
	private int gongJiCiShu;

	public YuanMenSheJi(String name, ZFType t, int ready, float doneRate, float harmVal, int distance, Person persons,float jianGJVal,int gongJiCiShu) {
		super(name, t, ready, doneRate, harmVal, distance, persons);
		this.jianGJVal = jianGJVal;
		this.gongJiCiShu = gongJiCiShu;
	}

	public float getJianGJVal() {
		return jianGJVal;
	}

	public int getGongJiCiShu() {
		return gongJiCiShu;
	}

	public float getHarmVal(float harmVal,UpVal upVal) {
		return gongJiCiShu * super.getHarmVal(harmVal, upVal);
	}
}
