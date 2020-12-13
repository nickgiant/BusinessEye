package com.tool.gui;

import com.tool.rpt.PanelReportSettingsPreview;
import com.tool.model.EntityStatistics;
//import com.tool.guicomps.PanelReportSettingsAndPreview;
  import com.tool.guicomps.*;
import static com.tool.guicomps.Constants.ICO_CALENDAR;
import static com.tool.guicomps.Constants.KEYSTROKE_F_LOOKUP_SHOW;
import static com.tool.guicomps.Constants.LOOKUP_TYPE_DATE;
import static com.tool.guicomps.Constants.MONTH_END;
import static com.tool.guicomps.Constants.MONTH_START;
import static com.tool.guicomps.Constants.PANEL_FILTER_REPORT;
import static com.tool.guicomps.Constants.PANEL_FILTER_SEARCH;
  import com.tool.utils.*;
  import com.tool.model.*;
  import com.tool.jdbc.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.TableColumn;

//jdk6
import javax.swing.RowSorter;
import javax.swing.table.TableRowSorter;
//jdk6
import javax.swing.table.TableModel;
  import javax.swing.JDialog;
  import javax.swing.JLabel;
  import javax.swing.JPanel;
  import javax.swing.JTable;
  import javax.swing.table.DefaultTableModel;  
  import javax.swing.JFrame;
  import javax.swing.JButton;  
  import javax.swing.SwingUtilities;
  import javax.swing.KeyStroke;
  import javax.swing.Action;
  import javax.swing.AbstractAction;
  import javax.swing.KeyStroke;
  import javax.swing.JComponent;  
  import javax.swing.BorderFactory;
  import javax.swing.JScrollPane;
  import javax.swing.ListCellRenderer;
  import javax.swing.JList;
  import javax.swing.BoxLayout;
  import javax.swing.JComboBox;
  import javax.swing.JToolBar;
  import javax.swing.JMenuItem;
  import javax.swing.JPopupMenu;
  import javax.swing.ImageIcon;
    
  import java.sql.ResultSet;
  import java.sql.SQLException;
  
  import java.awt.event.ActionListener;
  import java.awt.event.ActionEvent;
  import java.awt.event.KeyEvent;  
  import java.awt.BorderLayout;
  import java.awt.FlowLayout;
  import java.awt.Dimension;
  import java.awt.Component;
  import java.awt.KeyboardFocusManager;
  import java.awt.Font;
  import java.awt.Color;
  import java.awt.Graphics;
  import java.awt.Graphics2D;
  import java.awt.GradientPaint;
  import java.awt.print.PrinterJob;
  import java.awt.print.PageFormat;
  import java.awt.Cursor;
import java.awt.Insets;
import java.time.LocalDate;

 
  import java.util.*;
