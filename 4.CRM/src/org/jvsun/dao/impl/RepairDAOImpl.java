package org.jvsun.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jvsun.dao.RepairDAO;
import org.jvsun.pojo.ProductPOJO;
import org.jvsun.pojo.RepairPOJO;

public class RepairDAOImpl implements RepairDAO {
	public Connection conn;
	public RepairDAOImpl(Connection conn){
		this.conn=conn;
	}
	
	/* 
	 * 报修单的删除
	 * @see org.jvsun.dao.RepairDAO#doDel(java.math.BigDecimal)
	 */
	public boolean doDel(BigDecimal repairId) {
		boolean flag= false;
		PreparedStatement pstate= null;
		String sql = "update repair set is_delete = 0 where repair_id = ?";
		try {
			this.conn.setAutoCommit(false);
			pstate= this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, repairId);//���޵�id
			pstate.execute();
			this.conn.commit();
			flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				pstate.close();
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	/*
	 * 保修单的新增
	 * @see org.jvsun.dao.RepairDAO#doIns(org.jvsun.pojo.RepairPOJO)
	 */
	public boolean doIns(RepairPOJO pojo) {
		boolean flag= false;
		PreparedStatement pstate= null;
		String sql = "insert into repair (repair_id ,product_name, repair_content ,up_date,is_over, customer_id ,is_delete )" +
				" values(dh3.nextval,?,?,sysdate,0,?,1)";
		try {
			this.conn.setAutoCommit(false);
			pstate= this.conn.prepareStatement(sql);
			pstate.setString(1, pojo.getProductName());
			pstate.setString(2, pojo.getRepairContent());
			pstate.setBigDecimal(3, pojo.getCustomerId());
			pstate.execute();
			this.conn.commit();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	/*
	 * 报修单的修改
	 * @see org.jvsun.dao.RepairDAO#doUpd(java.math.BigDecimal, java.lang.String)
	 */
	public boolean doUpd(BigDecimal repairId, String repairContent) {
		boolean flag = false;
		PreparedStatement pstate = null;
		String sql = "update repair  set repair_Content=? , up_date=sysdate,is_over = 0  where repair_Id = ? ";
		try {
			this.conn.setAutoCommit(false);
			pstate= this.conn.prepareStatement(sql);
			pstate.setString(1, repairContent);//������Ϣ
			pstate.setBigDecimal(2, repairId);//����ID
			pstate.execute();
			this.conn.commit();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				pstate.close();
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	/*
	 * 取得 报修单的数据笔数
	 * @see org.jvsun.dao.RepairDAO#findByCustomerId(java.math.BigDecimal)
	 */
	public List<ProductPOJO> findByCustomerId(BigDecimal customerId) {
		List<ProductPOJO> list = new ArrayList<ProductPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			String sql="select p.product_name from  customer_product cp,product p " +
				" where cp.customer_id=? and cp.product_id=p.product_id";
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, customerId);//�ͻ�ID
			res = pstate.executeQuery();
			while(res.next()){
				ProductPOJO pojo= new ProductPOJO(res.getString(1));
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
	/*
	 * 取得报修单的数据笔数
	 * @see org.jvsun.dao.RepairDAO#findByProduct(java.lang.String, java.math.BigDecimal, int, int)
	 */
	public List<RepairPOJO> findByProduct(String productName,
			BigDecimal customerId, int pageSize, int pageCurrent) {
		List<RepairPOJO> list = new ArrayList<RepairPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			String sql="select f.repair_id , f.product_name , f.repair_content , f.up_date , f.is_over , f.customer_id " +
					" ,f.rept_content,f.is_delete from (select a.repair_id , a.product_name , a.repair_content ," +
					" a.up_date , a.is_over , a.customer_id ,a.is_delete ,b.rept_content,rownum abc " +
					" from repair a,repair_return b where a.product_name like ? and b.repair_id(+)=a.repair_id " +
					" and a.is_delete= 1 and a.customer_id=?  order by a.is_over desc) f where  abc>? and abc<=? ";
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1,"%"+productName+"%");//��Ʒ��
			pstate.setBigDecimal(2, customerId);//�ͻ�id
			pstate.setInt(3, (pageCurrent-1)*pageSize);
			pstate.setInt(4, pageCurrent*pageSize);
			res = pstate.executeQuery();
			while(res.next()){
				RepairPOJO pojo = new RepairPOJO(res.getBigDecimal(1),res.getString(2),res.getString(3),res.getDate(4),
						res.getInt(5),res.getBigDecimal(6),res.getString(7),res.getInt(8));
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
	/*
	 * ��ѯ���ʱ��޼�¼
	 * @see org.jvsun.dao.RepairDAO#findByRepairId(java.math.BigDecimal)
	 */
	public RepairPOJO findByRepairId(BigDecimal repairId) {
		RepairPOJO pojo= null;
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("  select a.repair_id , a.product_name ,a.repair_content ,a.up_date,a.is_over,a.customer_id , b.rept_content from repair a,repair_return b where" +
					" a.repair_id=b.repair_id and a.repair_id=? and a.is_delete = 1");
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setBigDecimal(1, repairId);//����id
			res = pstate.executeQuery();
			while(res.next()){//
				 pojo = new RepairPOJO(res.getBigDecimal(1),res.getString(2),res.getString(3),res.getDate(4),
							res.getInt(5),res.getBigDecimal(6),res.getString(7),1);
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
	/*
	 * ��ѯ���޼�¼����
	 * @see org.jvsun.dao.RepairDAO#findCountById(java.math.BigDecimal)
	 */
	public int findCountById(BigDecimal customerId) {
		int count = 0;
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select count(repair_id) from repair where customer_id = ? and is_delete= 1");
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
	
	
	
	
	//根据id查询报修单实现方法
	public RepairPOJO findByRepairIdJi(BigDecimal repairId) {
		RepairPOJO pojo = null;
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			String sql = "select repair_id, product_name, repair_content, up_date, is_over, customer_id,is_delete from repair where repair_id = ? ";
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, repairId);
			res = pstate.executeQuery();
			while(res.next()){
				pojo = new RepairPOJO(res.getBigDecimal(1),res.getString(2),res.getString(3),res.getDate(4),
						res.getInt(5),res.getBigDecimal(6),res.getInt(7));
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
	
	
	
	
	
	

	//查询维修单的数据笔数实现方法
	public List<RepairPOJO> findByProductName(String productName,int isOver, int pageSize,
			int pageCurrent) {
		List<RepairPOJO> list = new ArrayList<RepairPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select repair_id,  product_name,repair_content, up_date, is_over, customer_id,is_delete " +
					" from (select repair_id,product_name, repair_content, up_date, is_over, customer_id,is_delete,rownum abc" +
					" from repair where product_name like ? and is_delete = 1");
					if(isOver !=2){  //0为未解决1为解决2为全部
						sql.append("and is_over = " +isOver);
					}
					sql.append(")where abc>? and abc<=? order by  up_date desc");
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%"+productName+"%");
			pstate.setInt(2, (pageCurrent-1)*pageSize);
			pstate.setInt(3, pageCurrent*pageSize);
			res = pstate.executeQuery();
			while(res.next()){
				RepairPOJO pojo = new RepairPOJO(res.getBigDecimal(1),res.getString(2),res.getString(3),res.getDate(4),
						res.getInt(5),res.getBigDecimal(6),res.getInt(7));
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
	
	
	
	

	//根据名称查询报修单的实现方法
	public int findCountByProductName(String productName,int isOver) {
		int count = 0;
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select count(repair_id) from repair where product_name like ?");
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
