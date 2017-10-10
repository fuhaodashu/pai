package com.pai.biz.auth.repository;

import com.pai.biz.frame.repository.IRepository;
import com.pai.biz.auth.domain.AuthUserLoginLog;
import com.pai.biz.auth.persistence.entity.AuthUserLoginLogPo;
/**
 * 对象功能:用户登录日志 Repository接口
 * 开发公司:PAI.COM
 * 开发人员:FUHAO
 * 创建时间:2016-10-15 23:19:03
 */
public interface AuthUserLoginLogRepository extends IRepository<String, AuthUserLoginLogPo,AuthUserLoginLog>{
	  
	 
}
