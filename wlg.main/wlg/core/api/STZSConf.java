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
			put(ZList.shimianmaifu.getName(),ZList.shimianmaifu.clone());
			put(ZList.shengdongjixi.getName(),ZList.shengdongjixi.clone());
			put(ZList.chugesiqi.getName(),ZList.chugesiqi.clone());
			put(ZList.shenbingtianjiang.getName(),ZList.shenbingtianjiang.clone());
			put(ZList.bugong.getName(),ZList.bugong.clone());
			put(ZList.shenmouyuanlv.getName(),ZList.shenmouyuanlv.clone());
			put(ZList.hunshuimoyv.getName(),ZList.hunshuimoyv.clone());
			put(ZList.fanjizhice.getName(),ZList.fanjizhice.clone());
			put(ZList.jijiaozhishi.getName(),ZList.jijiaozhishi.clone());
		}
	};
	//武将
	public final static Map<String,WuJiang> wjs = new HashMap<String,WuJiang>() {
		private static final long serialVersionUID = 2194861789096202457L;
		{
			put(WList.luxun.getName(),WList.luxun.clone());
			put(WList.lvmeng.getName(),WList.lvmeng.clone());
			put(WList.zhouyv.getName(),WList.zhouyv.clone());
		}
	};
	
}
