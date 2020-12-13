/*
* created 13-4-2015
* it contains many editable panels(parameters)
 */

package com.tool.gui;

import com.tool.guicomps.*;
import com.tool.utils.*;
import com.tool.model.*;

import static com.tool.guicomps.Constants.CLR_PANEL_END_ALTER;
import static com.tool.guicomps.Constants.CLR_PANEL_START_ALTER;
import static com.tool.guicomps.Constants.CLR_TITLE_BACKGROUND_END;
import static com.tool.guicomps.Constants.CLR_TITLE_BACKGROUND_START;
import static com.tool.guicomps.Constants.CLR_TITLE_CAPTION;
import static com.tool.guicomps.Constants.DATAENTRY;
import static com.tool.guicomps.Constants.ICO_STATISTICS16;
import static com.tool.guicomps.Constants.ICO_TABLE16;
//import com.tool.jdbc.Database;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.JFrame;


import java.text.*;
import java.util.ArrayList;
import javax.swing.*;
import static com.tool.guicomps.Constants.METRICS;
import javax.swing.border.BevelBorder;

/**
 *
 * @author sun
 */
public class PanelManyDataManyRec  extends JxPanel implements Constants
{
    private JPanelDecorated panelMain;
    private UtilsGui utilsGui;
    private UtilsString utilsString;
    private UtilsDate utilsDate;
    private JFrame frame;

  private String name;
  private String caption;
  private ArrayList listBtnMenu;
  
  private JxPanel panelCenter;
  private EntityManyDataManyRec entityManyDataManyRec;
  private JxPanel panelTitleAndMenu;
  private JxPanel panelMainCard;
  //private JLabel  lblTitle;
  private JxPanel panelTop;
  private JScrollPane treeScrollPane = new JScrollPane();
  private  JxPanel panelMenuMany;
  private PanelCollapsable panelCollapsCategoryMany ;
private  JButton btnManySave;
private ToolBarDataMany toolBarDataMany;
private ArrayList listPanelODOR;
private ArrayList listPanelODORCaption; // It is checked in selectionInMenu if a menu+panel is already loaded.Is needed so it does not query again but to show the already shown panel.
private ArrayList listSelectedButtonsCaption;
private PanelManagement panelManagement;

  private int btnMenuWidth = 115;
  private int btnMenuHeight = 28;


  public PanelManyDataManyRec(JFrame frame)  
    {
            try
           {     initialize(frame);   }
           catch (Exception e)
           {   e.printStackTrace();    }
    }
    
