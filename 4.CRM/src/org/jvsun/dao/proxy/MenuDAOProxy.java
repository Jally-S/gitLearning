package org.jvsun.dao.proxy;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import org.jvsun.dao.MenuDAO;
import org.jvsun.dao.RoleDAO;
import org.jvsun.dao.impl.MenuDAOImpl;
import org.jvsun.dao.impl.RoleDAOImpl;
import org.jvsun.pojo.MenuPOJO;
import org.jvsun.pojo.RolePOJO;
import org.jvsun.pojo.WorkerPOJO;
import org.jvsun.tools.JDBCHelper;

public class MenuDAOProxy implements MenuDAO {
	Connection conn = null;
	MenuDAOImpl impl = null;

	public MenuDAOProxy() {
		try {
			this.conn = JDBCHelper.getConn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.impl = new MenuDAOImpl(this.conn);

	}

	public boolean doIns(MenuPOJO pojo) {
		boolean flag = this.impl.doIns(pojo);
		this.close();
		return flag;
	}

	public boolean doUpd(MenuPOJO pojo) {
		boolean flag = this.impl.doUpd(pojo);
		this.close();
		return flag;
	}

	

	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	public List<MenuPOJO> findAll(String menuName, int isDelete, int pageSize,
			int pageCurrent) {
		List<MenuPOJO> list = this.impl.findAll(menuName, isDelete,
				pageSize, pageCurrent);
		this.close();
		return list;	}

	public MenuPOJO findByMenuId(BigDecimal menuId) {
		MenuPOJO pojo = this.impl.findByMenuId(menuId);
		this.close();
		return pojo;
	}
	public int findCountByNameState(String menuName, int isDelete) {
		int count = this.impl.findCountByNameState(menuName, isDelete);
		this.close();
		return count;
	}


	

}
