package com.pai.service.getui.entity;

import java.util.List;

import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;

public class GeTuiMessage {
	private static long defaultOffLineTime=3*24*60*60*1000;
	/**
	 * @return 
	 * 			SingleMessage:单推消息的消息体<br>
	 *         SingleMessage.offline:消息离线是否存储，默认为true<br>
	 *         SingleMessage.offlineExpireTime:消息离线存储多久，单位为毫秒，默认为60*1000<br>
	 *         SingleMessage.pushNetWorkType:是否wifi推送，1：wifi推送；0：不限制推送方式，默认为0<br>
	 */
	public static  SingleMessage getSingleMessage() {
		SingleMessage singleMessage = new SingleMessage();
		singleMessage.setOffline(true);
		singleMessage.setOfflineExpireTime(defaultOffLineTime);
		return singleMessage;
	}

	/**
	 * 
	 * @param offline
	 *            消息离线是否存储，默认为true
	 * @param offlineExpireTime
	 *            消息离线存储多久，单位为毫秒，默认为60*1000
	 * @param pushNetWorkType
	 *            是否wifi推送，1：wifi推送；0：不限制推送方式，默认为0
	 * @return
	 */
	public static SingleMessage getSingleMessage(boolean offline,
			long offlineExpireTime, int pushNetWorkType) {
		SingleMessage singleMessage = new SingleMessage();
		singleMessage.setOfflineExpireTime(defaultOffLineTime);
		singleMessage.setOffline(offline);
		if (offline && offlineExpireTime >= 0)
			singleMessage.setOfflineExpireTime(offlineExpireTime);
		if (pushNetWorkType == 0 || pushNetWorkType == 1)
			singleMessage.setPushNetWorkType(pushNetWorkType);
		return singleMessage;
	}

	/**
	 * 
	 * @return 
	 * 			ListMessage:列表推送消息的消息体<br>
	 *         ListMessage.offline:消息离线是否存储，默认为true<br>
	 *         ListMessage.offlineExpireTime:消息离线存储多久，单位为毫秒，默认为60*1000<br>
	 *         ListMessage.pushNetWorkType:是否wifi推送，1：wifi推送；0：不限制推送方式，默认为0<br>
	 */
	public static ListMessage getListMessage() {
		ListMessage listMessage = new ListMessage();
		listMessage.setOffline(true);
		listMessage.setOfflineExpireTime(defaultOffLineTime);
		return listMessage;
	}

	/**
	 * 
	 * @param offline
	 *            消息离线是否存储，默认为true
	 * @param offlineExpireTime
	 *            消息离线存储多久，单位为毫秒，默认为60*1000
	 * @param pushNetWorkType
	 *            是否wifi推送，1：wifi推送；0：不限制推送方式，默认为0
	 * @return
	 */
	public static ListMessage getListMessage(boolean offline,
			long offlineExpireTime, int pushNetWorkType) {
		ListMessage listMessage = new ListMessage();
		listMessage.setOfflineExpireTime(defaultOffLineTime);
		listMessage.setOffline(offline);
		if (offline && offlineExpireTime >= 0)
			listMessage.setOfflineExpireTime(offlineExpireTime);
		if (pushNetWorkType == 0 || pushNetWorkType == 1)
			listMessage.setPushNetWorkType(pushNetWorkType);
		return listMessage;
	}
	/**
	 * 
	 * @return 
	 * 			AppMessage:指定应用群推送消息的消息体<br>
	 *         AppMessage.offline:消息离线是否存储，默认为true<br>
	 *         AppMessage.offlineExpireTime:消息离线存储多久，单位为毫秒，默认为60*1000<br>
	 *         AppMessage.appIdList:指定推送的应用列表<br>
	 *         AppMessage.phoneTypeList:设置机型<br>
	 *         AppMessage.provinceList:设置省份或城市。可以是省份名称(浙江,上海,北京...)，也可以是城市编号(33010000,51010000...)<br>
	 *         AppMessage.tagList:设置标签内容<br>
	 *         AppMessage.pushNetWorkType:是否wifi推送，1：wifi推送；0：不限制推送方式，默认为0<br>
	 *         AppMessage.speed:定速推送<br>
	 */
	public static AppMessage getAppMessage(){
		AppMessage appMessage = new AppMessage();
		appMessage.setOffline(true);
		appMessage.setOfflineExpireTime(defaultOffLineTime);
		return appMessage;
	}
	public static AppMessage getAppMessage(boolean offline,
			long offlineExpireTime, int pushNetWorkType,List<String> appIdList,List<String> phoneTypeList,List<String> provinceList,List<String> tagList,int speed){
		AppMessage appMessage = new AppMessage();
		appMessage.setOfflineExpireTime(defaultOffLineTime);
		appMessage.setOffline(offline);
		if (offline && offlineExpireTime >= 0)
			appMessage.setOfflineExpireTime(offlineExpireTime);
		if (pushNetWorkType == 0 || pushNetWorkType == 1)
			appMessage.setPushNetWorkType(pushNetWorkType);
		if(appIdList!=null&&appIdList.size()>0)
			appMessage.setAppIdList(appIdList);
		if(phoneTypeList!=null&&phoneTypeList.size()>0)
			appMessage.setPhoneTypeList(phoneTypeList);
		if(provinceList!=null&&provinceList.size()>0)
			appMessage.setProvinceList(provinceList);
		if(tagList!=null&&tagList.size()>0)
			appMessage.setTagList(tagList);
		if(speed>=0)
			appMessage.setSpeed(speed);
		return appMessage;
	}
}
