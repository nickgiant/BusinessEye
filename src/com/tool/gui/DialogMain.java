 package com.tool.gui;
 
import com.tool.domain.EntityData;
 import com.tool.utils.*;
 import com.tool.guicomps.*;
import static com.tool.guicomps.Constants.ICO_USER16;
 import com.tool.jdbc.*;
 import com.tool.model.*;
 import com.tool.setup.*;
 
 import java.util.logging.*;
 import java.sql.*;
 import java.util.GregorianCalendar;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.Timer;
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.tree.*;

import javax.swing.*;
 import java.text.*;
import javax.swing.event .*;

 import javax.swing.border.BevelBorder;
import java.awt.im.InputContext;

 //import java.awt.event.ActionListener;
 import java.text.ParseException;
 
 import java.io.FileInputStream;
 import java.io.IOException;
  
 import java.util.Locale;
  import java.util.Properties;
  
  import java.io.*;
import java.util.Date;
import java.util.*;
 import java.net.*;
 import java.util.zip.*;
//import javax.swing.Timer;
 //import net.sourceforge.helpgui.*;
 
public class DialogMain extends JxFrame implements Constants
{
    

    private static  boolean isCapsLockBeforeEnteringApp = false;
    private static UtilsGui utilsGui = new UtilsGui();
//    Logger logger = Logger.getLogger("mine");
    private static UtilsFileSystem utilsFileSystem;
    private static UtilsOS utilsOS;
    private static UtilsString utilsString;    
   /* private Integer LAYER_FIRST  = new Integer(1); //base manage
    private Integer LAYER_SECOND   = new Integer(2); 
    private Integer LAYER_THIRD     = new Integer(3); 
    private Integer LAYER_FOURTH     = new Integer(4);// top about and systinfo
    */
    private JLabel lblUserCaption = new JLabel();
    private JLabel lblUser = new JLabel();
    private JLabel lblCompanyCaption = new JLabel();
    private JLabel lblCompany = new JLabel();
    private JLabel lblYearCaption = new JLabel();
    private JLabel lblYear;
    private JLabel lblDateCaption = new JLabel();
    private JLabel lblDate = new JLabel();
    private JLabel lblDbUrl = new JLabel();
    private JLabel lblMessageFromDbCompany = new JLabel();
    //private JLabel lblIcoResize = new JLabel();
    //MenuBar menuBar = new MenuBar();
    JMenuBar menuBar ;

    private JxPanel panelTop = new JxPanel();
    JPanel panelBottomMsg;// this is message
    //private CurvedGradientPanel panelBottom;//panel gradient      CurvedGradientPanel
    private JPanelDecorated panelBottom;
    private JxPanel panelBottomLeft;
    private JxPanel panelBottomRight;
    private JxPanel panelBottomRightCorner;
    
    //private BevelBorder borderRaised = new BevelBorder(javax.swing.border.BevelBorder.RAISED);
    
    
    ToolBarMain toolBarMain;
    
    private TableModelGeneric myModel;
   
    
    private DialogSystemInfo dialogSystemInfo;
    private PanelManagement panelManagement;
    //private DialogProgramConfiguration dialogProgramConfiguration;
    
    private JButton btnExit;
    
    private JButton btnCheckForUpdates;
    
    
    private static JFrame frame;

    private static String systemDirectorySymbol;
    
    private EntityData entityData;
    private boolean isTreePanelVisible = true;
//    private JDesktopPane desktopPane;
   // DialogCompanyLogin dialogCompanyLogin  = new DialogCompanyLogin();
    //private DatabaseMeta dbMeta;
    private DataTree dataTree;
    
    private JCheckBoxMenuItem mitemVisTreeMenu ;
    private JCheckBoxMenuItem mitemVisToolbarMain;
    
    //private Timer timerDelayExpand;
    private UtilsDouble utilsDouble; 
     private JToggleButton btnModule;
     //private JButton btnModule2;
    
    private ArrayList listBtnModule;
    private ArrayList listSections;
    private PanelHtmlBrowser pnlBrowser;
            
    private DialogBackUp dlgBackup;
    private WindowWait wwu;
    
    private final String TXTCHECKFORNEWVERSION=  "έλεγχος για νέα έκδοση";
    private String userId;
    private Database db= new Database();
    
    private int intModuleSelected=-1;
    
    public DialogMain() 
    {   
                    
        
        
        super(VariablesGlobal.appName+"  "+ VariablesGlobal.appUseName);//+" "+VariablesGlobal.appSubVersion);
        //System.out.println("loading DialogMain");
        initialize();
        //System.out.println("loaded DialogMain");
        
    }
    
    /**
     * This method is called from within the constructor to
     * initialize the form.
     */
    private void initialize() 
    {    
        toolBarMain = new ToolBarMain();
        utilsOS = new UtilsOS(); 
        utilsString = new UtilsString();
        	utilsFileSystem = new UtilsFileSystem();
                utilsDouble=new UtilsDouble();
            dlgBackup = new DialogBackUp();    
                listBtnModule = new ArrayList();
        getContentPane().setLayout(new BorderLayout());//------------ set layaout
     
 //       setJMenuBar(menuBar);
        //desktopPane = new JDesktopPane(); //a specialized layered pane
        panelManagement = new PanelManagement(this);
         pnlBrowser = new PanelHtmlBrowser();
         

       /* String dayArray[] = {"Κυριακή","Δευτέρα","Τρίτη","Τετάρτη","Πέμπτη","Παρασκευή","Σαββάτο" };
        GregorianCalendar cal = new GregorianCalendar();

        int dayInt = cal.get(Calendar.DAY_OF_WEEK);

        int d = cal.get(Calendar.DAY_OF_MONTH);
        int m = cal.get(Calendar.MONTH)+1;  // plus 1. dont know why
        int y = cal.get(Calendar.YEAR);

        String day = dayArray[dayInt-1];//dayArray starts from 0
        
        String today = day +" "+d+"/"+m+"/"+y;*/
        
        lblYear = new JLabel();
        lblUserCaption.setText("    χρήστης: ");
        lblUserCaption.setOpaque(false);
        lblUserCaption.setForeground(CLR_PANEL_BORDER);//lblUser.getBackground().darker().darker());
        lblUser.setText("");
        lblCompanyCaption.setText("    εταιρία: ");
        lblCompanyCaption.setOpaque(false);
        lblCompanyCaption.setForeground(CLR_PANEL_BORDER);//lblUser.getBackground().darker().darker());
        lblCompany.setText("");
        lblYearCaption.setText("    χρήση: ");
        lblYearCaption.setOpaque(false);
        lblYearCaption.setForeground(CLR_PANEL_BORDER);//lblUser.getBackground().darker().darker());
        lblYear.setText("");
        lblDateCaption.setText("    ημερομηνία: "); // set user,cooperative,year,today
        lblDateCaption.setOpaque(false);
        lblDateCaption.setForeground(CLR_PANEL_BORDER);//lblUser.getBackground().darker().darker());
        lblDate.setText("");
        
        
        lblMessageFromDbCompany.setVisible(false);
        lblMessageFromDbCompany.setIcon(ICO_EXCLAMATION16);
        
        //lblMessageFromDbCompany.setOpaque(true);
        //lblMessageFromDbCompany.setBackground(CLR_TOOLTIP);
//        IconResize icoResize = new IconResize();
//        lblIcoResize.setIcon(icoResize);
//        lblIcoResize.setOpaque(false);
//        lblIcoResize.setHorizontalAlignment(SwingConstants.RIGHT); 

        

        
        
        Color blue= new Color(129, 169, 226);
         Color lightBlue= new Color(196,218,246);//220,235,253);//148, 215, 254);
        panelBottom = new JPanelDecorated();// new CurvedGradientPanel(lightBlue,blue,0,0);
        panelBottom.setLayout(new BorderLayout());
       //panelBottom.setBorder(BorderFactory.createEmptyBorder(0,10,10,10)); 
       // panelBottom.setBorder(borderRaised);
        
        panelBottomMsg = new JPanel();// this is message
        panelBottomMsg.setOpaque(true);
        panelBottomMsg.setBackground(CLR_TOOLTIP);
        panelBottomMsg.setLayout(new FlowLayout());//BorderLayout());
                   
           panelBottomMsg.setVisible(false);
        
        
        JPanelDecorated panelBottomLogindata = new JPanelDecorated();     // it is for date,year,company name, etc
        panelBottomLogindata.setLayout(new BorderLayout());
       panelBottom.add( panelBottomMsg, BorderLayout.PAGE_START);//LINE_START);//PAGE_START);  
       panelBottom.add( panelBottomLogindata, BorderLayout.CENTER);  
       
       
       panelBottomMsg.add(lblMessageFromDbCompany);//, BorderLayout.CENTER);
       
        //panelBottom = new PanelGradient(CLR_PANEL_END.brighter(),CLR_PANEL_START,28);//(this.getBackground().brighter().brighter(), this.getBackground().darker(),23);
        //panelBottom = new CurvedGradientPanel();//panelGradient();
        panelBottomLeft = new JxPanel();//(CLR_PANEL_END.brighter(),CLR_PANEL_START,28);//(this.getBackground().brighter().brighter(), this.getBackground().darker(),23);
        panelBottomRight = new JxPanel();//PanelGradient(CLR_PANEL_END.brighter(),CLR_PANEL_START,28);//(this.getBackground().brighter().brighter(), this.getBackground().darker(),23);
        panelBottomRightCorner = new JxPanel();// PanelGradient(CLR_PANEL_END.brighter(),CLR_PANEL_START,28);//(this.getBackground().brighter().brighter(), this.getBackground().darker(),23);
        
         panelBottomMsg.setPreferredSize(new Dimension(getWidth(), 20));
        panelBottomLogindata.setPreferredSize(new Dimension(getWidth(), 20));
        //panelGradient = new PanelGradient(this.getBackground().brighter().brighter(), this.getBackground(),16);

        /*PanelSeparator panelSeparator1 = new PanelSeparator(CLR_PANEL_BORDER, Color.WHITE);
        PanelSeparator panelSeparator2 = new PanelSeparator(CLR_PANEL_BORDER, Color.WHITE);
        PanelSeparator panelSeparator3 = new PanelSeparator(CLR_PANEL_BORDER, Color.WHITE);
        PanelSeparator panelSeparator4 = new PanelSeparator(CLR_PANEL_BORDER, Color.WHITE);
        PanelSeparator panelSeparator5 = new PanelSeparator(CLR_PANEL_BORDER, Color.WHITE);*/
        //PanelSeparator panelSeparator6 = new PanelSeparator(Color.GRAY, Color.WHITE);
        

        
        panelBottomLeft.setLayout(new FlowLayout(FlowLayout.CENTER,5,1));

     
        
        panelBottomLeft.add(new JLabel(VariablesGlobal.appName));
        //panelBottomLeft.add(new JLabel(VariablesGlobal.appProductCaption));
        panelBottomLeft.add(new JLabel(VariablesGlobal.appLeadVersion+"."+VariablesGlobal.appSubVersion));
        //panelBottomLeft.add(new JLabel(VariablesGlobal.appUseName));
       //panelBottomLeft.add(panelSeparator1);
        panelBottomLeft.add(lblUserCaption);
        panelBottomLeft.add(lblUser);
        //panelBottomLeft.add(panelSeparator2);
        panelBottomLeft.add(lblCompanyCaption);
        panelBottomLeft.add(lblCompany);
        //panelBottomLeft.add(panelSeparator3);
        panelBottomLeft.add(lblYearCaption);
        panelBottomLeft.add(lblYear);
        //panelBottomLeft.add(panelSeparator4);
        panelBottomLeft.add(lblDateCaption);
        panelBottomLeft.add(lblDate); 
         
       // panelBottomLeft.add(panelSeparator5);
//        panelBottomLeft.add(lblIcoResize);
        
        final JLabel lblInfo = new JLabel("πληροφορίες");
        lblInfo.setIcon(ICO_INFO16);
        lblInfo.setToolTipText("πληροφορίες");
        //lblInfo.setBackground(Color.DARK_GRAY);
        lblInfo.setOpaque(false);
        lblInfo.setBackground(CLR_PANEL_END);//  CLR_PANEL_END   CLR_PANEL_START
        
        lblInfo.setBorder(BorderFactory.createLineBorder(CLR_LBL_NORMAL_BORDER));
        lblInfo.addMouseListener(new MouseAdapter()
                {       	      
                 public void mouseEntered(MouseEvent e)
                 {
                 	lblInfo.setBorder(BorderFactory.createLineBorder(CLR_LBL_ROLL_BORDER));
                 	lblInfo.setOpaque(true);
                 	lblInfo.setBackground(CLR_LBL_ROLL);
                 	lblInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
                 }
                 public void mouseExited(MouseEvent e)
                 {
                    lblInfo.setBorder(BorderFactory.createLineBorder(CLR_LBL_NORMAL_BORDER));                 
                    lblInfo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    lblInfo.setOpaque(false);
                    //lblInfo.setOpaque(true);
                    //lblInfo.setBackground(CLR_PANEL_END);//  CLR_PANEL_END   CLR_PANEL_START
                 }
                 public void mousePressed(MouseEvent e)
                 {
                 	//lblInfo.setBorder(BorderFactory.createLineBorder(Color.white)); 
                 	lblInfo.setOpaque(true);                
                 	lblInfo.setBorder(BorderFactory.createLineBorder(CLR_LBL_CLICKED_BORDER)); 
                 	lblInfo.setBackground(CLR_LBL_CLICKED);                 		

                 }
                 public void mouseReleased(MouseEvent e)
                 {
                 	lblInfo.setBorder(BorderFactory.createLineBorder(CLR_LBL_ROLL_BORDER));
                 	lblInfo.setOpaque(true);
                 	lblInfo.setBackground(CLR_LBL_ROLL);
                 	lblInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));              

                 }                   
                 public void mouseClicked(MouseEvent e)
                 {
                     displayDialogSystemInfo();
                     //System.out.println("DialogMain.initialize clicked ");
                     //clickedOnRow(Integer.parseInt(txtFieldFinal.getToolTipText()));
                 }                 
                });       
        panelBottomLeft.add(lblInfo);
        panelBottomLeft.add(lblDbUrl);
        /* final JLabel lblHelp = new JLabel("βοήθεια");
        lblHelp.setIcon(ICO_HELP16);
        lblHelp.setToolTipText("βοήθεια");
        //lblInfo.setBackground(Color.DARK_GRAY);
        
        lblHelp.setBorder(BorderFactory.createLineBorder(CLR_LBL_NORMAL_BORDER));
        lblHelp.addMouseListener(new MouseAdapter()
                {       	      
                 public void mouseEntered(MouseEvent e)
                 {
                 	lblHelp.setBorder(BorderFactory.createLineBorder(CLR_LBL_ROLL_BORDER));
                 	lblHelp.setCursor(new Cursor(Cursor.HAND_CURSOR));
                 	lblHelp.setBackground(CLR_LBL_ROLL);
                 	lblHelp.setOpaque(true);
                 }
                 
                 public void mouseExited(MouseEvent e)
                 {
                    lblHelp.setBorder(BorderFactory.createLineBorder(CLR_LBL_NORMAL_BORDER));                 
                    lblHelp.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    lblHelp.setOpaque(false);
                 }
                 

                 
                 public void mousePressed(MouseEvent e)
                 {
                 	lblHelp.setOpaque(true);                
                 	lblHelp.setBorder(BorderFactory.createLineBorder(CLR_LBL_CLICKED_BORDER)); 
                 	lblHelp.setBackground(CLR_LBL_CLICKED);                       

                 }
                 
                 public void mouseReleased(MouseEvent e)
                 {
                 	lblHelp.setBorder(BorderFactory.createLineBorder(CLR_LBL_ROLL_BORDER));
                 	lblHelp.setCursor(new Cursor(Cursor.HAND_CURSOR));
                 	lblHelp.setBackground(CLR_LBL_ROLL);
                 	lblHelp.setOpaque(true);            

                 }                    
                 public void mouseClicked(MouseEvent e)
                 {
                     //System.out.println("DialogMain.initialize clicked ");
                     displayDialogHelp();
                     //clickedOnRow(Integer.parseInt(txtFieldFinal.getToolTipText()));
                 }                 
                 
                 
                }); 

         panelBottomLeft.add(lblHelp);

         final JLabel lblKeys = new JLabel("πλήκτρα");
        lblKeys.setIcon(ICO_KEYS16);
        lblKeys.setToolTipText("πλήκτρα");
        //lblInfo.setBackground(Color.DARK_GRAY);
        lblKeys.setBorder(BorderFactory.createLineBorder(CLR_LBL_NORMAL_BORDER));
        lblKeys.addMouseListener(new MouseAdapter()
                {       	      
                 public void mouseEntered(MouseEvent e)
                 {
                 	lblKeys.setBorder(BorderFactory.createLineBorder(CLR_LBL_ROLL_BORDER));
                 	lblKeys.setCursor(new Cursor(Cursor.HAND_CURSOR));
                 	lblKeys.setBackground(CLR_LBL_ROLL);
                 	lblKeys.setOpaque(true);
                 }
                 
                 public void mouseExited(MouseEvent e)
                 {
                    lblKeys.setBorder(BorderFactory.createLineBorder(CLR_LBL_NORMAL_BORDER));                 
                    lblKeys.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    lblKeys.setOpaque(false);
                 }
                 
                 
                 public void mousePressed(MouseEvent e)
                 {
                 	lblKeys.setOpaque(true);                
                 	lblKeys.setBorder(BorderFactory.createLineBorder(CLR_LBL_CLICKED_BORDER)); 
                 	lblKeys.setBackground(CLR_LBL_CLICKED);              

                 }
                 
                 public void mouseReleased(MouseEvent e)
                 {
                 	lblKeys.setBorder(BorderFactory.createLineBorder(CLR_LBL_ROLL_BORDER));
                 	lblKeys.setCursor(new Cursor(Cursor.HAND_CURSOR));
                 	lblKeys.setBackground(CLR_LBL_ROLL);
                 	lblKeys.setOpaque(true);                

                 }                   
                 public void mouseClicked(MouseEvent e)
                 {
                     displayDialogKeys();
                     //System.out.println("DialogMain.initialize clicked ");
                     //clickedOnRow(Integer.parseInt(txtFieldFinal.getToolTipText()));
                 }                 

                }); 

         panelBottomLeft.add(lblKeys);
         */
         //panelBottomLogindata.setOpaque(true);
         //panelBottomLeft.setOpaque(true);
         //panelBottomRight.setOpaque(true);
         
     
