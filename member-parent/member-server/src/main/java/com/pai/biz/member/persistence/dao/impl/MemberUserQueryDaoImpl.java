package com.pai.biz.member.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.pai.base.db.mybatis.dao.MyBatisQueryDaoImpl;
import com.pai.biz.member.persistence.dao.MemberUserQueryDao;
import com.pai.biz.member.persistence.entity.MemberUserPo;

/**
 * 对象功能:会员表 QueryDao接口的实现
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-07-02 18:04:24
 */
@SuppressWarnings("serial")
@Repository
public class MemberUserQueryDaoImpl extends MyBatisQueryDaoImpl<String, MemberUserPo> implements MemberUserQueryDao{

    @Override
    public String getNamespace() {
        return MemberUserPo.class.getName();
    }
	
}
