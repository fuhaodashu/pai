package com.pai.biz.auth.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisQueryDaoImpl;
import com.pai.biz.auth.persistence.dao.AuthRoleUserQueryDao;
import com.pai.biz.auth.persistence.entity.AuthRoleUserPo;

/**
 * 对象功能:角色-用户 QueryDao接口的实现
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-12-28 20:59:27
 */
@SuppressWarnings("serial")
@Repository
public class AuthRoleUserQueryDaoImpl extends MyBatisQueryDaoImpl<String, AuthRoleUserPo> implements AuthRoleUserQueryDao{

    @Override
    public String getNamespace() {
        return AuthRoleUserPo.class.getName();
    }
	
}
