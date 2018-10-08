package wlg.core.bean.zhanfa;

public class YanFenJiZhen extends ZhanFa {

	private float secondHarmVal;
	
	public YanFenJiZhen(String name, ZFType t, int ready, float doneRate, float harmVal, int distance, Person persons,float secondHarmVal) {
		super(name, t, ready, doneRate, harmVal, distance, persons);
		this.secondHarmVal = secondHarmVal;
	}

	public float getSecondHarmVal() {
		return secondHarmVal;
	}
	
	public float getHarmVal(float harmVal,UpVal upVal) {
		float sum = 0.0f;
		//强力伤害
		sum = super.getHarmVal(harmVal, upVal);
		//火攻伤害
		float newRate = this.getDoneRate() * ConflictList.$().huogongChongTuRate();
		float newHarmVal = harmVal - this.getHarmRate() + secondHarmVal;
		sum += super.getHarmVal(newRate, newHarmVal, upVal);
		
		return sum;
	}
}
