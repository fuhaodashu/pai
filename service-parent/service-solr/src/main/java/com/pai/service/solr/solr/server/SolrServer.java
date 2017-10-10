package com.pai.service.solr.solr.server;

import org.apache.solr.client.solrj.impl.BinaryRequestWriter;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <pre> 
 * 描述：Solr服务连接类
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 * </pre>
 */
public class SolrServer {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(SolrServer.class);
	private Integer soTimeOut = 60000;
    private Integer connectionTimeOut = 60000;
    private Integer maxConnectionsPerHost = 1000;
    private Integer maxTotalConnections = 1000;
    private Integer maxRetries = 1;
    

	public HttpSolrServer getSolrServer(String endPoint) {
		try {
			HttpSolrServer solrServer = new HttpSolrServer(endPoint);
    	    solrServer.setRequestWriter(new BinaryRequestWriter());
            solrServer.setSoTimeout(soTimeOut);
            solrServer.setConnectionTimeout(connectionTimeOut);
            solrServer.setDefaultMaxConnectionsPerHost(maxConnectionsPerHost);
            solrServer.setMaxTotalConnections(maxTotalConnections);
            solrServer.setFollowRedirects(false);
            solrServer.setAllowCompression(true);
            solrServer.setMaxRetries(maxRetries);
            solrServer.setParser(new XMLResponseParser());
			return solrServer;
		} catch (Exception ex) {
			LOGGER.error("连接"+endPoint+"失败", ex);
		}
		return null;
	}
	
	
	public Integer getSoTimeOut() {
		return soTimeOut;
	}

	public void setSoTimeOut(Integer soTimeOut) {
		this.soTimeOut = soTimeOut;
	}

	public Integer getConnectionTimeOut() {
		return connectionTimeOut;
	}

	public void setConnectionTimeOut(Integer connectionTimeOut) {
		this.connectionTimeOut = connectionTimeOut;
	}

	public Integer getMaxConnectionsPerHost() {
		return maxConnectionsPerHost;
	}

	public void setMaxConnectionsPerHost(Integer maxConnectionsPerHost) {
		this.maxConnectionsPerHost = maxConnectionsPerHost;
	}

	public Integer getMaxTotalConnections() {
		return maxTotalConnections;
	}

	public void setMaxTotalConnections(Integer maxTotalConnections) {
		this.maxTotalConnections = maxTotalConnections;
	}

	public Integer getMaxRetries() {
		return maxRetries;
	}

	public void setMaxRetries(Integer maxRetries) {
		this.maxRetries = maxRetries;
	}

	public void closeServer(HttpSolrServer solrServer) {
		try {
			if (null != solrServer) {
				solrServer.shutdown();
			}
		} catch (Exception ex) {
			LOGGER.error("关闭连接失败", ex);
		}
	}
	
}
