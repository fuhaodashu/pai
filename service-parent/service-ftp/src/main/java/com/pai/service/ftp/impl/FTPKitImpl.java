package com.pai.service.ftp.impl;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import com.pai.service.ftp.FTPKit;

public class FTPKitImpl implements FTPKit {

	private static Logger log = Logger.getLogger(FTPKitImpl.class);
	private static String ROOT = "/";
	private FTPClient ftp;
	
    	
	public FTPClient getFtp() {
		return ftp;
	}
	public FTPKitImpl(String host, int port, String user, String pwd){
		ftp = new FTPClient();
		try {
			int reply;
			ftp.setConnectTimeout(2000);
			ftp.setDataTimeout(5000);
			ftp.connect(host, port);
			ftp.login(user, pwd);
			
			reply = ftp.getReplyCode();
			if(!FTPReply.isPositiveCompletion(reply)){
				ftp.disconnect();
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
	}
	
	@Override
	public boolean upload(File src, String dst) throws Exception {
		dst = dst.indexOf("/") == 0 ? dst : "/" + dst;
		resolveExpectedDirectory(dst, ftp);
		return ftp.storeFile(dst, new FileInputStream(src));
	}
	
	private void resolveExpectedDirectory(String dst, FTPClient ftp) throws Exception{
		ftp.changeWorkingDirectory(ROOT);
		String path = dst.substring(0, dst.lastIndexOf("/"));
		path = path.indexOf("/") == 0 ? path : ("/" + path);
		String[] dirs = path.split("/");
		
		if(dirs.length == 0){
			dirs = new String[]{"."};
		}else{
			dirs[0] = ".";
		}
		
		boolean cd = false;
		for(String dir : dirs){
			cd = ftp.changeWorkingDirectory(dir);
			if(!cd){
				ftp.makeDirectory(dir);
				ftp.changeWorkingDirectory(dir);
			}
		}
		ftp.changeWorkingDirectory(ROOT);
	}

	@Override
	public boolean rename(String src, String dst) throws Exception {
		ftp.changeWorkingDirectory(ROOT);
		return ftp.rename(src, dst);
	}

	@Override
	public boolean delete(String dst) throws Exception {
		ftp.changeWorkingDirectory(ROOT);
		return ftp.deleteFile(dst);
	}

	@Override
	public void discard() {
		try {
			ftp.logout();
			ftp.disconnect();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
