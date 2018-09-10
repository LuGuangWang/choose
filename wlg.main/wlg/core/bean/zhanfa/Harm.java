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
	public float getHarmVal(float harmVal) ;
	/**
	 * 添加属性加成
	 * @return
	 */
	public float addShuXingVal(float val);
}
