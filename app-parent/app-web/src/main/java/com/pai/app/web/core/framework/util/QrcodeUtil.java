//package com.pai.app.web.core.framework.util;
//
//import java.io.File;
//import java.util.Hashtable;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.MultiFormatWriter;
//import com.google.zxing.common.BitMatrix;
//import com.pai.base.core.util.FileUtils;
//import com.pai.service.image.CloudUploadResult;
//import com.pai.service.image.UploadHelper;
//import com.pai.service.image.entity.UploadPath;
//
//public class QrcodeUtil {
//
//	public static CloudUploadResult genCode(UploadPath uploadPath, String msg, int width, int height) throws Exception{
//		String format = "png";  
//        Hashtable hints= new Hashtable();  
//        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
//        BitMatrix bitMatrix = new MultiFormatWriter().encode(msg, BarcodeFormat.QR_CODE, width, height,hints);
//        String fullDir = uploadPath.getFullDir();
//        FileUtils.createDirs(fullDir, true);
//        File outputFile = new File(uploadPath.getFullPath());         
//        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);       
//        CloudUploadResult cloudUploadResult = UploadHelper.uploadToCloud(uploadPath, FileUtils.getBytes(outputFile));
//		return cloudUploadResult;
//	}
//}
