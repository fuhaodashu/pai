package com.pai.service.solr.solr.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.pai.service.solr.solr.entity.SolrBaseEntity;
import com.pai.service.solr.solr.page.Page;

/**
 * 描述：Solr API接口
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 * </pre>
 */
public abstract interface SolrAPI {
	
	  /**
	   * 根据ID 单个查询
	   * @param endPoint solr模块请求地址
	   * @param id 主键ID
	   * @param objectClass solr模块对象
	   * @return T
	   * @exception 
	   * @since  1.0.0
	   */
	  public <T extends SolrBaseEntity> T findById(String endPoint, String id, Class<T> objectClass);
	  
	  
	  /**
	   * 根据ID 批量查询
	   * @param endPoint solr模块请求地址
	   * @param ids 主键ID集合
	   * @param objectClass solr模块对象
	   * @return List<T>
	   * @exception 
	   * @since  1.0.0
	   */
	  public <T extends SolrBaseEntity> List<T> findByIds(String endPoint, List<String> ids, Class<T> objectClass);
	  
	  
	 /**
	   * 获得 Query响应对象，
	   * @param endPoint solr模块请求地址
	   * @param query (注意：带特殊符号的参数值 记得用ClientUtils.escapeQueryChars转义)
	   * @return QueryResponse {.getResults()=doc结果集，.getBeans(classType)=对象结果集，.getHighlighting()=高亮结果集, .getFacetFields()=分片结果集}
	   * @exception 
	   * @since  1.0.0
	   */
	  public QueryResponse query(String endPoint, SolrQuery query);
	  
	  /**
	   * 查询
	   * @param endPoint solr模块请求地址
	   * @param keyword 关键字/ query语法
	   * @param objectClass solr模块对象
	   * @param fields （非必填） 返回的字段  , 默认全部
	   * @return List<T>
	   * @exception 
	   * @since  1.0.0
	   */
	  public <T extends SolrBaseEntity> List<T> query(String endPoint, String keyword, Class<T> objectClass, String... fields);
	  
	  /**
	   * 分页查询
	   * @param endPoint solr模块请求地址
	   * @param keyword 关键字/ query语法
	   * @param objectClass solr模块对象
	   * @param pageNo 页码数
	   * @param pageSize  返回条数
	   * @param fields （非必填） 返回的字段 , 默认全部
	   * @return List<T>
	   * @exception 
	   * @since  1.0.0
	   */
	  public <T extends SolrBaseEntity> List<T> query(String endPoint, String keyword, Class<T> objectClass, Integer pageNo, Integer pageSize, LinkedHashMap<String, ORDER> sortMap, String... fields);

	  /**
	   * 自定义 query查询
	   * @param endPoint solr模块请求地址
	   * @param query solr查询对象
	   * @param objectClass solr模块对象
	   * @return List<T>
	   * @exception 
	   * @since  1.0.0
	   */
	  public <T extends SolrBaseEntity> List<T> query(String endPoint, SolrQuery query, Class<T> objectClass);
	  
	  /**
	   * 分页 查询
	   * @param endPoint solr模块请求地址
	   * @param keyword 关键字/ query语法
	   * @param objectClass solr模块对象
	   * @param fields （非必填） 返回的字段  , 默认全部
	   * @return Page<T> 分页对象
	   * @exception 
	   * @since  1.0.0
	   */
	  public <T extends SolrBaseEntity> Page<T> queryPage(String endPoint, String keyword, Class<T> objectClass, String... fields);
	  
	  /**
	   * 分页查询
	   * @param endPoint solr模块请求地址
	   * @param keyword 关键字/ query语法
	   * @param objectClass solr模块对象
	   * @param pageNo 页码数
	   * @param pageSize  返回条数
	   * @param fields （非必填） 返回的字段  , 默认全部
	   * @return Page<T> 分页对象
	   * @exception 
	   * @since  1.0.0
	   */
	  public <T extends SolrBaseEntity> Page<T> queryPage(String endPoint, String keyword, Class<T> objectClass, Integer pageNo, Integer pageSize, LinkedHashMap<String, ORDER> sortMap, String... fields);

	  /**
	   * 自定义 query查询
	   * @param endPoint solr模块请求地址
	   * @param query solr查询对象 (注意：带特殊符号的参数值 记得用ClientUtils.escapeQueryChars转义)
	   * @param objectClass solr模块对象 
	   * @return Page<T> 分页对象
	   * @exception 
	   * @since  1.0.0
	   */
	  public <T extends SolrBaseEntity> Page<T> queryPage(String endPoint, SolrQuery query, Class<T> objectClass);
	  
	  /**
	   * 添加索引数据
	   * @param endPoint solr模块请求地址
	   * @param obj solr模块对象
	   * @exception 
	   * @since  1.0.0
	   */
	  public <T extends SolrBaseEntity> boolean addObject(String endPoint, T obj);

	  /**
	   * 批量 添加索引数据
	   * @param endPoint solr模块请求地址
	   * @param list solr模块对象 集合 
	   * @exception 
	   * @since  1.0.0
	   */
	  public <T extends SolrBaseEntity> boolean addList(String endPoint, List<T> list);

	  /**
	   * 根据 语句删除 索引记录
	   * @param endPoint solr模块请求地址
	   * @param queryStr 例如（状态为禁用的索引记录）：status:disable
	   * @exception 
	   * @since  1.0.0
	   */
	  public boolean deleteByQuery(String endPoint, String queryStr);

	  /**
	   * 根据 id 删除 索引记录
	   * @param endPoint solr模块请求地址
	   * @param id 
	   * @exception 
	   * @since  1.0.0
	   */
	  public boolean deleteById(String endPoint, String id);

	  /**
	   * 批量删除索引记录
	   * @param endPoint solr模块请求地址
	   * @param ids 索引主键值list
	   * @exception 
	   * @since  1.0.0
	   */
	  public boolean deleteByIds(String endPoint, List<String> ids);
	  
	  /**
	   * 删除 指定solr模块 所有索引数据
	   * @param endPoint solr模块请求地址
	   * @exception 
	   * @since  1.0.0
	   */
	  public boolean deleteAll(String endPoint);
	  
	  /**
	   * ping服务器是否连接成功
	   * @param endPoint solr模块请求地址
	   * @return String  响应信息
	   * @exception 
	   * @since  1.0.0
	   */
	  public String ping(String endPoint);

	  /**
	   * 优化(碎片整理，优化索引结构,合并索引文件) 提高性能，但需要一定的时间
	   * <br/> 注意： 建议定时调用
	   * @param endPoint  solr模块请求地址
	   * @exception 
	   * @since  1.0.0
	   */
	  public void optimize(String endPoint);
	  
	  /**
	   * 过滤 分片数据（可以统计关键字及出现的次数、或是做自动补全提示）
	   * @param facets  分片数据
	   * @param wordMinLen  分片词语 最小长度
	   * @return Map<String,Long>
	   * @exception 
	   * @since  1.0.0
	   */
	  public Map<String, Long> filterFacetData(List<FacetField> facets, int wordMinLen);
}