package wlg.core.bean.zhanfa;

import wlg.core.CheckUtil;

public class JiaChengZhanFa extends ZhanFa {
	/**
	 * 加成伤伤害率
	 */
	private float addRate;
	/**
	 * 持续几回合
	 */
	private int keephuihe;
	
	public JiaChengZhanFa(String name,ZFType t,int ready, float doneRate, float harmRate, Person persons,float addRate,int keephuihe) {
		super(name,t,ready, doneRate, harmRate, persons);
		this.addRate=addRate;
		this.keephuihe=keephuihe;
	}
	
	public JiaChengZhanFa(String name,ZFType t,int ready, float doneRate, float harmRate, Person persons,float addRate) {
		super(name,t,ready, doneRate, harmRate, persons);
		this.addRate=addRate;
	}

	/**
	 * 当前战法增益伤害
	 * @return
	 */
	public float getExVal(ZhanFa other) {
		float sum = 0;
		boolean isZengYi = CheckUtil.isZengYi(other);
		float val = this.addRate;
		if(!isZengYi) {
			val += other.getHarmRate();
		}
		val *= other.getDoneRate();
		val = addShuXingVal(val);
		
		int[] ps = other.getPersons().getPersons();
		if(ps.length>0) {
			int len = ps.length;
			float rate = 1.0f/len;
			for(int i : getPersons().getPersons()) {
				sum += val* rate * i;
			}
		}
		return sum;
	}

	
}
