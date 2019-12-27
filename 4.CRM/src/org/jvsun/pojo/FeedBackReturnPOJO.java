package org.jvsun.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FeedBackReturnPOJO  implements Serializable {
	private static final long serialVersionUID = 166410492406653860L;
	private BigDecimal feedId;
	private String feedName;
	private String feedContent;
	private Date feedTime;
	private BigDecimal feedBackId;
	private BigDecimal workerId;
	private int isDelete;
	public BigDecimal getFeedId() {
		return feedId;
	}
	public void setFeedId(BigDecimal feedId) {
		this.feedId = feedId;
	}
	public String getFeedName() {
		return feedName;
	}
	public void setFeedName(String feedName) {
		this.feedName = feedName;
	}
	public String getFeedContent() {
		return feedContent;
	}
	public void setFeedContent(String feedContent) {
		this.feedContent = feedContent;
	}
	public Date getFeedTime() {
		return feedTime;
	}
	public void setFeedTime(Date feedTime) {
		this.feedTime = feedTime;
	}
	public BigDecimal getFeedBackId() {
		return feedBackId;
	}
	public void setFeedBackId(BigDecimal feedBackId) {
		this.feedBackId = feedBackId;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public FeedBackReturnPOJO(BigDecimal feedId, String feedName,
			String feedContent, Date feedTime, BigDecimal feedBackId,
			BigDecimal workerId, int isDelete) {
		super();
		this.feedId = feedId;
		this.feedName = feedName;
		this.feedContent = feedContent;
		this.feedTime = feedTime;
		this.feedBackId = feedBackId;
		this.workerId = workerId;
		this.isDelete = isDelete;
	}
	public FeedBackReturnPOJO(String feedName, String feedContent,
			Date feedTime, BigDecimal feedBackId, BigDecimal workerId,
			int isDelete) {
		super();
		this.feedName = feedName;
		this.feedContent = feedContent;
		this.feedTime = feedTime;
		this.feedBackId = feedBackId;
		this.workerId = workerId;
		this.isDelete = isDelete;
	}
	public FeedBackReturnPOJO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}