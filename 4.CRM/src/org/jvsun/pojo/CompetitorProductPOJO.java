package org.jvsun.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 竞争对手产品类
 */
public class CompetitorProductPOJO implements Serializable{
	private static final long serialVersionUID = 3344278872384333146L;
	private BigDecimal comProductId;	//产品id
	private String comProductName;   //产品名
	private BigDecimal competitorId;   //对应对手id
	private String comName;
	private int comProductClass;  //竞争等级
	private int isDelete;
	public BigDecimal getComProductId() {
		return comProductId;
	}
	public void setComProductId(BigDecimal comProductId) {
		this.comProductId = comProductId;
	}
	public String getComProductName() {
		return comProductName;
	}
	public void setComProductName(String comProductName) {
		this.comProductName = comProductName;
	}
	public BigDecimal getCompetitorId() {
		return competitorId;
	}
	public void setCompetitorId(BigDecimal competitorId) {
		this.competitorId = competitorId;
	}
	public int getComProductClass() {
		return comProductClass;
	}
	public void setComProductClass(int comProductClass) {
		this.comProductClass = comProductClass;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	} 
	public CompetitorProductPOJO(BigDecimal comProductId,
			String comProductName, BigDecimal competitorId,
			int comProductClass, int isDelete) {
		super();
		this.comProductId = comProductId;
		this.comProductName = comProductName;
		this.competitorId = competitorId;
		this.comProductClass = comProductClass;
		this.isDelete = isDelete;
	}
	public CompetitorProductPOJO(String comProductName,
			int comProductClass) {

		this.comProductName = comProductName;
		this.comProductClass = comProductClass;
	}
	public CompetitorProductPOJO(String comProductName, BigDecimal competitorId,
			int comProductClass, int isDelete) {
		this.comProductName = comProductName;
		this.competitorId = competitorId;
		this.comProductClass = comProductClass;
		this.isDelete = isDelete;
	}
	public CompetitorProductPOJO() {
		super();
	}
	public CompetitorProductPOJO(BigDecimal comProductId,String comProductName, String comName,
			int comProductClass) {
		this.comProductId = comProductId;
		this.comProductName = comProductName;
		this.comName = comName;
		this.comProductClass = comProductClass;
	}
	public CompetitorProductPOJO(BigDecimal competitorId,String comProductName, 
			int comProductClass,int isDelete) {
		this.competitorId = competitorId;
		this.comProductName = comProductName;
		this.comProductClass = comProductClass;
		this.isDelete = isDelete;
	}
	public CompetitorProductPOJO(BigDecimal comProductId,String comProductName, 
			int comProductClass) {
		this.comProductId = comProductId;
		this.comProductName = comProductName;
		this.comProductClass = comProductClass;
		
	}
	public CompetitorProductPOJO(BigDecimal comProductId,String competitorName,String comProductName,
			int comProductClass, int isDel) {
		
		this.comProductId = comProductId;
		this.comName = competitorName;
		this.comProductName = comProductName;
		this.comProductClass = comProductClass;
		this.isDelete=isDel;
	}
	
}
