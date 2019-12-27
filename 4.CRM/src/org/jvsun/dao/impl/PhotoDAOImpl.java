package org.jvsun.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jvsun.dao.PhotoDAO;
import org.jvsun.pojo.PhotoPOJO;

public class PhotoDAOImpl implements PhotoDAO {
	Connection conn;
	public PhotoDAOImpl(Connection conn){
		this.conn = conn;
	}
	public boolean doIns(PhotoPOJO pojo) {
		boolean flag=false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql="insert into photo(photo_id, photo_time, photo_name, contract_id  )values(DH5.nextval,sysdate,?,?)";
			pstate = this.conn.prepareStatement(sql);
			pstate.setString(1, pojo.getPhotoName());
			pstate.setInt(2,pojo.getContractId());
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

	public boolean doDel(int PhotoId) {
		boolean flag = false;
		PreparedStatement pstate = null;
		try {
			this.conn.setAutoCommit(false);
			String sql = "update photo set is_del = 0 where photo_id = ? ";
			pstate = this.conn.prepareStatement(sql);
			pstate.setInt(1, PhotoId);
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

	public boolean doUpd(PhotoPOJO pojo) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<PhotoPOJO> findAllByContractId(int ContractId) {
		List<PhotoPOJO> list = new ArrayList<PhotoPOJO>();
		PhotoPOJO pojo = null;
		PreparedStatement pstate = null;
		ResultSet res = null;
		String sql = "select photo_id,photo_name ,is_del from photo where contract_id = ? and is_del=1 ";
		try {
			pstate = this.conn.prepareStatement(sql);
			pstate.setInt(1,ContractId);
			res = pstate.executeQuery();
			while(res.next()){
				pojo=new PhotoPOJO(res.getInt(1),res.getString(2),ContractId);
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
	

	public List<PhotoPOJO> findAllByPhotoId(int PhotoId) {
		return null;
	}
}