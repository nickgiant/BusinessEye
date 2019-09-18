// created 22-09-2006

package com.tool.guicomps;

import com.tool.gui.*;
import com.tool.model.*;
import com.tool.jdbc.*;
import com.tool.utils.*;

import java.util.Vector;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.File;

import javax.swing.*;
import java.awt.*;
import javax.swing.filechooser.*;

import java.sql.*;

  public class ExportToFile implements Constants
  {
  	  private int colCount;
      private String[] colNames;
      private Class[] colClass;
      private Vector dataVector;
      private PrintWriter printWriter;
         private ResultSetMetaData rsmd;
         private Database db= new Database();
  	  //private WindowWait wWaitTimer;
  	private PanelExportToFileSettings panelExportToFileSettings;
  	private EntityExportFileType[] entityExportFileType;
  	
    public ExportToFile()
    {
    	 UtilsMiscEntities utilsMiscEntities = new UtilsMiscEntities();
    	 entityExportFileType = utilsMiscEntities.getExportToFileEntities(); 
    	 	
    	 
    }
 
    public void exportTo(int colCountIn, String[] colNamesIn, Class[] colClassIn, Vector dataVectorIn ,String typeName,  JFrame frame, String strOfMany)
    {
       panelExportToFileSettings = new PanelExportToFileSettings(frame);
       panelExportToFileSettings.setEntity(typeName);
       
 		String extension="";
 		String typeCaption="";
 		for(int e= 0 ;e<entityExportFileType.length;e++)
 		{
 			if(typeName.equals(entityExportFileType[e].getName()))
 			{
 				extension=entityExportFileType[e].getExtension();
 				typeCaption = entityExportFileType[e].getCaption();
 			}
 		}     
      //wWaitTimer = new WindowWait("<html>παρακαλώ περιμένετε <br> ()</html>",WINDOW_LOCATION_CENTER,null); 
      if(typeName == null || typeName.equalsIgnoreCase(""))// it is dialog settings
      {
      
          JOptionPane.showMessageDialog(null, "dialog settings not implemented yet", "attention",JOptionPane.INFORMATION_MESSAGE);
      }
      else if(typeName.equals("text")||typeName.equals("excel") || typeName.equals("xml") || typeName.equals("pdf") || typeName.equals("oocalc"))
      {
      	
      	DialogMulti dlg = new DialogMulti(frame);
      	
        dlg.setEntity(panelExportToFileSettings,PANEL_TYPE_ANY, "ρυθμίσεις εξαγωγής αρχείου τύπου "+typeCaption+" ("+extension+")",false);
        dlg.display();
      	
      	 // JOptionPane.showMessageDialog(null, "type '"+type+"' not implemented yet", "attention",JOptionPane.INFORMATION_MESSAGE);
      }
      else if (typeName.equals("html"))
      {
 		JFileChooser fc = new JFileChooser(); 
 		
 	//	FileFilter ff = new FileFilter(new File(type));
 	//	fc.addChoosableFileFilter(new FileFilter(new File(type)));
    //   setFileFilter(FileFilter filter) 
    
 		// show the filechooser 

 		
 		fc.setDialogTitle("Εξαγωγή αρχείου τύπου "+typeCaption);
 		File curDir = new File(System.getProperty("user.dir"));
 		fc.setCurrentDirectory(curDir);
 		fc.setSelectedFile(new File("Filename"+extension));
 		int result = fc.showSaveDialog(null); 
 		 
 		// if we selected an image, load the image 
 		if(result == JFileChooser.APPROVE_OPTION)
 		{ 
      	
      	//fc.getCurrentDirectory();
      	
       //wWaitTimer.showWindow();
      	
  	  int colCount =colCountIn;
      String[] colNames=colNamesIn;
      Class[] colClass=colClassIn;
      Vector dataVector=dataVectorIn;
      String file = fc.getSelectedFile().getName();
      //String file=fc.getName()+"."+type;
      String strExport="";
      String delimiter="|";
      
      //first
      if(typeName.equals("html"))
      {
      	          strExport="<html><table>";
      }
      
      //header
      for(int c = 1; c < colCount; c++)
      {
          if(typeName.equals("html"))
          {
      	      strExport=strExport+"<th>"+colNames[c]+"</th>";
      	  }
      	  
      }
      
      //data row
      for(int r=1; r<dataVector.size();r++)
      {
        if(typeName.equals("html"))
        {
      	  strExport=strExport+"<tr>";
      	}
      	
      	 	//column
         for(int c = 1; c < colCount; c++)
         {
            if(typeName.equals("html"))
            {    
                  Object data = ((Object[]) dataVector.elementAt(r))[c];
                  if (data!=null)
                  {
                  	 strExport=strExport+"<td>"+data+"</td>";
                  }
                  else
                  {
                  	 strExport=strExport+"<td> </td>";
                  }
                
      	         
      	    }
      	    //((Object[]) dataVector.elementAt(r))[c];
      	 	
      	 }

      	 
        if(typeName.equals("html"))
        {
      	  strExport=strExport+"</tr>";
      	}
      	  
      }
      
      //last
      if(typeName.equals("html"))
      {
      	          strExport=strExport+"</table></html>";
      }
      
      try
      {
      
          printWriter= new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), "ISO-8859-7"));	
          printWriter.print(strExport);
          printWriter.close();
          
          System.out.println("ExportToFile.exportTo exported "+file);
      } // try io
      catch (UnsupportedEncodingException e)
      {System.out.println("panelReport.setEntity UnsupportedEncodingException "+e);
      }
      catch (IOException e)
      {System.out.println("panelReport.setEntity IOException "+e);
      }
       //wWaitTimer.dispose();
 		} //if ok in open dialog clicked      	       
     }// if is html
     else
     {
     	JOptionPane.showMessageDialog(null, "type "+typeName+" not implemented yet", "attention",JOptionPane.INFORMATION_MESSAGE);
     }
     
    }
  
  
  
  }