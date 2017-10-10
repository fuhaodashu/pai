package com.pai.app.web.core.framework.web.util;

import java.util.List;

import net.sf.json.util.JSONStringer;

import com.pai.base.db.persistence.entity.PO;
import com.pai.base.db.persistence.entity.TreeType;

public class LigerUIUtils {
	public static String toSimpleJsonData(List<? extends PO> pos) {
		JSONStringer stringer = new JSONStringer();
		stringer.array();
		for(PO po:pos){
			stringer.object();
			stringer.key("text");
			stringer.value(po.getName());
			stringer.key("id");
			stringer.value(po.getId());
			stringer.endObject();
		}
		stringer.endArray();
		return stringer.toString();
	}
	
	/**
	 * 将Po集合转换成简单的树Json数据。每个节点只有id和name，如果有子节点，则由children
	 * @param pos
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String toSimpleTreeData(List<? extends PO> pos) {
		JSONStringer stringer = new JSONStringer();
		stringer.array();
		for(PO po:pos){
			appendItem(stringer, po);
		}
		stringer.endArray();
		return stringer.toString();
	}
	
	private static void appendItem(JSONStringer stringer,PO po) {
		stringer.object();
		stringer.key("id");
		stringer.value(((TreeType<PO>)po).getId());
		stringer.key("name");
		stringer.value(((TreeType<PO>)po).getName());
		if(((TreeType<PO>)po).getSubs().size()>0){
			appendSub(stringer, ((TreeType<PO>)po).getSubs());
		}
		stringer.endObject();		
	}
	
	private static void appendSub(JSONStringer stringer,List<PO> pos) {
		stringer.key("children");
		stringer.array();
		for(PO po:pos){
			appendItem(stringer, po);
		}
		stringer.endArray();
	}	
}
