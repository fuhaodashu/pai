package com.pai.biz.member;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @ClassName: DubboProvider 
 * @Description: TODO
 * @author: fuhao
 * @date: Jul 9, 2017 7:30:20 PM
 */
public class DubboProvider {
	
	private static final Log log = LogFactory.getLog(DubboProvider.class);

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/conf/member-server.xml");
			context.start();
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