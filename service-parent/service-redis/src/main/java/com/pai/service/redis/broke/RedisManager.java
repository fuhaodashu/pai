package com.pai.service.redis.broke;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedisPool;

import com.pai.base.core.helper.SpringHelper;

public class RedisManager {
	private static ShardedJedisPool shardedJedisPool = null;
	private static ApplicationContext app;
	private static JedisPool jedisPool = null;	

	private void Initialize() {
		if(SpringHelper.getBean("shardedJedisPool") !=null) {
			
			 shardedJedisPool = (ShardedJedisPool) SpringHelper.getBean("shardedJedisPool");
			 jedisPool = (JedisPool) SpringHelper.getBean("jedisPool");
			 
		}else{   /* 供本地测试环境使用 */
			
		    app = new ClassPathXmlApplicationContext("classpath:/conf/spring-beans.xml");
		    shardedJedisPool = (ShardedJedisPool) app.getBean("shardedJedisPool");
		    jedisPool = (JedisPool) app.getBean("jedisPool");
		}	
	}
	
	/**
	 *   防止重复实例化
	 */
	private RedisManager(int level){
		Initialize();
	}
	public static class Singleton{
		private static RedisManager manager = new RedisManager(200);
		
		public static RedisManager getInstance(){
			return Singleton.manager;
		}
	}
    public int initializePool(){
    	Initialize();
    	return 0;
    }    
    
    public int destoryPool(){
    	try{
    	    shardedJedisPool.destroy();
    	    jedisPool.destroy();
    	}catch (Exception e) {
			e.printStackTrace();
		}    	
    	return 0;
    }
    
	public static ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}
	public static void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		RedisManager.shardedJedisPool = shardedJedisPool;
	}
	public static ApplicationContext getApp() {
		return app;
	}
	public static void setApp(ApplicationContext app) {
		RedisManager.app = app;
	}
	public static JedisPool getJedisPool() {
		return jedisPool;
	}
	public static void setJedisPool(JedisPool jedisPool) {
		RedisManager.jedisPool = jedisPool;
	}
	
}
