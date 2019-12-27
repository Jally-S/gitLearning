package org.jvsun.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.jvsun.dao.UserLoginDAO;
import org.jvsun.pojo.MenuPOJO;
import org.jvsun.pojo.VLoginPOJO;

public class UserLoginDAOImpl implements UserLoginDAO {
	Connection conn;

	public UserLoginDAOImpl(Connection conn) {
		this.conn = conn;
	}

	public boolean checkLogin(String loginaccount ,String loginPassword ) {

		// TODO Auto-generated method stub
		boolean flag = false;
		PreparedStatement pstate = null;
		ResultSet res = null;
		// int roleMark ;
		try {
			String sql = "select count(user_id) from v_login v where v.acount = ? and v.password =?";
			pstate = this.conn.prepareStatement(sql);
			pstate.setString(1, loginaccount);
			pstate.setString(2, loginPassword);
			res = pstate.executeQuery();
			while (res.next()) {
				if (res.getInt(1) == 1) {
					flag = true;
				}
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
				// TODO: handle exception
			}
		}
		return flag;
	}

	public  VLoginPOJO findUserByLogin(String loginaccount) {
		PreparedStatement pstate = null;
		ResultSet res = null;
		VLoginPOJO pojo = null;
		try {
			String sql = " select login_id,password,user_name,user_type,user_id from v_login v where v.acount = ?  ";
			pstate = this.conn.prepareStatement(sql);
			pstate.setString(1, loginaccount);
			res = pstate.executeQuery();
			while (res.next()) {
				 pojo = new VLoginPOJO(res.getInt(1),loginaccount,res.getString(2),res.getString(3),res.getInt(4),res.getBigDecimal(5));
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
		return pojo;
	}

	public List<MenuPOJO> findMenuByLoginId(int logintId){
		List<MenuPOJO> list = new ArrayList<MenuPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			String sql = "select distinct d.menu_id,d.menu_path,d.menu_name from login a,role b ,acount_role c,menu d," +
					"role_menu e where a.login_id = c.login_id and b.role_id = c.role_id and b.is_delete = 1" +
					" and c.role_id = e.role_id and d.menu_id = e.menu_id and d.is_delete = 1 and length(d.menu_id)=1" +
					" and a.login_id=?  order by d.menu_id";
			
			pstate = this.conn.prepareStatement(sql);
			pstate.setInt(1,logintId);
			res = pstate.executeQuery();
			while (res.next()) {
			MenuPOJO menupojo = new MenuPOJO(res.getBigDecimal(1),res.getString(2),res.getString(3),1);
				list.add(menupojo);
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

	public List<MenuPOJO> findChildMenuByFaherId(BigDecimal menuId) {
		List<MenuPOJO> list = new ArrayList<MenuPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			
			String sql = "select m.menu_id,m.menu_path,m.menu_name from menu m  where length(m.menu_id)=4 and m.menu_id like ? and is_delete=1 ";
			pstate = this.conn.prepareStatement(sql);
			
			pstate.setString(1,menuId+"%");
			res = pstate.executeQuery();
			while (res.next()) {
				MenuPOJO pojo =new MenuPOJO(res.getBigDecimal(1),res.getString(2),res.getString(3),1);
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
}
