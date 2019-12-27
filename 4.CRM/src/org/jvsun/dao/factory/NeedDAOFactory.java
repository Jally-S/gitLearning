package org.jvsun.dao.factory;

import org.jvsun.dao.NeedDAO;
import org.jvsun.dao.proxy.NeedDAOProxy;

public class NeedDAOFactory {
	public static NeedDAO getDAOInstance(){
		return new NeedDAOProxy();
	}
}
