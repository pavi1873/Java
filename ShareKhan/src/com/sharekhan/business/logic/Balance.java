package com.sharekhan.business.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.sharekhan.common.ApplicationConstants;
import com.sharekhan.database.lib.Database;
import com.sharekhan.database.lib.DatabaseQueries;


public class Balance {

	public static void readBalanceFile()
	{
		String fileName =ApplicationConstants.BALANCE_DATA_DIRECTORY_PATH+ApplicationConstants.BALANCE_DATA_FILE_PATH;
		String line1 = null;
        try 
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line1 = bufferedReader.readLine()) != null) 
            {
            	if(line1.trim().length() != 0 )
            	{
            		loadAmountToBalanceTable(line1.trim());
                }
            }	
            bufferedReader.close();		
            reCreateBalanceFile();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");				
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void reCreateBalanceFile()
	{		
		Path fileFrom = Paths.get(ApplicationConstants.BALANCE_TEMPLATE_DATA_DIRECTORY_PATH + ApplicationConstants.BALANCE_TEMPLATE_DATA_FILE_PATH);
	    Path fileTo = Paths.get(ApplicationConstants.BALANCE_DATA_DIRECTORY_PATH + ApplicationConstants.BALANCE_DATA_FILE_PATH);
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
	
	private static void loadAmountToBalanceTable(String balance)
	{		
		String query = null;
		try 
		{
			query= DatabaseQueries.insertBalanceAmount.replace("{1}", balance);
			System.out.println(query);
			Database.modifyDataQuery(query);			
			Database.dispose();
		} catch (Exception e)
		{
			e.printStackTrace();
		}	
		

	}
}
