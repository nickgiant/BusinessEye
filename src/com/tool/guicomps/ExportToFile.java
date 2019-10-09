// created 22-09-2006

package com.tool.guicomps;

import com.tool.gui.*;
import static com.tool.guicomps.Constants.ICO_RELOAD16;
import static com.tool.guicomps.Constants.ICO_RELOADB16;
import static com.tool.guicomps.Constants.WINDOW_LOCATION_CENTER;
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

import javax.swing.table.TableModel;
import javax.swing.*;
import java.awt.*;
import javax.swing.filechooser.*;

import java.sql.*;
import java.util.Locale;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

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
 
    public void exportTo(int colCountIn, JTable tableIn, String[] colNamesIn, Class[] colClassIn, Vector dataVectorIn ,String typeName,  JFrame frame, String strOfMany)
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
      else if(typeName.equals("xls") ||typeName.equals("excel"))
      {
                    
                  
        //addCaption(sheet, 0, 0, "Header 1");
        //addCaption(sheet, 1, 0, "This is another header");

         JFileChooser fileChooser = new JFileChooser();//fldExportSelect.getText());
         // JFileChooser fileChooser = new JFileChooser(); 
         FileNameExtensionFilter filter = new FileNameExtensionFilter("XLS FILES", "xls", "xls");
         fileChooser.setFileFilter(filter);
        fileChooser.setSelectedFile(new File("FileToExport.xls"));
         int retval = fileChooser.showSaveDialog(frame);

    if (retval == JFileChooser.APPROVE_OPTION)
    {         
        //fileChooser.set
        File file = fileChooser.getSelectedFile(); 
    
        //File file = new File(strFileEmpty);
        //fldExportSelect.setText(file.getAbsolutePath())   ;
        exportToExcelXls(file.getAbsolutePath(),tableIn);
    }
       
        
        
      }
      else if(typeName.equals("text") || typeName.equals("xml") || typeName.equals("pdf") || typeName.equals("oocalc"))
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
  
    private void exportToExcelXls(String strFile,JTable tableIn)
    {
        

     //boolean boolReturn=false;
      
          	 WindowWait wwe = new WindowWait("παρακαλω περιμένετε, εξαγωγή",WINDOW_LOCATION_CENTER,ICO_RELOAD16, ICO_RELOADB16);
         wwe.animate();
   		          // thread for show window wait
	   Thread      thread = new Thread(new Runnable() {
	          public void run()
	          {
	          
           	       wwe.showWindow();
	          
	               //thread = null;
	          }
	          });
              thread.start();   	  	
     
              // thread for backup
              thread = new Thread(new Runnable() {
	          public void run()
	          {   	      
      
      
      
    try
    {
        // Lets create a times font
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
        // Define the cell format
        WritableCellFormat writableCellFormatTimes = new WritableCellFormat(times10pt);
        // Lets automatically wrap the cells
        writableCellFormatTimes.setWrap(true);

        // create create a bold font with unterlines
        WritableFont times10ptBoldUnderline = new WritableFont( WritableFont.TIMES, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
       WritableCellFormat writableCellFormatTimesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        // Lets automatically wrap the cells
        writableCellFormatTimesBoldUnderline.setWrap(true);

        CellView cv = new CellView();
        cv.setFormat(writableCellFormatTimes);
        cv.setFormat(writableCellFormatTimesBoldUnderline);
        cv.setAutosize(true);

        // Write a few headers
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("en", "EN"));

        //String strFile = fldExportSelect.getText();
        File file = new File(strFile);
        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Report", 0);
        WritableSheet excelSheet = workbook.getSheet(0);
       /*db = new Database();
        String sql = "SELECT * FROM "+table;
        db.retrieveDBDataFromQuery(sql, "PanelDataImportExport.fileExport");
        ResultSetMetaData rsmd = db.getRSMetaData();
        ResultSet rs = db.getRS();*/
    
        
        jxl.write.Label label;
        TableModel model = tableIn.getModel();
        for(int col=0; col< model.getColumnCount(); col++)
        {
            String columnLabel = model.getColumnName(col);//colNamesIn[col];//fieldsTranslation[i]; //get colunm name    
            //Label column = new Label(i, 0, model.getColumnName(i));            
            //System.out.println("PanelDataImport.emptyFileExport   table:"+table+"         ("+col+") "+columnLabel);
            
             label = new jxl.write.Label(col, 0, columnLabel, writableCellFormatTimesBoldUnderline);
             excelSheet.addCell(label);             
        
             jxl.write.Label data;
             //int row = 0;
                         int j = 0;
            for (int i = 0; i < model.getRowCount(); i++)
            {
                for (j = 0; j < model.getColumnCount(); j++)
                {
                    //Label lblRow = new Label(j, i + 1, model.getValueAt(i, j).toString(),writableCellFormatTimes);
                    if(model.getValueAt(i, j)!=null)
                    {
                   //System.out.println(j+"  "+ i +" " +model.getValueAt(i, j).toString());
                    data = new jxl.write.Label(j, i+1, model.getValueAt(i, j).toString(), writableCellFormatTimes);
                    excelSheet.addCell(data);                           
                    }
                   
                   //sheet1.addCell(row);
                }
            }
             //rs.beforeFirst();
           
        
        }
        
        
        
      

        workbook.write();
        workbook.close();
        
               wwe.close();
      // thread = null;
        
    
        //boolReturn = true;
        
    }
    catch(WriteException we)
    {
        //boolReturn = false;
         wwe.close();
         we.printStackTrace();
    }
    catch (IOException e)
    {    //boolReturn = false;
         wwe.close();
           e.printStackTrace();
    }  
                    }
                   });
              thread.start();               
    	
               
        
        
        
    }
  
  
  }