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
	private float zhanfaHurt = 4.0f;//战法造成的损失伤害
	private float attackHurt = 4.0f;//普通攻击造成的损失伤害
	//额外属性
	private boolean hasZengYi = false;
	private boolean hasJianShang = false;
	private float shuaxinRate = 0.0f;
	private int wujiangCount = 3;
	//封闭战法 TODO
	private float fengZhanfa = 0;
	//封闭普攻 TODO
	private float fengGongji = 0;
	//全部封
	public HuiHe getJSHuiHe(float jsRate) {
		HuiHe huihe = new HuiHe();
		int newId = id>1?id-1:1;
		huihe.setId(newId);
		huihe.fengGongji = jsRate;
		huihe.fengZhanfa = jsRate;
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
		return getZhanFaRate() + getAttackRate();
	}
	public float getZhanFaRate() {
		float r = zhanfaHurt/id * (1- fengZhanfa);
		return r == 0?zhanfaHurt/id:r;
	}
	public float getAttackRate() {
		float r = attackHurt/id * (1- fengGongji);
		return r == 0?attackHurt/id:r;
	}
	public void setId(int id) {
		this.id = id;
	}
}
