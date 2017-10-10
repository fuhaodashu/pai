package com.pai.app.admin.auth.controller;

import java.util.List;
import java.util.Map;

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
import com.pai.base.db.session.OnlineUserIdHolder;
import com.pai.biz.auth.domain.AuthUser;
import com.pai.biz.auth.persistence.entity.AuthRolePo;
import com.pai.biz.auth.persistence.entity.AuthUserPo;
import com.pai.biz.auth.repository.AuthRoleRepository;
import com.pai.biz.auth.repository.AuthUserRepository;
import com.pai.biz.frame.repository.IRepository;

/**
 * 对象功能:用户 控制类
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 */
@Controller
@RequestMapping("/admin/pai/auth/authUser")
public class AuthUserController extends AdminController<String, AuthUserPo, AuthUser>{
	 
	@Resource
	private AuthUserRepository authUserRepository;
	
	@Resource
	private AuthRoleRepository authRoleRepository;
	
	@Override
	protected IRepository<String, AuthUserPo, AuthUser> getRepository() {
		return authUserRepository;
	}
	
	@Override
	protected String getPoEntityComment() {		
		return "用户";
	}

	/**
	 * 查询【用户】列表
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
	public String listData(HttpServletRequest request,HttpServletResponse response,String roleId) throws Exception{
		//构造分页对象
		QueryBuilder queryBuilder = new QueryBuilder(request);
		Page page = PageUtil.buildPage(request);
		Map<String, Object> map = queryBuilder.buildMap();
		if(StringUtils.isNotEmpty(roleId)){
			map.put("roleId", roleId);
		}
		//查询用户列表
		List<AuthUserPo> authUserPoList = getRepository().findPaged(map,page);
		for(AuthUserPo authUserPo : authUserPoList){
			setRoleUser(authUserPo);
		}
		//查询总数
		Integer totalRecords = getRepository().count(queryBuilder.buildWhereSqlMap());
		//构造返回数据
		String listData = buildListData(authUserPoList,totalRecords);
		
		return listData;
	}

	/**
	 * 进入【用户】编辑页面
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
		AuthUser authUser = null;
		
		if(isNew){
			authUser = authUserRepository.newInstance();
		}else{
			authUser = authUserRepository.load(id);		
			AuthUserPo authUserPo = authUser.getData();
			setRoleUser(authUserPo);
		}		
		
		//根据新增或更新，进行若干业务处理
		if(isNew){
			//TODO
		}		
		
		//构造返回对象和视图
		ModelAndView modelAndView = buildAutoView(request);
		modelAndView.addObject("isNew",isNew);
		modelAndView.addObject(poEntityName, authUser.getData());
		
		//返回
		return modelAndView;		
	}	
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * ModelAndView
	 * @exception 
	 * @since  1.0.0
	 */	
	@RequestMapping("editPassword")
	public ModelAndView editPassword(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取主键
		String id = OnlineUserIdHolder.getUserId();
		//是否新增
		boolean isNew =StringUtils.isEmpty(id)?true:false; 
		AuthUser authUser = null;
		if(isNew){
			authUser = authUserRepository.newInstance();
		}else{
			authUser = authUserRepository.load(id);			
		}		
		
		//构造返回对象和视图
		ModelAndView modelAndView = buildAutoView(request);
		modelAndView.addObject("isNew",isNew);
		modelAndView.addObject(poEntityName, authUser.getData());
		
		//返回
		return modelAndView;		
	}	
	
	/**
	 * 保存【用户】
	 * @param request
	 * @param response
	 * @param authUserPo
	 * @throws Exception 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */	
	@RequestMapping("save")
	@ResponseBody
	public CommonResult save(HttpServletRequest request,HttpServletResponse response,AuthUserPo authUserPo) throws Exception{
		 //是否新增
		boolean isNew = StringUtils.isEmpty(authUserPo.getId())?true:false;
		
		//构造领域对象和保存数据
		AuthUser authUser = authUserRepository.newInstance();
		authUser.save(authUserPo);
		
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
	 * 删除【用户】
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
		AuthUser authUser = authUserRepository.newInstance();				
		authUser.destroy(id);
		
		//构造返回数据
		CommonResult result = new CommonResult();
		result.setSuccess(true);
		result.setMsgCode(ActionMsgCode.DELETE.name());		
		
		//返回
		return result;
	}

	/**
	 * 保存【用户】
	 * @param request
	 * @param response
	 * @param authUserPo
	 * @throws Exception 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */	
	@RequestMapping("savePassword")
	@ResponseBody
	public CommonResult savePassword(HttpServletRequest request,HttpServletResponse response,AuthUserPo authUserPo) throws Exception{
		//构造返回数据
		CommonResult result = new CommonResult();
		String passwordConfirm = RequestUtil.getParameterNullSafe(request, "passwordConfirm");
		if(!authUserPo.getPassword().equals(passwordConfirm)){
			result.setSuccess(false);
			result.setMsg("两次密码不一致");
			return result;
		}
		//构造领域对象和保存数据
		AuthUser authUser = authUserRepository.newInstance();
		authUser.setData(authUserPo);
		authUser.updatePassword(authUserPo);
		result.setSuccess(true);
		result.setMsgCode(ActionMsgCode.UPDATE.name());
		//返回
		return result;
	}	
	
	private void setRoleUser(AuthUserPo authUserPo) {
		String roleNames = "";
		String roleIds = "";
		List<AuthRolePo> authRolePos = authRoleRepository.findRoleByUserId(authUserPo.getId());
		for(AuthRolePo authRolePo : authRolePos){
			roleNames += ";" + authRolePo.getName();
			roleIds += ";" + authRolePo.getId();
		}
		if(roleIds.length() >= 1){
			roleIds = roleIds.substring(1);
			roleNames = roleNames.substring(1);
		}
		authUserPo.setRoleIds(roleIds);
		authUserPo.setRoleNames(roleNames);
	}

}
