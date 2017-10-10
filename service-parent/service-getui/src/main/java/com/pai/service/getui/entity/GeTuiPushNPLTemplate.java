package com.pai.service.getui.entity;

import java.io.Serializable;

public class GeTuiPushNPLTemplate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String notyIcon;// 通知栏图标
	private String notyTitle;//通知标题
	private String notyContent;// 通知栏内容
	private boolean cleared;// 通知栏是否可以清除（默认是）
	private boolean belled;// 是否响铃（默认是）
	private boolean vibrationed;// 是否震动（默认是）
	private String popTitle;// 弹出框标题
	private String popContent;// 弹出框内容
	private String popImage;// 弹出框图标
	private String popButton1;// 弹出框左边按钮名称
	private String popButton2;// 弹出框右边按钮名称
	private String loadIcon;// 下载图标
	private String loadTitle;// 下载标题
	private String loadUrl;// 下载地址
	private boolean autoInstall;// 是否自动安装
	private boolean actived;// 安装完成后是否自动启动应用程序
	private String androidMark;// 包名，比如：com.sina.weibo
	private String symbianMark;// UID，比如：0x27000000
	private String iphoneMark;// URL Schema，用于唤醒对应的IOS应用

	public String getNotyIcon() {
		return notyIcon;
	}

	public void setNotyIcon(String notyIcon) {
		this.notyIcon = notyIcon;
	}

	public String getNotyContent() {
		return notyContent;
	}

	public void setNotyContent(String notyContent) {
		this.notyContent = notyContent;
	}

	public boolean isCleared() {
		return cleared;
	}

	public void setCleared(boolean cleared) {
		this.cleared = cleared;
	}

	public boolean isBelled() {
		return belled;
	}

	public void setBelled(boolean belled) {
		this.belled = belled;
	}

	public boolean isVibrationed() {
		return vibrationed;
	}

	public void setVibrationed(boolean vibrationed) {
		this.vibrationed = vibrationed;
	}

	public String getPopTitle() {
		return popTitle;
	}

	public void setPopTitle(String popTitle) {
		this.popTitle = popTitle;
	}

	public String getPopContent() {
		return popContent;
	}

	public void setPopContent(String popContent) {
		this.popContent = popContent;
	}

	public String getPopImage() {
		return popImage;
	}

	public void setPopImage(String popImage) {
		this.popImage = popImage;
	}

	public String getPopButton1() {
		return popButton1;
	}

	public void setPopButton1(String popButton1) {
		this.popButton1 = popButton1;
	}

	public String getPopButton2() {
		return popButton2;
	}

	public void setPopButton2(String popButton2) {
		this.popButton2 = popButton2;
	}

	public String getLoadIcon() {
		return loadIcon;
	}

	public void setLoadIcon(String loadIcon) {
		this.loadIcon = loadIcon;
	}

	public String getLoadTitle() {
		return loadTitle;
	}

	public void setLoadTitle(String loadTitle) {
		this.loadTitle = loadTitle;
	}

	public String getLoadUrl() {
		return loadUrl;
	}

	public void setLoadUrl(String loadUrl) {
		this.loadUrl = loadUrl;
	}

	public String getAndroidMark() {
		return androidMark;
	}

	public void setAndroidMark(String androidMark) {
		this.androidMark = androidMark;
	}

	public String getSymbianMark() {
		return symbianMark;
	}

	public void setSymbianMark(String symbianMark) {
		this.symbianMark = symbianMark;
	}

	public String getIphoneMark() {
		return iphoneMark;
	}

	public void setIphoneMark(String iphoneMark) {
		this.iphoneMark = iphoneMark;
	}

	public boolean isAutoInstall() {
		return autoInstall;
	}

	public void setAutoInstall(boolean autoInstall) {
		this.autoInstall = autoInstall;
	}

	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	public String getNotyTitle() {
		return notyTitle;
	}

	public void setNotyTitle(String notyTitle) {
		this.notyTitle = notyTitle;
	}
}
