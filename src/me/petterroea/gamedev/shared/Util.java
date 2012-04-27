package me.petterroea.gamedev.shared;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Util {
	public static String readFile(File file)
	{
		try{
			  // Open the file that is the first 
			  // command line parameter
			  FileInputStream fstream = new FileInputStream(file);
			  // Get the object of DataInputStream
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  String total = "";
			  //Read File Line By Line
			  while ((strLine = br.readLine()) != null)   {
			  // Print the content on the console
			  System.out.println (strLine);
			  total = total + strLine + "\n";
			  }
			  //Close the input stream
			  in.close();
			  return total;
			    }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
		return null;
	}
	public static String readFile(InputStream file)
	{
		try{
			  // Open the file that is the first 
			  // command line parameter
			  // Get the object of DataInputStream
			  DataInputStream in = new DataInputStream(file);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  String total = "";
			  //Read File Line By Line
			  while ((strLine = br.readLine()) != null)   {
			  // Print the content on the console
			  System.out.println (strLine);
			  total = total + strLine + "\n";
			  }
			  //Close the input stream
			  in.close();
			  return total;
			    }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
		return null;
	}
}
