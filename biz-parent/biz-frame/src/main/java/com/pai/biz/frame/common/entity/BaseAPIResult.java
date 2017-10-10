package com.pai.biz.frame.common.entity;


import java.io.Serializable;

import com.pai.base.core.util.string.StringUtils;

/**
 * 状态编码
 *
 */
@SuppressWarnings("serial")
public class BaseAPIResult implements Serializable{
	/**
	 * 游客ID
	 */	
	private String vid;
	/**
	 * 状态码
	 */
	private String statusCode;
	/**
	 * 返回信息
	 */
	private String msg;

	public BaseAPIResult() {
		this.vid = "";
		this.statusCode="200";
		this.msg="";
	}
	
	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = StringUtils.get(statusCode);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = StringUtils.get(msg);
	}
	
}
