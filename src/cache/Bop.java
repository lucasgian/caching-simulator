package cache;



import java.io.*;

public class Bop
{
	Boolean op;
	String address = "";
	String data = "";
	int byteOfSet;
	int blockOfSet;
	int index;
	int tag;
	int assoc, word, numBlock;
	
	Bop(BufferedReader arq, int assoc, int word, int numBlock) throws IOException 
	{ 
		this.assoc = assoc;
		this.word = word + 2;
		this.numBlock = numBlock + word;
		String line = arq.readLine();
		while(line == null)
			line = arq.readLine();
		factoring(line); 
	}

	public void factoring(String line) throws IOException 
	{
		if(line.charAt(0) == '0')
			op = false;
		else 
			op = true;

		Boolean conf = false;
		for(int i=2, len = line.length(); i < len; i++)
		{
			if(line.charAt(i) != ' ' && !conf)
				address += line.charAt(i);
			else if(!conf)
				conf = true;
			else
				data +=  line.charAt(i);
			
			
		}
		
		convert(Integer.parseInt(address));
			
		
		byteOfSet();
		blockOfSet();
		index();
		tag();
		/*System.out.println(byteOfSet);
		System.out.println(blockOfSet);
		System.out.println(index);
		
		System.out.println(tag);
		System.exit(0);*/
	}


	private void convert(int address)
	{
		String binary = "";

		while(address > 0)
		{
			binary += address % 2;
			address = address >> 1; 
		}
		this.address = binary;
	}

	private int inverse(String binary)
	{
		int decimal = 0;
		
		for(int i = -1, exp=1, len = binary.length() - 1; i < len; len--, exp *= 2)
		{
			if(binary.charAt(len) == '1')
				decimal += exp; 
		}

		return decimal;
	}
	
	private void byteOfSet()
	{
		byteOfSet = inverse("" + address.charAt(0) + address.charAt(1));
	}
	
	private void blockOfSet()
	{
		String binary = "";
		
		if(word + 2 >= address.length())
			for(int i=2; i < word; i++)
				binary = "0" + binary;
		else
			for(int i=2; i < word; i++)
				binary = address.charAt(i) + binary;
		
		blockOfSet = inverse(binary);
	}
	
	private void index()
	{
		String binary = "";

		numBlock = numBlock > address.length() - 1 ? address.length() : numBlock;
		for(int i=word; i < numBlock; i++)
			binary = address.charAt(i) + binary;
		
		index = inverse(binary);
	}
	
	private void tag()
	{
		String binary = "";
		for(int i=numBlock, size=address.length(); i < size; i++)
			binary = address.charAt(i) + binary;
		
		tag = inverse(binary);
	}
	
	
}