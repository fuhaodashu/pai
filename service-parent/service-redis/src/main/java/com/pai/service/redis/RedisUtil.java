package com.pai.service.redis;

import java.util.Set;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

import com.pai.base.core.util.SerializeUtil;
import com.pai.service.redis.broke.RedisLock;
import com.pai.service.redis.broke.RedisManager;
import com.pai.service.redis.util.CommExecutor;

/**
  *   Redis最新调用类
  *   @author Suoron
  *   2015-08-31
  *   
  */

public class RedisUtil {	 
	private static Logger log = Logger.getLogger(RedisUtil.class);
    private static RedisManager manager = null;    
    
	public static class Singleton{
		private static RedisUtil instance = new RedisUtil(200);			
		
		public static RedisUtil getInstance(){			
			return Singleton.instance;
		}		
	}
		
	private RedisUtil(int level){  
		manager = RedisManager.Singleton.getInstance();
	}  
	
    public long incrId(final String key,final int minValue) {  
        return new CommExecutor<Long>(manager.getShardedJedisPool()) {   
            @Override
            public Long execute() {
            	RedisLock lock = new RedisLock(key, manager.getShardedJedisPool());
            	long id = 0;
            	if(lock.lock())
            	{
            		try {
		            	 id = shardedJedis.incr(key); 
		                 if ((id+99) >= Long.MAX_VALUE  || id < minValue) {  
		                    	shardedJedis.getSet(key, ""+minValue);               
		                     	id = minValue;
		                 }
                    }finally {  
                        lock.unlock();  
                    }                    
            	}            	                 
                return id;  
            }  
        }.getResult();  
    }  	
	public String flushDB(final int db) {
        return new CommExecutor<String>(manager.getShardedJedisPool()) {        	
            @Override  
            public String execute() {
            	String stat = "";
            	for (Jedis jedis : shardedJedis.getAllShards()) {
            		if(db > 0)
            		      jedis.select(db);            		
            		stat += "," + jedis.flushAll();
            	}
            	return stat;
            }  
        }.getResult();  		
	}	
	public String flushAll() {
        return new CommExecutor<String>(manager.getShardedJedisPool()) {        	
            @Override  
            public String execute() {
            	String stat = "";
            	for (Jedis jedis : shardedJedis.getAllShards()) {
            		stat += "," + jedis.flushAll();
            	}
            	return stat;
            }  
        }.getResult();  		
	}	
    public long delKeysLike(final String likeKey,final int db) {  
        return new CommExecutor<Long>(manager.getShardedJedisPool()) {        	
            @Override  
            public Long execute() {  
            	long count = 0;            	
            	for (Jedis jedis : shardedJedis.getAllShards()) {
            		if(db >0)
            			jedis.select(db);
                    Set<String> keys = jedis.keys(likeKey + "*");  
                    count += jedis.del(keys.toArray(new String[keys.size()]));                
            	}            	
                return count;  
            }  
        }.getResult();  
    }  
    public long delByKey(final String key,final int db) {  
        return new CommExecutor<Long>(manager.getShardedJedisPool()) {        	
            @Override  
           public Long execute() {
                long count = 0;  
                for (Jedis jedis : shardedJedis.getAllShards()) {
                    if(db >0)
                    	jedis.select(db);
                    count += jedis.del(key);
                }
                return count;  
            }
        }.getResult();  
    }  	
    public String setString(final String key, final String value, final int expire,final int db) {  
        return new CommExecutor<String>(manager.getShardedJedisPool()) {    
            @Override  
            public  String execute() {                 
                Jedis j = shardedJedis.getShard(key);
                if(db > 0){
              	  j.select(db);
                }              
                return  j.setex(key, expire, value);                 
            }  
        }.getResult();
    }
    public String getString(final String key, final int db) {  
        return new CommExecutor<String>(manager.getShardedJedisPool()) {
        	@Override  
        	public String execute() {            	
              Jedis j = shardedJedis.getShard(key.getBytes());
              if(db > 0){
            	  j.select(db);
              }              
              return j.get(key);                   
            }  
        }.getResult();       
    }    
    public Set<String> findByPrefix(final String keyPrefix, final int db) {  
        return new CommExecutor<Set<String>>(manager.getShardedJedisPool()) {
        	@Override  
        	public Set<String> execute() {            	
              Jedis j = shardedJedis.getShard(keyPrefix.getBytes());
              if(db > 0){
            	  j.select(db);
              }              
              return j.keys(keyPrefix);
            }  
        }.getResult();       
    } 	    
    public String setObject(final String key, final Object obj,final int db) {  
        return new CommExecutor<String>(manager.getShardedJedisPool()) {
        	@Override  
        	public String execute() {            	
              Jedis j = shardedJedis.getShard(key.getBytes());
              if(db > 0){
            	  j.select(db);
              }              
              return j.set(key.getBytes(), SerializeUtil.encodeObject(obj)); 
            }  
        }.getResult();  
    } 
    public Object getObject(final String key, final int db) {  
        return new CommExecutor<Object>(manager.getShardedJedisPool()) {
        	@Override  
        	public Object execute() {            	
              Jedis j = shardedJedis.getShard(key.getBytes());
              if(db > 0){
            	  j.select(db);
              }              
              byte[] value = j.get(key.getBytes());
      		  if (value != null && value.length > 0)
    			   return SerializeUtil.decodeObject(value);              
              return null; 
            }  
        }.getResult();       
    } 
    public String setString(final String key, final String value,final int db) {  
        return new CommExecutor<String>(manager.getShardedJedisPool()) {
        	@Override  
        	public String execute() {            	
              Jedis j = shardedJedis.getShard(key);
              if(db > 0){
            	  j.select(db);
              }              
              return j.set(key, value); 
            }  
        }.getResult();  
    }     
    public Long expire(final String key, final int expire_second, final int db) {  
        return new CommExecutor<Long>(manager.getShardedJedisPool()) {  
        	@Override  
        	public Long execute() {
        		long count = 0;
        		for (Jedis jedis : shardedJedis.getAllShards()){
        		      if(db > 0)
        		    	  jedis.select(db);
        		      jedis.expire(key, expire_second);
        		}
                return count;
            }  
        }.getResult();  
    }	
    public Long delKey(final String key) {  
        return new CommExecutor<Long>(manager.getShardedJedisPool()) {          	
            @Override  
            public Long execute() {  
                return shardedJedis.del(key);  
            }  
        }.getResult();  
    }
}  