    private void initialize(JFrame frameIn) throws Exception
    {
        utilsGui = new UtilsGui();
        utilsString = new UtilsString();
        utilsDate = new UtilsDate();
        frame =frameIn;    
    
    	frame = frameIn;
    	panelMain = new JPanelDecorated();
    	panelMain.setLayout(new BorderLayout());  // new CardLayout());//new BorderLayout());        
        
        listPanelODOR= new ArrayList();
        listPanelODORCaption= new ArrayList();
        
        
	toolBarDataMany = new ToolBarDataMany();
        toolBarDataMany.setFocusable(false);            
       panelTitleAndMenu = new JxPanel();

       listBtnMenu = new ArrayList();
       
       panelMainCard= new JxPanel();
       panelMainCard.setOpaque(false);
       panelMainCard.setLayout(new CardLayout());
       panelMainCard.setBorder(null);

       
      /*  lblTitle = new JLabel("επεξεργασία");
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
*/
/*	panelTop = new JxPanel();
	    panelTop.setBorder(null);
        panelTop.setLayout(new BorderLayout());*/
//        panelTop.add(paneTitle,BorderLayout.CENTER);
 //       panelTop.add(toolBarData,BorderLayout.PAGE_START); 
        
        //btnBack = new JButton();
         

        //panelTitleAndMenu.setOpaque(false);

        
        
//       panelOneDataOneRec = new PanelOneDataOneRecData(parent);
       //panelTwoDataOneRec = new PanelTwoDataOneRec(parent);
 //      panelStatistics =  new PanelStatistics(parent);
       
       
       

       utilsGui = new UtilsGui();
        

//	JPanelDecorated panelContainer = new JPanelDecorated();
 //       panelContainer.setLayout(new BorderLayout());  //new BoxLayout(panelContainer, BoxLayout.PAGE_AXIS));
    
        panelTitleAndMenu.setLayout(new BorderLayout());// new BoxLayout(panelTitleAndMenu, BoxLayout.PAGE_AXIS));
        
        panelTitleAndMenu.setBackground(panelMain.getBackground().darker()); //CLR_TOOLTIP);
    
//        panelContainer.add(panelTitleAndMenu, BorderLayout.PAGE_START);

 //       panelContainer.setBackground(panelMain.getBackground().darker()); //CLR_TOOLTIP);
       
        panelMain.add(toolBarDataMany,BorderLayout.PAGE_START);
        panelMain.add(panelTitleAndMenu,BorderLayout.LINE_START);
        panelMain.add(panelMainCard,BorderLayout.CENTER);
        this.setLayout(new BorderLayout());
        add(panelMain, BorderLayout.CENTER);
        
        
 //            panelMenuDataEntry = new JxPanel();
//            panelMenuDataEntry.setLayout(new GridLayout(0,1));
            
             panelMenuMany = new JxPanel();
            panelMenuMany.setLayout( new GridLayout(0,1));//new BoxLayout(panelMenuMany, BoxLayout.PAGE_AXIS));
          panelTitleAndMenu.add(panelMenuMany, BorderLayout.PAGE_START);
 //            panelCollapsCategoryDataEntry = new PanelCollapsable(panelMenuDataEntry,null, DATAENTRY, true,CLR_PANEL_START_ALTER, CLR_PANEL_END_ALTER); 
//             panelCollapsCategoryMany = new PanelCollapsable(panelMenuMany,null, METRICS, true, CLR_PANEL_START_ALTER,CLR_PANEL_END_ALTER); 
   
            
         setPageSelectionKeys();   //same in PanelEditOneDataRec and PanelManyDataManyRec     
        
        

        
        
        
        
        
    	//panelCenter = new JxPanel();
    	//panelCenter.setLayout(new FlowLayout()); // new BoxLayout(panelCenter, BoxLayout.PAGE_AXIS));//new FlowLayout());        
        
        
        
        //panelMain.add(panelCenter, BorderLayout.CENTER);
    	//panelMain.add(panelButtons, BorderLayout.PAGE_END);

    	//this.setLayout(new BorderLayout());
    	//this.add(panelMain);
        
       if (VariablesGlobal.globalShowInitialisations)
       {    System.out.println("PanelManyDataManyRec initialized");   }         
        
    }
    
    
 /* public void setMenuButtonsVisible( boolean[] isVisibleType)   
  {
          System.out.println("PanelManyDataManyRec.setMenuButtonsVisible  isVisibleType="+isVisibleType[0]+"  "+isVisibleType[1]+"  "+isVisibleType[2]);

               panelCollapsCategoryMany.setVisible(true);

  }*/    
    
 private void addPanelForEntity(EntityParameter [] entityParameterArray, int cn)   
 {
               EntityParameter entityParameter =  entityParameterArray[cn];
     
                        //EntityDBFields[] dbFieldsChild = dbFieldsInGroupOfPanels[i].getDbChildFields();
              EntityDBFields[] dbFields = entityParameter.getFields();   
                       
              String sql = entityParameter.getSqlReadOnly();
             
      //        System.out.println(" +---  PanelManyDataManyRec.selectionInMenu       cn:"+cn+"       list size:"+listPanelODOR.size()+"      caption:"+entityParameter.getCaption()+"      entityParameter.getEntityPanel().length:"+entityParameter.getEntityPanel().length+"        sql:"+sql+"    pk"+entityParameter.getPrimKey()+" pkdb:"+entityParameter.getPrimKeyDb());
                        //PanelOneDataOneRecData pnlODMRData = (PanelOneDataOneRecData)fieldTxts.get(f);
     /*                  PanelOneDataOneRecData panelODORData =  new PanelOneDataOneRecData(frame);

                     //        entityParameter.getFieldsForSums(), 
                      //listPanelODORData.size
                     panelODORData.setEntity(entityParameter.getEntityPanel()[0], entityParameter.getCaption(),entityParameter.getName(),dbFields,
                              entityParameter.getEntityGroupOfComps(), null,entityParameter.getPrimKey(),"",entityParameter.getPrimKeyDb(),sql,
                              false,false,true, entityParameter.getYearEnforce(),ICO_TABLE16,null,0,panelManagement);
       */                      //
                             
              //String formGlobalTableToGet1 =    entityParameter.getFormGlobalTableToGet1();
             // String formGlobalTableToApply1 = entityParameter.getFormGlobalTableToApply1();
                      
              PanelOneDataOneRec panelODOR =  new PanelOneDataOneRec(frame);
              // -1 is used instead for the selectedtablerow in readonlytable in order PKs to be found
             panelODOR.setEntity(entityParameter.getName(),entityParameter.getEntityPanel()[0],-1,/*primKeyValue,*//*""*/sql,null,null,"", false, false,
                      /*,formGlobalTableToGet1,formGlobalTableToApply1*/null,/* do not want to show title so null, entityParameter.getFieldsOnTitle(),
                       entityParameter.getFieldsOnTitleCaption()*/null, false, "",/*showShowListButtons*/true, true,ICO_TABLE16,
                       panelManagement,null,IS_CALLED_BY_MULTIPLE_TABLES_MDMR);  //0 the only one panel                        
               listPanelODOR.add(panelODOR);    
           panelMainCard.add(panelODOR, entityParameter.getCaption());         
           
 }
    
    
    
  
  
