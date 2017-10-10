package com.pai.biz.message.api.service;
import java.util.Map;

import com.pai.base.api.response.BaseResponse;
import com.pai.base.api.response.ResPage;
import com.pai.biz.message.api.model.MessageRecordBean;
/**
 * 对象功能:pai_message_record service接口
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-09-10 16:13:57
 * 命名规范：查list集合以listXx做前缀,单个po实体用getXx做前缀,数量countXx,条件查询加ByXx后缀,如getXxByName
 */
public interface MessageRecordService {
	/**
	 * 查询【pai_message_record】列表
	 * @param map(whereSql,orderBySql)
	 * @param page
	 * @return BaseResponse
	 * @throws Exception 
	 * @exception 
	 * @since  1.0.0
	 */
	BaseResponse<ResPage<MessageRecordBean>> listMessageRecordService(Map<String, Object> map, Integer pageNo, Integer pageSize);
	
	/**
	 * 根据id获取单个对象
	 * @param id
	 * @return BaseResponse
	 * @throws Exception 
	 * @exception 
	 * @since  1.0.0
	 */	 
	BaseResponse<MessageRecordBean> getMessageRecordServiceById(String id);
	
	/**
	 * 保存【pai_message_record】
	 * @param messageRecordBean
	 * @throws Exception 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */	
	BaseResponse saveMessageRecord(MessageRecordBean messageRecordBean);
	
	/**
	 * 删除【pai_message_record】
	 * @param id
	 * @return
	 * @throws Exception 
	 * CommonResult
	 * @exception 
	 * @since  1.0.0
	 */
	BaseResponse deleteMessageRecordById(String id);
}
