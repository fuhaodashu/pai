package com.pai.biz.member.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pai.biz.frame.domain.AbstractDomain;
import com.pai.base.core.helper.SpringHelper;
import com.pai.biz.member.persistence.dao.MemberFameDao;
import com.pai.biz.member.persistence.entity.MemberFamePo;

/**
 * 对象功能:名人堂，为匿名用户服务 领域对象实体
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-07-15 16:45:42
 * 命名规范：插入以saveXx前缀，修改updateXx前缀，删除deleteXx前缀
 */
@SuppressWarnings("serial")
@Service
@Scope("prototype")
public class MemberFame extends AbstractDomain<String, MemberFamePo>{
	 
	 private MemberFameDao memberFameDao = null;

	protected void init(){
		memberFameDao = SpringHelper.getBean(MemberFameDao.class);
		setDao(memberFameDao);
	}	 
	 
}