  /*
     *  isVisibleType dataentry, stats, ...
     * boolean 0 ODOR, 1 STATS, 2 TDOR
     */
    private void addVisibleMenuButtons()
    { 

                
           //System.out.println("PanelEditODR.addMenuButtons +++ entityPanel.length "+entityPanel.length+" isVisibleType[0]="+isVisibleType[0]+" isVisibleType[1]="+isVisibleType[1]);
          // panelMenuDataEntry.removeAll();
//           panelMenuMany.removeAll();
//           panelTitleAndMenu.removeAll();
           //panelTitleAndMenu.add(panelTop);
        

            EntityParameter[] entityParameter = entityManyDataManyRec.getEntityParameters();

 //          panelTitleAndMenu.add(panelCollapsCategoryMany);         

            
            ButtonGroup buttonGroup = new ButtonGroup();
 //           panelMainCard.removeAll(); // all panelODORDataD
 //           listBtnMenu.clear();
            int btnMenuInt=0;
      //            askIfDataChangedToSave();
      //            panelOneDataOneRec.setGuiLoaded(false);
           //System.out.println(" +++++++  PanelMDMR.addVisibleMenuButtonsAndPanels  +++ entityParameter.length="+entityParameter.length);
            
            
       if(entityParameter!= null && entityParameter.length>1)
       {
           
             panelTitleAndMenu.setMinimumSize(new Dimension(140, 130));//Dimension(int width, int height) 
             panelTitleAndMenu.setPreferredSize(new Dimension(140, 245));
           
           
           //System.out.println(" +++++++  PanelMDMR.addVisibleMenuButtonsAndPanels  +++  IF  entityParameter.length="+entityParameter.length); 
          for(int cn =0; cn<entityParameter.length;cn++)
          {
              
               JxToggleButton btnMenu = new JxToggleButton();
               btnMenu.setPreferredSize(new Dimension(btnMenuWidth, btnMenuHeight));
               buttonGroup.add(btnMenu);
               listBtnMenu.add(btnMenu);
                        
               btnMenu.setFocusable(false);
               btnMenu.setIcon(ICO_TABLE16);
               btnMenu.setHorizontalAlignment(SwingConstants.LEFT);
               btnMenu.setText(entityParameter[cn].getCaption());
               btnMenu.setToolTipText(entityParameter[cn].getCaption());
               
              //final  EntityPanelM epmFin= epm;
              final int cnFinal = cn;
              final int btnMenuIntFinal = btnMenuInt;
              final EntityParameter[] entityParameterFinal = entityParameter;
              
               btnMenu.addActionListener(new ActionListener()
               {
                   @Override
               public void actionPerformed(ActionEvent e)
               {
                   selectionInMenu(entityParameterFinal,btnMenuIntFinal);//cnFinal);
               }
               });          

                panelMenuMany.add(btnMenu);
                btnMenuInt++;

          } // for cn
       }
       else  // when there is only one button set to not visible 
       {
           //System.out.println(" +++++++  PanelMDMR.addVisibleMenuButtonsAndPanels  +++ ELSE entityParameter.length="+entityParameter.length);

           panelMenuMany.removeAll();
           panelTitleAndMenu.removeAll();

           selectionInMenu(entityParameter,0);
               JxToggleButton btnMenu = new JxToggleButton();
               //buttonGroup.add(btnMenu);
               listBtnMenu.add(btnMenu);

       }
          
          //System.out.println("PanelEditRec.addVisibleMenuButtonsAndPanels "+entityPanel[0].getType()+" "+entityPanel[0].getTitle());
//          panelOneDataOneRec.setGuiLoaded(true);

    }  
  
