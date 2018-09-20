package wlg.core.bean.zhanfa;

public class BiYueZhanFa extends ZhanFa {

	private int keephuihe;
	private float downFShuXingVal;//降低防御属性
	
	public BiYueZhanFa(String name, ZFType t, int ready, float doneRate,float downFShuXingVal, int distance, Person persons,int keephuihe) {
		super(name, t, ready, doneRate, 0.0f, distance, persons);
		this.downFShuXingVal=downFShuXingVal;
		this.keephuihe=keephuihe;
	}

	public int getKeephuihe() {
		return keephuihe;
	}
	public float getDownFShuXingVal() {
		return downFShuXingVal;
	}

}
