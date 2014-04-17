package com.sharekhan.business.logic;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sharekhan.database.lib.Database;
import com.sharekhan.database.lib.DatabaseQueries;

public class CalculateTrade {
	private final int TOTAL_TRADE_ALLOWED = 4;
	private final double RESERVE_BALANCE  = 500.00;
	
	public double getTotalTradeAmount()
	{
		double tradeableAmount =0;
		tradeableAmount = (getBalance() - RESERVE_BALANCE)/(TOTAL_TRADE_ALLOWED-getTotalTradePerformed());		
		return tradeableAmount;
	}
	
	public int getQuantity(double scripValue)
	{
		int quantity =0;
		
		quantity =  (int) (getTotalTradeAmount()/scripValue);
		
		return quantity;
	}
	
	private double getBalance()
	{
		double latestBalance =0;
		try {
			ResultSet resultSet = Database.selectQuery(DatabaseQueries.getLatestBalance);
			while (resultSet.next()) {
				latestBalance=resultSet.getDouble("balance");	
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		finally
		{
			Database.dispose();
		}
		return latestBalance;
	}
	
	public static int getTotalTradePerformed()
	{
		int count =0;
		try {
			ResultSet resultSet = Database.selectQuery(DatabaseQueries.getTradePerfromedCountPerDate);
			while (resultSet.next()) {
				count=resultSet.getInt("total_count");	
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		finally
		{
			Database.dispose();
		}
		return count;
	}
	
	//public void
	

}
