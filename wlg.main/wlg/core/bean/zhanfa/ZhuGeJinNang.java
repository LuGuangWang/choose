package wlg.core.bean.zhanfa;

public class ZhuGeJinNang extends ZhanFa {

	private int keephuihe;
	private float jiashangVal;
	private float jianshangVal;
	
	public ZhuGeJinNang(String name, ZFType t, int ready, float doneRate, int distance, Person persons,float jianshangVal,float jiashangVal,int keephuihe) {
		super(name, t, ready, doneRate, 0.0f, distance, persons);
		this.keephuihe=keephuihe;
		this.jiashangVal=jiashangVal;
		this.jianshangVal=jianshangVal;
	}

	public int getKeephuihe() {
		return keephuihe;
	}
	public float getJiashangVal() {
		return jiashangVal;
	}
	public float getJianshangVal() {
		return jianshangVal;
	}
	
}
