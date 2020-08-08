package com.tool.gui;

import com.tool.model.LookUpMgt;
import com.tool.model.LookupTableConstantsMgt;
import com.tool.model.EntityGroupOfComps;
import com.tool.model.EntityMessage;
import com.tool.model.EntityGroupOfPanels;
import com.tool.model.EntityPanel;
import com.tool.jdbc.*;
import com.tool.guicomps.*;
import static com.tool.guicomps.Constants.FIELD_VISIBLE_NOT_EDITABLE_ALWAYS;
import com.tool.guicomps.JPanelDecorated;
import com.tool.guicomps.JTextBoxWithEditButtons;
import com.tool.guicomps.JxPanel;
import com.tool.guicomps.PanelHtmlBrowser;
//import com.tool.guicomps.MediatorPanelTwoDataOneRec;
import com.tool.guicomps.PanelManagement;
//import com.tool.guicomps.PanelReportSettingsAndPreview;
import com.tool.guicomps.PlainDocumentInsertText;
import com.tool.guicomps.WindowLookUp;
import com.tool.utils.*;
import com.tool.model.*;

import java.util.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.awt.image.*;
import javax.imageio.*;


import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.*;

import javax.swing.text.*;
import java.text.*;

import java.sql.*;
import java.lang.reflect.Field;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.concurrent.TimeUnit;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

 public class PanelOneDataOneRecData extends JxPanel implements Constants
 {
    private ResultSet rs;
    
    private Database db;
    private DatabaseTableMeta databaseTableMeta;

    private JPanel eachDataFieldPanel;
    private JxPanelCollapsable dataPanel;
    private JPanelDecorated panelAllOnIt;

    private boolean guiLoaded = false;
    private ArrayList fieldTxts = new ArrayList(); // to hold tb the key
    private ArrayList fieldTxts2 = new ArrayList(); // to hold tb2    
    private ArrayList fieldTxts3 = new ArrayList(); // to hold tb3 
    private ArrayList fieldTxts4 = new ArrayList(); // to hold tb4
    private ArrayList listButtonValid = new ArrayList();
    private ArrayList listPanelGroups= new ArrayList();
  
    
    private ArrayList fieldTxtsKeyChanged = new ArrayList(); // to hold tb the key
    //private ArrayList listPanelCollapsable;
    //private ArrayList fieldTxtsDate = new ArrayList(); // to hold textFormatedDate  
    //private PanelOneDataOneRec panelOneDataOneRec;
    private LookUpMgt lookUp;
    private String entity;
    private String title;
    private String primKey;
    private String primKeyDb;
    private boolean isNewRec;
    private boolean isNewRecFromCopy;
    //private String[] sql2WhereField;
    //private String[] sql2WhereValue;
    private String query;
    private String[]fields;
    private String[]fieldsTranslation;    
    private JTextField tb;
     private JTextField tb2;
     private JTextField tb3;
     private JTextField tb4;
     private JTextArea textArea;
     private boolean hasDataChanged=false;
     private int selectedRow;
//     private boolean isMany;
     // must be public so the getValueForVariable to work
    public String primKeyValue;
    //public String globalYear;   
    public String globalDeliveryId;
    public String globalCompanyId; 
    //private String dbEngine = "derby";
    
    //private MediatorPanelTwoDataOneRec med;
    private UtilsGui utilsGui;
    private UtilsDate utilsDate;
    private UtilsDouble utilsDouble;
    //private UtilsDouble utilsDouble;
    private UtilsPanelReport utilsPanelReport;
    
    //JxFormattedTextFieldDate.FormatSpec formatSpec;
    private JFrame frame;
    //private String yearEnforce;
    //int intDateFormatCount=-1;// starts from 0 , incremented later if >1
    private boolean showDialogOnError=true;
    private ImageIcon ico;
    //private int[] fieldsOnTitle;
    //private String[] fieldsOnTitleCaption;
 	private JTextBoxWithEditButtons txtLookUpBtn;
    private  JTextBoxWithEditButtons txtLookUpBtn2;
    private  JTextBoxWithEditButtons txtLookUpBtn3;
    private int[] groupOfComps;
    private EntityDBFields[]dbFieldsInGroupOfPanels;
    private EntityGroupOfComps[] entityGroupOfComps;
    private EntityGroupOfPanels[] entityGroupOfPanels;
    private String []colClassName;
    private int []colWidth;
    private int []primaryKeyIntegerAutoInc;
    
    private int[] fieldObligatoryOrSuggest;
    private int[] fieldValidation ;
    private String[] defaultValue; // if already exists string into field do not complete
    private int[] isVisibleOrEditable;
    //private JLabel lblIcoAttention ;// = new JLabel(ICO_FIELDATTENTION);
	private UtilsString  utilsString;
    private WindowLookUp winLookUp;
    
    private boolean isFirstFieldAutoInc = false;
//    private boolean isMasterUnique;
    private String query2;
    private String entity2;
    private boolean keyChanged=false;
    private int countOfUniqueKeys=0;// like farmerid and like farmerid and productid
    private ArrayList listUniqueKeysIntOfCols; // the ints of columns
    private int intGroupOfPanelsToShow;
    private EntityDBFields[] dbFieldsAll;
    private PanelManagement panelManagement;
    private EntityPanel entityPanel;
    private ArrayList listLengthDbFieldsInGroupOfPanels;
    private int intOfListLengthDbFieldsInGroupOfPanels;
    //private PanelEditOneDataRec  panelEditOneDataRec;
    
    private PanelOneDataManyRecData panelOneDataManyRecData;
    
    private final int COLWIDTH_FOR_BIGTEXTBOX = 101;
    private final int COLWIDTH_FOR_HTML = 499;
    private EntityUpdateAdditional[] updateAdditional;
    
    private String filePrefsUniqueFields;
    private boolean dataHasChangedInAField = false;
//    private EntityReport entityReport;
 private String filePath=null;
  private String imageEmpty = "< κενό >";   
  //private String formGlobalTableToGet1;
  //private String formGlobalTableToApply1;
  String periodiki = "";// file:///C:/mydocuments/!projects/BusinessEye/reports/periodikifpaf2.htm"; 
  
  private ArrayList listHtmlFieldsAndValues =null;
  private ArrayList listFields = new ArrayList();
  
  
             private  String strReadFieldWhere = "";
             private String strReadFieldValue = "";
             
             
             private String qIsTemplateToBeReplaced="";
       private String qIsTemplateToReplace="";
       
       private int firstFieldThatIsEditable=0;
       private String  pkFromOnePanelForTables="";
       private boolean boolIsFromCopyOfRecord = false;
       private EntityCheckFields[] entityCheckFields;
       private EntityTemplate entityTemplate;
  //String path = VariablesGlobal.globalDirConfiguration;
   // private String formGlobalField1; // the db field
   // private String formGlobalVariable1; // the variable
 
    public PanelOneDataOneRecData(JFrame frame)
    {
           try
           {
               
             /*MediatorPanelTwoDataOneRec mediator; 
       	     mediator = new MediatorPanelTwoDataOneRec();
       	     
       	     PanelOneDataManyRecData = new PanelOneDataManyRecData(mediator,frame);                 
               */
               initialize(frame); 
           }
           catch (Exception e)
           {   e.printStackTrace();    }
    }
    
    // for mediator pattern
    /*public PanelOneDataOneRecData(MediatorPanelTwoDataOneRec md,JFrame frame)
    {
            try
           {
           	     med = md;
                 med.registerPanelOneDataOneRecData(this);
           	     initialize(frame);
           }
           catch (Exception e)
           {   e.printStackTrace();    }
    }*/

	private void initialize(JFrame frameIn) throws Exception
    {
    	setOpaque(false);
        // file:///C:/mydocuments/!projects/BusinessEye/reports/periodikifpaf2.htm"; 
     periodiki = "file:///"+VariablesGlobal.globalDirConfiguration+"/reports/periodikifpaf2.htm";

    	  //    Color blue= new Color(129, 169, 226);
          //    Color lightBlue= new Color(196,218,246);//220,235,253);//148, 215, 254);
        panelAllOnIt = new JPanelDecorated();//(blue,lightBlue,0,0);
       
        //panelAllOnIt.setOpaque(false) ;
        panelAllOnIt.setLayout(new BorderLayout());
        	
 	    frame=frameIn;
 	    databaseTableMeta = new DatabaseTableMeta();
 	    utilsPanelReport= new UtilsPanelReport();
 	    db= new Database();
 	    lookUp= new LookUpMgt();
 	    utilsGui = new UtilsGui();
 	    utilsDate = new UtilsDate();
 	    utilsDouble = new UtilsDouble();
            
            if(VariablesGlobal.globalShowInitialisations)
            {
            System.out.println("PanelODORData init");
            }
            
            utilsDouble = VariablesGlobal.globalUtilsDouble;
 	    utilsString = new UtilsString();

        winLookUp = new WindowLookUp(frame);

        
        listHtmlFieldsAndValues = new ArrayList();
        
        listLengthDbFieldsInGroupOfPanels = new ArrayList();
 	intOfListLengthDbFieldsInGroupOfPanels = 0;
 	  //  dataPanel = new JPanel(false); //panel that contains all components
   	  //  dataPanel.setLayout(new FlowLayout()); //public FlowLayout(int alignment,int horizontalGap,int verticalGap)
        //dataPanel.setBackground(Color.BLUE);

//        eachRecPanel = new JPanel(false); // for each field
	    //eachRecPanel.setLayout(new GridLayout(0,4,1,1)); 
//        eachRecPanel.setLayout(new GridBagLayout()); 
       // eachRecPanel.setBackground(Color.gray);
       if (VariablesGlobal.globalShowInitialisations)
       {   System.out.println("PanelODORData initialized");  }
       
       
         //following  String exists in PanelODORData.initialize and DialogDataEditConfig.initialize
       filePrefsUniqueFields =VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+FILE_DATAEDIT_PREFERENCES;   
       
   }
   
   public void closeDB()
   {
      // closeDBRS();
   	  db.releaseConnectionRs();
          db.releaseConnectionRsmd();
          //System.out.println("PanelODORData.closeDB");
   }
   

           

   /*
    * 
    *
    * called by  PanelOneDataOneRec.loadAndGetEntityPanelsOneDataOnRecData
    */

   public void setEntity(EntityPanel entityPanelIn, String titleIn, String entityIn,EntityDBFields[] dbFieldsIn, 
           EntityGroupOfComps[] entityGroupOfCompsIn, EntityGroupOfPanels[] entityGroupOfPanelsIn, String primKeyIn, String primKeyValueIn,String primKeyDbIn,
           /*String formGlobalTableToGet1In, String formGlobalTableToApply1In,*/
           String queryIn, boolean isNewRecIn, boolean isNewRecFromCopyIn, /*boolean isMasterUniqueIn,*/ /*String[] sql2WhereFieldIn, String[] sql2WhereValueIn, String query2In,String entity2In, boolean isManyIn, */ 
           boolean isEditable,String yearEnforceIn,ImageIcon icoIn,ArrayList listLengthDbFieldsInGroupOfPanelsIn, int intGroupOfPanelsToShowIn,
           PanelManagement panelManagementIn)
   {
        
       //----------------------------------------------
               strReadFieldWhere = "fieldName";
               strReadFieldValue = "fieldValue";
        //---------------------------------------------
       
        entityPanel= entityPanelIn;
        entityCheckFields = entityPanel.getEntityCheckFields();
        isNewRec=isNewRecIn;
        isNewRecFromCopy=isNewRecFromCopyIn;
        title=titleIn;
        entity = entityIn;
        primKey=primKeyIn;// the translated
        primKeyDb=primKeyDbIn;// the one in db
        //formGlobalTableToGet1=formGlobalTableToGet1In;
        //formGlobalTableToApply1=formGlobalTableToApply1In;
        //formGlobalField1 =formGlobalField1In;
        primKeyValue=primKeyValueIn;  
        dbFieldsAll = dbFieldsIn;
//        isMasterUnique=isMasterUniqueIn;
//        query2=query2In;
//        entity2=entity2In;
        entityGroupOfComps=entityGroupOfCompsIn;
        intGroupOfPanelsToShow=intGroupOfPanelsToShowIn;
        panelManagement= panelManagementIn;
        //yearEnforce = entityPanel.getyearEnforceInLines();
        fields = new String[dbFieldsAll.length];
        fieldsTranslation = new String[dbFieldsAll.length];
        groupOfComps = new int[dbFieldsAll.length];
        colClassName = new String[dbFieldsAll.length];
        colWidth = new int[dbFieldsAll.length];
        primaryKeyIntegerAutoInc = new int[dbFieldsAll.length];
        fieldObligatoryOrSuggest = new int[dbFieldsAll.length];
        fieldValidation = new int[dbFieldsAll.length];
        isVisibleOrEditable = new int[dbFieldsAll.length];
        defaultValue =  new String[dbFieldsAll.length];
        listLengthDbFieldsInGroupOfPanels=listLengthDbFieldsInGroupOfPanelsIn;
        
        entityGroupOfPanels = entityGroupOfPanelsIn;
        
        updateAdditional=entityPanel.getUpdateAdditional();
        entityTemplate = entityPanel.getEntityTemplate();

        //query=entityPanel.getQuery();
        query=queryIn;// or sqlOne

        
        //yearEnforce=VariablesGlobal.globalYear;
        ico=icoIn;
        
       //if(VariablesGlobal.globalShowReadSQLRow)
       //{
        System.out.println("PanelODORData.setEntity      ----------o----------      queryIn:"+queryIn+"   or    entityPanel.getQuery():"+entityPanel.getQuery());
       //}
        int intfields=0;
          if(entityGroupOfCompsIn!=null)  
          {       
                for(int egc = 0; egc<entityGroupOfCompsIn.length; egc++)
                {
                     for(int f=0;f<dbFieldsAll.length;f++)
                     { 
                         if( entityGroupOfCompsIn[egc]!= null && intGroupOfPanelsToShow == entityGroupOfCompsIn[egc].getIncludedInGroupOfPanels())
                         {
                             if(dbFieldsAll[f].getGroupOfComps() == egc)
                             {
             //System.out.println("PanelODOR.setEntity   dbFieldsIn.length:"+dbFieldsIn.length+"  "+f+"  "+dbFieldsIn[f].getDbField()+"   "+entityGroupOfCompsIn[egc].getCaption()+"   "+entityGroupOfCompsIn[egc].getIncludedInGroupOfPanels()+"    "+intGroupOfPanelsToShow);                                          
                                 intfields++;
                             }
                                 
                         }
                     }
                }        
          }
          else
          {
            
                intfields=dbFieldsAll.length;
          }
          
           dbFieldsInGroupOfPanels = new EntityDBFields[intfields];
//System.out.println("");
//           System.out.println("PanelODORData.setEntity   A->>>>     entity:"+entity+"    dbFieldsAll.length:"+dbFieldsAll.length+"      dbFieldsInGroupOfPanels.length:"+dbFieldsInGroupOfPanels.length+"   intGroupOfPanelsToShowIn:"+intGroupOfPanelsToShowIn+"   intfields:"+intfields);           
//System.out.println("");
int flds = 0;
          if(entityGroupOfCompsIn!=null)  
          {
        	for(int egc = 0; egc<entityGroupOfCompsIn.length; egc++)
                {
                     for(int f=0;f<dbFieldsAll.length;f++)
                     { 
                         if(entityGroupOfCompsIn[egc]!= null && intGroupOfPanelsToShow == entityGroupOfCompsIn[egc].getIncludedInGroupOfPanels())
                         {
                             if(dbFieldsIn[f].getGroupOfComps() == egc)
                             {
                                 dbFieldsInGroupOfPanels[flds]=dbFieldsAll[f];
             //System.out.println("PanelODOR.setEntity ***  dbFieldsIn.length:"+dbFieldsIn.length+"  "+f+"  "+dbFieldsIn[f].getDbField()+"   "+entityGroupOfCompsIn[egc].getCaption()+"   "+entityGroupOfCompsIn[egc].getIncludedInGroupOfPanels()+"    "+intGroupOfPanelsToShow);                                          
                                 flds++;
                             }
                                 
                         }
                     }
                }
          }
          else   //if(entityGroupOfCompsIn==null)  
          {
              
               for(int f=0;f<dbFieldsAll.length;f++)
               {
                   dbFieldsInGroupOfPanels[f]=dbFieldsAll[f];
               }
          }
          
        for(int f=0;f<dbFieldsInGroupOfPanels.length;f++)
        {
          if(entityGroupOfCompsIn!=null)  
          {
             for(int egc = 0; egc<entityGroupOfCompsIn.length; egc++)
             {
               
               if(entityGroupOfCompsIn[egc]!= null && intGroupOfPanelsToShow == entityGroupOfCompsIn[egc].getIncludedInGroupOfPanels())
               {
                        if(dbFieldsInGroupOfPanels[f].getGroupOfComps() == egc)
                        {
      //System.out.println("PanelODORData.setEntity  --  ("+f+") "+dbFieldsInGroupOfPanels[f].getDbField()+"  "+entityGroupOfCompsIn[egc].getCaption()+"   "+entityGroupOfCompsIn[egc].getIncludedInGroupOfPanels()+"    "+intGroupOfPanelsToShow);                                          
                   //System.out.println("PanelODORData.setEntity f:"+f+" "+dbFieldsIn[f].getDbField()+"  f group :"+dbFieldsIn[f].getGroupOfComps()+" egc group :"+entityGroupOfCompsIn[egc].getIncludedInGroupOfPanels() +"  intGroupOfComps" + intGroupOfPanelsToShow );
                   
                fields[f] = dbFieldsInGroupOfPanels[f].getDbField();
        	fieldsTranslation[f] = dbFieldsInGroupOfPanels[f].getCaption();
        	groupOfComps[f] = dbFieldsInGroupOfPanels[f].getGroupOfComps();  
                colClassName[f] = dbFieldsInGroupOfPanels[f].getColClassName();
                colWidth[f] = dbFieldsInGroupOfPanels[f].getColWidth();
                //primaryKeyIntegerAutoInc[f] = dbFieldsInGroupOfPanels[f].getPrimaryKeyIntegerAutoInc();
                fieldObligatoryOrSuggest[f] = dbFieldsInGroupOfPanels[f].getFieldObligatoryOrSuggest();     	
                fieldValidation[f]=dbFieldsInGroupOfPanels[f].getValidation();
                isVisibleOrEditable[f]=dbFieldsInGroupOfPanels[f].getIsVisibleOrEditable();
                defaultValue[f] =  dbFieldsInGroupOfPanels[f].getDefaultValue();
                primaryKeyIntegerAutoInc[f] = dbFieldsInGroupOfPanels[f].getPrimaryKeyIntegerAutoInc();
                listButtonValid.add(new JButton("l"));// add so when set or get have the same length              
                      }// if 
               
               //groupOfComps[f].
               
                     //  .getCompsFontSize(); 
               } // if
               else
               {
           //System.out.println("PanelODORData.setEntity  --oo--  ("+f+") "+dbFieldsInGroupOfPanels[f].getDbField()+"  "+entityGroupOfCompsIn[egc].getCaption()+"   "+entityGroupOfCompsIn[egc].getIncludedInGroupOfPanels()+"    "+intGroupOfPanelsToShow);                                                       
               }
               

               
               
               
            } //for egc    
          }
          else   // entityGroupOfCompsIn==null
          {
                   
                //System.out.println("PanelODORData.setEntity   ++   ("+f+") "+dbFieldsInGroupOfPanels[f].getDbField());
                fields[f] = dbFieldsInGroupOfPanels[f].getDbField();
        	fieldsTranslation[f] = dbFieldsInGroupOfPanels[f].getCaption();
        	groupOfComps[f] = dbFieldsInGroupOfPanels[f].getGroupOfComps();  
                colClassName[f] = dbFieldsInGroupOfPanels[f].getColClassName();
                colWidth[f] = dbFieldsInGroupOfPanels[f].getColWidth();
                fieldObligatoryOrSuggest[f] = dbFieldsInGroupOfPanels[f].getFieldObligatoryOrSuggest();     	
                fieldValidation[f]=dbFieldsInGroupOfPanels[f].getValidation();
                isVisibleOrEditable[f]=dbFieldsInGroupOfPanels[f].getIsVisibleOrEditable();
                defaultValue[f] =  dbFieldsInGroupOfPanels[f].getDefaultValue();
                primaryKeyIntegerAutoInc[f] = dbFieldsInGroupOfPanels[f].getPrimaryKeyIntegerAutoInc();
                listButtonValid.add(new JButton("l"));// add so when set or get have the same length              
          }
          
          
          
          //System.out.println("PanelODORData.setEntity         f:("+f+") "+fields[f]+"    dbFieldsInGroupOfPanels[f].getColClassName():"+dbFieldsInGroupOfPanels[f].getColClassName()+"   fieldTxts.size"+fieldTxts.size()+"    dbFieldsInGroupOfPanels.length:"+dbFieldsInGroupOfPanels.length+"    dbFieldsAll.length:"+dbFieldsAll.length);   
          
        }

        this.setLayout(new BorderLayout());
        this.add(panelAllOnIt, BorderLayout.CENTER);        
        

        boolean isFirstFieldAutoInc=false;
        //fieldsOnTitle=fieldsOnTitleIn;
  
        
        
       if (panelAllOnIt != null)
       { panelAllOnIt.removeAll(); } //erases all components placed during initialization
       

                        
        //this.setLayout(new BorderLayout());//new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        // listPanelGroups= new ArrayList();
        listPanelGroups.clear();
        if(entityGroupOfComps ==null)
        {
        	  dataPanel = new JxPanelCollapsable(); //panel that contains all components
        	  dataPanel.setEntityCollapsable(entityPanel.getTitle(), true,CLR_PANEL_END,CLR_PANEL_START,4,SHOW_PANEL_COLLAPSE_NOT,SHOW_BORDER_BTN_ONLY_ONE,entityPanel.getTitle());//CLR_PANEL_START,CLR_PANEL_END);
   	          
                  dataPanel.setOpaque(false);
   	          GridLayoutVariable layout = new GridLayoutVariable (GridLayoutVariable.FIXED_NUM_COLUMNS, 4);
   	          dataPanel.setLayout(layout); //public FlowLayout(int alignment,int horizontalGap,int verticalGap)
                  
               

        }
        else
        {
           
            JxPanel panelHolder = new JxPanel();
            
             
            
            panelHolder.setOpaque(false);
        	GridLayoutVariable layout1 = new GridLayoutVariable (GridLayoutVariable.FIXED_NUM_COLUMNS, 1);
        	//GridLayout layout1 = new GridLayout(0,1);
        	panelHolder.setLayout(layout1);
        	
        	 for(int gc=0;gc<entityGroupOfComps.length;gc++)
        	 {
        	 	 //System.out.println("PanelODORData.setEntity fields "+gc+" "+fields.length+" "+groupOfComps.length+" "+entityGroupOfComps[gc].getColumnsOfObjects());

                 JxPanelCollapsable panelGroup = new JxPanelCollapsable();
                  panelGroup.setEntityCollapsable(entityGroupOfComps[gc].getCaption(), true,CLR_PANEL_END,CLR_PANEL_START,entityGroupOfComps[gc].getColumnsOfObjects(),SHOW_PANEL_COLLAPSE_NOT,SHOW_BORDER_BTN_ONLY_ONE,entityGroupOfComps[gc].getCaption());//CLR_PANEL_START,CLR_PANEL_END);
          	 	
           // 	   panelGroup.setOpaque(false);
           GridLayoutVariable layoutGroup = null;
                layoutGroup = new GridLayoutVariable (GridLayoutVariable.FIXED_NUM_COLUMNS, entityGroupOfComps[gc].getColumnsOfObjects());

        	 	 
        	 	 panelGroup.setLayout(layoutGroup);
                         
     
               int pnlvisible = entityGroupOfComps[gc].getPanelVisible();
               if(pnlvisible==GROUP_OF_PANEL_NOT_VISIBLE)
               {
                  panelGroup.setVisible(false);
                  panelGroup.setPreferredSize(new Dimension(0,0));

               }
               else if(pnlvisible==GROUP_OF_PANEL_VISIBLE)
               {
                   panelGroup.setVisible(true);
                   
               }
               else
               {
                   System.out.println("  PanelODORData .setEntity UNDEFINED  pnlvisible:"+pnlvisible);
               }
                                   int columnCompsFontSize= 8;
                 
                 columnCompsFontSize =  entityGroupOfComps [ gc].getCompsFontSize();
                
                 
                 
                  if(columnCompsFontSize == FONT_SIZE_NOT_SET)
                  {
                       panelGroup.setOpaque(false);
                       panelGroup.setLayout(layoutGroup);
                       panelGroup.setHeadTitleVisible(true);
          
                  }
                  else
                  {
                   panelGroup.setOpaque(true);
                   panelGroup.setBackground(Color.WHITE);
                   FlowLayout groupFlowLayout = new FlowLayout();
                   groupFlowLayout.setVgap(1);
                   panelGroup.setLayout(groupFlowLayout);
                   panelGroup.setHeadTitleVisible(false);
                  // panelGroup.setPreferredSize(new Dimension(100, 120));// width, height
                   //panelGroup.setMaximumSize(new Dimension(100, 120));// width, height
                   //Border borderPanel = BorderFactory.createLineBorder(Color.WHITE);//.createLineBorder(CLR_PANEL_BORDER, 1);
                   panelGroup.setBorder(null);
                         
                  }
                  
               
        	 	listPanelGroups.add(panelGroup);   
	        	 	 
        	 	 if(entityGroupOfCompsIn[gc]!= null && entityGroupOfComps[gc].getIncludedInGroupOfPanels()== intGroupOfPanelsToShow || intGroupOfPanelsToShow==-1)
        	 	 {
         	        //panelGroup.setVisible(true);

                                   // panelHolder.add(pcl);
        
                                  
                      
                           // System.out.println("     gc"+gc+"  ");
         	        panelHolder.add(panelGroup);//keep
             
                         }
        	 	 else
        	 	 {
                             // System.out.println("..     gc"+gc+"  ");

                     //panelGroup.setVisible(false);
        	 	 }

        	 }
        	 
        	panelAllOnIt.add(panelHolder, BorderLayout.PAGE_START);
        }

        if (dataPanel != null)
        { dataPanel.removeAll(); } //erases all components placed during initialization
          
          utilsDate.readFromFileDateFormats();
//          utilsDouble.getSettingsFromDb();
        JxPanelCollapsable pnlIncludeJTableAsInsideBorderLayoutSizable = new JxPanelCollapsable();
       
        
        
        
        pnlIncludeJTableAsInsideBorderLayoutSizable.setLayout(new BorderLayout());  // must be because it has panel collapsable and the table
        //formatSpec = new JxFormattedTextFieldDate.FormatSpec("__-__-____", "**-**-****");
        //formatSpec = new JxFormattedTextFieldDate.FormatSpec();
          
        //panelOneDataOneRec = new PanelOneDataOneRec(frame);
   	    //dataPanel.setBackground(Color.gray);
   	    //dataPanel.setLayout(new BoxLayout(dataPanel,  BoxLayout.LINE_AXIS));
        fieldTxts.clear();
        fieldTxts2.clear();
        fieldTxts3.clear();
        fieldTxts4.clear();
        fieldTxtsKeyChanged.clear();
        //fieldTxtsDate.clear();
        //int tb2No=0;
        //intDateFormatCount = -1;// starts from 0, incremented later if >1
        
       
        
//    try
 //   {
// if there is no problem comment them        
//----      databaseTableMeta.retrievePrimKs(entity);
//----      databaseTableMeta.retrieveImpKsOnQuery(entity,query); //first retrieve them then find for each column the foreign table name
      
      
        if (query == null) // this should be after the retrieveImpKsOnQuery, so it will not cause more process consumption
        {
        	        query = "SELECT * FROM " + entity;
        	        System.out.println("panelODORData.setEntity [error perhaps] (query == null) query:"+query);
        }
       
        
        query=utilsString.removeCaptionsFromQuerySubStringSelect(query);
        if (VariablesGlobal.globalShowSQL)
        {    
          System.out.println("panelODORData.setEntity query: "+query); 
        }
       //System.out.println("panelODORData.setEntity  ooo   query: "+query); 
   	
       if(isNewRec && !isNewRecFromCopy)
       {
           selectedRow=0;
       }
       else
       {
           db.retrieveDBDataFromQuery(query,"PanelOneDataOnRecData.setEntity");
           rs=db.getRS();
           selectedRow=0;
           selectedRow=utilsPanelReport.getRowForPrimKey("PanelOneDataOnRecData.setEntity",query,rs,dbFieldsAll,primKeyDb,primKeyValue);
           closeDB();
       }
        GridBagConstraints gc = new GridBagConstraints();

/*       if(selectedRow != 0) 
       {
          rs.absolute(selectedRow); 
       }
       else //(selectedRow == 0 && db.getRecordCount()!=0)//  which means no line selected
       {
          selectedRow = 1; // 1st row if none selected
          rs.absolute(selectedRow); 
          
          System.out.println(" -  panelOneDataOneRecData.setEntity() no row selected set 1");
       }
*/       
        /*if(dbFieldsIn.length!=rsmd.getColumnCount())
        {
           System.out.println("  Info  PanelOneDataOneRecData.setEntity dbFieldsInGroupOfPanels"+dbFieldsIn.length+" rsmd"+rsmd.getColumnCount());   
        }*/
         
           int a = 0;
           int b = 0;
           int c = 1;
           boolean isTheOnlyGroupPanelAfterThePanelJTableSizable=false;
              for (int i = 0; i < dbFieldsInGroupOfPanels.length; i++)//  i = fieldTxts
              {
                // System.out.println("PanelODORData.setEntity for  =  ("+i+") "+fields[i]);
                 
                 String columnLabel = fieldsTranslation[i]; //get colunm name
                 String columnDbName = fields[i];
                 //System.out.println("panelODORData.setEntity col:");
                 int columnCompsFontSize= 8;
                 columnCompsFontSize =  entityGroupOfComps [ dbFieldsInGroupOfPanels[i].getGroupOfComps()].getCompsFontSize();
                        
                 int columnWidth = colWidth[i];//get column width
                 //columnWidth=columnWidth+2;
                 
                 String columnClass = colClassName[i]; 
                 int intPKAutoInc = primaryKeyIntegerAutoInc[i];// 
                 
                 int columnFieldObligatoryOrSuggest=fieldObligatoryOrSuggest[i];     	
                 int columnFieldValidation =  fieldValidation[i];
                 
   	           if(dbFieldsIn[i].getPrimaryKeyIntegerAutoInc() == FIELD_PRIMARY_KEY_AUTOINC)
   	           {
   	         	  isFirstFieldAutoInc=true;
   	           }                 

                 
                 
                 if(columnClass.equalsIgnoreCase("table"))
                 {
                     fieldTxts.add(new PanelOneDataManyRecData(frame));//PanelOneDataManyRecData. When it is an editable table like many
                    //  System.out.println("-PanelODORData.setEntity for    table     ("+i+") "+fields[i]+"    "+columnClass);
                     
                 }
                 else if(columnClass.equalsIgnoreCase("htmlfile"))
                 {
                     PanelHtmlBrowser panelHtmlBrowser = new PanelHtmlBrowser();
                     panelHtmlBrowser.loadURL(periodiki);
                     panelHtmlBrowser.setVisible(true);
                     fieldTxts.add(panelHtmlBrowser);
                 }
                 else if( columnClass.equalsIgnoreCase("java.awt.image.BufferedImage"))
                 {
                     fieldTxts.add(new JLabel());
                 }
                 else if( columnClass.equalsIgnoreCase("java.lang.String") || columnClass.equalsIgnoreCase("java.lang.Integer") || columnClass.equalsIgnoreCase("java.lang.Double"))
                 {
                     
                    if(dbFieldsInGroupOfPanels[i].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)
                    {  
                        fieldTxts.add(new JComboBox());           
                    }
                    else
                    {
                       fieldTxts.add(new JTextField());// so fieldTxts length = fieldTxts2 length
                    }
                 }   
                 else if (columnClass.equalsIgnoreCase("java.sql.Date") || columnClass.equalsIgnoreCase("java.lang.Date"))
                 {
                      fieldTxts.add(new JTextField());
                 }
                 else if( columnClass.equalsIgnoreCase("java.lang.Boolean")) 
                 {
                     fieldTxts.add(new JCheckBox());
                 }
                 else
                 {
                     //if()
                     fieldTxts.add(new JTextField());
                     System.out.println("PanelODORData.setEntity     NOT IDENTIFIED CLASS    for  =  ("+i+")  "+fields[i]+"    "+columnClass);
                 }
           //       System.out.println("PanelODORData.setEntity for  =  ("+i+") "+fields[i]+"    "+columnClass+"   fieldTxts.size"+fieldTxts.size()+"    dbFieldsInGroupOfPanels.length:"+dbFieldsInGroupOfPanels.length);   
                 
                 fieldTxts2.add(new JTextField());// so fieldTxts length = fieldTxts2 length
                 fieldTxts3.add(new JTextField());
                 fieldTxts4.add(new JTextField());
                 fieldTxtsKeyChanged.add(false);
                 
                 eachDataFieldPanel = new JPanel(false);
                 eachDataFieldPanel.setOpaque(false);
                //  eachDataFieldPanel.setBackground(Color.GREEN);
                 eachDataFieldPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2)); //;//public FlowLayout(int alignment,int horizontalGap,int verticalGap)           
                //changed in             else if(colWdth>40)
                
                // JLabel lbl = new JLabel(columnLabel+" ["+columnClass+"]"+" ("+columnWidth+"):", JLabel.RIGHT);
               JLabel lbl = null;
              tb=null;
              tb2=null;
              tb3=null;
              tb4=null;

             textArea=null;
             JScrollPane scrollPaneTextArea = null;
             // txtLookUpBtn = new JTextBoxWithEditButtons();
             // txtLookUpBtn2 = new JTextBoxWithEditButtons(); 
                      
             //System.out.println("panelOneDataOneRecData.setEntity "+dbFieldsIn[i].getLookupEntityName()+" "+dbFieldsIn[i].getDbField());
                                  
               String foreignTable = "";   //if foreign table = null assign
                //if ((databaseTableMeta.findForeignTable(columnDbName)==null) && (!entity.toUpperCase().equals(rsmd.getTableName(i+1).toString().toUpperCase())))
                if (dbFieldsInGroupOfPanels[i].getLookupType()== LOOKUPTYPE_NOLOOKUP && dbFieldsInGroupOfPanels[i].getLookupEntityName()==null)// && (!entity.toUpperCase().equalsIgnoreCase(dbFieldsIn[i].getLookupEntityName().toUpperCase())))
                {
                     foreignTable =entity; //rsmd.getTableName(i);
                }
                else
                {
                    foreignTable = dbFieldsInGroupOfPanels[i].getLookupEntityName();
                   //String ln = lookUp.getFromForeignTableTheName(entity);
                    // foreignTable = ln;//lookUp.getFromForeignTableTheName(lname);
                    /// foreignTable = dbFieldsInGroupOfPanels[i].getLookupEntityName(); // getFromForeignTableTheName
                }
                              
                // if has foreign key then calculate
                
             //   System.out.println("PanelODORData.setEntity  =  =  i:"+i+"   "+columnDbName+"       foreignTable:"+foreignTable+"   lookuptype:"+dbFieldsInGroupOfPanels[i].getLookupType()+"   lookupentityname:"+dbFieldsInGroupOfPanels[i].getLookupEntityName());
                //System.out.println("PanelODORData.setEntity "+i+" "+foreignTable+" "+databaseTableMeta.findForeignTable(columnLabel) +" "+ft+" "+entity);
                if (dbFieldsInGroupOfPanels[i].getLookupType()== LOOKUPTYPE_ONLYONE_THISFIELD && dbFieldsInGroupOfPanels[i].getLookupEntityName()!=null)//(foreignTable!= null) && (!ft.toUpperCase().equalsIgnoreCase(entity.toUpperCase())))//(!rsmd.getTableName(i).equalsIgnoreCase(entity)))
                {

                    /*ResultSetMetaData rsmdForeign;
                    String foreignMetaQuery = "SELECT * FROM "+foreignTable;
                   //System.out.println("panelODORData.setEntity  foreignTable:"+foreignTable+"="+dbFieldsInGroupOfPanels[i].getLookupEntityName()+"  foreignMetaQuery:"+foreignMetaQuery);
                   */
                    
                     //int luFieldIdx = lookUp.getLookUpFieldIndex(foreignTable);                  
                    int luFieldIdx = lookUp.getLookUpFieldIndex(foreignTable);
                    int foreignColumnWidth = lookUp.getLookUpFieldLength(foreignTable);//16; //rsmdForeign.getColumnDisplaySize(luFieldIdx);//get column width
                    String foreignColumnType = lookUp.getLookUpFieldType(foreignTable);//rsmdForeign.getColumnClassName(luFieldIdx);
                    //System.out.println("PanelOneDataOnRecData.setEntity  --  foreignTable:   '"+foreignTable+"'      foreignColumnWidth:"+foreignColumnWidth);
                    
                    
                    //int colWdth=20;
                //    int colWdthForeign=17;
                   if(columnWidth<20)
                    {
                    	columnWidth = columnWidth - (columnWidth/5);
                    }
                    else
                    {
                    	columnWidth = columnWidth - (columnWidth/4);
                    }
                    //System.out.println("PanelODORData.setEntity "+columnWidth+" "+colWdth);
                    
                    if(foreignColumnWidth<20)
                    {
                    	foreignColumnWidth = foreignColumnWidth - (foreignColumnWidth/5);
                    }                    
                    else
                    {
                    	foreignColumnWidth = foreignColumnWidth - (foreignColumnWidth/3);
                    }
                    //System.out.println("PanelODORData.setEntity "+foreignTable+" "+colWdth);



                    lbl = new JLabel(columnLabel+":("+KEYSTROKE_F_LOOKUP_SHOW+")", JLabel.RIGHT);
                    lbl.setIconTextGap(0);
                    
                    JLabel lbl2 = new JLabel(lookUp.getLookUpFieldLabel(foreignTable)+":("+KEYSTROKE_F_LOOKUP_SHOW+")", JLabel.RIGHT);
                    JLabel lbl3 = new JLabel(lookUp.getLookUpField2Label(foreignTable)+":", JLabel.RIGHT);
                    JLabel lbl4 = new JLabel(lookUp.getLookUpField3Label(foreignTable)+":", JLabel.RIGHT);
                    
                    if(columnFieldObligatoryOrSuggest==FIELD_OBLIGATORY)
                    {
                    	lbl.setIcon(ICO_FIELDOBLIGATORY); 
                        lbl.setToolTipText("<html><table><tr><td>Το πεδίο </td><td><img src=\""+ICO_FIELDOBLIGATORY+"\"></td><td>'"+dbFieldsIn[i].getCaption()+"'</td><td> είναι υποχρεωτικό να συμπληρωθεί.</td></tr></table></html>");
                    }
                    else if(columnFieldObligatoryOrSuggest==FIELD_SUGGEST)
                    {
                    	lbl.setIcon(ICO_FIELDSUGGEST); 
                        lbl.setToolTipText("<html><table><tr><td>Το πεδίο </td><td><img src=\""+ICO_FIELDSUGGEST+"\"></td><td>'"+dbFieldsIn[i].getCaption()+"'</td><td> προτείνεται να συμπληρωθεί.</td></tr></table></html>");
                             
                    }
                    else if(columnFieldObligatoryOrSuggest==FIELD_NOCOMPLETION)
                    {
                    	
                    }
                 else
                 {
                     System.out.println("PanelOneDataOneRecData.setEntity  NOT DEFINED  columnFieldObligatoryOrSuggest:"+columnFieldObligatoryOrSuggest);
                 }                    
                    
                    tb = new JTextField(columnWidth);
                    

                    
                    
                    
                    //tb.setUI(new TinyTextFieldUI());
                    //tb.setDocument(new PlainDocumentInsertText(columnWidth,columnClass));//limiting the capacity of txt 
                    int colWdth = foreignColumnWidth - (foreignColumnWidth/5);
                    tb2 = new JTextField(foreignColumnWidth);
                    tb3 = new JTextField(colWdth);
                    tb4 = new JTextField(colWdth);
                    
                    tb4.setFocusable(false);
                    tb4.setEditable(false);
                    tb4.setEnabled(false);
                    
                    //tb.setUI(new TinyTextFieldUI());
                    //tb2.setDocument(new PlainDocumentInsertText(foreignColumnWidth,foreignColumnType));


                       Action actionShowDialogLookUp = new ActionShowDialogLookUp(foreignTable, i);
                       tb.getInputMap().put(KeyStroke.getKeyStroke(KEYSTROKE_F_LOOKUP_SHOW), "showDialogLookUp"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
                       tb.getActionMap().put("showDialogLookUp", actionShowDialogLookUp);
                       tb2.getInputMap().put(KeyStroke.getKeyStroke(KEYSTROKE_F_LOOKUP_SHOW), "showDialogLookUp"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
                       tb2.getActionMap().put("showDialogLookUp", actionShowDialogLookUp);
                    

                   // Action actionShowDialogNew = new ActionShowDialogNew(foreignTable, i);
                    Action actionShowDialogEdit = new ActionShowDialogEdit(foreignTable, i);

                   /* ArrayList listMenuCaption = new ArrayList();

                    JMenuItem menuListItemInsert = new JMenuItem("εισαγωγή");
                    menuListItemInsert.addActionListener(actionShowDialogNew);
                    listMenuCaption.add(menuListItemInsert);

                    JMenuItem menuListItemEdit = new JMenuItem("επεξεργασία");
                    menuListItemEdit.addActionListener(actionShowDialogEdit);
                    listMenuCaption.add(menuListItemEdit);  */                  
                    
                     txtLookUpBtn = new JTextBoxWithEditButtons(tb, false ,null,null, false,null,null,0, frame,"","",MONTH_DATE_ONLY);                  	
                     txtLookUpBtn2 = new JTextBoxWithEditButtons(tb2, true ,ICO_LOOKUP,actionShowDialogLookUp, true,ICO_EDIT14, actionShowDialogEdit,0, frame,"","",MONTH_DATE_ONLY);                  	
                    //if(lookUp.getLookUpField2(foreignTable)!=null)
                    //{                     
                       txtLookUpBtn3 = new JTextBoxWithEditButtons(tb3, false ,null,null, false,null,null,0, frame,"","",MONTH_DATE_ONLY);                  	
                    //}
                                   
                    txtLookUpBtn.setDocument(new PlainDocumentInsertText(columnWidth,columnClass));//limiting the capacity of txt
                    txtLookUpBtn2.setDocument(new PlainDocumentInsertText(foreignColumnWidth+10,foreignColumnType)); // +10 because in look up 2 text we would like to show all text
                    
                    eachDataFieldPanel.add(txtLookUpBtn.getComponent());
                    if(lookUp.getLookUpField2(foreignTable)!=null)
                    {
                       eachDataFieldPanel.add(lbl2);
                    }
                    eachDataFieldPanel.add(txtLookUpBtn2.getComponent());
                    if(lookUp.getLookUpField2(foreignTable)!=null)
                    {
                    	eachDataFieldPanel.add(lbl3);
                    	eachDataFieldPanel.add(txtLookUpBtn3.getComponent());
                    }
                    if(lookUp.getLookUpField3(foreignTable)!=null)
                    {
                    	eachDataFieldPanel.add(lbl4);
                    	eachDataFieldPanel.add(tb4);
                    }                    
                    
                    final int finalCol = i;  //  must be final            
                    
                   tb.getDocument().addDocumentListener(new DocumentHandler(i,0,columnClass,foreignTable,columnDbName));  
                     final String foreignTableFinal = foreignTable;
                     final JTextField tbFinal = tb;
                     final EntityDBFields[] finalDbFieldsInGroupOfPanels = dbFieldsInGroupOfPanels;
                     //final String columnDbNameFinal =columnDbName;
                     //final String columnLabelFinal = columnLabel;
                   tb.addFocusListener(new FocusListener()  // look in showRow for focus gained in tb
                   {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox

                            // exists in focuslost and displayDialogLookUp

                           String lookUpResult = calculateTextForLookupsAfterKeyIsSet(finalCol,foreignTableFinal);
                          //System.out.println("panelODORData.focusLost     keyChanged:"+fieldTxtsKeyChanged.get(finalCol).toString()+"    finalCol:"+finalCol);
                           if(Boolean.parseBoolean(fieldTxtsKeyChanged.get(finalCol).toString()))//keyChanged)
                           {
	
                             // System.out.println("PanelODORData.dbFieldsCalculateSet a  hasDataChanged:"+hasDataChanged);
                              dbFieldsCalculateSet(finalDbFieldsInGroupOfPanels,finalCol,foreignTableFinal);
                              checkFieldsIfThenElse(finalCol,CHECK_ON_ENTRY);
                              
                              calculateVarFromPreFieldAndSetGlobal(finalCol);
                              	//keyChanged=false;
                           }
                           else
                           {
                               
                           }
                           //System.out.println(tbFinal);
                           if (lookUpResult.equalsIgnoreCase("-") && tbFinal!=null && !tbFinal.getText().trim().equalsIgnoreCase("")) 
                           {
                                 
                                 utilsGui.showMessageError("Key typed not correct. Please retry or delete value.");                               
                                 tbFinal.requestFocus();  // if not correct return to first textfield of lookup
                           } 
                           
                           //int intFieldToGetTheValue = utilsPanelReport.calculateAllFieldsFromParentDBFieldsForFormVariable1(dbFieldsAll);               
                          
                           //System.out.println("panelODORData.focusLost "+columnLabelFin+" "+getWhereValueNameThatMatchesColumn(columnLabelFin,null));
                           //System.out.println("panelODORData.focusLost isMasterUniqueFin"+isMasterUniqueFin);
                        
                        }
                        public void focusGained(FocusEvent e)  // look in showRow for focus gained and text selected in tb
                        {   
                             //System.out.println("panelODORData.focusGained     keyChanged:"+keyChanged);
                           
                             tbFinal.setSelectionStart(0);
                             tbFinal.setSelectionEnd(tbFinal.getText().length());                            
               //             keyChanged=false;///-------------------
                        }                    	
                    });

                   final JTextField tb2Final = tb2;
                   tb2.addFocusListener(new FocusListener()  // look in showRow for focus gained in tb
                   {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox

                           //calculateTextForLookupsAfterKeyIsSet(col,foreignTable);
                           // System.out.println("PanelODoRData.focusLost B  finalCol:"+finalCol);
                           if(Boolean.parseBoolean(fieldTxtsKeyChanged.get(finalCol).toString()))//keyChanged)
                           {                           
          //                    calculateRecordAfterKeySet(finalCol);
                              fieldTxtsKeyChanged.set(finalCol, false);
                              keyChanged=false;
                           }
                           //  dbFieldsCalculateSet(finalDbFieldsInGroupOfPanels,finalCol,foreignTableFinal);
                           //System.out.println("panelODORData.focusLost");
                           //System.out.println("panelODORData.focusLost "+columnLabelFin+" "+getWhereValueNameThatMatchesColumn(columnLabelFin,null));
                           //System.out.println("panelODORData.focusLost isMasterUniqueFin"+isMasterUniqueFin);
                          // int intFieldToGetTheValue = utilsPanelReport.calculateAllFieldsFromParentDBFieldsForFormVariable1(dbFieldsAll);               
                         calculateVarFromPreFieldAndSetGlobal(finalCol);
                        }
                        public void focusGained(FocusEvent e)  // look in showRow for focus gained and text selected in tb
                        {   
                             tb2Final.setSelectionStart(0);
                             tb2Final.setSelectionEnd(tb2Final.getText().length());                            
                           if(Boolean.parseBoolean(fieldTxtsKeyChanged.get(finalCol).toString()))//keyChanged)
                           {                        	
          //              	  calculateRecordAfterKeySet(finalCol);
                                  fieldTxtsKeyChanged.set(finalCol, false);
                        	  keyChanged=false;
                           }
                        }                   	
                    });
                         

                       fieldTxts.set(i,tb);
                       fieldTxts2.set(i,tb2);
                       fieldTxts3.set(i,tb3);
                       fieldTxts4.set(i,tb4);
                       //tb2No=tb2No+1; 
                                            
                       /*if(checkIfNameMatchesWhereValueName(columnLabel))
                       {
                       	tb.setVisible(false);
                       	tb2.setVisible(false);
                       	//btnLookup.setVisible(false);
                       	lbl.setVisible(false);
                       }*/
                       
                       //boolean isColumnPrimKey = databaseTableMeta.isColumnPrimKey(columnLabel);
                  /*   if( !isNewRec && intPKAutoInc == FIELD_PRIMARY_KEY_AUTOINC)
                       {
                             tb.setEditable(false);
                          	    tb.setFocusable(false);
                          	    txtLookUpBtn.setBtn1Enabled(false);
                       	        tb2.setEditable(false);
                       	        tb2.setFocusable(false);*/
                       	        //btnLookup.setVisible(false);
                    //   }    
                       
                      // System.out.println("PanelOneDataOnRecData.setEntity   lookup     --("+i+")--    isVisibleOrEditable[i]"+isVisibleOrEditable[i]);
                       /*if(isVisibleOrEditable[i]==FIELD_VISIBLE_NOT_EDITABLE_ALWAYS)
                       {
                      	  tb.setEditable(false);
                          tb.setFocusable(false);
                          txtLookUpBtn.setBtn1Enabled(false);
                       	  tb2.setEditable(false);
                       	  tb2.setFocusable(false);
                       	  tb3.setEditable(false);
                       	  tb3.setFocusable(false);                             
                       }
                       else
                       {
                      	  tb.setEditable(true);
                          tb.setFocusable(true);
                          txtLookUpBtn.setBtn1Enabled(true);
                       	  tb2.setEditable(true);
                       	  tb2.setFocusable(true);
                       	  tb3.setEditable(true);
                       	  tb3.setFocusable(true);                             
                       }*/
                       
                       
                       
                       //select the status where is not editable and is the prim key
               /*        if(!isEditable && !isNewRec && countOfUniqueKeys == 1)
                       {
                       	  //System.out.println("PanelODORData.setEntity now "+isEditable);
                      	  tb.setEditable(false);
                          tb.setFocusable(false);
                          txtLookUpBtn.setBtn1Enabled(false);
                       	  tb2.setEditable(false);
                       	  tb2.setFocusable(false);
                       	  tb3.setEditable(false);
                       	  tb3.setFocusable(false);                       	  
           //               txtLookUpBtn.setBtn1Visible(false);
           //               txtLookUpBtn2.setBtn1Visible(false);
                       	  
                       }   
                       else
                       {
                       	
                       	  /*tb.setEditable(true);
                          tb.setFocusable(true);
                       	  tb2.setEditable(true);
                       	  tb2.setFocusable(true);*/
                       	  //System.out.println("PanelODORData.setEntity now else "+isEditable);
                //       } 
                       
                       // because is edit not new initially false
                       
                     /* if(isNewRec)
                       {
                          txtLookUpBtn.setBtn1Visible(true);
                          txtLookUpBtn2.setBtn1Visible(true);
                       }
                       else
                       {
                          txtLookUpBtn.setBtn1Visible(false);
                          txtLookUpBtn2.setBtn1Visible(false);
                       }
                       */       

                   if(!txtLookUpBtn.isVisible() )// add doc handler so if there is change trigger save
                   {    }
                   else
                   {
                   	  txtLookUpBtn.getDocument().addDocumentListener(new DocumentHandler(i,0,foreignColumnType,foreignTable,columnDbName)); 
                   }

                   if(!txtLookUpBtn2.isVisible() )// add doc handler so if there is change trigger save
                   {    }
                   else
                   {
                   	  txtLookUpBtn2.getDocument().addDocumentListener(new DocumentHandler(i,1,"java.sql.String",foreignTable,columnDbName)); 
                   }                   
                   
                   if(txtLookUpBtn3!=null && !txtLookUpBtn3.isVisible() )// add doc handler so if there is change trigger save
                   {    }
                   else if(txtLookUpBtn3!=null && txtLookUpBtn3.isVisible())
                   {
                   	  txtLookUpBtn3.getDocument().addDocumentListener(new DocumentHandler(i,2,"java.sql.String",foreignTable,columnDbName)); 
                   }  
                   else
                   {
                   		
                   }
                }  // if there is foreign table
                else if(dbFieldsInGroupOfPanels[i].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)
                {  // setEntity
                    	
                    lbl = new JLabel(columnLabel+":("+KEYSTROKE_F_LOOKUP_SHOW+")", JLabel.RIGHT);
                    lbl.setIconTextGap(0);
                    

                    
                    if(columnFieldObligatoryOrSuggest==FIELD_OBLIGATORY)
                    {
                    	lbl.setIcon(ICO_FIELDOBLIGATORY); 
                        lbl.setToolTipText("<html><table><tr><td>Το πεδίο </td><td><img src=\""+ICO_FIELDOBLIGATORY+"\"></td><td>'"+dbFieldsIn[i].getCaption()+"'</td><td> είναι υποχρεωτικό να συμπληρωθεί.</td></tr></table></html>");
                    }
                    else if(columnFieldObligatoryOrSuggest==FIELD_SUGGEST)
                    {
                    	lbl.setIcon(ICO_FIELDSUGGEST); 
                        lbl.setToolTipText("<html><table><tr><td>Το πεδίο </td><td><img src=\""+ICO_FIELDSUGGEST+"\"></td><td>'"+dbFieldsIn[i].getCaption()+"'</td><td> προτείνεται να συμπληρωθεί.</td></tr></table></html>");
                             
                    }
                    else if(columnFieldObligatoryOrSuggest==FIELD_NOCOMPLETION)
                    {
                    	
                    }

                    
                    LookupTableConstantsMgt lookUpTableConstants = new LookupTableConstantsMgt();
                    EntityLookupTableConstantsData[] elutcData = lookUpTableConstants.getEntityLookupTableConstantsData(dbFieldsInGroupOfPanels[i].getLookupEntityName());
                    //dbFieldsInGroupOfPanels[i].getLookupEntityName();
                    //String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
                   JComboBox cmbBox = new JComboBox();
                   if(elutcData == null)
                   {
                       
                   }
                   else
                   {
                    for(int l= 0;l<elutcData.length;l++)
                    {
                        cmbBox.addItem(elutcData[l].getName()+"");

                    }
                   }
                     eachDataFieldPanel.add(cmbBox);
                     tb = new JTextField(columnWidth); // just for document changed, 
                     
                     tb.getDocument().addDocumentListener(new DocumentHandler(i,0,columnClass, null,columnDbName));      
                      
                     final JComboBox finalCmbBox = cmbBox;
                    final int finalCol = i;  //  must be final            
                     final EntityDBFields[] finalDbFieldsInGroupOfPanels = dbFieldsInGroupOfPanels;  
                     final JTextField finalTb = tb;
                   finalCmbBox.addItemListener(new ItemListener()
                   {
                     public void itemStateChanged(ItemEvent e)
                     {
                       //JCheckBox source = (JCheckBox)e.getSource();

                         if(e.getStateChange() == ItemEvent.SELECTED)
                         {

                           //if(Boolean.parseBoolean(fieldTxtsKeyChanged.get(finalCol).toString()))//keyChanged)
                           //{
          //                    calculateRecordAfterKeySet(finalCol);
                              System.out.println("   setEntity  cmbBox  selected  dbFieldsCalculateSet   "+dbFieldsInGroupOfPanels[finalCol].getLookupType()+"     e.getStateChange():"+e.getStateChange()+"   index:"+finalCmbBox.getSelectedIndex()+"    item:"+finalCmbBox.getSelectedItem()+"  "+e.getItem());
                               //System.out.println("PanelODORData.dbFieldsCalculateSet b  hasDataChanged:"+hasDataChanged);
                              dbFieldsCalculateSet(finalDbFieldsInGroupOfPanels,finalCol,"");
                              checkFieldsIfThenElse(finalCol,CHECK_ON_ENTRY);
                              	//keyChanged=false;
                           //}
                             
                         	finalTb.setText((finalCmbBox.getSelectedIndex()+1)+"");//  .getSelectedItem()+""); // true
                         }
                         else
                         {
                             //System.out.println("PanelODORData   setEntity  cmbBox  not selected  dbFieldsCalculateSet   "+dbFieldsInGroupOfPanels[finalCol].getLookupType()+"     e.getStateChange():"+e.getStateChange()+"   "+e.getItem());
                         }

                     }

                    });

  
                    
                    //tb = new JTextField(columnWidth); 

               /*    if(columnWidth<20)
                    {
                    	columnWidth = columnWidth - (columnWidth/5);
                    }
                    else
                    {
                    	columnWidth = columnWidth - (columnWidth/4);
                    }

                    Action actionShowDialogLookUp = new ActionShowDialogLookUp(foreignTable, i);
                       tb.getInputMap().put(KeyStroke.getKeyStroke(KEYSTROKE_F_LOOKUP_SHOW), "showDialogLookUp"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
                       tb.getActionMap().put("showDialogLookUp", actionShowDialogLookUp);

                    eachDataFieldPanel.add(tb);

                    final int finalCol = i;  //  must be final            
                    
                   tb.getDocument().addDocumentListener(new DocumentHandler(i,0,columnClass,foreignTable,columnDbName));  
                     final JTextField tbFinal = tb;
                     final EntityDBFields[] finalDbFieldsInGroupOfPanels = dbFieldsInGroupOfPanels;
                   tb.addFocusListener(new FocusListener()  // look in showRow for focus gained in tb
                   {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox

                            // exists in focuslost and displayDialogLookUp
  
                           //String lookUpResult = calculateTextForLookupsAfterKeyIsSet(finalCol,foreignTableFinal);
                           
                           
                          //System.out.println("panelODORData.focusLost     keyChanged:"+fieldTxtsKeyChanged.get(finalCol).toString()+"    finalCol:"+finalCol);
                           if(Boolean.parseBoolean(fieldTxtsKeyChanged.get(finalCol).toString()))//keyChanged)
                           {
                              calculateRecordAfterKeySet(finalCol);	
                              dbFieldsCalculateSet(finalDbFieldsInGroupOfPanels,finalCol);
                              	//keyChanged=false;
                           }
                           //System.out.println(tbFinal);
                          /* if (lookUpResult.equalsIgnoreCase("-") && tbFinal!=null && !tbFinal.getText().trim().equalsIgnoreCase("")) 
                           {
                                 
                                 utilsGui.showMessageError(this,"Key typed not correct. Please retry or delete value.");                               
                                 tbFinal.requestFocus();  // if not correct return to first textfield of lookup
                           } */

                           
                           //System.out.println("panelODORData.focusLost");
                           //System.out.println("panelODORData.focusLost "+columnLabelFin+" "+getWhereValueNameThatMatchesColumn(columnLabelFin,null));
                           //System.out.println("panelODORData.focusLost isMasterUniqueFin"+isMasterUniqueFin);
                        
                /*        }
                        public void focusGained(FocusEvent e)  // look in showRow for focus gained and text selected in tb
                        {   
                             //System.out.println("panelODORData.focusGained     keyChanged:"+keyChanged);
                           
                             tbFinal.setSelectionStart(0);
                             tbFinal.setSelectionEnd(tbFinal.getText().length());                            
               //             keyChanged=false;///-------------------
                        }                    	
                    });*/
                    
                    fieldTxts.set(i,cmbBox);
      
                } // lookup constants
/*                else if(columnClass.equalsIgnoreCase("java.awt.image.BufferedImage"))
                {

                   JLabel lblImage = new JLabel();

                     
                     tb = new JTextField(columnWidth); // just for document changed, 
                     
                     tb.getDocument().addDocumentListener(new DocumentHandler(i,0,columnClass, null,columnDbName));      
                      
                     final JLabel finalLblImage = lblImage;
                    final int finalCol = i;  //  must be final            
                     final EntityDBFields[] finalDbFieldsInGroupOfPanels = dbFieldsInGroupOfPanels;  
                     final JTextField finalTb = tb;
                     JButton btnLoadImage = new JButton();
                     JButton btnClearImage = new JButton();
                   
                     
        btnLoadImage.setText("έυρεση");
        //btnInsert.setText("νέα (F9)");
        btnLoadImage.setOpaque(false);
        btnLoadImage.setToolTipText("έυρεση εικόνας");
        btnLoadImage.setIcon(ICO_INSERT16);
        btnLoadImage.setFocusable(true);        
        btnLoadImage.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   
	           	        
	        } 
	    });
       // Action actionNewRec = new ActionNewRec();
       // btnLoadImage.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F9"), "newRec"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
       // btnLoadImage.getActionMap().put("newRec", actionNewRec);
   
        btnClearImage.setText("διαγραφή");
        //btnInsert.setText("νέα (F9)");
        btnClearImage.setOpaque(false);
        btnClearImage.setToolTipText("διαγραφή εικόνας");
        btnClearImage.setIcon(ICO_DELETE16);
        btnClearImage.setFocusable(true);        
        btnClearImage.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   
	                 finalLblImage.setIcon(null);
	        } 
	    });
       // Action actionNewRec = new ActionNewRec();
       // btnClearImage.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F9"), "newRec"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
       // btnClearImage.getActionMap().put("newRec", actionNewRec);
                  JxPanel pnlButtonsOfImage = new JxPanel();
                  pnlButtonsOfImage.setLayout(new BoxLayout(pnlButtonsOfImage, BoxLayout.PAGE_AXIS));
                  pnlButtonsOfImage.add(btnLoadImage);
                  pnlButtonsOfImage.add(btnClearImage);
                  //JScrollPane  finJscrlPanel
                  
       
       
                  JxPanel pnlImage = new JxPanel(); 
                  pnlImage.setLayout(new BorderLayout());
                  pnlImage.add(pnlButtonsOfImage, BorderLayout.LINE_START);
                  pnlImage.add(lblImage,BorderLayout.CENTER);
                  
                  eachDataFieldPanel.add(pnlImage);
                  
                  fieldTxts.set(i,lblImage);

                    
                }
 */               else
                {
                    /*int colWdth=0;
                    if(columnWidth<20)
                    {
                    	colWdth = columnWidth - (columnWidth/6);
                    	
                    }
                    else
                    {
                    	colWdth = columnWidth - (columnWidth/3);
                    }*/
                    
                    //System.out.println("PanelODORData.setEntity ("+i+")  "+columnDbName+"  "+columnWidth+" "+colWdth+" columnClass:"+columnClass);
                    
                    
                    
                    
                    if(columnDbName!= null && columnClass.equalsIgnoreCase("table") )
                    {
                         
                    }
                 else if(columnDbName!= null && columnClass.equalsIgnoreCase("htmlfile"))
                 {
                     lbl = new JLabel();
                 }                    
                    else if(columnDbName!= null && columnClass.equalsIgnoreCase("java.lang.Boolean"))
                    {
                    	lbl = new JLabel();
                    }
                    else if (columnDbName!= null)
                    {   
                    	if(columnLabel.equals(""))
                    	{
                    		lbl = new JLabel();
                    	}
                    	else
                    	{
                    	    lbl = new JLabel(columnLabel+":", SwingConstants.CENTER);
                            lbl.setIconTextGap(0);
                             if(columnFieldObligatoryOrSuggest==FIELD_OBLIGATORY)
                             {
                    	          lbl.setIcon(ICO_FIELDOBLIGATORY);  
                                  lbl.setToolTipText("<html><table><tr><td>Το πεδίο </td><td><img src=\""+ICO_FIELDOBLIGATORY+"\"></td><td>'"+dbFieldsInGroupOfPanels[i].getCaption()+"'</td><td> είναι υποχρεωτικό να συμπληρωθεί.</td></tr></table></html>");
                                 
                             }
                             else if(columnFieldObligatoryOrSuggest==FIELD_SUGGEST)
                             {
                    	             lbl.setIcon(ICO_FIELDSUGGEST); 
                                     lbl.setToolTipText("<html><table><tr><td>Το πεδίο </td><td><img src=\""+ICO_FIELDSUGGEST+"\"></td><td>'"+dbFieldsInGroupOfPanels[i].getCaption()+"'</td><td> προτείνεται να συμπληρωθεί.</td></tr></table></html>");
                                     
                             }
                             else if(columnFieldObligatoryOrSuggest==FIELD_NOCOMPLETION)
                             {
                    	
                             }	
                    	}
                    	

                      
                    }
                    else if(columnDbName == null)
                    {
                        System.out.println("error PanelODORData.setEntity ("+i+")  "+columnDbName+"  "+columnWidth+" "+columnClass);	
                    }    
                    else
                    {
                        System.out.println("error PanelODORData.setEntity ("+i+")  "+columnDbName+"  "+columnWidth+" "+columnClass);	
                    }
                    
                    //System.out.println("-PanelODORData.setEntity ("+i+")  "+columnDbName+" "+columnClass+"    "+dbFieldsInGroupOfPanels[i].getChildTable());   
                    if(columnClass.equalsIgnoreCase("table") )
                    {
                        EntityDBFields[] dbFieldsChild = dbFieldsInGroupOfPanels[i].getDbChildFields();
                        String strTableChild = dbFieldsInGroupOfPanels[i].getChildTable();
                        String sql = dbFieldsInGroupOfPanels[i].getSqlTableChildRead();//"SELECT FARMERID,AA, FARMERTELEPHONEID,DEPARTMENT,TELEPHONE,PERSON FROM farmertelephone";
                   int intTableOfParentDBFields = i;
                        
                 if(isEditable)// observed that when deleting from showing not editable ads to query
                 {
                       sql = utilsString.addSubqueryAndToWhereBeforeOrderBy(sql,strTableChild,primKeyDb,primKeyValue);
                 }                        
                        
                        
                        
                        
          //System.out.println(" - - - - - - - PanelODORData.setEntity ("+i+")    columnClass:"+columnClass+"    columnDbName:"+columnDbName+"   primKeyValue:"+primKeyValue+"    isNewRec:"+isNewRec+"     "+dbFieldsChild+"   sql:"+sql+"     query:"+query);
                        //PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(f);
                      PanelOneDataManyRecData pnlODMRData =  new PanelOneDataManyRecData(frame);
                      
                        
                        pnlODMRData.setEntity(dbFieldsInGroupOfPanels[i].getCaption(),sql,dbFieldsInGroupOfPanels[i].getChildTable(),true,true,dbFieldsInGroupOfPanels,
                                dbFieldsChild,isNewRec,primKeyDb,/*formGlobalTableToGet1,formGlobalTableToApply1,/*formGlobalField1,formGlobalVariable1,*/primKeyValue,
                                dbFieldsInGroupOfPanels[i].getChildTableFieldsForSums(),fieldTxts,this,intTableOfParentDBFields);
                        //setFormGlobalVariable1ToPanelODMRData();
                    //System.out.println(" - - - - - - - PanelODORData.setEntityA")    ;
                        pnlODMRData.filterForWritableTable(sql,isNewRec,isNewRecFromCopy,false);  // last parameter is true when we try to insert a completely new record with one and many, is false when we edit an already saved record
                   
                  // System.out.println(" - - - - - - - PanelODORData.setEntityB")    ;

                   lbl = new JLabel(dbFieldsInGroupOfPanels[i].getCaption());
                   lbl.setIconTextGap(0);
                   if(columnFieldObligatoryOrSuggest==FIELD_TABLE_ONEROWATLEAST_OBLIGATORY)
                   {
                    	    lbl.setIcon(ICO_FIELDOBLIGATORY);  
                            lbl.setToolTipText("<html><table><tr><td>Στον πίνακα </td><td><img src=\""+ICO_FIELDOBLIGATORY+"\"></td><td>'"+dbFieldsInGroupOfPanels[i].getCaption()+"'</td><td> είναι υποχρεωτικό να συμπληρωθεί τουλάχιστον μία σειρά.</td></tr></table></html>");
                                 
                   }
                   else if(columnFieldObligatoryOrSuggest==FIELD_TABLE_ONEROWATLEAST_SUGGEST)
                   {
                            lbl.setIcon(ICO_FIELDSUGGEST); 
                            lbl.setToolTipText("<html><table><tr><td>Στον πίνακα </td><td><img src=\""+ICO_FIELDSUGGEST+"\"></td><td>'"+dbFieldsInGroupOfPanels[i].getCaption()+"'</td><td> προτείνεται να συμπληρωθεί τουλάχιστον μία σειρά.</td></tr></table></html>");
                                     
                   }
                   else 
                   {
                    	System.out.println("PanelODORData.setEntity lbl for table UNKNOWN "+columnFieldObligatoryOrSuggest);
                   }	                   




                   
                 if(dbFieldsInGroupOfPanels[i].getChildTableInPosition()==CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL)
                 {
                     pnlODMRData.setScrollPaneSize(dbFieldsInGroupOfPanels[i].getChildTableHeight());
                     BorderLayout blayout = new BorderLayout();     
                     eachDataFieldPanel.setLayout(blayout);
                     eachDataFieldPanel.add(pnlODMRData,BorderLayout.CENTER);
                   //System.out.println("PanelODORData.setEntity -------- ChildTableInPosition ("+i+") a  "+dbFieldsInGroupOfPanels[i].getCaption());
                 }
                 else if(dbFieldsInGroupOfPanels[i].getChildTableInPosition()==CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE)
                 {// if CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE then placed in bottom of form
                    pnlODMRData.setScrollPaneSize(dbFieldsInGroupOfPanels[i].getChildTableHeight());// must be because it has panel collapsable and the table
              
                    JxPanelCollapsable pnlIncludeLabelFromJTable = new JxPanelCollapsable(); // must be because it has panel collapsable and the table
                  String captionOfGroup =  entityGroupOfComps[dbFieldsInGroupOfPanels[i].getGroupOfComps()].getCaption();
                    pnlIncludeLabelFromJTable.setEntityCollapsable(captionOfGroup, true,CLR_PANEL_END,CLR_PANEL_START,1,SHOW_PANEL_COLLAPSE_NOT,SHOW_BORDER_BTN_ONLY_ONE,captionOfGroup);//CLR_PANEL_START,CLR_PANEL_END);
                  
                    // must be because it has panel collapsable and the table
                    //pnlIncludeLabelFromJTable.add(lbl,BorderLayout.PAGE_START); 
                    pnlIncludeLabelFromJTable.add( pnlODMRData);// must be because it has panel collapsable and the table
                     //eachDataFieldPanel.add(pnlODMRData,BorderLayout.CENTER);                    
 //                    // must be because it has panel collapsable and the table
                       
                       pnlIncludeJTableAsInsideBorderLayoutSizable.add( pnlIncludeLabelFromJTable, BorderLayout.CENTER);// must be because it has panel collapsable and the table
                   //  pnlIncludeJTableAsInsideBorderLayoutSizable.add(pnlODMRData, BorderLayout.CENTER);
                     
                     //panelAllOnIt.add(pnlODMRData, BorderLayout.CENTER);
                     //System.out.println("PanelODORData.setEntity ------- ChildTableInPosition ("+i+") b  "+dbFieldsInGroupOfPanels[i].getCaption());
                 }
                 else
                 {
                     
                     //System.out.println("PanelODORData.setEntity ------- ChildTableInPosition  ("+i+") c NOT DEFINED:"+dbFieldsInGroupOfPanels[i].getChildTableInPosition()+" "+dbFieldsInGroupOfPanels[i].getCaption());
                 }

                   fieldTxts.set(i,pnlODMRData);
               //System.out.println("PanelODORData.setEntity add 00000000000000000000000000 no("+i+")PanelOneDataManyRecData to fieldTxts.  "+columnDbName+"  "+columnWidth+" "+columnClass);
   
                   
                   
                    } // is table
                    else if(columnDbName!= null && columnClass.equalsIgnoreCase("htmlfile"))
                    {
                        PanelHtmlBrowser panelHtmlBrowser = new PanelHtmlBrowser();
                        panelHtmlBrowser.setVisible(true);
                        panelHtmlBrowser.loadURL(periodiki);
                          //panelHtmlBrowser.loadURL("www.businesseye.gr");
          /*         	if(columnWidth>499) // like printform html // look also bellow
                        {
                            textArea = new JTextArea(30, 96);
                        }
                        else
                        {
                            textArea = new JTextArea(6, 96);
                        }*/
                   	
                   	lbl = new JLabel(columnLabel);
         //          	textArea.setFont(lbl.getFont());
         //           scrollPaneTextArea = new JScrollPane(textArea);//textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
         //           scrollPaneTextArea.setOpaque(false);
        //            textArea.setDocument(new PlainDocumentInsertText(columnWidth,columnClass));//limiting the capacity of txt	
       //  for change ask            	panelHtmlBrowser.getDocument().addDocumentListener(new DocumentHandler(i,0,columnClass,null,columnDbName)); 
                        final PanelHtmlBrowser panelHtmlBrowserFinal = panelHtmlBrowser;
                    panelHtmlBrowser.addFocusListener(new FocusListener()  // look in showRow for focus gained in tb
                    {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                           
                        }

                    	public void focusGained(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                  //           panelHtmlBrowserFinal.setSelectionStart(0);
                  //           panelHtmlBrowserFinal.setSelectionEnd(textAreaFinal.getText().length());                            
                            //textArea.remove(lblIcoAttention);
                        }
                    });                   
                   
                  JxPanel pnlHtml = new JxPanel(); 
                  pnlHtml.setLayout(new BorderLayout());
                  pnlHtml.add(panelHtmlBrowser, BorderLayout.CENTER);
                  //pnlImage.add(lblImage,BorderLayout.LINE_END);
                  
                  //eachDataFieldPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
                     BorderLayout blayout = new BorderLayout();     
                     eachDataFieldPanel.setLayout(blayout);                  
                  eachDataFieldPanel.add(pnlHtml,BorderLayout.CENTER);
                  
                  fieldTxts.set(i,panelHtmlBrowser);                                           
                        
                    }                     
                    else if(columnClass.equalsIgnoreCase("java.awt.image.BufferedImage")) // image ------
                    {


                   JLabel lblImage = new JLabel();
                   //lblImage.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                   //lblImage.setBounds(200,20,1300,800); 
                   //JScrollPane jScrollPaneLabel = new JScrollPane();
                   //jScrollPaneLabel.setViewportView(lblImage);
                   //jScrollPaneLabel.setBounds(330, 70, 210, 160);

                     
                     tb = new JTextField(columnWidth); // just for document changed, 
                     
                     tb.getDocument().addDocumentListener(new DocumentHandler(i,0,columnClass, null,columnDbName));      
                      
                     final JLabel finalLblImage = lblImage;
                    final int finalCol = i;  //  must be final            
                     final EntityDBFields[] finalDbFieldsInGroupOfPanels = dbFieldsInGroupOfPanels;  
                     final JTextField finalTb = tb;
                     JButton btnLoadImage = new JButton();
                     JButton btnClearImage = new JButton();
                   
                     
        //btnLoadImage.setText("έυρεση");
        btnLoadImage.setOpaque(false);
        btnLoadImage.setToolTipText("έυρεση εικόνας");
        btnLoadImage.setIcon(ICO_ADD);
        btnLoadImage.setFocusable(true);        
        btnLoadImage.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   
	           	    imageLoadFromFile(finalLblImage);
                            finalTb.setText("picture load"); // if changed ask
                    //finalLblImage.setIcon(null);
	        } 
	    });
       // Action actionNewRec = new ActionNewRec();
       // btnLoadImage.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F9"), "newRec"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
       // btnLoadImage.getActionMap().put("newRec", actionNewRec);
   
        //btnClearImage.setText("διαγραφή");
        btnClearImage.setOpaque(false);
        btnClearImage.setToolTipText("διαγραφή εικόνας");
        btnClearImage.setIcon(ICO_DELETE);
        btnClearImage.setFocusable(true);        
        btnClearImage.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   
                         finalLblImage.setText(imageEmpty);
	                 finalLblImage.setIcon(null);
                         finalTb.setText("picture empty"); // if changed ask
	        } 
	    });
       // Action actionNewRec = new ActionNewRec();
       // btnClearImage.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F9"), "newRec"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
       // btnClearImage.getActionMap().put("newRec", actionNewRec);
                  JxPanel pnlButtonsOfImage = new JxPanel();
                  pnlButtonsOfImage.setLayout(new BoxLayout(pnlButtonsOfImage, BoxLayout.PAGE_AXIS));
                  pnlButtonsOfImage.setBorder(BorderFactory.createEmptyBorder(20, 5, 30, 15));// top, left, bottom, right
                  pnlButtonsOfImage.add(btnLoadImage);
                  pnlButtonsOfImage.add(btnClearImage);
                  //JScrollPane  finJscrlPanel
                  
       
       
                  JxPanel pnlImage = new JxPanel(); 
                  pnlImage.setLayout(new BorderLayout());
                  pnlImage.add(pnlButtonsOfImage, BorderLayout.LINE_START);
                  pnlImage.add(lblImage,BorderLayout.LINE_END);
                  
                  //eachDataFieldPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
                  eachDataFieldPanel.add(pnlImage);
                  
                  fieldTxts.set(i,lblImage);

                                            
                        
                        
                        
                        




                        
                    // pnlODMRData.setScrollPaneSize(dbFieldsInGroupOfPanels[i].getChildTableHeight());
                  /*  JLabel lblBlob = new JLabel("-lblBlob-");
                     BorderLayout blayout = new BorderLayout();     
                     eachDataFieldPanel.setLayout(blayout);
                     eachDataFieldPanel.add(lblBlob,BorderLayout.CENTER);                        
                        fieldTxts.set(i,lblBlob);*/
                    }
                    
                    
                 //JLabel lblCheckValid =new JLabel(); 
                 	//lblCheckValid.setIcon(ICO_CANCEL16);
                 JButton btnCheckValid = new JButton();
                 btnCheckValid.setBorder(BorderFactory.createEmptyBorder(2,7,2,7));//new LineBorder(Color.WHITE));//.setSize(new Dimension(30,5));//setBounds(0, 0, 20, 10);
                 btnCheckValid.setIcon(ICO_CANCEL16);
                 final int columnFieldValidationFinal = columnFieldValidation;
                 final int iFinal = i;
                 btnCheckValid.addActionListener(new ActionListener()
                 {
	             public void actionPerformed(ActionEvent e) 
	            {	   
	                 displayDialogCheckValidation(columnFieldValidationFinal, iFinal);
	             } 
	        });
                 
                 
                   // java.lang.String
                   if(columnClass.equalsIgnoreCase("java.lang.String") && columnWidth>COLWIDTH_FOR_BIGTEXTBOX) //if change 40 change and later in adding it to panel, and in showrow, and in rowUpdate
                   {
                   	if(columnWidth>COLWIDTH_FOR_HTML) // like printform html // look also bellow
                        {
                            //textArea = new JTextArea(30, 96);
                          lbl = new JLabel(columnLabel);
                          System.out.println("setEntity  columnLabel:"+columnLabel+"   i:"+i+"  columnDbName:"+columnDbName+" columnWidth:"+columnWidth+"    columnClass:"+columnClass);
                           PanelHtmlEditor htmlTxtArea = new PanelHtmlEditor();//(PanelHtmlEditor)fieldTxts.get(i);//this is for html
                   //        fieldValue = htmlTxtArea.getTextAreaString();
                                                    
                    //scrollPaneTextArea.setOpaque(false);
                    htmlTxtArea.setDocument(new PlainDocumentInsertText(columnWidth,columnClass));//limiting the capacity of txt	
                   	htmlTxtArea.getDocument().addDocumentListener(new DocumentHandler(i,0,columnClass,null,columnDbName));                          
                      
                        final PanelHtmlEditor htmlTextAreaFinal = htmlTxtArea;
                    htmlTxtArea.addFocusListener(new FocusListener()  // look in showRow for focus gained in tb
                    {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                           
                        }

                    	public void focusGained(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                             htmlTextAreaFinal.setSelectionStart(0);
                             htmlTextAreaFinal.setSelectionEnd(htmlTextAreaFinal.getText().length());                            
                            //textArea.remove(lblIcoAttention);
                        }
                    });                                       
                        }
                        else
                        {
                            textArea = new JTextArea(6, 96);
                        
                   	
                   	lbl = new JLabel(columnLabel);
                   	textArea.setFont(lbl.getFont());
                    scrollPaneTextArea = new JScrollPane(textArea);//textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    scrollPaneTextArea.setOpaque(false);
                    textArea.setDocument(new PlainDocumentInsertText(columnWidth,columnClass));//limiting the capacity of txt	
                   	textArea.getDocument().addDocumentListener(new DocumentHandler(i,0,columnClass,null,columnDbName)); 
                        final JTextArea textAreaFinal = textArea;
                    textArea.addFocusListener(new FocusListener()  // look in showRow for focus gained in tb
                    {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                           
                        }

                    	public void focusGained(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                             textAreaFinal.setSelectionStart(0);
                             textAreaFinal.setSelectionEnd(textAreaFinal.getText().length());                            
                            //textArea.remove(lblIcoAttention);
                        }
                    });                   
                        }
                   }
                   else if (columnWidth<COLWIDTH_FOR_BIGTEXTBOX)// string or something else
                   {
                      tb = new JTextField(columnWidth);
                      /*if(columnFieldValidation==FIELD_VALIDATION_AFM)
                      {
                      	//lblCheckValid 
                      }
                      else
                      {
                      	
                      }*/
                      tb.setDocument(new PlainDocumentInsertText(columnWidth,columnClass));//limiting the capacity of txt
                      tb.getDocument().addDocumentListener(new DocumentHandler(i,0,columnClass,null,columnDbName)); 
                    final JTextComponent jtc = tb;
                    
                    jtc.addFocusListener(new FocusListener()  // look in showRow for focus gained in tb
                    {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                                  
                               //System.out.println("PanelODORData.dbFieldsCalculateSet b  hasDataChanged:"+hasDataChanged);
                            
                        }

                    	public void focusGained(FocusEvent e)
                        {  // if entered nothing or spaces on textbox but get focus
                             jtc.setSelectionStart(0);
                             jtc.setSelectionEnd(jtc.getText().length());                            
                            //System.out.println("PanelODORData.setEntity got focus");
                            //jtc.setBackground(Color.white);
                            //jtc.remove(lblIcoAttention);
                            //jtc.revalidate();
                            
                        }
                    });
                   
                   }
                    	               	
                   
                  //JTextBoxWithEditButtons txtWithBtnDate = new JTextBoxWithEditButtons();
                  //System.out.println("PanelODORData.setEntity columnClass "+columnClass+"   columnDbName:"+columnDbName+"   i:"+i);
                  
                  
                    if(columnCompsFontSize == FONT_SIZE_NOT_SET)
                    {
                        
                    }
                    else
                    {
                        //tb.setFont(new Font(tb.getFont().getFontName(), Font.PLAIN, columnCompsFontSize));
                         //  tb.setFont((tb.getFont().deriveFont(columnCompsFontSize)));// font size float
                        Font fonttext = new Font(tb.getFont().getFontName(), Font.PLAIN, columnCompsFontSize); //"SansSerif"
                        
                        tb.setFont(fonttext);
                        Font fontlabel = new Font(tb.getFont().getFontName(), Font.PLAIN, columnCompsFontSize+1); //"SansSerif"                       
                        lbl.setFont(fontlabel);
                       
                       eachDataFieldPanel.setOpaque(true);
                        eachDataFieldPanel.setBackground(Color.WHITE);
                        Border borderTextField = BorderFactory.createLineBorder(CLR_PANEL_BORDER, 1);
                       // .setBorder(borderPanel);
                        tb.setBorder(borderTextField); 
                      // System.out.println("PanelODORData.setEntity columnClass "+columnClass+"   columnDbName:"+columnDbName+"   i:"+i+"   lbl.getText():"+lbl.getText()); 
                        if(lbl.getText().equalsIgnoreCase("000:")) // when is hidden  // hidden must be java.lang.String
                        {
                            lbl.setForeground(eachDataFieldPanel.getBackground());//Color.WHITE);
                            Border borderTextNotField = BorderFactory.createLineBorder(eachDataFieldPanel.getBackground(), 1);
                            tb.setBorder(borderTextNotField);
                            tb.setOpaque(false);
                           // tb.setBackground(eachDataFieldPanel.getBackground());
                        }
                    }
                    
                  if((columnDbName!=null && columnClass.equalsIgnoreCase("java.lang.Integer")) || (columnDbName!=null && columnClass.equalsIgnoreCase("java.lang.Double")) )      
                  {
                        tb.setHorizontalAlignment(JTextField.RIGHT);
                        fieldTxts.set(i,tb);
                        eachDataFieldPanel.add(tb);
                        
                    
                      tb.setDocument(new PlainDocumentInsertText(columnWidth,columnClass));//limiting the capacity of txt	
                    final JTextComponent jtc = tb;
                    final int finalIntColumn = i;
                    jtc.addFocusListener(new FocusListener()  // look in showRow for focus gained in tb
                    {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                                  
                               System.out.println("PanelODORData.dbFieldsCalculateSet ("+finalIntColumn+")  -columnDbName:"+columnDbName+"-     hasDataChanged:"+hasDataChanged+"  columnClass:"+columnClass);
                              dbFieldsCalculateSet(dbFieldsInGroupOfPanels,finalIntColumn,"");
                              checkFieldsIfThenElse(finalIntColumn,CHECK_ON_ENTRY);
                        }

                    	public void focusGained(FocusEvent e)
                        {  // if entered nothing or spaces on textbox but get focus
                             jtc.setSelectionStart(0);
                             jtc.setSelectionEnd(jtc.getText().length());                            
                            //System.out.println("PanelODORData.setEntity got focus");
                            //jtc.setBackground(Color.white);
                            //jtc.remove(lblIcoAttention);
                            //jtc.revalidate();
                            
                        }
                    });                        
                        
                        
                  }                  
                  else if((columnDbName!=null && columnClass.equalsIgnoreCase("java.sql.Date")) || (columnDbName!=null && columnClass.equalsIgnoreCase("java.lang.Date")))
                  {
                  	int intColumnWidthDate = 8;//utilsDate.getDateFormatEditing().length();
                    //JxFormattedTextFieldDate.FormatSpec formatSpec = new JxFormattedTextFieldDate.FormatSpec("__-__-____", "**-**-****");
                    lbl.setText(columnLabel+":("+KEYSTROKE_F_LOOKUP_SHOW+")");
                    
                    //JxFormattedTextFieldDate textFormatedDate = new JxFormattedTextFieldDate(intColumnWidthDate,formatSpec);
                    
                    

    
     JFormattedTextField textFormatedDate = new JFormattedTextField();
     //textFormatedDate.setPreferredSize(new Dimension(80,textFormatedDate.getHeight()));                    
                    
                    
                    //fieldTxtsDate.add(textFormatedDate);
                    //intDateFormatCount = intDateFormatCount+1;
                    JTextBoxWithEditButtons txtWithBtnDate = new JTextBoxWithEditButtons(textFormatedDate, true,ICO_CALENDAR,null , false,null,null,LOOKUP_TYPE_DATE, frame,VariablesGlobal.globalDateFrom,VariablesGlobal.globalDateTo,MONTH_DATE_ONLY);                  	
         //         txtWithBtnDate.setDocument(new PlainDocumentInsertText(columnWidth,columnClass));//limiting the capacity of txt                    
                    //tf.setText(tb.getText());
                    //tb = (JComponent)txtdt.getComponent();
                    
                    // txtdate is added in fieldTxts array because it can be casted in rowUpdate but lookup cannot
                    eachDataFieldPanel.add(txtWithBtnDate.getComponent());
                    
                   
                    
                    
                    fieldTxts.set(i,txtWithBtnDate.getComponent());
                    //System.out.println("PanelODORData.setEntity size "+fieldTxts.size());
                  
                   if(!txtWithBtnDate.isVisible() ) // add doc handler so if there is change trigger save
                   {    }
                   else
                   {
                   	  txtWithBtnDate.getDocument().addDocumentListener(new DocumentHandler(i,0,columnClass, null,columnDbName)); 
                   }  
                  
                  }
          	  else if (columnDbName!=null && columnClass.equalsIgnoreCase("java.lang.Boolean"))
          	  {
                      
          	      	JCheckBox chk = new JCheckBox(columnLabel);
          	       chk.setOpaque(false);
                       tb = new JTextField(columnWidth);
          	      	//chk.setSelected(true);
                   eachDataFieldPanel.add(chk);
                   fieldTxts.set(i,chk); 
                   final JTextField finalTb = tb;
                   chk.addItemListener(new ItemListener()
                   {
                     public void itemStateChanged(ItemEvent e)
                     {
                       //JCheckBox source = (JCheckBox)e.getSource();

                         if(e.getStateChange() == ItemEvent.SELECTED)
                         {
                         	finalTb.setText("1"); // true
                         }
                         else
                         {
                         	finalTb.setText("0");// false
                         }
                     }

                    });

                    
                   	  tb.getDocument().addDocumentListener(new DocumentHandler(i,0,columnClass, null,columnDbName)); 
                           	    	
          	  }     //  java.lang.String//     html             
                  else if(columnDbName!=null && columnWidth>COLWIDTH_FOR_BIGTEXTBOX)
                  {

                 	JPanel panelTextArea =new JPanel(new BorderLayout());
                  	panelTextArea.setOpaque(false);
                        
                        //System.out.println("  panelHtmlEditor  i:"+i+"  columnLabel:"+columnLabel+"  columnWidth:"+columnWidth);
                      
                      
                        if(columnWidth>COLWIDTH_FOR_HTML) // like printform html // look also above
                        {

                         PanelHtmlEditor panelHtmlEditor = new PanelHtmlEditor();
                         panelHtmlEditor.setPreferredSize(new Dimension(670,480));
                   eachDataFieldPanel.add(panelHtmlEditor);
                   //System.out.println("  panelHtmlEditor  i:"+i+"  columnLabel:"+columnLabel);
                                            
                   /* panelHtmlEditor.addFocusListener(new FocusListener()  
                    {
                    	public void focusLost(FocusEvent e)
                        { */ 
            /* if(e.getComponent() instanceof JEditorPane)
            {
                JEditorPane ed = (JEditorPane)e.getComponent();
                textArea.setText(ed.getText());
                
            }*/                            
                                  
                     /*      textArea.setText(panelHtmlEditor.getTextAreaString());
                        }

                    	public void focusGained(FocusEvent e)
                        {  

                            panelHtmlEditor.setTextAreaString(textArea.getText());
                        }
                    });  */                       
                    
                    /*panelHtmlEditor.addMouseListener(new MouseListener()
                    {
                        public void mousePressed(MouseEvent me)
                        { 
                        }
                        public void mouseReleased(MouseEvent me)
                        { 
                        }
                        public void mouseEntered(MouseEvent me)
                        { 
                          panelHtmlEditor.setTextAreaString(textArea.getText());
                        }
                        public void mouseExited(MouseEvent me)
                        { 
                             textArea.setText(panelHtmlEditor.getTextAreaString());
                        }                    
                        public void mouseClicked(MouseEvent me)
                        { 
                        }                    
                    });*/
                    
             /*               JLabel lblArea = new JLabel();
                            lblArea.setBackground(Color.white);
                            lblArea.setOpaque(true);

                            panelHtmlEditor.setFocusable(true);
                        
                             JTextComponent txtFieldArea = (JTextComponent)fieldTxts.get(i);   //this is for html
                           final JTextComponent txtArea = txtFieldArea;
            panelHtmlEditor.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent arg0)
            {
                System.out.println("panel focus");
                 
              panelHtmlEditor.setTextAreaString(txtArea.getText());
              panelHtmlEditor.revalidate();                
            }
 
            @Override
            public void focusLost(FocusEvent arg0) 
            {
                // TODO Auto-generated method stub
                 txtArea.setText(panelHtmlEditor.getTextAreaString());  
            }
             
        });        */                    
                            
          panelHtmlEditor.requestFocusInWindow();
                            
                            
                            
                            
                            


              
              
 
              
              
           

                            
  ///                       JxPanel pnlPreview = new JxPanel();
                         //pnlPreview.setLayout(new BorderLayout());
                         //pnlPreview.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
   //                      pnlPreview.setLayout(new BorderLayout());//new BoxLayout(pnlPreview, BoxLayout.LINE_AXIS));
                        //  pnlPreview.setLayout(new FlowLayout());
          //               pnlPreview.add(scrollPaneTextArea);
    //                      pnlPreview.add(pnlButtons,BorderLayout.PAGE_START);
    //                      pnlPreview.add(panelHtmlEditor,BorderLayout.CENTER);
                           


                            
                            
/*                             JxPanel panelRadios = new JxPanel();
        //panelRadios.setBorder(new TitledBorder(""));
        panelRadios.setLayout(new BoxLayout(panelRadios, BoxLayout.LINE_AXIS));

        final JScrollPane  finJscrlPanel = scrollPaneTextArea;
        //final JTextArea finalTextAreaHtml  = textAreaHtml;
        //final JxPanel finPnlPre = pnlPreview;
        final PanelHtmlEditor  finPanelHtmlEditor  = panelHtmlEditor;
        final JLabel finLblArea = lblArea;
       JRadioButton radioText = new JRadioButton("επεξεργασία κώδικα");
        radioText.addActionListener(new ActionListener()
       {       	
          public void actionPerformed(ActionEvent e)
          {
                           //finJscrlPanel.setText(getText());
                           finJscrlPanel.setVisible(true);
                           finPanelHtmlEditor.setVisible(false);
                            finLblArea.setVisible(false);
                            
                           // finPanelHtmlEditor.setHtmlText(textArea.getText());
          	
          }
       });     


 
       	
       	//radioPreview.setSelected(true); 
        radioText.doClick();
        //radioPreview.doClick();
        panelRadios.add(radioText);
        panelRadios.add(radioHtml);
        panelRadios.add(radioPreview);      
                            
                            
                           // JTabbedPane panelTab = new JTabbedPane();
                           // panelTab.add("text", scrollPaneTextArea);
                           // panelTab.add("preview html", lblArea);
                            
                            

      
      ButtonGroup groupSelectTextOrPreview = new ButtonGroup();
      groupSelectTextOrPreview.add(radioText);
      groupSelectTextOrPreview.add(radioHtml);
       groupSelectTextOrPreview.add(radioPreview);                          
                            
                            //if(panelTab.getSelectedIndex()==1)
                           // {
 */                             
                            
                            
               //             panelTextArea.add(panelRadios, BorderLayout.PAGE_START);
                            
               //             panelTextArea.add(panelHtmlEditor,BorderLayout.PAGE_START);
                            panelTextArea.add(panelHtmlEditor, BorderLayout.CENTER);//.LINE_START);
                            panelTextArea.setBackground(Color.DARK_GRAY);
                            fieldTxts.set(i,panelHtmlEditor);
          //-                  fieldTxts.set(i,textArea);//this is for html
                        }
                        else
                        {
                            scrollPaneTextArea.setViewportView(textArea);
                            panelTextArea.add(scrollPaneTextArea);
                            fieldTxts.set(i,textArea);
                        }
                        
                  	
                  	eachDataFieldPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
                  	eachDataFieldPanel.add(panelTextArea);
                    
                     //eachDataFieldPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
                  }
                  else if(columnDbName!=null && columnClass.equalsIgnoreCase("table"))
                  {
                   //eachDataFieldPanel.add(tb);
                   //fieldTxts.set(i,tb);                      
                  }   
                 else if(columnDbName!= null && columnClass.equalsIgnoreCase("htmlfile"))
                 {
                     
                 }                  
                  else if(columnDbName!=null && columnClass.equalsIgnoreCase("java.awt.image.BufferedImage"))
                  {
                   //eachDataFieldPanel.add(tb);
                   //fieldTxts.set(i,tb);                      
                  }                    
                  else if(columnDbName!=null)
                  {
                       eachDataFieldPanel.add(tb);                   
                   fieldTxts.set(i,tb);

                      if(columnFieldValidation==FIELD_VALIDATION_AFM)
                      {
                      	eachDataFieldPanel.add(btnCheckValid);
                      	listButtonValid.set(i,btnCheckValid);
                        
                      }
                      else
                      {
                      	
                      }                    
                   
                      /*if(columnClass.equalsIgnoreCase("java.awt.image.BufferedImage"))
                      {
                      	eachDataFieldPanel.add(lblCheckValid);
                      	listLabelValid.set(i,lblCheckValid);                          
                          
                      }
                      else
                      {
                          
                      }*/
                   
                   if(!tb.isVisible())// add doc handler so if there is change trigger save
                   {  
                   
                     //System.out.println("error  PanelODORData.setEntity ("+i+") tb.isVisible():"+tb.isVisible()+"   "+columnDbName+"  "+columnWidth+" "+columnClass);
                    
                   }
                   else
                   {  
                   	  tb.getDocument().addDocumentListener(new DocumentHandler(i,0,columnClass, null,columnDbName)); 
                   }                     
                   // eachDataFieldPanel.add(tb);
                    //eachDataFieldPanel.add(tb2);
                  }                    
                  else if (columnDbName==null)  
                  {
                      System.out.println("error  PanelODORData.setEntity ("+i+")  "+columnDbName+"  "+columnWidth+" "+columnClass);	
                  }   
                  else
                  {
                      System.out.println("error  PanelODORData.setEntity ("+i+")  "+columnDbName+"  "+columnWidth+" "+columnClass);	
                  }
                   //eachDataFieldPanel.add(tb);
     

                } // else
                  

                   /*    System.out.println("PanelOneDataOnRecData.setEntity     --("+i+")--    "+fields[i]+"     isVisibleOrEditable[i]"+isVisibleOrEditable[i]);
                       //exists both in rowNew and setEntity. The reason is that it has to be called when is already in edit row and would like to make a new row
                       if(isVisibleOrEditable[i]==FIELD_VISIBLE_NOT_EDITABLE_ALWAYS)
                       {
                              if (columnClass.equalsIgnoreCase("java.lang.Boolean"))
                              {
          	  	        //System.out.println("PanelOneDataOneRecData.showRow boolean "+field);
              	                JCheckBox chk = (JCheckBox) fieldTxts.get(i);//i-1);
              	              
         	  	        chk.setEnabled(false);
                              }    
                              else if(columnClass.equalsIgnoreCase("java.sql.Date") || columnClass.equalsIgnoreCase("java.lang.Date"))
                              {

           	                 JTextBoxWithEditButtons textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(i);
                                 textEditFormatedDate.setEnabled(false);
                              }
                              else if(columnClass.equalsIgnoreCase("table"))
                              {
                                  
                              }
                              else 
                              {                           
                                  JTextComponent txtb = (JTextComponent)fieldTxts.get(i);//i-1);
                      	          txtb.setEditable(false);
                                  txtb.setFocusable(false);
                              }
                       }
                       else
                       {
                              if (columnClass.equalsIgnoreCase("java.lang.Boolean"))
                              {
          	  	         //System.out.println("PanelOneDataOneRecData.showRow boolean "+field);
              	                  JCheckBox chk = (JCheckBox) fieldTxts.get(i);//i-1);
              	              
         	  	         chk.setEnabled(true);
                              }     
                              else if(columnClass.equalsIgnoreCase("java.sql.Date") || columnClass.equalsIgnoreCase("java.lang.Date"))
                              {

           	                 JTextBoxWithEditButtons textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(i);
                                 textEditFormatedDate.setEnabled(true);
                              }      
                              else if(columnClass.equalsIgnoreCase("table"))
                              {
                                  
                              }                              
                              else 
                              {                             
                                       JTextComponent txtb = (JTextComponent)fieldTxts.get(i);//i-1);
                      	               txtb.setEditable(true);
                                       txtb.setFocusable(true);
                              }                            
                       }          */      
                
                
                if(isVisibleOrEditable[i]==FIELD_NOT_VISIBLE)
                {
                     if(VariablesGlobal.globalFieldIsVisibleWhenSetNotVisible) //  show
                     {
                         System.out.println("PanelODORData.setEntity isVisibleOrEditable[i]:"+isVisibleOrEditable[i]+"  columnDbName:"+columnDbName);  
                    eachDataFieldPanel.setVisible(true);
                    lbl.setVisible(true);                         
                     }
                     else // not show
                     {
                    eachDataFieldPanel.setVisible(false);
                    //eachDataFieldPanel.removeAll();
                    lbl.setVisible(false);                           
                     }
                }
                
                
                   //System.out.println("PanelODORData.setEntity     groupOfComps:"+groupOfComps);
                  if(entityGroupOfComps == null)
                  {
                  	
                     if(isVisibleOrEditable[i]==FIELD_NOT_VISIBLE)
                     {
                       if(VariablesGlobal.globalFieldIsVisibleWhenSetNotVisible) // do not show
                       {
                          dataPanel.add(lbl);
                         dataPanel.add(eachDataFieldPanel);                            
                       } 
                       else
                       {
                      
                       }
                     }
                     else
                     {
                         dataPanel.add(lbl);
                         dataPanel.add(eachDataFieldPanel); 	
                     }

                  }
                  else
                  {
                  	// System.out.println("PanelODORData.setEntity     groupOfComps.length:"+groupOfComps.length+"  "+(i)+"   "+groupOfComps[i]);
                  	 if(groupOfComps[i]==-1)
                  	 {  // if has -1 in groupOfComps not show
                  	 	
                  	 }
                  	 else
                  	 {
                   // System.out.println("PanelODORData.setEntity "+groupOfComps.length+" "+(i)+" "+groupOfComps[i]);
       // PanelCollapsable pcl = new PanelCollapsable(panelGroup,pnl, /*"panel first"*/ entityGroupOfComps[gc].getCaption(), true,CLR_PANEL_END,this.getBackground());//,CLR_PANEL_END); //this.getBackground(),
 
                 
                             
                if(dbFieldsInGroupOfPanels[i].getChildTableInPosition()==CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL)
                 {
                     
                  	        JxPanel panelGroup = (JxPanel)listPanelGroups.get(groupOfComps[i]);
                                 //panelGroup.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
                                // lbl = new JLabel(columnLabel);
                                lbl.setHorizontalAlignment(SwingConstants.CENTER);
                  	        panelGroup.add(lbl);
                  	        panelGroup.add(eachDataFieldPanel);  
                        // System.out.println("PanelODORData.setEntity ChildTableInPosition ("+i+") A' columnDbName:"+columnDbName);        
                         if(isTheOnlyGroupPanelAfterThePanelJTableSizable)
                         {
                         //PanelCollapsable pcl = new PanelCollapsable(panelGroup,pnl,  columnLabel, true,CLR_PANEL_END,this.getBackground());//,CLR_PANEL_END); //this.getBackground(),
                           
                         //panelAllOnIt.add(pcl,BorderLayout.PAGE_END);//
                           
                            panelAllOnIt.add(panelGroup,BorderLayout.PAGE_END);// 
                             //System.out.println("PanelODORData.setEntity ChildTableInPosition ("+i+") A' IF  columnDbName:"+columnDbName);  
                            isTheOnlyGroupPanelAfterThePanelJTableSizable=false;
                            
                         }
                         else
                         {
                             //System.out.println("PanelODORData.setEntity ChildTableInPosition  NOT defined ("+i+") A' else  columnDbName:"+columnDbName); 
                         }
                                
                         
                 }
                 else if(dbFieldsInGroupOfPanels[i].getChildTableInPosition()==CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE)
                 {// if CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE then placed in bottom of form
                                 JxPanelCollapsable panelGroup = (JxPanelCollapsable)listPanelGroups.get(groupOfComps[i]);
                                 
 
                                 panelGroup.setLayout(new BorderLayout());
                                 
                                 
                    
                                panelGroup.add(pnlIncludeJTableAsInsideBorderLayoutSizable,BorderLayout.CENTER);
                     
                     panelAllOnIt.add(panelGroup,BorderLayout.CENTER);// pnlIncludeJTableAsInsideBorderLayoutSizable
                    
  
                        
                         isTheOnlyGroupPanelAfterThePanelJTableSizable=true;
                 }
                 else
                 {
                  	        JPanel panelGroup = (JPanel)listPanelGroups.get(groupOfComps[i]);
                                 //panelGroup.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
         
                  	        panelGroup.add(lbl);
                  	        panelGroup.add(eachDataFieldPanel); 
                            
                     System.out.println("PanelODORData.setEntity ChildTableInPosition ("+i+") C' NOT DEFINED:"+dbFieldsInGroupOfPanels[i].getColClassName()+" "+dbFieldsInGroupOfPanels[i].getChildTableInPosition()+" "+dbFieldsInGroupOfPanels[i].getCaption());
                 }
                               
	
                  	 }

                  	            	
                  }
                            
              }// for each field
                     
                         //"fieldTxts.size()"+fieldTxts.size()+" ");                         //"fieldTxts.size()"+fieldTxts.size()+" ");
                               
          if(!isNewRec)
          {
             showSpecificRow(selectedRow,query);     // }	
          }     


   }
   
  /*
   *
   *  called by 
   */ 
   
private void imageLoadFromFile(JLabel lbl)
{
try
{

JFileChooser chooser=new JFileChooser(new File(System.getProperty("user.dir")));//"C:\\"));
 


        //JFileChooser chooser;
        FileNameExtensionFilter filter;
        //chooser = new JFileChooser();
        //chooser.setCurrentDirectory(image);
        filter = new FileNameExtensionFilter("jpeg, gif and png files", "jpg", "gif", "png");
        chooser.addChoosableFileFilter(filter);




chooser.setMultiSelectionEnabled(false);
chooser.setVisible(true);

chooser.showOpenDialog(this);
  lbl.setVerticalTextPosition(JLabel.BOTTOM);
  lbl.setHorizontalTextPosition(JLabel.CENTER);
File file=chooser.getSelectedFile();
if(file!=null){filePath=file.getPath();}
if(filePath!=null)
{
  lbl.setText(filePath);

  lbl.setIcon(new ImageIcon(filePath));
}


/*if(filePath!=null && check())
{
ps=connection.prepareStatement("insert into image values(?,?)");
FileInputStream fileInputStream=new FileInputStream(filePath);
byte b[]=new byte[fileInputStream.available()];
fileInputStream.read(b);
fileInputStream.close();
ps.setObject(1, jTextField1.getText());
ps.setBytes(2, b);
int val=ps.executeUpdate();
if(val>=1)JOptionPane.showMessageDialog(this, "Succesfully loaded...");
else
JOptionPane.showMessageDialog(this, "Error in storage...");

}
else
{
  JOptionPane.showMessageDialog(this,"Please select an Image of type .jpeg/gif/jpg...");
}
*/
}
catch(Exception e)
{

  //JOptionPane.showMessageDialog(this, e.getMessage());
  utilsGui.showMessageError(e.getMessage());
  e.printStackTrace();
}
}
   

/*private boolean check() 
{
  if(filePath!=null)
  {
     if(filePath.endsWith(".jpeg")||filePath.endsWith(".gif")||filePath.endsWith(".jpg")||filePath.endsWith(".JPEG")||filePath.endsWith(".GIF")||filePath.endsWith(".JPG"))
     {
          return true;
     }
     return false;
  }
  return false;
}*/



   /*
   * exists in tableModelResultSet.dbFieldsCalculateSet (tableModelWritable.setValue) and PanelODORData.dbFieldsCalculateSet 
   *  makes the calulation between fields
   * Do NOT use here closeDB() because affects showRow(closes rs of showRow) This does not leave any dbconnections open.
   */
   private void dbFieldsCalculateSet(EntityDBFields[] dbFieldsInGroupOfPanels,int col,String foreignTableIn)
   {
        //System.out.println("PanelODORData.dbFieldsCalculateSet CALLED  hasDataChanged:"+hasDataChanged);
          //int fieldCount = i;//i-1; // calculates the no of field starting from 0 when i = 1
          String colName =   dbFieldsInGroupOfPanels[col].getDbField();
          int columnWidth = dbFieldsInGroupOfPanels[col].getColWidth();
         //System.out.println("panelODORData.dbFieldsCalculateSet   --i--   ("+col+") colName:"+colName+" class:"+dbFieldsInGroupOfPanels[col].getColClassName());
          String classtype = dbFieldsInGroupOfPanels[col].getColClassName(); 
          //System.out.println("panelODORData.dbFieldsCalculateSet   --i--   ("+col+") colName:"+colName+" classtype:"+classtype+"  columnWidth:"+columnWidth+"    hasDataChanged:"+hasDataChanged);
       String value ="";
               if(classtype.equalsIgnoreCase("java.sql.Date") || classtype.equalsIgnoreCase("java.lang.Date"))
              {

           	  JTextBoxWithEditButtons textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(col);

                  
                  JTextComponent ta = (JTextComponent)textEditFormatedDate.getTextComp();
                  value = ta.getText();
           	 
              }
              else if (classtype.equalsIgnoreCase("java.lang.Boolean"))
              {
          	  	   //System.out.println("PanelOneDataOneRecData.showRow boolean "+field);
              	   JCheckBox chk = (JCheckBox) fieldTxts.get(col);//i-1);


              	   if(chk.isSelected())
              	   {
              	   	  value = "true";
              	   }
              	   else if(!chk.isSelected())
              	   {
              	   	  value="false";
              	   }
              	   else
              	   {
              	   	  System.out.println("PanelOneDataOneRecData.dbFieldsCalculateSet  boolean unknown value col:"+col+"  colName:"+colName+"     "+chk.isSelected());
              	   }
              	   
              	           	  	
              }              
              else if (classtype.equalsIgnoreCase("table"))
              {
                  value=null;
              }
              else if(classtype.equalsIgnoreCase("htmlfile"))
              {
                     
              	   PanelHtmlBrowser panelHtmlBrowser = (PanelHtmlBrowser) fieldTxts.get(col);
                           //                PanelHtmlBrowser panelHtmlBrowser = new PanelHtmlBrowser();
                        panelHtmlBrowser.setVisible(true);
                        panelHtmlBrowser.loadURL(periodiki);
                          //panelHtmlBrowser.loadURL("www.businesseye.gr");
     //         	   value = tb.getText();                     
     
              }              
              else if(dbFieldsInGroupOfPanels[col].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)             
              {
 
                   JComboBox cmbBox =  (JComboBox) fieldTxts.get(col);
                    value =  cmbBox.getSelectedItem().toString();
              }
              else if(classtype.equalsIgnoreCase("java.awt.image.BufferedImage"))
              {
                  System.out.println("   -    ?  PanelOneDataOneRecData.dbFieldsCalculateSet   ("+col+")   colName:"+colName+"   classtype:"+classtype);
              }
              else if (classtype.equalsIgnoreCase("java.lang.String")) // like printform html // look also above
              {
                   //System.out.println("PanelOneDataOneRecData.dbFieldsCalculateSet String ("+col+")  colName:"+colName+"   classtype:"+classtype);
                  if(columnWidth>COLWIDTH_FOR_HTML)
                  {
                         PanelHtmlEditor panelHtmlEditor = new PanelHtmlEditor();
                         value=panelHtmlEditor.getText();
                  }
                  else
                  {
                           JTextComponent tb = (JTextComponent) fieldTxts.get(col);
              	           value = tb.getText();                      
                  }
              }
              else
              {
                   
                           JTextComponent tb = (JTextComponent) fieldTxts.get(col);
              	           value = tb.getText();
                           //System.out.println("PanelOneDataOneRecData.dbFieldsCalculateSet else ("+col+")  colName:"+colName+"   classtype:"+classtype+"        value:"+value);
              }
               
               
        EntityDBFields dbField = dbFieldsInGroupOfPanels[col];
        // boolean isCalculated = dbField.getFollowingCalculationOrUpdate();// followingCalculationOrUpdateIn: boolean if true then calculation or if false then update
        
        //EntityDBFieldsCalculation[] fieldsCalculationUpdate = dbField.getFieldsCalculationUpdate(); 
        EntityDBFieldsCalculation[] fieldsCalculationSelect = dbField.getFieldsCalculationSelect();
        
        
      //System.out.println("PanelODORData.dbFieldsCalculateSet  -o-o-oo--o--o-o--o-o-o   col:"+col+" -   caption:"+dbField.getCaption()+"   value:"+value);
        if(dbField!=null && fieldsCalculationSelect!=null  && value!=null && !value.toString().equalsIgnoreCase(""))
        {
        try
        {
           for(int fc=0; fc<fieldsCalculationSelect.length;fc++)
           {
              
        	int[] textsCategoryInput = fieldsCalculationSelect[fc].getCalculationInputCategoryFields();
                int[] textsInput = fieldsCalculationSelect[fc].getCalculationInputFields();
        	String calculation =  fieldsCalculationSelect[fc].getCalculation();
                int calculateCategoryField =  fieldsCalculationSelect[fc].getWhenSetThenCalculateCategoryField();
                int calculateField =  fieldsCalculationSelect[fc].getWhenSetThenCalculateField();                
               
                int lengthOfArrayInputFields = 0;
             
             // if is null then insert rubish data. If there is no character # in calulation there is no problem 
             if(textsInput==null || textsInput.length==0)
             {
                 //System.out.println("PanelODORData.dbFieldsCalculateSet textsInput:"+textsInput+"   for  fc:"+fc+"   calculation:"+calculation);
                 textsInput = new int[1];
                 textsInput[0] = 1;
                textsCategoryInput = new int[1];
                textsCategoryInput[0]  = FIELDSCALCULATION_CATEGORY_SAME;
             }
             
                for(int c=0;c<textsInput.length;c++)
                {
                    
                  if(textsCategoryInput[c]  == FIELDSCALCULATION_CATEGORY_SAME || textsCategoryInput[c]  == FIELDSCALCULATION_CATEGORY_BACKWARD)
                  {                 
                    lengthOfArrayInputFields++;
                  
                  }
                }                
                 
               
               ArrayList listTextStringHasTablesContainer = new ArrayList();
               String[] textString = new String[lengthOfArrayInputFields];  
               String[]textStringTable = null;
               
        	//int[] textsInput = fieldsCalculation[fc].getCalculationInputFields();
        	//String calculation =  fieldsCalculation[fc].getCalculation();
        	String val = "";    
                
                //calculation= calculation+value;               
                boolean isCalculationInputHasTable = false;
             
                 
                for(int c=0;c<textsInput.length;c++)
                {
                    if(textsCategoryInput[c]  == FIELDSCALCULATION_CATEGORY_SAME || textsCategoryInput[c]  == FIELDSCALCULATION_CATEGORY_BACKWARD)
                    {
                        
                    }
                    else//is table , textsCategoryInput[c]
                    {
                         isCalculationInputHasTable=true;
                         //break;
                    }
                }
              
                
               //ArrayList listTextStringForRow = new ArrayList();
                ArrayList listTextString = new ArrayList();
                for(int c=0;c<textsInput.length;c++)
                {
                    if(isCalculationInputHasTable)
                    {
                      // System.out.println("PanelODORData.dbFieldsCalculateSet OooO..   col:"+col+"  c:"+c+"  textsCategoryInput[c]:"+textsCategoryInput[c]+"   lengthOfArrayInputFields:"+lengthOfArrayInputFields+" sql calculation:"+calculation); 
                       
                      if(textsCategoryInput[c]  == FIELDSCALCULATION_CATEGORY_SAME || textsCategoryInput[c]  ==FIELDSCALCULATION_CATEGORY_BACKWARD)
                      {
                       if(dbFieldsInGroupOfPanels[textsInput[c]].getLookupType()==LOOKUPTYPE_ONLYONE_THISFIELD)
                       {
                          JTextComponent tbToGet = (JTextComponent)fieldTxts.get(textsInput[c]);
                          textString[c] = tbToGet.getText(); 
                          listTextString.add(textString[c]);
                       }
                       else if (dbFieldsInGroupOfPanels[textsInput[c]].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)
                       {  // showRow
                         LookupTableConstantsMgt lookUpTableConstants = new LookupTableConstantsMgt();
                         EntityLookupTableConstantsData[] elutcData = lookUpTableConstants.getEntityLookupTableConstantsData(dbFieldsInGroupOfPanels[textsInput[c]].getLookupEntityName());
              
              	            JComboBox cb = (JComboBox) fieldTxts.get(textsInput[c]);
                            
                            /*for(int e=0 ;e<elutcData.length;e++)
                            {
                                if()
                                {*/
                             //System.out.println("PanelOneDataOneRecData.dbFieldsCalculateSet length:"+textString.length);
                          textString[c] = elutcData[cb.getSelectedIndex()].getPk();
                          listTextString.add(textString[c]);
                               // }
                          //System.out.println("       -       PanelOneDataOneRecData.dbFieldsCalculateSet   selection   LOOKUPTYPE_TABLECONSTANTS    c:"+c+"     fc:"+fc+"     index:"+cb.getSelectedIndex()+"  textsInput[c]:"+textString[c]+" isCalculationInputHasTable:"+isCalculationInputHasTable);
                            
  
                       }
                       else 
                       {
                           // LOOKUPTYPE_NOLOOKUP   is 0
                           System.out.println("  error PanelOneDataOneRecData.dbFieldsCalculateSet  isCalculationInputHasTable:"+isCalculationInputHasTable+"    c:"+c+"    textsCategoryInput:"+textsCategoryInput[c]+" (-1 is backwards) textsInput[c]:"+textsInput[c]+" UNKNOWN  getLookupType:"+dbFieldsInGroupOfPanels[textsInput[c]].getLookupType()+" no look up is 0 ");
                         // JTextComponent tbToGet = (JTextComponent)fieldTxts.get(textsInput[c]);
                         // textString[c] = tbToGet.getText(); 
                         // listTextString.add(textString[c]);
                            
                       }                             
                       
                        //cellString[c]= getValueAt(row,textsInput[c]).toString();
                          //-    closeDB();
                     
                      //System.out.println("PanelODORData.dbFieldsCalculateSet  inside isCalculationHasTable =OOOOOOOOOO=  c:"+c+" "+textString[c]+"   FIELDSCALCULATION_CATEGORY_SAME");
                      }
                      else//is table , textsCategoryInput[c]
                      {
                          
                        //System.out.println("  PanelODORData.dbFieldsCalculateSet  else  c:"+c+" "+textString);
                                                    //JTextComponent tbToGet = (JTextComponent)fieldTxts.get(c);
                                                    //JTextComponent tbToGet1 = (JTextComponent)fieldTxts.get(c);
                          
                          //System.out.println("PanelODORData.dbFieldsCalculateSet  inside isCalculationHasTable   ELSE  =OOOOOOOOOO=  c:"+c+"    textsCategoryInput:"+textsCategoryInput[c]+"    fieldTxts.size"+fieldTxts.size()+"   textsInput[c]:"+textsInput[c]+" getGroupOfComps:"+dbFieldsInGroupOfPanels[c].getGroupOfComps()+"  (textsInput[0]:"+textsInput[0]+"   textsInput[1]:"+textsInput[1]+"   textsInput[2]:"+textsInput[2]+")   textsInput.length"+textsInput.length+"  sql calculation:"+calculation);

                          PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(textsCategoryInput[c]);
                          ArrayList listTextStringTableRow = new ArrayList();
                         for(int r = 0;r<pnlODMRData.getRowCount();r++)
                         {
                           // be careful beacuse we may have  more than one table

                            //System.out.println("PanelODORData.dbFieldsCalculateSet  table r("+r+")  c:"+c+"   textsInput[c]:"+textsInput[c]+"     "+pnlODMRData.getTableValueAt(r, textsInput[c])+"       textsInput[c]:"+ textsInput[c] +" for category:"+ textsCategoryInput[c]);
                            //textStringTable[r] = pnlODMRData.getTableValueAt(r, textsInput[c]);
                             String valueFromTable = pnlODMRData.getTableValueAt(r, textsInput[c]);
                         
                         // when is double it might be a double with comma not with dot which is what database handles, so we convert it to double
                         if(dbFieldsInGroupOfPanels[textsInput[c]].getColClassName().equalsIgnoreCase("java.lang.Double")) 
                         {
                             valueFromTable=utilsDouble.getDoubleSaving(valueFromTable);
                         }
                         else
                         {
                            
                         }                                 
                            listTextStringTableRow.add(valueFromTable);
                    //        System.out.println("PanelODORData.dbFieldsCalculateSet  table r("+r+")  c:"+c+"   textsCategoryInput[c]:"+ textsCategoryInput[c]+ "     textsInput[c]:"+textsInput[c]+" valueFromTable:"+valueFromTable);
                            //System.out.println("PanelODORData.dbFieldsCalculateSet  table r("+r+")  c:"+c+"   textsCategoryInput[c]+       textStringTable[c]:"+textStringTable[r][c]+"         textsInput[c]:"+ textsInput[c] +" for category:"+"    "+valueFromTable  );
                         }
                         //System.out.println("PanelOneDataOneRecData.dbFieldsCalculateSet add  listTextStringHasTablesContainer  textsCategoryInput[c]:"+textsCategoryInput[c]);
                         listTextStringHasTablesContainer.add(listTextStringTableRow);

                        }

                      
                     
                    }
                    else // no calculation of table
                    {
                        
                       if(dbFieldsInGroupOfPanels[textsInput[c]].getLookupType()==LOOKUPTYPE_ONLYONE_THISFIELD || dbFieldsInGroupOfPanels[textsInput[c]].getLookupType()==LOOKUPTYPE_NOLOOKUP)
                       {
                          JTextComponent tbToGet = (JTextComponent)fieldTxts.get(textsInput[c]);
                          textString[c] = tbToGet.getText();               
                       }
                       else if (dbFieldsInGroupOfPanels[textsInput[c]].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)
                       {  // showRow
                         LookupTableConstantsMgt lookUpTableConstants = new LookupTableConstantsMgt();
                         EntityLookupTableConstantsData[] elutcData = lookUpTableConstants.getEntityLookupTableConstantsData(dbFieldsInGroupOfPanels[textsInput[c]].getLookupEntityName());
              
              	            JComboBox cb = (JComboBox) fieldTxts.get(textsInput[c]);
                            
                            /*for(int e=0 ;e<elutcData.length;e++)
                            {
                                if()
                                {*/
                          textString[c] = elutcData[cb.getSelectedIndex()].getPk();
                               // }
                        //  System.out.println("PanelOneDataOneRecData.dbFieldsCalculateSet sel index:"+cb.getSelectedIndex()+"  textsInput[c]:"+textString[c]+" isCalculationInputHasTable:"+isCalculationInputHasTable);
                            
  
                      }
                      else 
                      {
                            System.out.println("  error PanelOneDataOneRecData.dbFieldsCalculateSet   isCalculationInputHasTable:"+isCalculationInputHasTable+"  textsInput[c]:"+textsInput[c]+"  ("+c+")   UNKNOWN   name:"+dbFieldsInGroupOfPanels[textsInput[c]].getDbField()+"   getLookupType:"+dbFieldsInGroupOfPanels[textsInput[c]].getLookupType());
                      }    
                       
                         //System.out.println("  error PanelOneDataOneRecData.dbFieldsCalculateSet  "+textsInput[c]+"   "+colName+"   textString[c]:"+textString[c]);
                         // when is double it might be a double with comma not with dot which is what database handles, so we convert it to double
                         if(dbFieldsInGroupOfPanels[textsInput[c]].getColClassName().equalsIgnoreCase("java.lang.Double") && (textString[c]!=null) && (!textString[c].equalsIgnoreCase("")) )
                         {
                             textString[c]=utilsDouble.getDoubleSaving(textString[c]);
                         }
                         else
                         {
                             System.out.println("  error PanelOneDataOneRecData.dbFieldsCalculateSet     c:"+c+"    dbFieldOfInput:"+dbFieldsInGroupOfPanels[c].getDbField()+"   textString[c]="+textString[c]+"   dbFieldOfInput:"+dbFieldsInGroupOfPanels[textsInput[c]].getDbField()+"     getColClassName():"+dbFieldsInGroupOfPanels[textsInput[c]].getColClassName());
                         }                        
                    }
                }
                //System.out.println("PanelODORData.dbFieldsCalculateSet c  hasDataChanged:"+hasDataChanged);
         // System.out.println("PanelODORData.dbFieldsCalculateSet    calculateCategoryField:"+calculateCategoryField+"    FIELDSCALCULATION_CATEGORY_SAME:"+FIELDSCALCULATION_CATEGORY_SAME);
             if(calculateCategoryField!=FIELDSCALCULATION_CATEGORY_SAME) // if not FIELDSCALCULATION_CATEGORY_SAME
             {
                 //System.out.println("PanelODORData.dbFieldsCalculateSet  ------  fc"+fc+"  calculateCategoryField:"+calculateCategoryField+"    FIELDSCALCULATION_CATEGORY_SAME:"+FIELDSCALCULATION_CATEGORY_SAME);
                  ArrayList listRowTextStringAll = new ArrayList();

                           //System.out.println("PanelODORData.dbFieldsCalculateSet table col:"+col+" c:"+c+"  listTextStringHasTablesContainer.size"+listTextStringHasTablesContainer.size());
                         //System.out.println("PanelODORData.dbFieldsCalculateSet     //- if   before A for      size:"+listTextStringHasTablesContainer.size());
                         for(int t = 0;t<listTextStringHasTablesContainer.size();t++)
                         {
                             ArrayList listTextStringTableRow = (ArrayList)listTextStringHasTablesContainer.get(t);
                             PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(calculateCategoryField);
                        
                           //System.out.println("PanelODORData.dbFieldsCalculateSet   t:"+t+"    "+calculateCategoryField+"        listTextString:"+listTextString.size()+"  listTextStringHasTablesContainer:"+listTextStringHasTablesContainer.size());
                             
                             //for(int r= 0 ;r< listTextStringTableRow.size();r++)
                             //{                                   
                             //  System.out.println("PanelODORData.dbFieldsCalculateSet c:"+c+" r:"+r+"   "+listTextStringTableRow.get(r).toString());
                                //textStringAll[c]= listTextStringTableRow.get(r).toString();
                             
                             if(pnlODMRData.getRowCount()>0)
                             {
                               for(int r = 0 ;r<pnlODMRData.getRowCount();r++)
                               {
                                String[] textStringAll = new String[textsInput.length];
                                for(int c2 = 0;c2<textsInput.length;c2++)
                                {
 
                                    if(textsCategoryInput[c2]  == FIELDSCALCULATION_CATEGORY_SAME || textsCategoryInput[c2]  ==FIELDSCALCULATION_CATEGORY_BACKWARD)
                                    {
                                        for(int tr = 0 ; tr<listTextString.size(); tr++)
                                        { 
                                           textStringAll[c2]= listTextString.get(tr).toString();
                                         //  System.out.println("PanelODORData.dbFieldsCalculateSet (c2:"+c2+"   t:"+t+"    tr:"+tr+"    r:"+r+"     FIELDSCALCULATION_CATEGORY_SAME  textStringAll[c2]:"+textStringAll[c2]);
                                        }
                                    }
                                    else
                                    {
                                        //for(int tr =0 ;tr<listTextStringTableRow.size();tr++)
                                        //{ 
                                            textStringAll[c2]= listTextStringTableRow.get(r).toString(); 
                                           // System.out.println("PanelODORData.dbFieldsCalculateSet (c2:"+c2+")  t:"+t+"    r:"+r+"   ELSE  textStringAll[c2]:"+textStringAll[c2]); 
                                        //}  
                                    }
                                   // System.out.println("PanelODORData.dbFieldsCalculateSet table  -  c2:"+c2+"  t:"+t+"  r:"+r+"  textStringAll[c2]:"+textStringAll[c2]);
                                }
                                  listRowTextStringAll.add(textStringAll); 
                                  //System.out.println("PanelODORData.dbFieldsCalculateSet table ADD     textStringAll:"+textStringAll);
                               } 

                            }
                         }
                      //System.out.println("PanelODORData. //- if   before B for      size:"+listTextStringHasTablesContainer.size());
                         for(int t = 0;t<listTextStringHasTablesContainer.size();t++)
                         {
                             //ArrayList listTextStringTableRow = (ArrayList)listTextStringHasTablesContainer.get(t);
                             //PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(calculateCategoryField);
                        
                              PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(calculateCategoryField);
                             //System.out.println("PanelODORData.dbFieldsCalculateSet   t:"+t+"    calculateCategoryField:"+calculateCategoryField+"   rowcount:"+pnlODMRData.getRowCount()+"     listTextString:"+listTextString.size()+"  listTextStringHasTablesContainer:"+listTextStringHasTablesContainer.size()+"  hasDataChanged:"+hasDataChanged);                     
                             //ArrayList listTextStringTableRow = (ArrayList)listTextStringHasTablesContainer.get(t); 
                             if(pnlODMRData.getRowCount()>0)
                             {                             
                             for(int r= 0 ;r< pnlODMRData.getRowCount();r++)
                             {
                               //System.out.println("PanelODORData.dbFieldsCalculateSet -->     r:"+r+"   ");
                                //textStringAll[c]= listTextStringTableRow.get(r).toString(); 
                                 String[]  textStringAll = (String[])listRowTextStringAll.get(r);
                               /*for(int s =0;s<textStringAll.length;s++)
                               {
                                   System.out.println("PanelODORData.dbFieldsCalculateSet table  s:"+s+"    textStringAll:"+textStringAll[s]);
                               } */                     
                                String calculationEachRow = "";
                                int indexOfHashChar = calculation.indexOf("#");
                               
                               if(indexOfHashChar!=-1)
                               {    
                                calculationEachRow = utilsString.replaceTextOfAStringWithText("#", calculation, textStringAll, null);
                               }

                               //replaceTextOfAStringWithText(String textToBeReplacedBy, String str, String[] textToReplace)
                         //System.out.println("PanelODORData.dbFieldsCalculateSet table col:"+col+" table r=("+r+")  calculateField:"+calculateField+"   textStringAll:"+textStringAll.length+"       calculationEachRow:"+calculationEachRow);                                   
                         //System.out.println("PanelODORData.dbFieldsCalculateSet A  query"+calculation);
                                db.retrieveDBDataFromQuery(calculationEachRow,"PanelODORData.dbFieldsCalculateSet  A   ");
   	                        rs=db.getRS();
  	          
            
                                if (rs!=null && rs.first())
                                {
                                       rs = db.retrieveRow(rs, 1);// go to the only row  
                                        //System.out.println(PanelODORData.dbFieldsCalculateSet   calculation "+calculation);
                                       val = rs.getString(1)+"";// get field data	         	
                                }
                             
              //             System.out.println("PanelODORData.dbFieldsCalculateSet table col:"+col+"   table   r:("+r+")  calculateField:"+calculateField+"  val:"+val+"  calculationEachRow:"+calculationEachRow);
                               
                               pnlODMRData.setTableValueAt(val,r,calculateField);
                             //closeDB(); // not use because in servicesales when in 'combo constants' the db is closed but the field after needs to get value from db
                             }      // for each r  
                            
                             // recalculate sum fields after changes in combobox or textbox that affect table values for each row
    //                         calculateSumFields();

                             
                             }
                             //closeDB(); // not use because in servicesales when in 'combo constants' the db is closed but the field after needs to get value from db
 
                         }// t
             
                      //   closeDB(); // not use because in servicesales when in 'combo constants' the db is closed but the field after needs to get value from db
                //System.out.println("PanelODORData.dbFieldsCalculateSet  //-  before else   calculateCategoryField:"+calculateCategoryField+"    FIELDSCALCULATION_CATEGORY_SAME:"+FIELDSCALCULATION_CATEGORY_SAME);
             }            
             else  // is FIELDSCALCULATION_CATEGORY_SAME true
             {

                int indexOfHashChar = calculation.indexOf("#");// to find # look also below
                boolean hasQueryEmptyString = false;
               // System.out.println("PanelODORData.dbFieldsCalculateSet  FIELDSCALCULATION_CATEGORY_SAME  col:"+col+"    fc:"+fc+"    calculation:"+calculation);        
               for(int s=0;s<textString.length;s++)
               {   
                   // if does not have an empty string inside textString then continue
                 if(!textString[s].equalsIgnoreCase(""))
                 {
                       //System.out.println("   if  textString  s:"+s+"  textString:"+textString[s]);
                       //System.out.println("PanelODORData.dbFieldsCalculateSet B  query"+calculation);
                       hasQueryEmptyString = false;
                   
                 }//if
                 else
                 { // if has an empty string inside textString then break(do not calculate)
                     hasQueryEmptyString = true;
           //            System.out.println("PanelODORData.dbFieldsCalculateSet B     ELSE   BREAK  textString  s:"+s+"  textString:"+textString[s]);
                       break;     
                 }
               }// for*/
                

               
                 if(!hasQueryEmptyString)
                 {               
               
                       if(indexOfHashChar!=-1)
                       { 
                              calculation = utilsString.replaceTextOfAStringWithText("#", calculation, textString,  null);

                       }
   
                      db.retrieveDBDataFromQuery(calculation,"PanelODORData.dbFieldsCalculateSet B  ");
   	              rs=db.getRS();
                     if (rs!=null && rs.first())
                     {
                        rs = db.retrieveRow(rs, 1);// go to the only row  
                       //System.out.println(PanelODORData.dbFieldsCalculateSet   calculation "+calculation);
                        val = rs.getString(1)+"";// get field data	         	                            
                     }               
                 }//if
                 else
                 { // if has an empty string inside textString then break(do not calculate)
                       System.out.println("PanelODORData.dbFieldsCalculateSet C     ELSE    indexOfHashChar:"+indexOfHashChar+"    hasQueryEmptyString:"+hasQueryEmptyString);
                       if(indexOfHashChar!=-1)
                       { 
                           if(classtype.equalsIgnoreCase("java.lang.Double"))
                           {
                               // System.out.println("PanelODORData.dbFieldsCalculateSet            0              calculation:"+calculation);
                               calculation = utilsString.replaceTextOfAStringWithText("#", calculation, textString, "0");

                             db.retrieveDBDataFromQuery(calculation,"PanelODORData.dbFieldsCalculateSet C  ");
   	                    rs=db.getRS();
                            if (rs!=null && rs.first())
                            {
                                rs = db.retrieveRow(rs, 1);// go to the only row  
                                 //System.out.println(PanelODORData.dbFieldsCalculateSet   calculation "+calculation);
                                val = rs.getString(1)+"";// get field data	
                                val = utilsDouble.getDoubleReading(val,true);
                                System.out.println("PanelODORData.dbFieldsCalculateSet   val:"+val);
                            }                                
      
                           }
                           else
                           {
                                          //break;     
                                  val = "";
                           }

                       }
 
                 }               
  
                   
            

           ifHasValueChangedChangeOtherFieldsOrNot(col, colName, classtype, calculateField, val );
                   //is it different when changing lookup by key -or- by selecting in dialog with mouse?
                         
            } //  is FIELDSCALCULATION_CATEGORY_SAME true
           //System.out.println("error: PanelODORData.dbFieldsCalculateSet closeDB()  is after fc:"+fc+"   FIELDSCALCULATION_CATEGORY_SAME:"+FIELDSCALCULATION_CATEGORY_SAME);
//        closeDB(); // not use because in servicesales when in 'combo constants' the db is closed but the field after needs to get value from db
            System.out.println("PanelODORData.dbFieldsCalculateSet  +++++++++++++++++ col:"+col+"   colName:"+colName+"  classtype:"+classtype+"    calculateCategoryField:"+calculateCategoryField+"  textString.length:"+textString.length+"  calculateField:"+calculateField+"   calculation:"+calculation+"     val:"+val);
           } // for fieldscalculation fc
   //         fieldTxtsKeyChanged.set(col, false); //--- -- - -  
//        closeDB(); // not use because in servicesales when in 'combo constants' the db is closed but the field after needs to get value from db

         }//try
         catch ( SQLException sqlex)
         {
            
             System.out.println("error:PanelODORData.dbFieldsCalculateSet  "+sqlex.getMessage());
             sqlex.printStackTrace();
         }
         finally
         {
               closeDB();     
         }
                 		
        }    // if
        
        //System.out.println("PanelODORData.dbFieldsCalculateSet col:("+col+")   colName:"+colName);
        //System.out.println("PanelODORData.dbFieldsCalculateSet bef setvisoredi  hasDataChanged:"+hasDataChanged);
        
       this.setVisibleOrEditableFields(false);
       //this.calculateSumFields()
       // System.out.println("PanelODORData.dbFieldsCalculateSet after  hasDataChanged:"+hasDataChanged);
//       closeDB(); // not use because in servicesales when in 'combo constants' the db is closed but the field after needs to get value from db
       
   }
   
   private void ifHasValueChangedChangeOtherFieldsOrNot(int col, String colName, String classtype, int calculateField, String val)
   {
       
  
       String v = "";
       /*  if(!dbFieldsInGroupOfPanels[col].getColClassName().equalsIgnoreCase("table"))//.getGroupOfComps() ==-1)
         {
                          
         }
         else
         {*/
           if(dbFieldsInGroupOfPanels[calculateField].getLookupType()==LOOKUPTYPE_ONLYONE_THISFIELD || dbFieldsInGroupOfPanels[calculateField].getLookupType()==LOOKUPTYPE_NOLOOKUP)
          {
                JTextComponent tbToGet = (JTextComponent)fieldTxts.get(calculateField);//i-1);
               v = tbToGet.getText()+"";                   
          }
          else if (dbFieldsInGroupOfPanels[calculateField].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)
          {  // showRow
              
              	   JComboBox cb = (JComboBox) fieldTxts.get(calculateField);
                   
                    LookupTableConstantsMgt lookUpTableConstants = new LookupTableConstantsMgt();
                    EntityLookupTableConstantsData[] elutcData = lookUpTableConstants.getEntityLookupTableConstantsData(dbFieldsInGroupOfPanels[calculateField].getLookupEntityName());
                    //dbFieldsInGroupOfPanels[i].getLookupEntityName();
                   
                     v = cb.getSelectedIndex()+"";
                     /*for(int l = 0 ;l<elutcData.length;l++)
                     {
                       if(field.equalsIgnoreCase(elutcData[l].getPk()))
                       {
                            cb.setSelectedItem(elutcData[l].getTitle());
                       }                            
                     } */              
          }
          else
          {
               System.out.println("error  PanelODORData.ifHasValueChangedChangeOtherFieldsOrNot   col:"+col+"  colName:"+colName+" UNKNOWN LOOKUP TYPE  calculateField:"+calculateField+"  "+dbFieldsInGroupOfPanels[calculateField].getLookupType());              
          }       
 
             //used in order not to recalculate ie APY2 when  the existing record is edited,but to calculate when new is inserted(ie no of document)    ||  ie when calculating footer data like parakrathsh
              if(Boolean.parseBoolean(fieldTxtsKeyChanged.get(col).toString()) || dbFieldsInGroupOfPanels[calculateField].getIsVisibleOrEditable()==FIELD_VISIBLE_NOT_EDITABLE_ALWAYS)
               {
                
                   
          if(dbFieldsInGroupOfPanels[calculateField].getLookupType()==LOOKUPTYPE_ONLYONE_THISFIELD || dbFieldsInGroupOfPanels[calculateField].getLookupType()==LOOKUPTYPE_NOLOOKUP)
          {
             // System.out.println(" IF PanelODORData.dbFieldsCalculateSet col:("+col+")   colName:"+colName+"  UNKNOWN LOOKUP TYPE  calculateField:"+calculateField+"  "+dbFieldsInGroupOfPanels[calculateField].getLookupType());
                    JTextComponent tbToSet = (JTextComponent)fieldTxts.get(calculateField);
                   if(dbFieldsInGroupOfPanels[calculateField].getColClassName().equalsIgnoreCase("java.lang.Double")) 
                   {
                     tbToSet.setText(utilsDouble.getDoubleReading(val, false));
                     
                   }
                   else
                   {
                       tbToSet.setText(val);
                   }                 
          }
          else if (dbFieldsInGroupOfPanels[calculateField].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)
          {  // showRow
              
              	   JComboBox cb = (JComboBox) fieldTxts.get(calculateField);
                   
                    LookupTableConstantsMgt lookUpTableConstants = new LookupTableConstantsMgt();
                    EntityLookupTableConstantsData[] elutcData = lookUpTableConstants.getEntityLookupTableConstantsData(dbFieldsInGroupOfPanels[calculateField].getLookupEntityName());
                    //dbFieldsInGroupOfPanels[i].getLookupEntityName();
                   
                     //v = cb.getSelectedIndex()+"";
                     for(int l = 0 ;l<elutcData.length;l++)
                     {
                       if(val.equalsIgnoreCase(elutcData[l].getPk()))
                       {
                           System.out.println("PanelODORData.ifHasValueChangedChangeOtherFieldsOrNot cb.setSelected    elutcData[l].getTitle()"+elutcData[l].getName());
                            cb.setSelectedItem(elutcData[l].getName());
                       }                            
                     }              
          }                   
          else
          {
              System.out.println(" ELSE Changed PanelODORData.ifHasValueChangedChangeOtherFieldsOrNot col:("+col+")   colName:"+colName+"  UNKNOWN LOOKUP TYPE  calculateField:"+calculateField+"  "+dbFieldsInGroupOfPanels[calculateField].getLookupType());
          }           
                 
               }
               else
               {               
                   System.out.println(" ELSE ELSE NOT Changed - PanelODORData.ifHasValueChangedChangeOtherFieldsOrNot col:("+col+")   colName:"+colName+"   classtype:"+classtype + "     val:"+val+"   lookupType:"+dbFieldsInGroupOfPanels[calculateField].getLookupType());
               }       
       
      //}
       
       
   }
   
   
   /*
     called by PanelODOR.setActivePanel
   */
   public void setFocusInPageComponent()
   {
       
       //System.out.println("PanelOneDataOneRecData.setEntity   fieldTxts.size():"+fieldTxts.size());
       // request focus in first textfield  from http://www.rgagnon.com/javadetails/java-0510.html
       // focus also in rowNew

        
        //System.out.println("PanelODORData.setFocusInPageComponent   dbFieldsInGroupOfPanels:"+dbFieldsInGroupOfPanels.length);
         for (int i = 0; i < dbFieldsInGroupOfPanels.length; i++)
         {
              String columnClass =  dbFieldsInGroupOfPanels[i].getColClassName();
              String fieldName = dbFieldsInGroupOfPanels[i].getDbField();
             
              int isVisOrEdit = dbFieldsInGroupOfPanels[i].getIsVisibleOrEditable();
      //System.out.println("   PanelOneDataOnRecData.setFocusInPageComponent     --("+i+")--    "+fieldName+"    columnClass:"+columnClass+"    fieldTxts.size()"+fieldTxts.size());
                       
            if(columnClass.equalsIgnoreCase("java.lang.String") || columnClass.equalsIgnoreCase("java.lang.Double") || columnClass.equalsIgnoreCase("java.lang.Integer")
                    ||  columnClass.equalsIgnoreCase("java.awt.image.BufferedImage"))
            {

              //  JTextComponent txtb = (JTextComponent)fieldTxts.get(i);//i-1);
             //boolean isTxtEnabled = txtb.isEnabled();

       
             //System.out.println("   PanelODORData.setFocusInPageComponent  "+i+"    fieldName:"+fieldName);
               if(isVisOrEdit!=FIELD_VISIBLE_NOT_EDITABLE_ALWAYS && isVisOrEdit!=FIELD_NOT_VISIBLE  || (isNewRec && isVisOrEdit==FIELD_VISIBLE_NOT_EDITABLE_WHENEDIT_BUT_EDITABLE_ON_NEW))
               {
                   firstFieldThatIsEditable=i;
                   
                   break;
               }
               
            }//if colclass
          }//for
         
               if(fieldTxts.size()>0) 
              {
                  String columnClassFocus = dbFieldsInGroupOfPanels[firstFieldThatIsEditable].getColClassName();
                  String  fieldNameFocus = dbFieldsInGroupOfPanels[firstFieldThatIsEditable].getDbField();
                   int columnWidthFocus = dbFieldsInGroupOfPanels[firstFieldThatIsEditable].getColWidth();
         if(columnClassFocus.equalsIgnoreCase("java.sql.Date") || columnClassFocus.equalsIgnoreCase("java.lang.Date"))
         {
	
           final JTextBoxWithEditButtons textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(firstFieldThatIsEditable);// -1 because i starts from 1
           textEditFormatedDate.findDateNameInText();// to change day name in label when different date is typed 
                SwingUtilities.invokeLater(new Runnable()
                {
                  public void run()
                  {
                  textEditFormatedDate.requestFocus();
                  setGuiLoaded(true);
                  }
                });            
           
         } 
         else if(columnClassFocus.equalsIgnoreCase("java.lang.Boolean"))
         {
             final JCheckBox chk = (JCheckBox) fieldTxts.get(firstFieldThatIsEditable);
                SwingUtilities.invokeLater(new Runnable()
                {
                  public void run()
                  {
                  chk.requestFocus();
                  setGuiLoaded(true);
                  }
                });         
             //System.out.println("PanelODORData.DocumentHandler.insertUpdate  "+no+"  "+classtype+" "+foreignTable+"."+columnDbName); 
         }
         else if(dbFieldsInGroupOfPanels[firstFieldThatIsEditable].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)
         {
                  final JComboBox cmbBox =  (JComboBox) fieldTxts.get(firstFieldThatIsEditable);
                SwingUtilities.invokeLater(new Runnable()
                {
                  public void run()
                  {
                  cmbBox.requestFocus();
                   setGuiLoaded(true);
                 
                  }
                });                   
                    //value =  cmbBox.getSelectedItem().toString();             
         
         }
         else if( columnClassFocus.equalsIgnoreCase("java.awt.image.BufferedImage"))
         {
         		  JLabel la =(JLabel)fieldTxts.get(firstFieldThatIsEditable);
         	          //la.setBackground(l.getBackground());                    
         }         
         else if(columnClassFocus.equalsIgnoreCase("java.lang.String")|| columnClassFocus.equalsIgnoreCase("java.lang.Double") || columnClassFocus.equalsIgnoreCase("java.lang.Integer"))//if(colWidth>40)
         {                  
               
                        if(columnWidthFocus>COLWIDTH_FOR_HTML) // like printform html // look also above
                        {                 
                 
                         final  PanelHtmlEditor htmlEditor = (PanelHtmlEditor)fieldTxts.get(firstFieldThatIsEditable);//this is for html
    	        //final JTextComponent jc = (JTextComponent)fieldTxts.get(firstFieldThatIsEditable);
                SwingUtilities.invokeLater(new Runnable()
                {
                  public void run()
                  {
                  htmlEditor.requestFocus();
                  setGuiLoaded(true);
                 
                  }
                });  
                        }
                        else
                        {
             
             
      	        final JTextComponent jc = (JTextComponent)fieldTxts.get(firstFieldThatIsEditable);
                SwingUtilities.invokeLater(new Runnable()
                {
                  public void run()
                  {
                  jc.requestFocus();
                  setGuiLoaded(true);
                 
                  }
                });  
                        }
                
         }
         else if(columnClassFocus.equalsIgnoreCase("table"))
         {
      	        final JPanel jc = (JPanel)fieldTxts.get(firstFieldThatIsEditable);
                SwingUtilities.invokeLater(new Runnable()
                {
                  public void run()
                  {
                  jc.requestFocus();
                  setGuiLoaded(true);
                 
                  }
                });               
             
         }
         else
         {
             System.out.println("PanelODORData.setFocusInPageComponent UNKNOWN("+firstFieldThatIsEditable+")  fieldNameFocus:"+fieldNameFocus+"   columnClassFocus:"+columnClassFocus);
      	        final JTextComponent jc = (JTextComponent)fieldTxts.get(firstFieldThatIsEditable);
                SwingUtilities.invokeLater(new Runnable()
                {
                  public void run()
                  {
                  jc.requestFocus();
                  setGuiLoaded(true);
                 
                  }
                });               
         }
       }
               
   
       
   }
   
   /*
   *
   * called from panelOneDataOneRec.setTitle when a new record has been inserted
   */
      public String getPanelTitleFromTxtFields(String strtitle, String[] fieldsOnTitle, String[] fieldsOnTitleCaption)
      {
         String strRet="";
         String titleCaptionHtml="";

         String fieldValue = "";
        for(int f = 0;f<fieldsOnTitle.length;f++)
        {
          //System.out.println("field "+f+" "+fieldsOnTitleCaption[f]+" "+fieldsOnTitle[f]+" "+rs.getString(fieldsOnTitle[f]));
                            
          int colCount =dbFieldsInGroupOfPanels.length;
          
          for (int i = 0; i < colCount; i++)//  i = fieldTxts
          {
                  
          	  String columnName = fields[i]; //get colunm name  	           	
	          String classtype = dbFieldsInGroupOfPanels[i].getColClassName();  // if integer then not add ' and ' between values
          	  //String whereValueName = getWhereValueNameThatMatchesColumn(columnName,"global");
                  int columnWidth = dbFieldsInGroupOfPanels[i].getColWidth(); 
          //System.out.println("PanelOneDataOneRecData.rowUpdate >  "+i+"  "+classtype +"  "+columnName+"  whereValueName:"+whereValueName);

         	  //System.out.println("panelOneDataOneRecData.updateRow columnLabel "+columnLabel);
          	JTextComponent tb = new JTextField();
          	
          	JTextBoxWithEditButtons textEditFormatedDate = new JTextBoxWithEditButtons();
               // textEditFormatedDate.setYearEnforce(yearEnforce);
          	 String textFromDateFormat="";
          	 // date and lookup is not tb(JTextField) but panel
            if(fieldsOnTitle[f].equalsIgnoreCase(dbFieldsAll[i].getDbField()))
            {                    
             if (classtype.equalsIgnoreCase("java.sql.Date") || classtype.equalsIgnoreCase("java.lang.Date"))
             {
             	
             	   textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(i);
                   String strDate = textEditFormatedDate.getText();             
    	                        String[] allowedPatternsToRead = {"yyyy-MM-dd","yy/MM/dd","yy-MM-dd", "yyyy/MM/dd","dd-MM-yyyy","dd/MM/yy","dd-MM-yy", "dd/MM/yyyy"};
                                String fdate=utilsDate.reformatDateString(strDate,allowedPatternsToRead,"EEE dd-MM-yyyy");                                
                                fieldValue=fdate; 
             }
             else if (classtype.equalsIgnoreCase("java.lang.Boolean"))
             {
              	   JCheckBox chk = (JCheckBox) fieldTxts.get(i);
              	   chk.setOpaque(false);
              	   boolean sel =chk.isSelected(); 
              	   if(sel)
              	   {
              	   	fieldValue="1";
              	   }
              	   else 
              	   {
              	   	 fieldValue="0";
              	   }           	  	
             }   
             else if(classtype.equalsIgnoreCase("table"))
             {

                 System.out.println("PanelOneDataOneRecData.getPanelTitleFromTxtFields table");
             }  
             else if(classtype.equalsIgnoreCase("htmlfile"))
             {

                 System.out.println("PanelOneDataOneRecData.getPanelTitleFromTxtFields htmlfile");
             }              
             else if(dbFieldsInGroupOfPanels[i].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)           
             {
                   LookupTableConstantsMgt lookUpTableConstants = new LookupTableConstantsMgt();
                    EntityLookupTableConstantsData[] elutcData = lookUpTableConstants.getEntityLookupTableConstantsData(dbFieldsInGroupOfPanels[i].getLookupEntityName());
                    //dbFieldsInGroupOfPanels[i].getLookupEntityName();
                    JComboBox cmbBox =  (JComboBox) fieldTxts.get(i);
                    fieldValue = elutcData[cmbBox.getSelectedIndex()].getPk();         
             }
             else
             {
                 tb = (JTextComponent) fieldTxts.get(i);
                 fieldValue = tb.getText();
               //System.out.println("PanelOneDataOneRecData.rowUpdate "+fieldValue);
             }
            }
                    
          }
            titleCaptionHtml = titleCaptionHtml+"<span style='color:"+CLR_TITLE_CAPTION_DARK_HTML+"'>"+ fieldsOnTitleCaption[f]+":</span>"+ fieldValue +" ";
                               // titleCaptionStr=titleCaptionStr+ " "+ fieldsOnTitleCaption[f]+":"+ strField +" ";  
        }
        
        strRet ="<html>"+strtitle+" "+titleCaptionHtml+"</html>";
        return strRet;
      }
   
   
   /*
   * 
   * exists in UtilsPanelReport.getValueForVariable,  in PanelOneDataOneRecData.getValueForVariable
   * and TableModelResultSet.getValueForVariable
   */
 /* private String getValueForVariable(String variableName)
  {
      

      String valueStr="";
      if(variableName.equalsIgnoreCase("globalYear"))
      {
          valueStr=globalYear;
      }
      else if(variableName.equalsIgnoreCase("globalCompanyId"))
      {
          valueStr=globalCompanyId;
      }
      else if(variableName.equalsIgnoreCase("globalDeliveryId"))
      {
          valueStr=globalDeliveryId;
      }    
      else if(variableName.equalsIgnoreCase("primKeyValue"))
      {
          valueStr=primKeyValue;
      }
      else
      {
          valueStr="";
          System.out.println("error PanelOneDataOneRecData.getValueForVariable UNKNOWN   variableName:"+ variableName+"  valueStr:"+valueStr);
      }      
  */    
      

      
  /*	String valueStr="";
  	double valueDoub;
  	int valueInt;
  	Object valueObj;
    try
    {
      //System.out.println("panelTwoDataOneRec.getValueForVariable "+ PanelTwoDataOneRec.class.getName());
      Field thisField = PanelOneDataOneRecData.class.getField(variableName);

      Class thisClassType = thisField.getType();
      //System.out.println("panelTwoDataOneRec.getValueForVariable "+variableName+" "+ thisField+" "+thisClassType+" "+valueStr);
      //System.out.print("PanelODORData.getValueForVariable The variable '"+lookingForValue+"' contains ");
      if (thisClassType.toString().equals("double"))
      { 
          valueDoub=thisField.getDouble(this);
           valueStr=Double.toString(valueDoub);   
      }
      else if (thisClassType.toString().equals("int"))
      {
      	   valueInt=thisField.getInt(this);
              valueStr=Integer.toString(valueInt);
      }
      else if (thisClassType.toString().equals("class java.lang.String"))
      {
           valueObj=thisField.get(this);
           if (valueObj!=null)
           {      valueStr=valueObj.toString();}
           else
           { System.out.println("PanelODORData.getValueForVariable "+variableName+" valueObj="+valueObj);  }
         //System.out.println("PanelODORData.getValueForVariable "+variableName+" valueObj="+valueObj);
      }
      else if(variableName.equalsIgnoreCase("-"))
      {
      	
      }
      else
      {
        System.out.println("PanelODORData.getValueForVariable "+variableName+" Not recognized classtype:"+thisClassType.toString());  
      }
     
    }
       catch(Exception e)
       {  
       	
       	 System.out.println("PanelODORData.getValueForVariable "+variableName+" Not recognized classtype:"+e.getMessage());
       	 //e.printStackTrace();   
       }*/

 //   return valueStr;
 // }     
   
  
   /*private boolean checkIfNameMatchesWhereValueName(String columnLabel)
   { 
     boolean is=false;
     String word="global";
     if(sql2WhereField!=null)
     {
        for(int i=0;i<sql2WhereField.length;i++)
        {
           //System.out.println(".panelOneDataOneRecData.checkIfNameIsWhereField "+sql2WhereField[i]+" matches global");
           if(sql2WhereValue[i].regionMatches(true,0,word,0,word.length()))   	          
           {
           	   //System.out.println(".panelOneDataOneRecData.checkIfNameIsWhereField "+sql2WhereValue[i]+" matches global");
      	       if(sql2WhereField[i].toUpperCase().equals(columnLabel.toUpperCase()))
      	       {
      	       	//System.out.println(".panelOneDataOneRecData.checkIfNameIsWhereField "+sql2WhereValue[i]+" matches global");
      	          is=true	;
      	       }
      	   }
        }
      }
   	   return is;
   }*/
  
   
   private String getWhereValueNameThatMatchesColumn(String columnLabel,String matchWord)
   { 
     String whereValueName="-"; 
     //String matchWord="global";
   /*  if(sql2WhereField!=null)
     {
        for(int i=0;i<sql2WhereField.length;i++)
        {
            //System.out.println("PanelOneDataOneRecData.getWhereValueNameThatMatchesColumn   "+sql2WhereValue[i]+"    matches   "+columnLabel);
            if(matchWord==null)
            {
      	          if(sql2WhereField[i].toUpperCase().equals(columnLabel.toUpperCase()))
      	          {
      	       	     //System.out.println(".panelOneDataOneRecData.getWhereValueNameThatMatchesColumn "+sql2WhereValue[i]+" matches "+columnLabel);
      	             whereValueName=sql2WhereValue[i]	;
      	          }
            }
            else
            {
              //System.out.println("   .panelOneDataOneRecData.getWhereValueNameThatMatchesColumn "+sql2WhereField[i]+" matches global");
              if(sql2WhereValue[i].regionMatches(true,0,matchWord,0,matchWord.length()))   	          
              {
           	      //System.out.println(".panelOneDataOneRecData.getWhereValueNameThatMatchesColumn "+sql2WhereValue[i]+" matches global");
      	          if(sql2WhereField[i].toUpperCase().equals(columnLabel.toUpperCase()))
      	          {
      	       	     //System.out.println(".panelOneDataOneRecData.getWhereValueNameThatMatchesColumn "+sql2WhereValue[i]+" matches "+columnLabel);
      	             whereValueName=sql2WhereValue[i]	;
      	          }
      	      }
      	   }
        } // for
      }  // if   */
      
     System.out.println("PanelODORData.getWhereValueNameThatMatchesColumn DOES NOTHING (to be deleted)    columnLabel:"+columnLabel+"     matchWord:"+matchWord+"     whereValueName:"+whereValueName);
      
        return whereValueName;
   }
   
/*    public void displayDialogReportSettings()
   { 
       	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));

   
      UtilsString uString = new UtilsString();
      PanelReportSettingsAndPreview panelReportSettingsAndPreview = new PanelReportSettingsAndPreview((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this));

      String sqlQuery = uString.getQueryWithoutOrderby(query)+" WHERE "+primKeyDb+"="+primKeyValue;
      EntityQuery[] entityQuery = new EntityQuery[1];
      entityQuery[0] = new EntityQuery(sqlQuery,false,0,null,null,null,null,null);
            
//      panelReportSettingsAndPreview.setEntity(entity,"","ODOR",null,primKeyDb,null,null,entityQuery,"",sql2WhereField, sql2WhereValue,title,"",true,null,null,null,null,null);
      
      /* lic void setEntity(String entityIn,String entityManyIn,String viewTypeIn,String entityHeader, String primKeyIn, String primKeyValueIn, Vector dataVectorIn, 
       EntityQuery[] entityQueryIn,String queryManyIn,String[] sql2WhereFieldIn, String[] sql2WhereValueIn,String titleIn,String subTitleIn,
       boolean showSummaryPageIn, EntityFilterSettings[] entityFilterSettingsIn,int[] fieldSelectedIn,boolean[] fieldsEditableIn, int[] fieldsOrderby)*/
    
      /*setEntity(String entityIn,String entityManyIn,String viewTypeIn,String primKeyIn, String primKeyValueIn, Vector dataVectorIn, 
       String queryIn,String queryManyIn,String[] sql2WhereFieldIn, String[] sql2WhereValueIn,String titleIn,String subTitleIn,
       boolean showSummaryPageIn, EntityFilterSettings[] entityFilterSettingsIn,boolean[] fieldSelectedIn,boolean fieldsEditableIn)*/
      
/*      panelReportSettingsAndPreview.showDialog(); 
   
      //panelReportSettingsAndPreview panelReportSettingsAndPreview = new PanelReportSettingsAndPreview((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this));
      //panelReportSettingsAndPreview.setEntity(entity,"ODOR",primKey,primKeyValue,null,null,query,null,null,null,"title",true,null,null);
      //System.out.println("panelODORData.displayDialogReport todo "+primKeyDb+" "+primKeyValue);
      //panelReportSettingsAndPreview.showDialog();
      //String entity, int selectedRow, Vector dataVector, String query,String title,boolean showSummaryPage)
      this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

   }*/
   
   //exists modificated in PanelOneDataOneRecData and TableCellEditorLookUp
   public String displayWindowLookUp(String foreignTable, int dbCol)
   { 
   	  int noOfCharsForOpenningWindow=1;
   	  int noOfCharsToShowWindow=1;
   	  String ret=""; 
   	   
   	  
   	  JTextComponent tbc =(JTextComponent)fieldTxts.get(dbCol);
   	  
   	  JTextComponent tbc2 =(JTextComponent)fieldTxts2.get(dbCol);
   	  JTextComponent tbc3 = null;
   	  if(lookUp.getLookUpField2(foreignTable)!=null)
   	  {
   	     tbc3 =(JTextComponent)fieldTxts3.get(dbCol);	
   	  }
   	  
   	  //System.out.println("PanelODORData.displayWindowLookUp "+foreignTable+" "+ta.getText() +" "+tb.getText());
   	  

   	  
   	 if( tbc2.getText().length()>=noOfCharsForOpenningWindow ||( lookUp.getLookUpField2(foreignTable)!=null && tbc3.getText().length()>=noOfCharsForOpenningWindow))
   	 {
   	            
          //winLookUp = new WindowLookUp(frame);
        	 //System.out.println("isVisibleWinLookUpCheck ifisFalse "+winLookUpCheck.isVisible());
  	/*       ArrayList  arrList = new ArrayList();
           arrList.add("1 first");
           arrList.add("2 second");
           arrList.add("3 third");
           arrList.add("4 forth");
           arrList.add("5 fifth");*/



      if(tbc2.getText().length()<=noOfCharsToShowWindow && !tbc2.getText().trim().equalsIgnoreCase(""))
      {
          
          
      String fieldVariableFromPreField = dbFieldsInGroupOfPanels[dbCol].getFormVariableFromField();
      int intFieldToGetTheValue = -1;
      for(int f= 0 ;f<dbFieldsInGroupOfPanels.length;f++)
      {
          if(dbFieldsInGroupOfPanels[f].getDbField().equalsIgnoreCase(fieldVariableFromPreField))
          {
              intFieldToGetTheValue=f;
          }
      }
      //String strValueFromField = "";
      System.out.println("PanelODORData.displayWindowLookUp  ===   dbCol:"+dbCol+"  intFieldToGetTheValue:"+intFieldToGetTheValue);
      
      calculateVarFromPreFieldAndSetGlobal(dbCol);
      /*if(intFieldToGetTheValue!=-1)
      {
           JTextField tbf = (JTextField) fieldTxts.get(intFieldToGetTheValue);
            strValueFromField= tbf.getText();
            setVariablesGlobal1(fieldVariableFromPreField,strValueFromField);          
      }  */ 
        
      if(fieldVariableFromPreField!= null && !fieldVariableFromPreField.equalsIgnoreCase("") && VariablesGlobal.globalformGlobalVariable1.equalsIgnoreCase("") )//.equalsIgnoreCase("")) 
     {
         String caption = "";
         if(intFieldToGetTheValue!=-1)
         {
         caption = dbFieldsInGroupOfPanels[intFieldToGetTheValue].getCaption();
         
         utilsGui.showMessageInfo("Παρακαλώ συμπληρώστε πρώτα το πεδίο:"+caption);
         }
     }
     else
     {      
          

         System.out.println("PanelODORData.displayWindowLookUp  ===    dbCol"+dbCol+"  arGlobal");
        
         // String fieldVariableFromPreField = dbFieldsInGroupOfPanels[dbCol].getFormVariableFromField();
          winLookUp.setEntity(tbc,tbc2.getText(),foreignTable,lookUp, null,2,2,lookUp.getIntValidationColumn(foreignTable), lookUp.getIntValidationType(foreignTable),
                  dbFieldsInGroupOfPanels,dbCol,fieldTxts,WINDOWLOOKUP_IS_CALLED_IN_TEXTFIELD,-1); 
          //tb2.setText("");
           
           final JTextComponent tbFinal = tbc;
       	            tbFinal.addFocusListener(new FocusListener()  // look in showRow for focus gained in tb
                    {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                           
                        }

                    	public void focusGained(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                            winLookUp.close();
                          
                            //textArea.remove(lblIcoAttention);
                        }
                    });          
        
           final JTextComponent tb2Final = tbc2;


         if(lookUp.getLookUpField2(foreignTable)!=null)
   	     {
           final JTextComponent tb3Final = tbc3;

   	     }
   	    
   	    String selected = winLookUp.getTextIndex();
            
       if (selected!=null && !selected.equalsIgnoreCase("")) // means not selected cancel
       {
       System.out.println("PanelODORData.displayDialogLookUp   ====================================        foreignTable:"+foreignTable+"   selected:"+selected);      
             //setVariablesGlobal1(String foreignTableIn, String value)
             

             
            // tb.getText();
               tbc.setText(selected);
 
     //     System.out.println("PanelODORData.displayDialogLookUp    if2  bef   foreignTable:"+foreignTable+"   selected:"+selected);      
                            // exists in setEntity focuslost and displayDialogLookUp
        /*                   String lookUpResult = calculateTextForLookupsAfterKeyIsSet(dbCol,foreignTable);
                           if(Boolean.parseBoolean(fieldTxtsKeyChanged.get(dbCol).toString()))//keyChanged)
                           {
                  //            calculateRecordAfterKeySet(dbCol);	
                           }
                           //System.out.println(tbFinal);
                           if (lookUpResult.equalsIgnoreCase("-") && tb!=null && !tb.getText().trim().equalsIgnoreCase("")) 
                           {
                                 
                                 utilsGui.showMessageError(this,"Key typed not correct. Please retry."); 
                                 
                                 tb.setText("");
                                 tb.requestFocus();  // if not correct return to first textfield of lookup
                           }    
                          
                              dbFieldsCalculateSet(dbFieldsInGroupOfPanels,dbCol,foreignTable);          
            */
           //needed for tb2 to be updated in the following order
           //tb.requestFocus();
          tbc2.requestFocus();
           //tb.requestFocus();
       }
       else
       {    tbc.requestFocus();
       
       }            
            
            
            
            
            
            
            
       /*if (!selected.equalsIgnoreCase("")) // means selected cancel
       {
          //tb.setText(selected.trim());
       
           //needed for tb2 to be updated in the following order
          // tb.requestFocus();
          // tb2.requestFocus();
          // tb.requestFocus();
       }
       else
       {
       	    //tb.requestFocus();  // not this because it gets focus when for example something enterd in tb2
       }  */ 	     
   	     
   	  }
   	     //winLookUp.requestFocus();
   	    
   	 }// if
   	 else
   	 {
   	 	winLookUp.close();
               
              
   	 	
   	 }   	 
         }
   	 	return ret;
   }   
   
   
  
   
   
   
   private void displayDialogLookUp(String foreignTable, int dbCol)
   {
      // Component comp =this.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
       //System.out.println(comp.getClass());
      
       //System.out.println("PanelODORData.displayDialogLookUp   ====================================      entity:"+entity+"   foreignTable:"+foreignTable+"   dbCol:"+dbCol+"    fieldForm:"+dbFieldsInGroupOfPanels[dbCol].getFormVariableFromField()) ;
       
       this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      //DBFieldMgt dbFieldMgt = new DBFieldMgt();
      //dbFieldMgt.addEntitiesDBField(dbFieldsInGroupOfPanels);
      //dbFieldMgt[].getFormVariableFromField(title)
      
      //System.out.println("  == O ==>  PanelODORData.displayDialogLookUp    entity:"+entity+"   foreignTable:"+foreignTable+"   dbCol:"+dbCol);
      String fieldVariableFromPreField = dbFieldsInGroupOfPanels[dbCol].getFormVariableFromField();     
      
       System.out.println("  == O ==>  PanelODORData.displayDialogLookUp    entity:"+entity+"   foreignTable:"+foreignTable+"   dbCol:"+dbCol);
      String strValueFromField = calculateVarFromPreFieldAndSetGlobal(dbCol);
      /*if(fieldVariableFromPreField!= null && !fieldVariableFromPreField.equalsIgnoreCase(""))
      {
            JTextField tbf = (JTextField) fieldTxts.get(intFieldToGetTheValue);
            strValueFromField= tbf.getText();
            setVariablesGlobal1(fieldVariableFromPreField,strValueFromField);
          
          //strValueFromField = VariablesGlobal.globalformGlobalVariable1;
      }
      else
      {
           
            
      }*/
      //System.out.println("  == O ==>  PanelODORData.displayDialogLookUp    entity:"+entity+"   foreignTable:"+foreignTable+"   dbCol:"+dbCol+"    fieldVariableFromPreField:"+fieldVariableFromPreField+"    intFieldToGetTheValue:"+intFieldToGetTheValue+"   var glo1:"+VariablesGlobal.globalformGlobalVariable1) ;
    if(fieldVariableFromPreField!= null && !fieldVariableFromPreField.equalsIgnoreCase("") && VariablesGlobal.globalformGlobalVariable1.equalsIgnoreCase("") )//.equalsIgnoreCase("")) 
     {
         int intFieldToGetTheValue = utilsPanelReport.calculateAllFieldsFromParentDBFieldsForFormVariable1(dbFieldsAll); 
         String caption = "";
         if(intFieldToGetTheValue!=-1)
         {
         caption = dbFieldsInGroupOfPanels[intFieldToGetTheValue].getCaption();
         
         utilsGui.showMessageInfo("Παρακαλώ επιλέξτε πρώτα το πεδίο:"+caption);
         }
     }
     else
     {
       DialogLookUp.initialize((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this));
   	   
   	   String selectedKeyValue ="";

       JTextField tb = (JTextField) fieldTxts.get(dbCol);
       selectedKeyValue = tb.getText();
       fieldTxts.get(dbCol).toString();
       
       JTextField tb2 = (JTextField) fieldTxts2.get(dbCol);
       String tb2Text=tb2.getText();
       
 // System.out.println("---panelODORData.displayDialogLookUp A  dbCol:"+dbCol+"    selectedKeyValue:"+selectedKeyValue+"   tbText class:"+fieldTxts.get(dbCol).toString()+"   tb2Text:"+tb2Text);
       String selected="";
//       if(selectedKeyValue.equalsIgnoreCase(""))
 //      {
       	String sub="";
       	if(lookUp.getLookUpField(foreignTable).length>=1)
   	   	{
   	   	    // getLookUpField is array
   	   		int len = lookUp.getLookUpField(foreignTable).length;
   	   		for(int o= 0; o<len ;o++)
   	   		{
   	   		   
   	   		   String end ="";	
   	   		   	if(o==0)
   	   		   	{
   	   		   	   	sub=sub+lookUp.getLookUpField(foreignTable)[o]+" LIKE '"+tb2Text+"%'";
   	   		   	}
   	   		   	else
   	   		   	{
   	   		   		sub=sub+" OR "+lookUp.getLookUpField(foreignTable)[o]+" LIKE '"+tb2Text+"%'";
   	   		   	}
   	   		   	
   	   		}
   	   	}
       	  String qSub = "";
          String qSubForVar = "";
          String queryLookUpWhere = lookUp.getQuerySubqueryWhere(foreignTable);
       	 String queryLookUpWhereForFormVariable = lookUp.getQueryWhereForFormVariable(foreignTable);
          
          
      //if(formGlobalTableToApply1!=null && !formGlobalTableToApply1.equalsIgnoreCase(""))
      if(fieldVariableFromPreField!=null && !fieldVariableFromPreField.equalsIgnoreCase(""))
      {
          //if(!formGlobalTableToApply1.equalsIgnoreCase(foreignTable))             //if(entityIn.equalsIgnoreCase(VariablesGlobal.globalformGlobalTableToGet1))
          //{          
          if(utilsString.hasQueryWhere(queryLookUpWhere))
   	  {
       	      qSub = " AND( "+sub+" ) ";//lookUp.getLookUpField(foreignTable)+" LIKE '"+tb2Text+"%' "
   	  }
   	  else
   	  {
   	       	  qSub = " WHERE "+sub;//lookUp.getLookUpField(foreignTable)+" LIKE '"+tb2Text+"%' "
   	  }
          
       
          
          //}
      }
           
         
           
     //System.out.println("---panelODORData.displayDialogLookUp   dbCol:"+dbCol+"   selectedKeyValue:"+selectedKeyValue+"      tb2Text:"+tb2Text);
  
       String subQueryFilterFromRecType="";

  String queryLookUpIsActive = lookUp.getQuerySubqueryIsActive(foreignTable);

      //System.out.println(" panelODORData.displayDialogLookUp    -u--o--u-  fieldVariableFromPreField:"+fieldVariableFromPreField+"   intFieldToGetTheValue:"+intFieldToGetTheValue);
      if(fieldVariableFromPreField!=null && !fieldVariableFromPreField.equalsIgnoreCase(""))
      {

          if(strValueFromField.equalsIgnoreCase(""))
          {
              subQueryFilterFromRecType = "1)";   
          }
          else
          {
                 subQueryFilterFromRecType =  strValueFromField +" ) ";  
               //  subQueryFilterFromRecType = VariablesGlobal.globalformGlobalVariable1 +" ) ";  
          }
          
      } 
      else
      {

      }

      
    // System.out.println(" BEFORE panelODORData.displayDialogLookUp     "+foreignTable+"     ----selectedKeyValue:"+selectedKeyValue+"+++++       subQueryFilterFromRecType:"+subQueryFilterFromRecType);
      JTextComponent txtb = (JTextComponent)fieldTxts.get(dbCol);   
      String val = txtb.getText();
      
            
        if(subQueryFilterFromRecType.equalsIgnoreCase("")) //    selectedKeyValue.equalsIgnoreCase("") ||
           // if(subQueryFilterFromRecType!=null && !subQueryFilterFromRecType.equalsIgnoreCase(""))
        {
            
            
            String queryLUWithQ = lookUp.getQuery(foreignTable)+" "+queryLookUpWhere+"  "+qSub+" "+queryLookUpIsActive+" "+lookUp.getQueryOrderBy(foreignTable);
        
            //System.out.println("PanelODORData.displayDialogLookUp queryLUWithQ:"+queryLUWithQ);            
           // EntityReport entityReportLU = lookUp.getEntityReport(foreignTable);
        //System.out.println("panelODORData.displayDialogLookUp   IF     selectedKeyValue:"+selectedKeyValue+"      queryLUWithQ:"+queryLUWithQ);
           selected = DialogLookUp.showDialog(this,foreignTable, queryLUWithQ,lookUp.getLookUpKeyTranslation(foreignTable) , 
                   selectedKeyValue,lookUp.getShowToolbar(foreignTable),/*yearEnforce,*/panelManagement,lookUp.getFieldsForSums(foreignTable),fieldTxts);//,entityReportLU); 
           
           
          //System.out.println("panelODORData.displayDialogLookUp IF B  selectedKeyValue:"+selectedKeyValue+" selected:"+selected+" queryLUWithQ:"+queryLUWithQ);
       }
        else //if( !subQueryFilterFromRecType.equalsIgnoreCase(""))  //  !selectedKeyValue.equalsIgnoreCase("") &&
        {
            
            String queryLUWithQ = lookUp.getQuery(foreignTable)+" "+queryLookUpWhereForFormVariable+" "+subQueryFilterFromRecType+" "+/*qSub+*/" "+queryLookUpIsActive+" "+lookUp.getQueryOrderBy(foreignTable);
        
         // System.out.println("PanelODORData.displayDialogLookUp   ELSE IF      selectedKeyValue:"+selectedKeyValue+"     queryLUWithQ:"+queryLUWithQ);            
           // EntityReport entityReportLU = lookUp.getEntityReport(foreignTable);
      //  System.out.println("panelODORData.displayDialogLookUp  ELSE  IF     selectedKeyValue:"+selectedKeyValue+"      queryLUWithQ:"+queryLUWithQ);
           selected = DialogLookUp.showDialog(this,foreignTable, queryLUWithQ,lookUp.getLookUpKeyTranslation(foreignTable) , 
                   selectedKeyValue,lookUp.getShowToolbar(foreignTable),/*yearEnforce,*/panelManagement,lookUp.getFieldsForSums(foreignTable),fieldTxts);//,entityReportLU); 

        }

       
              //System.out.println("PanelODORData.displayDialogLookUp   ====================================    formGlobalTableToGet1:"+formGlobalTableToGet1);
       if (selected!=null && !selected.equalsIgnoreCase("")) // means not selected cancel
       {
       //System.out.println("PanelODORData.displayDialogLookUp   ====================================    formGlobalTableToGet1:"+formGlobalTableToGet1+"   formGlobalTableToApply1:"+formGlobalTableToApply1+"    foreignTable:"+foreignTable+"   selected:"+selected);      
             //setVariablesGlobal1(String foreignTableIn, String value)
              //tb.getText();
             if(!val.equalsIgnoreCase(selected)) // if changed
             {
                 fieldTxtsKeyChanged.set(dbCol, true);
             }
             else
             {
                 fieldTxtsKeyChanged.set(dbCol, false);
             }
              tb.setText(selected.trim());
 
     //     System.out.println("PanelODORData.displayDialogLookUp    if2  bef   foreignTable:"+foreignTable+"   selected:"+selected);      
                            // exists in setEntity focuslost and displayDialogLookUp
                           String lookUpResult = calculateTextForLookupsAfterKeyIsSet(dbCol,foreignTable);
                           if(Boolean.parseBoolean(fieldTxtsKeyChanged.get(dbCol).toString()))//keyChanged)
                           {
                  //            calculateRecordAfterKeySet(dbCol);	
                           }
                           //System.out.println(tbFinal);
                           if (lookUpResult.equalsIgnoreCase("-") && tb!=null && !tb.getText().trim().equalsIgnoreCase("")) 
                           {
                                 
                                 utilsGui.showMessageError(this,"Key typed not correct. Please retry."); 
                                 
                                 tb.setText("");
                                 tb.requestFocus();  // if not correct return to first textfield of lookup
                           }    
                           
                            //System.out.println("PanelODORData.dbFieldsCalculateSet c  hasDataChanged:"+hasDataChanged);
                              dbFieldsCalculateSet(dbFieldsInGroupOfPanels,dbCol,foreignTable); 
                              
                         
                              checkFieldsIfThenElse(dbCol,CHECK_ON_ENTRY);
           //needed for tb2 to be updated in the following order
           //tb.requestFocus();
          tb2.requestFocus();
           //tb.requestFocus();
       }
       else
       {    tb.requestFocus();
       
       }
       
       
     }// when thereis novariable set
       
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

   }
 
   
    private void displayDialogCheckValidation(int columnFieldValidation, int dbCol)
   { 
       
      // Component comp =this.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
       //System.out.println(comp.getClass());
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));

       JTextField tb = (JTextField) fieldTxts.get(dbCol);
       String selectedKeyValue = tb.getText();

        
        DialogMulti dlg = new DialogMulti(frame);
        String title = "";
        boolean showOkButton = false;
        JxPanel pnl = new JxPanel();
        pnl.setLayout(new BorderLayout());
        
        if(columnFieldValidation==FIELD_VALIDATION_AFM)
        {
            title=("αναζήτηση στοιχείων απο το taxisnet");
            showOkButton=true;
           /*JTextArea txtArea = new JTextArea();
           txtArea.setFont(new JLabel().getFont());            
            txtArea.setSize(100, 80);*/
           JxPanel pnlAfm = new JxPanel();
                GridLayoutVariable layout = new GridLayoutVariable (GridLayoutVariable.FIXED_NUM_COLUMNS, 3);
           pnlAfm.setLayout(layout);
           ArrayList listNewFieldNamesAndValues = new ArrayList();
           ArrayList listRadioBtns = new ArrayList();
           UtilsSoap utilsSoap = new UtilsSoap();
           String afmXml="";
  
               
       
               ArrayList lstSoan = new ArrayList();
               lstSoan.add(new EntitySoapResponseNamesNValues("onomasia","επωνυμία","java.lang.String",null,"title"));
               lstSoan.add(new EntitySoapResponseNamesNValues("deactivation_flag","ενεργός","java.lang.Boolean",null,"active"));
               lstSoan.add(new EntitySoapResponseNamesNValues("regist_date","έναρξη","java.lang.Date",null,"startdate"));
               lstSoan.add(new EntitySoapResponseNamesNValues("normal_vat_system_flag","κατηγορία ΦΠΑ","java.lang.String",null,"vatcat"));
               lstSoan.add(new EntitySoapResponseNamesNValues("doy","κωδ. ΔΟΥ","java.lang.String",null,"doy"));//
               lstSoan.add(new EntitySoapResponseNamesNValues("doy_descr","επωνυμία ΔΟΥ","java.lang.String",null,"dou"));
               lstSoan.add(new EntitySoapResponseNamesNValues("i_ni_flag_descr","νομική μορφή","java.lang.String",null,"nm"));
               lstSoan.add(new EntitySoapResponseNamesNValues("postal_area_description","περιοχή","java.lang.String",null,"addressCity"));
               lstSoan.add(new EntitySoapResponseNamesNValues("postal_zip_code","ΤΚ","java.lang.String",null,"addressPC"));
               lstSoan.add(new EntitySoapResponseNamesNValues("postal_address","διεύθυνση","java.lang.String",null,"addressStreet"));
               lstSoan.add(new EntitySoapResponseNamesNValues("postal_address_no","διεύθυνση νο","java.lang.String",null,"addressno"));
               lstSoan.add(new EntitySoapResponseNamesNValues("firm_act_descr","δραστηριότητα","java.lang.String",null,"activityDescr"));
//               lstSoan.add(new EntitySoapResponseNamesNValues("error_code","κωδ σφάλματος","java.lang.String",null,"error_code"));
               lstSoan.add(new EntitySoapResponseNamesNValues("error_descr","σφάλμα","java.lang.String",null,"error_descr"));

               
           Database dbafm = new Database();
           String sqlAFM ="SELECT afmTaxisUsername, afmTaxisPassword FROM dbcompany WHERE "+STRFIELD_DBCOMPANYID+" LIKE "+VariablesGlobal.globalCompanyId;;
           dbafm.retrieveDBDataFromQuery(sqlAFM, "PanelDataImportExport.displayDialogCheckValidation");
           //ResultSetMetaData rsmd = db.getRSMetaData();
           ResultSet rsAfm = dbafm.getRS();               
           try
           { 
             if(rsAfm.first())
             {
                 afmXml = utilsSoap.getXmlAfmFor(rsAfm.getString("afmTaxisUsername"),rsAfm.getString("afmTaxisPassword"),selectedKeyValue);
             }
           }
           catch(SQLException e)
           {
  
            System.out.println("PanelOneDataOneRecData.displayDialogCheckValidation    SQLException:"+e.getErrorCode()+"  "+e.getMessage());
           e.printStackTrace();
           }
           finally
           {
                dbafm.releaseConnectionRs();
                dbafm.releaseConnectionRsmd();
           }
           
                 if(!afmXml.equalsIgnoreCase(""))
                 {
                ArrayList lstSoanResult =  utilsSoap.getNameNValuesFromXml(lstSoan, afmXml);
                ButtonGroup btnRdioGroup = new ButtonGroup();
                 for(int i=0;i<lstSoanResult.size();i++)
                 {
                      EntitySoapResponseNamesNValues esrn = (EntitySoapResponseNamesNValues)lstSoanResult.get(i);
                      JRadioButton radioBtnNew = new JRadioButton();
                      listRadioBtns.add(radioBtnNew);
                   if(esrn.nameNode.equalsIgnoreCase("error_descr")   )
                   {
                       if(esrn.value != null)
                       {
                           pnlAfm.add(new JLabel(esrn.value));
                           showOkButton=false;
                       }
                   }
                   else
                   {
                      pnlAfm.add(new JLabel(esrn.caption));
                      
                     
                      pnlAfm.add(radioBtnNew);
                      
                      for(int f =0;f<dbFieldsAll.length;f++)
                      {
                      if(esrn.nameDb.equalsIgnoreCase(dbFieldsAll[f].getDbField()) && !esrn.nameNode.equalsIgnoreCase("deactivation_flag"))
                      {
                             radioBtnNew.setSelected(true);
                      }
                      }
                      if(esrn.classtype.equalsIgnoreCase("java.lang.Boolean"))
                      {
                          JCheckBox chk =new JCheckBox();
                          if(esrn.value.equalsIgnoreCase("1"))
                          {
                            chk.setSelected(true);
                          }
                          else
                          {
                            chk.setSelected(false);  
                          }
                          chk.setEnabled(false);
                          pnlAfm.add(chk);
                      }
                      else if(esrn.classtype.equalsIgnoreCase("java.lang.Date"))
                      {
                       String[] allowedPatternsToRead = {"yyyy-MM-dd","yy/MM/dd","yy-MM-dd", "yyyy/MM/dd","dd-MM-yyyy","dd/MM/yy","dd-MM-yy", "dd/MM/yyyy"};
                       String fdate=utilsDate.reformatDateString(esrn.value,allowedPatternsToRead,"dd-MM-yyyy"); //"EEE dd-MM-yyyy"); 
           	       JTextBoxWithEditButtons textEditFormatedDate = new JTextBoxWithEditButtons(); 
                       JTextComponent ta = (JTextComponent)textEditFormatedDate.getTextComp();
                       ta.setText(fdate);
                       ta.setEnabled(false);
                       pnlAfm.add(ta);
                      }
                      else if(esrn.classtype.equalsIgnoreCase("java.lang.String"))
                      {
                      JTextField txtValue = new JTextField(30);
                      txtValue.setText(esrn.value);
                      pnlAfm.add(txtValue);

                        txtValue.setEnabled(false);

                        if(esrn.nameNode.equalsIgnoreCase("firm_act_descr"))
                        {
                           btnRdioGroup.add(radioBtnNew);
                        }
                      
                      
                      }
                      else 
                      {
                      JTextField txtValue = new JTextField(30);
                      txtValue.setText(esrn.value);
                      txtValue.setEnabled(false);
                      pnlAfm.add(txtValue);
                      }                      
                     
                       listNewFieldNamesAndValues.add(esrn);      
                            
                      
                      
                   }
                     
                 }
                 
                 
          pnl.add(pnlAfm,BorderLayout.PAGE_START);
          dlg.setEntity(pnl,PANEL_TYPE_ANY, title,showOkButton);
          dlg.display();
        
          if(!dlg.getIsCancelClicked())
          {
            System.out.println("PanelOneDataOneRecData.displayDialogCheckValidation OK clicked");
            
            for(int f =0;f<dbFieldsAll.length;f++)
            {
                //System.out.println("PanelOneDataOneRecData.displayDialogCheckValidation B  f:"+f);
                 for(int e= 0;e<listNewFieldNamesAndValues.size();e++)
                 {
                     JRadioButton radioBtn = (JRadioButton)listRadioBtns.get(e);
                EntitySoapResponseNamesNValues esrn = (EntitySoapResponseNamesNValues)listNewFieldNamesAndValues.get(e);
               
                          if(esrn.nameDb.equalsIgnoreCase((dbFieldsAll[f].getDbField())))
                          {                
                              //System.out.println("PanelOneDataOneRecData.displayDialogCheckValidation B  :"+esrn.nameDb+" "+radioBtn.isSelected());
                              if(radioBtn.isSelected())
                              {
                            JTextField txtF = (JTextField)fieldTxts.get(f);
                            txtF.setText(esrn.value);
                            
                            System.out.println("PanelOneDataOneRecData.displayDialogCheckValidation OK   dbname:"+esrn.nameDb+" = "+esrn.value);
                              }
                          }
                 }
            }
                          
                 
                 }//if user n pass is empty
                 //txtArea.setText(afmXml);
             }
             else
             {
               
             }

          

            
                  
        } // is AFM
        

        
  
        
     
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        
   }
   
  /*
   *  parts also included in report record zoom in ReportAreaGenerated.clickedOnRow()
   */
   private void displayDialogNew(String foreignTable, int dbCol)
   { 
      // Component comp =this.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
       //System.out.println(comp.getClass());
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));


        
        
        
        
        
        PanelEditOneDataRec panelEODR = new PanelEditOneDataRec(frame);

        String primKeyValue = "0";
        String primKey ;
        primKey = primKeyValue;      ///panelOneDataManyRecData.getPrimKey();
        
   /*     if(isNewRec)
        {
            primKeyValue="0";
        }
        else
        {
              primKeyValue = PanelOneDataManyRecData.getPrimKeyValue();
        }        
*/


        LookUpMgt lookUp = new LookUpMgt();
        
        
        String[] fieldsOnTitle=lookUp.getFieldsOnTitle(foreignTable);
         String[] fieldsOnTitleCaption=lookUp.getFieldsOnTitleCaption(foreignTable);
        String editTitle=lookUp.getStrOfOne(foreignTable);
        EntityPanel[] entityPanel = lookUp.getEntityPanel(foreignTable);
  //      String primKey = lookUp.getLookUpKey(foreignTable);
        ImageIcon iconLU=lookUp.getIcon(foreignTable);
        String queryLookUp = lookUp.getQuery(foreignTable);
        String queryLookUpWhere = lookUp.getQuerySubqueryWhere(foreignTable);
        String queryLookUpIsActive = lookUp.getQuerySubqueryIsActive(foreignTable);
        String queryOrderByLookUp = lookUp.getQueryOrderBy(foreignTable);
   //     panelEditOneDataRec.setEntity(entity, entityPanel,fieldsOnTitle,fieldsOnTitleCaption,false,primKey,primKeyValue,primKeyDb,null,null,/*query,*/
   //     editTitle,ico,true,isNewRec,isNewRecFromCopy,true,categoryNodes, false);	
        String[] categoryNodes = lookUp.getCategoryNodes(foreignTable); // when it is new show only panel for edit

           if(utilsString.hasQueryWhere(queryLookUpWhere))
           {
               queryLookUp=queryLookUp+" "+queryLookUpWhere+" AND "+foreignTable+"."+lookUp.getLookUpKey(foreignTable)+" LIKE '"+primKeyValue+"'";//+rs.getString(primKey)+"'";//+" "+queryLookUpIsActive; //queryLookUpIsActive already has AND
           }
           else
           {
               queryLookUp=queryLookUp+" WHERE "+foreignTable+"."+lookUp.getLookUpKey(foreignTable)+" LIKE '"+primKeyValue+"'";//+rs.getString(primKey)+"' ";//+" "+queryLookUpIsActive;//queryLookUpIsActive already has AND
           }
        


        
        
        //System.out.println("panelOneDataManyRec.displayDialogEdit primKey:"+primKey+" primKeyValue:"+primKeyValue+"  queryReadOnly:"+queryReadOnly);

        
       //System.out.println("PanelODORData.displayDialogEdit   queryLookUp:"+queryLookUp+"     queryOrderByLookUp:"+queryOrderByLookUp+"  primKey:"+primKey+"       selectedKeyValue:"+selectedKeyValue); 
        panelEODR.setEntity(foreignTable, entityPanel,fieldsOnTitle,fieldsOnTitleCaption,false,primKey,primKeyValue,primKeyDb,/*formGlobalTableToGet1,formGlobalTableToApply1,*//*primKeyDb  (set to primKey instead of primKeyDb because in panelEditOneDataRec of buyers to the product statistics page there was an error in the title)*/
       /*, null,null,*/"",editTitle,iconLU,/*true,*/true,isNewRecFromCopy,true,categoryNodes, false, panelManagement);//,entityReport);	
     
        
        
        
        
        
        
   //     String editTitle=strOfOne;
  //      panelEODR.setEntity(entity, entityPanel,fieldsOnTitle,fieldsOnTitleCaption,false,primKey,primKeyValue,primKeyDb,/*null,null,*/queryReadOnly,
  //      editTitle,ico/*,true*/,isNewRec,isNewRecFromCopy,true,categoryNodes, false,panelManagement);//,entityReportForm);	
    	

        panelEODR.setVisible(true);
        
        DialogMulti dlg = new DialogMulti(frame);
        dlg.setEntity(panelEODR,PANEL_TYPE_EDITONEDATAONEREC, "επεξεργασία "+editTitle,true);
        
        dlg.display();        
        
        
        
        
        
        
        
     
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
   }   
   
   

   /*
   *  parts also included in report record zoom in ReportAreaGenerated.clickedOnRow()
   */
   private void displayDialogEdit(String foreignTable, int dbCol)
   { 
      // Component comp =this.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
       //System.out.println(comp.getClass());
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));

       String fieldVariableFromPreField = dbFieldsInGroupOfPanels[dbCol].getFormVariableFromField();
      int intFieldToGetTheValue = -1;
      for(int f= 0 ;f<dbFieldsInGroupOfPanels.length;f++)
      {
          if(dbFieldsInGroupOfPanels[f].getDbField().equalsIgnoreCase(fieldVariableFromPreField))
          {
              intFieldToGetTheValue=f;
          }
      }       
      String strValueFromField = calculateVarFromPreFieldAndSetGlobal(dbCol);
      /*if(intFieldToGetTheValue!=-1)
      {
           JTextField tbf = (JTextField) fieldTxts.get(intFieldToGetTheValue);
            strValueFromField= tbf.getText();
            setVariablesGlobal1(fieldVariableFromPreField,strValueFromField);
      }*/        
        
       String selectedKeyValue ="";

       JTextField tb = (JTextField) fieldTxts.get(dbCol);
       selectedKeyValue = tb.getText();
       
       JTextField tb2 = (JTextField) fieldTxts2.get(dbCol); // is needed later in focus
       String tb2Text=tb2.getText();
       
       //System.out.println("panelODORData.showDialogLookUp "+dbCol+" "+selectedKeyValue+" tb2Text "+tb2Text);
       
 // System.out.println("---panelODORData.displayDialogLookUp A  dbCol:"+dbCol+"    selectedKeyValue:"+selectedKeyValue+"   tbText class:"+fieldTxts.get(dbCol).toString()+"   tb2Text:"+tb2Text);
     //  String selected="";
//       if(selectedKeyValue.equalsIgnoreCase(""))
 //      {
       	String sub="";
       System.out.println("PanelODORData.displayDialogEdit    00000000     selectedKeyValue:"+selectedKeyValue+"   dbCol:"+dbCol+"   foreignTable:"+foreignTable);
     if(!selectedKeyValue.equals(""))
     {
       //PanelOneDataManyRecData PanelOneDataManyRecData;
       PanelEditOneDataRec  panelEditOneDataRec = new PanelEditOneDataRec(frame);

        //System.out.println("panelOneDataManyRec.displayDialogEdit primKeyValue "+primKeyValue);        
        //System.out.println("panelOneDataManyRec.displayDialogEdit primKey"+primKey);
                 
        //System.out.println("panelOneDataManyRec primKeyValue "+selectedKeyValue);
        LookUpMgt lookUp = new LookUpMgt();
        
        
        String[] fieldsOnTitle=lookUp.getFieldsOnTitle(foreignTable);
         String[] fieldsOnTitleCaption=lookUp.getFieldsOnTitleCaption(foreignTable);
        String editTitle=lookUp.getStrOfOne(foreignTable);
        EntityPanel[] entityPanel = lookUp.getEntityPanel(foreignTable);
        String primKey = lookUp.getLookUpKey(foreignTable);
        ImageIcon iconLU=lookUp.getIcon(foreignTable);
        String queryLookUp = lookUp.getQuery(foreignTable);
        String queryLookUpWhere = lookUp.getQuerySubqueryWhere(foreignTable);
         String queryLookUpWhereForFormVariable = lookUp.getQueryWhereForFormVariable(foreignTable);
        String queryLookUpIsActive = lookUp.getQuerySubqueryIsActive(foreignTable);
        String queryOrderByLookUp = lookUp.getQueryOrderBy(foreignTable);
   //     panelEditOneDataRec.setEntity(entity, entityPanel,fieldsOnTitle,fieldsOnTitleCaption,false,primKey,primKeyValue,primKeyDb,null,null,/*query,*/
   //     editTitle,ico,true,isNewRec,isNewRecFromCopy,true,categoryNodes, false);	
   

   
   
 	if(lookUp.getLookUpField(foreignTable).length>=1)
   	   	{
   	   	    // getLookUpField is array
   	   		int len = lookUp.getLookUpField(foreignTable).length;
   	   		for(int o= 0; o<len ;o++)
   	   		{
   	   		   
   	   		   String end ="";	
   	   		   	if(o==0)
   	   		   	{
   	   		   	   	sub=sub+lookUp.getLookUpField(foreignTable)[o]+" LIKE '"+tb2Text+"%'";
   	   		   	}
   	   		   	else
   	   		   	{
   	   		   		sub=sub+" OR "+lookUp.getLookUpField(foreignTable)[o]+" LIKE '"+tb2Text+"%'";
   	   		   	}
   	   		   	
   	   		}
   	   	}
       	  String qSub = "";
         String  qSubForVar = "";
          //String queryLookUpWhere = lookUp.getQuerySubqueryWhere(foreignTable);
       	 
          
          
     // if(formGlobalTableToApply1!=null && !formGlobalTableToApply1.equalsIgnoreCase(""))
     // {
      //    if(!formGlobalTableToApply1.equalsIgnoreCase(foreignTable))             //if(entityIn.equalsIgnoreCase(VariablesGlobal.globalformGlobalTableToGet1))
         // {  
         if(fieldVariableFromPreField!= null && !fieldVariableFromPreField.equalsIgnoreCase(""))
         {
           if(utilsString.hasQueryWhere(queryLookUpWhere))
   	  {
       	      qSub = " AND( "+sub+" ) ";//lookUp.getLookUpField(foreignTable)+" LIKE '"+tb2Text+"%' "
   	  }
   	  else
   	  {
   	       	  qSub = " WHERE "+sub;//lookUp.getLookUpField(foreignTable)+" LIKE '"+tb2Text+"%' "
   	  }  
           
         
          
         }
      //}
        
          
           
     System.out.println("---panelODORData.displayDialogLookUp   dbCol:"+dbCol+"   selectedKeyValue:"+selectedKeyValue+"      tb2Text:"+tb2Text);
  
        String subQueryFilterFromRecType="";

  //String queryLookUpIsActive = lookUp.getQuerySubqueryIsActive(foreignTable);

     String queryLUWithQ ="";
      //if(formGlobalTableToApply1!=null && !formGlobalTableToApply1.equalsIgnoreCase(""))
         if(fieldVariableFromPreField!= null && !fieldVariableFromPreField.equalsIgnoreCase(""))
         {
          //if(formGlobalTableToApply1.equalsIgnoreCase(foreignTable))             //if(entityIn.equalsIgnoreCase(VariablesGlobal.globalformGlobalTableToGet1))
          //{

          //String queryLookUpWhereTableOfForm = lookUp.getQuerySubqueryWhere(foreignTable);
         // queryClosingSubquery =  subQueryFilterFromRecType;      //queryLookUpWhereTableOfForm+subQueryFilterFromRecType;
          subQueryFilterFromRecType =  strValueFromField +" ) "; 
          System.out.println(" panelODORData.displayDialogLookUp    IF         foreignTable:"+foreignTable+"     subQueryFilterFromRecType:"+subQueryFilterFromRecType+"       VariablesGlobal.globalformGlobalVariable1"+VariablesGlobal.globalformGlobalVariable1);
           queryLUWithQ = lookUp.getQuery(foreignTable)+" "+queryLookUpWhereForFormVariable+" "+subQueryFilterFromRecType+" "+qSubForVar ;//+" "+queryLookUpIsActive+" "+lookUp.getQueryOrderBy(foreignTable);
           queryLUWithQ=queryLUWithQ+" AND "+foreignTable+"."+lookUp.getLookUpKey(foreignTable)+" LIKE '"+selectedKeyValue+"'";//+rs.getString(primKey)+"'";//+" "+queryLookUpIsActive; //queryLookUpIsActive already has AND
        } 
        else
        {
          
          System.out.println(" panelODORData.displayDialogLookUp    ----+++-----         foreignTable:"+foreignTable+"     VariablesGlobal.globalformGlobalVariable1"+VariablesGlobal.globalformGlobalVariable1);
          queryLUWithQ = lookUp.getQuery(foreignTable)+" "+queryLookUpWhere+" "+subQueryFilterFromRecType+" "+qSub ;//+" "+queryLookUpIsActive+" "+lookUp.getQueryOrderBy(foreignTable);
        
          if(utilsString.hasQueryWhere(queryLookUpWhere))
           {
               //queryLookUp=queryLookUp+" "+queryLookUpWhere+" AND "+foreignTable+"."+lookUp.getLookUpKey(foreignTable)+" LIKE '"+selectedKeyValue+"'";//+rs.getString(primKey)+"'";//+" "+queryLookUpIsActive; //queryLookUpIsActive already has AND
               queryLUWithQ=queryLUWithQ+" AND "+foreignTable+"."+lookUp.getLookUpKey(foreignTable)+" LIKE '"+selectedKeyValue+"'";//+rs.getString(primKey)+"'";//+" "+queryLookUpIsActive; //queryLookUpIsActive already has AND
           }
           else
           {
                queryLUWithQ=queryLUWithQ+" WHERE "+foreignTable+"."+lookUp.getLookUpKey(foreignTable)+" LIKE '"+selectedKeyValue+"'";//+rs.getString(primKey)+"' ";//+" "+queryLookUpIsActive;//queryLookUpIsActive already has AND
           }          


// subQueryFilterFromRecType = ""; //for lookup dialog of no globalvariable
             //System.out.println("ELSE ELSE     panelODORData.displayDialogLookUp    ELSE       globalformGlobalVariable1:"+VariablesGlobal.globalformGlobalVariable1+"    foreignTable:"+foreignTable);
       }
        //   coppied from UtilsPanelReport.getLookupValue "end"  ------------- not double checked
 
      //String queryLookUp = lookUp.getQuery(foreignTable)+" "+queryLookUpWhere+" "+subQueryFilterFromRecType+" "+queryLookUpIsActive+" "+queryOrderByLookUp;      
            

      
     // System.out.println(" BEFORE panelODORData.displayDialogLookUp   "+foreignTable+"    selectedKeyValue:"+selectedKeyValue+"       subQueryFilterFromRecType:"+subQueryFilterFromRecType+"        queryLookUp:"+queryLookUp);
      

    
            
            // queryLUWithQ = lookUp.getQuery(foreignTable)+" "+queryLookUpWhere+" "+subQueryFilterFromRecType+" "+qSub ;//+" "+queryLookUpIsActive+" "+lookUp.getQueryOrderBy(foreignTable);
    
   
   
   
   
   
   
   
   
   
   
 
        
       //System.out.println("PanelODORData.displayDialogEdit   queryLookUp:"+queryLookUp+"     queryOrderByLookUp:"+queryOrderByLookUp+"  primKey:"+primKey+"       selectedKeyValue:"+selectedKeyValue); 
       int selected = panelEditOneDataRec.setEntity(foreignTable, entityPanel,fieldsOnTitle,fieldsOnTitleCaption,false,primKey,selectedKeyValue,primKey,/*formGlobalTableToGet1,formGlobalTableToApply1,*//*primKeyDb  (set to primKey instead of primKeyDb because in panelEditOneDataRec of buyers to the product statistics page there was an error in the title)*/
       /*, null,null,*/queryLUWithQ/*queryLookUp*/,editTitle,iconLU,/*true,*/false,isNewRecFromCopy,true,null, false, panelManagement);//,entityReport);	
                                     
     if(selected == 0)// when 0 do not display
     {
         System.out.println("PanelODORData.displayDialogEdit      selected:"+selected);
          utilsGui.showMessageInfo("Η εγγραφή δεν υπάρχει. Πιθανόν είναι ανενεργή.");
     }
     else
     {

        panelEditOneDataRec.setVisible(true);
        
        DialogMulti dlg = new DialogMulti(frame);
        dlg.setEntity(panelEditOneDataRec,PANEL_TYPE_EDITONEDATAONEREC, "επεξεργασία "+editTitle,true);
        dlg.display();       

       if (!selectedKeyValue.equalsIgnoreCase("")) // means selected cancel
       {
          //tb.setText(selectedKeyValue.trim());
       
           //needed for tb2 to be updated in the following order
           tb.requestFocus();
           tb2.requestFocus();
           tb.requestFocus();
       }
       else
       {    tb.requestFocus();  }
       
     }
       
      }
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
   }

   /*
   *calculate the sum textboxes like count vatsum
   *  called by this rowSaveAll,  and PanelODMRData
   */
   public void calculateSumFields()
   {
       //System.out.println(" O==O PanelODORData.calculateSumFields   dbFieldsInGroupOfPanels"+dbFieldsInGroupOfPanels);
              int colCount =dbFieldsInGroupOfPanels.length;
              ArrayList listRetSavedAll = new ArrayList();
              
      for(int i = 0;i<dbFieldsInGroupOfPanels.length;i++) 
      {
     
         String cName = fields[i]; //get colunm name  
         int intTable =  dbFieldsInGroupOfPanels[i].getTableFromWhichTheSumWillBeCalculated();
        //System.out.println("PanelODORData.calculateSumFields:    i:"+i+"      cName:"+cName+"    intTable:"+intTable+"     fieldTxts.size:"+fieldTxts.size());
         
         if(intTable!=-1)  //none
         {
                 String columnName = fields[i]; //get colunm name  	           	
	         String classtype = dbFieldsInGroupOfPanels[i].getColClassName();  // if integer then not add ' and ' between values 
                 int intFieldOfTable =  dbFieldsInGroupOfPanels[i].getFieldOfTableThatWillBeSummed();
                 //System.out.println("-------------------------- PanelODORData.calculateSumFields   ("+i+")      intFieldOfTable:"+intFieldOfTable+"     columnName:"+columnName+"   classtype:"+classtype+"   intTable:"+intTable+"     fieldTxts.size:"+fieldTxts.size());
                  if(classtype.equalsIgnoreCase("table"))//.getGroupOfComps() ==-1)
                  {
                          
                   }
                   else
                  {
                   PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(intTable);
                   int rowCount = pnlODMRData.getRowCount();
                   
                   double value = 0;
                   int intValue = 0;
              	  //System.out.println(" O-O PanelODORData.calculateSumFields: i:"+i+"  intTable:"+intTable+"  classtype:"+classtype+"  intFieldOfTable:"+intFieldOfTable+"  rowCount:"+rowCount);
                 
                  for(int r=0;r<rowCount;r++)
                   {
                       String  strValue = pnlODMRData.getTableValueAt(r,intFieldOfTable)+"";
                 //      System.out.println(" O-O PanelODORData.calculateSumFields:  r:"+r+"("+rowCount+")   c:"+i+"   intFieldOfTable:"+intFieldOfTable+"         columnName:"+columnName+"="+strValue);
                       
                       if(strValue== null || strValue.equalsIgnoreCase(""))// || strValue.equalsIgnoreCase("null"))
                       {
                           
                       }
                       else
                       {
                            value=value+Double.parseDouble(strValue);     
                       }
                      
                      // System.out.println(" O PanelODORData.calculateSumFields: r:("+r+")  intFieldOfTable:"+intFieldOfTable+"    "+pnlODMRData.getTableValueAt(r,intFieldOfTable)+"   value:"+value);
                      
                   }
                   //tb.setText(field);
                   
                       JTextComponent tb = (JTextComponent) fieldTxts.get(i);
                       int intTypeOfSum =  dbFieldsInGroupOfPanels[i].getTypeOfSum();
                      //System.out.println(" - PanelODORData.calculateSumFields:  i:"+i+"  getvalue A      classtype:"+classtype+" intFieldOfTable:"+intFieldOfTable+"     intTypeOfSum:"+intTypeOfSum+"     value:"+value);
                       String strValue = "";
                       if(classtype.equalsIgnoreCase("java.lang.Double"))
                       {
                           //strValue=value+"";
                           
                           strValue = utilsDouble.getDoubleReading(value,false);
                           //System.out.println("PanelODPRData  +++ "+strValue);
                       }
                       else if(classtype.equalsIgnoreCase("java.lang.Integer"))
                       {
                           // intValue=Math.ceil(value);
                            intValue = (int) Math.round(value);
                            if(intValue==0)
                            {
                                strValue="";
                            }
                            else
                            {
                               strValue=intValue+"";
                            }
                       }
                       else
                       {
                           strValue=value+"";
                       }
                       //System.out.println(" - PanelODORData.calculateSumFields:  i:"+i+"  getvalue B  classtype:"+classtype+"   intFieldOfTable:"+intFieldOfTable+"     intTypeOfSum:"+intTypeOfSum+"     value:"+value);
                       
                       if(intTypeOfSum==DBFIELD_TYPE_OF_SUM_SUM)
                       {
                           //System.out.println("PanelODORData.calculateSumFields  DBFIELD_TYPE_OF_SUM_SUM ");
                           tb.setText(strValue.toString());
                           if(!strValue.toString().equalsIgnoreCase(tb.getText()))
                           {
                               System.out.println(" --> error  PanelODORData.calculateSumFields:  i:"+i+"     intFieldOfTable:"+intFieldOfTable+"     strValue:"+strValue+"  tb.getText:"+tb.getText() +"  txt value different than the value holds-->change size of textbox");
                           }
                       }
                       else if(intTypeOfSum==DBFIELD_TYPE_OF_SUM_COUNT)
                       {
                           //System.out.println("PanelODORData.calculateSumFields  DBFIELD_TYPE_OF_SUM_COUNT ");
                           tb.setText(rowCount+"");
                       }
                       else
                       {
                           System.out.println(" error  PanelODORData.calculateSumFields  intTypeOfSum:"+intTypeOfSum+"  UNKNOWN TYPE   strValue:"+strValue);
                       }
                       
                         //System.out.println(" error  PanelODORData.calculateSumFields  intTable:"+intTable+"       rowCount:"+rowCount+"     strValue:"+strValue);
                  }
                  
                  
                   //System.out.println("PanelODORData.calculateSumFields before dbFieldsCalculateSet  hasDataChanged:"+hasDataChanged+"  cName:"+cName+"   i:"+i+"     intTable:"+intTable);
                  
   //                dbFieldsCalculateSet( dbFieldsInGroupOfPanels,i,"");   // empty is 'foreigntable' which is not needed
                  
         }
         else
         {
              
             //System.out.println("PanelODORData.calculateSumFields    ELSE  i:"+i+"     cName:"+cName+"      intTable:"+intTable+"    fieldTxts.size:"+fieldTxts.size()+"    possibly not have the correct no for table that will be summed");
         }

      }
             //System.out.println("PanelODORData.calculateSumFields after dbFieldsCalculateSet  hasDataChanged:"+hasDataChanged+"    dbFieldsInGroupOfPanels.length:"+dbFieldsInGroupOfPanels.length);
             //dbFieldsCalculateSet(); 
             
      for(int i = 0;i<dbFieldsInGroupOfPanels.length;i++) 
      {
     
         String cName = fields[i]; //get colunm name  
         int intTable =  dbFieldsInGroupOfPanels[i].getTableFromWhichTheSumWillBeCalculated();
        //System.out.println("PanelODORData.calculateSumFields:    i:"+i+"      cName:"+cName+"    intTable:"+intTable+"     fieldTxts.size:"+fieldTxts.size());
         
         //if(intTable!=-1)  //none
         //{
                 String columnName = fields[i]; //get colunm name  	           	
	         String classtype = dbFieldsInGroupOfPanels[i].getColClassName();  // if integer then not add ' and ' between values 
                 int intFieldOfTable =  dbFieldsInGroupOfPanels[i].getFieldOfTableThatWillBeSummed();
                 
                 dbFieldsCalculateSet( dbFieldsInGroupOfPanels,i,"");
                 
         //}
      }
             
             
             
             
             setVisibleOrEditableFields(false);
            
       

      
   }
   
   private void showHtmlFormElements(int i, ResultSet rsin) throws SQLException
   {
       
                 String colName =   dbFieldsInGroupOfPanels[i].getDbField();
          String colTableName =dbFieldsInGroupOfPanels[i].getTableName();
          String colCaption =   dbFieldsInGroupOfPanels[i].getCaption();
          int columnWidth = dbFieldsInGroupOfPanels[i].getColWidth();
      	 //System.out.println("panelODORData.showRow i ("+i+") colName:"+colName+" class:"+dbFieldsInGroupOfPanels[i].getColClassName());
          String classtype = dbFieldsInGroupOfPanels[i].getColClassName();
   
                 
              EntityDBFields[] childFields = dbFieldsInGroupOfPanels[i].getDbChildFields();
              String strTableDocData = dbFieldsInGroupOfPanels[i].getChildTable();              

                  
                  
                String queryForHtmlFileDocRead =  dbFieldsInGroupOfPanels[i].getSqlTableChildRead();
     

              
              
 
    try
    {
        TimeUnit.SECONDS.sleep(1);
    }
    catch(InterruptedException ie)
    {
        ie.printStackTrace();
    }                  
 
                  String sqlDbParentWhereKey ="";    
                    for(int f = 0;f<childFields.length;f++)
                    {
                        
                          String fieldDBName = childFields[f].getDbField();
                          
                          //System.out.println("PanelODORData.showRow      f"+f+"   fieldDBName:"+fieldDBName);
                          int fieldTypeFromParentTable = childFields[f].getPrimaryKeyIntegerAutoInc();
                          
                          
                          //String fieldTableName = childFields[f].getTableName();
                          //String fieldClassName = childFields[f].getColClassName();
                          String strDefaultValue = childFields[f].getDefaultValue();
                           if(fieldTypeFromParentTable == FIELD_PRIMARY_KEY_AUTOINC)
                           {
                              // dbChildPrimaryKey = fieldDBName;
                               //subQueryFieldNames = subQueryFieldNames+" "+fieldDBName+", ";
                               //subQueryFieldValues = subQueryFieldValues + "0"+", ";                                 
                           }
                           else if(fieldTypeFromParentTable == FIELD_PRIMARY_KEY_FROM_PARENTTABLE)
                           {
                              // subQueryFieldNames = subQueryFieldNames+" "+fieldDBName+", ";
                              // subQueryFieldValues = subQueryFieldValues +" " +pkFromOnePanelForHtmlForm+", ";
                            //   sqlDbParentWhereKey = fieldDBName+" = '"+pkFromOnePanelForHtmlForm+"', ";// this ha quotation marks
                               
                   try
                   {
             if(rsin!=null && rsin.getString(fieldDBName)!=null && !rsin.getString(fieldDBName).equalsIgnoreCase(""))
             {
                 if(classtype.equalsIgnoreCase("java.awt.image.BufferedImage"))
                 {
                   // blobImage =  rsin.getBlob(colName);
                 }
                 else
                 {
                   sqlDbParentWhereKey =sqlDbParentWhereKey + fieldDBName+" LIKE "+ rsin.getString(fieldDBName);
                 }                        
             }                                
                               

                   }
                    catch(SQLException e)
                    {
                        System.out.println("PanelODORData.showHtmlFormElements   'htmlfile'  A    fieldDBName:"+fieldDBName+"   msg:"+e.getMessage());
                        e.printStackTrace();
                        
                    }                               
                           }
                    }// for
                    
         
          String q = queryForHtmlFileDocRead+" AND "+sqlDbParentWhereKey;

                 try
                 {
                       
                    System.out.println(" PanelODORData.showHtmlFormElements   ===           q:"+q+"             queryForHtmlFileDocRead"+queryForHtmlFileDocRead);
                     db.retrieveDBDataFromQuery(q, "PanelODORecData.showHtmlFormElements");
                     ResultSet rsDoc = db.getRS();
                     //ResultSetMetaData rsmdDoc = db.getRSMetaData();
                     int intDocCount = db.getRecordCount();
                                         

                        rsDoc.first();              

                        for(int d = 1;d<=intDocCount;d++)
                        {
                           rsDoc.absolute(d);
                     //System.out.println("   strFieldWhere:"+strFieldWhere+"   strFieldValue:"+strFieldValue);
                     String strField = rsDoc.getString(strReadFieldWhere);
                     String strValue = rsDoc.getString(strReadFieldValue);
                   //  String strField2 = rsDoc.getString(strFieldWhere2);
                   //  String strValue2 = rsDoc.getString(strFieldValue2);                     
                    // System.out.println("calculationFromToolBarButton   strField:"+strField+"   strValue:"+strValue);

                     String strJs =   "document.getElementsByName('" + strField + "')[0].value='" + strValue + "';";
                     System.out.println("PanelODORData.showHtmlFormElements  "+intDocCount+"     "+strJs);
                         //panelHtmlBrowser.addFieldsToTextFieldsList(strFieldsNValuesAdditional[f],strFieldsNValuesAdditionalValue[f]); 
                         listHtmlFieldsAndValues.add(strJs);
                    // panelHtmlBrowser.addFieldsToTextFieldsList(strField, strValue); // for one text, ie subject to vat
                     
                      //   listHtmlFieldsAndValues.add(strDocField+strField);
                             
                   //  panelHtmlBrowser.setTextToField(strField2, strValue2); // for two text, ie vat
                  //   listHtmlFieldsAndValues.add(strDocField2+strField2);
                     //panelHtmlBrowser.setTextTofield("declaration.document.content.f303", "1000.10");                            
                        }
                        
                       
                       rsDoc.close();
                    }
                    catch(SQLException e)
                    {
                        System.out.println("PanelODORData.showHtmlFormElements   'htmlfile'  B  "+e.getMessage());
                        e.printStackTrace();
                        
                    }
                     
                         
                  
       
   }
   
   /*
   *     for tables to show
   */
  /* public void setTemplateReplacementToSave(String subqueryIsNotTemplateIn, String subqueryIsTemplateIn)
   {
       qIsTemplateToBeReplaced=subqueryIsNotTemplateIn;
       qIsTemplateToReplace=subqueryIsTemplateIn;
   }*/
 
      /*
   *     for tables to save
   */
   /*public void setTemplateReplacementToShow(String subqueryIsNotTemplateIn, String subqueryIsTemplateIn)
   {
       qIsTemplateToBeReplaced=subqueryIsTemplateIn;
       qIsTemplateToReplace=subqueryIsNotTemplateIn;
   }*/
   
   /*
    * 
    * called by this.showSpecificRow
    */
    private void showRow(ResultSet rs) throws SQLException
    {
        setVariablesGlobal1Erase();
       //  ArrayList  listDbFieldsAll =  new ArrayList();
     //System.out.println("panelODORData.showRow: sizes "+fieldTxts.size() +" "+fieldTxts2.size() +" "+ rsmd.getColumnCount());
      if(fieldTxts.size() == fieldTxts2.size() && fieldTxts2.size() == dbFieldsInGroupOfPanels.length) 
      {
          
      }
      else
      {
      	// they should be the same size
      	System.out.println("error ---  panelODORData.showRow: sizes fieldTxts:"+fieldTxts.size() +" fieldTxts:"+fieldTxts2.size() +" dbFieldsInGroupOfPanels.length:"+ dbFieldsInGroupOfPanels.length);//rsmd.getColumnCount());
      }
      //System.out.println("panelODORData.showRow:----");
      
      if(fieldTxts.size()!=fieldTxts2.size() && fieldTxts2.size()!=dbFieldsAll.length)
      {
          System.out.println("PanelODORData.showRow -------  sizes different  fieldTxts:"+fieldTxts.size() +" fieldTxts:"+fieldTxts2.size() +" dbFieldsAll.length:"+ dbFieldsAll.length);//rsmd.getColumnCount());
      }
      
      boolean isFirstFieldAutoInc=false;

      for(int i = 0;i<dbFieldsInGroupOfPanels.length;i++)
      //for(int i = 0;i<dbFieldsAll.length;i++)  
      {
          //int fieldCount = i;//i-1; // calculates the no of field starting from 0 when i = 1
          String colName =   dbFieldsInGroupOfPanels[i].getDbField();
          String colTableName =dbFieldsInGroupOfPanels[i].getTableName();
          String colCaption =   dbFieldsInGroupOfPanels[i].getCaption();
          int columnWidth = dbFieldsInGroupOfPanels[i].getColWidth();
      	 //System.out.println("panelODORData.showRow i ("+i+") colName:"+colName+" class:"+dbFieldsInGroupOfPanels[i].getColClassName());
          String classtype = dbFieldsInGroupOfPanels[i].getColClassName();
          int isPrimaryKeyIntegerAutoInc =  dbFieldsInGroupOfPanels[i].getPrimaryKeyIntegerAutoInc();

          colName = colTableName+"."+colName;
          
          
/*--          int colForRS = i+1;
          if(intGroupOfPanelsToShow==0)
          {
              //System.out.println("panelODORData.showRow  "+intGroupOfPanelsToShow+"=0  ("+i+") "+colName+"  colForRS:["+colForRS+"] = "+rs.getString(colForRS));     
          }
          else if(intGroupOfPanelsToShow>0 )
          {
              
              colForRS = (Integer)listLengthDbFieldsInGroupOfPanels.get(intGroupOfPanelsToShow)+i+1;//dbFieldsAll.length-dbFieldsInGroupOfPanels.length-dbFieldsInGroupOfPanels.length+i+2;//intOfListLengthDbFieldsInGroupOfPanels+1+i;
             //System.out.println("PanelODOR.showRow intOfListLengthDbFieldsInGroupOfPanels:"+intOfListLengthDbFieldsInGroupOfPanels+" ---- "+(dbFieldsAll.length-dbFieldsInGroupOfPanels.length-dbFieldsInGroupOfPanels.length+i+2));         
              //System.out.println("panelODORData.showRow   "+intGroupOfPanelsToShow+"=1  ("+i+") "+colName+"  colForRS:["+colForRS+"] = "+rs.getString(colForRS));//+"     ["+((Integer)listLengthDbFieldsInGroupOfPanels.get(intGroupOfPanelsToShow)+i+1) +"]");
          }
--*/          
          
         
          
          
          String foreignTable = dbFieldsInGroupOfPanels[i].getLookupEntityName();//databaseTableMeta.findForeignTable(columnLabel);  
          //System.out.println(" PanelODORData.showRow    i:"+i+"    colName:"+colName+"   class name:"+dbFieldsInGroupOfPanels[i].getColClassName()+"    lookup type:"+dbFieldsInGroupOfPanels[i].getLookupType());
          if (dbFieldsInGroupOfPanels[i].getLookupType()==LOOKUPTYPE_ONLYONE_THISFIELD && foreignTable!= null )
          {             
              // show field value in tb
               
              
               
               //System.out.println("panelODORData.showRow 1b --- "+i+" "+field);
              JTextComponent tbe = (JTextComponent)fieldTxts.get(i);//i-1);
              
              /*if (!field.equalsIgnoreCase("") || field!=null)
              { field.trim(); }*/
              
         
              int intFieldToGetTheValue = calculateAllFieldsForFormVariable1(); 
              //System.out.println("PanelODoRData.showRow     foreignTable:"+foreignTable+"  colName:"+colName+"   i:"+i+" intFieldToGetTheValue:"+intFieldToGetTheValue);
          String fieldVariableFromPreField =  "";
          if(dbFieldsInGroupOfPanels!=null && intFieldToGetTheValue!=-1)
          {
              if(intFieldToGetTheValue < dbFieldsInGroupOfPanels.length )// 
              {
                 fieldVariableFromPreField = dbFieldsInGroupOfPanels[intFieldToGetTheValue].getDbField();
              }
              else
              {
                  System.out.println("error -> PanelODoRData.showRow     foreignTable:"+foreignTable+"  colName:"+colName+"   i:"+i+" intFieldToGetTheValue:"+intFieldToGetTheValue+" < "+dbFieldsInGroupOfPanels.length);
              }
          }
          
            if(dbFieldsInGroupOfPanels[i].getColClassName().equalsIgnoreCase("table"))
          {
              /* PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(i);//,PanelOneDataManyRecData);
              int lenClildFields = pnlODMRData.getDbFieldsChild().length;
              EntityDBFields[] edbfChilds = pnlODMRData.getDbFieldsChild();
              System.out.println("PanelODoRData.showRow     TABLE     colName:"+colName+"   lenClildFields:"+lenClildFields);  
              for(int r = 0;r<lenClildFields;r++)
              {
                  System.out.println("PanelODoRData.showRow    TABLE   colName:"+colName+"   i:"+i+"   edbfChilds:"+edbfChilds[r].getDbField()+"  fieldVariableFromPreField:"+fieldVariableFromPreField);
              }              
              */
          }
          else
          {
              if(!tbe.hasFocus())
              {
              	String field = "";

                        //field = rs.getString(colName);
                        field = rs.getString(colName);//fieldVariableFromPreField);
              	        tbe.setText(field);
                        
  /*                  if(intFieldToGetTheValue!=-1 && !colName.equalsIgnoreCase(fieldVariableFromPreField))
                    {

                         String strValueFromField = "";
                         JTextField tbf = (JTextField) fieldTxts.get(intFieldToGetTheValue);
                         strValueFromField= tbf.getText();
                        
                         setVariablesGlobal1(fieldVariableFromPreField,strValueFromField);

                  //System.out.println("PanelODoRData.showRow    ---I---F---  colName:"+colName+"     fieldVariableFromPreField:"+fieldVariableFromPreField+"     field:"+field);//+"    tb2Text:"+tb2Text);
                         
                    }
                    else
                    {
               //         field = rs.getString(colName);
               // System.out.println("PanelODoRData.showRow    ---ELSE----  colName:"+colName+"     fieldVariableFromPreField:"+fieldVariableFromPreField+"     field:"+field);
              	//tbe.setText(field);    
                        
                    }*/
              }
          }        
        
          
    
                   final EntityDBFields[] finalDbFieldsInGroupOfPanels = dbFieldsInGroupOfPanels;
                   final int finalCol = i;
                  final JTextComponent tbFinal=tbe;
                  final String columnDbNameFinal = colName;
                  final String foreignTableFinal = foreignTable;
                  // select all the text if focus gained
                  // tbe is for lookup
                  tbe.addFocusListener(new FocusListener()
                   {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                            
                            //System.out.println("PanelODoRData.showRow  finalCol:"+finalCol);
                           if(Boolean.parseBoolean(fieldTxtsKeyChanged.get(finalCol).toString()))//keyChanged)
                           {
                              //calculateRecordAfterKeySet(finalCol);
                      //        System.out.println("  showRow finalCol:"+finalCol+"      type"+dbFieldsInGroupOfPanels[finalCol].getLookupType());
                             //System.out.println("PanelODORData.showRow   ====================================  "+finalCol+"  formGlobalTableToGet1:"+formGlobalTableToGet1);
                              //System.out.println("PanelODORData.dbFieldsCalculateSet d  hasDataChanged:"+hasDataChanged); 
                             dbFieldsCalculateSet(finalDbFieldsInGroupOfPanels,finalCol,foreignTableFinal);
                              	//keyChanged=false;
                               
                           }                              
                            checkIfFieldsHaveUniqueValueWhileInDataEntry(columnDbNameFinal); 
                            checkFieldsIfThenElse(finalCol,CHECK_ON_ENTRY);//columnDbNameFinal
                            
                        }
                        public void focusGained(FocusEvent e)
                        {    //System.out.println("panelODORData.showRow focus gained");
                             tbFinal.setSelectionStart(0);
                             tbFinal.setSelectionEnd(tbFinal.getText().length());
                             hasDataFieldUniqueChanged(finalCol);  // dataHasChangedInAField  is set to true from this.hasDataFieldUniqueChanged() which is called when txt gets focus
                        }                    	
                    }); 
              
            

             
              // show field look up in tb2
              //ResultSet rsForeign;
              String foreignQuery="";
              String lookupText="";
              String lookupText3="";
              String lookupText4="";
              //System.out.println("panelODORData.showRow ");
              
              String k = ""; // primkey like farmerid in twodataonerec

                                k = rs.getString(colName);

              
              if (k!=null && !k.equals("0") && !k.equals(""))
              {

                
        
                  lookupText = utilsPanelReport.getLookupValue(entity,foreignTable,rs.getString(colName),1,false,fieldVariableFromPreField,/*formGlobalTableToGet1,formGlobalTableToApply1,*/"",entity);
         //    System.out.println("PanelODORData.showRow   =+++++++++++   +   +++++++++  i:"+i+"        colName:"+colName+"      foreignTable:"+foreignTable+"      fieldVariableFromPreField:"+ fieldVariableFromPreField +"  rs.getString(colName):"+rs.getString(colName) +"  lookupText:" +lookupText);               
                if(lookUp.getLookUpField2(foreignTable)!=null)
                {
                   //System.out.println("panelODORData.showRow "+lookUp.getLookUpField2Index(foreignTable));
                  // lookupText3 =rsForeign.getString(lookUp.getLookUpField2Index(foreignTable));// get field data	
                   //System.out.println("panelODORData.showRow "+lookupText3);
                   lookupText3 = utilsPanelReport.getLookupValue(entity,foreignTable,rs.getString(colName),2,false,fieldVariableFromPreField,/*formGlobalTableToGet1,formGlobalTableToApply1,*/"",entity);
                }
 
                if(lookUp.getLookUpField3(foreignTable)!=null)
                {
                   //System.out.println("panelODORData.showRow "+lookUp.getLookUpField2Index(foreignTable));
                  // lookupText4 =rsForeign.getString(lookUp.getLookUpField3Index(foreignTable));// get field data	
                   //System.out.println("panelODORData.showRow "+lookupText3);
                   lookupText4 = utilsPanelReport.getLookupValue(entity,foreignTable,rs.getString(colName),3,false,fieldVariableFromPreField,/*formGlobalTableToGet1,formGlobalTableToApply1,*/"",entity);
                }               
                //System.out.println("panelODORData.showRow if 2 "+(i)+" columnLabel "+columnLabel+" text "+lookupText);            

                
                
                
                
                
                
                
                //System.out.println("panelODORData.showRow "+foreignQuery);
              JTextComponent tb2 = (JTextComponent) fieldTxts2.get(i);//(i); 
              tb2.setText(lookupText.trim());
              
              
              // select all the text if focus gained
               final JTextComponent tb2Final=tb2;    
                   tb2.addFocusListener(new FocusListener()
                   {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                             
                        }
                        public void focusGained(FocusEvent e)
                        {    //System.out.println("panelODORData.showRow tb2 focus gained");
                             tb2Final.setSelectionStart(0);
                             tb2Final.setSelectionEnd(tb2Final.getText().length());
                        }                    	
                    });
              
              JTextComponent tb3 = (JTextComponent) fieldTxts3.get(i); 
              if(lookupText3 != null)
              {              
                tb3.setText(lookupText3);              
              }
              
              JTextComponent tb4 = (JTextComponent) fieldTxts4.get(i);
              if(lookupText4 != null)
              {
                 tb4.setText(lookupText4);    
              }
              
                  
              
              
              

                  

          }
              
              

              
              
          }
          else if(classtype.equalsIgnoreCase("table"))
          {   
             
              
              PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(i);//,PanelOneDataManyRecData);
              int lenClildFields = pnlODMRData.getDbFieldsChild().length;
              EntityDBFields[] edbfChilds = pnlODMRData.getDbFieldsChild();
              //System.out.println("PanelODoRData.showRow     TABLE     colName:"+colName+"   lenClildFields:"+lenClildFields);  

              
              
              

                String sqlWhere = "";
                String sqlWhereFromEntityData = dbFieldsInGroupOfPanels[i].getSqlTableChildRead();// perhaps has 'WHERE' already, so add 'AND'
              
              String primKeyNameTable="";  
              String primKeyValueTable ="";
              int primKeysCount=0;
                  for(int f = 0;f<dbFieldsAll.length;f++)
                  {
                      //System.out.println("PanelODORData.showRow ("+i+"."+f+")  primKeyName:"+dbFieldsAll[f].getDbField()+"  isPrimaryKeyIntegerAutoInc:"+dbFieldsAll[f].getPrimaryKeyIntegerAutoInc());
                      if (dbFieldsAll[f].getPrimaryKeyIntegerAutoInc() == FIELD_PRIMARY_KEY_AUTOINC)//rsmd.isAutoIncrement(i))
                      {
                          primKeysCount++;
                          primKeyNameTable =   dbFieldsAll[f].getDbField();  
                          

                          primKeyValueTable =  rs.getString(dbFieldsAll[f].getDbField());//tb.getText();

                                  if(utilsString.hasQueryWhere(sqlWhereFromEntityData))
                                  {
                                         sqlWhere=sqlWhere+" AND ";
                                  }
                                  else if(!utilsString.hasQueryWhere(sqlWhereFromEntityData) && primKeysCount>=1)
                                  {
                                         sqlWhere=sqlWhere+" WHERE ";
                                  }

                             sqlWhere=sqlWhere+primKeyNameTable+" LIKE "+primKeyValueTable;
                             
                             
                      }
                  }

              //          PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(i);
                   
		    	String queryWithoutOrderby = utilsString.getQueryWithoutOrderby(sqlWhereFromEntityData);
		    	String queryOrderby = utilsString.getOrderbySubQuery(sqlWhereFromEntityData);                        
                                   

                         //sqlWhere = sqlWhere.replaceAll("AND sxesoexoline.isTemplate LIKE 0","AND sxesoexoline.isTemplate LIKE 1");
                        String sql = queryWithoutOrderby+" "+sqlWhere+" "+queryOrderby;
                        //System.out.println("PanelODORData.showRow -------table1       isNewRec:"+isNewRec+"      isNewRecFromCopy:"+isNewRecFromCopy+"       sql:"+sql);
            if(entityTemplate!=null)// && !isNewRecFromCopy)  // when is template                
            {
               String subqueryIsNotTemplate = entityTemplate.getSubQueryIsNotTemplate();// = "AND sxesoexoline.isTemplate ='0'";
               String subqueryIsTemplate = entityTemplate.getSubQueryIsTemplate();//ToReplace = "AND sxesoexoline.isTemplate LIKE '1'";                        
                       if(isNewRec && isNewRecFromCopy)
                       { // when is new rec or copy or select template
                           sql = sql.replaceAll(subqueryIsTemplate,subqueryIsNotTemplate);
                       }
                       else if(isNewRec && !isNewRecFromCopy)
                       {
                           sql = sql.replaceAll(subqueryIsNotTemplate,subqueryIsTemplate);
                           System.out.println("  PanelODORData.showRow ===   isNewRec"+isNewRec+"    isNewRecFromCopy:"+isNewRecFromCopy+"    sql:"+sql);
                       }
                       else
                       { // when is edit
                           
                       }
                       
                    // pnlODMRData.filterForWritableTable(sql,false,false, false);//  when is template, copied from below    
            }                 
                        
                       // System.out.println("PanelODORData.showRow -------table2   sql:"+sql);
                if(VariablesGlobal.globalShowSelectRecord)
                {
                        System.out.println("++++PanelODORData.showRow ===  === === ("+i+")   primKeyValue:"+primKeyValue+"   isNewRec:"+isNewRec+"   isNewRecFromCopy:"+isNewRecFromCopy+"    qIsTemplateToBeReplaced:"+qIsTemplateToBeReplaced+"     sqlWhere:"+sqlWhere+"        sql:"+sql);           
                }
                

                      pnlODMRData.filterForWritableTable(sql,isNewRec,isNewRecFromCopy, false); // last parameter is true when we try to insert a completely new record with one and many, is false when we edit an already saved record
     
          }
          else if(dbFieldsInGroupOfPanels[i].getColClassName().equalsIgnoreCase("htmlfile"))
          {
              //listHtmlFieldsAndValues
             PanelHtmlBrowser panelHtmlBrowser = (PanelHtmlBrowser) fieldTxts.get(i);
                           //                PanelHtmlBrowser panelHtmlBrowser = new PanelHtmlBrowser();
                        
                 panelHtmlBrowser.setVisible(true);
              
     //           String field = "";
    //            field = rsin.getString(colName);
               
         showHtmlFormElements(i,rs);
         panelHtmlBrowser.setListOfFieldsAndValues(listHtmlFieldsAndValues);   
          panelHtmlBrowser.loadURL(periodiki);

    //          System.out.println("PanelODORData.showRow  "+listHtmlFieldsAndValues.size());
          }          
          else if (dbFieldsInGroupOfPanels[i].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)
          {  // showRow
               String field="";
             if(rs!=null && rs.getString(colName)!=null && !rs.getString(colName).equalsIgnoreCase("") )
             {
                field = rs.getString(colName);
                //System.out.println("panelODORData.showRow "+i+" "+rsmd.getColumnName(i)+" "+field);
             }
             else
             {
             	field="";
             }                
              	   JComboBox cb = (JComboBox) fieldTxts.get(i);
                   
                    LookupTableConstantsMgt lookUpTableConstants = new LookupTableConstantsMgt();
                    EntityLookupTableConstantsData[] elutcData = lookUpTableConstants.getEntityLookupTableConstantsData(dbFieldsInGroupOfPanels[i].getLookupEntityName());
                    //dbFieldsInGroupOfPanels[i].getLookupEntityName();
                   
                    if(elutcData == null)
                    {
                        
                    }
                    else
                    {
                     for(int l = 0 ;l<elutcData.length;l++)
                     {
                       if(field.equalsIgnoreCase(elutcData[l].getPk()))
                       {
       //                    System.out.println("+PanelODORData.showRow  setSelectedItem     ("+i+")  "+elutcData[l].getTitle());
                            cb.setSelectedItem(elutcData[l].getName());
                       }                            
                     }
                    }
               //System.out.println("panelODORData.showRow  LOOKUPTYPE_TABLECONSTANTS   i:"+i+"   "+cb.getSelectedItem());
        
              
          }
          else  // for not lookup, perhaps boolean
          {

              String field="";
              Blob blobImage = null;
          
            //System.out.println("  oooooooooooooooooo oooooooooooooooooooooooooo   PanelODORData.showRow ("+i+")   colName:"+colName+"    primKeyValue:"+primKeyValue+"  "); 
          // it must be here, else the rs is closed
       //try
        //{
         // db.retrieveDBDataFromQuery(query,"PanelOneDataOnRecData.showRow B ");
         // rs=db.getRS()
          rs.first();  
           
          //System.out.println("  o   PanelODORData.showRow ("+i+")   colName:"+colName+"    primKeyValue:"+primKeyValue); 
          if(rs!=null  && rs.getString(colName)!=null  && !rs.getString(colName).equalsIgnoreCase(""))
             {
                 if(classtype.equalsIgnoreCase("java.awt.image.BufferedImage"))
                 {
                     
                     
                     
                    blobImage =  rs.getBlob(colName);
              /*      InputStream binaryStream= null; 
                     
            if (bFile != null ) 
            {*/
               //  binaryStream = bFile.getBinaryStream(/*0 i*/i, bFile.length());
           // }                     
                     

        
                     
                 }
                 else
                 {
                     field = rs.getString(colName);
                 }
                //System.out.println("panelODORData.showRow "+i+" "+rsmd.getColumnName(i)+" "+field);
             }            
             else
             {
             	field="";
             } 
        /*}
         catch ( SQLException sqlex)
         {
            System.out.println("error: sql PanelOneDataOneRecData.showRow() B  msg:"+sqlex.getMessage());
            if(VariablesGlobal.globalShowPrintStackTrace)  
            {
               sqlex.printStackTrace();     
            }
         }*/
   //     closeDB();
             //System.out.println("panelODORData.showRow ("+i+") colName:"+colName+"="+field+" classtype:"+classtype+"   defaultValue:"+defaultValue[i]);
             
             //System.out.println("panelODORData.showRow "+i+" "+rsmd.getColumnName(i)+" "+field);
              
             if(!field.equalsIgnoreCase(""))
             { 
              if(classtype.equalsIgnoreCase("java.sql.Date") || classtype.equalsIgnoreCase("java.lang.Date"))
              {
                 field=utilsDate.reformatDateStringToEditFromDB(field);	
                 //System.out.println("PanelODORD.showRow "+field);
              }
              else if (classtype.equalsIgnoreCase("java.lang.String") || (classtype.equalsIgnoreCase("java.lang.Integer" )))
              {   
             	
              }
              else if(classtype.equalsIgnoreCase("java.lang.Double") || classtype.equalsIgnoreCase("java.math.BigDecimal")  || classtype.equalsIgnoreCase("java.lang.Long"))
              {   
             	field = utilsDouble.getDoubleReading(field,false);
              }    
              else if (classtype.equalsIgnoreCase("java.lang.Boolean"))
              {
          	  	

          	  	//System.out.println("err panelODORData.showRow classtype "+classtype+" "+i+" not implemented");
              }   
              else if(classtype.equalsIgnoreCase("java.awt.image.BufferedImage"))
              {
                             
              }              
              else
              {   
              	
             	System.out.println("error panelODORData.showRow A field:"+field+" classtype "+classtype+" "+i+" not implemented");
              }                   
             }
             else
             {
              if(classtype.equalsIgnoreCase("java.sql.Date") || classtype.equalsIgnoreCase("java.lang.Date"))
              {
                 field="";	
              }
              else if(classtype.equalsIgnoreCase("java.lang.String") || (classtype.equalsIgnoreCase("java.lang.Integer" )))
              {   
             	field="";	
              }
              else if (classtype.equalsIgnoreCase("java.lang.Boolean"))
              {
          	  	field="";
              } 
              else if(classtype.equalsIgnoreCase("java.awt.image.BufferedImage"))
              {
                         
              }              
              else
              {
             	System.out.println("error panelODORData.showRow B field:"+field+"  classtype "+classtype+" "+i+" not implemented");
              }
                  /* if (classtype.equalsIgnoreCase("java.sql.Double") || classtype.equalsIgnoreCase("java.lang.Double") )
                  {
             	      
                  } */
             }

              if(classtype.equalsIgnoreCase("java.sql.Date") || classtype.equalsIgnoreCase("java.lang.Date"))
              {

           	  JTextBoxWithEditButtons textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(i);

              	   //System.out.println("setEntity "+field);
              	   if(!utilsDate.isDateNull(field))
              	   {
              	   	  textEditFormatedDate.setText(field);
              	   	  textEditFormatedDate.findDateNameInText();
              	   	  
  	                  //System.out.println("PanelODORD.showRow "+field);

              	   }
              	 // for textEditFormatedDate to be all selected when focus gained implemented inside JTextBoxWithEditButtons
                  // select all the text if focus gained
                  
                  JTextComponent ta = (JTextComponent)textEditFormatedDate.getTextComp();
                
                  final JTextComponent taFinal=ta;
                  taFinal.addFocusListener(new FocusListener()
                   {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                        }
                        public void focusGained(FocusEvent e)
                        {    //System.out.println("panelODORData.showRow focus gained");
                             taFinal.setSelectionStart(0);
                             taFinal.setSelectionEnd(taFinal.getText().length());
                        }                    	
                    });              	   
              }
              else if (classtype.equalsIgnoreCase("java.lang.Boolean"))
              {
          	  	   //System.out.println("PanelOneDataOneRecData.showRow boolean "+field);
              	   JCheckBox chk = (JCheckBox) fieldTxts.get(i);//i-1);

              	   chk.setOpaque(false);
              	   boolean sel =false;
                   if(field.equals(""))
              	   {
              	   	sel=false;
              	   }
                   else if(field.equals("0"))
              	   {
              	   	sel=false;
              	   }
              	   else if(field.equals("1"))
              	   {
              	   	 sel=true;
              	   }
              	   else if(field.equals("TRUE"))
              	   {
              	   	  sel=true;
              	   }
              	   else if(field.equals("FALSE"))
              	   {
              	   	  sel=false;
              	   }
              	   else
              	   {
                        
              	   	  System.out.println("PanelOneDataOneRecData.showRow  boolean unknown value "+field);
              	   }
              	   
              	   chk.setSelected(sel);          	  	
              }   
              else if(classtype.equalsIgnoreCase("java.awt.image.BufferedImage"))
              {
                            JLabel lblImage = (JLabel) fieldTxts.get(i);
                            //lblImage.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
  	              if(blobImage!=null)      
                      {
                try
                {
                    byte[] imageByte = blobImage.getBytes(1, (int) blobImage.length());
                  //  JOptionPane.showMessageDialog(null, "connected");
                    InputStream is=new ByteArrayInputStream(imageByte);
                    BufferedImage imag=ImageIO.read(is);
                    Image image = imag;                            
 	                    // img = Toolkit.getDefaultToolkit().createImage(imageByte);
  	                    //  img = img.getScaledInstance(200,200,Image.SCALE_SMOOTH);
  	                	ImageIcon icon =new ImageIcon(image);                            
                            
                            
                           /* byte[] imageByte = blobImage.getBytes(1, (int) blobImage.length());
  	                     InputStream is=new ByteArrayInputStream(imageByte);
  	                    BufferedImage imag=ImageIO.read(is);
  	                    Image image = imag;*/
  	                    // img = Toolkit.getDefaultToolkit().createImage(imageByte);
  	                    //  img = img.getScaledInstance(200,200,Image.SCALE_SMOOTH);
  	                	
  	            lblImage.setIcon(icon) ;              
             
                    //lblImage.setBounds(200,20,300,400);
                }
                catch(Exception ex)
                {
                   // The driver could not handle this as a BLOB...
                   // Fallback to default (and slower) byte[] handling
                   //byte[] bytes = blobImage.getBytes(1);
                   //JOptionPane.showMessageDialog(null, "Error "+ex.getMessage());
                   ex.printStackTrace();
                }                 
                      }
                      else
                      {
                           lblImage.setText(imageEmpty);
                           //lblImage.setBounds(200,20,1300,800); 
                            lblImage.setIcon(null) ;  
                      }
                  
                 // JLabel lblImage = new JLabel("image");
              }
              else if(columnWidth>40) // text area
              {
                  

              	  
              	  
                    if(columnWidth<COLWIDTH_FOR_HTML) // text area
                    {
                         JTextComponent ta = (JTextComponent) fieldTxts.get(i);
                  // select all the text if focus gained
                  final JTextComponent taFinal=ta;
                   ta.setText(field);
                  taFinal.addFocusListener(new FocusListener()
                   {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                            
                        }
                        public void focusGained(FocusEvent e)
                        {    //System.out.println("panelODORData.showRow focus gained");
                             taFinal.setSelectionStart(0);
                             taFinal.setSelectionEnd(taFinal.getText().length());
                        }                    	
                    });    
                    
                    }
                    else
                    {
                           PanelHtmlEditor htmlEditor = (PanelHtmlEditor) fieldTxts.get(i);
                  // select all the text if focus gained
                  htmlEditor.setText(field);
                       final String fieldFinal=field;
                  final PanelHtmlEditor taFinal=htmlEditor;
                  taFinal.addFocusListener(new FocusListener()
                   {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                            //fieldFinal = taFinal.getText();
                        }
                        public void focusGained(FocusEvent e)
                        {    //System.out.println("panelODORData.showRow focus gained");
                             //taFinal.setText(fieldFinal);
                            //JTextComponent txtFieldArea = (JTextComponent)fieldTxts.get(i);
                            taFinal.setSelectionStart(0);
                            taFinal.setSelectionEnd(taFinal.getText().length());
                        }                    	
                    });                         
                    }
                   
              
                  
              }
              else
              {
             	   JTextComponent tbe = (JTextComponent) fieldTxts.get(i);
              	   tbe.setText(field);
              	   //System.out.println(" showrow "+i+" "+field);

                  // select all the text if focus gained
                   final EntityDBFields[] finalDbFieldsInGroupOfPanels = dbFieldsInGroupOfPanels;
                   final int finalCol = i;
                  final JTextComponent tbFinal=tbe;
                  final String columnDbNameFinal=colName;
                  final String columnCaptionFinal  = colCaption;
                 
                  //  tbe is for plain text field
                  tbe.addFocusListener(new FocusListener()
                   {
                    	public void focusLost(FocusEvent e)
                        {  // if entered nothing or spaces on textbox
                            
                          //System.out.println("panelODORData.showRow     keyChanged:"+fieldTxtsKeyChanged.get(finalCol).toString()+"    finalCol:"+finalCol+"    columnDbNameFinal:"+columnDbNameFinal);
                           if(Boolean.parseBoolean(fieldTxtsKeyChanged.get(finalCol).toString()))//keyChanged)
                           {
                              //calculateRecordAfterKeySet(finalCol);
                    //          System.out.println(" showrow  i:"+finalCol+"        type"+dbFieldsInGroupOfPanels[finalCol].getLookupType());
                             //System.out.println("PanelODORData.showRow   ====================================  "+finalCol+"  formGlobalTableToGet1:"+formGlobalTableToGet1);
                              //System.out.println("PanelODORData.dbFieldsCalculateSet e  hasDataChanged:"+hasDataChanged); 
                             dbFieldsCalculateSet(finalDbFieldsInGroupOfPanels,finalCol,"");
                              	//keyChanged=false;
                              
                           }                            
                            //System.out.println("PanelODORData.dbFieldsCalculateSet e  hasDataChanged:"+hasDataChanged+ "  columnDbNameFinal:"+columnDbNameFinal); 
                            checkIfFieldsHaveUniqueValueWhileInDataEntry(columnDbNameFinal); 
                            checkFieldsIfThenElse(finalCol,CHECK_ON_ENTRY);//columnDbNameFinal
                            
                        }
                        public void focusGained(FocusEvent e)
                        {    //System.out.println("panelODORData.showRow focus gained");
                             tbFinal.setSelectionStart(0);
                             tbFinal.setSelectionEnd(tbFinal.getText().length());
                             hasDataFieldUniqueChanged(finalCol);   // dataHasChangedInAField  is set to true from this.hasDataFieldUniqueChanged() which is called when txt gets focus
                        }                    	
                    }); 
                    	
                if(fieldValidation[i]==FIELD_VALIDATION_AFM)
         	    {     	
         		   // JLabel lb =(JLabel)listLabelValid.get(i);
                            JButton btn =(JButton)listButtonValid.get(i);
                            
         		
         		    if(utilsString.checkGreekAFM(tbe.getText()))	
         		    {
         			  //lb.setText("correct");
         			  btn.setIcon(ICO_OK16);
                                  btn.setEnabled(true);
         		    }
         		    else
         	    	{
         			   //lb.setText("faulty");
         			   btn.setIcon(ICO_CANCEL16);
                                    btn.setEnabled(false);
         	    	}                    	
         	    }    	             	   
              }
                                   
            if (isPrimaryKeyIntegerAutoInc == FIELD_PRIMARY_KEY_AUTOINC)
            {
            	JTextComponent tb = (JTextComponent) fieldTxts.get(i);
            	isFirstFieldAutoInc=true;     
            }
            
          }//else  // for not lookup
          
        
          
         }// for columnCount
 
       closeDB(); 
           
       setVisibleOrEditableFields(true);
       //System.out.println("++++PanelODORData.showRow ===  === === B");
        
    }    
   
    public EntityDBFields[] getDbFieldsInGroupOfPanels()
    {
        return dbFieldsInGroupOfPanels;
    }
    
    
    private String calculateVarFromPreFieldAndSetGlobal(int col)
    {
        String strValueFromField = "";
        
       if(col!=-1)
       {
        int intFieldToGetTheValue = utilsPanelReport.calculateAllFieldsFromParentDBFieldsForFormVariable1(dbFieldsAll);  
        
        System.out.println("ooooooo>>  panelODORData.calculateVarFromPreFieldAndSetGlobal       col:"+col+"     ?    intFieldToGetTheValue:"+intFieldToGetTheValue);
        
         if(col==intFieldToGetTheValue)  
         {
                String fieldVariableFromPreField = dbFieldsInGroupOfPanels[col].getFormVariableFromField();
                String colName = dbFieldsInGroupOfPanels[col].getDbField();
                
          System.out.println("ooooooo>>  panelODORData.calculateVarFromPreFieldAndSetGlobal  colName:"+colName+"  fieldVariableFromPreField:'"+fieldVariableFromPreField+"'");
                /*if(fieldVariableFromPreField!=null && !fieldVariableFromPreField.equalsIgnoreCase("") )
                {
                            JTextField tbf = (JTextField) fieldTxts.get(intFieldToGetTheValue);
                            strValueFromField= tbf.getText();
                            setVariablesGlobal1(fieldVariableFromPreField,strValueFromField);
                }  
                else
                {
                      System.out.println("ooooooo>>  panelODORData.calculateVarFromPreFieldAndSetGlobal     not set   colName:"+colName+"  fieldVariableFromPreField:'"+fieldVariableFromPreField+"'");      
                }*/
                
                            JTextField tbf = (JTextField) fieldTxts.get(intFieldToGetTheValue);
                            strValueFromField= tbf.getText();
                            setVariablesGlobal1(strValueFromField);
                
         }
      //System.out.println("ooooooo>>  panelODORData.calculateVarFromPreFieldAndSetGlobal       col:"+col+"   colName:"+colName+"  fieldVariableFromPreField:"+fieldVariableFromPreField+" =  strValueFromField:"+strValueFromField+"       strValueFromField:"+strValueFromField);       
       }
       else
       {
           
       }
   
        return strValueFromField;
    }
    


   public void setVariablesGlobal1Erase()
   {
       
                         //if(formGlobalTableToGet1.equalsIgnoreCase(foreignTableIn))
                 // {
                      
              //formGlobalVariable1 = null;
                   //formGlobalVariable1 = value; 
                   
                   VariablesGlobal.globalformGlobalVariable1 = "";
    //System.out.println("PanelODORData.setVariablesGlobal1Erase    =+++++OOOO++++++===  VariablesGlobal.globalformGlobalVariable1:'"+VariablesGlobal.globalformGlobalVariable1+"'");
   
   }
   
   private void setVariablesGlobal1( String value)
   {
     
   
             /*if(fieldVariableFromPreField!=null )//|| value!=null || !value.equalsIgnoreCase(""))
             { */   
                   VariablesGlobal.globalformGlobalVariable1 = value;

            // }
    //System.out.println("PanelODORData.setVariablesGlobal1    =+++++OOOO++++++===   value:"+value +"  VariablesGlobal.globalformGlobalVariable1:'"+VariablesGlobal.globalformGlobalVariable1+"'");

   }
    
    
    
    
    /*
    *   exists differentiated with differences both in PanelODoRData and panelODMRData
    */
    private int calculateAllFieldsForFormVariable1()
    {
        
      
    //    ArrayList listDbFieldsAll = new ArrayList();
      
      int intFieldToGetTheValue = -1;
      
      intFieldToGetTheValue = utilsPanelReport.calculateAllFieldsFromParentDBFieldsForFormVariable1(dbFieldsInGroupOfPanels);
      
     /*
      for(int f= 0 ;f<dbFieldsInGroupOfPanels.length;f++)
      {
         //System.out.println("PanelODoRData.showRow f:"+f+"    "+dbFieldsInGroupOfPanels[f].getCaption()+"   class:"+dbFieldsInGroupOfPanels[f].getColClassName());   
          listDbFieldsAll.add(dbFieldsInGroupOfPanels[f]);
          if(dbFieldsInGroupOfPanels[f].getColClassName().equalsIgnoreCase("table"))
          {
    
              //int lenOfTableFields = dbFieldsInGroupOfPanels[f].getDbChildFields().length;
             EntityDBFields[] childDBFields = dbFieldsInGroupOfPanels[f].getDbChildFields();
             int lenOfTableFields = childDBFields.length;
            for(int t = 0;t<lenOfTableFields;t++)
            {
                 //  System.out.println("PanelODoRData.showRow t:"+t+"  "+childDBFields[t].getDbField());
                  
                   listDbFieldsAll.add(childDBFields[t]);
            }
          }
      }
      
      
      for(int l =0 ;l<listDbFieldsAll.size();l++)
      {
          EntityDBFields cfields = (EntityDBFields)listDbFieldsAll.get(l);
          //System.out.println("PanelODoRData.calculateAllFieldsForFormVariable1        listDbFieldsAll     l:"+l+"    getTableName:"+cfields.getTableName()+" getDbField:"+cfields.getDbField()+"   -   "+cfields.getFormVariableFromField());
      }
      
      
       String fieldVariableFromPreField = "";
     
       ArrayList lstFieldVariable = new ArrayList();
       //int intFieldVar = -1;

          //String fieldNameThatHasFormVariable="";
          for(int g=0;g<listDbFieldsAll.size();g++)
          {
              EntityDBFields listChildDBField = (EntityDBFields)listDbFieldsAll.get(g);
              fieldVariableFromPreField  = listChildDBField.getFormVariableFromField();
              if(fieldVariableFromPreField!=null && !fieldVariableFromPreField.equalsIgnoreCase(""))
              {
                   fieldVariableFromPreField = listChildDBField.getFormVariableFromField();
                   //System.out.println("PanelODoRData.calculateAllFieldsForFormVariable1     equals    fieldVariableFromPreField:"+fieldVariableFromPreField);
                   break;
              }
              else
              {
                  fieldVariableFromPreField="";
              }
          }
       for(int k= 0 ;k<listDbFieldsAll.size();k++)
      {
            EntityDBFields listChildDBField = (EntityDBFields)listDbFieldsAll.get(k);
          //EntityDBFields gfv  = listDbFieldsAll.get(l);
            //fieldVariableFromPreField  = listChildDBField.getFormVariableFromField();
           String fieldName = listChildDBField.getDbField();
            //System.out.println("PanelODoRData.showRow    equals ?      i:"+i+"   k:"+k+"        fieldName"+fieldName);  
            if(fieldName.equalsIgnoreCase(fieldVariableFromPreField)) // fieldVariableFromPreField!=null && !fieldVariableFromPreField.equalsIgnoreCase("") 
            {
                 //   lstFieldVariable.add(fieldName);
                    //intFieldVar=k;
                    intFieldToGetTheValue = k;
                 //   System.out.println("PanelODoRData.showRow  -  equals ?  i:"+i+"   k:"+k+"   intFieldToGetTheValue:"+intFieldToGetTheValue+"    fieldVariableFromPreField:"+fieldVariableFromPreField+"     fieldName"+fieldName+"    lstFieldVariable:"+lstFieldVariable);  
                break;
                 //System.out.println("PanelODoRData.showRow    equals ?      i:"+i+"   k:"+k+"   fieldVariableFromPreField:"+fieldVariableFromPreField+"    lstFieldVariable:"+lstFieldVariable);  
                //fieldVariableFromPreField = fieldVariableFromPreField;
               // break;
            }

      }

      System.out.println("PanelODoRData.showRow    equals    fieldVariableFromPreField:"+fieldVariableFromPreField+"   intFieldToGetTheValue:"+intFieldToGetTheValue); 
*/
        
        return intFieldToGetTheValue;
        
    }
    
    //private void setFormGlobalVariable1ToPanelODMRData()//String formGlobalVariable1In)
   // {
        
      /*for(int i = 0;i<dbFieldsInGroupOfPanels.length;i++)
      //for(int i = 0;i<dbFieldsAll.length;i++)  
      {
          //int fieldCount = i;//i-1; // calculates the no of field starting from 0 when i = 1
          String colName =   dbFieldsInGroupOfPanels[i].getDbField();
          String colTableName =dbFieldsInGroupOfPanels[i].getTableName();
          String colCaption =   dbFieldsInGroupOfPanels[i].getCaption();
          int columnWidth = dbFieldsInGroupOfPanels[i].getColWidth();
      	 //System.out.println("panelODORData.showRow i ("+i+") colName:"+colName+" class:"+dbFieldsInGroupOfPanels[i].getColClassName());
          String classtype = dbFieldsInGroupOfPanels[i].getColClassName();        
        
        
    
                     if(colName!= null && classtype.equalsIgnoreCase("table") )
                    {
                                PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(i);
                                pnlODMRData.setFormGlobalVariable1(formGlobalVariable1In);
                    }
        
       }*/
  //  }
    
  /*
        private static final int IMG_WIDTH = 120;
    private static final int IMG_HEIGHT = 120;
    
    
       http://www.javaknowledge.info/resize-image-and-upload-to-mysql-db-using-java-swing/
    */  
    
    /*private static BufferedImage resizeImage(BufferedImage originalImage, int type)
    {
          int IMG_WIDTH = 120;
          int IMG_HEIGHT = 120;
        
        
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
 
        return resizedImage;
    } */   
    
    
    
    
    
    
   private int rowUpdate(Database dbTransaction,String pkFromOnePanelForTables) throws SQLException
   { 
       int ret=0;
      
      // globalDeliveryId =VariablesGlobal.globalDeliveryId;
       globalCompanyId=VariablesGlobal.globalCompanyId;
       

          String subquerySet = ""; // for each text field

          boolean addSqlComma =true;// comma after field
          
          if (dbFieldsInGroupOfPanels==null)
          {
              System.out.println("PanelOneDataOneRecData.rowUpdate dbFieldsInGroupOfPanels="+dbFieldsInGroupOfPanels);
          }
          int colCount =dbFieldsInGroupOfPanels.length;
          
          for (int i = 0; i < colCount; i++)//  i = fieldTxts
          {
                  
          	  String columnName = fields[i]; //get colunm name  	           	
	          String classtype = dbFieldsInGroupOfPanels[i].getColClassName();  // if integer then not add ' and ' between values
//          	  String whereValueName = getWhereValueNameThatMatchesColumn(columnName,"global");
                  int columnWidth = dbFieldsInGroupOfPanels[i].getColWidth(); 
           //System.out.println("PanelOneDataOneRecData.rowUpdate >>>>>>  ("+i+")     classtype:"+classtype +"     columnName:"+columnName+"                colCount:"+colCount);

         	  //System.out.println("panelOneDataOneRecData.updateRow columnLabel "+columnLabel);
          	JTextComponent tb = new JTextField();
          	String fieldValue = "";
          	JTextBoxWithEditButtons textEditFormatedDate = new JTextBoxWithEditButtons();
                //textEditFormatedDate.setYearEnforce(yearEnforce);
          	 String textFromDateFormat="";
          	 // date and lookup is not tb(JTextField) but panel
             if (classtype.equalsIgnoreCase("java.sql.Date") || classtype.equalsIgnoreCase("java.lang.Date"))
             {
             	
             	   textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(i);
                   textFromDateFormat = textEditFormatedDate.getText();

             }
             else if (classtype.equalsIgnoreCase("java.lang.Boolean"))
             {
              	   JCheckBox chk = (JCheckBox) fieldTxts.get(i);//i-1);
              	   chk.setOpaque(false);
              	   boolean sel =chk.isSelected(); 
              	   if(sel)
              	   {
              	   	fieldValue="1";
              	   }
              	   else 
              	   {
              	   	 fieldValue="0";
              	   }           	  	
             }   
             else if(classtype.equalsIgnoreCase("table"))
             {

                 System.out.println("PanelOneDataOneRecData.rowUpdate A ("+i+")of:"+colCount+" columnName:"+columnName+" classtype:"+classtype);
                 
             } 
             else if(classtype.equalsIgnoreCase("htmlfile"))
             {
               // String field = "";
               // field = rs.getString(colName);
  
         //        PanelHtmlBrowser panelHtmlBrowser = (PanelHtmlBrowser) fieldTxts.get(i);
         //        panelHtmlBrowser.setVisible(true);
                 //panelHtmlBrowser.loadURL("www.businesseye.gr");
        //         panelHtmlBrowser.loadURL(periodiki);
                                       
                 System.out.println("PanelOneDataOneRecData.rowUpdate A 'htmlfile' ("+i+")of:"+colCount+" columnName:"+columnName+" classtype:"+classtype);
             }              
             else if(classtype.equalsIgnoreCase("java.awt.image.BufferedImage"))
             {
                 System.out.println("PanelOneDataOneRecData.rowUpdate   ----->>>>>      classtype:"+classtype);
              	   JLabel lblImage = (JLabel) fieldTxts.get(i);//i-1);
                   //lblImage.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
              	   lblImage.setOpaque(false);                 
  
                   /*                 lblImage
                           
FileInputStream fileInputStream=new FileInputStream(filePath);
byte b[]=new byte[lblImage.getIcon()];
fileInputStream.read(b);
fileInputStream.close();
//ps.setObject(1, jTextField1.getText());
ps.setBytes(i, b);
//int val=ps.executeUpdate();                           
 */
  

//  http://www.javaknowledge.info/resize-image-and-upload-to-mysql-db-using-java-swing/
           DataBufferByte data;
               Image photo;
               //Icon photo;
    WritableRaster raster;
           
 /*          try
            {
    //            BufferedImage originalImage = ImageIO.read(image);
    //            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
 
                BufferedImage resizeImageJpg = resizeImage(originalImage, type);
   //             photo = new ImageIcon(toImage(resizeImageJpg));
                  photo = ((ImageIcon) lblImage.getIcon()).getImage(); 
                //converting buffered image to byte array
                raster = resizeImageJpg.getRaster();
                data = (DataBufferByte) raster.getDataBuffer();
 
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }           
           
           

            byte[] extractBytes = data.getData();
            psmnt.setBytes(3, extractBytes);
                           
 */                
             }
             else if(dbFieldsInGroupOfPanels[i].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)           
             {
                   LookupTableConstantsMgt lookUpTableConstants = new LookupTableConstantsMgt();
                    EntityLookupTableConstantsData[] elutcData = lookUpTableConstants.getEntityLookupTableConstantsData(dbFieldsInGroupOfPanels[i].getLookupEntityName());
                    //dbFieldsInGroupOfPanels[i].getLookupEntityName();
                    JComboBox cmbBox =  (JComboBox) fieldTxts.get(i);
                    fieldValue = elutcData[cmbBox.getSelectedIndex()].getPk();         
                              
                   
             }
             else
             {
                 
                  if( classtype.equalsIgnoreCase("java.lang.String") && columnWidth>COLWIDTH_FOR_BIGTEXTBOX)
                  {

                 	//JPanel panelTextArea =new JPanel(new BorderLayout());
                  	//panelTextArea.setOpaque(false);

                      
                        if(columnWidth>COLWIDTH_FOR_HTML) // like printform html // look also above
                        {                 
                 
                           PanelHtmlEditor htmlEditor = (PanelHtmlEditor)fieldTxts.get(i);//this is for html
                           fieldValue = htmlEditor.getText();
                        }
                        else
                        {
                           tb = (JTextComponent) fieldTxts.get(i);
              
                           fieldValue = tb.getText();                            
                        }
                  }
                  else
                  {
                 
                 tb = (JTextComponent) fieldTxts.get(i);
              
                 fieldValue = tb.getText();
                  }
               //System.out.println("PanelOneDataOneRecData.rowUpdate "+fieldValue);
             	
             }
          	 
             
  
      boolean isCountCommas = false;
          	if (classtype.equalsIgnoreCase("java.lang.String"))
          	{    
          	  	if(!fieldValue.equalsIgnoreCase(""))
          	  	{	     
          /*	       if(!whereValueName.equalsIgnoreCase("-"))
          	       {
          	       	   //System.out.println("panelOneDataOneRecData.updateRow "+columnLabel+" "+getValueForVariable(whereValueName));
          	             subquerySet = subquerySet+ columnName+" = '"+getValueForVariable(whereValueName)+"'";
          	       }
          	       else
          	       {   */
          	             subquerySet = subquerySet+ columnName+" = '"+fieldValue.trim()+"'";// this ha quotation marks
          	       //}
          	       isCountCommas = true;
          	  	}
          	  	else
          	  	{
                 	    subquerySet = subquerySet+ columnName+" = null";
                 	    isCountCommas = true;
          	  		
          	  	}
          	       
          	}
          	else if (classtype.equalsIgnoreCase("java.lang.Integer"))
          	{
          	  	 if(!fieldValue.equalsIgnoreCase(""))
                     {
  /*        	       if(!whereValueName.equalsIgnoreCase("-"))
          	       {
          	       	  //System.out.println("panelOneDataOneRecData.updateRow "+columnLabel+" "+getValueForVariable(whereValueName));
          	         subquerySet = subquerySet+ columnName+" = '"+getValueForVariable(whereValueName)+"'";
          	       }
          	       else
          	       {*/
                	   subquerySet = subquerySet+ columnName+" = '"+fieldValue.trim()+"'";
               //        }
                          isCountCommas = true;
                     }
                     else
                     {
   /*       	       if(!whereValueName.equalsIgnoreCase("-"))
          	       {
          	       	  //System.out.println("panelOneDataOneRecData.updateRow "+columnLabel+" "+getValueForVariable(whereValueName));
          	         subquerySet = subquerySet+ columnName+" = '"+getValueForVariable(whereValueName)+"'";
          	         isCountCommas = true;
          	       }
          	       else
          	       {     */            	// is lookup -> no 0 when is not completed
                 	//subquerySet = subquerySet+ columnLabel+" = 0";
                 	subquerySet = subquerySet+ columnName+" = null";
                 	isCountCommas = true;
                 	
          	  //     }
                      }  
          	 }
          	 else if (classtype.equalsIgnoreCase("java.lang.Double") || (classtype.equalsIgnoreCase("java.math.BigDecimal")))
          	 {
                    if(!fieldValue.equalsIgnoreCase(""))
                    {

  /*        	       if(!whereValueName.equalsIgnoreCase("-"))
          	       {
          	       	  //System.out.println("panelOneDataOneRecData.updateRow "+columnLabel+" "+getValueForVariable(whereValueName));
          	         subquerySet = subquerySet+ columnName+" = '"+getValueForVariable(whereValueName)+"'";
          	       }
          	       else
          	       {*/
                           //System.out.println("+++++   PanelODORData.rowUpdate   columnName:"+columnName+"   "+fieldValue);
                	   subquerySet = subquerySet+ columnName+" = '"+utilsDouble.getDoubleSaving(fieldValue.trim())+"'";
                   //    }
                        isCountCommas = true;
                    }
                    else
                    {
                 	isCountCommas = true;
                 	subquerySet = subquerySet+ columnName+" = '0.0'";
                    }  
                 
          	 }         	  
          	 else if (classtype.equalsIgnoreCase("java.sql.Date") || classtype.equalsIgnoreCase("java.lang.Date"))
          	 {

                    if(!fieldValue.equalsIgnoreCase(""))
                    {
   /*       	       if(!whereValueName.equalsIgnoreCase("-"))
          	       {
          	       	  //System.out.println("panelOneDataOneRecData.updateRow "+columnLabel+" "+getValueForVariable(whereValueName));
          	         subquerySet = subquerySet+ columnName+" = "+getValueForVariable(whereValueName)+"";
          	       }
          	       else
          	       {*/
          	       	
                	   subquerySet = subquerySet+ columnName+" = '"+utilsDate.reformatDateStringToSaveToDB(textEditFormatedDate.getText().trim())+"'";
                 //      }
                     isCountCommas = true;
                    }
                    else if(!textFromDateFormat.equalsIgnoreCase(""))
                    {
                 	subquerySet = subquerySet+ columnName+" = '"+utilsDate.reformatDateStringToSaveToDB(textEditFormatedDate.getText().trim())+"'";
                 	isCountCommas = true;
                    }
                    else
                    {
                 	subquerySet = subquerySet+ columnName+" = '"+utilsDate.getNullDate()+"'";
                 	isCountCommas = true;
                    }
                 
          	  }   
          	  else if (classtype.equalsIgnoreCase("java.lang.Boolean"))
          	  {
          	    if(!fieldValue.equalsIgnoreCase(""))
                    {
   /*       	       if(!whereValueName.equalsIgnoreCase("-"))
          	       {
          	       	  //System.out.println("panelOneDataOneRecData.updateRow "+columnLabel+" "+getValueForVariable(whereValueName));
          	         subquerySet = subquerySet+ columnName+" = '"+getValueForVariable(whereValueName)+"'";
          	       }
          	       else
          	       {*/
                	   subquerySet = subquerySet+ columnName+" = '"+fieldValue.trim()+"'";
              //         }
                   isCountCommas = true;
                    }
                    else
                    {
                 	subquerySet = subquerySet+ columnName+" = null";
                 	isCountCommas = true;
                    }  
          	 }
                 else if(classtype.equalsIgnoreCase("table"))
                 {
                     System.out.println("PanelOneDataOneRecData.rowUpdate B ("+i+")of:"+colCount+" columnName:"+columnName+" classtype:"+classtype);
                 } 
                 else if(classtype.equalsIgnoreCase("htmlfile"))
                 {
                     System.out.println("PanelOneDataOneRecData.rowUpdate B 'htmlfile'  ("+i+")of:"+colCount+" columnName:"+columnName+" classtype:"+classtype);
                 }                  
                 else if(classtype.equalsIgnoreCase("java.awt.image.BufferedImage"))
                 {
//                             subquerySet = subquerySet+ columnName+" = '"+fieldValue.trim()+"'";
                       System.out.println("error panelOneDataOneRecData.rowUpdate B classtype "+classtype);
                 }
          	 else
          	 { 
          	      
          	  	   System.out.println("error panelOneDataOneRecData.rowUpdate B classtype "+classtype+" not supported");
          	 }
         //System.out.println("panelOneDataOneRecData.updateRow ("+i+") "+intGroupOfPanelsToShow+".   "+title+"    "+columnName+":"+fieldValue);
              //System.out.println("addSqlComma "+addSqlComma+" "+subquerySet);

          	    if (isCountCommas ) 
          	    {
                        // add comma but not on the last field(before where), also not when there is only one field
          	       //System.out.println("minus "+i+" "+" "+rsmd.getColumnCount()+" "+colCount);
          	       	 subquerySet = subquerySet+",  ";  
          	       
                    }
           
           //System.out.println("PanelOneDataOneRecData.rowUpdate   =-=-=-=  subquerySet  ("+i+")  columnName:"+columnName+"   primKeyDb:"+primKeyDb+"       classtype:"+classtype+"       subquerySet:"+subquerySet);

                    
                 if(columnName.equalsIgnoreCase(primKeyDb))
                 {                  
                     primKeyValue=fieldValue.trim();
     
                 }                    
                    
          	  
          }// for
          // add comma but not on the last field(before where), also not when there is only one field
          //System.out.println("PanelODORData.rowUpdate   O o O    subquerySet:"+subquerySet);
          if(subquerySet.trim().equalsIgnoreCase(""))
          {
              
          }
          else
          {
                subquerySet = subquerySet.substring(0,subquerySet.length()-3);
          }
          
          
          String subqueryWhere = ""; // for each primary key
          
             utilsPanelReport.retrievePrimKeyValueForOnePK( query, selectedRow, null,dbFieldsAll,true,/*primKeyIn,intColumnOfDescriptionIn,
             sql2WhereField, sql2WhereValue,*/ entity, /*tableModelReadOnly,*/ primKeyDb);    
                       
             String[] primKeys = utilsPanelReport.getPrimKeys();
             //String[] primKeysCaption = utilsPanelReport.getPrimKeysCaption();
            //System.out.println("PanelOneDataOneRecData.rowUpdate '"+entity+"' selectedRow:"+selectedRow+"  primKeys:"+primKeys.length); 
             int primKeysCount = primKeys.length;
             String[] primKeysValue = utilsPanelReport.getPrimKeysValue();              
              

      //    databaseTableMeta.retrievePrimKs(entity); // first retrieve them
          for (int i = 0; i< primKeysCount; i++) // i=0 and i< because arraylist starts from 0
          {             
                //System.out.println("PanelOneDataOneRecData.rowUpdate '"+entity+"' "+primKeys[i]+"="+primKeysValue[i]); 

              
               System.out.println("PanelOneDataOneRecData.rowUpdate ------------ subqueryWhere  ("+i+")  "+primKey+"   "+primKeys[i]+"="+primKeysValue[i]+"     primKeyDb:"+primKeyDb+"  primKeyValue:"+primKeyValue+"   pkFromOnePanelForTables:"+pkFromOnePanelForTables);   
               if(primKeys[i].equalsIgnoreCase(primKeyDb))
               {
                   if(pkFromOnePanelForTables == null || pkFromOnePanelForTables.equalsIgnoreCase(""))
                   {
                       subqueryWhere = subqueryWhere+"("+primKeys[i]+" LIKE '"+primKeyValue+"')";
                   }
                    else
                   {
                       subqueryWhere = subqueryWhere+"("+primKeys[i]+" LIKE '"+pkFromOnePanelForTables+"')";
                   }  //primKeyValue+"')"; // when is updating if a second time after insert is selected
               }
               else
               {
                   subqueryWhere = subqueryWhere+"("+primKeys[i]+" LIKE '"+primKeysValue[i]+"')";
               }
          	  /*if(primKeyDb.equalsIgnoreCase(primKey))
          	  {// if is prim key like farmerId get from tb.getText
                      //System.out.println("PanelOneDataOneRecData.rowUpdate  subqueryWhere IF  ("+i+")  "+primKey+"   "+getPrimKeyValue(primKey)+"  primKeyValue:"+primKeyValue);   
                     if(isNewRec)
                     {                            
                            subqueryWhere = subqueryWhere+utilsPanelReport.getNoOfPKOfNewRecord(true,dbFieldsAll,entity)+"')";                    
          	       	        //System.out.println("panelOneDataOneRecData.updateRow "+columnLabel+" "+getValueForVariable(whereValueName));
                     }
                     else
                     {
                         subqueryWhere = subqueryWhere+primKeyValue+"')";
                     }
                       
          	       	  
          	  }
          	  else
          	  {*/
                      //System.out.println("PanelOneDataOneRecData.rowUpdate  subqueryWhere ELSE  ("+i+")  "+primKey+"   "+getPrimKeyValue(primKey)+"  primKeyValue:"+primKeyValue);   
          	  //   subqueryWhere = subqueryWhere+primKeysValue[i]+"')";	
          	  //}
          	  
                  //System.out.println("PanelODORData.rowUpdate  subqueryWhere "+i+" "+primKey+"  "+primKeyValue+"  "+getPrimKeyValue(primKey));
                  
                  
          	  if (i < primKeys.length-1 && primKeys.length>1) 
          	  // add AND but not on the last field(before where), also not when there is only one PK . -1 because arraylist starts from 0
          	  { subqueryWhere = subqueryWhere+" AND  ";   }              
              
              
/*              String primKey;
              primKey = databaseTableMeta.getPrimKeyName(i);
            //  System.out.println("PanelODORData.rowUpdate ==> ("+i+") "+primKey+" for "+entity);
              
               //System.out.println("PanelOneDataOneRecData.rowUpdate  subqueryWhere  ("+i+")  "+primKey+"   "+getPrimKeyValue(primKey)+"  primKeyValue:"+primKeyValue);   
               
              subqueryWhere = subqueryWhere+"("+primKey+" LIKE '";
          	  
          	  if(primKeyDb.equalsIgnoreCase(primKey))
          	  {// if is prim key like farmerId get from tb.getText
                      //System.out.println("PanelOneDataOneRecData.rowUpdate  subqueryWhere IF  ("+i+")  "+primKey+"   "+getPrimKeyValue(primKey)+"  primKeyValue:"+primKeyValue);   
                     if(isNewRec)
                     {                            
                            subqueryWhere = subqueryWhere+utilsPanelReport.getNoOfPKOfNewRecord(true,dbFieldsAll,entity)+"')";                    
          	       	        //System.out.println("panelOneDataOneRecData.updateRow "+columnLabel+" "+getValueForVariable(whereValueName));
                     }
                     else
                     {
                         subqueryWhere = subqueryWhere+primKeyValue+"')";
                     }
                       
          	       	  
          	  }
          	  else
          	  {
                      //System.out.println("PanelOneDataOneRecData.rowUpdate  subqueryWhere ELSE  ("+i+")  "+primKey+"   "+getPrimKeyValue(primKey)+"  primKeyValue:"+primKeyValue);   
          	     subqueryWhere = subqueryWhere+getPrimKeyValue(primKey)+"')";	
          	  }
          	  
                  //System.out.println("PanelODORData.rowUpdate  subqueryWhere "+i+" "+primKey+"  "+primKeyValue+"  "+getPrimKeyValue(primKey));
                  
                  
          	  if (i < databaseTableMeta.getCountOfPrimKeys()-1 && databaseTableMeta.getCountOfPrimKeys()>1) 
          	  // add AND but not on the last field(before where), also not when there is only one PK . -1 because arraylist starts from 0
          	  { subqueryWhere = subqueryWhere+" AND  ";   }

            */      
          }
         
        if(!subquerySet.trim().equalsIgnoreCase("")) // subquerySet when field class is 'table' it is empty, so go to else
        {
          String updateQuery = "UPDATE "+entity+
          " SET " + subquerySet+
          " WHERE "+subqueryWhere ; 
           
          if (VariablesGlobal.globalShowSQLEdit)
          { System.out.println("PanelODORData.rowUpdate query: "+updateQuery); }
   
          System.out.println("PanelODORData.rowUpdate query: "+updateQuery); 
          
            ret = dbTransaction.transactionUpdateQuery(updateQuery,"PanelODORData.rowUpdate", showDialogOnError);
            //ret = db.updateQuery(updateQuery,"PanelODORData.rowUpdate", showDialogOnError);
        }
        else //// subquerySet when field class is 'table' it is empty
        {
            System.out.println("PanelODORData.rowUpdate  (class is 'table')   subquerySet:"+subquerySet+"    ret:"+ret+"=0");
            ret = 0;
        }

        boolean retBool = this.rowInsertAndUpdateImage(dbTransaction);

       

         return ret;
   }
  
   
   private boolean rowUpdateHtmlFormElementsByDeleteAndInsert(Database dbTransaction) throws SQLException 
   {
       boolean ret = false;
       //-----------------------------------------------------------------
        String strTableDocDataForeignKey="vatDocForPeriodId"; 
       
     //--------------------------------------------------------------  
              //System.out.println("PanelODORData.rowDelete "+entity);


                       utilsPanelReport.retrievePrimKeyValueForOnePK(query, selectedRow, null,dbFieldsAll,true,/*primKeyIn,intColumnOfDescriptionIn,
                       sql2WhereField, sql2WhereValue,*/ entity, /*tableModelReadOnly,*/ null);    
                       
                        String[] primKeys = utilsPanelReport.getPrimKeys();
                        String[] primKeysCaption = utilsPanelReport.getPrimKeysCaption();
                        //System.out.println("-->  PanelOneDataOneRecData.rowDelete '"+entity+"' selectedRow:"+selectedRow+"  primKeys:"+primKeys.length); 
                        int primKeysCount = primKeys.length;
                        String[] primKeysValue = utilsPanelReport.getPrimKeysValue();              
                   String strTableDocDataForeignKeyValue = "";
                        for(int k= 0;k<primKeys.length;k++)
                        {
                            if(strTableDocDataForeignKey.equalsIgnoreCase(primKeys[k]))
                            {
                                      strTableDocDataForeignKeyValue = primKeysValue[k] ;
                            }
                            
                        }
                        
                        

   	      for(int i=0;i<fieldTxts.size();i++)
   	      {

             String colCaption =  dbFieldsInGroupOfPanels[i].getCaption(); 
             String classtype = dbFieldsInGroupOfPanels[i].getColClassName();  // rsmd.getColumnClassName(i+1);
             int columnWidth = dbFieldsInGroupOfPanels[i].getColWidth();  //rsmd.getColumnDisplaySize(i+1);   
             
          if(classtype.equalsIgnoreCase("htmlfile"))            
          {
              String tableChildDocData = dbFieldsInGroupOfPanels[i].getChildTable();
              //if(rowDeleteHtmlFormElements(dbTransaction,tableChildDocData, strTableDocDataForeignKey,strTableDocDataForeignKeyValue))
              //{
              rowDeleteHtmlFormElements(dbTransaction,tableChildDocData, strTableDocDataForeignKey,strTableDocDataForeignKeyValue);
                System.out.println("PanelODORData.rowUpdateHtmlFormElementsByDeleteAndInsert htmlfile  after Delete before Insert  strTableDocDataForeignKeyValue:"+strTableDocDataForeignKeyValue);     
                ret = rowInsertHtmlFormElements(dbTransaction,strTableDocDataForeignKeyValue);
               if(!ret)
                {
                   ret=false;
                   break;
                }
         }
         else
         {
                  ret=true;
                 
         }
         /* }   
          else
         { 
                 ret=true;//  if not set ret and continue
                            //System.out.println("PanelODORData.rowUpdateHtmlFormElementsByDeleteAndInsert  return is true");           
         }  */
           }// for
                    /*   int intRet =  dbTransaction.transactionUpdateQuery(updateQuery,"PanelODORData.rowUpdateHtmlFormElements ",showDialogOnError);
                      //System.out.println("PanelODORData.rowUpdateHtmlFormElements         intRet:"+intRet+"       from query query:"+insertQuery);
                     if(intRet==1)
                     {
                         ret=true;
                     }
                     else
                     {
                         System.out.println("PanelODORData.rowUpdateHtmlFormElementsByDeleteAndInsert ERROR    ");
                         ret=false;
                         break; 
                     }*/
       
       closeDB();
       
       return  ret;
       
   }
   
   
   
   
   
   
   // called by
   ///                                                                        // String pkFromOnePanelForTables for second panel (ie in second panel of form )
    private int rowUpdateAll(Database dbTransaction,boolean isNewRecIn,String pkFromOnePanelForTables) throws SQLException //taken and adapted from panelTwoDataOneRec
    {
        
          int ret=0;
 
          //System.out.println("PanelODORData.rowUpdateAll      isNewRec:"+isNewRec+"    boolIsFromCopyOfRecord:"+boolIsFromCopyOfRecord+"   pkFromOnePanelForTables:"+pkFromOnePanelForTables);
                 if(rowUpdateTables(dbTransaction,isNewRecIn,pkFromOnePanelForTables))
                 {
                     //System.out.println("PanelOneDataOneRecData.rowUpdateAll     IF     panelOneDataOneRecData not saved     pkFromOnePanelForTables:"+pkFromOnePanelForTables);
                    
                     if(rowUpdateHtmlFormElementsByDeleteAndInsert(dbTransaction))
                    // if(rowUpdateHtmlFormElements(dbTransaction))  //  pkFromOnePanelForTables shows th all+1 which is for new
                    {
                         //System.out.println("PanelOneDataOneRecData.rowUpdateAll     IF  IF ");
                    // boolean retBool = this.rowInsertAndUpdateImage(dbTransaction);    is inside    this.rowUpdate(dbTransaction);
                     ret = this.rowUpdate(dbTransaction, pkFromOnePanelForTables);  // String pkFromOnePanelForTables for second panel (ie in second panel of form )
                     
                 
                   // if (ret==0)
                   // {
                        if(ret==0 && dbFieldsAll.length == 1  )
                        {
                        System.out.println("00000  PanelOneDataOneRecData.rowUpdateAll panelOneDataOneRecData not saved");
                        }
                        else
                        {

                            
                        }
                   // }
                    }
                    else
                    {
                        System.out.println("00000  PanelOneDataOneRecData.rowUpdateAll panelOneDataOneRecData not saved   rowUpdateAll(dbTransaction) is false.");
                    }
                 }
                 else
                 {
                     System.out.println("PanelOneDataOneRecData.rowUpdateAll     ELSE     panelOneDataOneRecData not saved");
                     ret=0;
                 }
           
  

     return ret;
    }
    
    /*
     *  pkFromOnePanelForTables is used only when isNewRecIn is true
    */
    private boolean rowUpdateTables(Database dbTransaction,boolean isNewRecIn, String pkFromOnePanelForTables)throws SQLException
    {
    //System.out.println("PanelODORData.rowUpdateTables  === dbTransaction:"+dbTransaction+"  isNewRecIn:"+isNewRecIn);
        
               boolean ret=false;
               if (dbFieldsInGroupOfPanels==null)
               {
                    System.out.println("PanelOneDataOneRecData.rowUpdateAll  dbFieldsInGroupOfPanels="+dbFieldsInGroupOfPanels);
               }
               int colCount =dbFieldsInGroupOfPanels.length;
              ArrayList listRetSavedAll = new ArrayList();
              
              for (int i = 0; i < colCount; i++)//  i = fieldTxts
              { 
                 String columnName = fields[i]; //get colunm name  	           	
	         String classtype = dbFieldsInGroupOfPanels[i].getColClassName();  // if integer then not add ' and ' between values
     
                if(classtype.equalsIgnoreCase("table"))
                {
                    //System.out.println("  --------  PanelODORData.rowUpdateTables    table("+i+")     isNewRecIn:"+isNewRecIn);
                    PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(i);//,PanelOneDataManyRecData);
                    //System.out.println("PanelODORData.rowUpdateTables    table  ("+i+")   isNewRecIn:"+isNewRecIn+"  columnName:"+columnName+"     pkFromOnePanelForTables:"+pkFromOnePanelForTables);
                    if(isNewRecIn)
                    {
                        //String pkFromOnePanelForTables = utilsPanelReport.getNoOfPKAutoIncOfNewRecord(false,dbFieldsAll,entity);// used for table
                        
                        pnlODMRData.setPrimKeyValueInTableModelResultSet(pkFromOnePanelForTables); // when inserting a new record (from OneData) then the new pk must be set
                    }
                    else
                    {
                        for(int f=0;f<dbFieldsAll.length;f++)//searches in allfields, not only dbFieldsInGroupOfPanels
                        {        
                                String columnDbName = dbFieldsAll[f].getDbField(); //fields[i-1];//get colunm name 
                            //System.out.println("PanelODORData.rowUpdateTables  B  table  ("+i+")("+f+")   isNewRecIn:"+isNewRecIn+"  primKeyDb:"+primKeyDb+"    columnDbName:"+columnDbName+"      pkFromOnePanelForTables:"+pkFromOnePanelForTables);
                            if(primKeyDb.equalsIgnoreCase(columnDbName))
                            {
                                
                                 pnlODMRData.setPrimKeyValueInTableModelResultSet(primKeyValue); // when updating a record(there are rows of values in table) then insert the pk  that already the OneData record has    
                            }
                            else
                            {
                                
                            }
                        }
                    }
                  // 
                    // if(pnlODMRData.getRowCount()>0)
                    //{
                    System.out.println("   o=OOO=o   PanelOneDataOneRecData.rowUpdateTables        columnName:"+columnName+"     classtype:"+classtype+"     boolIsFromCopyOfRecord:"+boolIsFromCopyOfRecord);
                       ret = pnlODMRData.saveChanges(dbTransaction,boolIsFromCopyOfRecord,isNewRecIn);    
                   /* }
                    else
                    {
                        ret=true;
                    }*/
                    
                    
                    
                    listRetSavedAll.add(ret);

                }

              }                 
              
           if(listRetSavedAll.size()>0)         
           {   // when tables 1 or more than 1
              for(int l = 0 ;l<listRetSavedAll.size();l++)
              {
                  if(Boolean.parseBoolean(listRetSavedAll.get(l).toString()))
                  {
                      ret=true;
                  }
                  else
                  {
                      ret=false;
                      break;
                  }
              }
              
              System.out.println("  oo--oo PanelOneDataOneRecData.rowUpdateTables   size:"+listRetSavedAll.size()+"    ret:"+ret);    
           }
           else
           {  // when tables not exist
                ret=true;
           }
  
    	
       System.out.println("  --------- PanelOneDataOneRecData.rowUpdateTables   boolIsFromCopyOfRecord:"+boolIsFromCopyOfRecord);            
        
      return ret;
    }   
   
   
   
   /*
    * 
    * exists in PanelOneDataOneRecData, PanelOneDataManyRecData, TableModelResultSet and PanelDataFilter
    */
   public ArrayList getListOfFieldsUncompleted()
   {
   	 boolean ret = false;
   	 String output="";
   	 
   	 ArrayList listMessages = new ArrayList();
   	 
   	 if(fieldTxts!=null && dbFieldsInGroupOfPanels!=null && fieldTxts.size()==dbFieldsInGroupOfPanels.length)
   	 {

   	    for(int f=0;f<fieldTxts.size();f++)
   	    {
   	    	String columnClass="";
  	    		
   	    	  
   	    	  //rsmd = db.retrieveRSMetaData(query);
   	    		
   	    	   columnClass =  dbFieldsInGroupOfPanels[f].getColClassName();   //rsmd.getColumnClassName(f+1);	
   	    	   	
              // System.out.println("PanelODORData.getListOfFieldsUncompleted  "+f+" columnClass:"+columnClass+"    field:"+dbFieldsInGroupOfPanels[f].getDbField());
   	       
                int fieldObligatoryOrSuggest = dbFieldsInGroupOfPanels[f].getFieldObligatoryOrSuggest();  

               if(fieldObligatoryOrSuggest==FIELD_OBLIGATORY)
               {
               	  if(columnClass.equalsIgnoreCase("java.sql.Date") ||  columnClass.equalsIgnoreCase("java.lang.Date"))
               	  {
             	        JTextBoxWithEditButtons textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(f);
                       //String text = textEditFormatedDate.getText().trim();
                       //System.out.println("PanelODORData.getListOfFieldsUncompleted suggested "+f+"  "+text);
               	  	   if(textEditFormatedDate.isDateEmpty())       	
               	  	   {
               	  	   	//textEditFormatedDate.setBackground(Color.yellow);	 
               	  	   	 //System.out.println("PanelODORData.getListOfFieldsUncompleted obligatory "+f+" "+dbFieldsInGroupOfPanels[f].getDbField()+" is empty ");
               	  	   	 EntityMessage em =new EntityMessage(dbFieldsInGroupOfPanels[f].getCaption(),FIELD_OBLIGATORY,0,f,"");
               	  	   	 listMessages.add(em);
               	  	   	 
               	  	   	 //outputObligatory=outputObligatory+outputObligatoryFields+" "+dbFieldsInGroupOfPanels[f].getCaption()+outputObligatoryFieldsEnd+"\n";
               	  	   }               	  	
               	  }
               	  else if (columnClass.equalsIgnoreCase("java.lang.Boolean"))
          	  {
          	      	
          	  }             
                  else if(dbFieldsInGroupOfPanels[f].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)             
                  {
 
                   JComboBox cmbBox =  (JComboBox) fieldTxts.get(f);
                    String text =  cmbBox.getSelectedItem().toString();
               	  	   if(text.equals(""))        	
               	  	   {               

               	  	   	cmbBox.revalidate();  

               	  	   	 EntityMessage em =new EntityMessage(dbFieldsInGroupOfPanels[f].getCaption(),FIELD_OBLIGATORY,0,f,"");
               	  	   	 listMessages.add(em);               	  	   	     
               	  	   	//	outputObligatory=outputObligatory+outputObligatoryFields+" "+dbFieldsInGroupOfPanels[f].getCaption()+outputObligatoryFieldsEnd+"\n";
               	          //System.out.println("PanelODORData.getListOfFieldsUncompleted obligatory "+f+" "+dbFieldsInGroupOfPanels[f].getDbField()+" is empty ");
               	  	   }                      
                  }
                  else if (columnClass.equalsIgnoreCase("htmlfile"))
          	  {
                      
                  }
               	  else
               	  {
               	  	   	   JTextComponent ta = (JTextComponent) fieldTxts.get(f); // i-1 because fieldTxts is an array and starts from 0
                           String text = ta.getText().trim();  
               	  	   if(text.equals(""))        	
               	  	   {               
 	   		           // ta.setBackground(Color.yellow);	 
	                    //final int x = jt.getWidth()  - 18;
                        //jt.setMargin(new Insets(0,0, 0,  0));
                        //lblIcoAttention = new JLabel(ICO_FIELDATTENTION);
	                    //lblIcoAttention.setBounds(2,2,18,18);
	                    //ta.add(lblIcoAttention);
               	  	   	ta.revalidate();  

               	  	   	 EntityMessage em =new EntityMessage(dbFieldsInGroupOfPanels[f].getCaption(),FIELD_OBLIGATORY,0,f,"");
               	  	   	 listMessages.add(em);               	  	   	     
               	  	   	//	outputObligatory=outputObligatory+outputObligatoryFields+" "+dbFieldsInGroupOfPanels[f].getCaption()+outputObligatoryFieldsEnd+"\n";
               	          //System.out.println("PanelODORData.getListOfFieldsUncompleted obligatory "+f+" "+dbFieldsInGroupOfPanels[f].getDbField()+" is empty ");
               	  	   }
               	  }
               	  
               }
               else if(fieldObligatoryOrSuggest==FIELD_SUGGEST)
               {
               	  if(columnClass.equalsIgnoreCase("java.sql.Date") ||  columnClass.equalsIgnoreCase("java.lang.Date"))
               	  {
             	        JTextBoxWithEditButtons textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(f);
                       //String text = textEditFormatedDate.getText().trim();
                       //System.out.println("PanelODORData.getListOfFieldsUncompleted suggested "+f+"  "+text);
               	  	   if(textEditFormatedDate.isDateEmpty())       	
               	  	   {
               	  	   	//textEditFormatedDate.setBackground(Color.yellow);
               	  	   	 EntityMessage em =new EntityMessage(dbFieldsInGroupOfPanels[f].getCaption(),FIELD_SUGGEST,0,f,"");
               	  	   	 listMessages.add(em);               	  	   	
               	  	   	 //outputSuggest=outputSuggest+outputSuggestFields+" "+dbFieldsInGroupOfPanels[f].getCaption()+outputSuggestFieldsEnd+"\n";
               	  	   	 //System.out.println("PanelODORData.getListOfFieldsUncompleted suggested "+f+" "+dbFieldsInGroupOfPanels[f].getDbField()+" is empty ");
               	  	   }               	  	
               	  }
               	  else if (columnClass.equalsIgnoreCase("java.lang.Boolean"))
          	  {
               	  	  /* 	   JCheckBox ta = (JCheckBox) fieldTxts.get(f); // i-1 because fieldTxts is an array and starts from 0
                           boolean sel = ta.isSelected();  
               	  	   if(text.equals(""))        	
               	  	   {
               	  	   	 System.out.println("PanelODORData.getListOfFieldsUncompleted suggested "+f+" is empty ");
               	  	   }  */        	      	
          	  }     
                  else if(dbFieldsInGroupOfPanels[f].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)             
                  {
 
                   JComboBox cmbBox =  (JComboBox) fieldTxts.get(f);
                    String text =  cmbBox.getSelectedItem().toString();
               	  	   if(text.equals(""))        	
               	  	   {               

               	  	   	cmbBox.revalidate();  

               	  	   	 EntityMessage em =new EntityMessage(dbFieldsInGroupOfPanels[f].getCaption(),FIELD_SUGGEST,0,f,"");
               	  	   	 listMessages.add(em);               	  	   	     
               	  	   	//	outputObligatory=outputObligatory+outputObligatoryFields+" "+dbFieldsInGroupOfPanels[f].getCaption()+outputObligatoryFieldsEnd+"\n";
               	          //System.out.println("PanelODORData.getListOfFieldsUncompleted obligatory "+f+" "+dbFieldsInGroupOfPanels[f].getDbField()+" is empty ");
               	  	   } 
                  }
                  else if (columnClass.equalsIgnoreCase("htmlfile"))
          	  {
                      
                  }                  
               	  else
               	  {    
               	  	   	   JTextComponent ta = (JTextComponent) fieldTxts.get(f); // i-1 because fieldTxts is an array and starts from 0
                           String text = ta.getText().trim();  
               	  	   if(text.equals(""))        	
               	  	   {
               	  	   	//ta.setBackground(Color.yellow);	
               	  	   	//lblIcoAttention = new JLabel(ICO_FIELDATTENTION);
	                    //lblIcoAttention.setBounds(2,2,18,18);
	                    //ta.add(lblIcoAttention); 
	                    ta.revalidate();   

               	  	   	 EntityMessage em =new EntityMessage(dbFieldsInGroupOfPanels[f].getCaption(),FIELD_SUGGEST,0,f,"");
               	  	   	 listMessages.add(em);    	                            
	                     //outputSuggest=outputSuggest+outputSuggestFields+" "+dbFieldsInGroupOfPanels[f].getCaption()+outputSuggestFieldsEnd+"\n";	  	   	
               	  	   	 //System.out.println("PanelODORData.getListOfFieldsUncompleted suggested "+f+" "+dbFieldsInGroupOfPanels[f].getDbField()+" is empty ");
               	  	   }
               	   
               	  }
               }
               else if(fieldObligatoryOrSuggest==FIELD_NOCOMPLETION)
               {
                   
               }  
               else if(fieldObligatoryOrSuggest==FIELD_TABLE_NOROWCOMPLETION || fieldObligatoryOrSuggest==FIELD_TABLE_ONEROWATLEAST_OBLIGATORY ||  fieldObligatoryOrSuggest==FIELD_TABLE_ONEROWATLEAST_SUGGEST)
               {
                  if (columnClass.equalsIgnoreCase("table"))
          	  {
                       PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(f);
                      if(pnlODMRData.getRowCount()==0 && fieldObligatoryOrSuggest==FIELD_TABLE_ONEROWATLEAST_OBLIGATORY)
                      {
               	  	   	 EntityMessage em =new EntityMessage(dbFieldsInGroupOfPanels[f].getCaption(),FIELD_TABLE_ONEROWATLEAST_OBLIGATORY,0,f,dbFieldsInGroupOfPanels[f].getChildTable());
               	  	   	 listMessages.add(em);  
                      }
                      else if(pnlODMRData.getRowCount()==0 && fieldObligatoryOrSuggest==FIELD_TABLE_ONEROWATLEAST_SUGGEST)
                      {
               	  	   	 EntityMessage em =new EntityMessage(dbFieldsInGroupOfPanels[f].getCaption(),FIELD_TABLE_ONEROWATLEAST_SUGGEST,0,f,dbFieldsInGroupOfPanels[f].getChildTable());
               	  	   	 listMessages.add(em);  
                      }                          
                      else
                      {
                           ArrayList listMessagesMany =  pnlODMRData.getListOfFieldsUncompleted();
                           if(listMessagesMany.size()>0)
                           {
    	                       for(int l = 0;l<listMessagesMany.size();l++)
    	                       {
    	                         	listMessages.add(listMessagesMany.get(l));
    	                       }
                           }
                      }
                  } 
               }                 
   	       else
   	       {
   	 	System.out.println("PanelDataFilter.getListOfFieldsUncompleted UNKNOWN fieldObligatoryOrSuggest="+fieldObligatoryOrSuggest);
   	       }                                 
               	
   	    	
   	 	
   	    }
   	    
   	    //outputObligatory=outputObligatory+outputObligatoryFields;
        //outputSuggest=outputSuggest+outputSuggestFields;
        
       // output=outputObligatory+outputSuggest;
        
       // System.out.println("PanelODORData.getListOfFieldsUncompleted \n"+output);
   	 }
   	 else
   	 {
   	 	System.out.println("error   PanelODORData.getListOfFieldsUncompleted fieldTxts and dbFieldsInGroupOfPanels NOT have equal length. dbFieldsInGroupOfPanels="+dbFieldsInGroupOfPanels);
   	 }   	 
   	 
         //System.out.println("  PanelODORData.getListOfFieldsUncompleted    size:"+listMessages.size());
         
   	 return listMessages;
   }
   
   
   
   
   
   
   
   /*
   *  private, called from here, more than one time 
   *
   */
   private void checkIfFieldsHaveUniqueValueWhileInDataEntry(String fieldName)
   {
       //System.out.println("??????????????????  PanelOneDataOnRecData.checkUniquityOfFields   fieldName:"+fieldName);
      if(entityPanel.getFieldsUnique()!= null) 
      {
          String[] strFieldsUniqueArray = new String[entityPanel.getFieldsUnique().length];
          //String[] strFieldsValueArray = new String[entityPanel.getFieldsUnique().length];
          //String strAllFields="";
          String entityName = entityPanel.getEntity();
       //System.out.println("??????????????????  PanelOneDataOnRecData.checkUniquityOfFields    length:"+entityPanel.getFieldsUnique().length+"   fieldName:"+fieldName);
          
       for(int u = 0;u<entityPanel.getFieldsUnique().length;u++)
       {
           strFieldsUniqueArray[u] = entityPanel.getFieldsUnique()[u];
           
          if((entityName+"."+strFieldsUniqueArray[u]).equalsIgnoreCase(fieldName))
          {
             // System.out.println("PanelOneDataOnRecData.checkIfFieldsHaveUniqueValueWhileInDataEntry   if   =====>   "+u+"  fieldName:"+fieldName+"="+entityName+"."+strFieldsUniqueArray[u]);
              checkUniquityOfFields(IS_UNIQUE_WHILE_DATAENTRY);
          }
          else
          {
              //System.out.println("PanelOneDataOnRecData.checkIfFieldsHaveUniqueValueWhileInDataEntry   else   ===>   "+u+"  fieldName:"+fieldName+"   strFieldsUniqueArray[u]:"+strFieldsUniqueArray[u]);
          }

       }
      }
      else
      {
          System.out.println("error  PanelOneDataOnRecData.checkIfFieldsHaveUniqueValueWhileInDataEntry entityPanel.getFieldsUnique() = null");
      }
       
       
   }
   
   
   public String getFieldsCaptionsToBeCheckedForUniquenceSepparatedByCommas()
   {
       String colCaptions="";
      /*if(entityPanel.getFieldsUnique()!= null) 
      {

         // String[] strFieldsOnTitleArray = new String[entityPanel.];
         // String strAllFields="";
       //for(int u = 0;u<entityPanel.getFieldsUnique().length;u++)
       //{

          //String fieldName = "";
          
         // String fieldValue = "";
         for(int i = 0;i<dbFieldsInGroupOfPanels.length;i++) 
         {
             if(i<dbFieldsInGroupOfPanels.length-1)
            colCaptions =   colCaptions+", "+dbFieldsInGroupOfPanels[i].getCaption();
           //fieldName = dbFieldsInGroupOfPanels[i].getDbField();

         } 
       //}//for
  
      }*/

      if(entityPanel.getFieldsUnique()!= null) 
      {
          String[] strFieldsUniqueArray = new String[entityPanel.getFieldsUnique().length];
          String[] strFieldsCaptionArray = new String[entityPanel.getFieldsUnique().length];
          String[] strFieldsValueArray = new String[entityPanel.getFieldsUnique().length];
         // String[] strFieldsOnTitleArray = new String[entityPanel.];
          //String strAllFields="";
       for(int u = 0;u<entityPanel.getFieldsUnique().length;u++)
       {
           strFieldsUniqueArray[u] = entityPanel.getFieldsUnique()[u];
            //System.out.println("PanelOneDataOnRecData.checkUniquityOfFields     fields:"+entityPanel.getFieldsUnique()[u]);
       
       
          String fieldName = "";
          String colCaption="";
         // String fieldValue = "";
         for(int i = 0;i<dbFieldsInGroupOfPanels.length;i++) 
         {
            colCaption =   dbFieldsInGroupOfPanels[i].getCaption();
           fieldName = dbFieldsInGroupOfPanels[i].getDbField();
           boolean isVisible = dbFieldsInGroupOfPanels[i].getIsVisibleOrEditable()!=FIELD_NOT_VISIBLE;
           if(strFieldsUniqueArray[u].equalsIgnoreCase(fieldName) && isVisible )
           {
               
               if(u!=0)//<dbFieldsInGroupOfPanels.length-1)
               {
                  colCaptions = colCaptions+", "+colCaption;
               }
               else
               {
                   colCaptions = colCaption;
               }
                 //JTextComponent txtb = (JTextComponent)fieldTxts.get(i);// arraylist starts from 0
                 //strFieldsValueArray[u] = txtb.getText();
           }
         } 
       }//for      
      
      
      }
   return colCaptions;
   }
   
   
   private String getFieldValueFromFieldName(String fieldNameToCheck,int column)
   {
       
          String fieldName = "";
          String colCaption="";
          String classtype = "";
          String fieldValue = "";
         for(int i = 0;i<dbFieldsInGroupOfPanels.length;i++) 
         {
            colCaption =   dbFieldsInGroupOfPanels[i].getCaption();
           fieldName = dbFieldsInGroupOfPanels[i].getDbField();
            classtype = dbFieldsInGroupOfPanels[i].getColClassName();       
                if(classtype.equalsIgnoreCase("table"))
                {
                    //System.out.println("  --------  PanelODORData.rowUpdateTables    table("+i+")     isNewRecIn:"+isNewRecIn);
                    PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(i);//,PanelOneDataManyRecData);
                    //System.out.println("PanelODORData.checkUniquityOfFields row count"+pnlODMRData.getRowCount());
                    for(int r =0 ;r<pnlODMRData.getRowCount();r++)
                    {
                        EntityDBFields[] edb= pnlODMRData.getDbFieldsChild();
                        for(int c = 0;c<edb.length;c++)
                        {
                            if(fieldName.equalsIgnoreCase(edb[c].getDbField()))
                            {
                                System.out.println("   PanelODORData.getFieldValueFromFieldName tablevalue    r:"+r+"   c:"+c+"   col field:"+edb[c].getDbField()+"   value:"+pnlODMRData.getTableValueAt(r, c));     
                                //strFieldsValueArray[u] = pnlODMRData.getTableValueAt(r, c);
                            }
                           
                        }//dbFieldsInGroupOfPanels
                    }
                }
                else if (classtype.equalsIgnoreCase("htmlfile"))
          	{
                      
                }                
                else
                {
                    //if(fieldName.equalsIgnoreCase(fieldNameToCheck))
                    if(column==i)
                    {
                     JTextComponent txtb = (JTextComponent)fieldTxts.get(i);// arraylist starts from 0   
                    fieldValue = txtb.getText();
                    }
                }       
       
         }         
       
       return fieldValue;
       
       
   }
   
   /*
   * //colOfTxtField is the txtfield, 
   *  // fieldNameToCheck(ie customer.customerId or saleheader.customerId) fieldNameToCheck is the name of the field. But, since it also holds the table itmay be wrong.
   */
   private void checkFieldsIfThenElse(int colOfTxtField, int calledBy)//String fieldNameToCheck
   {


       /*EntityCheckFields[] entityCheckFields =new EntityCheckFields[1]
       entityCheckFields[0] = new EntityCheckFields("SELECT customer.vatNo FROM customer WHERE customer.dbCompanyId = "+VariablesGlobal.globalCompanyId,"customer.customerId","",7,
               "SELECT dbcompany.companyVatNo FROM dbcompany ","dbcompany.dbCompanyId",VariablesGlobal.globalCompanyId,-1,
               "Το ΑΦΜ του πελάτη είναι ίδιο με αυτό της επιχείρησης στην οποία εργάζεστε.");*/
      // queryIfA+idNoA
  if(entityCheckFields!=null)    
  {
    for(int f= 0; f<entityCheckFields.length;f++)
    {
        
 /*if(entityCheckFields[f].getQueryIfB().equalsIgnoreCase("") && entityCheckFields[f].getFieldPkB().equalsIgnoreCase("") && entityCheckFields[f].getFieldValueB().equalsIgnoreCase("") && entityCheckFields[f].getColumnB() == -1 )
 {*/

    if(colOfTxtField==entityCheckFields[f].getColumnΑ() && entityCheckFields[f].getWhenToCheck()== calledBy)//CHECK_ON_INSERT_OR_ON_UPDATE )
    {

        String queryCheck =  entityCheckFields[f].getQueryIfA();

                int[] textsInput = entityCheckFields[f].getInputFields();  
                String [] textString = new String[textsInput.length];

                for(int c=0;c<textsInput.length;c++)
                {
                       if(dbFieldsInGroupOfPanels[textsInput[c]].getLookupType()==LOOKUPTYPE_ONLYONE_THISFIELD || dbFieldsInGroupOfPanels[textsInput[c]].getLookupType()==LOOKUPTYPE_NOLOOKUP)
                       {
                          JTextComponent tbToGet = (JTextComponent)fieldTxts.get(textsInput[c]);
                          textString[c] = tbToGet.getText();
                          if(dbFieldsInGroupOfPanels[textsInput[c]].getColClassName().equalsIgnoreCase("java.lang.Double"))
                          {
                              textString[c] = utilsDouble.getDoubleSaving(textString[c]);
                              
                          }
                                         
                       }
                       else if (dbFieldsInGroupOfPanels[textsInput[c]].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)
                       {  // showRow
                         LookupTableConstantsMgt lookUpTableConstants = new LookupTableConstantsMgt();
                         EntityLookupTableConstantsData[] elutcData = lookUpTableConstants.getEntityLookupTableConstantsData(dbFieldsInGroupOfPanels[textsInput[c]].getLookupEntityName());
              
              	            JComboBox cb = (JComboBox) fieldTxts.get(textsInput[c]);
                          textString[c] = elutcData[cb.getSelectedIndex()].getPk();

                      }
                      else 
                      {
                            System.out.println("  error PanelOneDataOneRecData.checkFieldsIfThenElse    textsInput[c]:"+textsInput[c]+"  ("+c+")   UNKNOWN   name:"+dbFieldsInGroupOfPanels[textsInput[c]].getDbField()+"   getLookupType:"+dbFieldsInGroupOfPanels[textsInput[c]].getLookupType());
                      }                
                }           
        
 
             System.out.println("PanelODORData.checkFieldsIfThenElse  FIELDSCALCULATION_CATEGORY_SAME   colOfTxtField:"+colOfTxtField+" textString.length:"+textString.length+"      queryCheck:"+queryCheck);        
               for(int s=0;s<textString.length;s++)
               {   
                   int indexOfHashChar = queryCheck.indexOf("#");
                    //if does not have an empty string inside textString then continue
                   if(!textString[s].equalsIgnoreCase(""))
                   {
                       System.out.println("   if  textString  s:"+s+"  textString:"+textString[s]+"    indexOfHashChar:"+indexOfHashChar);
                        if(indexOfHashChar!=-1)
                       { 
 
                           
                          queryCheck = utilsString.replaceTextOfAStringWithText("#", queryCheck, textString, null);
                          //System.out.println("PanelODORData.checkFieldsIfThenElse   s:"+s+"   indexOfHashChar:"+indexOfHashChar+"      textString.length:"+textString.length+"    queryCheck:"+queryCheck);
                       }
               
                
                   }
                   else
                   {
                        System.out.println(" error  PanelOneDataOnRecData.checkFieldsIfThenElse  error  else  textString  s:"+s+"  textString[s]:"+textString[s]+"(is nothing)    indexOfHashChar:"+indexOfHashChar);
                   }
               }        

  
        if(queryCheck.indexOf("#")==-1)// if there is no # continue
        {
          String retStringA="";
          try
          {
            db.retrieveDBDataFromQuery(queryCheck,"PanelOneDataOnRecData.checkFieldsIfThenElse");
   	    rs=db.getRS();
            //System.out.println("PanelOneDataOnRecData.checkFieldsIfThenElse     queryCheck:"+queryCheck);
            rs.beforeFirst();
            if(rs.next())
            { 
               retStringA =  rs.getString(1);
            }
          }
          catch(SQLException e)
          {
            System.out.println("error  PanelOneDataOnRecData.checkFieldsIfThenElse "+e.getMessage());
            e.printStackTrace();
            
          }
          finally
          {
              closeDB();
          }      

      if(retStringA.equalsIgnoreCase("1"))
      {
     String textMessageWhenTrue = entityCheckFields[f].getTextMessageWhenTrue();
     utilsGui.showMessageInfo(textMessageWhenTrue);
      }
        }
      else
      {
                  System.out.println("error PanelOneDataOnRecData.checkFieldsIfThenElse     has #     queryCheck:"+queryCheck);

      }
     
    }
 /*}
 else
 {     

      System.out.println("   PanelODORData.checkFieldsIfThenElse  f:"+f+"   getFieldPkΑ:"+entityCheckFields[f].getFieldPkΑ()+"    getFieldPkB:"+entityCheckFields[f].getFieldPkB()+"   colOfTxtField:"+ colOfTxtField);
    //if(fieldNameToCheck.equalsIgnoreCase(fieldPk))//"customer.customerId"))
    if(colOfTxtField==entityCheckFields[f].getColumnΑ() && entityCheckFields[f].getWhenToCheck()== calledBy)//CHECK_ON_ENTRY )
    {
      
      String idNoA="";
      
      if(entityCheckFields[f].getFieldValueΑ().equalsIgnoreCase(""))
      {
          idNoA=getFieldValueFromFieldName(entityCheckFields[f].getFieldPkΑ(),entityCheckFields[f].getColumnΑ());
      }
      else
      {
          idNoA = entityCheckFields[f].getFieldValueΑ();
      }

       String idNoB="";
      if(entityCheckFields[f].getFieldValueB().equalsIgnoreCase(""))
      {
          idNoB=getFieldValueFromFieldName(entityCheckFields[f].getFieldPkB(),colOfTxtField);//when 'if' is true it is colOfTxtField=-1
      }
      else
      {
          idNoB = entityCheckFields[f].getFieldValueB();
      }      
      
      String queryCheckA = "";
      
      if(utilsString.hasQueryWhere( entityCheckFields[f].getQueryIfA()))
      {      
         queryCheckA=entityCheckFields[f].getQueryIfA()+" AND "+entityCheckFields[f].getFieldPkΑ()+" LIKE "+idNoA;// "SELECT customer.vatNo FROM customer WHERE customer.dbCompanyId = "+VariablesGlobal.globalCompanyId+" AND customer.customerId LIKE 10";
      }
      else
      {
          queryCheckA=entityCheckFields[f].getQueryIfA()+" WHERE "+entityCheckFields[f].getFieldPkΑ()+" LIKE "+idNoA;
      }
      
      
     if(idNoA.equalsIgnoreCase("")) 
     {
         
     }
     else
     {
      String queryCheckB =  "";
      if(utilsString.hasQueryWhere( entityCheckFields[f].getQueryIfB()))
      {
          queryCheckB=entityCheckFields[f].getQueryIfB()+" AND "+entityCheckFields[f].getFieldPkB()+" LIKE "+idNoB;
      }
      else
      {
         queryCheckB=entityCheckFields[f].getQueryIfB()+" WHERE "+entityCheckFields[f].getFieldPkB()+" LIKE "+idNoB;//"SELECT dbcompany.companyVatNo FROM dbcompany WHERE dbcompany.dbCompanyId LIKE 1";
      }
      
      
      String textMessageWhenTrue = entityCheckFields[f].getTextMessageWhenTrue();
      


  
    
    String retStringA="";
          try
          {
            db.retrieveDBDataFromQuery(queryCheckA,"PanelOneDataOnRecData.checkFieldsIfThenElseA");
   	    rs=db.getRS();
            //System.out.println("PanelOneDataOnRecData.checkUniquityOfFields readSettingsFromFileOrApp  F      isUniqueWhen:"+isUniqueWhen+"     queryCheck:"+queryCheck);
            rs.beforeFirst();
            if(rs.next())
            { 
               retStringA =  rs.getString(1);
            }
          }
          catch(SQLException e)
          {
            System.out.println("error  PanelOneDataOnRecData.checkFieldsIfThenElseA "+e.getMessage());
            e.printStackTrace();
            
          }
          finally
          {
              closeDB();
          }         
       
 
    String retStringB="";
           
          
          
         //System.out.println("PanelOneDataOnRecData.checkUniquityOfFields  retStringA:"+retStringA+"   retStringB:"+retStringB+"     queryCheckA:"+queryCheckA+"         queryCheckB:"+queryCheckB); 
       if(retStringA.equalsIgnoreCase(retStringB))
       {
           utilsGui.showMessageInfo(textMessageWhenTrue+"  ("+retStringA+" = "+retStringB+")");
       }          
     } // if idNoA is not empty
    }/// colOfTxtField==entityCheckFields[f].getColumnΑ()
 }*/
 
   }//for
          
  }// if!=null  
         
          
   }
   
   
   /*
   *  called from PanelODOR.checkUniquityOfFields when user wants to save.
   * also called by this.checkIfFieldsHaveUniqueValueWhileInDataEntry
   *                     //IS_UNIQUE_WHILE_DATAENTRY = 0;
   *                     //IS_UNIQUE_BEFORE_SAVING = 1;
   */
   public boolean checkUniquityOfFields(int isUniqueWhen)
   {
       boolean ret = false;
       //entityGroupOfPanels=null;
       /*for(int i = 0;i<dbFieldsInGroupOfPanels.length;i++) 
      {       
          dbFieldsInGroupOfPanels[i].getCaption();
         String colName =   dbFieldsInGroupOfPanels[i].getDbField();
         //rs.getString(colName);
      }*/
       
      if(entityPanel.getFieldsUnique()!= null) 
      {
          boolean[] boolIsVisibleArray = new boolean[entityPanel.getFieldsUnique().length];
          String[] strFieldsUniqueArray = new String[entityPanel.getFieldsUnique().length];
          String[] strFieldsCaptionArray = new String[entityPanel.getFieldsUnique().length];
          String[] strFieldsValueArray = new String[entityPanel.getFieldsUnique().length];
         // String[] strFieldsOnTitleArray = new String[entityPanel.];
          String strAllFields="";
       for(int u = 0;u<entityPanel.getFieldsUnique().length;u++)
       {
           strFieldsUniqueArray[u] = entityPanel.getFieldsUnique()[u];
            //System.out.println("PanelOneDataOnRecData.checkUniquityOfFields     fields:"+entityPanel.getFieldsUnique()[u]);
       
       
          String fieldName = "";
          String colCaption="";
          String classtype = "";
         // String fieldValue = "";
         for(int i = 0;i<dbFieldsInGroupOfPanels.length;i++) 
         {
            colCaption =   dbFieldsInGroupOfPanels[i].getCaption();
           fieldName = dbFieldsInGroupOfPanels[i].getDbField();
            classtype = dbFieldsInGroupOfPanels[i].getColClassName();
           if(strFieldsUniqueArray[u].equalsIgnoreCase(fieldName))
           {
                boolean isVisible = dbFieldsInGroupOfPanels[i].getIsVisibleOrEditable()!=FIELD_NOT_VISIBLE;
                boolIsVisibleArray[u] = isVisible;
               strFieldsCaptionArray[u] = colCaption;
               
                if(classtype.equalsIgnoreCase("table"))
                {
                    //System.out.println("  --------  PanelODORData.rowUpdateTables    table("+i+")     isNewRecIn:"+isNewRecIn);
                    PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(i);//,PanelOneDataManyRecData);
                    //System.out.println("PanelODORData.checkUniquityOfFields row count"+pnlODMRData.getRowCount());
                    for(int r =0 ;r<pnlODMRData.getRowCount();r++)
                    {
                        EntityDBFields[] edb= pnlODMRData.getDbFieldsChild();
                        for(int c = 0;c<edb.length;c++)
                        {
                            if(fieldName.equalsIgnoreCase(edb[c].getDbField()))
                            {
                                System.out.println("   OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO PanelODORData.checkUniquityOfFields tablevalue    r:"+r+"   c:"+c+"   col field:"+edb[c].getDbField()+"   value:"+pnlODMRData.getTableValueAt(r, c));     
                                strFieldsValueArray[u] = pnlODMRData.getTableValueAt(r, c);
                            }
                           
                        }//dbFieldsInGroupOfPanels
                    }
                }
                  else if (classtype.equalsIgnoreCase("htmlfile"))
          	  {
                      
                  }                
                else
                {
                   JTextComponent txtb = (JTextComponent)fieldTxts.get(i);// arraylist starts from 0   
                    strFieldsValueArray[u] = txtb.getText();
                }
               
                 
                
           }
         } 
       }//for
       
       boolean toContinue=false;
       boolean hasUniqueFieldEmptyValue=false;
       String fieldsAndCaptionMessage="";
       for(int u = 0 ; u<strFieldsUniqueArray.length;u++ )
       { 
        System.out.println("PanelOneDataOnRecData.checkUniquityOfFields   -------   u:"+u+"   strFieldsValueArray[u]:"+strFieldsValueArray[u]+"    strFieldsValueArray:"+strFieldsValueArray+"      strFieldsCaptionArray[u]:"+strFieldsCaptionArray[u]);
         
         if(strFieldsValueArray[u] != null  && !strFieldsValueArray[u].trim().equalsIgnoreCase(""))  //if null or "" do not add to caption
         {
             //  to get the keys and possibly block the data entry
             if(strFieldsUniqueArray.length==1 )
             {
                strAllFields = strFieldsUniqueArray[u]+" LIKE '"+strFieldsValueArray[u]+"'";
                if(boolIsVisibleArray[u])
                {
                    fieldsAndCaptionMessage = strFieldsCaptionArray[u]+" : "+strFieldsValueArray[u];
                }
             }
             else if (u==0 && strFieldsUniqueArray.length>1 )
             {
                strAllFields =  strFieldsUniqueArray[u]+" LIKE '"+strFieldsValueArray[u]+"'"; 
                if(boolIsVisibleArray[u])
                {
                  fieldsAndCaptionMessage = strFieldsCaptionArray[u]+" : "+strFieldsValueArray[u];
                }
             }
             else if (u>0 && strFieldsUniqueArray.length>1 )
             {
                 if(strAllFields.equalsIgnoreCase(""))
                 {
                   strAllFields =  strFieldsUniqueArray[u]+" LIKE '"+strFieldsValueArray[u]+"'";
                   if(boolIsVisibleArray[u])
                   {
                      fieldsAndCaptionMessage = strFieldsCaptionArray[u]+" : "+strFieldsValueArray[u];                      
                   }
                 }
                 else
                 {
                   strAllFields = strAllFields +" AND "+ strFieldsUniqueArray[u]+" LIKE '"+strFieldsValueArray[u]+"'";
                   if(boolIsVisibleArray[u])
                   {
                      fieldsAndCaptionMessage = fieldsAndCaptionMessage +", "+ strFieldsCaptionArray[u]+" : "+strFieldsValueArray[u];                     
                   }
                 }

             }
             else
             {
               System.out.println("PanelOneDataOnRecData.checkUniquityOfFields NOT DEFINED  ====  ELSE    "+u+"  strFieldsValueArray[i]:'"+strFieldsValueArray[u]+"'    ELSE   "+strAllFields+"  toContinue:"+toContinue+"   hasUniqueFieldEmptyValue:"+hasUniqueFieldEmptyValue);
             }
             


          // strFieldsUniqueArray[u] = entityPanel.getFieldsUnique()[u];
            //System.out.println("PanelOneDataOnRecData.checkUniquityOfFields     fields:"+entityPanel.getFieldsUnique()[u]);
       
       
               // variable dataHasChangedInAField  is set to true from this.hasDataFieldUniqueChanged() which is called when txt gets focus
                if(dataHasChangedInAField)// || !isFieldUniqueEmpty)
                {
                    toContinue=true;
                    hasUniqueFieldEmptyValue=false;
                }
                else
               {
                    toContinue=false;
                    hasUniqueFieldEmptyValue=false;
               }
                 //break;
                
   // System.out.println("PanelOneDataOnRecData.checkUniquityOfFields  ====B  ELSE "+u+"   strFieldsValueArray[i]:'"+strFieldsValueArray[u]+"' ELSE   "+strAllFields +"  toContinue:"+toContinue+"   hasUniqueFieldEmptyValue:"+hasUniqueFieldEmptyValue+"   dataHasChangedInAField:"+dataHasChangedInAField);
         }
         else if (strFieldsValueArray[u] != null && strFieldsValueArray[u].trim().equalsIgnoreCase(""))
         {
            // System.out.println("PanelOneDataOnRecData.checkUniquityOfFields  ====C  ELSE "+u+"   strFieldsValueArray[i]:'"+strFieldsValueArray[u]+"' ELSE   "+strAllFields +"  toContinue:"+toContinue+"   hasUniqueFieldEmptyValue:"+hasUniqueFieldEmptyValue);
             toContinue=true;
             hasUniqueFieldEmptyValue=true;
             break;// used when in a specific uniquefield there is no data
         }
         else
         {
            // System.out.println("PanelOneDataOnRecData.checkUniquityOfFields  ====D  ELSE "+u+"   strFieldsValueArray[i]:'"+strFieldsValueArray[u]+"' ELSE   "+strAllFields +"    toContinue:"+toContinue+"   hasUniqueFieldEmptyValue:"+hasUniqueFieldEmptyValue);
             toContinue=false;
             hasUniqueFieldEmptyValue=false;
           //  break;
         } 

       }// for u
       

         //System.out.println("PanelOneDataOnRecData.checkUniquityOfFields  ======== E =====                 strFieldsValueArray:"+strFieldsValueArray+"   toContinue:"+toContinue+"    isNewRec:"+isNewRec+"      hasUniqueFieldEmptyValue:"+hasUniqueFieldEmptyValue);               
         if(strFieldsValueArray != null &&  toContinue &&   !hasUniqueFieldEmptyValue   ) 
         {
             
             String queryCheck ="SELECT * FROM "+entity+" WHERE "+strAllFields;
    System.out.println("PanelOneDataOnRecData.checkUniquityOfFields      queryCheck:"+queryCheck+"         query:"+query);
          try
          {
            db.retrieveDBDataFromQuery(queryCheck,"PanelOneDataOnRecData.checkUniquityOfFields");
   	    rs=db.getRS();
            //System.out.println("PanelOneDataOnRecData.checkUniquityOfFields readSettingsFromFileOrApp  F      isUniqueWhen:"+isUniqueWhen+"     queryCheck:"+queryCheck);
            if(rs.next())
            {
                       // exists with modifications in DialogDataEditConfig.setEntity and in PanelODORData.checkUniquityOfFields (comments)
                       /*cmbChooseActionDataEntry.addItem("καμία");
                       cmbChooseActionDataEntry.addItem("προειδοποίηση");
                       
        
                       cmbChooseActionSave.addItem("καμία");
                       cmbChooseActionSave.addItem("προειδοποίηση");
                       cmbChooseActionSave.addItem("απαγόρευση");*/
                
                    // isUniqueWhen
                    //IS_UNIQUE_WHILE_DATAENTRY = 0;
                     //IS_UNIQUE_BEFORE_SAVING = 1;
                
                 //System.out.println("PanelOneDataOnRecData.checkUniquityOfFields readSettingsFromFileOrApp  C  before   readSettingsFromFileOrApp()    isUniqueWhen:"+isUniqueWhen);
                int intSettings = readSettingsFromFileOrApp(isUniqueWhen);
                int intUniquityMessages = -1;
                if(intSettings == 0)
                {
                    // do nothing
                    intUniquityMessages=0;
                     
                }
                else if (intSettings  == 1)// notify
                {                              //1 no, 0 yes ;
                    intUniquityMessages = utilsGui.showConfirmYesOrNo(this,"Έχει βρεθεί εγγραφή με ίδια στοιχεία.  "+fieldsAndCaptionMessage+" \n Θέλετε να συνεχίσετε;");  
                    
                }
                else if (intSettings  == 2)// restrict
                {
                     utilsGui.showMessageError(this,"Απαγορεύεται να συνεχίσετε γιατί έχει βρεθεί εγγραφη με ίδια στοιχεία. \n "+fieldsAndCaptionMessage);
                     intUniquityMessages=1;
                      
                }
                else
                {
                    System.out.println("error   PanelOneDataOnRecData.checkUniquityOfFields     UNKNOWN   intSettings:"+intSettings+"   strAllFields:"+fieldsAndCaptionMessage);
                }
                
             //System.out.println("PanelOneDataOnRecData.checkUniquityOfFields readSettingsFromFileOrApp  D  isUniqueWhen:"+isUniqueWhen);     
                if(intUniquityMessages==0) //1 no, 0 yes ;
                {
                    ret=true;
                }
                else if(intUniquityMessages==1)
                {
                   ret = false;    
                }
                else
                {
                    ret = false;
                }
                //}
           }
           else
           {
               ret = true;
               System.out.println("PanelODORData.checkUniquityOfFields   false");
           }                
            //rs.beforeFirst();
          
          }
          catch(SQLException e)
          {
            System.out.println("error  PanelOneDataOnRecData.checkUniquityOfFields "+e.getMessage());
            e.printStackTrace();
            
          }
          finally
          {
              closeDB();
          }
         }// if not null
         else
         {
             ret=true;
         }

      }
      else
      {
          ret=true;
      }
        
        closeDB();
            
         
         return ret;
   }   

   /*
   *
   *  // variable dataHasChangedInAField  is set to true from this.hasDataFieldUniqueChanged() which is called when txt gets focus
   */
   
 private void hasDataFieldUniqueChanged(int noOfFieldName)
 {
   if(entityPanel.getFieldsUnique()!=null)  
   {
     String[] strFieldsUniqueArray = new String[entityPanel.getFieldsUnique().length];
    strFieldsUniqueArray =  entityPanel.getFieldsUnique();
       for(int u = 0 ; u<strFieldsUniqueArray.length;u++ )
       { 

          String fieldName = "";
         // String colCaption="";
         // String fieldValue = "";
         // boolean isFieldUniqueEmpty = false;
        // for(int i = 0;i<dbFieldsInGroupOfPanels.length;i++)
        // {
            //colCaption =   dbFieldsInGroupOfPanels[i].getCaption();
           fieldName = dbFieldsInGroupOfPanels[noOfFieldName].getDbField();
           
           if(strFieldsUniqueArray[u].equalsIgnoreCase(fieldName))
           {
               JTextComponent txtbd = (JTextComponent)fieldTxts.get(noOfFieldName);// arraylist starts from 0
               //System.out.println("PanelOneDataOnRecData.hasDataFieldUniqueChanged ==============     i:"+i+"    u:"+u+"     fieldNameFinal:"+fieldName+"   "+txtbd.getText() ); 
               //strFieldsCaptionArray[u] = colCaption;
                
                 //dataHasChangedInAField = false;

                 txtbd.getDocument().addDocumentListener( new DocumentListener()
                 {
                    @Override
                    public void changedUpdate(DocumentEvent e) {
                       // System.out.println("PanelOneDataOnRecData.checkUniquityOfFields ---addDocumentListener A");
                        setUpdated();
                    }
                    @Override
                    public void removeUpdate(DocumentEvent e) {
                      // System.out.println("PanelOneDataOnRecData.checkUniquityOfFields ---addDocumentListener B");
                       setUpdated();
                    }
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                     //System.out.println("PanelOneDataOnRecData.checkUniquityOfFields ---addDocumentListener C");
                        setUpdated();
                   }
                    
                    private void setUpdated()
                    {
                        
                        dataHasChangedInAField=true;
                        //System.out.println("PanelOneDataOnRecData.hasDataFieldUniqueChanged --------------=====------------     fieldNameFinal:"+fieldNameFinal+"   uFinal:"+uFinal+"   iFinal:"+iFinal+"    dataHasChangedInAField:"+dataHasChangedInAField);
                    }
                    
                 });
                 //isFieldUniqueEmpty = txtbd.getText().trim().equalsIgnoreCase("");
                 //if(dataHasChangedInAField)
                 //System.out.println("PanelOneDataOnRecData.hasDataFieldUniqueChanged --------------O------------  "+u+"   "+i+"    "+txtbd.getText()+"   fieldName:"+fieldName+"="+strFieldsUniqueArray[u]+"         dataHasChangedInAField:"+ dataHasChangedInAField);    
           }
        // } // for i 
       }// for u
   }// entityPanel.getFieldsUnique()
 }

   
 /*
    *  //reading from file or from EntityData,  exists with modifications in DialogDataEditConfig.readSettingsFromFileOrApp and in PanelODORData.readSettingsFromFileOrApp
    */
    private int readSettingsFromFileOrApp(int isUniqueWhen)
    {
      int ret=-1;
   XMLReader reader = new XMLReader();
   String[] tagElements ={"name"};
   String[] tagElementsType ={"String"};    	
    	
     if(reader.checkIfElementExists(filePrefsUniqueFields, "DataEditUniqueFields",tagElements,tagElementsType,entity))
     {
                    //IS_UNIQUE_WHILE_DATAENTRY = 0;
                     //IS_UNIQUE_BEFORE_SAVING = 1;
       
         if(isUniqueWhen==IS_UNIQUE_WHILE_DATAENTRY)
         {// load file for actionWhileDataEntry ------------------------------------
             String[] tagElements1 ={"name","actionWhileDataEntry"};
             String[] tagElementsType1 ={"String", "String"}; 
             if(entityPanel.getUniqueWhileDataentryEditable())
             {
                 int  actionWhileDataEntry = utilsPanelReport.loadDataFromXmlFileRetInt(filePrefsUniqueFields, "DataEditUniqueFields",tagElements1,tagElementsType1,1,entity); 
                 ret =actionWhileDataEntry;     
             }
             else
             {
              
             }
         }
         else if(isUniqueWhen==IS_UNIQUE_BEFORE_SAVING)
         {
     	// load file for actionBeforeSaving ------------------------------------
              String[] tagElements2 ={"name","actionBeforeSaving"};
              String[] tagElementsType2 ={"String", "String"};  
              if(entityPanel.getUniqueBeforeSaveEditable())
              {          
                 int  actionBeforeSaving = utilsPanelReport.loadDataFromXmlFileRetInt(filePrefsUniqueFields, "DataEditUniqueFields",tagElements2,tagElementsType2,1,entity); 
                 ret = actionBeforeSaving;
              }
              else
              {
             
              }
         }
         else
         {
             System.out.println("error  PanelOneDataOnRecData.checkUniquityOfFields ELSE A   isUniqueWhen:"+isUniqueWhen);
         }
     }
     else
     {//get prefs from app   
         if(isUniqueWhen==IS_UNIQUE_WHILE_DATAENTRY)
         {
         
          ret=entityPanel.getUniqueWhileDataentry(); 
         }
         else if(isUniqueWhen==IS_UNIQUE_BEFORE_SAVING)
         {         
          ret = entityPanel.getUniqueBeforeSave();
         }
         else
         {
             System.out.println("error  PanelOneDataOnRecData.checkUniquityOfFields ELSE B   isUniqueWhen:"+isUniqueWhen);
         }
     }
     
     return ret;
     
    }
   
    
    
    private void setValuesInFieldsFromCalculationButton(ResultSet rsDocument,int i, String colName, String columnClass,String fieldNamePreffix, String[] arrayFieldAndValue)throws SQLException
    {
        
                       for(int f = 0; f<arrayFieldAndValue.length;f++)
                      {
                        String strField = rsDocument.getString(arrayFieldAndValue[f]);
                       // String strValue = rsDocument.getString(arrayFieldAndValue[1]);
                        
 //System.out.println("PanelODORecData.calculationFromToolBarButton      i:"+i+"  f:"+f+"     colName:"+ colName+"="+strValue);

                    // int fromToInstances=1;// from to like date                        
                       if(colName.equalsIgnoreCase(fieldNamePreffix+strField))
                       {
                            String strValue = rsDocument.getString(arrayFieldAndValue[f+1]);
     System.out.println("PanelODORecData.calculationFromToolBarButton      i:"+i+"  f:"+f+"     colName:"+ colName+"="+utilsDouble.getDoubleReading(strValue, true)+"   arrayFieldAndValue.length:"+arrayFieldAndValue.length);
                             JTextComponent txb = (JTextComponent) fieldTxts.get(i);
                              if(columnClass.equalsIgnoreCase("java.lang.Double"))
                             {
                                   txb.requestFocus();
              	                   txb.setText(utilsDouble.getDoubleReading(strValue, true));         
                             }
                             else
                             {
                                 txb.requestFocus();
                                 txb.setText(strValue);
                             }
                              JTextComponent txbLast = (JTextComponent) fieldTxts.get(dbFieldsInGroupOfPanels.length-1);
                              txbLast.requestFocus();
                              
                       }
                      }
        
        
        
        
        
        
    }
    
    
    /*
    * calculation from PanelOneDataOneRec toolbar button 'calculation'
    */
    public void calculationFromToolBarButton(int intPanel, PanelDataFilter pnlDataFilter, boolean checkIsCanceled, EntityCalculate entityCalculate)
    {
       

          String[] sqlQueryCalcArray  = entityCalculate.getQueryArray();
          
         
      int fromToInstances=1;
              for (int i = 0; i < dbFieldsInGroupOfPanels.length; i++)//  i = fieldTxts
              {

               String colCaption =   dbFieldsInGroupOfPanels[i].getCaption();
          String colName =   dbFieldsInGroupOfPanels[i].getDbField();
          String colTableName =   dbFieldsInGroupOfPanels[i].getTableName();
          int columnWidth = dbFieldsInGroupOfPanels[i].getColWidth();
      	 
          String columnClass = dbFieldsInGroupOfPanels[i].getColClassName();  
          //System.out.println("panelODORData.showRow  col ("+col+")  className"+className+"    colTableName:"+colTableName+" .  colName:"+colName+"  ==  fieldName:"+fieldName+"    f+strField:"+fieldNamePreffix+strField);             
                      
                if(checkIsCanceled)// == null || listUncompletedFields.size()==0)     
               {
                    //System.out.println("PanelODORData.calculationFromToolBarButton     checkToClose:"+checkIsCanceled);
                    
               }
               else
               {         
                        String filterCaption = pnlDataFilter.getFilterCaption(0);
                        String filterVariableType = pnlDataFilter.getFilterVariableType(0);
                        String tb1Text = pnlDataFilter.getFilterValue(0);
                        String tb2Text = pnlDataFilter.getFilterValueTwo(0);
                        
                       if(columnClass.toLowerCase().indexOf(filterVariableType.toLowerCase())!=-1)// if has the same class type
                       {
                      //System.out.println("PanelODORData.calculationFromToolBarButton  TRUE colCaption:"+colCaption+"   filterCaption:"+filterCaption+"   className:"+className+"  filterVariableType:"+filterVariableType+"   tb1Text:"+tb1Text +"tb2Text:"+tb2Text+"  fromToInstances:"+fromToInstances);    
                      
                       JTextBoxWithEditButtons txb = (JTextBoxWithEditButtons) fieldTxts.get(i);
                       //txb.setEmptyDate();
                       txb.setText(tb1Text);
                       
                       
                       if(fromToInstances>1)
                      {
                        JTextBoxWithEditButtons txb2 = (JTextBoxWithEditButtons) fieldTxts.get(i);
                        //txb2.setEmptyDate();
                        txb2.setText(tb2Text);
                        
                       }
                       
                       fromToInstances++;
                       }
                       else
                       {
                           //System.out.println("PanelODORData.calculationFromToolBarButton  colCaption:"+colCaption+"   filterCaption:"+filterCaption+"   className:"+className+"  filterVariableType:"+filterVariableType+"   tb1Text:"+tb1Text +"tb2Text:"+tb2Text+"  fromToInstances:"+fromToInstances);    
                       }    
               }
            //---------------------do the dates finish-------------------------
               /*  if(columnClass.equalsIgnoreCase("htmlfile"))// like old html vatdoc
                 {*/
               if(!columnClass.equalsIgnoreCase("table"))// calculation of ie myf
               {
               
          if( sqlQueryCalcArray.length==1)
          {

                //checkIsCanceled = dlgFilter.getIsCancelClicked();
               if(checkIsCanceled)// == null || listUncompletedFields.size()==0)     
               {
                    //System.out.println("PanelODORData.calculationFromToolBarButton     checkToClose:"+checkIsCanceled);
                    
               }
               else
               {                
               
             String fieldNamePreffix = entityCalculate.getPrefixOfFields();//"f" ;
             
             String[] arrayFieldAndValue1 = entityCalculate.getArrayFieldAndValue1();//{"sxaccount.vatDocCode","sumpre"};
             String[] arrayFieldAndValue2 = entityCalculate.getArrayFieldAndValue2();//{"sxaccount.vatDocCodeVat","sumvat"}; 
 
               
               String sqlQuery =  sqlQueryCalcArray[0];
               String qr =   pnlDataFilter.getSubquery(sqlQuery/*,0*/);// int reportgroup
               
                     db.retrieveDBDataFromQuery(qr, "PanelODORecData.calculationFromToolBarButton.");
                     ResultSet rsDocument = db.getRS();                
                    try
                    {
                    // System.out.println("PanelODORData.calculationFromToolBarButton            qr:"+qr);   
              //for (int i = 0; i < listDocFieldNames.size(); i++)//  i = fieldTxts
              while(rsDocument.next())
              {

                       setValuesInFieldsFromCalculationButton( rsDocument, i, colName,  columnClass, fieldNamePreffix, arrayFieldAndValue1); 
                       setValuesInFieldsFromCalculationButton( rsDocument, i, colName,  columnClass, fieldNamePreffix, arrayFieldAndValue2); 
    
              }

                    }
                    catch(SQLException e)
                    {
                        System.out.println("PanelODORecData.calculationFromToolBarButton.    qr:"+qr+"    "+e.getMessage());
                        e.printStackTrace();
                    } 
                    finally
                    {
                        closeDB(); 
                    }   
               }
                   
          }// if sqlQueryCalcArray               
          else
          {
              // System.out.println("PanelODORecData.calculationFromToolBarButton.   colName:"+colName+" columnClass:"+columnClass+"    sqlQueryCalcArray.length:"+sqlQueryCalcArray.length);
          }
               }
               else if(columnClass.equalsIgnoreCase("table"))// calculation of ie myf
               {

         String[] sqlQueryTableCalcArray  = entityCalculate.getQueryArray();
         String sqlQueryTableCalc = "";
           
         if(intPanel == 0)
         {
             sqlQueryTableCalc = sqlQueryTableCalcArray[0];
         }
         else
         {
             sqlQueryTableCalc = sqlQueryTableCalcArray[intPanel-1];
         }

                //checkIsCanceled = dlgFilter.getIsCancelClicked();
               if(checkIsCanceled)// == null || listUncompletedFields.size()==0)     
               {
                    //System.out.println("PanelODORData.calculationFromToolBarButton     checkToClose:"+checkIsCanceled);
                    
               }
               else
               {          

               String q =   pnlDataFilter.getSubquery(sqlQueryTableCalc/*,0*/);// int reportgroup

                                 
                    PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(i);
                   
                     TableModelResultSet tableModelResultSet = pnlODMRData.getTableModelResultSet();
             
                     db.retrieveDBDataFromQuery(q, "PanelODORecData.calculationFromToolBarButton table"); 
                     
                    try
                    {
                     ResultSet rsMyf = db.getRS();
                     ResultSetMetaData rsmdMyf = db.getRSMetaData();
                     int intMyfRecCount = db.getRecordCount();                   
                     int intMyfColCount = rsmdMyf.getColumnCount();
                             
        //   System.out.println("-- PanelODORecData.calculationFromToolBarButton table  "+i+"   intMyfColCount:"+ intMyfColCount+"    intMyfRecCount:"+intMyfRecCount+"    tableCount:"+tableCount);
                     
                       String[] strValues = new String[intMyfColCount];

                       //rsMyf.first(); // NOT
                       String[] columnName = new String[intMyfColCount];
                      tableModelResultSet.deleteAllTableRows();
                       int r =0;
                        while(rsMyf.next())
                        {
                            for(int m=1;m<=intMyfColCount;m++)
                            {
                             //System.out.println("   m:"+m+"    "+rsMyf.getString(m));
                             columnName[m-1]  = rsmdMyf.getColumnLabel(m) ;// dbFieldsInGroupOfPanels[i].getDbChildFields()[m-1].getCaption();//.getDbField();
                         //    System.out.println("PanelODORecData.calculationFromToolBarButton table m:"+m+"   "+(m-1)+"  colName[m-1]:"+colName[m-1]);
                             strValues[m-1] = rsMyf.getString(columnName[m-1]);
                             rsMyf.getType();
                             //System.out.println("PanelODORecData.calculationFromToolBarButton table "+(m-1)+"  colName[m-1]:"+colName[m-1]+"    strValues[m-1]:"+strValues[m-1]+"   type:"+rsMyf.getType()      );
                          //   intColumns[m-1]=m-1+5;
                            }
            //              System.out.println("PanelODORecData.calculationFromToolBarButton table A  r:"+r+"   colName:"+colName[r]+"   strValues:"+strValues[r]);
                         pnlODMRData.addNewRowIfThereIsnt(true);
                         
                         //pnlODMRData.setTableValuesAtRow(strValues, r, colName,dbFieldsInGroupOfPanels);
            //            System.out.println("PanelODORecData.calculationFromToolBarButton table  B  r:"+r+"   colName:"+colName[r]+"   strValues:"+strValues[r]);
                         tableModelResultSet.setValuesAtRow(strValues,r,columnName);
                          
                         r++;
                       }
                       // tableModelResultSet.setValuesAtRow[];
         //              calculateSumFields();
                    }
                    catch (SQLException e)
                    {
                        System.out.println("error PanelODORecData.calculationFromToolBarButton table "+ e.getMessage());
                        e.printStackTrace();
                    }
                    finally
                    {
                        closeDB(); 
                    }
                    //  //System.out.println("PanelODORData.calculationFromToolBarButton   table   ELSE name: "+columnDbName+" "+columnClass);
               }  

                 }//   table
                 else  //
                 {      // not table
                 
                     //System.out.println("PanelODORData.calculationFromToolBarButton: ELSE class:  "+columnClass+"  "+columnDbName);
                 }
               //closeDB(); 
//            }
           //} 
 //             }// for
               } // checkIsCanceled else
    }


    

   private boolean rowInsertHtmlFormElements(Database dbTransaction,String pkFromOnePanelForHtmlForm)  throws SQLException 
   {
       
 
       
       boolean ret = false;
            for (int i = 0; i < dbFieldsInGroupOfPanels.length; i++)//  i = fieldTxts
            {
                
                 String columnLabel = fieldsTranslation[i]; //get colunm name
                 String columnDbName = fields[i];
                 //System.out.println("panelODORData.setEntity col:");
                 
                 int columnWidth = colWidth[i];//get column width
                 //columnWidth=columnWidth+2;
                 
                 String columnClass = colClassName[i]; 
                
                
             if(columnClass.equalsIgnoreCase("htmlfile"))
             {
                 
              
                 
                // System.out.println("PanelHtmlBrowser is called");
                     PanelHtmlBrowser panelHtmlBrowser = (PanelHtmlBrowser)fieldTxts.get(i);
                     
                //     panelHtmlBrowser.getAllTextFromFields(periodiki);
                    /* for(int f =0; f<strFieldsNValuesAdditional.length; f++)
                     {
                         panelHtmlBrowser.setTextToField(strFieldsNValuesAdditional[f],strFieldsNValuesAdditionalValue[f]); 
                     }*/       
                     if(panelHtmlBrowser.isPageLoaded())
                     {
                    for(int d=0;d<listFields.size();d++)
                    {
                     // System.out.println( "PanelODORData.getFormElements  list size:"+listHtmlFieldsAndValues.size() +"          "+listHtmlFieldsAndValues.get(d).toString());  
                    
//                       panelHtmlBrowser.calculateTextFromField(listFields.get(d).toString());
                    }
                     }
                    
    try
    {
        TimeUnit.SECONDS.sleep(1);
    }
    catch(InterruptedException ie)
    {
        ie.printStackTrace();
    }                   
    
                    String subQueryFieldNames  ="";
                    String subQueryFieldValues="";   
          
                    EntityDBFields[] childFields = dbFieldsInGroupOfPanels[i].getDbChildFields();
                    String fieldChildTableName = dbFieldsInGroupOfPanels[i].getChildTable();
                     listFields = panelHtmlBrowser.getFieldsArrayList();
                    ArrayList listFieldValues = panelHtmlBrowser.getFieldValuesArrayList();
                   // System.out.println( "PanelODORData.rowInsertHtmlFormElements   -   list size:"+listFieldValues.size());
                    //for(int f = 0;f<listHtmlFields.size();f++)

                    for(int f = 0;f<childFields.length;f++)
                    {
                        
                          String fieldDBName = childFields[f].getDbField();
                          
                          System.out.println("PanelODORData.rowInsertHtmlFormElements      f"+f+"   fieldDBName:"+fieldDBName+"      childFields.length:"+childFields.length);
                          int fieldTypeFromParentTable = childFields[f].getPrimaryKeyIntegerAutoInc();
                          
                          
                          //String fieldTableName = childFields[f].getTableName();
                          //String fieldClassName = childFields[f].getColClassName();
                          String strDefaultValue = childFields[f].getDefaultValue();
                           if(fieldTypeFromParentTable == FIELD_PRIMARY_KEY_AUTOINC)
                           {
                               subQueryFieldNames = subQueryFieldNames+" "+fieldDBName+", ";
                               subQueryFieldValues = subQueryFieldValues + "0"+", ";                                 
                           }
                           else if(fieldTypeFromParentTable == FIELD_PRIMARY_KEY_FROM_PARENTTABLE)
                           {
                               subQueryFieldNames = subQueryFieldNames+" "+fieldDBName+", ";
                               subQueryFieldValues = subQueryFieldValues +" " +pkFromOnePanelForHtmlForm+", ";
                           }
                           else if(strDefaultValue != null && !strDefaultValue.equalsIgnoreCase(""))
                           {
                               
                               subQueryFieldNames = subQueryFieldNames+" "+fieldDBName+", ";
                               subQueryFieldValues = subQueryFieldValues + strDefaultValue+", ";                               
                                 
                           }
                           else
                           {
                                 if(fieldDBName.equalsIgnoreCase(strReadFieldWhere))
                                 {
                                //      subQueryFieldNames = subQueryFieldNames+" "+fieldDBName+", ";
                                //      subQueryFieldValues = subQueryFieldValues + listFields.get(d)+", ";
                                         
                                   
                                 }
                                 else if(fieldDBName.equalsIgnoreCase(strReadFieldValue))
                                 {
                                //      subQueryFieldNames = subQueryFieldNames+" "+fieldDBName+", ";
                                //      subQueryFieldValues = subQueryFieldValues + listFieldValues.get(d)+", ";
                                     
                                 }
                                 else
                                 {
                                     System.out.println("PanelODORData.rowInsertHtmlFormElements   ERROR   ELSE    f"+f+"   fieldDBName:"+fieldDBName);
                                 }
                           }
                    }
                    
                    
                               for(int d=0;d<listFields.size();d++)
                               {
                                //System.out.println("PanelODORData.rowInsertHtmlFormElements         d"+d+"      f"+f+"   fieldDBName:"+fieldDBName+"      listFields.get(d):"+listFields.get(d)+"       listFieldValues.get(d):"+listFieldValues.get(d));
                                 
                               String insertQuery = "INSERT INTO "+fieldChildTableName+" ( "+subQueryFieldNames+" "+strReadFieldWhere+", "+strReadFieldValue+" ) VALUES ( "+subQueryFieldValues+" '"+listFields.get(d)+"', '"+listFieldValues.get(d)+"' )";                               
                               if (VariablesGlobal.globalShowSQLEdit)
                               { System.out.println("PanelODORData.rowInsertHtmlFormElements          insertQuery:"+insertQuery);}  
                                        
                    
                    
                    
                               //for(int d=0;d<listFields.size();d++)
                               //{                            
                              /* String insertQuery = "INSERT INTO "+fieldChildTableName+" ( "+subQueryFieldNames+" ) VALUES ( "+subQueryFieldValues+" )";                               
                               if (VariablesGlobal.globalShowSQLEdit)
                               { System.out.println("PanelODORData.rowInsertHtmlFormElements          insertQuery:"+insertQuery);}                             
              */
                              //}          
                     int intRet =  dbTransaction.transactionUpdateQuery(insertQuery,"PanelODORData.rowInsertHtmlFormElements",showDialogOnError);
                      //System.out.println("PanelODORData.rowInsertHtmlFormElements         intRet:"+intRet+"       from query query:"+insertQuery);
                     if(intRet==1)
                     {
                         ret=true;
                     }
                     else
                     {
                         System.out.println("PanelODORData.rowInsertHtmlFormElements ERROR      intRet:"+intRet+"    query: "+insertQuery);
                         ret=false;
                         break; 
                     }
                               }
                         
                    
           // //return 1 there is already one record with the same keys                         
             }// htmlfile
            }// 
           closeDB();
            return  ret;
   }
    
   
   /*
   *  // bellow is needed so it will not calculate inside calculateSumFields
   */
   public void calculateAgainDbFields()
   {
    /*  for(int i = 0;i<dbFieldsInGroupOfPanels.length;i++) 
      {       
          
       dbFieldsCalculateSet(dbFieldsInGroupOfPanels,i);
      }
     */  
   }

 
   
   /*
    * 
    * called by PanelODOR.rowSave
    */   
   public int rowSaveAll(int intPanelODORData,boolean isNewRecIn, Database dbTransaction) throws SQLException
   {
         
       calculateSumFields();
       calculateAgainDbFields();
      
       for(int i = 0;i<dbFieldsInGroupOfPanels.length;i++)
      //for(int i = 0;i<dbFieldsAll.length;i++)  
      {    
                                   checkFieldsIfThenElse(i,CHECK_ON_INSERT_OR_ON_UPDATE);//columnDbNameFinal
      }       
         //System.out.println("panelOneDataOneRecData.rowSaveAll   ---  hasDataChanged:"+hasDataChanged+"   isNewRecIn:"+isNewRecIn);

   	 final int YES = 0;
    	 final int NO = 1;
   	 int ret = 0;
      
      // pkFromOnePanelForTables used only when isNewRecIn==true
       //System.out.println("PanelODORData.rowSave isNewRecIn:"+isNewRecIn+"   dbTransaction:"+dbTransaction); 
   	 if (isNewRecIn==true)
   	 {
             // also called below in getPkOfAutoIncInsertedRecord
              pkFromOnePanelForTables = utilsPanelReport.getNoOfPKAutoIncOfNewRecord(false,dbFieldsAll,entity,null,null,0);// used for table, last 
   	 	  if(intPanelODORData==0)  // also is 1 for second panel (ie in second panel of form )
                  {
                      
                      System.out.println("PanelODORData.rowSaveAll INSERT - - - -- - -  -  pkFromOnePanelForTables:"+pkFromOnePanelForTables+"   isNewRec:"+isNewRec+"     title:"+title+"    intPanelODORData:"+intPanelODORData);
                       rowInsertAll(dbTransaction,isNewRecIn,pkFromOnePanelForTables);
   	 	      
                       isNewRec=false;     
                       
                  }
                  else  // String pkFromOnePanelForTables also for second panel (ie in second panel of form )
                  {
                      System.out.println("PanelODORData.rowSaveAll   UPDATE    isNewRecIn:"+isNewRecIn+"     title:"+title+"    pkFromOnePanelForTables:"+pkFromOnePanelForTables+"    intPanelODORData:"+intPanelODORData);
                      ret = rowUpdateAll(dbTransaction,isNewRecIn,pkFromOnePanelForTables);
                      
                  }
                  
                    
         }
        else
        {  
               System.out.println("PanelODORData.rowSaveAll UPDATE    isNewRecIn:"+isNewRecIn+"     pkFromOnePanelForTables:"+pkFromOnePanelForTables+"    title:"+title);
                ret = rowUpdateAll(dbTransaction,isNewRecIn,pkFromOnePanelForTables);
                 
       
         
        }
   
          hasDataChanged=false;    
         System.out.println("panelOneDataOneRecData.rowSaveAll   ---  hasDataChanged:"+hasDataChanged);
         
         
            return ret;
   }

   /*
   *
   * must be called after this.rowSaveAll
   */
   public String getPkOfJustInsertedRecord() // after called once, get empty string
   {

       return  pkFromOnePanelForTables;
   }
   

   
   
   /*
   
       called by  rowInsertAll
   */
   private int rowInsert(Database dbTransaction,String pkFromOnePanelForTables)  throws SQLException 
   {
   	int ret=0;

       

   	     String subqueryfieldTxts="(";   	     
   	     String subqueryValues="(";
   	     

         int colCount =dbFieldsInGroupOfPanels.length;   	     
   	      //for (int i = 1; i <= rsmd.getColumnCount(); i++)//  i = fieldTxts
          for (int i = 0; i < colCount; i++)//  i = fieldTxts        
          {   
           	  String columnDbName = dbFieldsInGroupOfPanels[i].getDbField();//fields[i-1];//get colunm name 
           	  int columnWidth = dbFieldsInGroupOfPanels[i].getColWidth();  
           	  String classtype = dbFieldsInGroupOfPanels[i].getColClassName();

                
 //                String  returnPkFromOnePanelForTables = utilsPanelReport.getNoOfPKAutoIncOfNewRecord(false,dbFieldsAll,entity);
                  
                  
          	  	 JTextComponent tb=null;
                  String fieldValue = "";
                // System.out.println("PanelODORData.rowInsert  "+i+"  "+classtype+"  "+columnDbName);
          	 if (classtype.equalsIgnoreCase("java.sql.Date") || classtype.equalsIgnoreCase("java.lang.Date"))
          	 {
          	 	JTextBoxWithEditButtons textEditFormatedDate = new JTextBoxWithEditButtons();
                        //textEditFormatedDate.setYearEnforce(yearEnforce);

             	        textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(i);
                        tb = textEditFormatedDate.getTextComp();
                        fieldValue = tb.getText();

          	 }
          	 else if (classtype.equalsIgnoreCase("java.lang.Boolean"))
          	 {
              	   JCheckBox chk = (JCheckBox) fieldTxts.get(i);//i-1);
              	   chk.setOpaque(false);
              	   boolean sel =chk.isSelected(); 
              	   if(sel)
              	   {
              	   	fieldValue="1";
              	   }
              	   else 
              	   {
              	   	 fieldValue="0";
              	   }
              	   
              	           	  	
          	 }            	 
                 else if (classtype.equalsIgnoreCase("table"))
                 {
                     fieldValue="";
           	          //     PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(i);                       
                           //    pnlODMRData.setPrimKeyValueInTableModelResultSet(returnPkFromOnePanelForTables);
                 } 
                 else if (classtype.equalsIgnoreCase("htmlfile"))
                 {
                     fieldValue="";
           	          //     PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(i);                       
                           //    pnlODMRData.setPrimKeyValueInTableModelResultSet(returnPkFromOnePanelForTables);
                 }                  
                 else if(dbFieldsInGroupOfPanels[i].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)           
                 {
                   LookupTableConstantsMgt lookUpTableConstants = new LookupTableConstantsMgt();
                    EntityLookupTableConstantsData[] elutcData = lookUpTableConstants.getEntityLookupTableConstantsData(dbFieldsInGroupOfPanels[i].getLookupEntityName());
                    //dbFieldsInGroupOfPanels[i].getLookupEntityName();
                    JComboBox cmbBox =  (JComboBox) fieldTxts.get(i);
                    fieldValue = elutcData[cmbBox.getSelectedIndex()].getPk();         
                              
                   
                 } 
                 else if(dbFieldsInGroupOfPanels[i].getPrimaryKeyIntegerAutoInc() == FIELD_PRIMARY_KEY_AUTOINC)    //rsmd.isAutoIncrement(i+1))
                 {     //get from utils(get last from db)       
                    
	            fieldValue = pkFromOnePanelForTables;   	           
                 }   
                 else    
                 {
                     
                  if( classtype.equalsIgnoreCase("java.lang.String") && columnWidth>COLWIDTH_FOR_BIGTEXTBOX)
                  {

                 	//JPanel panelTextArea =new JPanel(new BorderLayout());
                  	//panelTextArea.setOpaque(false);

                      
                        if(columnWidth>COLWIDTH_FOR_HTML) // like printform html // look also above
                        {                 
                 
                           PanelHtmlEditor txtArea = (PanelHtmlEditor)fieldTxts.get(i);//this is for html
                           fieldValue = txtArea.getText();
                        } 
                        else
                        {
                           tb = (JTextComponent) fieldTxts.get(i);
	                   fieldValue = tb.getText().trim();                            
                        }
                 

                        //}
                  }
                  else
                  {                       
                    tb = (JTextComponent) fieldTxts.get(i);
	            fieldValue = tb.getText().trim();
                  }
                 }         	  	

                  if (classtype.equalsIgnoreCase("table"))
                  {
                      //subqueryfieldTxts = subqueryfieldTxts;
                  }
                 else if (classtype.equalsIgnoreCase("htmlfile"))
                 {
                     //rowInsertHtmlFormElements(dbTransaction,pkFromOnePanelForTables);
                 }                   
                  else
                  {
                          if(columnDbName.equalsIgnoreCase("hidden"))// hidden must be java.lang.String
                          {
                              
                          }
          	           else
          	           {
                               subqueryfieldTxts = subqueryfieldTxts+columnDbName;
                           }
                  }
                  
                  
 //         	      String whereValueName = getWhereValueNameThatMatchesColumn(columnDbName,"global");
        //System.out.println("PaneloDORData.rowInsert "+i+" "+columnDbName+" "+classtype+" "+fieldValue+" whereValueName:"+whereValueName);  	      
          	      if (classtype.equalsIgnoreCase("java.lang.String"))
          	      {
                          
                    if(!fieldValue.equalsIgnoreCase(""))
                    { 
                    	   	       
                          if(columnDbName.equalsIgnoreCase("hidden")) // hidden must be java.lang.String
                          {
                              
                          }
          	           else
          	           {
          	      	         subqueryValues = subqueryValues+"'"+fieldValue+"'";
          	           }
                   }
                   else
          	   {    
                          if(columnDbName.equalsIgnoreCase("hidden")) // hidden must be java.lang.String
                          {
                     
                          }
                          else
                          {
                              subqueryValues = subqueryValues+" null ";
                          }
          	   }
                    
                    
                    System.out.println(">>>>>>>>>>>>>>>>>>>A  panelOneDataOneRecData.rowInsert for:"+classtype+" "+subqueryValues); 
                    
          	      }
          	      else if (classtype.equalsIgnoreCase("java.lang.Integer"))
          	      {
          	      	//System.out.println("panelOneDataOneRecData.rowInsert "+columnLabel+" "+whereValueName+" '"+fieldValue+"'");
                  if(!fieldValue.equalsIgnoreCase(""))
                    {          	      	
      /*      	           if(!whereValueName.equalsIgnoreCase("-"))
          	           {
          	              //System.out.println("panelOneDataOneRecData.rowInsert "+columnLabel+" "+whereValueName+" "+getValueForVariable(whereValueName));
          	             subqueryValues = subqueryValues+"'"+getValueForVariable(whereValueName)+"'";
          	           }
          	           else
          	           {*/
          	         	  subqueryValues = subqueryValues+"'"+fieldValue+"'";
         // 	           }
                    }
                    else
                    {  // since it is a new record value is nothing
  /*        	           if(!whereValueName.equalsIgnoreCase("-"))
          	           {
          	              //System.out.println("panelOneDataOneRecData.rowInsert "+columnLabel+" "+whereValueName+" "+getValueForVariable(whereValueName));
          	             subqueryValues = subqueryValues+"'"+getValueForVariable(whereValueName)+"'";
          	           }
          	           else
          	           {             */       	
                    	subqueryValues = subqueryValues+" null ";
 //         	           }
                    }           	         
          	      }
          	     else if (classtype.equalsIgnoreCase("java.lang.Double"))
          	  {
                 if(!fieldValue.equalsIgnoreCase(""))
                 {

  /*        	       if(!whereValueName.equalsIgnoreCase("-"))
          	       {
          	       	  //System.out.println("panelOneDataOneRecData.rowInsert "+columnLabel+" "+getValueForVariable(whereValueName));
          	         subqueryValues = subqueryValues+getValueForVariable(whereValueName)+"";
          	       }
          	       else
          	       {*/
                           if(!fieldValue.equalsIgnoreCase("0"))
                           {  
                           System.out.println("PanelODORData.rowInsert:"+fieldValue);
                           }
                	   subqueryValues = subqueryValues+"'"+utilsDouble.getDoubleSaving(fieldValue.trim())+"'";
   //                }
                 }
                 else
                 {
                 	subqueryValues = subqueryValues+"'0.0'";
                 }  
                 
          	  }
          	  else if (classtype.equalsIgnoreCase("java.sql.Date") || classtype.equalsIgnoreCase("java.lang.Date"))
          	  {
                 if(!fieldValue.equalsIgnoreCase(""))
                 {
  /*        	       if(!whereValueName.equalsIgnoreCase("-"))
          	       {
          	       	  //System.out.println("panelOneDataOneRecData.updateRow "+columnLabel+" "+getValueForVariable(whereValueName));
          	         subqueryValues = subqueryValues+getValueForVariable(whereValueName)+"";
          	       }
          	       else
          	       {*/
                	   subqueryValues = subqueryValues+"'"+utilsDate.reformatDateStringToSaveToDB(fieldValue.trim())+"'";
 //                  }
                 }
                 else
                 {
                 	subqueryValues = subqueryValues+utilsDate.getNullDate();
                 }
                 
          	  }  
          	  else if (classtype.equalsIgnoreCase("java.lang.Boolean"))
          	  {
          	  	 if(!fieldValue.equalsIgnoreCase(""))
                      {
/*          	       if(!whereValueName.equalsIgnoreCase("-"))
          	       {
          	       	  //System.out.println("panelOneDataOneRecData.updateRow "+columnLabel+" "+getValueForVariable(whereValueName));
          	         subqueryValues = subqueryValues+getValueForVariable(whereValueName)+"'";
          	       }
          	       else
          	       {*/
                	   subqueryValues = subqueryValues+ "'"+fieldValue.trim()+"'";
 //                  }
                     }
                     else
                     {
                 	subqueryValues = subqueryValues+ "0";
                     }  
          	  }
                  else if(classtype.equalsIgnoreCase("table"))
                  {
                              
                  }
                 else if (classtype.equalsIgnoreCase("htmlfile"))
                 {
                     
                 }                   
                 else if(classtype.equalsIgnoreCase("java.awt.image.BufferedImage"))
                 {
                             
                 }                  
          	  else
          	  { 
          	      
          	  	   System.out.println("error panelOneDataOneRecData.rowInsert >>>>>> classtype "+classtype+" not supported");
          	  }
          	      
          	      //if (i < rsmd.getColumnCount() && rsmd.getColumnCount()>1) 
                      //System.out.println("PanelODORData.rowInsert i="+i+" dbFieldsInGroupOfPanels.length="+dbFieldsInGroupOfPanels.length);
                      /*if(dbFieldsInGroupOfPanels.length dbFieldsInGroupOfPanels[i+1].getColClassName().equalsIgnoreCase("table"))
                      {
                          
                      }*/
                      
                      if (i < dbFieldsInGroupOfPanels.length-1 && dbFieldsInGroupOfPanels.length>1 && !dbFieldsInGroupOfPanels[i+1].getColClassName().equalsIgnoreCase("table") && !dbFieldsInGroupOfPanels[i+1].getColClassName().equalsIgnoreCase("htmlfile")) 
          	      // add comma but not on the last field(before where), also not when there is only one field
          	      { 
                          
                          if(columnDbName.equalsIgnoreCase("hidden")) // hidden must be java.lang.String
                          {
                     
                          }
                          else
                          {
          	         subqueryfieldTxts = subqueryfieldTxts+", ";   
          	         subqueryValues = subqueryValues+", ";                               
                          }
  
          	      }
                      else
                      {
                         
                      }

          	  //}// else if 
                 if(columnDbName.equalsIgnoreCase(primKeyDb))
                 {
                     
                     primKeyValue=fieldValue.trim();
                 }
               
                 
           	  

                
                           
               EntityDBFields dbField = dbFieldsInGroupOfPanels[i];
               String fieldName = dbField.getDbField();
              // boolean isCalculated = dbField.getFollowingCalculationOrUpdate();// followingCalculationOrUpdateIn: boolean if true then calculation or if false then update
               EntityDBFieldsCalculation[] fieldsCalculationUpdate = dbField.getFieldsCalculationUpdate();
               String val="";
        if(dbField!=null && fieldsCalculationUpdate!=null)//!isCalculated)
        {
 /*       try
        {*/
           for(int fc=0; fc<fieldsCalculationUpdate.length;fc++)
           {
              
        	//int[] textsCategoryInput = fieldsCalculation[fc].getCalculationInputCategoryFields();
                int[] textsInput = fieldsCalculationUpdate[fc].getCalculationInputFields();
        	String calculation =  fieldsCalculationUpdate[fc].getCalculation();
                //int calculateCategoryField =  fieldsCalculation[fc].getWhenSetThenCalculateCategoryField();
                int calculateField =  fieldsCalculationUpdate[fc].getWhenSetThenCalculateField();  
                String [] textString = new String[textsInput.length];
                
                for(int c=0;c<textsInput.length;c++)
                {
                       if(dbFieldsInGroupOfPanels[textsInput[c]].getLookupType()==LOOKUPTYPE_ONLYONE_THISFIELD || dbFieldsInGroupOfPanels[textsInput[c]].getLookupType()==LOOKUPTYPE_NOLOOKUP)
                       {
                          JTextComponent tbToGet = (JTextComponent)fieldTxts.get(textsInput[c]);
                          textString[c] = tbToGet.getText();               
                       }
                       else if (dbFieldsInGroupOfPanels[textsInput[c]].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)
                       {  // showRow
                         LookupTableConstantsMgt lookUpTableConstants = new LookupTableConstantsMgt();
                         EntityLookupTableConstantsData[] elutcData = lookUpTableConstants.getEntityLookupTableConstantsData(dbFieldsInGroupOfPanels[textsInput[c]].getLookupEntityName());
              
              	            JComboBox cb = (JComboBox) fieldTxts.get(textsInput[c]);
                            
                            /*for(int e=0 ;e<elutcData.length;e++)
                            {
                                if()
                                {*/
                          textString[c] = elutcData[cb.getSelectedIndex()].getPk();
                               // }
                          System.out.println("PanelOneDataOneRecData.rowInsert sel index:"+cb.getSelectedIndex()+"  textsInput[c]:"+textString[c]);
                            
  
                      }
                      else 
                      {
                            System.out.println("  error PanelOneDataOneRecData.rowInsert    textsInput[c]:"+textsInput[c]+"  ("+c+")   UNKNOWN   name:"+dbFieldsInGroupOfPanels[textsInput[c]].getDbField()+"   getLookupType:"+dbFieldsInGroupOfPanels[textsInput[c]].getLookupType());
                      }                
                }
               

                
           }           
 /*        }//try
         catch ( SQLException sqlex)
         {
             System.out.println("error:PanelODORData.dbFieldsCalculateSet  "+sqlex.getMessage());
         }    */           
        }   
               

     
               //System.out.println("PanelODORData.rowInsert  ("+i+")  "+classtype+"   "+columnDbName+" "+primKeyDb+"   fieldValue.trim():"+fieldValue.trim()+"  primKeyValue:"+primKeyValue);        
          } //for
          subqueryfieldTxts=subqueryfieldTxts+")"; 
   	      subqueryValues=subqueryValues+")";
   	     
          String insertQuery = "INSERT INTO "+entity+" "+subqueryfieldTxts+" VALUES "+ subqueryValues;
          
          if (VariablesGlobal.globalShowSQLEdit)
          { 
              System.out.println("PanelODORData.rowInsert ------ insertQuery: "+insertQuery);
          }
           
           // //return 1 there is already one record with the same keys
          ret = dbTransaction.transactionUpdateQuery(insertQuery,"PanelODORData.rowInsert",showDialogOnError);
         // ret = db.updateQuery(insertQuery,"PanelODORData.rowInsert",showDialogOnError);
           
         boolean retBool = this.rowInsertAndUpdateImage(dbTransaction);
           //db.releaseConnectionRs();
           //db.releaseConnectionRsmd();
            hasDataChanged=false;//if save pressed before press ok then when pressed ok not save again
   // //return 1 there is already one record with the same keys
     return ret;
   }   

   
   /*
   
   *     java.awt.image.BufferedImage
   */
   private boolean rowInsertAndUpdateImage(Database dbTransaction/*,JLabel lblWithImageString pkFromOnePanelForTables,boolean isNewRecIn,boolean isFromCopyOfRecord*/)  throws SQLException 
   {
       boolean ret = false;

       String subquerySet = "";
     DataBufferByte data;
        
        
               if (dbFieldsInGroupOfPanels==null)
               {
                    System.out.println("PanelOneDataOneRecData.rowInsertAndUpdateImage  dbFieldsInGroupOfPanels="+dbFieldsInGroupOfPanels);
               }
               int colCount =dbFieldsInGroupOfPanels.length;
              ArrayList listRetSavedAll = new ArrayList();
              
       for (int i = 0; i < colCount; i++)//  i = fieldTxts
       { 
                 String columnName = fields[i]; //get colunm name  	           	
	         String classtype = dbFieldsInGroupOfPanels[i].getColClassName();  // if integer then not add ' and ' between values
     
                if(classtype.equalsIgnoreCase("java.awt.image.BufferedImage"))
                {
                    //System.out.println("  --------  PanelODORData.rowUpdateTables    table("+i+")     isNewRecIn:"+isNewRecIn);
                    
              JLabel lblWithImage = (JLabel) fieldTxts.get(i);
              String labelText = lblWithImage.getText()+"";
                subquerySet = entity+"."+columnName+" =  LOAD_FILE('"+labelText+"') ";
              
              
//                     subquerySet = entity+"."+columnName+" = ? ";
              
          String subqueryWhere = ""; // for each primary key
          
             utilsPanelReport.retrievePrimKeyValueForOnePK( query, selectedRow, null,dbFieldsAll,true,/*primKeyIn,intColumnOfDescriptionIn,
             sql2WhereField, sql2WhereValue,*/ entity, /*tableModelReadOnly,*/ primKeyDb);    
                       
             String[] primKeys = utilsPanelReport.getPrimKeys();
             //String[] primKeysCaption = utilsPanelReport.getPrimKeysCaption();
            //System.out.println("PanelOneDataOneRecData.rowUpdate '"+entity+"' selectedRow:"+selectedRow+"  primKeys:"+primKeys.length); 
             int primKeysCount = primKeys.length;
             String[] primKeysValue = utilsPanelReport.getPrimKeysValue();              
      
              
      //    databaseTableMeta.retrievePrimKs(entity); // first retrieve them
          for (int k = 0; k< primKeysCount; k++) // i=0 and i< because arraylist starts from 0
          {             
                //System.out.println("PanelOneDataOneRecData.rowUpdate '"+entity+"' "+primKeys[i]+"="+primKeysValue[i]); 
               
              subqueryWhere = subqueryWhere+"("+primKeys[k]+" LIKE '"+primKeysValue[k]+"')";
                  
                  
          	  if (k < primKeys.length-1 && primKeys.length>1) 
          	  // add AND but not on the last field(before where), also not when there is only one PK . -1 because arraylist starts from 0
          	  { subqueryWhere = subqueryWhere+" AND  ";   }              
   
          }              

              //System.out.println("PanelODORData.rowInsertAndUpdateImage      labelText:"+labelText);
              
        if( subquerySet!=null && !subquerySet.trim().equalsIgnoreCase("") && filePath!=null && !filePath.trim().equalsIgnoreCase("") &&  !labelText.trim().equalsIgnoreCase(imageEmpty)) // subquerySet when field class is 'table' it is empty, so go to else
        {
          String updateQuery = "UPDATE "+entity+
          " SET " + subquerySet+
          " WHERE "+subqueryWhere ; 
           
          if (VariablesGlobal.globalShowSQLEdit)
          { System.out.println("PanelODORData.rowInsertAndUpdateImage updateQuery: "+updateQuery); }
                 
          
          
          
          
         PreparedStatement psmnt = null;          
         try
         {
            Connection connection =  dbTransaction.getConnection();
            psmnt = connection.prepareStatement(updateQuery);             
             
            //JLabel lblImage = (JLabel) fieldTxts.get(i);
            //    http://stackoverflow.com/questions/20155300/image-saving-in-database-from-jlabel
            //    http://www.codejava.net/coding/upload-files-to-database-servlet-jsp-mysql
            //    http://www.mysqltutorial.org/mysql-jdbc-blob
             
             //lblWithImage.getIcon()
//             String path = lblWithImage.getText();
 //           FileInputStream fileInputStream=new FileInputStream(path);// filePath is string
            //System.out.println("PanelODORData.rowInsertAndUpdateImage        path:"+path);
            //byte bytes[]=new byte[fileInputStream.available()]; 
             
            //byte[] extractBytes = data.getData();
//            File file = new File(path);
            //psmnt.setBytes(1, bytes);
//            psmnt.setBinaryStream(1,fileInputStream,(int)file.length());
            System.out.println("PanelODORData.rowInsertAndUpdateImage     labelText:"+labelText);          
          
          
          
            int s = psmnt.executeUpdate();
            if (s > 0) {
                System.out.println("-------------------image saved successfully !-----------------------------");
                     ret = true;    
                //JOptionPane.showMessageDialog(this, "Uploaded successfully !");
            } else {
                System.out.println("---------------- unsucessfull to upload image. ----------------------------");
                     ret = false;  
                     break;
            }
            connection.close();
             psmnt.close();
        } // catch if found any exception during rum time.
        catch (SQLException sqlex)
        {
            System.out.println("PanelODORData.rowInsertAndUpdateImage   sqlex   Error :" + sqlex);
            utilsGui.showMessageError(this,"PanelODORData.rowInsertAndUpdateImage   sqlex  \n Error :" + sqlex);
            // sqlex.printStackTrace();
            //break; // not because it may has more image labels
        }         
        catch (Exception ex)
        {
            System.out.println("PanelODORData.rowInsertAndUpdateImage      Error :" + ex);
            utilsGui.showMessageError(this,"PanelODORData.rowInsertAndUpdateImage     \n Error :" + ex);
             //ex.printStackTrace();
            //break; // not because it may has more image labels
        }             
          
          
          
          
                     
         /*            
                     
                     
                   // Declare statement.
         PreparedStatement psmnt = null;          
         try
         {
            Connection connection =  dbTransaction.getConnection();
            psmnt = connection.prepareStatement(updateQuery);             
             
            //JLabel lblImage = (JLabel) fieldTxts.get(i);
            //    http://stackoverflow.com/questions/20155300/image-saving-in-database-from-jlabel
            //    http://www.codejava.net/coding/upload-files-to-database-servlet-jsp-mysql
            //    http://www.mysqltutorial.org/mysql-jdbc-blob
             
             //lblWithImage.getIcon()
             String path = lblWithImage.getText();
            FileInputStream fileInputStream=new FileInputStream(path);// filePath is string
            //System.out.println("PanelODORData.rowInsertAndUpdateImage        path:"+path);
            //byte bytes[]=new byte[fileInputStream.available()]; 
             
            //byte[] extractBytes = data.getData();
            File file = new File(path);
            //psmnt.setBytes(1, bytes);
            psmnt.setBinaryStream(1,fileInputStream,(int)file.length());
            System.out.println("PanelODORData.rowInsertAndUpdateImage     path:"+path);
            // executeUpdate() method execute specified sql query. Here this query 
            //insert data and image from specified address. 
            int s = psmnt.executeUpdate();
            if (s > 0) {
                System.out.println("-------------------image saved successfully !-----------------------------");
                     ret = true;    
                //JOptionPane.showMessageDialog(this, "Uploaded successfully !");
            } else {
                System.out.println("---------------- unsucessfull to upload image. ----------------------------");
                     ret = false;  
                     break;
            }
            connection.close();
            psmnt.close();
        } // catch if found any exception during rum time.
        catch (SQLException sqlex)
        {
            System.out.println("PanelODORData.rowInsertAndUpdateImage   sqlex   Error :" + sqlex);
            utilsGui.showMessageError(this,"PanelODORData.rowInsertAndUpdateImage   sqlex  \n Error :" + sqlex);
            // sqlex.printStackTrace();
            //break; // not because it may has more image labels
        }         
        catch (Exception ex)
        {
            System.out.println("PanelODORData.rowInsertAndUpdateImage      Error :" + ex);
            utilsGui.showMessageError(this,"PanelODORData.rowInsertAndUpdateImage     \n Error :" + ex);
             //ex.printStackTrace();
            //break; // not because it may has more image labels
        }                     
               */ 
        }//  if not null    
        else // if null
        {
                 ret = true;    // do not bother user since there is no image
        }
                 
                    
                    System.out.println("  o==o PanelOneDataOneRecData.rowInsertAndUpdateImage   columnName:"+columnName+"   classtype:"+classtype+"   ret:"+ret);
                    
                    //listRetSavedAll.add(ret);
                }  
                else   // if not "java.awt.image.BufferedImage")
                {
                    ret=true;
                }

     }//  for               
 
   
        
       
       
       return ret;
   }
   
   
   
   
   
   
   private boolean rowInsertFieldSpecific(Database dbTransaction,String pkFromOnePanelForTables)  throws SQLException 
   {
       boolean ret = true;
       
         int colCount =dbFieldsInGroupOfPanels.length;   	     
   	      //for (int i = 1; i <= rsmd.getColumnCount(); i++)//  i = fieldTxts
          for (int i = 0; i < colCount; i++)//  i = fieldTxts        
          {   
           	  String columnDbName = dbFieldsInGroupOfPanels[i].getDbField();//fields[i-1];//get colunm name 
           	  int columnWidth = dbFieldsInGroupOfPanels[i].getColWidth();  
           	  String classtype = dbFieldsInGroupOfPanels[i].getColClassName();                           
               EntityDBFields dbField = dbFieldsInGroupOfPanels[i];
               String fieldName = dbField.getDbField();
               //boolean isCalculated = dbField.getFollowingCalculationOrUpdate();// followingCalculationOrUpdateIn: boolean if true then calculation or if false then update
               EntityDBFieldsCalculation[] fieldsCalculationUpdate = dbField.getFieldsCalculationUpdate();
               String val="";
        if(dbField!=null && fieldsCalculationUpdate!=null )//  && !isCalculated)
        {       
           for(int fc=0; fc<fieldsCalculationUpdate.length;fc++)
           {
              
        	//int[] textsCategoryInput = fieldsCalculation[fc].getCalculationInputCategoryFields();
                int[] textsInput = fieldsCalculationUpdate[fc].getCalculationInputFields();
        	String calculation =  fieldsCalculationUpdate[fc].getCalculation();
                //int calculateCategoryField =  fieldsCalculation[fc].getWhenSetThenCalculateCategoryField();
                int calculateField =  fieldsCalculationUpdate[fc].getWhenSetThenCalculateField();  
                String [] textString = new String[textsInput.length];
                
                
                
                
                for(int c=0;c<textsInput.length;c++)
                {
                       if(dbFieldsInGroupOfPanels[textsInput[c]].getLookupType()==LOOKUPTYPE_ONLYONE_THISFIELD || dbFieldsInGroupOfPanels[textsInput[c]].getLookupType()==LOOKUPTYPE_NOLOOKUP)
                       {
                          JTextComponent tbToGet = (JTextComponent)fieldTxts.get(textsInput[c]);
                          textString[c] = tbToGet.getText();               
                       }
                       else if (dbFieldsInGroupOfPanels[textsInput[c]].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)
                       {  // showRow
                         LookupTableConstantsMgt lookUpTableConstants = new LookupTableConstantsMgt();
                         EntityLookupTableConstantsData[] elutcData = lookUpTableConstants.getEntityLookupTableConstantsData(dbFieldsInGroupOfPanels[textsInput[c]].getLookupEntityName());
              
              	            JComboBox cb = (JComboBox) fieldTxts.get(textsInput[c]);
                            
                            /*for(int e=0 ;e<elutcData.length;e++)
                            {
                                if()
                                {*/
                          textString[c] = elutcData[cb.getSelectedIndex()].getPk();
                               // }
                         // System.out.println("PanelOneDataOneRecData.rowInsertFieldSpecific sel index:"+cb.getSelectedIndex()+"   c:"+c+"  textsInput[c]:"+textString[c]);
                            
  
                      }
                      else 
                      {
                            System.out.println("  error PanelOneDataOneRecData.rowInsertFieldSpecific    textsInput[c]:"+textsInput[c]+"  ("+c+")   UNKNOWN   name:"+dbFieldsInGroupOfPanels[textsInput[c]].getDbField()+"   getLookupType:"+dbFieldsInGroupOfPanels[textsInput[c]].getLookupType());
                      }                
                }                
                
                
          //------------------------------- more insert(field specific)      
                int indexOfHashChar = calculation.indexOf("#");
             //System.out.println("PanelODORData.dbFieldsCalculateSet  FIELDSCALCULATION_CATEGORY_SAME      fc:"+fc+"    calculation:"+calculation);        
               for(int s=0;s<textString.length;s++)
               {   
                    //if does not have an empty string inside textString then continue
                   if(!textString[s].equalsIgnoreCase(""))
                   {
                       //System.out.println("   if  textString  s:"+s+"  textString:"+textString[s]);
                        if(indexOfHashChar!=-1)
                       { 
                          calculation = utilsString.replaceTextOfAStringWithText("#", calculation, textString, null);
                          //System.out.println("PanelODORData.dbFieldsCalculateSet   s:"+s+"   indexOfHashChar:"+indexOfHashChar+"      textString.length:"+textString.length);
                       }

                    /*  db.retrieveDBDataFromQuery(calculation,"PanelODORData.rowInsertFieldSpecific  ");
   	              rs=db.getRS();
  	          
            
                     if (rs!=null && rs.first())
                     {
                        rs = db.retrieveRow(rs, 1);// go to the only row  
                       //System.out.println(PanelODORData.dbFieldsCalculateSet   calculation "+calculation);
                        val = rs.getString(1);// get field data	         	                            
                     }*/                
                
                   }
               }
                
                String sqlUpdate = calculation+pkFromOnePanelForTables;//ex saleheader.saleHeaderId LIKE ");
                
               
                
                
           if (VariablesGlobal.globalShowSQLEdit)
          { System.out.println("PanelODORData.rowInsertFieldSpecific     --- (field:"+i+")   sqlUpdate: "+sqlUpdate);}
           
          // System.out.println("PanelODORData.rowInsertFieldSpecific     --- (field:"+i+")   sqlUpdate: "+sqlUpdate);

           // //return 1 there is already one record with the same keys
              int intret = dbTransaction.transactionUpdateQuery(sqlUpdate,"PanelODORData.rowInsertFieldSpecific",showDialogOnError);
            if(intret>0)
            {
                ret = true;
            }
            else
            {
                ret = false;
                break;
            }
              
           
                
           }//for fieldscalculation
        }//if
        }// for col
       
          
          
          return ret;
   }
   
   private void rowInsertAll(Database dbTransaction,boolean isNewRecIn,String pkFromOnePanelForTables) throws SQLException
   {


       
       
        //boolean boolContinue=false;
          int ret=0;
      //  PanelOneDataManyRecData panelODMRData =null;
          //String  pkFromOnePanelForTables = utilsPanelReport.getNoOfPKAutoIncOfNewRecord(false,dbFieldsAll,entity);
              ret = this.rowInsert(dbTransaction,pkFromOnePanelForTables);
              if (ret>0)
              { 
                 //if(this.rowInsertAndUpdateImage   is inside rowInsert
                 if(this.rowInsertFieldSpecific(dbTransaction, pkFromOnePanelForTables))
                 {    
                  
                 if(rowInsertTables(dbTransaction,isNewRecIn,pkFromOnePanelForTables))
                 {
                     if(rowInsertHtmlFormElements(dbTransaction,pkFromOnePanelForTables))
                     {

                       if(rowInsertAdditional(dbTransaction,pkFromOnePanelForTables))// like update of last document no in actiontype
                       {
                            ret=1;
                       }
                       else
                       {
                           ret=0;
                       }
                     }
                     else
                     {
                       if(rowInsertAdditional(dbTransaction,pkFromOnePanelForTables))// like update of last document no in actiontype, or bridge
                       {
                            ret=1;
                       }
                       else
                       {
                           ret=0;
                       }                         
                     }
                 }
                 else
                 {
                     ret=0;
                 }
                 }
                 else
                 {
                     ret =0;
                 }
              }
 
             
              if (ret==0)
              {
                  System.out.println("PanelOneDataOneRecData.rowInsertAll panelOneDataOneRecData not saved ret:"+ret); 
                        utilsGui.showMessageError(this,"PanelOneDataOneRecData.rowInsertAll panelOneDataOneRecData not saved");
              }
    // return ret;           
   }   
   
   /*
   
       called by  rowInsertAll
   */   
   
   private boolean rowInsertTables(Database dbTransaction,boolean isNewRecIn,String pkFromOnePanelForTables)throws SQLException
   {
       boolean ret=false;
       //System.out.println("PanelOneDataOneRecData.rowInsertTables  ===  isNewRecIn:"+isNewRecIn+"  pkFromOnePanelForTables:"+pkFromOnePanelForTables);    
       ret = rowUpdateTables(dbTransaction,isNewRecIn,pkFromOnePanelForTables); 
       return ret;
   }
   
   private boolean rowInsertAdditional(Database dbTransaction,String pkeyFromOnePanelForTablesIn) throws SQLException
   {
       boolean ret=false;
       
         if(updateAdditional!=null)
         {
             String  pkFromOnePanelOfAdditionalBridgeTable="";
                     for(int u=0;u<updateAdditional.length;u++)
                     {
                         String strIsEnabled= UPDATEADDITIONAL_NOT_ENABLED;
                         String strQuery = updateAdditional[u].getIfIsEnabledReturn1();
                         //String strQuery ="";
                         String[] strField = updateAdditional[u].getIfIsEnabledReturn1FieldsToReplace();
                         if(strField!=null && strQuery.length()>1)
                         {
                           //fieldData = updateAdditional[u].getIfIsEnabledReturn1FieldsToReplace();
                             //String[] strField = updateAdditional[u].getIfIsEnabledReturn1FieldsToReplace();
                          
                            String[] fieldData = new String[strField.length];
                            for(int f=0;f<strField.length;f++)
                            {           	     
                                 for (int i = 0; i < dbFieldsInGroupOfPanels.length; i++)//  i = fieldTxts        
                                 {   
           	                 String columnDbName = dbFieldsInGroupOfPanels[i].getDbField();  //get colunm name 
                             
                                 if(columnDbName.equalsIgnoreCase(strField[f]))
                                 {
                                    String colClass = dbFieldsInGroupOfPanels[i].getColClassName();
                               //System.out.println("  PanelODORData.rowInsertAdditional columnDbName:"+columnDbName+"   f"+f+"   i:"+i+"   colClass:"+colClass+"    strField[f]"+strField[f] );
                                  if(colClass.equalsIgnoreCase("java.sql.Date"))
                                  {
                                      
                                       JTextBoxWithEditButtons textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(i);
                                       JTextComponent ta = (JTextComponent)textEditFormatedDate.getTextComp();
                                       fieldData[f] = " '"+utilsDate.reformatDateStringToSaveToDB(ta.getText().trim())+"' ";
                                      
                                  }
                                  else if(colClass.equalsIgnoreCase("java.lang.Double"))
                                  {
                                      
                                         JTextComponent tb = (JTextComponent) fieldTxts.get(i); 
                                         String valdouble = utilsDouble.getDoubleSaving(tb.getText().trim());
	                                 fieldData[f] = valdouble;
                                      
                                  }                                  
                                  else
                                  {
                                     JTextComponent tb = (JTextComponent) fieldTxts.get(i); 
	                             fieldData[f] = tb.getText().trim();
                                  }
                                 }
                                 else
                                 {
                                     //System.out.println(" ==++++===== PanelODORData.rowInsertAdditional columnDbName:"+columnDbName+"   strField[f]"+strField[f] );
                                 }

                                 }
                            }                         
                           strQuery = utilsString.replaceTextOfAStringWithText("#", strQuery, fieldData, null);
                         }
                         
                         if(strQuery.length()>1)
                         {
                             db.getConnection();
                             System.out.println("PanelODORData.rowInsertAdditional :: updateAdditional query:"+strQuery);
                             db.retrieveDBDataFromQuery(strQuery, "PanelODORData.rowInsertAdditional");
                             ResultSet rsIf = db.getRS();
                             rsIf.first();
                             strIsEnabled= rsIf.getString(1);
                             
                             this.closeDB();
                         }
                         else
                         {
                             strIsEnabled=updateAdditional[u].getIfIsEnabledReturn1();
                         }
                         
  
                         if(strIsEnabled.equalsIgnoreCase(UPDATEADDITIONAL_ENABLED))// if updateAdditional is enabled
                         {
                                   String entityBridge = updateAdditional[u].getUpdateAdditionalBridgeEntity();
                            EntityDBFields[] dbFieldsBridge = updateAdditional[u].getUpdateAdditionalBridgeDbFields();    
                            
                            
                            if(entityBridge!= null)
                            {
                                  pkFromOnePanelOfAdditionalBridgeTable = utilsPanelReport.getNoOfPKAutoIncOfNewRecord(false,dbFieldsBridge,entityBridge,null,null,0);// used for table, last
                               //System.out.println("    rowInsertAdditional    pkFromOnePanelOfAdditionalBridgeTable:"+pkFromOnePanelOfAdditionalBridgeTable);
                            }                            
                            
                            
                        if(updateAdditional[u].getUpdateAdditionalWhen()==UPDATE_ON_INSERT_ONLY || updateAdditional[u].getUpdateAdditionalWhen()==UPDATE_ON_BOTH_INSERT_AND_UPDATE )
                        {
                          
                            String queryU = updateAdditional[u].getUpdateAdditionalQuery();
                               int intTable = -1;
                                 for (int i = 0; i < dbFieldsInGroupOfPanels.length; i++)//  i = fieldTxts        
                                 {   
                                    
           	                 String columnDbName = dbFieldsInGroupOfPanels[i].getDbField();  //get colunm name 
                                 String columnClassName =   dbFieldsInGroupOfPanels[i].getColClassName();
                                 String fieldTableOfSource = updateAdditional[u].getUpdateAdditionalFieldTable(); // like salelines
                                 //System.out.println("     rowInsertAdditional   i:"+i+" columnDbName:"+columnDbName+"   fieldTableOfSource:"+fieldTableOfSource);
                                 if(columnDbName.equalsIgnoreCase(fieldTableOfSource))
                                 {
                                     intTable = i;
                                     break;
                                 }
                                 
                                 }
                                 
                                   if(intTable!=-1)
                                   {
                                        PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(intTable);
                                        TableModelResultSet tableModelResultSet = pnlODMRData.getTableModelResultSet();
                                        int rowcount = tableModelResultSet.getRowCount();
                                      
                                    for(int r=0;r<rowcount;r++)
                                    {
                                        
                                         /*String groupFieldValue = updateAdditional[u].getUpdateAdditionalFieldTableGroupValue();
                                         db.getConnection();
                                         db.retrieveDBDataFromQuery(getQueryForAdditionalInsertInsideTable(u,groupFieldValue,pkFromOnePanelOfAdditionalBridgeTable,tableModelResultSet,r), "PanelODORData.rowInsertAdditional");
                                         db.retrieveRow(rs,1);
                                         String v = rs.getString(1);*/
                                         
                                         System.out.println("PanelODORData.rowInsertAdditional OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO   r:"+r);
                                        
                                        
                                         String queryAdd =  getQueryForAdditionalInsertInsideTable(u,queryU,pkFromOnePanelOfAdditionalBridgeTable,tableModelResultSet,r);
                                     this.closeDB();
                                         if(rowInsertAdditionalToDb(dbTransaction,u, queryAdd))
                                         {ret=true;}
                                         else
                                         { 
                                            // break;
                                         }
                                    }
                                    
                                   }
                                   else
                                   {
                                      queryU =  getQueryForAdditionalInsert(u,queryU,pkFromOnePanelOfAdditionalBridgeTable,pkeyFromOnePanelForTablesIn); // the last is for the saleheaderid     
                                      if(rowInsertAdditionalToDb(dbTransaction,u, queryU))
                                      {ret=true;}
                                      else
                                      { 
                                         // break;
                                      }                                     
                                   }      
                      
                        }
                         }// if is enabled
                     } //for
             
         }
         else// updateAdditional is null, has no update query entity
         {
             ret=true;
         }
                     

      return ret;               
   }   
   
   
   
   private boolean rowInsertAdditionalToDb( Database dbTransaction,int u, String queryU)  throws SQLException
   {
       
       boolean ret = false;
       
                         

                      if (VariablesGlobal.globalShowSQLEdit)
                      { 
                         System.out.println("PanelODORData.rowInsertAdditional        ++       u:"+u+"      queryU:"+queryU);
                     }
           
                      // //return 1 there is already one record with the same keys
                      int intRet = dbTransaction.transactionUpdateQuery(queryU+"","PanelODORData.rowInsertAdditionalToDb",showDialogOnError);   
                      //int intRet = db.updateQuery(queryU,"PanelODORData.rowInsertAdditional",showDialogOnError);   
                      
                      
                     if(intRet==1)
                     {
                         ret=true;
                     }
                     else
                     {
                         System.out.println("PanelODORData.rowInsertAdditional ERROR u:"+u+"    intRet:"+intRet+"    queryU: "+queryU);
                         ret=false;
                         
                     }
       
       
       return ret;
   }
   
      private String getQueryForAdditionalInsertInsideTable(int u, String queryU, String  pkFromOnePanelOfAdditionalBridgeTable, TableModelResultSet tableModelResultSet,int r )
   {
       
       
                          if(updateAdditional[u].getUpdateAdditionalQueryFields()!=null)  
                          {                        
                              
                             String[] strField = updateAdditional[u].getUpdateAdditionalQueryFields();
                          
                            String[] fieldData = new String[strField.length];
                            for(int f=0;f<strField.length;f++)
                            {
                                   	     
                                 //for (int i = 0; i < dbFieldsBridgeMany.length; i++)//  i = fieldTxts 
                                for (int c=0;c<tableModelResultSet.getColumnCount();c++)
                                 {   
           	                 String columnDbName = tableModelResultSet.getColumnDBName(c);  //get colunm name 
                              
                                 if(columnDbName.equalsIgnoreCase(strField[f]))
                                 {
                                    String colClass = tableModelResultSet.getColumnClassString(c);
                                
                                  if(colClass.equalsIgnoreCase("java.sql.Date"))
                                  {
                                      
                                       //JTextBoxWithEditButtons textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(i);
                                       //JTextComponent ta = (JTextComponent)textEditFormatedDate.getTextComp();
                                       fieldData[f] = " '"+utilsDate.reformatDateStringToSaveToDB(tableModelResultSet.getValueAt(r, c)+"")+"' ";
                                       
                                      
                                  }
                                  else if(colClass.equalsIgnoreCase("java.lang.Double"))
                                  {
                                      
                                         //JTextComponent tb = (JTextComponent) fieldTxts.get(i); 
                                         String valdouble = utilsDouble.getDoubleSaving(tableModelResultSet.getValueAt(r, c)+"");
	                                 fieldData[f] = valdouble;
                                      
                                  }                                  
                                  else
                                  {
                                     //JTextComponent tb = (JTextComponent) fieldTxts.get(i); 
	                             fieldData[f] = tableModelResultSet.getValueAt(r, c)+"";//tb.getText().trim();
                                  }
                                  
                                  //System.out.println(" PanelODORData.getQueryForAdditionalInsertInsideTable   r:"+r+"   columnDbName:"+columnDbName+"   colClass:"+colClass+"    fieldData[f]:"+fieldData[f] );
                                 }
                                 else
                                 {
                                     //System.out.println(" PanelODORData.rowInsertAdditional columnDbName:"+columnDbName+"   strField[f]"+strField[f] );
                                 }
                           
                                 /*if(dbFieldsBridgeMany[i].getPrimaryKeyIntegerAutoInc() == FIELD_PRIMARY_KEY_AUTOINC)   
                                  {     //get from utils(get last from db)       
                                            //String  returnPkFromOnePanelForTables = utilsPanelReport.getNoOfPKAutoIncOfNewRecord(false,dbFieldsAll,entity);
	                                    fieldData[f] = pkFromOnePanelForTables;   	           
                                  } */                                 

                                 }
                            }
                            
                            queryU = utilsString.replaceTextOfAStringWithText("#", queryU, fieldData, null);
                            queryU = queryU.replaceAll("&a",(r+1)+"");// is for no of row
                            //&@ is fot pk of bridge header, &# is for each row,    // &b is for the header of original id like saleheaderid  
                            queryU = queryU.replaceAll("&@", pkFromOnePanelOfAdditionalBridgeTable);    // is for the bridge pkey id like esoexoheaderid                        

                          }    
                          

                            
                            
                            //System.out.println(" PanelODORData.rowInsertAdditional   r:"+r+"   queryU:"+queryU);
       
       return queryU;
       
   }
   
   
   private String getQueryForAdditionalInsert(int u, String queryU, String  pkFromOnePanelOfAdditionalBridgeTable, String pkeyFromOnePanelForTablesIn)// the last is for the saleheaderid
   {
       
       
                          if(updateAdditional[u].getUpdateAdditionalQueryFields()!=null)  
                          {                        
                              
                             String[] strField = updateAdditional[u].getUpdateAdditionalQueryFields();
                          
                            String[] fieldData = new String[strField.length];
                            for(int f=0;f<strField.length;f++)
                            {           	     
                                 for (int i = 0; i < dbFieldsInGroupOfPanels.length; i++)//  i = fieldTxts        
                                 {   
           	                 String columnDbName = dbFieldsInGroupOfPanels[i].getDbField();  //get colunm name 
                             
                                 if(columnDbName.equalsIgnoreCase(strField[f]))
                                 {
                                    String colClass = dbFieldsInGroupOfPanels[i].getColClassName();
                               //System.out.println("  PanelODORData.rowInsertAdditional columnDbName:"+columnDbName+"   f"+f+"   i:"+i+"   colClass:"+colClass+"    strField[f]"+strField[f] );
                                  if(colClass.equalsIgnoreCase("java.sql.Date"))
                                  {
                                      
                                       JTextBoxWithEditButtons textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(i);
                                       JTextComponent ta = (JTextComponent)textEditFormatedDate.getTextComp();
                                       fieldData[f] = " '"+utilsDate.reformatDateStringToSaveToDB(ta.getText().trim())+"' ";
                                      
                                  }
                                  else if(colClass.equalsIgnoreCase("java.lang.Double"))
                                  {
                                      
                                         JTextComponent tb = (JTextComponent) fieldTxts.get(i); 
                                         String valdouble = utilsDouble.getDoubleSaving(tb.getText().trim());
	                                 fieldData[f] = valdouble;
                                      
                                  }                                  
                                  else
                                  {
                                     JTextComponent tb = (JTextComponent) fieldTxts.get(i); 
	                             fieldData[f] = tb.getText().trim();
                                  }
                                 }
                                 else
                                 {
                                     //System.out.println(" ==++++===== PanelODORData.rowInsertAdditional columnDbName:"+columnDbName+"   strField[f]"+strField[f] );
                                 }
                           
                               /*  if(dbFieldsInGroupOfPanels[i].getPrimaryKeyIntegerAutoInc() == FIELD_PRIMARY_KEY_AUTOINC)   
                                  {     //get from utils(get last from db)       
                                            //String  returnPkFromOnePanelForTables = utilsPanelReport.getNoOfPKAutoIncOfNewRecord(false,dbFieldsAll,entity);
                                      System.out.println(" ======================================================== getQueryForAdditionalInsert    columnDbName:"+columnDbName+"       pkFromOnePanelOfAdditionalBridgeTable:"+pkFromOnePanelOfAdditionalBridgeTable);
	                                    fieldData[f] = pkeyFromOnePanelForTablesIn;   	           
                                  } */                                 
                            
                                 }
                            }
                             queryU = utilsString.replaceTextOfAStringWithText("#", queryU, fieldData, null);
                            
                                queryU = queryU.replaceAll("&b", pkeyFromOnePanelForTablesIn);  // &b is for the header of original id like saleheaderid                            
                            queryU = queryU.replaceAll("&@", pkFromOnePanelOfAdditionalBridgeTable); // is for the bridge pkey id like esoexoheaderid
                          }       
       
       
       return queryU;
       
   }
   
     /*
      *  shows in second txt the value from table
      */
   private String calculateTextForLookupsAfterKeyIsSet(int col, String foreignTable)
   {
	String lookupResult1="-";
                            JTextComponent txtb = (JTextComponent)fieldTxts.get(col);
                            
                           
                            JTextComponent txtb2 = (JTextComponent)fieldTxts2.get(col);// arraylist starts from 0
                            //txtb2.getDocument().addDocumentListener(new DocumentListenerDoNothing());
                            JTextComponent txtb3 = (JTextComponent)fieldTxts3.get(col);// arraylist starts from 0
                            //txtb3.getDocument().addDocumentListener(new DocumentListenerDoNothing());
                            JTextComponent txtb4 = (JTextComponent)fieldTxts4.get(col);// arraylist starts from 0
                           // txtb4.getDocument().addDocumentListener(new DocumentListenerDoNothing());
                            
                           String lookupValue = txtb.getText();
                      //  System.out.println("PanelODORData.calculateTextForLookupsAfterKeyIsSet   =++++++++++++++++++=    ");
                           if (!lookupValue.equalsIgnoreCase("")&&!lookupValue.equalsIgnoreCase(" ")&&!lookupValue.equalsIgnoreCase("  "))
                           {
                  
                  int intFieldToGetTheValue = utilsPanelReport.calculateAllFieldsFromParentDBFieldsForFormVariable1(dbFieldsAll);               
                  String strValueFromField = calculateVarFromPreFieldAndSetGlobal(col);
                  
                String fieldVariableFromPreField = dbFieldsInGroupOfPanels[col].getFormVariableFromField();
                /*String colName = dbFieldsInGroupOfPanels[col].getDbField();
                int intFieldToGetTheValue = utilsPanelReport.calculateAllFieldsFromParentDBFieldsForFormVariable1(dbFieldsAll);  
                String strValueFromField = "";
                if(fieldVariableFromPreField!=null && !fieldVariableFromPreField.equalsIgnoreCase("") )
                {
                            JTextField tbf = (JTextField) fieldTxts.get(intFieldToGetTheValue);
                            strValueFromField= tbf.getText();
                            setVariablesGlobal1(fieldVariableFromPreField,strValueFromField);
                }  
                else
                {
                            
                }*/
                 //System.out.println("ooooooo>>  panelODORData.calculateTextForLookupsAfterKeyIsSet    foreignTable:"+foreignTable+"   col:"+col+"   colName:"+colName+"  fieldVariableFromPreField:"+fieldVariableFromPreField+" =  strValueFromField:"+strValueFromField+"       lookupValue:"+lookupValue);       
                              
                               
                               
                               
                          String subqueryWhereForAPreviousFieldValue = lookUp.getQueryWhereForFormVariable(foreignTable);     
                              // String fieldVariableFromPreField = dbFieldsInGroupOfPanels[col].getFormVariableFromField();
                          //System.out.println("panelODORData.calculateTextForLookupsAfterKeyIsSet    '"+foreignTable+"'   "+lookupValue);
                             lookupResult1 = utilsPanelReport.getLookupValue(entity,foreignTable,lookupValue,1,true,fieldVariableFromPreField,/*formGlobalTableToGet1,formGlobalTableToApply1,*/subqueryWhereForAPreviousFieldValue,entity);// last variable is to filter values from a value in a previous field
     //System.out.println("panelODORData.calculateTextForLookupsAfterKeyIsSet    foreignTable:"+foreignTable+"   lookupValue:"+lookupValue+"   lookupResult1:"+lookupResult1+"  col:"+col+"     fieldVariableFromPreField:"+fieldVariableFromPreField);   
                             
                             String lookupResult2 ="";
                             String lookupResult3 ="";
                             if(lookUp.getLookUpField2Index(foreignTable)!=0)
                             {
                                lookupResult2 = utilsPanelReport.getLookupValue(entity,foreignTable,lookupValue,2,true,fieldVariableFromPreField,/*formGlobalTableToGet1,formGlobalTableToApply1,*/"",entity);// last variable is to filter values from a value in a previous field
                             }

                             if(lookUp.getLookUpField3Index(foreignTable)!=0)
                             {
                                lookupResult3 = utilsPanelReport.getLookupValue(entity,foreignTable,lookupValue,3,true,fieldVariableFromPreField,/*formGlobalTableToGet1,formGlobalTableToApply1,*/"",entity);// last variable is to filter values from a value in a previous field
                             }
                            
                             //  System.out.println("panelODORData.calculateTextForLookupsAfterKeyIsSet  hasDataChanged"+hasDataChanged+" guiLoaded"+guiLoaded+" "+lookupResult1); 
                            if(hasDataChanged)  
                            {
                               if (lookupResult1.equalsIgnoreCase("-") && !lookupValue.equalsIgnoreCase("")) // - because getLookupValue produces - when not have any data
                               {  
                                  // if value not found
                                  //txtb.requestFocus();  // if not correct return to first textfield of lookup
                                  //utilsGui.showMessageError(this,"Key typed not correct. Please retry or delete value.");
                                   
                                   
                                   txtb2.setText(""); 
                                  //txtb3.setText(""); 
                                  //txtb4.setText("");                                   	
                               }
                               else
                               {   
                                  txtb2.setText(lookupResult1);      
                                  txtb3.setText(lookupResult2); 
                                  txtb4.setText(lookupResult3); 
                               }                            	
                            }

                           }
                           else
                           { 
                              
                                txtb2.setText("");
                                txtb3.setText(""); 
                                txtb4.setText("");                                 
                           }   	
   	return lookupResult1;
   	
   }
   
   

   
   
   /*
    *  retrieve record one and many in panelTwoDataOneRec
    *  --------- perhaps not needed  ------------
    */
 //  private void calculateRecordAfterKeySet(int col)
  // {
   
   	/*   	         String columnLabel = fieldsTranslation[col];//rsmd.getColumnLabel(i); //get colunm name
                 String columnDbName = fields[col];


                            JTextComponent tb = (JTextComponent)fieldTxts.get(col);
                            JTextComponent tb2 = (JTextComponent)fieldTxts2.get(col);// arraylist starts from 0
                            JTextComponent tb3 = (JTextComponent)fieldTxts3.get(col);// arraylist starts from 0
                            JTextComponent tb4 = (JTextComponent)fieldTxts4.get(col);// arraylist starts from 0
                            
                            String lookupValue = tb.getText();

                         
                                 //System.out.println("panelODORData.calculateRecordAfterKeySet "+hasDataChanged+" "+getWhereValueNameThatMatchesColumn(columnDbName,null).toUpperCase());
                         if ( countOfUniqueKeys >0 && isMasterUnique )
                         {

                         	int intCol = Integer.parseInt(listUniqueKeysIntOfCols.get(listUniqueKeysIntOfCols.size()-1).toString());
                         	if(col == intCol)
                         	{
                         	
                         	
                           	      //System.out.println("panelODORData.focusLost data has changed isMasterUniqueFin"+isMasterUniqueFin+" hasDataChanged"+hasDataChanged);
                            if(!lookupValue.trim().equalsIgnoreCase(""))
                            {                                     
                                   // System.out.println("panelODORData.calculateRecordAfterKeySet "+hasDataChanged); 
                                     
                           	        if(hasDataChanged)
                           	        {
                           	           //if(isChangeFocus)// only if is change focus of text then get record because creates error when key pressed

   	                                 db.retrieveDBDataFromQuery(query,"PanelOneDataOnRecData.calculateRecordAfterKeySet");
   	                                 rs=db.getRS();
   	                                  
                           	         boolean isNew=isNewRec; // if isNewRec=true showSpecificRow does nothing, so we change it temporary
                           	         isNewRec=false;
                           	           
                           	         //System.out.println("panelODORData.calculateRecordAfterKeySet focusLost isChangeFocus "+isChangeFocus);
                           	         int row = utilsPanelReport.getRowForPrimKey(rs, dbFieldsInGroupOfPanels,primKeyDb,lookupValue);                          	           	
                           	        //System.out.println("---------panelODORData.calculateRecordAfterKeySet   row:"+row+"   for lookupValue:"+lookupValue);   
                   	              	 if(row==0)
   	                                 {  
   	                                 	 //System.out.println("PanelODORData.calculateRecordAfterKeySet row"+row); 	 	
                                         rowNewForFieldsExceptUniqueKeys(isNewRecFromCopy,col);
                                       //  med.retrieveFromOneDataManyRecData(query2,entity2,sql2WhereField,sql2WhereValue,primKeyDb,lookupValue,true,false,true);	
                                       //  med.panelOneDataManyRecNewEmptyRow();
   	                                 }
   	                                 else
   	                                 {
   	                                 	//System.out.println("PanelODORData.calculateRecordAfterKeySet row"+row);
                           	           showSpecificRow(row);
                           	       //    med.retrieveFromOneDataManyRecData(query2,entity2,sql2WhereField,sql2WhereValue,primKeyDb,lookupValue,true,false,true);
   	                                 } 
                           	           
                           	           
                           	           hasDataChanged=false;
                           	           
                           	           primKeyValue = tb.getText();                           	           
                           	           
                           	           closeDB();
                          	            
                           	           //System.out.println("panelODORData.setEntity focusLost after ");
                           	             isNewRec=isNew; // if changed again to true then when inside TDOR makes insert instead of update
                           	           
                           	        }
                           	        else
                           	        {
                           	        	//System.out.println("panelODORData.calculateAllRecordAfterKeySet hasDataChanged"+hasDataChanged);
                           	        }
                                 
                              }
                              else
                              {
                            	 rowNewForFieldsExceptUniqueKeys(isNewRecFromCopy,-1);
                             	// med.retrieveFromOneDataManyRecData(query2,entity2,sql2WhereField,sql2WhereValue,primKeyDb,"0",true,false,true);
                            	
                            	
                              }
                         	}
                         }
                         else if (countOfUniqueKeys==0)
                         {
                         	
                         }
                         else
                         {
                         	System.out.println("  error PanelODORData.calculateRecordAfterKeySet  countOfUniqueKeys "+countOfUniqueKeys+" isMasterUnique "+isMasterUnique);
                         }                        
   	*/
 //  }
   
 
  /* private String getPrimKeyValue(String primKey)
   {
       System.out.println("PanelODORData.getPrimKeyValue IS USED "+isNewRec);
   	    String primKeyValue = "";
        /*     
        //System.out.println("PanelOneDataOneRecData.getPrimKeyValue "+selectedRow+" primKey "+primKey+" primKeyValue "+primKeyValue);
        
        db.retrieveDBDataFromQuery(query,"PanelOneDataOnRecData.getPrimKeyValue");
   	rs=db.getRS();
        rs.absolute(selectedRow);
        //for (int i = 1; i <= rsmd.getColumnCount(); i++)//  i = fieldTxts
        for (int i = 0; i < dbFieldsInGroupOfPanels.length; i++)
        {
          	String columnLabel = fields[i];//rsmd.getColumnLabel(i); //get colunm name  	  
        	if (columnLabel.toUpperCase().equalsIgnoreCase(primKey.toUpperCase()))
        	{
          	     primKeyValue =  rs.getString(i+1);
                     //System.out.println("PanelOneDataOneRecData.getPrimKeyValue ["+i+"]  "+primKey+" = "+primKeyValue);
          	}
        }

        
      }*/

    /*    return primKeyValue;
   }*/

   public void showPrevRow()   
   { 
       try
         {   
             boolean changed = hasDataChanged;
             db.retrievePrevRow(rs);
             showRow(rs);
             hasDataChanged=changed;
         }
         catch(Exception e)
         {  System.out.println("Error  PanelOneDataOneRecData.showPrevRow() " + e.getMessage());
             closeDB(); 
         }
         closeDB();        
   }

   public void showNextRow()   
   { 
       try
         {  
             boolean changed = hasDataChanged;
             rs = db.retrieveNextRow(rs);
             showRow(rs);
             hasDataChanged=changed;
         }
         catch(Exception e)
         {
             System.out.println("Error  PanelOneDataOneRecData.showNextRow() " + e.getMessage());  
             closeDB(); 
         }     
         closeDB();        
   }
   
   
   
   /*
    * 
    * called by PanelODOR.rowSave in order to be passed to setTitle and get the data of a new record
    */
   public String getPrimKeyValue()
   {
       return primKeyValue;
   }
   
   public String getPKeyFromOnePanelForTables()
   {
       return   pkFromOnePanelForTables;
   }      
   /*
   *  called by TableModelResulySet.dbFieldsCalculateSet
   */
   public ArrayList getFieldTxts()
   {
       return fieldTxts;
   }
 
      /*
   *  called by TableModelResulySet.dbFieldsCalculateSet
   */
   public EntityDBFields[] getDbFieldsRootAndChilds()
   {
       return dbFieldsInGroupOfPanels;
   }
   
   
   public void showSpecificRowForPKsAfterANewRecIsSaved(String pkFromOnePanel)// the autoinc pk for new rec
   {
       System.out.println("PanelODORData.showSpecificRowForPKsAfterANewRecIsSaved  +++++++  pkFromOnePanel:"+pkFromOnePanel+"        query:"+query);
   //     retrievePrimKeyValueForOnePK(String queryIn, int selectedTableRow, EntityDBFields[] dbFields,EntityDBFields[] dbFieldsMany, boolean isMany,
   //     /*String primKeyTranslationIn,*/ /*int intColumnOfDescription,*/ /*String[] sql2WhereField, String[] sql2WhereValue,*/ String entity, /*TableModel tableModel,*/
  //      String primKeyDb)       
      
             //int pkIntUnique =  Integer.parseInt(pkFromOnePanel);
       
                 String subqueryWhere = ""; // for each primary key
          
             utilsPanelReport.retrievePrimKeyValueForOnePK( query, 1/*selectedRow*/, null,dbFieldsAll,true,/*primKeyIn,intColumnOfDescriptionIn,
             sql2WhereField, sql2WhereValue,*/ entity, /*tableModelReadOnly,*/ primKeyDb);    
                       
             String[] primKeys = utilsPanelReport.getPrimKeys();
             //String[] primKeysCaption = utilsPanelReport.getPrimKeysCaption();
            //System.out.println("PanelOneDataOneRecData.showSpecificRowForPKs  o-A-o- '"+entity+"' selectedRow:"+selectedRow+"  primKeys:"+primKeys.length+"   primKeyDb:"+primKeyDb); 
             int primKeysCount = primKeys.length;
             String[] primKeysValue = utilsPanelReport.getPrimKeysValue();              
              

      //    databaseTableMeta.retrievePrimKs(entity); // first retrieve them
          for (int i = 0; i< primKeysCount; i++) // i=0 and i< because arraylist starts from 0
          {             
               
              if(primKeys[i].equalsIgnoreCase(primKeyDb))
              {
                  subqueryWhere = subqueryWhere+"("+primKeys[i]+" LIKE '"+pkFromOnePanel+"')";// the autoinc pk
              }
              else
              {
                  subqueryWhere = subqueryWhere+"("+primKeys[i]+" LIKE '"+primKeysValue[i]+"')";
              }  
         
          	  if (i < primKeys.length-1 && primKeys.length>1) 
          	  // add AND but not on the last field(before where), also not when there is only one PK . -1 because arraylist starts from 0
          	  { subqueryWhere = subqueryWhere+" AND  ";   }              
              
              

          }
          String queryWithoutWhere = utilsString.getQueryWithoutWhere(query);
          String q = queryWithoutWhere + " WHERE "+subqueryWhere;
       
          System.out.println("PanelODORData.showSpecificRowForPKsAfterANewRecIsSaved       pkFromOnePanel:"+pkFromOnePanel+"          q:"+q+"           query:"+query);
          isNewRec=false;
          guiLoaded = false;
          showSpecificRow(q);

       
       setDataHasChanged(false);
       
   }
   
   /*
   * also set predefined fields to their values. like dbyearid in templates
   */
    public void setFieldsTemplateZero(String fieldIsTemplate)// zero = no template
    {
        
        
      
        
        
        // for header field
   	      for(int i=0;i<fieldTxts.size();i++)
   	      {

             String colName =  dbFieldsInGroupOfPanels[i].getDbField(); 
             String classtype = dbFieldsInGroupOfPanels[i].getColClassName();  // rsmd.getColumnClassName(i+1);
             int columnWidth = dbFieldsInGroupOfPanels[i].getColWidth();  //rsmd.getColumnDisplaySize(i+1);
            
             // must be zero the autoinc
             if(dbFieldsInGroupOfPanels[i].getPrimaryKeyIntegerAutoInc() == FIELD_PRIMARY_KEY_AUTOINC)
   	     {
   	      	  JTextComponent tb = (JTextComponent) fieldTxts.get(i);
                     // pk  gets value in rowinsert, an other instance of the program may use the same value if calculated here: so  (returnPkFromOnePanelForTables);
   	      	  tb.setText("0");
             }
             
             
             // field 'isTemplate' must be 0
             if(colName.equalsIgnoreCase(fieldIsTemplate))
             {
                 JTextComponent tb = (JTextComponent) fieldTxts.get(i);
                  tb.setText("0");
             }
              
             // predefined fields to their values
             if(dbFieldsInGroupOfPanels[i].getDefaultValue()== null || dbFieldsInGroupOfPanels[i].getDefaultValue().equalsIgnoreCase(""))
   	     {
             }
             else
             {
                if(classtype.equalsIgnoreCase("java.sql.Date") || classtype.equalsIgnoreCase("java.lang.Date"))
                 {

           	  JTextBoxWithEditButtons textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(i);

                  
                  JTextComponent ta = (JTextComponent)textEditFormatedDate.getTextComp();
                   ta.setText(dbFieldsInGroupOfPanels[i].getDefaultValue()); 
           	  	  
                 }
                 else
                 {
                     JTextComponent tb = (JTextComponent) fieldTxts.get(i);
                     // pk  gets value in rowinsert, an other instance of the program may use the same value if calculated here: so  (returnPkFromOnePanelForTables);
   	      	  tb.setText(dbFieldsInGroupOfPanels[i].getDefaultValue());                 
                 }
             }
              }// for
              
            // for table rows fields  
              
               int colCount =dbFieldsInGroupOfPanels.length;
              //ArrayList listRetSavedAll = new ArrayList();
              //System.out.println("  ooooo PanelOneDataOneRecData.rowNewTables     colCount:"+colCount);
              for (int i = 0; i < colCount; i++)//  i = fieldTxts
              { 
                 String columnName = fields[i]; //get colunm name  	           	
	         String classtype = dbFieldsInGroupOfPanels[i].getColClassName();  // if integer then not add ' and ' between values
     
                if(classtype.equalsIgnoreCase("table"))
                {
                    
                    PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(i);//,PanelOneDataManyRecData);
                    for(int r = 0 ;r<pnlODMRData.getRowCount();r++)
                    {
                        
                        
                        EntityDBFields[] ed = pnlODMRData.getDbFieldsChild();
                        for(int b =0;b<ed.length;b++)
                        {
                            String strColumn = ed[b].getDbField();
                            if(strColumn.equalsIgnoreCase(fieldIsTemplate) || ed[b].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY_FROM_PARENTTABLE)
                            {
                                
                                //System.out.println("  ooooo PanelOneDataOneRecData.setFieldsTemplateZero      r:"+r+"     b:"+b+"    fieldIsTemplate:"+fieldIsTemplate);
                                     pnlODMRData.setTableValueAt("0", r, b);   
                            }
                            
             if(ed[b].getDefaultValue()!= null && !ed[b].getDefaultValue().equalsIgnoreCase(""))
   	     {
                 //System.out.println("  ooooo PanelOneDataOneRecData.setFieldsTemplateZero   IF   r:"+r+"     b:"+b+"   columnName:"+ed[b]+"    ed[b].getDefaultValue():"+ed[b].getDefaultValue());
                 pnlODMRData.setTableValueAt(ed[b].getDefaultValue(), r, b); 
             }
             else
             {
                 //System.out.println("  ooooo PanelOneDataOneRecData.setFieldsTemplateZero  ELSE    r:"+r+"     b:"+b+"  columnName:"+ed[b]+"   ed[b].getDefaultValue():"+ed[r].getDefaultValue());
   	      	   
             }                             
                            
                        }
                    }

                }
              }
              isNewRec =true;
           boolIsFromCopyOfRecord=true;  // // when set to true inserts all the table, is set in rowNewAll and setFieldsTemplateZero    
              
    }
   
   
    
   /*
    * 
    *
   *  public because is called by PanelODOR.selectTemplate
    */
   public void showSpecificRow(String q)   
   {     
       try
         {  

             db.retrieveDBDataFromQuery(q,"PanelOneDataOnRecData.showSpecificRow A");
   	     rs=db.getRS();
             rs.first();
             rs = db.retrieveRow(rs, 1); 
             
              //System.out.println("PanelOneDataOneRecData.showSpecificRow A1 ...........................q:"+q+" rs:"+rs);    
             if(rs!=null)
             {
                 // System.out.println("PanelOneDataOneRecData.showSpecificRow A2 ...........................q:"+q+" rs:"+rs);    
      //            rs.absolute(1);
                  //System.out.println("PanelOneDataOneRecData.showSpecificRow A3 ...........................q:"+q+" rs:"+rs);    
                  showRow(rs); 
                 //System.out.println("PanelOneDataOneRecData.showSpecificRow A4 ...........................q:"+q+" rs:"+rs);    
             }
            
         }
         catch(SQLException e)
         {
             System.out.println("Error PanelOneDataOneRecData.showSpecificRow() A   " + e.getMessage()+"     q:"+q); 
             e.printStackTrace();
            
         } 
         finally
         {
            closeDB();
         }
        
       
   
       
       setFocusInPageComponent();
       
       
           
    
   }
   /*
    * 
    * called by  setEntity, calculateRecordAfterKeySet of this class
   *  also called by saveAfterChecks in loading the saved data
    */
   public void showSpecificRow(int selectedRow, String q)   
   { 
       try
         {  

             db.retrieveDBDataFromQuery(q,"PanelOneDataOnRecData.showSpecificRow B");
   	     rs=db.getRS();
             rs = db.retrieveRow(rs, selectedRow); 
            if(VariablesGlobal.globalShowSelectRecord) 
            {
             System.out.println("PanelOneDataOneRecData.showSpecificRow B......... selectedRow:" + selectedRow +" ...........................q:"+q);      
            }
             if(rs!=null && rs.first()==true)
             {
                  rs.absolute(selectedRow);
                  showRow(rs); 
       
             }
            
         }
         catch(SQLException e)
         {
             System.out.println("Error PanelOneDataOneRecData.showSpecificRow() B  " + e.getMessage()+"     q:"+q); 
             e.printStackTrace();
             
         } 
         finally
         {
            closeDB();
         }
       
       setFocusInPageComponent();
       
       
       

   }

   public boolean isFirstRow()   
   { 
      boolean isFirst=false;
       try
         {  
             rs = db.retrievePrevRow(rs);
             isFirst = db.isFirstRow(rs);
         }
         catch(Exception e)
         {  System.out.println("Error PanelOneDataOneRecData.isFirstRow()" + e);      }  
        return isFirst;    
   }

   public boolean isLastRow()   
   { 
      boolean isLast=false;
       try
         {  
             rs = db.retrieveNextRow(rs);
             isLast = db.isLastRow(rs);
         }
         catch(Exception e)
         {  System.out.println("Error PanelOneDataOneRecData.isLastRow()" + e);      }  
        return isLast;    
   }
  
  /* calculate the last no of the PK, add 1 and get it
   * 
   * exists in 
   */
  /*private String getNoOfPKOfNewRecord(boolean isDoNotAddOne)// isDoNotAddOne when in update of second and more panel ofODORData, 
  {
  	 //databaseTableMeta.retrievePrimKs(entity);
  	 
     String lastNo="0";
  	 String primkeyAutoInc="";
         String[] primkeyFixedArray;// like companyId
         String[] primkeyFixedValueArray;
         int intPrimkeyFixed=0;
 
         for(int c=0;c<dbFieldsAll.length;c++)
  	 {
                if(dbFieldsAll[c].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY)
                {
                    intPrimkeyFixed++;
                }         
         }
         
                    primkeyFixedArray = new String[intPrimkeyFixed+1];
                    primkeyFixedValueArray = new String[intPrimkeyFixed+1];
         intPrimkeyFixed=0;           
  	 for(int c=0;c<dbFieldsAll.length;c++)
  	 {

                if(dbFieldsAll[c].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY_AUTOINC)
                {
                    primkeyAutoInc = dbFieldsAll[c].getDbField();
                }
                
                if(dbFieldsAll[c].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY)
                {
                    primkeyFixedArray[intPrimkeyFixed]=dbFieldsAll[c].getDbField();
                    primkeyFixedValueArray[intPrimkeyFixed]=dbFieldsAll[c].getDefaultValue();
                    //System.out.println("primkeyFixedArray:"+primkeyFixedArray[intPrimkeyFixed]+"="+primkeyFixedValueArray[intPrimkeyFixed]+"  "+intPrimkeyFixed);
                    intPrimkeyFixed++;
                }
                // FIELD_PRIMARY_KEY_FROM_PARENTTABLE
  	 }
         
         
         
  	 //System.out.println("PanelODORData.getNoOfPKOfNewRecord ===> "+entity+"  dbFieldsAll.length("+dbFieldsAll.length+")  primkeyAutoInc:"+primkeyAutoInc+" primkeyFixed:"+primkeyFixed+"="+primkeyFixedValue);
  	 if(!primkeyAutoInc.equalsIgnoreCase(""))
  	 {
             String sql="";
             String sqlWhere="";
             if(intPrimkeyFixed>0)
             {
             for(int k=0;k<intPrimkeyFixed;k++)
             {
                 if(!primkeyFixedArray[k].equalsIgnoreCase(""))
                 {
                     if(k==0)
                     {
                       sqlWhere=sqlWhere+primkeyFixedArray[k]+" LIKE "+primkeyFixedValueArray[k];
                     }
                     else
                     {
                         sqlWhere=sqlWhere+" AND "+primkeyFixedArray[k]+" LIKE "+primkeyFixedValueArray[k];
                     }
                 }
             }
             }
             
             if(intPrimkeyFixed>0)
             {
                 sql = "SELECT "+primkeyAutoInc+" FROM "+entity+" WHERE "+sqlWhere+" ORDER BY "+primkeyAutoInc;
                 
             }
             else
             {
                 sql = "SELECT "+primkeyAutoInc+" FROM "+entity+" ORDER BY "+primkeyAutoInc;
             }
             //System.out.println("PanelOneDataOnRecData.getNoOfPKOfNewRecord -  sql:"+sql);
  	 	
  	    db.retrieveDBDataFromQuery(sql,"PanelOneDataOnRecData.getNoOfPKOfNewRecord");
   	    ResultSet rspk=db.getRS();
        //ResultSetMetaData rsmdpk=db.getRSMetaData();
        try
        {
        while (rspk.next())
        {  
        	if(rspk.last())
        	{
        	  lastNo=rspk.getString(1);
        	} 	      	 	
  	 	
  	 	}
        }
  	 	catch(SQLException e)
  	 	{
  	 		System.out.println("PanelODORData.getNoOfPKOfNewRecord "+e);
  	 	}
  	 }
  	 int no = Integer.parseInt(lastNo);
  	 
         if(isDoNotAddOne)
         {
             //no;
         }
         else
         {
             no++;
         }
         
  	 return no+"";
  	  
  }*/
   
   /*
   *  if isPrinted==1 make read only
   */
   private boolean checkIfIsPrinted()
   {
       
       boolean isPrinted = false;
       try
       {
           //if isPrinted==1 make read only       
          for (int i = 0; i < dbFieldsInGroupOfPanels.length; i++)
          {
                String fieldName = dbFieldsInGroupOfPanels[i].getDbField();
                int isEditableOrVisible = dbFieldsInGroupOfPanels[i].getIsVisibleOrEditable();

                if (fieldName.equalsIgnoreCase(STRFIELD_ISPRINTED))
                {
             int selectedRow=0;       
   	    db.retrieveDBDataFromQuery(query,"PanelOneDataOnRecData.checkIfIsPrinted");
   	    rs=db.getRS();   
            //System.out.println("      panelOneDataOneRecData.checkIfAllComponentsShouldBeReadOnly   primKeyDb"+primKeyDb+"    primKeyValue:"+primKeyValue);
            selectedRow=utilsPanelReport.getRowForPrimKey("PanelOneDataOnRecData.checkIfIsPrinted",query,rs,dbFieldsAll,primKeyDb,primKeyValue);
            //System.out.println("      panelOneDataOneRecData.checkIfAllComponentsShouldBeReadOnly   selectedRow"+selectedRow+"   primKeyValue:"+primKeyValue+"  query:"+query);
            if(selectedRow != 0) 
            {
                 rs.absolute(selectedRow); 
            }
            else //(selectedRow == 0 && db.getRecordCount()!=0)//  which means no line selected
            {
                 selectedRow = 1; // 1st row if none selected
                 rs.absolute(selectedRow); 
            }    
          
                   int isPrintedValue = rs.getInt(fieldName); 
                   if(isPrintedValue>1)
                   {
                          isPrinted=true;
                          break;                       
                   }
                   else
                   {
                         isPrinted = false;
                   }
                   
                   //System.out.println("      panelOneDataOneRecData.checkIfAllComponentsShouldBeReadOnly   primKeyDb"+primKeyDb+"    primKeyValue:"+primKeyValue+" isPrintedValue:"+isPrintedValue);
                   
                   
                }
          }       
       }
       catch(SQLException e)
       {
            System.out.println("error   PanelODORData.checkIfIsPrinted  "+e.getMessage());
         if(VariablesGlobal.globalShowPrintStackTrace)  
         {
           e.printStackTrace();     
         }            
            
       }
       finally
       {
           closeDB();
       }       
       
       
      return isPrinted; 
   }
   
   private boolean checkIfIsInPreviousYear()
   {
       boolean isInPreviousYear = false;
   
       try
       {
           
           // if is in a previous year make read only
          for (int i = 0; i < dbFieldsInGroupOfPanels.length; i++)
          {
                String fieldName = dbFieldsInGroupOfPanels[i].getDbField();
                int isEditableOrVisible = dbFieldsInGroupOfPanels[i].getIsVisibleOrEditable();

                if (fieldName.equalsIgnoreCase("dbYearId") && !entity.equalsIgnoreCase("dbYear"))
                {
                    
                    
   	    db.retrieveDBDataFromQuery(query,"PanelOneDataOnRecData.checkIfIsInPreviousYear");
   	    rs=db.getRS();   
            //System.out.println("      panelOneDataOneRecData.checkIfIsInPreviousYear   primKeyDb"+primKeyDb+"    primKeyValue:"+primKeyValue);
            selectedRow=utilsPanelReport.getRowForPrimKey("PanelOneDataOnRecData.checkIfIsInPreviousYear",query,rs,dbFieldsAll,primKeyDb,primKeyValue);
            //System.out.println("      panelOneDataOneRecData.checkIfIsInPreviousYear   selectedRow"+selectedRow+"    query:"+query);
            if(selectedRow != 0) 
            {
                 rs.absolute(selectedRow); 
            }
            else //(selectedRow == 0 && db.getRecordCount()!=0)//  which means no line selected
            {
                 selectedRow = 1; // 1st row if none selected
                 rs.absolute(selectedRow); 
            }    
          
                   String yearid = rs.getString(fieldName);
                    System.out.println("PanelODORData.checkIfIsInPreviousYear  var yearId"+VariablesGlobal.globalYearId+"  fieldName:"+fieldName+"   yearid:"+yearid);             
                      if(yearid==null || yearid.equalsIgnoreCase(VariablesGlobal.globalYearId))
                      {
                          isInPreviousYear=false;
                          
                      }
                      else// should be read only
                      {
                          isInPreviousYear=true;
                          break;
                      }
                   
                }

                else
                {
                    isInPreviousYear=false;//false;
                   
                    //break;
                     
                }
          }
           
       }
       catch(SQLException e)
       {
            System.out.println("error   PanelODORData.checkIfIsInPreviousYear  "+e.getMessage());
         if(VariablesGlobal.globalShowPrintStackTrace)  
         {
           e.printStackTrace();     
         }            
            
       }
       finally
       {
           closeDB();
       }       
       
       return isInPreviousYear;
   }
   
   /*
   *  if isPrinted==1 make read only
   *  should be read only when is displayed in a different year than when inserted in db
   *  also called from  PanelODOR.rowSave to check if it should save
   */
   public boolean checkIfAllComponentsShouldBeReadOnly()
   {
       
       boolean ret=false;
       if(isNewRec)// when is new rec then continue
       {
           ret=false;
       }
       else
       {
           
           
           boolean isPrinted =  checkIfIsPrinted();
           boolean isInPreviousYear = checkIfIsInPreviousYear();
           
           if(isPrinted && isInPreviousYear)
           {
               ret = true;
           }
           else if(isPrinted && !isInPreviousYear)
           {
                ret = true;
           }
           else if(!isPrinted && isInPreviousYear)
           {
               ret = true;
           }
           else if(!isPrinted && !isInPreviousYear)
           {
               ret = false;
           }
           
           
       }// isNewRec false
      // System.out.println(" PanelODORData.checkIfAllComponentsShouldBeReadOnly ret:"+ret+"    isNewRec:"+isNewRec);
      //closeDB();        
       return ret;
   }
   

   /*
   * In table cell: editable is defined in tableModelResultSet.isCellEditable, visible is defned in PanelODMRData.packColumnsWritable
   * and FIELD_VISIBLE_NOT_EDITABLE_WHENEDIT_BUT_EDITABLE_ON_NEW not defined
   *   exists both in rowNew and setEntity. The reason is that it has to be called when is already in edit row and would like to make a new row 
   * also called in   panelODOR.showPrintPreviewForm
   */
   public void setVisibleOrEditableFields(boolean calledByEditOrNew)
   {
 
       
    //   setVisibleGroupOfFields();
       
       
       boolean shouldBeReadOnly = false;
       if(calledByEditOrNew)
       {
           shouldBeReadOnly = checkIfAllComponentsShouldBeReadOnly(); // should be read only when is displayed in a different year than when inserted in db
           //System.out.println("PanelODORData.setVisibleOrEditableFields     isNewRec:"+isNewRec+"     checkIfAllComponentsShouldBeReadOnly:"+shouldBeReadOnly);
       }
       //System.out.println("PanelODORData.setVisibleOrEditableFields     isNewRec:"+isNewRec+"   calledByEditOrNew:"+calledByEditOrNew+"   shouldBeReadOnly:"+shouldBeReadOnly+"  hasDataChanged:"+hasDataChanged);

       
       
       
          for (int i = 0; i < dbFieldsInGroupOfPanels.length; i++)
          {
              String columnClass =  dbFieldsInGroupOfPanels[i].getColClassName();
              int columnWidth =  dbFieldsInGroupOfPanels[i].getColWidth();
              String fieldName = dbFieldsInGroupOfPanels[i].getDbField();
              int visibilityEditability = dbFieldsInGroupOfPanels[i].getIsVisibleOrEditable();
           //System.out.println("PanelOneDataOnRecData.setVisibleOrEditableFields     --("+i+")--    "+fieldName+"     fieldTxts.size()"+fieldTxts.size()+"     isVisOrEdit:"+isVisOrEdit+" (is false "+FIELD_VISIBLE_NOT_EDITABLE_ALWAYS+")");
          //System.out.println("PanelODORData.setVisibleOrEditableFields     "+i+"       fieldName:"+fieldName+"       "+columnClass+"  "+visibilityEditability); 
               
                    /*if (columnClass.equalsIgnoreCase("java.lang.Double"))   
                    {
                       JTextComponent txtb = (JTextComponent)fieldTxts.get(i);
                      System.out.println("  ----------   setVisibleOrEditableFields     double   "+i+"  "+txtb.getText());
                     }*/ 
              
              
       /*FIELD_VISIBLE_AND_EDITABLE = 0;
        FIELD_VISIBLE_NOT_EDITABLE_ALWAYS = 1;
        FIELD_VISIBLE_NOT_EDITABLE_WHENEDIT_BUT_EDITABLE_ON_NEW = 2;
        FIELD_NOT_VISIBLE = 3;               */
                
               
                       if(visibilityEditability==FIELD_VISIBLE_NOT_EDITABLE_ALWAYS)
                       {
                           
                           JLabel lbl = new JLabel();
                           
                              if (columnClass.equalsIgnoreCase("java.lang.Boolean"))
                              {
          	  	        //System.out.println("PanelOneDataOneRecData.showRow boolean "+field);
              	                JCheckBox chk = (JCheckBox) fieldTxts.get(i);//i-1);
              	              
         	  	        chk.setEnabled(false);
                              }    
                              else if(columnClass.equalsIgnoreCase("java.sql.Date") || columnClass.equalsIgnoreCase("java.lang.Date"))
                              {

           	                 JTextBoxWithEditButtons textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(i);
                                 textEditFormatedDate.setEnabled(false);
                                 textEditFormatedDate.setBackground(lbl.getBackground());
                              }
                              else if(columnClass.equalsIgnoreCase("table"))
                              {
                                  //System.out.println("PanelODORData.setVisibleOrEditableFields table  FIELD_VISIBLE_NOT_EDITABLE_ALWAYS");
                                  PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(i);
                                  pnlODMRData.setVisible(true);
                      	          pnlODMRData.setFocusable(true); 
                                  pnlODMRData.setEditable(false);
                                  
                              }
                 else if (columnClass.equalsIgnoreCase("htmlfile"))
                 {
                     
                 }                               
                              else 
                              {
                                  //System.out.println("PanelODORData.setVisibleOrEditableFields  "+i+"  fieldName:"+fieldName+"      dbFieldsInGroupOfPanels[i].getLookupType():"+dbFieldsInGroupOfPanels[i].getLookupType());
                                 if(dbFieldsInGroupOfPanels[i].getLookupType() == LOOKUPTYPE_TABLECONSTANTS)
                                 {
                                     JComboBox cb = (JComboBox) fieldTxts.get(i);
                                     cb.setEnabled(false);
                                     cb.setEditable(false);
                                     cb.setFocusable(false); 
                                 }
                                 else
                                 {
                                  JTextComponent txtb = (JTextComponent)fieldTxts.get(i);//i-1);
                      	          txtb.setEditable(false);
                                  txtb.setFocusable(false);
                                  txtb.setBackground(lbl.getBackground());
                                  
                                  JTextComponent txtb2 = (JTextComponent)fieldTxts2.get(i);//i-1);
                      	          txtb2.setEditable(false);
                                  txtb2.setFocusable(false);
                                  txtb2.setBackground(lbl.getBackground());
                                  
                                  JTextComponent txtb3 = (JTextComponent)fieldTxts3.get(i);//i-1);
                      	          txtb3.setEditable(false);
                                  txtb3.setFocusable(false);
                                  txtb3.setBackground(lbl.getBackground());                                   
                                 } 
                                  
                              }
                       }
                       else if(visibilityEditability==FIELD_VISIBLE_NOT_EDITABLE_WHENEDIT_BUT_EDITABLE_ON_NEW)// like documenttype
                       {
                           if(shouldBeReadOnly)
                           {
                               
                               
                           }
                           else
                           {
                               
                           }    
                       }
                       else if(visibilityEditability==FIELD_NOT_VISIBLE )
                       {
                           if(VariablesGlobal.globalFieldIsVisibleWhenSetNotVisible) //  show
                           {
                                   JTextComponent t = new JTextField();
                              if (columnClass.equalsIgnoreCase("java.lang.Boolean"))
                              {
          	  	         //System.out.println("PanelOneDataOneRecData.showRow boolean "+field);
              	                  JCheckBox chk = (JCheckBox) fieldTxts.get(i);//i-1);
              	              
         	  	         chk.setVisible(true);
                              }     
                              else if(columnClass.equalsIgnoreCase("java.sql.Date") || columnClass.equalsIgnoreCase("java.lang.Date"))
                              {

           	                 JTextBoxWithEditButtons textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(i);
                                 textEditFormatedDate.setVisible(true);
                                 
                              }      
                              else if(columnClass.equalsIgnoreCase("table"))
                              {
                                  PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(i);
                                  pnlODMRData.setVisible(true);
                      	          pnlODMRData.setFocusable(true); 
                                  pnlODMRData.setEditable(true);
                              }         
                              else if(columnClass.equalsIgnoreCase("htmlfile"))
                              {
                                  
                                  
                                  
                                 PanelHtmlBrowser panelHtmlBrowser = (PanelHtmlBrowser) fieldTxts.get(i);
                                 panelHtmlBrowser.setVisible(true);
                                 panelHtmlBrowser.loadURL(periodiki);

                              }                                   
                              else 
                              {        
                                 if(dbFieldsInGroupOfPanels[i].getLookupType() == LOOKUPTYPE_TABLECONSTANTS)
                                 {
                                     JComboBox cb = (JComboBox) fieldTxts.get(i);
                                     cb.setVisible(true);
                                     //cb.setFocusable(false); 
                                 }
                                 else
                                 {                                  
                                       JTextComponent txtb = (JTextComponent)fieldTxts.get(i);//i-1);
                      	               txtb.setVisible(true);
                                 }
                              } 
                                                          
                           } // if(VariablesGlobal.globalFieldIsVisibleWhenSetNotVisible==false)
                           else
                           {  //if(VariablesGlobal.globalFieldIsVisibleWhenSetNotVisible)  //not show
                           JTextComponent t = new JTextField();
                              if (columnClass.equalsIgnoreCase("java.lang.Boolean"))
                              {
          	  	         //System.out.println("PanelOneDataOneRecData.showRow boolean "+field);
              	                  JCheckBox chk = (JCheckBox) fieldTxts.get(i);//i-1);
              	              
         	  	         chk.setVisible(false);
                              }     
                              else if(columnClass.equalsIgnoreCase("java.sql.Date") || columnClass.equalsIgnoreCase("java.lang.Date"))
                              {

           	                 JTextBoxWithEditButtons textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(i);
                                 textEditFormatedDate.setVisible(false);
                                 
                              }      
                              else if(columnClass.equalsIgnoreCase("table"))
                              {
                                  PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(i);
                                  pnlODMRData.setVisible(false);
                      	          pnlODMRData.setFocusable(false); 
                                  pnlODMRData.setEditable(false);
                              }         
                              else if(columnClass.equalsIgnoreCase("htmlfile"))
                              {
 
                                 PanelHtmlBrowser panelHtmlBrowser = (PanelHtmlBrowser) fieldTxts.get(i);
                                 panelHtmlBrowser.setVisible(false);
                                 panelHtmlBrowser.loadURL(periodiki);

                              }                                   
                              else 
                              {        
                                 if(dbFieldsInGroupOfPanels[i].getLookupType() == LOOKUPTYPE_TABLECONSTANTS)
                                 {
                                     JComboBox cb = (JComboBox) fieldTxts.get(i);
                                     cb.setVisible(false);
                                     //cb.setFocusable(false); 
                                 }
                                 else
                                 {                                  
                                       JTextComponent txtb = (JTextComponent)fieldTxts.get(i);//i-1);
                      	               txtb.setVisible(false);
                                 }
                              } 
                           }
                           
                       }
                       else  //visibilityEditability//  FIELD_VISIBLE_AND_EDITABLE
                       {
                           
                         if(shouldBeReadOnly)  //shouldBeReadOnly
                         {
                             
                           JLabel lbl = new JLabel();
                           
                              if (columnClass.equalsIgnoreCase("java.lang.Boolean"))
                              {
          	  	        //System.out.println("PanelOneDataOneRecData.showRow boolean "+field);
              	                JCheckBox chk = (JCheckBox) fieldTxts.get(i);//i-1);
              	              
         	  	        chk.setEnabled(false);
                              }    
                              else if(columnClass.equalsIgnoreCase("java.sql.Date") || columnClass.equalsIgnoreCase("java.lang.Date"))
                              {

           	                 JTextBoxWithEditButtons textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(i);
                                 textEditFormatedDate.setEnabled(false);
                                 textEditFormatedDate.setBackground(lbl.getBackground());
                              }
                              else if(columnClass.equalsIgnoreCase("table"))
                              {
                                  //System.out.println("PanelODORData.setVisibleOrEditableFields table  FIELD_VISIBLE_NOT_EDITABLE_ALWAYS");
                                  PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(i);
                                  pnlODMRData.setVisible(true);
                      	          pnlODMRData.setFocusable(true); 
                                  pnlODMRData.setEditable(false);
                                  
                              }                              
                              else if(columnClass.equalsIgnoreCase("htmlfile"))
                              {
                                 // PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(i);
                                 // pnlODMRData.setVisible(false);
                      	         // pnlODMRData.setFocusable(false); 
                                  //pnlODMRData.setEditable(false);
                              }                                    
                              else
                                  if(dbFieldsInGroupOfPanels[i].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)
                                  {
                                      //System.out.println("PanelODORData.setVisibleOrEditableFields ELSE A  "+i+"  fieldName:"+fieldName+"      dbFieldsInGroupOfPanels[i].getLookupType():"+dbFieldsInGroupOfPanels[i].getLookupType());
              	                       JComboBox cb = (JComboBox) fieldTxts.get(i);
                                       cb.setEnabled(false);
                                       cb.setEditable(false);
                                       cb.setFocusable(false); 
                                  }                              
                                  else 
                                  {
                                  JTextComponent txtb = (JTextComponent)fieldTxts.get(i);//i-1);
                      	          txtb.setEditable(false);
                                  txtb.setFocusable(false);
                                  txtb.setBackground(lbl.getBackground());
                                  
                                  JTextComponent txtb2 = (JTextComponent)fieldTxts2.get(i);//i-1);
                      	          txtb2.setEditable(false);
                                  txtb2.setFocusable(false);
                                  txtb2.setBackground(lbl.getBackground());      
                                  
                                  JTextComponent txtb3 = (JTextComponent)fieldTxts3.get(i);//i-1);
                      	          txtb3.setEditable(false);
                                  txtb3.setFocusable(false);
                                  txtb3.setBackground(lbl.getBackground());                                  
                                  }                             
                         
                         } 
                         else // not read only 
                         {
                             //System.out.println("PanelODORData.setVisibleOrEditableFields ELSE B "+i+"  fieldName:"+fieldName+"      dbFieldsInGroupOfPanels[i].getLookupType():"+dbFieldsInGroupOfPanels[i].getLookupType());
                           JTextComponent t = new JTextField();
                              if (columnClass.equalsIgnoreCase("java.lang.Boolean"))
                              {
          	  	         //System.out.println("PanelOneDataOneRecData.showRow boolean "+field);
              	                  JCheckBox chk = (JCheckBox) fieldTxts.get(i);//i-1);
              	              
         	  	         chk.setEnabled(true);
                              }     
                              else if(columnClass.equalsIgnoreCase("java.sql.Date") || columnClass.equalsIgnoreCase("java.lang.Date"))
                              {

           	                 JTextBoxWithEditButtons textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(i);
                                 textEditFormatedDate.setEnabled(true);
                                 textEditFormatedDate.setBackground(t.getBackground());
                              }      
                              else if(columnClass.equalsIgnoreCase("table"))
                              {
                                  PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(i);
                                  pnlODMRData.setVisible(true);
                      	          pnlODMRData.setFocusable(true);    
                                  pnlODMRData.setEditable(true);
                              }     
                              else if(columnClass.equalsIgnoreCase("htmlfile"))
                              {
                                  /*PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(i);
                                  pnlODMRData.setVisible(false);
                      	          pnlODMRData.setFocusable(false); 
                                  pnlODMRData.setEditable(false);*/
                              }                                    
                             else if(columnClass.equalsIgnoreCase("java.awt.image.BufferedImage"))
                              {
                                  JLabel lblImage = (JLabel)fieldTxts.get(i);
                                  lblImage.setVisible(true);
                      	          lblImage.setFocusable(true);    
                                  //lblImage.setEditable(true);
                              }                                
                              else
                              {
                                  if(dbFieldsInGroupOfPanels[i].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)
                                  {
              	                       JComboBox cb = (JComboBox) fieldTxts.get(i);
                                       cb.setEnabled(true);
                                       cb.setEditable(false); // can be selected but string not edited
                                       cb.setFocusable(true); 
                                  }
                                  else
                                  { 
                                      
                                   if(columnClass.equalsIgnoreCase("java.lang.String") && columnWidth>COLWIDTH_FOR_HTML) //if change 40 change and later in adding it to panel, and in showrow, and in rowUpdate
                                   {
                                      
                           PanelHtmlEditor htmlEditorArea = (PanelHtmlEditor)fieldTxts.get(i);//this is for html
                      	               htmlEditorArea.setEditable(true);
                                       htmlEditorArea.setFocusable(true);
                                       htmlEditorArea.setBackground(t.getBackground());                                     
                                       
                                       
                                       
                                   }
                                   else
                                    {
                                       JTextComponent txtb = (JTextComponent)fieldTxts.get(i);//i-1);
                      	               txtb.setEditable(true);
                                       txtb.setFocusable(true);
                                       txtb.setBackground(t.getBackground());
                                       
                                  JTextComponent txtb2 = (JTextComponent)fieldTxts2.get(i);//i-1);
                      	          txtb2.setEditable(true);
                                  txtb2.setFocusable(true);
                                  txtb2.setBackground(t.getBackground());   
                                  
                                  JTextComponent txtb3 = (JTextComponent)fieldTxts3.get(i);//i-1);
                      	          txtb3.setEditable(true);
                                  txtb3.setFocusable(true);
                                  txtb3.setBackground(t.getBackground());     
                                      }
                                }
                              }
                         }
                       }  
      
          }
   }
   

   /*
    * 
    * called by this.showRow and PanelODOR.rowNew
    */
   public void rowNewAll(boolean isNewRecIn,boolean isFromCopyOfRecord, int lookupField)  // lookupField -1 = none 
   {
          isNewRec=isNewRecIn;
          boolIsFromCopyOfRecord = isFromCopyOfRecord;    // when set to true inserts the table, is set in rowNewAll and setFieldsTemplateZero
          System.out.println("PanelODORData.rowNewAll      isNewRec:"+isNewRec+"    isFromCopyOfRecord:"+isFromCopyOfRecord+"         lookupField:"+lookupField);
   	  rowNew(isFromCopyOfRecord,lookupField);
          //String pkFromOnePanelForTables =  "";
         //  pkFromOnePanelForTables = utilsPanelReport.getNoOfPKAutoIncOfNewRecord(false,dbFieldsAll,entity);// used for table
           rowNewTables(isFromCopyOfRecord);
         setVisibleOrEditableFields(true);
         rowNewHtmlFormElements();
        setFocusInPageComponent();
    
       // focus also in setEntity
       /* if(isFirstFieldAutoInc)
        {
      	   JComponent tb = (JComponent)fieldTxts.get(1);
           if(tb.isFocusable())
           {
             tb.requestFocus();	
           }
        }

        else
        {
        	JComponent tb = (JComponent)fieldTxts.get(0);
           if(tb.isFocusable())
           {
             tb.requestFocus();	
           }
        }*/
  	   
   }
   
   private void rowNewHtmlFormElements()
   {
                      if (dbFieldsInGroupOfPanels==null)
               {
                    System.out.println("PanelOneDataOneRecData.rowNewHtmlFormElements dbFieldsInGroupOfPanels="+dbFieldsInGroupOfPanels);
               }
               int colCount =dbFieldsInGroupOfPanels.length;
              //ArrayList listRetSavedAll = new ArrayList();
              //System.out.println("  ooooo PanelOneDataOneRecData.rowNewTables     colCount:"+colCount);
              for (int i = 0; i < colCount; i++)//  i = fieldTxts
              { 
                 String columnName = fields[i]; //get colunm name  	           	
	         String classtype = dbFieldsInGroupOfPanels[i].getColClassName();  // if integer then not add ' and ' between values
     
       
                           if(classtype.equalsIgnoreCase("htmlfile"))
                          {
                 PanelHtmlBrowser panelHtmlBrowser = (PanelHtmlBrowser) fieldTxts.get(i);
                 panelHtmlBrowser.setVisible(true);
                 panelHtmlBrowser.loadURL(periodiki);
                 //panelHtmlBrowser.loadURL("www.businesseye.gr");
                          }        
       
              }
   }
   
  //primKeyValueTable: is the pk from panel one(example the invoiceId that is getted from panel one form, the customerId that is getted from onepanel for the two tables of bank and telephones)   
  private boolean rowNewTables(boolean isFromCopyOfRecord)//String primKeyValueTable)
  {
      boolean ret=false;
      
     
      
      if (dbFieldsInGroupOfPanels==null)
               {
                    System.out.println("PanelOneDataOneRecData.rowNewTables dbFieldsInGroupOfPanels="+dbFieldsInGroupOfPanels);
               }
               int colCount =dbFieldsInGroupOfPanels.length;
              //ArrayList listRetSavedAll = new ArrayList();
              //System.out.println("  ooooo PanelOneDataOneRecData.rowNewTables     colCount:"+colCount);
              for (int i = 0; i < colCount; i++)//  i = fieldTxts
              { 
                 String columnName = fields[i]; //get colunm name  	           	
	         String classtype = dbFieldsInGroupOfPanels[i].getColClassName();  // if integer then not add ' and ' between values
     
                if(classtype.equalsIgnoreCase("table"))
                {
                   //System.out.println("PanelOneDataOneRecData.rowNewTables ================================  isFromCopyOfRecord:"+isFromCopyOfRecord);
                    
                    if(isFromCopyOfRecord)// || (!isFromCopyOfRecord && isFromTemplate))// or is not copy but is fromtemplate
                    {

              String primKeyNameTable="";  
              //String primKeyValueTable ="";
                  for(int f = 0;f<dbFieldsAll.length;f++)
                  {
                      //System.out.println("PanelODORData.rowNewTables ("+i+"."+f+")  primKeyName:"+dbFieldsAll[f].getDbField()+"  isPrimaryKeyIntegerAutoInc:"+dbFieldsAll[f].getPrimaryKeyIntegerAutoInc());
                      if (dbFieldsAll[f].getPrimaryKeyIntegerAutoInc() == FIELD_PRIMARY_KEY_AUTOINC)//rsmd.isAutoIncrement(i))
                      {
                          primKeyNameTable =   dbFieldsAll[f].getDbField();  
                 //System.out.println("      ----    entity:"+entity+"     primKeyNameTable:"+primKeyNameTable);         
                          
                     //        primKeyValueTable =  utilsPanelReport.getNoOfPKOfNewRecord(false,dbFieldsAll,entity); //  primKeyValue;// rs.getString(dbFieldsAll[f].getDbField());//tb.getText();    
        
                      }
                      else
                      {
                          
                      }
                  }                        
      
                    PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(i);//,PanelOneDataManyRecData);
                    
                    
                    
                    
                    
                    
                    
                    
                    for(int r = 0 ;r<pnlODMRData.getRowCount();r++)
                    {
                        
                        
                        EntityDBFields[] ed = pnlODMRData.getDbFieldsChild();
                        for(int b =0;b<ed.length;b++)
                        {
                            String strColumn = ed[b].getDbField();
                            //System.out.println("PanelOneDataOneRecData.rowNewTables   isFromCopyOfRecord:"+isFromCopyOfRecord+"   b:"+b+"    strColumn:"+strColumn);
                            if(strColumn.equalsIgnoreCase(primKeyNameTable))
                            {
                                
                               // System.out.println("  ooooo PanelOneDataOneRecData.setFieldsTemplateZero      r:"+r+"     b:"+b);
                                     pnlODMRData.setTableValueAt("0", r, b);
                                    
                            }
                        }
                    }
  
                        
                    } // when not isFromCopyOfRecord
                    else
                    {
                    
                    PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(i);//,PanelOneDataManyRecData);
                    //ret = pnlODMRData.saveChanges();  
                    //System.out.println("  ooooo PanelOneDataOneRecData.rowNewTables columnName:"+columnName+" classtype:"+classtype+" ret:"+ret);
 
                    
                    
                    
            /*  String primKeyNameTable="";  
              //String primKeyValueTable ="";
                  for(int f = 0;f<dbFieldsAll.length;f++)
                  {
                      //System.out.println("PanelODORData.rowNewTables ("+i+"."+f+")  primKeyName:"+dbFieldsAll[f].getDbField()+"  isPrimaryKeyIntegerAutoInc:"+dbFieldsAll[f].getPrimaryKeyIntegerAutoInc());
                      if (dbFieldsAll[f].getPrimaryKeyIntegerAutoInc() == FIELD_PRIMARY_KEY_AUTOINC)//rsmd.isAutoIncrement(i))
                      {
                          primKeyNameTable =   dbFieldsAll[f].getDbField();  
                 //System.out.println("      ----    entity:"+entity+"     primKeyNameTable:"+primKeyNameTable);         
                          
                     //        primKeyValueTable =  utilsPanelReport.getNoOfPKOfNewRecord(false,dbFieldsAll,entity); //  primKeyValue;// rs.getString(dbFieldsAll[f].getDbField());//tb.getText();    
        
                      }
                      else
                      {
                          
                      }
                  }
                String sqlWhere = "";
                String sqlWhereFromEntityData = dbFieldsInGroupOfPanels[i].getSqlTableChildRead();// perhaps has 'WHERE' already, so add 'AND'

              if(primKeyNameTable.equalsIgnoreCase("")) 
              {
                  
              }
              else
              {
                if(utilsString.hasQueryWhere(sqlWhereFromEntityData))
                {
                    sqlWhere=sqlWhere+" AND ";
                }
                else
                {
                    sqlWhere=sqlWhere+" WHERE ";
                }
                
                sqlWhere=sqlWhere+primKeyNameTable+" LIKE  '-' ";//+primKeyValueTable;
                
              }*/
                
              
                    pnlODMRData.rowClearAll(false);             
              
                  //      EntityDBFields[] dbFieldsChild = dbFieldsInGroupOfPanels[i].getDbChildFields();
            
                        
                  /* 
                        String sql = sqlWhereFromEntityData+sqlWhere;
                        System.out.println("+-+ PanelODORData.rowNewTables ("+i+")   primKeyValue:"+primKeyValue +"    sql:"+sql);
           String queryWithoutOrderby = utilsString.getQueryWithoutOrderby(sql);
	    String queryOrderby = utilsString.getOrderbySubQuery(sql);
            sql = queryWithoutOrderby+" "+queryOrderby;                        
                        
                     System.out.println("+++ PanelODORData.rowNewTables           sql:"+sql+"      sqlWhereFromEntityData:"+sqlWhereFromEntityData);
                        
                   // pnlODMRData.setPrimKeyValue(primKeyValueTable);
    //                 pnlODMRData.filterForWritableTable(sql,true, true); // last parameter is true when we try to insert a completely new record with one and many, is false when we edit an already saved record
                      */

                } // when not isFromCopyOfRecord
                }

              }   
              
      return ret;        
  }
   
  // isNewRec if is new rec else if just erasing ,//  unique keys like farmerId in farmersvat (and productId in cw-management)
  // returns pk that is used in tables
   private void rowNew(boolean isFromCopyOfRecord, int lookupField) // lookupField -1 = none 
   {
     
   	
   	boolean isFirstFieldAutoInc=false;
  	   if(isFromCopyOfRecord)
   	   {
                              System.out.println("PanelODORData.rowNew    query:"+query+ "     isFromCopyOfRecord:"+isFromCopyOfRecord+"   primKeyValue:"+primKeyValue);
                     
           String queryForCopy = "";

            String qOrderByAndGroupByReadOnly = utilsString.getGroupbyAndOrderbySubQuery(query);           
   String subqueryWhere = ""; // for each primary key
  	    db.retrieveDBDataFromQuery(query,"PanelOneDataOnRecData.rowNew");
   	    rs=db.getRS();
            int selectedRow = utilsPanelReport.getRowForPrimKey("PanelOneDataOnRecData.rowNew",query,rs,dbFieldsAll,primKeyDb,primKeyValue);
             utilsPanelReport.retrievePrimKeyValueForOnePK( query, selectedRow, null,dbFieldsAll,true, entityPanel.getEntity(), primKeyDb);    
             System.out.println("----O------>  PanelOneDataOneRec.showPrintPreviewForm   '"+entityPanel.getEntity()+"   primKeyDb:"+primKeyDb+"  selectedRow:"+selectedRow+"'  primKeyValue:"+primKeyValue+"   query:"+query);          
            String[] primKeys = utilsPanelReport.getPrimKeys();
            String[] primKeysCaption = utilsPanelReport.getPrimKeysCaption();
      
            int primKeysCount = primKeys.length;
            String[] primKeysValue = utilsPanelReport.getPrimKeysValue(); 
             String sqlEntity = entityPanel.getEntity();
      //    databaseTableMeta.retrievePrimKs(entity); // first retrieve them
          for (int p = 0; p< primKeysCount; p++) // i=0 and i< because arraylist starts from 0
          {             
                //System.out.println("PanelOneDataOneRecData.rowUpdate '"+entity+"' "+primKeys[p]+"="+primKeysValue[p]); 

              
               //System.out.println("PanelOneDataOneRecData.rowUpdate  subqueryWhere  ("+i+")  "+primKey+"   "+primKeys[i]+"="+primKeysValue[i]+"     primKeyDb:"+primKeyDb+"  primKeyValue:"+primKeyValue);   
               if(primKeys[p].equalsIgnoreCase(primKeyDb))
               {
                subqueryWhere = subqueryWhere+"("+sqlEntity+"."+primKeys[p]+" LIKE '"+primKeyValue+"')"; // when is updating if a second time after insert is selected
               }
               else
               {
                   subqueryWhere = subqueryWhere+"("+sqlEntity+"."+primKeys[p]+" LIKE '"+primKeysValue[p]+"')";
               }           
                    if (p < primKeys.length-1 && primKeys.length>1) 
          	  // add AND but not on the last field(before where), also not when there is only one PK . -1 because arraylist starts from 0
          	  { subqueryWhere = subqueryWhere+" AND  ";   } 
          }
           
           
           queryForCopy= utilsString.getQueryBeforeWhere(query)+" WHERE "+subqueryWhere+" "+qOrderByAndGroupByReadOnly; 
           
           System.out.println("PanelOneDataOneRecData.rowNew oooooooo   queryForCopy:"+queryForCopy);
           
           
                          showSpecificRow(queryForCopy);

               for(int i=0;i<fieldTxts.size();i++)
   	      {

             String colCaption =  dbFieldsInGroupOfPanels[i].getCaption(); 
             String classtype = dbFieldsInGroupOfPanels[i].getColClassName();  // rsmd.getColumnClassName(i+1);
             int columnWidth = dbFieldsInGroupOfPanels[i].getColWidth();  //rsmd.getColumnDisplaySize(i+1);
            
            //System.out.println("PanelOneDataOneRecData.rowNew "+i+" "+colCaption);
             JTextComponent tb = null;              
   	           if(dbFieldsInGroupOfPanels[i].getPrimaryKeyIntegerAutoInc() == FIELD_PRIMARY_KEY_AUTOINC)
   	           {
   	           	  tb = (JTextComponent) fieldTxts.get(i);
                          // pk  gets value in rowinsert, an other instance of the program may use the same value if calculated here: so  (returnPkFromOnePanelForTables); 
                      
   	         	  tb.setText("0");
   	         	  isFirstFieldAutoInc=true;                      
                      
   	         	  //System.out.println("PanelOneDataOneRecData.rowNew: intPKAutoIncrement"+(i+1)+"label"+rsmd.getColumnLabel(i+1));
   	           }                          
              }       
                          
                          
   	}
   	else
   	{
                                             

   	      for(int i=0;i<fieldTxts.size();i++)
   	      {

             String colCaption =  dbFieldsInGroupOfPanels[i].getCaption(); 
             String classtype = dbFieldsInGroupOfPanels[i].getColClassName();  
             int columnWidth = dbFieldsInGroupOfPanels[i].getColWidth(); 
              int lookupType = dbFieldsInGroupOfPanels[i].getLookupType();  
            //System.out.println("PanelOneDataOneRecData.rowNew "+i+" "+colCaption);
             JTextComponent tb = null;

             JTextBoxWithEditButtons textFormatedDate = new JTextBoxWithEditButtons();
             //textFormatedDate.setYearEnforce(yearEnforce);
             textFormatedDate.setDateEnforceFromAndTo(VariablesGlobal.globalDateFrom,VariablesGlobal.globalDateTo);
          	 if (classtype.equalsIgnoreCase("java.sql.Date") || classtype.equalsIgnoreCase("java.lang.Date"))
          	 {
   	            textFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(i);
                                if(defaultValue[i]==null)
                                {
                                   //textFormatedDate.setText(""); 
                                   textFormatedDate.setEmptyDate();
                                }
                                else
                                {
                                    textFormatedDate.setText(defaultValue[i]);     // like global variables 
                                    //System.out.println("PanelOneDataOneRecData.rowNewForFieldsExceptUniqueKeys "+defaultValue[i]);
                                }
   	            
   	         }
   	         else if(classtype.equalsIgnoreCase("java.lang.Boolean"))
   	         {
              	   JCheckBox chk = (JCheckBox) fieldTxts.get(i);//i-1);
              	   chk.setOpaque(false);
              	   boolean sel =false;
              	   /*if(field.equals("0"))
              	   {
              	   	sel=false;
              	   }
              	   else if(field.equals("1"))
              	   {
              	   	 sel=true;
              	   }*/
              	   
              	   chk.setSelected(sel);     	         	
   	         }
   	         else
   	         {
   	         	//System.out.println("PanelOneDataOneRecData.rowNew 1 "+i);
/*   	         	if(columnWidth>40)
   	         	{
   	         		ta = (JTextComponent) fieldTxts.get(i);
   	         	}
   	         	else
   	         	{
*/
	               
/*   	         	}
                //JTextBoxWithEditButtons txtLookUpBtn = 
*/   	          }
   	        
                   //System.out.println("PanelOneDataOneRecData.rowNew: intPKAutoIncrement="+rsmd.intPKAutoIncrement(i+1)+" ("+(i+1)+") label="+rsmd.getColumnLabel(i+1));
   	           if(dbFieldsInGroupOfPanels[i].getPrimaryKeyIntegerAutoInc() == FIELD_PRIMARY_KEY_AUTOINC)
   	           {
   	           	  tb = (JTextComponent) fieldTxts.get(i);
                          // pk  gets value in rowinsert, an other instance of the program may use the same value if calculated here: so  (returnPkFromOnePanelForTables); 
                      
   	         	  tb.setText("0");
   	         	  isFirstFieldAutoInc=true;                      
                      
   	         	  //System.out.println("PanelOneDataOneRecData.rowNew: intPKAutoIncrement"+(i+1)+"label"+rsmd.getColumnLabel(i+1));
   	           }
   	           else
   	           {
 
   	              	
   	               //tb = (JTextComponent) fieldTxts.get(i);
          	           if (classtype.equalsIgnoreCase("java.sql.Date") || classtype.equalsIgnoreCase("java.lang.Date"))
          	           {   	              
                               
                               
                                if(defaultValue[i]==null)
                                {
                                    textFormatedDate.setEmptyDate();
                                  // tb.setText("");     
                                }
                                else
                                {
                                    textFormatedDate.setText(defaultValue[i]);     // like global variables 
                                    //System.out.println("PanelOneDataOneRecData.rowNewForFieldsExceptUniqueKeys "+defaultValue[i]);
                                }
                               
          	             
          	           }
                           else if(classtype.equalsIgnoreCase("java.lang.Integer")  &&  lookupType == LOOKUPTYPE_NOLOOKUP)// just integer, not lookupconstant
                           {
                               
                              
   	                        tb = (JTextComponent) fieldTxts.get(i);
   	                        
                                
                                if(defaultValue[i]==null)
                                {
                                   tb.setText("");     
                                }
                                else
                                {
                                    tb.setText(defaultValue[i]);     // like global variables 
                                    //System.out.println("PanelOneDataOneRecData.rowNewForFieldsExceptUniqueKeys "+defaultValue[i]);
                                }                                  
                                                             
                               
                           }
   	                  else if(classtype.equalsIgnoreCase("java.lang.Boolean"))
   	                  {
              	              JCheckBox chk = (JCheckBox) fieldTxts.get(i);
         
                                if(defaultValue[i]==null)
                                {
                                   chk.setSelected(false);     
                                }
                                else if(defaultValue[i].equalsIgnoreCase("true"))
                                {
                                     chk.setSelected(true);
                                }
                                else if(defaultValue[i].equalsIgnoreCase("false"))
                                {
                                     chk.setSelected(false);
                                } 
                                else
                                {
                                    System.out.println("   error PanelOneDataOneRecData.rowNew UNKNOWN default VALUE   defaultValue[i]:"+defaultValue[i]+"  ("+i+")  colCaption:"+colCaption);
                                }
          	                    	   	
          	          }
   	                  else if(classtype.equalsIgnoreCase("table"))
   	                  {
           	               //PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(i);                        
                               //pnlODMRData.setPrimKeyValueInTableModelResultSet("0");
                               
         	                    	   	
          	          }   
                           else if(classtype.equalsIgnoreCase("htmlfile"))
                          {
                 /*PanelHtmlBrowser panelHtmlBrowser = (PanelHtmlBrowser) fieldTxts.get(i);
                 panelHtmlBrowser.setVisible(true);
                 panelHtmlBrowser.loadURL(periodiki);*/
                 //panelHtmlBrowser.loadURL("www.businesseye.gr");
                          }                                
                          else if (dbFieldsInGroupOfPanels[i].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)
                          {
                              
                          
              	                   JComboBox cb = (JComboBox) fieldTxts.get(i);
                   
                                LookupTableConstantsMgt lookUpTableConstants = new LookupTableConstantsMgt();
                                EntityLookupTableConstantsData[] elutcData = lookUpTableConstants.getEntityLookupTableConstantsData(dbFieldsInGroupOfPanels[i].getLookupEntityName());
                                  //dbFieldsInGroupOfPanels[i].getLookupEntityName();
                   

                                
                                if(defaultValue[i]==null)  // || elutcData==null)
                                {
                                   cb.setSelectedIndex(0); //tb.setText("");     
                                }
                                else
                                {
                                    
                                    
                              System.out.println("PanelOneDataOneRecData.rowNew    i:"+i+"   defaultValue:"+defaultValue[i]+"  elutcData:"+elutcData) ;
                                for(int l = 0 ;l<elutcData.length;l++)
                                {
                                    //System.out.println("PanelOneDataOneRecData.rowNewForFieldsExceptUniqueKeys  "+defaultValue+"       pk:"+elutcData[l].getPk()+"   title:"+elutcData[l].getTitle());
                                    if(defaultValue[i].equalsIgnoreCase(elutcData[l].getPk()))
                                    {
                                        //defaultValue[i]
                                         cb.setSelectedItem(elutcData[l].getName());
                                    }  
                                    else
                                    {
                                        //System.out.println("PanelOneDataOneRecData.rowNewForFieldsExceptUniqueKeys NOTHING SELECTED  "+defaultValue[i]) ;
                                    }
                                }                                     
                                    
                                   // cb.setSelectedItem(defaultValue[i]);
                                    //tb.setText(defaultValue[i]);     // like global variables 
                                    //System.out.println("PanelOneDataOneRecData.rowNewForFieldsExceptUniqueKeys "+defaultValue[i]);
                                }                                
                          
                          
                          }
                          else if(dbFieldsInGroupOfPanels[i].getLookupType()==LOOKUPTYPE_ONLYONE_THISFIELD)
                          {

                               tb = (JTextComponent) fieldTxts.get(i);
                               tb.setText("");
                               JTextComponent txtb2 = (JTextComponent)fieldTxts2.get(i);//i-1);
                               txtb2.setText("");
                          }
                          else
          	          {   
                              if(classtype.equalsIgnoreCase("java.awt.image.BufferedImage"))
                              {
              	                  JLabel lblImage = (JLabel) fieldTxts.get(i);//i-1);
                                         //lblImage.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
              	                    lblImage.setOpaque(false);                                    
                              }
                              else
                              {
                                  
                                  
                                   if( columnWidth>COLWIDTH_FOR_HTML) //if change 40 change and later in adding it to panel, and in showrow, and in rowUpdate
                                   {
                                      
                                       PanelHtmlEditor htmlEditorArea = (PanelHtmlEditor)fieldTxts.get(i);//this is for html 
                           
                                       if(defaultValue[i]==null)
                                       {
                                           htmlEditorArea.setText("");     
                                       }
                                        else
                                       {
                                           htmlEditorArea.setText(defaultValue[i]);     // like global variables 
                                    //System.out.println("PanelOneDataOneRecData.rowNewForFieldsExceptUniqueKeys "+defaultValue[i]);
                                       }
                                   }
                                   else
                                   {
   	                        tb = (JTextComponent) fieldTxts.get(i);
   	                        
                                
                                if(defaultValue[i]==null)
                                {
                                   tb.setText("");     
                                }
                                else
                                {
                                    tb.setText(defaultValue[i]);     // like global variables 
                                    //System.out.println("PanelOneDataOneRecData.rowNewForFieldsExceptUniqueKeys "+defaultValue[i]);
                                }
                                   }
                              }

                                
   	              
          	          }
          	          
   	              }
   	              

               }
        }
   	               
    //System.out.println("PanelODORData.rowNewForFieldsExceptUniqueKeys       returnPkFromOnePanelForTables:"+returnPkFromOnePanelForTables);
      
    
    /*  if(isFromCopyOfRecord)// if is insert from copy of record then ask to save
      {
      	 hasDataChanged=true;   	  
      }
      else
      {
         hasDataChanged=false;   	  
      }*/
   
   }


   
   
   /*
   *  called by panelODOR.rowDelete
   
   */
   public boolean rowDeleteTables(Database dbTransaction)throws SQLException
   {

       boolean ret = false;
       
 //      	      String queryDelete = "DELETE FROM "+entity;
//       	      String subqueryWhere= " WHERE ";
       
               if (dbFieldsInGroupOfPanels==null)
               {
                    System.out.println("PanelOneDataOneRecData.rowDeleteTables dbFieldsInGroupOfPanels="+dbFieldsInGroupOfPanels);
               }
               int colCount =dbFieldsInGroupOfPanels.length;
              ArrayList listRetDeleted = new ArrayList();
                      
              //System.out.println("---->>    PanelOneDataOneRecData.rowDeleteTables colCount:"+colCount);        
                      
              for (int i = 0; i < colCount; i++)//  i = fieldTxts
              { 
                 String columnName = dbFieldsInGroupOfPanels[i].getDbField(); //get colunm name  	           	
	         String classtype = dbFieldsInGroupOfPanels[i].getColClassName();  // if integer then not add ' and ' between values
                //System.out.println("---->>    PanelOneDataOneRecData.rowDeleteTables   "+columnName+"  "+classtype);
                if(classtype.equalsIgnoreCase("table"))
                {
                    System.out.println("----> PanelOneDataOneRecData.rowDeleteTables  ("+i+")  columnName:"+columnName+" classtype:"+classtype);
                    PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(i);//,PanelOneDataManyRecData);
                    if(pnlODMRData.rowDbDeleteAll(dbTransaction))
                    {
                        listRetDeleted.add(true);
                    }
                    else
                    {
                        listRetDeleted.add(false);
                    }

                    
                    //EntityDBFields[]  dbFieldsChild = panelODMRData.getDbFieldsChild();
                    //panelODMRData.saveChanges();
                    //rowUpdateTable(panelODMRData);
                }
              }
              
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
   
   
   /*
    * 
    * 
    * called by PanelODOR.rowDelete
    * 
    */
   public boolean rowDelete(Database dbTransaction)throws SQLException
   {
       boolean ret = false;

     //-----------------------------------------------------------------
        String strTableDocDataForeignKey="vatDocForPeriodId"; 
       
     //--------------------------------------------------------------  
              //System.out.println("PanelODORData.rowDelete "+entity);


                       utilsPanelReport.retrievePrimKeyValueForOnePK(query, selectedRow, null,dbFieldsAll,true,/*primKeyIn,intColumnOfDescriptionIn,
                       sql2WhereField, sql2WhereValue,*/ entity, /*tableModelReadOnly,*/ null);    
                       
                        String[] primKeys = utilsPanelReport.getPrimKeys();
                        String[] primKeysCaption = utilsPanelReport.getPrimKeysCaption();
                        //System.out.println("-->  PanelOneDataOneRecData.rowDelete '"+entity+"' selectedRow:"+selectedRow+"  primKeys:"+primKeys.length); 
                        int primKeysCount = primKeys.length;
                        String[] primKeysValue = utilsPanelReport.getPrimKeysValue();              


   	      for(int i=0;i<fieldTxts.size();i++)
   	      {

             String colCaption =  dbFieldsInGroupOfPanels[i].getCaption(); 
             String classtype = dbFieldsInGroupOfPanels[i].getColClassName();  // rsmd.getColumnClassName(i+1);
             int columnWidth = dbFieldsInGroupOfPanels[i].getColWidth();  //rsmd.getColumnDisplaySize(i+1);   
             
          if(classtype.equalsIgnoreCase("htmlfile"))            
          {
              
              
                   String strTableDocDataForeignKeyValue = "";
                        for(int k= 0;k<primKeys.length;k++)
                        {
                            if(strTableDocDataForeignKey.equalsIgnoreCase(primKeys[k]))
                            {
                                      strTableDocDataForeignKeyValue = primKeysValue[k] ;
                            }
                            
                        }              
              
              
              
              String tableChildDocData = dbFieldsInGroupOfPanels[i].getChildTable();
              rowDeleteHtmlFormElements(dbTransaction,tableChildDocData, strTableDocDataForeignKey,strTableDocDataForeignKeyValue);
          }
              }
              
               for(int p = 0; p<primKeys.length;p++)
               {
                   System.out.println("-->  PanelOneDataOneRecData.rowDelete '"+entity+"' "+primKeys[p]+"="+primKeysValue[p]); 
               }
                        
               
          
             //  databaseTableMeta.retrievePrimKs(entity);               
              
              //int primKeysCount=databaseTableMeta.getCountOfPrimKeys();//
       	      String queryDelete = "DELETE FROM "+entity;
       	      String subqueryWhere= " WHERE ";               
       	      int count = 0;
    	      for (int pk=0;pk<primKeysCount;pk++)
       	      {
/*   	              String primKey;
                  primKey = databaseTableMeta.getPrimKeyName(pk);

       	      	//System.out.println("PanelODORData.deleteRow "+pk+" "+primKeys[pk]+" "+primKeysValue[pk]);
                 // System.out.println("PanelODORData.deleteRow ("+pk+") "+primKey+" "+getPrimKeyValue(primKey)+"    "+primKeyValue);
       	      	  /*if(primKeysCount==1 || pk==primKeysCount-1)
       	      	  {  subqueryWhere=subqueryWhere+primKey+" LIKE "+getPrimKeyValue(primKey) ;  }
       	      	  else
       	      	  {  subqueryWhere=subqueryWhere+primKey+" LIKE "+getPrimKeyValue(primKey)+" AND ";  }*/
                  
                  
                   if (count>=1)//after the first time that runs adds an and
             	   {  subqueryWhere = subqueryWhere + " AND ";    }

                              subqueryWhere = subqueryWhere+ primKeys[pk]+" = '"+primKeysValue[pk]+"'";//getPrimKeyValue(primKey)+"'";

                         count++;

               } // for                    
                  
               
       	      
                
               queryDelete=queryDelete+subqueryWhere;
               if (VariablesGlobal.globalShowSQLEdit)
               {    System.out.println("PanelODORData.rowDelete queryDelete:"+queryDelete);   }
               
               //int retRec = db.updateQuery(queryDelete,"PanelODORData.rowDelete", showDialogOnError);
               int retRec = dbTransaction.transactionUpdateQuery(queryDelete,"PanelODORData.rowDelete", showDialogOnError);
               
               if(retRec!=0)
               {
                   ret = true;
               }
             

      closeDB();
      return ret;
   }

   
   /*
   
   *  called by above function, in the start of it
   */
   private boolean rowDeleteHtmlFormElements(Database dbTransaction,String  tableChildDocData, String strTableDocDataForeignKey, String strTableDocDataForeignKeyValue)throws SQLException
   {
       

              String queryHtmlDocDataRead =   "SELECT * FROM "+tableChildDocData;
 
       
       boolean ret = false;
              //System.out.println("PanelODORData.rowDeleteHtmlFormElements  selectedRow:"+selectedRow+"  queryHtmlDocDataRead:"+queryHtmlDocDataRead);
                       utilsPanelReport.retrievePrimKeyValueForOnePK(queryHtmlDocDataRead, selectedRow, null,dbFieldsAll,true,/*primKeyIn,intColumnOfDescriptionIn,
                       sql2WhereField, sql2WhereValue,*/ tableChildDocData, /*tableModelReadOnly,*/ null);    
                   //System.out.println("PanelOneDataOneRecData.rowDeleteHtmlFormElements     queryHtmlDocDataRead:"+queryHtmlDocDataRead);
                        String[] primKeys = utilsPanelReport.getPrimKeys();
                        String[] primKeysCaption = utilsPanelReport.getPrimKeysCaption();
                        //System.out.println("-->  PanelOneDataOneRecData.rowDelete '"+entity+"' selectedRow:"+selectedRow+"  primKeys:"+primKeys.length); 
                        int primKeysCount = primKeys.length;
                        String[] primKeysValue = utilsPanelReport.getPrimKeysValue();              
              
                   //String strTableDocDataForeignKeyValue = "";
                        /*for(int k= 0;k<primKeys.length;k++)
                        {
                            if(strTableDocDataForeignKey.equalsIgnoreCase(primKeys[k]))
                            {
                                      strTableDocDataForeignKeyValue = primKeysValue[k] ;
                            }
                            
                        } */                       
                        
                        
                        
               for(int p = 0; p<primKeys.length;p++)
               {
                   System.out.println("-->  PanelOneDataOneRecData.rowDeleteHtmlFormElements '"+tableChildDocData+"'  "+primKeys[p]+"="+primKeysValue[p]+"        strTableDocDataForeignKeyValue:"+strTableDocDataForeignKeyValue); 
               }
                        
             //  databaseTableMeta.retrievePrimKs(entity);               
              
              //int primKeysCount=databaseTableMeta.getCountOfPrimKeys();//
       	      String queryDelete = "DELETE FROM "+tableChildDocData;
       	      String subqueryWhere= " WHERE ";               
       	      int count = 0;
    	      for (int pk=0;pk<primKeysCount;pk++)
       	      {
/*   	              String primKey;
                  primKey = databaseTableMeta.getPrimKeyName(pk);

       	      	//System.out.println("PanelODORData.deleteRow "+pk+" "+primKeys[pk]+" "+primKeysValue[pk]);
                 // System.out.println("PanelODORData.deleteRow ("+pk+") "+primKey+" "+getPrimKeyValue(primKey)+"    "+primKeyValue);
       	      	  /*if(primKeysCount==1 || pk==primKeysCount-1)
       	      	  {  subqueryWhere=subqueryWhere+primKey+" LIKE "+getPrimKeyValue(primKey) ;  }
       	      	  else
       	      	  {  subqueryWhere=subqueryWhere+primKey+" LIKE "+getPrimKeyValue(primKey)+" AND ";  }*/
                  
                  
                   if (count>=1)//after the first time that runs adds an and
             	   {  subqueryWhere = subqueryWhere + " AND ";    }

                            if(strTableDocDataForeignKey.equalsIgnoreCase(primKeys[pk]))
                            {
                                      //strTableDocDataForeignKeyValue = primKeysValue[pk] ;
                                  subqueryWhere = subqueryWhere+ primKeys[pk]+" = '"+strTableDocDataForeignKeyValue+"'";                                      
                            }
                            else
                            {
                              subqueryWhere = subqueryWhere+ primKeys[pk]+" = '"+primKeysValue[pk]+"'";//getPrimKeyValue(primKey)+"'";
                            }
                         count++;

               } // for                    
                  
               
       	      
                
               queryDelete=queryDelete+subqueryWhere;
               if (VariablesGlobal.globalShowSQLEdit)
               {    System.out.println("PanelODORData.rowDeleteHtmlFormElements        queryDelete:"+queryDelete);   }
               
               //int retRec = db.updateQuery(queryDelete,"PanelODORData.rowDelete", showDialogOnError);
               int retRec = dbTransaction.transactionUpdateQuery(queryDelete,"PanelODORData.rowDeleteHtmlFormElements", showDialogOnError);
               
               if(retRec!=0)
               {
                   ret = true;
               }
             
      closeDB();
      return ret;
   }   
   
   
   
   

//exists modificated in PanelOneDataOneRecData and TableCellEditorLookUp and windowlookup
  private class DocumentHandler implements DocumentListener
  { // if data changed in txt
  
    int no=0;
    String classtype="";
    String foreignTable = null;
    int txtIndex=0; //   txtIndex key=0  , lookup text 1, lookup text  2
    String columnDbName;
    public DocumentHandler( int noIn, int txtIndexIn, String classtypeIn, String foreignTableIn,String columnDbNameIn )
    {
        no = noIn;
        txtIndex=txtIndexIn;
        classtype=classtypeIn;
        foreignTable=foreignTableIn;
        columnDbName=columnDbNameIn;
        //System.out.println("PanelODORData.DocumentHandler  "+no+"  "+classtype+" "+foreignTable+"."+columnDbName);
    }
       @Override 
    public void insertUpdate(DocumentEvent e)
    {
        
        
    Runnable doChange = new Runnable() {
        @Override
        public void run() {
             changeText();
        }
    };       
    SwingUtilities.invokeLater(doChange);        
      	   
          //System.out.println("PanelODORD.DocumentHandler insertUpdate hasDataChanged "+hasDataChanged);
        //System.out.println("PanelODORD DocumentHandler.insertUpdate hasDataChanged"+hasDataChanged+" - keyChanged"+keyChanged+" txtIndex"+txtIndex+" not defined, foreignTable "+foreignTable+" noIn"+no);
    }
    
    //   https://stackoverflow.com/questions/15206586/getting-attempt-to-mutate-notification-exception
     @Override
    public void removeUpdate(DocumentEvent e)
    {
       
    Runnable doChange = new Runnable() {
        @Override
        public void run() {
             changeText();
        }
    };       
    SwingUtilities.invokeLater(doChange);           
       
        //hasDataChanged=true; //---------------
        //System.out.println("PanelODORD.DocumentHandler removeUpdate hasDataChanged "+hasDataChanged);
         //System.out.println("PanelODORD DocumentHandler.removeUpdate hasDataChanged"+hasDataChanged+" guiLoaded"+guiLoaded+" - keyChanged"+keyChanged+" txtIndex"+txtIndex+" not defined, foreignTable "+foreignTable+" noIn"+no);
    }
    
    //  https://stackoverflow.com/questions/15206586/getting-attempt-to-mutate-notification-exception
     @Override
    public void changedUpdate(DocumentEvent e)
    {

    Runnable doChange = new Runnable() {
        @Override
        public void run() {
             changeText();
        }
    };       
    SwingUtilities.invokeLater(doChange);           
       
        //System.out.println("PanelODORD DocumentHandler.changedUpdate hasDataChanged"+hasDataChanged+" - keyChanged"+keyChanged+" txtIndex"+txtIndex+" not defined, foreignTable "+foreignTable+" noIn"+no);
    }

    
    
    private void changeText()
    {

        if (guiLoaded)
        {
           
           hasDataChanged=true;
        
          JTextField t = new JTextField();
          JLabel l = new JLabel();

         if(classtype.equalsIgnoreCase("java.sql.Date") || classtype.equalsIgnoreCase("java.lang.Date"))
         {
           JTextBoxWithEditButtons textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(no);// -1 because i starts from 1
           textEditFormatedDate.findDateNameInText();// to change day name in label when different date is typed 
           textEditFormatedDate.setBackground(t.getBackground());
         } 
         else if(classtype.equalsIgnoreCase("java.lang.Boolean"))
         {
             //System.out.println("PanelODORData.DocumentHandler.insertUpdate  "+no+"  "+classtype+" "+foreignTable+"."+columnDbName); 
         }
         else if(dbFieldsInGroupOfPanels[no].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)
         {
                JComboBox cmbBox =  (JComboBox) fieldTxts.get(no);
                    //value =  cmbBox.getSelectedItem().toString();             
         
         }
         else if( classtype.equalsIgnoreCase("java.awt.image.BufferedImage"))
         {
         	JLabel la =(JLabel)fieldTxts.get(no);
         	la.setBackground(l.getBackground());                    
         }         
         else //if(colWidth>40)
         {
         	if(fieldValidation[no]==FIELD_VALIDATION_AFM)
         	{
         		JTextComponent tac =(JTextComponent)fieldTxts.get(no);
         		//System.out.println("PanelODORD.DocumentHandler.insertUpdate AFM");
         		tac.setBackground(t.getBackground());  
         		
         		//JLabel lb =(JLabel)listLabelValid.get(no);	
         		 JButton btn =(JButton)listButtonValid.get(no);
                        
         		if(utilsString.checkGreekAFM(tac.getText()))	
         		{
         			btn.setIcon(ICO_OK16);
                                btn.setEnabled(true);
         		}
         		else
         		{
         			btn.setIcon(ICO_CANCEL16);
                                btn.setEnabled(false);
         		}
      		
         	}
                else
         	{
                   
         		  JTextComponent tac =(JTextComponent)fieldTxts.get(no);
         	          tac.setBackground(t.getBackground());
         	}
         	
         	if(dbFieldsInGroupOfPanels[no].getLookupType()==LOOKUPTYPE_ONLYONE_THISFIELD && foreignTable!=null)// look up
         	{   
                    
         		if(txtIndex==0)
         		{
                            JTextComponent ta =(JTextComponent)fieldTxts.get(no);                            
                            JTextComponent tb2 =(JTextComponent)fieldTxts2.get(no);
                             //System.out.println("PanelODORData.  DocumentHandler.insertUpdate   ====================================  txtIndex:"+txtIndex+" ("+no+") formGlobalTableToGet1:"+formGlobalTableToGet1);  
                            //System.out.println("PanelODORData.DocumentHandler.insertUpdate  ("+no+")   keyChanged:"+fieldTxtsKeyChanged.get(no) +"   txtIndex:"+txtIndex+" countOfUniqueKeys:"+countOfUniqueKeys+" isMasterUnique:"+isMasterUnique);
         		    tb2.setText(calculateTextForLookupsAfterKeyIsSet(no, foreignTable));
         		   //if (countOfUniqueKeys >0 && isMasterUnique )

                                fieldTxtsKeyChanged.set(no, true);
         		    	keyChanged=true; // ie farmerid 
            //System.out.println("PanelODORData.DocumentHandler.changeText  ("+no+")   keyChanged:"+fieldTxtsKeyChanged.get(no) +"   txtIndex:"+txtIndex);                          
         		}
         		else if(txtIndex==1)
         		{
                            JTextComponent tb2 =(JTextComponent)fieldTxts2.get(no);
                              if(tb2.hasFocus())//display window when the focus in on tb2. Not show when the focus in in tb1 and keyb is setted
                              {
                                //System.out.println("PanelODORData.DocumentHandler.insertUpdate  ("+no+")   keyChanged:"+fieldTxtsKeyChanged.get(no) +"   txtIndex:"+txtIndex+" countOfUniqueKeys:"+countOfUniqueKeys+"   hasDataChanged:"+hasDataChanged);
                                displayWindowLookUp(foreignTable,no);
                              }
         		}
         		else
         		{
         	       //System.out.println("error DocumentHandler.insertUpdate"+" -> txtIndex"+txtIndex+" not defined, foreignTable "+foreignTable+" noIn"+no); 		
         		}
         	      
       		      
         	}
                else
                {
                    //System.out.println("PanelODORData.DocumentHandler.insertUpdate  ("+no+") "+fieldTxtsKeyChanged.get(no).toString());
                                fieldTxtsKeyChanged.set(no, true);
         		    	keyChanged=true; // ie farmerid                     
                }
          }  
                // if is not editable(like sum fields of a table) calculate
               //System.out.println("PanelODORData.  DocumentHandler.insertUpdate   ====================================   formGlobalTableToGet1:"+formGlobalTableToGet1);           
                if(dbFieldsInGroupOfPanels[no].getIsVisibleOrEditable() != FIELD_VISIBLE_AND_EDITABLE && Boolean.parseBoolean(fieldTxtsKeyChanged.get(no).toString()))
                {
                   System.out.println("PanelODORData.DocumentHandler.insertUpdate ++++ != FIELD_VISIBLE_AND_EDITABLE ("+no+")   "+dbFieldsInGroupOfPanels[no].getCaption()+"      "+fieldTxtsKeyChanged.get(no).toString());
                      //        calculateRecordAfterKeySet(no);	
                    
                    dbFieldsCalculateSet(dbFieldsInGroupOfPanels,no,foreignTable);  
                    fieldTxtsKeyChanged.set(no, false);
                    hasDataChanged=false;
                    //System.out.println("PanelODORData.dbFieldsCalculateSet f  hasDataChanged:"+hasDataChanged);
                }
                else if(dbFieldsInGroupOfPanels[no].getIsVisibleOrEditable() != FIELD_VISIBLE_AND_EDITABLE && !Boolean.parseBoolean(fieldTxtsKeyChanged.get(no).toString()))
                {
                    
                   // dbFieldsCalculateSet(dbFieldsInGroupOfPanels,no,foreignTable);  
                   // fieldTxtsKeyChanged.set(no, false);
                   // hasDataChanged=false;                    
                    
                    System.out.println("PanelODORData.DocumentHandler.insertUpdate +++----++++ ("+no+")   "+dbFieldsInGroupOfPanels[no].getCaption()+"   "+dbFieldsInGroupOfPanels[no].getIsVisibleOrEditable()+"   "+fieldTxtsKeyChanged.get(no).toString());
                }
                        

               //System.out.println(" PanelODORData.DocumentHandler.changeText   ------------------------------ "+no+"   "+classtype+" "+foreignTable+"."+columnDbName+"   keyChanged:"+keyChanged);
         	//ta.remove(lblIcoAttention);
         
          
        }
        else
        {
             fieldTxtsKeyChanged.set(no, false);
        }
        
        
    }
    
    
    
    /*private void changeText2()
    {
        
        
 if (guiLoaded)
        {
          //System.out.println("==OOOOOo     PanelODORData.DocumentHandler.removeUpdate  "+no+"  "+classtype+" "+foreignTable+"."+columnDbName);
        	 hasDataChanged=true;
        JTextField t = new JTextField();
        JLabel l = new JLabel();
         if(classtype.equalsIgnoreCase("java.sql.Date") || classtype.equalsIgnoreCase("java.lang.Date"))
         {
            //System.out.println("PanelODORD.DocumentHandler.removeUpdate hasDataChanged "+hasDataChanged+" "+no+" "+classtype);
	
           JTextBoxWithEditButtons textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(no);// -1 because i starts from 1
           textEditFormatedDate.findDateNameInText();// to change day name in label when different date is typed 
           textEditFormatedDate.setBackground(t.getBackground());
         }
         else if(classtype.equalsIgnoreCase("java.lang.Boolean"))
         {
             //System.out.println("PanelODORData.DocumentHandler.removeUpdate  "+no+"  "+classtype+" "+foreignTable+"."+columnDbName);
         }   
         else if(dbFieldsInGroupOfPanels[no].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)
         {
                   JComboBox cmbBox =  (JComboBox) fieldTxts.get(no);
                    //value =  cmbBox.getSelectedItem().toString();             
         
         }    
         else if( classtype.equalsIgnoreCase("java.awt.image.BufferedImage"))
         {
         		  JLabel la =(JLabel)fieldTxts.get(no);
         	          la.setBackground(l.getBackground());                    
         }          
         else
         {
         	if(fieldValidation[no]==FIELD_VALIDATION_AFM)
         	{
         		JTextComponent ta =(JTextComponent)fieldTxts.get(no);
         		//System.out.println("PanelODORD.DocumentHandler.insertUpdate AFM");
         		ta.setBackground(t.getBackground());   
         			
         		JLabel lb =(JLabel)listLabelValid.get(no);	
         		
         		if(utilsString.checkGreekAFM(ta.getText()))	
         		{
         			//lb.setText("correct");
         			lb.setIcon(ICO_OK16);
         		}
         		else
         		{
         			//lb.setText("faulty");
         			lb.setIcon(ICO_CANCEL16);
         		}
         	     
         	           		
         	}
         	else
         	{
                    
         	       JTextComponent ta =(JTextComponent)fieldTxts.get(no);
         	       ta.setBackground(t.getBackground());
         	}
         	
         	if(dbFieldsInGroupOfPanels[no].getLookupType()==LOOKUPTYPE_ONLYONE_THISFIELD && foreignTable!=null) // look up
         	{
                   
         		if(txtIndex==0)
         		{
                             //System.out.println("PanelODORData.DocumentHandler.removeUpdate  ("+no+")   keyChanged:"+fieldTxtsKeyChanged.get(no) +"   txtIndex:"+txtIndex+" countOfUniqueKeys:"+countOfUniqueKeys+" isMasterUnique:"+isMasterUnique);
         		//System.out.println("PanelODORData.  DocumentHandler.removeUpdate   ====================================  txtIndex:"+txtIndex+"   ("+no+")  formGlobalTableToGet1:"+formGlobalTableToGet1);  	
                            calculateTextForLookupsAfterKeyIsSet(no, foreignTable);
                                
         		    //if (countOfUniqueKeys >0   && isMasterUnique )
                            //{
                                fieldTxtsKeyChanged.set(no, true);
         		    	keyChanged=true; // ie farmerid 
                            //}
         		}
         		else if(txtIndex==1)
         		{
                              JTextComponent tb2 =(JTextComponent)fieldTxts2.get(no);
                              if(tb2.hasFocus())//display window when the focus in on tb2. Not show when the focus in in tb1 and keyb is setted
                              {
                               //System.out.println("PanelODORData.DocumentHandler.removeUpdate  ("+no+")   keyChanged:"+fieldTxtsKeyChanged.get(no) +"   txtIndex:"+txtIndex+" countOfUniqueKeys:"+countOfUniqueKeys+"   hasDataChanged:"+hasDataChanged);
                               displayWindowLookUp(foreignTable,no);	
                              }
         		}
         		else
         		{
         	       //System.out.println("error DocumentHandler.removeUpdate"+" -> txtIndex"+txtIndex+" not defined, foreignTable "+foreignTable+" noIn"+no); 		
         		}       
         	}
                // if is not editable(like sum fields of a table) calculate
                //System.out.println("PanelODORData.  DocumentHandler.removeUpdate   ====================================   formGlobalTableToGet1:"+formGlobalTableToGet1);          
                if(dbFieldsInGroupOfPanels[no].getIsVisibleOrEditable() != FIELD_VISIBLE_AND_EDITABLE && Boolean.parseBoolean(fieldTxtsKeyChanged.get(no).toString()))
                {

                   // System.out.println("PanelODORData.DocumentHandler.removeUpdate  ("+no+") "+fieldTxtsKeyChanged.get(no).toString());
                   //           calculateRecordAfterKeySet(no);	
                              
                     dbFieldsCalculateSet(dbFieldsInGroupOfPanels,no,foreignTable);                    
                    
                }         	
         	//System.out.println("  change "+foreignTable); 
         }
        }
        keyChanged=true;
        //hasDataChanged=true; //---------------
        //System.out.println("PanelODORD.DocumentHandler removeUpdate hasDataChanged "+hasDataChanged);
         //System.out.println("PanelODORD DocumentHandler.removeUpdate hasDataChanged"+hasDataChanged+" guiLoaded"+guiLoaded+" - keyChanged"+keyChanged+" txtIndex"+txtIndex+" not defined, foreignTable "+foreignTable+" noIn"+no);
           
        
        
    }*/
    
  }

   class  ActionShowDialogLookUp extends AbstractAction                 
   {       
         String iForeignTable;
         int iF;
         int tb2N;
        public ActionShowDialogLookUp(String foreignTable, int f)
        {
                  iForeignTable = foreignTable;
                  iF=f;
                  //tb2N=tb2No;
           //System.out.println("ActionShowDialogLookUp ("+iForeignTable+" , "+iF+")");
        }
      	
    	public void actionPerformed(ActionEvent e)
      	{
                 //    System.out.println("ActionShowDialogLookUp ("+iForeignTable+" , "+iF+")");
                displayDialogLookUp(iForeignTable, iF);
        }    	
    }                

   class  ActionShowDialogNew extends AbstractAction                 
   {       
         String iForeignTable;
         int iF;
         int tb2N;
        public ActionShowDialogNew(String foreignTable, int f)
        {
                  iForeignTable = foreignTable;
                  iF=f;
                  //tb2N=tb2No;
                  //primKey=primKeyIn;
                 
           //System.out.println("ActionShowDialogEdit ("+iForeignTable+" , "+iF+")");
        }
      	
    	public void actionPerformed(ActionEvent e)
      	{
                      //System.out.println("ActionShowDialogLookUp ("+iForeignTable+" , "+iF+")");
                displayDialogNew(iForeignTable, iF);

        }    	
    } 
   
   
   class  ActionShowDialogEdit extends AbstractAction                 
   {       
         String iForeignTable;
         int iF;
         int tb2N;
        public ActionShowDialogEdit(String foreignTable, int f)
        {
                  iForeignTable = foreignTable;
                  iF=f;
                  //tb2N=tb2No;
                  //primKey=primKeyIn;
                 
           //System.out.println("ActionShowDialogEdit ("+iForeignTable+" , "+iF+")");
        }
      	
    	public void actionPerformed(ActionEvent e)
      	{
                      //System.out.println("ActionShowDialogLookUp ("+iForeignTable+" , "+iF+")");
                displayDialogEdit(iForeignTable, iF);
        }    	
    }    

  
  public void setGuiLoaded(boolean loaded)
  {
      
      //System.out.println("PanelODORData   ++++   setGuiLoaded:"+loaded);
  	guiLoaded=loaded; 
  }
   
  
  public void setDataHasChanged(boolean dataChanged)
  {
    
  	  hasDataChanged=dataChanged;
          System.out.println("------------- PanelODORData.setDataHasChanged  hasDataChanged:"+hasDataChanged+"    dbFieldsInGroupOfPanels.length:"+dbFieldsInGroupOfPanels.length);
          
       if(dbFieldsInGroupOfPanels!=null)
      {
        for(int f = 0;f<dbFieldsInGroupOfPanels.length;f++)
        {
           if(dbFieldsInGroupOfPanels[f].getColClassName().equalsIgnoreCase("table"))
          {
              PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(f);
              pnlODMRData.setDataHasChanged(dataChanged);
          }
           else if(dbFieldsInGroupOfPanels[f].getColClassName().equalsIgnoreCase("htmlfile"))
          {     
              
          }      
        }
      }
  	 System.out.println("PanelODORD.setDataHasChanged ++++++++++++    hasDataChanged:"+hasDataChanged);	
  }
  
  
  /*
  *
  *
  */
  public boolean getHasDataChanged()
  {  
      boolean checkIfDataHasChanged = false;
      
      // System.out.println(" PanelODORData.getHasDataChanged ----  hasDataChanged:"+hasDataChanged+"    checkIfDataHasChanged:"+checkIfDataHasChanged);
      
     if(dbFieldsInGroupOfPanels!=null)
      {
        for(int f = 0;f<dbFieldsInGroupOfPanels.length;f++)
        {
           if(dbFieldsInGroupOfPanels[f].getColClassName().equalsIgnoreCase("table"))
          {
              //System.out.println("PanelODORData.getHasDataChanged   f:"+f+"     dbFieldsInGroupOfPanels[f].getColClassName():"+dbFieldsInGroupOfPanels[f].getColClassName()+"   "+dbFieldsInGroupOfPanels[f].getCaption()+"    length:"+dbFieldsInGroupOfPanels.length+"      hasDataChanged:"+hasDataChanged);
              PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(f);
              
              if(pnlODMRData.getHasDataChanged())  // hasDataChanged is from this panel
              {
                   checkIfDataHasChanged = true;
                   System.out.println("PanelODORD.getHasDataChanged -true- "+pnlODMRData.getHasDataChanged()+" or "+hasDataChanged);
                   break;
              }
              else
              {
                  checkIfDataHasChanged = false;
              }
              //System.out.println("PanelODORD.getHasDataChanged checkIfDataHasChanged:"+checkIfDataHasChanged+" dbFieldsInGroupOfPanels:"+dbFieldsInGroupOfPanels.length);
          }
           else if(dbFieldsInGroupOfPanels[f].getColClassName().equalsIgnoreCase("htmlfile"))
          {     
              
          }             
        } // for
        
        //System.out.println(" PanelODORData.getHasDataChanged ----  hasDataChanged:"+hasDataChanged+"    checkIfDataHasChanged:"+checkIfDataHasChanged);
        
        if(hasDataChanged || checkIfDataHasChanged)// hasDataChanged from panelOneDataOneRec
        {
            checkIfDataHasChanged=true;
        }
        else
        {
            checkIfDataHasChanged=false;
        }
        
      }//  if
      else
      {
          checkIfDataHasChanged = hasDataChanged;
      }
       //System.out.println("----  PanelODORData.getHasDataChanged   hasDataChanged:"+hasDataChanged);	
  	return checkIfDataHasChanged;
  }

}
