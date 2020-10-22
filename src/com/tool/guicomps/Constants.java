
package com.tool.guicomps;

import java.util.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.Icon;

import java.awt.*;
/**
 * An interface that holds the global constants.
 */
public interface Constants
{
	/**
	 * Corresponding int for CANCEL.
	 */
	//public static final int CANCEL = 0;

	/**
	 * Corresponding int for OK.
	 */
	//public static final int OK = 1;

	/**
	 * Corresponding int for HELP.
	 */
	//public static final int HELP = 2;

	/**
	 * Corresponding int for NEW.
	 */
	//public static final int NEW = 3;

       JLabel lbl = new JLabel();
       
       // Color blue= new Color(129, 169, 226);
       //Color lightBlue= new Color(220,235,253);//148, 215, 254);
     /*public static final int EXPORTFILE_TXT =1;  
     public static final int EXPORTFILE_HTML =2;  
     public static final int EXPORTFILE_EXCELCALC =3; 
    public static final int[] EXPORTFILE_TYPES ={EXPORTFILE_TXT,EXPORTFILE_HTML,EXPORTFILE_EXCELCALC};*/
       
   //whereIsCalledFrom in PanelOneDataOneRec.loadAndGetEntityPanelsOneDataOnRecData
      
  // also in DialogUpdateProcess in update project
       public static final String PRODUCT_OLA = "0"; //input on jar
       //public static final String PRODUCT_TIMOLOGIA = "1";
       //public static final String PRODUCT_APLOGRAFIKA = "2";
       public static final String PRODUCT_FARMERSVAT = "3";
       public static final String PRODUCT_FARMERSVAT_NOT = "4";

       public static final String PRODUCT_OLA_CAPTION = "";
       public static final String PRODUCT_TIMOLOGIA_CAPTION = "τιμολόγηση";
       public static final String PRODUCT_TIMOLOGIA_CAPTION_OF = "τιμολόγησης";
       public static final String PRODUCT_APLOGRAFIKA_CAPTION = "έσοδα - έξοδα";       
       public static final String PRODUCT_APLOGRAFIKA_CAPTION_OF = "εσόδων - εξόδων";  
       public static final String PRODUCT_PAYROLL_CAPTION = "μισθοδοσία";    
       public static final String PRODUCT_PAYROLL_CAPTION_OF = "μισθοδοσίας";   
       public static final String PRODUCT_FARMERSVAT_CAPTION = "ΦΠΑ αγροτών";   

       public static final boolean HAS_TEMPLATES =true; // panel templates, like protupa
       public static final boolean HAS_NOT_TEMPLATES =false;
       
       public static final  int NUMBER_OF_FORMROWS_PER_PAGE = 5;
       
       
       public static final String  DATABASENAME_TOCREATE_DEFAULT ="businesseye";
       
      /*
       
        String productCaption = "";
        if(VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_TIMOLOGIA))
        {
            productCaption = PRODUCT_TIMOLOGIA_CAPTION;
        }
        else if(VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_APLOGRAFIKA))
        {
            productCaption = PRODUCT_APLOGRAFIKA_CAPTION;
        }
        else
        {
            productCaption = PRODUCT_OLA_CAPTION;
        }       
       
       */
       
       public static final String STR_VERSIONSUB_START = "1.2589";
       
       public final int FONT_SIZE_NOT_SET = 0;
       
       public static final int CHECK_ON_ENTRY = 1;
       public static final int CHECK_ON_INSERT_OR_ON_UPDATE = 2;
        public static final int CHECK_ON_OPEN_TO_EDIT_OR_DELETE = 3; // records from bridge
       
       public static final String CAPTION_IMPORT_DATA = "εισαγωγή δεδομένων";
       public static final String CAPTION_EXPORT_DATA = "εξαγωγή δεδομένων";
      
       
      public static final int YES = 0;
      public static final int NO = 1;
       
       
       public static final boolean SHOW_PANEL_COLLAPSE_NOT = false;
       public static final boolean SHOW_PANEL_COLLAPSE = true;
       
        public static final int SHOW_BORDER_BTN_NONE = 0;
        public static final int SHOW_BORDER_BTN_ONLY_ONE = 1;
       
        public static final String UPDATEADDITIONAL_ENABLED="1";
        public static final String UPDATEADDITIONAL_NOT_ENABLED="0";
       
       
       public static int IS_CALLED_BY_MULTIPLE_TABLES_MDMR = 0;
       public static int IS_CALLED_BY_ONE_TABLE_ODOR = 1;
       
       public static int WINDOWLOOKUP_IS_CALLED_IN_TEXTFIELD = 0;
       public static int WINDOWLOOKUP_IS_CALLED_IN_TABLE = 1;
       
    public static int IS_UNIQUE_WHILE_DATAENTRY = 0;
    public static int IS_UNIQUE_BEFORE_SAVING = 1;       
       
       
      public static final boolean UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_NO = false;
      public static final boolean UNIQUE_FIELDS_WHILE_DATAENTRY_EDITABLE_YES = true;
      public static final boolean UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_NO = false;
      public static final boolean UNIQUE_FIELDS_BEFORE_SAVE_EDITABLE_YES = true;
      
  
       public static final int DBFIELD_TYPE_OF_SUM_SUM = 0;
       public static final int DBFIELD_TYPE_OF_SUM_COUNT = 1;
       
