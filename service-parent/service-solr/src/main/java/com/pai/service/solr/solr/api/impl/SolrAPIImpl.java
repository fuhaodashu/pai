package com.pai.service.solr.solr.api.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SolrPingResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pai.base.core.util.string.StringUtils;
import com.pai.service.solr.solr.api.SolrAPI;
import com.pai.service.solr.solr.entity.SolrBaseEntity;
import com.pai.service.solr.solr.page.Page;
import com.pai.service.solr.solr.server.SolrServer;
import com.pai.service.solr.solr.util.SolrUtil;

/**
 * 描述：Solr API 接口实现类
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 * </pre>
 */
public class SolrAPIImpl implements SolrAPI {
	
	
	private final static Logger LOGGER = LoggerFactory.getLogger(SolrAPIImpl.class);
	
	private SolrServer solrServer;

	public SolrAPIImpl(SolrServer solrServer) {
		this.solrServer = solrServer;
	}
	
	/**
	 * 根据ID 单个查询
	 * @param endPoint solr模块请求地址
	 * @param id 主键ID
	 * @param objectClass solr模块对象
	 * @return T
	 * @exception 
	 * @since  1.0.0
	 */
	@Override
	public <T extends SolrBaseEntity> T findById(String endPoint, String id,
			Class<T> objectClass) {
		SolrQuery query = new SolrQuery("id:"+id);
		List<T> list = query(endPoint, query, objectClass);
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
	
	/**
	 * 根据ID 批量查询
	 * @param endPoint solr模块请求地址
	 * @param ids 主键ID集合
	 * @param objectClass solr模块对象
	 * @return List<T>
	 * @exception 
	 * @since  1.0.0
	 */
	@Override
	public <T extends SolrBaseEntity> List<T> findByIds(String endPoint,
			List<String> ids, Class<T> objectClass) {
		StringBuilder str = new StringBuilder();
		for (String id : ids) {
			str.append("id:" + id + " OR ");
		}
		SolrQuery query = new SolrQuery(str.substring(0, str.length()-4));
		return query(endPoint, query, objectClass);
	}
	
   /**
    * 获得 Query响应对象，
    * @param endPoint solr模块请求地址
    * @param query
    * @return QueryResponse {.getResults()=doc结果集，.getBeans(classType)=对象结果集，.getHighlighting()=高亮结果集, .getFacetFields()=分片结果集}
    * @exception 
    * @since  1.0.0
    */
	@Override
	public QueryResponse query(String endPoint, SolrQuery query) {
		HttpSolrServer server = solrServer.getSolrServer(endPoint);
		try {
			return server.query(query);
		} catch (SolrServerException e) {
			LOGGER.error("get QueryResponse fail;", e);
		}finally{
			solrServer.closeServer(server);
		}
		
		return null;
	}
	
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
	  @Override
	  public <T extends SolrBaseEntity> List<T> query(String endPoint, String keyword, Class<T> objectClass, String... fields){
		  return query(endPoint, keyword, objectClass, null, null, null, fields);
	  }
	  
	  /**
	   * 分页查询
	   * @param endPoint solr模块请求地址
	   * @param keyword 关键字/ query语法
	   * @param objectClass solr模块对象
	   * @param pageNo 页码数
	   * @param pageSize  返回条数
	   * @param fields （非必填） 返回的字段  , 默认全部
	   * @return List<T>
	   * @exception 
	   * @since  1.0.0
	   */
	  @Override
	  public <T extends SolrBaseEntity> List<T> query(String endPoint, String keyword, Class<T> objectClass, Integer pageNo, Integer pageSize, LinkedHashMap<String, ORDER> sortMap, String... fields){
		  if(StringUtils.isBlank(keyword))
			  return null;
		  HttpSolrServer server = solrServer.getSolrServer(endPoint);
		  if(server == null)
			  return null;
		  SolrQuery query = SolrUtil.parseQueryStr(keyword, pageNo, pageSize, sortMap, fields);
		  return getListResult(server, query, objectClass);
	  }

	  /**
	   * 自定义 query查询
	   * @param endPoint solr模块请求地址
	   * @param query solr查询对象
	   * @param objectClass solr模块对象
	   * @return List<T>
	   * @exception 
	   * @since  1.0.0
	   */
	  @Override
	  public <T extends SolrBaseEntity> List<T> query(String endPoint, SolrQuery query, Class<T> objectClass){
		  HttpSolrServer server = solrServer.getSolrServer(endPoint);
		  if(server == null)
			  return null;
		  return getListResult(server, query, objectClass);
	  }

      private <T extends SolrBaseEntity> List<T> getListResult(HttpSolrServer server, SolrQuery query, Class<T> objectClass){
		try {
			QueryResponse response = server.query(query);
			SolrDocumentList docList = response.getResults();
			//是否 高亮
			isHighLighting(query, response, docList);
			DocumentObjectBinder binder = new DocumentObjectBinder();
			return binder.getBeans(objectClass, docList);
		} catch (SolrServerException e) {
			LOGGER.error("solr query fail, queryStr:{};", new Object[]{query.toString(), e});
			return null;
		}finally{
			solrServer.closeServer(server);
		}
	  }
	  
      /**
       * 包装 高亮数据
       * @param docList
       * @param pkId 主键ID
       * @param hlField 高亮字段
       * @param hlValues  高亮后的值
       * @return SolrDocumentList
       * @exception 
       * @since  1.0.0
       */
      @SuppressWarnings("unchecked")
	  private void warpHighLighting(SolrDocumentList docList, String pkId, String hlField, List<String> hlValues, String hl_simple_pre, String hl_simple_post){
		  for (SolrDocument doc : docList) {
			  String id  = (String)doc.get("id");
			  if(pkId.equals(id)){
				  //拿到 高亮字段 原值
				  Object hlFieldOriginalVal = doc.get(hlField);
				  
				  //原值为集合时
				  if(hlFieldOriginalVal instanceof Collection) {
					  List<Object> array = (List<Object>)hlFieldOriginalVal;
					  for (String child : hlValues) {
						 //替换 高亮值的 前后描述
						 String hlVal = child.replace(hl_simple_pre, "").replace(hl_simple_post, "");
						 int dex = array.indexOf(hlVal);
						 if(dex != -1){
							 array.set(dex, child);
						 }
					  }
		    	  }else{
		    		  doc.setField(hlField, hlValues.get(0));
		    	  }
			  }
		  }
       }
      
      /**
       * 是否 高亮
       * @param query
       * @param response
       * @param docList 
       * @exception 
       * @since  1.0.0
       */
      private void isHighLighting(SolrQuery query, QueryResponse response, SolrDocumentList docList){
    	  String hl = query.get("hl");//是否开启高亮
    	  String hlFieldStr = query.get("hl.fl");//高亮字段
    	  String hl_simple_pre = query.get("hl.simple.pre");//高亮 前缀
    	  String hl_simple_post = query.get("hl.simple.post");//高亮 后缀
			if("true".equals(hl) && StringUtils.isNotBlank(hlFieldStr) 
					&& docList != null && docList.size() > 0){
				String[] hlFields = hlFieldStr.split(",");
				//获得 高亮数据
				Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
				if(highlighting != null && highlighting.size() > 0){
					//遍历高亮数据
					for (String id : highlighting.keySet()) {
						//ID对应的 高亮记录
						Map<String, List<String>> hlResult = highlighting.get(id);
						if(hlResult != null && hlResult.size() > 0){
							//为设置高亮的字段 重新设值
							for (String hlField : hlFields) {
								List<String> hlValues = hlResult.get(hlField);//高亮字段 值（为list 是因为 有可能该字段存放的是 list）
								if(hlValues != null){
									//包装 高亮数据
									warpHighLighting(docList, id, hlField, hlValues, hl_simple_pre, hl_simple_post);
								}
							}
						}
					}
				}
			}
      }
      
      
	  private <T extends SolrBaseEntity> Page<T> getPageResult(HttpSolrServer server, SolrQuery query, Class<T> objectClass, Integer pageNo, Integer pageSize){
		  Page<T> page = new Page<T>(pageSize, pageNo);
		  try {
				QueryResponse response = server.query(query);
				SolrDocumentList docList = response.getResults();
				//是否 高亮
				isHighLighting(query, response, docList);
				DocumentObjectBinder binder = new DocumentObjectBinder();
				page.setResult(binder.getBeans(objectClass, docList));
				page.setTotalCount(docList.getNumFound());
			} catch (SolrServerException e) {
				LOGGER.error("solr query fail, queryStr:{};", new Object[]{query.toString(), e});
			}finally{
				solrServer.closeServer(server);
			}
		  	return page;
	  }
	  
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
	  @Override
	  public <T extends SolrBaseEntity> Page<T> queryPage(String endPoint, String keyword, Class<T> objectClass, String... fields){
		  return queryPage(endPoint, keyword, objectClass, null, null, null, fields);
	  }
	  
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
	  @Override
	  public <T extends SolrBaseEntity> Page<T> queryPage(String endPoint, String keyword, Class<T> objectClass, Integer pageNo, Integer pageSize, LinkedHashMap<String, ORDER> sortMap, String... fields){
		  if(StringUtils.isBlank(keyword))
			  return null;
		  HttpSolrServer server = solrServer.getSolrServer(endPoint);
		  if(server == null)
			  return null;
		  SolrQuery query = SolrUtil.parseQueryStr(keyword, pageNo, pageSize, sortMap, fields);
		  return getPageResult(server, query, objectClass, pageNo, pageSize);
	  }

	  /**
	   * 自定义 query查询
	   * @param endPoint solr模块请求地址
	   * @param query solr查询对象
	   * @param objectClass solr模块对象
	   * @return Page<T> 分页对象
	   * @exception 
	   * @since  1.0.0
	   */
	  @Override
	  public <T extends SolrBaseEntity> Page<T> queryPage(String endPoint, SolrQuery query, Class<T> objectClass){
		  HttpSolrServer server = solrServer.getSolrServer(endPoint);
		  if(server == null)
			  return null;
		  int pageNo = (query.getStart()/(query.getRows()<1?10:query.getRows()))+1;
		  return getPageResult(server, query, objectClass, pageNo, query.getRows());
	  }
	  
	  /**
	   * 添加索引数据
	   * @param endPoint solr模块请求地址
	   * @param obj solr模块对象
	   * @exception 
	   * @since  1.0.0
	   */
	  @Override
	  public <T extends SolrBaseEntity> boolean addObject(String endPoint, T obj){
		  HttpSolrServer server = solrServer.getSolrServer(endPoint);
		  if(server == null)
			  return false;
		  try {
				server.addBean(obj);
				server.commit(false, false);
			} catch (Exception e) {
				//回滚
				try {server.rollback();} catch (Exception e1) {}
				LOGGER.error("solr addObject fail, obj:{};", new Object[]{obj, e});
				return false;
			}finally{
				solrServer.closeServer(server);
			}
		  return true;
	  }

	  /**
	   * 批量 添加索引数据
	   * @param endPoint solr模块请求地址
	   * @param list solr模块对象 集合 
	   * @exception 
	   * @since  1.0.0
	   */
	  @Override
	  public <T extends SolrBaseEntity> boolean addList(String endPoint, List<T> list){
		  HttpSolrServer server = solrServer.getSolrServer(endPoint);
		  if(server == null)
			  return false;
		  try {
				server.addBeans(list);
				server.commit(false, false);
			} catch (Exception e) {
				//回滚
				try {server.rollback();} catch (Exception e1) {}
				LOGGER.error("solr addList fail;", e);
				return false;
			}finally{
				solrServer.closeServer(server);
			}
		  return true;
	  }

	  /**
	   * 根据 语句删除 索引记录
	   * @param endPoint solr模块请求地址
	   * @param queryStr 例如（状态为禁用的索引记录）：status:disable
	   * @exception 
	   * @since  1.0.0
	   */
	  @Override
	  public boolean deleteByQuery(String endPoint, String queryStr){
		  HttpSolrServer server = solrServer.getSolrServer(endPoint);
		  if(server == null)
			  return false;
		  try {
				server.deleteByQuery(queryStr);
				server.commit(false, false);
			} catch (Exception e) {
				//回滚
				try {server.rollback();} catch (Exception e1) {}
				LOGGER.error("solr deleteByQuery fail, queryStr:{};", new Object[]{queryStr, e});
				return false;
			}finally{
				solrServer.closeServer(server);
			}
		  return true;
	  }

	  /**
	   * 根据 id 删除 索引记录
	   * @param endPoint solr模块请求地址
	   * @param id 
	   * @exception 
	   * @since  1.0.0
	   */
	  @Override
	  public boolean deleteById(String endPoint, String id){
		  HttpSolrServer server = solrServer.getSolrServer(endPoint);
		  if(server == null)
			  return false;
		  try {
				server.deleteById(id);
				server.commit(false, false);
			} catch (Exception e) {
				//回滚
				try {server.rollback();} catch (Exception e1) {}
				LOGGER.error("solr deleteById fail, id:{};", new Object[]{id, e});
				return false;
			}finally{
				solrServer.closeServer(server);
			}
		  return true;
	  }

	  /**
	   * 批量删除索引记录
	   * @param endPoint solr模块请求地址
	   * @param ids 索引主键值list
	   * @exception 
	   * @since  1.0.0
	   */
	  @Override
	  public boolean deleteByIds(String endPoint, List<String> ids){
		  HttpSolrServer server = solrServer.getSolrServer(endPoint);
		  if(server == null)
			  return false;
		  try {
				server.deleteById(ids);
				server.commit(false, false);
			} catch (Exception e) {
				//回滚
				try {server.rollback();} catch (Exception e1) {}
				LOGGER.error("solr deleteByIds fail;", e);
				return false;
			}finally{
				solrServer.closeServer(server);
			}
		  return true;
	  }
	  /**
	   * 删除 指定solr模块 所有索引数据
	   * @param endPoint solr模块请求地址
	   * @exception 
	   * @since  1.0.0
	   */
	  @Override
	  public boolean deleteAll(String endPoint){
		  HttpSolrServer server = solrServer.getSolrServer(endPoint);
		  if(server == null)
			  return false;
		  try {
				server.deleteByQuery("*:*");
				server.commit(false, false);
			} catch (Exception e) {
				//回滚
				try {server.rollback();} catch (Exception e1) {}
				LOGGER.error("solr deleteAll fail;", e);
				return false;
			}finally{
				solrServer.closeServer(server);
			}
		  return true;
	  }
	  
	  /**
	   * ping服务器是否连接成功
	   * @param endPoint solr模块请求地址
	   * @return String  响应信息
	   * @exception 
	   * @since  1.0.0
	   */
	  @Override
	  public String ping(String endPoint){
		  HttpSolrServer server = solrServer.getSolrServer(endPoint);
		  try {
			    SolrPingResponse response = server.ping();
			    return response.getResponse().toString();
			} catch (Exception e) {
				LOGGER.error("solr ping fail;", e);
				return null;
			}finally{
				solrServer.closeServer(server);
			}
	  }

	  /**
	   * 优化(碎片整理，优化索引结构,合并索引文件) 提高性能，但需要一定的时间
	   * <br/> 注意： 建议定时调用
	   * @param endPoint  solr模块请求地址
	   * @exception 
	   * @since  1.0.0
	   */
	  @Override
	  public void optimize(String endPoint){
		  String module = SolrUtil.getModuleName(endPoint);
		  HttpSolrServer server = solrServer.getSolrServer(endPoint);
		  try {
			  LOGGER.debug("正在优化 {} 索引库...", module);
				long start = System.currentTimeMillis();
				server.optimize(true, false);
				server.optimize(false, false);
				long end = System.currentTimeMillis();
				LOGGER.debug("优化 {} 索引库完毕，花费时间:{} 秒;", new Object[]{module, Long.valueOf((end - start) / 1000L)});
			} catch (Exception ex) {
				LOGGER.error("solr optimize fail, model:{};", new Object[]{module, ex});
			}finally{
				solrServer.closeServer(server);
			}
	  }
	  
	  /**
	   * 过滤 分片数据（可以统计关键字及出现的次数、或是做自动补全提示）
	   * @param facets  分片数据
	   * @param wordMinLen  分片词语 最小长度
	   * @return Map<String,Long>
	   * @exception 
	   * @since  1.0.0
	   */
	  @Override
	  public Map<String, Long> filterFacetData(List<FacetField> facets, int wordMinLen){
		  if(facets == null)
			  return new HashMap<String, Long>();
		  Map<String,Long> map = new LinkedHashMap<String,Long>();
		  for (FacetField facet : facets) {
				List<Count> facetCounts = facet.getValues();
				 for (FacetField.Count count : facetCounts) {
					 if(count.getName().length() >= wordMinLen);
					 map.put(count.getName(), count.getCount());
				 }
		  }
		  return map;
	  }

}