package com.pai.app.api;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pai.base.api.response.BaseResponse;
import com.pai.base.api.response.ResPage;
import com.pai.biz.member.api.model.MemberUserBean;
import com.pai.biz.member.api.service.MemberUserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/pai")
@Api(value = "/pai", basePath = "/pai", description = "pai测试")
public class TestController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private MemberUserService memberUserService;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET) 
	@ResponseBody
	@ApiOperation(value = "/test", notes = "测试", response = BaseResponse.class, httpMethod = "GET")
	public BaseResponse test(@RequestParam("name") String name){
		System.out.println(name);
		return BaseResponse.buildSuccess(); 
	}
	
	@RequestMapping(value = "/a", method = RequestMethod.POST) 
	@ResponseBody
	@ApiOperation(value = "/a", notes = "测试", response = BaseResponse.class, httpMethod = "POST")
	public BaseResponse testObj(@RequestBody A a){
		System.out.println(a.toString());
		logger.info(a.toString());
		return BaseResponse.buildSuccess(); 
	}
	
	@RequestMapping(value = "/b", method = RequestMethod.GET) 
	@ResponseBody
	@ApiOperation(value = "/b", notes = "测试", response = BaseResponse.class, httpMethod = "GET")
	public BaseResponse b(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
		BaseResponse<ResPage<MemberUserBean>> baseResponse = memberUserService.listMemberUserService(null, pageNo, pageSize);
		return baseResponse; 
	}
}
