package com.pai.service.solr.solr.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.MapUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.util.ClientUtils;

import com.pai.base.core.util.string.StringUtils;
import com.pai.service.solr.solr.page.Page;

public class SolrUtil {

	public static final String DEFAULT_QUERY = "*:*";//默认查询
	public static final String SMALL_DATE = "1949-01-01T01:00:00Z";
	
	public static void main(String[] args) {
		
	}

	@SuppressWarnings("deprecation")
	public static SolrQuery parseQueryStr(String keyword, Integer pageNo, Integer pageSize, LinkedHashMap<String, ORDER> sortMap, String... fields){
		SolrQuery query = new SolrQuery();
		//关键字
		if(StringUtils.isNotBlank(keyword)){
			if(keyword.indexOf(":") != -1){
				query.setQuery(keyword);
			}else{
				query.setQuery(ClientUtils.escapeQueryChars(keyword));
			}
		}
		
		//分页
		if(pageNo != null){
			if(pageNo < 1){
				pageNo = 1;
			}
			query.setStart((pageNo-1)*pageSize);
		}
		if(pageSize != null){
			if(pageSize > Page.MAX_ROWS){
				pageSize = Page.MAX_ROWS;
			}
			query.setRows(pageSize);
		}
		
		// 设置排序条件
		if (null != sortMap) {
			for (String field : sortMap.keySet()) {
				query.addSortField(field, sortMap.get(field));
			}
		}
		
		//返回字段
		if(fields != null)
			query.setFields(fields);
		return query;
	}
	
	public static LinkedHashMap<String, ORDER> parseSort(LinkedHashMap<String, String> sortMap){
		LinkedHashMap<String, ORDER> sort = new LinkedHashMap<String, ORDER>();
		if(MapUtils.isEmpty(sortMap)){
			return sort;
		}
		for (String field : sortMap.keySet()) {
			sort.put(field, SortTool.get(sortMap.get(field)));
		}
		return sort;
	}
	
	public static class SortTool { 
	    public static ORDER get(String key) {
	     	if("asc".equals(key.toLowerCase())){
	     		return ORDER.asc;
	     	}else{
	     		return ORDER.desc;
	     	}
	    }
	}
	

	/**
	 * 是否全是 英文
	 * @param keyword
	 * @return boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isAllEnglish(String keyword) {
		Pattern pat_ch = Pattern.compile("^[a-zA-Z]+$");
		Matcher matcher = pat_ch.matcher(keyword);
		if (matcher.find())
			return true;
		return false;
	}
	
	/**
	 * 是否全是中文
	 * @param keyword
	 * @return  boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isAllChinese(String keyword) {
		Pattern pat_ch = Pattern.compile("^[\u4e00-\u9fa5]+$");
		Matcher matcher = pat_ch.matcher(keyword);
		if (matcher.find())
			return true;
		return false;
	}
	
	/**
	 * 过滤 关键字 （过滤 ,+-=%等特殊字符）
	 * @param keyword
	 * @return boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static String filterKeyword(String keyword) {
		return (keyword.toLowerCase()).replaceAll("[+/=?？<>《》\\[\\]【】@!！#$%^……&（）(),，;；\\|/“”'\"~*][^.]", "").trim();
	}
	
	/**
	 * 是否全是数字
	 * @param keyword
	 * @return boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isAllNumber(String keyword) {
		Pattern pat_num = Pattern.compile("^[0-9]+$");
		Matcher matcher = pat_num.matcher(keyword);
		if (matcher.find())
			return true;
		return false;
	}
	
	public static final String DATE_NUMBER_PATTERN = "yyyyMMddHHmmss";
	private static final String DATE_NORM_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * date 转 long类型日期 （yyyyMMddHHmmss）
	 * @param date
	 * @return long  eg: 20150530113020
	 * @since  1.0.0
	 */
	public static long toLongTime(Date date){
		SimpleDateFormat date_format = new SimpleDateFormat(DATE_NUMBER_PATTERN);
		return Long.parseLong(date_format.format(date));
	}
	
	/**
	 * @param time yyyyMMddHHmmss
	 * @return Date
	 * @exception 
	 * @since  1.0.0
	 */
	public static Date longTimeToDate(long time){
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DATE_NUMBER_PATTERN);
		try {
			return dateTimeFormatter.parse(time+"");
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * @param time yyyyMMddHHmmss
	 * @return String yyyy-MM-dd HH:mm:ss
	 * @exception 
	 * @since  1.0.0
	 */
	public static String dateToString(long time){
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DATE_NORM_PATTERN);
		try {
			return dateTimeFormatter.format(longTimeToDate(time));
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获得 solr模块名
	 * @param endPoint
	 * @return  String
	 * @since  1.0.0
	 */
	public static String getModuleName(String endPoint){
		if(endPoint.endsWith("/"))
			endPoint = endPoint.substring(0, endPoint.length()-1);
		return endPoint.substring(endPoint.lastIndexOf("/")+1, endPoint.length());
	}
	
}
