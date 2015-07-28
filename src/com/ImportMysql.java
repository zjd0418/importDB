package com;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
public class ImportMysql {
	private Connection connection;
	private PreparedStatement ps;
	public ImportMysql(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("加载驱动失败");
		}
		String url = "jdbc:mysql://localhost:3306/jc?characterEncoding=UTF-8";
		String user = "root";
		String password = "mysql";
		try {
			connection = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("连接mysql数据库失败");
		}
	}
	
	public boolean ImportCopyDB(String name , int age , java.sql.Timestamp releaseDate){
	
		String sql = "insert into copy(name,age,releaseDate) values(?,?,?)";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1,name);
			ps.setInt(2,age);
			ps.setTimestamp(3,releaseDate);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("插入失败...");
			return false;
		}
		
		return true;
	}
	
	public void Close(){
		if(null != ps){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(null != connection){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
