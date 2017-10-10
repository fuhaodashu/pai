package com.pai.biz.common.repository.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.pai.base.core.helper.SpringHelper;
import com.pai.base.db.persistence.dao.IQueryDao;
import com.pai.biz.frame.repository.AbstractRepository;
import com.pai.biz.common.domain.JobTask;
import com.pai.biz.common.repository.JobTaskRepository;
import com.pai.biz.common.persistence.dao.JobTaskQueryDao;
import com.pai.biz.common.persistence.entity.JobTaskPo;

/**
 * 对象功能:任务调度 Repository接口的实现类
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2017-02-10 10:18:12
 */
@Repository
public class JobTaskRepositoryImpl extends AbstractRepository<String, JobTaskPo,JobTask> implements JobTaskRepository{
	  
	@Resource
	private  JobTaskQueryDao jobTaskQueryDao;

	public JobTask newInstance() {
		JobTaskPo po = new JobTaskPo();
		JobTask jobTask = SpringHelper.getBean(JobTask.class);
		jobTask.setData(po);
		return jobTask;
	}

	public JobTask newInstance(JobTaskPo po) {
		JobTask jobTask = SpringHelper.getBean(JobTask.class);
		jobTask.setData(po);
		return jobTask;
	} 
	
	@Override
	protected IQueryDao<String, JobTaskPo> getQueryDao() {
		return jobTaskQueryDao;
	}
	
}
