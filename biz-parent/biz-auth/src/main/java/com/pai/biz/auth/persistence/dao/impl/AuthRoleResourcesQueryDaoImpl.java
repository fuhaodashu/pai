package com.pai.biz.auth.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisQueryDaoImpl;
import com.pai.biz.auth.persistence.dao.AuthRoleResourcesQueryDao;
import com.pai.biz.auth.persistence.entity.AuthRoleResourcesPo;

/**
 * 对象功能:角色-授权 QueryDao接口的实现
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-12-28 11:56:47
 */
@SuppressWarnings("serial")
@Repository
public class AuthRoleResourcesQueryDaoImpl extends MyBatisQueryDaoImpl<String, AuthRoleResourcesPo> implements AuthRoleResourcesQueryDao{

    @Override
    public String getNamespace() {
        return AuthRoleResourcesPo.class.getName();
    }
	
}
