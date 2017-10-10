package com.pai.biz.auth.repository;

import java.util.List;

import com.pai.biz.auth.domain.AuthRole;
import com.pai.biz.auth.persistence.entity.AuthRolePo;
import com.pai.biz.frame.repository.IRepository;
/**
 * 对象功能:角色 Repository接口
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-12-29 09:36:49
 */
public interface AuthRoleRepository extends IRepository<String, AuthRolePo,AuthRole>{

	List<AuthRolePo> findRoleByUserId(String id);

}
