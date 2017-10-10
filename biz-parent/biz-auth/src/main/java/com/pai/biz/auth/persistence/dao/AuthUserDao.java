package com.pai.biz.auth.persistence.dao;
import com.pai.base.db.persistence.dao.IDao;
import com.pai.biz.auth.persistence.entity.AuthUserPo;

/**
 * 对象功能:用户 Dao接口
 * 开发公司:PAI.COM
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 */
public interface AuthUserDao extends IDao<String, AuthUserPo> {

	void updatePassword(AuthUserPo authUserPo);

}
