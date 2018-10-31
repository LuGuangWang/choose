package wlg.core.bean.zhanfa;

public class PoZhenQiangXi extends ZhanFa {

	private float baozouRate;
	private float upFSVal;
	private float maxUpFSVal;
	
	public PoZhenQiangXi(String name, ZFType t, int ready, float doneRate, float harmVal, int distance,
			Person persons,float baozouRate,float upFSVal,float maxUpFSVal) {
		super(name, t, ready, doneRate, harmVal, distance, persons);
		this.baozouRate=baozouRate;
		this.upFSVal=upFSVal;
		this.maxUpFSVal=maxUpFSVal;
	}

	public float getBaozouRate() {
		return baozouRate;
	}
	public float getUpFSVal(int huiheId) {
		float val = upFSVal * huiheId;
		return Math.min(val, maxUpFSVal);
	}
	public float getMaxUpFSVal() {
		return maxUpFSVal;
	}

}
