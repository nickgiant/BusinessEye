package com.tool.gui;

import com.tool.rpt.PanelReportSettingsPreview;
import com.tool.model.EntityGroupOfComps;
import com.tool.model.EntityGroupOfPanels;
import com.tool.jdbc.*;
import com.tool.gui.*;
import com.tool.guicomps.*;

import com.tool.utils.*;
import com.tool.model.*;
import java.awt.*;

import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Action;
import javax.swing.AbstractAction;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.border.BevelBorder;
import javax.swing.text.JTextComponent;

 public class PanelOneDataOneRec extends JxPanel implements Constants
 {
       //private EntityGroupOfPanels[] entityGroupOfPanels;
        //private EntityPanel[] entityPanelArray;
        private JxPanel panelMain;
        public JButton btnSaveAndShowList;
        public JButton btnBackToList;
        private JButton btnInsert;
        private JButtonListMenu btnSelectTemplate;
        private JPopupMenu popupMenuTemplates;
              
        
        //private JButton btnSaveAsTemplate;
        private JButton btnSaveAndNew;
        
        private JButton btnInsertFromCopy;
        private JButton btnDelete;
        private JButton btnSave;
        private JButton btnEditPreferences;
        private JButton btnPrevious;
        private JButton btnNext;
        private JButton btnCalculation;
        
        //private JButton btnFind;
       //private JButtonListMenu btnTemplates;
       // private JMenuItem mItemTemplatesSelect;
       // private JMenuItem mItemTemplatesSaveAs;         
        private JButton btnPrintPreview;
        //private JButton btnPrint;
        /*private JLabel lblIcoSeparator1 ;
        private JLabel lblIcoSeparator2 ;
        private JLabel lblIcoSeparator3 ;
        private JLabel lblIcoSeparator4 ;
        private JLabel lblIcoSeparator5 ;*/
       // private JPopupMenu popupMenuTemplates = new JPopupMenu(); // the only from toolbar
        
    private BorderLayout layoutMultiData;
    private JLabel lblTitle;
   
    private JScrollPane scrollpaneTable  = new JScrollPane();
    private PanelOneDataOneRecData panelOneDataOneRecData;
    private JPanel panelTop;
    private ToolBarData toolBarData;
    private boolean isNewRec;
    private boolean wasNewRec =false;
    private boolean isNewRecFromCopy;
    private PanelGradient paneTitle;
    private String title;
    private String[]fieldsOnTitle;
    private String[] fieldsOnTitleCaption;
    private String query;
    private String queryReadOnly;
    private ResultSet rs;
    private Database db;
    private String primKeyDb;
    private String primKeyValue;
    private UtilsGui utilsGui;
    private UtilsString utilsString;
    private JFrame frame;
    private PanelManagement panelManagement;
    private PanelEditOneDataRec  panelEditOneDataRec;
    private String yearEnforce;
    private ArrayList listPanelOneDataOneRecData;
    //private ArrayList listPanelOneDataOneRec;
    private JxPanel panelHasAllOfODORData;
    private JxPanel   panelMainCard;
    private PanelOneDataOneRecData panelOneDataOneRecDataSetEntity;
    private ArrayList listLengthDbFieldsInGroupOfPanels;
    private EntityDBFields[] dbFieldsAll;
    private EntityPanel entityPanel;
    private UtilsPanelReport utilsPanelReport;
    private UtilsDate utilsDate;
    private String titleCaptionHtml;
    private String titleCaptionStr;
    //private EntityReport entityReportForm;
    private String[] primKeys;
    private String[] primKeysCaption;
    private int primKeysCount;
    private String[] primKeysValue;
   // private String formGlobalTableToGet1;
   // private String formGlobalTableToApply1;
    //private String formGlobalField1;
    //private int rFinal = 0;//  for showTemplates
    private EntityCalculate entityCalculate;
    //private String primKeyOfNewRecord="";
    private EntityTemplate entityTemplate;
    
    public PanelOneDataOneRec(JFrame frame)  
    {
            try
           {     initialize(frame);   }
           catch (Exception e)
           {   e.printStackTrace();    }
    }
    
    private void initialize(JFrame frameIn) throws Exception
    {
    	setOpaque(false);
        layoutMultiData = new BorderLayout();
        this.setLayout(layoutMultiData);
        db= new Database();
        utilsGui = new UtilsGui();
        utilsString = new UtilsString();
        utilsDate = new UtilsDate();
        frame =frameIn;
        utilsPanelReport=new UtilsPanelReport();
        
        
       panelMainCard= new JxPanel();
       panelMainCard.setOpaque(false);
       panelMainCard.setLayout(new CardLayout());
       panelMainCard.setBorder(null);        
        
        
        listPanelOneDataOneRecData= new ArrayList();
        
        btnSaveAndShowList = new JButton();
        btnBackToList = new JButton();
        
        toolBarData = new ToolBarData();
        toolBarData.setFocusable(false);
        //panelTop.setLayout(new GridLayout(0,1));
        
        //listPanelOneDataOneRec  = new ArrayList();
        //this.addComponentListener(new CompShowListener());

        
        scrollpaneTable.setOpaque(false);
        
        panelOneDataOneRecData = new PanelOneDataOneRecData(frame);
        panelOneDataOneRecData.setLayout(new BorderLayout());
        
        lblTitle = new JLabel();

        panelHasAllOfODORData = new JxPanel();
        panelHasAllOfODORData.setLayout(new BorderLayout());
        
        JScrollPane scrollpanePanelHasAllOfODORData  = new JScrollPane();
        scrollpanePanelHasAllOfODORData.setOpaque(false);
        scrollpanePanelHasAllOfODORData.setViewportView(panelHasAllOfODORData);
        
        lblTitle.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
       //lblTitle.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD));
        //lblTitle.setBackground(java.awt.SystemColor.activeCaption);//panel.getBackground().brighter());        
        //lblTitle.setFont(new java.awt.Font("Century", 1, 16));
        lblTitle.setForeground(CLR_TITLE_CAPTION);//Color.white);
        lblTitle.setFocusable(false);
        lblTitle.setOpaque(false);

        paneTitle = new PanelGradient(CLR_TITLE_BACKGROUND_START,CLR_TITLE_BACKGROUND_END,16);//new Color(0, 0, 0, 0),this.getBackground().darker().darker().darker().darker(),26);//new PanelTitle(new Color(0, 0, 0, 0),this.getBackground().darker().darker().darker().darker(),Color.white,"title");
        paneTitle.add(lblTitle);

	panelTop = new JPanel();
	panelTop.setOpaque(false);
        panelTop.setLayout(new BorderLayout());
        panelTop.add(paneTitle,BorderLayout.CENTER);
        panelTop.add(toolBarData,BorderLayout.PAGE_START); 

        
        panelMain = new JxPanel();
        panelMain.setOpaque(false);
        panelMain.setLayout(new BorderLayout());
        
       // panelHasAllOfODORData.add(panelMainCard,BorderLayout.CENTER);
        
        panelMain.add(panelTop, BorderLayout.PAGE_START);
        panelMain.add(scrollpanePanelHasAllOfODORData, BorderLayout.CENTER);//scrollpaneTable, BorderLayout.CENTER);  
      
        panelOneDataOneRecDataSetEntity = new PanelOneDataOneRecData(frame);
     	
         
       if (VariablesGlobal.globalShowInitialisations)
       {    System.out.println("panelODOR initialized");   }   
    }
    
    /*
     * 
     * called by PanelEditOneDataRec.addVisibleMenuButtonsAndPanels
     */
    private PanelOneDataOneRecData loadAndGetEntityPanelsOneDataOnRecData(EntityPanel entityPanelIn,/*boolean isInsidePanelTwoData,*/ String titleIn, /*String entityIn,*/
     /*EntityDBFields[] dbFieldsIn, EntityGroupOfComps[] entityGroupOfCompsIn,EntityGroupOfPanels[] entityGroupOfPanelsIn, String primKeyIn,*/ 
     /*String primKeyValueIn,String primKeyDbIn, */String queryIn, boolean isNewRecIn, boolean isNewRecFromCopyIn, /*boolean isMasterUniqueIn,*/ 
     /*String[] sql2WhereFieldIn, String[] sql2WhereValueIn, String query2In,String entity2In, boolean isManyIn,*/ boolean isEditable,String yearEnforceIn, 
     /*ImageIcon icoIn,*/ArrayList listLengthDbFieldsInGroupOfPanelsIn, int intGroupOfPanelsToShow/*,PanelManagement panelManagementIn*/,int whereIsCalledFrom//,
    /*String formGlobalTableToGet1In, String formGlobalTableToApply1In*/)
    {
        entityPanel=entityPanelIn;
        EntityGroupOfPanels[] entityGroupOfPanels=entityPanel.getEntityGroupOfPanels();
        EntityGroupOfComps[] entityGroupOfComps = entityPanel.getEntityGroupOfComps();
        String entityType = entityPanel.getType(); 
        String entity = entityPanel.getEntity();
        EntityDBFields[] fields = entityPanel.getDBFields(); 
        String primKey = entityPanel.getPrimKey();
        //String formGlobalTableToGet1In =  entityPanel.g
        //boolean isMasterUnique = entityPanel.getIsMasterUnique();
        //boolean isMany = entityPanel.getIsMany();
        
        ImageIcon icon = entityPanel.getIco();
        
        
        
        
        
        
        
       //public static int IS_CALLED_BY_MULTIPLE_TABLES_MDMR = 0;
       //public static int IS_CALLED_BY_ONE_TABLE_ODOR = 1;
        
        if(whereIsCalledFrom == IS_CALLED_BY_ONE_TABLE_ODOR)// 1
        {
            //fields  = entityPanel.getDBFields();
            EntityDBFields[] fieldsNew  = entityPanel.getDBFields()[0].getDbChildFields();
           // EntityGroupOfPanels[] entityGroupOfPanels=entityPanel.getEntityGroupOfPanels();
            if(fieldsNew == null)// is null when the list is shown
            {
                 fields  = entityPanel.getDBFields();    
            }
            else
            {
                fields = fieldsNew;
            }
        }
        else if(whereIsCalledFrom == IS_CALLED_BY_MULTIPLE_TABLES_MDMR)// 0
        {
            
            fields  = entityPanel.getDBFields();
            //fields  = entityPanel.getDBFields()[0].getDbChildFields();
        }
        else
        {
            System.out.println("  error UNKNOWN LSE    PanelODOR .loadAndGetEntityPanelsOneDataOnRecData   ---   titleIn:"+titleIn+"    fields.length:"+fields.length+"   whereIsCalledFrom:"+whereIsCalledFrom+"    fields.length:"+fields.length);
        }
        
   //     System.out.println("  ooooo    PanelODOR .loadAndGetEntityPanelsOneDataOnRecData   ---   titleIn:"+titleIn+"    fields:"+fields+"   whereIsCalledFrom:"+whereIsCalledFrom);
        
        
                        
       PanelOneDataOneRecData panelOneDataOneRecDataNew = new PanelOneDataOneRecData(frame);
       panelOneDataOneRecDataNew.setLayout(new BorderLayout());

       panelOneDataOneRecDataNew.setEntity(entityPanel,titleIn, entity ,fields,entityGroupOfComps,entityGroupOfPanels,primKey, primKeyValue, primKeyDb,/*formGlobalTableToGet1In,
               formGlobalTableToApply1In,*/queryIn, isNewRec, isNewRecFromCopy, /*isMasterUnique,null,null,*//*for panelTwo null, null,isMany,*/true, yearEnforceIn,icon,
               listLengthDbFieldsInGroupOfPanelsIn, intGroupOfPanelsToShow, panelManagement);//,entityReportForm); 
       
    //System.out.println("PanelODOR.loadAndGetEntityPanelsOneDataOnRecData  OOOO   ("+intGroupOfPanelsToShow+") type="+entityType+" title="+titleIn +"     queryIn:"+queryIn+"     entityPanel.getQuery():"+ entityPanel.getQuery());
       

         //System.out.println("PanelODOR.loadAndGetEntityPanelsOneDataOnRecData ----o----> ("+intGroupOfPanelsToShow+") title="+titleIn+"  listPanelOneDataOneRecData.size()="+listPanelOneDataOneRecData.size());       
       
        return panelOneDataOneRecDataNew;
    }
    
    
    /*
    *
    *  
    */
    public void setActivePanel(String captionIn,int intActivePanel)
    {
        //System.out.println("PanelODOR.setActivePanel actionPerformed  caption="+captionIn+"    panelMainCard.getComponentCount="+panelMainCard.getComponentCount());
                     CardLayout cl = (CardLayout)(panelMainCard.getLayout());
                     cl.show(panelMainCard, captionIn);                 
        

              PanelOneDataOneRecData pnl = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(intActivePanel);
               pnl.setFocusInPageComponent();              

           /*Component c[] = panelMainCard.getComponents();
           for(int p= 0;p<panelMainCard.getComponentCount();p++)
           {
           	  System.out.println("PanelEditOneDataRec.setActivePanel "+p+"  panelMainCard Count "+panelMainCard.getComponentCount());//" name="+c[p].getName());
           } */              
 
    }
    
    public int getCountOfPanelODORData()
    {
         //System.out.println("PanelODOR.getCountOfPanelODORData size"+listPanelOneDataOneRecData.size());
        return listPanelOneDataOneRecData.size();
    }
    
    /*
     * 
     * called by PanelEditOneRec.addVisibleMenuButtonsAndPanels and PanelMDMR
     */
    public int setEntity(String entityIn,/*EntityPanel[] entityPanelArrayIn,*/EntityPanel entityPanelIn, String primKeyValueIn,String queryIn,
            String titleIn, boolean isNewRecIn, boolean isNewRecFromCopyIn/*,String formGlobalTableToGet1In, String formGlobalTableToApply1In*/, String[]fieldsOnTitleIn,  
            String[] fieldsOnTitleCaptionIn, boolean showOnlySaveNPrintButton,  String yearEnforceIn, boolean isShowBackToListButtons, boolean showToolBar, ImageIcon icon, 
            PanelManagement panelManagementIn, PanelEditOneDataRec  panelEditOneDataRecIn, int whereIsCalledFromIn)
    {
        int selectedRow=0;// when 0 do not displaydialog
        entityPanel=entityPanelIn;
        
        EntityGroupOfPanels[] entityGroupOfPanels=entityPanel.getEntityGroupOfPanels();//entityGroupOfPanelsIn;
        queryReadOnly =queryIn;
        
        query = entityPanel.getQuery(); 
        primKeyDb = entityPanel.getPrimKeyDb();
        String entityType = entityPanel.getType();
        dbFieldsAll = entityPanel.getDBFields();
       entityCalculate =  entityPanel.getEntityCalculate();
       entityTemplate = entityPanel.getEntityTemplate();
       
        //formGlobalField1=formGlobalField1In;
    	title=titleIn;
    	//primKeyDb=primKeyDbIn;
    	primKeyValue=primKeyValueIn;
    	fieldsOnTitle=fieldsOnTitleIn;
    	fieldsOnTitleCaption=fieldsOnTitleCaptionIn;
    	panelManagement=panelManagementIn;
        panelEditOneDataRec =  panelEditOneDataRecIn;
        yearEnforce=yearEnforceIn;
        
    	isNewRec=isNewRecIn;
        
    	isNewRecFromCopy=isNewRecFromCopyIn;
            if (this != null)
            { this.removeAll(); }//erases all components placed during initialization
        toolBarData.setVisible(showToolBar);
        
        entityCalculate = entityPanel.getEntityCalculate();
     
        if(entityCalculate!= null && btnCalculation.isVisible())
        {
        btnCalculation.setText("<html>"+entityCalculate.getCaption()+"</html>");//entityCalculate.getCaption())
        btnCalculation.setToolTipText(entityCalculate.getCaption());
        }
        //lblTitle.setBackground(java.awt.SystemColor.activeCaption);
      //listPanelOneDataOneRec.clear();
    //System.out.println("PanelODOR.setEntity ----ooooooooooooooooo------     entityType:"+entityType+"          entityPanel.getEntityReportForm bands 0 :"+entityPanel.getEntityReportForm().getEntityReportBands()[0]+"         primKeyValue:"+primKeyValue+"     queryReadOnly="+queryReadOnly+"     query"+query  );
    //System.out.println("PanelODOR.setEntity ----ooooooooooooooooo------     entityType:"+entityType+"           primKeyValue:"+primKeyValue+"    ---    queryReadOnly="+queryReadOnly+"      ---      query"+query  );
      
   
    
    if(entityTemplate==null)
    {
        btnSelectTemplate.setVisible(false);
    }
    else 
    {
         btnSelectTemplate.setVisible(true);
    }
    
    if (showOnlySaveNPrintButton)
      {
      	toolBarData.showOnlySaveNPrintButton();
      }
      
      if(entityCalculate==null)
      {
          toolBarData.showBtnCalculation(false);
      }
      else
      {
           toolBarData.showBtnCalculation(true);
      }
      
        if(entityPanel.getEntityReportForm() != null)
        {
           btnPrintPreview.setVisible(true);
        }      
        else
        {
            btnPrintPreview.setVisible(false);
        }
      
      if(panelEditOneDataRec != null)  
      {
             boolean[] isVisibleType={true,true,true};
             panelEditOneDataRec.setMenuButtonsVisible(isVisibleType); 
      } 
      
      if(isShowBackToListButtons)
      {
      	 btnSaveAndShowList.setVisible(true);
         btnBackToList.setVisible(true);
      	 //lblIcoSeparator2.setVisible(true);
      }
      else
      {
         btnSaveAndShowList.setVisible(false);  
         btnBackToList.setVisible(false); 
         //lblIcoSeparator2.setVisible(false);  	
      }
      //System.out.println("paneTitle"+title);
       if(title==null)
       { 
          paneTitle.setVisible(false);
       }
       else
       {
       	  paneTitle.setVisible(true);
       	  //lblTitle.setText(title);
       }
       
       if(icon==null)
       {
       	  lblTitle.setIcon(ICO_EDIT16);
       }
       else
       {
         lblTitle.setIcon(icon);
       }
       //System.out.println("PanelOneDataOneRec.setTitle '"+entityPanel.getEntity()+"'   queryReadOnly:"+queryReadOnly);
       queryReadOnly=utilsString.removeCaptionsFromQuerySubStringSelect(queryReadOnly);

        if(!isNewRec)
        {
            String queryOfWhere =  utilsString.getQueryWhere(queryReadOnly);
            String queryBeforeWhere= utilsString.getQueryBeforeWhere(queryReadOnly);//   query with only select and from
           //String queryWithoutOrderby = utilsString.getQueryWithoutOrderby(queryReadOnly);
           String queryWithOutGroupByAndOrderBy = utilsString.getQueryWithoutGroupByOrOrderBy(queryReadOnly);
	    String queryOrderby = utilsString.getOrderbySubQuery(queryReadOnly);
            String queryGroupby = utilsString.getQueryGroupby(queryReadOnly);
            queryReadOnly = queryBeforeWhere+"  "+queryOfWhere+"  "+queryGroupby+" "+queryOrderby;
           
         if(VariablesGlobal.globalShowSelectRecord) 
         {
         System.out.println(" --O-->  PanelOneDataOneRec.setEntity     queryWithoutWhere:"+queryBeforeWhere+"     queryOfWhere:"+queryOfWhere+"      queryGroupby:"+queryGroupby+"      queryOrderby:"+queryOrderby + "   queryReadOnly:"+queryReadOnly);
         }
          //System.out.println(" --O-->  PanelOneDataOneRec.setEntity   entityIn:"+entityIn+" '"+entityPanel.getEntity()+"'     isNewRec:"+isNewRec+"     primKeyValue:"+primKeyValue+"  queryReadOnly:"+queryReadOnly+"  query:"+query);
            //try
            //{
            // also in printPreviewForm
            db.retrieveDBDataFromQuery(queryReadOnly,"PanelOneDataOneRec.setEntity"); // use queryReadOnly, not query because it has the 'where' clause with pk to limit
                                                                               //the rows to find the selectedRow
   	    rs=db.getRS();

           // }
           // catch (SQLException sqlex)
           // {
           //     sqlex.printStackTrace();
                
           // }
         if(VariablesGlobal.globalShowSelectRecord) 
         {
          System.out.println("  PanelOneDataOneRec.setEntity  OOooooooOO     "+entityPanel.getEntity()+".primKeyDb:"+primKeyDb+"=primKeyValue:"+primKeyValue+"    queryReadOnly:"+queryReadOnly);
         }
          selectedRow = utilsPanelReport.getRowForPrimKey("PanelOneDataOneRec.setEntity",queryReadOnly,rs,dbFieldsAll,primKeyDb,primKeyValue);
            
           /* if (selectedRow==0)    
            {
                
            }
            else
            {*/
             
            //System.out.println(" -->  PanelOneDataOneRec.setTitle '"+entityPanel.getEntity()+"'  isNewRec:"+isNewRec+" primKeyValue:"+primKeyValue);
            utilsPanelReport.retrievePrimKeyValueForOnePK( queryReadOnly, selectedRow, null,dbFieldsAll,true,/*primKeyIn,intColumnOfDescriptionIn,
             sql2WhereField, sql2WhereValue,*/ entityPanel.getEntity(), /*tableModelReadOnly,*/ primKeyDb);
                       
              primKeys = utilsPanelReport.getPrimKeys();
             primKeysCaption = utilsPanelReport.getPrimKeysCaption();
         // System.out.println("-->  PanelOneDataOneRec.setEntity  entityIn:"+entityIn+" '"+entityPanel.getEntity()+"' selectedRow:"+selectedRow+"  primKeys.length:"+primKeys.length); 
             primKeysCount = primKeys.length;
             primKeysValue = utilsPanelReport.getPrimKeysValue();              
            
             closeDB();  
             
             String queryWhere="";
               for(int p = 0; p<primKeysCount;p++)
               {
                   queryWhere=queryWhere+" "+entityPanel.getEntity()+"."+primKeys[p]+" = "+primKeysValue[p];  
                   if(primKeysCount==1 || p==primKeysCount-1)
                   {
                       
                   }
                   else
                   {
                       queryWhere=queryWhere+" AND ";
                   }
                   //System.out.println(" ---O--->  PanelOneDataOneRec.setEntity '"+entityPanel.getEntity()+"'    primKeysCount:"+primKeysCount+"    "+primKeys[p]+"="+primKeysValue[p]+"         queryWhere:"+queryWhere+"            queryReadOnly:"+queryReadOnly+"             query:"+query); 
                   
               }
             
               
         
            //----------- for query  
         String qWithOutOrderBy = utilsString.getQueryWithoutGroupByOrOrderBy(query);
         /*if(qWithOutOrderBy.equalsIgnoreCase(""))
         {
            qWithOutOrderBy = utilsString.getQueryWithoutOrderby(query);    
         }
         */
         if(qWithOutOrderBy.equalsIgnoreCase("") || queryWhere.equalsIgnoreCase(""))// check if is correct:  || queryWhere.equalsIgnoreCase("")
         {    

         }
         else
         {
            String  qIn="";     
            if(utilsString.hasQueryWhere(qWithOutOrderBy))
            {
              qIn= qWithOutOrderBy +" AND "+queryWhere;  
            }
            else
            {
              qIn= qWithOutOrderBy +" WHERE "+queryWhere;  
            }
               
         
             String qOrderBy = utilsString.getGroupbyAndOrderbySubQuery(query);
             String qq= qIn+" "+qOrderBy;             
             
             
              query = utilsString.removeCaptionsFromQuerySubStringSelect(qq);
         if(VariablesGlobal.globalShowSelectRecord) 
         {              
              System.out.println("   PanelOneDataOneRec.setEntity   A-->>>>    '"+entityPanel.getEntity()+"'     qIn:"+qIn+"    qOrderBy:"+qOrderBy+"           queryWhere:"+queryWhere+"            queryReadOnly:"+queryReadOnly+"             query:"+query); 
         }
         }
         
         
       
        //---------------- for query read only  
          String  q="";     
           String qReadOnlyWithOutGroupByAndOrderBy = utilsString.getQueryWithoutGroupByOrOrderBy(queryReadOnly);
          /* if(qReadOnlyWithOutGroupByAndOrderBy.equalsIgnoreCase(""))
           {
               qReadOnlyWithOutGroupByAndOrderBy = utilsString.getQueryWithoutOrderby(queryReadOnly); 
           }*/
           
         if(qReadOnlyWithOutGroupByAndOrderBy.equalsIgnoreCase("") || queryWhere.equalsIgnoreCase(""))  // check if is correct:  || queryWhere.equalsIgnoreCase("")
         {    

         }
         else
         {   
            if(utilsString.hasQueryWhere(qReadOnlyWithOutGroupByAndOrderBy))
            {
              q= qReadOnlyWithOutGroupByAndOrderBy +" AND "+queryWhere;
            }
            else
            {
              q= qReadOnlyWithOutGroupByAndOrderBy +" WHERE "+queryWhere;
            }          
          
         String qOrderByAndGroupByReadOnly = utilsString.getGroupbyAndOrderbySubQuery(queryReadOnly);
         String qReadOnly= q+" "+qOrderByAndGroupByReadOnly;          
    
           queryReadOnly = utilsString.removeCaptionsFromQuerySubStringSelect(qReadOnly);
         if(VariablesGlobal.globalShowSelectRecord) 
         {           
           System.out.println("    PanelOneDataOneRec.setEntity   -- B-->>>>    entity:"+entityPanel.getEntity()+"     queryReadOnly:"+qReadOnly+"      q:"+q+"      queryReadOnly:"+queryReadOnly+"      query:"+query); 
         }
         }
           
          
          
          //prepare in order to be used later in panelOneDataOneRecDataSetEntity = this.loadAndGetEntityPanelsOneDataOnRecData
           // }// line == 0
          
        } // if !isNewRec 
        //System.out.println(" ------->  PanelOneDataOneRec.setEntity    isNewRec:"+isNewRec+"       '"+entityPanel.getEntity()+"'    primKeyValue:"+primKeyValue+"    query:"+query+"        queryReadOnly"+queryReadOnly); 
       //panelHasAllOfODORData.removeAll();
      // System.out.println("PanelODOR.setEntity   entityPanel.length="+entityPanel.length);
       //JxPanel panelHasAllOfODORData = new JxPanel();
       panelHasAllOfODORData.removeAll();
        listPanelOneDataOneRecData.clear();
        
      
       if (entityGroupOfPanels!=null)
       {
           //listPanelOneDataOneRec  = new ArrayList();
           
            if(entityType.equalsIgnoreCase("ODOR"))// || entityType.equalsIgnoreCase("MDMR"))  //MDMR
            {
                  listLengthDbFieldsInGroupOfPanels = new ArrayList();
                  
             EntityGroupOfComps[] entityGroupOfComps = entityPanel.getEntityGroupOfComps()  ;  
             //EntityDBFields[] dbFieldsAll = entityPanel.getDBFields();
             int intGroupOfPanels = 0;
           // needed so columns in PanelODORData.showRow have the right i in rs.getString(i) , 
          //   initializes PanelODORData each time, so values cannot be holded in the initialization of the class
          if(entityGroupOfComps!=null)  
          {       
                for(int egc = 0; egc<entityGroupOfComps.length; egc++)
                {
                     for(int f=0;f<dbFieldsAll.length;f++)
                     { 
                         for(int cp = 0;cp<entityGroupOfPanels.length;cp++)
                         {
                           if(cp == entityGroupOfComps[egc].getIncludedInGroupOfPanels())
                           {
                             if(dbFieldsAll[f].getGroupOfComps() == egc)
                             {
                                 if(cp==intGroupOfPanels)
                                 {
             //System.out.println("PanelODOR.setEntity  "+f+" "+cp+" "+intGroupOfPanels+" dbFieldsIn.length:"+dbFieldsAll.length+" ");
                                           intGroupOfPanels++;
                                           listLengthDbFieldsInGroupOfPanels.add(f);
                                 }
                                 else
                                 {
                                     
                                 }
                                 
                             }
                           }      
                         }
                     }
                }        
          }
   
               for(int cp = 0;cp<entityGroupOfPanels.length;cp++)
               {
                //System.out.println("PanelODOR.setEntity---->("+cp+") type="+entityType+" title="+entityPanel.getTitle()+"  entityGroupOfPanels[cn].getCaption()="+entityGroupOfPanels[cp].getCaption());
                //PanelOneDataOneRecData panelOneDataOneRecDataSetEntity = new PanelOneDataOneRecData(frame);
       
                panelOneDataOneRecDataSetEntity = this.loadAndGetEntityPanelsOneDataOnRecData(entityPanel,entityGroupOfPanels[cp].getCaption(),/* entityPanel.getEntity(),*/
                    /*entityPanel.getDBFields(), entityPanel.getEntityGroupOfComps(),entityPanel.getEntityGroupOfPanels(),entityPanel.getPrimKey(),*/
                    /*primKeyValue, primKeyDb,*/ query, isNewRec, isNewRecFromCopy, /*entityPanel.getIsMasterUnique(),null,null,*//*for panelTwonull, null,
                    entityPanel.getIsMany(),*/true, yearEnforce,/* icon,*/listLengthDbFieldsInGroupOfPanels, cp/*, panelManagement*/,whereIsCalledFromIn/*,
                    formGlobalTableToGet1, formGlobalTableToApply1*/);
                    /*    public PanelOneDataOneRecData loadAndGetEntityPanelsOneDataOnRecData(EntityPanel entityPanel,boolean isInsidePanelTwoData, String titleIn, String entityIn,
             EntityDBFields[] dbFieldsIn, EntityGroupOfComps[] entityGroupOfCompsIn,EntityGroupOfPanels[] entityGroupOfPanelsIn, String primKeyIn, 
             String primKeyValueIn,String primKeyDbIn, String queryIn, boolean isNewRecIn, boolean isNewRecFromCopyIn, boolean isMasterUniqueIn, 
             String[] sql2WhereFieldIn, String[] sql2WhereValueIn, String query2In,String entity2In, boolean isManyIn, boolean isEditable,String yearEnforceIn, 
             ImageIcon icoIn, int intGroupOfPanelsToShow,PanelManagement panelManagementIn)*/
       
                listPanelOneDataOneRecData.add(panelOneDataOneRecDataSetEntity);
            //    System.out.println("PanelODOR.setEntity---->("+cp+") caption()=  "+entityGroupOfPanels[cp].getCaption());
                panelMainCard.add(panelOneDataOneRecDataSetEntity,entityGroupOfPanels[cp].getCaption());
                //System.out.println("PanelODOR.setEntity ----o----> ("+cp+") title="+titleIn+"  listPanelOneDataOneRecData.size()="+listPanelOneDataOneRecData.size());       
                //scrollpaneTable.setViewportView(panelMainCard);
               } 
            }            
            else if(entityType.equalsIgnoreCase("STATS"))
            {
                System.out.println("error PanelOneDataOneRec.setEntity  "+title+"  entityType stats:"+entityType+"...");
            }
            else
            {
                 System.out.println("error PanelOneDataOneRec.setEntity  "+title+"  entityType:"+entityType);
            }
            
   //         listPanelOneDataOneRec.add(this);
           
           panelHasAllOfODORData.add(panelMainCard,BorderLayout.CENTER);
           

       }
       else  //if (entityGroupOfPanels==null)
       {
           
                  panelOneDataOneRecDataSetEntity = this.loadAndGetEntityPanelsOneDataOnRecData(entityPanel,title, /*entityPanel.getEntity() ,*/
                    /*entityPanel.getDBFields(), entityPanel.getEntityGroupOfComps(),entityPanel.getEntityGroupOfPanels(),entityPanel.getPrimKey(),*/
                    /*primKeyValue, primKeyDb,*/ query, isNewRec, isNewRecFromCopy, /*entityPanel.getIsMasterUnique(),null,null,*//*for panelTwonull, null,
                    entityPanel.getIsMany(),*/true, yearEnforce, /*icon,*/null, 0/*, panelManagement*/,whereIsCalledFromIn/*,
                    formGlobalTableToGet1, formGlobalTableToApply1*/); // 0 is for the only panel  
           
                  listPanelOneDataOneRecData.add(panelOneDataOneRecDataSetEntity);
                 
                  scrollpaneTable.setViewportView(panelOneDataOneRecDataSetEntity);
                  panelHasAllOfODORData.add(scrollpaneTable);
                  
           
       }
            
        scrollpaneTable.setBorder(null);
        //System.out.println("PanelODOR.setEntity  isNewRec:"+isNewRec);
        //System.out.println("PanelOneDataOneRec.setEntity       New Rec            isNewRec:"+isNewRec+"     isNewRecFromCopy:"+isNewRecFromCopy);
        if(isNewRec)
        {
             /*btnSave.setEnabled(true);
             btnDelete.setEnabled(false);
             btnInsert.setEnabled(false);
            btnEditPreferences
             btnInsertFromCopy.setEnabled(false);   */
            
             rowNew(isNewRecFromCopy,true);
        }
        else
        {
             btnSave.setEnabled(true);
             btnSaveAndNew.setEnabled(true);
             btnDelete.setEnabled(true);
             btnPrintPreview.setEnabled(true);
             btnInsert.setEnabled(true);
             
             btnEditPreferences.setEnabled(true);
             //btnInsertFromCopy.setEnabled(true);             
        }
        
        
       
        
        if(entityPanel.getFieldsUnique()!=null)
        {
             btnEditPreferences.setVisible(true);
        }
        else
        {
            btnEditPreferences.setVisible(false);
        }
        
        if(fieldsOnTitle!=null)
        {
            panelTop.setVisible(true);
            //lblTitle.setVisible(true);
            if (selectedRow==0)    // no line selected, so do not run and therefore do not display. 
            {
                
            }
            else
            {           
            setTitle(isNewRec,primKeyValue);
            }
        }
        else
        {
            //showOnlySaveNPrintButton();
            panelTop.setVisible(false);
            //lblTitle.setVisible(false);
        }
        //System.out.println("PanelOneDataRec.setEntity panelMainCard.getComponentCount()="+panelMainCard.getComponentCount());//+"  panel count="+panelOneDataOneRecNew.getCountOfPanelODORData());         
           
       this.setLayout(new BorderLayout());
        this.add(panelMain,BorderLayout.CENTER);  
       
            //System.out.println("PanelODOR.setEntity                   selected:"+selectedRow);
            return selectedRow;
     }
    
  public boolean getHasDataChanged()
  {
      boolean hasDataChanged = false;
           for(int p=0;p<listPanelOneDataOneRecData.size();p++)
           {
               PanelOneDataOneRecData pnl = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(p);
               //System.out.println("PanelOneDataOneRec.getHasDataChanged -----------  hasDataChanged:"+hasDataChanged);
               if(pnl.getHasDataChanged())
               {
                   hasDataChanged=true;
               }
           } 
         //System.out.println("PanelOneDataOneRec.getHasDataChanged -----------  hasDataChanged:"+hasDataChanged);
    return   hasDataChanged;
 
       
  }
  
   public String getPkValueAfterNewIsInserted()
   {
       String retStr ="";
       if(listPanelOneDataOneRecData!=null && listPanelOneDataOneRecData.size()>0)
       {
          PanelOneDataOneRecData pnlODORData = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(0);//  <----  the fist panel only    
          retStr = pnlODORData.getPkOfJustInsertedRecord();
       }
       
       
       
       return retStr;
   }  

   public void setWasNewRecordFalse()
   {
        wasNewRec=false;
   }
   
   
   public boolean getWasNewRecord()
   {
       
       return wasNewRec;
   }   
   
   
  
  //used in DocumentHandler
  /*public void setGuiLoaded(boolean loaded)
  {
  	

           //for(int p=0;p<listPanelOneDataOneRecData.size();p++)
           //{
      if(listPanelOneDataOneRecData.size()>0)
      {
               PanelOneDataOneRecData pnl = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(0);
               pnl.setGuiLoaded(loaded);
      }    
           //}      

      
     // panelOneDataOneRecData.setGuiLoaded(loaded);
  }*/
  
  public void setDataHasChanged(boolean hasChanged)
  {
           for(int p=0;p<listPanelOneDataOneRecData.size();p++)
           {
               PanelOneDataOneRecData pnl = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(p);
               pnl.setDataHasChanged(hasChanged);
           }  

  }


   /* private class CompShowListener implements ComponentListener
    {
    	
    public void componentHidden(ComponentEvent e) {
       // displayMessage(e.getComponent().getClass().getName() + " --- Hidden");
    }

    public void componentMoved(ComponentEvent e) {
       // displayMessage(e.getComponent().getClass().getName() + " --- Moved");
    }

    public void componentResized(ComponentEvent e)
    {
    	panelOneDataOneRecData.collapsablePanelsExpand();// should be on componentShown
    	//    	System.out.println("PanelODOR.CompShowListener.componentResized "+e.getComponent().getClass().getName());

      //  displayMessage(e.getComponent().getClass().getName() + " --- Resized ");            
    }

    public void componentShown(ComponentEvent e)
    {
    	panelOneDataOneRecData.collapsablePanelsExpand();
    	//System.out.println("PanelODOR.CompShowListener.componentShown "+e.getComponent().getClass().getName());
       // displayMessage(e.getComponent().getClass().getName() + " --- Shown");

    }    	
    	
    }*/

  
     private int askIfDataChangedToSave()
   {
   	     final int ESC = -1;
     	 final int YES = 0;
    	 final int NO = 1;
    	 final int CANCEL = 2;
        
        int ask = -2; // not changed not asked
        if(getHasDataChanged())
        {       
             ask = utilsGui.showConfirmYesOrNoOrCancel(frame,"Έγιναν αλλαγές. Θέλετε να τις αποθηκεύσετε;");
        
          //System.out.println("DialogEditRec.closeWindow "+ask);
           if(ask==YES)
           { 

              	if(rowSave(true))
                {
              	  setDataHasChanged(false);
              	  closeDialog();
                }
                else
                {
                    
                }
           }
           else if(ask==NO)
           {    
               setDataHasChanged(false);
           }
           else if (ask==CANCEL || ask==ESC)
           {
               
           }
        // if press escape -1
        }
        else
        { 
           
        }
          
   	 return ask;
   	
   }

   /*
 * 
 *   called by PanelManagement.prepareCloseOfTabPanel
 */
   public boolean closePanelAsk()
   {     
         boolean ret =false;// true close panel, false not close panel
         final int ESC = -1;
     	 final int YES = 0;
    	 final int NO = 1;
    	 final int CANCEL = 2;
     
     int ask =  askIfDataChangedToSave();
    if(ask == YES)
    {
    	ret= true;
    }
    else if(ask == ESC || ask==CANCEL)
    {
    	ret= false; //false not close panel
    }
    else if(ask == NO)
    {
    	ret= true;
    }    
    else if(ask == -2) // not changed not asked
    {
    	ret= true;
    }   
     	
       // closeDB();	
     

        return ret ;
   }



   private void rowNew(boolean isFromCopyOfRecord, boolean boolAskToSaveChanges)
   {
       System.out.println("PanelODOR.rowNew      ==== ============     isNewRecFromCopy:"+isNewRecFromCopy+"      primKeyValue:"+primKeyValue);
       if(isNewRecFromCopy && primKeyValue==null)
       {
           isNewRecFromCopy=false;
       }
       else
       {
       }
   	  //panelOneDataOneRecData.rowNewForFieldsExceptLookup(isFromCopyOfRecord) ;
      //panelOneDataOneRecData.rowNewForFieldsLookup(isFromCopyOfRecord);
      int ask = -2;
      if(boolAskToSaveChanges)
      {
          //int ask = -2; // not changed not asked
        ask = askIfDataChangedToSave();
      }
      
         final int ESC = -1;
     	 final int YES = 0;
    	 final int NO = 1;
    	 final int CANCEL = 2;      
      
       if(ask==YES || ask==NO || ask == -2)
       {
           if(panelEditOneDataRec != null)  
           {
             boolean[] isVisibleType={true,false,true};
             panelEditOneDataRec.setMenuButtonsVisible(isVisibleType); 
             btnSave.setEnabled(true);
             btnSaveAndNew.setEnabled(true);
             btnInsert.setEnabled(true);
             btnDelete.setEnabled(false);      
    //         btnSelectTemplate.setEnabled(false);
            // btnSaveAsTemplate.setEnabled(false);
             btnPrintPreview.setEnabled(false);
             
             btnEditPreferences.setEnabled(true);
             //btnInsertFromCopy.setEnabled(false);
           } 
           
           
           
/*           String queryForCopy = "";

            String qOrderByAndGroupByReadOnly = utilsString.getGroupbyAndOrderbySubQuery(queryReadOnly);           
   String subqueryWhere = ""; // for each primary key
  	    db.retrieveDBDataFromQuery(queryReadOnly,"PanelOneDataOnRec.rowNew");
   	    rs=db.getRS();
            int selectedRow = utilsPanelReport.getRowForPrimKey(rs,dbFieldsAll,primKeyDb,primKeyValue);
            utilsPanelReport.retrievePrimKeyValueForOnePK( queryReadOnly, selectedRow, null,dbFieldsAll,true, entityPanel.getEntity(), primKeyDb);    
        //     System.out.println("----O------>  PanelOneDataOneRec.showPrintPreviewForm   '"+entityPanel.getEntity()+"   primKeyDb:"+primKeyDb+"  selectedRow:"+selectedRow+"'  primKeyValue:"+primKeyValue+"  primKeys.length:"+primKeys.length+"  queryReadOnly:"+queryReadOnly);          
             primKeys = utilsPanelReport.getPrimKeys();
             primKeysCaption = utilsPanelReport.getPrimKeysCaption();
      
             primKeysCount = primKeys.length;
             primKeysValue = utilsPanelReport.getPrimKeysValue(); 
             String sqlEntity = entityPanel.getEntity();
      //    databaseTableMeta.retrievePrimKs(entity); // first retrieve them
          for (int i = 0; i< primKeysCount; i++) // i=0 and i< because arraylist starts from 0
          {             
                //System.out.println("PanelOneDataOneRecData.rowUpdate '"+entity+"' "+primKeys[i]+"="+primKeysValue[i]); 

              
               //System.out.println("PanelOneDataOneRecData.rowUpdate  subqueryWhere  ("+i+")  "+primKey+"   "+primKeys[i]+"="+primKeysValue[i]+"     primKeyDb:"+primKeyDb+"  primKeyValue:"+primKeyValue);   
               if(primKeys[i].equalsIgnoreCase(primKeyDb))
   *///            {
    //            subqueryWhere = subqueryWhere+"("+sqlEntity+"."+primKeys[i]+" LIKE '"+primKeysValue[i]+/*pkValue+*/"')"; // when is updating if a second time after insert is selected
   /*            }
               else
               {
                   subqueryWhere = subqueryWhere+"("+sqlEntity+"."+primKeys[i]+" LIKE '"+primKeysValue[i]+"')";
               }           
                    if (i < primKeys.length-1 && primKeys.length>1) 
          	  // add AND but not on the last field(before where), also not when there is only one PK . -1 because arraylist starts from 0
          	  { subqueryWhere = subqueryWhere+" AND  ";   } 
          }
           
           
           queryForCopy= utilsString.getQueryBeforeWhere(queryReadOnly)+" WHERE "+subqueryWhere+" "+qOrderByAndGroupByReadOnly; 
           
                      
          System.out.println("PanelODOR.rowNew =====================  queryForCopy:"+queryForCopy); 
       */    
           
           
           
           for(int p=0;p<listPanelOneDataOneRecData.size();p++)
           {
               //System.out.println("PanelODOR.rowNew p:"+p);
               PanelOneDataOneRecData pnl = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(p);
               pnl.rowNewAll(true, isFromCopyOfRecord, -1);
           }
 
             isNewRec = true;
             setTitle(true,primKeyValue);
           
       }
       else
       {
           System.out.println("PanelODOR.rowNew Not defined else. ask:"+ask);
       }
      
       
   }
   
   
   /*
   * called by button
   */
   private void rowDelete()
   {

       boolean isReadOnly = false;
           for(int p=0;p<listPanelOneDataOneRecData.size();p++)
           {
               
               PanelOneDataOneRecData pnl = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(p);
              isReadOnly =  pnl.checkIfAllComponentsShouldBeReadOnly();
              System.out.println("PanelODOR.rowDelete p:"+p+"  isReadOnly:"+isReadOnly);
              if(isReadOnly)
              {
                  break;
              }
           
           }       
       
       if(isReadOnly)
       {
           utilsGui.showMessageInfo("Η εγγραφή δεν γίνεται να διαγραφεί. Είναι μόνο για προβολή.");
       }
       else
       {
       
       final int YES = 0;
    	final int NO = 1;
    	if (utilsGui.showConfirmYesOrNo(this, "Σίγουρα θέλετε να διαγράψετε αυτή την εγγραφή; \n "+titleCaptionStr) == YES)
    	{
     
             boolean continueToDel=true;
            
             btnSave.setEnabled(false);
             btnSaveAndNew.setEnabled(false);
             btnDelete.setEnabled(false);
             btnPrintPreview.setEnabled(false);
             btnSaveAndShowList.setEnabled(false);
             btnInsert.setEnabled(true);
             btnSelectTemplate.setEnabled(true);
             //btnSaveAsTemplate.setEnabled(true);
             btnEditPreferences.setEnabled(true);
             //btnInsertFromCopy.setEnabled(true); 
             
         Database dbTransaction = new Database();
         try
         {
          dbTransaction.transactionLoadConnection();
          dbTransaction.setTransactionAutoCommit(false);
         System.out.println("PanelODOR.rowDelete     dbTransaction:"+dbTransaction);                 
             
           for(int l = 0;l<listPanelOneDataOneRecData.size();l++)
           {
                panelOneDataOneRecData = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(l);
                if(panelOneDataOneRecData.rowDeleteTables(dbTransaction))
                {

                    
                        continueToDel=true;
                   
                        
                    
                }
                else
                {
                    
                    continueToDel=false;
                }
            }            
       
             if(continueToDel && listPanelOneDataOneRecData.size()>0)
             {
                panelOneDataOneRecData = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(0);
                panelOneDataOneRecData.rowDelete(dbTransaction);                 
             }
        dbTransaction.transactionCommit();
        dbTransaction.updateShowWindowSuccessDelete(titleCaptionHtml);  //+" deleted successfully"
        dbTransaction.setTransactionAutoCommit(true);               
       
       }
       catch(SQLException e)
       {
           dbTransaction.transactionRollback();
           System.out.println(" error  PanelODOR.rowDelete   rollBack  dbTransaction:"+dbTransaction); 
           dbTransaction.transactionUpdateQuerySQLException(e,true,"PanelODOR.rowDelete");
         /*if(VariablesGlobal.globalShowPrintStackTrace)  
         {
           e.printStackTrace();     
         } */          
       }
       finally
	{
	      if (!dbTransaction.isTransactionConnectionNull())
              {
	           dbTransaction.transactionClose();
              }
        }              
         //panelOneDataOneRecData = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(0);
   	 //if(panelOneDataOneRecData.rowDeleteAll())
        // {
   //         this.rowNew(false);    
         //}
        }
       }
   }

 private void showPrintPreviewFormIfDataNotChanged(boolean displayInTabOrDialog)
 {
     
              if(getHasDataChanged())
             {
                   int intAnswer =  utilsGui.showConfirmYesOrNo(this, "Πρίν εκτυπωθεί το έντυπο πρέπει να αποθηκευθεί. Θέλετε να συνεχίσετε;");
                  //System.out.println("  intAnswer:"+intAnswer);
                   int YES = 0;	  int NO = 1;
                   if(intAnswer==NO)
                   {
                                
                   }
                   else if(intAnswer ==  YES)
                   {
                         
              	if(rowSave(false))
                {
              	  setDataHasChanged(false);
                  showPrintPreviewForm(displayInTabOrDialog);
                }
                   
                   }
             }
             else
             {
                 showPrintPreviewForm(displayInTabOrDialog); 
             }
     
   
 }
   
   
   private void showPrintPreviewForm(boolean displayInTabOrDialog)// in tab true, in dialog false
   {
       /*if(entityPanel.getEntityReportForm() == null)
       {
           utilsGui.showMessageInfo("Δεν υπάρχει φόρμα.");
       }
       else
       {*/

       
              for(int p=0;p<listPanelOneDataOneRecData.size();p++)
             {
                   PanelOneDataOneRecData pnlODORData = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(p);
                   //System.out.println("--->PanelODOR.rowSave     dbTransaction:"+dbTransaction+"   p:"+p);  
                  // if(p==0)// only when the data of the first 'tab' panel is saved, show data and title. When isNewRec is true.
                   //{
                      primKeyValue =  pnlODORData.getPrimKeyValue();
                   //}
             }
       
       System.out.println("---- + ++ ++ ------>  PanelOneDataOneRec.showPrintPreviewForm   primKeyDb:"+primKeyDb+"  primKeyValue:"+primKeyValue+"     isNewRec:"+isNewRec+"  rs:"+rs); 

         // if(isNewRec)
         // {
         
         

       db.getConnection();
       db.retrieveDBDataFromQuery(queryReadOnly, "PanelODOR.showPrintPreviewForm  rsk");
       ResultSet rsk = db.getRS();
      
       db.getRecordCount();
       
       closeDB(); 
         
            int selectedRow = utilsPanelReport.getRowForPrimKey("PanelODOR.showPrintPreviewForm  rsk",queryReadOnly,rsk,dbFieldsAll,primKeyDb,primKeyValue);
            utilsPanelReport.retrievePrimKeyValueForOnePK( queryReadOnly, selectedRow, null,dbFieldsAll,true,/*primKeyIn,intColumnOfDescriptionIn,
             sql2WhereField, sql2WhereValue,*/ entityPanel.getEntity(), /*tableModelReadOnly,*/ primKeyDb);    
        //     System.out.println("----O------>  PanelOneDataOneRec.showPrintPreviewForm   '"+entityPanel.getEntity()+"   primKeyDb:"+primKeyDb+"  selectedRow:"+selectedRow+"'  primKeyValue:"+primKeyValue+"  primKeys.length:"+primKeys.length+"  queryReadOnly:"+queryReadOnly);          
             primKeys = utilsPanelReport.getPrimKeys();
             primKeysCaption = utilsPanelReport.getPrimKeysCaption();
      
      //       primKeysCount = primKeys.length;
             primKeysValue = utilsPanelReport.getPrimKeysValue();              
            System.out.println("----OO------>  PanelOneDataOneRec.showPrintPreviewForm   '"+entityPanel.getEntity()+"'   primKeyDb:"+primKeyDb+"'  primKeyValue:"+primKeyValue+"  primKeys.length:"+primKeys.length);
             //closeDB();  
       //    }
       
       
                    String[] pkTableArray = new String[primKeys.length];//entityPanel.getEntity()
             String[] primKeysWithTable = new String[primKeys.length];
             for(int k =0;k<primKeys.length;k++)
             {
                 pkTableArray[k] = entityPanel.getEntity();
                 primKeysWithTable[k] = pkTableArray[k]+"."+primKeys[k];
                 if(!primKeyValue.equalsIgnoreCase("") && primKeyDb.equalsIgnoreCase(primKeys[k]))
                 {
                     System.out.println("-- ---   -- - - - - -->  PanelOneDataOneRec.showPrintPreviewForm   k:"+k+"   primKeyDb:"+primKeyDb +"    primKeyValue:"+primKeyValue);
                     primKeysValue[k] = primKeyValue;
                 }
                 System.out.println("PanelODOR.showPrintPreviewForm      --+++++++++---A     entityPanel.getEntityReportForm bands  k:"+k+"    primKeysWithTable:"+primKeysWithTable[k]+"="+primKeysValue[k]);
                 //System.out.println("PanelODOR.showPrintPreviewForm      --+++++++++---A     entityPanel.getEntityReportForm bands  k:"+k+"             getQuery:"+entityPanel.getEntityReportForm());
             }             
        
       PanelReportSettingsPreview panelReportSettingsPreview = new PanelReportSettingsPreview(frame);
       String[] arrayOfNameOfPksOfRecordToShow = primKeysWithTable;//{entityPanel.getEntity()+"."+primKeyDb, entityPanel.getEntity()+"."+"dbCompanyId"};
       String[] arrayOfValueOfPksOfRecordToShow = primKeysValue;//{primKeyValue, VariablesGlobal.globalCompanyId};
       
       panelReportSettingsPreview.setEntityDirectPreviewOfForm(entityPanel.getEntity(),entityPanel.getEntityReportForm(),arrayOfNameOfPksOfRecordToShow,arrayOfValueOfPksOfRecordToShow,true, panelManagement);      

       
       
       // get query with 'where' clause
       String queryToGetForm = entityPanel.getEntityReportForm().getQuery();     //.getFormQueryForPrinting();
              String queryWhereAnd = "";
             if(arrayOfNameOfPksOfRecordToShow!=null)
             {
              for(int p=0;p<arrayOfNameOfPksOfRecordToShow.length;p++)
              {

                    //System.out.println("PanelODOR.showPrintPreviewForm    ++++++++   p:"+p+"  column:"+arrayOfNameOfPksOfRecordToShow[p]+"="+arrayOfValueOfPksOfRecordToShow[p]); 
                  queryWhereAnd = queryWhereAnd + " AND "+arrayOfNameOfPksOfRecordToShow[p]+" LIKE "+arrayOfValueOfPksOfRecordToShow[p];
              }
             }
             queryToGetForm = queryToGetForm+" "+queryWhereAnd;
       
       
       
       
       boolean hasData = false;
       try
       {
       
       db.getConnection();
       db.retrieveDBDataFromQuery(queryToGetForm, "PanelODOR.showPrintPreviewForm   queryToGetForm ");
       ResultSet rsForm = db.getRS();
       hasData = rsForm.next();
       db.getRecordCount();
       //System.out.println(" error  PanelODOR.showPrintPreviewForm    rec count:"+db.getRecordCount()); 
       }
       catch(SQLException e)
       {
           System.out.println(" error  PanelODOR.showPrintPreviewForm   code:"+e.getErrorCode()+"  msg:"+e.getMessage()); 
           e.printStackTrace();
       }
       finally
       {
            closeDB();
       }
       
       System.out.println("    PanelODOR.showPrintPreviewForm   queryToGetForm:"+queryToGetForm);
       if (queryToGetForm.equalsIgnoreCase("") || !hasData) // when there is no form data
       {

                /*DialogMulti dlg = new DialogMulti(frame);
                dlg.setEntity(panelReportSettingsPreview,PANEL_TYPE_REPORT, entityPanel.getEntityReportForm().getCaption(),false);
                //panelReportSettingsPreview.setPreferredSize(new Dimension(850,650));
                dlg.display(); // pack, center and visible  */
                
                utilsGui.showMessageError(this,"Είτε δεν υπάρχει φόρμα εκτύπωσης είτε δεν είναι ενεργή για να εμφανισθεί."); // exist both in showPrintPreviewForm and getAnswerOfAskToPrintOrNot
          
       }
       else  //  when there is form data
       {
       //   "εκτύπωση φόρμας "+
       if(displayInTabOrDialog)//// in tab true, in dialog false
       {
           panelManagement.addShowTabWithPanel(entityPanel.getEntityReportForm().getCaption(),ICO_PRINT_PREVIEW16, panelReportSettingsPreview,PANEL_TYPE_REPORT);
       }
       else // in tab false, in dialog true
       {
          DialogMulti dlg = new DialogMulti(frame);
          dlg.setEntity(panelReportSettingsPreview,PANEL_TYPE_REPORT, entityPanel.getEntityReportForm().getCaption(),false);
          panelReportSettingsPreview.setPreferredSize(new Dimension(850,650));
          dlg.display(); // pack, center and visible

       }
       
       }
              for(int p=0;p<listPanelOneDataOneRecData.size();p++)
             {
                   PanelOneDataOneRecData pnlODORData = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(p);
                   //System.out.println("--->PanelODOR.rowSave     dbTransaction:"+dbTransaction+"   p:"+p);  
                  // if(p==0)// only when the data of the first 'tab' panel is saved, show data and title. When isNewRec is true.
                   //{
                pnlODORData.setVisibleOrEditableFields(true);
             }
        
   }
   
   public boolean checkUniquityOfFields(int isUniqueWhen)
   {
       boolean boolCheck= false;
      //for(int p=0;p<listPanelOneDataOneRecData.size();p++)
      // {
             PanelOneDataOneRecData pnlODORData = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(0);//  <----  the fist panel only
            //IS_UNIQUE_WHILE_DATAENTRY = 0;
           //IS_UNIQUE_BEFORE_SAVING = 1;             
             boolCheck = pnlODORData.checkUniquityOfFields(isUniqueWhen);   
      // }
       return boolCheck;
   }
 
   
   public String getFieldsCaptionsToBeCheckedForUniquenceSepparatedByCommas()
   {
       String captions="";
      //for(int p=0;p<listPanelOneDataOneRecData.size();p++)
      // {
             PanelOneDataOneRecData pnlODORData = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(0);// <----   the fist panel only
            //IS_UNIQUE_WHILE_DATAENTRY = 0;
           //IS_UNIQUE_BEFORE_SAVING = 1;             
             captions = pnlODORData.getFieldsCaptionsToBeCheckedForUniquenceSepparatedByCommas();   
      // }
       return captions;
   }   
   
   
   private void setFieldsTemplateZero(String fieldIsTemplate)
   {
       

       for(int p=0;p<listPanelOneDataOneRecData.size();p++)
       {
           PanelOneDataOneRecData pnl = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(p);
           pnl.setFieldsTemplateZero(fieldIsTemplate);
           //System.out.println("PanelODOR.calculationForFields   p:"+p+"         listPanelOneDataOneRecData.size:"+listPanelOneDataOneRecData.size());
       }  
   }
   
   
   private int getAnswerOfAskToPrintOrNot()
   {
       int ret = -1;
       EntityReport entityReportForm = entityPanel.getEntityReportForm();
       if(entityReportForm != null)
       {
           String tableAndFieldOfAskingIfToPrintOrNot = entityReportForm.getTableAndFieldOfAskingToPrintOrNot();
           if(!tableAndFieldOfAskingIfToPrintOrNot.equalsIgnoreCase(""))
           {
           
           entityPanel.getEntityReportForm().getCaption();
       
              for(int p=0;p<listPanelOneDataOneRecData.size();p++)
             {
                   PanelOneDataOneRecData pnlODORData = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(p);

                      primKeyValue =  pnlODORData.getPrimKeyValue();

             }
       
           
           String queryForPrint = entityReportForm.getQuery();        // .getFormQueryForPrinting();
          
           String queryWithoutWhere = utilsString.getQueryWithoutWhere(queryForPrint);
            String queryWhereAnd = " AND "+entityPanel.getEntity()+"."+primKeyDb+" LIKE "+primKeyValue;// maybe not only one primarykey, but it already filters it
           
           String queryWhere = utilsString.getQueryWhere(queryForPrint)+queryWhereAnd;
       
           String queryToGetActionForAskingToPrintOrNot = queryWithoutWhere+queryWhere;// entityReportForm.getQuery();//
       
       try
       {
       
       db.getConnection();
       db.retrieveDBDataFromQuery(queryToGetActionForAskingToPrintOrNot, "PanelODOR.getAnswerOfAskToPrintOrNot   queryToGetActionForAskingToPrintOrNot");
       ResultSet rs = db.getRS();
       //hasData = rsForm.next();
       if(rs.first())
       {
           ret = rs.getInt(tableAndFieldOfAskingIfToPrintOrNot);  ///      "actionseries.askForPrint"
       }
       else
       {
           System.out.println(" error  PanelODOR.getAnswerOfAskToPrintOrNot   ---  queryToGetActionForAskingToPrintOrNot:"+queryToGetActionForAskingToPrintOrNot);
           utilsGui.showMessageError(this,"Είτε δεν υπάρχει φόρμα εκτύπωσης είτε δεν είναι ενεργή για να εμφανισθεί.");  //  // exist both in showPrintPreviewForm and getAnswerOfAskToPrintOrNot
       }
       
       }
       catch(SQLException e)
       {
           System.out.println(" error  PanelODOR.getAnswerOfAskToPrintOrNot   code:"+e.getErrorCode()+"  msg:"+e.getMessage()); 
           e.printStackTrace();
       }
       finally
       {
            closeDB();
       }           
           
           
           
           }  
       }
       
       
       //System.out.println("     --   entityReportForm:"+entityReportForm+"    ret:"+ret);
       
       
       return ret;
       
   }
   
  /*
   * 
   * called from this and  PanelEditOneDataRec
   *  hasAlreadyAskedToPrint is true when called by printPreview
   */
   public boolean rowSave(boolean askToPrintAfterSaving)   // use also setHasDataChanged(false)
   {
       
       boolean ret=false;
       int intListMessages;
        
            //IS_UNIQUE_WHILE_DATAENTRY = 0;
           //IS_UNIQUE_BEFORE_SAVING = 1;         
       if(checkUniquityOfFields(IS_UNIQUE_BEFORE_SAVING))
       {
          intListMessages = checkForListMessages();
          
           if(intListMessages==0) //1 no, 0 yes       
           {
              if(saveAfterChecks(intListMessages))// if true show printpreview form
              {
                if(!askToPrintAfterSaving)  
                {
                    //
                }
                else
                {
                  int answerOfAskToPrint = getAnswerOfAskToPrintOrNot();
                  if(answerOfAskToPrint==1)//ask
                  {
                      int ans = utilsGui.showConfirmYesOrNo(this, "Θέλετε να εκτυπώσετε τη φόρμα;");
                      if(ans==utilsGui.YES)
                      {
                          showPrintPreviewForm(false);
                      }
                      else if(ans==utilsGui.NO)
                      {
                          
                      }
                      else if(ans==utilsGui.CANCEL)
                      {
                          
                      }
                  }
                  else if(answerOfAskToPrint==2)// preview
                  {
                     showPrintPreviewForm(false);
                  }
                  else if(answerOfAskToPrint==3)// print
                  {
                      // TODO direct print
                  }
                  else if(answerOfAskToPrint==4)// no action
                  {
                      
                  }
                  else
                  {
                      System.out.println("   rowSave:   UNKNOWN answerOfAskToPrint:"+answerOfAskToPrint);
                  }
                }
              }
              ret=true;
           }
          
       }
       else
       {

                   ret=false;         
       }
           
          
       
       return ret;
   }   
  
   
   private void rowSaveAndNew()
   {
       
	           if(rowSave(true))
                   {
                        rowNew(false,false);    //boolean isFromCopyOfRecord, boolean boolAskToSaveChanges,boolean isFromTemplate
                   }       
       
       
   }



   
   /*
   * called by calculation button
   */
   private void calculationFromButton()
   {
       

         
         
                PanelDataFilter pnlDataFilter = new PanelDataFilter(frame);
                pnlDataFilter.setEntity(entityCalculate.getEntityFilterSettings(), entityCalculate.getEntityGroupOfComps(), PANEL_FILTER_SEARCH, /*entityCalculate.getYearEnforce(),*/ panelManagement);

               DialogMulti dlgFilter = new DialogMulti(frame);
                dlgFilter.setEntity(pnlDataFilter,PANEL_TYPE_CALCULATIONDOCFILTER,entityCalculate.getCaption(),true);
                //dlgDocFilter.setPanelFilters(pnlDataFilter);
                dlgFilter.display();
                boolean checkIsCanceled = dlgFilter.getIsCancelClicked();
   
       
       //panelOneDataOneRecData.calculationFromToolBar();
       for(int p=0;p<listPanelOneDataOneRecData.size();p++)
       {
           PanelOneDataOneRecData pnl = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(p);
           pnl.calculationFromToolBarButton(p,pnlDataFilter,checkIsCanceled, entityCalculate);
           //System.out.println("PanelODOR.calculationForFields   p:"+p+"         listPanelOneDataOneRecData.size:"+listPanelOneDataOneRecData.size());
       }       

   }

   
   
   /*
   *  also called by PanelMDMR.saveChanges
   *
   */
   public int checkForListMessages()
   {
       
      //boolean boolContinue=false;
       
       boolean isEditable =  false;
       for(int p=0;p<listPanelOneDataOneRecData.size();p++)
       {
           PanelOneDataOneRecData pnl = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(p);
           if(pnl.checkIfAllComponentsShouldBeReadOnly()) // should be read only when is in a different year
           {
               isEditable=false;
               break;
           }
           else
           {
               isEditable=true;
           }
           System.out.println("PanelODOR.checkForListMessages   p:"+p+"    isEditable:"+isEditable+"       listPanelOneDataOneRecData.size:"+listPanelOneDataOneRecData.size());
       }
      
       
       int intListMessages = 1; //1 no, 0 yes
     if(isEditable)
     {
       //System.out.println("--++--  PanelODOR.rowSave   "+title);
      // System.out.println("PanelODOR.rowSave listPanelOneDataOneRecData.size()="+listPanelOneDataOneRecData.size());	    
         
  
       ArrayList listMessages=new ArrayList();
       ArrayList listMsgs = new ArrayList();
       for(int p=0;p<listPanelOneDataOneRecData.size();p++)
       {
           PanelOneDataOneRecData pnl = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(p);
   	  listMsgs = pnl.getListOfFieldsUncompleted();
          System.out.println("PanelODOR.checkForListMessages     p:"+p+"     isEditable:"+isEditable+"       listMsgs.size():"+listMsgs.size());
    	for(int l = 0;l<listMsgs.size();l++)
    	{
    		listMessages.add(listMsgs.get(l));
    	}

          //System.out.println("PanelODOR.rowSave p:"+p+"  "+title);

       }   // for listPanelOneDataOneRecData
       
       if(listMessages!=null)
       {
           
         intListMessages = utilsGui.showMessageFromList(listMessages);       
         //System.out.println(" ------ PanelODOR.rowSave   utilsGui.showMessageFromList   intListMessages: "+intListMessages);
       //boolean continueListMessages = false;

       }
     }
       return intListMessages;
       
   }
    
   /*
   *  also called by PanelMDMR.saveChanges
   * NOT in here the load after save but here PanelODORData.rowSaveAll
   *
   */
   public boolean saveAfterChecks(int intListMessages)//    intListMessages==0) //1 no, 0 yes  
   {    
       boolean boolContinue=false;
       
       if(intListMessages==0) //1 no, 0 yes  
       {
  
   Database dbTransaction = new Database();
   try
   {         
       dbTransaction.transactionLoadConnection();
       dbTransaction.setTransactionAutoCommit(false);
        
       
       //System.out.println("   BBBBBB  PanelODOR.saveAfterChecks         listPanelOneDataOneRecData:"+listPanelOneDataOneRecData);    
       
             // for after new db transaction, bacause we need to rollback all if something goes wrong      
       for(int p=0;p<listPanelOneDataOneRecData.size();p++)
       {
          //System.out.println("   BBBBBB  PanelODOR.saveAfterChecks     p:"+p+"    listPanelOneDataOneRecData.size:"+listPanelOneDataOneRecData.size());    
           
           
             PanelOneDataOneRecData pnlODORData = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(p);
          //System.out.println("PanelODOR.rowSave  intListMessages:"+intListMessages);
          if(intListMessages==1) // if one panel is not then do not continue,    //1 no, 0 yes ;	
          {
                //isNewRec= false;
              System.out.println("Error PanelODOR.saveAfterChecks IF=1 ++++++++++++++++++ perhaps delete, intListMessages: "+intListMessages);
                boolContinue=false;
       	        break;
          }
          else
          {
              System.out.println("   BBBBBB  PanelODOR.saveAfterChecks  ELSE  START   p:"+p+"     isNewRec:"+isNewRec+"    listPanelOneDataOneRecData.size:"+listPanelOneDataOneRecData.size());    
   	  	   pnlODORData.rowSaveAll(p,isNewRec,dbTransaction); 

                   boolContinue=true;
               //System.out.println("   BBBBBB  PanelODOR.saveAfterChecks  C ");
          }
       
        }//for
       
       
                   dbTransaction.transactionCommit();
                   dbTransaction.setTransactionAutoCommit(true);  
               //for again to show title 
                   String strTitleCaptionHTML ="";
                   
                    
                   
                   
                   
                   
                   
             for(int p=0;p<listPanelOneDataOneRecData.size();p++)
             {
                   PanelOneDataOneRecData pnlODORData = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(p);
                   //System.out.println(" BBBBBB   --->PanelODOR.rowSave     dbTransaction:"+dbTransaction+"   p:"+p);  
                   if(p==0)// only when the data of the first 'tab' panel is saved, show data and title. When isNewRec is true.
                   {
                       
                   if(isNewRec)
                   {
                     String primKeyOfNewRecord =  pnlODORData.getPkOfJustInsertedRecord();  
                     pnlODORData.showSpecificRowForPKsAfterANewRecIsSaved(primKeyOfNewRecord);
                   }
                   else
                   {
                    //   primKeyOfNewRecord="";
                   }                        
                     
                   String pkFromOne = pnlODORData.getPKeyFromOnePanelForTables();
                   if(pkFromOne== null || pkFromOne.equalsIgnoreCase(""))
                   {
                      strTitleCaptionHTML = setTitle(false,pnlODORData.getPrimKeyValue());
                   }
                   else
                   {
                       strTitleCaptionHTML = setTitle(false,pkFromOne);
                   }
                    
                   }                   
             }
             dbTransaction.updateShowWindowSuccessSave(strTitleCaptionHTML);    //+" saved successfully."   
       
      }
      catch(SQLException e)
      {
                 dbTransaction.transactionRollback();
                 System.out.println(" error  PanelODOR.saveAfterChecks   rollBack  dbTransaction:"+dbTransaction);  
                 dbTransaction.transactionUpdateQuerySQLException(e,true,"PanelODOR.saveAfterChecks");
        
      }
      finally
      {
	        if (!dbTransaction.isTransactionConnectionNull())
                {
	             dbTransaction.transactionClose();
                }
     }  
   
         }
         else if(intListMessages==1) //1 no, 0 yes ;
         {
           
         }
         else
         {
              System.out.println(" ------ PanelODOR.saveAfterChecks   utilsGui.showMessageFromList  ELSE  intListMessages: "+intListMessages);
         }
       
 
       /*else//   (listMessages==null)
       {
           
       } 
      }// if isEditable
      */
    
        
       // System.out.println(" ------ PanelODOR.saveAfterChecks         O         isNewRec:"+isNewRec);

       if(boolContinue)
       {
           

           
            isNewRec= false; 
            wasNewRec =true;
            btnSave.setEnabled(true);
            btnSaveAndNew.setEnabled(true);
            btnDelete.setEnabled(true);
            btnPrintPreview.setEnabled(true);
            btnInsert.setEnabled(true);
            btnSelectTemplate.setEnabled(true);
            //.setEnabled(true);
            
            btnEditPreferences.setEnabled(true);
            
            // btnInsertFromCopy.setEnabled(true);             
           
       
      //setTitle(); cannot show the new auto increment id
        }
     else
     {
         System.out.println("PanelODOR.saveAfterChecks     ");
     }
       return boolContinue;
   }
  
   
       private void displayDialogEditPreferences()
    {// same as PanelStatistics.displayDialogTablePreferences
    	//String[] colTitles = {"ναι/οχι","όνομασία στήλης"};
    	
       // TableColumnModel columnModel = table.getColumnModel(); 

    	DialogDataEditConfig dlgDataEditConfig;
    	
    	/*Vector dataVector = new Vector();
    	int colCount=table.getColumnCount();
        for (int c = 0; c < colCount; c++) // for each field
        {
        	String colName = table.getColumnName(c);
            Object[] record = new Object[] { new Boolean(true), colName } ;
            dataVector.addElement(record);
        }*/

            dlgDataEditConfig = new DialogDataEditConfig((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this));
            //entityPanel.getStrOfMany2();
            String text ="Επιλογές καταχώρησης";//+entityPanel.getStrOfMany2();
   //         dlgDataConfig.setEntity(entity,text,query, fieldsOrderby,primKey,entityFilterSettings);\
            String cptionsOfFields = getFieldsCaptionsToBeCheckedForUniquenceSepparatedByCommas();
            dlgDataEditConfig.setEntity(entityPanel,text,cptionsOfFields);
    	    dlgDataEditConfig.showDialog();
         
    }
      

   
  //same as DialoEditRec.getTitle
 /*
  similars in PanelODMR.getTitleCaption, PanelODOR.setTitle

 */
  private String setTitle(boolean isInsertedFirstTime, String pkValue)
  {
  	//System.out.println("   PanelOneDataOneRec.setTitle      isNewRec:"+isNewRec);
  	      //String caption="";
      titleCaptionHtml="";
      titleCaptionStr="";
  
       if(isNewRec && isInsertedFirstTime)// when row new. before a record is inserted
        {
            //String v = utilsPanelReport.getNoOfPKOfNewRecord(false,dbFieldsAll,entityPanel.getEntity());
            //System.out.println(" -->  PanelOneDataOneRec.setTitle '"+entityPanel.getEntity()+"'  isNewRec:"+isNewRec+" primKeyValueNew:"+v); 
        	titleCaptionHtml="<html>"+title+" </html>";    
        }
        else if(isNewRec && !isInsertedFirstTime)// when a new record is saved
        {
             panelOneDataOneRecData = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(0);
            String strTitleHTML = panelOneDataOneRecData.getPanelTitleFromTxtFields(title,fieldsOnTitle,fieldsOnTitleCaption);
            
            titleCaptionHtml=strTitleHTML;    
        }
        else // when a record is edited
        {
           
           String queryForTitle = "";

            String qOrderByAndGroupByReadOnly = utilsString.getGroupbyAndOrderbySubQuery(queryReadOnly);           
   String subqueryWhere = ""; // for each primary key
  	    db.retrieveDBDataFromQuery(queryReadOnly,"PanelOneDataOnRec.setTitle");
   	    rs=db.getRS();
            int selectedRow = utilsPanelReport.getRowForPrimKey("PanelOneDataOnRec.setTitle",queryReadOnly,rs,dbFieldsAll,primKeyDb,primKeyValue);
            utilsPanelReport.retrievePrimKeyValueForOnePK( queryReadOnly, selectedRow, null,dbFieldsAll,true, entityPanel.getEntity(), primKeyDb);    
        //     System.out.println("----O------>  PanelOneDataOneRec.showPrintPreviewForm   '"+entityPanel.getEntity()+"   primKeyDb:"+primKeyDb+"  selectedRow:"+selectedRow+"'  primKeyValue:"+primKeyValue+"  primKeys.length:"+primKeys.length+"  queryReadOnly:"+queryReadOnly);          
             primKeys = utilsPanelReport.getPrimKeys();
             primKeysCaption = utilsPanelReport.getPrimKeysCaption();
      
             primKeysCount = primKeys.length;
             primKeysValue = utilsPanelReport.getPrimKeysValue(); 
             String sqlEntity = entityPanel.getEntity();
      //    databaseTableMeta.retrievePrimKs(entity); // first retrieve them
          for (int i = 0; i< primKeysCount; i++) // i=0 and i< because arraylist starts from 0
          {             
                //System.out.println("PanelOneDataOneRecData.rowUpdate '"+entity+"' "+primKeys[i]+"="+primKeysValue[i]); 

              
               //System.out.println("PanelOneDataOneRecData.rowUpdate  subqueryWhere  ("+i+")  "+primKey+"   "+primKeys[i]+"="+primKeysValue[i]+"     primKeyDb:"+primKeyDb+"  primKeyValue:"+primKeyValue);   
               if(primKeys[i].equalsIgnoreCase(primKeyDb))
               {
                   subqueryWhere = subqueryWhere+"("+sqlEntity+"."+primKeys[i]+" LIKE '"+pkValue+"')"; // when is updating if a second time after insert is selected
               }
               else
               {
                   subqueryWhere = subqueryWhere+"("+sqlEntity+"."+primKeys[i]+" LIKE '"+primKeysValue[i]+"')";
               }           
                    if (i < primKeys.length-1 && primKeys.length>1) 
          	  // add AND but not on the last field(before where), also not when there is only one PK . -1 because arraylist starts from 0
          	  { subqueryWhere = subqueryWhere+" AND  ";   } 
          }
           
           
           queryForTitle= utilsString.getQueryBeforeWhere(queryReadOnly)+" WHERE "+subqueryWhere+" "+qOrderByAndGroupByReadOnly; 
           
           
           
           
           
           try
           {    
               //System.out.println("PanelODOR.setTitle()     ---=====---      primKeyDb:"+primKeyDb+"  pkValue:"+pkValue+"    isNewRec:"+isNewRec+"    isInsertedFirstTime:"+isInsertedFirstTime+"      queryForTitle:"+queryForTitle+"         -------     subqueryWhere:"+subqueryWhere);
   	           db.retrieveDBDataFromQuery(queryForTitle,"PanelOneDataOneRec.setTitle");
   	           rs=db.getRS();
   	           //ResultSetMetaData rsmd = db.getRSMetaData();
   	                    	      
                //rs = db.retrieveResultSet(q);
               
                rs = db.retrieveRow(rs, 1);// go to the only row   
                
                if (rs!=null &&  fieldsOnTitle!=null)
                {
                     //System.out.println(" ====  PanelODOR.setTitle      queryReadOnly:"+queryReadOnly);
                    rs.first();
                	for(int f = 0;f<fieldsOnTitle.length;f++)
                	{
                            //System.out.println("PanelODOR.setTitle  field "+f+"  title length:"+fieldsOnTitle.length+"    "+fieldsOnTitleCaption[f]+" "+fieldsOnTitle[f]+"="+rs.getString(fieldsOnTitle[f]));
                            String strField="";
                            // dbFieldsAll = entityPanel.getDBFields();
                             
                             //System.out.println(".....................................PanelODOR.setTitle()   --    queryReadOnly:"+queryReadOnly+"    --    fieldsOnTitle[f]:"+fieldsOnTitle[f]);
 
                               String str = rs.getString(fieldsOnTitle[f]);
                                
    	                    
    	                        String[] allowedPatternsToRead = {"yyyy-MM-dd","yy/MM/dd","yy-MM-dd", "yyyy/MM/dd","dd-MM-yyyy","dd/MM/yy","dd-MM-yy", "dd/MM/yyyy"};
                                String fdate=utilsDate.reformatDateString(str,allowedPatternsToRead,"EEE dd-MM-yyyy");                                
                                strField=fdate;                             
                              if(fdate==null || fdate.equalsIgnoreCase(""))
                              {
                                  strField = str;
                              }
                              else
                              {
                                  
                                  strField=fdate;
                              }  
                		titleCaptionHtml = titleCaptionHtml+"<span style='color:"+CLR_TITLE_CAPTION_DARK_HTML+"'>"+ fieldsOnTitleCaption[f]+":</span>"+strField;// strField +" ";
                                titleCaptionStr=titleCaptionStr+ " "+ fieldsOnTitleCaption[f]+":"+strField;// strField +" ";
                                
                          /*for(int t=0; t<dbFieldsAll.length; t++)
                          {  
                              
                            if(fieldsOnTitle[f].equalsIgnoreCase(dbFieldsAll[t].getDbField()))
                            {                               
                              if(dbFieldsAll[t].getColClassName().equalsIgnoreCase("java.sql.Date") || dbFieldsAll[f].getColClassName().equalsIgnoreCase("java.util.Date"))    
                              //if(rsmd.getColumnClassName(fieldsOnTitle[f]).equalsIgnoreCase("java.sql.Date"))
                              {
                                String str = rs.getString(fieldsOnTitle[f]);
                                
    	                    
    	                        String[] allowedPatternsToRead = {"yyyy-MM-dd","yy/MM/dd","yy-MM-dd", "yyyy/MM/dd","dd-MM-yyyy","dd/MM/yy","dd-MM-yy", "dd/MM/yyyy"};
                                String fdate=utilsDate.reformatDateString(str,allowedPatternsToRead,"EEE dd-MM-yyyy");                                
                                strField=fdate;
                              }
                              else
                              {
                                 strField = rs.getString(fieldsOnTitle[f]);
                              }
                            }
                          
                          }*/                                 
                	
                        }
                }
           }//try
           catch ( SQLException sqlex)
           {
               System.out.println("error:  PanelODOR.setTitle:  "+sqlex.getMessage()+"    title:" +title+"   pkValue:"+pkValue+"     query:"+ query+"       queryForTitle:"+queryForTitle);
               if(VariablesGlobal.globalShowPrintStackTrace)
               {
                   sqlex.printStackTrace();
               }
          }
           finally
           {
            closeDB();
           }
     //System.out.println("PanelODOR.setTitle()  title=" +title+"  titleCaptionStr:"+titleCaptionStr );
            titleCaptionHtml="<html>"+title+" "+titleCaptionHtml+"</html>";
  	   }

        lblTitle.setText(titleCaptionHtml);
        
        return titleCaptionHtml;
  }
  
  
  private void showTemplates()
  {
      popupMenuTemplates.removeAll();
      //------------------------------
      //   sxesoexoheader.esoexoHeaderId, sxesoexoheader.titleOfTemplate
      String queryTemplates = entityTemplate.getQueryShowTemplates();//"SELECT * FROM sxesoexoheader WHERE  sxesoexoheader.dbCompanyId = "+VariablesGlobal.globalCompanyId+"  AND  sxesoexoheader.isTemplate = 1 ORDER BY sxesoexoheader.titleOfTemplate";//esoexoheaderId";//sxesoexoheader.titleOfTemplate";
      //String subqueryIsNotTemplate = entityTemplate.getSubQueryIsNotTemplate();// = "AND sxesoexoline.isTemplate ='0'";
      //String subqueryIsTemplate = entityTemplate.getSubQueryIsTemplate();//ToReplace = "AND sxesoexoline.isTemplate LIKE '1'";
      String fieldHeaderId = entityTemplate.getFieldPrimkeyId();//"sxesoexoheader.esoexoheaderId";
      String strNameOfFieldOfTemplateMenu = entityTemplate.getFieldOfTemplateMenu();//"sxesoexoheader.titleOfTemplate";
      String setFieldIsTemplate = entityTemplate.getFieldIsTemplate();//"isTemplate";
      //------------------------------------------
      //System.out.println(" OOOOOOOOOOOOOOOOOOOOO "+query);
            try
           {    
               //System.out.println("PanelODOR.showTemplates()                 queryTemplates:"+queryTemplates);
   	           db.retrieveDBDataFromQuery(queryTemplates,"PanelOneDataOneRec.showTemplates");
   	           ResultSet rsTemp=db.getRS();
   	           //ResultSetMetaData rsmdTemp = db.getRSMetaData();
   	                    	     
                //rs = db.retrieveResultSet(q);           
                //rs = db.retrieveRow(rs, 1);// go to the only row   
                
                if (rsTemp!=null)
                {
                     //System.out.println(" ====  PanelODOR.showTemplates      queryReadOnly:"+queryReadOnly);
                   // rs.first(); 
                   //int r =0;
                    //final int rFinal = r;
                    final String queryTemplatesFinal =queryTemplates;
                    //rsTemp.first();
                    //rFinal=0;
                    //for(int r = 0;r<db.getRecordCount();r++)
                    int line =0;
                    while (rsTemp.next())
                    {
                        
                     line++;  
                     
                        //    rsTemp.getString("sxesoexoheader.esoexoHeaderId")+" - "+
                          JMenuItem menuItem = new JMenuItem(/*line+" . "+*/rsTemp.getString(strNameOfFieldOfTemplateMenu));
         menuItem.addActionListener(new ActionSelectTemplate( rsTemp.getString(fieldHeaderId), fieldHeaderId, queryTemplates,setFieldIsTemplate));
        popupMenuTemplates.add(menuItem);
         
     
                   }
                    
                     
                }
                
           }//try
           catch ( SQLException sqlex)
           {
               System.out.println("error:  PanelODOR.showTemplates:  "+sqlex.getMessage()+"            queryTemplates:"+queryTemplates);
               if(VariablesGlobal.globalShowPrintStackTrace)
               {
                   sqlex.printStackTrace();
               }
           }
            closeDB();                
                
                

  }
  
  
  private void selectTemplate(String pkId,String fieldHeaderId,String queryTemplates,String setFieldIsTemplate)
  {
      
        //--------------------------------------------
        //String setFieldIsTemplate = "isTemplate";
       //--------------------------------------------------
    
      //showSpecificRow(line, queryTemplates);
      
      rowNew(false,true); //boolean isFromCopyOfRecord, boolean boolAskToSaveChanges,boolean isFromTemplate
           for(int l = 0;l<listPanelOneDataOneRecData.size();l++)
           {
                panelOneDataOneRecData = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(l);      
                //panelOneDataOneRecData.setTemplateReplacementToShow(subqueryIsNotTemplateIn, subqueryIsTemplateIn);
                queryTemplates = utilsString.getQueryWithoutOrderby(queryTemplates)+" AND "+fieldHeaderId+" LIKE "+pkId+" "+utilsString.getOrderbySubQuery(queryTemplates);
                panelOneDataOneRecData.showSpecificRow(queryTemplates);
 

           EntityDBFields[] dbFieldsInGroupOfPanels = panelOneDataOneRecData.getDbFieldsInGroupOfPanels();
           
           System.out.println("  ======================================  PanelODOR.selectTemplate  l:"+l+"    queryTemplates:"+queryTemplates);
     /* for(int i = 0;i<dbFieldsInGroupOfPanels.length;i++)
      //for(int i = 0;i<dbFieldsAll.length;i++)  
      {
          //int fieldCount = i;//i-1; // calculates the no of field starting from 0 when i = 1
          String colName =   dbFieldsInGroupOfPanels[i].getDbField();
          String colTableName =dbFieldsInGroupOfPanels[i].getTableName();
          String colCaption =   dbFieldsInGroupOfPanels[i].getCaption();
          int columnWidth = dbFieldsInGroupOfPanels[i].getColWidth();
      	 //System.out.println("panelODORData.showRow i ("+i+") colName:"+colName+" class:"+dbFieldsInGroupOfPanels[i].getColClassName());
          String classtype = dbFieldsInGroupOfPanels[i].getColClassName();                
          
          if(classtype.equalsIgnoreCase("table"))
          { 
               System.out.println("  ======================================  PanelODOR.selectTemplate  l:"+l+"  i:"+i+"    colName:"+colName+"    colTableName:"+colTableName+"  queryTemplates:"+queryTemplates);
          }
          
      }*/      

           }
           
           isNewRec =true;
           
       setFieldsTemplateZero(setFieldIsTemplate);
      
  }
  
  
/*
 *  called by PanelOneDataManyRec.initialize
 *  allows outside classes to add actionlisteners
 */ // called from PanelEditOneDataRec
    public void addActionListenerSaveAndShowList(ActionListener al)
    {
      /*for (JButton button : buttons)
      {
        button.addActionListener(al);
      }*/
      //System.out.println("PanelODOR.addActionListenerShowList for ONE addActionListener: "+al);
      btnSaveAndShowList.addActionListener(al);
    }    
  
/*
 *  called by PanelOneDataManyRec.initialize
 *  allows outside classes to add actionlisteners
 */
    public void addActionListenerBackToList(ActionListener al)
    {
      /*for (JButton button : buttons)
      {
        button.addActionListener(al);
      }*/
      //System.out.println("PanelODOR.addActionListenerShowList for ONE addActionListener: "+al);
      btnBackToList.addActionListener(al);
    }    
    
    
   public void closeDB()
   {
       //System.out.println("PanelODOR.closeDB");
       
       db.releaseConnectionRs();
       db.releaseConnectionRsmd();
       
       for(int p=0;p<listPanelOneDataOneRecData.size();p++)
       {
           PanelOneDataOneRecData pnl = (PanelOneDataOneRecData)listPanelOneDataOneRecData.get(p);
   	    pnl.closeDB();
       }
	    //panelOneDataOneRecData.closeDB();	
   }
  
  public void closeDialog()
  {
      panelOneDataOneRecData.setVariablesGlobal1Erase();
      panelOneDataOneRecData.closeDB();
      closeDB();
  }

  
  public class ToolBarData extends JToolBar implements Constants
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
            
        btnInsert = new JButton();
        btnInsert.setHorizontalTextPosition(SwingConstants.CENTER);
        btnInsert.setVerticalTextPosition(SwingConstants.BOTTOM); 
        
        btnSelectTemplate = new JButtonListMenu();
        btnSelectTemplate.setHorizontalTextPosition(SwingConstants.CENTER);
        btnSelectTemplate.setVerticalTextPosition(SwingConstants.BOTTOM);  
        
        /*btnSaveAsTemplate = new JButton();
        btnSaveAsTemplate.setHorizontalTextPosition(SwingConstants.CENTER);
        btnSaveAsTemplate.setVerticalTextPosition(SwingConstants.BOTTOM); */       
        
        btnInsertFromCopy = new JButton();
        btnDelete = new JButton();
        btnDelete.setHorizontalTextPosition(SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(SwingConstants.BOTTOM);        
        
        btnSave = new JButton();
        btnSave.setHorizontalTextPosition(SwingConstants.CENTER);
        btnSave.setVerticalTextPosition(SwingConstants.BOTTOM);        
        
        btnSaveAndNew = new JButton();
        btnSaveAndNew.setHorizontalTextPosition(SwingConstants.CENTER);
        btnSaveAndNew.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        btnEditPreferences = new JButton();
        btnEditPreferences.setHorizontalTextPosition(SwingConstants.CENTER);
        btnEditPreferences.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        btnPrevious = new JButton();
        btnNext = new JButton();
        //btnFind = new JButton();
        
        btnCalculation = new JButton();// init
        btnCalculation.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCalculation.setVerticalTextPosition(SwingConstants.BOTTOM);           
        
 
        
        JPopupMenu popupMenuPreview = new JPopupMenu();
        btnPrintPreview = new JButton();
        btnPrintPreview.setHorizontalTextPosition(SwingConstants.CENTER);
        btnPrintPreview.setVerticalTextPosition(SwingConstants.BOTTOM);       
        // btnPrint = new JButton();
        //btnTemplates = new JButtonListMenu();
        //mItemTemplatesSelect = new JMenuItem("επιλογή προτύπου");
        //mItemTemplatesSaveAs = new JMenuItem("αποθήκευση ως πρότυπο");  
        	
        //setFloatable(false);
        //setRollover(true);
        //this.setOpaque(false);

        btnSaveAndShowList.setText("<html>αποθήκευση κ πίσω <b>F5</b></html>");
        btnSaveAndShowList.setHorizontalTextPosition(SwingConstants.CENTER);
        btnSaveAndShowList.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        //btnSaveAndShowList.setText("αποθήκευση και πίσω (F2)");
        btnSaveAndShowList.setOpaque(false);

        
        //btnFilters.setText("<html>προεπισκόπηση <b>O</b></html>");	    
       // btnFilters.setText("<html>προεπισκόπηση <b>alt+O</b></html>");
        btnSaveAndShowList.setToolTipText("αποθήκευση και πίσω στη λίστα");
        btnSaveAndShowList.setIcon(ICO_PANELBACKOK);
        //btnFilters.setMnemonic(KeyEvent.VK_O);
        btnSaveAndShowList.setFocusable(false);
        btnSaveAndShowList.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	 
	          //System.out.println("btnSaveAndShowList click "+e);
	        }
	    });
        Action actionSaveAndShowList = new ActionSaveAndShowList();
        btnSaveAndShowList.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F5"), "saveAndshowList"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnSaveAndShowList.getActionMap().put("saveAndshowList", actionSaveAndShowList);


        btnBackToList.setText("<html>πίσω χωρις αποθήκ. <b>F3</b></html>");
        btnBackToList.setHorizontalTextPosition(SwingConstants.CENTER);
        btnBackToList.setVerticalTextPosition(SwingConstants.BOTTOM);        
        //btnBackToList.setText("πίσω");
        //btnBackToList.setText("πίσω χωρις αποθήκ. (F3)");
        btnBackToList.setOpaque(false);
        //btnFilters.setText("<html>προεπισκόπηση <b>O</b></html>");	    
       // btnFilters.setText("<html>προεπισκόπηση <b>alt+O</b></html>");
        btnBackToList.setToolTipText("πίσω στη λίστα");
        btnBackToList.setIcon(ICO_PANELBACKCANCEL);
        //btnFilters.setMnemonic(KeyEvent.VK_O);
        btnBackToList.setFocusable(false);
        btnBackToList.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {
	          //System.out.println("btnSaveAndShowList click "+e);
	        }
	    });
        Action actionBackToList = new ActionBackToList();
        btnBackToList.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F3"), "backToList"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnBackToList.getActionMap().put("backToList", actionBackToList);        
        
        
        btnInsert.setText("<html>νέα <b>F9</b></html>");
        //btnInsert.setText("νέα (F9)");
        btnInsert.setOpaque(false);
        btnInsert.setToolTipText("νέα εγγραφή");
        btnInsert.setIcon(ICO_INSERT16);
        btnInsert.setFocusable(false);        
        btnInsert.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   
	           rowNew(false,true);	        //boolean isFromCopyOfRecord, boolean boolAskToSaveChanges,boolean isFromTemplate
	        } 
	    });
        Action actionNewRec = new ActionNewRec();
        btnInsert.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F9"), "newRec"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnInsert.getActionMap().put("newRec", actionNewRec);

        btnInsertFromCopy.setText("<html><b>ctrl F9</b></html>");
        //btnInsertFromCopy.setText("(ctrl F9)");
        btnInsertFromCopy.setOpaque(false);
        btnInsertFromCopy.setToolTipText("εισαγωγή εγγραφής με αντιγραφή επιλογής");
        btnInsertFromCopy.setIcon(ICO_INSERT16);
        btnInsertFromCopy.setFocusable(false);
        btnInsertFromCopy.addActionListener(new ActionListener()
	    {
	    
	        public void actionPerformed(ActionEvent e) 
	        {	   rowNew(true,true);  } // boolean isFromCopyOfRecord, boolean boolAskToSaveChanges,boolean isFromTemplate
	    });
   //     Action actionNewRecFromCopy = new ActionNewRecFromCopy();
   //     btnInsertFromCopy.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F9 ,java.awt.event.InputEvent.CTRL_DOWN_MASK ), "newRecFromCopy"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
   //     btnInsertFromCopy.getActionMap().put("newRecFromCopy", actionNewRecFromCopy);


        
        btnSelectTemplate.setText("<html>πρότυπα</html>");
        //btnSelectTemplate.setText("νέα (F9)");
        btnSelectTemplate.setOpaque(false);
        btnSelectTemplate.setToolTipText("επιλογή πρότυπου");
        btnSelectTemplate.setIcon(ICO_TEMPLATESELECT);
        btnSelectTemplate.setFocusable(false);   
        popupMenuTemplates = new JPopupMenu("----");
        btnSelectTemplate.setMenuPopup(popupMenuTemplates);          
        btnSelectTemplate.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   
	        	 showTemplates();
      
	        } 
	    });

 
      /*          btnSaveAsTemplate.setText("<html>αποθήκευση ως πρότυπο</html>");
        //btnSelectTemplate.setText("νέα (F9)");
        btnSaveAsTemplate.setOpaque(false);
        btnSaveAsTemplate.setToolTipText("αποθήκευση εγγραφής ως πρότυπο");
        btnSaveAsTemplate.setIcon(ICO_TEMPLATESAVEAS);
        btnSaveAsTemplate.setFocusable(false);        
        btnSaveAsTemplate.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   
	           //rowNew(false);	        
	        } 
	    });*/
        
        btnDelete.setText("<html>διαγραφή <b>F12</b></html>");
        //btnDelete.setText("διαγραφή (F11)");
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



        btnSaveAndNew.setText("<html>αποθήκ. κ νέα <b>F2</b></html>");
        
        btnSaveAndNew.setOpaque(false);
        btnSaveAndNew.setToolTipText("αποθήκευση και νέα εγγραφή");
        btnSaveAndNew.setIcon(ICO_SAVEANDNEW16);
        btnSaveAndNew.setFocusable(false);        
        btnSaveAndNew.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	
                    rowSaveAndNew();
	        
	        } 
	    });
        Action actionSaveAndNewRec = new ActionSaveAndNewRec();
        btnSaveAndNew.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F2"), "saveAndNewRec"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnSaveAndNew.getActionMap().put("saveAndNewRec", actionSaveAndNewRec);




        
        btnSave.setText("<html>αποθήκευση <b>F11</b></html>");
        //btnSave.setText("αποθήκευση (F11)");
        btnSave.setOpaque(false);
        btnSave.setToolTipText("αποθήκευση αλλαγών");
        btnSave.setIcon(ICO_UPDATE16);
        btnSave.setFocusable(false);
        //btnSave.setVerticalTextPosition(AbstractButton.BOTTOM);
        //btnSave.setHorizontalTextPosition(AbstractButton.CENTER);
        btnSave.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   

                if(rowSave(true))
                {
              	  setDataHasChanged(false);
                }   
	        } 
	    });
        Action actionSaveRec = new ActionSaveRec();
        btnSave.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F11"), "saveRec"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnSave.getActionMap().put("saveRec", actionSaveRec);

        btnEditPreferences.setText("<html>προ<b>Τ</b>ιμήσεις</html>");
        //btnSave.setText("αποθήκευση (F11)");
        btnEditPreferences.setOpaque(false);
        btnEditPreferences.setToolTipText("προτιμήσεις καταχώρησης");
        btnEditPreferences.setIcon(ICO_TABLE_PREFS16);
        btnEditPreferences.setMnemonic(KeyEvent.VK_T);
        btnEditPreferences.setFocusable(false);
        //btnSave.setVerticalTextPosition(AbstractButton.BOTTOM);
        //btnSave.setHorizontalTextPosition(AbstractButton.CENTER);
        btnEditPreferences.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   
                    
                    displayDialogEditPreferences();
                   
	        } 
	    });
 /*       Action actionPrefs = new ActionPrefs();
        btnSave.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F11"), "prefs"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnSave.getActionMap().put("saveRec", actionSaveRec);        
 */       
        
        btnPrevious.setText("<html></html>");
        btnPrevious.setOpaque(false);
        btnPrevious.setToolTipText("προηγούμενη εγγραφή");
