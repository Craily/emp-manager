package com.example.craily.utils;

import java.io.Serializable;
import java.util.Map;

/**
 * 前台响应类
 * @author Craily
 *
 */
public class ResponeUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8984673570183635867L;
	private int status = 0;//状态0失败；1成功
	private String msg;//信息
	private Map<String, ?> data;//数据

	/**
	 * 
	 */
	public ResponeUtil() {
		// TODO Auto-generated constructor stub
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

	public Map<String, ?> getData() {
		return data;
	}

	public void setData(Map<String, ?> data) {
		this.data = data;
	}

}
