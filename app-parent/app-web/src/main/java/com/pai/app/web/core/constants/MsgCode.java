package com.pai.app.web.core.constants;

public enum MsgCode {
	LOGIN("200",""),
	CAPTCHA("400","验证码错误"),
	LOGINERROR("300","用户名或密码错误"),
	PARAMERROR("900","参数异常");
	String code;
	String msg;
	private MsgCode(String code, String msg){
		this.code = code;
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
