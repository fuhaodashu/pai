package com.pai.biz.common.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pai.biz.frame.domain.AbstractDomain;
import com.pai.base.core.helper.SpringHelper;
import com.pai.biz.common.persistence.dao.JobTaskParamDao;
import com.pai.biz.common.persistence.entity.JobTaskParamPo;

/**
 * 对象功能:任务调度参数表，同一个定时器不同时间段跑不同的任务就需要传参 领域对象实体
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2017-02-10 14:05:12
 * 命名规范：插入以saveXx前缀，修改updateXx前缀，删除deleteXx前缀
 */
@SuppressWarnings("serial")
@Service
@Scope("prototype")
public class JobTaskParam extends AbstractDomain<String, JobTaskParamPo>{
	 
	 private JobTaskParamDao jobTaskParamDao = null;

	protected void init(){
		jobTaskParamDao = SpringHelper.getBean(JobTaskParamDao.class);
		setDao(jobTaskParamDao);
	}	 
	 
}
