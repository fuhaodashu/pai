<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
# pai
#代码生成器详解
-源码位于pai-tools/pai-codegen中，操作代码生成器需要修改配置文件codegen.xml。

-代码生成器是基于数据表结构、代码模板和模板解析引擎生成标准代码文件。用于减少重复工作量

-代码模板一般使用FreeMarker或Velocity，因为这两者都是模板解析语言，不需要预编译。我采用FreeMarker技术。

-数据表设计有两种形式：1）单表：最为常见。2）主从表：因为从表可能没有独立的菜单，它的CRUD依赖主表。

1、配置文件解析层：

-代码生成配置放置到codegen.xml中。

-设计一个专门的ConfigModelHelper用于解析xml文件，构造为Java对象，方便开发处理。

2、数据表访问解析层：

-就是根据数据库配置，读取指定要生成代码的表的结构，包括表名、字段、类型和备注等等。

-如果是主从表，则同时要读取外键信息，构造主从关系。

-通过数据库工具类（基于接口实现，不同数据库类型不同的实现）

3、代码生成

-基于前面构造好的配置文件对象（ConfigModel）和数据表结构对象（TableModel）

-遍历所有待处理的代码模板，通过FreeMarker模板引擎进行代码文件生成

-根据配置文件的参数进行覆盖或跳过等。

-控制台打印日志，方便查看生成结果。

4、代码删除

-原理同上，只是做的操作是文件删除。

#效果展示，以下是我自己实验用的一个小例子，代码架构设计请点击 [系统搭建](https://github.com/fuhaodashu/pai/blob/master/SYSTEM.md)
-在codegen.xml中填入要生成的表信息，代码所属模块，db,dao,domain,controller,view,api 6个层次指定哪几个会生成相应的代码

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/3.png)

-源码中代码生成器的执行入口

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/8.png)

-生成结果

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/7.png)

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/1.png)

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/2.png)

-效果展示，给刚生成的功能配置路劲

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/5.png)

-查看效果

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/6.png)
>>>>>>> branch 'master' of https://github.com/fuhaodashu/pai.git
=======
# pai
#代码生成器详解
-源码位于pai-tools/pai-codegen中，操作代码生成器需要修改配置文件codegen.xml。

-代码生成器是基于数据表结构、代码模板和模板解析引擎生成标准代码文件。用于减少重复工作量

-代码模板一般使用FreeMarker或Velocity，因为这两者都是模板解析语言，不需要预编译。我采用FreeMarker技术。

-数据表设计有两种形式：1）单表：最为常见。2）主从表：因为从表可能没有独立的菜单，它的CRUD依赖主表。

1、配置文件解析层：

-代码生成配置放置到codegen.xml中。

-设计一个专门的ConfigModelHelper用于解析xml文件，构造为Java对象，方便开发处理。

2、数据表访问解析层：

-就是根据数据库配置，读取指定要生成代码的表的结构，包括表名、字段、类型和备注等等。

-如果是主从表，则同时要读取外键信息，构造主从关系。

-通过数据库工具类（基于接口实现，不同数据库类型不同的实现）

3、代码生成

-基于前面构造好的配置文件对象（ConfigModel）和数据表结构对象（TableModel）

-遍历所有待处理的代码模板，通过FreeMarker模板引擎进行代码文件生成

-根据配置文件的参数进行覆盖或跳过等。

-控制台打印日志，方便查看生成结果。

4、代码删除

-原理同上，只是做的操作是文件删除。

#效果展示，以下是我自己实验用的一个小例子，代码架构设计请点击 [系统搭建](https://github.com/fuhaodashu/pai/blob/master/SYSTEM.md)
-在codegen.xml中填入要生成的表信息，代码所属模块，db,dao,domain,controller,view,api 6个层次指定哪几个会生成相应的代码

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/3.png)

-源码中代码生成器的执行入口

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/8.png)

-生成结果

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/7.png)

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/1.png)

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/2.png)

-效果展示，给刚生成的功能配置路劲

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/5.png)

-查看效果

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/6.png)
>>>>>>> branch 'master' of https://github.com/fuhaodashu/pai.git
=======
# pai
#代码生成器详解
-源码位于pai-tools/pai-codegen中，操作代码生成器需要修改配置文件codegen.xml。

