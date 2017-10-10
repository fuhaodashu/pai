package com.pai.biz.message.api.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pai.base.api.constants.Bool;
import com.pai.base.api.constants.ResponseCode;
import com.pai.base.core.entity.MapBuilder;
import com.pai.base.core.util.HttpClientUtil;
import com.pai.base.core.util.Mapper;
import com.pai.base.db.mybatis.impl.domain.MyBatisPage;
import com.pai.biz.message.api.model.Message;
import com.pai.biz.message.api.service.MessageService;
import com.pai.biz.message.persistence.entity.MessageRecordPo;
import com.pai.biz.message.repository.MessageRecordRepository;
import com.pai.biz.message.util.NotifyParamUtil;
import com.pai.service.mq.JmsService;

/**
 * 分布式消息组件
 * @ClassName: MessageServiceImpl 
 * @Description: 最终一致性分布式服务解决方案
 * @author: fuhao
 * @date: Sep 5, 2017 12:47:49 AM
 */
@Service
public class MessageServiceImpl implements MessageService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final Integer PAGESIZE = 30;
	
	@Resource
	private MessageRecordRepository messageRecordRepository;
	
	@Resource
	private JmsService jmsService;
	
	@Resource
	ThreadPoolTaskExecutor taskExecutor;
	
	@Override
	public String saveMessageWaitingConfirm(Message message) {
		MessageRecordPo messageRecordPo = Mapper.getInstance().copyProperties(message, MessageRecordPo.class);
		messageRecordPo.setAreadlyDead(Bool.N.key());
		messageRecordPo.setMessageSendTimes((short) 1);
		messageRecordPo.setCreateTime(new Date());
		messageRecordPo.setStatus(MessageRecordPo.MessageStatusEnum.WAITING_CONFIRM.name());
		messageRecordRepository.newInstance(messageRecordPo).save();
		return messageRecordPo.getId();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void confirmAndSendMessage(String messageId) {
		MessageRecordPo messageRecordPo = messageRecordRepository.get(messageId);
		messageRecordPo.setStatus(MessageRecordPo.MessageStatusEnum.SENDING.name());
		messageRecordPo.setUpdateTime(new Date());
		messageRecordRepository.newInstance(messageRecordPo).save();
		Message message = Mapper.getInstance().copyProperties(messageRecordPo, Message.class);
		jmsService.send(message);
	}
	
	//先执行本地事务，再保存消息。返回成功则提交本地事务，否则抛异常回滚
	//如果消息服务器拿到消息返回结果的时候，主动方服务器挂了，会出现事务问题
	@Deprecated
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String saveAndSendMessage(Message message) {
		MessageRecordPo messageRecordPo = Mapper.getInstance().copyProperties(message, MessageRecordPo.class);
		messageRecordPo.setAreadlyDead(Bool.N.key());
		messageRecordPo.setMessageSendTimes((short) 1);
		messageRecordPo.setCreateTime(new Date());
		messageRecordPo.setUpdateTime(new Date());
		messageRecordPo.setStatus(MessageRecordPo.MessageStatusEnum.SENDING.name());
		messageRecordRepository.newInstance(messageRecordPo).save();
		jmsService.send(message);
		return messageRecordPo.getId();
	}

	@Override
	public void deleteMessageById(String messageId) {
		messageRecordRepository.newInstance().destroy(messageId);
	}

	@Override
	public void handleWaitingConfirmTimeOutMessages() {
		//递归分页获取所有的为死亡待确认消息，不返回消息主体，防止数据量太大造成超时
		List<MessageRecordPo> messageRecordPos = new ArrayList<MessageRecordPo>();
		findMessageRecords(messageRecordPos, MessageRecordPo.MessageStatusEnum.WAITING_CONFIRM.name(), new MyBatisPage(1, PAGESIZE));
		for(final MessageRecordPo messageRecordPo : messageRecordPos) {
			//先通过回调函数查主动方是否与消息记录，判断是否事务提交
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						if(taskExecutor.getActiveCount() < taskExecutor.getMaxPoolSize()) {
							String result = HttpClientUtil.httpJsonPost(messageRecordPo.getUrl(), JSON.toJSONString(MapBuilder.getInstance().a("messageId", messageRecordPo.getId())));
							log.info(messageRecordPo.getUrl()+"-->"+messageRecordPo.getId()+"-->"+result);
							if(StringUtils.isNotBlank(result)) {
								//判断值是否成功
								JSONObject jsonObject = JSONObject.parseObject(result);
								if(ResponseCode.OK.getCode().equals(jsonObject.getString("code"))){
									confirmAndSendMessage(messageRecordPo.getId());
								}else {
									deleteMessageById(messageRecordPo.getId());
								}
							}
						}
					} catch (Exception e) {
						log.error("handleWaitingConfirmTimeOutMessages-->" + messageRecordPo.getId(), e);
					}
				}
			});
		}
	}
	
	@Override
	public void handleSendingTimeOutMessage() {
		// 递归分页获取所有的未死亡发送中的消息，不返回消息主体，防止数据量太大造成超时
		List<MessageRecordPo> messageRecordPos = new ArrayList<MessageRecordPo>();
		findMessageRecords(messageRecordPos, MessageRecordPo.MessageStatusEnum.SENDING.name(), new MyBatisPage(1, PAGESIZE));
		// 根据配置获取通知间隔时间 
		final Map<Integer, Integer> notifyParam = NotifyParamUtil.getSendTime();
		final int maxTimes = notifyParam.size();
		for(final MessageRecordPo messageRecordPo : messageRecordPos) {
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						if(taskExecutor.getActiveCount() < taskExecutor.getMaxPoolSize()) {
							int reSendTimes = messageRecordPo.getMessageSendTimes();
							if (maxTimes < reSendTimes) {
								// 标记为死亡
								setMessageToAreadlyDead(messageRecordPo);
							}else {
								// 判断是否达到发送消息的时间间隔条件
								long needTime = Calendar.getInstance().getTimeInMillis()-notifyParam.get(reSendTimes) * 60 * 1000;
								if(messageRecordPo.getUpdateTime().getTime() <= needTime) {
									// 更新重发次数
									messageRecordPo.setMessageSendTimes((short) (messageRecordPo.getMessageSendTimes()+1));
									messageRecordPo.setUpdateTime(new Date());
									messageRecordRepository.newInstance(messageRecordPo).save();
									// 重新发送消息
									Message message = Mapper.getInstance().copyProperties(messageRecordPo, Message.class);
									jmsService.send(message);
								}
							}
						}
					} catch (Exception e) {
						log.error("handleSendingTimeOutMessage-->" + messageRecordPo.getId(), e);
					}
				}
			});
		}
	}
	
	private void setMessageToAreadlyDead(MessageRecordPo messageRecordPo) {
		messageRecordPo.setAreadlyDead(Bool.Y.key());
		messageRecordRepository.newInstance(messageRecordPo).save();
	}
	
	// 递归分页获取所有的未死亡发送中的消息，不返回消息主体，消息主体在被动方需要的时候先从redis中获取，如果没有则从数据库中获取
	private List<MessageRecordPo> findMessageRecords(List<MessageRecordPo> list, String status, MyBatisPage page) {
		//获取所有未死亡待确认的消息
		List<MessageRecordPo> messageRecordPos = messageRecordRepository.findNoDeadMessages(status, page);
		list.addAll(messageRecordPos);
		if(messageRecordPos.size() == page.getPageSize()) {
			page.setPage(page.getPage() + 1);
			findMessageRecords(list, status, page);
		} 
		return list;
	}
}
