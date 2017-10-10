package com.pai.service.image.entity;

import java.io.InputStream;

import com.pai.base.api.model.IMsgVo;

public class CloudUploadGif implements IMsgVo {
	
	private static final long serialVersionUID = -7816590714824963692L;

	public final static String MSG_TYPE="cloudUploadGif";
	
	private String fileName;
	private Integer size;
	private String contentType;
	private byte[] data;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	@Override
	public String getMsgType() {
		return MSG_TYPE;
	}
}
