package wlg.core;

import wlg.core.bean.CommZhanFa;
import wlg.core.bean.Person;

public class Choose {
	
	public float calcVal(CommZhanFa... zhanfa) {
		float sum = 0;
		//主要伤害
		for(CommZhanFa z:zhanfa) {
			sum += z.getHarmVal();
		}
		//增益伤害
		sum += calcExVal(zhanfa);
		return sum;
	}

	private float calcExVal(CommZhanFa... zhanfa) {
		float sum = 0;
		if(zhanfa.length>1) {
			for(int i=0;i<zhanfa.length;i++) {
				CommZhanFa b = zhanfa[i];
				if(b.isByOther()) {
					for(int j=0;j<zhanfa.length;j++) {
						if(j!= i)
							sum += b.getExVal(zhanfa[j]);
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
		CommZhanFa huoshifengwei = new CommZhanFa(4,0.4f,1.11f,new Person(3),0f,2.21f);
		//战法 十面埋伏  
		CommZhanFa shimianmaifu = new CommZhanFa(4,0.4f,1.3f,new Person(3),0f,0f);
		//战法 声东击西
		CommZhanFa shengdongjixi = new CommZhanFa(4,0.5f,2.31f,new Person(1,2),0f,0f);
		
		System.out.println(calcVal(shimianmaifu,huoshifengwei,shengdongjixi));
		return 0;
	}
	
	public static void main(String[] args) {
		Choose c = new Choose();
		c.calcZhanFaVal();
	}
}
