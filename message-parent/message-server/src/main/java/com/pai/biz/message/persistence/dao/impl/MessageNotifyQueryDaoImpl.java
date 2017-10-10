package com.pai.biz.message.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisQueryDaoImpl;
import com.pai.biz.message.persistence.dao.MessageNotifyQueryDao;
import com.pai.biz.message.persistence.entity.MessageNotifyPo;

/**
 * 对象功能:最大通知记录表 QueryDao接口的实现
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-09-07 23:37:41
 */
@SuppressWarnings("serial")
@Repository
public class MessageNotifyQueryDaoImpl extends MyBatisQueryDaoImpl<String, MessageNotifyPo> implements MessageNotifyQueryDao{

    @Override
    public String getNamespace() {
        return MessageNotifyPo.class.getName();
    }
	
}
