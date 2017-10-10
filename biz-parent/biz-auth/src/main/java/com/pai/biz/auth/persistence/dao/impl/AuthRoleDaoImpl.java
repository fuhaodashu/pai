package com.pai.biz.auth.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisDaoImpl;
import com.pai.biz.auth.persistence.dao.AuthRoleDao;
import com.pai.biz.auth.persistence.entity.AuthRolePo;

/**
 * 对象功能:角色 Dao接口的实现类
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-12-29 09:36:49
 */
@Repository
public class AuthRoleDaoImpl extends MyBatisDaoImpl<String, AuthRolePo> implements AuthRoleDao{

    @Override
    public String getNamespace() {
        return AuthRolePo.class.getName();
    }
	
}
