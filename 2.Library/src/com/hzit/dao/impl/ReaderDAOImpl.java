package com.hzit.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hzit.dao.IReaderDAO;
import com.hzit.entity.Reader;
import com.hzit.entity.ReaderType;
import com.hzit.util.DatabaseConnection;

public class ReaderDAOImpl implements IReaderDAO {

	@Override
	public int add(Reader reader) {
		// TODO Auto-generated method stub
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection conn = db.getConn();
			
			String sql = "insert into reader(username,password,name,tel,typeid,cardid,gender) values('"+reader.getUsername()+"','"+reader.getPassword()+
					"','"+reader.getName()+"','"+reader.getTel()+"',"+reader.getTypeid()+",'"+
					reader.getCardid()+"','"+reader.getGender()+"')";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			int i = pstmt.executeUpdate();
			
			pstmt.close();
			db.close();
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public boolean isReaderExist(String username) {
		// TODO Auto-generated method stub
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection conn = db.getConn();
			
			String sql = "select username from reader where username='"+username+"'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				return true;
			}
			rs.close();
			pstmt.close();
			db.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Reader login(String username, String password) {
		// TODO Auto-generated method stub
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection conn = db.getConn();
			
			String sql = "select * from reader where username='"+username+"' and password='"+password+"'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(4);
				int typeid = rs.getInt(6);
				String gender = rs.getString(8);
				Reader reader = new Reader();
				reader.setId(id);
				reader.setName(name);
				reader.setGender(gender);
				
				ReaderType readerType = getReaderTypeById(typeid);
				reader.setReaderType(readerType);
				
				return reader;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ReaderType getReaderTypeById(int typeid) {
		// TODO Auto-generated method stub
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection conn = db.getConn();
			
			String sql = "select * from readertype where id="+typeid;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int number = rs.getInt(3);
				ReaderType readerType = new ReaderType();
				readerType.setId(id);
				readerType.setName(name);
				readerType.setNumber(number);
				return readerType;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Reader getReaderById(int readid) {
		// TODO Auto-generated method stub
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection conn = db.getConn();
			
			String sql = "select * from reader where id="+readid;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(4);
				String tel = rs.getString(5);
				String cardid = rs.getString(7);
				Reader reader = new Reader();
				reader.setId(id);
				reader.setName(name);
				reader.setTel(tel);
				reader.setCardid(cardid);
				return reader;
			}
			rs.close();
			pstmt.close();
			db.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
