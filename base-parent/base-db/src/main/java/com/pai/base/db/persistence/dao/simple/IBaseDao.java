package com.pai.base.db.persistence.dao.simple;

import java.util.List;
import java.util.Map;

import com.pai.base.api.model.Page;

public interface IBaseDao<PK, P> {
	
	public void create(P po);

	public void update(P po);

	public void updateByKey(String sqlKey, Map<String, Object> params);

	public void delete(PK id);

	public void deleteByKey(String sqlKey, Map<String, Object> params);

	public void deleteByIds(List<PK> ids);

	/**
	 * 查询单个PO
	 * 
	 * @param id
	 * @return
	 */
	public P get(PK id);

	/**
	 * 查询最新的PO
	 * 
	 * @return
	 */
	public P getLast();

	/**
	 * 查询count方法
	 * 
	 * @return
	 */
	public Integer countAll();

	/**
	 * 查询count方法，传入参数
	 * 
	 * @param params
	 * @return
	 */
	public Integer count(Map<String, Object> params);

	/**
	 * 根据sqlKey进行count sql执行，支持参数传入
	 * 
	 * @param sqlKey
	 * @param params
	 * @return
	 */
	public Integer countByKey(String sqlKey, Map<String, Object> params);

	public List<P> findAll();

	public List<P> findPaged(Page page);

	public List<P> findPaged(Map<String, Object> params, Page page);

	public List<P> findByIds(List<PK> ids);

	/**
	 * 返回单条数据，如 select count(*) from table_a
	 * 
	 * @param sqlKey
	 * @param params
	 * @return
	 */
	public P getByKey(String sqlKey, Object params);

	/**
	 * 根据在Map配置文件中的Sql Key及参数获取实列表
	 * 
	 * @param sqlKey
	 * @param params
	 * @return
	 */
	public List<P> findByKey(String sqlKey, Map<String, Object> params);

	/**
	 * 根据在Map配置文件中的Sql Key及参数分页获取实列表
	 * 
	 * @param sqlKey
	 * @param params
	 * @param page
	 * @return
	 */
	public List<P> findByKey(String sqlKey, Map<String, Object> params, Page page);

	public List<P> findAll(Map<String, Object> params);

}
