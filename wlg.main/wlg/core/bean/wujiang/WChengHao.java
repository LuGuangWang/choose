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
	//东吴大都督
	public static final Map<String,Integer> dwddd = new HashMap<>();
	
	public static WCType checkChengHao(String wjName) {
		WCType res = WCType.none;
		if(dongwudadudu.contains(wjName)) {
			dwddd.put(wjName, 1);
		}
		if(dwddd.size()==3) {
			dwddd.clear();
			res = WCType.dongwudadudu;
		}
		return res;
	}
	
	
}
