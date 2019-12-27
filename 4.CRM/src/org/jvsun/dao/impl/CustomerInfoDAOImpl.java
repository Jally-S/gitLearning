package org.jvsun.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jvsun.dao.CustomerInfoDAO;
import org.jvsun.pojo.CustomerPOJO;
/**
 * 客户个人信息实现类
 * @author Administrator
 *
 */
public class CustomerInfoDAOImpl implements CustomerInfoDAO {
	public Connection conn;//数据库连接
	/*
	 * 客户信息实现类构造方法
	 */
	public CustomerInfoDAOImpl(Connection conn){
		this.conn=conn;
	}
	/*
	 * 客户信息修改
	 * @see org.jvsun.dao.CustomerInfoDAO#doUpd(org.jvsun.pojo.CustomerPOJO)
	 */
	public boolean doUpd(CustomerPOJO pojo) {
		boolean flag = false;//定义返回值
		PreparedStatement pstate = null;
		String sql = "update customer set customer_name = ?,customer_sex  =? , customer_tel  = ? ,customer_adress = ? ," +
				"customer_age=?,company =?  where customer_id = ? and is_delete = 1";
		try {
			this.conn.setAutoCommit(false);//取消自动提交
			pstate= this.conn.prepareStatement(sql);
			pstate.setString(1,pojo.getCustomerName());//客户姓名
			pstate.setInt(2,pojo.getCustomerSex());//客户性别
			pstate.setString(3,pojo.getCustomerTel());//客户电话
			pstate.setString(4,pojo.getCustomerAdress());//客户地址
			pstate.setInt(5, pojo.getCustomerAge());//客户年龄
			pstate.setString(6, pojo.getCompany());//客户公司
			pstate.setBigDecimal(7, pojo.getCustomerId());//客户id
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
	 * 客户个人信息查询
	 * @see org.jvsun.dao.CustomerInfoDAO#findByWorkerId(java.math.BigDecimal)
	 */
	public CustomerPOJO findByWorkerId(BigDecimal customerId) {
		CustomerPOJO pojo =null;//客户对象
		PreparedStatement pstate = null;//预处理对象
		ResultSet res = null;//结果集对象
		String sql = "select customer_name ,customer_sex , customer_tel  ,customer_adress ,customer_age,company from" +
				" Customer where customer_id = ? and is_delete = 1";
		try {
			this.conn.setAutoCommit(false);//取消自动提交
			pstate=this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, customerId);
			res= pstate.executeQuery();
			while(res.next()){
				pojo = new CustomerPOJO(customerId,res.getString(1),res.getInt(2),res.getString(3),res.getString(4)
						,res.getInt(5),res.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				res.close();
				pstate.close();
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pojo;
	}

}
