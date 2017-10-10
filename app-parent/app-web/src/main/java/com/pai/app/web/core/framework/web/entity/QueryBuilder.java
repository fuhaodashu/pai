package com.pai.app.web.core.framework.web.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.pai.base.api.model.FieldInfo;
import com.pai.base.api.model.TableInfo;
import com.pai.base.core.helper.SearchInfoHelper;
import com.pai.base.core.util.RequestUtil;
import com.pai.base.core.util.string.StringCollections;
import com.pai.base.core.util.string.StringUtils;

/**
 * 查询构造类
 * @author fuhao
 *
 */
public class QueryBuilder {
	public final static String TOKEN="__";
	public final static String PERFIX="Q" + TOKEN;
	public final static String ALIAS = "A" + TOKEN;
	
	public final static String TYPE_STRING="S";
	public final static String TYPE_NUMBER="N";
	public final static String TYPE_DATE="D";
	
	public final static String COMPARE_EQ="EQ";
	public final static String COMPARE_LIKE="LK";
	public final static String COMPARE_NEQ="NEQ";
	public final static String COMPARE_GT="GT";
	public final static String COMPARE_LT="LT";
	public final static String COMPARE_BETWEEN="BW";
	
	public final static String CAL_TYPE_NAME = "CAL_TYPE";
	public final static String CAL_TYPE_AND = " and ";
	public final static String CAL_TYPE_OR = " or ";
	
	//后台请求的通用路径
	public final static String ADMIN="/admin";
	
	/**
	 * 查询参数
	 * 每一个参数的结构是： Q__[TYPE]__[COMPARE]__[参数名] 或 Q__[TYPE]__[COMPARE]__[表别名]__[参数名]
	 * （中间为两条下划线）
	 * 如Q__S__LK__id_ 或 Q__EQ__LK__t1__id_ 
	 * 现在的格式是默认4段，可加表别名t1选5段。
	 * Q 为前缀，没有业务意义。A 也是前缀无实际意义，只是作为一个表关联查询时的标注，如表AA aa join 表BB bb,我们前端页设置A__aa__AA注明给你自己看的，不设置也行
	 * S  表示数据类型。
     * LK 表示比较方式。
     * t1表示别名，即当是多表查询时，可以写别名。 
     * id_ 表示字段 
     * 构造时，即形成：t1.id_
	 */
	
	/**
	 * 表别名参数
	 * A__[别名]__[实体名，第一个字母小写]，如：A__t1__id_
	 */
	
	private Map<String, Object> paramValueMap = new HashMap<String, Object>();
	
	private Map<String, String> paramDataTypeMap = new HashMap<String, String>();
	
	private Map<String, String> paramCompareMap = new HashMap<String, String>();
	
	/**
	 * key ： 表别名，如t1；value：表参数名。如productMedia
	 */
	private Map<String, String> tableAliasMap = new HashMap<String,String>();
	
	private String calType;	//And 或 or
	
	private String sortname;
	private String sortorder;
	private String sortTableAlias;
	
	private String defaultTableVar;
	
	public QueryBuilder(HttpServletRequest request){		
		Map<String, Object> datas = RequestUtil.getRequestMap(request);
		
		for(Iterator<String> it = datas.keySet().iterator();it.hasNext();){
			String paramNamePost = it.next();
			if (paramNamePost.startsWith(ALIAS)) {
				parseEntityName(paramNamePost);
			}
		}
		
		if(tableAliasMap.size()==0){
			defaultTableVar = getTblNameFromUri(request.getRequestURI());
		}
		
		for(Iterator<String> it = datas.keySet().iterator();it.hasNext();){
			String paramNamePost = it.next();
			if(paramNamePost.startsWith(PERFIX)){
				Object value = datas.get(paramNamePost);				
				String paramName = getParamName(paramNamePost);
				String dataType = getDataType(paramNamePost);
				String compare = getCompare(paramNamePost);
				
				if(isNotEmpty(value)){
					paramValueMap.put(paramName, value);
					paramDataTypeMap.put(paramName, dataType);
					paramCompareMap.put(paramName, compare);
				}
			}
		}
		
		calType = RequestUtil.getParameterNullSafe(request, CAL_TYPE_NAME);
		if(StringUtils.isEmpty(calType)){
			calType = CAL_TYPE_AND;
		}
		
		buildSortParams(request);					
	}	
	
