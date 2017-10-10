package com.pai.service.image.entity;

import com.pai.base.api.model.IMsgVo;

public class CloudUploadMsg implements IMsgVo{
	public final static String MSG_TYPE="cloudUpload";
	
	private String fullDir;
	private String fileName;
	private String categoryDir;
	private String upLoadType;
	
	@Override
	public String getMsgType() {
		return MSG_TYPE;
	}
	public String getFullDir() {
		return fullDir;
	}
	public void setFullDir(String fullDir) {
		this.fullDir = fullDir;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getCategoryDir() {
		return categoryDir;
	}
	public void setCategoryDir(String categoryDir) {
		this.categoryDir = categoryDir;
	}
	public String getUpLoadType() {
		return upLoadType;
	}
	public void setUpLoadType(String upLoadType) {
		this.upLoadType = upLoadType;
	}
}
