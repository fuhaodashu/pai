package com.pai.service.quartz;

import java.util.List;

import org.quartz.Trigger.TriggerState;

import com.pai.service.quartz.entity.IJobTaskPo;
import com.pai.service.quartz.entity.IJobTaskParamPo;

/**
 * <pre> 
 * 描述：Service-Quartz 对外提供的服务。
 * 构建组：service-quartz
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 * </pre>
 */
public interface SchedulerService {

	public boolean startJob(String jobTaskId);
	
	public boolean startJob(IJobTaskPo jobTaskPo);
	/**
	 * 启动非持久化的任务
	 * @param beanId 任务的Spring Bean Id
	 * @param group 分组值
	 * @param expr Cron表达式
	 * @param jobParamPos 参数列表
	 * @return 
	 * boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public boolean startExprJob(String jobTaskId,String beanId,String groupName,String expression,List<IJobTaskParamPo> iJobTaskParamPos);
	
	/**
	 * 启动任务一次。
	 * @param beanId 任务的Spring Bean Id
	 * @param group 分组值
	 * @param jobParamPos 参数列表
	 * @return 
	 * boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public boolean startOneTime(String jobTaskId,String beanId,String groupName,List<IJobTaskParamPo> iJobTaskParamPos);
	
	/**
	 * 停止计划
	 * @param beanId 任务的Spring Bean Id
	 * @param group 分组值
	 * @return 
	 * boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public boolean shutdownPlan(String jobTaskId,String bean,String group);
		
	public boolean startPlan(String planId,String bean, String group);
	
	/**
	 * 返回计划的执行状态
	 * @param planId 计划的ID
	 * @param group 分组值
	 * @return 
	 * TriggerState
	 * @exception 
	 * @since  1.0.0
	 */
	public TriggerState getTriggerState(String planId,String bean,String group); 
	
	/**
	 * 启动Scheduler（由框架管理，开发人员不需要调用） 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void startScheduler();
	
	/**
	 * 关闭Scheduler（由框架管理，开发人员不需要调用）
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void shutdownScheduler();
}
