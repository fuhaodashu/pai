package com.pai.biz.auth.repository.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.pai.base.core.helper.SpringHelper;
import com.pai.base.db.persistence.dao.IQueryDao;
import com.pai.biz.auth.domain.AuthUser;
import com.pai.biz.auth.persistence.dao.AuthUserQueryDao;
import com.pai.biz.auth.persistence.entity.AuthUserPo;
import com.pai.biz.auth.repository.AuthUserRepository;
import com.pai.biz.frame.repository.AbstractRepository;

/**
 * 对象功能:用户 Repository接口的实现类
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 */
@Repository
public class AuthUserRepositoryImpl extends AbstractRepository<String, AuthUserPo,AuthUser> implements AuthUserRepository{
	  
	@Resource
	private  AuthUserQueryDao authUserQueryDao;

	public AuthUser newInstance() {
		AuthUserPo po = new AuthUserPo();
		AuthUser authUser = SpringHelper.getBean(AuthUser.class);
		authUser.setData(po);
		return authUser;
	}

	public AuthUser newInstance(AuthUserPo po) {
		AuthUser authUser = SpringHelper.getBean(AuthUser.class);
		authUser.setData(po);
		return authUser;
	} 
	
	@Override
	protected IQueryDao<String, AuthUserPo> getQueryDao() {
		return authUserQueryDao;
	}

	@Override
	public AuthUserPo getAccount(String userName, String encryptPassword) {
		return authUserQueryDao.getAccount(userName, encryptPassword);
	}
	
}
