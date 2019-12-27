package org.jvsun.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FeedBackPOJO implements Serializable {
	private BigDecimal feedbackId;//����id
	private BigDecimal customerId;//�ͻ�id
	private Date feedbackDate;//��������
	private String productName;//��Ʒ��
	private String feedbackContent;//������Ϣ
	private String feedContent;//������ִ��Ϣ
	private int isOver;//�Ƿ���
	private int is_delete;//�Ƿ�ɾ��
	public String getFeedContent() {
		return feedContent;
	}
	public void setFeedContent(String feedContent) {
		this.feedContent = feedContent;
	}
	public int getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(int isDelete) {
		is_delete = isDelete;
	}
	public BigDecimal getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(BigDecimal feedbackId) {
		this.feedbackId = feedbackId;
	}
	public BigDecimal getCustomerId() {
		return customerId;
	}
	public void setCustomerId(BigDecimal customerId) {
		this.customerId = customerId;
	}
	public Date getFeedbackDate() {
		return feedbackDate;
	}
	public void setFeedbackDate(Date feedbackDate) {
		this.feedbackDate = feedbackDate;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getFeedbackContent() {
		return feedbackContent;
	}
	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}
	public int getIsOver() {
		return isOver;
	}
	public void setIsOver(int isOver) {
		this.isOver = isOver;
	}
	
	public FeedBackPOJO(BigDecimal feedbackId, BigDecimal customerId,
			Date feedbackDate, String productName, String feedbackContent,
			String feedContent, int isOver, int isDelete) {
		super();
		this.feedbackId = feedbackId;
		this.customerId = customerId;
		this.feedbackDate = feedbackDate;
		this.productName = productName;
		this.feedbackContent = feedbackContent;
		this.feedContent = feedContent;
		this.isOver = isOver;
		is_delete = isDelete;
	}
	
	public FeedBackPOJO(BigDecimal customerId, Date feedbackDate,
			String productName, String feedbackContent, String feedContent,
			int isOver, int isDelete) {
		super();
		this.customerId = customerId;
		this.feedbackDate = feedbackDate;
		this.productName = productName;
		this.feedbackContent = feedbackContent;
		this.feedContent = feedContent;
		this.isOver = isOver;
		is_delete = isDelete;
	}
	public FeedBackPOJO(BigDecimal feedbackId, BigDecimal customerId,
			Date feedbackDate, String productName, String feedbackContent,
			int isOver, int isDelete) {
		super();
		this.feedbackId = feedbackId;
		this.customerId = customerId;
		this.feedbackDate = feedbackDate;
		this.productName = productName;
		this.feedbackContent = feedbackContent;
		this.isOver = isOver;
		is_delete = isDelete;
	}
	public FeedBackPOJO() {
		super();
		// TODO Auto-generated constructor stub
	} 
}
