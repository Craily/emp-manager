package com.example.craily.model;

import java.io.Serializable;

public class OperationsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4452062945224022977L;

	private String operationsNo;

	private String jobNo;

	private String menuNo;

	private String operationsName;

	private String LAY_CHECKED;

	/**
	 * 
	 */
	public OperationsBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getOperationsNo() {
		return operationsNo;
	}

	public void setOperationsNo(String operationsNo) {
		this.operationsNo = operationsNo;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}

	public String getOperationsName() {
		return operationsName;
	}

	public void setOperationsName(String operationsName) {
		this.operationsName = operationsName;
	}

	public String getLAY_CHECKED() {
		return LAY_CHECKED;
	}

	public void setLAY_CHECKED(String lAY_CHECKED) {
		LAY_CHECKED = lAY_CHECKED;
	}

}
