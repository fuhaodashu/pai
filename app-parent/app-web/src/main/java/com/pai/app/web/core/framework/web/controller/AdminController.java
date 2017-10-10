package com.pai.app.web.core.framework.web.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.pai.app.web.core.constants.WebConstants;
import com.pai.app.web.core.framework.web.entity.DWZResultInfo;
import com.pai.base.api.constants.Bool;
import com.pai.base.core.exception.GeneralException;
import com.pai.base.core.util.GenericsUtils;
import com.pai.base.core.util.RequestUtil;
import com.pai.base.core.util.string.StringUtils;
import com.pai.base.db.persistence.entity.PO;
import com.pai.biz.frame.domain.Domain;
import com.pai.biz.frame.repository.IRepository;

public abstract class AdminController<PK extends Serializable,P extends PO<PK>,D extends Domain<PK, P>> extends LigerUIController{			
	
	protected Class<P> poClass;	
	/**
	 * 列表集合名称，在页面使用
	 */
	protected String poListName;
	/**
	 * 表单页实体名称
	 */
	protected String poEntityName;
	/**
	 * 列表记录主键名称，在页面使用
	 */
	protected String poListIdName;
	
	protected String poTabId;
	
	protected String poListTabId;

	protected boolean isNew(P po) {
		if(po.getId()==null){
			return true;
		}
		return false;
	}
	
	protected void afterSave(HttpServletRequest request,HttpServletResponse response,P po,boolean isNew){
		afterSave(request,response,po,isNew,true,"");
	}
	protected void afterSave(HttpServletRequest request,HttpServletResponse response,P po,String message){
		afterSave(request,response,po,false,true,"");
	}	
	protected void afterSave(HttpServletRequest request,HttpServletResponse response,P po,boolean isNew,boolean closeCurrent,String message_){		
		try{
			String forwardUrl = "";
			Serializable id = po.getId();
			//保存并新增
			String saveAndNew = RequestUtil.getParameterNullSafe(request, WebConstants.PARAM_SAVE_AND_NEW);
			//保存并编辑下一个
			String saveAndNext = RequestUtil.getParameterNullSafe(request, WebConstants.PARAM_SAVE_AND_NEXT);
			if(saveAndNew.equals(Bool.Y.name())){
				//返回新增页面
				forwardUrl = WebConstants.DEFAULT_ADD_URL + WebConstants.ACTION_POSTFIX;
			}else if(saveAndNext.equals(Bool.Y.name())){
				//返回保存并编辑下一个				
				forwardUrl = WebConstants.DEFAULT_EDIT_URL + WebConstants.ACTION_POSTFIX + "?nextEdit=Y&id=" + id;							
			}			
			String message = null;
			if(StringUtils.isNotEmpty(message_)){
				message = message_;
			}else if(isNew){
				message = "新增【" +getPoEntityComment()+ "】成功";
			}else{
				message = "保存【"+ getPoEntityComment() +"】成功";
			}			
			DWZResultInfo info = DWZResultInfo.newInstance(DWZResultInfo.STATUS.OK);
			info.setMessage(message);
			info.setNavTabId(poTabId);
			info.setCallbackType(closeCurrent?"closeCurrent":"");
			info.setForwardUrl(forwardUrl);
			String result = convertToJson(info);
			this.printResult(response, result);
		}catch(Exception e){			
			throw new GeneralException(po.getClass(),e.getMessage());
		}	
	}			

	/*protected void afterListUpdate(HttpServletRequest request,HttpServletResponse response,String message){
		DWZResultInfo info = DWZResultInfo.newInstance(DWZResultInfo.STATUS.OK);
		info.setMessage(message);
		info.setNavTabId(poListTabId);
		String result = convertToJson(info);
		this.printResult(response, result);
	}*/
	
	@Override
	protected void initController() {
		poClass = GenericsUtils.getSuperClassGenricType(getClass(), 1);
		poEntityName = StringUtils.lowerFirst(poClass.getSimpleName());
		poTabId = poEntityName + "TabId";
		poListTabId = poEntityName + "ListTabId";
	}
	
	protected List<P> getPos(List<D> domains){
		List<P> poList = new ArrayList<P>();
		for(D d:domains){
			@SuppressWarnings("unchecked")
			P po = (P) d.getData();
			poList.add(po);						
		}
		return poList;
	}
	
	protected String buildListData(List<P> poList,Integer total) throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, String> exProperties = new HashMap<String, String>();
		return buildListData(poList, total, exProperties);
	}
	
	protected String buildListData(List<P> poList,Integer total,Map<String, String> exProperties) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.getSerializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));  
		String listData = objectMapper.writeValueAsString(poList);
		
		StringBuilder sb = new StringBuilder();		
		sb.append("{\"Rows\":");
		sb.append(listData);
		sb.append(",\"Total\":");
		sb.append(total);
		if(exProperties!=null && exProperties.size()>0){
			for(Iterator<String> it = exProperties.keySet().iterator();it.hasNext();){
				String key = it.next();
				String value = exProperties.get(key);
				sb.append(",\""+key+"\":");
				sb.append("\""+value+"\"");				
			}
		}
		sb.append("}");
		//System.out.println(sb.toString());
		return sb.toString();
	}
	
	protected abstract IRepository<PK, P, D> getRepository();
	
	protected abstract String getPoEntityComment();
	
}
