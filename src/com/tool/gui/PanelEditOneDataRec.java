// PanelEditOneDataRec.java
// created 02-07-2009

package com.tool.gui;

import com.tool.gui.PanelOneDataOneRec;
//import com.tool.gui.PanelTwoDataOneRec;
import com.tool.model.EntityGroupOfPanels;
import com.tool.model.EntityPanel;
import com.tool.model.EntityPanelM;
import com.tool.jdbc.*;
import com.tool.gui.*;
import com.tool.guicomps.Constants;
import com.tool.guicomps.JPanelDecorated;
import com.tool.guicomps.JxPanel;
import com.tool.guicomps.JxToggleButton;
import com.tool.guicomps.PanelCollapsable;
import com.tool.guicomps.PanelGradient;
import com.tool.guicomps.PanelManagement;
import com.tool.model.*;
import com.tool.utils.*;


import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*; 
import javax.swing.tree.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.BevelBorder;

 public class PanelEditOneDataRec extends JPanel implements Constants
 {
   private PanelOneDataOneRec panelOneDataOneRec;
   //private PanelTwoDataOneRec panelTwoDataOneRec;
   private PanelStatistics panelStatistics;
//   private ArrayList listPanelOneDataOneRec;
   //private JTreeDec treeNavigation;
   //private NavigationTreeModel navigationTreeModel;
   private JScrollPane treeScrollPane = new JScrollPane();
   //JFrame parent;
   private UtilsGui utilsGui;
   private JDialog parentD;
   private JxPanel panelMainCard;
  //private JxPanel panelMainCardPanelsODORData;// it exists into panelMainCard
  //private JPanel panelOnMain;

  private JPanel panelSouth;
  //private JButton btnClose;
  //private JButton btnOk;
  //private boolean dataOne;
  private ResultSet rs;
  private Database db;
  
        /*JLabel lblIcoSeparator1;
        JLabel lblIcoSeparator2;
        JLabel lblIcoSeparator3;
        JLabel lblIcoSeparator4 ;
        JLabel lblIcoSeparator5 ;*/
  
  private String entity;
  private String title;
  // private String query;
  // private String primKeyDb;
  private String[] primKeys;
   private String[] primKeysValue;
   private boolean isNewRec;
   
   private boolean isNewRecFromCopy;
   private String[] fieldsOnTitle;
   private String[] fieldsOnTitleCaption;
   private EntityPanel[] entityPanel;
   private String[] categoryNodes;
   private EntityPanelM epm;
   private JLabel lblTitle;
   private JPanelDecorated panelMain;
   JFrame parent;
  private boolean showShowListButtons;
  String query;
       //private JButton btnSaveAndShowList;
      //private JButton btnBack;
     private JButton    btnBackToListAskToSave;
       private ToolBarData toolBarData;
  private PanelManagement panelManagement;
 // private PanelCollapsable panelCollapsable;
  private JxPanel panelTitleAndMenu;
  private JxPanel panelTop;
  private  JxPanel panelMenuDataEntry;
  private JxPanel panelMenuStatistics ;        
  private PanelCollapsable panelCollapsCategoryDataEntry ;
  private PanelCollapsable panelCollapsCategoryStatistics ; 
  private ArrayList listBtnMenu;
  private EntityUpdateAdditional[] updateAdditional;
 //private String[] fieldsForSumsMany;
  //private UtilsDouble uDouble;
  private EntityReport entityReportForm;
  private int btnMenuWidth = 115;
  private int btnMenuHeight = 28;
  //private String formGlobalTableToGet1;
  //private String formGlobalTableToApply1;
  //private String formGlobalField1;
  private UtilsString utilsString ;
  private  UtilsPanelReport utilsPanelReport ;
  private int selectedTableRow;
  private String primKeyValue;
    public PanelEditOneDataRec(JFrame parent) 
    {
    	super();
        try {
            initialize(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //pack();
    }

    
    private void initialize(JFrame parentIn) throws Exception
    {
    	parent= parentIn;
       db= new Database();
       epm= new EntityPanelM();
       utilsString = new UtilsString();
       utilsPanelReport = new UtilsPanelReport();
        /*uDouble=new UtilsDouble();
        System.out.println("PanelEditOneDataRec init");
        uDouble.getSettingsFromDb();       */
       
       
       //setCloseClick();
       //panelMain = new JPanel();
       //               Color blue= new Color(129, 169, 226);
       //       Color lightBlue= new Color(196,218,246);//220,235,253);//148, 215, 254);
        panelMain = new JPanelDecorated();//new CurvedGradientPanel(blue,lightBlue,0,0);       
       
       panelMain.setLayout(new BorderLayout());

       toolBarData = new ToolBarData();
       
       panelTitleAndMenu = new JxPanel();

       listBtnMenu = new ArrayList();
       
       panelMainCard= new JxPanel();
       panelMainCard.setOpaque(false);
       panelMainCard.setLayout(new CardLayout());
       panelMainCard.setBorder(null);

       
        lblTitle = new JLabel("επεξεργασία");
        lblTitle.setOpaque(true);
        lblTitle.setIcon(ICO_TABLE16);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD));
       //lblTitle.setBackground(java.awt.SystemColor.activeCaption);//panel.getBackground().brighter());        
        //lblTitle.setFont(new java.awt.Font("Century", 1, 16));
        lblTitle.setForeground(CLR_TITLE_CAPTION);//Color.white);Color.white);
        lblTitle.setFocusable(false);
        lblTitle.setOpaque(false);

        PanelGradient paneTitle = new PanelGradient(CLR_TITLE_BACKGROUND_START,CLR_TITLE_BACKGROUND_END,16);//(new Color(0, 0, 0, 0),this.getBackground().darker().darker().darker().darker(),26);//new PanelTitle(new Color(0, 0, 0, 0),this.getBackground().darker().darker().darker().darker(),Color.white,"title");
        paneTitle.add(lblTitle);

	panelTop = new JxPanel();
	    panelTop.setBorder(null);
        panelTop.setLayout(new BorderLayout());
 //       panelTop.add(paneTitle,BorderLayout.CENTER);
        panelTop.add(toolBarData,BorderLayout.PAGE_START); 
        
        //btnBack = new JButton();
         
        treeScrollPane.setMinimumSize(new Dimension(120, 60));//Dimension(int width, int height) 
        treeScrollPane.setPreferredSize(new Dimension(120, 245));
        treeScrollPane.setOpaque(false);

        
        
       panelOneDataOneRec = new PanelOneDataOneRec(parent);
       //panelTwoDataOneRec = new PanelTwoDataOneRec(parent);
       panelStatistics =  new PanelStatistics(parent);
       
       
       

       utilsGui = new UtilsGui();
        

	JPanelDecorated panelContainer = new JPanelDecorated();
        panelContainer.setLayout(new BorderLayout());  //new BoxLayout(panelContainer, BoxLayout.PAGE_AXIS));
    
        panelTitleAndMenu.setLayout( new BoxLayout(panelTitleAndMenu, BoxLayout.PAGE_AXIS));
        
    
        panelContainer.add(panelTitleAndMenu, BorderLayout.PAGE_START);

        panelContainer.setBackground(panelMain.getBackground().darker()); //CLR_TOOLTIP);
       
        panelMain.add(panelContainer,BorderLayout.LINE_START);
        panelMain.add(panelMainCard,BorderLayout.CENTER);
        this.setLayout(new BorderLayout());
        add(panelMain, BorderLayout.CENTER);
        
        
             panelMenuDataEntry = new JxPanel();
            panelMenuDataEntry.setLayout(new GridLayout(0,1));
            
             panelMenuStatistics = new JxPanel();
            panelMenuStatistics.setLayout(new GridLayout(0,1));
            
             panelCollapsCategoryDataEntry = new PanelCollapsable(panelMenuDataEntry,null, DATAENTRY, true,CLR_PANEL_START_ALTER, CLR_PANEL_END_ALTER); 
             panelCollapsCategoryStatistics = new PanelCollapsable(panelMenuStatistics,null, METRICS, true, CLR_PANEL_START_ALTER,CLR_PANEL_END_ALTER); 
   
            
         setPageSelectionKeys();//same in PanelEditOneDataRec and PanelManyDataManyRec 
        
    }
      	
      	
     // called by 	PanelOneDataManyRec.displayDialogEdit,   PanelOneDataManyRec.showPanelEditOneDataRec, RepoprtAreaGenerated.displayDrillDialog, tableCellEditorLookupOne.displayDialogEdit
     public int setEntity(String entityIn, EntityPanel[] entityPanelIn,int selectedTableRowIn,String[]fieldsOnTitleIn, String[] fieldsOnTitleCaptionIn, 
    boolean isMasterUnique, String[] primKeysIn, String[] primKeysValueIn/*,String primKeyValueIn/*when is ie dbcompanysettings*/,/*String formGlobalTableToGet1In, String formGlobalTableToApply1In,*//*String formGlobalField1In,*/
    String queryIn,String titleIn,ImageIcon ico, boolean isNewRecIn,boolean isNewRecFromCopyIn ,
    boolean showBtnOk, String[] categoryNodesIn, boolean showShowListButtonsIn ,PanelManagement panelManagementIn)//,EntityReport entityReportFormIn)
    {
         int selected = 0; //when 0 do not display
 
        entity=entityIn;
        title=titleIn;
        query=queryIn;
        //primKeyDb=primKeyDbIn;
        //formGlobalTableToGet1=formGlobalTableToGet1In;
        //formGlobalTableToApply1=formGlobalTableToApply1In;
        //formGlobalField1 =formGlobalField1In;
        primKeys=primKeysIn;
        primKeysValue=primKeysValueIn;
  //      primKeyValue=primKeyValueIn;
        isNewRec=isNewRecIn;
        isNewRecFromCopy=isNewRecFromCopyIn;
        fieldsOnTitle=fieldsOnTitleIn;
        fieldsOnTitleCaption=fieldsOnTitleCaptionIn;
        entityPanel=entityPanelIn;
        categoryNodes=categoryNodesIn;
        showShowListButtons=showShowListButtonsIn;
        //dataOne=dataOneIn;    
        panelManagement=panelManagementIn;        
        //entityReportForm = 
        selectedTableRow=selectedTableRowIn;
              
       //System.out.println("PanelEditOneDataRec.setEntity   ==oo==      entity:"+entity+"   query:"+query);

        if(ico!=null)
        {
          lblTitle.setIcon(ico);	
        }
        else
        {
        	lblTitle.setIcon(ICO_EDIT16);
        }

        
       //System.out.println("PanelEditODR.setEntity  showShowListButtons:"+showShowListButtons);
        //if are shown there is also code in this.addVisibleMenuButtonsAndPanels
        if(showShowListButtons) //if is on dialog do not show showListButton
        {
        	// btnSaveAndShowList.setVisible(true);
                btnBackToListAskToSave.setVisible(true);
        	lblTitle.setVisible(true);
        }
        else
        {
        	toolBarData.setVisible(false);
               // btnSaveAndShowList.setVisible(false);
                btnBackToListAskToSave.setVisible(false);
        }
        EntityDBFields[] dbFieldsAll = entityPanel[0].getDBFields();
        
  	   // db.retrieveDBDataFromQuery(query,"PanelEditOneDataRec.setEntity");
   	    //rs=db.getRS(); 
        //System.out.println("PanelEditOneDataRec.setEntity   ==o======o==    entity:"+entity+"  "+primKeyDb+":"+primKeyValue+"    query:"+query);
        //int selectedRow = utilsPanelReport.getRowForPrimKey("PanelEditOneDataRec.setEntity",query,rs,dbFieldsAll,primKeyDb,primKeyValue);
        
        //utilsPanelReport.retrievePrimKeyValueForOnePK( query, selectedTableRow, null,dbFieldsAll,true, entity, primKeyDb); 
        if(primKeys!=null)
        {
        int lengthOfPKs = primKeys.length;//utilsPanelReport.getPrimKeys().length;
        //String[] primKeyFields = utilsPanelReport.getPrimKeys();
        //String[] primKeyFieldValues = utilsPanelReport.getPrimKeysValue();
        for(int pk=0;pk<lengthOfPKs;pk++)
        {
                        if(utilsString.hasQueryWhere(query))
                        {
                            String befWhere = utilsString.getQueryBeforeWhere(query);
                            String aftWhere = utilsString.getQueryAfterWhere(query);
                            String qWhere = utilsString.getQueryWhere(query);
                            qWhere = qWhere + " AND "+entity+"."+primKeys[pk]+" LIKE "+primKeysValue[pk]+" ";
                            query = befWhere + qWhere + aftWhere;
                        }    
                        else
                        {
                            System.out.println("--------*   PanelEditOneDataRec.setEntity   query has no where  query:"+query);
                        } 
        }
        }
        System.out.println("  --OOOOOO--   panelEditOneDataRec setEntity --------------------------------------- selectedTableRow:"+selectedTableRow+"  entityPanel.length:"+entityPanel.length+"        query:"+query+"     "+entityPanel[0].getQuery());
        
        
        
        
        if(entityPanel==null)
        {
        	
	System.out.println("PanelEditOneDataRec.setEntity  ==o==     entityPanel:"+entityPanel+"   Check EntityData!");
        /*if (dataOne)
        {
            //panelOneDataOneRec.setEntity(entity,fields,fieldsTranslation,primKey,primKeyValue,primKeyDb, query, null, ico,isNewRec,showToolBar,isMasterUnique,isMany,null,null,false);
            //panelMainCard.add(panelOneDataOneRec, BorderLayout.CENTER);
           
            
            /*
            // load all panelOneDataOneRec panels, then show
            for(int cn =0; cn<entityPanel.length;cn++)        
            {                /*for(int e=entityGroupOfPanels.length-1;e>=0;e--)
          	      	{
          	      		//DefaultMutableTreeNode leaf = new DefaultMutableTreeNode(entityGroupOfPanels[e].getCaption());
          	      		//System.out.println("PanelEditODR.setVisibleTreeLeafs "+e+" " + entityGroupOfPanels[e].getCaption());
          	      		
          	      		
          	      		
          	      		//navigationTreeModel.insertNodeInto(leaf, base, e);
          	      	}
            
                       
                        if(entityGroupOfPanels.length>1)
          	      {*/
          	/*EntityGroupOfPanels[] entityGroupOfPanels = entityPanel[cn].getEntityGroupOfPanels();
                         //for(int e=entityGroupOfPanels.length-1;e>=0;e--)
                        for(int e=0; e<entityGroupOfPanels.length;e++)
          	      	{
          	      		
          	      		//System.out.println("PanelEditODR.setVisibleTreeLeafs "+e+" " + entityGroupOfPanels[e].getCaption());
          	      		
          	      		//loadEditPanels(entityPanel[cn],entityGroupOfPanels[e],e);
          	      		
                            loadPanelsODOR(entityPanel[cn],entityGroupOfPanels[e],e);
          	      	}                                 
                      
                      
                      
                          
            }*/
                        
                        
            
            //(EntityPanel ep,EntityGroupOfPanels entityGroupOfPanels, int intGroupOfPanelsToShow)
            // entityPanel[cnFinal],entityGroupOfPanelsFinal[eFinal],eFinal

        }
        else //if(entityPanel!=null)
        {
            
          //if(entityPanel.length==1)	
         // {
             // toolBarData.setVisible(false);
                //btnSaveAndShowList.setVisible(false);
                //btnBackToListAskToSave.setVisible(false);
              //	panelEast.setVisible(false);
          //}
          //else if(entityPanel.length>1)
          //{
                //System.out.println("PanelEditODR.setEntity length:"+entityPanel.length);
                //toolBarData.setVisible(true);
                //btnSaveAndShowList.setVisible(true);
                //btnBackToListAskToSave.setVisible(true);
              //panelEast.setVisible(true);
         // }

           boolean[] isVisibleType={true,true,true};
           selected = addVisibleMenuButtonsAndPanels(isVisibleType);
//           panelOneDataOneRec.setGuiLoaded(true);

        }//else

        return selected; // when 0 do not display
     }

    /*
     *  isVisibleType dataentry, stats, ...
     * boolean 0 ODOR, 1 STATS, 2 TDOR
     *  called by PanelODOR
     */     
  public void setMenuButtonsVisible( boolean[] isVisibleType)   
  {
            //System.out.println("PanelEditODR.setMenuButtonsVisible  isVisibleType="+isVisibleType[0]+"  "+isVisibleType[1]+"  "+isVisibleType[2]);
            if(isVisibleType[0]) // ODOR
            {
               panelCollapsCategoryDataEntry.setVisible(true);
            }
            else
            {
                panelCollapsCategoryDataEntry.setVisible(false);
                //System.out.println("PanelEditODR.setMenuButtonsVisible isVisibleType[0]="+isVisibleType[0]);
            }
            
            if(isVisibleType[1] )// STATS
            {
               panelCollapsCategoryStatistics.setVisible(true);
            }
            else
            {
                panelCollapsCategoryStatistics.setVisible(false);
                 //System.out.println("PanelEditODR.setMenuButtonsVisible isVisibleType[1]="+isVisibleType[1]+" isNewRecIn="+isNewRecIn);
            }
            
            if(isVisibleType[2] )// TDOR
            {
                //System.out.println("PanelEditODR.setMenuButtonsVisible TDOR isVisibleType[2]="+isVisibleType[2]);
            }
            else
            {
                //System.out.println("PanelEditODR.setMenuButtonsVisible TDOR isVisibleType[2]="+isVisibleType[2]);
            }
  }
     
   
    /*
     *  isVisibleType dataentry, stats, ...
     * boolean 0 ODOR, 1 STATS, 2 TDOR
     */
    private int addVisibleMenuButtonsAndPanels( boolean[] isVisibleType)
    { 
       int selected = 0; // when 0 do not display
                
           //System.out.println("PanelEditODR.addMenuButtons +++ entityPanel.length "+entityPanel.length+" isVisibleType[0]="+isVisibleType[0]+" isVisibleType[1]="+isVisibleType[1]);
           panelMenuDataEntry.removeAll();
           panelMenuStatistics.removeAll();
           panelTitleAndMenu.removeAll();
           panelTitleAndMenu.add(panelTop);
             //System.out.println("++++PanelEditODR.addVisibleMenuButtonsAndPanels isVisibleType[0]="+isVisibleType[0]+"     isVisibleType[1]="+isVisibleType[1]+" isNewRec="+isNewRec  +"     entityPanel.length:"+entityPanel.length);
            if(isVisibleType[0])
            {
                if( entityPanel.length>=1 )// ==1, dataentry is only one panel, > because it may have and stats
                {
                    EntityGroupOfPanels[] entityGroupOfPanels = entityPanel[0].getEntityGroupOfPanels();
                   if(entityGroupOfPanels!=null && entityGroupOfPanels.length>1) 
                   {
                     panelTitleAndMenu.add(panelCollapsCategoryDataEntry);  
                   }
                   else
                   {
                       panelTitleAndMenu.removeAll();
                   }
                }
                else
                {
                    panelTitleAndMenu.removeAll();
                }
               
            }
            else
            {
                System.out.println("PanelEditODR.addVisibleMenuButtonsAndPanels isVisibleType[0]="+isVisibleType[0]);
            }
            
            if(isVisibleType[1] || !isNewRec)
            {
                if( entityPanel.length!=1 ) // ==1, dataentry is only one panel
                {
                    panelTitleAndMenu.add(panelCollapsCategoryStatistics);         
                }
                else 
                {
//                   panelTitleAndMenu.removeAll();
                }                
               
            }
            else
            {
                 System.out.println("PanelEditODR.addVisibleMenuButtonsAndPanels isVisibleType[1]="+isVisibleType[1]+" isNewRec="+isNewRec);
            }
            
       
         
         
    	   //for(int cn=entityPanel.length-1;cn>=0;cn--)
            
            ButtonGroup buttonGroup = new ButtonGroup();
            panelMainCard.removeAll(); // all panelODORD
            listBtnMenu.clear();
            int btnMenuInt=0;
//            askIfDataChangedToSave();
    //        panelOneDataOneRec.setGuiLoaded(false);
          System.out.println("PanelEditOneDataRec.addVisibleMenuButtonsAndPanels  +++   selectedTableRow:"+selectedTableRow+" entityPanel.length="+entityPanel.length+"  "+entityPanel[0].getSqlOne());
            
            

          for(int cn =0; cn<entityPanel.length;cn++)
          {
  	     //   DefaultMutableTreeNode base;
            
            //System.out.println("PanelEditODR.addVisibleMenuButtonsAndPanels -FOR- " + cn+" type="+entityPanel[cn].getType()+" title="+entityPanel[cn].getTitle());
            epm.addEntityPanel(entityPanel[cn]);
//->            entityPanel[cn].getDrillEntityPanel();
            //System.out.println("PanelEditODR.addVisibleMenuButtonsAndPanels     AAAA-=-=-=-=-=-=-=-=-AAA  type:"+entityPanel[cn].getType());
            /*if(entityPanel[cn].getType().equalsIgnoreCase("ODOR"))
            {    
            System.out.println("PanelEditODR.addVisibleMenuButtonsAndPanels     AAAA-=-=-=-=-=-=-=-=-AAA  type:"+entityPanel[cn].getType()+"   cn:"+ cn+"        drillpanel:"+entityPanel[cn].getDrillEntityPanel()+"     getEntityReportForm bands 0"+entityPanel[cn].getEntityReportForm().getEntityReportBands()[0]);
            }
            else if(entityPanel[cn].getType().equalsIgnoreCase("STATS"))
            {
            System.out.println("PanelEditODR.addVisibleMenuButtonsAndPanels     AAAA-=-=-=-=-=-=-=-=-AAA  type:"+entityPanel[cn].getType()+"   cn:"+ cn+"        drillpanel:"+entityPanel[cn].getDrillEntityPanel()+"     getEntityReportForm bands 0"+entityPanel[cn].getEntityReportForm().getEntityReportBands()[0]);    
            }*/
            
            if(entityPanel[cn].getType().equalsIgnoreCase("ODOR") ||  (entityPanel[cn].getType().equalsIgnoreCase("MDMR")))
            {
               //System.out.println("  ======== PanelEditODR.addVisibleMenuButtonsAndPanels  " + cn+"  type:"+entityPanel[cn].getType());
               	  EntityGroupOfPanels[] entityGroupOfPanels = entityPanel[cn].getEntityGroupOfPanels();
                  //JxToggleButton btnMenu = new JxToggleButton();
                  
               if(entityGroupOfPanels==null)// || entityGroupOfPanels.length==1)  // ONLY ONE panel of data entry with or without statistics
               {

                           final String  titleOfPanel = entityPanel[cn].getTitle(); 
               
                       selected = panelOneDataOneRec.setEntity(entity,entityPanel[cn],selectedTableRow,/*primKeyValue,*/query,primKeys,primKeysValue,titleOfPanel+" "+title, isNewRec, isNewRecFromCopy,
                       /*formGlobalTableToGet1,formGlobalTableToApply1,*/fieldsOnTitle, fieldsOnTitleCaption, false, "",showShowListButtons, true,
                       entityPanel[cn].getIco(),panelManagement,this,IS_CALLED_BY_ONE_TABLE_ODOR);
                           
                           //System.out.println("PanelEditODR.addVisibleMenuButtonsAndPanels  +++  ODOR loadPanelsODOR  entityGroupOfPanels=null  cn=" + cn+" titleOfPanel="+titleOfPanel);
                           //System.out.println(" --  PanelEditOneDataRec.addVisibleMenuButtonsAndPanels   size"+panelMainCard.getComponentCount());
                          panelMainCard.add(panelOneDataOneRec, titleOfPanel);  
                           
                             JxToggleButton btnMenu = new JxToggleButton();
                             btnMenu.setPreferredSize(new Dimension(btnMenuWidth, btnMenuHeight));
                             
                             buttonGroup.add(btnMenu);
                             listBtnMenu.add(btnMenu);
                             btnMenuInt++;
                             btnMenu.setFocusable(false);
                             btnMenu.setIcon(ICO_TABLE16);
                             btnMenu.setHorizontalAlignment(SwingConstants.LEFT);
                             btnMenu.setText(entityPanel[cn].getTitle());
                             btnMenu.setToolTipText(entityPanel[cn].getTitle());
                             btnMenu.addActionListener(new ActionListener()
                                 {
                                      @Override
                                   public void actionPerformed(ActionEvent e)
                                   {
                                   
                     CardLayout cl = (CardLayout)(panelMainCard.getLayout());
                     cl.show(panelMainCard, titleOfPanel);  //entityGroupOfPanelsFinal[eFinal].getCaption());                                     
       
                                   }
                                 });
                              
                          // when shown (init), before selected 
                            panelMenuDataEntry.add(btnMenu);
                                btnMenu.doClick();
             }
             else if(entityGroupOfPanels!=null) //  MORE THAN ONE panels of data entry with statistics
             {
          	      	//ArrayList listBtnMenu = new ArrayList();
                        //System.out.println("PanelEditODR.addVisibleMenuButtonsAndPanels +++ entityGroupOfPanels=!null  cn="+cn);  
                           final String  titleOfPanel = entityPanel[cn].getTitle(); 
                   
      
                 selected = panelOneDataOneRec.setEntity(entity,/*entityPanel,*/entityPanel[cn],selectedTableRow,/*primKeyValue,*/query,primKeys,primKeysValue,titleOfPanel+" "+title, isNewRec, isNewRecFromCopy,
                         /*formGlobalTableToGet1,formGlobalTableToApply1,*/fieldsOnTitle,
                          fieldsOnTitleCaption, false, "",showShowListButtons, true, entityPanel[cn].getIco(),/*cn,*/panelManagement,this,IS_CALLED_BY_ONE_TABLE_ODOR);//,entityReportForm); 
                 
                  JxToggleButton btnMenu ;

                  for(int cp = 0;cp<entityGroupOfPanels.length;cp++) 
                  {
                              String caption =  entityGroupOfPanels[cp].getCaption();
                       //System.out.println("PanelEditODR.addVisibleMenuButtonsAndPanels +++ entityGroupOfPanels=!null  cn="+cn+"   caption:"+caption);                                 
                           btnMenu = new JxToggleButton();
                           btnMenu.setPreferredSize(new Dimension(btnMenuWidth, btnMenuHeight));
                           listBtnMenu.add(btnMenu); 
                           btnMenuInt++;
                           buttonGroup.add(btnMenu); 
                           btnMenu.setFocusable(false);
                           
                           btnMenu.setHorizontalAlignment(SwingConstants.LEFT);
                           btnMenu.setIcon(ICO_TABLE16);
                          // final int cpFinal=cp;
                 	  // final EntityGroupOfPanels[] entityGroupOfPanelsFinal=entityGroupOfPanels;
                           btnMenu.setText(caption);
                           btnMenu.setToolTipText(caption);                  
                           final String captionFinal = caption;
                           final PanelOneDataOneRec panelOneDataOneRecNewFinal = panelOneDataOneRec;
                          
                          final EntityGroupOfPanels[] entityGroupOfPanelsFinal = entityGroupOfPanels;
                           final  EntityPanelM epmFin= epm;
                          final int cnFinal = cn;
                          final int cpFinal = cp;
                          btnMenu.addActionListener(new ActionListener()
                          {
                              @Override
                              public void actionPerformed(ActionEvent e)
                              {
                                  
                                                          // AbstractButton abstractButton = (AbstractButton) e.getSource();
                              EntityPanel ep =epmFin.getEntityPanel(entityPanel[cnFinal].getTitle());
                              //System.out.println("PanelEditOneDataRec.addVisibleMenuButtonsAndPanels  title:"+entityPanel[cnFinal].getTitle()+" captionFinal:"+captionFinal);
                              //EntityPanel ep =
               	   // if is leaf of ODOR (has parent ODOR)
               	   if (ep==null)
               	   {
               	   	System.out.println("  Error  ep is null. PanelEditOneDataRec.addVisibleMenuButtonsAndPanels ep:"+ep);
               	   	 //ep = epmFin.getEntityPanel(node.getParent().toString());
                   }

                   
                  //System.out.println("PanelEditODR.addMenuButtons    "+ep+"     "+entityPanel[cnFinal].getType()+"    cnFinal:"+cnFinal+"     cpFinal:"+cpFinal+"   entityPanel[cnFinal].getTitle()"+entityPanel[cnFinal].getTitle());
                   selectionInMenu(ep, entityGroupOfPanelsFinal[cpFinal],cpFinal);                    
                             }
                         });              
                          
                        panelMenuDataEntry.add(btnMenu);
                      // System.out.println("PanelEditOneDataRec.addVisibleMenuButtonsAndPanels ooooooooooo=> caption:"+caption);
                  }// for cp listOfODORPanels.size()

                                   // choose the 1st button
                                JxToggleButton btnMenu0 = (JxToggleButton)listBtnMenu.get(0);
                                btnMenu0.doClick();
              //System.out.println("PanelEditOneDataRec.addVisibleMenuButtonsAndPanels panelMainCard.getComponentCount()="+panelMainCard.getComponentCount());//+"  panel count="+panelOneDataOneRecNew.getCountOfPanelODORData());         
                                
           /*  Component c[] = panelMainCard.getComponents();
            for(int p= 0;p<panelMainCard.getComponentCount();p++)
           {
           	  System.out.println("PanelEditOneDataRec.addVisibleMenuButtonsAndPanels "+p+"  panelMainCard Count "+panelMainCard.getComponentCount());//" name="+c[p].getName());
           } */ 
                  
          	}  // else if (entityGroupOfPanels!=null) // more than one panels data entry

            } // ODOR
            else if(entityPanel[cn].getType().equalsIgnoreCase("STATS") )// STATS has no egp
            {
                //System.out.println("  ======== PanelEditODR.addVisibleMenuButtonsAndPanels  " + cn+"  type:"+entityPanel[cn].getType()+"     drill entity panel"+entityPanel[cn].getDrillEntityPanel()+"    reportform from drill 0:"+entityPanel[cn].getDrillEntityPanel()[0].getEntityReportForm()+"    title="+entityPanel[cn].getTitle()); // null:     
                
                //System.out.println("  ======== PanelEditODR.addVisibleMenuButtonsAndPanels  " + cn+"  type:"+entityPanel[cn].getType()+" reportform from drill BAND 0 0:"+entityPanel[cn].getDrillEntityPanel()[0].getEntityReportForm().getEntityReportBands()[0]);
                
                
                if ( !isNewRec)
                {
               JxToggleButton btnMenu = new JxToggleButton();
               btnMenu.setPreferredSize(new Dimension(btnMenuWidth, btnMenuHeight));
               buttonGroup.add(btnMenu);
               listBtnMenu.add(btnMenu);
               btnMenuInt++;         
               btnMenu.setFocusable(false);
               btnMenu.setIcon(ICO_STATISTICS16);
               btnMenu.setHorizontalAlignment(SwingConstants.LEFT);
               btnMenu.setText(entityPanel[cn].getTitle());
               btnMenu.setToolTipText(entityPanel[cn].getTitle());
               
              final  EntityPanelM epmFin= epm;
              final int cnFinal = cn;
               btnMenu.addActionListener(new ActionListener()
               {
                   @Override
               public void actionPerformed(ActionEvent e)
               {    
                        // AbstractButton abstractButton = (AbstractButton) e.getSource();
                   EntityPanel ep =epmFin.getEntityPanel(entityPanel[cnFinal].getTitle());

               	   // if is leaf of ODOR (has parent ODOR)
               	   if (ep==null)
               	   {
               	   	System.out.println("  Error  ep is null. PanelEditOneDataRec.addVisibleMenuButtonsAndPanels ep:"+ep);
               	   	 //ep = epmFin.getEntityPanel(node.getParent().toString());
                   }

                   
                   EntityGroupOfPanels entityGroupOfPanels = null; // STATS is null
                   int intGroupOfPanelsToShow=-1;
                   
            //        System.out.println(" =====  PanelEditODR.addMenuButtons STATS "+ep+" "+entityGroupOfPanels+"   type:"+entityPanel[cnFinal].getType()+"    cnFinal:"+cnFinal+"       "+intGroupOfPanelsToShow+"     entityPanel[cnFinal].getTitle()"+entityPanel[cnFinal].getTitle());
                   selectionInMenu(ep, entityGroupOfPanels,intGroupOfPanelsToShow);
                           //(EntityPanel ep, EntityGroupOfPanels entityGroupOfPanels, int intGroupOfPanelsToShow)
               }
               });          


                panelMenuStatistics.add(btnMenu);
                    
                }
                else
                {
                    panelCollapsCategoryStatistics.setVisible(false);
                    
                }
            }
            else
            {
                        System.out.println("error UNKNOWN  ELSE PanelEditOneDataRec.addVisibleMenuButtonsAndPanels    --  --    implemented PanelEditRec.addVisibleMenuButtonsAndPanels   type:"+entityPanel[cn].getType()+"     title:"+entityPanel[cn].getTitle());
            }
              
          } // for cn
          // panelOneDataOneRec.setGuiLoaded(true);
          //System.out.println("PanelEditRec.addVisibleMenuButtonsAndPanels "+entityPanel[0].getType()+" "+entityPanel[0].getTitle());
          

          return selected; // when 0 do not display
    }


    /**
     * 
     * Called by this
     * @param ep the entity panel.
     * @param entityGroupOfPanels the group of panels.
     * @param intGroupOfPanelsToShow the selected group to show.
     *  called by 
     */
    private void selectionInMenu(EntityPanel ep, EntityGroupOfPanels entityGroupOfPanels, int intGroupOfPanelsToShow)
    {
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
 
        // not loading everything, so print on screen to comment the code
        //System.out.println("PanelEditOneDataRec.selectionInMenu  type="+ep.getType()+" title="+ep.getTitle()+" entityGroupOfPanels="+entityGroupOfPanels+" intGroupOfPanelsToShow="+intGroupOfPanelsToShow);
    if (entityGroupOfPanels != null)
    {  
        if(ep.getType().equalsIgnoreCase("ODOR"))	
        {
            String caption =  entityGroupOfPanels.getCaption();
         //System.out.println("PanelEditOneDataRec.selectionInMenu  ("+intGroupOfPanelsToShow+")  caption:"+caption+"   title:"+ep.getTitle()+"   entityGroupOfPanels!=null");
        
          panelOneDataOneRec.setActivePanel(caption,intGroupOfPanelsToShow);
       
        panelMainCard.add(panelOneDataOneRec, ep.getTitle());//caption);	  
        }
        else
        {
            System.out.println("  PanelEditOneDataRec.selectionInMenu not supported type of ep  ("+intGroupOfPanelsToShow+") type="+ep.getType()+" entityGroupOfPanels!=null");
        }
     }
     else if(entityGroupOfPanels == null)
     {    
        if (ep.getType().equalsIgnoreCase("STATS"))
        {
           
           //askIfDataChangedToSave();
            
        	//navigationTreeModel.getStatisticsNode().toString()+"/"+
            if(!isNewRec)
            {
                
               
                System.out.println("PanelEditOneDataRec.selectionInMenu      ooo-o-o-o-o-o-o-ooo    Type:"+ep.getType()+"       ep.getName():"+ep.getName()+"           getDrillEntityPanelReportForm 0 0:"+ep.getDrillEntityPanel()[0].getEntityReportForm().getEntityReportBands()[0]+"          isNewRec:"+isNewRec);
                // be careful below.it is 0   ep.getDrillEntityPanel()[0].getEntityReportForm()
        		//System.out.println("DialogEditRec.setVisibleTreeLeafs "+ ep.getQueryWhere()+primKeyValue+" "+ep.getQueryOrderBy());
                        //         getTitleCaption  ->  entityPanel[0] of the first panel which usually is dataentry
        
        int lengthOfPKs = primKeys.length;//utilsPanelReport.getPrimKeys().length;
        //String[] primKeyFields = utilsPanelReport.getPrimKeys();
        //String[] primKeyFieldValues = utilsPanelReport.getPrimKeysValue();
        String qWhere="";
        for(int pk=0;pk<lengthOfPKs;pk++)
        {
                       /* if(utilsString.hasQueryWhere(query))
                        {*/
                            //String befWhere = utilsString.getQueryBeforeWhere(query);
                            //String aftWhere = utilsString.getQueryAfterWhere(query);
                            //String qWhere = utilsString.getQueryWhere(query);
                            qWhere = qWhere + " AND "+entity+"."+primKeys[pk]+" LIKE "+primKeysValue[pk]+" ";
                            //query = befWhere + qWhere + aftWhere;
                        /*}    
                        else
                        {
                            System.out.println("--------*   PanelEditOneDataRec.setEntity   query has no where  query:"+query);
                        } */
        }                        
                        String filterEntityStatisticsKeys = ep.getQueryWhere()+qWhere;   //change because of primKeys 
                        
                        
                        String tit = getTitleCaption(entityPanel[0], isNewRec,fieldsOnTitle,fieldsOnTitleCaption);
        	  panelStatistics.setEntity(ep.getName(),null,null,ep.getTitle()+" "+title,true,tit,
                          ep.getIco(),ep.getQuerySelect(),ep.getQueryFrom(),filterEntityStatisticsKeys,ep.getQueryGroupBy(), ep.getQueryOrderBy(),ep.getIsFilterCompany(),
                          ep.getFieldCompanyIdName(),ep.getIsFilterYear(),ep.getFieldYearName(),ep.getDrillEntityPanel(),ep.getFieldsOnStatisticsTitle(),
                          ep.getFieldsOnStatisticsTitleCaption(),categoryNodes,null,null,/*formGlobalTableToGet1,formGlobalTableToApply1,*/null,null,panelManagement);  //,ep.getDrillEntityPanel()[0].getEntityReportForm());//ep.getEntityReportForm());                
                  
                  
            }
            else
            {
               // System.out.println("PanelEditOneDataRec.selectionInMenu NONSENCE primKeyDb:"+primKeyDb+" isNewRec:"+isNewRec);
                //  panelStatistics.setEntity(ep.getName(),null,null,ep.getTitle(),true,getTitleCaption(isNewRec,primKeyDb,primKeyValue,fieldsOnTitle,fieldsOnTitleCaption),ep.getIco(),ep.getQuerySelect(),ep.getQueryFrom(),ep.getQueryWhere()+"0",ep.getQueryGroupBy(),
           	//     ep.getQueryOrderBy(),ep.getIsFilterCompany(),ep.getFieldCompanyIdName(),ep.getIsFilterYear(),ep.getFieldYearName(),null,null,null,null,panelManagement);
	
            }
            
                
            panelMainCard.add(panelStatistics, ep.getTitle());	
            
            //System.out.println("PanelEditOneDataRec.selectionInMenu "+ep.getTitle()+" "+title+" "+getTitleCaption(isNewRec,primKeyDb,primKeyValue,fieldsOnTitle,fieldsOnTitleCaption));
        }
       /* else if(ep.getType().equalsIgnoreCase("TDOR"))	
        {
                //askIfDataChangedToSave();
        	//System.out.println("PanelEditRec.selectionInMenu -*TDOR*- "+entityPanel.length+" "+entityPanel[0].getSqlOne());
        	//panelTwoDataOneRec.setGuiLoaded(false);
            panelTwoDataOneRec.setEntity(ep,//ep.getEntity(),ep.getSqlOne(),ep.getDBFields(),ep.getDBFieldsMany(),ep.getEntityGroupOfComps(),
                    null,//ep.getEntityGroupOfPanels(),ep.getFieldsManyOnInsert(),ep.getFieldsManyTranslationOnInsert(), 
                    //ep.getEntityMany(),ep.getSqlMany(),ep.getIsMasterUnique(),ep.getSql2WhereField(),ep.getSql2WhereValue(), ep.getPrimKey(),
                    primKeyValue,// ep.getPrimKeyDb(),ep.getPrimKeysMany(),ep.getPrimKeysManyTran(),
                    isNewRec,// ep.getTitle(), ep.getIco(), ep.getStrOfMany2(),
                    false,//ep.getextsumcalcsIntTableActionValueField(),ep.getextsumcalcsTablePercentage(), ep.getextsumcalcsTablePercentageKey(), ep.getextsumcalcsTableCategory(),
                    //ep.getextsumcalcsTableCategoryKey(), ep.getextsumcalcsIntTableCategoryField(),  ep.getyearEnforceInAction(),ep.getyearEnforceInLines(),
                    panelManagement, this);
            //System.out.println("DialogEditRec.selectionInMenu ep.getSqlOne()"+ep.getSqlOne());
            panelMainCard.add(panelTwoDataOneRec, ep.getTitle());
            //panelTwoDataOneRec.setGuiLoaded(true);
        }*/
        else
        {
        	System.out.println("  PanelEditOneDataRec.selectionInMenu not supported type of ep type="+ep.getType()+" entityGroupOfPanels==null");
        }

    }   //  if(entityGroupOfPanels == null)
     
        CardLayout cl = (CardLayout)(panelMainCard.getLayout());         
        cl.show(panelMainCard, ep.getTitle());
        
        //System.out.println("PanelEditOneDataRec.selectionInMenu panelMainCard.getComponentCount="+panelMainCard.getComponentCount());
        
        this.revalidate();    
    
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));   	
    }
  

    //same as PanelODOR.setTitle,for statistics
    private String getTitleCaption(EntityPanel entityPanelTitle , boolean isNewRec , /*String primKeyDb, String pkValue,*/ String[]fieldsOnTitle, String[]fieldsOnTitleCaption)
    {
    	
        
     EntityDBFields[] dbFieldsAll = entityPanelTitle.getDBFields();
    	 String caption="";//"<html>";
          
         if(!isNewRec)
         {

           try
           {    
               
            
           String queryForTitle = "";

            String qOrderByAndGroupByReadOnly = utilsString.getGroupbyAndOrderbySubQuery(query);           
   String subqueryWhere = ""; // for each primary key
  	    db.retrieveDBDataFromQuery(query,"PanelEditODR.getTitleCaption");
   	    rs=db.getRS();
       //     int selectedRow = utilsPanelReport.getRowForPrimKey("PanelEditODR.getTitleCaption",query,rs,dbFieldsAll,primKeyDb,primKeyValue);
       //     utilsPanelReport.retrievePrimKeyValueForOnePK( query, selectedRow, null,dbFieldsAll,true, entityPanelTitle.getEntity(), primKeyDb);    
        //     System.out.println("----O------>  PanelOneDataOneRec.showPrintPreviewForm   '"+entityPanel.getEntity()+"   primKeyDb:"+primKeyDb+"  selectedRow:"+selectedRow+"'  primKeyValue:"+primKeyValue+"  primKeys.length:"+primKeys.length+"  queryReadOnly:"+queryReadOnly);          
             //String[] primKeys = utilsPanelReport.getPrimKeys();
             //String[] primKeysCaption = utilsPanelReport.getPrimKeysCaption();
      
               int primKeysCount = primKeys.length;
               //String[] primKeysValue = utilsPanelReport.getPrimKeysValue(); 
             String sqlEntity = entityPanelTitle.getEntity();
               
           
          for (int i = 0; i< primKeysCount; i++) // i=0 and i< because arraylist starts from 0
          {             
                //System.out.println("PanelOneDataOneRecData.rowUpdate '"+entity+"' "+primKeys[i]+"="+primKeysValue[i]); 

              
               //System.out.println("PanelOneDataOneRecData.rowUpdate  subqueryWhere  ("+i+")  "+primKey+"   "+primKeys[i]+"="+primKeysValue[i]+"     primKeyDb:"+primKeyDb+"  primKeyValue:"+primKeyValue);   
        /*       if(primKeys[i].equalsIgnoreCase(primKeyDb))
               {
                subqueryWhere = subqueryWhere+"("+sqlEntity+"."+primKeys[i]+" LIKE '"+pkValue+"')"; // when is updating if a second time after insert is selected
               }
               else
               {*/
                   subqueryWhere = subqueryWhere+"("+sqlEntity+"."+primKeys[i]+" LIKE '"+primKeysValue[i]+"')";
        //       }           
                    if (i < primKeys.length-1 && primKeys.length>1) 
          	  // add AND but not on the last field(before where), also not when there is only one PK . -1 because arraylist starts from 0
          	  { subqueryWhere = subqueryWhere+" AND  ";   } 
          }
           
           
           queryForTitle= utilsString.getQueryBeforeWhere(query)+" WHERE "+subqueryWhere+" "+qOrderByAndGroupByReadOnly; 
           
                  
               
               
               
               
               
               
           	  //  String queryTitle = "SELECT * FROM "+entity+" WHERE "+primKeyDb+" = "+primKeyValue;
           	    	
           	    //System.out.println("PanelEditOneDataRec.getTitleCaption "+queryTitle);
   	            db.retrieveDBDataFromQuery(queryForTitle,"PanelEditOneDataRec.getTitleCaption");
   	            rs=db.getRS();
   	            //rsmd=db.getRSMetaData(); 
                  if(db.getRecordCount()>1)                      
                  {
                      System.out.println(" -------- PanelEditOneDataRec.getTitleCaption    more than one row :"+ db.getRecordCount()+"   query:"+queryForTitle);//+"                queryTitle:"+queryTitle);
                   //utilsGui.showMessageError(this,"Error  PanelEditOneDataRec.getTitleCaption   line885     more than one row :"+ db.getRecordCount());
                  }
                //rs = db.retrieveResultSet("SELECT * FROM "+entity+" WHERE "+primKeyDb+" = "+primKeyValue);
                rs = db.retrieveRow(rs, 1);// go to the only row               
                if (rs!=null && fieldsOnTitle!=null)
                {
                	for(int f = 0;f<fieldsOnTitle.length;f++)
                	{
                		//System.out.println("field "+f+" "+fieldsOnTitleCaption[f]+" "+fieldsOnTitle[f]+" "+rs.getString(fieldsOnTitle[f]));
                		caption = caption+"<span style='color:"+CLR_TITLE_CAPTION_DARK_HTML+"'>"+ fieldsOnTitleCaption[f]+":</span>"+ rs.getString(fieldsOnTitle[f])+" ";
                	}
                	
                    caption=caption+"</html>";
                }
           }//try
           catch ( SQLException sqlex)
           {
               System.out.println("error:sql PanelEditOneDataRec.getTitleCaption():"+sqlex.getMessage());
           }
           finally
           {
               closeDB();
           }
         }// if     
    	
    	
    	  
    	return caption;
    }
    
 /*    public void showDialog()
     {
        locateOnCenterOfTheScreen();
        this.setVisible(true);
     }*/

