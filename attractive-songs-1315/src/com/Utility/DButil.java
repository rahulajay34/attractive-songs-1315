package com.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {

	public static Connection provideConnection(){
		Connection conn= null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String url="jdbc:mysql://localhost:3306/cw";
		
		try {
			conn= DriverManager.getConnection(url,"root","1111");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return conn;
	}
	
}
