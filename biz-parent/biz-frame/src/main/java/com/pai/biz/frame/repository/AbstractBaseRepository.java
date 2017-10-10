package com.pai.biz.frame.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pai.base.api.model.Page;
import com.pai.base.core.entity.MapBuilder;
import com.pai.base.db.persistence.dao.simple.IBaseDao;
import com.pai.base.db.persistence.entity.PO;
import com.pai.base.db.persistence.entity.TreeType;
import com.pai.biz.frame.domain.Domain;
/**
 * 对象功能:用户 Repository接口
 * 开发公司:PI.COM
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 */
public abstract class AbstractBaseRepository<PK extends Serializable,P  extends PO<PK>,D extends Domain<PK, P>> implements IRepository<PK, P, D>{

	public MapBuilder b(){
		return new MapBuilder();
	}
	
	public D load(PK id) {
		P po =  getDao().get(id);
		if(po!=null){
			D d = newInstance(po);
			return d;	
		}
		return null;
	}
	
	public D getLast() {
		P po =  getDao().getLast();
		if(po!=null){
			D d = newInstance(po);
			return d;
		}
		return null;
	}
	
	public Integer countAll(){
		return getDao().countAll();
	}
	public Integer count(Map<String, Object> params){
		return getDao().count(params);
	}
	public List<P> findAll() {
		List<P> poList =  getDao().findAll();		
		return poList;
	}
	public List<P> findAll(Map<String, Object> params) {
		List<P> poList =  getDao().findAll(params);		
		return poList;
	}
	public List<P> findPaged(Page page) {
		List<P> poList =  getDao().findPaged(page);
		return poList;
	}
	
	public List<P> findPaged(Map<String, Object> params, Page page) {
		return getDao().findPaged(params, page);
	}

	public List<P> findByIds(List<PK> ids){
		List<P> poList =  getDao().findByIds(ids);
		return poList;
	}
	
	protected abstract IBaseDao<PK, P> getDao();
	
	protected List<P> convertToTree(List<P> pos) {
		Integer maxDepth = getMaxDepth(pos);		
		return convertToTree(pos, maxDepth);
	}
	
	protected List<P> convertToTree(List<P> pos,Integer maxDepth) {		
		List<P> tree = new ArrayList<P>();
		List<P> tempList = new ArrayList<P>();
		for(int i=1;i<=maxDepth;i++){
			for(P po:pos){
				if(po instanceof TreeType){
					TreeType tPo = (TreeType)po;
					if(tPo.getDepth()==i){
						if(i==1){	//是根节点资源
							tree.add(po);
							tempList.add(po);
						}else{		//不是根节点资源，寻找它的父资源。
							P tempPo = null;
							for(P inPo:tempList){
								TreeType tInPo = (TreeType)inPo;
								if(tInPo.getDepth()==(i-1) && tPo.getParentId().equals(tInPo.getId())){
									tInPo.addSub(po);
									tempPo = po;
								}
							}
							if(tempPo!=null){
								tempList.add(tempPo);
							}
						}
					}					
				}
			}
		}
		return tree;
	}
	
	protected Integer getMaxDepth(List<P> pos) {
		Integer maxDepth = 1;
		for (int i = 0; i < pos.size(); i++) {
			P po = pos.get(i);
			if(po instanceof TreeType){
				TreeType treeType = (TreeType)po;
				if(treeType.getDepth()>maxDepth){
					maxDepth = treeType.getDepth();
				}
			}
		} 
		return maxDepth;
	}
}
