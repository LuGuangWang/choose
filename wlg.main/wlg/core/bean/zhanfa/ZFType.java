package wlg.core.bean.zhanfa;

public enum ZFType {
	ZhuDong_FaShu,//主动类 法术伤害
	ZhuDong_FaShuShangHai_KongZhiGongji,//主动类 先法术伤害 然后控制攻击
	ZhiHui_KongZhiGongJi_FaShuShangHai,//指挥类 先控制攻击 然后法术伤害
	
	KongZhi_ALL,//控制攻击与法术,不造成伤害
} 
