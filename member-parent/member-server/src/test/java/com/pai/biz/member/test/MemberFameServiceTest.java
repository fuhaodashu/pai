package com.pai.biz.member.test;

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
import com.pai.biz.member.api.model.MemberFameBean;
import com.pai.biz.member.api.service.MemberFameService;

/**
 * 测试类，。
 * @author FU_HAO
 *
 */
@ContextConfiguration({"classpath:conf/member-server.xml"})
public class MemberFameServiceTest extends BaseTestCase{
	
	@Resource
    private IdGenerator idGenerator;
    
    @Resource
    private MemberFameService memberFameService;
    
    @Test
	public void listMemberFameServiceTest(){
		Map<String, Object> map = new HashMap<String, Object>();
		BaseResponse<ResPage<MemberFameBean>> baseResponse = memberFameService.listMemberFameService(map, 1, 10);
		System.out.println(JSON.toJSONString(baseResponse, true));
	}
}