    private void selectionInMenu(EntityParameter [] entityParameterArray, int cn)
    {
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
 
        // not loading everything, so print on screen to comment the code
        //System.out.println("PanelManyDataManyRec.selectionInMenu    cn:"+cn+"  entityParameter:"+entityParameter.getCaption());

        		//System.out.println("DialogEditRec.setVisibleTreeLeafs "+ ep.getQueryWhere()+primKeyValue+" "+ep.getQueryOrderBy());
/*        	  panelStatistics.setEntity(ep.getName(),null,null,ep.getTitle()+" "+title,true,getTitleCaption(isNewRec,primKeyDb,primKeyValue,fieldsOnTitle,fieldsOnTitleCaption),ep.getIco(),ep.getQuerySelect(),ep.getQueryFrom(),ep.getQueryWhere()+primKeyValue,ep.getQueryGroupBy(),
        	     ep.getQueryOrderBy(),ep.getIsFilterCompany(),ep.getFieldCompanyIdName(),ep.getIsFilterYear(),ep.getFieldYearName(),ep.getDrillEntityPanel(),ep.getFieldsOnStatisticsTitle(),ep.getFieldsOnStatisticsTitleCaption(),categoryNodes,null,null,null,null,panelManagement);                
*/                  
               EntityParameter entityParameter =  entityParameterArray[cn];
     
                        //EntityDBFields[] dbFieldsChild = dbFieldsInGroupOfPanels[i].getDbChildFields();
//              EntityDBFields[] dbFields = entityParameter.getFields();   
                       
//              String sql = entityParameter.getSqlReadOnly();
//              System.out.println(" +---  PanelManyDataManyRec.selectionInMenu       cn:"+cn+"       list size:"+listPanelODOR.size()+"      caption:"+entityParameter.getCaption()+"      entityParameter.getEntityPanel().length:"+entityParameter.getEntityPanel().length+"        sql:"+sql+"    pk"+entityParameter.getPrimKey()+" pkdb:"+entityParameter.getPrimKeyDb());
                        //PanelOneDataOneRecData pnlODMRData = (PanelOneDataOneRecData)fieldTxts.get(f);
     /*                  PanelOneDataOneRecData panelODORData =  new PanelOneDataOneRecData(frame);

                     //        entityParameter.getFieldsForSums(), 
                      //listPanelODORData.size
                     panelODORData.setEntity(entityParameter.getEntityPanel()[0], entityParameter.getCaption(),entityParameter.getName(),dbFields,
                              entityParameter.getEntityGroupOfComps(), null,entityParameter.getPrimKey(),"",entityParameter.getPrimKeyDb(),sql,
                              false,false,true, entityParameter.getYearEnforce(),ICO_TABLE16,null,0,panelManagement);
       */                      //
                             
                 
                      
//              PanelOneDataOneRec panelODOR =  new PanelOneDataOneRec(frame);
//              panelODOR.setEntity(entityParameter.getName(),entityParameter.getEntityPanel()[0],"",sql,"", false, false,null,/* do not want to show title so null, entityParameter.getFieldsOnTitle(),
//                       entityParameter.getFieldsOnTitleCaption()*/null, false, "",/*showShowListButtons*/true, true,ICO_TABLE16,panelManagement,null,IS_CALLED_BY_MULTIPLE_TABLES_MDMR);  //0 the only one panel                        
//               listPanelODOR.add(panelODOR);    
//           panelMainCard.add(panelODOR, entityParameter.getCaption());       
                     
           
           //panelODORData.filterForWritableTable(sql, false);
    System.out.println("   cn:"+cn+"   listPanelODOR.size:"+listPanelODOR.size()+"        entityParameterArray.length:"+entityParameterArray.length);
/*           if(entityParameterArray != null && entityParameterArray.length >= 0)// &&  !listPanelODORCaption.get(cn).toString().equalsIgnoreCase(entityParameter.getCaption()) )// && listPanelODOR.get(cn)!=null)// && listPanelODOR.get(cn) != null)
           {
               System.out.println(" IF  cn:"+cn+"   listPanelODOR.size:"+listPanelODOR.size()+"   entityParameterArray.length:"+entityParameterArray.length);
                panelODOR = (PanelOneDataOneRec)listPanelODOR.get(cn);
              if(listPanelODOR.size() >cn && listPanelODORCaption.get(cn).toString().equalsIgnoreCase(entityParameter.getCaption()))
              {
               panelMainCard.add(panelODOR, entityParameter.getCaption());               
               listPanelODOR.add(panelODOR);
               //panelMainCard.get   .getComponent(cn);
               // CardLayout cl = (CardLayout)(panelMainCard.getLayout()); 
               
               //panelMainCard.getComponent(cn);
              }
              else  // == null then add
              {
               
               panelMainCard.add(panelODOR, entityParameter.getCaption());
               listPanelODOR.add(panelODOR);
               listPanelODORCaption.add(entityParameter.getCaption());
              }
           }*/
/*           else if(listPanelODOR != null && listPanelODOR.size() == 0)
           {
               System.out.println("  ELSE   cn:"+cn+"   listPanelODOR.size:"+listPanelODOR.size());
                //panelODOR = (PanelOneDataOneRec)listPanelODOR.get(cn);
                      
           }*/
           
       addPanelForEntity(entityParameterArray,cn);    
           
              CardLayout cl = (CardLayout)(panelMainCard.getLayout()); 
              cl.show(panelMainCard, entityParameter.getCaption());           
            listSelectedButtonsCaption.add(entityParameter.getCaption());
         //System.out.println("PanelManyDataManyRec.selectionInMenu     panelMainCard.getComponentCount:"+panelMainCard.getComponentCount());
  
        this.revalidate();    
    
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));   	
    }  
   
    
    private boolean askPanelIfChanged(int intPanelOfList)
    {
      boolean ret=false;

       
            //EntityParameter[] entityParameter = entityManyDataManyRec.getEntityParameters();
         for(int b=0;b<listPanelODOR.size();b++)   
         {
             
          PanelOneDataOneRec panelODOR = (PanelOneDataOneRec)listPanelODOR.get(b);
          boolean hasDataChanged = panelODOR.getHasDataChanged();             
             
             JxToggleButton btnMenu = (JxToggleButton)listBtnMenu.get(intPanelOfList);
             String selcapt = listSelectedButtonsCaption.get(b)+"";
           // System.out.println("PanelMDMR.askPanelIfChanged       intPanelOfList:"+intPanelOfList+"  b:"+b+"  selcapt:"+selcapt+"  btnText:"+btnMenu.getText()+"  listPanelODOR.size:"+listPanelODOR.size());
            if(selcapt.equalsIgnoreCase(btnMenu.getText()) && hasDataChanged)
            {
                ret = true;
                break;
            }
            else
            {
                ret = false;
            }
         }

        return ret;
    }
    
  public boolean closePanelAsk()
  { // true close panel, false not close panel

     
     boolean ret = false;
      EntityParameter[] entityParameter = entityManyDataManyRec.getEntityParameters();
      
      for(int p=0;p<entityParameter.length;p++)
      {
          
          
          
          boolean hasDataChanged = askPanelIfChanged(p);

          //for(int cn =0; cn<entityParameter.length;cn++)
          //{
         // System.out.println("PanelManyDataManyRec.closePanelAsk  ___________  p("+p+")      caption:"+entityParameter[p].getCaption()+"    name:"+entityParameter[p].getName()+"        hasDataChanged:"+hasDataChanged+"     name:"+entityParameter[p].getName()+"     entityParameter.length:"+entityParameter.length+"   listPanelODOR:"+listPanelODOR.size());
            if(hasDataChanged)
            {
                int ync = utilsGui.showConfirmYesOrNoOrCancel(this,"Στον πίνακα:"+entityParameter[p].getCaption()+" τα δεδομένα έχουν αλλάξει.\n Θέλετε να αποθηκευθούν;");
               System.out.println("PanelManyDataManyRec.closePanelAsk        CHANGED   p("+p+")     return of msg box:"+ync+"    listPanelODOR.size"+listPanelODOR.size()+"    hasDataChanged:"+hasDataChanged+"     name:"+entityParameter[p].getName()+"       caption:"+entityParameter[p].getCaption());
               
               if(ync == 0)//
               {
                  ret=saveChangesForPanel(p);  
                  ret = true;  // true close panel, false not close panel
                  //break;
                  
               }
               else if(ync == 1)// no
               {
                   ret = true;  // true close panel, false not close panel
                   //break;
               }               
               else if(ync == 2)// 
               {
                   ret = false;  // true close panel, false not close panel
                   break;
               }
               else if (ync == 3)
               {
                   ret = false;  // true close panel, false not close panel
                   break;
               }
               else if (ync == -1)
               {
                   ret = false;  // true close panel, false not close panel
                   break;
               }               
                //ret = true;  // true close panel, false not close panel
            } 
            else
            {
                System.out.println("PanelManyDataManyRec.closePanelAsk       NOT CHANGED    p("+p+")   hasDataChanged:"+hasDataChanged);
                ret = true;  // true close panel, false not close panel
                //break;
            }
          //}      
                  
                  
       //           panelODOR.closeDB();
      }
      
  	//boolean ret = panelEditOneDataRec.toClosePanelOrGoBackAskIfDataChanged();// closes panelonedataonerec and twodataonerec
  	
  	//panelOneDataManyRecData.closeDB();
  	
  	return ret;
  }    
    
    
    
   public void setEntity(EntityManyDataManyRec entityManyDataManyRecIn, PanelManagement panelManagementIn)
   {
   //public void setEntity(String nameIn,String captionIn,String subTitleIn, String[] calculationTypeIn, 
   //EntityFilterSettings[] entityFilterSettingsIn,EntityQuery[] entityQueryIn, boolean isNullifyIn)
      
   
         entityManyDataManyRec=entityManyDataManyRecIn;
         panelManagement=panelManagementIn;
         listPanelODORCaption.clear();
         listPanelODOR.clear();
         
           listSelectedButtonsCaption = new ArrayList();   
           addVisibleMenuButtons();
           
           EntityParameter[] entityParameterArray = entityManyDataManyRecIn.getEntityParameters();
           
      /*     for(int cn = 0;cn<entityParameterArray.length; cn++)
           {    
           
                addPanelForEntity(entityParameterArray,cn);
           }
*/
           /*    for(int cn = 0;cn<entityParameterArray.length; cn++)
           {    
              EntityParameter entityParameter =  entityParameterArray[cn];
     
                        //EntityDBFields[] dbFieldsChild = dbFieldsInGroupOfPanels[i].getDbChildFields();
              EntityDBFields[] dbFields = entityParameter.getFields();   
                       
              String sql = entityParameter.getSqlReadOnly();           
              PanelOneDataOneRec panelODOR =  new PanelOneDataOneRec(frame);
        *///       panelODOR.setEntity(entityParameter.getName(),entityParameter.getEntityPanel()[0],"",sql,"", false, false,null,/* do not want to show title so null, entityParameter.getFieldsOnTitle(),
         //               entityParameter.getFieldsOnTitleCaption()*/null, false, "",/*showShowListButtons*/true, true,ICO_TABLE16,panelManagement,null,IS_CALLED_BY_MULTIPLE_TABLES_MDMR,null);  //0 the only one panel                        
          /*     listPanelODOR.add(panelODOR);
               panelMainCard.add(panelODOR, entityParameter.getCaption());	
           }
         */          
           
           
                         
         
           
           
           if(entityParameterArray!=null  &&  entityParameterArray.length>0)
           {
               JxToggleButton btnMenu = (JxToggleButton)listBtnMenu.get(0);
               btnMenu.doClick();
           }    
           
   }
   
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
   
    /*
    *  should be updated
    */
