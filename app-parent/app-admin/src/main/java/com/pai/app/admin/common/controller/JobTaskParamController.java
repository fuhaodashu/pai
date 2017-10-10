package com.pai.app.admin.common.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pai.app.web.core.framework.util.PageUtil;
import com.pai.app.web.core.framework.web.controller.AdminController;
import com.pai.app.web.core.framework.web.entity.QueryBuilder;
import com.pai.base.api.model.Page;
import com.pai.base.core.constants.ActionMsgCode;
import com.pai.base.core.entity.CommonResult;
import com.pai.base.core.util.RequestUtil;
import com.pai.base.core.util.string.StringUtils;
import com.pai.base.db.mybatis.impl.domain.PageList;
import com.pai.biz.common.domain.JobTaskParam;
import com.pai.biz.common.persistence.entity.JobTaskParamPo;
import com.pai.biz.common.repository.JobTaskParamRepository;
import com.pai.biz.frame.repository.IRepository;

/**
 * 对象功能:任务调度参数表，同一个定时器不同时间段跑不同的任务就需要传参 控制类
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2017-02-13 17:05:06
 */
@Controller
@RequestMapping("/admin/pai/common/jobTaskParam")
public class JobTaskParamController extends AdminController<String, JobTaskParamPo, JobTaskParam>{
	 
	@Resource
	private JobTaskParamRepository jobTaskParamRepository;
	
	@Override
	protected IRepository<String, JobTaskParamPo, JobTaskParam> getRepository() {
		return jobTaskParamRepository;
	}

	@Override
	protected String getPoEntityComment() {		
		return "任务调度参数表，同一个定时器不同时间段跑不同的任务就需要传参";
	}

	/**
	 * 查询【任务调度参数表，同一个定时器不同时间段跑不同的任务就需要传参】列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping("listData")	
	@ResponseBody
	public String listData(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//构造分页对象
		QueryBuilder queryBuilder = new QueryBuilder(request);
		Page page = PageUtil.buildPage(request);
		//查询任务调度参数表，同一个定时器不同时间段跑不同的任务就需要传参列表
		PageList<JobTaskParamPo> jobTaskParamPoList = (PageList<JobTaskParamPo>) getRepository().findPaged(queryBuilder.buildMap(),page);
		//构造返回数据
		String listData = buildListData(jobTaskParamPoList,jobTaskParamPoList.getPageResult().getTotalCount());
		
		return listData;
	}
	
	/**
	 * 进入【任务调度参数表，同一个定时器不同时间段跑不同的任务就需要传参】编辑页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 * @exception 
	 * @since  1.0.0
	 */	
	@RequestMapping("edit")
	public ModelAndView edit(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取主键
		String id = RequestUtil.getParameterNullSafe(request, "id");
		String jobId = RequestUtil.getParameterNullSafe(request, "jobId");
		
		//装载领域对象
		//是否新增
		boolean isNew =StringUtils.isEmpty(id)?true:false; 
		JobTaskParam jobTaskParam = null;
		if(isNew){
			jobTaskParam = jobTaskParamRepository.newInstance();
		}else{
			jobTaskParam = jobTaskParamRepository.load(id);			
		}		
		
		//根据新增或更新，进行若干业务处理
		if(isNew)
			jobTaskParam.getData().setJobId(jobId);
		//构造返回对象和视图
		ModelAndView modelAndView = buildAutoView(request);
		modelAndView.addObject("isNew",isNew);
		modelAndView.addObject(poEntityName, jobTaskParam.getData());
		
		//返回
		return modelAndView;		
	}	
	
	/**
	 * 保存【任务调度参数表，同一个定时器不同时间段跑不同的任务就需要传参】
	 * @param request
	 * @param response
	 * @param jobTaskParamPo
	 * @throws Exception 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */	
	@RequestMapping("save")
	@ResponseBody
	public CommonResult save(HttpServletRequest request,HttpServletResponse response,JobTaskParamPo jobTaskParamPo) throws Exception{
		//是否新增
		boolean isNew = StringUtils.isEmpty(jobTaskParamPo.getId())?true:false;
		
		//构造领域对象和保存数据
		JobTaskParam jobTaskParam = jobTaskParamRepository.newInstance();
		jobTaskParam.setData(jobTaskParamPo);
		jobTaskParam.save();
		
		//构造返回数据
		CommonResult result = new CommonResult();
		result.setSuccess(true);
		if(isNew){
			result.setMsgCode(ActionMsgCode.CREATE.name());	
		}else {
			result.setMsgCode(ActionMsgCode.UPDATE.name());
		}			
		
		//返回
		return result;
	}		
	
	/**
	 * 删除【任务调度参数表，同一个定时器不同时间段跑不同的任务就需要传参】
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception 
	 * CommonResult
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping("delete")
	@ResponseBody
	public CommonResult delete(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获得待删除的id
		String id = RequestUtil.getParameterNullSafe(request, "id");
		
		//构造领域对象和进行删除操作
		JobTaskParam jobTaskParam = jobTaskParamRepository.newInstance();				
		jobTaskParam.destroy(id);
		
		//构造返回数据
		CommonResult result = new CommonResult();
		result.setSuccess(true);
		result.setMsgCode(ActionMsgCode.DELETE.name());		
		
		//返回
		return result;
	}

}
