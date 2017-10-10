package com.pai.service.ftp;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool.Config;

/**
 *   连接池配置类
 *   @author Suoron
 *   
 */

public class FTPConfig {
	
	
    public static GenericObjectPool.Config getConfig(){
    	
        GenericObjectPool.Config config = new Config();  	          
        config.maxActive=PAIFTPManager.MAX_ACTIVE;
        config.maxWait=PAIFTPManager.MAX_WAitTIME;
        config.whenExhaustedAction = PAIFTPManager.WHEN_EXHAUSTED_GROW;  	   
        
        config.testOnBorrow=true;               
        config.testOnReturn=true;    	                
        return config;
   } 
    
}
