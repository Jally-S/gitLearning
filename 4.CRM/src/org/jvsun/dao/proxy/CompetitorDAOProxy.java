package org.jvsun.dao.proxy;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import org.jvsun.dao.CompetitorDAO;
import org.jvsun.dao.impl.CompetitorDAOImpl;
import org.jvsun.pojo.CompetitorPOJO;
import org.jvsun.tools.JDBCHelper;

public class CompetitorDAOProxy implements CompetitorDAO {
	Connection conn = null;
	CompetitorDAOImpl impl=null;
	public CompetitorDAOProxy(){
		try {
			this.conn=JDBCHelper.getConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.impl=new CompetitorDAOImpl(this.conn);
	}
	public boolean doIns(CompetitorPOJO pojo) {
		boolean flag = this.impl.doIns(pojo);
		this.close();
		return flag;
	}
	public boolean doDel(BigDecimal cid) {
		boolean flag = this.impl.doDel(cid);
		this.close();
		return flag;
	}
	public boolean doUpd(CompetitorPOJO pojo) {
		boolean flag = this.impl.doUpd(pojo);
		this.close();
		return flag;
	}
	public List<CompetitorPOJO> findAllByNameClass(String name,
			int clas, int pageSize, int pageCurrent) {
		List<CompetitorPOJO> list=this.impl.findAllByNameClass(name,clas, pageSize, pageCurrent);
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
	public CompetitorPOJO findById(BigDecimal pid) {
		CompetitorPOJO pojo=this.impl.findById(pid);
		this.close();
		return pojo;
	}
}
