package com.pai.app.web.core.framework.util;

import javax.servlet.http.HttpServletRequest;

import com.pai.base.api.model.Page;
import com.pai.base.core.util.RequestUtil;
import com.pai.base.db.mybatis.impl.domain.MyBatisPage;

public class PageUtil {
	//分页参数名称
	public final static String PAGINGVO="PagingVo";	
	public class NAME{
		//当前页参数名称
		public final static String PAGE_NUMBER = "page";
		//总页数参数名称
		public final static String TOTAL_PAGES = "total_pages_";
		//总记录数参数名称
		public final static String TOTAL_RECORDS = "Total";
		//每页记录数参数名称
		public final static String PAGE_SIZE="pageSize";	
	}
	
	
	public static Page buildPage(){
		HttpServletRequest request=RequestUtil.getRequest();
		Integer pageSize = RequestUtil.getIntegerParameter(request, NAME.PAGE_SIZE);
		return buildPage(request, pageSize);
	}
			
	public static Page buildPage(HttpServletRequest request){
		Integer pageSize = RequestUtil.getIntegerParameter(request, NAME.PAGE_SIZE);
		return buildPage(request, pageSize);
	}
	
	public static Page buildPage(HttpServletRequest request,Integer pageSize){
		MyBatisPage page = new MyBatisPage();
		
		//取得当前页码
		Integer pageNumber = RequestUtil.getIntegerParameter(request, NAME.PAGE_NUMBER);
		if(pageNumber==null || pageNumber<1){
			page.setPage(1);
		}else{
			page.setPage(pageNumber);
		}
		
		//取得每页记录数
		if(pageSize==null || pageSize<1){
			pageSize = Page.DEFAULT_PAGE_SIZE;
		}
		page.setLimit(pageSize);
		
		return page;
	}
}
