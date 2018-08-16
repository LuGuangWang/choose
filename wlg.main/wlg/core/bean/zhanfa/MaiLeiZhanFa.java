package wlg.core.bean.zhanfa;
/**
 * 需要在敌方行动前行动,才能触发伤害
 * @author seven
 *
 */
public class MaiLeiZhanFa extends ZhanFa{
	//持续几回合
	private int keep = 1;
	//速度,相对于你队伍中的其他两名成员计算 值可为 0,1,2
	private int speed = 0;
	
	public MaiLeiZhanFa(String name,int ready,int keep,int speed, float doneRate, float harmRate, Person persons) {
		super(name,ready, doneRate, harmRate, persons);
		this.keep = keep;
		this.speed=speed;
	}

	public int getKeep() {
		return keep;
	}

	public float getSpeed() {
		return speed / 2.0f;
	}
	
}
