package com.pai.biz.auth.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.pai.base.core.helper.SpringHelper;
import com.pai.base.db.persistence.dao.IQueryDao;
import com.pai.biz.frame.repository.AbstractRepository;
import com.pai.biz.auth.domain.AuthRoleUser;
import com.pai.biz.auth.repository.AuthRoleUserRepository;
import com.pai.biz.auth.persistence.dao.AuthRoleUserQueryDao;
import com.pai.biz.auth.persistence.entity.AuthRoleUserPo;

/**
 * 对象功能:角色-用户 Repository接口的实现类
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-12-28 20:59:27
 */
@Repository
public class AuthRoleUserRepositoryImpl extends AbstractRepository<String, AuthRoleUserPo,AuthRoleUser> implements AuthRoleUserRepository{
	  
	@Resource
	private  AuthRoleUserQueryDao authRoleUserQueryDao;

	public AuthRoleUser newInstance() {
		AuthRoleUserPo po = new AuthRoleUserPo();
		AuthRoleUser authRoleUser = SpringHelper.getBean(AuthRoleUser.class);
		authRoleUser.setData(po);
		return authRoleUser;
	}

	public AuthRoleUser newInstance(AuthRoleUserPo po) {
		AuthRoleUser authRoleUser = SpringHelper.getBean(AuthRoleUser.class);
		authRoleUser.setData(po);
		return authRoleUser;
	} 
	
	@Override
	protected IQueryDao<String, AuthRoleUserPo> getQueryDao() {
		return authRoleUserQueryDao;
	}

	@Override
	public List<AuthRoleUserPo> findByUserId(String userId) {
		return authRoleUserQueryDao.findByKey("findByUserId", b().a("userId", userId).p());
	}
	
}
