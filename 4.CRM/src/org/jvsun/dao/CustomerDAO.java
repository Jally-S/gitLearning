package org.jvsun.dao;

import java.math.BigDecimal;
import java.util.List;

import org.jvsun.pojo.CustomerPOJO;
import org.jvsun.pojo.ProductPOJO;

/**
 *客户接口类
 */
public interface CustomerDAO {
	/**
	 * @param pojo 客户类
	 * @return 新增客户
	 */
	public boolean doIns(CustomerPOJO pojo);
	/**
	 * @param customerId  客户id
	 * @return 删除客户
	 */
	public boolean doDel(BigDecimal customerId);
	/**
	 * @param pojo  客户类
	 * @return 更新客户信息
	 */
	public boolean doUpd(CustomerPOJO pojo);
	/**
	 * @param customerId
	 * @return 根据客户id，名称查询客户所有信息
	 */
	public CustomerPOJO findById(BigDecimal cid);
	/**
	 * @param customerName
	 * @return 根据客户名查询全部信息
	 */
	public CustomerPOJO findByName(String cname);
	/**
	 * 列出所有客户
	 */
	public List<CustomerPOJO> findAll(int pageSize, int pageCurrent);
	
	/**
	 *查询客户数量
	 */
	public int findAllCount();
	/**
	 * 根据客户id查询已购买的产品
	 * @param cid
	 * @return
	 */
	public List<ProductPOJO> findProductByCid(BigDecimal cid);
	
	public boolean doInsCP(BigDecimal pid,BigDecimal cid);
	
	public boolean doDelCP(BigDecimal pid,BigDecimal cid);
}
