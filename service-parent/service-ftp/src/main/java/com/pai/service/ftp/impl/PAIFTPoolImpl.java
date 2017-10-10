package com.pai.service.ftp.impl;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

import com.pai.service.ftp.PAIFTPool;
import com.pai.service.ftp.common.PAIFTPClient;

/**
 *   PAIFTP连接池
 *   @author Suoron
 *   @since 2015-10-22  
 * 
 */

public class PAIFTPoolImpl implements PAIFTPool<PAIFTPClient>{
	
	   private GenericObjectPool<PAIFTPClient> ftPool = null;  
       	    
	    public PAIFTPoolImpl(GenericObjectPool.Config poolConfig, PoolableObjectFactory factory)    
	    {  
	    	  this.ftPool = new GenericObjectPool<PAIFTPClient>(factory, poolConfig);   
	    }  
	      
	    public PAIFTPClient getResource(){  
	        try{  	        	
	            return this.ftPool.borrowObject();  
	        }catch(Exception e){  
	            e.printStackTrace();  
	            return null;  
	        }  
	    }  
	      
	    public void returnResource(PAIFTPClient resource){  
	        try {  
	            this.ftPool.returnObject(resource);  
	        }catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	      
	    public void destroy(){  
	        try{  
	            this.ftPool.close();    
	        }catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	      
	    public int inPoolSize(){  
	        try{  
	            return this.ftPool.getNumIdle();  
	        }catch(Exception e){  
	            e.printStackTrace();  
	            return 0;  
	        }  
	    }  
	      
	    public int borrowSize(){  
	        try{  
	            return this.ftPool.getNumActive();  
	        }catch(Exception e){  
	            e.printStackTrace();  
	            return 0;  
	        }  
	    }       
}
