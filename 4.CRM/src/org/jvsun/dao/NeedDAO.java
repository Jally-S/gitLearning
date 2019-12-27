package org.jvsun.dao;

import java.math.BigDecimal;
import java.util.List;

import org.jvsun.pojo.NeedPOJO;

/**
 * 需求类接口
 *
 */
public interface NeedDAO {
	public boolean doIns(NeedPOJO pojo);
	public boolean doDel(BigDecimal cId);
	public boolean doUpd(NeedPOJO pojo);
	/**
	 * @param customerId
	 * @param nhobby
	 * @param productName
	 * @param pageSize
	 * @param pageCurrent
	 * @return 根据客户id，客户爱好和产品名来查询
	 */
	public List<NeedPOJO> findAllByCusHobbyProduct(String customerName,String nhobby,String productName,int pageSize, int pageCurrent);
	public int findAllCount();
	public NeedPOJO findById(BigDecimal cid);
}
