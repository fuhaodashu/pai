package com.pai.biz.member.repository;

import com.pai.biz.frame.repository.IRepository;
import com.pai.biz.member.domain.MemberFame;
import com.pai.biz.member.persistence.entity.MemberFamePo;
/**
 * 对象功能:名人堂，为匿名用户服务 Repository接口
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-07-15 16:45:42
 * 命名规范：查list集合以findXx做前缀,单个po实体用getXx做前缀,数量countXx,条件查询加ByXx后缀,如getXxByName
 */
public interface MemberFameRepository extends IRepository<String, MemberFamePo,MemberFame>{
	  
	 
}
