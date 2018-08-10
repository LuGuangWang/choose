package wlg.core.bean.zhanfa;
/**
 * 需要在敌方行动前行动,才能触发伤害
 * @author seven
 *
 */
public class MaiLeiZhanFa extends ZhanFa{

	public MaiLeiZhanFa(int ready, float doneRate, float harmRate, Person persons) {
		super(ready, doneRate, harmRate, persons);
	}

	
}