/*    private void locateOnCenterOfTheScreen()
    {
    	Dimension paneSize   = this.getSize();
    	Dimension screenSize = this.getToolkit().getScreenSize();
    	this.setLocation(
            (screenSize.width  - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
    }*/

 /*  private void ok()
   {
     if(getHasDataChanged())
     {       

        if (dataOne)
        {

        	   panelOneDataOneRec.rowSave();
        	   panelOneDataOneRec.closeDialog();
    	     
        }
        else
        { 	   
        	 
        	 
        	 panelTwoDataOneRec.recordSave();
        }
     }
     
   }*/

   
   private boolean getHasDataChanged()
   {
   	boolean changed=false;
   	
   	//System.out.println("PanelEditOneDataRec.getHasDataChanged  one:"+panelOneDataOneRec.getHasDataChanged()+"   two:"+panelTwoDataOneRec.getHasDataChanged()+"     dataOne:"+dataOne);
   	
      /*if (dataOne)
      {*/
      	changed = panelOneDataOneRec.getHasDataChanged();
        
        /*for(int c = 0;c<panelMainCard.getComponentCount();c++)
          {
               panelMainCard.getComponent(c);    
          }*/
      /*}
      else
      {
      	changed = panelTwoDataOneRec.getHasDataChanged();
      }*/  	
      
      //System.out.println("PanelEditOneDataRec.getHasDataChanged dataOne="+dataOne+" changed="+changed);
      
   	return changed;
   }

   
   public void setDataHasChanged(boolean hasChanged)
   {

      	panelOneDataOneRec.setDataHasChanged(hasChanged);
        
        /*for(int c = 0;c<panelMainCard.getComponentCount();c++)
          {
               panelMainCard.getComponent(c);    
          }*/

   }   
   
   public void setWasNewRecordFalse()
   {
        panelOneDataOneRec.setWasNewRecordFalse();
   }
   
   public boolean getWasNewRecord()
   {
       return panelOneDataOneRec.getWasNewRecord();
   }
   
   
   public String getPkValueAfterNewIsInserted()
   {
       return panelOneDataOneRec.getPkValueAfterNewIsInserted();
   }
   
   
   public int askIfDataChangedToSave()
   {
   	 final int ESC = -1;
     	 final int YES = 0;
    	 final int NO = 1;
    	 final int CANCEL = 2;
        
        int ask = -2;// not changed not asked
        boolean datachanged = getHasDataChanged();
        if(datachanged)
        {       
             ask = utilsGui.showConfirmYesOrNoOrCancel(parent,"Έγιναν αλλαγές. Θέλετε να τις αποθηκεύσετε;");
        
          //System.out.println("DialogEditRec.closeWindow "+ask);
           if(ask==YES)
           { 
              /*if (dataOne)
              {*/
              	  panelOneDataOneRec.rowSave(true);
              	  panelOneDataOneRec.setDataHasChanged(false);
              	  panelOneDataOneRec.closeDialog();
              /*}
              else
              {    	panelTwoDataOneRec.recordSave(); 	       }   */                      
           }
           else if(ask==NO)
           {    
              panelOneDataOneRec.setDataHasChanged(false);
           }
           else if(ask==CANCEL || ask==ESC)
           {
               
           }
           else
           {
               System.out.println("PanelEditODR.askIfDataChangedToSave else datachanged="+datachanged+" ask="+ask);
           }
        // if press escape -1
        }// datachanged
        else
        { 
           //System.out.println("PanelEditODR.askIfDataChangedToSave not defined ELSE. datachanged="+datachanged);
        }

   	 return ask;
   }
   
   private void closeDB()
   {
   	  db.releaseConnectionRs();
   	  db.releaseConnectionRsmd();
   }
  
   /*
    * called by PanelODMR.PanelActionListenerSaveAndShowList 
    * called by    PanelTDMRec.PanelActionListenerSaveAndShowListToChild
    */
   public boolean saveAndClose()
   {
       boolean boolContinue=false;
              /*if (dataOne)
              {*/
              	 if( panelOneDataOneRec.rowSave(true))// if has messages that data has to be corrected
                 {
              	   panelOneDataOneRec.setDataHasChanged(false);
              	   panelOneDataOneRec.closeDialog();
                   boolContinue=true;
                 }
                 else
                 {
                     boolContinue=false;
                 }
              /*}
              else
              {    	
                  if( panelTwoDataOneRec.recordSave())
                  {
                      boolContinue=true;
                  }
                  else
                  {
                      boolContinue=false;
                  }
              }*/   

               closeDB();
     	
             /* if (dataOne)
              {*/
              	  panelOneDataOneRec.closeDB();
             /* }
              else
              {
              	panelTwoDataOneRec.closeDB();
              } */
              
              return boolContinue;
   }
   
   
   /*
    * called by multiple panels and dialogs
    * also called by 
    */
   public boolean toClosePanelOrGoBackAskIfDataChanged()
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
     	
     	closeDB();
     	
            /*  if (dataOne)
              {*/
              	  panelOneDataOneRec.closeDB();
             /* }
              else
              {
              	panelTwoDataOneRec.closeDB();
              } */
      
    

        return ret ;
   }
  
  
  //similar in PanelMangement and WindowSelectTab
   /* private class TreeRendererIcons extends DefaultTreeCellRenderer implements Constants
    {
    	private Color backgroundSelectionColor;
        private Color backgroundNonSelectionColor;
    	private DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();
    	
      /*  public TreeRendererIcons()
        { 
        
        backgroundSelectionColor = defaultRenderer.getBackgroundSelectionColor();
        backgroundNonSelectionColor = defaultRenderer.getBackgroundNonSelectionColor();        
        
        }*/

      /* public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus)
        {
            setLeafIcon(ICO_TABLE16);
            setOpenIcon(ICON_TREEFOLDER_OPENED);
            setClosedIcon(ICON_TREEFOLDER_CLOSED);
           
           // 	System.out.println("PanelManagement.TreeRendererIcons "+value);

            super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row,hasFocus);

            DefaultMutableTreeNode node =(DefaultMutableTreeNode)value;

            EntityPanel entityPanel =new EntityPanel();


            if(epm.getCount()>0)
            {
            	

                 //Object nodeObj = ((DefaultMutableTreeNode)value).getUserObject();
                // entityPanel = getEntityMenuObjectForNode(getNode(node.toString()), row);
                 
                 //EntityPanelM entityPanelMangement = new EntityPanelM();
                 entityPanel = epm.getEntityPanel(node.toString());
                if(entityPanel!=null) 
                {
                 //System.out.println("PanelEditOneDataRec.TreeRendererIcons "+entityPanel.getEntity());
                 
                 if(entityPanel.getIco()!=null)
                 {
                     setIcon( entityPanel.getIco());	
                 }
                 else // = null ico
                 {   
                      //System.out.println("PanelEditOneDataRec.TreeRendererIcons icon null row "+row);
                 }            
                }
               }
               
        if (sel) {
          this.setBackground(backgroundSelectionColor);
        } else {
          this.setBackground(backgroundNonSelectionColor);
        }
     
            return this;
        }

    }*/

    // from http://www.rgagnon.com/javadetails/java-0210.html
 /* public void expandAll(JTree tree)
  {
    int row = 0;
    while (row < tree.getRowCount())
    {
      tree.expandRow(row);
      row++;
    }
  }*/


    /*
     *  
     *  allows outside classes to add actionlisteners
     * called by PanelOneDataManyRec.initialize the >1 panels
     */
