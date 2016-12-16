package com.application.base.core.obj;

import java.util.List;

public class Pagination<T> {

	private int pageNo = 1;
	
	private int pageSize = 10;
	
	private long pageCount = 1;
	
	private long recordsTotal = 0;
	
	private List<T> data = null;

	public Pagination() {
	}

	public Pagination(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public Pagination(List<T> data, int pageNo, int pageSize) {
		this.data = data;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public Pagination(List<T> data, int pageNo) {
		this.data = data;
		this.pageNo = pageNo;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public long getRecordsTotal() {

		return recordsTotal;
	}

	public void setRecordsTotal(long recordsTotal) {
		pageCount = recordsTotal > 0
				? (recordsTotal % pageSize == 0 ? recordsTotal / pageSize : recordsTotal / pageSize + 1) : 0;
		this.recordsTotal = recordsTotal;
	}

}
