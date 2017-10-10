package com.pai.service.mq.activemq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.pai.base.api.model.IMsgVo;


public class SendRunnable  implements Runnable{
	Log logger = LogFactory.getLog(this.getClass());
	private int queueLength = 9999;
	
	private AtomicBoolean stopSending = new AtomicBoolean();
	private ArrayBlockingQueue<Object> messagesToSend = new ArrayBlockingQueue<Object>(queueLength);
	private JmsTemplate producerTemplate;
	private String QUEUE;

	
	public String getQUEUE() {
		return QUEUE;
	}

	public void setQUEUE(String qUEUE) {
		QUEUE = qUEUE;
	}

	public SendRunnable(JmsTemplate producerTemplate,String queue) {
		super();
		this.producerTemplate = producerTemplate;
		//IConfigHelper configHelper =SpringHelper.getBean(IConfigHelper.class);
		//QUEUE = configHelper.getParamValue(queue);
		QUEUE = queue;
	}

	public void send(Object message) {
		try {
			messagesToSend.put(message);
		} catch (InterruptedException e) {
			System.err.println("Unable to send even: " + e);
		}
	}

	public void run() {
		while (!stopSending.get()) {
			try {
				final Object message = messagesToSend.take();
				if(message!=null){
					if(message instanceof IMsgVo){
						final IMsgVo msgVo = (IMsgVo)message;
						producerTemplate.send(QUEUE, new MessageCreator() {
							public Message createMessage(Session session) throws JMSException {
								logger.info(Thread.currentThread() + " 发出消息：" + message);								
								return session.createObjectMessage(msgVo);
							}
						});
					}else{
						producerTemplate.send(QUEUE, new MessageCreator() {
							public Message createMessage(Session session) throws JMSException {
								logger.info(Thread.currentThread() + " 发出String消息：" + message);
								return session.createTextMessage(message.toString());
							}
						});
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void shutdown() {
		stopSending.set(true);
	}
}
