package wlg.core;

import java.util.Map;
import java.util.TreeMap;

import wlg.core.bean.wujiang.WList;
import wlg.core.bean.zhanfa.ZList;
import wlg.core.calc.CalcHarm;

public class Choose {
	
	
	public static void main(String[] args) {
		WList.luxun.setSecondZhanFa(ZList.bugong).setThreeZhanFa(ZList.shimianmaifu);
		System.out.println(WList.luxun.toString() + 1);
		
		
		
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
		float a = CalcHarm.calcVal(ZList.huoshifengwei,ZList.shimianmaifu,ZList.shengdongjixi);
		result.put(a, "火势风威 十面埋伏 声东击西 伤害值:");
		
		float b = CalcHarm.calcVal(ZList.huoshifengwei,ZList.shimianmaifu,ZList.chugesiqi);
		result.put(b, "火势风威 十面埋伏 楚歌四起(低速度) 伤害值:");
		
		float c = CalcHarm.calcVal(ZList.huoshifengwei,ZList.shimianmaifu,ZList.shenmouyuanlv);
		result.put(c, "火势风威 十面埋伏 深谋远虑 伤害值:");
		
		float d = CalcHarm.calcVal(ZList.huoshifengwei,ZList.chugesiqi,ZList.shenmouyuanlv);
		result.put(d, "火势风威 楚歌四起(低速度) 深谋远虑 伤害值:");
		
		float f = CalcHarm.calcVal(ZList.huoshifengwei,ZList.chugesiqi,ZList.bugong);
		result.put(f, "火势风威 楚歌四起(低速度) 不攻 伤害值:");
		
		float g = CalcHarm.calcVal(ZList.huoshifengwei,ZList.shengdongjixi,ZList.bugong);
		result.put(g, "火势风威 声东击西 不攻 伤害值:");
		
		float h = CalcHarm.calcVal(ZList.huoshifengwei,ZList.shimianmaifu,ZList.bugong);
		result.put(h, "火势风威 十面埋伏 不攻 伤害值:");
		
		float i = CalcHarm.calcVal(ZList.huoshifengwei,ZList.shenmouyuanlv,ZList.bugong);
		result.put(i, "火势风威 深谋远虑 不攻 伤害值:");
		
		return result;
	}
}
