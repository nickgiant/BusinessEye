package com.tool.gui;


import com.tool.jdbc.*;

import com.tool.guicomps.*;
import com.tool.utils.*;
import com.tool.model.*;

import java.awt.*;

import java.util.Set;
import java.util.HashSet;

import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.KeyboardFocusManager;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

import javax.swing.*;
import java.util.*;

import javax.swing.event.*;

import java.sql.*;
import javax.swing.border.BevelBorder;

 public class PanelOneDataManyRec extends JxPanel implements Constants
 {	
 	    private JPanelDecorated panelAllOnIt;
        //private JButton btnViewMultiR ;
        //private JButton btnViewOneR;
        private JButton btnInsert;
        private JButton btnCopy;
        //private JButton btnInsertFromCopy;
        private JButton btnEdit;
        private JButton btnDelete;

       // private JxComboBox cmbView ;
       //private JButtonListMenu btnTools;
        //private JButtonListMenu btnTemplates;
        private JMenuItem mItemTemplatesSelect;
        private JMenuItem mItemTemplatesNew;   
  //      private JMenuItem mItemTemplatesSaveAs;
        private JMenuItem mItemTemplatesMgmt;     
        
        private JButtonListMenu btnView;
        //private JButton btnFind;
         private JButton btnCard;
        private JButton btnPrintPreview;
        private JButton btnExport;
        private JMenuItem mItemPrintPreview;
        //private JMenuItem mItemExport;
        /*private JMenuItem mItemHtml;
        private JMenuItem mItemPdf;
        private JMenuItem mItemExcel;*/
        private EntityExportFileType[] entityExportFileType;
        private JButton btnCopyFromOtherCompany;
        private JButton btnPreferences;
        /*private JLabel lblIcoSeparator1 =new JLabel();
        private JLabel lblIcoSeparator2 =new JLabel();
        private JLabel lblIcoSeparator3 =new JLabel();
        private JLabel lblIcoSeparator4 =new JLabel();*/
        
 	private BorderLayout layoutOneData;
    
    private PanelGradient paneTitle;
    
    private JLabel lblTitle;
  /*  private JLabel lblSearch1;
    private JLabel lblSearch2;
    private JLabel lblSearch3;
    private JLabel lblSearch4;
    private JTextField txtSearch1;
    private JTextField txtSearch2;
    private JTextField txtSearch3;
    private JTextField txtSearch4;*/
    private JButton btnSearch;
    private JButton btnSet;// hidden button to set lookup
    //private String[] searchField;
    private ToolBarData toolBarData;
//    private ToolBarView toolBarDataView;
    private JPanelDecorated panelTop;
    private JxPanel panelSearch;
    private PanelOneDataManyRecData panelOneDataManyRecData;
    //private DialogEditRec dialogEditRec;
    private PanelEditOneDataRec panelEditOneDataRec;
    //private DialogMain dialogMain;
    //private DialogMulti dlg;
//    private DialogReport dlgReport;
    private String entity;
    //private String caption;
    private String query;
    private String queryReadOnly;
    private String queryReadAfterFilter;
    private String primKeyDb;
    private String title;
    /*private String[]fields;
    private String[]fieldsTranslation;
    private String[]fieldsMany;
    private String[]fieldsManyTranslation;*/
    private ImageIcon ico;
    private String strOfMany;
    private String strOfOne;
    
    private JPopupMenu popupMenuTools = new JPopupMenu();
    private JPopupMenu popupMenuPrint = new JPopupMenu(); // the only from toolbar
    private JPopupMenu popupMenuView = new JPopupMenu(); // the only from toolbar
    //private JPopupMenu popupMenuTemplates = new JPopupMenu(); // the only from toolbar
    
    private boolean isLookUpDialog = false;
    private String[] categoryNodes;
    private EntityPanel[] entityPanel;
    private String[] fieldsOnTitle;
    private String[] fieldsOnTitleCaption;
    private final JLabel lblGuide = new JLabel("οδηγός");
    
    private PanelDataFilter panelDataFilter;
   private EntityFilterSettings[] entityFilterSettings;
   private EntityGroupOfComps[] entityGroupOfComps;
    private JFrame frame;
    //private String strView="προβολή";
    //private String yearEnforce;
    private PanelManagement panelManagement;
//    private EntityUpdateAdditional[] updateAdditional;
    private UtilsPanelReport utilsPanelReport;
    private UtilsString utilsString;
     
           private ResultSet rs;
    private Database db; 
    private UtilsGui utilsGui;
    private final int  YES =0;
    private final int NO = 1;
    private final int CANCEL = 3;     
    private String[] fieldsForSums;
    private UtilsDate utilsDate;
    //private EntityReport entityReportForm;
   // private String formGlobalTableToGet1;
   // private String formGlobalTableToApply1;
    //private String formGlobalField1;
    //private String formGlobalVariable1;
    private boolean showDialogOnError=true;
    private ArrayList fieldTxts;
    private boolean isNewRec =false;
    
    public PanelOneDataManyRec(JFrame frame)
    {
            try
           {    initialize(frame);    }
           catch (Exception e)
           {   e.printStackTrace();    }
    }

	private void initialize(JFrame frameIn) throws Exception
    {   
    	panelAllOnIt = new JPanelDecorated();
    	setOpaque(false);
      frame=frameIn;
      utilsPanelReport = new UtilsPanelReport();
      //dialogEditOneDataOneRec = new DialogEditOneDataOneRec();//here loads faster
       panelDataFilter = new PanelDataFilter(frameIn);
       utilsString = new UtilsString();
       utilsGui = new UtilsGui();
       
       db = new Database();
       utilsDate = new UtilsDate();
       
       btnSet = new JButton("set"); // hidden btn
	             
       layoutOneData = new BorderLayout();
       this.setLayout(layoutOneData);
       lblTitle = new JLabel();
       lblTitle.setOpaque(true);
       lblTitle.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

      //lblTitle.setBackground(this.getBackground().darker().darker().darker());//java.awt.SystemColor.activeCaption);
  //    lblTitle.setBackground(SystemColor.activeCaption);//panel.getBackground().brighter());              
  //    lblTitle.setForeground(this.getBackground().brighter().brighter());

        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD));
    //   lblTitle.setBackground(SystemColor.activeCaption);//panel.getBackground().brighter());        
        //lblTitle.setFont(new java.awt.Font("Century", 1, 16));
        lblTitle.setForeground(Color.white);
        lblTitle.setFocusable(false);
        lblTitle.setOpaque(false);
         //SystemColor.activeCaption
       
       lblGuide.setIcon(ICO_INFO16);
       lblGuide.setForeground(Color.white);
       lblGuide.setToolTipText("οδηγός");
        //lblInfo.setBackground(Color.DARK_GRAY);
        lblGuide.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        lblGuide.addMouseListener(new MouseAdapter()
                {       	      
                 public void mouseEntered(MouseEvent e)
                 {
                 	lblGuide.setBorder(BorderFactory.createLineBorder(Color.white));
                 	//lblHelp.setCursor(new Cursor(Cursor.HAND_CURSOR));
                 }
                 
                 public void mouseExited(MouseEvent e)
                 {
                    lblGuide.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));                 
                    //lblHelp.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                 }                 
                }); 
     
        paneTitle = new PanelGradient(new Color(0, 0, 0, 0),this.getBackground().darker().darker().darker().darker(),26);//new PanelTitle(new Color(0, 0, 0, 0),this.getBackground().darker().darker().darker().darker(),Color.white,"title");
        paneTitle.setLayout(new BoxLayout(paneTitle, BoxLayout.LINE_AXIS));
        paneTitle.add(lblTitle);
        paneTitle.add(Box.createHorizontalGlue());
        paneTitle.add(lblGuide);
       
/*       toolBarDataView = new ToolBarView();
       toolBarDataView.setFocusable(false);
       toolBarDataView.setFloatable(false);
 */   
        panelTop = new JPanelDecorated();
     
       toolBarData = new ToolBarData();
       toolBarData.setFocusable(false);
//       toolBarData.setFloatable(false);
       //panelTop = new CurvedGradientPanel();
       panelTop.setLayout(new BorderLayout());
       
       panelSearch = new JxPanel();     
       panelSearch.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
       panelSearch.setAlignmentY(10);
       //panelSearch.setPreferredSize(new Dimension(getWidth(), 22));

        panelEditOneDataRec = new PanelEditOneDataRec(frame);
//        panelEditOneDataRec.addActionListenerSaveToShowList(new PanelActionListenerSaveAndShowList()); // for btnShowlist of >1 panels
        panelEditOneDataRec.addActionListenerBackToList(new PanelActionListenerBackToListAskForSave()); // for btnShowlist of >1 panels
        
        panelEditOneDataRec.addActionListenerSaveToShowListToChildOneData(new PanelActionListenerSaveAndShowList());     // for btnShowlist of =1 panel  // PanelActionListenerShowListFromChild());
        panelEditOneDataRec.addActionListenerBackToListToChildOneData(new PanelActionListenerBackToList());  // for btnShowlist of =1 panel 
        
        btnSearch = new JButton();
        btnSearch.setText("<html><b>Α</b>ναζήτηση</html>");
        btnSearch.setFocusable(false) ;
        btnSearch.setMnemonic(KeyEvent.VK_A);          
        btnSearch.setIcon(ICO_FIND16);       
        btnSearch.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	  
                      if (panelDataFilter.checkIfFieldsAreCompleted())
                      {
                             filter();  // like refresh  
                             isNewRec = false; // is needed here because after inserted one rec and selected other, it used to reselect the new
                      }
                
                } 
	    });
       
        panelSearch.add(panelDataFilter.getPanelFilters());
        panelSearch.add(btnSearch);

 //       JPanel panelToolBars = new JPanel();
