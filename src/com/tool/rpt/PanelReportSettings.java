

package com.tool.rpt;

//import com.tool.rpt.PanelReportFormDesign;
//import com.tool.rpt.PanelReportFormData;
import com.tool.model.EntityGroupOfComps;
import com.tool.model.EntityFilterSettings;
import com.tool.gui.*;
import com.tool.guicomps.Constants;
import com.tool.guicomps.JButtonForPanelDecorated;
import com.tool.guicomps.JPanelDecorated;
import com.tool.guicomps.JTextBoxWithEditButtons;
import com.tool.guicomps.JxPanel;
import com.tool.guicomps.JxTabbedPane;
import com.tool.guicomps.PanelDataFilter;
import com.tool.guicomps.PanelDataFilterSettings;
import com.tool.guicomps.PanelDataViewNOrder;
import com.tool.guicomps.PanelExportToFileSettings;
import com.tool.guicomps.PanelManagement;
import com.tool.guicomps.RecColumn;
import com.tool.guicomps.WindowLookUpMultipleCheck;
import com.tool.jdbc.*;
import com.tool.utils.*;
import com.tool.model.*;

import java.awt.*;
import javax.swing.*;
//import java.awt.event.*;
//import java.awt.image.*;
//import java.util.*;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.print.Printable;
import java.awt.Insets;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.print.Paper;

//import javax.swing.*;

import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import javax.swing.text.JTextComponent;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
//import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;
import javax.swing.JToolBar;
import javax.swing.JRadioButton;
import javax.swing.InputMap;
import javax.swing.ButtonGroup;
//import javax.swing.border.*;
//import javax.swing.event.*;

    import java.util.Vector;
import java.util.ArrayList;

import java.io.*;
import javax.swing.border.BevelBorder;


 public class PanelReportSettings extends JPanel implements Constants
 {
   //private JxPanel panelReportFilters;
   private JxPanel panelPrinter;
   //private JxPanel panelReportView;
   //private JxPanel panelReportOrder;
   private JxPanel panelReportMisc;
   private JxPanel panelReportVisualMisc1;
   private JxPanel panelReportVisualMisc2;
   private JxPanel panelReportPrinterSettings;
   
   private JPanelDecorated PanelTabFilters;
   //private JPanel panelTabViewAndOrder;
   private JPanelDecorated panelTabFilterSettings;
   private JPanelDecorated panelTabVisualSet;
   
   private JxPanel panelIntoTabFilters;
   //private JPanel panelIntoTabViewAndOrder;
   private JxPanel panelIntoTabFilterSettings;
   private JxPanel panelIntoTabVisualSet;
   //private TitledBorder borderTitle;
   private JxTabbedPane tabbedPane; //up position, one tab for all + groups
   // private JxxTabbedPane tabPaneGroup;// middle position, add groups only
   
   private JxPanel panelFilterSetting;
   //private JxPanel panelReportSets;
   private JxPanel panelIncludeReportSets;
   private JScrollPane scrollPaneTabFilters;
   private JScrollPane scrollPaneTabVisualSet;
//   private JLabel lblFilterSetting1;  // for lookup fromto

   //private JTableDec table;
   //private JScrollPane tableScrollPane;
   //private TableModelResultSetCheckBoxes tableModel;
   //private JxPanel panelTable;
   
   private JScrollPane scrollPaneTabViewAndOrder;
   //private JPanel panelView;
   //private JLabel lblView;
   //private JCheckBox chkView;

   private JScrollPane scrollPaneTabFilterSettings;
   //private JPanel panelOrder;
   //private DefaultComboBoxModel mdlColumnCombo;
  // private JComboBox cmbColumn; 
   
   /*private JLabel lblOrder;
   private JComboBox cmbOrder;
   private DefaultComboBoxModel mdlOrder;*/
   
   private String strPrintingTypeLaser = "inkjet/laser (graphic)";
   private String strPrintingTypeDotMatrix = "dot matrix (draft)";
   private String strPrintingTypeFile = "export in a file";
   //private String strPrintingTypePDF = "PDF";
   //private String strPrintingTypeExcel = "MS Excel / OO Calc";
   //private String strPrintingTypeHTML = "HTML";

   private JRadioButton radioLaser ;
   private JRadioButton radioDotMatrix;
   private JRadioButton radioFile ;
          
   private String selectedPrintingType;
       
   private JxPanel cards;
   private JLabel lblPage;
   //private JComboBox cmbPrintingTypeDevice;
   
   //private JComboBox cmbPageOrient;
   //private JRadioButton radioOrientation;// = new JRadioButton(catString);
   //private String orientationLandscape="οριζόντιος";
   //private String orientationPortrait="κατακόρυφος";
   private JRadioButton radioPortrait;
   private JRadioButton radioLandscape;
   
   private JComboBox cmbPageSize;
   /*private String SizeA5="A5";
   private String SizeA4="A4";
   private String SizeB4="B4";
   private String SizeA3="A3";*/
   private String strPageSize=STRPAGESIZE_A4;   //default
   
   private JButtonForPanelDecorated btnPageSetup;
   
   private boolean isDotmatrix = false;
   
   private JComboBox cmbPageSizeDotmatrix;
   private String strPageSizeDotmatrix="A4";  //default A4
   private String SizeA4Dotmatrix="A4";
   private String SizeA3Dotmatrix="A3";
   
   private JComboBox cmbPortDotmatrix;
   private String strPortDotmatrix="LPT1";    // default LPT1
   private String portLPT1="LPT1";
   private String portLPT2="LPT2";
   
   private JComboBox cmbCpiDotmatrix;
   private int dotmatrixCpi=-1;    
   private String cpiNoStr="not set";
   private int cpiNo=-1;
   private int cpi10=10;
   private int cpi15=15;
   
   private JPanel panelBottom;
  // private JButton btnPrint;
   //private JButton btnPrintPreview;
   private JButton btnClose;
   
  
   //private String entityMany;
   private String viewType;
   //private String primKey;
   //private String primKeyValue;
   private Vector dataVector;
   //private EntityQuery[] entityQuery;
   //private String query;
   //private String queryMany;
   private String title;
   private String subTitle;
   private boolean showSummaryPage;
   private EntityReportBand[] entityReportBand;
   
   private EntityFilterSettings[] entityFilterSettings;  
   	
   	private JTextBoxWithEditButtons txtBtnDirSave;
    private PanelDataViewNOrder panelDataViewNOrder;

    

    
   private Database db; 
   //private ResultSetMetaData rsmd;
   private UtilsDate utilsDate;
//   private LookUp lookUp;
   private UtilsPanelReport utilsPanelReport;
   //private UtilsTable utilsTable;
   
   //private int columns;
   private int columnsHeader;
   private boolean isPrintPreview;

   //private ArrayList fieldTxtsLookUpId2 = new ArrayList(); // to hold tb
   //private ArrayList fieldTxtsLookUpText2 = new ArrayList(); // to hold tb2
   
  
    private PrinterJob printerJob;
    private PageFormat pageFormat;
   
  //private String [] sql2WhereField;
  //private String[] sql2WhereValue;
  //private boolean[] showLines ;
  private JCheckBox chkShowHorizontalLines;
  private JCheckBox chkShowVerticalLines;
  private JCheckBox chkShowZero;
  //private JCheckBox chkShowDoubleHeightRow;
  //private JCheckBox chkShowColumnWithoutData;
  

   private int [] isFieldSelected;
   private boolean[] isFieldEditable;
   
    private String sqlForPrintPreview="";
    
    
  //   private JxFormattedTextFieldDate.FormatSpec formatSpec;
  //   private JxFormattedTextFieldDate textFormatedDate;
    
/*   private JxFormattedTextFieldDate.FormatSpec formatSpec2;
   private JxFormattedTextFieldDate textFormatedDate2;  */  
    
    private WindowLookUpMultipleCheck winLookUpCheck;
    private String entityHeader;
    private JTextField txtTitleToPrint;
    private JTextField txtSubTitleToPrint;
    
    private FlowLayout flowLayoutLeft2;
    String filePrefs;
    //private JxFormattedTextFieldDate textFormatedPrintingDate;
    
    private UtilsString uString;
    
 		private ToolBarData toolBarData;
 			private JButton btnPrintPreview;
 			//private JButton btnFormDesign;
 			//private JMenuItem  mItemFormParametersData;
    		//private JMenuItem  mItemFormParametersDesign ; 
    		//private JButton btnNewPrintDesign;
    		//private JButton btnPrintDesignSaveAs; 
    		private JButton btnPrintDesignSave; 
    		//private JButton btnPrintDesignDel;
    		//private JComboBox cmbPrintDesign;
    	//	private JButtonListMenu btnExport;
    		//private JPopupMenu popupMenuFormParameters = new JPopupMenu(); // the only from toolbar

   	//		private JPopupMenu popupMenuExport = new JPopupMenu(); // the only from toolbar
   			private JPopupMenu popupMenuFormParameters  = new JPopupMenu();

    
    private PanelDataFilter panelDataFilter;
   //private PanelDataViewNOrder panelDataViewNOrder;
   //private PanelDataViewNOrder panelDataViewNOrderHeader;
   private PanelDataFilterSettings panelDataFilterSettings;
   
  // private  String  sqlHeaderForPrintPreview;
   private int[] showColumns;
   private int[] showColumnsHeader;
  // private int[] fieldsOrderby;
   
   //private boolean isMany = false;
   private boolean hasGroups= false;
   private JFrame parent;
   private ArrayList listPanelDataViewNOrder;
   private PanelExportToFileSettings panelExportToFileSettings;
   private EntityExportFileType[] entityExportFileType;
   
   JTextBoxWithEditButtons txtBtnDate;
   

      boolean[] boolSettingsReport;
      int[] intSettingsReport;
      
   private JLabel lblCpi;
   private UtilsFileSystem utilsFileSystem;
   private UtilsGui utilsGui;
   //private PanelCollapsable panelCollapsable;
        
        JComboBox  cmbPrintingDate; 
         JComboBox cmbPrintCompanyTitle;
         //JComboBox cmbPrintCompanyInfo ;   
        JComboBox cmbPrintFilterInfo ; 
        
   private JComboBox  cmbBandAllignment; 
   private JComboBox  cmbBandBackColor; 
            	 JCheckBox chkBoxReportParameter;	 

     	       JCheckBox chkBoxReportParameter2;
     	       JCheckBox chkBoxReportParameter3 ;
      	 	
      	       JCheckBox chkBoxReportParameter4;  	
         
  //private String yearEnforce;
   private int entityReportBandLength=0;
   private PanelManagement panelManagement;
   
   private ArrayList  listChkShowHorizontalLines;
   private ArrayList listChkShowVerticalLines;
   private ArrayList listChkShowZero;
  //private ArrayList listChkShowColumnWithoutData;   
   private EntityReport entityReport;
   
    public PanelReportSettings(JFrame parent)
    {
        try {
            initialize(parent);
        } catch (Exception e) {
           System.out.println("PanelReportSettings.init "+e.getMessage());// e.printStackTrace();
        }
    }

   // fromto -> while is 1 object the 'to' it is layed out in different column because the object is lengthy
	private void initialize(JFrame parentIn)throws Exception
    {
    	parent = parentIn;
       db = new Database();
       utilsDate = new UtilsDate();
       utilsFileSystem=new UtilsFileSystem();
       utilsPanelReport = new UtilsPanelReport();
       utilsGui= new UtilsGui();
       uString = new UtilsString();
       
     listChkShowHorizontalLines = new ArrayList();    
     listChkShowVerticalLines = new ArrayList();
     listChkShowZero = new ArrayList();  
     //listChkShowColumnWithoutData = new ArrayList();  
     
     
     
      txtBtnDate = new JTextBoxWithEditButtons();
      
        radioLandscape = new JRadioButton(ORIENTATION_LANDSCAPE);
        radioLandscape.addActionListener(new ActionListener()
       {       	
          public void actionPerformed(ActionEvent e)
          {
          	boolean isPortrait = false;
          	setSelectedPageOrientation();
          }
       });     
        
        radioPortrait = new JRadioButton(ORIENTATION_PORTRAIT);
        radioPortrait.addActionListener(new ActionListener()
       {       	
          public void actionPerformed(ActionEvent e)
          {
          	boolean isPortrait = true;
          	setSelectedPageOrientation( );
          }
       });     
       	
       	radioPortrait.setSelected(true);
      
      ButtonGroup groupOrientation = new ButtonGroup();
      groupOrientation.add(radioPortrait);
       groupOrientation.add(radioLandscape);



       filePrefs = VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+FILE_REPORTPREFERENCES;
       panelExportToFileSettings = new PanelExportToFileSettings(parent);
       
       panelDataFilter = new PanelDataFilter(parent);
       
       //tabPaneGroup = new JxxTabbedPane();
       
       

       
       flowLayoutLeft2 = new FlowLayout();
       flowLayoutLeft2.setVgap(2);
       flowLayoutLeft2.setHgap(2);
       flowLayoutLeft2.setAlignment(FlowLayout.LEFT);

       
       this.setLayout(new BorderLayout());
       
       PrinterJob printerJob = PrinterJob.getPrinterJob();
       pageFormat = printerJob.defaultPage();
       
/*       panelReportFilters = new JxPanel();
       panelReportFilters.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 4));
       //panelReportFilters.setLayout(new GridLayout(0,2));//new BoxLayout(panelReportFilters, BoxLayout.PAGE_AXIS));
       panelReportFilters.setBorder(new TitledBorder("φίλτρα εγγραφών"));*/
       


       panelPrinter = new JxPanel(); // panel of printing in laser dot matrix
       panelPrinter.setLayout(new FlowLayout());   
      panelPrinter.setBorder( new TitledBorder(BorderFactory.createTitledBorder(null, "printer",TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null,CLR_PANEL_BORDER)));                                

       

       panelReportMisc = new JxPanel();
       panelReportMisc.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS,2));//(new BorderLayout());//new BoxLayout(panelReportMisc, BoxLayout.PAGE_AXIS));
      panelReportMisc.setBorder( new TitledBorder(BorderFactory.createTitledBorder(null, "προτιμήσεις εκτύπωσης",TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null,CLR_PANEL_BORDER)));                                       

       
           

        
        JLabel lblTitleToPrint = new JLabel("title");
        txtTitleToPrint = new JTextField(38);
        final JTextField textfldTitleFinal = txtTitleToPrint;
         txtTitleToPrint.addFocusListener(new FocusListener()
         {
                   	 public void focusLost(FocusEvent e)
                     {                      
                     
                     }
                     public void focusGained(FocusEvent e)
                     { 
                           textfldTitleFinal.setSelectionStart(0);
                           textfldTitleFinal.setSelectionEnd(textfldTitleFinal.getText().length());
                     }
         });        
        
        JxPanel panelTitleToPrint = new JxPanel();
        panelTitleToPrint.setLayout(flowLayoutLeft2);
        panelTitleToPrint.add(lblTitleToPrint);
        panelTitleToPrint.add(txtTitleToPrint);

        
        JLabel lblSubTitleToPrint = new JLabel("subtitle");
        txtSubTitleToPrint = new JTextField(38);
        final JTextField textfldSubTitleFinal = txtSubTitleToPrint;
         txtSubTitleToPrint.addFocusListener(new FocusListener()
         {
                   	 public void focusLost(FocusEvent e)
                     {                      
                     
                     }
                     public void focusGained(FocusEvent e)
                     { 
                           textfldSubTitleFinal.setSelectionStart(0);
                           textfldSubTitleFinal.setSelectionEnd(textfldSubTitleFinal.getText().length());
                     }
         });        
        JxPanel panelSubTitleToPrint = new JxPanel();
        panelSubTitleToPrint.setLayout(flowLayoutLeft2);
        panelSubTitleToPrint.add(lblSubTitleToPrint);
        panelSubTitleToPrint.add(txtSubTitleToPrint);


        //JTextField  txtPrintingDate = new JTextField(10); 		

       //JTextBoxWithEditButtons txtBtnDateFinal = new JTextBoxWithEditButtons();   
        int intColumnWidthDate = 8;//utilsDate.getDateFormatEditing().length();  
