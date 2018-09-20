package wlg.core.bean.zhanfa;

import wlg.core.bean.conf.Conf;

/**
 * 战法列表
 * @author seven
 *
 */
public abstract class ZList {
	/*****    -------------   武将自带战法     begin -------------   *****/
	//陆逊 火势风威
	public static ZengYiZhanFa huoshifengwei = new ZengYiZhanFa("火势风威",ZFType.ZhuDong_FaShu,1, 0.4f, 1.11f, 5,new Person(3), 0f, 2.21f);
	//吕蒙 白衣渡江
	public static KongZhiAndHarmZhanFa baiyidujiang = new KongZhiAndHarmZhanFa("白衣渡江",ZFType.ZhiHui_KongZhiGongJi_FaShuShangHai, 2, 2.15f, 1.0f,5,new Person(2));
	//周瑜 玄武巨流
	public static KongZhiAndHarmZhanFa xuanwujuliu = new KongZhiAndHarmZhanFa("玄武洰流",ZFType.ZhuDong_FaShuShangHai_KongZhiGongji,1,0.3f,1.5f,2,0f,5, new Person(3));
	//SP赵云 银龙孤胆
	public static DieJiaZhanFa yinlonggudan = new DieJiaZhanFa("银龙孤胆",ZFType.ZhuDong_GongJi,1,0.4f,0.8f,7,0.08f,5,new Person(1,2,3));
	//黄月英 匠心不竭
	public static MultipleHarmZhanFa jiangxinbujie = new MultipleHarmZhanFa("匠心不竭", ZFType.ZhiHui_Multiple_FaShu, 0, 1, 0.34f, 3,0.41f, 5,0.44f, 6, new Person(3));
	//荀彧 驱虎吞狼 TODO 不可恢复兵力
	public static ZhanFa quhutunlang = new ZhanFa("驱虎吞狼",ZFType.ZhuDong_FaShu,0,0.3f,1.43f,5,new Person(3));
	//张春华 强势
	public static QiangShiZhanFa qiangshi = new QiangShiZhanFa("强势",ZFType.ZhuDong_JianShang_KongZhiFaShu, 0, 0.35f, 0.32f, 5, new Person(2),2);
	//曹操 魏武之世
	public static WeiWuZhiShiZhanFa weiwuzhishi = new WeiWuZhiShiZhanFa("魏武之世", ZFType.ZhiHui_JiaShuXing_JiaJuLi, 0, 1, 1.15f,1,5, new Person(3));
	//曹丕 魏武之泽
	public static GongJiZhanFa weiwuzhize = new GongJiZhanFa("魏武之泽", ZFType.ZhuDong_JiaGongJi_LianJi, 0, 0.35f, 1.25f, 2, new Person(1,2));
	//王异 世仇 TODO 不可急救和休整 持续2回合
	public static ZhanFa shichou = new ZhanFa("世仇", ZFType.ZhuiJi_FaShu, 0, 0.6f, 2.33f, 4, new Person(1));
	//郭嘉 奇佐鬼谋
	public static QiZuoGuiMou qizuoguimou = new QiZuoGuiMou("奇佐鬼谋", ZFType.ZhuDong_JiaShuXing_KongZhi, 0, 0.35f, 0.22f,1,2, 5, new Person(2));
	//汉貂蝉 闭月
	public static BiYueZhanFa biyue = new BiYueZhanFa("闭月", ZFType.ZhuDong_BaoZou_jianFangYu, 1, 0.35f, 29.0f, 4, new Person(2),3);
	/*****    -------------   武将自带战法     end -------------   *****/
	
	
	/*****    -------------   拆解战法     begin -------------   *****/
	// 十面埋伏
	public static JianShangZhanFa shimianmaifu = new JianShangZhanFa("十面埋伏",ZFType.ZhuDong_FaShu_JianShang,1, 0.4f, 1.3f, 5,new Person(3),0.0f,Conf.dafu_xiajiang);
	// 声东击西
	public static ZhanFa shengdongjixi = new ZhanFa("声东击西",ZFType.ZhuDong_FaShu,1, 0.5f, 2.31f, 5,new Person(1, 2));
	// 楚歌四起 与速度相关
	public static MaiLeiZhanFa chugesiqi = new MaiLeiZhanFa("楚歌四起",ZFType.ZhuDong_FaShu,1, 2, 0.5f, 1.27f, 5,new Person(2, 3));
	//神兵天降
	public static JiaShangZhanFa shenbingtianjiang = new JiaShangZhanFa("神兵天降",ZFType.ZhiHui_FuZhu_ALL,0,1,0.3f,4,new Person(2),3);
	// 大赏三军
	public static JiaShangZhanFa dashangsanjun = new JiaShangZhanFa("大赏三军",ZFType.ZhiHui_FuZhu_ALL,0,1,0.3f,3,new Person(2),3);
	// 加成战法 不攻
	public static JiaChengZhanFa bugong = new JiaChengZhanFa("不攻",ZFType.ZhiHui_FaShuJiacheng_FaShuGongJi_BuGongJi,0, 1, 0.83f, 5,new Person(1), 0.25f);
	// 刷新战法 深谋远虑
	public static ShuaXinZhanFa shenmouyuanlv = new ShuaXinZhanFa("深谋远虑",ZFType.BeiDong_JiaCheng,0.09f,1);
	//控制战法 浑水摸鱼
	public static KongZhiZhanFa hunshuimoyv = new KongZhiZhanFa("浑水摸鱼", ZFType.ZhuDong_KongZhi_ALL,1, 2, 0.35f, 4,new Person(2));
	//反计之策
	public static FanJiZhiCeZhanFa fanjizhice = new FanJiZhiCeZhanFa("反计之策", ZFType.ZhiHui_JianshangFashu_KongZhiFaShu,0, 1,Conf.dafu_xiajiang, 4,new Person(2),3);
	//犄角之势
	public static JiJiaoZhiShiZhanFa jijiaozhishi = new JiJiaoZhiShiZhanFa("掎角之势", ZFType.ZhuDong_FaShu_GongJi,0, 0.4f,1.43f, 3,new Person(1,2),0.4f,1.8f);
	//回马
	public static FanJiZhanFa huima = new FanJiZhanFa("回马",ZFType.BeiDong_GongJi,0,1,0.6f,5,new Person(1));
	//战必断金
	public static ZhanBiZhanFa zhanbiduanjin = new ZhanBiZhanFa("战必断金", ZFType.ZhiHui_KongZhiGongJi, 0, 1, 0.9f, 4, new Person(2),3);
	//妖术
	public static BaoZouZhanFa yaoshu = new BaoZouZhanFa("妖术", ZFType.ZhuDong_BaoZou, 1, 0.5f, 2, 4, new Person(2));
	//始计
	public static ShiJiZhanFa shiji = new ShiJiZhanFa("始计", ZFType.ZhiHui_JiaFaShu_JianShang_MianYi, 0, 1.0f, 1.2f,0.3f,1, 1, new Person(1),4);
	/*****    -------------   拆解战法     end -------------   *****/
}
