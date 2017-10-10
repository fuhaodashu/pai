package com.pai.service.ftp;

public interface PAIFTPool<F> {		
	
	/**
	 * 获取FTPClient实例
	 */	
    public F getResource();
    
	/**
	 * 将FTPClient实例放回池中
	 * @param resource FTPClient
	 */	    
    public void returnResource(F resource);
    
	/**
	 * 销毁FTP连接池
	 *  
	 */	        
    public void destroy();
    
	/**
	 * 获取空闲连接数
	 *  
	 */    
    public int inPoolSize();
    
    /**
	 * 获取活动连接数
	 *  
	 */        
    public int borrowSize();    
    
}