//        panelToolBars.setLayout(new FlowLayout());//(new BoxLayout(panelToolBars, BoxLayout.PAGE_AXIS));
//        panelToolBars.add(toolBarDataView );
//        panelToolBars.add(toolBarData);
        panelTop.add(toolBarData,BorderLayout.PAGE_START);//panelToolBars,BorderLayout.PAGE_START);
        panelTop.add(panelSearch,BorderLayout.PAGE_END);   

        panelOneDataManyRecData = new PanelOneDataManyRecData(frame);
        panelOneDataManyRecData.setLayout(new BorderLayout());  // to fit panel to scrollpanel
        
        // add listener for 2 click in table
        panelOneDataManyRecData.addTableMouseListener(new TableMouseListener());        

        if (VariablesGlobal.globalShowInitialisations)
        {   System.out.println("panelODMR initialized");   }
        
    }
        
        private void closeDB()
        {
            db.releaseConnectionRs();
            db.releaseConnectionRsmd();
        }
        

        /*
         * 
         * called by PanelManagement 
         * 
         */
     public void setEntityForEntityInfo(EntityInfo entIn, String queryIn,EntityMenu entityMenuIn,Boolean isLookUpDialogIn,PanelManagement panelManagementIn)//,
            // EntityReport entityReportFormIn)
     {
 
        
        //System.out.println("  PanelODMRec.setEntityForEntityInfo        -o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-oo- ");
         
         
       setEntity(entIn.getName(), entIn.getSqlReadOnly(), queryIn,entIn.getFieldsForSums(), entIn.getCaption(),
            entIn.getPrimKey(),entIn.getPrimKeyDb()/*,entIn.getFormGlobalTableToGet1(), entIn.getFormGlobalTableToApply1()*/,entityMenuIn.getEntityIcon(), entIn.getEntityFilterSettings(),entIn.getEntityGroupOfComps(),entIn.getStrOfOne(), 
            entIn.getStrOfMany(),false, entIn.getIntValidationColumn(), entIn.getIntValidationType(), entIn.getCategoryNodes(),entIn.getEntityPanel(),entIn.getFieldsOnTitle(),
            entIn.getFieldsOnTitleCaption(),entIn.getFieldsOrderby(), /*entIn.getYearEnforce(),*/ fieldTxts,panelManagementIn);//,entityReportFormIn);             
        
        
     }   

     
        /*
         * 
         * called by PanelManagement 
         * 
         */
     public void setEntityForEntityParameters(EntityParameter entIn, String queryIn,EntityMenu entityMenuIn,Boolean isLookUpDialogIn,PanelManagement panelManagementIn)//,
             //EntityReport entityReportFormIn)
     {

        
        
       setEntity(entIn.getName(), entIn.getSqlReadOnly(), queryIn,entIn.getFieldsForSums(), entIn.getCaption(),
            entIn.getPrimKey(),entIn.getPrimKeyDb(),/*entIn.getFormGlobalTableToGet1(),entIn.getFormGlobalTableToApply1(),*/entityMenuIn.getEntityIcon(), entIn.getEntityFilterSettings(),entIn.getEntityGroupOfComps(),entIn.getStrOfOne(), 
            entIn.getStrOfMany(),false, entIn.getIntValidationColumn(), entIn.getIntValidationType(), entIn.getCategoryNodes(),entIn.getEntityPanel(),entIn.getFieldsOnTitle(),
            entIn.getFieldsOnTitleCaption(),entIn.getFieldsOrderby(), /*entIn.getYearEnforce(),*/ fieldTxts,panelManagementIn);//,entityReportFormIn);             
        
        
     }        
     
     
        /*
         * 
         * called by DialogLookUp.setLookUpEntityAndQuery + above
         * 
         */     
    public void setEntity(String entityIn,String queryReadOnlyIn, String queryIn,String[] fieldsForSumsIn,String titleIn,String primKey,String primKeyDbIn,/*String formGlobalTable1ToGetIn, 
     String formGlobalTableToApply1In, */ImageIcon icoIn, EntityFilterSettings[] entityFilterSettingsIn, EntityGroupOfComps[] entityGroupOfCompsIn,String strOfOneIn,
     String strOfManyIn, Boolean isLookUpDialogIn,int intValidationColumnIn, int intValidationTypeIn,String[] categoryNodesIn, EntityPanel[] entityPanelIn, String[]fieldsOnTitleIn,
     String[] fieldsOnTitleCaptionIn, int[] fieldsOrderby,/*,String yearEnforceIn,*/ ArrayList fieldTxtsIn, PanelManagement panelManagementIn)//,EntityReport entityReportFormIn)
    {

        //String primKey = entIn.getPrimKey();
        //int[] fieldsOrderby = entIn.getFieldsOrderby();
        //int intValidationColumn = entIn.getIntValidationColumn();
        //int intValidationType = entIn.getIntValidationType();
        entity=entityIn; //use it to send info to edit dialog
    	primKeyDb=primKeyDbIn;//entIn.getPrimKeyDb();
        //formGlobalTableToGet1 = formGlobalTable1ToGetIn;
        //formGlobalTableToApply1=formGlobalTableToApply1In;
        //formGlobalField1 = formGlobalField1In;
        //formGlobalVariable1 = formGlobalVariable1In; 
    	queryReadOnly=queryReadOnlyIn;//entIn.getSqlReadOnly();
        queryReadAfterFilter = queryReadOnly;
    	query=queryIn;
    	title=titleIn;
    	ico=icoIn;//entityMenuIn.getEntityIcon();
        strOfMany=strOfManyIn;//entIn.getStrOfMany();
        strOfOne=strOfOneIn;//entIn.getStrOfOne();
        isLookUpDialog=isLookUpDialogIn;
        categoryNodes = categoryNodesIn;//entIn.getCategoryNodes();
        entityPanel = entityPanelIn;//entIn.getEntityPanel();//later get dbfields from here
        fieldsOnTitle=fieldsOnTitleIn;//entIn.getFieldsOnTitle();
        fieldsOnTitleCaption=fieldsOnTitleCaptionIn;//entIn.getFieldsOnTitleCaption();
        //lblTitle.setBackground(java.awt.SystemColor.activeCaption);
        entityFilterSettings=entityFilterSettingsIn;//entIn.getEntityFilterSettings();
        entityGroupOfComps=entityGroupOfCompsIn;//entIn.getEntityGroupOfComps();
        //System.out.println("panelODMR.setEntity entityFilterSettings "+entityFilterSettings);
        //yearEnforce = yearEnforceIn;//entIn.getYearEnforce();
        panelManagement = panelManagementIn;
        fieldsForSums=fieldsForSumsIn;//entIn.getFieldsForSums();  
        fieldTxts  = fieldTxtsIn;
        //entityReportForm = entityPanel;
        
            if (this != null)
            {  this.removeAll(); }
    	
    	//caption=CaptionIn;

       
     // System.out.println("PanelODMR.setEntity   +OooooooooooooooooooOOOooooooooooooooooooO+   entity:"+entity+"."+primKey+" "+primKeyDb+"       entityPanel.length:"+entityPanel.length+"            fieldsForSums:"+fieldsForSums);
      //for(int p = 0;p<entityPanel.length;p++)
      //{
      //    System.out.println("panelOneDataManyRec.setEntity ooo    "+p+"      title:"+entityPanel[p].getTitle()+"         ReportForm:"+entityPanel[p].getEntityReportForm() +"   form report bands:"+entityPanel[p].getEntityReportForm().getEntityReportBands()[0]+"   entityPanel dbFields:"+entityPanel[p].getDBFields());
      //}
      
      
      
      
      
       //System.out.println("  panelODMR.setEntity query "+query+"   queryReadOnly:"+queryReadOnly);
       lblGuide.setToolTipText("<html><p>"+title+"</p> <p>"+entity+"</p> <p>"+strOfMany+"</p></html>");
       if (isLookUpDialog)
       {
       	 paneTitle.setVisible(false);
       	 btnPreferences.setVisible(false);
       	 btnPrintPreview.setVisible(false);
         btnCopy.setVisible(false);
         btnCopyFromOtherCompany.setVisible(false);
       	 //btnExport.setVisible(true);
         
       	// lblIcoSeparator3.setVisible(false);
       }

        lblTitle.setIcon(ico);      
       lblTitle.setText(title);

      panelDataFilter.setEntity(entityFilterSettings,entityGroupOfComps,PANEL_FILTER_SEARCH,/*yearEnforce,*/panelManagement);//,updateAdditional);
      
      if(entityFilterSettings!=null && entityFilterSettings.length>0)
      {
         panelSearch.setVisible(true);	
      }
      else
      {
      	  panelSearch.setVisible(false);	
      }
      
      
        // try to have icon up center,  description down center
        //btnInsert.setVerticalTextPosition(AbstractButton.CENTER);
        //btnInsert.setHorizontalTextPosition(AbstractButton.LEADING); 
       final String mnuText1 ="όλοι οι "+title+"";  
       final JMenuItem mView1 = new JMenuItem(mnuText1) ;    
      popupMenuView.add(mView1);
        mView1.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   selectMenuView(mView1,mnuText1);  }
	    });
      popupMenuView.add(new JMenuItem("όσοι "+title+" κινήθηκαν"));
      //popupMenuView.add( new JMenuItem("όσοι "+title+" κινήθηκαν σε ("+VariablesGlobal.globalCompanyId+")"+VariablesGlobal.globalCompanyName+" σε όλα τα έτη"));
      //popupMenuView.add( new JMenuItem("όσοι "+title+" κινήθηκαν το "+VariablesGlobal.globalYear+" σε όλες τις εταιρίες"));
      //popupMenuView.add( new JMenuItem("όσοι "+title+" κινήθηκαν σε ("+VariablesGlobal.globalCompanyId+")"+VariablesGlobal.globalCompanyName+" το "+VariablesGlobal.globalYear));
      //popupMenuView.add( new JMenuItem("όσοι "+title+" κινήθηκαν σε ("+VariablesGlobal.globalCompanyId+")"+VariablesGlobal.globalCompanyName+" το "+VariablesGlobal.globalYear+" στην αποστολή "+VariablesGlobal.globalDeliveryId));
      popupMenuView.add( new JMenuItem("όσοι "+title+" δεν κινήθηκαν"));
      //popupMenuView.add( new JMenuItem("όσοι "+title+" δεν κινήθηκαν σε ("+VariablesGlobal.globalCompanyId+")"+VariablesGlobal.globalCompanyName+" σε όλα τα έτη"));
      //popupMenuView.add( new JMenuItem("όσοι "+title+" δεν κινήθηκαν το "+VariablesGlobal.globalYear+" σε όλες τις εταιρίες"));
      //popupMenuView.add( new JMenuItem("όσοι "+title+" δεν κινήθηκαν σε ("+VariablesGlobal.globalCompanyId+")"+VariablesGlobal.globalCompanyName+" το "+VariablesGlobal.globalYear));
      //popupMenuView.add( new JMenuItem("όσοι "+title+" δεν κινήθηκαν σε ("+VariablesGlobal.globalCompanyId+")"+VariablesGlobal.globalCompanyName+" το "+VariablesGlobal.globalYear+" στην αποστολή "+VariablesGlobal.globalDeliveryId));
       
       selectMenuView(mView1, mnuText1);
      //popupMenuView.setSelected();                          

      /*btnView.addItem("όσοι δεν κινήθηκαν σε "+VariablesGlobal.globalCompanyName+" σε όλα τα έτη");
      btnView.addItem("όσοι δεν κινήθηκαν το "+VariablesGlobal.globalYear+" σε όλες τις εταιρίες");    
      btnView.addItem("όσοι δεν κινήθηκαν σε "+VariablesGlobal.globalCompanyName+" το "+VariablesGlobal.globalYear);
      btnView.setSelectedIndex(0);
      
        Dimension d = cmbView.getPreferredSize();
        cmbView.setPreferredSize(new Dimension(70, d.height));
        cmbView.setPopupWidth(d.width);*/

       btnInsert.setToolTipText("εισαγωγή "+strOfOne);
       btnCopy.setToolTipText("αντιγραφή "+strOfOne);
      // btnInsertFromCopy.setToolTipText("εισαγωγή "+strOfOne+" με αντιγραφή επιλογής");
       btnEdit.setToolTipText("επεξεργασία "+strOfOne);
       btnDelete.setToolTipText("διαγραφή "+strOfOne);
       
       btnPrintPreview.setToolTipText("εκτύπωση "+strOfMany);
       btnExport.setToolTipText("εξαγωγή "+strOfMany+" σε αρχείο");
       btnPreferences.setToolTipText("προτιμήσεις πίνακα "+strOfMany);

       // ---    if has dbCompanyId show btnCopyFromOtherCompany
       EntityDBFields[] eDBFields = entityPanelIn[0].getDBFields();
       boolean hasDbCompanyId=false;
       if(eDBFields.length>0)
       {
       for(int e=0;e<eDBFields.length;e++)
       {
           if(eDBFields[e]!=null && eDBFields[e].getDbField().equalsIgnoreCase(STRFIELD_DBCOMPANYID))
           {
               hasDbCompanyId = true;
               break;
           }
       }
       }   
       
       if(hasDbCompanyId)
       { 
           if (isLookUpDialog)
           {
               btnCopyFromOtherCompany.setVisible(false);
           }
           else
           {
              btnCopyFromOtherCompany.setVisible(true);
           }
       }
       else
       {
           btnCopyFromOtherCompany.setVisible(false);
       }
       
       
       int intTableOfParentDBFields = -1;
       
       panelOneDataManyRecData.setEntity(entityIn,titleIn,queryReadOnlyIn,fieldsForSumsIn,entityPanelIn[0].getDBFields(),entityPanelIn[0].getDBFieldsMany(),null,/*null,null,*/primKey,
        primKeyDbIn,/*formGlobalVariable1,*//*null,null,null,null,*/null, false, /*false,*/false, strOfManyIn,false,entity,false, 0,"","","","",0,"", fieldsOrderby, -1,null,intValidationColumnIn, 
        intValidationTypeIn, entityFilterSettingsIn,entityGroupOfCompsIn,/*yearEnforceIn,*/fieldTxts, panelManagementIn,intTableOfParentDBFields/*,formGlobalTableToGet1,formGlobalTableToApply1*/);// String yearEnforceInLinesIn)
       //panelOneDataManyRecData.setEntity(entity,queryReadOnly,fieldsManyIn,fieldsManyTranslationIn,null,null,primKey,primKeyDb,null,null,null,null,null, false, false,false, strOfMany,false,false, 0,"","","","",0);//entity, query, showExtendedSummary
       
      
          //         EntityDBFields[]  dbFieldsInGroupOfPanels = entityPanel[0].getDBFields();
          //         String queryChild = dbFieldsInGroupOfPanels[i].getSqlTableChildRead();
          //         String tableChild = dbFieldsInGroupOfPanels[i].getChildTable();
         //          EntityDBFields[] dbFieldsChild = dbFieldsInGroupOfPanels[i].getDbChildFields();         
       
       //panelOneDataManyRecData.setEntity(dbFieldsInGroupOfPanels[i].getCaption(),sql,tableChild,true,true,dbFieldsChild,primKeyDb,primKeyValue);//,updateAdditional);
       //panelOneDataManyRecData.setEntity(title,queryReadOnly,entity,false,false,entityPanelIn[0].getDBFieldsMany(),primKeyDb,null);
       
       
       panelOneDataManyRecData.setVisible(true);
       panelEditOneDataRec.setVisible(false);
       panelTop.setVisible(true);


       JxPanel panelContainer = new JxPanel();
       panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.PAGE_AXIS));

       panelContainer.add(panelOneDataManyRecData);
       panelContainer.add(panelEditOneDataRec);

       panelAllOnIt = new JPanelDecorated();
       panelAllOnIt.setLayout(new BorderLayout());

       panelAllOnIt.add(panelTop, BorderLayout.NORTH);
       panelAllOnIt.add(panelContainer, BorderLayout.CENTER );  
       	
       	add(panelAllOnIt, BorderLayout.CENTER );  
        
        if (panelDataFilter.checkIfFieldsAreCompleted())
        {
            filter();  // like refresh    
        }        

       panelOneDataManyRecData.setScrollPaneSize(0);// 0 is not set
        
        
        
    }
    
    
   /*
   * 
   */
   private void displayDialogCopyFromOtherCompany()
   {
   
//    private String strOfMany;    private String strOfOne;      
       UtilsDouble utilsDouble;
       utilsDouble=new UtilsDouble();
       utilsDouble.getSettingsFromDb();
     
      
        EntityFilterSettings[] actionTypeCompanyErs = new EntityFilterSettings[1];       
       // actionTypeCompanyErs[0]=new EntityFilterSettings("ονομασία","","string","equals","actionTypeDescription","actiontype",null,"",-1,-1,-1,FIELD_NOCOMPLETION);       
       if(strOfMany == null)
       {
           strOfMany="";
       }
       actionTypeCompanyErs[0]=new EntityFilterSettings("επιλογή "+strOfMany+" από εταιρία","onelookup","string","","dbCompanyId","dbcompany",entity/*"dbcompany"*/,"",0,-1,-1,FIELD_OBLIGATORY);
       
                PanelDataFilter pnlDataFilter = new PanelDataFilter(frame);
                pnlDataFilter.setEntity(actionTypeCompanyErs, null,/*entityCalculate.getEntityGroupOfComps()*/ PANEL_FILTER_SEARCH, /*entityCalculate.getYearEnforce(),*/ panelManagement);
                 
     
          JTableDec  table = new JTableDec();
       JScrollPane tableScrollPane = new JScrollPane();	
       tableScrollPane.setViewportView(table);
     
        TableCellRendererDefault tcr = new TableCellRendererDefault();
        TableCellRendererDouble tcrDouble = new TableCellRendererDouble(utilsDouble);
        TableCellRendererInteger tcrInteger = new TableCellRendererInteger();
        TableCellRendererDate tcrDate = new TableCellRendererDate();
        TableCellRendererBoolean tcrBoolean = new TableCellRendererBoolean();
        
        tcrDate.pushUtilsDateToReadFromFile();
        //TableCellRendererCheckBox tcrBoolean = new TableCellRendererCheckBox();

        // see also PrintTable
        table.setDefaultRenderer(Object.class, tcr);
        table.setDefaultRenderer(Integer.class, tcrInteger);
        table.setDefaultRenderer(Number.class, tcrInteger);
        table.setDefaultRenderer(Double.class, tcrDouble); 
        table.setDefaultRenderer(java.util.Date.class, tcrDate);
        table.setDefaultRenderer(Boolean.class,  tcrBoolean);        	
        //table.setDefaultRenderer(Boolean.class, tcrBoolean);
        
        //table.getColumn(0).setCellRenderer(tcrBoolean);
        //table.getColumn(0).setCellEditor(rowEditor);
        
        table.setShowVerticalLines(true);        
        table.setShowHorizontalLines(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setGridColor(CLR_TABLE_GRID);
        //table.setGridColor(table.getTableHeader().getBackground());

        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//.SINGLE_SELECTION);// select a single row only  
          	
    	TableModelResultSetCheckBoxes tableModel= new TableModelResultSetCheckBoxes();

        table.setModel(tableModel);

    	
    	
     
    Vector dataVector = new Vector();
    String[] headers = null;


       
            UtilsTable utilsTable=new UtilsTable();
             tableScrollPane.setPreferredSize(new Dimension(800,360));
          
       tableScrollPane.revalidate();    

          
     

         btnSearch = new JButton();
        
          
       JButton btnShowFromCompany = new JButton();
       btnShowFromCompany.setText("<html><b>Α</b>ναζήτηση</html>");
       btnShowFromCompany.setFocusable(false) ;
        btnShowFromCompany.setMnemonic(KeyEvent.VK_A);          
        btnShowFromCompany.setIcon(ICO_FIND16);  
        
        btnShowFromCompany.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {     
                    
                    if(pnlDataFilter.checkIfFieldsAreCompleted())
                    {
                    
                    //if(pnlDataFilter.getFilterSettingHasValue(0))
                    //{
                    String compsubq = pnlDataFilter.getSubquery(queryReadOnly);
                    //String compId = 
                   // System.out.println("PanelODMR  compId:"+compId+"        getValue:"+actionTypeCompanyErs[0].getValue());
                   // String queryReadOnlyA = compsubq.replaceAll("dbCompanyId = "+VariablesGlobal.globalCompanyId, "dbCompanyId = "+compId);
                   // String queryReadOnlyB = queryReadOnlyA.replaceAll("dbCompanyId LIKE "+VariablesGlobal.globalCompanyId, "dbCompanyId LIKE "+compId);
                    //System.out.println("PanelODMR  compsubq:"+compsubq);
                    tableModel.setQuery(compsubq); 
                    //dbCompanyId     
                    
        UtilsTable utilsTable=new UtilsTable();
        int totalWidthOfColumns =3;
        for (int c=0; c<table.getColumnCount(); c++)
        {   // table,column, margin
            totalWidthOfColumns=totalWidthOfColumns+utilsTable.packColumn(table, c, 2,true,false,null);
        }
        //System.out.println("----"+totalWidthOfColumns);

        //tableScrollPane.setPreferredSize(utilsTable.setTableScrollPaneSize(table,580,380));
         
        tableScrollPane.setPreferredSize(utilsTable.setTableScrollPaneSize(table,totalWidthOfColumns,280));
       
       tableScrollPane.revalidate();                    
                    
                    }
                    else
                    {
                        
                    }
                    
                }
       }
       );
       
       

       
       
     
                
                
                JxPanel paneFilter = new JxPanel(new FlowLayout());
                paneFilter.add(pnlDataFilter);
                paneFilter.add(btnShowFromCompany);
                
                JxPanel panelMainCopy = new JxPanel();
                BorderLayout brd = new BorderLayout();
                        panelMainCopy.setLayout(brd);
                        panelMainCopy.add(paneFilter,BorderLayout.PAGE_START);
                        panelMainCopy.add(tableScrollPane,BorderLayout.CENTER);
                        //panelMainCopy.add(new JLabel(queryReadOnly));
                        //panelMainCopy.add(new JLabel(query));
                //

               DialogMulti dlgCopy = new DialogMulti(frame);
               dlgCopy.setEntity(panelMainCopy,PANEL_TYPE_COPY_FROM_OTHERCOMPANY,title+": αντιγραφή από άλλη εταιρία",true);
                //dlgDocFilter.setPanelFilters(pnlDataFilter);
                dlgCopy.display();
                boolean checkIsCanceled = dlgCopy.getIsCancelClicked();// look for type  like PANEL_TYPE_COPY_FROM_OTHERCOMPANY in DialogMulty.closeDialog
                
                if(!checkIsCanceled)
                {
                    String fromCompPkValue =  pnlDataFilter.getFilterValue(0);
                    int countRows = tableModel.getRowCount();
                   // boolean[] isCopied = new boolean[countRows];
                   // String pkRowValues = "";
                   
                    
        Database dbTransaction = new Database();                
        try
        {
          dbTransaction.transactionLoadConnection();
          dbTransaction.setTransactionAutoCommit(false);
         System.out.println("PanelODMR.displayDialogCopyFromOtherCompany          dbTransaction:"+dbTransaction); 
                            
                    
                    
                    for(int r = 0; r<countRows; r++)
                    {
                        boolean  isToCopy = Boolean.parseBoolean(tableModel.getValueAt(r, 0).toString());
                        if(isToCopy)
                        {
                          String pkOfRowValue = tableModel.getValueAt(r, 1).toString();
                            
                          this.copyFromCompanyRow( entityPanel,fromCompPkValue,pkOfRowValue,dbTransaction,r);
                          
                          /*isCopied[r] = this.copyFromCompanyRow( entityPanel,fromCompPkValue,pkOfRowValue,dbTransaction);
                            if(isCopied[r])
                            {
                              pkRowValues = pkRowValues+" "+pkOfRowValue+",";
                            }*/
                           
                        }
                        
                    }
                      
                    /*String strLinesCopied ="";
                    for(int i=0;i<isCopied.length;i++)
                    {
                        if(isCopied[i])
                        {
                            strLinesCopied =strLinesCopied+" "+i+",";
                        }
                    }*/
               
        
        System.out.println("PanelODMR.displayDialogCopyFromOtherCompany     dbTransaction:"+dbTransaction+"  commit  OK!");
        dbTransaction.transactionCommit();
        dbTransaction.updateShowWindowSuccessSave("Η αντιγραφή από άλλη εταιρία τελείωσε επιτυχώς.");  //+" deleted successfully."
        dbTransaction.setTransactionAutoCommit(true);               
      
       }
       catch(SQLException e)
       {
           dbTransaction.transactionRollback();
           System.out.println(" error  PanelODMR.displayDialogCopyFromOtherCompany   rollBack  dbTransaction:"+dbTransaction); 
           dbTransaction.transactionUpdateQuerySQLException(e,true,"PanelODMR.displayDialogCopyFromOtherCompany");
         if(VariablesGlobal.globalShowPrintStackTrace)  
         {
           e.printStackTrace();     
         }           
       }
       finally
	{
	      if (!dbTransaction.isTransactionConnectionNull())
              {
	           dbTransaction.transactionClose();
              }
        }
                }
                

        
        if (panelDataFilter.checkIfFieldsAreCompleted())
        {
            filter();  // like refresh    
        }
                
   }    
  
   private void copyFromCompanyRow(EntityPanel[] entPanel,String fromCompPkValue, String pkOfRowValue,Database dbTransaction, int countOfRowsToBeAdded) throws SQLException
   {
      // boolean ret=false;
       // System.out.println("PanelODMR.copyFromCompanyRow     to copy r:"+row+"   "+ent+"    "+entPanel[0].getPrimKeyDb()+"  "+entPanel[0].getQuery()); 
        
       EntityTemplate entityTemplate = entPanel[0].getEntityTemplate();

        
      ArrayList listDbFields = new ArrayList();
      ArrayList listDbTable = new ArrayList();
      ArrayList listPanelOfDbFields = new ArrayList();
      ArrayList listPanelOfDbTable = new ArrayList();
      
      ArrayList listDbFieldsChild = new ArrayList();
      ArrayList listDbTableChild = new ArrayList();
        
        for(int p = 0;p<entPanel.length;p++)
        {
            
            if(entPanel[p].getDBFields()!=null)
            {
            for(int f = 0;f<entPanel[p].getDBFields().length;f++)
            {
                EntityDBFields dbfield = entPanel[p].getDBFields()[f];

                
               // System.out.println("PanelODMR.copyFromCompanyRow     p:"+p+"  f:"+f+"   "+dbfield.getTableName()+"."+dbfield.getDbField()+" "+dbfield.getCaption()+" ");//
                if(dbfield.getDbChildFields()!=null)
                {
                    for(int fs = 0;fs<dbfield.getDbChildFields().length;fs++)
                    {
                        EntityDBFields dbfieldchild = dbfield.getDbChildFields()[fs];
                       // System.out.println("      -        fs:"+fs+"  "+dbfieldchild.getTableName()+"."+dbfieldchild.getDbField()+" "+dbfieldchild.getCaption()+" ");
                //+" "+dbfield.getDbChildFields().length);
                        listDbFieldsChild.add(dbfieldchild.getDbField());
                        listDbTableChild.add(dbfieldchild.getTableName());
                    }
                }
                else
                {  // when not child, add
                    listDbTable.add(dbfield.getTableName()); 
                    listDbFields.add(dbfield.getDbField());                  
                }
                       
            } 
            }
            listPanelOfDbTable.add(listDbTable);// for panel p
            listPanelOfDbFields.add(listDbFields);// for panel p
        }
        
        
        //--- find the last pk value in current company and then add one
        String lastNoOfRecs="0";
        int intLastNoOfRecs=0;
        String queryRowCount = "SELECT "+entPanel[0].getPrimKeyDb()+" FROM `"+listDbTable.get(0)+"` WHERE `dbCompanyId` LIKE "+VariablesGlobal.globalCompanyId+" ORDER BY "+entPanel[0].getPrimKeyDb()+" DESC LIMIT 1";
        db.retrieveDBDataFromQuery(queryRowCount, "PanelODMR.copyFromCompanyRow get last pk");
        try
        {
           ResultSet rs = db.getRS();


            rs.first();
            lastNoOfRecs = rs.getString(1);
            System.out.println("PanelODMR.copyFromCompanyRow get last "+lastNoOfRecs+"   queryRowCount:"+queryRowCount);
        }
        catch(SQLException e)
        {
                System.out.println("error PanelODMR.copyFromCompanyRow   queryRowCount:"+queryRowCount);
                    System.out.println("error PanelODMR.copyFromCompanyRow get last pk  :"+e.getMessage());
        }
        finally
        {
            // NOT rs.close();
            
        }
        
        
    try
    {
        intLastNoOfRecs =  Integer.parseInt(lastNoOfRecs);
        intLastNoOfRecs = intLastNoOfRecs+countOfRowsToBeAdded;
        intLastNoOfRecs++;
    } catch (NumberFormatException | NullPointerException nfe)
    {
        System.out.println("PanelODMR.copyFromCompanyRow  lastNoOfRecs:"+lastNoOfRecs+" is NOT integer. nfe:"+nfe.getMessage());  
    }

        
        if(intLastNoOfRecs>=1)
        {
            lastNoOfRecs = intLastNoOfRecs+"";
        }
        else
        {
            lastNoOfRecs=lastNoOfRecs;
        }        

        
        
        
        for(int p = 0;p<entPanel.length;p++)
        {    
          if(entPanel[p].getPrimKeyDb() != null && !entPanel[p].getPrimKeyDb().equalsIgnoreCase("")) // null when the panel is statistics or something except data entry panel
          {            
            // --- for parent
          String strFields = "";
          ArrayList listDbFieldsOfPanel = (ArrayList)listPanelOfDbFields.get(p); // holds listDbFields
          ArrayList listDbTablesOfPanel = (ArrayList)listPanelOfDbTable.get(p); // holds listDbTable
          for(int f = 0;f<listDbFieldsOfPanel.size();f++)
          {
            strFields=strFields+" "+listDbFieldsOfPanel.get(f);
                    if(f<listDbFieldsOfPanel.size()-1)
                    {
                        strFields=strFields+", ";
                    }
          }
        
          String strPk = "";

              strPk = entPanel[p].getPrimKeyDb();
          
          System.out.println("PanelODMRec.copyFromCompanyRow    strPk:"+strPk+"  p:"+p+"       entityTemplate:"+entityTemplate);
          String strFieldsWithChangedDbCompanyId = strFields.replaceAll("dbCompanyId", VariablesGlobal.globalCompanyId);// replaces value with global var
          if(entityTemplate!=null)
          {
             strFieldsWithChangedDbCompanyId = strFields.replaceAll(entityTemplate.getFieldIsTemplate(), "1");
          }
          strFieldsWithChangedDbCompanyId = strFieldsWithChangedDbCompanyId.replaceAll(strPk,lastNoOfRecs);// would not like to set autoinc value
          String queryCopy = "INSERT INTO `"+listDbTablesOfPanel.get(0)+"` ("+strFields+") SELECT "+strFieldsWithChangedDbCompanyId+" FROM `"+listDbTablesOfPanel.get(0)+"` WHERE `dbCompanyId` LIKE "+fromCompPkValue+" AND "+entPanel[p].getPrimKeyDb()+" LIKE "+pkOfRowValue;
          System.out.println("PanelODMR.copyFromCompanyRow     queryCopy:"+queryCopy);
          int intRet = dbTransaction.transactionUpdateQuery(queryCopy,"PanelODMR.copyFromCompanyRow parent",true);
          
         
          

        //--- for childs
     //     ArrayList listDbFieldsChildOfPanel = (ArrayList)listDbFieldsChild.get(p); // holds childs listDbFields
     //     ArrayList listDbTablesChildOfPanel = (ArrayList)listDbTableChild.get(p); // holds childs listDbTable             
        for(int gf = 0 ;gf<listDbFieldsOfPanel.size();gf++)// for groups of childs fields
        {
       //System.out.println("      -        gf:"+gf+"  "+listDbFieldsOfPanel.get(gf));
            // 
           // EntityDBFields dbfield  = (EntityDBFields)listDbFieldsChild.get(gf);

            EntityDBFields dbfield = entPanel[p].getDBFields()[gf];
            
                 //if(listOfFieldOfPanels!=null)
                if(dbfield.getDbChildFields()!=null)
                {
                    for(int fs = 0;fs<dbfield.getDbChildFields().length;fs++)
                    {
                        EntityDBFields dbfieldchild = dbfield.getDbChildFields()[fs];
                        
                        System.out.println("      +        fs:"+fs+"  "+dbfieldchild.getTableName()+"."+dbfieldchild.getDbField()+" "+dbfieldchild.getCaption()+" "+dbfield.getDbChildFields().length);
                        
        String queryCopyChild="";
        String strFieldsChild = "";
        for(int f = 0;f<listDbFieldsChild.size();f++)
        {
            strFieldsChild=strFieldsChild+" "+listDbFieldsChild.get(f);
                    if(f<listDbFieldsChild.size()-1)
                    {
                        strFieldsChild=strFieldsChild+", ";
                    }
        }
        
        String strFieldsChildWithChangedDbCompanyId = strFieldsChild.replaceAll("dbCompanyId", VariablesGlobal.globalCompanyId);// replaces value with global var
       strFieldsChildWithChangedDbCompanyId = strFieldsChildWithChangedDbCompanyId.replaceAll(entPanel[p].getPrimKeyDb(),lastNoOfRecs);// would not like to set autoinc value
       
          if(entityTemplate!=null)
          {
             strFieldsChildWithChangedDbCompanyId = strFieldsChildWithChangedDbCompanyId.replaceAll(entityTemplate.getFieldIsTemplate(), "1");
          }       
       
       queryCopyChild = "INSERT INTO `"+dbfieldchild.getTableName()+"` ("+strFieldsChild+") SELECT "+strFieldsChildWithChangedDbCompanyId+" FROM `"+dbfieldchild.getTableName()+"` WHERE `dbCompanyId` LIKE "+fromCompPkValue+" AND "+entPanel[p].getPrimKeyDb()+" LIKE "+pkOfRowValue;
       System.out.println("PanelODMR.copyFromCompanyRow        queryCopyChild:"+queryCopyChild);
       int intRetChild = dbTransaction.transactionUpdateQuery(queryCopyChild,"PanelODMR.copyFromCompanyRow child",true);
        
           
                      }//  for
                 }// /if
        } // if, when p is valid
        }// for groups of childs fields
        } // entPanel
        
        

       
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
            //this.setOpaque(false);
        btnInsert = new JButton();
    //    btnInsert.setHorizontalTextPosition(SwingConstants.CENTER);
   //     btnInsert.setVerticalTextPosition(SwingConstants.BOTTOM);
        //btnInsertFromCopy = new JButton();
        btnEdit = new JButton();
    //    btnEdit.setHorizontalTextPosition(SwingConstants.CENTER);
    //    btnEdit.setVerticalTextPosition(SwingConstants.BOTTOM);        
        
        btnCopy = new JButton();
    
        btnDelete = new JButton();
    //    btnDelete.setHorizontalTextPosition(SwingConstants.CENTER);
    //    btnDelete.setVerticalTextPosition(SwingConstants.BOTTOM);        
        //btnTemplates = new JButtonListMenu();
        //btnTools= new JButtonListMenu();
        //mItemTemplatesSelect = new JMenuItem("επιλογή προτύπου");
//        mItemTemplatesSaveAs = new JMenuItem("αποθήκευση εγγραφής ως πρότυπη");        
       // mItemTemplatesNew = new JMenuItem("νέο πρότυπο");     
        //mItemTemplatesMgmt = new JMenuItem("διαχείρηση προτύπων");     
        //btnView = new JButtonListMenu();
        //btnFind = new JButton();
       btnCard = new JButton();
        btnPrintPreview = new JButton();
    //    btnPrintPreview.setHorizontalTextPosition(SwingConstants.CENTER);
    //    btnPrintPreview.setVerticalTextPosition(SwingConstants.BOTTOM);        
        btnExport = new JButton();
        //mItemPrintPreview = new JMenuItem("προεπισκόπηση εκτύπωσης");
        //mItemExport = new JMenuItem("εξαγωγή...");
        /*mItemHtml = new JMenuItem("άμεση εξαγωγή σε αρχείο html");
        mItemPdf = new JMenuItem("άμεση εξαγωγή σε αρχείο txt");
        mItemExcel = new JMenuItem("άμεση εξαγωγή σε αρχείο excel");*/
        btnCopyFromOtherCompany = new JButton();
        btnPreferences = new JButton();
   //     btnPreferences.setHorizontalTextPosition(SwingConstants.CENTER);
   //     btnPreferences.setVerticalTextPosition(SwingConstants.BOTTOM);        
        	
//        setFloatable(false);
//        setRollover(true);
        //this.setOpaque(false);
         
        btnInsert.setText("<html>νέα <b>F9</b></html>");
        //btnInsert.setText("εισαγωγή (F9)");
        btnInsert.setOpaque(false);
        btnInsert.setToolTipText("νέα εγγραφή");
        btnInsert.setIcon(ICO_INSERT16);
        btnInsert.setFocusable(false);
        btnInsert.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	
	          showEdit(true,false);
	         /*	if(isLookUpDialog)
	         	{
	         		displayDialogEdit(true,false);
	         	}
	         	else
	         	{
	         	   showPanelEditOneDataRec(true,false);
	         	}*/
	        } 
	    });
        Action actionNewRec = new ActionNewRec();
        btnInsert.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F9"), "newRec"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnInsert.getActionMap().put("newRec", actionNewRec);
       
       
        btnCopy.setText("<html>αντιγραφή</html>");
        //btnCopy.setText("(ctrl F9)");
        btnCopy.setOpaque(false);
        btnCopy.setToolTipText("εισαγωγή εγγραφής με αντιγραφή");
        btnCopy.setIcon(ICO_INSERT_COPY);
        btnCopy.setFocusable(false);
        btnCopy.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	 
	        
	           showEdit(true,true);

	        } 
	    });
        //Action actionNewRecFromCopy = new ActionNewRecFromCopy();
        //btnCopy.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F9 ,java.awt.event.InputEvent.CTRL_DOWN_MASK ), "newRecFromCopy"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        //btnCopy.getActionMap().put("newRecFromCopy", actionNewRecFromCopy);
        
        btnEdit.setText("<html>επεξεργασία <b>F10</b></html>");
        //btnEdit.setText("επεξεργασία (F10)");
        btnEdit.setOpaque(false);
        btnEdit.setToolTipText("επεξεργασία εγγραφής");
        btnEdit.setIcon(ICO_EDIT16);
        btnEdit.setFocusable(false);
        btnEdit.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	showEdit(false,false);
	        } 
	    });
        Action actionEditRec = new ActionEditRec();
        btnEdit.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F10"), "editRec"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnEdit.getActionMap().put("editRec", actionEditRec);

        btnDelete.setText("<html>διαγραφή <b>F12</b></html>");
        //btnDelete.setText("διαγραφή (F12)");
        btnDelete.setOpaque(false);
        btnDelete.setToolTipText("διαγραφή εγγραφής");
        btnDelete.setIcon(ICO_DELETE16);
        btnDelete.setFocusable(false);
        //btnDelete.setVerticalTextPosition(AbstractButton.BOTTOM);
        //btnDelete.setHorizontalTextPosition(AbstractButton.CENTER);
        btnDelete.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {
                    rowDelete();     
	         } 
	    });
        Action actionDelRec = new ActionDelRec();
        btnDelete.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F12"), "delRec"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnDelete.getActionMap().put("delRec", actionDelRec);


        /*btnTemplates.setText("<html>πρότυπα</html>");
        btnTemplates.setOpaque(false);
        //btnTemplates.setText("<html>εξαγωγή <b>Ξ</b></html>");
        btnTemplates.setToolTipText("πρότυπα");
        btnTemplates.setIcon(ICO_TEMPLATE);
        btnTemplates.setFocusable(false);
        //btnTemplates.setMnemonic(KeyEvent.VK_J);
        btnTemplates.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	 showMenuTemplates();
	        }
	    });
        popupMenuTemplates.add( mItemTemplatesSelect);
	    popupMenuTemplates.add( mItemTemplatesNew);
  //      popupMenuTemplates.add( mItemTemplatesSaveAs);
	    popupMenuTemplates.add( mItemTemplatesMgmt);
	            
        mItemTemplatesNew.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   displayDialogTemplateNew();  }
	    });        

        mItemTemplatesSelect.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   displayDialogTemplateSelect();  }
	    });  */ 

	            
       /* mItemTemplatesSaveAs.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   
	        	//displayDialogTemplateNew(); 
	        }
	    });    */    

       /* mItemTemplatesMgmt.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   displayDialogTemplateSelect(); 
	        
	        }
	    });  */ 
        
        // card = kartela
        btnCard.setText("<html>καρτέλα</html>");
        //btnPrintPreview.setText("εκτύπωση (F7)");
        btnCard.setOpaque(false);
        //btnPrintPreview.setText("<html>προεπισκόπηση <b>O</b></html>");	    
       // btnPrintPreview.setText("<html>προεπισκόπηση <b>alt+O</b></html>");
        btnCard.setToolTipText("προβολή καρτέλας");
        //btnCard.setIcon(ICO_PRINT_PREVIEW16);
        //btnPrintPreview.setMnemonic(KeyEvent.VK_O);
        btnCard.setFocusable(false);
        btnCard.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   displayPanelReport();  }
	    });
        //Action actionPrintPreview = new ActionPrintPreview();
        //btnCard.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), "printPreview"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        //btnCard.getActionMap().put("printPreview", actionPrintPreview);

        
        
        btnCopyFromOtherCompany.setText("<html>αντιγραφή από άλλη εταιρία</html>");
        //btnCalculation.setText("εκτύπωση (F7)");
        btnCopyFromOtherCompany.setOpaque(false);
        //btnCalculation.setText("<html>προεπισκόπηση <b>O</b></html>");	    
       // btnCalculation.setText("<html>προεπισκόπηση <b>alt+O</b></html>");
       if(strOfMany==null || strOfMany.equalsIgnoreCase(""))
       {
           btnCopyFromOtherCompany.setToolTipText("αντιγραφή από άλλη εταιρία");
       }
       else
       {
        btnCopyFromOtherCompany.setToolTipText("αντιγραφή από άλλη εταιρία "+strOfMany);
       }
        btnCopyFromOtherCompany.setIcon(ICO_COPY_FROM_OTHER_COMPANY);
        //btnPrintPreview.setMnemonic(KeyEvent.VK_O);
        btnCopyFromOtherCompany.setFocusable(false);
        btnCopyFromOtherCompany.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {       
                    displayDialogCopyFromOtherCompany();
                }
	    });        


        btnPrintPreview.setText("<html>εκτύπωση λίστας <b>F7</b></html>");
        //btnPrintPreview.setText("εκτύπωση (F7)");
        btnPrintPreview.setOpaque(false);
        //btnPrintPreview.setText("<html>προεπισκόπηση <b>O</b></html>");	    
       // btnPrintPreview.setText("<html>προεπισκόπηση <b>alt+O</b></html>");
        btnPrintPreview.setToolTipText("εκτύπωση");
        btnPrintPreview.setIcon(ICO_PRINT_PREVIEW16);
        //btnPrintPreview.setMnemonic(KeyEvent.VK_O);
        btnPrintPreview.setFocusable(false);
        btnPrintPreview.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   displayPanelReport();  }
	    });
        Action actionPrintPreview = new ActionPrintPreview();
        btnPrintPreview.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), "printPreview"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnPrintPreview.getActionMap().put("printPreview", actionPrintPreview);

