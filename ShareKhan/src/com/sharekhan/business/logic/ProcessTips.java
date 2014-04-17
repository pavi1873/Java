package com.sharekhan.business.logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sharekhan.business.data.Tip;
import com.sharekhan.database.lib.Database;
import com.sharekhan.database.lib.DatabaseQueries;

public class ProcessTips {
	
	public static ArrayList<Tip> getNewProcessedTips()
	{
		ArrayList<Tip> tipList = new ArrayList<Tip>();
		try {
			ResultSet resultSet = Database.selectQuery(DatabaseQueries.getNewProcessedTips);
			while (resultSet.next()) {
				Tip tip = new Tip();
				tip.setTipId(resultSet.getInt("id"));	
				tip.setScripName(resultSet.getString("tip"));					
				tip.setTipType(resultSet.getString("tip_type"));
				tip.setTradeType(resultSet.getString("trade_type"));
				tip.setScripValue(resultSet.getDouble("scrip_amount"));
				tip.setStatus(resultSet.getString("status"));
				tipList.add(tip);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			Database.dispose();
		}
		return tipList;
	}
	
	public void processTips()
	{
		if(!isNewTipPresent())
		{
			return ;
		}
		
		int id = 0;
		String tipContent = null;
		Tip tip = new Tip();
		
		try {
			ResultSet resultSet = Database.selectQuery(DatabaseQueries.getNewMarketStockTips);
			while (resultSet.next()) {
				id =resultSet.getInt("id");	
				tipContent =resultSet.getString("tip");					
				tip = analyseTip(id, tipContent);	
				insertTipDetails(tip);
				updateTipStatusAsProcessed(tip);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			Database.dispose();
		}
	}
	
	private void updateTipStatusAsProcessed(Tip tip)
	{		
		String query = DatabaseQueries.updateMarketStockTipsAsProcessed.replace("{0}", Integer.toString(tip.getTipId()));
		System.out.println(query);
		Database.modifyDataQuery(query);
	}
	
	private void insertTipDetails(Tip tip)
	{
		String query = DatabaseQueries.insertProcessedTip.replace("{1}", Integer.toString(tip.getTipId())).replace("{2}", tip.getTipType())
				.replace("{3}", tip.getTradeType()).replace("{4}", tip.getScripName()).replace("{5}", Double.toString(tip.getScripValue()));
		System.out.println(query);
		int tipId=0;
		try {
			Database.modifyDataQuery(query);
			
			ResultSet resultSet = Database.selectQuery(DatabaseQueries.getNewMarketStockTips);
			while (resultSet.next()) {
				tipId =resultSet.getInt("id");						
			}
			
			for(double targetValue :  tip.getTargets())
			{
				query=DatabaseQueries.insertProcessedTipData.replace("{1}", Integer.toString(tipId)).replace("{2}","Target").replace("{3}",Double.toString(targetValue));
				System.out.println(query);
				Database.modifyDataQuery(query);
			}
			
			for(double stopLossValue :  tip.getStopLosses())
			{
				query=DatabaseQueries.insertProcessedTipData.replace("{1}", Integer.toString(tipId)).replace("{2}","StopLoss").replace("{3}",Double.toString(stopLossValue));
				System.out.println(query);
				Database.modifyDataQuery(query);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		finally
		{
			Database.dispose();
		}
	}
	
	private boolean isNewTipPresent()
	{
		boolean isNewTipPresent = false;		
		int id = 0;		
		try {
			ResultSet resultSet = Database.selectQuery(DatabaseQueries.getMarketStockTipsCount);
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
		isNewTipPresent = (id==0)? false: true;
		
		return isNewTipPresent;
	}
	
	private Tip analyseTip(int id, String tipContent)
	{
		if (tipContent == null)
		{
			return null;
		}
		
		Tip tip = new Tip();
		
		if(tipContent.toUpperCase().contains("INTRADAY"))
		{
			tip = analyseIntraDayTip( id, tipContent);
		}
		return tip;
	}
	// INTRADAY SELL YESBANK around/below 369, Targets: 364, 360, stoploss: 375
	
	private Tip analyseIntraDayTip(int id, String tipContent)
	{
		Tip tip = new Tip();
		tip.setTipId(id);
		tip.setTipType(SplitData(tipContent, " ", 0));
		tip.setTradeType(SplitData(tipContent, " ", 1));
		tip.setScripName(SplitData(SplitData(tipContent,"around/below", 0), " ", 2));
		tip.setScripValue(Double.valueOf(SplitData(SplitData(tipContent,"around/below", 1),"Targets:",0).replace(",", "")));
		
		String tipTarget =  SplitData(SplitData(tipContent,"Targets:", 1),"stoploss:",0);
		tip.setTargets(SplitDataForArrayList(tipTarget));
		
		String tipStopLoss =  SplitData(tipContent,"stoploss:", 1);
		tip.setStopLosses(SplitDataForArrayList(tipStopLoss));
		
		return tip;
	}
	
	private String SplitData(String stringValue, String expression, int count)
	{		
		String stringArray[] = new String[100];
		stringArray=stringValue.split(expression);
		if(stringValue.split(expression).length > count)
		{
			return stringArray[count];
		}
		else 
		{
			return null;
		}		
	}
	
	private ArrayList<Double> SplitDataForArrayList(String stringValue)
	{
		ArrayList<Double> arrayList = new ArrayList<Double>();
		String splitArray[] = new String[4];
		splitArray = stringValue.split(",");
		for(int i=0; i<splitArray.length;i++)
		{
			if(splitArray[i] != null && !splitArray[i].equals(" "))
			{
				arrayList.add(Double.valueOf(splitArray[i].trim()));
			}
		}
		return arrayList;
	}
	
	public static void main(String[] args) 
	{
		ProcessTips p = new ProcessTips();
		p.analyseTip(1,"INTRADAY SELL YESBANK around/below 369, Targets: 364, 360, stoploss: 375");
	}
}
