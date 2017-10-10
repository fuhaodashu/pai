package com.pai.biz.auth.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pai.biz.frame.domain.AbstractDomain;
import com.pai.base.core.helper.SpringHelper;
import com.pai.biz.auth.persistence.dao.AuthRoleResourcesDao;
import com.pai.biz.auth.persistence.entity.AuthRoleResourcesPo;

/**
 * 对象功能:角色-授权 领域对象实体
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-12-28 11:56:47
 */
@SuppressWarnings("serial")
@Service
@Scope("prototype")
public class AuthRoleResources extends AbstractDomain<String, AuthRoleResourcesPo>{
	 
	 private AuthRoleResourcesDao authRoleResourcesDao = null;

	protected void init(){
		authRoleResourcesDao = SpringHelper.getBean(AuthRoleResourcesDao.class);
		setDao(authRoleResourcesDao);
	}	 
	 
}
