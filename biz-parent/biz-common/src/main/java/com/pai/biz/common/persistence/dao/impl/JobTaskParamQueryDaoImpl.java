package com.pai.biz.common.persistence.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisQueryDaoImpl;
import com.pai.biz.common.persistence.dao.JobTaskParamQueryDao;
import com.pai.biz.common.persistence.entity.JobTaskParamPo;

/**
 * 对象功能:任务调度参数表，同一个定时器不同时间段跑不同的任务就需要传参 QueryDao接口的实现
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2017-02-10 14:05:12
 */
@SuppressWarnings("serial")
@Repository
public class JobTaskParamQueryDaoImpl extends MyBatisQueryDaoImpl<String, JobTaskParamPo> implements JobTaskParamQueryDao{

    @Override
    public String getNamespace() {
        return JobTaskParamPo.class.getName();
    }

	@Override
	public List<JobTaskParamPo> findParamByJobTaskId(String jobTaskId) {
		return super.findByKey("findParamByJobTaskId", b().a("jobId", jobTaskId).p());
	}
	
}
