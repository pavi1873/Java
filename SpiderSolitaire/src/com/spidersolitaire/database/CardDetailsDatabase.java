package com.spidersolitaire.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.spidersolitaire.common.Stacks;

public class CardDetailsDatabase {
	
	public static int getCardFromStack(Stacks stackColumn)
	{
		int cardValue = 0;
		String query = DatabaseQueries.getMaxDataFromStack.replaceAll("{0}", stackColumn.toString());
		ResultSet res =Database.selectQuery(query);
		try {
			while (res.next()) {
				cardValue =res.getInt("card");			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cardValue;
	}

	public static int insertCardToStack(Stacks stackColumn, int cardValue)
	{
		int result = 0;
		String query = DatabaseQueries.insertCardToStack.replace("{0}", stackColumn.toString()).replace("{1}", Integer.toString(cardValue));
		System.out.println(query);
		result =Database.modifyDataQuery(query);
	
		return result;
	}
	
	public static int deleteCardFromStack(String stackColumn)
	{
		int result = 0;
		String query = DatabaseQueries.deleteMaxdataFromStack.replaceAll("{0}", stackColumn);
		result =Database.modifyDataQuery(query);
	
		return result;
	}
	
	public static int deleteAllCardFromStack(Stacks stackColumn)
	{
		int result = 0;
		String query = DatabaseQueries.deleteAlldataFromStack.replace("{0}", stackColumn.toString());
		System.out.println(query);
		result =Database.modifyDataQuery(query);
	
		return result;
	}
	
	public static ArrayList<Integer> getCardListFromStack(Stacks stackColumn)
	{
		ArrayList<Integer> cardList = new ArrayList<Integer>();
		String query = DatabaseQueries.getDataFromStack.replaceAll("{0}", stackColumn.toString());
		ResultSet res =Database.selectQuery(query);
		try {
			while (res.next()) {
				cardList.add(res.getInt("card"));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cardList;
	}
}
