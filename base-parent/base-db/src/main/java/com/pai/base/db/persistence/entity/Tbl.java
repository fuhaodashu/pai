package com.pai.base.db.persistence.entity;

import java.util.Date;

public interface Tbl<PK> {
	public PK getId();
	public String getName();
	public String getCreateBy();
	public Date getCreateTime();
	public String getUpdateBy();
	public Date getUpdateTime();
}
