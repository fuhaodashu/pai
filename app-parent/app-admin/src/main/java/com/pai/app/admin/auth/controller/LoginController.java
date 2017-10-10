package com.pai.app.admin.auth.controller;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.octo.captcha.service.image.ImageCaptchaService;
import com.pai.app.web.core.constants.MsgCode;
import com.pai.app.web.core.constants.UrlConstants;
import com.pai.app.web.core.constants.WebConstants;
import com.pai.app.web.core.framework.web.context.WebOnlineHolder;
import com.pai.app.web.core.framework.web.controller.LigerUIController;
import com.pai.base.core.entity.CommonResult;
import com.pai.base.core.helper.SpringHelper;
import com.pai.base.core.util.string.StringUtils;
import com.pai.base.core.validate.AnnotationValidator;
import com.pai.base.core.validate.parser.ValidateResult;
import com.pai.biz.auth.event.LoginEvent;
import com.pai.biz.auth.persistence.entity.AuthResourcesPo;
import com.pai.biz.auth.persistence.entity.AuthUserPo;
import com.pai.biz.auth.persistence.entity.LoginInfo;
import com.pai.biz.auth.repository.AuthResourcesRepository;
import com.pai.biz.auth.repository.AuthUserRepository;

/**
 * 后台用户登录，及登录后的操作
 * <pre> 
 * 构建组：app-admin
 * 作者：fuhao
 * 日期：2016年11月4日-上午9:58:58
 * </pre>
 */
@Controller
@RequestMapping("/")
public class LoginController extends LigerUIController{

	private final static String LOGIN_INFO = "loginInfo";
	
	@Resource
	private ImageCaptchaService imageCaptchaService;
	
	@Resource
	AuthUserRepository authUserRepository;	
	@Resource
	AuthResourcesRepository authResourcesRepository;
	@Override
	protected void initController() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 登录查找权限
	 * @param request
	 * @param response
	 * @param loginInfo
	 * @return
	 */
	@RequestMapping("adminLogin")
	public ModelAndView login(final HttpServletRequest request,HttpServletResponse response,LoginInfo loginInfo){
		 ModelAndView modelAndView = new ModelAndView(UrlConstants.LOGIN_FTL);
		 if(loginInfo.isLogin()){	//是提交登录	
			CommonResult commonResult = null;
			//必填校验
			 ValidateResult validateResult = AnnotationValidator.validate(loginInfo);
	 		 if(!validateResult.isValid()){
	 			commonResult = new CommonResult();
				commonResult.setSuccess(false);
				commonResult.setMsgCode(MsgCode.PARAMERROR.getCode());
				commonResult.setMsg(validateResult.getMessage());
	 		 }
		 	 //匹配验证码
	 		 else if(isCaptchaCorrect(request.getSession().getId(),loginInfo.getCaptchaCode())){
				//查询用户
				final AuthUserPo authUserPo = authUserRepository.getAccount(loginInfo.getUserName(), loginInfo.getEncryptPassword());
				if(authUserPo != null){
					//登录后发布一个事件，记录登录日志等操作，mq topic
					ExecutorService executorService = Executors.newCachedThreadPool();
					try {
						executorService.execute(new Runnable() {
							@Override
							public void run() {
								SpringHelper.publishEvent(new LoginEvent(authUserPo, getIpAddr(request)));
							}
						});
					} finally {
						executorService.shutdown();
					}
					
					//查询用户关联资源列表,用于拦截器匹配请求权限，资源放在session中，（当放置添加新的资源或配置角色资源时，必须使session失效，或者重新放置session-这个不需要了）
					List<AuthResourcesPo> authResourcesPoList = authResourcesRepository.findResourcesByUserId(authUserPo.getId());
					authUserPo.setAuthResourcesPos(authResourcesPoList);
					//放置session
					WebOnlineHolder.setUserPo(request.getSession(), authUserPo);
					
					//查询所有资源URL，用于拦截器匹配，需要设置权限的url必须登记在资源表中，当添加资源或给角色配置资源时，在重新登录前新加的资源不在session中所以并不会被拦截
					List<String> urls = authResourcesRepository.findAllUrls();
					WebOnlineHolder.setAuthResUrl(urls);
					
					//重定向到后台主页
					redirectUrl(response, request.getAttribute(WebConstants.CONTEXT_PATH)+UrlConstants.MAIN_URL); 
				}else{
					commonResult = new CommonResult();
					commonResult.setSuccess(false);
					commonResult.setMsgCode(MsgCode.LOGINERROR.getCode());
					commonResult.setMsg(MsgCode.LOGINERROR.getMsg());
				}
			}else{
				commonResult = new CommonResult();
				commonResult.setSuccess(false);
				commonResult.setMsgCode(MsgCode.CAPTCHA.getCode());
				commonResult.setMsg(MsgCode.CAPTCHA.getMsg());
			}				
			loginInfo.setCommonResult(commonResult);		
		 }	
		 
		 modelAndView.addObject(LOGIN_INFO, loginInfo);
		 return	modelAndView;
	}
	
	@RequestMapping("logout")
	public void logout(HttpServletRequest request,HttpServletResponse response){
//		OuOnlineHolder.logout();
		request.getSession().invalidate();
//		redirectUrl(response, request.getAttribute(WebConstants.CONTEXT_PATH) + UrlConstants.LOGIN_URL);
	}
	
	private boolean isCaptchaCorrect(String sessionId,String postCaptchaCode){
		boolean isCorrect = true;
		String captchaId = sessionId;		
		if (StringUtils.isNotEmpty(postCaptchaCode)) {
			try{
				isCorrect = imageCaptchaService.validateResponseForID(captchaId, postCaptchaCode.toLowerCase()).booleanValue();
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}else{
			isCorrect = false;		
		}
		return isCorrect;
	}	
	
	public String getIpAddr(HttpServletRequest request) {  
		   String fromSource = "X-Real-IP";  
		    String ip = request.getHeader("X-Real-IP");  
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("X-Forwarded-For");  
		        fromSource = "X-Forwarded-For";  
		    }  
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("Proxy-Client-IP");  
		        fromSource = "Proxy-Client-IP";  
		    }  
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("WL-Proxy-Client-IP");  
		        fromSource = "WL-Proxy-Client-IP";  
		    }  
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getRemoteAddr();  
		        fromSource = "request.getRemoteAddr";  
		    }  
		    logger.info("App Client IP: "+ip+", fromSource: "+fromSource);  
		    return ip;  
	}  
}
