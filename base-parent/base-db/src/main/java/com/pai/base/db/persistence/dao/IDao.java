package com.pai.base.db.persistence.dao;

import java.util.List;
import java.util.Map;

public interface IDao<PK,P> {	
	public void create(P po);
	public void update(P po);
	public void updateByKey(String sqlKey,Map<String, Object> params);
	public void updateByExampleSelective(P po,Map<String, Object> params);
	public void delete(PK id);
	public void deleteByKey(String sqlKey,Map<String, Object> params);
	public void deleteByIds(List<PK> ids);
}