/*    public void addActionListenerSaveToShowList(ActionListener al)
    {   
      btnSaveAndShowList.addActionListener(al);
    }
*/
    /*
     *  
     *  allows outside classes to add actionlisteners
     * called by PanelOneDataManyRec.initialize the >1 panels
     */
    public void addActionListenerBackToList(ActionListener al)
    {   
      btnBackToListAskToSave.addActionListener(al);
    }    
    
    /*
     *  called by PanelOneDataManyRec
     *  as an outside class adds actionlisteners
     */
    public void addActionListenerSaveToShowListToChildOneData(ActionListener al)
    {
      //System.out.println("PanelEditOneDataOneRec.addActionListenerShowListToChildOneData   <<Action>> for One "+al.toString() );         
      panelOneDataOneRec.addActionListenerSaveAndShowList(al);   
    }

    public void addActionListenerBackToListToChildOneData(ActionListener al)
    {
      //System.out.println("PanelEditOneDataOneRec.addActionListenerShowListToChildOneData   <<Action>> for One "+al.toString() );         
      panelOneDataOneRec.addActionListenerBackToList(al);   
    }    
    
     /*
     *  called by PanelTwoDataManyRec
     *  as an outside class adds actionlisteners
     */
    /*public void addActionListenerSaveToShowListToChildTwoData(ActionListener al)
    {
      //System.out.println("PanelEditOneDataOneRec.addActionListenerShowListToChildTwoData   <<Action>> for Two" );  
      //panelTwoDataOneRec.addActionListenerSaveAndShowList(al);
    }*/

     /*
     *  called by PanelTwoDataManyRec
     *  as an outside class adds actionlisteners
     */
    /*public void addActionListenerBackToListToChildTwoData(ActionListener al)
    {
      //System.out.println("PanelEditOneDataOneRec.addActionListenerShowListToChildTwoData   <<Action>> for Two" );  
     // panelTwoDataOneRec.addActionListenerBackToList(al);
    }  */  

    
    private int getPageIsSelected()
    {
        int intPageSelected = 0 ;
             for(int b =0 ;b<listBtnMenu.size();b++)
             {
                 JxToggleButton btnMenu = (JxToggleButton)listBtnMenu.get(b);
                 
                 if(btnMenu.isSelected())
                 {
                     intPageSelected=b;

                 }
                           
             }
        return intPageSelected;
    }
    
   private void setPageSelectionKeys()
   {
       
             Action actionPageKeySelectDown = new ActionPageKeySelectDown();
             KeyStroke pageDown = KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, 0, true);
             panelTitleAndMenu.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(pageDown, "selectPageDown"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
             panelTitleAndMenu.getActionMap().put("selectPageDown", actionPageKeySelectDown);
       
            Action actionPageKeySelectUp = new ActionPageKeySelectUp();
             KeyStroke pageUp = KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, 0, true);
             panelTitleAndMenu.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(pageUp, "selectPageUp"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
             panelTitleAndMenu.getActionMap().put("selectPageUp", actionPageKeySelectUp);
             
   }
  
   
   class  ActionPageKeySelectDown extends AbstractAction                 
   {       
        public ActionPageKeySelectDown()
        {

        }
        public void actionPerformed(ActionEvent e)
      	{
           if(listBtnMenu.size()>1)//.isVisible() || panelCollapsCategoryStatistics.isVisible()) 
           {    
             if(getPageIsSelected()<listBtnMenu.size()-1)
             {    
                     JxToggleButton btnMenu = (JxToggleButton)listBtnMenu.get(getPageIsSelected()+1);
                     btnMenu.doClick();
             }
             else // if after the last then go to first
             {
                     JxToggleButton btnMenu = (JxToggleButton)listBtnMenu.get(0);
                     btnMenu.doClick();
                
             }
                   //  System.out.println("ActionPageKeySelectDown   "+getPageIsSelected()); 
           }
         }


    }   
   
 
      class  ActionPageKeySelectUp extends AbstractAction                 
   {       
        public ActionPageKeySelectUp()
        {

        }
        public void actionPerformed(ActionEvent e)
      	{
           if(listBtnMenu.size()>1)//.isVisible() || panelCollapsCategoryStatistics.isVisible()) 
           {              
             if(getPageIsSelected()<1)// when first then go to last
             {             
                     JxToggleButton btnMenu = (JxToggleButton)listBtnMenu.get(listBtnMenu.size()-1);
                     btnMenu.doClick();            
             }
             else
             {
                     JxToggleButton btnMenu = (JxToggleButton)listBtnMenu.get(getPageIsSelected()-1);
                     btnMenu.doClick();
             }        
           }
                  //System.out.println("ActionPageKeySelectUp "+getPageIsSelected());

        }                  
    }   
    
   // called by this class in setEntity for PanelOneDataOneRec btn
