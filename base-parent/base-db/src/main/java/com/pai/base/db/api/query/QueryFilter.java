package com.pai.base.db.api.query;

import java.util.List;
import java.util.Map;

import com.pai.base.api.model.Page;
import com.pai.base.db.api.model.FieldSort;

public abstract interface QueryFilter {
	public abstract Page getPage();

	public abstract FieldLogic getFieldLogic();

	public abstract Map<String, Object> getParams();

	public abstract List<FieldSort> getFieldSortList();

	public abstract void addFilter(String paramString, Object paramObject,
			QueryOP paramQueryOP);

	public abstract void addParamsFilter(String paramString, Object paramObject);
}