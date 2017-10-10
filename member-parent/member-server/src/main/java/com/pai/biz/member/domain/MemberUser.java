package com.pai.biz.member.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pai.biz.frame.domain.AbstractDomain;
import com.pai.base.core.helper.SpringHelper;
import com.pai.biz.member.persistence.dao.MemberUserDao;
import com.pai.biz.member.persistence.entity.MemberUserPo;

/**
 * 对象功能:会员表 领域对象实体
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-07-02 18:04:24
 * 命名规范：插入以saveXx前缀，修改updateXx前缀，删除deleteXx前缀
 */
@SuppressWarnings("serial")
@Service
@Scope("prototype")
public class MemberUser extends AbstractDomain<String, MemberUserPo>{
	 
	 private MemberUserDao memberUserDao = null;

	protected void init(){
		memberUserDao = SpringHelper.getBean(MemberUserDao.class);
		setDao(memberUserDao);
	}	 
	 
}
