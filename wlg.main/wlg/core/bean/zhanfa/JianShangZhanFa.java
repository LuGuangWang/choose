package wlg.core.bean.zhanfa;

/**
 * 减伤战法
 * @author seven
 *
 */
public class JianShangZhanFa extends MaiLeiZhanFa {

	
	public JianShangZhanFa(String name,ZFType t,int ready,int keep,float doneRate,Person persons) {
		super(name, t, ready, keep,doneRate, 0f, persons);
	}

}
