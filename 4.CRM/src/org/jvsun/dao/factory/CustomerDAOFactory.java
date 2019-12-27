package org.jvsun.dao.factory;

import org.jvsun.dao.CustomerDAO;
import org.jvsun.dao.proxy.CustomerDAOProxy;

public class CustomerDAOFactory {
	public static CustomerDAO getDAOInstance(){
		return new CustomerDAOProxy();
	}
}
