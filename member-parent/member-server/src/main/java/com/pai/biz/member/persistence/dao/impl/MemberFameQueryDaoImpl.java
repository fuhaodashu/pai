package com.pai.biz.member.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisQueryDaoImpl;
import com.pai.biz.member.persistence.dao.MemberFameQueryDao;
import com.pai.biz.member.persistence.entity.MemberFamePo;

/**
 * 对象功能:名人堂，为匿名用户服务 QueryDao接口的实现
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-07-15 16:45:42
 */
@SuppressWarnings("serial")
@Repository
public class MemberFameQueryDaoImpl extends MyBatisQueryDaoImpl<String, MemberFamePo> implements MemberFameQueryDao{

    @Override
    public String getNamespace() {
        return MemberFamePo.class.getName();
    }
	
}
