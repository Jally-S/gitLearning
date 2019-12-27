package org.jvsun.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

//contract_id cus_customer_id worker_id contract_name contract_content contract_time is_photo is_del 
/**
 *合同类
 */
public class ContractPOJO implements Serializable{
	private static final long serialVersionUID = 4727220397075923653L;
	private BigDecimal contractId;//合同ID
	private String contractName;//合同名
	private String contractContent;//合同内容
	private Date contractTime;//签订时间
	private int isPhoto;//是否拍照
	private BigDecimal customerId;//顾客id
	private BigDecimal workerId;//职工ID
	private String customerName;
	private String workerName;
	private int isDelete;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public BigDecimal getContractId() {
		return contractId;
	}
	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getContractContent() {
		return contractContent;
	}
	public void setContractContent(String contractContent) {
		this.contractContent = contractContent;
	}
	public Date getContractTime() {
		return contractTime;
	}
	public void setContractTime(Date contractTime) {
		this.contractTime = contractTime;
	}
	public int getIsPhoto() {
		return isPhoto;
	}
	public void setIsPhoto(int isPhoto) {
		this.isPhoto = isPhoto;
	}
	public BigDecimal getCustomerId() {
		return customerId;
	}
	public void setCustomerId(BigDecimal customerId) {
		this.customerId = customerId;
	}
	public BigDecimal getWorkerId() {
		return workerId;
	}
	public void setWorkerId(BigDecimal workerId) {
		this.workerId = workerId;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public ContractPOJO(BigDecimal contractId, String contractName,
			String contractContent, Date contractTime, int isPhoto,
			BigDecimal customerId, BigDecimal workerId, int isDelete) {
		super();
		this.contractId = contractId;
		this.contractName = contractName;
		this.contractContent = contractContent;
		this.contractTime = contractTime;
		this.isPhoto = isPhoto;
		this.customerId = customerId;
		this.workerId = workerId;
		this.isDelete = isDelete;
	}
	public ContractPOJO(BigDecimal contractId,BigDecimal customerId, 
			BigDecimal workerId, String contractName,
			String contractContent, int isPhoto) {
		this.contractId = contractId;
		this.contractName = contractName;
		this.contractContent = contractContent;
		this.isPhoto = isPhoto;
		this.customerId = customerId;
		this.workerId = workerId;
	}
	public ContractPOJO(BigDecimal contractId, String customerName,
			String workerName, String contractName, String contractContent,
			 int isPhoto) {

		this.contractId = contractId;
		this.customerName = customerName;
		this.workerName = workerName;
		this.contractName = contractName;
		this.contractContent = contractContent;
		this.isPhoto = isPhoto;

	}
	public ContractPOJO(BigDecimal customerName,
			BigDecimal workerName, String contractName, String contractContent,
			 int isPhoto) {
		this.customerId = customerName;
		this.workerId= workerName;
		this.contractName = contractName;
		this.contractContent = contractContent;
		this.isPhoto = isPhoto;

	}
	public ContractPOJO(String contractName,
			String contractContent, Date contractTime, int isPhoto,
			BigDecimal customerId, BigDecimal workerId, int isDelete) {
		this.contractName = contractName;
		this.contractContent = contractContent;
		this.contractTime = contractTime;
		this.isPhoto = isPhoto;
		this.customerId = customerId;
		this.workerId = workerId;
		this.isDelete = isDelete;
	}
	public ContractPOJO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
