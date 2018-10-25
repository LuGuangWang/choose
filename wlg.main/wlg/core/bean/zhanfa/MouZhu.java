package wlg.core.bean.zhanfa;

public class MouZhu extends ZhanFa {

	private float guibiRate;
	private float dongChaRate;
	private float xianShouRate;
	private float yichuRate;//移除有害效果
	private int keepHuihe;
	
	public MouZhu(String name, ZFType t, int ready, float doneRate, int distance, Person persons,
			float guibiRate,float dongChaRate,float xianShouRate,int keepHuihe) {
		super(name, t, ready, doneRate, 0.0f, distance, persons);
		this.guibiRate=guibiRate;
		this.dongChaRate=dongChaRate;
		this.xianShouRate=xianShouRate;
		this.yichuRate = doneRate;
		this.keepHuihe = keepHuihe;
	}
	
	public float getGuibiRate() {
		return guibiRate;
	}
	public float getDongChaRate() {
		return dongChaRate;
	}
	public float getXianShouRate() {
		return xianShouRate;
	}
	public float getYichuRate() {
		return yichuRate;
	}
	public int getKeepHuihe() {
		return keepHuihe;
	}

}
