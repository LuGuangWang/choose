package wlg.core.bean.zhanfa;

import java.util.ArrayList;
import java.util.List;

public class ConflictList {
	private final static ConflictList instance = new ConflictList();
	private ConflictList() {}
	
	private final String celue_chongtu = "不攻|大赏三军";
	private List<ZhanFa> celue_list = new ArrayList<>();
	
	public static ConflictList $() {
		return instance;
	}
	
	public boolean isCeluechongtu() {
		return celue_list.size()>1;
	}

	public void checkChongTu(ZhanFa zf) {
		if(celue_chongtu.contains(zf.getName())) {
			celue_list.add(zf);
		}
	}

	public void reset() {
		celue_list.clear();
	}
}
