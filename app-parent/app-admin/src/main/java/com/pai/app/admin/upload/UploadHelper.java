package com.pai.app.admin.upload;

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.fusesource.hawtbuf.ByteArrayInputStream;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pai.app.web.core.framework.constants.PropertiesConstants;
import com.pai.app.web.core.framework.web.util.ImageUtil;
import com.pai.base.api.image.ImageWatermarkService;
import com.pai.base.core.helper.SpringHelper;
import com.pai.base.core.image.ImageScaleHelper;
import com.pai.base.core.util.ConfigHelper;
import com.pai.base.core.util.FileUtils;
import com.pai.base.core.util.RequestUtil;
import com.pai.base.core.util.string.StringUtils;
import com.pai.service.image.CloudUploadResult;
import com.pai.service.image.constants.ContentTypeConstants;
import com.pai.service.image.constants.UploadType;
import com.pai.service.image.entity.CloudUploadMsg;
import com.pai.service.image.entity.UploadPath;
import com.pai.service.image.entity.UploadResult;
import com.pai.service.mq.JmsService;
/**
 * 文件上传帮助类
 */
public class UploadHelper {		
	private static final String OVERLENGTH = "overlength";
	private static final String SUCCESS = "Success";

	public static UploadResult upload(HttpServletRequest request) throws Exception{
		UploadResult uploadResult = new UploadResult();
						
		if (!(request instanceof MultipartHttpServletRequest)) {
			throw new Exception("Request is not a MultipartHttpServletRequest");
		}
		//放置的分类目录
		String categoryDir = RequestUtil.getParameterNullSafe(request, "categoryDir");
		//是否重命名
		Boolean isRename =RequestUtil.getBooleanParameter(request, "isRename");
        // 获得目录路径				
		categoryDir = StringUtils.isEmpty(categoryDir)?"temp":categoryDir;		
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;		
		
		//自定义图片大小。全局是10m以下。本参数以 KB 为单位。
		Integer customKb = RequestUtil.getIntegerParameter(request, "customKb");
		boolean isCustomSizeOver= false;
		
	    //获得文件：   
		for (Iterator<String> it = multipartRequest.getFileNames(); it.hasNext();) {  
	        String key = it.next();  
	        MultipartFile multipartFile = multipartRequest.getFile(key);  
	        if(customKb!=null){
	        	long size = multipartFile.getSize();
	        	double sizeKb = (double) (size / 1024);
	        	if(sizeKb>customKb.doubleValue()){
	        		isCustomSizeOver = true;
	        		break;
	        	}
			}
			// 获得文件名
		    String fileName = multipartFile.getOriginalFilename();
		    fileName = new String(fileName.getBytes(),"UTF-8");		    
	    	UploadPath uploadPath = new UploadPath(categoryDir,fileName,isRename);
	        
			String fileDir = uploadPath.getFullDir();
			//创建目录
			FileUtils.createDirs(fileDir, true);		
						
			//新建文件
	        File saveTo = new File(uploadPath.getFullPath());
	        
	        //将上传的文件写入到新文件中
	        multipartFile.transferTo(saveTo);	
	        
	        upload(saveTo,uploadResult,uploadPath,request.getContextPath(),categoryDir);			
	    }   	
	    		
		if(isCustomSizeOver){
    		uploadResult.setFlag(false);
    		uploadResult.setMsg(OVERLENGTH);
		}else {
		    uploadResult.setFlag(true);
		    uploadResult.setMsg(SUCCESS);	
		}	    
	    return uploadResult;
	}
	
	/**
	 * 将本地的文件上传到云，注意调用此方法之前，该文件已经保存在 uploadPath 对应的 getFullPath() 中
	 * (本方法只是将已保存到 getFullPath()路径中的文件上传到云服务器，并返还信息。）
	 * @param fullDir 文件目录路径（仅是目录，不含文件名）
	 * @param fileName 文件名（带后缀）
	 * @param categoryDir 文件放置的分类目录名称
	 */
	public static CloudUploadResult uploadToCloud(UploadPath uploadPath){
		CloudUploadResult cloudUploadResult = buildResult(uploadPath);
		
		//构造使用Jms的信息
		CloudUploadMsg cloudUploadMsg = new CloudUploadMsg();
		cloudUploadMsg.setFullDir(uploadPath.getFullDir());
		cloudUploadMsg.setFileName(uploadPath.getFileName());
		cloudUploadMsg.setCategoryDir(uploadPath.getCategoryDir());
		cloudUploadMsg.setUpLoadType(UploadType.IMAGE);
		
		JmsService jmsService = SpringHelper.getBean(JmsService.class);
		jmsService.send(cloudUploadMsg);		
		
		return cloudUploadResult;
	}
	