import javax.swing.border.BevelBorder;
import javax.swing.text.JTextComponent;

  

  
 public class PanelStatistics extends JxPanel implements Constants
 {
 	private JPanelDecorated panelAllOnIt;
    private JLabel lblTitle;
    //private PanelGradient paneTitle;    
    private JPanel paneTitle;    
    private JLabel lblCompany;
    private JComboBox comboCompany;
    private JLabel lblDates;
    private JLabel lblBottom;
    private JButtonListMenu btnDatePeriodListMenu;
    private JTextBoxWithEditButtons txtdate;
    private JTextBoxWithEditButtons txtdate2;
    //private JTextField  txtFilterDates1; 		
    //private JTextField  txtFilterDates2; 
    private ArrayList fieldFilterTxts1;
    private ArrayList fieldFilterTxts2;
    private JxPanel panelTop;
    private JxPanel panelSelections;
    private JxPanel panelBottom;
    private JTableDec table;
    private JScrollPane scrollpaneTable;
    private TableModelReadOnly tableModel;
    private UtilsTable utilsTable;
    
    private ResultSet rs;
    private Database db;
    
    private Vector vectorCompany;
    //private Vector vectorYear;
    
    private String companyId;

    
    private String title;
    private String subTitle;
    private String querySelect;
    private String queryFrom;
    private String queryWhere;
    private String queryGroupBy;
    private String queryOrderBy;
    private boolean isFilterCompany;
    private String fieldCompanyIdName;
    //private String fieldCompanyValue;
    private boolean isFilterDates;
    private String fieldDatesName;
    //private String fieldYearValue;    
    private ToolBarData toolBarData;
    
    private boolean executeSQL;
    private String query;
    private ListSelectionModel listSelectionModel;
    private PanelReportSettingsPreview dlgPrintPreview;
    
        private JButton btnShowZoomIn;
        private JButton btnShowZoomOut;
        private JButton btnPrintPreview;

        
        /*private JMenuItem mItemHtml;
        private JMenuItem mItemPdf;
        private JMenuItem mItemExcel;*/
//        private JButton btnPreferences;


     //   private JButtonListMenu btnExport;        
   // private EntityExportFileType[] entityExportFileType;
   // private JPopupMenu popupMenuExport = new JPopupMenu(); // the only from toolbar

    private  UtilsPanelReport utilsPanelReport;
    private UtilsGui utilsGui;
    private String primKey;
    private String primKeyDb;
    private String entity;
    private String[] categoryNodes;
   	private EntityStatistics[] entityStatistics;
   	private ImageIcon icon;
   	private int level;    
   	private String primKeyValue;
   	private String parentTitle;
    private JFrame frame;
    private UtilsString utilsString;
    private String name;
    
    private String filePrefs;
    
    private EntityPanel[] drillEntityPanels;
    private String[] fieldsOnStatisticsTitle;
    private String[] fieldsOnStatisticsTitleCaption;
    
    private int[] fieldsShowColumns;
    private PanelManagement panelManagement;
    private UtilsDouble uDouble;
    private UtilsDate utilsDate;
    //private EntityReport entityReportForm;
    private String formGlobalTableToGet1;
    private String formGlobalTableToApply1;
    
     public PanelStatistics(JFrame frameIn)
    {
        try {
            initialize(frameIn);
        } catch (Exception e) {
           System.out.println("PanelStatistics init"+e.getMessage());
        // e.printStackTrace();
        }
       // pack();
    }


	private void initialize(JFrame frameIn) throws Exception
    {


        uDouble=VariablesGlobal.globalUtilsDouble;
        utilsDate = new UtilsDate();	
        utilsDate.closeDB();         
        utilsDate.readFromFileDateFormats();
        //uDouble=uDoubleIn;
        
    	filePrefs =VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+FILE_TABLEPREFERENCES;
    	
    	panelAllOnIt = new JPanelDecorated();
    	panelAllOnIt.setLayout(new BorderLayout());
    	
 	    this.setLayout(new BorderLayout());
 	    db= new Database();
    	 utilsPanelReport=new UtilsPanelReport();
    	 utilsGui = new UtilsGui();
    	 utilsString = new UtilsString();
         utilsTable=new UtilsTable();
         
    	 frame = frameIn;
    	
    	vectorCompany = new Vector();
    	
    	    	
    	//vectorYear = new Vector();
    	//retrieveYearList();
        fieldFilterTxts1 = new ArrayList();
        fieldFilterTxts2 = new ArrayList();
        
   

    	
       panelTop = new JxPanel();
       panelTop.setLayout(new BorderLayout());

       
       toolBarData = new ToolBarData();
       toolBarData.setFocusable(false);
       
       
       
       lblTitle = new JLabel();
       lblTitle.setOpaque(true);
       lblTitle.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

      //lblTitle.setBackground(this.getBackground().darker().darker().darker());//java.awt.SystemColor.activeCaption);
  //    lblTitle.setBackground(SystemColor.activeCaption);//panel.getBackground().brighter());              
  //    lblTitle.setForeground(this.getBackground().brighter().brighter());

        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD));
    //   lblTitle.setBackground(SystemColor.activeCaption);//panel.getBackground().brighter());        
        //lblTitle.setFont(new java.awt.Font("Century", 1, 16));
        lblTitle.setForeground(CLR_TITLE_CAPTION);//Color.white);
        lblTitle.setFocusable(false);
        lblTitle.setOpaque(false);
         //SystemColor.activeCaption
         //  transparent   new Color(0, 0, 0, 0)
        //paneTitle = new PanelGradient(CLR_TITLE_BACKGROUND_START,CLR_TITLE_BACKGROUND_END,16);//CLR_PANEL_END,CLR_PANEL_START,15);//(new Color(0, 0, 0, 0),this.getBackground().darker().darker().darker().darker(),26);//new PanelTitle(new Color(0, 0, 0, 0),this.getBackground().darker().darker().darker().darker(),Color.white,"title");
        paneTitle = new JPanel();
        paneTitle.add(lblTitle);
        
       //toolBarData = new ToolBarData();
       //toolBarData.setFocusable(false);

    	
    	panelSelections = new JxPanel();
        panelSelections.setLayout(new FlowLayout(FlowLayout.CENTER,5,1));
        //panelSelections.setAlignmentY(10);
        panelSelections.setPreferredSize(new Dimension(getWidth(), 22));

    	
        
        retrieveCompanyList();
        retrieveDatesList();
        
    	lblCompany = new JLabel("εταιρία") ;
    	
    	
    	comboCompany = new JComboBox(vectorCompany);
		comboCompany.setRenderer( new PanelRenderer(50) );
        comboCompany.addActionListener(new ActionListener()
        {       	
          public void actionPerformed(ActionEvent e)
          {
          	selectCompany(e);
          }
        });
        
    
    	lblDates = new JLabel("ημερομηνίες") ;
    	  
        JButton btnFilter = new JButton("φιλτράρισμα");
        btnFilter.addActionListener(new ActionListener()
        {       	
          public void actionPerformed(ActionEvent e)
          {
          	filter();
          }
        });

		panelSelections.add(lblCompany);
		panelSelections.add(comboCompany);
		panelSelections.add(lblDates);
                panelSelections.add(btnDatePeriodListMenu);
		panelSelections.add(txtdate);		
    	        panelSelections.add(new JLabel(" ως "));
                panelSelections.add(txtdate2);	
                panelSelections.add(btnFilter);	
                
    	panelTop.add(paneTitle, BorderLayout.LINE_START);
    	panelTop.add(toolBarData,BorderLayout.PAGE_START); 
    	panelTop.add(panelSelections, BorderLayout.PAGE_END);
    	
    	table=new JTableDec();
    	table.setEntity(false);
        table.setShowVerticalLines(true);        
        table.setShowHorizontalLines(true);
        table.setAutoResizeMode(JTableDec.AUTO_RESIZE_OFF);
        table.setRowSelectionAllowed(true);
        table.setGridColor(CLR_TABLE_GRID);
       
       new ActionTableFind().install(table);// tooltip find, enable with ctrl i
       
        // not delete it:  cannot format given object as a number
        TableCellRendererDefault tcr = new TableCellRendererDefault();
        TableCellRendererDouble tcrDouble = new TableCellRendererDouble(uDouble);
        //tcrDouble.getDoubleSettingsFromFile();        
        TableCellRendererInteger tcrInteger = new TableCellRendererInteger();
        TableCellRendererDate tcrDate = new TableCellRendererDate();
        tcrDate.pushUtilsDateToReadFromFile();        
        table.setDefaultRenderer(Object.class, tcr);
        table.setDefaultRenderer(Integer.class, tcrInteger);
        table.setDefaultRenderer(Number.class, tcrInteger);
        table.setDefaultRenderer(Double.class, tcrDouble); 
        table.setDefaultRenderer(java.util.Date.class, tcrDate); 
        
        //table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// select a single row only 
        scrollpaneTable = new JScrollPane();
        scrollpaneTable.setViewportView(table);
        //scrollpaneTable.setPreferredSize(new Dimension(700, 300));
    	
    	
    	panelBottom = new JxPanel();
        panelBottom.setLayout(new FlowLayout());
        panelBottom.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
    	
    	lblBottom=new JLabel();
    	lblBottom.setIcon(ICO_INFO16);
        lblBottom.setOpaque(false);
    	
    	
    	
    	
    	panelAllOnIt.add(panelTop, BorderLayout.PAGE_START);
    	panelAllOnIt.add(scrollpaneTable, BorderLayout.CENTER);
        panelAllOnIt.add(lblBottom, BorderLayout.PAGE_END);
            
        add(panelAllOnIt,BorderLayout.CENTER);
 
                      LocalDate a = LocalDate.parse(utilsDate.reformatDateStringToSaveToDB(VariablesGlobal.globalDate));             
                        int yearCurrent = (a.getYear());        

        txtdate.setText("01-01-"+yearCurrent);
        txtdate2.setText("31-12-"+yearCurrent);
        
        
        //selection();
        //selection();
        setTableNavigationKeys();
        //this.setPreferredSize(new Dimension(90, 240)); //Dimension(int width, int height) 
    }
    
    private void selectCompany(ActionEvent e)
    {        
             JComboBox cbCompany = (JComboBox)e.getSource();
             int sel = cbCompany.getSelectedIndex();
             ItemList item = (ItemList)vectorCompany.get(sel);
    	     companyId = item.getId();
    	     filter();
    	     
    }
    
    /*private void selectYear(ActionEvent e)
    {        
             JComboBox cbYear = (JComboBox)e.getSource();
             int sel = cbYear.getSelectedIndex();
             ItemList item = (ItemList)vectorYear.get(sel);
             year=item.getId();
    	     filter();

    }  */  
    
    /*private void setSelectedYear(String yearIn)
    {
    	for (int y=0;y<vectorYear.size();y++)
    	{
    		ItemList item = (ItemList)vectorYear.get(y);
    		if(item.getId().equalsIgnoreCase(yearIn))
    		{
    			//System.out.println("PanelStatistics.setSelectedYear"+item.getId());
    			comboYear.setSelectedItem(item);
    			executeSQL=false;
    		}
    	}
    	year=yearIn;
    }*/

    /*private String getSelectedYear()
    {
      String ret="";
    	if(isFilterYear)
        {
    	System.out.println("PanelStatistics.getSelectedYear index"+comboYear.getSelectedIndex()+" size"+vectorYear.size());
        
        for (int y=0;y<vectorYear.size();y++)
    	{
            if(comboYear.getSelectedIndex()!=-1 && vectorYear.size()>0)
            {
    		ItemList item = (ItemList)vectorYear.get((comboYear.getSelectedIndex())+1);
    		ret = item.getId();
            }
    		
    	}	
        }
      
    
    return ret;	
    }*/


    private String getSelectedCompany()
    {
   
      String ret="";
     	if(isFilterCompany)
        {    	
    	System.out.println("PanelStatistics.getSelectedCompany index"+comboCompany.getSelectedIndex()+" size"+vectorCompany.size());
        
        for (int y=0;y<vectorCompany.size();y++)
    	{
            if(comboCompany.getSelectedIndex()!=-1 && vectorCompany.size()>0)
            {
    		ItemList item = (ItemList)vectorCompany.get((comboCompany.getSelectedIndex()+1));
    		ret = item.getId();
            }
    		
    	}	
    	
        }
    
    return ret;	
       
    }

    private void setSelectedCompany(String companyIdIn)
    {
    	
    	for (int c=0;c<vectorCompany.size();c++)
    	{
    		ItemList item = (ItemList)vectorCompany.get(c);
    		if(item.getId().equalsIgnoreCase(companyIdIn))
    		{
    			//System.out.println("PanelStatistics.setSelectedCompany"+item.getId());
    			comboCompany.setSelectedItem(item);
    			executeSQL=false;
    		}	
    	}	
    	companyId=companyIdIn;
    }


    
    
   private void selection()
   {
        
   	    final boolean isSelectTableRow = true;
            listSelectionModel=null;
   	        listSelectionModel = table.getSelectionModel();
            // listSelectionModel.addListSelectionListener(new ListSelectionHandler());
             
              
            listSelectionModel.addListSelectionListener(new ListSelectionListener()
            {
                
                public void valueChanged(ListSelectionEvent e)
                {
                   
                    //Ignore extra messages.
                  if(entity!=null)  
                  {
                    if (e.getValueIsAdjusting()) return;
                    listSelectionModel = (ListSelectionModel)e.getSource();
                    if (listSelectionModel.isSelectionEmpty())
                    {
                        System.out.println("panelODMRData.selection No rows are selected.");
                    }
                    else
                    { 
                    
                         int  selectedTableRow = listSelectionModel.getMinSelectionIndex();                             
                        //System.out.println("PanelODMRData.selection primKeyDb "+primKeyDb);


//                        utilsPanelReport.retrievePrimKeyValue( query, selectedTableRow, primKey,0,false, null, null, entity, tableModel, null, primKeyDb,  null);  
                    
               /*     int  selectedTableRow = listSelectionModel.getMinSelectionIndex();                             
                        //System.out.println("PanelODMRData.selection primKeyDb "+primKeyDb);

                        utilsPanelReport.retrievePrimKeyValue( query, selectedTableRow, primKey,0,
                       false, null, null, entity, tableModel, null, primKeyDb,  null);
                  /*retrievePrimKeyValue(String queryIn, int selectedTableRow, String primKeyIn,int intColumnOfDescription,
    boolean isQuery2,String[] sql2WhereField, String[] sql2WhereValue, String entity, TableModel tableModel,
    String[] fieldsMany, String primKeyDb, String primKeyValueSetted)*/      
                        
                        
                       // selectedDataRow = selectedTableRow+1; //by default 1s selectedrow is 0. since there is no 0 record, we add 1

                    }
                   }
                  else
                  {
                      //System.out.println("panelODMRData.selection entity:"+entity);
                  }
                }
            });
          
          table.addMouseListener(new MouseAdapter()
          {
            public void mouseClicked(MouseEvent e)
            {
               
            	table = (JTableDec)e.getSource();
                if (e.getClickCount() == 2 && (entityStatistics!=null && entityStatistics.length-1>level)) // make it 2 for doubleclick
                {   
                    //retrievePrimKeyValue( query, selectedTableRow, primKey);
                    //System.out.println("panelStatistics.selection table double clicked. selectedTableRow "+selectedTableRow+" PKvalue "+getPrimKeyValue());
                    int  selectedTableRow = listSelectionModel.getMinSelectionIndex();                             
                        //System.out.println("PanelODMRData.selection primKeyDb "+primKeyDb);
                   
                    //System.out.println("PanelODMRData.selection B selectedTableRow:"+selectedTableRow);               
              //String queryIn, int selectedTableRow, String primKeyIn,int intColumnOfDescription,String[] sql2WhereField, String[] sql2WhereValue, String entity, TableModel tableModel, String primKeyDb)      
                  //  utilsPanelReport.retrievePrimKeyValueForOnePK(query, selectedTableRow,null,null,false/*, primKey, 0, null, null*/, entity, /*tableModel,*/ primKeyDb) ;
                    
                    
   //                     utilsPanelReport.retrievePrimKeyValue( query, selectedTableRow, primKey,0, false, null, null, entity, tableModel, null, primKeyDb,  null);
                  /*retrievePrimKeyValue(String queryIn, int selectedTableRow, String primKeyIn,int intColumnOfDescription,
    boolean isQuery2,String[] sql2WhereField, String[] sql2WhereValue, String entity, TableModel tableModel,
    String[] fieldsMany, String primKeyDb, String primKeyValueSetted)*/      
                        
                        
                       // selectedDataRow = selectedTableRow+1; //by default 1s selectedrow is 0. since there is no 0 record, we add 1
                  //System.out.println("panelStatistics.selection selectedTableRow:"+selectedTableRow+" primkey "+primKey+"="+utilsPanelReport.getPrimKeyValue());        
                       //System.out.println("panelODMRData.setEntity scroll "+selectedTableRow*15);
                       double sel =0.00;
                       double tr = selectedTableRow-8;  // -8 in order to be in the center of height
                       double rc = table.getRowCount();
                       sel = tr/rc;
                       double num =scrollpaneTable.getVerticalScrollBar().getMaximum();
                       num=num*sel;
                    if(isSelectTableRow)
                    {
                       scrollpaneTable.getVerticalScrollBar().setValue((int)num);  
                    }   
                    
                    
                    btnShowZoomIn.doClick();
                    //displayAnalysisDialog(utilsPanelReport.getPrimKeyValue());
                                      
                }
                else if(e.getClickCount() == 2)
                {
                    int  selectedTableRow = listSelectionModel.getMinSelectionIndex(); 
                    System.out.println("panelODMRData.selection mouseClicked                          entityStatistics:"+entityStatistics+"   selectedTableRow:"+selectedTableRow);
                    displayDrillDialog(selectedTableRow);
                }
                else
                {
                    
                }
            } 
           });

   	
   	
   }
   
   /*
   
   * similar in ReportAreaGenerated.displayDrillDialog
   */
   private void displayDrillDialog(int selectedTableRow)
   {
        
                               //String queryIn, int selectedTableRow, String primKeyIn,int intColumnOfDescription,String[] sql2WhereField, String[] sql2WhereValue, String entity, TableModel tableModel, String primKeyDb)
                          //System.out.println("PanelODMRData.displayDrillDialog A selectedTableRow:"+selectedTableRow);  
      //STATS be careful to have in this query all the fields that are also in the title
       String que = querySelect+" "+queryFrom+" "+queryWhere+" "+queryGroupBy+" "+queryOrderBy ;
       String entity = drillEntityPanels[0].getEntity();
        String primKeyDbDrill = drillEntityPanels[0].getPrimKeyDb();   //    <-- try delete if primKeys  is ok
        EntityDBFields[] dbFields =  drillEntityPanels[0].getDBFields();
        utilsPanelReport.retrievePrimKeyValueForOnePK(que, selectedTableRow+1,dbFields,null,false, /*primKey, 0, null, null,*/ entity,/* tableModel,*/ primKeyDbDrill);
                          //utilsPanelReport.getPrimKeyValue();

             String[] primKeys = utilsPanelReport.getPrimKeys();
             String[] primKeysCaption = utilsPanelReport.getPrimKeysCaption();
         // System.out.println("-->  PanelOneDataOneRec.setEntity  entityIn:"+entityIn+" '"+entityPanel.getEntity()+"' selectedRow:"+selectedRow+"  primKeys.length:"+primKeys.length); 
             int primKeysCount = primKeys.length;
             String[] primKeysValue = utilsPanelReport.getPrimKeysValue();                             
       //    String pkValue = utilsPanelReport.getPrimKeyValue();
     // System.out.println("panelStatistics.displayDrillDialog selectedTableRow:"+(selectedTableRow+1)+" primKeyDbDrill:"+primKeyDbDrill+"="+utilsPanelReport.getPrimKeyValue()+" query:"+query);                            

      
        PanelEditOneDataRec panelEODR = new PanelEditOneDataRec(frame);

      //EntityPanel[] entityPanel = drillEntityPanels[0].getDrillEntityPanel();
      
      String queryReadOnly = drillEntityPanels[0].getQuery();
      String editTitle = drillEntityPanels[0].getTitle();
      ImageIcon ico = drillEntityPanels[0].getIco();  
      
      //ImageIcon ico = drillEntityPanels[0].getType(); 
        //String q = querySelect+" "+queryFrom+" "+queryWhere+" "+queryGroupBy+" "+queryOrderBy;
       
                 
        //when dril get the right query for title
      /*  String selectTitleFields ="";
       if(fieldsOnStatisticsTitle!=null && fieldsOnStatisticsTitle.length!=0) 
       {    
         for(int f = 0 ;f<fieldsOnStatisticsTitle.length; f++)
         {
             if(selectTitleFields.equalsIgnoreCase(""))
             {
                 selectTitleFields  = fieldsOnStatisticsTitle[f];
             }
             else
             {
                  selectTitleFields  = selectTitleFields+", "+fieldsOnStatisticsTitle[f];
             }
         }
       }
       else
       {
           System.out.println("  error  panelStatistics.displayDrillDialog   fieldsOnStatisticsTitle:"+fieldsOnStatisticsTitle);
       }*/
       //  WRONG 
       //if(utilsString.hasQueryWhere(queryReadOnly))
       //{
       //    utilsString.replaceStringFromString(ERROR, WIDTH, que, ROOT) utilsString.indexOfQueryWhere(queryReadOnly)
       //}
       
        
       if  (primKeyValue!=null && !primKeyValue.equals(""))
         if(utilsString.hasQueryWhere(queryWhere))
         {
         	queryWhere=queryWhere+" AND "+primKeyDb+" LIKE '"+primKeyValue+"'";
         }
         else
         {
         	queryWhere="WHERE "+primKeyDb+" LIKE '"+primKeyValue+"'";
         }
               
       //         must be *
       String q = "SELECT * "+queryFrom+" "+queryWhere;//+" "+queryGroupBy+" "+queryOrderBy ;       
     
     //  String q = "SELECT "+selectTitleFields+" "+queryFrom+" "+queryWhere+" "+queryGroupBy+" "+queryOrderBy ; //  <-----
       
       System.out.println("panelStatistics.displayDrillDialog    OOOOO   drillEntityPanels.length:"+drillEntityPanels.length+"    drill entity report bands length 0 0 :"+drillEntityPanels[0].getEntityReportForm().getEntityReportBands()[0]+"       drillEntityPanels:"+drillEntityPanels+"   q:"+q);
       
        panelEODR.setEntity(entity, drillEntityPanels,selectedTableRow,fieldsOnStatisticsTitle,fieldsOnStatisticsTitleCaption,false,primKeys,primKeysValue,/*primKeyValue usually for dbcompanysettings,*//*primKeyDbDrill,/*formGlobalTableToGet1,
                formGlobalTableToApply1,*/q,//queryReadOnly,//query,//
                editTitle,ico,false,false,true,categoryNodes, false,panelManagement);//,drillEntityPanels[0].getEntityReportForm());
      
      
/*        panelEODR.setEntity(entity, entityPanel,fieldsOnTitle,fieldsOnTitleCaption,false,primKey,primKeyValue,primKeyDbDrill,queryReadOnly,
        editTitle,ico,false,false,true,categoryNodes, false,panelManagement);	
*/
        /*public void setEntity(String entityIn, EntityPanel[] entityPanelIn,int[]fieldsOnTitleIn, String[] fieldsOnTitleCaptionIn, 
    boolean isMasterUnique, String primKey,  String primKeyValueIn,String primKeyDbIn, String[] primKeysMany,String[] primKeysManyTran,
    String titleIn,ImageIcon ico, boolean dataOneIn, boolean isNewRecIn, boolean showBtnOk, String[] categoryNodes, boolean showShowListButtonsIn) 	)*/
    	

        panelEODR.setVisible(true);
        
        DialogMulti dlg = new DialogMulti(frame);
        dlg.setEntity(panelEODR,PANEL_TYPE_EDITONEDATAONEREC, "επεξεργασία "+editTitle,true);
        dlg.display();      
      
        filter();
        table.setRowSelectionInterval(selectedTableRow, selectedTableRow);
      
      
   }
   
   
    private void filter()
    {  
     this.setCursor(new Cursor(Cursor.WAIT_CURSOR));

     
     
     //utilsDate.readFromFileDateFormats();
     
     if (executeSQL)
     {
         String initialQueryWhere=queryWhere;
      //System.out.println("PanelStatistics.filter: '"+queryWhere+"'");       
        if (isFilterCompany)
        { 
            if(utilsString.hasQueryWhere(queryWhere))
            { // when there is only WHERE in clause and not any table to combine
            	
            	queryWhere=queryWhere+" AND "+fieldCompanyIdName+" LIKE '"+companyId+"'";
            }
            else
            {
                queryWhere="WHERE "+fieldCompanyIdName+" LIKE '"+companyId+"'";
            }
        }
        
         if (isFilterDates)
         {
             
                 //String  strdate1 =txtdate.getText();
                 //String  strdate2 = txtdate2.getText(); 
              JTextBoxWithEditButtons  tb = (JTextBoxWithEditButtons)fieldFilterTxts1.get(0);
              String  strdate1 = utilsDate.reformatDateStringToSaveToDB( tb.getText());
              
              JTextBoxWithEditButtons  tb2 = (JTextBoxWithEditButtons)fieldFilterTxts2.get(0);              
                 //String  strdate1 = utilsDate.reformatDateStringToSaveToDB(txtdate.getText());
                 String  strdate2 = utilsDate.reformatDateStringToSaveToDB(tb2.getText());
             
             
            if(utilsString.hasQueryWhere(queryWhere))
            { // when there is only WHERE in clause and not any table to combine
            	
            	queryWhere=queryWhere+" AND "+fieldDatesName+" >= '"+strdate1+"' AND "+fieldDatesName+" <= '"+strdate2+"'";
            }
            else
            {
        	    queryWhere="WHERE "+fieldDatesName+" >= '"+strdate1+"' AND "+fieldDatesName+" <= '"+strdate2+"'";
            }
            System.out.println("PanelStatistics.filter    queryWhere:"+queryWhere);
        }
               
        
       if  (primKeyValue!=null && !primKeyValue.equals(""))
         if(utilsString.hasQueryWhere(queryWhere))
         {
         	queryWhere=queryWhere+" AND "+primKeyDb+" LIKE '"+primKeyValue+"'";
         }
         else
         {
         	queryWhere="WHERE "+primKeyDb+" LIKE '"+primKeyValue+"'";
         }
               
    	query = querySelect+" "+queryFrom+" "+queryWhere+" "+queryGroupBy+" "+queryOrderBy ;//"SELECT buyer.buyerId, buyer.title, COUNT(invoice.buyerId) AS count, SUM(invoice.value) AS sum, AVG(invoice.value) AS average, MIN(invoice.value) AS min, MAX(invoice.value) AS max FROM buyer, invoice WHERE buyer.buyerId=invoice.buyerId AND invoice.year LIKE '"+year+"' AND invoice.companyId LIKE '"+companyId+"' GROUP BY buyer.buyerId ORDER BY buyer.title";
    	
    	
    	//setEntity("buyer", query);
    	if(VariablesGlobal.globalShowStatisticsSQL)
    	{
    	   System.out.println("PanelStatistics.filter: "+query);
    	}
    	System.out.println("PanelStatistics.filter: "+query);
    	tableModel= new TableModelReadOnly();
        table.setModel(tableModel);
        
        
   String[] tagElements ={"name"};
   String[] tagElementsType ={"String"};    	
    	XMLReader reader = new XMLReader();
     if(reader.checkIfElementExists(filePrefs, "Table",tagElements,tagElementsType,name))
     {
     	
          String[] tagElements1 ={"name","showColumns"};
          String[] tagElementsType1 ={"String", "String"};  
       int[]  fieldsShowColumnsNew = utilsPanelReport.loadDataFromXmlFileRetIntArray(filePrefs, "Table",tagElements1,tagElementsType1,1,name); //name instead of entity
  	
     	fieldsShowColumns=fieldsShowColumnsNew;     	
       if(utilsPanelReport.getSubQueryOrderByForPreferences(name).equalsIgnoreCase(""))
       {
       	  
       }
       else
       {
       	  query=utilsString.getQueryWithoutOrderby(query)+utilsPanelReport.getSubQueryOrderByForPreferences(name);
       }
     }
     else
     {
     	
     }               

        tableModel.setQuery(query);
  
        // jdk6
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
        table.setRowSorter(sorter);
        //jdk6
     
        showExtendedSummary();

        TableColumnModelCustom  columnModel = new TableColumnModelCustom();
         
        table.setColumnModel(columnModel);
        table.createDefaultColumnsFromModel();
       

       	//System.out.println("PanelOneDatManyRecData.retrieveDataFromQuery "+fieldsShowColumns.length);	
       if(fieldsShowColumns==null)
       {
       	   columnModel.setAllColumnsVisible();
       }
       else
       {
         for(int i = 0;i<fieldsShowColumns.length;i++) 
         {       	
       	//System.out.println("PanelOneDatManyRecData.retrieveDataFromQuery "+i+" "+fieldsShowColumns[i]);	
        if(fieldsShowColumns[i]!=0)
        {
         	    TableColumn column  = columnModel.getColumnByModelIndex(i);
                boolean     visible = columnModel.isColumnVisible(column);
                columnModel.setColumnVisible(column, visible);         	
                //System.out.println("PanelOneDatManyRecData.retrieveDataFromQuery visible "+i+" "+fieldsShowColumns[i]+" "+visible);	
        }
         else
         {
              
         	    TableColumn column  = columnModel.getColumnByModelIndex(i);
                boolean     visible = columnModel.isColumnVisible(column);
                columnModel.setColumnVisible(column, !visible);
                //System.out.println("PanelOneDatManyRecData.retrieveDataFromQuery NOT visible "+i+" "+fieldsShowColumns[i]+" "+!visible);
         }
        }
       }
       
        queryWhere=initialQueryWhere;

        int totalWidthOfColumns=0;
        for (int c=0; c<table.getColumnCount(); c++)
        {   // table,column, margin
            totalWidthOfColumns=utilsTable.packColumn(table, c, 2,true,false,null);
        } 
        scrollpaneTable.setPreferredSize(utilsTable.setTableScrollPaneSize(table,totalWidthOfColumns,420));

      }// if execute	
      
      closeDB();
      //tableModel.closeDB();
      
      this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

      //tableModel.releaseConnection();
    }

    
    /*
    *  called by
    */
   public void setEntity(String parentTitleIn, EntityStatistics[] entityStatisticsIn,ImageIcon iconIn, int levelIn,String formGlobalTableToGet1In,String formGlobalTableToApply1In,
           String primKeyValueIn, String year, String companyId, PanelManagement panelManagementIn)
   {
   	parentTitle=parentTitleIn;
   	entityStatistics= entityStatisticsIn;
   	icon=iconIn;
   	level=levelIn;
   	primKeyValue=primKeyValueIn;
   	panelManagement=panelManagementIn;
        //formGlobalTableToGet1=formGlobalTableToGet1In;
        //formGlobalTableToApply1 = formGlobalTableToApply1In;
        
        //System.out.println("PanelStatistics.setEntity  entityStatistics:"+entityStatistics+" level:"+level+"  "+primKey+"  "+ primKeyValue);
   	setEntity(entityStatistics[level].name, parentTitle,entityStatistics[level].entity,entityStatistics[level].caption, false,
   	"",icon, entityStatistics[level].querySelect,
   	entityStatistics[level].queryFrom,entityStatistics[level].queryWhere,entityStatistics[level].queryBroupBy,
   	entityStatistics[level].queryOrderBy,entityStatistics[level].isFilterCompany,entityStatistics[level].fieldCompanyIdName,
   	entityStatistics[level].isFilterYear,entityStatistics[level].fieldYearName,entityStatistics[level].getDrillEntityPanel(),entityStatistics[level].getFieldsOnStatisticsTitle(),
        entityStatistics[level].getFieldsOnStatisticsTitleCaption() ,categoryNodes,entityStatistics[level].primKey,
   	entityStatistics[level].primKeyDb,/* formGlobalTableToGet1,formGlobalTableToApply1,*/year,companyId, panelManagement);
   	System.out.println("PanelStatistics.setEntity(short)  entityStatistics:"+entityStatistics+"                                                 level:"+level+"  "+primKey+"  "+ primKeyValue);
   	
   }

    /*
     * 
     * called by this.setEntity() and PanelEditOneDataRec.selectionInMenu
     * subTitle set by PanelEditODOR.getTitleCaption
     */
    public void setEntity(String nameIn, String parentTitle,String entityIn,String titleIn, boolean showTitle, String subTitleIn,ImageIcon icon, 
    String querySelectIn,String queryFromIn, String queryWhereIn,String queryGroupByIn,String queryOrderByIn,
    boolean isFilterCompanyIn, String fieldCompanyIdNameIn, boolean isFilterDatesIn, String fieldDatesNameIn,EntityPanel[] drillEntityPanelsIn,String[] fieldsOnStatisticsTitleIn,
    String[] fieldsOnStatisticsTitleCaptionIn, String[] categoryNodesIn, String primKeyIn, String primKeyDbIn,/*String formGlobalTableToGet1In,String formGlobalTableToApply1In,*/
    String selYear, String selCompany, PanelManagement panelManagementIn)
    {
    	name=nameIn;
    	title=titleIn;
    	entity=entityIn;
    	subTitle=subTitleIn;
        querySelect=querySelectIn;
        queryFrom=queryFromIn;
        queryWhere=queryWhereIn;
        queryGroupBy=queryGroupByIn;
        queryOrderBy=queryOrderByIn;
        
        isFilterCompany=isFilterCompanyIn;
        fieldCompanyIdName=fieldCompanyIdNameIn;
        //fieldCompanyValue=fieldCompanyValueIn;
        isFilterDates=isFilterDatesIn;
        fieldDatesName=fieldDatesNameIn;
        //fieldYearValue=fieldYearValueIn;
        drillEntityPanels=drillEntityPanelsIn;
        categoryNodes=categoryNodesIn;
        primKey=primKeyIn;
        primKeyDb=primKeyDbIn;
        panelManagement=panelManagementIn;
        fieldsOnStatisticsTitle = fieldsOnStatisticsTitleIn;
        fieldsOnStatisticsTitleCaption = fieldsOnStatisticsTitleCaptionIn;
        
       // formGlobalTableToGet1=formGlobalTableToGet1In;
       // formGlobalTableToApply1=formGlobalTableToApply1In;
        //System.out.println("PanelStatistics init");
        //uDouble.getSettingsFromDb();        
        //System.out.println("PanelStatistics.setEntity  primKeyDb:"+primKeyDb);
        
        retrieveDatesList();
        
        if(drillEntityPanels !=null)
        {
        System.out.println("PanelStatistics.setEntity                                    drillEntityPanels.length:"+drillEntityPanels.length+"     primKeyDb:"+primKeyDb);
        }
        
        
        
       if(icon==null)
       {
       	  lblTitle.setIcon(ICO_STATISTICS16);
       }
       else
       {
         lblTitle.setIcon(icon);
       }

  
        if(entityStatistics!=null && entityStatistics.length-1>level)
        {
        	btnShowZoomIn.setVisible(true);
        }
        else
        {
        	btnShowZoomIn.setVisible(false);
        }
        
        if(entityStatistics!=null && entityStatistics.length>1  && level>0)
        {
            btnShowZoomOut.setVisible(true);
        }
        else
        {
        	btnShowZoomOut.setVisible(false);
        }        
        
        if(parentTitle==null)
        {
        	lblTitle.setText("<html>"+title+" "+subTitle);
        	//paneTitle.setVisible(false);
        }
        else
        {
        	lblTitle.setText(parentTitle+"/"+"<html>"+title+" "+subTitle);
        	//paneTitle.setVisible(true);
        }
        
        //System.out.println("PanelStatistics.setEntity  ====>  parentTitle:"+parentTitle+" title:"+title+" subTitle:"+subTitle);
        
        paneTitle.setVisible(showTitle);
        
        if(isFilterDates)
        {
        	lblDates.setVisible(true);
                btnDatePeriodListMenu.setVisible(true);
                        txtdate.setVisible(true);		
        		txtdate2.setVisible(true);
    //    	comboYear.setVisible(true); 
        }
        else
        {   
            lblDates.setVisible(false);
            btnDatePeriodListMenu.setVisible(false);	
                            txtdate.setVisible(false);		
        		txtdate2.setVisible(false);
        }
        if(isFilterCompany)
        {
        	lblCompany.setVisible(true);
        	comboCompany.setVisible(true);
        }
        else
        {
        	lblCompany.setVisible(false);
        	comboCompany.setVisible(false);
        }        
        if(!isFilterDates && !isFilterCompany)
        {
        	panelSelections.setVisible(false);
        }
        else
        {
        	panelSelections.setVisible(true);
        }
        
        //System.out.println("panelStatistics.setEntity "+selYear);
        if(selYear==null || selYear.equals(""))
        {
  //      	setSelectedYear(VariablesGlobal.globalYear);
        }

        if(selCompany==null || selCompany.equals(""))
        {
        	setSelectedCompany(VariablesGlobal.globalCompanyId);
        }       


        selection();
        //setTableNavigationKeys();        
       retrieveCompanyList();
 	retrieveDatesList(); 
        
        executeSQL=true;
   
        filter(); 
    }
    
   /* public void show()
    {
    	this.locateOnCenterOfTheScreen();
    	this.setVisible(true);
    }*/
    private void retrieveCompanyList()
    {   
        String query = "SELECT dbCompanyId, title FROM dbcompany";

   	    db.retrieveDBDataFromQuery(query,"PanelStatistics.retrieveCompanyList");
   	    rs=db.getRS();
   	    //rsmd=db.getRSMetaData();
   	    
        //rs = db.retrieveResultSet(query);
        vectorCompany.clear();    	
    	vectorCompany.add( new ItemList("%", "όλες οι εταιρίες του προγράματος" ) );
    
    try
    {	
    	while (rs.next())
    	{
    		vectorCompany.add( new ItemList(rs.getString(1), rs.getString(2) ) );
              //  System.out.println("PanelStatistics.retrieveCompanyList "+rs.getString("title"));
    	}
     }
     catch ( SQLException sqlex)
     {
           System.out.println("error:DialogStatistics.retrieveCompanyList():"+sqlex.getMessage());
     }
      
      closeDB();
    }
    
    
    private void retrieveDatesList()
    {
        

        	    
        	            //  setting dates for period
        		  JTextField txtFilterDates1 = new JTextField(10); 		
        		  JTextField txtFilterDates2 = new JTextField(10);
        		   
        		  // txtFilterDates1.setText(entityFilterSettings[s].getValue());  
        		   //txtFilterDates2.setText(entityFilterSettings[s].getValue());  

        		   
        		   int intColumnWidthDate = 8;//utilsDate.getDateFormatEditing().length();
        		   
                                   
                         JFormattedTextField textFormatedDate = new JFormattedTextField();
                         txtdate = new JTextBoxWithEditButtons(textFormatedDate, true,ICO_CALENDAR,null , false,null,null,LOOKUP_TYPE_DATE, frame,VariablesGlobal.globalDateFrom,VariablesGlobal.globalDateTo,MONTH_START);                    	
                         JFormattedTextField textFormatedDate2 = new JFormattedTextField();
                         txtdate2 = new JTextBoxWithEditButtons(textFormatedDate2, true,ICO_CALENDAR,null , false,null,null,LOOKUP_TYPE_DATE, frame,VariablesGlobal.globalDateFrom,VariablesGlobal.globalDateTo,MONTH_END);                   	
                          
                          final JTextBoxWithEditButtons txtdateFinal = txtdate;
                          final JTextBoxWithEditButtons txtdateFinal2 = txtdate2;
                          final JFrame frameFinal = frame;
                          

                          
                      String dateCurrent = VariablesGlobal.globalDate;
    	              UtilsDate utildate = new UtilsDate();
                      utildate.closeDB();
                      utildate.readFromFileDateFormats();
    	              String today = utildate.getCurrentDateStringFormattedLocaly(dateCurrent);
                      
                      LocalDate a = LocalDate.parse(utildate.reformatDateStringToSaveToDB(VariablesGlobal.globalDate));             
                        int yearCurrent = (a.getYear());
                      //    int yearPrevious = (Integer.parseInt(VariablesGlobal.globalYear))-1;                      
                          final int yearCurrentFinal = yearCurrent;
                      //    final int yearPreviousFinal = yearPrevious;

                          
                          JPopupMenu popupMenuDates = new JPopupMenu();
                          
       JMenuItem menuItem1 = new JMenuItem("χρήση "+utildate.reformatDateStringToReadFromDB(VariablesGlobal.globalDateFrom)+"  -  "+utildate.reformatDateStringToReadFromDB(VariablesGlobal.globalDateTo));
       popupMenuDates.add(menuItem1);
      // JMenuItem menuItem2 = new JMenuItem("χρήση "+yearPrevious);
      // popupMenuDates.add(menuItem2);
       JMenuItem menuItem3 = new JMenuItem("A τρίμηνο "+yearCurrent);	
       popupMenuDates.add(menuItem3);
       JMenuItem menuItem4 = new JMenuItem("B τρίμηνο "+yearCurrent);
       popupMenuDates.add(menuItem4);
       JMenuItem menuItem5 = new JMenuItem("Γ τρίμηνο "+yearCurrent);	
       popupMenuDates.add(menuItem5);
       JMenuItem menuItem6 = new JMenuItem("Δ τρίμηνο "+yearCurrent);
       popupMenuDates.add(menuItem6); 
       
                    
        
  

       menuItem1.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	    
                          txtdateFinal.setText("01-01-"+yearCurrentFinal);
                          txtdateFinal2.setText("31-12-"+yearCurrentFinal);
                          filter();
	        }
	    });           

       /*menuItem2.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	    
                          txtdateFinal.setText("01-01-"+yearPreviousFinal);
                          txtdateFinal2.setText("31-12-"+yearPreviousFinal);
                          filter();
	        }
	    });   */    
       
       
       menuItem3.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	    
                          txtdateFinal.setText("01-01-"+yearCurrentFinal);
                          txtdateFinal2.setText("31-03-"+yearCurrentFinal);
                          filter();
	        }
	    });

       menuItem4.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	    
                          txtdateFinal.setText("01-04-"+yearCurrentFinal);
                          txtdateFinal2.setText("30-06-"+yearCurrentFinal);
                          filter();
	        }
	    });       
       
       menuItem5.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	    
                          txtdateFinal.setText("01-07-"+yearCurrentFinal);
                          txtdateFinal2.setText("30-09-"+yearCurrentFinal);
                          filter();
	        }
	    });
       
       menuItem6.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	    
                          txtdateFinal.setText("01-10-"+yearCurrentFinal);
                          txtdateFinal2.setText("31-12-"+yearCurrentFinal);
                          filter();
	        }
	    });       
                
       
       
       
       
                btnDatePeriodListMenu = new JButtonListMenu("",popupMenuDates);
                btnDatePeriodListMenu.setMargin(new Insets(1, 9, 1, 1));  // Insets(int top, int left, int bottom, int right)   
                btnDatePeriodListMenu.setIcon(ICO_CALENDAR);


                         
                        
                   /*      if(calledFromPanel==PANEL_FILTER_SEARCH)
                         {
                                JxPanel   panelFilterLabelA = new JxPanel();
                                panelFilterLabelA.setLayout(flowLayoutLabelRight);
                  
                             JxPanel   panelFilterSettingA = new JxPanel();
                                panelFilterSettingA.setLayout(flowLayoutLeft2);
                  
                                panelFilterLabelA.add(lblFilterSetting);
                                panelFilterSettingA.add(btnDatePeriodListMenu);//           txtdate
                                panelFilterSettingA.add(txtdate.getComponent());
                  
                                panelReportFilters.add(panelFilterLabelA);
                                panelReportFilters.add(panelFilterSettingA);
                  
                                lblTo.setText(" ως:("+KEYSTROKE_F_LOOKUP_SHOW+")");   
                            //panelFilterSetting.add(lblTo);
                                panelFilterLabel.add(lblTo);
                                panelFilterSetting.add(txtdate2.getComponent());
                            }
                            else if(calledFromPanel==PANEL_FILTER_REPORT)
                            {             
                                   panelFilterLabel.add(lblFilterSetting);
        		           lblTo.setText("ως:");
                                   panelFilterSetting.add(btnDatePeriodListMenu);//           txtdate
        		            panelFilterSetting.add(txtdate.getComponent());
        		            panelFilterSetting.add(lblTo);
        		           panelFilterSetting.add(txtdate2.getComponent());
                          }
                          else
                          {
     	                         System.out.println("PanelDataFilter.setEntity calledFromPanel "+calledFromPanel);
                          }    */              
                       
                           txtdate.getDocument().addDocumentListener(new DocumentHandler(0,0,0,null,-1));   		
                           txtdate2.getDocument().addDocumentListener(new DocumentHandler(0,0,0,null,-1));  
                      
                           fieldFilterTxts1.add(txtdate.getComponent()); // panelODORD has added to arraylist the textBoxWithButtons but here is the dateformat
                           fieldFilterTxts2.add(txtdate2.getComponent());
        		      
        
    }
    
    
    
    
    
    
    
    
    /*private void retrieveYearList()
    {   
        String query = "SELECT DISTINCT dbyear, dbyear FROM dbyear ORDER BY dbyear DESC";

   	    db.retrieveDBDataFromQuery(query,"PanelStatistics.retrieveYearList");
   	    rs=db.getRS();
   	    //rsmd=db.getRSMetaData();
        //rs = db.retrieveResultSet(query);
        //System.out.println("PanelStatistics.retrieveYearList");    	
    	vectorYear.clear();
    	vectorYear.add( new ItemList("%", "όλες οι χρονιές" ) );
    
    try
    {	
    	while (rs.next())
    	{
    		vectorYear.add( new ItemList(rs.getString(1), rs.getString(2) ) );
    		System.out.println("PanelStatistics.retrieveYearList "+rs.getString("dbyear"));
    	}
     }
     catch ( SQLException sqlex)
     {
           System.out.println("error:DialogStatistics.retrieveYearList():"+sqlex.getMessage());
     }	

      closeDB();
     
    }*/
    
    
  public void showExtendedSummary()
  {
        boolean showExtendedSummary=true;
        String summary="";

        
        int columnsCount=0;
        String columnsTxt="";
        boolean hasColDouble=false;
        boolean hasColInteger=false;
        double sumDouble=0.00;
        double sumDoubleValue=0.0;
        int sumInteger=0;  	
        
        //System.out.println("panelODMRData.showExtendedSummary row count "+table.getRowCount());
  	//
        if (showExtendedSummary)
        {
           columnsTxt=columnsTxt+"<table><tr align=center>";	
           columnsTxt=columnsTxt+"<td>|</td>";
          for (int c =0; c<table.getColumnCount(); c++) //Note that this may be different from the number of columns in the table model. 
          {
              //System.out.println("PanelStatistics.showExtendedSummary "+primKey+" "+tableModel.getColumnName(c));
            if(primKey!=null && primKey.equalsIgnoreCase(tableModel.getColumnName(c)))// if is primKey do not show in sums
            {
            }
            else
            {
             if (tableModel.getColumnClass(c) == Double.class)
             {
             	columnsTxt=columnsTxt+"<td>"+tableModel.getColumnName(c)+"</td><td>|</td>";
             }
             else if (tableModel.getColumnClass(c) == Integer.class)
             {       
               columnsTxt=columnsTxt+"<td>"+tableModel.getColumnName(c)+"</td><td>|</td>";     	
             }
             if (tableModel.getColumnClass(c) == Long.class)
             {
             	columnsTxt=columnsTxt+"<td>"+tableModel.getColumnName(c)+"</td><td>|</td>";
             }              	
             else
             {
             	
             }
            }//else is not primKey
          }
          columnsTxt=columnsTxt+"</tr>";
          columnsTxt=columnsTxt+"<tr align=right>";
          columnsTxt=columnsTxt+"<td>|</td>";
          for (int c =0; c<table.getColumnCount(); c++) //Note that this may be different from the number of columns in the table model. 
          {
              
              
            if(primKey!=null && primKey.equalsIgnoreCase(tableModel.getColumnName(c)))// if is primKey do not show in sums
            {
            }
            else
            {              
               columnsCount=columnsCount+1;
            
            //System.out.println(c+" "+tableModel.getColumnClass(c));
              if (tableModel.getColumnClass(c) == Double.class)
              {
              	 //System.out.println("panelODMRData.setEntity Double "+c);
                  for (int r =0; r<table.getRowCount(); r++)
                  {
                	 //System.out.println(tableModel.getValueAt(r,c));
                 	 if (tableModel.getValueAt(r,c)==null)
                	 { 	sumDouble=sumDouble+0.00;       	}
                	 else
                	 {   sumDouble = sumDouble + Double.parseDouble(tableModel.getValueAt(r,c).toString());  	}
                	 hasColDouble=true;
                  }
                  
///////////                  //String strSumDouble= sumDouble.toString();
                  //sumDouble=;
                  //System.out.println("panelODMRData getStringWithTwoDigitsAfterDecimalFromDouble "+ getStringWithTwoDigitsAfterDecimalFromDouble(sumDouble));
                  
                  
                  columnsTxt=columnsTxt+"<td>"+uDouble.getDoubleReading(sumDouble,true)+"</td><td>|</td>";
                  sumDoubleValue=sumDouble;
                  sumDouble=0.00; //if there is a second or third column it will start from 0
              }
             else if (tableModel.getColumnClass(c) == Integer.class)
             {
             	//System.out.println("panelODMRData.setEntity Integer "+c);
                  for (int r =0; r<table.getRowCount(); r++)
                  {
                  	  //System.out.println(" problem "+tableModel.getColumnName(c)+" "+tableModel.getValueAt(r,c));
                	   if (tableModel.getValueAt(r,c)==null)
                	   {  sumInteger=sumInteger+0;         }
                       else
                       {   sumInteger = sumInteger + Integer.parseInt(tableModel.getValueAt(r,c).toString());      }
                       hasColInteger=true;
                  }

                  //columnsTxt=columnsTxt+"  ["+tableModel.getColumnName(c)+": <b>"+sumInteger+"</b> ]";
                  columnsTxt=columnsTxt+"<td>"+sumInteger+"</td><td>|</td>";
                  sumInteger=0; //if there is a second or third column it will start from 0
              }
              if (tableModel.getColumnClass(c) == Long.class)
              {
              	 //System.out.println("panelODMRData.setEntity Double "+c);
                  for (int r =0; r<table.getRowCount(); r++)
                  {
                	 //System.out.println(tableModel.getValueAt(r,c));
                 	 if (tableModel.getValueAt(r,c)==null)
                	 { 	sumDouble=sumDouble+0.00;       	}
                	 else
                	 {   sumDouble = sumDouble + Double.parseDouble(tableModel.getValueAt(r,c).toString());  	}
                	 hasColDouble=true;
                  }
                  
///////////                  //String strSumDouble= sumDouble.toString();
                  //sumDouble=;
                  //System.out.println("panelODMRData getStringWithTwoDigitsAfterDecimalFromDouble "+ getStringWithTwoDigitsAfterDecimalFromDouble(sumDouble));
                  
                  //columnsTxt=columnsTxt+"  ["+tableModel.getColumnName(c)+": <b>"+uDouble.getDoubleReading(sumDouble)+"</b> ]";
                  columnsTxt=columnsTxt+"<td>"+uDouble.getDoubleReading(sumDouble,true)+"</td><td>|</td>";
                  sumDoubleValue=sumDouble;
                  sumDouble=0.00; //if there is a second or third column it will start from 0
              }              
              else
              {  //System.out.println("not Double not Number "+c); 
              }
            }//else is not primKey
              
          } // for
          columnsTxt=columnsTxt+"</tr>";
          columnsTxt=columnsTxt+"</table>";
         /* if (hasColDouble || hasColInteger )
          {    columnsTxt = "σύνολα:" +  columnsTxt;     }/*
          
          // need them for ExtendedSummaryCalcs
          
         /*  if(isQuery2)
          {
              query = getQueryMany(query, isNewRec ,primKeyValue);
          }
          
          showExtendedSummaryCalcs(entity, query, isNewRec , primKeyValue);*/
        } //if 
               
        
        int sumRowCount=0;
       /* if (isEditable)// -1 because 1 
        {   sumRowCount=table.getRowCount()-1;}
        else
        { */   
          sumRowCount=table.getRowCount();
          
        //}
        
        
         lblBottom.setText("<html><table><tr><td>πλήθος [<b>"+sumRowCount+"</b>]<td>"+columnsTxt+summary+"</td></table></html>"); 
         lblBottom.revalidate();
         //this.revalidate();

  	
  }
    
    
     public void locateOnCenterOfTheScreen()
    {
    	Dimension paneSize   = this.getSize();
    	Dimension screenSize = this.getToolkit().getScreenSize();
    	this.setLocation(
            (screenSize.width  - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
    }
 
    private void setTableNavigationKeys()
    {
    	Set forwardKeys = new HashSet();
        forwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_TAB,0));
        forwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0));
        table.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,forwardKeys);
        
        Set backwardKeys = new HashSet();
        backwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_TAB,1));// 1 for alt key
        table.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS ,backwardKeys);  	
  	
        table.setFocusTraversalKeys(KeyboardFocusManager.DOWN_CYCLE_TRAVERSAL_KEYS,null);
        table.setFocusTraversalKeys(KeyboardFocusManager.UP_CYCLE_TRAVERSAL_KEYS,null);
  	
    }

    private void showExportMenu()
    {
//          popupMenuExport.show(btnExport, 0, btnExport.getY()+ btnExport.getHeight());
    }

   private void exportTo(String type)
   { //same as PanelOneDataManyRecData.exportTo
   	  int colCount = tableModel.getColumnCount();
      String[] colNames = tableModel.getTableColumnRealNames();
      Class[] colClass = tableModel.getTableColumnsClass();
      Vector data = tableModel.getTableDataVector();
      
      ExportToFile exportToFile = new ExportToFile();
      
      exportToFile.exportTo(colCount,table,colNames,colClass,data,type,frame,title);
   }
   
    public void displayDialogTablePreferences()
    {  //same as PanelOneDataManyRecData.displayDialogTablePreferences
    	
    	//String[] colTitles = {"ναι/οχι","όνομασία στήλης"};
    	
    //	String[][] columns = new String[table.getColumnCount()][table.getRowCount()];   // to hold tb columns 
    //	int col=1; //this will be the second column in table
    	
    /*	for(int c=0; c<table.getColumnCount();c++)
    	{
    		String colName = table.getColumnName(c);
    		columns[c][col]=colName;
    	    columns[c][0]=true;
    	}*/
    	
    	DialogDataTableConfig dlgDataConfig;
    	
    	Vector dataVector = new Vector();
    	int colCount=table.getColumnCount();
        for (int c = 0; c < colCount; c++) // for each field
        {
        	String colName = table.getColumnName(c);
            Object[] record = new Object[] {Boolean.valueOf(true), colName } ;
            dataVector.addElement(record);
        }
   
            dlgDataConfig = new DialogDataTableConfig((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this));
            String text ="Επιλογές πίνακα "+title+"";
            dlgDataConfig.setEntity(name,text,query,null,primKeyDb,null);
    	    dlgDataConfig.showDialog();
    	        	//table.removeColumn(col);
    
    
 
    
    filter();
    }
   
    private void displayPanelReportSettings()
    {// entity,0,query,strOfMany,true

    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    
     String subTitleFromSetEntity=subTitle;
      if (subTitle==null || subTitle.equals(""))
      {
      	if (lblCompany.isVisible())
      	{
      	   subTitle= subTitle+" "+lblCompany.getText()+":"+comboCompany.getItemAt(comboCompany.getSelectedIndex());
      	}

      	if (lblDates.isVisible())
      	{      	
  //    	    subTitle=subTitle+" "+lblYear.getText()+":"+comboYear.getItemAt(comboYear.getSelectedIndex());
      	}
      }
    
       PrinterJob printerJob = PrinterJob.getPrinterJob();
       PageFormat pageFormat = printerJob.defaultPage();
       
       if(table.getColumnCount()>6)
       {
             pageFormat.setOrientation(PageFormat.LANDSCAPE);  
       }
    
    
    
    	dlgPrintPreview = new PanelReportSettingsPreview(frame); 


    	
    	    	UtilsString uString = new UtilsString();
    	
    	EntityQuery[] entityQuery = new EntityQuery[1]; 
        entityQuery[0]= new EntityQuery(uString.getQueryWithoutOrderby(query),QUERY_READ,null,null,null,null,null);
    	
        PanelReportSettingsPreview panelReportSettingsPreview = new PanelReportSettingsPreview(frame);

//        EntityReport entityReport = new EntityReport();
//       EntityReportBand[] entityReportBand = new EntityReportBand[1];
//       entityReportBand[0] = new EntityReportBand("bandFromStatistics",title,uString.getQueryWithoutOrderby(query),"",entity,ENTITYREPORT_QUERY_TYPE_MAIN,-1,null,null); 
       //EntityReportBand(String nameIn,  String captionIn, String tableNameIn ,EntityReportBandFields[] entityReportBandFieldsIn,String sqlGroupByFieldIn, int typeIn,boolean [] boolSettingsIn)/*,int groupOfCompsIn,String colClassNameIn,int colWidthIn,int primaryKeyIntegerAutoIncIn,  */
        
//        panelReportSettingsPreview.setEntity("",entityReportGroup,"ODMR",null,null,null,null,title,subTitle,true, null, null,null,null,null, year,panelManagement);
  
        
        //panelReportSettingsPreview.showDialog();  
        panelManagement.addShowTabWithPanel(title,ICO_PRINT_PREVIEW16,panelReportSettingsPreview,PANEL_TYPE_REPORT);
        subTitle=subTitleFromSetEntity; 
    
       this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    	
    }
    
    private void showZoom(int levelOfZoom, String primKeyVal)
    {
    	
      //if(entityStatistics !=null && entityStatistics.length-1>level)
      //{
      String year  = "";  //     getSelectedYear();
      
          setEntity(parentTitle,  entityStatistics, icon, levelOfZoom/*level+1*/,formGlobalTableToGet1,formGlobalTableToApply1, primKeyVal,year,
          getSelectedCompany(), panelManagement);
                                             
      
      
      //}    	
    	
    	
    }
    
   /* private void displayAnalysisDialog(String primKeyValue)
    {
      if(entityStatistics !=null && entityStatistics.length-1>level)
      {
    	
	
           DialogMulti dlg = new DialogMulti(frame);
 	       dlg.setTitle(title);

 	       // entityFilterSettings=task.getEntityFilterSettings();
 	       // System.out.println("PanelTwoDataManyRec.executeTask "+entityFilterSettings[1].dbTable);
           PanelStatistics panelStats = new PanelStatistics(frame);
    
           //level= level+1;
    
    
           panelStats.setEntity( parentTitle,  entityStatistics, icon, level+1, primKeyValue);
    
 	       dlg.add(panelStats);
 	       dlg.pack();
 	       dlg.locateOnCenterOfTheScreen();
 	       dlg.show();    	
       }	
    }*/

   // called by PanelManagement.prepareCloseOfTabPanel 
   public void closePanel()
   {   	
   	  
  	  closeDB();
   }
 
    public void closeDB()
   {
   	
   	      db.releaseConnectionRs();
          db.releaseConnectionRsmd();
   	
   }   
     /*public static void main(String args[])
    {
        DialogStatistics dlg  = new DialogStatistics(null);

        
        //dlg.setEntity(entity,query);
        dlg.setSelectedYear("2006");
        dlg.setSelectedCompany("1");
        dlg.filter();
        dlg.locateOnCenterOfTheScreen();
        dlg.setVisible(true);
    }   
    */
 
  
  // panel
	class PanelRenderer implements ListCellRenderer
	{
		private JxPanel renderer;
		private JLabel first;
		private JLabel second;
		private JLabel lbl;
        //private ArrayList itemsArray;
        
		public PanelRenderer(int firstColumnWidth)
		{
			//itemsArray=itemsA;
			renderer = new JxPanel();
			renderer.setLayout(new BoxLayout(renderer, BoxLayout.X_AXIS) );
            
           /* for(int i=0; i<itemsArray.size();i++)
            {
            	lbl = new JLabel(" "+i+" ");
            	//lbl.setMaximumSize();
            	renderer.add(lbl);
            	System.out.println("lbl");
            }*/
            
			first = new JLabel(" ");
			Dimension d = first.getPreferredSize();
			d.width = firstColumnWidth;
			first.setMaximumSize(d);
			second = new JLabel();
			//renderer.add(first );
			renderer.add(second );
		}
 
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			//ItemList item = (ItemList)companyList.get(index+1);
            //System.out.println(index+item.toString());
			
			ItemList item = (ItemList)value;
            
            //System.out.println(item.toString());
			
			first.setText( item.getId() );
			second.setText( item.getDescription() );
 
			renderer.setBackground(isSelected ? list.getSelectionBackground() : null);
			renderer.setForeground(isSelected ? list.getSelectionForeground() : null);
 
			return renderer;
		}
	}
    
   	class ItemList
	{
		private String id;
		private String description;
 
		public ItemList(String idIn, String descriptionIn)
		{
			id = idIn;
			description = descriptionIn;
    		//System.out.println("ItemList "+id+description);

		}
 
		public String getId()
		{
			return id;
		}
 
		public String getDescription()
		{
			return description;
		}
 
		public String toString()
		{
			return description;
		}
	}
	
   class  ActionPrintPreview extends AbstractAction                 
   {       
        public ActionPrintPreview()
        {        }
    	public void actionPerformed(ActionEvent e)
      	{          btnPrintPreview.doClick();        }    	
    } 
   
