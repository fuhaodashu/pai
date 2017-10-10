package com.pai.biz.message.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pai.biz.frame.domain.AbstractDomain;
import com.pai.base.core.helper.SpringHelper;
import com.pai.biz.message.persistence.dao.MessageRecordDao;
import com.pai.biz.message.persistence.entity.MessageRecordPo;

/**
 * 对象功能:pai_message_record 领域对象实体
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-09-10 16:00:11
 * 命名规范：插入以saveXx前缀，修改updateXx前缀，删除deleteXx前缀
 */
@SuppressWarnings("serial")
@Service
@Scope("prototype")
public class MessageRecord extends AbstractDomain<String, MessageRecordPo>{
	 
	 private MessageRecordDao messageRecordDao = null;

	protected void init(){
		messageRecordDao = SpringHelper.getBean(MessageRecordDao.class);
		setDao(messageRecordDao);
	}	 
	 
}
