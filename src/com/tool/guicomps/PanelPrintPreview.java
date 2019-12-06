package com.tool.guicomps;

import com.tool.jdbc.*;
import com.tool.gui.*;
import com.tool.model.*;
import com.tool.utils.*;
//import prt.*;
import com.tool.rpt.*;
//import com.tool.report.*;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.awt.print.PageFormat;

 import java.awt.event.ActionListener;
 import java.awt.event.ActionEvent;
 import java.awt.CardLayout;
 import java.awt.print.PrinterJob;
 import java.awt.print.PageFormat;
 import java.awt.print.PrinterException;
 import java.awt.SystemColor;
 import java.awt.event.KeyEvent;
 import java.awt.print.Paper;
 

import javax.swing.KeyStroke;
import javax.swing.JEditorPane;
import javax.swing.JDialog;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import javax.swing.text.View;
import javax.swing.text.html.HTMLDocument;
import javax.swing.Action;
import javax.swing.AbstractAction;

import javax.swing.RepaintManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.print.Printable;
import java.awt.print.Pageable;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.GraphicsEnvironment;
import java.awt.print.PrinterJob;


import javax.swing.JTable;
import javax.swing.JComponent;
import javax.swing.border.MatteBorder;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
//import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
//import javax.swing.border.TitledBorder;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JToolBar;

import javax.print.event.PrintJobAdapter;
/*import javax.print.PrintService;
import javax.print.PrintServiceLookup;
 import javax.print.attribute.PrintRequestAttributeSet;
 import javax.print.attribute.HashPrintRequestAttributeSet;
 import javax.print.ServiceUI;
 import javax.print.DocPrintJob;*/
 

 import java.net.URL;
import java.net.MalformedURLException;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.PrintStream;

import java.nio.charset.Charset;

    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.ResultSetMetaData;
    
    import java.util.Timer;
    import java.util.TimerTask;
    import java.util.Vector;
    import java.util.ArrayList;
    import java.util.StringTokenizer;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.border.BevelBorder;
    
public class PanelPrintPreview extends JxPanel implements Constants
{

    private JLabel lblTitle;
    private String name;

    private JButton btnPrint;
    private JScrollPane scrollpaneLabel;
    private JScrollPane scrollpaneText;

    private JPanel panelPrintPreview;
    private JPanel panelPrintPreviewNormal;
    private JPanel panelPrintPreviewDotmatrix;
    private JListDec reportAreaList;//  dot matrix print area
    private JTextField txtPrintPagesFrom;//
    private JLabel lblPageTo;
    private JTextField txtPrintPagesTo;
  
    private JButton btnPrevious;
    private JButton btnNext;
    private JButton btnFirst;
    private JButton btnLast;
    

    private boolean isDotmatrix ;

    private String entity;
    private String entityMany;
    private String query;

    private String title;
    private String subTitle;
    //private Vector dataVector;
   
    private String viewType;
    //private int[] showColumns; 
    private ArrayList showColumnsPerBand;
    private int[] showColumnsHeader;
    private String primKey;
    //private String primKeyValue;
    private String sqlHeader;
    

    
    private String strPrintDotMatrix;
    private String strPortDotmatrix;
    
    private JComboBox comboPage;


    private PrinterJob printerJob;
    private PageFormat pageFormat;
    

    private JPanel panelScrollLeft;
    private JPanel panelScrollRight;
    
    private int rowData=1;
    //private String orientationLandscape="οριζόντια";
    //private String orientationPortrait="κατακόρυφα";
    //private int columnCount;
    private boolean showSummaryPage;

    
    private ReportCalcs reportCalcs;
    //private ReportCalculations reportCalculations;

    //private String[] sql2WhereField;
    //private String[] sql2WhereValue;
   //ReportPanelDoc  reportPanelDoc;
    
    private UtilsGui utilsGui;

    private JLabel lblFromPages;
    private JLabel lblPage;
    private JLabel lblPageFrom;
    //private boolean[] showLines;
    
    private String strDotMatrixPageSize;
    
    private JScrollPane scrollPanel;
    private JPanel panelScrollCenter;
    
    private int pageCurrent=1;
    private int entityReportSettingsLength=0;
    
    ReportPanelPage reportPanelPage;
    EntityFilterSettings[] entityFilterSettings;
    //private Thread thread;
    
    private String[] arrayStringsToBePrinted;
    //private ReportPanel reportPanel;
    private String entityHeader;
    
    private JButton btnFilters;
    
   // private EntityReportBand[] entityReportBand;
    private DefaultListModel listModel;
    private UtilsString utilsString;
    
    
    
    private String[] arrayOfNameOfPksOfRecordToShow;
   private String[] arrayOfValueOfPksOfRecordToShow;
    
    private int dotmatrixCpi;
    private String pageSizeLaser="";
    private boolean pageOrientationIsPortraitLaser=true;
 
     /* decimal ascii values for epson ESC/P commands */
    private static final char ESC = 27; //escape
    
    private static final char RS = 30; // 10 cpi for oki
    private static final char DC2 = 18; // 10 cpi proprinter
    
    private static final char AT = 64; //@
    private static final char LINE_FEED = 10; //line feed/new line
    private static final char PARENTHESIS_LEFT = 40;
    private static final char BACKSLASH = 92;
    private static final char CR = 13; //carriage return
    private static final char TAB = 9; //horizontal tab
    private static final char FF = 12; //form feed
    private static final char g = 103; //15cpi pitch
    private static final char p = 112; //used for choosing proportional mode or fixed-pitch
    private static final char t = 116; //used for character set assignment/selection
    private static final char l = 108; //used for setting left margin
    private static final char x = 120; //used for setting draft or letter quality (LQ) printing
    private static final char E = 69; //bold font on
    private static final char F = 70; //bold font off
    private static final char J = 74; //used for advancing paper vertically
    private static final char P = 80; //10cpi pitch
    private static final char Q = 81; //used for setting right margin
    private static final char $ = 36; //used for absolute horizontal positioning
    private static final char ARGUMENT_0 = 0;
    private static final char ARGUMENT_1 = 1;
    private static final char ARGUMENT_2 = 2;
    private static final char ARGUMENT_3 = 3;
    private static final char ARGUMENT_4 = 4;
    private static final char ARGUMENT_5 = 5;
    private static final char ARGUMENT_6 = 6;
    private static final char ARGUMENT_7 = 7;
    private static final char ARGUMENT_25 = 25;  
    
    private boolean[] boolSettingsReport;
    private int[] intSettingsReport;
    private EntityReport entityReport;
    private ToolBarData toolBarData;
    private JTextArea txtAreaNoData;
    
    private String queryFormForPrinting ="" ;              
    private String formFieldToGetData="" ;
    
    //private  String lastVal = "";    //getPageTotal
   // private  int countOfPages =1;
   // private  int changesOfHeaderRecord=0;
    
	public PanelPrintPreview()
	{
            try
           {     initialize();   }
           catch (Exception e)
           {   e.printStackTrace();    }
		
	}
	
	private void initialize() throws Exception
    {
    	
       printerJob = PrinterJob.getPrinterJob();
       utilsString = new UtilsString();
       
    
//        printTextPane = new PrintTextPane();
//        printTable = new PrintTable();
        utilsGui = new UtilsGui();
//        reportPanel = new ReportPanel();
        
        toolBarData = new ToolBarData();


	    
	    reportCalcs = new ReportCalcs();
            //reportCalculations = new  ReportCalculations();
	    //reportPanelTwoDataManyRec = new ReportPanelTwoDataManyRec();
	    

       //isPrintPreview=true;
       //lblTitle.setFont(new java.awt.Font("Courier", 1, 11));
       lblTitle = new JLabel("Εκτύπωση: ");
       lblTitle.setOpaque(true);
       lblTitle.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
       lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD));
       lblTitle.setBackground(SystemColor.activeCaption);//panel.getBackground().brighter());        
        //lblTitle.setFont(new java.awt.Font("Century", 1, 16));
        lblTitle.setForeground(Color.white);
        lblTitle.setFocusable(false);
        lblTitle.setIcon(ICO_PRINT_PREVIEW16);
        this.setLayout(new BorderLayout());

        reportAreaList = new JListDec();//(10,70);
        reportAreaList.setFixedCellHeight(15);
        listModel = new DefaultListModel();
        
        reportAreaList.setModel(listModel);
        
        // reportAreaList.setEditable(false);
        JScrollPane scrollPaneTextArea = new JScrollPane(reportAreaList);

       

        scrollpaneLabel  = new JScrollPane();    
         //        scrollpaneLabel.setViewportView(lblPrintPreview);
         //       reportArea.setBackground(Color.white);
       //scrollpaneLabel.setViewportView(reportArea);
       
       //scrollpaneLabel.setViewportView(reportCalcs); 
        //scrollpaneLabel.setViewportView(reportCalcs);        
        
       /* scrollpaneText  = new JScrollPane();    
        scrollpaneText.setViewportView(lstPrintPreviewArea);*/
        

        panelPrintPreview = new JPanel();
       // panelPrintPreview.setLayout(new CardLayout());//new BoxLayout(panelPrintPreview, BoxLayout.Y_AXIS));
        panelPrintPreview.setLayout(new BorderLayout());
        //panelPrintPreview.add(scrollpaneLabel);


             
        panelPrintPreviewNormal = new JPanel();
        panelPrintPreviewNormal.setLayout(new BoxLayout(panelPrintPreviewNormal, BoxLayout.Y_AXIS));
        panelPrintPreviewNormal.add(scrollpaneLabel);
        
        panelPrintPreviewDotmatrix = new JPanel();
        panelPrintPreviewDotmatrix.setLayout(new BoxLayout(panelPrintPreviewDotmatrix, BoxLayout.Y_AXIS));
        panelPrintPreviewDotmatrix.add(scrollPaneTextArea); 


       JPanel panelScroll = new JPanel();
       
       panelScrollCenter  = new JPanel();
       panelScrollCenter.setLayout(new CardLayout());//new BorderLayout());
       panelScrollCenter.setBackground(Color.GRAY);  //ORANGE
       //panelScrollCenter.setPreferredSize(new Dimension(420, 250));
