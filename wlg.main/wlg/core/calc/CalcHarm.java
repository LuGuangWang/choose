package wlg.core.calc;

import java.util.HashMap;
import java.util.Map;

import wlg.core.CheckUtil;
import wlg.core.bean.HuiHe;
import wlg.core.bean.conf.Conf;
import wlg.core.bean.zhanfa.KongZhiAndHarmZhanFa;
import wlg.core.bean.zhanfa.KongZhiZhanFa;
import wlg.core.bean.zhanfa.ZFType;
import wlg.core.bean.zhanfa.ZhanFa;

public class CalcHarm {
	
	@SuppressWarnings("unchecked")
	public static <T extends ZhanFa> float calcKongZhiHuiHe(HuiHe huihe, T... zhanfa) {
		float sum = 0;
		Map<String,Float> kongzhiMap = new HashMap<>();
		Conf.log("==================计算控制战法生效时造成的伤害值==============");
		for(int i=0;i<zhanfa.length;i++) {
			if(zhanfa[i] instanceof KongZhiZhanFa) {
				KongZhiZhanFa b = (KongZhiZhanFa)zhanfa[i];
				for(int p:b.getPersons().getPersons()) {
					//不受伤害的概率
					float rate = CalcDoRate.getCommRate(huihe,b);
					float unHurt = p/1.0f/huihe.getWujiangCount();
					unHurt = unHurt>1 ? rate:rate*unHurt;
					//控制主的概率
					float kongzhiRate = unHurt * b.getDoneRate();
					float unHurtVal = kongzhiRate * b.getKeep() * calcKongZhiAllHuiHe(huihe.getAllFeng(kongzhiRate,p),zhanfa);
					kongzhiMap.put(b.getName(), kongzhiRate);
					sum += unHurtVal;
				}
			}
			if(zhanfa[i].getT().equals(ZFType.ZhiHui_KongZhiGongJi_FaShuShangHai)) {
				KongZhiAndHarmZhanFa b = (KongZhiAndHarmZhanFa)zhanfa[i];
				for(int p:b.getPersons().getPersons()) {
					//不受伤害的概率
					float rate = CalcDoRate.getCommRate(huihe,b);
					float unHurt = p/1.0f/huihe.getWujiangCount();
					unHurt = unHurt>1 ? rate:rate*unHurt;
					if(huihe.getId()>b.getKeephuihe()) {
						unHurt = 0;
					}
					//控制主的概率
					float kongzhiRate = unHurt * b.getDoneRate();
					float unHurtVal = kongzhiRate * calcKongZhiAllHuiHe(huihe.getFengGongji(kongzhiRate,p),zhanfa);
					kongzhiMap.put(b.getName(), kongzhiRate);
					sum += unHurtVal;
				}
			}
			if(zhanfa[i].getT().equals(ZFType.ZhuDong_FaShuShangHai_KongZhiGongji)) {
				KongZhiAndHarmZhanFa b = (KongZhiAndHarmZhanFa)zhanfa[i];
				for(int p:b.getPersons().getPersons()) {
					//不受伤害的概率
					float rate = CalcDoRate.getKongZhiRate(huihe,b);
					float unHurt = p/1.0f/huihe.getWujiangCount();
					unHurt = unHurt>1 ? rate:rate*unHurt;
					//控制主的概率
					float kongzhiRate = unHurt * b.getDoneRate();
					float unHurtVal = b.getKeephuihe() * kongzhiRate * calcKongZhiAllHuiHe(huihe.getFengGongji(kongzhiRate,p),zhanfa);
					kongzhiMap.put(b.getName(), kongzhiRate);
					sum += unHurtVal;
				}
			}
			if(zhanfa[i].getT().equals(ZFType.ZhuDong_FaShu_JianShang)){
				ZhanFa b = zhanfa[i];
				int p = 1;
				//不受伤害的概率
				float rate = CalcDoRate.getKongZhiRate(huihe,b);
				float unHurt = p/1.0f/huihe.getWujiangCount();
				unHurt = unHurt>1 ? rate:rate*unHurt;
				//控制主的概率
				float kongzhiRate = unHurt * b.getDoneRate();
				float unHurtVal = kongzhiRate * calcKongZhiAllHuiHe(huihe.getFengGongji(kongzhiRate*b.getExHarmVal(),p),zhanfa);
				kongzhiMap.put(b.getName(), kongzhiRate);
				sum += unHurtVal;
			}
		}
		Conf.log("==================计算控制战法不生效时造成的伤害值==============");
		//受伤的概率
		float kongzhiRate = 0.0f;
		for(float t:kongzhiMap.values()) {
			kongzhiRate += t;
		}
		float hurt = 1 - kongzhiRate;
		Conf.log("======本回合不受伤的概率：" + kongzhiRate + " 受伤的概率为："+hurt);
		hurt = hurt>0 ? hurt:0;
		float hurtVal = hurt *  calcKongZhiAllHuiHe(huihe,zhanfa);
		sum += hurtVal;
		
		return sum;
	}

