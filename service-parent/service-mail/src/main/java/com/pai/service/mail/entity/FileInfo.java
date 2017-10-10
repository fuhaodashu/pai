package com.pai.service.mail.entity;

import java.io.File;
import java.util.Arrays;

import com.pai.base.core.constants.ImageType;
import com.pai.base.core.util.date.DateConverter;

public class FileInfo {	
	private String fileName = "";
	private String filePath = "";
	private String lastModifiedTime = "";	
	private boolean isHidden = false;
	private boolean isDir = false;	
	private boolean hasFile = false;
	private long fileSize = 0L;
	private String fileSizeDesc = "";	
	private boolean isPhoto = false;
	private String fileType = "";
	
	public FileInfo(File file){
		fileName = file.getName();
		lastModifiedTime = DateConverter.toString(file.lastModified());
		filePath = file.getAbsolutePath().replaceAll("[////]", "/");
		isHidden = file.isHidden();
		
		if(file.isDirectory()) {
			isDir = true;
			hasFile = (file.listFiles() != null);
			fileSize = 0L;
			fileSizeDesc = "0byte";
			isPhoto = false;
			fileType = "DIRECTORY";
		} else if(file.isFile()){			 
			isDir = false;
			hasFile = false;
			fileSize = file.length();
            if(fileSize/1024/1024/1024 >0){
            	fileSizeDesc =fileSize / 1024/1024/1024 + "G";
            }else if(fileSize/1024/1024 >0){
            	fileSizeDesc = fileSize / 1024/1024 + "M";
            }else if(fileSize/1024 >0){
            	fileSizeDesc = fileSize / 1024 + "K";
            }else{
            	fileSizeDesc = fileSize+"byte";
            }						
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			isPhoto = Arrays.<String>asList(ImageType.toArray()).contains(fileExt);
			fileType = fileExt;
		}		
	}
	
	public String getFileName() {
		return fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public String getLastModifiedTime() {
		return lastModifiedTime;
	}
	public boolean isHidden() {
		return isHidden;
	}
	public boolean isDir() {
		return isDir;
	}
	public boolean isHasFile() {
		return hasFile;
	}
	public long getFileSize() {
		return fileSize;
	}	
	public String getFileSizeDesc() {
		return fileSizeDesc;
	}
	public boolean isPhoto() {
		return isPhoto;
	}
	public String getFileType() {
		return fileType;
	}

}
