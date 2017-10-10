package com.pai.base.db.api.query;
import java.util.List;

public abstract interface FieldLogic extends SqlClause {
	public abstract List<SqlClause> getWhereClauses();
}