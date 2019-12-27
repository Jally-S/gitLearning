package org.jvsun.dao.proxy;

import java.math.BigDecimal;
import java.sql.Connection;

import org.jvsun.dao.WorkerInfoDAO;
import org.jvsun.dao.impl.WorkerInfoDAOImpl;
import org.jvsun.pojo.WorkerPOJO;
import org.jvsun.tools.JDBCHelper;
/**
 * �ͻ�������Ϣ
 * @author Administrator
 *
 */
public class WorkerInfoDAOProxy implements WorkerInfoDAO {
	Connection conn = null;
	WorkerInfoDAOImpl impl =null;
	/*
	 * ���췽��
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
	 * �޸�ְ���ĸ�����Ϣ(non-Javadoc)
	 * @see org.jvsun.dao.WorkerInfoDAO#doUpd(org.jvsun.pojo.WorkerPOJO)
	 */
	public boolean doUpd(WorkerPOJO pojo) {
		boolean flag = this.impl.doUpd(pojo);
		this.close();
		return flag;
	}
	/*
	 * ��ѯְ��������Ϣ(non-Javadoc)
	 * @see org.jvsun.dao.WorkerInfoDAO#findByWorkerId(java.math.BigDecimal)
	 */
	public WorkerPOJO findByWorkerId(BigDecimal workerId) {
		WorkerPOJO pojo = this.impl.findByWorkerId(workerId);
		this.close();
		return pojo;
	}
	/*
	 * �ر����ݿ�����
	 */
	public void close(){
		try {
			this.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