//        popupMenuPrintExport.add(mItemPrintPreview);
//        popupMenuPrintExport.addSeparator();
        

        
        /*
        //popupMenuPrintExport.add(mItemExport);
        popupMenuPrintExport.add(mItemHtml);
        popupMenuPrintExport.add(mItemPdf);
        popupMenuPrintExport.add(mItemExcel);*/

        /*mItemPrintPreview.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   displayPanelReport();  }
	    });*/


        /*mItemExport.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	 exportTo("");
	        }
	    });*/

        /*mItemHtml.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   exportTo("html");  }
	    });

        mItemPdf.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   exportTo("txt");  }
	    });

        mItemExcel.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   exportTo("xls");  }
	    });*/
        
       
      
        btnExport.setOpaque(false);
        btnExport.setText("<html>εξαγωγή</html>");
        //.setToolTipText("εργαλεία");
        btnExport.setIcon(ICO_BACKUP);
        btnExport.setFocusable(false);
        //btnExport.setMnemonic(KeyEvent.VK_J);   
        btnExport.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   exportTo("xls");  }
	    });
  
  
      	/*UtilsMiscEntities utilsMiscEntities = new UtilsMiscEntities();
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
    	   popupMenuPrintExport.add(mItemExport);
    	}   
    		*/
    		
    		
  
  
        
       /* popupMenuPrintExport.add( mItemHtml);
        popupMenuPrintExport.add( mItemPdf);
        popupMenuPrintExport.add( mItemExcel);
        
        mItemHtml.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   exportTo("html");  }
	    });

        mItemPdf.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   exportTo("txt");  }
	    });

        mItemExcel.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   exportTo("xls");  }
	    });
               */
        btnPreferences.setText("<html>προ<b>Τ</b>ιμήσεις πίνακα</html>");
        //btnPreferences.setText("προτιμήσεις (alt t)");
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
       /* Action actionTablePreferences = new ActionTablePreferences();
        btnPreferences.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F3"), "prefs"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnPreferences.getActionMap().put("prefs", actionTablePreferences);*/
