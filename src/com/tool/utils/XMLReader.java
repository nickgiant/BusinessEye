// created 04-01-2010
// http://www.java-tips.org/java-se-tips/javax.xml.parsers/how-to-read-xml-file-in-java.html

package com.tool.utils;

import com.tool.model.*;

import java.io.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.lang.reflect.Constructor;

public class XMLReader
 {
 	 

 public static void main(String argv[])
 {
  /*try {
  File file = new File("C:\\mydocs\\!projects\\xml read\\laserfarmerapp.xml");
  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
  DocumentBuilder db = dbf.newDocumentBuilder();
  Document doc = db.parse(file);
  doc.getDocumentElement().normalize();
  System.out.println("Root element " + doc.getDocumentElement().getNodeName());
  NodeList nodeLst = doc.getElementsByTagName("page");
  System.out.println("Information of all employees");

  for (int s = 0; s < nodeLst.getLength(); s++) {

    Node fstNode = nodeLst.item(s);
    
    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
  
           Element fstElmnt = (Element) fstNode;
      NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("firstname");
      Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
      NodeList fstNm = fstNmElmnt.getChildNodes();
      System.out.print("First Name : "  + ((Node) fstNm.item(0)).getNodeValue());
      NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("lastname");
      Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
      NodeList lstNm = lstNmElmnt.getChildNodes();
      System.out.println("   Last Name : " + ((Node) lstNm.item(0)).getNodeValue());
    }

  }
  } catch (Exception e) {
    e.printStackTrace();
  }*/
  
  
  
  UtilsPanelReport utilsPanelReport = new UtilsPanelReport();

   XMLReader reader = new XMLReader();
  
  /*  System.out.println("PanelReportFormDesign.laserReadTemplateXMLFile     file:"+file);
   String[] tagElementsPage ={"name","caption","backgroundFile","height","width", "pageOrientation"};
   String[] tagElementsPageType ={"String","String","String","int","int", "int"};
   ArrayList listProperties = reader.parse(file,"page",tagElementsPage,tagElementsPageType, "com.tool.model.EntityReportForm");

   
   String[] tagElementsField ={"name","caption","pageTab","x","y","height","width","alignment","showBorder","showBackground","showBold","showItalic","fontsize"};   
   String[] tagElementsFieldType ={"String","String","int","int","int","int","int","int","boolean","boolean","boolean","boolean","int"};   
   ArrayList listComponents = reader.parse(file,"field",tagElementsField,tagElementsFieldType, "com.tool.model.EntityReportFormField");
   */
  
            String[] tagElements1 ={"name","showColumns0"};
          String[] tagElementsType1 ={"String", "String"};  
          	//System.out.println("PanelReportSettings 1- "+group+" "+tagElements1[0]+" "+tagElements1[1]+" "+reportdesign);
       String fieldsShowColumnsNew = reader.getValueFromXmlElement("D:\\mydocuments\\!projects\\DBTool\\prefsReport.xml", "Report1",tagElements1,tagElementsType1,1,"invok"); 
            System.out.println("XMLReader.main "+fieldsShowColumnsNew);
   
   
   
   
   /*String[] tagElements ={"name","sqlOrderBy"};
   String[] tagElementsType ={"String", "String"}; 
  
  
  String filePrefs =  "E:\\mydocuments\\!projects\\DBTool\\prefsReport.xml";
  
          String[] tagElements1 ={"reportEntity","showColumns0"};
          String[] tagElementsType1 ={"String", "String"};  
       int[]  fieldsShowColumnsNew = utilsPanelReport.loadDataFromXmlFileRetIntArray(filePrefs, "Report",tagElements1,tagElementsType1,1,"rptPayment"); 
   	
   	System.out.println("XmlReader "+fieldsShowColumnsNew.length);
   	*/
   	
   	
   	
   	
//   	reader.getValueFromXmlElement("E:\\mydocuments\\!projects\\DBTool\\tableprefs.xml","Table",tagElements,tagElementsType,1,"buyer");
   	
// reader.checkIfElementExists("E:\\mydocuments\\!projects\\DBTool\\tableprefs.xml", "Table",tagElements,tagElementsType,"buyer");   	
   	
   /*String[] tagElementsPage ={"name","caption","backgroundFile","height","width", "pageOrientation"};
   String[] tagElementsPageType ={"String","String","String","int","int", "int"};
   ArrayList listPage = reader.parse("C:\\mydocs\\!projects\\xml read\\laser.xml","page",tagElementsPage,tagElementsPageType, "model.EntityReportForm");
   System.out.println("XMLReader pages "+listPage.size());
   
   String[] tagElementsField ={"name","caption","page","x","y","height","width","alignment","showBorder","showBackground","showBold","showItalic","fontsize"};   
   String[] tagElementsFieldType ={"String","String","int","int","int","int","int","int","boolean","boolean","boolean","boolean","int"};   
   ArrayList listField = reader.parse("C:\\mydocs\\!projects\\xml read\\laser.xml","field",tagElementsField,tagElementsFieldType, "model.EntityReportFormField");
   System.out.println("XMLReader fields "+listField.size());*/
   
 }
 
 
 public int getElementLine(String xmlFile, String tagCategory, String[] tagElements, String[] tagElementsType, String elementValue)
 {
  int ret =-1;
   ArrayList listObjects= new ArrayList();
  try {
  File file = new File(xmlFile);
  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
  DocumentBuilder db = dbf.newDocumentBuilder();
  Document doc = db.parse(file);
  doc.getDocumentElement().normalize();
  //System.out.println("Root element " + doc.getDocumentElement().getNodeName());
  NodeList nodeLst = doc.getElementsByTagName(tagCategory);
  //System.out.println("Information of all employees");

  for (int s = 0; s < nodeLst.getLength(); s++) {

    Node fstNode = nodeLst.item(s);
    //System.out.println("");
    //System.out.println("XMLReader.parse "+s+" "+tagCategory);

       	      	    
    
    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
  
       Element fstElmnt = (Element) fstNode;
       Object[] obj = new Object[tagElements.length];    
      for(int t=0;t<tagElements.length;t++)
      {
        NodeList fstNmElmntLst = fstElmnt.getElementsByTagName(tagElements[t]);
        Element fstNmElmnt = (Element) fstNmElmntLst.item(t);
        NodeList fstNm = fstNmElmnt.getChildNodes();
        if(elementValue.equalsIgnoreCase((String) fstNm.item(0).getNodeValue()))
        {
           ret =s;	
        }
        
        //System.out.println(" - "+tagElements[t]+" ("+t+"):"  + ((Node) fstNm.item(t)).getNodeValue());      	      	
      	//obj[t]=	parseObjectFromString((String) fstNm.item(0).getNodeValue(),tagElementsType[t]);      	
      	//initializeObject(returnObject)      	
      	//listObjects.add(((Node) fstNm.item(0)).getNodeValue());      	
      }
             
             //listObjects.add(initializeObject(obj,tagElementsType,returnObject));

    }

  }
  }
  catch (Exception e)
  {
    System.out.println("XMLReader.getElementLine "+tagCategory+" "+elementValue+" "+e.getMessage());//+" "+ );
    //e.printStackTrace();
  } 	
 	return ret;
 }
 
 
 public String getValueFromXmlElement(String xmlFile,String tagCategory, String[] tagElements, String[] tagElementsType,int tagElement, String elementValue)
 {
     //System.out.println("XMLReader.getValueFromXmlElement         "+tagElements[0]+" "+tagElements[1]+" "+tagElement+" "+elementValue);
  String ret ="";
   ArrayList listObjects= new ArrayList();
  try {
  File file = new File(xmlFile);
  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
  DocumentBuilder db = dbf.newDocumentBuilder();
  Document doc = db.parse(file);
  doc.getDocumentElement().normalize();
  //System.out.println("Root element " + doc.getDocumentElement().getNodeName());
  NodeList nodeLst = doc.getElementsByTagName(tagCategory);
  //System.out.println("Information of all employees");

  for (int s = 0; s < nodeLst.getLength(); s++) {

    Node fstNode = nodeLst.item(s);
    //System.out.println("");
    //System.out.println("XMLReader.getValueFromXmlElement === ("+s+")  "+tagCategory+"  nodeLst.getLength:"+nodeLst.getLength());
    
    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
  
       Element fstElmnt = (Element) fstNode;
       Object[] obj = new Object[tagElements.length]; 
       boolean boolNode = false;
      for(int t=0;t<tagElements.length;t++)
      {
        NodeList fstNmElmntLst = fstElmnt.getElementsByTagName(tagElements[t]);
        // System.out.println("XMLReader.getValueFromXmlElement - "+tagElements[t]);
        Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
        //System.out.println("XMLReader.getValueFromXmlElement - ("+s+")("+t+") "+tagElements[t]+" - fstNmElmnt:"+fstNmElmnt);
        //  gets null exception when a tag is not exist

        if(fstNmElmnt!= null && fstNmElmnt.getChildNodes()!=null)
        {
        NodeList fstNm = fstNmElmnt.getChildNodes();
        
        
        String val = "";
      	if(fstNm!=null && fstNm.item(0)!=null && fstNm.item(0).getNodeValue()!=null)
        {
        	val=(String) fstNm.item(0).getNodeValue();
        }
        //System.out.println("XMLReader.getValueFromXmlElement - "+tagElements[t]+" ("+t+"):"  + ((Node) fstNm.item(0)).getNodeValue());      	      	
      	if(elementValue.equalsIgnoreCase(val) )
      	{
      		
      	
         NodeList lstNmElmntLst = fstElmnt.getElementsByTagName(tagElements[tagElement]);
         Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
         NodeList lstNm = lstNmElmnt.getChildNodes();      	
      	    
      	    //System.out.println("XMLReader.getValueFromXmlElement - "+tagElements[t]+" ("+t+"):"  + ((Node) fstNm.item(0)).getNodeValue());      	      	
      	    if(lstNm!=null && lstNm.item(0)!=null && lstNm.item(0).getNodeValue()!=null)
      	    {
      	      ret = (String) lstNm.item(0).getNodeValue();	
      	    }
      	    
      	    //System.out.println("XMLReader.getValueFromXmlElement "+elementValue+" === "+(String) lstNm.item(0).getNodeValue()+" "+ret);

      	}      	
        else
        {
            //System.out.println("XMLReader.getValueFromXmlElement else -- "+tagElements[t]+" ("+t+"):"  + ((Node) fstNm.item(0)).getNodeValue());      	      	
        }
      	//obj[t]=	parseObjectFromString((String) fstNm.item(0).getNodeValue(),tagElementsType[t]);      	
      	//initializeObject(returnObject)      	
      	//listObjects.add(((Node) fstNm.item(0)).getNodeValue());      	
      }
      }      
             //listObjects.add(initializeObject(obj,tagElementsType,returnObject));

    }

  }
  }
  catch (Exception e)
  {
    System.out.println("error XMLReader.getValueFromXmlElement "+tagCategory+" "+e.getMessage() );
    if(VariablesGlobal.globalShowPrintStackTrace)
    {
        e.printStackTrace();    
    }
    
  } 	
 	return ret;
 }
 
 
 public boolean checkIfElementExists(String xmlFile,String tagCategory, String[] tagElements, String[] tagElementsType, String elementValue)
 {
  boolean ret =false;
   ArrayList listObjects= new ArrayList();
  try {
  File file = new File(xmlFile);
  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
  DocumentBuilder db = dbf.newDocumentBuilder();
  Document doc = db.parse(file);
  doc.getDocumentElement().normalize();
  //System.out.println("Root element " + doc.getDocumentElement().getNodeName());
  NodeList nodeLst = doc.getElementsByTagName(tagCategory);
  //System.out.println("Information of all employees");

  for (int s = 0; s < nodeLst.getLength(); s++) {

    Node fstNode = nodeLst.item(s);
    //System.out.println("");
    //System.out.println("XMLReader.parse "+s+" "+tagCategory);
    
    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
  
       Element fstElmnt = (Element) fstNode;
       Object[] obj = new Object[tagElements.length];    
      for(int t=0;t<tagElements.length;t++)
      {
        NodeList fstNmElmntLst = fstElmnt.getElementsByTagName(tagElements[t]);
        Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
        NodeList fstNm = fstNmElmnt.getChildNodes();
      
        //System.out.print(" - "+tagElements[t]+" ("+t+"):"  + ((Node) fstNm.item(0)).getNodeValue());      	      	
      	//obj[t]=	parseObjectFromString((String) fstNm.item(0).getNodeValue(),tagElementsType[t]);      	
      	//initializeObject(returnObject)      	
      	//listObjects.add(((Node) fstNm.item(0)).getNodeValue());      	
        if(elementValue.equalsIgnoreCase((String) fstNm.item(0).getNodeValue()))
        {
           ret =true;	
        } 
        
        }
             
          //   listObjects.add(initializeObject(obj,tagElementsType,returnObject));

    }

  }
  }
  catch (Exception e)
  {
    System.out.println("error XMLReader.checkIfElementExists "+tagCategory+" "+elementValue+" "+e.getMessage());
    //e.printStackTrace();
  } 	
 	return ret;
 }


  public ArrayList parseToArraylistWithObjectsOLD(String xmlFile, String tagCategory, String[] tagElements,String[] tagElementsType, String returnClass)
 {
   System.out.println("XMLReader.parseToArraylistWithObjects       xmlFile:"+xmlFile+"        tagCategory:"+tagCategory+"          returnClass:"+returnClass);
   ArrayList listObjects= new ArrayList();
  

   
  try
  {
 
  File file = new File(xmlFile);
  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
  DocumentBuilder docbuild = dbf.newDocumentBuilder();
  Document doc = docbuild.parse(file);
  doc.getDocumentElement().normalize();
  //System.out.println("XMLReader.parse   xmlFile:"+xmlFile+"   tagCategory:"+tagCategory+"    root element " + doc.getDocumentElement().getNodeName());
  NodeList nodeLst = doc.getElementsByTagName(tagCategory);
  //System.out.println("XMLReader.parse   xmlFile:"+xmlFile+"   tagCategory:"+tagCategory+"     nodeLst.getLength():"+nodeLst.getLength());

  for (int s = 0; s < nodeLst.getLength(); s++)
  {

    Node fstNode = nodeLst.item(s);
    //System.out.println("");
    //System.out.println("XMLReader.parse "+s+" "+tagCategory);
    
    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
    {
  
       Element fstElmnt = (Element) fstNode;
       Object[] obj = new Object[tagElements.length];    
      for(int t=0;t<tagElements.length;t++)
      {
        NodeList fstNmElmntLst = fstElmnt.getElementsByTagName(tagElements[t]);
        Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
        //System.out.println("XMLReader.parseToArraylistWithObjects  tagCategory:"+tagCategory+"   t:"+t+"    s:"+s+"   fstNmElmnt:"+fstNmElmnt+"    tagElements[t]:"+tagElements[t]);
        NodeList fstNm = fstNmElmnt.getChildNodes();
      
        //System.out.print("XMLReader.parseToArraylistWithObjects - "+tagElements[t]+" ("+t+"):"  + ((Node) fstNm.item(0)).getNodeValue());      	      	
      	obj[t]=	parseObjectFromString((String) fstNm.item(0).getNodeValue(),tagElementsType[t]);      	
      	//initializeObject(returnObject)      	
      	//listObjects.add(((Node) fstNm.item(0)).getNodeValue());      	
      }
             
             listObjects.add(initializeObjectFromClassOLD(obj,tagElementsType,returnClass));

    }

  }
  }  //try
  catch (Exception  e )
  {
       System.out.println("error XMLReader.parseToArraylistWithObjects  tagCategory:"+tagCategory+"     listObjects:"+listObjects+"     (tagGategory must not exist in other tag in file)  e.getMessage():"+e.getMessage());
            if(VariablesGlobal.globalShowPrintStackTrace)	
            {
                e.printStackTrace();
            }
  } // try

  
  
  
 	return listObjects;
 }
 
 
 
 
