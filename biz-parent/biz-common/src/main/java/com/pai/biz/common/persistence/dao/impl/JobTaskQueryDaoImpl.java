package com.pai.biz.common.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisQueryDaoImpl;
import com.pai.biz.common.persistence.dao.JobTaskQueryDao;
import com.pai.biz.common.persistence.entity.JobTaskPo;

/**
 * 对象功能:任务调度 QueryDao接口的实现
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2017-02-10 10:18:12
 */
@SuppressWarnings("serial")
@Repository
public class JobTaskQueryDaoImpl extends MyBatisQueryDaoImpl<String, JobTaskPo> implements JobTaskQueryDao{

    @Override
    public String getNamespace() {
        return JobTaskPo.class.getName();
    }
	
}
