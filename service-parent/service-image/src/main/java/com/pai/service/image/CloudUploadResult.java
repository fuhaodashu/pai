package com.pai.service.image;

public class CloudUploadResult {
	public final static String RETURN_KEY="key";
	public final static String RETURN_URL="url";
	private String type;
	private String url;
	private String localPath;
	private String mediaType;
	private String cloudType;
	private String cloudKey;
	private String cloudPath;
	private int width;
	private int height;
	private String isMain;
	private String cateId;	
	private String isCateMain;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	public String getLocalPath() {
		return localPath;
	}
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getCloudType() {
		return cloudType;
	}
	public void setCloudType(String cloudType) {
		this.cloudType = cloudType;
	}
	public String getCloudKey() {
		return cloudKey;
	}
	public void setCloudKey(String cloudKey) {
		this.cloudKey = cloudKey;
	}
	public String getCloudPath() {
		return cloudPath;
	}
	public void setCloudPath(String cloudPath) {
		this.cloudPath = cloudPath;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getCateId() {
		return cateId;
	}
	public void setCateId(String cateId) {
		this.cateId = cateId;
	}
	public String getIsMain() {
		return isMain;
	}
	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}
	public String getIsCateMain() {
		return isCateMain;
	}
	public void setIsCateMain(String isCateMain) {
		this.isCateMain = isCateMain;
	}
	
	
}
