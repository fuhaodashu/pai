package com.pai.biz.message.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisQueryDaoImpl;
import com.pai.biz.message.persistence.dao.MessageRecordQueryDao;
import com.pai.biz.message.persistence.entity.MessageRecordPo;

/**
 * 对象功能:pai_message_record QueryDao接口的实现
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-09-10 16:00:11
 */
@SuppressWarnings("serial")
@Repository
public class MessageRecordQueryDaoImpl extends MyBatisQueryDaoImpl<String, MessageRecordPo> implements MessageRecordQueryDao{

    @Override
    public String getNamespace() {
        return MessageRecordPo.class.getName();
    }
	
}
