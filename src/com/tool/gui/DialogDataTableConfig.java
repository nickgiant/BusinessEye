// created 23-09-2006
// similarities between DialogDataConfic and DialogDataEditConfig

package com.tool.gui;

import com.tool.model.EntityFilterSettings;
import com.tool.guicomps.*;
import com.tool.jdbc.*;//PanelDataViewNOrder
import com.tool.utils.*;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.TableModel;
//import javax.swing.table.DefaultTableColumnModel;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableColumn;
import javax.swing.table .TableCellRenderer;
import javax.swing.ListSelectionModel;
//import javax.swing.event.TableModelListener;
//import javax.swing.event.TableModelEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.InputMap;
import javax.swing.ButtonGroup;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.CardLayout;
import java.awt.FlowLayout;
 import java.awt.KeyboardFocusManager;


import java.sql.ResultSetMetaData;
import java.sql.SQLException;


import java.util.ArrayList;
import java.util.Vector;
  import java.util.Set;
  import java.util.HashSet;

import java.io.*;


public class DialogDataTableConfig extends JDialog implements Constants
{

   
   //private JxTabbedPane tabbedPane;
   
   //final static String PANELVIEW = "JPanelView";
   //final static String PANELORDER = "JPanelOrder";
   
   private String entity;
   private String query;
   private int[] fieldsOrderby;
   private JPanelDecorated panelMain;
   private JxPanel panelViewMain;
   
   private JPanelDecorated panelViewNOrder;
   private JPanelDecorated panelFilterSettings;
   
   private JPanel panelDataView;
   private JPanel panelViewTop;
   //private JPanel panelViewTopCheckButtons;

   private JxPanel panelOrderMain;
   private JPanel panelOrderData;
   private JPanel panelOrderMainEachRow;
   
   private JxPanel panelBottomButtons;
   
  // private JTable table;
  // private TableModelResultSetCheckBoxes tableModel;
  // private JScrollPane scrollpaneView;
   private ListSelectionModel lsm;   
   private ResultSetMetaData rsmd;
  // private JLabel lblOrder;
  // private JComboBox cmbOrder;
  // private DefaultComboBoxModel mdlOrder;
   
   private Database db= new Database();
   private JButtonForPanelDecorated btnSet;
   private JButtonForPanelDecorated btnClose;
  // private JButton btnCheckAll;
  // private JButton btnCheckNone;
   //private JLabel lblTop;
   private UtilsTable utilsTable;
  // private final int CHECKBOX_COLUMN = 0;
   //private final String ORDER_NO="καμία";
   
   private int columns;
   private JCheckBox chkView;
   
   private ArrayList fieldView = new ArrayList();
   private ArrayList fieldOrder = new ArrayList();
  // private int orderComboboxes=4;

   private UtilsFileSystem utilsFileSystem;
   
   private JxTabbedPane tabbedPane;
   
   //private PanelDataFilter panelDataFilter;
   private PanelDataViewNOrder panelDataViewNOrder;
   //private PanelDataFilterSettings panelDataFilterSettings;
   
   //private final String TAB_FILTERVIEW ="προβολή φίλτρων";
   private final String TAB_COLVIEWNORDER ="προβολή και ταξινόμηση στηλών";
   private String fileTablePrefs;
   private UtilsString utilsString;
   private UtilsPanelReport utilsPanelReport;
   
   public DialogDataTableConfig(JFrame parent)
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
       
       
       //panelDataFilter = new PanelDataFilter(parent);
       panelDataViewNOrder = new PanelDataViewNOrder();
       //panelDataFilterSettings = new PanelDataFilterSettings(parent);
       
       tabbedPane = new JxTabbedPane();
       //  tabbedPane.setUI(new AquaBarTabbedPaneUI()); 
         	// PlasticTabbedPaneUI      AquaBarTabbedPaneUI       

       
    	panelMain= new JPanelDecorated();
    	panelMain.setLayout(new BorderLayout());
    	
    	panelFilterSettings = new JPanelDecorated();
    	panelFilterSettings.setLayout(new FlowLayout());
    	
