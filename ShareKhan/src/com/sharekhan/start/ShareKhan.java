package com.sharekhan.start;

import java.util.concurrent.TimeUnit;

import com.sharekhan.business.logic.Balance;
import com.sharekhan.business.logic.DownloadSoftware;
import com.sharekhan.business.logic.ProcessTips;
import com.sharekhan.common.*;
import com.sharekhan.exec.RunProcess;

public class ShareKhan {

	public static void main(String[] args) 
	{	
		try
		{
			while(true)
			{
				DayTime dayTime = TimeManagement.GetJobType();
				dayTime = DayTime.OPENING_BALANCE_TIME;
				System.out.println("Main Process : "+ dayTime);
				switch(dayTime)
				{
					case DOWNLOAD_TIME:			
						downloadTime();	
						break;
					case OPENING_BALANCE_TIME:
						getbalance();
						break;
					case TRADE_TIME:
						//String arguments = "\"BUY\" \"BSE\" \"TCS\" \"10\" \"10\" \"1000.00\" \"1000.00\"";
						//String arguments = "\"SELL\" \"BSE\" \"TCS\" \"10\" \"10\" \"1000.00\" \"1000.00\"";
						
						//arguments = "\"BUY\" \"BSE\" \"TCS\" \"10\" \"10\" \"1000.00\" \"1000.00\"";
						//RunProcess.Run(ApplicationConstants.APPLICATION_DIRECTORY_PATH,ApplicationConstants.APPLICATION_EXECUTABLE_FILE,arguments);
						break;
					case WAIT_TIME:
						break;
					default:
						break;					
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void processTips()
	{
		ProcessTips processTip = new ProcessTips();
		processTip.processTips();
	}
	
	public static void downloadTime() throws Exception 
	{
		String arguments = "\"DOWNLOAD_SOFTWARE\"";
		RunProcess.Run(ApplicationConstants.APPLICATION_DIRECTORY_PATH,ApplicationConstants.APPLICATION_EXECUTABLE_FILE,arguments);		
		TimeUnit.MINUTES.sleep(5);			
		DownloadSoftware.insertDownloadSoftwareTable();
	}
	
	public static void getbalance() throws Exception  
	{
		String arguments = "\"BALANCE\"";
		RunProcess.Run(ApplicationConstants.APPLICATION_DIRECTORY_PATH,ApplicationConstants.APPLICATION_EXECUTABLE_FILE,arguments);						
		TimeUnit.MINUTES.sleep(1);			
		Balance.readBalanceFile();
	}
	

}
