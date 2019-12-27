package org.jvsun.dao.factory;

import org.jvsun.dao.RoleDAO;
import org.jvsun.dao.WorkerDAO;
import org.jvsun.dao.proxy.RoleDAOProxy;
import org.jvsun.dao.proxy.WorkerDAOProxy;

public class RoleDAOFactory {
	public static RoleDAO getDAOInstance() {
		return new RoleDAOProxy();
	}
}
