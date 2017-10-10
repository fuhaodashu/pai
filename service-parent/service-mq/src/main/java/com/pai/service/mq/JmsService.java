package com.pai.service.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.pai.base.api.model.IMsgVo;

public class JmsService {
	
	private JmsTemplate jmsTemplate;

	private String queue;
	
	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	/**
	 * 发送一条消息到指定的队列（目标）
	 * @param queueName 队列名称
	 * @param message 消息内容
	 */
	public void send(IMsgVo msgVo) {
		sendObj(queue, msgVo);
	}
	
	public void send(String message) {
		sendText(queue, message);
	}
	
	private void sendObj(String queueName, final IMsgVo msgVo){
		jmsTemplate.send(queueName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(msgVo);
			}
		});
	}
	
	public void sendText(String queueName,final String message){
		jmsTemplate.send(queueName, new MessageCreator() {
		@Override
		public Message createMessage(Session session) throws JMSException {
			return session.createTextMessage(message);
		}
	});
}
}
