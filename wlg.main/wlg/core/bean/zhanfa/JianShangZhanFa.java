package wlg.core.bean.zhanfa;

/**
 * 减伤战法
 * @author seven
 *
 */
public class JianShangZhanFa extends ZhanFa {

	public JianShangZhanFa(String name,int ready,float doneRate,Person persons) {
		super(name, ZFType.jianshang, ready, doneRate, 0f, persons);
	}
}
