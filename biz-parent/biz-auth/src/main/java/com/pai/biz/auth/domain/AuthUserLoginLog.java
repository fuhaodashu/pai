package com.pai.biz.auth.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pai.biz.frame.domain.AbstractDomain;
import com.pai.base.core.helper.SpringHelper;
import com.pai.biz.auth.persistence.dao.AuthUserLoginLogDao;
import com.pai.biz.auth.persistence.entity.AuthUserLoginLogPo;

/**
 * 对象功能:用户登录日志 领域对象实体
 * 开发公司:PAI.COM
 * 开发人员:FUHAO
 * 创建时间:2016-10-15 23:19:03
 */
@SuppressWarnings("serial")
@Service
@Scope("prototype")
public class AuthUserLoginLog extends AbstractDomain<String, AuthUserLoginLogPo>{
	 
	 private AuthUserLoginLogDao authUserLoginLogDao = null;

	protected void init(){
		authUserLoginLogDao = SpringHelper.getBean(AuthUserLoginLogDao.class);
		setDao(authUserLoginLogDao);
	}	 
	 
}
