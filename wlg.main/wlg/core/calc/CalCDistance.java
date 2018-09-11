package wlg.core.calc;

import wlg.core.bean.conf.Conf;

public class CalCDistance {
	
	/**
	 * 计算武将战法攻击距离
	 * @param zfDis
	 * @param wjPosition
	 * @return
	 */
	public static int calcDistance(int zfDis,int wjPosition) {
		int distance = 1;
		switch(wjPosition) {
		case 1://大营
			distance = zfDis - 2;
			break;
		case 2://中军
			distance = zfDis - 1;
			break;
		case 3://前锋
			distance = zfDis;
			break;
		default:
			Conf.log("------------攻击距离有问题.");
		}
		distance = distance<=0?0:distance;
		Conf.log("===攻击距离实际可伤害的最大人数：" + distance);
		return distance;
	}
	
	/**
	 * 计算武将反击攻击距离
	 * @param wuDis
	 * @param wjPosition
	 * @return
	 */
	public static int calcfDistance(int wuDis,int wjPosition) {
		int distance = 1;
		switch(wjPosition) {
		case 1://大营
			distance = wuDis - 2;
			break;
		case 2://中军
			distance = wuDis - 1;
			break;
		case 3://前锋
			distance = wuDis;
			break;
		default:
			Conf.log("------------攻击距离有问题.");
		}
		distance = distance<=0?0:distance;
		Conf.log("===反击攻击距离实际可伤害的最大人数：" + distance);
		return distance;
	}
}
