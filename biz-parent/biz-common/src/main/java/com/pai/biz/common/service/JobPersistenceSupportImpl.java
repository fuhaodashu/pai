package com.pai.biz.common.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pai.base.api.service.IdGenerator;
import com.pai.biz.common.persistence.dao.JobTaskLogDao;
import com.pai.biz.common.persistence.dao.JobTaskParamQueryDao;
import com.pai.biz.common.persistence.dao.JobTaskQueryDao;
import com.pai.biz.common.persistence.entity.JobTaskLogPo;
import com.pai.biz.common.persistence.entity.JobTaskParamPo;
import com.pai.biz.common.persistence.entity.JobTaskPo;
import com.pai.service.quartz.JobPersistenceSupport;
import com.pai.service.quartz.constants.JobConstants;
import com.pai.service.quartz.entity.IJobTaskParamPo;
import com.pai.service.quartz.entity.IJobTaskPo;
/**
 * 这个类主要用来实现service底层的接口
 * <pre> 
 * 构建组：biz-common
 * 作者：fuhao
 * 日期：2017年2月10日-下午2:00:11
 * </pre>
 */
@Service
public class JobPersistenceSupportImpl implements JobPersistenceSupport{
	
	@Resource
	JobTaskQueryDao jobTaskQueryDao;
	
	@Resource
	JobTaskParamQueryDao jobTaskParamQueryDao;
	
	@Resource
	JobTaskLogDao jobTaskLogDao;
	
	@Resource
	IdGenerator idGenerator;
	
	@Override
	public List<IJobTaskPo> findActivedJobTaskPos(String groupName) {
		String whereSql = " group_name='" + groupName + "' and status=" + JobConstants.JobTaskStatusEnum.RUN.getStatus();		
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("whereSql", whereSql);
		List<JobTaskPo> jobTaskPos = jobTaskQueryDao.findAll(params);
		List<IJobTaskPo> iJobTaskPos = new ArrayList<IJobTaskPo>();
		for (JobTaskPo jobDefPo:jobTaskPos) {
			iJobTaskPos.add(jobDefPo);
		}
		return iJobTaskPos;
	}

	@Override
	public IJobTaskPo getJobTaskPo(String jobTaskId) {
		JobTaskPo jobTaskPo = jobTaskQueryDao.get(jobTaskId);
		return jobTaskPo;
	}

	@Override
	public List<IJobTaskParamPo> findTaskParams(String jobTaskId) {
		List<JobTaskParamPo> jobParamPos= jobTaskParamQueryDao.findParamByJobTaskId(jobTaskId);
		List<IJobTaskParamPo> iJobParamPos = new ArrayList<IJobTaskParamPo>();
		for(JobTaskParamPo jobParamPo:jobParamPos){
			iJobParamPos.add(jobParamPo);
		}
		return iJobParamPos;
	}

	@Override
	public void saveJobTaskLog(String key, Integer status, String log) {
		JobTaskLogPo jobTaskLogPo = new JobTaskLogPo();
		jobTaskLogPo.setId(idGenerator.genSid());
		jobTaskLogPo.setJobKey(key);
		jobTaskLogPo.setStatus(status);
		jobTaskLogPo.setJobLog(log);
		jobTaskLogPo.setCreateTime(new Date());
		jobTaskLogDao.create(jobTaskLogPo);
	}

}
