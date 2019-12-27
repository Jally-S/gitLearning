package org.jvsun.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.jvsun.dao.WorkerDAO;
import org.jvsun.pojo.WorkerPOJO;

public class WorkerDAOImpl implements WorkerDAO {
	Connection conn;

	public WorkerDAOImpl(Connection conn) {
		this.conn = conn;
	}

	public boolean doDel(BigDecimal workerId) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql = "update  worker set is_delete=0 where worker_id=? and is_delete=1";
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, workerId);
			pstate.execute();
			this.conn.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
			// TODO: handle exception
		} finally {
			try {
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
		}
		return flag;
		// TODO Auto-generated method stub

	}

	public boolean doIns(WorkerPOJO pojo) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			// private BigDecimal workerId;// ְ��ID
			// private String workerName;// ְ����
			// private int workerSex;// �Ա�
			// private String workerTel;// �绰
			// private String workerAdress;// ��ַ
			// private String wAccount;// �˺�
			// private int isDelete;
			String sql = "begin " +
					"insert into worker (worker_id, worker_name, worker_sex, "
					+ "worker_tel, worker_adress, w_acount, is_delete )"
					+ "values(CRM_1.nextval,?,?,?,?,?,1);"+
					
					"insert into login (login_id,acount, password)"
					
					+ "values(CRM_2.nextval,?,123);" +
							"end;";
			pstate = this.conn.prepareStatement(sql);
		
			pstate.setString(1, pojo.getWorkerName());
			pstate.setInt(2, pojo.getWorkerSex());
			pstate.setString(3, pojo.getWorkerTel());
			pstate.setString(4, pojo.getWorkerAdress());
			pstate.setString(5, pojo.getwAccount());
			pstate.setString (6, pojo.getwAccount());
		
			pstate.execute();// ִ��
			this.conn.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
			// TODO: handle exception
		} finally {
			try {
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
		}
		return flag;
	}

	public boolean doUpd(WorkerPOJO pojo) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql = "update  worker set worker_name=?, worker_sex=?, "
					+ "worker_tel=?, worker_adress=?, w_acount=?, is_delete=? where worker_id=?";

			pstate = this.conn.prepareStatement(sql);

			pstate.setString(1, pojo.getWorkerName());
			pstate.setInt(2, pojo.getWorkerSex());
			pstate.setString(3, pojo.getWorkerTel());
			pstate.setString(4, pojo.getWorkerAdress());
			pstate.setString(5, pojo.getwAccount());
			pstate.setInt(6, pojo.getIsDelete());
			pstate.setBigDecimal(7, pojo.getWorkerId());

			pstate.execute();// ִ��
			this.conn.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
			// TODO: handle exception
		} finally {
			try {
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
		}
		return flag;
	}

	public List<WorkerPOJO> findAll(String workerName, int isDelete,
			int pageSize, int pageCurrent) {
		List<WorkerPOJO> list = new ArrayList<WorkerPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {/*
			 * 
			 * private BigDecimal workerId;// ְ��ID private String workerName;//
			 * ְ���� private int workerSex;// �Ա� private String workerTel;// �绰
			 * private String workerAdress;// ��ַ private String wAccount;// �˺�
			 * private int isDelete;
			 */
			StringBuffer sql = new StringBuffer();
			sql.append("select worker_id,worker_name, worker_sex, worker_tel, "
					+ " worker_adress, w_acount,is_delete"
					+ " from (select worker_id,worker_name, worker_sex, worker_tel, "
					+ " worker_adress, w_acount,is_delete,rownum abc from worker where worker_name like ? ");

			if (isDelete != 2) {
				sql.append(" and is_delete = " + isDelete);// 1Ϊ��ְ0Ϊ��ְ2Ϊȫ��
			}
			sql.append(" ) where abc>? and abc<=? order by worker_id,is_delete desc");
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%" + workerName + "%");
			pstate.setInt(2, (pageCurrent - 1) * pageSize);
			pstate.setInt(3, pageCurrent * pageSize);
			res = pstate.executeQuery();
			while (res.next()) {
				WorkerPOJO pojo = new WorkerPOJO(res.getBigDecimal(1), res
						.getString(2), res.getInt(3), res.getString(4), res
						.getString(5), res.getString(6), res.getInt(7));
				list.add(pojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				res.close();
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
		// TODO Auto-generated method stub

	}

	public WorkerPOJO findByWorkerId(BigDecimal workerId) {
		// TODO Auto-generated method stub

		PreparedStatement pstate = null;
		ResultSet res = null;
		WorkerPOJO pojo  =null;
		try {/*
			 * 
			 * private BigDecimal workerId;// ְ��ID private String workerName;//
			 * ְ���� private int workerSex;// �Ա� private String workerTel;// �绰
			 * private String workerAdress;// ��ַ private String wAccount;// �˺�
			 * private int isDelete;
			 */
		
			String sql="select worker_name, worker_sex, worker_tel, "
					+ " worker_adress, w_acount,is_delete"
					+ " from  worker where worker_id = ? ";

		
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setBigDecimal(1,workerId);
		
			res = pstate.executeQuery();
			while (res.next()) {
				 pojo = new WorkerPOJO(workerId, res
						.getString(1), res.getInt(2), res.getString(3), res
						.getString(4), res.getString(5), res.getInt(6));}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				res.close();
				pstate.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return pojo;
	}

	

	public int findCountByNameState(String workerName, int isDelete) {
		int count = 0;
		PreparedStatement pstate = null;
		ResultSet res = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select count(worker_name) from worker where worker_name like ? ");
			
			if(isDelete != 2){
				sql.append(" and is_delete = "+isDelete);
			}
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%"+workerName+"%");
			res = pstate.executeQuery();
			while(res.next()){
				count = res.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
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

}
