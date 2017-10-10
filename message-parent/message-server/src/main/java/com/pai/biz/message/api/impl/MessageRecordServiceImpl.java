package com.pai.biz.message.api.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.pai.base.api.annotion.doc.AutoDocMethod;
import com.pai.base.api.annotion.doc.AutoDocParam;
import com.pai.base.api.constants.DeveloperType;
import com.pai.base.api.constants.ModuleType;
import com.pai.base.api.constants.VersionType;
import com.pai.base.api.model.Page;
import com.pai.base.api.response.BaseResponse;
import com.pai.base.api.response.ResPage;
import com.pai.base.core.util.string.StringUtils;
import com.pai.base.db.mybatis.impl.domain.MyBatisPage;
import com.pai.base.core.util.Mapper;
import com.pai.base.db.mybatis.impl.domain.PageResult;
import com.pai.base.db.mybatis.impl.domain.PageList;
import com.pai.biz.message.api.model.MessageRecordBean;
import com.pai.biz.message.api.service.MessageRecordService;
import com.pai.biz.message.domain.MessageRecord;
import com.pai.biz.message.repository.MessageRecordRepository;
import com.pai.biz.message.persistence.entity.MessageRecordPo;

/**
 * 对象功能:pai_message_record 控制类
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-09-10 16:13:57
 */
@Service("messageRecordService")
public class MessageRecordServiceImpl implements MessageRecordService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	 
	@Resource
	private MessageRecordRepository messageRecordRepository;
	
	@Override
    @AutoDocMethod(author = DeveloperType.FU_HAO, createTime = "2017-09-10 16:13:57", cver = VersionType.V100, module = ModuleType.MEMBER, name = "pai_message_record列表查询", description = "查询返回pai_message_record列表", sort = 1)
	public BaseResponse<ResPage<MessageRecordBean>> listMessageRecordService(@AutoDocParam("whereSql查询参数") Map<String, Object> map, @AutoDocParam("页码") Integer pageNo, @AutoDocParam("条数") Integer pageSize){
		//查询pai_message_record列表
		List<MessageRecordPo> messageRecordPoList = messageRecordRepository.findPaged(map,  new MyBatisPage(pageNo, pageSize));
		List<MessageRecordBean> messageRecordBeanList = Mapper.getInstance().copyList(messageRecordPoList, MessageRecordBean.class);
		PageList<MessageRecordPo> pageList = (PageList<MessageRecordPo>) messageRecordPoList;
		//构造返回数据
		return BaseResponse.buildSuccess(new ResPage<MessageRecordBean>(pageList.getPageResult().getPage(), pageList.getPageResult().getLimit(), pageList.getPageResult().getTotalCount(), messageRecordBeanList));
	}
	
	@Override
    @AutoDocMethod(author = DeveloperType.FU_HAO, createTime = "2017-09-10 16:13:57", cver = VersionType.V100, module = ModuleType.MEMBER, name = "pai_message_record单条查询", description = "查询返回pai_message_record单条记录", sort = 2)
	public BaseResponse<MessageRecordBean> getMessageRecordServiceById(String id){
		return BaseResponse.buildSuccess(Mapper.getInstance().copyProperties(messageRecordRepository.get(id), MessageRecordBean.class));		
	}	
	
	@Override
    @AutoDocMethod(author = DeveloperType.FU_HAO, createTime = "2017-09-10 16:13:57", cver = VersionType.V100, module = ModuleType.MEMBER, name = "保存pai_message_record", description = "保存pai_message_record", sort = 3)
	public BaseResponse saveMessageRecord(MessageRecordBean messageRecordBean){
		//构造领域对象和保存数据
		MessageRecord messageRecord = messageRecordRepository.newInstance();
		messageRecord.setData(Mapper.getInstance().copyProperties(messageRecordBean, MessageRecordPo.class));
		messageRecord.save();
		
		//返回
		return BaseResponse.buildSuccess();
	}		
	
	@Override
    @AutoDocMethod(author = DeveloperType.FU_HAO, createTime = "2017-09-10 16:13:57", cver = VersionType.V100, module = ModuleType.MEMBER, name = "删除pai_message_record单条记录", description = "删除pai_message_record单条记录", sort = 4)
	public BaseResponse deleteMessageRecordById(String id){
		
		//构造领域对象和进行删除操作
		MessageRecord messageRecord = messageRecordRepository.newInstance();				
		messageRecord.destroy(id);
		
		//返回
		return BaseResponse.buildSuccess();
	}

}
