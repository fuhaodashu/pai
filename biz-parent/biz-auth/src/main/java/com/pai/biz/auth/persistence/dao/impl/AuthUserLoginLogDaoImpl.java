package com.pai.biz.auth.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisDaoImpl;
import com.pai.biz.auth.persistence.dao.AuthUserLoginLogDao;
import com.pai.biz.auth.persistence.entity.AuthUserLoginLogPo;

/**
 * 对象功能:用户登录日志 Dao接口的实现类
 * 开发公司:PAI.COM
 * 开发人员:FUHAO
 * 创建时间:2016-10-15 23:19:03
 */
@Repository
public class AuthUserLoginLogDaoImpl extends MyBatisDaoImpl<String, AuthUserLoginLogPo> implements AuthUserLoginLogDao{

    @Override
    public String getNamespace() {
        return AuthUserLoginLogPo.class.getName();
    }
	
}
