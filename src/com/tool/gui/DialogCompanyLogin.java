
  package com.tool.gui;
   
import com.tool.model.EntityGroupOfComps;
import com.tool.model.EntityGroupOfPanels;
import com.tool.model.EntityPanel;
  import com.tool.guicomps.*;
  import com.tool.jdbc.*;
  import com.tool.utils.*;
  import com.tool.model.*;
  

  import javax.swing.text.DefaultFormatter;


  import java.awt.Frame;
  import java.awt.BorderLayout;
  import java.awt.FlowLayout;
  import java.awt.event.ActionListener;
  import java.awt.event.ActionEvent;
  import java.awt.event.KeyEvent;
  import java.awt.Dimension;
  import java.awt.Color;
  import java.awt.event.MouseAdapter;
  import java.awt.event.MouseEvent;
  import java.awt.Font;
  import java.awt.GridLayout;
  import java.awt.event.KeyEvent;
   import java.awt.event.WindowAdapter;
 import java.awt.event.WindowEvent;
  import java.awt.KeyboardFocusManager;
  
  import java.sql.ResultSet;
  import java.sql.ResultSetMetaData;
  import java.sql.SQLException;

  import javax.swing.*;
  import javax.swing.event.ListSelectionListener;
  import javax.swing.event.ListSelectionEvent;
  
  import java.util.Calendar;
  import java.util.GregorianCalendar;
  import java.util.Set;
  import java.util.HashSet;

 
  import java.util.Vector;
  
public class DialogCompanyLogin extends JDialog implements Constants
{
	private JPanelCurvedGradient paneTitle;
	private JButtonForPanelDecorated btnClose;
    private JButtonForPanelDecorated btnOk;
    private JButtonForPanelDecorated btnCreateCompany;
    private JButtonForPanelDecorated btnDeleteCompany;
    private JButtonForPanelDecorated btnCreateYear;
    private JButtonForPanelDecorated btnDeleteYear;
    //private JLabel lblPassword;
    private JLabel lblCompany;
    private JLabel lblDate;
    private JLabel lblTitle;
    //private JLabel lblUser;
    private JLabel lblYear;
    //private JListDec listCompany;
    private JListDec listYear;
    private JxPanel panelBottom;
    private JxPanel panelActionButtons;    
//    private JxPanel panelBottomCompany;
//    private JxPanel panelBottomYear;
     
    private JPanelDecorated panelAllOnIt;
    private JxPanel panelMain;
    private String userId="";
    private JScrollPane scrollpaneCompany;
    private JScrollPane scrollpaneYear;
    //private JTextField txtDate;
   // private JTextField txtPassword;
    //private JTextField txtUser;
    private JTextField txtYear;  // in joptionpane  createYear()
    private JTableDec table;
    private ListSelectionModel lsm;
    private TableModelReadOnly tableModel;
    private DatabaseTableMeta databaseTableMeta;
    //private PanelOneDataManyRecData panelOneDataManyRecData;

	private DialogMain dialogMain;
	private boolean isExit = false;
    private Database db= new Database();
    
    private boolean isOkClicked=false;
   
    private static DialogCompanyLogin myInstance; // singleton pattern
    private UtilsTable utilsTable;
    private UtilsGui utilsGui;
    private UtilsDate utilsDate;
    private UtilsString utilsString;
    //   private JxFormattedTextFieldDate.FormatSpec formatSpec;
    private JTextBoxWithEditButtons txtDate;
    
    
    /** Creates new form LoginForm */
    private DialogCompanyLogin(JFrame parent)// private singleton patterm (Frame parent, boolean modal) 
    {
        super(parent,VariablesGlobal.appName+"   "+UtilsResource.getString("Login"),true);//super(parent, modal);
       // System.out.println("loading DialogCompanyLogin");
        initialize( parent);
       //	System.out.println("loaded DialogCompanyLogin");


    }

    public DialogCompanyLogin()// private singleton patterm (Frame parent, boolean modal) 
    {
        // not load  initComponents() but utilsGui
        this.setIconImage(IMG_BOOKMARK);
          utilsGui = new UtilsGui();
          utilsString = new UtilsString();
    }

    public DialogCompanyLogin(java.awt.Dialog parent)// private singleton patterm (Frame parent, boolean modal) 
    {
        super(parent,VariablesGlobal.appName+"   "+UtilsResource.getString("Login"),true);//super(parent, modal);
       // System.out.println("loading DialogCompanyLogin");
        initialize( null);
    }

    public static DialogCompanyLogin getInstance(JFrame parent)
    {
        if (myInstance == null)
        {
            if(parent==null)
            {
                myInstance = new DialogCompanyLogin((java.awt.Dialog)null);//new DialogCompanyLogin((java.awt.Dialog)null);// is used to show dialog in taskbar
            }
            else
            {
               myInstance = new DialogCompanyLogin(parent);// is used to get the same dialog (singleton pattern)
            }
        }
        return myInstance;
    }

