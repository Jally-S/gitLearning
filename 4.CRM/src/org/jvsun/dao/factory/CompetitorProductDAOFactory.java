package org.jvsun.dao.factory;

import org.jvsun.dao.CompetitorProductDAO;
import org.jvsun.dao.proxy.CompetitorProductDAOProxy;

public class CompetitorProductDAOFactory {
	public static CompetitorProductDAO getDAOInstance(){
		return new CompetitorProductDAOProxy();
	}
}
