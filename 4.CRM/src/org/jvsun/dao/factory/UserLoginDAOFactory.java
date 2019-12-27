package org.jvsun.dao.factory;

import org.jvsun.dao.UserLoginDAO;
import org.jvsun.dao.proxy.UserLoginDAOProxy;



public class UserLoginDAOFactory {
	public static UserLoginDAO getDAOInstance(){
		return new UserLoginDAOProxy();
	}

}
