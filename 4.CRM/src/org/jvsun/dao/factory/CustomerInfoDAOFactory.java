package org.jvsun.dao.factory;

import org.jvsun.dao.CustomerInfoDAO;
import org.jvsun.dao.proxy.CustomerInfoDAOProxy;
/**
 * �ͻ���Ϣ������
 * @author Administrator
 *
 */
public class CustomerInfoDAOFactory {
	public static CustomerInfoDAO getDAOInstance(){
		return new CustomerInfoDAOProxy();
	}
}
