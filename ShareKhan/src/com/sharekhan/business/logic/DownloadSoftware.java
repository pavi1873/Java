package com.sharekhan.business.logic;

import com.sharekhan.database.lib.Database;
import com.sharekhan.database.lib.DatabaseQueries;

public class DownloadSoftware {
	
	public static void insertDownloadSoftwareTable()
	{		
		String query = null;
		try 
		{
			query= DatabaseQueries.insertDownloadSoftware;
			System.out.println(query);
			Database.modifyDataQuery(query);			
			Database.dispose();
		} catch (Exception e)
		{
			e.printStackTrace();
		}	

	}

}
