package com.pai.biz.common.repository.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.pai.base.core.helper.SpringHelper;
import com.pai.base.db.persistence.dao.IQueryDao;
import com.pai.biz.frame.repository.AbstractRepository;
import com.pai.biz.common.domain.JobTaskLog;
import com.pai.biz.common.repository.JobTaskLogRepository;
import com.pai.biz.common.persistence.dao.JobTaskLogQueryDao;
import com.pai.biz.common.persistence.entity.JobTaskLogPo;

/**
 * 对象功能:任务调度日志 Repository接口的实现类
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2017-02-10 14:05:35
 */
@Repository
public class JobTaskLogRepositoryImpl extends AbstractRepository<String, JobTaskLogPo,JobTaskLog> implements JobTaskLogRepository{
	  
	@Resource
	private  JobTaskLogQueryDao jobTaskLogQueryDao;

	public JobTaskLog newInstance() {
		JobTaskLogPo po = new JobTaskLogPo();
		JobTaskLog jobTaskLog = SpringHelper.getBean(JobTaskLog.class);
		jobTaskLog.setData(po);
		return jobTaskLog;
	}

	public JobTaskLog newInstance(JobTaskLogPo po) {
		JobTaskLog jobTaskLog = SpringHelper.getBean(JobTaskLog.class);
		jobTaskLog.setData(po);
		return jobTaskLog;
	} 
	
	@Override
	protected IQueryDao<String, JobTaskLogPo> getQueryDao() {
		return jobTaskLogQueryDao;
	}
	
}
