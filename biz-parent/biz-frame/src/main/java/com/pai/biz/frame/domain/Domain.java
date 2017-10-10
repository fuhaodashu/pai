package com.pai.biz.frame.domain;


import java.io.Serializable;

import com.pai.base.db.persistence.entity.PO;

public interface Domain<PK extends Serializable,P extends PO<PK>> {
	public PK getId();
	public P getData();
	public void setData(P data_);
	public void save();
	public void create();
	public void update();
	public void destroy();	
	public void destroy(PK id_);
}