       public static final int GROUP_OF_PANEL_NOT_VISIBLE = 0; // when it has txtboxes that are pks and we wouldn't like to show them
       public static final int GROUP_OF_PANEL_VISIBLE = 1;
       
       public static final int FIELD_VISIBLE_AND_EDITABLE = 0;
       public static final int FIELD_VISIBLE_NOT_EDITABLE_ALWAYS = 1;
       //public static final int FIELD_VISIBLE_NOT_EDITABLE_WHENNEW = 0;
       public static final int FIELD_VISIBLE_NOT_EDITABLE_WHENEDIT_BUT_EDITABLE_ON_NEW = 2;
       public static final int FIELD_NOT_VISIBLE = 3;
       
       public static final int FIELDSCALCULATION_CATEGORY_BACKWARD = -1;
       public static final int FIELDSCALCULATION_CATEGORY_SAME = 0;
       //public static final int FIELDSCALCULATION_CATEGORYFIELDS_TABLE =  the no of dbfield that is the table
       
       //EntityUpdateAdditional: updateWhen in EntityInfo // UPDATE_ON_INSERT_ONLY,UPDATE_ON_UPDATE_ONLY, UPDATE_ON_BOTH_INSERT_AND_UPDATE,UPDATE_ON_DELETE
       public static final int NO_UPDATE = 0;
       public static final int UPDATE_ON_INSERT_ONLY = 1;
       public static final int UPDATE_ON_UPDATE_ONLY = 2; // first delete then insert
       public static final int UPDATE_ON_BOTH_INSERT_AND_UPDATE= 3; // not implemented completely
       public static final int UPDATE_ON_DELETE = 4; // first delete then insert
       public static final int UPDATE_ON_PRINT = 5;
       
       public static final int CHILDTABLEINPOSITION_INSIDE_EACH_DATAFIELD_PANEL =0;
       public static final int CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE =1;  // if CHILDTABLEINPOSITION_BORDER_LAYOUT_CENTER_SIZABLE then placed in bottom of form
       
       public static final String STRTABLE_DBCOMPANY ="dbcompany";
       public static final String STRTABLE_DBYEAR ="dbyear"; //  has to be changed in db with dbyearid       
       // prim keys column name in db 
       public static final String STRFIELD_DBCOMPANYID ="dbcompanyid"; // for replace case insensitive use (?i)STRFIELD_DBCOMPANYID
       public static final String STRFIELD_DBYEARID ="dbyearId"; //  has to be changed in db with dbyearid
       public static final String STRFIELD_ISPRINTED = "isPrinted";
       public static final String STRFIELD_SOURCE = "sourceModuleId"; // the db returns 2 from sales, 1 from eso exo
       public static final int SOURCE_SALES = 2;
       public static final int SOURCE_ESOEXO = 1;
       
       public static final int QUERY_READ = 0;
       public static final int QUERY_UPDATE = 1;
       public static final int QUERY_UPDATE_STOREDPROCEDURE =2;
       
  public static final int INTSETTINGSREPORT_SHOWINALLPAGES = 0;
  public static final int INTSETTINGSREPORT_SHOWFIRSTPAGE = 1;
  public static final int INTSETTINGSREPORT_NOTSHOW = 2;
      
  public static final int LOOKUPTYPE_NOLOOKUP = 0;    
  public static final int LOOKUPTYPE_ONLYONE_THISFIELD = 1;         
  //public static final int LOOKUPTYPE_TWO_FIRSTFIELD = 2;        //like  buyerId the id
  //public static final int LOOKUPTYPE_TWO_SECONDFIELD = 3;       // like buyertitle  
  public static final int LOOKUPTYPE_TABLECONSTANTS = 4;
  
    public static final String KEYSTROKE_F_LOOKUP_SHOW = "F4";
     
    
    public static final int TYPE_OF_VIEWANDORDER_TABLE= 0;
    public static final int TYPE_OF_VIEWANDORDER_REPORT= 1;
    
       
    /** First cmbYear that can be selected. */
    public static final int FIRST_YEAR = 1900;

    /** Last cmbYear that can be selected. */
    public static final int LAST_YEAR = 2100;
     
    
    
    public static final int MONTH_DATE_ONLY= 0; // only a specific date
    public static final int MONTH_START = 1; // period from
    public static final int MONTH_END = 2;     // period to
     
    public static final Color CLR_TITLE_BACKGROUND_END = lbl.getBackground().darker().darker().darker();//Color.DARK_GRAY;
    public static final Color CLR_TITLE_BACKGROUND_START = lbl.getBackground().darker();//Color.LIGHT_GRAY;
    public static final Color CLR_TITLE_CAPTION = Color.BLACK;//LIGHT_GRAY;
    public static final String CLR_TITLE_CAPTION_DARK_HTML = "#A9A9A9";//"#FFEFD5";//"#778899";//#BOC4DE";
    
      public static final Color CLR_HIGHLIGHT = new Color(255, 242, 201);    //  same as CLR_ROW_SELECTED_CELL
      	
