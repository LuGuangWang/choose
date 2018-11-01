package wlg.core.bean.zhanfa;

public class MiMouDingShu extends ZhanFa {

	private float jianshangVal;
	private float zuzhouVal;
	private int keepHuihe;
	
	public MiMouDingShu(String name, ZFType t, int ready, float doneRate, float harmVal, int distance, Person persons,
			float jianshangVal,float zuzhouVal,int keepHuihe) {
		super(name, t, ready, doneRate, harmVal, distance, persons);
		this.jianshangVal = jianshangVal;
		this.zuzhouVal = zuzhouVal;
		this.keepHuihe = keepHuihe;
	}

	public float getJianshangVal() {
		return jianshangVal;
	}
	public float getZuzhouVal() {
		return zuzhouVal;
	}
	public int getKeepHuihe() {
		return keepHuihe;
	}
	
	public int getKeephuihe() {
		return keepHuihe;
	}

	public float getHarmVal(float harmVal,UpVal upVal) {
		float konghuangVal =  getHarmVal(getDoneRate(), harmVal, upVal);
		
		float zuzhouHarmVal = harmVal - getHarmRate() + zuzhouVal;
		float zuzhouDoneRate = getDoneRate() * 0.25f;
		
		float zuzhouVal =  getHarmVal(zuzhouDoneRate, zuzhouHarmVal, upVal);
		
		return konghuangVal + zuzhouVal;
		
	}
}
