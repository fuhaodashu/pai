package com.pai.service.image;


public interface ICloudUploadGif {
	
	/**
	 * 长传GIF图
	 * @param in
	 * @param fileName
	 * @param size
	 * @param contentType
	 */
	public void uploadGif(byte[] data, String fileName, Integer size, String contentType);
	
}
