//package com.pai.service.getui;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.springframework.test.context.ContextConfiguration;
//
//import com.pai.base.core.test.BaseTestCase;
//import com.pai.service.getui.entity.GeTuiPushMessage;
//import com.pai.service.getui.entity.GeTuiPushNPLTemplate;
//import com.pai.service.getui.entity.GeTuiPushTemplate;
//
//@ContextConfiguration(locations = {"classpath:conf/getui-context-test.xml"})
//public class GeTuiTest extends BaseTestCase {
//	@Resource
//	private GeTuiService geTuiService;
//	String CID="ec2e50bb0454532d5faed13e12cbbfd9";
//	String appid="KIeMep5xa09cORJEJXb142";
//	@Test
//	public void testTransmissionToSingle(){
//		try {
//			geTuiService.transmissionToSingle("测试","内容", CID, 2);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void testTransmissionToSingleX(){
//		try {
//			List<String> cids=new ArrayList<String>();
//			cids.add(CID);
//			GeTuiPushMessage geTuiPushMessage=new GeTuiPushMessage();
//			geTuiPushMessage.setCID(CID);
//			geTuiPushMessage.setOffline(true);
//			geTuiPushMessage.setOfflineExpireTime(10*1000);
//			geTuiPushMessage.setPushNetWorkTyp(0);
//			GeTuiPushTemplate geTuiPushTemplate=new GeTuiPushTemplate();
//			geTuiPushTemplate.setTitle("沃日");
//			geTuiPushTemplate.setTransmissionContent("尼玛");
//			geTuiPushTemplate.setTransmissionType(1);
//			geTuiService.transmissionToSingleOrList(null,geTuiPushMessage,geTuiPushTemplate);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void testTransmissionToSingleList(){
//		try {
//			List<String> cids=new ArrayList<String>();
//			cids.add(CID);
//			geTuiService.transmissionToList("哇哈哈", cids, 1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void testNotificationToSingle(){
//		try {
//			geTuiService.notificationToSingle("测试","测试", CID, 1,"测试","icon.png","http://img.pai.com/paiBbs/BbsMemberProfile/C4XDD7TY-VFDJ-O1P3-1446028363264-O8PTUICQ4T1F.gif");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void testNotificationToSingleX(){
//		try {
//			List<String> cids=new ArrayList<String>();
//			cids.add(CID);
//			GeTuiPushMessage geTuiPushMessage=new GeTuiPushMessage();
//			geTuiPushMessage.setCID(CID);
//			geTuiPushMessage.setOffline(true);
//			geTuiPushMessage.setOfflineExpireTime(1*1000);
//			geTuiPushMessage.setPushNetWorkTyp(0);
//			GeTuiPushTemplate geTuiPushTemplate=new GeTuiPushTemplate();
//			geTuiPushTemplate.setTitle("沃日");
//			geTuiPushTemplate.setText("尼玛");
//			geTuiPushTemplate.setLogo("icon.png");
//			//geTuiPushTemplate.setLogoUrl("http://img.pai.com/paiBbs/BbsMemberProfile/C4XDD7TY-VFDJ-O1P3-1446028363264-O8PTUICQ4T1F.gif");
//			geTuiPushTemplate.setTransmissionType(1);
//			geTuiPushTemplate.setIsClearable(false);
//			geTuiPushTemplate.setIsRing(false);
//			geTuiPushTemplate.setIsVibrate(true);
//			geTuiService.notificationToSingleOrList(null,geTuiPushMessage,geTuiPushTemplate);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void testNotificationToList(){
//		try {
//			List<String> cids=new ArrayList<String>();
//			cids.add(CID);
//			geTuiService.notificationToList("你妈逼","尼玛",cids, 1,"标题","icon.png","http://img.pai.com/paiBbs/BbsMemberProfile/C4XDD7TY-VFDJ-O1P3-1446028363264-O8PTUICQ4T1F.gif");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void testLinkTemplateToSingle(){
//		try {
//			geTuiService.LinkTemplateToSingle("你妈逼", CID,"标题","icon.png","http://img.pai.com/paiBbs/BbsMemberProfile/C4XDD7TY-VFDJ-O1P3-1446028363264-O8PTUICQ4T1F.gif","http://q.pai.com");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void testLinkTemplateToSingleX(){
//		try {
//			List<String> cids=new ArrayList<String>();
//			cids.add(CID);
//			GeTuiPushMessage geTuiPushMessage=new GeTuiPushMessage();
//			geTuiPushMessage.setCID(CID);
//			geTuiPushMessage.setOffline(true);
//			geTuiPushMessage.setOfflineExpireTime(1*1000);
//			geTuiPushMessage.setPushNetWorkTyp(0);
//			GeTuiPushTemplate geTuiPushTemplate=new GeTuiPushTemplate();
//			geTuiPushTemplate.setTitle("沃日");
//			geTuiPushTemplate.setText("尼玛");
//			geTuiPushTemplate.setLogo("icon.png");
//			geTuiPushTemplate.setLogoUrl("http://img.pai.com/paiBbs/BbsMemberProfile/C4XDD7TY-VFDJ-O1P3-1446028363264-O8PTUICQ4T1F.gif");
//			geTuiPushTemplate.setIsClearable(false);
//			geTuiPushTemplate.setIsRing(false);
//			geTuiPushTemplate.setIsVibrate(true);
//			geTuiPushTemplate.setUrl("http://q.pai.com");
//			geTuiService.LinkTemplateToSingleOrList(null,geTuiPushMessage,geTuiPushTemplate);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void testLinkTemplateToList(){
//		try {
//			geTuiService.LinkTemplateToSingle("你妈逼", CID,"标题","icon.png","http://img.pai.com/paiBbs/BbsMemberProfile/C4XDD7TY-VFDJ-O1P3-1446028363264-O8PTUICQ4T1F.gif","http://q.pai.com");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void testNPLTemplateToSingleOrList(){
//		try {
//			List<String> cids=new ArrayList<String>();
//			cids.add(CID);
//			GeTuiPushMessage geTuiPushMessage=new GeTuiPushMessage();
//			geTuiPushMessage.setCID(CID);
//			geTuiPushMessage.setOffline(true);
//			geTuiPushMessage.setOfflineExpireTime(10*1000);
//			geTuiPushMessage.setPushNetWorkTyp(0);
//			GeTuiPushNPLTemplate geTuiPushTemplate=new GeTuiPushNPLTemplate();
//			geTuiPushTemplate.setNotyIcon("icon.png");
//			geTuiPushTemplate.setNotyTitle("通知栏标题");
//			geTuiPushTemplate.setNotyContent("通知栏内容");
//			geTuiPushTemplate.setCleared(false);
//			geTuiPushTemplate.setBelled(true);
//			geTuiPushTemplate.setVibrationed(true);
//			geTuiPushTemplate.setPopTitle("弹出框标题");
//			geTuiPushTemplate.setPopContent("弹出框内容");
//			geTuiPushTemplate.setPopImage("http://img.pai.com/paiBbs/BbsMemberProfile/C4XDD7TY-VFDJ-O1P3-1446028363264-O8PTUICQ4T1F.gif");
//			geTuiPushTemplate.setPopButton1("左边按钮");
//			geTuiPushTemplate.setPopButton2("右边按钮");
//			geTuiPushTemplate.setLoadIcon("下载图标");
//			geTuiPushTemplate.setLoadTitle("下载标题");
//			geTuiPushTemplate.setLoadUrl("http://paidownload.oss-cn-hangzhou.aliyuncs.com/pai.apk");
//			geTuiPushTemplate.setAutoInstall(true);
//			geTuiPushTemplate.setActived(true);
//			geTuiPushTemplate.setAndroidMark("");
//			geTuiPushTemplate.setSymbianMark("");
//			geTuiPushTemplate.setIphoneMark("");
//			geTuiService.NPLTemplateToSingleOrList(null, geTuiPushMessage, geTuiPushTemplate);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void testTransmissionToApp(){
//		try {
//			List<String> appIdList = new ArrayList<String>();
//	        List<String> phoneTypeList = new ArrayList<String>();
//	        List<String> provinceList = new ArrayList<String>();
//	        List<String> tagList = new ArrayList<String>();
//	        appIdList.add(appid);
//	        phoneTypeList.add("ANDROID");
//	        phoneTypeList.add("IOS");
//			//provinceList.add("广东");
//			GeTuiPushMessage geTuiPushMessage=new GeTuiPushMessage();
//			geTuiPushMessage.setAppIdList(appIdList);
//			geTuiPushMessage.setPhoneTypeList(phoneTypeList);
//			geTuiPushMessage.setProvinceList(provinceList);
//			geTuiPushMessage.setTagList(tagList);
//			geTuiPushMessage.setOffline(true);
//			geTuiPushMessage.setOfflineExpireTime(10*1000);
//			geTuiPushMessage.setPushNetWorkTyp(0);
//			GeTuiPushTemplate geTuiPushTemplate=new GeTuiPushTemplate();
//			geTuiPushTemplate.setTransmissionContent("尼玛");
//			geTuiPushTemplate.setTransmissionType(2);
//			geTuiService.transmissionToApp(geTuiPushMessage,geTuiPushTemplate);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void testNotificationToApp(){
//		try {
//			List<String> appIdList = new ArrayList<String>();
//	        List<String> phoneTypeList = new ArrayList<String>();
//	        List<String> provinceList = new ArrayList<String>();
//	        List<String> tagList = new ArrayList<String>();
//	        appIdList.add(appid);
//	        phoneTypeList.add("ANDROID");
//	        phoneTypeList.add("IOS");
//			provinceList.add("广东");
//			GeTuiPushMessage geTuiPushMessage=new GeTuiPushMessage();
//			geTuiPushMessage.setAppIdList(appIdList);
//			geTuiPushMessage.setPhoneTypeList(phoneTypeList);
//			geTuiPushMessage.setProvinceList(provinceList);
//			geTuiPushMessage.setTagList(tagList);
//			geTuiPushMessage.setOffline(true);
//			geTuiPushMessage.setOfflineExpireTime(10*1000);
//			geTuiPushMessage.setPushNetWorkTyp(0);
//			GeTuiPushTemplate geTuiPushTemplate=new GeTuiPushTemplate();
//			geTuiPushTemplate.setTitle("沃日");
//			geTuiPushTemplate.setText("尼玛");
//			geTuiPushTemplate.setLogo("icon.png");
//			geTuiPushTemplate.setLogoUrl("http://img.pai.com/paiBbs/BbsMemberProfile/C4XDD7TY-VFDJ-O1P3-1446028363264-O8PTUICQ4T1F.gif");
//			geTuiPushTemplate.setTransmissionType(1);
//			geTuiPushTemplate.setTransmissionContent("哇哈哈哈啊哈哈哈");
//			geTuiPushTemplate.setIsClearable(false);
//			geTuiPushTemplate.setIsRing(false);
//			geTuiPushTemplate.setIsVibrate(true);
//			geTuiService.notificationToApp(geTuiPushMessage,geTuiPushTemplate);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void testLinkToApp(){
//		try {
//			List<String> appIdList = new ArrayList<String>();
//	        List<String> phoneTypeList = new ArrayList<String>();
//	        List<String> provinceList = new ArrayList<String>();
//	        List<String> tagList = new ArrayList<String>();
//	        appIdList.add(appid);
//	        phoneTypeList.add("ANDROID");
//			provinceList.add("广东");
//			GeTuiPushMessage geTuiPushMessage=new GeTuiPushMessage();
//			geTuiPushMessage.setAppIdList(appIdList);
//			geTuiPushMessage.setPhoneTypeList(phoneTypeList);
//			geTuiPushMessage.setProvinceList(provinceList);
//			geTuiPushMessage.setTagList(tagList);
//			geTuiPushMessage.setOffline(true);
//			geTuiPushMessage.setOfflineExpireTime(10*1000);
//			geTuiPushMessage.setPushNetWorkTyp(0);
//			GeTuiPushTemplate geTuiPushTemplate=new GeTuiPushTemplate();
//			geTuiPushTemplate.setTitle("沃日");
//			geTuiPushTemplate.setText("尼玛");
//			geTuiPushTemplate.setLogo("icon.png");
//			geTuiPushTemplate.setLogoUrl("http://img.pai.com/paiBbs/BbsMemberProfile/C4XDD7TY-VFDJ-O1P3-1446028363264-O8PTUICQ4T1F.gif");
//			geTuiPushTemplate.setIsClearable(false);
//			geTuiPushTemplate.setIsRing(false);
//			geTuiPushTemplate.setIsVibrate(true);
//			geTuiPushTemplate.setUrl("http://q.pai.com");
//			geTuiService.linkToApp(geTuiPushMessage,geTuiPushTemplate);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
