package org.jvsun.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RepairReturnPOJO  implements Serializable {
	private static final long serialVersionUID = -5214517738483279282L;
	private BigDecimal reptId;
	private String reptName;
	private String reptContent;
	private Date reptTime;
	private BigDecimal repairId;
	private int isDelete;
	public BigDecimal getReptId() {
		return reptId;
	}
	public void setReptId(BigDecimal reptId) {
		this.reptId = reptId;
	}
	public String getReptName() {
		return reptName;
	}
	public void setReptName(String reptName) {
		this.reptName = reptName;
	}
	public String getReptContent() {
		return reptContent;
	}
	public void setReptContent(String reptContent) {
		this.reptContent = reptContent;
	}
	public Date getReptTime() {
		return reptTime;
	}
	public void setReptTime(Date reptTime) {
		this.reptTime = reptTime;
	}
	public BigDecimal getRepairId() {
		return repairId;
	}
	public void setRepairId(BigDecimal repairId) {
		this.repairId = repairId;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public RepairReturnPOJO(BigDecimal reptId, String reptName,
			String reptContent, Date reptTime, int isDelete, BigDecimal repairId) {
		super();
		this.reptId = reptId;
		this.reptName = reptName;
		this.reptContent = reptContent;
		this.reptTime = reptTime;
		this.repairId = repairId;
		this.isDelete = isDelete;
	}
	public RepairReturnPOJO(String reptName, String reptContent, Date reptTime, int isDelete,
			BigDecimal repairId) {
		super();
		this.reptName = reptName;
		this.reptContent = reptContent;
		this.reptTime = reptTime;
		this.repairId = repairId;
		this.isDelete = isDelete;
	}
	public RepairReturnPOJO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
