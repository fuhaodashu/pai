package com.pai.base.db.persistence.dao;

import javax.annotation.Resource;

import com.pai.base.api.service.IdGenerator;
import com.pai.base.db.persistence.entity.AbstractPo;

public abstract class AbstractDao<PK extends java.io.Serializable,T extends AbstractPo> extends GenericDao implements IDao<PK, T> {
	@Resource
	protected IdGenerator idGenerator;
}
