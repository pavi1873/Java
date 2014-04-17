package com.sharekhan.business.logic;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sharekhan.database.lib.Database;
import com.sharekhan.database.lib.DatabaseQueries;

public class PreTrading {

	public static void performPreTrade()
	{
		
	}
		
	private void processPreTradeSetup()
	{	
		
		
	}
		
	private void tradePerformed()
	{
		String query = null;
		
		if(CalculateTrade.getTotalTradePerformed() == 0)
		{	
			query = DatabaseQueries.insertTradePerformed;			
		}
		else
		{
			query = DatabaseQueries.updateTradePerformed;
		}
		
		if(query != null)
		{
			System.out.println(query);		
			Database.modifyDataQuery(query);
		}
	}
	
	public boolean isNewProcessedTipPresent()
	{
		boolean isNewProcessedTipPresent = false;		
		int id = 0;		
		try {
			ResultSet resultSet = Database.selectQuery(DatabaseQueries.getProcessedTipsCount);
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
		isNewProcessedTipPresent = (id==0)? false: true;
		
		return isNewProcessedTipPresent;
	}
}