//        btnPrevious.setIcon(ICO_DBPREVIOUS16);
        btnPrevious.setFocusable(false);
        btnPrevious.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   
//	           panelOneDataOneRecData.showPrevRow();	        
	        } 
	    });

        btnNext.setText("<html></html>");
        btnNext.setOpaque(false);
        btnNext.setToolTipText("επόμενη εγγραφή");
//        btnNext.setIcon(ICO_DBNEXT16);
        btnNext.setFocusable(false);        
        btnNext.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   
//	           panelOneDataOneRecData.showNextRow();	        
	        } 
	    });


       /* btnTemplates.setText("<html>πρότυπα</html>");
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
	    popupMenuTemplates.add( mItemTemplatesSaveAs);
        
        mItemTemplatesSaveAs.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   displayDialogTemplateNew();  }
	    });        

        mItemTemplatesSelect.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   displayDialogTemplateSelect();  }
	    }); */


            
            
            
           btnCalculation.setText("<html>υπολογισμός</html>");
        //btnCalculation.setText("εκτύπωση (F7)");
        btnCalculation.setOpaque(false);
        //btnCalculation.setText("<html>προεπισκόπηση <b>O</b></html>");	    
       // btnCalculation.setText("<html>προεπισκόπηση <b>alt+O</b></html>");
        btnCalculation.setToolTipText("υπολογισμός");
        btnCalculation.setIcon(ICO_TASK);
        //btnPrintPreview.setMnemonic(KeyEvent.VK_O);
        btnCalculation.setFocusable(false);
        btnCalculation.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   
                    calculationFromButton(); //displayPanelReport();  calculation
                }
	    });
       // Action actionPrintPreview = new ActionPrintPreview();
       // btnCalculation.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), "printPreview"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
       // btnCalculation.getActionMap().put("printPreview", actionPrintPreview);
       
           
       
       
       
	    
        btnPrintPreview.setText("<html>προεπισκόπηση...<b>F7</b></html>");
        //btnPrintPreview.setText("προεπισκόπηση (F7)");
        btnPrintPreview.setOpaque(false);
        btnPrintPreview.setToolTipText("προεπισκόπηση...");
        btnPrintPreview.setIcon(ICO_PRINT_PREVIEW_FORM16);
        btnPrintPreview.setFocusable(false);         
        btnPrintPreview.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {
                         //  showPrintPreviewForm(false);
                    showPrintPreviewFormIfDataNotChanged(false); 
	        }
	    });
        Action actionPrintPreview = new ActionPrintPreview();
        btnPrintPreview.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), "reportpreview"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnPrintPreview.getActionMap().put("reportpreview", actionPrintPreview);

        
        
    /*    btnPrint.setText("<html>εκτύπωση...<b>F8</b></html>");
        //btnPrint.setText("προεπισκόπηση (F8)");
        btnPrint.setOpaque(false);
        btnPrint.setToolTipText("εκτύπωση...");
        btnPrint.setMnemonic(KeyEvent.VK_P);
        btnPrint.setIcon(ICO_PRINT_PREVIEW16);
        btnPrint.setFocusable(false);        
        btnPrint.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {
                    
                    displayGoToPrintPrefsDialog();
	        }
	    });
        Action actionPrint = new ActionPrint();
        btnPrint.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F8"), "report"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnPrint.getActionMap().put("report", actionPrint);        
        */
         this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        
        //addSeparator();  
        //add(lblIcoSeparator1);	              
        add(btnSaveAndShowList);
        add(btnBackToList);
        add(btnSaveAndNew);
        //addSeparator();
       // addSeparator();
        
       //if(VariablesGlobal.globalEntityHasTemplatesTOCORRECT)
       //{
        add(btnSelectTemplate);
       //}
       
        add(btnInsert);
       
      //  add(btnInsertFromCopy);
        add(btnSave);
        add(btnDelete);
        //addSeparator();	              
        //addSeparator();
        //add( btnPrevious );
        //add( btnNext );
        //addSeparator();
        //addSeparator(); 
        //add(btnTemplates);    
   //     add(btnSaveAsTemplate);
       
        add(btnCalculation);

           add(btnPrintPreview);

       // add(btnPrint);
            //if(btnEditPreferences.isVisible())
            //{
            //addSeparator();	     
            add(btnEditPreferences);
            //}
        //addSeparator();
        //addSeparator();
        }
  
        public void showBtnCalculation(boolean isVisible)
        {
        
            btnCalculation.setVisible(isVisible);
        }

       
        
        public void showOnlySaveNPrintNNewNDelButton()
        {
        
            btnInsert.setVisible(true);
            btnSelectTemplate.setVisible(true);
            //btnSaveAsTemplate.setVisible(true);
            btnDelete.setVisible(true);
            btnSave.setVisible(true);
            btnSaveAndNew.setVisible(true);
            //lblIcoSeparator2.setVisible(true);              
            btnPrevious.setVisible(false);
            btnNext.setVisible(false);
            //lblIcoSeparator3.setVisible(false);            
            btnPrintPreview.setVisible(true);
            //lblIcoSeparator4.setVisible(true);     	
        	
        }
  
        public void showOnlySaveNPrintButton()
        {
        
            btnInsert.setVisible(false);
            btnSelectTemplate.setVisible(false);
            //btnSaveAsTemplate.setVisible(false);
            btnDelete.setVisible(false);
            btnSave.setVisible(true);
            btnSaveAndNew.setVisible(false);
            //lblIcoSeparator2.setVisible(true);              
            btnPrevious.setVisible(false);
            btnNext.setVisible(false);
            //lblIcoSeparator3.setVisible(false);            
            btnPrintPreview.setVisible(true);
            //lblIcoSeparator4.setVisible(true);     	
        	
        }        
        
       protected void paintComponent(Graphics g)
        {
              /*Graphics2D g2 = (Graphics2D) g;   //                     15
             GradientPaint paint = new GradientPaint(0, 0, this.getBackground().brighter(), 0, 30, this.getBackground().darker(),true);
             g2.setPaint(paint);
             g2.fill(getBounds());*/
             super.paintComponent(g);
        }

       
}
  
  
      class  ActionSelectTemplate implements ActionListener
      {
            private String pkId;
            private String fieldHeaderId;
            private String queryTemplates;
            private String subqueryIsNotTemplate;
            private String subqueryIsTemplate;
            private String setFieldIsTemplate;
        public ActionSelectTemplate(String pkIdIn,String fieldHeaderIdIn,String queryTemplatesIn,String setFieldIsTemplateIn)
        {
            pkId=pkIdIn;
            fieldHeaderId = fieldHeaderIdIn;
            queryTemplates = queryTemplatesIn;
            //subqueryIsNotTemplate=subqueryIsNotTemplateIn;
            //subqueryIsTemplate=subqueryIsTemplateIn;
            setFieldIsTemplate=setFieldIsTemplateIn;
        
        }        
       
            @Override
        public void actionPerformed(ActionEvent ev)
        {
                selectTemplate(pkId, fieldHeaderId, queryTemplates, setFieldIsTemplate);
        }
         
      }
  
     class  ActionBackToList extends AbstractAction                 
   {       
        public ActionBackToList()
        {      }
      	
    	public void actionPerformed(ActionEvent e)
      	{     
      	  btnBackToList.doClick();
      	}    	
    }     
     
   
     class  ActionSaveAndShowList extends AbstractAction                 
   {       
        public ActionSaveAndShowList()
        {      }
      	
    	public void actionPerformed(ActionEvent e)
      	{     
      	  btnSaveAndShowList.doClick();
      	}    	
    }  
  
   class  ActionNewRec extends AbstractAction                 
   {       
        public ActionNewRec()
        {        }
      	
    	public void actionPerformed(ActionEvent e)
      	{
           btnInsert.doClick();
        }    	
    }

   class  ActionDelRec extends AbstractAction                 
   {       
        public ActionDelRec()
        {        }
      	
    	public void actionPerformed(ActionEvent e)
        {
           btnDelete.doClick();
        }    	
    }                

   class  ActionSaveRec extends AbstractAction                 
   {       
        public ActionSaveRec()
        {        }
      	
    	public void actionPerformed(ActionEvent e)
      	{          btnSave.doClick();        }    	
    }                

   
   class  ActionSaveAndNewRec extends AbstractAction                 
   {       
        public ActionSaveAndNewRec()
        {        }
      	
    	public void actionPerformed(ActionEvent e)
      	{          btnSaveAndNew.doClick();        }    	
    }   
   
   
   
   
   
   class  ActionPrintPreview extends AbstractAction                 
   {       
        public ActionPrintPreview()
        {        }
    	public void actionPerformed(ActionEvent e)
      	{          btnPrintPreview.doClick();        }    	
    } 

  // this allows outside classes to add actionlisteners
    public void addActionListenerInsert(ActionListener al)
    {
      
      btnInsert.addActionListener(al);
      btnInsertFromCopy.addActionListener(al);
    }
    
    
    
   /*class  ActionPrint extends AbstractAction                 
   {       
        public ActionPrint()
        {        }
    	public void actionPerformed(ActionEvent e)
      	{          btnPrint.doClick();        }    	
    } */


}