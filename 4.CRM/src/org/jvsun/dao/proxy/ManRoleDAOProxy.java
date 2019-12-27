package org.jvsun.dao.proxy;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import org.jvsun.dao.ManRoleDAO;
import org.jvsun.dao.impl.ManRoleDAOImpl;
import org.jvsun.dao.impl.MenuDAOImpl;
import org.jvsun.pojo.MenuPOJO;
import org.jvsun.pojo.RolePOJO;
import org.jvsun.pojo.VLoginPOJO;
import org.jvsun.tools.JDBCHelper;

public class ManRoleDAOProxy implements ManRoleDAO {
	Connection conn = null;
	ManRoleDAOImpl impl = null;

	public ManRoleDAOProxy() {
		try {
			this.conn = JDBCHelper.getConn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.impl = new ManRoleDAOImpl(this.conn);

	}

	public boolean doDel(int loginId, BigDecimal roleId) {
		boolean flag = this.impl.doDel(loginId,roleId);
		this.close();
		return flag;
	}

	public boolean doIns(int loginId, BigDecimal roleId) {
		boolean flag = this.impl.doIns(loginId,roleId);
		this.close();
		return flag;
	}

	public List<VLoginPOJO> findAll(String userName, int pageSize,
			int pageCurrent) {
		List<VLoginPOJO> list = this.impl.findAll(userName, pageSize,
				pageCurrent);
		this.close();
		return list;
	}

	public List<RolePOJO> findAll() {
		List<RolePOJO> list = this.impl.findAll();
		this.close();
		return list;
	}

	public int findCountByNameState(String userName) {
		int count = this.impl.findCountByNameState(userName);
		this.close();
		return count;
	}

	public List<String> findUserRole(BigDecimal userId) {
		List<String> list = this.impl.findUserRole(userId);
		this.close();
		return list;
	}

	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}


}
