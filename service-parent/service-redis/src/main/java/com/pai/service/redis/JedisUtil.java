package com.pai.service.redis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisException;

import com.pai.base.core.util.SerializeUtil;

public class JedisUtil {
//	private static JedisUtil redis = null;
	private Logger log = Logger.getLogger(JedisUtil.class);

	private static ApplicationContext app;
	private static JedisPool jedisPool = null;
	
	private static ShardedJedisPool shardedJedisPool = null;

	/**
	 * 缓存生存时间
	 */
	private final int expire = 60000;
	private final long TWO_SECONDS = 2000000000L;
	
	static {
		init();
	}
	
	private JedisUtil(){}
	
	private static class LazyHolder {
		private static final JedisUtil jedisUtil = new JedisUtil();  
	}
	
	public static final JedisUtil getInstance() {
		return LazyHolder.jedisUtil;
	}
	
	/**
	 * 构建redis连接池
	 * 
	 * @param ip
	 * @param port
	 * @return JedisPool
	 */
	public static void init() {
		app = new ClassPathXmlApplicationContext(
				"classpath:/conf/service-redis.xml");
		//集群配置，日后改造
		shardedJedisPool = (ShardedJedisPool) app.getBean("shardedJedisPool");
		jedisPool = (JedisPool) app.getBean("jedisPool");
	}

	public JedisPool getPool() {
		return jedisPool;
	}

	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	/**
	 * 从jedis连接池中获取获取jedis对象
	 * 
	 * @return Jedis
	 */
	public Jedis getJedis() {
		return jedisPool.getResource();
	}

	/**
	 * 回收jedis
	 * 
	 * @param jedis
	 */
	public void returnJedis(Jedis jedis) {
		jedisPool.returnResource(jedis);
	}

