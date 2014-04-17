package com.sharekhan.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeManagement {
	
	public static void main(String[] args) 
	{
		GetJobType();
	}

	public static DayTime GetJobType()
	{ 
		DayTime dayTime =DayTime.NONE;
		
		Calendar cal = Calendar.getInstance();
    	
		SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
    	int hours = Integer.parseInt(hourFormat.format(cal.getTime()));
    	
    	SimpleDateFormat minuteFormat = new SimpleDateFormat("mm");
    	int minutes = Integer.parseInt(minuteFormat.format(cal.getTime()));
    	
    	if (hours == 8 && (minutes > 01 && minutes <30 ) && !GetTimeProcessing.isSoftwareUpdated())
    	{
    		dayTime=DayTime.DOWNLOAD_TIME;
       	}
    	if (hours == 8 && (minutes > 40 && minutes <45 ) && !GetTimeProcessing.isBalanceUpdated())
    	{
    		dayTime=DayTime.OPENING_BALANCE_TIME;
    	}
    	else if ((hours > 9 && hours < 16) )
    	{
    		dayTime=DayTime.TRADE_TIME;
    	}
    	else
    	{
    		dayTime=DayTime.WAIT_TIME;
    	}
		
    	System.out.println("Time period : " + dayTime.toString());
    	
    	return dayTime;
	}
}
