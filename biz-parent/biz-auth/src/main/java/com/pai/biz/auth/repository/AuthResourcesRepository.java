package com.pai.biz.auth.repository;

import java.util.List;

import com.pai.biz.frame.repository.IRepository;
import com.pai.biz.auth.domain.AuthResources;
import com.pai.biz.auth.persistence.entity.AuthResourcesPo;
/**
 * 对象功能:资源 Repository接口
 * 开发公司:PAI.COM
 * 开发人员:FUHAO
 * 创建时间:2016-08-07 14:07:40
 */
public interface AuthResourcesRepository extends IRepository<String, AuthResourcesPo,AuthResources>{

	List<AuthResourcesPo> listResourcesByUserId(String userId);

	List<String> findAllUrls();

	List<AuthResourcesPo> findResourcesByUserId(String userId);

	List<AuthResourcesPo> findChildsByParentId(String id);

	List<AuthResourcesPo> findResourcesWithByRoleId(String roleId);
	
}
