package com.pai.app.admin.upload;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.util.JSONStringer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pai.app.web.core.framework.constants.PropertiesConstants;
import com.pai.app.web.core.framework.web.controller.GenericController;
import com.pai.base.api.helper.IConfigHelper;
import com.pai.base.core.constants.ImageType;
import com.pai.base.core.util.DirUtils;
import com.pai.base.core.util.RequestUtil;
import com.pai.base.core.util.ServletContextHelper;
import com.pai.service.image.comparator.NameComparator;
import com.pai.service.image.comparator.SizeComparator;
import com.pai.service.image.comparator.TypeComparator;
import com.pai.service.image.constants.CompareType;
import com.pai.service.image.entity.FileManagerEntity;

@Controller
public class FileManagerController extends GenericController {
	
	@Resource
	IConfigHelper configHelper; 
	
	@RequestMapping("showFileManager")
	public void showFileManager(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = buildAutoView(request);
		//根目录路径，可以指定绝对路径，比如 /var/www/attached/
		
		String rootPath = getRootPath();
		
		//根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
		String rootUrl  = getRootUrl();
		
		//图片扩展名
		String[] fileTypes = ImageType.toArray();

		//当前目录
		String currentDirPath = RequestUtil.getParameterNullSafe(request, "path");		
		//不允许使用..移动到上一级目录
		if (currentDirPath.indexOf("..") >= 0) {
			printResult(response,"Access is not allowed.");
			return;
		}		
		//最后一个字符不是/
		if (!"".equals(currentDirPath) && !currentDirPath.endsWith("/")) {
			printResult(response,"Parameter is not valid.");
			return;
		}		
		
		//当前路径
		String currentPath = rootPath + currentDirPath;
		//目录不存在或不是目录
		File currentPathFile = new File(currentPath);
		if(!currentPathFile.isDirectory()){
			printResult(response,"Directory does not exist.");
			return;
		}		
		
		//当前URL
		String currentUrl = rootUrl + currentDirPath;

		//上一级目录
		String upLevelDir = DirUtils.getUpLevelDir(currentDirPath);

		//排序形式，name or size or type（默认是name）		
		String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : CompareType.NAME.lower();		

		//遍历目录取的文件信息
		List<Hashtable<String,Object>> fileList = new ArrayList<Hashtable<String,Object>>();
		if(currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				FileManagerEntity fileEntity = new FileManagerEntity(file);
				fileList.add(fileEntity.toHashtable());
			}
		}

		//排序方式
		if (CompareType.SIZE.lower().equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if (CompareType.TYPE.lower().equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}
		
		//构造返回信息
		JSONStringer stringer = new JSONStringer();
		stringer.object();
		stringer.key("moveup_dir_path");
		stringer.value(upLevelDir);
		stringer.key("current_dir_path");
		stringer.value(currentDirPath);
		stringer.key("current_url");
		stringer.value(currentUrl);
		stringer.key("total_count");
		stringer.value(fileList.size());
		stringer.key("file_list");
		stringer.value(fileList);		

		response.setContentType("application/json; charset=UTF-8");
		printResult(response,stringer.toString());
	}
	
	private String getRootPath(){
		String realPath = ServletContextHelper.getRealPath();
		String rootPath =  realPath + getPath();		
		return rootPath;
	}
	
	private String getRootUrl(){
		String contextPath = ServletContextHelper.getContextPath();
		return contextPath + getPath();
	}
	
	private String getPath(){		
		String uploadPath = configHelper.getParamValue(PropertiesConstants.UPLOAD_ROOT);
		String mediaPath = configHelper.getParamValue(PropertiesConstants.MEDIA_PATH);
		String kindeditor = "/kindeditor/";
		return uploadPath + mediaPath + kindeditor;
	}

	@Override
	protected void initController() {
		// TODO Auto-generated method stub
		
	}
}
