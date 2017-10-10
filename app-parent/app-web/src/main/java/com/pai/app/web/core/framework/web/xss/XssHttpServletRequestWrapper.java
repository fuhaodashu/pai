package com.pai.app.web.core.framework.web.xss;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;


public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        return escapeAll(super.getHeader(name));
    }

    @Override
    public String getQueryString() {
        return escapeAll(super.getQueryString());
    }

    @Override
    public String getParameter(String name) {
        return escapeAll(super.getParameter(name));
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if(values != null) {
            int length = values.length;
            String[] escapseValues = new String[length];
            for(int i = 0; i < length; i++){
                escapseValues[i] = escapeAll(values[i]);
            }
            return escapseValues;
        }
        return super.getParameterValues(name);
    }
    
    /**
     * 过滤html、js和sql字符
     * @param str
     * @return
     */
    private String escapeAll(String str){
    	String resultString = str;
    	
    	/*String result = StringEscapeUtils.escapeHtml(str);
    	result = StringEscapeUtils.escapeJavaScript(result);
    	result = StringEscapeUtils.escapeSql(result);*/    	    	
    	resultString = escapeJavaScript(resultString);
    	return resultString;
    }
    private String emptyTagProperty(String source, String element, String attr) {
        String reg = "<" + element + "[^<>]*?\\s" + attr + "[\\s]?=[\\s'\"]?[^>'\"\\s]+['\"\\s>]";
        String resultString = null;
       
        Matcher m = Pattern.compile(reg,Pattern.CASE_INSENSITIVE).matcher(source);         
        StringBuffer sb = new StringBuffer();        
        while (m.find()) {        	
            String r = m.group();
            String value = r.substring(r.lastIndexOf("=")+1).replaceAll("['\"\\s>]", "");
                        
            /* 清空http标签属性内容 */            
            Matcher m1 = Pattern.compile(attr,Pattern.CASE_INSENSITIVE).matcher(r);       
            while(m1.find()){            	
            	r = r.substring(0,r.lastIndexOf("=")); 
            	r = r.replace(m1.group(), "");   //onclick                  	
            }
            if(m.group().endsWith(">"))
            	r += ">";
            
/*        清空http标签属性内容，复杂不能搞定，比如:onclick="alert('onchang="123"')"   
 *         if(value.trim().length() == 0){
            	m.appendReplacement(sb, r);
            	continue;
            }
            
            if(r.endsWith(">")){
            	r = r.substring(0,r.lastIndexOf("=")) + "=''>" ;            
            }else{
            	r = r.substring(0,r.lastIndexOf("=")) + "='' " ;
            } */           

            m.appendReplacement(sb, r);
        }        
        m.appendTail(sb);  
        resultString = sb.toString();
        
        m = Pattern.compile(reg,Pattern.CASE_INSENSITIVE).matcher(resultString);
        if(m.find()){
        	resultString = emptyTagProperty(resultString, element, attr);
        }
        
        return resultString;  
    }  	
	  /**
     * 替换输入内容中的script字符
     */    
    private String escapeJavaScript(String str) {
    	
    	if(str == null || str.trim().length() == 0)
    		return str;

    	str = str.replaceAll("(?i)<script", "<\\\\/script");
    	str = str.replaceAll("(?i)</script>", "<\\\\/script>");
    	//str = str.replaceAll("(?i)<iframe", "<\\\\/iframe");
    	//str = str.replaceAll("(?i)</iframe>", "<\\\\/iframe");
    	str = str.replaceAll("(?i)script", "script_");
    	str = str.replaceAll("(?i)width:expression", "");
    	str = str.replaceAll("(?i)onmouse", "");    	
    	str = str.replaceAll("(?i)<object", "<\\\\/object");
    	str = str.replaceAll("(?i)</object>", "<\\\\/object>");
    	str = str.replaceAll("&#[0-9]+", "");
    	    	    	
    	str = emptyTagProperty(str, "a", "on[a-z]+");
    	str = emptyTagProperty(str, "img", "on[a-z]+");
    	str = emptyTagProperty(str, "body", "on[a-z]+"); 

		return str;
	}    
}