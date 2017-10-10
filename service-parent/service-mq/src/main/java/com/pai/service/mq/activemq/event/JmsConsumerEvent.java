package com.pai.service.mq.activemq.event;

import org.springframework.context.ApplicationEvent;

public class JmsConsumerEvent extends ApplicationEvent{
	/**
	 * serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = -2686157400135866619L;
	
	public JmsConsumerEvent(Object source) {
		super(source);
	}

	

}