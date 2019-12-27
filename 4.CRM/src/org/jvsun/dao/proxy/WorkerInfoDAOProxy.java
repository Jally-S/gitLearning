package org.jvsun.dao.proxy;

import java.math.BigDecimal;
import java.sql.Connection;

import org.jvsun.dao.WorkerInfoDAO;
import org.jvsun.dao.impl.WorkerInfoDAOImpl;
import org.jvsun.pojo.WorkerPOJO;
import org.jvsun.tools.JDBCHelper;
/**
 * 客户个人信息
 * @author Administrator
 *
 */
public class WorkerInfoDAOProxy implements WorkerInfoDAO {
	Connection conn = null;
	WorkerInfoDAOImpl impl =null;
	/*
	 * 构造方法
	 */
	public WorkerInfoDAOProxy(){
		try {
			this.conn = JDBCHelper.getConn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.impl=new WorkerInfoDAOImpl(this.conn);
	}
	/*
	 * 修改职工的个人信息(non-Javadoc)
	 * @see org.jvsun.dao.WorkerInfoDAO#doUpd(org.jvsun.pojo.WorkerPOJO)
	 */
	public boolean doUpd(WorkerPOJO pojo) {
		boolean flag = this.impl.doUpd(pojo);
		this.close();
		return flag;
	}
	/*
	 * 查询职工个人信息(non-Javadoc)
	 * @see org.jvsun.dao.WorkerInfoDAO#findByWorkerId(java.math.BigDecimal)
	 */
	public WorkerPOJO findByWorkerId(BigDecimal workerId) {
		WorkerPOJO pojo = this.impl.findByWorkerId(workerId);
		this.close();
		return pojo;
	}
	/*
	 * 关闭数据库连接
	 */
	public void close(){
		try {
			this.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
