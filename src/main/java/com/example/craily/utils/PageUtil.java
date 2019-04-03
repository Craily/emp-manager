package com.example.craily.utils;

import java.io.Serializable;

/**
 * 分页工具
 * 
 * @author Craily
 *
 */
public class PageUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 78442880675518769L;
	private int page;//当前页
	private int limit;//条数

	/**
	 * 
	 */
	public PageUtil() {
		super();
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
