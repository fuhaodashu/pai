package com.pai.service.getui;

import java.util.List;

import com.pai.service.getui.entity.GeTuiPushMessage;
import com.pai.service.getui.entity.GeTuiPushNPLTemplate;
import com.pai.service.getui.entity.GeTuiPushTemplate;

public interface GeTuiService {

	String transmissionToSingle(String mes,String content, String CID, int transmissionType);

	String notificationToSingle(String mes, String content, String CID,
			int transmissionType, String title, String logo, String logoUrl);

	String LinkTemplateToSingle(String mes, String CID, String title,
			String logo, String logoUr, String url);

	String transmissionToList(String mes, List<String> CID, int transmissionType);

	String notificationToList(String mes, String content, List<String> CID,
			int transmissionType, String title, String logo, String logoUrl);

	String LinkTemplateToList(String mes, List<String> CID, String title,
			String logo, String logoUr, String url);

	String transmissionToSingleOrList(List<String> CIDs,
			GeTuiPushMessage geTuiPushMessage,
			GeTuiPushTemplate geTuiPushTemplate);

	String notificationToSingleOrList(List<String> CIDs,
			GeTuiPushMessage geTuiPushMessage,
			GeTuiPushTemplate geTuiPushTemplate);

	String LinkTemplateToSingleOrList(List<String> CIDs,
			GeTuiPushMessage geTuiPushMessage,
			GeTuiPushTemplate geTuiPushTemplate);

	String NPLTemplateToSingleOrList(List<String> CIDs,
			GeTuiPushMessage geTuiPushMessage,
			GeTuiPushNPLTemplate geTuiPushNPLTemplate);

	String transmissionToApp(GeTuiPushMessage geTuiPushMessage,
			GeTuiPushTemplate geTuiPushTemplate);

	String notificationToApp(GeTuiPushMessage geTuiPushMessage,
			GeTuiPushTemplate geTuiPushTemplate);

	String linkToApp(GeTuiPushMessage geTuiPushMessage,
			GeTuiPushTemplate geTuiPushTemplate);

		
}
