package com.pai.biz.message.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.pai.base.core.helper.SpringHelper;
import com.pai.base.db.mybatis.impl.domain.MyBatisPage;
import com.pai.base.db.persistence.dao.IQueryDao;
import com.pai.biz.frame.repository.AbstractRepository;
import com.pai.biz.message.domain.MessageRecord;
import com.pai.biz.message.persistence.dao.MessageRecordQueryDao;
import com.pai.biz.message.persistence.entity.MessageRecordPo;
import com.pai.biz.message.repository.MessageRecordRepository;

/**
 * 对象功能:pai_message_record Repository接口的实现类
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-09-10 16:00:11
 */
@Repository
public class MessageRecordRepositoryImpl extends AbstractRepository<String, MessageRecordPo,MessageRecord> implements MessageRecordRepository{
	  
	@Resource
	private  MessageRecordQueryDao messageRecordQueryDao;

	public MessageRecord newInstance() {
		MessageRecordPo po = new MessageRecordPo();
		MessageRecord messageRecord = SpringHelper.getBean(MessageRecord.class);
		messageRecord.setData(po);
		return messageRecord;
	}

	public MessageRecord newInstance(MessageRecordPo po) {
		MessageRecord messageRecord = SpringHelper.getBean(MessageRecord.class);
		messageRecord.setData(po);
		return messageRecord;
	} 
	
	@Override
	protected IQueryDao<String, MessageRecordPo> getQueryDao() {
		return messageRecordQueryDao;
	}

	@Override
	public List<MessageRecordPo> findNoDeadMessages(String status, MyBatisPage page) {
		return messageRecordQueryDao.findByKey("findNoDeadMessages", b().a("status", status).p(), page);
	}
	
}
