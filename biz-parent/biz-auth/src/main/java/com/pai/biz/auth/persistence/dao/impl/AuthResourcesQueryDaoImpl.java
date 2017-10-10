package com.pai.biz.auth.persistence.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisQueryDaoImpl;
import com.pai.biz.auth.persistence.dao.AuthResourcesQueryDao;
import com.pai.biz.auth.persistence.entity.AuthResourcesPo;

/**
 * 对象功能:资源 QueryDao接口的实现
 * 开发公司:PAI.COM
 * 开发人员:FUHAO
 * 创建时间:2016-08-07 14:07:40
 */
@Repository
public class AuthResourcesQueryDaoImpl extends MyBatisQueryDaoImpl<String, AuthResourcesPo> implements AuthResourcesQueryDao{

    @Override
    public String getNamespace() {
        return AuthResourcesPo.class.getName();
    }

	@Override
	public List<AuthResourcesPo> listResourcesByUserId(String userId, Integer type) {
		return findByKey("listResourcesByUserId", b().a("userId", userId).a("type", type).p());
	}

	@Override
	public List<String> findAllUrls() {
		return sqlSessionTemplate.selectList(getNamespace() + ".findAllUrls", null);
	}
	
}
