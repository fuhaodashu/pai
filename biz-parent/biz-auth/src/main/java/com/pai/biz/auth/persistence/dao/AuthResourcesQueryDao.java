package com.pai.biz.auth.persistence.dao;
import java.util.List;

import com.pai.base.db.persistence.dao.IQueryDao;
import com.pai.biz.auth.persistence.entity.AuthResourcesPo;

/**
 * 对象功能:资源 QueryDao接口
 * 开发公司:PAI.COM
 * 开发人员:FUHAO
 * 创建时间:2016-08-07 14:07:40
 */
public interface AuthResourcesQueryDao extends IQueryDao<String, AuthResourcesPo> {
	/**
	 * 根据用户Id查找权限
	 * @param userId
	 * @return
	 */
	public List<AuthResourcesPo> listResourcesByUserId(String userId, Integer type);
	
	/**
	 * 查询所有url用于拦截器做匹配
	 * @return
	 */
	public List<String> findAllUrls();
}
