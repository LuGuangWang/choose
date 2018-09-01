package wlg.core.calc;

import wlg.core.bean.HuiHe;
import wlg.core.bean.zhanfa.KongZhiAndHarmZhanFa;
import wlg.core.bean.zhanfa.KongZhiZhanFa;
import wlg.core.bean.zhanfa.MaiLeiZhanFa;
import wlg.core.bean.zhanfa.ZFType;
import wlg.core.bean.zhanfa.ZhanFa;
/**
 * 计算发动战法成功概率
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
	 * 加成战法发动成功的概率
	 * @return
	 */
	public static float getJiaChengRate() {
		return 1.0f;
	}
	/**
	 * 有刷新战法,发动战法成功概率
	 * @param huihe
	 * @param zhanfa
	 * @return
	 */
	public static <T extends ZhanFa> float getShuaXinRate(HuiHe huihe, T zhanfa) {
		float rate = 0;
		//可以发动战法
		if(huihe.getId() > zhanfa.getReady()) {
			rate = 1;
		} 
		//埋雷战法
		if(zhanfa instanceof MaiLeiZhanFa) {
			rate = 0;
			MaiLeiZhanFa mz = (MaiLeiZhanFa) zhanfa;
			int ready = zhanfa.getReady() + 1;
			
			if(huihe.getId() == ready) {
				rate = mz.getSpeed()>0?1:0;
			//刷新后,战法可以叠加
			}else if(huihe.getId()> ready) {
				rate = 1;
			}
			//持续回合
			rate = rate * mz.getKeep();
		}
		//控制
		if(zhanfa instanceof KongZhiZhanFa) {
			rate = 0;
			int ready = zhanfa.getReady() + 1;
			if(huihe.getId() == ready) {
				rate = 1;
			//可能已发动过战法 存在同等或更高程度,不会叠加战法
			}else if(huihe.getId()> ready) {
				rate = 1 - zhanfa.getDoneRate();
			}
		}
		//持续多少回合后，进行伤害
		if(zhanfa.getT().equals(ZFType.ZhiHui_KongZhiGongJi_FaShuShangHai)) {
			KongZhiAndHarmZhanFa t = (KongZhiAndHarmZhanFa)zhanfa;
			if(huihe.getId()>(t.getKeephuihe()+1)) {
				rate = 0;
			}
		}
		return rate;
	}
	/**
	 * 控制成功的概率
	 * @param huihe
	 * @param zhanfa
	 * @return
	 */
	public static <T extends ZhanFa> float getKongZhiRate(HuiHe huihe, T zhanfa) {
		float rate = 0;
		//可以发动战法
		if(huihe.getId() > zhanfa.getReady()) {
			rate = 1;
		} 
		if(zhanfa.getT().equals(ZFType.ZhuDong_FaShuShangHai_KongZhiGongji)) {
			KongZhiAndHarmZhanFa t = (KongZhiAndHarmZhanFa)zhanfa;
			rate = 0;
			int ready = t.getReady() + 1;
			if(huihe.getId() == ready) {
				rate = 1;
			//可能已发动过战法 存在同等或更高程度,不会叠加战法
			}else if(huihe.getId()> ready) {
				rate = 1 - t.getDoneRate();
			}
		}
		return rate;
	}
	/**
	 * 无刷新战法,发动成功的概率
	 * @param huihe
	 * @param zhanfas
	 * @return
	 */
	public static <T extends ZhanFa> float getCommRate(HuiHe huihe, T zhanfa) {
		float rate = 0;
		//可以发动战法
		if(huihe.getId() > zhanfa.getReady()) {
			rate = 1;
		} 
		//埋雷战法
		if(zhanfa instanceof MaiLeiZhanFa) {
			rate = 0;
			MaiLeiZhanFa mz = (MaiLeiZhanFa) zhanfa;
			int ready = zhanfa.getReady() + 1;
			if(huihe.getId() == ready) {
				rate = mz.getSpeed()>0?1:0;
			//可能已发动过战法 存在同等或更高程度,不会叠加战法
			}else if(huihe.getId()> ready) {
				rate = 1 - zhanfa.getDoneRate();
			}
			//持续回合
			rate = rate * mz.getKeep();
		}
		//减伤
		if(zhanfa instanceof KongZhiZhanFa) {
			rate = 0;
			int ready = zhanfa.getReady() + 1;
			if(huihe.getId() == ready) {
				rate = 1;
			//TODO 同类型 同效果战法才不能叠加
			//可能已发动过战法 存在同等或更高程度,不会叠加战法
			}else if(huihe.getId()> ready) {
				rate = 1 - zhanfa.getDoneRate();
			}
		}
		//持续多少回合
		if(zhanfa.getT().equals(ZFType.ZhiHui_KongZhiGongJi_FaShuShangHai)) {
			KongZhiAndHarmZhanFa t = (KongZhiAndHarmZhanFa)zhanfa;
			if(huihe.getId()>(t.getKeephuihe()+1)) {
				rate = 0;
			}
		}
		
		return rate;
	}
}
