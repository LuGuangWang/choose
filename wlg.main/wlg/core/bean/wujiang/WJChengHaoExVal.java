package wlg.core.bean.wujiang;

public class WJChengHaoExVal {
	private StringBuilder name = new StringBuilder();
	private float speedRate = 0.0f;//速度加成比
	private float attackRate = 0.0f;//攻击加成比
	private float defenseRate = 0.0f;//防御加成比
	private float strategyRate = 0.0f;//策略加成比
	
//	private int speedVal = 0;//速度加成值
//	private int strategyVal = 0;//策略加成值
//	private int attackVal = 0;//攻击加成值
//	private int defenseVal = 0;//防御加成值
	
	private final String regex = ",";
	
	public int size() {
		int size = 0;
		String names = name.toString();
		if(names.contains(regex)) {
			size = names.split(regex).length;
		}
		return size;
	}
	
	public StringBuilder getName() {
		return name;
	}
	public void addName(String name) {
		this.name.append(name).append(regex);
	}
	public float getSpeedRate() {
		return speedRate;
	}
	public void addSpeedRate(float speedRate) {
		this.speedRate += speedRate;
	}
	public float getAttackRate() {
		return attackRate;
	}
	public void addAttackRate(float attackRate) {
		this.attackRate += attackRate;
	}
	public float getDefenseRate() {
		return defenseRate;
	}
	public void addDefenseRate(float defenseRate) {
		this.defenseRate += defenseRate;
	}
	public float getStrategyRate() {
		return strategyRate;
	}
	public void addStrategyRate(float strategyRate) {
		this.strategyRate += strategyRate;
	}
	
}
