//// created 07-03-2015
// similarities between DialogDataConfic and DialogDataEditConfig
/*

 */

package com.tool.gui;

import com.tool.guicomps.*;
import com.tool.utils.*;
import com.tool.jdbc.*;//PanelDataViewNOrder
import com.tool.model.*;






import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.Writer;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.JLabel;
import javax.swing.JComboBox;

/**
 *
 * @author sun
 */
public class DialogDataEditConfig extends JDialog implements Constants
{
      private String entity;
   private String filePrefsUniqueFields;
   private UtilsString utilsString;
   private UtilsPanelReport utilsPanelReport;
   private UtilsFileSystem utilsFileSystem;
   private JPanelDecorated panelMain;
      private JButtonForPanelDecorated btnSet;
   private JButtonForPanelDecorated btnClose;
   private JxPanel panelBottomButtons;
   private JxTabbedPane tabbedPane;
   //private JLabel lblFields;
   private JComboBox cmbChooseActionDataEntry;
   private JComboBox cmbChooseActionSave;
   private JLabel lblFields;
   
   public DialogDataEditConfig(JFrame parent)
   {
   	    	super(parent,"επιλογές για",true);
  
            try
           {    initialize(parent);    }
           catch (Exception e)
           {   e.printStackTrace();    }   
   }
   