class ToolBarData extends JToolBar implements Constants
{
        
        public ToolBarData()
        {
            try
           {     initialize();   }
           catch (Exception e)
           {   System.out.println("PanelStatistics.ToolBarData"+e.getMessage());
           }
           //.printStackTrace();    }
        }


        private void initialize() throws Exception
        {
            
            
            BevelBorder borderRaised = new BevelBorder(javax.swing.border.BevelBorder.RAISED);
            this.setBorder(borderRaised);            
            
       // btnViewMultiR = new JButton();
       // btnViewOneR = new JButton();
        btnShowZoomIn = new JButton();
        btnShowZoomOut = new JButton();
        btnPrintPreview = new JButton();
//        btnExport = new JButtonListMenu();
        /*mItemHtml = new JMenuItem("σε html");
        mItemPdf = new JMenuItem("σε txt");
        mItemExcel = new JMenuItem("σε excel");*/
//        btnPreferences = new JButton();
        	
        //setFloatable(false);
        //setRollover(true);
        //this.setOpaque(false);


        btnShowZoomOut.setText("<html>zoom out</html>");
        btnShowZoomOut.setOpaque(false);
        //btnPrintPreview.setText("<html>προεπισκόπηση <b>O</b></html>");	    
       // btnPrintPreview.setText("<html>προεπισκόπηση <b>alt+O</b></html>");
        btnShowZoomOut.setToolTipText("zoom out");
//        btnShowZoomOut.setIcon(ICO_ZOOMOUT);
        btnShowZoomOut.setMnemonic(KeyEvent.VK_O);
        btnShowZoomOut.setFocusable(false);
        btnShowZoomOut.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	
	           //displayAnalysisDialog(utilsPanelReport.getPrimKeyValue());
	           showZoom(level-1,null);
	        }
	    });

        btnShowZoomOut.setVisible(false);

        btnShowZoomIn.setText("<html>zoom in</html>");
        btnShowZoomIn.setOpaque(false);
        //btnPrintPreview.setText("<html>προεπισκόπηση <b>O</b></html>");	    
       // btnPrintPreview.setText("<html>προεπισκόπηση <b>alt+O</b></html>");
        btnShowZoomIn.setToolTipText("zoom in");
