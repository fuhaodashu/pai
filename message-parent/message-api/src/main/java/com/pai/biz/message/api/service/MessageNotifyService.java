package com.pai.biz.message.api.service;

import com.pai.biz.message.api.model.Notify;

/**
 * 最大通知数接口,主要用于第三方异步通行,无需返回呈现到前端
 * @ClassName: MessageNotifyService 
 * @Description: TODO
 * @author: max
 * @date: Sep 9, 2017 9:43:52 PM
 */
public interface MessageNotifyService {
	
	public void submitMessageNotifyService(Notify notify);

	public void startNoyifyThread();
}
