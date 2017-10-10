package com.pai.app.web.core.framework.web.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.pai.base.core.constants.ImageType;

public class ImageUtil {
	
	public static int getImgWidth(String absolutePath) {
		File file = new File(absolutePath);
		return getImgWidth(file);
	}
	/**
     * 获取图片宽度
     * @param file  图片文件
     * @return 宽度
     */
    public static int getImgWidth(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            ret = src.getWidth(null); // 得到源图宽
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }      
      
	public static int getImgHeight(String absolutePath) {
		File file = new File(absolutePath);
		return getImgHeight(file);
	}    
    
    /**
     * 获取图片高度
     * @param file  图片文件
     * @return 高度
     */
    public static int getImgHeight(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            ret = src.getHeight(null); // 得到源图高
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    /**
     * 判断是否为图片
     * @param file  图片文件
     * @return 高度
     */
    public static boolean isImageFile(String fileName) {
		for(ImageType imageType:ImageType.values()){
			String suffix = "." + imageType.name().toLowerCase();
			if(fileName.toLowerCase().endsWith(suffix)){
				return true;
			}
		}
		return false;
	}
}
