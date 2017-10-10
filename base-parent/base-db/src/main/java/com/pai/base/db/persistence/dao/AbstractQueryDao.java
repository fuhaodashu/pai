package com.pai.base.db.persistence.dao;

import com.pai.base.api.service.IdGenerator;
import com.pai.base.db.persistence.entity.AbstractPo;
/**
 * 对象功能:用户 Repository接口
 * 开发公司:PI.COM
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 */
public abstract class AbstractQueryDao<PK extends java.io.Serializable,T extends AbstractPo> extends GenericDao implements IQueryDao<PK, T> {
	protected IdGenerator idGenerator;
}