//        panelBottomRightCorner.add(lblIcoResize);
//        panelBottomRight.add(panelBottomRightCorner);

        panelBottomLogindata.add(panelBottomLeft, BorderLayout.CENTER);
//        panelBottomLogindata.add(panelBottomRight, BorderLayout.LINE_END);
        
        
        //22 with borderRaised, 18 without border  
//      panelBottomMsg.setPreferredSize(new Dimension(getWidth(), 20)); //22 with borderRaised, 18 without border
  //      panelBottomLogindata.setPreferredSize(new Dimension(getWidth(), 20)); //22 with borderRaised, 18 without border
        //panelBottomLogindata.setBorder(borderRaised);       

        panelTop.setLayout(new BorderLayout());
       //panelTop.setLayout(new BoxLayout(panelTop,BoxLayout.Y_AXIS));
       //panelTop.setBorder(borderRaised);
        panelTop.add(toolBarMain,BorderLayout.CENTER);
        //panelTop.setLayout(new FlowLayout( FlowLayout.CENTER ));
        
        getContentPane().add(panelTop, BorderLayout.PAGE_START);  //------------ north     //   add   main toolbar     
        getContentPane().add(panelManagement, BorderLayout.CENTER);     
       //getContentPane().add(desktopPane, BorderLayout.CENTER);       
        //jLabel2.setText("status");
        getContentPane().add(panelBottom, BorderLayout.PAGE_END); //------------ south
        
        
        //getContentPane().add(panelButtomDown, BorderLayout.SOUTH);
 //       loadEntitySections();
        
        
        
        frame =this;
        setCloseClick();
        
        pack();
        
    }
 


    public void locateOnCenterOfTheScreen()
    {
    	Dimension paneSize   = this.getSize();
    	Dimension screenSize = this.getToolkit().getScreenSize();
    	//System.out.println(getSize()+" - "+getToolkit().getScreenSize());
    	this.setLocation(
            (screenSize.width  - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
    }

    private void displayMessageDialog(String title, String message)
    {
   	   JOptionPane.showMessageDialog(this, message, title,JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayDialogBackupRestore()
    {
    	
    /*	
    String database="";	
    String databaseVersion="";
    String databaseUrl="";
    String databaseUser="";
     try
     {
        Connection conn = DbConnection.getConnectionFromFile();//get from connection factory
        DatabaseMetaData metaData = conn.getMetaData();
        database = metaData.getDatabaseProductName();
        databaseVersion = metaData.getDatabaseProductVersion();
        databaseUrl=metaData.getURL();
        databaseUser=metaData.getUserName();
        
        DbConnection.releaseConnection(conn);
     }
     catch ( SQLException sqlex)
     {
          System.out.println("error:DialogMain.displayDialogBackupRestore:"+sqlex.getMessage());
     }    	
    	int dbEngine =0;
    	if(database.equalsIgnoreCase("mysql"))
    	{
    		dbEngine=DBENGINE_MYSQL;
    	}
    	else if(database.equalsIgnoreCase("h2"))
    	{
    		dbEngine=DBENGINE_H2EMBEDDED;
    	}
    	else
    	{
    		System.out.println("error:DialogMain.displayDialogBackupRestore unknown database "+database);
    	}*/
        if(panelManagement.closeAllTabs())
        {	
    	
    	DialogBackUp dialogBackUp = new DialogBackUp(this);
    	//(JFrame parent, int dbEngineIn,String dbEngineNameIn,String urlIn,String userIn)

        dialogBackUp.setVisible(true);
        }

    }


    /* private void displayWindowNew()
     {
     	FrameNew f = new FrameNew();
     	f.setEntity( VariablesGlobal.appName+" "+VariablesGlobal.appSubVersion+" ετ:"+ VariablesGlobal.globalCompanyName+" χρ:"+VariablesGlobal.globalYear,null);
     	f.setVisible(true);
     	
     }*/
    
     private void displayDialogQueryBrowser()
     {

    	DialogQueryBrowser dialogQueryBrowser = new DialogQueryBrowser();
       	//Dimension paneSize = this.getSize();
        dialogQueryBrowser.displayDialogQueryBrowser();
       	dialogQueryBrowser.locateOnCenterOfTheScreen();
        dialogQueryBrowser.setVisible(true);
     }
     
     
    private void displayDialogKeys()
    {
    	DialogKeys dialogKeys = new DialogKeys(this);
       	Dimension paneSize = this.getSize();
       	dialogKeys.locateOnCenterOfTheScreen();
        dialogKeys.setVisible(true);
     /*   desktopPane.add(dialogKeys,LAYER_FOURTH);
       try
        {         dialogKeys.setSelected(true);         }
       catch (java.beans.PropertyVetoException e) {}   	  */ 
    }

 /*   private void displayDialogParameters()
    {
    	DialogParameters dialogParameters = new DialogParameters(this);
       	Dimension paneSize = this.getSize();
       	//dialogParameters.locateOnCenterOfTheScreen();
        dialogParameters.setVisible(true);
     /*   desktopPane.add(dialogKeys,LAYER_FOURTH);
       try
        {         dialogKeys.setSelected(true);         }
       catch (java.beans.PropertyVetoException e) {}   	  */ 
    /*}*/

 /*   private void displayDialogManagement()
    {
    	dialogManagement = new DialogManagement();
    	dialogManagement.setSize(700,400);
       	Dimension paneSize = desktopPane.getSize();
       	dialogManagement.locateOnCenterOfTheScreen(paneSize.width, paneSize.height);
        dialogManagement.setVisible(true);
        desktopPane.add(dialogManagement,LAYER_FIRST);//number is layer  
        try
        {           dialogManagement.setSelected(true);              }
        catch (java.beans.PropertyVetoException e) {}
    }*/

    private void displayDialogHelp()
    {   //from  http://helpgui.sourceforge.net/documentation.php
    	
    	//JFrame helpFrame = new net.sourceforge.helpgui.gui.MainFrame("/docs/help/","Crystal");
        //helpFrame.setVisible(true);
    	
    	DialogHelp dialogHelp =new DialogHelp(this);
    	
    	dialogHelp.display();
    	//net.sourceforge.helpgui.HelpGui;
    	//HelpGui help= new HelpGui();
    }


 /*   private void displayDialogAbout()
    {
    	DialogAbout dialogAbout = new DialogAbout(this);
       	Dimension paneSize = this.getSize();
       	dialogAbout.locateOnCenterOfTheScreen();
        dialogAbout.setVisible(true);
       // desktopPane.add(dialogAbout,LAYER_FOURTH);//number is layer  
   //    try
  //     {       dialogAbout.setSelected(true);        	      }
   //     catch (java.beans.PropertyVetoException e) {}
    }*/

    private void displayDialogSystemInfo()
    {
    	DialogSystemInfo dialogSystemInfo = new DialogSystemInfo(this);
       	Dimension paneSize = this.getSize();
       	dialogSystemInfo.locateOnCenterOfTheScreen();
        dialogSystemInfo.setVisible(true);
    /*    desktopPane.add(dialogSystemInfo,LAYER_FOURTH);
       try
        {         dialogSystemInfo.setSelected(true);         }
       catch (java.beans.PropertyVetoException e) {}*/
    }
    
    
    private void displayWebsite()
    {
        
        if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    URI uri = new URI("http://www.businesseye.gr");
                    desktop.browse(uri);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                }
        
        }
        
        /* pnlBrowser = new PanelHtmlBrowser();
        pnlBrowser.showUrlText(false);
        pnlBrowser.loadURL("http://www.businesseye.gr");
        panelManagement.addShowTabWithPanel("ιστοσελίδα", null, pnlBrowser , 0);*/
    }

    
    private void checkSilentlyForNewVersion()
    {

        int MINUTES = 60; // The delay in minutes
   Timer timer = new Timer();
   timer.schedule(new TimerTask()
   {
    @Override
    public void run()
    { // Function runs every MINUTES minutes.
        // Run the code you want here
        //CLASSB.funcb(); // If the function you wanted was static
       try
       {
          if (Double.parseDouble(UpdateInfo.getLatestVersion(false).toString()) > Double.parseDouble(VariablesGlobal.appSubVersion.toString().toString()))
          {
               btnCheckForUpdates.setText("νέα έκδοση !!!");
               btnCheckForUpdates.setForeground(Color.RED);
          }
          else
          {
                btnCheckForUpdates.setText(TXTCHECKFORNEWVERSION);
                btnCheckForUpdates.setForeground(Color.black);
          }
          
       }
       catch(UnknownHostException uhe)
       {
            System.out.println("DialogMain.checkSilentlyForNewVersion  UnknownHostException  No internet connection. "+uhe.getMessage());
       }
       catch(java.lang.NumberFormatException nfe)
       {
           System.out.println("DialogMain.checkSilentlyForNewVersion  NumberFormatException  "+nfe.getMessage());
       }
       catch (Exception ex) {
            ex.printStackTrace();
       }                
    }
     }, 0, 1000 * 60 * MINUTES);
    // 1000 milliseconds in a second * 60 per minute * the MINUTES variable. 
    
    }
    
    
    
    private void checkForNewVersion()
    {
        try {
            if (Double.parseDouble(UpdateInfo.getLatestVersion(true).toString()) > Double.parseDouble(VariablesGlobal.appSubVersion.toString().toString()))
            {
               
                if(panelManagement.closeAllTabs())
                {                
                
                    new DialogUpdateInformation(UpdateInfo.getLatestVersion(false),UpdateInfo.getNewDataSize(),UpdateInfo.getVersionRssURL());
                }
            }
            else
            {
                utilsGui.showMessageInfo("Έχετε την τελευταία έκδοση:"+VariablesGlobal.appLeadVersion+"."+VariablesGlobal.appSubVersion.toString());
            }
        }
       catch(UnknownHostException uhe)
       {
            System.out.println("DialogMain.checkForNewVersion  UnknownHostException  No internet connection. "+uhe.getMessage());
       }        
       catch(java.lang.NumberFormatException nfe)
       {
           System.out.println("DialogMain.checkForNewVersion  NumberFormatException  "+nfe.getMessage());
       }        
        catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    
    
    
    private void displayDialogDbConnect()
    { 
        if(panelManagement.closeAllTabs())
        {
               DialogDbConnect dialogSetupDb = new DialogDbConnect(this);
          
               dialogSetupDb.setVisible(true);
               
               lblDbUrl.setText( this.getDBinfo());
        }
    }
    
    
    private void displayDialogLoginUser()
    {
        if(panelManagement.closeAllTabs())
        {
    	 
    	 //panelManagement.panelButtonExpandOrCollapseAll(false);
         
            panelManagement.panelMenuButtonRemove();
            
            DialogUserLogin dialogUserLogin = new DialogUserLogin(this);
            dialogUserLogin.setLoginAgain(true);
            dialogUserLogin.setVisible(true);
            
           if(dialogUserLogin.closeAndContinue())
           {
                userId = dialogUserLogin.getUserId();    
                VariablesGlobal.globalUserId=userId;  //  in displayDialogLoginUser and setCompanyYearUserDate
               lblUser.setText("("+userId+") "+getUserName());        //     +user);//  in displayDialogLoginUser and setCompanyYearUserDate
           }
        }
    }
    
    
    private void displayDialogLoginCompany()
    {
        if(panelManagement.closeAllTabs())
        {
    	 
    	 //panelManagement.panelButtonExpandOrCollapseAll(false);
         
            panelManagement.panelMenuButtonRemove();
            
              DialogCompanyLogin dialogCompanyLogin  = DialogCompanyLogin.getInstance(this); 
              int row = dialogCompanyLogin.getSelectedCompany();
              dialogCompanyLogin.retrieveCompanies();
              dialogCompanyLogin.setSelectedCompany(row);
              
              dialogCompanyLogin.setIsExit(false);//false when called when allready in
              //dialogCompanyLogin.locateOnCenterOfTheScreen();
              dialogCompanyLogin.setVisible(true);
              boolean isOk = dialogCompanyLogin.getIsOkClicked();

         if (isOk == true)
         {
              String strDate = dialogCompanyLogin.getDate();
             String yearId = dialogCompanyLogin.getYearId();
              
            setCompanyYearUserDate(false,dialogCompanyLogin.getCompanyId(), dialogCompanyLogin.getCompanyName(),
                yearId,dialogCompanyLogin.getDbYearDateFrom(yearId),dialogCompanyLogin.getDbYearDateTo(yearId), dialogCompanyLogin.getYearDescr(),userId,
                dialogCompanyLogin.getDate(), dialogCompanyLogin.getCompanyMessage());	
         }
        }  
    }

    /*private void displayDialogConfiguration()
    {
       dialogProgramConfiguration = new DialogProgramConfiguration(this); 
        //dialogConfiguration.setSize(360,300);
       dialogProgramConfiguration.locateOnCenterOfTheScreen();
        dialogProgramConfiguration.setVisible(true);
    }*/

    //create a window listener to respond to the window close click
   private void setCloseClick()
   {
   	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);   
    addWindowListener(new WindowAdapter()
    {
       public void windowClosing(WindowEvent e)
       {    exitApplication();   }
    });

   }
  
       private String getDBinfo()
    {
        String dbUrl="";
     try
     {
        Connection conn = DbConnection.getConnectionFromFile();//get from connection factory
        DatabaseMetaData metaData = conn.getMetaData();
        //database = metaData.getDatabaseProductName();
        //databaseVersion = metaData.getDatabaseProductVersion();
        //driver = metaData.getDriverName();
        //driverVersion = metaData.getDriverVersion();
        dbUrl = metaData.getURL();

        
        DbConnection.releaseConnection(conn);
     }
     catch ( SQLException sqlex)
     {
          System.out.println("error:DialogMain.getDBinfo:"+sqlex.getMessage());
     }
     return dbUrl;
    }
    public void exitApplication()
    {
        if(panelManagement.closeAllTabs())
        {
        	//panelManagement.panelButtonExpandOrCollapseAll(false);
        	
        	System.exit(0);
        }

    	// turn caps lock off
    //	Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, isCapsLockBeforeEnteringApp);
    	
  		
    /* DO NOT DELETE
     * 
     *	 final int YES = 0;
    	 final int NO = 1;
    
    	//System.out.println(" exit "+utilsGui.showConfirm(this, "Σίγουρα θέλετε να κλείσετε την εφαρμογή ;"));
    	if (utilsGui.showConfirmYesOrNo(null, "Σίγουρα θέλετε να κλείσετε την εφαρμογή ;") == YES)
    	{
    		
    		// turn caps lock off
    		Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, isCapsLockBeforeEnteringApp);
            
            System.exit(0);
    	}*/
    }

    
    private String getUserName()
    {
        String query = "SELECT username FROM dbuser WHERE userId LIKE "+userId;
        String username="";
  //rs = db.retrieveResultSet(query);
      try
      {
          db.retrieveDBDataFromQuery(query,"DialogMain.getUserName");
           //db.retrieveDBDataFromQueryPreparedStatement(query,"DialogCompanyLogin.checkUserNPassFordbCompanyId");     ' OR '1'='1 
   	   ResultSet rs=db.getRS();
   	   //rsmd=db.getRSMetaData();
         
         
         //System.out.println("DialogCompanyLogin.checkUserNPassFordbCompanyId  hasOneOnlyRow="+hasOneOnlyRow);
         if ( rs.next() ) // for mysql, h2 and sqlite   //rs.isFirst()) //hasOneOnlyRow !=0 ) 
         { 
               username =  rs.getString("username");  //hasOneOnlyRow; // the first and only
              
             
         }
         else
         {
           	
           	System.out.println("error:    DialogMain.getUserName:   userId:"+userId+"  no username ");
         }
          //System.out.println("DialogCompanyLogin.checkUserNPassFordbCompanyId  hasOneOnlyRow="+hasOneOnlyRow);
          
           //db.releaseConnectionRs();
           //setListData(Object[] listData) 
       }
       catch ( SQLException sqlex)
       {
           System.out.println("error:    DialogMain.getUserName:   "+sqlex.getMessage());
           //sqlex.printStackTrace();
       }
      finally
      {
             closeDB();     
      }        
        
        return username;
        
    }
    
     private void closeDB()
    {
    	db.releaseConnectionRs();
        db.releaseConnectionRsmd();
    }   
    
    //public because DialogCompanyLogin.closeAndShowMainDialog() also uses it
    public void setCompanyYearUserDate(boolean isNotLoginAgain,int companyId, String company,String yearId, String yearDateFrom,  String yearDateTo, String yearDescr, String userIdIn,
            String date,String companyMessage)
    {
          
        userId=userIdIn;
    	UtilsDate utildate = new UtilsDate();
    	String[] allowedPatternsToRead = {"dd-MM-yyyy","dd/MM/yy","dd-MM-yy", "dd/MM/yyyy"};

        lblCompany.setText("("+companyId+") "+company);
        lblYear.setText("("+yearId+")"+yearDescr);
        lblUser.setText("("+userId+") "+getUserName());        //     +user);//  in displayDialogLoginUser and setCompanyYearUserDate

        String fdate=utildate.reformatDateString(date,allowedPatternsToRead,"EEEEEEEEEEEEE dd-MM-yyyy");
        lblDate.setText(fdate);
        lblDbUrl.setText( this.getDBinfo());
        
        VariablesGlobal.globalUserId=userId;  //  in displayDialogLoginUser and setCompanyYearUserDate
        VariablesGlobal.globalCompanyId=Integer.toString(companyId);
        VariablesGlobal.globalCompanyName=company;
        VariablesGlobal.globalYearId=yearId;
        VariablesGlobal.globalDateFrom = yearDateFrom;
        VariablesGlobal.globalDateTo = yearDateTo;
        VariablesGlobal.globalYearDescr=yearDescr;
 //       VariablesGlobal.globalYear = /// globalYear to be deleted
        
        String globDate=utildate.reformatDateString(date,allowedPatternsToRead,"dd-MM-yyyy");
        VariablesGlobal.globalDate=globDate;
        
        utilsDouble.getSettingsFromDb();
        VariablesGlobal.globalUtilsDouble = utilsDouble;
        //panelManagement.initializePanels();
        

        panelManagement.setDate(date);
        
  
        // called here because if called in initialization it will ad it more times if more times logged in
        menuBar= new JMenuBar();
        setJMenuBar(menuBar);  
         loadFixedMenu(menuBar);
         loadEntitySectionsMenu(menuBar);
         loadFixedMenuLast(menuBar);
         

       if(companyMessage == null || companyMessage.trim().equalsIgnoreCase(""))
       {
           lblMessageFromDbCompany.setVisible(false);
           panelBottomMsg.setVisible(false);
       }
       else
       {
           lblMessageFromDbCompany.setVisible(true);
            panelBottomMsg.setVisible(true);
        lblMessageFromDbCompany.setText(companyMessage);
       }
        

     //  if(isNotLoginAgain)
    //   {
       loadEntitySectionsButtons();
       checkSilentlyForNewVersion(); 
    //   }
       
    }

    

    
    
    /*
    
    */
    /*private void setModuleInPanelManagement(ArrayList listBtnModule, DataTreeNode dataTreeNode)
    {
        panelManagement.setSectionActive(listBtnModule,dataTreeNode);
        
    }*/

    private void loadSectionsFromEntityData()
    {
        
        
                EntityData ed = new EntityData();
       listSections = ed.loadAndGetAllEntitySections();

      for(int n=0;n<listSections.size();n++) // -------- level 0
      {
          
          //DataTreeNode dtnr = dTreeNodeSections.getChildFromIndex(n);
          //EntityMenu emCat = (EntityMenu)dtnr.getData();//dTreeNodeRoot.getChildFromIndex(n);
          
          
       /* DataTreeNode dTreeNodeChild = ed.loadAndGetAllEntitySections();
              //DataTreeNode dTreeNodeRoot = dTreeSections.getRootElement();
      int countChildren = dTreeNodeChild.getNumberOfChildren();            
          for(int c = 0;c<countChildren;c++)
          {
              DataTreeNode dtnrChild = dTreeNodeChild.getChildFromIndex(c);
              EntityMenu emCatChild = (EntityMenu)dtnrChild.getData();
              System.out.println("loadSectionsInPanelManagement  "+c+" "+n+"  getEntityType:"+emCatChild.getEntityType()+"  "+emCatChild.getEntityCaption());
          
          }*/
 
       
     
/*       EntityMenu em = (EntityMenu)listSections.get(n);
   //           System.out.println("loadSectionsFromEntityData   "+n+"  getEntityType:"+em.getEntityType()+"  "+em.getEntityCaption());
      DataTree dt = em.getEntitySectionDataTree();
      DataTreeNode dTreeNode = dt.getRootElement();
      int countChildren = dTreeNode.getNumberOfChildren();            
          for(int c = 0;c<countChildren;c++)
          {   
              
              DataTreeNode dtnrChild = dTreeNode.getChildFromIndex(c);
              EntityMenu emCatChild = (EntityMenu)dtnrChild.getData();
              //System.out.println("loadSectionsFromEntityData  "+c+" "+n+"  getEntityType:"+emCatChild.getEntityType()+"  "+emCatChild.getEntityCaption());              
          }
      
    */ 
      }
        
    }
    
    
    private void setSectionActive(int intSection)
    {
       /* JToggleButton listSelectedModuleBtn = (JToggleButton)listBtnModule.get(intSection);
     if(listSelectedModuleBtn.isSelected()){
    System.out.println("button intSection:"+intSection+"  is selected");
} else {
    System.out.println("button intSection:"+intSection+"  is not selected");
    panelManagement.setSectionActive(listSections,intSection);
}*/   
        if(intModuleSelected==intSection)
        {
    
        }
        else
        {
            panelManagement.setSectionActive(listSections,intSection);
            intModuleSelected=intSection;
        }

    }
    
    
    
	public void changeLookAndFeel(String selection)
	{
        
   	  try
   	  {
		 UIManager.setLookAndFeel(selection); 
		 SwingUtilities.updateComponentTreeUI(DialogMain.this);
	  }
      catch (UnsupportedLookAndFeelException exc)
        {System.err.println("DialogConfiguration:UnsupportedLookAndFeel: "+selection+exc);}
      catch (Exception exc)
        {System.err.println("Error "+selection+": "+exc);} 
		
		//prefs.put(ConfigConstants.KEY_LOOK_N_FEEL, selection);
	}
    
    private void loadFixedMenu(JMenuBar menuBarIn)
    {


       JMenu menuFunctions;
      
       JMenuItem mitemFuncLoginCompany = new JMenuItem();
       JMenuItem mitemFuncLoginUser = new JMenuItem();
     //  JMenuItem mitemWindowNew = new JMenuItem();

     
       JMenuItem mitemFuncExit = new JMenuItem();
   /*   private JMenuItem mitemRepReport1 = new JMenuItem();
      private JMenuItem mitemRepReport2 = new JMenuItem();
      private JMenuItem mitemRepReport3 = new JMenuItem();*/
      
      

        
        	
        	mitemVisTreeMenu = new  JCheckBoxMenuItem();
           mitemVisToolbarMain = new  JCheckBoxMenuItem();
           
            // functions menu -------------------------------------------------------
            menuFunctions = new JMenu("Λειτουργίες");
            //menuFunctions.setMnemonic(KeyEvent.VK_ALT);
            
            //menuitemFuncLogin.setEnabled(false);
            mitemFuncLoginCompany.setIcon(ICO_USER16);
            mitemFuncLoginCompany.setText("αλλαγή εταιρίας");
            mitemFuncLoginCompany.setAccelerator(KeyStroke.getKeyStroke('L', KeyEvent.CTRL_DOWN_MASK));
            mitemFuncLoginCompany.getAccessibleContext().setAccessibleDescription("Login company function");
            mitemFuncLoginCompany.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
            {    displayDialogLoginCompany();       }
            });
            
            
            mitemFuncLoginUser.setIcon(ICO_USER16);
            mitemFuncLoginUser.setText("αλλαγή χρήστη");
            //mitemFuncLoginUser.setAccelerator(KeyStroke.getKeyStroke('L', KeyEvent.CTRL_MASK));
            mitemFuncLoginUser.getAccessibleContext().setAccessibleDescription("Login user function");
            mitemFuncLoginUser.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
            {    displayDialogLoginUser();       }
            });            
            
            
   /*         mitemFuncManage.setText("Διαχείρηση");
            mitemFuncManage.setAccelerator(KeyStroke.getKeyStroke('M', KeyEvent.CTRL_MASK));
            mitemFuncManage.getAccessibleContext().setAccessibleDescription("Login function");
            mitemFuncManage.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
            {    displayDialogManagement();       }
            });*/
            //menuConfig = new JMenu("Παραμετροποίηση");
            //menuConfig.setMnemonic(KeyEvent.VK_Π);
            
            //mitemConfigOptions.setEnabled(false);
            
            
            
        /*    mitemWindowNew.setIcon(ICO_WINDOWNEW16);
            mitemWindowNew.setText("νέο παράθυρο");
            mitemWindowNew.setAccelerator(KeyStroke.getKeyStroke('N', KeyEvent.CTRL_MASK));
            //mitemWindowNew.getAccessibleContext().setAccessibleDescription("Login function");
            mitemWindowNew.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
            {    displayWindowNew();       }
            });
          */  
                        

            
            
            //mitemVisTreeMenu.setIcon(ICO_CONFIG16);
            mitemVisTreeMenu.setText("εμφάνιση δενδρικού μενού");
            mitemVisTreeMenu.setSelected(true);
            //mitemVisTreeMenu.setAccelerator(KeyStroke.getKeyStroke('R', KeyEvent.CTRL_MASK));
            mitemVisTreeMenu.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
            {    

               	setTreePanelVisible();

            }
            });




            //mitemVisTreeMenu.setIcon(ICO_CONFIG16);
            mitemVisToolbarMain.setText("εμφάνιση κύριας μπάρας");
            mitemVisToolbarMain.setSelected(true);
            //mitemVisTreeMenu.setAccelerator(KeyStroke.getKeyStroke('R', KeyEvent.CTRL_MASK));
            mitemVisToolbarMain.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
            {    
                if(toolBarMain.isVisible())
                {
                	toolBarMain.setVisible(false);
                }
                else
                {
                	toolBarMain.setVisible(true);
                }
               	

            }
            });






            
           // add items to configure menu
            //menuConfig.add();
            
            //redoItem.setEnabled(false);
            mitemFuncExit.setIcon(ICO_EXIT16);
            mitemFuncExit.setText("exit");
            mitemFuncExit.setAccelerator(KeyStroke.getKeyStroke('E', KeyEvent.CTRL_DOWN_MASK));
            mitemFuncExit.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
            {    exitApplication();      }
            });
           
           // add items to functions menu
            menuFunctions.add(mitemFuncLoginCompany);
            menuFunctions.add(mitemFuncLoginUser);
           // menuFunctions.add(mitemWindowNew);
            menuFunctions.addSeparator();
            menuFunctions.add(mitemVisTreeMenu);
            menuFunctions.add(mitemVisToolbarMain);

            menuFunctions.addSeparator();
            menuFunctions.add(mitemFuncExit);
           //--------------------

             

             

         
            menuBarIn.add(menuFunctions);



      


    	
    }
    
    
    private void loadFixedMenuLast(JMenuBar menuBarIn)
    {
        
    
        //JMenuItem mitemConfigOptions = new JMenuItem();
    //private JCheckBoxMenuItem mitemVisTreeMenu = new  JCheckBoxMenuItem();
     //private JCheckBoxMenuItem mitemVisToolbarMain = new  JCheckBoxMenuItem();
     JMenuItem mitemDbConnect = new JMenuItem(); 
     JMenuItem mitemBackupRestore = new JMenuItem();    
     JMenuItem mitemQueryBrowser = new JMenuItem();  
     JMenuItem mitemWebsite = new JMenuItem(); 
      JMenu menuTools; 
        
    	
       JMenuItem mitemHelpKeys  = new JMenuItem();
       JMenuItem mitemHelpSystemInfo  = new JMenuItem();
       JMenuItem mitemHelpAbout = new JMenuItem();
       JMenu menuHelp;
    	
              
            // tools menu -------------------------------------------------------
           menuTools = new JMenu("tools");       
           /*
              mitemConfigOptions.setIcon(ICO_CONFIG16);
            mitemConfigOptions.setText("options");
            mitemConfigOptions.setAccelerator(KeyStroke.getKeyStroke('R', KeyEvent.CTRL_DOWN_MASK));
            mitemConfigOptions.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
            {    displayDialogConfiguration();  }
            });
            */
  
            
          mitemBackupRestore.setText("back up/restore");
          //btnBackupRestore.setOpaque(false);
          mitemBackupRestore.setToolTipText("αποθήκευση / επαναφορά");
          mitemBackupRestore.setIcon(ICO_BACKUP);
          mitemBackupRestore.setMnemonic(KeyEvent.VK_B);
          //mitemBackupRestore.setFocusable(false);
          mitemBackupRestore.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {     displayDialogBackupRestore();    
	        }//displayCalendarDialog(btnConfig);
	  });
          
          
          mitemDbConnect.setText("connect to database");
          //mitemDbConnect.setToolTipText("αποθήκευση / επαναφορά");
          mitemDbConnect.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {          
          displayDialogDbConnect();
                }
          });
          
 
          mitemQueryBrowser.setText("query browser");
          //mitemQueryBrowser.setOpaque(false);
          mitemQueryBrowser.setToolTipText("query browser");
          //mitemQueryBrowser.setIcon(ICO_BACKUP);
          mitemQueryBrowser.setMnemonic(KeyEvent.VK_Q);
          //mitemBackupRestore.setFocusable(false);
          mitemQueryBrowser.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {     displayDialogQueryBrowser();  
	        }//displayCalendarDialog(btnConfig);
	  });
          
          
       
          
            
         // menuTools.addSeparator();
        //  menuTools.add(mitemDbConnect);
          menuTools.add(mitemBackupRestore); 
          menuTools.add(mitemQueryBrowser);
          //menuTools.add(mitemConfigOptions);
          
          
          menuBarIn.add(menuTools);  
            
              
            // help menu -------------------------------------------------------
            menuHelp = new JMenu("Βοήθεια");
            //menuHelp.setMnemonic(KeyEvent.VK_Π);
            
            //mitemHelpKeys.setIcon(ICO_SYSTINFO16);
            mitemHelpKeys.setText("πλήκτρα συντόμευσης");
            mitemHelpKeys.setAccelerator(KeyStroke.getKeyStroke('K', KeyEvent.CTRL_DOWN_MASK));
            
            mitemHelpKeys.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
            {    displayDialogKeys();       }
            });

            
            //mitemConfigOptions.setEnabled(false);
            mitemHelpSystemInfo.setIcon(ICO_SYSTINFO16);
            mitemHelpSystemInfo.setText("πληροφορίες συστήματος");
            mitemHelpSystemInfo.setAccelerator(KeyStroke.getKeyStroke('I', KeyEvent.CTRL_DOWN_MASK));
            mitemHelpSystemInfo.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
            {    displayDialogSystemInfo();       }
            });

           
          
          mitemWebsite.setText("ιστοσελίδα");
          //mitemQueryBrowser.setOpaque(false);
          mitemWebsite.setToolTipText("ιστοσελίδα");
          //mitemQueryBrowser.setIcon(ICO_BACKUP);
         // mitemQueryBrowser.setMnemonic(KeyEvent.VK_Q);
          //mitemBackupRestore.setFocusable(false);
          mitemWebsite.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {     displayWebsite();  
	        }//displayCalendarDialog(btnConfig);
	  }); 
          
           // add items to help menu
            menuHelp.add(mitemHelpKeys);
            menuHelp.add(mitemHelpSystemInfo); 
            menuHelp.add(mitemWebsite);            
    	    menuBarIn.add(menuHelp);
    }
    
      
    
    
    private void loadEntitySectionsButtons()
    {
       toolBarMain.removeAll();
      
       
       
        loadSectionsFromEntityData();
        //loadEntitySectionsMenu();   
       
        
        //boolean[] isNodeVisible ={true, true, true, true,true};
        ArrayList listSectionEntityMenu = new ArrayList();
        EntityData ed = new EntityData();


        
         listSections = ed.loadAndGetAllEntitySections();
              //DataTreeNode dTreeNodeRoot = dTreeSections.getRootElement();
      //int countChildren = dTreeNodeSections.getNumberOfChildren();         
      int countChildren = listSections.size();         

      //DataTreeNode dTreeNodeChild = dTreeNodeRoot.getChildFromIndex(n);  
    
//      listBtnModule.removeAll();
      ButtonGroup buttonGroup = new ButtonGroup();
      // ActionListener listener = actionEvent -> System.out.println(actionEvent.getActionCommand() + " Selected");
      for(int n=0;n<countChildren;n++) // -------- level 0
      {
          btnModule = new JToggleButton();
          //DataTreeNode dtnr = dTreeNodeSections.getChildFromIndex(n);
          //EntityMenu emCat = (EntityMenu)dtnr.getData();//dTreeNodeRoot.getChildFromIndex(n);
          
          EntityMenu emCat = (EntityMenu)listSections.get(n);

         
          
          btnModule.setText(emCat.getEntityCaption());
          btnModule.setOpaque(false);
          btnModule.setToolTipText(emCat.getEntityCaption());
          btnModule.setIcon(emCat.getEntityIcon());//ICO_REPORTDOCUMENT);
         // btnModule1.setMnemonic(KeyEvent.VK_1);//.VK_1);
          btnModule.setFocusable(false);
          listSectionEntityMenu.add(n,emCat);
          listBtnModule.add(n,btnModule);
          buttonGroup.add(btnModule);
          /*JToggleButton listSelectedModuleBtn = (JToggleButton)listBtnModule.get(n);
 listSelectedModuleBtn.addItemListener(new ItemListener() {
   public void itemStateChanged(ItemEvent ev) {
      if(ev.getStateChange()==ItemEvent.SELECTED){
        System.out.println("button is selected");
      } else if(ev.getStateChange()==ItemEvent.DESELECTED){
        System.out.println("button is not selected");
      }
   }
});  */        
          //final JButton btnModuleFinal=  btnModule;
          final int finalN = n;
          //final DataTreeNode finalDtnr = dtnr;
          //final ArrayList listSectionEntityMenuFinal = listSectionEntityMenu;
         
          
          
         // btnModule.addActionListener(listener);
          btnModule.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {  
                                  setSectionActive(finalN);

	        }
	   });  
          
          
         toolBarMain.add(btnModule,n);

      }
      
      
      toolBarMain.addMainButtons();
      

        
    }    
 
    
    

    
    /*
    * for application menu
    * 
    */
    
    private void loadEntitySectionsMenu(JMenuBar menuBarIn)
    {
      
                
       //toolBarMain.removeAll();
      
       
       
        loadSectionsFromEntityData();
        //loadEntitySectionsMenu();   
       
        
        //boolean[] isNodeVisible ={true, true, true, true,true};
        ArrayList listSectionEntityMenu = new ArrayList();
        EntityData ed = new EntityData();


        
         listSections = ed.loadAndGetAllEntitySections();
              //DataTreeNode dTreeNodeRoot = dTreeSections.getRootElement();
      //int countChildren = dTreeNodeSections.getNumberOfChildren();         
      int countChildren = listSections.size();         

      //DataTreeNode dTreeNodeChild = dTreeNodeRoot.getChildFromIndex(n);  
    
//      listBtnModule.removeAll();
      //ButtonGroup buttonGroup = new ButtonGroup();
      for(int n=0;n<countChildren;n++) // -------- level 0
      {
          
          EntityMenu emCat = (EntityMenu)listSections.get(n);

       JMenu menuFunctions;

            // functions menu -------------------------------------------------------
            menuFunctions = new JMenu(emCat.getEntityCaption());          
          
 
          loadMenuFromDataTree(n,menuFunctions);
          
          menuBarIn.add(menuFunctions);
          
      }
      
    /*           EntityData ed = new EntityData();
       listSections = ed.loadAndGetAllEntitySections();       

       int countChildren = listSections.size(); 
      
      
      for(int n=0;n<countChildren;n++) // -------- level 0
      {
          
          
         loadMenuFromDataTree(n);          
          
          
          //btnModule = new JButton();
          //DataTreeNode dtnr = dTreeNodeSections.getChildFromIndex(n);
          //final EntityMenu emCat = (EntityMenu)dtnr.getData();//dTreeNodeRoot.getChildFromIndex(n); 
 /*         final EntityMenu emCat = (EntityMenu)listSections.get(n);
          JMenu menu = new JMenu();
                    menu.setText(emCat.getEntityCaption());
          
          //menu.setToolTipText(emCat.getEntityCaption());
          menu.setIcon(emCat.getEntityIcon());//ICO_REPORTDOCUMENT);

           System.out.println("DialogMain.loadEntitySectionsMenu      countChildren"+countChildren+"    "+n+"      emCat.getEntityCaption()"+emCat.getEntityCaption());
*/
          
      

 
     
    /*   EntityMenu em = (EntityMenu)listSections.get(n);
              //System.out.println("setSectionActive   "+e+"  getEntityType:"+em.getEntityType()+"  "+em.getEntityCaption());
      DataTree dTreeSections = em.getEntitySectionDataTree();
   DataTreeNode dTreeNodeRoot = dTreeSections.getRootElement();
      //int countChildren = dTreeNode.getNumberOfChildren();    
       
     */       
             
    
      
     //    }        
        

        
    }
    
    
    
    private void loadMenuFromDataTree(int intSection, JMenu menuFunctions)
    {

    
    
       EntityMenu em = (EntityMenu)listSections.get(intSection);
              //System.out.println("setSectionActive   "+e+"  getEntityType:"+em.getEntityType()+"  "+em.getEntityCaption());
      DataTree dTreeSections = em.getEntitySectionDataTree();
   DataTreeNode dTreeNodeRoot = dTreeSections.getRootElement();        

   
   
  
   
   
   
   
        
        
        
      EntityMenu entityMenu = em;
     // EntityMenu entityMenuParent = new EntityMenu();
      
 //     DataTreeNode dTreeNodeRoot = dataTreeNodeIn;
     int countChildren = dTreeNodeRoot.getNumberOfChildren(); //dTreeSections.getRootElement().getNumberOfChildren();    
      //int countChildren = dataTreeNodeIn
      //int countChildren = dataTreeIn.getRootElement().getNumberOfChildren(); 
 
   int count = 0;     
      /*for(int n=0;n<countChildren;n++) // -------- level 0
      {*/
          
      //int n= intSection;
            
         JMenu mainMenu = menuFunctions;//new JMenu(dTreeNodeRoot.getChildFromIndex(n).toString());
    	 //menuBarIn.add(mainMenu);
        // menuBar.add(mainMenu);
         
    	 DataTreeNode dTreeNodeChild = dTreeNodeRoot;//dTreeNodeRoot.getChildFromIndex(n);  
    	      
    	 if(dTreeNodeChild.hasNodeChildren())//----------- level 1  --------------------------------
    	 {
    	      
    	     for(int v=0;v<dTreeNodeChild.getNumberOfChildren();v++)
      	     {

                   DataTreeNode dTreeNodeChild2 = dTreeNodeChild.getChildFromIndex(v);
    	        
                   entityMenu = (EntityMenu)dTreeNodeChild2.getData();      
   //                entityMenuParent = (EntityMenu)dTreeNodeChild.getData();      
                    
    	        
    	          JMenuItem mitem  = new JMenuItem(dTreeNodeChild.getChildFromIndex(v).toString());
    //-              mainMenu.add(mitem);//mitem);
    	          if(entityMenu.getEntityIcon()!=null)
    	          {
    	              mitem.setIcon(entityMenu.getEntityIcon());	
    	          }
    	          else
    	          {
    //	          	  mitem.setIcon(entityMenuParent.getEntityIcon());
    	          }
    	          

    	          final EntityMenu emFinal = entityMenu;
    	          mitem.addActionListener(new ActionListener()
                  {
                      public void actionPerformed(ActionEvent e)
                      {
                      	    //menuSelection( dtNodeFianal, idxOfTreeFinal);//displayDialogLogin(); 
                      	    menuSelection(emFinal);
                      }
                  });
    	              	 		

    	                if(dTreeNodeChild2.hasNodeChildren())//------------- level 2  --------------------------------
    	                {
            	              JMenu subMenu2 = new JMenu();//dTreeNodeChild.getChildFromIndex(v).toString());
            	             // mainMenu.add(subMenu2);
    	                     for(int h=0;h<dTreeNodeChild2.getNumberOfChildren();h++)
      	                     {               
      	                         //System.out.println("PanelManagement.presentJtreeFromDataTree +"+dTreeNodeChild.getChildFromIndex(v));
                                 subMenu2.setText(dTreeNodeChild2.getChildFromIndex(h).toString());
                                 DataTreeNode dTreeNodeChild3 = dTreeNodeChild2.getChildFromIndex(h);
    	                           entityMenu = (EntityMenu)dTreeNodeChild3.getData();
    	             //              entityMenuParent = (EntityMenu)dTreeNodeChild.getData();   
    	                           
    	                           JMenuItem mitem2  = new JMenuItem(dTreeNodeChild2.getChildFromIndex(h).toString());
                                  
                                   mainMenu.add(mitem2);
    	                           if(entityMenu.getEntityIcon()!=null)
    	                           {
    	                               mitem2.setIcon(entityMenu.getEntityIcon());	
    	                           }
    	                           else
    	                           {
    	          	//                   mitem2.setIcon(entityMenuParent.getEntityIcon());
    	                            }
    	                           
    	                           final EntityMenu em2 = entityMenu;
    	                           mitem2.addActionListener(new ActionListener()
                                   {
                                       public void actionPerformed(ActionEvent e)
                                       {
                                        	    menuSelection(em2); //displayDialogLogin(); 
                                       }
                                   }); 
                                      	                           
    	                           
    	                        if(dTreeNodeChild3.hasNodeChildren())//----------- level 3 -----------------------------
    	                        {
            	                    JMenu subMenu3 = new JMenu();
    	                             for(int k=0;k<dTreeNodeChild3.getNumberOfChildren();k++)
      	                             {
                                         subMenu3.setText(dTreeNodeChild2.getChildFromIndex(h).toString());
                                        
                                         

                                      DataTreeNode dTreeNodeChild4 = dTreeNodeChild3.getChildFromIndex(k);
    	                              entityMenu = (EntityMenu)dTreeNodeChild4.getData();
    	               //               entityMenuParent = (EntityMenu)dTreeNodeChild.getData();   
    	                              
    	                             JMenuItem mitem3  = new JMenuItem(dTreeNodeChild3.getChildFromIndex(k).toString());
                                     subMenu3.add(mitem3);
    	                             
    	                             if(entityMenu.getEntityIcon()!=null)
    	                             {
    	                                mitem3.setIcon(entityMenu.getEntityIcon());	
    	                             }
    	                             else
    	                             {
    	          	 //                    mitem3.setIcon(entityMenuParent.getEntityIcon());
    	                             }
    	                             
    	                             
    	                             final EntityMenu em3 = entityMenu;
    	                             mitem3.addActionListener(new ActionListener()
                                     {
                                          public void actionPerformed(ActionEvent e)
                                          {
                      	                      menuSelection(em3);//displayDialogLogin(); 
                                          }
                                     });    	                             
    	                             
    	 		                         
    	 		                          
    	 		    	               if(dTreeNodeChild4.hasNodeChildren())//------------- level 4  -----------------------------
    	                               {
    	 		                         System.out.println("error DialogMain.presentMenuFromDataTree level 4 of tree not supported");
    	 		                       }
    	 		                       else
    	 		                       {// else (dTreeNodeChild4.hasNodeChildren())//------------- level 4  -----------------------------
    	 		                       	//  subMenu3.add(mitem3);
    	 		                       }

                          	         }
    	 		                 
                                          mainMenu.add(subMenu3);
                                           subMenu2.add(mitem2);
                                     
    	                        } // (dTreeNodeChild3.hasNodeChildren())//----------- level 3 -----------------------------
    	                        else
    	                        {

    	                        }

                                  
    	 		
                          	     }// for 2
                          	      
 

    	                } // else (dTreeNodeChild2.hasNodeChildren())//------------- level 2  --------------------------------
    	                else
    	                {
    	                	mainMenu.add(mitem);  
    	                }
    	 		
    	 		
    	 		
    	 		
    	     }

    	 }    	          
    	          
      
     // } // n

    	
    }
  
