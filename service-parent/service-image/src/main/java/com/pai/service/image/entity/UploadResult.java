package com.pai.service.image.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.pai.service.image.CloudUploadResult;

public class UploadResult {
	/**
	 * 成功上传的文件列表
	 */
	private List<String> uploadedFiles = new ArrayList<String>();
	/**
	 * 返回信息
	 */
	private String msg;
	/**
	 * 成功标示：true 成功；false 失败。
	 */
	private boolean flag;
	
	private CloudUploadResult cloudUploadResult;
	
	public List<String> getUploadedFiles() {
		return Collections.unmodifiableList(uploadedFiles);
	}
	public void addUploadFile(String fileRelativePath){
		uploadedFiles.add(fileRelativePath);
	}
	public String getLastFile(){
		if(getUploadedFiles().size()>0){
			return getUploadedFiles().get(getUploadedFiles().size()-1);
		}
		return "";
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public CloudUploadResult getCloudUploadResult() {
		return cloudUploadResult;
	}
	public void setCloudUploadResult(CloudUploadResult cloudUploadResult) {
		this.cloudUploadResult = cloudUploadResult;
	}
	
}
