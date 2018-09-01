package wlg.core;

import wlg.core.bean.zhanfa.ZengYiZhanFa;
import wlg.core.bean.zhanfa.ZhanFa;

/**
 * 条件判断
 * @author seven
 *
 */
public class CheckUtil {
	
	public static boolean isJiaCheng(ZhanFa zf) {
		boolean isJiaCheng = isFaShuJiaCheng(zf);
		return isJiaCheng;
	}
	
	/**
	 * 是否是法术加成战法
	 * @param zf
	 * @return
	 */
	public static boolean isFaShuJiaCheng(ZhanFa zf) {
		boolean isJiaCheng = false;
		switch (zf.getT()) {
		case ZhiHui_FaShuJiacheng_FaShuGongJi_BuGongJi:
			isJiaCheng = true;
			break;
		default:
			isJiaCheng = false;
			break;
		}
		return isJiaCheng;
	}
	
	/**
	 * 是否自身不攻击战法
	 * @param zf
	 * @return
	 */
	public static boolean isBuGongJi(ZhanFa zf) {
		boolean isBuGongJi = false;
		switch (zf.getT()) {
		case ZhiHui_FaShuJiacheng_FaShuGongJi_BuGongJi:
			isBuGongJi = true;
			break;
		default:
			isBuGongJi = false;
			break;
		}
		return isBuGongJi;
	}
	
	public static boolean isKongZhi(ZhanFa zf) {
		boolean isKongZhi = false;
		switch (zf.getT()) {
		case ZhuDong_KongZhi_ALL:
			isKongZhi = true;
			break;
		case ZhuDong_FaShuShangHai_KongZhiGongji:
			isKongZhi = true;
			break;
		default:
			isKongZhi = false;
			break;
		}
		return isKongZhi;
	}
	
	/**
	 * 是否策略伤害
	 * @param zf
	 * @return
	 */
	public static boolean isStrategy(ZhanFa zf) {
		boolean isStrategy = false;
		switch (zf.getT()) {
		case ZhiHui_FaShuJiacheng_FaShuGongJi_BuGongJi:
			isStrategy = true;
			break;
		case ZhuDong_FaShu:
			isStrategy = true;
			break;
		case ZhiHui_KongZhiGongJi_FaShuShangHai:
			isStrategy = true;
			break;
		case ZhuDong_FaShu_JianShang:
			isStrategy = true;
			break;
		default:
			isStrategy = false;
			break;
		}
		return isStrategy;
	}
	
	/**
	 * 是否0伤害
	 * @param zf
	 * @return
	 */
	public static boolean isZeroHarm(ZhanFa zf) {
		boolean isZeroHarm = false;
		switch (zf.getT()) {
		case ZhuDong_KongZhi_ALL:
			isZeroHarm = true;
			break;
		case ZhuDong_FuZhu:
			isZeroHarm = true;
			break;
		case BeiDong_JiaCheng:
			isZeroHarm = true;
			break;
		default:
			isZeroHarm = false;
			break;
		}
		return isZeroHarm;
	}
	
	/**
	 * 是否由其他战法触发伤害
	 * @param zf
	 * @return
	 */
	public static boolean isZengYi(ZhanFa zf) {
		boolean isZengYi = (zf instanceof ZengYiZhanFa);
		return isZengYi;
	}
}