package wlg.core;

import java.util.Map;
import java.util.TreeMap;

import wlg.core.bean.wujiang.WList;
import wlg.core.bean.wujiang.WuJiang;
import wlg.core.bean.zhanfa.ZList;
import wlg.core.calc.CalcWJHarm;

public class Choose {
	
	public static void main(String[] args) {
		System.out.println("战法组合的伤害值越高,代表越好:");
		Choose c = new Choose();
		
		Map<Float, String> results = c.calcWuJiangsVal();
		((TreeMap<Float, String>) results).descendingMap().forEach((k,v)->{
			System.out.println(v + k);
		});
		
		System.out.println("==================================================");
		
		Map<Float, String> result = c.calcDanGeWuJiangVal();
		((TreeMap<Float, String>) result).descendingMap().forEach((k,v)->{
			System.out.println(v + k);
		});
	}
	/**
	 * 计算武将组合
	 * @return
	 */
	public Map<Float, String> calcWuJiangsVal() {
		TreeMap<Float, String> result = new TreeMap<>();
		WuJiang one,two,three;
		
		one = WList.luxun.setSecondZhanFa(ZList.bugong).setThreeZhanFa(ZList.shimianmaifu);
		two = WList.zhouyv.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		three= WList.lvmeng.setSecondZhanFa(ZList.fanjizhice).setThreeZhanFa(ZList.jijiaozhishi);
		float sum3 = CalcWJHarm.calcVal(one,two,three);
		String key3 = buildKey(one,two,three);
		result.put(sum3, key3);
		
		one = WList.luxun.setSecondZhanFa(ZList.shengdongjixi).setThreeZhanFa(ZList.shimianmaifu);
		two = WList.zhangchunhua.setSecondZhanFa(ZList.yaoshu).setThreeZhanFa(ZList.mizhen);
		three= WList.zhaoyun.setSecondZhanFa(ZList.qingnangmiyao).setThreeZhanFa(ZList.huima);
		float sum13 = CalcWJHarm.calcVal(one,two,three);
		String key13 = buildKey(one,two,three);
		result.put(sum13, key13);
		
		one = WList.huangyueying.setSecondZhanFa(ZList.chugesiqi).setThreeZhanFa(ZList.zengyuan);
		two = WList.zhangchunhua.setSecondZhanFa(ZList.hunshuimoyv).setThreeZhanFa(ZList.shiji);
		three= WList.hetaihou.setSecondZhanFa(ZList.zhanbiduanjin).setThreeZhanFa(ZList.qingnangmiyao);
		float sum25 = CalcWJHarm.calcVal(one,two,three);
		String key25 = buildKey(one,two,three);
		result.put(sum25, key25);
		
		one = WList.xunyv.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.bugong);
		two = WList.guojia.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		three= WList.caocao.setSecondZhanFa(ZList.fanjizhice).setThreeZhanFa(ZList.jijiaozhishi);
		float sum24 = CalcWJHarm.calcVal(one,two,three);
		String key24 = buildKey(one,two,three);
		result.put(sum24, key24);
		
		one = WList.luxun.reset();
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.luxun.setSecondZhanFa(ZList.shenmouyuanlv).setThreeZhanFa(ZList.chugesiqi);
		two = WList.zhouyv.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		float sum_1 = CalcWJHarm.calcVal(one,two);
		String key_1 = buildKey(one,two);
		result.put(sum_1, key_1);
		
		one = WList.luxun.setSecondZhanFa(ZList.shengbingqiuzhan).setThreeZhanFa(ZList.shimianmaifu);
		two = WList.zhouyv.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		three = WList.lvmeng.setSecondZhanFa(ZList.jijiaozhishi).setThreeZhanFa(ZList.fanjizhice);
		float sum = CalcWJHarm.calcVal(one,two,three);
		String key = buildKey(one,two,three);
		result.put(sum, key);
		
		one = WList.luxun.reset();
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.luxun.setSecondZhanFa(ZList.shenmouyuanlv).setThreeZhanFa(ZList.chugesiqi);
		two = WList.zhouyv.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		float sum_2 = CalcWJHarm.calcVal(one,two);
		String key_2 = buildKey(one,two);
		result.put(sum_2, key_2);
		
		one = WList.luxun.setSecondZhanFa(ZList.bugong).setThreeZhanFa(ZList.chugesiqi);
		two = WList.zhouyv.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		three= WList.lvmeng.setSecondZhanFa(ZList.fanjizhice).setThreeZhanFa(ZList.jijiaozhishi);
		float sum1 = CalcWJHarm.calcVal(one,two,three);
		String key1 = buildKey(one,two,three);
		result.put(sum1, key1);
		
