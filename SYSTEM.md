#实例的系统结构使用spring,springmvc,mybatis等搭建，maven结构，参考了领域驱动设计，对于小项目还是很方便。以后业务扩展，只需对biz-xx进行服务化改造（接口+服务实现）即可，非常方便。

#项目模块分层图

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/module.png)

#系统结构设计图

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/code.png)

#base-paren基础接口、工具和数据存储

-base-api:
 提供最为基础的常量和接口。还有一个关键类OnlineHolder中通过ThreadLocal<HttpSession>存储session，方便其他上层模块快速获取session，通过filter维护OnlineHolder类以便系统每一个线程都能拿到session中信息。

-base-core:
 依赖base-api，针对部分接口提供实现，并提供大量的工具类和基础服务类

-base-db:使用MyBatis技术对所有关系数据库提供访问支持，使用方言处理各种数据库的差异。支持数据库连接池和动态数据源切换。主键生成策略：为保持唯一性，暂时id单数从redis中取，redis无法取到从数据库id表中取双数。防止并发重复同时方便扩展分布式结构，加锁如下

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

#service-parent：service服务接口模块，按需依赖

-service-mq、service-redis等service-xx模块

#biz-parent具体的业务模块

-biz-frame:提供最基础的接口定义和一些常量。

-biz-xx:用户权限相关模块

#app-parent应用层

-app-web:提供基础的web层公共接口、抽象类和相关服务、支持（比如filter、context、Interceptor等）

-app-admin:后台

-app-api:api

-app-front:前端