   	private void initialize(JFrame parent) throws Exception
    {
       
       utilsFileSystem = new UtilsFileSystem();
       utilsString = new UtilsString();
       utilsPanelReport = new UtilsPanelReport();
       
        panelMain= new JPanelDecorated();
    	panelMain.setLayout(new BorderLayout());   
        JxPanel pnlComponents = new JxPanel();
        pnlComponents.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 2,9,9));
        //pnlComponents.setb
        
         tabbedPane = new JxTabbedPane();
        
        JLabel lblFieldsCaption = new JLabel("πεδία ελέγχου:");
        lblFields = new JLabel("");
        JLabel lblAvailableActions1 = new JLabel("ενέργεια κατα τη διαρκεια της καταχώρησης:");
        cmbChooseActionDataEntry = new JComboBox();
        JLabel lblAvailableActions2 = new JLabel("ενέργεια κατα την αποθήκευση:");
        cmbChooseActionSave = new JComboBox();
        
       pnlComponents.add(lblFieldsCaption);
       pnlComponents.add(lblFields);
       pnlComponents.add(lblAvailableActions1);
       pnlComponents.add(cmbChooseActionDataEntry);
       pnlComponents.add(lblAvailableActions2);
       pnlComponents.add(cmbChooseActionSave);
       
       tabbedPane.addTab("έλεγχος μοναδικότητας εγγραφής", pnlComponents);
       panelMain.add(tabbedPane, BorderLayout.PAGE_START);
       
        //-------------- bottom buttons  -----------------
        btnSet = new JButtonForPanelDecorated();
        btnSet.setText("<html>"+UtilsResource.getString("Apply")+" <b>F5</b></html>");
        btnSet.setIcon(ICO_OK16);
        btnSet.setFocusable(false);
        btnSet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {   setButton() ;   }
        });
        Action actionSet = new DialogDataEditConfig.ActionSet();
        btnSet.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F5"), "set"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnSet.getActionMap().put("set", actionSet);


        btnClose = new JButtonForPanelDecorated();
        btnClose.setText("<html>"+UtilsResource.getString("Cancel")+" <b>esc</b></html>");
        btnClose.setIcon(ICO_CANCEL16);
        btnClose.setFocusable(false);
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 closeWindow();//frame.setVisible(false);//DialogLookUp.dialog.setVisible(false);
            }
        });
        Action actionClose = new DialogDataEditConfig.ActionClose();
        btnClose.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnClose.getActionMap().put("close", actionClose);
        
        panelBottomButtons = new JxPanel();
        //panelBottomButtons.setLayout(new BoxLayout(panelBottomButtons, BoxLayout.X_AXIS));
        panelBottomButtons.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
       // panelBottomButtons.add(Box.createHorizontalGlue());
        panelBottomButtons.add(btnSet);
        panelBottomButtons.add(Box.createRigidArea(new Dimension(10, 0)));
        panelBottomButtons.add(btnClose);

        //Put everything together, using the content pane's BorderLayout.
       
        //panelMain.add(lblTop, BorderLayout.PAGE_START);
        //panelMain.add(tabbedPane, BorderLayout.PAGE_START);
        panelMain.add(panelBottomButtons, BorderLayout.PAGE_END);
        
        Container contentPane = getContentPane();
        contentPane.add(panelMain, BorderLayout.CENTER);        
        
        
          //following  String exists in PanelODORData.initialize and DialogDataEditConfig.initialize
        filePrefsUniqueFields =VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+FILE_DATAEDIT_PREFERENCES;
       //System.out.println("DialogDataEditConfig.initialize");
    }
        
    private void closeWindow()
    {
       dispose();
    }        
        
    private void setButton()
    { 
    	
    	saveConfig();
        dispose();
       /*   lookUp = new LookUp();
          System.out.println("DialogLookUp.actionSetButton() key "+lookUp.getLookUpKeyFT(lookupEntity));
          DialogLookUp.keyValue = panelOneDataManyRecData.getSelectedDataRowKeyValue(lookUp.getLookUpKeyFT(lookupEntity));
          DialogLookUp.dialog.setVisible(false);
          System.out.println("DialogLookUp.actionSetButton() "+DialogLookUp.keyValue);
          */
    }
    
    /*
    *  called by
    */
    
    public void setEntity(EntityPanel entityPanel,String title, String fieldsToCheck)//, String queryIn, int[] fieldsOrderbyIn,String primKeyDb, EntityFilterSettings[] entityFilterSettingsIn)  // if query is null use entity
    {
    	
    	entity=entityPanel.getEntity();
    	//query=queryIn;
    	//fieldsOrderby=fieldsOrderbyIn;
        this.setTitle(title);
        lblFields.setText(fieldsToCheck);
        
       // exists with modifications in DialogDataEditConfig.setEntity and in PanelODORData.checkUniquityOfFields (comments)
        cmbChooseActionDataEntry.addItem("καμία");
        cmbChooseActionDataEntry.addItem("προειδοποίηση");
        //cmbChooseActionDataEntry.addItem("απαγόρευση");
        
        cmbChooseActionSave.addItem("καμία");
        cmbChooseActionSave.addItem("προειδοποίηση");
        cmbChooseActionSave.addItem("απαγόρευση");
        
        
        readSettingsFromFileOrApp(entityPanel);
    }
    
    
    /*
    *  //reading from file or from EntityData,  exists with modifications in DialogDataEditConfig.readSettingsFromFileOrApp and in PanelODORData.readSettingsFromFileOrApp
    */
    private void readSettingsFromFileOrApp(EntityPanel entityPanel)
    {
        
   XMLReader reader = new XMLReader();
   String[] tagElements ={"name"};
   String[] tagElementsType ={"String"};    	
    	
     if(reader.checkIfElementExists(filePrefsUniqueFields, "DataEditUniqueFields",tagElements,tagElementsType,entity))
     {

       // load file for intsShowColumns ------------------------------------
          String[] tagElements1 ={"name","actionWhileDataEntry"};
          String[] tagElementsType1 ={"String", "String"}; 
          if(entityPanel.getUniqueWhileDataentryEditable())
          {
           int  actionWhileDataEntry = utilsPanelReport.loadDataFromXmlFileRetInt(filePrefsUniqueFields, "DataEditUniqueFields",tagElements1,tagElementsType1,1,entity); 
           cmbChooseActionDataEntry.setSelectedIndex(actionWhileDataEntry);     
          }
          else
          {
              cmbChooseActionDataEntry.setEnabled(false);
          }
     	// load file for intsOrderBy ------------------------------------
          String[] tagElements2 ={"name","actionBeforeSaving"};
          String[] tagElementsType2 ={"String", "String"};  
          if(entityPanel.getUniqueBeforeSaveEditable())
          {          
                 int  actionBeforeSaving = utilsPanelReport.loadDataFromXmlFileRetInt(filePrefsUniqueFields, "DataEditUniqueFields",tagElements2,tagElementsType2,1,entity); 
                 cmbChooseActionSave.setSelectedIndex(actionBeforeSaving);
          }
          else
          {
              cmbChooseActionSave.setEnabled(false);
          }
     }
     else
     {//get prefs from app   
         cmbChooseActionDataEntry.setSelectedIndex(entityPanel.getUniqueWhileDataentry()); 
         cmbChooseActionDataEntry.setEnabled(entityPanel.getUniqueWhileDataentryEditable());
         
          cmbChooseActionSave.setSelectedIndex(entityPanel.getUniqueBeforeSave());
          cmbChooseActionSave.setEnabled(entityPanel.getUniqueBeforeSaveEditable());
     }

    }    
    
    
    // first create a file in DialogMain.main, also below in composeAndGetDataEditPrefsXML
    private void saveConfig()
    {
    	//utilsFileSystem.writeFile(composeAndGetPrefsXML(),filePrefsUniqueFields);
    	
   XMLReader reader = new XMLReader();
   
   String[] tagElements ={"name"};
   String[] tagElementsType ={"String"};    	
    	
     if(reader.checkIfElementExists(filePrefsUniqueFields, "DataEditUniqueFields",tagElements,tagElementsType,entity))
     {
     	//System.out.println("DialogDataConfig.saveConfig replace for "+entity);
     	// replace
     	utilsFileSystem.replaceElementInXMLFile(filePrefsUniqueFields,"DataEditUniqueFields",entity,composeAndGetDataEditPrefsXML(),tagElements,tagElementsType);
     }
     else
     {
     	//System.out.println("DialogDataConfig.saveConfig add for "+entity);
     	utilsFileSystem.addToXMLFile(composeAndGetDataEditPrefsXML(),filePrefsUniqueFields,"</DataEditUniqueFieldsPrefs>");// also in DialogMain.composeAndGetPrefsDataEntryXML
     	//add
     }

    }     

    
   private String composeAndGetDataEditPrefsXML()
  {

      String ret = "";
      
        Writer writer = new java.io.StringWriter();
        XmlWriter xmlwriter = new XmlWriter(writer);
    
    //xmlwriter.writeEntity("TablePrefs"); 
    //xmlwriter.changeLine(); 
    //xmlwriter.writeEntity("property");   
    //for(int l =0;l<tableReportFields.getModel().getRowCount();l++)
    //{  
            xmlwriter.writeEntity("DataEditUniqueFields");
       

        	xmlwriter.writeEntity("name").writeText(entity);
        	xmlwriter.endEntity();
        	
                
                // fields and actions
        	//xmlwriter.writeEntity("fields").writeText(getSqlOrderBy());
        	//xmlwriter.endEntity(); 
                
        	xmlwriter.writeEntity("actionWhileDataEntry").writeText(cmbChooseActionDataEntry.getSelectedIndex()+"");
        	xmlwriter.endEntity();                 

        	xmlwriter.writeEntity("actionBeforeSaving").writeText(cmbChooseActionSave.getSelectedIndex()+"");
        	xmlwriter.endEntity();                                 

                
                
                
                
                xmlwriter.endEntity(); // page
          xmlwriter.changeLine(); 
    

     //xmlwriter.endEntity(); // props 
     //xmlwriter.close();
  	 
      ret = writer.toString();
 
      System.out.println("DialogDataEditConfig.composeAndGetDataEditPrefsXML    "+entity+"    "+filePrefsUniqueFields);
      //ret = entity;
      
      return ret;
  }
        
    private void locateOnCenterOfTheScreen()
    {
    	Dimension paneSize   = this.getSize();
    	Dimension screenSize = this.getToolkit().getScreenSize();
    	this.setLocation(
            (screenSize.width  - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
    }

     public void showDialog()
     {
     	
        this.pack();
        locateOnCenterOfTheScreen();
        this.setVisible(true);      
        //this.showDialog();
     }        
        

    class  ActionSet extends AbstractAction                 
    {       
        public ActionSet()
        {        }    	
    	public void actionPerformed(ActionEvent e)
      	{          btnSet.doClick();       }    	
    }                

    class  ActionClose extends AbstractAction                 
    {       
        public ActionClose()
        {        }
    	public void actionPerformed(ActionEvent e)
      	{           btnClose.doClick();        }    	
    }                
    


}