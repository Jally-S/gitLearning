package org.jvsun.dao;

import java.math.BigDecimal;
import java.util.List;

import org.jvsun.pojo.CompetitorProductPOJO;

/**
 * @author dalin
 *竞争对手产品接口
 */
public interface CompetitorProductDAO {
	/**
	 * @param pojo
	 * @return 新增产品
	 */
	public boolean doIns(CompetitorProductPOJO pojo);
	/**
	 * @param productId
	 * @return 删除产品
	 */
	public boolean doDel(BigDecimal productId);
	/**
	 * @param pojo
	 * @return 更新产品信息
	 */
	public boolean doUpd(CompetitorProductPOJO pojo);
	/**
	 * @param productId
	 * @return 根据名称,对手名称或者类别查询查询产品信息
	 */
	public List<CompetitorProductPOJO> findAllByPnameCnameClass(String pname,String cname,int clas,int pageSize, int pageCurrent);
	
	/**
	 * 查询产品数量
	 * @return
	 */
	public int findAllCount();
	
	/**
	 * @param pid
	 * @return  根据产品id查询产品信息
	 */
	public CompetitorProductPOJO findById(BigDecimal pid);
}