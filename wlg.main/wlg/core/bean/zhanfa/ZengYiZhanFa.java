package wlg.core.bean.zhanfa;
/**
 * 带增益的战法
 * @author seven
 *
 */
public class ZengYiZhanFa extends ZhanFa{
	/**
	 * @param doneRate	发动概率
	 * @param harmRate	伤害率
	 * @param persons	打击队伍数
	 * @param exRate	增益发动概率
	 * @param exHarmRate  增益伤害率
	 */
	public ZengYiZhanFa(String name,ZFType t,int ready, float doneRate,float harmRate,Person persons,float exRate,float exHarmRate) {
		super(name,t,ready,doneRate,harmRate,persons,exRate,exHarmRate);
	}
	
	/**
	 * 当前战法增益伤害,需其他战法造成伤害
	 * @return
	 */
	public float getExVal(ZhanFa other) {
		float sum = 0;
		if(other.getHarmRate()>0) {
			float val = this.getExHarmRate()*this.getDoneRate()*other.getDoneRate();
			val = addShuXingVal(val);
			int[] persons = other.getPersons().getPersons();
			
			int[] ps = this.getPersons().getPersons();
			
			float orate = 1.0f/persons.length;
			float prate = 1.0f/ps.length;
			
			for(int i : persons) {
				for(int p:ps) {
					//实际可伤害人数
					int realP = Math.min(i, p);
					//可全部命中
					if(realP<=p) {
						sum += prate * orate * val * realP;
					}else {
						int total = 3;//初始化总队伍数
						for(int m=realP;m>0;m--) {
							float realRate = m*1.0f/total;
							sum += prate * orate * val * realRate;
							total --; 
						}
					}
				}
			}
		}
		return sum;
	}
}