//       panelScrollCenter.setBorder(BorderFactory.createLineBorder(Color.black,1));
       
       
        panelScroll.setLayout(new BorderLayout());
        
        panelScrollLeft = new JPanel();
        panelScrollLeft.setBackground(Color.gray);
        panelScrollRight = new JPanel();
        panelScrollRight.setBackground(Color.gray);
        JPanel panelScrollTop = new JPanel();
        panelScrollTop.setBackground(Color.gray);
        JPanel panelScrollBottom = new JPanel();
        panelScrollBottom.setBackground(Color.gray);
       
     
       
       panelScroll.add(panelScrollLeft,BorderLayout.LINE_START);
       panelScroll.add(panelScrollRight,BorderLayout.LINE_END);
       panelScroll.add(panelScrollTop,BorderLayout.PAGE_START);
       panelScroll.add(panelScrollBottom,BorderLayout.PAGE_END);
       
       JPanel panelScrollCenterInto = new JPanel();
       panelScrollCenterInto.setBackground(Color.GRAY); // ORANGE
        panelScrollCenterInto.add(
            panelScrollCenter,
            new GridBagConstraints(
                0,
                0,
                1,
                1,
                1.0,
                0.0,
                GridBagConstraints.CENTER,
                GridBagConstraints.CENTER,
                new Insets(1, 1, 1, 1),
                0,
                0));      
       
       panelScroll.add(panelScrollCenterInto,BorderLayout.CENTER);
       //panelScroll.add(panelScrollCenter,BorderLayout.CENTER);
                         
      
        scrollPanel = new JScrollPane(panelScroll);


        //panelPrintPreview.addActionListener(heavyAction);                     /////////////////////////////////////

      /* reportPanelDoc =  new ReportPanelDoc();
        BorderLayout bl = new BorderLayout();
        //this.setLayout(bl);
        reportPanelDoc.setLayout(bl);*/

            txtAreaNoData = new JTextArea();
            Font f = this.getFont();
            txtAreaNoData.setFont(f);            
            txtAreaNoData.setBackground(this.getBackground());
            txtAreaNoData.setForeground(this.getForeground());
            txtAreaNoData.setEditable(false);

            
        add(toolBarData,BorderLayout.PAGE_START);
        add(panelPrintPreview,BorderLayout.CENTER);
        add(txtAreaNoData,BorderLayout.PAGE_END);                         
                 
      
      

    }

        
    public void setText(String text)
    {
        //btnPreview.setText("προεπισκόπηση "+text);
        lblTitle.setText("Εκτύπωση :"+text+":");
//        lblPrintPreview.setText("Εκτύπωση \n"+text+":");
    }	

    /*
    *  called by  PanelReportSettingsPreview.showPanelReport
    */
        public void setEntityForPreviewOfForm(String entityIn, EntityReport entityReportIn,boolean isDotmatrixIn,String strDotMatrixPageSizeIn,String strPortDotmatrixIn,
                String [] arrayStringsToBePrintedIn, PageFormat pageFormatIn,int[] intSettingsReportIn,String titleIn, String subTitleIn, String queryIn,
                String pageSizeLaserIn, boolean pageOrientationIsPortraitLaserIn ,int dotmatrixCpiIn, String[] arrayOfNameOfPksOfRecordToShowIn,
                String[] arrayOfValueOfPksOfRecordToShowIn)
      {
          entity=entityIn;
            entityReport=entityReportIn;
            name = entityReport.getName();
            viewType=entityReport.getType();
        strDotMatrixPageSize=strDotMatrixPageSizeIn;
        strPortDotmatrix=strPortDotmatrixIn;
        //System.out.println("PanelPrintPreview.setEntity      ====       isDotmatrixIn:"+isDotmatrixIn);
        isDotmatrix = isDotmatrixIn;    
        selectIsDotmatrix(isDotmatrixIn);
//            entityReportBand=entityReportIn.getEntityReportBands();
            arrayStringsToBePrinted=arrayStringsToBePrintedIn;
            dotmatrixCpi=dotmatrixCpiIn;
            pageFormat=pageFormatIn;
            intSettingsReport=intSettingsReportIn;
        title=titleIn;
        subTitle=subTitleIn;    
        query=queryIn; // is getted from panel data filter
        pageSizeLaser=pageSizeLaserIn;
        pageOrientationIsPortraitLaser=pageOrientationIsPortraitLaserIn;     
       // entityFilterSettings=entityFilterSettingsIn;
        
       // showColumnsPerBand=showColumnsPerBandIn;
       // showColumnsHeader= showColumnsHeaderIn;   
         //      queryFormForPrinting          formFieldToGetData
               

     queryFormForPrinting = entityReport.getFormQueryForPrinting();//+" "+utilsString.getOrderbySubQuery(queryIn); //AND printForm.dbCompanyId LIKE "+VariablesGlobal.globalCompanyId;
       
       //   actiontype.dbCompanyId = 1 AND printForm.dbCompanyId = 1 AND                  
     formFieldToGetData = entityReport.getFormFieldToGetData();  
       
       
   	txtPrintPagesFrom.setVisible(false);
        lblPageTo.setVisible(false);
       txtPrintPagesTo.setVisible(false);  
       
       
          arrayOfNameOfPksOfRecordToShow=arrayOfNameOfPksOfRecordToShowIn;
          arrayOfValueOfPksOfRecordToShow=arrayOfValueOfPksOfRecordToShowIn;
            
        
        
//      System.out.println("-PanelPrintPreview.setEntityForDirectPrintOfForm   showColumnsPerBand:"+showColumnsPerBand+"      query:"+query+"          pages to add "+getPageTotal());
/*        if (pageFormat == null)
        {
             printerJob = PrinterJob.getPrinterJob();
             pageFormat = printerJob.defaultPage();
        }        
*/

        fetchData(); 

        System.out.println("PanelPrintPreview.setEntityForDirectPreviewOfForm     viewType:"+viewType+"       after fetchData    isDotmatrix:"+isDotmatrix+"   query:"+query);
        int totalPages = getPageTotal();
        comboPage.removeAllItems();
        txtPrintPagesFrom.setText("1");
        txtPrintPagesTo.setText(totalPages+"");
        
        //int[] arrPages = new int[getPageTotal()];
       // System.out.println("PanelPrintPreview.setEntityForDirectPreviewOfForm             pages to add:"+getPageTotal());
        for(int pg =0; pg<totalPages;pg++ )
        {
         	  //arrPages[p]= p+1;
         	  //System.out.println("pg --  "+pg);
         	  comboPage.addItem(pg+1);
        }
        
        if (totalPages>0)
        {
        	//comboPage.setSelectedIndex(0); // loads 2 times if selected
        }
        
        if(arrayOfNameOfPksOfRecordToShow==null)
        {  
            btnFilters.setVisible(true);
            //System.out.println("PanelPrintPreview.setEntityForDirectPreviewOfForm   -FORM-   arrayOfNameOfPksOfRecordToShow:"+arrayOfNameOfPksOfRecordToShow+" NOT for aspecific record. Perhaps mass printing");        
        }      
        else
        {
           btnFilters.setVisible(false);
        }
        
           closeDB();   
      }

        /*
        
        *  called by PanelReportSettingsPreview.showPanel
        
        */
        public void setEntity(String entityIn,EntityReport entityReportIn,boolean isDotmatrixIn,String strDotMatrixPageSizeIn,String strPortDotmatrixIn, String [] arrayStringsToBePrintedIn, 
                PageFormat pageFormatIn,int[] intSettingsReportIn,String titleIn, String subTitleIn, String queryIn, ArrayList showColumnsPerBandIn,int[] showColumnsHeaderIn,
        String pageSizeLaserIn, boolean pageOrientationIsPortraitLaserIn ,EntityFilterSettings[] entityFilterSettingsIn, int dotmatrixCpiIn)
        {
            entity=entityIn;
            entityReport=entityReportIn;
            name = entityReport.getName();
            viewType=entityReport.getType();
        strDotMatrixPageSize=strDotMatrixPageSizeIn;
        strPortDotmatrix=strPortDotmatrixIn;
        //System.out.println("PanelPrintPreview.setEntity      ====       isDotmatrixIn:"+isDotmatrixIn);
        isDotmatrix = isDotmatrixIn;    
        selectIsDotmatrix(isDotmatrixIn);
//            entityReportBand=entityReportIn.getEntityReportBands();
            arrayStringsToBePrinted=arrayStringsToBePrintedIn;
            dotmatrixCpi=dotmatrixCpiIn;
             pageFormat=pageFormatIn;
             intSettingsReport=intSettingsReportIn;
        title=titleIn;
        subTitle=subTitleIn;    
        query=queryIn; // is getted from panel data filter
        pageSizeLaser=pageSizeLaserIn;
        pageOrientationIsPortraitLaser=pageOrientationIsPortraitLaserIn;     
        entityFilterSettings=entityFilterSettingsIn;
        
        showColumnsPerBand=showColumnsPerBandIn;
        showColumnsHeader= showColumnsHeaderIn;        



        
        
        System.out.println("PanelPrintPreview.setEntity  ---- pageSizeLaser:"+pageSizeLaser+"     pageOrientationIsPortraitLaser:"+pageOrientationIsPortraitLaser+"    showColumnsPerBand:"+showColumnsPerBand+"      query:"+query+"          pages to add "+getPageTotal());
        if (pageFormat == null)
        {
             printerJob = PrinterJob.getPrinterJob();
             pageFormat = printerJob.defaultPage();
        }        
        
        fetchData();
        System.out.println("PanelPrintPreview.setEntity     viewType:"+viewType+"       after fetchData    isDotmatrix:"+isDotmatrix+"     query:"+query);
        
        comboPage.removeAllItems();
        txtPrintPagesFrom.setText("1");
        txtPrintPagesTo.setText(getPageTotal()+"");
        
        int[] arrPages = new int[getPageTotal()];
        
        
        
        
        
        
        for(int pg =0; pg<getPageTotal();pg++ )
        {
            //System.out.println("PanelPrintPreview.setEntity       page:"+pg+"     to add:"+getPageTotal());
        	buildAndGetLaserPage(pg+1, true); // true:is for preview
         	  //arrPages[p]= p+1;
         	  //System.out.println("pg --  "+pg);
         	  comboPage.addItem(pg+1);
        }
        
        if (getPageTotal()>0)
        {
            showPageFirst();
        	
        }
       closeDB();            
            
            
            
        }
    
        /**
         *
         * called by PanelReportSettingsPreview.showPanel().
         *   check if not called
         */
