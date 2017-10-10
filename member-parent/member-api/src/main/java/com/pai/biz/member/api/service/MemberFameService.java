package com.pai.biz.member.api.service;
import java.util.Map;

import com.pai.base.api.response.BaseResponse;
import com.pai.base.api.response.ResPage;
import com.pai.biz.member.api.model.MemberFameBean;
/**
 * 对象功能:名人堂，为匿名用户服务 service接口
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-07-15 16:45:43
 * 命名规范：查list集合以listXx做前缀,单个po实体用getXx做前缀,数量countXx,条件查询加ByXx后缀,如getXxByName
 */
public interface MemberFameService {
	/**
	 * 查询【名人堂，为匿名用户服务】列表
	 * @param map(whereSql,orderBySql)
	 * @param page
	 * @return BaseResponse
	 * @throws Exception 
	 * @exception 
	 * @since  1.0.0
	 */
	BaseResponse<ResPage<MemberFameBean>> listMemberFameService(Map<String, Object> map, Integer pageNo, Integer pageSize);
	
	/**
	 * 根据id获取单个对象
	 * @param id
	 * @return BaseResponse
	 * @throws Exception 
	 * @exception 
	 * @since  1.0.0
	 */	 
	BaseResponse<MemberFameBean> getMemberFameServiceById(String id);
	
	/**
	 * 保存【名人堂，为匿名用户服务】
	 * @param memberFameBean
	 * @throws Exception 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */	
	BaseResponse saveMemberFame(MemberFameBean memberFameBean);
	
	/**
	 * 删除【名人堂，为匿名用户服务】
	 * @param id
	 * @return
	 * @throws Exception 
	 * CommonResult
	 * @exception 
	 * @since  1.0.0
	 */
	BaseResponse deleteMemberFameById(String id);
}
