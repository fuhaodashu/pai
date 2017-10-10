package com.pai.base.db.persistence.dao;

import com.pai.base.core.entity.MapBuilder;

public abstract class GenericDao {
    /**
     * 获得一个MapBuilder，通过它快速添加参数。
     * @return
     */
    protected MapBuilder buildMapBuilder() {    	
		return new MapBuilder();
	}
    
    /**
     * 和buildMapBuilder同义，缩写。
     * @return 
     * MapBuilder
     * @exception 
     * @since  1.0.0
     */
    protected MapBuilder b() {    	
		return new MapBuilder();
	}
    
}
