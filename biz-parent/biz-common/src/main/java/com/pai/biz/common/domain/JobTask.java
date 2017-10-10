package com.pai.biz.common.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pai.biz.frame.domain.AbstractDomain;
import com.pai.base.core.helper.SpringHelper;
import com.pai.biz.common.persistence.dao.JobTaskDao;
import com.pai.biz.common.persistence.entity.JobTaskPo;

/**
 * 对象功能:任务调度 领域对象实体
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2017-02-10 10:18:12
 * 命名规范：插入以saveXx前缀，修改updateXx前缀，删除deleteXx前缀
 */
@SuppressWarnings("serial")
@Service
@Scope("prototype")
public class JobTask extends AbstractDomain<String, JobTaskPo>{
	 
	 private JobTaskDao jobTaskDao = null;

	protected void init(){
		jobTaskDao = SpringHelper.getBean(JobTaskDao.class);
		setDao(jobTaskDao);
	}	 
	 
}
