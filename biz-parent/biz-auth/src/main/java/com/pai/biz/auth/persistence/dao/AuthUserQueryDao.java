package com.pai.biz.auth.persistence.dao;
import com.pai.base.db.persistence.dao.IQueryDao;
import com.pai.biz.auth.persistence.entity.AuthUserPo;

/**
 * 对象功能:用户 QueryDao接口
 * 开发公司:PI.COM
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 */
public interface AuthUserQueryDao extends IQueryDao<String, AuthUserPo> {

	AuthUserPo getAccount(String userName, String encryptPassword);

}