/*   private boolean checkUniquityOfFields(int isUniqueWhen)
   {
       boolean ret=false;
       int intUniquityMessages = 1; //1 no, 0 yes ;
       boolean boolCheck= false;
      //for(int p=0;p<listPanelOneDataOneRecDataData.size();p++)
      // {
             PanelOneDataOneRec pnlODOR = (PanelOneDataOneRec)listPanelODOR.get(0);// the fist panel only
            //IS_UNIQUE_WHILE_DATAENTRY = 0;
           //IS_UNIQUE_BEFORE_SAVING = 1;             
             boolCheck = pnlODOR.checkUniquityOfFields(isUniqueWhen);
             boolCheck=true;
      // }


       return boolCheck;
   }    */
    
    
/*   private boolean saveChanges()  
   {
       
       boolean ret=false;
       int intListMessages;
      // System.out.println("PanelMDMR.saveChanges   size:"+listPanelODOR.size()); 
            //IS_UNIQUE_WHILE_DATAENTRY = 0;
           //IS_UNIQUE_BEFORE_SAVING = 1;         
       if(checkUniquityOfFields(IS_UNIQUE_BEFORE_SAVING))
       {
          intListMessages = checkForListMessages();
           if(intListMessages==0) //1 no, 0 yes       
           {
              saveAfterChecks(intListMessages);
              ret=true;
           }  
       }
       else
       {

                   ret=false;         
       }
       return ret;
   }  
*/    
       
