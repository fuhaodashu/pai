package com.pai.biz.message.api.model;

import java.io.Serializable;

import com.pai.base.api.annotion.doc.AutoDocField;
import com.pai.base.api.annotion.validate.NotBlank;
import com.pai.base.api.model.IMsgVo;

public class Message implements IMsgVo, Serializable {

	private static final long serialVersionUID = -6799398214899495970L;

	//回调URL地址。保存消息后，拿到messageId保存在本地，提供dubbo rest地址或者dubbo泛化引用提供查询。
	@NotBlank(fieldName="回调URL地址")
	@AutoDocField("回调URL地址,rest或者dubbo泛化引用")
	private String url;
	
	//json数据格式
	@NotBlank(fieldName="json数据")
	@AutoDocField("json数据")
	private String messageBody;
	
	//监听处理类handler前缀
	@NotBlank(fieldName="监听处理类handler前缀")
	@AutoDocField("监听处理类handler前缀")
	private String msgType;
	
	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@Override
	public String getMsgType() {
		return msgType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
