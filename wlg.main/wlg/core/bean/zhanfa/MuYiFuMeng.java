package wlg.core.bean.zhanfa;

public class MuYiFuMeng extends ZhanFa {
	private float jianshangVal = 0.0f;//伤害降低值
	private float jiangshangRate = 1.0f;//减伤的概率
	private int keephuihe = 0;//在前多少回合
	private int guibihuihe = 1;
	
	public MuYiFuMeng(String name, ZFType t, int ready, float doneRate, float jianshangRate,float jianshangVal, int distance, 
			Person persons,int guibihuihe,int keephuihe) {
		super(name, t, ready, doneRate, 0.0f, distance, persons);
		this.jianshangVal = jianshangVal;
		this.jiangshangRate = jianshangRate;
		this.keephuihe = keephuihe;
		this.guibihuihe = guibihuihe;
	}

	public int getGuibihuihe() {
		return guibihuihe;
	}
	public float getJianshangVal() {
		return jianshangVal;
	}
	public float getJiangshangRate() {
		return jiangshangRate;
	}
	public int getKeephuihe() {
		return keephuihe;
	}

}
