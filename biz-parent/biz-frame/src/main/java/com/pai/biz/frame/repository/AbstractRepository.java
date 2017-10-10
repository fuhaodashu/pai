package com.pai.biz.frame.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pai.base.api.model.Page;
import com.pai.base.core.entity.MapBuilder;
import com.pai.base.db.persistence.dao.IQueryDao;
import com.pai.base.db.persistence.entity.PO;
import com.pai.base.db.persistence.entity.TreeType;
import com.pai.biz.frame.domain.Domain;
/**
 * 对象功能:用户 Repository接口
 * 开发公司:PI.COM
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 */
public abstract class AbstractRepository<PK extends Serializable,P  extends PO<PK>,D extends Domain<PK, P>> implements IRepository<PK, P, D>{

	public MapBuilder b(){
		return new MapBuilder();
	}
	
	public D load(PK id) {
		P po = get(id);
		if(po!=null){
			D d = newInstance(po);
			return d;	
		}
		return null;
	}
	
	public P get(PK id) {
		return getQueryDao().get(id);
	}
	
	public D getLast() {
		P po =  getQueryDao().getLast();
		if(po!=null){
			D d = newInstance(po);
			return d;
		}
		return null;
	}
	
	public Integer countAll(){
		return getQueryDao().countAll();
	}
	public Integer count(Map<String, Object> params){
		return getQueryDao().count(params);
	}
	public List<P> findAll() {
		List<P> poList =  getQueryDao().findAll();		
		return poList;
	}
	public List<P> findAll(Map<String, Object> params) {
		List<P> poList =  getQueryDao().findAll(params);		
		return poList;
	}
	public List<P> findPaged(Page page) {
		List<P> poList =  getQueryDao().findPaged(page);
		return poList;
	}
	
	public List<P> findPaged(Map<String, Object> params, Page page) {
		return getQueryDao().findPaged(params, page);
	}

	public List<P> findByIds(List<PK> ids){
		List<P> poList =  getQueryDao().findByIds(ids);
		return poList;
	}
	
	protected abstract IQueryDao<PK, P> getQueryDao();
	
	@Deprecated
	protected List<P> convertToTree(List<P> pos) {
		Integer maxDepth = getMaxDepth(pos);		
		return convertToTree(pos, maxDepth);
	}
	
	@Deprecated
	protected List<P> convertToTree(List<P> pos,Integer maxDepth) {		
		List<P> tree = new ArrayList<P>();
		List<P> tempList = new ArrayList<P>();
		//按数的层数做遍历
		for(int i=1;i<=maxDepth;i++){
			for(P po:pos){
				if(po instanceof TreeType){
					TreeType tPo = (TreeType)po;
					if(tPo.getDepth()==i){
						//先找出一级菜单
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
	
	@Deprecated
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
	
	/**
	 * list集合是按层次,parentId,sort排序,构造成树返回
	 * by fuhao
	 * @param pos
	 * @return
	 */
	protected List<P> listToTree(List<P> pos) {
		List<P> tree = new ArrayList<P>();
		Map<Integer, List<P>> map = new HashMap<Integer, List<P>>();
		//每一个层次的集合
		List<P> subTree = new ArrayList<P>();
		//存储父节点的位置
		int k = -1;
		for(int j=0; j<pos.size(); j++){
			if(pos.get(j) instanceof TreeType){
				TreeType tPo = (TreeType)pos.get(j);
				if(tPo.getDepth() == 1){
					tree.add(pos.get(j));
					subTree.add(pos.get(j));
				}else{
					//层次切换判断
					TreeType ptPo = (TreeType)pos.get(j-1);
					if(tPo.getDepth() != ptPo.getDepth()){
						//每次层次切换时把父节点的集合存储起来
						map.put(ptPo.getDepth(), subTree);
						subTree = new ArrayList<P>();
					}
					//无层次切换则说明同级
					subTree.add(pos.get(j));
					//子节点放入父节点下
					List<P> parentTree = map.get(tPo.getDepth()-1);
					for(int i=0; i<parentTree.size(); i++){
						TreeType pPo = (TreeType)parentTree.get(i);
						//父节点判断
						if(pPo.getId().equals(tPo.getParentId())){
							pPo.addSub(pos.get(j));
							if(k != -1 && i != k){
								//去除父节点集合中上一个节点的父节点,减少下次循环
								parentTree.remove(k);
								//位置重置
								i = 0;
							}
							k = i;
							break;
						}
					}
				}
			}
		}
		return tree;
	}
}
