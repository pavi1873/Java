package com.sharekhan.common;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sharekhan.database.lib.Database;
import com.sharekhan.database.lib.DatabaseQueries;

public class GetTimeProcessing {
	
	public static boolean isSoftwareUpdated()
	{
		boolean isUpdated = false;		
		int id = 0;		
		try {
			ResultSet resultSet = Database.selectQuery(DatabaseQueries.getDownloadSoftwareCount);
			while (resultSet.next()) {
				id =resultSet.getInt("total_count");			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			Database.dispose();
		}
		isUpdated = (id==0)? false: true;
		
		return isUpdated;
	}
	
	public static boolean isBalanceUpdated() 
	{
		boolean isUpdated = false;		
		int id = 0;		
		try {
			ResultSet resultSet = Database.selectQuery(DatabaseQueries.getBalanceCount);
			while (resultSet.next()) {
				id =resultSet.getInt("total_count");			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			Database.dispose();
		}
		isUpdated = (id==0)? false: true;
		
		return isUpdated;
	}
}
