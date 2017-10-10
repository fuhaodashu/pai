package com.pai.service.mq.jms;


/**
 * 
 * <pre> 
 * 描述： 发送消息处理接口
 * 		目前实现 1.发送邮件消息。 2.发送短消息。 3.发送内部消息。
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 * </pre>
 */
public interface JmsHandler<T> {
	/**
	 * 返回该Handler对应的消息类型键。
	 * 系统自带的则从MsgType枚举中获得，扩展的可以加枚举，也可以直接写值。
	 * @return  String
	 */
	String getMsgType();
	
	/**
	 * 处理JmsVo，如发送、持久化等操作。
	 * @param IJmsVo
	 * @return  boolean
	 */
	void execute(T vo);
}
