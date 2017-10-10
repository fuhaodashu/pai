package com.pai.biz.message.test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.alibaba.fastjson.JSON;
import com.pai.base.api.response.BaseResponse;
import com.pai.base.api.response.ResPage;
import com.pai.base.api.service.IdGenerator;
import com.pai.base.core.test.BaseTestCase;
import com.pai.biz.message.api.model.MessageRecordBean;
import com.pai.biz.message.api.service.MessageRecordService;

/**
 * 测试类，。
 * @author FU_HAO
 *
 */
@ContextConfiguration({"classpath:conf/message-server.xml"})
public class MessageRecordServiceTest extends BaseTestCase{
	
	@Resource
    private IdGenerator idGenerator;
    
    @Resource
    private MessageRecordService messageRecordService;
    
    @Test
	public void listMessageRecordServiceTest(){
		Map<String, Object> map = new HashMap<String, Object>();
		BaseResponse<ResPage<MessageRecordBean>> baseResponse = messageRecordService.listMessageRecordService(map, 1, 10);
		System.out.println(JSON.toJSONString(baseResponse, true));
	}
}
