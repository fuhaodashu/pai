package com.pai.service.getui.entity;

import com.gexin.rp.sdk.template.APNTemplate;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.NotyPopLoadTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;

public class GeTuiITemplate {
	@SuppressWarnings("unchecked")
	public static <T> T productTemplate(String appID,String appKey,Class<T> type){
		if(TransmissionTemplate.class==type)
			return (T) getTransmissionTemplate(appID, appKey);
		else if (NotificationTemplate.class==type) {
			return (T) getNotificationTemplate(appID, appKey);
		}
		else if (LinkTemplate.class==type) {
			return (T) getLinkTemplate(appID, appKey);
		}
		else if (NotyPopLoadTemplate.class==type) {
			return (T) getNotyPopLoadTemplate(appID, appKey);
		}
		else if (APNTemplate.class==type) {
			return (T) getAPNTemplate(appID, appKey);
		}
		else  {
			return (T) getTransmissionTemplate(appID, appKey);
		}
	}
	/**
	 * TransmissionTemplate
	 * 透传消息
	 */
	private static TransmissionTemplate getTransmissionTemplate(String appID,String appKey){
		TransmissionTemplate transmissionTemplate=new TransmissionTemplate();
		transmissionTemplate.setAppId(appID);
		transmissionTemplate.setAppkey(appKey);
		return transmissionTemplate;
	}
	/**
	 * NotificationTemplate
	 * 	点击通知启动应用
	 */
	private static NotificationTemplate getNotificationTemplate(String appID,String appKey){
		NotificationTemplate notificationTemplate=new NotificationTemplate();
		notificationTemplate.setAppId(appID);
		notificationTemplate.setAppkey(appKey);
		return notificationTemplate;
	}
	/**
	 * LinkTemplate
	 * 点击通知打开网页
	 */
	private static LinkTemplate getLinkTemplate(String appID,String appKey){
		LinkTemplate linkTemplate=new LinkTemplate();
		linkTemplate.setAppId(appID);
		linkTemplate.setAppkey(appKey);
		return linkTemplate;
	}
	/**
	 * NotyPopLoadTemplate
	 * 通知栏弹框下载模版
	 */
	private static NotyPopLoadTemplate getNotyPopLoadTemplate(String appID,String appKey){
		NotyPopLoadTemplate notyPopLoadTemplate=new NotyPopLoadTemplate();
		notyPopLoadTemplate.setAppId(appID);
		notyPopLoadTemplate.setAppkey(appKey);
		return notyPopLoadTemplate;
	}
	/**
	 * APNTemplate
	 */
	private static APNTemplate getAPNTemplate(String appID,String appKey){
		APNTemplate apnTemplate=new APNTemplate();
		apnTemplate.setAppId(appID);
		apnTemplate.setAppkey(appKey);
		return apnTemplate;
	}
}