/*   private int checkForListMessages()
   {
       
      //boolean boolContinue=false;
       //PanelOneDataOneRecData pnlODMRData =  new PanelOneDataOneRecData(frame);
       EntityParameter[] entityParameter = entityManyDataManyRec.getEntityParameters();
       
       

       //System.out.println("--++--  PanelMDMR.checkForListMessages   "+title);
      // System.out.println("PanelMDMR.checkForListMessages listPanelOneDataOneRecDataData.size()="+listPanelOneDataOneRecDataData.size());	    
         
      int intListMessages = 1; //1 no, 0 yes
       ArrayList listMessages=new ArrayList();
       ArrayList listMsgs = new ArrayList();
       for(int p=0;p<listPanelODOR.size();p++)
       {
           PanelOneDataOneRec pnl = (PanelOneDataOneRec)listPanelODOR.get(p);
   	  listMsgs = pnl.getListOfFieldsUncompleted();
    	for(int l = 0;l<listMsgs.size();l++)
    	{
    		listMessages.add(listMsgs.get(l));
    	}

          System.out.println("PanelMDMR.checkForListMessages  p:"+p);


       
       if(listMessages!=null)
       {
           
         intListMessages = utilsGui.showMessageFromList(listMessages);       
         //System.out.println(" ------ PanelMDMR.checkForListMessages   utilsGui.showMessageFromList   intListMessages: "+intListMessages);
       //boolean continueListMessages = false;

       }
     }
       return intListMessages;
       
   }
  */ 
 
