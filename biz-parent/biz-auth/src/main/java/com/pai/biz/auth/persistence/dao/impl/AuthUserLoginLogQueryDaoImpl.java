package com.pai.biz.auth.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisQueryDaoImpl;
import com.pai.biz.auth.persistence.dao.AuthUserLoginLogQueryDao;
import com.pai.biz.auth.persistence.entity.AuthUserLoginLogPo;

/**
 * 对象功能:用户登录日志 QueryDao接口的实现
 * 开发公司:PAI.COM
 * 开发人员:FUHAO
 * 创建时间:2016-10-15 23:19:03
 */
@SuppressWarnings("serial")
@Repository
public class AuthUserLoginLogQueryDaoImpl extends MyBatisQueryDaoImpl<String, AuthUserLoginLogPo> implements AuthUserLoginLogQueryDao{

    @Override
    public String getNamespace() {
        return AuthUserLoginLogPo.class.getName();
    }
	
}
