package com.pai.service.quartz.constants;

public class JobConstants {
	
	public static enum JobTaskStatusEnum{
		RUN(1),
		STOP(2);
		private JobTaskStatusEnum(Integer status){
			this.status = status;
		}
		private Integer status;
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
	}
	
	public static enum JobTaskTypeEnum{
		ONE_TIME, //只执行一次
		EXPRESSION; //按表达式执行
	}
	
	public final static class DATA_TYPE{
		public final static String INT="int";
		public final static String LONG="long";
		public final static String DOUBLE="double";
		public final static String DATE="date";
		public final static String STRING="string";
	}
	
	public final static class LOG_STATUS{
		public final static Integer SUCCESS=1;
		public final static Integer FAILURE=2;
	}
	
	public final static class EXECUTE{
		public final static String METHOD_ID="methodId";
		public final static String TASK_NAME="taskName";
		public final static String METHOD_NAME="methodName";
		public final static String CLASS_NAME="className";
		public final static String PARAMS="params";
		public final static String PARAM_TYPES="paramTypes";
	}
}
