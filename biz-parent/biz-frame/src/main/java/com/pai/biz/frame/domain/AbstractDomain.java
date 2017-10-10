package com.pai.biz.frame.domain;


import java.io.Serializable;

import com.pai.base.api.service.IdGenerator;
import com.pai.base.core.entity.MapBuilder;
import com.pai.base.core.helper.SpringHelper;
import com.pai.base.core.util.string.StringUtils;
import com.pai.base.db.persistence.dao.IDao;
import com.pai.base.db.persistence.entity.AbstractPo;
import com.pai.base.db.persistence.entity.PO;

@SuppressWarnings("serial")
public abstract class AbstractDomain<PK extends Serializable,P extends PO<PK>> implements com.pai.biz.frame.domain.Domain<PK,P>,Serializable{
	
	private P data;
	
	private IDao<PK,P> dao;
	
	private IdGenerator idGenerator = SpringHelper.getBean(IdGenerator.class);
	
	private boolean isNewFlag = false; 
	
	public MapBuilder b(){
		return new MapBuilder();
	}
	
	public P getData() {
		return data;
	}	

	public PK getId() {
		if(data!=null){
			return data.getId();
		}
		return null;
	}

	public void setData(P data_) {
		data = data_;		
		init();
	}

	protected abstract void init();
	
	public void save(){
		onSave();
		if(isNew()){			
			if(((AbstractPo)data).getId()==null){
				((AbstractPo)data).setId(idGenerator.genSid());	
			}
			create();
		}else{			
			update();
		}
	}
	
	protected void onSave() {}
	
	protected boolean isNew(){
		if(isNewFlag){
			return true;
		}
		boolean isNew = false;
		if(data.getId()==null){
			isNew = true;
		}else if(data.getId() instanceof String){
			if(StringUtils.isEmpty((String)data.getId())){
				isNew = true;
			}
		}
		return isNew;
	}
	
	protected boolean isUpdate() {
		return !isNew();
	}
	
	public void create() {	
		if(((AbstractPo)data).getId()==null){
			((AbstractPo)data).setId(idGenerator.genSid());	
		}		
		getDao().create(data);
	}

	public void update() {
		getDao().update(data);
	}

	public void destroy() {
		getDao().delete(getId());
	}

	public void destroy(PK id_) {
		dao.delete(id_);
	}

	protected IDao<PK, P> getDao() {
		return dao;
	}

	protected void setDao(IDao<PK, P> dao) {
		this.dao = dao;
	}

	public IdGenerator getIdGenerator() {
		return idGenerator;
	}

	public boolean isNewFlag() {
		return isNewFlag;
	}

	public void setNewFlag(boolean isNewFlag) {
		this.isNewFlag = isNewFlag;
	}
	
}
