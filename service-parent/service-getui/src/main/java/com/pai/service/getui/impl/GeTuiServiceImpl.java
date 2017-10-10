package com.pai.service.getui.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pai.service.getui.GeTuiService;
import com.pai.service.getui.entity.GeTuiITemplate;
import com.pai.service.getui.entity.GeTuiMessage;
import com.pai.service.getui.entity.GeTuiPushMessage;
import com.pai.service.getui.entity.GeTuiPushNPLTemplate;
import com.pai.service.getui.entity.GeTuiPushTemplate;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.NotyPopLoadTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;

@Service
public class GeTuiServiceImpl implements GeTuiService {
	private static Logger logger = LoggerFactory
			.getLogger(GeTuiServiceImpl.class);
	@Value("${getui.appId}")
	private String appId;
	@Value("${getui.appkey}")
	private String appkey;
	@Value("${getui.master}")
	private String master;
	@Value("${getui.host}")
	private String host;
	@Value("${tatatoutiao.logoUrl}")
	private String logoUrl;
	@Value("${tatatoutiao.title}")
	private String title;
	/**
	 * 单个用户透传
	 * 
	 * @param mes
	 *            内容
	 * @param CID
	 *            clientId
	 * @param transmissionType
	 *            应用启动类型：1 应用立即启动 2 广播等待客户端自启动
	 * @return
	 */
	@Override
	public String transmissionToSingle(String mes,String content, String CID,
			int transmissionType) {  
		IGtPush push = new IGtPush(host, appkey, master);
		TransmissionTemplate template = GeTuiITemplate.productTemplate(appId,
				appkey, TransmissionTemplate.class);
		SingleMessage singleMessage = GeTuiMessage.getSingleMessage();
		template.setTransmissionContent(content);
		template.setTransmissionType(transmissionType);
		APNPayload payload = new APNPayload();
	    payload.setBadge(1);
	    payload.setSound("default");
	    payload.setCategory("");
		APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
	    alertMsg.setBody(mes);
	    alertMsg.setActionLocKey(mes);
	    alertMsg.setLocKey(mes);
	    alertMsg.addLocArg("loc-args");
	    alertMsg.setLaunchImage("launch-image");
	    // IOS8.2以上版本支持
	    alertMsg.setTitle(title);
	    alertMsg.setTitleLocKey(title);
	    alertMsg.addTitleLocArg("TitleLocArg");
	    payload.setAlertMsg(alertMsg);
		template.setAPNInfo(payload);
		singleMessage.setData(template);
		Target target = new Target();
		target.setAppId(appId);
		target.setClientId(CID);
		IPushResult ret = null;
		try {
			ret = push.pushMessageToSingle(singleMessage, target);
		} catch (RequestException e) {
			e.printStackTrace();
			ret = push.pushMessageToSingle(singleMessage, target,
					e.getRequestId());
		}
		if (ret != null) {
			System.out.println(ret.getResponse().toString());
			return ret.getResponse().toString();
		} else {
			System.out.println("服务器响应异常");
			return "服务器响应异常";
		}
	}

	/**
	 * 多个用户透传
	 * 
	 * @param mes
	 *            内容
	 * @param CID
	 *            clientId
	 * @param transmissionType
	 *            应用启动类型：1 应用立即启动 2 广播等待客户端自启动
	 * @return
	 */
	@Override
	public String transmissionToList(String mes, List<String> CID,
			int transmissionType) {
		IGtPush push = new IGtPush(host, appkey, master);
		TransmissionTemplate template = GeTuiITemplate.productTemplate(appId,
				appkey, TransmissionTemplate.class);
		ListMessage listMessage = GeTuiMessage.getListMessage();
		template.setTransmissionContent(mes);
		template.setTransmissionType(transmissionType);
		listMessage.setData(template);
		List<Target> targets = new ArrayList<Target>();
		for (String cid : CID) {
			Target target = new Target();
			target.setAppId(appId);
			target.setClientId(cid);
			targets.add(target);
		}
		IPushResult ret = push.pushMessageToList(
				push.getContentId(listMessage), targets);
		if (ret != null) {
			System.out.println(ret.getResponse().toString());
			return ret.getResponse().toString();
		} else {
			System.out.println("服务器响应异常");
			return "服务器响应异常";
		}
	}

