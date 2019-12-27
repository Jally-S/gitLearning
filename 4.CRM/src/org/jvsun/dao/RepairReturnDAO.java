package org.jvsun.dao;

import java.math.BigDecimal;
import java.util.List;

import org.jvsun.pojo.RepairReturnPOJO;

/**
 * @author dalin
 *维修回执单接口
 */
public interface RepairReturnDAO {
	
	/**
	 * 
	 * 维修回执单的新增方法
	 * @param pojo
	 * @return
	 */
	public boolean doIns(RepairReturnPOJO pojo);
	/**
	 * 维修回执单的修改方法
	 * @param pojo
	 * @return
	 */
	public boolean doUpd(RepairReturnPOJO pojo);
	/**
	 * 维修回执单的删除方法
	 * @param reptId
	 * @return
	 */
	public boolean doDel(BigDecimal reptId);
	/**
	 * 查询维修回执单的数据笔数
	 * @param reptName
	 * @param pageSize
	 * @param pageCurrent
	 * @return
	 */
	public List<RepairReturnPOJO> findByReptName(String reptName,int pageSize,int pageCurrent);
	/**
	 * 根据条件查询维修回执单的所有数据
	 * @param reptName
	 * @return
	 */
	public int findCountByReptName(String reptName);
	/**
	 * 根据id查询维修回执单
	 * @param reptId
	 * @return
	 */
	public RepairReturnPOJO findByReptId(BigDecimal reptId);
	
/*	
	public List<RepairReturnVwPOJO> findRepairReturn(int pageSize,int pageCurrent,String reptName,String repairName);
	
	public int countRepairReturn(String reptName,String repairName);
	
	
*/	
	
	
	
	
	
}	
