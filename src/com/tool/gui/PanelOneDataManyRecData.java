package com.tool.gui;

import com.tool.rpt.PanelReportSettingsPreview;
import com.tool.model.LookUpMgt;
import com.tool.model.EntityGroupOfComps;
import com.tool.model.EntityMessage;
import com.tool.model.EntityFilterSettings;
import com.tool.jdbc.*;
import com.tool.gui.*;
import com.tool.guicomps.*;
import com.tool.guicomps.Constants;
import static com.tool.guicomps.Constants.FIELD_PRIMARY_KEY_AUTOINC;
import static com.tool.guicomps.Constants.ICO_UPDATE16;
import com.tool.guicomps.JTableDec;
import com.tool.guicomps.JxPanel;
//import com.tool.guicomps.MediatorPanelTwoDataOneRec;
import com.tool.guicomps.PanelManagement;

import com.tool.guicomps.PlainDocumentInsertText;
import com.tool.guicomps.TableCellEditorDate;
import com.tool.guicomps.TableCellEditorDouble;
import com.tool.guicomps.TableCellEditorInteger;
import com.tool.guicomps.TableCellEditorLookupOne;
import com.tool.guicomps.TableCellEditorLookupTwoA;
import com.tool.guicomps.TableCellEditorLookupTwoB;
import com.tool.guicomps.TableCellEditorString;
import com.tool.guicomps.TableCellRendererBoolean;
import com.tool.guicomps.TableCellRendererDate;
import com.tool.guicomps.TableCellRendererDefault;
import com.tool.guicomps.TableCellRendererDouble;
import com.tool.guicomps.TableCellRendererInteger;
import com.tool.guicomps.TableCellRendererLookUp;
import com.tool.guicomps.TableCellRendererValidation;
import com.tool.guicomps.TableColumnModelCustom;
import com.tool.guicomps.TableHeaderRenderer;
//import com.tool.guicomps.TableModelListenerGeneric;
import com.tool.guicomps.TableModelReadOnly;
import com.tool.guicomps.TableModelResultSet;
import com.tool.utils.*;
import com.tool.model.*;



import java.awt.*;
import java.awt.event .*;
import javax.accessibility.*;
import javax.swing.*;

import javax.swing.table.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.KeyStroke;
//jdk6
import javax.swing.RowSorter;
import javax.swing.table.TableRowSorter;
//jdk6
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.ListSelectionModel;
//import javax.swing.event.TableModelListener;
//import javax.swing.event.TableModelEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.TableModelListener;
import javax.swing.event.TableModelEvent;

import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.text.NumberFormatter;
  import javax.swing.text.MaskFormatter;
 import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.JTableHeader;
import javax.swing.InputMap;
import javax.swing.event.ChangeEvent;


import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.KeyboardFocusManager;
 import java.awt.print.PrinterJob;
 import java.awt.print.PageFormat;

import java.sql.*;

import java.util.ArrayList;
import java.util.Vector;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import java.util.Collections;

import java.text.NumberFormat;

