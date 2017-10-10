package com.pai.service.image;

import javax.servlet.http.HttpServletRequest;

import com.pai.service.image.entity.UploadResult;

public interface IUploadHelper {
	public UploadResult upload(HttpServletRequest request) throws Exception;
}
