package com.pai.biz.auth.repository;

import com.pai.biz.frame.repository.IRepository;
import com.pai.biz.auth.domain.AuthUser;
import com.pai.biz.auth.persistence.entity.AuthUserPo;
/**
 * 对象功能:用户 Repository接口
 * 开发公司:PI.COM
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 */
public interface AuthUserRepository extends IRepository<String, AuthUserPo,AuthUser>{

	AuthUserPo getAccount(String userName, String encryptPassword);
	  
	 
}
