package wlg.core.calc;

import java.util.List;

import wlg.core.CheckUtil;
import wlg.core.bean.HuiHe;
import wlg.core.bean.conf.Conf;
import wlg.core.bean.wujiang.WuJiang;
import wlg.core.bean.zhanfa.ConflictList;
import wlg.core.bean.zhanfa.KongZhiAndHarmZhanFa;
import wlg.core.bean.zhanfa.ShiJiZhanFa;
import wlg.core.bean.zhanfa.ZFType;
import wlg.core.bean.zhanfa.ZhanFa;

/**
 * 计算发动战法成功概率
 * 
 * @author seven
 *
 */
public class CalcDoRate {
	/**
	 * 普通攻击的发动成功的概率
	 */
	public static float getAttackRate() {
		return 1.0f;
	}

	/**
	 * TODO 同类型的加成战法不叠加 加成战法发动成功的概率
	 * 
	 * @return
	 */
	public static float getJiaChengRate(ZhanFa zf) {
		float rate = zf.getDoneRate();
		Conf.log("====战法" + zf.getName() + "成功发动的概率:" + rate);
		return rate;
	}

	/**
	 * 无刷新战法,发动成功的概率
	 * 
	 * @param huihe
	 * @param zhanfas
	 * @return
	 */
	public static <T extends ZhanFa> float getCommRate(HuiHe huihe, T zhanfa) {
		float rate = getSameRate(huihe, zhanfa);
		if(rate>0) {
			// 免疫控制
			if (CheckUtil.isBeiKongZhi(zhanfa)) {
				rate *= huihe.getWj().getMianyiVal();
			} 
			//先手效果
			rate += huihe.getXianshouRate();
			
			rate = rate>1.0f?1.0f:rate;
		}
		Conf.log("======第" + huihe.getId() + "回合战法" + zhanfa.getName() + "成功发动的概率:" + rate);
		return rate;
	}

	/**
	 * 控制成功的概率
	 * 
	 * @param huihe
	 * @param zhanfa
	 * @return
	 */
	public static <T extends ZhanFa> float getKongZhiRate(HuiHe huihe, T zhanfa) {
		float rate = getSameRate(huihe, zhanfa);
		//十面埋伏
		if (zhanfa.getT().equals(ZFType.ZhuDong_FaShu_JianShang)) {
			rate = 0;
			int ready = zhanfa.getReady() + 1;
			if (huihe.getId() > ready) {
				rate = 0.5f;
			}
		}
		if(rate>0) {
			// 免疫控制
			if (CheckUtil.isBeiKongZhi(zhanfa)) {
				rate *= huihe.getWj().getMianyiVal();
			} 
			//先手效果
			rate += huihe.getXianshouRate();
			
			rate = rate>1.0f?1.0f:rate;
		}
		Conf.log("======第" + huihe.getId() + "回合战法" + zhanfa.getName() + "成功发动控制的概率:" + rate);
		return rate;
	}

	private static boolean isXianYuDaying(List<WuJiang> wujiangs) {
		int dayingPos = 0, zhanfaPos = 3, pos = 0;
		boolean isXYDY = false;
		int daying = 1;
		if (wujiangs.size() == 2) {
			daying = 2;
		} else if (wujiangs.size() == 1) {
			daying = 3;
		}

		for (WuJiang wj : wujiangs) {
			pos++;
			if (wj.getFinalp() == daying) {
				dayingPos = pos;
			}
			for (ZhanFa zf : wj.getZhanfa()) {
				if (zf.getT().equals(ZFType.ZhiHui_JiaFaShu_JianShang_MianYi)) {
					zhanfaPos = pos;
					break;
				}
			}
		}
		isXYDY = zhanfaPos <= dayingPos;
		Conf.log("========战法始计是否先发于大营武将:" + isXYDY);
		return isXYDY;
	}

