package wlg.core.bean.zhanfa;
/**
 * 需要在敌方行动前行动,才能触发伤害
 * @author seven
 *
 */
public class MaiLeiZhanFa extends ZhanFa{
	//持续几回合
	private int keep = 1;
	
	public MaiLeiZhanFa(String name,ZFType t,int ready,int keep, float doneRate, float harmRate,int distance, Person persons) {
		super(name,t,ready, doneRate, harmRate, distance,persons);
		this.keep = keep;
	}

	public int getKeep() {
		return keep;
	}

	public float getHarmVal() {
		return keep * super.getHarmVal();
	}
}
