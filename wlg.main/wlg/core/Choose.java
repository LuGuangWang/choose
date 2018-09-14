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
		WuJiang one,two,three;
		
		one = WList.luxun.reset();
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.luxun.setSecondZhanFa(ZList.shenmouyuanlv).setThreeZhanFa(ZList.chugesiqi);
		two = WList.zhouyv.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		float sum_1 = CalcWJHarm.calcVal(one,two);
		String key_1 = buildKey(one,two);
		result.put(sum_1, key_1);
		
		one = WList.luxun.setSecondZhanFa(ZList.shenmouyuanlv).setThreeZhanFa(ZList.chugesiqi);
		two = WList.zhouyv.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		three = WList.lvmeng.setSecondZhanFa(ZList.fanjizhice).setThreeZhanFa(ZList.jijiaozhishi);
		float sum = CalcWJHarm.calcVal(one,two,three);
		String key = buildKey(one,two,three);
		result.put(sum, key);
		
		one = WList.luxun.reset();
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.luxun.setSecondZhanFa(ZList.shenmouyuanlv).setThreeZhanFa(ZList.chugesiqi);
		two = WList.zhouyv.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		float sum_2 = CalcWJHarm.calcVal(one,two);
		String key_2 = buildKey(one,two);
		result.put(sum_2, key_2);
		
		one = WList.luxun.setSecondZhanFa(ZList.bugong).setThreeZhanFa(ZList.chugesiqi);
		two = WList.zhouyv.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		three= WList.lvmeng.setSecondZhanFa(ZList.fanjizhice).setThreeZhanFa(ZList.jijiaozhishi);
		float sum1 = CalcWJHarm.calcVal(one,two,three);
		String key1 = buildKey(one,two,three);
		result.put(sum1, key1);
		
		one = WList.luxun.setSecondZhanFa(ZList.bugong).setThreeZhanFa(ZList.chugesiqi);
		two = WList.zhouyv.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		three= WList.zhaoyun.setSecondZhanFa(ZList.huima).setThreeZhanFa(ZList.jijiaozhishi);
		float sum2 = CalcWJHarm.calcVal(one,two,three);
		String key2 = buildKey(one,two,three);
		result.put(sum2, key2);
		
		one = WList.luxun.setSecondZhanFa(ZList.bugong).setThreeZhanFa(ZList.shimianmaifu);
		two = WList.zhouyv.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		three= WList.lvmeng.setSecondZhanFa(ZList.fanjizhice).setThreeZhanFa(ZList.jijiaozhishi);
		float sum3 = CalcWJHarm.calcVal(one,two,three);
		String key3 = buildKey(one,two,three);
		result.put(sum3, key3);
		
		one = WList.huangyueying.setSecondZhanFa(ZList.bugong).setThreeZhanFa(ZList.chugesiqi);
		two = WList.zhouyv.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		three= WList.zhaoyun.setSecondZhanFa(ZList.huima).setThreeZhanFa(ZList.jijiaozhishi);
		float sum4 = CalcWJHarm.calcVal(one,two,three);
		String key4 = buildKey(one,two,three);
		result.put(sum4, key4);
		
		one = WList.xunyv.setSecondZhanFa(ZList.shenmouyuanlv).setThreeZhanFa(ZList.chugesiqi);
		two = WList.zhangchunhua.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		three= WList.caocao.setSecondZhanFa(ZList.fanjizhice).setThreeZhanFa(ZList.jijiaozhishi);
		float sum5 = CalcWJHarm.calcVal(one,two,three);
		String key5 = buildKey(one,two,three);
		result.put(sum5, key5);
		
		return result;
	}
	
	/**
	 * 计算单个武将
	 * @return
	 */
	public Map<Float, String> calcDanGeWuJiangVal() {
		TreeMap<Float, String> result = new TreeMap<>();
		WuJiang one;
		
		one = WList.huangyueying.reset();
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.huangyueying.setSecondZhanFa(ZList.shimianmaifu);
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.luxun.reset();
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.zhaoyun.setSecondZhanFa(ZList.huima).setThreeZhanFa(ZList.hunshuimoyv);
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.luxun.reset();
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.luxun.setSecondZhanFa(ZList.chugesiqi).setThreeZhanFa(ZList.shenmouyuanlv);
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.luxun.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.shengdongjixi);
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.luxun.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.chugesiqi);
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.luxun.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.shenmouyuanlv);
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.luxun.setSecondZhanFa(ZList.chugesiqi).setThreeZhanFa(ZList.bugong);
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.luxun.setSecondZhanFa(ZList.shengdongjixi).setThreeZhanFa(ZList.bugong);
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.luxun.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.bugong);
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.luxun.setSecondZhanFa(ZList.shenmouyuanlv).setThreeZhanFa(ZList.bugong);
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.luxun.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.hunshuimoyv);
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.zhouyv.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.lvmeng.setSecondZhanFa(ZList.fanjizhice).setThreeZhanFa(ZList.jijiaozhishi);
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.xunyv.setSecondZhanFa(ZList.fanjizhice).setThreeZhanFa(ZList.jijiaozhishi);
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.zhangchunhua.setSecondZhanFa(ZList.fanjizhice).setThreeZhanFa(ZList.jijiaozhishi);
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.caocao.setSecondZhanFa(ZList.fanjizhice).setThreeZhanFa(ZList.huima);
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
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
