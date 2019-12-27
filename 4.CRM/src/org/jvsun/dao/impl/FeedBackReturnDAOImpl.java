package org.jvsun.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jvsun.dao.FeedBackReturnDAO;
import org.jvsun.pojo.FeedBackReturnPOJO;

public class FeedBackReturnDAOImpl implements FeedBackReturnDAO {
	Connection conn = null;
	public FeedBackReturnDAOImpl(Connection conn){
		this.conn = conn;
	}
	
	
	//删除反馈回执单的实现方法
	public boolean doDel(BigDecimal feedId) {
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql = "update feedback_return set is_delete = 0 where feed_id = ?";
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, feedId);
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

	
	
	//新增反馈回执单的实现方法
	public boolean doIns(FeedBackReturnPOJO pojo) {
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql = "begin " +
					" insert into feedback_return(feed_id, feed_name, feed_content, feed_time, feedback_id, worker_id, is_delete )" +
					" values(CRM_SEQUENCES.nextval,?,?,?,?,?,1);" +
					" update feedback set is_over = 1 where feedback_id = ?;" +
					 " end;";
			pstate = this.conn.prepareStatement(sql);
			pstate.setString(1, pojo.getFeedName());
			pstate.setString(2, pojo.getFeedContent());
			pstate.setDate(3, new java.sql.Date(pojo.getFeedTime().getTime()));
			pstate.setBigDecimal(4, pojo.getFeedBackId());
			pstate.setBigDecimal(5, pojo.getWorkerId());
			pstate.setBigDecimal(6, pojo.getFeedBackId());
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

	
	
	//修改反馈回执单的实现方法
	public boolean doUpd(FeedBackReturnPOJO pojo) {
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql = "update feedback_return set feed_name=? ,  feed_content = ? where feed_id = ?";
			pstate = this.conn.prepareStatement(sql);
			pstate.setString(1, pojo.getFeedName());
			pstate.setString(2, pojo.getFeedContent());
			pstate.setBigDecimal(3, pojo.getFeedId());
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

	
	
	
	
	
	//根据id查询反馈回执单
	public FeedBackReturnPOJO findByFeedId(BigDecimal feedId) {
		FeedBackReturnPOJO pojo = null;
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			String sql = "select feed_id, feed_name, feed_content, feed_time ,feedback_id, worker_id,is_delete  from feedback_return where feed_id = ? and is_delete = 1";
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, feedId);
			res = pstate.executeQuery();
			while(res.next()){
				pojo = new FeedBackReturnPOJO(feedId,res.getString(2),res.getString(3),res.getDate(4),res.getBigDecimal(5),res.getBigDecimal(6),res.getInt(7));
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

	
	
	//查询反馈回执单数据笔数
	public List<FeedBackReturnPOJO> findByFeedName(String feedName,
			int pageSize, int pageCurrent) {
		List<FeedBackReturnPOJO> list = new ArrayList<FeedBackReturnPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select feed_id, feed_name, feed_content, feed_time ,feedback_id, worker_id ,is_delete" +
					" from (select feed_id, feed_name, feed_content, feed_time ,feedback_id, worker_id,is_delete,rownum abc from feedback_return" +
					" where  feed_name like ? and is_delete = 1) where abc>? and abc<=? order by feed_time desc ");
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%"+feedName+"%");
			pstate.setInt(2, (pageCurrent-1)*pageSize);
			pstate.setInt(3, pageCurrent*pageSize);
			res = pstate.executeQuery();
			while(res.next()){
				FeedBackReturnPOJO pojo = new FeedBackReturnPOJO(res.getBigDecimal(1),res.getString(2),res.getString(3),res.getDate(4),
						res.getBigDecimal(5),res.getBigDecimal(6),res.getInt(7));
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

	
	
	
	
	
	
	
	//根据名称查询反馈回执表的所有数据
	public int findCountByFeedName(String feedName) {
		int count = 0;
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select count(feed_id) from feedback_return where feed_name like ? and is_delete = 1");
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%"+feedName+"%");
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



/*
	public int countRepairReturn(String feedName, String productName) {
		int count = 0;
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select count(*) from feedback_return_vw  where  feed_name like ? and product_name like ? and is_delete = 1");
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%"+feedName+"%");
			pstate.setString(2, "%"+productName+"%");
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


	public List<FeedbackReturnVwPOJO> findFeedBackReturn(int pageSize,
			int pageCurrent, String feedName, String productName) {
		List<FeedbackReturnVwPOJO> list = new ArrayList<FeedbackReturnVwPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {            
			StringBuffer sql = new StringBuffer();
			sql.append("select feed_id, feed_name, feed_content, feed_time, worker_id, is_delete, feedback_content,feedback_date, f_is_over,customer_id, customer_name, product_id, product_name,feedback_id " +
					" from (select feed_id, feed_name, feed_content, feed_time, worker_id, is_delete, feedback_content,feedback_date, f_is_over,customer_id, customer_name, product_id, product_name ,feedback_id,rownum ind " +
					" from feedback_return_vw where  feed_name like ? and product_name like ?  and is_delete = 1 order by feed_time desc ) where  ind > ? and ind <= ? ");
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%"+feedName+"%");
			pstate.setString(2, "%"+productName+"%");
			pstate.setInt(3, (pageCurrent-1)*pageSize);
			pstate.setInt(4, pageCurrent*pageSize);
			res = pstate.executeQuery();
			while(res.next()){
				FeedbackReturnVwPOJO pojo = new FeedbackReturnVwPOJO(res.getInt(1),res.getString(2),res.getString(3),res.getDate(4),res.getInt(5),res.getInt(6),res.getString(7),new java.util.Date(res.getDate(8).getTime()),res.getInt(9),res.getInt(10),res.getString(11),res.getInt(12),res.getString(13),res.getInt(14));
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

	
	
*/	


}