private boolean saveChangesForPanel(int p) // p for panel, It must be name
{
    boolean boolContinue=false;
      int intListMessages;
      //EntityParameter[] entityParameter = entityManyDataManyRec.getEntityParameters();
     
         
       /*for(int b=0;b<listPanelODOR.size();b++)   
       {
             
          PanelOneDataOneRec panelODOR = (PanelOneDataOneRec)listPanelODOR.get(b);
          boolean hasDataChanged = panelODOR.getHasDataChanged();    
       }*/
              
                  PanelOneDataOneRec pnlODOR = (PanelOneDataOneRec)listPanelODOR.get(p);
                System.out.println("             ---- +  A ---->              PanelMDMR.saveChangesForPanel        p:"+p+"     listPanelODOR.size()"+listPanelODOR.size());  
                  
               if(pnlODOR.checkUniquityOfFields(IS_UNIQUE_BEFORE_SAVING))
               {
                   intListMessages  = pnlODOR.checkForListMessages();
                   System.out.println("         ---- +  B ---->                        PanelMDMR.saveChangesForPanel        p:"+p+"    [ intListMessages:"+intListMessages+" ]    listPanelODOR.size()"+listPanelODOR.size()+"  boolContinue:"+boolContinue);  
                    if(intListMessages==0) //1 no, 0 yes       
                    {
                          pnlODOR.saveAfterChecks(intListMessages);
                          boolContinue=true;
                     }
               }
               else
               {               
                   boolContinue=false; 
               }             
                  //boolContinue = pnlODOR.rowSave();
          //System.out.println("---- +  C ---->PanelMDMR.saveChangesForPanel        p:"+p+"     listPanelODOR.size()"+listPanelODOR.size()+"  boolContinue:"+boolContinue);  
                /*  if(!boolContinue)
                  {
                      break;
                  }*/   
                return boolContinue;
}
    
   
private boolean saveChanges()//    intListMessages==0) //1 no, 0 yes  
{
       boolean boolContinue=false;
     
             for(int p=0;p<listPanelODOR.size();p++)
             {
                 if(saveChangesForPanel(p))
                 {
                     boolContinue = true;
                 }       
                 else
                 {
                     boolContinue = false;
                     
                 }
             }       
    return boolContinue;

    
    }   
    
    
    
    
    
    
  /*  private void saveChangesToErase()
    {
        //PanelOneDataOneRecData pnlODMRData
        // for  ArrayList   length
        //getHasDataChanged();
        //getListOfFieldsUncompleted()
        EntityParameter[] ep = entityManyDataManyRec.getEntityParameters();
        PanelOneDataOneRecData pnlODMRData =  new PanelOneDataOneRecData(frame);// also has initialisation with mediator
        if(pnlODMRData.getHasDataChanged())
        {
            pnlODMRData.saveChanges()
        }
        else
        {
            
        }
    

        
        
    }
   */
   private void setPageSelectionKeys()
   {
       
             Action actionPageKeySelectDown = new PanelManyDataManyRec.ActionPageKeySelectDown();
             KeyStroke pageDown = KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, 0, true);
             panelTitleAndMenu.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(pageDown, "selectPageDown"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
             panelTitleAndMenu.getActionMap().put("selectPageDown", actionPageKeySelectDown);
       
            Action actionPageKeySelectUp = new PanelManyDataManyRec.ActionPageKeySelectUp();
             KeyStroke pageUp = KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, 0, true);
             panelTitleAndMenu.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(pageUp, "selectPageUp"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
             panelTitleAndMenu.getActionMap().put("selectPageUp", actionPageKeySelectUp);
             
   }
      
      
