package com.pai.service.image.aliyunoss;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.pai.base.core.util.ConfigHelper;
import com.pai.base.core.util.FileUtils;
import com.pai.base.core.util.string.StringUtils;
import com.pai.service.image.CloudUploadResult;
import com.pai.service.image.ICloudUpload;
import com.pai.service.image.constants.ContentTypeConstants;
import com.pai.service.image.constants.UploadType;
import com.pai.service.image.utils.ImageUtil;

@Service
@Scope("singleton")
public class AliyunossUpload implements ICloudUpload {

	private String ossBucketBame;
	private String ossAccesId;
	private String ossAccessKey;
	private String ossFileUrl;
	private boolean ossSign;

	private boolean isInit;
	private String uploadType;

	public CloudUploadResult upload(String filePath, String fileName,
			String categoryDir) {
		return upload(filePath, fileName, categoryDir, getContentType(fileName));
	}
   
	public CloudUploadResult upload(String filePath, String fileName, String categoryDir,String upLoadType) {
		// TODO Auto-generated method stub
		String contentType = getContentType(fileName);
		this.uploadType = upLoadType;
		init();
		// 使用默认的OSS服务器地址创建OSSClient对象。
		OSSClient ossClient = new OSSClient(ossAccesId, ossAccessKey);

		CloudUploadResult result = new CloudUploadResult();
		
		String relativePath = categoryDir + "/"+ fileName;
		String absolutePath = filePath + fileName;
		
		String url = uploadFile(ossClient, ossBucketBame, relativePath, absolutePath,contentType);
				
		if(ossSign){
			result.setUrl(url);
			result.setType(CloudUploadResult.RETURN_KEY);	
		}else{
			result.setUrl(ossFileUrl + "/" + url);
			result.setType(CloudUploadResult.RETURN_URL);
		}		
		result.setMediaType(contentType);
		return result;
	}

	private void init() {
			ossAccesId = ConfigHelper.getInstance().getParamValue(
					"OSS_ACCESS_ID");
			ossAccessKey = ConfigHelper.getInstance().getParamValue(
					"OSS_ACCESS_KEY");
			ossSign = ConfigHelper.getInstance().getBool("OSS_IS_SIGN");
			
			if(this.uploadType.equals(UploadType.GLFILE)){
				ossBucketBame = ConfigHelper.getInstance().getParamValue(
						"OSS_GLFILE_BUCKET_NAME");
				ossFileUrl = ConfigHelper.getInstance().getParamValue(
						"OSS_GLFILE_FILE_URL");
			}else if (this.uploadType.equals(UploadType.IMAGE)) {
				ossBucketBame = ConfigHelper.getInstance().getParamValue(
						"OSS_BUCKET_NAME");
				ossFileUrl = ConfigHelper.getInstance().getParamValue(
						"OSS_FILE_URL");
			}
			/*switch (this.uploadType) {
				case UploadType.GLFILE:
					ossBucketBame = ConfigHelper.getInstance().getParamValue(
							"OSS_GLFILE_BUCKET_NAME");
					ossFileUrl = ConfigHelper.getInstance().getParamValue(
							"OSS_GLFILE_FILE_URL");
					break;
				case UploadType.IMAGE:
					ossBucketBame = ConfigHelper.getInstance().getParamValue(
							"OSS_BUCKET_NAME");
					ossFileUrl = ConfigHelper.getInstance().getParamValue(
							"OSS_FILE_URL");
					break;
				default:
					break;
			}*/
	}

	// 上传文件
	private String uploadFile(OSSClient client, String bucketName, String key,String filename,String contentType) {
		File file = new File(filename);

		ObjectMetadata objectMeta = new ObjectMetadata();
		objectMeta.setContentLength(file.length());
		// 可以在metadata中标记文件类型
		objectMeta.setContentType(contentType);

		InputStream input = null;
		String result = key;
		try {
			input = new FileInputStream(file);
			client.putObject(bucketName, key, input, objectMeta);			
			System.out.println(key);
		} catch (Exception e) {
			result = "";
		}
		
		//上传完成后，删除本地的文件
		if(StringUtils.isNotBlank(result))
		try {
			FileUtils.deleteFile(filename);
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		
		return result;
	}

	// 通过签名生成防盗链的URL, 默认有效时间30分钟
	@Override
	public String getUrl(String key) {
		init();
		OSSClient ossClient = new OSSClient(ossAccesId, ossAccessKey);
		URL url = ossClient.generatePresignedUrl(ossBucketBame, key, new Date((new Date()).getTime() + 1800000));
		return ossFileUrl+url.getFile();
	}

	// 通过签名生成防盗链的URL, 有效时间date
	public String getUrl(String key, Date date) {
		init();
		OSSClient ossClient = new OSSClient(ossAccesId, ossAccessKey);
		URL url = ossClient.generatePresignedUrl(ossBucketBame, key, date);
		return ossFileUrl+url.getFile();
	}
	//获取文件类型
	private String getContentType(String fileName){
		String extName = fileName.substring(fileName.lastIndexOf("."));
		if(ImageUtil.isImageFile(extName)){
			return ContentTypeConstants.IMAGE;
		}
		extName = extName.replace(".", "");
		extName = extName.toLowerCase();
		 if(extName.equals("bmp"))
			 return ContentTypeConstants.BMP;
	     if(extName.equals("gif"))
	        return ContentTypeConstants.GIF;
	     if(extName.equals("html")||extName.equals("htm"))
	        return ContentTypeConstants.HTML;
	     if(extName.equals("txt"))
	        return ContentTypeConstants.TXT;
	     if(extName.equals("vsd"))
	        return ContentTypeConstants.VSD;
	     if(extName.equals("pptx")|| extName.equals("ppt"))
	        return ContentTypeConstants.PPTX;
	     if(extName.equals("docx")||extName.equals("doc"))
	        return ContentTypeConstants.DOCX;
	     if(extName.equals("xml"))
	    	 return ContentTypeConstants.XML;
	     if(extName.equals("xls"))
	    	 return ContentTypeConstants.XLS;
	     if(extName.equals("APK"))
	    	 return ContentTypeConstants.APK;
	     if(extName.equals("exe"))
	    	 return ContentTypeConstants.EXE;
	     if(extName.equals("class"))
	    	 return ContentTypeConstants.CLASS;
	     if(extName.equals("css"))
	    	 return ContentTypeConstants.CSS;
	     return ContentTypeConstants.ALL;
	}
}
