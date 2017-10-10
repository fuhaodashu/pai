package com.pai.biz.member.repository.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.pai.base.core.helper.SpringHelper;
import com.pai.base.db.persistence.dao.IQueryDao;
import com.pai.biz.frame.repository.AbstractRepository;
import com.pai.biz.member.domain.MemberFame;
import com.pai.biz.member.repository.MemberFameRepository;
import com.pai.biz.member.persistence.dao.MemberFameQueryDao;
import com.pai.biz.member.persistence.entity.MemberFamePo;

/**
 * 对象功能:名人堂，为匿名用户服务 Repository接口的实现类
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-07-15 16:45:42
 */
@Repository
public class MemberFameRepositoryImpl extends AbstractRepository<String, MemberFamePo,MemberFame> implements MemberFameRepository{
	  
	@Resource
	private  MemberFameQueryDao memberFameQueryDao;

	public MemberFame newInstance() {
		MemberFamePo po = new MemberFamePo();
		MemberFame memberFame = SpringHelper.getBean(MemberFame.class);
		memberFame.setData(po);
		return memberFame;
	}

	public MemberFame newInstance(MemberFamePo po) {
		MemberFame memberFame = SpringHelper.getBean(MemberFame.class);
		memberFame.setData(po);
		return memberFame;
	} 
	
	@Override
	protected IQueryDao<String, MemberFamePo> getQueryDao() {
		return memberFameQueryDao;
	}
	
}
