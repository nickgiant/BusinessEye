/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.setup;
import com.tool.guicomps.*;
import com.tool.gui.*;
import com.tool.jdbc.Database;
import com.tool.utils.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

/**
 *
 * @author small
 */
public class DialogDbConnect  extends JDialog implements Constants
{
    private JLabel lblTop;
    private JPanel panelButtons;
    private JPanel panelChecks;
    private JPanel panelLabels;
    private JPanel panelTexts;
    private JPanel panelLabelsAndTexts;
    //private JButton btnShowMetadata;
    //private JButton btnSetup;
    private JButton btnCheck;
    private JButton btnSave;
    private JButton btnCreateDB;
    //private JButton btnSetupConfig;
    //private JButton btnOpenLibPath;
    //private JButton btnShowBackup;
    private JButton btnShowBackupRestore;
    private JButton btnShowSystemInfo; 
    private JButton btnStart;
     //private JLabel lblDbName;
    //private JLabel lblDbPath;
    private JLabel lblDbDriver;
    private JLabel lblDbUrl;
    private JLabel lblDbUser;
    private JLabel lblDbPass;
    private JLabel lblDBInfo;
    //private JTextField txtDbName;
    private JTextArea txtDbInfo;    
    //private JTextField txtDbPath;
    private JTextField txtDbDriver;
    private JTextField txtDbUrl;
    private JTextField txtDbUser;
    private JTextField txtDbPass;
   // private JTextField txtDbSalt;
    //private JTextField txtBackupDir;
    //private JTextField txtDbEngineDir;    
    private JTextField txtDbSettingsDir;   
	//private JLabel lblDbExists;
	//private JCheckBox chkDbExists;

    private int dbEngine=0;
    private String dbName = "";  // farmersvat2
    private String dbPath;
    private String dbDriver;
    private String dbHost;
    private String dbUrl;
    private String dbUser;
    private String dbPass;
    //private String dbEngineDir ="";
    private String dbSettingsDir ="";
    //private String backUpsDir ="";
    private Properties dbProperties;
    private Properties fileProperties;
    //private JTextArea queryTextArea;
    private final String DB_TXT_FILE = "db.txt";
    //private JTextArea txtareaLog;
    
    private static UtilsOS utilsOS;
    private static UtilsGui utilsGui;
    private static UtilsString utilsString;
    
    //private WindowWait ww;
    
    private Thread thread;
    
    JTableDec table;
     private Connection connection;
    
    
    private String[] columnNames;
    private Vector rows;
    
    
 //   private JDBCTableModel tableModel;
    
    private TableModelWritable tableModel;
    
    //private TableModelReadOnly tableModel;
    private JFrame frameQueryBrowser;
    private Connection con;
    
    private ListSelectionModel lsm;
    
    //private DatabaseTableMeta databaseTableMeta;
    JRadioButton radioMariaDB;
       JRadioButton radioMySql;
      // JRadioButton radioPostgreSql ;
       JRadioButton radioMSSqlServer ;
       //JRadioButton radioHsql ;
       JRadioButton radioH2embedded;
       //JRadioButton radioH2server;
       //JRadioButton radioSqlite;

      //private String derbyString="Derby";
      //private JRadioButton radioDerbyEmbedded = new JRadioButton(derbyString);
    
    private String database ;
    private String databaseVersion;
    private static String systemDirectorySymbol;
    private JFrame frame;
    //private JTextArea textAreaMetaData;
    
    private String   curDir;
    
         static ArrayList classes;
   
        // private DialogQueryBrowser dialogQueryBrowser;
     //private PasswordUtils passUtils;    
         
         
    public DialogDbConnect()//(Frame parent, boolean modal)
    {
        initComponents(null);
        this.setTitle("Setup DB");
        checkMariaDB();
        loadConfigFromFile();
    }
    
    /** This method is called from within the constructor to initialize the form.*/

    public DialogDbConnect(JFrame frame)//(Frame parent, boolean modal)
    {
        super(frame,VariablesGlobal.appName+" Setup DB", true);//LANGUAGE.getString("AboutDialog.title"));
        initComponents(frame);
        checkMariaDB();
        loadConfigFromFile();
         //txtDbInfo.append("");
    }
         
    private void initComponents(JFrame frameIn) 
    {
    	frame=frameIn;
        
     utilsOS = new UtilsOS();  
      // JFrame.setDefaultLookAndFeelDecorated(true);
      // JDialog.setDefaultLookAndFeelDecorated(true);  // setted in start
       systemDirectorySymbol=System.getProperty("file.separator");
       
       utilsGui = new UtilsGui(); 
       utilsString = new UtilsString();
       //utilsOS = new UtilsOS();

      systemDirectorySymbol=System.getProperty("file.separator");
//      String   curDir="";
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
        
        
        
        VariablesGlobal.globalDirConfiguration = curDir;  
        
        utilsGui.setLookAndFeel();       

 	  //String dirInstalled="C:\\Program Files\\MySQL\\MySQL Server 5.1\\bin\\";

       
        // txtareaLog = new JTextArea(3, 10); // rows columns it belongs to DialogQueryBrowser
       
//        dbName = "farmersvat";
        dbUser="root";
        dbPass="";
        
                
        
        panelButtons = new JPanel();
       /* btnShowMetadata = new JButton();
        btnSetup = new JButton();
        btnCheck = new JButton();        
        btnSave = new JButton();
        btnOpenLibPath = new JButton(); */
        panelChecks = new JPanel();
        panelLabels = new JPanel(new GridLayout(0,1));
        panelTexts= new JPanel(new GridLayout(0,1));
        panelLabelsAndTexts = new JPanel();
        panelLabelsAndTexts.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 2));
        panelLabelsAndTexts.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));// int top, int left, int bottom, int right)

        
        //panelTexts.setLayout(new SpringLayout());
        //lblDbExists = new JLabel("DB runs?", JLabel.CENTER);
        //chkDbExists = new JCheckBox();
