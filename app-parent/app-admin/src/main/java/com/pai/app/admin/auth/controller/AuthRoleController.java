package com.pai.app.admin.auth.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pai.app.web.core.framework.util.PageUtil;
import com.pai.app.web.core.framework.web.controller.AdminController;
import com.pai.app.web.core.framework.web.entity.Condition;
import com.pai.app.web.core.framework.web.entity.QueryBuilder;
import com.pai.base.api.model.Page;
import com.pai.base.core.constants.ActionMsgCode;
import com.pai.base.core.entity.CommonResult;
import com.pai.base.core.util.JsonUtil;
import com.pai.base.core.util.RequestUtil;
import com.pai.base.core.util.string.StringUtils;
import com.pai.biz.auth.domain.AuthRole;
import com.pai.biz.auth.persistence.entity.AuthRolePo;
import com.pai.biz.auth.repository.AuthResourcesRepository;
import com.pai.biz.auth.repository.AuthRoleRepository;
import com.pai.biz.frame.repository.IRepository;

/**
 * 对象功能:角色 控制类
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2017-01-02 16:56:47
 */
@Controller
@RequestMapping("/admin/pai/auth/authRole")
public class AuthRoleController extends AdminController<String, AuthRolePo, AuthRole>{
	 
	@Resource
	private AuthRoleRepository authRoleRepository;
	
	@Resource
	private AuthResourcesRepository authResourcesRepository;
	
	@Override
	protected IRepository<String, AuthRolePo, AuthRole> getRepository() {
		return authRoleRepository;
	}

	@Override
	protected String getPoEntityComment() {		
		return "角色";
	}

	/**
	 * 查询【角色】列表
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
	public String listData(HttpServletRequest request,HttpServletResponse response,String condition) throws Exception{
		//构造分页对象
		QueryBuilder queryBuilder = new QueryBuilder(request);
		Page page = PageUtil.buildPage(request);
		//用户分配所属角色时，弹框ligerPopupEdit构造的查询条件为一个json对象
		if(StringUtils.isNotEmpty(condition)){
			List<Condition> conditions = JsonUtil.getDTOList(condition,	Condition.class);
			for (Condition c : conditions) {
				if (c.getField().equals("name")) {
					queryBuilder.putParamValue("name", c.getValue(),queryBuilder.TYPE_STRING,queryBuilder.COMPARE_LIKE);
				}
			}
		}
		//查询角色列表
		List<AuthRolePo> authRolePoList = getRepository().findPaged(queryBuilder.buildMap(),page);
		//查询总数
		Integer totalRecords = getRepository().count(queryBuilder.buildWhereSqlMap());
		//构造返回数据
		String listData = buildListData(authRolePoList,totalRecords);
		
		return listData;
	}
	
	/**
	 * 进入【角色】编辑页面
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
		AuthRole authRole = null;
		
		if(isNew){
			authRole = authRoleRepository.newInstance();
		}else{
			authRole = authRoleRepository.load(id);		
		}		
		
		//根据新增或更新，进行若干业务处理
		if(isNew){
			//TODO
		}		
		
		//构造返回对象和视图
		ModelAndView modelAndView = buildAutoView(request);
		modelAndView.addObject("isNew",isNew);
		modelAndView.addObject(poEntityName, authRole.getData());
		
		//返回
		return modelAndView;		
	}	
	
	/**
	 * 保存【角色】
	 * @param request
	 * @param response
	 * @param authRolePo
	 * @throws Exception 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */	
	@RequestMapping("save")
	@ResponseBody
	public CommonResult save(HttpServletRequest request,HttpServletResponse response,AuthRolePo authRolePo, String resourcesId) throws Exception{
		//是否新增
		boolean isNew = StringUtils.isEmpty(authRolePo.getId())?true:false;
		
		//构造领域对象和保存数据
		AuthRole authRole = authRoleRepository.newInstance();
		authRole.save(authRolePo, resourcesId);
		
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
	 * 删除【角色】
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
		AuthRole authRole = authRoleRepository.newInstance();				
		authRole.destroy(id);
		
		//构造返回数据
		CommonResult result = new CommonResult();
		result.setSuccess(true);
		result.setMsgCode(ActionMsgCode.DELETE.name());		
		
		//返回
		return result;
	}

}
