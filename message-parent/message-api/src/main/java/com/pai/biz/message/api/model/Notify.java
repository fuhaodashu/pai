package com.pai.biz.message.api.model;

import java.io.Serializable;
import java.util.Date;

import com.pai.base.api.annotion.doc.AutoDocField;
import com.pai.base.api.annotion.validate.NotBlank;

public class Notify implements Serializable {

	private static final long serialVersionUID = -5225772066248994752L;
	
	//首次id不传值
	private String id;
	
	//不传
	private Date latestNotifyTime;
	
	//不传
	private Integer notifyTimes; 
	
	@NotBlank(fieldName="URL地址")
	@AutoDocField("URL地址")
	private String url;
	
	@NotBlank(fieldName="json参数")
	@AutoDocField("json参数")
	private String paramBody;
	
	@NotBlank(fieldName="请求类型。POST/GET")
	@AutoDocField("请求类型。POST/GET")
	private String notifyType;
	
	@NotBlank(fieldName="成功返回字符串")
	@AutoDocField("成功返回字符串")
	private String successVal;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParamBody() {
		return paramBody;
	}

	public void setParamBody(String paramBody) {
		this.paramBody = paramBody;
	}

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public String getSuccessVal() {
		return successVal;
	}

	public void setSuccessVal(String successVal) {
		this.successVal = successVal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getLatestNotifyTime() {
		return latestNotifyTime;
	}

	public void setLatestNotifyTime(Date latestNotifyTime) {
		this.latestNotifyTime = latestNotifyTime;
	}

	public Integer getNotifyTimes() {
		return notifyTimes;
	}

	public void setNotifyTimes(Integer notifyTimes) {
		this.notifyTimes = notifyTimes;
	}
	
}
