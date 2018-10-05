package wlg.core.bean.zhanfa;

import java.util.HashSet;
import java.util.Set;

import wlg.core.bean.conf.Conf;

public class ConflictList {
	private final static ConflictList instance = new ConflictList();
	private ConflictList() {}
	
	private final String celue_chongtu = "不攻|大赏三军|始计";
	private Set<String> celue_list = new HashSet<>();
	
	private final String baozou_chongtu = "妖术|奇佐鬼谋|闭月|迷阵";
	private Set<String> baozou_list = new HashSet<>();
	
	private final String zhihui_konghuang_chongtu = "匠心不竭|帝临回光";
	private Set<String> zhihui_konghuang_list = new HashSet<>();
	
	private final String celue_jiangshang_chongtu = "始计|母仪浮梦";
	private Set<String> celue_jiangshang_list = new HashSet<>();
	
	public static ConflictList $() {
		return instance;
	}
	
	public boolean isCelueJianShangchongtu() {
		boolean chongtu = false;
		if(chongtu = celue_jiangshang_list.size()>1) {
			Conf.log("=========战法冲突：" + listToString(celue_jiangshang_list));
		}
		return chongtu;
	}
	
	public boolean isCeluechongtu() {
		boolean chongtu = false;
		if(chongtu = celue_list.size()>1) {
			Conf.log("=========战法冲突：" + listToString(celue_list));
		}
		return chongtu;
	}
	
	public boolean isZhiHuiKonghuangchongtu() {
		boolean chongtu = false;
		if(chongtu = zhihui_konghuang_list.size()>1) {
			Conf.log("=========战法冲突：" + listToString(zhihui_konghuang_list));
		}
		return chongtu;
	}
	
	public float baozouChongTuRate() {
		float rate = 1.0f;
		if(baozou_list.size()>1) {
			rate = 1.0f/baozou_list.size();
			Conf.log("=========战法冲突：" + listToString(baozou_list));
		}
		return rate;
	}
	
	public void checkChongTu(ZhanFa zf) {
		if(celue_chongtu.contains(zf.getName())) {
			celue_list.add(zf.getName());
		}
		if(baozou_chongtu.contains(zf.getName())) {
			baozou_list.add(zf.getName());
		}
		if(zhihui_konghuang_chongtu.contains(zf.getName())) {
			zhihui_konghuang_list.add(zf.getName());
		}
		if(celue_jiangshang_chongtu.contains(zf.getName())) {
			celue_jiangshang_list.add(zf.getName());
		}
	}

	private String listToString(Set<String> zfs) {
		StringBuilder res = new StringBuilder();
		zfs.forEach(zf->res.append(zf).append("|"));
		return res.toString();
	}
	
	public void reset() {
		celue_list.clear();
		baozou_list.clear();
		zhihui_konghuang_list.clear();
	}
}
