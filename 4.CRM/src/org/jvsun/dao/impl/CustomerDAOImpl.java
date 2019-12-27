package org.jvsun.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jvsun.dao.CustomerDAO;
import org.jvsun.pojo.CustomerPOJO;
import org.jvsun.pojo.ProductPOJO;


public class CustomerDAOImpl implements CustomerDAO {
	Connection conn;
	public CustomerDAOImpl(Connection conn){
		this.conn = conn;
	}
	/* (non-Javadoc)
	 * @see org.jvsun.dao.CustomerDAO#doIns(org.jvsun.pojo.CustomerPOJO)
	 * 新增客户
	 */
	public boolean doIns(CustomerPOJO pojo) {
		boolean flag=false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql="insert into customer(customer_id,customer_name,customer_sex,customer_tel,customer_adress,customer_age,c_acount,company)values(DH1.nextval,?,?,?,?,?,?,?)";
			pstate = this.conn.prepareStatement(sql);
			pstate.setString(1, pojo.getCustomerName());
			pstate.setInt(2, pojo.getCustomerSex());
			pstate.setString(3, pojo.getCustomerTel());
			pstate.setString(4, pojo.getCustomerAdress());
			pstate.setInt(5, pojo.getCustomerAge());
			pstate.setString(6, pojo.getCacount());
			pstate.setString(7, pojo.getCompany());
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

	/* (non-Javadoc)
	 * @see org.jvsun.dao.CustomerDAO#doDel(java.math.BigDecimal)
	 * 伪删除，使信息不可见，方便误删找回
	 */
	public boolean doDel(BigDecimal customerId) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql = "update customer set is_delete=0 where customer_id = ?";
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, customerId);
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

	/* (non-Javadoc)
	 * @see org.jvsun.dao.CustomerDAO#doUpd(org.jvsun.pojo.CustomerPOJO)
	 * 更新客户信息
	 */
	public boolean doUpd(CustomerPOJO pojo) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql = "update customer set customer_name=?,customer_sex=?,customer_tel=?,customer_adress=?,customer_age=?,c_acount=?,company=?,is_delete=?,role_mark=? where customer_id=?";
			pstate = this.conn.prepareStatement(sql);
			pstate.setString(1, pojo.getCustomerName());
			pstate.setInt(2, pojo.getCustomerSex());
			pstate.setString(3,pojo.getCustomerTel());
			pstate.setString(4, pojo.getCustomerAdress());
			pstate.setInt(5,pojo.getCustomerAge());
			pstate.setString(6, pojo.getCacount());
			pstate.setString(7, pojo.getCompany());
			pstate.setInt(8, pojo.getIsDelete());
			pstate.setInt(9, pojo.getRoleMark());
			pstate.setBigDecimal(10, pojo.getCustomerId());
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

	/* (non-Javadoc)
	 * @see org.jvsun.dao.CustomerDAO#findById(java.math.BigDecimal)
	 * 根据id查询客户信息
	 */
	public CustomerPOJO findById(BigDecimal customerId) {
		CustomerPOJO pojo = null;
		PreparedStatement pstate = null;
		ResultSet res = null;
		String sql = "select customer_name, customer_sex, customer_tel, customer_adress, customer_age,c_acount,company, is_delete, role_mark from customer where customer_id = ? and is_delete=1";
		try {
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, customerId);
			res = pstate.executeQuery();
			while(res.next()){
				pojo=new CustomerPOJO(customerId,res.getString(1),res.getInt(2),res.getString(3),res.getString(4),res.getInt(5),res.getString(6),res.getString(7),res.getInt(8),res.getInt(9));
				
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

	/* (non-Javadoc)
	 * @see org.jvsun.dao.CustomerDAO#findByName(java.lang.String)
	 * 根据名字查询客户信息
	 */
	public CustomerPOJO findByName(String customerName) {
		CustomerPOJO pojo = null;
		PreparedStatement pstate = null;
		ResultSet res = null;
		String sql = "select customer_id, customer_sex, customer_tel, customer_adress, customer_age,c_acount,company, is_delete, role_mark from customer where customer_name = ? and is_delete=1";
		try {
			pstate = this.conn.prepareStatement(sql);
			pstate.setString(1, customerName);
			res = pstate.executeQuery();
			while(res.next()){
				pojo=new CustomerPOJO(res.getBigDecimal(1),customerName,res.getInt(2),res.getString(3),res.getString(4),res.getInt(5),res.getString(6),res.getString(7),res.getInt(8),res.getInt(9));
				
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
	/* (non-Javadoc)
	 * @see org.jvsun.dao.CustomerDAO#findAll(int, int)
	 * 列表列出所有客户信息
	 */
	public List<CustomerPOJO> findAll(int pageSize, int pageCurrent) {
		List<CustomerPOJO> list = new ArrayList<CustomerPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		String sql = "select customer_id,customer_name,customer_sex, customer_tel, customer_adress, customer_age,c_acount,company, is_delete, role_mark from " +
				"(select customer_id,customer_name, customer_sex, customer_tel, customer_adress, customer_age,c_acount,company, is_delete, role_mark, rownum as rn from  customer)  where rn > ? and rn<=? and is_delete = 1" ;
		try {
			pstate = this.conn.prepareStatement(sql);
			pstate.setInt(1, (pageCurrent-1)*pageSize);
			pstate.setInt(2, pageCurrent*pageSize);
			res = pstate.executeQuery();
			while(res.next()){
				CustomerPOJO pojo=new CustomerPOJO(res.getBigDecimal(1),res.getString(2),res.getInt(3),res.getString(4),res.getString(5),res.getInt(6),res.getString(7),res.getString(8),res.getInt(9),res.getInt(10));
				list.add(pojo);
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
		return list;
	}
	/* (non-Javadoc)
	 * @see org.jvsun.dao.CustomerDAO#findAllCount()
	 * 查询出客户的人数
	 */
	public int findAllCount() {
		int count = 0;
		PreparedStatement pstate = null;
		ResultSet res = null;
		String sql = "select count(customer_id) from customer where is_delete=1" ;
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
	public List<ProductPOJO> findProductByCid(BigDecimal cid) {
		List<ProductPOJO> list = new ArrayList<ProductPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			String sql = "select product_name,product_describe,product_class ,product_inventory ,product_price from customer_product cp,product p where cp.product_id = p.product_id and p.is_delete = 1 and cp.customer_id = ?";
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, cid);
			res = pstate.executeQuery();
			while(res.next()){
				ProductPOJO pojo=new ProductPOJO(res.getString(1),res.getString(2),res.getInt(3),res.getInt(4),res.getInt(5));
				list.add(pojo);
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
		return list;
	}
	public boolean doInsCP(BigDecimal pid, BigDecimal cid) {
		boolean flag=false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql="insert into customer_product(customer_id,product_id)values(?,?)";
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, cid);
			pstate.setBigDecimal(2, pid);
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
	public boolean doDelCP(BigDecimal pid, BigDecimal cid) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql = "delete from customer_product where product_id = ? and customer_id = ?";
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, pid);
			pstate.setBigDecimal(2, cid);
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

	
}
