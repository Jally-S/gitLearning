package org.jvsun.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.jvsun.dao.MenuDAO;
import org.jvsun.pojo.MenuPOJO;

public class MenuDAOImpl  implements MenuDAO {
	Connection conn;

	public MenuDAOImpl(Connection conn) {
		this.conn = conn;
	}

	

	public boolean doIns(MenuPOJO pojo) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			
			String sql = "insert into menu (menu_id,menu_path,menu_name,is_delete)"
					+ "values(?,?,?,1)";
			pstate = this.conn.prepareStatement(sql);
		
			pstate.setBigDecimal(1, pojo.getMenuId());
			pstate.setString(2, pojo.getMenuPath());
			pstate.setString(3, pojo.getMenuName());
			pstate.execute();
			this.conn.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
			// TODO: handle exception
		} finally {
			try {
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
		}
		return flag;
	}

	public boolean doUpd(MenuPOJO pojo) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			
			String sql = " update  menu set menu_path=?, menu_name=?,"
					+ " is_delete= ? where menu_id= ?";

			pstate = this.conn.prepareStatement(sql);

			pstate.setString(1, pojo.getMenuPath());
			pstate.setString(2, pojo.getMenuName());
			pstate.setInt(3, pojo.getIsDelete());
			pstate.setBigDecimal(4, pojo.getMenuId());

			pstate.execute();
			this.conn.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
			// TODO: handle exception
		} finally {
			try {
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
		}
		return flag;
	}

	public List<MenuPOJO> findAll(String menuName, int isDelete, int pageSize,
			int pageCurrent) {
		List<MenuPOJO> list = new ArrayList<MenuPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select menu_id,menu_path,menu_name,is_delete "
					
					+ " from (select menu_id,menu_path,menu_name,is_delete,rownum abc "
					+ " from menu where menu_name like ? ");

			if (isDelete != 2) {
				sql.append(" and is_delete = " + isDelete);
			}
			sql.append(" order by menu_id,is_delete desc) where abc>? and abc<=? ");
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%" + menuName + "%");
			pstate.setInt(2, (pageCurrent - 1) * pageSize);
			pstate.setInt(3, pageCurrent * pageSize);
			res = pstate.executeQuery();
			while (res.next()) {
				MenuPOJO pojo = new MenuPOJO(res.getBigDecimal(1), res.getString(2), res.getString(3), res.getInt(4));
				list.add(pojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				res.close();
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}

	public MenuPOJO findByMenuId(BigDecimal menuId) {
		PreparedStatement pstate = null;
		ResultSet res = null;
		MenuPOJO pojo  =null;
		try {
			String sql="select menu_path, menu_name, is_delete "
			
					+ " from  menu where menu_id = ? ";

		
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setBigDecimal(1,menuId);
		
			res = pstate.executeQuery();
			while (res.next()) {
				 pojo = new MenuPOJO(menuId, res
						.getString(1), res.getString(2), res.getInt(3));}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				res.close();
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return pojo;
	}

	public int findCountByNameState(String menuName, int isDelete) {
		int count = 0;
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select count(menu_name) from menu where menu_name like ? ");
			
			if(isDelete != 2){
				sql.append(" and is_delete = "+isDelete);
			}
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%"+menuName+"%");
			res = pstate.executeQuery();
			while(res.next()){
				count = res.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally{
			try {
				res.close();
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return count;
	}
}
