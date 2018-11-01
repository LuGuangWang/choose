package wlg.core.bean.zhanfa;

/**
 * 控制战法
 * @author seven
 *
 */
public class KongZhiZhanFa extends ZhanFa {

	//持续几回合
	private int keep = 1;
	
	public KongZhiZhanFa(String name,ZFType t,int ready,int keep,float doneRate,int distance,Person persons) {
		super(name, t, ready,doneRate, 0f, distance, persons);
		this.keep = keep;
	}

	public int getKeep() {
		return keep;
	}
	
	public int getKeephuihe() {
		return keep;
	}
}
