package com.marketstocktips.database.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private static Connection conn = null;
	private static Statement st = null;
	
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "share_khan";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM  opening_balance");
			while (res.next()) {
				int id = res.getInt("id");
				double balance = res.getDouble("balance");
				System.out.println(id + "\t" + balance);
			}

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Connection getConnection()
	{
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "share_khan";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
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
		
		ResultSet resultSet = null;
		try {
			conn = getConnection();
			st = conn.createStatement();
			resultSet = st.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return resultSet;
	}
	
	public static int executeScalar(String query , String columnName)
	{
		
		ResultSet resultSet = null;
		int returnValue=0;
		try {
			conn = getConnection();
			st = conn.createStatement();
			resultSet = st.executeQuery(query);
			while(resultSet.next())
			{
				returnValue = resultSet.getInt(columnName);
			}
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
		return returnValue;
	}
	
	public static int modifyDataQuery(String query)
	{
		int result =0;
		try {
			conn = getConnection();
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
	
	public static void dispose()
	{
		try {		
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}


