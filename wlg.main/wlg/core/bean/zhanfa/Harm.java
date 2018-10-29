package wlg.core.bean.zhanfa;

public interface Harm {
	/**
	 * 战法增益伤害
	 * @param other
	 * @return
	 */
	public float getExVal(ZhanFa other);
	/**
	 * 当前战法伤害
	 * @return
	 */
	public float getHarmVal();
	/**
	 * 当前战法伤害
	 * @return
	 */
	public float getHarmVal(float harmVal,UpVal upVal) ;
	/**
	 * 当前战法伤害
	 * @return
	 */
	public float getHarmVal(float doneRate,float harmVal,UpVal upVal);
	/**
	 * 添加属性加成
	 * @return
	 */
	public float addShuXingVal(float val,UpVal upVal);
	
	public int getChiXuHuihe();
	
	public float getUpGongJiRate();
	/**
	 * 增加攻击和策略伤害
	 * @return
	 */
	public float getUpVal();
}
