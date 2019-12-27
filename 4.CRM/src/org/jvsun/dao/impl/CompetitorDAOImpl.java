package org.jvsun.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jvsun.dao.CompetitorDAO;
import org.jvsun.pojo.CompetitorPOJO;

public class CompetitorDAOImpl implements CompetitorDAO {
	Connection conn;
	public CompetitorDAOImpl(Connection conn){
		this.conn = conn;
	}
	public boolean doIns(CompetitorPOJO pojo) {
		boolean flag=false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql="insert into competitor(competitor_id ,competitor_name, competitor_level)values(DH3.nextval,?,?)";
			pstate = this.conn.prepareStatement(sql);
			pstate.setString(1, pojo.getCompetitorName());
			pstate.setInt(2, pojo.getCompetitorClass());
			pstate.execute();
			this.conn.commit();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				pstate.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean doDel(BigDecimal cId) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql = "update competitor set is_del=0 where competitor_id = ?";
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, cId);
			pstate.execute();
			this.conn.commit();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally{
			try {
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return flag;
	}

	public boolean doUpd(CompetitorPOJO pojo) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql = "update competitor set competitor_name = ?,competitor_level =? where competitor_id = ?";
			pstate = this.conn.prepareStatement(sql);
			pstate.setString(1, pojo.getCompetitorName());
			pstate.setInt(2, pojo.getCompetitorClass());
			pstate.setBigDecimal(3,pojo.getCompetitorId());
			pstate.execute();
			this.conn.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally{
			try {
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return flag;
	}

	public List<CompetitorPOJO> findAllByNameClass(String name, int clas,
			int pageSize, int pageCurrent) {
		List<CompetitorPOJO> list = new ArrayList<CompetitorPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("select competitor_id ,competitor_name, competitor_level from (select competitor_id ,competitor_name, competitor_level,is_del ,rownum as rn from competitor where competitor_name like ? ");
			if(clas!=0){
				sql.append(" and competitor_level = "+clas);
			}
			sql.append(" ) where rn>? and rn<=? and is_del=1 order by competitor_id");
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%"+name+"%");
			pstate.setInt(2, (pageCurrent-1)*pageSize);
			pstate.setInt(3, pageCurrent*pageSize);
			res = pstate.executeQuery();
			while(res.next()){
				CompetitorPOJO pojo = new CompetitorPOJO(res.getBigDecimal(1),res.getString(2),res.getInt(3));
				list.add(pojo);
			}
		}catch(Exception e){
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


	public int findAllCount() {
		int count = 0;
		PreparedStatement pstate = null;
		ResultSet res = null;
		String sql = "select count(competitor_id) from competitor where is_del=1" ;
		try {
			pstate = this.conn.prepareStatement(sql);
			res = pstate.executeQuery();
			while(res.next()){
				count = res.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
	public CompetitorPOJO findById(BigDecimal cid) {
		CompetitorPOJO pojo = null;
		PreparedStatement pstate = null;
		ResultSet res = null;
		String sql = "select competitor_name, competitor_level ,is_del from competitor where competitor_id = ? and is_del = 1";
		try {
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1,cid);
			System.out.println(cid+"&&&&&&&&&&&&&");
			res = pstate.executeQuery();
			while(res.next()){
				pojo=new CompetitorPOJO(cid,res.getString(1),res.getInt(2),res.getInt(3));
				
			}
		} catch (SQLException e) {
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
}