//        lblDbName = new JLabel("database name:", JLabel.CENTER);
        //lblDbPath  = new JLabel("path:", JLabel.CENTER);
        lblDbDriver = new JLabel("driver:", JLabel.CENTER);
        lblDbUrl = new JLabel("URL:", JLabel.CENTER);
        lblDbUser = new JLabel("χρήστης:", JLabel.CENTER);
        lblDbPass = new JLabel("κωδικός:", JLabel.CENTER);
        //JLabel lblBackupDir = new JLabel("back up dir:", JLabel.CENTER);
        JLabel lblDbSettingsDir = new JLabel("settings dir:", JLabel.CENTER);
        //JLabel lblDbSalt = new JLabel("salt:", JLabel.CENTER);
        
        //JLabel lblDbEngineDir = new JLabel("db engine dir:", JLabel.CENTER);
        lblDBInfo = new JLabel("info:", JLabel.CENTER);
        int intTxtLength = 53;
 //       txtDbName = new JTextField(intTxtLength);
        //txtDbPath = new JTextField(55);
        txtDbDriver = new JTextField(intTxtLength);
        txtDbUrl = new JTextField(intTxtLength);
        txtDbUser = new JTextField(intTxtLength);
        txtDbPass = new JPasswordField(intTxtLength);
        //txtDbSalt = new JTextField(intTxtLength);
        //txtBackupDir= new JTextField(55);
        txtDbSettingsDir = new JTextField(intTxtLength);
        //txtDbEngineDir= new JTextField(55);        
        txtDbInfo = new JTextArea(6,10);
        txtDbInfo.setBackground(panelChecks.getBackground());
        txtDbInfo.setEditable(false);
        Font f = this.getFont();
        txtDbInfo.setFont(f);
        JScrollPane scrollForInfo = new JScrollPane();
        scrollForInfo.setViewportView(txtDbInfo);
        
        setResizable(true);

   
       
       //txtBackupDir.setText(curDir+systemDirectorySymbol+"backup"+systemDirectorySymbol);
       txtDbSettingsDir.setText(curDir+systemDirectorySymbol);
       txtDbSettingsDir.setEditable(false);
       
        //panelChecks.setLayout(new AbsoluteLayout()); 
      //  lblTop.setText("Ρυθμίσεις για τη βάση δεδομένων");
       String mariadbString="MariaDB";
       radioMariaDB = new JRadioButton(mariadbString);
       //radioMySql.setMnemonic(KeyEvent.VK_M);
       radioMariaDB.setActionCommand(mariadbString);
      
      String mysqlString="MySQL";
       radioMySql = new JRadioButton(mysqlString);
       //radioMySql.setMnemonic(KeyEvent.VK_M);
       radioMySql.setActionCommand(mysqlString);


       String mssqlServerString="MSSqlServer";
       radioMSSqlServer = new JRadioButton(mssqlServerString);
       //radioMySql.setMnemonic(KeyEvent.VK_M);
       radioMSSqlServer.setActionCommand(mssqlServerString);
       

       String h2embeddedString="H2(embedded)";
       radioH2embedded = new JRadioButton(h2embeddedString);
       //radioDerbyEmbedded.setMnemonic(KeyEvent.VK_D);
       radioH2embedded.setActionCommand(h2embeddedString);

       
       
       ButtonGroup group = new ButtonGroup();
       group.add(radioMariaDB);
       group.add(radioMySql);
      
       group.add(radioMSSqlServer);
      
       group.add(radioH2embedded);

       
       radioMariaDB.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {  checkMariaDB();      }
	    });       

       radioMySql.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {  checkMySql();      }
	    });

	    
       radioMSSqlServer.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {  checkMSSqlServer();      }
	    });

        radioH2embedded.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        { checkH2Embedded();      }
	    });
	    
    

       panelChecks.add(radioMariaDB);
       panelChecks.add(radioMySql);
  
       panelChecks.add(radioMSSqlServer);

       panelChecks.add(radioH2embedded);

       //panelChecks.add(radioHsql);

 //      panelLabelsAndTexts.add(lblDbName);
 //      panelLabelsAndTexts.add(txtDbName);
       //panelLabelsAndTexts.add(lblDbPath);
       //panelLabelsAndTexts.add(txtDbPath);
 //      panelLabelsAndTexts.add(lblDbDriver);
 //      panelLabelsAndTexts.add(txtDbDriver);
       panelLabelsAndTexts.add(lblDbUrl);
       panelLabelsAndTexts.add(txtDbUrl);
       panelLabelsAndTexts.add(lblDbUser);
       panelLabelsAndTexts.add(txtDbUser);
       panelLabelsAndTexts.add(lblDbPass);
       panelLabelsAndTexts.add(txtDbPass);
       //panelLabelsAndTexts.add(lblDbSalt);
       //panelLabelsAndTexts.add(txtDbSalt);       
       //panelLabelsAndTexts.add(lblBackupDir);
       //panelLabelsAndTexts.add(txtBackupDir);
       //panelLabelsAndTexts.add(lblDbEngineDir);        
       //panelLabelsAndTexts.add(txtDbEngineDir);
       panelLabelsAndTexts.add(lblDbSettingsDir);        
       panelLabelsAndTexts.add(txtDbSettingsDir);
       //panelLabelsAndTexts.add(lblDbExists);
       //panelLabelsAndTexts.add(chkDbExists);
       panelLabelsAndTexts.add(lblDBInfo);
       panelLabelsAndTexts.add(scrollForInfo);

       
        
        ToolBarCommands toolbar = new ToolBarCommands();
        toolbar.setFocusable(false);
       toolbar.setFloatable(false);
        
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new GridLayoutVariable(GridLayoutVariable.FIXED_NUM_COLUMNS, 1));
        panelTop.add(toolbar);
        panelTop.add(panelChecks);
        getContentPane().add(panelTop, BorderLayout.NORTH);
        getContentPane().add(panelLabelsAndTexts, BorderLayout.CENTER);
        //getContentPane().add(panelButtons, BorderLayout.SOUTH);

        //getRootPane().setDefaultButton(btnShowMetadata);
        
        
        
    addWindowListener(new WindowAdapter()
    {
       public void windowClosing(WindowEvent e)
       {    closeNStart();   }
    });

      this.pack();

              locateOnCenterOfTheScreen();

    }
    
    public void locateOnCenterOfTheScreen()//on the center
    {
    	Dimension paneSize   = this.getSize();
    	Dimension screenSize = this.getToolkit().getScreenSize();
    	//System.out.println(getSize()+" - "+getToolkit().getScreenSize());
    	this.setLocation(
            (screenSize.width  - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
    }

    public void locateOnCenterOfTheScreen(JFrame frame)//on the center
    {
    	Dimension paneSize   = frame.getSize();
    	Dimension screenSize = this.getToolkit().getScreenSize();
    	//System.out.println(getSize()+" - "+getToolkit().getScreenSize());
    	frame.setLocation(
            (screenSize.width  - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
    }

    private void showMessageErrorSelectDatabase()
    {
    	
    	utilsGui.showMessageError(this,"Επιλέξτε πρώτα βάση δεδομένων.");
    	
    }
    
    
    public boolean closeNStart()
    {
        
            boolean isStart = false;
	            if(dbEngine!=0)
	            {
	                 //dbCheck(dbEngine);
                        if( dbCheck(dbEngine) )
                        {
                            if( !dbName.equalsIgnoreCase(""))
                            {
                         if(saveConfig())
                         {
                             closeWindow();  
                             isStart=true;
                         }
                         else
                         {
                             //utilsGui.showMessageError(this," saveConfig is false");
                         }
                            }
                            else
                            {
                                 txtDbInfo.append("\nΔεν υπάρχει βάση. Παρακαλώ επιλέξτε 'δημιουργία βάσης'.");
                            }
                        }
                        else
                        {
                            
                            //utilsGui.showMessageError(this," false check db engine.   dbName:"+dbName);
                        }
	            }
	            else
	            {
                        utilsGui.showMessageError(this,"Παρακαλώ προσπαθήστε ξανά.");
	            	
	            }  
                    
           return isStart;         
        
    }
    
    private void closeWindow()
    {
    	System.out.println("DialogSetupDb.closeWindow frame:"+frame);
        if (frame==null)
        {
           System.exit(0);	
        }
        else
        {
          this.dispose();
        }
        
    }

	private void loadConfigFromFile()
	{
	  try
	  {
     
        if (dbEngine==DBENGINE_MARIADB)
        {
        	radioMariaDB.setSelected(true);
        	 database="MariaDB";
        }
        else if (dbEngine==DBENGINE_MYSQL)
        {
        	radioMySql.setSelected(true);
        	 database="MySql";
        }        
        else if(dbEngine==DBENGINE_H2EMBEDDED)
        {
        	radioH2embedded.setSelected(true);
        	 database="H2 Embedded";
        }
        else if(dbEngine==DBENGINE_H2SERVER)
        {
        	
        }   
        else if(dbEngine==DBENGINE_POSTGRESQL)
        {
        	
        }  
        else if(dbEngine==DBENGINE_DERBYEMBEDDED)
        {
        
        }  
        else if(dbEngine==DBENGINE_MSSQLSERVER)
        {
        	radioMSSqlServer.setSelected(true);
        	 database="MS SQL Server";
        }  
        else if(dbEngine==DBENGINE_HSQL)
        {
        
        }   
        else if(dbEngine== DBENGINE_SQLITE)
        {
           
        }
        else
        {
        	database="unknown";
        	System.out.println("DialogSetupDb.loadConfigFromFile not supported dbEngine "+dbEngine);
        }
              
              
              
              
              
              
     	Properties props = new Properties(); //properties to get from file
//     String curDir ="";

      
        FileInputStream in = new FileInputStream(VariablesGlobal.globalDirConfiguration+systemDirectorySymbol+FILE_DB_CONFIG);
        props.load(in);
        dbName=props.getProperty("jdbc.dbname");
        dbUrl = props.getProperty("jdbc.url");
        dbDriver = props.getProperty("jdbc.drivers");
        dbUser = props.getProperty("jdbc.username");
        // dbSalt = props.getProperty("jdbc.salt");

        dbPass = utilsString.decrypt(props.getProperty("jdbc.password"));
           
          
         
        
       
        //dbPath=props.getProperty("derby.system.home");
        //dbEngineDir=props.getProperty("backup.dbenginedir");
        //backUpsDir=props.getProperty("backup.filesdir");
        dbEngine=Integer.valueOf(props.getProperty("dbengine"));
         
 //         txtDbName.setText(dbName);
          //txtDbPath.setText(dbPath);
          txtDbDriver.setText(dbDriver);                 
          txtDbUrl.setText(dbUrl);
          txtDbUser.setText(dbUser);
          txtDbPass.setText(dbPass);
	 //     txtDbEngineDir.setText(dbEngineDir);
	      //txtBackupDir.setText(backUpsDir);      
        System.out.println("DialogDbConnect.loadConfigFromFile  txtDbUrl:"+txtDbUrl.getText());
  /*
  
         group.add(radioMySql);
       group.add(radioPostgreSql);
       //group.add(radioMSSqlServer);
       group.add(radioDerbyEmbedded);
       //group.add(radioHsql);
       group.add(radioH2embedded);
       group.add(radioH2server);
  
  */
        

                	        	        	     
        
      }
      catch (IOException ex)
      {
          System.err.println("DialogConfiguration.IOException:Cannot find text file:"+FILE_CONFIG);
          //System.err.println(ex);
      }
      
   }
        
        
    public void checkMySql()
    {
    	//dbName =getDbName();
    	
    	  dbEngine=DBENGINE_MYSQL;
	      dbPath="";
          dbDriver="com.mysql.jdbc.Driver";                 //  thi is for 0000-00-00 dates
          dbHost = "127.0.0.1:3306";
          dbUrl="jdbc:mysql://"+dbHost+"/"+dbName;  //+"?zeroDateTimeBehavior=convertToNull";
          //dbUser="";
          //dbPass="";
         /* if(utilsOS.isOSWindows())
          {
             dbEngineDir="C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\";	
          }
          else
          {
          	  dbEngineDir="";
          }*/
          
         
          
        if (dbDriver != null)
        System.setProperty("jdbc.drivers", dbDriver);

          //txtDbPath.setEditable(false);
          //txtDbPath.setText(dbPath);
          txtDbDriver.setEditable(true);
          txtDbDriver.setText(dbDriver);                 
          txtDbUrl.setEditable(true);
          txtDbUrl.setText(dbUrl);
          txtDbUser.setEditable(true);
          txtDbUser.setText(dbUser);
          txtDbPass.setEditable(true);
          //txtDbPass.setText(dbPass);
         // txtDbSalt.setEditable(false);
          //txtDbSalt.setText(dbSalt);
          //txtDbEngineDir.setText(dbEngineDir);
          
          dbCheck(dbEngine);  
    }

    
        public void checkMariaDB()
    {
        radioMariaDB.setSelected(true);
    	//dbName =getDbName();
    	
    	  dbEngine=DBENGINE_MARIADB;
	      dbPath="";
          dbDriver="org.mariadb.jdbc.Driver";                 //  thi is for 0000-00-00 dates
          dbHost = "127.0.0.1:3306";
          dbUrl="jdbc:mariadb://"+dbHost+"/"+dbName;  //+"?zeroDateTimeBehavior=convertToNull";
          //dbUser="";
          //dbPass="";
          /*if(utilsOS.isOSWindows())
          {
             dbEngineDir="C:\\Program Files\\MariaDB 10.2\\bin\\";	
          }
          else
          {
          	  dbEngineDir="";
          }*/
          
         
          
        if (dbDriver != null)
        System.setProperty("jdbc.drivers", dbDriver);

          //txtDbPath.setEditable(false);
          //txtDbPath.setText(dbPath);
          txtDbDriver.setEditable(true);
          txtDbDriver.setText(dbDriver);                 
          txtDbUrl.setEditable(true);
          txtDbUrl.setText(dbUrl);
          txtDbUser.setEditable(true);
          txtDbUser.setText(dbUser);
          txtDbPass.setEditable(true);
          //txtDbPass.setText(dbPass);
          //txtDbSalt.setEditable(false);
         // txtDbSalt.setText(dbSalt);
          //txtDbEngineDir.setText(dbEngineDir);
          
          dbCheck(dbEngine);  
    }
    
   

        public void checkMSSqlServer()
    {
    	//dbName =getDbName();
    	  dbEngine=DBENGINE_MSSQLSERVER;
	      dbPath="";
          dbDriver="com.microsoft.jdbc.sqlserver.SQLServerDriver"; 
          dbHost= "127.0.0.1:1433";
          dbUrl="jdbc:microsoft:sqlserver://"+dbHost+"/"+dbName;
          dbUser="sa";
          dbPass="";


        if (dbDriver != null)
        System.setProperty("jdbc.drivers", dbDriver);

         // txtDbPath.setEditable(false);
         // txtDbPath.setText(dbPath);
          txtDbDriver.setEditable(true);
          txtDbDriver.setText(dbDriver);                 
          txtDbUrl.setEditable(true);
          txtDbUrl.setText(dbUrl);
          txtDbUser.setEditable(true);
          txtDbUser.setText(dbUser);
          txtDbPass.setEditable(true);
          //txtDbPass.setText(dbPass);
          //txtDbEngineDir.setText("");
          
          dbCheck(dbEngine);  
    }

 



    public void checkH2Embedded()
    {
    	/*
    	 *  ;FILE_LOCK=SERIALIZED   http://www.h2database.com/html/advanced.html?highlight=serialized&search=serialize#file_locking_serialized
    	 *   slower
    	 *
    	 *
    	 *   ;AUTO_SERVER=TRUE
    	 *
    	 *
    	 */ 
    	 //dbName =getDbName();
    	 	
         String autoserver = ";AUTO_SERVER=TRUE;DB_CLOSE_DELAY=10"; // if more than 1 connection set to server, firewall issues
          if(utilsOS.isOSWindows())
          {
                dbPath=VariablesGlobal.globalDirConfiguration+"\\db_h2";// System.getProperty("user.dir")+"\\h2_db";
                dbUrl="jdbc:h2:file:"+dbPath+"\\"+dbName+autoserver;
                System.setProperty("derby.system.home", dbPath); // set directory where db is saved
          }
          else
          {
          	//String curDir="";
          	   //String classPath = System.getProperty("java.class.path");
      	       //curDir = classPath.substring(0,classPath.lastIndexOf(systemDirectorySymbol));	
               //String userHomeDir = curDir;//System.getProperty("java.class.path", ".");
               String systemDir = VariablesGlobal.globalDirConfiguration + systemDirectorySymbol+"db_h2";
               dbPath = systemDir;
               dbUrl="jdbc:h2:file:"+dbPath+systemDirectorySymbol+dbName+autoserver;
               System.setProperty("derby.system.home", "derby.system.home="+ systemDir); // set directory where db is saved
          } 
             
          dbEngine=DBENGINE_H2EMBEDDED;
	      dbHost="";
	     // dbEngineDir="";
          dbDriver="org.h2.Driver";                 
          
          dbUser="sa";
          dbPass="";

          
          //txtDbPath.setEditable(true);
          //txtDbPath.setText(dbPath);
          txtDbDriver.setEditable(true);
          txtDbDriver.setText(dbDriver);                 
          txtDbUrl.setEditable(true);
          txtDbUrl.setText(dbUrl);
          txtDbUser.setEditable(true);
          txtDbUser.setText(dbUser);
          txtDbPass.setEditable(true);
          //txtDbPass.setText(dbPass);
          //txtDbEngineDir.setText("");
          
          dbCheck(dbEngine);
    }

        private boolean dbCheck(int dbEngine)
    {   
        //chkDbExists.setEnabled(true);
        txtDbInfo.setText("");
        boolean ret = false;
    
          //dbPath=txtDbPath.getText();
          dbDriver=txtDbDriver.getText();                 
          dbUrl=txtDbUrl.getText();
          dbUser=txtDbUser.getText();
          dbPass=txtDbPass.getText();
         // dbSalt = txtDbSalt.getText();
               
        try  // load driver
        {
            Class.forName(dbDriver);
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }

        if (dbEngine==DBENGINE_DERBYEMBEDDED)
        {
          
          String dbLocation= "";               	
          /*if(utilsOS.isOSWindows())
          {
      	        dbLocation = dbPath;
          }
          else
          {
          	   dbLocation = System.getProperty("derby.system.home") + "/"+dbName;
          }*/                
               /* File dbFileDir = new File(dbLocation); //   check if exists
                if (dbFileDir.exists())
                {     dbRuns = true;     }*/
        	//System.out.println("derby "+dbDriver+" "+dbUrl);
        }
        else if (dbEngine==DBENGINE_HSQL)
        {
        	//System.out.println("hsql "+dbDriver+" "+dbUrl);
        
         }
        else if (dbEngine==DBENGINE_MYSQL)
        {
        	//System.out.println("mysql "+dbDriver+" "+dbUrl);
           
           
         }
         else if (dbEngine==DBENGINE_POSTGRESQL)
         {
        	//System.out.println("postgresql "+dbDriver+" "+dbUrl);
        	
         }
         else if (dbEngine==DBENGINE_MSSQLSERVER)
         {
        	//System.out.println("postgresql "+dbDriver+" "+dbUrl);
        	
         }
         else if (dbEngine==DBENGINE_H2EMBEDDED)
         {
        	//System.out.println("postgresql "+dbDriver+" "+dbUrl);
        	
         }
         else if (dbEngine==DBENGINE_H2SERVER)
         {
        	//System.out.println("postgresql "+dbDriver+" "+dbUrl);
        	
         }                  
         else
         {  System.out.println("DialogSetupDb.dbCheck Not a known dbEngine "+dbEngine);   }
       
           try
           {
      //  Connection conn = DbConnection.getConnectionFromFile();//get from connection factory
        //if (dbDriver != null)
        //   System.setProperty("jdbc.drivers", dbDriver); // set driver
        Connection conn = getConnectionToDB();

        DatabaseMetaData metaData = conn.getMetaData();
        //String driverVersion = metaData.getDriverName()+" "+metaData.getDriverVersion();
        
         database = metaData.getDatabaseProductName();
         databaseVersion = metaData.getDatabaseProductVersion();
        String driver = metaData.getDriverName();
        String driverVersion = metaData.getDriverVersion();
           //System.out.println("drivers loaded"+System.getProperty("jdbc.drivers"));
         
    	       
            txtDbInfo.append("database: "+database+"   version:"+databaseVersion);	       
            txtDbInfo.append("\ndriver: "+driver+"  driver version:"+driverVersion);
            



           /*if(dbName.equalsIgnoreCase(""))
           {
               utilsGui.showMessageError(this,"Η βάση δεδομένων '"+dbName+"' δεν υπάρχει."); 
                ret = false;
           }
           else
           {*/
           ret = true;
           //}
            conn.close();
            

            
            
          }
  /*        catch(SocketException socketE)
          {
          	JOptionPane.showMessageDialog(null, socketE.getMessage());
          }*/          
          catch ( SQLException sqlex)
          {
          	if (sqlex.getErrorCode() == 1049)
          	{
                    txtDbInfo.append("Η βάση δεδομένων '"+dbName+"' δεν υπάρχει.");	
                    //System.out.println("DialogSetupDb.dbCheck  error:"+sqlex.getMessage());
                        //utilsGui.showMessageError(this,"Η βάση δεδομένων '"+dbName+"' δεν υπάρχει.");          		
          	}
          	else if (sqlex.getErrorCode() == 1045)
          	{
          	      utilsGui.showMessageError(this,"Δεν εχετε πρόσβαση για αυτό το χρήστη.\nΑλλάξτε username ή password.");
          	}
          	else
          	{
                   System.out.println("error:DialogSetupDb.dbRuns:"+sqlex.getErrorCode()+" "+sqlex.getMessage());
                     utilsGui.showMessageError(this,"DialogSetupDb.dbCheck err code "+sqlex.getErrorCode()+" \n"+sqlex.getMessage());
               }
                ret = false;
          }
        
       //chkDbExists.setSelected(dbRuns);
       //chkDbExists.setEnabled(false);

       System.out.println("DialogSetupDb.dbRuns for "+dbEngine+":"+ret);
        return ret;
    }
        
        
            // same as DialogBackUp
    private Connection getConnectionToDB()throws SQLException
    {
    	
        Properties p = new Properties(); // properties to be used for connection
   	    String url="";



        String drivers = txtDbDriver.getText();
        if (drivers != null)
           System.setProperty("jdbc.drivers", drivers);

        url = txtDbUrl.getText();

       // String dbDir = txtDbPath.getText() ;
        //if(dbDir !=null )  //if there is in text file
        //{           System.setProperty("derby.system.home", dbDir);  } // load it

        p.put("user", txtDbUser.getText());// username);
        //dbSalt = txtDbSalt.getText();

            
            
           //String pass = passUtils.generateSecurePassword(txtDbPass.getText(), dbSalt);
           p.put("password", dbPass);//password);

        p.put("useUnicode", "true");
        p.put("characterEncoding", "utf8");//iso-8859-7
      
       return DriverManager.getConnection(url, p);
	
    }
    
    
    private void displayDialogBackupRestore()
    {
    	DialogBackUp dialogBackUp = new DialogBackUp();
    	//(JFrame parent, int dbEngineIn,String dbEngineNameIn,String urlIn,String userIn)

        dialogBackUp.setVisible(true);        
        
    }
    
 
    
    
    private void createDB()
    {

      int YES = 0;   	 int NO = 1;
          String strReturn = JOptionPane.showInputDialog(this,"Εισάγετε το ονομα της βάσης:", DATABASENAME_TOCREATE_DEFAULT);
          if(strReturn!= null && !strReturn.equalsIgnoreCase(""))
          {
                    int ask = utilsGui.showConfirmYesOrNo(this,"Σίγουρα θέλετε να δημιουργήσετε τη βάση '"+strReturn+"' ;");
          		//int ask = utilsGui.showConfirmYesOrNo(this,"Η βάση δεδομένων '"+dbName+"' δεν υπάρχει. \n Θέλετε να τη δημιουργήσετε;");
                        if(ask == YES)
                        {
                            try
                            {
                            Connection conn = getConnectionToCreateDB();
                             Statement stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
       
             // System.out.println("Database.updateQuery "+query);      
        
        	              int  ret = stmnt.executeUpdate("CREATE DATABASE IF NOT EXISTS `"+strReturn+"` /*!40100 DEFAULT CHARACTER SET utf8 */;");// IF NOT EXISTS

                              if(ret==0)
                              {
                                  dbName = strReturn;
                               //   txtDbName.setText(dbName);
                                  txtDbUrl.setText(txtDbUrl.getText()+dbName);
                                  ///  NOT reates error checkMariaDB();
                                  txtDbInfo.append("\nΣφάλμα. Η βάση δεδομένων '"+strReturn+"' υπάρχει ήδη και δεν δημιουργήθηκε\n για να μή διαγραφούν τα δεδομένα. Προσπαθήστε με άλλο όνομα.");                                  
                                  utilsGui.showMessageError(this,"Σφάλμα. Η βάση δεδομένων '"+strReturn+"' υπάρχει ήδη και δεν δημιουργήθηκε\n για να μή διαγραφούν τα δεδομένα. Προσπαθήστε με άλλο όνομα.");
                              }
                              else if (ret==1)
                              {
                                  stmnt.execute("USE "+strReturn+";");
                              // exists also in DialogMain.createDbSystemTableWhenDatabseExists    
                              stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS `dbsystem` ("+
                    "`dbsystemid` int(11) NOT NULL AUTO_INCREMENT,"+
                    "`dbleadversion` varchar(10) DEFAULT '0',"+
                    "`dbsubversion` varchar(10) DEFAULT '0',"+
                    "PRIMARY KEY (`dbsystemid`)"+
                     ") ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8");

                   stmnt.executeUpdate("REPLACE INTO dbsystem (dbsystemid, dbleadversion, dbsubversion) VALUES ( 1 ,  '1',  '"+STR_VERSIONSUB_START+"' )");                                  
                                  
                                  
                                  //utilsGui.showMessageError(this,"Η βάση δεδομένων '"+strReturn+"' δημιουργήθηκε.")
                                  dbName = strReturn;
                               //   txtDbName.setText(dbName);
                                  txtDbUrl.setText(txtDbUrl.getText()+dbName);
                                  ///  NOT reates error checkMariaDB();
                                  dbCheck(dbEngine);
                                  txtDbInfo.append("\n\nΗ βάση δεδομένων '"+dbName+"' δημιουργήθηκε.");
                                  saveConfig();
                                 
                              }
                              else if(ret>1)
                              {
                                  System.out.println("DialogDbConnect.createDB   strReturn:"+strReturn+"   ret:"+ret);
                              }
                            }
                            catch ( SQLException sqex)
                            {                            
                                if (sqex.getErrorCode() == 1045)
          	                {
          	                     utilsGui.showMessageError(this,"Δεν εχετε πρόσβαση για αυτό το χρήστη.\nΑλλάξτε username ή password.");
          	                }
                                else if(sqex.getErrorCode() == 1049)
                                {
                                    utilsGui.showMessageError(this,"Παρακαλώ διαγράψτε το όνομα της βάσης στο url\n(δηλαδή το κείμενο μετα το χαρακτήρα  /  χωρίς αυτόν).");
                                }
                                else
          	                {                                
                                    utilsGui.showMessageError(this,"Σφάλμα... "+sqex.getErrorCode()+"  "+sqex.getMessage());
                                }
                            }
                        }
                        else
                        {
                            
                        }      
          }
        
    }
    

    private Connection getConnectionToCreateDB()throws SQLException
    {
    	
        Properties p = new Properties(); // properties to be used for connection
   	    String url="";



        String drivers = txtDbDriver.getText();
        if (drivers != null)
           System.setProperty("jdbc.drivers", drivers);

        url = txtDbUrl.getText();

       // String dbDir = txtDbPath.getText() ;
        //if(dbDir !=null )  //if there is in text file
        //{           System.setProperty("derby.system.home", dbDir);  } // load it

        p.put("user", txtDbUser.getText());// username);
        p.put("password", txtDbPass.getText());//password);
        p.put("useUnicode", "true");
        p.put("characterEncoding", "utf8");//iso-8859-7
      
       return DriverManager.getConnection(url, p);
	
    }
        
    
    
    
    
        private boolean saveConfig()
    {
        boolean ret = false;
    	String curDir="";
    
    String dirToBeSaved = txtDbSettingsDir.getText()+FILE_DB_CONFIG;	// +systemDirectorySymbol
    	
     if(dbEngine!=0)
	 {
       //   dbName=txtDbName.getText();
          //dbPath=txtDbPath.getText();
          dbDriver=txtDbDriver.getText();                 
          dbUrl=txtDbUrl.getText();
          dbUser=txtDbUser.getText();
          dbPass=txtDbPass.getText();
	  //    dbEngineDir=txtDbEngineDir.getText();
	 //     backUpsDir=txtBackupDir.getText();
	      dbSettingsDir = txtDbSettingsDir.getText();
	      
        Properties fileProperties = new Properties();
   	
          fileProperties.setProperty("jdbc.dbname", dbName);
          fileProperties.setProperty("jdbc.url", dbUrl);
          fileProperties.setProperty("jdbc.drivers", dbDriver);
          fileProperties.setProperty("jdbc.username", dbUser);
         // UserDataStore p = UserDataStore.getInstance();          
         // fileProperties.setProperty("jdbc.password", p.calcAndGetEncodedPassword(dbPass));
                
        // Protect user's password. The generated value can be stored in DB.
        String pass = utilsString.encrypt(dbPass);
        if(pass==null || pass.equalsIgnoreCase(""))
        {
         
          ret=false;
        }
        else
        {
             fileProperties.setProperty("jdbc.password", pass);
        }
        //fileProperties.setProperty("jdbc.salt", dbSalt);
          //fileProperties.setProperty("derby.system.home", dbPath);
          //fileProperties.setProperty("backup.dbenginedir", dbEngineDir);
         // fileProperties.setProperty("backup.filesdir", backUpsDir);
          fileProperties.setProperty("dbengine",dbEngine+"");
          fileProperties.setProperty("dbSettingsDir",dbSettingsDir); 
         
    

      
      
      
      //System.out.println("DialogSetupDb.saveConfig dir: "+dirToBeSaved);  
      try
      {
        File file = new File(dirToBeSaved);
        // Create file if it does not exist
        boolean success = file.createNewFile();
        if (success)
        {
            // File did not exist and was created
            fileProperties.store(new FileOutputStream(file), null);
        }
        else
        {
            // File already exists
            fileProperties.store(new FileOutputStream(file), null);
        }
        
         txtDbInfo.append("\nsave in:"+dirToBeSaved);
       //txtDbInfo.append("\nuser:"+System.getProperty("user.name"));      
        ret=true;
      }
      catch (IOException e)
      {
          ret=false;
          System.err.println("DialogSetupDb.saveConfig (cannot find file) "+e+" "+FILE_DB_CONFIG);    
      }
      
      
       }
       else
       {
           ret=false;
        	showMessageErrorSelectDatabase();
       } 	
       return ret;
    }
        
        
            /*private String getDbName()
    {


   	String db = ""; //JOptionPane.showInputDialog("Παρακαλώ εισάγετε όνομα βάσης δεδομένων","");//dbName;//txtDbName.getText(); // JOptionPane.showInputDialog("Παρακαλώ εισάγετε όνομα βάσης δεδομένων","");//utilsGui.showMessageError(this,"εισάγετε όνομα βάσης δεδομένων");
  /* if (db == null || db.equalsIgnoreCase(""))
   {
       // db="businesseye";
       txtDbName.setText(db);
   	
   }
   else
   {
   	
   }*/

   	  
   /*   return db;

    }*/
 
            
            
/*            
            private boolean loadFileToDatabase(Connection dbConnection, String fromFile)
    {
       boolean bCreatedTables = false;

    try
    {
    	// open file  db.txt and read line
    	 BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fromFile),"utf-8"));//fileName), "UTF8"));
               //strPrint = in.read();
         
	
    	
        Statement statement = null;
        try {
            statement = dbConnection.createStatement();
           
          if (dbEngine==DBENGINE_DERBYEMBEDDED)
          {  
            String[] createQueries = new String[2];
            
            createQueries[0]= "CREATE TABLE town ("+
            "townId INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"+
            "name VARCHAR(25),"+
            "postCode VARCHAR(6),"+
            "phoneCode VARCHAR(6),"+
            "PRIMARY KEY (townId)"+
            ")";
            
            createQueries[1]= "CREATE TABLE farmer ("+
            "farmerId int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"+
            "surname char(25) default NULL,"+
            "name char(15) default NULL,"+
            "fatherName char(10) default NULL,"+
            "farmerAfm char(10) default NULL,"+
            "idNo char(8) default NULL,"+
            "address char(25) default NULL,"+
            "townId int default NULL,"+
            "phone char(10) default NULL,"+
            "PRIMARY KEY  (farmerId),"+
            "CONSTRAINT farmer_ibfk_1 FOREIGN KEY (townId) REFERENCES town (townId)"+
            ")";


            for(int i=0; i< createQueries.length; i++ )
            {
            	statement.execute(createQueries[i]);       
            	System.out.println("DialogSetupDb.createTables query "+i+" executed");
            }
           }
           else if (dbEngine==DBENGINE_HSQL)
           {
               
            String[] createQueries = new String[2];
            
            createQueries[0]= "CREATE TABLE town ("+
            "townId INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL ,"+
            "name VARCHAR(25),"+
            "postCode VARCHAR(6),"+
            "phoneCode VARCHAR(6),"+
            "PRIMARY KEY (townId)"+
            ")";
            
            createQueries[1]= "CREATE TABLE farmer ("+
            "farmerId int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,"+
            "surname char(25) default NULL,"+
            "name char(15) default NULL,"+
            "fatherName char(10) default NULL,"+
            "farmerAfm char(10) default NULL,"+
            "idNo char(8) default NULL,"+
            "address char(25) default NULL,"+
            "townId int default NULL,"+
            "phone char(10) default NULL,"+
            "PRIMARY KEY  (farmerId),"+
            "CONSTRAINT farmer_ibfk_1 FOREIGN KEY (townId) REFERENCES town (townId)"+
            ")";

            for(int i=0; i< createQueries.length; i++ )
            {
            	statement.execute(createQueries[i]);       
            	System.out.println("DialogSetupDb.createTables query "+i+" executed");
            }
           }
          
          
    
           else
           {
          
             System.out.println("dialogSetupDb.loadFileToDatabase dbEngine "+dbEngine+" not supported");
           }
           
            bCreatedTables = true;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
         }
         catch (IOException e)
         {
         	//ww.close();
         	utilsGui.showMessageError(e.getMessage());
         	System.out.println("DialoSetupDB.loadFileToDatabase IOException "+e.getMessage());
         }
        
        System.out.println("DialogSetupDb.loadFileToDatabase CreatedTables"+bCreatedTables);
        return bCreatedTables;
    }        */
          
       private void displayDialogSystemInfo()
    {
    	DialogSystemInfo dialogSystemInfo = new DialogSystemInfo(null);
       	Dimension paneSize = this.getSize();
       	dialogSystemInfo.locateOnCenterOfTheScreen();
        dialogSystemInfo.setVisible(true);
    /*    desktopPane.add(dialogSystemInfo,LAYER_FOURTH);
       try
        {         dialogSystemInfo.setSelected(true);         }
       catch (java.beans.PropertyVetoException e) {}*/
    }        
 
    
       public static void main(String args[])
    {
    	
     new FrameRedirected(true, false, null, 1020,510, JFrame.DISPOSE_ON_CLOSE);
    	
      DialogDbConnect dialog = new DialogDbConnect();
      
      System.out.println("DialogSetupDb.main classpath "+System.getProperty("java.class.path"));
      System.out.println("DialogSetupDb.main userdir "+System.getProperty("user.dir"));
        
                 Toolkit.getDefaultToolkit().getSystemEventQueue().push(new EventQueueTxtRightClick()); 

         //dialog.locateOnCenterOfTheScreen(this);
         //dialog.setTitle(VariablesGlobal.appName+" Setup DB");
         dialog.setVisible(true);
    }
          
  class ToolBarCommands extends JToolBar implements Constants
{
        
        public ToolBarCommands()
        {
            try
           {     initialize();   }
           catch (Exception e)
           {   e.printStackTrace();    }
        }


        private void initialize() throws Exception
        {


       // btnShowMetadata = new JButton();
       // btnSetup = new JButton();
        btnCheck = new JButton();        
        btnSave = new JButton();
        btnCreateDB = new JButton();
        //btnSetupConfig= new JButton();
        //btnOpenLibPath = new JButton(); 
        btnShowBackupRestore  = new JButton(); 
        btnShowSystemInfo  = new JButton(); 
        btnStart = new JButton(); 

        btnCheck.setText("<html>test</html>");
        btnCheck.setIcon(ICO_INFO16);
        btnCheck.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {   
	            if(dbEngine!=0)
	            {
	                 dbCheck(dbEngine);	
	            }
	            else
	            {
	            	showMessageErrorSelectDatabase();
	            }
	        	
	        }
	    });
        

        
        btnCreateDB.setText("<html>δημιουργία βάσης</html>");
        btnCreateDB.setIcon(ICO_INSERT16);
        btnCreateDB.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {     	
	           createDB();    
	        }
	    });        
        
        
        
        btnSave.setText("<html>αποθήκευση</html>");
        btnSave.setIcon(ICO_UPDATE16);
        btnSave.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {     	
	           saveConfig();    
	        }
	    });

          
        btnShowBackupRestore.setText("<html>backUp/restore</html>");
        btnShowBackupRestore.setOpaque(false);
        btnShowBackupRestore.setToolTipText("αποθήκευση / επαναφορά");
        btnShowBackupRestore.setIcon(ICO_BACKUP);

        btnShowBackupRestore.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	   displayDialogBackupRestore();
	        }
	    });        
        


        btnShowSystemInfo.setText("<html>πληροφορίες</html>");
        btnShowSystemInfo.setIcon(ICO_ABOUT16);
        btnShowSystemInfo.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	   displayDialogSystemInfo();
	        }
	    });


        btnStart.setText("<html>ΟΚ εκκίνηση</html>");
        btnStart.setIcon(ICO_OK16);
        btnStart.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {     
                    
                    closeNStart();

	        }
	    });        
      /*IconSeparator icoSeparator = new IconSeparator();
      JLabel lblIcoSeparator1 =new JLabel();
      JLabel lblIcoSeparator2 =new JLabel();
      JLabel lblIcoSeparator3 =new JLabel();
        


        
        lblIcoSeparator1.setOpaque(false);
        //lblIcoSeparator.setHorizontalAlignment(SwingConstants.RIGHT);
         
         lblIcoSeparator1.setIcon(icoSeparator);
         lblIcoSeparator2.setIcon(icoSeparator);
         lblIcoSeparator3.setIcon(icoSeparator);*/
          
        //add(lblIcoSeparator1);
 
       /* if(frame!=null)
        {
            btnCreateDB.setVisible(false);
            btnShowBackupRestore.setVisible(false);
        }*/
        
        
        add(btnCreateDB);
        add(btnCheck);
        //add(btnOpenLibPath);        
        add(btnSave);
         //addSeparator();
         //add(btnSetupConfig);
       //  addSeparator();
        //add(btnShowMetadata);        
  //      add(btnShowBackupRestore);
       // addSeparator();
        add(btnShowSystemInfo);
        add(btnStart);
        //addSeparator();
        //addSeparator();

        }
}
}