	/**
	 * 将二进制的内容上传到云，实现方式是先保存到本地，再上传到云。
	 * @param uploadPath
	 * @param datas
	 * @return
	 */
	public static CloudUploadResult uploadToCloud(UploadPath uploadPath,byte[] datas){
		InputStream in = new ByteArrayInputStream(datas);
		File file = new File(uploadPath.getFullPath());
		FileUtils.write(in, file);
		
		return uploadToCloud(uploadPath);
	}
	
	private static void upload(File saveTo,UploadResult uploadResult,UploadPath uploadPath,String contextPath,String categoryDir) throws Exception{
		//水印
        water(uploadPath);
		//收窄
		zoom(uploadPath);
		//保存到云服务器
		CloudUploadResult cloudUploadResult = uploadToCloud(uploadPath);
		
        //加入到返回对象中
        uploadResult.addUploadFile(contextPath + uploadPath.getRelativePath());		
		uploadResult.setCloudUploadResult(cloudUploadResult);
	}	
	
	private static void water(UploadPath uploadPath) throws Exception{
		//图片处理
		//加水印
		boolean needWatermark = ConfigHelper.getInstance().getBool(PropertiesConstants.IMAGE_WATERMARK);
		if(needWatermark && ImageUtil.isImageFile(uploadPath.getOriginFileName())){				
			ImageWatermarkService imageWatermarkService = SpringHelper.getBean(ImageWatermarkService.class);
			imageWatermarkService.addWatermark(uploadPath.getFullPath(), uploadPath.getFullPath());
		}
	}
	
	private static void zoom(UploadPath uploadPath) throws Exception{
		//生成小图
		boolean needZoom = ConfigHelper.getInstance().getBool(PropertiesConstants.IMAGE_ZOOM);
		if(needZoom && ImageUtil.isImageFile(uploadPath.getOriginFileName())){
			List<Integer> widths = ConfigHelper.getInstance().getParamToListInteger(PropertiesConstants.IMAGE_ZOOM_WIDTH);
			List<String> postfixs = ConfigHelper.getInstance().getParamToList(PropertiesConstants.IMAGE_ZOOM_POSTFIX);
			for(int i=0;i<widths.size();i++){
				ImageScaleHelper.zoomImage(uploadPath.getFullPath(), uploadPath.getFullPathAppendPostfix(postfixs.get(i)), widths.get(i));	
			}				
		}			
	}

	private static CloudUploadResult buildResult(UploadPath uploadPath) {
		CloudUploadResult result = new CloudUploadResult();
		result.setLocalPath(uploadPath.getWebRelativePath());
		String ossFileUrl = ConfigHelper.getInstance().getParamValue("OSS_FILE_URL");
		result.setType(CloudUploadResult.RETURN_URL);
		result.setUrl(ossFileUrl + uploadPath.getCategoryDir() + "/" + uploadPath.getFileName());
		result.setMediaType(ContentTypeConstants.IMAGE);			
		merge(uploadPath,result);
		return result;
	}
	
	private static void merge(UploadPath uploadPath,CloudUploadResult result){
		//GlCateRepository glCateRepository = SpringHelper.getBean(GlCateRepository.class);
		//GlCate glCate = glCateRepository.getByCateKey(uploadPath.getCategoryDir());
								
		if(result.getType().equals(CloudUploadResult.RETURN_KEY)){
			result.setCloudType("key");
			result.setCloudKey(result.getUrl());
		}else{
			result.setCloudType("path");
			result.setCloudPath(result.getUrl());			
		}
		int width = ImageUtil.getImgWidth(uploadPath.getFullPath());
		int height = ImageUtil.getImgHeight(uploadPath.getFullPath());
		result.setWidth(width);
		result.setHeight(height);
//		if(glCate!=null)
//			result.setCateId(glCate.getId());
	}

}
