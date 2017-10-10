package com.pai.biz.auth.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pai.base.core.helper.SpringHelper;
import com.pai.base.core.util.string.StringCollections;
import com.pai.base.core.util.string.StringUtils;
import com.pai.base.db.session.OnlineUserIdHolder;
import com.pai.biz.auth.persistence.dao.AuthRoleDao;
import com.pai.biz.auth.persistence.entity.AuthRolePo;
import com.pai.biz.auth.persistence.entity.AuthRoleResourcesPo;
import com.pai.biz.auth.repository.AuthRoleResourcesRepository;
import com.pai.biz.frame.domain.AbstractDomain;

/**
 * 对象功能:角色 领域对象实体
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-12-29 09:36:49
 */
@SuppressWarnings("serial")
@Service
@Scope("prototype")
public class AuthRole extends AbstractDomain<String, AuthRolePo>{
	 
	private AuthRoleDao authRoleDao = null;

	@Resource
	private AuthRoleResourcesRepository authRoleResourcesRepository;
	
	protected void init(){
		authRoleDao = SpringHelper.getBean(AuthRoleDao.class);
		setDao(authRoleDao);
	}

	public void save(AuthRolePo authRolePo, String resourcesId) {
		ArrayList<String> resourcesIds = new ArrayList<String>(StringCollections.toList(resourcesId, ";"));
		if(StringUtils.isEmpty(authRolePo.getId())){
			super.setData(authRolePo);
			super.save();
			//角色-资源
			addRoleResources(authRolePo, resourcesIds);
		}else{
			authRoleDao.update(authRolePo);
			//角色-资源
			List<AuthRoleResourcesPo> authRoleResourcesPos = authRoleResourcesRepository.findRoleResourcesByRoleId(authRolePo.getId());
			for(AuthRoleResourcesPo authRoleResourcesPo : authRoleResourcesPos){
				if(resourcesIds.contains(authRoleResourcesPo.getResourceId())){
					resourcesIds.remove(authRoleResourcesPo.getResourceId());
				}else{
					//删除原来多余的角色-资源关联，物理直接删除
					authRoleResourcesRepository.newInstance().destroy(authRoleResourcesPo.getId());
				}
			}
			//新增加的角色-资源
			addRoleResources(authRolePo, resourcesIds);
		}
	}

	private void addRoleResources(AuthRolePo authRolePo, List<String> resourcesIds) {
		for(String resourcesId : resourcesIds){
			AuthRoleResourcesPo authRoleResourcesPo = new AuthRoleResourcesPo();
			authRoleResourcesPo.setResourceId(resourcesId);
			authRoleResourcesPo.setRoleId(authRolePo.getId());
			authRoleResourcesPo.setCreateBy(OnlineUserIdHolder.getUserId());
			authRoleResourcesPo.setCreateTime(new Date());
			authRoleResourcesRepository.newInstance(authRoleResourcesPo).save();
		}
	}	 
	 
}
