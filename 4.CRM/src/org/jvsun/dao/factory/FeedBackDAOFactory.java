package org.jvsun.dao.factory;

import org.jvsun.dao.FeedBackDAO;
import org.jvsun.dao.proxy.FeedBackDAOProxy;

/**
 * 反馈单工厂类
 * @author Proso
 *
 */
public class FeedBackDAOFactory {

	public static FeedBackDAO getDAOInstance(){
		return new FeedBackDAOProxy();
	}
}
