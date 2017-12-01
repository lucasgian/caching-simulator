package cache;


import java.io.*;
import java.util.Random;

public class Cache 
{ 
	ReadTrace trace = new ReadTrace();
	int assoc, div, word, numBlock, size;
	Node joint;
	PrintWriter g;
	int hit = 0, miss = 0, access = 0;
	Queue fifo = new Queue();

	Cache() throws IOException
	{ 
		g = writer("list.data");
		create();

		size = trace.lengthOp() - 1;
		

		for(int i=0; i < size; i++)
		{
			Bop arq = trace.op(log(assoc), log(word), log(numBlock / assoc));
			
			if(arq.op)
				writer(joint, arq);
			else
				read(joint, arq);
		}
		

		System.out.println("MISS -> " + miss);
		System.out.println("HIT -> " + hit);
		System.out.println("Taxa de Hit -> " + (((double)hit) / ((size + 1) / 100)) + "%");
		

		
		
		try (PrintWriter f = open("value.data"))
	    {
			f.println(hit);
			f.println(miss);
			f.println(access);
	    }
	    catch (IOException e)
	    {
	      System.out.println(e.getMessage());
	    }
		
	}

	private void create() throws IOException
	{
		numBlock = trace.number();
		word = trace.size();
		assoc = trace.assoc();
		div = numBlock / assoc;
		initBlock(div);
		
	}

	private void initBlock(int length)
	{
		joint = new Node(length);
		Node node = joint;
		for(int i=1; i < assoc; i++, node = node.nextNode)
			node.nextNode = new Node(length);
	}

	private void writer(Node node, Bop arq)
	{
		int index = arq.index, tag = arq.tag;

	
		node = nodeTag(node, index, tag);
		
		
		if(node.empty(index))
		{
			g.println(arq.data + " WRITER");
			node.block[index] = new Block(arq, random(assoc), word);
			access++;
			miss++;
		}
		
		else if(node.block[index].tag(tag))
		{
			g.println(arq.data + " WRITER");
			node.block[index] = new Block(arq, random(assoc), word);
			access++;
			miss++;
		}
			
		else if(node.block[index].valid)
		{	
			g.println(arq.data + " WRITER");
			access++;
			hit++;
		}
		else
		{
			g.println(arq.data + " WRITER");
			node.block[index] = new Block(arq, arq.blockOfSet, word);
			access++;
			miss++;
		}
	}
	private void read(Node node, Bop arq)
	{
		int index = arq.index, tag = arq.tag;
		
		node = nodeTag(node, index, tag);
		
		
		if(node.empty(index))
		{
			fifo.insert(arq.address);
			g.println("<MISS> Acesso a memoria READ");
			node.block[index] = new Block(arq, random(assoc), word);
			miss++; access++;
		}
		else if(node.block[index].tag(tag))
		{
			fifo.insert(arq.address);
			g.println("<MISS> Acesso a memoria READ");
			node.block[index] = new Block(arq, random(assoc), word);
			miss++; access++;
		}
		else if(node.block[index].valid)
		{
			System.out.println(arq.blockOfSet %  node.block[index].word.length);
			g.println("<HIT> " + node.block[index].word[arq.blockOfSet % node.block[index].word.length] + " READ");
			hit++;
		}
		else
		{
			g.println("<MISS> Acesso a memoria READ");
			node.block[index] = new Block(arq, arq.blockOfSet, word);
			miss++; access++;
		}
	}
	
	private Node nodeTag(Node node, int index, int tag)
	{
		for(int i=0; i < assoc; i++, node = node.nextNode)
		{
			if(!node.empty(index))
				if(node.block[index].tag(tag))
					break;
		}
		return node == null ? joint : node;
	}
	
	
	private int random(int limit)
	{
		if(limit == 0)
			return 0;
		Random rd = new Random();
		return rd.nextInt(limit);
	}
	
	private int log(int x)
	{
		int r=1;
		
		while(x > 1)
		{
			r++;
			x = x >> 1;
		}
		
		return r;
	}
	
	
	private PrintWriter writer(String fileName)throws IOException
	{
		return new PrintWriter(new FileWriter(fileName));
	}
	

	private PrintWriter open(String fileName)
	throws IOException
	{
		return new PrintWriter(new FileWriter(fileName));
	}

}



class Block
{
	Boolean valid = false;
	String word[];
	int tag[];

	Block(Bop arq, int blockOfSet, int size)
	{
		this.valid = true;
		this.word = new String[size + 1];
		this.tag = new int[size + 1];
		for(int i=0; i <= size; i++)
		{
			word[i] = i == blockOfSet ? arq.data : "Acesso a memoria";
			tag[i] = arq.tag;
		}
	}
	
	public Boolean tag(int compTag)
	{
		Boolean equals = false;
		if(valid)
			for(int i=0, size=tag.length; i < size; i++)
				if(tag[i] == compTag)
				{
					equals = true;
					break;
				}
		
		return equals;
	}
	
	public Boolean empty(int index)
	{
		return word[index] == null ? true : false;
	}
}



class Node
{
	Node nextNode;
	Block[] block;

	Node(int length){block = new Block[length];}
	
	public Boolean empty(int index)
	{
		if(block == null || block[index] == null)
			return true;
		
		return false;
	}
}


class Queue
{
	String[] array;
	int index = 0, first = 0;
	
	Queue()
	{
		array = new String[64];
	}
	
	public void insert(String value)
	{
		if(index == array.length)
			dinamic();
		array[index] = value;
		index++;
	}
	
	public void remove()
	{
		if(!empty())
			first++;
		
		System.out.println("<null>");
	}
	
	public String first()
	{
		return array[first];
	}
	
	private boolean empty()
	{
		return index == first ? true : false;
	}
	
	private void dinamic()
	{
		String[] aux = new String[array.length << 1];
		
		for(int i=0, size=array.length; i < size; i++)
			aux[i] = array[i];
		
		array = aux;
	}
}