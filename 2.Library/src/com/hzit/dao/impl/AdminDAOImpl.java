package com.hzit.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hzit.dao.IAdminDAO;
import com.hzit.entity.Admin;
import com.hzit.util.DatabaseConnection;

public class AdminDAOImpl implements IAdminDAO {

	@Override
	public Admin login(String username, String password) {
		// TODO Auto-generated method stub
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection conn = db.getConn();
			
			String sql = "select * from admin where username='"+username+"' and password='"+password+"'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(2);
				Admin admin = new Admin();
				admin.setId(id);
				admin.setUsername(name);
				return admin;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
