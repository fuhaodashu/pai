package com.pai.biz.auth.repository;

import java.util.List;
import java.util.Map;

import com.pai.base.api.model.Page;
import com.pai.biz.auth.domain.AuthRoleResources;
import com.pai.biz.auth.persistence.entity.AuthRoleResourcesPo;
import com.pai.biz.frame.repository.IRepository;
/**
 * 对象功能:角色-授权 Repository接口
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-12-28 11:56:47
 */
public interface AuthRoleResourcesRepository extends IRepository<String, AuthRoleResourcesPo,AuthRoleResources>{

	List<AuthRoleResourcesPo> findRoleResourcesByRoleId(String roleId);

	List<AuthRoleResourcesPo> findRoleByResourceId(Map<String, Object> params, Page page);
	 
}
