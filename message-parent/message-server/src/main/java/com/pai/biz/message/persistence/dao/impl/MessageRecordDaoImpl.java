package com.pai.biz.message.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisDaoImpl;
import com.pai.biz.message.persistence.dao.MessageRecordDao;
import com.pai.biz.message.persistence.entity.MessageRecordPo;

/**
 * 对象功能:pai_message_record Dao接口的实现类
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-09-10 16:00:10
 */
@Repository
public class MessageRecordDaoImpl extends MyBatisDaoImpl<String, MessageRecordPo> implements MessageRecordDao{

    @Override
    public String getNamespace() {
        return MessageRecordPo.class.getName();
    }
	
}
