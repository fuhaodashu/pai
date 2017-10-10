package com.pai.biz.member.repository;

import com.pai.biz.frame.repository.IRepository;
import com.pai.biz.member.domain.MemberUser;
import com.pai.biz.member.persistence.entity.MemberUserPo;
/**
 * 对象功能:会员表 Repository接口
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-07-02 18:04:24
 * 命名规范：查list集合以findXx做前缀,单个po实体用getXx做前缀,数量countXx,条件查询加ByXx后缀,如getXxByName
 */
public interface MemberUserRepository extends IRepository<String, MemberUserPo,MemberUser>{
	  
	 
}
