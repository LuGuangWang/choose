package wlg.core.bean.zhanfa;

public enum ZFType {
	ZhuDong_FaShu,//主动类 法术伤害
	ZhuDong_FuZhu,//主动类 辅助 本身不造成伤害
	ZhuDong_FaShu_JianShang,//主动类 法术伤害 减伤
	ZhuDong_FaShuShangHai_KongZhiGongji,//主动类 先法术伤害 然后控制攻击
	ZhuDong_KongZhi_ALL,//控制攻击与法术,本身不造成伤害
	
	ZhiHui_KongZhiGongJi_FaShuShangHai,//指挥类 先控制攻击 然后法术伤害
	ZhiHui_FaShuJiacheng_FaShuGongJi_BuGongJi,//指挥类 法术加成 法术攻击 本身不造成攻击伤害
	
	
	
	BeiDong_JiaCheng,//被动类 加成 本身不造成伤害
	
} 
