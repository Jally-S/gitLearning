package org.jvsun.dao.factory;

import org.jvsun.dao.CustomerInfoDAO;
import org.jvsun.dao.proxy.CustomerInfoDAOProxy;
/**
 * 客户信息工厂类
 * @author Administrator
 *
 */
public class CustomerInfoDAOFactory {
	public static CustomerInfoDAO getDAOInstance(){
		return new CustomerInfoDAOProxy();
	}
}
