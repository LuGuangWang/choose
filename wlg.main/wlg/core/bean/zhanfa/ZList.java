package wlg.core.bean.zhanfa;
/**
 * 战法列表
 * @author seven
 *
 */
public abstract class ZList {
	/*****    -------------   武将自带战法     begin -------------   *****/
	// 增益战法 火势风威
	public static ZengYiZhanFa huoshifengwei = new ZengYiZhanFa(1, 0.4f, 1.11f, new Person(3), 0f, 2.21f);
	/*****    -------------   武将自带战法     end -------------   *****/
	
	
	/*****    -------------   拆解战法     begin -------------   *****/
	// 战法 十面埋伏
	public static ZhanFa shimianmaifu = new ZhanFa(1, 0.4f, 1.3f, new Person(3));
	// 战法 声东击西
	public static ZhanFa shengdongjixi = new ZhanFa(1, 0.5f, 2.31f, new Person(1, 2));
	// 埋雷战法 楚歌四起 与速度相关
	public static MaiLeiZhanFa chugesiqi = new MaiLeiZhanFa(1, 2, 0, 0.5f, 1.27f, new Person(2, 3));
	// 加成战法 不攻
	public static JiaChengZhanFa bugong = new JiaChengZhanFa(0, 1, 0.83f, new Person(1), 0.25f);
	// 刷新战法 深谋远虑
	public static ShuaXinZhanFa shenmouyuanlv = new ShuaXinZhanFa(0.09f);
	/*****    -------------   拆解战法     end -------------   *****/
}
