package com.pai.biz.auth.repository;

import java.util.List;

import com.pai.biz.frame.repository.IRepository;
import com.pai.biz.auth.domain.AuthRoleUser;
import com.pai.biz.auth.persistence.entity.AuthRoleUserPo;
/**
 * 对象功能:角色-用户 Repository接口
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-12-28 20:59:27
 */
public interface AuthRoleUserRepository extends IRepository<String, AuthRoleUserPo,AuthRoleUser>{

	List<AuthRoleUserPo> findByUserId(String id);
	  
	 
}
