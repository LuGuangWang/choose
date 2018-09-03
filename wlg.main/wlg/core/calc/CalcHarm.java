package wlg.core.calc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wlg.core.CheckUtil;
import wlg.core.bean.HuiHe;
import wlg.core.bean.conf.Conf;
import wlg.core.bean.zhanfa.FanJiZhiCeZhanFa;
import wlg.core.bean.zhanfa.JiaShangZhanFa;
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
			ZhanFa zf = zhanfa[i];
			if(zf instanceof KongZhiZhanFa) {
				float unHurtVal = calcKZZhanFa(huihe, kongzhiMap, zf, zhanfa);
				sum += unHurtVal;
			}
			if(zf.getT().equals(ZFType.ZhiHui_KongZhiGongJi_FaShuShangHai)) {
				float unHurtVal = calcKZGongJiThenFaShuShanghai(huihe, kongzhiMap, zf, zhanfa);
				sum += unHurtVal;
			}
			if(zf.getT().equals(ZFType.ZhuDong_FaShuShangHai_KongZhiGongji)) {
				float unHurtVal = calcFashuShangHaiThenKZGongji(huihe, kongzhiMap, zf, zhanfa);
				sum += unHurtVal;
			}
			if(zf.getT().equals(ZFType.ZhuDong_FaShu_JianShang)){
				float unHurtVal = calcJianshang(huihe, kongzhiMap, zf, zhanfa);
				sum += unHurtVal;
			}
			if(zf.getT().equals(ZFType.ZhiHui_JianshangFashu_KongZhiFaShu)) {
				float unHurtVal = calcFanjizhice(huihe, kongzhiMap, zf, zhanfa);
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
		Conf.log("======本回合不受伤概率下造成的伤害：" + sum + " 不受伤的概率为："+kongzhiRate);
		hurt = hurt>0 ? hurt:0;
		float hurtVal = hurt *  calcKongZhiAllHuiHe(huihe,zhanfa);
		sum += hurtVal;
		
		return sum;
	}

	@SuppressWarnings("unchecked")
	private static <T extends ZhanFa> float calcFanjizhice(HuiHe huihe, Map<String, Float> kongzhiMap, ZhanFa zf, T... zhanfa) {
		FanJiZhiCeZhanFa b = (FanJiZhiCeZhanFa)zf;
		float unHurtVal = 0.0f;
		//控制战法发动成功的概率
		float rate = CalcDoRate.getKongZhiRate(huihe,b);
		for(int p:b.getPersons().getPersons()) {
			float unHurt = p/1.0f/huihe.getWujiangCount();
			unHurt = unHurt>1 ? rate:rate*unHurt;
			//控制主的概率
			float kongzhiVal = unHurt * b.getDoneRate();
			float tmp = 0.0f;
			if(huihe.getId()==1) {
				tmp =  kongzhiVal * calcKongZhiAllHuiHe(huihe.getFengZhanfa(kongzhiVal,p),zhanfa);
			}else {
				tmp =  kongzhiVal * calcKongZhiAllHuiHe(huihe.getFengZhanfa(kongzhiVal * b.getHarmRate(),p),zhanfa);
			}
			kongzhiMap.put(b.getName(), kongzhiVal);
			unHurtVal += tmp;
		}
		return unHurtVal;
	}

	@SuppressWarnings("unchecked")
	private static <T extends ZhanFa> float calcJianshang(HuiHe huihe, Map<String, Float> kongzhiMap, ZhanFa zf, T... zhanfa) {
		ZhanFa b = zf;
		int p = 1;
		//控制战法发动成功的概率
		float rate = CalcDoRate.getKongZhiRate(huihe,b);
		//不受伤害的概率
		float unHurt = p/1.0f/huihe.getWujiangCount();
		unHurt = unHurt>1 ? rate:rate*unHurt;
		//控制主的概率
		float kongzhiVal = unHurt * b.getDoneRate();
		float unHurtVal = kongzhiVal * calcKongZhiAllHuiHe(huihe.getFengGongji(kongzhiVal*b.getExHarmVal(),p),zhanfa);
		kongzhiMap.put(b.getName(), kongzhiVal);
		return unHurtVal;
	}

	@SuppressWarnings("unchecked")
	private static <T extends ZhanFa> float calcFashuShangHaiThenKZGongji(HuiHe huihe, Map<String, Float> kongzhiMap, ZhanFa zf,
			T... zhanfa) {
		KongZhiAndHarmZhanFa b = (KongZhiAndHarmZhanFa)zf;
		float unHurtVal = 0.0f;
		//控制战法发动成功的概率
		float rate = CalcDoRate.getKongZhiRate(huihe,b);
		for(int p:b.getPersons().getPersons()) {
			float unHurt = p/1.0f/huihe.getWujiangCount();
			unHurt = unHurt>1 ? rate:rate*unHurt;
			//控制主的概率
			float kongzhiVal = unHurt * b.getDoneRate();
			float tmp = b.getKeephuihe() * kongzhiVal * calcKongZhiAllHuiHe(huihe.getFengGongji(kongzhiVal,p),zhanfa);
			kongzhiMap.put(b.getName(), kongzhiVal);
			unHurtVal += tmp;
		}
		return unHurtVal;
	}

	@SuppressWarnings("unchecked")
	private static <T extends ZhanFa> float calcKZGongJiThenFaShuShanghai(HuiHe huihe, Map<String, Float> kongzhiMap, ZhanFa zf,
			T... zhanfa) {
		float unHurtVal = 0.0f;
		KongZhiAndHarmZhanFa b = (KongZhiAndHarmZhanFa)zf;
		//控制战法发动成功的概率
		float rate = CalcDoRate.getKongZhiRate(huihe,b);
		for(int p:b.getPersons().getPersons()) {
			//不受伤害的概率
			float unHurt = p/1.0f/huihe.getWujiangCount();
			unHurt = unHurt>1 ? rate:rate*unHurt;
			if(huihe.getId()>b.getKeephuihe()) {
				unHurt = 0;
			}
			//控制主的概率
			float kongzhiVal = unHurt * b.getDoneRate();
			float tmp = kongzhiVal * calcKongZhiAllHuiHe(huihe.getFengGongji(kongzhiVal,p),zhanfa);
			kongzhiMap.put(b.getName(), kongzhiVal);
			unHurtVal += tmp;
		}
		return unHurtVal;
	}

	@SuppressWarnings("unchecked")
	private static <T extends ZhanFa> float calcKZZhanFa(HuiHe huihe, Map<String, Float> kongzhiMap, ZhanFa zf, T... zhanfa) {
		KongZhiZhanFa b = (KongZhiZhanFa)zf;
		float unHurtVal = 0.0f;
		//控制战法发动成功的概率
		float rate = CalcDoRate.getKongZhiRate(huihe,b);
		for(int p:b.getPersons().getPersons()) {
			//不受伤害的概率
			float unHurt = p/1.0f/huihe.getWujiangCount();
			unHurt = unHurt>1 ? rate:rate*unHurt;
			//控制主的概率
			float kongzhiVal = unHurt * b.getDoneRate();
			float tmp = kongzhiVal * b.getKeep() * calcKongZhiAllHuiHe(huihe.getAllFeng(kongzhiVal,p),zhanfa);
			kongzhiMap.put(b.getName(), kongzhiVal);
			unHurtVal += tmp;
		}
		return unHurtVal;
	}

	@SuppressWarnings("unchecked")
	public static <T extends ZhanFa> float calcKongZhiAllHuiHe(HuiHe huihe, T... zhanfa) {
		float sum = 0.0f;
		Conf.log("=============计算有控制战法的普通主伤害值==========");
		sum = calcCommHuiHe(huihe,zhanfa);
		if(huihe.isHasZengYi()) {
			Conf.log("=============计算有控制战法的普通增益害值==========");
			sum += calcExVal(huihe,zhanfa);
		}
		return sum;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends ZhanFa> float calcCommHuiHe(HuiHe huihe, T... zhanfa) {
		float sum = 0;
		List<JiaShangZhanFa> jss = new ArrayList<>();
		int executeJss = 0;
		//主要伤害
		for(int i=0;i<zhanfa.length;i++) {
			T z = zhanfa[i];
			float shuaxinVal = 0.0f;
			if(CheckUtil.isStrategy(z)) {
				//只对当前武将的战法生效
				if(huihe.getWj().getPosition()==z.getPosition()) {
					shuaxinVal = huihe.getShuaxinVal() * huihe.getId();
				}
			}
			if(z.getT().equals(ZFType.ZhiHui_KongZhiGongJi_FaShuShangHai)) {
				KongZhiAndHarmZhanFa tmp = (KongZhiAndHarmZhanFa) z;
				if(tmp.getKeephuihe()+1 == huihe.getId()) {
					shuaxinVal += tmp.getExHarmVal();
				}else {
					shuaxinVal += z.getHarmRate();
				}
			}else if(CheckUtil.isJiaShang(z)){
				JiaShangZhanFa tmp = (JiaShangZhanFa)z;
				if(huihe.getId()<=tmp.getKeephuihe()) {
					jss.add(tmp);
					continue;
				} else {//	方便查看日志
					shuaxinVal = 0.0f;
				}
			}else {
				shuaxinVal += z.getHarmRate();
			}
			float rate = huihe.getShuaxinVal()>0?CalcDoRate.getShuaXinRate(huihe, z):CalcDoRate.getCommRate(huihe, z);
			
			float shibingVal = huihe.getSolderRate(z.getPosition(),z.getDefense());
			float harmval = rate * z.getHarmVal(shuaxinVal) * shibingVal;
			//有伤害 才能触发加伤战法
			if(harmval>0) {
				executeJss++;
			}
			Conf.log("===战法 " + z.getName() + " 最终杀伤力：" + harmval);
			sum += harmval;
			
		}
		
		//计算加伤战法
		if(jss.size()>0) {
			for(JiaShangZhanFa zf:jss) {
				float harmval = executeJss * zf.getHarmVal() * huihe.getSolderRate(zf.getPosition(),zf.getDefense());
				Conf.log("===战法 " + zf.getName() + " 最终杀伤力：" + harmval);
				sum += harmval;
			}
		}
		
		//检查是否有武将损失
		huihe.removeWujiang(huihe.getWj());
		
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
					float shuaxinRate = huihe.getShuaxinVal() * huihe.getId();
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