//	public void setEntity(String nameIn,String entityIn,String entityManyIn,boolean isDotmatrixIn,String strDotMatrixPageSizeIn,String strPortDotmatrixIn,
 //          EntityReportBand[] entityReportBandIn, String viewTypeIn,	/*Vector dataVectorIn,String primKeyValueIn,  String[] sql2WhereFieldIn, String[] sql2WhereValueIn,*/ 
//	String titleIn, String subTitleIn, boolean summaryPageIn, int[] showColumnsIn,int[] showColumnsHeaderIn, PageFormat pageFormatIn, /*boolean[] showLinesIn,*/
//	EntityFilterSettings[] entityFilterSettingsIn, String [] arrayStringsToBePrintedIn, int dotmatrixCpiIn, int[] intSettingsReportIn, /* boolean[] boolSettingsReportIn,*/
//        String pageSizeLaserIn, boolean pageOrientationIsPortraitLaserIn )
/*    {
    	name=nameIn;
        entity=entityIn;
        entityMany=entityManyIn;
        isDotmatrix=isDotmatrixIn;
        strDotMatrixPageSize=strDotMatrixPageSizeIn;
        strPortDotmatrix=strPortDotmatrixIn;
       // chkDotmatrix.setSelected(isDotmatrixIn);
        selectIsDotmatrix(isDotmatrixIn);

        entityReportBand=entityReportBandIn;

        title=titleIn;
        subTitle=subTitleIn;
        viewType=viewTypeIn;
        showColumns=showColumnsIn;
        showColumnsHeader= showColumnsHeaderIn;
        lblTitle.setText("Εκτύπωση "+title+":");
        showSummaryPage=summaryPageIn;

        pageFormat=pageFormatIn;
        entityFilterSettings=entityFilterSettingsIn;
        arrayStringsToBePrinted=arrayStringsToBePrintedIn;
        dotmatrixCpi=dotmatrixCpiIn;
        
        intSettingsReport=intSettingsReportIn;
        //boolSettingsReport= boolSettingsReportIn;
        pageSizeLaser=pageSizeLaserIn;
        pageOrientationIsPortraitLaser=pageOrientationIsPortraitLaserIn;
         //query = entityQuery[0].getQuery();

        
        System.out.println("PanelPrintPreview.setEntity  ----  entityReportBandIn "+entityReportBandIn+"     showColumns:"+showColumns);
        
        if (entityFilterSettings == null)
        {
           entityReportSettingsLength = 0;	
        }
        else
        {
        	entityReportSettingsLength=entityFilterSettings.length;
        }

        //System.out.println("PanelPrintPreview.setEntity entityReportSettingsLength "+entityReportSettingsLength);
        
        if (pageFormat == null)
        {
             PrinterJob printerJob = PrinterJob.getPrinterJob();
             pageFormat = printerJob.defaultPage();
        }


        fetchData(); 
        
        comboPage.removeAllItems();
        txtPrintPagesFrom.setText("1");
        txtPrintPagesTo.setText(getPageTotal()+"");
        
        int[] arrPages = new int[getPageTotal()];
        //System.out.println("PanelPrintPreview.setEntity pages to add "+getPageTotal());
        for(int pg =0; pg<getPageTotal();pg++ )
        {
        	
         	  //arrPages[p]= p+1;
         	  //System.out.println("pg --  "+pg);
         	  comboPage.addItem(pg+1);
        }
        
        if (getPageTotal()>0)
        {
        	//comboPage.setSelectedIndex(0); // loads 2 times if selected
        }
       closeDB();
    }*/	
        
 private void showPanelFormPrintPreview(ReportAreaForm raForm,int p)
 {
        
        
           toolBarData.setVisible(true);
            panelPrintPreview.setVisible(true);
            txtAreaNoData.setVisible(false); 
            //for(int pa=1;pa<=listPagesForm.size();pa++)
            //{
    	       panelScrollCenter.add((ReportAreaForm)raForm,"page "+p); 
            //}
               //panelScrollCenter.setPreferredSize(new Dimension(850,650));
    	       CardLayout cl = (CardLayout)(panelScrollCenter.getLayout());               
              
               cl.show(panelScrollCenter, "page "+p);        
        
             
 }
        
 private void showPanelNoDataMessage()
 {
          
   // Θα πρέπει να επιλέξετε φόρμα στο αντίστοιχο παραστατικό.
           
           //this.setPreferredSize(new Dimension(50,50));
           
            toolBarData.setVisible(false);
            panelPrintPreview.setVisible(false);

            
            txtAreaNoData.setVisible(true);     

            txtAreaNoData.setText(entityReport.getFormMessageNoForm());
 
 }
        

        /**
         * called by this.setEntity().
         */
   private void fetchData()
   {

       if(viewType.equalsIgnoreCase("ODMR"))
       {
       	
       	//System.out.println("      ooooo               PanelPrintPreview.fetchData ODMR entityReportSettingsLength:"+entityReportSettingsLength+"   showColumnsPerBand.size:"+showColumnsPerBand.size());
       
        reportCalcs.setEntity(entityReport,/*entityReportBand,*/entity,entityMany,query,/*sqlHeader,entityHeader,primKey,*/title,entityReportSettingsLength, isDotmatrix, showSummaryPage,pageFormat,
                pageSizeLaser,pageOrientationIsPortraitLaser,entityFilterSettings,intSettingsReport);     	
        
          
       	 lblFromPages.setText(" από: "+getPageTotal());
       	//(String entityIn, String queryIn, String titleIn,boolean[] showColumnsIn,boolean isPrintPreviewIn,boolean isDotmatrixIn, showSummaryPage )

       }
       else if (viewType.equalsIgnoreCase("ODOR"))
       {
       	System.out.println("PanelPrintPreview.fetchData fetch ODOR (not implemented)");
       	reportCalcs.setEntity(entityReport,/*entityReportBand,*/entity,entityMany,query,/*sqlHeader,entityHeader,primKey,*/title,entityReportSettingsLength, isDotmatrix, showSummaryPage,pageFormat,
                pageSizeLaser,pageOrientationIsPortraitLaser,entityFilterSettings,intSettingsReport);
       
        lblFromPages.setText(" από: "+getPageTotal());
       
       }
       else if(viewType.equalsIgnoreCase("FORM")) // etnypo
       {
          
        String q = "";
        if(arrayOfNameOfPksOfRecordToShow!=null)
        {
           for( int p =0;p<arrayOfNameOfPksOfRecordToShow.length;p++)
           {
               q = q+" AND "+arrayOfNameOfPksOfRecordToShow[p]+" LIKE "+arrayOfValueOfPksOfRecordToShow[p];
           }
        }
        else
        {
            System.out.println("  error    PanelPrintPreview.fetchData   -FORM-   arrayOfNameOfPksOfRecordToShow:"+arrayOfNameOfPksOfRecordToShow+" NOT for aspecific record. Perhaps mass printing");
        }
        
        //System.out.println("PanelPrintPreview.fetchData   FORM    1 query:"+query);
          if(utilsString.hasQueryOrderby(query))
   	  {
   	  	String sqlOrderby = utilsString.getOrderbySubQuery(query);
   	        query = utilsString.getQueryWithoutOrderby(query)+q+" "+sqlOrderby;
          }
          else
          {
               query = query+q;
          }
        //System.out.println("PanelPrintPreview.fetchData   FORM    2 query:"+query);
         System.out.println("PanelPrintPreview.fetchData     ---FORM---    dotmatrix:"+isDotmatrix+"   entityReport bands length:"+entityReport.getEntityReportBands().length+"    query:"+query);

               reportCalcs.setEntity(entityReport,entity,entityMany,query,/*sqlHeader,entityHeader,primKey,*/title,entityReportSettingsLength, isDotmatrix, showSummaryPage,pageFormat,
               pageSizeLaser,pageOrientationIsPortraitLaser,entityFilterSettings,intSettingsReport); 
   
 
               lblFromPages.setText(" από: "+getPageTotal());
       }

       	Font monospaced11 = new Font("Monospaced",Font.PLAIN,11);
        reportAreaList.setFont(monospaced11); 	
   }

  
  private void selectIsDotmatrix(boolean isDM)
  {

          	  if (!isDM)
          	  {  
          	  	    //cl.show(panelPrintPreview, "normal");   	 
          	        panelPrintPreview.add(scrollPanel, BorderLayout.CENTER);
          	       // btnPageSetup.setVisible(true);
        
          	  }
          	  else
          	  {	    // cl.show(panelPrintPreview, "dotmatrix");  	 
          	       panelPrintPreview.add(panelPrintPreviewDotmatrix, BorderLayout.CENTER);
          	       //btnPageSetup.setVisible(false);
          	  }
          	  
          	  
   }

   class  ActionPrintPreview extends AbstractAction                 
   {       
        public ActionPrintPreview()
        {      }
      	
    	public void actionPerformed(ActionEvent e)
      	{     
      	  btnPrint.doClick();
      	}    	
    }

   class  ActionShowFilters extends AbstractAction                 
   {       
        public ActionShowFilters()
        {      }
      	
    	public void actionPerformed(ActionEvent e)
      	{     
      	  btnFilters.doClick();
      	}    	
    }
    
