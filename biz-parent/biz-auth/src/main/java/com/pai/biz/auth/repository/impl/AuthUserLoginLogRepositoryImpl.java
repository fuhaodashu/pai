package com.pai.biz.auth.repository.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.pai.base.core.helper.SpringHelper;
import com.pai.base.db.persistence.dao.IQueryDao;
import com.pai.biz.frame.repository.AbstractRepository;
import com.pai.biz.auth.domain.AuthUserLoginLog;
import com.pai.biz.auth.repository.AuthUserLoginLogRepository;
import com.pai.biz.auth.persistence.dao.AuthUserLoginLogQueryDao;
import com.pai.biz.auth.persistence.entity.AuthUserLoginLogPo;

/**
 * 对象功能:用户登录日志 Repository接口的实现类
 * 开发公司:PAI.COM
 * 开发人员:FUHAO
 * 创建时间:2016-10-15 23:19:03
 */
@Repository
public class AuthUserLoginLogRepositoryImpl extends AbstractRepository<String, AuthUserLoginLogPo,AuthUserLoginLog> implements AuthUserLoginLogRepository{
	  
	@Resource
	private  AuthUserLoginLogQueryDao authUserLoginLogQueryDao;

	public AuthUserLoginLog newInstance() {
		AuthUserLoginLogPo po = new AuthUserLoginLogPo();
		AuthUserLoginLog authUserLoginLog = SpringHelper.getBean(AuthUserLoginLog.class);
		authUserLoginLog.setData(po);
		return authUserLoginLog;
	}

	public AuthUserLoginLog newInstance(AuthUserLoginLogPo po) {
		AuthUserLoginLog authUserLoginLog = SpringHelper.getBean(AuthUserLoginLog.class);
		authUserLoginLog.setData(po);
		return authUserLoginLog;
	} 
	
	@Override
	protected IQueryDao<String, AuthUserLoginLogPo> getQueryDao() {
		return authUserLoginLogQueryDao;
	}
	
}
