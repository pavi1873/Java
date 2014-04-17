package com.marketstocktips.start;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.marketstocktips.business.logic.ProcessTips;
import com.marketstocktips.common.ApplicationConstants;
import com.marketstocktips.exec.RunProcess;


public class MarketStockTips {

	public static void main(String[] args) 
	{
		displayMessages("Process starting : ");
		try 
		{
			while(true)
			{	
				displayMessages("Process running : ");
				Thread.sleep(30000);
				displayMessages("Auto It execution started : ");
				RunProcess.Run(ApplicationConstants.APPLICATION_DIRECTORY_PATH,ApplicationConstants.APPLICATION_EXECUTABLE_FILE,"");
				Thread.sleep(60000);
				displayMessages("Auto It execution completed : ");
				ProcessTips.readTipFile();
				ProcessTips processTips = new ProcessTips();
				processTips.processTips();				
				displayMessages("Process completed : ");
			}
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	
	private static void displayMessages(String messages)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		System.out.println(messages + dateFormat.format(cal.getTime()));
	}	
	
}
