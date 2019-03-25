package com.example.craily.utils;

import java.io.Serializable;
import java.util.Map;

/**
 * 前台响应类
 * 
 * @author Craily
 *
 */
public class ResponeUtil<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8984673570183635867L;
	private int status = 0;// 状态0失败；1成功
	private String msg;// 信息
	private T data;// 数据

	/**
	 * 
	 */
	public ResponeUtil() {
		super();
	}

	/**
	 * @param status
	 * @param msg
	 */
	public ResponeUtil(int status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}

	/**
	 * @param status
	 * @param msg
	 * @param data
	 */
	public ResponeUtil(int status, String msg, T data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
