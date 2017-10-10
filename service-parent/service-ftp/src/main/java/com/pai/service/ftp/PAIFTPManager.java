package com.pai.service.ftp;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.pai.base.core.util.EncryptUtil;
import com.pai.service.ftp.common.FTPPoolableObjectFactory;
import com.pai.service.ftp.common.PAIFTPClient;
import com.pai.service.ftp.entity.FTPEntity;
import com.pai.service.ftp.impl.PAIFTPoolImpl;

public class PAIFTPManager {            
	   
	   public static final int MAX_ACTIVE = 50;                       //最大池容量
	   public static final int MAX_WAitTIME = 15*1000;          //等待的最大毫秒数.
	   public static final int WHEN_EXHAUSTED_GROW = 2;  //每次增加对象数
	   public static final boolean TESTONBORROW = true;   //取出对象时验证(此处设置成验证ftp是否处于连接状态).   
	   public static final boolean TESTONRETURN = true;    //还回对象时验证(此处设置成验证ftp是否处于连接状态).	
	   
	   private static Map<String, PAIFTPool>  pool_list = null;
	   private static Object pool_list_lock = new Object();          //锁定连接池
	  
	   
	   /**
	    *    根据FTP账号信息，创建FTP连接池
	    */
	   public PAIFTPool createPool(FTPEntity entity){
		    String key = EncryptUtil.md5(entity.toString());
		    PAIFTPool pool = null;
		    
		    synchronized (pool_list_lock) {
			     if(pool_list == null){
			    	 pool_list = new ConcurrentHashMap<String, PAIFTPool>();			    	 
			     }
			     pool = pool_list.get(key); 
			     if(pool == null){
			    	 pool = new PAIFTPoolImpl(FTPConfig.getConfig(), new  FTPPoolableObjectFactory(entity));
			    	 pool_list.put(key, pool);
			     }
			}
            return pool;		     
	   }
	   
	   public PAIFTPClient getResource(FTPEntity entity){		   
		   return (PAIFTPClient)getPaiftPool(entity).getResource();
	   }
	   
	    public void returnResource(FTPEntity entity,PAIFTPClient resource){  
	        try {
	        	getPaiftPool(entity).returnResource(resource);
	        }catch (Exception e) {
	            e.printStackTrace();  
	        }
	    }
	      
	    public void destroy(FTPEntity entity){  
	        try{  
	        	getPaiftPool(entity).destroy();
	        	synchronized (pool_list_lock) {
	        		pool_list.remove(EncryptUtil.md5(entity.toString()));
	        	}
	        }catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	      
	    public int inPoolSize(FTPEntity entity){  
	        try{  
	            return getPaiftPool(entity).inPoolSize();  
	        }catch(Exception e){  
	            e.printStackTrace();  
	            return 0;  
	        }  
	    }  
	      
	    public int borrowSize(FTPEntity entity){  
	        try{  
	            return getPaiftPool(entity).borrowSize();  
	        }catch(Exception e){  
	            e.printStackTrace();  
	            return 0;  
	        }  
	    } 	   
	   
	   public PAIFTPManager(int level){
		   
	   }
	   
	   public PAIFTPool getPaiftPool(FTPEntity entity){
		   PAIFTPool pool = null;
		   if(pool_list != null){
			   pool = pool_list.get(EncryptUtil.md5(entity.toString()));			   
		   }else{
			   pool = createPool(entity);
		   }
           return pool;		   
	   }
	   
       static class Singleton{
     	    private static PAIFTPManager manager = new PAIFTPManager(500);
    	    public static PAIFTPManager getInstance(){
    	    	 return Singleton.manager;
    	    } 
       }
}
