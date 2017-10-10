<#import "function.ftl" as func>
<#assign class=model.variables.class>
<#assign classVar=model.variables.classVar>
<#assign sys=model.variables.sys>
<#assign module=model.variables.module>
<#assign sub=model.sub>
<#assign foreignKey=func.convertUnderLine(model.foreignKey)>
package com.${sys}.app.admin.${module}.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pai.base.api.model.Page;
import com.pai.base.api.response.BaseResponse;
import com.pai.base.api.response.ResPage;
import com.pai.base.core.constants.ActionMsgCode;
import com.pai.base.core.entity.CommonResult;
import com.pai.app.web.core.framework.util.PageUtil;
import com.pai.app.web.core.framework.web.controller.AdminRpcController;
import com.pai.app.web.core.framework.web.entity.QueryBuilder;
import com.pai.base.core.util.string.StringUtils;
import com.pai.base.db.mybatis.impl.domain.PageList;
import com.${sys}.biz.${module}.api.service.${class}Service;
import com.${sys}.biz.${module}.api.model.${class}Bean;

/**
 * 对象功能:${model.tabComment} 控制类
 <#if vars.company?exists>
 * 开发公司:${vars.company}
 </#if>
 <#if vars.developer?exists>
 * 开发人员:${vars.developer}
 </#if>
 * 创建时间:${date?string("yyyy-MM-dd HH:mm:ss")}
 */
@Controller
@RequestMapping("/admin/${sys}/${module}/${classVar}")
public class ${class}Controller extends AdminRpcController<${class}Bean>{
	 
	@Resource
	private ${class}Service ${classVar}Service;
	
	@Override
	protected String getPoEntityComment() {		
		return "${model.tabComment}";
	}

	/**
	 * 查询【${model.tabComment}】列表
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
		//查询${model.tabComment}列表
		BaseResponse<ResPage<${class}Bean>> baseResponse = ${classVar}Service.list${class}Service(queryBuilder.buildMap(), page.getPageNo(), page.getPageSize());
		//构造返回数据
		String listData = buildListData(baseResponse.getData().getDataList(),baseResponse.getData().getTotal());
		
		return listData;
	}
	
	/**
	 * 进入【${model.tabComment}】编辑页面
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
		
		${class}Bean ${classVar}Bean = new ${class}Bean();
		//根据新增或更新，进行若干业务处理
		if(!isNew){
			BaseResponse<${class}Bean> baseResponse = ${classVar}Service.get${class}ServiceById(id);
			${classVar}Bean = baseResponse.getData();
		}
		
		//构造返回对象和视图
		ModelAndView modelAndView = buildAutoView(request);
		modelAndView.addObject("isNew",isNew);
		modelAndView.addObject(poEntityName, ${classVar}Bean);
		
		//返回
		return modelAndView;		
	}	
	
	/**
	 * 保存【${model.tabComment}】
	 * @param request
	 * @param response
	 * @param ${classVar}Bean
	 * @throws Exception 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */	
	@RequestMapping("save")
	@ResponseBody
	public CommonResult save(HttpServletRequest request,HttpServletResponse response,${class}Bean ${classVar}Bean) throws Exception{
		//是否新增
		boolean isNew = StringUtils.isEmpty(${classVar}Bean.getId())?true:false;
		
		BaseResponse baseResponse = ${classVar}Service.save${class}(${classVar}Bean);
		
		//返回
		return new CommonResult(baseResponse.isSuccess(), isNew ? ActionMsgCode.CREATE.name() : ActionMsgCode.UPDATE.name(), baseResponse.getMsg());
	}		
	
	/**
	 * 删除【${model.tabComment}】
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
		
		BaseResponse baseResponse = ${classVar}Service.delete${class}ById(id);
		
		//返回
		return new CommonResult(baseResponse.isSuccess(), ActionMsgCode.DELETE.name(), baseResponse.getMsg());
	}

}
