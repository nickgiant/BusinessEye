// created 14-2-2009

package com.tool.guicomps;

import com.tool.model.LookUpMgt;
import com.tool.model.EntityGroupOfComps;
import com.tool.model.EntityFilterSettings;
import com.tool.gui.*;
import com.tool.jdbc.*;
import com.tool.model.*;
import com.tool.utils.*;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.*;
import javax.swing.border.*;
import javax.swing.event.*;

 public class PanelDataFilter extends JxPanel implements Constants
 {
  //private String sqlForPrintPreview;
  //private String query;
     
     private TitledBorder lineBorder;
     
  private EntityFilterSettings[] entityFilterSettings;

  private JxPanel panelReportFilters;

    private ArrayList fieldFilterTxts1 ; // to hold txt
    private ArrayList fieldFilterTxts2 ; // to hold txt
    private ArrayList fieldView;
    private ArrayList fieldOrder;
    private ArrayList fieldOrderA;// for order of ascenting
    private ArrayList fieldOrderD ;// for order of decenting
    
   private ArrayList fieldTxtsLookUpId ; // to hold tb
   private ArrayList fieldTxtsLookUpText ; // to hold tb2

   private JxPanel panelFilterSetting;
   
   private FlowLayout flowLayoutLeft2;
   private FlowLayout flowLayoutLabelRight;
   
   private JLabel lblFilterSetting1;  // for lookup fromto
   private JLabel lblFilterSetting;
   private LookUpMgt lookUp;
   
   private UtilsString utilsString;
   private UtilsPanelReport utilsPanelReport;
   private UtilsDate utilsDate;
   
   //private JxFormattedTextFieldDate.FormatSpec formatSpec;
   private JFormattedTextField textFormatedDate;
    
//   private JxFormattedTextFieldDate.FormatSpec formatSpec2;
   private JFormattedTextField textFormatedDate2;  
   private WindowLookUpMultipleCheck winLookUpCheck;
   private JFrame frame;
   private JxPanel panelMain;
   
   private PanelManagement panelManagement;
  private UtilsGui utilsGui;
  //private String yearEnforce ;
  //private EntityUpdateAdditional[] updateAdditional;
  
    public PanelDataFilter(JFrame frame)
    {
        try {
            initialize(frame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
	private void initialize(JFrame frameIn) throws Exception
    {
    	frame = frameIn;
        utilsDate = new UtilsDate();	
        utilsDate.closeDB();
        utilsDate.readFromFileDateFormats();        
        
        
       lookUp= new LookUpMgt();
       utilsPanelReport = new UtilsPanelReport();
       utilsString = new UtilsString();
       utilsGui = new UtilsGui();
      
       panelMain = new JxPanel();
       panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.PAGE_AXIS));
       
       //formatSpec = new JxFormattedTextFieldDate.FormatSpec();
       //formatSpec2 = new JxFormattedTextFieldDate.FormatSpec();
    
        lineBorder = javax.swing.BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, this.getBackground().darker()));
       
    	
       flowLayoutLeft2 = new FlowLayout();
       flowLayoutLeft2.setVgap(2);
       flowLayoutLeft2.setHgap(2);
       flowLayoutLeft2.setAlignment(FlowLayout.LEFT);   
       
       
       
       flowLayoutLabelRight = new FlowLayout();
       flowLayoutLabelRight.setVgap(5);
       flowLayoutLabelRight.setHgap(2);
       flowLayoutLabelRight.setAlignment(FlowLayout.RIGHT);  	
       this.add(panelMain); // except class PanelODORData.calculationFromToolBarButton others call function getPanelFilters() to get the panel. To do: change other calls of function getPanelFilters().
    }
    
    public void setNewEntityFilterSettings(EntityFilterSettings[] entityFilterSettingsIn)
    {
    	entityFilterSettings=entityFilterSettingsIn;
    }
    
    
    // use getPanelFilters to get panel
    
    public void setEntity(EntityFilterSettings[] entityFilterSettingsIn,EntityGroupOfComps[] entityGroupOfComps, int calledFromPanel, /*String yearEnforceIn,*/
            PanelManagement panelManagementIn )//,EntityUpdateAdditional[] updateAdditionalIn)
    {
        panelManagement=panelManagementIn;
        entityFilterSettings=entityFilterSettingsIn;
    	panelReportFilters = new JxPanel();
    	panelReportFilters.setOpaque(false);
        panelMain.removeAll();
    	panelMain.setOpaque(false);
       // yearEnforce=yearEnforceIn;
//        entityReport = entityReportIn;
      // updateAdditional=updateAdditionalIn;
        
    ArrayList listPanelGroups= new ArrayList();
    	
     int intSizeOfTextBox=0;
     int intSizeOfTextBoxKey=0;
     if(calledFromPanel==PANEL_FILTER_SEARCH)
     {
       //panelReportFilters.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 6));
        if(entityGroupOfComps!=null)
        {
            JxTabbedPane  tabbedPane = new JxTabbedPane();
       
        	JxPanel panelHolder = new JxPanel();
        	GridLayoutVariable layout1 = new GridLayoutVariable (GridLayoutVariable.FIXED_NUM_COLUMNS, 1);
        	panelHolder.setLayout(layout1);
        	//System.out.println("PanelODORData.setEntity fields "+fields.length+" "+groupOfComps.length);
        	
        	 for(int gc=0;gc<entityGroupOfComps.length;gc++)
        	 {
        	 	 
        	 	 JxPanel panelGroup = new JxPanel();
        	 	 GridLayoutVariable layoutGroup = new GridLayoutVariable (GridLayoutVariable.FIXED_NUM_COLUMNS, entityGroupOfComps[gc].getColumnsOfObjects());
        	 	 panelGroup.setLayout(layoutGroup);
        	 	 //panelGroup.setBorder(new TitledBorder(entityGroupOfComps.getCaption()[gc])); 
        	 	 listPanelGroups.add(panelGroup);
                 tabbedPane.addTab(entityGroupOfComps[gc].getCaption(),panelGroup);
        	 	 
        	 	 //panelHolder.add(panelGroup);
        	 }
       	  
       	   panelMain.add(tabbedPane);      

         }
         else
         {
         	
         panelReportFilters.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 8));/// change no of columns in filters of panels(not reports)
         //panelReportFilters.setBorder(new TitledBorder("φίλτρα εγγραφών")); 
         //.setBorder( new TitledBorder(BorderFactory.createTitledBorder(null, "προτιμήσεις εκτύπωσης",TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null,CLR_PANEL_BORDER)));                                       
        // intSizeOfTextBox=10; 
             
         panelMain.add(panelReportFilters); 
         }
       
       
       //panelReportFilters.setBorder(new TitledBorder("φίλτρα εγγραφών")); 
       //panelMain.add(panelReportFilters); 
       intSizeOfTextBox=10; 
       intSizeOfTextBoxKey= 3;  	
     }
     else if(calledFromPanel==PANEL_FILTER_REPORT)
     {
       panelReportFilters.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 4));
       if(entityGroupOfComps!=null)
       {
       	 
        	JxPanel panelHolder = new JxPanel();
        	panelHolder.setOpaque(false);
        	GridLayoutVariable layout1 = new GridLayoutVariable (GridLayoutVariable.FIXED_NUM_COLUMNS, 1);
        	panelHolder.setLayout(layout1);
        	//System.out.println("PanelODORData.setEntity fields "+fields.length+" "+groupOfComps.length);
        	
        	 for(int gc=0;gc<entityGroupOfComps.length;gc++)
        	 {
        	 	 if(entityGroupOfComps[gc]==null)
        	 	 {
        	 	 	System.out.println("error PanelODORData.setEntity entityGroupOfComps is "+entityGroupOfComps[gc]);
        	 	 }
        	 	 
        	 	 JxPanel panelGroup = new JxPanel();
        	 	 
        	 	 GridLayoutVariable layoutGroup = new GridLayoutVariable (GridLayoutVariable.FIXED_NUM_COLUMNS, entityGroupOfComps[gc].getColumnsOfObjects());
        	 	 panelGroup.setLayout(layoutGroup);
        	 	 
        	 	// PanelCollapsable panelCollapsable = new PanelCollapsable(panelGroup,entityGroupOfComps[gc].getCaption(),true);
        	 	  //panelGroup.setBorder(new TitledBorder(new MatteBorder(1,0,0,0,this.getBackground().brighter()),entityGroupOfComps[gc].getCaption()));  // CLR_PANEL_BORDER
        	 	 panelGroup.setBorder( new TitledBorder(BorderFactory.createTitledBorder(lineBorder, entityGroupOfComps[gc].getCaption(),TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null,CLR_PANEL_BORDER)));                                       
        	 	 listPanelGroups.add(panelGroup);
        	 	 panelHolder.add(panelGroup);
        	 }
       	  
       	  panelMain.add(panelHolder);
       }
       else
       {
       	    //panelReportFilters.setBorder(new TitledBorder("φίλτρα εγγραφών")); 
            panelReportFilters.setBorder( new TitledBorder(BorderFactory.createTitledBorder(null, "φίλτρα εγγραφών",TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null,CLR_PANEL_BORDER)));                                       
             //panelReportFilters.setBorder(new TitledBorder(new MatteBorder(1,0,0,0,this.getBackground().brighter()),"φίλτρα εγγραφών"));  // CLR_PANEL_BORDER
       	    panelMain.add(panelReportFilters); 
       }
       
       intSizeOfTextBox=16;
       intSizeOfTextBoxKey=  5;	
     }
     else if(calledFromPanel==PANEL_FILTER_TASK)
     {
       panelReportFilters.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 4));
       //panelReportFilters.setBorder(new TitledBorder("φίλτρα εγγραφών")); 
       panelMain.add(panelReportFilters);  
       intSizeOfTextBox=13;  
       intSizeOfTextBoxKey=5;
     }
     else if(calledFromPanel==PANEL_FILTER_CALCULATE_DIALOG)
     {     // is like  PANEL_FILTER_REPORT
       panelReportFilters.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 4));
       if(entityGroupOfComps!=null)
       {
       	 
        	JxPanel panelHolder = new JxPanel();
        	panelHolder.setOpaque(false);
        	GridLayoutVariable layout1 = new GridLayoutVariable (GridLayoutVariable.FIXED_NUM_COLUMNS, 1);
        	panelHolder.setLayout(layout1);
        	//System.out.println("PanelODORData.setEntity fields "+fields.length+" "+groupOfComps.length);
        	
        	 for(int gc=0;gc<entityGroupOfComps.length;gc++)
        	 {
        	 	 if(entityGroupOfComps[gc]==null)
        	 	 {
        	 	 	System.out.println("error PanelODORData.setEntity entityGroupOfComps is "+entityGroupOfComps[gc]);
        	 	 }
        	 	 
        	 	 JxPanel panelGroup = new JxPanel();
        	 	 
        	 	 GridLayoutVariable layoutGroup = new GridLayoutVariable (GridLayoutVariable.FIXED_NUM_COLUMNS, entityGroupOfComps[gc].getColumnsOfObjects());
        	 	 panelGroup.setLayout(layoutGroup);
        	 	 
        	 	// PanelCollapsable panelCollapsable = new PanelCollapsable(panelGroup,entityGroupOfComps[gc].getCaption(),true);
        	 	  //panelGroup.setBorder(new TitledBorder(new MatteBorder(1,0,0,0,this.getBackground().brighter()),entityGroupOfComps[gc].getCaption()));  // CLR_PANEL_BORDER
          
                          //  below is the only change
                	 //	 panelGroup.setBorder( new TitledBorder(BorderFactory.createTitledBorder(lineBorder, entityGroupOfComps[gc].getCaption(),TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null,CLR_PANEL_BORDER)));                                       
                 panelGroup.setBorder( new TitledBorder(BorderFactory.createTitledBorder(null, entityGroupOfComps[gc].getCaption(),TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null,CLR_PANEL_BORDER)));                                       	 	 
           
                 listPanelGroups.add(panelGroup);
        	 	 panelHolder.add(panelGroup);
        	 }
       	  
       	  panelMain.add(panelHolder);
       }
       else
       {
       	    //panelReportFilters.setBorder(new TitledBorder("φίλτρα εγγραφών")); 
            panelReportFilters.setBorder( new TitledBorder(BorderFactory.createTitledBorder(null, "φίλτρα εγγραφών",TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null,CLR_PANEL_BORDER)));                                       
             //panelReportFilters.setBorder(new TitledBorder(new MatteBorder(1,0,0,0,this.getBackground().brighter()),"φίλτρα εγγραφών"));  // CLR_PANEL_BORDER
       	    panelMain.add(panelReportFilters); 
       }         
        intSizeOfTextBox=16;
       intSizeOfTextBoxKey=  5;	
     }     
     else
     {
     	System.out.println("PanelDataFilter.setEntity calledFromPanel "+calledFromPanel);
     }
     
      
      //query=queryIn;
      entityFilterSettings=entityFilterSettingsIn;
      //System.out.println("PanelDataFilter.entityFilterSettings"+entityFilterSettings.length);


    fieldFilterTxts1 = new ArrayList(); // to hold txt
    fieldFilterTxts2 = new ArrayList(); // to hold txt

    fieldTxtsLookUpId = new ArrayList(); // to hold tb
    fieldTxtsLookUpText = new ArrayList(); // to hold tb2

    JxPanel panelFilterLabel ;
    
       fieldFilterTxts1.clear();
       fieldFilterTxts2.clear();
       fieldTxtsLookUpId.clear();
       fieldTxtsLookUpText.clear();
     //  fieldTxtsLookUpId2.clear();
       //fieldTxtsLookUpText2.clear();
       
       
       //txtFilterSetting1 = new JTextField(4);
       //txtFilterSetting2 = new JTextField(4);
      //utilsDate.readFromFileDateFormats();
      
       //winLookUpCheck = new WindowLookUpMultipleCheck(frame);

       if(entityFilterSettings!=null)
       {
       	int lu =0; //look up count
         for (int s = 0 ; s<entityFilterSettings.length; s++)
         {  
                 // if error here then change in entitydata length array                 
             int groupOfComps = entityFilterSettingsIn[s].groupOfComps;

              panelFilterLabel = new JxPanel();
              panelFilterLabel.setLayout(flowLayoutLabelRight);
              //lblFilterSetting
              panelFilterSetting = new JxPanel();
              panelFilterSetting.setLayout(flowLayoutLeft2);
              
        	lblFilterSetting1=new JLabel("",JLabel.RIGHT);
        	lblFilterSetting=new JLabel("",JLabel.RIGHT);
        
                
                    if(entityFilterSettings[s].getFieldObligatoryOrSuggest()==FIELD_OBLIGATORY)
                    {
                    	lblFilterSetting1.setIcon(ICO_FIELDOBLIGATORY); 
                        lblFilterSetting1.setToolTipText("<html><table><tr><td>Το πεδίο </td><td><img src=\""+ICO_FIELDOBLIGATORY+"\"></td><td>'"+entityFilterSettings[s].caption+"'</td><td> είναι υποχρεωτικό να συμπληρωθεί.</td></tr></table></html>");
                        lblFilterSetting.setIcon(ICO_FIELDOBLIGATORY);              
                        lblFilterSetting.setToolTipText("<html><table><tr><td>Το πεδίο </td><td><img src=\""+ICO_FIELDOBLIGATORY+"\"></td><td>'"+entityFilterSettings[s].caption+"'</td><td> είναι υποχρεωτικό να συμπληρωθεί.</td></tr></table></html>");
                    }
                    else if(entityFilterSettings[s].getFieldObligatoryOrSuggest()==FIELD_SUGGEST)
                    {
                    	lblFilterSetting1.setIcon(ICO_FIELDSUGGEST); 
                        lblFilterSetting1.setToolTipText("<html><table><tr><td>Το πεδίο </td><td><img src=\""+ICO_FIELDSUGGEST+"\"></td><td>'"+entityFilterSettings[s].caption+"'</td><td> προτείνεται να συμπληρωθεί.</td></tr></table></html>");//"προτεινόμενο πεδίο");
                        lblFilterSetting.setIcon(ICO_FIELDSUGGEST); 
                        lblFilterSetting.setToolTipText("<html><table><tr><td>Το πεδίο </td><td><img src=\""+ICO_FIELDSUGGEST+"\"></td><td>'"+entityFilterSettings[s].caption+"'</td><td> προτείνεται να συμπληρωθεί.</td></tr></table></html>");//"προτεινόμενο πεδίο");
                    }
                    else if(entityFilterSettings[s].getFieldObligatoryOrSuggest()==FIELD_NOCOMPLETION)
                    {
                    	
                    }                
                

                    
                    

                 
                    

                    
            //---dates no------
            
           JTextField txtFilterSetting1 = null;
           JTextField txtFilterSetting2 = null;
           JTextField txtFilterSettingText1;
           JTextField txtFilterSettingText2;

        
         // System.out.println("PanelDataFilter.setEntity"+s+" "+entityFilterSettings[s].getValue());
        
        if(entityFilterSettings[s]  == null)
        {
           System.out.println(" error: PanelDataFilters.setEntity: total no of array entityReportSetting is ("+entityFilterSettings.length+")> than the actual no of values ("+s+"is null)");
        }
        
        if(entityFilterSettings[s].type.equalsIgnoreCase("checkboxTable"))	
        {
            String queryWhere = "";
        	String qWhere = lookUp.getQuerySubqueryWhere(entityFilterSettings[s].dbTable);
                String qWhereIsActive = lookUp.getQuerySubqueryIsActive(entityFilterSettings[s].dbTable);
                
             //System.out.println("PanelDataFilters.setEntity: dbTable:"+entityFilterSettings[s].dbTable+"  caption:"+entityFilterSettings[s].caption+"  qWhere:"+qWhere);
                
                if(utilsString.hasQueryWhere(qWhere))
                {
                    queryWhere = qWhere+" "+qWhereIsActive;
                }
                else
                {
                   // System.out.println(" error: PanelDataFilters.setEntity NO WHERE QUERY   qWhere:"+qWhere+"   qWhereIsActive:"+qWhereIsActive);
                }
                
            String queryAll = lookUp.getQuery(entityFilterSettings[s].dbTable)+" "+queryWhere+" "+lookUp.getQueryOrderBy(entityFilterSettings[s].dbTable);//"SELECT * FROM dbcompany";
        	//String table = entityFilterSettings[s].dbTable;
        	 //lblFilterSetting = new JLabel(JLabel.RIGHT);
        	 lblFilterSetting.setText(entityFilterSettings[s].caption+":("+KEYSTROKE_F_LOOKUP_SHOW+")");
      		 JTextField  txtFilterSetting = new JTextField(intSizeOfTextBox);
             txtFilterSetting.setText(entityFilterSettings[s].getValue());
             
             Action actionShowCheck = new ActionShowWinLookUpCheck(entityFilterSettings[s].caption,queryAll, lookUp.getEntityFilterSettings(entityFilterSettings[s].dbTable) , txtFilterSetting, lookUp.getIntValidationColumn(entityFilterSettings[s].dbTable), lookUp.getIntValidationType(entityFilterSettings[s].dbTable));

             JTextBoxWithEditButtons txtLookUpBtn = new JTextBoxWithEditButtons();       
             txtLookUpBtn = new JTextBoxWithEditButtons(txtFilterSetting, true ,ICO_CHECKS,actionShowCheck, false,null,null,0, frame,"","",MONTH_DATE_ONLY);                  	

             txtLookUpBtn.getDocument().addDocumentListener(new DocumentHandler(s,lu,0,null,entityFilterSettings[s].filterFromSelectedField)); 
             
             txtFilterSetting.getInputMap().put(KeyStroke.getKeyStroke(KEYSTROKE_F_LOOKUP_SHOW), "showWindowCheck"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
             txtFilterSetting.getActionMap().put("showWindowCheck", actionShowCheck);

        		   fieldFilterTxts1.add(txtLookUpBtn);// array list
                   fieldFilterTxts2.add(txtLookUpBtn);// array list not needed but date 2 produces error about array size
        		   panelFilterLabel.add(lblFilterSetting);
        		   panelFilterSetting.add(txtLookUpBtn);
                   
        }// checkboxTable
        else if (entityFilterSettings[s].type.equalsIgnoreCase("table"))
        {
        /*table = new JTable();
        tableScrollPane = new JScrollPane();
        tableScrollPane.setViewportView(table);
        
        // not delete it:  cannot format given object as a number
        TableRenderer tcr = new TableRenderer();
        TableCellRendererDouble tcrDouble = new TableCellRendererDouble();
        TableCellRendererInteger tcrInteger = new TableCellRendererInteger();
        TableCellRendererDate tcrDate = new TableCellRendererDate();
        
        // see alse PrintTable
        table.setDefaultRenderer(Object.class, tcr);
        table.setDefaultRenderer(Integer.class, tcrInteger);
        table.setDefaultRenderer(Number.class, tcrInteger);
        table.setDefaultRenderer(Double.class, tcrDouble); 
        table.setDefaultRenderer(java.util.Date.class, tcrDate);         	
        
        table.setShowVerticalLines(true);        
        table.setShowHorizontalLines(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setGridColor(table.getTableHeader().getBackground());
        table.setGridColor(CLR_TABLE_GRID);

        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);//.SINGLE_SELECTION);// select a single row only  
        
        
        String query = lookUp.getQuery(entityFilterSettings[s].dbTable);//"SELECT * FROM dbcompany";
        tableModel= new TableModelResultSetCheckBoxes();
        table.setModel(tableModel);
        tableModel.setQuery(query);
 
        //table.setTableHeader(null); //no table header
     
        utilsTable=new UtilsTable();
        int totalWidthOfColumns =20;
        for (int c=0; c<table.getColumnCount(); c++)
        {   // table,column, margin
            totalWidthOfColumns=totalWidthOfColumns+utilsTable.packColumn(table, c, 2);
        }
        //System.out.println("----"+totalWidthOfColumns);
        setTableScrollPaneSize(totalWidthOfColumns);
        
        panelTable = new JPanel();
        panelTable.setLayout(new BorderLayout());
        JLabel lblTableCaption = new JLabel(entityFilterSettings[s].caption+":");
        
        panelTable.add(lblTableCaption, BorderLayout.PAGE_START);
        panelTable.add(tableScrollPane, BorderLayout.CENTER);
        
        panelFilterSetting.add(panelTable);*/
        } // if type table   
        else if(entityFilterSettings[s].type.equalsIgnoreCase("onelookup"))
        {
        	        JTextBoxWithEditButtons txtLookUpBtn = new JTextBoxWithEditButtons();
                    JTextBoxWithEditButtons txtLookUpBtnText = new JTextBoxWithEditButtons(); 

        		    lblFilterSetting1.setText(entityFilterSettings[s].caption+":("+KEYSTROKE_F_LOOKUP_SHOW+")");
            		//lblFilterSetting.setText(entityFilterSettings[s].caption+":"+(KEYSTROKE_F_LOOKUP_SHOW+") ως:");
        		   	
        		   txtFilterSetting1 = new JTextField(intSizeOfTextBoxKey);
        		   txtFilterSettingText1 = new JTextField(intSizeOfTextBox);
                   
                   String foreignTable = entityFilterSettings[s].dbTable;
                   //System.out.println("PanelDataFilter.setEmtity onelookup "+lu+" "+fieldTxtsLookUpId.size());
                       Action actionShowDialogLookUp = new ActionShowDialogLookUp(foreignTable, lu,lu,s,entityFilterSettings[s].filterFromSelectedField);
        		   
                     txtLookUpBtn = new JTextBoxWithEditButtons(txtFilterSetting1, false ,ICO_LOOKUP,actionShowDialogLookUp, false,null,null,0, frame,"","",MONTH_DATE_ONLY);                  	
                     txtLookUpBtnText = new JTextBoxWithEditButtons(txtFilterSettingText1, true ,ICO_LOOKUP,actionShowDialogLookUp, false,null,null,0, frame,"","",MONTH_DATE_ONLY);                  	        		   

                       txtFilterSetting1.getInputMap().put(KeyStroke.getKeyStroke(KEYSTROKE_F_LOOKUP_SHOW), "displayDialogLookUp"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
                       txtFilterSetting1.getActionMap().put("displayDialogLookUp", actionShowDialogLookUp);
                       txtFilterSettingText1.getInputMap().put(KeyStroke.getKeyStroke(KEYSTROKE_F_LOOKUP_SHOW), "displayDialogLookUp"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
                       txtFilterSettingText1.getActionMap().put("displayDialogLookUp", actionShowDialogLookUp);
        		   
        		   fieldFilterTxts1.add(txtFilterSetting1);// array list to calculateSubquery
        		   fieldFilterTxts2.add(txtFilterSetting1); // array list to calculateSubquery (just to be calculated with s)
        		   
        		   fieldTxtsLookUpId.add(txtLookUpBtn);  // array list
        		   fieldTxtsLookUpText.add(txtLookUpBtnText);
        		   

        		   //txtFilterSettingText1.setEditable(false);
        		   
        		   final int sFinal=s;
        		   final int luFinal=lu;

                   txtLookUpBtn.getDocument().addDocumentListener(new DocumentHandler(s,lu,0,foreignTable,entityFilterSettings[s].filterFromSelectedField)); 
                   	
                   	//System.out.println("PanelDataFilter.setEntity s"+s+" lu"+lu+"---");
        	
        	     final   JTextBoxWithEditButtons txtLookUpBtnFinal = txtLookUpBtn;
                    final JTextBoxWithEditButtons txtLookUpBtnTextFinal = txtLookUpBtnText;
                   
                   txtLookUpBtnFinal.addFocusListener(new FocusListener()
                   {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox                       


                           String lookupValue = txtLookUpBtnFinal.getText();
                           String lookupResult = txtLookUpBtnTextFinal.getText();
                           if (!lookupValue.equalsIgnoreCase("")&&!lookupValue.equalsIgnoreCase(" ")&&!lookupValue.equalsIgnoreCase("  "))// if this is empty then do nothing
                           {
                              
                              //System.out.println("PanelDataFilter.setEntity lookupResult "+entityFilterSettings[sFinal].dbTable);
                            // String lookupResult = utilsPanelReport.getLookupValue(entityFilterSettings[sFinal].dbTable,lookupValue,1);
                            //String lookupResult = txtLookUpBtnText.getText();
                             
                             if (lookupResult.equalsIgnoreCase("")) // - because getLookupValue produces - when not have any data
                             {     // if value not found
                               utilsGui.showMessageError("Key typed not correct. Please retry or delete value.");                               
                                 //txtLookUpBtnFinal.requestFocus();  // if not correct return to first textfield of lookup
                                 //txtLookUpBtnFinal.setSelectionStart(0);
                                 //txtLookUpBtnFinal.setSelectionEnd(txtLookUpBtnFinal.getText().length());
                                  //tb.setBackground(Color.RED);
                                  //txtLookUpBtn.requestFocus();
                                  //txtLookUpBtn.setText("");
                                  //txtLookUpBtnText.setText(""); 
                            /* }                            
                             else
                             {   
                                 txtLookUpBtnText.setText(lookupResult.trim());      
                             }*/
                            }
                            else  
                            { 
                               // txtLookUpBtnText.setText("");
                            }                           
                           }   
                        }
                        public void focusGained(FocusEvent e)
                        {    
                        }
                    });
    	               //System.out.println("PanelDataFilters.setEntity "+lu);  

                     //  final String foreignTableFinal = foreignTable;
                       
	               lu++;// look up count   

                    txtFilterSetting1.setText(entityFilterSettings[s].getValue());
                    //System.out.println("-----------"+s+"--"+entityFilterSettings[s].getValue());
                    	                   
                    String lookupValue = txtLookUpBtn.getText();  
                    String val ="";
                    if (!lookupValue.equalsIgnoreCase(""))
                    {
                    	val = utilsPanelReport.getLookupValue(""/*name*/,entityFilterSettings[s].dbTable,lookupValue,1,true,"","",""); //String subqueryWhereForAPreviousFieldValue,String formGlobalVariable1)
                    }
                   txtFilterSettingText1.setText(val);  

        		   // for lookup fromto (panelReportFilters = GridLayout)
        		   JxPanel panelFilterSetting1 = new JxPanel();
                   panelFilterSetting1.setLayout(flowLayoutLeft2);
                   
                   //panelReportFilters.add(lblFilterSetting1);
                   panelFilterLabel.add(lblFilterSetting1);
        		   panelFilterSetting1.add(txtLookUpBtn);
        		   panelFilterSetting1.add(txtLookUpBtnText);       		   
        		   //panelReportFilters.add(panelFilterSetting1);
                   //System.out.println("PanelDataFilter "+s+" "+entityFilterSettings[s].type);
                   //panelFilterSetting.add(lblFilterSetting1);
        		   panelFilterSetting.add(txtLookUpBtn);
        		   panelFilterSetting.add(panelFilterSetting1);
                   

                   	
        	      //	 lu++;// look up count	
        		 
              	  /* fieldFilterTxts1.add(txtFilterSetting1);
              	   fieldFilterTxts2.add(txtFilterSetting2);*/

        	       	
        }    	// type onelookup 
        else
        {
        	
               	// fromto -> while is 1 object the 'to' it is layed out in different column because the object is lengthy
        	if(entityFilterSettings[s].equivalence.equalsIgnoreCase("fromto"))
        	{
        		
             JTextBoxWithEditButtons txtLookUpBtn = new JTextBoxWithEditButtons();
             JTextBoxWithEditButtons txtLookUpBtnText = new JTextBoxWithEditButtons(); 
             
             
             JTextBoxWithEditButtons txtLookUpBtn2 = new JTextBoxWithEditButtons();
             JTextBoxWithEditButtons txtLookUpBtnText2 = new JTextBoxWithEditButtons(); 
        		
        		
        		if (entityFilterSettings[s].type.equalsIgnoreCase("lookup"))
        		{
        		    lblFilterSetting1.setText(entityFilterSettings[s].caption+":("+KEYSTROKE_F_LOOKUP_SHOW+") από:");
            		lblFilterSetting.setText(/*entityFilterSettings[s].caption+*/":("+KEYSTROKE_F_LOOKUP_SHOW+") ως:");
        		   	
        		   txtFilterSetting1 = new JTextField( intSizeOfTextBoxKey);
        		   txtFilterSettingText1 = new JTextField(intSizeOfTextBox);
                   
                   String dbtable = entityFilterSettings[s].dbTable;
                   
                       Action actionShowDialogLookUp = new ActionShowDialogLookUp(dbtable, lu,lu,s,entityFilterSettings[s].filterFromSelectedField);
        		   
        		   //System.out.println("PanelDataFilter.setEmtity fromto "+lu+" "+fieldTxtsLookUpId.size());
        		   
                     txtLookUpBtn = new JTextBoxWithEditButtons(txtFilterSetting1, false ,ICO_LOOKUP,actionShowDialogLookUp, false,null,null,0, frame,"","",MONTH_DATE_ONLY);                  	
                     txtLookUpBtnText = new JTextBoxWithEditButtons(txtFilterSettingText1, true ,ICO_LOOKUP,actionShowDialogLookUp, false,null,null,0, frame,"","",MONTH_DATE_ONLY);                  	        		   

                       txtFilterSetting1.getInputMap().put(KeyStroke.getKeyStroke(KEYSTROKE_F_LOOKUP_SHOW), "displayDialogLookUp"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
                       txtFilterSetting1.getActionMap().put("displayDialogLookUp", actionShowDialogLookUp);
                       txtFilterSettingText1.getInputMap().put(KeyStroke.getKeyStroke(KEYSTROKE_F_LOOKUP_SHOW), "displayDialogLookUp"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
                       txtFilterSettingText1.getActionMap().put("displayDialogLookUp", actionShowDialogLookUp);
        		   
        		   
        		   fieldFilterTxts1.add(txtLookUpBtn);// array list
        		   //fieldFilterTxts2.add(txtLookUpBtnText);
        		   
        		   fieldTxtsLookUpId.add(txtLookUpBtn);  // array list
        		   fieldTxtsLookUpText.add(txtLookUpBtnText);
        		   

        		   //txtFilterSettingText1.setEditable(false);
        		   
        		   //final int sFinal=s;
        		   //final int luFinal=lu;

              txtLookUpBtn.getDocument().addDocumentListener(new DocumentHandler(s,lu,0,entityFilterSettings[s].dbTable,entityFilterSettings[s].filterFromSelectedField)); 
             
        		
        	  
                   /*txtLookUpBtn.addFocusListener(new FocusListener()
                   {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox                       

                           JTextBoxWithEditButtons txtLookUpBtn = (JTextBoxWithEditButtons)fieldTxtsLookUpId.get(luFinal);
                           JTextBoxWithEditButtons txtLookUpBtnText = (JTextBoxWithEditButtons)fieldTxtsLookUpText.get(luFinal);// arraylist starts from 0
                           String lookupValue = txtLookUpBtn.getText();
                           if (!lookupValue.equalsIgnoreCase("")&&!lookupValue.equalsIgnoreCase(" ")&&!lookupValue.equalsIgnoreCase("  "))
                           {

                             String lookupResult = utilsPanelReport.getLookupValue(entityFilterSettings[sFinal].dbTable,lookupValue,1);
                             if (lookupResult.equalsIgnoreCase("-")) // - because getLookupValue produces - when not have any data
                             {     // if value not found
                                  //tb.setBackground(Color.RED);
                                  txtLookUpBtn.requestFocus();
                                  txtLookUpBtn.setText("");
                                  txtLookUpBtnText.setText(""); 
                             }
                             else
                             {   
                                 txtLookUpBtnText.setText(lookupResult.trim());      
                             }
                           }
                           else  
                           { 
                                txtLookUpBtnText.setText("");
                           }                           
                                       
                        }
                        public void focusGained(FocusEvent e)
                        {    
                        }
                    });*/
    	               //System.out.println("PanelDataFilters.setEntity "+lu);  

                     //  final String dbtableFinal = dbtable;
                       
	               lu++;// look up count   
	                   
        		   txtFilterSetting2 = new JTextField( intSizeOfTextBoxKey);
        		   txtFilterSettingText2 = new JTextField(intSizeOfTextBox-3);

                       Action actionShowDialogLookUp2 = new ActionShowDialogLookUp(dbtable, lu,lu,s,entityFilterSettings[s].filterFromSelectedField);
        		   
                     txtLookUpBtn2 = new JTextBoxWithEditButtons(txtFilterSetting2, false ,ICO_LOOKUP,actionShowDialogLookUp2, false,null,null,0, frame,"","",MONTH_DATE_ONLY);                 	
                     txtLookUpBtnText2 = new JTextBoxWithEditButtons(txtFilterSettingText2, true ,ICO_LOOKUP,actionShowDialogLookUp2, false,null,null,0, frame,"","",MONTH_DATE_ONLY);                	        		   

                       txtFilterSetting2.getInputMap().put(KeyStroke.getKeyStroke(KEYSTROKE_F_LOOKUP_SHOW), "displayDialogLookUp"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
                       txtFilterSetting2.getActionMap().put("displayDialogLookUp", actionShowDialogLookUp2);
                       txtFilterSettingText2.getInputMap().put(KeyStroke.getKeyStroke(KEYSTROKE_F_LOOKUP_SHOW), "displayDialogLookUp"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
                       txtFilterSettingText2.getActionMap().put("displayDialogLookUp", actionShowDialogLookUp2);

        		   //fieldFilterTxts1.add(); 
        		   fieldFilterTxts2.add(txtLookUpBtn2);// array list 
        		   
        		   fieldTxtsLookUpId.add(txtLookUpBtn2); // array list
        		   fieldTxtsLookUpText.add(txtLookUpBtnText2);

        		  // txtFilterSettingText2.setEditable(false);
                       
                   txtLookUpBtn2.getDocument().addDocumentListener(new DocumentHandler(s,lu,0,entityFilterSettings[s].dbTable,entityFilterSettings[s].filterFromSelectedField));         		       
                   /*final int luFinal2=lu;
                       
                   txtLookUpBtn2.addFocusListener(new FocusListener()
                   {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox                       

                           JTextBoxWithEditButtons txtLookUpBtn = (JTextBoxWithEditButtons)fieldTxtsLookUpId.get(luFinal2);
                           JTextBoxWithEditButtons txtLookUpBtnText = (JTextBoxWithEditButtons)fieldTxtsLookUpText.get(luFinal2);// arraylist starts from 0
                           String lookupValue = txtLookUpBtn.getText();

                           if (!lookupValue.equalsIgnoreCase("")&&!lookupValue.equalsIgnoreCase(" ")&&!lookupValue.equalsIgnoreCase("  "))
                           {
                           	
                             String lookupResult = utilsPanelReport.getLookupValue(entityFilterSettings[sFinal].dbTable,lookupValue,1);
                             
                             if (lookupResult.equalsIgnoreCase("-")) // - because getLookupValue produces - when not have any data
                             {     // if value not found
                                  //tb.setBackground(Color.RED);
                                  txtLookUpBtn.requestFocus();
                                  txtLookUpBtn.setText("");
                                  txtLookUpBtnText.setText(""); 
                             }
                             else
                             {   
                                 txtLookUpBtnText.setText(lookupResult.trim());      
                             }
                           }
                           else  
                           { 
                                txtLookUpBtnText.setText("");
                           }                           
                                       
                        }
                        public void focusGained(FocusEvent e)
                        {    
                        }
                    });	*/
                    
                    txtFilterSetting1.setText(entityFilterSettings[s].getValue());  
                    txtFilterSetting2.setText(entityFilterSettings[s].getValue());  
	                   

        		   // for lookup fromto (panelReportFilters = GridLayout)
        		   JxPanel   panelFilterSetting1 = new JxPanel();
                   panelFilterSetting1.setLayout(flowLayoutLeft2);
                   
                   //panelReportFilters.add(lblFilterSetting1);
                   //panelFilterLabel.add(lblFilterSetting1);
        		   panelFilterSetting1.add(txtLookUpBtn);
        		   panelFilterSetting1.add(txtLookUpBtnText);       		   
        		   //panelReportFilters.add(panelFilterSetting1);
         
                  if(entityGroupOfComps != null)
                  {
                  	 //System.out.println("PanelDataFilter.setEntity listPanelGroups.size()"+listPanelGroups.size()+" s"+s+" entityFilterSettings.length"+entityFilterSettings.length);// -1 because i starts from 1
                  	 JxPanel panelGroup = (JxPanel)listPanelGroups.get(groupOfComps);
                  	 
                  	 
                  	 panelGroup.add(lblFilterSetting1);
                  	 panelGroup.add(panelFilterSetting1); 
                  } 
                  else
                  {     panelReportFilters.add(lblFilterSetting1);
                  	 	panelReportFilters.add(panelFilterSetting1);
                  }
         
                   
                   panelFilterLabel.add(lblFilterSetting);
        		   panelFilterSetting.add(txtLookUpBtn2);
        		   panelFilterSetting.add(txtLookUpBtnText2);




        		 lu++;// look up count	
        		 
              	  /* fieldFilterTxts1.add(txtFilterSetting1);
              	   fieldFilterTxts2.add(txtFilterSetting2);*/

        		} // type lookup
        		else if(entityFilterSettings[s].variableType.equalsIgnoreCase("date"))
        		{ // date
        	       lblFilterSetting.setText(entityFilterSettings[s].caption+":("+KEYSTROKE_F_LOOKUP_SHOW+")");
        	            //  setting dates for period
        		   txtFilterSetting1 = new JTextField(10); 		
        		   txtFilterSetting2 = new JTextField(10);
        		   
        		   txtFilterSetting1.setText(entityFilterSettings[s].getValue());  
        		   txtFilterSetting2.setText(entityFilterSettings[s].getValue());  

        		   
        		   int intColumnWidthDate = 8;//utilsDate.getDateFormatEditing().length();
        		   
                                   
                          textFormatedDate = new JFormattedTextField();
                          JTextBoxWithEditButtons txtdate = new JTextBoxWithEditButtons(textFormatedDate, true,ICO_CALENDAR,null , false,null,null,LOOKUP_TYPE_DATE, frame,VariablesGlobal.globalDateFrom,VariablesGlobal.globalDateTo,MONTH_START);                    	
                          textFormatedDate2 = new JFormattedTextField();
                          JTextBoxWithEditButtons txtdate2 = new JTextBoxWithEditButtons(textFormatedDate2, true,ICO_CALENDAR,null , false,null,null,LOOKUP_TYPE_DATE, frame,VariablesGlobal.globalDateFrom,VariablesGlobal.globalDateTo,MONTH_END);                   	
                          
                          final JTextBoxWithEditButtons txtdateFinal = txtdate;
                          final JTextBoxWithEditButtons txtdateFinal2 = txtdate2;
                          final JFrame frameFinal = frame;
                          
                         
                          
                      String dateCurrent = VariablesGlobal.globalDate;

    	              String today = utilsDate.getCurrentDateStringFormattedLocaly(dateCurrent);
                      
                      LocalDate a = LocalDate.parse(utilsDate.reformatDateStringToSaveToDB(VariablesGlobal.globalDate));             
                        int yearCurrent = (a.getYear());
                      //    int yearPrevious = (Integer.parseInt(VariablesGlobal.globalYear))-1;                      
                          final int yearCurrentFinal = yearCurrent;
                      //    final int yearPreviousFinal = yearPrevious;
                          
                          JPopupMenu popupMenuDates = new JPopupMenu();
                          
       JMenuItem menuItem1 = new JMenuItem("χρήση "+utilsDate.reformatDateStringToReadFromDB(VariablesGlobal.globalDateFrom)+"  -  "+utilsDate.reformatDateStringToReadFromDB(VariablesGlobal.globalDateTo));
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
                          txtdateFinal.setText(utilsDate.reformatDateStringToReadFromDB(VariablesGlobal.globalDateFrom));
                          txtdateFinal2.setText(utilsDate.reformatDateStringToReadFromDB(VariablesGlobal.globalDateTo));	        
	        }
	    });           

      /* menuItem2.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	    
                          txtdateFinal.setText("01-01-"+yearPreviousFinal);
                          txtdateFinal2.setText("31-12-"+yearPreviousFinal);	        
	        }
	    });  */     
       
       
       menuItem3.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	    
                          txtdateFinal.setText("01-01-"+yearCurrentFinal);
                          txtdateFinal2.setText("31-03-"+yearCurrentFinal);
	        }
	    });

       menuItem4.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	    
                          txtdateFinal.setText("01-04-"+yearCurrentFinal);
                          txtdateFinal2.setText("30-06-"+yearCurrentFinal);
	        }
	    });       
       
       menuItem5.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	    
                          txtdateFinal.setText("01-07-"+yearCurrentFinal);
                          txtdateFinal2.setText("30-09-"+yearCurrentFinal);
	        }
	    });
       
       menuItem6.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	    
                          txtdateFinal.setText("01-10-"+yearCurrentFinal);
                          txtdateFinal2.setText("31-12-"+yearCurrentFinal);
	        }
	    });       
                
       
       
       
       
                JButtonListMenu btnDatePeriodListMenu = new JButtonListMenu("",popupMenuDates);
                btnDatePeriodListMenu.setMargin(new Insets(1, 9, 1, 1));  // Insets(int top, int left, int bottom, int right)   
                btnDatePeriodListMenu.setIcon(ICO_CALENDAR);


                         
                          JLabel lblTo = new JLabel("ως:");
                        if(calledFromPanel==PANEL_FILTER_SEARCH)
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
                  
                                lblTo.setText(/*entityFilterSettings[s].caption+*/" ως:("+KEYSTROKE_F_LOOKUP_SHOW+")");   
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
                          else if(calledFromPanel==PANEL_FILTER_CALCULATE_DIALOG)
                          {        // is like PANEL_FILTER_REPORT
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
                          }                  
                    
                           txtdate.getDocument().addDocumentListener(new DocumentHandler(s,lu,0,null,entityFilterSettings[s].filterFromSelectedField));   		
                           txtdate2.getDocument().addDocumentListener(new DocumentHandler(s,lu,0,null,entityFilterSettings[s].filterFromSelectedField));  
                         
                            fieldFilterTxts1.add(txtdate.getComponent()); // panelODORD has added to arraylist the textBoxWithButtons but here is the dateformat
                            fieldFilterTxts2.add(txtdate2.getComponent());
        		
                            // default days
                          txtdateFinal.setText(utilsDate.reformatDateStringToReadFromDB(VariablesGlobal.globalDateFrom));
                          txtdateFinal2.setText(VariablesGlobal.globalDate);//  reformatDateStringToReadFromDB  .globalDateTo));                        
                        
                        
                        } // date simple from to
        		else 
        		{
                            lblFilterSetting.setText(entityFilterSettings[s].caption+":");
        	       
        		   txtFilterSetting1 = new JTextField(intSizeOfTextBox-5); 		
        		   txtFilterSetting2 = new JTextField(intSizeOfTextBox-5);
 
         		   txtFilterSetting1.setText(entityFilterSettings[s].getValue());  
        		   txtFilterSetting2.setText(entityFilterSettings[s].getValue()); 
 
        		   JLabel lblTo = new JLabel("ως:");
        		   panelFilterLabel.add(lblFilterSetting);
                           
        		   panelFilterSetting.add(txtFilterSetting1);
        		   panelFilterSetting.add(lblTo);
        		   panelFilterSetting.add(txtFilterSetting2);
        		   
                           
                           txtFilterSetting1.getDocument().addDocumentListener(new DocumentHandler(s,lu,0,null,entityFilterSettings[s].filterFromSelectedField)); 
                         
                           
            	 fieldFilterTxts1.add(txtFilterSetting1); // panelODORD has added to arraylist the textBoxWithButtons but here is the dateformat
        	     fieldFilterTxts2.add(txtFilterSetting2);        			
        				
        		}
        		
        	}// equivalence fromto
        	else // is not fromto
        	{
        		lblFilterSetting.setText(entityFilterSettings[s].caption+":");
        		
        		txtFilterSetting1 = new JTextField(intSizeOfTextBox);
        		txtFilterSetting2 = new JTextField(intSizeOfTextBox);
                        txtFilterSetting1.setToolTipText("χαρακτήρες μπαλαντέρ % και _");
                         txtFilterSetting2.setToolTipText("χαρακτήρες μπαλαντέρ % και _");
                //txtFilterSetting1.setColumns(15);
        		   txtFilterSetting1.setText(entityFilterSettings[s].getValue());  
        		   txtFilterSetting2.setText(entityFilterSettings[s].getValue()); 
        		                   
                panelFilterLabel.add(lblFilterSetting);
        		panelFilterSetting.add(txtFilterSetting1);
                   
                        txtFilterSetting1.getDocument().addDocumentListener(new DocumentHandler(s,lu,0,null,entityFilterSettings[s].filterFromSelectedField)); 
                        txtFilterSetting2.getDocument().addDocumentListener(new DocumentHandler(s,lu,0,null,entityFilterSettings[s].filterFromSelectedField)); 
        	   fieldFilterTxts1.add(txtFilterSetting1);
        	   fieldFilterTxts2.add(txtFilterSetting2); // added because otherwise it creates error
                
        	} // else  is not fromto

        }// if type not table
  

                  if(entityGroupOfComps == null)
                  {
                  	 //System.out.println("PanelODORData.setEntity "+groupOfComps);
                     panelReportFilters.add(panelFilterLabel);
                     panelReportFilters.add(panelFilterSetting);
                  }
                  else
                  {
                  	 //System.out.println("PanelODORData.setEntity "+groupOfComps.length+" "+(i-1)+" "+groupOfComps[i-1]);// -1 because i starts from 1
                  	 
                  	 if(groupOfComps==-1)
                  	 {
                  	 	
                  	 	
                  	 }
                  	 else
                  	 {
                  	 	 //System.out.println("PanelDataFilter.setEntity "+groupOfComps);
                  	 	
                  	     JxPanel panelGroup = (JxPanel)listPanelGroups.get(groupOfComps);
                  	 
                  	     panelGroup.add(panelFilterLabel);
                  	     panelGroup.add(panelFilterSetting);                   	 	
                  	 }
                  	 // groupOfComps[i].
                  }        


      	//System.out.println("PanelDataFilters.setEntity entityFilterSettings"+entityFilterSettings[s].caption+" "+entityFilterSettings[s].dbField);

         if(txtFilterSetting1 != null)
         {
            final JTextField textfldFinal1 = txtFilterSetting1;
            txtFilterSetting1.addFocusListener(new FocusListener()
            {
                   	 public void focusLost(FocusEvent e)
                     {                      
                     
                     }
                     public void focusGained(FocusEvent e)
                     { 
                           textfldFinal1.setSelectionStart(0);
                           textfldFinal1.setSelectionEnd(textfldFinal1.getText().length());
                     }
            });
         }

         
         if(txtFilterSetting2 != null)
         {
            final JTextField textfldFinal2 = txtFilterSetting2;         	
             txtFilterSetting2.addFocusListener(new FocusListener()
             {
                   	 public void focusLost(FocusEvent e)
                     {                   
                     
                     }
                     public void focusGained(FocusEvent e)
                     { 
                           textfldFinal2.setSelectionStart(0);
                           textfldFinal2.setSelectionEnd(textfldFinal2.getText().length());
                     }
             });
          }
          
          }//for
        }//if
       
       //System.out.println("PanelDataFilter.setEntity   "+fieldFilterTxts1.size());
    if(fieldFilterTxts1.size()>0)  
    {
    	
      	        final JComponent jc = (JComponent)fieldFilterTxts1.get(0);
                SwingUtilities.invokeLater(new Runnable()
                {
                  public void run()
                  {
                  jc.requestFocus();
                  }
                });  
      /*if(fieldFilterTxts1.get(0) instanceof JTextComponent)
      {
      	//System.out.println("PanelDataFilter.JTextComponent");
        final JTextComponent tb = (JTextComponent)fieldFilterTxts1.get(0);
        SwingUtilities.invokeLater(new Runnable()
         {
              public void run()
              {
                  tb.requestFocus();
              }
          });   
      }
      else if(fieldFilterTxts1.get(0) instanceof JTextBoxWithEditButtons)
      {
      	//System.out.println("PanelDataFilter.JTextBoxWithEditButtons");
      	JTextBoxWithEditButtons tbEb = (JTextBoxWithEditButtons)fieldFilterTxts1.get(0);
        final JTextComponent	tb = tbEb.getTextComp();
        SwingUtilities.invokeLater(new Runnable()
         {
              public void run()
              {
                  tb.requestFocus();
              }
          });         	
      }
      else
      {
      	System.out.println("error PanelDataFilter requestFocus "+fieldFilterTxts1.get(0));
      }*/
     }// if(fieldFilterTxts1.size()>0)  
    }
   
    /*
     * 
     * called by 
     */
   public boolean checkIfFieldsAreCompleted()
   {
       boolean areFieldsCompleted=false;
       ArrayList listMessages = new ArrayList();
       listMessages = this.getListOfFieldsUncompleted();

     if(listMessages.size()>0)
     {

        	//1 no, 0 yes  
          int isOk =utilsGui.showMessageFromList(listMessages);
          //System.out.println("PanelDataFilter.checkIfFilesAreCompleted int isOk"+isOk);
          
          if(isOk==0)
          {
             areFieldsCompleted=true;
          
          }   
          else if(isOk==1)
          {
              areFieldsCompleted=false;
          }
          else
          {
              areFieldsCompleted=false;
          }
     
     
     }// if
     else
     {
         areFieldsCompleted=true;
     }

        


       
       
       return areFieldsCompleted;
   }
    
    
    
    /*
    * 
    * exists in PanelOneDataOneRecData, PanelOneDataManyRecData, TableModelResultSet and PanelDataFilter
    */
