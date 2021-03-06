package wlg.core.bean.wujiang;
/**
 * 武将卡
 * @author seven
 *
 */

import wlg.core.bean.zhanfa.ZList;

public class WList {
	/******     吴国        *******/
	//陆逊
	public static WuJiang luxun = new WuJiang("陆逊",WZType.wu,WBType.bu,40,95,63,95,2,ZList.huoshifengwei);
	//吕蒙
	public static WuJiang lvmeng = new WuJiang("吕蒙",WZType.wu,WBType.gong,54,92,86,93,4,ZList.baiyidujiang);
	//周瑜
	public static WuJiang zhouyv = new WuJiang("周瑜",WZType.wu,WBType.gong,53,92,70,98,4,ZList.xuanwujuliu);
	//小甘宁
	public static WuJiang xiaoganning = new WuJiang("小甘宁", WZType.wu,WBType.gong, 47, 75, 96, 71, 3, ZList.qingxiawangwei);
	//小凌统
	public static WuJiang xiaolingtong = new WuJiang("小凌统", WZType.wu,WBType.gong,49, 85, 82, 41, 2, ZList.guotu);
	/******     蜀国        *******/
	//SP赵云
	public static WuJiang zhaoyun = new WuJiang("SP赵云",WZType.shu,WBType.bu,65,92,101,78,3,ZList.yinlonggudan);
	//黄月英
	public static WuJiang huangyueying = new WuJiang("黄月英",WZType.shu,WBType.bu,29,70,33,94,2,ZList.jiangxinbujie);
	//马云禄
	public static WuJiang mayunlu = new WuJiang("马云禄", WZType.shu, WBType.qi, 88, 78, 89, 55, 3, ZList.hongyantieqi);
	//步诸葛
	public static WuJiang buzhuge = new WuJiang("步诸葛", WZType.shu,WBType.bu, 22, 95, 49, 108, 2, ZList.zhugejinnang);
	//SP徐庶
	public static WuJiang xushu = new WuJiang("SP徐庶", WZType.shu, WBType.qi, 88, 90, 80, 97, 1, ZList.pozhenqiangxi);
	//庞统
	public static WuJiang pangtong = new WuJiang("庞统", WZType.shu,WBType.bu, 42, 88, 71, 98, 3, ZList.mimoudingshu);
	//S3 弓诸葛
	public static WuJiang gongzhuge = new WuJiang("弓诸葛", WZType.shu, WBType.gong, 55, 95, 49, 108, 3, ZList.mingqixushi);
	/******     魏国        *******/
	//荀彧
	public static WuJiang xunyv = new WuJiang("荀彧", WZType.wei, WBType.qi, 85, 89, 47, 96, 2, ZList.quhutunlang);
	//张春花
	public static WuJiang zhangchunhua = new WuJiang("张春华", WZType.wei, WBType.gong, 55, 56, 77, 82, 3, ZList.qiangshi);
	//曹操
	public static WuJiang caocao = new WuJiang("曹操", WZType.wei, WBType.qi, 88, 107, 80, 90, 2, ZList.weiwuzhishi);
	//曹丕
	public static WuJiang caopi = new WuJiang("曹丕", WZType.wei, WBType.gong, 45, 78, 83, 83, 4, ZList.weiwuzhize);
	//王异
	public static WuJiang wangyi = new WuJiang("王异", WZType.wei, WBType.gong, 53, 52, 88, 90, 4, ZList.shichou);
	//郭嘉
	public static WuJiang guojia = new WuJiang("郭嘉", WZType.wei, WBType.qi, 78, 87, 40, 100, 2, ZList.qizuoguimou);
	//小郭嘉
	public static WuJiang xiaoguojia = new WuJiang("小郭嘉", WZType.wei, WBType.qi, 85, 65, 50, 100, 3, ZList.guimou);
	//夏侯渊
	public static WuJiang xiahouyuan = new WuJiang("夏侯渊", WZType.wei, WBType.qi, 100, 90, 91, 66, 3, ZList.hubuguanyou);
	//张辽
	public static WuJiang zhangliao = new WuJiang("张辽", WZType.wei, WBType.qi, 105, 94, 91, 83, 3, ZList.qijirufeng);
	//SP荀攸
	public static WuJiang xunyou = new WuJiang("SP荀攸", WZType.wei, WBType.bu, 32, 83, 45, 98, 2, ZList.mouzhu);
	/******     汉       *******/
	//貂蝉
	public static WuJiang handiaochan = new WuJiang("汉貂蝉", WZType.han, WBType.gong, 68, 74, 79, 88, 2, ZList.biyue);
	//灵帝
	public static WuJiang lingdi = new WuJiang("灵帝", WZType.han, WBType.gong, 65, 91, 85, 84, 5, ZList.dilinhuiguang);
	//何太后
	public static WuJiang hetaihou = new WuJiang("何太后", WZType.han, WBType.gong, 53, 77, 68, 81, 3, ZList.muyifumeng);
	//朱儁
	public static WuJiang xiaozhujun = new WuJiang("小朱儁", WZType.han, WBType.gong, 48, 83, 89, 75, 4, ZList.jiezhenguandong);
	/******     群       *******/
	//张宁
	public static WuJiang zhangning = new WuJiang("张宁", WZType.qun, WBType.bu, 47, 72, 37, 88, 2, ZList.huangtianyvyin);
	//马超
	public static WuJiang machao = new WuJiang("马超", WZType.qun, WBType.qi, 92, 91, 97, 45, 3, ZList.xuejianhuangsha);
	//S2 吕布
	public static WuJiang lvbu = new WuJiang("S2吕布", WZType.qun, WBType.gong, 77, 72, 101, 29, 4, ZList.yuanmensheji);
}
