package com.pai.base.db.persistence.dao;

import java.util.List;
import java.util.Map;

import com.pai.base.api.model.Page;
/**
 * 对象功能:用户 Repository接口
 * 开发公司:PI.COM
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 */
public interface IQueryDao<PK,P>  {
	/**
	 * 查询单个PO
	 * @param id
	 * @return
	 */
	public P get(PK id);
	/**
	 * 查询最新的PO
	 * @return
	 */
	public P getLast();
	/**
	 * 查询count方法
	 * @return
	 */
	public Integer countAll();
	
	/**
	 * 查询count方法，传入参数
	 * @param params
	 * @return
	 */
	public Integer count(Map<String, Object> params);
	
	/**
	 * 根据sqlKey进行count sql执行，支持参数传入
	 * @param sqlKey
	 * @param params
	 * @return
	 */
	public Integer countByKey(String sqlKey,Map<String, Object> params);
	/**
	 * 查找所有数据集合
	 * @return
	 */
	public List<P> findAll();
	/**
	 * 分页查找数据集合
	 * @param page
	 * @return
	 */
	public List<P> findPaged(Page page);
	/**
	 * 根据参数分页查找数据集合
	 * @param params
	 * @param page
	 * @return
	 */
	public List<P> findPaged(Map<String, Object> params,Page page);
	/**
	 * 根据id集查找数据集合
	 * @param ids
	 * @return
	 */
	public List<P> findByIds(List<PK> ids);	
	/**
	 * 返回单条数据，如 select count(*) from table_a 
	 * @param sqlKey
	 * @param params
	 * @return
	 */
	public P getByKey(String sqlKey,Object params);
    /**
     * 根据在Map配置文件中的Sql Key及参数获取实列表
     * @param sqlKey
     * @param params
     * @return
     */
    public List<P> findByKey(String sqlKey,Map<String,Object> params);
    /**
     * 根据在Map配置文件中的Sql Key及参数分页获取实列表
     * @param sqlKey
     * @param params
     * @param page
     * @return
     */
    public List<P> findByKey(String sqlKey,Map<String,Object> params,Page page);
    /**
     * 根据map传参查找所有数据集合
     * @param params
     * @return
     */
	public List<P> findAll(Map<String, Object> params);
}
