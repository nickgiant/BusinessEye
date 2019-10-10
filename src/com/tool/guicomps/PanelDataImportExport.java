/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.guicomps;

import com.tool.gui.*;
import com.tool.jdbc.*;



import java.sql.*;

import com.tool.model.EntityDBFields;
import com.tool.model.EntityDataEsoExo;
import com.tool.model.EntityDataSerSales;
import com.tool.utils.DataTree;
import com.tool.utils.EventQueueTxtRightClick;
import com.tool.utils.GridLayoutVariable;
import com.tool.utils.UtilsGui;
import com.tool.utils.UtilsOS;
import com.tool.utils.VariablesGlobal;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.swing.JFileChooser;


import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.CellView;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
/**
 *
 * @author small
 */
public class PanelDataImportExport extends JxPanel implements Constants
{
    
    private JxPanel panelMain;
    private JxPanel pnlCenterImport;
    private ArrayList lstSpreadsheetColumns;
    private ArrayList lstSpreadsheetColumnsType;
    private JTextField fldSelectToImport;
    private JTextField fldExportSelect;
    private WritableCellFormat writableCellFormatTimesBoldUnderline;
    private WritableCellFormat writableCellFormatTimes;  
    private final int IMPORT = 1;
    private final int EXPORT = 2;
    private Database db;
    public PanelDataImportExport(int importexport)//(Frame parent, boolean modal)
    {
       initialize(importexport);
    }

    
    /** This method is called from within the constructor to initialize the form.*/
     
