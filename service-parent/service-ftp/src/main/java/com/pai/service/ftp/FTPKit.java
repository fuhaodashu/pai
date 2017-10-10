package com.pai.service.ftp;

import java.io.File;

import org.apache.commons.net.ftp.FTPClient;

public interface FTPKit {

	/**
	 * 本地文件上传到服务器
	 * @param src	本地文件
	 * @param dst	目标地址
	 * @throws Exception
	 */
	boolean upload(File src, String dst) throws Exception;
	
	/**
	 * 修改文件名称
	 * @param src	远程文件名称
	 * @param dst	远程文件修改后的名称
	 * @throws Exception
	 */
	boolean rename(String src, String dst) throws Exception;
	
	/**
	 * 删除远程文件
	 * @param dst
	 * @return
	 * @throws Exception
	 */
	boolean delete(String dst) throws Exception;
	
	/**
	 * 调用完成其他对象之后，丢弃当前对象，关闭连接
	 * @return
	 * @throws Exception
	 */
	void discard();
	public FTPClient getFtp();
}