    /**
     * Initialization.
     */      
    private void initialize(JFrame parent) 
    {
        this.setIconImage(IMG_BOOKMARK);
    	utilsTable = new UtilsTable();
    	utilsString = new UtilsString();
    	utilsGui = new UtilsGui();
    	utilsDate = new UtilsDate();
        utilsDate.readFromFileDateFormats();
        
        lblTitle = new JLabel();
        panelMain = new JxPanel(); 
        //panelMain = new JxPanel();
        panelActionButtons = new JxPanel();        
        lblCompany = new JLabel();

        lblYear = new JLabel();
        scrollpaneCompany = new JScrollPane();
        scrollpaneYear = new JScrollPane();
        // = new JListDec();
        lblDate = new JLabel();
        //txtDate = new JTextField();
        panelBottom = new JxPanel();

        btnOk = new JButtonForPanelDecorated();
        btnClose = new JButtonForPanelDecorated();
        btnCreateCompany = new JButtonForPanelDecorated();
        btnDeleteCompany = new JButtonForPanelDecorated();
        btnCreateYear = new JButtonForPanelDecorated();
        btnDeleteYear = new JButtonForPanelDecorated();

      //  setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        setResizable(true);

        lblTitle.setText("::::::: "+VariablesGlobal.appName+" :: :: ver.:"+VariablesGlobal.appLeadVersion+"."+VariablesGlobal.appSubVersion+" :::::::");//(UtilsResource.getString("Login"));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD));        
        //lblTitle.setForeground(Color.white);
        //lblTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));//top,left,bottom,right
        
        //lblTitle.setIcon(ICO_LOCKED32);  //ICO_BACK
              Color blue= new Color(129, 169, 226);
              //Color blue= new Color(63, 183, 255);
              Color lightBlue= new Color(196,218,246);//220,235,253);//148, 215, 254);
            //lightblue =  lightblue.brighter().brighter();
        paneTitle = new JPanelCurvedGradient(CLR_PANEL_START_ALTER,CLR_PANEL_END_ALTER,0,0);//PanelGradient(lblTitle.getBackground().brighter().brighter(),lblTitle.getBackground().darker(),60);//new PanelTitle(new Color(0, 0, 0, 0),this.getBackground().darker().darker().darker().darker(),Color.white,"title");
        paneTitle.add(lblTitle);

        //panelMain.setLayout(new AbsoluteLayout()); //absoluteLayout
        panelMain.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 5));

        int topOfLabels = 10;
        int topOfComponents = 25;
        int leftOfRightComps = 325;
         listYear = new JListDec();
        //panelMain.setPreferredSize(new Dimension(410, 220));
        panelMain.setBorder(BorderFactory.createEmptyBorder(5,10, 5, 10));
        
        lblCompany.setText(UtilsResource.getString("Company"));
        lblCompany.setOpaque(false);
        panelMain.add(lblCompany);//, new AbsoluteConstraints(100, topOfLabels, -1, -1));
        panelMain.add(new JLabel(""));
        lblYear.setText("");
        panelMain.add(lblYear);//, new AbsoluteConstraints(270, topOfLabels, -1, -1));
        panelMain.add(new JLabel(""));

        JPanel panelTextBox  = new JPanel();
        panelTextBox.setOpaque(false);
        panelTextBox.setLayout(new FlowLayout());//new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 1));
        
        panelMain.add(new JLabel(""));


        
        tableModel= new TableModelReadOnly();
        table = new JTableDec(tableModel);
        //table.setBackground(lightBlue);
        databaseTableMeta = new DatabaseTableMeta(); 
        // not delete it:  cannot format given object as a number
        TableCellRendererDefault tcr = new TableCellRendererDefault();
        TableCellRendererInteger tcrInteger = new TableCellRendererInteger();
        
        table.setEntity(false);
        
        table.setDefaultRenderer(Object.class, tcr);
        table.setDefaultRenderer(Integer.class, tcrInteger);
        table.setDefaultRenderer(Number.class, tcr);
        table.setDefaultRenderer(Double.class, tcr); 
        table.setDefaultRenderer(java.util.Date.class, tcr); 
        
        table.setShowVerticalLines(true);        
        table.setShowHorizontalLines(true);
        table.setAutoResizeMode(JTableDec.AUTO_RESIZE_OFF);
        table.setGridColor(CLR_TABLE_GRID);

        /* table.setColumnSelectionAllowed(true);*/
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// select a single row only       
        table.setOpaque(true);
        //table.setSurrendersFocusOnKeystroke(true);

           lsm = table.getSelectionModel();
            //rowSM.addListSelectionListener(new ListSelectionListener() {
            lsm.addListSelectionListener(new ListSelectionListener()
            {
                public void valueChanged(ListSelectionEvent e)
                {
                    //Ignore extra messages.
                    if (e.getValueIsAdjusting()) return;
                                        
                    lsm = (ListSelectionModel)e.getSource();
                    if (lsm.isSelectionEmpty())
                    {
                        //System.out.println(UtilsResource.getString("PleaseSelectARow"));
                    } else
                    {   
                      /*  selectedTableRow = lsm.getMinSelectionIndex();                   
                        selectedDataRow = selectedTableRow+1; //by default 1s selectedrow is 0. since there is no 0 record, we add 1
                         */       
            //     string of selected row
                     //conver object toString then parseInt to integer
                      int key = 0;
                      if(tableModel.getRowCount()>0)
                      {
                        key = Integer.parseInt((tableModel.getValueAt(lsm.getMinSelectionIndex(),0).toString())) ;
                        retrieveYearsForCompany(key);
                      }
                      else
                      {
                          
                      }
                              
                    }
                }
            });
        
        //table.setUI(new TableUICustom());
        scrollpaneCompany.setViewportView(table);
        

        retrieveCompanies();
        
        lsm.setSelectionInterval(0,0); // select the first one
        
        panelMain.add(scrollpaneCompany);//, new AbsoluteConstraints(20, topOfComponents, 220, 187));
        
        JPanel panelVerticalBorder1 = new JPanel();
        panelVerticalBorder1.setPreferredSize(new Dimension(10, 220));
        panelVerticalBorder1.setOpaque(false);
        panelMain.add(panelVerticalBorder1 );
         
        scrollpaneYear.setViewportView(listYear);
        listYear.setEnabled(false);
        

        scrollpaneYear.setPreferredSize(new Dimension(140, 220));


        panelMain.add(scrollpaneYear);//, new AbsoluteConstraints(258, topOfComponents, 50, 187));


        JPanel panelVerticalBorder2 = new JPanel();
        panelVerticalBorder2.setPreferredSize(new Dimension(10, 220));
        panelVerticalBorder2.setOpaque(false);
        panelMain.add(panelVerticalBorder2 );


        lblDate.setText(UtilsResource.getString("Date"));
        panelTextBox.add(lblDate);//, new AbsoluteConstraints(leftOfRightComps+15, 100, -1, -1));


        //String dayArray[] = {"Κυριακή","Δευτέρα","Τρίτη","Τετάρτη","Πέμπτη","Παρασκευή","Σαββάτο" };
        GregorianCalendar cal = new GregorianCalendar();

        int dayInt = cal.get(Calendar.DAY_OF_WEEK);
        int d = cal.get(Calendar.DAY_OF_MONTH);
        int m = cal.get(Calendar.MONTH)+1;  // plus 1. dont know why
        int y = cal.get(Calendar.YEAR);
        //String day = dayArray[dayInt-1];//dayArray starts from 0
        
        String strDateEditing = utilsDate.getDateFormatEditing();
        String today = "";//d+"-"+m+"-"+y;
        if(strDateEditing.equalsIgnoreCase("dd-MM-yyyy"))
        {
            today = d+"-"+m+"-"+y;
        }
        else if(strDateEditing.equalsIgnoreCase("MM-dd-yyyy"))
        {
            today = m+"-"+d+"-"+y;
        }
        else if(strDateEditing.equalsIgnoreCase("yyyy-MM-dd"))
        {
            today = y+"-"+m+"-"+d;
        }
        else if(strDateEditing.equalsIgnoreCase("EEE dd-MM-yyyy"))
        {
            System.out.println(" ERROR DialogCompanyLogin.initialize UNKNOWN strDateEditing:"+strDateEditing+" EEE dd-MM-yyyy");
        }        
        
        

        
        
    
    JFormattedTextField textFormattedDate = new JFormattedTextField();
    
    //textFormattedDate.setPreferredSize(new Dimension(80,textFormattedDate.getHeight()));
    //((DefaultFormatter) textFormattedDate.getFormatter()).setCommitsOnValidEdit(true);
    //((DefaultFormatter) textFormattedDate.getFormatter()).setAllowsInvalid(false);

     


       int intColumnWidthDate = 8;//utilsDate.getDateFormatEditing().length();		   
       //JxFormattedTextFieldDate textFormatedDate = new JxFormattedTextFieldDate(intColumnWidthDate,formatSpec);
       txtDate = new JTextBoxWithEditButtons(textFormattedDate, true,ICO_CALENDAR,null,false,null,null,LOOKUP_TYPE_DATE, parent,"","",MONTH_DATE_ONLY);               	
         
       String[]pattern =utilsDate.allowedPatternsToReadFromGui;
       
       System.out.println("DialogCompanyLogin.initialize "+today+"   "+pattern[0]+" "+pattern[1]+" "+pattern[2]+" "+pattern[3]+"  :"+utilsDate.reformatDateStringToEdit(today,pattern));
       
       

        txtDate.setText(utilsDate.reformatDateStringToEdit(today,pattern));  
        
        panelTextBox.add(txtDate.getComponent());//, new AbsoluteConstraints(leftOfRightComps-5, 115, 100, -1));
      

        panelBottom.setLayout(new FlowLayout());
        panelBottom.setBorder(BorderFactory.createEmptyBorder(0, 3, 3, 3));
        
        panelActionButtons.setLayout(new BoxLayout(panelActionButtons, BoxLayout.X_AXIS));

        btnOk.setIcon(ICO_OK16);
        btnOk.setFocusable(false);
        btnOk.setText("<html>"+UtilsResource.getString("OK")+" <b>F5</b></html>");
       //panelBottom.add(btnOk, new AbsoluteConstraints(150, 0, 80, -1));
        //getRootPane().setDefaultButton(btnOk);
           btnOk.addActionListener(new ActionListener()
           {
	         public void actionPerformed(ActionEvent e) 
	         {      closeAndShowMainDialog();	      }
	       });
	    Action actionOk = new ActionOk();
        btnOk.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F5"), "ok"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnOk.getActionMap().put("ok", actionOk);
        
           btnClose.setText("<html>"+UtilsResource.getString("Close")+" <b>esc</b></html>");
           btnClose.setIcon(ICO_CANCEL16);
           btnClose.setFocusable(false);
           btnClose.addActionListener(new ActionListener()
           {
	         public void actionPerformed(ActionEvent e) 
	         {      close();     }
	       });
        Action actionClose = new ActionClose();
        btnClose.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnClose.getActionMap().put("close", actionClose);


        panelActionButtons.add(btnOk);
        panelActionButtons.add(btnClose);
        panelActionButtons.setOpaque(false);

        panelBottom.add(panelActionButtons);       

        getContentPane().add(paneTitle, BorderLayout.NORTH);
        getContentPane().add(panelBottom, BorderLayout.PAGE_END);
        
        panelMain.setOpaque(false);
        panelBottom.setOpaque(false);
        
        panelAllOnIt = new JPanelDecorated();//(blue,lightBlue,0,1);
        panelAllOnIt.setLayout(new BorderLayout());
        panelAllOnIt.add(panelMain, BorderLayout.CENTER);  
        panelAllOnIt.add(panelTextBox, BorderLayout.SOUTH);        
        getContentPane().add(panelAllOnIt, BorderLayout.CENTER);
        
        utilsTable.setTableNavigationKeys(table);
        utilsTable.setTableExitNavigationKeys(table);

        setCloseClick();
        pack();
        locateOnCenterOfTheScreen();
    }

    /**
     * Retrieves companies from database.
     */      
    public void retrieveCompanies()
    {
    	//System.out.println("DialogCompanyLogin.retrieveCompanies ");
    	
    	//String query="SELECT  c.dbCompanyId, c.title, i.count FROM dbcompany c LEFT JOIN (SELECT COUNT(dbCompanyId) AS count,dbCompanyId FROM invoice i GROUP BY dbCompanyId ) i ON  c.dbCompanyId = i.dbCompanyId";
    	String query = "SELECT dbCompanyId AS \"No\", title AS \"εταιρίες\", companyVatNo AS \"ΑΦΜ\" FROM dbcompany ORDER BY dbCompanyId";//title";      //   , companyAfm AS \"ΑΦΜ\"  

        tableModel.setQuery(query);
 
        //table.setTableHeader(null); //no table header
     
        /*utilsTable=new UtilsTable();
        for (int c=0; c<table.getColumnCount(); c++)
        {   // table,column, margin
            utilsTable.packColumn(table, c, 2);
        }  */     
                scrollpaneCompany.setPreferredSize(utilsTable.setTableScrollPaneSize(table,packColumns(),300));//new Dimension(300, 220));

    }