/*  public ArrayList parseToArraylistWithObjectsOfReportFormField(String xmlFile, String tagCategory, String[] tagElements,String[] tagElementsType)
 {
   System.out.println("XMLReader.parseToArraylistWithObjectsOfReportFormField       xmlFile:"+xmlFile+"        tagCategory:"+tagCategory);
   ArrayList listObjects= new ArrayList();
  

   
  try
  {
 
  File file = new File(xmlFile);
  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
  DocumentBuilder docbuild = dbf.newDocumentBuilder();
  Document doc = docbuild.parse(file);
  doc.getDocumentElement().normalize();
  //System.out.println("XMLReader.parse   xmlFile:"+xmlFile+"   tagCategory:"+tagCategory+"    root element " + doc.getDocumentElement().getNodeName());
  NodeList nodeLst = doc.getElementsByTagName(tagCategory);
  //System.out.println("XMLReader.parse   xmlFile:"+xmlFile+"   tagCategory:"+tagCategory+"     nodeLst.getLength():"+nodeLst.getLength());

  for (int s = 0; s < nodeLst.getLength(); s++)
  {

    Node fstNode = nodeLst.item(s);
    //System.out.println("");
    //System.out.println("XMLReader.parse "+s+" "+tagCategory);
    
    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
    {
  
       Element fstElmnt = (Element) fstNode;
       Object[] obj = new Object[tagElements.length];    
      for(int t=0;t<tagElements.length;t++)
      {
        NodeList fstNmElmntLst = fstElmnt.getElementsByTagName(tagElements[t]);
        Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
        //System.out.println("XMLReader.parseToArraylistWithObjects  tagCategory:"+tagCategory+"   t:"+t+"    s:"+s+"   fstNmElmnt:"+fstNmElmnt+"    tagElements[t]:"+tagElements[t]);
        NodeList fstNm = fstNmElmnt.getChildNodes();
      
        //System.out.print("XMLReader.parseToArraylistWithObjects - "+tagElements[t]+" ("+t+"):"  + ((Node) fstNm.item(0)).getNodeValue());      	      	
      	obj[t]=	parseObjectFromString((String) fstNm.item(0).getNodeValue(),tagElementsType[t]);      	
      	//initializeObject(returnObject)      	
      	//listObjects.add(((Node) fstNm.item(0)).getNodeValue());      	
      
        
      }
      
      
          //EntityReportFormField entityReportFormField = new EntityReportFormField(tableNameIn,  nameIn ,alignmentIn, widthIn);
          //    public EntityReportFormField(String tableNameIn, String nameIn ,int alignmentIn,int widthIn)
      
      listObjects.add(initializeObjectFromClassReportFormField(obj,tagElementsType));

             //listObjects.add(initializeObjectFromClassReportFormField(obj,tagElementsType));

    }

  }
  }  //try
  catch (Exception  e )
  {
       System.out.println("error XMLReader.parseToArraylistWithObjectsOfReportFormField  tagCategory:"+tagCategory+"     listObjects:"+listObjects+"     (tagGategory must not exist in other tag in file)  e.getMessage():"+e.getMessage());
            if(VariablesGlobal.globalShowPrintStackTrace)	
            {
                e.printStackTrace();
            }
  } // try

  
  
  
 	return listObjects;
 }
 */
  

  /*public ArrayList parseToArraylistWithObjectsOfReportFormField(String xmlFile)
  {
      ArrayList listObjects= new ArrayList();
      
      return listObjects;
  }*/
  
  
  public ArrayList parseToArraylistWithObjectsOfReportFormField(String xmlFile, String tagCategory,String[] tagElements, String[] tagElementsType)
 {
     
     ArrayList listObjects= new ArrayList();
     
  try
  {
 
 // File file = new File(xmlFile);
  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
  DocumentBuilder docbuild = dbf.newDocumentBuilder();
  System.out.println("XMLReader.parseToArraylistWithObjectsOfReportFormField   xmlFile:"+xmlFile+"   tagCategory:"+tagCategory+"  tagElements:"+tagElements.length);
  Document doc = docbuild.parse(xmlFile);
  doc.getDocumentElement().normalize();
  //System.out.println("XMLReader.parse   xmlFile:"+xmlFile+"   tagCategory:"+tagCategory+"    root element " + doc.getDocumentElement().getNodeName());
  NodeList nodeLst = doc.getElementsByTagName(tagCategory);
  //System.out.println("XMLReader.parse   xmlFile:"+xmlFile+"   tagCategory:"+tagCategory+"     nodeLst.getLength():"+nodeLst.getLength());

  for (int s = 0; s < nodeLst.getLength(); s++)
  {

    Node fstNode = nodeLst.item(s);
    //System.out.println("");
    //System.out.println("XMLReader.parse "+s+" "+tagCategory);
    
    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
    {
  
        Element fstElmnt = (Element) fstNode;
       Object[] obj = new Object[tagElements.length];    
      for(int t=0;t<tagElements.length;t++)
      {
        NodeList fstNmElmntLst = fstElmnt.getElementsByTagName(tagElements[t]);
        Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
        //System.out.println("XMLReader.parseToArraylistWithObjects  tagCategory:"+tagCategory+"   t:"+t+"    s:"+s+"   fstNmElmnt:"+fstNmElmnt+"    tagElements[t]:"+tagElements[t]);
        NodeList fstNm = fstNmElmnt.getChildNodes();
      
        //System.out.print("XMLReader.parseToArraylistWithObjects - "+tagElements[t]+" ("+t+"):"  + ((Node) fstNm.item(0)).getNodeValue());      	      	
      	obj[t]=	parseObjectFromString((String) fstNm.item(0).getNodeValue(),tagElementsType[t]);      	
      	//initializeObject(returnObject)      	
      	//listObjects.add(((Node) fstNm.item(0)).getNodeValue());      	
      }
      
   EntityReportFormField entityReportFormField = null;

               entityReportFormField = new EntityReportFormField(obj[0].toString(),obj[1].toString(),Integer.parseInt(obj[2].toString()),Integer.parseInt(obj[3].toString()));
   
            listObjects.add(entityReportFormField);

    }
  }
   }  //try
  catch (IOException ioe)
  {
      System.out.println("error XMLReader.parseToArraylistWithObjectsOfReportFormField ioe.getMessage():"+ioe.getMessage());
            if(VariablesGlobal.globalShowPrintStackTrace)	
            {
                ioe.printStackTrace();
            }
  }  
  catch (Exception  e )
  {
       System.out.println("error XMLReader.parseToArraylistWithObjectsOfReportFormField  tagCategory:"+tagCategory+"     listObjects:"+listObjects+"     (tagGategory must not exist in other tag in file)  e.getMessage():"+e.getMessage());
            if(VariablesGlobal.globalShowPrintStackTrace)	
            {
                e.printStackTrace();
            }
  } // try
  
  
  
  return listObjects;
 

   
 }  
  
  
  
  public ArrayList parseToArraylistWithObjectsOfReportForm(String xmlFile, String tagCategory,String[] tagElements, String[] tagElementsType)
 {
     
     ArrayList listObjects= new ArrayList();
     
  try
  {
 
 // File file = new File(xmlFile);
  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
  DocumentBuilder docbuild = dbf.newDocumentBuilder();
  System.out.println("XMLReader.parseToArraylistWithObjectsOfReportForm   xmlFile:"+xmlFile+"   tagCategory:"+tagCategory+"  tagElements:"+tagElements.length);
  Document doc = docbuild.parse(xmlFile);
  doc.getDocumentElement().normalize();
  //System.out.println("XMLReader.parseToArraylistWithObjectsOfReportForm   xmlFile:"+xmlFile+"   tagCategory:"+tagCategory+"    root element " + doc.getDocumentElement().getNodeName());
  NodeList nodeLst = doc.getElementsByTagName(tagCategory);
  //System.out.println("XMLReader.parseToArraylistWithObjectsOfReportForm   xmlFile:"+xmlFile+"   tagCategory:"+tagCategory+"     nodeLst.getLength():"+nodeLst.getLength());

  for (int s = 0; s < nodeLst.getLength(); s++)
  {

    Node fstNode = nodeLst.item(s);
    //System.out.println("");
    //System.out.println("XMLReader.parse "+s+" "+tagCategory);
    
    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
    {
  
        Element fstElmnt = (Element) fstNode;
       Object[] obj = new Object[tagElements.length];    
      for(int t=0;t<tagElements.length;t++)
      {
        NodeList fstNmElmntLst = fstElmnt.getElementsByTagName(tagElements[t]);
        Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
        //System.out.println("XMLReader.parseToArraylistWithObjects  tagCategory:"+tagCategory+"   t:"+t+"    s:"+s+"   fstNmElmnt:"+fstNmElmnt+"    tagElements[t]:"+tagElements[t]);
        NodeList fstNm = fstNmElmnt.getChildNodes();
      
        //System.out.print("XMLReader.parseToArraylistWithObjects - "+tagElements[t]+" ("+t+"):"  + ((Node) fstNm.item(0)).getNodeValue());      	      	
      	obj[t]=	parseObjectFromString((String) fstNm.item(0).getNodeValue(),tagElementsType[t]);      	
      	//initializeObject(returnObject)      	
      	//listObjects.add(((Node) fstNm.item(0)).getNodeValue());      	
      }
      System.out.println("XMLReader.parseToArraylistWithObjectsOfReportForm   xmlFile:"+xmlFile+"   tagCategory:"+tagCategory+"    s:"+s+"      nodeLst.getLength():"+nodeLst.getLength());
   EntityReportForm entityReportForm = null;

               entityReportForm = new EntityReportForm(obj[0].toString(),obj[1].toString(),obj[2].toString(),Integer.parseInt(obj[3].toString()),Integer.parseInt(obj[4].toString()),Integer.parseInt(obj[5].toString()));
   
            listObjects.add(entityReportForm);

    }
  }
   }  //try
  catch (IOException ioe)
  {
      System.out.println("error XMLReader.parseToArraylistWithObjectsOfReportForm ioe.getMessage():"+ioe.getMessage());
            if(VariablesGlobal.globalShowPrintStackTrace)	
            {
                ioe.printStackTrace();
            }    
  }
  catch (Exception  e )
  {
       System.out.println("error XMLReader.parseToArraylistWithObjectsOfReportForm  tagCategory:"+tagCategory+"     listObjects:"+listObjects+"     (tagGategory must not exist in other tag in file)  e.getMessage():"+e.getMessage());
            if(VariablesGlobal.globalShowPrintStackTrace)	
            {
                e.printStackTrace();
            }
  } // try
  
  
  
  return listObjects;
 

   
 }    
  
  
 
  
 
  private Object initializeObjectFromClassOLD(Object[] obj, String[] strObjTypes, String returnClass)
 {
   //  http://www.rgagnon.com/javadetails/java-0351.html
   // http://onjava.com/pub/a/onjava/2007/03/15/reflections-on-java-reflection.html?page=1
     
     // alternative of reflection in order to creating a java object from xml file
     
  	
    // System.out.println("XMLReader.initializeObject length "+obj.length);

      /*Class[] classParm = {String.class, String.class, String.class, Integer.TYPE, 
      	Integer.TYPE, Integer.TYPE};*/
            //String.class, 
            //String.class, 
            //Integer.TYPE };

      Class[] classParm = new Class[strObjTypes.length];
      for (int t = 0;t<strObjTypes.length;t++)
      {
      	 classParm[t] = returnObjectTypeClassFromString(strObjTypes[t]);
      	 //System.out.print(" "+classParm[t]);
      }

   try
   {
       
 	  
       Class cl = null;
       Constructor co= null;
       if(returnClass.toString().equalsIgnoreCase("com.tool.model.EntityReportFormField"))
       {
           EntityReportFormField entityReportFormField = new EntityReportFormField();
               cl = entityReportFormField.getClass();    
               
               
       }//Class.forName(returnObject);
       /*else if(returnClass.toString().equalsIgnoreCase("com.tool.model.EntityReportForm"))
       {
           EntityReportForm entityReportForm = new EntityReportForm();
               cl = entityReportForm.getClass(); 
               
       }*/
       else
       {
           
           System.out.println("XMLReader.initializeObjectFromClass   ELSE    UNKNOWN: returnClass:"+returnClass); 
       }
 	
    //   System.out.println("XMLReader.initializeObjectFromClass    -    returnClass:"+returnClass+"          cl:"+cl);
       
       co = cl.getConstructor(classParm);
 	  //Constructor cnst[] = cl.getConstructors();
 	  /*for (int c =0;c<cnst.length;c++)
 	  {
 	  	System.out.println("XMLReader.initializeObject constractors "+c+" "+returnObject+" "+cnst[c].toString());
 	  }*/
 	  
       
      //System.out.println("XMLReader.initializeObjectFromClass "+returnObject+" "+co.newInstance(obj).toString());
      return  co.newInstance(obj);
   }
   catch (Exception e)
   {
      System.out.println("error XMLReader.initializeObjectFromClass  returnClass:"+returnClass+" - message:"+e.getMessage());
      e.printStackTrace();
      return null;
   }
   
   
 }
 
  
  
  
  