//        btnShowZoomIn.setIcon(ICO_ZOOMIN);
        btnShowZoomIn.setMnemonic(KeyEvent.VK_O);
        btnShowZoomIn.setFocusable(false);
        btnShowZoomIn.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	
	          String primKVal = utilsPanelReport.getPrimKeyValue();
                  //table.getSelectedRow();
                  boolean isSelected = table.getSelectionModel().isSelectionEmpty();
	           //if(primKVal==null || primKVal.equals(""))
                  if(isSelected)
	           {
	           	  utilsGui.showMessageInfo("Παρακαλώ επιλέξτε πρώτα μια γραμμή.");
	           }
	           else
	           {
	               showZoom(level+1, primKVal);	
	           }
	           
	        }
	    });

                 
        
        btnPrintPreview.setText("<html>προεπισκόπηση <b>F7</b></html>");
        btnPrintPreview.setOpaque(false);
        //btnPrintPreview.setText("<html>προεπισκόπηση <b>O</b></html>");	    
       // btnPrintPreview.setText("<html>προεπισκόπηση <b>alt+O</b></html>");
        btnPrintPreview.setToolTipText("προεπισκόπηση εκτύπωσης");
        btnPrintPreview.setIcon(ICO_PRINT_PREVIEW16);
        btnPrintPreview.setMnemonic(KeyEvent.VK_O);
        btnPrintPreview.setFocusable(false);
        btnPrintPreview.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	displayPanelReportSettings();     }
	    });
        Action actionPrintPreview = new ActionPrintPreview();
        btnPrintPreview.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), "report"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnPrintPreview.getActionMap().put("report", actionPrintPreview);
        
