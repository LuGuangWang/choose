package wlg.core.bean.wujiang;

import java.util.HashMap;
import java.util.Map;

/**
 * 武将称号
 * @author seven
 *
 */
public class WChengHao {
	//东吴大都督
	public static final String dongwudadudu = "陆逊|周瑜|吕蒙";
	//魏之智
	public static final String weizhizhi = "小郭嘉|郭嘉|荀彧|SP荀攸";
	//东吴大都督
	public static final Map<String,Integer> dwddd = new HashMap<>();
	//魏之智
	public static final Map<String,Integer> wzz = new HashMap<>();
	
	public static WCType checkChengHao(String wjName) {
		WCType res = WCType.none;
		if(dongwudadudu.contains(wjName)) {
			dwddd.put(wjName, 1);
		}
		if(dwddd.size()==3) {
			dwddd.clear();
			res = WCType.dongwudadudu;
		}
		if(weizhizhi.contains(wjName)) {
			wzz.put(wjName, 1);
		}
		if(wzz.size()==3) {
			wzz.clear();
			res = WCType.wzz;
		}
		
		return res;
	}
	//重置称号设置
	public static void reset() {
		dwddd.clear();
		wzz.clear();
	}
	
}
