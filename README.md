#系统实例主要使用spring,springmvc,mybatis等搭建，maven结构，使用dubbo和zookeeper提供分布式服务。

[代码生成器](https://github.com/fuhaopai/pai/blob/master/CODEGEN.md)

[文档生成器](https://github.com/fuhaopai/pai/blob/master/DOCGEN.md)

[接口校验组件](https://github.com/fuhaopai/pai/blob/master/INTFCHECK.md)

[分布式事务解决方案-最终一致性](https://github.com/fuhaopai/pai/blob/master/TRANSACTION.md)

[分布式主键ID获取方案](https://github.com/fuhaopai/pai/blob/master/GETID.md)

[nginx集群和高可用配置](https://github.com/fuhaopai/pai/blob/master/nginx.md)

[session-redis共享方案](https://github.com/fuhaopai/pai/blob/master/session.md)

#项目模块分层图

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/module.png)

#系统结构设计图

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/code.png)

#base-paren：基础接口、工具和数据存储

-base-api:
 提供最为基础的常量,注解和接口。服务模块的API依赖它。

-base-core:
 依赖base-api，针对部分接口提供实现，并提供大量的工具类和基础服务类

-base-db:使用MyBatis技术对所有关系数据库提供访问支持，使用方言处理各种数据库的差异。支持数据库连接池和动态数据源切换。主键生成策略：为保持唯一性，暂时id单数从redis中取，redis无法取到从数据库id表中取双数。防止并发重复，方便改造分布式加锁如下

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

#service-parent：本地按需选择依赖

-service-mq:异步消息

-service-redis:缓存（因base-db使用，已移到base-core）

-service-quartz:定时器

-service-solr：搜索

-service-getui:消息推送

-service-image:图片上传

-service-mail:邮件

#biz-parent：具体的业务模块
-biz-frame:提供基础业务的接口定义和一些常量。

-biz-auth:用户权限相关模块

-biz-common:公共业务模块

-biz-message:分布式消息组件

-biz-member:会员服务模块

-biz-api:各服务API接口

-biz-A/B/C:服务实现

#app-parent应用层
-app-web:提供基础的web层公共接口、抽象类和相关服务、支持（比如filter、context、Interceptor等）

-app-admin:后台web项目

-app-api:（为web,wap,app提供RESTful规范的接口,替换为dubbo rest）
