package com.pai.service.mq.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pai.base.api.model.IMsgVo;
import com.pai.base.core.helper.SpringHelper;
import com.pai.service.mq.activemq.event.JmsConsumerEvent;
/**
 * 队列异步不会丢消息，主题会丢消息，即通过管控台发送异步消息，服务器启动后队列能收到，主题不能收到
 * 队列发送1,2,3,4时，如果监听器配置了A/B/C，3个监听类，则A,B,C随机收到1,2,3。收到1的还会收到4
 * 主题模式发送1,2,3,4时，如果监听器配置了A/B/C，3个监听类，则A，B，C同时收到1,2,3,4
 * 如果要实现topic模式又不丢失消息，则使用spring事件模拟，但这只能监听一个destination,还是要配置多个destination实现
 */
public class JmsConsumer {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	public void execute(Object obj) {
		if(obj instanceof IMsgVo) {
			IMsgVo msgVo = (IMsgVo)obj;
			JmsHandler<IMsgVo> jmsHandler = (JmsHandler<IMsgVo>)SpringHelper.getBean(msgVo.getMsgType() + "Handler");
			if(jmsHandler!=null){
				jmsHandler.execute(msgVo);
			}else {
				//模拟多个监听
				JmsConsumerEvent event = new JmsConsumerEvent(msgVo);
				SpringHelper.publishEvent(event);
			}
		}else {
			//text
			String msg = (String) obj;
			
		}
	}	
}
