package com.spidersolitaire.actions;

import com.spidersolitaire.common.ApplicationConstants;
import com.spidersolitaire.common.RunProcess;


public class FirstCardReadFromAllStacks {
	
	public void start()
	{
		String arguments = null;
		
		//String arguments = "\"BUY\" \"BSE\" \"TCS\" \"10\" \"10\" \"1000.00\" \"1000.00\"";
		arguments = "\"DOWNLOAD_SOFTWARE\"";		
		RunProcess.Run(ApplicationConstants.APPLICATION_DIRECTORY_PATH,ApplicationConstants.APPLICATION_EXECUTABLE_FILE,arguments);	
		
	}

}
