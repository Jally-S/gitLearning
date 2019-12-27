package org.jvsun.dao;

import java.util.List;

import org.jvsun.pojo.ProductPOJO;

/**
 *产品接口类
 */
public interface ProductDAO {
	/**
	 * @param pojo
	 * @return 新增产品
	 */
	public boolean doIns(ProductPOJO pojo);
	/**
	 * @param productId
	 * @return 删除产品
	 */
	public boolean doDel(int productId);
	/**
	 * @param pojo
	 * @return 更新产品信息
	 */
	public boolean doUpd(ProductPOJO pojo);
	/**
	 * @param productId
	 * @return 根据id，名称或者类别查询查询产品信息
	 */
	public List<ProductPOJO> findAllByNamePriceClass(String name,double price,int clas,int pageSize, int pageCurrent);
	
	/**
	 * 查询产品数量
	 * @return
	 */
	public int findAllCount();
	
	/**
	 * @param pid
	 * @return  根据产品id查询产品信息
	 */
	public ProductPOJO findById(int pid);
}
