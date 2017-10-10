package com.pai.base.db.api.query;
public enum QueryOP {
	EQUAL, EQUAL_IGNORE_CASE, LESS, GREAT, LESS_EQUAL, GREAT_EQUAL, NOT_EQUAL, LIKE, LEFT_LIKE, RIGHT_LIKE, IS_NULL, NOTNULL, IN, NOT_IN, BETWEEN;

	private String val;
	private String op;
	private String desc;

	public String value() {
		return this.val;
	}

	public String op() {
		return this.op;
	}

	public String desc() {
		return this.desc;
	}

	public static QueryOP getByOP(String op) {
		for (QueryOP queryOP : values()) {
			if (queryOP.op().equals(op)) {
				return queryOP;
			}
		}
		return null;
	}

	public static QueryOP getByVal(String val) {
		for (QueryOP queryOP : values()) {
			if (queryOP.val.equals(val)) {
				return queryOP;
			}
		}
		return null;
	}
}