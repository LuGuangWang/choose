package wlg.core.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import wlg.core.CheckUtil;
import wlg.core.bean.HuiHe;
import wlg.core.bean.conf.Conf;
import wlg.core.bean.zhanfa.FanJiZhiCeZhanFa;
import wlg.core.bean.zhanfa.JiaShangZhanFa;
import wlg.core.bean.zhanfa.KongZhiAndHarmZhanFa;
import wlg.core.bean.zhanfa.KongZhiZhanFa;
import wlg.core.bean.zhanfa.MultipleHarmZhanFa;
import wlg.core.bean.zhanfa.QiangShiZhanFa;
import wlg.core.bean.zhanfa.ZFType;
import wlg.core.bean.zhanfa.ZhanFa;

public class CalcHarm {
	
	public static <T extends ZhanFa> float calcKongZhiHuiHe(HuiHe huihe, boolean calcPrimy,List<ZhanFa> kongzhiZf,T[] zhanfa) {
		float sum = 0;
		Map<String,Float> kongzhiMap = new HashMap<>();
		//所有战法
		Set<ZhanFa> allZfSet= new HashSet<>(Arrays.asList(zhanfa));
		//对其他武将影响的战法
		kongzhiZf.forEach(zf->{
			if(CheckUtil.isAllCalc(zf)) {
				allZfSet.add(zf);
			}
		});
		ZhanFa[] allZfs = allZfSet.toArray(new ZhanFa[allZfSet.size()]);
		
		Conf.log("==================计算控制战法生效时造成的伤害值==============");
		for(int i=0;i<kongzhiZf.size();i++) {
			ZhanFa zf = kongzhiZf.get(i);
			float unHurtVal = 0.0f;
			
			if(zf instanceof KongZhiZhanFa) {
				unHurtVal = calcKZZhanFa(huihe,calcPrimy, kongzhiMap, zf, allZfs);
			}else if(zf.getT().equals(ZFType.ZhiHui_KongZhiGongJi_FaShuShangHai)) {
				unHurtVal = calcKZGongJiThenFaShuShanghai(huihe, calcPrimy,kongzhiMap, zf, allZfs);
			}else  if(zf.getT().equals(ZFType.ZhuDong_FaShuShangHai_KongZhiGongji)) {
				unHurtVal = calcFashuShangHaiThenKZGongji(huihe, calcPrimy,kongzhiMap, zf, allZfs);
			}else if(zf.getT().equals(ZFType.ZhuDong_FaShu_JianShang)){
				unHurtVal = calcJianshang(huihe, calcPrimy,kongzhiMap, zf, allZfs);
			}else if(zf.getT().equals(ZFType.ZhiHui_JianshangFashu_KongZhiFaShu)) {
				unHurtVal = calcFanjizhice(huihe,calcPrimy, kongzhiMap, zf, allZfs);
			}else if(zf.getT().equals(ZFType.ZhuDong_JianShang_KongZhiFaShu)) {
				unHurtVal = calcQiangShi(huihe, calcPrimy, kongzhiMap, zf,allZfs);
			}
			
			sum += unHurtVal;
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
		float hurtVal = hurt *  calcKongZhiAllHuiHe(huihe,calcPrimy,allZfs);
		sum += hurtVal;
		
		return sum;
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends ZhanFa> float calcQiangShi(HuiHe huihe, boolean calcPrimy, Map<String, Float> kongzhiMap,
			ZhanFa zf, T... zhanfa) {
		float unHurtVal = 0.0f;
		
		QiangShiZhanFa b = (QiangShiZhanFa)zf;
		//控制战法发动成功的概率
		float rate = CalcDoRate.getKongZhiRate(huihe,b);
		//每个人数的随机概率
		float evrate = 1.0f/b.getPersons().getPersons().length;
		for(int p:b.getPersons().getPersons()) {
			int distance = CalCDistance.calcDistance(b.getDistance(), b.getPosition());
			if(distance<=0) {
				continue;
			}else {
				p = Math.min(p, distance);
			}
			float unHurt = evrate * p/1.0f/huihe.getWujiangCount();
			unHurt = unHurt>1 ? rate:rate*unHurt;
			//控制主的概率
			float kongzhiVal = unHurt * b.getDoneRate();
			float tmp = 0.0f;
			tmp =  kongzhiVal * b.getKeephuihe() * calcKongZhiAllHuiHe(huihe.getAllFeng(1.0f, b.getJianshangVal()),calcPrimy,zhanfa);
			kongzhiMap.put(b.getName(), kongzhiVal);
			unHurtVal += tmp;
		}
		
		return unHurtVal;
	}

	@SuppressWarnings("unchecked")
	private static <T extends ZhanFa> float calcFanjizhice(HuiHe huihe, boolean calcPrimy,Map<String, Float> kongzhiMap, ZhanFa zf, T... zhanfa) {
		FanJiZhiCeZhanFa b = (FanJiZhiCeZhanFa)zf;
		float unHurtVal = 0.0f;
		//控制战法发动成功的概率
		float rate = CalcDoRate.getKongZhiRate(huihe,b);
		//每个人数的随机概率
		float evrate = 1.0f/b.getPersons().getPersons().length;
		for(int p:b.getPersons().getPersons()) {
			int distance = CalCDistance.calcDistance(b.getDistance(), b.getPosition());
			if(distance<=0) {
				continue;
			}else {
				p = Math.min(p, distance);
			}
			float unHurt = evrate * p/1.0f/huihe.getWujiangCount();
			unHurt = unHurt>1 ? rate:rate*unHurt;
			//控制主的概率
			float kongzhiVal = unHurt * b.getDoneRate();
			float tmp = 0.0f;
			if(huihe.getId()==1) {
				tmp =  kongzhiVal * calcKongZhiAllHuiHe(huihe.getFengZhanfa(kongzhiVal),calcPrimy,zhanfa);
			}else {
				tmp =  kongzhiVal * calcKongZhiAllHuiHe(huihe.getFengZhanfa(kongzhiVal * b.getHarmRate()),calcPrimy,zhanfa);
			}
			kongzhiMap.put(b.getName(), kongzhiVal);
			unHurtVal += tmp;
		}
		return unHurtVal;
	}

	@SuppressWarnings("unchecked")
	private static <T extends ZhanFa> float calcJianshang(HuiHe huihe, boolean calcPrimy,Map<String, Float> kongzhiMap, ZhanFa zf, T... zhanfa) {
		ZhanFa b = zf;
		int p = 1;
		int distance = CalCDistance.calcDistance(b.getDistance(), b.getPosition());
		if(distance<=0) {
			p = 0;
		}else {
			p = Math.min(p, distance);
		}
		//控制战法发动成功的概率
		float rate = CalcDoRate.getKongZhiRate(huihe,b);
		//不受伤害的概率
		float unHurt = p/1.0f/huihe.getWujiangCount();
		unHurt = unHurt>1 ? rate:rate*unHurt;
		//控制主的概率
		float kongzhiVal = unHurt * b.getDoneRate();
		float unHurtVal = kongzhiVal * calcKongZhiAllHuiHe(huihe.getFengGongji(kongzhiVal*b.getExHarmVal(),p),calcPrimy,zhanfa);
		kongzhiMap.put(b.getName(), kongzhiVal);
		return unHurtVal;
	}

	@SuppressWarnings("unchecked")
	private static <T extends ZhanFa> float calcFashuShangHaiThenKZGongji(HuiHe huihe, boolean calcPrimy,Map<String, Float> kongzhiMap, ZhanFa zf,
			T... zhanfa) {
		KongZhiAndHarmZhanFa b = (KongZhiAndHarmZhanFa)zf;
		float unHurtVal = 0.0f;
		//控制战法发动成功的概率
		float rate = CalcDoRate.getKongZhiRate(huihe,b);
		//每个人数的随机概率
		float evrate = 1.0f/b.getPersons().getPersons().length;
		for(int p:b.getPersons().getPersons()) {
			int distance = CalCDistance.calcDistance(b.getDistance(), b.getPosition());
			if(distance<=0) {
				continue;
			}else {
				p = Math.min(p, distance);
			}
			float unHurt = evrate * p/1.0f/huihe.getWujiangCount();
			unHurt = unHurt>1 ? rate:rate*unHurt;
			//控制主的概率
			float kongzhiVal = unHurt * b.getDoneRate();
			float tmp = b.getKeephuihe() * kongzhiVal * calcKongZhiAllHuiHe(huihe.getFengGongji(kongzhiVal,p),calcPrimy,zhanfa);
			kongzhiMap.put(b.getName(), kongzhiVal);
			unHurtVal += tmp;
		}
		return unHurtVal;
	}

	@SuppressWarnings("unchecked")
	private static <T extends ZhanFa> float calcKZGongJiThenFaShuShanghai(HuiHe huihe,boolean calcPrimy, Map<String, Float> kongzhiMap, ZhanFa zf,
			T... zhanfa) {
		float unHurtVal = 0.0f;
		KongZhiAndHarmZhanFa b = (KongZhiAndHarmZhanFa)zf;
		//控制战法发动成功的概率
		float rate = CalcDoRate.getKongZhiRate(huihe,b);
		//每个人数的随机概率
		float evrate = 1.0f/b.getPersons().getPersons().length;
		for(int p:b.getPersons().getPersons()) {
			int distance = CalCDistance.calcDistance(b.getDistance(), b.getPosition());
			if(distance<=0) {
				continue;
			}else {
				p = Math.min(p, distance);
			}
			//不受伤害的概率
			float unHurt = evrate * p/1.0f/huihe.getWujiangCount();
			unHurt = unHurt>1 ? rate:rate*unHurt;
			if(huihe.getId()>b.getKeephuihe()) {
				unHurt = 0;
			}
			//控制主的概率
			float kongzhiVal = unHurt * b.getDoneRate();
			float tmp = kongzhiVal * calcKongZhiAllHuiHe(huihe.getFengGongji(kongzhiVal,p),calcPrimy,zhanfa);
			kongzhiMap.put(b.getName(), kongzhiVal);
			unHurtVal += tmp;
		}
		return unHurtVal;
	}

	@SuppressWarnings("unchecked")
	private static <T extends ZhanFa> float calcKZZhanFa(HuiHe huihe, boolean calcPrimy, Map<String, Float> kongzhiMap, ZhanFa zf, T... zhanfa) {
		KongZhiZhanFa b = (KongZhiZhanFa)zf;
		float unHurtVal = 0.0f;
		//控制战法发动成功的概率
		float rate = CalcDoRate.getKongZhiRate(huihe,b);
		//每个人数的随机概率
		float evrate = 1.0f/b.getPersons().getPersons().length;
		for(int p:b.getPersons().getPersons()) {
			int distance = CalCDistance.calcDistance(b.getDistance(), b.getPosition());
			if(distance<=0) {
				continue;
			}else {
				p = Math.min(p, distance);
			}
			//不受伤害的概率
			float unHurt = evrate * p/1.0f/huihe.getWujiangCount();
			unHurt = unHurt>1 ? rate:rate*unHurt;
			//控制主的概率
			float kongzhiVal = unHurt * b.getDoneRate();
			float tmp = kongzhiVal * b.getKeep() * calcKongZhiAllHuiHe(huihe.getAllFeng(kongzhiVal),calcPrimy,zhanfa);
			kongzhiMap.put(b.getName(), kongzhiVal);
			unHurtVal += tmp;
		}
		return unHurtVal;
	}

	@SuppressWarnings("unchecked")
	public static <T extends ZhanFa> float calcKongZhiAllHuiHe(HuiHe huihe, boolean calcPrimy, T... zhanfa) {
		float sum = 0.0f;
		if(calcPrimy) {
			sum = calcCommHuiHe(huihe,zhanfa);
		}else {
			if(huihe.isHasZengYi()) {
				sum = calcExVal(huihe,zhanfa);
			}
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
			}else if(z.getT().equals(ZFType.BeiDong_GongJi)){
				shuaxinVal += z.getHarmRate();
				//被打后反击
				int distance = CalCDistance.calcfDistance(huihe.getWj().getDistance(),huihe.getWj().getPosition());
				float kongzhiVal = huihe.getFengGongji()>0?huihe.getFengGongji():0.0f;
				Conf.log("========封住攻击的概率：" + huihe.getFengGongji());
				shuaxinVal *= distance * (1.0f - kongzhiVal);
			}else if(z.getT().equals(ZFType.ZhiHui_Multiple_FaShu)){
				float newHVal = 0.0f;
				MultipleHarmZhanFa tmp = (MultipleHarmZhanFa)z;
				newHVal += (shuaxinVal + tmp.getHarmRate());
				if(huihe.getId()>=tmp.getSecondHId()) {
					newHVal += (shuaxinVal + tmp.getSecondHVal());
				}
				if(huihe.getId()>=tmp.getThreeHId()) {
					newHVal += (shuaxinVal + tmp.getThreeHVal());
				}
				shuaxinVal = newHVal;
			}else {
				shuaxinVal += z.getHarmRate();
			}
			
			float rate = huihe.getShuaxinVal()>0?CalcDoRate.getShuaXinRate(huihe, z):CalcDoRate.getCommRate(huihe, z);
			//TODO 考虑被控制效果  规避效果
			
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
				float rate = huihe.getShuaxinVal()>0?CalcDoRate.getShuaXinRate(huihe, zf):CalcDoRate.getCommRate(huihe, zf);
				float harmval = rate * executeJss * zf.getHarmVal() * huihe.getSolderRate(zf.getPosition(),zf.getDefense());
				Conf.log("===战法 " + zf.getName() + " 最终杀伤力：" + harmval);
				sum += harmval;
			}
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
