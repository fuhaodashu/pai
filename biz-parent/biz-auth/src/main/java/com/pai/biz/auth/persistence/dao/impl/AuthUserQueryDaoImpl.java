package com.pai.biz.auth.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisQueryDaoImpl;
import com.pai.biz.auth.persistence.dao.AuthUserQueryDao;
import com.pai.biz.auth.persistence.entity.AuthUserPo;

/**
 * 对象功能:用户 QueryDao接口的实现
 * 开发公司:PI.COM
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 */
@SuppressWarnings("serial")
@Repository
public class AuthUserQueryDaoImpl extends MyBatisQueryDaoImpl<String, AuthUserPo> implements AuthUserQueryDao{

    @Override
    public String getNamespace() {
        return AuthUserPo.class.getName();
    }

	@Override
	public AuthUserPo getAccount(String userName, String encryptPassword) {
		return getByKey("getAccount", b().a("name", userName).a("password", encryptPassword).p());
	}
	
}
