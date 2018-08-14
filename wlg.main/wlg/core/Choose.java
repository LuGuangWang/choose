package wlg.core;

import java.util.Map;
import java.util.TreeMap;

import wlg.core.bean.zhanfa.JiaChengZhanFa;
import wlg.core.bean.zhanfa.MaiLeiZhanFa;
import wlg.core.bean.zhanfa.Person;
import wlg.core.bean.zhanfa.ShuaXinZhanFa;
import wlg.core.bean.zhanfa.ZengYiZhanFa;
import wlg.core.bean.zhanfa.ZhanFa;
import wlg.core.calc.CalcHarm;

public class Choose {
	
	//增益战法 火势风威
	ZengYiZhanFa huoshifengwei = new ZengYiZhanFa(1,0.4f,1.11f,new Person(3),0f,2.21f);
	//战法 十面埋伏  
	ZhanFa shimianmaifu = new ZhanFa(1,0.4f,1.3f,new Person(3));
	//战法 声东击西
	ZhanFa shengdongjixi = new ZhanFa(1,0.5f,2.31f,new Person(1,2));
	//埋雷战法 楚歌四起 与速度相关
	MaiLeiZhanFa chugesiqi = new MaiLeiZhanFa(1,2,2,0.5f,1.27f,new Person(2,3));
	//加成战法 不攻
	JiaChengZhanFa bugong = new JiaChengZhanFa(0,1,0.83f,new Person(1),0.25f);
	//刷新战法 深谋远虑
	ShuaXinZhanFa shenmouyuanlv = new ShuaXinZhanFa(0.09f);
	
	/**
	 * TODO 添加下一回合逻辑  减伤 回血 普通攻击的伤害
	 * @return
	 */
	public Map<Float, String> calcZhanFaVal() {
		TreeMap<Float, String> result = new TreeMap<>();
		float a = CalcHarm.calcVal(huoshifengwei,shimianmaifu,shengdongjixi);
		result.put(a, "火势风威 十面埋伏 声东击西 伤害值:");
		
		float b = CalcHarm.calcVal(huoshifengwei,shimianmaifu,chugesiqi);
		result.put(b, "火势风威 十面埋伏 楚歌四起(低速度) 伤害值:");
		
		float c = CalcHarm.calcVal(huoshifengwei,shimianmaifu,shenmouyuanlv);
		result.put(c, "火势风威 十面埋伏 深谋远虑 伤害值:");
		
		float d = CalcHarm.calcVal(huoshifengwei,chugesiqi,shenmouyuanlv);
		result.put(d, "火势风威 楚歌四起(低速度) 深谋远虑 伤害值:");
		
		float f = CalcHarm.calcVal(huoshifengwei,chugesiqi,bugong);
		result.put(f, "火势风威 楚歌四起(低速度) 不攻 伤害值:");
		
		float g = CalcHarm.calcVal(huoshifengwei,shengdongjixi,bugong);
		result.put(g, "火势风威 声东击西 不攻 伤害值:");
		
		float h = CalcHarm.calcVal(huoshifengwei,shimianmaifu,bugong);
		result.put(h, "火势风威 十面埋伏 不攻 伤害值:");
		
		float i = CalcHarm.calcVal(huoshifengwei,shenmouyuanlv,bugong);
		result.put(i, "火势风威 深谋远虑 不攻 伤害值:");
		
		return result;
	}
	
	public static void main(String[] args) {
		Choose c = new Choose();
		Map<Float, String> result = c.calcZhanFaVal();
		System.out.println("战法组合的伤害值越高,代表越好:");
		((TreeMap<Float, String>) result).descendingMap().forEach((k,v)->{
			System.out.println(v + k);
		});
	}
}
