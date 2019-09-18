// created 10-4-2009
package com.tool.rpt;

import com.tool.guicomps.*;
import com.tool.utils.*;
import com.tool.gui.*;
import com.tool.jdbc.*;
import com.tool.model.*;

import javax.swing.*;
import java.awt.print.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;  
import java.util.*;  
import java.io.*;


 public class ReportAreaForm extends JPanel implements Constants, Printable
 {
    private String strPrintDotMatrix;
    private String strPrintLaser;
    private JPanel panelArea;
    private PageFormat pageFormat;
    private UtilsGui utilsGui;
    private UtilsString utilsString;
    private UtilsOS utilsOS;
    private String strSpace=" ";
    private String strField=">";
     private Database db; 
     private String text = "";
     
    private ResultSet rs;
    private ResultSetMetaData rsmd;     
    private  String tableEnd ="";
    private  String tableStart =""; //+3
    private  int intTableStartPlusLength = 0;
    private  int totalLengthOfTableStart = 0;
    private String textFromFile="";
    private String nameOfForm;
    //private String query;
    
    private String dirTemplateReports;
    private UtilsDate utilsDate;
    private UtilsDouble utilsDouble;
 
    private final String STR_VERBAL_FUNCTION_MONEY = "/verbalm"; // m at the end means money
    //  d decimal, h hundrend,t thousant, m milions
    private final String STR_VERBAL_FUNCTION_MONEY_DEC = "/verbalmd"; // m at the end means money
    private final String STR_VERBAL_FUNCTION_MONEY_HUN = "/verbalmh"; // m at the end means money
    private final String STR_VERBAL_FUNCTION_MONEY_THO = "/verbalmt"; // m at the end means money
    private final String STR_VERBAL_FUNCTION_MONEY_MIL = "/verbalmm"; // m at the end means money
    private final String STR_VERBAL_FUNCTION_MONEY_BIL = "/verbalmb"; // m at the end means money
    private final String STR_VERBAL_FUNCTION = "/verbal"; // no m at the end means just number
    
    //private  int NUMBER_OF_FORMROWS_PER_PAGE = 5;
    
    private String[] arrayOfNameOfPksOfRecordToShow;
    private String[] arrayOfValueOfPksOfRecordToShow;
    
    private JLabel labelHtml; 
    

   //private  int changesOfHeaderRecord=0;
    
    public ReportAreaForm()  //    usually an html form
    {
            try
           {    initialize();    }
           catch (Exception e)
           {   e.printStackTrace();    }
    }

	private void initialize() throws Exception
    {  
       
    	 db = new Database();
    	utilsDate = new UtilsDate();
    	utilsDate.readFromFileDateFormats();
    	utilsDouble = new UtilsDouble();
    	utilsDouble = VariablesGlobal.globalUtilsDouble;
    	utilsGui = new UtilsGui();
    	utilsString = new UtilsString();
    	utilsOS = new UtilsOS();
    	dirTemplateReports = VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+DIR_REPORTDOCUMENTTEMPLATE;
        panelArea = new JPanel();
        panelArea.setLayout(new BorderLayout());
        this.add(panelArea);
        labelHtml = new JLabel(); 
        labelHtml.setBackground(Color.WHITE);
      
        labelHtml.setOpaque(true);        
        
        
        
        
        panelArea.add(labelHtml,BorderLayout.CENTER);
    }
    
        
   private String getFormDataString(boolean isDotmatrix, String formQuery, String formField)     
   {
       String ret = "";
       if(isDotmatrix)
       {
           ret = processAndGetFileFormDotMatrix();
       }
       else
       {
           ret = processAndGetFormLaserFromDB(formQuery, formField);    //processAndGetFileFormLaser();
           
       }       
       
       return ret;
   }
        
        
   /*
   *
   *   sql comes from PanelPrintPreview.fetchData
   */
    //called by ReportPanelPage.generateMain
   public boolean setEntity(String nameOfFormIn,EntityReport entityReportIn,boolean isDotmatrix,int pageIn, ArrayList listOfListOfColumns,ArrayList listRecords, 
        String[] arrayOfNameOfPksOfRecordToShowIn,String[] arrayOfValueOfPksOfRecordToShowIn, String formQuery, String formField)
   {
         boolean ret = false;
       
   	 nameOfForm=nameOfFormIn;
         
         arrayOfNameOfPksOfRecordToShow = arrayOfNameOfPksOfRecordToShowIn;
         arrayOfValueOfPksOfRecordToShow =arrayOfValueOfPksOfRecordToShowIn;

         String strForm = getFormDataString(isDotmatrix, formQuery, formField);

       String strFirstField = "";
       
       String strTable = "";
         //String strPrintLaserTableRowToBeChanged = "";

         String lines = "";
         ArrayList listHtmlPages = new ArrayList();// the text
         ArrayList listHtmlPagesNumber = new ArrayList();// the r number         
         
  //System.out.println("ReportAreaForm.setEntity  -o-  isDotmatrix:"+isDotmatrix+"    nameOfFormIn:"+nameOfFormIn +"     pageIn:"+pageIn+"        listRecords.size:"+listRecords.size());
           if(arrayOfNameOfPksOfRecordToShow!=null)// when one print form
           {
               ret = generateForm( nameOfFormIn, isDotmatrix, pageIn, listOfListOfColumns, listRecords, strForm,NUMBER_OF_FORMROWS_PER_PAGE);
               System.out.println("  ===== IF ReportAreaForm.setEntity      entityReportIn:"+entityReportIn+" (only one rec form)");   // only one, else mass form printing // only one, else mass form printing
           }
           else// when mass print of form  //exists with modifications in PanelPrintPreniew.getPageTotal and in  ReportAreaForm.setEntity
           {      

             
              String lastVal = "";
           String previousVal = ""; // setEntity
           int countOfTableRows =1;// setEntity   
           int changesOfHeaderRecord=0;
            for(int r =0 ;r<listRecords.size();r++)
            {  
                
                EntityReportRow entityReportRow = (EntityReportRow)listRecords.get(r);
                 Object[] arrayRecValues = entityReportRow.getFieldValues();
                    
           RecColumn[] arrayRecColumn = (RecColumn[])listOfListOfColumns.get(entityReportRow.getIntOfEntityReportBand());
            for(int c= 0;c<arrayRecColumn.length;c++)
            {
                 RecColumn col = (RecColumn)arrayRecColumn[c];
                 int columnWidth=  col.getColumnLength();//getColumnLengthMaxActual(); //intSumPagePreLength[i];
                 String colClass= col.getColumnClass();	
                 String columnName = col.getColumnName();
                 String columnCaption = col.getColumnCaption();
                 String columnTable = col.getColumnTable();
                 boolean colIsPK = col.getColumnIsPK();
  
               String   val =  (String)arrayRecValues[c];                
                
               EntityReportBand erb = entityReportRow.getEntityReportBand();
                 String groupByField = erb.getSqlGroupByField();  
                  EntityReportBandField [] erbf=erb.getEntityReportBandFields();
                  int fieldCount = erbf.length;
                      groupByField=erb.getTableName()+"."+groupByField;
                     if(groupByField.equalsIgnoreCase(columnTable+"."+columnName) )// && val.equalsIgnoreCase(pageIn+""))
                     {
              System.out.println(" ===r===    ReportAreaForm.setEntity   pageIn:"+pageIn+"   r:"+r+"   c:"+c+"      "+columnTable+"."+columnName+"  = "+val+"  previousVal:"+previousVal+"     lastVal:"+lastVal+"       group by field:"+groupByField+"   countOfTableRows:"+countOfTableRows);
                        if(lastVal.equalsIgnoreCase(val))
                        {
                             countOfTableRows++;//is OK
                           
                        }
                        else
                        {
                          // countOfTableRows=1;
                            lastVal = val; 
                            changesOfHeaderRecord++;
                          
                        }
      //                  System.out.println(" ===r===    ReportAreaForm.setEntity   pageIn:"+pageIn+"   r:"+r+"   c:"+c+"      "+columnTable+"."+columnName+"  = "+val+"      lastVal:"+lastVal+"       group by field:"+groupByField+"   countOfTableRows:"+countOfTableRows);
                     }
                 // System.out.println(" ===== ELSE   ReportAreaForm.setEntity  r:"+r+"  band no:"+entityReportRow.getRecordIntNoOfBand()+"    "+columnTable+"  "+columnName+"  :"+val+"    group by field:"+ erb.getSqlGroupByField());
              }


              if(changesOfHeaderRecord == pageIn)
              {
                 System.out.println(" ===    ReportAreaForm.setEntity     changesOfHeaderRecord:"+changesOfHeaderRecord+" =  pageIn:"+pageIn+"   countOfTableRows:"+countOfTableRows);
                 ret = generateForm( nameOfFormIn, isDotmatrix, pageIn, listOfListOfColumns, listRecords, strForm,countOfTableRows);                                   
                 //countOfTableRows=1;
                 //break;
              }
              else
              {
                  countOfTableRows=1;
                  //System.out.println(" ===    ReportAreaForm.setEntity  ELSE   changesOfHeaderRecord:"+changesOfHeaderRecord+"   pageIn:"+pageIn+"   countOfTableRows:"+countOfTableRows);
                   //break;
              }
             
            }
           // System.out.println(" ===  ReportAreaForm.setEntity     changesOfHeaderRecord:"+changesOfHeaderRecord+"   pageIn:"+pageIn+"   countOfTableRows:"+countOfTableRows);
            //  ret = generateForm( nameOfFormIn, isDotmatrix, pageIn, listOfListOfColumns, listRecords, strForm,countOfTableRows);                                   
          
            //System.out.println("     =    ReportAreaForm.setEntity       pageIn:"+pageIn+"  changesOfHeaderRecord:"+changesOfHeaderRecord+"   countOfTableRows:"+countOfTableRows);
              // ret = generateForm( nameOfFormIn, isDotmatrix, pageIn, listOfListOfColumns, listRecords, strForm,countOfTableRows/*NUMBER_OF_FORMROWS_PER_PAGE*/);                                   
           }
  
        return ret;
   }
   

   
    private boolean generateForm(String nameOfFormIn,boolean isDotmatrix,int pageIn,ArrayList listOfListOfColumns,ArrayList listRecords,String strForm, int noOfFormRowsPerPage )
    {
        boolean ret = false;
    
        
         String strRowSubstring="";
         String strRowSubstringBefore = "";
         String strRowSubstringAfter  ="";
         //String strRowSubstringInitial="";
        String strHtmlPage = "";
    if(isDotmatrix==true)
    {
        
       if(strForm.trim().equalsIgnoreCase(""))
       {
           ret = false;
       }   
       else
       {        
           ret = true;
        System.out.println("ReportAreaForm.setEntity  isDotmatrix:"+isDotmatrix+"  nameOfFormIn:"+nameOfFormIn +"             arrayOfNameOfPksOfRecordToShow:"+arrayOfNameOfPksOfRecordToShow);
       }          
    }
    else // if is laser
    {
        

    if(strForm.equalsIgnoreCase(""))
    {
        ret = false;
    }   
    else // if has text in laser
    {
  
        strPrintLaser = strForm;

         ArrayList listHtmlLines  = new ArrayList();// the r number
        
         int intFrom =  1;//((r+1));
         int intTo =intFrom+noOfFormRowsPerPage;
        
          //String strPrintLaserTableRowChanged = "";
          ArrayList listPrintLaserTableRowsChanged = new ArrayList();
          String strPrintLaserTableRowsChanged = "";
      int intCountOfPages = 1;
      
       String strLaserReplaced="";
       for(int r =0 ;r<listRecords.size();r++)
       {       
         
            String strTableStartHTML = "<tr>\n<td>"; // not use it. does not work
            String strTableStartText = "#@";
            String strTableEndHTML = "</tr>";
          
           
 
            int idxOfTableRowStart = strPrintLaser.indexOf(strTableStartText);
            int idxOfTableRowEnd = strPrintLaser.indexOf(strTableEndHTML,idxOfTableRowStart);   
            
            if(idxOfTableRowStart==-1)// form without table lines
            {
                
                
             EntityReportRow entityReportRow = (EntityReportRow)listRecords.get(r);
             Object[] arrayRecValues = entityReportRow.getFieldValues();
              //String strTableRow = "";         
           RecColumn[] arrayRecColumn = (RecColumn[])listOfListOfColumns.get(entityReportRow.getIntOfEntityReportBand());
            for(int c= 0;c<arrayRecColumn.length;c++)
            {
                 RecColumn col = (RecColumn)arrayRecColumn[c];
                 int columnWidth=  col.getColumnLength();//getColumnLengthMaxActual(); //intSumPagePreLength[i];
                 String colClass= col.getColumnClass();	
                 String columnName = col.getColumnName();
                 String columnCaption = col.getColumnCaption();
                 String columnTable = col.getColumnTable();
                 
            
          String   val =  (String)arrayRecValues[c];
          
              
      //System.out.println("ReportAreaForm.setEntity   ++++++++    c:"+c+"   r:"+r+"         arrayRecColumn.length:"+arrayRecColumn.length+"    column:"+columnTable+"."+columnName+"="+val);  
                
        if(val!=null)  
        {
            
           String tobeChanged = "#"+columnTable+"."+columnName;
           //String tobeChangedHTML = tobeChanged;   // "<td>"+tobeChanged+"</td>";   

             //String tobeChangedFieldFromTable = strTableStartText+columnTable+"."+columnName;
               //strRowSubstring = strRowSubstring.replaceAll(tobeChangedFieldFromTable, val);  
               //          strPrintLaser= strPrintLaser.replaceAll(tobeChangedHTML, val);
                         strPrintLaser = strPrintLaser.replaceAll("(?i)" + tobeChanged, val);     // "(?i)" +    for case insensitive           
               // System.out.println(tobeChanged);
                
        }       
            }      
                
               
                strHtmlPage = strPrintLaser;
                //int totalOfPages = getTotalPages(listRecords.size());
                
            }
            else// form with table lines
            {
            
            
                    //  int indexStart = indexTableStart+strPrintLaser.length()-textFromFile.length();	        
                    //  int indexAfterStart=indexAfterTableStart+text.length()-textFromFile.length();
                    //  int indexEnd=indexTableEnd+text.length()-textFromFile.length();  
          
   
          
 //System.out.println("ReportAreaForm.setEntity   -oooooo-  r:"+r+"           page:"+page+"    idxOfTableRowStart:"+idxOfTableRowStart+"    idxOfTableRowEnd:"+idxOfTableRowEnd);          
          
          strRowSubstring =   strPrintLaser.substring(idxOfTableRowStart, idxOfTableRowEnd);
         
        strRowSubstringBefore = strPrintLaser.substring(0,idxOfTableRowStart);
        strRowSubstringAfter = strPrintLaser.substring(idxOfTableRowEnd, strPrintLaser.length());
                   
 
           
             EntityReportRow entityReportRow = (EntityReportRow)listRecords.get(r);
             Object[] arrayRecValues = entityReportRow.getFieldValues();
              //String strTableRow = "";         
           RecColumn[] arrayRecColumn = (RecColumn[])listOfListOfColumns.get(entityReportRow.getIntOfEntityReportBand());
            for(int c= 0;c<arrayRecColumn.length;c++)
            {
                 RecColumn col = (RecColumn)arrayRecColumn[c];
                 int columnWidth=  col.getColumnLength();//getColumnLengthMaxActual(); //intSumPagePreLength[i];
                 String colClass= col.getColumnClass();	
                 String columnName = col.getColumnName();
                 String columnCaption = col.getColumnCaption();
                 String columnTable = col.getColumnTable();
                 

          String   val =  (String)arrayRecValues[c];
              
      //System.out.println("ReportAreaForm.setEntity   ++++++++    c:"+c+"   r:"+r+"         arrayRecColumn.length:"+arrayRecColumn.length+"    column:"+columnTable+"."+columnName+"="+val);  
                
        if(val!=null)  
        {
           String tobeChanged = "#"+columnTable+"."+columnName;
           String tobeChangedHTML = tobeChanged;   // "<td>"+tobeChanged+"</td>";   
             String tobeChangedFieldFromTable = strTableStartText+columnTable+"."+columnName;

                //strRowSubstring = strRowSubstring.replaceAll(tobeChangedFieldFromTable, val);  

                         strRowSubstringBefore = strRowSubstringBefore.replaceAll("(?i)" + tobeChangedHTML, val);     // "(?i)" +    for case insensitive    
                         strRowSubstringAfter = strRowSubstringAfter.replaceAll("(?i)" + tobeChangedHTML, val);
                
                         strRowSubstring=strRowSubstring.replaceAll("(?i)" + tobeChangedFieldFromTable, val);
          }// if
        
            }//c   

              listHtmlLines.add(strRowSubstring+"</tr>");
          //    lines = lines+strRowSubstring+"</tr>";           
   

           
            //int totalOfPages = getTotalPages(listRecords.size());
            int intPge = getRowIsInPage(r,noOfFormRowsPerPage);           
           
            intFrom =  (noOfFormRowsPerPage*intPge)-noOfFormRowsPerPage;

            intTo = intFrom+noOfFormRowsPerPage;//noOfFormRowsPerPage*(intCountOfPages);

         //    System.out.println("ReportAreaForm.setEntity   intFrom:"+intFrom+"   intTo:"+intTo+"     r:"+r+"       noOfFormRowsPerPage:"+noOfFormRowsPerPage+"      each record");
           String strLinesOfAPage = "";   

            if(pageIn==intPge)
            {
                for(int l= 0;l<listHtmlLines.size();l++)   
                {
                    if(l>=intFrom && l<intTo) //listRecords.size() == r+1 )
                    {
                     //System.out.println("ReportAreaForm.setEntity FOR  IF     --r:"+r+"    l:"+l+"  intFrom"+intFrom+"  listHtmlLines.size:"+listHtmlLines.size()+"  pageIn:"+pageIn);
                       strLinesOfAPage = strLinesOfAPage + listHtmlLines.get(l); 
                       intCountOfPages++; 
                    }
                   
                }

        //   System.out.println("ReportAreaForm.setEntity    r:"+r+"   pageIn:"+pageIn+"  in page:"+getTotalPages(r)+"  listHtmlPages.size:"+listHtmlPages.size()+"  intPge:"+intPge+"    intCountOfPages:"+intCountOfPages+"  listRecords.size:"+listRecords.size()+"  totalOfPages:"+totalOfPages);
             strHtmlPage = strRowSubstringBefore+strLinesOfAPage+strRowSubstringAfter;
                               
          }            

            }// else for wrong html or no htmal
            
       }// r  

       
       
                     
         String strAll="";     

strAll = strHtmlPage;
      strPrintLaser =   "<html>"
             
              +strAll
              +"</html>";  
       
     
 



        ret = true;
        labelHtml.setText(strPrintLaser);
        //System.out.println("ReportAreaForm.setEntity   strPrintLaser:"+strPrintLaser);
        // if has text in laser 
     }
      // is laser
    }
        
   
       
     return ret;
   }

   private int getRowIsInPage(int rowCount, int noOfFormRowsPerPage)
   {
   	  // reportPanel.getPageTotal();
   	  int pages = 0;
   	  
 
   	
   	     double dbRecords = rowCount+1;
             pages = (int)(Math.ceil(dbRecords / noOfFormRowsPerPage)); //a value has to be double in order ceil to work. 
             //System.out.println("getPageTotal  pages:"+pages+"   "+dbRecords+"/"+noOfFormRowsPerPage+"  ceil:"+Math.ceil(dbRecords / noOfFormRowsPerPage));
             //Math.ceil((double)dbRecords/153);

   	  
   	  return pages;
   }   
   
   


  /*
   *
   *   sql comes from PanelPrintPreview.fetchData
   *   to be deprecated
   */
    //called by ReportPanelPage.generateMain
/*   public void setEntityOLD(String nameOfFormIn,boolean isDotmatrix,int page,  String queryIn,  ArrayList listOfListOfColumns,ArrayList listRecords,
                String[] arrayOfNameOfPksOfRecordToShowIn,String[] arrayOfValueOfPksOfRecordToShowIn)
   {
   	 nameOfForm=nameOfFormIn;
         
         arrayOfNameOfPksOfRecordToShow = arrayOfNameOfPksOfRecordToShowIn;
         arrayOfValueOfPksOfRecordToShow =arrayOfValueOfPksOfRecordToShowIn;
         
         query=queryIn;
   	//ArrayList listOfListOfColumns=new ArrayList();
   	//ArrayList listRecords=new ArrayList();
   	  strPrintDotMatrix="";
   	  //ArrayList listReportFields;
   	  ArrayList listLineString= new ArrayList();
   	  ArrayList listStringField= new ArrayList();
   	  
   	  String line ="";
   	  
   	  System.out.println("ReportAreaForm.setEntity    nameOfFormIn:"+nameOfFormIn +"   listRecords:"+listRecords.size());
   	  
   	    
         // EntityReportBand[] erb =entityReport.getEntityReportBands();// primKeyName should be taken from the band
  //        String primKeyName = 
  //      String primKeyTable = entityReport.getName();//.getEntity();//get.getPrimKeyTable();

         ArrayList listOfColumns = new ArrayList();
//       listOfColumns = (ArrayList)listOfListOfColumns.get(0);// 2 is the master 0 is the header 1 is the footer
         int columnCount = 0;// recColumn.length;
    //     RecColumn[] arrayRecColumn = new RecColumn[listOfListOfColumns.size()];
         for(int b = 0; b<listOfListOfColumns.size(); b++)
         {
             // look at ReportCalcs.fetchDataBandColumnTitle
//             EntityReportBandField[] erbf = entityReportBand[b].getEntityReportBandFields();
           RecColumn[] arrayRecColumn = (RecColumn[])listOfListOfColumns.get(b);
            for(int c= 0;c<arrayRecColumn.length;c++)
            {
                
       	
                 RecColumn col = (RecColumn)arrayRecColumn[c];
                 int columnWidth=  col.getColumnLength();//getColumnLengthMaxActual(); //intSumPagePreLength[i];
                 String colClass= col.getColumnClass();	
                 String columnName = col.getColumnName();
                 String columnCaption = col.getColumnCaption();
                 String columnTable = col.getColumnTable();
            //boolean isColumnToBeShown =  getIfColumnIsToBeShown(c,page,b,col);//,arrayOfColumns.length,AREA_HEADER);                     
                
                
                RecColumn recCol ;//= new RecColumn();
                recCol = new RecColumn(columnName,columnCaption,0,columnTable,colClass,columnWidth,0,false);
                listOfColumns.add(recCol);
                columnCount = listOfColumns.size();
               
                
             EntityReportRow entityReportRow = (EntityReportRow)listRecords.get(b);//page-1);
             Object[] arrayRecValues = entityReportRow.getFieldValues();
             String val =  (String)arrayRecValues[c];
              
             System.out.println("ReportAreaForm.setEntity   ++++++++  b:"+b+"  c:"+c+"  columnCount:"+columnCount+"   column:"+columnTable+"."+columnName+"="+val);  
                
             
             for(int p=0;p<arrayOfNameOfPksOfRecordToShow.length;p++)
             {
                if((columnTable+"."+columnName).equalsIgnoreCase(arrayOfNameOfPksOfRecordToShow[p]))
                {
                    System.out.println("ReportAreaForm.setEntity    ++++++++   PK column:"+columnTable+"."+columnName+"="+val); 
                }
                else
                {
                    
                }
                    
                
             }
             
            }  //c
             
             
             //columnCount= columnCount+
         }// b

         // 2 is the master 0 is the header 1 is the footer


         

         
//         int columnCount =  listOfColumns.size();
        //System.out.println("ReportAreaForm.setEntity   ++++++++   columnCount:"+columnCount);


     //for(int r =0 ; r<listRecords.size(); r++)// go to next record
     //{
         //System.out.println("bbbbb r"+r);
        String ln="";   
         String columnName=""; 
         String columnTable=""; 
         String colValue=""; 
         String primKeyValue="";
         // for each field in rs
        // String textToBeReplaced=strField+columnCaption+"[*]*"; //+",\\d\\d,[l,r,c]"; 
 

//            perhaps should be enabled in order the report to be calculated correctly
 
         for (int i = 0; i < columnCount; i++)//  i = fields
         {
       	   //System.out.println("bbbbb  i"+i);
                 RecColumn recColumn = (RecColumn)listOfColumns.get(i);
                 
                 int columnWidth=  recColumn.getColumnLength(); //intSumPagePreLength[i];
                 String colClass= recColumn.getColumnClass();	
                 columnName = recColumn.getColumnName();
                 columnTable=recColumn.getColumnTable();
                 //columnCaption = columnCaption.toLowerCase();
                  
        //System.out.println("ReportAreaForm.setEntity "+listRecords.size());//+columnName);                    

             //ArrayList lstrec = (ArrayList)listRecords.get(page-1);
             EntityReportRow entityReportRow = (EntityReportRow)listRecords.get(page-1);
             Object[] arrayRecValues = entityReportRow.getFieldValues(); 
          
         
         
         }   
            // RecColumn[] col = (RecColumn[])listOfListOfColumns.get(0);
             //RecColumn col = (RecColumn)arrayOfColumns[page-1];
           
                //  for each record
            	  
   	  

   	  
   	  System.out.println("ReportAreaForm.setEntity        query:"+query);
   	  
   	    db.retrieveDBDataFromQuery(query,"ReportAreaForm.setEntity");
   	    rs=db.getRS();
   	    rsmd=db.getRSMetaData(); 
   	          //rs = db.retrieveResultSet(query);
              //rsmd = db.retrieveRSMetaData(query);
              int rsmdColumnCount = db.getRecordCount();
   	  //System.out.println("ReportAreaForm.setEntity rs"+rs);
   	   ArrayList listTableColCaption = new ArrayList();
       	
       ArrayList listTableRecord= new ArrayList();   	  
         
      try
       {
       	rsmdColumnCount = rsmd.getColumnCount();
       	rs.beforeFirst();
       	
    	
    	 
    	 //System.out.println("ReportAreaForm.setEntity rsmdColumnCount"+rsmdColumnCount);
    	 //System.out.println("ReportAreaForm.setEntity rows ");
         for (int i = 1; i <= rsmdColumnCount; i++)//  i = fields
         {
       	          String colCaption = rsmd.getColumnLabel(i);
       	          String colName = rsmd.getColumnName(i);
                  int colType = rsmd.getColumnType(i);// class
                  String colClass = rsmd.getColumnClassName(i);// class
                  //System.out.println("ReportCalcs.fetchDataMaster "+colClass);
                  int colLength = rsmd.getColumnDisplaySize(i);
                  //listColumnMaster.add(recColumn = new RecColumn(colName,colCaption,colType,colClass,colLength));
               System.out.println("ReportAreaForm.setEntity  rsmd   i"+i+"       colCaption:"+colCaption+"        rsmdColumnCount:"+rsmdColumnCount);
 //               listOfListOfColumns.add(colCaption);
                 listTableColCaption.add(colCaption);
       	 }   	  
   	     int row=1;
       	  while ( rs.next() )
          {
             ArrayList listTableField= new ArrayList();
          	 for (int i = 1; i <= rsmdColumnCount; i++)//  i = fields
             {      
                  int colType = rsmd.getColumnType(i);// class
                  String colClass = rsmd.getColumnClassName(i);// class               	 
               
               	String columnValue="";
               	if(colClass.equalsIgnoreCase("java.sql.Date") || colClass.equalsIgnoreCase("java.lang.Date")  )
               	{
               		columnValue = utilsDate.reformatDateStringToReadFromDB(rs.getString(i));
               	}
               	else if (colClass.equalsIgnoreCase("java.lang.Double")|| colClass.equalsIgnoreCase("java.math.BigDecimal") )
               	{
               		 columnValue = utilsDouble.getDoubleReading(rs.getString(i),false);
               	}
               	else if(colClass.equalsIgnoreCase("java.lang.String"))
               	{
               	    columnValue = rs.getString(i);    
               	}
               	else if(colClass.equalsIgnoreCase("java.lang.Integer"))
               	{
               		columnValue = rs.getString(i);
               	}
                else if(colClass.equalsIgnoreCase("java.lang.Long"))
                {
                    columnValue = rs.getString(i); 
                }
                else if(colClass.equalsIgnoreCase("java.lang.Boolean"))
                {
                    columnValue = rs.getString(i); 
                }                
               	else
               	{
               		System.out.println("ReportAreaForm.setEntity column ("+i+") "+rsmd.getColumnLabel(i)+" "+columnValue+" "+colClass+" NOT supported");      
               		columnValue = rs.getString(i); 
               	}
               	
               	//System.out.println("ReportAreaForm.setEntity row "+row+" "+columnValue+" "+colClass);      

          	    listTableField.add(columnValue);
          	   //colValue.toString();
 //         	   listField.add(rs.getString(i));
          	 }
            listTableRecord.add(listTableField);
            //  System.out.println("ReportAreaForm.setEntity row "+row);
               row++;
              
   	      }
      }// try sql
      catch ( SQLException sqlex)
      {
         System.out.println("error:ReportAreaForm.setEntity: sql"+sqlex.getMessage());
      }
      
   	  ArrayList listFilesInZip = new ArrayList();
   	  
       String zipFile = dirTemplateReports+VariablesGlobal.globalSystemDirectorySymbol+nameOfForm+TEMPLATE_REPORTFORMFILETYPE;   
          
    //String dirZipFile = nameOfForm+TEMPLATE_REPORTFORMFILETYPE;
    File file=new File(zipFile);
    boolean exists = file.exists();
    if (!exists)
    {
      // It returns false if File or directory does not exist
        System.out.println("PanelReportFormDesign.formOpen file not found "+zipFile);
        //utilsGui.showMessageInfo("Δεν βρέθηκε το αρχείο: \n"+zipFile+".");
        
    }
    else
    {             
          try
   	  {
   	  
   	  //FileInputStream in = new FileInputStream();
      
      
      //System.out.println("ReportAreaForm.setEntity zipFile "+zipFile);
      // listFilesInZip = utilsOS.unzipFile(zipFile, dirTemplateReports);
      
      
      String reportfileDotmatrix = FILE_REPORTFORMTEMPLATE_DOTMATRIX;//VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+DIR_REPORTFORMTEMPLATE+VariablesGlobal.globalSystemDirectorySymbol+nameOfForm+FILE_REPORTFORMTEMPLATE_DOTMATRIX;
      //String reportfileLaser = FILE_REPORTFORMTEMPLATE_LASER;
      
       //BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(reportfile)));//fileName),
       
       BufferedReader in = utilsOS.getFileFromZip(reportfileDotmatrix,zipFile);
                       while((line = in.readLine()) != null)
                       {
        	               listLineString.add(line);
        	               if(line.indexOf("<")!=-1)
        	               {
        	                 listStringField.add(line);	
        	               }
        	               
                            text= text+line+"\n"; //TXT_CHANGELINE;// "\n";
                       } 
                       textFromFile=text;
                                    
      }
      catch (IOException ex)
      {
          System.err.println("ReportAreaForm.setEntity IOException: DOT MATRIX "+ex.getMessage()+"     file not found:"+VariablesGlobal.globalSystemDirectorySymbol+DIR_REPORTDOCUMENTTEMPLATE+VariablesGlobal.globalSystemDirectorySymbol+nameOfForm+FILE_REPORTFORMTEMPLATE_DOTMATRIX);
          //System.err.println(ex);
      }
    
    }
      tableEnd = "/tblend";
      tableStart ="/tblstart"; //+3
      intTableStartPlusLength = 3;
      String tableStartCode =  tableStart+",\\d\\d,";
      totalLengthOfTableStart = tableStartCode.length();//tableStart.length()+intTableStartPlusLength;
      
      ArrayList listIndexOfTableStart = new ArrayList();
      ArrayList listIndexOfTableEnd = new ArrayList();
      ArrayList listTableRowsPerPage = new ArrayList();
      
      
      listIndexOfTableStart = utilsString.getIndicesOf(text,tableStart);
      listIndexOfTableEnd = utilsString.getIndicesOf(text,tableEnd);
 
     
     for(int t =0;t<listIndexOfTableStart.size();t++)
     {
     	      int indexTableStart = Integer.valueOf(listIndexOfTableStart.get(t).toString());
     	      int indexTableEnd = Integer.valueOf(listIndexOfTableEnd.get(t).toString());
     	      
      	      String tblStart = text.substring(indexTableStart,indexTableStart+totalLengthOfTableStart);
      	      int idxOfFirstComma = tblStart.indexOf(",",0);
      	      idxOfFirstComma=idxOfFirstComma+1;
              int idxOfSecondComma = tblStart.indexOf(",",idxOfFirstComma);
              //System.out.println("table start "+idxOfFirstComma+" "+idxOfSecondComma);
              //System.out.println("table start "+tblStart.substring(idxOfFirstComma,idxOfSecondComma));
              int NUMBER_OF_FORMROWS_PER_PAGE = Integer.parseInt(tblStart.substring(idxOfFirstComma,idxOfSecondComma));
              listTableRowsPerPage.add(NUMBER_OF_FORMROWS_PER_PAGE);
              	
              //System.out.println("ReportAreaForm table start ("+t+") "+indexTableStart +"-"+indexTableEnd+" "+text.substring(indexTableStart,indexTableEnd));
     }
      
      String textTable="";
      String textLine ="";
      String textLineOfFile ="";
      String textReplaced="";
      if(listIndexOfTableStart.size()>0)// if there is table
      {
        for(int t=0;t<listIndexOfTableStart.size();t++)
        {
        int indexTableStart = (Integer.parseInt(listIndexOfTableStart.get(t).toString()));
        int NUMBER_OF_FORMROWS_PER_PAGE = Integer.parseInt(listTableRowsPerPage.get(t).toString());
        int indexAfterTableStart = indexTableStart+totalLengthOfTableStart;
        int indexTableEnd = Integer.parseInt(listIndexOfTableEnd.get(t).toString());
        if(NUMBER_OF_FORMROWS_PER_PAGE >= listTableRecord.size())
        {  // less than a page
          
              //System.out.println("ReportAreaForm table "+indexAfterTableStart+"-"+indexTableEnd);
          
              textTable = createTableForDotmatrixAndGet(text,NUMBER_OF_FORMROWS_PER_PAGE,textLine,textLineOfFile,indexAfterTableStart,indexTableEnd,listTableRecord,listTableColCaption, listStringField,  page,0, NUMBER_OF_FORMROWS_PER_PAGE);
 
              textLineOfFile= text.substring(indexTableStart,indexTableEnd);
               //System.out.println(" textLineOfFile "+textLineOfFile);
              text = utilsString.removeStringFromString(textLineOfFile,text);// delete 1st line 

                 	 //textReplaced = text.replaceFirst(tableStartCode, textTable);
                     	String textBefore = text.substring(0,indexTableStart);
                     	String subText = text.substring(indexTableStart,indexTableEnd-textLineOfFile.length());
                     	String textAfter = text.substring(indexTableEnd-textLineOfFile.length(),text.length());
           	     
           	            String textRep = textTable;//subText.replaceFirst(tableStartCode, textTable);
           	            //System.out.println("ReportAreaForm --------subtext-----"+indexTableStart+" "+indexTableEnd);
           	            //System.out.println("ReportAreaForm -------------- "+textTable+"-------");
           	            textReplaced=textBefore+textRep+textAfter;                 	 
	


        }// if(noOfRows>listTableRecord.size())
        else if (NUMBER_OF_FORMROWS_PER_PAGE<listTableRecord.size())// more than a page     
        {
           //System.out.println("ReportAreaForm  more than a page - for app "+listTableRecord.size()+"/"+NUMBER_OF_FORMROWS_PER_PAGE);
           double morePagesOfApplication = 	(double)listTableRecord.size()/NUMBER_OF_FORMROWS_PER_PAGE;
           int morePagesOfApplicationRounded =  (int)Math.ceil(morePagesOfApplication); // floor , ceil
           
        	
        	for(int p= 0;p<morePagesOfApplicationRounded;p++)
        	{
                     //System.out.println("ReportAreaForm p "+p);
                     if(p>0)
                     {
                        text = textReplaced + textFromFile; 
                     }
                     else
                     {
                         //text = textFromFile;
                     }
                     
                       //System.out.println("ReportAreaForm  - "+p+" "+indexTableStart+"-"+indexTableEnd);
                       
                      int indexStart = indexTableStart+text.length()-textFromFile.length();	        
                      int indexAfterStart=indexAfterTableStart+text.length()-textFromFile.length();
                      int indexEnd=indexTableEnd+text.length()-textFromFile.length();  
                     
                     //System.out.println("ReportAreaForm  --- "+p+" "+indexStart+"-"+indexEnd+"  "+text.length()+" "+textFromFile.length());
                     
                     int fromRec=p*NUMBER_OF_FORMROWS_PER_PAGE;
                     int toRec=(p*NUMBER_OF_FORMROWS_PER_PAGE)+NUMBER_OF_FORMROWS_PER_PAGE;
                     System.out.println("ReportAreaForm  more than a page for app -"+page+"- "+listTableRecord.size()+"/"+NUMBER_OF_FORMROWS_PER_PAGE+"="+morePagesOfApplication+" "+morePagesOfApplicationRounded+" "+fromRec+"-"+toRec);
        	         textTable = createTableForDotmatrixAndGet(text,NUMBER_OF_FORMROWS_PER_PAGE,textLine, textLineOfFile,indexAfterStart,indexEnd,listTableRecord,listTableColCaption, listStringField,  page,fromRec, toRec);

         	         //System.out.println("ReportAreaForm  --- "+p+" "+fromRec+"-"+toRec+":"+textTable);
        
                     textLineOfFile= text.substring(indexAfterStart,indexEnd);
                     //System.out.println(" textLineOfFile "+textLineOfFile);
                     text = utilsString.removeStringFromString(textLineOfFile,text);// delete 1st line 
                     //indexTableEnd=indexTableEnd-textLineOfFile.length();
                     
                     	String textBefore = "";
                     	String subText = "";
                     	String textAfter = "";                     
                     

                     	textBefore = text.substring(0,indexStart);
                     	subText = text.substring(indexStart,indexEnd-textLineOfFile.length());
                     	textAfter = text.substring(indexEnd-textLineOfFile.length(),text.length());
           	            
           	            String textRep = textTable;//subText.replaceFirst(tableStartCode, textTable);
           	            
           	            //System.out.println("ReportAreaForm replace--------- ."+p+".  ");//+indexTableStart+"-"+indexTableEnd+"--------"+subText+"--->"+textTable);
           	            textReplaced=textBefore+textRep+textAfter;
                        //System.out.println("ReportAreaForm "+p+")"+textReplaced);
                 
              }
                      	     	
        	//System.out.println("text.length 2 "+text.length());
        
        }
        }// for each table
      }
    
    
      listIndexOfTableStart.clear();
      listIndexOfTableEnd.clear();
      listTableRowsPerPage.clear();
    
    text=textReplaced;
    
      if(text.indexOf(tableEnd)!=-1)
      {
      	text = text.replaceAll(tableEnd, "");
      	
      }
     
//         ArrayList listOfColumns = new ArrayList();
//         	listOfColumns = (ArrayList)listOfListOfColumns.get(0);// 2 is the master 0 is the header 1 is the footer
//         int columnCount =  listOfColumns.size();
         //System.out.println("ReportAreaODMRec.setEntity showColumns"+showColumns.length);


   
         String columnCaption=""; 
          columnTable="";
          columnName="";
             // ArrayList lstrec = (ArrayList)listRecords.get(page-1);
             

            
         //   System.out.println("ReportAreaForm.setEntity     is it usefull? l:"+l);
            
       // }*/

      
       
  //          EntityReportRow entRepRow = (EntityReportRow)listRecords.get(page-1);
 //           RecColumn[] arrayOfColumns = (RecColumn[])entRepRow.getRecColumn();          	
              
         // for each field in rs
        // String textToBeReplaced=strField+columnCaption+"[*]*"; //+",\\d\\d,[l,r,c]"; 
/*        for(int r = 0;r<listRecords.size();r++    )
        { 
            
         for (int c = 0; c < listOfColumns.size(); c++)//  i = fields
         {
       	         
                 RecColumn col = (RecColumn)listOfColumns.get(c);
                 int columnWidth = col.getColumnLength();
                 String colClass= col.getColumnClass();	
                 columnCaption = col.getColumnCaption();
                 columnCaption = columnCaption.toLowerCase();
                 columnName = col.getColumnName();
                 columnTable = col.getColumnTable();
                 
                 /*arrayOfColumns[i-1].getColumnLength(); //intSumPagePreLength[i];
                 String colClass= arrayOfColumns[i-1].getColumnClass();	
                 columnCaption = arrayOfColumns[i-1].getColumnCaption();
                 columnCaption = columnCaption.toLowerCase();
                 columnName = arrayOfColumns[i-1].getColumnName();
                 columnTable = arrayOfColumns[i-1].getColumnTable();*/
              //System.out.println("ReportAreaForm.setEntity     r:"+r+"   c:"+c+"  columnCount:"+columnCount+"       listOfColumns.size():"+listOfColumns.size()+"      listRecords.size():"+listRecords.size()+"          "+columnTable+"."+columnName);   

               //Object[] arrayOfObjects = (Object[])entRepRow.getFieldValues();

               //RecColumn[] arrayOfColumns = (RecColumn[])entReportRow.getRecColumn();          	
                  
                //  for each record
 /*               String fld="";
             if(listRecords != null && listRecords.size()!=0 && listRecords.get(r)!=null)
             {
                  EntityReportRow entReportRow = (EntityReportRow)listRecords.get(r);
                  String[] strArrValues = entReportRow.getFieldValues();
                	// if record != null

               if (entReportRow!= null  && entReportRow.getFieldValues().length>0 )//&& !entReportRow.getFieldValues()[  entReportRow.getRecordNoOfBand()].equalsIgnoreCase(""))
               {
                   
        //            System.out.println("ReportAreaForm.setEntity ooooooooooo  r:"+r+"   c:"+c+"    columnCount:"+columnCount+"      strArrValues.length:"+strArrValues.length+"       entReportRow.getRecordNoOfBand():"+entReportRow.getRecordNoOfBand()+"        entReportRow.getIntOfEntityReportBand()"+entReportRow.getIntOfEntityReportBand()+"           listOfColumns.size():"+listOfColumns.size()+"            "+columnTable+"."+columnName);                                              
                   //.getRecColumn();     //.getRecordNoOfBand()
                      String strValue = strArrValues[entReportRow.getIntOfEntityReportBand()];//c];//entReportRow.getRecordNoOfBand()];
                  

               	String columnValue="";
               	if(colClass.equalsIgnoreCase("java.sql.Date") || colClass.equalsIgnoreCase("java.lang.Date")  )
               	{
               		//columnValue = utilsDate.reformatDateStringToReadFromDB(rs.getString(i));
               		colValue=utilsDate.reformatDateStringToReadFromDB(strValue);
               	}
               	else if (colClass.equalsIgnoreCase("java.lang.Double")) //|| colClass.equalsIgnoreCase("java.lang.Long"))
               	{
                    
               		 colValue = utilsDouble.getDoubleReading(strValue,false);
                         
               	}               	
               	else if(colClass.equalsIgnoreCase("java.lang.String"))
               	{
               	    colValue=strValue;   
               	}
               	else if(colClass.equalsIgnoreCase("java.lang.Integer") )
               	{
               		colValue=strValue;  
               	}     
                else if(colClass.equalsIgnoreCase("java.lang.Long"))
                {
                    //System.out.println("ReportAreaForm.setEntity column long -"+i+"-  "+columnCaption+" "+colValue+" "+colClass);
                       colValue=strValue;  
                }
               	else
               	{
               		System.out.println("ReportAreaForm.setEntity column - c"+c+"- r:"+r+"  "+columnCaption+" "+colValue+" "+colClass+" NOT supported");
               		colValue=strValue;   
               	}

          //System.out.println("ReportAreaForm.setEntity  --  c:("+c+")     - r:"+r+"       "+columnTable+"."+columnName+"("+columnCaption+")="+colValue+"      "+colClass +"     columnCount:"+columnCount+"     listRecords.size:"+listRecords.size());                
                      	
                    for(int f=0;f<listStringField.size();f++)
                    {
                    	String field = listStringField.get(f).toString();
                    	String fieldColumn;
                    	if(field.indexOf(",")!=-1)
                    	{
                    	  fieldColumn = field.substring(1,field.indexOf(","));	
                    	}
                    	else
                    	{
                    		fieldColumn=field;
                    	}
                    	
                    //System.out.println("ReportAreaForm.setEntity  rep f("+f+") "+fieldColumn+" "+field);
                      text = replaceValuesOLD(text, fieldColumn, field,  columnCaption, colValue, page);
                     

                     
                     
                     }// for f  

                }
                else
                {// a field in record is null
                  
                    System.out.println("ReportAreaForm.setEntity     c:"+c+" "+columnCaption+" null");
                       String textToBeReplaced=strField+columnCaption+"[*]*"; //+",\\d\\d,[l,r,c]";   
                    for(int f=0;f<listStringField.size();f++)
                    {
                    	String field = listStringField.get(f).toString();
                    	String fieldColumn;
                    	if(field.indexOf(",")!=-1)
                    	{
                    	  fieldColumn = field.substring(1,field.indexOf(","));	
                    	}
                    	else
                    	{
                    		fieldColumn=field;
                    	}
                    	
                    	//System.out.println("ReportAreaForm "+fieldColumn+" "+field);
                      if(fieldColumn.equalsIgnoreCase(columnCaption))
                      {
                      text = replaceValuesOLD(text, fieldColumn, field,  columnCaption, null, page);
                     

                      }
                    }
                  	System.out.println("error ReportAreaForm.setEntity  null listRecords ");
                }
      
              }
              else
              {
                	System.out.println("error ReportAreaForm.setEntity  listRecords size 0 "+page);
                	colValue="";
              }                  

          }/// column  
        
     }// record
    

     

     // first look for derivatives of money, then money, then numbers
     
    text = findVerbalAndReplace(text,STR_VERBAL_FUNCTION_MONEY_DEC);// = "/verbalmd"; // m at the end means money
    text = findVerbalAndReplace(text,STR_VERBAL_FUNCTION_MONEY_HUN);// = "/verbalmh"; // m at the end means money
    text = findVerbalAndReplace(text,STR_VERBAL_FUNCTION_MONEY_THO);// = "/verbalmt"; // m at the end means money
    text = findVerbalAndReplace(text,STR_VERBAL_FUNCTION_MONEY_MIL);// = "/verbalmm"; // m at the end means money

     text = findVerbalAndReplace(text,STR_VERBAL_FUNCTION_MONEY);
     
     text = findVerbalAndReplace(text,STR_VERBAL_FUNCTION);

     
      text = text.replaceAll("/chpg", TXT_CHANGEPAGE); // change page
      

      //System.out.println(text);*/
      
      // do not show comments /* */ and //
   //   text = text.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)",""); 
     
        // do it because if has TXT_CHANGELINE the 'replace comments' makes error
        // also JList understands change line -> TXT_CHANGELINE, not "\n"
   //     text = text.replaceAll("\n",TXT_CHANGELINE);
         //TXT_CHANGELINE;
/*        
       strPrintDotMatrix = text;
       System.out.println("ReportAreaForm.setEntity       isDotmatrix:"+isDotmatrix+"        strPrintDotMatrix:"+strPrintDotMatrix);
   	 JLabel labelHtml = new JLabel(); 
        
       if(isDotmatrix)
       {
           
       }
       else
       {
           
          
           
  //         labelHtml.setText(processAndGetFileFormLaser());
       }
              
      
       panelArea.add(labelHtml,BorderLayout.CENTER);
   	 
     /*           // delete unziped files        
            for(int i = 0; i < listFilesInZip.size(); i++)
            {   
                utilsOS.deleteFile(dirTemplateReports+VariablesGlobal.globalSystemDirectorySymbol+listFilesInZip.get(i));
            }       	 */
   	 
//   }
   
   /*
   *  read html file
   */
   /*private String processAndGetFileFormLaser()
   {
        String zipFile = dirTemplateReports+VariablesGlobal.globalSystemDirectorySymbol+nameOfForm+TEMPLATE_REPORTFORMFILETYPE;   
   	  String reportfileLaser = FILE_REPORTFORMTEMPLATE_LASER; 
   	  //FileInputStream in = new FileInputStream();
        StringBuilder contentBuilder = new StringBuilder();
        try
        {
            System.err.println("ReportAreaForm.processAndGetFileFormLaser LASER    zipFile:"+zipFile+"    reportfileLaser:"+reportfileLaser);
            BufferedReader in = utilsOS.getFileFromZip(reportfileLaser,zipFile);
          //BufferedReader in = new BufferedReader(new FileReader(reportfileLaser));
         String str = "";
           
          if(arrayOfNameOfPksOfRecordToShow == null) // when is to massively print records
         {
             
         }
          else // for selected record and document
         {
           /* str = "<html>"
                 +"<table>"
                 + "<tr><td>"+query+"</td></tr>"
                 + "</table>"
                 + "<table>"
                 + "<tr><td>"+arrayOfNameOfPksOfRecordToShow[0]+"</td> <td>-</td> <td>"+arrayOfValueOfPksOfRecordToShow[0]+"</td></tr>"
                 + "<tr><td>"+arrayOfNameOfPksOfRecordToShow[1]+"</td> <td>-</td> <td>"+arrayOfValueOfPksOfRecordToShow[1]+"</td></tr>"
                 + "</table> ";*/
/*         }
          //contentBuilder.append(str);           

         while ((str = in.readLine()) != null)
         {
             
             //str=str+str;
            // str = str;
         contentBuilder.append(str);
         }
           in.close();
        
        }
        catch (IOException ex)
        {
          System.err.println("ReportAreaForm.processAndGetFileFormLaser LASER    IOException: "+ex.getMessage()+"     file not found:"+VariablesGlobal.globalSystemDirectorySymbol+DIR_REPORTDOCUMENTTEMPLATE+VariablesGlobal.globalSystemDirectorySymbol+nameOfForm+FILE_REPORTFORMTEMPLATE_LASER);
          //System.err.println(ex);
        }
 
        return contentBuilder.toString();      
      
      //System.out.println("ReportAreaForm.setEntity zipFile "+zipFile);
      // listFilesInZip = utilsOS.unzipFile(zipFile, dirTemplateReports);
   }*/
   
   
   private void closeDB()
   {
       db.releaseConnectionRs();
       db.releaseConnectionRsmd();
   }
   
   
      /*
   *  read html from db field
   */
   private String processAndGetFormLaserFromDB(String queryFormForPrinting, String strQueryFieldOfForm)
   {
       String strReturn="";
       

     
     
              String queryWhereAnd = "";
             if(arrayOfNameOfPksOfRecordToShow!=null)
             {
              for(int p=0;p<arrayOfNameOfPksOfRecordToShow.length;p++)
              {

                  //  System.out.println("ReportAreaForm.processAndGetFormLaserFromDB    ++++++++   p:"+p+"  column:"+arrayOfNameOfPksOfRecordToShow[p]+"="+arrayOfValueOfPksOfRecordToShow[p]); 
                  queryWhereAnd = queryWhereAnd + " AND "+arrayOfNameOfPksOfRecordToShow[p]+" LIKE "+arrayOfValueOfPksOfRecordToShow[p];
              }
             }
             //else

             queryFormForPrinting = queryFormForPrinting+" "+queryWhereAnd;

   	    db.retrieveDBDataFromQuery(queryFormForPrinting,"ReportAreaForm.processAndGetFormLaserFromDB");
   	    rs=db.getRS();
   	   // rsmd=db.getRSMetaData();
         
      try
       {
          //rs.beforeFirst();

       	int row = 0;
          while ( rs.next() )
          {
              String reportText = rs.getString(strQueryFieldOfForm); // "printForm.printFormLaser"
              
              row++;
              strReturn = reportText;
          }
                    
                  
          System.out.println("ReportAreaForm.processAndGetFormLaserFromDB while      row:"+row+"     strQueryFieldOfForm:"+strQueryFieldOfForm+"         sql:"+queryFormForPrinting);
          
          
          if (row == 0) 
          {
              System.out.println("error:  ReportAreaForm.processAndGetFormLaserFromDB:  row:"+row+"   Perhaps not any row. Not available report !!    queryFormForPrinting: "+queryFormForPrinting);
          }
          else if(row > 1)
          {
              System.out.println("      error:       ReportAreaForm.processAndGetFormLaserFromDB:     count >1 row:"+row+"      More than one row of reports? !!      queryFormForPrinting: "+queryFormForPrinting);
              //utilsGui.showMessageError(this,"error:  ReportAreaForm.processAndGetFormLaserFromDB: count >1 row:"+row+". More than one row of reports? !!");
          }
          else if(row == 1)
          {
              
          }
              
      }// try sql
      catch ( SQLException sqlex)
      {
         System.out.println("error:ReportAreaForm.processAndGetFormLaserFromDB:   "+sqlex.getMessage()+"       queryFormForPrinting: "+queryFormForPrinting);
         sqlex.printStackTrace();
        
      }     
      finally
      {
           closeDB();
      }
            
      if(strReturn==null)
      {
          strReturn ="";
      }
       
/*        String zipFile = dirTemplateReports+VariablesGlobal.globalSystemDirectorySymbol+nameOfForm+TEMPLATE_REPORTFORMFILETYPE;   
   	  String reportfileLaser = FILE_REPORTFORMTEMPLATE_LASER; 
   	  //FileInputStream in = new FileInputStream();
        StringBuilder contentBuilder = new StringBuilder();
        try
        {
            System.out.println("ReportAreaForm.processAndGetFileFormLaser LASER    zipFile:"+zipFile+"    reportfileLaser:"+reportfileLaser);
            BufferedReader in = utilsOS.getFileFromZip(reportfileLaser,zipFile);
          //BufferedReader in = new BufferedReader(new FileReader(reportfileLaser));
         String str = "";
           
        

         while ((str = in.readLine()) != null)
         {
             
             //str=str+str;
            // str = str;
         contentBuilder.append(str);
         }
           in.close();
        
        }
        catch (IOException ex)
        {
          System.err.println("ReportAreaForm.processAndGetFileFormLaser LASER    IOException: "+ex.getMessage()+"     file not found:"+VariablesGlobal.globalSystemDirectorySymbol+DIR_REPORTDOCUMENTTEMPLATE+VariablesGlobal.globalSystemDirectorySymbol+nameOfForm+FILE_REPORTFORMTEMPLATE_LASER);
          //System.err.println(ex);
        }
 
        return contentBuilder.toString();      
  */

      closeDB();
     return strReturn;
      //System.out.println("ReportAreaForm.setEntity zipFile "+zipFile);
      // listFilesInZip = utilsOS.unzipFile(zipFile, dirTemplateReports);
   }
   
   
   
   
   private String processAndGetFileFormDotMatrix()
   {
       String line ="";
          
       ArrayList listLineString= new ArrayList();
       ArrayList listStringField= new ArrayList();
          
   	  ArrayList listFilesInZip = new ArrayList();
   	  
       String zipFile = dirTemplateReports+VariablesGlobal.globalSystemDirectorySymbol+nameOfForm+TEMPLATE_REPORTFORMFILETYPE;   
          
    //String dirZipFile = nameOfForm+TEMPLATE_REPORTFORMFILETYPE;
    File file=new File(zipFile);
    boolean exists = file.exists();
    if (!exists)
    {
      // It returns false if File or directory does not exist
        System.out.println("ReportAreaForm.processAndGetFileFormDotMatrix file not found "+zipFile);
        //utilsGui.showMessageInfo("Δεν βρέθηκε το αρχείο: \n"+zipFile+".");
        
    }
    else
    {             
          try
   	  {
   	  
   	  //FileInputStream in = new FileInputStream();
      
      
      //System.out.println("ReportAreaForm.setEntity zipFile "+zipFile);
      // listFilesInZip = utilsOS.unzipFile(zipFile, dirTemplateReports);
      
      
      String reportfileDotmatrix = FILE_REPORTFORMTEMPLATE_DOTMATRIX;//VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+DIR_REPORTFORMTEMPLATE+VariablesGlobal.globalSystemDirectorySymbol+nameOfForm+FILE_REPORTFORMTEMPLATE_DOTMATRIX;
      //String reportfileLaser = FILE_REPORTFORMTEMPLATE_LASER;
      
       //BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(reportfile)));//fileName),
       
       BufferedReader in = utilsOS.getFileFromZip(reportfileDotmatrix,zipFile);
                       while((line = in.readLine()) != null)
                       {
        	               listLineString.add(line);
        	               if(line.indexOf("<")!=-1)
        	               {
        	                 listStringField.add(line);	
        	               }
        	               
                            text= text+line+"\n"; //TXT_CHANGELINE;// "\n";
                       } 
                       textFromFile=text;
                                    
      }
      catch (IOException ex)
      {
          System.err.println("ReportAreaForm.processAndGetFileFormDotMatrix  IOException: DOT MATRIX "+ex.getMessage()+
          "     file not found:"+VariablesGlobal.globalSystemDirectorySymbol+DIR_REPORTDOCUMENTTEMPLATE+VariablesGlobal.globalSystemDirectorySymbol+nameOfForm+FILE_REPORTFORMTEMPLATE_DOTMATRIX);
          //System.err.println(ex);
      }
    
    }       
    
    return textFromFile;
   }




   
   
   private String createTableForDotmatrixAndGet(String allText,int NUMBER_OF_FORMROWS_PER_PAGE,String textLine, String textLineOfFile, int indexAfterTableStart,int indexTableEnd,
    ArrayList listTableRecord, ArrayList listTableColCaption, ArrayList listStringField, int page, int intFromRec, int intToRec)
   {
   	String textTable="";
   	//String textLineOfFile="";
   	      
 
   	      
          for (int r= intFromRec;r<intToRec;r++)
          {
            textLine = allText.substring(indexAfterTableStart,indexTableEnd);
            //System.out.println("ReportAreaForm.createTable r"+r+" "+textLine+" "+indexAfterTableStart+"-"+indexTableEnd);
            textLineOfFile=textLine;
            
          	 if(r<listTableRecord.size())
          	 {
              
          	  ArrayList lsRec = (ArrayList)listTableRecord.get(r);
          	  //System.out.println(" lsRec "+lsRec.size()+" "+rsmdColumnCount+" "+listTableColCaption.size());
          	  for(int c=0;c<listTableColCaption.size();c++)
          	  {
          	  	
          		  String columnValue = (String)lsRec.get(c);
          		  String columnCaption = (String)listTableColCaption.get(c);
          		  //System.out.println(" klm createTable"+columnValue+" "+r+" "+c);
          		
                     for(int f=0;f<listStringField.size();f++)
                     {
                    	String field = listStringField.get(f).toString();
                    	String fieldColumn;
                    	if(field.indexOf(",")!=-1)
                    	{
                    	  fieldColumn = field.substring(1,field.indexOf(","));	
                    	}
                    	else
                    	{
                    		fieldColumn=field;
                    	}
                    	
                      //System.out.println("ReportAreaForm "+fieldColumn+" "+columnCaption+" "+columnValue);
                      textLine = replaceValuesDotMatrix(textLine, fieldColumn, field,  columnCaption, columnValue, page);
                      //System.out.println(" "+r+textLine);
                     }// for f           		
          		
          		//replaceValues(textLine ,String fieldColumn,String field, columnCaption,  columnValue, int page)
          	  }// for 
          	  //System.out.println(" "+r);
          	   
          	  	textTable=textTable+textLine+"\n"; // not TXT_CHANGELINE because adds two lines
          	  	//System.out.println("ReportAreaForm.createTable r"+r+") "+textTable);
          	  	
          	 }
          	 else
          	 {  // add empty rows
          	    //System.out.println("ReportAreaForm.createTable "+r+" TXT_CHANGELINE");
          		textTable=textTable+"\n"; // not TXT_CHANGELINE because adds two lines   TXT_CHANGELINE;//"\n"; //TXT_CHANGELINE;// "\n";
          		
          	 }        		
          }
          
          //System.out.println("ReportAreaForm.createTable ---------"+textTable);
   	 return textTable;
   }
   
   private String findVerbalAndReplace(String textSource, String strVerbalFunction)
   {
     
     //System.out.println("ReportAreaForm.findVerbalAndReplace "+textSource.substring(0,500));
     
     ArrayList pointOfAmount = utilsString.getIndicesOf(textSource,strVerbalFunction);
     int idx = 0;
     // the first point is found bu the second has changed index after replacing the first
     for(int p=0; p<pointOfAmount.size();p++)
     {

         // if more
         //System.out.println("ReportAreaForm.findVerbalAndReplace +++"+p);
         if(p==0)
         {
         	textSource = replaceVerbal((Integer)pointOfAmount.get(p)+strVerbalFunction.length(), textSource, strVerbalFunction);      	
         	idx = textSource.indexOf(strVerbalFunction,(Integer)pointOfAmount.get(p)+strVerbalFunction.length()+strVerbalFunction.length());
         }
         else if(pointOfAmount.size()<p+1)// not possible
         {
         	
         }
         else
         {
         	textSource = replaceVerbal(idx+strVerbalFunction.length(), textSource, strVerbalFunction); 
         	idx = textSource.indexOf(strVerbalFunction, idx+strVerbalFunction.length()+strVerbalFunction.length());
         }   	
     }   	
   	
   	return textSource;
   }
   
   private String replaceVerbal(int point, String text, String tag )
   {

     double amount = 0;
     String strV = text.substring(point,text.indexOf(" ",point));
     strV = utilsDouble.returnDoubleWithDotInsteadOfAComma(strV);
     //System.out.println("ReportAreaForm.replaceVerbal strV "+strV+" from "+point+" to "+text.indexOf(" ",point));
     if(strV!=null && !strV.trim().equals(""))
     {
     	amount = Double.parseDouble(strV);
     }
     
     //System.out.println("ReportAreaForm.replaceVerbal "+point+" "+text.indexOf(" ",point)+" amount "+amount);
     // a all   d decimal, h hundrend,t thousant, m milions
     if (tag.equalsIgnoreCase(STR_VERBAL_FUNCTION_MONEY))
     {
     	text = text.replaceFirst(tag+"[0-9., ]*", utilsString.getVerbal(amount, true , true,"a")); 
     }
     else if (tag.equalsIgnoreCase(STR_VERBAL_FUNCTION_MONEY_DEC))// = "/verbalmd"; // m at the end means money
     {
     	text = text.replaceFirst(tag+"[0-9., ]*", utilsString.getVerbal(amount, true , true,"d")); 
     }
     else if (tag.equalsIgnoreCase(STR_VERBAL_FUNCTION_MONEY_HUN))// = "/verbalmh"; // m at the end means money
     {
     	text = text.replaceFirst(tag+"[0-9., ]*", utilsString.getVerbal(amount, true , true,"h")); 
     }
     else if (tag.equalsIgnoreCase(STR_VERBAL_FUNCTION_MONEY_THO))// = "/verbalmt"; // m at the end means money
     {
     	text = text.replaceFirst(tag+"[0-9., ]*", utilsString.getVerbal(amount, true , true,"t")); 
     }
     else if (tag.equalsIgnoreCase(STR_VERBAL_FUNCTION_MONEY_MIL))// = "/verbalmm"; // m at the end means money     
     {
     	text = text.replaceFirst(tag+"[0-9., ]*", utilsString.getVerbal(amount, true , true,"m")); 
     }
     else if (tag.equalsIgnoreCase(STR_VERBAL_FUNCTION_MONEY_BIL))// = "/verbalmm"; // m at the end means money     
     {
     	text = text.replaceFirst(tag+"[0-9., ]*", utilsString.getVerbal(amount, true , true,"b")); 
     }     
     else if (tag.equalsIgnoreCase(STR_VERBAL_FUNCTION))
     {
     	text = text.replaceFirst(tag+"[0-9., ]*", utilsString.getVerbal(amount, true , false, "a")); 
     		//   (double money, boolean showZero,boolean showCurrency )
     }
     else
     {
     	System.out.println("ReportAreaForm.replaceVerbal "+tag+" not found");
     }
     
      //System.out.println("ReportAreaForm.replaceVerbal "+point+" "+text.indexOf(" ",point)+" amount "+amount);  	
   	
   	return text;
   }
   
   private String replaceValuesDotMatrix(String textArea,String fieldColumn,String field, String columnCaption, String colValue, int page)
   {
   	

   	
   	
                      if(fieldColumn.equalsIgnoreCase(columnCaption))
                      {
                      	
                    String textToBeReplaced=strField+columnCaption+"[*]*"; //+",\\d\\d,[l,r,c]";   	
                      
                     int idxOfFirstComma = field.indexOf(",",0);
                     int idxOfSecondComma = field.indexOf(",",idxOfFirstComma+1);
                     int idxOfThirdComma = field.indexOf(",",idxOfSecondComma+1);
                     
                     
                     int lengthOfField =  Integer.valueOf(field.substring(idxOfFirstComma+1,idxOfSecondComma).toString());
                     String justificationOfField=field.substring(idxOfSecondComma+1,idxOfThirdComma).toString();
                     
                     String textToReplace = "";
                     
                     //int lengthTextToBeReplaced = lengthOfField;
                     int lengthTextToReplace = 0;
                     
                     int dispute = 0;
                     if(colValue==null)
                     {
                     	dispute=lengthOfField;
                     }
                     else
                     {
                     	textToReplace = colValue;
                     	lengthTextToReplace = textToReplace.length();
                     	dispute=lengthOfField-lengthTextToReplace;
                     }
                     
                     /*	if(fieldColumn.equalsIgnoreCase("farPC"))
                     	{
                     		System.out.println("ReportAreaForm.replaceValues "+fieldColumn+" "+columnCaption+" dispute "+dispute);	
                     	}  */                   
                     //int dispute = lengthOfField-lengthTextToReplace;
                     //System.out.println("ReportAreaForm "+columnCaption+" "+lengthOfField+" "+dispute+" "+lengthTextToReplace);
                     if (dispute>0)
                     {
                     	
                     	String space="";
                     	for(int s=0;s<dispute;s++)
                     	{
                     		space=space+strSpace;
                     	}
                     	
                     	if(justificationOfField.equalsIgnoreCase("l"))
                     	{
                     	   textToReplace=textToReplace+space;	
                     	}
                     	else if(justificationOfField.equalsIgnoreCase("r"))
                     	{
                     		textToReplace=space+textToReplace;	
                     	}
                     	else if(justificationOfField.equalsIgnoreCase("c"))
                     	{
                     		String halfSpace = "";
                     		//dispute = dispute/2;
                     		int disputeRemain=dispute%2;
                     		//System.out.println("ReportAreaForm disp remainder "+dispute+" "+disputeRemain);
                     	  	for(int s=0;s<dispute/2;s++)
                     	    {
                     		   halfSpace=halfSpace+strSpace;
                     	    }
                     	    if(disputeRemain==1)
                     	    {
                     	       textToReplace=halfSpace+textToReplace+halfSpace+"-";		
                     	    }
                     	    else
                     	    {
                     	    	textToReplace=halfSpace+textToReplace+halfSpace;	
                     	    }
                     		
                     		
                     	}
                     	
                   if(fieldColumn.equalsIgnoreCase("farPC"))
                   {
                   	System.out.println("ReportAreaForm.replaceValues "+fieldColumn+" "+columnCaption+"   textToBeReplaced:'"+textToBeReplaced+"'   textToReplace:'"+textToReplace+"'");	
                   }  
                     	
                     }
                     else if (dispute<0)
                     {
                     	textToReplace=textToReplace.substring(0,lengthOfField);
                     }
                     //System.out.println("ReportAreApplication  = "+fieldColumn+" "+columnCaption+" "+textToReplace);
                          textArea = textArea.replaceAll(textToBeReplaced, textToReplace);	
                        
                        
                     } // if is field
                     else if (fieldColumn.equalsIgnoreCase("aa"))
                     {
                     	
                     	textArea = textArea.replaceAll(">aa", page+"");
                     }   
                     	

                     		
   	return textArea;
   }
   
 /*   private String getCode10CPI()
    { //10 characters per inch (condensed available)
   
        //pstream.print(ESC);
        //pstream.print(P);        	
        return ESC+P+"";
    }
   
    public String getCode15CPI() { //15 characters per inch (condensend not available)
        //pstream.print(ESC);
        //pstream.print(g);
        return ESC+g+"";
    } */  
   
   
   
   /*
   *called by  ReportPanelPage.generateMain
   */
   /*public JPanel getReportAreaFormPanel()
   {
       
       //System.out.println("ReportAreaForm.getReportAreaPanel    panelArea:"+panelArea+"    this:"+this);
   	 return this;//labelHtml;
   }*/
   
   
   // called by PanelPrintPreview.goPrint , ReportPanelPage.generateMain()
    public String getStrPrintDotmatrix()// for dot matrix
   {
   	
   	//System.out.println("ReportAreaForm.getStrPrintDotmatrix "+strPrintDotMatrix);
   	
   	  return strPrintDotMatrix;
   }
   
    
    /*public int getCurrentPage()
    {
    	return pageCurrent;
    }*/
   
  public void setPageFormat(PageFormat pf)
  {
  	pageFormat=pf;
  	//System.out.println("ReportPanelPage.setPageFormat pf -> "+pf);
  	
  } 
 
   
  public Printable getPrintable()
  { return this; } 








    
    
    
    
    
    
    
       public int print(Graphics g, PageFormat pf, int pageIndex)  throws PrinterException
 {

	Graphics2D g2 = (Graphics2D) g;
	int pageHeight = (int) Math.round(pf.getImageableHeight());
    int pageWidth = (int) Math.round(pf.getImageableWidth());
    double panelWidth = Double.valueOf(this.getWidth()).doubleValue();
    double panelHeight= this.getHeight();
    double scale = 1;
    
    //System.out.println("ReportPanelOneDataManyRecPage.print "+pageIndex);
    
    
     if (panelWidth >= pageWidth)
     {
            scale = pageWidth / panelWidth;
     }   
    //System.out.println("Printpanel.print pageHeight="+pageHeight+" panelHeight="+panelHeight);
	//System.out.println("ReportPanelOneDataManyRecPage.print scale="+scale+" pageHeight/panelHeight="+pageHeight/panelHeight);

    g2.translate(pf.getImageableX(), pf.getImageableY());
	
    // As suggested by Jaume (Tau Ingenieros <tauinge@menta.net>), use
    // paint instead of paintComponent and resize to page format.
    Dimension oldSize = getSize();
    //setSize((int)pf.getWidth(), (int)pf.getHeight());

    //System.out.println("ReportPanelOneDataManyRecPage.print pageHeight"+pageHeight+" pageWidth "+pageWidth);
    setSize(pageWidth,pageHeight);
    print(g);
    setSize(oldSize);

    return Printable.PAGE_EXISTS;
 }
 }