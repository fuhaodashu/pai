package com.pai.biz.common.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisDaoImpl;
import com.pai.biz.common.persistence.dao.JobTaskLogDao;
import com.pai.biz.common.persistence.entity.JobTaskLogPo;

/**
 * 对象功能:任务调度日志 Dao接口的实现类
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2017-02-10 14:05:35
 */
@Repository
public class JobTaskLogDaoImpl extends MyBatisDaoImpl<String, JobTaskLogPo> implements JobTaskLogDao{

    @Override
    public String getNamespace() {
        return JobTaskLogPo.class.getName();
    }
	
}
