package com.pai.biz.auth.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisDaoImpl;
import com.pai.biz.auth.persistence.dao.AuthRoleResourcesDao;
import com.pai.biz.auth.persistence.entity.AuthRoleResourcesPo;

/**
 * 对象功能:角色-授权 Dao接口的实现类
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-12-28 11:56:47
 */
@Repository
public class AuthRoleResourcesDaoImpl extends MyBatisDaoImpl<String, AuthRoleResourcesPo> implements AuthRoleResourcesDao{

    @Override
    public String getNamespace() {
        return AuthRoleResourcesPo.class.getName();
    }

	@Override
	public void deleteByResourceId(String resourceId) {
		super.deleteByKey("deleteByResourceId", b().a("resourceId", resourceId).p());
	}

	@Override
	public void createAdminResource(String id, String resourceId, String userId) {
		sqlSessionTemplate.insert(getNamespace() + ".createAdminResource", b().a("id", id).a("resourceId", resourceId).a("createBy", userId).p());
	}
	
}
