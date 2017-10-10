package com.pai.service.quartz;

import java.util.List;

import com.pai.service.quartz.entity.IJobTaskPo;
import com.pai.service.quartz.entity.IJobTaskParamPo;

/**
 * <pre> 
 * 描述：本模块需要的持久化支持
 * 构建组：service-quartz
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 * </pre>
 */
public interface JobPersistenceSupport {
	
	/**
	 * 查询所有激活的任务，根据组值。
	 * @param group
	 * @return 
	 * List<IJobDefPo>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<IJobTaskPo> findActivedJobTaskPos(String group);
	
	public IJobTaskPo getJobTaskPo(String jobTaskId);
	
	/**
	 * 查询该任务的默认参数列表
	 * @param jobTaskId
	 * @return 
	 * List<IJobParam>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<IJobTaskParamPo> findTaskParams(String jobTaskId);
	
	/**
	 * 保存执行历史到相应的数据表中
	 * @param jobId
	 * @param status
	 * @param log 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void saveJobTaskLog(String key, Integer status, String log);
}
