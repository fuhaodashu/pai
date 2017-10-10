package com.pai.base.db.persistence.dao.simple;

import javax.annotation.Resource;

import com.pai.base.api.service.IdGenerator;
import com.pai.base.db.persistence.dao.GenericDao;
import com.pai.base.db.persistence.entity.AbstractPo;

public abstract class AbstractBaseDao<PK extends java.io.Serializable, P extends AbstractPo> extends GenericDao {

	@Resource
	protected IdGenerator idGenerator;
}
