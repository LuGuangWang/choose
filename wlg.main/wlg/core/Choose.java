package wlg.core;

import wlg.core.bean.zhanfa.JiaChengZhanFa;
import wlg.core.bean.zhanfa.MaiLeiZhanFa;
import wlg.core.bean.zhanfa.Person;
import wlg.core.bean.zhanfa.ShuaXinZhanFa;
import wlg.core.bean.zhanfa.ZengYiZhanFa;
import wlg.core.bean.zhanfa.ZhanFa;
import wlg.core.calc.CalcHarm;

public class Choose {
	
	/**
	 * TODO 添加下一回合逻辑  减伤 回血 普通攻击的伤害
	 * @return
	 */
	public float calcZhanFaVal() {
		//增益战法 火势风威
		ZengYiZhanFa huoshifengwei = new ZengYiZhanFa(1,0.4f,1.11f,new Person(3),0f,2.21f);
		
		//战法 十面埋伏  
		ZhanFa shimianmaifu = new ZhanFa(1,0.4f,1.3f,new Person(3));
		//战法 声东击西
		ZhanFa shengdongjixi = new ZhanFa(1,0.5f,2.31f,new Person(1,2));
		
		//埋雷战法 楚歌四起
		MaiLeiZhanFa chugesiqi = new MaiLeiZhanFa(1,2,6,0.5f,1.27f,new Person(2,3));
		//加成战法 不攻
		JiaChengZhanFa bugong = new JiaChengZhanFa(0,1,0.83f,new Person(1),0.25f);
		//刷新战法 深谋远虑
		ShuaXinZhanFa shenmouyuanlv = new ShuaXinZhanFa(0.09f);
		
		float a = CalcHarm.calcVal(shimianmaifu,huoshifengwei,shengdongjixi);
		System.out.println("火势风威 十面埋伏 声东击西 伤害值:" + a);
		
		float b = CalcHarm.calcVal(shimianmaifu,huoshifengwei,chugesiqi);
		System.out.println("火势风威 十面埋伏 楚歌四起(低速度) 伤害值:" + b);
		
		float c = CalcHarm.calcVal(shimianmaifu,huoshifengwei,shenmouyuanlv);
		System.out.println("火势风威 十面埋伏 深谋远虑 伤害值:" + c);
		
		float d = CalcHarm.calcVal(shimianmaifu,chugesiqi,shenmouyuanlv);
		System.out.println("火势风威 楚歌四起(低速度) 深谋远虑 伤害值:" + d);
		
		float f = CalcHarm.calcVal(huoshifengwei,chugesiqi,bugong);
		System.out.println("火势风威 楚歌四起(低速度) 不攻 伤害值:" + f);
		
		float g = CalcHarm.calcVal(huoshifengwei,shengdongjixi,bugong);
		System.out.println("火势风威 声东击西 不攻 伤害值:" + g);
		
		float h = CalcHarm.calcVal(huoshifengwei,shimianmaifu,bugong);
		System.out.println("火势风威 十面埋伏 不攻 伤害值:" + h);
		
		float i = CalcHarm.calcVal(huoshifengwei,shenmouyuanlv,bugong);
		System.out.println("火势风威 深谋远虑 不攻 伤害值:" + i);
		
		return 0;
	}
	
	public static void main(String[] args) {
		Choose c = new Choose();
		c.calcZhanFaVal();
	}
}