//
     //   MouseListener popupListener = new PopupListener();
  //      btnExport.addMouseListener(popupListener);



         
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
  //      addSeparator();
        add(btnInsert);
        add(btnCopy);
        //add(btnInsertFromCopy);
        add(btnEdit);
        add(btnDelete);
         add(btnCopyFromOtherCompany);
  //      addSeparator();
        //add(btnView);
        //add(btnTemplates);
        
        //addSeparator();
       // add( btnPrevious );
       // add( btnNext );
       // addSeparator();
        //add( btnFind );
  //     add(btnCard);
        add(btnPrintPreview);
  //      addSeparator();
        //add(btnExport);
        add(btnExport);
        add(btnPreferences);
   //     addSeparator();

        }
        
        @Override
        protected void paintComponent(Graphics g)
        {
             /*Graphics2D g2 = (Graphics2D) g;   //                     15
             GradientPaint paint = new GradientPaint(0, 0, this.getBackground().brighter(), 0, 30,this.getBackground().darker(),true);
             g2.setPaint(paint);
             g2.fill(getBounds());*/
             super.paintComponent(g);
        }
  }

/*
  similars in PanelODMR.getTitleCaption, PanelODOR.setTitle

*/
private String getTitleCaption(int row)
{
    String caption="";
            try
           {    
               //System.out.println("PanelOneDataManyRec.getTitleCaption()   --    q:"+q);
   	           db.retrieveDBDataFromQuery(utilsString.removeCaptionsFromQuerySubStringSelect(queryReadAfterFilter),"PanelOneDataManyRec.getTitleCaption");
   	           rs=db.getRS();
   	           //ResultSetMetaData rsmd = db.getRSMetaData();
   	                    	      
                //rs = db.retrieveResultSet(q);
             
                rs = db.retrieveRow(rs, row + 1 );// go to the only row   
                //System.out.println("PanelODMR.getTitleCaption  -------------  row:"+row+"      rs:"+rs+"      queryReadAfterFilter:"+utilsString.removeCaptionsFromQuerySubStringSelect(queryReadAfterFilter));
                if (rs!=null)
                {
                        String strField="";
                	for(int f = 0;f<fieldsOnTitle.length;f++)
                	{
                		//System.out.println("PanelODMR.getTitleCaption  -A-  field "+f+" "+fieldsOnTitleCaption[f]+" "+fieldsOnTitle[f]+" = "+rs.getString(fieldsOnTitle[f]));
                          
                          EntityDBFields[] dbFieldsAll = entityPanel[0].getDBFields();
                          
                                String str = rs.getString(fieldsOnTitle[f]);
    	                        String[] allowedPatternsToRead = {"yyyy-MM-dd","yy/MM/dd","yy-MM-dd", "yyyy/MM/dd","dd-MM-yyyy","dd/MM/yy","dd-MM-yy", "dd/MM/yyyy"};
                                String fdate=utilsDate.reformatDateString(str,allowedPatternsToRead,"EEE dd-MM-yyyy");                                
                              if(fdate==null || fdate.equalsIgnoreCase(""))
                              {
                                  strField = str;
                              }
                              else
                              {
                                  
                                  strField=fdate;
                              }
                          
                		caption = caption+" "+ fieldsOnTitleCaption[f]+":"+ strField;
                	}
                }
           }//try
           catch ( SQLException sqlex)
           {
               System.out.println("error:  PanelODMR.getTitleCaption(): "+sqlex.getMessage()+"  title=" +title+" query="+ query);
           }    
    closeDB();
    return caption;
    
}
  
    private void showEdit(boolean isNewRecIn,boolean isNewRecFromCopy)
    {
        isNewRec=isNewRecIn;
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    	int selectedRow = panelOneDataManyRecData.getSelectedTableRow();
         //System.out.println("PanelODMR.showEdit ooooooooooooooooooooooooo isNewRec:"+isNewRec+"     isNewRecFromCopy:"+isNewRecFromCopy+"        selectedRow:"+selectedRow+"    isLookUpDialog:"+isLookUpDialog); 
        if(panelOneDataManyRecData.getRowCountFromReadOnlyTable()==0 && isNewRec==false)
        {
             utilsGui.showMessageInfo("No records to edit.");
        }
        else if(selectedRow==-1 && isNewRec==false)//when selected table row is -1 then no data in the table
        {
             utilsGui.showMessageInfo("Please select a record first.");
        }
        else
        {
             if(panelOneDataManyRecData.getRowCountFromReadOnlyTable()==0 && isNewRec && isNewRecFromCopy)
             {
                   utilsGui.showMessageInfo("No records to copy.");
             }
             else
             {
        String primKeyValue = "0";
        String primKey ;

        primKey = panelOneDataManyRecData.getPrimKey();
        //int selectedRow = PanelOneDataManyRecData.getSelectedTableRow();
        primKeyValue = panelOneDataManyRecData.getPrimKeyValue();
       System.out.println("PanelODMR.showEdit isNewRec:"+isNewRec+"  isNewRecFromCopy:"+isNewRecFromCopy+"   "+primKey+"="+primKeyValue+"        selectedRow:"+selectedRow+"    isLookUpDialog:"+isLookUpDialog);     	

       if(isLookUpDialog)
       {
	  	displayDialogEdit(isNewRec,isNewRecFromCopy);
       }
       else
       {
	        showPanelEditOneDataRec(isNewRec,isNewRecFromCopy);
       }
	         	
       // if(searchField!=null)
       // {            }
        
        if (panelDataFilter.checkIfFieldsAreCompleted())
        {
            filter();  // like refresh 

        }
        
        if(isNewRec)
        {
            
            
        	panelOneDataManyRecData.setSelectedTableRow(primKey,primKeyValue);
        	System.out.println("panelOneDataManyRec.showPanelEditOneDataRec TODO the new rec should be selected if ok pressed   primKey:"+primKey+"="+primKeyValue);
        }
        else
        {
            //System.out.println("panelOneDataManyRec.displayDialogEdit "+primKey+" "+primKeyValue);
            panelOneDataManyRecData.setSelectedTableRow(primKey,primKeyValue);
        }
             }
        }// selectedRow

    	this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  
        panelOneDataManyRecData.closeDB();
    }
  
  
    // part of code also in PanelOneDataOneRecData.displayDialogEdit
    public void displayDialogEdit(boolean isNewRecIn,boolean isNewRecFromCopy)
    {  
        isNewRec=isNewRecIn;
        PanelEditOneDataRec panelEODR = new PanelEditOneDataRec(frame);

        String primKeyValue = "0";
        String primKey ;
        primKey = panelOneDataManyRecData.getPrimKey();
        
        if(isNewRec)
        {
            primKeyValue="0";
        }
        else
        {
              primKeyValue = panelOneDataManyRecData.getPrimKeyValue();
        }        
        
        
        //System.out.println("panelOneDataManyRec.displayDialogEdit primKey:"+primKey+" primKeyValue:"+primKeyValue+"  queryReadOnly:"+queryReadOnly);
                 
        System.out.println("panelOneDataManyRec displayDialogEdit ==========================================  primKeyValue:"+primKeyValue);
        
        
        String editTitle=strOfOne;
        int selected = panelEODR.setEntity(entity, entityPanel,fieldsOnTitle,fieldsOnTitleCaption,false,primKey,primKeyValue,primKeyDb,/*formGlobalTableToGet1,formGlobalTableToApply1,/*null,null,*/
                queryReadOnly, editTitle,ico/*,true*/,isNewRec,isNewRecFromCopy,true,categoryNodes, false,panelManagement);//,entityReportForm);	
    	/*public void setEntity(String entityIn, EntityPanel[] entityPanelIn,int[]fieldsOnTitleIn, String[] fieldsOnTitleCaptionIn, 
    boolean isMasterUnique, String primKey,  String primKeyValueIn,String primKeyDbIn, String[] primKeysMany,String[] primKeysManyTran,
    String titleIn,ImageIcon ico, boolean dataOneIn, boolean isNewRecIn, boolean showBtnOk, String[] categoryNodes, boolean showShowListButtonsIn) 	)*/
    	
     if(selected == 0 && !isNewRec)// when 0 do not display
     {
         System.out.println("PanelODMR.displayDialogEdit                                      selected:"+selected);
          utilsGui.showMessageInfo("Η εγγραφή δεν υπάρχει. Πιθανόν είναι ανενεργή.");
     }
     else
     {
        panelEODR.setVisible(true);
        
        DialogMulti dlg = new DialogMulti(frame);
        dlg.setEntity(panelEODR,PANEL_TYPE_EDITONEDATAONEREC, "επεξεργασία "+editTitle,true);
        
        dlg.display();
     }  
 
         
     
    }   
    
    /*
    * 
    */
    public void showPanelEditOneDataRec(boolean isNewRecIn,boolean isNewRecFromCopy)
    {
        isNewRec=isNewRecIn;
        String primKeyValue = "0";
        String primKey;
        primKey = panelOneDataManyRecData.getPrimKey();
        //selectedRow = PanelOneDataManyRecData.getSelectedDataRow();
        
        if(isNewRec && !isNewRecFromCopy)
        {
            primKeyValue="0";
        }
        else
        {
              primKeyValue = panelOneDataManyRecData.getPrimKeyValue();
        }
        //System.out.println("panelOneDataManyRec.displayDialogEdit primKeyValue "+primKeyValue);        
        //System.out.println("panelOneDataManyRec.displayDialogEdit primKey"+primKey);
                 
        System.out.println("    OOOOOO   panelOneDataManyRec showPanelEditOneDataRec ---------------------------------------   entityPanel.length:"+entityPanel.length+"  primKeyValue:"+primKeyValue+"    "+queryReadOnly+"  "+query+"  "+entityPanel[0].getQuery());
        String editTitle=strOfOne;
        panelEditOneDataRec.setEntity(entity, entityPanel,fieldsOnTitle,fieldsOnTitleCaption,false,primKey,primKeyValue,primKeyDb,/*formGlobalTableToGet1,formGlobalTableToApply1,/*null,null,*/
                queryReadOnly,     editTitle,ico/*,true*/,isNewRec,isNewRecFromCopy,true,categoryNodes, true, panelManagement);//, entityReportForm);	
    	/*public void setEntity(String entityIn, EntityPanel[] entityPanelIn,int[]fieldsOnTitleIn, String[] fieldsOnTitleCaptionIn, 
    boolean isMasterUnique, String primKey,  String primKeyValueIn,String primKeyDbIn, String[] primKeysMany,String[] primKeysManyTran,
    String titleIn,ImageIcon ico, boolean dataOneIn, boolean isNewRecIn, boolean showBtnOk, String[] categoryNodes)*/

       panelOneDataManyRecData.setVisible(false);
       panelTop.setVisible(false);
       panelEditOneDataRec.setVisible(true); 
       
       
    }

     // PanelODORData.checkIfIsPrinted
    //PanelODMR.checkIfIsPrinted
    private boolean checkIfIsPrinted(int selectedRow)
    {
        boolean isReadOnly = false;

       /* boolean isNewRec = false;
        boolean isNewRecFromCopy = false;
        PanelEditOneDataRec panelEODR = new PanelEditOneDataRec(frame);
        String editTitle=strOfOne;*/
       // int selected = panelEODR.setEntity(entity, entityPanel,fieldsOnTitle,fieldsOnTitleCaption,false,panelOneDataManyRecData.getPrimKey(),primKeyValue,primKeyDb,/*formGlobalTableToGet1,formGlobalTableToApply1,/*null,null,*/
       //         queryReadOnly, editTitle,ico/*,true*/,isNewRec,isNewRecFromCopy,true,categoryNodes, false,panelManagement);//,entityReportForm);        


      ArrayList listDbFields = new ArrayList();
      ArrayList listDbTable = new ArrayList();
      ArrayList listPanelOfDbFields = new ArrayList();
      ArrayList listPanelOfDbTable = new ArrayList();

      ArrayList listDbFieldsChild = new ArrayList();
      ArrayList listDbTableChild = new ArrayList(); 
      ArrayList<EntityDBFields> listFieldsInGroupOfPanels = new ArrayList();
        for(int p = 0;p<entityPanel.length;p++)
        {
            if(entityPanel[p].getDBFields()==null)
            {
                break;
            }
            else
            {

              for(int f = 0;f<entityPanel[p].getDBFields().length;f++)
              {
                 EntityDBFields dbfield = entityPanel[p].getDBFields()[f];
                 listFieldsInGroupOfPanels.add(dbfield);

              // System.out.println("PanelODMR.PanelODMR     p:"+p+"  f:"+f+"   "+dbfield.getTableName()+"."+dbfield.getDbField()+" "+dbfield.getCaption()+" ");//

                //    listDbTable.add(dbfield.getTableName()); 
                 //   listDbFields.add(dbfield.getDbField());                  

              }
            }

        }        
      if(listFieldsInGroupOfPanels==null)
      {

      }
      else
      {
        // transfer dbfield from ArrayList listFieldsInGroupOfPanels to array dbFieldsInGroupOfPanels
        EntityDBFields[] dbFieldsInGroupOfPanels = new EntityDBFields[listFieldsInGroupOfPanels.size()];
        for(int l = 0 ;l<listFieldsInGroupOfPanels.size();l++)
        {
            dbFieldsInGroupOfPanels[l] = listFieldsInGroupOfPanels.get(l);
        }



       try
       {
           //if isPrinted==1 make read only       
          for (int i = 0; i < dbFieldsInGroupOfPanels.length; i++)
          {
                String fieldName = dbFieldsInGroupOfPanels[i].getDbField();
               // int isEditableOrVisible = dbFieldsInGroupOfPanels[i].getIsVisibleOrEditable();

                //System.out.println("   PanelODMR.checkIfAllComponentsShouldBeReadOnly   primKeyDb:"+primKeyDb+"    fieldName:"+fieldName+"   selectedRow:"+selectedRow);


                if (fieldName.equalsIgnoreCase(STRFIELD_ISPRINTED))
                {

            String primKeyValue = panelOneDataManyRecData.getPrimKeyValue();    
          String subqueryWhere = ""; // for each primary key          
             utilsPanelReport.retrievePrimKeyValueForOnePK( query, selectedRow, dbFieldsInGroupOfPanels,null,false,/*ismany,/*primKeyIn,intColumnOfDescriptionIn,
             sql2WhereField, sql2WhereValue,*/ entity, /*tableModelReadOnly,*/ primKeyDb);    

             String[] primKeys = utilsPanelReport.getPrimKeys();
             //String[] primKeysCaption = utilsPanelReport.getPrimKeysCaption();
            //System.out.println("PanelODMR.rowUpdate '"+entity+"' selectedRow:"+selectedRow+"  primKeys:"+primKeys.length); 
             int primKeysCount = primKeys.length;
             String[] primKeysValue = utilsPanelReport.getPrimKeysValue();              


      //    databaseTableMeta.retrievePrimKs(entity); // first retrieve them
          for (int p = 0; p< primKeysCount; p++) // i=0 and i< because arraylist starts from 0
          {             

               //System.out.println("PanelODMR.rowUpdate  subqueryWhere  ("+i+")  "+primKey+"   "+primKeys[i]+"="+primKeysValue[i]+"     primKeyDb:"+primKeyDb+"  primKeyValue:"+primKeyValue);   
               if(primKeys[p].equalsIgnoreCase(primKeyDb))
               {
                subqueryWhere = subqueryWhere+"("+primKeys[p]+" LIKE '"+primKeyValue+"')"; // when is updating if a second time after insert is selected
               }
               else
               {
                   subqueryWhere = subqueryWhere+"("+primKeys[p]+" LIKE '"+primKeysValue[p]+"')";
               }

          	  if (p < primKeys.length-1 && primKeys.length>1) 
          	  // add AND but not on the last field(before where), also not when there is only one PK . -1 because arraylist starts from 0
          	  { subqueryWhere = subqueryWhere+" AND  ";   }              
          }                    



                    String q = utilsString.getQueryBeforeWhere(query)+" WHERE "+subqueryWhere;


   	    db.retrieveDBDataFromQuery(q,"PanelODMR.checkIfIsPrinted");
   	    rs=db.getRS(); 
            rs.first();
            //System.out.println("      panelOneDataOneRecData.checkIfAllComponentsShouldBeReadOnly   primKeyDb"+primKeyDb+"    primKeyValue:"+primKeyValue);

             //System.out.println("      panelOneDataOneRecData.checkIfAllComponentsShouldBeReadOnly    selectedRow"+selectedRow+"     q:"+q);



                   int isPrintedValue = rs.getInt(fieldName);  
                   if(isPrintedValue>1)
                   {
                          isReadOnly=true;
                          break;                       
                   }
                   else
                   {
                         isReadOnly = false;
                   }
                }
          }       
       }
       catch(SQLException e)
       {
            System.out.println("error   PanelODMR.checkIfIsPrinted  "+e.getMessage());
         if(VariablesGlobal.globalShowPrintStackTrace)  
         {
           e.printStackTrace();     
         }            

       }
       finally
       {
           closeDB();
       }             

      }


        return isReadOnly;
    }


    
    /*
    *
    * called by this button delete
    */
    private void rowDelete()
    {
       

        int selectedParentTableRow = 0;
        selectedParentTableRow = panelOneDataManyRecData.getSelectedTableRow();        
        
        
      if(panelOneDataManyRecData.getRowCountFromReadOnlyTable()>0)  
      {
         if(!checkIfIsPrinted(selectedParentTableRow))    
       {         
        int retShowMessage = YES;

        String titleCaption = this.getTitleCaption(selectedParentTableRow);
        retShowMessage=utilsGui.showConfirmYesOrNo(frame,"Are you sure you would like to delete this record? \n "+titleCaption);
        
     if(retShowMessage==YES)
     {


        Database dbTransaction = new Database();
        try
        {
          dbTransaction.transactionLoadConnection();
          dbTransaction.setTransactionAutoCommit(false);
         System.out.println("  PanelODMR.rowDelete     row:"+(selectedParentTableRow+1)+"      dbTransaction:"+dbTransaction+"      selectedParentTableRow:"+selectedParentTableRow); 
        
        
       // if()
       // {
            rowDeleteChildTablesAndHtmlFile(selectedParentTableRow+1,dbTransaction);

            panelOneDataManyRecData.rowDbDelete(selectedParentTableRow,false,dbTransaction);
       // }
        
        System.out.println("PanelODMR.rowDelete     dbTransaction:"+dbTransaction+"  commit"); 
        dbTransaction.transactionCommit();
        dbTransaction.updateShowWindowSuccessDelete(titleCaption);  //+" deleted successfully."
        dbTransaction.setTransactionAutoCommit(true);               
      
       }
       catch(SQLException e)
       {
           dbTransaction.transactionRollback();
           System.out.println(" error  PanelODMR.rowDelete   rollBack  dbTransaction:"+dbTransaction); 
           dbTransaction.transactionUpdateQuerySQLException(e,true,"PanelODMR.rowDelete");
         if(VariablesGlobal.globalShowPrintStackTrace)  
         {
           e.printStackTrace();     
         }           
       }
       finally
	{
	      if (!dbTransaction.isTransactionConnectionNull())
              {
	           dbTransaction.transactionClose();
              }
        }          
        
        if (panelDataFilter.checkIfFieldsAreCompleted())
        {
            filter();  // like refresh    
        }
     } 
      } // rowCount>0
        else // is not Editable 
       {
          utilsGui.showMessageInfo("Η εγγραφή δεν γίνεται να διαγραφεί. Είναι μόνο για προβολή.");
       }
      }
      else
      {
          utilsGui.showMessageInfo("No records to delete.");
          
      }
      
    }
 
    
    

    
    
    
    
    // also exists different in PanelOneDataOneRecData.rowDeleteTables
   private boolean rowDeleteChildTablesAndHtmlFile(int selParentTableRow,Database dbTransaction)throws SQLException
   {

       boolean ret = false;
       
 //      	      String queryDelete = "DELETE FROM "+entity;
//       	      String subqueryWhere= " WHERE ";
     //System.out.println(" DEL PanelOneDataManyRec.rowDeleteChildTables   queryReadAfterFilter :"+queryReadAfterFilter);
                      String q = panelOneDataManyRecData.retrieveDataFromReadOnlyTable(queryReadAfterFilter,entity);
                      //int selTableRow= PanelOneDataManyRecData.getSelectedTableRow()+1;
            
                      utilsPanelReport.retrievePrimKeyValueForOnePK( q,selParentTableRow, entityPanel[0].getDBFields(),null,false,/*primKeyIn,intColumnOfDescriptionIn,
                       sql2WhereField, sql2WhereValue,*/ entity, /*tableModelReadOnly,*/ primKeyDb);
                       
    
               EntityDBFields[]  dbFieldsInGroupOfPanels = entityPanel[0].getDBFields();
               if (dbFieldsInGroupOfPanels==null)
               {
                    System.out.println("PanelOneDataManyRec.rowDeleteChildTablesAndHtmlFile dbFieldsInGroupOfPanels="+dbFieldsInGroupOfPanels);
               }
              // int colCount =dbFieldsInGroupOfPanels.length;
              ArrayList listRetDeleted = new ArrayList();
                      
              //System.out.println("---->>    PanelOneDataOneRecData.rowDeleteTables colCount:"+colCount);        
//     try
//     {
              for (int i = 0; i < dbFieldsInGroupOfPanels.length; i++)//  i = fieldTxts
              { 
                 String columnName = dbFieldsInGroupOfPanels[i].getDbField(); //get colunm name  	           	
	         String classtype = dbFieldsInGroupOfPanels[i].getColClassName();  // if integer then not add ' and ' between values

                //System.out.println("---->>    PanelOneDataOneRecData.rowDeleteTables   "+columnName+"  "+classtype);
                if(classtype.equalsIgnoreCase("table"))
                {
                   String queryChild = dbFieldsInGroupOfPanels[i].getSqlTableChildRead();
                   //String[] arrayQueryChild = dbFieldsInGroupOfPanels[i].getFieldsForCalculationOfTableRead();
              
                   String tableChild = dbFieldsInGroupOfPanels[i].getChildTable();
                   EntityDBFields[] dbFieldsChild = dbFieldsInGroupOfPanels[i].getDbChildFields();                              
                   int intTableOfParentDBFields = i;
                   //primKeys = utilsPanelReport.getPrimKeys();
                        //primKeysCount = primKeys.length;
                        String primKeyValue = utilsPanelReport.getPrimKeyValue();                    
                    

             // String primKeyValueTable ="";
              
                 db.retrieveDBDataFromQuery(queryChild,"PanelOneDataManyRec.rowDeleteChildTablesAndHtmlFile table");
   	         rs=db.getRS();              
              // System.out.println("PanelOneDataManyRec.rowDeleteChildTables  ("+i+")    primKeyDb:"+primKeyDb+"     primKeyValue:"+primKeyValue);
                 int selectedDataRow=utilsPanelReport.getRowForPrimKey("PanelOneDataManyRec.rowDeleteChildTablesAndHtmlFile table",queryChild,rs,dbFieldsChild,primKeyDb,primKeyValue);
          if(selectedDataRow!=0)     // no matching table rows
          {
                
   //              utilsPanelReport.retrievePrimKeyValueForOnePK( queryChild, selectedRow, null,dbFieldsChild,true,/*primKeyIn,intColumnOfDescriptionIn,
   //                    sql2WhereField, sql2WhereValue,*/ tableChild, /*tableModelReadOnly,*/ primKeyDb);
                       
                        
  /*                    String[]  primKeys = utilsPanelReport.getPrimKeys();
                      int  primKeysCount = primKeys.length;
                      String[]  primKeysValue = utilsPanelReport.getPrimKeysValue();
*/
        
              String primKeyNameTable="";  
              String primKeyValueTable ="";   
              String subqueryWhere="";  
              if(utilsString.hasQueryWhere(queryChild))
              {
                  subqueryWhere=" AND ";
              }
              else
              {
                  subqueryWhere=" WHERE ";
              }   
              int primKeysCount=0;
                  for(int f = 0;f<dbFieldsChild.length;f++)
                  {
                      //System.out.println("PanelODORData.showRow ("+i+"."+f+")  primKeyName:"+dbFieldsAll[f].getDbField()+"  isPrimaryKeyIntegerAutoInc:"+dbFieldsAll[f].getPrimaryKeyIntegerAutoInc());
                      if (dbFieldsChild[f].getPrimaryKeyIntegerAutoInc() == FIELD_PRIMARY_KEY_FROM_PARENTTABLE || dbFieldsChild[f].getPrimaryKeyIntegerAutoInc() == FIELD_PRIMARY_KEY)//rsmd.isAutoIncrement(i))
                      {
                          
                          primKeyNameTable =   dbFieldsChild[f].getDbField();  
                          
                          //primKeyName = colName;
                          // primKeyValue
                           rs = db.retrieveRow(rs, selectedDataRow);
                          System.out.println("---->A PanelOneDataManyRec.rowDeleteChildTablesAndHtmlFile  (i:"+i+"  f:"+f+")   selectedDataRow:"+selectedDataRow+"    primKeyValue:"+primKeyValue+"  ("+f+")   primKeyNameTable:"+primKeyNameTable+"      queryChild:"+queryChild);
                          primKeyValueTable =  rs.getString(dbFieldsChild[f].getDbField());//tb.getText();
                          
                          
                          
                 	 if (primKeysCount>=1)//after the first time that runs adds an and
             	          {  	 subqueryWhere = subqueryWhere + " AND ";    }
                         
                          
                             subqueryWhere=subqueryWhere+primKeyNameTable+" LIKE "+primKeyValueTable;    
                          
                         primKeysCount++; 
                      }
                      else
                      {
                          
                      }
                  }
                              
                   
              
              
 
                               
		     String queryWithoutOrderby = utilsString.getQueryWithoutOrderby(queryChild);
		     String queryOrderby = utilsString.getOrderbySubQuery(queryChild);                        
                                                
                     String sql = queryWithoutOrderby+subqueryWhere+" "+queryOrderby;
System.out.println("---->B PanelOneDataManyRec.rowDeleteChildTablesAndHtmlFile  ("+i+")  columnName:"+columnName+" classtype:"+classtype +"     selectedDataRow:"+selectedDataRow+"    primKeyDb:"+primKeyDb+"=primKeyValue:"+primKeyValue+"      subqueryWhere:"+subqueryWhere+"          sql:"+sql);                           
                        
                    
                    
                    PanelOneDataManyRecData pnlODMRData = new PanelOneDataManyRecData(frame);//(PanelOneDataManyRecData)fieldTxts.get(i);//,PanelOneDataManyRecData);
 System.out.println("---->C PanelOneDataManyRec.rowDeleteChildTablesAndHtmlFile  ("+i+")       "+dbFieldsInGroupOfPanels[i].getCaption()+"    sql:"+sql+"    primKeyDb:"+primKeyDb+"="+primKeyValue+"     dbFieldsChild.length:"+dbFieldsChild.length+"   queryChild:"+queryChild+"    sql:"+sql);
                    pnlODMRData.setEntity(dbFieldsInGroupOfPanels[i].getCaption(),sql,tableChild,true,true,null/*dbFieldsParent*/,dbFieldsChild,false/*isNewRec*/,primKeyDb,/*formGlobalTableToGet1,formGlobalTableToApply1,//formGlobalField1,formGlobalVariable1,*/
                            primKeyValue,fieldsForSums,fieldTxts,null,intTableOfParentDBFields);//,updateAdditional);

                    pnlODMRData.filterToDeleteForWritableTable(sql,false);
             
                   int rowCount =  pnlODMRData.getRowCount();
 
System.out.println("---->D PanelOneDataManyRec.rowDeleteChildTablesAndHtmlFile  ("+i+")     rowCount:"+rowCount+"    dbFieldsChild.length:"+dbFieldsChild.length+"   queryChild:"+queryChild+"    sql:"+sql);                   
                  for(int r=0 ;r<rowCount;r++)
                  {
                      //System.out.println("PanelOneDataManyRec.rowDeleteChildTables  (i"+i+")  (r"+r+")   rowCount:"+rowCount); 
                     if(pnlODMRData.rowDbDelete(r,false,dbTransaction))
                     {
                         listRetDeleted.add(true);
                     }
                    else
                    {
                        listRetDeleted.add(false);
                    }                     
                  }



          }
          else// selectedRow==0
          {
              
          }
                }// if table
                else if(classtype.equalsIgnoreCase("htmlfile"))
                {
                    
                   String queryChild = dbFieldsInGroupOfPanels[i].getSqlTableChildRead();
                   String tableChild = dbFieldsInGroupOfPanels[i].getChildTable();
                   EntityDBFields[] dbFieldsChild = dbFieldsInGroupOfPanels[i].getDbChildFields();                              
                        //primKeys = utilsPanelReport.getPrimKeys();
                        //primKeysCount = primKeys.length;
                        String primKeyValue = utilsPanelReport.getPrimKeyValue();                    
                    

             // String primKeyValueTable ="";
              
                 db.retrieveDBDataFromQuery(queryChild,"PanelOneDataManyRec.rowDeleteChildTablesAndHtmlFile htmlfile");
   	         rs=db.getRS();              
              // System.out.println("PanelOneDataManyRec.rowDeleteChildTables  ("+i+")    primKeyDb:"+primKeyDb+"     primKeyValue:"+primKeyValue);
                 int selectedDataRow=utilsPanelReport.getRowForPrimKey("PanelOneDataManyRec.rowDeleteChildTablesAndHtmlFile htmlfile",queryChild,rs,dbFieldsChild,primKeyDb,primKeyValue);
          if(selectedDataRow!=0)     // no matching table rows
          {
                
   //              utilsPanelReport.retrievePrimKeyValueForOnePK( queryChild, selectedRow, null,dbFieldsChild,true,/*primKeyIn,intColumnOfDescriptionIn,
   //                    sql2WhereField, sql2WhereValue,*/ tableChild, /*tableModelReadOnly,*/ primKeyDb);
                       
                        
  /*                    String[]  primKeys = utilsPanelReport.getPrimKeys();
                      int  primKeysCount = primKeys.length;
                      String[]  primKeysValue = utilsPanelReport.getPrimKeysValue();
*/
        
              String primKeyNameTable="";  
              String primKeyValueTable ="";   
              String subqueryWhere="";  
              if(utilsString.hasQueryWhere(""))// it was variable 'queryChild' but we need "where"
              {
                  subqueryWhere=" AND ";
              }
              else
              {
                  subqueryWhere=" WHERE ";
              }   
              int primKeysCount=0;
                  for(int f = 0;f<dbFieldsChild.length;f++)
                  {
                      //System.out.println("PanelODORData.showRow ("+i+"."+f+")  primKeyName:"+dbFieldsAll[f].getDbField()+"  isPrimaryKeyIntegerAutoInc:"+dbFieldsAll[f].getPrimaryKeyIntegerAutoInc());
                      if (dbFieldsChild[f].getPrimaryKeyIntegerAutoInc() == FIELD_PRIMARY_KEY_FROM_PARENTTABLE || dbFieldsChild[f].getPrimaryKeyIntegerAutoInc() == FIELD_PRIMARY_KEY)//rsmd.isAutoIncrement(i))
                      {
                          
                          primKeyNameTable =   dbFieldsChild[f].getDbField();  
                          
                          //primKeyName = colName;
                          // primKeyValue
                           rs = db.retrieveRow(rs, selectedDataRow);
                          //System.out.println("=====>A PanelOneDataManyRec.rowDeleteChildTablesAndHtmlFile  (i:"+i+"  f:"+f+")   selectedDataRow:"+selectedDataRow+"    primKeyValue:"+primKeyValue+"  ("+f+")   primKeyNameTable:"+primKeyNameTable+"      queryChild:"+queryChild);
                          primKeyValueTable =  rs.getString(dbFieldsChild[f].getDbField());//tb.getText();
                          
                          
                          
                 	 if (primKeysCount>=1)//after the first time that runs adds an and
             	          {  	 subqueryWhere = subqueryWhere + " AND ";    }
                         
                          
                             subqueryWhere=subqueryWhere+primKeyNameTable+" LIKE "+primKeyValueTable;    
                          
                         primKeysCount++; 
                      }
                      else
                      {
                          
                      }
                  }
                               
		     String queryWithoutOrderby = utilsString.getQueryWithoutOrderby(queryChild);
		     String queryOrderby = utilsString.getOrderbySubQuery(queryChild);                        
                                                
                     String sql = queryWithoutOrderby+subqueryWhere+" "+queryOrderby;
System.out.println("PanelOneDataManyRec.rowDeleteChildTablesAndHtmlFile  ("+i+")  columnName:"+columnName+" classtype:"+classtype +"     selectedDataRow:"+selectedDataRow+"    primKeyDb:"+primKeyDb+"=primKeyValue:"+primKeyValue+"      subqueryWhere:"+subqueryWhere+"          sql:"+sql);                           
                     String queryDelete = "DELETE FROM "+tableChild+" "+subqueryWhere;
                     //System.out.println(queryDelete+" "+subqueryWhere);
               int intRet = dbTransaction.transactionUpdateQuery(queryDelete,"PanelODMR.rowDeleteChildTablesAndHtmlFile htmlfile  ",true);
               if(intRet>=1)
               {
                   ret=true;    
               }
               else
               {
                  ret = false;
               }
                    if(ret)
                    {
                         listRetDeleted.add(true);
                    }
                    else
                    {
                        listRetDeleted.add(false);
                    } 
               
               
               
               
          }      
          else  // selectedDataRow==0
          {}
                        
                }//   htmlfile
                else
                {
                    
                }
              }// 

 /*         }
          catch(SQLException sqlex)
          {
                System.out.println("error: sql PanelOneDataManyRec.rowDeleteChildTables()  :"+sqlex.getMessage());
                if(VariablesGlobal.globalShowPrintStackTrace)  
                {
                      sqlex.printStackTrace();     
                }                             
          }  */           
              
            if(listRetDeleted.size()>0)  // if has table
            {
              for(int l =0; l<listRetDeleted.size();l++)
              {
                  if(Boolean.parseBoolean(listRetDeleted.get(l).toString()))
                  {
                      ret=true;
                  }
                  else
                  {
                      ret=false;
                      break;
                  }
              }
            }
            else  //   if there are no tables
            {
                ret=true;
            }
       
        return ret;
   }    
    
    
    
    private void showListPanel()
    {
       panelOneDataManyRecData.setVisible(true);
       panelEditOneDataRec.setVisible(false);
       panelEditOneDataRec.setDataHasChanged(false);
       panelTop.setVisible(true);    	
    }
    
    
    
  /*  private void calculationFromToolBarButton()
    {
          // --------------------------------------------
        
                String sqlQueryVat = "SELECT a.traderId, a.name, a.actionTypeId, a.actionTypeCode, a.myfCatId,count(ch), sum(sppv), sum(bbv)\n" +
"FROM (" +
"SELECT trader.traderId, trader.name, sxesoexoheader.actionTypeId, sxactiontype.actionTypeCode, sxactiontype.myfCatId, count(sxesoexoheader.esoexoHeaderId)AS ch, sum(sxesoexoheader.pricePreVat) AS sppv,  sum(sxesoexoline.priceBeforeVat) AS bbv, sum(sxesoexoline.vatValue), sum(sxesoexoline.valueWithVat) " +
"FROM trader,sxactiontype,sxesoexoheader,sxesoexoline " +
"WHERE trader.traderId = sxesoexoheader.traderId AND sxactiontype.actionTypeId = sxesoexoheader.actionTypeId AND sxesoexoline.esoexoHeaderId = sxesoexoheader.esoexoHeaderId" +
" AND sxactiontype.dbCompanyId = sxesoexoheader.dbCompanyId AND sxesoexoline.dbCompanyId = sxesoexoheader.dbCompanyId AND sxesoexoheader.dbCompanyId LIKE 1 AND sxactiontype.myfCatId = 2" +
" GROUP BY sxesoexoheader.esoexoHeaderId" +
" ORDER BY  trader.traderId ) AS a" +
" GROUP BY a.traderId";        
        
        
        
        
        
                EntityFilterSettings[] eFilterSettings = new EntityFilterSettings[1];
        //eFilterSettings[0]=new EntityFilterSettings("ονομασία","","string","equals","vatDocDescr","sxvatdocforperiod",null,"",-1,-1,-1,FIELD_NOCOMPLETION);
        eFilterSettings[0]=new EntityFilterSettings("ημερομηνία παραστατικών","","date","fromto","dateOfesoexo","","sxesoexoheader","",-1,-1,-1,FIELD_OBLIGATORY);

                EntityGroupOfComps[] entityGroupOfFilterComps = null; // if not null creates tabs, and nothing is shown


        
         //-----------------------------------------------
               PanelDataFilter pnlDataFilter = new PanelDataFilter(frame);
                pnlDataFilter.setEntity(eFilterSettings, entityGroupOfFilterComps, PANEL_FILTER_SEARCH, yearEnforce, panelManagement);
                
            /*    public void setEntity(EntityFilterSettings[] entityFilterSettingsIn,EntityGroupOfComps[] entityGroupOfComps, int calledFromPanel, String yearEnforceIn,
            PanelManagement panelManagementIn )//,EntityUpdateAdditional[] updateAdditionalIn)                    
                */            

  /*              DialogMulti dlgDocFilter = new DialogMulti(frame);
                dlgDocFilter.setEntity(pnlDataFilter,PANEL_TYPE_CALCULATIONDOCFILTER,"υπολογισμός",true);
                //dlgDocFilter.setPanelFilters(pnlDataFilter);
                dlgDocFilter.display();

               boolean checkIsCanceled = dlgDocFilter.getIsCancelClicked();
               if(checkIsCanceled)// == null || listUncompletedFields.size()==0)     
               {
                    //System.out.println("PanelODORData.calculationFromToolBarButton     checkToClose:"+checkIsCanceled);
               }
               else
               {
               String q =   pnlDataFilter.getSubquery(sqlQueryVat,0);// int reportgroup
               
                     db.retrieveDBDataFromQuery(q, "PanelODMRec.calculationFromToolBarButton");
                     ResultSet rsDoc = db.getRS();
                     //ResultSetMetaData rsmdDoc = db.getRSMetaData();
                     int intDocCount = db.getRecordCount();
                     
                    try
                    {
                        rsDoc.first();
                        for(int d = 1;d<=intDocCount;d++)
                        {
                           rsDoc.absolute(d);
                     //System.out.println("   strFieldWhere:"+strFieldWhere+"   strFieldValue:"+strFieldValue);
     //                String strField = rsDoc.getString(strFieldWhere);
                         
                        }

                    }
                    catch(SQLException e)
                    {
                        System.out.println("PanelODMRec.calculationFromToolBarButton   "+e.getMessage());
                        e.printStackTrace();
                    }
                     
                     closeDB();
                
               }                         
            
                
    }*/
    
    
    
    
    /*
     * like refresh
     */
    private void filter()
    {
      //this.setCursor(new Cursor(Cursor.WAIT_CURSOR));

      String selPrimKeyValue = panelOneDataManyRecData.getPrimKeyValue();
      String selPrimKey = panelOneDataManyRecData.getPrimKey();

    //System.out.println("  panelODMR.filter  query "+query+"   queryReadOnly:"+queryReadOnly);
      queryReadAfterFilter = panelOneDataManyRecData.filterForReadOnlyTable(panelDataFilter.getSubquery(queryReadOnly/*,-1*/));// (-1 all) filters apply to all the groups of data
    
     boolean wasNewRec = panelEditOneDataRec.getWasNewRecord();
      
      if(isNewRec || wasNewRec)
      {
          selPrimKeyValue = panelEditOneDataRec.getPkValueAfterNewIsInserted();
     System.out.println("PanelOneDataManyRec.filter  ========== A selPrimKey:"+selPrimKey+" : selPrimKeyValue:"+selPrimKeyValue+"     isNewRec:"+isNewRec);//+"  wasNewRec:"+wasNewRec);
          if(selPrimKeyValue==null || selPrimKeyValue.equalsIgnoreCase(""))
          {
              selPrimKeyValue = panelOneDataManyRecData.getPrimKeyValue();
          }
          panelEditOneDataRec.setWasNewRecordFalse();
      }
     System.out.println("PanelOneDataManyRec.filter  ========== B  selPrimKey:"+selPrimKey+" : selPrimKeyValue:"+selPrimKeyValue+"     isNewRec:"+isNewRec);//+"  wasNewRec:"+wasNewRec);

       panelOneDataManyRecData.setSelectedTableRow(selPrimKey,selPrimKeyValue);
          
      //isNewRec = false;
   	    //    this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
     ///utilsGui.showMessageInfo("filter . "+PanelOneDataManyRecData.getRowCountFromReadOnlyTable());
    }

    // get from PanelOneDataManyRecData for DialogLookUp
    public void setSelectedTableRow(String keyField, String selectedKeyValue, boolean showToolbar)
   {
   	 if(showToolbar)
   	 {
   	 	toolBarData.setVisible(true);
   	 }
   	 else
   	 {
   	 	toolBarData.setVisible(false);
   	 }
             panelOneDataManyRecData.setSelectedTableRow(keyField, selectedKeyValue);

   }
    // get from PanelOneDataManyRecData for DialogLookUp
   public int getSelectedTableRow()
   {
   	    return panelOneDataManyRecData.getSelectedTableRow();
   }
    // get from PanelOneDataManyRecData for DialogLookUp
   public String getSelectedRowPrimaryKeyValue(String queryIn, String primKey, int intColDescription)
   {
   	   return panelOneDataManyRecData.getSelectedRowPrimaryKeyValue( queryIn, primKey, intColDescription);
   }

  // get from PanelOneDataManyRecData for DialogLookUp
  // getSelectedRowPrimaryKeyValue should be called before 
   public String getSelectedRowDescriptionValue()
   {
   	   return panelOneDataManyRecData.getColDescriptionValue();
   }
    
    private void displayMessageDialog(String title, String message)
    {
    	JOptionPane.showMessageDialog(this, message, title,JOptionPane.INFORMATION_MESSAGE);
    }  

    private void displayPanelReport()
    {
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    	//System.out.println("odmr readonly query "+queryReadOnly);
    	panelOneDataManyRecData.displayPanelReport(entity,queryReadOnly,title,true,entityFilterSettings,entityPanel,fieldsOnTitle,fieldsOnTitleCaption,entityGroupOfComps/*,yearEnforce*/);//showSummaryPage should be false
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        
    }  

    private void displayDialogTablePreferences()
    {
        panelOneDataManyRecData.displayDialogTablePreferences();
        
        
        if (panelDataFilter.checkIfFieldsAreCompleted())
        {
            filter();  // like refresh    
        }        
        
    }
    
    private void selectMenuView(Component menu, String text)
    {
    	int length =1000;
    	popupMenuView.setSelected(menu);
    	String shortText="";
    	if(text.length()>length)
    	{
    	    shortText=text.substring(0,length-1);
    	    shortText=shortText+"..";	
    	}
    	else
    	{
    		shortText=text;
    	}

    	//btnView.setText("<html>"+shortText+"</html>");
    	//btnView.setToolTipText(strView+": "+text);
    }
    
    // exists in PanelOneDataManyRec, PanelOneDataOneRec, PanelTwoDataOneRec and in PanelTwoDataManyRec differentiated
    /*private void displayDialogTemplateNew()
    {
    	
    	displayDialogEdit(true,false);
    }*/
    
    // exists in PanelOneDataManyRec, PanelOneDataOneRec, PanelTwoDataOneRec and in PanelTwoDataManyRec differentiated
    /*private void displayDialogTemplateSelect()
    {
    	
    	PanelOneDataManyRec panelOneDataManyRec = new PanelOneDataManyRec (frame);
    	//panelOneDataManyRec.setEntity()
    	
    	DialogMulti dlg = new DialogMulti(frame);
        dlg.setEntity(panelOneDataManyRec,PANEL_TYPE_ONEDATAMANYREC, "επιλογή προτύπου "+strOfOne);
        dlg.display();
    	
    }*/
    
    /*private void showMenuTemplates()
    {
          popupMenuTemplates.show(btnTemplates, 0, btnTemplates.getY()+ btnTemplates.getHeight());
    }*/
    
    /*private void showMenuView()
    {
          popupMenuView.show(btnView, 0, btnView.getY()+ btnView.getHeight());
    }*/


    /*private void showMenuTools()
    {
          popupMenuTools.show(btnTools, 0, btnTools.getY()+ btnTools.getHeight());
    }*/


   private void exportTo(String type)
   {
   	  panelOneDataManyRecData.exportTo(type);
   }
  
    
  public boolean returnBackAsk()
  {
      
      
      boolean ret = panelEditOneDataRec.toClosePanelOrGoBackAskIfDataChanged();
      panelOneDataManyRecData.closeDB();
      db.releaseConnectionRs();
      db.releaseConnectionRsmd();
      
      return ret;
  }          
    
    
  public boolean closePanelAsk()
  { // true close panel, false not close panel
  	boolean ret = panelEditOneDataRec.toClosePanelOrGoBackAskIfDataChanged();// closes panelonedataonerec and twodataonerec
  	
  	panelOneDataManyRecData.closeDB();
        
      db.releaseConnectionRs();
      db.releaseConnectionRsmd();        
  	
  	return ret;
  }
  
  /*
   * 
   * called from this class
   * 
   */
  private class PanelActionListenerSaveAndShowList implements ActionListener
  {// look at     public void addActionListener(ActionListener al) in panelPrintPreview and PanelReportSettings
    // here:
    //      	panelReportSettings.addActionListener(new PanelActionListener());
    //    	panelPrintPreview.addActionListener(new PanelActionListener());
    

        @Override
    public void actionPerformed(ActionEvent e)
    {
         //System.out.println("PanelODMR.PanelActionListenerShowList actionPerformed Action \"" + e.getActionCommand() + "\" performed from within PanelOneDataManyRec class");
      

        if(panelEditOneDataRec.saveAndClose()) // if has no messages
        {
          showListPanel();
          if (panelDataFilter.checkIfFieldsAreCompleted())
          {
             filter();  // like refresh    
          }
        }
    }
  }
 
  
    private class PanelActionListenerBackToListAskForSave implements ActionListener
  {// look at     public void addActionListener(ActionListener al) in panelPrintPreview and PanelReportSettings
    // here:
    //      	panelReportSettings.addActionListener(new PanelActionListener());
    //    	panelPrintPreview.addActionListener(new PanelActionListener());
    

        @Override
    public void actionPerformed(ActionEvent e)
    {
         //System.out.println("PanelODMR.PanelActionListenerShowList actionPerformed Action \"" + e.getActionCommand() + "\" performed from within PanelOneDataManyRec class");
         

        if(returnBackAsk())
        {
          showListPanel();
          if (panelDataFilter.checkIfFieldsAreCompleted())
          {
            filter();  // like refresh    
          }
        }
    }
  }
  
  
  
  /*
   * 
   * called from this class
   * 
   */
  private class PanelActionListenerBackToList implements ActionListener
  {// look at     public void addActionListener(ActionListener al) in panelPrintPreview and PanelReportSettings
    // here:
    //      	panelReportSettings.addActionListener(new PanelActionListener());
    //    	panelPrintPreview.addActionListener(new PanelActionListener());
    

        @Override
    public void actionPerformed(ActionEvent e)
    {
         //System.out.println("PanelODMR.PanelActionListenerShowList actionPerformed Action \"" + e.getActionCommand() + "\" performed from within PanelOneDataManyRec class");
       
        panelOneDataManyRecData.closeDB();
         showListPanel();
        if (panelDataFilter.checkIfFieldsAreCompleted())
        {
            filter();  // like refresh    
        } 
    }
  }
  
  
  private class TableMouseListener implements MouseListener
  { // look at     public void addListSelectionListener(ListSelectionListener al) in PanelOneDataManyRecData
    // same as PanelTDMR and PanelODMR and DialogLookup
      public void mouseClicked(MouseEvent e)
      {
                if (e.getClickCount() == 2) // make it 2 for doubleclick
                {   
                    //retrievePrimKeyValue( query, selectedTableRow, primKey);
                    //System.out.println("panelODMR.selection table double clicked.");
                    if(isLookUpDialog)
                    {
                    	btnSet.doClick();
                    }
                    else
                    {
                        btnEdit.doClick();  
                    }
                    
                }
      } 
      public void mouseExited(MouseEvent e)
      {
      }
      public void mouseEntered(MouseEvent e)
      {
      }      
      public void mouseReleased(MouseEvent e)
      {
      }       
      public void mousePressed(MouseEvent e)
      {	
      }     
  } 
    
    // for lookup dialog
    public void addLookUpTableActionListener(ActionListener al)
    {
        //hidden component
        btnSet.addActionListener(al);

    }
