package org.jvsun.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.jvsun.dao.ModifyPasswordDAO;

public class ModifyPasswordDAOImpl implements ModifyPasswordDAO {
	public Connection conn;
	public ModifyPasswordDAOImpl(Connection conn){
		this.conn=conn;
	}
	public boolean doUpdate(String password,BigDecimal loginId) {
		boolean flag = false;
		PreparedStatement pstate = null;
		String sql = "update login set password= ? where login_id =?";
		try {
			this.conn.setAutoCommit(false);
			pstate= this.conn.prepareStatement(sql);
			pstate.setString(1, password);//ְ    סַ
			pstate.setBigDecimal(2, loginId);//ְ  ID
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

}