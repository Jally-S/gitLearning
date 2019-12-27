package org.jvsun.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 竞争对手类
 */
public class CompetitorPOJO implements Serializable{
	private static final long serialVersionUID = -5973295713094705317L;
	private BigDecimal competitorId;  //竞争对手id
	private String competitorName;//竞争对手姓名
	private int competitorClass;//竞争对手等级
	private int isDelete;//是否删除
	public BigDecimal getCompetitorId() {
		return competitorId;
	}
	public void setCompetitorId(BigDecimal competitorId) {
		this.competitorId = competitorId;
	}
	public String getCompetitorName() {
		return competitorName;
	}
	public void setCompetitorName(String competitorName) {
		this.competitorName = competitorName;
	}
	public int getCompetitorClass() {
		return competitorClass;
	}
	public void setCompetitorClass(int competitorClass) {
		this.competitorClass = competitorClass;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public CompetitorPOJO(BigDecimal competitorId, String competitorName,
			int competitorClass, int isDelete) {
		super();
		this.competitorId = competitorId;
		this.competitorName = competitorName;
		this.competitorClass = competitorClass;
		this.isDelete = isDelete;
	}
	public CompetitorPOJO(String competitorName,
			int competitorClass, int isDelete) {
		this.competitorName = competitorName;
		this.competitorClass = competitorClass;
		this.isDelete = isDelete;
	}
	public CompetitorPOJO(BigDecimal competitorId,String competitorName,
			int competitorClass) {
		this.competitorId = competitorId;
		this.competitorName = competitorName;
		this.competitorClass = competitorClass;
	}
	public CompetitorPOJO(String competitorName,
			int competitorClass) {
		this.competitorName = competitorName;
		this.competitorClass = competitorClass;
	}
	public CompetitorPOJO() {
		super();
	} 
	
}
