package org.jvsun.dao.proxy;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import org.jvsun.dao.ContractDAO;
import org.jvsun.dao.impl.ContractDAOImpl;
import org.jvsun.pojo.ContractPOJO;
import org.jvsun.tools.JDBCHelper;

public class ContractDAOProxy implements ContractDAO {
	Connection conn = null;
	ContractDAOImpl impl=null;
	public ContractDAOProxy(){
		try {
			this.conn=JDBCHelper.getConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.impl=new ContractDAOImpl(this.conn);
	}
	public boolean doIns(ContractPOJO pojo) {
		boolean flag = this.impl.doIns(pojo);
		this.close();
		return flag;
	}

	public boolean doDel(int cId) {
		boolean flag = this.impl.doDel(cId);
		this.close();
		return flag;
	}

	public boolean doUpd(ContractPOJO pojo) {
		boolean flag = this.impl.doUpd(pojo);
		this.close();
		return flag;
	}

	public List<ContractPOJO> findAllByNameWnameCname(String name,
			String Wname, String Cname, int pageSize, int pageCurrent) {
		List<ContractPOJO> list=this.impl.findAllByNameWnameCname(name, Wname, Cname, pageSize, pageCurrent);
		this.close();
		return list;
	}

	public int findAllCount() {
		int count=this.impl.findAllCount();
		this.close();
		return count;
	}

	public ContractPOJO findById(BigDecimal cid) {
		ContractPOJO pojo=this.impl.findById(cid);
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
