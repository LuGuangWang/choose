package wlg.core.bean.zhanfa;

public class ShuiYanQiJun extends ZhanFa {

	private float jianGongJiVal;//降低攻击属性值
	
	public ShuiYanQiJun(String name, ZFType t, int ready, float doneRate, float harmVal, int distance, Person persons,float jianGongJiVal) {
		super(name, t, ready, doneRate, harmVal, distance, persons);
		this.jianGongJiVal = jianGongJiVal;
	}

	public float getJianGongJiVal() {
		return jianGongJiVal;
	}
	
	
}
