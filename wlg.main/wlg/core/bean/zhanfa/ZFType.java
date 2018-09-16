package wlg.core.bean.zhanfa;

public enum ZFType {
	ZhuDong_FaShu,//主动类 法术伤害
	ZhuDong_GongJi,//主动类 攻击伤害
	ZhuDong_KongZhi_ALL,//控制攻击与法术,本身不造成伤害
	ZhuDong_FaShu_GongJi,//主动类 攻击伤害 法术伤害
	ZhuDong_FaShu_JianShang,//主动类 法术伤害 减伤
	ZhuDong_JiaGongJi_LianJi,//主动类 加攻击 然后连击
	ZhuDong_JiaShuXing_KongZhi,//主动类 加属性点 控制战法
	ZhuDong_JianShang_KongZhiFaShu,//主动类 减伤 然后控制法术,本身不造成伤害
	ZhuDong_FaShuShangHai_KongZhiGongji,//主动类 先法术伤害 然后控制攻击
	
	ZhiHui_FuZhu_ALL,//指挥类 增加法术和攻击伤害
	ZhiHui_KongZhiGongJi,//指挥类 控制攻击
	ZhiHui_Multiple_FaShu,//指挥类 多种伤害效果
	ZhiHui_JiaShuXing_JiaJuLi,//指挥类 加属性 加距离
	ZhiHui_KongZhiGongJi_FaShuShangHai,//指挥类 先控制攻击 然后法术伤害
	ZhiHui_JianshangFashu_KongZhiFaShu,//指挥类  降低法术伤害 控制法术伤害
	ZhiHui_FaShuJiacheng_FaShuGongJi_BuGongJi,//指挥类 法术加成 法术攻击 本身不造成攻击伤害
	
	BeiDong_JiaCheng,//被动类 加成 本身不造成伤害
	BeiDong_GongJi,//被动类，攻击伤害
	
	ZhuiJi_FaShu,//追击类 法术伤害
	
} 