	/**
	 * 单个用户透传
	 * 
	 * @param geTuiPushMessage
	 *            内容
	 * @param geTuiPushTemplate
	 *            模板
	 * @return
	 */
	@Override
	public String transmissionToSingleOrList(List<String> CIDs,
			GeTuiPushMessage geTuiPushMessage,
			GeTuiPushTemplate geTuiPushTemplate) {
		IGtPush push = new IGtPush(host, appkey, master);
		TransmissionTemplate template = GeTuiITemplate.productTemplate(appId,
				appkey, TransmissionTemplate.class);
		template.setTransmissionContent(geTuiPushTemplate
				.getTransmissionContent());
		template.setTransmissionType(geTuiPushTemplate.getTransmissionType());
		IPushResult ret = null;
		if (CIDs == null || CIDs.size() == 0) {
			SingleMessage singleMessage = GeTuiMessage.getSingleMessage(
					geTuiPushMessage.isOffline(),
					geTuiPushMessage.getOfflineExpireTime(),
					geTuiPushMessage.getPushNetWorkTyp());
			singleMessage.setData(template);
			Target target = new Target();
			target.setAppId(appId);
			target.setClientId(geTuiPushMessage.getCID());
			try {
				ret = push.pushMessageToSingle(singleMessage, target);
			} catch (RequestException e) {
				e.printStackTrace();
				ret = push.pushMessageToSingle(singleMessage, target,
						e.getRequestId());
			}
		} else {
			ListMessage listMessage = GeTuiMessage.getListMessage(
					geTuiPushMessage.isOffline(),
					geTuiPushMessage.getOfflineExpireTime(),
					geTuiPushMessage.getPushNetWorkTyp());
			List<Target> targets = new ArrayList<Target>();
			for (String cid : CIDs) {
				Target target = new Target();
				target.setAppId(appId);
				target.setClientId(cid);
				targets.add(target);
			}
			listMessage.setData(template);
			ret = push.pushMessageToList(push.getContentId(listMessage),
					targets);
		}
		if (ret != null) {
			System.out.println(ret.getResponse().toString());
			return ret.getResponse().toString();
		} else {
			System.out.println("服务器响应异常");
			return "服务器响应异常";
		}
	}

	/**
	 * 单个用户通知(激活应用)
	 * 
	 * @param mes
	 *            内容
	 * @param CID
	 *            clientId
	 * @param transmissionType
	 *            应用启动类型：1 应用立即启动 2 广播等待客户端自启动
	 * @param title
	 *            标题
	 * @param logo
	 * @param logoUrl
	 * @return
	 */
	@Override
	public String notificationToSingle(String mes,String content, String CID,
			int transmissionType, String title, String logo, String logoUrl) {
		IGtPush push = new IGtPush(host, appkey, master);
		NotificationTemplate template =GeTuiITemplate.productTemplate(appId,
				appkey, NotificationTemplate.class);
		template.setAppId(appId);
		template.setAppkey(appkey);
		SingleMessage singleMessage = GeTuiMessage.getSingleMessage();
		template.setText(mes);
		template.setTransmissionContent(content);
		template.setTitle(this.title);
		if(title!=null&&!"".equals(title))
		template.setTitle(title);
		template.setLogo(logo);
		template.setLogoUrl(this.logoUrl);
		if(logoUrl!=null&&!"".equals(logoUrl))
		template.setLogoUrl(logoUrl);
		template.setTransmissionType(transmissionType);
		singleMessage.setData(template);
		Target target = new Target();
		target.setAppId(appId);
		target.setClientId(CID);
		IPushResult ret = null;
		ret = push.pushMessageToSingle(singleMessage, target);
		if (ret != null) {
			System.out.println(ret.getResponse().toString());
			return ret.getResponse().toString();
		} else {
			logger.info("服务器响应异常");
			return "服务器响应异常";
		}
	}

