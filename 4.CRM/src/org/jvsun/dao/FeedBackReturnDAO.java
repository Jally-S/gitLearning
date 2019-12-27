package org.jvsun.dao;

import java.math.BigDecimal;
import java.util.List;

import org.jvsun.pojo.FeedBackReturnPOJO;

/**
 * @author dalin
 *反馈回执接口
 */
public interface FeedBackReturnDAO{
	
	
	/**
	 * 反馈回执单的新增接口方法
	 * @param pojo
	 * @return
	 */
	public boolean doIns(FeedBackReturnPOJO pojo );
	
	
	/**
	 * 反馈回执单的删除方法
	 * @param feedId
	 * @return
	 */
	public boolean doDel(BigDecimal feedId);
	
	
	/**
	 * 反馈单回执单的修改方法
	 * @param pojo
	 * @return
	 */
	public boolean doUpd(FeedBackReturnPOJO pojo);
	
	
	
	/**
	 * 查询反馈回执单的数据笔数
	 * @param feedName
	 * @param pageSize
	 * @param pageCurrent
	 * @return
	 */
	public List<FeedBackReturnPOJO> findByFeedName(String feedName,int pageSize,int pageCurrent);
	
	
	
	/**
	 * 根据条件查询反馈回执单的所有数据
	 * @param feedName
	 * @return
	 */
	public int findCountByFeedName(String feedName);
	
	/**
	 * 根据id查询反馈回执单
	 * @param feedId
	 * @return
	 */
	public FeedBackReturnPOJO findByFeedId(BigDecimal feedId);
	
	
/*	public List<FeedbackReturnVwPOJO> findFeedBackReturn(int pageSize,int pageCurrent,String feedName,String productName);
	
	public int countRepairReturn(String feedName,String productName);
*/	
	
	
	
	
	
	
	
}