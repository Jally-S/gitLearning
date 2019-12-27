package org.jvsun.dao.proxy;

import java.math.BigDecimal;
import java.sql.Connection;

import org.jvsun.dao.CustomerInfoDAO;
import org.jvsun.dao.impl.CustomerInfoDAOImpl;
import org.jvsun.pojo.CustomerPOJO;
import org.jvsun.tools.JDBCHelper;
/**
 * 客户信息代理类
 * @author Administrator
 *
 */
public class CustomerInfoDAOProxy implements CustomerInfoDAO {
	Connection conn = null;
	CustomerInfoDAOImpl impl =null;
	/*
	 *  构造方法
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
	 * 客户个人信息修改
	 * @see org.jvsun.dao.CustomerInfoDAO#doUpd(org.jvsun.pojo.CustomerPOJO)
	 */
	public boolean doUpd(CustomerPOJO pojo) {
		boolean flag = this.impl.doUpd(pojo);
		this.close();
		return flag;
	}
	/*
	 * 客户个人信息查询
	 * @see org.jvsun.dao.CustomerInfoDAO#findByWorkerId(java.math.BigDecimal)
	 */
	public CustomerPOJO findByWorkerId(BigDecimal customerId) {
		CustomerPOJO pojo = this.impl.findByWorkerId(customerId);
		this.close();
		return pojo;
	}
	/*
	 * 关闭数据库链接
	 */
	public void close(){
		try {
			this.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
