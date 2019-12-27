package org.jvsun.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jvsun.dao.ContractDAO;
import org.jvsun.pojo.ContractPOJO;

public class ContractDAOImpl implements ContractDAO {
	Connection conn;
	public ContractDAOImpl(Connection conn){
		this.conn = conn;
	}
	public boolean doIns(ContractPOJO pojo) {
		boolean flag=false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql="insert into contract(contract_id ,cus_customer_id, worker_id, contract_name ,contract_content,is_photo )values(DH5.nextval,?,?,?,?,?)";
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, pojo.getCustomerId());
			pstate.setBigDecimal(2, pojo.getWorkerId());
			pstate.setString(3, pojo.getContractName());
			pstate.setString(4, pojo.getContractContent());
			pstate.setInt(5, pojo.getIsPhoto());
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
	public boolean doDel(int ContractId) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql = "update contract set is_del = 0 where contract_id = ? ";
			pstate = this.conn.prepareStatement(sql);
			pstate.setInt(1, ContractId);
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

	public boolean doUpd(ContractPOJO pojo) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql = "update contract set cus_customer_id = ?, worker_id = ?, contract_name = ?,contract_content = ?,is_photo = ? where contract_id = ?";
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1, pojo.getCustomerId());
			pstate.setBigDecimal(2, pojo.getWorkerId());
			pstate.setString(3, pojo.getContractName());
			pstate.setString(4, pojo.getContractContent());
			pstate.setInt(5, pojo.getIsPhoto());
			pstate.setBigDecimal(6, pojo.getContractId());
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
	public List<ContractPOJO> findAllByNameWnameCname(String name,
			String Wname, String Cname, int pageSize, int pageCurrent) {
		List<ContractPOJO> list = new ArrayList<ContractPOJO>();
		PreparedStatement pstate = null;
		ResultSet res = null;
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("select contract_id ,customer_name ,worker_name , contract_name ,contract_content , is_photo from( select contract_id ,customer_name ,worker_name, contract_name ,contract_content , is_photo ,contract.is_del ,rownum as rn from contract,customer,worker where cus_customer_id = customer_id and contract.worker_id = worker.worker_id and contract_name like ? and contract.is_del = 1 ");
			if(null!=Wname && !"".equals(Wname)){
				sql.append(" and worker_name like '%"+Wname+"%'");
			}
			if(null!=Cname && !"".equals(Cname)){
				sql.append(" and customer_name like '%"+Cname+"%'");
			}
			sql.append( ") where rn > ? and rn<= ? and is_del=1");
			System.out.println("查询语句："+sql.toString());
			pstate = this.conn.prepareStatement(sql.toString());
			pstate.setString(1, "%"+name+"%");
			pstate.setInt(2, (pageCurrent-1)*pageSize);
			pstate.setInt(3, pageCurrent*pageSize);
			res = pstate.executeQuery();
			while(res.next()){
				ContractPOJO pojo = new ContractPOJO(res.getBigDecimal(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getInt(6));
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
		String sql = "select count(contract_id) from contract where is_del=1" ;
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
	public ContractPOJO findById(BigDecimal cid) {
		ContractPOJO pojo = null;
		PreparedStatement pstate = null;
		ResultSet res = null;
		String sql = "select customer_name ,worker_name ,contract_name ,contract_content, is_photo from customer,worker,contract where cus_customer_id = customer_id and contract.worker_id  = worker.worker_id  and contract_id = ? ";
		try {
			pstate = this.conn.prepareStatement(sql);
			pstate.setBigDecimal(1,cid);
			res = pstate.executeQuery();
			while(res.next()){
				pojo=new ContractPOJO(cid,res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getInt(5));
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
