package org.jvsun.tools;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCHelper {
	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:dalin";
	public static final String DBNAME = "CRM";
	public static final String PASSWORD = "123";
	public static Connection getConn() throws Exception{
		Class.forName(DRIVER);
		Connection conn = DriverManager.getConnection(URL, DBNAME, PASSWORD);
		return conn;
		
		
	}
}
