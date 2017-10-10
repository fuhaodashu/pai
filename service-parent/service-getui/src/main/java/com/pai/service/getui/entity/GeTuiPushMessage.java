package com.pai.service.getui.entity;

import java.io.Serializable;
import java.util.List;

public class GeTuiPushMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String CID;//clientID
	private boolean offline;//是否离线缓存
	private long offlineExpireTime;//缓存时间
	private int pushNetWorkTyp;//wifi状态要求，1为必须，0为任何网络
	private List<String> AppIdList;//应用id列表
	private List<String> PhoneTypeList;//手机型号列表
	private List<String> ProvinceList;//省份列表
	private List<String> TagList;//标签列表
	private int speed;//推送速度(毫秒)
	public String getCID() {
		return CID;	
	}
	public void setCID(String cID) {
		CID = cID;
	}
	public boolean isOffline() {
		return offline;
	}
	public void setOffline(boolean offline) {
		this.offline = offline;
	}
	public long getOfflineExpireTime() {
		return offlineExpireTime;
	}
	public void setOfflineExpireTime(long offlineExpireTime) {
		this.offlineExpireTime = offlineExpireTime;
	}
	public int getPushNetWorkTyp() {
		return pushNetWorkTyp;
	}
	public void setPushNetWorkTyp(int pushNetWorkTyp) {
		this.pushNetWorkTyp = pushNetWorkTyp;
	}
	public List<String> getAppIdList() {
		return AppIdList;
	}
	public void setAppIdList(List<String> appIdList) {
		AppIdList = appIdList;
	}
	public List<String> getPhoneTypeList() {
		return PhoneTypeList;
	}
	public void setPhoneTypeList(List<String> phoneTypeList) {
		PhoneTypeList = phoneTypeList;
	}
	public List<String> getProvinceList() {
		return ProvinceList;
	}
	public void setProvinceList(List<String> provinceList) {
		ProvinceList = provinceList;
	}
	public List<String> getTagList() {
		return TagList;
	}
	public void setTagList(List<String> tagList) {
		TagList = tagList;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
