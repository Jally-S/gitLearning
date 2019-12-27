package org.jvsun.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jvsun.dao.RepairReturnDAO;
import org.jvsun.pojo.RepairReturnPOJO;

public class RepairRetrunDAOImpl implements RepairReturnDAO {
	Connection conn = null;
	public RepairRetrunDAOImpl(Connection conn){
		this.conn = conn;
	}
	
	
	
	
	
	//删除维修回执单实现方法
	public boolean doDel(BigDecimal reptId) {
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql = "update repair_return set is_delete = 0 where rept_id = ?";
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, reptId);
			pstate.execute();
			this.conn.commit();
			return true;
		} catch (SQLException e) {
			try {
				this.conn.rollback();
				return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	
	
	//新增维修回执单实现方法
	public boolean doIns(RepairReturnPOJO pojo) {
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql = "begin " +
					" insert into repair_return(rept_id, rept_name, rept_content, rept_time, is_delete, repair_id )values(CRM_SEQUENCES.nextval,?,?,?,1,?);" +
					" update  repair  set is_over=1 where repair_id = ? ;" +
					" end;";
			pstate = this.conn.prepareStatement(sql);
			pstate.setString(1, pojo.getReptName());
			pstate.setString(2, pojo.getReptContent());
			pstate.setDate(3, new java.sql.Date(pojo.getReptTime().getTime()));
			pstate.setBigDecimal(4, pojo.getRepairId());
			pstate.setBigDecimal(5, pojo.getRepairId());
			pstate.execute();
			this.conn.commit();
			return true;
		} catch (SQLException e) {
			try {
				this.conn.rollback();
				return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	
	
	
	
	//修改维修回执单实现方法
	public boolean doUpd(RepairReturnPOJO pojo) {
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql = "update repair_return set  rept_name = ? , rept_content = ?  where rept_id = ? ";
			pstate = this.conn.prepareStatement(sql);
			pstate.setString(1, pojo.getReptName());
			pstate.setString(2, pojo.getReptContent());
			pstate.setBigDecimal(3, pojo.getReptId());
			pstate.execute();
			this.conn.commit();
			return true;
		} catch (SQLException e) {
			try {
				this.conn.rollback();
				return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	
	
	
	
	
	
	

	
	
	
	
	//根据id查询维修回执单实现方法
	public RepairReturnPOJO findByReptId(BigDecimal reptId) {
		RepairReturnPOJO pojo = null;
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			String sql = "select rept_name, rept_content, rept_time,repair_id  from repair_return where rept_id = ? and is_delete = 1";
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, reptId);
			res = pstate.executeQuery();
			while(res.next()){
				pojo = new RepairReturnPOJO(reptId,res.getString(1),res.getString(2),res.getDate(3),1,res.getBigDecimal(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				res.close();
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return pojo;
	}

	
	
	
	
	
	//查询维修回执单数据笔数的实现方法
	public List<RepairReturnPOJO> findByReptName(String reptName, int pageSize,
			int pageCurrent) {
		List<RepairReturnPOJO> list = new ArrayList<RepairReturnPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select rept_id,rept_name, rept_content, rept_time,repair_id " +
					" from (select rept_id, rept_name, rept_content, rept_time,repair_id,rownum abc" +
					" from repair_return where rept_name like ? and is_delete = 1)where abc>? and abc<=? order by rept_time desc ");
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%"+reptName+"%");
			pstate.setInt(2, (pageCurrent-1)*pageSize);
			pstate.setInt(3, pageCurrent*pageSize);
			res = pstate.executeQuery();
			while(res.next()){
				RepairReturnPOJO pojo = new RepairReturnPOJO(res.getBigDecimal(1),res.getString(2),res.getString(3),res.getDate(4),1,res.getBigDecimal(5));
				list.add(pojo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				res.close();
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}

	
	
	
	
	

	
	
	
	
	//根据名称查询维修回执单的实现方法
	public int findCountByReptName(String reptName) {
		int count = 0;
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select count(rept_id) from repair_return where rept_name like ? and is_delete = 1");
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%"+reptName+"%");
			res = pstate.executeQuery();
			while(res.next()){
				count = res.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				res.close();
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return count;
	}





/*	public List<RepairReturnVwPOJO> findRepairReturn(int pageSize,
			int pageCurrent, String reptName, String repairName) {
		List<RepairReturnVwPOJO> list = new ArrayList<RepairReturnVwPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select rept_id, rept_name, rept_content, rept_time, is_delete, repair_id, repair_name, repair_content,r_up_date, r_is_over, customer_id, customer_name,product_id,product_name " +
					" from ( select rept_id, rept_name, rept_content, rept_time, is_delete, repair_id, repair_name, repair_content,r_up_date, r_is_over, customer_id, customer_name,product_id,product_name ,rownum ind " +
					" from repair_return_vw where  rept_name like ? and repair_name like ?  and is_delete = 1 order by rept_time desc ) where  ind > ? and ind <= ? ");
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%"+reptName+"%");
			pstate.setString(2, "%"+repairName+"%");
			pstate.setInt(3, (pageCurrent-1)*pageSize);
			pstate.setInt(4, pageCurrent*pageSize);
			res = pstate.executeQuery();
			while(res.next()){
				RepairReturnVwPOJO pojo = new RepairReturnVwPOJO(res.getInt(1),res.getString(2),res.getString(3),res.getDate(4),res.getInt(5),res.getInt(6),res.getString(7),res.getString(8),new java.util.Date(res.getDate(9).getTime()),res.getInt(10),res.getInt(11),res.getString(12),res.getInt(13),res.getString(14));
				list.add(pojo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				res.close();
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}





	public int countRepairReturn(String reptName, String repairName) {
		int count = 0;
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select count(*) from repair_return_vw  where  rept_name like ? and repair_name like ? and is_delete = 1");
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%"+reptName+"%");
			pstate.setString(2, "%"+repairName+"%");
			res = pstate.executeQuery();
			while(res.next()){
				count = res.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				res.close();
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return count;
	}
*/
}
