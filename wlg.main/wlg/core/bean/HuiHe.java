package wlg.core.bean;
/**
 * 回合
 * @author seven
 *
 */
public class HuiHe {
	private int id = 1;
	//战法因为士兵减少 威力下降 默认值 1/8 TODO 将此参数根据武将防御属性获取
	private float solderRate = 8.0f;
	
	//额外属性
	private boolean hasZengYi = false;
	private boolean hasJianShang = false;
	private float shuaxinRate = 0.0f;
	private int wujiangCount = 3;
	
	private float jsRate = 0;//减伤的效果
	
	public HuiHe getJSHuiHe(float jsRate) {
		HuiHe huihe = new HuiHe();
		huihe.setId(id);
		huihe.jsRate = jsRate;
		return huihe;
	}
	
	public int getWujiangCount() {
		return wujiangCount;
	}
	public void setWujiangCount(int wujiangCount) {
		this.wujiangCount = wujiangCount;
	}
	public float getShuaxinRate() {
		return shuaxinRate;
	}
	public void setShuaxinRate(float shuaxinRate) {
		this.shuaxinRate = shuaxinRate;
	}
	public boolean isHasZengYi() {
		return hasZengYi;
	}
	public void setHasZengYi(boolean hasZengYi) {
		this.hasZengYi = hasZengYi;
	}
	public boolean isHasJianShang() {
		return hasJianShang;
	}
	public void setHasJianShang(boolean hasJianShang) {
		this.hasJianShang = hasJianShang;
	}
	public int getId() {
		return id;
	}
	public float getSolderRate() {
		float r = solderRate/id * (1- jsRate);
		int newId = this.id>1?(this.id - 1):1;
		return r == 0?solderRate/newId:r;
	}
	public void setId(int id) {
		this.id = id;
	}
}
