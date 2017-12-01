package cache;


import java.io.*;

public class ReadTrace
{
	private BufferedReader arq;

	ReadTrace() throws IOException {readTrace();}


	private BufferedReader open(String fileName) throws FileNotFoundException 
    {
        return new BufferedReader(new FileReader(fileName));
    }

	public void readTrace() throws FileNotFoundException
	{
		arq = open("trace.in");
		

	}	
	// Quantidade de Block
	public int number() throws IOException
	{
		return Integer.parseInt(arq.readLine());
	}

	// Quanatidade de words por Block
	public int size() throws IOException
	{
		return Integer.parseInt(arq.readLine());
	}

	// A associação da cache 
	public int assoc() throws IOException
	{
		return Integer.parseInt(arq.readLine());
	}
	
	// A associação da cache 
	public int lengthOp() throws IOException
	{
		return Integer.parseInt(arq.readLine());
	}	

	// Operações da cache
	public Bop op(int assoc, int word, int numBlock) throws IOException
	{
		return new Bop(arq, assoc, word, numBlock);
	}
	
	
	public void closeArq() throws IOException
	{
		arq.close();
	}

}





