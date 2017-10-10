package com.pai.base.db.api.constants;
/**
 * sql 比较符号 
 *
 */
public enum Operator {
	EQ("="),//等于
	LE("<="),//小于等于
	GE(">="),//大于等于
	LT("<"),//小于
	GT(">"),//大于
	NE("!="),//不等于
	LIKE("like"),//模糊查询
	ISNULL("is"),//是
	ISNOTNULL("is not");//不是
	private String code;
	private Operator(String code_) {
		code = code_;
	}
	public String getCode(){
		return code;
	}
}