/*        btnExport.setText("<html>ε<b>Ξ</b>αγωγή</html>");
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
	        	 showExportMenu();
	        
	        }
	    });
       /* Action actionExport = new ActionExport();
        btnExport.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F2"), "export"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnExport.getActionMap().put("export", actionExport);*/

/*    	UtilsMiscEntities utilsMiscEntities = new UtilsMiscEntities();
    	 entityExportFileType = utilsMiscEntities.getExportToFileEntities();    	
    	
    	String[] strExportFileType = new String[entityExportFileType.length];
    	for(int i=0;i<entityExportFileType.length;i++)
    	{
    		strExportFileType[i]=entityExportFileType[i].getCaption() +" ("+entityExportFileType[i].getExtension() +")";
    	   JMenuItem mItemExport = new JMenuItem("άμεση εξαγωγή σε αρχείο "+strExportFileType[i]);
    	   final int iFinal = i;
           mItemExport.addActionListener(new ActionListener()
           {
	           public void actionPerformed(ActionEvent e) 
	           {	   exportTo((String)entityExportFileType[iFinal].getName());  }
	       });    	   
    	   popupMenuExport.add(mItemExport);
    	}   */
        


/*        btnPreferences.setText("<html>προ<b>Τ</b>ιμήσεις</html>");
        btnPreferences.setOpaque(false);
        //btnPreferences.setText("<html>προτιμήσεις <b>Π</b></html>");
        btnPreferences.setToolTipText("προτιμήσεις πίνακα");
        btnPreferences.setIcon(ICO_TABLE_PREFS16);
        btnPreferences.setMnemonic(KeyEvent.VK_T);
        btnPreferences.setFocusable(false);
        btnPreferences.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   displayDialogTablePreferences();  }
	    });
        */
         this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        //add(lblIcoSeparator1);
        //addSeparator();
        addSeparator();
        //addSeparator();
       // add( btnPrevious );
       // add( btnNext );
       // addSeparator();
        //add( btnFind );