	/**
	 * 设置过期时间
	 * 
	 * @author ruan 2013-4-11
	 * @param key
	 * @param seconds
	 */
	public void expire(String key, int seconds) {
		if (seconds <= 0) {
			return;
		}
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.expire(key, seconds);
		} catch (JedisException  e) {
			log.error(e.getMessage());
			if(jedis != null){
				jedisPool.returnBrokenResource(jedis);
			}
		} catch (Exception  e) {
			log.error(e.getMessage());
		} finally {
			if(jedis != null){
				jedisPool.returnResource(jedis);
			}
		}
	}
	
	/**
	 * 添加到List<String>中
	 * @param key List的名称
	 * @param valueElemClazz List中元素的类型
	 * @param value
	 */
	public void addToList(String key, Object value, int db) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(db);
			
			//value为字符串，直接存字符串
			if(value.getClass().equals(String.class)){
				jedis.lpush(key, (String)value);
			}
			
			jedis.lpush(key.getBytes(), SerializeUtil.encodeObject(value));
		} catch (JedisException  e) {
			log.error(e.getMessage());
			if(jedis != null){
				jedisPool.returnBrokenResource(jedis);
			}
		} catch (Exception  e) {
			log.error(e.getMessage());
		} finally {
			if(jedis != null){
				jedisPool.returnResource(jedis);
			}
		}
	}
	
	/**
	 * 添加到List<String>中
	 * @param key List的名称
	 * @param value
	 */
	public List<? extends Object> getFromList(String key, Class valueElemClazz, int db) {
		Jedis jedis = null;
		List<Object> rt = new ArrayList<Object>();
		try {
			jedis = getJedis();
			jedis.select(db);
			
			if(valueElemClazz.equals(String.class)){
				Long count = jedis.llen(key);
				return jedis.lrange(key, 0, count.intValue() - 1);
			}
			
			Long count = jedis.llen(key.getBytes());
			List<byte[]> list = jedis.lrange(key.getBytes(), 0, count.intValue() - 1);
			for(byte[] el : list){
				rt.add( SerializeUtil.decodeObject(el) );
			}
			
		} catch (JedisException  e) {
			log.error(e.getMessage());
			if(jedis != null){
				jedisPool.returnBrokenResource(jedis);
			}
		} catch (Exception  e) {
			log.error(e.getMessage());
		} finally {
			if(jedis != null){
				jedisPool.returnResource(jedis);
			}
		}
		
		return rt;
	}

	/**
	 * 设置默认过期时间
	 * 
	 * @author ruan 2013-4-11
	 * @param key
	 */
	public void expire(String key) {
		expire(key, expire);
	}
	/**
	 * 获取hash中的field
	 * 
	 * @param key
	 * @param field
	 * @param databaseIndex
	 * @param expire    --过期秒数
	 */	
    public Long setHashFieldValue(String key, String field,int db,String value) {
    	if (value == null || value.equals("")) {
			log.info("key: " + key + " 对应的value为空");
			return 0L;
		}		
		Jedis jedis = null;
		Long l = 0L;
		
		try {
			jedis = getJedis();
			jedis.select(db);
			
			int n = field.indexOf("_");
			if(n < 0)
				 n = 0;				
			
            String script = "local res=redis.call('HKEYS','"+key+"') local field='"+field.substring(n)+"'"
                    + " for i,v in ipairs(res) do local n = string.sub(v,13) if(n == field) then redis.call('HDEL','"+key+"',v) end end" 
                    + " return redis.call('HSET','"+key+"','"+field+"','"+value+"') ";
            
            //l = jedis.hset(key, field, value);						   
            l = Long.parseLong(jedis.eval(script).toString());
		} catch (JedisException  e) {
			log.error(e.getMessage());
			if(jedis != null){
				jedisPool.returnBrokenResource(jedis);
			}
		} catch (Exception  e) {
			log.error(e.getMessage());
		} finally {
			if(jedis != null){
				jedisPool.returnResource(jedis);
			}
		}  
		return l;
    }
	/**
	 * 获取hash中的field
	 * 
	 * @param key
	 * @param field
	 * @param databaseIndex
	 * @param expire    --过期秒数
	 */
	public Object getHashField(String key, String field,int db,int expire) {		
		Jedis jedis = null;
		Object obj =null;
		try {
			jedis = getJedis();
			jedis.select(db);
                              
            Date dt = new Date();
            dt.setTime(dt.getTime() - (expire*1000));
                    
            String script = "local res=redis.call('HKEYS','"+key+"') local field='_"+field+"'"
                    + " for i,v in ipairs(res) do local n = string.sub(v,0,12)  if(tonumber(n)< tonumber('"+new SimpleDateFormat("yyyyMMddHHmm").format(dt)+"')) then redis.call('HDEL','"+key+"',v) end "
                    + " n=string.sub(v,13) if (n==field) then field= v end end" 
                    + " return redis.call('HGET','"+key+"',field)";
            
      		obj = jedis.eval(script);

		} catch (JedisException  e) {
			log.error(e.getMessage());
		} catch (Exception  e) {
			log.error(e.getMessage());
		} finally {
			if(jedis != null){
				jedisPool.returnResource(jedis);
			}					
		}		
		return obj;
	}	
	/**
	 * 自增JSON缓存属性
	 * @param key
	 * @param property
	 * @param databaseIndex
	 */
	public long incrJSON(String key, String property,int db,int value) {		
		Jedis jedis = null;
		long redisValue = 0;
		try {
			jedis = getJedis();
			jedis.select(db);
			
		    String script = "local msg=redis.call('get','"+key+"');local result=0;"
					   + "local msg=string.gsub(msg,'\"(%a+)\":(%d+)\',function(k,v) if(string.find(k, '"+property+"')) then result=v+"+value+"; if(result < 0) then result=0; end return '\"'..k..'\":'..result; end end);"
					   + "redis.call('set','"+key+"',msg);"	
			           + "return result;";
      		Object obj = jedis.eval(script);
      		if(obj != null){
      			redisValue = Long.parseLong(obj.toString());     			
      		} 
		} catch (JedisException  e) {
			log.error(e.getMessage());
		} catch (Exception  e) {
			log.error(e.getMessage());
		} finally {
			if(jedis != null){
				jedisPool.returnResource(jedis);
			}					
		}
		
		return redisValue;
	}	
	
	public String incr(String key, int db){
		return incrBy(key, 1, db);
	}
	
	public String incrBy(String key, int num, int db) {
		Jedis jedis = getJedis();
		try {
			jedis.select(db);
			if(lock(jedis, key)){
				try {
					jedis.incrBy(key, num);
					return get(key, db);
				} finally {
					unlock(jedis, key);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			jedisPool.returnResource(jedis);
		}
		return null;
	}
	
	public boolean lock(Jedis jedis, String key){
		try {
			key += "_lock";
			long nano = System.nanoTime();
			//允许最多2秒的等待时间进行incr操作
			while ((System.nanoTime() - nano) < TWO_SECONDS){
				if(jedis.setnx(key, "TRUE") == 1){
					jedis.expire(key, 180);
					return true;
				}
				Thread.sleep(1, new Random().nextInt(500));  
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return false;
	}
	
	public void unlock(Jedis jedis, String key) {
		key += "_lock";
        jedis.del(key);  
	}
	
	/**
	 * 设置缓存
	 * 
	 * @param key
	 * @param value
	 * @param databaseIndex
	 */
	public void set(String key, String value, int db) {
		if (value == null || value.equals("")) {
			log.info("key: " + key + " 对应的value为空");
			return;
		}
		
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(db);
			jedis.set(key, value);
		} catch (JedisException  e) {
			log.error(e.getMessage());
			if(jedis != null){
				jedisPool.returnBrokenResource(jedis);
			}
		} catch (Exception  e) {
			log.error(e.getMessage());
		} finally {
			if(jedis != null){
				jedisPool.returnResource(jedis);
			}
		}
	}
	/**
	 * 设置缓存
	 * 
	 * @param key
	 * @param seconds 过期时间
	 * @param value
	 * @param databaseIndex
	 */
	public void set(String key, int seconds, String value, int db, boolean override) {
		if (value == null || value.equals("")) {
			log.info("key: " + key + " 对应的value为空");
			return;
		}
		
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(db);
			if(override)
			   jedis.setex(key, seconds, value);
			else{
			   jedis.setnx(key, value);
			   jedis.expire(key, seconds);
			}
		} catch (JedisException  e) {
			log.error(e.getMessage());
			if(jedis != null){
				jedisPool.returnBrokenResource(jedis);
			}
		} catch (Exception  e) {
			log.error(e.getMessage());
		} finally {
			if(jedis != null){
				jedisPool.returnResource(jedis);
			}
		}
	}

	public void set(String key, int seconds, Object obj, int db, boolean override) {
		if (obj == null) {
			log.info("key: " + key + " 对应的value为空");
			return;
		}
		
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(db);
		    if(override)
			   jedis.setex(key.getBytes(), seconds, SerializeUtil.encodeObject(obj));
		    else{
		    	jedis.setnx(key.getBytes(), SerializeUtil.encodeObject(obj));
		    	jedis.expire(key.getBytes(), seconds);
		    }
		} catch (JedisException  e) {
			log.error(e.getMessage());
			if(jedis != null){
				jedisPool.returnBrokenResource(jedis);
			}
		} catch (Exception  e) {
			log.error(e.getMessage());
		} finally {
			if(jedis != null){
				jedisPool.returnResource(jedis);
			}
		}
	}
	
	public void set(String key, Object obj, int db) {
		if (obj == null) {
			log.info("key: " + key + " 对应的value为空");
			return;
		}
		
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(db);
			jedis.set(key.getBytes(), SerializeUtil.encodeObject(obj));
		} catch (JedisException  e) {
			log.error(e.getMessage());
			if(jedis != null){
				jedisPool.returnBrokenResource(jedis);
			}
		} catch (Exception  e) {
			log.error(e.getMessage());
		} finally {
			if(jedis != null){
				jedisPool.returnResource(jedis);
			}
		}
	}

	/**
	 * 获取缓存
	 * 
	 * @param key
	 * @param value
	 * @param databaseIndex
	 */
	public String get(String key, int db) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(db);
			value = jedis.get(key);
		} catch (JedisException  e) {
			log.error(e.getMessage());
			if(jedis != null){
				jedisPool.returnBrokenResource(jedis);
			}
		} catch (Exception  e) {
			log.error(e.getMessage());
		} finally {
			if(jedis != null){
				jedisPool.returnResource(jedis);
			}
		}
		
		return value;

	}

	/**
	 * 获取缓存
	 * 
	 * @param key
	 * @param value
	 * @param databaseIndex
	 */
	public <T> T getObject(String key, int db) {
		
		byte[] value = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(db);
			value = jedis.get(key.getBytes());
		} catch (JedisException  e) {
			log.error(e.getMessage());
			if(jedis != null){
				jedisPool.returnBrokenResource(jedis);
			}
		} catch (Exception  e) {
			log.error(e.getMessage());
		} finally {
			if(jedis != null){
				jedisPool.returnResource(jedis);
			}
		}
		
		if (value != null && value.length > 0)
			return SerializeUtil.decodeObject(value);
		return null;
	}
	
	/**
	 * 根据键的前缀模糊匹配查询
	 * @param keyPrefix
	 * @param db
	 * @return Set
	 */
	public Set<String> findByPrefix(String keyPrefix, int db){
		Set<String> set = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(db);
			set = jedis.keys(keyPrefix);
		} catch (JedisException  e) {
			log.error(e.getMessage());
			if(jedis != null){
				jedisPool.returnBrokenResource(jedis);
			}
		} catch (Exception  e) {
			log.error(e.getMessage());
		} finally {
			if(jedis != null){
				jedisPool.returnResource(jedis);
			}
		}
		
		return set;
	}	

	/**
	 * 根据key删除缓存
	 * 
	 * @param key
	 * @param value
	 * @param databaseIndex
	 */
	public void delByKey(String key, int db) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(db);
			jedis.del(key);			
		} catch (JedisException  e) {
			log.error(e.getMessage());
			if(jedis != null){
				jedisPool.returnBrokenResource(jedis);
			}
		} catch (Exception  e) {
			log.error(e.getMessage());
		} finally {
			if(jedis != null){
				jedisPool.returnResource(jedis);
			}
		}

	}

	/**
	 * 根据前缀删除缓存
	 * 
	 * @param key
	 * @param value
	 * @param databaseIndex
	 */
	public void delByPrefix(String likeKey, int db) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(db);
			Set<String> keys = jedis.keys(likeKey + "*");
			if (keys.size() > 0) {
				String[] delKeys = new String[keys.size()];
				int i = 0;
				for (String delKey : keys) {
					delKeys[i] = delKey;
					i++;
				}
				jedis.del(delKeys);
			}			
		} catch (JedisException  e) {
			log.error(e.getMessage());
			if(jedis != null){
				jedisPool.returnBrokenResource(jedis);
			}
		} catch (Exception  e) {
			log.error(e.getMessage());
		} finally {
			if(jedis != null){
				jedisPool.returnResource(jedis);
			}
		}
		
	}

	/**
	 * 清空所有数据库
	 */
	public String flushAll() {
		String state = null;
		Jedis jedis = null;
		
		try {
			jedis = getJedis();
			state = jedis.flushAll();
		} catch (JedisException  e) {
			log.error(e.getMessage());
			if(jedis != null){
				jedisPool.returnBrokenResource(jedis);
			}
		} catch (Exception  e) {
			log.error(e.getMessage());
		} finally {
			if(jedis != null){
				jedisPool.returnResource(jedis);
			}
		}
		
		
		if ("OK".equals(state)) {
			log.warn("清空redis所有缓存成功!");
		} else {
			log.warn("清空redis所有缓存失败!");
		}
		return state;
	}

	/**
	 * 清空单个数据库
	 */
	public String flushDB(int db) {
		
		String state = null;
		Jedis jedis = null;
		
		try {
			jedis = getJedis();
			jedis.select(db);
			state = jedis.flushDB();
		} catch (JedisException  e) {
			log.error(e.getMessage());
			if(jedis != null){
				jedisPool.returnBrokenResource(jedis);
			}
		} catch (Exception  e) {
			log.error(e.getMessage());
		} finally {
			if(jedis != null){
				jedisPool.returnResource(jedis);
			}
		}
		
		
		if ("OK".equals(state)) {
			log.warn("清空redis所有缓存成功!");
		} else {
			log.warn("清空redis所有缓存失败!");
		}
		return state;
		
	}
	/**
	 * 
	 * TODO 获取Value Map 的值
	 * @param userId
	 * @exception 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	private Object getObject(String key, String mapKey,int db){		 
		 byte[] val = null;
		 Jedis jedis = getJedis();
		 try {		   
			 
		   if(db > 0)
		       jedis.select(db);
		   
		   val = jedis.hget(key.getBytes(), mapKey.getBytes());
		 } catch (JedisException e) {
		   log.error(e.getMessage());
		   if (jedis != null) {
		     jedisPool.returnBrokenResource(jedis);
		   }
		 } catch (Exception e) {
		   log.error(e.getMessage());
		 } finally {
		   if (jedis != null) {
		     jedisPool.returnResource(jedis);
		   }
		 }
		 if (val != null && val.length > 0)
			 	return SerializeUtil.decodeObject(val);
		 return null ;
	}
	
	/**
	 * 
	 * TODO 更新或新增缓存信息
	 * @param <T>
	 * @param userId
	 * @exception 
	 * @since  1.0.0
	 * @return String
	 */
	public String setObject(String key,Map<byte[], byte[]> map,int db){
		Jedis jedis = getJedis();
		 try {
		  if(db > 0)
		       jedis.select(db);
		  
		   //如果存在则插入覆盖，如果不存在则初始化
		   return jedis.hmset(key.getBytes(), map);		   
		 } catch (JedisException e) {
		   log.error(e.getMessage());
		   if (jedis != null) {
		     jedisPool.returnBrokenResource(jedis);
		   }
		 } catch (Exception e) {
		   log.error(e.getMessage());
		 } finally {
		   if (jedis != null) {
		     jedisPool.returnResource(jedis);
		   }
		 }
		 return null;
	}
	/**
	 * Set集合 add操作
	 * @param key
	 * @param member
	 * @param db
	 * @return Long
	 */
	public Long sadd(String key, String member, int db){
		Jedis jedis = getJedis();
		 try {
		  if(db > 0)
		       jedis.select(db);
		  
		   return jedis.sadd(key, member);
		 } catch (JedisException e) {
		   log.error(e.getMessage());
		   if (jedis != null) {
		     jedisPool.returnBrokenResource(jedis);
		   }
		 } catch (Exception e) {
		   log.error(e.getMessage());
		 } finally {
		   if (jedis != null) {
		     jedisPool.returnResource(jedis);
		   }
		 }
		 return 0l;
	}
	/**
	 * Set集合 删除操作
	 * @param key
	 * @param db
	 * @param member
	 * @return Long
	 */
	public Long srem(String key, int db,String... member){
		Jedis jedis = getJedis();
		 try {
		  if(db > 0)
		       jedis.select(db);
		  
		   return jedis.srem(key, member);
		 } catch (JedisException e) {
		   log.error(e.getMessage());
		   if (jedis != null) {
		     jedisPool.returnBrokenResource(jedis);
		   }
		 } catch (Exception e) {
		   log.error(e.getMessage());
		 } finally {
		   if (jedis != null) {
		     jedisPool.returnResource(jedis);
		   }
		 }
		 return 0l;
	}
	/**
	 * Set集合 add操作（字节）
	 * @param key
	 * @param member
	 * @param db
	 * @return Long
	 */
	public Long saddByte(String key, String member, int db){
		Jedis jedis = getJedis();
		 try {
		  if(db > 0)
		       jedis.select(db);
		   //如果存在则插入覆盖，如果不存在则初始化
		   return jedis.sadd(key.getBytes(), member.getBytes());
		 } catch (JedisException e) {
		   log.error(e.getMessage());
		   if (jedis != null) {
		     jedisPool.returnBrokenResource(jedis);
		   }
		 } catch (Exception e) {
		   log.error(e.getMessage());
		 } finally {
		   if (jedis != null) {
		     jedisPool.returnResource(jedis);
		   }
		 }
		 return 0l;
	}
	/**
	 * Lish 右PUSH操作，添加元素到末尾
	 * @param key
	 * @param member
	 * @param db
	 * @return Long
	 */
	public Long RPUSH(String key, String member, int db){
		Jedis jedis = getJedis();
		 try {
		  if(db > 0)
		       jedis.select(db);
		   //如果存在则插入覆盖，如果不存在则初始化
		   return jedis.rpush(key, member);
		 } catch (JedisException e) {
		   log.error(e.getMessage());
		   if (jedis != null) {
		     jedisPool.returnBrokenResource(jedis);
		   }
		 } catch (Exception e) {
		   log.error(e.getMessage());
		 } finally {
		   if (jedis != null) {
		     jedisPool.returnResource(jedis);
		   }
		 }
		 return 0l;
	}
	/**
	 * 获取List 某个区间(注意此方法为闭区间)
	 * @param key
	 * @param startIndex	0代表第一个元素
	 * @param endIndex		-1代表最后一个元素
	 * @param db
	 * @return List
	 */
	public List<String> LRANGE(String key,long startIndex, long endIndex, int db){
		Jedis jedis = getJedis();
		 try {
		  if(db > 0)
		       jedis.select(db);
		   //如果存在则插入覆盖，如果不存在则初始化
		   return jedis.lrange(key,startIndex, endIndex);
		 } catch (JedisException e) {
		   log.error(e.getMessage());
		   if (jedis != null) {
		     jedisPool.returnBrokenResource(jedis);
		   }
		 } catch (Exception e) {
		   log.error(e.getMessage());
		 } finally {
		   if (jedis != null) {
		     jedisPool.returnResource(jedis);
		   }
		 }
		 return new ArrayList<String>();
	}
	/**
	 * List移除数据
	 * @param key
	 * @param count <br>
		    count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。 <br>
		    count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。 <br>
		    count = 0 : 移除表中所有与 value 相等的值。 <br>
	 * @param value
	 * @param db
	 * @return long
	 */
	public long LREM(String key,long count , String value, int db){
		Jedis jedis = getJedis();
		 try {
		  if(db > 0)
		       jedis.select(db);
		   //如果存在则插入覆盖，如果不存在则初始化
		   return jedis.lrem(key,count, value);
		 } catch (JedisException e) {
		   log.error(e.getMessage());
		   if (jedis != null) {
		     jedisPool.returnBrokenResource(jedis);
		   }
		 } catch (Exception e) {
		   log.error(e.getMessage());
		 } finally {
		   if (jedis != null) {
		     jedisPool.returnResource(jedis);
		   }
		 }
		 return 0l;
	}

}
