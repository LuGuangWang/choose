package wlg.core;

import wlg.core.bean.HuiHe;
import wlg.core.bean.zhanfa.MaiLeiZhanFa;
import wlg.core.bean.zhanfa.Person;
import wlg.core.bean.zhanfa.ShuaXinZhanFa;
import wlg.core.bean.zhanfa.ZengYiZhanFa;
import wlg.core.bean.zhanfa.ZhanFa;
import wlg.core.calc.CalcOneHarm;

public class Choose {
	
	/**
	 * 8回合总伤害
	 * @return
	 */
	public static <T extends ZhanFa> float calcVal(T... zhanfa) {
		float sum = 0;
		HuiHe huihe = new HuiHe();
		for(int i=1;i<9;i++) {
			huihe.setId(i);
			sum += CalcOneHarm.calcVal(huihe, zhanfa);
		}
		return sum;
	}
	
	
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
		MaiLeiZhanFa chugesiqi = new MaiLeiZhanFa(1,2,0,0.5f,1.27f,new Person(2,3));
		
		//刷新战法 深谋远虑
		ShuaXinZhanFa shenmouyuanlv = new ShuaXinZhanFa(0.09f);
		
//		float a = calcVal(shimianmaifu,huoshifengwei,shengdongjixi);
//		float b = calcVal(shimianmaifu,huoshifengwei,chugesiqi);
		float c = calcVal(shimianmaifu,huoshifengwei,shenmouyuanlv);
		
//		System.out.println("火势风威 十面埋伏 声东击西 伤害值:" + a);
//		System.out.println("火势风威 十面埋伏 楚歌四起(低速度) 伤害值:" + b);
		System.out.println("火势风威 十面埋伏 深谋远虑 伤害值:" + c);
		
		return 0;
	}
	
	public static void main(String[] args) {
		Choose c = new Choose();
		c.calcZhanFaVal();
	}
}
