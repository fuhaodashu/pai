#前期先更基于mq最终一致性解决方案，和最大通知数方案。后期将补上分布式事务TCC方案的详细分析，TCC主要参考开源github项目https://github.com/changmingxie/tcc-transaction/tree/master-1.2.x

1、基于mq最终一致性，主要适用场景为异步保存，并且不需要返回B模块数据

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/transaction/transaction.png)

--假设要同时操作A模块和B模块的数据，为了确保两个服务之间的一致性，同时又保证事务减少对业务的耦合，这里把事务控制做成了一个通用消息组件

--对于消息服务，主要包含四个方法saveMessageWaitingConfirm(Message)、confirmAndSendMessage(String)，还有两个定时任务处理handleWaitingConfirmTimeOutMessages()、handleSendingTimeOutMessage(),具体使用看上图。

--massage实体消息类中主要包含
URL回调地址: 用于确认A模块的本地事务是否执行成功（A模块在本地事务中会保存saveMessageWaitingConfirm方法返回的messageId记录，每个事务发起方都会创建一张消息表），如果不成功则在handleWaitingConfirmTimeOutMessages中删除消息
messageBody: B模块的操作数据
msgType: 限定JMS-Server使用的java类

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/transaction/message1.png)

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/transaction/message.png)

--A模块调用完后，在B模块实现幂等性操作，防止重读调用，然后删除消息id

--缺点
不能满足时效性，可补偿性，有一些场景也不适用，比如：A模块执行本地事务，然后把数据给B模块调用，调用B模块后，A模块要用B模块返回数据继续后面的事务，这些带数据返回和有先后顺序的场景是不适用的。

2、最大通知数，主要适用场景为调用第三方公司接口，比如第三方支付，引入的第三方合作公司的http接口

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/transaction/notify.png)

3、TCC事务处理，自定义全局事务，自定义注解@Compensable，通过拦截器的配置控制confirmMethod和cancelMethod方法的执行，用于提交和事务补偿操作

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/transaction/transaction2.png)

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/transaction/transaction3.png)

--缺点

代码编写比较复杂，每一次提交都需要实现三个方法实现难度较大。


