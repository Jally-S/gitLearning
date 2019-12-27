package org.jvsun.dao.factory;

import org.jvsun.dao.CompetitorDAO;
import org.jvsun.dao.proxy.CompetitorDAOProxy;

public class CompetitorDAOFactory {
	public static CompetitorDAO getDAOInstance(){
		return new CompetitorDAOProxy();
	}
}
