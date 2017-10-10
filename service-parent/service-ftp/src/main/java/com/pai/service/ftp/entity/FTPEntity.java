package com.pai.service.ftp.entity;


public class FTPEntity implements java.io.Serializable {
		
    private String host;  
    private int port;  
    private String user;  
    private String password;  
    private String passiveModeConf;
    
    public String toString(){
    	
    	StringBuffer sb = new StringBuffer();
    	sb.append("host:"+host+"\n");
    	sb.append("port:"+port+"\n");
    	sb.append("user:"+user+"\n");
    	sb.append("password:"+password+"\n");
    	sb.append("passiveModeConf:"+passiveModeConf+"\n");
    	
    	return sb.toString();        
    }
    
    public FTPEntity(String host,int port,String user,String password,String passiveModeConf){
        this.host = host;  
        this.port = port;  
        this.user = user;  
        this.password = password;  
        this.passiveModeConf = passiveModeConf;  
    }
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassiveModeConf() {
		return passiveModeConf;
	}
	public void setPassiveModeConf(String passiveModeConf) {
		this.passiveModeConf = passiveModeConf;
	}
    
}
