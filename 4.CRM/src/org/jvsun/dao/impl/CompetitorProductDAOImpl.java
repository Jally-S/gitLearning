package org.jvsun.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jvsun.dao.CompetitorProductDAO;
import org.jvsun.pojo.CompetitorProductPOJO;

public class CompetitorProductDAOImpl implements CompetitorProductDAO {
	Connection conn;
	public CompetitorProductDAOImpl(Connection conn){
		this.conn = conn;
	}
	public boolean doIns(CompetitorProductPOJO pojo) {
		boolean flag=false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql="insert into com_product(com_product_id ,competitor_id ,com_product_name ,com_product_level )values(DH4.nextval,?,?,?)";
			pstate = this.conn.prepareStatement(sql);
			System.out.println("竞争对手id:"+pojo.getCompetitorId());
			pstate.setBigDecimal(1, pojo.getCompetitorId());
			pstate.setString(2, pojo.getComProductName());
			pstate.setInt(3, pojo.getComProductClass());
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

	public boolean doDel(BigDecimal cpid) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql = "update com_product set is_del = 0 where com_product_id = ? ";
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, cpid);
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

	public boolean doUpd(CompetitorProductPOJO pojo) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			/*from com_product,competitor where com_product.competitor_id = competitor.competitor_id*/
			String sql = "update com_product set com_product_name = ? ,com_product_level = ? where com_product_id = ?";
			pstate = this.conn.prepareStatement(sql);
			pstate.setString(1, pojo.getComProductName());
			pstate.setInt(2, pojo.getComProductClass());
			pstate.setBigDecimal(3, pojo.getComProductId());
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

	public List<CompetitorProductPOJO> findAllByPnameCnameClass(String pname,
			String cname, int clas, int pageSize, int pageCurrent) {
		List<CompetitorProductPOJO> list = new ArrayList<CompetitorProductPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("select com_product_id ,competitor_name ,com_product_name ,com_product_level from (select  com_product.com_product_id ,competitor.competitor_name ,com_product_name ,com_product_level ,competitor.is_del ,rownum as rn from com_product,competitor where com_product.competitor_id = competitor.competitor_id and com_product_name like ? and com_product.is_del = 1 ");
			System.out.println("cname的值："+cname);
			if(/*cname==null||cname.equals("")*/cname!=null&&!cname.isEmpty())
			{
				sql.append(" and competitor_name = '"+cname+"'");
			}
			if(clas!=0){
				sql.append(" and com_product_level = '"+clas+"'");
			}
			sql.append( ") where rn > ? and rn<= ? and is_del=1 order by com_product_id");
			System.out.println("查询语句："+sql.toString());
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%"+pname+"%");
			pstate.setInt(2, (pageCurrent-1)*pageSize);
			pstate.setInt(3, pageCurrent*pageSize);
			res = pstate.executeQuery();
			while(res.next()){
				CompetitorProductPOJO pojo = new CompetitorProductPOJO(res.getBigDecimal(1),res.getString(2),res.getString(3),res.getInt(4));
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
		String sql = "select count(com_product_id) from com_product where is_del=1" ;
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

	public CompetitorProductPOJO findById(BigDecimal pid) {
		CompetitorProductPOJO pojo = null;
		PreparedStatement pstate = null;
		ResultSet res = null;
		String sql = "select competitor_name, com_product_name ,com_product_level,com_product.is_del from com_product,competitor where com_product.competitor_id = competitor.competitor_id and com_product_id = ? ";
		try {
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1,pid);
			res = pstate.executeQuery();
			while(res.next()){
				pojo=new CompetitorProductPOJO(pid,res.getString(1),res.getString(2),res.getInt(3),res.getInt(4));
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
