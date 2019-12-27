package org.jvsun.dao.factory;

import org.jvsun.dao.ManRoleDAO;
import org.jvsun.dao.proxy.ManRoleDAOProxy;

public class ManRoleDAOFactory {
	public static ManRoleDAO getDAOInstance() {
		return new ManRoleDAOProxy();
	}
}
