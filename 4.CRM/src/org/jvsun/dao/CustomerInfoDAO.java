package org.jvsun.dao;

import java.math.BigDecimal;

import org.jvsun.pojo.CustomerPOJO;

/**
 * @author dalin
 *客户信息接口
 */
public interface CustomerInfoDAO {
	public  CustomerPOJO findByWorkerId(BigDecimal customerId);
	public boolean doUpd(CustomerPOJO pojo);
}
