package wlg.core.bean;

import wlg.core.bean.conf.Conf;

/**
 * 回合
 * @author seven
 *
 */
public class HuiHe implements Cloneable{
	private int id = 1;
	
	//TODO 将此参数根据武将防御属性获取
	//额外属性
	private boolean hasZengYi = false;
	private boolean hasKongZhi = false;
	private boolean hasBuGong = false;
	private float shuaxinRate = 0.0f;
	private int wujiangCount = 3;
	//全封闭
	private float fengAll = 2;
	//封闭战法
	private float fengZhanfa = 2;
	//封闭普攻
	private float fengGongji = 2;
	//封战法 也封攻击
	public HuiHe getAllFeng(float jsRate) {
		HuiHe huihe = this.clone();
		huihe.fengAll = jsRate;
		return huihe;
	}
	//封攻击
	public HuiHe getFengGongji(float jsRate) {
		HuiHe huihe = this.clone();
		huihe.fengGongji = jsRate;
		return huihe;
	}
	//封攻击
	public HuiHe getFengZhanfa(float jsRate) {
		HuiHe huihe = this.clone();
		huihe.fengZhanfa = jsRate;
		return huihe;
	}
	
	public boolean isHasBuGong() {
		return hasBuGong;
	}
	public void setHasBuGong(boolean hasBuGong) {
		this.hasBuGong = hasBuGong;
	}
	public int getWujiangCount() {
		return wujiangCount;
	}
	public void setWujiangCount(int wujiangCount) {
		this.wujiangCount = wujiangCount;
	}
	public float getShuaxinRate() {
		return shuaxinRate;
	}
	public void setShuaxinRate(float shuaxinRate) {
		this.shuaxinRate = shuaxinRate;
	}
	public boolean isHasZengYi() {
		return hasZengYi;
	}
	public void setHasZengYi(boolean hasZengYi) {
		this.hasZengYi = hasZengYi;
	}
	public boolean isHasKongZhi() {
		return hasKongZhi;
	}
	public void setHasKongZhi(boolean hasKongZhi) {
		this.hasKongZhi = hasKongZhi;
	}
	public int getId() {
		return id;
	}
	public float getSolderRate(int position) {
		float sunShi = position * Conf.SunShiCount;
		if(fengAll<=1) {
			sunShi -= Conf.SunShiCount * fengAll;
		}else if(fengZhanfa <= 1) {
			sunShi -= Conf.SunShiCount * fengZhanfa * 0.5f;
		}else if(fengGongji <= 1) {
			sunShi -= Conf.SunShiCount * fengGongji * 0.5f;
		}
		return sunShi * id< Conf.totalCount?1:0;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public HuiHe clone() {
		HuiHe o = null;
		try {
			o = (HuiHe) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
}