	@SuppressWarnings("unchecked")
	public static <T extends ZhanFa> float calcKongZhiAllHuiHe(HuiHe huihe, T... zhanfa) {
		float sum = 0.0f;
		Conf.log("=============计算有控制战法的普通主伤害值==========");
		sum = calcCommHuiHe(huihe,zhanfa);
		Conf.log("=============计算有控制战法的普通增益害值==========");
		sum += calcExVal(huihe,zhanfa);
		return sum;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends ZhanFa> float calcCommHuiHe(HuiHe huihe, T... zhanfa) {
		float sum = 0;
		//主要伤害
		for(int i=0;i<zhanfa.length;i++) {
			T z = zhanfa[i];
			float rate = huihe.getShuaxinRate()>0?CalcDoRate.getShuaXinRate(huihe, z):CalcDoRate.getCommRate(huihe, z);
			float shuaxinRate = huihe.getShuaxinRate() * huihe.getId();
			if(z.getT().equals(ZFType.ZhiHui_KongZhiGongJi_FaShuShangHai)) {
				KongZhiAndHarmZhanFa tmp = (KongZhiAndHarmZhanFa) z;
				if(tmp.getKeephuihe()+1 == huihe.getId()) {
					shuaxinRate += tmp.getExHarmVal();
				}else {
					shuaxinRate += z.getHarmRate();
				}
			}else {
				shuaxinRate += z.getHarmRate();
			}
			float harmval = rate * z.getHarmVal(shuaxinRate) * huihe.getSolderRate(z.getPosition(),z.getDefense());
			Conf.log("===战法 " + z.getName() + " 最终杀伤力：" + harmval);
			sum += harmval;
		}
		return sum;
	}

	/**
	 * 增益伤害
	 * @param huihe
	 * @param zhanfa
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends ZhanFa> float calcExVal(HuiHe huihe,T... zhanfa) {
		float sum = 0;
		if(zhanfa.length>1) {
			for(int i=0;i<zhanfa.length;i++) {
				T b = zhanfa[i];
				if(CheckUtil.isZengYi(b)) {
					float shuaxinRate = huihe.getShuaxinRate() * huihe.getId();
					shuaxinRate += b.getExHarmVal();
					for(int j=0;j<zhanfa.length;j++) {
						float exharmVal = 0.0f;
						ZhanFa zf = zhanfa[j];
						if(j!= i) {
							float rate = CalcDoRate.getCommRate(huihe, zf);
							if(zf.getT().equals(ZFType.ZhiHui_KongZhiGongJi_FaShuShangHai)) {
								KongZhiAndHarmZhanFa tmp = (KongZhiAndHarmZhanFa) zf;
								if(tmp.getKeephuihe()+1 == huihe.getId()) {
									exharmVal = rate * b.getExVal(tmp,shuaxinRate) * huihe.getSolderRate(b.getPosition(),b.getDefense());
								}
							}else {
								exharmVal = rate * b.getExVal(zf,shuaxinRate) * huihe.getSolderRate(b.getPosition(),b.getDefense());
							}
							Conf.log("======战法"+zf.getName() + " 触发战法" + b.getName() +" 造成最终额外杀伤力" + exharmVal);
						}
						sum += exharmVal;
					}
				}
			}
		}
		return sum;
	}
	
	
	
}