/*
    *    with lots of submenus
    */
private void loadSubMenusFromDataTree(int intSection)
    {

    
    
       EntityMenu em = (EntityMenu)listSections.get(intSection);
              //System.out.println("setSectionActive   "+e+"  getEntityType:"+em.getEntityType()+"  "+em.getEntityCaption());
      DataTree dTreeSections = em.getEntitySectionDataTree();
   DataTreeNode dTreeNodeRoot = dTreeSections.getRootElement();        
        
        
        
        
      EntityMenu entityMenu = new EntityMenu();
      EntityMenu entityMenuParent = new EntityMenu();
      
 //     DataTreeNode dTreeNodeRoot = dataTreeNodeIn;
     int countChildren = dTreeSections.getRootElement().getNumberOfChildren();    
      //int countChildren = dataTreeNodeIn
      //int countChildren = dataTreeIn.getRootElement().getNumberOfChildren(); 
      int count = 0;     
      for(int n=0;n<countChildren;n++) // -------- level 0
      {
          
      
            
         JMenu mainMenu = new JMenu(dTreeNodeRoot.getChildFromIndex(n).toString());
    	 //menuBarIn.add(mainMenu);
         menuBar.add(mainMenu);
         
    	 DataTreeNode dTreeNodeChild = dTreeNodeRoot.getChildFromIndex(n);  
    	      
    	 if(dTreeNodeChild.hasNodeChildren())//----------- level 1  --------------------------------
    	 {
    	      
    	     for(int v=0;v<dTreeNodeChild.getNumberOfChildren();v++)
      	     {

                   DataTreeNode dTreeNodeChild2 = dTreeNodeChild.getChildFromIndex(v);
    	        
                   entityMenu = (EntityMenu)dTreeNodeChild2.getData();      
                   entityMenuParent = (EntityMenu)dTreeNodeChild.getData();      
                   
    	        
    	          JMenuItem mitem  = new JMenuItem(dTreeNodeChild.getChildFromIndex(v).toString());
    	          if(entityMenu.getEntityIcon()!=null)
    	          {
    	              mitem.setIcon(entityMenu.getEntityIcon());	
    	          }
    	          else
    	          {
    	          	  mitem.setIcon(entityMenuParent.getEntityIcon());
    	          }
    	          
    	          
    	          int idxOfTree = 0;
    	          
    	          
    	          // find count of previous node children
    	          if(n!=0 )
    	          {
    	            DataTreeNode dTreeNodeChildPre = dTreeNodeRoot.getChildFromIndex(n-1); 
    	            if( v==0)
    	            {
    	                count = count+ dTreeNodeChildPre.getNumberOfChildren();    	          		
    	            }
    	            
    	          }

    	          
    	          final int idxOfTreeFinal =count+1+v+1;
    	          //System.out.println("DialogMain.loadMenuFromDataTree  n"+n+" v"+v+" "+(n+v)+" count"+count+" "+idxOfTreeFinal); 
    	          final DataTreeNode dtNodeFianal = dTreeNodeChild.getChildFromIndex(v);
//    	          final EntityMenu entityTypeFinal = (EntityMenu)dTreeNodeChild.getChildFromIndex(v);     //.getEntityType();
    	          final EntityMenu emFinal = entityMenu;
    	          mitem.addActionListener(new ActionListener()
                  {
                      public void actionPerformed(ActionEvent e)
                      {
                      	    //menuSelection( dtNodeFianal, idxOfTreeFinal);//displayDialogLogin(); 
                      	    menuSelection(emFinal);
                      }
                  });
    	              	 		

    	                if(dTreeNodeChild2.hasNodeChildren())//------------- level 2  --------------------------------
    	                {
            	              JMenu subMenu2 = new JMenu(dTreeNodeChild.getChildFromIndex(v).toString());
            	              mainMenu.add(subMenu2);
    	                     for(int h=0;h<dTreeNodeChild2.getNumberOfChildren();h++)
      	                     {               
      	                         //System.out.println("PanelManagement.presentJtreeFromDataTree +"+dTreeNodeChild.getChildFromIndex(v));

                                 DataTreeNode dTreeNodeChild3 = dTreeNodeChild2.getChildFromIndex(h);
    	                           entityMenu = (EntityMenu)dTreeNodeChild3.getData();
    	                           entityMenuParent = (EntityMenu)dTreeNodeChild.getData();   
    	                           
    	                           JMenuItem mitem2  = new JMenuItem(dTreeNodeChild2.getChildFromIndex(h).toString());
    	                           //mitem2.setIcon(entityMenu.getEntityIcon());
    	                           if(entityMenu.getEntityIcon()!=null)
    	                           {
    	                               mitem2.setIcon(entityMenu.getEntityIcon());	
    	                           }
    	                           else
    	                           {
    	          	                   mitem2.setIcon(entityMenuParent.getEntityIcon());
    	                            }
    	                           
    	                           final EntityMenu em2 = entityMenu;
    	                           mitem2.addActionListener(new ActionListener()
                                   {
                                       public void actionPerformed(ActionEvent e)
                                       {
                                        	    menuSelection(em2); //displayDialogLogin(); 
                                       }
                                   }); 
                                      	                           
    	                           
    	                        if(dTreeNodeChild3.hasNodeChildren())//----------- level 3 -----------------------------
    	                        {
            	                     JMenu subMenu3 = new JMenu(dTreeNodeChild2.getChildFromIndex(v).toString());
            	                     subMenu2.add(subMenu3); 
    	                             for(int k=0;k<dTreeNodeChild3.getNumberOfChildren();k++)
      	                             {
                   
      	                              //System.out.println("PanelManagement.presentJtreeFromDataTree +"+dTreeNodeChild.getChildFromIndex(v));
                                      DataTreeNode dTreeNodeChild4 = dTreeNodeChild3.getChildFromIndex(k);
    	                              entityMenu = (EntityMenu)dTreeNodeChild4.getData();
    	                              entityMenuParent = (EntityMenu)dTreeNodeChild.getData();   
    	                              
    	                             JMenuItem mitem3  = new JMenuItem(dTreeNodeChild3.getChildFromIndex(k).toString());
    	                             
    	                             if(entityMenu.getEntityIcon()!=null)
    	                             {
    	                                mitem3.setIcon(entityMenu.getEntityIcon());	
    	                             }
    	                             else
    	                             {
    	          	                     mitem3.setIcon(entityMenuParent.getEntityIcon());
    	                             }
    	                             
    	                             
    	                             final EntityMenu em3 = entityMenu;
    	                             mitem3.addActionListener(new ActionListener()
                                     {
                                          public void actionPerformed(ActionEvent e)
                                          {
                      	                      menuSelection(em3);//displayDialogLogin(); 
                                          }
                                     });    	                             
    	                             
    	 		                         
    	 		                          
    	 		    	               if(dTreeNodeChild4.hasNodeChildren())//------------- level 4  -----------------------------
    	                               {
    	 		                         System.out.println("error DialogMain.presentMenuFromDataTree level 4 of tree not supported");
    	 		                       }
    	 		                       else
    	 		                       {// else (dTreeNodeChild4.hasNodeChildren())//------------- level 4  -----------------------------
    	 		                       	  subMenu3.add(mitem3);
    	 		                       }
    	 		
                          	         }
                                     
                                     //subMenu2.add(subMenu3);
    	                        } // (dTreeNodeChild3.hasNodeChildren())//----------- level 3 -----------------------------
    	                        else
    	                        {
    	                        	subMenu2.add(mitem2);
    	                        }


    	 		
                          	     }// for 2
                          	     
                          	     //mainMenu.add(subMenu2);

    	                } // else (dTreeNodeChild2.hasNodeChildren())//------------- level 2  --------------------------------
    	                else
    	                {
    	                	mainMenu.add(mitem);  
    	                }
    	 		
    	 		
    	 		
    	 		
    	     }

    	 }    	          
    	          
      
      }
 //System.out.println("DialogMain.presentMenuFromDataTree");

/*ChangeListener updateListener = new ChangeListener() {
public void stateChanged(ChangeEvent e) {
MenuElement[] menus = manager.getSelectedPath();
if (menus != null && menus.length > 0)
{
	     //JMenuItem menu = new JMenuItem("dynamic");
         //lazyMenu.add(menu);
//loadIfNecessary(menus[menus.length - 1]);
}
}
};
manager.addChangeListener(updateListener);*/
 

  	

    	
    }


    
    /*private void menuSelection( Object entity, int intMenu)
    { 
         
        panelManagement.setMenuObject(entity,intMenu); 
        panelManagement.navTreeSelection();

    }*/

    private void menuSelection( EntityMenu entityMenu)
    { 
         
        //panelManagement.setMenuObject(entity,intMenu); 
        //System.out.println("DialogMain.menuSelection "+entityMenu.getEntityCaption());
        panelManagement.navTreeSelection(entityMenu);
        

    }
    
    private void setTreePanelVisible()
    {
    	if(panelManagement.isTreePanelVisible())
    	{  		
    	     mitemVisTreeMenu.setSelected(false);
    	     panelManagement.setTreePanelVisible();
    		
    	}
    	else
    	{
    		mitemVisTreeMenu.setSelected(true);
    		panelManagement.setTreePanelVisible();
    	}
        
    	
    }
    
    public void updateUIs()
    {
        SwingUtilities.updateComponentTreeUI(this);
//-        SwingUtilities.updateComponentTreeUI(aboutDialog);
        
/*        SwingUtilities.updateComponentTreeUI(accountPanel);
        SwingUtilities.updateComponentTreeUI(categoryPanel);
        SwingUtilities.updateComponentTreeUI(aboutDialog);
        SwingUtilities.updateComponentTreeUI(optionsDialog);
        SwingUtilities.updateComponentTreeUI(waitDialog);
        SwingUtilities.updateComponentTreeUI(accountChooser);
        SwingUtilities.updateComponentTreeUI(fileChooser);
        SwingUtilities.updateComponentTreeUI(qifFileChooser);
        SwingUtilities.updateComponentTreeUI(mt940FileChooser);
        SwingUtilities.updateComponentTreeUI(accountBalancesReportPanel);
        SwingUtilities.updateComponentTreeUI(incomeExpenseReportPanel);
        navigationTree.setCellRenderer(new NavigationTreeCellRenderer());*/
    }
 
 
 
   class ToolBarMain extends JToolBar
   {

      private JButton btnCompanyLogin = new JButton();
      private JButton btnUserLogin = new JButton();
      //private JButton btnManagement = new JButton();
      //private JButton btnConfig = new JButton();

      //private JToggleButton btnModule1 = new JToggleButton();
      //private JButton btnModule1 = new JButton();   // is in initialization
       
     // private JButton btnModule3 = new JButton();
     // private JButton btnModule4 = new JButton();
     // private JButton btnModule5 = new JButton();
      
      private JButton btnBackupRestore = new JButton();
      
      
      
      
      private JButton btnHelp = new JButton();
      private JButton btnKeys = new JButton();
      private JButton btnInfo = new JButton();
      private JButton btnAbout = new JButton();
     // private IconSeparator icoSeparator;
      //private JLabel lblIcoSeparator1= new JLabel();
      //private JLabel lblIcoSeparator2= new JLabel();
      //private JLabel lblIcoSeparator3= new JLabel();
        
        public ToolBarMain()
        {
            try
           {     initialize();   }
           catch (Exception e)
           {   e.printStackTrace();    }
        }

        private void initialize() throws Exception
        {
          setFloatable(false);
          setRollover(true);
          BevelBorder borderRaised = new BevelBorder(javax.swing.border.BevelBorder.RAISED);
          this.setBorder(borderRaised);
         // this.setOpaque(false);

          //this.setBackground(Color.blue);
          //this.setOrientation(this.VERTICAL);
         
         btnCheckForUpdates = new JButton();
          btnCheckForUpdates.setText(TXTCHECKFORNEWVERSION);
          //btnCompanyLogin.setText("<html><b><font color='#993333'>A</font></b>λλαγή εταιρίας</html>");
          //btnCompanyLogin.setText("<html><b>E</b>ίσοδος σε εταιρία</html>");
          btnCompanyLogin.setText("αλλαγή εταιρίας");//είσοδος σε εταιρία");
          btnCompanyLogin.setOpaque(false);
          btnCompanyLogin.setToolTipText("login");//
          btnCompanyLogin.setIcon(ICO_USER16);
          btnCompanyLogin.setMnemonic(KeyEvent.VK_E);
          btnCompanyLogin.setFocusable(false);
          btnCompanyLogin.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        { displayDialogLoginCompany();
	        }
	      });

          
          
          //btnCompanyLogin.setText("<html><b><font color='#993333'>A</font></b>λλαγή εταιρίας</html>");
          //btnCompanyLogin.setText("<html><b>E</b>ίσοδος σε εταιρία</html>");
          btnUserLogin.setText("αλλαγή χρήστη");//είσοδος σε εταιρία");
          btnUserLogin.setOpaque(false);
          btnUserLogin.setToolTipText("login");//
          btnUserLogin.setIcon(ICO_USER16);
          //btnUserLogin.setMnemonic(KeyEvent.VK_E);
          btnUserLogin.setFocusable(false);
          btnUserLogin.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        { displayDialogLoginUser();
	        }
	      });          
    /*      btnModule1 = new JButton();
          btnModule1.setText("παροχή υπηρεσίας");
          btnModule1.setOpaque(false);
          btnModule1.setToolTipText("παροχή υπηρεσίας");
          btnModule1.setIcon(ICO_PAPER);//ICO_REPORTDOCUMENT);
         // btnModule1.setMnemonic(KeyEvent.VK_1);//.VK_1);
          btnModule1.setFocusable(false);
          btnModule1.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {  
                    setModuleInPanelManagement(btnModule1.getText(), (ImageIcon) btnModule1.getIcon());
                    //displayDialogParameters();// displayMessageDialog("ρυθμίσεις","ρυθμίσεις που αφορουν το πρόγραμμα, \n όπως εμφάνιση κτλ...");    
	        }//displayCalendarDialog(btnConfig);
	      });          
          btnModule2 = new JButton();
          btnModule2.setText("έσοδα εξοδα");
          btnModule2.setOpaque(false);
          btnModule2.setToolTipText("έσοδα εξοδα");
          btnModule2.setIcon(ICO_ESODAEXODA);
         // btnModule2.setMnemonic(KeyEvent.VK_2);
          btnModule2.setFocusable(false);
          btnModule2.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {  
                    setModuleInPanelManagement(btnModule2.getText(), (ImageIcon) btnModule2.getIcon());
                    //displayDialogParameters();// displayMessageDialog("ρυθμίσεις","ρυθμίσεις που αφορουν το πρόγραμμα, \n όπως εμφάνιση κτλ...");    
	        }//displayCalendarDialog(btnConfig);
	      });  
          
            btnModule3.setText("πάγια");
          btnModule3.setOpaque(false);
          btnModule3.setToolTipText("πάγια");
          btnModule3.setIcon(ICO_FIXEDASSETS);
        // btnModule3.setMnemonic(KeyEvent.VK_3);
          btnModule3.setFocusable(false);
          btnModule3.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {  
                    setModuleInPanelManagement(btnModule3.getText(), (ImageIcon) btnModule3.getIcon());
                    //displayDialogParameters();// displayMessageDialog("ρυθμίσεις","ρυθμίσεις που αφορουν το πρόγραμμα, \n όπως εμφάνιση κτλ...");    
	        }//displayCalendarDialog(btnConfig);
	      });  
          
          btnModule4.setText("έντυπα");
          btnModule4.setOpaque(false);
          btnModule4.setToolTipText("έντυπα");
          btnModule4.setIcon(ICO_REPORTDOCUMENT);//ICO_REPORTDOCUMENT);//ICO_BUYER16);      ICO_MENUCAT_REPORT
        //  btnModule4.setMnemonic(KeyEvent.VK_4);
          btnModule4.setFocusable(false);
          btnModule4.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {  
                    setModuleInPanelManagement(btnModule4.getText(), (ImageIcon) btnModule4.getIcon());
                    //displayDialogParameters();// displayMessageDialog("ρυθμίσεις","ρυθμίσεις που αφορουν το πρόγραμμα, \n όπως εμφάνιση κτλ...");    
	        }//displayCalendarDialog(btnConfig);
	      });            
  
         btnModule5.setText("σύστημα");
          btnModule5.setOpaque(false);
          btnModule5.setToolTipText("σύστημα");
          btnModule5.setIcon(ICO_SYSTEM);
        //  btnModule5.setMnemonic(KeyEvent.VK_5);
          btnModule5.setFocusable(false);
          btnModule5.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {  
                    setModuleInPanelManagement(btnModule5.getText(), (ImageIcon) btnModule5.getIcon());
                    //displayDialogParameters();// displayMessageDialog("ρυθμίσεις","ρυθμίσεις που αφορουν το πρόγραμμα, \n όπως εμφάνιση κτλ...");    
	        }//displayCalendarDialog(btnConfig);
	      });           
         */ 
  /*        btnManagement.setText("<html>διαχείρηση <b>ctrl+M</b></html>");
          btnManagement.setIcon(ICO_MANAGEMENT22);
          btnManagement.setFocusable(false);
          btnManagement.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {          displayDialogManagement() ; // displayMessageDialog("ρυθμίσεις","ρυθμίσεις που αφορουν το πρόγραμμα, \n όπως εμφάνιση κτλ...");    
	        }//displayCalendarDialog(btnConfig);
	      });*/


          //btnConfig.setText("<html>ρυθμίσεις <b>ctrl+P</b></html>");
          //btnConfig.setText("<html><b>P</b>υθμίσεις</html>");
         /* btnConfig.setText("ρυθμίσεις");
          btnConfig.setOpaque(false);
          btnConfig.setToolTipText("ρυθμίσεις");
          btnConfig.setIcon(ICO_CONFIG16);
          btnConfig.setMnemonic(KeyEvent.VK_R);
          btnConfig.setFocusable(false);
          btnConfig.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {   displayDialogConfiguration(); // displayMessageDialog("ρυθμίσεις","ρυθμίσεις που αφορουν το πρόγραμμα, \n όπως εμφάνιση κτλ...");    
	        }//displayCalendarDialog(btnConfig);
	      });*/

         /* btnParameters.setText("<html><b>Π</b>αραμετροποίηση</html>");
          btnParameters.setOpaque(false);
          btnParameters.setToolTipText("παραμετροποίηση");
          btnParameters.setIcon(ICO_PARAMETERS16);
          btnParameters.setMnemonic(KeyEvent.VK_P);
          btnParameters.setFocusable(false);
          btnParameters.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {    displayDialogParameters();// displayMessageDialog("ρυθμίσεις","ρυθμίσεις που αφορουν το πρόγραμμα, \n όπως εμφάνιση κτλ...");    
	        }//displayCalendarDialog(btnConfig);
	      });*/
          
          
          //btnBackupRestore.setText("<html><b>Β</b>ackUp/Restore</html>");
          btnBackupRestore.setText("backUp/restore");
          btnBackupRestore.setOpaque(false);
          btnBackupRestore.setToolTipText("αποθήκευση / επαναφορά");
          btnBackupRestore.setIcon(ICO_BACKUP);
          btnBackupRestore.setMnemonic(KeyEvent.VK_B);
          btnBackupRestore.setFocusable(false);
          btnBackupRestore.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {     displayDialogBackupRestore();    }
	      });
          
          
          btnCheckForUpdates.setText("έλεγχος για νέα έκδοση");
         
          btnCheckForUpdates.setOpaque(false);
          btnCheckForUpdates.setToolTipText("έλεγχος για νέα έκδοση");
          btnCheckForUpdates.setIcon(ICO_ABOUT16);
          //btnCheckForUpdates.setMnemonic(KeyEvent.VK_B);
          btnCheckForUpdates.setFocusable(false);
          btnCheckForUpdates.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {     checkForNewVersion();    }
	      });
          
          //btnHelp.setText("<html>πλήκτρα <b>ctrl+K</b></html>");
          //btnHelp.setText("<html><b>Β</b>οήθεια</html>");
