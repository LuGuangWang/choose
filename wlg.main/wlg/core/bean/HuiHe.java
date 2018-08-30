package wlg.core.bean;
/**
 * 回合
 * @author seven
 *
 */
public class HuiHe {
	private int id = 1;
	
	//TODO 将此参数根据武将防御属性获取
	//战法因为士兵减少 威力下降 默认值 1/8 
	private float zhanfaHurt = 1.0f;//战法造成的损失伤害
	private float attackHurt = 1.0f;//普通攻击造成的损失伤害
	//额外属性
	private boolean hasZengYi = false;
	private boolean hasKongZhi = false;
	private boolean hasBuGong = false;
	private float shuaxinRate = 0.0f;
	private int wujiangCount = 3;
	//封闭战法 TODO
	private float fengZhanfa = 0;
	//封闭普攻 TODO
	private float fengGongji = 0;
	//封战法 也封攻击
	public HuiHe getAllFeng(float jsRate) {
		this.fengGongji = jsRate;
		this.fengZhanfa = jsRate;
		return this;
	}
	//封攻击
	public HuiHe getFengGongji(float jsRate) {
		this.fengGongji = jsRate;
		return this;
	}
	
	
	public boolean isHasBuGong() {
		return hasBuGong;
	}
	public void setHasBuGong(boolean hasBuGong) {
		this.hasBuGong = hasBuGong;
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
	public boolean isHasKongZhi() {
		return hasKongZhi;
	}
	public void setHasKongZhi(boolean hasKongZhi) {
		this.hasKongZhi = hasKongZhi;
	}
	public int getId() {
		return id;
	}
	public float getSolderRate() {
		return getZhanFaRate() + getAttackRate();
	}
	public float getZhanFaRate() {
		int newId = id>1?id-1:1;
		float r = zhanfaHurt/id * (1- fengZhanfa);
		return r == 0?zhanfaHurt/newId:r;
	}
	public float getAttackRate() {
		int newId = id>1?id-1:1;
		float r = attackHurt/id * (1- fengGongji);
		return r == 0?attackHurt/newId:r;
	}
	public void setId(int id) {
		this.id = id;
	}
}
