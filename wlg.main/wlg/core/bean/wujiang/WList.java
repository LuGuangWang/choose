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
	
	/******     魏国        *******/
	//荀彧
	public static WuJiang xunyv = new WuJiang("荀彧", WZType.wei, WBType.qi, 85, 89, 47, 96, 2, ZList.quhutunlang);
	//张春花
	public static WuJiang zhangchunhua = new WuJiang("张春华", WZType.wei, WBType.gong, 55, 56, 77, 82, 3, ZList.qiangshi);
	//曹操
	public static WuJiang caocao = new WuJiang("曹操", WZType.wei, WBType.qi, 88, 107, 80, 90, 2, ZList.weiwuzhishi);
	//曹丕
	public static WuJiang caopi = new WuJiang("曹丕", WZType.wei, WBType.gong, 45, 78, 83, 83, 4, ZList.weiwuzhize);
	/******     汉       *******/
	
	
	/******     群       *******/
}
