package wlg.core.calc;

import wlg.core.bean.HuiHe;
import wlg.core.bean.conf.Conf;
import wlg.core.bean.zhanfa.FanJiZhiCeZhanFa;
import wlg.core.bean.zhanfa.KongZhiAndHarmZhanFa;
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
	 * TODO 同类型的加成战法不叠加
	 * 加成战法发动成功的概率
	 * @return
	 */
	public static float getJiaChengRate(ZhanFa zf) {
		float rate = zf.getDoneRate();
		Conf.log("====战法"+zf.getName()+"成功发动的概率:"+rate);
		return rate;
	}
	/**
	 * 有刷新战法,发动战法成功概率
	 * @param huihe
	 * @param zhanfa
	 * @return
	 */
	public static <T extends ZhanFa> float getShuaXinRate(HuiHe huihe, T zhanfa) {
		float rate = getSameRate(huihe, zhanfa);
		//埋雷战法
		if(zhanfa instanceof MaiLeiZhanFa) {
			rate = 0;
			MaiLeiZhanFa mz = (MaiLeiZhanFa) zhanfa;
			int ready = zhanfa.getReady() + 1;
			
			if(huihe.getId() == ready) {
				rate = mz.getSpeed()>0?1:0;
			//刷新后,战法可以叠加
			}else if(huihe.getId()> ready) {
				//刷新战法只对本武将生效
				if(huihe.getWj().getPosition() == zhanfa.getPosition()){
					rate = 1;
				}else {
					rate = 1 - zhanfa.getDoneRate();
				}
			}
		}
		Conf.log("======第"+huihe.getId()+"回合受刷新影响，战法"+zhanfa.getName()+"成功发动的概率:"+rate);
		return rate;
	}
	/**
	 * 控制成功的概率
	 * @param huihe
	 * @param zhanfa
	 * @return
	 */
	public static <T extends ZhanFa> float getKongZhiRate(HuiHe huihe, T zhanfa) {
		float rate = getSameRate(huihe, zhanfa);
		//发动后下一回合生效
		if(zhanfa.getT().equals(ZFType.ZhuDong_FaShu_JianShang)) {
			rate = 0;
			int ready = zhanfa.getReady() + 1;
			if(huihe.getId() > ready) {
				rate = 1;
			}
		}
		
		Conf.log("======第"+huihe.getId()+"回合战法"+zhanfa.getName()+"成功发动控制的概率:"+rate);
		return rate;
	}
	/**
	 * 无刷新战法,发动成功的概率
	 * @param huihe
	 * @param zhanfas
	 * @return
	 */
	public static <T extends ZhanFa> float getCommRate(HuiHe huihe, T zhanfa) {
		float rate = getSameRate(huihe, zhanfa);
		//埋雷战法
		if(zhanfa instanceof MaiLeiZhanFa) {
			rate = 0;
			MaiLeiZhanFa mz = (MaiLeiZhanFa) zhanfa;
			int ready = zhanfa.getReady() + 1;
			if(huihe.getId() == ready) {
				rate = mz.getSpeed()>0?1:0;
			//TODO 同类型 同效果战法才不能叠加
			//可能已发动过战法 存在同等或更高程度,不会叠加战法
			}else if(huihe.getId()> ready) {
				rate = 1 - zhanfa.getDoneRate();
			}
		}
		Conf.log("======第"+huihe.getId()+"回合战法"+zhanfa.getName()+"成功发动的概率:"+rate);
		return rate;
	}
	/**
	 * 一般,刷新,控制战法 相同逻辑的概率 
	 * @param huihe
	 * @param zhanfa
	 * @return
	 */
	private static <T extends ZhanFa> float getSameRate(HuiHe huihe, T zhanfa) {
		float rate = 0;
		//可以发动战法  //控制战法 效果一样 相当于叠加
		if(huihe.getId() > zhanfa.getReady()) {
			rate = 1;
		} 
		//持续多少回合后
		if(zhanfa.getT().equals(ZFType.ZhiHui_KongZhiGongJi_FaShuShangHai)) {
			KongZhiAndHarmZhanFa t = (KongZhiAndHarmZhanFa)zhanfa;
			if(huihe.getId()>(t.getKeephuihe()+1)) {
				rate = 0;
			}
		}
		//持续多少回合
		if(zhanfa.getT().equals(ZFType.ZhiHui_JianshangFashu_KongZhiFaShu)) {
			FanJiZhiCeZhanFa t = (FanJiZhiCeZhanFa)zhanfa;
			if(huihe.getId()>t.getKeephuihe()) {
				rate = 0;
			}
		}
		return rate;
	}
}