import java.lang.reflect.Field;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.event.*;
import javax.swing.text.JTextComponent;
//import java.text.NumberFormat;

 public class PanelOneDataManyRecData extends JxPanel implements Constants//, TableModelListener
 {
     
     
        private JButton btnManyInsert;
        private JButton btnManyInsertInLine;
        private JButton btnManyMultiInsert;
        private JButton btnManyDelete;
        private JButton btnManyEdit;
        private JButton btnManyCopyAboveCell;
        private JButton btnManyClearAll;
       // private JButton btnManySave;
        
 	private String title;
 	private JxPanel panelAllOnIt;
    private ResultSet rs;
    private ResultSetMetaData rsmd;
    //private ResultSet rsPercentage;
    //private ResultSetMetaData rsmdPercentage;
    private Database db;
//    private DatabaseTableMeta databaseTableMeta;
    private TableModelResultSet tableModelResultSet;
    private TableModelReadOnly tableModelReadOnly;
    private ListSelectionModel listSelectionModel;
    private JTableDec table;
   // private JTableDec table;
    private JLabel lblBottom;
    private JScrollPane scrollpaneTable;
    private int selectedTableRow;
    private int selectedDataRow;
    private String summary="";
    //private DialogDataTableConfig dlgDataConfig;
    private String entity="";
    private String entityMany;
    private String strOfMany="";
    private UtilsTable utilsTable;
    private UtilsGui utilsGui;
    private UtilsOS uOS;
    private LookUpMgt lookUp;
    private ArrayList tableCols;
    //private PanelReportSettingsAndPreview dlgPrintPreview;
    private int totalWidthOfColumns;
    //private String[] sql2WhereField;
    //private String[] sql2WhereValue;
    private boolean isEditable;
    //private String[]fieldsMany;
    //private String[]fieldsManyTranslation;
    //private String[]fieldsManyOnInsert;
    //private String[]fieldsManyTranslationOnInsert;
    private String primKey;
    private String primKeyDb;
    private String primKeyValueSetted;
    //private String[] primKeysMany;
    //private String[] primKeysManyTran;
    private String[] primKeys;
    private String[] primKeysValue;
    private int primKeysCount;
    //private String primKeyValue;
    private String query;
    private boolean isQuery2;
    //private boolean showExtendedSummary;
    private boolean showExtendedSummaryCalcs;
    private boolean isNewRec;

    public String primKeyValue;
    //public String globalYear;   
    //public String globalDeliveryId;
    //public String globalCompanyId; 
    
    //private MediatorPanelTwoDataOneRec med;
    private UtilsPanelReport utilsPanelReport;
    private UtilsDouble uDouble;
    
    //private TableModelListenerGeneric tblModelListenerGeneric;
    
    private boolean rowInsertedOrDeleted=false;
    
    private int intTableActionValueField;//for table action the int(from db table) of the double field 'value'
           // for table percentage
    private String tablePercentage;
    private String tablePercentageKey;//Case sensitive
           //get from tableCategory entries that belong to the same tablePercentage
           // and add them
    private String tableCategory;
    private String tableCategoryKey;//Case sensitive
    private int intTableCategoryField;// starts from 1
    private boolean isSelectTableRow =false;
    private String yearEnforceInLines;
//    private String yearEnforce;
    private int[] fieldsOrderby;
    //private EntityFilterSettings[] entityFilterSettings;
    private JFrame frame;
    private JxPanel panelInfo;
    private JLabel lblInfo;
    private int intCheckBoxesColumn;
    private String[] checkBoxInfo;
    
    private String colDescriptionValue="";
    
    private EntityFilterSettings[] entityFilterSettings;
    private EntityGroupOfComps[] entityGroupOfComps;
    private Timer timerForPanelInfo;
    
    private int secondsToShowPanelInfo = 7000;  //7000 is 7 seconds
    
    private EntityDBFields[] dbFieldsParent;
    private EntityDBFields[] dbFieldsMany;
    private EntityDBFields[] dbFields;
    
    private UtilsString utilsString;
    private int intValidationColumn;
    private int intValidationType;
    //private JLabel lblIcoAttention	
    private final int  YES =0;

    private final int NO = 1;
    private final int CANCEL = 3;    
    
    TableColumnModelCustom  columnModel;	
    private PanelManagement panelManagement;
   private String[] fieldsForSums;
    	
    private TableCellRendererDouble tcrDouble;
    	
    
    private ArrayList listTableCellEditorLookup;
       private String filePrefs;
       private String queryMany;
      private ToolBarDataManyEditable toolBarDataManyEditable;
      private JxPanel panelManyWithTable;
      //private EntityUpdateAdditional[] updateAdditional;
      private PanelOneDataOneRecData panelODORData;
    private UtilsDate utilsDate;
    private String formGlobalTableToGet1;
    private String formGlobalTableToApply1;
    //private String formGlobalField1;
    private ArrayList fieldTxts;  
    private int intTableOfParentDBFields;
    EntityPanel entityPanel;
    
    private boolean isTableEditable;
    
    public PanelOneDataManyRecData(JFrame frame) 
    {
            try
           {    initialize(frame);    }
           catch (Exception e)
           {   e.printStackTrace();    }
    }
    
    /*
    used in selection bellow
    */
 public static enum DIR
 {
      Up, Down, Left, Right
 }      
    

    // for mediator pattern
    /*public PanelOneDataManyRecData(MediatorPanelTwoDataOneRec md, JFrame frame)
    {
            try
           {
           	     med = md;
                 med.registerPanelOneDataManyRecData(this);
           	     initialize(frame);
           }
           catch (Exception e)
           {   e.printStackTrace();    }
    }*/

	private void initialize(JFrame frameIn) throws Exception
    {    	
    	setOpaque(false);
        frame=frameIn;
    	utilsGui = new UtilsGui();
    	utilsString = new UtilsString();
    	uOS = new UtilsOS();
    	this.setLayout(new BorderLayout());
        table = new JTableDec();
        //table = new JTableDec();
        tableModelReadOnly= new TableModelReadOnly();
        tableModelResultSet= new TableModelResultSet();
        utilsPanelReport=new UtilsPanelReport();
        uDouble=new UtilsDouble();
        if(VariablesGlobal.globalShowInitialisations)
        {
        System.out.println("PanelODMRData init");
        }
        uDouble = VariablesGlobal.globalUtilsDouble;
        utilsTable=new UtilsTable();
        lookUp = new LookUpMgt();
       utilsDate= new UtilsDate();
       utilsDate.readFromFileDateFormats();
        
 	toolBarDataManyEditable = new ToolBarDataManyEditable();
        toolBarDataManyEditable.setFocusable(false);        
        
        db= new Database();
        
        
        filePrefs =VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+FILE_TABLEPREFERENCES;
        
//        databaseTableMeta = new DatabaseTableMeta();
        
        panelInfo =new JxPanel();
        //panelInfo.setOpaque(true);
        panelInfo.setLayout(new FlowLayout());
        lblInfo = new JLabel("lblInfo");
        
        panelInfo.setOpaque(true);
        lblInfo.setOpaque(true);
       
        panelInfo.add(lblInfo);
        //panelInfo.setBorder(BorderFactory.createLineBorder(Color.RED)); 
       	panelInfo.setBackground(CLR_HIGHLIGHT);
        lblInfo.setBackground(CLR_HIGHLIGHT);
        
        // not delete it:  cannot format given object as a number
        TableCellRendererDefault tcr = new TableCellRendererDefault();
        tcrDouble = new TableCellRendererDouble(uDouble); // has setEntity
        //tcrDouble.getDoubleSettingsFromFile();
        TableCellRendererInteger tcrInteger = new TableCellRendererInteger();
        TableCellRendererDate tcrDate = new TableCellRendererDate();
        TableCellRendererBoolean tcrBoolean = new TableCellRendererBoolean();
        tcrDate.pushUtilsDateToReadFromFile();
        // see alse PrintTable
        
        table.setDefaultRenderer(Object.class, tcr);
        
        table.setDefaultRenderer(Object.class, tcr);
        table.setDefaultRenderer(Integer.class, tcrInteger);
        table.setDefaultRenderer(Number.class, tcrInteger);
        table.setDefaultRenderer(Double.class, tcrDouble); 
        table.setDefaultRenderer(Boolean.class, tcrBoolean);
        table.setDefaultRenderer(java.util.Date.class, tcrDate); 
        table.addFocusListener( new FocusListener()
        {
              public void focusGained(FocusEvent e)
              {
              	if(table.getRowCount()>0)
              	{
              		if(table.getSelectedRow()==-1)
              		{
              		   table.setRowSelectionInterval(0,0);		
              		}   
              	}
                else
                {
                    addNewRowBelow();
                }
                 //displayMessage("Focus gained", e);
              }

              public void focusLost(FocusEvent e)
              {
                   
                   //displayMessage("Focus lost", e);
              }
	
        });        
        //table.setAutoCreateRowSorter(true);                                        ---- row sorter
        
        //show less border when editing object
        JTextField tfObject = new JTextField();
        tfObject.setBorder(BorderFactory.createEmptyBorder()); 
        DefaultCellEditor editorObject = new DefaultCellEditor(tfObject);
        editorObject.setClickCountToStart(1);
        table.setDefaultEditor(Object.class,  editorObject);
        
        
        //NumberFormat format = NumberFormat.getNumberInstance(); // noumber with , for double
        
       // NumberFormat format = NumberFormat.getNumberInstance();//.getCurrencyInstance();
       // NumberFormatter formatter = new NumberFormatter(format);
        //JFormattedTextField field = new JFormattedTextField(); // formatter);
       
        /*JTextField tfDouble = new JTextField();
        tfDouble.setBorder(BorderFactory.createEmptyBorder()); 
        TableCellEditorDouble editorDbl = new TableCellEditorDouble(tfDouble);//, format);
        editorDbl.setClickCountToStart(1);
        table.setDefaultEditor(Double.class, editorDbl);*/
        
        table.setShowVerticalLines(true);        
        table.setShowHorizontalLines(true);
        table.setAutoResizeMode(JTableDec.AUTO_RESIZE_OFF);
        //table.setCellSelectionEnabled(true);
        
        /* table.setColumnSelectionAllowed(true);*/
        //table.setRowSelectionAllowed(true);
    
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// select a single row only       
        //table.setSurrendersFocusOnKeystroke(true);
        table.setGridColor(CLR_TABLE_GRID);
        

        
        scrollpaneTable  = new JScrollPane();  
//        scrollpaneTable.setViewportView(table);
 
        lblBottom = new JLabel("");  
        	lblBottom.setOpaque(false); 
        lblBottom.setIcon(ICO_INFO16);
        //lblBottom.setOpaque(true);
        //lblBottom.setBackground(lblBottom.getBackground().brighter());//SystemColor.info);
        
        selection();
        /* listSelectionModel=null;
   	     listSelectionModel = table.getSelectionModel();
         listSelectionModel.addListSelectionListener(new ListSelectionHandler());
         
         //table.setSelectionModel(listSelectionModel);

          table.addMouseListener(new MouseAdapter()
          {
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 2) // make it 2 for doubleclick
                {   
                    System.out.println("panelODMRData.selection table double clicked. selectedTableRow "+selectedTableRow+" PKvalue "+getPrimKeyValue());
                }
            } 
           });   */     
         
      



     timerForPanelInfo = new Timer(secondsToShowPanelInfo, new ActionListener() 
     {
       public void actionPerformed(ActionEvent evt)
       {
        //if (/* thread is done */) {

            timerForPanelInfo.stop();
            panelInfo.setVisible(false);
            //System.out.println("PanelODMRData.initialize timerForPanelInfo stop");
            //...Update the GUI...

        //}

       }

      });

       panelInfo.setVisible(false);
        
       
//       tableModelResultSet.addTableModelListener(this);
        //panelManyWithTable.add(PanelOneDataManyRecData, BorderLayout.CENTER );
       
       if (VariablesGlobal.globalShowInitialisations)
       {   System.out.println("panelODMRData initialized");  }
        //add(lblBottom,BorderLayout.PAGE_END);
    }

   public void closeDB()
   {
   	
   	  db.releaseConnectionRs();
          db.releaseConnectionRsmd();
   	
   }
   
   
   
   
   /*
   *   called by PanelODORData.
   */
   /*public void setFormGlobalVariable1(String formGlobalVariable1In)
   {
       //formGlobalTable1=formGlobalTableToGet1In;
       formGlobalVariable1 = formGlobalVariable1In;
   }*/
   
   /*
   
     called by PanelODORData.setEntity
     also needed to call -->this.filterForWritableTable after setEntity  <---
   */
   public void setEntity(String titleIn,EntityPanel entityPanelIn, String queryIn,String entityManyIn,boolean isQuery2In,boolean isEditableIn, 
           EntityDBFields[] dbFieldsParentIn,EntityDBFields[] dbFieldsManyIn,EntityGroupOfComps[] entityGroupOfCompsIn,boolean isNewRecIn, String primKeyDbIn,
           /*String formGlobalTableToGet1In,String formGlobalTableToApply1In,*//* String formGlobalField1In,String formGlobalVariable1In,*/
           String primKeyValueIn,String[] fieldsForSumsIn,ArrayList fieldTxtsIn,PanelOneDataOneRecData panelODORDataIn, int intTableOfParentDBFieldsIn)//,EntityUpdateAdditional[] updateAdditionalIn)
   {
       entity=entityManyIn;
       entityPanel=entityPanelIn;
       title=titleIn;
       query=queryIn;
       entityMany=entityManyIn;
       isQuery2=isQuery2In;
       isEditable=isEditableIn;
       dbFieldsParent = dbFieldsParentIn;
       dbFieldsMany= dbFieldsManyIn;
       entityGroupOfComps=entityGroupOfCompsIn;
       isNewRec= isNewRecIn;
       primKeyDb=primKeyDbIn;
       
      //formGlobalTableToGet1=formGlobalTableToGet1In;
      //formGlobalTableToApply1=formGlobalTableToApply1In;
 //      formGlobalField1=formGlobalField1In;
 //      formGlobalVariable1 = formGlobalVariable1In;
       primKeyValue=primKeyValueIn;
       fieldsForSums=fieldsForSumsIn;
//       uDouble.getSettingsFromDb();
       panelODORData=panelODORDataIn;
      fieldTxts = fieldTxtsIn;
      intTableOfParentDBFields=intTableOfParentDBFieldsIn;
     
      
       
       this.add(panelInfo,BorderLayout.PAGE_START);
        //add(scrollpaneTable, BorderLayout.CENTER );
        //add(lblBottom,BorderLayout.PAGE_END);
        
       
       
        if( !isEditable )
        {     utilsTable.setTableExitNavigationKeys(table);    }
        else
        {    	utilsTable.setTableNavigationKeys(table);      }
        
        
       
        panelAllOnIt =new JxPanel();
        panelAllOnIt.setLayout(new BorderLayout());
        
        panelAllOnIt.add(scrollpaneTable, BorderLayout.CENTER );
        panelAllOnIt.add(lblBottom,BorderLayout.PAGE_END);
        panelManyWithTable = new JxPanel();
        panelManyWithTable.setLayout(new BorderLayout());
        
        if(isEditable)
        {
           panelManyWithTable.add(toolBarDataManyEditable, BorderLayout.PAGE_START);            
        }
        
        panelManyWithTable.add(panelAllOnIt, BorderLayout.CENTER );
        
           
        if(isEditable)
        {
           table.setEntity(isEditable);
           table.setRowHeight(22);
           String strMultiInsertCaption =  "";
           for(int f = 0; f<dbFieldsParent.length;f++)
           {
              
               if(dbFieldsParent[f].getColClassName().equalsIgnoreCase("table") && dbFieldsParent[f].getMultipleInsertField()!=null)
               {
                   strMultiInsertCaption = dbFieldsParent[f].getMultipleInsertCaption();
                    btnManyMultiInsert.setVisible(true);
                    break;
               }
               else
               {
                    btnManyMultiInsert.setVisible(false);
               }
           }
          
           btnManyMultiInsert.setText(strMultiInsertCaption);
        }
        else
        {
            btnManyMultiInsert.setVisible(false);
        	table.setEntity(false);
                new ActionTableFind().install(table); // tooltip find, enable with ctrl i
        }
        
        if( !isEditable )
        {     utilsTable.setTableExitNavigationKeys(table);    }
        else
        {    	utilsTable.setTableNavigationKeys(table);      }        
    
        // moved to panelODORData.setEntity
        /*if(isEditable)// observed that when deleting from showing not editable ads to query
        {
           query = utilsString.addSubqueryAndToWhereBeforeOrderBy(query,entity,primKeyDb,primKeyValue);
        }*/
                
        
        
       if(!isEditable)
       {
           toolBarDataManyEditable.setVisible(isEditableIn);
           table.setModel(tableModelReadOnly);
       }
       else
       {
           table.setModel(tableModelResultSet);
       }    
        
        add(panelManyWithTable, BorderLayout.CENTER );

       
        /*tblModelListenerGeneric = new TableModelListenerGeneric(table);
        table.getModel().addTableModelListener(tblModelListenerGeneric);          
     */
        //if(tblModelListenerGeneric.getRowCount()==0 && isEditable )
       // {
           // btnManyDelete.setEnabled(false);
            //this.showExtendedSummaryForWritable();
        //}
        
     
        closeDB();       
   }
   
   
    /*
     * called by PanelODMR.setEntity
     * 
     */
    public void setEntity(String entityIn,/*EntityPanel entityPanelIn,*/String titleIn, String queryIn, String[] fieldsForSumsIn,EntityDBFields[] dbFieldsIn,EntityDBFields[] dbFieldsManyIn, 
          /*EntityGroupOfComps[] entityGroupOfCompsManyIn,*/ /*String[] fieldsManyOnInsertIn, String[]fieldsManyTranslationOnInsertIn,*/ String primKeyIn, String primKeyDbIn,
          //String formGlobalVariable1In,
          /*String[] primKeysManyIn,String[] primKeysManyTranIn, String[] sql2WhereFieldIn, String[] sql2WhereValueIn,*/ String primKeyValueIn, boolean isEditableIn,
          /*boolean showExtendedSummaryIn,*/boolean showExtendedSummaryCalcsIn,String strOfManyIn, boolean isNewRecIn,String entityManyIn, boolean isQuery2In,
          int extsumcalcsIntTableActionValueField, String extsumcalcsTablePercentage, String extsumcalcsTablePercentageKey, String extsumcalcsTableCategory, 
          String extsumcalcsTableCategoryKey, int extsumcalcsIntTableCategoryField,String yearEnforceInLinesIn, int[] fieldsOrderbyIn, int intCheckBoxesColumnIn, 
          String[] checkBoxInfoIn, int intValidationColumnIn, int intValidationTypeIn, EntityFilterSettings[] entityFilterSettingsIn, EntityGroupOfComps[] entityGroupOfCompsIn, 
          /*String yearEnforceIn,*/ ArrayList fieldTxtsIn,PanelManagement panelManagementIn, int intTableOfParentDBFieldsIn/*,String formGlobalTableToGet1In,String formGlobalTableToApply1In*/) // if query is null use entity
    {
            if (this != null)
            {  this.removeAll(); }
        
        //fieldsMany=fieldsManyIn;
        //fieldsManyTranslation=fieldsManyTranslationIn;
        //formGlobalTableToApply1=formGlobalTableToApply1In;
        // entityPanel = entityPanelIn;
        title=titleIn;
        fieldsForSums=fieldsForSumsIn;
        dbFields = dbFieldsIn;
        dbFieldsMany= dbFieldsManyIn;
        //fieldsManyOnInsert=fieldsManyOnInsertIn;
        //fieldsManyTranslationOnInsert=fieldsManyTranslationOnInsertIn;        
        primKey=primKeyIn;
        primKeyDb=primKeyDbIn;
        //formGlobalVariable1=formGlobalVariable1In;
        primKeyValueSetted=primKeyValueIn;// <----both get from primKeyValueIn
        primKeyValue=primKeyValueIn;//  <---------both get from primKeyValueIn
        //primKeysMany=primKeysManyIn;
        //primKeysManyTran=primKeysManyTranIn;
        entity=entityIn;
        entityMany=entityManyIn;
        strOfMany=strOfManyIn;
       // selectedDataRow = 0; // 0 it here to avoid accidentally being set when user has selected before an other table 
//        sql2WhereField=sql2WhereFieldIn;
//        sql2WhereValue=sql2WhereValueIn;
        isEditable=isEditableIn;
        query=queryIn;
        isNewRec=isNewRecIn;
        isQuery2=isQuery2In;
        
        showExtendedSummaryCalcs=showExtendedSummaryCalcsIn; //extsumcalcs
        yearEnforceInLines=yearEnforceInLinesIn;
        fieldsOrderby=fieldsOrderbyIn;
        intCheckBoxesColumn = intCheckBoxesColumnIn;
        checkBoxInfo=checkBoxInfoIn;
        intValidationColumn=intValidationColumnIn;
        intValidationType=intValidationTypeIn;
        entityFilterSettings=entityFilterSettingsIn;
       // entityFilterSettings =entityFilterSettingsIn;
        entityGroupOfComps=entityGroupOfCompsIn;
        panelManagement=panelManagementIn;
//        uDouble.getSettingsFromDb();
        fieldTxts = fieldTxtsIn;
      intTableOfParentDBFields=intTableOfParentDBFieldsIn;
        
     //   System.out.println("PanelODMRData.setEntity    ++++     (long) entity:"+entity+"."+primKeyDb+" "+primKey+" primKeyValue:"+primKeyValue+"     fieldsForSums:"+fieldsForSums+"  dbFields:"+dbFields+"  dbFieldsMany:"+dbFieldsMany);

        
           intTableActionValueField=extsumcalcsIntTableActionValueField;//6;//for table action the int(from db table) of the double field 'value'
           // for table percentage
           tablePercentage=extsumcalcsTablePercentage;//"producttype";
           tablePercentageKey=extsumcalcsTablePercentageKey;//"productTypeId";//Case sensitive
           //get from tableCategory entries that belong to the same tablePercentage
           // and add them
           tableCategory=extsumcalcsTableCategory;//"product";
           tableCategoryKey=extsumcalcsTableCategoryKey;//"productId";//Case sensitive
           intTableCategoryField=extsumcalcsIntTableCategoryField;//3;// starts from 1
 //       yearEnforce=yearEnforceIn;
           
           
        if(isEditable)
        {
           table.setEntity(isEditable);
           table.setRowHeight(21);	
        }
        else
        {
        	table.setEntity(false);
        }
        
        
        //System.out.println("PanelOneDataManyRecData.setEntity queryIn "+queryIn);
        
        if(isEditable)
        {

        }
        else
        {
               new ActionTableFind().install(table); // tooltip find, enable with ctrl i
        }
        
        
        //System.out.println("panelODMRData.setEntity Query "+query);
        
        if( !isEditable )
        {     utilsTable.setTableExitNavigationKeys(table);    }
        else
        {    	utilsTable.setTableNavigationKeys(table);      }
        //System.out.println("PanelOneDataManyRecData.setEntity "+primKeyDb);
        
 //      if ( isEditable )
 //      {     databaseTableMeta.retrieveImpKsOnQuery(entity,query); }//first retrieve them then find for each column the foreign table name

        if (query == null) // this should be after the retrieveImpKsOnQuery, so it will not cause more process consumption
        {        query = "SELECT * FROM " + entity;}
  
  
  
 //       retrieveDataFromWritableTable(query,entity,sql2WhereFieldIn,sql2WhereValueIn,primKeyValue,isEditable,isNewRec,isQuery2);
       //System.out.println("panelODMRData.setEntity "+query);
      

        //   setScrollPaneSize(totalWidthOfColumns);

        this.add(panelInfo,BorderLayout.PAGE_START);
        //add(scrollpaneTable, BorderLayout.CENTER );
        //add(lblBottom,BorderLayout.PAGE_END);
        
        panelAllOnIt =new JxPanel();
        panelAllOnIt.setLayout(new BorderLayout());
        
        panelAllOnIt.add(scrollpaneTable, BorderLayout.CENTER );
        panelAllOnIt.add(lblBottom,BorderLayout.PAGE_END);
              
//        add(panelAllOnIt, BorderLayout.CENTER );

        panelManyWithTable = new JxPanel();
        panelManyWithTable.setLayout(new BorderLayout());
        
        if(isEditable)
        {
           panelManyWithTable.add(toolBarDataManyEditable, BorderLayout.PAGE_START);            
        }
  
        
       if(!isEditable)
       {
           toolBarDataManyEditable.setVisible(isEditableIn);
           table.setModel(tableModelReadOnly);
       }
       else
       {
           table.setModel(tableModelResultSet);
       }        
        
        
        panelManyWithTable.add(panelAllOnIt, BorderLayout.CENTER );
        
        
        add(panelManyWithTable, BorderLayout.CENTER );        
        
        //table.getModel().addTableModelListener(this);
        
       /* tblModelListenerGeneric = new TableModelListenerGeneric(table);
        table.getModel().addTableModelListener(tblModelListenerGeneric);          
     */
        //if(tblModelListenerGeneric.getRowCount()==0 && isEditable )
        //{
            //btnManyDelete.setEnabled(false);
            //this.showExtendedSummaryForWritable();
        //}
        
  
        
        closeDB();
    }
    
    
    
  /* public void setTableValuesAtRow(Object[] value, int row, String[] colName,EntityDBFields[] dbFieldsInGroupOfPanels)
   {
       tableModelResultSet.setDBFieldsValueAt(dbFieldsInGroupOfPanels);
       for(int i = 0;i<tableModelResultSet.getColumnCount();i++)
       {
          
          // RecColumn rc = (RecColumn)value[i];
            String clnm =  "";
            clnm = tableModelResultSet.getColumnName(i);
             //clnm = rc.getColumnCaption();
             for(int k=0;k<colName.length;k++)
             {  
                 System.out.println("PanelODMRData.setTableValuesAtRow         i:"+i+"    value:"+value.length+"   clnm:"+clnm+"   colName:"+colName[k]);
               if(colName!=null && !clnm.equalsIgnoreCase("") && colName[k].equalsIgnoreCase(clnm))
               {   
                   System.out.println("PanelODMRData.setTableValuesAtRow         i:"+i+"    value:"+value.length+"   clnm:"+clnm+" = colName:"+colName[k]);
                  setTableValueAt(value[k]+"", row, i);
               }
             }
       }
   }*/
    
    
    
    /*
    *
    * called by PanelODORData.dbFieldsChildCalculationSet 
    */
    public void setTableValueAt(String value,int row,int col)
    {
        tableModelResultSet.setValueAt(value, row, col);
    }
  
    /*
    *
    * called by PanelODORData.dbFieldsChildCalculationSet 
    */
    public String getTableValueAt(int row,int col)
    {
        //System.out.println("PanelODORData.getTableValueAt    row"+row+"  col"+col);
        String strReturn = tableModelResultSet.getValueAt(row, col)+"";
        return strReturn;
    }    
  
     public TableModelResultSet getTableModelResultSet()
    {
        //System.out.println("PanelODMRData.getTableValueAt    row"+row+"  col"+col);
    
        return tableModelResultSet;
    }    
    
  
     public JTableDec getTable()
    {
        //System.out.println("PanelODORData.getTableValueAt    row"+row+"  col"+col);
    
        return table;
    }  
     
    
  public void setButtonManyDeleteEnabled(boolean bool)  
  {
      btnManyDelete.setEnabled(bool);
  }
  
  // called by  this
  public void showExtendedSummaryForWritable()
  {
      showExtendedSummary(tableModelResultSet);
      //System.out.println(" O-O PanelODMRData.showExtendedSummaryForWritable ");
      
  }
   
  // in PanelODMRData, WinLookupMultipleCheck, this
  private void showExtendedSummary(TableModel multipleTableModel)
  {
        int columnsCount=0;
        String columnsTxt="";
        boolean hasColDouble=false;
        boolean hasColInteger=false;
        double sumDouble=0.00;
        double sumDoubleValue=0.0;
        int sumInteger=0;  	
        
        //System.out.println("panelODMRData.showExtendedSummary ----  "+fieldsForSums);
  	if ( fieldsForSums!=null && fieldsForSums.length>0)
        {
           columnsTxt=columnsTxt+"<table><tr align=center>";	
           columnsTxt=columnsTxt+"<td>|</td>";
           
           
          for (int c =0; c<multipleTableModel.getColumnCount(); c++) //Note that this may be different from the number of columns in the table model. 
          {
              //TableModel tableModel=table.getModel();
              String colCaption = multipleTableModel.getColumnName(c);
              
              //System.out.println("panelODMRData.showExtendedSummary showExtendedSummary  ("+c+") "+colCaption);
              for(int f = 0 ;f<fieldsForSums.length;f++)
              {
                  if(colCaption.equalsIgnoreCase(fieldsForSums[f]))
                  {
                      //System.out.println("panelODMRData.showExtendedSummary showExtendedSummary "+fieldsForSums[f]+"   "+colCaption);
                      columnsTxt=columnsTxt+"<td>"+colCaption+"</td><td>|</td>";
                  } 
              }
              
          }
          columnsTxt=columnsTxt+"</tr>";
          columnsTxt=columnsTxt+"<tr align=right>";
          columnsTxt=columnsTxt+"<td>|</td>";
          for (int c =0; c<multipleTableModel.getColumnCount(); c++) //Note that this may be different from the number of columns in the table model. 
          {
              String colCaption = multipleTableModel.getColumnName(c);
              //System.out.println("panelODMRData.showExtendedSummary   ("+c+")    colCaption"+colCaption+" class:"+multipleTableModel.getColumnClass(c));
              for(int f = 0 ;f<fieldsForSums.length;f++)
              {
                  if(colCaption.equalsIgnoreCase(fieldsForSums[f]))
                  {
                      //System.out.println("panelODMRData.showExtendedSummary showExtendedSummary "+fieldsForSums[f]+"   "+colCaption);
               columnsCount=columnsCount+1;

              //System.out.println("PanelODMRData.showExtendedSummary 2 "+c);
              if (multipleTableModel.getColumnClass(c) == Double.class )
              {
              	
                  for (int r =0; r<table.getRowCount(); r++)
                  {
                 	 if (multipleTableModel.getValueAt(r,c)==null)
                	 { 	sumDouble=sumDouble+0.00;       	}
                	 else
                	 {   sumDouble = sumDouble + Double.parseDouble(multipleTableModel.getValueAt(r,c).toString());  	}
                	 hasColDouble=true;
                  }
                   //System.out.println("panelODMRData.setEntity  "+colCaption+"     Double "+c+"    "+sumDouble+"       "+uDouble.getDoubleReading(sumDouble,true));
                  //String strSumDouble= sumDouble.toString();
                  //sumDouble=;
                  //System.out.println("panelODMRData getStringWithTwoDigitsAfterDecimalFromDouble "+ getStringWithTwoDigitsAfterDecimalFromDouble(sumDouble));
                  
                  //columnsTxt=columnsTxt+"  ["+tableModelReadOnly.getColumnName(c)+": <b>"+uDouble.getDoubleReading(sumDouble)+"</b> ]";
                  columnsTxt=columnsTxt+"<td>"+uDouble.getDoubleReading(sumDouble,true)+"</td><td>|</td>";
                  sumDoubleValue=sumDouble;
                  sumDouble=0.00; //if there is a second or third column it will start from 0
              }
             else if (multipleTableModel.getColumnClass(c) == Integer.class)
             {
             	//System.out.println("panelODMRData.setEntity Integer "+c);
                  for (int r =0; r<table.getRowCount(); r++)
                  {
                  	  //System.out.println(" problem "+tableModelReadOnly.getColumnName(c)+" "+tableModelReadOnly.getValueAt(r,c));
                	   if (multipleTableModel.getValueAt(r,c)==null)
                	   {  sumInteger=sumInteger+0;         }
                       else
                       {   sumInteger = sumInteger + Integer.parseInt(multipleTableModel.getValueAt(r,c).toString());      }
                       hasColInteger=true;
                  }

                  //columnsTxt=columnsTxt+"  ["+tableModelReadOnly.getColumnName(c)+": <b>"+sumInteger+"</b> ]";
                  columnsTxt=columnsTxt+"<td>"+sumInteger+"</td><td>|</td>";
                  sumInteger=0; //if there is a second or third column it will start from 0
              }
              else if (multipleTableModel.getColumnClass(c) == Long.class)
              {
              	//System.out.println("panelODMRData.setEntity "+colCaption+"     Long "+c);
                  for (int r =0; r<table.getRowCount(); r++)
                  {
                	 //System.out.println(tableModelReadOnly.getValueAt(r,c));
                 	 if (multipleTableModel.getValueAt(r,c)==null)
                	 { 	sumDouble=sumDouble+0.00;       	}
                	 else
                	 {   sumDouble = sumDouble + Double.parseDouble(multipleTableModel.getValueAt(r,c).toString());  	}
                	 hasColDouble=true;
                  }
                  
///////////                  //String strSumDouble= sumDouble.toString();
                  //sumDouble=;
                  //System.out.println("panelODMRData getStringWithTwoDigitsAfterDecimalFromDouble "+ getStringWithTwoDigitsAfterDecimalFromDouble(sumDouble));
                  
                  //columnsTxt=columnsTxt+"  ["+tableModelReadOnly.getColumnName(c)+": <b>"+uDouble.getDoubleReading(sumDouble)+"</b> ]";
                  columnsTxt=columnsTxt+"<td>"+uDouble.getDoubleReading(sumDouble,true)+"</td><td>|</td>";
                  sumDoubleValue=sumDouble;
                  sumDouble=0.00; //if there is a second or third column it will start from 0
              }              
              else
              {  //System.out.println("not Double not Number "+c); 
              }
                      }  
          } // for  fieldsForSums.length
          }// for   table.getColumnCount()
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
        } //if  showExtendedSummary

        int sumRowCount=0;
        sumRowCount=table.getRowCount();

         lblBottom.setText("<html><table><tr><td>πλήθος "+strOfMany+": [<b>"+sumRowCount+"</b>]<td>"+columnsTxt+summary+"</td></table></html>"); 
         lblBottom.revalidate();
         //this.revalidate();

         
  	
  }
  
    /*
    * 
    * exists in PanelOneDataOneRecData, PanelOneDataManyRecData, TableModelResultSet and PanelDataFilter
    */
   public ArrayList getListOfFieldsUncompleted()
   {
   	
   	 // todo get row col and color yellow the cells
   	  ArrayList listMessages = tableModelResultSet.getListOfFieldsUncompleted();
   	  for(int l=0;l<listMessages.size();l++)
   	  {
   	  	EntityMessage em = (EntityMessage)listMessages.get(l);
   	    

   	  TableColumn col = table.getColumnModel().getColumn(em.getColumn());
   	  final EntityMessage emFinal =em;
   	  col.setCellRenderer(new DefaultTableCellRenderer(){   	  
   	    public Component getTableCellRendererComponent(JTableDec tableIn, Object value, boolean isSelected, 
         boolean hasFocus, int row, int col)
       {
         Component comp = super.getTableCellRendererComponent(tableIn,  value, isSelected, hasFocus, row, col);

          //String s =  table.getModel().getValueAt(row, VALIDATION_COLUMN ).toString();

        /* if(s.equalsIgnoreCase("Fail")) 
         {*/
         //System.out.println("PanelODMRData.getListOfFieldsUncompleted "+emFinal.getTableRow()+" "+row);
       if(emFinal.getTableRow()-1==row)  
       {
         if(isSelected)
         {
         	comp.setForeground(Color.black);
         	comp.setBackground(Color.yellow);
         }
         else
         {
         	comp.setForeground(Color.black);
         	comp.setBackground(Color.yellow);
         }
       }   
       else
       {


     /* if( !isSelected )
      {
      	 Color highlight = null;
      	 if(uOS.isOSWindows())
      	 {
      	     highlight = VariablesGlobal.globalColorWindowsHighlight;//.activeCaption;	
      	   //ac.brighter().brighter();
             Color c = table.getBackground();
             if( (row%2)==0)
             {	  
                    setBackground(highlight); 
             }
             else
             {     setBackground(c);    }      	     
      	 }
      	 else
      	 {
      	 //	highlight = this.getForeground();
      	 }
      }*/
       	   
       }
          //
        /* }
         else 
         {
             comp.setForeground(null);
         }*/
          
       comp.repaint();
          
         return( comp );
       }});

   	  	
   	   }


       table.revalidate();
   	  
      //col.getCellRenderer().setBackground(Color.yellow); 
   	
   	  return listMessages;
   }  
  
   /*private void setVisibleOrEditableFields(EntityDBFields[] dbFieldsMany)
   {
 //exists both in rowNew and setEntity. The reason is that it has to be called when is already in edit row and would like to make a new row 
          for (int c = 0; c < dbFieldsMany.length; c++)
          {
              String columnClass =  dbFieldsMany[c].getColClassName();
              String fieldName = dbFieldsMany[c].getDbField();
              int visibilityEditability = dbFieldsMany[c].getIsVisibleOrEditable();
  //System.out.println("PanelOneDataOnRecData.setVisibleOrEditableFields     --("+i+")--    "+fieldName+"     fieldTxts.size()"+fieldTxts.size()+"     isVisOrEdit:"+isVisOrEdit+" (is false "+FIELD_VISIBLE_NOT_EDITABLE_ALWAYS+")");
               System.out.println("PanelODMRData.setVisibleOrEditableFields     "+c+"       fieldName:"+fieldName+"       "+columnClass+"  "+visibilityEditability); 
               
               
       /*FIELD_VISIBLE_AND_EDITABLE = 0;
        FIELD_VISIBLE_NOT_EDITABLE_ALWAYS = 1;
        FIELD_VISIBLE_NOT_EDITABLE_WHENEDIT_BUT_EDITABLE_ON_NEW = 2;
        FIELD_NOT_VISIBLE = 3;               */
               
        /*       if(dbFieldsMany[c].getIsVisibleOrEditable()==FIELD_VISIBLE_NOT_EDITABLE_ALWAYS)
               {
                   
               }
               
               
          }
   }*/
   
   
   
   
   
  private int packColumnsWritable(boolean isSetColWidthFromEntityData,EntityDBFields[] dbFieldsMany)
  {
  	     //System.out.println("PanelODMRData.packColumns "+table.getRowCount());
  	     
         //for each column pack
         int totalWidthOfColumns =0; 
         for (int c=0; c<table.getColumnCount(); c++)
         {   // table,column, margin
                //System.out.println("PanelODMRData.packColumns "+c);

             
                if(tableModelResultSet.getColumnClass(c) == Date.class)
                {
                	//System.out.println("PanelOneDataManyRecData.packColumns "+c+" "+dbFieldsMany[c].getCaption());
            	    totalWidthOfColumns = totalWidthOfColumns + utilsTable.packColumn(table, c, 18,true,isSetColWidthFromEntityData,dbFieldsMany[c]);
                } 	
                else if(dbFieldsMany[c].getIsVisibleOrEditable()==FIELD_NOT_VISIBLE)
                {  if(VariablesGlobal.globalFieldIsVisibleWhenSetNotVisible)
                   {
                      totalWidthOfColumns = totalWidthOfColumns + utilsTable.packColumn(table, c, 3,true,isSetColWidthFromEntityData,dbFieldsMany[c]); 
                   }
                   else
                   {
                      totalWidthOfColumns = totalWidthOfColumns + utilsTable.packColumn(table, c, 3,false,isSetColWidthFromEntityData,dbFieldsMany[c]);  
                   }
                }
                else
                {
                	//System.out.println("PanelOneDataManyRecData.packColumns "+dbFieldsMany);//+" "+dbFieldsMany[c].getCaption());
            	    totalWidthOfColumns = totalWidthOfColumns + utilsTable.packColumn(table, c, 3,true,isSetColWidthFromEntityData,dbFieldsMany[c]);
                }
             
         }       
    return totalWidthOfColumns;// needed for setScrollPaneSize in panelODMRD
  	
  }

  
    private int packColumnsReadOnly()
  {
  	     //System.out.println("PanelODMRData.packColumns "+table.getRowCount());
  	     
         //for each column pack
         int totalWidthOfColumns =0; 
         for (int c=0; c<table.getColumnCount(); c++)
         {   // table,column, margin
                //System.out.println("PanelODMRData.packColumns "+c);
             if(tableModelReadOnly.getColumnCount()>1)
             {  
                if(tableModelReadOnly.getColumnClass(c) == Date.class)
                {
                	//System.out.println("PanelOneDataManyRecData.packColumns "+c+" "+dbFieldsMany[c].getCaption());
            	    totalWidthOfColumns = totalWidthOfColumns + utilsTable.packColumn(table, c, 18,true,false,null);
                } 	
                else
                {
                	//System.out.println("PanelOneDataManyRecData.packColumns "+dbFieldsMany);//+" "+dbFieldsMany[c].getCaption());
            	    totalWidthOfColumns = totalWidthOfColumns + utilsTable.packColumn(table, c, 3,true, false,null);
                }
             }
         }       
    return totalWidthOfColumns;// needed for setScrollPaneSize in panelODMRD
  	
  }
  
    private void showExtendedSummaryCalcs(String entity,String queryIn, boolean isNewRecIn ,String primKeyValueIn)
    {
    /*	  
          //System.out.println("panelODMRData.showExtendedSummaryCalcs query "+queryIn);

   	    db.retrieveDBDataFromQuery(queryIn,"PanelOneDataManyRecData.showExtendedSummaryCalcs");
   	    rs=db.getRS();
   	    rsmd=db.getRSMetaData();
          //rs = db.retrieveResultSet(query);
          //rsmd = db.retrieveRSMetaData(queryIn);
          
         try
         {
          

           String queryPercentage = "SELECT * FROM "+tablePercentage;
           //System.out.println("panelODMRData.showExtendedSummaryCalcs rsPercentage "+queryPercentage);
   	      db.retrieveDBDataFromQuery(queryPercentage,"PanelOneDataManyRecData.showExtendedSummaryCalcs");
   	      rsPercentage=db.getRS();
   	      rsmdPercentage=db.getRSMetaData();           
           //rsmdPercentage = db.retrieveRSMetaData(queryPercentage);
           //rsPercentage = db.retrieveResultSet(queryPercentage);
           

           databaseTableMeta.retrieveImpKsOnQuery(entity,null);

               ArrayList sumArray=new ArrayList();
             
              for (int i = 1; i <= rsmd.getColumnCount(); i++)//  i = fields
              {  
                 String columnLabel = rsmd.getColumnLabel(i); //get colunm name
                 //System.out.println("panelODMRData.showExtendedSummaryCalcs col label "+columnLabel);
                if ((databaseTableMeta.findForeignTable(columnLabel)!=null) &&(rsmd.getTableName(i).equalsIgnoreCase(entity))&&
                   (databaseTableMeta.findForeignTable(columnLabel).equalsIgnoreCase(tableCategory)))
                {
                  
               
               double sumRes=0;
               sumArray.clear();
           	   	int valueFK=-1;
           	   int cat=0;
           	   double sum1=0;
           	   double sum2=0;
               double sum3=0;
           	      rs.beforeFirst(); //Moves the cursor to the front of this ResultSet object, just before the first row
                  while ( rs.next() )
                  {
                   	 valueFK=rs.getInt(i);
                    // System.out.println("value "+value+" i "+i);
                  	 ResultSet rsCategory;
                  	 ResultSetMetaData rsmdCategory;
                     
                       String categoryQuery = "SELECT * FROM "+tableCategory+" WHERE "+tableCategoryKey+" = "+valueFK; //    ?  change getInt
                       //System.out.println("panelODMRData.showExtendedSummaryCalcs categoryQuery "+categoryQuery);
   	                    db.retrieveDBDataFromQuery(categoryQuery,"PanelOneDataManyRecData.showExtendedSummaryCalcs");
   	                    rsCategory=db.getRS();
   	                   //rsmd=db.getRSMetaData();                       
                       //rsCategory = db.retrieveResultSet(categoryQuery);
                       rsCategory = db.retrieveRow(rsCategory, 1);// go to the only row               
                       cat = rsCategory.getInt(intTableCategoryField);// get field data
                       //System.out.println("panelODMRData.showExtendedSummaryCalcs cat "+cat);
                      
                         	
                   	 sum1= sum1+getSum(cat,1,sumRes,tablePercentageKey,intTableActionValueField);
                   	 sum2= sum2+getSum(cat,2,sumRes,tablePercentageKey,intTableActionValueField);
                   	 sum3= sum3+getSum(cat,3,sumRes,tablePercentageKey,intTableActionValueField);
                   	 
                    //System.out.println("panelODMRData.showExtendedSummaryCalcs sumRes "+intTableActionValueField+" "+sum1+" "+sum2+" "+sum3);
                  } //while ( rs.next() )
                  sumArray.add(sum1);
                  sumArray.add(sum2);
                  sumArray.add(sum3);
                  
                  }//if
              } //for getColumnCount()
      

          
           double percentage=0.0;
           double result=0.0;
           int percentagesInt=0;
           
           
           summary="<table align=right>"+
           "<tr align=right>";
           for(int s=0;s<sumArray.size();s++)
           {           
             summary=summary+"<td>"+uDouble.getDoubleReading((Double)sumArray.get(s))+"</td>";
             //System.out.println("panelODMRData.showExtendedSummaryCalcs sumRes "+uDouble.getDoubleReading((Double)sumArray.get(s)));
           }
           summary=summary+"</tr>"+
           "<tr align=right>";  // tableCalc 
  
         ArrayList resultArray=new ArrayList();
           if(rsmdPercentage.getColumnCount() == 0)
           {
                System.out.println("err PanelODMRData.showExtendedSummaryCalcs rsmdPercentage col count "+rsmdPercentage.getColumnCount());
           }
           
          rsPercentage.beforeFirst(); //Moves the cursor to the front of this ResultSet object, just before the first row
           for (int cp=1; cp<=rsmdPercentage.getColumnCount(); cp++)
           {  
                  //System.out.println(rsmdPercentage.getColumnName(cp)+" "+rsmdPercentage.getColumnClassName(cp) );

              //rsPercentage = db.retrieveRow(rsPercentage, 1); // query of invoice has the no of row of producttype
              //System.out.println("PanelODMRData.showExtendedSummaryCalcs producttype "+cp+" ");
              if (rsmdPercentage.getColumnClassName(cp).equalsIgnoreCase("java.lang.Double") || rsmdPercentage.getColumnClassName(cp).equalsIgnoreCase("java.math.BigDecimal") )//it understands double as float
              {         
                   rsPercentage.beforeFirst();
                 int i =0;
                   while ( rsPercentage.next() )
                   {
 	         //          if (rsPercentage.getDouble(cp)!=0.0)// if to not show percentage 0%
 	         //          {
                        percentage=rsPercentage.getDouble(cp);
                        summary=summary+"<td>"+uDouble.getDoubleReading(percentage)+"%</td>";
                        //System.out.println(" cp "+(cp-1)+"  i"+i+"-"+(Double)sumArray.get(i)*(percentage/100)+"="+result);
                        result=(Double)sumArray.get(i)*(percentage/100);

                        resultArray.add(result);
                        percentagesInt++;
              //         }
                     i++;                 
                   }//while
              }//if java.lang.Double  
              else
              {
              	 //enable if want to find class that is not processed as double
              	//System.out.println("err PanelODMRData.showExtendedSummaryCalcs class "+rsmdPercentage.getColumnClassName(cp));
              }                      	
           }// for

           summary=summary+
           "</tr>"+
           "<tr align=right>";
           if(percentagesInt == 0)
           {
              System.out.println("err PanelODMRData.showExtendedSummaryCalcs percentagesInt "+percentagesInt);       
           }
           
           for (int res=0;res<percentagesInt;res++)
           {
                double finalResult = Double.valueOf(resultArray.get(res).toString()).doubleValue(); 
                
           	    //System.out.println("panelODMRData.setEntity res"+res+" "+percentagesInt+" "+finalResult);
                //System.out.println("char . "+finalResult.indexOf('.'));
                

        
                summary=summary+"<td>"+uDouble.getDoubleReading(finalResult)+"</td>";
           }
           

           summary=summary+"</tr>"+
           "</table>";
           
          // System.out.println("PanelODMRData.showExtendedSummaryCalcs"+summary);
           
          closeDB();

           
           lblBottom.revalidate();
           //this.revalidate();
           

       */  

                  
    }
    
 /*   private double getSum(int cat,int percentageId, double sumRes,String tablePercentageKey,int intTableActionValueField)
    {
    	try
    	{           rsPercentage.beforeFirst();
              	   	while (rsPercentage.next())
              	   	{
                      //System.out.println("panelODMRData.getSum rsPercentage"+cat+" "+rsPercentage.getInt(tablePercentageKey));
                       if (cat==percentageId)//rsPercentage.getInt(tablePercentageKey) )
                   	   {
                   	     //System.out.println("PanelODMRData.getSum cat "+cat+" rs.getDouble "+intTableActionValueField);
                         sumRes=rs.getDouble(intTableActionValueField);
                         //System.out.println("panelODMRData.getSum sumRes "+sumRes);
                   	   }// if  (cat==1 )
                  	}//while
                   	
         }
         catch ( SQLException sqlex)
         {  // if the error is: Column index out of range then perhaps the LookUp is not registered
            System.out.println("error:PanelOneDataManyRecData.getSum: "+sqlex.getMessage());
         } 
        closeDB();
    	return sumRes;
    }
*/
     // called by this.selection, filter, MediatorPanelTwoDataOneRec
    //public void retrieveDataFromReadOnlyTable(String queryIn,String entityIn,String[] sql2WhereFieldIn,String[] sql2WhereValueIn, String primKeyValueIn)
    public String retrieveDataFromReadOnlyTable(String queryIn,String entityIn)
    {
       
        if(VariablesGlobal.globalShowReadDatabaseSQL)
        {
          System.out.println("PanelOneDataManyRecData.retrieveDataFromReadOnlyTable queryIn:"+queryIn);    
        }
        
        
     // jdk6
/*       if ( !isEditable )
       {
        RowSorter<TableModel> sorter = new BetterTableRowSorter<TableModel>(tableModelReadOnly);
        table.setRowSorter(sorter);
        //jdk6
       }
*/  
   int[] fieldsShowColumns=null;
   String[] tagElements ={"name"};
   String[] tagElementsType ={"String"};
    	XMLReader reader = new XMLReader();
     if(reader.checkIfElementExists(filePrefs, "Table",tagElements,tagElementsType,entity))
     {
     	
          String[] tagElements1 ={"name","showColumns"};
          String[] tagElementsType1 ={"String", "String"};
       int[]  fieldsShowColumnsNew = utilsPanelReport.loadDataFromXmlFileRetIntArray(filePrefs, "Table",tagElements1,tagElementsType1,1,entity); 
  	
     	
     	fieldsShowColumns=fieldsShowColumnsNew;
       if(utilsPanelReport.getSubQueryOrderByForPreferences(entity).equalsIgnoreCase(""))
       {
       	  
       }
       else
       {     	
         queryIn=utilsString.getQueryWithoutOrderby(queryIn)+utilsPanelReport.getSubQueryOrderByForPreferences(entity);   
       }
     }
     else
     {
     	
     }   
   //System.out.println(" oooooooooooooo   PanelODMRData.retrieveDataFromReadOnlyQuery XML read     queryIn:"+queryIn);
        //tableModelReadOnly= new TableModelReadOnly();
   //     table.setModel(tableModelReadOnly);
        
        tableModelReadOnly.setQuery(queryIn);
     
        scrollpaneTable.setViewportView(table);
        
        columnModel = new TableColumnModelCustom();
         
        table.setColumnModel(columnModel);
        table.createDefaultColumnsFromModel();
  
       for (int i = 0; i < tableModelReadOnly.getColumnCount(); i++)//  i = fields
      {       
        
       if(intValidationColumn==i)
       {
       	if(intValidationType==FIELD_VALIDATION_AFM)
       	{
        	   for(int r= 0 ;r<tableModelReadOnly.getRowCount(); r++)
        	   {       	
        	   	  Object val = tableModelReadOnly.getValueAt(r, i);
        	   	  //System.out.println("PanelODMRData. check afm "+val);
       	          if(val!=null)
       	          {
       	          	 //table.getCell(r,i).setTableCellRendererComponent(new DefaultTableCellRenderer()
       	             TableColumn tcol;
                     tcol = columnModel.getColumn(i);
                     tcol.setCellRenderer(new TableCellRendererValidation());
       	          }
       	          else if(val==null)
       	          {
       	          	 TableColumn tcol;
                     tcol = columnModel.getColumn(i);
                     tcol.setCellRenderer(new TableCellRendererValidation());      	          	
       	          }
        	   }// for
        }//if
       }// if
       }// for  
       
           //System.out.println("PanelODMRData.retrieveDataFromReadOnlyTable  rowcount "+tableModelReadOnly.getRowCount()+" columncount"+tableModelReadOnly.getColumnCount());
            if(  intCheckBoxesColumn!=-1 )
           {
        	boolean thereAreChecked = false;
        	boolean thereAreNotChecked = false;

                if(tableModelReadOnly.getRowCount()>0)
        	{
        	  panelInfo.setVisible(true);	
        	   for(int r= 0 ;r<tableModelReadOnly.getRowCount(); r++)
        	   {   // if error change intCheckBoxesColumn to -1
        	       Boolean chcked = false;
        	      // System.out.println("PanelODMRData.showExtendedSummary "+intCheckBoxesColumn);

                       Object val = tableModelReadOnly.getValueAt(r, intCheckBoxesColumn);
                       //System.out.println("error PanelODMRData.retrieveDataFromReadOnlyQuery row"+r+" col"+intCheckBoxesColumn+" is null val="+val);
               if(val!=null)
               {    
        	       if( val.toString().equals("1") || val.toString().trim().equalsIgnoreCase("true")  )
        	       {
        	       	 chcked= true;
        	       }
        	       else if (val.toString().equals("0") || val.toString().trim().equalsIgnoreCase("false"))
        	       {
        	       	  chcked = false;
        	       }
        	       else
        	       {
                           chcked = (Boolean)val;
        	       	 System.out.println("error PanelODMRData.retrieveDataFromReadOnlyQuery row"+r+" col"+intCheckBoxesColumn+" is null val="+val);
        	       }
        	       
        	       if(chcked)
        	       {
        	   	       thereAreChecked = true;
        	       }
        	       else
        	       {
        	   	    //break;
        	   	       thereAreNotChecked = true;
        	       }
                 } // val!=null
                 else
                 {
                     System.out.println("error PanelODMRData.retrieveDataFromReadOnlyQuery row"+r+" col"+intCheckBoxesColumn+" is null val="+val+" chcked="+chcked);
                 }

                 //System.out.println("error PanelODMRData.retrieveDataFromReadOnlyQuery row"+r+" col"+intCheckBoxesColumn+" is null val="+val+" chcked="+chcked);
               
               
        	    }// for
        	 }// rowcount not >0
        	 else
        	 {
        	  		panelInfo.setVisible(false);
        	 }

        	if(thereAreChecked && !thereAreNotChecked)// checked only
        	{
        	     lblInfo.setText(checkBoxInfo[2]);
        	    panelInfo.setVisible(true);
        	    timerForPanelInfo.start();         	     

        	}
        	else if(!thereAreChecked && thereAreNotChecked) // not checked only
        	{
        		lblInfo.setText(checkBoxInfo[0]);
        	    panelInfo.setVisible(true);
        	    timerForPanelInfo.start();        		
        	}
        	else if (thereAreChecked && thereAreNotChecked)// checked and not checked
        	{
        	    lblInfo.setText(checkBoxInfo[1]);
        	    panelInfo.setVisible(true);
        	    //timerForPanelInfo.start();         	    
        	}

        }
        else
        {
        	panelInfo.setVisible(false);
        }

       if(fieldsShowColumns==null)// for table that there is not a writen configuration use dbfields
       {
         /*if(isEditable)
         {
          for (int f = 0 ; f < dbFieldsMany.length; f++)
          {

              //boolFieldsShowColumns[f]= !dbFieldsMany[f].getIsHidden();
              TableColumn column  = columnModel.getColumnByModelIndex(f);
              columnModel.setColumnVisible(column, !dbFieldsMany[f].getIsHidden());
          }
         }
         else
         {*/
             columnModel.setAllColumnsVisible();  
       //  }
       }
       else
       {
         for(int i = 0;i<fieldsShowColumns.length;i++) 
         {       	
       	//System.out.println("PanelOneDatManyRecData.retrieveDataFromWritableTable "+i+" "+fieldsShowColumns[i]);	
        if(fieldsShowColumns[i]!=0)
        {
         	TableColumn column  = columnModel.getColumnByModelIndex(i);
                boolean     visible = columnModel.isColumnVisible(column);
                columnModel.setColumnVisible(column, visible);         	
                //System.out.println("PanelOneDatManyRecData.retrieveDataFromWritableTable visible "+i+" "+fieldsShowColumns[i]+" "+visible);	
        }
         else
         {
         	TableColumn column  = columnModel.getColumnByModelIndex(i);
                boolean     visible = columnModel.isColumnVisible(column);
                columnModel.setColumnVisible(column, !visible);
                //System.out.println("PanelOneDatManyRecData.retrieveDataFromWritableTable NOT visible "+i+" "+fieldsShowColumns[i]+" "+!visible);
         }
        }
       }
      totalWidthOfColumns = packColumnsReadOnly();
       //System.out.println("PanelODMRData.retrieveDataFromReadOnlyTable  rowcount "+tableModelReadOnly.getRowCount()+" columncount"+tableModelReadOnly.getColumnCount());

        showExtendedSummary(tableModelReadOnly);
        return queryIn;
    }

       /*
    * 
    * called by PanelOneDataOneRecData.rowUpdateTables Reason to have new key when insetring a new record in PanelOneDataOneRecData
    */
    
    public void setPrimKeyValueInTableModelResultSet(String primKeyValueIn)
    {
        
        

        
        tableModelResultSet.setPrimKeyValueFromPanelOneData(primKeyValueIn);
    }
    
    /* called by
    * called from filterForWritableTable
     *  Has editors, can be added DocumentHandler like PanelODORData so it can understand changes in textboxes without waiting for tablemodel.savechanges
    * public for PanelODORecData.calculationFromToolBarButton table
     */
    private void retrieveDataFromWritableTable(String queryIn,String entityIn,String primKeyDbFromParentIn, String primKeyValueFromParentIn,
            boolean isEditableIn, boolean isNewRecIn, boolean isCopyFromNewRecIn, boolean isQuery2In)
    {
        //System.out.println("PanelOneDataManyRecData.retrieveDataFromWritableTable primKeyDbIn:"+primKeyDbIn+" primKeyDb:"+primKeyDb+"     isNewRec:"+isNewRec+"   isQuery2:"+isQuery2+"  queryIn:"+queryIn +"       queryMany:"+queryMany);
    	
        isQuery2=isQuery2In;
    	isNewRec=isNewRecIn;
        listTableCellEditorLookup = new ArrayList();
//        tableModelResultSet= new TableModelResultSet();

        // load preferences
      int[] fieldsShowColumns=null;
      String[] tagElements ={"name"};
      String[] tagElementsType ={"String"};    	
    	XMLReader reader = new XMLReader();
     if(reader.checkIfElementExists(filePrefs, "Table",tagElements,tagElementsType,entity))
     {
     	
          String[] tagElements1 ={"name","showColumns"};
          String[] tagElementsType1 ={"String", "String"};  
       int[]  fieldsShowColumnsNew = utilsPanelReport.loadDataFromXmlFileRetIntArray(filePrefs, "Table",tagElements1,tagElementsType1,1,entity); 
  	
     	
     	fieldsShowColumns=fieldsShowColumnsNew;
       if(utilsPanelReport.getSubQueryOrderByForPreferences(entity).equalsIgnoreCase(""))
       {

          
       }
       else
       {     	
         queryIn=utilsString.getQueryWithoutOrderby(queryIn)+utilsPanelReport.getSubQueryOrderByForPreferences(entity);   
       }
     }
     else // if DBFields in EntityData has a fiels hidden then not show it
     {
    	
     }  
      if(VariablesGlobal.globalShowSelectDataFromWritable)
      {
        System.out.println("..... PanelOneDataManyRecData.retrieveDataFromWritableTable  queryIn:"+queryIn+"   isQuery2:"+isQuery2+"   isNewRec:"+isNewRec);
        System.out.println("--- PanelODMRData.retrieveDataFromWritableTable   primKeyValueFromParentIn:"+primKeyValueFromParentIn+"   isNewRec:"+isNewRec+"  isQuery2:"+isQuery2+"   queryMany:"+queryMany+"      query:"+query+"      queryIn:"+queryIn);//+"  queryOrderby:"+queryOrderby+"    queryWithoutOrderby:"+queryWithoutOrderby); 
      }
        if (isQuery2)
        {
          

      if(VariablesGlobal.globalShowSelectDataFromWritable)
      {
        System.out.println("OOOO PanelOneDataManyRecData.retrieveDataFromWritableTable   A      isQuery2:"+isQuery2+"     primKeyDbFromParentIn:"+primKeyDbFromParentIn+" primKeyDb:"+primKeyDb+"     isNewRec:"+isNewRec+"   isQuery2:"+isQuery2+"   queryIn:"+queryIn +"       queryMany:"+queryMany);
      }
        retrievePrimKeyValueForWritableTable(queryIn,selectedTableRow);//,primKeyDb,0);  // be carefull. it is query(set in setEntity short), not queryIn
        
        
          queryMany=queryIn;
        }
        else
        {
            

            queryMany=queryIn;
            String queryWithoutOrderby = utilsString.getQueryWithoutOrderby(queryMany);
	    String queryOrderby = utilsString.getOrderbySubQuery(queryMany);
            queryMany = queryWithoutOrderby+" "+queryOrderby;             
      if(VariablesGlobal.globalShowSelectDataFromWritable)
      {            
            System.out.println("OOOO PanelOneDataManyRecData.retrieveDataFromWritableTable  B  isQuery2:"+isQuery2+"     queryMany:"+queryMany +"   query:"+query );
      }
        }
       
         

        
        
        
   //System.out.println("O PanelOneDataManyRecData.retrieveDataFromWritableTable  --A--   primKeyDbFromParentIn:"+primKeyDbFromParentIn+"     primKeyDb:"+primKeyDb);

    
   //     table.setModel(tableModelResultSet);
//  for writable       tableModelResultSet.setQuery(queryIn, entityIn,dbFieldsMany,primKeysMany,primKeysManyTran,sql2WhereField,sql2WhereValue,primKeyValueIn);
        tableModelResultSet.setQuery(queryMany, entityIn,dbFieldsParent,dbFieldsMany,isNewRec,isCopyFromNewRecIn,primKeyDbFromParentIn,primKeyValueFromParentIn,isEditableIn,panelODORData);        

        
        //   public void setQuery(String query,String entity,EntityDBFields[] dbFieldsManyIn,String[] primKeysManyIn, String[] sql2WhereFieldIn, String[] sql2WhereValueIn )
        //System.out.println("PanelOneDataManyRecData.retrieveDataFromWritableTable q "+queryIn+" tableModelResultSet "+table );
        scrollpaneTable.setViewportView(table);        
      if(VariablesGlobal.globalShowSelectDataFromWritable)
      {
        System.out.println("   PanelOneDataManyRecData.retrieveDataFromWritableTable   C    isEditableIn:"+isEditableIn);
      } //System.out.println("PanelOneDataManyRecData.retrieveDataFromWritableTable  "+queryIn);


     
         // tableModelListenerGeneric
        
      for (int i = 0; i < tableModelResultSet.getColumnCount(); i++)//  i = fields
      {  
       
         String columnLabelTranslated;
         String columnDBName;
         int columnLength;
         //Class columnClass = null;//tableModelResultSet.getColumnClass(i);
         //String columnClass = dbFields[i].getColClassName();
         String columnType="java.lang.String";
         int columnFieldObligatoryOrSuggest=0;     	
         int columnFieldValidation=0 ;


         if(!isQuery2In)
         {
            columnDBName=tableModelResultSet.getColumnDBName(i);//in english
         }
         else
         {
            columnDBName = dbFieldsMany[i].getDbField();
            columnFieldObligatoryOrSuggest=dbFieldsMany[i].getFieldObligatoryOrSuggest();
            columnFieldValidation =dbFieldsMany[i].getValidation();
            columnType = dbFieldsMany[i].getColClassName();
         }
         

         
    /*     columnClass=tableModelResultSet.getColumnClass(i);
         if (columnClass==Date.class)
         { columnType="java.lang.Date";  }
         else if (columnClass==String.class)
         { columnType="java.lang.String";  }
         else if (columnClass==Integer.class)
         { columnType="java.lang.Integer";  }
         else if (columnClass==Double.class)
         { columnType="java.lang.Double";  }*/

        
         //   System.out.println("PanelODMRData.retrieveDataFromWritableTable - "+i+" "+columnLabel+" "+columnType+" "+tableModelResultSet.getColumnCount());

         //System.out.println("PanelODMRData.retrieveDataFromWritableTable - "+i+" "+columnDBName+" "+dbFieldsMany);

         String luname ="";   //if foreign table = null assign
                //if ((databaseTableMeta.findForeignTable(columnDBName)==null) )//&&(!rsmd.getTableName(i).equalsIgnoreCase(entity)))
                if(isEditableIn && dbFieldsMany[i].getLookupType()== LOOKUPTYPE_NOLOOKUP && dbFieldsMany[i].getLookupEntityName()==null)
                {
                    luname =entity; //rsmd.getTableName(i);
                  //System.out.println("panelODORData.setEntity if ft :"+ft);
                }
                else if (isEditableIn && dbFieldsMany[i].getLookupType()== LOOKUPTYPE_ONLYONE_THISFIELD && (dbFieldsMany[i].getLookupEntityName()!=null) )
                {
                	luname = dbFieldsMany[i].getLookupEntityName();//lookUp.getTable(dbFieldsMany[i].getLookupEntityName());
                                
                	//System.out.println("panelODORData.setEntity else ft :"+ft+" for "+dbFieldsMany[i].getLookupEntityName());
                }
                else if (isEditableIn && dbFieldsMany[i].getLookupType()== LOOKUPTYPE_TABLECONSTANTS && (dbFieldsMany[i].getLookupEntityName()!=null) )
                {
                   System.out.println("--> panelODMRData.retrieveDataFromWritableTable() ELSE  LOOKUPTYPE_TABLECONSTANTS  luname:"+luname+"  entity:"+entity+"   isEditableIn:"+isEditableIn+" columnDBName: "+columnDBName+"    dbFieldsMany[i].getLookupType(): "+dbFieldsMany[i].getLookupType()+"  dbFieldsMany[i].getLookupEntityName():"+dbFieldsMany[i].getLookupEntityName());                    
                }
                else
                {
                    System.out.println("panelODMRData.retrieveDataFromWritableTable() ELSE NOT DEFINED  luname:"+luname+"  entity:"+entity+"   isEditableIn:"+isEditableIn+" columnDBName: "+columnDBName+"    dbFieldsMany[i].getLookupType(): "+dbFieldsMany[i].getLookupType()+"  dbFieldsMany[i].getLookupEntityName():"+dbFieldsMany[i].getLookupEntityName());
                }

                //System.out.println("panelODMRData.retrieveDataFromWritableTable() -  "+entity+" "+tableModelResultSet.getColumnTable(i)+"."+columnDBName);
                //System.out.println("panelODMRData.retrieveDataFromWritableTable() "+ft+" "+entity+" "+columnDBName);
                              
                // if has foreign key then calculate
               // final String lunameFinal = lun;   

    // if(isEditable)
    // {
         
         columnLabelTranslated = tableModelResultSet.getColumnName(i);// the translated
      //System.out.println("PanelODMRData.retrieveDataFromWritableTable ("+i+") columnLabelTranslated:"+columnLabelTranslated+"  "+isEditable+" "+columnType);
         columnLength=tableModelResultSet.getColumnLength(i);
         

        if(columnType.equalsIgnoreCase("java.lang.Date") || columnType.equalsIgnoreCase("java.sql.Date"))
        {
        	MaskFormatter mask = null;
        	
           /*try
           {
                 mask = new MaskFormatter("##-##-##");
           }
           catch(ParseException pe)
           {
                    System.out.println("error panelODMRData.retrieveDataFromWritableTable  date   "+columnClass);
           }*/
        	
           JFormattedTextField textFieldDate = new JFormattedTextField(mask);
           //System.out.println("panelODMRData.retrieveDataFromWritableTable "+columnClass);
           
           //JTextField textFieldDate = new JTextField();
           textFieldDate.setBorder(BorderFactory.createEmptyBorder());
           textFieldDate.setDocument(new PlainDocumentInsertText(columnLength,columnType));//limiting the capacity of txt
           TableCellEditorDate tceDate = new TableCellEditorDate(textFieldDate,yearEnforceInLines,utilsDate);
           tceDate.setClickCountToStart(VariablesGlobal.jtableEditableClickCountToStart); //exists inside TableCellEditorLookup
           table.getColumn(columnLabelTranslated).setCellEditor(tceDate); 
           //System.out.println("panelODMRData.retrieveDataFromWritableTable "+columnClass+" "+columnLabelTranslated);
        }
        else if (columnType.equalsIgnoreCase("java.lang.Double"))
        {        	
        	
        	//System.out.println("panelODMRData.retrieveDataFromWritableTable "+columnClass);
        	
           JTextField textFieldDouble = new JTextField();
           textFieldDouble.setBorder(BorderFactory.createEmptyBorder());
           textFieldDouble.setDocument(new PlainDocumentInsertText(columnLength,columnType));//limiting the capacity of txt
           
           TableCellEditorDouble tceDouble = new TableCellEditorDouble(textFieldDouble,uDouble);
           tceDouble.setClickCountToStart(VariablesGlobal.jtableEditableClickCountToStart); 
           table.getColumn(columnLabelTranslated).setCellEditor(tceDouble);	
           //System.out.println("panelODMRData.retrieveDataFromWritableTable "+columnClass+" "+columnLabelTranslated);
        }
        else if (columnType.equalsIgnoreCase("java.lang.Integer"))
        {
            //System.out.println("PanelODMRData.retrieveDataFromWritableTable ("+i+") columnLabelTranslated:"+columnLabelTranslated+"  "+isEditable+" "+columnType);
           JTextField textFieldInt = new JTextField();
           //textFieldString.setBorder(BorderFactory.createEmptyBorder());
           textFieldInt.setDocument(new PlainDocumentInsertText(columnLength,columnType));//limiting the capacity of txt
           
           TableCellEditorInteger tceInteger = new TableCellEditorInteger(textFieldInt,dbFieldsMany[i]);
           tceInteger.setClickCountToStart(VariablesGlobal.jtableEditableClickCountToStart); 
           table.getColumn(columnLabelTranslated).setCellEditor(tceInteger);	        	
        }
        else //if (columnType.equalsIgnoreCase("java.lang.String"))
        {
        	//System.out.println("PanelOneDataManyRecData.seEntity "+columnDBName+" "+tableModelResultSet.getColumnTable(i)+" "+entity);
    		if (tableModelResultSet.getColumnTable(i)!=null && !tableModelResultSet.getColumnTable(i).equals("") && !tableModelResultSet.getColumnTable(i).toUpperCase().equalsIgnoreCase(entity.toUpperCase()))//(foreignTable!= null) && (!ft.toUpperCase().equals(entity.toUpperCase())))
    		{
    			//in this case do not assign editor because it will be assigned later in " lookUp.getLookUpIntNoOfColsWhenInTable(ft)==2) "
	
    		}
    		else
    		{
                  JTextField textFieldString = new JTextField();
                  //textFieldString.setBorder(BorderFactory.createEmptyBorder());
                  textFieldString.setDocument(new PlainDocumentInsertText(columnLength,columnType));//limiting the capacity of txt
           
                  TableCellEditorString tceString = new TableCellEditorString(textFieldString);
                  tceString.setClickCountToStart(VariablesGlobal.jtableEditableClickCountToStart); 
                 // System.out.println("PanelODMRData.retrieveDataFromWritableTable i:"+i+" columnLabelTranslated:"+columnLabelTranslated);
                  table.getColumn(columnLabelTranslated).setCellEditor(tceString);	   			
    		}

        }

         //System.out.println("PanelOneDataManyRecData.seEntity "+columnLabel);
         //System.out.println("panelODMRData.setEntity() - "+entity+" "+tableModelResultSet.getColumnTable(i)+" "+columnLabel);
         
                 // lookup cell editor
                if(dbFieldsMany[i].getLookupType()== LOOKUPTYPE_NOLOOKUP)
                {
                    
                }
                else if (dbFieldsMany[i].getLookupType()== LOOKUPTYPE_ONLYONE_THISFIELD && dbFieldsMany[i].getLookupEntityName()!=null)//(foreignTable!= null) && (!ft.toUpperCase().equals(entity.toUpperCase())))//(!rsmd.getTableName(i).equalsIgnoreCase(entity)))
                {
                  //  System.out.println("panelODORData.setEntity() "+ft+" "+i+" "+columnDBName);

                    //ResultSetMetaData rsmdForeign;
                    //String foreignMetaQuery = "SELECT * FROM "+foreignTable;
                    //System.out.println("panelODMRData.setEntity foreignMetaQuery "+foreignMetaQuery);
                    
                    //int luFieldIdx = lookUp.getLookUpFieldIndex(foreignTable);   
                    //rsmdForeign = db.retrieveRSMetaData(foreignMetaQuery);
                   	
                  //System.out.println("PanelODMRData.retrieveDataFromWritableTable "+dbFieldsMany[i].getDbField()+" "+ dbFieldsMany[i].getLookupEntityName()+" "+ft);
                   
                    Action actionShowDialogNew = new ActionShowDialogNew(luname, i);
                    Action actionShowDialogEdit = new ActionShowDialogEdit(luname, i);                  
                    ArrayList listMenuCaption = new ArrayList();

                    JMenuItem menuListItemNew = new JMenuItem("εισαγωγή");
                    menuListItemNew.addActionListener(actionShowDialogNew);
                    listMenuCaption.add(menuListItemNew);

                    JMenuItem menuListItemEdit = new JMenuItem("επεξεργασία");
                    menuListItemEdit.addActionListener(actionShowDialogEdit);
                    listMenuCaption.add(menuListItemEdit);                   
                  
                    
                    
                      //After adding dbFieldsParent and dbFieldsMany
                      
                      
                  
                  
                  
                  //int intFieldToGetTheValue = calculateAllFieldsForFormVariable1(dbFieldsParent);
                 // System.out.println("---------panelODMRData.retrieveDataFromWritableTable -------LOOKUPTYPE_ONLYONE_THISFIELD----------  i:"+i+" "+dbFieldsMany[i].getLookupEntityName()+"  dbFields:"+dbFields+"    dbFieldsParent:"+dbFieldsParent.length+"     dbFieldsMany:"+dbFieldsMany.length);
                  
                  
                  String foreignTable = lookUp.getFromTheNameTheForeignTable(luname);
                   // lookup takes 1 column
                   if(lookUp.getLookUpIntNoOfColsWhenInTable(dbFieldsMany[i].getLookupEntityName())==1)  
                   {
                   	

                      JTextField textField = new JTextField();
                      textField.setBorder(BorderFactory.createEmptyBorder());
                      textField.setDocument(new PlainDocumentInsertText(columnLength,columnType));//limiting the capacity of txt
                      //String fieldVariableFromPreField = dbFieldsMany[i].getFormVariableFromField();
                      TableCellEditorLookupOne tceLookup = new TableCellEditorLookupOne(textField,columnLength,luname,listMenuCaption,entity,
                         frame,dbFieldsMany,i,dbFieldsParent, intTableOfParentDBFields,fieldTxts,panelManagement);
                      // dbFieldsParent is all when is here(table)thus in    WINDOWLOOKUP_IS_CALLED_IN_TABLE
                      
                      
                      
                      table.getColumn(columnLabelTranslated).setCellEditor(tceLookup); 
                      listTableCellEditorLookup.add(tceLookup);
                      
                      
                      TableCellRendererLookUp tcrLookUp = new TableCellRendererLookUp(luname,dbFieldsMany,entity);
                      table.getColumn(columnLabelTranslated).setCellRenderer(tcrLookUp);  
                      //System.out.println("panelODMRData.retrieveDataFromWritableTable lookup 1"+columnClass);
                      //System.out.println("panelODMRData.setEntity edit+renderer "+columnDBName);	
                      	                 	
                   }
                   // lookup takes 2 columns
                   else if(lookUp.getLookUpIntNoOfColsWhenInTable(dbFieldsMany[i].getLookupEntityName())==2)                   
                   {
                   
  
                     //System.out.println("panelODMRData.setEntity lookUp "+ft+" "+columnLabelTranslated+"-"+lookUp.getLookUpLabel(ft));
                       //EntityReport entityReport = lookUp.getEntityReport(foreignTable);
                     JTextField textField = new JTextField();
                     textField.setBorder(BorderFactory.createEmptyBorder());
                     textField.setDocument(new PlainDocumentInsertText(columnLength,columnType));//limiting the capacity of txt
                     TableCellEditorLookupTwoA tceLookupA = new TableCellEditorLookupTwoA(textField,columnLength,luname, foreignTable,listMenuCaption,entity,frame,dbFieldsMany,intTableOfParentDBFields,i,
                             panelManagement);
                     listTableCellEditorLookup.add(tceLookupA.getCellSelectedValue());
                     table.getColumn(columnLabelTranslated).setCellEditor(tceLookupA); 
                  
                     //TableCellRendererLookUp tcrLookUp = new TableCellRendererLookUp(foreignTable);
                     //table.getColumn(columnLabelTranslated).setCellRenderer(tcrLookUp);
                     
                     JTextField textFieldB = new JTextField();
                     textFieldB.setBorder(BorderFactory.createEmptyBorder());
                     textFieldB.setDocument(new PlainDocumentInsertText(80,"java.lang.String"));//limiting the capacity of txt
                     TableCellEditorLookupTwoB tceLookupB = new TableCellEditorLookupTwoB(textFieldB,columnLength,luname, foreignTable,listMenuCaption,frame,dbFieldsMany,intTableOfParentDBFields,i,
                             panelManagement);
                     //System.out.println("PanelODMRData.retrieveDataFromWritableTable i:"+i+" "+lookUp.getLookUpLabel(ft));
                     table.getColumn(lookUp.getLookUpLabel(luname)).setCellEditor(tceLookupB); 
                     tableModelResultSet.setColumnLookUpTwoB(i);// set column B so it does not calculate lookup column B
                     //TableCellRendererLookUp tcrLookUp = new TableCellRendererLookUp(foreignTable,dbFieldsMany);
                     //table.getColumn(columnLabelTranslated).setCellRenderer(tcrLookUp);
                   	//System.out.println("panelODMRData.retrieveDataFromWritableTable lookup 2"+columnClass);
                   }
                   
                  
                }
                else if (isEditableIn && dbFieldsMany[i].getLookupType()== LOOKUPTYPE_TABLECONSTANTS && (dbFieldsMany[i].getLookupEntityName()!=null) )
                {
                    System.out.println(" -->PanelODMRData.retrieveDataFromWritableTable   ELSE   LOOKUPTYPE_TABLECONSTANTS    columnDBName:"+columnDBName+"   dbFieldsMany[i].getLookupType(): "+dbFieldsMany[i].getLookupType()+"   dbFieldsMany[i].getLookupEntityName():"+dbFieldsMany[i].getLookupEntityName());
                }
                else
                {
                    System.out.println(" PanelODMRData.retrieveDataFromWritableTable   ELSE NOT DEFINED    columnDBName:"+columnDBName+"   dbFieldsMany[i].getLookupType(): "+dbFieldsMany[i].getLookupType()+"   dbFieldsMany[i].getLookupEntityName():"+dbFieldsMany[i].getLookupEntityName());
                }
       	  	
             TableHeaderRenderer thr = new TableHeaderRenderer();
             
             

              if(columnFieldObligatoryOrSuggest==FIELD_OBLIGATORY)
              {
                      thr.setIcon(ICO_FIELDOBLIGATORY);  
                      thr.setToolTip("<html><table><tr><td>Το πεδίο </td><td><img src=\""+ICO_FIELDOBLIGATORY+"\"></td><td>'"+columnLabelTranslated+"'</td><td> είναι υποχρεωτικό να συμπληρωθεί.</td></tr></table></html>");
              }
              else if(columnFieldObligatoryOrSuggest==FIELD_SUGGEST)
              {
                      thr.setIcon(ICO_FIELDSUGGEST); 
                      thr.setToolTip("<html><table><tr><td>Το πεδίο </td><td><img src=\""+ICO_FIELDSUGGEST+"\"></td><td>'"+columnLabelTranslated+"'</td><td> προτείνεται να συμπληρωθεί.</td></tr></table></html>");
              }
              else if(columnFieldObligatoryOrSuggest==FIELD_NOCOMPLETION)
              {
                    thr.setToolTip("<html><table><tr><td>πεδίο </td><td>'"+columnLabelTranslated+"'</td></tr></table></html>");	
              }	             
             //thr.setIcon(icon);
             TableColumn col = table.getColumnModel().getColumn(i);
             col.setHeaderRenderer(thr);       	  	


      }  // for              
       //System.out.println("PanelOneDataManyRecData.retrieveDataFromWritableTable  "+queryIn);//System.out.println("PanelOneDataManyRecData.retrieveDataFromWritableTable  "+queryIn);

        totalWidthOfColumns = packColumnsWritable(true,dbFieldsMany);
        
       // System.out.println("PanelODMRData.retrieveDataFromWritableTable showExtendedSummaryCalcs "+showExtendedSummaryCalcs);
        
        tableModelResultSet.closeDB();
        
            
        

    }


    /*
    *   exists differentiated with differences both in PanelODoRData and panelODMRData
    */
    /*private int calculateAllFieldsFromParentDBFieldsForFormVariable1(EntityDBFields[] dbFieldsInGroupOfPanels)
    {
        
       int intFieldToGetTheValue = utilsPanelReport.calculateAllFieldsFromParentDBFieldsForFormVariable1(dbFieldsInGroupOfPanels);
     // System.out.println("panelODMRData.calculateAllFieldsForFormVariable1    equals ?  i:"+i+" fieldVariableFromPreField:"+fieldVariableFromPreField+"   intFieldToGetTheValue:"+intFieldToGetTheValue); 
        return intFieldToGetTheValue;      
    }*/
    



    private void addNewRowBelow()
    {
         
       
      // setPrimKeyValueInTableModelResultSet(getPrimKeyValue()); 
       //System.out.println("-----panelODMRData.addNewRowBelow IF "+tableModelResultSet.hasEmptyRow()+"  primKeyValue:"+primKeyValue+"  tableModelResultSet.getRowCount():"+tableModelResultSet.getRowCount()+"    getSelectedTableRow()"+getSelectedTableRow());                
        String pKey = getPrimKeyValue();
       if (!tableModelResultSet.hasEmptyRow() && isEditable)// && !isNewRec)
       {
       //	System.out.println("-----panelODMRData.addNewRowIfThereIsnt IF "+tableModelResultSet.hasEmptyRow()+"  afterCurrentLine:"+afterCurrentLine+"  tableModelResultSet.getRowCount():"+tableModelResultSet.getRowCount()+"    getSelectedTableRow()"+getSelectedTableRow());                

                 
                 if(tableModelResultSet.getRowCount()==0)
                 {
       	     	        tableModelResultSet.addEmptyRow(0,pKey,table);
       	     	        //table.changeSelection(0,0,true,false);
                        table.setRowSelectionInterval(0,0); 
                        
                        //  http://stackoverflow.com/questions/7656568/jtable-select-next-cell-on-tab-but-first-focus-selects-same-cell-not-next-one
                        /*table.setColumnSelectionInterval(0, 0);
                        table.scrollRectToVisible(table.getCellRect(0, 0, true));
                        table.editCellAt(0, 0);
                        table.transferFocus();                        */
                 }
                 else
                 {
                     
                     int rowlast = tableModelResultSet.getRowCount();
                     tableModelResultSet.addEmptyRow(rowlast,pKey,table);
                     table.setRowSelectionInterval(rowlast, rowlast);
                     
                        //not working.cannot select the row  table.setRowSelectionInterval(tableModelResultSet.getRowCount()-1,tableModelResultSet.getRowCount()-1);
       	     	        //not working.cannot select the row table.setRowSelectionInterval(getSelectedTableRow()+1,getSelectedTableRow()+1);
                 }
                 
                showExtendedSummaryForWritable();
                int rowCount = tableModelResultSet.getRowCount();
                if(rowCount>0) //  do not make calculations when the first row is inserted
                {
                    panelODORData.calculateSumFields();
   //                 panelODORData.calculateAgainDbFields();
                }
                setDataHasChanged(true);
       	     
       }
       else
       {
           System.out.println("panelODMRData.addNewRowBelow  NOT SUPPORTED   hasEmptyRow:"+tableModelResultSet.hasEmptyRow()+"    isEditable:"+isEditable+"    isNewRec:"+isNewRec);
       }       
        
    }



    
    /*
    *
    * called by this
    * called by this
    * als0 called by PanelODORData.calculationFromToolBarButton,  table
    */
    
    public void addNewRowIfThereIsnt(boolean afterCurrentLine)
    {        
       String pKey = utilsPanelReport.getPrimKeyValue();
     
       //int allRowCount = tableModelResultSet.getRowCount();

  //System.out.println("-----panelODMRData.addNewRowIfThereIsnt IF "+tableModelResultSet.hasEmptyRow()+"  pKey:"+pKey+"    afterCurrentLine:"+afterCurrentLine+"    allRowCount:"+allRowCount+"   getSelectedTableRow()"+getSelectedTableRow());                
       if (!tableModelResultSet.hasEmptyRow() && isEditable)// && !isNewRec)
       {
       	//System.out.println("-----panelODMRData.addNewRowIfThereIsnt IF "+tableModelResultSet.hasEmptyRow()+"  afterCurrentLine:"+afterCurrentLine+"     allRowCount:"+allRowCount+"       getSelectedTableRow()"+getSelectedTableRow());                
       	     if(afterCurrentLine)
       	     { // when true, at the end of table
                 //System.out.println("-----panelODMRData.addNewRowIfThereIsnt IF   afterCurrentLine:"+afterCurrentLine+"     tableModelResultSet.getRowCount():"+tableModelResultSet.getRowCount()+"  pKey:"+pKey+"     getSelectedTableRow()"+getSelectedTableRow());                
                 if(tableModelResultSet.getRowCount()==0)
                 {
       	     	        tableModelResultSet.addEmptyRow(0,pKey,table);
       	     	   // table.changeSelection(0,0,true,false);
                        table.setRowSelectionInterval(0,0); 
                        
                        //  http://stackoverflow.com/questions/7656568/jtable-select-next-cell-on-tab-but-first-focus-selects-same-cell-not-next-one
                        /*table.setColumnSelectionInterval(0, 0);
                        table.scrollRectToVisible(table.getCellRect(0, 0, true));
                        table.editCellAt(0, 0);
                        table.transferFocus();                        */
                 }
                 else
                 {
                     
                     
                     tableModelResultSet.addEmptyRow(tableModelResultSet.getRowCount(),pKey,table);
                     
                        //not working.cannot select the row  table.setRowSelectionInterval(tableModelResultSet.getRowCount()-1,tableModelResultSet.getRowCount()-1);
       	     	        //not working.cannot select the row table.setRowSelectionInterval(getSelectedTableRow()+1,getSelectedTableRow()+1);
                 }
                
                showExtendedSummaryForWritable();

       	     }
       	     else// when is to be inserted before current line
       	     {  // when false at insert button clicked
       	        int row = getSelectedTableRow();
                int rowCount = tableModelResultSet.getRowCount();
          //System.out.println("-- --panelODMRData.addNewRowIfThereIsnt else (!afterCurrentLine:"+afterCurrentLine+")  tableModelResultSet:"+tableModelResultSet+"  "+tableModelResultSet.hasEmptyRow()+"  afterCurrentLine:"+afterCurrentLine+"  tableModelResultSet.getRowCount():"+tableModelResultSet.getRowCount()+"    getSelectedTableRow()"+getSelectedTableRow());                       

                if(rowCount==0)
       	        {
                    //System.out.println("panelODMRData.addNewRowIfThereIsnt   if  row:"+row+" rowCount:"+rowCount);
                     //System.out.println("--o--panelODMRData.addNewRowIfThereIsnt else A  hasemptyrow:"+tableModelResultSet.hasEmptyRow()+"  colcount:"+table.getColumnCount()); 
                    tableModelResultSet.addEmptyRow(0,pKey,table);
                    //System.out.println("--o--panelODMRData.addNewRowIfThereIsnt else B  hasemptyrow:"+tableModelResultSet.hasEmptyRow()+"  colcount:"+table.getColumnCount()); 
                     
       	         //table.changeSelection(0,0,true,false);	
                    table.setRowSelectionInterval(0,0);
                       
                        /*table.setColumnSelectionInterval(0, 0);
                        table.scrollRectToVisible(table.getCellRect(0, 0, true));
                        table.editCellAt(0, 0);
                        table.transferFocus();                           */
       	        }
       	        else
       	        {
                    //System.out.println("panelODMRData.addNewRowIfThereIsnt   else  row:"+row+" rowCount:"+rowCount);
                    if(row!=-1)
                    {
                        tableModelResultSet.addEmptyRow(row,pKey,table);	
       	        	table.setRowSelectionInterval(row,row);
                    }
                    else
                    {
                        tableModelResultSet.addEmptyRow(rowCount,pKey,table);	
       	        	table.setRowSelectionInterval(rowCount,rowCount);
                    }
                    
                        //table.setColumnSelectionInterval(row, row);
                       
                        /*table.setColumnSelectionInterval(row, 0);
                        table.scrollRectToVisible(table.getCellRect(row, 0, true));
                        table.editCellAt(row, 0);
                        table.transferFocus();   */
       	        }
                
       	        showExtendedSummaryForWritable();
                //if(rowCount>0)//  do not make calculations when the first row is inserted
                //{
                //    panelODORData.calculateSumFields();
     //               panelODORData.calculateAgainDbFields();
                //}
       	     }
                int rowCount = tableModelResultSet.getRowCount();
               System.out.println("-- --panelODMRData.addNewRowIfThereIsnt   calculateSumFields rowCount:"+rowCount);
                if(rowCount>0) //  do not make calculations when the first row is inserted
                {
                    panelODORData.calculateSumFields();
                    //panelODORData.calculateAgainDbFields();
                }
       	     
       }
       else
       {
           System.out.println("panelODMRData.addNewRowIfThereIsnt  NOT SUPPORTED   hasEmptyRow:"+tableModelResultSet.hasEmptyRow()+"    isEditable:"+isEditable+"    isNewRec:"+isNewRec);
       }
    	
    }
    
    /*
    *
    *
    */
    public void setScrollPaneSize(int heightFixed)
    {
        int totalwidth=packColumnsReadOnly();
    	setScrollPaneSize(totalwidth,heightFixed);
    }
    
    
    /*
    * called by up 
    */
    private void setScrollPaneSize(int totalWidthOfColumns,int heightFixed)
    {
        int height=100;
        int width=100;
        int columnCount=table.getColumnCount();
        int rowCount=table.getRowCount();

        //System.out.println("PanelODMRData.setScrollPaneSize                    totalWidthOfColumns:"+totalWidthOfColumns);
        if (totalWidthOfColumns<850) // maximum width 795
        {   width = totalWidthOfColumns + 5;}
        else
        {
            width=850;
        }
        
   /*     if (columnCount<=2)
        {      width=250;     }
        else if ((columnCount>2) && (columnCount<=4))
        {      width=400;     }
        else if ((columnCount>4) && (columnCount<=6))
        {      width=580;     }
        else if (columnCount>6)
        {      width=730;     }*/
        
        //System.out.println("panelODMRData.setScrollPaneSize rowCount "+rowCount);
        
        if (rowCount<=2)
        {      height=80;     }
        else if ((rowCount>2) && (rowCount<=3))
        {      height=100;     }
        else if ((rowCount>3) && (rowCount<=4))
        {      height=120;     }
        else if ((rowCount>4) && (rowCount<=6))
        {      height=160;     }
        else if ((rowCount>6) && (rowCount<=8))
        {      height=190;     }
        else if ((rowCount>8) && (rowCount<=10))
        {      height=210;     }
        else if ((rowCount>10) && (rowCount<=12))
        {      height=240;       }
        else if ((rowCount>12) && (rowCount<=14))
        {      height=250;       }    
        else if ((rowCount>14) && (rowCount<=16))
        {      height=260;       }        
        else if (rowCount>16)
        {
        	int maxWidth=totalWidthOfColumns+25;
        	   height=280; 
                width=maxWidth ;

                if (maxWidth>1000) // maximum width 795
                {   width=1000;  }
     
        }
        if(heightFixed==0)// 0 is not set
        {
            scrollpaneTable.setPreferredSize(new Dimension(width, height));    
        }
        else
        {
            scrollpaneTable.setPreferredSize(new Dimension(width, heightFixed));    
        }
        //System.out.println(" PanelODMRData.setScrollPaneSize  width:"+width+"     height:"+height+"     heightFixed:"+heightFixed);
    }
   
   /**
    * 
    *  called by PanelTDMR.rowDelete.
    */
  /* public void rowsManyAllDeleteFromPanelTDMR()
   {
   	//System.out.println("PanelODMRData.rowsManyAllDeleteFromPanelTDMR");
   	   try
           {   
       	      String queryDelete = "DELETE FROM "+entityMany;
       	      String subqueryWhere= " WHERE ";
       	      int count=0;
       	      
       	      //System.out.println("PanelODMRData.rowsManyAllDeleteFromPanelTDMR primKeysCount:"+primKeysCount+" primKeysMany.length:"+primKeysMany.length);
              //System.out.println("PanelODMRData.rowsManyAllDeleteFromPanelTDMR '"+entityMany+"' primKeysCount:"+primKeysCount+" primKeys.length:"+primKeys.length+" sql2WhereField.length:"+sql2WhereField.length);
       	      for (int pk=0;pk<primKeysCount;pk++)
       	      {
                      //String primKeyName = primKeys[pk];
                      //System.out.println("PanelODMRData.rowsManyAllDeleteFromPanelTDMR ("+pk+")  "+entityMany+"."+primKeys[pk]+"="+primKeysValue[pk]+"  "+databaseTableMeta);
       	      	      //System.out.println("PanelODMRData.rowsManyAllDeleteFromPanelTDMR ("+pk+") "+entity+" "+primKeys[pk]+" "+primKeysValue[pk]);
       	      	  
       	      	      String whereValueName = utilsPanelReport.getWhereValueNameThatMatchesColumn(primKeys[pk],sql2WhereField, sql2WhereValue);
          	     System.out.println("PanelODMRData.rowsManyAllDeleteFromPanelTDMR  ("+pk+")  "+primKeys[pk]+"="+whereValueName+"   "+primKeys[pk]+"="+primKeysValue[pk]+" primKey:"+primKeyDb);
          	      if(!whereValueName.equalsIgnoreCase("-"))
          	      {
                          //System.out.println("PanelODMRData.rowsManyAllDeleteFromPanelTDMR "+whereValueName+" "+primKeyName+" "+primKeysValue[pk]);
                          
                 	 if (count>=1)//after the first time that runs adds an and
             	          {  	 subqueryWhere = subqueryWhere + " AND ";    }

                          subqueryWhere = subqueryWhere+ primKeys[pk]+" = '"+primKeysValue[pk]+"'"; //getValueForVariable(whereValueName)+"'";
                          count++;
                      }
       	       }
               queryDelete=queryDelete+subqueryWhere;
               if (VariablesGlobal.globalShowSQLEdit)
               {    System.out.println("PanelODMRData.rowsManyAllDeleteFromPanelTDMR queryDelete:"+queryDelete);   }
               
//               db.updateQuery(queryDelete,"PanelODMRData.rowsManyAllDeleteFromPanelTDMR",true);

                //tableModelReadOnly.fireTableRowsDeleted(selectedTableRow, selectedTableRow);
               //System.out.println("panelODMRData.rowDelete--------!"+primKeyValue);

              // System.out.println("panelODMRData.rowDelete "+queryReadOnlyIn);
              //retrieveDataFromWritableTable(queryReadOnlyIn,entity,sql2WhereField,sql2WhereValue,primKeyValueSetted,isEditable,false,true);
              //table.setRowSelectionInterval(0,0); // select 1st row             
               
               db.releaseConnectionRs();
           }
           catch(Exception e)
           {  
               System.out.println("Error PanelODMRData.rowsManyAllDeleteFromPanelTDMR() "+ e);      
               if(VariablesGlobal.globalShowPrintStackTrace)
               {
                   e.printStackTrace();
               }           
           closeDB();
           } 
   	closeDB();
   } */
   
    /**
     * 
     * Called by PanelTDOR.recordDelete.
     */     
   /*public boolean rowsManyAllDeleteFromPanelTDOR(String primKeyDbIn)
   {
       primKeyDb=primKeyDbIn;
       //System.out.println("PanelOneDataManyRecData.rowsManyAllDeleteFromPanelTDOR  primKeyValue:"+primKeyValue +"  globalYear:"+globalYear);      

                
       boolean ret = false;
   	
       //System.out.println("PanelODMRData.rowsManyAllDeleteFromPanelTDOR  primKeyValue:"+primKeyValue);
            try
           {
       	      String queryDelete = "DELETE FROM "+entityMany;
       	      String subqueryWhere= " WHERE ";
       	      int count=0;
              
              //System.out.println("PanelODMRData.rowsManyAllDeleteFromPanelTDOR '"+entityMany+"' primKeysCount:"+primKeysCount+" primKeys.length:"+primKeys.length+" sql2WhereField.length:"+sql2WhereField.length);
       	      for (int pk=0;pk<primKeysCount;pk++)
       	      {
       	      	
                      //String primKeyName = primKeys[pk];
                      //System.out.println("PanelODMRData.rowsManyAllDeleteFromPanelTDMR ("+pk+")  "+entityMany+"."+primKeys[pk]+"="+primKeysValue[pk]+"  "+databaseTableMeta);
       	      	      //System.out.println("PanelODMRData.rowsManyAllDeleteFromPanelTDMR ("+pk+") "+entity+" "+primKeys[pk]+" "+primKeysValue[pk]);
       	      	  
       	      	      String whereValueName = utilsPanelReport.getWhereValueNameThatMatchesColumn(primKeys[pk],sql2WhereField, sql2WhereValue);
          	     //System.out.println("PanelODMRData.rowsManyAllDeleteFromPanelTDOR  ("+pk+")  "+primKeys[pk]+"="+whereValueName+"   "+primKeys[pk]+"="+primKeysValue[pk]+" primKey:"+primKeyDb+"="+primKeyValue);
          	      if(!whereValueName.equalsIgnoreCase("-"))
          	      {
                          //System.out.println("PanelODMRData.rowsManyAllDeleteFromPanelTDMR "+whereValueName+" "+primKeyName+" "+primKeysValue[pk]);
                         

                          
                 	 if (count>=1)//after the first time that runs adds an and
             	          {  	 subqueryWhere = subqueryWhere + " AND ";    }

                          if(primKeys[pk].equalsIgnoreCase(primKeyDb))// like farmerid
                          {
                              subqueryWhere = subqueryWhere+ primKeys[pk]+" = '"+primKeyValue+"'";
                          }
                          else
                          {
                              subqueryWhere = subqueryWhere+ primKeys[pk]+" = '"+primKeysValue[pk]+"'"; //getValueForVariable(whereValueName)+"'";
                          }
                          count++;
                      }
             
 //System.out.println("PanelODMRData.rowsManyAllDeleteFromPanelTDOR  ("+pk+")  "+entityMany+"."+primKeys[pk]+"="+primKeysValue[pk]+"  "+whereValueName+"   primKey:"+primKeyDb+"   primKeyValue:"+primKeyValue);                           
       	       
              }
               queryDelete=queryDelete+subqueryWhere;
               if (VariablesGlobal.globalShowSQLEdit)
               {    System.out.println("PanelODMRData.rowsManyAllDeleteFromPanelTDOR queryDelete:"+queryDelete);   }
               
               tableModelReadOnly.fireTableRowsDeleted(selectedTableRow, selectedTableRow);
               //System.out.println("panelODMRData.rowDelete--------!"+primKeyValue);
               
              int retQ =  db.updateQuery(queryDelete,"PanelODMRData.rowsManyAllDeleteFromPanelTDOR",true); 
               if(retQ>0)
               {
              // System.out.println("panelODMRData.rowDelete "+query);
               retrieveDataFromWritableTable(query,entity,primKeyDbIn,primKeyValueSetted,isEditable,false,true);
               
               if(table.getRowCount()>1)
               {
                   table.setRowSelectionInterval(0,0); // select 1st row             
               }
               
              ret=true;
               }
           }
           catch(Exception e)
           { 
               System.out.println("Error PanelODMRData.rowsManyAllDeleteFromPanelTDOR() "+ e);
               if(VariablesGlobal.globalShowPrintStackTrace)
               {
                   e.printStackTrace();
               }
               closeDB();
           }
        closeDB();    
        
        return ret;
   }*/

   private void copyAboveCell()
   {
       int row = this.getSelectedTableRow();
       int col = this.getSelectedColumn();
       
       if(this.getRowCount()>1)
       {
       String value = getTableValueAt(row-1,col);
       //System.out.println("PanelODMRData.copyAboveCell to  row:"+row+"  col:"+col+"   value:"+value );
      // this.setTableValueAt(value, row, col);
       TableCellEditor tce = table.getCellEditor(row, col);
       tce.cancelCellEditing();
        this.setTableValueAt(value, row, col);
       }
   }
 
   private void displayMultipleInsertDialog()
   {
     
              int intFieldThatIsFormVar = -1;
              intFieldThatIsFormVar = utilsPanelReport.calculateAllFieldsFromParentDBFieldsForFormVariable1(dbFieldsParent); // not dbFieldsParent because it is null when called by tablecelleditor
       
      if(VariablesGlobal.globalformGlobalVariable1.equalsIgnoreCase("") && intFieldThatIsFormVar!=-1)
      {
          utilsGui.showMessageInfo("Συμπληρώστε πρώτα το πεδίο '"+dbFieldsParent[intFieldThatIsFormVar].getCaption()+"'.");
      }
      else
      {       
       
          String strMultiInsertCaption =  "";
          String luname = "";
          int intOfColumnOfChildField = -1;
           for(int f = 0; f<dbFieldsParent.length;f++)
           {
              
               if(dbFieldsParent[f].getColClassName().equalsIgnoreCase("table") && dbFieldsParent[f].getMultipleInsertField()!=null)
               {
                  strMultiInsertCaption = dbFieldsParent[f].getMultipleInsertCaption();
                  String fieldToInsert = dbFieldsParent[f].getMultipleInsertField();
                  EntityDBFields[] childFields =  dbFieldsParent[f].getDbChildFields();
             if(childFields!=null)
             {
                  System.out.println("PanelODMRData.displayMultipleInsertDialog   f:"+f+"       fieldToInsert:"+fieldToInsert+"  childFields.length:"+childFields.length);

               for(int c = 0;c<childFields.length;c++)
               {
                  
                 if(childFields[c].getDbField().equalsIgnoreCase(fieldToInsert))
                 {
                 
                  luname = childFields[c].getLookupEntityName();
                  intOfColumnOfChildField = c;
                  System.out.println("PanelODMRData.displayMultipleInsertDialog   f:"+f+" c:"+c+" "+childFields[c].getDbField()+"   luname:"+luname);
                
                 
                 }
               }
             }
                  // break;
               }

           }
         
 
           String queryAll = getQueryLookUp(luname,dbFieldsParent);
          //System.out.println("PanelODMRData.displayMultipleInsertDialog       queryAll:"+queryAll);
          
          WindowLookUpMultipleCheck  winLookUpCheck = new WindowLookUpMultipleCheck(frame);
           winLookUpCheck.setEntity(null,table,intOfColumnOfChildField, queryAll,getPrimKeyValue(), lookUp.getEntityFilterSettings(luname), strMultiInsertCaption/*fieldTxts*/, WINDOW_LOCATION_CENTER, 0, lookUp.getIntValidationColumn(luname), lookUp.getIntValidationType(luname), panelManagement);
           
      }
   }
   
   private int getSelectedColumn()
   {
       int ret  = -1;
       for(int c = 0;c < tableModelResultSet.getColumnCount();c++)
       {
         String columnName = tableModelResultSet.getColumnName(c);
            //System.out.println("PanelODMRData.getSelectedColumn   columnName:"+columnName+"  "+c );
         if(table.isColumnSelected(c))
         {
             //String columnName = tableModelResultSet.getColumnName(c);
             ret = c;
             //System.out.println("PanelODMRData.getSelectedColumn   columnName:"+columnName+"   ColumnSelected   c:"+c );
         }
       }
       return ret;
   }
   
    private boolean rowSelectedDeleteIfEmpty(int rowSelectedDeleteIfEmpty)
    {
        boolean retIsDeleted =false;
        
                        boolean isRowEmpty = tableModelResultSet.isRowEmpty(rowSelectedDeleteIfEmpty);
                       	if(isRowEmpty)
                        {
                        
                            rowSelectedDelete(rowSelectedDeleteIfEmpty);
                            retIsDeleted=true;
                            
                        }  
                        else
                        {
                            retIsDeleted =false;
                        }
         return  retIsDeleted;
    }
    
    
    public void rowClearAll(boolean showConfirmation)
    {
       if (isEditable && tableModelResultSet.getRowCount()>0)// && !isNewRec)
       {        
        
         /* final int YES = 0;
    	 final int NO = 1;*/        
        //int rcount = tableModelResultSet.getRowCount();
        int ret = 0;
        if(showConfirmation)
        {
           ret=     utilsGui.showConfirmYesOrNo((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this), "Θέλετε να σβήσετε ολες τις εγγραφές;");
        }
        if(ret == 0)
        {
            int rCount = tableModelResultSet.getRowCount();
            for(int rc = 0; rc< rCount;rc++)
            {
              rowCancelCellEditing(rc);
            }             
            this.revalidate();
            

                  tableModelResultSet.deleteAllTableRows();// the jtable row

                  this.showExtendedSummaryForWritable();
                  panelODORData.calculateSumFields();        
                  setDataHasChanged(true);   
                if(tableModelResultSet.getRowCount()==1)  
                {
                    rowSelectedDeleteIfEmpty(0);
                }
                 
                  
                  
        }
       }
       else
       {
 //          System.out.println("panelODMRData.rowClearAll  NOT SUPPORTED   hasEmptyRow:"+tableModelResultSet.hasEmptyRow()+"    isEditable:"+isEditable+"    isNewRec:"+isNewRec);
       }        

       

       
       
    }
    
    private void rowCancelCellEditing(int row)
    {
        int colCount = tableModelResultSet.getColumnCount();
        for(int c=0;c<colCount;c++)
        {
            TableCellEditor tce = table.getCellEditor(row, c);
            tce.cancelCellEditing();
        }
    }
    
    
   // called by this
   private void rowSelectedDelete(int rowSelected)
   {
    	final int YES = 0;
    	final int NO = 1;
        //int rowSelected = table.getSelectedRow();
   if(rowSelected!=-1)     
   {
     System.out.println("PanelODMRData.rowSelectedDelete isQuery2:"+isQuery2+" sel:"+(rowSelected+1)+" count:"+table.getRowCount());
     if(isQuery2 && !tableModelResultSet.isRowEmpty(rowSelected))
     {  
         int rowplusone =rowSelected+1;
    	if (utilsGui.showConfirmYesOrNo((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this), "Are you sure you would like to delete this row ("+rowplusone+")?") == YES)
    	{         
    	          //int row = table.getSelectedRow();
                  rowCancelCellEditing(rowSelected);
                  tableModelResultSet.deleteTableRow(rowSelected);// the jtable row
                  this.showExtendedSummaryForWritable();
                  panelODORData.calculateSumFields();
              //    panelODORData.calculateAgainDbFields();
                  //tableModelResultSet.rowsDbToUpdateAddToList(row, row); // (int initialTableRow, int currentTableRow)
                 // tableModelResultSet.rowDbToDelete(row);
    	}
      }
      else if (isQuery2 && tableModelResultSet.isRowEmpty(rowSelected))
      {
          rowCancelCellEditing(rowSelected);
            tableModelResultSet.deleteEmptyRowIfThereIsOne();
            //System.out.println(" --- error PanelODMRData.rowSelectedDelete else if     tableModelResultSet.deleteEmptyRowIfThereIsOne()");
      	  this.revalidate();
      }
      else
      {
      	System.out.println("error PanelODMRData.rowSelectedDelete else NOT defined");
      }
    	//System.out.println("PanelODMRData.rowSelectedDelete isQuery2:"+isQuery2+" tableModelResultSet.isRowEmpty(table.getSelectedRow()"+tableModelResultSet.isRowEmpty(table.getSelectedRow()));
   }
   else
   {
       utilsGui.showMessageError(this,"Please select a row.");
   }
    }
      
 
   /*
    * 
    * called by PanelOneDataOneRecData.rowDeleteTables 
    */
   public boolean rowDbDeleteAll(Database dbTransaction)throws SQLException
   {
       boolean ret = true;
            ArrayList listRetDeleted = new ArrayList();
            int countRowsToBeDeleted = getRowCount();
            for(int r= 0 ; r<countRowsToBeDeleted;r++)
            {
                 System.out.println("PanelOneDataManyRecData.rowDbDeleteAll DELETTE ALL ROWS for row:"+r);
                  if(rowDbDelete(r,false,dbTransaction)) // r for each row, also r in 
                  {
                        listRetDeleted.add(true);
                        ret=true;    
                  }
                  else
                  {
                        listRetDeleted.add(false);
                        ret=false;
                        break;
                  }     
            }       
       return ret;
   }
   

   // used by PanelOneDataManyRec.rowDelete
   /* and PanelODORData.rowDeleteAll
    * and  this.rowDbDeleteAll
   *  and OanelODMR.rowDeleteChildTables
    */ 
   public boolean rowDbDelete(int row, boolean showMessage,Database dbTransaction)throws SQLException
   {
       boolean ret = false;
       int retShowMessage = YES;
      if(showMessage)
      {
          retShowMessage=utilsGui.showConfirmYesOrNo(frame,"Are you sure you would like to delete this record ?");// message when deleting from PanelOneDatManyRec
      }
      else
      {
          retShowMessage=YES;
      }
      
          
   	  if(retShowMessage==YES)
   	  {
  

       	      String queryDelete = "DELETE FROM "+entityMany;
       	      String subqueryWhere= " WHERE ";
              
    /* ArrayList listDbFieldsPKs = new ArrayList();          
              
         int intPrimkeyFixed=0;

         for(int c=0;c<dbFields.length;c++)
  	 {
                if(dbFields[c].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY)
                {
                    intPrimkeyFixed++;
                }         
         }   
                    
         intPrimkeyFixed=0;           
  	 for(int c=0;c<dbFields.length;c++)
  	 {

                if(dbFields[c].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY_AUTOINC)
                {
                    //primkeyAutoInc = dbFields[c].getDbField();
                    listDbFieldsPKs.add(dbFields[c]);
                    
                }
                
                if(dbFields[c].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY)
                {
                    //primkeyFixedArray[intPrimkeyFixed]=dbFields[c].getDbField();
                    //primkeyFixedValueArray[intPrimkeyFixed]=dbFields[c].getDefaultValue();
                    //System.out.println("+ UtilsPanelReport.retrievePrimKeyValueForOnePK ("+entity+") FIELD_PRIMARY_KEY primkeyFixedArray:"+primkeyFixedArray[intPrimkeyFixed]+"="+primkeyFixedValueArray[intPrimkeyFixed]+"  "+intPrimkeyFixed);
                    listDbFieldsPKs.add(dbFields[c]);
                    
                    intPrimkeyFixed++;
                }
                
  	 }        

         String [] primKeysDb = new String[listDbFieldsPKs.size()];
         String[] primKeysCaption = new String[listDbFieldsPKs.size()];
         String[] primKeysDbValue = new String[listDbFieldsPKs.size()];
         
        if(listDbFieldsPKs!=null)
        {
           for (int pk=0;pk<listDbFieldsPKs.size();pk++)
           {
             
               
               
               EntityDBFields edbf = (EntityDBFields)listDbFieldsPKs.get(pk);
               
               primKeysDb[pk]=edbf.getDbField();
               primKeysCaption[pk] = edbf.getCaption();
               if(edbf.getDefaultValue() == null || edbf.getDefaultValue().toString().equalsIgnoreCase(""))
               {
                   primKeysDbValue[pk] = primKeysValue[pk];
               }
               else
               {
                   primKeysDbValue[pk] = edbf.getDefaultValue();    
               }

       	     //System.out.println("------> PanelODMRData.rowDbDelete ("+pk+") "+primKeysDb[pk]+" "+primKeysDbValue[pk]);
             
       	      	  if(primKeysCount==1 || pk==primKeysCount-1)
       	      	  {  subqueryWhere=subqueryWhere+primKeysDb[pk]+" LIKE "+primKeysDbValue[pk] ;  }
       	      	  else
       	      	  {  subqueryWhere=subqueryWhere+primKeysDb[pk]+" LIKE "+primKeysDbValue[pk]+" AND ";  }

           }
        }*/
              
          //    System.out.println("   -Q-Q-Q-   PanelODMRData.rowDbDelete        entity:"+entity+"       entityMany:"+entityMany+"   row:["+row+"]    isQuery2"+isQuery2+"      query:"+query +"   queryMany:"+queryMany);
              
              if(isQuery2)
              {                             //queryMany
                    utilsPanelReport.retrievePrimKeyValueForOnePK( queryMany, row+1, dbFields,dbFieldsMany,isQuery2,/*primKeyIn,intColumnOfDescriptionIn,
                       sql2WhereField, sql2WhereValue,*/ entity, /*tableModelReadOnly,*/ primKeyDb);                  
              }
              else
              {                               //query
                   utilsPanelReport.retrievePrimKeyValueForOnePK( query, row+1, dbFields,dbFieldsMany,isQuery2,/*primKeyIn,intColumnOfDescriptionIn,
                       sql2WhereField, sql2WhereValue,*/ entity, /*tableModelReadOnly,*/ primKeyDb);                  
              }
              

                       
                        
                        primKeys = utilsPanelReport.getPrimKeys();
                        primKeysCount = primKeys.length;
                        primKeysValue = utilsPanelReport.getPrimKeysValue();
                        
       	      	 for(int pk =0;pk<primKeysCount;pk++)
                 {
                  if(primKeysCount==1 || pk==primKeysCount-1)
       	      	  {  subqueryWhere=subqueryWhere+primKeys[pk]+" LIKE "+primKeysValue[pk] ;  }
       	      	  else
       	      	  {  subqueryWhere=subqueryWhere+primKeys[pk]+" LIKE "+primKeysValue[pk]+" AND ";  }
                 }      
              

               queryDelete=queryDelete+subqueryWhere;
               if (VariablesGlobal.globalShowSQLEdit)
               {   
                  System.out.println("PanelODMRData.rowDbDelete     queryDelete:"+queryDelete);
               
               }
               
               tableModelReadOnly.fireTableRowsDeleted(selectedTableRow, selectedTableRow);
               //System.out.println("panelODMRData.rowDelete--------!"+primKeyValue);
               
           
               
               
               
               
               //System.out.println("PanelODMRData.rowDbDelete     ("+row+")     queryDelete:"+queryDelete+"    dbTransaction:"+dbTransaction); 
               int intRet = dbTransaction.transactionUpdateQuery(queryDelete,"PanelODMRData.rowDbDelete",true);
               if(intRet==1)
               {
                   ret=true;    
               }
               else
               {
                  ret = false;
               }
               
               //System.out.println("  DELETE   panelODMRData.rowDbDelete "+queryDelete);
               
               if(isEditable)
               {    
//                   retrieveDataFromWritableTable(query,entity,/*sql2WhereField,sql2WhereValue,*/primKeyDb,primKeyValueSetted,isEditable,false,isQuery2);
//                  table.setRowSelectionInterval(0,0); // select 1st row             
              }
               else
               {
                   filterForReadOnlyTable(query);
                   if(table.getRowCount()==0)
                   {
                       
                   }
                   else if(table.getRowCount()-1>row)
                   {
                       table.setRowSelectionInterval(row,row); // select 1st row        
                   }
                   else
                   {
                       table.setRowSelectionInterval(table.getRowCount()-1,table.getRowCount()-1); // select 1st row        
                   }
                   
               }
//               closeDB();
           

   	  }// if(retShowMessage==YES)
      
      return ret;

   }
   
   
   private void selection()
   {
            listSelectionModel=null;
   	        listSelectionModel = table.getSelectionModel();
            // listSelectionModel.addListSelectionListener(new ListSelectionHandler());
            
            
            //add key listener if down pressed add new line
            table.addKeyListener(new KeyListener()
            {
            	public void keyTyped(KeyEvent e) 
            	{
            		//System.out.println("panelODMRData.selection keyTyped. "+e+" selectedTableRow "+selectedTableRow);	
            	}


                 public void keyPressed(KeyEvent e)
                 {
                  if(isEditable)
                  {                 	
                    if(isQuery2 && selectedTableRow==tableModelResultSet.getRowCount()-1 && e.getKeyCode()==e.VK_DOWN)
                    {
                    	
                      //System.out.println("panelODMRData.selection keyPressed. selectedTableRow "+selectedTableRow);
                      addNewRowIfThereIsnt(true); // already has -> panelODORData.calculateAgainDbFields();
                      showExtendedSummaryForWritable();
                      panelODORData.calculateSumFields();
                      
                      //System.out.println("panelODMRData.selection keyPressed. selectedTableRow="+selectedTableRow+" count="+tableModelResultSet.getRowCount());
                    	
                    }
                    // last row and up key
                    else if (isQuery2 && selectedTableRow==tableModelResultSet.getRowCount()-1 && e.getKeyCode()==e.VK_UP && tableModelResultSet.isRowEmpty(tableModelResultSet.getRowCount()-1))
                    {
                       if(tableModelResultSet.getRowCount()==1)
                       {
                       	
                       }
                       else
                       {
                      
                       int row = table.getSelectedRow();
                       	rowSelectedDeleteIfEmpty(row);
                       	
                    	//System.out.println("panelODMRData.selection KEY Pressed. last row="+row+" count="+tableModelResultSet.getRowCount());
                    	
                    	table.setRowSelectionInterval(row-1,row-1);
                      showExtendedSummaryForWritable();
                      panelODORData.calculateSumFields();   
                      //panelODORData.calculateAgainDbFields();                      
                   
                    	
                    	//System.out.println("panelODMRData.selection "+table.getSelectedRow());                    		
                  	    //table.setRowSelectionInterval(tableModelResultSet.getRowCount()-1,tableModelResultSet.getRowCount()-1);
                       }
                    	
                    }
                    else if (isQuery2 && tableModelResultSet.hasEmptyRow() && e.getKeyCode()==e.VK_UP)
                    {

                       int row = table.getSelectedRow();
                       	rowSelectedDeleteIfEmpty(row);
                                                      
                       	//  tableModelResultSet.deleteEmptyRowIfThereIsOne();
                        //System.out.println("panelODMRData.selection keyPressed. VK_UP last row="+row+" count="+tableModelResultSet.getRowCount());
                        
                       	table.setRowSelectionInterval(row,row);
                      showExtendedSummaryForWritable();
                      panelODORData.calculateSumFields(); 
                    //  panelODORData.calculateAgainDbFields();
                       	//System.out.println("panelODMRData.selection "+row);                       	                       	
                    
                       	
                    }
                    else if (isQuery2 && tableModelResultSet.hasEmptyRow() && e.getKeyCode()==e.VK_DOWN)
                    {
                       int row = table.getSelectedRow();
                       	rowSelectedDeleteIfEmpty(row);
                        
                       // System.out.println("panelODMRData.selection keyPressed. VK_DOWN last row="+row+" count="+tableModelResultSet.getRowCount());
                        
                       	table.setRowSelectionInterval(row-1,row-1);
                      showExtendedSummaryForWritable();
                      panelODORData.calculateSumFields();
                     // panelODORData.calculateAgainDbFields();
                       
                    }
                    else if(e.getKeyCode()==e.VK_DOWN || e.getKeyCode()==e.VK_UP)
                    {
                      showExtendedSummaryForWritable();
                      panelODORData.calculateSumFields();     
                     // panelODORData.calculateAgainDbFields();
                    }
                    else if(e.getKeyCode()==KeyEvent.VK_TAB || e.getKeyCode()==KeyEvent.VK_ENTER)//  when enter (to move focus in next cell) keys are pressed
                    {
                        
  
                        
                        
                  // from   https://gist.github.com/exhuma/976531  
                  //  on key enter or tab -> focus on next editable 
                  // also (as comment) in JtableDec.changeSelection      
/*  int targetViewRow = table.getSelectedRow();//row;
int targetViewCol = table.getSelectedColumn();//col;
 
int targetModelRow = table.convertRowIndexToModel(targetViewRow);
int targetModelCol = table.convertColumnIndexToModel(targetViewCol);
 

 System.out.println("panelODMRData.selection  before if "+table.isEditable +" "+ table.getModel().isCellEditable(targetModelRow, targetModelCol)+"  "+targetModelRow+"  "+targetModelCol);

if (table.isEditable && !table.getModel().isCellEditable(targetModelRow, targetModelCol))
{

 
// Find the row and column we're coming from.
int oldViewRow = table.getEditingRow();
int oldViewCol = table.getEditingColumn();
if (oldViewRow == -1)
{
oldViewRow = table.getSelectedRow();
}
if (oldViewCol == -1)
{
oldViewCol = table.getSelectedColumn();
}
 


System.out.println("panelODMRData.selection  A "+table.isEditable);

DIR direction;
if (oldViewCol == targetViewCol && oldViewRow < targetViewRow)
{
direction = DIR.Down;
} else if (oldViewCol == targetViewCol && oldViewRow >= targetViewRow)
{
direction = DIR.Up;
} else if (oldViewCol == targetViewCol && oldViewRow == targetViewRow)
{
direction = DIR.Left;
} else 
{
// defaulting to right
direction = DIR.Right;
}
 
//LOG.fine(String.format("Moving %s", direction));
 
System.out.println("panelODMRData.selection  B "+table.isEditable);
// determine next cell position
while (table.isEditable && table.getModel().isCellEditable(targetModelRow, targetModelCol))
{
//LOG.fine(String.format("Model-Cell %d,%d is still not editable",targetModelRow, targetModelCol));

    System.out.println("panelODMRData.selection  SWITCH "+ direction +"  targetModelRow:"+targetModelRow+"  targetModelCol:"+targetModelCol+"  "+table.isEditable +"    " +table.getModel().isCellEditable(targetModelRow, targetModelCol)); 
switch (direction)
{
   
case Up:
targetViewRow -= 1;
if (targetViewRow < 0)
{
targetViewRow = getRowCount() - 1;
}
//System.out.println("JtableDec.changeSelection SWITCH UP"+ direction); 
break;
case Down:
targetViewRow += 1;
if (targetViewRow > getRowCount() - 1)
{
targetViewRow = 0;
}
//System.out.println("JtableDec.changeSelection SWITCH DOWN"+ direction); 
break;
case Left:
targetViewCol -= 1;
if (targetViewCol < 0) 
{
targetViewCol = getRowCount() - 1;
targetViewRow -= 1;
if (targetViewRow < 0)
{
targetViewRow = getRowCount() - 1;
}
}
//System.out.println("JtableDec.changeSelection SWITCH LEFT"+ direction); 
break;
case Right:
targetViewCol += 1;
if (targetViewCol > table.getColumnCount() - 1)
{
targetViewCol = 0;
targetViewRow += 1;
if (targetViewRow > getRowCount() - 1) 
{
targetViewRow = 0;
}
}
//System.out.println("JtableDec.changeSelection SWITCH RIGHT"+ direction); 
break;
}
targetModelRow = table.convertRowIndexToModel(targetViewRow);
targetModelCol = table.convertColumnIndexToModel(targetViewCol);
}
//LOG.fine(String.format("Trying to move selection to %d,%d instead!",targetViewRow, targetViewCol));
//System.out.println("JtableDec.changeSelection LAST");
}                        
                        
      //doesn't work here
           System.out.println("panelODMRData.selection    isCellEditable"+table.isCellEditable(targetViewRow,targetViewCol)+"  "+targetViewRow+"   "+targetViewCol);                       
          // make edit text in next focusable text
            if(table.isCellEditable(targetViewRow,targetViewCol)) 
            {

           System.out.println("panelODMRData.selection    editCellAt"+table.editCellAt(targetViewRow, targetViewCol)+"  "+targetViewRow+"   "+targetViewCol);            
         if (table.editCellAt(targetViewRow, targetViewCol))
        {
              // doesn't work here
            Component editor = table.getEditorComponent();
            editor.requestFocusInWindow();
//          ((JTextComponent)editor).selectAll();
        }
             }    
                        
  
        */    
            
                        
                        
                        
                        
                      showExtendedSummaryForWritable();
                      panelODORData.calculateSumFields();
                     
                        //panelODORData.calculateAgainDbFields(); // not used because it  moves focus in next cell and recalculates everything
                    }
                    else
                    {
                      //showExtendedSummaryForWritable();
                      //panelODORData.calculateSumFields();                        
                    }
    
                  }// if is editable
                    
                 }


                 public void keyReleased(KeyEvent e)
                 {
                   //displayInfo(e, "KEY RELEASED: ");
                 }

             });

            
           listSelectionModel.addListSelectionListener(new ListSelectionListener()
           {
                public void valueChanged(ListSelectionEvent e)
                {
                    //Ignore extra messages.
                    if (e.getValueIsAdjusting()) return;
                    listSelectionModel = (ListSelectionModel)e.getSource();
                    if (listSelectionModel.isSelectionEmpty() && listSelectionModel.getMinSelectionIndex()==-1)//tableModelResultSet.getRowCount()==0)
                    {
                       // System.out.println("panelODMRData.selection No rows are selected.  tableModelResultSet.getRowCount():"+tableModelResultSet.getRowCount()+"     listSelectionModel.getMinSelectionIndex()"+listSelectionModel.getMinSelectionIndex());
                        selectedTableRow=-1;
                    }
                    else
                    {   
                        selectedTableRow = listSelectionModel.getMinSelectionIndex();                             
                        //System.out.println("PanelODMRData.selection primKeyDb "+primKeyDb);
                    
                       // System.out.println("panelODMRData.selection     selectedTableRow:"+selectedTableRow +"          isQuery2:"+isQuery2+"          primKey:"+primKey);
                        if(isQuery2)
                        {
                            // queryMany setted in  retrieveDataFromWritableTable
                           retrievePrimKeyValueForWritableTable( queryMany, selectedTableRow);//, primKey,0);    
                        }
                        else
                        {
                            
                            
                            retrievePrimKeyValueForReadOnlyTable( query, selectedTableRow, primKey,0);    
                        }
                        
                        
                        selectedDataRow = selectedTableRow+1; //by default 1s selectedrow is 0. since there is no 0 record, we add 1
                        //System.out.println("panelODMRData.selection selectedTableRow:"+selectedTableRow+" selectedDataRow"+selectedDataRow);        
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
                    
                    
                       //scrollpaneTable.getVerticalScrollBar().setValue((table.getRowHeight()-table.getRowMargin())*selectedTableRow);

              //        rs = selectDataRow(selectedDataRow,queryFinal); // dialog is loaded when user clicks edit button in PanelOneDataManyRec and displayDialogEditOneDataOneRec is fired.
              //        System.out.println("panelODMRData.selection rs "+rs);
                    
                  }
                    int rowCount = tableModelResultSet.getRowCount();
                    if(isEditable && panelODORData!=null && panelODORData.getHasDataChanged() && rowCount>0)
                    {
                         panelODORData.calculateAgainDbFields();
      //                  showExtendedSummaryForWritable();
                        //showExtendedSummary(tableModelResultSet); //////////test      
                    }
                  
                    }
                    
              //closeDB();
            });
          
          table.addMouseListener(new MouseAdapter()
          {
            public void mouseClicked(MouseEvent e)
            {
            	//table = (JTableDec)e.getSource();
                if (e.getClickCount() == 1 && selectedTableRow==-1 && tableModelResultSet.getRowCount()==0 && isEditable) // make it 2 for doubleclick
                {   
                    //retrievePrimKeyValue( query, selectedTableRow, primKey);
                    //System.out.println("panelODMRData.selection table clicked. selectedTableRow (addNewRowIfThereIsnt)"+selectedTableRow);//+" PKvalue "+getPrimKeyValue());
                    addNewRowIfThereIsnt(true);// already has -> panelODORData.calculateAgainDbFields();
                    showExtendedSummaryForWritable();
                    panelODORData.calculateSumFields();
                    
                }
                
                if (isQuery2 && tableModelResultSet.hasEmptyRow() && tableModelResultSet.getRowCount()>1)
                {
                     //int row = rowSelectedDelete();
                     tableModelResultSet.deleteEmptyRowIfThereIsOne();
                     showExtendedSummaryForWritable();
                     panelODORData.calculateSumFields();
                        
               }                
                
                   
                    
            } 
            //closeDB();
           });


     //     System.out.println("PanelODMRData.selection "+table.getAccessibleContext().getAccessibleName() );


          /* AccessibleJTableCell.addFocusListener(new FocusListener ()
          {
          	
            public void focusGained(FocusEvent e)
            {
              System.out.println("PanelODMRData.selection Focus gained ");
            }



             public void focusLost(FocusEvent e)
             {
             	System.out.println("PanelODMRData.selection Focus lost");
             }          	
          	
          
          });*/

              //isEditable
   	
   	
   }


   public ResultSet selectDataRow(int selectedDataRow,String query)   
   {
       try
         {  
              // needed it so we can release connection of rs at the end of setEntity
   	    db.retrieveDBDataFromQuery(query,"PanelOneDataManyRecData.selectDataRow");
   	    rs=db.getRS();


//             System.out.println("PanelODMRData.selectDataRow [selectedDataRow "+selectedDataRow+"]");
             rs = db.retrieveRow(rs, selectedDataRow);
         }
         catch(Exception e)
         {  System.out.println("Error PanelOneDataManyRecData.selectDataRow() " + e);      }
       closeDB();
        return rs;
   }
   

   /*
    * 
    * called by this.getSelectedRowPrimaryKeyValue , PanelTDMRData.rowDeleteODORData   and this.selection
   *  exists in ReportAreaGenerated ,  PanelODMRData.clickedOnRow
    */
   public void retrievePrimKeyValueForReadOnlyTable(String queryIn, int selectedTableRowIn, String primKeyIn,int intColumnOfDescriptionIn)
   {
    
       if(VariablesGlobal.globalShowSelectRecord)
       {
       System.out.println("---sel--- PanelODMRData.retrievePrimKeyValueForReadOnlyTable     selectedTableRowIn:"+selectedTableRowIn+"      PKlength:"+primKeys +"     queryIn:"+queryIn);           
       }
       utilsPanelReport.retrievePrimKeyValueForOnePK( queryIn, selectedTableRowIn+1, dbFields,dbFieldsMany,isQuery2,/*primKeyIn,intColumnOfDescriptionIn,
                       sql2WhereField, sql2WhereValue,*/ entity, primKeyDb);
                       
                     
                        primKeys = utilsPanelReport.getPrimKeys();
                        primKeysCount = primKeys.length;
                        primKeysValue = utilsPanelReport.getPrimKeysValue();
       if(VariablesGlobal.globalShowSelectRecord)
       {                
         System.out.println("---sel-- PanelODMRData.retrievePrimKeyValueForReadOnlyTable    PKlength:"+primKeys.length+"    queryIn:"+queryIn);           
       }
  } 
   

   
   /*
    * 
    * called by panelTwoDataManyRec.rowDelete before 
    */
   //public void setVariablesForRetrievePrimKeyValueForWritableTable(boolean isQuery2In,String[] sql2WhereFieldIn ,String[] sql2WhereValueIn ,String entityManyIn, 
   //        /*EntityDBFields [] dbFieldsManyIn,*/String primKeyDbIn/*,String primKeyValueIn*/)
  // {
       
   /*       String [] fieldsMany =null;
   	  if(dbFieldsMany!=null)
   	  {	
   	       fieldsMany = new String[dbFieldsMany.length];
   	       for(int f=0;f<dbFieldsMany.length;f++)
   	      {
   	     	fieldsMany[f]=dbFieldsMany[f].getDbField();
   	      }   	  		  	
   	  }
       isQuery2=isQuery2In;
       sql2WhereField=sql2WhereFieldIn;
       sql2WhereValue=sql2WhereValueIn;
       entityMany=entityManyIn;
       //dbFieldsManyIn=dbFieldsMany;
       primKeyDb=primKeyDbIn;*/
      // primKeyValue=primKeyValueIn;
 //  }
   
   
   
   
   
  /**
   * 
   * called by   this.selection.
   */
   private void retrievePrimKeyValueForWritableTable(String queryIn, int selectedTableRow)//, String primKeyIn,int intColumnOfDescriptionIn)
   // parameters are needed for panelTwoDataManyRec
   {
      
   	  String [] fieldsMany =null;
   	  if(dbFieldsMany!=null)
   	  {	
   	      fieldsMany = new String[dbFieldsMany.length];
   	      for(int f=0;f<dbFieldsMany.length;f++)
   	      {
   	     	fieldsMany[f]=dbFieldsMany[f].getDbField();
   	      }   	  		  	
   	  }
    /*                   String pkValue = null;
                       if (utilsPanelReport.getPrimKeysValue()!=null)//gets value that is selected in table(farmerid)
                       {
                           
                           pkValue = utilsPanelReport.getPrimKeysValue()[0];
                       }
    */                   
                       //System.out.println(" ---- PanelODMRData.retrievePrimKeyValueForWritableTable "+primKeyDb+" "+primKeyValueSetted);
      //                 utilsPanelReport.retrievePrimKeyValueForManyPKs( queryIn, selectedTableRow, null/*must be null in order to be calculated*/,intColumnOfDescriptionIn, true,//isQuery2,
      //                 sql2WhereField, sql2WhereValue, entityMany, tableModelResultSet, fieldsMany, primKeyDb, pkValue);
                      
                       utilsPanelReport.retrievePrimKeyValueForOnePK( queryIn, selectedTableRow+1, dbFields,dbFieldsMany,isQuery2,/*primKeyDb,intColumnOfDescriptionIn,
                       sql2WhereField, sql2WhereValue,*/ entityMany,/* tableModelResultSet,*/ primKeyDb);
                       
                     /* retrievePrimKeyValueForOnePK(String queryIn, int selectedTableRow, String primKeyIn,int intColumnOfDescription,
                                   String[] sql2WhereField, String[] sql2WhereValue, String entity, TableModel tableModel, String primKeyDb);*/
                       
                       
                       primKeys = utilsPanelReport.getPrimKeys();
                       primKeysCount = primKeys.length;
                       primKeysValue = utilsPanelReport.getPrimKeysValue();
                       
           //System.out.println("PanelODMRData.retrievePrimKeyValueForWritableTable "+primKeys[0]+"="+primKeysValue[0]+" "+primKeys[1]+"="+primKeysValue[1]+" "+primKeys[4]+"="+primKeysValue[4]);
  }    
   
   
   // the one that is getted from panelODMR
    public String getPrimKeyValue()
    {
        //System.out.println("PanelOneDataManyRecData.getPrimKeyValue      getPrimKeyValue():"+utilsPanelReport.getPrimKeyValue());
    	return utilsPanelReport.getPrimKeyValue();
    }
     
    
    
    
   //  is getted from panelODMR
    public String getColDescriptionValue()
    {
     //colDescriptionValue ="ttttt";
      //System.out.println("PanelOneDataManyRecData.getColDescriptionValue "+colDescriptionValue);  
      return colDescriptionValue;
    }

    public String[] getPrimKeys()
    {
        return utilsPanelReport.getPrimKeys();
    }

    public String[] getPrimKeysValue()
    {
        return utilsPanelReport.getPrimKeysValue();
    }    
    
    public String getPrimKey()
    {
        //System.out.println("PanelOneDataManyRecData.getPrimKeyValue primKeyValue"+primKeyValue);
    	return primKey;
    }
    
    public String getQuery()
    {
    	return query;
    }
 
    public EntityDBFields[] getDbFieldsChild()
    {
    	return dbFieldsMany;
    }    
    
    public String getEntity()
    {
    	return entity;
    }

    public boolean getIsEditable()
    {
    	return isEditable;
    }
 
   
   //called by DialogLookUp. Is needed to select the row with the keyvalue
   public void setSelectedTableRow(String keyField, String keyValue)
   {      

       
        //System.out.println("panelODMRData.setSelectedTableRow   "+keyField+":"+keyValue+"  isNewRec:"+isNewRec);
        for(int i=0; i<table.getRowCount();i++ )
        {
        	if((int)getColumnIntFromName(keyField)!=-1)
        	{	
        	// doesn't work when there two or more PKs
        	//System.out.println("panelODMRData.setSelectedTableRow toString "+getColumnIntFromName(keyField));
        	String value = (table.getValueAt(i,getColumnIntFromName(keyField)).toString()); // get value from jtable and convert object to string
        	//System.out.println("panelODMRData.setSelectedTableRow  value: "+value+" keyValue: "+keyValue);
        	   if( value.equalsIgnoreCase(keyValue))
        	   {
        		   isSelectTableRow =true;
                   table.setRowSelectionInterval(i,i); // select the row
                   //System.out.println("panelODMRData.setSelectedTableRow  value: "+value+" keyValue: "+keyValue);
                   isSelectTableRow =false;
                   //System.out.println("panelODMRData.setSelectedTableRow "+i);
       	       }
       	       // if not set anything select 0 row
       	       else if(keyValue==null || keyValue.equalsIgnoreCase(""))
       	       {
       	       	  isSelectTableRow =true;
       	       	  table.setRowSelectionInterval(0,0);
       	       	  isSelectTableRow =false;
       	       }
        	}
        	else
        	{
        		isSelectTableRow =false;
        		//System.out.println("error panelODMRData.setSelectedTableRow keyField="+keyField+" getColumnIntFromName="+getColumnIntFromName(keyField));
        	}
        }
        
        if((int)getColumnIntFromName(keyField)==-1)
        {
             System.out.println("error panelODMRData.setSelectedTableRow NO VALID NAME FOR COLUMN keyField:"+keyField+" getColumnIntFromName:"+getColumnIntFromName(keyField));
        }
        
   }

    
   public int getColumnIntFromName(String colName)
   { 
   	   for(int i=0; i<table.getColumnCount(); i++)
   	   { 
   	       if (colName.equalsIgnoreCase(table.getColumnName(i)))
   	       {
   	       return  i; }
   	       else
   	       {  return -1 ;  }
   	   }
   	   return -1;
   }
   
   // called from DialogLookUp.setButton to get and then set DataRowKeyValue
   public String getSelectedRowPrimaryKeyValue(String queryIn, String primKey, int intColDescription)
   //public String getSelectedDataRowKeyValue(String fieldName) 
   {
   	  String keyValue = "nothing";
   	//  try
   	//  {  
   	    
   	     //System.out.println(" PanelOneDataManyRecData.getSelectedDataRowKeyValue()   primKey:"+primKey+" "+intColDescription+"  selectedTableRow:"+selectedTableRow+" - queryIn:"+queryIn);
   	     retrievePrimKeyValueForReadOnlyTable( queryIn, selectedTableRow, primKey,intColDescription);
   	     keyValue = getPrimKeyValue();
   	//     keyValue = rs.getString(getColumnIntFromName(fieldName)+1);  // +1 because index of jtable starts at 0 and dbtable starts at 1         
         
    //  }
    //  catch ( SQLException sqlex)
    //  {
   //      System.out.println("error:PanelOneDataManyRecData.getSelectedDataRowKeyValue():"+sqlex.getMessage());
   //   }
   
   System.out.println("++++++++++++++ panelODMRData.getSelectedRowPrimaryKeyValue      keyValue:"+keyValue+"       primKey:"+primKey);
   	  
   	  return keyValue;
   }


 /*  // called from DialogLookUp.setButton to get and then set DataRowKeyValue
   public String getSelectedFieldValue(String queryIn, String primKey, String columnName)
   //public String getSelectedDataRowKeyValue(String fieldName) 
   {
   	  String keyValue = "nothing";
   	     
   	     retrievePrimKeyValue( queryIn, selectedTableRow, primKey);
   	     keyValue = getPrimKeyValue();
   
  // System.out.println("panelODMRData.getSelectedRowPrimaryKeyValue "+keyValue);
   	  
   	  return keyValue;
   }*/


   //called from DialogLookUp.setButton
   public int getSelectedTableRow()
   {
       //System.out.println("panelODMRData.getSelectedTableRow "+selectedTableRow);   	    
   	    return selectedTableRow;
   }


   /*public ResultSet getResultSet()
   {
   	   return rs;
   }*/

   /*
    * 
    * called by PanelODORData.rowDeleteTables
    * also called by PanelODORData.rowUpdateTables
    */
public int getRowCount()
{
   //System.out.println("panelODMRData.getRowCount:"+ tableModelResultSet.getRowCount());
    return  tableModelResultSet.getRowCount();
}

public int getRowCountFromReadOnlyTable()
{
   return tableModelReadOnly.getRowCount();
}
   
   
  //called by PanelODORData.rowUpdateTables
  public boolean saveChanges(Database dbTransaction,boolean insertAllRecs,boolean isNewRecIn)throws SQLException
  {
      boolean ret = false;
      showExtendedSummaryForWritable();
      if(panelODORData!= null)// When saveChanges is called by PanelMDMR.saveAfterChecks no panelODORData exists.
      {
          panelODORData.calculateSumFields();
      }
      
        if(tableModelResultSet.saveChanges(dbTransaction,insertAllRecs,isNewRecIn))
        {
            ret=true;
           
        }
        return ret;
      //for writable tableModelResultSet.updateDB();
  }

  public boolean getHasDataChanged()
  {
  	boolean ret=false;
  	if (isEditable)
  	{
  		ret = tableModelResultSet.getHasDataChanged();
  	}
  	//System.out.println("PanelODMRData.getHasDataChanged isEditable: "+isEditable+" ret: "+ret);
  	return ret;
  }

  public void setDataHasChanged(boolean hasChanged)
  {
      
            //System.out.println("------ PanelODMRData.setDataHasChanged     TableModelResultSet hasChanged:"+hasChanged);
              tableModelResultSet.setDataHasChanged(hasChanged);      
             
  }
  
   public int getSelectedDataRow() // used by panelOneDataManyRec
   {
   	   return selectedDataRow;
   } 
   
   
   // exists in PanelODMRData and PanelReportSettings
   public void exportTo(String type)
   {//same as PanelStatistics.exportTo
   	  
   	  int colCount = tableModelReadOnly.getColumnCount();
      String[] colNames = tableModelReadOnly.getTableColumnRealNames();
      Class[] colClass = tableModelReadOnly.getTableColumnsClass();
      Vector data = tableModelReadOnly.getTableDataVector();
     
      ExportToFile exportToFile = new ExportToFile();
 
      exportToFile.exportTo(colCount,table,colNames,colClass,data,type,frame,strOfMany);
  
   }
   

    
    //called by PanelOneDataManyRec
    public void displayPanelReport(String entity,String queryIn, String title,boolean showSummaryPage, EntityFilterSettings[] entityFilterSettings, EntityPanel[] entityPanelDrillIn, 
            String[] palDrillTitleIn,String[] palDrillTitleCaptionIn,EntityGroupOfComps[] entityGroupOfCompsIn/*, String yearEnforce*/)
    {
            
    	/*
    	table.setFocusable(false);
    	PrintTable pt = new PrintTable();
        pt.preview(table,"title");*/
        
       /*PrinterJob printerJob = PrinterJob.getPrinterJob();
       PageFormat pageFormat = printerJob.defaultPage();
       
       if(table.getColumnCount()>5)
       {
             pageFormat.setOrientation(PageFormat.LANDSCAPE);  
       }
    	
    	dlgPrintPreview = new PanelReportSettingsAndPreview((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this)); 
        */
    	//Vector dataVector = new Vector();
    	//dataVector = tableModel.getTableDataVector();
    	// if printpreview directly                       // table
    	//dlgPrintPreview.setEntity(false,entity,"ODMR","","",null,null,query,null,null,null,title,showSummaryPage,null,pageFormat,null);
    	//dlgPrintPreview.showDialog();
    	
    	UtilsString uString = new UtilsString();
    	
        //PanelManagement panelManagement = new PanelManagement(frame);
        
        
     //   PanelPrintPreview panelPrintPreview = new PanelPrintPreview();
       // panelPrintPreview
        
        
       
        // (String caption,ImageIcon ico, JPanel panel, int typeOfPanel)
        
    	PanelReportSettingsPreview panelReportSettingsPreview = new PanelReportSettingsPreview(frame);
    	
       //EntityQuery[] entityQuery = new EntityQuery[1];
       //entityQuery[0]= new EntityQuery(uString.getQueryWithoutOrderby(queryIn),false,0,null,null,null,null,null);
       try
       {
       
        String queryNoCaptionsNoOrderby = uString.removeCaptionsFromQuerySubStringSelect(uString.getQueryWithoutOrderby(queryIn));
        uString.getQueryWithoutOrderby(queryIn);
          db.retrieveDBDataFromQuery(queryNoCaptionsNoOrderby,"PanelODMRData.displayPanelReport");
   	  //rs=db.getRS();
   	  rsmd=db.getRSMetaData();
          
           //EntityDBFields[] entityDBFields = new EntityDBFields[dbFieldsParent.length];//
           EntityReportBandField[] entityReportBandFields = new EntityReportBandField[rsmd.getColumnCount()];//rsmd.getColumnCount()];
          
             for(int c= 0 ; c<rsmd.getColumnCount();c++)
             {
                 String colName = rsmd.getColumnName(c+1);
                 String colCaption = rsmd.getColumnLabel(c+1);
                 String colTable = rsmd.getTableName(c+1);
                 String colClass = rsmd.getColumnClassName(c+1);
                 int colLength = rsmd.getColumnDisplaySize(c+1);
                 
                 entityReportBandFields[c] = new EntityReportBandField(colTable,colName,colCaption,colClass,colLength,true,null,null);
             }
                

       boolean[] boolSettingsReport = {true,false,true,true,false};
      
       int[] intReportSettings= {0,0,0,0};        
    
       

       
       EntityReportBand[] entityReportBand = new EntityReportBand[1];
       //entityReportBand[0].getEntityDBFields()
       //entityReportGroup[0] = new EntityReportGroup(strOfMany,uString.getQueryWithoutOrderby(queryIn),"",entity,ENTITYREPORT_QUERY_TYPE_MAIN,-1,null,null);
       entityReportBand[0] = new EntityReportBand(entity,  strOfMany, entity , entityReportBandFields,fieldsOrderby,/*entityPanelIn[0].getDBFields(),*/"", 0,null,entityPanelDrillIn, palDrillTitleIn,
       palDrillTitleCaptionIn/*,formGlobalTableToGet1,formGlobalTableToApply1*/);
       // example    ("farmers","αγρότες","farmer",entityReportBandFieldsFarmer,ENTITYREPORT_QUERY_TYPE_GROUP,boolSettingsCustomer);
       // public EntityReportBand(String nameIn,  String captionIn, String tableNameIn ,EntityReportBandField[] entityReportBandFieldsIn, int typeIn,boolean [] boolSettingsIn)

      EntityReport entityReport = new EntityReport("rpt"+entity,"",entityReportBand,queryNoCaptionsNoOrderby,"","ODMR",title,
              "",entityFilterSettings,entityGroupOfCompsIn,null,null,/*fieldsOrderby,*/"","","",intReportSettings,
              boolSettingsReport,""/*,yearEnforce*/); 
      //EntityReport entityReport = new EntityReport("rptCustomer",REPORT_CAT_ECONOMIC,reportBandCustomer,"SELECT * FROM customer","ORDER BY name","ODMR","πελάτες","έτους χρήσης "+VariablesGlobal.globalYear,invoiceErs,invoiceEntityGroupOfComps,invoicesSelected, null,invoiceCheckFieldOrderby,intReportSettingsInvoice,boolSettingsReportInvoice,globalYearPlusOne);
/*        panelReportSettingsPreview.setEntity(entityReport,entity,entityReportBand,"ODMR",null,null, null,null,title,"",showSummaryPage,entityFilterSettings,
                entityGroupOfCompsIn,null,null, fieldsOrderby, yearEnforce,panelManagement);
*/
        panelReportSettingsPreview.setEntity(entityReport,/*entity,entityReportBand,"ODMR",*/showSummaryPage,panelManagement);        
        
         //panelReportSettingsPreview.showDialog();     

       }
       catch (SQLException sqlex)
       {
           System.out.println("sql error  PanelODMRData.displayPanelReport "+sqlex.getMessage());
           sqlex.printStackTrace();
       }      

        closeDB();
       panelManagement.addShowTabWithPanel(title,ICO_PRINT_PREVIEW16,panelReportSettingsPreview,PANEL_TYPE_REPORT);
       
    }  


    public void displayDialogTablePreferences()
    {// same as PanelStatistics.displayDialogTablePreferences
    	//String[] colTitles = {"ναι/οχι","όνομασία στήλης"};
    	
       // TableColumnModel columnModel = table.getColumnModel(); 

    	DialogDataTableConfig dlgDataConfig;
    	
    	/*Vector dataVector = new Vector();
    	int colCount=table.getColumnCount();
        for (int c = 0; c < colCount; c++) // for each field
        {
        	String colName = table.getColumnName(c);
            Object[] record = new Object[] { new Boolean(true), colName } ;
            dataVector.addElement(record);
        }*/

            dlgDataConfig = new DialogDataTableConfig((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this));
            String text ="Επιλογές πίνακα "+strOfMany+"";
            dlgDataConfig.setEntity(entity,text,query, fieldsOrderby,primKey,entityFilterSettings);
    	    dlgDataConfig.showDialog();
         
    }
   
    
    
 private String getQueryLookUp(String luname,EntityDBFields[] dbFieldsAllIn)
 {
     
         
        String subQueryFilterFromRecType="";
      String queryLookUpWhere = lookUp.getQuerySubqueryWhere(luname);
       String queryLookUpWhereForFormVariable = lookUp.getQueryWhereForFormVariable(luname);
      String queryLookUpIsActive = lookUp.getQuerySubqueryIsActive(luname);
      String queryOrderByLookUp = lookUp.getQueryOrderBy(luname);
      int noIndex = lookUp.getLookUpFieldIndex(luname);

 
          String queryLookUp  = "";
              int intFieldThatIsFormVar = -1;
              intFieldThatIsFormVar = utilsPanelReport.calculateAllFieldsFromParentDBFieldsForFormVariable1(dbFieldsAllIn); // not dbFieldsParent because it is null when called by tablecelleditor
              //System.out.println("--  --  --  --- panelODMRData.getQueryLookUp---   "+luname+"      intFieldThatIsFormVar:"+intFieldThatIsFormVar+"   noIndex:"+noIndex+"   glo var1:"+VariablesGlobal.globalformGlobalVariable1);
              if(intFieldThatIsFormVar!=-1 )
              {
                  
                  
                  if( VariablesGlobal.globalformGlobalVariable1!=null && !VariablesGlobal.globalformGlobalVariable1.equalsIgnoreCase(""))
                  {
                      subQueryFilterFromRecType = VariablesGlobal.globalformGlobalVariable1 +" ) ";  
                  }
                  else
                  {
                      subQueryFilterFromRecType =" 999 ) ";  
                  }
                   if(queryLookUpWhereForFormVariable.equalsIgnoreCase("") && !queryLookUpIsActive.equalsIgnoreCase(""))
                   { // where, when there is no WHERE but there is AND in EntityData
                     queryLookUpIsActive = utilsString.replaceAndWithWhere(queryLookUpIsActive);
                   }
                   else
                   {
          
                   }                   
                  
                   queryLookUp = lookUp.getQuery(luname)+" "+queryLookUpWhereForFormVariable+" "+subQueryFilterFromRecType+" "+queryLookUpIsActive+" "+queryOrderByLookUp;      
               // System.out.println("--++----panelODMRData.displayDialogLookUp   queryLookUp:"+queryLookUp);
              }
              else
              {
                   subQueryFilterFromRecType = "";
                   if(queryLookUpWhere.equalsIgnoreCase("") && !queryLookUpIsActive.equalsIgnoreCase(""))
                   { // where, when there is no WHERE but there is AND in EntityData
                     queryLookUpIsActive = utilsString.replaceAndWithWhere(queryLookUpIsActive);
                   }
                   else
                   {
          
                   }                   
                     // when to show all, not just the formvariables
                   queryLookUp = lookUp.getQuery(luname)+" "+queryLookUpWhere+" "+queryLookUpIsActive+" "+queryOrderByLookUp;      
                          
              }
          
          //String queryLookUpWhereTableOfForm = lookUp.getQuerySubqueryWhere(luname);
         // queryClosingSubquery =  subQueryFilterFromRecType;      //queryLookUpWhereTableOfForm+subQueryFilterFromRecType;
 
          //System.out.println(" panelODMRData.displayDialogLookUp           luname:"+luname+"    intFieldThatIsFormVar:"+intFieldThatIsFormVar+"    subQueryFilterFromRecType:"+subQueryFilterFromRecType+"       VariablesGlobal.globalformGlobalVariable1:"+VariablesGlobal.globalformGlobalVariable1);
         
    return queryLookUp;      
 }
    
    /*
    *   called by TableCellEditorLookup
    */
   
   public String displayDialogLookUp(String selectedKeyValue, String luname,String foreignTable, EntityDBFields[] dbFieldsAllIn)
   {
       String selected="";
              int intFieldThatIsFormVar = -1;
              intFieldThatIsFormVar = utilsPanelReport.calculateAllFieldsFromParentDBFieldsForFormVariable1(dbFieldsParent); // not dbFieldsParent because it is null when called by tablecelleditor
       
      if(VariablesGlobal.globalformGlobalVariable1.equalsIgnoreCase("") && intFieldThatIsFormVar!=-1)
      {
          utilsGui.showMessageInfo("Συμπληρώστε πρώτα το πεδίο '"+dbFieldsParent[intFieldThatIsFormVar].getCaption()+"'.");
      }
      else
      {
      DialogLookUp.initialize((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this));
       
      
      String queryLookUp = getQueryLookUp(luname,dbFieldsAllIn);
      
      
      
      
      //System.out.println("DD:::: BEFORE panelODMRData.displayDialogLookUp   "+foreignTable+"    selectedKeyValue:"+selectedKeyValue+"       subQueryFilterFromRecType:"+subQueryFilterFromRecType+"           colDescriptionValue:"+colDescriptionValue+"       queryLookUpWhere:"+queryLookUpWhere+"       lookUp.getQuery(foreignTable):"+lookUp.getQuery(foreignTable)+"       queryLookUp:"+queryLookUp);
      
      //String  queryLookUp = lookUp.getQuery(foreignTable)+" "+lookUp.getQueryOrderBy(foreignTable);
      
       //System.out.println("DD::: panelODMRData.displayDialogLookUp "+foreignTable+"    selectedKeyValue:"+selectedKeyValue+"     queryLookUp:"+queryLookUp);
       //EntityReport entityReport = lookUp.getEntityReport(foreignTable);
       selected = DialogLookUp.showDialog(this,luname, queryLookUp,lookUp.getLookUpKeyTranslation(luname) ,
               selectedKeyValue,lookUp.getShowToolbar(luname), /*yearEnforce,*/panelManagement,fieldsForSums,fieldTxts);//,entityReport);  
       colDescriptionValue = DialogLookUp.getFieldDescriptionValue();
     // System.out.println("DD:::: AFTER  panelODMRData.displayDialogLookUp   "+foreignTable+"    selectedKeyValue:"+selectedKeyValue+"        selected:"+selected+"         colDescriptionValue:"+colDescriptionValue+"          selected:"+selected);             
       

      //packColumns(); //is not working
      }
       return selected;
   }

   public void setEditable(boolean isEditableIn)
   {
       
        isEditable=isEditableIn;
       if(!isEditable)
       {
           toolBarDataManyEditable.setVisible(isEditableIn);
           
           table.setEnabled(isEditable);
             /*table.setModel(new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                 return false;
            }
            });
            table.revalidate();
            this.revalidate();*/
       }
       else
       {
           
       }
   }
   

     
   public String filterForReadOnlyTable(String queryIn)//, String[] strSearchField, String[] strSearch)
   {
   	  this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
   	   //tableModelReadOnly.filter(strSearchField,strSearch);
   	  
   	   query = retrieveDataFromReadOnlyTable(queryIn,entity);//,sql2WhereField,sql2WhereValue,primKeyValue,isEditable,isNewRec,isQuery2);
   	//System.out.println("  F   PanelODMRData.filterForReadOnlyTable FILTER READ "+query);
           //  public void retrieveDataFromWritableTable(String queryIn,String entityIn,String[] sql2WhereFieldIn,String[] sql2WhereValueIn, String primKeyValueIn,boolean isEditableIn, boolean isNewRecIn, boolean isQuery2In)
   	   
           
           table.revalidate();
   	   this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
           return query;
   }
   
   //called by and PanelODORData.showRow
   public void filterForWritableTable(String queryIn,boolean isNewRecIn,boolean isCopyFromNewRecIn,boolean boolWhenPKisNothing)//String[] strSearchField, String[] strSearch)
   {
       this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      String pkValue = "";
       if(boolWhenPKisNothing)
       {
           pkValue=" '-' ";// when we insert a record(with one and many) in many it must be '-'
       }
       else
       {
           //pkValue = primKeyValue; // when we would like to edit a record(ith one and many)
       }
   	  this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
   	   //tableModelReadOnly.filter(strSearchField,strSearch);
       
       if(VariablesGlobal.globalShowSelectDataFromWritable)
       {
           System.out.println("   F W    PanelODMRData.filterForWritableTable  primKeyValue:"+primKeyValue+"    pkValue:"+pkValue+"   boolWhenPKisNothing:"+boolWhenPKisNothing+"     queryIn:"+queryIn+"        isNewRecIn:"+isNewRecIn);
       }
           retrieveDataFromWritableTable(queryIn,entity,primKeyDb,pkValue,isEditable,isNewRecIn,isCopyFromNewRecIn,isQuery2);
       
       
        if (showExtendedSummaryCalcs)
        {   
            showExtendedSummaryCalcs(entity, queryMany, isNewRec , primKeyValue);
        }
      
        showExtendedSummaryForWritable();
        
       
       //System.out.println("panelOneDataManyRecData.filterForWritableTable row count:"+table.getRowCount());
         if (table.getRowCount()>0)        	
        {
            table.setRowSelectionInterval(0,0);
        }    	   
           
           
           table.revalidate();
   	   this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
   }
   
   //called by  and PanelODORData.showRow
   public void filterToDeleteForWritableTable(String queryIn,boolean isNewRecIn)//String[] strSearchField, String[] strSearch)
   {
      
   	  this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
   	   //tableModelReadOnly.filter(strSearchField,strSearch);
          //System.out.println("   F W    PanelODMRData.filterForWritableTable  primKeyValue:"+primKeyValue+"   queryIn:"+queryIn);
   	   retrieveDataFromWritableTable(queryIn,entity,/*sql2WhereField,sql2WhereValue,*/primKeyDb,primKeyValue,isEditable,isNewRecIn,false,isQuery2);
           
   	   table.revalidate();
   	   this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
   }   
   
   
   private void displayDialogNew(String luname, String selectedKeyValue)// int i, String selectedKeyValue
   {

     if(!selectedKeyValue.equals(""))
     {
       
       
       
       //PanelOneDataManyRecData PanelOneDataManyRecData;
       PanelEditOneDataRec  panelEditOneDataRec = new PanelEditOneDataRec(frame);
       
       
       
                 
        //System.out.println("panelOneDataManyRec primKeyValue "+selectedKeyValue);
        LookUpMgt lookUp = new LookUpMgt();
        
        String[] fieldsOnTitle=lookUp.getFieldsOnTitle(luname);
         String[] fieldsOnTitleCaption=lookUp.getFieldsOnTitleCaption(luname);
        String editTitle=lookUp.getStrOfOne(luname);
        EntityPanel[] luEntityPanel = lookUp.getEntityPanel(luname);
        String primKey = lookUp.getLookUpKey(luname);
  /*      String query = "";
        if(lookUp.getQuerySubqueryWhere(luname) !=null && !lookUp.getQuerySubqueryWhere(luname).trim().equalsIgnoreCase("") )
        {
            query = lookUp.getQuery(luname)+" "+lookUp.getQuerySubqueryWhere(luname)+" AND "+luname+"."+primKey+" LIKE "+selectedKeyValue;
        }
        else
        {
            query = lookUp.getQuery(luname)+" WHERE "+luname+"."+primKey+" LIKE "+selectedKeyValue;
        }
        
  */      
        ImageIcon iconLU=lookUp.getIcon(luname);
   //     panelEditOneDataRec.setEntity(entity, luEntityPanel,fieldsOnTitle,fieldsOnTitleCaption,false,primKey,primKeyValue,primKeyDb,null,null,/*query,*/
   //     editTitle,ico,true,isNewRec,isNewRecFromCopy,true,categoryNodes, false);	
      //  -1 is the selectedTableRow in readonlytable used to get the PKs
      
      String[] selPrimKeys ={primKey};
      String[] selPrimKeysValue ={selectedKeyValue};
       panelEditOneDataRec.setEntity(luname, luEntityPanel,-1,fieldsOnTitle,fieldsOnTitleCaption,false,selPrimKeys,selPrimKeysValue,/*primKey,*/
               /*null,null,*//*,query*/"", editTitle,iconLU/*,true*/,true,false,true,null, false, panelManagement);	
   
    	

        panelEditOneDataRec.setVisible(true);
        
        DialogMulti dlg = new DialogMulti(frame);
        dlg.setEntity(panelEditOneDataRec,PANEL_TYPE_EDITONEDATAONEREC, "επεξεργασία "+editTitle,true);
        dlg.display();       
       
       
    }   
            
       
   }
   
   private void displayDialogTableRowEdit()
   {
       PanelOneDataOneRecData panelOneDataOneRecData= new PanelOneDataOneRecData(frame);
       System.out.println("PanelODMRData.displayDialogTableRowEdit   getName:"+entityPanel.getName()+"    entity:"+entityPanel.getEntity()+"     getPrimKey:"+entityPanel.getPrimKey()+"         query:"+query+"       entityPanel getSqlMany:"+entityPanel.getQuery());
       ArrayList<String> lstTempDataRow = new ArrayList();
       int tableRow = getSelectedTableRow();
       int colCount = tableModelResultSet.getColumnCount();
       for(int c=0;c<colCount;c++)
       {
           lstTempDataRow.add(tableModelResultSet.getValueAt(tableRow, c)+"");
       }
       if(getSelectedTableRow()>=0)
       {
       EntityGroupOfComps[] egoc =new EntityGroupOfComps[1];
       egoc[0] = new EntityGroupOfComps(title,6,0,FONT_SIZE_NOT_SET, GROUP_OF_PANEL_VISIBLE);
       panelOneDataOneRecData.setEntity(entityPanel, title, entity, dbFieldsMany, egoc/*entityGroupOfComps*/, null/*entityGroupOfPanelsIn*/, /*primKeys*/null, /*primKeysValue*/null, 
             /*primKeyDb*/null, query, /*isNewRec*/false, false/*isNewRec*/, isEditable, yearEnforceInLines, ICO_ADD, 0, panelManagement,lstTempDataRow);//getSelectedTableRow

       String editTitle = (tableRow+1)+"";
       
        DialogMulti dlg = new DialogMulti(frame);
        dlg.setEntity(panelOneDataOneRecData,PANEL_TYPE_ANY, "επεξεργασία γραμμής "+editTitle,true);
        dlg.display();
        
        if(!dlg.getIsCancelClicked())
        {
        ArrayList lstResultRow = panelOneDataOneRecData.getTemporaryRow(tableRow);
        for(int c =0;c<lstResultRow.size();c++)
        {
            tableModelResultSet.setValueAt(lstResultRow.get(c), tableRow, c);
        }
        }
       }
   }
   
   
   
   private void displayDialogEdit(String luname, String selectedKeyValue, EntityDBFields[] dbFieldsAllIn)// int i, String selectedKeyValue
   { 
      // Component comp =this.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
       //System.out.println(comp.getClass());
    	

   	   
  // 	   String selectedKeyValue ="";

  // 	     selectedKeyValue = txtCompKey.getText();//value = (String)table.getValueAt(getCellEditedRow(), getCellEditedCol());
  
     String foreignTable = lookUp.getFromTheNameTheForeignTable(luname);
             
             
     if(!selectedKeyValue.equals(""))
     {
       
       
       
       //PanelOneDataManyRecData PanelOneDataManyRecData;
       PanelEditOneDataRec  panelEditOneDataRec = new PanelEditOneDataRec(frame);
       
       
       
                 
        //System.out.println("panelOneDataManyRec primKeyValue "+selectedKeyValue);
        LookUpMgt lookUp = new LookUpMgt();
        
        String[] fieldsOnTitle=lookUp.getFieldsOnTitle(luname);
         String[] fieldsOnTitleCaption=lookUp.getFieldsOnTitleCaption(luname);
        String editTitle=lookUp.getStrOfOne(luname);
        EntityPanel[] luEntityPanel = lookUp.getEntityPanel(luname);
        String primKey = lookUp.getLookUpKey(luname);
        String query = "";
        
        
        String subQueryFilterFromRecType="";
       String queryLookUpWhere = lookUp.getQuerySubqueryWhere(luname);
       String queryLookUpWhereForFormVariable = lookUp.getQueryWhereForFormVariable(luname);
      
        
      
      
      
      String queryLookUpIsActive = lookUp.getQuerySubqueryIsActive(luname);
      String queryOrderByLookUp = lookUp.getQueryOrderBy(luname);
      //queryLookUpWhere = query;

      
     
      
      
     System.out.println("DD:::: BEFORE panelODMRData.displayDialogEdit   "+luname+"    selectedKeyValue:"+selectedKeyValue);      
     
     int intFieldThatIsFormVar = -1;
     intFieldThatIsFormVar = utilsPanelReport.calculateAllFieldsFromParentDBFieldsForFormVariable1(dbFieldsAllIn); // not dbFieldsParent because it is null when called by tablecelleditor
      
      
      
      String queryLookUp ="";
     if(intFieldThatIsFormVar!=-1 )
     {
                  if( VariablesGlobal.globalformGlobalVariable1!=null && !VariablesGlobal.globalformGlobalVariable1.equalsIgnoreCase(""))
                  {
                      subQueryFilterFromRecType = VariablesGlobal.globalformGlobalVariable1 +" ) ";  
                  }
                  else
                  {
                      subQueryFilterFromRecType =" 1 ) ";  
                  }      
      

          query = lookUp.getQuery(luname)+" "+queryLookUpWhereForFormVariable+" "+subQueryFilterFromRecType+" AND "+foreignTable+"."+primKey+" LIKE "+selectedKeyValue;
   //        System.out.println(" panelODMRData.displayDialogEdit    IF IF        foreignTable:"+foreignTable+"     VariablesGlobal.globalformGlobalVariable1"+VariablesGlobal.globalformGlobalVariable1);

      } 
      else
      { 
        // if(VariablesGlobal.globalformGlobalVariable1!=null && !VariablesGlobal.globalformGlobalVariable1.equalsIgnoreCase("") )
       //  {
         // subQueryFilterFromRecType = VariablesGlobal.globalformGlobalVariable1 +" ) ";
          //String queryLookUpWhereTableOfForm = lookUp.getQuerySubqueryWhere(foreignTable);
         // queryClosingSubquery =  subQueryFilterFromRecType;      //queryLookUpWhereTableOfForm+subQueryFilterFromRecType;
          query = lookUp.getQuery(luname)+" "+queryLookUpWhere+" "+/*subQueryFilterFromRecType+*/" AND "+foreignTable+"."+primKey+" LIKE "+selectedKeyValue;
  //        System.out.println(" panelODMRData.displayDialogEdit    ELSE IF        foreignTable:"+foreignTable+"     VariablesGlobal.globalformGlobalVariable1"+VariablesGlobal.globalformGlobalVariable1);
    
        // System.out.println("ELSE     panelODMRData.displayDialogLookUp    ELSE       globalformGlobalVariable1:"+VariablesGlobal.globalformGlobalVariable1+"    foreignTable:"+foreignTable);
      }

      if(query.equalsIgnoreCase("") && !queryLookUpIsActive.equalsIgnoreCase(""))
      { // where, when there is no WHERE but there is AND in EntityData
          queryLookUpIsActive = utilsString.replaceAndWithWhere(queryLookUpIsActive);
      }
      else
      {
          
      }      
      
      queryLookUp = query+" "+queryLookUpIsActive+" "+queryOrderByLookUp;    
      // = query/*+" "+queryLookUpWhere+" "*/+subQueryFilterFromRecType+" "+queryLookUpIsActive+" "+queryOrderByLookUp;      
 
      
     
      
      
    //  System.out.println("DD:::: AFTER panelODMRData.displayDialogEdit   "+foreignTable+"    selectedKeyValue:"+selectedKeyValue+"       subQueryFilterFromRecType:"+subQueryFilterFromRecType+"           colDescriptionValue:"+colDescriptionValue+"          queryLookUp:"+queryLookUp);
              
      /*  if(queryLookUp !=null && !queryLookUp.equalsIgnoreCase("") )
        {
            query = lookUp.getQuery(foreignTable)+" "+lookUp.getQuerySubqueryWhere(foreignTable)+" AND "+foreignTable+"."+primKey+" LIKE "+selectedKeyValue;
        }
        else
        {
            query = lookUp.getQuery(foreignTable)+" WHERE "+foreignTable+"."+primKey+" LIKE "+selectedKeyValue;
        }          
        
      */

        
        
        ImageIcon iconLU=lookUp.getIcon(luname);
   //     panelEditOneDataRec.setEntity(entity, luEntityPanel,fieldsOnTitle,fieldsOnTitleCaption,false,primKey,primKeyValue,primKeyDb,null,null,/*query,*/
   //     editTitle,ico,true,isNewRec,isNewRecFromCopy,true,categoryNodes, false);	
   //  -1 is the selectedTableRow in readonlytable used to get the PKs
         String[] selPrimKeys ={primKey};
      String[] selPrimKeysValue ={selectedKeyValue};
     int selected =  panelEditOneDataRec.setEntity(foreignTable, luEntityPanel,-1,fieldsOnTitle,fieldsOnTitleCaption,false,selPrimKeys,selPrimKeysValue,/*primKey,*/
               /*null,null,*/queryLookUp, editTitle,iconLU/*,true*/,false,false,true,null, false, panelManagement);	
   
    	
     if(selected == 0)// when 0 do not display
     {
         System.out.println("PanelODMRData.displayDialogEdit                                      selected:"+selected);
           utilsGui.showMessageInfo("Η εγγραφή δεν υπάρχει. Πιθανόν είναι ανενεργή.");
     }
     else
     {    	

        panelEditOneDataRec.setVisible(true);
        
        DialogMulti dlg = new DialogMulti(frame);
        dlg.setEntity(panelEditOneDataRec,PANEL_TYPE_EDITONEDATAONEREC, "επεξεργασία "+editTitle,true);
        dlg.display();       
     }
       
    }   
     
      

   }   
   
   
   
   
     // this allows outside classes to add actionlisteners
    public void addTableMouseListener(MouseListener al)
    {
      /*for (JButton button : buttons)
      {
        button.addActionListener(al);
      }*/
      //System.out.println("table.addMouseListener(al)");
      table.addMouseListener(al);
    }

class ToolBarDataManyEditable extends JToolBar implements Constants
{

        public ToolBarDataManyEditable()
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

            
            
        btnManyInsert = new JButton();
        btnManyInsertInLine = new JButton();
        btnManyMultiInsert = new JButton();
        btnManyEdit = new JButton();
        btnManyCopyAboveCell = new JButton();
        btnManyDelete = new JButton();
        btnManyClearAll = new JButton();
        
        //btnManySave = new JButton();
        //btnManyPreferences = new JButton();

        //setFloatable(false);
        //setRollover(true);


        //btnManyInsert.setText("<html>εισαγωγή</html>"); //   <b>ins</b>
        btnManyInsert.setText("εισαγωγή");
        btnManyInsert.setOpaque(false);
        btnManyInsert.setToolTipText("εισαγωγή");
        btnManyInsert.setIcon(ICO_ADD);
        btnManyInsert.setFocusable(false);        
        btnManyInsert.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   
                     addNewRowBelow();
	           	   
	        } 
	    });
        Action actionNewRecMany = new ActionNewRecMany();
 //       btnManyInsert.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0), "newRecMany"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
 //       btnManyInsert.getActionMap().put("newRecMany", actionNewRecMany);

        
       // btnManyInsertInLine.setText("<html>εισαγωγή κάτω</html>");
        btnManyInsertInLine.setText("εισαγωγή στη γραμμή");
        btnManyInsertInLine.setOpaque(false);
        btnManyInsertInLine.setToolTipText("εισαγωγή στην επιλεγμένη γραμμή");
        btnManyInsertInLine.setIcon(ICO_ADDBELOW);
        btnManyInsertInLine.setFocusable(false);        
        btnManyInsertInLine.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   
	           	   addNewRowIfThereIsnt(false);      
                           //addNewRowBelow();      
	        } 
	    });
        //Action actionNewRecManyDown = new ActionNewRecMany();
        //btnManyInsertInLine.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0), "newRecMany"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        //btnManyInsertInLine.getActionMap().put("newRecMany", actionNewRecMany);        
        
 
       // btnManyInsertInLine.setText("<html>εισαγωγή κάτω</html>");
       /* btnManyInsertInLine.setText("εισαγωγή κάτω");
        btnManyInsertInLine.setOpaque(false);
        btnManyInsertInLine.setToolTipText("εισαγωγή στην τελευταία σειρά");
        btnManyInsertInLine.setIcon(ICO_ADDBELOW);
        btnManyInsertInLine.setFocusable(false);        
        btnManyInsertInLine.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   
	           	   addNewRowBelow();      
	        } 
	    });*/
        //Action actionNewRecManyDown = new ActionNewRecMany();
        //btnManyInsertInLine.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0), "newRecMany"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        //btnManyInsertInLine.getActionMap().put("newRecMany", actionNewRecMany);        
  
        //btnManyDelete.setText("<html>διαγραφή επιλογής</html>"); //  <b>del</b>
        btnManyDelete.setText("<html>διαγραφή επιλογής</html>");
        btnManyDelete.setOpaque(false);
        btnManyDelete.setToolTipText("διαγραφή επιλογής");
        btnManyDelete.setIcon(ICO_DELETE16);
        btnManyDelete.setFocusable(false);        
        //btnDelete.setVerticalTextPosition(AbstractButton.BOTTOM);
        //btnDelete.setHorizontalTextPosition(AbstractButton.CENTER);
        btnManyDelete.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	
                    
	           rowSelectedDelete(table.getSelectedRow());        
	        } 
	    });
 //       Action actionDelRecMany = new ActionDelRecMany();
 //       btnManyDelete.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delRecMany"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
 //       btnManyDelete.getActionMap().put("delRecMany", actionDelRecMany);        
        
        //btnManyDelete.setText("<html>διαγραφή επιλογής</html>"); //  <b>del</b>
        btnManyMultiInsert.setText("πολλαπλή εισαγωγή");
        btnManyMultiInsert.setOpaque(false);
        btnManyMultiInsert.setToolTipText("πολλαπλή εισαγωγή");
        btnManyMultiInsert.setIcon(ICO_CHECKS);
        btnManyMultiInsert.setFocusable(false);        
        //btnDelete.setVerticalTextPosition(AbstractButton.BOTTOM);
        //btnDelete.setHorizontalTextPosition(AbstractButton.CENTER);
        btnManyMultiInsert.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	
                    displayMultipleInsertDialog();
	           //rowSelectedDelete(table.getSelectedRow());        
	        } 
	    });
 //       Action actionDelRecMany = new ActionDelRecMany();
 //       btnManyDelete.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delRecMany"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
 //       btnManyDelete.getActionMap().put("delRecMany", actionDelRecMany);
 
   
         btnManyEdit.setText("επεξεργασία");
        btnManyEdit.setOpaque(false);
        btnManyEdit.setToolTipText("επεξεργασία");
        btnManyEdit.setIcon(ICO_SETTINGS);
        btnManyEdit.setFocusable(false);        
        btnManyEdit.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   
                     //addNewRowBelow();
	           	displayDialogTableRowEdit();   
	        } 
	    });
  //      Action actionNewRecMany = new ActionNewRecMany();
 //       btnManyInsert.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0), "newRecMany"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
 //       btnManyInsert.getActionMap().put("newRecMany", actionNewRecMany);

 
        
        //btnManyCopyAboveCell.setText("<html>αντιγραφή επάνω κελιού</html>");
        btnManyCopyAboveCell.setText("αντιγραφή επάνω κελιού");
        btnManyCopyAboveCell.setOpaque(false);
        btnManyCopyAboveCell.setToolTipText("αντιγραφή επάνω κελιού");
        btnManyCopyAboveCell.setIcon(ICO_COPY_VALUE_FROM_ABOVE_CELL);
        btnManyCopyAboveCell.setFocusable(false);        
        //btnDelete.setVerticalTextPosition(AbstractButton.BOTTOM);
        //btnDelete.setHorizontalTextPosition(AbstractButton.CENTER);
        btnManyCopyAboveCell.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	
                    copyAboveCell();
	           //rowSelectedDelete(table.getSelectedRow());        
	        } 
	    });
        //Action actionDelRecMany = new ActionDelRecMany();
        //btnManyCopyAboveCell.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delRecMany"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        //btnManyCopyAboveCell.getActionMap().put("delRecMany", actionDelRecMany);        
        
        
        
       //  btnManyClearAll.setText("<html>καθαρισμός πίνακα</html>");
        btnManyClearAll.setText("καθαρισμός πίνακα");
        btnManyClearAll.setOpaque(false);
        btnManyClearAll.setToolTipText("καθαρισμός πίνακα");
        btnManyClearAll.setIcon(ICO_CLEARTABLE);
        btnManyClearAll.setFocusable(false);        
        //btnManyClearAll.setVerticalTextPosition(AbstractButton.BOTTOM);
        //btnManyClearAll.setHorizontalTextPosition(AbstractButton.CENTER);
        btnManyClearAll.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {
                    // ask first
                    rowClearAll(true);
	           //rowSelectedDelete(table.getSelectedRow());        
	        } 
	    });
        //Action actionDelRecMany = new ActionDelRecMany();
        //btnManyClearAll.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delRecMany"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        //btnManyClearAll.getActionMap().put("delRecMany", actionDelRecMany);        
              
        
        
        
        
     /*    btnManySave.setText("<html>αποθήκευση <b>F11</b></html>");
        //btnSave.setText("αποθήκευση (F11)");
        btnManySave.setOpaque(false);
        btnManySave.setToolTipText("αποθήκευση αλλαγών");
        btnManySave.setIcon(ICO_UPDATE16);
        btnManySave.setFocusable(false);
        //btnSave.setVerticalTextPosition(AbstractButton.BOTTOM);
        //btnSave.setHorizontalTextPosition(AbstractButton.CENTER);
        btnManySave.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   

                   
	        } 
	    });
        Action actionSaveRec = new PanelOneDataManyRecData.ActionSaveRec();
        btnManySave.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F11"), "saveRec"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnManySave.getActionMap().put("saveRec", actionSaveRec);
*/
        
        
        
        
        /*btnManyPreferences.setText("<html>προτιμήσεις</html>");            
        btnManyPreferences.setOpaque(false);
        btnManyPreferences.setToolTipText("προτιμήσεις πίνακα");
        btnManyPreferences.setIcon(ICO_TABLE_PREFS16);
        btnManyPreferences.setFocusable(false);
        //btnSave.setVerticalTextPosition(AbstractButton.BOTTOM);
        //btnSave.setHorizontalTextPosition(AbstractButton.CENTER);
        btnManyPreferences.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   
	           displayDialogTableManyPreferences();	        
	        } 
	    });*/

       // this.setLayout(new FlowLayout());//new BoxLayout(this, BoxLayout.LINE_AXIS));  
        GridLayoutVariable layout = new GridLayoutVariable ();
          this.setLayout(layout);        
