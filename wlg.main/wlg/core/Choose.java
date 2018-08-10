package wlg.core;

import wlg.core.bean.ZengYiZhanFa;
import wlg.core.bean.ZhanFa;
import wlg.core.bean.Person;

public class Choose {
	
	@SuppressWarnings("unchecked")
	public <T extends ZhanFa> float calcVal(T... zhanfa) {
		float sum = 0;
		//主要伤害
		for(T z:zhanfa) {
			sum = sum + (z.getHarmVal() * 4);
		}
		//增益伤害
		sum += calcExVal(zhanfa);
		return sum;
	}
	
	@SuppressWarnings("unchecked")
	private <T extends ZhanFa> float calcExVal(T... zhanfa) {
		float sum = 0;
		if(zhanfa.length>1) {
			for(int i=0;i<zhanfa.length;i++) {
				T b = zhanfa[i];
				if(b instanceof ZengYiZhanFa) {
					for(int j=0;j<zhanfa.length;j++) {
						if(j!= i)
							sum += (b.getExVal(zhanfa[j]) * 4);
					}
				}
			}
		}
		return sum;
	}
	
	/**
	 * TODO 添加下一回合逻辑  减伤 回血
	 * @return
	 */
	public float calcZhanFaVal() {
		//战法 火势风威
		ZengYiZhanFa huoshifengwei = new ZengYiZhanFa(0.4f,1.11f,new Person(3),0f,2.21f);
		//战法 十面埋伏  
		ZhanFa shimianmaifu = new ZhanFa(0.4f,1.3f,new Person(3));
		//战法 声东击西
		ZhanFa shengdongjixi = new ZhanFa(0.5f,2.31f,new Person(1,2));
		
		//战法 楚歌四起
		ZhanFa chugesiqi = new ZhanFa(0.5f,1.27f,new Person(2,3));
		
		float b = calcVal(shimianmaifu,huoshifengwei,shengdongjixi);
		float a = calcVal(shimianmaifu,huoshifengwei,chugesiqi);
		
		System.out.println("火势风威 十面埋伏 声东击西 伤害值:" + b);
		System.out.println("火势风威 十面埋伏 楚歌四起 伤害值:" + a);
		return 0;
	}
	
	public static void main(String[] args) {
		Choose c = new Choose();
		c.calcZhanFaVal();
	}
}
