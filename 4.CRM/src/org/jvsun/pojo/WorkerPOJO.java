package org.jvsun.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class WorkerPOJO implements Serializable {

	private BigDecimal workerId;// 职工ID
	private String workerName;// 职工名
	private int workerSex;// 性别
	private String workerTel;// 电话
	private String workerAdress;// 地址
	private String wAccount;// 账号
	private int isDelete;

	public BigDecimal getWorkerId() {
		return workerId;
	}

	public void setWorkerId(BigDecimal workerId) {
		this.workerId = workerId;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public int getWorkerSex() {
		return workerSex;
	}

	public void setWorkerSex(int workerSex) {
		this.workerSex = workerSex;
	}

	public String getWorkerTel() {
		return workerTel;
	}

	public void setWorkerTel(String workerTel) {
		this.workerTel = workerTel;
	}

	public String getWorkerAdress() {
		return workerAdress;
	}

	public void setWorkerAdress(String workerAdress) {
		this.workerAdress = workerAdress;
	}

	public String getwAccount() {
		return wAccount;
	}

	public void setwAccount(String wAccount) {
		this.wAccount = wAccount;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public WorkerPOJO(BigDecimal workerId, String workerName, int workerSex,
			String workerTel, String workerAdress, String wAccount, int isDelete) {
		super();
		this.workerId = workerId;
		this.workerName = workerName;
		this.workerSex = workerSex;
		this.workerTel = workerTel;
		this.workerAdress = workerAdress;
		this.wAccount = wAccount;
		this.isDelete = isDelete;
	}

	public WorkerPOJO(String workerName, int workerSex, String workerTel,
			String workerAdress, String wAccount, int isDelete) {
		super();

		this.workerName = workerName;
		this.workerSex = workerSex;
		this.workerTel = workerTel;
		this.workerAdress = workerAdress;
		this.wAccount = wAccount;
		this.isDelete = isDelete;
	}

	public WorkerPOJO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WorkerPOJO(BigDecimal workerId, String workerName, int workerSex,
			String workerTel, String workerAdress) {
		super();
		this.workerId = workerId;
		this.workerName = workerName;
		this.workerSex = workerSex;
		this.workerTel = workerTel;
		this.workerAdress = workerAdress;
	}

}
