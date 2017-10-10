package com.pai.app.web.core.framework.web.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pai.base.api.service.IdGenerator;
import com.pai.base.core.entity.CommonResult;
import com.pai.base.core.util.ServletContextHelper;

/**
 * 
 * <pre> 
 * 描述：提供两种测试方式：
 *    一：构造request和response，通过excuteAction 执行获得ModelAndView
 *    二：通过mockMvc 直接执行。
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 * </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:conf/web-context-test.xml"})
public class JUnitControllerBase {
	
	@Resource
	protected HandlerMapping handlerMapping;
	
	@Resource
    protected HandlerAdapter handlerAdapter;  
    
	@Resource
	protected IdGenerator idGenerator;
	
	@Autowired  
	private WebApplicationContext wac; 
	
	 protected MockMvc mockMvc;  
	 
    /** 
     * 执行request请求的action 
     *  
     * @param request 
     * @param response 
     * @return 
     * @throws Exception 
     */  
    protected ModelAndView excuteAction(HttpServletRequest request,  
            HttpServletResponse response) throws Exception {  
        // 这里需要声明request的实际类型，否则会报错  
        request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);  
        HandlerExecutionChain chain = handlerMapping.getHandler(request);  
        ModelAndView model = null;  
        try {  
            model = handlerAdapter  
                    .handle(request, response, chain.getHandler());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return model;  
    }            
    
    @Before  
    public void setup() {  
        // webAppContextSetup 注意上面的static import  
        // webAppContextSetup 构造的WEB容器可以添加fileter 但是不能添加listenCLASS  
        // WebApplicationContext context =  
        // ContextLoader.getCurrentWebApplicationContext();  
        // 如果控制器包含如上方法 则会报空指针  
        this.mockMvc = webAppContextSetup(this.wac).build();
        
        initContext();
    }  
    
    protected void p(String content) {
		System.out.println(content);
	}
    
    protected JSONObject executeResponseBody(MockHttpServletRequestBuilder builder) throws Exception{
    	ResultActions resultActions = mockMvc.perform(builder).andExpect(status().isOk());
        MvcResult mvcResult = resultActions.andReturn();
        String body = mvcResult.getResponse().getContentAsString();
        
        JSONObject obj = JSONObject.fromObject(body);
        return obj;
    }
    
    protected CommonResult executeCommonResult(MockHttpServletRequestBuilder builder) throws Exception{
    	JSONObject object = executeResponseBody(builder);
    	
    	CommonResult commonResult = (CommonResult)JSONObject.toBean(object,CommonResult.class);
        return commonResult;
    }    
    
    protected CommonResult convert(String bodyJson) {
        JSONObject obj = JSONObject.fromObject(bodyJson);
        CommonResult commonResult = (CommonResult)JSONObject.toBean(obj,CommonResult.class);
        return commonResult;
	}    
    
    private void initContext(){
    	MockHttpSession session = new MockHttpSession();
    	
    	//将servletContext放到Helper中
		MockServletContext servletContext = new MockServletContext();
		servletContext.setContextPath("/");		
		ServletContextHelper.init(servletContext);
		
		//默认设置为登录，以便保存数据时，可以获得创建人。
		/*UserRepository userRepository = SpringHelper.getBean(UserRepository.class);
		User user = userRepository.getLast();
		OuOnlineHolder.setUserPo(session,user.getData());	*/			
		
    }
    
}
