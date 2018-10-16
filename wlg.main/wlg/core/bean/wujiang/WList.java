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
	
	/******     蜀国        *******/
	//赵云
	public static WuJiang zhaoyun = new WuJiang("SP赵云",WZType.shu,WBType.bu,65,92,101,78,3,ZList.yinlonggudan);
	//黄月英
	public static WuJiang huangyueying = new WuJiang("黄月英",WZType.shu,WBType.bu,29,70,33,94,2,ZList.jiangxinbujie);
	//马云禄
	public static WuJiang mayunlu = new WuJiang("马云禄", WZType.shu, WBType.qi, 88, 78, 89, 55, 3, ZList.hongyantieqi);
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
}
