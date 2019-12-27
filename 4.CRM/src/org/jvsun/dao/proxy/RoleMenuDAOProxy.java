package org.jvsun.dao.proxy;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import org.jvsun.dao.ManRoleDAO;
import org.jvsun.dao.RoleMenuDAO;
import org.jvsun.dao.impl.ManRoleDAOImpl;
import org.jvsun.dao.impl.RoleMenuDAOImpl;
import org.jvsun.pojo.MenuPOJO;
import org.jvsun.pojo.RolePOJO;
import org.jvsun.pojo.VLoginPOJO;
import org.jvsun.tools.JDBCHelper;

public class RoleMenuDAOProxy implements RoleMenuDAO{
	Connection conn = null;
	RoleMenuDAOImpl impl = null;

	public RoleMenuDAOProxy() {
		try {
			this.conn = JDBCHelper.getConn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.impl = new RoleMenuDAOImpl(this.conn);

	}

	public boolean doDel(BigDecimal roleId, BigDecimal menuId) {
		boolean flag = this.impl.doDel(roleId, menuId);
		this.close();
		return flag;
	}

	public boolean doIns(BigDecimal roleId, BigDecimal menuId) {
		boolean flag = this.impl.doIns(roleId, menuId);
		this.close();
		return flag;
	}

	public List<RolePOJO> findAll(String roleName, int pageSize,
			int pageCurrent) {
		List<RolePOJO> list = this.impl.findAll(roleName,pageSize,pageCurrent);
		this.close();
		return list;	
		}

	public List<MenuPOJO> findAll() {
		List<MenuPOJO> list = this.impl.findAll();
		this.close();
		return list;
	}

	public int findCountByNameState(String roleName) {
		int count = this.impl.findCountByNameState(roleName);
		this.close();
		return count;
	}

	public List<String> findRoleMenu(BigDecimal roleId) {
		List<String> list = this.impl.findRoleMenu(roleId);
		this.close();
		return list;
	}
	public void close(){
		try {
			this.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
