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
	//埋雷战法,敌方武将行动前执行的概率  TODO 将此参数根据武将速度获取
	private float maileiRate = 0.5f;
	
	public float getMaileiRate() {
		return maileiRate;
	}
	public void setMaileiRate(float maileiRate) {
		this.maileiRate = maileiRate;
	}
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
