package com.pai.biz.message.api.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.DelayQueue;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.pai.base.core.constants.StatusEnum;
import com.pai.base.core.util.Mapper;
import com.pai.base.db.mybatis.impl.domain.MyBatisPage;
import com.pai.biz.message.api.model.Notify;
import com.pai.biz.message.api.service.MessageNotifyService;
import com.pai.biz.message.notify.NotifyTask;
import com.pai.biz.message.persistence.entity.MessageNotifyPo;
import com.pai.biz.message.repository.MessageNotifyRepository;
import com.pai.biz.message.util.NotifyParamUtil;

@Service
public class MessageNotifyServiceImpl implements MessageNotifyService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final Integer PAGESIZE = 30;
	
	@Resource
	private ThreadPoolTaskExecutor threadPool;
	
	@Resource
	private MessageNotifyRepository messageNotifyRepository;
	
	//只开启一个线程，一直占用，不然如果一次调用就开启一个线程，容易耗光线程池
	public static DelayQueue<NotifyTask> tasks = new DelayQueue<NotifyTask>();
	
	@Override
	public void submitMessageNotifyService(Notify notify) {
		MessageNotifyPo messageNotifyPo = Mapper.getInstance().copyProperties(notify, MessageNotifyPo.class);
        // 刚刚接收到的数据
        if (StringUtils.isBlank(messageNotifyPo.getId())) {
        	//首次通知
        	messageNotifyPo.setLatestNotifyTime(new Date());
        	messageNotifyPo.setCreateTime(new Date());
        	messageNotifyPo.setNotifyTimes(1);
        	messageNotifyRepository.newInstance(messageNotifyPo).save();
        }
        // 通知次数
     	Integer notifyTimes = messageNotifyPo.getNotifyTimes(); 
        Map<Integer, Integer> notifyParam = NotifyParamUtil.getSendTime();
        Integer maxNotifyTime = notifyParam.size();
        long time = messageNotifyPo.getLatestNotifyTime().getTime();
        if (notifyTimes < maxNotifyTime) {
            Integer nextKey = notifyTimes + 1;
            Integer next = notifyParam.get(nextKey);
            if (next != null) {
                time += 1000 * 60 * next + 1;
                messageNotifyPo.setLatestNotifyTime(new Date(time));
                tasks.put(new NotifyTask(messageNotifyPo, this));
            }
        } else {
        	messageNotifyPo.setStatus(StatusEnum.FAILURE.name());
        	messageNotifyPo.setUpdateTime(new Date());
        	messageNotifyRepository.newInstance(messageNotifyPo).save();
        }
	}
	
	public void startThread() {
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						if (threadPool.getActiveCount() < threadPool.getMaxPoolSize()) {
							final NotifyTask task = tasks.take();//使用take方法获取过期任务,如果获取不到,就一直等待,知道获取到数据
                            if (task != null) {
                            	threadPool.execute(new Runnable() {
									@Override
									public void run() {
										log.info(threadPool.getActiveCount() + "---------");
                                        tasks.remove(task);
                                        task.run();
									}
								});
                            }
						}
					}
				} catch (Exception e) {
					log.error("MessageNotifyServiceImpl-->", e);
				}
			}
		});
	}

	@Override
	public void startNoyifyThread() {
		//开启线程
		startThread();
		//查找所有不成功且通知次数少于5次的记录
		List<MessageNotifyPo> messageNotifyPos = new ArrayList<MessageNotifyPo>();
		findMessageNotifys(messageNotifyPos, new MyBatisPage(1, PAGESIZE));
		for(MessageNotifyPo messageNotifyPo : messageNotifyPos) {
			messageNotifyPo.setLatestNotifyTime(new Date());
			submitMessageNotifyService(Mapper.getInstance().copyProperties(messageNotifyPo, Notify.class));
		}
	}
	
	// 递归分页获取所有的未死亡发送中的消息
	private List<MessageNotifyPo> findMessageNotifys(List<MessageNotifyPo> list, MyBatisPage page) {
		//获取所有未死亡待确认的消息
		List<MessageNotifyPo> messageNotifyPos = messageNotifyRepository.findUsedMessageNotifys(NotifyParamUtil.getSendTime().size(), page);
		list.addAll(messageNotifyPos);
		if(messageNotifyPos.size() == page.getPageSize()) {
			page.setPage(page.getPage() + 1);
			findMessageNotifys(list, page);
		} 
		return list;
	}
}
