package wlg.core.bean.zhanfa;

/**
 * 控制战法
 * @author seven
 *
 */
public class KongZhiZhanFa extends MaiLeiZhanFa {

	
	public KongZhiZhanFa(String name,ZFType t,int ready,int keep,float doneRate,Person persons) {
		super(name, t, ready, keep,doneRate, 0f, persons);
	}

}
