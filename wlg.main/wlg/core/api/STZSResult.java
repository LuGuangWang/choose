package wlg.core.api;

import java.io.Serializable;

public class STZSResult implements Serializable{

	private static final long serialVersionUID = -839490471114211273L;

	private String desc ="伤害值：";
	private float harmval = 0.0f;
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public float getHarmval() {
		return harmval;
	}
	public void setHarmval(float harmval) {
		this.harmval = harmval;
	}
}