	private void buildSortParams(HttpServletRequest request){
		sortname = RequestUtil.getParameterNullSafe(request, "aliasSortName");
		sortorder = RequestUtil.getParameterNullSafe(request, "sortorder");
		if(StringUtils.isEmpty(sortTableAlias)){
			sortTableAlias = RequestUtil.getParameterNullSafe(request, "aliasTableName");
		}
		boolean isDone = false;
		if(StringUtils.isNotEmpty(sortTableAlias)){
			String entityVar = tableAliasMap.get(sortTableAlias);
			TableInfo tableInfo = SearchInfoHelper.getTableInfo(entityVar);
			if(tableInfo!=null && tableInfo.getFieldInfo(sortname)!=null){
				sortname =tableInfo.getFieldInfo(sortname).getColumn(); 
				isDone = true;
			}
		}else{
			for(Iterator<String> it = tableAliasMap.keySet().iterator();it.hasNext();){
				String alias = it.next();
				String entityVar = tableAliasMap.get(alias);
				TableInfo tableInfo = SearchInfoHelper.getTableInfo(entityVar);
				if(tableInfo!=null && tableInfo.getFieldInfo(sortname)!=null){
					sortTableAlias = alias;
					sortname =tableInfo.getFieldInfo(sortname).getColumn();
					isDone = true;
					break;
				}
			}
		}
		if(!isDone){
			TableInfo tableInfo = SearchInfoHelper.getTableInfo(defaultTableVar);
			if(tableInfo!=null && tableInfo.getFieldInfo(sortname)!=null){
				sortname =tableInfo.getFieldInfo(sortname).getColumn(); 
			}
		}
	}
	
	private String getTblNameFromUri(String uri){
		String[] array = uri.split("/");
		if(uri.startsWith(ADMIN)){
			if(array.length>5){
				return array[4];
			}	
		}else if(uri.indexOf(ADMIN)>0){
			if(array.length>6){
				return array[5];
			}
		}
		return "";
	}
	
	private boolean isNotEmpty(Object value){
		if(value == null){
			return false;
		}else{
			if(value instanceof String){
				if(StringUtils.isNotEmpty((String)value)){
					return true;
				}
			}
			if(value instanceof String[]){
				String[] array = (String[])value;
				if(array.length==2 && (StringUtils.isNotEmpty(array[0]) || StringUtils.isNotEmpty(array[1]))){
					return true;
				}
			}
		}
		return false;
	}
	
