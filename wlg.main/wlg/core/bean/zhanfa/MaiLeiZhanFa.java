package wlg.core.bean.zhanfa;
/**
 * 需要在敌方行动前行动,才能触发伤害
 * @author seven
 *
 */
public class MaiLeiZhanFa extends ZhanFa{
	//持续几回合
	private int keep = 1;
	
	public MaiLeiZhanFa(int ready,int keep, float doneRate, float harmRate, Person persons) {
		super(ready, doneRate, harmRate, persons);
		this.keep = keep;
	}

	public int getKeep() {
		return keep;
	}
}
