package com.pai.service.redis.broke;

import java.util.Random;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisLock {  
	
	public static final String LOCKED = "TRUE";  	
	public static final long MILLI_NANO_CONVERSION = 1000 * 1000L;  	 
	public static final long DEFAULT_TIME_OUT = 1000;  
	public static final Random RANDOM = new Random();  
	 
	public static final int EXPIRE = 3 * 60;  
	
	private ShardedJedisPool shardedJedisPool;  
	private ShardedJedis jedis;  
	private String key;  
	
	// 锁状态标志  
	private boolean locked = false;  
	
	public RedisLock(String key, ShardedJedisPool shardedJedisPool) {  
	    this.key = key + "_lock";  
	    this.shardedJedisPool = shardedJedisPool;  
	    this.jedis = this.shardedJedisPool.getResource();  
	}  
	
	public boolean lock(long timeout) {
	    long nano = System.nanoTime();  
	    timeout *= MILLI_NANO_CONVERSION;  
	    try {  
	        while ((System.nanoTime() - nano) < timeout) {  
	            if (this.jedis.setnx(this.key, LOCKED) == 1) {  
	                this.jedis.expire(this.key, EXPIRE);  
	                this.locked = true;  
	                return this.locked;  
	            }
	            // 短暂休眠，避免出现活锁  
	            Thread.sleep(3, RANDOM.nextInt(500));  
	        }  
	    } catch (Exception e) {  
	        throw new RuntimeException("Locking error", e);  
	    }  
	    return false;  
	}  
	
	 
	public boolean lock(long timeout, int expire) {  
	    long nano = System.nanoTime();  
	    timeout *= MILLI_NANO_CONVERSION;  
	    try {  
	        while ((System.nanoTime() - nano) < timeout) {  
	            if (this.jedis.setnx(this.key, LOCKED) == 1) {  
	                this.jedis.expire(this.key, expire);  
	                this.locked = true;  
	                return this.locked;  
	            }  
	            // 短暂休眠，避免出现活锁  
	            Thread.sleep(3, RANDOM.nextInt(500));  
	        }  
	    } catch (Exception e) {  
	        throw new RuntimeException("Locking error", e);  
	    }  
	    return false;  
	}  		
	public boolean lock() {  
	    return lock(DEFAULT_TIME_OUT);  
	}
	
	public void unlock() {  
	    try {  
	        if (this.locked) {  
	            this.jedis.del(this.key);  
	        }  
	    } finally {  
	        this.shardedJedisPool.returnResource(this.jedis);  
	    }  
	}  
}  

