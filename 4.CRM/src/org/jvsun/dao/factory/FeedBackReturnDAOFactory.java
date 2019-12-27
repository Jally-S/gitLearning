package org.jvsun.dao.factory;

import org.jvsun.dao.FeedBackReturnDAO;
import org.jvsun.dao.proxy.FeedBackReturnDAOProxy;

/**
 * 
 * 反馈回执单的工厂类
 * @author Proso
 *
 */
public class FeedBackReturnDAOFactory {
	public static FeedBackReturnDAO getDAOInstance(){
		return new FeedBackReturnDAOProxy();
	}
}