      public static final Color CLR_TOOLTIP = new Color(255, 255, 225);// Color.white;
                                                          //  tooltip  new Color(255, 255, 225);
        public static final Color CLR_TABLE_GRID =  new Color(235,235,232);//172, 168, 153);
        public static final Color CLR_ROW_SELECTED_CELL = new Color(255, 255, 225);   //  same as  CLR_HIGHLIGHT
        public static final Color CLR_ROW_ODD = new Color(255,255,255);////255, 255, 225);   
    	public static final Color CLR_ROW_EVEN = new Color(247,247,247);//(236, 233, 216); 

  public static final Color CLR_PANEL_START_ALTER = new Color(114, 166, 252);//(127,158,174);// green accountant//(114, 166, 252);//(129, 169, 226) winXPform;// (240,224,160); accountant bright // (114, 166, 252); blue
  public static final Color CLR_PANEL_END_ALTER = new Color(196,218,246);//(177,197,207);// green accountant //(196,218,246);// winXPform//(255,200,000); // accountant  dark //new Color(196,218,246); blue

  public static final Color CLR_PANEL_START = new Color(114, 166, 252);// green accountant//(114, 166, 252);//(129, 169, 226) winXPform;// (240,224,160); accountant bright // (114, 166, 252); blue
  public static final Color CLR_PANEL_END = new Color(196,218,246);// green accountant //(196,218,246);// winXPform//(255,200,000); // accountant  dark //new Color(196,218,246); blue
  public static final Color CLR_PANEL_BORDER = new Color(045,104,202);// green accountant //(196,218,246);// winXPform//(255,200,000); // accountant  dark //new Color(196,218,246); blue
 
  public static final Color CLR_PANEL_CARD_START = new Color(114, 166, 252);// Color.white;new Color(114, 166, 252);// green accountant//(114, 166, 252);//(129, 169, 226) winXPform;// (240,224,160); accountant bright // (114, 166, 252); blue
  public static final Color CLR_PANEL_CARD_END = new Color(255, 255, 255);// Color.white;new Color(196,218,246);// green accountant //(196,218,246);// winXPform//(255,200,000); // accountant  dark //new Color(196,218,246); blue
  public static final Color CLR_PANEL_CARD_BORDER =  lbl.getBackground();//  cyan-blue-light new Color(114, 166, 255);// Color.white;     new Color(045,104,202);// green accountant //(196,218,246);// winXPform//(255,200,000); // accountant  dark //new Color(196,218,246); blue
   
       
        public static final Color CLR_LBL_ROLL = new Color(255, 242, 201); //new Color(196,218,246);//(220,235,253); //(114, 166, 232);//210,210,240);
        public static final Color CLR_LBL_ROLL_BORDER = new Color(0, 0, 0);//Color.blue;//new Color(129, 169, 226);//Color.blue;
        public static final Color CLR_LBL_NORMAL = lbl.getBackground();
        public static final Color CLR_LBL_NORMAL_BORDER = CLR_PANEL_START;        
        public static final Color CLR_LBL_CLICKED = new Color(255, 143, 89); //Color.white;//lbl.getBackground();
        public static final Color CLR_LBL_CLICKED_BORDER = new Color(0, 0, 0);//Color.white;


 
  public static final Color CLR_BUTTONPANEL_START = CLR_PANEL_END;//new Color(255, 242, 201);//yellow
  public static final Color CLR_BUTTONPANEL_END = CLR_PANEL_START;//new Color(255, 210, 148);// yellow
   
  public static final Color CLR_BUTTONPANEL_ROLLOVER_START = new Color(255, 242, 201);//yellow  //  new Color(255, 143, 89); 
  public static final Color CLR_BUTTONPANEL_ROLLOVER_END = new Color(255, 210, 148);// yellow   // new Color(255, 143, 89); 
  
  public static final Color CLR_BUTTONPANEL_PRESSED = new Color(204, 67, 0); 	
  public static final Color CLR_BUTTONPANEL_CAPTION = new Color(40, 40, 40);
  public static final Color CLR_BUTTONPANEL_CAPTION_ROLLOVER = new Color(0,0,0);//255, 255, 255);

  public static final Color CLR_BORDER_INEDIT = Color.BLACK;//LIGHT_GRAY;
    //public static final boolean SHOW_SQL = true;
    // used in panel report and search
  //                                 0 = nothing
    
    
   public static final int DBENGINE_MARIADB =1;
   public static final int DBENGINE_MYSQL =2;
    public static final int DBENGINE_H2EMBEDDED =3;  
    public static final int DBENGINE_H2SERVER =4;      
    public static final int DBENGINE_POSTGRESQL =5;    
    public static final int DBENGINE_DERBYEMBEDDED =6;    
    public static final int DBENGINE_SQLITE = 7;	
    public static final int DBENGINE_MSSQLSERVER =8;  
    public static final int DBENGINE_HSQL =9;  
    //  if add then remember to also add engine to DialogSetupDb.loadConfigFromFile
    
    public static final int PANEL_FILTER_SEARCH =1;
    public static final int PANEL_FILTER_REPORT =2;
    public static final int PANEL_FILTER_TASK =3;
    public static final int PANEL_FILTER_CALCULATE_DIALOG =4;// ιε δοψθμεντσ λικε περιοδικι ανδ μυφ
    
    
    public static final int LOOKUP_TYPE_DATE =1;
    
