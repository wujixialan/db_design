package com.db_design.utils;

import com.mysql.jdbc.Driver;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
	public static Connection getConnection() throws Exception {
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		/**
		 *  读取内路径下的 jdbc.properties 文件
		 */
		InputStream in =
				DBUtils.class.getClassLoader().getResourceAsStream("db.properties");
		Properties pro = new Properties();
		pro.load(in);
		driverClass = pro.getProperty("jdbc.driverClass");
		jdbcUrl = pro.getProperty("jdbc.jdbcUrl");
		user = pro.getProperty("jdbc.user");
		password = pro.getProperty("jdbc.password");
		Driver driver = (Driver) Class.forName(driverClass).newInstance();
		Properties info = new Properties();
		info.put("user", user);
		info.put("password", password);
		Connection con = driver.connect(jdbcUrl, info);
		return con;
	}
	
	//编译执行
	public static PreparedStatement getPs(Connection conn , String sql){
		PreparedStatement ps = null; 
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps ; 
	}

	public static void close(Connection conn){
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	}
	
	public static void close(ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(PreparedStatement ps){
		if(ps != null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		DBUtils.getConnection();
	}
}
