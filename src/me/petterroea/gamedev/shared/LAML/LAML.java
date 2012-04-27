package me.petterroea.gamedev.shared.LAML;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * LAML reader.
 * 
 * LAML stands for "Lame-ass Markup language"
 * @author petterroea
 *
 */
public class LAML {
	String raw = "";
	public LAML(File file)
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
			  raw = total;
			  generateNodes();
			    }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
	}
	public LAML(InputStream stream)
	{
		try{
			DataInputStream in = new DataInputStream(stream);
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
			raw = total;
			generateNodes();
		}catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
		}
	}
	public void generateNodes()
	{
		raw = raw.replace("\n", "");
	}
}