    public static final int GRAPH_TYPE_PIE=1;
    public static final int GRAPH_TYPE_BAR3D=2;
    public static final int GRAPH_TYPE_LINE=3;
    
    // 0 on error
    public static final int ENTITY_TYPE_DATAENTRY = 1;
    public static final int ENTITY_TYPE_DATAMANY_PARAMETERS = 2;
    public static final int ENTITY_TYPE_REPORT = 3;
    public static final int ENTITY_TYPE_PARAMETER = 4;
    public static final int ENTITY_TYPE_STATISTICS = 5;
    public static final int ENTITY_TYPE_TASK = 6;
    public static final int ENTITY_TYPE_DOCKABLEGRAPH = 7;
    public static final int ENTITY_TYPE_TOOL = 8;
    public static final int ENTITY_TYPE_DOCUMENT = 9;
    
    //public static final int ENTITY_TYPE_CATEGORY = 10;
    public static final int ENTITY_TYPE_CATEGORY1 = 11;//reports
    public static final int ENTITY_TYPE_CATEGORY2 = 12; //reports
    public static final int ENTITY_TYPE_CATEGORY3 = 13;//reports
    public static final int ENTITY_TYPE_SECTION = 14;


    public static final int PANEL_TYPE_ONEDATAMANYREC = 1;
    public static final int PANEL_TYPE_ONEDATAONEREC = 2;
    public static final int PANEL_TYPE_MANYDATAMANYREC_PARAMETERS = 3;
    
    public static final int PANEL_TYPE_EDITONEDATAONEREC=5;
    public static final int PANEL_TYPE_STATISTICS = 6;
    public static final int PANEL_TYPE_REPORT = 7;
    public static final int PANEL_TYPE_TASK = 8;
    public static final int PANEL_TYPE_SCOREBOARD = 9;
    public static final int PANEL_TYPE_CALCULATIONDOCFILTER = 10;
    public static final int PANEL_TYPE_COPY_FROM_OTHERCOMPANY = 11;
    public static final int PANEL_TYPE_TOOL = 12;
    public static final int PANEL_TYPE_ANY = 50; // used in dispalyPrintDialog in PanelODOR
    
    public static final int DOTMATRIX_CHARS_PER_LINE_A4_CPI10=80;
    final int DOTMATRIX_CHARS_PER_LINE_A4_CPI15=118;
    public static final int DOTMATRIX_CHARS_PER_LINE_A3=135;
 //  final int DOTMATRIX_LINES_PER_PAGE=47;
    public static final String DOTMATRIX_LINE_CHAR_HEADER="=";
    //public static final String DOTMATRIX_LINE_CHAR="=";
    
    public final String FILE_DATAEDIT_PREFERENCES= "prefsDataEdit.xml";
    public final String FILE_TABLEPREFERENCES = "prefsTable.xml";
    public final String FILE_REPORTPREFERENCES = "prefsReport.xml";
    

   public final String STRPAGESIZE_A5="A5";
   public final String STRPAGESIZE_A4="A4";
   public final String STRPAGESIZE_B4="B4";
   public final String STRPAGESIZE_A3="A3";
    public final String[] STRARRAYPAGESIZELASER = { STRPAGESIZE_A5,STRPAGESIZE_A4,STRPAGESIZE_B4, STRPAGESIZE_A3};

    public final  String ORIENTATION_LANDSCAPE="οριζόντια σελίδα";
    public final String ORIENTATION_PORTRAIT="κατακόρυφη σελίδα";
   
    
    public final String FILE_REPORTFORMTEMPLATE_DOTMATRIX = "dotmatrix.txt";
    public final String FILE_REPORTFORMTEMPLATE_LASER = "laser.xml";
    public final String FILE_REPORTFORMTEMPLATE_FIELDS = "fields.xml";
    public final String TEMPLATE_REPORTFORMFILETYPE = ".zip";    
    public final String DIR_REPORTDOCUMENTTEMPLATE = "reports";
   
    public final String DIR_DATABACKUP = "backup";
    public static final String DIR_EXT_POOL_PICTURES = "pictures";
    
    //public final String FILE_DBVERSION_INFO =  "propsdbinfo.txt";
    
       /* 
       *   exist both in DialogUpdateProcess(Update project) and Constants
       */
    public static final String DIR_DATAUPDATES = "updates";
    public static final String UPDATE_JAR = "update.jar";
    public static final String FILE_FOR_UPDATE = "update.zip"; 
    /**/    
    public static final String UPDATE_DB_PREFIX = "db";
    public static final String UPDATE_DB_POSTFIX = ".sql"; 
    public static final String UPDATE_DB_ZIPPEDPOSTFIX = ".zip"; 
    
    
    public static final String TXT_CHANGELINE = "\r\n";
    public static final String TXT_CHANGEPAGE = "\f";
    public static final String TXT_ENDOFSQL = ";";
    public static final String TXT_SQLSTARTCOMMENTS1 = "--";
    public static final String TXT_SQLSTARTCOMMENTS2 = "/*";

    /*public static final String REPORT_CAT_ECONOMIC = "economic";
    public static final String REPORT_CAT_DOCUMENTS = "file-form";
    public static final String REPORT_CAT_INFORMATIVE = "informative";
    public static final String REPORT_CAT_CATALOGS = "catalogs";*/
    //public static final String REPORT_CAT_CUSTOM = "customized";    
   // public static final String[] REPORTS_CAT_ARRAY = {REPORT_CAT_ECONOMIC,REPORT_CAT_APPLICATIONS,REPORT_CAT_INFORMATIVE};    
    
