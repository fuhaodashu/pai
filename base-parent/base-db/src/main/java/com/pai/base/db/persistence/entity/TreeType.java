package com.pai.base.db.persistence.entity;

import java.util.List;

/**
 * 树分类模型接口
 * @author csx
 *
 */
public interface TreeType<P extends PO> {
	/**
	 * 返回当前节点的ID
	 * @return
	 */
	public String getId();	
	/**
	 * 返回当前节点的显示名称
	 * @return
	 */
	public String getName();
	
	/**
	 * 返回父节点的ID
	 * @return
	 */
	public String getParentId();
	
	/**
	 * 返回当前节点在树中的路径。路径结构由 ID和小数点合成，如ID1.ID2.ID3
	 * @return
	 */
	public String getPath();
	
	/**
	 * 返回当前节点在树中的层次，根节点为1，下面的每层加1.
	 * @return
	 */
	public Integer getDepth();
	
	/**
	 * 返回排序号
	 * @return
	 */
	public Integer getSort();
	
	public List<P> getSubs();
	
	public void addSub(P po);
}