//         textFormatedPrintingDate = new JxFormattedTextFieldDate(intColumnWidthDate,formatSpec);


        //txtBtnDate = new JTextBoxWithEditButtons(textFormatedPrintingDate, true,ICO_CALENDAR,null , false,null,null,LOOKUP_TYPE_DATE, parent,"",MONTH_DATE_ONLY);                  	
        
                   JFormattedTextField textFormattedDate = new JFormattedTextField();
                    //JTextBoxWithEditButtons(textFormatedDate, true,ICO_CALENDAR,null , false,null,null,LOOKUP_TYPE_DATE, parent,"",MONTH_DATE_ONLY);                    	
                     txtBtnDate = new JTextBoxWithEditButtons(textFormattedDate, true,ICO_CALENDAR,null,false,null,null,LOOKUP_TYPE_DATE, parent,/*yearEnforce*/"","",MONTH_DATE_ONLY);  
        
        
         String[] comboInfo ={"show in all pages","show in 1st page only","do not show"};
       
       JLabel lblShowDate = new JLabel("print of date");
        cmbPrintingDate = new JComboBox(comboInfo); 
        cmbPrintingDate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent  e)
            {
              JComboBox cb = (JComboBox)e.getSource();
              int sel = (int)cb.getSelectedIndex();
              intSettingsReport[0]=sel;
           }
        });
        
        cmbPrintingDate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent  e)
            {
               JComboBox cb = (JComboBox)e.getSource();
           int sel = (int)cb.getSelectedIndex();

           if (sel == 2)// no  //{"σε όλες τις σελίδες","μονο η 1η σελίδα","καθόλου"};
           {
           	 txtBtnDate.setText("");
           }
           else
           {
              
                txtBtnDate.setText(VariablesGlobal.globalDate);
           }
           }
        });        
        
 
         JLabel lblPrintCompanyTitle=new JLabel("print of company title");
         cmbPrintCompanyTitle = new JComboBox(comboInfo);
        cmbPrintCompanyTitle.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent  e)
            {
              JComboBox cb = (JComboBox)e.getSource();
              int sel = (int)cb.getSelectedIndex();
              intSettingsReport[1]=sel;
           }
        });
                 
        /* JLabel lblPrintCompanyInfo=new JLabel("print of company information");
         cmbPrintCompanyInfo = new JComboBox(comboInfo);   
        cmbPrintCompanyInfo.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent  e)
            {
              JComboBox cb = (JComboBox)e.getSource();
              int sel = (int)cb.getSelectedIndex();
              intSettingsReport[2]=sel;
           }
        });*/
               
          JLabel lblPrintFilterInfo=new JLabel("print of filters");
         cmbPrintFilterInfo = new JComboBox(comboInfo); 
         cmbPrintFilterInfo.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent  e)
            {
              JComboBox cb = (JComboBox)e.getSource();
              int sel = (int)cb.getSelectedIndex();
              intSettingsReport[2]=sel;
           }
        });



        


        JxPanel panelPrintInfo = new JxPanel();
        panelPrintInfo.setLayout(new GridLayout(0,2));
        
        panelPrintInfo.add(panelTitleToPrint);
        panelPrintInfo.add(panelSubTitleToPrint);

        JxPanel panelPrintingDate = new JxPanel();
        panelPrintingDate.setLayout(flowLayoutLeft2);
        panelPrintingDate.add(lblShowDate);
        panelPrintingDate.add(cmbPrintingDate);
        panelPrintingDate.add(txtBtnDate.getComponent());
        

        
        panelPrintInfo.add(panelPrintingDate);

        JxPanel panelPrintCompanyTitle = new JxPanel();
        panelPrintCompanyTitle.setLayout(flowLayoutLeft2);        
        panelPrintCompanyTitle.add(lblPrintCompanyTitle);
        panelPrintCompanyTitle.add(cmbPrintCompanyTitle);
        panelPrintInfo.add(panelPrintCompanyTitle);
           	
        JxPanel panelCompanyInfo = new JxPanel();
        panelCompanyInfo.setLayout(flowLayoutLeft2);            	
        //panelCompanyInfo.add(lblPrintCompanyInfo);
        //panelCompanyInfo.add(cmbPrintCompanyInfo);
        panelPrintInfo.add(panelCompanyInfo);
        
        
        JxPanel panelPrintFilterInfo = new JxPanel();
        panelPrintFilterInfo.setLayout(flowLayoutLeft2);            	
        panelPrintFilterInfo.add(lblPrintFilterInfo);
        panelPrintFilterInfo.add(cmbPrintFilterInfo);
        panelPrintInfo.add(panelPrintFilterInfo);
        
        

        panelReportMisc.add(panelPrintInfo);

        
        //--------------------------------misc -------------------------------------------
        panelIntoTabFilterSettings = new JxPanel();
        panelIntoTabFilterSettings.setLayout(new FlowLayout());//new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 2));//(new GridLayout(0,2)); //(panelIntoTabFilterSettings, BoxLayout.PAGE_AXIS));
        panelDataFilterSettings = new PanelDataFilterSettings(parentIn);
        panelIntoTabFilterSettings.add(panelDataFilterSettings.getPanelFilterSettings());
        //------------------------------  misc end--------------------------------     

       PanelTabFilters = new JPanelDecorated();
       PanelTabFilters.setLayout(new BorderLayout());//(new FlowLayout());
       panelIntoTabFilters = new JxPanel();
       panelIntoTabFilters.setLayout(new BoxLayout(panelIntoTabFilters, BoxLayout.PAGE_AXIS));
       //panelIntoTabFilters.add(panelReportFilters);
       //panelIntoTabFilters.add(panelReportMisc); //      add misc to tab 1
       //panelIntoTabFilters.add(panelPrinter);
       PanelTabFilters.add(panelIntoTabFilters, BorderLayout.PAGE_START);

        scrollPaneTabFilters = new JScrollPane();
        scrollPaneTabFilters.setBorder(null); 
        scrollPaneTabFilters.setViewportView(PanelTabFilters);        
     
       panelTabFilterSettings = new JPanelDecorated();
       panelTabFilterSettings.setLayout(new BoxLayout(panelTabFilterSettings, BoxLayout.PAGE_AXIS));//(new BorderLayout());
       panelTabFilterSettings.add(panelIntoTabFilterSettings,BorderLayout.CENTER);  //   add misc to tab 3

        scrollPaneTabFilterSettings = new JScrollPane();
        scrollPaneTabFilterSettings.setBorder(null);
       scrollPaneTabFilterSettings.setViewportView(panelTabFilterSettings);

       
       panelTabVisualSet = new JPanelDecorated();
       panelTabVisualSet.setLayout(new BorderLayout());
       panelIntoTabVisualSet = new JxPanel();
       //panelIntoTabVisualSet.setLayout(new BoxLayout(panelIntoTabVisualSet, BoxLayout.PAGE_AXIS));//(new BorderLayout());     //(new FlowLayout());
       panelIntoTabVisualSet = new JxPanel();
       panelIntoTabVisualSet.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 1));//(new GridLayout(0,2));
       panelTabVisualSet.add(panelIntoTabVisualSet, BorderLayout.PAGE_START);
       //panelTabVisualSet.add(panelDataViewNOrder);
       
        scrollPaneTabVisualSet = new JScrollPane();
        scrollPaneTabVisualSet.setBorder(null);
       scrollPaneTabVisualSet.setViewportView(panelTabVisualSet);
       
       
       
       //scrollPaneTabVisualSet.add(tabPaneGroup);
       
       tabbedPane = new JxTabbedPane();
       
       JPanel panelProp = new JxPanel();
       panelProp.setLayout(new BoxLayout(panelProp, BoxLayout.PAGE_AXIS));

       panelProp.add(scrollPaneTabVisualSet);
       //panelProp.add(tabPaneGroup);  / middle posion for groups
       
       
       
       
       tabbedPane.addTab("φίλτρα",scrollPaneTabFilters);
       tabbedPane.addTab("προτιμήσεις",panelProp);//scrollPaneTabVisualSet);  tabPaneGroup
       //tabbedPane.addTab("προβολή & ταξινόμηση στηλών",scrollPaneTabViewAndOrder);
       //tabbedPane.addTab("προβολή φίλτρων",scrollPaneTabFilterSettings);


        Action actionTabForward = new ActionTabForward();
        tabbedPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN ,0), "forward"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        tabbedPane.getActionMap().put("forward", actionTabForward);
        
        Action actionTabBackward = new ActionTabBackward();
        tabbedPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP ,0), "backward"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        tabbedPane.getActionMap().put("backward", actionTabBackward);



        // ------------------------------ printer settings------------------------------
       

       radioLaser = new JRadioButton(strPrintingTypeLaser);
       radioLaser.setOpaque(false);
       radioLaser.setActionCommand(strPrintingTypeLaser);
       radioLaser.addActionListener(new ActionSelectPrintingType());
       radioDotMatrix = new JRadioButton(strPrintingTypeDotMatrix);
       radioDotMatrix.setOpaque(false);
       radioDotMatrix.setActionCommand(strPrintingTypeDotMatrix);
       radioDotMatrix.addActionListener(new ActionSelectPrintingType());
       radioFile = new JRadioButton(strPrintingTypeFile);
       radioFile.setActionCommand(strPrintingTypeFile); 
       radioFile.setOpaque(false);
       radioFile.addActionListener(new ActionSelectPrintingType());      
       
       ButtonGroup group = new ButtonGroup();
       group.add(radioLaser);
       group.add(radioDotMatrix);
       group.add(radioFile);
        
       /* group.addItemListener(new ItemListener() 
        {
          public void itemStateChanged(ItemEvent evt)
          {

             CardLayout cl = (CardLayout)(cards.getLayout());
             cl.show(cards, (String)evt.getItem());
             selectedPrintingType = (String)evt.getItem();
             
          }
        });
       group.addActionListener(new ActionListener()
       {       	
          public void actionPerformed(ActionEvent e)
          {
          	setSelectedPrintingTypeDevice();
          }
       });*/

        

                
        JxPanel pnlLaser = new JxPanel();
        JxPanel pnlDotmatrix = new JxPanel();
        JxPanel pnlFile = new JxPanel();

       
        lblPage = new JLabel("page"); 
        
        //-------------------------------------------
        	
        //String[] strArPageSize = { SizeA5,SizeA4,SizeB4, SizeA3};
       
       JLabel lblPageSize = new JLabel("size of page");
       cmbPageSize = new JComboBox(STRARRAYPAGESIZELASER);  //array
       cmbPageSize.setSelectedItem(strPageSize);// default size
       cmbPageSize.addActionListener(new ActionListener()
       {       	
          public void actionPerformed(ActionEvent e)
          {
          	setSelectedPageSize();
          }
       });
       //Create the combo box, select item at index 4.
       //Indices start at 0, so 4 specifies the pig.
       


       
        btnPageSetup= new JButtonForPanelDecorated();
        btnPageSetup.setText("page setup");
       btnPageSetup.addActionListener(new ActionListener()
       {       	
          public void actionPerformed(ActionEvent e)
          {
          	displayDialogPageSetup();
          }
       });
        
       //------------------------------------------- 
        
       JLabel lblPageSizeDM = new JLabel("size of page"); 
       String[] strPageSizeDotmatrix = { SizeA4Dotmatrix,SizeA3Dotmatrix}; 
       cmbPageSizeDotmatrix = new JComboBox(strPageSizeDotmatrix);
       cmbPageSizeDotmatrix.setSelectedItem(strPageSizeDotmatrix);
       cmbPageSizeDotmatrix.addActionListener(new ActionListener()
       {       	
          public void actionPerformed(ActionEvent e)
          {
          	setSelectedPageSizeDotmatrix();
          }
       });        
        
       JLabel lblPortDM = new JLabel("port of printer"); 
       String[] strPortDotmatrix = { portLPT1,portLPT2}; 
       cmbPortDotmatrix = new JComboBox(strPortDotmatrix);
       cmbPortDotmatrix.setSelectedItem(strPortDotmatrix);
       cmbPortDotmatrix.addActionListener(new ActionListener()
       {       	
          public void actionPerformed(ActionEvent e)
          {
          	setSelectedPortDotmatrix();
          }
       });

       lblCpi = new JLabel("Characters Per Inch (cpi)"); 
       String[] dotmatrixCpi = { cpiNoStr,cpi10+"", cpi15+""}; 
       cmbCpiDotmatrix = new JComboBox(dotmatrixCpi);
       cmbCpiDotmatrix.setSelectedItem(cpiNoStr);
       cmbCpiDotmatrix.addActionListener(new ActionListener()
       {       	
          public void actionPerformed(ActionEvent e)
          {
          	setSelectedCpiDotmatrix();
          }
       });


        //-------------------------------------------
        
        JLabel lblFilePath = new JLabel("directory");
        JTextField txtFilePath = new JTextField(26);
        //txtBtnDirSave = new JTextBoxWithEditButtons();
        txtBtnDirSave = new JTextBoxWithEditButtons(txtFilePath, true ,ICON_TREEFOLDER_OPENED,null, false,null,null,2, parent, "","",MONTH_DATE_ONLY);                  	

        txtBtnDirSave.setText(System.getProperty("user.dir"));
        
       JButtonForPanelDecorated btnExportSettings = new JButtonForPanelDecorated("export preferences");
       btnExportSettings.addActionListener(new ActionListener()
       {       	
          public void actionPerformed(ActionEvent e)
          {
          	displayDialogExportToFileSettings();
          }
       });        
        //ExportToFileSettings
        
        //-------------------------------------------
        

        
        JxPanel panelOrientation = new JxPanel();
              panelOrientation.setBorder( new TitledBorder(BorderFactory.createTitledBorder(null, "προσανατολισμός",TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null,CLR_PANEL_BORDER)));                                       
       
        
        panelOrientation.setLayout(new BoxLayout(panelOrientation, BoxLayout.LINE_AXIS));
               

        panelOrientation.add(radioPortrait);
        panelOrientation.add(radioLandscape);
        
        pnlLaser.add(lblPageSize);
        pnlLaser.add(cmbPageSize);

        
        pnlLaser.add(panelOrientation);
        pnlLaser.add(btnPageSetup);
        
        pnlDotmatrix.add(lblPageSizeDM);
        pnlDotmatrix.add(cmbPageSizeDotmatrix);
        pnlDotmatrix.add(lblPortDM);
        pnlDotmatrix.add(cmbPortDotmatrix);
        pnlDotmatrix.add(lblCpi);
        pnlDotmatrix.add(cmbCpiDotmatrix);
        
        pnlFile.add(lblFilePath);
        pnlFile.add(txtBtnDirSave.getComponent());
        pnlFile.add(btnExportSettings);
        
        
        
        JxPanel panelPrintingType = new JxPanel();
        panelPrintingType.setBorder( new TitledBorder(BorderFactory.createTitledBorder(null,"type of printer",TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null,CLR_PANEL_BORDER)));                                       
        
        panelPrintingType.setLayout(new BoxLayout(panelPrintingType, BoxLayout.PAGE_AXIS));
 //       radioLaser.setSelected(true);
 //       panelPrintingType.add(radioLaser);
 //       panelPrintingType.add(radioDotMatrix);
 //       panelPrintingType.add(radioFile);
        
 //       cards  = new JxPanel();
 //       cards.setLayout(new CardLayout());
