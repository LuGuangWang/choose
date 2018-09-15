package wlg.core.bean;

import java.util.ArrayList;
import java.util.List;

import wlg.core.bean.conf.Conf;
import wlg.core.bean.wujiang.WuJiang;

/**
 * 回合
 * @author seven
 *
 */
public class HuiHe implements Cloneable{
	private int id = 1;
	//所有武将
	private List<WuJiang> wujiangs = new ArrayList<>();
	//当前武将
	private WuJiang wj;
	
	//TODO 将此参数根据武将防御属性获取
	//额外属性
	private boolean hasZengYi = false;
	private boolean hasKongZhi = false;
	private boolean hasBuGong = false;
	private boolean hasZiShenJiaCheng = false;
	
	//TODO 连击概率 发动概率 * 作用到当前人
	private float lianjiVal = 0.0f;
	//本回合刷新战法伤害值
	private float shuaxinVal = 0.0f;
	//本回合攻击提高伤害值
	private float upGongJiVal = 1.0f;
	//本回合控制力
	private float kongzhiVal = 0.0f;
	//武将数 
	private int wujiangCount = 3;
	//全封闭
	private float fengAll = 0.0f;
	//封闭战法
	private float fengZhanfa = 0.0f;
	//封闭普攻
	private float fengGongji = 0.0f;
	//被封攻击的人数
	private int fengGJP = 0;
	//封战法 也封攻击
	public HuiHe getAllFeng(float jsRate) {
		HuiHe huihe = this.clone();
		huihe.fengAll = jsRate;
		huihe.fengZhanfa = 0.0f;
		huihe.fengGongji = 0.0f;
		return huihe;
	}
	
	//封战法 也封攻击
	public HuiHe getAllFeng(float fashuRate,float gongjiRate) {
		HuiHe huihe = this.clone();
		huihe.fengZhanfa = fashuRate;
		huihe.fengGongji = gongjiRate;
		huihe.fengAll = 0.0f;
		return huihe;
	}
	
	//封攻击 概率和人数
	public HuiHe getFengGongji(float jsRate,int fengGJP) {
		HuiHe huihe = this.clone();
		huihe.fengGongji = jsRate;
		huihe.fengGJP = fengGJP;
		
		huihe.fengAll = 0.0f;
		huihe.fengZhanfa = 0.0f;
		return huihe;
	}
	//封法术
	public HuiHe getFengZhanfa(float jsRate) {
		HuiHe huihe = this.clone();
		huihe.fengZhanfa = jsRate;
		huihe.fengAll = 0.0f;
		huihe.fengGongji = 0.0f;
		return huihe;
	}
	
	public float getFengGongji() {
		if(fengAll!=0) {
			return fengAll;
		}
		return fengGongji;
	}
	public int getFengGJP() {
		return fengGJP;
	}
	public WuJiang getWj() {
		return wj;
	}
	public void setWj(WuJiang wj) {
		this.wj = wj;
	}
	public List<WuJiang> getWujiangs() {
		return wujiangs;
	}
	public void setWujiangs(List<WuJiang> wujiangs) {
		this.wujiangs = wujiangs;
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
	public boolean isHasZiShenJiaCheng() {
		return hasZiShenJiaCheng;
	}
	public void setHasZiShenJiaCheng(boolean hasZiShenJiaCheng) {
		this.hasZiShenJiaCheng = hasZiShenJiaCheng;
	}
	public int getId() {
		return id;
	}
	/**
	 * 武将损失
	 * @param wj
	 */
	public boolean removeWujiang(WuJiang wj) {
		boolean isRemove = false;
		Conf.log("=========检查武将"+wj.getName()+"是否有损失===========");
		float left = wj.getTotalCount() - wj.getSunshiCount();
		wj.setTotalCount(left);
		Conf.log("=====武将"+wj.getName()+"剩余兵力:"+left);
		boolean isDied = (left>0)?false:true;
		if(isDied && wujiangs.contains(wj)) {
			isRemove = true;
			//损失大营
			if(wj.getPosition()==1) {
				Conf.log("=====第"+id+"回合损失大营武将"+wj.getName());
				this.wujiangs.clear();
			}else {
				Conf.log("=====第"+id+"回合损失武将"+wj.getName());
				this.wujiangs.remove(wj);
				//损失中军
				if(wj.getPosition()==2) {
					this.wujiangs.forEach(v->{
						if(v.getFinalp()==1) {
							v.setPosition(v.getPosition()+1);
						}
					});
				}
				//损失前锋
				if(wj.getPosition()==3) {
					this.wujiangs.forEach(v->{
						v.setPosition(v.getPosition()+1);
					});
				}
			}
		}
		return isRemove;
	}
	/**
	 * 自身士兵损失
	 * @param position 武将位置
	 * @param defenseVal 防御属性值
	 * @return
	 */
	public float getSolderRate(int position,float defenseVal) {
		//设置每回合的兵力损失
		getSunShi(wj.getPosition(),wj.getDefense());
		
		return Conf.binglijishu/id;
	}
	/**
	 * 自身士兵损失值
	 * @param position
	 * @param defenseVal
	 * @return
	 */
	private float getSunShi(int position, float defenseVal) {
		float sunShi = 0.0f;
		if(fengAll!=0) {
			float sunshi = Conf.SunShiCount* position - Conf.SunShiCount * fengAll;
			sunshi = sunshi>0?sunshi:0;
			Conf.log("===全控制减伤值：" + fengAll + " 士兵损失值：" + sunshi);
			sunShi += sunshi;
		}
		if(fengZhanfa !=0) {
			float sunshi =Conf.SunShiCount* position - Conf.SunShiCount * fengZhanfa * Conf.sf_s_rate;
			sunshi = sunshi>0?sunshi:0;
			Conf.log("===控制法术减伤值：" + fengZhanfa +" 士兵损失值：" + sunshi);
			sunShi += sunshi;
		}
		if(fengGongji !=0) {
			float sunshi = Conf.SunShiCount* position - Conf.SunShiCount * fengGongji * Conf.gj_s_rate;
			sunshi = sunshi>0?sunshi:0;
			Conf.log("===控制攻击减伤值：" + fengGongji + " 士兵损失值：" + sunshi);
			sunShi += sunshi;
		}
		//法术伤害
		if(fengAll==0 && fengZhanfa==0) {
			float fangyuVal = Conf.SunShiCount * Conf.sf_s_rate;
			fangyuVal *= position;
			Conf.log("==敌军法术造成士兵的损失值：" + fangyuVal);
			sunShi += fangyuVal;
		}
		//防御是防御攻击造成的伤害
		if(fengAll==0 && fengGongji ==0) {
			float fangyuVal = Conf.SunShiCount * Conf.gj_s_rate - Conf.fg_rate * defenseVal;
			fangyuVal = fangyuVal>0?fangyuVal:0;
			fangyuVal *= position;
			Conf.log("===防御力减伤值：" + defenseVal + " 敌军普通攻击造成的士兵损失值：" + fangyuVal);
			sunShi += fangyuVal;
		}
		//按受到最小伤害进行更新
		this.getWj().setSunshiCount(sunShi);
		
		Conf.log("============本回合士兵损失值：" + sunShi);
		return sunShi;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getLianjiVal() {
		return lianjiVal;
	}
	public void setLianjiVal(float lianjiVal) {
		this.lianjiVal = lianjiVal;
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
