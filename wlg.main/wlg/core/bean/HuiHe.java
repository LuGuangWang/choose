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
	
	public int getId() {
		return id;
	}
	public float getSolderRate() {
		return solderRate/id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setSolderRate(float solderRate) {
		this.solderRate = solderRate;
	}
}
