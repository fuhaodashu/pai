package com.pai.biz.auth.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.pai.base.core.helper.SpringHelper;
import com.pai.base.db.persistence.dao.IQueryDao;
import com.pai.biz.auth.domain.AuthRole;
import com.pai.biz.auth.persistence.dao.AuthRoleQueryDao;
import com.pai.biz.auth.persistence.entity.AuthRolePo;
import com.pai.biz.auth.repository.AuthRoleRepository;
import com.pai.biz.frame.repository.AbstractRepository;

/**
 * 对象功能:角色 Repository接口的实现类
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-12-29 09:36:49
 */
@Repository
public class AuthRoleRepositoryImpl extends AbstractRepository<String, AuthRolePo,AuthRole> implements AuthRoleRepository{
	  
	@Resource
	private  AuthRoleQueryDao authRoleQueryDao;

	public AuthRole newInstance() {
		AuthRolePo po = new AuthRolePo();
		AuthRole authRole = SpringHelper.getBean(AuthRole.class);
		authRole.setData(po);
		return authRole;
	}

	public AuthRole newInstance(AuthRolePo po) {
		AuthRole authRole = SpringHelper.getBean(AuthRole.class);
		authRole.setData(po);
		return authRole;
	} 
	
	@Override
	protected IQueryDao<String, AuthRolePo> getQueryDao() {
		return authRoleQueryDao;
	}

	@Override
	public List<AuthRolePo> findRoleByUserId(String userId) {
		return authRoleQueryDao.findByKey("findRoleByUserId", b().a("userId", userId).p());
	}
	
}
