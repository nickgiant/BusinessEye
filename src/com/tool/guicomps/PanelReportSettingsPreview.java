package com.tool.guicomps;

import com.tool.model.EntityGroupOfComps;
import com.tool.model.EntityFilterSettings;
import com.tool.gui.*;
import com.tool.jdbc.*;
import com.tool.utils.*;
import com.tool.model.*;
//import com.tool.report.*;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.JComponent;
import javax.swing.BoxLayout;

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

 public class PanelReportSettingsPreview extends JPanel implements Constants
 {
 	
 	private PanelReportSettings panelReportSettings;
 	private PanelPrintPreview panelPrintPreview;
  
 	private EntityReportBand[] entityReportBand;
// 	 private ToolBarData toolBarData;
       private JButton btnExecute;
       private String entityHeader;
       private Thread thread;
   private WindowWait ww;	
 	private String name;
 	boolean [] boolSettingsReport;
 	int[] intSettingsReport;
 	
   private JFrame frame;
   private EntityReport  entityReport;
 private UtilsString utilsString;
 
     private String[] arrayOfNameOfPksOfRecordToShow;
   private String[] arrayOfValueOfPksOfRecordToShow;
private Database db;
private String entity;   

        public PanelReportSettingsPreview(JFrame frame)
    {
        try {
            initialize(frame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
	private void initialize(JFrame frameIn) throws Exception
    {
         utilsString  = new UtilsString();
    	frame = frameIn;
    	panelReportSettings = new PanelReportSettings(frame);
    	panelPrintPreview = new PanelPrintPreview();

    	JxPanel panelReportSP = new JxPanel();
    	panelReportSP.setLayout (new BoxLayout(panelReportSP, BoxLayout.PAGE_AXIS));
    	panelReportSP.add(panelReportSettings);
    	panelReportSP.add(panelPrintPreview);

    	
    	panelReportSettings.addActionListener(new PanelActionListenerShowReport());
    	panelPrintPreview.addActionListener(new PanelActionListenerShowSettings());
    	db= new Database();

    	this.setLayout(new BorderLayout());

    	this.add(panelReportSP, BorderLayout.CENTER)	;
    	
    }
    
        /**
         * 
         *   called by panelManagement, PanelReportSettingsPreview.
         */
/*    public void setEntity(String nameIn,String entityIn,EntityReportBand[] entityReportBandIn,String viewTypeIn, String primKeyValueIn, Vector dataVectorIn, 
         String[] sql2WhereFieldIn, String[] sql2WhereValueIn,String titleIn,String subTitleIn,
	       boolean showSummaryPageIn, EntityFilterSettings[] entityReportSettingsIn, EntityGroupOfComps[] entityGroupOfComps,int[] fieldSelectedIn,
	       boolean[] fieldsEditableIn,int[] fieldsOrderby,int[] intSettingsReportIn,  boolean[] boolSettingsReportIn , String yearEnforce, PanelManagement panelManagement)
*/
  public void setEntity(EntityReport erpt, boolean showSummaryPageIn,  PanelManagement panelManagementIn)        
  {

   /* erpt.getEntityReportBand();
    erpt.getType();
    erpt.getCaption(); 
    erpt.getSubTitle();
    erpt.getEntityFilterSettings();
    erpt.getEntityGroupOfComps();
    erpt.getIsFieldSelected();
    erpt.getAreFieldsEditable();
    erpt.getFieldOrderby();
   
    erpt.getBoolSettingsReport();
    erpt.getYearEnforce();
       name=erpt.getName();
       entityReportBand=erpt.getEntityReportBand();*/
      //System.out.println("PanelReportSettingsPreview.setEntity type:"+erpt.getType());

        intSettingsReport= erpt.getIntSettingsReport();    // must get from gui
        boolSettingsReport= erpt.getBoolSettingsReport();
        entityReport = erpt;      
      
      
      if(erpt.getType().equalsIgnoreCase("FORM"))// only setEntityMassPreviewOfForm
      {
          setEntityMassPreviewOfForm( erpt,  showSummaryPageIn,  panelManagementIn);  
      }
      else
      {

       panelReportSettings.setEntity( erpt,  showSummaryPageIn,  panelManagementIn);   
       

         showSettings();//// direct 1, mass 2
      }
    }

  /*
  *
  *  called by PanelODOR.showPrintPreviewForm
  */
  public void setEntityDirectPreviewOfForm(String entityIn,EntityReport erpt,String[] arrayOfNameOfPksOfRecordToShowIn,String[] arrayOfValueOfPksOfRecordToShowIn, boolean showSummaryPageIn,
          PanelManagement panelManagementIn)        
  {

      entity=entityIn;
      
       arrayOfNameOfPksOfRecordToShow=arrayOfNameOfPksOfRecordToShowIn;
       arrayOfValueOfPksOfRecordToShow=arrayOfValueOfPksOfRecordToShowIn;
       
       panelReportSettings.setEntityForDirectPrintOfForm( erpt,  panelManagementIn);//, arrayOfNameOfPksOfRecordToShowIn, arrayOfValueOfPksOfRecordToShowIn);   
       
        intSettingsReport= erpt.getIntSettingsReport();    // must get from gui
        boolSettingsReport= erpt.getBoolSettingsReport();
        entityReport = erpt;     
      
       showReport();// direct 1, mass 2
  }  
 
 
  
  /*
  *
  *  called by this.setEntity
  */
  private void setEntityMassPreviewOfForm(EntityReport erpt, boolean showSummaryPageIn,  PanelManagement panelManagementIn) //(EntityReport erpt,String[] arrayOfNameOfPksOfRecordToShowIn,String[] arrayOfValueOfPksOfRecordToShowIn, boolean showSummaryPageIn,
          //PanelManagement panelManagementIn)        
  {
 
       String[] arrayOfNameOfPksOfRecordToShowIn;
        String[] arrayOfValueOfPksOfRecordToShowIn;

        panelReportSettings.setEntity( erpt,  showSummaryPageIn,  panelManagementIn);
       
       /* intSettingsReport= erpt.getIntSettingsReport();    // must get from gui
        boolSettingsReport= erpt.getBoolSettingsReport();
        entityReport = erpt;*/      
     
        showSettings();//// direct 1, mass 2
  } 
  
  
   private class PanelActionListenerShowSettings implements ActionListener
  {// look at     public void addActionListener(ActionListener al) in panelPrintPreview and PanelReportSettings
    // here:
    //      	panelReportSettings.addActionListener(new PanelActionListener());
    //    	panelPrintPreview.addActionListener(new PanelActionListener());
    public void actionPerformed(ActionEvent e)
    {
      //System.out.println("PanelActionListener \"" + e.getActionCommand() + "\" performed from within PanelReportSettingsPreview class");

        showSettings();// direct 1, mass 2
    }
  }
          
          
          


   private class PanelActionListenerShowReport implements ActionListener
   {// look at     public void addActionListener(ActionListener al) in panelPrintPreview and PanelReportSettings
    // here:
    //      	panelReportSettings.addActionListener(new PanelActionListener());
    //    	panelPrintPreview.addActionListener(new PanelActionListener());
    public void actionPerformed(ActionEvent e)
    {
      //System.out.println("PanelActionListener \"" + e.getActionCommand() + "\" performed from within PanelReportSettingsPreview class");

        showReport();// direct 1, mass 2
    }
  }
  

    private void showReport()//int directOrMass)// direct 1, mass 2
   {
            boolean boolFieldsCompleted = panelReportSettings.checkIfFieldsAreCompleted();
            if( boolFieldsCompleted)
            {
   	       final WindowWait ww = new WindowWait("παρακαλω περιμένετε",WINDOW_LOCATION_CENTER,ICO_RELOAD16, ICO_RELOADB16);
             ww.animate();
       	      thread = new Thread(new Runnable() {
	          public void run()
	          {
	           
           	       ww.showWindow();
	               thread = null;
	          }
	          });
              thread.start();
              
   	thread = new Thread(new Runnable() {
	          public void run()
	          {

    
     if(entityReport.getType().equalsIgnoreCase("FORM"))       
     {
       
         panelReportSettings.calculateSQLForPrintPreview();// disable when    calculateAndGetSQLForPrintPreviewOfForm
  //       String strQuery = panelReportSettings.calculateAndGetSQLForPrintPreviewOfForm();
         String strQuery = panelReportSettings.getSqlForPrintPreview();
         System.out.println(".PanelReportSettingsPreview.showReport        FORM     +++++++        strQuery:"+strQuery);
         panelPrintPreview.setEntityForPreviewOfForm(entity,entityReport,panelReportSettings.getIsDotmatrix(), panelReportSettings.getStrPageSizeDotmatrix(), 
                  panelReportSettings.getStrPortDotmatrix(), panelReportSettings.getArrayStringsToBePrinted(), panelReportSettings.getPageFormat() /*pf*/, intSettingsReport,
        panelReportSettings.getTitle(),panelReportSettings.getSubTitle(),strQuery,// panelReportSettings.calculateAndGetSQLForPrintPreviewOfForm(),
        panelReportSettings.getSelectedPageSizeLaser(),  panelReportSettings.getSelectedPageOrientationIsPortraitLaser(),
        panelReportSettings.getDotmatrixCpi(),arrayOfNameOfPksOfRecordToShow,arrayOfValueOfPksOfRecordToShow);                  
         
        
     }
     else
     {
         
        panelReportSettings.calculateSQLForPrintPreview();
        //System.out.println("PanelReportSettingsPreview.showPanelReport     +o+       getSqlForPrintPreview:"+panelReportSettings.getSqlForPrintPreview());
         
          panelPrintPreview.setEntity(entity,entityReport,panelReportSettings.getIsDotmatrix(), panelReportSettings.getStrPageSizeDotmatrix(), 
                  panelReportSettings.getStrPortDotmatrix(), panelReportSettings.getArrayStringsToBePrinted(), panelReportSettings.getPageFormat(), intSettingsReport,
        panelReportSettings.getTitle(),panelReportSettings.getSubTitle(), panelReportSettings.getSqlForPrintPreview(),panelReportSettings.getShowColumnsPerBand(),
        panelReportSettings.getShowColumnsHeader(),panelReportSettings.getSelectedPageSizeLaser(),  panelReportSettings.getSelectedPageOrientationIsPortraitLaser(),
        panelReportSettings.getEntityFilterSettings(), panelReportSettings.getDotmatrixCpi());        
     }
	   panelReportSettings.setVisible(false);
	   panelPrintPreview.setVisible(true);
           panelPrintPreview.revalidate();
//           panelReportPreview.setVisible(true);
	   
	               ww.close();
	               panelPrintPreview.requestFocus();
	               thread = null;
	          }
	          });
              thread.start();  
            }     
   }
  
 
    private void showSettings()//int directOrMass)// direct 1, mass 2
   {
             panelReportSettings.setVisible(true);
	     panelPrintPreview.setVisible(false); 
	     panelReportSettings.requestFocus();        
   }    
    
/*
  *
  *
  *
  */
 /*  private void showPanelReport()//int directOrMass)// direct 1, mass 2
   {       
    

   	if(panelReportSettings.isVisible() || entityReport.getType().equalsIgnoreCase("FORM")) //      show print preview
   	{
            //System.out.println(".PanelReportSettingsPreview.showPanelReport   IF   isVisible:"+panelReportSettings.isVisible()+"  type:"+entityReport.getType());
            boolean boolFieldsCompleted = panelReportSettings.checkIfFieldsAreCompleted();
            if( boolFieldsCompleted)
            {
   	       final WindowWait ww = new WindowWait("παρακαλω περιμένετε",WINDOW_LOCATION_CENTER,ICO_RELOAD16, ICO_RELOADB16);
             ww.animate();
       	      thread = new Thread(new Runnable() {
	          public void run()
	          {
	           
           	       ww.showWindow();
	               thread = null;
	          }
	          });
              thread.start();
              
   	thread = new Thread(new Runnable() {
	          public void run()
	          {
   	  
           
         
                      
    
     if(entityReport.getType().equalsIgnoreCase("FORM"))       
     {
       
         panelReportSettings.calculateSQLForPrintPreview();// disable when    calculateAndGetSQLForPrintPreviewOfForm
  //       String strQuery = panelReportSettings.calculateAndGetSQLForPrintPreviewOfForm();
         String strQuery = panelReportSettings.getSqlForPrintPreview();
         System.out.println(".PanelReportSettingsPreview.showPanelReport        FORM     +++++++        strQuery:"+strQuery);
     */ //   panelPrintPreview.setEntityForPreviewOfForm(entityReport,panelReportSettings.getIsDotmatrix(), panelReportSettings.getStrPageSizeDotmatrix(), 
    //              panelReportSettings.getStrPortDotmatrix(), panelReportSettings.getArrayStringsToBePrinted(), panelReportSettings.getPageFormat() /*pf*/, intSettingsReport,
     //   panelReportSettings.getTitle(),panelReportSettings.getSubTitle(),strQuery,// panelReportSettings.calculateAndGetSQLForPrintPreviewOfForm(),
    /*    panelReportSettings.getSelectedPageSizeLaser(),  panelReportSettings.getSelectedPageOrientationIsPortraitLaser(),
        panelReportSettings.getDotmatrixCpi(),arrayOfNameOfPksOfRecordToShow,arrayOfValueOfPksOfRecordToShow);                  
         
        
     }
     else
     {
         
        panelReportSettings.calculateSQLForPrintPreview();
        //System.out.println("PanelReportSettingsPreview.showPanelReport     +o+       getSqlForPrintPreview:"+panelReportSettings.getSqlForPrintPreview());
         
          panelPrintPreview.setEntity(entityReport,panelReportSettings.getIsDotmatrix(), panelReportSettings.getStrPageSizeDotmatrix(), 
                  panelReportSettings.getStrPortDotmatrix(), panelReportSettings.getArrayStringsToBePrinted(), panelReportSettings.getPageFormat(), intSettingsReport,
        panelReportSettings.getTitle(),panelReportSettings.getSubTitle(), panelReportSettings.getSqlForPrintPreview(),panelReportSettings.getShowColumnsPerBand(),
        panelReportSettings.getShowColumnsHeader(),panelReportSettings.getSelectedPageSizeLaser(),  panelReportSettings.getSelectedPageOrientationIsPortraitLaser(),
        panelReportSettings.getEntityFilterSettings(), panelReportSettings.getDotmatrixCpi());        
     }

	   
	   panelReportSettings.setVisible(false);
	   panelPrintPreview.setVisible(true);
           panelPrintPreview.revalidate();
//           panelReportPreview.setVisible(true);
	   
	               ww.close();
	               panelPrintPreview.requestFocus();
	               thread = null;
	          }
	          });
              thread.start();  

            }
	}
        else
        {
            //System.out.println(".PanelReportSettingsPreview.showPanelReport   ELSE   isVisible:"+panelReportSettings.isVisible()+"  type:"+entityReport.getType());
            //Go back to filters when not in form
             if(entityReport.getType().equalsIgnoreCase("FORM"))       
             {             

             panelReportSettings.setVisible(false);
             panelPrintPreview.setVisible(true); 
             panelPrintPreview.requestFocus();

         
             }
             else
             {
             panelReportSettings.setVisible(true);
	     panelPrintPreview.setVisible(false); 
	     panelReportSettings.requestFocus();
            
            //System.out.println("  error  PanelReportSettingsPreview   else NOT DEFINED    entityReport.getType():"+entityReport.getType()+"      query "+panelReportSettings.getSqlForPrintPreview());
             }
        
        }
       
	  	//System.out.println("PanelReportSettingsPreview query "+panelReportSettings.getSqlForPrintPreview());
   }*/
  

   
/*class ToolBarData extends JToolBar implements Constants
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
        btnExecute = new JButton();
    //    btnPrint = new JButton();
        btnNewPrintDesign = new JButton();
        btnPrintDesign = new JButton();
        
        	
        setFloatable(false);
        setRollover(true);
        this.setOpaque(false);
         
        
        btnExecute.setText("<html>προεπισκόπηση <b>F7</b></html>");
        btnExecute.setOpaque(false);
        //btnExecute.setText("<html>προεπισκόπηση <b>O</b></html>");	    
       // btnExecute.setText("<html>προεπισκόπηση <b>alt+O</b></html>");
        btnExecute.setToolTipText("προεπισκόπηση εκτύπωσης");
        btnExecute.setIcon(ICO_PRINT_PREVIEW16);
        //btnExecute.setMnemonic(KeyEvent.VK_O);
        btnExecute.setFocusable(false);
        btnExecute.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	 
	          showPanel(); 
	        }
	    });
        Action actionPrintPreview = new ActionPrintPreview();
        btnExecute.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), "report"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnExecute.getActionMap().put("report", actionPrintPreview);

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
        btnExecute.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), "report"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnExecute.getActionMap().put("report", actionPrintPreview);*/

/*        btnNewPrintDesign.setText("<html><b>ν</b>έο σχέδιο</html>");
        btnNewPrintDesign.setOpaque(false);
        btnNewPrintDesign.setToolTipText("νέο σχέδιο εκτύπωσης");
        btnNewPrintDesign.setIcon(ICO_PRINT_PREVIEW16);
        btnNewPrintDesign.setMnemonic(KeyEvent.VK_E);
        btnNewPrintDesign.setFocusable(false);
        btnNewPrintDesign.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	     }
	    });
        /*Action actionPrintPreview = new ActionPrintPreview();
        btnExecute.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), "report"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnExecute.getActionMap().put("report", actionPrintPreview);*/

 /*       btnPrintDesign.setText("<html><b>σ</b>χεδιασμός</html>");
        btnPrintDesign.setOpaque(false);
        btnPrintDesign.setToolTipText("σχεδιασμός εκτύπωσης");
        btnPrintDesign.setIcon(ICO_PRINT_PREVIEW16);
        btnPrintDesign.setMnemonic(KeyEvent.VK_E);
        btnPrintDesign.setFocusable(false);
        btnPrintDesign.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	    }
	    });
        /*Action actionPrintPreview = new ActionPrintPreview();
        btnExecute.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), "report"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnExecute.getActionMap().put("report", actionPrintPreview);*/
        
/*         IconSeparator icoSeparator = new IconSeparator();
        
         JLabel lblIcoSeparator1 =new JLabel();
         JLabel lblIcoSeparator2 =new JLabel();
         JLabel lblIcoSeparator3 =new JLabel();

        
        lblIcoSeparator1.setOpaque(false);
        //lblIcoSeparator.setHorizontalAlignment(SwingConstants.RIGHT);
         
         lblIcoSeparator1.setIcon(icoSeparator);
         lblIcoSeparator2.setIcon(icoSeparator);
         lblIcoSeparator3.setIcon(icoSeparator);
          
        add(lblIcoSeparator1);
        //addSeparator();
        //addSeparator();
        add(btnExecute);
       // add(btnPrint);
        add(lblIcoSeparator2);
        add(btnNewPrintDesign);
        add(btnPrintDesign);
        add(lblIcoSeparator3);
        ///addSeparator();
        //addSeparator();
        }  
        
    /*    protected void paintComponent(Graphics g)
        {
             Graphics2D g2 = (Graphics2D) g;   //                     15
             GradientPaint paint = new GradientPaint(0, 0, this.getBackground().brighter(), 0, 30, this.getBackground().darker(),true);
             g2.setPaint(paint);
             g2.fill(getBounds());
             super.paintComponent(g);
        }*/
  //}*/
    
   
   public void closeDB()
   {
       //System.out.println("PanelODOR.closeDB");
       
       db.releaseConnectionRs();
       db.releaseConnectionRsmd();
   }
       
   
   class  ActionPrintPreview extends AbstractAction                 
   {       
        public ActionPrintPreview()
        {      }
      	
    	public void actionPerformed(ActionEvent e)
      	{     
      	  btnExecute.doClick();
      	}    	
    }     
}