		one = WList.luxun.setSecondZhanFa(ZList.bugong).setThreeZhanFa(ZList.chugesiqi);
		two = WList.zhouyv.setSecondZhanFa(ZList.dashangsanjun).setThreeZhanFa(ZList.hunshuimoyv);
		three= WList.zhaoyun.setSecondZhanFa(ZList.huima).setThreeZhanFa(ZList.jijiaozhishi);
		float sum2 = CalcWJHarm.calcVal(one,two,three);
		String key2 = buildKey(one,two,three);
		result.put(sum2, key2);
		
		one = WList.huangyueying.setSecondZhanFa(ZList.bugong).setThreeZhanFa(ZList.chugesiqi);
		two = WList.zhouyv.setSecondZhanFa(ZList.shuiyanqijun).setThreeZhanFa(ZList.yanfenjizhen);
		three= WList.zhaoyun.setSecondZhanFa(ZList.huima).setThreeZhanFa(ZList.jijiaozhishi);
		float sum4 = CalcWJHarm.calcVal(one,two,three);
		String key4 = buildKey(one,two,three);
		result.put(sum4, key4);
		
		one = WList.luxun.setSecondZhanFa(ZList.shenmouyuanlv).setThreeZhanFa(ZList.chugesiqi);
		two = WList.zhangchunhua.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		three= WList.caocao.setSecondZhanFa(ZList.fanjizhice).setThreeZhanFa(ZList.jijiaozhishi);
		float sum5 = CalcWJHarm.calcVal(one,two,three);
		String key5 = buildKey(one,two,three);
		result.put(sum5, key5);
		
		one = WList.caopi.setSecondZhanFa(ZList.shenmouyuanlv).setThreeZhanFa(ZList.chugesiqi);
		two = WList.wangyi.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		three= WList.caocao.setSecondZhanFa(ZList.zhanbiduanjin).setThreeZhanFa(ZList.jijiaozhishi);
		float sum6 = CalcWJHarm.calcVal(one,two,three);
		String key6 = buildKey(one,two,three);
		result.put(sum6, key6);
		
		one = WList.luxun.setSecondZhanFa(ZList.bugong).setThreeZhanFa(ZList.shimianmaifu);
		two = WList.zhouyv.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		three= WList.lvmeng.setSecondZhanFa(ZList.jijiaozhishi).setThreeZhanFa(ZList.dashangsanjun);
		float sum7 = CalcWJHarm.calcVal(one,two,three);
		String key7 = buildKey(one,two,three);
		result.put(sum7, key7);
		
