package cache;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EndRead 
{
	BufferedReader arq;
	
	EndRead(String fileName) throws FileNotFoundException
	{
		arq = open(fileName);
	}
	private BufferedReader open(String fileName)throws FileNotFoundException
	{
		return new BufferedReader(new FileReader(fileName));
	}
	
	public String read() throws IOException
	{
		return arq.readLine();	
	}
}
