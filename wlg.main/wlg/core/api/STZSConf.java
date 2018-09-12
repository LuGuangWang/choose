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
		}
	};
	//武将
	public final static Map<String,WuJiang> wjs = new HashMap<String,WuJiang>() {
		private static final long serialVersionUID = 2194861789096202457L;
		{
			put(WList.luxun.getName(),WList.luxun.reset());
			put(WList.lvmeng.getName(),WList.lvmeng.reset());
			put(WList.zhouyv.getName(),WList.zhouyv.reset());
			put(WList.zhaoyun.getName(),WList.zhaoyun.reset());
		}
	};
	
}
