package com.pai.service.solr.solr.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;

import com.pai.base.core.util.string.StringUtils;


/**
 * 描述：Solr查询构建器
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 * </pre>
 */
@SuppressWarnings("unused")
public class SolrQueryBuilder {

	private String qt;//query type，指定那个类型来处理查询请求，一般不用指定，默认是select。 
	private String wt;//(writer type)指定输出格式，可以有 xml, json, php, phps 
	
	private String q;//查询
	private String fq;//filter query，过虑查询。
	private String fl;//是逗号分隔的列表，用来指定文档结果中应返回的 Field 集。默认为 “*”，指所有的字段。
	private String df;//默认的查询字段
	private String sort;//排序
	
	//分页
	private Integer start;
	private Integer rows;
	
	//高亮
	private Boolean hl;// 是否高亮
	private String hl_fl;//高亮field 用空格或逗号隔开的字段列表。要启用某个字段的highlight功能，就得保证该字段在schema中是stored。
	private String hl_simple_pre;//高亮 前缀
	private String hl_simple_post;//高亮 后缀
	private Integer hl_snippets;//高亮 片段的最大数
	private Integer hl_fragsize;//高亮 每个分片返回的最大字符数
	private Boolean hl_requireFieldMatch;//如果置为true，除非该字段的查询结果不为空才会被高亮。它的默认值是false，意味着它可能匹配某个字段却高亮一个不同的字段。
	//如果hl.fl使用了通配符，那么就要启用该参数。尽管如此，如果你的查询是all字段（可能是使用copy-field 指令），那么还是把它设为false，这样搜索结果能表明哪个字段的查询文本未被找到
	
	//分片  （参考地址：http://martin3000.iteye.com/blog/1330106）
	private Boolean facet;//是否 启用分片统计
	private String facet_query;//
	private String facet_field;//统计字段
	private String facet_prefix;//前缀
	private String facet_mincount;//设置facet最少的统计数量（默认值：1）
	private String facet_limit;//facet结果的返回行数（默认值：25）
	private Boolean facet_sort;//排序， true=按count数排序(默认)，false=按index排序
	private Boolean facet_missing;//如果为true,那么将统计那些Facet字段值为null的记录
	
	//竞价排名
	private String defType;//指定query parser，常用defType=lucene, defType=dismax, defType=edismax
	private String bq;// 对某个field的value进行boost,例如level:4^5.0
	private Boolean lowercaseOperators;//是否 小写转换

	/**
	 * 构建 SolrQuery
	 * @param params 获得 请求参数
	 * @return  SolrQuery
	 * @exception 
	 * @since  1.0.0
	 */
	public static SolrQuery queryBuilder(Map<String, Object> params){
		SolrQuery query = new SolrQuery();
		if(params != null){
			Field[] fields = SolrQueryBuilder.class.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				Object fieldVal = params.get(fieldName);
				if(fieldVal != null){//把 有值的参数 设置到 SolrQuery 
					query.set(fieldName.replace("_", "."), fieldVal.toString());
				}
			}
		}
		return query;
	}
	
	/**
	 * 构建查询语句
	 * @param isExcludeMode 是否排除
	 * @param isAndCond 是否是 并且(AND)条件, 否则是 OR 条件
	 * @param params
	 * @return  String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String builderQueryStatements(boolean isExcludeMode, boolean isAndCond, Map<String, Object> params){
		StringBuilder builder = new StringBuilder();
		String not = "", join = " AND ";
		if(isExcludeMode)
			not = "NOT ";
		if(!isAndCond)
			join = " OR ";
		if(params != null && params.size() > 0){
			for(String field : params.keySet()){
				if(params.get(field) != null && params.get(field).toString().trim().length() > 0){
					builder.append(not + field+":" + params.get(field) + join);
				}
			}
			if(builder.length() != 0)
				return builder.substring(0, builder.length()-5);
		}
		return builder.toString();
	}
	
	/** 构建 指定返回哪些些字段内容
	 * @param query 查询参数
	 * @param fields  指定返回那些字段内容
	 * @exception 
	 * @since  1.0.0
	 */
	public static void builderReturnField(Map<String, Object> query, String fields){
		if(StringUtils.isNotBlank(fields)){
			if(fields.endsWith(",")){
				fields = fields.substring(0, fields.length()-1);
			}
			query.put("fl", fields);//返回 字段
		}
	}
	
	public static void main(String[] args) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("q", "id:23");
		params.put("start", "0");
		params.put("rows", "10");
		params.put("hl", true);
		params.put("hl_fl", "age");
		SolrQuery query = SolrQueryBuilder.queryBuilder(params);
		System.out.println(query.toString());
	}

}