	/**
	 * 多个用户通知(激活应用)
	 * 
	 * @param mes
	 *            内容
	 * @param CID
	 *            clientId
	 * @param transmissionType
	 *            应用启动类型：1 应用立即启动 2 广播等待客户端自启动
	 * @param title
	 *            标题
	 * @param logo
	 * @param logoUrl
	 * @return
	 */
	@Override
	public String notificationToList(String mes,String content, List<String> CID,
			int transmissionType, String title, String logo, String logoUrl) {
		IGtPush push = new IGtPush(host, appkey, master);
		NotificationTemplate template = GeTuiITemplate.productTemplate(appId,
				appkey, NotificationTemplate.class);
		ListMessage listMessage = GeTuiMessage.getListMessage();
		template.setText(mes);
		template.setTransmissionContent(content);
		template.setTitle(this.title);
		if(title!=null&&!"".equals(title))
		template.setTitle(title);
		template.setLogo(logo);
		template.setLogoUrl(this.logoUrl);
		if(logoUrl!=null&&!"".equals(logoUrl))
		template.setLogoUrl(logoUrl);
		template.setTransmissionType(transmissionType);
		listMessage.setData(template);
		List<Target> targets = new ArrayList<Target>();
		for (String cid : CID) {
			Target target = new Target();
			target.setAppId(appId);
			target.setClientId(cid);
			targets.add(target);
		}
		IPushResult ret = push.pushMessageToList(
				push.getContentId(listMessage), targets);
		if (ret != null) {
			System.out.println(ret.getResponse().toString());
			return ret.getResponse().toString();
		} else {
			System.out.println("服务器响应异常");
			return "服务器响应异常";
		}
	}

	/**
	 * 单个用户通知(激活应用)
	 * 
	 * @param mes
	 *            内容
	 * @param CID
	 *            clientId
	 * @param transmissionType
	 *            应用启动类型：1 应用立即启动 2 广播等待客户端自启动
	 * @param title
	 *            标题
	 * @param logo
	 * @param logoUrl
	 * @return
	 */
	@Override
	public String notificationToSingleOrList(List<String> CIDs,
			GeTuiPushMessage geTuiPushMessage,
			GeTuiPushTemplate geTuiPushTemplate) {
		IGtPush push = new IGtPush(host, appkey, master);
		NotificationTemplate template = GeTuiITemplate.productTemplate(appId,
				appkey, NotificationTemplate.class);
		template.setText(geTuiPushTemplate.getText());
		template.setTransmissionContent(geTuiPushTemplate.getTransmissionContent());
		template.setTitle(this.title);
		if(geTuiPushTemplate.getTitle()!=null&&!"".equals(geTuiPushTemplate.getTitle()))
		template.setTitle(geTuiPushTemplate.getTitle());
		template.setLogo(geTuiPushTemplate.getLogo());
		template.setLogoUrl(this.logoUrl);
		if(geTuiPushTemplate.getLogoUrl()!=null&&!"".equals(geTuiPushTemplate.getLogoUrl()))
		template.setLogoUrl(geTuiPushTemplate.getLogoUrl());
		template.setTransmissionType(geTuiPushTemplate.getTransmissionType());
		template.setIsRing(geTuiPushTemplate.getIsRing());
		template.setIsClearable(geTuiPushTemplate.getIsClearable());
		template.setIsVibrate(geTuiPushTemplate.getIsVibrate());
		IPushResult ret = null;
		if (CIDs == null || CIDs.size() == 0) {
			SingleMessage singleMessage = GeTuiMessage.getSingleMessage(
					geTuiPushMessage.isOffline(),
					geTuiPushMessage.getOfflineExpireTime(),
					geTuiPushMessage.getPushNetWorkTyp());
			singleMessage.setData(template);
			Target target = new Target();
			target.setAppId(appId);
			target.setClientId(geTuiPushMessage.getCID());
			try {
				ret = push.pushMessageToSingle(singleMessage, target);
			} catch (RequestException e) {
				e.printStackTrace();
				ret = push.pushMessageToSingle(singleMessage, target,
						e.getRequestId());
			}
		} else {
			ListMessage listMessage = GeTuiMessage.getListMessage(
					geTuiPushMessage.isOffline(),
					geTuiPushMessage.getOfflineExpireTime(),
					geTuiPushMessage.getPushNetWorkTyp());
			List<Target> targets = new ArrayList<Target>();
			for (String cid : CIDs) {
				Target target = new Target();
				target.setAppId(appId);
				target.setClientId(cid);
				targets.add(target);
			}
			listMessage.setData(template);
			ret = push.pushMessageToList(push.getContentId(listMessage),
					targets);
		}
		if (ret != null) {
			System.out.println(ret.getResponse().toString());
			return ret.getResponse().toString();
		} else {
			System.out.println("服务器响应异常");
			return "服务器响应异常";
		}
	}

