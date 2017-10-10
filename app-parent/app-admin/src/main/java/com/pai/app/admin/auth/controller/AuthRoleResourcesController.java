package com.pai.app.admin.auth.controller;

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
import com.pai.biz.auth.domain.AuthRoleResources;
import com.pai.biz.auth.persistence.entity.AuthRoleResourcesPo;
import com.pai.biz.auth.repository.AuthRoleRepository;
import com.pai.biz.auth.repository.AuthRoleResourcesRepository;
import com.pai.biz.frame.repository.IRepository;

/**
 * 对象功能:角色-授权 控制类
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-12-28 11:56:47
 */
@Controller
@RequestMapping("/admin/pai/auth/authRoleResources")
public class AuthRoleResourcesController extends AdminController<String, AuthRoleResourcesPo, AuthRoleResources>{
	 
	@Resource
	private AuthRoleResourcesRepository authRoleResourcesRepository;
	
	@Resource
	private AuthRoleRepository authRoleRepository;
	
	@Override
	protected IRepository<String, AuthRoleResourcesPo, AuthRoleResources> getRepository() {
		return authRoleResourcesRepository;
	}

	@Override
	protected String getPoEntityComment() {		
		return "角色-授权";
	}

	/**
	 * 查询【角色-授权】列表
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
		//查询角色-授权列表
		PageList<AuthRoleResourcesPo> authRoleResourcesPoList = (PageList<AuthRoleResourcesPo>) getRepository().findPaged(queryBuilder.buildMap(),page);
		//构造返回数据
		String listData = buildListData(authRoleResourcesPoList,authRoleResourcesPoList.getPageResult().getTotalCount());
		
		return listData;
	}
	
	/**
	 * 根绝资源id查找角色
	 * @param request
	 * @param response
	 * @param resourceId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findRoleByResourceId")	
	@ResponseBody
	public String findRoleByResourceId(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//构造分页对象
		QueryBuilder queryBuilder = new QueryBuilder(request);
		//构造分页对象
		Page page = PageUtil.buildPage(request);
		//查询角色列表 
		PageList<AuthRoleResourcesPo> authRolePoList = (PageList<AuthRoleResourcesPo>)authRoleResourcesRepository.findRoleByResourceId(queryBuilder.buildMap(), page);
		//构造返回数据
		String listData = buildListData(authRolePoList,authRolePoList.getPageResult().getTotalCount());
		
		return listData;
	}
	/**
	 * 进入【角色-授权】编辑页面
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
		
		//装载领域对象
		//是否新增
		boolean isNew =StringUtils.isEmpty(id)?true:false; 
		AuthRoleResources authRoleResources = null;
		if(isNew){
			authRoleResources = authRoleResourcesRepository.newInstance();
		}else{
			authRoleResources = authRoleResourcesRepository.load(id);			
		}		
		
		//根据新增或更新，进行若干业务处理
		if(isNew){
			//TODO
		}		
		
		//构造返回对象和视图
		ModelAndView modelAndView = buildAutoView(request);
		modelAndView.addObject("isNew",isNew);
		modelAndView.addObject(poEntityName, authRoleResources.getData());
		
		//返回
		return modelAndView;		
	}	
	
	/**
	 * 保存【角色-授权】
	 * @param request
	 * @param response
	 * @param authRoleResourcesPo
	 * @throws Exception 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */	
	@RequestMapping("save")
	@ResponseBody
	public CommonResult save(HttpServletRequest request,HttpServletResponse response,AuthRoleResourcesPo authRoleResourcesPo) throws Exception{
		//是否新增
		boolean isNew = StringUtils.isEmpty(authRoleResourcesPo.getId())?true:false;
		
		//构造领域对象和保存数据
		AuthRoleResources authRoleResources = authRoleResourcesRepository.newInstance();
		authRoleResources.setData(authRoleResourcesPo);
		authRoleResources.save();
		
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
	 * 删除【角色-授权】
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
		AuthRoleResources authRoleResources = authRoleResourcesRepository.newInstance();				
		authRoleResources.destroy(id);
		
		//构造返回数据
		CommonResult result = new CommonResult();
		result.setSuccess(true);
		result.setMsgCode(ActionMsgCode.DELETE.name());		
		
		//返回
		return result;
	}

}
