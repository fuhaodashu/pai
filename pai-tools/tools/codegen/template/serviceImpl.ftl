<#import "function.ftl" as func>
<#assign class=model.variables.class>
<#assign classVar=model.variables.classVar>
<#assign sys=model.variables.sys>
<#assign module=model.variables.module>
<#assign sub=model.sub>
<#assign foreignKey=func.convertUnderLine(model.foreignKey)>
package com.${sys}.biz.${module}.api.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.${sys}.base.api.annotion.doc.AutoDocMethod;
import com.${sys}.base.api.annotion.doc.AutoDocParam;
import com.${sys}.base.api.constants.DeveloperType;
import com.${sys}.base.api.constants.ModuleType;
import com.${sys}.base.api.constants.VersionType;
import com.${sys}.base.api.model.Page;
import com.${sys}.base.api.response.BaseResponse;
import com.${sys}.base.api.response.ResPage;
import com.${sys}.base.core.util.string.StringUtils;
import com.${sys}.base.db.mybatis.impl.domain.MyBatisPage;
import com.${sys}.base.core.util.Mapper;
import com.${sys}.base.db.mybatis.impl.domain.PageResult;
import com.${sys}.base.db.mybatis.impl.domain.PageList;
import com.${sys}.biz.${module}.api.model.${class}Bean;
import com.${sys}.biz.${module}.api.service.${class}Service;
import com.${sys}.biz.${module}.domain.${class};
import com.${sys}.biz.${module}.repository.${class}Repository;
import com.${sys}.biz.${module}.persistence.entity.${class}Po;

/**
 * 对象功能:${model.tabComment} 控制类
 <#if vars.company?exists>
 * 开发公司:${vars.company}
 </#if>
 <#if vars.developer?exists>
 * 开发人员:${vars.developer}
 </#if>
 * 创建时间:${date?string("yyyy-MM-dd HH:mm:ss")}
 */
@Service("${classVar}Service")
public class ${class}ServiceImpl implements ${class}Service {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	 
	@Resource
	private ${class}Repository ${classVar}Repository;
	
	@Override
    @AutoDocMethod(author = DeveloperType.${vars.developer}, createTime = "${date?string("yyyy-MM-dd HH:mm:ss")}", cver = VersionType.V100, module = ModuleType.MEMBER, name = "${model.tabComment}列表查询", description = "查询返回${model.tabComment}列表", sort = 1)
	public BaseResponse<ResPage<${class}Bean>> list${class}Service(@AutoDocParam("whereSql查询参数") Map<String, Object> map, @AutoDocParam("页码") Integer pageNo, @AutoDocParam("条数") Integer pageSize){
		//查询${model.tabComment}列表
		List<${class}Po> ${classVar}PoList = ${classVar}Repository.findPaged(map,  new MyBatisPage(pageNo, pageSize));
		List<${class}Bean> ${classVar}BeanList = Mapper.getInstance().copyList(${classVar}PoList, ${class}Bean.class);
		PageList<${class}Po> pageList = (PageList<${class}Po>) ${classVar}PoList;
		//构造返回数据
		return BaseResponse.buildSuccess(new ResPage<${class}Bean>(pageList.getPageResult().getPage(), pageList.getPageResult().getLimit(), pageList.getPageResult().getTotalCount(), ${classVar}BeanList));
	}
	
	@Override
    @AutoDocMethod(author = DeveloperType.${vars.developer}, createTime = "${date?string("yyyy-MM-dd HH:mm:ss")}", cver = VersionType.V100, module = ModuleType.MEMBER, name = "${model.tabComment}单条查询", description = "查询返回${model.tabComment}单条记录", sort = 2)
	public BaseResponse<${class}Bean> get${class}ServiceById(String id){
		return BaseResponse.buildSuccess(Mapper.getInstance().copyProperties(${classVar}Repository.get(id), ${class}Bean.class));		
	}	
	
	@Override
    @AutoDocMethod(author = DeveloperType.${vars.developer}, createTime = "${date?string("yyyy-MM-dd HH:mm:ss")}", cver = VersionType.V100, module = ModuleType.MEMBER, name = "保存${model.tabComment}", description = "保存${model.tabComment}", sort = 3)
	public BaseResponse save${class}(${class}Bean ${classVar}Bean){
		//构造领域对象和保存数据
		${class} ${classVar} = ${classVar}Repository.newInstance();
		${classVar}.setData(Mapper.getInstance().copyProperties(${classVar}Bean, ${class}Po.class));
		${classVar}.save();
		
		//返回
		return BaseResponse.buildSuccess();
	}		
	
	@Override
    @AutoDocMethod(author = DeveloperType.${vars.developer}, createTime = "${date?string("yyyy-MM-dd HH:mm:ss")}", cver = VersionType.V100, module = ModuleType.MEMBER, name = "删除${model.tabComment}单条记录", description = "删除${model.tabComment}单条记录", sort = 4)
	public BaseResponse delete${class}ById(String id){
		
		//构造领域对象和进行删除操作
		${class} ${classVar} = ${classVar}Repository.newInstance();				
		${classVar}.destroy(id);
		
		//返回
		return BaseResponse.buildSuccess();
	}

}
