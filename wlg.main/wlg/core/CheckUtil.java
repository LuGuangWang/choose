package wlg.core;

import wlg.core.bean.zhanfa.ZengYiZhanFa;
import wlg.core.bean.zhanfa.ZhanFa;

/**
 * 条件判断
 * @author seven
 *
 */
public class CheckUtil {
	
	//自身加成 以后不要这样写 TODO 改造
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
		case ZhiHui_GuiBi_JianShang:
		case ZhiHui_YouXian_DongYao:
		case ZhiHui_SkipReady_Jiashang:
		case ZhiHui_DaYing_ZhongJun_QianFeng:
		case ZhiHui_JiaFaShu_JianShang_MianYi:
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
	/**
	 * 控制攻击与法术
	 * @param zf
	 * @return
	 */
	public static boolean isKongZhiAll(ZhanFa zf) {
		boolean isKongZhi = false;
		switch (zf.getT()) {
		case ZhuDong_BaoZou:
		case ZhuDong_JiaShuXing:
		case ZhuDong_KongZhi_ALL:
		case ZhuDong_FaShu_BaoZou:
		case ZhuDong_Multiple_KongZhi:
		case ZhuDong_BaoZou_jianFangYu:
		case ZhuDong_JiaShuXing_KongZhi:
			isKongZhi = true;
			break;
		default:
			isKongZhi = false;
			break;
		}
		return isKongZhi;
	}
	/**
	 * 控制法术伤害
	 * @param zf
	 * @return
	 */
	private static boolean isKongZhiFaShu(ZhanFa zf) {
		boolean isKongZhi = false;
		switch (zf.getT()) {
		case ZhuiJi_GongJi_FengFaShu:
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
	/**
	 * 控制攻击伤害
	 * @param zf
	 * @return
	 */
	private static boolean isKongZhiGongJi(ZhanFa zf) {
		boolean isKongZhi = false;
		switch (zf.getT()) {
		case ZhiHui_KongZhiGongJi:
		case ZhuDong_FaShu_JianShang:
		case ZhuDong_FaShu_jianGongJi:
		case ZhuiJi_GongJi_KongZhiGongJi:
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
	/**
	 * 是否有控制效果
	 * @param zf
	 * @return
	 */
	public static boolean isKongZhi(ZhanFa zf) {
		boolean isKongZhi = isKongZhiAll(zf)||isKongZhiFaShu(zf)||isKongZhiGongJi(zf)||isLianJi(zf)||isFuZhuKongZhi(zf);
		return isKongZhi;
	}
	/**
	 * 有益效果的控制 ，像无视规避，无视控制
	 * @param zf
	 * @return
	 */
	private static boolean isFuZhuKongZhi(ZhanFa zf) {
		boolean isKongZhi = false;
		switch (zf.getT()) {
		case ZhiHui_MianYi_jiagongji:
		case ZhiHui_MianYi_WushiGuiBi:
		case ZhuDong_ShouCi_JiaGongJi:
		case ZhiHui_JiaSuDu_JiaPuGong:
			isKongZhi = true;
			break;
		default:
			isKongZhi = false;
			break;
		}
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
		case ZhuDong_GongJi_YiJi:
		case ZhuDong_FaShu_GongJi:
		case ZhuiJi_GongJi_FengFaShu:
		case ZhuDong_JiaGongJi_LianJi:
		case ZhiHui_JiaSuDu_JiaPuGong:
		case BeiDong_LianJi_jiagongji:
		case ZhuiJi_GongJi_KongZhiGongJi:
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
		case ZhuDong_FaShu_ZiDai:
		case ZhuDong_FaShu_GongJi:
		case ZhuDong_FaShu_BaoZou:
		case ZhiHui_Multiple_FaShu:
		case ZhiHui_YouXian_DongYao:
		case ZhuDong_FaShu_JianShang:
		case ZhuDong_FaShu_KeepHuiHe:
		case ZhuDong_FaShu_jianGongJi:
		case ZhuDong_FaShu_KongZhiHuiFu:
		case ZhiHui_JiaJuLi_FenBing_KongHuang:
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
	 * 是否是持续多少回合就失去效果
	 * @param zf
	 * @return
	 */
	public static boolean isChiXuHuiHe(ZhanFa zf) {
		boolean isChiXuHuiHe = false;
		switch (zf.getT()) {
		case ZhiHui_FuZhu_ALL:
		case ZhiHui_KongZhiGongJi:
		case ZhiHui_GuiBi_JianShang:
		case ZhiHui_YouXian_DongYao:
		case ZhiHui_MianYi_jiagongji:
		case ZhiHui_JiaSuDu_JiaPuGong:
		case ZhiHui_JianshangFashu_KongZhiFaShu:
			isChiXuHuiHe = true;
			break;
		default:
			isChiXuHuiHe = false;
			break;
		}
		return isChiXuHuiHe;
	}
	
	/**
	 * 是否0伤害
	 * @param zf
	 * @return
	 */
	public static boolean isZeroHarm(ZhanFa zf) {
		boolean isZeroHarm = false;
		switch (zf.getT()) {
		case BeiDong_huifu:
		case ZhuDong_BaoZou:
		case BeiDong_JiaCheng:
		case ZhuDong_JiaShuXing:
		case ZhuDong_KongZhi_ALL:
		case ZhuDong_QunTi_HuiFu:
		case ZhiHui_KongZhiGongJi:
		case ZhiHui_GuiBi_JianShang:
		case BeiDong_WuFS_JiaGongJi:
		case ZhiHui_MianYi_jiagongji:
		case ZhuDong_Multiple_KongZhi:
		case ZhuDong_ShouCi_JiaGongJi:
		case ZhiHui_MianYi_WushiGuiBi:
		case ZhiHui_SkipReady_Jiashang:
		case ZhuDong_BaoZou_jianFangYu:
		case ZhuDong_JiaShuXing_KongZhi:
		case ZhuDong_ZiSheng_YouJun_HuiFu:
		case ZhuDong_JianShang_KongZhiFaShu:
		case ZhiHui_DaYing_ZhongJun_QianFeng:
		case ZhiHui_JiaFaShu_JianShang_MianYi:
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
	 * 有益效果持续多少回合的控制战法，像：暴走持续多少回合
	 * @param zf
	 * @return
	 */
	public static boolean isKongZhiKeep(ZhanFa zf) {
		boolean isKongZhiKeep = false;
		switch (zf.getT()) {
		case ZhuDong_BaoZou:
		case ZhuDong_KongZhi_ALL:
		case ZhuDong_FaShu_KeepHuiHe:
		case ZhuDong_FaShu_jianGongJi:
		case ZhuDong_Multiple_KongZhi:
		case ZhuDong_BaoZou_jianFangYu:
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
	/**
	 * 无视规避
	 * @param zf
	 * @return
	 */
	public static boolean isMianYiGuiBi(ZhanFa zf) {
		boolean isMianYiGuiBi = false;
		switch (zf.getT()) {
		case ZhiHui_YouXian_DongYao:
		case ZhiHui_MianYi_WushiGuiBi:
		case ZhiHui_JiaJuLi_FenBing_KongHuang:
			isMianYiGuiBi = true;
			break;
		default:
			isMianYiGuiBi = false;
			break;
		}
		return isMianYiGuiBi;
	}
	/**
	 * 免疫控制
	 * @param zf
	 * @return
	 */
	public static boolean isMianYiKongZhi(ZhanFa zf) {
		boolean isMianYiGuiBi = false;
		switch (zf.getT()) {
		case ZhiHui_MianYi_jiagongji:
		case ZhiHui_MianYi_WushiGuiBi:
			isMianYiGuiBi = true;
			break;
		default:
			isMianYiGuiBi = false;
			break;
		}
		return isMianYiGuiBi;
	}
	/**
	 * 增加全属性
	 * @param zf
	 * @return
	 */
	public static boolean isUpAllShuXing(ZhanFa zf) {
		boolean isUpAllShuXing = false;
		switch (zf.getT()) {
		case ZhuDong_JiaShuXing:
			isUpAllShuXing = true;
			break;
		default:
			isUpAllShuXing = false;
			break;
		}
		return isUpAllShuXing;
	}
	//增加策略属性值比
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
	//增加攻击伤害比
	public static boolean isUpGJRate(ZhanFa zf) {
		boolean isUpGJRate = false;
		switch (zf.getT()) {
		case BeiDong_WuFS_JiaGongJi:
		case ZhiHui_MianYi_jiagongji:
			isUpGJRate = true;
			break;
		default:
			isUpGJRate = false;
			break;
		}
		return isUpGJRate;
	}
	/**
	 * 是否是全体加伤法术和攻击战法
	 * @param zf
	 * @return
	 */
	public static boolean isJiaShang(ZhanFa zf) {
		boolean isJiaShang = false;
		switch (zf.getT()) {
		case ZhiHui_FuZhu_ALL:
			isJiaShang = true;
			break;
		default:
			isJiaShang = false;
			break;
		}
		return isJiaShang;
	}
	/**
	 * 是否是连击战法
	 * @param zf
	 * @return
	 */
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
	/**
	 * 是否是暴走
	 * @param zf
	 * @return
	 */
	public static boolean isBaoZou(ZhanFa zf) {
		boolean isBaoZou = false;
		switch (zf.getT()) {
		case ZhuDong_BaoZou:
		case ZhuDong_FaShu_BaoZou:
		case ZhuDong_Multiple_KongZhi:
		case ZhuDong_BaoZou_jianFangYu:
		case ZhuDong_JiaShuXing_KongZhi:
			isBaoZou = true;
			break;
		default:
			isBaoZou = false;
			break;
		}
		return isBaoZou;
	}
	/**
	 * 控制敌军不可恢复兵力
	 * @param zf
	 * @return
	 */
	public static boolean isKongZhiHuiFu(ZhanFa zf) {
		boolean isZhuiJi = false;
		switch (zf.getT()) {
		case ZhuiJi_FaShu:
		case ZhuDong_Multiple_KongZhi:
		case ZhuDong_FaShu_KongZhiHuiFu:
			isZhuiJi = true;
			break;
		default:
			isZhuiJi = false;
			break;
		}
		return isZhuiJi;
	}
	/**
	 * 追击战法
	 * @param zf
	 * @return
	 */
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
	 * 武将自带主动类战法
	 * @param zf
	 * @return
	 */
	public static boolean isZiDaiZHuDong(ZhanFa zf) {
		boolean isZhuiJi = false;
		switch (zf.getT()) {
		case ZhuDong_GongJi:
		case ZhuDong_JiaShuXing:
		case ZhuDong_FaShu_ZiDai:
		case ZhuDong_ShouCi_JiaGongJi:
		case ZhuDong_JiaGongJi_LianJi:
		case ZhuDong_BaoZou_jianFangYu:
		case ZhuDong_JiaShuXing_KongZhi:
		case ZhuDong_FaShu_KongZhiHuiFu:
		case ZhuDong_JianShang_KongZhiFaShu:
		case ZhuDong_FaShuShangHai_KongZhiGongji:
			isZhuiJi = true;
			break;
		default:
			isZhuiJi = false;
			break;
		}
		return isZhuiJi;
	}
	/**
	 * 降低敌军防御的战法
	 * @param zf
	 * @return
	 */
	public static boolean isDownFangYu(ZhanFa zf) {
		boolean isDownFangYu = false;
		switch (zf.getT()) {
		case ZhuDong_BaoZou_jianFangYu:
			isDownFangYu = true;
			break;
		default:
			isDownFangYu = false;
			break;
		}
		return isDownFangYu;
	}
	/**
	 * 降低敌军攻击的战法
	 * @param zf
	 * @return
	 */
	public static boolean isDownGongJi(ZhanFa zf) {
		boolean isDownGongJi = false;
		switch (zf.getT()) {
		case ZhuDong_FaShu_jianGongJi:
			isDownGongJi = true;
			break;
		default:
			isDownGongJi = false;
			break;
		}
		return isDownGongJi;
	}
	/**
	 * 被控制的战法 像：被混乱控制
	 * @param zf
	 * @return
	 */
	public static boolean isBeiKongZhi(ZhanFa zf) {
		boolean isBeiKongZhi = false;
		switch (zf.getT()) {
		case ZhuDong_FaShu:
		case ZhuDong_BaoZou:
		case ZhuDong_GongJi:
		case ZhuDong_JiaShuXing:
		case ZhuDong_KongZhi_ALL:
		case ZhuDong_FaShu_ZiDai:
		case ZhuDong_QunTi_HuiFu:
		case ZhuDong_GongJi_YiJi:
		case ZhuDong_FaShu_GongJi:
		case ZhuDong_FaShu_BaoZou:
		case ZhuDong_FaShu_JianShang:
		case ZhuDong_FaShu_KeepHuiHe:
		case ZhuDong_FaShu_jianGongJi:
		case ZhuDong_Multiple_KongZhi:
		case ZhuDong_JiaGongJi_LianJi:
		case ZhuDong_ShouCi_JiaGongJi:
		case ZhuDong_BaoZou_jianFangYu:
		case ZhuDong_JiaShuXing_KongZhi:
		case ZhuDong_FaShu_KongZhiHuiFu:
		case ZhuDong_ZiSheng_YouJun_HuiFu:
		case ZhuDong_JianShang_KongZhiFaShu:
		case ZhuDong_FaShuShangHai_KongZhiGongji:
		/** 追击类攻击战法 **/
		case ZhuiJi_GongJi_FengFaShu:
		case ZhuiJi_GongJi_KongZhiGongJi:
			isBeiKongZhi = true;
			break;
		default:
			isBeiKongZhi = false;
			break;
		}
		return isBeiKongZhi;
	}
	/**
	 * 自带需要准备的战法
	 * @param zf
	 * @return
	 */
	public static boolean isZiDaiReady(ZhanFa zf) {
		boolean isZiDaiReady = false;
		switch (zf.getT()) {
		case ZhuDong_GongJi:
		case ZhuDong_FaShu_ZiDai:
		case ZhuDong_BaoZou_jianFangYu:
		case ZhuDong_FaShuShangHai_KongZhiGongji:
			isZiDaiReady = true;
			break;
		default:
			isZiDaiReady = false;
			break;
		}
		return isZiDaiReady;
	}
	/**
	 * 恢复自身兵力的战法
	 * @param zf
	 * @return
	 */
	public static boolean isZiShenHuiFu(ZhanFa zf) {
		boolean isHuiFu = false;
		switch (zf.getT()) {
		case BeiDong_huifu:
		case ZhuDong_ZiSheng_YouJun_HuiFu:
			isHuiFu = true;
			break;
		default:
			isHuiFu = false;
			break;
		}
		return isHuiFu;
	}
	/**
	 * 恢复全体兵力的战法
	 * @param zf
	 * @return
	 */
	public static boolean isQunTiHuiFu(ZhanFa zf) {
		boolean isHuiFu = false;
		switch (zf.getT()) {
		case ZhuDong_QunTi_HuiFu:
		case ZhuDong_ZiSheng_YouJun_HuiFu:
			isHuiFu = true;
			break;
		default:
			isHuiFu = false;
			break;
		}
		return isHuiFu;
	}
}
