#文档生成器详解

-对于一个团队开发来说，文档都是必不可少的，比如团队之间进行对接，需要文档来表达自己的意图。但一般来说文档的编写却又是开发者最讨厌做的事，对于比较"懒"的开发来说，都希冀有一种有效的方法来减免这件事。

-市场上有一些常用的文档生成器，比如swagger，和spring有较好融合，但是它一般是用于web controller层的api文档，对于现在流行的分布式框架来说，可能一个系统分出了不同的模块给不同的团队进行开发，此时各团队的协作就需要有效的文档来说明自己的服务接口。

-本文档生成器主要利用了java的反射机制，提供rpc服务接口规范文档和rest风格接口规范文档，能独立运行，与系统本身无耦合。

1、工具使用详解：

-参数说明，在接口类属性上加上@AutoDocField注解，或者在接口方法内部加上@AutoDocParam注解。因为两处获取注解的方式不一样，所以用不同的注解做区分

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/doc/doc1.png)

-方法说明，在接口方法上加上@AutoDocMethod进行注明

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/doc/doc2.png)

2、效果展示：

-执行文档生成

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/doc/doc5.png)

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/doc/doc4.png)

