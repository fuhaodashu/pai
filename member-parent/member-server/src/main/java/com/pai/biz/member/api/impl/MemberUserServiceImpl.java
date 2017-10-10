package com.pai.biz.member.api.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pai.base.api.annotion.PAICacheable;
import com.pai.base.api.annotion.doc.AutoDocMethod;
import com.pai.base.api.annotion.doc.AutoDocParam;
import com.pai.base.api.constants.DeveloperType;
import com.pai.base.api.constants.ModuleType;
import com.pai.base.api.constants.VersionType;
import com.pai.base.api.response.BaseResponse;
import com.pai.base.api.response.ResPage;
import com.pai.base.core.redis.constants.RedisDb;
import com.pai.base.core.util.Mapper;
import com.pai.base.db.mybatis.impl.domain.MyBatisPage;
import com.pai.base.db.mybatis.impl.domain.PageList;
import com.pai.biz.member.api.model.MemberUserBean;
import com.pai.biz.member.api.service.MemberUserService;
import com.pai.biz.member.domain.MemberUser;
import com.pai.biz.member.persistence.entity.MemberUserPo;
import com.pai.biz.member.repository.MemberUserRepository;

/**
 * 对象功能:会员表 控制类
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-07-15 20:45:31
 */
@Service("memberUserService")
public class MemberUserServiceImpl implements MemberUserService {
	 
	@Resource
	private MemberUserRepository memberUserRepository;
	
	@Override
    @AutoDocMethod(author = DeveloperType.FU_HAO, createTime = "2017-07-15 20:45:31", cver = VersionType.V100, module = ModuleType.MEMBER, name = "会员表列表查询", description = "查询返回会员表列表", sort = 1)
	@PAICacheable(key="member_${pageNo}", db=RedisDb.DBZERO, params="map,pageNo,pageSize", seconds=60*2)
	public BaseResponse<ResPage<MemberUserBean>> listMemberUserService(@AutoDocParam("whereSql查询参数") Map<String, Object> map, @AutoDocParam("页码") Integer pageNo, @AutoDocParam("条数") Integer pageSize){
		//查询会员表列表
		List<MemberUserPo> memberUserPoList = memberUserRepository.findPaged(map,  new MyBatisPage(pageNo, pageSize));
		List<MemberUserBean> memberUserBeanList = Mapper.getInstance().copyList(memberUserPoList, MemberUserBean.class);
		PageList<MemberUserPo> pageList = (PageList<MemberUserPo>) memberUserPoList;
		//构造返回数据
		return BaseResponse.buildSuccess(new ResPage<MemberUserBean>(pageList.getPageResult().getPage(), pageList.getPageResult().getLimit(), pageList.getPageResult().getTotalCount(), memberUserBeanList));
	}
	
	@Override
    @AutoDocMethod(author = DeveloperType.FU_HAO, createTime = "2017-07-15 20:45:31", cver = VersionType.V100, module = ModuleType.MEMBER, name = "会员表单条查询", description = "查询返回会员表单条记录", sort = 2)
	public BaseResponse<MemberUserBean> getMemberUserServiceById(String id){
		return BaseResponse.buildSuccess(Mapper.getInstance().copyProperties(memberUserRepository.get(id), MemberUserBean.class));		
	}	
	
	@Override
    @AutoDocMethod(author = DeveloperType.FU_HAO, createTime = "2017-07-15 20:45:31", cver = VersionType.V100, module = ModuleType.MEMBER, name = "保存会员表", description = "保存会员表", sort = 3)
	public BaseResponse saveMemberUser(MemberUserBean memberUserBean){
		//构造领域对象和保存数据
		MemberUser memberUser = memberUserRepository.newInstance();
		memberUser.setData(Mapper.getInstance().copyProperties(memberUserBean, MemberUserPo.class));
		memberUser.save();
		
		//返回
		return BaseResponse.buildSuccess();
	}		
	
	@Override
    @AutoDocMethod(author = DeveloperType.FU_HAO, createTime = "2017-07-15 20:45:31", cver = VersionType.V100, module = ModuleType.MEMBER, name = "删除会员表单条记录", description = "删除会员表单条记录", sort = 4)
	public BaseResponse deleteMemberUserById(String id){
		
		//构造领域对象和进行删除操作
		MemberUser memberUser = memberUserRepository.newInstance();				
		memberUser.destroy(id);
		
		//返回
		return BaseResponse.buildSuccess();
	}

}
