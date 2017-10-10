package com.pai.biz.common.repository;

import com.pai.biz.frame.repository.IRepository;
import com.pai.biz.common.domain.JobTaskParam;
import com.pai.biz.common.persistence.entity.JobTaskParamPo;
/**
 * 对象功能:任务调度参数表，同一个定时器不同时间段跑不同的任务就需要传参 Repository接口
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2017-02-10 14:05:12
 * 命名规范：查list集合以findXx做前缀,单个po实体用getXx做前缀,数量countXx,条件查询加ByXx后缀,如getXxByName
 */
public interface JobTaskParamRepository extends IRepository<String, JobTaskParamPo,JobTaskParam>{
	  
	 
}
