package com.pai.biz.member.repository.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.pai.base.core.helper.SpringHelper;
import com.pai.base.db.persistence.dao.IQueryDao;
import com.pai.biz.frame.repository.AbstractRepository;
import com.pai.biz.member.domain.MemberUser;
import com.pai.biz.member.repository.MemberUserRepository;
import com.pai.biz.member.persistence.dao.MemberUserQueryDao;
import com.pai.biz.member.persistence.entity.MemberUserPo;

/**
 * 对象功能:会员表 Repository接口的实现类
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-07-02 18:04:24
 */
@Repository
public class MemberUserRepositoryImpl extends AbstractRepository<String, MemberUserPo,MemberUser> implements MemberUserRepository{
	  
	@Resource
	private  MemberUserQueryDao memberUserQueryDao;

	public MemberUser newInstance() {
		MemberUserPo po = new MemberUserPo();
		MemberUser memberUser = SpringHelper.getBean(MemberUser.class);
		memberUser.setData(po);
		return memberUser;
	}

	public MemberUser newInstance(MemberUserPo po) {
		MemberUser memberUser = SpringHelper.getBean(MemberUser.class);
		memberUser.setData(po);
		return memberUser;
	} 
	
	@Override
	protected IQueryDao<String, MemberUserPo> getQueryDao() {
		return memberUserQueryDao;
	}
	
}
