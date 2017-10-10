package com.pai.base.db.api.constants;
/**
 * @author yuanli
 *
 */
public enum OrderByType {
	DESC("desc"),
	ASC("asc");
	private String code;
	OrderByType(String code_){
		code = code_;
	}
	public String getCode() {
		return code;
	}
}
