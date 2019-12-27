package org.jvsun.dao.factory;

import org.jvsun.dao.WorkerInfoDAO;
import org.jvsun.dao.proxy.WorkerInfoDAOProxy;
/**
 * 职工信息工厂类
 * @author Administrator
 *
 */
public class WorkerInfoDAOFactory {
	public static WorkerInfoDAO getDAOInstance(){
		return new WorkerInfoDAOProxy();
	}
}
