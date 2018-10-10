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
import wlg.core.bean.wujiang.WuJiang;
import wlg.core.bean.zhanfa.BaoZouZhanFa;
import wlg.core.bean.zhanfa.BiYueZhanFa;
import wlg.core.bean.zhanfa.ConflictList;
import wlg.core.bean.zhanfa.FanJiZhiCeZhanFa;
import wlg.core.bean.zhanfa.GongJiZhanFa;
import wlg.core.bean.zhanfa.JiaShangZhanFa;
import wlg.core.bean.zhanfa.KongZhiAndHarmZhanFa;
import wlg.core.bean.zhanfa.KongZhiZhanFa;
import wlg.core.bean.zhanfa.MuYiFuMeng;
import wlg.core.bean.zhanfa.MultipleHarmZhanFa;
import wlg.core.bean.zhanfa.QiZuoGuiMou;
import wlg.core.bean.zhanfa.QiangShiZhanFa;
import wlg.core.bean.zhanfa.ShiJiZhanFa;
import wlg.core.bean.zhanfa.ShuiYanQiJun;
import wlg.core.bean.zhanfa.UpVal;
import wlg.core.bean.zhanfa.ZFType;
import wlg.core.bean.zhanfa.ZhanBiZhanFa;
import wlg.core.bean.zhanfa.ZhanFa;
import wlg.core.bean.zhanfa.ZhuiJiZhanFa;

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
			}else if(zf.getT().equals(ZFType.ZhiHui_KongZhiGongJi)) {
				unHurtVal = calcKZGongJi(huihe, calcPrimy, kongzhiMap, zf,allZfs);
			}else if(zf.getT().equals(ZFType.ZhuDong_JiaShuXing_KongZhi)
					|| zf.getT().equals(ZFType.ZhuDong_Multiple_KongZhi)) {
				unHurtVal = calcJiaShuXing(huihe, calcPrimy, kongzhiMap, zf,allZfs);
			}else if(zf.getT().equals(ZFType.ZhuDong_BaoZou)) {
				unHurtVal = calcBaoZou(huihe, calcPrimy, kongzhiMap, zf,allZfs);
			}else if(zf.getT().equals(ZFType.ZhiHui_JiaFaShu_JianShang_MianYi)) {
				unHurtVal = calcShiJi(huihe, calcPrimy, kongzhiMap, zf,allZfs);
			}else if(zf.getT().equals(ZFType.ZhuDong_BaoZou_jianFangYu)
					|| zf.getT().equals(ZFType.ZhuDong_FaShu_BaoZou)) {
				unHurtVal = calcBiyue(huihe, calcPrimy, kongzhiMap, zf,allZfs);
			}else if(zf.getT().equals(ZFType.ZhuiJi_GongJi_KongZhiGongJi)) {
				unHurtVal = calcZhuiJi(huihe, calcPrimy, kongzhiMap, zf,allZfs);
			}else if(zf.getT().equals(ZFType.ZhuDong_JiaShuXing)) {
				unHurtVal = calcJianShuXing(huihe, calcPrimy, kongzhiMap, zf,allZfs);
			}else if(zf.getT().equals(ZFType.ZhiHui_GuiBi_JianShang )) {
				unHurtVal = calcMuYiFuMeng(huihe,calcPrimy,kongzhiMap,zf,allZfs);
			}else if(zf.getT().equals(ZFType.ZhuDong_FaShu_jianGongJi)) {
				unHurtVal = calcJianGongJi(huihe,calcPrimy,kongzhiMap,zf,allZfs);
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
	
	private static float calcJianGongJi(HuiHe huihe, boolean calcPrimy, Map<String, Float> kongzhiMap, ZhanFa zf,
			ZhanFa... allZfs) {
		ShuiYanQiJun b = (ShuiYanQiJun)zf;
		float unHurtVal = 0.0f;
		//控制战法发动成功的概率
		float rate = CalcDoRate.getKongZhiRate(huihe,b);
		//不能发动战法时，直接返回
		if(rate<=0) {
			return 0;
		}
		//每个人数的随机概率
		float evrate = 1.0f/b.getPersons().getPersons().length;
		for(int p:b.getPersons().getPersons()) {
			int distance = CalCDistance.calcDistance(b.getDistance(), b.getPosition());
			if(distance<=0) {
				continue;
			} else {
				p = Math.min(p, distance);
			}
			float unHurt = evrate * p/1.0f/Conf.WuJiang_Count;
			unHurt = unHurt>1 ? rate:rate*unHurt;
			//控制主的概率
			float kongzhiVal = unHurt * b.getDoneRate();
			//受谋略属性影响
			float fengVal = b.getJianGongJiVal() + Math.round(1.0f * b.getStrategy() / Conf.shuxing_val_suoxiao);
			float tmp =  kongzhiVal * calcKongZhiAllHuiHe(huihe.getFengGongji(fengVal, true),calcPrimy,allZfs);
			kongzhiMap.put(b.getName(), kongzhiVal);
			unHurtVal += tmp;
		}
		return unHurtVal;
	}

	private static float calcMuYiFuMeng(HuiHe huihe, boolean calcPrimy, Map<String, Float> kongzhiMap, ZhanFa zf,
			ZhanFa... allZfs) {
		MuYiFuMeng b = (MuYiFuMeng)zf;
		float unHurtVal = 0.0f;
		//控制战法发动成功的概率
		float rate = CalcDoRate.getKongZhiRate(huihe,b);
		//不能发动战法时，直接返回
		if(rate<=0) {
			return 0;
		}
		//每个人数的随机概率
		float evrate = 1.0f/b.getPersons().getPersons().length;
		for(int p:b.getPersons().getPersons()) {
			int distance = CalCDistance.calcDistance(b.getDistance(), b.getPosition());
			if(distance<=0) {
				continue;
			} else {
				p = Math.min(p, distance);
			}
			float unHurt = evrate * p/1.0f/Conf.WuJiang_Count;
			unHurt = unHurt>1 ? rate:rate*unHurt;
			//控制主的概率
			float kongzhiVal = unHurt * b.getDoneRate() * b.getJiangshangRate();
			float tmp = 0.0f;
			if(huihe.getId()==1) {
				tmp =  kongzhiVal * calcKongZhiAllHuiHe(huihe.getGuiBi(0.5f,kongzhiVal * b.getJianshangVal()),calcPrimy,allZfs);
			} else {
				tmp =  kongzhiVal * calcKongZhiAllHuiHe(huihe.getAllFeng(kongzhiVal * b.getJianshangVal()),calcPrimy,allZfs);
			}
			kongzhiMap.put(b.getName(), kongzhiVal);
			unHurtVal += tmp;
		}
		return unHurtVal;
	}

	private static float calcJianShuXing(HuiHe huihe, boolean calcPrimy, Map<String, Float> kongzhiMap, ZhanFa zf,
			ZhanFa... allZfs) {
		float unHurtVal = 0.0f;
		//控制战法发动成功的概率
		float rate = CalcDoRate.getKongZhiRate(huihe,zf);
		//不能发动战法时，直接返回
		if(rate<=0) {
			return 0;
		}
		//每个人数的随机概率
		float evrate = 1.0f/zf.getPersons().getPersons().length;
		for(int p:zf.getPersons().getPersons()) {
			int distance = CalCDistance.calcDistance(zf.getDistance(), zf.getPosition());
			if(distance<=0) {
				continue;
			}else {
				p = Math.min(p, distance);
			}
			//不受伤害的概率
			float unHurt = evrate * p/1.0f/Conf.WuJiang_Count;
			unHurt = unHurt>1 ? rate:rate*unHurt;
			//控制主的概率
			float kongzhiVal = unHurt * zf.getDoneRate();
			
			float tmp = kongzhiVal * calcKongZhiAllHuiHe(huihe.getAllFeng(kongzhiVal * huihe.getUpQuanShuXing(),true),calcPrimy,allZfs);
			kongzhiMap.put(zf.getName(), kongzhiVal);
			unHurtVal += tmp;
		}
		return unHurtVal;
	}

	private static float calcZhuiJi(HuiHe huihe, boolean calcPrimy, Map<String, Float> kongzhiMap, ZhanFa zf,
			ZhanFa... allZfs) {
		ZhuiJiZhanFa b = (ZhuiJiZhanFa)zf;
		float unHurtVal = 0.0f;
		//控制战法发动成功的概率
		float rate = CalcDoRate.getKongZhiRate(huihe,b);
		//不能发动战法时，直接返回
		if(rate<=0) {
			return 0;
		}
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
			float unHurt = evrate * p/1.0f/Conf.WuJiang_Count;
			unHurt = unHurt>1 ? rate:rate*unHurt;
			//控制主的概率
			float kongzhiVal = unHurt * b.getDoneRate();
			
			if(huihe.getWj().getZishenlianjiVal()>0) {
				kongzhiVal *= huihe.getWj().getZishenlianjiVal();
			}
			
			kongzhiVal = kongzhiVal>1?1:kongzhiVal;
			
			float tmp = kongzhiVal * calcKongZhiAllHuiHe(huihe.getFengGongji(kongzhiVal,p),calcPrimy,allZfs);
			kongzhiMap.put(b.getName(), kongzhiVal);
			unHurtVal += tmp;
		}
		return unHurtVal;
	}

	private static float calcBiyue(HuiHe huihe, boolean calcPrimy, Map<String, Float> kongzhiMap, ZhanFa zf,
			ZhanFa... allZfs) {
		BiYueZhanFa b = (BiYueZhanFa)zf;
		float unHurtVal = 0.0f;
		//控制战法发动成功的概率
		float rate = CalcDoRate.getKongZhiRate(huihe,b);
		//不能发动战法时，直接返回
		if(rate<=0) {
			return 0;
		}
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
			float unHurt = evrate * p/1.0f/Conf.WuJiang_Count;
			unHurt = unHurt>1 ? rate:rate*unHurt;
			//控制主的概率
			float kongzhiVal = unHurt * b.getDoneRate();
			
			float fengAll = kongzhiVal * Conf.baozou_rate * ConflictList.$().baozouChongTuRate();
			
			float tmp = kongzhiVal * b.getKeephuihe() * calcKongZhiAllHuiHe(huihe.getAllFeng(fengAll),calcPrimy,allZfs);
			kongzhiMap.put(b.getName(), kongzhiVal);
			unHurtVal += tmp;
		}
		return unHurtVal;
	}

	private static float calcShiJi(HuiHe huihe, boolean calcPrimy, Map<String, Float> kongzhiMap, ZhanFa zf,
			ZhanFa... allZfs) {
		ShiJiZhanFa b = (ShiJiZhanFa)zf;
		float unHurtVal = 0.0f;
		//控制战法发动成功的概率
		float rate = CalcDoRate.getKongZhiRate(huihe,b);
		//不能发动战法时，直接返回
		if(rate<=0) {
			return 0;
		}
		//每个人数的随机概率
		int p = 1;
		//不受伤害的概率
		float unHurt = p/1.0f/Conf.WuJiang_Count;
		unHurt = unHurt>1 ? rate:rate*unHurt;
		//控制主的概率
		float kongzhiVal = unHurt * b.getDoneRate() * b.getSpeedVal();
		//始计 0.5概率生效在战法上
		float fengAll = 0.0f;
		if(!ConflictList.$().isCelueJianShangchongtu()) {
			fengAll = kongzhiVal * 0.5f * b.getDownVal();
		}
		float tmp = kongzhiVal * calcKongZhiAllHuiHe(huihe.getAllFeng(fengAll),calcPrimy,allZfs);
		kongzhiMap.put(b.getName(), kongzhiVal);
		unHurtVal += tmp;
		
		return unHurtVal;
	}

	private static float calcBaoZou(HuiHe huihe, boolean calcPrimy, Map<String, Float> kongzhiMap, ZhanFa zf,
			ZhanFa... allZfs) {
		BaoZouZhanFa b = (BaoZouZhanFa)zf;
		float unHurtVal = 0.0f;
		//控制战法发动成功的概率
		float rate = CalcDoRate.getKongZhiRate(huihe,b);
		//不能发动战法时，直接返回
		if(rate<=0) {
			return 0;
		}
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
			float unHurt = evrate * p/1.0f/Conf.WuJiang_Count;
			unHurt = unHurt>1 ? rate:rate*unHurt;
			//控制主的概率
			float kongzhiVal = unHurt * b.getDoneRate();
			
			float fengAll = kongzhiVal * Conf.baozou_rate * ConflictList.$().baozouChongTuRate();
			
			float tmp = kongzhiVal * b.getKeephuihe() * calcKongZhiAllHuiHe(huihe.getAllFeng(fengAll),calcPrimy,allZfs);
			kongzhiMap.put(b.getName(), kongzhiVal);
			unHurtVal += tmp;
		}
		return unHurtVal;
	}

	private static float calcJiaShuXing(HuiHe huihe, boolean calcPrimy, Map<String, Float> kongzhiMap, 
			ZhanFa zf,ZhanFa... zhanfa) {
		QiZuoGuiMou b = (QiZuoGuiMou)zf;
		float unHurtVal = 0.0f;
		//控制战法发动成功的概率
		float rate = CalcDoRate.getKongZhiRate(huihe,b);
		//不能发动战法时，直接返回
		if(rate<=0) {
			return 0;
		}
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
			float unHurt = evrate * p/1.0f/Conf.WuJiang_Count;
			unHurt = unHurt>1 ? rate:rate*unHurt;
			//控制主的概率
			float kongzhiVal = unHurt * b.getDoneRate() * b.getKongzhiRate();
			float fenggongji = kongzhiVal * 0.4f;
			float fengfashu = kongzhiVal * 0.4f;
			
			float tmp = kongzhiVal * b.getKeephuihe() * calcKongZhiAllHuiHe(huihe.getAllFeng(fengfashu,fenggongji),calcPrimy,zhanfa);
			kongzhiMap.put(b.getName(), kongzhiVal);
			unHurtVal += tmp;
		}
		return unHurtVal;
	}

	@SuppressWarnings("unchecked")
	private static <T extends ZhanFa> float calcQiangShi(HuiHe huihe, boolean calcPrimy, Map<String, Float> kongzhiMap,
			ZhanFa zf, T... zhanfa) {
		float unHurtVal = 0.0f;
		
		QiangShiZhanFa b = (QiangShiZhanFa)zf;
		//控制战法发动成功的概率
		float rate = CalcDoRate.getKongZhiRate(huihe,b);
		//不能发动战法时，直接返回
		if(rate<=0) {
			return 0;
		}
		//每个人数的随机概率
		float evrate = 1.0f/b.getPersons().getPersons().length;
		for(int p:b.getPersons().getPersons()) {
			int distance = CalCDistance.calcDistance(b.getDistance(), b.getPosition());
			if(distance<=0) {
				continue;
			}else {
				p = Math.min(p, distance);
			}
			float unHurt = evrate * p/1.0f/Conf.WuJiang_Count;
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
		//不能发动战法时，直接返回
		if(rate<=0) {
			return 0;
		}
		//每个人数的随机概率
		float evrate = 1.0f/b.getPersons().getPersons().length;
		for(int p:b.getPersons().getPersons()) {
			int distance = CalCDistance.calcDistance(b.getDistance(), b.getPosition());
			if(distance<=0) {
				continue;
			}else {
				p = Math.min(p, distance);
			}
			float unHurt = evrate * p/1.0f/Conf.WuJiang_Count;
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
		//不能发动战法时，直接返回
		if(rate<=0) {
			return 0;
		}
		//不受伤害的概率
		float unHurt = p/1.0f/Conf.WuJiang_Count;
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
		//不能发动战法时，直接返回
		if(rate<=0) {
			return 0;
		}
		//每个人数的随机概率
		float evrate = 1.0f/b.getPersons().getPersons().length;
		for(int p:b.getPersons().getPersons()) {
			int distance = CalCDistance.calcDistance(b.getDistance(), b.getPosition());
			if(distance<=0) {
				continue;
			}else {
				p = Math.min(p, distance);
			}
			float unHurt = evrate * p/1.0f/Conf.WuJiang_Count;
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
		//不能发动战法时，直接返回
		if(rate<=0) {
			return 0;
		}
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
			float unHurt = evrate * p/1.0f/Conf.WuJiang_Count;
			unHurt = unHurt>1 ? rate:rate*unHurt;
			if(huihe.getId()>b.getKeephuihe()) {
				unHurt = 0;
				return 0;
			}else {
				//更新武将免疫攻击的控制力
				huihe.getWj().setMianyiGBVal(b.getWushiguibi());
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
	private static <T extends ZhanFa> float calcKZGongJi(HuiHe huihe, boolean calcPrimy, Map<String, Float> kongzhiMap, ZhanFa zf, T... zhanfa) {
		ZhanBiZhanFa b = (ZhanBiZhanFa)zf;
		float unHurtVal = 0.0f;
		//控制战法发动成功的概率
		float rate = CalcDoRate.getKongZhiRate(huihe,b);
		//不能发动战法时，直接返回
		if(rate<=0) {
			return 0;
		}
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
			float unHurt = evrate * p/1.0f/Conf.WuJiang_Count;
			unHurt = unHurt>1 ? rate:rate*unHurt;
			//控制主的概率
			float kongzhiVal = unHurt * b.getDoneRate()*b.getKonzhiVal();
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
		//不能发动战法时，直接返回
		if(rate<=0) {
			return 0;
		}
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
			float unHurt = evrate * p/1.0f/Conf.WuJiang_Count;
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
			T zf = zhanfa[i];
			float shuaxinVal = 0.0f;
			if(CheckUtil.isStrategy(zf)) {
				//只对当前武将的战法生效
				if( huihe.getShuaxinPos() == zf.getPosition()) {
					shuaxinVal = huihe.getShuaxinVal() * huihe.getId();
				}
				//大营战法加成  法术加成概率为0.75
				if(huihe.getWujiangCount()==3 && huihe.getWj().getFinalp()== Conf.daying) {
					shuaxinVal += huihe.getUpFaShaShangHaiVal() * 0.75f;
				}else if(huihe.getWujiangCount()==2 && huihe.getWj().getFinalp()== Conf.zhongjun) {
					shuaxinVal += huihe.getUpFaShaShangHaiVal() * 0.75f;
				}else if(huihe.getWujiangCount()==1 && huihe.getWj().getFinalp()== Conf.qianfeng) {
					shuaxinVal += huihe.getUpFaShaShangHaiVal() * 0.75f;
				}
			}
			
			float rate = CalcDoRate.getCommRate(huihe, zf);
			
			if(zf.getT().equals(ZFType.ZhiHui_KongZhiGongJi_FaShuShangHai)) {
				KongZhiAndHarmZhanFa tmp = (KongZhiAndHarmZhanFa) zf;
				//此控制效果结束后，才造成一次伤害
				if(tmp.getKeephuihe()+1 == huihe.getId()) {
					shuaxinVal += tmp.getExHarmVal();
				}else {
					shuaxinVal = 0.0f;
				}
			}else if(CheckUtil.isJiaShang(zf)){
				JiaShangZhanFa tmp = (JiaShangZhanFa)zf;
				if(huihe.getId()<=tmp.getKeephuihe()) {
					jss.add(tmp);
					continue;
				} else {//	方便查看日志
					shuaxinVal = 0.0f;
				}
			}else if(zf.getT().equals(ZFType.BeiDong_GongJi)){
				shuaxinVal += zf.getHarmRate();
				//被打后反击
				int distance = CalCDistance.calcfDistance(huihe.getWj().getDistance(),huihe.getWj().getPosition());
				float kongzhiVal = huihe.getFengGongji()>0?huihe.getFengGongji():0.0f;
				Conf.log("========封住攻击的概率：" + huihe.getFengGongji());
				shuaxinVal *= distance * (1.0f - kongzhiVal);
			}else if(zf.getT().equals(ZFType.ZhiHui_Multiple_FaShu)){
				float newHVal = 0.0f;
				MultipleHarmZhanFa tmp = (MultipleHarmZhanFa)zf;
				newHVal += (shuaxinVal + tmp.getHarmRate());
				if(huihe.getId()>=tmp.getSecondHId()) {
					newHVal += (shuaxinVal + tmp.getSecondHVal());
				}
				if(huihe.getId()>=tmp.getThreeHId()) {
					newHVal += (shuaxinVal + tmp.getThreeHVal());
				}
				shuaxinVal = newHVal;
			}else if(CheckUtil.isLianJi(zf)) {
				//TODO 注意 添加其它连击战法时，注意攻击距离
				addGongJiZfExVal(huihe, zf);
			}else if(CheckUtil.isZhuiJi(zf)) {
				shuaxinVal += zf.getHarmRate() * huihe.getLianjiVal();
			}else if(huihe.getSkipReadyVal()>0 
					&& zf.getPosition()==huihe.getSkipReadyPos()
					&& CheckUtil.isZiDaiReady(zf)){//胜兵求战
				rate *= 2.0f;
				shuaxinVal += zf.getHarmRate();
			}else {
				shuaxinVal += zf.getHarmRate();
			}
			
			//TODO 考虑被控制效果  规避效果 不可恢复
			float shibingVal = huihe.getSolderRate(zf);
			//属性加成值
			UpVal upVal = buildUpVal(huihe,zf);
			
			float harmval = rate * zf.getHarmVal(shuaxinVal,upVal) * shibingVal;
			
			//有伤害 才能触发加伤战法
			if(harmval>0) {
				executeJss++;
				//行兵之极  中军首次加伤害				
				if(huihe.isIsxingbing() && huihe.getWj().getFinalp()==Conf.zhongjun && executeJss==1) {
					harmval *= (1.0f + huihe.getZhongjunUpVal());
				}
				//胜兵求战 首次加伤害
				if(huihe.getShengbingUpVal()>0 && zf.getPosition()==huihe.getSkipReadyPos() && executeJss==1) {
					harmval *= (huihe.getShengbingUpVal() * 0.5f + 1.0f);
				}
				//降低防御属性增加的伤害值
				harmval += huihe.getDownFangYuVal() * Conf.fg_rate;
				//免疫规避
				if(!CheckUtil.isMianYiGuiBi(zf)) {
					harmval *= huihe.getWj().getMianyiGBVal();
				}
			}
			Conf.log("===战法 " + zf.getName() + " 最终杀伤力：" + harmval);
			sum += harmval;
		}
		
		//计算加伤战法  TODO 以后不要使用这种写法
		if(jss.size()>0) {
			for(JiaShangZhanFa zf:jss) {
				float rate = CalcDoRate.getCommRate(huihe, zf);
				
				//属性加成值
				UpVal upVal = buildUpVal(huihe,zf);
				
				float harmval = rate * executeJss * zf.getHarmVal(zf.getUpVal(),upVal) * huihe.getSolderRate(zf);
				//降低防御属性增加的伤害值
				harmval += huihe.getDownFangYuVal() * Conf.fg_rate;
				//免疫规避
				if(!CheckUtil.isMianYiGuiBi(zf)) {
					harmval *= huihe.getWj().getMianyiGBVal();
				}
				Conf.log("===战法 " + zf.getName() + " 最终杀伤力：" + harmval);
				sum += harmval;
			}
		}
		return sum;
	}

	private static UpVal buildUpVal(HuiHe huihe, ZhanFa zf) {
		float addStrategyVal = 1.0f;//策略属性加成比
		float addQuanShuXingVal = 0.0f;//策略属性加成值
		
		UpVal upVal = new UpVal();
		
		if(huihe.getWj().getPosition()==zf.getPosition()) {
			//增加自身谋略属性点
			addStrategyVal = huihe.getUpFaShuVal();
			addQuanShuXingVal = huihe.getUpQuanShuXing();
		}else {
			int otherCount = huihe.getWujiangCount()-1;
			otherCount = otherCount<=0?1:otherCount;
			//奇佐鬼谋 
			float upval = huihe.getUpFaShuVal()-1;
			upval = 1.0f/otherCount * upval + 1;
			addStrategyVal = upval;
			//黄天余音
			addQuanShuXingVal = 1.0f/otherCount * huihe.getUpQuanShuXing();
		}
		//行兵之极
		if(huihe.isIsxingbing()) {
			if(huihe.getWj().getFinalp()==Conf.daying) {//行兵之极 大营
				boolean beCan = CheckUtil.isZhuiJi(zf) || CheckUtil.isZiDaiZHuDong(zf);
				if(huihe.getWj().getZiDaiZFName().equals(zf.getName()) && beCan) {
					Conf.log("=======战法" + zf.getName() + "发动概率提升:" + huihe.getDayingUpZFVal());
					upVal.setDayingUpZFVal(huihe.getDayingUpZFVal());
				}
			}
		}
		
		upVal.setAddStrategyVal(addStrategyVal);
		upVal.setAddQuanShuXingVal(addQuanShuXingVal);
		return upVal;
	}

	private static <T extends ZhanFa> void addGongJiZfExVal(HuiHe huihe, T z) {
		GongJiZhanFa gjzf = (GongJiZhanFa)z;
		
		float attack = 0.0f;
		int wjCount = 0;
		
		for(WuJiang wj:huihe.getWujiangs()) {
			if(!wj.getName().equals(huihe.getWj().getName())) {
				attack += wj.getAttack();
				wjCount ++;
			}
		}
		if(wjCount>=1) {
			float otherAttackVal = attack/wjCount;
			Conf.log("===========战法"+ gjzf.getName() + "友军平均攻击力："+otherAttackVal);
			gjzf.setOtherAttackVal(otherAttackVal);
		}else {
			gjzf.setOtherAttackVal(0.0f);
		}
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
					float zengyiRate = CalcDoRate.getCommRate(huihe, b);
					float shuaxinRate = huihe.getShuaxinVal() * huihe.getId();
					shuaxinRate += b.getExHarmVal();
					for(int j=0;j<zhanfa.length;j++) {
						float exharmVal = 0.0f;
						ZhanFa zf = zhanfa[j];
						if(j!= i) {
							float rate = CalcDoRate.getCommRate(huihe, zf);
			
							//胜兵求战
							if(huihe.getSkipReadyVal()>0  
									&& huihe.getSkipReadyPos() == b.getPosition() 
									&& CheckUtil.isZiDaiReady(b)) {
								rate *= zengyiRate * 2.0f;
							}else {
								rate *= zengyiRate;
							}
							//属性加成值
							UpVal upVal = buildUpVal(huihe,zf);
							
							if(zf.getT().equals(ZFType.ZhiHui_KongZhiGongJi_FaShuShangHai)) {
								KongZhiAndHarmZhanFa tmp = (KongZhiAndHarmZhanFa) zf;
								if(tmp.getKeephuihe()+1 == huihe.getId()) {
									exharmVal = rate * b.getExVal(tmp,shuaxinRate,upVal) * huihe.getSolderRate(b);
								}
							}else {
								exharmVal = rate * b.getExVal(zf,shuaxinRate,upVal) * huihe.getSolderRate(b);
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
