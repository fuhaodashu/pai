package com.pai.biz.message.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisDaoImpl;
import com.pai.biz.message.persistence.dao.MessageNotifyDao;
import com.pai.biz.message.persistence.entity.MessageNotifyPo;

/**
 * 对象功能:最大通知记录表 Dao接口的实现类
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-09-07 23:37:41
 */
@Repository
public class MessageNotifyDaoImpl extends MyBatisDaoImpl<String, MessageNotifyPo> implements MessageNotifyDao{

    @Override
    public String getNamespace() {
        return MessageNotifyPo.class.getName();
    }
	
}