	/**
	 * 单个用户通知(打开网页)
	 * 
	 * @param mes
	 *            内容
	 * @param CID
	 *            clientId
	 * @param title
	 *            标题
	 * @param logo
	 * @param logoUrl
	 * @param url
	 *            要打开的网址
	 * @return
	 */
	@Override
	public String LinkTemplateToSingle(String mes, String CID, String title,
			String logo, String logoUrl, String url) {
		IGtPush push = new IGtPush(host, appkey, master);
		LinkTemplate template = GeTuiITemplate.productTemplate(appId, appkey,
				LinkTemplate.class);
		SingleMessage singleMessage = GeTuiMessage.getSingleMessage();
		template.setText(mes);
		template.setTitle(this.title);
		if(title!=null&&!"".equals(title))
		template.setTitle(title);
		template.setLogo(logo);
		template.setLogoUrl(this.logoUrl);
		if(logoUrl!=null&&!"".equals(logoUrl))
		template.setLogoUrl(logoUrl);
		template.setUrl(url);
		singleMessage.setData(template);
		Target target = new Target();
		target.setAppId(appId);
		target.setClientId(CID);
		IPushResult ret = null;
		try {
			ret = push.pushMessageToSingle(singleMessage, target);
		} catch (RequestException e) {
			e.printStackTrace();
			ret = push.pushMessageToSingle(singleMessage, target,
					e.getRequestId());
		}
		if (ret != null) {
			System.out.println(ret.getResponse().toString());
			return ret.getResponse().toString();
		} else {
			System.out.println("服务器响应异常");
			return "服务器响应异常";
		}
	}

	/**
	 * 多个用户通知(打开网页)
	 * 
	 * @param mes
	 *            内容
	 * @param CID
	 *            clientId
	 * @param title
	 *            标题
	 * @param logo
	 * @param logoUrl
	 * @param url
	 *            要打开的网址
	 * @return
	 */
	@Override
	public String LinkTemplateToList(String mes, List<String> CID,
			String title, String logo, String logoUr, String url) {
		IGtPush push = new IGtPush(host, appkey, master);
		LinkTemplate template = GeTuiITemplate.productTemplate(appId, appkey,
				LinkTemplate.class);
		ListMessage listMessage = GeTuiMessage.getListMessage();
		template.setText(mes);
		template.setTitle(this.title);
		if(title!=null&&!"".equals(title))
		template.setTitle(title);
		template.setLogo(logo);
		template.setLogoUrl(this.logoUrl);
		if(logoUrl!=null&&!"".equals(logoUrl))
		template.setLogoUrl(logoUrl);
		template.setUrl(url);
		listMessage.setData(template);
		List<Target> targets = new ArrayList<Target>();
		for (String cid : CID) {
			Target target = new Target();
			target.setAppId(appId);
			target.setClientId(cid);
			targets.add(target);
		}
		IPushResult ret = push.pushMessageToList(
				push.getContentId(listMessage), targets);
		if (ret != null) {
			System.out.println(ret.getResponse().toString());
			return ret.getResponse().toString();
		} else {
			System.out.println("服务器响应异常");
			return "服务器响应异常";
		}
	}

