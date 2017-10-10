package com.pai.service.quartz;

/**
 * 
 * <pre> 
 * 描述：供应用启动时，根据持久化的定时任务进行任务启动使用。
 * 构建组：service-quartz
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 * </pre>
 */
public interface SchedulerStartupRegister {
	/**
	 * 将该应用下面的所有任务加到容器里，根据状态进行处理
	 * @param group 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void registerJobs(String group);
}
