package com.pai.biz.auth.repository.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.pai.base.api.model.Page;
import com.pai.base.core.helper.SpringHelper;
import com.pai.base.db.persistence.dao.IQueryDao;
import com.pai.biz.auth.domain.AuthRoleResources;
import com.pai.biz.auth.persistence.dao.AuthRoleResourcesQueryDao;
import com.pai.biz.auth.persistence.entity.AuthRoleResourcesPo;
import com.pai.biz.auth.repository.AuthRoleResourcesRepository;
import com.pai.biz.frame.repository.AbstractRepository;

/**
 * 对象功能:角色-授权 Repository接口的实现类
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-12-28 11:56:47
 */
@Repository
public class AuthRoleResourcesRepositoryImpl extends AbstractRepository<String, AuthRoleResourcesPo,AuthRoleResources> implements AuthRoleResourcesRepository{
	  
	@Resource
	private  AuthRoleResourcesQueryDao authRoleResourcesQueryDao;

	public AuthRoleResources newInstance() {
		AuthRoleResourcesPo po = new AuthRoleResourcesPo();
		AuthRoleResources authRoleResources = SpringHelper.getBean(AuthRoleResources.class);
		authRoleResources.setData(po);
		return authRoleResources;
	}

	public AuthRoleResources newInstance(AuthRoleResourcesPo po) {
		AuthRoleResources authRoleResources = SpringHelper.getBean(AuthRoleResources.class);
		authRoleResources.setData(po);
		return authRoleResources;
	} 
	
	@Override
	protected IQueryDao<String, AuthRoleResourcesPo> getQueryDao() {
		return authRoleResourcesQueryDao;
	}

	@Override
	public List<AuthRoleResourcesPo> findRoleResourcesByRoleId(String roleId) {
		return authRoleResourcesQueryDao.findByKey("findRoleResourcesByRoleId", b().a("roleId", roleId).p());
	}

	@Override
	public List<AuthRoleResourcesPo> findRoleByResourceId(Map<String, Object> params, Page page) {
		return authRoleResourcesQueryDao.findByKey("findRoleByResourceId", params, page);
	}
	
}
