package com.pai.service.image.entity;

import java.io.File;
import java.util.Hashtable;

import com.pai.base.core.util.entity.FileInfo;
import com.pai.service.image.constants.FileConstants;


public class FileManagerEntity extends FileInfo{	
	
	public FileManagerEntity(File file){
		super(file);
	}
	
	public Hashtable<String, Object> toHashtable(){
		Hashtable<String, Object> hash = new Hashtable<String, Object>();
		hash.put(FileConstants.FILE_NAME, getFileName());
		hash.put(FileConstants.IS_DIR, isDir());
		hash.put(FileConstants.HAS_FILE, isHasFile());
		hash.put(FileConstants.FILE_SIZE, getFileSize());
		hash.put(FileConstants.IS_PHOTO, isPhoto());
		hash.put(FileConstants.FILE_TYPE, getFileType());
		hash.put(FileConstants.DATE_TIME, getLastModifiedTime());
		return hash;
	}

	
	
}
