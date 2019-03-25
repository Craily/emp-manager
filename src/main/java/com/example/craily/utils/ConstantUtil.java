package com.example.craily.utils;

public enum ConstantUtil {

	Fail(0, "失败"), Success(1, "成功"), empty(2, "结果为空"), error(3, "异常");

	private int code;
	private String msg;

	/**
	 * @param code
	 * @param msg
	 */
	private ConstantUtil(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
