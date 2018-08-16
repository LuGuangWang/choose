package wlg.core.bean.zhanfa;

public class JiaChengZhanFa extends ZhanFa {
	/**
	 * 加成伤伤害率
	 */
	private float addRate;
	
	public JiaChengZhanFa(String name,int ready, float doneRate, float harmRate, Person persons,float addRate) {
		super(name,ready, doneRate, harmRate, persons);
		this.addRate=addRate;
	}

	/**
	 * 当前战法增益伤害
	 * @return
	 */
	public float getExVal(ZhanFa other) {
		float sum = 0;
		float val = (other.getHarmRate() + this.addRate)*other.getDoneRate();
		
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
