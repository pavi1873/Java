package com.marketstocktips.business.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.marketstocktips.business.data.Tip;
import com.marketstocktips.common.ApplicationConstants;
import com.marketstocktips.database.lib.Database;
import com.marketstocktips.database.lib.DatabaseQueries;

public class ProcessTips {
	
	public static void main(String[] args) 
	{
		//readTipFile();
		
		ProcessTips p = new ProcessTips();
		p.processTips();
		
	}
	
	public static void readTipFile()
	{
		String fileName =ApplicationConstants.TIP_DATA_DIRECTORY_PATH+ApplicationConstants.TIP_DATA_FILE_PATH;
		String line1 = null;
		String line2 = null;
        try 
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line1 = bufferedReader.readLine()) != null) 
            {
            	if(line1.trim().length() != 0 )
            	{
            		if ((line2 = bufferedReader.readLine()) != null && line2.trim().length() != 0)
            		{
                		loadTipToMarketStockTipTable(line1, line2);
            		}
                }
            }	
            bufferedReader.close();		
            reCreateTipsFile();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");				
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void reCreateTipsFile()
	{		
		Path fileFrom = Paths.get(ApplicationConstants.TIP_TEMPLATE_DATA_DIRECTORY_PATH + ApplicationConstants.TIP_TEMPLATE_DATA_FILE_PATH);
	    Path fileTo = Paths.get(ApplicationConstants.TIP_DATA_DIRECTORY_PATH + ApplicationConstants.TIP_DATA_FILE_PATH);
		try
		{
			if(Files.exists(fileTo))
			{	
				Files.copy(fileFrom, fileTo, StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	private static void loadTipToMarketStockTipTable(String readLine1, String readLine2)
	{		
		String tipDate = readLine1.split("</b><br>")[0].trim().substring(3).trim();
		String tipContent = readLine2.trim().substring(11).trim();
		String query = DatabaseQueries.checkForMarketStockTips.replace("{1}", tipContent).replace("{2}", tipDate);				
		int isPresent = 0;
		try 
		{
			System.out.println(query);
			isPresent = Database.executeScalar(query,"total_count");
			
			if(isPresent == 0)
			{
				query= DatabaseQueries.insertMarketStockTips.replace("{1}", tipContent).replace("{2}", tipDate);
				System.out.println(query);
				Database.modifyDataQuery(query);
			}
			Database.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}	

	}
	
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
			Database.dispose();
		} catch (SQLException e) {
			e.printStackTrace();
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
			while (resultSet.next()) 
			{
				id =resultSet.getInt("id");	
				tipContent =resultSet.getString("tip");					
				tip = analyseTip(id, tipContent);
				if	(tip.getStatus() == "processed")
				{
					insertTipDetails(tip);
				}
				updateTipStatus(tip);
			}			
			Database.dispose();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updateTipStatus(Tip tip)
	{		
		String query = DatabaseQueries.updateMarketStockTipsStatus.replace("{1}", Integer.toString(tip.getTipId())).replace("{2}", tip.getStatus());
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
			
			ResultSet resultSet = Database.selectQuery(DatabaseQueries.getProcessedTipID);
			System.out.println(DatabaseQueries.getProcessedTipID);
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
			Database.dispose();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	private boolean isNewTipPresent()
	{
		boolean isNewTipPresent = false;		
		int id = 0;		
		id = Database.executeScalar(DatabaseQueries.getMarketStockTipsCount,"total_count");		
		isNewTipPresent = (id==0)? false: true;
		
		return isNewTipPresent;
	}
	
	private Tip analyseTip(int id, String tipContent)
	{
		System.out.println("Processing tip id : "+id);
		
		if (tipContent == null)
		{
			return null;
		}
		
		Tip tip = new Tip();
		String content[] = tipContent.trim().toUpperCase().split(" ");
		
		if(content[0].contains("INTRADAY/"))
		{
			String dataContent = null;
			if(tipContent.toUpperCase().contains("SHORT TERM")) 
			{
				dataContent = tipContent.substring(tipContent.toUpperCase().indexOf("SHORT TERM")+ 10).trim();
				tip = analyseShortTermTip( id, dataContent);
				tip.setStatus("processed");
			}
			else if(tipContent.toUpperCase().contains(" DAYS"))
			{
				dataContent = tipContent.substring(tipContent.toUpperCase().indexOf("DAYS")+ 4).trim();
				tip = analyseShortTermTip( id, dataContent);
				tip.setStatus("processed");
			}
		}		
		else if(content[0].contains("INTRADAY"))
		{
			tip = analyseIntraDayTip( id, tipContent.trim());
			tip.setStatus("processed");
		}
		else
		{
			tip.setTipId(id);
			tip.setStatus("not processed");
		}
		return tip;
	}
	
	private Tip analyseIntraDayTip(int id, String tipContent)
	{		
		Tip tip = new Tip();
		tip.setTipId(id);
		tip.setTipType(SplitData(tipContent, " ", 0));
		tip.setTradeType(SplitData(tipContent, " ", 1));
		
		String fetchTradeDetail = tipContent.substring(tip.getTipType().length()+1+tip.getTradeType().length()).trim();
		String splitAroundValue=tip.getTradeType().toUpperCase().contains("BUY")?"around/above":"around/below";		
		tip.setScripName(SplitData(fetchTradeDetail,splitAroundValue, 0).trim());
		
		String fetchTransactionDetail = fetchTradeDetail.substring(tip.getScripName().length() + 1 + splitAroundValue.length()).trim();		
		tip.setScripValue(Double.valueOf(SplitData(fetchTransactionDetail,"Targets:",0).replace(",", " ").trim()));		
		
		String tipTarget =  SplitData(SplitData(tipContent,"Targets:", 1),"Stoploss:",0).trim();		
		tip.setTargets(SplitDataForArrayList(tipTarget));
		
		String tipStopLoss =  SplitData(tipContent,"Stoploss:", 1).trim();
		tip.setStopLosses(SplitDataForArrayList(tipStopLoss));
		
		return tip;
	}
	
	private Tip analyseShortTermTip(int id, String tipContent)
	{
		Tip tip = new Tip();
		tip.setTipId(id);
		tip.setTipType("SHORT TERM");
		tip.setTradeType(SplitData(tipContent.trim(), " ", 0));
		
		String fetchTradeDetail = tipContent.substring(tip.getTradeType().length()).trim();
		String splitAroundValue=tip.getTradeType().toUpperCase().contains("BUY")?"around/above":"around/below";		
		tip.setScripName(SplitData(fetchTradeDetail,splitAroundValue, 0).trim());
		
		String fetchTransactionDetail = fetchTradeDetail.substring(tip.getScripName().length() + 1 + splitAroundValue.length()).trim();		
		tip.setScripValue(Double.valueOf(SplitData(fetchTransactionDetail,"Targets:",0).replace(",", " ").trim()));		
		
		String tipTarget =  SplitData(SplitData(tipContent,"Targets:", 1),"Stoploss:",0).trim();		
		tip.setTargets(SplitDataForArrayList(tipTarget));
		
		String tipStopLoss =  SplitData(tipContent,"Stoploss:", 1).trim();
		tip.setStopLosses(SplitDataForArrayList(tipStopLoss));
		
		return tip;
	}
	
	private String SplitData(String stringValue, String expression, int count)
	{		
		String stringArray[] = new String[100];
		stringArray=stringValue.split(expression);
		if(stringValue.split(expression).length > count)
		{
			return stringArray[count].trim();
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
				try
				{
					arrayList.add(Double.valueOf(splitArray[i].trim()));
				}
				catch(Exception e)
				{
					System.out.println("Exception : "+ splitArray[i].trim() );
				}
			}
		}
		return arrayList;
	}

}
