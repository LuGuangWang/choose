package wlg.core.bean.zhanfa;

/**
 * 减伤战法
 * @author seven
 *
 */
public class JianShangZhanFa extends MaiLeiZhanFa {

	
	public JianShangZhanFa(String name,int ready,int keep,float doneRate,Person persons) {
		super(name, ZFType.jianshang, ready, keep,doneRate, 0f, persons);
	}

}