    	panelViewNOrder = new JPanelDecorated();
    	//panelViewNOrder.setLayout(new BoxLayout(panelViewNOrder, BoxLayout.LINE_AXIS));//(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 1));;//(new BorderLayout());
        panelViewNOrder.setLayout(new BorderLayout());
         
        tabbedPane.addTab(TAB_COLVIEWNORDER,  panelViewNOrder);
        //tabbedPane.addTab(TAB_FILTERVIEW, null, panelFilterSettings,null);
        

        //-------------- bottom buttons  -----------------
        btnSet = new JButtonForPanelDecorated();
        btnSet.setText("<html>"+UtilsResource.getString("Apply")+" <b>F5</b></html>");
        btnSet.setIcon(ICO_OK16);
        btnSet.setFocusable(false);
        btnSet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {   setButton() ;   }
        });
        Action actionSet = new ActionSet();
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
        Action actionClose = new ActionClose();
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
        panelMain.add(tabbedPane, BorderLayout.PAGE_START);
        panelMain.add(panelBottomButtons, BorderLayout.PAGE_END);
        
        Container contentPane = getContentPane();
        contentPane.add(panelMain, BorderLayout.CENTER);
        
        fileTablePrefs =VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+FILE_TABLEPREFERENCES;
   }
   
    public void setEntity(String entityIn,String title, String queryIn, int[] fieldsOrderbyIn,String primKeyDb, EntityFilterSettings[] entityFilterSettingsIn)  // if query is null use entity
    {
    	
    	entity=entityIn;
    	query=queryIn;
    	fieldsOrderby=fieldsOrderbyIn;

    	
    	
    	this.setTitle(title);
            if (panelViewMain != null)
            {  panelViewMain.removeAll(); }
       
          	    db.retrieveDBDataFromQuery(query,"DialogDataConfig.setEntity");
   	          //rs=db.getRS();
   	          rsmd=db.getRSMetaData();
   	    
      try
      {      	
         columns = rsmd.getColumnCount();
         //
         RecColumn[] recColumn = new RecColumn[columns];
                   //strColsCaption = new String[rsmd.getColumnCount()];
                   //strColsName = new String[rsmd.getColumnCount()];
           //strCols[0]="";
           //int cNew=1;
          
              for (int c = 1; c <= columns; c++)
              {

                      //System.out.println("DialogDataTableConfig.setEntity "+c+" "+rsmd.getColumnName(c));
                      //recColumn[cNew-1]= new RecColumn(rsmd.getColumnName(c),rsmd.getColumnLabel(c),rsmd.getColumnType(c),rsmd.getTableName(c),rsmd.getColumnTypeName(c),rsmd.getColumnDisplaySize(c),0,rsmd.isAutoIncrement(c));
                      recColumn[c-1] = new RecColumn(rsmd.getColumnName(c),rsmd.getColumnLabel(c),rsmd.getColumnType(c),rsmd.getTableName(c),rsmd.getColumnTypeName(c),rsmd.getColumnDisplaySize(c),0,rsmd.isAutoIncrement(c));
                      //cNew++;
                  
                  	//public RecColumn(String colDbNameIn,String colCaptionIn,int colDbTypeIn,String colDbTableIn,String colDbClassIn,int colDbLengthIn)
                  	//strColsCaption[c-1]=rsmd.getColumnLabel(c);//.getColumnName(c));
                  	//strColsName[c-1]=rsmd.getColumnName(c);
              }
              
              /*if(isPrimKeyColumn)
              {
                  columns=columns-1;
                 
              }*/

         
         // prepare for order by 
          
  	   XMLReader reader = new XMLReader();
   String[] tagElements ={"name"};
   String[] tagElementsType ={"String"};    	
    	
     if(reader.checkIfElementExists(fileTablePrefs, "Table",tagElements,tagElementsType,entity))
     {

       // load file for intsShowColumns ------------------------------------
          String[] tagElements1 ={"name","showColumns"};
          String[] tagElementsType1 ={"String", "String"};  
       int[]  fieldsShowColumnsNew = utilsPanelReport.loadDataFromXmlFileRetIntArray(fileTablePrefs, "Table",tagElements1,tagElementsType1,1,entity); 
            
     	// load file for intsOrderBy ------------------------------------
          String[] tagElements2 ={"name","intsOrderBy"};
          String[] tagElementsType2 ={"String", "String"};  
       int[]  fieldsOrderByNew = utilsPanelReport.loadDataFromXmlFileRetIntArray(fileTablePrefs, "Table",tagElements2,tagElementsType2,1,entity); 

        // load file for intsOrderByAsc  ----------------------------------------------
          String[] tagElements3 ={"name","boolsOrderByAsc"};
          String[] tagElementsType3 ={"String", "String"};  
       boolean[]  fieldsOrderByAscNew = utilsPanelReport.loadDataFromXmlFileRetBool(fileTablePrefs, "Table",tagElements3,tagElementsType3,1,entity); 
         // load file for intsOrderByAsc   ---------------------------------------
          String[] tagElements4 ={"name","boolsOrderByDesc"};
          String[] tagElementsType4 ={"String", "String"};  
       boolean[]  fieldsOrderByDescNew = utilsPanelReport.loadDataFromXmlFileRetBool(fileTablePrefs, "Table",tagElements4,tagElementsType4,1,entity); 
            
            //-----------------------------------------                       
          panelDataViewNOrder.setEntity(recColumn, fieldsShowColumnsNew, null, fieldsOrderByNew,fieldsOrderByAscNew,fieldsOrderByDescNew,1,TYPE_OF_VIEWANDORDER_TABLE,primKeyDb);
     	fieldsOrderby=fieldsOrderByNew;//if there is nothing selected in initially in entitydata
     }
     else
     {
     	//System.out.println("DialogDataConfic.setEntity get prefs from app for "+entity);
     	//get prefs from app
     	
     	  panelDataViewNOrder.setEntity(recColumn, null, null, fieldsOrderby,null,null,1,TYPE_OF_VIEWANDORDER_TABLE,primKeyDb);
     }           
          
      
   
          	//     public void setEntity( RecColumn[] recColumnIn, int[] isFieldSelected,boolean[] fieldsEditable, int[] fieldsOrderby)
              

      }// try
      catch ( SQLException sqlex)
      {  
         System.out.println("error:PanelReportSettings.setEntity: "+sqlex.getMessage());
      }
       
          
          
        db.releaseConnectionRsmd();
          // ------------------------------- misc settings ---------------------------
  
       panelViewNOrder.add(panelDataViewNOrder.getPanelView(), BorderLayout.LINE_START);
       panelViewNOrder.add(panelDataViewNOrder.getPanelOrder(), BorderLayout.LINE_END);   
       
       
       if(entityFilterSettingsIn !=null && entityFilterSettingsIn.length>0)
       {
       	  
//          panelFilterSettings.add(panelDataFilterSettings.getPanelFilterSettings());	
       }
       else
       {
//       	  tabbedPane.remove(1);
       	  
       }       
  
  //--------------------------------------------------------------------------------
         pack();
    }
    
    private int[] getShowColumns()
    {
        panelDataViewNOrder.calculateSubquery("");
       
        
    	return	panelDataViewNOrder.getShowColumns();
    }
    
    
    private String getSqlOrderBy()
    {
    	String ret = "";
    
         ret = panelDataViewNOrder.getSubquery("");
    //System.out.println("DialogDataTableConfig.getSqlOrderBy "+ret);
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

     public void showDialog()
     {
     	locateOnCenterOfTheScreen();
        this.pack();
        this.setVisible(true);     
        //this.showDialog();
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
    
    // // first create a file in DialogMain.main, also below in composeAndGetPrefsXML
    private void saveConfig()
    {
    	//utilsFileSystem.writeFile(composeAndGetPrefsXML(),fileTablePrefs);
    	
   XMLReader reader = new XMLReader();
   
   String[] tagElements ={"name"};
   String[] tagElementsType ={"String"};    	
    	
     if(reader.checkIfElementExists(fileTablePrefs, "Table",tagElements,tagElementsType,entity))
     {
     	//System.out.println("DialogDataTableConfig.saveConfig replace for "+entity);
     	// replace
     	utilsFileSystem.replaceElementInXMLFile(fileTablePrefs,"Table",entity,composeAndGetPrefsXML(),tagElements,tagElementsType);
     }
     else
     {
     	//System.out.println("DialogDataTableConfig.saveConfig add for "+entity+"  fileTablePrefs:"+fileTablePrefs);
     	utilsFileSystem.addToXMLFile(composeAndGetPrefsXML(),fileTablePrefs,"</TablePrefs>");// also in DialoGmain.composeAndGetPrefsXML
     	//add
     }

    }
    

  private String composeAndGetPrefsXML()
  {	
  

  	String ret = "";

        Writer writer = new java.io.StringWriter();
        XmlWriter xmlwriter = new XmlWriter(writer);
    
    //xmlwriter.writeEntity("TablePrefs"); 
    //xmlwriter.changeLine(); 
    //xmlwriter.writeEntity("property");   
    //for(int l =0;l<tableReportFields.getModel().getRowCount();l++)
    //{  
            xmlwriter.writeEntity("Table");
       

        	xmlwriter.writeEntity("name").writeText(entity);
        	xmlwriter.endEntity();
        	

        	

        	
        	String allIntsShowColumns = "";
               System.out.println("DialogDataTableConfig.composeAndGetPrefsXML "+getShowColumns());
            int[] fieldsShowColumns = getShowColumns();
               for(int f= 0 ; f<fieldsShowColumns.length;f++)
               {
            	   allIntsShowColumns=allIntsShowColumns+fieldsShowColumns[f]+",";	
               }                	
         	xmlwriter.writeEntity("showColumns").writeText(allIntsShowColumns);
        	xmlwriter.endEntity();       	
        	
        	
        	xmlwriter.writeEntity("sqlOrderBy").writeText(getSqlOrderBy());
        	xmlwriter.endEntity();
        	
        	String allIntsOrderBy = "";
             fieldsOrderby = getFieldsOrderby();
               for(int f= 0 ; f<fieldsOrderby.length;f++)
               {
            	   allIntsOrderBy=allIntsOrderBy+fieldsOrderby[f]+",";	
               }                   	
        	
        	xmlwriter.writeEntity("intsOrderBy").writeText(allIntsOrderBy);
        	xmlwriter.endEntity();
      	

        	String allBoolsOrderByAsc = "";
           boolean[]  fieldsOrderbyAsc = getFieldsOrderbyAsc();
               for(int f= 0 ; f<fieldsOrderbyAsc.length;f++)
               {
            	   allBoolsOrderByAsc=allBoolsOrderByAsc+fieldsOrderbyAsc[f]+",";	
               }                   	
        	
        	xmlwriter.writeEntity("boolsOrderByAsc").writeText(allBoolsOrderByAsc);
        	xmlwriter.endEntity();                   
                   
 
         	String allBoolsOrderByDesc = "";
           boolean[]  fieldsOrderbyDesc = getFieldsOrderbyDesc();
               for(int f= 0 ; f<fieldsOrderbyDesc.length;f++)
               {
            	   allBoolsOrderByDesc=allBoolsOrderByDesc+fieldsOrderbyDesc[f]+",";	
               }                   	
        	
        	xmlwriter.writeEntity("boolsOrderByDesc").writeText(allBoolsOrderByDesc);
        	xmlwriter.endEntity();                    
                   
    
         xmlwriter.endEntity(); // page
         xmlwriter.changeLine(); 
    //}

     //xmlwriter.endEntity(); // props 
     //xmlwriter.close();
  	 
  	 ret = writer.toString();
  	
  	return ret;         	
    }


    private void closeWindow()
    {
       dispose();
    }
    
    private void checkNone()
    {
    	for(int i=0; i<fieldView.size();i++ )
    	{    
    	   chkView =(JCheckBox)fieldView.get(i);
    	   chkView.setSelected(false);
    	}
    }

    private void checkAll()
    {
    	for(int i=0; i< fieldView.size();i++ )
    	{    
    	    chkView =(JCheckBox)fieldView.get(i);
    	    chkView.setSelected(true); 
    	}
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
    
   /* class  ActionTabForward extends AbstractAction                 
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
    }    */             


}