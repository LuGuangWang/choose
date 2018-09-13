package wlg.core.bean.zhanfa;

/**
 * 多种战法伤害
 * 匠心不竭
 * @author seven
 *
 */
public class MultipleHarmZhanFa extends ZhanFa {
	private float secondHVal;//第二种伤害值
	private int secondHId;//第二种伤害生效回合
	private float threeHVal;//第三种伤害值
	private int threeHId;//第三种伤害生效回合
	
	public MultipleHarmZhanFa(String name, ZFType t, int ready, float doneRate, float harmVal, 
			int secondHId,float secondHVal,int threeHId,float threeHVal,int distance,Person persons) {
		super(name, t, ready, doneRate, harmVal, distance, persons);
		this.secondHVal=secondHVal;
		this.threeHVal=threeHVal;
		this.secondHId=secondHId;
		this.threeHId=threeHId;
	}

	public float getSecondHVal() {
		return secondHVal;
	}
	public float getThreeHVal() {
		return threeHVal;
	}
	public int getSecondHId() {
		return secondHId;
	}
	public int getThreeHId() {
		return threeHId;
	}
	
}
