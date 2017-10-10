package com.pai.biz.auth.domain;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pai.base.api.service.IdGenerator;
import com.pai.base.core.helper.SpringHelper;
import com.pai.base.core.util.string.StringUtils;
import com.pai.base.db.session.OnlineUserIdHolder;
import com.pai.biz.auth.persistence.dao.AuthResourcesDao;
import com.pai.biz.auth.persistence.dao.AuthResourcesQueryDao;
import com.pai.biz.auth.persistence.dao.AuthRoleResourcesDao;
import com.pai.biz.auth.persistence.entity.AuthResourcesPo;
import com.pai.biz.frame.domain.AbstractDomain;

/**
 * 对象功能:资源 领域对象实体
 * 开发公司:PAI.COM
 * 开发人员:FUHAO
 * 创建时间:2016-08-07 14:07:40
 */
@SuppressWarnings("serial")
@Service
@Scope("prototype")
public class AuthResources extends AbstractDomain<String, AuthResourcesPo>{
	 
	private AuthResourcesDao authResourcesDao = null;
	
	@Resource
	private AuthResourcesQueryDao authResourcesQueryDao;
	
	@Resource
	private IdGenerator idGenerator;
	
	@Resource
	private AuthRoleResourcesDao authRoleResourcesDao;
	
	protected void init(){
		authResourcesDao = SpringHelper.getBean(AuthResourcesDao.class);
		setDao(authResourcesDao);
	}

	public void save(AuthResourcesPo authResourcesPo) {
		//新增
		if(StringUtils.isEmpty(authResourcesPo.getId())){
			String id = idGenerator.genSid();
			setPath(authResourcesPo, id);
			authResourcesPo.setId(id);
			super.setData(authResourcesPo);
			super.create();	
			//角色-资源，配置超级管理员权限
			authRoleResourcesDao.createAdminResource(idGenerator.genSid(), id, OnlineUserIdHolder.getUserId());
		}else{
			setPath(authResourcesPo, authResourcesPo.getId());
			super.setData(authResourcesPo);
			super.update();
		}
		
		//给管理员session配置权限。因为拦截器中新药拦截的url也放在Session中的，所以对新加的url并不会拦截
		/*HashSet sessionsHashSet = (HashSet) OnlineHolder.getSession().getServletContext().getAttribute("sessions");
		for(Iterator it=sessionsHashSet.iterator();it.hasNext();){
			HttpSession session = (HttpSession) it.next();
			Object obj = OnlineHolder.getSession().getAttribute(Constants.PAI_AUTH_USER);
			if(obj instanceof AuthUserPo){
				AuthUserPo authUserPo = (AuthUserPo)obj;
				if(authUserPo.getName().equals("admin")){
					List<AuthResourcesPo> authResourcesPoList = authResourcesQueryDao.listResourcesByUserId(authUserPo.getId(), null);
					authUserPo.setAuthResourcesPos(authResourcesPoList);
					session.setAttribute(Constants.PAI_AUTH_USER, authUserPo);
					OnlineHolder.setSession(session);
					break;
				}
			}			
		}*/
	}

	private void setPath(AuthResourcesPo authResourcesPo, String id) {
		StringBuilder sb = new StringBuilder();
		//查找父节点的树分支
		if(StringUtils.isNotEmpty(authResourcesPo.getParentId())){
			AuthResourcesPo parentResourcePo = authResourcesQueryDao.get(authResourcesPo.getParentId());
			if(StringUtils.isNotEmpty(parentResourcePo.getPath())){
				sb.append(parentResourcePo.getPath()).append(".").append(id);
				authResourcesPo.setPath(sb.toString());
			}
		}else {
			authResourcesPo.setPath(id);
			authResourcesPo.setDepth(1);
		}
	}

	public void delete(String id) {
		//删除角色-资源关联
		authRoleResourcesDao.deleteByResourceId(id);
		this.destroy(id);
	}	 
	 
}