//        add(btnShowZoomIn);
//         add(btnShowZoomOut);
//        add(btnPrintPreview);
//        add(btnExport);
//        add(btnPreferences);
        //add(lblIcoSeparator3);
        addSeparator();
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
 

 private class DocumentHandler implements DocumentListener
  { // if data changed in txt
  
    int no=0;
    int additionalCol;  // additionalCol for lookup from to for difference between the twho compnents (the same s)
    String classtype="";
    String foreignTable = null;
    int txtIndex=0; //   txtIndex key=0  , lookup text 1, lookup text  2
    int filterFromPreviousSelectedField=-1;
    public DocumentHandler( int noIn, int additionalColIn,int txtIndexIn, String foreignTableIn,int filterFromPreviousSelectedFieldIn )
    {
        no = noIn;
        additionalCol = additionalColIn;
        txtIndex=txtIndexIn;
        //classtype=classtypeIn;
        foreignTable=foreignTableIn;
        filterFromPreviousSelectedField=filterFromPreviousSelectedFieldIn;
    }
        
    public void insertUpdate(DocumentEvent e)
    {  
     //System.out.println("DocumentHandler.insertUpdate "+no);
    
 

    	
      if(fieldFilterTxts1.get(no) instanceof JTextComponent)
      {
      	//System.out.println("PanelDataFilter.JTextComponent");
        final JTextComponent tb = (JTextComponent)fieldFilterTxts1.get(no);
        tb.setBackground(Color.WHITE);	
      }
      else if(fieldFilterTxts1.get(no) instanceof JTextBoxWithEditButtons)
      {
      	//System.out.println("PanelDataFilter.JTextBoxWithEditButtons");
      	JTextBoxWithEditButtons tbEb = (JTextBoxWithEditButtons)fieldFilterTxts1.get(no);
        final JTextComponent	tb = tbEb.getTextComp();
   	tb.setBackground(Color.WHITE);	
      }
      else
      {
      	System.out.println("error PanelDataFilter.DocumentHandler insertUpdate fieldFilterTxts1 "+no+" "+fieldFilterTxts1.get(no));
      }                
 
      if(fieldFilterTxts2.get(no) instanceof JTextComponent)
      {
      	//System.out.println("PanelDataFilter.JTextComponent");
        final JTextComponent tb = (JTextComponent)fieldFilterTxts2.get(no);
        tb.setBackground(Color.WHITE);	
      }
      else if(fieldFilterTxts2.get(no) instanceof JTextBoxWithEditButtons)
      {
      	//System.out.println("PanelDataFilter.JTextBoxWithEditButtons");
      	JTextBoxWithEditButtons tbEb = (JTextBoxWithEditButtons)fieldFilterTxts2.get(no);
        final JTextComponent	tb = tbEb.getTextComp();
   	tb.setBackground(Color.WHITE);	
      }
      else
      {
      	System.out.println("error PanelDataFilter.DocumentHandler insertUpdate fieldFilterTxts2 "+no+" "+fieldFilterTxts2.get(no));
      } 

          //System.out.println("PanelODORD.DocumentHandler hasDataChanged "+hasDataChanged);
    }

    public void removeUpdate(DocumentEvent e)
    {
      //System.out.println("DocumentHandler.removeUpdate "+no);
                  
      if(fieldFilterTxts1.get(no) instanceof JTextComponent)
      {
      	//System.out.println("PanelDataFilter.JTextComponent");
        final JTextComponent tb = (JTextComponent)fieldFilterTxts1.get(no);
        tb.setBackground(Color.WHITE);	
      }
      else if(fieldFilterTxts1.get(no) instanceof JTextBoxWithEditButtons)
      {
      	//System.out.println("PanelDataFilter.JTextBoxWithEditButtons");
      	JTextBoxWithEditButtons tbEb = (JTextBoxWithEditButtons)fieldFilterTxts1.get(no);
        final JTextComponent	tb = tbEb.getTextComp();
   	tb.setBackground(Color.WHITE);	
      }
      else
      {
      	System.out.println("error PanelDataFilter.DocumentHandler removeUpdate fieldFilterTxts1 "+no+" "+fieldFilterTxts1.get(no));
      }                
 
      if(fieldFilterTxts2.get(no) instanceof JTextComponent)
      {
      	//System.out.println("PanelDataFilter.JTextComponent");
        final JTextComponent tb = (JTextComponent)fieldFilterTxts2.get(no);
        tb.setBackground(Color.WHITE);	
      }
      else if(fieldFilterTxts2.get(no) instanceof JTextBoxWithEditButtons)
      {
      	//System.out.println("PanelDataFilter.JTextBoxWithEditButtons");
      	JTextBoxWithEditButtons tbEb = (JTextBoxWithEditButtons)fieldFilterTxts2.get(no);
        final JTextComponent	tb = tbEb.getTextComp();
   	tb.setBackground(Color.WHITE);	
      }
      else
      {
      	System.out.println("error PanelDataFilter.DocumentHandler removeUpdate fieldFilterTxts2 "+no+" "+fieldFilterTxts2.get(no));
      }                 

    }

    public void changedUpdate(DocumentEvent e)
    {
       /* if (guiLoaded)
        { 
           hasDataChanged=true;
           
           //System.out.println("error DocumentHandler.removeUpdate"+" -> txtIndex"+txtIndex+" not defined, foreignTable "+foreignTable+" noIn"+no); 		
        }*/
    }

  }


 }
 