    public static final String ROOT = "ROOT";
    public static final String DATAENTRY = "καταχώρηση";
    public static final String REPORTS = "εκτυπώσεις";
    public static final String PERFORMANCEMEASUREMENT = "μέτρηση απόδοσης";
    public static final String DOCUMENTS = "έντυπα";
    public static final String METRICS = "δείκτες";
    public static final String METRICS_SERSALES_OFCOMPANY = "δείκτες εταιρίας";
    public static final String METRICSOF_SALES = "δείκτες πωλήσεων"; 
    //public static final String METRICS_ESOEXO_OFCOMPANY = "δείκτες εταιρίας";
    public static final String METRICSOF_ESOEXO = "δείκτες εσόδων εξόδων";
    public static final String METRICSOF_ESOEXO_ACC = "δείκτες λογαριασμών";    
    
    public static final String PARAMETERS = "παράμετροι";   
    public static final String PARAMETERSOFSYSTEM = "παράμετροι"; //  συστήματος
    public static final String TOOLS = "εργαλεία";  
    public static final String MAINTENANCE = "συντήρηση";
    public static final String MAINTENANCEOFSYSTEM = "συντήρηση"; // συστήματος
    //public static final String[] ENTITIES_CAT_ARRAY_FOR_TREE = {DATAENTRY,REPORT_CAT_ECONOMIC,REPORT_CAT_APPLICATIONS,REPORT_CAT_INFORMATIVE,PARAMETERS,METRICS};
   // public static final int [] REPORTS_NO_IN_ARRAY_FOR_TREE = {1,2,3};
    public static final String[] ENTITIES_CAT_ARRAY = {DATAENTRY,REPORTS,PARAMETERS,METRICS,};
    public static final int REPORTS_NO_IN_ARRAY_FOR_MENU = 1;
    
    public static final String REPORT_STRING_ON_TITLE_OF_TAB="";
    
    public static final int ENTITYREPORT_QUERY_TYPE_MAIN = 0;
    //public static final int ENTITYREPORT_QUERY_TYPE_GROUP = 1;
    public static final int ENTITYREPORT_QUERY_TYPE_ADDITIONAL = 1;
    
    /*public static final int ENTITYREPORTTYPE_LIST = 0; "ODMR" and more
    public static final int ENTITYREPORTTYPE_FORM = 1;  "FORM"  */
    
    
    
    public static final String FILE_DB_CONFIG = "propsdb.txt";
    public static final String FILE_CONFIG = "props.txt";
    
    
    
    public static final String FILE_REPORT_TXT = "report.txt";
    //public static final String FILE_REPORT_HTML = "report.html";
    //public static final String FILE_REPORT_CSS = "report.css";
    public static final String FILE_PRINTERS = "printers.xml";
    
     public static final int WINDOW_LOCATION_CENTER = 0;
     public static final int WINDOW_LOCATION_DOWNRIGHT = 1;
     public static final int WINDOW_LOCATION_COMPONENT = 2;
    
    public static final int PRINTPREVIEW_SHOW_NONE = 1;
    public static final int PRINTPREVIEW_SHOW_ALL = 2;
    public static final int PRINTPREVIEW_SHOW_ONLY_DOTMATRIX = 3;
    public static final int PRINTPREVIEW_SHOW_ONLY_HTML = 4;
    
    public static final int REPORT_TYPE_GENERATED = 1;
    public static final int REPORT_TYPE_GENERATED_FROM_FILE = 2;
    public static final int REPORT_TYPE_COMPILED = 3;


    public static final int FIELD_NOCOMPLETION = 0;
    public static final int FIELD_OBLIGATORY = 1;
    public static final int FIELD_SUGGEST = 2;
    public static final int FIELD_TABLE_NOROWCOMPLETION = 3;
    public static final int FIELD_TABLE_ONEROWATLEAST_OBLIGATORY = 4;
    public static final int FIELD_TABLE_ONEROWATLEAST_SUGGEST = 5;
    
    public static final int FIELD_NORMAL_NO_PRIMARY_KEY=0;
    public static final int FIELD_PRIMARY_KEY_AUTOINC=1;
    public static final int FIELD_PRIMARY_KEY=2;
    public static final int FIELD_PRIMARY_KEY_FROM_PARENTTABLE=3;// like farmerid in telephones
            
    public static final int FIELD_VALIDATION_NO = 10;
    public static final int FIELD_VALIDATION_AFM = 11;
    public static final int FIELD_VALIDATION_AMKA = 12;
    
    
    public static final ImageIcon ICO_BOOKMARK = new ImageIcon(Constants.class.getResource("/images/bookmark16.png"));

    public static final Image IMG_BOOKMARK = ICO_BOOKMARK.getImage(); // for frame icon

    
    
    public static final String STR_SQL_SHOW_PROCESSLIST = "SHOW PROCESSLIST;";
    public static final int INT_SQL_SHOW_PROCESSLIST_SECONDS = 400;
    
