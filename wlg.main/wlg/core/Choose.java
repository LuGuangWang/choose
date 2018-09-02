package wlg.core;

import java.util.Map;
import java.util.TreeMap;

import wlg.core.bean.wujiang.WList;
import wlg.core.bean.zhanfa.ZList;
import wlg.core.calc.CalcWJHarm;

public class Choose {
	
	
	public static void main(String[] args) {
//		WList.luxun.setSecondZhanFa(ZList.shenmouyuanlv).setThreeZhanFa(ZList.chugesiqi);
//		WList.zhouyv.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
//		float sum = CalcWJHarm.calcVal(WList.luxun,WList.zhouyv,WList.lvmeng);
//		String key = WList.zhouyv.toKey() + WList.luxun.toKey() + WList.lvmeng.toKey() + "伤害值:";
//		System.out.println(key + sum);
//		
//		System.out.println("==================================================");
		
		Choose c = new Choose();
		Map<Float, String> result = c.calcDanGeWuJiangVal();
		System.out.println("单个武将战法组合的伤害值越高,代表越好:");
		((TreeMap<Float, String>) result).descendingMap().forEach((k,v)->{
			System.out.println(v + k);
		});
	}
	
	/**
	 * 计算单个武将
	 * @return
	 */
	public Map<Float, String> calcDanGeWuJiangVal() {
		TreeMap<Float, String> result = new TreeMap<>();
//		WList.luxun.setSecondZhanFa(ZList.chugesiqi).setThreeZhanFa(ZList.shenmouyuanlv);
//		result.put(CalcWJHarm.calcVal(WList.luxun), WList.luxun.toString());
//		
//		WList.luxun.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.shengdongjixi);
//		result.put(CalcWJHarm.calcVal(WList.luxun), WList.luxun.toString());
//		
//		WList.luxun.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.chugesiqi);
//		result.put(CalcWJHarm.calcVal(WList.luxun), WList.luxun.toString());
//		
//		WList.luxun.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.shenmouyuanlv);
//		result.put(CalcWJHarm.calcVal(WList.luxun), WList.luxun.toString());
//		
//		WList.luxun.setSecondZhanFa(ZList.chugesiqi).setThreeZhanFa(ZList.bugong);
//		result.put(CalcWJHarm.calcVal(WList.luxun), WList.luxun.toString());
//		
//		WList.luxun.setSecondZhanFa(ZList.shengdongjixi).setThreeZhanFa(ZList.bugong);
//		result.put(CalcWJHarm.calcVal(WList.luxun), WList.luxun.toString());
//		
//		WList.luxun.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.bugong);
//		result.put(CalcWJHarm.calcVal(WList.luxun), WList.luxun.toString());
//		
//		WList.luxun.setSecondZhanFa(ZList.shenmouyuanlv).setThreeZhanFa(ZList.bugong);
//		result.put(CalcWJHarm.calcVal(WList.luxun), WList.luxun.toString());
//		
//		WList.luxun.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.hunshuimoyv);
//		result.put(CalcWJHarm.calcVal(WList.luxun), WList.luxun.toString());
//		
		WList.zhouyv.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		result.put(CalcWJHarm.calcVal(WList.zhouyv), WList.zhouyv.toString());
		
		return result;
	}
}
