package com.pai.base.db.api.datasource;

import javax.sql.DataSource;

public interface MultiDataSource extends DataSource{
	public DataSource getDataSource();
}