    //public static final String STR_ICO_PRINTER = "/images/print16.png";
    //public static final String STR_ICO_EXPORT ="/images/export16.png";
    //public static final String STR_ICO_EDIT = "/images/edit16.png";
    //public static final String STR_ICO_LOOK = "/images/look16.png";
    
    public static final ImageIcon APP_LOGO = null  ;//new ImageIcon(Constants.class.getResource("/images/logo.png"));
    //public static final ImageIcon ICO_LOGIN = new ImageIcon(Constants.class.getResource("/images/login.png"));
    
    
    public static final ImageIcon ICO_MENUESODAEXODA = new ImageIcon(Constants.class.getResource("/images/menuesodaexoda.png"));
    public static final ImageIcon ICO_MENUSERVICESALES = new ImageIcon(Constants.class.getResource("/images/menuservicesales.png"));
    public static final ImageIcon ICO_MENUPAYROLL = new ImageIcon(Constants.class.getResource("/images/payroll.png"));
    public static final ImageIcon ICO_MENUFARMERSVAT = new ImageIcon(Constants.class.getResource("/images/menufarmer.png"));
    public static final ImageIcon ICO_MENUSYSTEM = new ImageIcon(Constants.class.getResource("/images/menusystem.png"));
    //public static final ImageIcon ICO_MENUTAMEIO = new ImageIcon(Constants.class.getResource("/images/menutameio.png"));
    public static final ImageIcon ICO_MENUMETRICS = new ImageIcon(Constants.class.getResource("/images/menumetrics.png"));
    
    
    // modules
    public static final ImageIcon ICO_ESODAEXODA = new ImageIcon(Constants.class.getResource("/images/esodaexoda.png"));
    public static final ImageIcon ICO_ESOEXOTEMPLATE = new ImageIcon(Constants.class.getResource("/images/esoexotemp.png"));
    //public static final ImageIcon ICO_FIXEDASSETS = new ImageIcon(Constants.class.getResource("/images/bricks.png"));
    public static final ImageIcon ICO_SYSTEM = new ImageIcon(Constants.class.getResource("/images/system.png"));
    
    
    public static final ImageIcon ICO_BACK = new ImageIcon(Constants.class.getResource("/images/back.jpg"));
    //public static final ImageIcon ICO_BACK_BIG = new ImageIcon(Constants.class.getResource("/images/backbig.jpg"));
    
    //public static final ImageIcon ICO_SETUP_CONFIG = new ImageIcon(Constants.class.getResource("/images/setupconfig.png"));
    public static final ImageIcon ICO_TOOLS = new ImageIcon(Constants.class.getResource("/images/tool.png"));
    //public static final ImageIcon ICO_MENUCAT_TOOLS = new ImageIcon(Constants.class.getResource("/images/foldertool.png"));
    public static final ImageIcon ICO_MENUCATEGORY_EXP_COL = new ImageIcon(Constants.class.getResource("/images/menucategory.png"));
    public static final ImageIcon ICO_MENUCAT_EXPLORE = new ImageIcon(Constants.class.getResource("/images/folderexplore.png"));
    public static final ImageIcon ICO_MENUCAT_EDIT = new ImageIcon(Constants.class.getResource("/images/folderedit.png"));
    public static final ImageIcon ICO_MENUCAT_REPORT = new ImageIcon(Constants.class.getResource("/images/folderreport.png"));
    public static final ImageIcon ICO_MENUCAT_SYSTEM = new ImageIcon(Constants.class.getResource("/images/folderedit.png"));
    public static final ImageIcon ICO_MENUCAT_DOCUMENT = new ImageIcon(Constants.class.getResource("/images/folderdocument.png"));
    
    public static final ImageIcon ICO_SHOWSLIDE = new ImageIcon(Constants.class.getResource("/images/showslide.png"));
    
    public static final ImageIcon ICO_FIELDOBLIGATORY = new ImageIcon(Constants.class.getResource("/images/fieldobligatory.png"));
    public static final ImageIcon ICO_FIELDSUGGEST = new ImageIcon(Constants.class.getResource("/images/fieldsuggest.png"));
    //public static final ImageIcon ICO_FIELDATTENTION = new ImageIcon(Constants.class.getResource("/images/fieldattention.png"));
    
    public static final ImageIcon ICO_TAM_MONEY = new ImageIcon(Constants.class.getResource("/images/money.png"));
    public static final ImageIcon ICO_FARMER16 = new ImageIcon(Constants.class.getResource("/images/farmer.png"));
    public static final ImageIcon ICO_BUYER16 = new ImageIcon(Constants.class.getResource("/images/buyer.png"));
    public static final ImageIcon ICO_PAPER = new ImageIcon(Constants.class.getResource("/images/paper.png"));
    public static final ImageIcon ICO_CHARTBAR = new ImageIcon(Constants.class.getResource("/images/chartbar.png"));
    
    public static final ImageIcon ICO_ADD = new ImageIcon(Constants.class.getResource("/images/add.png"));
    public static final ImageIcon ICO_ADDBELOW = new ImageIcon(Constants.class.getResource("/images/addbelow.png"));
    public static final ImageIcon ICO_DELETE = new ImageIcon(Constants.class.getResource("/images/delete.png"));    
    public static final ImageIcon ICO_COPY_VALUE_FROM_ABOVE_CELL = new ImageIcon(Constants.class.getResource("/images/copyfromabovecell.png"));
    
