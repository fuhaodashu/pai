package com.pai.biz.member.api.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pai.base.api.annotion.doc.AutoDocMethod;
import com.pai.base.api.annotion.doc.AutoDocParam;
import com.pai.base.api.constants.DeveloperType;
import com.pai.base.api.constants.ModuleType;
import com.pai.base.api.constants.VersionType;
import com.pai.base.api.response.BaseResponse;
import com.pai.base.api.response.ResPage;
import com.pai.base.core.util.Mapper;
import com.pai.base.core.util.string.StringUtils;
import com.pai.base.db.mybatis.impl.domain.MyBatisPage;
import com.pai.base.db.mybatis.impl.domain.PageList;
import com.pai.biz.member.api.model.MemberFameBean;
import com.pai.biz.member.api.service.MemberFameService;
import com.pai.biz.member.domain.MemberFame;
import com.pai.biz.member.persistence.entity.MemberFamePo;
import com.pai.biz.member.repository.MemberFameRepository;

/**
 * 对象功能:名人堂，为匿名用户服务 控制类
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-07-15 16:45:43
 */
@Service("memberFameService")
public class MemberFameServiceImpl implements MemberFameService {
	 
	@Resource
	private MemberFameRepository memberFameRepository;
	
	@Override
    @AutoDocMethod(author = DeveloperType.FU_HAO, createTime = "2017-07-15 16:45:43", cver = VersionType.V100, module = ModuleType.MEMBER, name = "名人堂，为匿名用户服务列表查询", description = "查询返回名人堂，为匿名用户服务列表", sort = 1)
	public BaseResponse<ResPage<MemberFameBean>> listMemberFameService(@AutoDocParam("whereSql查询参数") Map<String, Object> map, @AutoDocParam("页码") Integer pageNo, @AutoDocParam("条数") Integer pageSize){
		//查询名人堂，为匿名用户服务列表
		List<MemberFamePo> memberFamePoList = memberFameRepository.findPaged(map,  new MyBatisPage(pageNo, pageSize));
		List<MemberFameBean> memberFameBeanList = Mapper.getInstance().copyList(memberFamePoList, MemberFameBean.class);
		PageList<MemberFamePo> pageList = (PageList<MemberFamePo>) memberFamePoList;
		//构造返回数据
		return BaseResponse.buildSuccess(new ResPage<MemberFameBean>(pageList.getPageResult().getPage(), pageList.getPageResult().getLimit(), pageList.getPageResult().getTotalCount(), memberFameBeanList));
	}
	
	@Override
    @AutoDocMethod(author = DeveloperType.FU_HAO, createTime = "2017-07-15 16:45:43", cver = VersionType.V100, module = ModuleType.MEMBER, name = "名人堂，为匿名用户服务单条查询", description = "查询返回名人堂，为匿名用户服务单条记录", sort = 2)
	public BaseResponse<MemberFameBean> getMemberFameServiceById(String id){
		return BaseResponse.buildSuccess(Mapper.getInstance().copyProperties(memberFameRepository.load(id).getData(), MemberFameBean.class));		
	}	
	
	@Override
    @AutoDocMethod(author = DeveloperType.FU_HAO, createTime = "2017-07-15 16:45:43", cver = VersionType.V100, module = ModuleType.MEMBER, name = "保存名人堂，为匿名用户服务", description = "保存名人堂，为匿名用户服务", sort = 3)
	public BaseResponse saveMemberFame(MemberFameBean memberFameBean){
		//是否新增
		boolean isNew = StringUtils.isEmpty(memberFameBean.getId())?true:false;
		
		//构造领域对象和保存数据
		MemberFame memberFame = memberFameRepository.newInstance();
		memberFame.setData(Mapper.getInstance().copyProperties(memberFameBean, MemberFamePo.class));
		memberFame.save();
		
		//返回
		return BaseResponse.buildSuccess();
	}		
	
	@Override
    @AutoDocMethod(author = DeveloperType.FU_HAO, createTime = "2017-07-15 16:45:43", cver = VersionType.V100, module = ModuleType.MEMBER, name = "删除名人堂，为匿名用户服务单条记录", description = "删除名人堂，为匿名用户服务单条记录", sort = 4)
	public BaseResponse deleteMemberFameById(String id){
		
		//构造领域对象和进行删除操作
		MemberFame memberFame = memberFameRepository.newInstance();				
		memberFame.destroy(id);
		
		//返回
		return BaseResponse.buildSuccess();
	}

}
