package org.jvsun.dao.proxy;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import org.jvsun.dao.UserLoginDAO;
import org.jvsun.dao.impl.UserLoginDAOImpl;
import org.jvsun.pojo.MenuPOJO;
import org.jvsun.pojo.VLoginPOJO;
import org.jvsun.tools.JDBCHelper;



public class UserLoginDAOProxy implements UserLoginDAO{
	Connection conn = null;
	UserLoginDAOImpl impl = null;
	public UserLoginDAOProxy(){
		try {
			this.conn = JDBCHelper.getConn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.impl = new UserLoginDAOImpl(this.conn);
		
	}
	public boolean checkLogin(String loginaccount ,String loginPassword ) {
		
		boolean flag = this.impl.checkLogin(loginaccount,loginPassword);
		this.close();
		return flag;
	}
	
	public void close(){
		try {
			this.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	public  VLoginPOJO findUserByLogin(String loginaccount){
	VLoginPOJO pojo= this.impl.findUserByLogin(loginaccount);
		this.close();
		return pojo;
		// TODO Auto-generated method stub
		
}
	public List<MenuPOJO>  findMenuByLoginId(int loginId) {
		// TODO Auto-generated method stub
		List<MenuPOJO> list = this.impl.findMenuByLoginId(loginId);
		this.close();
		return list;
	}
	
	public List<MenuPOJO> findChildMenuByFaherId(BigDecimal menuId) {
		List<MenuPOJO> list = this.impl.findChildMenuByFaherId(menuId);
		this.close();
		return list;
	}
	
}