	/**
	 * 一般,刷新,控制战法 相同逻辑的概率
	 * 
	 * @param huihe
	 * @param zhanfa
	 * @return
	 */
	private static <T extends ZhanFa> float getSameRate(HuiHe huihe, T zhanfa) {
		float rate = 0;
		// 可以发动战法 //控制战法 效果一样 相当于叠加
		if (huihe.getId() > zhanfa.getReady()) {
			if (zhanfa.getReady() > 0) {
				rate = 0.5f;
			} else {
				rate = 1.0f;
			}
		}
		// 可能已发动过战法 存在同等或更高程度,不会叠加战法
		if (CheckUtil.isKongZhiKeep(zhanfa)) {
			int ready = zhanfa.getReady() + 1;
			if (huihe.getId() > ready) {
				// 有刷新，且有伤害，刷新对当武将自身战法生效
				if (huihe.getShuaxinVal() > 0 && zhanfa.getHarmRate() > 0
						&& huihe.getShuaxinPos() == zhanfa.getPosition()) {
					rate = 1.0f;
				} else {
					int wjCount = Conf.WuJiang_Count;
					int psize = zhanfa.getPersons().getPersons().length;
					float thiR = 0.0f;
					for (int p : zhanfa.getPersons().getPersons()) {
						int live = (wjCount - Math.min(p, wjCount));
						live = live > 0 ? live : 0;
						// 同样两个人的概率
						switch (live) {
						case 2:
							thiR += 1.0f / 3.0f / psize * zhanfa.getDoneRate();
							break;
						case 1:
							thiR += 1.0f / 6.0f / psize * zhanfa.getDoneRate();
							break;
						default:
							thiR += 1.0f / psize * zhanfa.getDoneRate();
						}
					}
					rate = (1 - thiR) > 0 ? 1 - thiR : 0.0f;
					if (zhanfa.getReady() > 0) {
						rate *= 0.5f;
					}
				}
			}
		}

		// 胜兵求战
		if (huihe.getSkipReadyVal() > 0 && huihe.getSkipReadyPos() == zhanfa.getPosition()
				&& CheckUtil.isZiDaiReady(zhanfa)) {
			rate = 1.0f;
		}

		// 持续多少回合后
		if (zhanfa.getT().equals(ZFType.ZhiHui_KongZhiGongJi_FaShuShangHai)) {
			KongZhiAndHarmZhanFa t = (KongZhiAndHarmZhanFa) zhanfa;
			if (huihe.getId() > (t.getKeephuihe() + 1)) {
				t.setHarmRate(0.0f);// 会影响增益战法的计算
				rate = 0;
			}
		} else if (zhanfa.getT().equals(ZFType.ZhiHui_JiaFaShu_JianShang_MianYi)) {
			boolean isXYDY = isXianYuDaying(huihe.getWujiangs());
			int keephuihe = ((ShiJiZhanFa) zhanfa).getKeephuihe();
			rate = 0.0f;
			if (isXYDY) {
				if (huihe.getId() <= keephuihe) {
					rate = 1.0f;
				}
			} else {
				if (huihe.getId() >= 2 && huihe.getId() <= (keephuihe + 1)) {
					rate = 1.0f;
				}
			}
			
		}
		//战斗开始后前多少回合
		if(CheckUtil.isChiXuHuiHe(zhanfa)) {
			if (huihe.getId() > zhanfa.getChiXuHuihe()) {
				rate = 0.0f;
			}
		}
		
		// 帝临回光
		if (zhanfa.getT().equals(ZFType.ZhiHui_JiaJuLi_FenBing_KongHuang)
				&& ConflictList.$().isZhiHuiKonghuangchongtu()) {
			rate = 0;
		}

		return rate;
	}

	public static float calcMianyiVal(int speed) {
		float mianyiVal = speed / Conf.base_speed;
		mianyiVal = mianyiVal > Conf.max_mianyi_val ? Conf.max_mianyi_val
				: (mianyiVal < Conf.min_mianyi_val ? Conf.min_mianyi_val : mianyiVal);
		return mianyiVal;
	}

}
