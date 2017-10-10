package com.pai.service.ftp;

import com.pai.service.ftp.impl.FTPKitImpl;

public class FTPKitFactory {

	private String host;
	private int port;
	private String user;
	private String pwd;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public static FTPKit newInstance(String host, int port, String user, String pwd){
		return new FTPKitImpl(host, port, user, pwd);
	}
	
	public FTPKit create(){
		return new FTPKitImpl(host, port, user, pwd);
	}
}
