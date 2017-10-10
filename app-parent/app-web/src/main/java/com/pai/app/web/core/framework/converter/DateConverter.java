package com.pai.app.web.core.framework.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import com.pai.base.core.util.string.StringUtils;

public class DateConverter implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		if(StringUtils.isNotEmpty(source)){
			if(source.length()<=10){
			    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			    dateFormat.setLenient(false);  
			    try {  
			        return dateFormat.parse(source);  
			    } catch (ParseException e) {  
			        e.printStackTrace();  
			    }
		    } else if(source.length()>15) {
			    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			    dateFormat.setLenient(false);  
			    try {  
			        return dateFormat.parse(source);  
			    } catch (ParseException e) {  
			        e.printStackTrace();  
			    }	
			}
		}
	    return null;
	}

}
