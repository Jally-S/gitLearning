package org.jvsun.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jvsun.dao.WorkerInfoDAO;
import org.jvsun.pojo.WorkerPOJO;
/**
 * 职工信息实现类
 * @author Administrator
 *
 */
public class WorkerInfoDAOImpl implements WorkerInfoDAO {
	public Connection conn;
	public WorkerInfoDAOImpl(Connection conn){
		this.conn=conn;
	}
	/*
	 * 职工信息修改
	 * @see org.jvsun.dao.WorkerInfoDAO#doUpd(org.jvsun.pojo.WorkerPOJO)
	 */
	public boolean doUpd(WorkerPOJO pojo) {
		boolean flag = false;
		PreparedStatement pstate = null;
		String sql = "update worker set worker_name= ?,worker_sex =? , worker_tel = ? ,worker_adress= ?  where worker_id= ?";
		try {
			this.conn.setAutoCommit(false);
			pstate= this.conn.prepareStatement(sql);
			pstate.setString(1, pojo.getWorkerName());//职工名
			pstate.setInt(2, pojo.getWorkerSex());//职工性别
			pstate.setString(3, pojo.getWorkerTel());//职工电话
			pstate.setString(4, pojo.getWorkerAdress());//职工住址
			pstate.setBigDecimal(5, pojo.getWorkerId());//职工ID
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
	 * 职工个人信息查询
	 * @see org.jvsun.dao.WorkerInfoDAO#findByWorkerId(java.math.BigDecimal)
	 */
	public WorkerPOJO findByWorkerId(BigDecimal workerId) {
		WorkerPOJO pojo = null;
		PreparedStatement pstate = null;
		ResultSet res = null;
		String sql = "select worker_name,worker_sex, worker_tel ,worker_adress  from WORKER where worker_id= ?";
		try {
			this.conn.setAutoCommit(false);
			pstate=this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, workerId);//职工ID
			res= pstate.executeQuery();
			while(res.next()){
				pojo = new WorkerPOJO(workerId,res.getString(1),res.getInt(2),res.getString(3),res.getString(4));
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
