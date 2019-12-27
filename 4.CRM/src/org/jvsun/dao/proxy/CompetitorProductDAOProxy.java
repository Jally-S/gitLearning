package org.jvsun.dao.proxy;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import org.jvsun.dao.CompetitorProductDAO;
import org.jvsun.dao.impl.CompetitorProductDAOImpl;
import org.jvsun.pojo.CompetitorProductPOJO;
import org.jvsun.tools.JDBCHelper;

public class CompetitorProductDAOProxy implements CompetitorProductDAO{
	Connection conn = null;
	CompetitorProductDAOImpl impl=null;
	public CompetitorProductDAOProxy(){
		try {
			this.conn=JDBCHelper.getConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.impl=new CompetitorProductDAOImpl(this.conn);
	}
	public boolean doIns(CompetitorProductPOJO pojo) {
		boolean flag = this.impl.doIns(pojo);
		this.close();
		return flag;
	}

	public boolean doDel(BigDecimal productId) {
		boolean flag = this.impl.doDel(productId);
		this.close();
		return flag;
	}

	public boolean doUpd(CompetitorProductPOJO pojo) {
		boolean flag = this.impl.doUpd(pojo);
		this.close();
		return flag;
	}

	public List<CompetitorProductPOJO> findAllByPnameCnameClass(String pname,
			String cname, int clas, int pageSize, int pageCurrent) {
		List<CompetitorProductPOJO> list=this.impl.findAllByPnameCnameClass(pname,cname,clas, pageSize, pageCurrent);
		this.close();
		return list;
	}

	public int findAllCount() {
		int count=this.impl.findAllCount();
		this.close();
		return count;
	}

	public CompetitorProductPOJO findById(BigDecimal pid) {
		CompetitorProductPOJO pojo=this.impl.findById(pid);
		this.close();
		return pojo;
	}
	public void close(){
		try {
			this.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
