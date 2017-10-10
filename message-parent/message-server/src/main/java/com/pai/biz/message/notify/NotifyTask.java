package com.pai.biz.message.notify;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.pai.base.core.constants.StatusEnum;
import com.pai.base.core.helper.SpringHelper;
import com.pai.base.core.util.HttpClientUtil;
import com.pai.base.core.util.JsonUtil;
import com.pai.base.core.util.Mapper;
import com.pai.biz.message.api.model.Notify;
import com.pai.biz.message.api.service.MessageNotifyService;
import com.pai.biz.message.domain.MessageNotify;
import com.pai.biz.message.persistence.entity.MessageNotifyPo;
import com.pai.biz.message.util.NotifyParamUtil;

public class NotifyTask implements Runnable, Delayed {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private long executeTime;
	
	private MessageNotifyPo messageNotifyPo;
	
	private MessageNotifyService messageNotifyService;
	
	public NotifyTask() {
		
    }

    public NotifyTask(MessageNotifyPo messageNotifyPo, MessageNotifyService messageNotifyService) {
        super();
        this.messageNotifyPo = messageNotifyPo;
        this.messageNotifyService = messageNotifyService;
        this.executeTime = getExecuteTime(messageNotifyPo);
    }
	
	private long getExecuteTime(MessageNotifyPo messageNotifyPo) {
        long lastTime = messageNotifyPo.getLatestNotifyTime().getTime();
        Integer nextNotifyTime = NotifyParamUtil.getSendTime().get(messageNotifyPo.getNotifyTimes());
        return (nextNotifyTime == null ? 0 : nextNotifyTime * 1000) + lastTime;
    }
	
	@Override
	public int compareTo(Delayed o) {
		NotifyTask task = (NotifyTask) o;
        return executeTime > task.executeTime ? 1 : (executeTime < task.executeTime ? -1 : 0);
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(executeTime - System.currentTimeMillis(), unit.SECONDS);
	}

	@Override
	public void run() {
		MessageNotify messageNotify = SpringHelper.getBean(MessageNotify.class);
		try {
			String result = "POST".equalsIgnoreCase(messageNotifyPo.getNotifyType()) ? HttpClientUtil.httpJsonPost(messageNotifyPo.getUrl(), messageNotifyPo.getParamBody()) : HttpClientUtil.httpGet(messageNotifyPo.getUpdateBy(), JsonUtil.getMapFromJson(messageNotifyPo.getParamBody()));
			log.info("NotifyTask-->" + messageNotifyPo.getUrl() + "-->" + JSON.toJSONString(result));
			//成功
			if(StringUtils.isNotBlank(result) && result.contains(messageNotifyPo.getSuccessVal())) {
				messageNotifyPo.setStatus(StatusEnum.SUCCESS.name());
				messageNotifyPo.setNotifyTimes(messageNotifyPo.getNotifyTimes() + 1);
				//覆盖真正的返回消息
				messageNotifyPo.setSuccessVal(result); 
				messageNotifyPo.setUpdateTime(new Date());
				messageNotify.setData(messageNotifyPo);
				messageNotify.update();
			}else {
				saveAndSubmitNotify(messageNotify);
			}
		} catch (Exception e) {
			log.error("NotifyTask-->" + messageNotifyPo.getUpdateBy(), e);
			saveAndSubmitNotify(messageNotify);
		}
	}

	private void saveAndSubmitNotify(MessageNotify messageNotify) {
		messageNotifyPo.setNotifyTimes(messageNotifyPo.getNotifyTimes() + 1);
		messageNotify.setData(messageNotifyPo);
		messageNotify.update();
		messageNotifyService.submitMessageNotifyService(Mapper.getInstance().copyProperties(messageNotifyPo, Notify.class));
	}
	
}