//        cards.add(pnlLaser, strPrintingTypeLaser);
 //       cards.add(pnlDotmatrix, strPrintingTypeDotMatrix);
//        cards.add(pnlFile, strPrintingTypeFile);
        
        panelPrinter.add( pnlLaser);
 //       panelPrinter.add(panelPrintingType);
//        panelPrinter.add(cards);
       
       

       toolBarData = new ToolBarData();
       toolBarData.setFocusable(false);    	
    	
    	//this.setLayout(new BorderLayout());
    	this.add(toolBarData, BorderLayout.PAGE_START);

        this.add(tabbedPane, BorderLayout.CENTER);
       // this.add(panelBottom, BorderLayout.PAGE_END);   
    }

        
    /*
    *  called by PanelReportSettingsPreview.setEntityDirectPreviewForm    
    */    
    public void setEntityForDirectPrintOfForm(EntityReport erpt,  PanelManagement panelManagementIn)//,String[] arrayOfNameOfPksOfRecordToShowIn,
            //String[] arrayOfValueOfPksOfRecordToShowIn)
    {
        //arrayOfNameOfPksOfRecordToShow=arrayOfNameOfPksOfRecordToShowIn;
        //System.out.println("PanelReportSettings.setEntityForDirectPrintOfForm ---    erpt:"+erpt);
      entityReport=erpt;
      entityReportBand=erpt.getEntityReportBands();
      //entityReportBandLength=entityReportBand.length;
      EntityGroupOfComps[] entityGroupOfComps = erpt.getEntityGroupOfComps();

      viewType=erpt.getType();

      //primKeyValue=primKeyValueIn;
      //dataVector=dataVectorIn;

      //sql2WhereField=sql2WhereFieldIn;
      //sql2WhereValue=sql2WhereValueIn;
      title=erpt.getCaption();
      subTitle=erpt.getSubTitle();
      //showSummaryPage=showSummaryPageIn;
      entityFilterSettings=erpt.getEntityFilterSettings();
      //isFieldSelected=fieldSelectedIn;
      //isFieldEditable=fieldsEditableIn;
      //fieldsOrderby=erpt.getFieldsOrderby();
      intSettingsReport=erpt.getIntSettingsReport();
      boolSettingsReport=erpt.getBoolSettingsReport();
     // yearEnforce=erpt.getYearEnforce();
      panelManagement= panelManagementIn;        
             //if(viewType.equalsIgnoreCase("FORM"))
        //System.out.println("PanelReportSettings.setEntityForDirectPrintOfForm --- viewType:"+viewType);

        
        
        
    }      
        
   
    /**
     * 
     * called by panelreportsettingspreview.setEntity, 
     */
    /*public void setEntity(String entityIn,EntityReportBand[] entityReportBandIn,String viewTypeIn,/*String entityHeaderIn,String primKeyIn, String primKeyValueIn,
    String titleIn,String subTitleIn, boolean showSummaryPageIn, EntityFilterSettings[] entityFilterSettingsIn,
    EntityGroupOfComps[] entityGroupOfComps,int[] fieldSelectedIn, boolean[] fieldsEditableIn, int[] fieldsOrderbyIn,int[] intSettingsReportIn, boolean[] boolSettingsReportIn,
    String yearEnforceIn, PanelManagement panelManagementIn)
    */
        
     public void setEntity(EntityReport erpt, boolean showSummaryPageIn,  PanelManagement panelManagementIn)     
    {
      // if (this != null)
      // { this.removeAll(); }//erases all components placed during initialization
      
      entityReport=erpt;
      entityReportBand=erpt.getEntityReportBands();
      //entityReportBandLength=entityReportBand.length;
      EntityGroupOfComps[] entityGroupOfComps = erpt.getEntityGroupOfComps();

      viewType=erpt.getType();
       //System.out.println("PanelReportSettings.setEntity type:"+viewType);
      //primKeyValue=primKeyValueIn;
      //dataVector=dataVectorIn;

      //sql2WhereField=sql2WhereFieldIn;
      //sql2WhereValue=sql2WhereValueIn;
      title=erpt.getCaption();
      subTitle=erpt.getSubTitle();
      //showSummaryPage=showSummaryPageIn;
      entityFilterSettings=erpt.getEntityFilterSettings();
      //isFieldSelected=fieldSelectedIn;
      //isFieldEditable=fieldsEditableIn;
      //fieldsOrderby=erpt.getFieldsOrderby();
      intSettingsReport=erpt.getIntSettingsReport();
      boolSettingsReport=erpt.getBoolSettingsReport();
      //yearEnforce=erpt.getYearEnforce();
      panelManagement= panelManagementIn;
      
      //query = entityQuery[0].getQuery();
     if(viewType.equalsIgnoreCase("FORM"))
     {
     	//btnFormDesign.setVisible(true);
     	
     	cmbCpiDotmatrix.setVisible(false);
     	lblCpi.setVisible(false);
     	

     }
     else
     {
     	//btnFormDesign.setVisible(false);
     	
     	cmbCpiDotmatrix.setVisible(true);
     	lblCpi.setVisible(true);     	
     }
       
 //    txtBtnDate = new JTextBoxWithEditButtons();
     //txtBtnDate.setYearEnforce(yearEnforceIn);
       utilsDate.readFromFileDateFormats();
     
     listPanelDataViewNOrder = new ArrayList();
     
     //panelDataFilter.calculateSubquery();
     //System.out.println("PanelReportSettings. "+query);
     panelIntoTabFilters.add(panelDataFilter.getPanelFilters());  
     //panelIntoTabFilters.add(panelReportMisc); //      add misc to tab 1
      
      panelIntoTabFilters.add(panelPrinter);     
       
     panelIntoTabVisualSet.add(panelReportMisc);  
     
     //panelIntoTabVisualSet.add(panelIncludeReportSets); 
 
     
     //----------------------------- first tab -----------------------------------
	
       
        //---------------second tab  ---------------------------------------
        

        	
        	//System.out.println("--PanelReportSettings.setEntity "+intSettingsReport[0] + " " +intSettingsReport[1]	);
        cmbPrintingDate.setSelectedIndex(intSettingsReport[0]);
        if(intSettingsReport[0] == 2)
        {
           //txtBtnDate.setText(VariablesGlobal.globalDate);
           txtBtnDate.setText("");	
        }
        
        cmbPrintCompanyTitle.setSelectedIndex(intSettingsReport[1]);
        //cmbPrintCompanyInfo.setSelectedIndex(intSettingsReport[2]);
        cmbPrintFilterInfo.setSelectedIndex(intSettingsReport[2]); 


        // --------------------------   misc -------------------------------------
          txtTitleToPrint.setText(title);
          txtSubTitleToPrint.setText(subTitle);
         // System.out.println("PanelReportSettings date "+VariablesGlobal.globalDate);

                // --------------------------   view -------------------------------------

          listChkShowHorizontalLines.clear();
          listChkShowVerticalLines.clear();
          listChkShowZero.clear();
          //listChkShowColumnWithoutData.clear();

      for(int e=0;e<entityReportBand.length;e++ )
      {
        //System.out.println("PanelReportSettings.setEntity   e:"+e+"   "+ENTITYREPORT_QUERY_TYPE_MAIN+ "    viewType:"+viewType+"    entityReportBand.length:"+entityReportBand.length);
      	System.out.println("PanelReportSettings.setEntity    entityReportBand  e:"+e+"    type:"+entityReportBand[e].getType()+"    viewType:"+viewType+"    entityReportBand.length:"+entityReportBand.length);
          //EntityDBFields[] entityDBFields = entityReportBand[e].getEntityDBFields();//.getEntityReportBandFields();
   //       EntityReportBandField[] entityReportBandFields = entityReportBand[e].getEntityReportBandFields();
          
          
      	/* if(entityReportBand[e].getType()==ENTITYREPORT_QUERY_TYPE_GROUP)
      	 {
      	 	hasGroups = true;

      	 
      	 		
      	 	//query = entityReportBand[e].getQuery()+" "+entityReportBand[e].getQueryOrderBy();
                //System.out.println(".....PanelReportSettings.setEntity ....."+e+" ");
            panelDataFilter.setEntity(entityFilterSettings,entityGroupOfComps,PANEL_FILTER_REPORT,yearEnforce,panelManagement);
            
          
            addPanelBand(entityReportBandFields, entityReportBand[e].getCaption(), e);       	 		
            
      	 	
      	 }*/
      	 //else
          if(entityReportBand[e].getType()==ENTITYREPORT_QUERY_TYPE_MAIN)
      	 {
      	 	
      	 	//query = entityReportBand[e].getQuery()+" "+entityReportBand[e].getQueryOrderBy();
            panelDataFilter.setEntity(entityFilterSettings,entityGroupOfComps,PANEL_FILTER_REPORT,/*yearEnforce,*/panelManagement);
            //System.out.println("PanelReportSettings.setEntity (setEntity) "+query);
      
            
            addPanelBand(entityReportBand[e]);   
           
           /* if(entityReportBand[e].getEntityReportBandChild()!=null)
            {
                System.out.println("PanelReportSettings.setEntity      e:"+e +"     captionofband:"+entityReportBand[e].getCaption()+"    entityReportBand[e].getEntityReportBandChild().lenth:"+entityReportBand[e].getEntityReportBandChild().length);	 	
               //EntityReportBandField[] entityReportBandFieldsChild = entityReportBand[e].getEntityReportBandChild().getEntityReportBandFields();
                 addPanelBand(entityReportBand[e]);     
            
              /*for(int i = 0;i<entityReportBand[e].getEntityReportBandChild().length; i++)
              {
                  addPanelBand(entityReportBand[e].getEntityReportBandChild()[i]/*.getEntityReportBandChild()*//*);  //,i); 
              }*/
           /* }
            else // if is null
            {
                 addPanelBand(entityReportBand[e]);     
            }*/
            
      	 }
      	 else if(entityReportBand[e].getType()==ENTITYREPORT_QUERY_TYPE_ADDITIONAL)
      	 {
      	 	System.out.println("PanelReportSettings.setEntity type ENTITYREPORT_QUERY_TYPE_ADDITIONAL "+entityReportBand[e].getType());
      	 }
      	 else
      	 {
      	 	System.out.println(" PanelReportSettings.setEntity UNKNOWN type "+entityReportBand[e].getType());
      	 }
      	
      }

      panelDataFilterSettings.setEntity(entityFilterSettings);
   

 
   	   XMLReader reader = new XMLReader();
   String[] tagElements ={"reportEntity"};
   String[] tagElementsType ={"String"};    	
    	
    System.out.println(" PanelReportSettings.setEntity    filePrefs:"+filePrefs+"   entityReport.getName():"+entityReport.getName());
     if(reader.checkIfElementExists(filePrefs, "Report",tagElements,tagElementsType,entityReport.getName()))
     {     
         chooseReportDesign(entityReport.getName());
       
     }
      	    
}
  /*
     *
     *
     *
     *   called by this.setEntity
     */
   private void addPanelBand(EntityReportBand entityReportBandIn)  //,  int noOfGroup)
   {
   	   
   	   panelDataViewNOrder = new PanelDataViewNOrder();
   	   // ---- isFieldSelected, isFieldEditable,fieldsOrderby   is not for all groups  
           //EntityDBFields[] entityDBFields  =  entityReportBandIn.getEntityDBFields();
           EntityReportBandField[] entityReportBandFields  =  entityReportBandIn.getEntityReportBandFields();
       panelDataViewNOrder.setEntity(getColumnsForGroupViewNOrder(/*entityDBFields*/entityReportBandFields), isFieldSelected, isFieldEditable,entityReportBandIn.getFieldsOrderby(),null,null,entityReportBandLength,TYPE_OF_VIEWANDORDER_REPORT,"");             
       listPanelDataViewNOrder.add(panelDataViewNOrder);
        
       JxPanel  panelTabGroup = new JxPanel();
       panelTabGroup.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 1));//(new BoxLayout(panelTabGroup, BoxLayout.PAGE_AXIS));//(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 1));//new BorderLayout());
        JxPanel panelIntoTabViewAndOrder = new JxPanel();
       //panelIntoTabViewAndOrder.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 2));//(new GridLayout(0,2));
       panelIntoTabViewAndOrder.setLayout(new BorderLayout());
       
       panelIntoTabViewAndOrder.add(panelDataViewNOrder.getPanelView(), BorderLayout.LINE_START);
       
        panelIntoTabViewAndOrder.add(panelDataViewNOrder.getPanelOrder(), BorderLayout.LINE_END); 	


        //------------------------------ VisualMisc  1--------------------------------
       
      
        panelReportVisualMisc1 = new JxPanel();
        panelReportVisualMisc1.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 4));//(new BorderLayout());//new BoxLayout(panelReportMisc, BoxLayout.PAGE_AXIS));
        panelReportVisualMisc1.setBorder( new TitledBorder(BorderFactory.createTitledBorder(null, "ρυθμίσεις εμφάνισης",TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null,CLR_PANEL_BORDER)));                                       
        //panelReportVisualMisc1.setBorder(new TitledBorder("ρυθμίσεις εμφάνισης"));          


        chkShowHorizontalLines = new JCheckBox("show horizontal lines");
        chkShowHorizontalLines.setOpaque(false); 
        listChkShowHorizontalLines.add(chkShowHorizontalLines);
        chkShowVerticalLines = new JCheckBox("show vertical lines");
        chkShowVerticalLines.setOpaque(false);
        listChkShowVerticalLines.add(chkShowVerticalLines);
        chkShowZero = new JCheckBox("show zero (0.00)");
        chkShowZero.setOpaque(false);  
        listChkShowZero.add(chkShowZero);
        //chkShowColumnWithoutData = new JCheckBox("show column without data");
        //chkShowColumnWithoutData.setOpaque(false);
        //listChkShowColumnWithoutData.add(chkShowColumnWithoutData);
                     
        //chkShowDoubleHeightRow = new JCheckBox("διπλό ύψος γραμμής");
        //chkShowDoubleHeightRow.setOpaque(false);          
      //System.out.println("PanelReportSettings.addPanelBand    -("+noOfGroup+")-     boolSettings.length:"+boolSettings.length);
        boolean[] boolSettings = entityReportBandIn.getBoolSettings();
        setAllBoolSettings(boolSettings);

                
      panelReportVisualMisc1.add(chkShowHorizontalLines);
        panelReportVisualMisc1.add(chkShowVerticalLines);
        panelReportVisualMisc1.add(chkShowZero);
        //panelReportVisualMisc1.add(chkShowColumnWithoutData);        
        //panelReportVisualMisc1.add(chkShowDoubleHeightRow);

        
        
        //------------------------------ VisualMisc 2 --------------------------------
        
                panelReportVisualMisc2 = new JxPanel();
        panelReportVisualMisc2.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 4));//(new BorderLayout());//new BoxLayout(panelReportMisc, BoxLayout.PAGE_AXIS));
        panelReportVisualMisc2.setBorder( new TitledBorder(BorderFactory.createTitledBorder(null, "στοίχηση και χρώμα",TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null,CLR_PANEL_BORDER)));                          
        
        

        
         String[] comboInfoAllignment ={"αριστερά","κέντρο","δεξιά"};
       
       JLabel lblBandAllignment = new JLabel("στοίχηση:");
       lblBandAllignment.setHorizontalAlignment(JLabel.RIGHT);
        cmbBandAllignment = new JComboBox(comboInfoAllignment); 
        cmbBandAllignment.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent  e)
            {
              JComboBox cb = (JComboBox)e.getSource();
              int sel = (int)cb.getSelectedIndex();
  //            intSettingsReport[0]=sel;
           }
        });
        
        cmbBandAllignment.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent  e)
            {
               JComboBox cb = (JComboBox)e.getSource();
           int sel = (int)cb.getSelectedIndex();
            }

        });        
                
        
             
           String[] comboInfoColor ={"ασπρο","γκρι ανοιχτό","γκρι","γκρι σκούρο"};
       
       JLabel lblBandColor = new JLabel("χρώμα ενότητας:");
       lblBandColor.setHorizontalAlignment(JLabel.RIGHT);
        cmbBandBackColor = new JComboBox(comboInfoColor); 
        cmbBandBackColor.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent  e)
            {
              JComboBox cb = (JComboBox)e.getSource();
              int sel = (int)cb.getSelectedIndex();
  //            intSettingsReport[0]=sel;
           }
        });
        
        cmbBandBackColor.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent  e)
            {
               JComboBox cb = (JComboBox)e.getSource();
           int sel = (int)cb.getSelectedIndex();
            }

        });        
        
        
        panelReportVisualMisc2.add(lblBandAllignment);
        panelReportVisualMisc2.add(cmbBandAllignment);
        panelReportVisualMisc2.add(lblBandColor);
        panelReportVisualMisc2.add(cmbBandBackColor);
        
  
        
        // ----------------------------------------------------------------------
       panelTabGroup.add(panelIntoTabViewAndOrder);
