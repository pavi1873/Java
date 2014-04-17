package com.sharekhan.exec;

import java.io.File;
import java.io.IOException;

public class RunProcess {

	public static void Run(String workingPath, String executableFile, String arguments)
	{
		  ProcessBuilder pb = new ProcessBuilder("cmd", "/c","start" ,executableFile, arguments);
		  pb.directory(new File(workingPath));
		  try 
		  {
			pb.start();
		  }
		  catch (IOException e) 
		  {
			e.printStackTrace();
		  }     
	}
}
