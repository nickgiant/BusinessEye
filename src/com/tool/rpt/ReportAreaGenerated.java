// created 22-06-2008

package com.tool.rpt;

import com.tool.gui.DialogMulti;
import com.tool.model.LookUpMgt;
import com.tool.gui.PanelEditOneDataRec;
import com.tool.model.*;
import com.tool.guicomps.*;
import static com.tool.guicomps.Constants.PANEL_TYPE_EDITONEDATAONEREC;
import com.tool.utils.*;
import com.tool.jdbc.*;

import javax.swing.*;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.Box;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;

import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PageFormat;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Cursor;

import java.awt.Graphics;
import java.awt.GridBagConstraints;

  import java.sql.ResultSet;
  import java.sql.SQLException;
  import java.sql.ResultSetMetaData;
  
  import java.util.ArrayList;  
  import java.util.Vector;  
import javax.swing.ImageIcon;


 public class ReportAreaGenerated extends JPanel implements Constants, Printable
 {
 	
 //	 private String query;
 //private String entity;
 private String title;
 private String subTitle;
 private String queryReport;

 private boolean isDotmatrix;
 private boolean showSummaryInPage;
 	
 private UtilsDouble uDouble;
 private UtilsString utilsString;
 private UtilsDate uDate;
 private UtilsGui utilsGui;
 private UtilsPanelReport utilsPanelReport;
 private Database db;
 	
 private int pageCurrent;
 private int columnCountHeader;
 private String strPrintDotMatrix;
 	
 private boolean showVerticalLines;
 private boolean showHorizontalLines;
     
 private Font fontNormal;
   private Font fontBold;
   private FlowLayout layoutHorizBorderLeft;
   private FlowLayout layoutHorizBorderCenter;
   private FlowLayout layoutHorizBorderRight;
   private Dimension separationVerticalLine;
   //private Dimension separationVertical2Line;
   private Dimension separationVertical3Line;
   
   private int intColWidthOfTxt;
 	
 	private PageFormat pageFormat;
 	
 	   private int fromRecInPage;
   private int toRecInPage=0;
   private int pageTotal;
   
//    private int columnCount =0;
 private int rowCount =0;
 
  private ArrayList listOfListOfColumns = new ArrayList(); // holds column data
//   private ArrayList listRecord = new ArrayList(); // holds column value
   private ArrayList listRecordField; // initialized on fetchData because else it doesnt calculate well
 	
    private ArrayList listSumsOfPage;
   private ArrayList listSumPagePre = new ArrayList();
   private ArrayList listSumPagePreField;
   private ArrayList listSumRecCount;	
   	
   	private ArrayList listOfRecords;
 	
 	private ArrayList listRowHeaderInMasterRecsPointer = new ArrayList();
        private ArrayList showColumnsPerBand;
 	
 	private int[] showColumnsHeader;
         //private int[] showColumns;
 	
 	private String strPageSizeDotmatrix;
 	private int charsPerLine;
 	private String dotmatrixHorizLineHeader="";
 	
 	private int lines;
 	private int linesPerPage;
 	
 	private JPanel panelArea;
 	private JPanel panelPageMain;
 	private JPanel panelAreaHeader;
 	private JPanel panelAreaFooter;
 	
 	   private String[] sumPage;
   private String[] sumPagePre;// sum of previous pages
 	
 	private boolean hasHeaderNfooter;
 	
 	private ArrayList listRowHeaderInMasterRecsLevel;
 	private ReportPanelPage reportPanelPage;
 	
 	private JPanel panelLineLayoutHeader;
 	
 	private int dotmatrixCpi;
 	private int countOfGroups;
 	private int rowSelectable =0;
 	
 	
 	//private boolean []showLines;
 	private int[] intSettingsReport;
 	private boolean[] boolSettingsReport;
        //private EntityReportBand[] entityReportBand;
        private final int AREA_HEADER = 0;
        private final int AREA_MAIN = 1;
        private final int AREA_FOOTER = 2;
        private String viewType;
 	//private String query;
        Color colWhite = new Color(255,255,255);//Color.white;
        Color colWhiteDark1 = new Color(240,240,240);
        Color colWhiteDark2 = new Color(224,224,224);//colWhite.darker();//Color.DARK_GRAY;
 	//Color colWhiteDark2 = new Color(208,208,208);//colWhite.darker();//Color.DARK_GRAY;
        //Color colWhiteDark2 = new Color(192,192,192);//colWhite.darker();//Color.DARK_GRAY;
        
        
   public ReportAreaGenerated()
    {
            try
           {    initialize();    }
           catch (Exception e)
           {   e.printStackTrace();    }
    }

	private void initialize() throws Exception
    {  
       panelPageMain = new JPanel();
       
       utilsGui = new UtilsGui();
       db = new Database();
       
       uDouble=new UtilsDouble();
       utilsString = new UtilsString();
       uDate= new UtilsDate();
       utilsPanelReport=new UtilsPanelReport();
       
       showVerticalLines=true;
       showHorizontalLines=true;
        
       JTextField txt = new JTextField();
       fontNormal = new Font(txt.getFont().getFontName(), txt.getFont().getStyle(), 9);
       fontBold = new Font("Arial", Font.BOLD, 9);
       
       layoutHorizBorderLeft = new FlowLayout(FlowLayout.LEFT,2,0); //hgap, int vgap 2,4 for all rows
       layoutHorizBorderCenter = new FlowLayout(FlowLayout.CENTER,2,0); //hgap, int vgap 2,4 for all rows
       layoutHorizBorderRight = new FlowLayout(FlowLayout.RIGHT,2,0); //2,4 for footer row
       separationVerticalLine = new Dimension(1,9);//was 15 // width, height
       //separationVertical2Line = new Dimension(1,15*2);
       //separationVertical3Line = new Dimension(1,15*3);
       
       intColWidthOfTxt=2; // width of JTextFields the smaller the smaller the fields


       
       panelArea=new JPanel();
       panelArea.setLayout(new BorderLayout());  
       
       
           panelPageMain.setLayout(new BoxLayout(panelPageMain, BoxLayout.PAGE_AXIS));
           panelPageMain.setBackground(Color.white);// white
           panelPageMain.setBorder(null);
           panelArea.setBackground(Color.white);// white
           panelArea.setBorder(null);      
           
           panelArea.add(panelPageMain, BorderLayout.CENTER);       
       
       this.setBackground(Color.white);
      this.add(panelArea);
    }
    

    
        /**
         * 
         *    Called by ReportPanelPage.generateMain().
         */
  public void setEntity(String titleIn,String subTitleIn,/*EntityReportBand[] entityReportBandIn,*/ArrayList listOfRecordsIn,boolean isDotmatrixIn, boolean hasHeaderNfooterIn,
          String viewTypeIn, String strPageSizeDotmatrixIn, int page,int fromRecInPageIn,int toRecInPageIn,String queryReportIn,ArrayList listOfListOfColumnsIn,
          ArrayList showColumnsPerBandIn, int[] showColumnsHeaderIn, /*int columnCountIn,int columnCountHeaderIn,*/ ArrayList listSumPagePreIn, ArrayList listRecordFieldIn,
          ArrayList listSumRecCountIn,  ArrayList listSumsOfPageIn,/*ArrayList listRowHeaderInMasterRecsPointerIn, ArrayList listRowHeaderInMasterRecsLevelIn,*/ int pageTotalIn, 
          PageFormat pageFormatIn, boolean showSummaryInPageIn, int dotmatrixCpiIn,  int[] intSettingsReportIn/*,  boolean[] boolSettingsReportIn*/ )
   {

   	title=titleIn;
   	subTitle=subTitleIn;
        //entityReportBand=entityReportBandIn;
        
   	queryReport=queryReportIn;
   	listOfRecords=listOfRecordsIn;
   	isDotmatrix=isDotmatrixIn;
   	hasHeaderNfooter=hasHeaderNfooterIn;
        viewType=viewTypeIn;
   	fromRecInPage=fromRecInPageIn;
   	toRecInPage=toRecInPageIn;

   	listOfListOfColumns=listOfListOfColumnsIn;
   	listSumPagePre=listSumPagePreIn;

   	listRecordField=listRecordFieldIn;
   	listSumRecCount=listSumRecCountIn;

   	strPageSizeDotmatrix=strPageSizeDotmatrixIn;
   	pageTotal=pageTotalIn;
   	pageFormat=pageFormatIn;
        showColumnsPerBand = showColumnsPerBandIn;
   	//showColumns=showColumnsIn;
   	showColumnsHeader = showColumnsHeaderIn;
   	showSummaryInPage=showSummaryInPageIn;
   	dotmatrixCpi=dotmatrixCpiIn;

   	
   	intSettingsReport=intSettingsReportIn;
   	//boolSettingsReport= boolSettingsReportIn;
   	
       reportPanelPage = new ReportPanelPage();

       
       panelAreaHeader=new JPanel();
       panelAreaHeader.setLayout(new BoxLayout(panelAreaHeader, BoxLayout.PAGE_AXIS));
       panelAreaHeader.setBackground(Color.white);

     countOfGroups = listOfListOfColumns.size();


   
     //System.out.println(" ============== ReportAreaGenerated.setEntity  ");

     /*boolean [] boolSettingsReport = entityReportBand[0].getBoolSettings();
    	if (boolSettingsReport == null)
    	{
    		showHorizontalLines=true;
    		showVerticalLines=true;
    	}
    	else 
    	{
    	    showHorizontalLines= boolSettingsReport[0];
    	    showVerticalLines= boolSettingsReport[1];
    	}*/

       if(strPageSizeDotmatrixIn!=null && strPageSizeDotmatrixIn.equalsIgnoreCase("A3"))
       {
       	 charsPerLine=DOTMATRIX_CHARS_PER_LINE_A3;
       }
       else
       {

       	 if(dotmatrixCpi==-1)
       	 {
       	 	charsPerLine=DOTMATRIX_CHARS_PER_LINE_A4_CPI10;
       	 }
       	 else if(dotmatrixCpi==10)
       	 {
       	 	charsPerLine=DOTMATRIX_CHARS_PER_LINE_A4_CPI10;
       	 }
       	 else if(dotmatrixCpi == 15)
       	 {
       	 	charsPerLine=DOTMATRIX_CHARS_PER_LINE_A4_CPI15;
       	 }       	
       	
       }       
       for(int i=0; i<charsPerLine;i++)
   	   {
   	      dotmatrixHorizLineHeader=dotmatrixHorizLineHeader+DOTMATRIX_LINE_CHAR_HEADER;
   	   }
   	
   	
   	uDouble = VariablesGlobal.globalUtilsDouble;
   	uDate.readFromFileDateFormats();
   	
   	rowSelectable =0;
       
   }

  /*
  * called by ReportPanelPage.generateMain
  */
   public void generateArea(int page)
   {
     if(listOfRecords.size()!=0)
     {
   	pageCurrent=page;
   	//System.out.println("ReportAreaGenerated.generateArea    0000000000   page:"+page+"         listOfRecords.size():"+listOfRecords.size());	
   	strPrintDotMatrix="";
       // header
        strPrintDotMatrix=strPrintDotMatrix+dotmatrixHorizLineHeader+TXT_CHANGELINE; 

       for(int g=0;g<countOfGroups;g++)
       {
            generateAreaHeader(page,g);
            panelArea.add(panelAreaHeader, BorderLayout.PAGE_START);    // header	
       }
        panelAreaHeader.setBorder(BorderFactory.createLineBorder(Color.black));
        strPrintDotMatrix=strPrintDotMatrix+dotmatrixHorizLineHeader+TXT_CHANGELINE; 
   	generateAreaMain(page);      // main
   	if(countOfGroups<=1)
        {
        generateAreaFooter(page,countOfGroups-1);// the last one  // and 	at  ReportPanelPage
        }
     }
     else
     {
   		System.out.println("ReportAreaGenerated.generateArea        listOfRecords.size():"+listOfRecords.size());
     }
     
     
     //System.out.println("ReportAreaGenerated.generateArea    --------------------------    listOfRecords.size():"+listOfRecords.size());
     
   }
   
   
   /*
    * TODO
    * ----> listSumPagePre size is not equal with columns count
    * ----> also when a column data is null like tel2 in buyers
    * 
    * 
    */
   private boolean getIfColumnIsToBeShown(int column, int page,int group,RecColumn recColumn)//,int lengthOfColumns,int intAreaHeaderOrMainOrFooter)
   {
       boolean ret = true;
                 
        boolean showColumnWithoutData = true;
        String colClass= recColumn.getColumnClass();
        String colName = recColumn.getColumnName();
    //System.out.println("ReportAreaGenerated.getIfColumnIsToBeShown   ++ --   column:"+column+"     group:"+group+"    colName:"+colName+"    page:"+page +"     listOfRecords.size:"+listOfRecords.size());         
//        boolSettingsReport = entityReportBand[group].getBoolSettings();
       
    	if (boolSettingsReport == null)
    	{
              showColumnWithoutData=true;
    	}
    	else 
    	{
              showColumnWithoutData = boolSettingsReport[3];
    	}

//        String txtSum3="";
      
      //if(!viewType.equalsIgnoreCase("TDMR"))  //intAreaHeaderOrMainOrFooter==AREA_MAIN)
     // {    //  && !viewType.equalsIgnoreCase("TDMR")
//    if(colClass.equalsIgnoreCase("java.lang.Double") || colClass.equalsIgnoreCase("java.math.BigDecimal") || colClass.equalsIgnoreCase("java.lang.Long"))// || colClass.equalsIgnoreCase("java.lang.Integer"))
//    {
        
  //ArrayList lstAfter=new ArrayList();
              /*  if(viewType.equalsIgnoreCase("TDMR"))
                {
         lstAfter = (ArrayList)listSumPagePre.get(group); // get sum arraylist, page starts from 1 while arraylist from 0
    //System.out.println("ReportAreaGenerated.getIfColumnIsToBeShown    -------  column:("+column+")    group:"+group+"    colName:"+colName+"    listSumPagePre.size:"+listSumPagePre.size()+"    lstAfter.size:"+lstAfter.size()+"  lstAfter.get(column):"+lstAfter.get(column)+"   showColumnWithoutData:"+showColumnWithoutData+"  page:"+page);         
                }
                else
                {*/
  //      lstAfter = (ArrayList)listSumPagePre.get(page); // get sum arraylist, page starts from 1 while arraylist from 0                    
               // }
 //       txtSum3=uDouble.getDoubleReading((Object)lstAfter.get(column),false);
 //        System.out.println("ReportAreaGenerated.getIfColumnIsToBeShown  -------  column:("+column+")    group:"+group+"     page:"+page+"   colName:"+colName+"    listSumPagePre.size:"+listSumPagePre.size()+"    lstAfter.size:"+lstAfter.size()+"    showColumnWithoutData:"+showColumnWithoutData+"    txtSum3:"+txtSum3);         
        //String txtSum3=lstAfter.get(column)+"";       
       
 //   }
       //if(txtSum3.equalsIgnoreCase("") && !showColumnWithoutData &&(colClass.equalsIgnoreCase("java.lang.Double") || colClass.equalsIgnoreCase("java.math.BigDecimal") || colClass.equalsIgnoreCase("java.lang.Long")))// || colClass.equalsIgnoreCase("java.lang.Integer")))
        if(!showColumnWithoutData &&(colClass.equalsIgnoreCase("java.lang.Double") || colClass.equalsIgnoreCase("java.math.BigDecimal") || colClass.equalsIgnoreCase("java.lang.Long")))// || colClass.equalsIgnoreCase("java.lang.Integer")))
       {
           ret=false;
       }
       else
       {
           ret=true;
       }

       return ret;
   }
   
   private void generateAreaHeader(int page, int noOfGroup)
   {
	
          JPanel panelPageHeader3 = new JPanel();
          panelPageHeader3.setLayout(layoutHorizBorderLeft);//layoutHorizBorderCenter);
          panelPageHeader3.setBackground(Color.white);
          
          panelPageHeader3.setBorder(null);
        //panelPageHeader3.setBorder(BorderFactory.createLineBorder(Color.black));
          panelAreaHeader.add(panelPageHeader3);
      
          //panelArea.add(panelAreaHeader, BorderLayout.PAGE_START);
   	            
   	        JPanel colSeparation = new JPanel();
            if(showVerticalLines)     
            {
            
                 colSeparation.setBackground(Color.lightGray);
            }
            else
            {
                 colSeparation.setBackground(Color.white);
            }
                 colSeparation.setPreferredSize(separationVerticalLine);
                 panelPageHeader3.add(colSeparation);
            
            if(noOfGroup<countOfGroups-1)
            {
                JPanel panelLine = new JPanel();
                FlowLayout lineLayout = new FlowLayout();
                lineLayout.setVgap(1);
                panelLine.setBackground(Color.lightGray);
                panelLine.setLayout(lineLayout);
                panelAreaHeader.add(panelLine);
            }  


   	      
// -------------------------------------  dot matrix --------------------------------------------   	        	      	     
   	     //strPrintDotMatrix=strPrintDotMatrix+dotmatrixHorizLineHeader+TXT_CHANGELINE; 
         strPrintDotMatrix=strPrintDotMatrix +"|";
         
//----------------------------------------------------------------------------------------------------         
         //columnCount = rsmd.getColumnCount();
         //also in generate main
         RecColumn[] arrayOfColumns = null;//new Object[columnCount];
         //System.out.println("ReportAreaODMRec.setEntity -> count of groups "+listOfListOfColumns.size()+" hasHeaderNfooter "+hasHeaderNfooter);
         /*if(hasHeaderNfooter)
         {
            arrayOfColumns = (RecColumn[])listOfListOfColumns.get(1);//2 // 2 is the master 0 is the header 1 is the footer	
         }
         else
         {
         	arrayOfColumns = (RecColumn[])listOfListOfColumns.get(0);// 2 is the master 0 is the header 1 is the footer
         }*/

         arrayOfColumns = (RecColumn[])listOfListOfColumns.get(noOfGroup);// 2 is the master 0 is the header 1 is the footer

//         EntityReportRow entRepRow = (EntityReportRow)listOfRecords.get(noOfGroup);
         int[] showCols = (int[])showColumnsPerBand.get(noOfGroup);//showColumns; //showColumnsHeader;   //showColumns;// either showColumnsHeader or showColumns
//         int[] showCols panelReportSettings.
         int columnCount = showCols.length;//arrayOfColumns.length;
       // System.out.println("ReportAreaGenerated.generateAreaHeader      noOfGroup "+noOfGroup+"     columnCount "+columnCount+"       page"+page+"     showCols.length:"+showCols.length+"       arrayOfColumns.length:"+arrayOfColumns.length);         
         //System.out.println("ReportAreaODMRec.setEntity columnCount"+columnCount);
         for (int i = 0; i < showCols.length; i++)//  i = fields
         {
         	  //            String columnName = rsmd.getColumnLabel(i); //get colunm name
                           
            //System.out.println("ReportAreaGenerated.generateAreaHeader "+i+" columnCount:"+columnCount);
           //entRepRow.getEntityReportBand().getEntityReportBandFields()[i].getIsVisible();
           
            if (showCols[i]!=0 ) // -1 because ArrayList from DialogReportSetting starts from 0 	
            {
      //      System.out.println("ReportAreaGenerated.generateAreaHeader "+i+"   columnCount:"+columnCount);
       	
                 RecColumn col = (RecColumn)arrayOfColumns[i];
                 int columnWidth=  col.getColumnLength();//getColumnLengthMaxActual(); //intSumPagePreLength[i];
                 String colClass= col.getColumnClass();	
                 String columnName = col.getColumnName();
                 String columnCaption = col.getColumnCaption();
            boolean isColumnToBeShown =  getIfColumnIsToBeShown(i,page,noOfGroup,col);//,arrayOfColumns.length,AREA_HEADER);     
            if(isColumnToBeShown)
            {
                //String columnName = rsmd.getColumnLabel(i); //get colunm name
              //System.out.println("ReportAreaGenerated.generatePageHeader "+columnName+" "+i+" true");
              //int columnWidth = rsmd.getColumnDisplaySize(i);//get column width
                 //String columnType = rsmd.getColumnTypeName(i);
             // make column name smaller
             if (columnCaption.length()>columnWidth)
             {
                 char chrLabel[] = new char[columnWidth];
                 columnCaption.getChars(0,columnWidth,chrLabel,0);
                 //copyValueOf  produces unrecognised characters                     
                 //columnCaption="";
                 for (int c=0; c<chrLabel.length; c++)
                 {   columnCaption= columnCaption +chrLabel[c]  ;     }
                 
     //            System.out.println("ReportAreaGenerated.generatePageHeader   shown"+i+"     columnName:"+columnName+"     columnCaption:"+columnCaption);
             }
             else
             {
                 //System.out.println("ReportAreaGenerated.generatePageHeader   notshown"+i+"     columnName:"+columnName+"     columnCaption:"+columnCaption);
             }

      
                 JTextField txtTitle = new JTextField(columnWidth-columnWidth/intColWidthOfTxt);
                 txtTitle.setHorizontalAlignment(SwingConstants.CENTER);
                 txtTitle.setBorder(null);
                 txtTitle.setFont(fontNormal);
                 txtTitle.setBackground(Color.white);
                 txtTitle.setEditable(false);
                 txtTitle.setText(columnCaption);
                 panelPageHeader3.add(txtTitle);
                 
                 JPanel colSeparationIn = new JPanel();
            if(showVerticalLines)     
            {
                 colSeparationIn.setBackground(Color.lightGray);
             }
             else
             {
                 colSeparationIn.setBackground(Color.white);
             }
                 colSeparationIn.setPreferredSize(separationVerticalLine);
                 panelPageHeader3.add(colSeparationIn);
                 
                 
                 
                 //String colClass = rsmd.getColumnClassName(i);
                  
                  

//--------------------------------------   dot matrix -----------------------------------------------
                
                
                strPrintDotMatrix=strPrintDotMatrix+columnCaption;
                 
                 String strSpaces ="";
                 int spaces=0;
                 // if double or long show smaller length
                 /*if(colClass.equalsIgnoreCase("java.lang.Double")) //&& columnWidthDoubleDotmatrix<columnWidth)
                 {
                   spaces =columnWidthDoubleDotmatrix-columnName.length();
                 }
                 else if(colClass.equalsIgnoreCase("java.lang.Long"))//&& columnWidthDoubleDotmatrix<columnWidth)
                 {
                  	spaces =columnWidthDoubleDotmatrix-columnName.length();
                 }
                 else
                 {*/
                 	spaces =columnWidth-columnName.length();
                 //}
                 
                 for (int s =1; s<=spaces; s++)
                 {
                 	strSpaces=strSpaces+" ";
                    
                 }
                 
                  strPrintDotMatrix=strPrintDotMatrix+strSpaces;
                  strPrintDotMatrix=strPrintDotMatrix +"|";
                  
              } //&& isColumnToBeShown
             }//if showColumnsIn
         }//for
         strPrintDotMatrix=strPrintDotMatrix+TXT_CHANGELINE;
        // strPrintDotMatrix=strPrintDotMatrix+dotmatrixHorizLineHeader+TXT_CHANGELINE; 
   	
 	
   	
   }
   
   /*
       
   
   
   */
   private void generateAreaMain(int page)
   {

        lines = 0;
        //int pageCurrent=1;
   	    int headers=1;
   	 
   	     //also in areaheader
         RecColumn[] arrayOfColumns = null;//new Object[columnCount];
         //int lengthMax=0;
        // System.out.println("ReportAreaGenerated.generateAreaMain   - - - - - perhaps useless   [[hasHeaderNfooter:"+hasHeaderNfooter+"]] ");
 
         
            
         
         
      	 double[] amountDouble=null;
          int[] amountInt=null; 
         RecColumn[] arrayRecColumn=null;
         int colCount = 0;
   
  
     //System.out.println("ReportAreaGenerated.generateAreaMain  +O+       fromRecInPage:"+fromRecInPage+"  toRecInPage:"+toRecInPage+"     listOfRecords.size():"+listOfRecords.size());        
         for(int r =fromRecInPage ; r<toRecInPage; r++)// go to next record
         {     
            EntityReportRow entityReportRow;
            int intGroup;
     //System.out.println("ReportAreaGenerated.generateAreaMain  ++++  r:"+r+"        listOfRecords.size:"+listOfRecords.size()+"       fromRecInPage:"+fromRecInPage+"  toRecInPage:"+toRecInPage);        
            entityReportRow = (EntityReportRow)listOfRecords.get(r);
            intGroup = entityReportRow.getIntOfEntityReportBand();        
            
            
            EntityPanel[] entityPanelDrill = entityReportRow.getEntityReportBand().getEntityPanelDrill();
            
     
            
         //for(int g=0;g<listOfListOfColumns.size();g++)
        //{
             arrayRecColumn = (RecColumn[])listOfListOfColumns.get(intGroup);

                
             colCount = arrayRecColumn.length;// .size();                
         //}
     
           //colCount = listOfListOfColumns.size();
            sumPage = new String[colCount];
      	    amountDouble= new double[colCount];
            amountInt=new int[colCount];     
     
     
     //System.out.println("ReportAreaGenerated.generateAreaMain  +O+       fromRecInPage:"+fromRecInPage+"  toRecInPage:"+toRecInPage+"      r:"+r+"      intGroup:"+intGroup+"     colCount:"+colCount);        
     
          
             
//              System.out.println("ReportAreaGenerated.generateAreaMain      r:"+r+"   fromRecInPage:"+fromRecInPage+"  toRecInPage:"+toRecInPage+"     colCount:"+colCount+"    listOfRecords.size:"+listOfRecords.size()+"      intGroup:"+intGroup);

          
           
//          System.out.println("ReportAreaGenerated.generateAreaMain   r["+r+"] "+arrayRecValuesT[1]+" - "+arrayRecValuesT[2]+" - "+arrayRecValuesT[3]+" arrayRecValues.length"+arrayRecValuesT.length+" arrayOfColumns.length"+arrayOfColumns.length);  
          //  System.out.println("ReportAreaGenerated.generateAreaMain         o    r:("+r+")        intGroup:"+intGroup+"    listOfRecords.size"+listOfRecords.size()); 
           if(intGroup<countOfGroups-1)
           {
           	  rowSelectable=rowSelectable+1;// entityReportRow.getRecordNoOfBand();  //rowSelectable+1;  // row for tooltip
           }
                       
            
            //System.out.println("ReportAreaGenerated.generateAreaMain r "+r +" "+entRepRow);
//            arrayOfColumns = (RecColumn[])entityReportRow.getRecColumn();  
            //showCols=showColumns;// either showColumnsHeader or showColumns
             
           lines++;	
         	
           if(lines==linesPerPage*headers+1)
           {	
             
              headers++;
           }
	
           JPanel panelPageMainRow = new JPanel();
           panelPageMainRow.setLayout(layoutHorizBorderLeft);//layoutHorizBorderCenter);  // or layoutHorizBorderLeft
           

           // to change also in mouse exited
           if(intGroup == 0)
           {
                panelPageMainRow.setBackground(colWhite); // set collor and horiz left center or right
           }
           else if(intGroup == 1)
           {
               panelPageMainRow.setBackground(colWhiteDark1);  
           }   
           else if(intGroup == 2)
           {
               panelPageMainRow.setBackground(colWhiteDark2);  
           }
           else
           {
               panelPageMainRow.setBackground(Color.red);  
           }
   

          /*final JPanel panelPageMainRowFinal =panelPageMainRow;
              
                panelPageMainRowFinal.addMouseListener(new MouseAdapter()
                {       	
                
                 public void mouseEntered(MouseEvent e)
                 {
                   // panelPageMainRowFinal.setBackground(Color.yellow);
                    panelPageMainRowFinal.setCursor(new Cursor(Cursor.HAND_CURSOR));
                 }
                 
                 public void mouseExited(MouseEvent e)
                 {
                    //panelPageMainRowFinal.setBackground(Color.white);
                    panelPageMainRowFinal.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                 }
  
                 
                });  */

                panelPageMain.add(panelPageMainRow);

                JPanel panelLine = new JPanel();
                  //panelLine.setBorder(null);
            
            
             // if no groups
             if(intGroup==-1)
             {
                 intGroup=0;
             }
         
         boolean  showZero = false;
         
         boolean showColumnWithoutData = false;
        //EntityReportRow entityReportRow = (EntityReportRow)listOfRecords.get(r);
         //entityReportRow.getEntityReportBand().getBoolSettings();
         boolSettingsReport = entityReportRow.getEntityReportBand().getBoolSettings();
    	if (boolSettingsReport == null)
    	{
    		showHorizontalLines=true;
    		showVerticalLines=true;
                showZero = false;
                showColumnWithoutData=false;
                             
    	}
    	else 
    	{
    	    showHorizontalLines= boolSettingsReport[0];
    	    showVerticalLines= boolSettingsReport[1];
            showZero = boolSettingsReport[2];
            showColumnWithoutData = boolSettingsReport[3]; 
                     
    	}                
             
              //System.out.println("ReportAreaGeneration.generateAreaMain  page:"+page+"   intGroup:["+intGroup+"]   showHorizontalLines:"+showHorizontalLines+"  showVerticalLines:"+showVerticalLines);
             if (showHorizontalLines)
             {
                  panelLine.setBackground(Color.lightGray );
             }
             else
             {
                  panelLine.setBackground(Color.white);
             }
             
              // if change group then set black line
              if(intGroup< countOfGroups-1 && countOfGroups!=1)//intGroup != entityReportRow.getEntityReportGroup().getGroupNo() )//&& entityReportRow.getEntityReportGroup().getGroupNo() < countOfGroups-1 )
              {
              	  //System.out.println("ReportAreaGenerated.generateAreaMain group "+entityReportRow.getEntityReportGroup().getGroupNo()+" intGroup "+intGroup);
              	  panelLine.setBackground(Color.black);    	  
              }
                //intGroup=entityReportRow.getEntityReportGroup().getGroupNo();
             
                  FlowLayout lineLayout = new FlowLayout();
                  lineLayout.setVgap(1);
                  //System.out.println("reportPanelOneDataManyRec "+lineLayout.getVgap()+" "+panelLine.getHeight());
                  panelLine.setLayout(lineLayout);                  
                  
                  //panelLine.setMaximumSize(new Dimension(700,1));
                  //panelLine.setSize(400,2);
                  panelPageMain.add(panelLine);
             
            
                 JPanel colSeparation = new JPanel();
            if(showVerticalLines)     
            {
                 colSeparation.setBackground(Color.lightGray );
             }
             else
             {
                 colSeparation.setBackground(Color.white);
             }    
                 colSeparation.setPreferredSize(separationVerticalLine);
                 panelPageMainRow.add(colSeparation);
            
            strPrintDotMatrix=strPrintDotMatrix +"|";   
             
            int columnWidthTotal = 0;
           

          
/*           if(hasHeaderNfooter) 
           {               
           
             System.out.println("ReportAreaGenerated.generateMain isRowHeaderInMasterNLevel "+isRowHeaderInMasterNLevel);      	
                if(isRowHeaderInMasterNLevel==1)// 1 = header
                {
      	             //amountDouble = new double[columnCountHeader];
                     //amountInt=new int[columnCountHeader];
                                     	
                     //arrayOfColumns = (RecColumn[])listOfListOfColumns.get(0);
                     EntityReportRow entRepRow = (EntityReportRow)listOfRecords.get(lines-1);
                     arrayOfColumns = (RecColumn[])entRepRow.getRecColumn();
                     
                     showCols=showColumns;// either showColumnsHeader or showColumns
                }
                else if(isRowHeaderInMasterNLevel==2)// 2 = footer
                {
      	             //amountDouble = new double[columnCountHeader];
                     //amountInt=new int[columnCountHeader];
                                     	
                	panelLineLayoutHeader=new JPanel();
                    panelLineLayoutHeader.setBackground(Color.black);
                	FlowLayout lineLayoutHeader = new FlowLayout();
                    lineLayoutHeader.setVgap(1);
                	panelLineLayoutHeader.setLayout(lineLayoutHeader); 
                	panelPageMain.add(panelLineLayoutHeader); 
                    arrayOfColumns = ( RecColumn[])listOfListOfColumns.get(1);
                    
                    showCols=showColumnsHeader;// either showColumnsHeader or showColumns
                }   
                else if(isRowHeaderInMasterNLevel==-1)// -1 = ordinary master
                {
                	 //System.out.println("ReportPanelOneDataManyRec.generateMain  master");
      	             //amountDouble = new double[columnCount];
                     //amountInt=new int[columnCount];
                	
                	 //arrayOfColumns = ( RecColumn[])listOfListOfColumns.get(1);//2
                     EntityReportRow entRepRow = (EntityReportRow)listOfRecords.get(lines-1);
                     arrayOfColumns = (RecColumn[])entRepRow.getRecColumn();                	 
                	
                	 showCols=showColumns;// either showColumnsHeader or showColumns
                }
                else
                {
      	             //amountDouble = new double[columnCount];
                     //amountInt=new int[columnCount];                	
                	
                	 showCols=showColumns;// either showColumnsHeader or showColumns
                	 System.out.println("error ReportAreaGenerated.generateAreaMain  level not defined ="+isRowHeaderInMasterNLevel);
                }   
              
                         
            }
            else
            {
                   arrayOfColumns = (RecColumn[])listOfListOfColumns.get(0);
                   showCols=showColumns;// either showColumnsHeader or showColumns
            }*/

 //           entityReportRow = (EntityReportRow)listOfRecords.get(r);
           // entityReportRow.getEntityReportGroup().getGroupNo()
            //arrayOfColumns = (RecColumn[])listOfListOfColumns.get(intGroup);
            Object[] arrayRecValues = entityReportRow.getFieldValues();
//            arrayOfColumns = (RecColumn[])entityReportRow.getRecColumn();
            arrayOfColumns = (RecColumn[])listOfListOfColumns.get(intGroup);
            //System.out.println("ReportAreaGenerated.generateMain    -     arrayOfColumns.length "+arrayOfColumns.length+"  intofentity"+intGroup);
           //System.out.println("ReportAreaGenerated.generateAreaMain r"+r+" "+arrayRecValues[1]+" - "+arrayRecValues[2]+" - "+arrayRecValues[3]+" arrayRecValues.length"+arrayRecValues.length+" arrayOfColumns.length"+arrayOfColumns.length);
            int[] showCols = (int[])showColumnsPerBand.get(intGroup);
           //System.out.print("ReportAreaGenerated.generateAreaMain    vvv      r:"+r+"    caption of band:"+entityReportRow.getEntityReportBand().getCaption()+"   noOfGroup"+intGroup+"      arrayRecValues.length:"+arrayRecValues.length); 
           //System.out.println(); 
           for(int i =1;i<=showCols.length;i++)
            {
            
               //int[] showCols = (int[])showColumnsPerBand.get(intGroup);
               //int[] showCols = showColumns;
           //System.out.println("ReportAreaGenerated.generateAreaMain       ....-....     r:("+r+"}     intGroup"+intGroup+"    recordno:"+entityReportRow.getRecordNoOfBand()+"       showCols.length:"+showCols.length+"     arrayOfColumns.length:"+arrayOfColumns.length);

            //System.out.println("ReportAreaGenerated.generateAreaMain    --------    noOfGroup"+intGroup+"   i:"+i+"   arrayOfColumns.length"+arrayOfColumns.length+"     showCols[i-1]!=0:"+showCols[i-1]);                   
            if (showCols[i-1]!=0) // because ArrayList from DialogReportSetting starts from 0 	
            {
               //System.out.println("ReportAreaGenerated.generateAreaMain    --------    noOfGroup"+intGroup+"     arrayOfColumns.length"+arrayOfColumns.length);                   
                 RecColumn col = (RecColumn)arrayOfColumns[i-1];
           
               // EntityReportBandField[] erbf =  entityReportRow.getEntityReportBand().getEntityReportBandFields();
                //boolean isColumnToBeShown = showCols[i-1]; //erbf[i-1].getIsVisible();
                boolean isColumnToBeShown = getIfColumnIsToBeShown(i-1,page,intGroup,col);//,arrayOfColumns.length,AREA_MAIN);
    //       System.out.println("ReportAreaGenerated.generateAreaMain   ++ --   column:"+(i-1)+"     isColumnToBeShown:"+isColumnToBeShown+"     intGroup:"+intGroup+"        page:"+page +"     listOfRecords.size:"+listOfRecords.size());         
                if(isColumnToBeShown)
                {
                 int columnWidth= col.getColumnLength();//intSumPagePreLength[i];// col.getColumnLengthMaxActual();
                 String colClass= col.getColumnClass();
                      
             
                 String colName = col.getColumnName();
            	
                  columnWidthTotal = columnWidthTotal+columnWidth;
                  Object colValue="";

             //System.out.println("ReportAreaGenerated.generateMain   (showCols)    i:"+i+"   caption of band "+entityReportRow.getEntityReportBand().getCaption()+"       intGroup():"+intGroup);       

   //       System.out.println("ReportAreaGenerated.generateAreaMain   OOOOOOO   r"+r+" i"+i+" "+arrayRecValues[0]+" "+arrayRecValues[1]+" length"+arrayRecValues.length+" col length"+arrayOfColumns.length);
                
                if(arrayRecValues.length!=0)
                {
                  if (arrayRecValues[i-1]!=null)
                  {
                      colValue=(Object)arrayRecValues[i-1];
                      //System.out.println("bbbbb r"+r+" i"+i+" "+colValue);
                  }
                  else
                  {// a field in record is null
                  	//System.out.println("error ReportAreaGenerated.generateMain  null listRecord "+r);
                  }
                }
                else
                {
                	System.out.println("error ReportAreaGenerated.generateAreaMain  listRecord size 0 "+r);
                	colValue="";
                }
           //System.out.print("-i:"+i+"show?"+isColumnToBeShown+"  "+colName+"="+colValue);                   
                
                String value= colValue.toString();
                if(value.equalsIgnoreCase("true"))
                {
                	value="ΝΑΙ";
                }
                else if(value.equalsIgnoreCase("false"))
                {                
                     value="ΟΧΙ";
                }
                
                JTextField txtField = new JTextField();

                txtField.setColumns(columnWidth-columnWidth/intColWidthOfTxt);

                
                 //JTextField txtField = new JTextField(columnWidth-columnWidth/intColWidthOfTxt);
                 txtField.setOpaque(false);
                 txtField.setBorder(null);
                 txtField.setFont(fontNormal);
                 txtField.setBackground(Color.white);
                 //txtField.setForeground(Color.black);
                 txtField.setEditable(false);
                 //txtField.setEnabled(false);
                 txtField.setText(value);
                 

                txtField.setToolTipText(""+r);//rowSelectable);
                 //
                 panelPageMainRow.add(txtField);
                 panelPageMain.setBackground(Color.white);

               final JTextField txtFieldFinal =txtField;
               final int intGroupFinal=intGroup;
               final int intRFinal = r;
               final EntityPanel[] entityPanelDrillFinal = entityPanelDrill;
               //final JxPanel panelPageMainRowFinal = panelPageMainRow;
               final JPanel panelPageMainRowFinal =panelPageMainRow;
                txtFieldFinal.addMouseListener(new MouseAdapter()
                {       	      
                 public void mouseEntered(MouseEvent e)
                 {
                    txtFieldFinal.setOpaque(true);
                    txtFieldFinal.setBackground(CLR_LBL_ROLL);//Color.yellow);
                    panelPageMainRowFinal.setBackground(CLR_LBL_ROLL);//Color.yellow);
                    if(entityPanelDrillFinal != null)
                    {
                      panelPageMainRowFinal.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }
                    
                    //System.out.println("mouse entered ");
                 }
                 
                 public void mouseExited(MouseEvent e)
                 {
                     panelPageMainRowFinal.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                 	txtFieldFinal.setOpaque(false);
                        
                        

                        
                        
               // to change also in generatemain, but not final..         
           if(intGroupFinal == 0)
           {
                panelPageMainRowFinal.setBackground(colWhite);
           }
           else if(intGroupFinal == 1)
           {
               panelPageMainRowFinal.setBackground(colWhiteDark1);  
           }   
           else if(intGroupFinal == 2)
           {
               panelPageMainRowFinal.setBackground(colWhiteDark2);  
           }
           else
           {
               panelPageMainRowFinal.setBackground(Color.red);  
           }                                     
                 }

                 public void mouseClicked(MouseEvent e)
                 {
                     //System.out.println("ReportAreaGenerated.generateAreaMain clicked     intRFinal:"+intRFinal);
                   displayDrillDialog(intRFinal);
                 }                 
                }); 
                  
               JPanel colSeparationIn = new JPanel();
            
             if(showVerticalLines)
             {
                 colSeparationIn.setBackground(Color.lightGray);
             }
             else
             {
                 colSeparationIn.setBackground(Color.white);
             }
                 colSeparationIn.setPreferredSize(separationVerticalLine);
                 panelPageMainRow.add(colSeparationIn);
                 
                  //int columnType =  col.getColumnType();//4 integer, 1 string

               // System.out.println("ReportAreaGenerated.generateAreaMain colClass "+colClass+" "+(i-1));
                  
                  if(colClass.equalsIgnoreCase("java.lang.Double") || colClass.equalsIgnoreCase("java.math.BigDecimal"))
                  {
                  	txtField.setHorizontalAlignment(SwingConstants.RIGHT);
                  	 txtField.setText(uDouble.getDoubleReading(colValue,showZero));
                  	//txtField.setColumns(columnWidthDouble);
                      //System.out.println("ReportAreaGenerated.generateAreaMain "+i+" "+showZero+" "+uDouble.getDoubleReading(colValue));
                    if(colValue.toString().equalsIgnoreCase("") && colValue!=null)
                    {
                       //System.out.println("ReportAreaGenerated "+i);
                       // colValue ="";    // produces 0.0 in dot matrix printing
                      amountDouble[i-1] = amountDouble[i-1] ;
                      sumPage[i-1] = "";                        
                    }
                    else
                    {
                      //System.out.println("ReportAreaGenerated.generateAreaMain "+showCols.length+" "+arrayOfColumns.length);
                      //System.out.println("ReportAreaGenerated.generateAreaMain "+(i-1)+" "+amountDouble[i-1]+" "+Double.valueOf(colValue.toString()).doubleValue());
                      amountDouble[i-1] = amountDouble[i-1] + Double.valueOf(colValue.toString()).doubleValue();
                      sumPage[i-1] = Double.toString(amountDouble[i-1]);
                      //System.out.println("ReportAreaGenerated.generateAreaMain "+(i-1)+" "+amountDouble[i-1]);
                    }
                  }  
                  else if(colClass.equalsIgnoreCase("java.lang.Long"))
                  {
                  	txtField.setHorizontalAlignment(SwingConstants.RIGHT);
                  	//txtField.setColumns(columnWidthLong);
                  	txtField.setText(uDouble.getDoubleReading(colValue,showZero));
                  	                  	                  	
                  	if(colValue.toString().equalsIgnoreCase(""))
                    {
                    	//colValue ="0.0";     // produces 0.0 in dot matrix printing
                        amountDouble[i-1] = amountDouble[i-1];
                        sumPage[i-1] = "";                      	
                    }
                  	else
                  	{
                        amountDouble[i-1] = amountDouble[i-1] + Double.valueOf(colValue.toString()).doubleValue();
                        sumPage[i-1] = Double.toString(amountDouble[i-1]);                  		
                  	}
                  }
                  else if (colClass.equalsIgnoreCase("java.lang.Integer"))
                  {
                      txtField.setHorizontalAlignment(SwingConstants.RIGHT);

                    //System.out.println("aaaa"+colValue);
                    //System.out.println("ReportAreaGenerated.generateAreaMain Integer "+i+" "+colValue+" "+lines);
                    if(!colValue.toString().equalsIgnoreCase(""))
                    {
                      amountInt[i-1] = amountInt[i-1] + Integer.parseInt(colValue.toString());
                      sumPage[i-1] = Integer.toString(amountInt[i-1]);                     
                    }
                    else
                    {
                    	//System.out.println("page java.lang.Integer "+colValue+" "+lines);
                    	amountInt[i-1] = amountInt[i-1];
                    	sumPage[i-1] ="";
                    }
                  }
                  else if(colClass.equalsIgnoreCase("java.lang.Date") || colClass.equalsIgnoreCase("java.sql.Date"))
                  {
                  	txtField.setHorizontalAlignment(SwingConstants.CENTER);
                  	txtField.setText(uDate.reformatDateStringToReadFromDB(colValue.toString()));
                  	sumPage[i-1]="";
                  }
                  else if(colClass.equalsIgnoreCase("java.lang.Boolean") )
                  {
                  	txtField.setHorizontalAlignment(SwingConstants.CENTER);
                  	
                  	if(colValue.toString().equals("1") || colValue.toString().equalsIgnoreCase("TRUE"))
                  	{
                  	   txtField.setText("ναι");	
                  	}
                  	else if(colValue.toString().equals("0")|| colValue.toString().equalsIgnoreCase("FALSE"))
                  	{
                  		txtField.setText("όχι");	
                  	}
                  	else
                  	{
                  		txtField.setText("boolean");
                  		System.out.println("error ReportPanelOneDataManyRec.generateAreaMain, class "+colClass+" value "+colValue+" not recognized");
                  	}
                  	sumPage[i-1]="";
                  }                  
                  else
                  {
                  	sumPage[i-1]="";
                  	txtField.setHorizontalAlignment(SwingConstants.LEFT);
                  	if(!colClass.equalsIgnoreCase("java.lang.String"))
                  	{
                  	      	System.out.println("error ReportPanelOneDataManyRec.generateAreaMain, class "+colClass+" NOT supported "+colValue);
                  	}
                  }
// --------------------------  dot matrix  --------------------------------------------                       
                 
                 int intSpaces =0;
               if(arrayRecValues.length!=0 )
               {                 
                 if (colValue !=null)
                 { // for doubles that column has too mach length
                   /* if(colClass.equalsIgnoreCase("java.lang.Double") && columnWidthDoubleDotmatrix<columnWidth)
                    {
                        intSpaces = columnWidthDoubleDotmatrix - uDouble.getDoubleReading(colValue).length();
                    }
                    else if(colClass.equalsIgnoreCase("java.lang.Integer") && columnWidthIntDotmatrix<columnWidth)
                    {
                        intSpaces = columnWidthIntDotmatrix - colValue.toString().length();
                    }
                    else if(colClass.equalsIgnoreCase("java.lang.Long") && columnWidthDoubleDotmatrix<columnWidth)
                    {
                    	intSpaces = columnWidthLongDotmatrix - uDouble.getDoubleReading(colValue).length();
                    }
                    else
                    {
                         intSpaces =columnWidth-colValue.toString().length();
                    }
                    System.out.println("intSpaces"+i+" "+intSpaces);*/
                    if(colClass.equalsIgnoreCase("java.lang.Double") || colClass.equalsIgnoreCase("java.math.BigDecimal"))
                    {
                        intSpaces = columnWidth+1 - uDouble.getDoubleReading(colValue,showZero).length();// 1 the comma
                        //System.out.println("ReportPanelOneDataManyRec.generateMain intSpaces "+intSpaces+" columnWidth "+columnWidth+" i "+i);
                    }
                    else if(colClass.equalsIgnoreCase("java.lang.Integer") )
                    {
                        intSpaces = columnWidth - colValue.toString().length();
                    }
                    else if(colClass.equalsIgnoreCase("java.lang.Long"))
                    {
                    	intSpaces = columnWidth - uDouble.getDoubleReading(colValue,showZero).length();
                    }
                    else if(colClass.equalsIgnoreCase("java.lang.Boolean") )
                    {                    
                        if(colValue.toString().equals("1") || colValue.toString().equalsIgnoreCase("TRUE"))
                     	{
                     	   	intSpaces =columnWidth-"ναι".length();
                  	    } 
                  	    else if(colValue.toString().equals("0")|| colValue.toString().equalsIgnoreCase("FALSE"))
                  	    {
                  		    intSpaces =columnWidth-"όχι".length();	
                  	    }
                  	    else
                  	    {
                  		   System.out.println("error ReportPanelOneDataManyRec.generateAreaMain, class "+colClass+" value "+colValue+" not recognized");
                  	    }
                    }
                    else
                    {
                         intSpaces =columnWidth-value.length();
                    }                
                 }
                 else
                 {
                 	intSpaces = columnWidth;
                 	System.out.println("ReportPanelOneDataManyRec.generateAreaMain intSpaces set to column width ="+intSpaces);
                 }
               }
               else
               {
               	    System.out.println("error ReportAreaGenerated.generateAreaMain  listRecord dotmatrix size 0 "+r);
                	colValue=""; 
               } 
                 
                 String strSpaces="";                 
                 for (int s =1; s<=intSpaces; s++)
                 {
                    //printWriter.print(" ");
                    strSpaces=strSpaces+" ";
                 }

                  if (colClass.equalsIgnoreCase("java.lang.Integer"))
                  {
                  	strPrintDotMatrix=strPrintDotMatrix+strSpaces+colValue;
                  }
                  else if(colClass.equalsIgnoreCase("java.lang.Double") || colClass.equalsIgnoreCase("java.math.BigDecimal"))
                  {
                  	strPrintDotMatrix=strPrintDotMatrix+strSpaces+uDouble.getDoubleReading(colValue,showZero);
                  }
                  else if(colClass.equalsIgnoreCase("java.lang.Long"))
                  {
                  	strPrintDotMatrix=strPrintDotMatrix+strSpaces+uDouble.getDoubleReading(colValue,showZero);
                  }
                  else if(colClass.equalsIgnoreCase("java.lang.Date") || colClass.equalsIgnoreCase("java.sql.Date"))
                  {
                  	 strPrintDotMatrix=strPrintDotMatrix+uDate.reformatDateStringToReadFromDB(colValue.toString())+strSpaces;
                  }
                  else if(colClass.equalsIgnoreCase("java.lang.Boolean") )
                  {                    
                        if(colValue.toString().equals("1") || colValue.toString().equalsIgnoreCase("TRUE"))
                     	{
                     	   //txtField.setText("ναι");	
                     	   	//intSpaces =columnWidth-"ναι".length();
                     	   	strPrintDotMatrix=strPrintDotMatrix+"ναι"+strSpaces;
                  	    } 
                  	    else if(colValue.toString().equals("0")|| colValue.toString().equalsIgnoreCase("FALSE"))
                  	    {
                  		    //txtField.setText("όχι");
                  		    //intSpaces =columnWidth-"όχι".length();	
                  		    strPrintDotMatrix=strPrintDotMatrix+"όχι"+strSpaces;
                  	    }
                  	    else
                  	    {
                  		   //txtField.setText("boolean");
                  		   System.out.println("error ReportPanelOneDataManyRec.generateAreaMain, class "+colClass+" value "+colValue+" not recognized");
                  	    }
                  }                  
                  else
                  {
                  	strPrintDotMatrix=strPrintDotMatrix+value+strSpaces;
                  }                  

                  strPrintDotMatrix=strPrintDotMatrix+"|";
                //}//  isColumnToBeShown 
//              }// if showColumnsIn
          
            }// for each column
            
                        
            

            
            }//  isColumnToBeShown
             
            }// for i
            //System.out.println();
         panelLine.setPreferredSize(new Dimension(columnWidthTotal,1));
         //System.out.println("ReportPanelOneDatManyRec.generateMain l "+lines+" l*p "+linesPerPage+" pp "+pageCurrent);

        
          strPrintDotMatrix=strPrintDotMatrix+TXT_CHANGELINE;
// --------------------------------------------------------------------------------------
         }//while rs.next
         
        //} // for(int g=0;g<listOfListOfColumns.size();g++)
      
   	}
   
   private void generateAreaFooter(int page, int groupOfSum)
   {
      if(showSummaryInPage)
      { 	
      	panelAreaFooter = new JPanel();
      	panelAreaFooter.setBorder(null);
        panelAreaFooter.setBackground(Color.white);

	
      	boolean hasDoubleOrLongOrInt=false;
      	int columnsStringTotalWidth=0;
      	int countColumnsString=0;

      	 JPanel panelPageFooterText = new JPanel();
      	 BoxLayout boxText = new BoxLayout(panelPageFooterText, BoxLayout.PAGE_AXIS);
      	 panelPageFooterText.setLayout(boxText);
      	    
      	 panelPageFooterText.setBorder(null);
      	
      	 JTextField txtSummaryPagePre = new JTextField();
      	 txtSummaryPagePre.setBackground(Color.white);
      	 txtSummaryPagePre.setEditable(false);
         panelPageFooterText.add(txtSummaryPagePre);
      	
      	 JTextField txtSummaryPage = new JTextField();
      	 txtSummaryPage.setBackground(Color.white);
      	 txtSummaryPage.setEditable(false);
         panelPageFooterText.add(txtSummaryPage);
    
         JTextField txtSummaryPageAfter = new JTextField();
      	 txtSummaryPageAfter.setBackground(Color.white);
      	 txtSummaryPageAfter.setEditable(false);
         panelPageFooterText.add(txtSummaryPageAfter);
         
         panelAreaFooter.add(panelPageFooterText);
         
      // dot matrix
      //strPrintDotMatrix=strPrintDotMatrix+dotmatrixHorizLineHeader+TXT_CHANGELINE; 
      //System.out.println("ReportAreaGenerated.generatePageFooter length "+listSumPagePre.size()+" page "+page);
      ArrayList lstPre = (ArrayList)listSumPagePre.get(page-1); // get sum arraylist, page starts from 1 while arraylist from 0, the previous page
      ArrayList lstAfter = (ArrayList)listSumPagePre.get(page); // get sum arraylist, page starts from 1 while arraylist from 0
      String recCountPre = (String)listSumRecCount.get(page-1);
      String recCountAfter = (String)listSumRecCount.get(page);
       //ArrayList lstSumPage = (ArrayList)listSumsOfPage.get(page-1);// get sum arraylist, page starts from 1 while arraylist from 0
      //System.out.println("ReportAreaGenerated.generateAreaFooter lstPre "+lstPre.size()+" lstAfter "+lstAfter.size()+" page "+page);
      
      /*for(int l = 0;l<lstPre.size();l++)
      {
      	Object obj = (Object)lstPre.get(l);
      	 System.out.println("ReportAreaGenerated.generatePageFooter obj "+l+" "+obj.toString()+" size="+lstPre.size());
      }*/
      
      String strDotmatrixSumL1="";
      String strDotmatrixSumL2="";
      String strDotmatrixSumL3="";
      
      int columnWidthString=0; // for dot matrix for columns that are strings
      int columnCountIntOrDouble=0;// for dot matrix for columns that are double or integer
     
     
         RecColumn[] arrayOfColumns = null;//new Object[columnCount];  ArrayList listOfColumns = new ArrayList();
         /*if(hasHeaderNfooter)
         {
         	//System.out.println("ReportAreaGenerated.generatePageFooter "+listOfListOfColumns.size());
         	
            arrayOfColumns = (RecColumn[])listOfListOfColumns.get(1);// 2 is the master 0 is the header 1 is the footer	
         }
         else
         {
         	arrayOfColumns = (RecColumn[])listOfListOfColumns.get(0);// 2 is the master 0 is the header 1 is the footer
         }*/
          
          arrayOfColumns = (RecColumn[])listOfListOfColumns.get(groupOfSum);// 2 is the master 0 is the header 1 is the footer
         
          

         EntityReportRow entRepRow = (EntityReportRow)listOfRecords.get(groupOfSum);
 //        int[] showCols=entRepRow.getEntityReportBand().getShowColumns();   //showColumns;// either showColumnsHeader or showColumns       
          
         for (int c=0;c<arrayOfColumns.length;c++)
         {
             
 
            
            //System.out.println("ReportAreaGenerated.generateMain "+i+" "+showColumns[i-1]+" showCols "+showCols);
//            if(showCols.length<arrayOfColumns.length)
//            {
//            	showCols = new int[arrayOfColumns.length];

//            }

//            if (showCols[c]!=0) // because ArrayList from DialogReportSetting starts from 0 	
//            {

               //System.out.println("ReportAreaGenerated.generateAreaMain "+i+" "+showColumns[i-1]);           
                 RecColumn col = (RecColumn)arrayOfColumns[c];
                //boolean isColumnToBeShown =  getIfColumnIsToBeShown(c,page,groupOfSum,col);//,arrayOfColumns.length,AREA_FOOTER);                 
                
                //if(isColumnToBeShown)
                //{          

         	//System.out.println("ReportAreaGenerated.generateAreaFooter col "+c+" "+arrayOfColumns.length+" "+listOfListOfColumns.size());
            JPanel panelPageFooterLine = new JPanel();
      	    BoxLayout box = new BoxLayout(panelPageFooterLine, BoxLayout.PAGE_AXIS);
      	    panelPageFooterLine.setLayout(box);
      	    
      	    panelPageFooterLine.setBorder(null);
            //panelPageFooterLine.setBackground(Color.blue);
         	
//           if (showColumns[c] ) // -1 because ArrayList from DialogReportSetting starts from 0 	
//           {
           	
         	 // String columnName = rsmd.getColumnLabel(i); //get colunm name
             // System.out.println("ReportAreaGenerated.generateMain "+columnName);
             
             JPanel colSeparationIn = new JPanel();
             if(showVerticalLines)     
             {
                 colSeparationIn.setBackground(Color.lightGray);
             }
             else
             {
                 colSeparationIn.setBackground(Color.white);
             }
              colSeparationIn.setPreferredSize(separationVerticalLine);  //(separationVertical3Line);

               //RecColumn col = (RecColumn)arrayOfColumns[c];
               
               int columnWidth= 0;
               
               //int[] showCols = null;

             //arrayOfColumns = (RecColumn[])entRepRow.getRecColumn();  
            
            	//EntityReportRow entRepRow = (EntityReportRow)listOfRecords.get(1);  // possibly 1 -> groupOfSum             
//            	showCols=entRepRow.getEntityReportBand().getShowColumns();  
               
 //              if((showCols[c]!=0) )    
 //              {      	
                 columnWidth = col.getColumnLengthMaxActual();
//               }
               
               String colClass= col.getColumnClass();
               
               // System.out.println("ReportAreaGenerated.generateAreaFooter "+c+" "+col.getColumnName()+" "+columnWidth);
               
               JTextField txtSumPre = new JTextField(columnWidth-columnWidth/intColWidthOfTxt);
               JTextField txtField = new JTextField(columnWidth-columnWidth/intColWidthOfTxt);
               JTextField txtSumAfter = new JTextField(columnWidth-columnWidth/intColWidthOfTxt);
                  if((colClass.equalsIgnoreCase("java.lang.Double") || colClass.equalsIgnoreCase("java.math.BigDecimal") || (colClass.equalsIgnoreCase("java.lang.Long"))) )//&&  (showCols[c]!=0) )
                  {
                  	//System.out.println("ReportAreaGenerated.generateAreaFooter c"+c);

                  	hasDoubleOrLongOrInt=true;

                  	txtSumPre.setHorizontalAlignment(SwingConstants.RIGHT);
                  	String txtSum1=uDouble.getDoubleReading((Object)lstPre.get(c),true);
                  	txtSumPre.setText(txtSum1);
                  	//txtSumPre.setColumns(columnWidthDouble);
                  	panelPageFooterLine.add(txtSumPre);                                                         //  1 is to add a character for each column
                    strDotmatrixSumL1=strDotmatrixSumL1+utilsPanelReport.getStringForPositionInTheRight(txtSum1,"",columnWidth+1);   // bacause we have vertical char |

                  	txtField.setHorizontalAlignment(SwingConstants.RIGHT);
                  	//System.out.println("ReportAreaGenerated.generateAreaFooter "+c+" "+sumPage.length);
                  	String txtSum2=uDouble.getDoubleReading(sumPage[c],true);
                  	txtField.setText(txtSum2);
                  	//txtField.setColumns(columnWidthDouble);
                  	panelPageFooterLine.add(txtField);
                   strDotmatrixSumL2=strDotmatrixSumL2+utilsPanelReport.getStringForPositionInTheRight(txtSum2,"",columnWidth+1);

                  	txtSumAfter.setHorizontalAlignment(SwingConstants.RIGHT);
                  	String txtSum3=uDouble.getDoubleReading((Object)lstAfter.get(c),true);
                  	txtSumAfter.setText(txtSum3);
                  	//txtSumAfter.setColumns(columnWidthDouble);
                  	panelPageFooterLine.add(txtSumAfter);
                  strDotmatrixSumL3=strDotmatrixSumL3+utilsPanelReport.getStringForPositionInTheRight(txtSum3,"",columnWidth+1);              	

                  	panelAreaFooter.add(panelPageFooterLine);
                  	panelAreaFooter.add(colSeparationIn);

                  	columnCountIntOrDouble++;// dot matrix count column if double or int
                  }
                  else // string field
                  {
                  	//panelAreaFooter.add(Box.createRigidArea(new Dimension(columnWidth-columnWidth/5,0)));
                    panelAreaFooter.setBorder(null);
                    countColumnsString++;
                    columnsStringTotalWidth = columnsStringTotalWidth+(columnWidth-columnWidth/intColWidthOfTxt);// for laser

                    columnWidthString=columnWidthString+columnWidth;// for dot matrix
                    //System.out.println("ReportAreaGeneratedPage.generatePageFooter columnWidth "+columnWidth);
                  }
                  
                 txtSumPre.setBorder(null);
                 txtSumPre.setFont(fontNormal);
                 txtSumPre.setBackground(Color.white);
                 txtSumPre.setEditable(false);
              
                 txtField.setBorder(null);
                 txtField.setFont(fontNormal);
                 txtField.setBackground(Color.white);
                 txtField.setEditable(false);
                 
                 txtSumAfter.setBorder(null);
                 txtSumAfter.setFont(fontNormal);
                 txtSumAfter.setBackground(Color.white);
                 txtSumAfter.setEditable(false);
                 
              //  }// isColumnToBeShown
 //            }//(showColumns[c] )

           }// for each column
           
                txtSummaryPagePre.setColumns(columnsStringTotalWidth);
                txtSummaryPagePre.setBorder(null);

                txtSummaryPage.setColumns(columnsStringTotalWidth);
                txtSummaryPage.setBorder(null);

                txtSummaryPageAfter.setColumns(columnsStringTotalWidth);
                txtSummaryPageAfter.setBorder(null);

             if(hasDoubleOrLongOrInt==true)
             {
             	String sum1Txt = "απο μεταφορά ("+recCountPre+"): ";
                txtSummaryPagePre.setText(sum1Txt);
                txtSummaryPagePre.setHorizontalAlignment(SwingConstants.RIGHT); //            | the no of vertical line
                txtSummaryPagePre.setFont(fontNormal);
                strDotmatrixSumL1=utilsPanelReport.getStringForPositionInTheLeft(sum1Txt,columnWidthString+countColumnsString)+strDotmatrixSumL1;
                  //                                                                                         | 1 of the start vertical line
                String sum2Txt = "σύνολα σελίδας ("+lines+"): ";
                txtSummaryPage.setText(sum2Txt);
                //txtSummaryPage.setText("εγγραφές("+lines+")  σύνολα σελίδας: ");
                txtSummaryPage.setHorizontalAlignment(SwingConstants.RIGHT); 
                txtSummaryPage.setFont(fontNormal);
                strDotmatrixSumL2=utilsPanelReport.getStringForPositionInTheLeft(sum2Txt,columnWidthString+countColumnsString)+strDotmatrixSumL2;
                
                String sum3aTxt = "τελικά σύνολα ("+recCountAfter+"): ";
                String sum3bTxt ="σε μεταφορά ("+recCountAfter+"): ";
                if(pageTotal==page)
                {// last page
                   txtSummaryPageAfter.setText(sum3aTxt);
                   strDotmatrixSumL3=utilsPanelReport.getStringForPositionInTheLeft(sum3aTxt,columnWidthString+countColumnsString)+strDotmatrixSumL3;
                   
                }
                else
                {
                	txtSummaryPageAfter.setText(sum3bTxt);
                	strDotmatrixSumL3=utilsPanelReport.getStringForPositionInTheLeft(sum3bTxt,columnWidthString+countColumnsString)+strDotmatrixSumL3;
                }
                txtSummaryPageAfter.setHorizontalAlignment(SwingConstants.RIGHT); 
                txtSummaryPageAfter.setFont(fontNormal);
                
                panelAreaFooter.setLayout(layoutHorizBorderRight);
                
     
             }  
             else
             {
             	    	
             	String sum1Txt = "απο μεταφορά ("+recCountPre+")";
                txtSummaryPagePre.setText(sum1Txt);
                txtSummaryPagePre.setHorizontalAlignment(SwingConstants.CENTER); //            | the no of vertical line
                strDotmatrixSumL1=utilsPanelReport.getStringForPositionInTheMiddle(sum1Txt,"",charsPerLine);
                  //                                                                                         | 1 of the start vertical line
                String sum2Txt = "σύνολα σελίδας ("+lines+")";
                txtSummaryPage.setText(sum2Txt);
                //txtSummaryPage.setText("εγγραφές("+lines+")  σύνολα σελίδας: ");
                txtSummaryPage.setHorizontalAlignment(SwingConstants.CENTER); 
                strDotmatrixSumL2=utilsPanelReport.getStringForPositionInTheMiddle(sum2Txt,"",charsPerLine);
                
                String sum3aTxt = "τελικά σύνολα ("+recCountAfter+")";
                String sum3bTxt ="σε μεταφορά ("+recCountAfter+")";
                if(pageTotal==page)
                {// last page
                   txtSummaryPageAfter.setText(sum3aTxt);
                   strDotmatrixSumL3=utilsPanelReport.getStringForPositionInTheMiddle(sum3aTxt,"",charsPerLine);
                   
                }
                else
                {
                	txtSummaryPageAfter.setText(sum3bTxt);
                	strDotmatrixSumL3=utilsPanelReport.getStringForPositionInTheMiddle(sum3bTxt,"",charsPerLine);
                }
             	txtSummaryPageAfter.setHorizontalAlignment(SwingConstants.CENTER); 
             	
             	/*txtSummaryPage.setText(" πλήθος εγγραφών:("+lines+")");
             	txtSummaryPage.setHorizontalAlignment(SwingConstants.CENTER);
             	panelAreaFooter.setLayout(layoutHorizBorderCenter);
             	
             	strPrintDotMatrix=strPrintDotMatrix+" πλήθος εγγραφών "+lines+TXT_CHANGELINE;*/
             }
 
              panelAreaFooter.setBorder(BorderFactory.createLineBorder(Color.black));
              panelArea.add(panelAreaFooter, BorderLayout.PAGE_END);
              // -----------------------------------------  dot matrix--------------------------
             
             strPrintDotMatrix=strPrintDotMatrix+dotmatrixHorizLineHeader+TXT_CHANGELINE;
             strPrintDotMatrix=strPrintDotMatrix+strDotmatrixSumL1+TXT_CHANGELINE;
             strPrintDotMatrix=strPrintDotMatrix+strDotmatrixSumL2+TXT_CHANGELINE;
             strPrintDotMatrix=strPrintDotMatrix+strDotmatrixSumL3+TXT_CHANGELINE;
             strPrintDotMatrix=strPrintDotMatrix+dotmatrixHorizLineHeader+TXT_CHANGELINE;               

        
                /* JTextField txtFieldFooter = new JTextField();
                 txtFieldFooter.setBorder(null);
                 //Font font = new Font(txtFieldFooter.getFont().getFontName(), txtFieldFooter.getFont().getStyle(), 11);
                 txtFieldFooter.setFont(fontNormal);
                 txtFieldFooter.setBackground(Color.white);
                 txtFieldFooter.setEditable(false);
                 txtFieldFooter.setText(" πλήθος εγγραφών: "+linesTotal);
                 
                 
               	  JPanel panelAreaFooter = new JPanel();
                  panelAreaFooter.setBorder(null);
                  panelAreaFooter.setBackground(Color.white);
                  panelAreaFooter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                  //FlowLayout panelFooter = new FlowLayout();
                  //lineLayout.setVgap(1);
                  //System.out.println("reportPanelOneDataManyRec "+lineLayout.getVgap()+" "+panelLine.getHeight());
                  panelAreaFooter.setLayout(new FlowLayout());
               	
               	  panelAreaFooter.add(txtFieldFooter);*/
       }  // if(showSummaryInPage)
     strPrintDotMatrix=strPrintDotMatrix+TXT_CHANGEPAGE; // change page in printer
   }
   
   
   /*
   *  get code from panelODORData.displayDialogEdit and panelODORData.setEntity
   *  alternatively looka at   panelstatistics.displayDrillDialog    
   */
   private void displayDrillDialog( int selectedTableRowIn)
   {
   	//System.out.println("ReportAreaGenerated.clickedOnRow "+reportPanelPage.clickedOnRow(queryIn,  entityIn,  selectedTableRowIn,  primKeyIn));
         String caption =""; 
         String name ="";
         String tableName = "";
         int intOfEntityRow = 0;
           //EntityData entityData = new EntityData();
      
           EntityReportRow entityReportRow = (EntityReportRow)listOfRecords.get(selectedTableRowIn);
           intOfEntityRow=entityReportRow.getIntOfEntityReportBand();
           EntityPanel[] entityPanelDrill = entityReportRow.getEntityReportBand().getEntityPanelDrill();
      if(entityPanelDrill !=null )     
      {    
           String[] getPanelDrillFieldsOnTitle = entityReportRow.getEntityReportBand().getPanelDrillFieldsOnTitle();
           String[] getPanelDrillFieldsOnTitleCaption = entityReportRow.getEntityReportBand().getPanelDrillFieldsOnTitleCaption();
           

           	    //rowSelectable+1;  // row for tooltip
                  tableName = entityReportRow.getEntityReportBand().getTableName();
                  name = entityReportRow.getEntityReportBand().getName();
                  caption = entityReportRow.getEntityReportBand().getCaption();
            String formGlobalTableToGet1 = entityReportRow.getEntityReportBand().getFormGlobalTableToGet1();
            String formGlobalTableToApply1 = entityReportRow.getEntityReportBand().getFormGlobalTableToApply1();
                 
                  
    //System.out.println("=   ReportAreaGenerated.displayDrillDialog    selectedTableRowIn:"+selectedTableRowIn+"       no of band:"+entityReportRow.getRecordIntNoOfBand()+"        intOfEntityRow:"+intOfEntityRow+"   ------    "+tableName+"."+name+"     caption:"+caption+"      entityPanelDrill.length:"+entityPanelDrill.length+"     queryReport:"+queryReport);


   //   ----- similar with panelstatistics.displayDrillDialog -------    
                               //String queryIn, int selectedTableRow, String primKeyIn,int intColumnOfDescription,String[] sql2WhereField, String[] sql2WhereValue, String entity, TableModel tableModel, String primKeyDb)
                          //System.out.println("PanelODMRData.displayDrillDialog A selectedTableRow:"+selectedTableRow);  
      //STATS be careful to have in this query all the fields that are also in the title
       
        //String que = querySelect+" "+queryFrom+" "+queryWhere+" "+queryGroupBy+" "+queryOrderBy ;
        
        
        String entity = entityPanelDrill[0].getEntity();
        String primKeyDbDrill = entityPanelDrill[0].getPrimKeyDb();
        
        
        
        EntityDBFields[] dbFields =  entityPanelDrill[0].getDBFields();
          utilsPanelReport.retrievePrimKeyValueForOnePK(queryReport, entityReportRow.getRecordIntNoOfBand()/*(selectedTableRowIn)*/,dbFields,null,false,  entity, primKeyDbDrill);
                          //utilsPanelReport.getPrimKeyValue();
             String[] primKeys = utilsPanelReport.getPrimKeys();
             String[] primKeysCaption = utilsPanelReport.getPrimKeysCaption();
         // System.out.println("-->  PanelOneDataOneRec.setEntity  entityIn:"+entityIn+" '"+entityPanel.getEntity()+"' selectedRow:"+selectedRow+"  primKeys.length:"+primKeys.length); 
             int primKeysCount = primKeys.length;
             String[] primKeysValue = utilsPanelReport.getPrimKeysValue();                             
           String pkValue = utilsPanelReport.getPrimKeyValue();
      //System.out.println("= ReportAreaGenerated.displayDrillDialog ========  selectedTableRow:"+(selectedTableRowIn)+"      "+entity+"."+primKeyDbDrill+"= pkValue:"+pkValue);                            

        

      //EntityPanel[] entityPanel = drillEntityPanels[0].getDrillEntityPanel();
      
      //String queryReadOnly = entityPanelDrill[0].getQuery();
      String editTitle = entityPanelDrill[0].getTitle();
      ImageIcon ico = entityPanelDrill[0].getIco();  
     
  
      PanelEditOneDataRec panelEODR = new PanelEditOneDataRec(null);
      String queryBeforeWhere = utilsString.getQueryBeforeWhere(queryReport) ;
      String queryAfterWhere = utilsString.getQueryAfterWhere(queryReport);
      String queryWhere = utilsString.getQueryWhere(queryReport);
        if  (pkValue!=null && !pkValue.equals(""))
        
        for(int k= 0 ; k<primKeysCount; k++)    
        {
            
         if(utilsString.hasQueryWhere(queryReport))
         {
         	queryWhere=queryWhere+" AND "+entity+"."+primKeys[k]+" LIKE '"+primKeysValue[k]+"'";
         }
         else
         {
         	queryWhere="WHERE "+entity+"."+primKeys[k]+" LIKE '"+primKeysValue[k]+"'";
         }
         
        } // for k 
       //         must be *
       String q = queryBeforeWhere+" "+queryWhere+" "+queryAfterWhere;//+" "+queryGroupBy+" "+queryOrderBy ;       
     
     //String q = "SELECT "+selectTitleFields+" "+queryFrom+" "+queryWhere+" "+queryGroupBy+" "+queryOrderBy ; //  <-----
       
        //throws null      System.out.println("ReportAreaGenerated.displayDrillDialog   OOOOO   drillEntityPanels.length:"+entityPanelDrill.length+"    drill entity report bands length 0 0 :"+entityPanelDrill[0].getEntityReportForm().getEntityReportBands()[0]+"           primKeyDbDrill:"+primKeyDbDrill+" pkValue:"+pkValue+"   drillEntityPanels:"+entityPanelDrill+"   q:"+q);
       
       
       String[] categoryNodes = {DATAENTRY,METRICS};
       System.out.println("ReportAreaGenerated.displayDrillDialog   OOOOO------>     primKeyDbDrill:"+primKeyDbDrill+"    pkValue:"+pkValue);
        
      panelEODR.setEntity(entity, entityPanelDrill,getPanelDrillFieldsOnTitle,getPanelDrillFieldsOnTitleCaption,false,primKeyDbDrill,pkValue,primKeyDbDrill,/*formGlobalTableToGet1,
        formGlobalTableToApply1,*/q,//queryReadOnly,//query,//
        editTitle,ico,false,false,true,categoryNodes, false,/*panelManagement*/null);//,drillEntityPanels[0].getEntityReportForm());
      
        panelEODR.setVisible(true);
        
        //DialogMulti dlg = new DialogMulti(frame);
        DialogMulti dlg = new DialogMulti(null);
        //JxPanel pnl = new JxPanel();
        //JLabel lbl = new JLabel(listOfRecords.size()+"    name:"+name+"      no of band:"+entityReportRow.getRecordIntNoOfBand()+"     editTitle:"+editTitle+"         selectedTableRowIn:"+selectedTableRowIn+"  pkValue:"+pkValue);//+"  queryReport:"+queryReport);
        //pnl.add(lbl);
        //dlg.setEntity(pnl,PANEL_TYPE_ANY, caption,false);
        dlg.setEntity(panelEODR,PANEL_TYPE_EDITONEDATAONEREC, "επεξεργασία "+caption,true);
        dlg.display();      
      }
      else // drillentity is null
      {
          
      }
  
   }
   
   /*
     called from ReportPanelPage.generateMain
   */
   public JPanel getReportAreaPanel()
   {
   	 return panelArea;
   }
   
    public String getStrPrintDotmatrix()// for dot matrix
   {
   	  return strPrintDotMatrix;
   }
   
    public int print(Graphics g, PageFormat pf, int pageIndex)  throws PrinterException
 {

	Graphics2D g2 = (Graphics2D) g;
	int pageHeight = (int) Math.round(pf.getImageableHeight());
    int pageWidth = (int) Math.round(pf.getImageableWidth());
    double panelWidth = Double.valueOf(this.getWidth()).doubleValue();
    double panelHeight= this.getHeight();
    double scale = 1;
    
    //System.out.println("ReportAreaGenerated.print "+pageIndex);
    
    
     if (panelWidth >= pageWidth)
     {
            scale = pageWidth / panelWidth;
     }   
    //System.out.println("Printpanel.print pageHeight="+pageHeight+" panelHeight="+panelHeight);
	//System.out.println("ReportAreaGenerated.print scale="+scale+" pageHeight/panelHeight="+pageHeight/panelHeight);

    g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
	
    // As suggested by Jaume (Tau Ingenieros <tauinge@menta.net>), use
    // paint instead of paintComponent and resize to page format.
    Dimension oldSize = getSize();
    //setSize((int)pf.getWidth(), (int)pf.getHeight());

    //System.out.println("ReportAreaGenerated.print pageHeight"+pageHeight+" pageWidth "+pageWidth);
    setSize(pageWidth,pageHeight);
    print(g);
    setSize(oldSize);

    return Printable.PAGE_EXISTS;
 }
   
   
 }