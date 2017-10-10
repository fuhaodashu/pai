package com.pai.biz.member.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisDaoImpl;
import com.pai.biz.member.persistence.dao.MemberUserDao;
import com.pai.biz.member.persistence.entity.MemberUserPo;

/**
 * 对象功能:会员表 Dao接口的实现类
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-07-02 18:04:24
 */
@Repository
public class MemberUserDaoImpl extends MyBatisDaoImpl<String, MemberUserPo> implements MemberUserDao{

    @Override
    public String getNamespace() {
        return MemberUserPo.class.getName();
    }
	
}