/*          btnHelp.setText("βοήθεια");
          btnHelp.setOpaque(false);
          btnHelp.setToolTipText("βοήθεια");
          btnHelp.setIcon(ICO_HELP16);
          btnHelp.setMnemonic(KeyEvent.VK_B);          
          btnHelp.setFocusable(false);
          btnHelp.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {  
	            displayDialogHelp();
	        }
	      });
*/
          //btnKeys.setText("<html>π<b>Λ</b>ήκτρα</html>");
  /*        btnKeys.setText("πλήκτρα");
          btnKeys.setOpaque(false);
          btnKeys.setToolTipText("πλήκτρα συντομεύσεων");
          btnKeys.setIcon(ICO_KEYS16);
          btnKeys.setMnemonic(KeyEvent.VK_L);          
          btnKeys.setFocusable(false);
          btnKeys.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {  
	            displayDialogKeys();
	        }
	      });
*/
          
          //btnInfo.setText("<html>πληροφορίες <b>ctrl+I</b></html>");
          //btnInfo.setText("<html>πλ<b>Η</b>ροφορίες</html>");
  /*        btnInfo.setText("πληροφορίες");
          btnInfo.setOpaque(false);
          btnInfo.setToolTipText("πληροφορίες");
          btnInfo.setMnemonic(KeyEvent.VK_H);          
          btnInfo.setIcon(ICO_ABOUT16);
          btnInfo.setFocusable(false);
          //btnInfo.setVerticalTextPosition(AbstractButton.BOTTOM);
          //btnInfo.setHorizontalTextPosition(AbstractButton.CENTER);
          btnInfo.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {    displayDialogSystemInfo();    }
	      });*/
	      
	      
       /*   btnAbout.setText("<html><b>Σ</b>χετικά</html>");
          btnAbout.setOpaque(false);
          btnAbout.setToolTipText("σχετικά με την εφαρμογή");
          btnAbout.setMnemonic(KeyEvent.VK_S);          
          btnAbout.setIcon(ICO_ABOUT16);
          btnAbout.setFocusable(false);
          //btnInfo.setVerticalTextPosition(AbstractButton.BOTTOM);
          //btnInfo.setHorizontalTextPosition(AbstractButton.CENTER);
          btnAbout.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {    displayDialogAbout();    }
	      });*/


	      
  /*        btnExit = new JButton();
          //btnExit.setText("<html>έξοδος <b>ctrl+E</b></html>");
          //btnExit.setText("<html>έξοδος <b>esc</b></html>");
          btnExit.setText("έξοδος");
          btnExit.setOpaque(false);
          btnExit.setToolTipText("έξοδος από την εφαρμογή");
          btnExit.setIcon(ICO_EXIT16);
          btnExit.setFocusable(false);
          btnExit.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {   
                 exitApplication();
	        }
	      });
*/	      

	      
/*        Action actionClose = new ActionClose();
        btnExit.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnExit.getActionMap().put("close", actionClose);
*/
          //addSeparator();
        //IconResize icoResize = new IconResize();
        //icoSeparator = new IconSeparator();
        //lblIcoSeparator1.setIcon(icoSeparator);
        //lblIcoSeparator1.setText("kj");
        
        //lblIcoSeparator1.setOpaque(false);
       // lblIcoSeparator1.setHorizontalAlignment(SwingConstants.LEFT);
        //lblIcoSeparator1.setVerticalAlignment(SwingConstants.TOP);
        
        //lblIcoSeparator2.setIcon(icoSeparator);  
        //lblIcoSeparator3.setIcon(icoSeparator);
                  
          //add(lblIcoSeparator1);


         // add(lblIcoSeparator3);
         
          addMainButtons();
  
        }
        
        
        private void addMainButtons()
        {
            
          addSeparator();
         
         // add(btnModule2);
        //  add(btnModule1);
        //  add(btnModule4);
//          add(btnModule3);
        //  add(btnModule5);
        //  addSeparator();
          addSeparator();
          add(btnCompanyLogin);
          add(btnUserLogin);
          addSeparator();
          //add(btnConfig);
         // addSeparator();
          //add(btnParameters);
          add(btnBackupRestore);
          add(btnCheckForUpdates);
          //add(lblIcoSeparator2);
         // add(btnKeys);
         //addSeparator();
   //       add(btnHelp);
          //addSeparator();
          //add(btnInfo);
          //add(btnAbout);
  //        add(btnExit);
          addSeparator();            
        }
        
        @Override
        protected void paintComponent(Graphics g)
        {
             /*Graphics2D g2 = (Graphics2D) g;   //                     15
             GradientPaint paint = new GradientPaint(0, 0, this.getBackground().brighter(), 0, 37, this.getBackground().darker(),true);
             g2.setPaint(paint);
             g2.fill(getBounds());*/
             super.paintComponent(g);
        }
   
   }

   /* class MenuBar extends JMenuBar
    {
      private JMenu menuFunctions;
      //private JMenu menuReports;
      //private JMenu menuConfig;
      private JMenu menuHelp;
      
      private JMenuItem mitemFuncLogin = new JMenuItem();
      private JMenuItem mitemWindowNew = new JMenuItem();
    private JMenuItem mitemConfigOptions = new JMenuItem();
    //private JCheckBoxMenuItem mitemVisTreeMenu = new  JCheckBoxMenuItem();
     //private JCheckBoxMenuItem mitemVisToolbarMain = new  JCheckBoxMenuItem();
    private JMenuItem mitemBackupRestore = new JMenuItem();
     
      private JMenuItem mitemFuncExit = new JMenuItem();
   /*   private JMenuItem mitemRepReport1 = new JMenuItem();
      private JMenuItem mitemRepReport2 = new JMenuItem();
      private JMenuItem mitemRepReport3 = new JMenuItem();*/
      
      
    /*  private JMenuItem mitemHelpKeys  = new JMenuItem();
      private JMenuItem mitemHelpSystemInfo  = new JMenuItem();
      private JMenuItem mitemHelpAbout = new JMenuItem();
        
        public MenuBar()
        {
            try
            {      initialize();    }
            catch (Exception e)
            {   e.printStackTrace();  }
        }
        
        private void initialize() throws Exception
        {
        	
        	mitemVisTreeMenu = new  JCheckBoxMenuItem();
           mitemVisToolbarMain = new  JCheckBoxMenuItem();
           
            // functions menu -------------------------------------------------------
            menuFunctions = new JMenu("Λειτουργίες");
            //menuFunctions.setMnemonic(KeyEvent.VK_ALT);
            
            //menuitemFuncLogin.setEnabled(false);
            mitemFuncLogin.setIcon(ICO_USER16);
            mitemFuncLogin.setText("αλλαγή εταιρίας");
            mitemFuncLogin.setAccelerator(KeyStroke.getKeyStroke('L', KeyEvent.CTRL_MASK));
            mitemFuncLogin.getAccessibleContext().setAccessibleDescription("Login function");
            mitemFuncLogin.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
            {    displayDialogLogin();       }
            });
            
   /*         mitemFuncManage.setText("Διαχείρηση");
            mitemFuncManage.setAccelerator(KeyStroke.getKeyStroke('M', KeyEvent.CTRL_MASK));
            mitemFuncManage.getAccessibleContext().setAccessibleDescription("Login function");
            mitemFuncManage.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
            {    displayDialogManagement();       }
            });*/
            //menuConfig = new JMenu("Παραμετροποίηση");
            //menuConfig.setMnemonic(KeyEvent.VK_Π);
            
            //mitemConfigOptions.setEnabled(false);
            
            
            
            /*mitemWindowNew.setIcon(ICO_WINDOWNEW16);
            mitemWindowNew.setText("νέο παράθυρο");
            mitemWindowNew.setAccelerator(KeyStroke.getKeyStroke('N', KeyEvent.CTRL_MASK));
            //mitemWindowNew.getAccessibleContext().setAccessibleDescription("Login function");
            mitemWindowNew.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
            {    displayWindowNew();       }
            });
            
                        
            mitemConfigOptions.setIcon(ICO_CONFIG16);
            mitemConfigOptions.setText("ρυθμίσεις");
            mitemConfigOptions.setAccelerator(KeyStroke.getKeyStroke('R', KeyEvent.CTRL_MASK));
            mitemConfigOptions.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
            {    displayDialogConfiguration();  }
            });
            
            
            //mitemVisTreeMenu.setIcon(ICO_CONFIG16);
            mitemVisTreeMenu.setText("εμφάνιση δενδρικού μενού");
            mitemVisTreeMenu.setSelected(true);
            //mitemVisTreeMenu.setAccelerator(KeyStroke.getKeyStroke('R', KeyEvent.CTRL_MASK));
            mitemVisTreeMenu.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
            {    

               	setTreePanelVisible();

            }
            });




            //mitemVisTreeMenu.setIcon(ICO_CONFIG16);
            mitemVisToolbarMain.setText("εμφάνιση κύριας μπάρας");
            mitemVisToolbarMain.setSelected(true);
            //mitemVisTreeMenu.setAccelerator(KeyStroke.getKeyStroke('R', KeyEvent.CTRL_MASK));
            mitemVisToolbarMain.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
            {    
                if(toolBarMain.isVisible())
                {
                	toolBarMain.setVisible(false);
                }
                else
                {
                	toolBarMain.setVisible(true);
                }
               	

            }
            });








          mitemBackupRestore.setText("back up/restore");
          //btnBackupRestore.setOpaque(false);
          mitemBackupRestore.setToolTipText("αποθήκευση / επαναφορά");
          mitemBackupRestore.setIcon(ICO_BACKUP);
          mitemBackupRestore.setMnemonic(KeyEvent.VK_B);
          //mitemBackupRestore.setFocusable(false);
          mitemBackupRestore.addActionListener(new ActionListener()
          {
	        public void actionPerformed(ActionEvent e) 
	        {     displayDialogBackupRestore();    
	        }//displayCalendarDialog(btnConfig);
	      });

            
           // add items to configure menu
            //menuConfig.add();
            
            //redoItem.setEnabled(false);
            mitemFuncExit.setIcon(ICO_EXIT16);
            mitemFuncExit.setText("έξοδος");
            mitemFuncExit.setAccelerator(KeyStroke.getKeyStroke('E', KeyEvent.CTRL_MASK));
            mitemFuncExit.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
            {    exitApplication();      }
            });
           
           // add items to functions menu
            menuFunctions.add(mitemFuncLogin);
            menuFunctions.add(mitemWindowNew);
            menuFunctions.addSeparator();
            menuFunctions.add(mitemConfigOptions);
            menuFunctions.add(mitemVisTreeMenu);
            menuFunctions.add(mitemVisToolbarMain);
            menuFunctions.addSeparator();
            menuFunctions.add(mitemBackupRestore);
            menuFunctions.addSeparator();
            menuFunctions.add(mitemFuncExit);
           //--------------------

             

             

         
            // help menu -------------------------------------------------------
            menuHelp = new JMenu("Βοήθεια");
            //menuHelp.setMnemonic(KeyEvent.VK_Π);
            
            //mitemHelpKeys.setIcon(ICO_SYSTINFO16);
            mitemHelpKeys.setText("πλήκτρα συντόμευσης");
            mitemHelpKeys.setAccelerator(KeyStroke.getKeyStroke('K', KeyEvent.CTRL_MASK));
            
            mitemHelpKeys.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
            {    displayDialogKeys();       }
            });

            
            //mitemConfigOptions.setEnabled(false);
            mitemHelpSystemInfo.setIcon(ICO_SYSTINFO16);
            mitemHelpSystemInfo.setText("πληροφορίες συστήματος");
            mitemHelpSystemInfo.setAccelerator(KeyStroke.getKeyStroke('I', KeyEvent.CTRL_MASK));
            mitemHelpSystemInfo.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
            {    displayDialogSystemInfo();       }
            });

            //mitemConfigOptions.setEnabled(false);
         /*   mitemHelpAbout.setIcon(ICO_ABOUT16);
            mitemHelpAbout.setText("Σχετικά");
            mitemHelpAbout.setAccelerator(KeyStroke.getKeyStroke('A', KeyEvent.CTRL_MASK));
            mitemHelpAbout.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
            {    displayDialogAbout();     }
            });*/
          
           // add items to help menu
           /* menuHelp.add(mitemHelpKeys);
            menuHelp.add(mitemHelpSystemInfo);
 
             entityData = new EntityData();
             //entityData.loadGenericData();            
            add(menuFunctions);

         //JMenu menu = new JMenu("men");
        // add(menu);            
            
            //add(menuConfig);
            add(menuHelp);
         }
    	
    }*/
    
  /* class  ActionClose extends AbstractAction                 
   {       
        public ActionClose()
        {      }
      	
    	public void actionPerformed(ActionEvent e)
      	{      btnExit.doClick();       }    	
    }  */              
    
    
    /** Exit the Application
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0); 
    }*/

    
    private void saveTablePrefsInitial()
    {
    	
    	utilsFileSystem.writeFile(composeAndGetPrefsXML(),VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+FILE_TABLEPREFERENCES);
    }
      private void saveDataEditPrefsInitial()
    {
    	
    	utilsFileSystem.writeFile(composeAndGetPrefsDataEntryXML(),VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+FILE_DATAEDIT_PREFERENCES);
    }
    
    private void saveReportPrefsInitial()
    {
    	
    	utilsFileSystem.writeFile(composeAndGetPrefsReport(),VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+FILE_REPORTPREFERENCES);
    }    

    
  private String composeAndGetPrefsXML()
  {
  	String ret = "";

        Writer writer = new java.io.StringWriter();
        XmlWriter xmlwriter = new XmlWriter(writer);
    
    xmlwriter.writeEntity("TablePrefs"); 
    xmlwriter.closeOpenTag();
    xmlwriter.changeLine(); 
    //xmlwriter.writeEntity("property");   
    //for(int l =0;l<tableReportFields.getModel().getRowCount();l++)
    //{  

     xmlwriter.endEntity(); // props 
     xmlwriter.close();
  	 
  	 ret = writer.toString();
  	
  	return ret;         	
    }    
    
    
  private String composeAndGetPrefsDataEntryXML()
  {
  	String ret = "";

        Writer writer = new java.io.StringWriter();
        XmlWriter xmlwriter = new XmlWriter(writer);
    
    xmlwriter.writeEntity("DataEditUniqueFieldsPrefs"); 
    xmlwriter.closeOpenTag();
    xmlwriter.changeLine(); 
    //xmlwriter.writeEntity("property");   
    //for(int l =0;l<tableReportFields.getModel().getRowCount();l++)
    //{  

     xmlwriter.endEntity(); // props 
     xmlwriter.close();
  	 
  	 ret = writer.toString();
  	
  	return ret;         	
    }

  private String composeAndGetPrefsReport()
  {
  	String ret = "";

        Writer writer = new java.io.StringWriter();
        XmlWriter xmlwriter = new XmlWriter(writer);
    
    xmlwriter.writeEntity("ReportPrefs"); 
    xmlwriter.closeOpenTag();
    xmlwriter.changeLine(); 
    //xmlwriter.writeEntity("property");   
    //for(int l =0;l<tableReportFields.getModel().getRowCount();l++)
    //{  

     xmlwriter.endEntity(); // props 
     xmlwriter.close();
  	 
  	 ret = writer.toString();
  	
  	return ret;         	
    }
  
  

  
  
  
  
  /*
  *
  *  called by 
  */
    private String getFileContentsFromDBZipFile(String zipPathNFile, String sqlFileName)
    {
    	// http://www.java2s.com/Code/Java/File-Input-Output/ReadingtheContentsofaZIPFile.htm
     String strRet = "";//txtareaRestoreComments.setText("");
     
     //System.out.println("DialogMain.getFileContentsFromDBZipFile    zipPathNFile:"+zipPathNFile+"   sqlFileName:"+sqlFileName);
     
    try {
      ZipFile zf = new ZipFile(zipPathNFile);
      Enumeration entries = zf.entries();

      BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
      while (entries.hasMoreElements())
      {
        ZipEntry ze = (ZipEntry) entries.nextElement();
        //System.out.println("Read " + ze.getName() + "?");
        //String inputLine = input.readLine();
        if (ze.getName().equalsIgnoreCase(sqlFileName))
        {                //inputLine.equalsIgnoreCase("yes")) {
          long size = ze.getSize();
          if (size > 0) {
            //System.out.println("Length is " + size);
            BufferedReader br = new BufferedReader( new InputStreamReader(zf.getInputStream(ze),"utf-8"));
            String line;
            
            while ((line = br.readLine()) != null)
            {
              strRet = strRet + line;
              //System.out.println(line);
            }
            br.close();
          }
        }
      }
    }
    catch (FileNotFoundException fnfe)
    {
        //System.out.println("DialogMain.getFileContentsFromDBZipFile   zipPathNFile:"+zipPathNFile+"  sqlFileName:"+sqlFileName+"  MSG:"+fnfe.getMessage());
    }
    catch (java.nio.file.NoSuchFileException nf)
    {
        System.out.println("DialogMain.getFileContentsFromDBZipFile   zipPathNFile:"+zipPathNFile+"     error:"+nf.getMessage());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    	
    	return strRet;
   }
    
    
    

  
       /*
       *
       *  called by updateDb
       */
  private boolean hasFileThenRestore(String strZippedFile, String strVersion)throws IOException
  {
      boolean ret = false;
      
      
      String sqlText = getFileContentsFromDBZipFile(strZippedFile,"db"+strVersion+".sql");
                    
                      
     if(sqlText!=null && !sqlText.equalsIgnoreCase(""))                  
     {
         //System.out.println("DialogMain.hasFileThenRestore  strVersion:"+strVersion);
         
          //System.out.println("DialogMain.hasFileThenRestore  TRUE  upd   zip:"+strZippedFile+"      version:"+strVersion+"   sql:"+sqlText); 
          dlgBackup.restore(strZippedFile,"db"+strVersion+".sql",strVersion,"παρακαλω περιμένετε, αναβάθμιση βάσης",true);
         

        
          ret = true;
     }
     else
     {
         //System.out.println("DialogMain.hasFileThenRestore  upd   zip:"+strZippedFile+"   version:"+strVersion+"    empty sql:"+sqlText); 
         ret=false;
     }
     
      return ret;
  }
  

  
  /*
  *
  * called by isRestoreCompleted
  */
    public boolean updateDb(double oldVersion, double newVersion)
    {
        boolean ret = false;
         wwu = new WindowWait("αναβάθμιση  βάσης",WINDOW_LOCATION_CENTER,ICO_RELOAD16, ICO_RELOADB16);
          
         //wwu.showWindow();
         
        // wwu.animate();         
           		          // thread for show window wait
	        Thread thread1 = new Thread(new Runnable() {
	          public void run()
	          {
	            wwu.animate();
           	       wwu.showWindow();
	               wwu.setComment("εκκίνηση");
	               //thread = null;
	          }
	          });

              thread1.start(); 	 

                          
                  
                 for(double k = oldVersion+0.0001 ; k <=newVersion; k = k+0.0001) // not equal
                   {
                      
                       double val = k ;
                               //val.setPrecision(4);
                               //double no=12.786;
                       DecimalFormat dec = new DecimalFormat("#0.0000");
                       DecimalFormatSymbols decimalSymbol = new DecimalFormatSymbols(Locale.getDefault());
                       decimalSymbol.setDecimalSeparator('.');
                       dec.setDecimalFormatSymbols(decimalSymbol);
                       
                        String strVal =  dec.format(val);
                        
                         wwu.setComment(strVal); 
                       
                        String strZippedFile = UPDATE_DB_PREFIX+strVal+UPDATE_DB_ZIPPEDPOSTFIX; 
                      String dirAndZippedFile = VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+DIR_DATAUPDATES+VariablesGlobal.globalSystemDirectorySymbol+strZippedFile;
                  try
                  {
                      //System.out.println("DialogMain.updateDb   dirAndZippedFile:"+dirAndZippedFile);
                      ret = hasFileThenRestore(dirAndZippedFile, strVal);
                       
                    
                       if(k==newVersion)
                       {
          	         /* wwu.showWindow();
         
                          wwu.animate();
                          wwu.setComment("OK");  */
                         
                       }
                      }
                      catch(IOException ioe)
                      {
                          System.out.println("DialogMain.updateDb  IOException  "+dirAndZippedFile+"     "+ioe.getMessage());
                      }
                      //k = k+0.01; 
                   }     
                 
                 

                 wwu.setComment("OK"); 
                 wwu.close();
             
                 
           // thread1.join();     
      /*            
       wwu.close();
         thread = null;
*/

        return ret;
    }	
  
    private void updateVersionDbTag()
    {  
           Database dbu = new Database();
           dbu.updateQueryNotTransaction("UPDATE dbsystem SET dbleadversion = "+VariablesGlobal.appLeadVersion+", dbsubversion = "+VariablesGlobal.appSubVersion+" WHERE dbsystemid = 1","DialogMain.updateVersionDbTag",false,false);             
    }
    
  
    private boolean restoreNewVersionDb(double oldVersion)
    {
        boolean retIsCompleted = false;
        
           /*DatabaseMeta dbMeta2 = new DatabaseMeta();
  	   String running2 = dbMeta2.isDBrunning();
         
           if(!running2.equals(""))
           {
        	 System.exit(0);
           }*/

           boolean isToUpdate = oldVersion<Double.parseDouble(VariablesGlobal.appSubVersion);
           System.out.println("DialogMain.restoreNewVersionDb   is? "+oldVersion+" - "+Double.parseDouble(VariablesGlobal.appSubVersion)+"    isToUpdate:"+isToUpdate);
           if(isToUpdate)
           {        
                     retIsCompleted = this.inStartWouldYouLikeToUpdateInNewVersion(oldVersion);  
           }
           else
           {

           }    
            
            return retIsCompleted;
    }
  
    private boolean inStartWouldYouLikeToUpdateInNewVersion(double oldVersion)
    {
         boolean ret = false;
             final int YES = 0;
    	    final int NO = 1;
           int answer = utilsGui.showConfirmYesOrNo(this,"Βρέθηκε νέα έκδοσή της βάσης δεδομένων. Θα ξεκινήσει αναβάθμιση. Θέλετε να συνεχίσετε;");
            if(answer == YES)
            {
              System.out.println("DialogMain.inStartWouldYouLikeToUpdateInNewVersion to call   updateDb from:"+oldVersion+" to "+Double.parseDouble(VariablesGlobal.appSubVersion+"")); 
                // also after creating the database in main
             boolean isUpdated =  this.updateDb(oldVersion,Double.parseDouble(VariablesGlobal.appSubVersion+""));
            System.out.println("DialogMain.inStartWouldYouLikeToUpdateInNewVersion    isUpdated:"+isUpdated+" because "+oldVersion+"-"+Double.parseDouble(VariablesGlobal.appSubVersion+""));
           if(isUpdated)
           {
              this.updateVersionDbTag();
               ret = true;
              
           }
           else
           {
               System.out.println("DialogMain.inStartWouldYouLikeToUpdateInNewVersion  ERROR  isUpdated:"+isUpdated+"  perhaps error in filename or directory.");
           }

            }
            else if(answer == NO)
            {
                 System.exit(0);
            }        
            
            return ret;
    }
    
    
    private void readFromFileDbSettings()
    {
        
     try
     {   
        Properties props = new Properties(); //properties to get from file 
        //String dbSettingsDir = props.getProperty("dbSettingsDir");  
        FileInputStream in = new FileInputStream(VariablesGlobal.globalDirConfiguration+systemDirectorySymbol+FILE_DB_CONFIG);
        props.load(in);
        String url = props.getProperty("jdbc.url");
        //String dbSettingsDir = props.getProperty("dbSettingsDir"); 



        VariablesGlobal.globalFilePassForDb = utilsString.decrypt(props.getProperty("jdbc.password"));

        System.out.println("DialogMain.readFromFileDbSettings  read file  dbSettingsDir:"+url);
      }
      catch (IOException ex)
      {
          System.err.println("DialogMain.readFromFileDbSettings :Cannot find text file. "+VariablesGlobal.globalDirConfiguration+systemDirectorySymbol+FILE_DB_CONFIG+"  "+ex);
         utilsGui.showMessageError("Το αρχείο ρυθμίσεων της βάσης δεν βρέθηκε. Η εφαρμογή θα τερματιστεί.");
          System.exit(0);
      }         
        
    }
    
    // also exists in DialogDbConnect.createDB
    private boolean createDbSystemTableWhenDatabseExistsButNotTheDbsystemTable()
    {
        boolean ret=false;
        Database db = new Database();
        String dbName = db.getDbName();
           try
           {
               
           
                            Connection conn = db.getConnection();
                             Statement stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
       
         stmnt.execute("USE "+dbName+";");
                              // exists also in DialogMain.createDbSystemTableWhenDatabseExists    
                              stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS `dbsystem` ("+
                    "`dbsystemid` int(11) NOT NULL AUTO_INCREMENT,"+
                    "`dbleadversion` varchar(10) DEFAULT '0',"+
                    "`dbsubversion` varchar(10) DEFAULT '0',"+
                    "PRIMARY KEY (`dbsystemid`)"+
                     ") ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8");

                   stmnt.executeUpdate("REPLACE INTO dbsystem (dbsystemid, dbleadversion, dbsubversion) VALUES ( 1 ,  '1',  '"+STR_VERSIONSUB_START+"' )");                                  
           ret=true;
           
           }                      
           catch ( SQLException sqlex)
          {
          	if (sqlex.getErrorCode() == 1049)
          	{
                    //txtDbInfo.append("Η βάση δεδομένων '"+dbName+"' δεν υπάρχει.");	
                    System.out.println("DialogMain.createDbSystemTableWhenDatabseExists  error:"+sqlex.getMessage());
                        utilsGui.showMessageError(this,"Η βάση δεδομένων '"+dbName+"' δεν υπάρχει.");          		
          	}
          	else if (sqlex.getErrorCode() == 1045)
          	{
          	      utilsGui.showMessageError(this,"Δεν εχετε πρόσβαση για αυτό το χρήστη.\nΑλλάξτε username ή password.");
          	}
          	else
          	{
               //System.out.println("error:DialogSetupDb.dbRuns:"+sqlex.getErrorCode()+" "+sqlex.getMessage());
                     utilsGui.showMessageError(this,"DialogMain.createDbSystemTableWhenDatabseExists err code "+sqlex.getErrorCode()+" \n"+sqlex.getMessage());
               }
                ret = false;
          }  
           finally
           {
               closeDB();
           }
           
           return ret;
    }
    
    private boolean isDbRunning()
    {
        boolean ret = false;
        try
        {
             FileInputStream in = new FileInputStream(VariablesGlobal.globalDirConfiguration+systemDirectorySymbol+FILE_DB_CONFIG);
        }
        catch(IOException ioe)
        {
             System.out.println("error DialogMain.isDbRunning  IOException    "+ioe.getMessage());
              return false;
        }
         
        
        try
        {

            Database dbr = new Database();
            this.readFromFileDbSettings();
           // dbr.getConnection();
           // dbr.getDbName();
            dbr.retrieveDBDataFromQuery("SELECT dbleadversion, dbsubversion FROM dbsystem WHERE dbsystemid = 1","DialogMain.isDbRunning");
            
            if(dbr.getRS()==null)
            {
                 createDbSystemTableWhenDatabseExistsButNotTheDbsystemTable();
                return true;
            }
            else
            {
               
                return true;
            }
            //ResultSet rs = db.getRS();
        }

        catch(Exception e)
        {
                
               // if(!createDbSystemTableWhenDatabseExistsButNotTheDbsystemTable())
                  System.out.println("error DialogMain.isDbRunning  SQLException      "+e.getMessage());
                  ret = false;
                  e.printStackTrace();

        }        
        finally
        {
            db.releaseConnectionRs();
        }
        
        
        
            return ret;
    }
    
    private double checkVersionOfDb()
    {
        double dbVersion = 1.2585;
            Database db = new Database();
            db.retrieveDBDataFromQuery("SELECT dbleadversion, dbsubversion FROM dbsystem WHERE dbsystemid = 1","DialogMain.isRestoreCompleted");
            ResultSet rs = db.getRS();

            String strDbLeadVer = "1";
            String strDbSubVer = "1.2585";
             try
             {
            if(rs==null || rs.first()==false)
            {           
              createDbSystemTableWhenDatabseExistsButNotTheDbsystemTable();
            }
                rs.first();
               strDbLeadVer = rs.getString("dbleadversion");
               strDbSubVer= rs.getString("dbsubversion");
             }
             catch (SQLException sqle)
             {
                      System.out.println("DialogMain.isRestoreCompleted       SQLException:"+sqle.getErrorCode()+"     "+sqle.getMessage());  
             }
             finally
             {
                db.releaseConnectionRs();
             }
            //String strOldVer = rs.getString(1);//uFileSystem.readFileAndGet(FILE_DBVERSION_INFO);
              try
              {
                 dbVersion = Double.parseDouble(strDbSubVer);
              }
              catch(java.lang.NumberFormatException nfe)
              { 
              }                    
        
        return dbVersion;  
    }
    
    public static void main(String args[])
    {
        //if(args!=null)
        //{  
            //VariablesGlobal.appRunParam2= "0";//args[2];
            //VariablesGlobal.appShowLogFrame = "1";//args[1];
          if(VariablesGlobal.globalShowFrameRedirected)// && VariablesGlobal.appShowLogFrame.equalsIgnoreCase("1"))
          {
            new FrameRedirected(true, false, null, 1080,980, JFrame.DISPOSE_ON_CLOSE);
          }
        //}
        System.setProperty("file.encoding", "UTF-8");
        //(boolean catchErrors, boolean logFile, String fileName, int width,
         //int height, int closeOperation)
    	System.out.println("system file encoding: "+System.getProperty("file.encoding"));
    	System.out.println(VariablesGlobal.appName+" "+VariablesGlobal.appLeadVersion+"."+VariablesGlobal.appSubVersion+" Date: " + new Date());
        System.out.println("DialogMain.main classpath "+System.getProperty("java.class.path"));
        System.out.println("DialogMain.main user.dir "+System.getProperty("user.dir"));
        //if(args!=null)
        //{
            //try
           // {
            VariablesGlobal.appProduct = PRODUCT_FARMERSVAT_NOT;//PRODUCT_OLA;//args[0];
           /* }
            catch(ArrayIndexOutOfBoundsException e)
            {
                e.printStackTrace();
            }*/
            /*      PRODUCT_OLA = "0";
       PRODUCT_TIMOLOGIA = "1";
        PRODUCT_APLOGRAFIKA = "2";
            
            */
        //String productCaption = "";
       /* if(VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_TIMOLOGIA)  )
        {
            VariablesGlobal.appProductCaption  = PRODUCT_TIMOLOGIA_CAPTION;
        }
        else if(VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_APLOGRAFIKA))
        {
            VariablesGlobal.appProductCaption  = PRODUCT_APLOGRAFIKA_CAPTION;
        }
        else if( VariablesGlobal.appProduct.equalsIgnoreCase(PRODUCT_OLA))
        {
            VariablesGlobal.appProductCaption  = PRODUCT_OLA_CAPTION;
        }      
            System.out.println("DialogMain.main args "+args[0]+"  "+args[1]);
        //}  */      
        
        ///   https://stackoverflow.com/questions/6481627/java-security-illegal-key-size-or-default-parameters
        utilsString = new UtilsString();
        utilsString.fixEncryptDecryptKeyLength();
        
        // set right click on texts
        Toolkit.getDefaultToolkit().getSystemEventQueue().push(new EventQueueTxtRightClick()); 
        
       
      
      
      String   curDir="";
      if(utilsOS.isOSWindows())
      {
      	curDir = System.getProperty("user.dir"); // get current dir
      }
      else
      {
      	//String classPath = System.getProperty("java.class.path");
      	//curDir = classPath.substring(0,classPath.lastIndexOf(systemDirectorySymbol));	
          curDir = System.getProperty("user.dir"); // get current dir
      }
        
        systemDirectorySymbol=System.getProperty("file.separator");
        VariablesGlobal.globalDirConfiguration = curDir;
 
        utilsGui.createUiConfigToFile();
        utilsGui.setLookAndFeel();      
        
         //  new ImageIcon(Constants.class.getResource("/images/logo.png")
  	WindowSplash splashScr = new WindowSplash(VariablesGlobal.appName+"  (εκδ "+VariablesGlobal.appLeadVersion+"."+VariablesGlobal.appSubVersion+")",ICO_BACK);///APP_LOGO );   //  APP_LOGO
        

        DialogMain dialogMain = new DialogMain();
       
       
      
      
    //     dialogMain.readFromFileDbSettings();
  	boolean running = dialogMain.isDbRunning();
  	      	 	
     	 //System.out.println("DialogMain.main  running:"+running+"     ");
        if(running) // 
        {
            
       
            dialogMain.readFromFileDbSettings();
            
             double dbversion = dialogMain.checkVersionOfDb();

            //System.out.println("DialogMain.main     restoreNewVersionDb  dbversion:"+dbversion);
            dialogMain.restoreNewVersionDb(dbversion); 
              
            dialogMain.updateVersionDbTag();
 
        } 
        else if(!running)
        {
     
        	/*splashScr.dispose();
        	utilsGui.showMessageError(this,"Η επαφή με τη βάση δεδομένων δεν λειτουργεί. Ελέγξτε εαν \n"+
        	"1. έχετε εγκαταστήσει jdbc driver\n"+
        	"2. η βάση δεδομένων τρέχει\n"+
        	"3. έχετε τρέξει το εργαλείο SetupDB\n"+
        	"4. έχετε χρεισιμοποιήσει το σωστό password\n\n"+
            running	);
            System.exit(0);*/
            boolean isFromDbSetup = false;
           DialogDbConnect dialogSetupDb = new DialogDbConnect(dialogMain);
           
           dialogSetupDb.setVisible(true);
           isFromDbSetup = dialogSetupDb.closeNStart();

          /* DatabaseMeta dbMeta2 = new DatabaseMeta();
  	   String running2 = dbMeta2.dbFileExists();
         
           if(!running2.equals(""))
           {
        	 System.exit(0);
           }*/
           
           
           
           dialogMain.readFromFileDbSettings();
           // also in isRestoreCompleted
           // used here again in order to create the db
           Double oldVersion = 1.2585;
           //--  setup when first time run
           boolean isUpdated = dialogMain.updateDb(oldVersion,Double.parseDouble(VariablesGlobal.appSubVersion+""));
           System.out.println("DialogMain.main    isUpdated:"+isUpdated+" because "+oldVersion+"-"+Double.parseDouble(VariablesGlobal.appSubVersion+""));
           if(isUpdated)
           {
                dialogMain.updateVersionDbTag();}
           else
           {
               System.out.println("DialogMain.main   ERROR   isUpdated:"+isUpdated+"  Perhaps error in filename or directory.");
           }            
            
                        
        }
        else
        {
            System.out.println("DialogMain.main  ELSE running:"+running);
        }
       

        dialogMain.setSize(1300,765);
        dialogMain.locateOnCenterOfTheScreen();        
        DialogUserLogin dialogUserLogin = new DialogUserLogin(dialogMain);

        DialogCompanyLogin dialogCompanyLogin  = DialogCompanyLogin.getInstance(dialogMain); 
        dialogCompanyLogin.setIsExit(true);//false when called when allready in
              //dialogCompanyLogin.locateOnCenterOfTheScreen();

        splashScr.dispose();
        dialogUserLogin.setLoginAgain(false);
        dialogUserLogin.setVisible(true);
    if(dialogUserLogin.closeAndContinue())
    {
         String userId = dialogUserLogin.getUserId();
        
         dialogCompanyLogin.setVisible(true);
         dialogMain.setVisible(true);

       /* isCapsLockBeforeEnteringApp = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
        dialogMain.addWindowListener(new WindowAdapter()
        {
            public void windowActivated(WindowEvent e)
			{    // set caps lock after login
			    Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
			    //InputContext.getInstance().selectInputMethod(new Locale("el_GR")); 
			    //InputContext.getInstance().selectInputMethod(new Locale("el")); 
			    //System.out.println("DialogMain caps lock = " + Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK));
			}

		});*/
        
        
         boolean isOk = dialogCompanyLogin.getIsOkClicked();
              
         if (isOk == true)
         {
             String strDate = dialogCompanyLogin.getDate();
             String yearId= dialogCompanyLogin.getYearId();
           dialogMain.setCompanyYearUserDate(true,dialogCompanyLogin.getCompanyId(), dialogCompanyLogin.getCompanyName(),
                   yearId,dialogCompanyLogin.getDbYearDateFrom(yearId),dialogCompanyLogin.getDbYearDateTo(yearId),dialogCompanyLogin.getYearDescr(), userId,dialogCompanyLogin.getDate(),
                   dialogCompanyLogin.getCompanyMessage());	
         } 
    }
         // create documents if they dont exist
         if(utilsFileSystem.fileExistsOrNot(VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+FILE_TABLEPREFERENCES))
         {
         	
         }
         else
         {
             dialogMain.saveTablePrefsInitial();	
         }
  
         if(utilsFileSystem.fileExistsOrNot(VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+FILE_DATAEDIT_PREFERENCES))
         {
         	
         }
         else
         {
             dialogMain.saveDataEditPrefsInitial();	
         }         
  
         if(utilsFileSystem.fileExistsOrNot(VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+FILE_REPORTPREFERENCES))
         {
         	
         }
         else
         {    
         	 
             dialogMain.saveReportPrefsInitial();	
         }
        
         
     }
     
  
}