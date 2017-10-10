package com.pai.biz.auth.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.pai.base.core.helper.SpringHelper;
import com.pai.base.db.persistence.dao.IQueryDao;
import com.pai.biz.auth.domain.AuthResources;
import com.pai.biz.auth.persistence.dao.AuthResourcesQueryDao;
import com.pai.biz.auth.persistence.entity.AuthResourcesPo;
import com.pai.biz.auth.repository.AuthResourcesRepository;
import com.pai.biz.frame.repository.AbstractRepository;

/**
 * 对象功能:资源 Repository接口的实现类
 * 开发公司:PAI.COM
 * 开发人员:FUHAO
 * 创建时间:2016-08-07 14:07:40
 */
@Repository
public class AuthResourcesRepositoryImpl extends AbstractRepository<String, AuthResourcesPo,AuthResources> implements AuthResourcesRepository{
	  
	@Resource
	private  AuthResourcesQueryDao authResourcesQueryDao;

	public AuthResources newInstance() {
		AuthResourcesPo po = new AuthResourcesPo();
		AuthResources authResources = SpringHelper.getBean(AuthResources.class);
		authResources.setData(po);
		return authResources;
	}

	public AuthResources newInstance(AuthResourcesPo po) {
		AuthResources authResources = SpringHelper.getBean(AuthResources.class);
		authResources.setData(po);
		return authResources;
	} 
	
	@Override
	protected IQueryDao<String, AuthResourcesPo> getQueryDao() {
		return authResourcesQueryDao;
	}
	
	//查询菜单资源，用于后台主页树菜单操作
	@Override
	public List<AuthResourcesPo> listResourcesByUserId(String userId) {
		return listToTree(listResourcesByUserId(userId, AuthResourcesPo.ResourceTypeEnum.MENU.getType()));
	}
	
	//查询所有url用于权限过滤
	@Override
	public List<String> findAllUrls() {
		return authResourcesQueryDao.findAllUrls();
	}
	
	//查询带有按钮的资源链接，用于权限过滤
	@Override
	public List<AuthResourcesPo> findResourcesByUserId(String userId) {
		return listResourcesByUserId(userId, null);
	}
	
	private List<AuthResourcesPo> listResourcesByUserId(String userId, Integer type){
		return authResourcesQueryDao.listResourcesByUserId(userId, type);
	}
	
	//根据父节点查找其子节点 
	@Override
	public List<AuthResourcesPo> findChildsByParentId(String parentId) {
		return authResourcesQueryDao.findByKey("findChildsByParentId", b().a("parentId", parentId).p());
	}

	//查询所有的资源并根据角色id判断该角色是否拥有此资源权限
	@Override
	public List<AuthResourcesPo> findResourcesWithByRoleId(String roleId) {
		return listToTree(authResourcesQueryDao.findByKey("findResourcesWithByRoleId", b().a("roleId", roleId).p()));
	}
	
}
