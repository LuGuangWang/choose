package wlg.core.bean.zhanfa;

public enum ZFType {
	ZhuDong_FaShu,//主动类 法术伤害
	ZhuDong_BaoZou,//主动类 暴走
	ZhuDong_GongJi,//主动类 攻击伤害
	ZhuDong_JianGongJi,//主动类 减攻击伤害
	ZhuDong_JiaShuXing,//主动类 加属性
	ZhuDong_KongZhi_ALL,//控制攻击与法术,本身不造成伤害
	ZhuDong_FaShu_ZiDai,//主动类 法术伤害 武将自带战法
	ZhuDong_QunTi_HuiFu,//主动类 群体类 恢复
	ZhuDong_GongJi_YiJi,//主动类 一骑当千
	ZhuDong_FaShu_GongJi,//主动类 攻击伤害 法术伤害
	ZhuDong_FaShu_BaoZou,//主动类 法术伤害 暴走效果
	ZhuDong_FaShu_HunLuan,//主动类 法术伤害 混乱
	ZhuDong_FaShu_KeepHuiHe,//主动类 法术伤害 持续回合
	ZhuDong_FaShu_JianShang,//主动类 法术伤害 减伤
	ZhuDong_Multiple_KongZhi,//主动类 多重控制
	ZhuDong_JiaGongJi_LianJi,//主动类 加攻击 然后连击
	ZhuDong_FaShu_jianGongJi,//主动类 法术伤害 降低攻击属性
	ZhuDong_ShouCi_JiaGongJi,//主动类 提高首次攻击伤害
	ZhuDong_BaoZou_jianFangYu,//主动类 暴走 降低防御属性
	ZhuDong_JianShang_JiaShang,//主动类 减伤 加伤战法
	ZhuDong_JiaShuXing_KongZhi,//主动类 加属性点 控制战法
	ZhuDong_FaShu_KongZhiHuiFu,//主动类 法术伤害 控制恢复
	ZhuDong_ZiSheng_YouJun_HuiFu,//主动类 自身和友军恢复
	ZhuDong_JianShang_KongZhiFaShu,//主动类 减伤 然后控制法术,本身不造成伤害
	ZhuDong_jianshang_konghuang_zuzhou,//主动类 减伤 恐慌 妖术诅咒
	ZhuDong_FaShuShangHai_KongZhiGongji,//主动类 先法术伤害 然后控制攻击
	ZhuDong_YiChu_GuiBi_DongCha_XianShou,//主动类 移除有害 进入规避，洞察，先手
	
	
	ZhiHui_FuZhu_ALL,//指挥类 增加法术和攻击伤害
	ZhiHui_KongZhiGongJi,//指挥类 控制攻击
	ZhiHui_Multiple_FaShu,//指挥类 多种伤害效果
	ZhiHui_GuiBi_JianShang,//指挥类 规避伤害 降低伤害
	ZhiHui_YouXian_DongYao,//指挥类 优先 动摇
	ZhiHui_MianYi_jiagongji,//指挥类 免疫控制 加攻击
	ZhiHui_MianYi_WushiGuiBi,//指挥类 免疫控制 无视规避
	ZhiHui_JiaSuDu_JiaPuGong,//指挥类 加速度，两次普攻
	ZhiHui_JiaShuXing_JiaJuLi,//指挥类 加属性 加距离
	ZhiHui_SkipReady_Jiashang,//指挥类 跳过准备 下一次攻击加伤害
	ZhiHui_JianMouLue_KongZhiFaShu,//指挥类 降低谋略属性 控制法术
	ZhiHui_DaYing_ZhongJun_QianFeng,//指挥类 大营加概率，中军加伤害，前锋减伤
	ZhiHui_JiaFaShu_JianShang_MianYi,//指挥类 加攻击或法术伤害 减攻击或法术伤害 免疫被控制
	ZhiHui_JiaJuLi_FenBing_KongHuang,//指挥类 加攻击距离 分兵 恐慌
	ZhiHui_KongZhiGongJi_FaShuShangHai,//指挥类 先控制攻击 然后法术伤害
	ZhiHui_JianshangFashu_KongZhiFaShu,//指挥类  降低法术伤害 控制法术伤害
	ZhiHui_FaShuJiacheng_FaShuGongJi_BuGongJi,//指挥类 法术加成 法术攻击 本身不造成攻击伤害
	
	BeiDong_huifu,//被动类 恢复
	BeiDong_GongJi,//被动类，攻击伤害
	BeiDong_JiaCheng,//被动类 加成 本身不造成伤害
	BeiDong_WuFS_JiaGongJi,//被动类 无法发动主动战法 加攻击
	BeiDong_LianJi_jiagongji,//被动类 连击 增加攻击属性
	
	ZhuiJi_FaShu,//追击类 法术伤害
	ZhuiJi_GongJi_FengFaShu,//追击类
	ZhuiJi_GongJi_KongZhiGongJi,//追击类 攻击伤害
	ZhuiJi_FaShu_BaoZou_JiaFaShu,//追击类 法术伤害 暴走 加自身策略
} 
