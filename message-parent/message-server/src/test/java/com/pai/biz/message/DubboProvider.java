package com.pai.biz.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pai.base.core.helper.SpringHelper;
import com.pai.biz.message.api.service.MessageNotifyService;

/**
 * 
 * @ClassName: DubboProvider 
 * @Description: TODO
 * @author: fuhao
 * @date: Jul 9, 2017 7:30:20 PM
 */
public class DubboProvider {
	
	private static Logger log = LoggerFactory.getLogger(DubboProvider.class);

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/conf/message-server.xml");
			context.start();
			
			MessageNotifyService messageNotifyService = SpringHelper.getBean(MessageNotifyService.class);
			messageNotifyService.startNoyifyThread();
			log.info("=======dubbo 启动成功=======");
		} catch (Exception e) {
			log.error("== DubboProvider context start error:",e);
		}
		synchronized (DubboProvider.class) {
			while (true) {
				try {
					DubboProvider.class.wait();
				} catch (InterruptedException e) {
					log.error("== synchronized error:",e);
				}
			}
		}
	}
    
}