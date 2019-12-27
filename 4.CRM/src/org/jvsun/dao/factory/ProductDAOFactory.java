package org.jvsun.dao.factory;

import org.jvsun.dao.ProductDAO;
import org.jvsun.dao.proxy.ProductDAOProxy;

public class ProductDAOFactory {
	public static ProductDAO getDAOInstance(){
		return new ProductDAOProxy();
	}
}
