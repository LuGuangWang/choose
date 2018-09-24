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
	public ZengYiZhanFa(String name,ZFType t,int ready, float doneRate,float harmRate,int distance,Person persons,float exRate,float exHarmRate) {
		super(name,t,ready,doneRate,harmRate,distance,persons,exRate,exHarmRate);
	}
	
	/**
	 * 当前战法增益伤害,需其他战法造成伤害
	 * @return
	 */
	public float getExVal(ZhanFa other) {
		UpVal upVal = new UpVal();
		upVal.setAddStrategyVal(1.0f);
		
		float sum = getExVal(other, this.getExHarmVal(),upVal);
		return sum;
	}
	
}
