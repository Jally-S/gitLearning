package org.jvsun.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.jvsun.dao.RoleDAO;
import org.jvsun.pojo.RolePOJO;

public class RoleDAOImpl implements RoleDAO {
	Connection conn;

	public RoleDAOImpl(Connection conn) {
		this.conn = conn;
	}
	public boolean doDel(BigDecimal roleId) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql = "update  role set is_delete=0 where role_id=? and is_delete=1";
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, roleId);
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

	public boolean doIns(RolePOJO pojo) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			
			String sql = "insert into role (role_id,role_name,role_mark,is_delete)"
					+ "values(CRM_2.nextval,?,?,1)";
			pstate = this.conn.prepareStatement(sql);
		
			pstate.setString(1, pojo.getRoleName());
			pstate.setInt(2, pojo.getRoleMark());
			
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

	public boolean doUpd(RolePOJO pojo) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			
			String sql = " update  role set role_name=?, role_mark=?,"
					+ " is_delete= ? where role_id= ?";

			pstate = this.conn.prepareStatement(sql);

			pstate.setString(1, pojo.getRoleName());
			pstate.setInt(2, pojo.getRoleMark());
			pstate.setInt(3, pojo.getIsDelete());
			pstate.setBigDecimal(4, pojo.getRoleId());

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

	public List<RolePOJO> findAll(String roleName, int isDelete, int pageSize,
			int pageCurrent) {
		List<RolePOJO> list = new ArrayList<RolePOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select role_id,role_name, role_mark, is_delete "
					
					+ " from (select role_id,role_name, role_mark, is_delete,rownum abc "
					+ " from role where role_name like ? ");

			if (isDelete != 2) {
				sql.append(" and is_delete = " + isDelete);// 1Ϊ����0Ϊͣ��2Ϊȫ��
			}
			sql.append(" ) where abc>? and abc<=? order by role_id,is_delete desc");
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

	public RolePOJO findByRoleId(BigDecimal roleId) {

		PreparedStatement pstate = null;
		ResultSet res = null;
		RolePOJO pojo  =null;
		try {
			String sql="select role_name, role_mark, is_delete "
			
					+ " from  role where role_id = ? ";

		
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setBigDecimal(1,roleId);
		
			res = pstate.executeQuery();
			while (res.next()) {
				 pojo = new RolePOJO(roleId, res
						.getString(1), res.getInt(2), res.getInt(3));}
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

	public int findCountByNameState(String roleName, int isDelete) {
		int count = 0;
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select count(role_name) from role where role_name like ? ");
			
			if(isDelete != 2){
				sql.append(" and is_delete = "+isDelete);
			}
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

}
