package com.pai.service.image.jms.handle;

import org.springframework.stereotype.Service;

import com.pai.base.api.model.DefaultMsgVo;
import com.pai.service.mq.jms.JmsHandler;

@Service
public class ConsoleHandler implements JmsHandler<DefaultMsgVo>{

	@Override
	public String getMsgType() {
		return DefaultMsgVo.MSG_TYPE;
	}

	@Override
	public void execute(DefaultMsgVo vo) {
		System.out.println(vo.getMsg());		
	}

}
