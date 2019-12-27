package org.jvsun.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class RepairPOJO {
	private BigDecimal repairId;//���޵�ID
	private String productName;//��Ʒ��
	private String repairContent;//������Ϣ
	private Date upDate;//�ύ����
	private int isOver;//�Ƿ���
	private BigDecimal customerId;//�ͻ�ID
	private String reptContent;//���޴�����
	private int isDelete;//�Ƿ�ɾ��
	public BigDecimal getRepairId() {
		return repairId;
	}
	public void setRepairId(BigDecimal repairId) {
		this.repairId = repairId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getRepairContent() {
		return repairContent;
	}
	public void setRepairContent(String repairContent) {
		this.repairContent = repairContent;
	}
	public Date getUpDate() {
		return upDate;
	}
	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}
	public int getIsOver() {
		return isOver;
	}
	public void setIsOver(int isOver) {
		this.isOver = isOver;
	}
	public BigDecimal getCustomerId() {
		return customerId;
	}
	public void setCustomerId(BigDecimal customerId) {
		this.customerId = customerId;
	}
	public String getReptContent() {
		return reptContent;
	}
	public void setReptContent(String reptContent) {
		this.reptContent = reptContent;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public RepairPOJO(BigDecimal repairId, String productName,
			String repairContent, Date upDate, int isOver,
			BigDecimal customerId, String reptContent, int isDelete) {
		super();
		this.repairId = repairId;
		this.productName = productName;
		this.repairContent = repairContent;
		this.upDate = upDate;
		this.isOver = isOver;
		this.customerId = customerId;
		this.reptContent = reptContent;
		this.isDelete = isDelete;
	}
	public RepairPOJO(String productName, String repairContent, Date upDate,
			int isOver, BigDecimal customerId, String reptContent, int isDelete) {
		super();
		this.productName = productName;
		this.repairContent = repairContent;
		this.upDate = upDate;
		this.isOver = isOver;
		this.customerId = customerId;
		this.reptContent = reptContent;
		this.isDelete = isDelete;
	}
	public RepairPOJO(BigDecimal repairId, String productName,
			String repairContent, Date upDate, int isOver,
			BigDecimal customerId, int isDelete) {
		super();
		this.repairId = repairId;
		this.productName = productName;
		this.repairContent = repairContent;
		this.upDate = upDate;
		this.isOver = isOver;
		this.customerId = customerId;
		this.isDelete = isDelete;
	}
	public RepairPOJO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
