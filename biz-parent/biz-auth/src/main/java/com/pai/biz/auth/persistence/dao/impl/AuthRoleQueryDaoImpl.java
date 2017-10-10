package com.pai.biz.auth.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisQueryDaoImpl;
import com.pai.biz.auth.persistence.dao.AuthRoleQueryDao;
import com.pai.biz.auth.persistence.entity.AuthRolePo;

/**
 * 对象功能:角色 QueryDao接口的实现
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-12-29 09:36:49
 */
@SuppressWarnings("serial")
@Repository
public class AuthRoleQueryDaoImpl extends MyBatisQueryDaoImpl<String, AuthRolePo> implements AuthRoleQueryDao{

    @Override
    public String getNamespace() {
        return AuthRolePo.class.getName();
    }

	@Override
	public AuthRolePo getRoleByUserId(String userId) {
		return getByKey("getRoleByUserId", userId);
	}
	
}