/**
 * Retrieves the years for the companyId.
 * @param i the companyId
 */
    private void retrieveYearsForCompany(int i)
    {
      try
      {	   
      	   ResultSet rs;
           Vector years = new Vector(); 
           years.clear();
         
           String query = "SELECT dbYearDescr FROM dbyear WHERE dbCompanyId ='"+i+"' ORDER BY dbYearDescr";

           if (VariablesGlobal.globalShowSQL)
           {  System.out.println("DialogCompanyLogin.retrieveYearsForCompany: "+query);  }
           
   	    db.retrieveDBDataFromQuery(query,"DialogCompanyLogin.retrieveYearsForCompany");
   	    rs=db.getRS();
   	    //rsmd=db.getRSMetaData();           
        //   rs = db.retrieveResultSet(query);
         //  String strListOfYears="";
           while ( rs.next() )
           {  
               String y = rs.getString("dbYearDescr");
          //     strListOfYears=strListOfYears+"<tr><td>"+y+"</td></tr>";
              years.add(y);
           }
          // table.setToolTipText("<html><table>"+strListOfYears+"</table></html>");
          listYear.setListData(years);

          
          closeDB();
           //setListData(Object[] listData) 
       }
       catch ( SQLException sqlex)
       {
           System.out.println("error:DialogCompanyLogin.retrieveYearsForCompany: "+sqlex.getMessage());
       }

    }

    private void createCompany()
    {
/*        int selectedComp = lsm.getMinSelectionIndex();
    //    DialogEditRec dialogEditRec = new DialogEditRec((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this));
       PanelEditOneDataRec panelEditOneDataRec = new PanelEditOneDataRec((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this)); 
        //dialogEditOneDataOneRec = new DialogEditOneDataOneRec(); not here loads slow
        int selectedRow = 0;
       // selectedRow = panelOneDataManyRecData.getSelectedDataRow();
        //System.out.println("panelOneDataManyRec selectedRow "+selectedRow);
        String title = UtilsResource.getString("Company");//εταιρίας";
        String query=null;
        ImageIcon ico=null;
        /*String[] dbCompanyFields={"dbCompanyId","title","companyAfm","doyId","townId"};
        String[] dbCompanyFieldsTranslation={"Νο εταιρίας","τίτλος","Α.Φ.Μ.","doyId","townId"};  
        int[] dbCompanyGroupOfComps = null;
        EntityDBFields dbCompanyDBFields = new EntityDBFields (dbCompanyFields,dbCompanyFieldsTranslation,dbCompanyGroupOfComps);
        
              
        String dbCompanyQueryEditable="SELECT dbcompany.dbCompanyId AS\"Νο εταιρίας\", dbcompany.title AS\"τίτλος\", dbcompany.companyAfm AS\"Α.Φ.Μ.\", dbcompany.doyId, dbcompany.townId FROM dbcompany";
        EntityPanel entityPanelDbCompanyDataentry = new EntityPanel("ODOR","dbcompany",dbCompanyDBFields,null,null,"Νο εταιρίας","","dbCompanyId",dbCompanyQueryEditable,"βασικά στοιχεία",null, false, false,false,false,false);      
        
        
        EntityPanel[] entityPanelDbCompany = new EntityPanel[] { entityPanelDbCompanyDataentry};   */
        /*String[] dbCompanyFields={"dbCompanyId","title","companyAfm","doyId", "townId", "president","bank", "bankAccount","bankAccountIBAN","notes"};
        String[] dbCompanyFieldsTranslation={"Νο εταιρίας","τίτλος","Α.Φ.Μ.","doyId", "townId", "επών-όνομα προέδρου", "υποκατάστημα", "λογαριασμός", "ΙΒΑΝ",""};
        int[] dbCompanyGroupOfComps={0,0,0,0,0,0,1,1,1,2};
        String[] dbCompanyGroupOfCompsCaption = {"βασικά","λογαριασμός τράπεζας","σημειώσεις"};
        int[] dbCompanyGroupOfCompsColumns = {4,6,2};
        int[] dbCompanyGroupOfPanels = {-1,-1,-1};
        int[] dbCompanyIncludedInGroupOfPanels=null;
        EntityDBFields dbCompanyDBFields = new EntityDBFields (dbCompanyFields,dbCompanyFieldsTranslation,dbCompanyGroupOfComps);
        EntityGroupOfComps dbCompanyEntityGroupOfComps = new EntityGroupOfComps(dbCompanyGroupOfCompsCaption,dbCompanyGroupOfCompsColumns,dbCompanyIncludedInGroupOfPanels);
        //EntityGroupOfComps dbCompanyEntityGroupOfComps = new EntityGroupOfComps(dbCompanyFieldsTranslation,dbCompanyFields,dbCompanyIncludedInGroupOfPanels);
        EntityGroupOfPanels dbCompanyEntityGroupOfPanels = null;*/
        
        
// to reenable
//        EntityDBFields[] dbCompanyDBFields = new EntityDBFields[10];        
/*    	dbCompanyDBFields[0] = new EntityDBFields("dbCompanyId","Νο εταιρίας",0,null, true,FIELD_OBLIGATORY,FIELD_VALIDATION_NO);
       	dbCompanyDBFields[1] = new EntityDBFields("title","τίτλος",0,null, false,FIELD_OBLIGATORY,FIELD_VALIDATION_NO);
       	dbCompanyDBFields[2] = new EntityDBFields("companyAfm","Α.Φ.Μ.",0,null, false, FIELD_SUGGEST,FIELD_VALIDATION_AFM);
       	dbCompanyDBFields[3] = new EntityDBFields("doyId","Δ.Ο.Υ.",0,"doy", false,FIELD_SUGGEST,FIELD_VALIDATION_NO);
       	dbCompanyDBFields[4] = new EntityDBFields("president","επών-όνομα προέδρου",0,null, false,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO);
       	dbCompanyDBFields[5] = new EntityDBFields("townId","πόλη/χωριό",0,"town", false,FIELD_SUGGEST,FIELD_VALIDATION_NO);
       	dbCompanyDBFields[6] = new EntityDBFields("bankId","υποκατάστημα",1,"bank", false,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO);
       	dbCompanyDBFields[7] = new EntityDBFields("bankAccount","λογαριασμός",1,null, false,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO);
       	dbCompanyDBFields[8] = new EntityDBFields("bankAccountIBAN","ΙΒΑΝ",1,null, false,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO);
       	dbCompanyDBFields[9] = new EntityDBFields("notes","σημειώσεις",2,null, false,FIELD_NOCOMPLETION,FIELD_VALIDATION_NO);
*/
      		        
/*        EntityGroupOfComps[] dbCompanyEntityGroupOfComps= new EntityGroupOfComps[3];
        dbCompanyEntityGroupOfComps[0]= new EntityGroupOfComps("βασικά",4,-1);
        dbCompanyEntityGroupOfComps[1]= new EntityGroupOfComps("λογαριασμός τράπεζας",6,-1);
        dbCompanyEntityGroupOfComps[2]= new EntityGroupOfComps("σημειώσεις",1,-1);
               	
    
        EntityGroupOfPanels[] dbCompanyEntityGroupOfPanels = null;
        
        String dbCompanyQueryEditable=   "SELECT dbcompany.dbCompanyId AS\"Νο εταιρίας\", dbcompany.title AS\"τίτλος\", dbcompany.companyAfm AS\"Α.Φ.Μ.\", dbcompany.doyId, dbcompany.president, dbcompany.townId , dbcompany.bankId , dbcompany.bankAccount , dbcompany.bankAccountIBAN, dbcompany.notes FROM dbcompany";

        //"SELECT dbcompany.dbCompanyId AS\"Νο εταιρίας\", dbcompany.title AS\"τίτλος\", dbcompany.companyAfm AS\"Α.Φ.Μ.\", dbcompany.doyId, dbcompany.townId, dbcompany.president , dbcompany.bank , dbcompany.bankAccount , dbcompany.bankAccountIBAN , dbcompany.notes FROM dbcompany";
        int[] fieldsOnTitleDbCompany ={1,2,3};
        String[] fieldsOnTitleCaptionDbCompany  ={"Νο","τίτλος","ΑΦΜ"};        
        EntityPanel entityPanelDbCompanyDataentry = new EntityPanel("ODOR","dbcompany",dbCompanyDBFields,dbCompanyEntityGroupOfComps,dbCompanyEntityGroupOfPanels,"Νο εταιρίας","","dbCompanyId",dbCompanyQueryEditable,"βασικά στοιχεία",null, false, true,false,false,false);      
        EntityPanel[] entityPanelDbCompany = new EntityPanel[] { entityPanelDbCompanyDataentry};    
        
        
        /*String dbCompanyQueryEditable="SELECT dbcompany.dbCompanyId AS\"Νο εταιρίας\", dbcompany.title AS\"τίτλος\", dbcompany.companyAfm AS\"Α.Φ.Μ.\", dbcompany.doyId, dbcompany.townId, dbcompany.president , dbcompany.bank , dbcompany.bankAccount , dbcompany.bankAccountIBAN , dbcompany.notes FROM dbcompany";
        int[] fieldsOnTitleDbCompany ={1,2,3};
        String[] fieldsOnTitleCaptionDbCompany  ={"Νο","τίτλος","ΑΦΜ"};        
        EntityPanel entityPanelDbCompanyDataentry = new EntityPanel("ODOR","dbcompany",dbCompanyDBFields,dbCompanyEntityGroupOfComps,dbCompanyEntityGroupOfPanels,"Νο εταιρίας","","dbCompanyId",dbCompanyQueryEditable,"βασικά στοιχεία",null, false, true,false,false,false);      
        EntityPanel[] entityPanelDbCompany = new EntityPanel[] { entityPanelDbCompanyDataentry};          
          */     
        
        
        
/*        panelEditOneDataRec.setEntity("dbcompany",entityPanelDbCompany,null,null,
        false,null,null,null,null,null,
        title,ico,true,true,false,true,null,false,null);	
      /*public void setEntity(String entityIn, EntityPanel[] entityPanelIn,int[]fieldsOnTitleIn, String[] fieldsOnTitleCaptionIn, 
    boolean isMasterUnique, String primKey,  String primKeyValueIn,String primKeyDbIn, String[] primKeysMany,String[] primKeysManyTran,
    String titleIn,ImageIcon ico, boolean dataOneIn, boolean isNewRecIn, boolean showBtnOk, String[] categoryNodes, boolean showShowListButtonsIn) 	)*/
        	
    	//dialogEditRec.showDialog();
    	
/*        panelEditOneDataRec.setVisible(true);
        panelEditOneDataRec.setOpaque(false);
        DialogMulti dlg = new DialogMulti((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this));
        dlg.setEntity(panelEditOneDataRec,PANEL_TYPE_EDITONEDATAONEREC, UtilsResource.getString("CreateCompany"));
        dlg.display();    	
    	
      // if set clicked then retrieveCompanies();
        int tableRow=table.getSelectedRow();
        retrieveCompanies();
   	   
   	      if (tableRow>-1)
   	      {
   	        table.setRowSelectionInterval(selectedComp,selectedComp);
   	      }        
       System.out.println("dialogLogin.createCompany todo the new rec should be selected");
*/
    }
    
    private void createYear()
    {
 /*   	  JTextField txtYear = new JTextField(20);
          
          String inputDialog=JOptionPane.showInputDialog(panelMain,UtilsResource.getString("InsertYear"));
          
          System.out.println("DialogCompanyLogin.createYear '"+inputDialog+"'");
          
          if (inputDialog==null)
          {
          	 //if cancel is clicked
          }
          else
          {
              inputDialog=inputDialog.trim();
          
              if (!inputDialog.equalsIgnoreCase(""))
              {
              	
              if(inputDialog.length()<4)
              {
                   utilsGui.showMessageError(this,"Η χρονιά πρέπει να έχει το λιγότερο 4 χαρακτήρες !");
              }
              else if (inputDialog.length()>4)
              {
               utilsGui.showMessageError(this,"Η χρονιά πρέπει να έχει το μέγιστο 4 χαρακτήρες !");
              }
              else if (inputDialog.length()==4)
              {
              	
              	
                  String query = "INSERT INTO dbyear (dbyear,dbCompanyId) VALUES ('"+inputDialog+"' , "+getCompanyId()+")";
                  db.updateQuery(query,"DialogCompanyLogin.createYear",false);
                  if (VariablesGlobal.globalShowSQLEdit)
                  {
                      System.out.println("DialogCompanyLogin.createYear "+query);
                  }
                  retrieveYearsForCompany(getCompanyId());
                  System.out.println("DialogCompanyLogin.createYear make validation rules");
               }
               }
               else
               {
                  //utilsGui.showMessageError(this,"Η χρονιά δεν μπορεί να είναι κενή !");
               }
            }

  */        
    }
   
   private void deleteCompany()
   {	   
/*
   	   String company = getCompanyName();
   	   int dbCompanyId = getCompanyId();
   	   int tableRow=table.getSelectedRow();
   	   
       DialogAskUserNPass dialogAskUserNPass = new DialogAskUserNPass((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this),"<html><table><tr><td>Εαν θέλετε να διαγραφούν όλες οι χρήσεις της εταιρίας: "+
   	        "</td></tr><tr><td>"+dbCompanyId+" - "+company+"</td></tr><tr><td> παρακαλώ εισάγετε όνομα χρήστη και κωδικό.</td></tr></html>",dbCompanyId,null);
   	   dialogAskUserNPass.show();
   	   if (dialogAskUserNPass.getIsLoginOk())
   	   {   
   	      //System.out.println("delete the company "+dbCompanyId+" - "+company);

   	      String queryYear = "DELETE FROM dbyear WHERE dbCompanyId = "+dbCompanyId;
          if (VariablesGlobal.globalShowSQLEdit)
          {
          	System.out.println("DialogCompanyLogin.deleteCompany year "+queryYear);
          }
   	      db.updateQuery(queryYear,"DialogCompanyLogin.deleteCompany",false);

   	      String queryCompany = "DELETE FROM dbcompany WHERE dbCompanyId = "+dbCompanyId;
          if (VariablesGlobal.globalShowSQLEdit)
          {
          	System.out.println("DialogCompanyLogin.deleteCompany company "+queryCompany);
          }

   	      db.updateQuery(queryCompany,"DialogCompanyLogin.deleteCompany",false);
   	      retrieveCompanies();
   	      if (tableRow>0)
   	      {
   	        table.setRowSelectionInterval(tableRow-1,tableRow-1);
   	      }
   	      
   	   }
   	   else
   	   {
   	      //System.out.println("not delete the selected company");	
   	   }
*/   	   
   }

   private void deleteYear()
   {
/*   	String year = getYear();
   	String company = getCompanyName();
   	int dbCompanyId = getCompanyId();
   	if( year==null || year.equalsIgnoreCase(""))
   	{
   		//System.out.println("delete the selected year -"+year+"-");
   	}
   	else
   	{
    	  DialogAskUserNPass dialogAskUserNPass = new DialogAskUserNPass((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this),"<html><table><tr><td>Εαν θέλετε να διαγραφεί η χρονιά: "+
   	         "</td></tr><tr><td>"+year+" της εταιρίας "+dbCompanyId+" - "+company+"</td></tr><tr><td> παρακαλώ εισάγετε όνομα χρήστη και κωδικό.</td></tr></html>",dbCompanyId ,year);
   	      dialogAskUserNPass.show();
   	      
   	       if (dialogAskUserNPass.getIsLoginOk())
   	       { 
   	       
       	      String query = "DELETE FROM dbyear WHERE dbCompanyId = "+dbCompanyId+" AND dbyear = "+year;
              if (VariablesGlobal.globalShowSQLEdit)
              {
                	System.out.println("DialogCompanyLogin.deleteYear "+query);
              }    
     	      db.updateQuery(query,"DialogCompanyLogin.deleteYear",false);
              retrieveYearsForCompany(dbCompanyId); 
  	         
   	       }
   	       else
   	       {
   	           //System.out.println("NOT delete the selected year "+year);
     	   }
   	}*/
   }

    /**
     * Packs the columns of the table.
     */   
  public int packColumns()
  {
  	     //System.out.println("PanelODMRData.packColumns "+table.getRowCount());
         //for each column pack
         int totalWidthOfColumns =3; 
         for (int c=0; c<table.getColumnCount(); c++)
         {   // table,column, margin
                //System.out.println("PanelODMRData.packColumns "+c);

                	//System.out.println("PanelOneDataManyRecData.packColumns "+dbFieldsMany);//+" "+dbFieldsMany[c].getCaption());
            	    totalWidthOfColumns = totalWidthOfColumns + utilsTable.packColumn(table, c, 3,true,false,null);
            
             
         }       
        //System.out.println("DialogCompanyLogin.packColumns "+table.getColumnCount()+" "+totalWidthOfColumns); 	
    return totalWidthOfColumns;// needed for setScrollPaneSize in panelODMRD
  	
  }



    /**
     * Gets the selected company.
     * @return the selected company
     */     
   public int getSelectedCompany()
   {
   	   return lsm.getMinSelectionIndex();
   }
 
    /**
     * Sets the selected company.
     * @param row the selected row of the table
     */     
   public void setSelectedCompany(int row)
   {
       lsm.setSelectionInterval(row,row);
   } 
    
   public boolean getIsOkClicked()
   {
   	   return isOkClicked;
   }
 
    /**
     * Gets the companyId.
     * @return the selected company
     */     
   public int getCompanyId()
   {
   	  int key = Integer.parseInt((tableModel.getValueAt(lsm.getMinSelectionIndex(),0).toString())) ;
      return key;
   }
 
    /**
     * Gets the company name.
     */     
   public String getCompanyName()
   {
   	  String name = (tableModel.getValueAt(lsm.getMinSelectionIndex(),1).toString().trim()) ;
      return name;
   }

   public String getCompanyMessage()
   {
       String ret = "";
       getCompanyId();

      try
      {	   
      	   ResultSet rs;
           Vector years = new Vector(); 
           years.clear();
         
           String query = "SELECT * FROM dbcompany WHERE dbCompanyId ='"+getCompanyId()+"' ORDER BY dbCompanyId";

           if (VariablesGlobal.globalShowSQL)
           {  System.out.println("DialogCompanyLogin.getCompanyMessage: "+query);  }
           
   	    db.retrieveDBDataFromQuery(query,"DialogCompanyLogin.getCompanyMessage");
   	    rs=db.getRS();
   	    //rsmd=db.getRSMetaData();           
        //   rs = db.retrieveResultSet(query);
           
            rs.first();
            ret=rs.getString("message");
           
            /*while ( rs.next() )
           {  
              years.add(rs.getString(1));
           }
          listYear.setListData(years);
          listYear.setSelectedIndex(years.size()-1) ;*/
          
          closeDB();
           //setListData(Object[] listData) 
       }
       catch ( SQLException sqlex)
       {
           System.out.println("error:DialogCompanyLogin.getCompanyMessage: "+sqlex.getMessage());
       }       
       
       return ret;
   }
   
   
   public String getDbYearDateFrom(String dbYearId)
   {
       String dateFrom="";
      try
      {	   
      	   ResultSet rs;
           //Vector years = new Vector(); 
           //years.clear();
         //String strDateEditing = utilsDate.getDateFormatEditing();
          //String strDate = utilsDate.reformatDateStringToReadFromDB(getDate());
          //String strDate = getDate();
          String strRefDate = utilsDate.reformatDateStringToSaveToDB(getDate());
           String query = "SELECT dbDateFrom FROM dbyear WHERE dbCompanyId ='"+getCompanyId()+"' AND dbyearId LIKE "+dbYearId+" ";

           System.out.println("DialogCompanyLogin.getDbYearDateFrom:   query:"+query); 
           
           if (VariablesGlobal.globalShowSQL)
           {  System.out.println("DialogCompanyLogin.getDbYearDateFrom: "+query);  }
           
   	    db.retrieveDBDataFromQuery(query,"DialogCompanyLogin.getDbYearDateFrom");
   	    rs=db.getRS();
   	    //rsmd=db.getRSMetaData();           
        //   rs = db.retrieveResultSet(query);
           
            if(rs.first())
            {
            dateFrom=rs.getString("dbDateFrom");
            }
            else
            {
                dateFrom="";
                //utilsGui.showMessageError(this,"DialogCompanyLogin.getYearId      strRefDate:"+strRefDate);
            }           
       }
       catch ( SQLException sqlex)
       {
           System.out.println("error:DialogCompanyLogin.getDbYearDateFrom: "+sqlex.getMessage());
           if(VariablesGlobal.globalShowPrintStackTrace)
          {      sqlex.printStackTrace();}
       }
      finally
      {
           closeDB();
      }
       
       System.out.println("DialogCompanyLogin.getDbYearDateFrom:      dateFrom:"+dateFrom);
    return dateFrom;   
              
   }
   
   
   public String getDbYearDateTo(String dbYearId)
   {
       String dateTo="";
      try
      {	   
      	   ResultSet rs;
           //Vector years = new Vector(); 
           //years.clear();
         //String strDateEditing = utilsDate.getDateFormatEditing();
          //String strDate = utilsDate.reformatDateStringToReadFromDB(getDate());
          //String strDate = getDate();
          //String strRefDate = utilsDate.reformatDateStringToSaveToDB(getDate());
           String query = "SELECT dbDateTo FROM dbyear WHERE dbCompanyId ='"+getCompanyId()+"' AND dbyearId LIKE "+dbYearId+" ";

           System.out.println("DialogCompanyLogin.getDbYearDateTo:   query:"+query); 
           
           if (VariablesGlobal.globalShowSQL)
           {  System.out.println("DialogCompanyLogin.getDbYearDateTo: "+query);  }
           
   	    db.retrieveDBDataFromQuery(query,"DialogCompanyLogin.getDbYearDateTo");
   	    rs=db.getRS();
   	    //rsmd=db.getRSMetaData();           
        //   rs = db.retrieveResultSet(query);
           
            if(rs.first())
            {
            dateTo=rs.getString("dbDateTo");
            }
            else
            {
                dateTo="";
                
            }           
       }
       catch ( SQLException sqlex)
       {
           System.out.println("error:DialogCompanyLogin.getDbYearDateTo: "+sqlex.getMessage());
           if(VariablesGlobal.globalShowPrintStackTrace)
          {      sqlex.printStackTrace();}
       }
      finally
      {
           closeDB();
      }
       
       System.out.println("DialogCompanyLogin.getDbYearDateTo:      dateTo:"+dateTo);
    return dateTo;   
              
   }  
   
   
   public String getYearId()
   {
       String yearId="";
      try
      {	   
      	   ResultSet rs;
           //Vector years = new Vector(); 
           //years.clear();
         //String strDateEditing = utilsDate.getDateFormatEditing();
          //String strDate = utilsDate.reformatDateStringToReadFromDB(getDate());
          //String strDate = getDate();
          String strRefDate = utilsDate.reformatDateStringToSaveToDB(getDate());
           String query = "SELECT dbyearId FROM dbyear WHERE dbCompanyId ='"+getCompanyId()+"' AND dbDateFrom <='"+strRefDate+"' AND dbDateTo >= '"+strRefDate+"' ORDER BY dbYearDescr";

           System.out.println("DialogCompanyLogin.getYearId:   query:"+query); 
           
           if (VariablesGlobal.globalShowSQL)
           {  System.out.println("DialogCompanyLogin.getYearId: "+query);  }
           
   	    db.retrieveDBDataFromQuery(query,"DialogCompanyLogin.getYearId");
   	    rs=db.getRS();
   	    //rsmd=db.getRSMetaData();           
        //   rs = db.retrieveResultSet(query);
           
            if(rs.first())
            {
            yearId=rs.getString("dbyearId");
            }
            else
            {
                yearId="";
                //utilsGui.showMessageError(this,"DialogCompanyLogin.getYearId      strRefDate:"+strRefDate);
            }           
       }
       catch ( SQLException sqlex)
       {
           System.out.println("error:DialogCompanyLogin.getYearId: "+sqlex.getMessage());
           if(VariablesGlobal.globalShowPrintStackTrace)
          {      sqlex.printStackTrace();}
       }
      finally
      {
           closeDB();
      }
       
       System.out.println("DialogCompanyLogin.getYearId:      yearId:"+yearId);
    return yearId;   
       
   }
   
   /**
     * Gets the year.
     */     
   public String getYearDescr()
   {
   	 
   	 String yearDescr="";


      try
      {	   
      	   ResultSet rs;
           //Vector years = new Vector(); 
           //years.clear();
         //String strDateEditing = utilsDate.getDateFormatEditing();
          //String strDate = utilsDate.reformatDateStringToSaveToDB(getDate());
          //String strDate = utilsDate.reformatDateStringToSaveToDB(dateIn);
          //String strDate = getDate();//dateIn;
          String strRefDate = utilsDate.reformatDateStringToSaveToDB(getDate());
           String query = "SELECT dbYearDescr FROM dbyear WHERE dbCompanyId ='"+getCompanyId()+"'  AND dbDateFrom <='"+strRefDate+"' AND dbDateTo >= '"+strRefDate+"'  ORDER BY dbYearDescr";
           
           System.out.println("DialogCompanyLogin.getYearDescr:  query:"+query+"   strRefDate:"+strRefDate); 
           if (VariablesGlobal.globalShowSQL)
           {  System.out.println("DialogCompanyLogin.getYearDescr:      query:"+query);  }
           
   	    db.retrieveDBDataFromQuery(query,"DialogCompanyLogin.getYearDescr");
   	    rs=db.getRS();
   	    //rsmd=db.getRSMetaData();           
        //   rs = db.retrieveResultSet(query);
           
            if(rs.first())
            {
            yearDescr=rs.getString("dbYearDescr");
            }
            else
            {
                yearDescr="";
            }


       }
       catch ( SQLException sqlex)
       {
           System.out.println("error:DialogCompanyLogin.getYearDescr: "+sqlex.getMessage());
           if(VariablesGlobal.globalShowPrintStackTrace)
           {      sqlex.printStackTrace();}
       }
      finally
      {
           closeDB();
      }
   	  
      return yearDescr;
   }

    /**
     * Gets the username.
     */     
   /*public String getUser()
   {
      return txtUser.getText().trim();
   }*/
 


    /**
     * Gets the password.
     */    
   /*public String getPassword()
   {
      return txtPassword.getText().trim();
   }*/


    /**
     * Gets the date.
     */    
   public String getDate()
   {
      return txtDate.getText().trim();
   }

   

   public boolean getHasUserLoggedIn(String userIdIn)
   {
       boolean ret = false;
       userId = userIdIn;
       if(userId.equalsIgnoreCase(""))
       {
           ret=false;
       }
       else
       {
           ret=true;
       }
       
       return ret;
   }
    /**
     * Shows the main dialog.
     */   
    private void closeAndShowMainDialog()
    {

      /*if (listYear.getSelectedValue()==null)
      {
        	  utilsGui.showMessageError(UtilsResource.getString("PleaseSelectCompanyAndYear"));
        	  //JOptionPane.showMessageDialog(null,"Παρακαλώ επιλέξτε εταιρία και χρήση.", "Προσοχή !", JOptionPane.ERROR_MESSAGE);
      }
      else
      {*/
        isOkClicked = true;
        
        int companyId = getCompanyId();
        
        if( getYearId().equalsIgnoreCase(""))
        {
            utilsGui.showMessageError(this,"Δεν υπάρχει ανοιγμένη χρήση για την ημερομηνία: "+getDate());
        }
        else
        {

           this.setVisible(false);
          if (isExit == true ) // exit
          {
            dialogMain = new DialogMain();
   	        String name = (tableModel.getValueAt(lsm.getMinSelectionIndex(),1).toString()) ;
                //String strDate = utilsDate.reformatDateStringToSaveToDB(getDate());
            String yearDescr = getYearDescr(); //listYear.getSelectedValue().toString(); 
            boolean isNotLoginAgain =isExit;

            if(isNotLoginAgain)
            {
            	// the first time it loges in, dialogMain.main takes action to "if isOk clicked"
            }
            else
            {
                String yearId = getYearId();
                dialogMain.setCompanyYearUserDate(isNotLoginAgain,companyId, name.trim(),yearId, getDbYearDateFrom(yearId), getDbYearDateTo(yearId),yearDescr.trim(), userId, txtDate.getText().trim(), getCompanyMessage());
            }
          } 

       }
    }


    /**
     * create a window listener to respond to the window close click.
     *
     */
   private void setCloseClick()
   {
   	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);   
    addWindowListener(new WindowAdapter()
    {
       public void windowClosing(WindowEvent e)
       {    close();   }
    });
   }
    
    private void closeDB()
    {
    	db.releaseConnectionRs();
        db.releaseConnectionRsmd();
    }
    
    
    private void close()
    {
       if (isExit == true ) // exit
       {
       	   closeDB();
           System.exit(0);
       }
       else
       {
          isOkClicked = false;
          closeDB();
          this.dispose();
       }
    }

    public void setIsExit(boolean exit)
    {
    	isExit = exit;
    	if (isExit==true)
    	{
    	   btnClose.setIcon(ICO_EXIT16);
    	   btnClose.setText("<html>"+UtilsResource.getString("Exit")+" <b>esc</b></html>");
    	}
    }
 

    /**
     * Locates the frame on the screen center.
     */
    private void locateOnCenterOfTheScreen()
    {
    	Dimension paneSize   = this.getSize();
    	Dimension screenSize = this.getToolkit().getScreenSize();
    	this.setLocation(
            (screenSize.width  - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //new DialogCompanyLogin(new JFrame(), true).show();
        DialogCompanyLogin login  = new DialogCompanyLogin((java.awt.Dialog)null);
        login.setVisible(true);
    }
   

   class  ActionOk extends AbstractAction                 
   {       
        public ActionOk()
        {        }
      	
    	public void actionPerformed(ActionEvent e)
      	{    btnOk.doClick();      }    	
    }                


   class  ActionClose extends AbstractAction                 
   {       
        public ActionClose()
        {        }
      	
    	public void actionPerformed(ActionEvent e)
      	{     btnClose.doClick();     }    	
    }                


}