	public Map<String, Object> buildMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("whereSql", buildWhereSql());
		String orderBySql = buildOrderBySql();
		if(StringUtils.isNotEmpty(orderBySql)){
			map.put("orderBySql", buildOrderBySql());
		}
		return map;
	}
	
	public String buildOrderBySql(){
		StringBuilder sb = new StringBuilder("");
		
		if(!StringUtils.isEmpty(sortorder)&&!StringUtils.isEmpty(sortname)){
			sb.append(" ");
			if(!StringUtils.isEmpty(sortTableAlias)){
				sb.append(sortTableAlias);
				sb.append(".");
			}
			
			sb.append(sortname);
			sb.append(" "+sortorder);
		}
	
		return sb.toString();
	}
	
	public String buildWhereSql(){
		StringBuilder sb = new StringBuilder("");
		for(Iterator<String> it = paramValueMap.keySet().iterator();it.hasNext();){
			String paramName = it.next();
			Object paramValue = paramValueMap.get(paramName);
			String dataType = paramDataTypeMap.get(paramName);
			String compare = paramCompareMap.get(paramName);
			
			if(!sb.toString().equals("")){
				sb.append(" " + calType + " ");
			}
			
			if(dataType.equals(TYPE_STRING)){
				if(compare.equals(COMPARE_EQ)){
					sb.append(paramName + " = '" + paramValue + "' ");
				}else if(compare.equals(COMPARE_LIKE)){
					sb.append(paramName + " like '%" + paramValue + "%' ");
				}else if(compare.equals(COMPARE_NEQ)){
					sb.append(paramName + " != '" + paramValue + "' ");
				}
			}else if(dataType.equals(TYPE_NUMBER)){
				if(compare.equals(COMPARE_EQ)){
					sb.append(paramName + " =" + paramValue + " ");
				}else if(compare.equals(COMPARE_BETWEEN)){
					String[] range = (String[])paramValue;
					if(StringUtils.isNotEmpty(range[0]) && StringUtils.isNotEmpty(range[1])){
						sb.append(paramName + " between  " + range[0] + " and " + range[1] + " ");
					}else if(StringUtils.isNotEmpty(range[0])){
						sb.append(paramName + " >=" + range[0] + " ");
					}else if(StringUtils.isNotEmpty(range[1])){
						sb.append(paramName + " <=" + range[1] + " ");
					}
				}
			}else if(dataType.equals(TYPE_DATE)){
				if(compare.equals(COMPARE_EQ)){
					sb.append(paramName + " ='" + paramValue + "' ");
				}else if(compare.equals(COMPARE_BETWEEN)){
					String[] range = (String[])paramValue;
					if(StringUtils.isNotEmpty(range[0]) && StringUtils.isNotEmpty(range[1])){
						if(range[1].length()==10){
							range[1]=range[1]+" 23:59:59";
						}
						sb.append(paramName + " between  '" + range[0] + "' and '" + range[1] + "' ");
					}else if(StringUtils.isNotEmpty(range[0])){
						sb.append(paramName + " >='" + range[0] + "' ");
					}else if(StringUtils.isNotEmpty(range[1])){
						if(range[1].length()==10){
							range[1]=range[1]+" 23:59:59";
						}
						sb.append(paramName + " <='" + range[1] + "' ");
					}
				}
			}			
		}
		return sb.toString();
	}
	
	public Map<String, Object> buildWhereSqlMap(){
		Map<String,Object> whereSqlMap = new HashMap<String,Object>();
		whereSqlMap.put("whereSql", buildWhereSql());
		return whereSqlMap;
	}
	
	public void putParamValue(String paramName,String paramValue, String dataType,String compare){
		paramValueMap.put(paramName, paramValue);
		paramDataTypeMap.put(paramName, dataType);
		paramCompareMap.put(paramName, compare);
	}
	
	private String getDataType(String paramNamePost){
		if(paramNamePost.startsWith(PERFIX)){
			String[] array = StringCollections.toArray(paramNamePost, TOKEN);
			return array[1];
		}
		return TYPE_STRING;
	}
	
	private String getCompare(String paramNamePost){
		if(paramNamePost.startsWith(PERFIX)){
			String[] array = StringCollections.toArray(paramNamePost, TOKEN);
			return array[2];
		}
		return COMPARE_LIKE;
	}
	
	private String getParamName(String paramNamePost){
		String tableAlias = "";
		String paramVar = "";
		String paramName = "";
		if(paramNamePost.startsWith(PERFIX)){
			String[] array = paramNamePost.split(TOKEN);			
			if(array.length==5){
				tableAlias = array[3];
				paramVar = array[4];
				paramName = array[3] + "." + array[4];
			}else if(array.length==4){
				paramVar = array[3];
				paramName = array[3];			
			}
		}
		
		TableInfo tableInfo = null;
		if(StringUtils.isNotEmpty(tableAlias)){
			String entityVar = tableAliasMap.get(tableAlias);
			tableInfo = SearchInfoHelper.getTableInfo(entityVar);
		}else if(StringUtils.isNotEmpty(defaultTableVar)){
			tableInfo = SearchInfoHelper.getTableInfo(defaultTableVar);
		}
		
		if(tableInfo!=null){
			FieldInfo fieldInfo = tableInfo.getFieldInfo(paramVar);
			if(fieldInfo!=null){			
				if(StringUtils.isNotEmpty(tableAlias)){
					paramName = tableAlias + "." + fieldInfo.getColumn();
				}else {
					paramName = fieldInfo.getColumn();
				}
			}
		}
		
		return paramName;
	}
	
	private void parseEntityName(String paramNamePost){
		if(paramNamePost.startsWith(ALIAS)){
			String paramName = paramNamePost.replaceAll(ALIAS, "");
			String[] array = paramName.split(TOKEN);
			if(array.length==2){
				tableAliasMap.put(array[0], array[1]);
			}
		}
	}

}
