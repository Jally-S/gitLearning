package org.jvsun.dao.proxy;

import java.sql.Connection;
import java.util.List;

import org.jvsun.dao.PhotoDAO;
import org.jvsun.dao.impl.PhotoDAOImpl;
import org.jvsun.pojo.PhotoPOJO;
import org.jvsun.tools.JDBCHelper;

public class PhotoDAOProxy implements PhotoDAO {
	Connection conn = null;
	PhotoDAOImpl impl=null;
	public PhotoDAOProxy(){
		try {
			this.conn=JDBCHelper.getConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.impl=new PhotoDAOImpl(this.conn);
	}
	public boolean doIns(PhotoPOJO pojo) {
		boolean flag = this.impl.doIns(pojo);
		this.close();
		return flag;
	}
	public boolean doDel(int PhotoId) {
		boolean flag = this.impl.doDel(PhotoId);
		this.close();
		return flag;
	}
	public boolean doUpd(PhotoPOJO pojo) {
		// TODO Auto-generated method stub
		return false;
	}
	public List<PhotoPOJO> findAllByContractId(int ContractId) {
		List<PhotoPOJO> list=impl.findAllByContractId(ContractId);
		this.close();
		return list;
	}
	public List<PhotoPOJO> findAllByPhotoId(int PhotoId) {
		// TODO Auto-generated method stub
		return null;
	}
	public void close(){
		try {
			this.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