/*
  * not in use
  */
 public ArrayList parse(String xmlFile, String tagCategory, String[] tagElements,String[] tagElementsType, String returnObject)
 {
   System.out.println("XMLReader.parse           tagCategory:"+tagCategory+"          returnObject:"+returnObject);
   ArrayList listObjects= new ArrayList();
  try
  {
  File file = new File(xmlFile);
  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
  DocumentBuilder docbuild = dbf.newDocumentBuilder();
  Document doc = docbuild.parse(file);
  doc.getDocumentElement().normalize();
  //System.out.println("XMLReader.parse   xmlFile:"+xmlFile+"   tagCategory:"+tagCategory+"    root element " + doc.getDocumentElement().getNodeName());
  NodeList nodeLst = doc.getElementsByTagName(tagCategory);
  //System.out.println("XMLReader.parse   xmlFile:"+xmlFile+"   tagCategory:"+tagCategory+"     nodeLst.getLength():"+nodeLst.getLength());

  for (int s = 0; s < nodeLst.getLength(); s++)
  {

    Node fstNode = nodeLst.item(s);
    //System.out.println("");
    //System.out.println("XMLReader.parse "+s+" "+tagCategory);
    
    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
    {
  
       Element fstElmnt = (Element) fstNode;
       Object[] obj = new Object[tagElements.length];    
      for(int t=0;t<tagElements.length;t++)
      {
        NodeList fstNmElmntLst = fstElmnt.getElementsByTagName(tagElements[t]);
        Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
        //System.out.println("XMLReader.parse  tagCategory:"+tagCategory+"   t:"+t+"    s:"+s+"   fstNmElmnt:"+fstNmElmnt+"    tagElements[t]:"+tagElements[t]);
        NodeList fstNm = fstNmElmnt.getChildNodes();
      
        //System.out.print("XMLReader.parse - "+tagElements[t]+" ("+t+"):"  + ((Node) fstNm.item(0)).getNodeValue());      	      	
      	obj[t]=	parseObjectFromString((String) fstNm.item(0).getNodeValue(),tagElementsType[t]);      	
      	//initializeObject(returnObject)      	
      	//listObjects.add(((Node) fstNm.item(0)).getNodeValue());      	
      }
             
             listObjects.add(initializeObject(obj,tagElementsType,returnObject));

    }

  }
  }//try
  catch (Exception e)
  {
       System.out.println("error XMLReader.parse  tagCategory:"+tagCategory+" (tagGategory must not exist in other tag in file)  e.getMessage():"+e.getMessage());
            if(VariablesGlobal.globalShowPrintStackTrace)	
            {
                e.printStackTrace();
            }
  } 	
 	return listObjects;
 }
 
  /*
 public ArrayList getListForEntity(String xmlFile, String tagCategory, String[] tagElements,String[] tagElementsType, int tagElement, String entityValue)
 {

   ArrayList listObjects= new ArrayList();
  try {
  File file = new File(xmlFile);
  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
  DocumentBuilder db = dbf.newDocumentBuilder();
  Document doc = db.parse(file);
  doc.getDocumentElement().normalize();
  //System.out.println("Root element " + doc.getDocumentElement().getNodeName());
  NodeList nodeLst = doc.getElementsByTagName(tagCategory);
  //System.out.println("Information of all employees");

  for (int s = 0; s < nodeLst.getLength(); s++) {

    Node fstNode = nodeLst.item(s);
    //System.out.println("");
    //System.out.println("XMLReader.parse "+s+" "+tagCategory);
    
    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
  
       Element fstElmnt = (Element) fstNode;
       Object[] obj = new Object[tagElements.length];    
     
      for(int t=0;t<tagElements.length;t++)
      {
        NodeList fstNmElmntLst = fstElmnt.getElementsByTagName(tagElements[t]);
        Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
        NodeList fstNm = fstNmElmnt.getChildNodes();
      
      NodeList lstNmElmntLst = fstElmnt.getElementsByTagName(tagElements[tagElement]);
      Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
      NodeList lstNm = lstNmElmnt.getChildNodes();
      //System.out.println("Last Name : " + ((Node) lstNm.item(0)).getNodeValue());
      
      String element = ((String) fstNm.item(0).getNodeValue());
        //System.out.println("XMLReader.getListForEntity - "+tagElements[t]+" ("+t+"): first "  + ((Node) fstNm.item(0)).getNodeValue()+" last "+((Node) lstNm.item(0)).getNodeValue()+" "+entityValue+" = "+element);      	      	
      	//obj[t]=	parseObjectFromString((String) fstNm.item(0).getNodeValue(),tagElementsType[t]);      	
      //System.out.println("XMLReader.getListForEntity - "+tagElements[t]+" ("+t+"):  "+entityValue+" = "+element);      	      	
      if(element.equalsIgnoreCase(entityValue))
      {
         listObjects.add(lstNm.item(0).getNodeValue());	
      }
      //obj[t] = lstNm.item(0).getNodeValue();
      
      	//initializeObject(returnObject)      	
      	//listObjects.add(((Node) fstNm.item(0)).getNodeValue());      	
      }
             
            // listObjects.add(obj);

    }

  }
  }
  catch (Exception e)
  {
    System.out.println("error XMLReader.getListForEntity "+tagCategory+" "+e.getMessage());//e.printStackTrace();
    //e.printStackTrace();
  } 	
 	return listObjects;
 }*/
 
  



 
 
 private Object initializeObject(Object[] obj, String[] strObjTypes, String returnObject)
 {
   //  http://www.rgagnon.com/javadetails/java-0351.html
   // http://onjava.com/pub/a/onjava/2007/03/15/reflections-on-java-reflection.html?page=1
     
     // alternative of reflection in order to creating a java object from xml file
     
  	
    // System.out.println("XMLReader.initializeObject length "+obj.length);

      /*Class[] classParm = {String.class, String.class, String.class, Integer.TYPE, 
      	Integer.TYPE, Integer.TYPE};*/
            //String.class, 
            //String.class, 
            //Integer.TYPE };

      Class[] classParm = new Class[strObjTypes.length];
      for (int t = 0;t<strObjTypes.length;t++)
      {
      	 classParm[t] = returnObjectTypeClassFromString(strObjTypes[t]);
      	 //System.out.print(" "+classParm[t]);
      }

   try
   {
       System.out.println("XMLReader.initializeObject    returnObject:"+returnObject);
 	  Class cl = Class.forName(returnObject);
 	  
 	  //Constructor cnst[] = cl.getConstructors();
 	  /*for (int c =0;c<cnst.length;c++)
 	  {
 	  	System.out.println("XMLReader.initializeObject constractors "+c+" "+returnObject+" "+cnst[c].toString());
 	  }*/
 	  
      Constructor co = cl.getConstructor(classParm);
      //System.out.println("XMLReader.initializeObject "+returnObject+" "+co.newInstance(obj).toString());
      return  co.newInstance(obj);
   }
   catch (Exception e)
   {
      System.out.println("error XMLReader.initializeObject  returnObject:"+returnObject+" - message:"+e.getMessage());
      return null;
   }
   
   
 }
 
 private Class returnObjectTypeClassFromString(String strClass)
 {
 	Class ret = null;
 
     try
     {
  if(strClass.equalsIgnoreCase("Integer") || strClass.equalsIgnoreCase("int"))
  {
          Class cl = Integer.TYPE;
    ret =  cl;  	
  }
  else if(strClass.equals("Double") || strClass.equals("double"))
  {
          Class cl = Double.TYPE;
    ret =  cl; 
  }
  else if(strClass.equals("String"))
  {
          Class cl = String.class;
  	
    ret =  cl;
  }
  else if(strClass.equalsIgnoreCase("Boolean") || strClass.equalsIgnoreCase("boolean"))
  {
          Class cl = Boolean.TYPE;
    ret =  cl; 
  }
  else
  {
  	  System.out.println("error XMLReader.returnObjectTypeClassFromStrng "+strClass+" unsupported");
  }

     }
     catch (Exception e)
     {
            System.out.println("error XMLReader.returnObjectTypeClassFromStrng "+strClass+" - "+e.getMessage());
            ret = null;
     }
	
 	
 	return ret;
 	
 }
 

 private Object parseObjectFromString(String str, String strClass ) throws Exception
 {
  //String className = class.getSimpleName();
  Object ret = null;
  if(strClass.equalsIgnoreCase("Integer") || strClass.equalsIgnoreCase("int"))
  {
    ret =  Integer.parseInt(str);
  }
  else if(strClass.equals("Double") || strClass.equals("double"))
  {
    ret =  Double.parseDouble(str);
  }
  else if(strClass.equals("String"))
  {
    ret =  str;
  }
  else if(strClass.equalsIgnoreCase("Boolean") || strClass.equalsIgnoreCase("boolean"))
  {
    ret =  Boolean.parseBoolean(str);
  }
   
    return ret;
 }
 
 
}