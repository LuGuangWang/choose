package wlg.core.calc;

import java.util.List;

import wlg.core.CheckUtil;
import wlg.core.bean.HuiHe;
import wlg.core.bean.conf.Conf;
import wlg.core.bean.wujiang.WuJiang;
import wlg.core.bean.zhanfa.ConflictList;
import wlg.core.bean.zhanfa.FanJiZhiCeZhanFa;
import wlg.core.bean.zhanfa.JiaShangZhanFa;
import wlg.core.bean.zhanfa.KongZhiAndHarmZhanFa;
import wlg.core.bean.zhanfa.MaiLeiZhanFa;
import wlg.core.bean.zhanfa.ShiJiZhanFa;
import wlg.core.bean.zhanfa.ZFType;
import wlg.core.bean.zhanfa.ZhanBiZhanFa;
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
				float tr = mz.getSpeed()/Conf.base_speed;
				rate = tr>1?1:tr;
			//刷新后,战法可以叠加
			}else if(huihe.getId()> ready) {
				//刷新战法只对本武将生效
				if(huihe.getWj().getPosition() == zhanfa.getPosition()){
					rate = 1;
				}
			}
		}
		//免疫控制
		if(!CheckUtil.isMianYiKongZhi(zhanfa)) {
			rate *= huihe.getWj().getMianyiFSVal();
		}else if(CheckUtil.isAttack(zhanfa)) {
			rate *= huihe.getWj().getMianyiGJVal();
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
				rate = 1.0f;
			}
		}
		if(zhanfa.getT().equals(ZFType.ZhiHui_JiaFaShu_JianShang_MianYi)) {
			boolean isXYDY = isXianYuDaying(huihe.getWujiangs());
			int keephuihe = ((ShiJiZhanFa)zhanfa).getKeephuihe();
			rate = 0.0f;
			if(isXYDY) {
				if(huihe.getId() <= keephuihe) {
					rate = 1.0f;
				}
			} else {
				if(huihe.getId() >= 2 && huihe.getId() <= (keephuihe+1)) {
					rate = 1.0f;
				}
			}
		}
		//免疫控制
		if(!CheckUtil.isMianYiKongZhi(zhanfa)) {
			rate *= huihe.getWj().getMianyiFSVal();
		}else if(CheckUtil.isAttack(zhanfa)) {
			rate *= huihe.getWj().getMianyiGJVal();
		}
		Conf.log("======第"+huihe.getId()+"回合战法"+zhanfa.getName()+"成功发动控制的概率:"+rate);
		return rate;
	}
	
	private static boolean isXianYuDaying(List<WuJiang> wujiangs) {
		int dayingPos = 0,zhanfaPos = 3,pos = 0;
		boolean isXYDY = false;
		int daying = 1;
		if(wujiangs.size()==2) {
			daying = 2;
		}else if(wujiangs.size()==1) {
			daying = 3;
		}
		
		for(WuJiang wj:wujiangs) {
			pos ++ ;
			if(wj.getFinalp()==daying) {
				dayingPos = pos;
			}
			for(ZhanFa zf:wj.getZhanfa()) {
				if(zf.getT().equals(ZFType.ZhiHui_JiaFaShu_JianShang_MianYi)) {
					zhanfaPos = pos;
					break;
				}
			}
		}
		isXYDY = zhanfaPos<=dayingPos;
		Conf.log("========战法始计是否先发于大营武将:"+isXYDY);
		return isXYDY;
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
		//TODO 同类型 同效果战法才不能叠加
		if(zhanfa instanceof MaiLeiZhanFa) {
			rate = 0;
			MaiLeiZhanFa mz = (MaiLeiZhanFa) zhanfa;
			int ready = zhanfa.getReady() + 1;
			if(huihe.getId() == ready) {
				float tr = mz.getSpeed()/Conf.base_speed;
				rate = tr>1 ?1:tr;
			}
		}
		//免疫控制
		if(!CheckUtil.isMianYiKongZhi(zhanfa)) {
			rate *= huihe.getWj().getMianyiFSVal();
		}else if(CheckUtil.isAttack(zhanfa)) {
			rate *= huihe.getWj().getMianyiGJVal();
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
		//可能已发动过战法 存在同等或更高程度,不会叠加战法
		if(CheckUtil.isKongZhiKeep(zhanfa)) {
			int ready = zhanfa.getReady() + 1;
			if(huihe.getId()> ready) {
				int wjCount = huihe.getWujiangCount();
				int psize = zhanfa.getPersons().getPersons().length;
				float thiR = 0.0f;
				for(int p:zhanfa.getPersons().getPersons()) {
					int live = (wjCount-Math.min(p,wjCount));
					live = live>0?live:0;
					//同样两个人的概率
					switch(live) {
					case 2:
						thiR += 1.0f/3.0f/psize * zhanfa.getDoneRate();
						break;
					case 1:
						thiR += 1.0f/6.0f/psize * zhanfa.getDoneRate();
						break;
					default:
						thiR += 1.0f/psize * zhanfa.getDoneRate();
					}
				}
				rate = (1 - thiR)>0?1 - thiR:0.0f;
			}
		}
		//持续多少回合后
		if(zhanfa.getT().equals(ZFType.ZhiHui_KongZhiGongJi_FaShuShangHai)) {
			KongZhiAndHarmZhanFa t = (KongZhiAndHarmZhanFa)zhanfa;
			if(huihe.getId()>(t.getKeephuihe()+1)) {
				t.setHarmRate(0.0f);//会影响增益战法的计算
				rate = 0;
			}
		}
		//战斗开始后前多少回合
		if(zhanfa.getT().equals(ZFType.ZhiHui_JianshangFashu_KongZhiFaShu)) {
			FanJiZhiCeZhanFa t = (FanJiZhiCeZhanFa)zhanfa;
			if(huihe.getId()>t.getKeephuihe()) {
				t.setHarmRate(0.0f);//会影响增益战法的计算
				rate = 0;
			}
		}
		//战斗开始后前多少回合
		if(zhanfa.getT().equals(ZFType.ZhiHui_FuZhu_ALL)) {
			JiaShangZhanFa t = (JiaShangZhanFa)zhanfa;
			if(huihe.getId()>t.getKeephuihe()) {
				t.setHarmRate(0.0f);//会影响增益战法的计算
				rate = 0;
			}
		}
		//战斗开始后前多少回合
		if(zhanfa.getT().equals(ZFType.ZhiHui_KongZhiGongJi)) {
			ZhanBiZhanFa t = (ZhanBiZhanFa)zhanfa;
			if(huihe.getId()>t.getKeephuihe()) {
				t.setHarmRate(0.0f);//会影响增益战法的计算
				rate = 0;
			}
		}
		//帝临回光
		if(zhanfa.getT().equals(ZFType.ZhiHui_JiaJuLi_FenBing_KongHuang) && ConflictList.$().isZhiHuiKonghuangchongtu()) {
			rate = 0;
		}
		
		return rate;
	}
	
}
