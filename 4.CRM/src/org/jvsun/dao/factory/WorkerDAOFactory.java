package org.jvsun.dao.factory;

import org.jvsun.dao.UserLoginDAO;
import org.jvsun.dao.WorkerDAO;
import org.jvsun.dao.proxy.UserLoginDAOProxy;
import org.jvsun.dao.proxy.WorkerDAOProxy;

public class WorkerDAOFactory {
	public static WorkerDAO getDAOInstance() {
		return new WorkerDAOProxy();
	}
}
