// created 05-04-2008

package com.tool.rpt;

import com.tool.model.EntityFilterSettings;
import com.tool.guicomps.*;
import com.tool.jdbc.*;
import com.tool.utils.*;
import com.tool.gui.*;
import com.tool.model.*;


import java.awt.print.Printable;
import java.awt.print.PrinterException;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Cursor;


import java.awt.print.PageFormat;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.Box;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;


  import java.sql.ResultSet;
  import java.sql.SQLException;
  import java.sql.ResultSetMetaData;
  
  import java.util.ArrayList;  
  import java.util.Vector;  


 public class ReportPanelPage extends JPanel implements Constants, Printable
 {	
  private String queryForForm;
  private String queryReport;
 //private String entity;
 private String title;
 private String subTitle;
 //private int[] showColumns;
 private ArrayList showColumnsPerBand;
 private int[] showColumnsHeader;
 private boolean isDotmatrix;
 private boolean showSummaryInPage;
 
 private int columnCount =0;
 private int columnCountHeader =0;
 private int rowCount =0;
 private Database db; 
 private ResultSet rs;
 //private ResultSetMetaData rsmd; 
  
  private String strPrintDotMatrix="";  
  private String printWriterPageHeader="";
  
  
  private JPanel panelPage; // the one to be printed
  private JPanel panelPageContainer; //contains page
    
  private int lines;  
  //private int linesTotal;
  //private int linesPerPage;
    private int pageCurrent=1;
    
    private boolean showVerticalLines;
    private boolean showHorizontalLines;
   private ArrayList arrayPanelPage = new ArrayList(); // to hold page
   private ArrayList arrayPanelPageBrake = new ArrayList(); // to hold page
   //private JTextField txtFieldRecords;
   
   private JPanel panelPageHeader;
   private JPanel panelPageFooter;
   
   private JPanel panelReportHeader;
     
   private String[] sumPage;
   private String[] sumPagePre;// sum of previous pages
   
   private ArrayList listSumsOfPage;
   private ArrayList listSumPagePre = new ArrayList();
   private ArrayList listSumPagePreField;
   private ArrayList listSumRecCountMaster;
   private ArrayList listSumRecCountMany;
   
   
   
   JPanel panelPageBrake;
   private PageFormat pageFormat;
   JPanel panelLeft;
   JPanel panelRight;
   
   
   
   Font fontNormal;
   Font fontBold;
   FlowLayout layoutHorizBorderCenter;
   FlowLayout layoutHorizBorderRight;
   //Dimension separationVerticalLine;
   //Dimension separationVertical2Line;
   //Dimension separationVertical3Line;
   
   /*final int DOTMATRIX_CHARS_PER_LINE_A4_CPI10=80;
   final int DOTMATRIX_CHARS_PER_LINE_A4_CPI15=115;
   final int DOTMATRIX_CHARS_PER_LINE_A3=135;
 //  final int DOTMATRIX_LINES_PER_PAGE=47;
   final String DOTMATRIX_LINE_CHAR="=";
   //final String DOTMATRIX_CHAR_EMPTY=" ";*/
   
   
   private String strPageSizeDotmatrix;
   
   int linesOfReportHeader=0;
   
   private int charsPerLine=0; 
   
   String dotmatrixHorizLine="";
   
   private Thread thread;
   
   private ArrayList listOfListOfColumns = new ArrayList(); // holds column data
   private ArrayList listColumnMany = new ArrayList(); // holds column data
   private ArrayList listRecord = new ArrayList(); // holds column value
   private ArrayList listRecordField; // initialized on fetchData because else it doesnt calculate well
   private ArrayList listRowHeaderInMasterRecsPointer; 
   
   private ArrayList listOfRecords;
   
   private RecColumn recColumn;
   
   private int fromRecInPage;
   private int toRecInPage=0;
   private int pageTotal;
   
   //private boolean isDotmatrix=false;
   private UtilsDouble uDouble;
   private UtilsDate uDate;
   private UtilsGui utilsGui;
   private UtilsPanelReport utilsPanelReport;
   private EntityFilterSettings[] entityFilterSettings;
   
   private int intColWidthOfTxt;
   
   //private boolean[] showLines;
   private String viewType;
   private String primKey;
   
    private ArrayList listRecordFieldMany;
   	private ArrayList listRecordMany;
   	private int columnCountMany;
   	
   	private ArrayList listFromRecInAreaMany;
   	private ArrayList listToRecInAreaMany;
   	private ArrayList listPrimKeyVal;
        
   private String[] arrayOfNameOfPksOfRecordToShow;
   private String[] arrayOfValueOfPksOfRecordToShow;	

    private ReportAreaGenerated reportAreaGenerated;

    
    private String[] arrayStringsToBePrinted;
    private String reportDateToBePrinted="";
    
    private ArrayList listRowHeaderInMasterRecsLevel;
    
    //private EntityReportBand[] entityReportBand;
    private EntityReport entityReport;
    private String name;
    private UtilsString utilsString;
    private int dotmatrixCpi;
    
    //private EntityFilterSettings[] entityFilterSettings;
     private int[] intSettingsReport;
   //	 private boolean[] boolSettingsReport;
    private ReportAreaForm reportAreaForm;
    private JPanel panelPageMain;
    
    
    
    public ReportPanelPage()
    {
            try
           {    initialize();    }
           catch (Exception e)
           {   e.printStackTrace();    }
    }

    private void initialize() throws Exception
    {   
       
      // this.setLayout(new FlowLayout());// BoxLayout(this, BoxLayout.PAGE_AXIS));
        BorderLayout brdlay = new BorderLayout();
        brdlay.setHgap(0);
        brdlay.setVgap(0);
        this.setLayout(brdlay);
       this.setBackground(Color.MAGENTA);// gray

       utilsGui = new UtilsGui();
       db = new Database();
       utilsString = new UtilsString();
       
       
       
       
       pageFormat =new PageFormat();
       
       uDouble=new UtilsDouble();
       uDate= new UtilsDate();
       utilsPanelReport=new UtilsPanelReport();
       
       arrayPanelPage.clear();
       
       listSumsOfPage= new ArrayList();
       
       showVerticalLines=true;
       showHorizontalLines=true;
        
       JTextField txt = new JTextField();
       fontNormal = new Font(txt.getFont().getFontName(), txt.getFont().getStyle(), 9);
       fontBold = new Font(txt.getFont().getFontName(), Font.BOLD, 9);
       
       layoutHorizBorderCenter = new FlowLayout(FlowLayout.CENTER,2,0); //hgap, int vgap 2,4 for all rows
       layoutHorizBorderRight = new FlowLayout(FlowLayout.RIGHT,2,0); //2,4 for footer row
       //separationVerticalLine = new Dimension(1,15); // width, height
       //separationVertical2Line = new Dimension(1,15*2);
       //separationVertical3Line = new Dimension(1,15*3);
       
       intColWidthOfTxt=4; // width of JTextFields the smaller the smaller the fields
       //panelPage.setPreferredSize(new Dimension(500,500));
   	   //panelPage.setMaximumSize(new Dimension(500,500));    
           
           panelPage = new JPanel();
           reportAreaForm = new ReportAreaForm();
           
           
           
		   panelPageMain = new JPanel();
                   panelPageMain.setBackground(Color.CYAN);
   		   FlowLayout fl = new FlowLayout();
   		   fl.setHgap(0);
   		   fl.setVgap(0);
           panelPageMain.setLayout(fl);
           panelPageMain.setBorder(null);
        
           panelPage.setLayout(fl);
           panelPage.setBackground(Color.CYAN);
           
           panelPage.add(panelPageMain);//, BorderLayout.CENTER);           
           
         
           
    }
    
   public String getStrPrintDotmatrixToView()
   {
   	
       	     
             int sumLengthOfCode=0;
             String codeCpi="/cpi=";
             int lengthOfCpi = codeCpi.length();
             String codeCpi10 = "/cpi=10";
             String codeCpi15 = "/cpi=15";
             int lengthOfPrintCode = 7 ;
             
            ArrayList lstcpi =  utilsString.getIndicesOf(strPrintDotMatrix,codeCpi);
            // exists also (for different purpose) in PanelPrintPreview.goPrint (to change cpi)
            if(lstcpi.size()>1)
            {            
              int p=0;
              for(int l = 0;l<lstcpi.size();l++)
              {
              	 int idx = Integer.parseInt(lstcpi.get(l).toString());
              	 //String strSize = strPrintDotMatrix.substring(idx+lengthOfCpi,idx+lengthOfPrintCode);
              	
              	 //System.out.println("PanelPrintPreview.goPrint "+l +" idx"+idx+"->"+strSize+"-"+strPrintDotMatrix.substring(idx,idx+lengthOfPrintCode));
              	 //int intSize=  Integer.parseInt(strSize);
              	
              	 strPrintDotMatrix = utilsString.replaceStringFromString(idx,idx+lengthOfPrintCode,"       ",strPrintDotMatrix);
              	 //System.out.println("PanelPrintPreview.goPrint "+l+" remove from"+idx+" to"+(idx+lengthOfPrintCode));
              	 
              	 
                 	//System.out.println("PanelPrintPreview.goPrint "+l+" from"+p+" to"+idx+" "+strPrintDotMatrix.substring(p,idx));
                 	
              	  /*  printWriter.print(strPrintDotMatrix.substring(p,idx));
              	    
              	    p = idx;              	 
              	 
              	    if(intSize==10)
              	    {
              	 	
              	 	    printCode10CPI(printWriter);
              	    }
              	    else if(intSize==15)
              	    {
              	 	
              	    	printCode15CPI(printWriter);
              	    }   */              	
                 	
              	 
              	 ArrayList lstcpiNew = utilsString.getIndicesOf(strPrintDotMatrix,codeCpi);
              	 if(l<lstcpi.size()-1)
              	 {
              	    lstcpi.set(l+1,Integer.parseInt(lstcpiNew.get(0).toString()));
              	    //System.out.println("PanelPrintPreview.goPrint "+l+" "+lstcpi.get(l+1));
              	 }
              	 
              }           
              
                 //System.out.println("PanelPrintPreview.goPrint from"+(Integer.parseInt(lstcpi.get(lstcpi.size()-1).toString())-sumLengthOfCode)+" to "+(strPrintDotMatrix.length()-sumLengthOfCode)+" total "+strPrintDotMatrix.length());
              
                 //printWriter.print(strPrintDotMatrix.substring(Integer.parseInt(lstcpi.get(lstcpi.size()-1).toString()),strPrintDotMatrix.length()));
              }       	     
       	        	
   	 return strPrintDotMatrix;
   	  
   }
   
   public String getStrPrintDotmatrixToPrint()
   {
   	 return strPrintDotMatrix;
   }
   
   /*
   *
   * called by PanelPrintPreview.showPage
   */
   public ReportAreaForm getPanelPageFormLaserToShow()//int page,String queryFormForPrinting, String formFieldToGetData)
   {
/*      ReportAreaForm raf = null;
      
       if(generateMain(page, queryFormForPrinting,  formFieldToGetData))
       {
           raf = reportAreaForm;
       }
       else
       {
           raf = null; // null when has no data
       }
    
       
       return raf; ///  reportAreaForm;    //.getReportAreaFormPanel();
       */
       //System.out.println(".getPanelPageFormLaserToShow    no of page :"+page);
       
       return reportAreaForm;
       
   }
 
   public ReportAreaForm getPanelPageFormLaserToPrint()//int page,String queryFormForPrinting, String formFieldToGetData)
   {
       
       //arrayPanelPage.get(1);
        
//       generateMain(page,  queryFormForPrinting, formFieldToGetData);
       //System.out.println("       ---   size:"+arrayPanelPage.size());
       
       return reportAreaForm;
       
       
   }
   
   
   
   
   // only for APPLICATION / FORM called from PanelPrintPreview.showPage
   public void setEntityForForm(String nameIn,boolean isDotmatrixIn,String viewTypeIn, int page,EntityReport entityReportIn,String queryIn, ArrayList listOfListOfColumnsIn,
           ArrayList listRecordIn,String queryFormForPrinting, String formField, String[] arrayOfNameOfPksOfRecordToShowIn,String[] arrayOfValueOfPksOfRecordToShowIn)
   {
   	name=nameIn;
        isDotmatrix=isDotmatrixIn;
        entityReport=entityReportIn;
        queryForForm=queryIn;
   	viewType=viewTypeIn;
   	listOfListOfColumns=listOfListOfColumnsIn;
   	listRecord=listRecordIn;   	
   	//entityReportBand=;
   	arrayOfNameOfPksOfRecordToShow=arrayOfNameOfPksOfRecordToShowIn;
        arrayOfValueOfPksOfRecordToShow=arrayOfValueOfPksOfRecordToShowIn;
        
   	
   	pageCurrent=page;
   	//System.out.println("reportPanelPage.setEntityForForm      nameIn:"+nameIn);	
   	//  strPrintDotMatrix="";
   	//generatePageHeader(page);
        
       
        
        
        
        
          //setPageDimensions();
   	  generatePageStart();  
          generateMain(page,queryForForm, /*queryFormForPrinting,*/formField);
 
  
   }
   
   
   /**
    * 
    * called by PanelPrintPreview.buildAndGetPage , buildAndGetDotmatrixPage, showPage.
    */
   public void setEntity( String viewTypeIn,String titleIn,String subTitleIn,/*EntityReportBand[] entityReportBandIn,*/ArrayList listOfRecordsIn,String queryReportIn,
   boolean isDotmatrixIn,String strPageSizeDotmatrixIn, int page,int fromRecInPageIn,int toRecInPageIn,
   ArrayList listOfListOfColumnsIn/*listColumnIn*/,ArrayList showColumnsPerBandIn, int[] showColumnsHeaderIn, /*int columnCountIn,int columnCountHeaderIn, */ArrayList listSumPagePreIn, 
   ArrayList listRecordIn,ArrayList listRecordFieldIn, ArrayList listSumRecCountIn,ArrayList listSumsOfPageIn,  int pageTotalIn, 
   PageFormat pageFormatIn, boolean showSummaryInPageIn, String[] arrayStringsToBePrintedIn, EntityFilterSettings[] entityFilterSettingsIn, 
   String primKeyIn, ArrayList listPrimKeyValIn, int dotmatrixCpiIn,int[] intSettingsReportIn /*,  boolean[] boolSettingsReportIn*/ )
   {
   	
   	viewType=viewTypeIn;
   	title=titleIn;
   	subTitle=subTitleIn;
        //entityReportBand=entityReportBandIn;
   	listOfRecords=listOfRecordsIn;
   	queryReport=queryReportIn;// is getted from panel data filter
   	isDotmatrix=isDotmatrixIn;
   	fromRecInPage=fromRecInPageIn;
   	toRecInPage=toRecInPageIn;
   	//columnCount=columnCountIn;
   	//columnCountHeader=columnCountHeaderIn;
   	listOfListOfColumns=listOfListOfColumnsIn;
   	listSumPagePre=listSumPagePreIn;
   	listRecord=listRecordIn;
   	listRecordField=listRecordFieldIn;
   	listSumRecCountMaster=listSumRecCountIn;
   	//listRowHeaderInMasterRecsPointer=listRowHeaderInMasterRecsPointerIn;
   	//listRowHeaderInMasterRecsLevel= listRowHeaderInMasterRecsLevelIn;
   	strPageSizeDotmatrix=strPageSizeDotmatrixIn;
   	pageTotal=pageTotalIn;
   	pageFormat=pageFormatIn;
   	//showColumns=showColumnsIn;
        showColumnsPerBand=showColumnsPerBandIn;
   	showColumnsHeader=showColumnsHeaderIn;
   	showSummaryInPage=showSummaryInPageIn;
   	entityFilterSettings=entityFilterSettingsIn;
   	//entityFilterSettings=entityFilterSettingsIn;
   	primKey=primKeyIn;
   	listPrimKeyVal=listPrimKeyValIn;
   	arrayStringsToBePrinted=arrayStringsToBePrintedIn;
   	dotmatrixCpi=dotmatrixCpiIn;
   	//System.out.println("ReportPanelPage "+listFromRecInAreaManyIn.get(1));
   	 intSettingsReport = intSettingsReportIn;
   	 //boolSettingsReport = boolSettingsReportIn;
   	 //entityFilterSettings=entityFilterSettingsIn;
   	
   	
   //	System.out.println("ReportPanelPage.setEntity    oooooooo   showColumnsPerBand:"+showColumnsPerBand);
   	
    reportDateToBePrinted = arrayStringsToBePrinted[0];
   	

/*  if(entityFilterSettings!=null)
   	{
   	  	linesOfReportHeader=entityFilterSettings.length;
   	}
   	else
   	{
   		linesOfReportHeader=0;
   	}   */

   	
   	       //System.out.println("ReportPanelPage.setEntity strPageSizeDotmatrixIn -"+strPageSizeDotmatrixIn+"-");
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
       
       
       
      /* for(int i=0; i<charsPerLine;i++)
   	   {
   	      dotmatrixHorizLine=dotmatrixHorizLine+DOTMATRIX_LINE_CHAR_HEADER;
   	   }*/
  	
  	//System.out.println("ReportPanelPage.setEntity A  ");
  	pageCurrent=page;
        //setPageDimensions();
   	generatePageStart();   
        generateReportHeader();
        generatePageHeader(page);
//  	strPrintDotMatrix="";        
   	generateMain(page,"","");
        
//   	generatePageFooter(page);
   }
   
   
  /* public void setPageDimensions()
   {
       
       panelPageContainer = new JPanel();
       panelPageContainer.setBackground(Color.white);       
       //                                      Math.floor
       Dimension pageDimension = new Dimension((int) Math.round(pageFormat.getImageableWidth()),(int) Math.round(pageFormat.getImageableHeight()));
       
  
       
       //                                   ---------      here       --------------
       panelPageContainer.setPreferredSize(pageDimension);
     
       
       
       System.out.println("ReportPanelOneDataManyRecPage.setPageDimensions  ================    width:"+(int) (pageFormat.getImageableWidth())+"   height:"+(int)(pageFormat.getImageableHeight())+"   arrayPanelPage.size:"+arrayPanelPage.size());
       
   }*/
   
   
   
   /*
   * 
   */
    private void generatePageStart()
    {

               
       panelPageContainer = new JPanel();
       panelPageContainer.setBackground(Color.WHITE);//  BLUE,  LIGHT_GRAY
       FlowLayout flwCont = new FlowLayout();
       flwCont.setHgap(0);
       flwCont.setVgap(0);
       panelPage.setLayout(flwCont);       
       panelPageContainer.setBorder(null);
       //                                      Math.floor
       Dimension pageDimension = new Dimension((int) Math.round(pageFormat.getImageableWidth()),(int) Math.round(pageFormat.getImageableHeight())); // round , ceil
       

       panelPageContainer.setPreferredSize(pageDimension);
     
       
       //   //panelScrollCenter.setBorder(BorderFactory.createLineBorder(Color.black,1));  at PanelPrintPreview
      // Dimension pageDimension = new Dimension((int) Math.round(pageFormat.getImageableWidth()),(int) Math.round(pageFormat.getImageableHeight()));
     
      panelPageContainer.add(panelPage);
       arrayPanelPage.add(panelPage); 
       
       //panelPage.setBackground(Color.RED);   
       panelPage.setPreferredSize(pageDimension);
       BorderLayout brdpage = new BorderLayout();
       panelPage.setLayout(brdpage);
       brdpage.setHgap(0);
       brdpage.setVgap(0);       
       panelPage.setBackground(Color.white);  
       //panelPage.setBorder(BorderFactory.createLineBorder(Color.white,1)); // black, the line around the page
       panelPage.setBorder(null);       
      //System.out.println("ReportPanelPage.generatePageStart  ================    width:"+(int) (pageFormat.getImageableWidth())+"   height:"+(int)(pageFormat.getImageableHeight())+"   arrayPanelPage.size:"+arrayPanelPage.size());
       
      // BorderLayout brdthis = new BorderLayout();
       FlowLayout flwthis = new FlowLayout();
       flwthis.setHgap(0);
       flwthis.setVgap(0);
      // this.setLayout(new BorderLayout());  // goto initialize
       this.setBorder(null);
       //this.setPreferredSize(pageDimension);
       this.removeAll();
       this.add(panelPageContainer, BorderLayout.LINE_START);//, BorderLayout);    BorderLayout.LINE_START  // not BorderLayout.CENTER  is printed wrong
       //this.setBackground(Color.CYAN);
       
       
    }
    
   /*
    * called by this.setEntity and this.setEntityForForm
    */
   private void generateReportHeader()
   {
   	

   	
   	
   	  panelReportHeader = new JPanel();
      panelReportHeader.setLayout(new BoxLayout(panelReportHeader, BoxLayout.PAGE_AXIS));
      panelReportHeader.setBackground(Color.white);
   	      
      
      // not enabled because I cannot add a line each time 
      //strPrintDotMatrix=strPrintDotMatrix+dotmatrixHorizLine+TXT_CHANGELINE; 
      
      for(int f =0;f<linesOfReportHeader;f++)
      {
         
          JPanel panelReportHeader1 = new JPanel();
          panelReportHeader1.setLayout(layoutHorizBorderCenter);
          panelReportHeader1.setBackground(Color.white);
   	      
   	      JTextField txtFilter = new JTextField();
   	      txtFilter.setText(entityFilterSettings[f].caption+": ");
   	      txtFilter.setBorder(null);
          txtFilter.setFont(fontNormal);
          txtFilter.setBackground(Color.white);
          txtFilter.setEditable(false);
          txtFilter.setHorizontalAlignment(SwingConstants.LEFT);

   	      JTextField txtValue = new JTextField();
   	      txtValue.setText(entityFilterSettings[f].getValue());
   	      txtValue.setBorder(null);
          txtValue.setFont(fontNormal);
          txtValue.setBackground(Color.white);
          txtValue.setEditable(false);
          txtValue.setHorizontalAlignment(SwingConstants.LEFT);          
          
    	  panelReportHeader1.add(txtFilter);
    	  panelReportHeader1.add(txtValue);
    	  panelReportHeader.add(panelReportHeader1);    
   	      
   	      //--------------------------------- dot matrix --------------------
   	      strPrintDotMatrix=strPrintDotMatrix+utilsPanelReport.getStringForPositionInTheMiddle(entityFilterSettings[f].caption+": "+entityFilterSettings[f].getValue(),"",charsPerLine);
          strPrintDotMatrix=strPrintDotMatrix+TXT_CHANGELINE;
      }   	
   	
   }
   
    
   private void generatePageHeader(int page)
   {   
   	     
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
                System.out.println("ReportPanelPage.generatePageHeader UNKNOWN else intSettingsReport[2] "+intSettingsReport[2]);
                linesOfReportHeader=0;
            }
   	}
   	else
   	{
   		linesOfReportHeader=0;
   	}

        
   	     
   	      panelPageHeader = new JPanel();
          panelPageHeader.setLayout(new BoxLayout(panelPageHeader, BoxLayout.PAGE_AXIS));
          panelPageHeader.setBackground(Color.white);
   	      
   	      JPanel panelPageHeader1 = new JPanel();
          panelPageHeader1.setLayout(new BoxLayout(panelPageHeader1, BoxLayout.LINE_AXIS));
          panelPageHeader1.setBackground(Color.white);
   	      panelPageHeader.add(panelPageHeader1);
   	      

    	  JPanel panelPageHeader2 = new JPanel();
          panelPageHeader2.setLayout(new BorderLayout());
          panelPageHeader2.setBackground(Color.white);
   	      panelPageHeader.add(panelPageHeader2);


   	     String dotmatrixCompanyName="";
   	     String dotmatrixDate="";
   	     

          
          //txtCompany set text first because dotmatrixDate needs to be second to calculate beeing in center of page
          
   	      JTextField txtCompany = new JTextField();
              //  String[] comboInfo ={"show in all pages","show in 1st page only","do not show"};
   	        if(intSettingsReport[1]==INTSETTINGSREPORT_SHOWINALLPAGES)
   	        {
   	        	txtCompany.setText(VariablesGlobal.globalCompanyName);
   	        	dotmatrixCompanyName = VariablesGlobal.globalCompanyName;
   	        }
   	        else if (intSettingsReport[1]==INTSETTINGSREPORT_SHOWFIRSTPAGE)
   	        {
   	        	if(page==1)
   	        	{
   	        	    txtCompany.setText(VariablesGlobal.globalCompanyName);	
   	        	    dotmatrixCompanyName=VariablesGlobal.globalCompanyName;	
   	        	}
                        else
                        {
                            	txtCompany.setText("");
   	        	        dotmatrixCompanyName="";   
                        }
   	        	 
   	        }
   	        else if (intSettingsReport[1]==INTSETTINGSREPORT_NOTSHOW)
   	        {
   	        	txtCompany.setText("");
   	        	dotmatrixCompanyName="";
   	        }
   	        else
   	        {
   	           System.out.println("ReportPanelPage.generatePageHeader UNKNOWN else intSettingsReport[1] "+intSettingsReport[1]);	
                      	txtCompany.setText("");
   	        	dotmatrixCompanyName="";
   	        }
   	             txtCompany.setBorder(null);
                 txtCompany.setFont(fontNormal);
                 txtCompany.setBackground(Color.white);
                 txtCompany.setEditable(false);
                 txtCompany.setHorizontalAlignment(SwingConstants.LEFT);
          
 
 
 
    	      JTextField txtDate = new JTextField();
   	        if(intSettingsReport[0]==INTSETTINGSREPORT_SHOWINALLPAGES)
   	        {
   	        	txtDate.setText(reportDateToBePrinted);
   	        	//dotmatrixDate.setText(reportDateToBePrinted);
   	        	dotmatrixDate = utilsPanelReport.getStringForPositionInTheMiddle(reportDateToBePrinted,dotmatrixCompanyName,charsPerLine);
   	        }
   	        else if (intSettingsReport[0]==INTSETTINGSREPORT_SHOWFIRSTPAGE)
   	        {
   	        	if(page==1)
   	        	{
   	        	    txtDate.setText(reportDateToBePrinted);
   	        	    //dotmatrixDate.setText(reportDateToBePrinted);	
   	        	    dotmatrixDate = utilsPanelReport.getStringForPositionInTheMiddle(reportDateToBePrinted,dotmatrixCompanyName,charsPerLine);
   	        	}
   	        	 
   	        }
   	        else if (intSettingsReport[0]==INTSETTINGSREPORT_NOTSHOW)
   	        {
   	        	txtDate.setText("");
   	        	//dotmatrixDate.setText("");
   	        	dotmatrixDate = utilsPanelReport.getStringForPositionInTheMiddle("",dotmatrixCompanyName,charsPerLine);
   	        }
   	        else
   	        {
   	           System.out.println("ReportPanelPage.generatePageHeader UNKNOWN else intSettingsReport[0] "+intSettingsReport[0]);	
   	        	txtDate.setText("");
   	        	//dotmatrixDate.setText("");
   	        	dotmatrixDate = utilsPanelReport.getStringForPositionInTheMiddle("",dotmatrixCompanyName,charsPerLine);                   
   	        }   	         
   	         	 txtDate.setBorder(null);
                 txtDate.setFont(fontNormal);
                 txtDate.setBackground(Color.white);
                 txtDate.setEditable(false); 
                 txtDate.setHorizontalAlignment(SwingConstants.CENTER);



  
                 
                 
          JTextField txtPage = new JTextField();
          txtPage.setText("σελίδα "+pageCurrent+" από "+pageTotal);
          //dotmatrixPage=.setText("σελίδα "+pageCurrent+" από "+pageTotal);
   	         	 txtPage.setBorder(null);
                 txtPage.setFont(fontNormal);
                 txtPage.setBackground(Color.white);
                 txtPage.setEditable(false);   
                 txtPage.setHorizontalAlignment(SwingConstants.RIGHT);        	      
   	      
   	      panelPageHeader1.add(txtCompany);
   	      panelPageHeader1.add(Box.createHorizontalGlue());  //   not working
   	      panelPageHeader1.add(txtDate);
   	      panelPageHeader1.add(Box.createHorizontalGlue());
   	      panelPageHeader1.add(txtPage);
 
              
              //title=title.toUpperCase();
    	      JTextField txtReportTitle = new JTextField(title);
   	         	 txtReportTitle.setBorder(null);
                 txtReportTitle.setFont(fontBold);
                 txtReportTitle.setBackground(Color.white);
                 txtReportTitle.setEditable(false);
                 txtReportTitle.setHorizontalAlignment(SwingConstants.CENTER);

    	      JTextField txtReportSubTitle = new JTextField(subTitle);
   	         	 txtReportSubTitle.setBorder(null);
                 txtReportSubTitle.setFont(fontBold);
                 txtReportSubTitle.setBackground(Color.white);
                 txtReportSubTitle.setEditable(false);
                 txtReportSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
          
          JPanel panelTitle = new JPanel();
          panelTitle.setLayout(new BoxLayout(panelTitle, BoxLayout.PAGE_AXIS));
          panelTitle.add(txtReportTitle);
          panelTitle.add(txtReportSubTitle);
          
          
          panelPageHeader2.add(panelTitle, BorderLayout.CENTER);
        
        // -------------------------------------  dot matrix --------------------------------------------   	     
   	     //dotmatrixCompanyName = VariablesGlobal.globalCompanyName;
   	     //dotmatrixDate = utilsPanelReport.getStringForPositionInTheMiddle(reportDateToBePrinted,dotmatrixCompanyName,charsPerLine);
   	     String dotmatrixPage =utilsPanelReport.getStringForPositionInTheRight("σελίδα "+pageCurrent+" από "+pageTotal,dotmatrixDate+dotmatrixCompanyName,charsPerLine);
   	     strPrintDotMatrix=strPrintDotMatrix+dotmatrixCompanyName+dotmatrixDate+dotmatrixPage+"\r\n"; 
  	     strPrintDotMatrix=strPrintDotMatrix+utilsPanelReport.getStringForPositionInTheMiddle(title,"",charsPerLine)+"\r\n"; 
  	     strPrintDotMatrix=strPrintDotMatrix+utilsPanelReport.getStringForPositionInTheMiddle(subTitle,"",charsPerLine)+"\r\n";
   	      
        //-----------------------------------------------------------------------------------------------  
          //if(page==1 && linesOfReportHeader>0) // only in first page
          

          
          if(linesOfReportHeader>0) 	      
          {// generate report header
             generateReportHeader(); 
             panelPageHeader.add(panelReportHeader);	      
          }
           	      
   	      
 	      JPanel panelPageHeader3 = new JPanel();
          panelPageHeader3.setLayout(layoutHorizBorderCenter);
          panelPageHeader3.setBackground(Color.white);
          //panelPageHeader3.setBorder(BorderFactory.createLineBorder(Color.black));
          panelPageHeader.add(panelPageHeader3);
          
          
          panelPage.add(panelPageHeader, BorderLayout.PAGE_START);
   	      

   }
   
   //called by this setEntity
   /*private void generatePage(int page)
   {
   	pageCurrent=page;
   	//System.out.println("ReportPanelPage.generatePage "+page);	
   	strPrintDotMatrix="";
   	generatePageHeader(page);
   	generateMain(page);
//   	generatePageFooter(page);
   	
   }*/
    // sets settings to reportAreaGenerated
   private boolean generateMain(int page, String queryFormForPrinting, String formFieldToGetData)
   {
       boolean hasDataInReport = false;
    //System.out.println(" -- ReportPanelPage.generateMain  viewType:"+viewType+"     showColumnsPerBand:"+showColumnsPerBand+"     page:"+page);
       if(viewType.equalsIgnoreCase("ODMR"))
       {
       	
        //System.out.println("ReportPanelPage.generateMain  --=--  page:"+page+"    fromRecInPage"+fromRecInPage +"       toRecInPage:"+toRecInPage+"     listOfRecords"+listOfRecords.size());
           
       	  reportAreaGenerated = new ReportAreaGenerated(); //   ReportAreaGenerated iscalled only from this point
   	       reportAreaGenerated.setEntity(title,subTitle,/*entityReportBand,*/listOfRecords, isDotmatrix,false,viewType,strPageSizeDotmatrix,
            page,fromRecInPage,toRecInPage,queryReport,  listOfListOfColumns, showColumnsPerBand, showColumnsHeader, listSumPagePre, listRecordField,  listSumRecCountMaster, listSumsOfPage, pageTotal, 
            pageFormat,  showSummaryInPage,   /*showLines,*/dotmatrixCpi, intSettingsReport );
   	       
   	       reportAreaGenerated.generateArea(page);
   	       
   	       strPrintDotMatrix=strPrintDotMatrix+reportAreaGenerated.getStrPrintDotmatrix();
   	       

           panelPage.add(reportAreaGenerated, BorderLayout.CENTER);
           
           hasDataInReport = true;
       	
       }
       else if (viewType.equalsIgnoreCase("ODOR"))
       {
           hasDataInReport = true;
       }
       else if(viewType.equalsIgnoreCase("FORM"))
       {
           if(entityReport==null)
           {
               System.out.println("  NULL  ReportPanelPage.generateMain      entityReport:"+entityReport);  
           }

      	  boolean hasData = reportAreaForm.setEntity(name,entityReport,isDotmatrix,page,listOfListOfColumns,listRecord, arrayOfNameOfPksOfRecordToShow,arrayOfValueOfPksOfRecordToShow,
                  queryFormForPrinting, formFieldToGetData);
         // System.out.println("ReportPanelPage.generateMain      viewType:"+viewType+"     hasData:"+hasData+"     isDotmatrix:"+isDotmatrix+"     listRecord.size:"+listRecord.size()+"       queryFormForPrinting:"+queryFormForPrinting+"       formFieldToGetData:"+formFieldToGetData);
       	  if(hasData)   
          {
           strPrintDotMatrix=reportAreaForm.getStrPrintDotmatrix();     
         
             panelPage.add(reportAreaForm, BorderLayout.CENTER);
             hasDataInReport = true;
          }
          else
          {
              hasDataInReport = false;
          }
       }
       
      
       return hasDataInReport;
   }
   
   
   private void generatePageFooter(int page)
   {     
   
      
   }

   /*private String[] clickedOnRow(String queryIn, String entityIn, int selectedTableRowIn, String primKeyIn)
   {
   	
      return retrievePrimKeyValue(queryIn,entityIn,selectedTableRowIn,primKeyIn);// row starts from 1
   	
   }*/

    // there is a similar in PanelODMRData but differs
   /*private String[] retrievePrimKeyValue(String queryIn, String entityIn, int selectedTableRowIn, String primKeyIn)
   // parameters are needed for panelTwoDataManyRec
   {
 
       String[] primKeysValue = new String[1];  
         DatabaseTableMeta databaseTableMeta = new DatabaseTableMeta();
              
     try
     {
         databaseTableMeta.retrievePrimKs(entityIn);
         int primKeysCount = databaseTableMeta.getCountOfPrimKeys();
         //System.out.println("PanelODMRData.retrievePrimKeyValue primKeysCount "+primKeysCount);
         String[] primKeys = new String[primKeysCount];
         primKeysValue = new String[primKeysCount];
         for (int pk=0;pk<primKeysCount;pk++)
         {
             	
            	String pkName;
             	//System.out.println("PanelODMRData.retrievePrimKeyValue isEditable "+isEditable);
             	if (primKeyIn==null)
             	{
             	  pkName=databaseTableMeta.getPrimKeyName(pk);//before
             	  //System.out.println("PanelODMRData.retrievePrimKeyValue "+entity+" --- "+pk+" "+pkName+"-"+primKeyIn);
              	}
             	else
             	{
                  pkName=primKeyIn;// so translation be equal with translation
                  //System.out.println("PanelODMRData.retrievePrimKeyValue "+entity+" "+pk+" "+pkName);
                }

                
         /* 	     String whereValueName = getWhereValueNameThatMatchesColumn(pkName);
          	   // System.out.println("PanelODMRData.retrievePrimKeyValue whereValueName"+whereValueName);
          	     if(!whereValueName.equalsIgnoreCase("-"))
          	     {
                     if (whereValueName.equalsIgnoreCase("primKeyValue"))
                     {
                     	//System.out.println("PanelODMRData.retrievePrimKeyValue whereValueName "+whereValueName);
                     	//System.out.println("PanelODMRData.retrievePrimKeyValue isQuery2"+isQuery2);
                     	if(!isQuery2)
                     	{*/
                     	  // System.out.println("PanelODMRData.retrievePrimKeyValue ="+pk+" pkName:"+pkName);
                        //System.out.println("PanelODMRData.retrievePrimKeyValue "+queryIn); 
    /*                    db.retrieveDBDataFromQuery(queryIn,"ReportPanelPage.retrievePrimKeyValue");
                        // rs = db.getRS();
                          // System.out.println("PanelODMRData.retrievePrimKeyValue "+queryIn);
                           //System.out.println("PanelODMRData.retrievePrimKeyValue ="+pk+" pkName:"+pkName+" primKeyValue "+primKeyValue);
                        rs = db.retrieveRow(rs, selectedTableRowIn);
                        primKeysValue[pk] =rs.getString(pkName);
                        System.out.println("PanelODMRData.retrievePrimKeyValue pk:"+pk+" pkName:"+pkName+" "+primKeysValue[pk]); 
                           
                     /*	}
                     	else
                     	{
                     	   //System.out.println("PanelODMRData.retrievePrimKeyValue +"+pk+" pkName:"+pkName);
                           primKeys[pk]=pkName;
                           primKeysValue[pk]=primKeyValueSetted;                     		
                          // System.out.println("PanelODMRData.retrievePrimKeyValue -"+pk+" pkName:"+pkName+" primKeyValue "+primKeyValue); 
                     	}
                    
                     //query = "SELECT * FROM "+entity;
                     //System.out.println("PanelODMRData.retrievePrimKeyValue "+q);


                //     System.out.println("PanelODMRData.retrievePrimKeyValue -"+pk+" pkName:"+pkName+" primKeysValue[pk] "+primKeysValue[pk]);
                     }
                     else  //if is a globalvariable then value should be getted from getValueForVariable(getWhereValueNameThatMatchesColumn(pkName));
                     {
                     	primKeys[pk]=pkName;
                     	//System.out.println("PanelODMRData.retrievePKValue [glb]"+pk+" "+primKeys[pk] );
                     	primKeysValue[pk]=getValueForVariable(getWhereValueNameThatMatchesColumn(pkName));
                     //	System.out.println("PanelODMRData.retrievePKValue [glb]"+pk+" primKeys[pk]:"+primKeys[pk]+" primKeysValue[pk] "+primKeysValue[pk]);
                     }
                     //System.out.println("PanelODMRData.retrievePrimKeyValue pkName:"+pkName+" "+getValueForVariable(whereValueName)+" whereValueName "+whereValueName);
                     //getValueForVariable(whereValueName);
                 }
                 else
                 {
                 	
                 	//System.out.println("PanelODMRData.retrievePKValue else "+pk);
 	                //if is not a globalvariable then value should be get from jtable
                    for (int i = 0; i < tableModel.getColumnCount(); i++)//  i = fields
                    {
                    	//System.out.println("PanelODMRData.retrievePKValue "+i);
                    	String columnLabel="";
                    	//columnLabel = tableModel.getColumnName(i);//fieldsManyTranslation[i]; //get colunm name
                     	  columnLabel = tableModel.getColumnName(i);//fieldsManyTranslation[i]; //get colunm name
        	            if (columnLabel.equalsIgnoreCase(pkName))
        	            //if (pkName.equalsIgnoreCase(fieldsMany[i]))
        	            {
          	                primKeyValue =  tableModel.getValueAt(selectedTableRow,i).toString();
       
                            primKeys[pk]=primKeyDb;
                            primKeysValue[pk]=tableModel.getValueAt(selectedTableRow,i).toString();
            
        		            //System.out.println("PanelODMRData.retrievePKValue [jtb]"+pk+" primKeys[pk]:"+primKeys[pk]+" primKeyValue "+primKeyValue);
          	             }
          	             else
          	             {
          	             	//System.out.println("PanelODMRData.retrievePKValue "+pk+" "+i);
          	             	if (isQuery2 && pkName.equalsIgnoreCase(fieldsMany[i]))
                     	    {
                     	    	
                     		   //System.out.println("PanelODMRData.retrievePrimKeyValue columnLabel "+columnLabel+" "+fieldsMany[i]+" "+pkName);
                     		   
                            primKeys[pk]=pkName;
                            primKeysValue[pk]=tableModel.getValueAt(selectedTableRow,i).toString();
                     //	System.out.println("PanelODMRData.retrievePKValue [jtb alt]"+pk+" primKeys[pk]:"+primKeys[pk]+" primKeyValue "+primKeyValue);	   
                     		
                     	     }
          	                 //primKeys[pk]=pkName;
          	                 //System.out.println("PanelODMRData.retrievePrimKeyValue columnLabel"+columnLabel+" not "+pkName);
          	             }
                     }
                 }*/
                
  /*       }       
      }
      catch(Exception e)
      {  System.out.println("Error ReportPanelPage.retrievePrimKeyValue()" + e);
      }
      
      return primKeysValue;
   }*/ 


 
 // called by panelPrintPreview.goPrint
   public void getHeaderFooterHeight()
   { //footer 22,   header 52 
   	 int height = panelPageHeader.getHeight();//+panelPageFooter.getHeight();
   	 System.out.println("  TODO rewrite code: ReportPanelPage.getHeaderFooterHeight "+height);
   }
    
    
    public void getPageWidth()
    {
    	
    	for(int p =0;p<arrayPanelPage.size();p++)
    	{
    	    JPanel panel = (JPanel)arrayPanelPage.get(p);	
        	System.out.println("ReportPanelOneDataManyRec.getPageWidth  width:"+panel.getWidth());

    	}
    	
    }
    
    public void getPageHeight()
    {
    	
    	 //JPanel printablePanel = new JPanel();
    	//printablePanel.setLayout(new BoxLayout(printablePanel, BoxLayout.PAGE_AXIS));
    	
    	//System.out.println("ReportPanelOneDataManyRec.getPageHeight");
    	
    	for(int p =0;p<arrayPanelPage.size();p++)
    	{
    	    JPanel panel = (JPanel)arrayPanelPage.get(p);
        	
        	System.out.println("ReportPanelPage.getPageHeight "+p+" "+panel.getHeight()+" "+(p*panel.getHeight()+panel.getHeight()) );

    	}
    	
    	//return printablePanel;	
    }
    

    
    public boolean isPageWidthBiggerThanPaper()
    {
      boolean ret=false;
      double lengthOfColumns=0;
    	
      if(isDotmatrix)// is dot matrix
      {
      	int pageWidth = 0;
       if(strPageSizeDotmatrix!=null && strPageSizeDotmatrix.equalsIgnoreCase("A3"))
       {
       	 pageWidth=DOTMATRIX_CHARS_PER_LINE_A3;
       }
       else
       {
       	//pageWidth=DOTMATRIX_CHARS_PER_LINE_A4;
       	 if(dotmatrixCpi==-1)
       	 {
       	 	pageWidth=DOTMATRIX_CHARS_PER_LINE_A4_CPI10;
       	 }
       	 else if(dotmatrixCpi==10)
       	 {
       	 	pageWidth=DOTMATRIX_CHARS_PER_LINE_A4_CPI10;
       	 }
       	 else if(dotmatrixCpi == 15)
       	 {
       	 	pageWidth=DOTMATRIX_CHARS_PER_LINE_A4_CPI15;
       	 }       	
       	
       }
      	//System.out.println("ReportPanelPage.isPageWidthBiggerThanPaper"+lengthOfColumns+" "+pageWidth);
      	
      	 
    	for(int i =0;i<listOfListOfColumns.size();i++)
    	{
//    		if(showColumns[i])
//    		{
    		  
    		  RecColumn col = (RecColumn)listOfListOfColumns.get(i+1);
    		  lengthOfColumns=lengthOfColumns+col.getColumnLength();
    		  //lengthOfColumns=lengthOfColumns+intSumPagePreLength[i]+1;
//    		}
    	}
    	lengthOfColumns=lengthOfColumns+1;
    	
    	//System.out.println("ReportPanelPage.isPageWidthBiggerThanPaper"+lengthOfColumns+" "+pageWidth);
    	
    	if(lengthOfColumns>pageWidth)
    	{
    		ret=true;
    		System.out.println("ReportPanelPage.isPageWidthBiggerThanPaper"+lengthOfColumns+">"+pageWidth);
    		if(strPageSizeDotmatrix.equalsIgnoreCase("A4"))
    		{
    			utilsGui.showMessageError(this,"ReportPanelPage.isPageWidthBiggerThanPaper\nΤο πλάτος της σελίδας είναι μικρότερο από το άθροισμα των επιλεγμένων στηλών."+
    		    "\nΕίτε αποεπειλέξτε στήλες είτε επιλέξτε σελίδα Α3.");
    		}
    		else
    		{
    		 	utilsGui.showMessageError(this,"ReportPanelPage.isPageWidthBiggerThanPaper\nΤο πλάτος της σελίδας είναι μικρότερο από το άθροισμα των επιλεγμένων στηλών."+
    		    "\nΑποεπειλέξτε στήλες.");
    		}
    	}
      	
      }
      else
      {
      
       	double pageWidth = pageFormat.getImageableWidth();
       	
    	for(int i =0;i<listOfListOfColumns.size();i++)
    	{
//    		if(showColumns[i])
//    		{
    		  
    		  RecColumn col = (RecColumn)listOfListOfColumns.get(i+1);
    		  lengthOfColumns=lengthOfColumns+col.getColumnLength();
    		  //lengthOfColumns=lengthOfColumns+intSumPagePreLength[i]+1;
//    		}
    	}
    	
    	lengthOfColumns=(lengthOfColumns*6.18)-30;
    	
    	System.out.println("ReportPanelPage.isPageWidthBiggerThanPaper"+lengthOfColumns+"--"+pageWidth);
    	
    	if(lengthOfColumns>pageWidth)
    	{
    		ret=true;
    		System.out.println("ReportPanelPage.isPageWidthBiggerThanPaper"+lengthOfColumns+">"+pageWidth);
    		utilsGui.showMessageError(this,"ReportPanelPage.isPageWidthBiggerThanPaper\nΤο πλάτος της σελίδας είναι μικρότερο από το άθροισμα των επιλεγμένων στηλών."+
    		"\nΕίτε αποεπειλέξτε στήλες είτε επιλέξτε μεγαλύτερη σελίδα.");
    	}
      }	
      

      
    	return ret;
    }
    
    public int getCurrentPage()
    {
    	return pageCurrent;
    }
   
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
    
    //System.out.println("ReportPanelPage.print  "+pageIndex+" "+pageFormat+" pf "+pf);
    
     if (panelWidth >= pageWidth)
     {
            scale = pageWidth / panelWidth;
     }   
    //System.out.println("Printpanel.print pageHeight="+pageHeight+" panelHeight="+panelHeight);
	//System.out.println("ReportPanelOneDataManyRecPage.print scale="+scale+" pageHeight/panelHeight="+pageHeight/panelHeight);

    g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
    //g2.translate(pf.getImageableX(), pf.getImageableY());

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