    public static final ImageIcon ICO_OK16 = new ImageIcon(Constants.class.getResource("/images/ok16.png"));
    public static final ImageIcon ICO_CANCEL16 = new ImageIcon(Constants.class.getResource("/images/cancel16.png"));
    public static final ImageIcon ICO_EXIT16 = new ImageIcon(Constants.class.getResource("/images/exit16.png"));
    public static final ImageIcon ICO_EXCLAMATION16 = new ImageIcon(Constants.class.getResource("/images/exclamation16.png"));
    //public static final ImageIcon ICO_WINDOWNEW16 = new ImageIcon(Constants.class.getResource("/images/windownew.png"));

    public static final ImageIcon ICON_TREEFOLDER_OPENED = new ImageIcon(Constants.class.getResource("/images/TreeFolderOpened.png"));
    //public static final ImageIcon ICON_TREEFOLDER_OPENED14 = new ImageIcon(Constants.class.getResource("/images/TreeFolderOpened14.png"));// for texbtn
    public static final ImageIcon ICON_TREEFOLDER_CLOSED = new ImageIcon(Constants.class.getResource("/images/TreeFolderClosed.png"));
    public static final ImageIcon ICO_TABLE16 = new ImageIcon(Constants.class.getResource("/images/Table16.png"));
    public static final ImageIcon ICO_SETTINGS = new ImageIcon(Constants.class.getResource("/images/settings.png"));
    public static final ImageIcon ICO_STATISTICS16 = new ImageIcon(Constants.class.getResource("/images/Statistics16.png"));
    //public static final ImageIcon ICO_TEMPLATE = new ImageIcon(Constants.class.getResource("/images/template.png"));
    
    public static final ImageIcon ICO_PANELBACK = new ImageIcon(Constants.class.getResource("/images/PanelBack.png"));
    public static final ImageIcon ICO_PANELBACKOK = new ImageIcon(Constants.class.getResource("/images/PanelBackOk.png"));
    public static final ImageIcon ICO_PANELBACKCANCEL = new ImageIcon(Constants.class.getResource("/images/PanelBackCancel.png"));
    public static final ImageIcon ICO_INSERT16 = new ImageIcon(Constants.class.getResource("/images/dbinsert16.png"));
    public static final ImageIcon ICO_SAVEANDNEW16 = new ImageIcon(Constants.class.getResource("/images/dbupdatendnew16.png"));
    public static final ImageIcon ICO_EDIT16 =  new ImageIcon(Constants.class.getResource("/images/dbedit16.png"));
    public static final ImageIcon ICO_DELETE16 =  new ImageIcon(Constants.class.getResource("/images/dbdelete16.png"));
    public static final ImageIcon ICO_UPDATE16 =  new ImageIcon(Constants.class.getResource("/images/dbupdate16.png"));    
    public static final ImageIcon ICO_TASK = new ImageIcon(Constants.class.getResource("/images/task.png"));
    public static final ImageIcon ICO_EDIT14 =  new ImageIcon(Constants.class.getResource("/images/dbedit14.png"));// dbedit14.png)); // for lookups
    public static final ImageIcon ICO_TEMPLATESELECT =  new ImageIcon(Constants.class.getResource("/images/templateselect.png"));
    //public static final ImageIcon ICO_TEMPLATESAVEAS =  new ImageIcon(Constants.class.getResource("/images/templatesaveas.png"));
    public static final ImageIcon ICO_COPY_FROM_OTHER_COMPANY = new ImageIcon(Constants.class.getResource("/images/copyfromothercompany.png"));
    public static final ImageIcon ICO_INSERT_COPY = new ImageIcon(Constants.class.getResource("/images/insertcopy.png"));
    //public static final ImageIcon ICO_DBPREVIOUS16 = new ImageIcon(Constants.class.getResource("/images/dbgoprevio.png"));us16.png"));
    //public static final ImageIcon ICO_DBNEXT16 = new ImageIcon(Constants.class.getResource("/images/dbgonext16.png")); 
    
    
    public static final ImageIcon ICO_LOOKUP = new ImageIcon(Constants.class.getResource("/images/lookup14.png"));//lookup.png"));
    public static final ImageIcon ICO_CALENDAR = new ImageIcon(Constants.class.getResource("/images/calendar.png"));
    public static final ImageIcon ICO_CHECKS = new ImageIcon(Constants.class.getResource("/images/checks.png"));
    public static final ImageIcon ICO_FIND16 = new ImageIcon(Constants.class.getResource("/images/find16.png"));
    public static final ImageIcon ICO_PRINT_PREVIEW16 = new ImageIcon(Constants.class.getResource("/images/printpreview16.png"));
    public static final ImageIcon ICO_PRINT_PREVIEW_FORM16 = new ImageIcon(Constants.class.getResource("/images/printpreviewform16.png"));
    public static final ImageIcon ICO_REPORTDOCUMENT = new ImageIcon(Constants.class.getResource("/images/reportform.png"));
    //public static final ImageIcon ICO_REPORTFILE = new ImageIcon(Constants.class.getResource("/images/reportfile.png"));
    public static final ImageIcon ICO_PRINT16 = new ImageIcon(Constants.class.getResource("/images/print16.png"));
    //public static final ImageIcon ICO_EXPORT16 = new ImageIcon(Constants.class.getResource("/images/export16.png"));
    public static final ImageIcon ICO_TABLE_PREFS16 = new ImageIcon(Constants.class.getResource("/images/TablePrefs16.png"));
    public static final ImageIcon ICO_PAGEPROPERTIES16 = new ImageIcon(Constants.class.getResource("/images/pageproperties16.png"));
    public static final ImageIcon ICO_RELOAD16 = new ImageIcon(Constants.class.getResource("/images/reload16.png"));
    public static final ImageIcon ICO_RELOADB16 = new ImageIcon(Constants.class.getResource("/images/reloadb16.png"));