		one = WList.luxun.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.shenmouyuanlv);
		two = WList.zhangchunhua.setSecondZhanFa(ZList.yaoshu).setThreeZhanFa(ZList.luolei);
		three= WList.caocao.setSecondZhanFa(ZList.xingbingzhiji).setThreeZhanFa(ZList.shenbingtianjiang);
		float sum8 = CalcWJHarm.calcVal(one,two,three);
		String key8 = buildKey(one,two,three);
		result.put(sum8, key8);
		
		one = WList.luxun.setSecondZhanFa(ZList.bugong).setThreeZhanFa(ZList.shimianmaifu);
		two = WList.xunyv.setSecondZhanFa(ZList.chugesiqi).setThreeZhanFa(ZList.shenmouyuanlv);
		three= WList.caocao.setSecondZhanFa(ZList.yaoshu).setThreeZhanFa(ZList.mizhen);
		float sum9 = CalcWJHarm.calcVal(one,two,three);
		String key9 = buildKey(one,two,three);
		result.put(sum9, key9);
		
		one = WList.luxun.setSecondZhanFa(ZList.bugong).setThreeZhanFa(ZList.shimianmaifu);
		two = WList.guojia.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		three= WList.lvmeng.setSecondZhanFa(ZList.fanjizhice).setThreeZhanFa(ZList.jijiaozhishi);
		float sum10 = CalcWJHarm.calcVal(one,two,three);
		String key10 = buildKey(one,two,three);
		result.put(sum10, key10);
		
		one = WList.luxun.setSecondZhanFa(ZList.bugong).setThreeZhanFa(ZList.shimianmaifu);
		two = WList.xunyv.setSecondZhanFa(ZList.mizhen).setThreeZhanFa(ZList.chugesiqi);
		three= WList.caocao.setSecondZhanFa(ZList.yaoshu).setThreeZhanFa(ZList.jijiaozhishi);
		float sum11 = CalcWJHarm.calcVal(one,two,three);
		String key11 = buildKey(one,two,three);
		result.put(sum11, key11);
		
		one = WList.luxun.setSecondZhanFa(ZList.shengdongjixi).setThreeZhanFa(ZList.shimianmaifu);
		two = WList.zhangning.setSecondZhanFa(ZList.yaoshu).setThreeZhanFa(ZList.mizhen);
		three= WList.caocao.setSecondZhanFa(ZList.dashangsanjun).setThreeZhanFa(ZList.jijiaozhishi);
		float sum12 = CalcWJHarm.calcVal(one,two,three);
		String key12 = buildKey(one,two,three);
		result.put(sum12, key12);
		
		one = WList.luxun.setSecondZhanFa(ZList.bugong).setThreeZhanFa(ZList.shimianmaifu);
		two = WList.handiaochan.setSecondZhanFa(ZList.yaoshu).setThreeZhanFa(ZList.mizhen);
		three= WList.caocao.setSecondZhanFa(ZList.dashangsanjun).setThreeZhanFa(ZList.jijiaozhishi);
		float sum14 = CalcWJHarm.calcVal(one,two,three);
		String key14 = buildKey(one,two,three);
		result.put(sum14, key14);
		
		one = WList.luxun.setSecondZhanFa(ZList.shengdongjixi).setThreeZhanFa(ZList.shimianmaifu);
		two = WList.lingdi.setSecondZhanFa(ZList.yaoshu).setThreeZhanFa(ZList.jijiaozhishi);
		three= WList.caocao.setSecondZhanFa(ZList.xingbingzhiji).setThreeZhanFa(ZList.huima);
		float sum15 = CalcWJHarm.calcVal(one,two,three);
		String key15 = buildKey(one,two,three);
		result.put(sum15, key15);
		
		one = WList.luxun.setSecondZhanFa(ZList.shengdongjixi).setThreeZhanFa(ZList.shimianmaifu);
		two = WList.hetaihou.setSecondZhanFa(ZList.yaoshu).setThreeZhanFa(ZList.jijiaozhishi);
		three= WList.caocao.setSecondZhanFa(ZList.xingbingzhiji).setThreeZhanFa(ZList.huima);
		float sum16 = CalcWJHarm.calcVal(one,two,three);
		String key16 = buildKey(one,two,three);
		result.put(sum16, key16);
		
		one = WList.luxun.setSecondZhanFa(ZList.bugong).setThreeZhanFa(ZList.shimianmaifu);
		two = WList.xunyv.setSecondZhanFa(ZList.mizhen).setThreeZhanFa(ZList.yanfenjizhen);
		three= WList.caocao.setSecondZhanFa(ZList.yaoshu).setThreeZhanFa(ZList.zhanbiduanjin);
		float sum17 = CalcWJHarm.calcVal(one,two,three);
		String key17 = buildKey(one,two,three);
		result.put(sum17, key17);
		
		one = WList.luxun.setSecondZhanFa(ZList.shenmouyuanlv).setThreeZhanFa(ZList.chugesiqi);
		two = WList.zhouyv.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		three= WList.lvmeng.setSecondZhanFa(ZList.fanjizhice).setThreeZhanFa(ZList.jijiaozhishi);
		float sum18 = CalcWJHarm.calcVal(one,two,three);
		String key18 = buildKey(one,two,three);
		result.put(sum18, key18);
		
		one = WList.luxun.setSecondZhanFa(ZList.shengbingqiuzhan).setThreeZhanFa(ZList.shimianmaifu);
		two = WList.zhouyv.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		three= WList.lvmeng.setSecondZhanFa(ZList.fanjizhice).setThreeZhanFa(ZList.jijiaozhishi);
		float sum19 = CalcWJHarm.calcVal(one,two,three);
		String key19 = buildKey(one,two,three);
		result.put(sum19, key19);
		
		one = WList.luxun.setSecondZhanFa(ZList.bugong).setThreeZhanFa(ZList.shimianmaifu);
		two = WList.zhouyv.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.hunshuimoyv);
		three= WList.lvmeng.setSecondZhanFa(ZList.fanjizhice).setThreeZhanFa(ZList.jijiaozhishi);
		float sum20 = CalcWJHarm.calcVal(one,two,three);
		String key20 = buildKey(one,two,three);
		result.put(sum20, key20);
		
		one = WList.luxun.setSecondZhanFa(ZList.bugong).setThreeZhanFa(ZList.shimianmaifu);
		two = WList.guojia.setSecondZhanFa(ZList.xingbingzhiji).setThreeZhanFa(ZList.hunshuimoyv);
		three= WList.lvmeng.setSecondZhanFa(ZList.fanjizhice).setThreeZhanFa(ZList.jijiaozhishi);
		float sum21 = CalcWJHarm.calcVal(one,two,three);
		String key21 = buildKey(one,two,three);
		result.put(sum21, key21);
		
		one = WList.luxun.setSecondZhanFa(ZList.shengbingqiuzhan).setThreeZhanFa(ZList.shimianmaifu);
		two = WList.zhouyv.setSecondZhanFa(ZList.hunshuimoyv).setThreeZhanFa(ZList.shenbingtianjiang);
		three= WList.lvmeng.setSecondZhanFa(ZList.fanjizhice).setThreeZhanFa(ZList.dashangsanjun);
		float sum22 = CalcWJHarm.calcVal(one,two,three);
		String key22 = buildKey(one,two,three);
		result.put(sum22, key22);
		
		one = WList.luxun.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.bugong);
		two = WList.zhangchunhua.setSecondZhanFa(ZList.shenbingtianjiang).setThreeZhanFa(ZList.yaoshu);
		three= WList.caocao.setSecondZhanFa(ZList.dashangsanjun).setThreeZhanFa(ZList.qingnangmiyao);
		float sum23 = CalcWJHarm.calcVal(one,two,three);
		String key23 = buildKey(one,two,three);
		result.put(sum23, key23);
		
		one = WList.xiaozhujun.setSecondZhanFa(ZList.dunbingcuorui).setThreeZhanFa(ZList.bugong);
		two = WList.handiaochan.setSecondZhanFa(ZList.mizhen).setThreeZhanFa(ZList.yaoshu);
		three= WList.hetaihou.setSecondZhanFa(ZList.heliu).setThreeZhanFa(ZList.qingnangmiyao);
		float sum26 = CalcWJHarm.calcVal(one,two,three);
		String key26 = buildKey(one,two,three);
		result.put(sum26, key26);
		
		one = WList.xiahouyuan.setSecondZhanFa(ZList.dunbingcuorui).setThreeZhanFa(ZList.bugong);
		two = WList.handiaochan.setSecondZhanFa(ZList.mizhen).setThreeZhanFa(ZList.yaoshu);
		three= WList.hetaihou.setSecondZhanFa(ZList.heliu).setThreeZhanFa(ZList.qingnangmiyao);
		float sum27 = CalcWJHarm.calcVal(one,two,three);
		String key27 = buildKey(one,two,three);
		result.put(sum27, key27);
		
		one = WList.xunyv.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.shengdongjixi);
		two = WList.guojia.setSecondZhanFa(ZList.hunshuimoyv).setThreeZhanFa(ZList.luolei);
		three= WList.xunyou.setSecondZhanFa(ZList.zhanbiduanjin).setThreeZhanFa(ZList.fanjizhice);
		float sum28 = CalcWJHarm.calcVal(one,two,three);
		String key28 = buildKey(one,two,three);
		result.put(sum28, key28);
		
		one = WList.pangtong.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.shengdongjixi);
		two = WList.buzhuge.setSecondZhanFa(ZList.hunshuimoyv).setThreeZhanFa(ZList.luolei);
		three= WList.xushu.setSecondZhanFa(ZList.zhanbiduanjin).setThreeZhanFa(ZList.fanjizhice);
		float sum29 = CalcWJHarm.calcVal(one,two,three);
		String key29 = buildKey(one,two,three);
		result.put(sum29, key29);
		
		one = WList.pangtong.setSecondZhanFa(ZList.shimianmaifu).setThreeZhanFa(ZList.shengdongjixi);
		two = WList.gongzhuge.setSecondZhanFa(ZList.hunshuimoyv).setThreeZhanFa(ZList.luolei);
		three= WList.xushu.setSecondZhanFa(ZList.zhanbiduanjin).setThreeZhanFa(ZList.fanjizhice);
		float sum30 = CalcWJHarm.calcVal(one,two,three);
		String key30 = buildKey(one,two,three);
		result.put(sum30, key30);
		
		return result;
	}
	
	/**
	 * 计算单个武将
	 * @return
	 */
	public Map<Float, String> calcDanGeWuJiangVal() {
		TreeMap<Float, String> result = new TreeMap<>();
		WuJiang one;
		
//		one = WList.luxun.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.lvmeng.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.zhouyv.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.zhaoyun.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.huangyueying.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.mayunlu.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.xunyv.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.zhangchunhua.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.caocao.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.caopi.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.wangyi.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.guojia.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.xiaoguojia.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.handiaochan.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.lingdi.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.hetaihou.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.zhangning.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//
//		one = WList.xiaozhujun.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.xiaoganning.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.xiaolingtong.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.xiahouyuan.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.zhangliao.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.xunyou.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.buzhuge.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.xushu.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.pangtong.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
//		
//		one = WList.gongzhuge.reset();
//		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		one = WList.lvbu.reset();
		result.put(CalcWJHarm.calcVal(one), one.toString());
		
		return result;
	}
	

	protected static String buildKey(WuJiang... wjs) {
		StringBuilder key = new StringBuilder();
		for(WuJiang wj:wjs) {
			key.append(wj.toKey());
		}
		key.append("该组合伤害值:");
		return key.toString();
	}
	
}
