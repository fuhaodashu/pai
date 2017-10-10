package com.pai.app.web.core.framework.web.entity;
/**
 * 弹出表格过滤条件
 *
 */

public class Condition {
	private String op;
	private String field;
	private String value;
	private String text;
	
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
}