-代码生成器是基于数据表结构、代码模板和模板解析引擎生成标准代码文件。用于减少重复工作量

-代码模板一般使用FreeMarker或Velocity，因为这两者都是模板解析语言，不需要预编译。我采用FreeMarker技术。

-数据表设计有两种形式：1）单表：最为常见。2）主从表：因为从表可能没有独立的菜单，它的CRUD依赖主表。

1、配置文件解析层：

-代码生成配置放置到codegen.xml中。

-设计一个专门的ConfigModelHelper用于解析xml文件，构造为Java对象，方便开发处理。

2、数据表访问解析层：

-就是根据数据库配置，读取指定要生成代码的表的结构，包括表名、字段、类型和备注等等。

-如果是主从表，则同时要读取外键信息，构造主从关系。

-通过数据库工具类（基于接口实现，不同数据库类型不同的实现）

3、代码生成

-基于前面构造好的配置文件对象（ConfigModel）和数据表结构对象（TableModel）

-遍历所有待处理的代码模板，通过FreeMarker模板引擎进行代码文件生成

-根据配置文件的参数进行覆盖或跳过等。

-控制台打印日志，方便查看生成结果。

4、代码删除

-原理同上，只是做的操作是文件删除。

#效果展示，以下是我自己实验用的一个小例子，代码架构设计请点击 [系统搭建](https://github.com/fuhaodashu/pai/blob/master/SYSTEM.md)
-在codegen.xml中填入要生成的表信息，代码所属模块，db,dao,domain,controller,view,api 6个层次指定哪几个会生成相应的代码

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/3.png)

-源码中代码生成器的执行入口

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/8.png)

-生成结果

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/7.png)

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/1.png)

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/2.png)

-效果展示，给刚生成的功能配置路劲

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/5.png)

-查看效果

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/6.png)
>>>>>>> branch 'master' of https://github.com/fuhaodashu/pai.git
=======
# pai
#代码生成器详解
-源码位于pai-tools/pai-codegen中，操作代码生成器需要修改配置文件codegen.xml。

-代码生成器是基于数据表结构、代码模板和模板解析引擎生成标准代码文件。用于减少重复工作量

-代码模板一般使用FreeMarker或Velocity，因为这两者都是模板解析语言，不需要预编译。我采用FreeMarker技术。

-数据表设计有两种形式：1）单表：最为常见。2）主从表：因为从表可能没有独立的菜单，它的CRUD依赖主表。

1、配置文件解析层：

-代码生成配置放置到codegen.xml中。

-设计一个专门的ConfigModelHelper用于解析xml文件，构造为Java对象，方便开发处理。

2、数据表访问解析层：

-就是根据数据库配置，读取指定要生成代码的表的结构，包括表名、字段、类型和备注等等。

-如果是主从表，则同时要读取外键信息，构造主从关系。

-通过数据库工具类（基于接口实现，不同数据库类型不同的实现）

3、代码生成

-基于前面构造好的配置文件对象（ConfigModel）和数据表结构对象（TableModel）

-遍历所有待处理的代码模板，通过FreeMarker模板引擎进行代码文件生成

-根据配置文件的参数进行覆盖或跳过等。

-控制台打印日志，方便查看生成结果。

4、代码删除

-原理同上，只是做的操作是文件删除。

#效果展示，以下是我自己实验用的一个小例子，代码架构设计请点击 [系统搭建](https://github.com/fuhaodashu/pai/blob/master/SYSTEM.md)
-在codegen.xml中填入要生成的表信息，代码所属模块，db,dao,domain,controller,view,api 6个层次指定哪几个会生成相应的代码

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/3.png)

-源码中代码生成器的执行入口

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/8.png)

-生成结果

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/7.png)

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/1.png)

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/2.png)

-效果展示，给刚生成的功能配置路劲

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/5.png)

-查看效果

![image](https://github.com/fuhaodashu/pai/blob/master/pai-tools/image/codegen/6.png)
>>>>>>> branch 'master' of https://github.com/fuhaodashu/pai.git