public ArrayList getListOfFieldsUncompleted()
   {
   	 //boolean ret = false;
   	 //String output="";
   	 //int fieldObligatoryOrSuggest = FIELD_NOCOMPLETION;
   	 ArrayList listMessages = new ArrayList();
   	 boolean isDateEmpty1 = false;
         boolean isDateEmpty2 = false;
         
         //System.out.println("error   PanelDataFilter.getListOfFieldsUncompleted Start A entityFilterSettings="+entityFilterSettings.length);
         //System.out.println("error   PanelDataFilter.getListOfFieldsUncompleted Start B fieldFilterTxts1="+fieldFilterTxts1.size());
         //System.out.println("error   PanelDataFilter.getListOfFieldsUncompleted Start C fieldFilterTxts2="+fieldFilterTxts2.size());
         //System.out.println("error   PanelDataFilter.getListOfFieldsUncompleted Start D entityFilterSettings="+entityFilterSettings+" fieldFilterTxts1="+fieldFilterTxts1+" either NOT have equal length OR are null ");
   	 if(entityFilterSettings!=null && fieldFilterTxts1!=null && fieldFilterTxts2!=null && (entityFilterSettings.length==fieldFilterTxts1.size() && entityFilterSettings.length==fieldFilterTxts2.size()))
   	 {

   	    for(int f=0;f<entityFilterSettings.length;f++)
   	    {
   	    	
                int fieldObligatoryOrSuggest = entityFilterSettings[f].getFieldObligatoryOrSuggest();  	 	   
                if(fieldFilterTxts1.size()>0)  
                {
       
      String text1="";
      JTextComponent ta1 =null;
      if(fieldFilterTxts1.get(f) instanceof JTextComponent)
      {
      	//System.out.println("PanelDataFilter.JTextComponent");
         ta1 = (JTextComponent)fieldFilterTxts1.get(f);
         text1 = ta1.getText().trim(); 
         isDateEmpty1=false;
      }
      else if(fieldFilterTxts1.get(f) instanceof JTextBoxWithEditButtons)
      {
      	//System.out.println("PanelDataFilter.JTextBoxWithEditButtons");
      	JTextBoxWithEditButtons tbEb1 = (JTextBoxWithEditButtons)fieldFilterTxts1.get(f);
        ta1 = tbEb1.getTextComp();
        if(entityFilterSettings[f].variableType.equalsIgnoreCase("date"))
        {
              isDateEmpty1 = tbEb1.isDateEmpty();            
        }
        else
        {
              isDateEmpty1=false;
        }
        //System.out.println("PanelODORData.getListOfFieldsUncompleted isDateEmpty1="+isDateEmpty1+" for f"+f+" "+entityFilterSettings[f].caption);
      	text1 = ta1.getText().trim();   
      }
      else
      {
      	System.out.println("error PanelDataFilter.getListOfFieldsUncompleted for ta1 "+f+" "+fieldFilterTxts1.get(f));
      }
 

//                           JTextComponent ta2 = (JTextComponent) fieldFilterTxts2.get(f); // i-1 because fieldTxts is an array and starts from 0
//                           String text2 = ta2.getText().trim();                  
      
               if(fieldObligatoryOrSuggest==FIELD_OBLIGATORY)
               {
                   //System.out.println("PanelODORData.getListOfFieldsUncompleted FIELD_OBLIGATORY "+f+" text1="+text1.equals("")+" date1="+isDateEmpty1+" for1="+entityFilterSettings[f].caption);
               	  	   if(text1.equals("") && !isDateEmpty1)//is not date
               	  	   {               
 	   		         ta1.setBackground(Color.yellow);
               	  	   	 ta1.revalidate();  
               	  	   	 EntityMessage em =new EntityMessage(entityFilterSettings[f].getCaption(),FIELD_OBLIGATORY,0,f,"");
               	  	   	 listMessages.add(em);               	  	   	     
               	          //System.out.println("PanelODORData.getListOfFieldsUncompleted obligatory "+f+" "+dbFields[f].getDbField()+" is empty ");
               	  	   }
                           else if(!text1.equals("") && isDateEmpty1) //is date
                           {
 	   		         ta1.setBackground(Color.yellow);
               	  	   	 ta1.revalidate();  
               	  	   	 EntityMessage em =new EntityMessage(entityFilterSettings[f].getCaption(),FIELD_OBLIGATORY,0,f,"");
               	  	   	 listMessages.add(em);                                 
                           }

               }
               else if(fieldObligatoryOrSuggest==FIELD_SUGGEST)
               {
                   //System.out.println("PanelODORData.getListOfFieldsUncompleted FIELD_SUGGEST "+f+" text1="+text1.equals("")+" date1="+isDateEmpty1+" for1="+entityFilterSettings[f].caption);
               	  	   if(text1.equals("") && !isDateEmpty1)//is not date
               	  	   {
               	  	   	ta1.setBackground(Color.yellow);	
	                        ta1.revalidate();   
               	  	   	 EntityMessage em =new EntityMessage(entityFilterSettings[f].getCaption(),FIELD_SUGGEST,0,f,"");
               	  	   	 listMessages.add(em);    	                            
	                     //outputSuggest=outputSuggest+outputSuggestFields+" "+dbFields[f].getCaption()+outputSuggestFieldsEnd+"\n";	  	   	
               	  	   	 //System.out.println("PanelODORData.getListOfFieldsUncompleted suggested "+f+" "+dbFields[f].getDbField()+" is empty ");
                           }
                           else if(!text1.equals("") && isDateEmpty1) //is date
                           {
               	  	   	ta1.setBackground(Color.yellow);	
	                        ta1.revalidate();   
               	  	   	 EntityMessage em =new EntityMessage(entityFilterSettings[f].getCaption(),FIELD_SUGGEST,0,f,"");
               	  	   	 listMessages.add(em);                                
                           }
                     
               }
               else if(fieldObligatoryOrSuggest==FIELD_NOCOMPLETION )
               {
                   
               }
               else
   	       {
   	 	System.out.println("PanelDataFilter.getListOfFieldsUncompleted UNKNOWN fieldFilterTxts1 fieldObligatoryOrSuggest="+fieldObligatoryOrSuggest);
   	       }                  
                }// if txt 1
               
                if(fieldFilterTxts2.size()>0 && entityFilterSettings[f].equivalence.equalsIgnoreCase("fromto"))
               {
      String text2="";
      JTextComponent ta2 =null;
      if(fieldFilterTxts2.get(f) instanceof JTextComponent)
      {
      	//System.out.println("PanelDataFilter.JTextComponent");
         ta2 = (JTextComponent)fieldFilterTxts2.get(f);
         text2 = ta2.getText().trim();          
      }
      else if(fieldFilterTxts2.get(f) instanceof JTextBoxWithEditButtons)
      {
      	//System.out.println("PanelDataFilter.JTextBoxWithEditButtons");
      	JTextBoxWithEditButtons tbEb2 = (JTextBoxWithEditButtons)fieldFilterTxts2.get(f);
        ta2 = tbEb2.getTextComp();
        if(entityFilterSettings[f].variableType.equalsIgnoreCase("date"))
        {
              isDateEmpty2 = tbEb2.isDateEmpty();            
        }
        else
        {
              isDateEmpty2=false;
        }
        //System.out.println("PanelODORData.getListOfFieldsUncompleted isDateEmpty2="+isDateEmpty1+" for f"+f+" "+entityFilterSettings[f].caption);        
      	text2 = ta2.getText().trim();   
      }
      else
      {
      	System.out.println("error PanelDataFilter.getListOfFieldsUncompleted for ta2 "+f+" "+fieldFilterTxts2.get(f));
      }                
               
      
               if(fieldObligatoryOrSuggest==FIELD_OBLIGATORY )
               {
               	  	   if(text2.equals("") && !isDateEmpty2)//is not date
               	  	   {               
 	   		        ta2.setBackground(Color.yellow);
               	  	   	ta2.revalidate();  
                                EntityMessage em =new EntityMessage(entityFilterSettings[f].getCaption(),FIELD_OBLIGATORY,0,f,"");
               	  	   	listMessages.add(em);   
          	  	   	     
               	          //System.out.println("PanelODORData.getListOfFieldsUncompleted obligatory "+f+" "+dbFields[f].getDbField()+" is empty ");
               	  	   }
                           else if(!text2.equals("") && isDateEmpty2) //is date
                           {
               	  	   	ta2.setBackground(Color.yellow);	
	                        ta2.revalidate();   
               	  	   	EntityMessage em =new EntityMessage(entityFilterSettings[f].getCaption(),FIELD_OBLIGATORY,0,f,"");
               	  	   	listMessages.add(em);                                
                           }
       	  
               }
               else if(fieldObligatoryOrSuggest==FIELD_SUGGEST )
               {
               	  	   if(text2.equals("") && !isDateEmpty2)//is not date
               	  	   {               
 	   		        ta2.setBackground(Color.yellow);
               	  	   	ta2.revalidate();  
                                EntityMessage em =new EntityMessage(entityFilterSettings[f].getCaption(),FIELD_SUGGEST,0,f,"");
               	  	   	listMessages.add(em);   
          	  	   	     
               	          //System.out.println("PanelODORData.getListOfFieldsUncompleted obligatory "+f+" "+dbFields[f].getDbField()+" is empty ");
               	  	   }
                           else if(!text2.equals("") && isDateEmpty2) //is date
                           {
               	  	   	ta2.setBackground(Color.yellow);	
	                        ta2.revalidate();   
               	  	   	EntityMessage em =new EntityMessage(entityFilterSettings[f].getCaption(),FIELD_SUGGEST,0,f,"");
               	  	   	listMessages.add(em);                                
                           }
               }
               else if(fieldObligatoryOrSuggest==FIELD_NOCOMPLETION )
               {
                   
               }
               else
   	       {
   	 	System.out.println("PanelDataFilter.getListOfFieldsUncompleted UNKNOWN fieldFilterTxts2 fieldObligatoryOrSuggest="+fieldObligatoryOrSuggest);
   	       }      
      
      
               }// if txt2 and entityFilterSettings[s].equivalence.equalsIgnoreCase("fromto")
                
   	    }// for
         }// if   
   	 else
   	 {
                      //System.out.println("error   PanelDataFilter.getListOfFieldsUncompleted Else A entityFilterSettings="+entityFilterSettings);
                      //System.out.println("error   PanelDataFilter.getListOfFieldsUncompleted Else B fieldFilterTxts1="+fieldFilterTxts1.size());
                      //System.out.println("error   PanelDataFilter.getListOfFieldsUncompleted Else C fieldFilterTxts2="+fieldFilterTxts2.size());
             
   	 	System.out.println("PanelDataFilter.getListOfFieldsUncompleted (perhaps has no filters) entityFilterSettings.length fieldFilterTxts1.size either NOT have equal length OR are null ");
   	 }   	    
   	//outputObligatory=outputObligatory+outputObligatoryFields;
        //outputSuggest=outputSuggest+outputSuggestFields;        
       // output=outputObligatory+outputSuggest;    
       // System.out.println("PanelODORData.getListOfFieldsUncompleted \n"+output);
   	 return listMessages;
   }
       
    
    
    
    
   private void displayDialogLookUp(String foreignTable, int i , int j,int noOfFilter, int filterFromPreviousSelectedField)// filterFromPreviousSelectedField   for example we set here 0(companyid) when field is dbyear
   { 
       //System.out.println(comp.getClass());

       DialogLookUp.initialize(frame);
   	   
   	   String selectedKeyValue ="";
       String  selectedKeyValuePrevious="";
       
       //System.out.println("PanelDataFilter.displayDialogLookUp filterFromPreviousSelectedField "+filterFromPreviousSelectedField + " "+noOfFilter);
       
       if(filterFromPreviousSelectedField!=-1)  // for example filter year after company selected
       {	
           JTextBoxWithEditButtons txtLookUpBtnPrevious = (JTextBoxWithEditButtons)fieldTxtsLookUpId.get(noOfFilter-1);
           selectedKeyValuePrevious = txtLookUpBtnPrevious.getText();
       }
       
       JTextBoxWithEditButtons txtLookUpBtn = (JTextBoxWithEditButtons)fieldTxtsLookUpId.get(i);
        selectedKeyValue = txtLookUpBtn.getText();
  

       JTextBoxWithEditButtons txtLookUpBtnEdit = (JTextBoxWithEditButtons)fieldTxtsLookUpText.get(j);
       String  selectedTextValue = txtLookUpBtnEdit.getText();

       
  //System.out.println("--panelDataFilter.displayDialogLookUp -- "+i+" selectedKeyValue:"+selectedKeyValue+" "+lookUp.getLookUpKeyTranslation(foreignTable)+"    noOfFilter:"+noOfFilter+"   filterFromPreviousSelectedField:"+filterFromPreviousSelectedField);
       String selected="";
       
      
       
       if(selectedKeyValue!=null && !selectedKeyValue.trim().equalsIgnoreCase(""))
       {
       	   //where=where+lookUp.getLookUpField(foreignTable)+" LIKE '"+selectedTextValue+"%' "
   	  if(filterFromPreviousSelectedField==-1)
       	  {           
     	   
           String strWhere ="";
           String sub ="";
           String where=lookUp.getQuerySubqueryWhere(foreignTable);
           /*int len = lookUp.getLookUpField(foreignTable).length;
              if(where!=null  && !where.equalsIgnoreCase(""))
           {
               strWhere = " "+where+" AND ";// 'WHERE is already in string from lookup
           }
           else
           {
               strWhere= " WHERE ";
           }
   	   		sub=foreignTable+"."+lookUp.getLookUpKey(foreignTable)+" LIKE '"+selectedKeyValue+"%'";
               */      
   	   		/*for(int o= 0; o<len ;o++)
   	   		{
   	   		   
   	   		   String end ="";	
   	   		   	if(o==0)// if there is only one field
   	   		   	{
   	   		   	   	sub=sub+foreignTable+"."+lookUp.getLookUpField(foreignTable)[i]+" LIKE '"+selectedKeyValue+"%'";
   	   		   	}
   	   		   	else
   	   		   	{
   	   		   		sub=sub+" AND "+foreignTable+"."+lookUp.getLookUpField(foreignTable)[i]+" LIKE '"+selectedKeyValue+"%'";
   	   		   	}
   	   		} */        	   
             String sql = lookUp.getQuery(foreignTable)+/*strWhere*/" "+where+" "+lookUp.getQueryOrderBy(foreignTable);
           	
             selected = DialogLookUp.showDialog(this,foreignTable,sql,lookUp.getLookUpKeyTranslation(foreignTable) , selectedKeyValue,lookUp.getShowToolbar(foreignTable),/*yearEnforce,*/
                   panelManagement,lookUp.getFieldsForSums(foreignTable),fieldFilterTxts1);  
               System.out.println("panelDataFilter.displayDialogLookUp -- A "+i+" selectedKeyValue:"+selectedKeyValue+" "+lookUp.getLookUpKeyTranslation(foreignTable)+"    selected:"+selected+"   sql:"+sql);
       
          }
          else// same as D (the forth else below)
          {

          String sub =  entityFilterSettings[filterFromPreviousSelectedField].dbField+" LIKE '"+selectedKeyValuePrevious+"'"	;
       	  String q = "";
       	   if(utilsString.hasQueryWhere(lookUp.getQuery(foreignTable)))
   	       {
       	      q = " AND "+sub;//lookUp.getLookUpField(foreignTable)+" LIKE '"+tb2Text+"%' "
   	       }
   	       else
   	       {
   	       	  q = " WHERE "+sub;//lookUp.getLookUpField(foreignTable)+" LIKE '"+tb2Text+"%' "
   	       }

       	  	  selected = DialogLookUp.showDialog(this,foreignTable, lookUp.getQuery(foreignTable)+q+" "+lookUp.getQueryOrderBy(foreignTable),lookUp.getLookUpKeyTranslation(foreignTable),
                         selectedKeyValue ,lookUp.getShowToolbar(foreignTable),/*yearEnforce,*/panelManagement,lookUp.getFieldsForSums(foreignTable),fieldFilterTxts1);  
       	  	 //System.out.println("PanelDataFilter.displayDialogLookUp filterFromPreviousSelectedField "+filterFromPreviousSelectedField + " "+noOfFilter);              
              
              
              System.out.println("panelDataFilter.displayDialogLookUp -- B "+i+" selectedKeyValue:"+selectedKeyValue+" "+lookUp.getLookUpKeyTranslation(foreignTable)+"    selected:"+selected+"   q:"+q);
          }
       }
       else // C
       {
   	  if(filterFromPreviousSelectedField==-1)// called when there is data entered in first textbox(primary key) of txtfield
       	  {
              
     	   //String sub ="";
           //String strWhere ="";
           String where=lookUp.getQuerySubqueryWhere(foreignTable);
           
           
              String sql = lookUp.getQuery(foreignTable)+" "+where+" "+lookUp.getQueryOrderBy(foreignTable);
              
              
       	  	   //System.out.println("PanelDataFilter.displayDialogLookUp filterFromPreviousSelectedField "+filterFromPreviousSelectedField + " "+noOfFilter);
       	  	  selected = DialogLookUp.showDialog(this,foreignTable, sql,lookUp.getLookUpKeyTranslation(foreignTable),
                          selectedKeyValue,lookUp.getShowToolbar(foreignTable),/*yearEnforce,*/panelManagement,lookUp.getFieldsForSums(foreignTable),fieldFilterTxts1);  
                  System.out.println("panelDataFilter.displayDialogLookUp -- C "+i+" selectedKeyValue:"+selectedKeyValue+" "+lookUp.getLookUpKeyTranslation(foreignTable)+"    selected:"+selected);
       	  }
       	  else//  D
       	  {
       	  	 //String where ="";

          String sub =  entityFilterSettings[filterFromPreviousSelectedField].dbField+" LIKE '"+selectedKeyValuePrevious+"'"	;
       	  String q = "";
       	   if(utilsString.hasQueryWhere(lookUp.getQuery(foreignTable)))
   	       {
       	      q = " AND "+sub;//lookUp.getLookUpField(foreignTable)+" LIKE '"+tb2Text+"%' "
   	       }
   	       else
   	       {
   	       	  q = " WHERE "+sub;//lookUp.getLookUpField(foreignTable)+" LIKE '"+tb2Text+"%' "
   	       }
   	       
   	       //selected = DialogLookUp.showDialog(this,foreignTable, lookUp.getQuery(foreignTable)+q+lookUp.getQueryOrderBy(foreignTable),lookUp.getLookUpKeyTranslation(foreignTable) , selectedKeyValue,lookUp.getShowToolbar(foreignTable));         	  	 
       	  	 
       	  	/* if(utilsString.hasQueryWhere(lookUp.getQuery(foreignTable)))
       	  	 {
       	  	    
       	  	    where=" AND "+entityFilterSettings[filterFromPreviousSelectedField].dbField+" LIKE '"+selectedKeyValuePrevious+"'"	;
       	  	 	
       	  	 }
       	  	 else
       	  	 {
       	  	 	where=" WHERE "+entityFilterSettings[filterFromPreviousSelectedField].dbField+" LIKE '"+selectedKeyValuePrevious+"'"	;
       	  	 }*/
       	  	 
       	  	  selected = DialogLookUp.showDialog(this,foreignTable, lookUp.getQuery(foreignTable)+q+" "+lookUp.getQueryOrderBy(foreignTable),lookUp.getLookUpKeyTranslation(foreignTable),
                         selectedKeyValue ,lookUp.getShowToolbar(foreignTable),/*yearEnforce,*/panelManagement,lookUp.getFieldsForSums(foreignTable),fieldFilterTxts1);  
       	  	 //System.out.println("PanelDataFilter.displayDialogLookUp filterFromPreviousSelectedField "+filterFromPreviousSelectedField + " "+noOfFilter);
       	     System.out.println("panelDataFilter.displayDialogLookUp -- D "+i+" selectedKeyValue:"+selectedKeyValue+" "+lookUp.getLookUpKeyTranslation(foreignTable)+"    selected:"+selected);
       	  }	        	
       	
           
         //System.out.println("panelODORData.displayDialogLookUp ++ selected:"+selected);
       }
       
     if(selected!= null)
     {
       if (!selected.equalsIgnoreCase("")) // means selected cancel
       {
          txtLookUpBtn.setText(selected.trim());
       
           //needed for tb2 to be updated in the following order
           txtLookUpBtn.requestFocus();
           txtLookUpBtnEdit.requestFocus();
           txtLookUpBtn.requestFocus();
       }
       else
       {    txtLookUpBtn.requestFocus();  }
       
     }
     else
     {
         System.out.println("error   PanelDataFilter.displayDialogLookUp  selected:"+selected);
     }
   }

   /*
   * // except class PanelODORData.calculationFromToolBarButton others call function getPanelFilters() to get the panel. To do: change other calls of function getPanelFilters().
   */
    public JPanel getPanelFilters()
    {

    	return panelMain;
    }
  
           // additionalCol for lookup from to for difference between the twho compnents (the same s)
   private void calculateTextForLookupsAfterKeyIsSet(int col, int additionalCol, String foreignTable,int filterFromPreviousSelectedField)
   {
   	
                          JTextBoxWithEditButtons txtLookUpBtn = (JTextBoxWithEditButtons)fieldTxtsLookUpId.get(additionalCol);
                           JTextBoxWithEditButtons txtLookUpBtnText = (JTextBoxWithEditButtons)fieldTxtsLookUpText.get(additionalCol);// arraylist starts from 0
                           String lookupValue = txtLookUpBtn.getText();
                           if (!lookupValue.equalsIgnoreCase("")&&!lookupValue.equalsIgnoreCase(" ")&&!lookupValue.equalsIgnoreCase("  "))
                           {
                              

       String  selectedKeyValuePrevious=""; 
       String subqueryWhere="";
       if(filterFromPreviousSelectedField!=-1)  // for example filter year after company selected
       {	
           JTextBoxWithEditButtons txtLookUpBtnPrevious = (JTextBoxWithEditButtons)fieldTxtsLookUpId.get(col-1);
           selectedKeyValuePrevious = txtLookUpBtnPrevious.getText();
       //System.out.println("PanelDataFilter.calculateTextForLookupsAfterKeyIsSet   filterFromPreviousSelectedField:"+filterFromPreviousSelectedField +"  selectedKeyValuePrevious:"+selectedKeyValuePrevious);
         subqueryWhere =  entityFilterSettings[filterFromPreviousSelectedField].dbField+" LIKE '"+selectedKeyValuePrevious+"'"	;
       }                          

                               
                   //             System.out.println(" ====  PanelDataFilter.calculateTextForLookupsAfterKeyIsSet  table:"+entityFilterSettings[col].dbTable +"   sub:"+subqueryWhere);
                             String lookupResult = utilsPanelReport.getLookupValue(""/*name*/,entityFilterSettings[col].dbTable,lookupValue,1,true,"",subqueryWhere,"");
                            
                             
                             
                             
                              
                             
                             if (lookupResult.equalsIgnoreCase("-")) // - because getLookupValue produces - when not have any data
                             {     // if value not found
                                  //tb.setBackground(Color.RED);
                                  //txtLookUpBtn.requestFocus();
                                  //txtLookUpBtn.setText("");
                                  txtLookUpBtnText.setText(""); 
                             }
                             else
                             {   
                                 txtLookUpBtnText.setText(lookupResult.trim());      
                             }
                           }
                           else  
                           { 
                                txtLookUpBtnText.setText("");
                           }     	
   	
   	
   }  
   
    private String calculateSubquery(String query)
    {
    	String sqlForPrintPreview="";
   	//final panelReportSettingsAndPreview panelReportSettingsAndPreview = new panelReportSettingsAndPreview(frame);

       // -------------------------filter -----------------------------------
        // take out order by, add later
          String queryOrderBy=""; 
       String queryNOB=""; // query no order by
         //System.out.println("PanelDataFilter.calculateSuquery "+query); 
          if(query.indexOf("ORDER BY")>0)
          {
             //char[] ch=query.toCharArray(); 
             queryOrderBy=query.substring(query.indexOf("ORDER BY"),query.length());
             queryNOB=query.substring(0,query.indexOf("ORDER BY"));
          }
          else
          {
          	  queryNOB=query;
          }
         //System.out.println("PanelDataFilter.calculateSuquery NOB "+queryNOB); 
       //    take out GROUP BY
       String queryGroupBy=""; 
       String queryNGB=""; // query no group by
          if(queryNOB.indexOf("GROUP BY")>0)
          {
             //char[] ch=queryNOB.toCharArray(); 
             queryGroupBy=queryNOB.substring(queryNOB.indexOf("GROUP BY"),queryNOB.length());
             queryNGB=queryNOB.substring(0,queryNOB.indexOf("GROUP BY"));
          }
          else
          {
          	  queryNGB=queryNOB;
          }
   
       // System.out.println("PanelDataFilter.calculate suquery NGB"+queryNGB);
       
       String sqlWhere="";
       
       //char[] ch=query.toCharArray();               
       //query=query.copyValueOf(ch,0,query.indexOf("GROUP BY"));
       // -------------------------------  sql where ----------------------------------
       if(!queryNGB.contains("WHERE"))
       {
          sqlWhere="WHERE";
       }
       else
       {
       	 sqlWhere="AND";
       }
       
       int txtWritten=0;
      if(entityFilterSettings!=null)
      {
        //System.out.println("----A PanelDataFilters.calculateSQL length "+entityFilterSettings.length);
       for (int s = 0 ; s<entityFilterSettings.length; s++)
       {   
       
         //System.out.println("----B PanelDataFilters.displayPrintPreviewDialog entityFilterSettings "+s+" "+entityFilterSettings[s].dbField);
         if(entityFilterSettings.length != fieldFilterTxts1.size())
         {
           System.out.println("error PanelDataFilter.calculateSubquery entityFilterSettings "+entityFilterSettings.length+" != "+fieldFilterTxts1.size());	
         	
         }
         
           JTextField txtSet1 = new JTextField();
           JTextBoxWithEditButtons textEditFormatedDate = new JTextBoxWithEditButtons();
//          textEditFormatedDate.setYearEnforce(yearEnforce);
          /*    if(entityFilterSettings[s].variableType.equalsIgnoreCase("date"))
              {
              	  
              	  textEditFormatedDate = (JTextBoxWithEditButtons)fieldFilterTxts1.get(s);
              }
              else
              {
                  txtSet1 = (JTextField)fieldFilterTxts1.get(s);
              }*/
              
           //String equi = entityFilterSettings[s].equivalence;
           //System.out.println("   PanelDataFilter.calculateSubquery "+s+" "+entityFilterSettings[s].equivalence+"   "+entityFilterSettings[s].dbField+"    "+entityFilterSettings[s].variableType+"      intReportGroup:"+intReportGroup);
           
          if( ! entityFilterSettings[s].equivalence.equalsIgnoreCase("fromto"))// || (entityFilterSettings[s].getForEntityReportGroup()==-1) )  // (entityFilterSettings[s].getForEntityReportGroup()==intReportGroup || entityFilterSettings[s].getForEntityReportGroup()==-1) )         
          {
              //System.out.println(" A  PanelDataFilter.calculateSubquery "+s+" "+entityFilterSettings[s].equivalence+"   "+entityFilterSettings[s].dbField+"    "+entityFilterSettings[s].variableType+"      intReportGroup:"+intReportGroup);
   	    	     JTextBoxWithEditButtons txtLookUpCheck = new JTextBoxWithEditButtons();
            if (entityFilterSettings[s].type.equalsIgnoreCase("checkboxTable") )
       	    {
       	    	txtLookUpCheck = (JTextBoxWithEditButtons)fieldFilterTxts1.get(s);
       	    	//System.out.println("PanelDataFilters.calculateSQL "+txtLookUpCheck.getText());
       	    	
       	    	entityFilterSettings[s].setValue(txtLookUpCheck.getText());// set value to be printed in first page of report
       	    	
       	    	String where ="";
       	    	if(!txtLookUpCheck.getText().equals(""))
       	    	{
       	            where = entityFilterSettings[s].dbForeignTable+"."+entityFilterSettings[s].dbField+" in ("+txtLookUpCheck.getText()+")";
       	              
       	              //sqlWhere = sqlWhere+" "+where;       	        
       	            
       	            if(txtWritten>=1)
       	            {
       	        	    sqlWhere=sqlWhere+" AND "+where;
       	            }
       	            else
       	            {
                        sqlWhere=sqlWhere+" "+where;
                    }
                     txtWritten++;
       	        }// if is not empty  
       	    }
            else if(entityFilterSettings[s].type.equalsIgnoreCase("onelookup"))
            {
                     txtSet1 = (JTextField)fieldFilterTxts1.get(s);	
                     //System.out.println("PanelDataFilters.calculateSubquery "+s+" "+txtSet1.getText());
                   if(!txtSet1.getText().equals(""))
                   {
           	
           	       String txt1 =txtSet1.getText();
           	
           	      entityFilterSettings[s].setValue(txt1);// set value to be printed in first page of report
                      
       	           String where = entityFilterSettings[s].dbForeignTable+"."+entityFilterSettings[s].dbField+" LIKE '"+txt1+"'";
       	           //System.out.println("PanelDataFilters.displayPrintPreviewDialog where "+where);
       	           if(txtWritten>=1)
       	            {
       	            	sqlWhere=sqlWhere+" AND "+where;
       	            }
       	            else
       	            {
                        sqlWhere=sqlWhere+" "+where;
                   }
                      txtWritten++;
                   }
                   else
                  {
           	          entityFilterSettings[s].setValue("");// clear 
                  }  
                  	
                  	//System.out.println("PanelDataFilter.calculateSubquery - "+);                   
            }
          	else 
          	{ //  else not checkbox
          
          	    
             //System.out.println("PanelDataFilters.calculateSQLForPrintPreview NOT fromto"+s+" from "+entityFilterSettings.length+" "+entityFilterSettings[s].dbField);
              if(entityFilterSettings[s].variableType.equalsIgnoreCase("date"))
              {
              	  
              	  textEditFormatedDate = (JTextBoxWithEditButtons)fieldFilterTxts1.get(s);
              }
              else
              {

                  txtSet1 = (JTextField)fieldFilterTxts1.get(s);
           
              }

             if(!txtSet1.getText().equals(""))
             {
           	
           	   String txt1 =txtSet1.getText();
           	
           	   entityFilterSettings[s].setValue(txt1);// set value to be printed in first page of report
            
               if(entityFilterSettings[s].variableType.equalsIgnoreCase("date") )
               {  	  
                  txt1 = utilsDate.reformatDateStringToSaveToDB(txt1);
               }  	  
           	  
       	       String where = entityFilterSettings[s].dbTable+"."+entityFilterSettings[s].dbField+" LIKE '"+txt1+"'";
       	       //System.out.println("PanelDataFilters.displayPrintPreviewDialog where "+where);
       	       if(txtWritten>=1)
       	       {
       	        	sqlWhere=sqlWhere+" AND "+where;
       	       }
       	       else
       	       {
                    sqlWhere=sqlWhere+" "+where;
               }
                txtWritten++;
              }
              else
              {
           	      entityFilterSettings[s].setValue("");// clear 
              }
            }// not checkbox
          } // not equivalence fromto 
          else if (entityFilterSettings[s].equivalence.equals("fromto"))// && (entityFilterSettings[s].getForEntityReportGroup()==-1)  )// && (entityFilterSettings[s].getForEntityReportGroup()==intReportGroup || entityFilterSettings[s].getForEntityReportGroup()==-1)  )
          {
                //System.out.println("   B  PanelDataFilter.calculateSubquery "+s+" "+entityFilterSettings[s].equivalence+"   "+entityFilterSettings[s].dbField+"    "+entityFilterSettings[s].variableType+"      intReportGroup:"+intReportGroup);
                   //JTextField txtSet1 = (JTextField)fieldFilterTxts1.get(s);
     	           //JTextField txtSet2 = (JTextField)fieldFilterTxts2.get(s);
     	     JTextBoxWithEditButtons txtLookUpBtn1 = new JTextBoxWithEditButtons();
     	     JTextBoxWithEditButtons txtLookUpBtn2 = new JTextBoxWithEditButtons();
     	     
              if(entityFilterSettings[s].variableType.equalsIgnoreCase("date"))
              {
              	  
              	  textEditFormatedDate = (JTextBoxWithEditButtons)fieldFilterTxts1.get(s);
              }
              else
              {
              	
       	        		if (entityFilterSettings[s].type.equalsIgnoreCase("lookup"))
       	        		{
              	                txtLookUpBtn1 = (JTextBoxWithEditButtons)fieldFilterTxts1.get(s);
       	        		}
       	        		else
       	        		{
       	        			
                               txtSet1 = (JTextField)fieldFilterTxts1.get(s);
       	        		}              	
              }
     	             
           JTextField txtSet2 = new JTextField();
           JTextBoxWithEditButtons textEditFormatedDate2 = new JTextBoxWithEditButtons();
 //          textEditFormatedDate2.setYearEnforce(yearEnforce);
              if(entityFilterSettings[s].variableType.equalsIgnoreCase("date"))
              {
              	  //System.out.println("PanelDataFilters.calculateSQL length date "+s);
              	  textEditFormatedDate2 = (JTextBoxWithEditButtons)fieldFilterTxts2.get(s);
              }
              else
              {
       	        	 if (entityFilterSettings[s].type.equalsIgnoreCase("lookup"))
       	             {
              	          txtLookUpBtn2 = (JTextBoxWithEditButtons)fieldFilterTxts2.get(s);  
                     }
                     else
                     {
                         txtSet2 = (JTextField)fieldFilterTxts2.get(s);   	
                     	
                     }
              	
                  
              }
               
  	           if(entityFilterSettings[s].variableType.equalsIgnoreCase("date") &&( !textEditFormatedDate.isDateEmpty() || !textEditFormatedDate2.isDateEmpty()))
               {                 
                 txtSet1.setText(textEditFormatedDate.getText());  // text from date textbox goes to textfield(  date textbox is a panel)
                 txtSet2.setText(textEditFormatedDate2.getText()); 
               }
               else if (entityFilterSettings[s].type.equalsIgnoreCase("lookup"))
               {
                 txtSet1.setText(txtLookUpBtn1.getText());  // text from date textbox goes to textfield(  date textbox is a panel)
                 txtSet2.setText(txtLookUpBtn2.getText()); 
               }
               else
               {
               	 // nothing for normal fromto
               }

      //System.out.println("PanelDataFilters.calculateSQL "+s+" "+txtSet1.getText()+"-"+txtSet2.getText());

           if(!txtSet1.getText().equals("") && !txtSet2.getText().equals("") )// both empty
           {
         	   
         	   String txt1 =txtSet1.getText();
         	   String txt2 =txtSet2.getText();
         	   
         	entityFilterSettings[s].setValue(txt1+"  -  "+txt2);//
         	   
   	           if(entityFilterSettings[s].variableType.equalsIgnoreCase("date") && !textEditFormatedDate.isDateEmpty() && !textEditFormatedDate2.isDateEmpty())
               {  	  
                   txt1 = utilsDate.reformatDateStringToSaveToDB(txt1);
                   txt2 = utilsDate.reformatDateStringToSaveToDB(txt2);
                   
               }

               String where = "";
               
                 if(entityFilterSettings[s].dbTable.equalsIgnoreCase(""))
                 {
                        where = entityFilterSettings[s].dbField+" >= '"+txt1+"'"+ " AND "+entityFilterSettings[s].dbField+" <= '"+txt2+"'";
  	         }
  	         else if(entityFilterSettings[s].type.equalsIgnoreCase("lookup"))
  	         {
  	         	where = entityFilterSettings[s].dbForeignTable+"."+entityFilterSettings[s].dbField+" >= '"+txt1+"'"+ " AND " +entityFilterSettings[s].dbForeignTable+"."+entityFilterSettings[s].dbField+" <= '"+txt2+"'";
  	         }
  	         else if (!entityFilterSettings[s].type.equalsIgnoreCase("lookup"))
  	         {               
                        where = entityFilterSettings[s].dbTable+"."+entityFilterSettings[s].dbField+" >= '"+txt1+"'"+ " AND " +entityFilterSettings[s].dbTable+"."+entityFilterSettings[s].dbField+" <= '"+txt2+"'";
  	         }
       	      //System.out.println("PanelDataFilters.displayPrintPreviewDialog "+s+where);
       	      if(txtWritten>=1)
       	      {
       	      	sqlWhere=sqlWhere+" AND "+where;
       	      }
       	      else
       	      {
                  sqlWhere=sqlWhere+" "+where;
              }
              txtWritten++;
           }
           else if (txtSet1.getText().equals("") && txtSet2.getText().equals("") )
           {
           	   entityFilterSettings[s].setValue("");// clear 
           }
           else // one of them is not empty
           {
         	   String txt1 =txtSet1.getText();
         	   String txt2 =txtSet2.getText();
         	   
         	entityFilterSettings[s].setValue(txt1+"  -  "+txt2);//
         	   
   	       if(entityFilterSettings[s].variableType.equalsIgnoreCase("date")&&( !textEditFormatedDate.isDateEmpty() || !textEditFormatedDate2.isDateEmpty()))
               {
                   txt1 = utilsDate.reformatDateStringToSaveToDB(txt1);
                   txt2 = utilsDate.reformatDateStringToSaveToDB(txt2);
                   
                   if(txt1==null)
                   {
                   	 txt1 ="";
                   }

                   if(txt2==null)
                   {
                   	 txt2 ="";
                   }
                   
               }

               String where="";

                if((txt1!=null || !txt1.equalsIgnoreCase("") ) && (txt2==null || txt2.equalsIgnoreCase("")))
                {
                        where = entityFilterSettings[s].dbTable+"."+entityFilterSettings[s].dbField+" >= '"+txt1+"'"; // it should be dbTable
  	        }
  	        else if ((txt1==null || txt1.equalsIgnoreCase("") ) && (txt2!=null || !txt2.equalsIgnoreCase("")))
  	        {
  	            	where = entityFilterSettings[s].dbTable+"."+entityFilterSettings[s].dbField+" <= '"+txt2+"'";   // it should be dbTable
  	        }  	
  	        else
  	        {
  	               System.out.println("PanelDataFilters.displayPrintPreviewDialog else NOT DEFINED "+txt1+" "+txt2);         		
  	        }
     if(VariablesGlobal.globalShowReadSQLRow)
     {  	            
  	         System.out.println("    ---------> PanelDataFilters.calculateSQL   s:"+s+"  '"+txtSet1.getText()+"'-'"+txtSet2.getText()+"'    table:"+entityFilterSettings[s].dbTable+"     dbForeignTable:"+entityFilterSettings[s].dbForeignTable+"    where:"+where);
     } 	      
       	      if(txtWritten>=1)
       	      {
       	      	sqlWhere=sqlWhere+" AND "+where;
       	      }
       	      else
       	      {
                  sqlWhere=sqlWhere+" "+where;
              }
              txtWritten++;
           }
          }  // fromto
          else
          {
              System.out.println("  C   ELSE  UNDEFINED  PanelDataFilter.calculateSubquery "+s+" ("+entityFilterSettings[s].equivalence+")   ("+entityFilterSettings[s].dbForeignTable+")  ("+entityFilterSettings[s].dbTable+")   ("+entityFilterSettings[s].dbField+")    ("+entityFilterSettings[s].variableType);//+")      intReportGroup:"+intReportGroup);
          }
       
          
         	   String txt1 =txtSet1.getText();
         	   //String txt2 =txtSet2.getText();          
                   // when field is dbCompanyId then replace the VarablesGlobal value with the selected. only for onelookup.                   
                      if(entityFilterSettings[s].dbField.equalsIgnoreCase("dbCompanyId"))
                      {
                          if(txt1.equalsIgnoreCase(""))
                          {
                              // use pnlDataFilter.getFilterSettingHasValue
                              //utilsGui.showMessageError(this,"Παρακαλώ επιλέξτε μια εταιρία.");
                              
                          }
                          else
                          {
                         String queryA = queryNGB.replaceAll("dbCompanyId = "+VariablesGlobal.globalCompanyId, "dbCompanyId = "+txt1);
                         String queryB = queryA.replaceAll("dbCompanyId LIKE "+VariablesGlobal.globalCompanyId, "dbCompanyId LIKE "+txt1);
                          // String queryReadOnlyA = compsubq.replaceAll("dbCompanyId = "+VariablesGlobal.globalCompanyId, "dbCompanyId = "+compId);
                          // String queryReadOnlyB = queryReadOnlyA.replaceAll("dbCompanyId LIKE "+VariablesGlobal.globalCompanyId, "dbCompanyId LIKE "+compId);                            
                          queryNGB = queryB;
                          System.out.println("PanelDataFilters.displayPrintPreviewDialog  if  queryNGB:"+queryNGB);
                          }
                      }
                           
          
          
       }// for
        
      }      
        //System.out.println("PanelDataFilters ++"+sqlWhere);
        
        
        
       
        
        
        
        
        
       if (txtWritten>0)
       {   
         int intIdexOfParenth = queryNGB.lastIndexOf(")", queryNGB.length());// last index searches backward
         String strIN = "in";
        int intIndexOfOpenParenthWithIN =  queryNGB.lastIndexOf(strIN.toUpperCase()+"(", queryNGB.length());
        int intIndexOfOpenParenthWithINandSpace =  queryNGB.lastIndexOf(strIN.toUpperCase()+" (", queryNGB.length());
         //System.out.println("PanelDataFilters intIdexOfParenth "+intIdexOfParenth+ "  for "+queryNGB);
       if(intIndexOfOpenParenthWithIN!=-1 || intIndexOfOpenParenthWithINandSpace!=-1)
       {
           sqlForPrintPreview= queryNGB+" "+sqlWhere;
       }
       else
       {  //when there is no IN() in query
          if(intIdexOfParenth > queryNGB.length()-10) // if query at the last 10 chars has ) -> it is a subquery
          {// in a sub query add sqlWhere before )
             String queryAfterParenth ="";
             if(!queryNGB.equalsIgnoreCase(""))
             {
                queryAfterParenth = queryNGB.substring(intIdexOfParenth, queryNGB.length());	
              	sqlForPrintPreview= queryNGB.substring(0,intIdexOfParenth)+" "+sqlWhere+queryAfterParenth;
     if(VariablesGlobal.globalShowReadSQLRow)
     {                
                System.out.println(" ======== A PanelDataFilters intIdexOfParenth "+intIdexOfParenth+ " queryNGB:"+queryNGB+"    sqlWhere:"+sqlWhere+"    -      sqlForPrintPreview:"+sqlForPrintPreview);
     }
             }
             else
             {
             	sqlForPrintPreview= sqlWhere+queryAfterParenth;
             }
     if(VariablesGlobal.globalShowReadSQLRow)
     {             
              System.out.println(" ======= B PanelDataFilters intIdexOfParenth "+intIdexOfParenth+ " queryNGB:"+queryNGB+"    sqlWhere:"+sqlWhere+"    -   sqlForPrintPreview:"+sqlForPrintPreview);
             //query = query.substring(0,intIdexOfParenth)+" "+sqlWhere+queryAfterParenth;
     
     }
          }
          else
          {
          	
          	sqlForPrintPreview=queryNGB+" "+sqlWhere;
     if(VariablesGlobal.globalShowReadSQLRow)
     {               
                System.out.println(" ===== C PanelDataFilters intIdexOfParenth "+intIdexOfParenth+ " queryNGB:"+queryNGB+"    sqlWhere:"+sqlWhere+"   -  sqlForPrintPreview:"+sqlForPrintPreview);
     }                
          }
       }
       }
       else
       {
       	  sqlForPrintPreview=queryNGB;
       }
       
       // add group by
     if(VariablesGlobal.globalShowReadSQLRow)
     {
       //System.out.println(" ==== PanelDataFilters  sqlForPrintPreview:"+sqlForPrintPreview);
      } 
       sqlForPrintPreview = sqlForPrintPreview+" "+queryGroupBy+" "+queryOrderBy;


        //System.out.println("PanelDataFilter.getSubquery    sqlForPrintPreview:"+sqlForPrintPreview);
    
    	return sqlForPrintPreview;
    }
    
 
    
    
    
     // called by PanelReportSettings, PanelOneDataManyRec, PanelTwoDataManyRec, PanelScoreBoard
    public String getSubquery(String queryIn)//, int intReportGroup)
    {	
       // System.out.println("PanelDataFilter.getSubquery    queryIn:"+queryIn);
    	return calculateSubquery(queryIn);//, intReportGroup);
    }

  /* public boolean filterTextBoxHasValue(int f)
   {
   	boolean ret =false;
   	   
   	  

      String text1="";
      JTextComponent ta1 =null;
      boolean isDateEmpty1 =true;
      if(fieldFilterTxts1.get(f) instanceof JTextComponent)
      {
      	//System.out.println("PanelDataFilter.JTextComponent");
         ta1 = (JTextComponent)fieldFilterTxts1.get(f);
         text1 = ta1.getText().trim(); 
         isDateEmpty1=false;
      }
      else if(fieldFilterTxts1.get(f) instanceof JTextBoxWithEditButtons)
      {
      	//System.out.println("PanelDataFilter.JTextBoxWithEditButtons");
      	JTextBoxWithEditButtons tbEb1 = (JTextBoxWithEditButtons)fieldFilterTxts1.get(f);
        ta1 = tbEb1.getTextComp();
        if(entityFilterSettings[f].variableType.equalsIgnoreCase("date"))
        {
              isDateEmpty1 = tbEb1.isDateEmpty();            
        }
        else
        {
              isDateEmpty1=false;
        }
        //System.out.println("PanelODORData.getListOfFieldsUncompleted isDateEmpty1="+isDateEmpty1+" for f"+f+" "+entityFilterSettings[f].caption);
      	text1 = ta1.getText().trim();   
      }
      else
      {
      	System.out.println("error PanelDataFilter.filterTextBoxHasValue for ta1 "+f+" "+fieldFilterTxts1.get(f));
      }


      if(text1!=null  &&  !text1.trim().equalsIgnoreCase("") && !isDateEmpty1)
      {
          ret = true;
      }
      else
      {
          ret = false;
      }
           
   System.out.println("PanelDataFilter.filterTextBoxHasValue    f:"+f+"   length:"+entityFilterSettings.length+"     text1:"+text1);
   	
   	return ret;
   }  */  
   
     public String getFilterVariableType(int intFilterSetting)
   {
         return entityFilterSettings[intFilterSetting].getVariableType();
   }  
    
   public String getFilterCaption(int intFilterSetting)
   {
         return entityFilterSettings[intFilterSetting].getCaption();
   }
    
   
     public String getFilterValueTwo(int intFilterSetting)
   {
       String text2 ="";
       
   	 if(entityFilterSettings!=null && fieldFilterTxts1!=null && fieldFilterTxts2!=null && (entityFilterSettings.length==fieldFilterTxts1.size() && entityFilterSettings.length==fieldFilterTxts2.size()))
   	 {

   //	    for(int f=0;f<entityFilterSettings.length;f++)
   //	    {
   	    	
                int fieldObligatoryOrSuggest = entityFilterSettings[intFilterSetting].getFieldObligatoryOrSuggest();  	 	   
                if(fieldFilterTxts2.size()>0)  
                {
       

      JTextComponent ta1 =null;
      if(fieldFilterTxts2.get(intFilterSetting) instanceof JTextComponent)
      {
      	//System.out.println("PanelDataFilter.JTextComponent");
         ta1 = (JTextComponent)fieldFilterTxts2.get(intFilterSetting);
         text2 = ta1.getText().trim(); 
       
      }
      else if(fieldFilterTxts2.get(intFilterSetting) instanceof JTextBoxWithEditButtons)
      {
      	//System.out.println("PanelDataFilter.JTextBoxWithEditButtons");
      	JTextBoxWithEditButtons tbEb1 = (JTextBoxWithEditButtons)fieldFilterTxts2.get(intFilterSetting);
        ta1 = tbEb1.getTextComp();

        //System.out.println("PanelODORData.getListOfFieldsUncompleted isDateEmpty1="+isDateEmpty1+" for f"+f+" "+entityFilterSettings[f].caption);
      	text2 = ta1.getText().trim();   
      }
      else
      {
      	System.out.println("error PanelDataFilter.getFilterValue for ta2     intFilterSetting: "+intFilterSetting+" "+fieldFilterTxts2.get(intFilterSetting));
      }
                }
    //        }
         }
       
       
       return text2;
   }  
    
    
   public String getFilterValue(int intFilterSetting)
   {
       String text1 ="";
       
   	 if(entityFilterSettings!=null && fieldFilterTxts1!=null && fieldFilterTxts2!=null && (entityFilterSettings.length==fieldFilterTxts1.size() && entityFilterSettings.length==fieldFilterTxts2.size()))
   	 {

   //	    for(int f=0;f<entityFilterSettings.length;f++)
   //	    {
   	   int f=intFilterSetting; 	
                int fieldObligatoryOrSuggest = entityFilterSettings[f].getFieldObligatoryOrSuggest();  	 	   
                if(fieldFilterTxts1.size()>0)  
                {
       

      JTextComponent ta1 =null;
      if(fieldFilterTxts1.get(f) instanceof JTextComponent)
      {
      	//System.out.println("PanelDataFilter.JTextComponent");
         ta1 = (JTextComponent)fieldFilterTxts1.get(f);
         text1 = ta1.getText().trim(); 
       
      }
      else if(fieldFilterTxts1.get(f) instanceof JTextBoxWithEditButtons)
      {
      	//System.out.println("PanelDataFilter.JTextBoxWithEditButtons");
      	JTextBoxWithEditButtons tbEb1 = (JTextBoxWithEditButtons)fieldFilterTxts1.get(f);
        ta1 = tbEb1.getTextComp();

        //System.out.println("PanelODORData.getListOfFieldsUncompleted isDateEmpty1="+isDateEmpty1+" for f"+f+" "+entityFilterSettings[f].caption);
      	text1 = ta1.getText().trim();   
      }
      else
      {
      	System.out.println("error PanelDataFilter.getFilterValue for ta1 "+f+" "+fieldFilterTxts1.get(f));
      }
                }
    //        }
         }
       
       
       return text1;
   }
    
    
   public boolean getFilterSettingHasValue(int intFilterSetting)
   {
   	boolean ret =false;
   	   
   	   //System.out.println("PanelDataFilter.getFilterSettingHasValue-"+entityFilterSettings.length);
   	   
   	   if(entityFilterSettings[intFilterSetting].getValue().equalsIgnoreCase(""))
   	   {
   	   	 ret = false;//not
   	   }
   	   else
   	   {
   	   	 ret = true;// has
   	   }
   	
   	return ret;
   }
   
  private void displayWindowCheckBox(String dialogTitle, String query, EntityFilterSettings[] entityFilterSettings ,
   JTextComponent textIn, int intValidationColumn, int intValidationType)
  {
  	//System.out.println("PanelDataFilters.displayCheckBox     query:"+query);
        //
       
        if (!winLookUpCheck.isVisible())
        {
        	 //System.out.println("isVisibleWinLookUpCheck ifisFalse "+winLookUpCheck.isVisible());

            
             winLookUpCheck=new WindowLookUpMultipleCheck(frame);
            
            //System.out.println("PanelDataFilter.displayWindowCheckBox "+entityFilterSettings.length);
            winLookUpCheck.setEntity(textIn,null,-1,query, entityFilterSettings ,dialogTitle ,WINDOW_LOCATION_COMPONENT,2, intValidationColumn+1, intValidationType,/*yearEnforce,*/panelManagement);  //if type = indices(1), if type text(2)     
        }
        else
        {
        	 winLookUpCheck.close();
        }
      
  }

   class  ActionShowWinLookUpCheck extends AbstractAction                 
   {       
      	String dialogTitle;
         String query;
         JTextComponent text;
         EntityFilterSettings[] entityFilterSettings;
         int intValidationColumn;
         int intValidationType;
        public ActionShowWinLookUpCheck(String dialogTitleIn,String queryIn, EntityFilterSettings[] entityFilterSettingsIn ,
         JTextComponent textIn, int intValidationColumnIn, int intValidationTypeIn)
        {
            dialogTitle=dialogTitleIn;
                 query=queryIn;
                 text=textIn;
                 entityFilterSettings=entityFilterSettingsIn;
                 intValidationColumn =intValidationColumnIn;
                 intValidationType =	intValidationTypeIn;
                 /* iForeignTable = foreignTable;
                  i=iIn;
                  j=jIn;  */     
             winLookUpCheck = new WindowLookUpMultipleCheck(frame);

        }
        
        public void actionPerformed(ActionEvent e)
      	{
                   //   System.out.println("ActionShowDialogLookUp ("+entityFilterSettings.length);
                   
                displayWindowCheckBox(dialogTitle,query,entityFilterSettings,text,intValidationColumn,intValidationType);
        }  

    }
   
    class  ActionShowDialogLookUp extends AbstractAction                 
   {       
         String value;
      	
         String iForeignTable;
         int i;
         int j;
         int noOfFilter;
         int filterFromPreviousSelectedField;
        public ActionShowDialogLookUp(String foreignTable, int iIn, int jIn, int noOfFilterIn, int filterFromPreviousSelectedFieldIn)
        {
                  iForeignTable = foreignTable;
                  i=iIn;
                  j=jIn;
                  noOfFilter=noOfFilterIn;       
                  filterFromPreviousSelectedField=filterFromPreviousSelectedFieldIn;
      	      // value = editorComp.getText();//value = (String)table.getValueAt(getCellEditedRow(), getCellEditedCol());

                //System.out.println("tableCellEditorLookUp.ActionShowDialogLookUp "+value+" "+entity+" "+getCellEditedRow()+" "+getCellEditedCol());            
        }
        
        public void actionPerformed(ActionEvent e)
      	{
                      //System.out.println("ActionShowDialogLookUp ("+iForeignTable+" , "+iF+")");
                displayDialogLookUp(iForeignTable,i,j,noOfFilter,filterFromPreviousSelectedField);
        }  
          // table.requestFocus(); 	
    }    

    
    /*
    *
    *  similar in PanelStatistics
    */
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
    
     /* if (entityFilterSettings[no].variableType.equalsIgnoreCase("date") )
      {
                  //System.out.println("PanelODORD.DocumentHandler insertUpdate DATE");
      }
      else // is not date
      {*/
         	if(foreignTable!=null)// look up
         	{   
         		if(txtIndex==0)
         		{
         			calculateTextForLookupsAfterKeyIsSet(no,additionalCol, foreignTable,filterFromPreviousSelectedField);
         		}
         		else if(txtIndex==1)
         		{
         		   //displayWindowLookUp(foreignTable,no);	
         		}
         		else
         		{
         	       //System.out.println("error DocumentHandler.insertUpdate"+" -> txtIndex"+txtIndex+" not defined, foreignTable "+foreignTable+" noIn"+no); 		
         		}      
         	}// if is date or checklookup

    //  } // is not date

    	
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
        //if (guiLoaded)
        //{ 
          //hasDataChanged=true;
          // JTextField t = new JTextField();

      /*if (entityFilterSettings[no].variableType.equalsIgnoreCase("date"))
      {
                  //System.out.println("PanelODORD.DocumentHandler removeUpdate DATE");
      }
      else // is not date
      {*/
         	
         	if(foreignTable!=null)// is look up
         	{   
         		if(txtIndex==0)
         		{
         			calculateTextForLookupsAfterKeyIsSet(no,additionalCol, foreignTable,filterFromPreviousSelectedField);
         		}
         		else if(txtIndex==1)
         		{
         		   //displayWindowLookUp(foreignTable,no);	
         		}
         		else
         		{
         	       //System.out.println("error DocumentHandler.insertUpdate"+" -> txtIndex"+txtIndex+" not defined, foreignTable "+foreignTable+" noIn"+no); 		
         		}      
         	} // else if is date or checklookup
     // }// is not date
                  
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