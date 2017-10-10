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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pai.base.api.model.Page;
import com.pai.biz.frame.repository.IRepository;
import com.pai.base.core.constants.ActionMsgCode;
import com.pai.base.core.entity.CommonResult;
import com.pai.app.web.core.framework.util.PageUtil;
import com.pai.app.web.core.framework.web.controller.AdminController;
import com.pai.app.web.core.framework.web.entity.QueryBuilder;
import com.pai.base.core.util.RequestUtil;
import com.pai.base.core.util.string.StringUtils;
import com.pai.base.db.mybatis.impl.domain.PageList;
import com.${sys}.biz.${module}.domain.${class};
import com.${sys}.biz.${module}.repository.${class}Repository;
import com.${sys}.biz.${module}.persistence.entity.${class}Po;

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
public class ${class}Controller extends AdminController<String, ${class}Po, ${class}>{
	 
	@Resource
	private ${class}Repository ${classVar}Repository;
	
	@Override
	protected IRepository<String, ${class}Po, ${class}> getRepository() {
		return ${classVar}Repository;
	}

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
		PageList<${class}Po> ${classVar}PoList = (PageList<${class}Po>) getRepository().findPaged(queryBuilder.buildMap(),page);
		//构造返回数据
		String listData = buildListData(${classVar}PoList,${classVar}PoList.getPageResult().getTotalCount());
		
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
=======
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pai.base.api.model.Page;
import com.pai.biz.frame.repository.IRepository;
import com.pai.base.core.constants.ActionMsgCode;
import com.pai.base.core.entity.CommonResult;
import com.pai.app.web.core.framework.util.PageUtil;
import com.pai.app.web.core.framework.web.controller.AdminController;
import com.pai.app.web.core.framework.web.entity.QueryBuilder;
import com.pai.base.core.util.string.StringUtils;
import com.pai.service.image.utils.RequestUtil;
import com.pai.base.db.mybatis.impl.domain.PageList;
import com.${sys}.biz.${module}.domain.${class};
import com.${sys}.biz.${module}.repository.${class}Repository;
import com.${sys}.biz.${module}.persistence.entity.${class}Po;

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
public class ${class}Controller extends AdminController<String, ${class}Po, ${class}>{
	 
	@Resource
	private ${class}Repository ${classVar}Repository;
	
