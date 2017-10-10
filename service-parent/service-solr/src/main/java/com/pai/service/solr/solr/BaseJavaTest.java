package com.pai.service.solr.solr;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pai.service.solr.solr.api.SolrAPI;
import com.pai.service.solr.solr.api.impl.SolrAPIImpl;
import com.pai.service.solr.solr.server.SolrServer;

public class BaseJavaTest {

	protected static Logger logger = LoggerFactory.getLogger(BaseJavaTest.class);
	
	String posts_url = "http://10.105.7.111/solr/bbs_posts";
	String topic_url = "http://10.105.7.111/solr/bbs_topics";
	
	private SolrAPI solrAPI;
	
	@Before
	public void init() {
		if(solrAPI == null)
			solrAPI = new SolrAPIImpl(new SolrServer());
	}
	
	@Test
	public void ping() {	
		String result = solrAPI.ping(posts_url);
		System.out.println(result);
		
		String result3 = solrAPI.ping(posts_url);
		System.out.println(result3);
		
		String result4 = solrAPI.ping(posts_url);
		System.out.println(result4);
		
		String result2 = solrAPI.ping(topic_url);
		System.out.println(result2);
	}
	
	@Test
	public void optimize() {	
		solrAPI.optimize(posts_url);
		solrAPI.optimize(topic_url);
	}

}
