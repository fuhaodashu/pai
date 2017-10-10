package com.pai.biz.common.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisQueryDaoImpl;
import com.pai.biz.common.persistence.dao.JobTaskLogQueryDao;
import com.pai.biz.common.persistence.entity.JobTaskLogPo;

/**
 * 对象功能:任务调度日志 QueryDao接口的实现
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2017-02-10 14:05:35
 */
@SuppressWarnings("serial")
@Repository
public class JobTaskLogQueryDaoImpl extends MyBatisQueryDaoImpl<String, JobTaskLogPo> implements JobTaskLogQueryDao{

    @Override
    public String getNamespace() {
        return JobTaskLogPo.class.getName();
    }
	
}
