package com.pai.service.redis.util;

import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.ShardedJedis;
//redis.clients.jedis.Jedis

public abstract class CommExecutor<T> {
	
    public ShardedJedis shardedJedis = null;  
    public ShardedJedisPool shardedJedisPool = null;  

    public CommExecutor(ShardedJedisPool shardedJedisPool) {  
        this.shardedJedisPool = shardedJedisPool;  
        shardedJedis = this.shardedJedisPool.getResource();  
    }      
    protected abstract T execute();  
    
    public T getResult() {  
        T result = null;  
        try {  
            result = execute();  
        } catch (Throwable e) {  
            throw new RuntimeException("Redis execute exception", e);  
        } finally {  
            if (shardedJedis != null) {
                shardedJedisPool.returnResource(shardedJedis);  
            }  
        }  
        return result;  
    }
}
