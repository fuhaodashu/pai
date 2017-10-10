#代码生成器详解

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

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/codegen.png)

#效果展示
-在codegen.xml中填入要生成的表信息，代码所属模块，db,dao,domain,controller,view,api,service,serviceImpl 6个层次指定哪几个会生成相应的代码

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/codegen/3.png)

-源码中代码生成器的执行入口

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/codegen/8.png)

-生成结果

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/codegen/7.png)

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/codegen/1.png)

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/codegen/2.png)

-dubbo配置服务端和消费端，启动admin-tomcat和相关service服务

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/codegen/10.png)

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/codegen/11.png)

-界面展示效果，先登录

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/codegen/4.png)

-给刚生成的功能配置路劲

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/codegen/5.png)
![image](https://github.com/fuhaopai/pai/blob/master/doc/image/codegen/9.png)

-查看效果

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/codegen/6.png)
