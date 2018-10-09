package wlg.core.bean.zhanfa;

import wlg.core.bean.conf.Conf;
import wlg.core.calc.CalCDistance;
import wlg.core.calc.CalcDoRate;

public class DiGuangHuiLin extends ZhanFa{

	private float secondHarmVal = 0.0f;
	private Person secondPerson;
	
	
	public DiGuangHuiLin(String name, ZFType t, int ready, float doneRate, float harmVal, int distance,
			Person persons,float secondHarmVal,Person secondPerson) {
		super(name, t, ready, doneRate, harmVal, distance, persons);
		this.secondHarmVal = secondHarmVal;
		this.secondPerson=secondPerson;
	}

	public Person getSecondPerson() {
		return secondPerson;
	}
	public float getSecondHarmVal() {
		return secondHarmVal;
	}
	
	public float getHarmVal(float harmVal,UpVal upVal) {
		float sum = 0.0f;
		float fashuVal = super.getHarmVal(harmVal, upVal);
		float gongjiVal = getGongjiVal(upVal);
		
		sum = fashuVal + gongjiVal;
		Conf.log("========战法"+this.getName()+" 单次杀伤力："+ sum + " 原始伤害值:"+ this.getHarmRate() + " 当前伤害值:"+harmVal);
		
		return sum;
	}

	private float getGongjiVal(UpVal upVal) {
		float sum = 0.0f;
		
		float mianyiVal = CalcDoRate.calcMianyiVal(this.getSpeed());
		
		float pval = (mianyiVal + upVal.getDayingUpZFVal()) * secondHarmVal;
		pval *= (this.getAttack() + upVal.getAddQuanShuXingVal()) * Conf.gongji_rate;
		if (secondPerson.getPersons().length > 0) {
			int len = getPersons().getPersons().length;
			float rate = 1.0f / len;
			for (int i : secondPerson.getPersons()) {
				int distance = CalCDistance.calcDistance(this.getDistance(), this.getPosition());
				if(distance<=0) {
					continue;
				}else {
					i = Math.min(i, distance);
				}
				sum += pval * rate * i;
			}
		}
		return sum;
	}
	
}
