package com.spidersolitaire.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "spider_solitaire";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM cards ORDER BY id ASC");
			while (res.next()) {
				int id = res.getInt("id");
				System.out.println(id);
			}

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Connection getConnection()
	{
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "spider_solitaire";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		Connection conn = null;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return conn;
	}
	
	public static ResultSet selectQuery(String query)
	{
		Connection conn = getConnection();
		Statement st = null;
		ResultSet resultSet = null;
		try {
			st = conn.createStatement();
			resultSet = st.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return resultSet;
	}
	
	public static int modifyDataQuery(String query)
	{
		Connection conn = getConnection();
		Statement st = null;
		int result =0;
		try {
			st = conn.createStatement();
			result = st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return result;
	}
}


