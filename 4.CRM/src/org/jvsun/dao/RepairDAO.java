package org.jvsun.dao;

import java.math.BigDecimal;
import java.util.List;

import org.jvsun.pojo.ProductPOJO;
import org.jvsun.pojo.RepairPOJO;

/**
 * @author dalin
 *报修接口
 */
public interface RepairDAO {

	public boolean doIns(RepairPOJO pojo);

	public int findCountById(BigDecimal customerId);

	public RepairPOJO findByRepairId(BigDecimal repairId);

	public List<RepairPOJO> findByProduct(String productName,BigDecimal customerId,int pageSize,int pageCurrent);

	public boolean doUpd(BigDecimal repairId,String repairContent);

	public boolean doDel(BigDecimal repairId);

	public List<ProductPOJO> findByCustomerId(BigDecimal customerId);

	public List<RepairPOJO> findByProductName(String productName,int isOver,int pageSize,int pageCurrent);

	public int findCountByProductName(String productName,int isOver);
	/**
	 * 根据id查询报修单
	 * @param repairId
	 * @return
	 */
	public RepairPOJO findByRepairIdJi(BigDecimal repairId);
	
	
}
