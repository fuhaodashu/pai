package com.pai.biz.common.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pai.biz.frame.domain.AbstractDomain;
import com.pai.base.core.helper.SpringHelper;
import com.pai.biz.common.persistence.dao.JobTaskLogDao;
import com.pai.biz.common.persistence.entity.JobTaskLogPo;

/**
 * 对象功能:任务调度日志 领域对象实体
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2017-02-10 14:05:35
 * 命名规范：插入以saveXx前缀，修改updateXx前缀，删除deleteXx前缀
 */
@SuppressWarnings("serial")
@Service
@Scope("prototype")
public class JobTaskLog extends AbstractDomain<String, JobTaskLogPo>{
	 
	 private JobTaskLogDao jobTaskLogDao = null;

	protected void init(){
		jobTaskLogDao = SpringHelper.getBean(JobTaskLogDao.class);
		setDao(jobTaskLogDao);
	}	 
	 
}
