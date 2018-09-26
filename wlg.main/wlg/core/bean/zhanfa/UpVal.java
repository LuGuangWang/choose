package wlg.core.bean.zhanfa;

public class UpVal {

	private float addStrategyVal = 0.0f;//策略属性提高比
	private float addQuanShuXingVal = 0.0f;//策略属性提高值
	
	//行兵之极 大营加战法发动概率
	private float dayingUpZFVal = 0.0f;
	//行兵之极 中军增加伤害
	private float zhongjunUpVal = 0.0f;
	//行兵之极 前锋降低伤害
	private float qianfengUpVal = 0.0f;
	
	public float getAddStrategyVal() {
		return addStrategyVal;
	}
	public void setAddStrategyVal(float addStrategyVal) {
		this.addStrategyVal = addStrategyVal;
	}
	public float getAddQuanShuXingVal() {
		return addQuanShuXingVal;
	}
	public void setAddQuanShuXingVal(float addQuanShuXingVal) {
		this.addQuanShuXingVal = addQuanShuXingVal;
	}
	public float getDayingUpZFVal() {
		return dayingUpZFVal;
	}
	public void setDayingUpZFVal(float dayingUpZFVal) {
		this.dayingUpZFVal = dayingUpZFVal;
	}
	public float getZhongjunUpVal() {
		return zhongjunUpVal;
	}
	public void setZhongjunUpVal(float zhongjunUpVal) {
		this.zhongjunUpVal = zhongjunUpVal;
	}
	public float getQianfengUpVal() {
		return qianfengUpVal;
	}
	public void setQianfengUpVal(float qianfengUpVal) {
		this.qianfengUpVal = qianfengUpVal;
	}
}
