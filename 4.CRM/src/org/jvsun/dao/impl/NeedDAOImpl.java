package org.jvsun.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jvsun.dao.NeedDAO;
import org.jvsun.pojo.NeedPOJO;

public class NeedDAOImpl implements NeedDAO {
	Connection conn;
	public NeedDAOImpl(Connection conn){
		this.conn = conn;
	}
	public boolean doIns(NeedPOJO pojo) {
		boolean flag=false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql="insert into need(need_id, customer_id, n_hobby ,product_name)values(DH5.nextval,?,?,?)";
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, pojo.getCustomerId());
			pstate.setString(2, pojo.getNhobby());
			pstate.setString(3, pojo.getProductName());
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
			String sql = "update need set is_del = 0 where need_id = ? ";
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

	public boolean doUpd(NeedPOJO pojo) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			/*from com_product,competitor where com_product.competitor_id = competitor.competitor_id*/
			String sql = "update need set  n_hobby = ? ,product_name = ? where need_id = ?";
			pstate = this.conn.prepareStatement(sql);
			pstate.setString(1, pojo.getNhobby());
			pstate.setString(2, pojo.getProductName());
			pstate.setBigDecimal(3, pojo.getNeedId());
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

	public List<NeedPOJO> findAllByCusHobbyProduct(String customerName,
			String nhobby, String productName, int pageSize, int pageCurrent) {
		List<NeedPOJO> list = new ArrayList<NeedPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		System.out.println("客户名"+customerName+"爱好"+nhobby+"产品名"+productName);
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("select need_id ,customer_name ,n_hobby ,product_name from (select  need_id, customer_name ,n_hobby ,product_name, need.is_del ,rownum as rn from need,customer where need.customer_id = customer.customer_id and customer_name like ? and need.is_del = 1 ");
			if(null!=nhobby && !"".equals(nhobby)){
				sql.append(" and n_hobby like '%"+nhobby+"%'");
			}
			if(null!=productName && !"".equals(productName)){
				sql.append(" and product_name like '%"+productName+"%'");
			}
			sql.append( ") where rn > ? and rn<= ? and is_del=1");
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%"+customerName+"%");
			pstate.setInt(2, (pageCurrent-1)*pageSize);
			pstate.setInt(3, pageCurrent*pageSize);
			res = pstate.executeQuery();
			while(res.next()){
				NeedPOJO pojo = new NeedPOJO(res.getBigDecimal(1),res.getString(2),res.getString(3),res.getString(4));
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
		String sql = "select count(need_id) from need where is_del=1" ;
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

	public NeedPOJO findById(BigDecimal cid) {
		NeedPOJO pojo = null;
		PreparedStatement pstate = null;
		ResultSet res = null;
		String sql = "select customer_name ,n_hobby ,product_name,need.is_del from customer,need where customer.customer_id = need.customer_id and need_id = ? ";
		try {
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1,cid);
			res = pstate.executeQuery();
			while(res.next()){
				pojo=new NeedPOJO(cid,res.getString(1),res.getString(2),res.getString(3),res.getInt(4));
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
