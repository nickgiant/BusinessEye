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
    private DefaultListModel listImportFooterModel;
    private DefaultListModel listExportFooterModel;
    private String fieldNameAutoInc = "";
    private UtilsGui utilsGui;
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
     utilsGui = new UtilsGui();
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
        db = new Database();
         pnlCenterImport = new JxPanel();
        // panelMain.setLayout(new BorderLayout());
         //panelExport.setLayout(new GridBagLayout());
         panelMain.setBorder( new TitledBorder("εισαγωγή αρχείου στη βάση δεδομένων") );
      JxPanel pnlTop = new JxPanel();
      pnlTop.setLayout(new FlowLayout());
       JLabel lblSelectFile = new JLabel("    1.επιλογή αρχείου");
       fldSelectToImport = new JTextField(37);
       fldSelectToImport.setText(VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol);
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
      ArrayList lstTables = getTablesImportExport();
      String[] tables = new String[lstTables.size()];
      for(int t = 0 ;t<lstTables.size();t++)
      {
       ImportExportTable tab = (ImportExportTable)lstTables.get(t);
       tables[t] =   tab.caption;
      }
      JComboBox cmbTable = new JComboBox(tables);
      cmbTable.addItemListener(new ItemListener(){
        @Override
        public void itemStateChanged(ItemEvent e)
        {  
       ImportExportTable tab = (ImportExportTable)lstTables.get(cmbTable.getSelectedIndex());
       setSelectedTable(   tab.tableName);            
  
        }
    });
      
      
       //JLabel lblMaxRowsToImport = new JLabel("    3.μέγιστος αριθμός γραμμών");
       //JTextField fldMaxRowsToImport = new JTextField(6);      
      
      
       JLabel lblImportFile = new JLabel("    3.");
      JButton btnImportFile = new JButton("εισαγωγή");    
      btnImportFile.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {
                    ImportExportTable tab = (ImportExportTable)lstTables.get(cmbTable.getSelectedIndex());
                    setFileToImport(fldSelectToImport.getText(),tab.tableName+"", tab.hasDbCompanyId);
                    //setTableToExport(cmbTableExport.getSelectedItem()+"",null);
	        } 
	    });  
   
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
      //pnlTop.add(lblMaxRowsToImport);
      //pnlTop.add(fldMaxRowsToImport);
      pnlTop.add(lblImportFile);
      pnlTop.add(btnImportFile);   


      GridLayoutVariable layout = new GridLayoutVariable (GridLayoutVariable.FIXED_NUM_COLUMNS, 6);
      
      pnlCenterImport.setLayout(layout);
      pnlCenterImport.setBorder(new TitledBorder("διαθέσιμα πεδία στη βάση δεδομένων"));
      

      JxPanel pnlBottom = new JxPanel();
      pnlBottom.setLayout(new FlowLayout());
      pnlBottom.setBorder( new TitledBorder("αποτελέσματα εισαγωγής"));   
      
      JList listImportFooter = new JList();
      listImportFooter.setSize(new Dimension(300,100));
       listImportFooterModel = new DefaultListModel();
     listImportFooter.setModel(listImportFooterModel);
      pnlBottom.add(listImportFooter);

         panelMain.add(pnlTop, BorderLayout.PAGE_START);
         panelMain.add(pnlCenterImport, BorderLayout.CENTER);
         panelMain.add(pnlBottom, BorderLayout.PAGE_END);
    }    

    
    
    
    public void setEntityExport()
    {
 panelMain.setBorder( new TitledBorder("εξαγωγή αρχείου από τη βάση δεδομένων") );
      JxPanel pnlTop = new JxPanel();
      pnlTop.setLayout(new FlowLayout());     

      JLabel lblSelectTable = new JLabel("    1.επιλογή πίνακα");
      ArrayList lstTables = getTablesImportExport();
      String[] tables = new String[lstTables.size()];
      for(int t = 0 ;t<lstTables.size();t++)
      {

       ImportExportTable tab = (ImportExportTable)lstTables.get(t);
       tables[t] =   tab.caption;
      }  
      JComboBox cmbTableExport = new JComboBox(tables);

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

       ImportExportTable tab = (ImportExportTable)lstTables.get(cmbTableExport.getSelectedIndex());
                        
                    setTableToExport(   tab.tableName+"",null,tab.hasDbCompanyId);
	        } 
	    });      
      

      cmbTableExport.addItemListener(new ItemListener(){
        @Override
        public void itemStateChanged(ItemEvent e)
        {
            fldExportSelect.setText(VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+"export"+cmbTableExport.getSelectedItem()+".xls");
          //btnExportFile.setText("δημιουργία "+cmbTableExport.getSelectedItem());
        }
    });  
      

      pnlTop.add(lblSelectTable);
      pnlTop.add(cmbTableExport);      
      pnlTop.add(lblSelectFile);
      pnlTop.add(fldExportSelect);
      pnlTop.add(btnSelectFile);
      pnlTop.add(lblExportFile);
      pnlTop.add(btnExportFile);

      
      JxPanel pnlBottom = new JxPanel();
      pnlBottom.setLayout(new FlowLayout());
      pnlBottom.setBorder( new TitledBorder("αποτελέσματα εξαγωγής"));  

      
      JList listExportFooter = new JList();
      listExportFooter.setSize(new Dimension(300,100));
       listExportFooterModel = new DefaultListModel();
     listExportFooter.setModel(listExportFooterModel);
      pnlBottom.add(listExportFooter);
      
         panelMain.add(pnlTop, BorderLayout.PAGE_START);
       //  panelMain.add(pnlCenter, BorderLayout.CENTER);
         panelMain.add(pnlBottom, BorderLayout.PAGE_END);  
    }    
    
    
      private void locateOnCenterOfTheScreen()
     {
    	Dimension paneSize   = this.getSize();
    	Dimension screenSize = this.getToolkit().getScreenSize();
    	this.setLocation(
            (screenSize.width - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
      }
    
      
      
      private class ImportExportTable
      {
          public String tableName;
           public String caption;
           public boolean hasDbCompanyId;



           
          private ImportExportTable(String tableNameIn, String captionIn, boolean hasDbCompanyIdIn)
          {
              tableName=tableNameIn;
              caption=captionIn;
              hasDbCompanyId=hasDbCompanyIdIn;
          }
          
      }
      
      
      private ArrayList getTablesImportExport()
      {
          ArrayList<ImportExportTable> lstTables = new ArrayList<ImportExportTable>();
          lstTables.add(new ImportExportTable("","",false));
          
   if(VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_TIMOLOGIA) || VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_OLA))
   { 

       lstTables.add(new ImportExportTable("customer","πελάτες",true));
       lstTables.add(new ImportExportTable("stock","υπηρεσίες",true));
   }   
   
   
   if(VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_APLOGRAFIKA ) || VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_OLA))
   {  
       lstTables.add(new ImportExportTable("sxtrader","συναλλασσόμενοι",false));
       lstTables.add(new ImportExportTable("sxaccount","λογαριασμοί",false));
   }
         
         
          
          return lstTables;
      }
      
      
      /*
      *   add table fields also to EntityDataSerSales.getEntityDbFieldsToImport
      */
      /*private String[] getTablesImportForProcess()
      {
          String[] tables = new String[3];
          tables[0] = "";
          tables[1] = "customer";
          tables[2] = "stock" ;
         
          
          return tables;
      }*/
    
      
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
         
         EntityDataEsoExo  edee=new  EntityDataEsoExo();
         EntityDBFields[] edbfeeImport = edee.getEntityDbFieldsToImport();
      

          if(showSelectedFieldsToImport(table,edbfssImport))
          {
              System.out.println("PanelODORData.setSelectedTable  SerSales  table:"+table);
          }
          else
          {
              System.out.println("PanelODORData.setSelectedTable  Not SerSales   table:"+table);

              if(showSelectedFieldsToImport(table,edbfeeImport))
              {
                   System.out.println("PanelODORData.setSelectedTable  EsoExo  table:"+table);
              }
          }
      
       
      }
    
      private boolean showSelectedFieldsToImport(String table,EntityDBFields[] edbfImport)
      {
          
          boolean boolReturn=false;
     if(!table.equalsIgnoreCase(""))
     {
          
       for(int g = 0;g<edbfImport.length;g++)
       {
        
       String strTable =  edbfImport[g].getTableName();
       //System.out.println("PanelODORData.showSelectedFieldsToImport   table:"+table+"  strTable:"+strTable);

          if(!table.equalsIgnoreCase("") && strTable.equalsIgnoreCase(table))
          {
               boolReturn= true;
          }
       }
        db = new Database();
        String sql = "SELECT * FROM "+table;
        db.retrieveDBDataFromQuery(sql, "PanelDataImportExport.showSelectedFieldsToImport");
        ResultSetMetaData rsmd = db.getRSMetaData();
        ResultSet rs = db.getRS();
        try
        {
           pnlCenterImport.removeAll();
        for(int col=1; col<= rsmd.getColumnCount(); col++)
        {
                 String columnLabel = rsmd.getColumnLabel(col);//fieldsTranslation[i]; //get colunm name  
                 String columnName = rsmd.getColumnName(col);
                 setWhichFieldIsAutoInc(table, edbfImport);
                 //System.out.println("PanelDataImportExport.showSelectedFieldsToImport  table:"+table+"  columnName:"+columnName+"  "+col);
                 JLabel lblCap = new JLabel();
                 lblCap.setHorizontalAlignment(JLabel.RIGHT);
                  String lblText = col+") "+columnName+"("+getColumnCaptionFromName(columnName,table,edbfImport)+") ";
                  lblCap.setText(lblText);
                  pnlCenterImport.add(lblCap);
                    //pnlCenterImport.add(new JTextField(cc));
                  //JComboBox cmbElse = new JComboBox(getSpreadsheetColumnTitles());
                  JLabel lblField = new JLabel("  "+getSpreadsheetColumnTitle(columnName));
                  lblField.setHorizontalAlignment(JLabel.LEFT);
                 pnlCenterImport.add(lblField);
                  
                  //pnlCenterImport.add(cmbElse);
                  //cmbElse.setSelectedIndex(calculateAndSelectFieldFromString(columnName));
                  //cmbElse.setEditable(false);
                 
             
        }
        }
        catch(SQLException e)
        {
                boolReturn=false;
     
            System.out.println("PanelDataImportExport.showSelectedFieldsToImport    SQLException:"+e.getErrorCode()+"  "+e.getMessage());
           e.printStackTrace();
        }
        finally
        {
                db.releaseConnectionRs();
                db.releaseConnectionRsmd();
        } 
            
     }  
  
          return boolReturn;
      }
      
      
      private void setWhichFieldIsAutoInc(String table,EntityDBFields[] edbfImport)
      {
          
        
       for(int g = 0;g<edbfImport.length;g++)
       {
        
       String strTable =  edbfImport[g].getTableName();
       //System.out.println("PanelODORData.showSelectedFieldsToImport   table:"+table+"  strTable:"+strTable);
          
          if(strTable.equalsIgnoreCase(table))
          {
                 
                 String columnLabel = edbfImport[g].getCaption();//fieldsTranslation[i]; //get colunm name
                 String columnDbName = edbfImport[g].getDbField();
                 //System.out.println("PanelODORData.showSelectedFieldsToImport for  =  ("+g+") "+columnDbName);
                 int intObligatoryOrSuggest = edbfImport[g].getFieldObligatoryOrSuggest();
                 String columnClass = edbfImport[g].getColClassName(); 
                 //int intPKAutoInc = primaryKeyIntegerAutoInc[i];// 
                 String cc = columnClass;

                 if(edbfImport[g].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY_AUTOINC)
                 {
                     fieldNameAutoInc = columnDbName;
                 }
                 
          }
       } 
          
      }
      
      private String getColumnCaptionFromName(String colname, String table,EntityDBFields[] edbfImport)
      {
          String strReturn="";
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

                 if(colname.equalsIgnoreCase(columnDbName))
                 {
                     strReturn = columnLabel;
                 }
                 
          }
       }  
        
        return strReturn;
      }
      
    /* private boolean showSelectedFieldsToImport(String table,EntityDBFields[] edbfImport)
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

                 if(edbfImport[g].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY_AUTOINC)
                 {
                     fieldNameAutoInc = columnDbName;
                 }
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
      }*/

     
     
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

     private boolean isColumnHeaderLike(String strHeader,Sheet sheet)
     {
         boolean ret = false;
         
              for (int i = 0; i < sheet.getColumns(); i++)
                {
                 Cell cellColumnHeader = sheet.getCell(i, 0);
                CellType typeColumnHeader = cellColumnHeader.getType();
                if(cellColumnHeader.getContents().equalsIgnoreCase(strHeader))
                {
                    ret=true;
                    break;
                }
                }
         
         return ret;
     }
     
     
     
     private void setFileToImport(String strFile, String strTable, boolean hasDbCompanyId)
     {
      //boolean boolReturn=false;
      if(!strFile.endsWith(".xls"))
      {
          utilsGui.showMessageError("Παρακαλώ επιλέξτε αρχείο με κατάληξη .xls.");
      }
      else
      {
          	 WindowWait wwe = new WindowWait("παρακαλω περιμένετε, εισαγωγή",WINDOW_LOCATION_CENTER,ICO_RELOAD16, ICO_RELOADB16);
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
               
         int countRows=0;
         try
         {
        try
        {
        File inputWorkbook = new File(strFile);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            // Get the first sheet
            Sheet sheet = w.getSheet(0);
            // Loop over first 10 column and lines
                 

                 db.transactionLoadConnection();
                 db.setTransactionAutoCommit(false); 
                
            //ArrayList record = new ArrayList()
            for (int j = 0; j < sheet.getRows(); j++) 
            {
                if(j!=0)// j is the header
                {
                
              String subQueryColumns = "";
              String subQueryValues = "";
                boolean hasDbCompanyId = false;
   
    
                for (int i = 0; i < sheet.getColumns(); i++)
                {
                 Cell cellColumnHeader = sheet.getCell(i, 0);
                CellType typeColumnHeader = cellColumnHeader.getType();
                
               /* for(int sl = 0 ;sl<lstSpreadsheetColumns.size();sl++)
                {
               String strLstColumnHeader = lstSpreadsheetColumns.get(sl).toString();
               //lstSpreadsheetColumnsType.add(typeFirstDataCaption);
               if(cellColumnHeader.getContents().equalsIgnoreCase(strLstColumnHeader))
               {
                System.out.println("I got a column "+i+" "+sl+"  "+ cellColumnHeader.getContents()+"="+strLstColumnHeader);    
               }
                } */               
                
                 
                 boolean iColumnEmpty = isColumnHeaderLike("",sheet); //when sheet 1st page is changed, change this
                 boolean isColumnAutoInc = isColumnHeaderLike(fieldNameAutoInc,sheet); //when sheet 1st page is changed, change this
                // when empty or field name is autoinc. (autoinc is set in showSelectedFieldsToImport), also remember to set -1 in commas

                

                Cell cell = sheet.getCell(i, j);
                CellType type = cell.getType();
                if(cellColumnHeader.getContents().equalsIgnoreCase("") || cellColumnHeader.getContents().equalsIgnoreCase(fieldNameAutoInc) )    
                {
                   
                }
                else
                {

                    if (type == CellType.LABEL) 
                    {
                        String val = cell.getContents();
                        if(val.equalsIgnoreCase(""))
                        {
                        subQueryColumns = subQueryColumns + " "+cellColumnHeader.getContents();
                        subQueryValues = subQueryValues + "  null ";                       
                        }
                        else
                        {
                        //System.out.println("I got a label "+ cell.getContents());
                        subQueryColumns = subQueryColumns + " "+cellColumnHeader.getContents();
                        subQueryValues = subQueryValues + " '"+cell.getContents()+"'";
                        }
                        //subQueryWithValues = subQueryWithValues++" = '"+cell.getContents()+"'";
                    }
                    else if (type == CellType.NUMBER)
                    {
                        //System.out.println("I got a number "+ cell.getContents());
                         if(cell.getContents().equalsIgnoreCase(""))
                         {
                         subQueryColumns = subQueryColumns + " "+cellColumnHeader.getContents();
                         subQueryValues = subQueryValues + " 0 ";                             
                         }
                         else
                         {
                         subQueryColumns = subQueryColumns + " "+cellColumnHeader.getContents();
                         subQueryValues = subQueryValues + " "+cell.getContents()+"";                        
                         }
                        //subQueryWithValues = subQueryWithValues+" "+cellColumnHeader.getContents()+" = "+cell.getContents()+"";
                    }                 
                    else
                    {
                        String val = cell.getContents();
                        if(val.equalsIgnoreCase(""))
                        {
                        subQueryColumns = subQueryColumns + " "+cellColumnHeader.getContents();
                        subQueryValues = subQueryValues + "  null ";                            
                        }
                        else
                        {
                        //System.out.println("I got a label "+ cell.getContents());
                        subQueryColumns = subQueryColumns + " "+cellColumnHeader.getContents();
                        subQueryValues = subQueryValues + " '"+cell.getContents()+"'";
                        }
                        // subQueryWithValues = subQueryWithValues+" "+cellColumnHeader.getContents()+" = '"+cell.getContents()+"'";
                    }
                   // }
                   
                   System.out.println("PanelDataImportExport.setFileToImport "+sheet.getColumns()+"     "+i+"  "+cellColumnHeader.getContents()+"  "+fieldNameAutoInc);
                   if(i>=sheet.getColumns()-1) 
                   {
                       
                   }
                   else
                   {
                       subQueryColumns = subQueryColumns+", ";
                    subQueryValues=subQueryValues+", ";
                   }
                    
                }// when there is no value
                }//for cols
                
                String query =  "INSERT INTO "+strTable+"  ("+subQueryColumns+") VALUES ("+subQueryValues+")";
                System.out.println("PanelDataImportExport.fileImport   "+query);
                           int retCount =   db.transactionUpdateQuery(query,"PanelDataImportExport.fileImport",true);
                            if(retCount!=1)
                            {
                                listImportFooterModel.addElement("γραμμή "+j+" :  δεν έγινε αντιγραφή στη βάση");
                            }         
                    wwe.setComment("γραμμή: "+j);
                    countRows=j;
                
                    
            }//  j!=0
            }// for rows 
        } catch (BiffException e) {
            e.printStackTrace();
        }                      
             }
             catch (IOException e)
            {
                e.printStackTrace();
            }                      
         
        db.transactionCommit();
          }
          catch (SQLException e)
          {
                           System.out.println("  error PanelDataImportExport.setFileToImport "+e.getErrorCode()+"  "+e.getMessage());
                           listImportFooterModel.addElement(e.getMessage());
                           db.transactionRollback();
                           e.printStackTrace();
           }
           finally
           {
                         db.transactionClose();            
           }        
                      
                      
                      
              listImportFooterModel.addElement("εισάχθηκαν "+countRows+" γραμμές στο "+strTable);        
                      
                     
         
         
               wwe.close();
      // thread = null;
        
    
        //boolReturn = true;


                    }
                   });
              thread.start();               
    	

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
     private void setTableToExport(String strTable,EntityDBFields[] edbfExport, boolean hasDbCompanyId)
     {
      //boolean boolReturn=false;
      if(!strTable.equalsIgnoreCase(""))
      {
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
       String sql="";
       if(hasDbCompanyId)
       {
           sql = "SELECT * FROM "+strTable+" WHERE "+STRFIELD_DBCOMPANYID+" LIKE "+VariablesGlobal.globalCompanyId;
       }
       else
       {
          sql = "SELECT * FROM "+strTable;   
       }
        
        db.retrieveDBDataFromQuery(sql, "PanelDataImportExport.fileExport");
        ResultSetMetaData rsmd = db.getRSMetaData();
        ResultSet rs = db.getRS();
        try
        {
        int countRows=0;
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
            countRows=row-1;// one is the header
        
        }
        
        
        listExportFooterModel.addElement("εξήχθησαν "+countRows+" γραμμές από "+strTable); 
        
        }
        catch(SQLException e)
        {
                   wwe.close();
     
            System.out.println("PanelDataImportExport.fileExport    SQLException:"+e.getErrorCode()+"  "+e.getMessage());
           e.printStackTrace();
        }
        finally
        {
                db.releaseConnectionRs();
                db.releaseConnectionRsmd();
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
     // return boolReturn;
         
     }
     
      private String getSpreadsheetColumnTitle(String columnName)
      {
          String spreadsheetColumn="";
          if(lstSpreadsheetColumns.size()>0)
          {
           
            for(int l = 0;l<lstSpreadsheetColumns.size();l++)
            {
                String clmn = lstSpreadsheetColumns.get(l)+"";
                if(columnName.equalsIgnoreCase(clmn))
                {
                   spreadsheetColumn = columnName;
                }
              
            }
          }
          else
          {
          }
          

          
          
          return spreadsheetColumn;
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
