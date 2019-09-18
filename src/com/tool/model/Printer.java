//created 15-04-2007

package com.tool.model;

public class Printer
{
	int id;
	String name;
	String type;
	String port;
	
	public Printer(int idIn,String nameIn,String typeIn,String portIn)
	{
		id=idIn;
		name=nameIn;
		type=typeIn;
		port = portIn;
		
	}

	public int getId()
	{
		return id;
	}

	
	public String getName()
	{
		return name;
	}
	
     public String getType()
	{
		return type;
	}

	public String getPort()
	{
		return port;
	}

	
}