// created 25-09-2010
package com.tool.utils;

import com.tool.guicomps.*;

import java.util.*;




import java.io.*;


 public class UtilsFileSystem implements Constants
 {
  	
 	
  	
    public UtilsFileSystem()
    {
          
    }

    
    
  public String readFileAndGet(String fromFile)  // xml
  {	
      String retString = "";
BufferedReader br = null;
		FileReader fr = null;
		try
                {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(fromFile);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null)
                        {
				//System.out.println(sCurrentLine);
                                retString = retString+sCurrentLine;
			}

		}
                catch (java.io.FileNotFoundException fnf)
                {
                    System.out.println("UtilsFileSystem.readFileAndGet   FileNotFoundException   fromFile:"+fromFile);
                }
                catch (IOException e)
                {

			e.printStackTrace();

		}
                finally
                {

			try
                        {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}      
      
      
  	return retString;  	
  	  	
  }    
 
    
  public boolean writeFile(String text, String toFile)  // xml
  {
      boolean retIsWritten = false;
      try
      {
   
          PrintWriter printWriter= new PrintWriter(new OutputStreamWriter(new FileOutputStream(toFile), "UTF-8"));//"ISO-8859-7"));	
          printWriter.print(text);          
          printWriter.close();
          retIsWritten = true;
          //System.out.println("PanelReportFormDesign.writeFile "+toFile);
      } // try io
      catch (UnsupportedEncodingException e)
      {System.out.println("UtilsFileSystem.writeFile UnsupportedEncodingException "+e);
      retIsWritten = false;
      }
      catch (IOException e)
      {System.out.println("UtilsFileSystem.writeFile IOException "+e);
      retIsWritten = false;
      }	  	  	
  	return retIsWritten;  	
  }    
  
  public void replaceElementInXMLFile(String toFile,String tagCategory,String entityToRemove,String elementToAdd, String[] tagElements,String[] tagElementsType )
  {
  	   int intLine =-1;
       XMLReader reader = new XMLReader();
       //entityToRemove
      //String[] tagElements ={"name"};
      //String[] tagElementsType ={"String"};           
      intLine = reader.getElementLine(toFile,tagCategory,tagElements,tagElementsType,entityToRemove);  	

       String text="";
       String line ="";
   	  try
   	  {
       BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(toFile)));//fileName),
 
                       int intLineInFile=-1;
                       while((line = in.readLine()) != null)
                       {  
                       	    if(intLineInFile==intLine)
                       	    {
                       	    	text=text+elementToAdd;
                       	    	intLineInFile++;  
                       	    }
                       	    else
                       	    {
                               text= text+line+"\n";
                               intLineInFile++;                       	    	
                       	    }
                       	    

                       } 
                in.close();       
      }
      catch (IOException ex)
      {
          System.err.println("UtilsFileSystem.replaceElementInXMLFile IOException:"+ex+" file: "+toFile);
          //System.err.println(ex);
      }              
       
      try
      {
          //  true = append
          PrintWriter printWriter= new PrintWriter(new OutputStreamWriter(new FileOutputStream(toFile), "UTF-8"));//"ISO-8859-7"));	
          printWriter.write(text);          
          printWriter.close();
          
          //System.out.println("PanelReportFormDesign.writeFile "+toFile);
      } // try io
      catch (UnsupportedEncodingException e)
      {System.out.println("UtilsFileSystem.replaceElementInXMLFile UnsupportedEncodingException "+e);
      }
      catch (IOException e)
      {System.out.println("UtilsFileSystem.replaceElementInXMLFile IOException "+e);
      }
  	
  }
  
  
  public void addToXMLFile(String textToAdd, String toFile, String elementAtTheEnd)
  {
       //read file and remove element
       String text="";
       String line ="";
   	  try
   	  {
       BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(toFile)));//fileName),
 
 
                       while((line = in.readLine()) != null)
                       {  
                            text= text+line+"\n";
                       } 
                in.close();       
      }
      catch (IOException ex)
      {
          System.err.println("UtilsFileSystem.addToXMLFile IOException:"+ex+" file: "+toFile);
          //System.err.println(ex);
      }         
       text=text.replaceFirst(elementAtTheEnd,"");
       	text = text.substring(0,text.length()-1);
       	text=text+textToAdd;
       
      //add element to the end
      text=text+elementAtTheEnd;       
       
      try
      {
          //  true = append
          PrintWriter printWriter= new PrintWriter(new OutputStreamWriter(new FileOutputStream(toFile), "UTF-8"));//"ISO-8859-7"));	
          printWriter.write(text);          
          printWriter.close();
          
          //System.out.println("PanelReportFormDesign.writeFile "+toFile);
      } // try io
      catch (UnsupportedEncodingException e)
      {System.out.println("UtilsFileSystem.addToXMLFile UnsupportedEncodingException "+e);
      }
      catch (IOException e)
      {System.out.println("UtilsFileSystem.addToXMLFile IOException "+e);
      }
      

  	
  }
   
  public boolean fileExistsOrNot(String file)
  {  
  boolean exists = (new File(file)).exists(); 
  return exists; 
  }
 }