package com.pai.biz.message.api.service;

import com.pai.biz.message.api.model.Message;

public interface MessageService {
	/**
	 * 预存储消息. 
	 */
	public String saveMessageWaitingConfirm(Message message);
	
	/**
	 * 确认并发送消息.
	 */
	public void confirmAndSendMessage(String messageId);
	
	/**
	 * 保存发送消息，在业务的最后执行，保存前面的代码本地事务不异常.
	 */
	public String saveAndSendMessage(Message message);
	
	/**
	 * 根据消息ID删除消息，被动方事务提交后删除消息或者定时删除发起方成功创建消息，确事务回滚的消息
	 */
	public void deleteMessageById(String messageId);
	
	/**
	 * 处理[WAITING_CONFIRM]状态的消息
	 */
	public void handleWaitingConfirmTimeOutMessages();
	
	/**
	 * 处理[SENDING]状态的消息
	 */
	public void handleSendingTimeOutMessage();
}
