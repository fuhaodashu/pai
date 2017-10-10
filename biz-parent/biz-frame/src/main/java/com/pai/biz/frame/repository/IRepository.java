package com.pai.biz.frame.repository;

import java.util.List;
import java.util.Map;

import com.pai.base.api.model.Page;
/**
 * 对象功能:用户 Repository接口
 * 开发公司:PI.COM
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 */
public interface IRepository<PK,P,D> {
	public D load(PK id);
	public D newInstance();
	public D newInstance(P po);
	public D getLast();
	public Integer countAll();
	public Integer count(Map<String, Object> params);
	public P get(PK id);
	public List<P> findAll();
	public List<P> findPaged(Page page);
	public List<P> findPaged(Map<String, Object> params,Page page);
	public List<P> findByIds(List<PK> ids);
	public List<P> findAll(Map<String, Object> params);
	
}
