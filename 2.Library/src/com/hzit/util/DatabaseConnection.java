package com.hzit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	//锟斤拷锟斤拷锟斤拷菘锟�
 	String dbdriver = "com.mysql.jdbc.Driver";
    String dburl = "jdbc:mysql://127.0.0.1:3306/librarydemo?characterEncoding=UTF-8";
    String dbuser = "root";
    String dbpass = "root";
    
    private Connection conn;
    
    public DatabaseConnection() throws Exception{
    	Class.forName(dbdriver);
    	conn = DriverManager.getConnection(dburl,dbuser,dbpass);
    }
    
    public Connection getConn(){
    	return conn;
    }
    
    public void close() throws SQLException{
    	conn.close();
    }
}
