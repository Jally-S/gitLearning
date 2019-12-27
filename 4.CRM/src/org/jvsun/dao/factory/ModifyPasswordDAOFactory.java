package org.jvsun.dao.factory;

import org.jvsun.dao.ModifyPasswordDAO;
import org.jvsun.dao.proxy.ModifyPasswordDAOProxy;

public class ModifyPasswordDAOFactory {
	public static ModifyPasswordDAO getDAOInstance(){
		return new ModifyPasswordDAOProxy();
	}
}
