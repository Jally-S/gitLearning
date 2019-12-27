package org.jvsun.dao.proxy;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import org.jvsun.dao.CustomerDAO;
import org.jvsun.dao.impl.CustomerDAOImpl;
import org.jvsun.pojo.CustomerPOJO;
import org.jvsun.pojo.ProductPOJO;
import org.jvsun.tools.JDBCHelper;

public class CustomerDAOProxy implements CustomerDAO {
	Connection conn = null;
	CustomerDAOImpl impl=null;
	public CustomerDAOProxy(){
		try {
			this.conn=JDBCHelper.getConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.impl=new CustomerDAOImpl(this.conn);
	}
	public boolean doIns(CustomerPOJO pojo) {
		boolean flag = this.impl.doIns(pojo);
		this.close();
		return flag;
	}
	public boolean doDel(BigDecimal customerId) {
		boolean flag = this.impl.doDel(customerId);
		this.close();
		return flag;
	}
	public boolean doUpd(CustomerPOJO pojo) {
		boolean flag = this.impl.doUpd(pojo);
		this.close();
		return flag;
	}
	public CustomerPOJO findById(BigDecimal cid) {
		CustomerPOJO pojo = this.impl.findById(cid);
		this.close();
		return pojo;
	
	}
	public CustomerPOJO findByName(String cname) {
		CustomerPOJO pojo = this.impl.findByName(cname);
		this.close();
		return pojo;
	}
	public List<CustomerPOJO> findAll(int pageSize, int pageCurrent) {
		List<CustomerPOJO> list=this.impl.findAll(pageSize, pageCurrent);
		this.close();
		return list;
	}
	public int findAllCount() {
		int count=this.impl.findAllCount();
		this.close();
		return count;
	}
	public void close(){
		try {
			this.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<ProductPOJO> findProductByCid(BigDecimal cid) {
		List<ProductPOJO> list = this.impl.findProductByCid(cid);
		this.close();
		return list;
	}
	public boolean doInsCP(BigDecimal pid, BigDecimal cid) {
		boolean flag = this.impl.doInsCP(pid,cid);
		this.close();
		return flag;
	}
	public boolean doDelCP(BigDecimal pid, BigDecimal cid) {
		boolean flag = this.impl.doDelCP(pid,cid);
		this.close();
		return flag;
	}
	

}
