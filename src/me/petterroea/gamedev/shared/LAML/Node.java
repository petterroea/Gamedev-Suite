package me.petterroea.gamedev.shared.LAML;

import java.util.LinkedList;

public class Node {
	LinkedList<Node> nodes;
	String name;
	String value;
	public Node(String contents)
	{
		if(!(contents.charAt(0)=='<'))
		{
			new InvalidNodeException("The node should start with '<'").printStackTrace();
		}
		String nameraw = "";
		boolean inString = false;
		int startEnd = 0;
		int endStart = 0;
		for(int i = 0; i < contents.length(); i++)
		{
			if(contents.charAt(i)=='>' && !inString)
			{
				startEnd = i+1;
				break;
			}
			else
			{
				nameraw = nameraw+contents.charAt(i);
			}
			if(contents.charAt(i)=='"')
			{
				if(inString)
				{
					inString = false;
				}
				else
				{
					inString = true;
				}
			}
		}
		this.name = nameraw;
		for(int i = 0; i < contents.length(); i++)
		{
			if(contents.substring(i)=="</"+name+">")
			{
				endStart = i;
			}
		}
		//Get inside nodes
		if(!(contents.charAt(startEnd)=='<'))
		{
			//This node contains data
			for(int i = startEnd; i < endStart; i++)
			{
				value = value + contents.charAt(i);
			}
		}
		else
		{
			//This node contains nodes
			String currName = "";
			boolean onNode = false;
			for(int i = startEnd; i < endStart; i++)
			{
				if(contents.charAt(i)=='<'&&!onNode)
				{
					onNode = true;
					String tempName = "";
					for(int a = i+1; a < endStart; a++)
					{
						if(contents.charAt(a)=='>')
						{
							break;
						}
						else
						{
							tempName = tempName + contents.charAt(a);
						}
					}
					currName = tempName;
					String data = "";
					for(int a = i; a < endStart; a++)
					{
						if(contents.substring(a, 3+currName.length())=="</"+currName+">")
						{
							//Get the end tag
							for(int p = a; p < 4+currName.length(); p++)
							{
								//TODO: Working here
							}
						}
						else
						{
							data = data + contents.charAt(a);
						}
					}
				}
			}
		}
	}
}
