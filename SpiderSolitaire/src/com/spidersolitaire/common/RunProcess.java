package com.spidersolitaire.common;

import java.io.File;
import java.io.IOException;

public class RunProcess {

	public static int Run(String workingPath, String executableFile, String arguments)
	{
		  ProcessBuilder pb = new ProcessBuilder("cmd", "/c","start" ,executableFile, arguments);
		  pb.directory(new File(workingPath));
		  int returnValue =0;
		  try 
		  {
			pb.start();
		  }
		  catch (IOException e) 
		  {
			e.printStackTrace();
		  }
		  return returnValue;
	}
}
