package org.jvsun.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jvsun.dao.FeedBackDAO;
import org.jvsun.pojo.FeedBackPOJO;
import org.jvsun.pojo.ProductPOJO;


public class FeedBackDAOImpl implements FeedBackDAO {
	public Connection conn;
	public FeedBackDAOImpl(Connection conn){
		this.conn=conn;
	}

	public boolean doDel(BigDecimal feedBackId) {
		boolean flag= false;
		PreparedStatement pstate=null;
		String sql="update feedback  set is_delete=? where feedback_id=? ";
		try {
			this.conn.setAutoCommit(false);
			pstate=this.conn.prepareStatement(sql);
			pstate.setInt(1, 0);//ɾ��Ϊ0
			pstate.setBigDecimal(2, feedBackId);//����id
			pstate.execute();
			this.conn.commit();
			flag=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pstate.close();
				this.conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean doIns(FeedBackPOJO pojo) {
		boolean flag = false;
		PreparedStatement pstate=null;
		
		String sql="insert into feedback(feedback_id,customer_id,feedback_date,product_name,feedback_content,is_Over,is_Delete)" +
				" values(DH2.nextval,?,sysdate,?,?,0,1)";
		try {
			this.conn.setAutoCommit(false);
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, pojo.getCustomerId());//�ͻ�id
			pstate.setString(2, pojo.getProductName());//��Ʒ��
			pstate.setString(3, pojo.getFeedbackContent());//������Ϣ
			pstate.execute();
			conn.commit();
			flag=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pstate.close();
				this.conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean update(BigDecimal feedBackId,String FeedbackContent) {
		boolean flag=false;
		PreparedStatement pstate=null;
		try {
			this.conn.setAutoCommit(false);
			String sql="update feedback  set Feedback_Content=?,feedback_date= sysdate ,is_over = 0 where feedback_Id = ? ";
			pstate=this.conn.prepareStatement(sql);
			pstate.setString(1, FeedbackContent);
			pstate.setBigDecimal(2, feedBackId);
			pstate.execute();
			this.conn.commit();
			flag=true;
		} catch (SQLException e) {
			try {
				this.conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				pstate.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public int findById(BigDecimal customerId) {
		int count = 0;
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select count(feedback_id) from feedback where customer_id = ? and is_delete= 1");
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setBigDecimal(1, customerId);//�ͻ�id
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

	public List<FeedBackPOJO> findById(String productName,BigDecimal customerId,int pageSize, int pageCurrent) {
		List<FeedBackPOJO> list = new ArrayList<FeedBackPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select b.feedback_id,b.customer_id, b.feedback_date, b.product_name, b.feedback_content," +
					" b.is_over ,b.is_delete ,b.feed_content from (select f.feedback_id, f.customer_id, f.feedback_date," +
					" f.product_name, f.feedback_content, f.is_over ,f.is_delete ,rownum abc,fr.feed_content " +
					" from feedback f ,feedback_return fr where f.feedback_id=fr.feedback_id(+) and " +
					" f.product_name like ?  and f.is_delete= 1 and f.customer_id=? ) b " +
					" where abc>? and abc<=? order by b.is_over desc");
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1,"%"+productName+"%");//��Ʒ��
			pstate.setBigDecimal(2, customerId);//�ͻ�id
			pstate.setInt(3, (pageCurrent-1)*pageSize);
			pstate.setInt(4, pageCurrent*pageSize);
			res = pstate.executeQuery();
			while(res.next()){
				FeedBackPOJO pojo = new FeedBackPOJO(res.getBigDecimal(1), res.getBigDecimal(2),
						res.getDate(3), res.getString(4),res.getString(5),res.getString(8),res.getInt(6),res.getInt(7));
				list.add(pojo);
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
		return list;
		
	}

	public List<ProductPOJO> findByCustomerId(BigDecimal customerId) {
		List<ProductPOJO> list=new ArrayList<ProductPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		String sql="select p.product_name from  customer_product cp,product p " +
				"where cp.customer_id=? and cp.product_id=p.product_id";
		try {
			pstate=this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, customerId);//�ͻ�id
			res=pstate.executeQuery();
			while(res.next()){
				ProductPOJO pojo= new ProductPOJO(res.getString(1));
				list.add(pojo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				res.close();
				pstate.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public FeedBackPOJO findOneByCustomerId(BigDecimal feedBackId) {
		FeedBackPOJO pojo =null;
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select a.customer_Id,feedback_date,a.product_Name, a.feedback_content,b.feed_content,a.is_over" +
					" from feedback a,feedback_return b" +
					" where a.feedback_id=b.feedback_id and a.feedBack_Id=? and a.is_delete = 1");
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setBigDecimal(1, feedBackId);
			res = pstate.executeQuery();
			while(res.next()){

				pojo = new 	FeedBackPOJO(feedBackId,res.getBigDecimal(1),res.getDate(2),res.getString(3),
						res.getString(4),res.getString(5),res.getInt(6),1);
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
		return pojo;
	}
	
	
	
	
	//根据id查询反馈单
	public FeedBackPOJO findByFeedBackId(BigDecimal feedBackId) {
		FeedBackPOJO pojo = null;
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			String sql = "select customer_id, feedback_date, product_name, feedback_content, is_over from feedback where feedback_id = ? and is_delete=1 ";
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, feedBackId);
			res = pstate.executeQuery();
			while(res.next()){
				pojo = new FeedBackPOJO(feedBackId,res.getBigDecimal(1),res.getDate(2),res.getString(3),res.getString(4),res.getInt(5),1);
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



	//查询反馈单的数据笔数
	public List<FeedBackPOJO> findByProductName(String productName,int isOver,
			int pageSize, int pageCurrent) {
		List<FeedBackPOJO> list = new ArrayList<FeedBackPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select feedback_id, customer_id, feedback_date, product_name, feedback_content ,is_over " +
					" from (select feedback_id, customer_id, feedback_date, product_name, feedback_content ,is_over,rownum abc from feedback" +
					" where  product_name like ?");
					if(isOver !=2){  //0为未解决1为解决2为全部
						sql.append("and is_over = " +isOver);
					}
					sql.append(") where abc>? and abc<=? order by feedback_date desc");
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%"+productName+"%");
			pstate.setInt(2, (pageCurrent-1)*pageSize);
			pstate.setInt(3, pageCurrent*pageSize);
			res = pstate.executeQuery();
			while(res.next()){
				FeedBackPOJO pojo = new FeedBackPOJO(res.getBigDecimal(1),res.getBigDecimal(2),res.getDate(3),res.getString(4),
						res.getString(5),res.getInt(6),1);
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

	
	

	//根据名称查询反馈表的所有数据
	public int findCountByProductName(String productName,int isOver) {
		int count = 0;
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select count(feedback_id) from feedback where product_name like ?");
			if(isOver !=2){  //0为未解决1为解决2为全部
				sql.append("and is_over = " +isOver);
			}
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%"+productName+"%");
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

}