    private void initialize(int importexport) 
    {
    	//changeSupport = new PropertyChangeSupport(this);
    lstSpreadsheetColumns = new ArrayList();
    lstSpreadsheetColumnsType = new ArrayList();
    panelMain = new JxPanel();
     panelMain.setLayout(new BorderLayout());
      this.setLayout(new BorderLayout());      
            this.add(panelMain, BorderLayout.PAGE_START); 
            if(importexport==IMPORT)
            {
                setEntityImport();
            }
            else if(importexport==EXPORT)
            {
                setEntityExport();
            }
            else
            {
                
            }
    }
    /*
    *
    * called by
    */
    public void setEntityImport()
    {
         pnlCenterImport = new JxPanel();
        // panelMain.setLayout(new BorderLayout());
         //panelExport.setLayout(new GridBagLayout());
         panelMain.setBorder( new TitledBorder("εισαγωγή αρχείου στη βάση δεδομένων") );
      JxPanel pnlTop = new JxPanel();
      pnlTop.setLayout(new FlowLayout());
       JLabel lblSelectFile = new JLabel("    1.επιλογή αρχείου");
       fldSelectToImport = new JTextField(37);
       fldSelectToImport.setText(VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+"logariasmoi.xls");
      JButton btnSelectFile = new JButton();
      btnSelectFile.setIcon(ICON_TREEFOLDER_OPENED);
      btnSelectFile.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   
	           	    calculateSpreadsheetColumnsFromFile();
	        } 
	    });
     

      JLabel lblSelectTable = new JLabel("    2.επιλογή πίνακα");
      JComboBox cmbTable = new JComboBox(getTablesImportForProcess());
      cmbTable.addItemListener(new ItemListener(){
        @Override
        public void itemStateChanged(ItemEvent e)
        {
          setSelectedTable(cmbTable.getSelectedItem()+"");
        }
    });
      
      
       JLabel lblMaxRowsToImport = new JLabel("    3.μέγιστος αριθμός γραμμών");
       JTextField fldMaxRowsToImport = new JTextField(6);      
      
      
       JLabel lblImportFile = new JLabel("    4.");
      JButton btnImportFile = new JButton("εισαγωγή");    

   
      // JLabel lblCreateEmptyFile = new JLabel("δημιουργία αρχείου χωρις δεδομένα,");
     /* JLabel lblSelectTableForEmpty = new JLabel("επιλογή πίνακα");
      
       JLabel lblSelectEmptyFile = new JLabel("επιλογή αρχείου");
       fldEmptySelect = new JTextField(37);
       fldEmptySelect.setText(VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+"logariasmoiempty.xls"); 
      JButton btnSelectFileEmpty = new JButton();  
      btnSelectFileEmpty.setIcon(ICON_TREEFOLDER_OPENED);
      btnSelectFileEmpty.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	
                    chooseFileToExportEmpty();
	        }
	    });       
       
      JComboBox cmbTableForEmpty = new JComboBox(getTablesForProcess());
      JButton btnCreateEmptyFile = new JButton("δημιουργία");  
      btnCreateEmptyFile.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	
                    setTableToExportEmpty( cmbTableForEmpty.getSelectedItem()+"");
	        } 
	    });
      
     
      cmbTableForEmpty.addItemListener(new ItemListener(){
        @Override
        public void itemStateChanged(ItemEvent e)
        {
          btnCreateEmptyFile.setText("δημιουργία "+cmbTableForEmpty.getSelectedItem());
        }
    });       */

      pnlTop.add(lblSelectFile);
      pnlTop.add(fldSelectToImport);
      pnlTop.add(btnSelectFile);
      pnlTop.add(lblSelectTable);
      pnlTop.add(cmbTable);
      pnlTop.add(lblMaxRowsToImport);
      pnlTop.add(fldMaxRowsToImport);
      pnlTop.add(lblImportFile);
      pnlTop.add(btnImportFile);   


      GridLayoutVariable layout = new GridLayoutVariable (GridLayoutVariable.FIXED_NUM_COLUMNS, 4);
      
      pnlCenterImport.setLayout(layout);
      pnlCenterImport.setBorder(new TitledBorder("διαθέσιμα πεδία στη βάση δεδομένων"));
      
     //cmbTable.setSelectedIndex(0);
     /*
      JxPanel pnlBottom = new JxPanel();
      pnlBottom.setLayout(new FlowLayout());
      pnlBottom.setBorder( new TitledBorder("δημιουργία αρχείου χωρις δεδομένα"));      
     
      //pnlBottom.add(lblCreateEmptyFile);
      pnlBottom.add(lblSelectTableForEmpty);
      pnlBottom.add(cmbTableForEmpty);
      pnlBottom.add(lblSelectEmptyFile);
      pnlBottom.add(fldEmptySelect);
      pnlBottom.add(btnSelectFileEmpty);
      pnlBottom.add(btnCreateEmptyFile);     
      */
         panelMain.add(pnlTop, BorderLayout.PAGE_START);
         panelMain.add(pnlCenterImport, BorderLayout.CENTER);
         //panelMain.add(pnlBottom, BorderLayout.PAGE_END);
    }    

    
    
    
    public void setEntityExport()
    {
 panelMain.setBorder( new TitledBorder("εξαγωγή αρχείου από τη βάση δεδομένων") );
      JxPanel pnlTop = new JxPanel();
      pnlTop.setLayout(new FlowLayout());     

      JLabel lblSelectTable = new JLabel("    1.επιλογή πίνακα");
      JComboBox cmbTableExport = new JComboBox(getTablesExportForProcess());
           
      
      
      
      
      
      JLabel lblSelectFile = new JLabel("    2.επιλογή αρχείου");
      fldExportSelect = new JTextField(37);
       fldExportSelect.setText(VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+"export.xls");
      JButton btnSelectFile = new JButton();
      btnSelectFile.setIcon(ICON_TREEFOLDER_OPENED);
      btnSelectFile.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   
                     chooseFileToExport();
	           	    //calculateSpreadsheetColumnsFromFile();
	        } 
	    });      
      
      
       JLabel lblExportFile = new JLabel("    3.");
      JButton btnExportFile = new JButton("εξαγωγή"); 
      btnExportFile.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	
                    
                    setTableToExport(cmbTableExport.getSelectedItem()+"",null);
	        } 
	    });      
      

      cmbTableExport.addItemListener(new ItemListener(){
        @Override
        public void itemStateChanged(ItemEvent e)
        {
            fldExportSelect.setText(VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+"export"+cmbTableExport.getSelectedItem()+".xls");
          btnExportFile.setText("δημιουργία "+cmbTableExport.getSelectedItem());
        }
    });  
      

      pnlTop.add(lblSelectTable);
      pnlTop.add(cmbTableExport);      
      pnlTop.add(lblSelectFile);
      pnlTop.add(fldExportSelect);
      pnlTop.add(btnSelectFile);
      pnlTop.add(lblExportFile);
      pnlTop.add(btnExportFile);

      
      //JxPanel pnlBottom = new JxPanel();
      //pnlBottom.setLayout(new FlowLayout());
      //pnlBottom.setBorder( new TitledBorder("δημιουργία αρχείου χωρις δεδομένα"));  

         panelMain.add(pnlTop, BorderLayout.PAGE_START);
       //  panelMain.add(pnlCenter, BorderLayout.CENTER);
       //  panelMain.add(pnlBottom, BorderLayout.PAGE_END);  
    }    
    
    
      private void locateOnCenterOfTheScreen()
     {
    	Dimension paneSize   = this.getSize();
    	Dimension screenSize = this.getToolkit().getScreenSize();
    	this.setLocation(
            (screenSize.width - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
      }
    
      
      
      private String[] getTablesExportForProcess()
      {
          String[] tables = new String[2];
          
          
          tables[0] = "customer";
          tables[1] = "service" ;
         // tables[2] = "customer";
         
          
          return tables;
      }
      
      
      /*
      *   add table fields also to EntityDataSerSales.getEntityDbFieldsToImport
      */
      private String[] getTablesImportForProcess()
      {
          String[] tables = new String[2];
          
          
          tables[0] = "customer";
          tables[1] = "service" ;
         
          
          return tables;
      }
    
      
      private int calculateAndSelectFieldFromString(String strColumn)
      {
          int intSel = 0;
          String[] spreadsheetColumns = new String[lstSpreadsheetColumns.size()+1];
           if(lstSpreadsheetColumns.size()>0)
          {
            spreadsheetColumns[0]= "";
            for(int l = 0;l<lstSpreadsheetColumns.size();l++)
            {
                spreadsheetColumns[l+1]= lstSpreadsheetColumns.get(l)+"";
                //System.out.println("PanelDataImportExport.calculateAndSelectFieldFromString      l:"+l+"     "+strColumn+"   spreadsheetColumns[l+1]"+spreadsheetColumns[l+1]);
                if(strColumn.equalsIgnoreCase(spreadsheetColumns[l+1]))
                {
                    intSel=l+1;
                }
            }
          }          
          
          return intSel;
      }
      
      
      
      private void setSelectedTable(String table)
      {
        if (pnlCenterImport != null)
        { pnlCenterImport.removeAll(); } //erases all components placed during initialization
        
        
       // DataTree dtEsoexo = this.loadAndGetEsodaExodaDataTreeNode();
       // DataTree dtSerSal = this.loadAndGetServiceSalesDataTreeNode();
        
       // int intchldesoexo = dtEsoexo.getRootElement().getNumberOfChildren();
         EntityDataSerSales  edss = new  EntityDataSerSales();
         EntityDBFields[] edbfssImport = edss.getEntityDbFieldsToImport();
           
          if(showSelectedFieldsToImport(table,edbfssImport))
          {
              System.out.println("PanelODORData.setSelectedTable  SerSales  table:"+table);
          }
          else
          {
            System.out.println("PanelODORData.setSelectedTable  Not SerSales   table:"+table);
              EntityDataEsoExo  edee=new  EntityDataEsoExo();
              EntityDBFields[] edbfeeImport = edee.getEntityDbFieldsToImport();
              if(showSelectedFieldsToImport(table,edbfeeImport))
              {
                   System.out.println("PanelODORData.setSelectedTable  EsoExo  table:"+table);
              }
          }
       
      }
      
     private boolean showSelectedFieldsToImport(String table,EntityDBFields[] edbfImport)
     {
        
         boolean boolReturn=false;
        int d = 1;
       for(int g = 0;g<edbfImport.length;g++)
       {
        
       String strTable =  edbfImport[g].getTableName();
      // System.out.println("PanelODORData.showSelectedFieldsToImport   table:"+table+"  strTable:"+strTable);
          
          if(strTable.equalsIgnoreCase(table))
          {
                // System.out.println("PanelODORData.showSelectedFieldsToImport for  =  ("+i+") "+fields[i]);
                 String columnLabel = edbfImport[g].getCaption();//fieldsTranslation[i]; //get colunm name
                 String columnDbName = edbfImport[g].getDbField();
                 int intObligatoryOrSuggest = edbfImport[g].getFieldObligatoryOrSuggest();
                 String columnClass = edbfImport[g].getColClassName(); 
                 //int intPKAutoInc = primaryKeyIntegerAutoInc[i];// 
                 String cc = columnClass;

                 String lblTextType = "";
                 
                 
                 JLabel lblCap = new JLabel();
                 lblCap.setHorizontalAlignment(JLabel.RIGHT);
                 if(intObligatoryOrSuggest==FIELD_OBLIGATORY)
                 {
                     lblCap.setIcon(ICO_FIELDOBLIGATORY);
                 }
                 else if(intObligatoryOrSuggest==FIELD_SUGGEST)
                 {
                     lblCap.setIcon(ICO_FIELDSUGGEST);
                 }
                 else if (intObligatoryOrSuggest==FIELD_NOCOMPLETION)
                 {
                     
                 }
                 else
                 {
                     System.out.println("PanelDataImport.showSelectedFieldsToImport  NOT DEFINED  intObligatoryOrSuggest:"+intObligatoryOrSuggest);
                 }
                  pnlCenterImport.add(lblCap);
                  if(cc.equalsIgnoreCase("table"))
                  {
                      lblTextType = "(πίνακας)";
                    //  pnlCenterImport.add(new JLabel("πίνακας")); 
                      pnlCenterImport.add(new JLabel("-",JLabel.CENTER));                    
                  }
                  else if(cc.equalsIgnoreCase("java.lang.Boolean"))
                  {
                      lblTextType = "(ναι ή όχι)";
                      // pnlCenterImport.add(new JLabel("ναι ή όχι")); 
                     //  String[] yn = {"","yes","no"};
                     JComboBox cmbBool = new JComboBox(getSpreadsheetColumnTitles());
                      pnlCenterImport.add(cmbBool); 
                      cmbBool.setSelectedIndex(calculateAndSelectFieldFromString(columnDbName));
                  }
                  else if(cc.equalsIgnoreCase("java.lang.Integer"))
                  {
                      lblTextType = "(αριθμός)";
                     // pnlCenterImport.add(new JLabel("αριθμός")); 
                      JComboBox cmbInteg = new JComboBox(getSpreadsheetColumnTitles());
                      pnlCenterImport.add(cmbInteg); 
                      cmbInteg.setSelectedIndex(calculateAndSelectFieldFromString(columnDbName));
                  }
                  else if(cc.equalsIgnoreCase("java.lang.Double"))
                  {
                      lblTextType = "(αριθμός με δεκαδικά)";
                     // pnlCenterImport.add(new JLabel("αριθμός")); 
                      JComboBox cmbInteg = new JComboBox(getSpreadsheetColumnTitles());
                      pnlCenterImport.add(cmbInteg); 
                      cmbInteg.setSelectedIndex(calculateAndSelectFieldFromString(columnDbName));
                  }                  
                  else if(cc.equalsIgnoreCase("java.lang.String"))
                  {
                      lblTextType = "(κείμενο)";
                  // pnlCenterImport.add(new JLabel("κείμενο"));
                   JComboBox cmbText = new JComboBox(getSpreadsheetColumnTitles());
                  pnlCenterImport.add(cmbText); 
                  cmbText.setSelectedIndex(calculateAndSelectFieldFromString(columnDbName));//0);
                  }
                  else 
                  {
                      lblTextType = cc;
                   //pnlCenterImport.add(new JTextField(cc));
                   JComboBox cmbElse = new JComboBox(getSpreadsheetColumnTitles());
                  pnlCenterImport.add(cmbElse); 
                  cmbElse.setSelectedIndex(calculateAndSelectFieldFromString(columnDbName));
                  }                  

                  
                  
                  String lblText = d+") "+columnLabel+" ("+columnDbName+") "+lblTextType+": ";
                  d++;
                  lblCap.setText(lblText);
                  
              // }             
              boolReturn= true;
         } 
         else
         {
           boolReturn = false;
         }
    }
         return boolReturn; 
      }

     
     
     /*  
           http://www.vogella.com/tutorials/JavaExcel/article.html
     */
     private void calculateSpreadsheetColumnsFromFile()
     {
         String strField;
         JFileChooser fileChooser = new JFileChooser(fldSelectToImport.getText());
         // JFileChooser fileChooser = new JFileChooser();
          
   int retval = fileChooser.showOpenDialog(fldSelectToImport);

    if (retval == JFileChooser.APPROVE_OPTION)
    {         
        File file = fileChooser.getSelectedFile();
        if (file != null) 
        {
            if (!file.getName().toLowerCase().endsWith(".xls"))
            {
                file = new File(file.getParentFile(), file.getName() + ".xls");



            }
        fldSelectToImport.setText(file.getPath());
     /*       try {
                
                
              fldSelectToImport.setText(file.getPath());
      //          ExcelExporter exp=new ExcelExporter();
      //          exp.exportTable(jTable1, file);
                Desktop.getDesktop().open(file);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("not found");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
         }     
    }
         
         
         // http://www.vogella.com/tutorials/JavaExcel/article.html
         
           strField = fldSelectToImport.getText();
File inputWorkbook = new File(strField);
        Workbook w;          
try {
            w = Workbook.getWorkbook(inputWorkbook);
            // Get the first sheet
            Sheet sheet = w.getSheet(0);
            // Loop over first 10 column and lines
            lstSpreadsheetColumns.clear();
            lstSpreadsheetColumnsType.clear();
            for (int j = 0; j < sheet.getColumns(); j++)
            {
                Cell cellColumnHeader = sheet.getCell(j, 0);
                    CellType typeColumnHeader = cellColumnHeader.getType();
                    if (typeColumnHeader == CellType.LABEL)
                    {
                        
                        Cell cellColumnFirstData = sheet.getCell(j, 1);
                        CellType typeFirstData = cellColumnFirstData.getType();
                        String typeFirstDataCaption="";
                        if (typeFirstData == CellType.LABEL)
                        {
                           typeFirstDataCaption = "(κείμενο)";
                        }
                        else if (typeFirstData == CellType.NUMBER) 
                        {
                           typeFirstDataCaption = "(αριθμός)"; 
     
                        }
                        else if (typeFirstData == CellType.DATE) 
                        {
                            typeFirstDataCaption = "(ημερομηνία)"; 
                        }
                        else
                        {
                            
                        }
                                
                         lstSpreadsheetColumns.add(cellColumnHeader.getContents());
                         lstSpreadsheetColumnsType.add(typeFirstDataCaption);
                        
                    }                
                for (int i = 0; i < sheet.getRows(); i++)
                {
                    Cell cell = sheet.getCell(j, i);
                    CellType type = cell.getType();
                    if (type == CellType.LABEL) {
        //                System.out.println("I got a label " + cell.getContents());
                    }

                    if (type == CellType.NUMBER) {
       //                 System.out.println("I got a number " + cell.getContents());
                    }

                }
            }
            } catch (IOException e)
            {
                e.printStackTrace();
            }            
         catch (BiffException e)
        {
            e.printStackTrace();
        }          
         
     }

      private void chooseFileToExport()
      {
          
                  
        //addCaption(sheet, 0, 0, "Header 1");
        //addCaption(sheet, 1, 0, "This is another header");

         JFileChooser fileChooser = new JFileChooser(fldExportSelect.getText());
         // JFileChooser fileChooser = new JFileChooser();      
    int retval = fileChooser.showSaveDialog(this);

    if (retval == JFileChooser.APPROVE_OPTION)
    {         
        File file = fileChooser.getSelectedFile(); 
    
        //File file = new File(strFileEmpty);
        fldExportSelect.setText(file.getAbsolutePath())   ;
    }
               
      }


     
     
     /*  
           http://www.vogella.com/tutorials/JavaExcel/article.html
     */
     private void setTableToExport(String table,EntityDBFields[] edbfExport)
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
        writableCellFormatTimes = new WritableCellFormat(times10pt);
        // Lets automatically wrap the cells
        writableCellFormatTimes.setWrap(true);

        // create create a bold font with unterlines
        WritableFont times10ptBoldUnderline = new WritableFont( WritableFont.TIMES, 10, WritableFont.BOLD, false, UnderlineStyle.SINGLE);
        writableCellFormatTimesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        // Lets automatically wrap the cells
        writableCellFormatTimesBoldUnderline.setWrap(true);

        CellView cv = new CellView();
        cv.setFormat(writableCellFormatTimes);
        cv.setFormat(writableCellFormatTimesBoldUnderline);
        cv.setAutosize(true);

        // Write a few headers
        

        

        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("en", "EN"));

        String strFile = fldExportSelect.getText();
        File file = new File(strFile);
        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Report", 0);
        WritableSheet excelSheet = workbook.getSheet(0);
       db = new Database();
        String sql = "SELECT * FROM "+table;
        db.retrieveDBDataFromQuery(sql, "PanelDataImportExport.fileExport");
        ResultSetMetaData rsmd = db.getRSMetaData();
        ResultSet rs = db.getRS();
        try
        {
        
        Label label;
        for(int col=1; col<= rsmd.getColumnCount(); col++)
        {
            String columnLabel = rsmd.getColumnLabel(col);//fieldsTranslation[i]; //get colunm name    
            //System.out.println("PanelDataImport.emptyFileExport   table:"+table+"         ("+col+") "+columnLabel);

             label = new Label(col, 0, columnLabel, writableCellFormatTimesBoldUnderline);
             excelSheet.addCell(label);             
        
             Label data;
             int row = 1;
             rs.beforeFirst();
            while(rs.next())
            {
                wwe.setComment("γραμμή:"+row);
            String columnData = rs.getString(columnLabel);//fieldsTranslation[i]; //get colunm name    
           //System.out.println("PanelDataImport.emptyFileExport   table:"+table+"         ("+col+") "+columnLabel+":"+columnData);

             data = new Label(col, row, columnData, writableCellFormatTimes);
             excelSheet.addCell(data);                
                
                
               // excel.write(model.getValueAt(i,j).toString()+"\t");
                row++;
            }
        
        }
        
        
        
        
        }
        catch(SQLException e)
        {
                   wwe.close();
     
            System.out.println("PanelDataImportExport.fileExport    SQLException:"+e.getErrorCode()+"  "+e.getMessage());
           e.printStackTrace();
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
    	
       

       

     // return boolReturn;
         
     }
     
      private String[] getSpreadsheetColumnTitles()
      {
          String[] spreadsheetColumns = new String[lstSpreadsheetColumns.size()+1];
          if(lstSpreadsheetColumns.size()>0)
          {
            spreadsheetColumns[0]= "";
            for(int l = 0;l<lstSpreadsheetColumns.size();l++)
            {
                spreadsheetColumns[l+1]= lstSpreadsheetColumns.get(l)+" "+lstSpreadsheetColumnsType.get(l);
            }
          }
          else
          {
              spreadsheetColumns = new String[1];
              spreadsheetColumns[0]= "";
          }
          
          if(spreadsheetColumns==null)
          {
              spreadsheetColumns = new String[1];
               spreadsheetColumns[0]= "";
          }
          
          
          return spreadsheetColumns;
      }     
     
   
      
       private void setCloseClick(JDialog dlg)
   {
   	dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);   
    dlg.addWindowListener(new WindowAdapter()
    {
       public void windowClosing(WindowEvent e)
       {    dlg.dispose();   }
    });
   }

    private DataTree loadAndGetEsodaExodaDataTreeNode()
    {
      EntityDataEsoExo  entityDataEsoExo = new EntityDataEsoExo();
        
       boolean[] isNodeVisible ={true, true, true, true,true};
        entityDataEsoExo.addMainNavigationNodes(isNodeVisible);
        entityDataEsoExo.loadAllNodes();       
        DataTree dTreeMetrics = entityDataEsoExo.getDataTree();
             
       return dTreeMetrics;//dTree;
        
    }          
       
     private DataTree loadAndGetServiceSalesDataTreeNode()
    {
      EntityDataSerSales    entityDataSersal = new EntityDataSerSales();
        
       boolean[] isNodeVisible ={true, true, true, true,true};
        entityDataSersal.addMainNavigationNodes(isNodeVisible);
        entityDataSersal.loadAllNodes();       
        DataTree dTreeSsales = entityDataSersal.getDataTree();
             
       return dTreeSsales;//dTree;
        
    } 
       
       
       
       
    public static void main(String args[])
    {
    	
    	VariablesGlobal.globalCompanyId="1";
    	VariablesGlobal.globalCompanyName="no";
    	VariablesGlobal.globalDate="01-01-2009";
    	//VariablesGlobal.globalDeliveryId="1";
    	VariablesGlobal.globalDirConfiguration = System.getProperty("user.dir");
    	VariablesGlobal.globalUserId="user";
    	//VariablesGlobal.globalYear="2009";    	
    	
        // set right click on texts
        Toolkit.getDefaultToolkit().getSystemEventQueue().push(new EventQueueTxtRightClick());     	
    	
    	UtilsGui utilsGui = new UtilsGui();
    	utilsGui.setLookAndFeel();
    	
    	JDialog dialogthis =new JDialog();
        dialogthis.setLayout(new BorderLayout());
          
        // IMPORT = 1;       EXPORT = 2;
        PanelDataImportExport pnl = new PanelDataImportExport(1);
    	
        dialogthis.add(pnl, BorderLayout.CENTER);

        dialogthis.pack();
        pnl.locateOnCenterOfTheScreen();
    	pnl.setCloseClick(dialogthis);
    	dialogthis.setVisible(true);
    }    
 
}
