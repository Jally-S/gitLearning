package org.jvsun.dao;

import java.math.BigDecimal;
import java.util.List;

import org.jvsun.pojo.CompetitorPOJO;

/**
 *竞争对手接口
 */
public interface CompetitorDAO {
	/**
	 * @param 
	 * @return 新增竞争对手
	 */
	public boolean doIns(CompetitorPOJO pojo);
	/**
	 * @param 
	 * @return 删除竞争对手
	 */
	public boolean doDel(BigDecimal cId);
	/**
	 * @param pojo
	 * @return 更新对手信息
	 */
	public boolean doUpd(CompetitorPOJO pojo);
	/**
	 * @param productId
	 * @return 根据名称或者级别查询对手信息
	 */
	public List<CompetitorPOJO> findAllByNameClass(String name,int clas,int pageSize, int pageCurrent);
	
	/**
	 * 查询对手数量
	 * @return
	 */
	public int findAllCount();
	
	/**
	 * @param pid
	 * @return  根据id查询对手信息
	 */
	public CompetitorPOJO findById(BigDecimal cid);
}
