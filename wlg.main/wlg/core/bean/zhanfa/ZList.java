package wlg.core.bean.zhanfa;

import wlg.core.bean.conf.Conf;

/**
 * 战法列表
 * @author seven
 *
 */
public abstract class ZList {
	/*****    -------------   武将自带战法     begin -------------   *****/
	// 增益战法 火势风威
	public static ZengYiZhanFa huoshifengwei = new ZengYiZhanFa("火势风威",ZFType.ZhuDong_FaShu,1, 0.4f, 1.11f, new Person(3), 0f, 2.21f);
	//白衣渡江
	public static KongZhiAndHarmZhanFa baiyidujiang = new KongZhiAndHarmZhanFa("白衣渡江",ZFType.ZhiHui_KongZhiGongJi_FaShuShangHai, 2, 2.15f, new Person(2));
	//玄武巨流
	public static KongZhiAndHarmZhanFa xuanwujuliu = new KongZhiAndHarmZhanFa("玄武巨流",ZFType.ZhuDong_FaShuShangHai_KongZhiGongji,1,0.3f,1.5f,2,0f,new Person(3));
	/*****    -------------   武将自带战法     end -------------   *****/
	
	
	/*****    -------------   拆解战法     begin -------------   *****/
	// 战法 十面埋伏
	public static JianShangZhanFa shimianmaifu = new JianShangZhanFa("十面埋伏",ZFType.ZhuDong_FaShu_JianShang,1, 0.4f, 1.3f, new Person(3),0.0f,Conf.dafu_xiajiang);
	// 战法 声东击西
	public static ZhanFa shengdongjixi = new ZhanFa("声东击西",ZFType.ZhuDong_FaShu,1, 0.5f, 2.31f, new Person(1, 2));
	// 埋雷战法 楚歌四起 与速度相关
	public static MaiLeiZhanFa chugesiqi = new MaiLeiZhanFa("楚歌四起",ZFType.ZhuDong_FaShu,1, 2, 0.5f, 1.27f, new Person(2, 3));
	//神兵天降
	public static JiaShangZhanFa shenbingtianjiang = new JiaShangZhanFa("神兵天降",ZFType.ZhiHui_FuZhu_ALL,0,1,0.3f,new Person(2),3);
	// 加成战法 不攻
	public static JiaChengZhanFa bugong = new JiaChengZhanFa("不攻",ZFType.ZhiHui_FaShuJiacheng_FaShuGongJi_BuGongJi,0, 1, 0.83f, new Person(1), 0.25f);
	// 刷新战法 深谋远虑
	public static ShuaXinZhanFa shenmouyuanlv = new ShuaXinZhanFa("深谋远虑",ZFType.BeiDong_JiaCheng,0.09f);
	//控制战法 浑水摸鱼
	public static KongZhiZhanFa hunshuimoyv = new KongZhiZhanFa("浑水摸鱼", ZFType.ZhuDong_KongZhi_ALL,1, 2, 0.35f, new Person(2));
	//反计之策
	public static FanJiZhiCeZhanFa fanjizhice = new FanJiZhiCeZhanFa("反计之策", ZFType.ZhiHui_JianshangFashu_KongZhiFaShu,0, 1,Conf.dafu_xiajiang, new Person(2),3);
	//犄角之势
	public static JiJiaoZhiShiZhanFa jijiaozhishi = new JiJiaoZhiShiZhanFa("犄角之势", ZFType.ZhuDong_GongJi_FaShu,0, 0.4f,1.8f, new Person(2),0.4f,1.43f);
	/*****    -------------   拆解战法     end -------------   *****/
}
