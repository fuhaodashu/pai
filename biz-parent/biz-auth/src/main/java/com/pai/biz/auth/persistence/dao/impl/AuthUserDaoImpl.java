package com.pai.biz.auth.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisDaoImpl;
import com.pai.biz.auth.persistence.dao.AuthUserDao;
import com.pai.biz.auth.persistence.entity.AuthUserPo;

/**
 * 对象功能:用户 Dao接口的实现类
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 */
@Repository
public class AuthUserDaoImpl extends MyBatisDaoImpl<String, AuthUserPo> implements AuthUserDao{

    @Override
    public String getNamespace() {
        return AuthUserPo.class.getName();
    }

	@Override
	public void updatePassword(AuthUserPo authUserPo) {
		updateByExampleSelective(authUserPo, b().a("whereSql", "id_="+authUserPo.getId()).p());
	}
	
}
