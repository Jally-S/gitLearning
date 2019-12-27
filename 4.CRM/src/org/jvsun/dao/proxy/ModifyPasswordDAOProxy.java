package org.jvsun.dao.proxy;

import java.math.BigDecimal;
import java.sql.Connection;

import org.jvsun.dao.ModifyPasswordDAO;
import org.jvsun.dao.WorkerInfoDAO;
import org.jvsun.dao.impl.ModifyPasswordDAOImpl;
import org.jvsun.dao.impl.NeedDAOImpl;
import org.jvsun.tools.JDBCHelper;

public class ModifyPasswordDAOProxy  implements ModifyPasswordDAO{
	Connection conn = null;
	ModifyPasswordDAOImpl impl=null;
	public ModifyPasswordDAOProxy(){
		try {
			this.conn=JDBCHelper.getConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.impl=new ModifyPasswordDAOImpl(this.conn);
	}
	public boolean doUpdate(String password, BigDecimal loginId) {
		boolean flag = this.impl.doUpdate(password, loginId);
		this.close();
		return flag;
	}
	public void close(){
		try {
			this.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
