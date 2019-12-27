package org.jvsun.dao;

import java.math.BigDecimal;
import java.util.List;
import org.jvsun.pojo.FeedBackPOJO;
import org.jvsun.pojo.ProductPOJO;

/**
 * @author dalin
 *反馈信息接口
 */
public interface FeedBackDAO {

	public boolean doIns(FeedBackPOJO pojo) ;
	public int findById(BigDecimal customerId);
	public FeedBackPOJO findOneByCustomerId(BigDecimal feedBackId);
	public List<FeedBackPOJO> findById(String productName,BigDecimal customerId,int pageSize, int pageCurrent);
	public boolean doDel(BigDecimal feedBackId);
	public boolean update(BigDecimal feedBackId,String FeedbackContent);
	public List<ProductPOJO> findByCustomerId(BigDecimal customerId);
	public List<FeedBackPOJO> findByProductName(String productName,int isOver,int pageSize,int pageCurrent);
	public int findCountByProductName(String productName,int isOver);
	public FeedBackPOJO findByFeedBackId(BigDecimal feedBackId);
}   