   // public static final ImageIcon ICO_LOCKED32 = new ImageIcon(Constants.class.getResource("/images/locked32.png"));
    public static final ImageIcon ICO_USER16 = new ImageIcon(Constants.class.getResource("/images/user16.png"));
    public static final ImageIcon ICO_CONFIG16 = new ImageIcon(Constants.class.getResource("/images/config16.png"));
    public static final ImageIcon ICO_PARAMETERS16 = new ImageIcon(Constants.class.getResource("/images/parameters16.png"));    
    //public static final ImageIcon ICO_BACKUP16 =  new ImageIcon(Constants.class.getResource("/images/backup16.png"));    
    public static final ImageIcon ICO_KEYS16 = new ImageIcon(Constants.class.getResource("/images/keys16.png"));
    public static final ImageIcon ICO_HELP16 = new ImageIcon(Constants.class.getResource("/images/help16.png"));
    public static final ImageIcon ICO_ABOUT16 = new ImageIcon(Constants.class.getResource("/images/about16.png"));
    public static final ImageIcon ICO_SYSTINFO16 = new ImageIcon(Constants.class.getResource("/images/systinfo16.png"));
    //public static final ImageIcon ICO_EXIT16 = new ImageIcon(Constants.class.getResource("/images/exit16.png"));
    public static final ImageIcon ICO_INFO16 = new ImageIcon(Constants.class.getResource("/images/info16.png"));
   
   
    public static final ImageIcon ICO_FIRST16 = new ImageIcon(Constants.class.getResource("/images/gofirst16.png"));
    public static final ImageIcon ICO_LAST16 = new ImageIcon(Constants.class.getResource("/images/golast16.png"));
    public static final ImageIcon ICO_PREVIOUS16 = new ImageIcon(Constants.class.getResource("/images/goprevious16.png"));
    public static final ImageIcon ICO_NEXT16 = new ImageIcon(Constants.class.getResource("/images/gonext16.png")); 
    
    public static final ImageIcon ICO_BACKUP = new ImageIcon(Constants.class.getResource("/images/backup.png")); 
    public static final ImageIcon ICO_RESTORE = new ImageIcon(Constants.class.getResource("/images/restore.png")); 
    
    //public static final ImageIcon ICO_REPORTFORMSETTINGS = new ImageIcon(Constants.class.getResource("/images/reportformsettings.png")); 
    
    //public static final ImageIcon ICO_ZOOMOUT = new ImageIcon(Constants.class.getResource("/images/zoomout.png")); 
    //public static final ImageIcon ICO_ZOOMIN = new ImageIcon(Constants.class.getResource("/images/zoomin.png")); 
    public static final ImageIcon ICO_CLEARTABLE = new ImageIcon(Constants.class.getResource("/images/cleartable.png")); 
    /*public static final ImageIcon ICO_INFO22 = new ImageIcon(Constants.class.getResource("/images/info22.png"));
    public static final ImageIcon ICO_USER22 = new ImageIcon(Constants.class.getResource("/images/user22.png"));
    public static final ImageIcon ICO_MANAGEMENT22 = new ImageIcon(Constants.class.getResource("/images/Table22.png"));
    public static final ImageIcon ICO_CONFIG22 = new ImageIcon(Constants.class.getResource("/images/config22.png"));
    public static final ImageIcon ICO_HELP22 = new ImageIcon(Constants.class.getResource("/images/help22.png"));
    public static final ImageIcon ICO_ABOUT22 = new ImageIcon(Constants.class.getResource("/images/about22.png"));
    public static final ImageIcon ICO_SYSTINFO22 = new ImageIcon(Constants.class.getResource("/images/systinfo22.png"));
    public static final ImageIcon ICO_EXIT22 = new ImageIcon(Constants.class.getResource("/images/exit22.png"));*/

    /*public static final String APPLICATION = "DBTool";
    public static final String VERSION = "0.18";
    public static final String VERSIONYEAR = "2010";*/

/*

	public static final ImageIcon JMONEY_IMAGE =
		new ImageIcon(Constants.class.getResource("images/jmoney_logo.png"));

	public static final ImageIcon ACCOUNTS_ICON =
		new ImageIcon(Constants.class.getResource("images/Accounts.gif"));

*/

	/**
	 * The language resource bundle.
	 */
//	public static ResourceBundle LANGUAGE =
//		ResourceBundle.getBundle("net.sf.jmoney.resources.Language");

	/**
	 * The general resource bundle.
	 */
//	public static ResourceBundle GENERAL =
//		ResourceBundle.getBundle("net.sf.jmoney.resources.General");



}
