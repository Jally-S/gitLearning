package org.jvsun.dao.proxy;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import org.jvsun.dao.NeedDAO;
import org.jvsun.dao.impl.NeedDAOImpl;
import org.jvsun.pojo.NeedPOJO;
import org.jvsun.tools.JDBCHelper;

public class NeedDAOProxy implements NeedDAO {
	Connection conn = null;
	NeedDAOImpl impl=null;
	public NeedDAOProxy(){
		try {
			this.conn=JDBCHelper.getConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.impl=new NeedDAOImpl(this.conn);
	}
	public boolean doIns(NeedPOJO pojo) {
		boolean flag = this.impl.doIns(pojo);
		this.close();
		return flag;
	}

	public boolean doDel(BigDecimal cId) {
		boolean flag = this.impl.doDel(cId);
		this.close();
		return flag;
	}

	public boolean doUpd(NeedPOJO pojo) {
		boolean flag = this.impl.doUpd(pojo);
		this.close();
		return flag;
	}

	public List<NeedPOJO> findAllByCusHobbyProduct(String customerName,
			String nhobby, String productName, int pageSize, int pageCurrent) {
		List<NeedPOJO> list=this.impl.findAllByCusHobbyProduct(customerName,nhobby,productName, pageSize, pageCurrent);
		this.close();
		return list;
	}

	public int findAllCount() {
		int count=this.impl.findAllCount();
		this.close();
		return count;
	}

	public NeedPOJO findById(BigDecimal cid) {
		NeedPOJO pojo=this.impl.findById(cid);
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