/*  public class PanelActionListenerSaveAndShowList implements ActionListener
  {// look at     public void addActionListener(ActionListener al) in panelonedataonerec
    //        // ask panelODORec to add an actionlistener to its buttons: 
    //   panelOneDataOneRec.addActionListener(new PanelActionListener());
    public void actionPerformed(ActionEvent e)
    {
      System.out.println("PanelEditOneDataOneRec.PanelActionListenerSaveAndShowList   Action \"" + e.getActionCommand() + "\" performed from within PanelEditODOR. class");

        saveAndClose();

    }
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
            
        this.setFocusable(false);
        //this.setFloatable(false);	
        //this.setOpaque(false);
        //this.setOrientation(VERTICAL);
       
        
        btnBackToListAskToSave = new JButton();
        btnBackToListAskToSave.setHorizontalTextPosition(SwingConstants.CENTER);
        btnBackToListAskToSave.setVerticalTextPosition(SwingConstants.BOTTOM);   
         btnBackToListAskToSave.setText("<html>πίσω</html>"); //<b>F3</b></html>");
        // btnBackToListAskToSave.setText("πίσω χωρίς αποθήκευση (F3)");
        btnBackToListAskToSave.setOpaque(false);
        btnBackToListAskToSave.setToolTipText("πίσω στη λίστα");
        btnBackToListAskToSave.setIcon(ICO_PANELBACK);  // ICO_PANELBACKCANCEL
        //btnFilters.setMnemonic(KeyEvent.VK_O);
        btnBackToListAskToSave.setFocusable(false);
        btnBackToListAskToSave.addActionListener(new ActionListener()
        {
                @Override
	        public void actionPerformed(ActionEvent e) 
	        {	 
                   //code in PanelActionListenerBackToList
	          //closePanel(); 
	        }
	    });
        Action actionBackToListAskToSave = new ActionBackToListAskToSave();
        btnBackToListAskToSave.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F3"), "backToListAskToSave"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnBackToListAskToSave.getActionMap().put("backToListAskToSave", actionBackToListAskToSave);
        btnBackToListAskToSave.setOpaque(false);
        
         this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        //addSeparator();
        //add(lblIcoSeparator1);
       // add(btnSaveAndShowList);  
        add(btnBackToListAskToSave);  
       // addSeparator();
        //add(lblIcoSeparator2);
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

   
public class PanelOneDataOneRecManagement
{
   //EntityLookUp entityLookUp;
   ArrayList listEntities;	
	
   public PanelOneDataOneRecManagement()
   {
        listEntities = new ArrayList();
        //addEntitiesLookup();
   }
   
   public void addEntitiesPanelOneDataOneRec(PanelOneDataOneRec pnl)
   { 
      listEntities.add(pnl);

   }
   
   public PanelOneDataOneRec getEntityPanelOneDataOneRec(int p)
   {
       PanelOneDataOneRec panelOneDataOneRecGet = null;
   	  for(int i =0; i<listEntities.size(); i++)
   	  {  
   	     PanelOneDataOneRec pnl = (PanelOneDataOneRec)listEntities.get(i);
   	     //System.out.println("LookUp.getTable "+elu.getForeignTable()+"--"+foreignTable);
   	     /*if (pnl.getName().toUpperCase().equalsIgnoreCase(foreignTable.toUpperCase()))// non case sensitive search
   	     {
   	     	//System.out.println("LookUp.getTable --- "+elu.getForeignTable());
   	     	return pnl.getForeignTable();
   	     }*/
             if(i==p)
             {
                 panelOneDataOneRecGet= pnl;
                 break;
             }
             else
             {
                 panelOneDataOneRecGet =null;
             }
   	  }
      return panelOneDataOneRecGet;
   }
   
}  
    /* class  ActionSaveAndShowList extends AbstractAction                 
   {       
        public ActionSaveAndShowList()
        {      }
      	
    	public void actionPerformed(ActionEvent e)
      	{     
      	  btnSaveAndShowList.doClick();
      	  //System.out.println("PanelEditOneDataRec.btnSaveAndShowList.doClick()");
      	}    	
    }*/
 
     class  ActionBackToListAskToSave extends AbstractAction                 
   {       
        public ActionBackToListAskToSave()
        {      }
      	
    	public void actionPerformed(ActionEvent e)
      	{     
      	  btnBackToListAskToSave.doClick();
      	  //System.out.println("PanelEditOneDataRec.btnSaveAndShowList.doClick()");
      	}    	
    }     
     
}