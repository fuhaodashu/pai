package com.pai.biz.message.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pai.biz.frame.domain.AbstractDomain;
import com.pai.base.core.helper.SpringHelper;
import com.pai.biz.message.persistence.dao.MessageNotifyDao;
import com.pai.biz.message.persistence.entity.MessageNotifyPo;

/**
 * 对象功能:最大通知记录表 领域对象实体
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-09-07 23:37:41
 * 命名规范：插入以saveXx前缀，修改updateXx前缀，删除deleteXx前缀
 */
@SuppressWarnings("serial")
@Service
@Scope("prototype")
public class MessageNotify extends AbstractDomain<String, MessageNotifyPo>{
	 
	 private MessageNotifyDao messageNotifyDao = null;

	protected void init(){
		messageNotifyDao = SpringHelper.getBean(MessageNotifyDao.class);
		setDao(messageNotifyDao);
	}	 
	 
}
