package com.pai.biz.auth.persistence.dao;
import com.pai.base.db.persistence.dao.IDao;
import com.pai.biz.auth.persistence.entity.AuthRoleResourcesPo;

/**
 * 对象功能:角色-授权 Dao接口
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-12-28 11:56:47
 */
public interface AuthRoleResourcesDao extends IDao<String, AuthRoleResourcesPo> {

	void deleteByResourceId(String id);

	void createAdminResource(String id, String resourceId, String userId);

}
