package com.pai.app.admin.upload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pai.app.web.core.constants.WebConstants;
import com.pai.app.web.core.framework.web.controller.GenericController;
import com.pai.app.web.core.framework.web.util.ViewUtil;
import com.pai.service.image.entity.UploadResult;

@Controller
@RequestMapping("/admin/pai/upload/")
public class UploadController extends GenericController{
	
	@RequestMapping("ajax")
	@ResponseBody
	public UploadResult ajax(HttpServletRequest request,HttpServletResponse response) throws Exception{
		UploadResult uploadResult = UploadHelper.upload(request);
		return uploadResult;
	}
	
	@RequestMapping("post")
	public ModelAndView post(HttpServletRequest request,HttpServletResponse response) throws Exception{
		UploadResult uploadResult = UploadHelper.upload(request);
	    	    
	    String viewPath = ViewUtil.getByURI(request.getRequestURI(), request.getContextPath(), WebConstants.ACTION_POSTFIX);
	    ModelAndView modelAndView = new ModelAndView(viewPath);
	    modelAndView.addObject("uploadResult", uploadResult);
	    
		return modelAndView;		
	}
	
	@Override
	protected void initController() {
		// TODO Auto-generated method stub
		
	}

}
