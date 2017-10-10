package com.pai.service.ftp.common;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.pool.BasePoolableObjectFactory;

import com.pai.service.ftp.entity.FTPEntity;

/**
 *   PAIFTP连接池对象
 *   @author Suoron
 *   @since 2015-10-22  
 * 
 */

public class FTPPoolableObjectFactory extends BasePoolableObjectFactory{      
    private FTPEntity entity = null;  

    public FTPPoolableObjectFactory(FTPEntity entity){
    	this.entity = entity;    	
    }
    
    public FTPPoolableObjectFactory(String host,int port,String user,String password,String passiveModeConf){
    	entity = new FTPEntity(host,port,user,password, passiveModeConf);
    }  
      
    @Override  
    public Object makeObject() throws Exception {  
            PAIFTPClient ftpClient = new PAIFTPClient();  
            ftpClient.connect(entity.getHost(), entity.getPort());              
            ftpClient.setControlKeepAliveTimeout(300);   //set timeout to 5 minutes  
            ftpClient.login(entity.getUser(), entity.getPassword());  
            boolean passiveMode = false;  
            
            if (entity.getPassiveModeConf() == null || Boolean.parseBoolean(entity.getPassiveModeConf()) == true){  
                    passiveMode = true;  
           }  
           if (passiveMode) {
                    ftpClient.enterLocalPassiveMode();  
           }  
           ftpClient.setFileType(FTP.BINARY_FILE_TYPE);  
           return ftpClient;  
    }  
  
    @Override  
    public void destroyObject(Object obj) throws Exception {  
        if(obj instanceof PAIFTPClient){  
            PAIFTPClient ftpClient=(PAIFTPClient)obj;  
            if(!ftpClient.isConnected()) return ;  
            try{  
                ftpClient.disconnect();  
            }catch(Exception e){  
                e.printStackTrace();  
            }  
        }  
    }  
    @Override  
    public boolean validateObject(Object obj) {  
        if(obj instanceof PAIFTPClient){  
            PAIFTPClient ftpClient=(PAIFTPClient)obj;  
            try{  
                return ftpClient.isConnected();  
            }catch(Exception e){  
                return false;  
            }  
        }  
        return false;  
    }  
} 

