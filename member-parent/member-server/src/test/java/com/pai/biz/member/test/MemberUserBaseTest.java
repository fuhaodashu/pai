package com.pai.biz.member.test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;

import com.alibaba.fastjson.JSON;
import com.pai.base.api.response.BaseResponse;
import com.pai.base.api.response.ResPage;
import com.pai.base.api.service.IdGenerator;
import com.pai.base.core.test.BaseTestCase;
import com.pai.biz.member.api.model.MemberUserBean;
import com.pai.biz.member.api.service.MemberUserService;
/**
 * 测试基类，。
 * 其下的测试类均继承该子类
 * @author FU_HAO
 *
 */
@ContextConfiguration(locations = "classpath:/conf/member-server.xml")
public class MemberUserBaseTest extends BaseTestCase{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
    protected IdGenerator idGenerator;
	
	@Resource
	private MemberUserService memberUserService;
	
	@Test
	public void listMemberUserServiceTest(){
		Map<String, Object> map = new HashMap<String, Object>();
		BaseResponse<ResPage<MemberUserBean>> baseResponse = memberUserService.listMemberUserService(map, 1, 10);
		System.out.println(JSON.toJSONString(baseResponse, true));
	}
	
}
