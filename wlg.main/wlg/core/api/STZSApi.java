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
			daying = STZSConf.wjs.get(p.getWj1());
			if(STZSConf.zfs.containsKey(p.getZf1())) {
				daying = daying.setSecondZhanFa(STZSConf.zfs.get(p.getZf1()));
			}
			if(STZSConf.zfs.containsKey(p.getZf2())) {
				daying = daying.setThreeZhanFa(STZSConf.zfs.get(p.getZf2()));
			}
		}
		
		if(STZSConf.wjs.containsKey(p.getWj2())) {
			zhongjun = STZSConf.wjs.get(p.getWj2());
			if(STZSConf.zfs.containsKey(p.getZf3())) {
				zhongjun = zhongjun.setSecondZhanFa(STZSConf.zfs.get(p.getZf3()));
			}
			if(STZSConf.zfs.containsKey(p.getZf4())) {
				zhongjun = zhongjun.setThreeZhanFa(STZSConf.zfs.get(p.getZf4()));
			}
		}
		
		if(STZSConf.wjs.containsKey(p.getWj3())) {
			qianfeng = STZSConf.wjs.get(p.getWj3());
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
			String desc = buildKey(ws);
			float val = CalcWJHarm.calcVal(ws);
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
		key.append("该组合伤害值:");
		return key.toString();
	}
	
	public static void main(String[] args) {
		Map<String,String> wjs = STZSApi.$().getWjs();
		wjs.forEach((k,v)->{
			System.out.println("key:" + k + " val:" + v);
		});
		
		Map<String,String> zfs = STZSApi.$().getZfs();
		zfs.forEach((k,v)->{
			System.out.println("key:" + k + " val:" + v);
		});
		
		STZSParams p = new STZSParams();
		p.setWj1("陆逊");
		p.setZf1("声东击西");
		p.setZf2("十面埋伏");
		STZSResult res = STZSApi.$().calcHarmVal(p);
		System.out.println(res.getDesc() + res.getHarmval());
	}
}
