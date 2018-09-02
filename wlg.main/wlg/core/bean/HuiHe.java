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
	private boolean hasJiaCheng = false;
	
	//本回合刷新战法伤害值
	private float shuaxinVal = 0.0f;
	//本回合攻击提高伤害值
	private float upGongJiVal = 1.0f;
	//本回合控制力
	private float kongzhiVal = 0.0f;
	//武将数 
	private int wujiangCount = 3;
	//全封闭
	private float fengAll = 2.0f;
	//封闭战法
	private float fengZhanfa = 2.0f;
	//封闭普攻
	private float fengGongji = 2.0f;
	//封战法 也封攻击
	public HuiHe getAllFeng(float jsRate,int person) {
		HuiHe huihe = this.clone();
		huihe.fengAll = jsRate * person;
		return huihe;
	}
	//封攻击
	public HuiHe getFengGongji(float jsRate,int person) {
		HuiHe huihe = this.clone();
		huihe.fengGongji = jsRate * person;
		return huihe;
	}
	//封攻击
	public HuiHe getFengZhanfa(float jsRate,int person) {
		HuiHe huihe = this.clone();
		huihe.fengZhanfa = jsRate * person;
		return huihe;
	}
	
	public float getUpGongJiVal() {
		return upGongJiVal;
	}
	public void setUpGongJiVal(float upGongJiVal) {
		this.upGongJiVal = upGongJiVal;
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
		Conf.log("=====本回合参与战斗武将数："+this.wujiangCount);
	}
	public float getShuaxinVal() {
		return shuaxinVal;
	}
	public float getKongzhiVal() {
		return kongzhiVal>1?1.0f:kongzhiVal;
	}
	public void setKongzhiVal(float kongzhiVal) {
		this.kongzhiVal = kongzhiVal;
	}
	public void addKongzhiVal(float kongzhiVal) {
		this.kongzhiVal += kongzhiVal;
	}
	public void setShuaxinVal(float shuaxinVal) {
		this.shuaxinVal = shuaxinVal;
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
	public boolean isHasJiaCheng() {
		return hasJiaCheng;
	}
	public void setHasJiaCheng(boolean hasJiaCheng) {
		this.hasJiaCheng = hasJiaCheng;
	}
	public int getId() {
		return id;
	}
	
	public float getKongZhiSolderVal(int position,float defenseVal) {
		float sunShi = position * Conf.SunShiCount;
		
		float kzss = Conf.SunShiCount * this.getKongzhiVal();
		sunShi -= kzss;
		Conf.log("======本回合最终控制力：" + this.getKongzhiVal() + " 避免士兵损失值：" + kzss);
		
		//防御是防御攻击造成的伤害
		float fangyuVal = Conf.SunShiCount * 0.5f * Conf.fg_rate * defenseVal;
		sunShi -= fangyuVal;
		sunShi *= id;
		Conf.log("======本回合最终防御力避免士兵损失值："+fangyuVal+ " 本回合最终士兵损失值：" + sunShi);
		
		boolean isDied = (sunShi< Conf.totalCount)?false:true;
		return isDied?0.0f:1.0f;
	}
	
	/**
	 * 自身士兵损失
	 * @param position 武将位置
	 * @param defenseVal 防御属性值
	 * @return
	 */
	public float getSolderRate(int position,float defenseVal) {
		float sunShi = position * Conf.SunShiCount;
		float kzss = 0.0f;
		if(fengAll<=1) {
			kzss = Conf.SunShiCount * fengAll;
			Conf.log("===全控制减伤值：" + fengAll + " 避免士兵损失值：" + kzss);
		}else if(fengZhanfa <= 1) {
			kzss = Conf.SunShiCount * fengZhanfa * Conf.dk_rate;
			Conf.log("===控制法术减伤值：" + fengZhanfa +" 避免士兵损失值：" + kzss);
		}else if(fengGongji <= 1) {
			kzss = Conf.SunShiCount * fengGongji * Conf.dk_rate;
			Conf.log("===控制攻击减伤值：" + fengGongji + " 避免士兵损失值：" + kzss);
		}
		sunShi -= kzss;
		//防御是防御攻击造成的伤害
		float fangyuVal = Conf.SunShiCount * 0.5f * Conf.fg_rate * defenseVal;
		sunShi -= fangyuVal;
		sunShi *= id;
		Conf.log("======本回合防御力避免士兵损失值："+fangyuVal + " 本回合士兵损失值：" + sunShi);
		boolean isDied = (sunShi< Conf.totalCount)?false:true;
		return isDied?0.0f:1.0f;
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
