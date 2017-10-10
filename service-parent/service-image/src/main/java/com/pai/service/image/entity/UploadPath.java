package com.pai.service.image.entity;

import java.io.File;
import java.util.UUID;

import com.pai.base.api.helper.IConfigHelper;
import com.pai.base.core.helper.SpringHelper;
import com.pai.base.core.util.ServletContextHelper;
import com.pai.base.core.util.date.DateConverter;
import com.pai.base.core.util.string.StringUtils;
import com.pai.service.image.constants.PropertiesConstants;

public class UploadPath{
	
	private String categoryDir;			//自定义目录路径
	private String originFileName;		//原始文件名
	private String fileName;					//保存的文件名
	private String relativeDir;				//相对路径
		
	IConfigHelper configHelper = SpringHelper.getBean(IConfigHelper.class); 
	
	public UploadPath(String categoryDir_, String fileName_, boolean isRename_){
		this.categoryDir = categoryDir_;
		this.originFileName = fileName_;
		fileName = originFileName;
		if(isRename_){
			fileName = rename();
		}		
	}

	public String getCategoryDir() {
		return categoryDir;
	}

	public String getRelativeDir() {
		if(StringUtils.isEmpty(relativeDir)){
			StringBuilder sb = new StringBuilder();
			sb.append(File.separator);
			sb.append(configHelper.getParamValue(PropertiesConstants.UPLOAD_ROOT));
			sb.append(File.separator);
			sb.append(configHelper.getParamValue(PropertiesConstants.MEDIA_PATH));		
			sb.append(File.separator);
			sb.append(categoryDir);
			sb.append(File.separator);
			sb.append(DateConverter.nowDateLine());
			sb.append(File.separator);		
			sb.append(DateConverter.nowTimeLine());
			sb.append(File.separator);			
			relativeDir = sb.toString();	
		}
		return relativeDir;
	}

	public String getWebRelativeDir(){
		StringBuilder sb = new StringBuilder();
		sb.append("/");
		sb.append(configHelper.getParamValue(PropertiesConstants.UPLOAD_ROOT));
		sb.append("/");
		sb.append(configHelper.getParamValue(PropertiesConstants.MEDIA_PATH));
		sb.append("/");
		sb.append(categoryDir);
		sb.append("/");
		sb.append(DateConverter.nowDateLine());
		sb.append("/");
		sb.append(DateConverter.nowTimeLine());
		sb.append("/");
		return sb.toString();		
	}
	
	public String getWebRelativePath(){
		StringBuilder sb = new StringBuilder();
		sb.append(getWebRelativeDir());
		sb.append(fileName);
		return sb.toString();		
	}
	
	public String getRelativePath(){
		return getRelativeDir() + fileName;
	}
	
	public String getFullDir() {
		StringBuilder sb = new StringBuilder();
		sb.append(ServletContextHelper.getRealPath());
		sb.append(getRelativeDir());
		return sb.toString();
	}
	
	public String getFullPath(){	
		return getFullDir() + File.separator + fileName;
	}
	
	public String getFullPathAppendPostfix(String postfix){
		return getFullDir() + File.separator + StringUtils.appendNamePostfix(fileName, postfix);
	}
	
	public String getOriginFileName(){
		return originFileName;
	}
	
	public String getFileName(){
		return fileName;
	}

	private String rename() {
		String suffix = StringUtils.getFileExt(fileName);
		//加上UUID重命名
		String newName = UUID.randomUUID().toString();
		
		//加上后缀返回
		return newName + (suffix.equals("") ? "" : "." + suffix);
	}
}
