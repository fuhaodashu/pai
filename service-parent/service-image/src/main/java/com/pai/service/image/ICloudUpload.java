package com.pai.service.image;

import java.util.Date;

public interface ICloudUpload {
	/**
	 * 上传图片
	 * @param filePath
	 * @param fileName
	 * @param categoryDir
	 * @return
	 */
	public CloudUploadResult upload(String filePath,String fileName,String categoryDir);
	
	/**
	 * 上传图片或其它类型文件，contentType使用 ContentTypeConstants常量
	 * @param filePath
	 * @param fileName
	 * @param categoryDir
	 * @param contentType
	 * @return
	 */
	public CloudUploadResult upload(String filePath,String fileName,String categoryDir,String contentType);
	
	/**
	 * 通过签名生成防盗链的URL, 默认有效时间30分钟
	 * @param key
	 * @return
	 */
	public String getUrl(String key) ;

	/**
	 * 通过签名生成防盗链的URL, 有效时间date
	 * @param key
	 * @param date
	 * @return
	 */
	public String getUrl(String key, Date date);
}