	@Override
	protected IRepository<String, ${class}Po, ${class}> getRepository() {
		return ${classVar}Repository;
	}

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
		PageList<${class}Po> ${classVar}PoList = (PageList<${class}Po>) getRepository().findPaged(queryBuilder.buildMap(),page);
		//构造返回数据
		String listData = buildListData(${classVar}PoList,${classVar}PoList.getPageResult().getTotalCount());
		
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
	public ModelAndView edit(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取主键
		String id = RequestUtil.getParameterNullSafe(request, "id");
		
>>>>>>> branch 'master' of https://github.com/fuhaodashu/pai.git
=======
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pai.base.api.model.Page;
import com.pai.biz.frame.repository.IRepository;
import com.pai.base.core.constants.ActionMsgCode;
import com.pai.base.core.entity.CommonResult;
import com.pai.app.web.core.framework.util.PageUtil;
import com.pai.app.web.core.framework.web.controller.AdminController;
import com.pai.app.web.core.framework.web.entity.QueryBuilder;
import com.pai.base.core.util.string.StringUtils;
import com.pai.service.image.utils.RequestUtil;
import com.pai.base.db.mybatis.impl.domain.PageList;
import com.${sys}.biz.${module}.domain.${class};
import com.${sys}.biz.${module}.repository.${class}Repository;
import com.${sys}.biz.${module}.persistence.entity.${class}Po;

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
public class ${class}Controller extends AdminController<String, ${class}Po, ${class}>{
	 
	@Resource
	private ${class}Repository ${classVar}Repository;
	
	@Override
	protected IRepository<String, ${class}Po, ${class}> getRepository() {
		return ${classVar}Repository;
	}

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
		PageList<${class}Po> ${classVar}PoList = (PageList<${class}Po>) getRepository().findPaged(queryBuilder.buildMap(),page);
		//构造返回数据
		String listData = buildListData(${classVar}PoList,${classVar}PoList.getPageResult().getTotalCount());
		
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
	public ModelAndView edit(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取主键
		String id = RequestUtil.getParameterNullSafe(request, "id");
		
>>>>>>> branch 'master' of https://github.com/fuhaodashu/pai.git
=======
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pai.base.api.model.Page;
import com.pai.biz.frame.repository.IRepository;
import com.pai.base.core.constants.ActionMsgCode;
import com.pai.base.core.entity.CommonResult;
import com.pai.app.web.core.framework.util.PageUtil;
import com.pai.app.web.core.framework.web.controller.AdminController;
import com.pai.app.web.core.framework.web.entity.QueryBuilder;
import com.pai.base.core.util.string.StringUtils;
import com.pai.service.image.utils.RequestUtil;
import com.pai.base.db.mybatis.impl.domain.PageList;
import com.${sys}.biz.${module}.domain.${class};
import com.${sys}.biz.${module}.repository.${class}Repository;
import com.${sys}.biz.${module}.persistence.entity.${class}Po;

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
public class ${class}Controller extends AdminController<String, ${class}Po, ${class}>{
	 
	@Resource
	private ${class}Repository ${classVar}Repository;
	
	@Override
	protected IRepository<String, ${class}Po, ${class}> getRepository() {
		return ${classVar}Repository;
	}

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
		PageList<${class}Po> ${classVar}PoList = (PageList<${class}Po>) getRepository().findPaged(queryBuilder.buildMap(),page);
		//构造返回数据
		String listData = buildListData(${classVar}PoList,${classVar}PoList.getPageResult().getTotalCount());
		
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
	public ModelAndView edit(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取主键
		String id = RequestUtil.getParameterNullSafe(request, "id");
		
>>>>>>> branch 'master' of https://github.com/fuhaodashu/pai.git
=======
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pai.base.api.model.Page;
import com.pai.biz.frame.repository.IRepository;
import com.pai.base.core.constants.ActionMsgCode;
import com.pai.base.core.entity.CommonResult;
import com.pai.app.web.core.framework.util.PageUtil;
import com.pai.app.web.core.framework.web.controller.AdminController;
import com.pai.app.web.core.framework.web.entity.QueryBuilder;
import com.pai.base.core.util.string.StringUtils;
import com.pai.service.image.utils.RequestUtil;
import com.pai.base.db.mybatis.impl.domain.PageList;
import com.${sys}.biz.${module}.domain.${class};
import com.${sys}.biz.${module}.repository.${class}Repository;
import com.${sys}.biz.${module}.persistence.entity.${class}Po;

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
public class ${class}Controller extends AdminController<String, ${class}Po, ${class}>{
	 
	@Resource
	private ${class}Repository ${classVar}Repository;
	
	@Override
	protected IRepository<String, ${class}Po, ${class}> getRepository() {
		return ${classVar}Repository;
	}

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
		PageList<${class}Po> ${classVar}PoList = (PageList<${class}Po>) getRepository().findPaged(queryBuilder.buildMap(),page);
		//构造返回数据
		String listData = buildListData(${classVar}PoList,${classVar}PoList.getPageResult().getTotalCount());
		
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
	public ModelAndView edit(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取主键
		String id = RequestUtil.getParameterNullSafe(request, "id");
		
>>>>>>> branch 'master' of https://github.com/fuhaodashu/pai.git
		//装载领域对象
		//是否新增
		boolean isNew =StringUtils.isEmpty(id)?true:false; 
		${class} ${classVar} = null;
		if(isNew){
			${classVar} = ${classVar}Repository.newInstance();
		}else{
			${classVar} = ${classVar}Repository.load(id);			
		}		
		
		//根据新增或更新，进行若干业务处理
		if(isNew){
			//TODO
		}		
		
		//构造返回对象和视图
		ModelAndView modelAndView = buildAutoView(request);
		modelAndView.addObject("isNew",isNew);
		modelAndView.addObject(poEntityName, ${classVar}.getData());
		
		//返回
		return modelAndView;		
	}	
	
	/**
	 * 保存【${model.tabComment}】
	 * @param request
	 * @param response
	 * @param ${classVar}Po
	 * @throws Exception 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */	
	@RequestMapping("save")
	@ResponseBody
	public CommonResult save(HttpServletRequest request,HttpServletResponse response,${class}Po ${classVar}Po) throws Exception{
		//是否新增
		boolean isNew = StringUtils.isEmpty(${classVar}Po.getId())?true:false;
		
		//构造领域对象和保存数据
		${class} ${classVar} = ${classVar}Repository.newInstance();
		${classVar}.setData(${classVar}Po);
		${classVar}.save();
		
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
	public CommonResult delete(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获得待删除的id
		String id = RequestUtil.getParameterNullSafe(request, "id");
		
		//构造领域对象和进行删除操作
		${class} ${classVar} = ${classVar}Repository.newInstance();				
		${classVar}.destroy(id);
		
		//构造返回数据
		CommonResult result = new CommonResult();
		result.setSuccess(true);
		result.setMsgCode(ActionMsgCode.DELETE.name());		
		
		//返回
		return result;
	}

}
