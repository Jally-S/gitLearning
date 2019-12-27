package org.jvsun.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jvsun.dao.CustomerInfoDAO;
import org.jvsun.pojo.CustomerPOJO;
/**
 * �ͻ�������Ϣʵ����
 * @author Administrator
 *
 */
public class CustomerInfoDAOImpl implements CustomerInfoDAO {
	public Connection conn;//���ݿ�����
	/*
	 * �ͻ���Ϣʵ���๹�췽��
	 */
	public CustomerInfoDAOImpl(Connection conn){
		this.conn=conn;
	}
	/*
	 * �ͻ���Ϣ�޸�
	 * @see org.jvsun.dao.CustomerInfoDAO#doUpd(org.jvsun.pojo.CustomerPOJO)
	 */
	public boolean doUpd(CustomerPOJO pojo) {
		boolean flag = false;//���巵��ֵ
		PreparedStatement pstate = null;
		String sql = "update customer set customer_name = ?,customer_sex  =? , customer_tel  = ? ,customer_adress = ? ," +
				"customer_age=?,company =?  where customer_id = ? and is_delete = 1";
		try {
			this.conn.setAutoCommit(false);//ȡ���Զ��ύ
			pstate= this.conn.prepareStatement(sql);
			pstate.setString(1,pojo.getCustomerName());//�ͻ�����
			pstate.setInt(2,pojo.getCustomerSex());//�ͻ��Ա�
			pstate.setString(3,pojo.getCustomerTel());//�ͻ��绰
			pstate.setString(4,pojo.getCustomerAdress());//�ͻ���ַ
			pstate.setInt(5, pojo.getCustomerAge());//�ͻ�����
			pstate.setString(6, pojo.getCompany());//�ͻ���˾
			pstate.setBigDecimal(7, pojo.getCustomerId());//�ͻ�id
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
	 * �ͻ�������Ϣ��ѯ
	 * @see org.jvsun.dao.CustomerInfoDAO#findByWorkerId(java.math.BigDecimal)
	 */
	public CustomerPOJO findByWorkerId(BigDecimal customerId) {
		CustomerPOJO pojo =null;//�ͻ�����
		PreparedStatement pstate = null;//Ԥ�������
		ResultSet res = null;//���������
		String sql = "select customer_name ,customer_sex , customer_tel  ,customer_adress ,customer_age,company from" +
				" Customer where customer_id = ? and is_delete = 1";
		try {
			this.conn.setAutoCommit(false);//ȡ���Զ��ύ
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
