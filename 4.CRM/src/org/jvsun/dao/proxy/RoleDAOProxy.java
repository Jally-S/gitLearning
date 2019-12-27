package org.jvsun.dao.proxy;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import org.jvsun.dao.RoleDAO;
import org.jvsun.dao.impl.RoleDAOImpl;
import org.jvsun.dao.impl.WorkerDAOImpl;
import org.jvsun.pojo.RolePOJO;
import org.jvsun.pojo.WorkerPOJO;
import org.jvsun.tools.JDBCHelper;

public class RoleDAOProxy implements RoleDAO {
	Connection conn = null;
	RoleDAOImpl impl = null;

	public RoleDAOProxy() {
		try {
			this.conn = JDBCHelper.getConn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.impl = new RoleDAOImpl(this.conn);

	}

	public boolean doDel(BigDecimal roleId) {
		// TODO Auto-generated method stub
		boolean flag = this.impl.doDel(roleId);
		this.close();
		return flag;
	}

	public boolean doIns(RolePOJO pojo) {
		boolean flag = this.impl.doIns(pojo);
		this.close();
		return flag;
	}

	public boolean doUpd(RolePOJO pojo) {
		boolean flag = this.impl.doUpd(pojo);
		this.close();
		return flag;
	}

	public List<RolePOJO> findAll(String roleName, int isDelete, int pageSize,
			int pageCurrent) {
		List<RolePOJO> list = this.impl.findAll(roleName, isDelete,
				pageSize, pageCurrent);
		this.close();;
		return list;
	}

	public RolePOJO findByRoleId(BigDecimal roleId) {
		RolePOJO pojo = this.impl.findByRoleId(roleId);
		this.close();
		return pojo;
	}

	public int findCountByNameState(String roleName, int isDelete) {
		int count = this.impl.findCountByNameState(roleName, isDelete);
		this.close();
		return count;
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
