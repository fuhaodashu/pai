package com.pai.biz.auth.listener;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.pai.biz.auth.event.LoginEvent;
import com.pai.biz.auth.persistence.entity.AuthUserLoginLogPo;
import com.pai.biz.auth.persistence.entity.AuthUserPo;
import com.pai.biz.auth.repository.AuthResourcesRepository;
import com.pai.biz.auth.repository.AuthUserLoginLogRepository;

@Component
public class LoginListener implements ApplicationListener<LoginEvent> {
	
	@Resource
	private AuthUserLoginLogRepository authUserLoginLogRepository;
	@Resource
	private AuthResourcesRepository authResourcesRepository;
	
	@Override
	public void onApplicationEvent(LoginEvent event) {
		if(event != null){
			AuthUserPo authUserPo = event.getUser();
			String userId = authUserPo.getId();
			String ip = event.getIp();
			AuthUserLoginLogPo authUserLoginLogPo = new AuthUserLoginLogPo();
			authUserLoginLogPo.setUserId(userId);
			authUserLoginLogPo.setCreateTime(new Date());
			authUserLoginLogPo.setIp(ip);
			authUserLoginLogRepository.newInstance(authUserLoginLogPo).save();
		}
	}

}