//add(lblIcoSeparator1);	   
	//    addSeparator();          
        //addSeparator();
        add(btnManyInsert);
        add(btnManyInsertInLine);
        add(btnManyMultiInsert);
        add(btnManyDelete);
        add(btnManyEdit);
        add(btnManyCopyAboveCell);
         //addSeparator();
        
        add(btnManyClearAll);
        // addSeparator();
     //   add(btnManySave);
    //    addSeparator();
        //add(btnManyPreferences);
        //add(lblIcoSeparator3);
        //addSeparator();
        //addSeparator();
        }
        
    private void displayMessageDialog(String title, String message)
    {
    	JOptionPane.showMessageDialog(null, message, title,JOptionPane.INFORMATION_MESSAGE, ICO_INFO16);
    }  
}


/*   class  ActionSaveRec extends AbstractAction                 
   {       
        public ActionSaveRec()
        {        }
      	
    	public void actionPerformed(ActionEvent e)
      	{          btnManySave.doClick();        }    	
    }*/ 


   class  ActionDelRecMany extends AbstractAction                 
   {       
        public ActionDelRecMany()
        {        }
      	
    	public void actionPerformed(ActionEvent e)
      	{
           btnManyDelete.doClick();
        }    	
    }                

    class  ActionNewRecMany extends AbstractAction                 
   {       
        public ActionNewRecMany()
        {        }
      	
    	public void actionPerformed(ActionEvent e)
      	{
      		btnManyInsert.doClick();
        }    	
    }    
        /*
         * 
         * TableModelListener
         */
        //@Override
        public void tableChanged(TableModelEvent e)
        {
            int intRowCount = table.getRowCount();
           // System.out.println("  intRowCount:"+intRowCount+"  "+e);
           if(intRowCount==0)
           {
                 btnManyDelete.setEnabled(false);
           }
           else
           {
                 btnManyDelete.setEnabled(true);
           }
           
           /*
            *    do not know why it doesnot work
            
             int firstRow = e.getFirstRow();
             int lastRow = e.getLastRow();
             
             System.out.println("  intRowCount:"+intRowCount);

             
            int mColIndex = e.getColumn();
    
            switch (e.getType())
            {
              case TableModelEvent.INSERT:
                // The inserted rows are in the range [firstRow, lastRow]
             System.out.println("TableModelListenerGeneric TableModelEvent.INSERT");                
                //rowInsertedOrDeletedOrChanged=true;
                /*for (int r=firstRow; r<=lastRow; r++)
                {
                    // Row r was inserted
                    
                }*/
    /*            break;
              case TableModelEvent.UPDATE:
                //System.out.println("TableModelListenerGeneric TableModelEvent.UPDATE");
                //rowInsertedOrDeletedOrChanged=true;
                /* if (firstRow == TableModelEvent.HEADER_ROW)
                {
                    if (mColIndex == TableModelEvent.ALL_COLUMNS)
                    {
                        // A column was added
                    } else
                    {
                        // Column mColIndex in header changed
                    }
                }
                else
                {
                    // The rows in the range [firstRow, lastRow] changed
                    for (int r=firstRow; r<=lastRow; r++)
                    {
                        // Row r was changed
    
                        if (mColIndex == TableModelEvent.ALL_COLUMNS)
                        {
                            // All columns in the range of rows have changed
                        } else
                        {
                            // Column mColIndex changed
                        }
                    }
                }*/
     /*           break;
              case TableModelEvent.DELETE:
                //System.out.println("TableModelListenerGeneric TableModelEvent.DELETE");
                // rowInsertedOrDeletedOrChanged=true;
                // The rows in the range [firstRow, lastRow] changed
                /*for (int r=firstRow; r<=lastRow; r++)
                {
                    // Row r was deleted
                   
                }*/
                
         /*       break;
            }
           }//if*/
        } // tableChanged 
        
   class  ActionShowDialogNew extends AbstractAction                 
   {       
         String luname;
         int iF;
         int tb2N;
        public ActionShowDialogNew(String lunameIn, int f)
        {
                  luname = lunameIn;
                  iF=f;
                  //tb2N=tb2No;
                  //primKey=primKeyIn;
                 
           
        }
      	
    	public void actionPerformed(ActionEvent e)
      	{

                  System.out.println("ActionShowDialogNew       (size:"+listTableCellEditorLookup.size()+" , iF:"+iF+")");
           
            
            if (dbFieldsMany[iF].getLookupType()== LOOKUPTYPE_ONLYONE_THISFIELD && dbFieldsMany[iF].getLookupEntityName()!=null)//(foreignTable!= null) && (!ft.toUpperCase().equals(entity.toUpperCase())))//(!rsmd.getTableName(i).equalsIgnoreCase(entity)))
            {
                for(int r = 0 ;r<listTableCellEditorLookup.size();r++)
                {   //TableCellEditorLookupOne tceLookup = new TableCellEditorLookupOne
                    
                    TableCellEditorLookupOne tceLookup = (TableCellEditorLookupOne)listTableCellEditorLookup.get(r);
                    System.out.println("ActionShowDialogNew     r:"+r+"    value:"+tceLookup.getCellSelectedValue()); 
                      displayDialogNew( luname,tceLookup.getCellSelectedValue());
                }
            }
            
          //      displayDialogNew(iForeignTable, iF);
        }    	
    } 
   
   
   class  ActionShowDialogEdit extends AbstractAction                 
   {       
         String luname;
         //String foreignTable;
         int iF;
         int tb2N;
         String selectedCellValue = "";
        public ActionShowDialogEdit(String lunameIn,  int f)
        {
            
                  luname = lunameIn;
                  iF=f;
                  //tb2N=tb2No;
                  //primKey=primKeyIn;
                 
           //System.out.println("ActionShowDialogEdit ("+iForeignTable+" , "+iF+")");
        }
        

      	
    	public void actionPerformed(ActionEvent e)
      	{
                 // System.out.println("ActionShowDialogEdit       (size:"+listTableCellEditorLookup.size()+" , iF:"+iF+")");
           
            
            if (dbFieldsMany[iF].getLookupType()== LOOKUPTYPE_ONLYONE_THISFIELD && dbFieldsMany[iF].getLookupEntityName()!=null)//(foreignTable!= null) && (!ft.toUpperCase().equals(entity.toUpperCase())))//(!rsmd.getTableName(i).equalsIgnoreCase(entity)))
            {
                for(int r = 0 ;r<listTableCellEditorLookup.size();r++)
                {   //TableCellEditorLookupOne tceLookup = new TableCellEditorLookupOne
                    
                    TableCellEditorLookupOne tceLookup = (TableCellEditorLookupOne)listTableCellEditorLookup.get(r);
                    //System.out.println("ActionShowDialogEdit     r:"+r+"    value:"+tceLookup.getCellSelectedValue()); 
                            //dbFields = dbFieldsIn;
                           // dbFieldsMany= dbFieldsManyIn;
                      displayDialogEdit( luname,tceLookup.getCellSelectedValue(),dbFieldsMany);
                }
            }
                
        }    	
    }           
        
        
        
}    