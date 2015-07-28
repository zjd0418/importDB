package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadMsSql {
	
	private Connection connection;
	private ResultSet rs;
	private PreparedStatement ps;
	public ReadMsSql(){
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
	
	public ResultSet getMsSqlData(String tableName){
		String sql = "select * from "+tableName+"";
		
		try {
			ps= connection.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询失败");
		}
		return rs;
	}
	
	public void Close(){
		if(null != ps){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(null != rs){
			try {
				rs.close();
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
