package org.jvsun.dao.proxy;

import java.sql.Connection;
import java.util.List;

import org.jvsun.dao.ProductDAO;
import org.jvsun.dao.impl.ProductDAOImpl;
import org.jvsun.pojo.ProductPOJO;
import org.jvsun.tools.JDBCHelper;

public class ProductDAOProxy implements ProductDAO {
	Connection conn = null;
	ProductDAOImpl impl=null;
	public ProductDAOProxy(){
		try {
			this.conn=JDBCHelper.getConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.impl=new ProductDAOImpl(this.conn);
	}
	public boolean doIns(ProductPOJO pojo) {
		boolean flag = this.impl.doIns(pojo);
		this.close();
		return flag;
	}
	public boolean doDel(int productId) {
		boolean flag = this.impl.doDel(productId);
		this.close();
		return flag;
	}
	public boolean doUpd(ProductPOJO pojo) {
		boolean flag = this.impl.doUpd(pojo);
		this.close();
		return flag;
	}
	public List<ProductPOJO> findAllByNamePriceClass(String name,double price, 
			int clas, int pageSize, int pageCurrent) {
		List<ProductPOJO> list=this.impl.findAllByNamePriceClass(name,price,clas, pageSize, pageCurrent);
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
	public ProductPOJO findById(int pid) {
		ProductPOJO pojo=this.impl.findById(pid);
		this.close();
		return pojo;
	}


	
	
}
