package org.jvsun.dao.factory;

import org.jvsun.dao.RepairDAO;
import org.jvsun.dao.proxy.RepairDAOProxy;

/**
 * 报修单代理类
 * @author Proso
 *
 */
public class RepairDAOFactory {

	public static RepairDAO getDAOInstance(){
		return new RepairDAOProxy();
	}
}
