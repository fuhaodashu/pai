package com.pai.biz.message.util;

import java.util.HashMap;
import java.util.Map;

import com.pai.base.core.util.ConfigHelper;

public class NotifyParamUtil {

	public static Map<Integer, Integer> getSendTime() {
		Map<Integer, Integer> notifyParam = new HashMap<Integer, Integer>();
		notifyParam.put(1, ConfigHelper.getInstance().getInt("message.send.1.time"));
		notifyParam.put(2, ConfigHelper.getInstance().getInt("message.send.2.time"));
		notifyParam.put(3, ConfigHelper.getInstance().getInt("message.send.3.time"));
		notifyParam.put(4, ConfigHelper.getInstance().getInt("message.send.4.time"));
		notifyParam.put(5, ConfigHelper.getInstance().getInt("message.send.5.time"));
		return notifyParam;
	}
	
}
