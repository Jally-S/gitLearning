package org.jvsun.dao.factory;

import org.jvsun.dao.WorkerInfoDAO;
import org.jvsun.dao.proxy.WorkerInfoDAOProxy;
/**
 * ְ����Ϣ������
 * @author Administrator
 *
 */
public class WorkerInfoDAOFactory {
	public static WorkerInfoDAO getDAOInstance(){
		return new WorkerInfoDAOProxy();
	}
}
