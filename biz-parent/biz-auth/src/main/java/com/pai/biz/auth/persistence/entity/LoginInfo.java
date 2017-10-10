package com.pai.biz.auth.persistence.entity;

import com.pai.base.api.annotion.validate.NotBlank;
import com.pai.base.api.constants.Bool;
import com.pai.base.core.entity.CommonResult;
import com.pai.base.core.helper.PasswordHelper;

public class LoginInfo {
	//验证码
	@NotBlank(fieldName="验证码")
	private String captchaCode;
	//用户名
	@NotBlank(fieldName="用户名")
	private String userName;
	//密码
	@NotBlank(fieldName="密码")
	private String password;
	//是否登录操作
	private String isLogin;

	public boolean isCheck() {
		if (userName==null||password==null||"".equals(userName)||"".equals(password)){
			return false;
		}else {
			return  true;
		}
	}
	
	public boolean isLogin() {
		if(isLogin!=null && isLogin.equals(Bool.Y.name())){
			return true;
		}
		return false;
	}
	
	public String getIsLogin() {
		return isLogin;
	}
	
	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}

	private CommonResult commonResult = null;
	
	public void reset(){
		isLogin = Bool.N.name();
		captchaCode = "";
		password = "";
	}
	
	public String getCaptchaCode() {
		return captchaCode;
	}
	
	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getEncryptPassword() {
		return PasswordHelper.getEncryptPassword(userName+password).substring(1);
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public CommonResult getCommonResult() {
		return commonResult;
	}

	public void setCommonResult(CommonResult commonResult) {
		this.commonResult = commonResult;
	}
	
}
