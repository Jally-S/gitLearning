package org.jvsun.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.jvsun.dao.RoleMenuDAO;
import org.jvsun.pojo.MenuPOJO;
import org.jvsun.pojo.RolePOJO;

public class RoleMenuDAOImpl implements RoleMenuDAO{
	Connection conn;
	public RoleMenuDAOImpl(Connection conn) {
		this.conn = conn;
	}
	public boolean doDel(BigDecimal roleId, BigDecimal menuId) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql = "delete from role_menu where role_id= ? and menu_id= ? ";
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, roleId);
			pstate.setBigDecimal(2, menuId);
			pstate.execute();// ִ��
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
		// TODO Auto-generated method stub
	}

	public boolean doIns(BigDecimal roleId, BigDecimal menuId) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			
			String sql = 
					"insert into role_menu (role_id, menu_id )"

					+ "values(?,?)";
					

			pstate = this.conn.prepareStatement(sql);
		
			pstate.setBigDecimal(1, roleId);
			pstate.setBigDecimal(2, menuId);
			
			pstate.execute();// ִ��
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

	public List<RolePOJO> findAll(String roleName,int pageSize,int pageCurrent) {
		List<RolePOJO> list = new ArrayList<RolePOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		/*
		 * 
	private BigDecimal roleId;// ��ɫID
	private String roleName;// ��ɫ��
	private int roleMark;// ��ɫ��ʶ
	private int isDelete;

	*/
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select role_id,role_name,role_mark,is_delete"
					
					+ " from (select   role_id,role_name,role_mark,is_delete ,rownum abc "
					+ " from role where role_name like ? ");

		
			sql.append(" order by role_id) where abc>? and abc<=? ");
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%" + roleName + "%");
			pstate.setInt(2, (pageCurrent - 1) * pageSize);
			pstate.setInt(3, pageCurrent * pageSize);
			res = pstate.executeQuery();
			while (res.next()) {
				RolePOJO pojo = new RolePOJO(res.getBigDecimal(1), res.getString(2), res.getInt(3), res.getInt(4));
				list.add(pojo);

			}
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
		return list;
	}

	public List<MenuPOJO> findAll(){
		List<MenuPOJO> list = new ArrayList<MenuPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		/*
		 * 

	
	*/
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select menu_id,menu_path,menu_name,is_delete from menu ");


			pstate = this.conn.prepareStatement(sql.toString());
		
		
			res = pstate.executeQuery();
			while (res.next()) {
				MenuPOJO pojo = new MenuPOJO(res.getBigDecimal(1), res.getString(2), res.getString(3), res.getInt(4));
				list.add(pojo);

			}
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
		return list;
	}

	public int findCountByNameState(String roleName) {
		int count = 0;
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select count(role_name) from role where role_name like ? ");
			
		
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%"+roleName+"%");
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

	
	public List<String> findRoleMenu(BigDecimal roleId) {
		List<String> list = new ArrayList<String>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		/*
		 * private BigDecimal roleId;// ��ɫID
	private String roleName;// ��ɫ��
	private int roleMark;// ��ɫ��ʶ
	private int isDelete;

	
	*/
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select  m.menu_name from role r , menu m  ,role_menu  a where r.role_id=a.role_id  and m.menu_id=a.menu_id and r.role_id=?");

	
			pstate = this.conn.prepareStatement(sql.toString());
		
			pstate.setBigDecimal(1, roleId);
			res = pstate.executeQuery();
			while (res.next()) {
				String RoleName = res.getString(1);
				list.add(RoleName);

			}
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
		return list;
	}





}