	/**
	 * 单个用户通知(打开网页)
	 * 
	 * @param mes
	 *            内容
	 * @param CID
	 *            clientId
	 * @param title
	 *            标题
	 * @param logo
	 * @param logoUrl
	 * @param url
	 *            要打开的网址
	 * @return
	 */
	@Override
	public String LinkTemplateToSingleOrList(List<String> CIDs,
			GeTuiPushMessage geTuiPushMessage,
			GeTuiPushTemplate geTuiPushTemplate) {
		IGtPush push = new IGtPush(host, appkey, master);
		LinkTemplate template = GeTuiITemplate.productTemplate(appId, appkey,
				LinkTemplate.class);
		template.setText(geTuiPushTemplate.getText());
		template.setTitle(this.title);
		if(geTuiPushTemplate.getTitle()!=null&&!"".equals(geTuiPushTemplate.getTitle()))
		template.setTitle(geTuiPushTemplate.getTitle());
		template.setLogo(geTuiPushTemplate.getLogo());
		template.setLogoUrl(this.logoUrl);
		if(geTuiPushTemplate.getLogoUrl()!=null&&!"".equals(geTuiPushTemplate.getLogoUrl()))
		template.setLogoUrl(geTuiPushTemplate.getLogoUrl());
		template.setUrl(geTuiPushTemplate.getUrl());
		template.setIsRing(geTuiPushTemplate.getIsRing());
		template.setIsClearable(geTuiPushTemplate.getIsClearable());
		template.setIsVibrate(geTuiPushTemplate.getIsVibrate());
		IPushResult ret = null;
		if (CIDs == null || CIDs.size() == 0) {
			SingleMessage singleMessage = GeTuiMessage.getSingleMessage(
					geTuiPushMessage.isOffline(),
					geTuiPushMessage.getOfflineExpireTime(),
					geTuiPushMessage.getPushNetWorkTyp());
			singleMessage.setData(template);
			Target target = new Target();
			target.setAppId(appId);
			target.setClientId(geTuiPushMessage.getCID());
			try {
				ret = push.pushMessageToSingle(singleMessage, target);
			} catch (RequestException e) {
				e.printStackTrace();
				ret = push.pushMessageToSingle(singleMessage, target,
						e.getRequestId());
			}
		}
		else{
			ListMessage listMessage = GeTuiMessage.getListMessage(
					geTuiPushMessage.isOffline(),
					geTuiPushMessage.getOfflineExpireTime(),
					geTuiPushMessage.getPushNetWorkTyp());
			List<Target> targets = new ArrayList<Target>();
			for (String cid : CIDs) {
				Target target = new Target();
				target.setAppId(appId);
				target.setClientId(cid);
				targets.add(target);
			}
			listMessage.setData(template);
			ret = push.pushMessageToList(
					push.getContentId(listMessage), targets);
		}
		if (ret != null) {
			System.out.println(ret.getResponse().toString());
			return ret.getResponse().toString();
		} else {
			System.out.println("服务器响应异常");
			return "服务器响应异常";
		}
	}
	/**
	 * 
	 */
	@Override
	public String NPLTemplateToSingleOrList(List<String> CIDs,
			GeTuiPushMessage geTuiPushMessage,
			GeTuiPushNPLTemplate geTuiPushNPLTemplate) {
		IGtPush push = new IGtPush(host, appkey, master);
		NotyPopLoadTemplate template = GeTuiITemplate.productTemplate(appId, appkey,
				NotyPopLoadTemplate.class);		
		template.setNotyIcon(geTuiPushNPLTemplate.getNotyIcon());
		template.setNotyTitle(geTuiPushNPLTemplate.getNotyTitle());
		template.setNotyContent(geTuiPushNPLTemplate.getNotyContent());
		template.setCleared(geTuiPushNPLTemplate.isCleared());
		template.setBelled(geTuiPushNPLTemplate.isBelled());
		template.setVibrationed(geTuiPushNPLTemplate.isVibrationed());
		template.setPopTitle(geTuiPushNPLTemplate.getPopTitle());
		template.setPopContent(geTuiPushNPLTemplate.getPopContent());
		template.setPopImage(geTuiPushNPLTemplate.getPopImage());
		template.setPopButton1(geTuiPushNPLTemplate.getPopButton1());
		template.setPopButton2(geTuiPushNPLTemplate.getPopButton2());
		template.setLoadIcon(geTuiPushNPLTemplate.getLoadIcon());
		template.setLoadTitle(geTuiPushNPLTemplate.getLoadTitle());
		template.setLoadUrl(geTuiPushNPLTemplate.getLoadUrl());
		template.setAutoInstall(geTuiPushNPLTemplate.isAutoInstall());
		template.setActived(geTuiPushNPLTemplate.isActived());
		template.setAndroidMark(geTuiPushNPLTemplate.getAndroidMark());
		/*template.setSymbianMark(geTuiPushNPLTemplate.getSymbianMark());
		template.setIphoneMark(geTuiPushNPLTemplate.getIphoneMark());*/
		IPushResult ret = null;
		if(CIDs==null||CIDs.size()==0){
			SingleMessage singleMessage = GeTuiMessage.getSingleMessage(
					geTuiPushMessage.isOffline(),
					geTuiPushMessage.getOfflineExpireTime(),
					geTuiPushMessage.getPushNetWorkTyp());
			singleMessage.setData(template);
			Target target = new Target();
			target.setAppId(appId);
			target.setClientId(geTuiPushMessage.getCID());
			try {
				ret = push.pushMessageToSingle(singleMessage, target);
			} catch (RequestException e) {
				e.printStackTrace();
				ret = push.pushMessageToSingle(singleMessage, target,
						e.getRequestId());
			}
		}
		else{
			ListMessage listMessage = GeTuiMessage.getListMessage(
					geTuiPushMessage.isOffline(),
					geTuiPushMessage.getOfflineExpireTime(),
					geTuiPushMessage.getPushNetWorkTyp());
			List<Target> targets = new ArrayList<Target>();
			for (String cid : CIDs) {
				Target target = new Target();
				target.setAppId(appId);
				target.setClientId(cid);
				targets.add(target);
			}
			listMessage.setData(template);
			ret = push.pushMessageToList(
					push.getContentId(listMessage), targets);
		}
		if (ret != null) {
			System.out.println(ret.getResponse().toString());
			return ret.getResponse().toString();
		} else {
			System.out.println("服务器响应异常");
			return "服务器响应异常";
		}
	}
	/**
	 * 透传到整个app
	 * @param geTuiPushMessage
	 * @param geTuiPushTemplate
	 * @return
	 */
	@Override
	public String transmissionToApp(GeTuiPushMessage geTuiPushMessage,GeTuiPushTemplate geTuiPushTemplate){
			IGtPush push = new IGtPush(host, appkey, master);
			TransmissionTemplate template = GeTuiITemplate.productTemplate(appId, appkey,
					TransmissionTemplate.class);		
			APNPayload payload = new APNPayload();
		    payload.setBadge(1);
		   // payload.setContentAvailable(1);
		    payload.setSound("default");
		    payload.setCategory("");
			 APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
			    alertMsg.setBody(geTuiPushTemplate.getText());
			    alertMsg.setActionLocKey(geTuiPushTemplate.getText());
			    alertMsg.setLocKey(geTuiPushTemplate.getText());
			    alertMsg.addLocArg("loc-args");
			    alertMsg.setLaunchImage("launch-image");
			    // IOS8.2以上版本支持
			    alertMsg.setTitle(title);
			    alertMsg.setTitleLocKey(title);
			    alertMsg.addTitleLocArg("TitleLocArg");
			    payload.setAlertMsg(alertMsg);
			template.setAPNInfo(payload);
			AppMessage message = GeTuiMessage.getAppMessage(
					geTuiPushMessage.isOffline(),
					geTuiPushMessage.getOfflineExpireTime(),
					geTuiPushMessage.getPushNetWorkTyp(),
					geTuiPushMessage.getAppIdList(),
					geTuiPushMessage.getPhoneTypeList(),
					geTuiPushMessage.getProvinceList(),
					geTuiPushMessage.getTagList(),
					geTuiPushMessage.getSpeed()
					);
			template.setTransmissionContent(geTuiPushTemplate.getTransmissionContent());
			template.setTransmissionType(geTuiPushTemplate.getTransmissionType());
	        message.setData(template);
			IPushResult ret = push.pushMessageToApp(message);
			if (ret != null) {
				System.out.println(ret.getResponse().toString());
				return ret.getResponse().toString();
			} else {
				System.out.println("服务器响应异常");
				return "服务器响应异常";
			}
	}
	/**
	 * 通知(打开应用)到整个app
	 * @param geTuiPushMessage
	 * @param geTuiPushTemplate
	 * @return
	 */
	@Override
	public String notificationToApp(GeTuiPushMessage geTuiPushMessage,GeTuiPushTemplate geTuiPushTemplate){
			IGtPush push = new IGtPush(host, appkey, master);
			NotificationTemplate template = GeTuiITemplate.productTemplate(appId, appkey,
					NotificationTemplate.class);		
			AppMessage message = GeTuiMessage.getAppMessage(
					geTuiPushMessage.isOffline(),
					geTuiPushMessage.getOfflineExpireTime(),
					geTuiPushMessage.getPushNetWorkTyp(),
					geTuiPushMessage.getAppIdList(),
					geTuiPushMessage.getPhoneTypeList(),
					geTuiPushMessage.getProvinceList(),
					geTuiPushMessage.getTagList(),
					geTuiPushMessage.getSpeed()
					);
			template.setText(geTuiPushTemplate.getText());
			template.setTransmissionContent(geTuiPushTemplate.getTransmissionContent());
			template.setTitle(this.title);
			if(geTuiPushTemplate.getTitle()!=null&&!"".equals(geTuiPushTemplate.getTitle()))
			template.setTitle(geTuiPushTemplate.getTitle());
			template.setLogo(geTuiPushTemplate.getLogo());
			template.setLogoUrl(this.logoUrl);
			if(geTuiPushTemplate.getLogoUrl()!=null&&!"".equals(geTuiPushTemplate.getLogoUrl()))
			template.setLogoUrl(geTuiPushTemplate.getLogoUrl());
			template.setTransmissionType(geTuiPushTemplate.getTransmissionType());
			template.setIsRing(geTuiPushTemplate.getIsRing());
			template.setIsClearable(geTuiPushTemplate.getIsClearable());
			template.setIsVibrate(geTuiPushTemplate.getIsVibrate());
	        message.setData(template);
			IPushResult ret = push.pushMessageToApp(message);
			if (ret != null) {
				System.out.println(ret.getResponse().toString());
				return ret.getResponse().toString();
			} else {
				System.out.println("服务器响应异常");
				return "服务器响应异常";
			}
	}
	/**
	 * 通知(打开网页)到整个app
	 * @param geTuiPushMessage
	 * @param geTuiPushTemplate
	 * @return
	 */
	@Override
	public String linkToApp(GeTuiPushMessage geTuiPushMessage,GeTuiPushTemplate geTuiPushTemplate){
			IGtPush push = new IGtPush(host, appkey, master);
			LinkTemplate template = GeTuiITemplate.productTemplate(appId, appkey,
					LinkTemplate.class);		
			AppMessage message = GeTuiMessage.getAppMessage(
					geTuiPushMessage.isOffline(),
					geTuiPushMessage.getOfflineExpireTime(),
					geTuiPushMessage.getPushNetWorkTyp(),
					geTuiPushMessage.getAppIdList(),
					geTuiPushMessage.getPhoneTypeList(),
					geTuiPushMessage.getProvinceList(),
					geTuiPushMessage.getTagList(),
					geTuiPushMessage.getSpeed()
					);
			template.setText(geTuiPushTemplate.getText());
			template.setTitle(this.title);
			if(geTuiPushTemplate.getTitle()!=null&&!"".equals(geTuiPushTemplate.getTitle()))
			template.setTitle(geTuiPushTemplate.getTitle());
			template.setLogo(geTuiPushTemplate.getLogo());
			template.setLogoUrl(this.logoUrl);
			if(geTuiPushTemplate.getLogoUrl()!=null&&!"".equals(geTuiPushTemplate.getLogoUrl()))
			template.setLogoUrl(geTuiPushTemplate.getLogoUrl());
			template.setUrl(geTuiPushTemplate.getUrl());
			template.setIsRing(geTuiPushTemplate.getIsRing());
			template.setIsClearable(geTuiPushTemplate.getIsClearable());
			template.setIsVibrate(geTuiPushTemplate.getIsVibrate());
	        message.setData(template);
			IPushResult ret = push.pushMessageToApp(message);
			if (ret != null) {
				System.out.println(ret.getResponse().toString());
				return ret.getResponse().toString();
			} else {
				System.out.println("服务器响应异常");
				return "服务器响应异常";
			}
	}
}
