package org.jvsun.dao.factory;

import org.jvsun.dao.RepairReturnDAO;
import org.jvsun.dao.proxy.RepairReturnDAOProxy;

/**
 * 维修回执单工厂类
 * @author Proso
 *
 */
public class RepairReturnDAOFactory {

	public static RepairReturnDAO getDAOInstance(){
		return new RepairReturnDAOProxy();
	}
}