//       panelTabGroup.add(panelReportVisualMisc1);
//       panelTabGroup.add(panelReportVisualMisc2);

       

       JPanelDecorated panelTabGroupDecor = new JPanelDecorated();
       panelTabGroupDecor.setLayout(new BorderLayout());
       panelTabGroupDecor.add(panelTabGroup, BorderLayout.PAGE_START);
        
       JScrollPane scrollPaneTabGroup = new JScrollPane();
       scrollPaneTabGroup.setBorder(null);
       scrollPaneTabGroup.setOpaque(false);
       scrollPaneTabGroup.setViewportView(panelTabGroupDecor); 
     //System.out.println("PanelReportSettings.addPanelBand           --B");  

       tabbedPane.addTab("ενότητα "+entityReportBandIn.getCaption(), scrollPaneTabGroup);     
     
   }


   /**
    * 
    * called from this.addPanelBand and this.chooseReportDesignGroup.
    */
   private void setAllBoolSettings(boolean[] boolSettings)
   {
       
      if(boolSettings!=null) 
      {
        chkShowHorizontalLines.setSelected(boolSettings[0]);  
        chkShowVerticalLines.setSelected(boolSettings[1]);
        chkShowZero.setSelected(boolSettings[2]);
        //chkShowColumnWithoutData.setSelected(boolSettings[3]);
        //chkShowDoubleHeightRow.setSelected(boolSettings[4]);
        
      }
      else
      {
          System.out.println("PanelReportSettings.setAllBoolSettings   --   boolSettings:"+boolSettings);
        chkShowHorizontalLines.setSelected(true);  
        chkShowVerticalLines.setSelected(true);
        chkShowZero.setSelected(false);
        //chkShowColumnWithoutData.setSelected(false);
        //chkShowDoubleHeightRow.setSelected(false);
              	
      }       
       
       
   }
   
   
    private RecColumn[] getColumnsForGroupViewNOrder(EntityReportBandField[] entityReportBandFields)//EntityDBFields[] entityDBFields)
    {

      RecColumn[] recColumn = null;
      recColumn = new RecColumn[entityReportBandFields.length];

      
             for (int c = 0; c < entityReportBandFields.length; c++)
            {
                        // for reports and table preferences
        //RecColumn(String colDbNameIn,String colCaptionIn,int colDbTypeIn,String colDbTableIn,String colDbClassStringIn,int colDbLengthIn, int colDbLengthMaxActualIn, boolean isColPKIn)
        
                
                
                //System.out.println("PanelReportSettings.getColumnsForGroupViewNOrder:   ==   c:"+c+"    "+entityReportBandFields[c].getCaption());
                //  	recColumn[c]= new RecColumn(entityReportBandFields[c].getTableName(),entityReportBandFields[c].getCaption(),0,null,entityReportBandFields[c].getColClassName(),entityReportBandFields[c].getColWidth(),0,false);       //rsmd.getColumnType(c),rsmd.getTableName(c),rsmd.getColumnClassName(c),rsmd.getColumnDisplaySize(c),0,rsmd.isAutoIncrement(c));
                recColumn[c]= new RecColumn(entityReportBandFields[c].getDbFieldName(), entityReportBandFields[c].getCaption(),0,entityReportBandFields[c].getTableName(),entityReportBandFields[c].getColClassName(),entityReportBandFields[c].getColWidth(),entityReportBandFields[c].getColWidth(),false);
            }	
    	return recColumn;
    }

  
  
    private void saveReportPrefs()
    {
    	//utilsFileSystem.writeFile(composeAndGetPrefsXML(),filePrefs);
    	
    /*String name =  utilsGui.showInputMessage((Object)"Παρακαλώ εισάγετε ονομασία εκτύπωσης.",(Object)entityReport.getName());
    boolean nameExists=false;
    // if name exists ask to change
    for (int d=0;d<cmbPrintDesign.getItemCount();d++)
    {
    	if(name.equalsIgnoreCase(cmbPrintDesign.getItemAt(d).toString()))
    	{
    		utilsGui.showMessageInfo("Η ονομασία '"+name+"' υπάρχει ήδη. Παρακαλώ αποθηκέυστε με άλλο όνομα.");
    		nameExists=true;
    		break;
    	}    
    	else
    	{ 
    		nameExists=false;

    	}
    }
    
    if(!nameExists)
    {*/
      //utilsFileSystem.addToXMLFile(composeAndGetReportPrefsXML(name),filePrefs,"</ReportPrefs>");
      
      //utilsFileSystem.replaceElementInXMLFile(filePrefs,"Report"+entityReportBandLength,cmbPrintDesign.getSelectedItem().toString(),composeAndGetReportPrefsXML(cmbPrintDesign.getSelectedItem().toString()));
      
   	   XMLReader reader = new XMLReader();
   String[] tagElements ={"reportEntity"};
   String[] tagElementsType ={"String"};    	
    	
     if(reader.checkIfElementExists(filePrefs, "Report",tagElements,tagElementsType,entityReport.getName()))
     {
         //chooseReportDesign(entityReport.getName());
         //public void replaceElementInXMLFile(String toFile,String tagCategory,String entityToRemove,String elementToAdd )
         
         
         utilsFileSystem.replaceElementInXMLFile(filePrefs,"Report",entityReport.getName(),composeAndGetReportPrefsXML(),tagElements,tagElementsType);
     }
      else
      {   
         utilsFileSystem.addToXMLFile(composeAndGetReportPrefsXML(),filePrefs,"</ReportPrefs>");
      }


     /*if(reader.checkIfElementExists(filePrefs,"Report",tagElements,tagElementsType,entityReport.getName()))
     {
     	System.out.println("PanelReportSettings.saveAsReportPrefs replace for "+entityReport.getName());
     	// replace
     	utilsFileSystem.replaceElementInXMLFile(filePrefs,"Report",entityReport.getName(),composeAndGetReportPrefsXML(name));
     }
     else
     {*/
     	//System.out.println("DialogDataConfig.saveConfig add for "+entityReport.getName());


    	//System.out.println("DialogDatConfig.saveConfig "+);
    	
    }
   
   // called by saveAsReportPrefs
  private String composeAndGetReportPrefsXML()
  {
  	
  	//System.out.println("PanelReportSettings---- "+name+" "+txtTitleToPrint.getText());
  	
  	String ret = "";

        Writer writer = new java.io.StringWriter();
        XmlWriter xmlwriter = new XmlWriter(writer);

            xmlwriter.writeEntity("Report");
       
            //EntityReportForm entityReportForm = (EntityReportForm)listEntityReportForm.get(p);
          
            //String backgroundFile = entityReportForm.getBackgroundFile();
        	//xmlwriter.writeEntity("name").writeText(tableReportFields.getModel().getValueAt(l,1).toString());
        	//xmlwriter.endEntity();
        	xmlwriter.writeEntity("reportEntity").writeText(entityReport.getName());
        	xmlwriter.endEntity();
        	
        	xmlwriter.writeEntity("reportEntity").writeText(entityReport.getName()); //two times because we need to get it in "chooseReportDesign"
        	xmlwriter.endEntity();
        		
         	xmlwriter.writeEntity("reportTitle").writeText(txtTitleToPrint.getText());
        	xmlwriter.endEntity();        	
        	
        	xmlwriter.writeEntity("reportSubTitle").writeText(txtSubTitleToPrint.getText());
        	xmlwriter.endEntity();


        	xmlwriter.writeEntity("intShowDate").writeText(cmbPrintingDate.getSelectedIndex()+"");
        	xmlwriter.endEntity();    	
            
        	xmlwriter.writeEntity("intShowCompanyTitle").writeText(cmbPrintCompanyTitle.getSelectedIndex()+"");
        	xmlwriter.endEntity(); 

        	//xmlwriter.writeEntity("intShowCompanyInfo").writeText(cmbPrintCompanyInfo.getSelectedIndex()+"");
        	//xmlwriter.endEntity();        	


        	xmlwriter.writeEntity("intPrintFilters").writeText(cmbPrintFilterInfo.getSelectedIndex()+"");
        	xmlwriter.endEntity(); 
  
 
  
/*          	String isPrintPageSum="false";
        	if(chkPrintPageSum.isSelected())
        	{    		isPrintPageSum="true";      	}
        	else
        	{     		isPrintPageSum="false";       	}
        	xmlwriter.writeEntity("boolPrintPageSum").writeText(isPrintPageSum);
        	xmlwriter.endEntity();       	
*/        		
        /*	String allBoolsOrderByAsc = "";
           boolean[]  fieldsOrderbyAsc = getFieldsOrderbyAsc();
               for(int f= 0 ; f<fieldsOrderbyAsc.length;f++)
               {
            	   allBoolsOrderByAsc=allBoolsOrderByAsc+fieldsOrderbyAsc[f]+",";	
               }                   	
        	
        	xmlwriter.writeEntity("boolsOrderByAsc").writeText(allBoolsOrderByAsc);
        	xmlwriter.endEntity(); */ 
        //	xmlwriter.changeLine();                 
          for(int e=0;e<entityReportBand.length;e++)
          {
            //xmlwriter.writeEntity("ReportGroup"+(e+1));
        	xmlwriter.writeEntity("reportGroupName"+(e)).writeText(entityReportBand[e].getName());
        	xmlwriter.endEntity();                 	


        	String allIntsShowColumns = "";
        	
            int[] fieldsShowColumns = getShowColumns(e);
            //System.out.println("PanelReportSettings.composeAndGetReportPrefsXML fieldsShowColumns: "+fieldsShowColumns.length);
               for(int f= 0 ; f<fieldsShowColumns.length;f++)
               {
            	   allIntsShowColumns=allIntsShowColumns+fieldsShowColumns[f]+",";	
               }                	
           	System.out.println("PanelReportSettings.composeAndGetReportPrefsXML fieldsShowColumns.length:"+fieldsShowColumns.length+" "+allIntsShowColumns);
         	xmlwriter.writeEntity("showColumns"+(e)).writeText(allIntsShowColumns);
        	xmlwriter.endEntity();       	
        	
        	
        	xmlwriter.writeEntity("sqlOrderBy"+(e)).writeText(getSqlOrderBy());
        	xmlwriter.endEntity();
        	
        	String allIntsOrderBy = "";
               // entityReportBand[e].getFieldsOrderby()
            int[] fieldsOrderby = getFieldsOrderby();
               for(int f= 0 ; f<fieldsOrderby.length;f++)
               {
            	   allIntsOrderBy=allIntsOrderBy+fieldsOrderby[f]+",";	
               }                   	
        	
        	xmlwriter.writeEntity("intsOrderBy"+(e)).writeText(allIntsOrderBy);
        	xmlwriter.endEntity();
      	

        	String allBoolsOrderByAsc = "";
           boolean[]  fieldsOrderbyAsc = getFieldsOrderbyAsc();
               for(int f= 0 ; f<fieldsOrderbyAsc.length;f++)
               {
            	   allBoolsOrderByAsc=allBoolsOrderByAsc+fieldsOrderbyAsc[f]+",";	
               }                   	
        	
        	xmlwriter.writeEntity("boolsOrderByAsc"+(e)).writeText(allBoolsOrderByAsc);
        	xmlwriter.endEntity();                   
        
                
 
         	String allBoolsOrderByDesc = "";
            boolean[]  fieldsOrderbyDesc = getFieldsOrderbyDesc();
               for(int f= 0 ; f<fieldsOrderbyDesc.length;f++)
               {
            	   allBoolsOrderByDesc=allBoolsOrderByDesc+fieldsOrderbyDesc[f]+",";	
               }
        	xmlwriter.writeEntity("boolsOrderByDesc"+(e)).writeText(allBoolsOrderByDesc);
        	xmlwriter.endEntity();             	
                System.out.println("PanelReportSettings.composeAndGetReportPrefsXML    allBoolsOrderByAsc:"+allBoolsOrderByAsc+"   allBoolsOrderByDesc:"+allBoolsOrderByDesc);

         	String allBoolSettings = "";
                allBoolSettings = getAllBoolSettings(e);
        	xmlwriter.writeEntity("allBoolSettings"+(e)).writeText(allBoolSettings);
        	xmlwriter.endEntity();                             
             System.out.println("PanelReportSettings.composeAndGetReportPrefsXML  allBoolSettings:" +allBoolSettings);   
         //xmlwriter.endEntity(); // report
         //xmlwriter.changeLine();
          
          }//  entityReportBand
         	xmlwriter.endEntity(); 
        	xmlwriter.changeLine();           

     //xmlwriter.endEntity(); // props 
     //xmlwriter.close();

  	 ret = writer.toString();

  	return ret;         	
    }
   
    private String getAllBoolSettings(int group)
    {
        String ret="";

         JCheckBox chkHor =  (JCheckBox)listChkShowHorizontalLines.get(group);
        if(chkHor.isSelected())
        {       ret=ret+"true,";       }
        else
        {   ret=ret+"false,";         }        
         JCheckBox chkVer =  (JCheckBox)listChkShowVerticalLines.get(group);
        if(chkVer.isSelected())
        {       ret=ret+"true,";       }
        else
        {       ret=ret+"false,";      }

         JCheckBox chkShowZero =  (JCheckBox)listChkShowZero.get(group);
        if(chkShowZero.isSelected())
        {       ret=ret+"true,";       }
        else
        {       ret=ret+"false,";      }
        /* JCheckBox chkColWithoutData =  (JCheckBox)listChkShowColumnWithoutData.get(group);
        if(chkColWithoutData.isSelected())
        {       ret=ret+"true,";       }
        else
        {       ret=ret+"false,";      }  */

        
        
        return ret;
    }
  
    private String getSqlOrderBy()
    {
    	String ret = "";
    
         ret = panelDataViewNOrder.getSubquery("");//,0);
      //System.out.println("DialogDataConfig.getSqlOrderBy "+ret);
       return ret;
    }    
    
    // before this is called, this: panelDataViewNOrder.getSubquery("") should be called
    private int[] getFieldsOrderby()
    {
      return	panelDataViewNOrder.getFieldsOrderby();
    	
    }
   
    private boolean[] getFieldsOrderbyAsc()
    {
      return	panelDataViewNOrder.getFieldsOrderbyRadioAsc(); 	
    }   
   
    private boolean[] getFieldsOrderbyDesc()
    {
      return	panelDataViewNOrder.getFieldsOrderbyRadioDesc(); 	
    }      
    
    private void locateOnCenterOfTheScreen()
    {
    	Dimension paneSize   = this.getSize();
    	Dimension screenSize = this.getToolkit().getScreenSize();
    	this.setLocation(
            (screenSize.width  - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
    }

   private void print()
   {
   	
   	    System.out.println("PanelReportSettings.print "+selectedPrintingType);
   	
   }
    
   
   public boolean checkIfFieldsAreCompleted()
   {
       
       boolean areFildsCompleted = panelDataFilter.checkIfFieldsAreCompleted();
    
       return areFildsCompleted;
   }
   
   
   public String calculateAndGetSQLForPrintPreviewOfForm()
   {
      /* String retQuery="";
        retQuery = entityReport.getQuery()+" "+entityReport.getQueryOrderBy();
       */
       
        String query = entityReport.getQuery()+" "+entityReport.getQueryOrderBy();
      sqlForPrintPreview = panelDataFilter.getSubquery(query);       
      System.out.println("PanelReportSettings.calculateSQLForPrintPreviewOfForm     =====--------------======     sqlForPrintPreview:"+sqlForPrintPreview);
      String q = sqlForPrintPreview; 
       
       return q;
   }
   
   /**
    * 
    * called by PanelReportSettingsPreview.showPanel
    */
   public void calculateSQLForPrintPreview()
   {
    /*public static final int ENTITYREPORT_QUERY_TYPE_MAIN = 0;
    public static final int ENTITYREPORT_QUERY_TYPE_GROUP = 1;
    public static final int ENTITYREPORT_QUERY_TYPE_ADDITIONAL = 2;*/
    String strQueryOrderBy = "";
    String queryReturn="";
    int intNoOfRows = 0;
  for(int e=0;e<entityReportBand.length;e++ )
  {
  	//System.out.println("PanelReportSettings.calculateSQLForPrintPreview    getType:"+entityReportBand[e].getType());
     /*if(entityReportBand[e].getType()==ENTITYREPORT_QUERY_TYPE_GROUP)//hasGroups)// header    1
     {
//----------------> make it for two or more, now sets the last
      PanelDataViewNOrder pdvno = (PanelDataViewNOrder)listPanelDataViewNOrder.get(e);
      // getSubquery gets only the orderby subquery
      //System.out.println("PanelReportSettings.calculateSQLForPrintPreview ViewNOrder "+sqlForPrintPreview);
      //String query = entityReport.getQuery()+" "+entityReport.getQueryOrderBy();
      String query = entityReport.getQuery()+" "+ entityReport.getQueryOrderBy();
      
         	    

      sqlForPrintPreview = panelDataFilter.getSubquery(query,e);
      
      sqlForPrintPreview = sqlForPrintPreview+pdvno.getSubquery(sqlForPrintPreview); 
      System.out.println("PanelReportSettings.calculateSQLForPrintPreview ViewNOrder ENTITYREPORT_QUERY_TYPE_GROUP  original Band("+e+") "+entityReport.getQuery()+" "+entityReport.getQueryOrderBy());
      System.out.println("PanelReportSettings.calculateSQLForPrintPreview ViewNOrder ENTITYREPORT_QUERY_TYPE_GROUP  GROUP Band:"+e+" "+sqlForPrintPreview);	
//      entityReportBand[e].setSettedQuery(sqlForPrintPreview);
//      entityReportBand[e].setShowColumns(pdvno.getShowColumns());
      showColumns = pdvno.getShowColumns();
      showColumnsHeader = pdvno.getShowColumns();
      

  
     }
     else*/ //if(entityReportBand[e].getType()==ENTITYREPORT_QUERY_TYPE_MAIN)       //0
  //   {
      
       // String query = entityReport.getQuery()+" "+entityReport.getQueryOrderBy();
      //sqlForPrintPreview = panelDataFilter.getSubquery(query);//,e);
      //System.out.println("     PanelReportSettings.calculateSQLForPrintPreview    e"+e+"   sqlForPrintPreview:"+sqlForPrintPreview+"   query:"+query);
      
      
      if(e==0)
      {    // get the no of 1st band
          intNoOfRows=0;
      }
      else
      {   
            if(listPanelDataViewNOrder!=null && listPanelDataViewNOrder.size()>0)
            {   // get the no of the 2nd and 3rd band
                 PanelDataViewNOrder pdvnoForward = (PanelDataViewNOrder)listPanelDataViewNOrder.get(e-1);
                 intNoOfRows =  intNoOfRows + pdvnoForward.getFieldsTableRowCount();
            }
      }
      
      String strAllFields="";
      
      if(listPanelDataViewNOrder!=null && listPanelDataViewNOrder.size()>0)
      {
      
         PanelDataViewNOrder pdvno = (PanelDataViewNOrder)listPanelDataViewNOrder.get(e);
         strAllFields = pdvno.getSubquery("ORDER BY");//,intNoOfRows);
         
         showColumns = pdvno.getShowColumns();
         showColumnsHeader = pdvno.getShowColumns();
         
      }
      //System.out.println("  OOOOOO   PanelReportSettings.calculateSQLForPrintPreview  ViewNOrder      e:"+e+" ++ intNoOfRows:"+intNoOfRows+"       strAllFields:"+strAllFields+"        strQueryOrderBy:"+strQueryOrderBy+"        sqlForPrintPreview:"+sqlForPrintPreview);
      strQueryOrderBy = strQueryOrderBy + strAllFields;// it has 'order by' so add clause without 'order by'
       
      //strQueryOrderBy = queryReturn + queryReturn;//strQueryOrderBy +  pdvno.getSubquery(sqlForPrintPreview);       
      
      

           	      
      
  }
      // getSubquery gets only the orderby subquery
      //System.out.println("PanelReportSettings.calculateSQLForPrintPreview ViewNOrder "+sqlForPrintPreview);
//         PanelDataViewNOrder pdvno = (PanelDataViewNOrder)listPanelDataViewNOrder.get(0);
//        String query = entityReport.getQuery()+" "+entityReport.getQueryOrderBy();
     
     
          if(uString.hasQueryOrderby(strQueryOrderBy))//strQueryOrderBy)))////strQueryOrderBy))
          {
          	 queryReturn=strQueryOrderBy;//pdvno.getSubquery(sqlForPrintPreview);
          }
          else
          {      
              if(strQueryOrderBy.startsWith(","))
              {
                  strQueryOrderBy = strQueryOrderBy.substring(1);
                 queryReturn = " ORDER BY "+strQueryOrderBy;//pdvno.getSubquery(sqlForPrintPreview);
              }
              
          }   

        String query = entityReport.getQuery()+" "+entityReport.getQueryOrderBy();
      sqlForPrintPreview = panelDataFilter.getSubquery(query);          
          
      sqlForPrintPreview = sqlForPrintPreview+queryReturn; 
          if(VariablesGlobal.globalShowReportSQL)
          {
     System.out.println("PanelReportSettings.calculateSQLForPrintPreview ViewNOrder -- - - -  -- -- -- - - - - - orderby queryReturn:"+queryReturn+"  sqlForPrintPreview:"+sqlForPrintPreview);
          }

       //------------------------------     --------------------------------------
        setSelectedPageSize();
    	setSelectedPageOrientation();

       /*showLines= new boolean[2];
       showLines[0]=chkShowVerticalLines.isSelected();
       showLines[1]=chkShowHorizontalLines.isSelected();*/
   }
   
   /*
   * first run calculateSQLForPrintPreview !!
   */
   public String getSqlForPrintPreview()
   {
               return sqlForPrintPreview;
   }
   
   
   // called by PanelReportSettingsPreview
   public boolean getIsDotmatrix()
   {
       //System.out.println("panelReportSettings.getIsDotmatrix()     oo    isDotmatrix:"+isDotmatrix);
   	  return isDotmatrix;
   }
   public String getStrPageSizeDotmatrix()
   {
   	  return strPageSizeDotmatrix;
   }   
   public String getStrPortDotmatrix()
   {
   	  return strPortDotmatrix;
   }  
   	
   public int getDotmatrixCpi()
   {
   	  //System.out.println("PanelReportSettings dotmatrixCpi "+dotmatrixCpi);
   	  return dotmatrixCpi;
   }     	

    public String getViewType()
   {
   	  return viewType;
   }

   public EntityReportBand getEntityReportBand(int band) 
   {
//System.out.println("PanelReportSettings.getEntityReportGroup A --"+group+"--  entityReportBand type:"+entityReportBand[group].getType());       
       EntityReportBand ret=null;
       boolean[] boolSettings = entityReportBand[band].getBoolSettings();
      
      if(boolSettings!=null) 
      {
      } // if
      else
      {
          boolSettings = new boolean[4];
      }

      if(!viewType.equalsIgnoreCase("FORM"))
      {
         JCheckBox chkHor =  (JCheckBox)listChkShowHorizontalLines.get(band);
        if(chkHor.isSelected())
        {       boolSettings[0]=true;       }
        else
        {       boolSettings[0]=false;      }      
         JCheckBox chkVer =  (JCheckBox)listChkShowVerticalLines.get(band);
        if(chkVer.isSelected())
        {       boolSettings[1]=true;       }
        else
        {       boolSettings[1]=false;      }
         JCheckBox chkShowZero =  (JCheckBox)listChkShowZero.get(band);
        if(chkShowZero.isSelected())
        {       boolSettings[2]=true;       }
        else
        {       boolSettings[2]=false;      }
         /*JCheckBox chkColWithoutData =  (JCheckBox)listChkShowColumnWithoutData.get(group);
        if(chkColWithoutData.isSelected())
        {       boolSettings[3]=true;       }
        else
        {       boolSettings[3]=false;      } */       
        //System.out.println("PanelReportSettings.getEntityReportGroups  =="+group+"==  "+boolSettings[0]+"   "+boolSettings[1]);
      }
      
       //System.out.println("PanelReportSettings.getEntityReportGroup   >--"+group+"--    boolSettings:"+boolSettings+"   entityReportBand type:"+ret.getType());       
        entityReportBand[band].setBoolSettings(boolSettings);
        ret=entityReportBand[band];       
       return ret;
   }
    
    
     //called by PanelReportSettingsPreview.showPanel       
   public EntityReportBand[] getEntityReportBands()
   {
       //System.out.println("PanelReportSettings.getEntityReportGroups  ----  entityReportBand type:"+entityReportBand[0].getType());

   	EntityReportBand[] erb = new EntityReportBand[entityReportBand.length];
       
       for(int g=0;g<entityReportBand.length;g++)
       {
           erb[g]=getEntityReportBand(g);
           //System.out.println("PanelReportSettings.getEntityReportGroups  ==="+g+"===  entityReportBand.length"+entityReportBand.length+"  horizontal:"+erg[g].getBoolSettings()[0]+"   vertical:"+erg[g].getBoolSettings()[1]);  
       }
  
       //System.out.println("PanelReportSettings.getEntityReportGroup  ----O-----  entityReportBand type:"+erg[0].getType());       
       
   	  return erb;
   } 
   
   public String getEntityName()
   {
       return entityReport.getName();
   }
   
       	// called by PanelReportSettingsPreview
   public String getTitle()
   {
   	  return txtTitleToPrint.getText();
   }
       	// called by PanelReportSettingsPreview
   public String getSubTitle()
   {
   	  return txtSubTitleToPrint.getText();
   } 
   public String[] getArrayStringsToBePrinted()
   {
   	 String[] ret = new String[1];
   	 ret[0] = txtBtnDate.getText();
   	 //System.out.println("PanelReportSettings.getArrayStringsToBePrinted "+ret[0]);

     return ret;
   }
   public boolean getShowSummaryPage()
   {
   	  return showSummaryPage;
   }  
     
   /**
    * 
    * called by this.composeAndGetReportPrefsXML().
    */
    private int[] getShowColumns(int group)
    {
    	PanelDataViewNOrder panelDataViewNOrder2 = (PanelDataViewNOrder)listPanelDataViewNOrder.get(group);
    	
    	panelDataViewNOrder2.getSubquery("");//,0);
    	System.out.println("----------* PanelReportSettings.getShowColumns  ---  ("+group+") "+panelDataViewNOrder2.getShowColumns().length);
    	return panelDataViewNOrder2.getShowColumns();
    }   
    
    public ArrayList getShowColumnsPerBand()
    {
        ArrayList listShowCols = new ArrayList();
        for(int e = 0;e<entityReportBand.length;e++)
        {
    	    PanelDataViewNOrder panelDataViewNOrder2 = (PanelDataViewNOrder)listPanelDataViewNOrder.get(e);
    	
    	    panelDataViewNOrder2.getSubquery("");//,0);
    	    //System.out.println("----------* PanelReportSettings.getShowColumnsPerBand  ---  band:("+e+") "+panelDataViewNOrder2.getShowColumns().length);
    	    listShowCols.add(panelDataViewNOrder2.getShowColumns());
        }
        return listShowCols;
    }       
    
    
    
    	// called by 
   public int[] getShowColumns()
   {
   	  //System.out.println("PanelReportSettings.getShowColumns()      ----+o+------    showColumns:"+showColumns);
   	  return showColumns;
   }
   
   public int[] getShowColumnsHeader()
   {
   	 //System.out.println("PanelReportSettings.getShowColumns "+showColumnsHeader);
   	  return showColumnsHeader;
   }    
   
   public PageFormat getPageFormat()
   {    
   	  return pageFormat;
   }                 
   /*public boolean[] getShowLines()
   {
   	  return showLines;
   } */
   
   public EntityFilterSettings[] getEntityFilterSettings()
   {
   	  // setting that are displayed in the first page of each report
   	  EntityFilterSettings[] entityFilterSettingsNew;
   	  if(entityFilterSettings==null)
   	  {
   	  	 entityFilterSettingsNew = null;
   	  }
   	  else
   	  {
   	  	
       ArrayList listEntSettings = new ArrayList();
       
       //System.out.println("PanelReportSettings.entityFilterSettings ++ "+entityFilterSettings.length);
       for(int e=0;e<entityFilterSettings.length;e++)
       {
       	  if(panelDataFilter.getFilterSettingHasValue(e))
       	  {
       	  	//System.out.println("PanelReportSettings.getEntityFilterSettings[e] "+e);
       	  	listEntSettings.add(entityFilterSettings[e]);
       	  }
       }
       
       entityFilterSettingsNew = new EntityFilterSettings[listEntSettings.size()];
       for(int e=0;e< listEntSettings.size();e++)
       {
       	  entityFilterSettingsNew[e]=(EntityFilterSettings)listEntSettings.get(e);
       }   	  
   	  }
   	  
   	  return entityFilterSettingsNew;
   }      
   /*private void removeKeyStrokeBinding()
   {
   	 KeyStroke pgDown = KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN,0);
   	 
       for(int c=0;c<orderComboboxes;c++)
       {
       	 System.out.println("removeKeyStrokeBinding "+c);
       	  JComboBox cmbOrder =(JComboBox)fieldOrder.get(c);
   	      InputMap map =cmbOrder.getInputMap();
   	      map.remove(pgDown);
   	   }
   	 
   	 
   }*/
   
   
   
   /* static Runnable heavyRunnable = new Runnable()
    { 
        public void run()
        {                              // (JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this)
            ProgressMonitor monitor = ProgressUtil.createModalProgressMonitor(btnPrintPreview, 100, false, 1000); 
            monitor.start("Fetching records from database..."); 
            try
            { 
                for(int i=0; i<10; i+=1)
                { 
                    fetchRecord(i); 
                    monitor.setCurrent("Fetching record "+(i+1)+" from database", (i+1)*10); 
                } 
            }
            finally
            { 
                // to ensure that progress dlg is closed in case of any exception 
                if(monitor.getCurrent()!=monitor.getTotal()) 
                    monitor.setCurrent(null, monitor.getTotal()); 
            } 
            heavyAction.setEnabled(true); 
        } 
 
        private void fetchRecord(int index)
        { 
            try{ 
                Thread.sleep(1000); 
            } catch(InterruptedException e){ 
                e.printStackTrace(); 
            } 
        } 
    }; 
 
    static Action heavyAction = new AbstractAction("Databse Query")
    { 
        public void actionPerformed(ActionEvent e)
        { 
            setEnabled(false); 
            new Thread(heavyRunnable).start(); 
        } 
    };*/
  
   
   /*
   *
   *  return is for panelReportSettingsPreview.showPanelForm
   */
    public PageFormat displayDialogPageSetup()
    {
    	setSelectedPageSize();
    	setSelectedPageOrientation();
    	
       printerJob = PrinterJob.getPrinterJob();
      // pageFormat = printerJob.defaultPage();
      return pageFormat = printerJob.pageDialog(pageFormat);
       
    }
    
    /**
     * 
     * called by PanelReportSettingPreview.showPanel().
     */
    public String getSelectedPageSizeLaser()
    {
        String pageSize = (String)cmbPageSize.getSelectedItem();
        return pageSize;
    }        


    /**
     * 
     * called by PanelReportSettingPreview.showPanel().
     */
    public boolean getSelectedPageOrientationIsPortraitLaser()
    {
        boolean isPortrait = (boolean)radioPortrait.isSelected();
        return isPortrait;
    }     
    
    
  private void setSelectedPageSize()
  {
             //JComboBox cbOr = (JComboBox)e.getSource();
             String pageSize = (String)cmbPageSize.getSelectedItem();
            //System.out.println("PanelReportSettings.setSelectedPageSize   = ==  ==   ==  pageSize:"+pageSize);
             
             if (pageSize.equalsIgnoreCase(STRPAGESIZE_A5))
             {    
                  Paper p = new Paper() ;
                  p.setSize(420.94488,595.27559);// in points
                  p.setImageableArea(28.34650, 28.34650, 364.25188, 524.40934);  
                  //(double x, double y, double width, double height)
                  pageFormat.setPaper(p);

             }             
             else if (pageSize.equalsIgnoreCase(STRPAGESIZE_A4))
             {    
                  Paper p = new Paper() ;
                  p.setSize(595.27559,841.88976);// in points
                  p.setImageableArea(28.34650, 28.34650, 538.58259, 771.02351);  
                  //(double x, double y, double width, double height)
                  pageFormat.setPaper(p);

             }
             else if (pageSize.equalsIgnoreCase(STRPAGESIZE_B4))
             {    
                  Paper p = new Paper() ;	
                  p.setSize(708.66250,1000.63145);// in points
                  p.setImageableArea(28.34650, 28.34650, 651.96950, 929.76520);  
                  //(double x, double y, double width, double height)
                  pageFormat.setPaper(p);

             }             
             else if (pageSize.equalsIgnoreCase(STRPAGESIZE_A3))
             {
             	   Paper p = new Paper() ;
             	   p.setSize(841.88976,1190.5512);
                   p.setImageableArea(28.34650, 28.34650, 785.19676, 1119.68495);             	   
             	   pageFormat.setPaper(p);      
             }
             else
             {
                 System.out.println("error  PanelReportSettings.setSelectedPageSize UNKNOWN pageSize:"+pageSize);
             }
              
           //Sets the width and height of this Paper object, which represents the properties of the page 
           //onto which printing occurs. The dimensions are supplied in 1/72nds of an inch. 
  }
  

   private void setSelectedPageOrientation( )
  {
  	
  	boolean isPortrait = (boolean)radioPortrait.isSelected();
  	//System.out.println("PanelReportSettings.setSelectedPageOrientation   = ======  isPortrait:"+isPortrait);
  	if(isPortrait)
  	{
  		pageFormat.setOrientation(PageFormat.PORTRAIT);
  	}
  	else 
  	{
  		pageFormat.setOrientation(PageFormat.LANDSCAPE);  
  	}
    
  }
  

  private void setSelectedPageSizeDotmatrix()
  {
  	
    strPageSizeDotmatrix=(String)cmbPageSizeDotmatrix.getSelectedItem();
    //System.out.println("PanelReportSettings.setSelectedPageSizeDotmatrix-"+strPageSizeDotmatrix+"-");
  }   
   
  private void setSelectedPortDotmatrix()
  {
              
     strPortDotmatrix=(String)cmbPortDotmatrix.getSelectedItem();
  }


  private void setSelectedCpiDotmatrix()
  {
  	int selIdx = (int)cmbCpiDotmatrix.getSelectedIndex();
  	String sel = (String)cmbCpiDotmatrix.getSelectedItem();
              
              if(selIdx!=0)
              {
              	dotmatrixCpi=Integer.parseInt(sel);
              }
              else
              {
              	dotmatrixCpi=-1;
              }
              
     
  }

 
   private void displayDialogExportToFileSettings()
   {
   	  
   	 /* int colCount = tableModel.getColumnCount();
      String[] colNames = tableModel.getTableColumnRealNames();
      Class[] colClass = tableModel.getTableColumnsClass();
      Vector data = tableModel.getTableDataVector();
      
      ExportToFile exportToFile = new ExportToFile();

      exportToFile.exportTo(colCount,colNames,colClass,data,type,parent,strOfMany); */

    	
    	DialogMulti dlg = new DialogMulti(parent);
        dlg.setEntity(panelExportToFileSettings,PANEL_TYPE_ANY, "ρυθμίσεις εξαγωγής",false);
        dlg.display();
             
     //JOptionPane.showMessageDialog(null, "type '"+type+"' not implemented yet \n dir: "+txtBtnDirSave.getText(), "attention",JOptionPane.INFORMATION_MESSAGE);
  

  }

   // exists in PanelODMRData and PanelReportSettings
   /*private void exportTo(String type)
   {//same as PanelStatistics.exportTo

    
     
     JOptionPane.showMessageDialog(null, "type '"+type+"' not implemented yet \n dir: "+txtBtnDirSave.getText(), "attention",JOptionPane.INFORMATION_MESSAGE);
  
  
   }*/

   // this allows outside classes to add actionlisteners
    public void addActionListener(ActionListener al)
    {
      /*for (JButton button : buttons)
      {
        button.addActionListener(al);
      }*/
      
      btnPrintPreview.addActionListener(al);
    }
    
    
   public void closeDB()
   {  
       db.releaseConnectionRs();
       db.releaseConnectionRsmd();
   }    
    
   public void closePanel()
   {  
        closeDB();
   }
 
    /*private void displayDialogFormDesign()
    {
        
        String strFormTemplateFileName = entityReport.getName();//+TEMPLATE_REPORTFORMFILETYPE; // .zip is added later
                
        
    /*    PanelReportFormDesign panelReportFormDesign = new PanelReportFormDesign(parent);
        String fileTemplateForm = VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+DIR_REPORTDOCUMENTTEMPLATE+VariablesGlobal.globalSystemDirectorySymbol+strFormTemplateFileName;//"farmerapp";
        //String fileTemplateLaser = VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+"reports"+VariablesGlobal.globalSystemDirectorySymbol+"laserfarmerapp.xml";
        
        //String query = entityReportBand[0].getQuery()+" "+entityReportBand[0].getQueryOrderBy();
        
        panelReportFormDesign.setEntity(fileTemplateForm, entityReport);
     */
   /*     JxPanel panelEmpty = new JxPanel();
    
        DialogMulti dlg = new DialogMulti(parent);
        dlg.setEntity(panelEmpty,PANEL_TYPE_ANY, "σχεδιασμός φόρμας εκτύπωσης",true);
        dlg.display();        
    }*/
    
    /*private void displayDialogFormData()
    {
        PanelReportFormData panelReportFormData = new PanelReportFormData(parent);
        panelReportFormData.setEntity();
          
        DialogMulti dlg = new DialogMulti(parent);
        dlg.setEntity(panelReportFormData,PANEL_TYPE_ANY, "δεδομένα φόρμας εκτύπωσης",true);
        dlg.display();          
    } */   
  
    /*private void displayFormParameters()
    {
          PanelReportFormParameters panelReportFormParameters = new PanelReportFormParameters(parent);
          panelReportFormParameters.setEntity();
          
        DialogMulti dlg = new DialogMulti(parent);
        dlg.setEntity(panelReportFormParameters,PANEL_TYPE_ANY, "παράμετροι φόρμας",true);
        dlg.display();
          //popupMenuFormParameters.show(btnFormParameters, 0, btnFormParameters.getY()+ btnFormParameters.getHeight());
    }*/
    

    
      //apply report design. called from setEntity

    private void chooseReportDesign(String reportdesign)
    {
    	//System.out.println("PanelReportSettings.chooseReportDesign "+reportdesign);
    	XMLReader reader = new XMLReader();
    	String[] tagElements ={"reportEntity","reportEntity","reportTitle","reportSubTitle","intShowDate","intShowCompanyTitle","intPrintFilters"};
        String[] tagElementsType ={"String","String","String","String","int","int","int"};
    	

   
   
    	
      String nodes1 = reader.getValueFromXmlElement(filePrefs,"Report",tagElements,tagElementsType,1,reportdesign);
      String nodes2 = reader.getValueFromXmlElement(filePrefs,"Report",tagElements,tagElementsType,2,reportdesign);
      String nodes3 = reader.getValueFromXmlElement(filePrefs,"Report",tagElements,tagElementsType,3,reportdesign);
      String nodes4 = reader.getValueFromXmlElement(filePrefs,"Report",tagElements,tagElementsType,4,reportdesign);
    	String nodes5 = reader.getValueFromXmlElement(filePrefs,"Report",tagElements,tagElementsType,5,reportdesign);
    	String nodes6 = reader.getValueFromXmlElement(filePrefs,"Report",tagElements,tagElementsType,6,reportdesign);
    	//String nodes7 = reader.getValueFromXmlElement(filePrefs,"Report",tagElements,tagElementsType,7,reportdesign);
    	//String nodes8 = reader.getValueFromXmlElement(filePrefs,"Report",tagElements,tagElementsType,8,reportdesign);
    	//System.out.println("PanelReportSettings.chooseReportDesign "+reportdesign+" "+entityReport.getName()+" "+nodes1+" "+nodes2+" "+nodes3+" "+nodes4);
         
        
        txtTitleToPrint.setText(nodes2);
        txtSubTitleToPrint.setText(nodes3);
       //String node2 = reader.getValueFromXmlElement(filePrefs,"Report"+entityReportBand.length,tagElements,tagElementsType,2,reportdesign);

        //System.out.println("PanelReportSettings.chooseReportDesign title"+node2+" - "+reportdesign+" ("+entityReportBand.length+")");





        		


       
        cmbPrintingDate.setSelectedIndex(Integer.parseInt(nodes4));
        cmbPrintCompanyTitle.setSelectedIndex(Integer.parseInt(nodes5));
        
        //cmbPrintCompanyInfo.setSelectedIndex(Integer.parseInt(nodes6));
        cmbPrintFilterInfo.setSelectedIndex(Integer.parseInt(nodes6));
       // cmbPrintCompanyInfo
        
        
        
        
         
        int noOfGroups = listPanelDataViewNOrder.size();
        
        for(int g =0 ;g<noOfGroups;g++)
        {
        	//System.out.println("PanelReportSettings.chooseReportDesign ->  "+g+" "+nodes1+" "+reportdesign);
        	 chooseReportDesignGroup(g,nodes1,reportdesign);
        }
       
       
    }
    
    //called by chooseReportDesign
    private void chooseReportDesignGroup(int group, String node, String reportdesign)    
    {
    	
  	/*  XMLReader reader = new XMLReader();
   String[] tagElements ={"reportEntity","reportEntity"+"showColumns"+group,"intsOrderBy"+group,"boolsOrderByAsc"+group,"boolsOrderByDesc"+group };
   String[] tagElementsType ={"String","String","String","int", "int","Boolean","Boolean"}; 	
   	*/
   	     
    	//System.out.println("PanelReportSettings.getShowColumns "+group+" "+panelDataViewNOrder1.getShowColumns());
    	//panelDataViewNOrder1.getSubquery("");
    	//panelDataViewNOrder1.getShowColumns();
   
    //System.out.println("PanelReportSettings.chooseReportDesignGroup "+group+" "+panelDataViewNOrder1.getShowColumns()+" "+reportdesign+" "+node);
    
    //String nodeEntity = reader.getValueFromXmlElement(filePrefs,"Report",tagElements,tagElementsType,0,node);
    //System.out.println("chooseReportDesignGroup.PanelReportSettings"+node+" "+nodeEntity);
    //if(nodeEntity.equalsIgnoreCase(node))
     //if(reader.checkIfElementExists(filePrefs, "Report",tagElements,tagElementsType,node))
     //panelDataViewNOrder1.getSubquery("");
    //System.out.println("PanelReportSettings.chooseReportDesignGroup "+node+"="+reportdesign+" "+query);
    
     
     
     PanelDataViewNOrder panelDataViewNOrder1 = (PanelDataViewNOrder)listPanelDataViewNOrder.get(group);
     
     //System.out.println("PanelReportSettings.chooseReportDesignGroup reportdesign"+ reportdesign+" node"+node+" group"+group);

      if(reportdesign.equalsIgnoreCase(node))
      {
       //System.out.println("PanelReportSettings.chooseReportDesignGroup"+tagElements[1]+" "+tagElements[2]+" "+tagElements[3]);         
       /*  String nodes2 = reader.getValueFromXmlElement(filePrefs,"Report",tagElements,tagElementsType,2,reportdesign);
         String nodes3 = reader.getValueFromXmlElement(filePrefs,"Report",tagElements,tagElementsType,3,reportdesign);
         String nodes4 = reader.getValueFromXmlElement(filePrefs,"Report",tagElements,tagElementsType,4,reportdesign);
         String nodes5 = reader.getValueFromXmlElement(filePrefs,"Report",tagElements,tagElementsType,5,reportdesign);
         */
       
       
        // load file for intsShowColumns ------------------------------------
          String[] tagElements1 ={"reportEntity","showColumns"+group};
          String[] tagElementsType1 ={"String", "String"};
          //	System.out.println("PanelReportSettings 1---- "+group+" tagElements1"+tagElements1[0]+" tagElements1"+tagElements1[1]+" reportdesign"+reportdesign);
         int[] fieldsShowColumnsNew = utilsPanelReport.loadDataFromXmlFileRetIntArray(filePrefs, "Report",tagElements1,tagElementsType1,1,reportdesign); 
          //  System.out.println("PanelReportSettings +++"+fieldsShowColumnsNew.length+" "+reportdesign);
     	// load file for intsOrderBy ------------------------------------
          String[] tagElements2 ={"reportEntity","intsOrderBy"+group};
          String[] tagElementsType2 ={"String", "String"};
          //System.out.println("PanelReportSettings 2- "+group+" "+reportdesign);
         int[] fieldsOrderByNew = utilsPanelReport.loadDataFromXmlFileRetIntArray(filePrefs, "Report",tagElements2,tagElementsType2,1,reportdesign); 
        //System.out.println("PanelReportSettings --"+fieldsOrderByNew.length+"  "+fieldsOrderByNew[0]);
        // load file for intsOrderByAsc  ----------------------------------------------
          String[] tagElements3 ={"reportEntity","boolsOrderByAsc"+group};
          String[] tagElementsType3 ={"String", "String"};
         boolean[]  fieldsOrderByAscNew = utilsPanelReport.loadDataFromXmlFileRetBool(filePrefs, "Report",tagElements3,tagElementsType3,1,reportdesign); 
         //System.out.println("PanelReportSettings "+fieldsOrderByAscNew.length);
        
         // load file for intsOrderByDesc   ---------------------------------------
          String[] tagElements4 ={"reportEntity","boolsOrderByDesc"+group};
          String[] tagElementsType4 ={"String", "String"};
          boolean[] fieldsOrderByDescNew = utilsPanelReport.loadDataFromXmlFileRetBool(filePrefs, "Report",tagElements4,tagElementsType4,1,reportdesign); 
            //System.out.println("PanelReportSettings "+fieldsOrderByDescNew.length);
  
         // load file for allBoolSettings   ---------------------------------------
          String[] tagElements5 ={"reportEntity","allBoolSettings"+group};
          String[] tagElementsType5 ={"String", "String"};
          boolean[] allBoolSettingsNew = utilsPanelReport.loadDataFromXmlFileRetBool(filePrefs, "Report",tagElements5,tagElementsType5,1,reportdesign);  
         //System.out.println("PanelReportSettings.chooseReportDesignGroup allBoolSettings  ("+group+")   "+allBoolSettingsNew.length);
       setAllBoolSettings(allBoolSettingsNew);
          //-----------------------------------------  
            
//          String q = entityReportBand[group].getQuery()+" "+entityReportBand[group].getQueryOrderBy();                     
          //System.out.println("PanelReportSettings.chooseReportDesignGroup arrays >>> "+fieldsShowColumnsNew.length+" "+fieldsOrderByNew.length+" "+fieldsOrderByAscNew.length+" " +fieldsOrderByDescNew.length);
          //System.out.println("PanelReportSettings.chooseReportDesignGroup =("+group+") "+fieldsShowColumnsNew[group]+" "+fieldsOrderByNew[group]+" "+fieldsOrderByAscNew[group]+" " +fieldsOrderByDescNew[group]+" "+getColumnsForGroupViewNOrder(query).length);
          //System.out.println("PanelReportSettings.chooseReportDesignGroup - "+getColumnsForGroupViewNOrder(query)+" "+query);
          
          //panelDataViewNOrder1.setEntity(getColumnsForGroupViewNOrder(q), fieldsShowColumnsNew, null, fieldsOrderByNew,fieldsOrderByAscNew,fieldsOrderByDescNew,entityReportBandLength);
          
          //EntityDBFields[] entityDBFields = entityReportBand[group].getEntityDBFields();
          EntityReportBandField[] entityReportBandFields = entityReportBand[group].getEntityReportBandFields();
          panelDataViewNOrder1.setEntity(getColumnsForGroupViewNOrder(entityReportBandFields), fieldsShowColumnsNew, null, fieldsOrderByNew,fieldsOrderByAscNew,fieldsOrderByDescNew,entityReportBandLength,TYPE_OF_VIEWANDORDER_REPORT,"");

      //public void setEntity( RecColumn[] recColumnIn, int[] isFieldSelected,boolean[] fieldsEditable, 
      //int[] fieldsOrderby, boolean[] boolOrderByAsc, boolean[] boolOrderByDesc, int entityReportBandLength)
     	  
 //    	  fieldsOrderby=fieldsOrderByNew;//if there is nothing selected in initially in entitydata
     	  
     }
     else
     {
     	//get prefs from app
          //EntityDBFields[] entityDBFields = entityReportBand[group].getEntityDBFields();//.getQuery()
          EntityReportBandField[] entityReportBandFields = entityReportBand[group].getEntityReportBandFields();
     	  panelDataViewNOrder1.setEntity(getColumnsForGroupViewNOrder(entityReportBandFields), null, null, entityReportBand[group].getFieldsOrderby(),null,null,entityReportBandLength,TYPE_OF_VIEWANDORDER_REPORT,"");
     }
          
    }

    /*private void showMenuFormParameters()
    {
          popupMenuFormParameters.show(btnFormParameters, 0, btnFormParameters.getY()+ btnFormParameters.getHeight());
    }*/


  
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
        btnPrintPreview = new JButton();
        //btnExport = new JButtonListMenu();
        //    btnPrint = new JButton();
        //btnFormDesign = new JButton();
       // btnNewPrintDesign = new JButton();
       // btnPrintDesignSaveAs = new JButton();
        btnPrintDesignSave = new JButton();
        //cmbPrintDesign = new JComboBox();
        //btnPrintDesignDel = new JButton();

        /*JMenuItem mItemHtml = new JMenuItem("εξαγωγή σε αρχείο html");
        JMenuItem mItemPdf = new JMenuItem("εξαγωγή σε αρχείο txt");
        JMenuItem mItemExcel = new JMenuItem("εξαγωγή σε αρχείο excel");*/
        	
        //mItemFormParametersData =  new JMenuItem("δεδομένα φόρμας");
        //mItemFormParametersDesign  =  new JMenuItem("σχεδιασμός φόρμας");
        	
        //setFloatable(false);
        //setRollover(true);
        //this.setOpaque(false);

        btnPrintPreview.setText("<html>προεπισκόπηση εκτύπωσης <b>F7</b></html>");
        btnPrintPreview.setOpaque(false);
        //btnPrintPreview.setText("<html>προεπισκόπηση <b>O</b></html>");	    
       // btnPrintPreview.setText("<html>προεπισκόπηση <b>alt+O</b></html>");
        btnPrintPreview.setToolTipText("προεπισκόπηση εκτύπωσης");
        btnPrintPreview.setIcon(ICO_PRINT_PREVIEW16);
        //btnPrintPreview.setMnemonic(KeyEvent.VK_O);
        btnPrintPreview.setFocusable(false);
        btnPrintPreview.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	 
	          //saveAsReportPrefs(); 
	        }
	    });
        Action actionPrintPreview = new ActionPrintPreview();
        btnPrintPreview.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), "report"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnPrintPreview.getActionMap().put("report", actionPrintPreview);



        /*btnExport.setText("<html>εξαγωγή <b>F2</b></html>");
        btnExport.setOpaque(false);
        //btnExport.setText("<html>εξαγωγή <b>Ξ</b></html>");
        btnExport.setToolTipText("εξαγωγή σε αρχείο");
        btnExport.setIcon(ICO_EXPORT16);
        btnExport.setFocusable(false);
        btnExport.setMnemonic(KeyEvent.VK_J);
        btnExport.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	 showMenuExport();
	        }
	    });
        Action actionExport = new ActionExport();
        btnExport.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F2"), "export"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnExport.getActionMap().put("export", actionExport);
    	
    	UtilsMiscEntities utilsMiscEntities = new UtilsMiscEntities();
    	 entityExportFileType = utilsMiscEntities.getExportToFileEntities();    	
    	
    	String[] strExportFileType = new String[entityExportFileType.length];
    	for(int i=0;i<entityExportFileType.length;i++)
    	{
    		strExportFileType[i]=entityExportFileType[i].getCaption() +" ("+entityExportFileType[i].getExtension() +")";
    	   JMenuItem mItemExport = new JMenuItem("εξαγωγή σε αρχείο "+strExportFileType[i]);
    	   final int iFinal = i;
           mItemExport.addActionListener(new ActionListener()
           {
	           public void actionPerformed(ActionEvent e) 
	           {	   exportTo((String)entityExportFileType[iFinal].getName());  }
	       });    	   
    	   popupMenuExport.add(mItemExport);
    	}   */
    		        

               
               
       /* btnFormDesign.setText("<html>σχεδιασμός φόρμας</html>");
        btnFormDesign.setOpaque(false);
        //btnFormParameters.setText("<html>προεπισκόπηση <b>O</b></html>");	    
       // btnFormParameters.setText("<html>προεπισκόπηση <b>alt+O</b></html>");
        //btnFormParameters.setToolTipText("προεπισκόπηση εκτύπωσης");
        btnFormDesign.setIcon(ICO_REPORTFORMSETTINGS);
        //btnFormParameters.setMnemonic(KeyEvent.VK_O);
        btnFormDesign.setFocusable(false);
        btnFormDesign.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	 
	           displayDialogFormDesign();
	        }
	    });
	 */
       
       
        /*popupMenuFormParameters.add( mItemFormParametersData);
        popupMenuFormParameters.add( mItemFormParametersDesign);
        mItemFormParametersData.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	  displayDialogFormData() ;  }
	    });

        mItemFormParametersDesign.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   displayDialogFormDesign();  }
	    });     	    
	    /*
        Action actionPrintPreview = new ActionPrintPreview();
        btnFormParameters.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), "report"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnFormParameters.getActionMap().put("report", actionPrintPreview);*/


  /*      btnPrint.setText("<html><b>E</b>κτύπωση</html>");
        btnPrint.setOpaque(false);
        btnPrint.setToolTipText("εκτύπωση");
        btnPrint.setIcon(ICO_PRINT16);
        btnPrint.setMnemonic(KeyEvent.VK_E);
        btnPrint.setFocusable(false);
        btnPrint.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   displaySettingsForPrint(selectedLine);  }
	    });*/
        /*Action actionPrintPreview = new ActionPrintPreview();
        btnPrintPreview.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), "report"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnPrintPreview.getActionMap().put("report", actionPrintPreview);*/

     /*  cmbPrintDesign.addItem("προεπιλογή");
       cmbPrintDesign.setSelectedIndex(0);
       cmbPrintDesign.addActionListener (new  ActionListener() 
       {

        public void actionPerformed(ActionEvent e)
        {
        JComboBox cb = (JComboBox)e.getSource();
        String reportdesign = (String)cb.getSelectedItem();
        //System.out.println("ToolBarData.initialize "+reportdesign);
        chooseReportDesign(reportdesign);
        }
       });


        btnNewPrintDesign.setText("<html><b>ν</b>έο σχέδιο</html>");
        btnNewPrintDesign.setOpaque(false);
        btnNewPrintDesign.setToolTipText("νέο σχέδιο εκτύπωσης");
        btnNewPrintDesign.setIcon(ICO_INSERT16);
        btnNewPrintDesign.setMnemonic(KeyEvent.VK_E);
        btnNewPrintDesign.setFocusable(false);
        btnNewPrintDesign.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	     }
	    });
        /*Action actionPrintPreview = new ActionPrintPreview();
        btnPrintPreview.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), "report"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnPrintPreview.getActionMap().put("report", actionPrintPreview);*/

       /* btnPrintDesignSaveAs.setText("<html>αποθήκευση ως</html>");
        btnPrintDesignSaveAs.setOpaque(false);
        btnPrintDesignSaveAs.setToolTipText("αποθήκευση σχεδίου ως");
        btnPrintDesignSaveAs.setIcon(ICO_UPDATE16);
        //btnPrintDesignSaveAs.setMnemonic(KeyEvent.VK_E);
        btnPrintDesignSaveAs.setFocusable(false);
        btnPrintDesignSaveAs.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	saveAsReportPrefs();    }
	    });
        /*Action actionPrintPreview = new ActionPrintPreview();
        btnPrintPreview.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), "report"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnPrintPreview.getActionMap().put("report", actionPrintPreview);*/
  
  
        btnPrintDesignSave.setText("<html>αποθήκευση</html>");
        btnPrintDesignSave.setOpaque(false);
        btnPrintDesignSave.setToolTipText("αποθήκευση σχεδίου");
        btnPrintDesignSave.setIcon(ICO_UPDATE16);
        //btnPrintDesignSave.setMnemonic(KeyEvent.VK_E);
        btnPrintDesignSave.setFocusable(false);
        btnPrintDesignSave.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	saveReportPrefs();
	        }
	    });
        /*Action actionPrintPreview = new ActionPrintPreview();
        btnPrintPreview.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), "report"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnPrintPreview.getActionMap().put("report", actionPrintPreview);*/  
        
       // cmbPrintDesign.setFocusable(false);
        

        /*btnPrintDesignDel.setText("<html>διαγραφή</html>");
        btnPrintDesignDel.setOpaque(false);
        btnPrintDesignDel.setToolTipText("διαγραφή σχεδίου");
        btnPrintDesignDel.setIcon(ICO_DELETE16);
        //btnPrintDesignSave.setMnemonic(KeyEvent.VK_E);
        btnPrintDesignDel.setFocusable(false);
        btnPrintDesignDel.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	    }
	    });  */
        /*Action actionPrintPreview = new ActionPrintPreview();
        btnPrintPreview.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), "report"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnPrintPreview.getActionMap().put("report", actionPrintPreview);*/  

        
         //IconSeparator icoSeparator = new IconSeparator();
        
         //JLabel lblIcoSeparator1 =new JLabel();
         //JLabel lblIcoSeparator2 =new JLabel();
         //JLabel lblIcoSeparator3 =new JLabel();

        
        //lblIcoSeparator1.setOpaque(false);
        //lblIcoSeparator.setHorizontalAlignment(SwingConstants.RIGHT);
         
         //lblIcoSeparator1.setIcon(icoSeparator);
         //lblIcoSeparator2.setIcon(icoSeparator);
         //lblIcoSeparator3.setIcon(icoSeparator);
          this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS)); 
        //add(lblIcoSeparator1);
        //addSeparator();
        add(btnPrintPreview);
        //add(btnExport);
       // add(btnPrint);
        //addSeparator();
        //add(lblIcoSeparator2);
        //add(btnFormDesign);
        //addSeparator();
        //add(cmbPrintDesign);
        //add(btnNewPrintDesign);
        //add(btnPrintDesignSaveAs);
 //       add(btnPrintDesignSave);
       // add(btnPrintDesignDel);
        //addSeparator();
        //add(lblIcoSeparator3);
        
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
  
   
   class  ActionPrintPreview extends AbstractAction                 
   {       
        public ActionPrintPreview()
        {      }
      	
    	public void actionPerformed(ActionEvent e)
      	{     
      	  btnPrintPreview.doClick();
      	}    	
    }                

 /*  class  ActionPrint extends AbstractAction                 
   {       
        public ActionPrint()
        {      }
      	
    	public void actionPerformed(ActionEvent e)
      	{      btnPrint.doClick();       }    	
    } */
   
   class  ActionClose extends AbstractAction                 
   {       
        public ActionClose()
        {      }
      	
    	public void actionPerformed(ActionEvent e)
      	{      btnClose.doClick();       }    	
    }                

   class  ActionSelectPrintingType extends AbstractAction                 
   {       
        public ActionSelectPrintingType()
        {      }
      	
    	public void actionPerformed(ActionEvent e)
      	{  
      	    String sel = (String)e.getActionCommand();
      	  
               CardLayout cl = (CardLayout)(cards.getLayout());
             cl.show(cards, (String)sel);
             selectedPrintingType = (String) sel;
          
           
            
            if (sel.equalsIgnoreCase(strPrintingTypeLaser))
            {
            	 isDotmatrix=false;
            }
            else if (sel.equalsIgnoreCase(strPrintingTypeDotMatrix))
            {
               isDotmatrix=true; 	
            }
            
            
            //System.out.println("PanelReportSettings  ActionSelectPrintingType  ===---===   isDotmatrix:"+isDotmatrix);
            
      	}
    	
    }

    class  ActionTabForward extends AbstractAction                 
    {       
        public ActionTabForward()
        {        }
    	public void actionPerformed(ActionEvent e)
      	{
      		int selTab = tabbedPane.getSelectedIndex();
      		if (selTab<tabbedPane.getTabCount()-1)
      		{
      		   tabbedPane.setSelectedIndex(selTab+1);
      		}
      		else
      		{
      			tabbedPane.setSelectedIndex(0);
      		}      	
      	}    	
    }                

    class  ActionTabBackward extends AbstractAction                 
    {       
        public ActionTabBackward()
        {        }
    	public void actionPerformed(ActionEvent e)
      	{
      		int selTab = tabbedPane.getSelectedIndex();
      		if (selTab==0)
      		{
      		   tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
      		}
      		else
      		{
      			tabbedPane.setSelectedIndex(selTab-1);
      		}      	
      	}    	
    } 

   /*class  ActionExport extends AbstractAction                 
   {     
        public ActionExport()
        {        }
    	public void actionPerformed(ActionEvent e)
      	{         btnExport.doClick();        }   	
    }*/ 
    

   /* public static void main(String args[])
    {
       /*PanelReportSettings rpt = new PanelReportSettings(null);
       EntityFilterSettings[] ers = new EntityFilterSettings[5];
       
       ers[0]=new EntityFilterSettings("νο αγρότη","lookup","int","fromto","farmerId","farmer");
       ers[1]=new EntityFilterSettings("επίθετο","","string","equals","surname","farmer");
       ers[2]=new EntityFilterSettings("ΑΦΜ","","string","equals","farmerAfm","farmer");
       ers[3]=new EntityFilterSettings("πόλη","table","string","or","townId","town");
       ers[4]=new EntityFilterSettings("ημερομηνία","","date","fromto","date","invoice");

      // ers[3]=new EntityFilterSettings("πόλη","string","equals","name","town");
       
       rpt.setEntity("invoice","","ODMR","","",null,"SELECT farmer.farmerId AS 'id',farmer.farmerAfm AS 'ΑΦΜ', farmer.surname AS 'επίθετο',farmer.name AS 'όνομα',invoice.* FROM invoice, "+
       "farmer WHERE invoice.farmerId = farmer.farmerId","παραστατικά αγροτών",null,null,"title","subtitle",true,ers, null, true) ;
       
       //rpt.setEntity("farmer","ODMR","","",null,"select * from farmer, town WHERE farmer.townId=town.townId",null,"farmers",true,ers);

       //rpt.setEntity("farmer","ODMR","","",null,"select * from farmer",null,"farmers",true);
       rpt.showDialog(true);*/
    
    //} 



}


