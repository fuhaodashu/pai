package com.pai.biz.member.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisDaoImpl;
import com.pai.biz.member.persistence.dao.MemberFameDao;
import com.pai.biz.member.persistence.entity.MemberFamePo;

/**
 * 对象功能:名人堂，为匿名用户服务 Dao接口的实现类
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-07-15 16:45:42
 */
@Repository
public class MemberFameDaoImpl extends MyBatisDaoImpl<String, MemberFamePo> implements MemberFameDao{

    @Override
    public String getNamespace() {
        return MemberFamePo.class.getName();
    }
	
}
