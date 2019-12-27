package org.jvsun.dao.proxy;

import java.math.BigDecimal;
import java.sql.Connection;

import org.jvsun.dao.CustomerInfoDAO;
import org.jvsun.dao.impl.CustomerInfoDAOImpl;
import org.jvsun.pojo.CustomerPOJO;
import org.jvsun.tools.JDBCHelper;
/**
 * �ͻ���Ϣ������
 * @author Administrator
 *
 */
public class CustomerInfoDAOProxy implements CustomerInfoDAO {
	Connection conn = null;
	CustomerInfoDAOImpl impl =null;
	/*
	 *  ���췽��
	 */
	public CustomerInfoDAOProxy(){
		try {
			this.conn = JDBCHelper.getConn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.impl=new CustomerInfoDAOImpl(this.conn);
	}
	/*
	 * �ͻ�������Ϣ�޸�
	 * @see org.jvsun.dao.CustomerInfoDAO#doUpd(org.jvsun.pojo.CustomerPOJO)
	 */
	public boolean doUpd(CustomerPOJO pojo) {
		boolean flag = this.impl.doUpd(pojo);
		this.close();
		return flag;
	}
	/*
	 * �ͻ�������Ϣ��ѯ
	 * @see org.jvsun.dao.CustomerInfoDAO#findByWorkerId(java.math.BigDecimal)
	 */
	public CustomerPOJO findByWorkerId(BigDecimal customerId) {
		CustomerPOJO pojo = this.impl.findByWorkerId(customerId);
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
