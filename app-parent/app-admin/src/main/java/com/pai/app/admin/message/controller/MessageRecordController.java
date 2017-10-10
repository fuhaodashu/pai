package com.pai.app.admin.message.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pai.app.web.core.framework.util.PageUtil;
import com.pai.app.web.core.framework.web.controller.AdminRpcController;
import com.pai.app.web.core.framework.web.entity.QueryBuilder;
import com.pai.base.api.model.Page;
import com.pai.base.api.response.BaseResponse;
import com.pai.base.api.response.ResPage;
import com.pai.base.core.constants.ActionMsgCode;
import com.pai.base.core.entity.CommonResult;
import com.pai.base.core.util.string.StringUtils;
import com.pai.biz.message.api.model.MessageRecordBean;
import com.pai.biz.message.api.service.MessageRecordService;

/**
 * 对象功能:pai_message_record 控制类
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-09-10 18:03:42
 */
@Controller
@RequestMapping("/admin/pai/message/messageRecord")
public class MessageRecordController extends AdminRpcController<MessageRecordBean>{
	 
	@Resource
	private MessageRecordService messageRecordService;
	
	@Override
	protected String getPoEntityComment() {		
		return "pai_message_record";
	}

	/**
	 * 查询【pai_message_record】列表
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
		//查询pai_message_record列表
		BaseResponse<ResPage<MessageRecordBean>> baseResponse = messageRecordService.listMessageRecordService(queryBuilder.buildMap(), page.getPageNo(), page.getPageSize());
		//构造返回数据
		String listData = buildListData(baseResponse.getData().getDataList(),baseResponse.getData().getTotal());
		
		return listData;
	}
	
	/**
	 * 进入【pai_message_record】编辑页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 * @exception 
	 * @since  1.0.0
	 */	
	@RequestMapping("edit")
	public ModelAndView edit(HttpServletRequest request,HttpServletResponse response, @RequestParam String id) throws Exception{
		//装载领域对象
		//是否新增
		boolean isNew =StringUtils.isEmpty(id)?true:false; 
		
		MessageRecordBean messageRecordBean = new MessageRecordBean();
		//根据新增或更新，进行若干业务处理
		if(!isNew){
			BaseResponse<MessageRecordBean> baseResponse = messageRecordService.getMessageRecordServiceById(id);
			messageRecordBean = baseResponse.getData();
		}
		
		//构造返回对象和视图
		ModelAndView modelAndView = buildAutoView(request);
		modelAndView.addObject("isNew",isNew);
		modelAndView.addObject(poEntityName, messageRecordBean);
		
		//返回
		return modelAndView;		
	}	
	
	/**
	 * 保存【pai_message_record】
	 * @param request
	 * @param response
	 * @param messageRecordBean
	 * @throws Exception 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */	
	@RequestMapping("save")
	@ResponseBody
	public CommonResult save(HttpServletRequest request,HttpServletResponse response,MessageRecordBean messageRecordBean) throws Exception{
		//是否新增
		boolean isNew = StringUtils.isEmpty(messageRecordBean.getId())?true:false;
		
		BaseResponse baseResponse = messageRecordService.saveMessageRecord(messageRecordBean);
		
		//返回
		return new CommonResult(baseResponse.isSuccess(), isNew ? ActionMsgCode.CREATE.name() : ActionMsgCode.UPDATE.name(), baseResponse.getMsg());
	}		
	
	/**
	 * 删除【pai_message_record】
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
	public CommonResult delete(HttpServletRequest request,HttpServletResponse response, @RequestParam String id) throws Exception{
		
		BaseResponse baseResponse = messageRecordService.deleteMessageRecordById(id);
		
		//返回
		return new CommonResult(baseResponse.isSuccess(), ActionMsgCode.DELETE.name(), baseResponse.getMsg());
	}

}
