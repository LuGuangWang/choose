package wlg.core.calc;

import java.util.List;

import wlg.core.bean.zhanfa.JiaChengZhanFa;
import wlg.core.bean.zhanfa.ZhanFa;

/**
 *	计算加成值
 * @author seven
 *
 */
public class CalcJiaCheng {

	/**
	 * TODO 同类型的加成不能叠加
	 * @param zfs
	 * @return
	 */
	public static float getJiaChengVal(List<ZhanFa> zfs) {
		float jiachengVal = 0.0f;
		for(ZhanFa zf:zfs) {
			JiaChengZhanFa t = (JiaChengZhanFa)zf;
			jiachengVal += CalcDoRate.getJiaChengRate(t) * t.getAddRate();
		}
		return jiachengVal;
	}
}
