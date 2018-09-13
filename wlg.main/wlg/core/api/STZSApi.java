package wlg.core.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wlg.core.bean.wujiang.WuJiang;
import wlg.core.calc.CalcWJHarm;

/**
 *	 对外api
 * @author seven
 *
 */
public class STZSApi {

	private static STZSApi instance = new STZSApi();
	private STZSApi() {}
	public static STZSApi $() {
		return instance;
	}
	
	public Map<String,String> getWjs(){
		Map<String,String> wjs = new HashMap<String,String>();
		STZSConf.wjs.keySet().forEach(key->{
			wjs.put(key, key);
		});
		return wjs;
	}
	
	public Map<String,String> getZfs(){
		Map<String,String> zfs = new HashMap<String,String>();
		STZSConf.zfs.keySet().forEach(key->{
			zfs.put(key, key);
		});
		return zfs;
	}
	
	public STZSResult calcHarmVal(STZSParams p) {
		STZSResult res = new STZSResult();
		WuJiang daying = null,zhongjun = null,qianfeng = null;
		
		if(STZSConf.wjs.containsKey(p.getWj1())) {
			daying = STZSConf.wjs.get(p.getWj1()).clone();
			if(STZSConf.zfs.containsKey(p.getZf1())) {
				daying = daying.setSecondZhanFa(STZSConf.zfs.get(p.getZf1()));
			}
			if(STZSConf.zfs.containsKey(p.getZf2())) {
				daying = daying.setThreeZhanFa(STZSConf.zfs.get(p.getZf2()));
			}
		}
		
		if(STZSConf.wjs.containsKey(p.getWj2())) {
			zhongjun = STZSConf.wjs.get(p.getWj2()).clone();
			if(STZSConf.zfs.containsKey(p.getZf3())) {
				zhongjun = zhongjun.setSecondZhanFa(STZSConf.zfs.get(p.getZf3()));
			}
			if(STZSConf.zfs.containsKey(p.getZf4())) {
				zhongjun = zhongjun.setThreeZhanFa(STZSConf.zfs.get(p.getZf4()));
			}
		}
		
		if(STZSConf.wjs.containsKey(p.getWj3())) {
			qianfeng = STZSConf.wjs.get(p.getWj3()).clone();
			if(STZSConf.zfs.containsKey(p.getZf5())) {
				qianfeng = qianfeng.setSecondZhanFa(STZSConf.zfs.get(p.getZf5()));
			}
			if(STZSConf.zfs.containsKey(p.getZf4())) {
				qianfeng = qianfeng.setThreeZhanFa(STZSConf.zfs.get(p.getZf6()));
			}
		}
		List<WuJiang> wjs = new ArrayList<>();
		if(daying!=null) {
			wjs.add(daying);
		}
		if(zhongjun!=null) {
			wjs.add(zhongjun);
		}
		if(qianfeng!=null) {
			wjs.add(qianfeng);
		}
		if(wjs.size()>0) {
			WuJiang[] ws = wjs.toArray(new WuJiang[wjs.size()]);
			float val = CalcWJHarm.calcVal(ws);
			String desc = buildKey(ws);
			res.setDesc(desc);
			res.setHarmval(val);
		}
		return res;
	}
	
	private String buildKey(WuJiang... wjs) {
		StringBuilder key = new StringBuilder();
		for(WuJiang wj:wjs) {
			key.append(wj.toKey());
		}
		return key.toString();
	}
	
	public static void main(String[] args) {
		System.out.println("================武将================");
		Map<String,String> wjs = STZSApi.$().getWjs();
		wjs.forEach((k,v)->{
			System.out.println("key:" + k + " val:" + v);
		});
		System.out.println("================战法================");
		Map<String,String> zfs = STZSApi.$().getZfs();
		zfs.forEach((k,v)->{
			System.out.println("key:" + k + " val:" + v);
		});
		System.out.println("================计算================");
		STZSParams p = new STZSParams();
		p.setWj1("陆逊");
		p.setZf1("声东击西");
		p.setZf2("十面埋伏");
		p.setWj2("周瑜");
		STZSResult res = STZSApi.$().calcHarmVal(p);
		System.out.println(res.getDesc() + res.getHarmval());
		
		STZSParams p2 = new STZSParams();
		p2.setWj1("陆逊");
		p2.setZf1("声东击西");
		p2.setZf2("十面埋伏");
		p2.setWj2("周瑜");
		p2.setZf3("反计之策");
		STZSResult res2 = STZSApi.$().calcHarmVal(p2);
		System.out.println(res2.getDesc() + res2.getHarmval());
		
		STZSParams p1 = new STZSParams();
		p1.setWj1("陆逊");
		p1.setZf1("声东击西");
		p1.setZf2("十面埋伏");
		p1.setWj2("周瑜");
		STZSResult res1 = STZSApi.$().calcHarmVal(p1);
		System.out.println(res1.getDesc() + res1.getHarmval());
	}
}
