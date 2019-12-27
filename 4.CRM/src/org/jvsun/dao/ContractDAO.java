package org.jvsun.dao;
import java.math.BigDecimal;
import java.util.List;

import org.jvsun.pojo.ContractPOJO;

/**
 *合同接口
 */
public interface ContractDAO {
	/**
	 * 新增合同
	 * @param pojo
	 * @return
	 */
	public boolean doIns(ContractPOJO pojo);
	/**
	 * 根据id删除合同
	 * @param ContractId
	 * @return
	 */
	public boolean doDel(int ContractId);
	/**
	 * 更新合同
	 * @param pojo
	 * @return
	 */
	public boolean doUpd(ContractPOJO pojo);

	/**
	 * @param name
	 * @param Wname
	 * @param Cname
	 * @param pageSize
	 * @param pageCurrent
	 * @return  根据合同名，职工名和客户名查询合同
	 */
	public List<ContractPOJO> findAllByNameWnameCname(String name,String Wname,String Cname,int pageSize, int pageCurrent);
	
	/**
	 * 查询合同数量
	 * @return
	 */
	public int findAllCount();
	
	/**
	 * @param pid
	 * @return  根据id查询合同
	 */
	public ContractPOJO findById(BigDecimal cid);
	
}
