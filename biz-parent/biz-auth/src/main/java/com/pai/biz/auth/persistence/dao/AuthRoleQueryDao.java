package com.pai.biz.auth.persistence.dao;
import com.pai.base.db.persistence.dao.IQueryDao;
import com.pai.biz.auth.persistence.entity.AuthRolePo;

/**
 * 对象功能:角色 QueryDao接口
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-12-29 09:36:49
 */
public interface AuthRoleQueryDao extends IQueryDao<String, AuthRolePo> {

	AuthRolePo getRoleByUserId(String userId);

}
