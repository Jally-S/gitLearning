package org.jvsun.dao.factory;

import org.jvsun.dao.ContractDAO;
import org.jvsun.dao.proxy.ContractDAOProxy;

public class ContractDAOFactory {
	public static ContractDAO getDAOInstance(){
		return new ContractDAOProxy();
	}
}
