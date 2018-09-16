package wlg.core;

import wlg.core.bean.zhanfa.JiaShangZhanFa;
import wlg.core.bean.zhanfa.ZengYiZhanFa;
import wlg.core.bean.zhanfa.ZhanFa;

/**
 * 条件判断
 * @author seven
 *
 */
public class CheckUtil {
	
	//自身加成
	public static boolean isZiShenJiaCheng(ZhanFa zf) {
		boolean isJiaCheng = isFaShuJiaCheng(zf);
		return isJiaCheng;
	}
	/**
	 * 先发类型的控制
	 * @param zf
	 * @return
	 */
	public static boolean isXianFaKongZhi(ZhanFa zf) {
		boolean isKongZhi = false;
		switch (zf.getT()) {
		case ZhiHui_FuZhu_ALL:
		case ZhiHui_KongZhiGongJi:
		case ZhiHui_KongZhiGongJi_FaShuShangHai:
		case ZhiHui_JianshangFashu_KongZhiFaShu:
			isKongZhi = true;
			break;
		default:
			isKongZhi = false;
			break;
		}
		return isKongZhi;
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
	
	public static boolean isKongZhiAll(ZhanFa zf) {
		boolean isKongZhi = false;
		switch (zf.getT()) {
		case ZhuDong_KongZhi_ALL:
		case ZhuDong_JiaShuXing_KongZhi:
			isKongZhi = true;
			break;
		default:
			isKongZhi = false;
			break;
		}
		return isKongZhi;
	}
	
	public static boolean isKongZhiFaShu(ZhanFa zf) {
		boolean isKongZhi = false;
		switch (zf.getT()) {
		case ZhuDong_JianShang_KongZhiFaShu:
		case ZhiHui_JianshangFashu_KongZhiFaShu:
			isKongZhi = true;
			break;
		default:
			isKongZhi = false;
			break;
		}
		return isKongZhi;
	}
	
	public static boolean isKongZhiGongJi(ZhanFa zf) {
		boolean isKongZhi = false;
		switch (zf.getT()) {
		case ZhiHui_KongZhiGongJi:
		case ZhuDong_FaShu_JianShang:
		case ZhiHui_KongZhiGongJi_FaShuShangHai:
		case ZhuDong_FaShuShangHai_KongZhiGongji:
			isKongZhi = true;
			break;
		default:
			isKongZhi = false;
			break;
		}
		return isKongZhi;
	}
	
	public static boolean isKongZhi(ZhanFa zf) {
		boolean isKongZhi = isKongZhiAll(zf)||isKongZhiFaShu(zf)||isKongZhiGongJi(zf)||isLianJi(zf);
		return isKongZhi;
	}
	
	/**
	 * 是否攻击伤害
	 * @param zf
	 * @return
	 */
	public static boolean isAttack(ZhanFa zf) {
		boolean isAttack = false;
		switch (zf.getT()) {
		case BeiDong_GongJi:
		case ZhuDong_GongJi:
		case ZhuDong_FaShu_GongJi:
		case ZhuDong_JiaGongJi_LianJi:
			isAttack = true;
			break;
		default:
			isAttack = false;
			break;
		}
		return isAttack;
	}
	
	/**
	 * 是否策略伤害
	 * @param zf
	 * @return
	 */
	public static boolean isStrategy(ZhanFa zf) {
		boolean isStrategy = false;
		switch (zf.getT()) {
		case ZhuiJi_FaShu:
		case ZhuDong_FaShu:
		case ZhiHui_FuZhu_ALL:
		case ZhuDong_FaShu_GongJi:
		case ZhiHui_Multiple_FaShu:
		case ZhuDong_FaShu_JianShang:
		case ZhiHui_KongZhiGongJi_FaShuShangHai:
		case ZhuDong_FaShuShangHai_KongZhiGongji:
		case ZhiHui_FaShuJiacheng_FaShuGongJi_BuGongJi:
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
		case BeiDong_JiaCheng:
		case ZhuDong_KongZhi_ALL:
		case ZhiHui_KongZhiGongJi:
		case ZhuDong_JiaShuXing_KongZhi:
		case ZhuDong_JianShang_KongZhiFaShu:
		case ZhiHui_JianshangFashu_KongZhiFaShu:
			isZeroHarm = true;
			break;
		default:
			isZeroHarm = false;
			break;
		}
		return isZeroHarm;
	}
	/**
	 * 持续keep回合的控制战法
	 * @param zf
	 * @return
	 */
	public static boolean isKongZhiKeep(ZhanFa zf) {
		boolean isKongZhiKeep = false;
		switch (zf.getT()) {
		case ZhuDong_KongZhi_ALL:
		case ZhuDong_JiaShuXing_KongZhi:
		case ZhuDong_JianShang_KongZhiFaShu:
		case ZhuDong_FaShuShangHai_KongZhiGongji:
			isKongZhiKeep = true;
			break;
		default:
			isKongZhiKeep = false;
			break;
		}
		return isKongZhiKeep;
	}
	
	/**
	 * 是否由其他战法触发额外伤害
	 * @param zf
	 * @return
	 */
	public static boolean isZengYi(ZhanFa zf) {
		boolean isZengYi = (zf instanceof ZengYiZhanFa);
		
		return isZengYi;
	}
	
	public static boolean isUpFashu(ZhanFa zf) {
		boolean isUpFashu = false;
		switch (zf.getT()) {
		case ZhuDong_JiaShuXing_KongZhi:
			isUpFashu = true;
			break;
		default:
			isUpFashu = false;
			break;
		}
		return isUpFashu;
	}
	
	/**
	 * 是否是全体加伤法术和攻击战法
	 * @param zf
	 * @return
	 */
	public static boolean isJiaShang(ZhanFa zf) {
		boolean isJiaShang = (zf instanceof JiaShangZhanFa);
		
		return isJiaShang;
	}
	
	public static boolean isLianJi(ZhanFa zf) {
		boolean isLianJi = false;
		switch (zf.getT()) {
		case ZhuDong_JiaGongJi_LianJi:
			isLianJi = true;
			break;
		default:
			isLianJi = false;
			break;
		}
		return isLianJi;
	}
	
	public static boolean isZhuiJi(ZhanFa zf) {
		boolean isZhuiJi = false;
		switch (zf.getT()) {
		case ZhuiJi_FaShu:
			isZhuiJi = true;
			break;
		default:
			isZhuiJi = false;
			break;
		}
		return isZhuiJi;
	}
	
	/**
	 * 每个战法都有计算一次
	 * @param zf
	 * @return
	 */
	public static boolean isAllCalc(ZhanFa zf) {
		boolean isAllCalc = isJiaShang(zf);
		return isAllCalc;
	}
}
