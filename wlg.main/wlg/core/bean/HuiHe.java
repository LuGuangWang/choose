package wlg.core.bean;

import java.util.ArrayList;
import java.util.List;

import wlg.core.bean.conf.Conf;
import wlg.core.bean.wujiang.WuJiang;
import wlg.core.bean.zhanfa.ZhanFa;

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
	
	//TODO 连击概率 发动概率 * 作用到当前人
	private float lianjiVal = 1.0f;
	//策略属性提高比
	private float upFaShuVal = 1.0f;
	//全属性提高值
	private float upQuanShuXing = 0.0f;
	//策略伤害提高值
	private float upFaShaShangHaiVal = 0.0f;
	//增加所有武将的攻击和谋略伤害比
	private float upAllWjVal = 0.0f;
	//防御属性值降低值
	private float downFangYuVal = 0.0f;
	//本回合刷新战法伤害值
	private float shuaxinVal = 0.0f;
	//本回合刷新战法的位置
	private int shuaxinPos;
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
	//控制是否是 数值计算
	private boolean isShuZhi = false;	
	//规避伤害
	private float guibiVal = 0.0f;
	//行兵之极是否生效
	private boolean isxingbing = false;
	//行兵之极 大营加战法发动概率
	private float dayingUpZFVal;
	//行兵之极 中军增加伤害
	private float zhongjunUpVal;
	//行兵之极 前锋降低伤害
	private float qianfengUpVal;
	/** 胜兵求战 begin**/
	private float skipReadyVal = 0.0f;
	private int skipReadyPos = 0;
	//TODO 胜兵求战的上一回合
	private float prevZhuDongRate = 0.0f;//上一回合主动战法发动的概率
	private float shengbingUpVal = 0.0f;
	/** 胜兵求战 end**/
	
	//每个武将的恢复率
	private float zishenHuifuVal = 0.0f;
	private int zishenHuifuPos = 0;
	private float huifuVal = 0.0f;
	//每个武将先手的概率
	private float xianshouRate = 0.0f;
	//提高自身攻击伤害
	private int zishenUpGjPos = 0;
	private float zishenUpGjRate = 0.0f;//提高自身攻击伤害比
	
	//封战法 也封攻击
	public HuiHe getAllFeng(float jsRate) {
		HuiHe huihe = this.clone();
		huihe.fengAll = jsRate;
		
		huihe.isShuZhi = false;
		huihe.fengZhanfa = 0.0f;
		huihe.fengGongji = 0.0f;
		return huihe;
	}
	
	//规避伤害
	public HuiHe getGuiBi(float guibiVal,float kongzhiALl) {
		HuiHe huihe = this.clone();
		huihe.guibiVal = guibiVal;
		huihe.fengAll = kongzhiALl;
		
		huihe.isShuZhi = false;
		huihe.fengZhanfa = 0.0f;
		huihe.fengGongji = 0.0f;
		return huihe;
	}
	
	//封战法 也封攻击
	public HuiHe getAllFeng(float fengVal,boolean isShuZhi) {
		HuiHe huihe = this.clone();
		huihe.fengAll = fengVal;
		huihe.isShuZhi = isShuZhi;
		
		huihe.fengZhanfa = 0.0f;
		huihe.fengGongji = 0.0f;
		return huihe;
	}
	
	//封战法 也封攻击
	public HuiHe getAllFeng(float fashuRate,float gongjiRate) {
		HuiHe huihe = this.clone();
		huihe.fengZhanfa = fashuRate;
		huihe.fengGongji = gongjiRate;
		
		huihe.isShuZhi = false;
		huihe.fengAll = 0.0f;
		return huihe;
	}
	//封攻击 概率和人数
	public HuiHe getFengGongji(float jsRate,boolean isShuZhi) {
		HuiHe huihe = this.clone();
		huihe.fengGongji = jsRate;
		huihe.isShuZhi = isShuZhi;
		
		huihe.fengAll = 0.0f;
		huihe.fengZhanfa = 0.0f;
		return huihe;
	}
	
	//封攻击 概率和人数
	public HuiHe getFengGongji(float jsRate) {
		HuiHe huihe = this.clone();
		huihe.fengGongji = jsRate;
		
		huihe.isShuZhi = false;
		huihe.fengAll = 0.0f;
		huihe.fengZhanfa = 0.0f;
		return huihe;
	}
	//封法术
	public HuiHe getFengZhanfa(float jsRate) {
		HuiHe huihe = this.clone();
		huihe.fengZhanfa = jsRate;
		
		huihe.isShuZhi = false;
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
	public int getZishenUpGjPos() {
		return zishenUpGjPos;
	}
	public void setZishenUpGjPos(int zishenUpGjPos) {
		this.zishenUpGjPos = zishenUpGjPos;
	}
	public float getZishenUpGjRate() {
		return zishenUpGjRate;
	}
	public void setZishenUpGjRate(float zishenUpGjRate) {
		this.zishenUpGjRate = zishenUpGjRate;
	}
	public WuJiang getWj() {
		return wj;
	}
	public float getXianshouRate() {
		return xianshouRate;
	}
	public void setXianshouRate(float xianshouRate) {
		this.xianshouRate = xianshouRate;
	}
	public boolean isIsxingbing() {
		return isxingbing;
	}
	public void setIsxingbing(boolean isxingbing) {
		this.isxingbing = isxingbing;
	}
	public float getUpFaShuVal() {
		return upFaShuVal;
	}
	public void setUpFaShuVal(float upFaShuVal) {
		this.upFaShuVal = upFaShuVal;
	}
	public float getDownFangYuVal() {
		return downFangYuVal;
	}
	public int getSkipReadyPos() {
		return skipReadyPos;
	}
	public float getZishenHuifuVal() {
		return zishenHuifuVal;
	}
	public void setZishenHuifuVal(float zishenHuifuVal) {
		this.zishenHuifuVal = zishenHuifuVal;
	}
	public void addZishenHuifuVal(float zishenHuifuVal) {
		this.zishenHuifuVal += zishenHuifuVal;
	}
	public int getZishenHuifuPos() {
		return zishenHuifuPos;
	}
	public void setZishenHuifuPos(int zishenHuifuPos) {
		this.zishenHuifuPos = zishenHuifuPos;
	}
	public void setSkipReadyPos(int skipReadyPos) {
		this.skipReadyPos = skipReadyPos;
	}
	public float getShengbingUpVal() {
		return shengbingUpVal;
	}
	public float getHuifuVal() {
		return huifuVal;
	}
	public void setHuifuVal(float huifuVal) {
		this.huifuVal = huifuVal;
	}
	public void addHuifuVal(float huifuVal) {
		this.huifuVal += huifuVal;
	}
	public void setShengbingUpVal(float shengbingUpVal) {
		this.shengbingUpVal = shengbingUpVal;
	}
	public void setDownFangYuVal(float downFangYuVal) {
		this.downFangYuVal = downFangYuVal;
	}
	public void setWj(WuJiang wj) {
		this.wj = wj;
	}
	public float getPrevZhuDongRate() {
		return prevZhuDongRate;
	}
	public void setPrevZhuDongRate(float prevZhuDongRate) {
		this.prevZhuDongRate = prevZhuDongRate;
	}
	public void addPrevZhuDongRate(float prevZhuDongRate) {
		this.prevZhuDongRate += prevZhuDongRate;
	}
	public float getSkipReadyVal() {
		return skipReadyVal;
	}
	public void setSkipReadyVal(float skipReadyVal) {
		this.skipReadyVal = skipReadyVal;
	}
	public List<WuJiang> getWujiangs() {
		return wujiangs;
	}
	public void setWujiangs(List<WuJiang> wujiangs) {
		this.wujiangs = wujiangs;
	}
	public float getUpQuanShuXing() {
		return upQuanShuXing;
	}
	public void setUpQuanShuXing(float upQuanShuXing) {
		if(this.upQuanShuXing<upQuanShuXing) {
			Conf.log("=====刷新全属性提高值" + this.upQuanShuXing + " -> " + upQuanShuXing);
			this.upQuanShuXing = upQuanShuXing;
		}
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
	public float getUpAllWjVal() {
		return upAllWjVal;
	}
	public void setUpAllWjVal(float upAllWjVal) {
		this.upAllWjVal = upAllWjVal;
	}
	public float getUpFaShaShangHaiVal() {
		return upFaShaShangHaiVal;
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
	public void setUpFaShaShangHaiVal(float upFaShaShangHaiVal) {
		this.upFaShaShangHaiVal = upFaShaShangHaiVal;
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
	public int getShuaxinPos() {
		return shuaxinPos;
	}
	public void setShuaxinPos(int shuaxinPos) {
		this.shuaxinPos = shuaxinPos;
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
		//
		
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
	 * 自身士兵损失，恢复自身的
	 * @param position 武将位置
	 * @param defenseVal 防御属性值
	 * @return
	 */
	public float getSolderRate(ZhanFa zf) {
		//先手的概率
		float xianshou = wj.getSpeed()/Conf.base_speed + this.xianshouRate;
		xianshou = xianshou>1.0f?1.0f:xianshou;
		//设置每回合的兵力损失
		getSunShi(zf,wj.getPosition(),wj.getDefense(),wj.getStrategy(),wj.getFinalp());
		
		//如果是先手不会有士兵损失
		float left = wj.getTotalCount() - wj.getSunshiCount() * (1- xianshou);
		
		float rate = left/Conf.totalCount + 1.1f;
		
		return rate>1.0f?(rate>2.1f?2.1f:rate):rate;
	}
	/**
	 * 自身士兵损失值
	 * @param position
	 * @param defenseVal
	 * @param finalP 武将原始位置
	 * @return
	 */
	private float getSunShi(ZhanFa zf,float position, float defenseVal,float strategy,int finalP) {
		float sunShi = 0.0f;
		if(fengAll!=0) {
			float sunshi = 0.0f;
			if(isShuZhi) {
				sunshi = Conf.SunShiCount - fengAll * Conf.fashu_rate - fengAll * Conf.gongji_rate;
			}else {
				sunshi = Conf.SunShiCount - Conf.SunShiCount * fengAll;
			}
			sunshi = sunshi>0?sunshi:0;
			Conf.log("===全控制减伤值：" + fengAll + " 士兵损失值：" + sunshi);
			sunShi += sunshi;
		}
		
		if(fengZhanfa !=0) {
			float sunshi =(Conf.SunShiCount - Conf.SunShiCount * fengZhanfa) * Conf.sf_s_rate;
			sunshi = sunshi>0?sunshi:0;
			Conf.log("===控制法术减伤值：" + fengZhanfa +" 士兵损失值：" + sunshi);
			sunShi += sunshi;
		} 

		if(fengGongji !=0) {
			float sunshi = 0.0f;
			if(isShuZhi) {
				sunshi = Conf.SunShiCount * Conf.gj_s_rate - Conf.gongji_rate * fengGongji;
			}else {
				sunshi = (Conf.SunShiCount - Conf.SunShiCount * fengGongji) * Conf.gj_s_rate;
			}
			sunshi = sunshi>0?sunshi:0;
			Conf.log("===控制攻击减伤值：" + fengGongji + " 士兵损失值：" + sunshi);
			sunShi += sunshi;
		}

		//防御是防御攻击造成的伤害
		if(fengAll==0 && fengGongji ==0) {
			float fangyuVal = Conf.SunShiCount * Conf.gj_s_rate - Conf.fg_rate * defenseVal;
			fangyuVal = fangyuVal>0?fangyuVal:0;
			Conf.log("===防御力减伤值：" + defenseVal + " 敌军普通攻击造成的士兵损失值：" + fangyuVal);
			sunShi += fangyuVal;
		}
		//法术伤害
		if(fengAll==0 && fengZhanfa ==0) {
			float fashuVal = Conf.SunShiCount * Conf.sf_s_rate - Conf.fg_rate * strategy;
			fashuVal = fashuVal>0?fashuVal:0;
			Conf.log("=====敌军法术攻击造成的士兵损失值：" + fashuVal);
			sunShi += fashuVal;
		}
		//行兵之极 前锋
		if(this.isxingbing && finalP==Conf.qianfeng) {
			sunShi *= (1.0f - this.getQianfengUpVal());
		}
		if(wj.getPosition()==Conf.zhongjun) {
			position = 1.8f;
		}else if(wj.getPosition()==Conf.daying) {
			position = 2.0f;
		}else if(wj.getPosition()==Conf.qianfeng) {
			position = 2.3f;
		}
		//规避伤害
		if(this.guibiVal>0.0f) {
			position -= this.guibiVal;
			position = position>0?position:0;
		}
		
		sunShi *= position;
		
		//战法救援武将自身
		if(this.zishenHuifuPos==wj.getPosition()) {
			float huifuCount = this.zishenHuifuVal;
			if(huifuCount<sunShi) {
				sunShi -= huifuCount;
			}else{
				sunShi = 1;
			}
		}
		//群体救援战法
		if(this.huifuVal>0) {
			float huifuCount = this.huifuVal;
			if(huifuCount<sunShi) {
				sunShi -= huifuCount;
			}else{
				sunShi = 1;
			}
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
