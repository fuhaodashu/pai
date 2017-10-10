package com.pai.biz.common.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisDaoImpl;
import com.pai.biz.common.persistence.dao.JobTaskDao;
import com.pai.biz.common.persistence.entity.JobTaskPo;

/**
 * 对象功能:任务调度 Dao接口的实现类
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2017-02-10 10:18:12
 */
@Repository
public class JobTaskDaoImpl extends MyBatisDaoImpl<String, JobTaskPo> implements JobTaskDao{

    @Override
    public String getNamespace() {
        return JobTaskPo.class.getName();
    }
	
}
