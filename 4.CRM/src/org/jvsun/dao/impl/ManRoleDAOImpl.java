package org.jvsun.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.jvsun.dao.ManRoleDAO;
import org.jvsun.pojo.RolePOJO;
import org.jvsun.pojo.VLoginPOJO;

public class ManRoleDAOImpl implements ManRoleDAO{
	Connection conn;

	public ManRoleDAOImpl(Connection conn) {
		this.conn = conn;
	}
	public boolean doDel(int loginId, BigDecimal roleId) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql = "delete from acount_role where login_id= ? and role_id= ? ";
			pstate = this.conn.prepareStatement(sql);
			pstate.setInt(1, loginId);
			pstate.setBigDecimal(2, roleId);
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

	public boolean doIns(int loginId, BigDecimal roleId) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			
			String sql = 
					"insert into acount_role (login_id, role_id )"
					+ "values(?,?)";
					

			pstate = this.conn.prepareStatement(sql);
		
			pstate.setInt(1, loginId);
			pstate.setBigDecimal(2, roleId);
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

	public List<VLoginPOJO> findAll(String userName,
			int pageSize, int pageCurrent) {
		List<VLoginPOJO> list = new ArrayList<VLoginPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		/*
		 * 
	private int loginId;//�˺�id
	private String account;//�˺�
	private String password;//����
	private String userName;//��ɫ
	private int userTape;//1λְ�� 0λ�ͻ�
	private BigDecimal userId;//�û�id
	*/
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select login_id,acount,password,user_name,user_type,user_id"
					
					+ " from (select  login_id,acount,password,user_name,user_type,user_id,rownum abc "
					+ " from v_login where user_name like ? ");

		
			sql.append(" order by user_id) where abc>? and abc<=? ");
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%" + userName + "%");
			pstate.setInt(2, (pageCurrent - 1) * pageSize);
			pstate.setInt(3, pageCurrent * pageSize);
			res = pstate.executeQuery();
			while (res.next()) {
				VLoginPOJO pojo = new VLoginPOJO(res.getInt(1), res.getString(2), res.getString(3), res.getString(4),res.getInt(5),res.getBigDecimal(6));
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

	public List<RolePOJO> findAll() {
		List<RolePOJO> list = new ArrayList<RolePOJO>();
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
			sql.append("select role_id,role_name,role_mark,is_delete from role ");


			pstate = this.conn.prepareStatement(sql.toString());
		
		
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

	public int findCountByNameState(String userName) {
		int count = 0;
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select count(user_name) from v_login where user_name like ? ");
			
		
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%"+userName+"%");
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

	public List<String> findUserRole(BigDecimal userId) {
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
			sql.append("select  r.role_name from v_login v, role r ,acount_role a where v.login_id=a.login_id and r.role_id=a.role_id  and v.user_id=?");

	
			pstate = this.conn.prepareStatement(sql.toString());
		
			pstate.setBigDecimal(1, userId);
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
