package com.pai.biz.auth.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pai.biz.frame.domain.AbstractDomain;
import com.pai.base.core.helper.SpringHelper;
import com.pai.biz.auth.persistence.dao.AuthRoleUserDao;
import com.pai.biz.auth.persistence.entity.AuthRoleUserPo;

/**
 * 对象功能:角色-用户 领域对象实体
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-12-28 20:59:27
 */
@SuppressWarnings("serial")
@Service
@Scope("prototype")
public class AuthRoleUser extends AbstractDomain<String, AuthRoleUserPo>{
	 
	 private AuthRoleUserDao authRoleUserDao = null;

	protected void init(){
		authRoleUserDao = SpringHelper.getBean(AuthRoleUserDao.class);
		setDao(authRoleUserDao);
	}	 
	 
}
