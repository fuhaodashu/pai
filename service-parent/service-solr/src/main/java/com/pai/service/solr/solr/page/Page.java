package com.pai.service.solr.solr.page;

import java.util.Collections;
import java.util.List;

/**
 * 
 * <pre> 
 * 描述： 分页工具
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 * </pre>
 */
public class Page<T>{

	public static final Integer MAX_ROWS = 30;	// 每页最大记录数限制
	
	protected int pageNo = 1;
	protected int pageSize = 10;
	protected List<T> result = Collections.emptyList();
	
	protected long totalCount = 0;

	public Page() {
	}

	public Page(int pageSize) {
		setPageSize(pageSize);
	}
	
	public Page(int pageSize,int pageNo) {
		setPageSize(pageSize);
		setPageNo(pageNo);
	}


	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;
		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	public Page<T> pageNo(final int thePageNo) {
		setPageNo(thePageNo);
		return this;
	}

	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}else if(pageSize > MAX_ROWS){
			this.pageSize = MAX_ROWS;
		}
	}

	public Page<T> pageSize(final int thePageSize) {
		setPageSize(thePageSize);
		return this;
	}

	public int getFirstResult() {
		return (pageNo - 1) * pageSize;
	}

	public List<T> getResult() {
		return result;
	}

	
	public void setResult(final List<T> result) {
		this.result = result;
	}
	
	public long getTotalCount() {
		return totalCount;
	}

	
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}
	
	public long getTotalPages() {
		if (totalCount <= 0)
			return 0;

		long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}
	
	public int getIntTotalPages() {
		if (totalCount <= 0)
			return 0;

		int count = Long.valueOf(totalCount / pageSize).intValue();
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	public int getNextPage() {
		if (isHasNext())
			return pageNo + 1;
		else
			return pageNo;
	}

	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	public int getPrePage() {
		if (isHasPre())
			return pageNo - 1;
		else
			return pageNo;
	}
	
}
