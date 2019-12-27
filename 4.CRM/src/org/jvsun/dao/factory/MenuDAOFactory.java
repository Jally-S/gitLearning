package org.jvsun.dao.factory;

import org.jvsun.dao.MenuDAO;
import org.jvsun.dao.proxy.MenuDAOProxy;
/**
 * 
 * @author dalin
 *
 */
public class MenuDAOFactory {
	public static MenuDAO getDAOInstance() {
		return new MenuDAOProxy();
	}
}
