package wlg.core;

import java.util.Map;
import java.util.TreeMap;

import wlg.core.bean.wujiang.WList;
import wlg.core.bean.wujiang.WuJiang;
import wlg.core.bean.zhanfa.ZList;
import wlg.core.calc.CalcWJHarm;

public class Choose {
	
	
	public static void main(String[] args) {
		System.out.println("战法组合的伤害值越高,代表越好:");
		Choose c = new Choose();
		
		Map<Float, String> results = c.calcWuJiangsVal();
		((TreeMap<Float, String>) results).descendingMap().forEach((k,v)->{
			System.out.println(v + k);
		});
		
		System.out.println("==================================================");
		
		Map<Float, String> result = c.calcDanGeWuJiangVal();
		((TreeMap<Float, String>) result).descendingMap().forEach((k,v)->{
			System.out.println(v + k);
		});
	}
	/**
	 * 计算武将组合
	 * @return
	 */
	public Map<Float, String> calcWuJiangsVal() {
		TreeMap<Float, String> result = new TreeMap<>();
//		WuJiang one,two,three;
//		
//		one = WList.luxun.setSecondZhanFa(ZList.shenmouyuanlv).setThreeZhanFa(ZList.chugesiqi);
//		two = WList.zhouyv.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
//		three = WList.lvmeng.setSecondZhanFa(ZList.fanjizhice).setThreeZhanFa(ZList.jijiaozhishi);
//		float sum = CalcWJHarm.calcVal(one,two,three);
//		String key = buildKey(WList.luxun,WList.zhouyv,WList.lvmeng);
//		result.put(sum, key);
//		
//		one = WList.luxun.setSecondZhanFa(ZList.bugong).setThreeZhanFa(ZList.chugesiqi);
//		two = WList.zhouyv.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
//		three= WList.lvmeng.setSecondZhanFa(ZList.fanjizhice).setThreeZhanFa(ZList.jijiaozhishi);
//		float sum1 = CalcWJHarm.calcVal(one,two,three);
//		String key1 = buildKey(WList.luxun,WList.zhouyv,WList.lvmeng);
//		result.put(sum1, key1);
		
		return result;
	}
	
	/**
	 * 计算单个武将
	 * @return
	 */
	public Map<Float, String> calcDanGeWuJiangVal() {
		TreeMap<Float, String> result = new TreeMap<>();
		WuJiang one;
		one = WList.zhaoyun.setSecondZhanFa(ZList.shimianmaifu);
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
//		one = WList.luxun.setSecondZhanFa(ZList.chugesiqi).setThreeZhanFa(ZList.shenmouyuanlv);
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.luxun.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.shengdongjixi);
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.luxun.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.chugesiqi);
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.luxun.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.shenmouyuanlv);
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.luxun.setSecondZhanFa(ZList.chugesiqi).setThreeZhanFa(ZList.bugong);
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.luxun.setSecondZhanFa(ZList.shengdongjixi).setThreeZhanFa(ZList.bugong);
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.luxun.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.bugong);
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.luxun.setSecondZhanFa(ZList.shenmouyuanlv).setThreeZhanFa(ZList.bugong);
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.luxun.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.hunshuimoyv);
//		result.put(CalcWJHarm.calcVal(one), one.toString());
		
//		one = WList.zhouyv.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.lvmeng.setSecondZhanFa(ZList.fanjizhice).setThreeZhanFa(ZList.jijiaozhishi);
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
		return result;
	}
	

	protected static String buildKey(WuJiang... wjs) {
		StringBuilder key = new StringBuilder();
		for(WuJiang wj:wjs) {
			key.append(wj.toKey());
		}
		key.append("该组合伤害值:");
		return key.toString();
	}
	
}
