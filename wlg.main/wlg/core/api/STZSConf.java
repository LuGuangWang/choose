package wlg.core.api;

import java.util.HashMap;
import java.util.Map;

import wlg.core.bean.wujiang.WList;
import wlg.core.bean.wujiang.WuJiang;
import wlg.core.bean.zhanfa.ZList;
import wlg.core.bean.zhanfa.ZhanFa;

public abstract class STZSConf {
	//战法
	public final static Map<String,ZhanFa> zfs = new HashMap<String,ZhanFa>() {
		private static final long serialVersionUID = -4123005129676138606L;
		{
			put(ZList.shimianmaifu.getName(),ZList.shimianmaifu);
			put(ZList.shengdongjixi.getName(),ZList.shengdongjixi);
			put(ZList.chugesiqi.getName(),ZList.chugesiqi);
			put(ZList.shenbingtianjiang.getName(),ZList.shenbingtianjiang);
			put(ZList.bugong.getName(),ZList.bugong);
			put(ZList.shenmouyuanlv.getName(),ZList.shenmouyuanlv);
			put(ZList.hunshuimoyv.getName(),ZList.hunshuimoyv);
			put(ZList.fanjizhice.getName(),ZList.fanjizhice);
			put(ZList.jijiaozhishi.getName(),ZList.jijiaozhishi);
			put(ZList.huima.getName(),ZList.huima);
			put(ZList.dashangsanjun.getName(),ZList.dashangsanjun);
			put(ZList.zhanbiduanjin.getName(),ZList.zhanbiduanjin);
			put(ZList.yaoshu.getName(),ZList.yaoshu);
			put(ZList.shiji.getName(),ZList.shiji);
			put(ZList.qingnangmiyao.getName(),ZList.qingnangmiyao);
			put(ZList.mizhen.getName(),ZList.mizhen);
			put(ZList.dunbingcuorui.getName(),ZList.dunbingcuorui);
			put(ZList.xingbingzhiji.getName(),ZList.xingbingzhiji);
			put(ZList.yanfenjizhen.getName(),ZList.yanfenjizhen);
			put(ZList.shuiyanqijun.getName(),ZList.shuiyanqijun);
			put(ZList.shengbingqiuzhan.getName(),ZList.shengbingqiuzhan);
			put(ZList.heliu.getName(),ZList.heliu);
			put(ZList.zengyuan.getName(),ZList.zengyuan);
			put(ZList.weiyakunjun.getName(),ZList.weiyakunjun);
			put(ZList.yijidangqian.getName(),ZList.yijidangqian);
			put(ZList.qiexinduozhi.getName(),ZList.qiexinduozhi);
			put(ZList.luolei.getName(),ZList.luolei);
		}
	};
	//武将  不要忘记 reset方法
	public final static Map<String,WuJiang> wjs = new HashMap<String,WuJiang>() {
		private static final long serialVersionUID = 2194861789096202457L;
		{
			put(WList.luxun.getName(),WList.luxun.reset());
			put(WList.lvmeng.getName(),WList.lvmeng.reset());
			put(WList.zhouyv.getName(),WList.zhouyv.reset());
			put(WList.zhaoyun.getName(),WList.zhaoyun.reset());
			put(WList.huangyueying.getName(),WList.huangyueying.reset());
			put(WList.xunyv.getName(),WList.xunyv.reset());
			put(WList.zhangchunhua.getName(),WList.zhangchunhua.reset());
			put(WList.caocao.getName(),WList.caocao.reset());
			put(WList.caopi.getName(),WList.caopi.reset());
			put(WList.wangyi.getName(),WList.wangyi.reset());
			put(WList.guojia.getName(),WList.guojia.reset());
			put(WList.handiaochan.getName(),WList.handiaochan.reset());
			put(WList.mayunlu.getName(),WList.mayunlu.reset());
			put(WList.zhangning.getName(),WList.zhangning.reset());
			put(WList.lingdi.getName(),WList.lingdi.reset());
			put(WList.hetaihou.getName(),WList.hetaihou.reset());
			put(WList.xiaoguojia.getName(),WList.xiaoguojia.reset());
			put(WList.xiaozhujun.getName(),WList.xiaozhujun.reset());
			put(WList.xiaoganning.getName(),WList.xiaoganning.reset());
			put(WList.xiaolingtong.getName(),WList.xiaolingtong.reset());
			put(WList.xiahouyuan.getName(),WList.xiahouyuan.reset());
			put(WList.zhangliao.getName(),WList.zhangliao.reset());
			put(WList.machao.getName(),WList.machao.reset());
			put(WList.xunyou.getName(),WList.xunyou.reset());
			put(WList.buzhuge.getName(),WList.buzhuge.reset());
			put(WList.xushu.getName(),WList.xushu.reset());
			put(WList.pangtong.getName(),WList.pangtong.reset());
			put(WList.gongzhuge.getName(),WList.gongzhuge.reset());
			put(WList.lvbu.getName(),WList.lvbu.reset());
		}
	};
	
}
