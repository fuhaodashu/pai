package com.pai.app.admin.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pai.app.web.core.framework.web.controller.LigerUIController;

@Controller
@RequestMapping("/admin/pai/auth")
public class MainController extends LigerUIController{
	
	@RequestMapping("main")
	public ModelAndView main(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = buildAutoView(request);
		return modelAndView;
	}
	
	@RequestMapping("welcome")
	public ModelAndView welcome(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = buildAutoView(request);
		return modelAndView;
	} 

	@Override
	protected void initController() {
		
	}
}
