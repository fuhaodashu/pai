package com.pai.app.web.core.framework.web.controller;

import net.sf.json.util.JSONStringer;

import com.pai.app.web.core.framework.web.entity.DWZResultInfo;

public abstract class LigerUIController extends GenericController{

	protected final String convertToJson(DWZResultInfo info) {
		JSONStringer stringer = new JSONStringer();
		stringer.object();
		stringer.key("statusCode");
		stringer.value(info.getStatusCode());
		stringer.key("message");
		stringer.value(info.getMessage());
		stringer.key("navTabId");
		stringer.value(info.getNavTabId());
		stringer.key("callbackType");
		stringer.value(info.getCallbackType());	
		stringer.key("forwardUrl");
		stringer.value(info.getForwardUrl());
		stringer.endObject();
		return stringer.toString();
	}
}
