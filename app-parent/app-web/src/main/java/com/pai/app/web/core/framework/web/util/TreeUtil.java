package com.pai.app.web.core.framework.web.util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TreeUtil {

	public static JSONArray toTree(List<?> list, String idField, String parentIdField, String childrenField) throws Exception {
		JSONArray array = JSONArray.parseArray(JSONArray.toJSONString(list));
		JSONArray result = new JSONArray();
		JSONArray data = new JSONArray();
		for(int i = 0; i < array.size(); i++) {
			JSONObject object = array.getJSONObject(i);
			if(object.getString(parentIdField) == null) {
				result.add(object);
			} else {
				data.add(object);
			}
		}
		for(int i = 0; i < result.size(); i++) {
			JSONObject object = result.getJSONObject(i);
			JSONArray children = toTree(object.getString(idField), data, parentIdField, idField, childrenField);
			if(children.size() > 0) {
				object.put(childrenField, children);
			}
		}
		
		return result;
	}
	
	private static JSONArray toTree(String parendId, JSONArray array, String parentIdField, String idField, String childrenField) throws Exception {
		JSONArray result = new JSONArray();
		JSONArray data = new JSONArray();
		for(int i = 0; i < array.size(); i++) {
			JSONObject object = array.getJSONObject(i);
			if(object.getString(parentIdField).equals(parendId)) {
				result.add(object);
			} else {
				data.add(object);
			}
		}
		for(int i = 0; i < result.size(); i++) {
			JSONObject object = result.getJSONObject(i);
			JSONArray children = toTree(object.getString(idField), data, parentIdField, idField, childrenField);
			if(children.size() > 0) {
				object.put(childrenField, children);
			}
		}
		
		return result;
	}
}