class SwingPrintBook implements Pageable
{

//protected StatusDialog statusDialog;
protected ArrayList pageContents;
protected PageFormat pageFormat;
protected boolean wasBuiltForUs;


SwingPrintBook(PageFormat format)
{
    
    pageFormat = format;
    pageContents = new ArrayList();
    //pageContents = pages;
    //System.out.println("PanelPrintPreview.SwingPrintBook "+pageContents.size());
}

SwingPrintBook( PageFormat format, ArrayList pages)
{
    
    pageFormat = format;
    //pageContents = new ArrayList();
    pageContents = pages;
    //System.out.println("PanelPrintPreview.SwingPrintBook "+pageContents.size());
}



public void addPages(ArrayList pages)
{
	
	pageContents = pages;
	/*for(int i = 0;i<pages.size();i++)
	{
		pageContents.add(i);
	}*/
	
}

public int getNumberOfPages()
{
    //System.out.println("SwingPrintBook.getNumberOfPages "+pageContents.size());
    return pageContents.size();
}

public PageFormat getPageFormat(int pageIndex)
{
    return pageFormat;
}



/**
 * Returns the specified swing page. If the page has already been constructed
 * for display, we return that page. If it has not, we create and return
 * a new, temporary page.
 */
@Override
public Printable getPrintable(int pageIndex)
{

   //System.out.println("SwingPrintBook.getPrintable "+pageIndex);
   
    ReportPanelPage contents;

    // "Forget" previous page if it was built 'specially for this print job.
 
    // If user cancelled, tell the rest of the world.


    
    contents=new ReportPanelPage();
    contents.setPageFormat(pageFormat);
    contents = (ReportPanelPage)pageContents.get(pageIndex);
    


            disableDoubleBuffering(contents);// don't forget to reenable<<<<<<<<<<<------------
            Printable page = null;
            page=contents.getPrintable();
            enableDoubleBuffering(contents);

    return page;
}







}




    
    
class SwingFormBook implements Pageable
{

//protected StatusDialog statusDialog;
protected ArrayList pageContents;
protected PageFormat pageFormat;
protected boolean wasBuiltForUs;


SwingFormBook(PageFormat format)
{
    
    pageFormat = format;
    pageContents = new ArrayList();
    //pageContents = pages;
    //System.out.println("PanelPrintPreview.SwingPrintBook "+pageContents.size());
}

SwingFormBook( PageFormat format, ArrayList pages)
{
    
    pageFormat = format;
    //pageContents = new ArrayList();
    pageContents = pages;
    //System.out.println("PanelPrintPreview.SwingPrintBook "+pageContents.size());
}



public void addPages(ArrayList pages)
{
	
	pageContents = pages;
	/*for(int i = 0;i<pages.size();i++)
	{
		pageContents.add(i);
	}*/
	
}

public int getNumberOfPages()
{
    //System.out.println("SwingPrintBook.getNumberOfPages "+pageContents.size());
    return pageContents.size();
}

public PageFormat getPageFormat(int pageIndex)
{
    return pageFormat;
}



/**
 * Returns the specified swing page. If the page has already been constructed
 * for display, we return that page. If it has not, we create and return
 * a new, temporary page.
 */
@Override
public Printable getPrintable(int pageIndex)
{

   //System.out.println("SwingPrintBook.getPrintable "+pageIndex);
   
    ReportAreaForm contents;

    // "Forget" previous page if it was built 'specially for this print job.
 
    // If user cancelled, tell the rest of the world.


    
    contents=new ReportAreaForm();
    contents.setPageFormat(pageFormat);
    contents = (ReportAreaForm)pageContents.get(pageIndex);
    


            disableDoubleBuffering(contents);// don't forget to reenable<<<<<<<<<<<------------
            Printable page = null;
            page=contents.getPrintable();
            enableDoubleBuffering(contents);

    return page;
}



}

/*
*   https://stackoverflow.com/questions/10455268/java-printing-creating-a-pageformat-with-minimum-acceptable-margin
*/

