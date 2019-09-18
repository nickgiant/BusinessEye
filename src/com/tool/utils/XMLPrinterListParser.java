// created 15-04-2007
// based on Big Java , Horstmann, ch24 p 958

package com.tool.utils;

import com.tool.model.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class XMLPrinterListParser
{	
	private DocumentBuilder builder;
	
	public XMLPrinterListParser() throws ParserConfigurationException
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		builder=factory.newDocumentBuilder();
	}
	
	public ArrayList parse(String fileName)throws SAXException , IOException
	{
		File f = new File(fileName);
		// get the <preferences> root element
		Document doc=builder.parse(f);
		Element root = doc.getDocumentElement();
		return getPrinters(root);
	}
	
	private static ArrayList getPrinters(Element e)
	{
		ArrayList printers = new ArrayList();
		
		NodeList children = e.getChildNodes();
		for(int i = 0; i<children.getLength();i++)
		{
			Node childNode=children.item(i);
			if(childNode instanceof Element)
			{
				Element childElement=(Element)childNode;
			   if (childElement.getTagName().equals("printer"))
			   {
				  Printer c = getPrinter(childElement);
			    	printers.add(c);
			   }
			}
		}
		return printers;
	}
		
	private static Printer getPrinter(Element e)
	{
		NodeList children = e.getChildNodes();
		int id=0;
		String name ="";
		String type="";
		String port="";
	    
	    //System.out.println("XMLPrinterListParser.getPrinter children "+ children.getLength());
	    for (int j=0;j<children.getLength();j++)
	    {
	    	Node childNode=children.item(j);
	    	
	    	
	    	if(childNode instanceof Element)
	    	{
	    		
	    		Element childElement=(Element)childNode;
	    		String tagName=childElement.getTagName();
	    		Text textNode=(Text)childElement.getFirstChild();
	    		
	    		String data=textNode.getData();
	    		if(tagName.equalsIgnoreCase("id"))
	    		{
	    			id =  Integer.parseInt(data);
	    		}
	    		else if(tagName.equalsIgnoreCase("name"))
	    		{
	    			name = data;
	    		}
	    		else if(tagName.equalsIgnoreCase("type"))
	    		{
	    			type = data;
	    		}
	    		else if(tagName.equalsIgnoreCase("port"))
	    		{
	    			port = data;
	    		}
	    	}
	    }
	    //System.out.println("XMLPrinterListParser.getPrinter name"+ name);
	    return new Printer(id,name,type,port);
	}

	
	
}


