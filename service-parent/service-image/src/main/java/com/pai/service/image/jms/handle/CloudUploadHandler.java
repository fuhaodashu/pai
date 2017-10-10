package com.pai.service.image.jms.handle;

import org.springframework.stereotype.Service;

import com.pai.base.core.helper.SpringHelper;
import com.pai.service.image.ICloudUpload;
import com.pai.service.image.entity.CloudUploadMsg;
import com.pai.service.mq.jms.JmsHandler;

@Service
public class CloudUploadHandler  implements JmsHandler<CloudUploadMsg>{

	@Override
	public String getMsgType() {
		return CloudUploadMsg.MSG_TYPE;
	}
	@Override
	public void execute(CloudUploadMsg vo) {
		
		CloudUploadMsg msg = (CloudUploadMsg)vo;
		//调用云存储接口
		ICloudUpload  iCloudUpload = SpringHelper.getBean(ICloudUpload.class);
		if(iCloudUpload!=null){
			iCloudUpload.upload(msg.getFullDir(), msg.getFileName(), msg.getCategoryDir(),msg.getUpLoadType());				
		}			
	}



}
