package com.pai.biz.common.repository;

import com.pai.biz.frame.repository.IRepository;
import com.pai.biz.common.domain.JobTask;
import com.pai.biz.common.persistence.entity.JobTaskPo;
/**
 * 对象功能:任务调度 Repository接口
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2017-02-10 10:18:12
 * 命名规范：查list集合以findXx做前缀,单个po实体用getXx做前缀,数量countXx,条件查询加ByXx后缀,如getXxByName
 */
public interface JobTaskRepository extends IRepository<String, JobTaskPo,JobTask>{
	  
	 
}