class ToolBarDataMany extends JToolBar implements Constants
{

        public ToolBarDataMany()
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
            btnManySave = new JButton();
            
            
         //setFloatable(false);
         //setRollover(true);
            
/*        btnManyInsert = new JButton();
        btnManyDelete = new JButton();
        btnManySave = new JButton();
        //btnManyPreferences = new JButton();

        setFloatable(false);
        setRollover(true);


        btnManyInsert.setText("<html>εισαγωγή <b>ins</b></html>");
        //btnManyInsert.setText("εισαγωγή (ins)");
        btnManyInsert.setOpaque(false);
        btnManyInsert.setToolTipText("εισαγωγή στην επιλεγμένη σειρά");
        btnManyInsert.setIcon(ICO_ADD);
        btnManyInsert.setFocusable(false);        
        btnManyInsert.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   
	           	   addNewRowIfThereIsnt(false);      
	        } 
	    });
        Action actionNewRecMany = new ActionNewRecMany();
        btnManyInsert.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0), "newRecMany"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnManyInsert.getActionMap().put("newRecMany", actionNewRecMany);

        btnManyDelete.setText("<html>διαγραφή <b>del</b> επιλεγμένης σειράς</html>");
       // btnManyDelete.setText("διαγραφή (del) επιλεγμένης σειράς");
        btnManyDelete.setOpaque(false);
        btnManyDelete.setToolTipText("διαγραφή επιλεγμένης σειράς");
        btnManyDelete.setIcon(ICO_DELETE);
        btnManyDelete.setFocusable(false);        
        //btnDelete.setVerticalTextPosition(AbstractButton.BOTTOM);
        //btnDelete.setHorizontalTextPosition(AbstractButton.CENTER);
        btnManyDelete.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   
	           rowSelectedDelete();        
	        } 
	    });
        Action actionDelRecMany = new ActionDelRecMany();
        btnManyDelete.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delRecMany"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnManyDelete.getActionMap().put("delRecMany", actionDelRecMany);
 */
       
        //btnManySave.setText("<html>αποθήκευση</html>");
        
        btnManySave.setText("<html>αποθήκευση<b>F11</b></html>");
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
	           /*if(rowSave())
                   {
                        rowNew(false);    
                   }*/
                    saveChanges();// this
                   
	        } 
	    });
        Action actionSaveRec = new PanelManyDataManyRec.ActionSaveRec();
        btnManySave.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F11"), "saveRec"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnManySave.getActionMap().put("saveRec", actionSaveRec);

        
        
        
        
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
          this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
          
        //add(lblIcoSeparator1);	   
	   // addSeparator();          
        //addSeparator();
       // add(btnManyInsert);
       // add(btnManyDelete);
        add(btnManySave);
        //addSeparator();
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
      
   class  ActionSaveRec extends AbstractAction                 
   {       
        public ActionSaveRec()
        {        }
      	
    	public void actionPerformed(ActionEvent e)
      	{          btnManySave.doClick();        }    	
    }       
      
class  ActionPageKeySelectDown extends AbstractAction                 
   {       
        public ActionPageKeySelectDown()
        {

        }
        public void actionPerformed(ActionEvent e)
      	{
           if(listBtnMenu.size()>1)//.isVisible() || panelCollapsCategoryMany.isVisible()) 
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
           if(listBtnMenu.size()>1)//.isVisible() || panelCollapsCategoryMany.isVisible()) 
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
      
    
}
