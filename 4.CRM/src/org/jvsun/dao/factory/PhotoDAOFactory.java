package org.jvsun.dao.factory;

import org.jvsun.dao.PhotoDAO;
import org.jvsun.dao.proxy.PhotoDAOProxy;

public class PhotoDAOFactory {
	public static PhotoDAO getDAOInstance(){
		return new PhotoDAOProxy();
	}
}
