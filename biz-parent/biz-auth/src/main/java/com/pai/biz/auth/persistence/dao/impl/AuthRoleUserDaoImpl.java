package com.pai.biz.auth.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisDaoImpl;
import com.pai.biz.auth.persistence.dao.AuthRoleUserDao;
import com.pai.biz.auth.persistence.entity.AuthRoleUserPo;

/**
 * 对象功能:角色-用户 Dao接口的实现类
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-12-28 20:59:27
 */
@Repository
public class AuthRoleUserDaoImpl extends MyBatisDaoImpl<String, AuthRoleUserPo> implements AuthRoleUserDao{

    @Override
    public String getNamespace() {
        return AuthRoleUserPo.class.getName();
    }
	
}
