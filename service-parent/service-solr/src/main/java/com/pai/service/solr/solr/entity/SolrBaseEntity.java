package com.pai.service.solr.solr.entity;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

public class SolrBaseEntity implements Serializable{
	
	private static final long serialVersionUID = -7581235090503479908L;

	/** id: 主键 */
	@Field
	public String id;

	/** createTime: 创建时间 */
	@Field
	private Long createTime;
	
	/** id: 主键 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	/** createTime: 创建时间 */
	public Long getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

}
