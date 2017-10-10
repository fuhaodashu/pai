package com.pai.biz.common.repository.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.pai.base.core.helper.SpringHelper;
import com.pai.base.db.persistence.dao.IQueryDao;
import com.pai.biz.frame.repository.AbstractRepository;
import com.pai.biz.common.domain.JobTaskParam;
import com.pai.biz.common.repository.JobTaskParamRepository;
import com.pai.biz.common.persistence.dao.JobTaskParamQueryDao;
import com.pai.biz.common.persistence.entity.JobTaskParamPo;

/**
 * 对象功能:任务调度参数表，同一个定时器不同时间段跑不同的任务就需要传参 Repository接口的实现类
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2017-02-10 14:05:12
 */
@Repository
public class JobTaskParamRepositoryImpl extends AbstractRepository<String, JobTaskParamPo,JobTaskParam> implements JobTaskParamRepository{
	  
	@Resource
	private  JobTaskParamQueryDao jobTaskParamQueryDao;

	public JobTaskParam newInstance() {
		JobTaskParamPo po = new JobTaskParamPo();
		JobTaskParam jobTaskParam = SpringHelper.getBean(JobTaskParam.class);
		jobTaskParam.setData(po);
		return jobTaskParam;
	}

	public JobTaskParam newInstance(JobTaskParamPo po) {
		JobTaskParam jobTaskParam = SpringHelper.getBean(JobTaskParam.class);
		jobTaskParam.setData(po);
		return jobTaskParam;
	} 
	
	@Override
	protected IQueryDao<String, JobTaskParamPo> getQueryDao() {
		return jobTaskParamQueryDao;
	}
	
}
