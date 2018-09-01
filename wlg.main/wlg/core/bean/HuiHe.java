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
	
	//本回合刷新战法伤害率
	private float shuaxinRate = 0.0f;
	//武将数 
	private int wujiangCount = 3;
	//全封闭
	private float fengAll = 2;
	//封闭战法
	private float fengZhanfa = 2;
	//封闭普攻
	private float fengGongji = 2;
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
	public boolean isHasJiaCheng() {
		return hasJiaCheng;
	}
	public void setHasJiaCheng(boolean hasJiaCheng) {
		this.hasJiaCheng = hasJiaCheng;
	}
	public int getId() {
		return id;
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
		boolean isDied = (sunShi * id< Conf.totalCount)?false:true;
		Conf.log("======本回合防御力："+fangyuVal + " 避免士兵损失值：" + sunShi);
		return isDied?0:1;
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
