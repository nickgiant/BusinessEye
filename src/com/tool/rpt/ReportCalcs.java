// created 26-10-07

package com.tool.rpt;

import com.tool.guicomps.*;
import com.tool.jdbc.*;
import com.tool.utils.*;
import com.tool.gui.*;
import com.tool.model.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.print.PageFormat;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.Box;
import javax.swing.SwingConstants;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Vector;

 public class ReportCalcs implements Constants
 {
 private String query;
 //private String queryHeader;
 private EntityReport entityReport;
 private String entity;
 private String title;
 //private int[] showColumns;
 //private ArrayList showColumnsPerBand;
 private boolean isDotmatrix;
 private boolean showSummaryInPage;
 
 //private int columnCountMaster =0;
 //private int columnCountHeader =0;
 private int rowCountMaster =0;
 private int rowCountHeader =0;
 private int rowCountTotal;
 private Database db; 
     
 private ResultSet rs;
 private ResultSetMetaData rsmd; 
 
 //  private ResultSet rsMany;
 // private ResultSetMetaData rsmdMany; 
    
  private ResultSet rsHeader;
  private ResultSetMetaData rsmdHeader;
   
  private String strPrintDotMatrix="";  
  private String printWriterPageHeader="";
  
  
  private JPanel panelPage; // the one to be printed
  private JPanel panelPageContainer; //contains page
    
  private int lines;  
  private int linesTotal;
  private int countBandsTitle;
  private int linesPerPage;
    //private int pageCurrent=1;
  private int pageCurrent=1;
  private int intNoOfRecordOfPreviousBand;
  private int intNoOfChangesOfBand;
  private boolean showVerticalLines;
  private boolean showHorizontalLines;
    
  private UtilsDouble uDouble;
  private UtilsDate uDate;
  
   private ArrayList arrayPanelPage; // to hold page
   private ArrayList arrayPanelPageBrake; // to hold page
   //private JTextField txtFieldRecords;
   
   private JPanel panelPageHeader;
   private JPanel panelPageFooter;
   
   private JPanel panelReportHeader;
   
   private int columnWidthDouble;
   private int columnWidthInt;
   private int columnWidthDoubleDotmatrix;
   private int columnWidthIntDotmatrix;
   
   
   private String[] sumPage;
   private String[] sumPagePre;// sum of previous pages
   
   //private String[] sumPagePreMany;
   
   private ArrayList listSumsOfPage;// listSumsOfPage of page
   private ArrayList listSumPagePre;
   private ArrayList listSumPagePreField;
   private ArrayList listSumRecCount = new ArrayList();
   //private ArrayList listSumRecCountHeader;
   private ArrayList listCountHeaderRecsInArea;
   
//   private int[] intSumPagePreLength; // length of sums numbers
   JPanel panelPageBrake;
   PageFormat pageFormat;
   JPanel panelLeft;
   JPanel panelRight;
   
   Dimension pageDimension;
   
   Font fontNormal;
   Font fontBold;
   FlowLayout layoutHorizBorderCenter;
   FlowLayout layoutHorizBorderRight;
   Dimension separationVerticalLine;
   Dimension separationVertical2Line;
   Dimension separationVertical3Line;
   
   final int DOTMATRIX_CHARS_PER_LINE_A4=80;
   final int DOTMATRIX_CHARS_PER_LINE_A3=135;
   final int DOTMATRIX_LINES_PER_PAGE=48; // lines of data, not all the lines
   final String DOTMATRIX_LINE_CHAR="=";
   final String DOTMATRIX_CHAR_EMPTY=" ";
   int linesOfReportHeader=0;   // have linesOfReportHeader as public
       
   private EntityFilterSettings[] entityFilterSettings;
   private int[] intSettingsReport;
   //private int charsPerLine=0; 
   String dotmatrixA4HorizLine="";
   
   //private Thread thread;
   
  private ArrayList listOfGroupsOfRecords; // holds lists of group of rows
  private ArrayList listOfRecords; // holds lists of rows
  
  //private ArrayList listOfAllOfRecords; // holds lists of rows

   private ArrayList listOfListOfColumns; // holds lists of columns (master and header)
  
   //private ArrayList listColumnMaster; // holds column data
   //private ArrayList listColumnHeader ; // holds column data
   //private ArrayList listRecordMaster ; // holds column value
   //private ArrayList listRecordHeader; // holds column value
//   private ArrayList listRecordFieldMaster; // initialized on fetchData because else it doesnt calculate well
   private ArrayList listRecordFieldHeader ; // initialized on fetchData because else it doesnt calculate well
   private ArrayList listRecordHeaderPk ;// for pk tofind it later
   private ArrayList listRecordGroupPk ;// for pk tofind it later
   private ArrayList listRecordHeaderFromPk;// after calculate from pk
   private ArrayList listRecordFooterFromPk;// after calculate from pk
   
   //private EntityReportRow entityReportRowHeaderFromPk;
   //private EntityReportRow entityReportRowFooterFromPk;
   
   private ArrayList listFromRecInAreaMany ;
   private ArrayList listToRecInAreaMany ;
   private ArrayList listPrimKeyVal ;
   private ArrayList listMasterRecsPerPage ;
   private ArrayList listNoOfPreviousRecordsOfBands;
   
   
   
   //private ArrayList listRowInMasterRecsPointer; // arrayList to hold header rows pointer inside all rows
   //private ArrayList listRowInMasterRecsLevel;// arrayList to hold header rows level inside all rows
  // private ArrayList listManyRecsInAreaPK = new ArrayList();
   private RecColumn recColumn;
   
   private int fromRecInPage;
   //private int fromMasterRecInPage;
   //private int toMasterRecInPage=0;
   //private int manyRecsPerPage=0;
   //private int manyRecsPerMasterRec;
   //private int fromManyRecInPage;
   //private int toManyRecInPage=0;   
   
   private String primKey;
    
    private boolean hasMany=false;
    
    private int toRecInRegion = 0;
    private int toRecInPage = 0;
    private int countManyRecs = 0;
    private int fromMasterInPage=0;
    
    private int ROWLEVEL_HEADER = 1;
    private int ROWLEVEL_FOOTER = 2;
    
    private EntityReportBand[] entityReportBand;
    private boolean hasGroups;
  private String[] arrayPreviousValue=null; //previous for the calculation of the same pk on different band
  //private String[] arrayPreviousValue2=null;
  private  int previousRowCount = 0;  //previous for the calculation of the same pk on different band
  
  private RecColumn[] arrayRecColumn;
  private String reportType = "";
    
    public ReportCalcs()
    {
            try
           {    initialize();    }
           catch (Exception e)
           {   e.printStackTrace();    }
    }

    private void initialize() throws Exception
    {   
       db = new Database();
       
       uDouble=new UtilsDouble();
       uDate= new UtilsDate();
       listSumsOfPage= new ArrayList();
       
       showVerticalLines=true;
       showHorizontalLines=true;
       
       //listSumPagePre = new ArrayList();
     /*  columnWidthDouble = 8; // width of column that is double or long
       columnWidthInt=6;
       columnWidthDoubleDotmatrix = 11; // width of column that is double or long
       columnWidthIntDotmatrix = 7;*/ // width of column that is double or long
       
       
     /*  JTextField txt = new JTextField();
       fontNormal = new Font(txt.getFont().getFontName(), txt.getFont().getStyle(), 11);
       fontBold = new Font("Arial", Font.BOLD, 11);*/
       
       
   /*    layoutHorizBorderCenter = new FlowLayout(FlowLayout.CENTER,2,0); //hgap, int vgap 2,4 for all rows
       layoutHorizBorderRight = new FlowLayout(FlowLayout.RIGHT,2,0); //2,4 for footer row
       separationVerticalLine = new Dimension(1,19);
       separationVertical2Line = new Dimension(1,19*2);
       separationVertical3Line = new Dimension(1,19*3);*/
       //panelPage.setPreferredSize(new Dimension(500,500));
   	   //panelPage.setMaximumSize(new Dimension(500,500));
    }
    
   public String getStrPrintDotmatrix()// for dot matrix
   {
   	  return strPrintDotMatrix;
   }
    
   /**
    * called by PanelPrintPreview.fetchData().
    */
    public void setEntity(EntityReport entityReportIn,/*EntityReportBand[] entityReportBandIn,*/String entityIn, String entityManyIn, String queryIn, /*String queryHeaderIn,String entityHeader,String primKeyIn,*/
    String titleIn, int linesOfReportHeaderIn, /*ArrayList showColumnsPerBandIn,*/boolean isDotmatrixIn, boolean showSummaryInPageIn, PageFormat pageFormatIn ,String pageSizeLaser,
    boolean pageOrientationIsPortraitLaser,EntityFilterSettings[] entityFilterSettingsIn,int[] intSettingsReportIn)
    {
    	
        entityReport=entityReportIn;
        countBandsTitle = entityReport.getEntityReportBands().length;
    	query=queryIn;// DO NOT delete: is getted from panel data filter
    	//queryHeader=queryHeaderIn;
    	//primKey=primKeyIn;
    	//isPrintPreview=isPrintPreviewIn;
    	title=titleIn;
    	entity=entityIn;
    	isDotmatrix=isDotmatrixIn;
    	//showColumns=showColumnsIn;
        //showColumnsPerBand = showColumnsPerBandIn;
    	showSummaryInPage=showSummaryInPageIn;
    	pageFormat=pageFormatIn;
    	linesOfReportHeader=linesOfReportHeaderIn;
        entityFilterSettings=entityFilterSettingsIn;
        intSettingsReport= intSettingsReportIn;
    	
    //System.out.println("ReportCalcs.setEntity entityFilterSettings.length:"+entityFilterSettings.length);
    	//showLines = new boolean[2];
   //listSumPagePre = new ArrayList();
    arrayPanelPage = new ArrayList(); // to hold page
    arrayPanelPageBrake = new ArrayList(); // to hold page
   listOfListOfColumns= new ArrayList();
   
   //listColumnMaster = new ArrayList(); // holds column data
   //listColumnHeader = new ArrayList(); // holds column data
   //listRecordMaster = new ArrayList(); // holds column value
   //listRecordHeader = new ArrayList();; // holds column value
   listRecordFieldHeader = new ArrayList();; // initialized on fetchData because else it doesnt calculate well
   listRecordHeaderPk = new ArrayList();// for pk tofind it later
   listFromRecInAreaMany = new ArrayList();
   listToRecInAreaMany  = new ArrayList();
   listPrimKeyVal  = new ArrayList();
   listMasterRecsPerPage = new ArrayList();
   reportType = entityReport.getType(); 	
   
   
   
   
   entityReportBand = entityReportIn.getEntityReportBands();
    arrayPreviousValue = new String[entityReportBand.length];
    //arrayPreviousValue2 = new String[entityReportBand.length-1];
   
    // the very first array of band must be set to ""
   for(int p=0;p<entityReportBand.length-1;p++)
   {
        arrayPreviousValue[p] = "";   
       // arrayPreviousValue2[p] = "";   
   }
    
//System.out.println("ReportCalcs.setEntity   ------   linesOfReportHeader"+linesOfReportHeader);//length:"+entityReportGroup.length+"  type:"+entityReportGroup[0].getType()); 	
   
    	uDouble = VariablesGlobal.globalUtilsDouble;
    	uDate.readFromFileDateFormats();
    	  if (isDotmatrixIn)
    	  {
    	  	linesPerPage=DOTMATRIX_LINES_PER_PAGE;// 5 is the no of lines in footer
    	  }
    	  else
    	  {
              int intFooterLines=0;
             if(entityReportBand!=null && entityReportBand.length>1)
             {
                 intFooterLines=0;
             }
             else
             {
                 intFooterLines=3;
             }
              
             if (pageSizeLaser.equalsIgnoreCase(STRPAGESIZE_A5))
             {    
                      if(pageOrientationIsPortraitLaser)
                      {
                          linesPerPage=37-intFooterLines;
                      }
                      else
                      {
                          linesPerPage=24-intFooterLines;
                      }
             }
             else if(pageSizeLaser.equalsIgnoreCase(STRPAGESIZE_A4))
             {
                      if(pageOrientationIsPortraitLaser)
                      {
                          linesPerPage=57-intFooterLines;
                      }
                      else
                      {
                          linesPerPage=37-intFooterLines;
                      }                 
             }
             else if(pageSizeLaser.equalsIgnoreCase(STRPAGESIZE_B4))
             {
                      if(pageOrientationIsPortraitLaser)
                      {
                          linesPerPage=69-intFooterLines;
                      }
                      else
                      {
                          linesPerPage=46-intFooterLines;
                      }                 
             }
             else if(pageSizeLaser.equalsIgnoreCase(STRPAGESIZE_A3))
             {
                      if(pageOrientationIsPortraitLaser)
                      {
                          linesPerPage=84-intFooterLines;
                      }
                      else
                      {
                          linesPerPage=56-intFooterLines;
                      }                 
             }
             else
             {
                 System.out.println("error  ReportCalcs.setEntity UNKNOWN pageSize:"+pageSizeLaser+"  pageOrientationIsPortraitLaser:"+pageOrientationIsPortraitLaser);
             }
              
               System.out.println("ReportCalcs.setEntity      ======       width:"+(int) (pageFormat.getImageableWidth())+"     height:"+(int)(pageFormat.getImageableHeight()) +"   linesPerPage:"+linesPerPage);
    	     //                                                              74 | this is pageheader+pagefooter (the smaller the more the lines)
    	
    	  }
    	
/*    	if (query == null) // if query is null set it 
        {
        	 query = "SELECT * FROM " + entity;
        	 System.out.println("ReportCalcs.setEntity query is null so it is "+query);
        }
*/
      listOfGroupsOfRecords = new ArrayList();
           
      //listOfAllOfRecords = new ArrayList();
      //ArrayList lstPgPre = new ArrayList();
      rowCountTotal = 0;
//      for(int e=0;e<entityReportBand.length;e++ )
//      {
      	//System.out.println("ReportCalcs.setEntity  "+e+"  length:"+entityReportGroup.length);
 /*     	 if(entityReportBand[e].getType()==ENTITYREPORT_QUERY_TYPE_GROUP)
      	 {
      	 	hasGroups = true;
//            query = entityReportBand[e].getSettedQuery();//+" "+entityReportGroup[e].getQueryOrderBy();  already calculated in PanelReportSettings calculate

//            rowCountTotal =  fetchDataGroup(query, entityReportBand[e].getEntity(),e);
//            rowCountTotal =  fetchDataGroup( entityReportBand,e);            
            //lstPgPre = getListSumPagePre();
            
 /*   	   if (VariablesGlobal.globalShowReportSQL)
    	   {
    	      System.out.println("ReportCalcs query group "+e+" "+query);
    	   }*/
//      	 }
      	 //else 
 //         if(entityReportBand[e].getType()==ENTITYREPORT_QUERY_TYPE_MAIN)
 //     	 {
//      	 	query = entityReportBand[e].getSettedQuery()+" "+entityReportBand[e].getQueryOrderBy();
//      	 	rowCountTotal =  fetchDataGroup(query, entityReportBand[e].getEntity(),e);
//      	 	rowCountTotal =  fetchDataGroup( entityReportBand,e);             

 /*          if (VariablesGlobal.globalShowReportSQL)              
    	   {
    	      System.out.println("ReportCalcs query "+query);
    	   }  */               

//      	 }
//      	 else if(entityReportBand[e].getType()==ENTITYREPORT_QUERY_TYPE_ADDITIONAL)
//      	 {
             // do not set because it confuses formApplication
      	 	/*query = entityReportGroup[e].getSettedQuery()+" "+entityReportGroup[e].getQueryOrderBy();
      	 	rowCountTotal =  fetchDataGroup(query, entityReportGroup[e].getEntity(),e);

           if (VariablesGlobal.globalShowReportSQL)              
    	   {
    	      System.out.println("ReportCalcs query additional "+query);
    	   }      */      	 	
      	 	
//      	 	System.out.println(" ReportCalcs.setEntity not supported type ENTITYREPORT_QUERY_TYPE_ADDITIONAL "+entityReportBand[e].getType());
//      	 }      	 
//      	 else
//      	 {
 //     	 	System.out.println("error ReportCalcs.setEntity unknown type "+entityReportBand[e].getType());
 //     	 }
 //     }

    if(VariablesGlobal.globalShowReportSQL)
    {
        System.out.println("ReportCalcs.setEntity     reportType:"+reportType+"     query:"+query+"     queryIn:"+queryIn);
    }
         int countBands = entityReportIn.getEntityReportBands().length; 
          
          listNoOfPreviousRecordsOfBands = new ArrayList();  
            
          if(!reportType.equalsIgnoreCase("FORM"))
          {
             for(int b=0;b<countBands;b++)
             {
                   fetchDataBandColumnTitle(b);
             }
          }
          else
          {   // when is form
              fetchDataOfRSMDColumns(query);
          }
          
             // no need to have seperate fetch for band or rsmd
             rowCountTotal =  fetchData(query);//,noOfBand);
          

    	if(entityManyIn!=null && !entityManyIn.equals(""))
    	{
    		hasMany=true;
    	}
    //System.out.println("rowCountTotal --->" + rowCountTotal);	
    	this.closeDb();
   }
 
    /*
    *  fetch from query rsmd
    */
    private void fetchDataOfRSMDColumns(String query)
    {
        listOfRecords = new ArrayList();
           db.retrieveDBDataFromQuery(query,"ReportCalcs.fetchDataOfRSMDColumns");
   	   rs=db.getRS();
           rsmd=db.getRSMetaData();
           
     try
     {
         RecColumn[] arrayRecColumn = new RecColumn[rsmd.getColumnCount()];
          for (int i = 1; i <= rsmd.getColumnCount(); i++)//  i = fields
         {
           //  System.out.println("ReportCalcs.fetchDataBandColumnTitle start B>   ("+i+")  noOfBand:"+noOfBand +"      columnCountBandFields:"+columnCountBandFields);        
             
       	          String colCaption = rsmd.getColumnLabel(i);
       	          String colName = rsmd.getColumnName(i);//.getDbField();
                 // int colType = erbf[i].getColumnType();// class
                  String colClass = rsmd.getColumnClassName(i);
                  String colTable = rsmd.getTableName(i);
                  //boolean isPK = erbf.isAutoIncrement(i);
                  //System.out.println("ReportCalcs.fetchDataMaster "+colClass);
                  int colLength = rsmd.getColumnDisplaySize(i);
                  arrayRecColumn[i-1] = new RecColumn(colName,colCaption,0,colTable,colClass,colLength,0,false);
                 // System.out.println("ReportCalcs.fetchDataBandColumnTitle start C> ("+i+") noOfBand:"+noOfBand +"      columnCountBandFields:"+columnCountBandFields);        
                  // System.out.println("ReportCalcs.fetchDataMany colName:"+rsmd.getColumnName(i)+" "+i);
       	 }         
         
       	 listOfListOfColumns.add(arrayRecColumn);         

      }// try
      catch ( SQLException sqlex)
      {
         System.out.println("error: ReportCalcs.fetchDataOfRSMDColumns:  msg "+sqlex.getMessage()+" sql "+query);
         sqlex.printStackTrace();
      }             
     
    //System.out.println("ReportCalcs.fetchDataOfRSMDColumns     query:"+query+"       listOfListOfColumns.size:"+listOfListOfColumns.size());
      //return listOfRecords.size();     
    
    }    
    
    
    
    private void fetchDataBandColumnTitle(int noOfBand)
    {
          //EntityReportBand[]   erb = entityReport.getEntityReportBands();
          
         

       // System.out.println("ReportCalcs.fetchDataBandColumnTitle start A>  noOfBand:"+noOfBand +"      entityReportBand:"+entityReportBand+"   erb fields:"+entityReportBand[noOfBand].getEntityReportBandFields());        
          EntityReportBandField[] erbf = entityReportBand[noOfBand].getEntityReportBandFields(); //     <-----
          //EntityDBFields[] edbf = entityReportBand[noOfBand].getEntityDBFields();     //     <-----
          int columnCountBandFields = erbf.length;        
        
          //RecColumn[]
                  arrayRecColumn = new RecColumn[columnCountBandFields];
         for (int i = 0; i < columnCountBandFields; i++)//  i = fields
         {
           //  System.out.println("ReportCalcs.fetchDataBandColumnTitle start B>   ("+i+")  noOfBand:"+noOfBand +"      columnCountBandFields:"+columnCountBandFields);        
             
       	          String colCaption = erbf[i].getCaption();
       	          String colName = erbf[i].getDbFieldName();//.getDbField();
                 // int colType = erbf[i].getColumnType();// class
                  String colClass = erbf[i].getColClassName();// class
                  String colTable = erbf[i].getTableName();
                  //boolean isPK = erbf.isAutoIncrement(i);
                  //System.out.println("ReportCalcs.fetchDataMaster "+colClass);
                  int colLength = erbf[i].getColWidth();
                  //listColumnGroup.add(recColumn = new RecColumn(colName,colCaption,colType,colTable,colClass,colLength));
                  arrayRecColumn[i] = new RecColumn(colName,colCaption,0,colTable,colClass,colLength,0,false);
                 // System.out.println("ReportCalcs.fetchDataBandColumnTitle start C> ("+i+") noOfBand:"+noOfBand +"      columnCountBandFields:"+columnCountBandFields);        
                  // System.out.println("ReportCalcs.fetchDataMany colName:"+rsmd.getColumnName(i)+" "+i);
       	 }         
         
       	 listOfListOfColumns.add(arrayRecColumn);
         
       	 //System.out.println("ReportCalcs.fetchDataBandColumnTitle   -------->  noOfBand:"+noOfBand+" columnCountFields:"+columnCountBandFields);        
        
    }
   
    /*
    *  fetch with defined bands and columns from EntityData
    */
    private int fetchData(String query)
    {
        //int rowCount = 0;
        listOfRecords = new ArrayList();
           db.retrieveDBDataFromQuery(query,"ReportCalcs.fetchData");
   	   rs=db.getRS();
           int rowCount = 0;
           //intNoOfRecordOfPreviousBand = 1 ;
        //   intNoOfChangesOfBand = 0;
           
           //rsmd=db.getRSMetaData();
           //int countReportBands = entityReportBand.length;
           
     try
     {
          rs.beforeFirst();
           // RecColumn[] arrayRecColumn = null;
          
          
          int previousRowCount = 0;

         
          boolean boolRowToInclude = false;
       	  while( rs.next() )
          {   
           rowCount++; 
           listSumPagePreField =  new ArrayList();// here
          // ArrayList listRecColumnAll = new ArrayList();
             // RecColumn[] arrayRecColumnAll= new RecColumn[5];
           
         // add columns from all the groups to one array
//            RecColumn[] arrayRecColumn;
            int colCount;
            //System.out.println("ReportCalcs.fetchDataOfDefinedBandsAndColumns   ---------listOfListOfColumns.size():"+listOfListOfColumns.size());
         for(int g=0;g<listOfListOfColumns.size();g++)
         {
             arrayRecColumn = (RecColumn[])listOfListOfColumns.get(g);

                 
           colCount = arrayRecColumn.length;// .size();         
       
             String[] arrayRecValues = new String[colCount];
          
             String strPkForGrouping = entityReportBand[g].getSqlGroupByField();

                 for(int c = 0 ; c<colCount;c++)
                 {
                  	 //System.out.println("ReportCalcs.fetchDataGroup "+c+" "+rowCount);
                  	 //EntityReportRow entRepRow (EntityReportRow)listOfRecords.get(l);

                 RecColumn col = (RecColumn)arrayRecColumn[c]; //  i-1 because ArrayList starts from 0
                 //boolean isPk = col.getColumnIsPK();
                 String colClass= col.getColumnClass();
                 String colTable= col.getColumnTable();
                 String colName= col.getColumnName();
                 String colValue ="";
                 if(colTable!=null && !colTable.equalsIgnoreCase("") )
                 {
                      colValue = rs.getString(colTable+"."+colName);//colTable+"."+colName);// be carefful in EntityData when two or more tables have a column with the same name
                 }
                 else
                 {
                      colValue = rs.getString(colName);//colTable+"."+colName);// be carefful in EntityData when two or more tables have a column with the same name
                 }
                 
                 arrayRecValues[c] = colValue;

                 

                 int len = 0;
                 //String val = listSumPagePreField.get(l).toString();
                 
                 //System.out.println("ReportCalcs.fetchDataGroup col length "+c+" "+col.getColumnName()+" "+arrayRecValues[c]+" "+colClass);
                 String val = "";
                 
                 if(arrayRecValues[c]!=null)
                 {
                 	val = arrayRecValues[c].toString();
                 }
  
         //System.out.println("ReportCalcs.fetchData   c:"+c+"  -  g:"+g+"    colCount:"+colCount+"      listOfListOfColumns.size():"+listOfListOfColumns.size()+"   -----table.column:"+(colTable+"."+colName)+" = arrayRecValues[c]:"+arrayRecValues[c]);
 
            	  if(colClass.equalsIgnoreCase("java.lang.Double") || colClass.equalsIgnoreCase("java.lang.Long") || colClass.equalsIgnoreCase("java.math.BigDecimal"))
                  {
                    
                  }
                  else if (colClass.equalsIgnoreCase("java.lang.Integer"))
                  {
                  	 /*len = val.length();  
                  	  if(len!=0)
                  	  {
                  	     col.setColumnLength(len);
                  	  }
                  	  else
                  	  {
                  	  	 col.setColumnLength(3);
                  	  }     */                	 	
                  }
                  else if(colClass.equalsIgnoreCase("java.lang.Date") || colClass.equalsIgnoreCase("java.sql.Date"))
                  {
                  	len = val.length();  
                  	  if(len!=0)
                  	  {
                  	     col.setColumnLengthMaxActual(len);
                  	  }
                  	  else
                  	  {
                  	  	 col.setColumnLengthMaxActual(3);
                  	  }                  		
                  }
                  else if(colClass.equalsIgnoreCase("java.lang.String"))
                  {

                            //System.out.println("l"+l+" r"+r+" "+recFieldValues[l]);
                          	//System.out.println("l"+l+" r"+r+" "+lst.get(l).toString()+" "+lst.get(l).toString().length()+" "+width);
                          	
                          	// exists in last and in for all after last
                          	 int width=0; 
                          	int lengthStr = 0;
                          	if(arrayRecValues[c]!=null)
                          	{
                          		lengthStr = arrayRecValues[c].toString().length();
                          	}
                          	width = Math.max(lengthStr+1, col.getColumnLengthMaxActual());
                          	//System.out.println("ReportCalcs.fetchDataGroup "+rowCount+" "+c+" "+colName+" "+(lengthStr+1)+" "+width);
      			  
                            len=width; 
                      //System.out.println("ReportCalcs.fetchDataGroup "+rowCount+" "+c+" "+colName+" "+len);
                  	  if(len!=0)
                  	  {
                         //System.out.println("ReportCalcs.fetchDataGroup "+l+" length "+len);
                  	     col.setColumnLengthMaxActual(len);
                  	     //System.out.println("ReportCalcs.fetchDataGroup last "+rowCount+" len "+len);
                  	  }
                  	  else
                  	  {
                  	  	System.out.println("ReportCalcs.fetchDataGroup  len 3");
                  	  	 col.setColumnLengthMaxActual(3);
                  	  }                      	
                  }
                  else
                  {
                  	len = val.length();
                  	
                  	  //int len = listSumPagePreField.get(l).toString().length();
                  	  if(len!=0)
                  	  {
                  	  	 //len = listSumPagePreField.get(l).toString().length();
                 	//      intSumPagePreLength[l]=len;
                         //System.out.println("ReportCalcs.fetchDataGroup "+l+" length "+len);
                  	     col.setColumnLengthMaxActual(len);
                  	     //System.out.println("ReportCalcs.fetchDataGroup last "+rowCount+" len "+len);
                  	  }
                  	  else
                  	  {
                  	  	 col.setColumnLengthMaxActual(3);
                  	  }                  	
                  }
                  
                
                  
                 if(!reportType.equalsIgnoreCase("FORM")) // must exist. else print preview of report FORM the fields of the database are not been replaced as they should.
                 {    
                   boolRowToInclude = removeRowThatIsBandHeaderAndAlreadyExists(strPkForGrouping, colTable , colName, g,  entityReportBand.length,  arrayRecValues[c]);
                   //System.out.println("ReportCalcs.fetchDataOfDefinedBandsAndColumns    o=oοοοοοοο "+c+" "+g+" "+boolRowToInclude);
                   if(!boolRowToInclude)
                   {
                       break;
                   }// if break
                   else
                   {
                      //
                       
                   }
                 }// if !FORM
                }  // colCount

                    
                   
  


                    EntityReportRow entityReportRow = null;
                
            if(boolRowToInclude && !reportType.equalsIgnoreCase("FORM"))
            {
                //System.out.println("       FORM   list count   g:"+g+" "+listOfRecords.size());

               
entityReportRow = new EntityReportRow(arrayRecColumn,arrayRecValues,g, entityReportBand[g],rowCount);//intNoOfRecordBand); // record entity              

listOfRecords.add(entityReportRow); 

//System.out.println("ReportCalcs.fetchData   ------------ = = = = =  add   if     (g"+g+")   rowCount:"+rowCount+"   intNoOfRecordBand:"+intNoOfRecordBand);                
            
            }
            else if(!boolRowToInclude && reportType.equalsIgnoreCase("FORM"))
            {
               
entityReportRow = new EntityReportRow(arrayRecColumn,arrayRecValues,g, entityReportBand[g],rowCount);//intNoOfRecordBand); // record entity              

listOfRecords.add(entityReportRow); 
                

            }
            else
            {
               
               //System.out.println("error ReportCalcs.fetchData  NOT DEFINED   g:"+g+"   boolRowToInclude:"+boolRowToInclude+"   reportType:"+reportType);     
            }                
                
               
            
            
            
            
            
            
            
         } //for(int g=0;g<listOfListOfColumns.size();g++)

          } //  rs.next
                 
             // System.out.println("ReportCalcs.fetchDataOfDefinedBandsAndColumns       listOfRecords.size():"+listOfRecords.size());
          
         
      }// try sql// try sql
      catch ( SQLException sqlex)
      {
         System.out.println("error: ReportCalcs.fetchData:  msg "+sqlex.getMessage()+" sql "+query);
         sqlex.printStackTrace();
      }         
     
           this.closeDb(); 


             for (int r = 0 ; r<listOfRecords.size();r++)
             {
   //System.out.println("ReportCalcs.fetchDataOfDefinedBandsAndColumns     r:"+r+"     listOfRecords.size():"+listOfRecords.size());
                 EntityReportRow entityReportRow = (EntityReportRow)listOfRecords.get(r);
                 RecColumn[] arrayRecColumn = entityReportRow.getRecColumn();
                 String[] arrayRecValues = entityReportRow.getFieldValues();
                 // System.out.println("ReportCalcs.fetchData            r:"+r+"     intOfEntityReportBand:"+entityReportRow.getIntOfEntityReportBand()+"     listOfRecords.size():"+listOfRecords.size());
                 String fieldGroupby =  entityReportBand[entityReportRow.getIntOfEntityReportBand()].getSqlGroupByField();
                // System.out.println("ReportCalcs.fetchData            r:"+r+"     intOfEntityReportBand:"+entityReportRow.getIntOfEntityReportBand()+"    fieldGroupby:"+fieldGroupby+"     listOfRecords.size():"+listOfRecords.size());
                 if(fieldGroupby==null)
                 {
                     fieldGroupby="";
                 }
                        
                               
            
             }
  //         listOfGroupsOfRecords.add(listOfRecords);// only 1, to be removed safely(not just remove this line)     
               
      return listOfRecords.size();
    }
    
    // not just for previous (sum of 2bands) fot for pre-previous(sum of 3 bands)
    private boolean removeRowThatIsBandHeaderAndAlreadyExists(String strPkForGrouping,String colTable, String colName, int band, int bandLength, String recValues )
    {
        boolean ret = true;         
        //System.out.println("  ALL  ReportCalcs.removeRowThatIsBandHeaderAndAlreadyExists    band:"+band+"     strPkForGrouping:"+strPkForGrouping+"       "+colTable+"."+colName+"="+recValues);
                if(strPkForGrouping!=null && colName != null && strPkForGrouping.equalsIgnoreCase(colName))  //pks
                 {
                     //for(int rc =0;rc<bandLength;rc++) 
                     //{
                      //System.out.println("  --***---ALL  ReportCalcs.removeRowThatIsBandHeaderAndAlreadyExists  =ο=ο=ο=ο=       band:"+band+"      strPkForGrouping:"+strPkForGrouping+"       colName:"+colName+"="+recValues+" ==  previous:"+arrayPreviousValue[band]);
                      if(recValues.equalsIgnoreCase(arrayPreviousValue[band]))// && previousRowCount != rowCount)
                      {
                          //System.out.println("  -- if  ReportCalcs.removeRowThatIsBandHeaderAndAlreadyExists     ----      band:"+band+"         strPkForGrouping:"+strPkForGrouping+"         "+colTable+"."+colName+"="+recValues+"   previousValue:"+arrayPreviousValue[band]);
                          ret = false;
                      }
                      else
                      {
                          //   ----   change  of   band
                          
                          arrayPreviousValue[band] = recValues;//  setted for first band in setEntity
 
                          //System.out.println("  -- else  ReportCalcs.removeRowThatIsBandHeaderAndAlreadyExists         band:"+band+"       strPkForGrouping:"+strPkForGrouping+"        "+colTable+"."+colName+"="+recValues+"     !!!   change band    previousValue:"+arrayPreviousValue[band]+"         intNoOfRecordOfPreviousBand:"+intNoOfRecordOfPreviousBand+"    listOfRecords.size():"+ listOfRecords.size() + "  intNoOfChangesOfBand:"  + intNoOfChangesOfBand);
                          ret = true;                          
                   
                    
                      }
                   //  }  // for
                      
                    /* if(previousRowCount != rowCount)
                     {
                    //  previousValue=recValues;
                          //previousG = g;
                          //previousRowCount = rowCount;
                     }*/
                    
                 }  
                else //when arrayPreviousValue[band] = nothing
                {
                    //System.out.println("-----ReportCalcs.removeRowThatIsBandHeaderAndAlreadyExists  ELSE   ----     band:"+band+"    colName:"+colName+"  strPkForGrouping:"+strPkForGrouping+"       "+colName+"="+recValues);
                    ret = true;
                }
   return ret;                
    }
  
  public void closeDb()
  {
  	db.releaseConnectionRs();
  	db.releaseConnectionRsmd();
  }

  
   public int getRowCountTotal()
   {
   	   return  rowCountTotal;
   }   
  

   /*
    * 
    * 
    * called by this.fetchDataGroup
    * 
    */
 /*   private boolean isLastRecordInPage(int rec, int page)
   {
   	 boolean ret=false;
   	 //rowCountTotal        linesPerPage
   	 if(rec>=(page*rowCountTotal)-linesOfReportHeader)
   	 {
   	 	ret=true;
   	 }    	   
   	 
   	 //System.out.println("ReportCalcs.isLastRecordInPage");
   	  return ret;
   }*/  
   
   //called from PanelPrintPreview
   public void calculateRecordsInPage(int pageToGo)
   {
   	
       
        //int [] array = caclulateRecordsInRegion(page,linesPerPage,getLinesOfReportHeader(page)+entityReportBand.length-1/*has more headers*/, rowCountTotal, -1,toRecInPage);
        	
        //fromMasterRecInPage = array[0];
        //toMasterRecInPage = array[1];
        //toRecInPage = toMasterRecInPage;
     
       if(pageToGo==1)
       {
          fromRecInPage = 0;//fromRecInPage + (linesPerPage * page)-linesPerPage;
          toRecInPage = /*fromRecInPage*/0 + linesPerPage - getLinesOfReportHeader(pageToGo)- countBandsTitle;/*for 1st band toREc*///listOfRecords.size() - fromRecInPage;//(page*linesPerPage);//fromRecInPage;//(page*linesPerPage)-(page*getLinesOfReportHeader(page) );
          if(getRowCountTotal()<toRecInPage) // when only one page is the result
          {
              toRecInPage = getRowCountTotal();
          } 
          else
          {
             // System.out.println("ReportCalcs.calculateRecordsInPage        -----oo------ IF=1  else.  getRowCountTotal():"+getRowCountTotal()+"    toRecInPage:"+toRecInPage);
          }
       System.out.println("ReportCalcs.calculateRecordsInPage        -----oo------ IF=1    pageToGo:"+pageToGo+"    linesPerPage:"+linesPerPage+"  header:"+getLinesOfReportHeader(pageToGo)+"    countBandsTitle:"+countBandsTitle+"    fromRecInPage:"+fromRecInPage+"   toRecInPage:"+toRecInPage+"     getRowCountTotal():"+getRowCountTotal());
       }
       /*else if (pageToGo == getPageTotal())
       {
           fromRecInPage = pageToGo*(linesPerPage+getLinesOfReportHeader(pageToGo)+countBandsTitle)-linesPerPage-getLinesOfReportHeader(pageToGo)-countBandsTitle;
           toRecInPage = getRowCountTotal();//fromRecInPage + linesPerPage + getLinesOfReportHeader(page);

       }*/
       else 
       {
           
           //fromRecInPage = pageToGo*(linesPerPage+getLinesOfReportHeader(pageToGo)+countBandsTitle)-linesPerPage-getLinesOfReportHeader(pageToGo)-countBandsTitle;
           fromRecInPage = /*fromRecInPage*/ (pageToGo-1) * (linesPerPage) - (pageToGo-1)*getLinesOfReportHeader(pageToGo)-(pageToGo-1)*countBandsTitle;// + pageToGo;//pageToGo;//listOfRecords.size() - fromRecInPage;//(page*linesPerPage);//fromRecInPage;//(page*linesPerPage)-(page*getLinesOfReportHeader(page) );
           toRecInPage =  fromRecInPage + linesPerPage  - getLinesOfReportHeader(pageToGo)-countBandsTitle;// -countBandsTitle;//-getLinesOfReportHeader(pageToGo); 
    System.out.println("ReportCalcs.calculateRecordsInPage        -----oo------  ELSE   pageToGo:"+pageToGo+"    linesPerPage:"+linesPerPage+"  header:"+getLinesOfReportHeader(pageToGo)+"    countBandsTitle:"+countBandsTitle+"    fromRecInPage:"+fromRecInPage+"   toRecInPage:"+toRecInPage+"     getRowCountTotal():"+getRowCountTotal());               
          if(getRowCountTotal()<toRecInPage) // in last page
          {
              toRecInPage = getRowCountTotal();
          }
       }

      //System.out.println("ReportCalcs.calculateRecordsInPage        -----oo------     pageToGo:"+pageToGo+"    linesPerPage:"+linesPerPage+"  header:"+getLinesOfReportHeader(pageToGo)+"    countBandsTitle:"+countBandsTitle+"    fromRecInPage:"+fromRecInPage+"   toRecInPage:"+toRecInPage+"     getRowCountTotal():"+getRowCountTotal());
       
        //System.out.println("ReportCalcs.calculateRecordsInPage        -----  pageToGo:"+pageToGo+"       linesPerPage:"+linesPerPage+"  getLinesOfReportHeader(page):"+getLinesOfReportHeader(pageToGo)+"      rowCountTotal:"+rowCountTotal+"  fromRecInPage:"+fromRecInPage+" - toRecInPage"+toRecInPage);
        //System.out.println("ReportCalcs.calculateRecordsInPage ===== page:"+page+"  linesOfReportHeader:"+linesOfReportHeader);
      
       
   
   }
   
   
   private int getLinesOfReportHeader(int page)    // filters
   {
    int ret=0;
    // have linesOfReportHeader as public
   	if(entityFilterSettings!=null)
   	{
            if(intSettingsReport[2]==INTSETTINGSREPORT_SHOWINALLPAGES)
            {
   	  	linesOfReportHeader=entityFilterSettings.length;
            }
            else if (intSettingsReport[2]==INTSETTINGSREPORT_SHOWFIRSTPAGE)
            {
	        	if(page==1)
   	        	{
                            linesOfReportHeader=entityFilterSettings.length;
                        }
                        else
                        {
                            linesOfReportHeader=0;
                        }
                
            }
            else if(intSettingsReport[2]==INTSETTINGSREPORT_NOTSHOW)
            {
                linesOfReportHeader=0;
            }
            else
            {
                System.out.println("ReportCalcs.getLinesOfReportHeader UNKNOWN else intSettingsReport[2] "+intSettingsReport[2]);
                linesOfReportHeader=0;
            }
   	}
   	else
   	{
   		linesOfReportHeader=0;
   	}       
        
        
       /* if(entityReportBand.length>1)
        {
            linesOfReportHeader=(linesOfReportHeader*entityReportBand.length);
            System.out.println("ReportCalcs.getLinesOfReportHeader OOOOOOOOOOOOOOOOOOO linesOfReportHeader:"+linesOfReportHeader+" = entityReportBand.length"+(entityReportBand.length-1));
        }*/
        
       
       return linesOfReportHeader;
   }

 
    public ArrayList getListSumPagePre()
    {  
        //System.out.println("ReportCalcs.getListSumPagePre.size   -- --  -> "+listSumPagePre.size());
        
      /*for(int l = 0;l<listSumPagePre.size();l++)
      {
      	ArrayList lst = (ArrayList)listSumPagePre.get(l);
      	 System.out.println("ReportCalcs.getListSumPagePre lst -------"+l+"  "+lst.size());
      }*/
    	return listSumPagePre;
    }   

    public ArrayList getListSumRecCount()
    {
    	//System.out.println("ReportCalcs.getListSumRecCountMaster "+listSumRecCountMaster.size());
    	return listSumRecCount;
    }  

    /**
     * 
     * called by PanelPrintPreview.getPageTotal.
     */
    public int getPageTotal()
    { 
        //wrong
         double d = (double)(rowCountTotal+linesOfReportHeader+countBandsTitle)/linesPerPage;
          double d1 = (double)(rowCountTotal+(linesOfReportHeader+countBandsTitle)*d)/linesPerPage;
        double d2 = (double)(rowCountTotal+(linesOfReportHeader+countBandsTitle)*d1)/linesPerPage;
        double d3 = (double)(rowCountTotal+(linesOfReportHeader+countBandsTitle)*d2)/linesPerPage;
        double d4 = (double)(rowCountTotal+(linesOfReportHeader+countBandsTitle)*d3)/linesPerPage;
       
      int ret = (int)Math.ceil(((double)(rowCountTotal+((linesOfReportHeader+countBandsTitle)*d4))/linesPerPage)); // floor is the less // Math.ceil
      
      /*if(toRecInPage <= (rowCountTotal+linesOfReportHeader+countBandsTitle))//(ret==0)// when math is zero we need a page
      {
          ret++; 
      }*/
      
       //fromRecInPage = rowCountTotal - fromRecInPage +  linesPerPage;
      // fromRecInPage=toRecInPage;
      System.out.println("ReportCalcs.getPageTotal    ******     rowCountTotal:"+rowCountTotal+" + ((linesOfReportHeader "+linesOfReportHeader+" + countBandsTitle:"+countBandsTitle+") *"+d4+") / linesPerPage "+linesPerPage+" = "+ret+"      d4:"+d4);
      return ret;
    }
    
    public ArrayList getListOfListOfColumns()
    {
    	return listOfListOfColumns;
    }
	

    public PageFormat getPageFormat()
    {
    	return pageFormat;
    }
    
    public int getFromRecInPage()
    {
    	//calculateRecordsInPage(page);
    	return fromRecInPage;
    }
    
    public int getToRecInPage()
    {
    	//calculateRecordsInPage(page);
    	return toRecInPage;
    }

     public ArrayList getListOfRecords()
    {
        //System.out.println("ReportCalcs        ==  "+listOfRecords.size());
    	//return (ArrayList)listOfGroupsOfRecords.get(listOfGroupsOfRecords.size()-1);
        return listOfRecords;
    }    
    
     public ArrayList getListSumsOfPage()
    {
    	
    	return listSumsOfPage;
    }
    
     public ArrayList getListPrimKeyVal()
    {
    	return listPrimKeyVal;
    } 
 }