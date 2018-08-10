package wlg.core.calc;

import wlg.core.bean.zhanfa.ZhanFa;

public interface CalcHarm {
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
}
