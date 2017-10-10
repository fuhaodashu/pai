package com.pai.service.image.jms.handle;

import org.springframework.stereotype.Service;

import com.pai.base.core.helper.SpringHelper;
import com.pai.service.image.ICloudUploadGif;
import com.pai.service.image.entity.CloudUploadGif;
import com.pai.service.mq.jms.JmsHandler;

@Service
public class CloudUploadGifHandler  implements JmsHandler<CloudUploadGif>{

	@Override
	public String getMsgType() {
		return CloudUploadGif.MSG_TYPE;
	}
	@Override
	public void execute(CloudUploadGif vo) {
		
		CloudUploadGif msg = (CloudUploadGif)vo;
		//调用云存储接口
		
		ICloudUploadGif iCloudUploadGif = SpringHelper.getBean(ICloudUploadGif.class);
		if(iCloudUploadGif!=null){
			iCloudUploadGif.uploadGif(msg.getData(), msg.getFileName(), msg.getSize(), msg.getContentType());				
		}			
	}



}
