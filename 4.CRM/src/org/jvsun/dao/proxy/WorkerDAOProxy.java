package org.jvsun.dao.proxy;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import org.jvsun.dao.WorkerDAO;
import org.jvsun.dao.impl.UserLoginDAOImpl;
import org.jvsun.dao.impl.WorkerDAOImpl;
import org.jvsun.pojo.MenuPOJO;
import org.jvsun.pojo.WorkerPOJO;
import org.jvsun.tools.JDBCHelper;

public class WorkerDAOProxy implements WorkerDAO {
	Connection conn = null;
	WorkerDAOImpl impl = null;

	public WorkerDAOProxy() {
		try {
			this.conn = JDBCHelper.getConn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.impl = new WorkerDAOImpl(this.conn);

	}

	public boolean doDel(BigDecimal workerId) {
		// TODO Auto-generated method stub
		boolean flag = this.impl.doDel(workerId);
		this.close();
		return flag;
	}

	public boolean doIns(WorkerPOJO pojo) {
		boolean flag = this.impl.doIns(pojo);
		this.close();
		return flag;
	}

	

	public boolean doUpd(WorkerPOJO pojo) {
		boolean flag = this.impl.doUpd(pojo);
		this.close();
		return flag;
	}

	public List<WorkerPOJO> findAll(String workerName, int isDelete,
			int pageSize, int pageCurrent) {
		List<WorkerPOJO> list = this.impl.findAll(workerName, isDelete,
				pageSize, pageCurrent);
		this.close();;
		return list;
	}

	public WorkerPOJO findByWorkerId(BigDecimal workerId) {
		WorkerPOJO pojo = this.impl.findByWorkerId(workerId);
		this.close();
		return pojo;
	}

	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	public int findCountByNameState(String workerName, int isDelete) {
		// TODO Auto-generated method stub
		int count = this.impl.findCountByNameState(workerName, isDelete);
		this.close();
		return count;
	}
}
