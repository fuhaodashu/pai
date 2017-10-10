package com.pai.biz.message.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.pai.base.core.helper.SpringHelper;
import com.pai.base.db.mybatis.impl.domain.MyBatisPage;
import com.pai.base.db.persistence.dao.IQueryDao;
import com.pai.biz.frame.repository.AbstractRepository;
import com.pai.biz.message.domain.MessageNotify;
import com.pai.biz.message.persistence.dao.MessageNotifyQueryDao;
import com.pai.biz.message.persistence.entity.MessageNotifyPo;
import com.pai.biz.message.repository.MessageNotifyRepository;

/**
 * 对象功能:最大通知记录表 Repository接口的实现类
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-09-07 23:37:41
 */
@Repository
public class MessageNotifyRepositoryImpl extends AbstractRepository<String, MessageNotifyPo,MessageNotify> implements MessageNotifyRepository{
	  
	@Resource
	private  MessageNotifyQueryDao messageNotifyQueryDao;

	public MessageNotify newInstance() {
		MessageNotifyPo po = new MessageNotifyPo();
		MessageNotify messageNotify = SpringHelper.getBean(MessageNotify.class);
		messageNotify.setData(po);
		return messageNotify;
	}

	public MessageNotify newInstance(MessageNotifyPo po) {
		MessageNotify messageNotify = SpringHelper.getBean(MessageNotify.class);
		messageNotify.setData(po);
		return messageNotify;
	} 
	
	@Override
	protected IQueryDao<String, MessageNotifyPo> getQueryDao() {
		return messageNotifyQueryDao;
	}

	@Override
	public List<MessageNotifyPo> findUsedMessageNotifys(int notifyTimes, MyBatisPage page) {
		return messageNotifyQueryDao.findByKey("findUsedMessageNotifys", b().a("notifyTimes", notifyTimes).p(), page);
	}
	
}
