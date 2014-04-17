package com.spidersolitaire.common;

public class ReadCardData {
	
	public void readCardFromStack(Stacks stackColumn)
	{
		String arguments = null;
		arguments = "\"DOWNLOAD_SOFTWARE\"";
		RunProcess.Run(ApplicationConstants.APPLICATION_DIRECTORY_PATH,ApplicationConstants.APPLICATION_EXECUTABLE_FILE,arguments);	
		
	}

}
