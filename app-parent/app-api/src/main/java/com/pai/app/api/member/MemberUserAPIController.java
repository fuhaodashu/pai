package com.pai.app.api.member;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pai.app.api.request.MemberRequest;
import com.pai.base.api.response.BaseResponse;
import com.pai.biz.member.api.service.MemberUserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/member")
@Api(value = "/api/member", basePath = "/api/member", description = "会员")
public class MemberUserAPIController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private MemberUserService memberUserService;
	
	@RequestMapping(value = "/regieter", method = RequestMethod.POST) 
	@ResponseBody
	@ApiOperation(value = "/regieter", notes = "注册", response = BaseResponse.class, httpMethod = "POST")
	public BaseResponse regieter(@RequestBody MemberRequest request) {
		
		return null;
	}
}