static private PageFormat getMinimumMarginPageFormat(PrinterJob printJob)
{
    PageFormat pf0 = printJob.defaultPage();
    PageFormat pf1 = (PageFormat) pf0.clone();
    Paper p = pf0.getPaper();
    p.setImageableArea(0, 0,pf0.getWidth(), pf0.getHeight());
    pf1.setPaper(p);
    PageFormat pf2 = printJob.validatePage(pf1);
    return pf2;     
}


   // called from button
   private void goPrint()
   {

   	int pageFrom = 1;
   	int pageTo = 1;
   	if(txtPrintPagesFrom.getText().trim().equalsIgnoreCase(""))
        {
            pageFrom=1; // usualy in FORM
        }
        else
        {
            pageFrom =Integer.valueOf(txtPrintPagesFrom.getText());
        }
      
   	if(txtPrintPagesTo.getText().trim().equalsIgnoreCase(""))
        {
            pageTo=1;  // usualy in FORM
        }        
        else
        {
            pageTo =Integer.valueOf(txtPrintPagesTo.getText());
        }
        
        
   	if(pageFrom>pageTo)
   	{
   		utilsGui.showMessageError(this,"Η σελίδα 'από' είναι μεγαλύτερη απο τη σελίδα 'ως'.("+pageFrom+">"+pageTo+")" );
   		return;
   	}
   	
   	if(pageTo>getPageTotal())
   	{
   		utilsGui.showMessageError(this,"Η σελίδα 'ως' είναι μεγαλύτερη απο το πλήθος των σελίδων.("+pageTo+">"+getPageTotal()+")" );
   		return;
   	}
   
        if (isDotmatrix)
        {    
              strPrintDotMatrix="";// else it shows the contents plus the current page
        	  for(int p = pageFrom;p<=pageTo;p++)
              {
                  strPrintDotMatrix=strPrintDotMatrix+buildAndGetDotmatrixPageToPrint(p);
              }
           	
       	 try
       	 {
       	 	
       	      PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(FILE_REPORT_TXT), "Cp737"));
             
             
             int sumLengthOfCode=0;
             String codeCpi="/cpi=";
             int lengthOfCpi = codeCpi.length();
             //String codeCpi10 = "/cpi=10";
             //String codeCpi15 = "/cpi=15";
             int lengthOfPrintCode = 7 ;
             
            ArrayList lstcpi =  utilsString.getIndicesOf(strPrintDotMatrix,codeCpi);
            
            //System.out.println("PanelPrintPreview.goPrint dot matrix start lstcpi.length() "+lstcpi.size());
            // exists also (for different purpose) in ReportPanelPage.generateMain() (to make not visible cpi code)
            if(lstcpi.size()>1)
            {            
              int p=0;
              for(int l = 0;l<lstcpi.size();l++)
              {
              	 int idx = Integer.parseInt(lstcpi.get(l).toString());
              	 String strSize = strPrintDotMatrix.substring(idx+lengthOfCpi,idx+lengthOfPrintCode);
              	
              	 //System.out.println("PanelPrintPreview.goPrint "+l +" idx"+idx+"->"+strSize+"-"+strPrintDotMatrix.substring(idx,idx+lengthOfPrintCode));
              	 int intSize=  Integer.parseInt(strSize);
              	
              	 strPrintDotMatrix = utilsString.replaceStringFromString(idx,idx+lengthOfPrintCode,"       ",strPrintDotMatrix);
              	 //System.out.println("PanelPrintPreview.goPrint "+l+" remove from"+idx+" to"+(idx+lengthOfPrintCode));
                 	
                 	
              	    printWriter.print(strPrintDotMatrix.substring(p,idx));
              	   // System.out.println("PanelPrintPreview.goPrint "+l+" intSize "+intSize);
              	    p = idx;              	 
              	 
              	    if(intSize==10)
              	    {
              	 	
              	 	    printCode10CPI(printWriter);
              	    }
              	    else if(intSize==15)
              	    {
              	 	
              	    	printCode15CPI(printWriter);
              	    }                 	
                 	
              	 
              	 ArrayList lstcpiNew = utilsString.getIndicesOf(strPrintDotMatrix,codeCpi);
              	 if(l<lstcpi.size()-1)
              	 {
              	    lstcpi.set(l+1,Integer.parseInt(lstcpiNew.get(0).toString()));
              	    //System.out.println("PanelPrintPreview.goPrint "+l+" "+lstcpi.get(l+1));
              	 }
              	 
              }           
              
                 //System.out.println("PanelPrintPreview.goPrint from"+(Integer.parseInt(lstcpi.get(lstcpi.size()-1).toString())-sumLengthOfCode)+" to "+(strPrintDotMatrix.length()-sumLengthOfCode)+" total "+strPrintDotMatrix.length());
              
                 printWriter.print(strPrintDotMatrix.substring(Integer.parseInt(lstcpi.get(lstcpi.size()-1).toString()),strPrintDotMatrix.length()));
              }
              else
              {
              	
 	
                if(dotmatrixCpi==10) 
                {
               	    printCode10CPI(printWriter);
                }
                else if(dotmatrixCpi==15)
                {
             	    printCode15CPI(printWriter);
                }
                else if(dotmatrixCpi==-1)
                {
                	// nothing to be set
                }           	
              	
              	printWriter.print(strPrintDotMatrix);
              }
              
              printWriter.close();
       	 } // try io
         catch (UnsupportedEncodingException e)
         {System.out.println("panelPrintPreview.showPage UnsupportedEncodingException "+e);
         }
         catch (IOException e)
         {System.out.println("panelPrintPreview.showPage IOException "+e);
         }
           	  	
           	
          //java encondings Cp737 PC Greek,Cp875 IBM Greek, Cp869 IBM Modern Greek, Cp1253 win greek, ISO8859_7 ISO 8859-7 Latin/Greek alphabet 
        try
        {
           System.out.println("print dot matrix to "+strPortDotmatrix);
           PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(strPortDotmatrix), "Cp737"));//Cp437

                 //System.out.println("ln "+isDotmatrix);
    //  TO DO: change to get from db. Not from file
                 BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_REPORT_TXT), "Cp737"));//fileName), "UTF8"));
                //strPrint = in.read();
         
                 String line ="";
                 //String all="";
                 while((line = in.readLine()) != null)
                 {
                      //System.out.println("ln "+line);
                      printWriter.println(line);
                      //all=all+"\r\n"+line;
                 }
             //printWriter.print("\f");
             printWriter.close();
             
           
         } // try io
         catch (UnsupportedEncodingException e)
         {System.out.println("panelPrintPreview.goPrint UnsupportedEncodingException "+e);
         }
         catch (IOException e)
         {
         
         	if(e.getMessage().contains(strPortDotmatrix))
         	{
         	         	utilsGui.showMessageError(this,"Η θύρα "+strPortDotmatrix+" είτε δεν υπάρχει είτε δεν λειτουργεί.");
         	}
         	else
         	{
         		   utilsGui.showMessageError(e.getMessage());
         	}
         	System.out.println("panelPrintPreview.goPrint IOException "+e);
         }

         }// is dotmatrix
         else   //   is not Dotmatrix
         {
           	

           	 
            
           
        setCursor( Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
           // listPages.clear();
            
          //printerJob = PrinterJob.getPrinterJob();  
            
            
            boolean isShowDialog =true;
            System.out.println("= PanelPrintPreview.goPrint     pageFormat:"+pageFormat);
            //SwingPrintBook book = new SwingPrintBook();
            //int k = pageFrom;
            
         if(viewType.equalsIgnoreCase("FORM"))
         {
           
  
                      
 //           PageFormat pf = panelReportSettings.displayDialogPageSetup();

                    printerJob = PrinterJob.getPrinterJob();
                    pageFormat = getMinimumMarginPageFormat(printerJob);
       //pageFormat = printerJob.defaultPage();
      //pageFormat = printerJob.pageDialog(pageFormat);
         
          //System.out.println(".PanelPrintPreview.goPrint          printerJob:"+printerJob+"           pageFormat:"+pageFormat+"             reportPanelPage:"+reportPanelPage);             

       ///  http://stackoverflow.com/questions/12141155/printing-an-html-file-in-java          may has solution

             
             ArrayList listPagesForm = new ArrayList();
             //panelScrollCenter//.getComponent(1);
             //listPages.add(panelScrollCenter);  getFormLaserToPrint
             ReportAreaForm[] raForm = new ReportAreaForm[getPageTotal()];
             System.out.println("PanelPrintPreview.goPrint FORM totalpages:"+getPageTotal());
             for(int pa=1;pa <= getPageTotal();pa++)
             {
                     showPage(pa);
                     
                    raForm[pa-1] =reportPanelPage.getPanelPageFormLaserToPrint();//pa, queryFormForPrinting,formFieldToGetData);//  getFormLaserToPrint();//  .getPanelPageFormLaserToPrint();
                    //System.out.println(pa);
                    listPagesForm.add((ReportAreaForm)raForm[pa-1]); //1 
             }
             
              SwingFormBook bookForm = new SwingFormBook(pageFormat);
             //System.out.println("PanelPrintPreview.goPrint     listPagesForm.size:"+listPagesForm.size()+" totalpages:"+getPageTotal()+"  raForm:"+raForm+"  raForm[0]:"+raForm[0]);
              bookForm.addPages(listPagesForm);
              printerJob.setPageable(bookForm);

             // System.out.println(".......PanelPrintPreview.goPrint       bookForm.getNumberOfPages:"+bookForm.getNumberOfPages()+"      listPagesForm.size:"+listPagesForm.size()+"      raForm.length:"+raForm.length+"      printerJob:"+printerJob+"      reportPanelPage:"+reportPanelPage);
              
              if(isShowDialog)
              {
                //  showPage(1);// to correct the bug of making not visible(grey) the page
                //  showPage(1);//
              	printPrinterJob(printerJob, true,"FORM");   
              	//System.out.println("PanelPrintPreview.goPrint isShowDialog setted to false")   ;
              	isShowDialog=false;
              }
              else
              {
              	printPrinterJob(printerJob, false,"FORM");      
              }             

          
         }
         else  // not form
         {    
            
             ArrayList listPages = new ArrayList();
             SwingPrintBook book = new SwingPrintBook(pageFormat);
             
            for(int p = pageFrom;p<=pageTo;p=p+10)
            {
                int k =p+10;
               if((k)>=pageTo)
               {
            	   //System.out.println("PanelPrintPreview.goPrint p"+p+" "+pageTo);
            	       //listPages = getListPages(pageFrom, pageTo);
         	    	    //listPages = getListPages(p,pageTo);
                        //SwingPrintBook book = new SwingPrintBook(pageFormat,listPages);
                      for(int s = p;s<=pageTo;s++)
                      {   
                        System.out.println("PanelPrintPreview.goPrint - p"+p+" "+s+" "+pageTo);
                        listPages.add(buildAndGetLaserPage(s, false));  // false: for print
                      }
            	   
               }
               else
               {
               	    //System.out.println("PanelPrintPreview.goPrint p"+p+" "+k);
            	       //listPages = getListPages(pageFrom, pageTo);
         	    	    //listPages = getListPages( p,k);
                        //SwingPrintBook book = new SwingPrintBook(pageFormat,listPages);
                      for(int s = p;s<=k;s++)
                      {   
                         System.out.println("PanelPrintPreview.goPrint p"+p+" "+s+" "+k);
                         listPages.add(buildAndGetLaserPage(s,false));
                      }              	 
               }

           //   System.out.println("PanelPrintPreview.goPrint p"+p+" "+k);

              book.addPages(listPages);
              printerJob.setPageable(book);
              
              
              if(isShowDialog)
              {
              	printPrinterJob(printerJob, true,"");   // is not viewType.equalsIgnoreCase("FORM")
              	System.out.println("PanelPrintPreview.goPrint isShowDialog setted to false")   ;
              	isShowDialog=false;
              }
              else
              {
              	printPrinterJob(printerJob, false,"");      // is not viewType.equalsIgnoreCase("FORM")
              }
    //             listPages.clear(); 
                 //panelScrollCenter.removeAll();
               
               // clear  panelScrollCenter from pages
              /* if((k)>=pageTo)
               {
            	   //System.out.println("PanelPrintPreview.goPrint p"+p+" "+pageTo);
                      for(int s = p;s<=pageTo;s++)
                      {   
                        System.out.println("PanelPrintPreview.goPrint remove page "+s+" s<="+pageTo);
                         panelScrollCenter.remove(s);  
                      }
            	   
               }
               else
               {
               	    //System.out.println("PanelPrintPreview.goPrint p"+p+" "+k);
                      for(int s = p;s<=k;s++)
                      {   
                         System.out.println("PanelPrintPreview.goPrint remove page "+s+" s<="+k);
                         panelScrollCenter.remove(s);  
                      }              	 
               }   */              
                 
                    	 
         	}// for (int p = pageFrom;p<=pageTo;p=p+10)
         }// else



          setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            
         // SwingPrintBook book = new SwingPrintBook(pageFormat,listPages);

        }  
           
     
   }

  private void printPrinterJob(PrinterJob printerJobIn, boolean isShowDialog,String isForm)
  {

    //System.out.println("PanelPrintPreview.printPrinterJob ");


         if(isShowDialog)
         {
              try 
              {
                    if (printerJobIn.printDialog())
                    {
                    	//print
                    	setCursor( Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    	
                    	printerJobIn.print(); 
              	        System.out.println("PanelPrintPreview.printPrinterJob printing.......");
                       if(isForm.equalsIgnoreCase("FORM"))
                       {
                          if(arrayOfNameOfPksOfRecordToShow==null) 
                          {
                               System.out.println("PanelPrintPreview.printPrinterJob MASS printing db NOT SET TO UPDATE");
                          }
                          else
                          {
                          
                           String qWhere = "";
                           for(int a = 0; a<arrayOfNameOfPksOfRecordToShow.length;a++)
                           {
                               String qAnd="";
                               if(a==0)
                               {
                                   qAnd = " ";
                               }
                               else
                               {
                                   qAnd = " AND ";
                               }
                               qWhere = qWhere + qAnd +arrayOfNameOfPksOfRecordToShow[a]+" LIKE '"+arrayOfValueOfPksOfRecordToShow[a]+"'";
                           }
                           
                           
                           String queryUpdate = "UPDATE "+entity+" SET "+entity+"."+STRFIELD_ISPRINTED+" = '1' WHERE "+qWhere;   
                           //System.out.println("PanelPrintPreview.printPrinterJob printing.......queryUpdate:"+queryUpdate); 
                           Database db = new Database();
                           if(db.updateQueryNotTransaction(queryUpdate, "PanelPrintPreview.printPrinterJob", true, false)==1)
                           {
                               System.out.println("PanelPrintPreview.printPrinterJob printing.... db UPDATED");
                               //if is 1 ok
                           }
                           else
                           {
                               System.out.println("PanelPrintPreview.printPrinterJob printing... db NOT UPDATED   queryUpdate:"+queryUpdate);
                           }
                           db.releaseConnectionRs();
                         }
                       }
                        setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        
                        // if job has finished : look for : Determining When a Print Job Has Finished.html
                    }
              }
              catch (PrinterException e)
              {  
                 String error = "PanelPrintPreview.printPrinterJob PrinterException "+e;
              	 System.out.println(error);
                 e.printStackTrace();
              	 utilsGui.showMessageError(error); 
              }
                       	
         }
         else
         {
              try 
              {   	

                    	//print
                    	setCursor( Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    	
                    	printerJobIn.print(); 
              	        System.out.println("PanelPrintPreview.printPrinterJob printing..........");

                        setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        

              }
              catch (PrinterException e)
              {  
                 String error = "PanelPrintPreview.printPrinterJob PrinterException "+e;
              	 System.out.println(error);
                 e.printStackTrace();
              	 utilsGui.showMessageError(error); 
              }         	
         }

  	
  }

    /** The speed and quality of printing suffers dramatically if
     *  any of the containers have double buffering turned on.
     *  So this turns if off globally.
     *  @see enableDoubleBuffering
     */
    private static void disableDoubleBuffering(Component c) {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(false);
    }
 
    /** The speed and quality of printing suffers dramatically if
     *  any of the containers have double buffering turned on.
     * Re-enables double buffering globally.
     * 
     */
    private static void enableDoubleBuffering(Component c) {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(true);
    }

   
    private void displayDialogPageSetup()
    {
       printerJob = PrinterJob.getPrinterJob();
      // pageFormat = printerJob.defaultPage();
       pageFormat = printerJob.pageDialog(pageFormat);
       
      /* if (pageRenderer != null)
       {
         pageRenderer.pageInit(pageFormat);
         showTitle();
       }*/
    }
    
    public void showPageFirst()
    {

       //showPage(1);
       pageCurrent=1;
       comboPage.setSelectedItem(1);
        
    }    
    
    private void showPagePrevious()
    {
    	if(pageCurrent==1)
    	{
    		pageCurrent=1;
    	}
    	else
    	{
    		pageCurrent = pageCurrent-1;
    	}
      //showPage(pageCurrent);
        comboPage.setSelectedItem(pageCurrent);
    }
    
   private void showPageNext()
   {
    	int tp =getPageTotal();
    	if(tp==1)
    	{
    		pageCurrent=1;
    	}
    	else if (tp==pageCurrent)
    	{
    		pageCurrent=tp;
    	}
    	else
    	{
    		pageCurrent = pageCurrent+1;
    	}
    	

      //showPage(pageCurrent);
       
       comboPage.setSelectedItem(pageCurrent);
        
   } 
   
     private void showPageLast()
    {
      pageCurrent=getPageTotal();
      //showPage(getPageTotal());
      comboPage.setSelectedItem(getPageTotal());
      
        
    }
   
     /*
     
     
        called from goPrint
     */
   private String buildAndGetDotmatrixPageToPrint(int p)
   {           

      reportCalcs.calculateRecordsInPage(p);
       
      
   	     	   reportPanelPage = new ReportPanelPage();
    	       reportPanelPage.setEntity(viewType,title,subTitle,/*entityReportBand,*/reportCalcs.getListOfRecords(),query/*queryReport*/,isDotmatrix,strDotMatrixPageSize,p,
    	       reportCalcs.getFromRecInPage(),  reportCalcs.getToRecInPage(), reportCalcs.getListOfListOfColumns(), showColumnsPerBand,  showColumnsHeader, reportCalcs.getListSumPagePre(),
    	       null,null,reportCalcs.getListSumRecCount(), reportCalcs.getListSumsOfPage(), getPageTotal(), reportCalcs.getPageFormat(),showSummaryPage,
    	       arrayStringsToBePrinted,entityFilterSettings,primKey, reportCalcs.getListPrimKeyVal(),dotmatrixCpi,
    	       intSettingsReport);
   
       

   	     return reportPanelPage.getStrPrintDotmatrixToPrint();

   }
   
   /*
   * called from goPrint
   */
   private JPanel buildAndGetLaserPage(int p, boolean isForPreviewOrForPrint)
   {
   	
   	System.out.println("PanelPrintPreview.buildAndGetLaserPageToPrint     show build page "+p);
       if(isForPreviewOrForPrint)  // for preview
       {
        panelScrollCenter.removeAll();
       }
      reportCalcs.calculateRecordsInPage(p);

   	

   	     	   reportPanelPage = new ReportPanelPage();
                   
    	       reportPanelPage.setEntity(viewType,title,subTitle,/*entityReportBand,*/reportCalcs.getListOfRecords(),query/*queryReport*/,isDotmatrix,strDotMatrixPageSize,p,
    	       reportCalcs.getFromRecInPage(),  reportCalcs.getToRecInPage(), reportCalcs.getListOfListOfColumns(), showColumnsPerBand,  showColumnsHeader, reportCalcs.getListSumPagePre(),
    	       null,null,reportCalcs.getListSumRecCount(),reportCalcs.getListSumsOfPage(), getPageTotal(), reportCalcs.getPageFormat(),showSummaryPage,
    	       arrayStringsToBePrinted,entityFilterSettings,primKey, reportCalcs.getListPrimKeyVal(),dotmatrixCpi, intSettingsReport );
   	
    	       // so it can layout the report
    	       //reportPanelPage.generatePageStart();

    	       
                panelScrollCenter.add(reportPanelPage,"page "+p); 
               panelScrollCenter.revalidate();
    	       // return to goPrint
    	       return reportPanelPage;//---check  if it works with reportPanelPage(as it was) or panelScrollCenter;
   }
   
   
   private void getFormDotmatrixToPrint()
   {
   	        strPrintDotMatrix = reportPanelPage.getStrPrintDotmatrixToPrint();
	        setTextReportAreaDotmatrix(strPrintDotMatrix); 
                System.out.println("PanelPrintPreview.buildAndGetDotmatrixPage  FORM  dotmatrix       strPrintDotMatrix:"+strPrintDotMatrix);

   }
   
   
   

   
   /*
   *    called by combo setPage
   */  
   private void showPage(int p)
   {
   	reportPanelPage = new ReportPanelPage();
   	
       if(viewType.equalsIgnoreCase("FORM")) // etnypo
       {

        // perhaps make it like not FORM (look bellow in 'else'). 
       // reportPanelPage = new ReportPanelPage();
        reportPanelPage.setEntityForForm(name,isDotmatrix,"FORM",p,entityReport,query,reportCalcs.getListOfListOfColumns(),reportCalcs.getListOfRecords(), 
              queryFormForPrinting,formFieldToGetData,  arrayOfNameOfPksOfRecordToShow,arrayOfValueOfPksOfRecordToShow);
        //panelScrollCenter.add(reportPanelPage,"page "+p); 
        //panelScrollCenter.add(reportPanelDoc,"page "+p); 
        //System.out.println("PanelPrintPreview.showPage      FORM       p:"+p+"     arrayOfNameOfPksOfRecordToShow:"+arrayOfNameOfPksOfRecordToShow);
   	 if (isDotmatrix)
         {
   	       strPrintDotMatrix = reportPanelPage.getStrPrintDotmatrixToView();
	       setTextReportAreaDotmatrix(strPrintDotMatrix); 
                       
   	 }
         else
         {
             
             
             ArrayList listPagesForm = new ArrayList();
             //panelScrollCenter//.getComponent(1);
             //listPages.add(panelScrollCenter);  getFormLaserToPrint
             ReportAreaForm raForm = null;
             
             /*for(int pa=1;pa <= getPageTotal();pa++)
             {
                    System.out.println("PanelPrintPreview.showPage     p:"+p+" pa:"+pa+" getPageTotal:"+getPageTotal());
                    raForm =reportPanelPage.getPanelPageFormLaserToShow(pa, queryFormForPrinting,formFieldToGetData);//  getFormLaserToPrint();//  .getPanelPageFormLaserToPrint();
                    listPagesForm.add(raForm); //1
                 
             
              
             } */ 
             System.out.println("PanelPrintPreview.showPage     formFieldToGetData:"+formFieldToGetData+"    queryFormForPrinting:"+queryFormForPrinting);
             raForm =reportPanelPage.getPanelPageFormLaserToShow();//p, queryFormForPrinting,formFieldToGetData);//  getFormLaserToPrint();//  .getPanelPageFormLaserToPrint();
             
             
             
         //    JPanel[] panelToShow = reportPanelPage.getPanelPageFormLaserToShow(p,queryFormForPrinting,formFieldToGetData);
             //System.out.println("PanelPrintPreview.showPage     panel:"+panel);
       
             if(raForm==null)//System.out.println("PanelPrintPreview.showPage      panel:"+panel);
             {
                
                  showPanelNoDataMessage();
             }
             else
             {
               
                  showPanelFormPrintPreview(raForm,p);

             }
         }
          lblPage.setText("φόρμα:");
          lblPageFrom.setText(" φόρμες");  

       }
       else // not FORM
       {
       pageCurrent=p;
      // System.out.println("PanelPrintPreview.showPage show page");

     
       reportCalcs.calculateRecordsInPage(p);

       //System.out.println("PanelPrintPreview.showPage "+arrayStringsToBePrinted);
    	       reportPanelPage.setEntity(viewType,title,subTitle,/*entityReportBand,*/reportCalcs.getListOfRecords(),query/*queryReport*/,isDotmatrix,strDotMatrixPageSize,p,
    	       reportCalcs.getFromRecInPage(),  reportCalcs.getToRecInPage(), reportCalcs.getListOfListOfColumns(), showColumnsPerBand,  showColumnsHeader, reportCalcs.getListSumPagePre(),
    	       null,null,reportCalcs.getListSumRecCount(), reportCalcs.getListSumsOfPage(), getPageTotal(), reportCalcs.getPageFormat(),showSummaryPage,
    	       arrayStringsToBePrinted,entityFilterSettings,primKey, reportCalcs.getListPrimKeyVal(),dotmatrixCpi,intSettingsReport );
    	       
    	       panelScrollCenter.add(reportPanelPage,"page "+p); 

    	CardLayout cl = (CardLayout)(panelScrollCenter.getLayout());
        cl.show(panelScrollCenter, "page "+p);
        //System.out.println(" ============ PanelPrintPreview.showPage         p:"+p+"         viewType:"+viewType) ;  
                
    	//panelScrollCenter.add(reportCalcs.getPage(),BorderLayout.LINE_START);
    	//strPrintDotMatrix = reportCalcs.getStrPrintDotmatrix();
    	setTextReportAreaDotmatrix(strPrintDotMatrix);
    	lblPage.setText("σελίδα:");
    	lblPageFrom.setText("σελίδες:");
    	lblFromPages.setText(" από: "+getPageTotal());	
   	    
   	   if (isDotmatrix)
           {
   	      strPrintDotMatrix = reportPanelPage.getStrPrintDotmatrixToView();
	      setTextReportAreaDotmatrix(strPrintDotMatrix); 
   	   }

   	  //  reportPanelPage.setPageDimensions();
       } // not form

        
   	     if(p==1)
   	     {
   	     	if(p==getPageTotal())
   	     	{
   	     	btnFirst.setEnabled(false);   	     	
   	     	btnPrevious.setEnabled(false);
   	     	btnNext.setEnabled(false);
   	     	btnLast.setEnabled(false);     	     		
   	     	}
   	     	else
   	     	{
   	     	btnFirst.setEnabled(false);   	     	
   	     	btnPrevious.setEnabled(false);
   	     	btnNext.setEnabled(true);
   	     	btnLast.setEnabled(true);   	     		
   	     	}     		
   	     }
   	     else if(p==getPageTotal())
   	     {
   	     	btnFirst.setEnabled(true);   	     	
   	     	btnPrevious.setEnabled(true);
   	     	btnNext.setEnabled(false);
   	     	btnLast.setEnabled(false);   	     	
   	     }
   	     else
   	     {
   	     	btnFirst.setEnabled(true);   	     	
   	     	btnPrevious.setEnabled(true);
   	     	btnNext.setEnabled(true);
   	     	btnLast.setEnabled(true);    	     	
   	     }    

   	
   }
   
   private void setTextReportAreaDotmatrix(String text)
   {
   	
   	listModel.clear(); // is used when someone shows print preview for second or more times
   	
     if(text!=null && !text.equals(""))
     {
       // System.out.println("PanelPrintPreview.setTextReportAreaDotmatrix     lllllll       length"+ text.length());
   	  int intFromChar = 0;
   	  int intLine=0;


   	  int idxChgPg=0;
   	  int idxChgLn=0;
   	  for(int c =0 ;c<text.length();c++)
   	  {
   	  	 //  if found character change line
   	  	 int idxChgLnNew =0;
   	  	 idxChgLnNew = text.indexOf(TXT_CHANGELINE, c);
   	  	 if(idxChgLnNew!=-1)//System.out.println("PanelPrintPreview.setTextReportAreaDotmatrix idxChgLnNew "+idxChgLnNew);
   	  	 {
   	  	     if (idxChgLn!=idxChgLnNew)
   	  	     {
   	  	 	   //System.out.println("PanelPrintPreview.setTextReportAreaDotmatrix line "+intLine+" "+intFromChar+"-"+c+","+idxChgLnNew);
   	  	 	   listModel.insertElementAt((Object)text.substring(c,idxChgLnNew),intLine);
   	  	 	   //intFromChar = c;
   	  	 	   intLine++;
   	  	 	   idxChgLn=idxChgLnNew;
   	  	 	   c=idxChgLn;
   	  	     }
   	  	 }
   	  	 else  // == -1 -> last line
   	  	 { 
   	  	 	//  if found character change page
   	  	   int idxChgPgNew =0;
   	  	   idxChgPgNew = text.indexOf(TXT_CHANGEPAGE, c);   	  	 		
   	  	   if(idxChgPgNew!=-1)//System.out.println("PanelPrintPreview.setTextReportAreaDotmatrix idxChgLnNew "+idxChgLnNew);
   	  	   {
   	  	     if (idxChgPg!=idxChgPgNew)
   	  	     {
   	  	 	   //System.out.println("PanelPrintPreview.setTextReportAreaDotmatrix line "+intLine+" "+intFromChar+"-"+c+","+idxChgLnNew);
   	  	 	   listModel.insertElementAt((Object)text.substring(c,text.length()),intLine);
   	  	 	   //intFromChar = c;
   	  	 	   intLine++;
   	  	 	   idxChgPg=idxChgPgNew;
   	  	 	   c=idxChgPg;
   	  	     }
   	  	   }
   	  	 }
   	  }
     //listModel.addElement("zero one");
     //listModel.addElement("zero two");
     }
     else
     {
                  
         // System.out.println("PanelPrintPreview.setTextReportAreaDotmatrix          NULL or empty");                  
                  
     }     

   	
   }
   
   
    private void printCode10CPI(PrintWriter pstream)
    { //10 characters per inch (condensed available)
        System.out.println("PanelPrintPreview.printCode 10 CPI");
         pstream.print(DC2); // pro printer
         //pstream.print(RS); // oki
        //pstream.print(ESC); // epson
        //pstream.print(P);   // epson     	
       
    }
   
    public void printCode15CPI(PrintWriter pstream) { //15 characters per inch (condensend not available)
      System.out.println("PanelPrintPreview.printCode 15 CPI");
        pstream.print(ESC);
        pstream.print(g);
  
    }    
   
   
   public boolean isPageWidthBiggerThanPaper()
   {
	  return reportPanelPage.isPageWidthBiggerThanPaper();
   }

   public int getRowCount()
   {
   	  return reportCalcs.getRowCountTotal();
   }

   private void closeDB()
   {
   	
   	  reportCalcs.closeDb();
   	
   }
   
   public void closePanel()
   {
       	 closeDB();
   }
   
   private int getPageTotal()
   {
   	  // reportPanel.getPageTotal();
   	  int pages = 0;
   	  
   	  if(viewType.equalsIgnoreCase("FORM"))
   	  {
              if(arrayOfNameOfPksOfRecordToShow!=null)
              {
   	           double dbRecords = reportCalcs.getRowCountTotal();
                   pages = (int)(Math.ceil(dbRecords / NUMBER_OF_FORMROWS_PER_PAGE)); //a value has to be double in order ceil to work. 
              }
              else
              { //exists with modifications in PanelPrintPreniew.getPageTotal and in  ReportAreaForm.setEntity
                   //entityReport.getQuery()

                ArrayList listRecords =  reportCalcs.getListOfRecords();
              ArrayList listOfListOfColumns = reportCalcs.getListOfListOfColumns();
           
              String lastVal = "";
           String previousVal = ""; // setEntity
           //  int countOfPages =1;
           int changesOfHeaderRecord = 0;
            
            for(int r =0 ;r<listRecords.size();r++)
            {                      
                EntityReportRow entityReportRow = (EntityReportRow)listRecords.get(r);
                 Object[] arrayRecValues = entityReportRow.getFieldValues();
                    
           RecColumn[] arrayRecColumn = (RecColumn[])listOfListOfColumns.get(entityReportRow.getIntOfEntityReportBand());

           //System.out.println(" =====    PanelPrintPreview.getPageTotal   r:"+r+"   int of band:"+entityReportRow.getIntOfEntityReportBand()+"      "+columnTable+"."+columnName+"  = "+val+"     previousVal:"+previousVal+"         listRecords.size()"+listRecords.size()+"    countOfPages:"+countOfPages);
             
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
                     groupByField=erb.getTableName()+"."+groupByField;
                     if(groupByField.equalsIgnoreCase(columnTable+"."+columnName) )  //&& val.equalsIgnoreCase(pageIn+""))
                     {

                        if(lastVal.equalsIgnoreCase(val))
                        {           
                            // countOfPages++;//is OK
                           
                        }
                        else
                        {
                          // countOfPages=1;
                            lastVal = val; 
                            changesOfHeaderRecord++;
                           
                        }                       

                         //System.out.println(" ===p===    PanelPrintPreview.getPageTotal   r:"+r+"   c:"+c+"        "+columnTable+"."+columnName+"  = "+val+"  previousVal:"+previousVal+" group by field:"+groupByField+"   listRecords.size()"+listRecords.size()+"      lastVal:"+lastVal+"       countOfPages:"+countOfPages+"   changesOfHeaderRecord:"+changesOfHeaderRecord);
                     }

                  //System.out.println(" ===== ELSE   ReportAreaForm.setEntity  r:"+r+"  band no:"+entityReportRow.getRecordIntNoOfBand()+"    "+columnTable+"  "+columnName+"  :"+val+"    group by field:"+ erb.getSqlGroupByField());
          }
                  pages=changesOfHeaderRecord;//countOfPages;

            }

                 
                  
             } // arrayOfNameOfPksOfRecordToShow == null
             
          //System.out.println("getPageTotal  pages:"+pages+"  ");
            
   	  }
   	  else
   	  { 	  
   	     //pages=reportCalcs.getPageTotal();
              pages=reportCalcs.getPageTotal();
   	  }
   	  
   	  return pages;
   }
 
   // this allows outside classes to add actionlisteners
    public void addActionListener(ActionListener al)
    {
   
      btnFilters.addActionListener(al);
    }
   
class ToolBarData extends JToolBar implements Constants
{
        
        public ToolBarData()
        {
            try
           {     initialize();   }
           catch (Exception e)
           {   e.printStackTrace();    }
        }


        private void initialize() throws Exception
        {
             BevelBorder borderRaised = new BevelBorder(javax.swing.border.BevelBorder.RAISED);
            this.setBorder(borderRaised);        
        this.setFocusable(false);
        //this.setFloatable(false);	
        //this.setOpaque(false);
        
        btnFilters = new JButton();
         btnFilters.setText("<html>φίλτρα <b>F2</b></html>");
        btnFilters.setOpaque(false);
        //btnFilters.setText("<html>προεπισκόπηση <b>O</b></html>");	    
       // btnFilters.setText("<html>προεπισκόπηση <b>alt+O</b></html>");
        //btnFilters.setToolTipText("προεπισκόπηση εκτύπωσης");
        btnFilters.setIcon(ICO_PANELBACK);
        //btnFilters.setMnemonic(KeyEvent.VK_O);
        btnFilters.setFocusable(false);

        Action actionShowFilters = new ActionShowFilters();
        btnFilters.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F2"), "showFilters"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnFilters.getActionMap().put("showFilters", actionShowFilters);

        	
       btnFirst = new JButton();
       btnFirst.setIcon(ICO_FIRST16);
       btnFirst.setText("πρώτη");
       btnFirst.setOpaque(false);
       btnFirst.addActionListener(new ActionListener()
       {       	
          public void actionPerformed(ActionEvent e)
          {
          	showPageFirst();
          }
       });       
       
       btnPrevious = new JButton();
       btnPrevious.setIcon(ICO_PREVIOUS16);
       btnPrevious.setText("προηγούμενη");
       btnPrevious.setOpaque(false);
       btnPrevious.addActionListener(new ActionListener()
       {       	
          public void actionPerformed(ActionEvent e)
          {
          	showPagePrevious();
          }
       });       

       btnNext = new JButton();
       btnNext.setIcon(ICO_NEXT16);
       btnNext.setOpaque(false);
       btnNext.setText("επόμενη");
       btnNext.addActionListener(new ActionListener()
       {       	
          public void actionPerformed(ActionEvent e)
          {
          	showPageNext();
          }
       }); 
       
       
       btnLast = new JButton();
       btnLast.setIcon(ICO_LAST16);
       btnLast.setOpaque(false);
       btnLast.setText("τελευταία");
       btnLast.addActionListener(new ActionListener()
       {       	
          public void actionPerformed(ActionEvent e)
          {
          	showPageLast();
          }
       });         
       
      /* int[] arrPages = new int[getPageTotal()];
       for(int p =0; p<getPageTotal();p++ )
       {
       	  arrPages[p]= p+1;
       }*/
       comboPage = new JComboBox();
       comboPage.addActionListener(new ActionListener()
       {
          public void actionPerformed(ActionEvent e)
          {

                 JComboBox cb = (JComboBox)e.getSource();
                 int pg = 1;
                 if(cb.getSelectedItem()!=null) // if not have it error is created when clearing items
                 {
                     pg = Integer.valueOf(cb.getSelectedItem().toString());	
                 }
                 
                    showPage(pg);	

                 
          }
        });



                 
       lblPage = new JLabel("σελίδα:");
       lblFromPages = new JLabel("pages");
       
       
        lblPageFrom= new JLabel("σελίδες:"); 
        txtPrintPagesFrom = new JTextField(2);
        txtPrintPagesFrom.setDocument(new PlainDocumentInsertText(10,"java.lang.Integer"));//limiting the capacity of txt

        lblPageTo= new JLabel("ως"); 
        txtPrintPagesTo = new JTextField(2);
        txtPrintPagesTo.setDocument(new PlainDocumentInsertText(10,"java.lang.Integer"));//limiting the capacity of txt
        
        //btnPageSetup = new JButton("<html><b>Ρ</b>υθμίσεις</html>");
        //btnPageSetup = new JButton("<html>ρυθμίσεις <b>Ρ</b></html>");
        //btnPageSetup = new JButton("<html><b>alt+Ρ</b></html>");
        //btnPageSetup.setToolTipText("ρυθμίσεις σελίδας");
        //btnPageSetup.setIcon(ICO_PAGEPROPERTIES16);
        /*btnPageSetup.setMnemonic(KeyEvent.VK_R);
        btnPageSetup.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e) 
	       {   
	            displayDialogPageSetup();
	       }
	    });       */ 

        //btnPrint = new JButton("<html><b>alt+E</b></html>");
        btnPrint = new JButton("<html>Εκτύπωση <b>F7</b></html>");
        btnPrint.setIcon(ICO_PRINT16);
        btnPrint.setOpaque(false);
        btnPrint.setMnemonic(KeyEvent.VK_E);
        btnPrint.setToolTipText("εκτύπωση");
        btnPrint.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e) 
	       {    goPrint();      }
	    });
        Action actionPrintPreview = new ActionPrintPreview();
        btnPrint.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), "report"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnPrint.getActionMap().put("report", actionPrintPreview);

        
        //IconSeparator icoSeparator = new IconSeparator();
        
      this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
       

        //addSeparator();
        add(btnFilters);  
       // addSeparator();
        add(btnFirst);
        add(btnPrevious);
        add(btnNext);
        add(btnLast);
       // addSeparator();
        add(lblPage);
        add(comboPage);
        add(lblFromPages);
        //addSeparator();
        //add(btnPageSetup);
        add(lblPageFrom);
        add(txtPrintPagesFrom);
        add(lblPageTo);
        add(txtPrintPagesTo);
        add(btnPrint);
        //addSeparator();
        //
        //addSeparator();
        }
        
        @Override
        protected void paintComponent(Graphics g)
        {
             /*Graphics2D g2 = (Graphics2D) g;   //                     15
             GradientPaint paint = new GradientPaint(0, 0, this.getBackground().brighter(), 0, 30, this.getBackground().darker(),true);
             g2.setPaint(paint);
             g2.fill(getBounds());*/
             super.paintComponent(g);
        }
        
        
  }
}
