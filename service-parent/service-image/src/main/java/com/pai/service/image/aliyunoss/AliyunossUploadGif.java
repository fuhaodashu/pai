package com.pai.service.image.aliyunoss;

import java.io.InputStream;

import org.apache.activemq.util.ByteArrayInputStream;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.pai.service.image.ICloudUploadGif;
import com.pai.base.core.util.ConfigHelper;

@Service
@Scope("singleton")
public class AliyunossUploadGif implements ICloudUploadGif {

	private String ossBucketBame;
	private String ossAccesId;
	private String ossAccessKey;
	private String ossFileUrl;
	private boolean ossSign;

	private String uploadType;

	private void init() {
			ossAccesId = ConfigHelper.getInstance().getParamValue(
					"OSS_ACCESS_ID");
			ossAccessKey = ConfigHelper.getInstance().getParamValue(
					"OSS_ACCESS_KEY");
			ossBucketBame = ConfigHelper.getInstance().getParamValue(
					"OSS_BUCKET_NAME");
			ossFileUrl = ConfigHelper.getInstance().getParamValue(
					"OSS_FILE_URL");
	}


	@Override
	public void uploadGif(byte[] data, String fileName, Integer size, String contentType) {
		init();
		OSSClient ossClient = new OSSClient(ossAccesId, ossAccessKey);
		ObjectMetadata objectMeta = new ObjectMetadata();
		objectMeta.setContentLength(size);
		objectMeta.setContentType(contentType);
		ossClient.putObject(ossBucketBame, fileName, new ByteArrayInputStream(data) , objectMeta);	
	}
}
