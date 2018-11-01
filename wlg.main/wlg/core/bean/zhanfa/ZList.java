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
	public static ZengYiZhanFa huoshifengwei = new ZengYiZhanFa("火势风威",ZFType.ZhuDong_FaShu_ZiDai,1, 0.4f, 1.11f, 5,new Person(3), 0f, 2.21f);
	//吕蒙 白衣渡江
	public static KongZhiAndHarmZhanFa baiyidujiang = new KongZhiAndHarmZhanFa("白衣渡江",ZFType.ZhiHui_KongZhiGongJi_FaShuShangHai, 2, 2.15f, 1.0f,5,new Person(2));
	//周瑜 玄武巨流
	public static KongZhiAndHarmZhanFa xuanwujuliu = new KongZhiAndHarmZhanFa("玄武洰流",ZFType.ZhuDong_FaShuShangHai_KongZhiGongji,1,0.3f,1.5f,2,0f,5, new Person(3));
	//SP赵云 银龙孤胆
	public static DieJiaZhanFa yinlonggudan = new DieJiaZhanFa("银龙孤胆",ZFType.ZhuDong_GongJi,1,0.4f,0.8f,7,0.07f,5,new Person(1,2,3));
	//黄月英 匠心不竭
	public static MultipleHarmZhanFa jiangxinbujie = new MultipleHarmZhanFa("匠心不竭", ZFType.ZhiHui_Multiple_FaShu, 0, 1, 0.34f, 3,0.41f, 5,0.44f, 6, new Person(3));
	//荀彧 驱虎吞狼
	public static ZhanFa quhutunlang = new ZhanFa("驱虎吞狼",ZFType.ZhuDong_FaShu_KongZhiHuiFu,0,0.3f,1.43f,5,new Person(3));
	//张春华 强势
	public static QiangShiZhanFa qiangshi = new QiangShiZhanFa("强势",ZFType.ZhuDong_JianShang_KongZhiFaShu, 0, 0.35f, 0.32f, 5, new Person(2),2);
	//曹操 魏武之世
	public static WeiWuZhiShiZhanFa weiwuzhishi = new WeiWuZhiShiZhanFa("魏武之世", ZFType.ZhiHui_JiaShuXing_JiaJuLi, 0, 1, 1.15f,1,5, new Person(3));
	//曹丕 魏武之泽
	public static GongJiZhanFa weiwuzhize = new GongJiZhanFa("魏武之泽", ZFType.ZhuDong_JiaGongJi_LianJi, 0, 0.35f, 1.25f, 2, new Person(1,2),2);
	//王异 世仇 
	public static ZhanFa shichou = new ZhanFa("世仇", ZFType.ZhuiJi_FaShu, 0, 0.6f, 2.33f, 4, new Person(1));
	//郭嘉 奇佐鬼谋
	public static QiZuoGuiMou qizuoguimou = new QiZuoGuiMou("奇佐鬼谋", ZFType.ZhuDong_JiaShuXing_KongZhi, 0, 0.35f, 0.22f,2, 5, new Person(2));
	//小郭嘉 鬼谋
	public static QiZuoGuiMou guimou = new QiZuoGuiMou("鬼谋", ZFType.ZhuDong_Multiple_KongZhi, 0, 0.35f, 3, 5, new Person(1),0.4f);
	//汉貂蝉 闭月
	public static BiYueZhanFa biyue = new BiYueZhanFa("闭月", ZFType.ZhuDong_BaoZou_jianFangYu, 1, 0.35f, 29.0f, 4, new Person(2),3);
	//马云禄 红颜铁骑
	public static LianJiZhanFa hongyantieqi = new LianJiZhanFa("红颜铁骑", ZFType.BeiDong_LianJi_jiagongji, 0, 1.0f, 30.0f, 1, new Person(1),2);
	//张宁 黄天余音
	public static JiaChengZhanFa huangtianyvyin = new JiaChengZhanFa("黄天余音", ZFType.ZhuDong_JiaShuXing, 0, 1.0f, 4, new Person(1),26,2);
	//灵帝 帝临回光  TODO 自身不可回复兵力
	public static DiGuangHuiLin dilinhuiguang = new DiGuangHuiLin("帝临回光", ZFType.ZhiHui_JiaJuLi_FenBing_KongHuang, 2, 1.0f, 0.59f, 5, new Person(3),0.7f,new Person(1,1,2));
	//何太后 母仪浮梦
	public static MuYiFuMeng muyifumeng = new MuYiFuMeng("母仪浮梦", ZFType.ZhiHui_GuiBi_JianShang, 0, 1.0f, 0.5f,0.3f, 5, new Person(3),1,4);
	//小朱儁 节镇关东
	public static JieZhenGuanDong jiezhenguandong = new JieZhenGuanDong("节镇关东", ZFType.ZhiHui_YouXian_DongYao, 0, 1.0f, 1.2f, 5, new Person(2),2,1.0f,1.0f);
	//小甘宁 轻侠妄为
	public static QingXiaWangWei qingxiawangwei = new QingXiaWangWei("轻侠妄为", ZFType.ZhiHui_MianYi_jiagongji, 0, 1.0f, 0.4f, 1, new Person(1),3);
	//小凌统 国土
	public static GuoTu guotu = new GuoTu("国土", ZFType.ZhiHui_MianYi_WushiGuiBi, 0, 1.0f, 3, new Person(1),4);
	//夏侯渊 虎步关右
	public static HuBuGuanYou hubuguanyou = new HuBuGuanYou("虎步关右", ZFType.ZhuDong_ShouCi_JiaGongJi, 0, 1.0f, 0.7f, 1, new Person(1));
	//张辽 其疾如风
	public static QiJiRuFeng qijirufeng = new QiJiRuFeng("其疾如风", ZFType.ZhiHui_JiaSuDu_JiaPuGong, 0, 1.0f, 41, 3, new Person(2,3),3,0.7f);
	//马超 血溅黄沙
	public static XueJianHuangSha xuejianhuangsha = new XueJianHuangSha("血溅黄沙", ZFType.BeiDong_WuFS_JiaGongJi, 0, 1.0f, 1.2f, 1, new Person(1),true);
	//荀攸 谋主
	public static MouZhu mouzhu = new MouZhu("谋主", ZFType.ZhuDong_YiChu_GuiBi_DongCha_XianShou, 0, 0.4f, 2, new Person(1),0.5f,0.5f,0.5f,2);
	//诸葛亮 诸葛锦囊
	public static ZhuGeJinNang zhugejinnang = new ZhuGeJinNang("诸葛锦囊", ZFType.ZhuDong_JianShang_JiaShang, 0, 0.35f, 2, new Person(3),0.35f,0.14f,2);
	//SP徐庶 破阵强袭
	public static PoZhenQiangXi pozhenqiangxi = new PoZhenQiangXi("破阵强袭", ZFType.ZhuiJi_FaShu_BaoZou_JiaFaShu, 0, 1.0f, 1.19f, 1, new Person(1),0.4f,0.05f,0.3f);
	//庞统 密谋定蜀
	public static MiMouDingShu mimoudingshu = new MiMouDingShu("密谋定蜀", ZFType.ZhuDong_jianshang_konghuang_zuzhou, 0, 0.35f, 1.15f, 5, new Person(2),0.3f,1.33f,2);
	/*****    -------------   武将自带战法     end -------------   *****/
	
	
	/*****    -------------   拆解战法     begin -------------   *****/
	// 十面埋伏
	public static JianShangZhanFa shimianmaifu = new JianShangZhanFa("十面埋伏",ZFType.ZhuDong_FaShu_JianShang,1, 0.4f, 1.3f, 5,new Person(3),0.0f,Conf.dafu_xiajiang);
	// 声东击西
	public static ZhanFa shengdongjixi = new ZhanFa("声东击西",ZFType.ZhuDong_FaShu,1, 0.5f, 2.31f, 5,new Person(1, 2));
	// 楚歌四起 与速度相关
	public static MaiLeiZhanFa chugesiqi = new MaiLeiZhanFa("楚歌四起",ZFType.ZhuDong_FaShu_KeepHuiHe,1, 2, 0.5f, 1.27f, 5,new Person(2, 3));
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
	public static ShiJiZhanFa shiji = new ShiJiZhanFa("始计", ZFType.ZhiHui_JiaFaShu_JianShang_MianYi, 0, 1.0f, 0.2f,0.3f,1, 1, new Person(1),4);
	//青囊秘要
	public static HuiFuZhanFa qingnangmiyao = new HuiFuZhanFa("青囊秘要", ZFType.BeiDong_huifu, 0, 1.0f, 1.5f, 1, new Person(1));
	//迷阵
	public static BiYueZhanFa mizhen = new BiYueZhanFa("迷阵", ZFType.ZhuDong_FaShu_BaoZou, 0, 0.35f, 1.55f, 4, new Person(1));
	//钝兵挫锐
	public static ZhuiJiZhanFa dunbingcuorui = new ZhuiJiZhanFa("钝兵挫锐", ZFType.ZhuiJi_GongJi_KongZhiGongJi, 0, 0.3f, 2.0f, 5, new Person(1));
	//焰焚箕轸
	public static YanFenJiZhen yanfenjizhen = new YanFenJiZhen("焰焚箕轸", ZFType.ZhuDong_FaShu, 1, 0.5f, 1.19f, 4, new Person(2,3),1.19f);
	//水淹七军
	public static ShuiYanQiJun shuiyanqijun = new ShuiYanQiJun("水淹七军", ZFType.ZhuDong_FaShu_jianGongJi, 1, 0.5f, 2.05f, 3, new Person(2),10.0f);
	//胜兵求战
	public static ShengBingQiuZhan shengbingqiuzhan = new ShengBingQiuZhan("胜兵求战", ZFType.ZhiHui_SkipReady_Jiashang, 0, 1.0f, 0.8f, 2, new Person(3),0.15f);
	//合流
	public static HuiFuZhanFa heliu = new HuiFuZhanFa("合流", ZFType.ZhuDong_ZiSheng_YouJun_HuiFu, 0, 0.4f, 1.31f, 3, new Person(2));
	//增援
	public static HuiFuZhanFa zengyuan = new HuiFuZhanFa("增援", ZFType.ZhuDong_QunTi_HuiFu, 1, 0.45f, 1.98f, 3, new Person(2));
	//危崖困军 TODO 降低防御属性
	public static ZhanFa weiyakunjun = new ZhanFa("危崖困军",ZFType.ZhuDong_FaShu, 1, 0.5f, 2.1f, 2, new Person(2));
	//一骑当千
	public static ZhanFa yijidangqian = new ZhanFa("一骑当千", ZFType.ZhuDong_GongJi_YiJi, 1, 0.3f, 2.8f, 5, new Person(3));
	//怯心夺志
	public static ZhanFa qiexinduozhi = new ZhanFa("怯心夺志", ZFType.ZhuiJi_GongJi_FengFaShu, 0, 0.3f, 2.0f, 5, new Person(1));
	//落雷
	public static ZhanFa luolei = new ZhanFa("落雷", ZFType.ZhuDong_FaShu_HunLuan, 0, 0.35f, 1.48f, 4, new Person(1));
	/**   =============S3 =================== **/
	//形兵之极
	public static XingBingZhiJi xingbingzhiji = new XingBingZhiJi("形兵之极", ZFType.ZhiHui_DaYing_ZhongJun_QianFeng, 0, 1.0f, 2, new Person(3),0.1f,0.4f,0.5f);
	/*****    -------------   拆解战法     end -------------   *****/
}
