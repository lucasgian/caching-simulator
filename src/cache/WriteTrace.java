package cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteTrace 
{
	WriteTrace(String assoc, String word) throws IOException
	{
		String[] file = new String[10020];
		
		readFile(file);
		
		file[0] = "1024"; file[1] = word; file[2] = assoc; file[3] = "9000";
		writeFile(file);
	}
	
	private void readFile(String[] file) throws IOException
	{
		BufferedReader arq = openRead("trace.in");
		String  line = arq.readLine(); line = arq.readLine(); line = arq.readLine(); line = arq.readLine(); line = arq.readLine();
		
		for(int i=3; line != null; i++)
		{
			file[i] = line;
			line = arq.readLine();
		}
	}
	
	private void writeFile(String[] file) throws IOException
	{
		PrintWriter arq = openWrite("trace.in");
		
		
		for(int i=0, size = file.length; i < size; i++)
		{
			arq.println(file[i]);
		}
	}
	
	private PrintWriter openWrite(String fileName)throws IOException
	{
		return new PrintWriter(new FileWriter(fileName));
	}
	
	private BufferedReader openRead(String fileName)throws IOException
	{
		return new BufferedReader(new FileReader(fileName));
	}
}













