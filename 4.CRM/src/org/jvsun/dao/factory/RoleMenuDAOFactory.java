package org.jvsun.dao.factory;

import org.jvsun.dao.RoleMenuDAO;
import org.jvsun.dao.UserLoginDAO;
import org.jvsun.dao.proxy.RoleMenuDAOProxy;
import org.jvsun.dao.proxy.UserLoginDAOProxy;

public class RoleMenuDAOFactory {
	public static RoleMenuDAO getDAOInstance(){
		return new RoleMenuDAOProxy();
	}
}
