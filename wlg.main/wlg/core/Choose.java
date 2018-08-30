package wlg.core;

import java.util.Map;
import java.util.TreeMap;

import wlg.core.bean.wujiang.WList;
import wlg.core.bean.zhanfa.ZList;
import wlg.core.calc.CalcWJHarm;

public class Choose {
	
	
	public static void main(String[] args) {
		float sum = CalcWJHarm.calcVal(WList.lvmeng,WList.luxun);
		
		String key = WList.luxun.toKey() + WList.lvmeng.toKey() + "伤害值:";
		System.out.println(key + sum);
		
		Choose c = new Choose();
		Map<Float, String> result = c.calcZhanFaVal();
		System.out.println("战法组合的伤害值越高,代表越好:");
		((TreeMap<Float, String>) result).descendingMap().forEach((k,v)->{
			System.out.println(v + k);
		});
	}
	
	/**
	 * TODO 添加下一回合逻辑  减伤 回血 普通攻击的伤害
	 * @return
	 */
	public Map<Float, String> calcZhanFaVal() {
		TreeMap<Float, String> result = new TreeMap<>();
		WList.luxun.setSecondZhanFa(ZList.chugesiqi).setThreeZhanFa(ZList.shenmouyuanlv);
		result.put(CalcWJHarm.calcVal(WList.luxun), WList.luxun.toString());
		
		WList.luxun.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.shengdongjixi);
		result.put(CalcWJHarm.calcVal(WList.luxun), WList.luxun.toString());
		
		WList.luxun.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.chugesiqi);
		result.put(CalcWJHarm.calcVal(WList.luxun), WList.luxun.toString());
		
		WList.luxun.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.shenmouyuanlv);
		result.put(CalcWJHarm.calcVal(WList.luxun), WList.luxun.toString());
		
		WList.luxun.setSecondZhanFa(ZList.chugesiqi).setThreeZhanFa(ZList.bugong);
		result.put(CalcWJHarm.calcVal(WList.luxun), WList.luxun.toString());
		
		WList.luxun.setSecondZhanFa(ZList.shengdongjixi).setThreeZhanFa(ZList.bugong);
		result.put(CalcWJHarm.calcVal(WList.luxun), WList.luxun.toString());
		
		WList.luxun.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.bugong);
		result.put(CalcWJHarm.calcVal(WList.luxun), WList.luxun.toString());
		
		WList.luxun.setSecondZhanFa(ZList.shenmouyuanlv).setThreeZhanFa(ZList.bugong);
		result.put(CalcWJHarm.calcVal(WList.luxun), WList.luxun.toString());
		
		WList.luxun.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.hunshuimoyv);
		result.put(CalcWJHarm.calcVal(WList.luxun), WList.luxun.toString());
		
		
		return result;
	}
}
