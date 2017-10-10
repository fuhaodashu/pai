#ID校验组件

-使用场景：在内部对接或者对外对接时，需要限定接口参数的类型，是否非空。

-作为程序员很烦对每一个方法接口进行参数校验，既容易遗漏，代码也不美观。我们更需要的是关注业务，所以希望有一种通用的方式来统一处理校验，简单易用，同时又不希望多余的代码影响业务代码的阅读性。

-我选择自定义注解的形式，支持可扩展，通过aop拦截接口参数来实现

1、使用示例：

-参数说明

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/check/check1.png)

-aop cglib代理拦截

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/doc/doc3.png)

2、效果展示：

-拦截返回错误信息，注释其中某个属性

![image](https://github.com/fuhaopai/pai/blob/master/doc/image/check/check2.png)


