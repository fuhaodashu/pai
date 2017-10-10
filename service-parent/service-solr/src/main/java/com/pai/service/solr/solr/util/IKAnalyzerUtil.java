package com.pai.service.solr.solr.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * <pre> 
 * 描述：IK中文分词 工具类
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 * </pre>
 */
public class IKAnalyzerUtil {

	private final static Logger LOGGER = LoggerFactory.getLogger(IKAnalyzerUtil.class);
	
	static IKAnalyzer analyzer = null;
	
	static{
		analyzer = new IKAnalyzer();
	}

	public static void main(String[] args) {
		List<String> list = splitKeyword("中国好声音", 2);
		for (String str : list) {
			System.out.println(str);
		}
	}
	
	/**
	 * 将关键词 ik分词
	 * @param keyword
	 * @param minItemLen 最小长度
	 * @return  List<String>
	 * @exception 
	 * @since  1.0.0
	 */
	public static List<String> splitKeyword(String keyword, int minItemLen){
		List<String> list = new ArrayList<String>();
		TokenStream ts = null;
		try {
			ts = analyzer.tokenStream("field",new StringReader(keyword));
			CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
			ts.reset();
			while (ts.incrementToken()) {
				if(term.toString().length() >= minItemLen){
					list.add(term.toString());
				}
			}
			ts.end();
		} catch (IOException e) {
			LOGGER.error("ikAnalyzer splitKeyword fail, keyword:{}; {}", new Object[]{keyword, e});
		}finally{
			if(ts != null)
				try {
					ts.close();
				} catch (IOException e) {
				}
		}
		return list;
    }
}