/*
   public void showPrevRow()   
   { 
       try
         { 
             db.retrievePrevRow(rs);
         }
         catch(Exception e)
         {  System.out.println("Error .showPrevRow()" + e);
         }
   }

   public void showNextRow()   
   { 
       try
         { 
             db.retrieveNextRow(rs);
         }
         catch(Exception e)
         {  System.out.println("Error .showNextRow()" + e);
         }
   } */

  /* class  ActionPrintPreview extends AbstractAction                 
   {       
        public ActionPrintPreview()
        {        }    	
    	public void actionPerformed(ActionEvent e)
      	{          btnPrintPreview.doClick();        }    	
    }  */              


    
   class  ActionNewRec extends AbstractAction                 
   {       
        public ActionNewRec()
        {        }    	
    	public void actionPerformed(ActionEvent e)
      	{          btnInsert.doClick();        }    	
    }                

   /*class  ActionNewRecFromCopy extends AbstractAction                 
   {       
        public ActionNewRecFromCopy()
        {        }    	
    	public void actionPerformed(ActionEvent e)
      	{          btnInsertFromCopy.doClick();        }    	
    } */  

   class  ActionEditRec extends AbstractAction                 
   {       
        public ActionEditRec()
        {        }	
    	public void actionPerformed(ActionEvent e)
      	{          btnEdit.doClick();        }    	
    }                

   class  ActionDelRec extends AbstractAction                 
   {       
        public ActionDelRec()
        {        }
    	public void actionPerformed(ActionEvent e)
      	{          btnDelete.doClick();        }    	
    }                

   class  ActionPrintPreview extends AbstractAction                 
   {       
        public ActionPrintPreview()
        {        }
    	public void actionPerformed(ActionEvent e)
      	{          btnPrintPreview.doClick();        }    	
    } 
   /*class  ActionExport extends AbstractAction                 
   {     
        public ActionExport()
        {        }
    	public void actionPerformed(ActionEvent e)
      	{         btnExport.doClick();        }   	
    } */                

   /*class  ActionTablePreferences extends AbstractAction                 
   {     
        public ActionTablePreferences()
        {        }
    	public void actionPerformed(ActionEvent e)
      	{         btnPreferences.doClick();        }    	
    }   */             

}