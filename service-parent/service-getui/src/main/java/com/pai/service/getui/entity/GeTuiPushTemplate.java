package com.pai.service.getui.entity;

import java.io.Serializable;

public class GeTuiPushTemplate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String transmissionContent;//透传内容
	private int transmissionType;//启动类型
	private String title;//标题
	private String text;//内容
	private String logo;//图标
	private String logoUrl;//网络图片
	private String url;//打开连接
	private boolean isRing;//是否响铃
	private boolean isVibrate;//是否振动
	private boolean isClearable;//是否可清除
	public String getTransmissionContent() {
		return transmissionContent;
	}
	public void setTransmissionContent(String transmissionContent) {
		this.transmissionContent = transmissionContent;
	}
	public int getTransmissionType() {
		return transmissionType;
	}
	public void setTransmissionType(int transmissionType) {
		this.transmissionType = transmissionType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean getIsRing() {
		return isRing;
	}
	public void setIsRing(boolean isRing) {
		this.isRing = isRing;
	}
	public boolean getIsVibrate() {
		return isVibrate;
	}
	public void setIsVibrate(boolean isVibrate) {
		this.isVibrate = isVibrate;
	}
	public boolean getIsClearable() {
		return isClearable;
	}
	public void setIsClearable(boolean isClearable) {
		this.isClearable = isClearable;
	}
}
