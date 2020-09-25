 package com.tool.setup;
 
 import com.tool.guicomps.*;

import com.tool.jdbc.*;
 import com.tool.utils.*;
 
 import java.io.InputStreamReader ;  
 import java.io.BufferedReader;
 import javax.swing.JOptionPane;
 import java.io.InputStream;
 import java.io.File;
 import java.io.PrintStream;
 import java.util.Date;
 
 import java.io.OutputStream;
 import java.io.FileInputStream;
 import java.io.LineNumberReader;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipInputStream;
 import java.util.zip.ZipFile;
 import java.util.GregorianCalendar;
 import java.util.Calendar;
 import java.util.Properties;

  import java.sql.*;
  import java.sql.SQLException;
  import java.sql.Connection;
  import java.sql.Statement;
  import java.sql.DriverManager;
 
 import java.util.*;
 import java.util.Enumeration;
 import java.io.BufferedInputStream;
 import java.io.BufferedOutputStream;
 import java.io.FilenameFilter;
 import java.io.PrintWriter;
 import java.io.OutputStreamWriter;
 import java.io.FileNotFoundException;
 import java.io.UnsupportedEncodingException;

 import javax.swing.*;
 import javax.swing.JTable;
 import javax.swing.JDialog;
 import javax.swing.JPanel;
 import javax.swing.JTabbedPane;
 import javax.swing.JFrame;
 import javax.swing.JButton;
 import javax.swing.JTabbedPane;
 import javax.swing.JTextField;
 import javax.swing.JLabel;
 import javax.swing.JTextArea;
 import javax.swing.JScrollPane;
 //import javax.swing.JList;
 import javax.swing.ListSelectionModel;
 import javax.swing.event.ListSelectionListener;
 import javax.swing.event.ListSelectionEvent;
 
import javax.swing.UIManager;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SwingUtilities;

import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.FlowLayout;
import java.awt.Font;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

//import com.mysql.jdbc.exceptions.*;
//import guicomps.*;
 
public class DialogBackUp extends JDialog implements Constants
{
  
      String dirInstalled;
      String saveFile;
      String saveDir;
      String host;
      String user;
      String pass;
      String saveFileZip;
      String openFileZip;
      String driver;
      String url;
      int dbEngine;
      String dbPath;
    String  commentsFile;
    String  dbFile;
    String strDBengine;
    
    private String filePre;
    
    private JFrame parentFrame;
   
    private JTextField txtDirInstalled;
    private JTextField txtHost;
    private JTextField txtUser;
    private JTextField txtPass ;

    private JTextBoxWithEditButtons txtBtnDirInstalled;
    private JLabel lblDirInstalled;
    private String dbEngineName;
    private JLabel lblHost;
    
    //private JList listDatabases;
    private JListDec listBackups;
    private JTableDec tblBackups;
    
    private JTextField txtRestoreToDB;
    
    private String[] filesRetrieved;
    
    private ListSelectionModel lsmR;
    private ListSelectionModel lsmB;
    
    private String today;
    
    private UtilsGui utilsGui;
    

    private String systemDirectorySymbol;
    //private String dbEngine = "mysql";
    //private String[] filesToZip;

   private JTableDec table;
   private TableModelReadOnly tableModel;
   private JScrollPane tableScrollerDatabases;
  //private String systemDirectorySymbol=System.getProperty("file.separator");
   private JLabel lblRetoreToDB;
   
   

    
     private	JTextField txtDirSave ;
     private JTextField txtDirBackToRestore;
   private UtilsDate utilsDate;
   private static UtilsOS utilsOS;   
       private Database db;
    private PrintWriter writer;
   // private JTextArea outText;
    private JTextArea txtareaBackupComments;
    private JTextArea txtareaRestoreComments;
    private JTextArea txtareaLog;   
    private String fileSql;
    private String strSelectedFileToRestore;
   
   private UtilsDouble uDouble;
   
   
     //  private PrintWriter logWriter = new PrintWriter(System.out);
    //private PrintWriter errorLogWriter = new PrintWriter(System.err);
    private Connection connectionRestore;
    //   private boolean stopOnError;
   // private boolean autoCommit;
   // private WindowWait wwb; // for backup
    //private WindowWait wwr; // for restore
    private Thread thread;
    
    private Thread thread1;
    private Thread thread2;
       private static final String DEFAULT_DELIMITER = ";";

    private String delimiter = DEFAULT_DELIMITER;
    private boolean fullLineDelimiter = false;
    private JLabel lblRestoreTo;
    
    private String bckFileDatabase = "";
    private String bckFileDatabaseLeadVersion = "";
    private String bckFileDatabaseSubVersion = "";
    private boolean wasRestoreSuccessful = false;
    
     public DialogBackUp()
    {
       this.setTitle("BackUp - Restore");
       initialize();
         createGui();
         //retrieveSettings();
//         loadConfigFromFile();
//         retrieveDatabases();
         locateOnCenterOfTheScreen();
         //setVisible(true);
    }
 
   
       public DialogBackUp(JFrame parent)//(Frame parent, boolean modal)
    {
       super(parent, "BackUp - Restore", true);
       parentFrame=parent;
       initialize();
       createGui();
       
       //retrieveSettings();
//       loadConfigFromFile();
       
  //     retrieveDatabases();
        //this.setSize(720,600); 
       locateOnCenterOfTheScreen();
       
    }
  

    public void initialize()
    {
    	
    	utilsGui = new UtilsGui();
    	uDouble=new UtilsDouble();
    	//systemDirectorySymbol=System.getProperty("file.separator");
    	
        db = new Database();
        
        txtareaLog = new JTextArea(8,65);

       utilsOS = new UtilsOS();

      systemDirectorySymbol=System.getProperty("file.separator");
      String   curDir="";
      if(utilsOS.isOSWindows())
      {
      	curDir = System.getProperty("user.dir"); // get current dir
      }
      else
      {
      	String classPath = System.getProperty("java.class.path");
      	curDir = classPath.substring(0,classPath.lastIndexOf(systemDirectorySymbol));	
      }
        
        VariablesGlobal.globalDirConfiguration = curDir; 
    	
        

       	  //strDBengine="MariaDB";
       saveDir = VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+DIR_DATABACKUP+systemDirectorySymbol;
       createBackupDirectoryIfDoesNotExist(saveDir);
       
       filePre="backup-";
 	commentsFile="comments.txt";
        fileSql="dbsql.sql";
        
        strSelectedFileToRestore ="";
        
    /*	String laf="java";
       try
       {
    	
         laf = UIManager.getSystemLookAndFeelClassName() ;   
         UIManager.setLookAndFeel(laf);   // when there is chosen laf
       }
       catch(ClassNotFoundException cnfe)   
       {
       	System.out.println(cnfe.getMessage());
       }
        catch (Exception exc)
        {System.out.println("Error "+laf+": "+exc);} 
        
        JDialog.setDefaultLookAndFeelDecorated(true);  
        JFrame.setDefaultLookAndFeelDecorated(true);*/
        
    	    //saveFile="backup"+database+".sql";
            //saveFileZip="backup"+database+".zip";
            
            //openFileZip="backup.zip";
     
    	
    }
    
       private JxPanel createPanelBackUpGui()
    {
       // JxPanel bckPanel = new JxPanel();
       // bckPanel.setLayout(new BorderLayout());
        JxPanel pnlMain = new JxPanel();
        pnlMain.setLayout(new BorderLayout());
        
        JButtonForPanelDecorated btnBackUp = new JButtonForPanelDecorated();
	btnBackUp.setText("<html>back up / αποθήκευση</html>");
	btnBackUp.setIcon(ICO_BACKUP);
	btnBackUp.addActionListener(new ActionListener()
    {
	       public void actionPerformed(ActionEvent e) 
	       {  
	       	    backup(txtDirSave.getText());
	       }
	});
        
        JxPanel panelTop = new JxPanel();
        JLabel lblDirToSave= new JLabel("κατάλογος αποθήκευσης:");
        txtDirSave = new JTextField(35);
        
        txtDirSave.setText(saveDir);
        JButton btnToSave = new JButton();
        btnToSave.setIcon(ICON_TREEFOLDER_OPENED);
        btnToSave.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	   
                     chooseFileToBackup();
	           	   
	        } 
	    });  
        
        
        
        panelTop.add(lblDirToSave);
         panelTop.add(txtDirSave);
        panelTop.add(btnToSave);
	
	JPanel panelCommentsBackup=new JPanel(new BorderLayout());
	JLabel lblCommentsBackup= new JLabel("σχόλια για το back up της βάσης δεδομένων:");
	lblCommentsBackup.setOpaque(false);
	panelCommentsBackup.add(lblCommentsBackup, BorderLayout.PAGE_START);
	
	txtareaBackupComments = new JTextArea(2, 8);
	txtareaBackupComments.setEditable(true);
	txtareaBackupComments.setLineWrap(true);
	txtareaBackupComments.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,11));
    JScrollPane scrollPaneBackupComments = new JScrollPane(txtareaBackupComments);
    panelCommentsBackup.setOpaque(false);
    panelCommentsBackup.add(scrollPaneBackupComments, BorderLayout.CENTER);
    
        JPanel panelBottom = new JPanel(new FlowLayout());
	panelBottom.setOpaque(false);
        panelBottom.add(btnBackUp);
        
        
        pnlMain.add(panelTop,BorderLayout.PAGE_START);
        pnlMain.add(panelCommentsBackup,BorderLayout.CENTER);
       pnlMain.add(panelBottom, BorderLayout.PAGE_END);

        return pnlMain;
    }
  
       
    private JxPanel createPanelRestoreGui()
    {
       // JxPanel bckPanel = new JxPanel();
       // bckPanel.setLayout(new BorderLayout());
        JxPanel pnlMain = new JxPanel();
        pnlMain.setLayout(new BorderLayout());

        
        JxPanel panelTop = new JxPanel();
        JLabel lblDir= new JLabel("κατάλογος:");
         txtDirBackToRestore = new JTextField(35);
        txtDirBackToRestore.setText(VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+DIR_DATABACKUP+systemDirectorySymbol);
        JButton btnDir = new JButton();
        btnDir.setIcon(ICON_TREEFOLDER_OPENED);
        btnDir.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	  
                     chooseDirToRestore();
                     retrieveBackUpFiles(txtDirBackToRestore.getText());
	           	  // VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+DIR_DATABACKUP+systemDirectorySymbol
	        } 
	    });  
        
       JButton btnRefresh = new JButton();
        btnRefresh.setIcon(ICO_RELOAD16);  // ICO_RELOAD16  
        btnRefresh.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {	  
                    //chooseDirToRestore();
                     retrieveBackUpFiles(txtDirBackToRestore.getText());
	           	  // VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+DIR_DATABACKUP+systemDirectorySymbol
	        } 
	    });          
        
        panelTop.add(lblDir);
         panelTop.add(txtDirBackToRestore);
        panelTop.add(btnDir);  
        panelTop.add(btnRefresh);
        
        
	JPanel panelRestoreMain = new JPanel(new BorderLayout());
	panelRestoreMain.setOpaque(false);        
       JLabel lblBackups = new JLabel("αρχεία backup:");
	lblBackups.setOpaque(false);
	panelRestoreMain.add(lblBackups, BorderLayout.PAGE_START);
	
    listBackups = new JListDec(); 
    listBackups.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            lsmR = listBackups.getSelectionModel();
                lsmR.addListSelectionListener(new ListSelectionListener()
            {
                public void valueChanged(ListSelectionEvent e)
                {
                    //Ignore extra messages.
                    if (e.getValueIsAdjusting()) return;
                                        
                    lsmR = (ListSelectionModel)e.getSource();
                    if (lsmR.isSelectionEmpty())
                    {
                        //System.out.println("No rows are selected.");
                    } else
                    {   
                      String fileZip = filesRetrieved[lsmR.getMinSelectionIndex()];
                      strSelectedFileToRestore = txtDirBackToRestore.getText()+fileZip;
                      showCommentsForRestoreFile(txtDirBackToRestore.getText()+fileZip);
                    }
                }
            });
    
    
	JScrollPane listScrollerBackups = new JScrollPane(listBackups);
    //listScrollerBackups.setPreferredSize(new Dimension(300, 120));
	panelRestoreMain.add(listScrollerBackups, BorderLayout.CENTER);      
        
        
        
       	JPanel panelRestoreToDB = new JPanel(new FlowLayout());
	panelRestoreToDB.setOpaque(false); 

JButtonForPanelDecorated btnRestore = new JButtonForPanelDecorated();
    btnRestore.setText("<html>restore / επαναφορά</html>");
    btnRestore.setIcon(ICO_RESTORE);
	btnRestore.addActionListener(new ActionListener()
    {
	       public void actionPerformed(ActionEvent e) 
	       { 
	         // txtareaLog.setText("");
	          if(strSelectedFileToRestore!=null && !strSelectedFileToRestore.equalsIgnoreCase(""))
                  {    //                     when last is false the this should be ""
                      try
                      {
                          restore(strSelectedFileToRestore,fileSql,"","παρακαλω περιμένετε, restore / επαναφορά",false);
                      }
                      catch(IOException ioe)
                      {
                          System.out.println("DialogBackUp.btnRestore  IOException  "+strSelectedFileToRestore+"   "+fileSql+"   "+ioe.getMessage());
                      }
                  }
                  else
                  {
                      utilsGui.showMessageInfo("Παρακαλώ επιλέξτε πρώτα ένα αρχείο.");
                  }

	       }
	});	
        panelRestoreToDB.add(btnRestore);
        JxPanel panelRestore = new JxPanel();
        	panelRestore.setLayout(new BorderLayout());
	panelRestore.setOpaque(false);
	
    
    JPanel panelCommentsRestore=new JPanel(new BorderLayout());
    JPanel panelRestoreTo=new JPanel(new FlowLayout());
    panelCommentsRestore.setOpaque(false);
	JLabel lblCommentsRestore= new JLabel("σχόλια επιλεγμένης βάσης δεδομένων:");
         lblRestoreTo = new JLabel();
	
         panelRestoreTo.add(lblCommentsRestore);
        panelRestoreTo.add(lblRestoreTo);
    
      panelCommentsRestore.add(panelRestoreTo, BorderLayout.PAGE_START);
    txtareaRestoreComments = new JTextArea(4, 8);
	txtareaRestoreComments.setEditable(false);
	txtareaRestoreComments.setLineWrap(true);
	txtareaRestoreComments.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,11));
    JScrollPane scrollPaneRestoreComments = new JScrollPane(txtareaRestoreComments);
    panelCommentsRestore.add(scrollPaneRestoreComments,BorderLayout.PAGE_END);
    panelRestore.add(panelCommentsRestore,BorderLayout.PAGE_START);     
    panelRestore.add(panelRestoreToDB, BorderLayout.PAGE_END);
        
        
        
        
      
        
        
        pnlMain.add(panelTop,BorderLayout.PAGE_START);
        pnlMain.add(panelRestoreMain,BorderLayout.CENTER);
       pnlMain.add(panelRestore, BorderLayout.PAGE_END);

        return pnlMain;        
    }
    
    private void createGui()
    {
    	utilsGui.setLookAndFeel();
    	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    	setLayout(new BorderLayout());
    //  ---------------------  northpanel------------------	
        	JPanel northPanel = new JPanel();
        	northPanel.setLayout(new BorderLayout());
        	
        	JPanel panelDirs = new JPanel(new BorderLayout());
        	
        	JPanel panelDirSave = new JPanel(new FlowLayout());
        	JLabel lblSaveDir = new JLabel("κατάλογος αποθήκευσης:");
        	panelDirSave.add(lblSaveDir);
        	txtDirSave = new JTextField(32);
        	JTextBoxWithEditButtons txtBtnDirSave = new JTextBoxWithEditButtons();
        	txtBtnDirSave = new JTextBoxWithEditButtons(txtDirSave, true ,ICON_TREEFOLDER_OPENED,null, false,null,null,2, (JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this),"","",MONTH_DATE_ONLY);                  	
        	        	
            //panelDirSave.add(txtDirSave);
            /*JButton btnDir = new JButton("κατάλογος");
          	btnDir.addActionListener(new ActionListener()
          {
	       public void actionPerformed(ActionEvent e) 
	       {  
	          showDirCatalog();
	       }
	      });*/
       	   panelDirSave.add(txtBtnDirSave.getComponent());
        	
  /*       	JPanel panelDirSQL = new JPanel(new FlowLayout());
        	lblDirInstalled = new JLabel("κατάλογος 'bin' MariaDB-MySQL:");
        	panelDirSQL.add(lblDirInstalled);
        	txtDirInstalled = new JTextField(32);
        	
        	txtBtnDirInstalled = new JTextBoxWithEditButtons();
        	txtBtnDirInstalled = new JTextBoxWithEditButtons(txtDirInstalled, true ,ICON_TREEFOLDER_OPENED,null, false,null,null,2, (JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this),"",MONTH_DATE_ONLY);                  	
        	
        	panelDirSQL.add(txtBtnDirInstalled.getComponent());
        	*/
        	panelDirs.add(panelDirSave, BorderLayout.PAGE_START);
        	//panelDirs.add(panelDirSQL, BorderLayout.PAGE_END);
        	northPanel.add(panelDirs);
        	
       /* 	JPanel panelDBProps = new JPanel(new FlowLayout());
        	lblHost = new JLabel("host:");
        	panelDBProps.add(lblHost);
        	txtHost = new JTextField(15);
        	panelDBProps.add(txtHost);
        	JLabel lblUser = new JLabel("user:");
        	panelDBProps.add(lblUser);
        	txtUser = new JTextField(10);
        	panelDBProps.add(txtUser);
        	JLabel lblPass = new JLabel("password:");
        	panelDBProps.add(lblPass);
        	txtPass = new JTextField(10);
        	panelDBProps.add(txtPass);
        	northPanel.add(panelDBProps, BorderLayout.PAGE_END);*/
        	
	// ------------------  tab --------------------------
    	final String BACKUPPANEL = "Back Up / αποθήκευση";
        final String RESTOREPANEL = "Restore / επαναφορά";
    	JxTabbedPane tabPanel = new JxTabbedPane();
        JxPanel panelBackup = this.createPanelBackUpGui();//new JxPanel();
        JxPanel panelRestore = this.createPanelRestoreGui();
    
       tabPanel.addChangeListener( new TabListener(){
        public void stateChanged(ChangeEvent e)
        {
        
        // Prints the string 3 times if there are 3 tabs etc
          if(tabPanel.getSelectedIndex()==0)
          {
              
          }
          else if (tabPanel.getSelectedIndex()==1)
          {
              retrieveBackUpFiles(txtDirBackToRestore.getText());
          }
          else
          {
              System.out.println("DialogBackUp.createGui  UNKNOWN tab:" + tabPanel.getSelectedIndex());
          }
        }
      
      
      } );

      tabPanel.addTab(BACKUPPANEL,ICO_BACKUP,panelBackup);
      tabPanel.addTab(RESTOREPANEL,ICO_RESTORE,panelRestore);
        // addTab(String title, Icon icon, Component component)       


    
	//----------------southpanel--------------------------------------
	
	txtareaLog.setEditable(false);
	txtareaLog.setLineWrap(true);
	txtareaLog.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,11));
        JScrollPane scrollPane = new JScrollPane(txtareaLog);

    	
    //-------------------------------------------------------
   // 	getContentPane().add(northPanel, BorderLayout.PAGE_START);
    	getContentPane().add(tabPanel, BorderLayout.CENTER);
    	getContentPane().add(scrollPane, BorderLayout.PAGE_END);
    	//setSize(200,200);
    	
    
    	
    	pack();
    }
   
    
       private void chooseDirToRestore()
      {
          // the third
// https://stackoverflow.com/questions/2883447/jfilechooser-select-directory-but-show-files
    
 
 JFileChooser fileChooser = new JFileChooser(txtDirBackToRestore.getText());

fileChooser.setFileSelectionMode(
        JFileChooser.FILES_AND_DIRECTORIES);

int option = fileChooser.showDialog(null, "Select Directory");

if (option == JFileChooser.APPROVE_OPTION) {
    File f = fileChooser.getSelectedFile();
    // if the user accidently click a file, then select the parent directory.
    if (!f.isDirectory()) {
        f = f.getParentFile();
    }
    //System.out.println("Selected directory for import " + f);
     txtDirBackToRestore.setText(f.getAbsolutePath()+systemDirectorySymbol)   ;
}         
          
    
      } 
    
    
    
         private void chooseFileToBackup()
      {

 JFileChooser fileChooser = new JFileChooser(txtDirSave.getText());

fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//.FILES_AND_DIRECTORIES);

int option = fileChooser.showDialog(null, "Select Directory");

if (option == JFileChooser.APPROVE_OPTION) {
    File f = fileChooser.getSelectedFile();
    // if the user accidently click a file, then select the parent directory.
    if (!f.isDirectory()) {
        f = f.getParentFile();
    }
        //File file = new File(strFileEmpty);
        txtDirSave.setText(f.getAbsolutePath()+systemDirectorySymbol)   ;
    
               
      }
    }
    
    public void locateOnCenterOfTheScreen()
    {
    	Dimension paneSize   = this.getSize();
    	Dimension screenSize = this.getToolkit().getScreenSize();
    	this.setLocation(
            (screenSize.width  - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
    }

   /*private void NOTbackup()
   {
   	  boolean isSelected=false;
   	  
   	  createBackupDirectoryIfDoesNotExist();
   	  

      final int selRow = table.getSelectedRow();
   	  if(selRow!=-1)
   	  {
   	  	
   	  	 ww = new WindowWait("παρακαλω περιμένετε, back-up / αποθήκευση",WINDOW_LOCATION_CENTER,ICO_RELOAD16, ICO_RELOADB16);
         ww.animate();
   		          // thread for show window wait
	         thread = new Thread(new Runnable() {
	          public void run()
	          {
	          
           	       ww.showWindow();
	          
	               thread = null;
	          }
	          });
              thread.start();   	  	
   	  	
   	  	 txtareaLog.setText("");
         //final ArrayList selectedDatabasesFinal = selectedDatabases;   	  	 
              // thread for backup
            thread = new Thread(new Runnable() {
	          public void run()
	          {

   	  //	for(int r =0;r<selectedDatabasesFinal.size();r++)
   	  //	{
         //final int rFinal = r;

   	          
	          //int selFinal=(Integer)selectedDatabases.get(r);
	               //int sel=(Integer)selectedDatabasesFinal.get(r);
	               String database = table.getValueAt(selRow,0).toString();

	               
	             if(dbEngine==DBENGINE_MARIADB)
	             {
	             	

	             	
	                txtareaLog.append("\nbacking up '"+filePre+database+today+"' please wait ...");
                    System.out.println("backing up '"+filePre+database+today+"' please wait...");
	             	
	               if(mysqldumpDb(database))
	               {
	               	   
	               	    //txtareaLog.append("\nΤο dumping ολοκληρώθηκε επιτυχώς. Ακολουθει συμπίεση.");
	                    if(zip(database))
	                    {
	                        txtareaLog.append("\nΤο back-up της "+database+" ολοκληρώθηκε επιτυχώς.");	
	                    }
	                    else
	                    {
	                    	txtareaLog.append("\nΤο back-up της "+database+" δεν ολοκληρώθηκε.");	
	                    }
	               }
	               else
	               {
	               	  txtareaLog.append("\nΤο dumping της "+database+" δεν ολοκληρώθηκε.");
	               }
	               
	               ww.close();
	               thread = null;
	             }
                     else if(dbEngine==DBENGINE_MYSQL)
	             {
	             	

	             	
	                txtareaLog.append("\nbacking up '"+filePre+database+today+"' please wait ...");
                    System.out.println("backing up '"+filePre+database+today+"' please wait...");
	             	
	               if(mysqldumpDb(database))
	               {
	               	   
	               	    //txtareaLog.append("\nΤο dumping ολοκληρώθηκε επιτυχώς. Ακολουθει συμπίεση.");
	                    if(zip(database))
	                    {
	                        txtareaLog.append("\nΤο back-up της "+database+" ολοκληρώθηκε επιτυχώς.");	
	                    }
	                    else
	                    {
	                    	txtareaLog.append("\nΤο back-up της "+database+" δεν ολοκληρώθηκε.");	
	                    }
	               }
	               else
	               {
	               	  txtareaLog.append("\nΤο dumping της "+database+" δεν ολοκληρώθηκε.");
	               }
	               
	               ww.close();
	               thread = null;
	             }
	             else if(dbEngine==DBENGINE_H2EMBEDDED)  
	             {
	             	//txtareaLog.append("not supported yet");
	             	//java org.h2.tools.Script -url jdbc:h2:file:C:\mydocs\!projects\DBTool\h2_db\farmersvat -user sa -script C:\mydocs\!projects\DBTool\db\backupH2.h2backup.zip -options compression zip
                    
	             	txtareaLog.append("\nbacking up '"+filePre+database+today+"'please wait ...");
                    System.out.println("backing up '"+filePre+database+today+"'please wait...");
                                        
                    if(h2DumpDb(database))
                    {
                    	
	               	    //txtareaLog.append("\nΤο dumping ολοκληρώθηκε επιτυχώς. Ακολουθει συμπίεση.");                    	
	                    if(zip(database))
	                    {
	                        txtareaLog.append("\nΤο back-up της "+database+" ολοκληρώθηκε επιτυχώς.");	
	                    }
	                    else
	                    {
	                    	txtareaLog.append("\nΤο back-up της "+database+" δεν ολοκληρώθηκε.");	
	                    }                  	
                    }
                    else
                    {
                    	txtareaLog.append("\nΤο back-up της "+database+" δεν ολοκληρώθηκε.");
                    }

	             	ww.close();
	             	thread = null;
	             }
	             else
	             {
	             	//ww.close();
	             	thread = null;
	             	utilsGui.showMessageError(this,"DialogBackUp.backup dbEngine:"+dbEngine+" "+dbEngineName+" not supported");
	             }
	             
	               ww.close();
	               //thread = null;

       // } // for

	          }
	          });
              thread.start();
        
        //ww.close();
   	   }  // if
   	   else
   	   {
   	   	  utilsGui.showMessageError(this,"Παρακαλώ επιλέξτε πρώτα όνομα βάσης δεδομένων προς backup.");
   	   }
   }*/
   
   /*private void restore()
   {
   	
   	  if(listBackups.getSelectedIndex()!=-1)
   	  {
   	  	 ww = new WindowWait("παρακαλω περιμένετε, restore / επαναφορά",WINDOW_LOCATION_CENTER,ICO_RELOAD16, ICO_RELOADB16);
       ww.animate();
       
       	     final  String zipFile = filesRetrieved[listBackups.getSelectedIndex()];
	         final String dir = txtDirSave.getText();
	          
   		          // thread for show window wait
	         thread = new Thread(new Runnable() {
	          public void run()
	          {
	          
	          
	              
           	       ww.showWindow();
	          
	               thread = null;
	          }
	          });
              thread.start();
              
              // thread for backup
              thread = new Thread(new Runnable() {
	          public void run()
	          {
	                
	               txtareaLog.setText("")  ;
                     if(dbEngine==DBENGINE_MARIADB)
	             {
	               if (unzip(dir+zipFile))
	               {	               
	                   if(mysqlRestoreDb(dir,dbFile))
	                   {
	                   	  txtareaLog.append("\nΤο restore του "+zipFile+" ολοκληρώθηκε.");
	                   }
	                   else
	                   {
	                   	    
                             
                            deleteFile(saveDir+systemDirectorySymbol+commentsFile);
                            deleteFile(saveDir+systemDirectorySymbol+dbFile);
                            
	                   	  txtareaLog.append("\nΤο restore του "+zipFile+" δεν ολοκληρώθηκε.");
	                   }
	               
	               }
	               else
	               {
                            deleteFile(saveDir+systemDirectorySymbol+commentsFile);
                            deleteFile(saveDir+systemDirectorySymbol+dbFile);               	  
	               	  txtareaLog.append("\nΤο restore του "+zipFile+" δεν ολοκληρώθηκε.");
	               }
	               
	               ww.close();
	               thread = null;
	             } 
                     else if(dbEngine==DBENGINE_MYSQL)
	             {
	               if (unzip(dir+zipFile))
	               {	               
	                   if(mysqlRestoreDb(dir,dbFile))
	                   {
	                   	  txtareaLog.append("\nΤο restore του "+zipFile+" ολοκληρώθηκε.");
	                   }
	                   else
	                   {
	                   	    
                             
                            deleteFile(saveDir+systemDirectorySymbol+commentsFile);
                            deleteFile(saveDir+systemDirectorySymbol+dbFile);
                            
	                   	  txtareaLog.append("\nΤο restore του "+zipFile+" δεν ολοκληρώθηκε.");
	                   }
	               
	               }
	               else
	               {
                            deleteFile(saveDir+systemDirectorySymbol+commentsFile);
                            deleteFile(saveDir+systemDirectorySymbol+dbFile);               	  
	               	  txtareaLog.append("\nΤο restore του "+zipFile+" δεν ολοκληρώθηκε.");
	               }
	               
	               ww.close();
	               thread = null;
	             }
	             else if(dbEngine==DBENGINE_H2EMBEDDED)  
	             {
	             	
	             //txtareaLog.append("h2");
	             	//java org.h2.tools.Script -url jdbc:h2:file:C:\mydocs\!projects\DBTool\h2_db\farmersvat -user sa -script C:\mydocs\!projects\DBTool\db\backupH2.h2backup.zip -options compression zip
                    if(unzip(dir+zipFile))
                    {
	                   if(h2RestoreDb(dir,dbFile))
	                   {
	                   	  txtareaLog.append("\nΤο restore του "+zipFile+" ολοκληρώθηκε.");
	                   }
	                   else
	                   {
                            deleteFile(saveDir+systemDirectorySymbol+commentsFile);
                            deleteFile(saveDir+systemDirectorySymbol+dbFile);	                   	
	                   	  txtareaLog.append("\nΤο restore του "+zipFile+" δεν ολοκληρώθηκε.");
	                   }
	               
	               }
	               else
	               {
	               	    deleteFile(saveDir+systemDirectorySymbol+commentsFile);
                        deleteFile(saveDir+systemDirectorySymbol+dbFile);
	               	  txtareaLog.append("\nΤο restore του "+zipFile+" δεν ολοκληρώθηκε.");
	               }
             	
	             	ww.close();
	             	thread = null;
	             }
	             else
	             {
	             	ww.close();
	             	thread = null;
	             	utilsGui.showMessageError(this,"DialogBackUp.restore dbEngine:"+dbEngine+" "+dbEngineName+" not supported");
	             }
	             
	               //ww.close();
	               //thread = null;
	          }
	          });
              thread.start();  
   	   }
   	   else
   	   {
   	   	  utilsGui.showMessageError(this,"Παρακαλώ επιλέξτε πρώτα όνομα βάσης δεδομένων για restore.");
   	   }   	
   	
   }*/
 
   private void createBackupDirectoryIfDoesNotExist(String strBackupDir)
   {
     // create directory if does not exist
    //System.out.println("DialogBackUp.loadConfigFromFile directory "+saveDir+" check");
    File file=new File(strBackupDir);
    boolean exists = file.exists();
    if (!exists)
    {
      // It returns false if File or directory does not exist
      System.out.println("the file or directory you are searching does NOT exist : " + exists);
      boolean success = (new File(strBackupDir)).mkdir();
      if (!success)
      {
      	System.out.println("DialogBackUp.loadConfigFromFile directory "+strBackupDir+" failed to be created");
      }   
    }
    else
    {
      // It returns true if File or directory exists
      //System.out.println("the file or directory you are searching does exist : " + exists);
    }
    
  }
   
   /*private boolean NOTmysqlRestoreDb(String dir, String dbFile)
   {
   	boolean ret =false;
   	
   	if(!txtRestoreToDB.getText().equals(""))
   	{
   
   	

   	
   	
   	
   		       	  // thread for show window wait


	          

	           //restoreDB(txtRestoreToDB.getText(),dir+dbFile);//zipFile.replaceAll(".zip",".txt"));
                   try
                   {
   
   	         /*    	http://ubuntuforums.org/showthread.php?t=1184395
             String[] executeCmd = new String[]{"mysql", databaseName, "- -user=" + userName, "--password=" + password, "-e", " source c:/data/sample.sql" };
               Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
               processComplete = runtimeProcess.waitFor();
              [Note: this 'processComplete' is an integer variable which is by default '0', and when the runtime process gets executed, if it is successfull, the process will return '0' otherwise it will return '1'.
   	           */   
	                       
	                       
	 //                      thread.sleep(200);
	                       
	                       
	           /* shell> mysql --host=localhost --user=myname --password=mypass mydb
                  shell> mysql -h localhost -u myname -pmypass mydb       */
               
               	
         /*      mysqlCreateDatabase(txtRestoreToDB.getText());
	                       
	           String[] executeCmd = new String[]{"mysql", "--database="+txtRestoreToDB.getText(), "--host="+txtHost.getText(),"--user=" + txtUser.getText(), "--password=" + txtPass.getText(), "-e", " source "+dir+dbFile };
               System.out.println("DialogBackUp.mysqlRestoreDb mysql --database="+txtRestoreToDB.getText()+" --host="+txtHost.getText()+" --user=" + txtUser.getText()+" --password=" + txtPass.getText()+ " < "+dir+dbFile );
               
               Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
               int processComplete = runtimeProcess.waitFor();
	                       
	                       if(processComplete==0)//loadFileToDatabase(getConnectionToDB(txtRestoreToDB.getText(),txtHost.getText()),dir+dbFile))
	                       {
	                       	  txtareaLog.append("\nΤο restore εκτελέστηκε επιτυχώς.");
	                       	  ret = true;
	                       }
	                       else
	                       {
	                       	  txtareaLog.append("\nΤο restore απέτυχε.");
	                       	  ret = false;
	                       }
	                       
                   } 
	               catch(IOException ioe)
	               {
	               	
	           	        //utilsGui.showMessageError(ioe.getMessage());
	           	     
	           	     System.out.println("DialogBackup.mysqlRestoreDb error "+ioe.getMessage());	               	
	               }              
	          	   
	              /* catch(SQLException se)
	               {
	               	 ww.close();
	               	 if(se.getErrorCode()==1049)
	               	 {
	               	 	int intRet = utilsGui.showConfirmYesOrNoOrCancel(listBackups,"Δεν υπάρχει βάσει η δεδομένων '"+txtRestoreToDB.getText()+"'. \n Θέλετε να τη δημιουργήσετε;");
	               	 	
	               	 	if(intRet==0)
	               	    {   // for createDatabase needs nick as user but to restore needs root //////////////////////////////////////////////////////////////////////////////////
	               	    	mysqlCreateDatabase(txtRestoreToDB.getText());
	               	    	txtareaLog.append("\nΗ βάση δεδομένων "+txtRestoreToDB.getText()+" δημουργήθηκε.");
	               	        mysqlRestoreDb(dir,dbFile);
	               	    }

	               	 }
	               	 else
	               	 {
	           	        utilsGui.showMessageError(se.getMessage());
	           	     }
	           	     System.out.println("DialogBackup.CreateGui error code"+se.getErrorCode()+" "+se.getMessage());
	               } */
	/*               catch(InterruptedException ie) 
	               {
	               	 utilsGui.showMessageError(ie.getMessage());
	           	     System.out.println("DialogBackup.mysqlRestoreDb "+ie.getMessage());
	               }

     }
     else
     {
     		       ww.close();
	               thread = null;
     	utilsGui.showMessageError(this,"Παρακαλώ συμπληρώστε το όνομα της βάσης δεδομένων στο οποίο θα γίνει το restore/επαναφορά.");
     }
	          


   	return ret;
   }*/
   
   private void NOTmysqlCreateDatabase(String db)
   {
 	   host=txtHost.getText();
       user=txtUser.getText();
       pass=txtPass.getText(); 
   
   try
   {
   
   Runtime rt = Runtime.getRuntime();
   Process child2;
   
   System.out.println("DialogBackUp.createDatabase "+dirInstalled+"mysqladmin -h"+host+" -u"+user+" -p"+pass+" CREATE "+db);
   
   child2=rt.exec(dirInstalled+"mysqladmin -h"+host+" -u"+user+" -p"+pass+" CREATE "+db);	
   int ch2;
   InputStream in2 = child2.getInputStream();
   while ((ch2 = in2.read()) != -1)
   {
      //ps.write(ch1);
      txtareaLog.append(""+ch2);
      System.out.write(ch2); //to view it by console   
   }

   InputStream err2 = child2.getErrorStream();
   while ((ch2 = err2.read()) != -1)
   {
       System.out.write(ch2);
   }
   
   
   }
   catch(IOException ioe)
   {
	   utilsGui.showMessageError(ioe.getMessage());
       ioe.printStackTrace();
   }
   catch(Exception exc)
   {
	   utilsGui.showMessageError(exc.getMessage());
       exc.printStackTrace();
   }	
   	
   }


    // same as DialogSetupDB
    private boolean NOTloadFileToDatabase(Connection dbConnection, String fromFile)
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
           else if (dbEngine==DBENGINE_MYSQL) /////////////////////// mysql
           {
 
           	   String line ="";
           	   int intline =0;
           	   String q="";	
           	   boolean hasEndOfSql= true;
           	   while((line = in.readLine()) != null)
               {
                 intline++;
               	if(!line.equals(""))
               	{  
                  
                 if(!line.startsWith(TXT_SQLSTARTCOMMENTS1) && !line.startsWith(TXT_SQLSTARTCOMMENTS2)) 
               	 {
               			  //System.out.println("not comments "+line);
               	       if(line.endsWith(TXT_ENDOFSQL))
                       {
  
                          if(!hasEndOfSql)// if it doesnt have end of sql in senteces add the next sentence wich has
                          {
                          	 q=q+line;
                          }
                          else
                          {
                          	 q=line;
                          }
                           hasEndOfSql= true;
                          //System.out.println("contains ;"+q); 
               		   }
               		   else
               		   {
                          q=q+line;
                          hasEndOfSql=false;
                    	  //System.out.println("sentence "+q);
               		   }
               	 }
               	 else
                 {
               		  if(line.startsWith(TXT_SQLSTARTCOMMENTS2))// if is view and starts with /*
               		  {
               		  	
               		  	
               		  	if(line.endsWith(TXT_ENDOFSQL))
                       {
  
                          if(!hasEndOfSql)// if it doesnt have end of sql in senteces add the next sentence wich has
                          {
                          	 q=q+line;
                          }
                          else
                          {
                          	 q=line;
                          	 
                          }
                           hasEndOfSql= true;
                          //System.out.println("contains ;"+q); 
               		   }
               		   else
               		   {
                          q=q+line;
                          hasEndOfSql=false;
                    	  //System.out.println("sentence "+q);
               		   }
               		  	
               		  }
               		  else
               		  {
               		  
               	    	//System.out.println("comments "+line);
               	    	hasEndOfSql= true;// to stop adding comment in the start of the first query
               			q=line;
               		  }
               	 }

                          if(!hasEndOfSql)// if it doesnt have end of sql in senteces add the next sentence wich has
                          {
                          	  //hasEndOfSql= true;
                          }
                          else
                          {
                          	  //System.out.println("q "+intline+" "+q);
               		          statement.execute(q);
                          	  q="";
                          }
               	 }// not nothing in line
               	}

           	   in.close();
           	 
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
         	
         	utilsGui.showMessageError(e.getMessage());
         	System.out.println("DialoSetupDB.loadFileToDatabase IOException "+e.getMessage());
         }
        
        System.out.println("DialogSetupDb.loadFileToDatabase CreatedTables"+bCreatedTables);
        return bCreatedTables;
    }
    
    // same as DialogSetupDb
    /*private Connection getConnectionToDB(String dbIn, String hostIn)throws SQLException
    {
    	
        Properties p = new Properties(); // properties to be used for connection
   	    String url="";



        String drivers="";
        if (dbEngine==DBENGINE_MYSQL)
        {
           drivers = "com.mysql.jdbc.Driver";	
           url = "jdbc:mysql://"+hostIn+":3306/"+dbIn;
        }
        else
        {
        	utilsGui.showMessageError(this,"DialogBackUp.dbEngine not mysql");
        	System.out.println("DialogBackUp.dbEngine not mysql");
        }
        
        if (drivers != null)
           System.setProperty("jdbc.drivers", drivers);

        
       
       /*  for derby
        String dbDir = txtDbPathDerby.getText() ;
        if(dbDir !=null )  //if there is in text file
        {           System.setProperty("derby.system.home", dbDir);  } // load it
        */
 
   /*     p.put("user", txtUser.getText());// username);
        p.put("password", txtPass.getText());//password);
        p.put("useUnicode", "true");
        p.put("characterEncoding", "utf8");//iso-8859-7
      
       return DriverManager.getConnection(url, p);
	
    }*/

 
 private boolean zip(String database,String strSaveDir)
 {

	/*   dirInstalled=txtDirInstalled.getText();
       saveDir= txtDirSave.getText();
 	   host=txtHost.getText();
       user=txtUser.getText();
       pass=txtPass.getText();*/
   
    boolean ret=false;
     try
     {
     PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(strSaveDir+commentsFile), "utf-8"));//Cp437
     printWriter.print("<comments>"+txtareaBackupComments.getText()+"</comments>"+"<database>"+database+"</database>"+"<versionlead>"+VariablesGlobal.appLeadVersion+"</versionlead>"+"<versionsub>"+VariablesGlobal.appSubVersion+"</versionsub>");
     printWriter.close();
     }
     catch ( FileNotFoundException fnfe)
     {
     	System.out.println(fnfe.getMessage());
     }
     catch(UnsupportedEncodingException uee)
     {
     	System.out.println(uee.getMessage());
     }
     // Specify files to be zipped
     String[] filesToZip = new String[2];// look to retrieveSettings
     //     strSaveDir because it compresses the folders into the zip
     filesToZip[0] = fileSql;//+database+today+".txt";
     filesToZip[1] = commentsFile;
    // filesToZip[2] = "temp\thirdfile.txt";

     byte[] bufferC = new byte[18024];

     // Specify zip file name
     String zipFileName = strSaveDir+filePre+database+"_"+VariablesGlobal.appLeadVersion+"."+VariablesGlobal.appSubVersion+"_"+today+".zip";

     try {

       ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));

       // Set the compression ratio
       out.setLevel(Deflater.BEST_COMPRESSION);//.DEFAULT_COMPRESSION);

       // iterate through the array of files, adding each to the zip file
       for (int i = 0; i < filesToZip.length; i++) 
       {
         
           //System.out.println("DialogBackup.zip  file:"+strSaveDir+filesToZip[i]);
         // Associate a file input stream for the current file
         FileInputStream in = new FileInputStream(strSaveDir+filesToZip[i]);  /////strSaveDir+filesToZip[i]);  ///
         
         // Add ZIP entry to output stream.
         out.putNextEntry(new ZipEntry(filesToZip[i]));

         // Transfer bytes from the current file to the ZIP file
         //out.write(buffer, 0, in.read(buffer));
       	 
       	 //txtareaLog.append("\n  zipping file "+(i+1)+" "+filesToZip[i]);
         //System.out.println("zipping file "+(i)+" "+filesToZip[i]);

         int len;
        while ((len = in.read(bufferC)) > 0)
        {
             out.write(bufferC, 0, len);
        }
         // Close the current entry
         out.closeEntry();

         // Close the current file input stream
         in.close();

       }// for
       // Close the ZipOutPutStream
       out.close();
       txtareaLog.setText(txtareaLog.getText()+"\n backup save dir:"+zipFileName);
       System.out.println("backup save dir:"+zipFileName);
       // delete sql file
    for(int i = 0; i < filesToZip.length; i++)
    {   
    	
       deleteFile(strSaveDir+filesToZip[i]);
    }
       ret=true;
     }
     catch (IllegalArgumentException iae)
     {
     	ret=false;
     	JOptionPane.showMessageDialog(null,iae.getMessage());
       iae.printStackTrace();
     }
     catch (FileNotFoundException fnfe)
     {
     	ret=false;
     	JOptionPane.showMessageDialog(null,fnfe.getMessage());
       fnfe.printStackTrace();
     }
     catch (IOException ioe)
     {
     	ret=false;
     	JOptionPane.showMessageDialog(null,ioe.getMessage());
       ioe.printStackTrace();
     }

     return ret;
   
}
 	
/* private boolean NOTunzip(String fileNPath)
 {
 	boolean ret=false;
 //	   dirInstalled=txtDirInstalled.getText();
 //      saveDir= txtDirSave.getText();
// 	   host=txtHost.getText();
//       user=txtUser.getText();
 //      pass=txtPass.getText();
 	
 	 int bufferE = 2048;
 	     try
     {
       //System.out.println("Example of ZIP file decompression.");

       // Specify file to decompress
       //String inFileName = "c:\example.zip";
       // Specify destination where file will be unzipped
       //String destinationDirectory = "c:\temp\";
       
       System.out.println("unzip "+fileNPath);
       
       File sourceZipFile = new File(fileNPath);
       File unzipDestinationDirectory = new File(saveDir);

       // Open Zip file for reading
       ZipFile zipFile = new ZipFile(sourceZipFile, ZipFile.OPEN_READ);

       // Create an enumeration of the entries in the zip file
       Enumeration zipFileEntries = zipFile.entries();

       // Process each entry
       while (zipFileEntries.hasMoreElements())
       {
         // grab a zip file entry
         ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();

         String currentEntry = entry.getName();
         txtareaLog.append("Extracting: " + entry);
         System.out.println("Extracting: " + entry);

         File destFile = new File(unzipDestinationDirectory, currentEntry);

         // grab file's parent directory structure
         File destinationParent = destFile.getParentFile();

         // create the parent directory structure if needed
         destinationParent.mkdirs();

         // extract file if not a directory
         if (!entry.isDirectory())
         {
           BufferedInputStream is = new BufferedInputStream(zipFile.getInputStream(entry));
           int currentByte;
           // establish buffer for writing file
           byte data[] = new byte[bufferE];

           // write the current file to disk
           FileOutputStream fos = new FileOutputStream(destFile);
           BufferedOutputStream dest =
           new BufferedOutputStream(fos, bufferE);

           // read and write until last byte is encountered
           while ((currentByte = is.read(data, 0, bufferE)) != -1)
           {
             dest.write(data, 0, currentByte);
           }
           dest.flush();
           dest.close();
           is.close();
         }
       }
       zipFile.close();
       
       ret=true;
     }
     catch (IOException ioe)
     {
        System.out.println("error DialogBackup.unzip "+ioe.getMessage());//ioe.printStackTrace();
     }
   
 	return ret;
 }*/
 
 
 
 /*String createDBComamnd = "mysqladmin -h "+ip+" -u "+user+" -p"+pass+" create "+database;
String restoreCommand = "mysql -h "+ip+" -u "+user+" --password="+pass+" --database="+ database +" < "+path ;
System.out.println(restoreCommand+"\n"); //output---> mysql -h localhost -u root --password=a lancelicensing < E:\Users\lancelicensing.sql
System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
Runtime rt = Runtime.getRuntime();
Runtime rta = Runtime.getRuntime();
try{
Process proc = rt.exec(createDBComamnd);
Process child = rta.exec(restoreCommand);
int ch;
InputStream er = proc.getErrorStream();
InputStream err = child.getErrorStream();
InputStream in = child.getInputStream();

while((ch = in.read())!=-1){
System.out.write(ch);
}
while ((ch = er.read()) != -1) {
System.out.write(ch);
}
while ((ch = err.read()) != -1) {
System.out.write(ch);
}
}catch(Exception ex){
ex.printStackTrace();
}*/
 
 
 
 /*private void restoreDB(String toDatabase, String pathNFile)
 {

	   dirInstalled=txtDirInstalled.getText();
       saveDir= txtDirSave.getText();
 	   host=txtHost.getText();
       user=txtUser.getText();
       pass=txtPass.getText(); 

//-------------------------- read dbFile

 
 Runtime rt = Runtime.getRuntime();
File file=new File(pathNFile);
PrintStream ps;

try
{
Process child1;
Process child2;
//Process child3;
	
System.out.println("start restoring... please wait");
	
//---------------------  delete database	
/*child1= rt.exec(dirInstalled+"mysqladmin -h"+host+" -u"+user+" -p"+pass+" -f DROP "+toDatabase);	// f is for force with no confirmation
int ch1;

InputStream in1 = child1.getInputStream();


while ((ch1 = in1.read()) != -1)
{    
    //ps.write(ch1);
    txtareaLog.append(""+ch1);
    System.out.write(ch1); //to view it by console
    
}

InputStream err1 = child1.getErrorStream();

while ((ch1 = err1.read()) != -1)
{
    System.out.write(ch1);
}*/

// ----------------------  create database
/*child2=rt.exec(dirInstalled+"mysqladmin -h"+host+" -u"+user+" -p"+pass+" CREATE "+toDatabase);	
int ch2;
InputStream in2 = child2.getInputStream();
while ((ch2 = in2.read()) != -1)
{
    //ps.write(ch1);
    txtareaLog.append(""+ch2);
    System.out.write(ch2); //to view it by console   
}

InputStream err2 = child2.getErrorStream();
while ((ch2 = err2.read()) != -1)
{
    System.out.write(ch2);
}*/

// ------------------------  restore database
/*Process child3 = rt.exec(dirInstalled+"mysqlimport -h"+host+" -u"+user+" -p"+pass+" "+toDatabase+" "+pathNFile); //+" < "+saveFile;
System.out.println("DialogBackup.restoreDB "+dirInstalled+"mysqlimport -h"+host+" -u"+user+" -p"+pass+" "+toDatabase+" "+pathNFile);
int ch3;
InputStream in3 = child3.getInputStream();
while ((ch3 = in3.read()) != -1)
{
    //ps.write(ch1);
    txtareaLog.append(""+ch3);
    System.out.write(ch3); //to view it by console   
}

InputStream err3 = child3.getErrorStream();
while ((ch3 = err3.read()) != -1)
{
	//JOptionPane.showMessageDialog(null, err3.read());
    System.out.write(ch3);
}

in3.close();
//deleteFile(pathNFile);

}
catch(IOException ioe)
{
	JOptionPane.showMessageDialog(null,ioe.getMessage());
    ioe.printStackTrace();
}
catch(Exception exc)
{
	JOptionPane.showMessageDialog(null,exc.getMessage());
   exc.printStackTrace();
}
 
 
 	
 }*/
 
 /*
 private boolean NOTmysqldumpDb(String database)
 {
 boolean ret = false;
 	   dirInstalled=txtDirInstalled.getText();
       saveDir= txtDirSave.getText();
 	   host=txtHost.getText();
       user=txtUser.getText();
       pass=txtPass.getText();
 	
Runtime rt = Runtime.getRuntime();
File file=new File(saveDir+dbFile);//filePre+database+today+".txt");
PrintStream ps;

try
{       
 String command = dirInstalled+"mysqldump -h"+host+" -u"+user+" -p"+pass+" --routines --triggers "+database;
System.out.println("DialogBackUp.dumpDb command "+command);
Process child = rt.exec(command);
ps=new PrintStream(file);





InputStream in = child.getInputStream();
int ch;

while ((ch = in.read()) != -1)
{
    ps.write(ch);
    //System.out.write(ch); //to view it by console
}


ret= true;

InputStream err = child.getErrorStream();
while ((ch = err.read()) != -1)
{
System.out.write(ch);
//txtareaLog.append(System.out.write(ch)+" \n");
ret =false;
}


System.out.println("mysql backup saved in:"+saveDir+dbFile);//filePre+database+today+".txt");


}catch(Exception exc)
{
  txtareaLog.append(exc.getMessage()+" \n");
  exc.printStackTrace();
  ret =false;
}

return ret;	
 }*/


	/*private void NOTloadConfigFromFile()
	{
		String strDBengine="";
	  try
	  {
     	Properties props = new Properties(); //properties to get from file
     String curDir ="";

      
        FileInputStream in = new FileInputStream(VariablesGlobal.globalDirConfiguration+systemDirectorySymbol+FILE_DB_CONFIG);
        props.load(in);
        url = props.getProperty("jdbc.url");
        driver = props.getProperty("jdbc.drivers");
        user = props.getProperty("jdbc.username");
        pass = props.getProperty("jdbc.password");
        dbPath=props.getProperty("derby.system.home");
        dirInstalled=props.getProperty("backup.dbenginedir");
        saveDir=props.getProperty("backup.filesdir");
        dbEngine=Integer.valueOf(props.getProperty("dbengine"));
        
    
        if(dbEngine==DBENGINE_MARIADB || dbEngine==DBENGINE_MYSQL)
        {
           host = url.substring(url.indexOf("//")+2,url.indexOf(":",url.indexOf("//")));
        }
       //host
       //filePre=filePreIn;
       //dirInstalled=dirInstalledIn;
       
       
  
          //txtDbPath.setText(dbPath);
          //txtDbDriver.setText(dbDriver);                 
          //txtDbUrl.setText(dbUrl);
          txtUser.setText(user);
          txtPass.setText(pass);
	      txtDirInstalled.setText(dirInstalled);
	      txtDirSave.setText(saveDir);  
	      txtHost.setText(host);
  
        
        GregorianCalendar cal = new GregorianCalendar();
 	   String [] allowedPatterns = new String[1];
 	   utilsDate = new UtilsDate();
 	   utilsDate.readFromFileDateFormats();
 	   allowedPatterns[0]=utilsDate.getDateFormatEditing();         
        
        //int dayInt = cal.get(Calendar.DAY_OF_WEEK);
        int d = cal.get(Calendar.DAY_OF_MONTH);
        int m = cal.get(Calendar.MONTH)+1;  // plus 1. dont know why
        int y = cal.get(Calendar.YEAR);
        //String day = dayArray[dayInt-1];//dayArray starts from 0
        int ampm =  cal.get(Calendar.AM_PM );
        String strAmpm="";
        if (ampm==1)
        {
        	strAmpm="pm";
        }
        else
        {
        	strAmpm="am";
        }
        int hour =  cal.get(Calendar.HOUR );
        int min =  cal.get(Calendar.MINUTE);
        
        //today = "("+y+"-"+m+"-"+d+"_"+strAmpm+"_"+hour+"-"+min+")";
        	   
       //today=utilsDate.reformatDateString()
       today=utilsDate.reformatDateString(d+"-"+m+"-"+y+"_"+hour+"-"+min,allowedPatterns,"yyyy-MM-dd")+"_"+strAmpm+"_"+hour+"-"+min;


       if(dbEngine==DBENGINE_MARIADB)
       {
       	  strDBengine="MariaDB";
       	  dbFile="mariadb.txt";
       	  txtHost.setVisible(true);
       	  lblHost.setVisible(true);
          lblDirInstalled.setVisible(true);
       	  txtBtnDirInstalled.setVisible(true);   
       	  	txtRestoreToDB.setVisible(true); 
       	  lblRetoreToDB.setVisible(true); 
       }       
       else if(dbEngine==DBENGINE_MYSQL)
       {
       	  strDBengine="MySQL";
       	  dbFile="mysqldb.txt";
       	  txtHost.setVisible(true);
       	  lblHost.setVisible(true);
          lblDirInstalled.setVisible(true);
       	  txtBtnDirInstalled.setVisible(true);   
       	  	txtRestoreToDB.setVisible(true); 
       	  lblRetoreToDB.setVisible(true); 
       }
       else if(dbEngine==DBENGINE_H2EMBEDDED)
       {
       	strDBengine="H2embedded";
       	dbFile="h2db.txt";
       	  txtHost.setVisible(false);
       	  lblHost.setVisible(false);
       	  lblDirInstalled.setVisible(false);
       	  txtBtnDirInstalled.setVisible(false);   
       	  	txtRestoreToDB.setVisible(false);  
       	  lblRetoreToDB.setVisible(false); 
       }
       else if(dbEngine==DBENGINE_H2SERVER)
       {
       	strDBengine="H2server";
       	dbFile="h2db.txt";
       	  txtHost.setVisible(true);
       	  lblHost.setVisible(true);
       	  lblDirInstalled.setVisible(false);
       	  txtBtnDirInstalled.setVisible(false); 
       	  	txtRestoreToDB.setVisible(true); 
       	  lblRetoreToDB.setVisible(true);         	  
       }    
       else if(dbEngine==DBENGINE_POSTGRESQL)
       {
       	strDBengine="PostgreSQL";
       	dbFile="postgresdb.txt";
       	  txtHost.setVisible(true);
       	  lblHost.setVisible(true);
       	  lblDirInstalled.setVisible(false);
       	  txtBtnDirInstalled.setVisible(false);  
       	  	txtRestoreToDB.setVisible(true); 
       	  lblRetoreToDB.setVisible(true);        	  
       }          
       else
       {
       	 strDBengine="unknown";
       	 dbFile="unknowndb.txt";
       	  txtHost.setVisible(true);
       	  lblHost.setVisible(true);
          lblDirInstalled.setVisible(true);
       	  txtBtnDirInstalled.setVisible(true);  
       	  	txtRestoreToDB.setVisible(true);  
       	  lblRetoreToDB.setVisible(true);  
       	  System.out.println("DialogBackUp.loadConfigFromFile unknown db engine "+dbEngine);       	
       }

        
      }
      catch (IOException ex)
      {
          System.err.println("DialogConfiguration.IOException:Cannot find text file:"+FILE_CONFIG);
          //System.err.println(ex);
      }

 	   filePre="backup-"+strDBengine+"-";
 	   commentsFile="comments.txt";
 	   
      
      //	retrieveDatabases();
      
   }*/

  /* private boolean MNOTh2DumpDb(String database)
   {
   	  boolean ret = false;
   	  // org.h2.tools.Script.execute(String url, String user, String password, String fileName, String charsetName, boolean continueOnError) 

   	  try
   	  {
   	       org.h2.tools.Script.process(this.getConnectionToDB(),user,pass,saveDir+systemDirectorySymbol+dbFile);	// process  with 2 empty strings as last parameters
   	       ret = true;
   	  }
   	  catch (SQLException e)
   	  {
   	  	ret = false;
   	  	txtareaLog.append("\n"+e.getMessage());
   	  	System.out.println("DialogBackUp.h2DumpDb dumping failed "+e.getMessage());
   	  }
   	  
   	  // -url jdbc:h2:file:C:\mydocs\!projects\DBTool\h2_db\farmersvat -user sa -password sa -script C:\mydocs\!projects\DBTool\db\db.sql 
   	  
   	  if(ret)
   	  {
   	  	 System.out.println("h2 back up saved in:"+saveDir+dbFile);//filePre+database+today+".txt");
   	  }
   	  
   	  return ret;
   }*/
   
  /* private boolean MNOTh2RestoreDb(String dir, String dbFile)
   {
   	 boolean ret = false;
   	  // org.h2.tools.RunScript.execute(String url, String user, String password, String fileName, String charsetName, boolean continueOnError) 
     
     //txtRestoreToDB.getText();
     
   	  try
   	  {
              //Charset chrset = new StandardCharsets();
   	       org.h2.tools.RunScript.execute(url,user,pass,saveDir+systemDirectorySymbol+dbFile,StandardCharsets.UTF_8, false);	
   	       ret = true;
   	  }
   	  catch (SQLException e)
   	  {
   	  	ret = false;
   	  	txtareaLog.append("\n"+e.getMessage());
   	  	System.out.println("DialogBackUp.h2RestoreDb restore failed "+e.getMessage());
   	  }   	  
   	  return ret;
   }*/

    private void NOTretrieveDatabases()
    {
    	

    ArrayList listDbs = new ArrayList();
    ArrayList listDbTables = new ArrayList();
    ArrayList listDbProc = new ArrayList();
    
    try
    {
   	
    Connection conn = getConnectionToDB();
    //System.out.println("Got Connection.");
    Statement st = conn.createStatement();    	
    ResultSet catalogs = null;
    DatabaseMetaData meta = conn.getMetaData();
    catalogs = meta.getCatalogs();
        /*Statement stmnt = conn.createStatement();        
        DatabaseMetaData dbm = conn.getMetaData();
        String[] types = {"TABLE"};
        ResultSet rs = dbm.getTables(null,null,"%",types);  */  
     
     while (catalogs.next())
     {
     	
       String catalog = catalogs.getString(1);  //"TABLE_CATALOG"
       listDbs.add(catalog);
       //System.out.println("catalog: "+catalog);

        int countTables=0;	
        int countProc=0;	
        Connection con = getConnectionToDB();
        //Statement stmnt = conn.createStatement();        
        DatabaseMetaData dbm = con.getMetaData();
        String[] types = {"TABLE"};
        ResultSet rs = dbm.getTables(catalog,null,"%",types);
       
        while (rs.next()) // for each table
        {
        	countTables++;
        	//System.out.println("DialogSetupDB.retrieveDatabases "+catalog+" "+rs.getString("TABLE_NAME")+" "+countTables);
         //String table = rs.getString("TABLE_NAME");
         //textAreaMetaData.append(countTables+". ["+table+"]\n");
         //String primKs =
         //rsPK = dbm.getPrimaryKeys( null, null, table);
         //System.out.println("DialogSetupDB.getTables PrimKs count "+table+"->"+databaseTableMeta.getNumberOfPrimKeys());
         //txtareaLog.append("PrimKs count "+table+"->"+dbm+"\n") ;       
        }
        listDbTables.add(countTables);
        
        ResultSet rsProc = dbm.getProcedures(catalog,null,null);
        
             /*getProcedures(String catalog,
                        String schemaPattern,
                        String procedureNamePattern)        */
        
        while (rsProc.next()) // for each table
        {
        	countProc++;
        	//System.out.println("DialogSetupDB.retrieveDatabases "+catalog+" "+rs.getString("TABLE_NAME")+" "+countTables);
         //String table = rs.getString("TABLE_NAME");
         //textAreaMetaData.append(countTables+". ["+table+"]\n");
         //String primKs =
         //rsPK = dbm.getPrimaryKeys( null, null, table);
         //System.out.println("DialogSetupDB.getTables PrimKs count "+table+"->"+databaseTableMeta.getNumberOfPrimKeys());
         //txtareaLog.append("PrimKs count "+table+"->"+dbm+"\n") ;       
        }        
        listDbProc.add(countProc);

        
        con.close();
        
     }
        
     }
     catch ( SQLException sqlex)
     {
     	utilsGui.showMessageError(this,"DialogBackup.retrieveDatabases err code "+sqlex.getErrorCode()+" \n"+sqlex.getMessage()); 	
     }
    	
    	//String[] dbs = new String[listDbs.size()];
    	Vector data = new Vector();
    
    	
    	for(int d=0;d<listDbs.size();d++)
    	{
    		Object[] rec = new Object[3];
    		//System.out.println("DialogBackUp. "+d+" "+listDbs.get(d));
    		//rec[0] = false;
    		rec[0] = listDbs.get(d).toString();
    		rec[1]=  listDbTables.get(d).toString();
    		rec[2]= listDbProc.get(d).toString();
    		data.add(d,rec);
       	}
    	
    	ArrayList listHeader = new ArrayList();
    	listHeader.add("βάσεις");
    	listHeader.add("πίνακες");
    	listHeader.add("procedures");
    	
    	//String[] header = {"βάσεις","πίνακες","procedures"};
    	tableModel.setDataVector( data,listHeader);
    	//tableModel.setData(header,data);//.setListData(dbs);
    	//table.setRowSelectionInterval(0,0);
    	
        UtilsTable utilsTable=new UtilsTable();


        int totalWidthOfColumns =20;
        for (int c=0; c<table.getColumnCount(); c++)
        {   // table,column, margin
            utilsTable.packColumn(table, c, 2,true,false,null);
        }

        
        
        
        //System.out.println("----"+totalWidthOfColumns);
        //tableScrollerDatabases.setPreferredSize(utilsTable.setTableScrollPaneSize(table,totalWidthOfColumns,280));	
    }

    private Connection getConnectionToDB()throws SQLException
    {
    	
        Properties p = new Properties(); // properties to be used for connection
   	   // String url="";
   	   
   	   //txtRestoreToDB.getText(),txtHost.getText()),dir+dbFile)

     //System.out.println(driver+" "+txtUser.getText()+" "+txtPass.getText()+" ");

       //String driver = txtDbDriver.getText();
        if (driver != null)
           System.setProperty("jdbc.drivers", driver);


        
        if(dbPath !=null )  //if there is in text file
        {           System.setProperty("derby.system.home", dbPath);  } // load it

        p.put("user", txtUser.getText());// username);
        p.put("password", txtPass.getText());//password);
        p.put("useUnicode", "true");
        p.put("characterEncoding", "utf8");//iso-8859-7
      
       return DriverManager.getConnection(url, p);
	
    }

    
    private void retrieveBackUpFiles(String strDirOfBackupFiles)
    {
    	
    
    	
    File dir = new File(strDirOfBackupFiles);	// VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+DIR_DATABACKUP+systemDirectorySymbol
    // It is also possible to filter the list of returned files.
    // This example does not return any files that start with `.'.
    FilenameFilter filter = new FilenameFilter()
    {
        public boolean accept(File dir, String name)
        {  boolean ret = false;
        	if  (/*name.startsWith("backup") &&*/ name.endsWith(".zip"))
        	{
        		ret = true;
        	}
        	
        	return ret;
        }
    };
    //children = dir.list(filter);
    
    /*tableModel= new TableModelReadOnly();
    tblBackups.setModel(tableModel);
    tblBackups.setSelectedIndex(0);*/
    
    filesRetrieved =dir.list(filter);
    String[] linesFromFiles = new String[filesRetrieved.length];
    
    for (int fl = 0; fl<filesRetrieved.length;fl++)
    {
    	linesFromFiles[fl]=filesRetrieved[fl]+" ("+(new File(strDirOfBackupFiles+filesRetrieved[fl]).length()/1000)+" kbytes)";
    	//System.out.println(" "+txtDirSave.getText()+systemDirectorySymbol+filesRetrieved[fl]);
    }
    
    listBackups.setListData(linesFromFiles);
    listBackups.setSelectedIndex(0);
    } 

    private void showCommentsForRestoreFile(String pathNFile)
    {
    	// http://www.java2s.com/Code/Java/File-Input-Output/ReadingtheContentsofaZIPFile.htm
    txtareaRestoreComments.setText("");
    try {
      ZipFile zf = new ZipFile(pathNFile);
      Enumeration entries = zf.entries();

      BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
      while (entries.hasMoreElements())
      {
        ZipEntry ze = (ZipEntry) entries.nextElement();
        //System.out.println("Read " + ze.getName() + "?");
        //String inputLine = input.readLine();
        if (ze.getName().equalsIgnoreCase(commentsFile))
        {                //inputLine.equalsIgnoreCase("yes")) {
          long size = ze.getSize();
          if (size > 0) {
            //System.out.println("Length is " + size);
            BufferedReader br = new BufferedReader( new InputStreamReader(zf.getInputStream(ze),"utf-8"));
            String line;
            
            while ((line = br.readLine()) != null)
            {
                
              
              if(line!= null && line.length()>1)
              {
              txtareaRestoreComments.append(line.substring(line.indexOf("<comments>")+10, line.indexOf("</comments>")));
              bckFileDatabase = line.substring(line.indexOf("<database>")+10, line.indexOf("</database>"));
              bckFileDatabaseLeadVersion = line.substring(line.indexOf("<versionlead>")+13, line.indexOf("</versionlead>"));
              bckFileDatabaseSubVersion = line.substring(line.indexOf("<versionsub>")+12, line.indexOf("</versionsub>"));
              lblRestoreTo.setText("   βάση:"+bckFileDatabase+"  έκδοση βάσης:"+bckFileDatabaseLeadVersion+"."+bckFileDatabaseSubVersion);
              }
              else
              {
                txtareaRestoreComments.append("");
                bckFileDatabase = "";  bckFileDatabaseLeadVersion=""; bckFileDatabaseSubVersion=""; 
                lblRestoreTo.setText("");
              }
              
              
              //lblRestoreTo.setText("   βάση:"+bckFileDatabase+"  έκδοση βάσης:"+bckFileDatabaseLeadVersion+"."+bckFileDatabaseSubVersion);
              
              //System.out.println(line);
            }
            br.close();
          }
        }
      }
    }
    catch (IOException e)
    {
        System.out.println("DialogBackup.showCommentsForRestoreFile "+e.getMessage());
      e.printStackTrace();
    }
    catch (java.lang.StringIndexOutOfBoundsException eiob)
    {
        utilsGui.showMessageError(this,"Το αρχείο δεν ειναι σωστό, περιέχει λάθη.");
       System.out.println("DialogBackup.showCommentsForRestoreFile  eiob "+ eiob.getMessage());
    }
    	
    	
   }
   
   //exists in DilogBackup and in PanelReportFormDesign
   private void deleteFile(String file) 
   {
   	 File f = new File(file);
   	    boolean success = f.delete();
       if (!success) 
       {
       	  txtareaLog.append("\n error: file '"+file+"' not deleted.");
          System.out.println(" error: file '"+file+"' not deleted.");
       }
       else
       {
       	   //txtareaLog.append("\nfile '"+file+"' deleted.");
    	   //System.out.println("file '"+file+"' deleted.");
       }             
   }
    
class TabListener implements ChangeListener
{

    public void stateChanged(ChangeEvent e)
    {

        JTabbedPane tp = (JTabbedPane)e.getSource();
        
        if(tp.getSelectedIndex()==1) // restore
        {
  //      	retrieveBackUpFiles();
        	
        }
    }

}


 private void retrieveTodayString()
 {
     
     
        GregorianCalendar cal = new GregorianCalendar();
 	   String [] allowedPatterns = new String[1];
 	   utilsDate = new UtilsDate();
 	   utilsDate.readFromFileDateFormats();
 	   allowedPatterns[0]=utilsDate.getDateFormatEditing();         
        
        //int dayInt = cal.get(Calendar.DAY_OF_WEEK);
        int d = cal.get(Calendar.DAY_OF_MONTH);
        int m = cal.get(Calendar.MONTH)+1;  // plus 1. dont know why
        int y = cal.get(Calendar.YEAR);
        //String day = dayArray[dayInt-1];//dayArray starts from 0
        int ampm =  cal.get(Calendar.AM_PM );
        String strAmpm="";
        if (ampm==1)
        {
        	strAmpm="pm";
        }
        else
        {
        	strAmpm="am";
        }
        int hour =  cal.get(Calendar.HOUR );
        int min =  cal.get(Calendar.MINUTE);     
        int sec =  cal.get(Calendar.SECOND); 
     
     
      today=utilsDate.reformatDateString(d+"-"+m+"-"+y+"_"+hour+"-"+min+"-"+sec,allowedPatterns,"yyyy-MM-dd")+"_"+strAmpm+"_"+hour+"-"+min+"-"+sec;
   
 }

 
 private String getDatabaseName()
 {
     
                  String strDatabase = ""   ;
          
       try
      {        
           strDatabase =db.getConnection().getCatalog();
      }
      catch ( SQLException sqlex)
      {
                  System.out.println("error: PanelBackup.getDatabaseName() sqlex:"+sqlex.getMessage());
                  sqlex.printStackTrace();
      }
     
     
     return strDatabase;
     
 }
   public void backup(String strSaveDir)
   {
      

       System.out.println("DialogBackUp.backup    strSaveDir:"+strSaveDir);
        txtareaLog.append("\nΤο back-up ξεκίνησε.");	
      final WindowWait wwb = new WindowWait("παρακαλω περιμένετε, backup / αποθήκευση",WINDOW_LOCATION_CENTER,ICO_RELOAD16, ICO_RELOADB16);
       // wwb.setComment("-");
         
   		          // thread for show window wait
	        thread = new Thread(new Runnable() {
	          public void run()
	          {
           	       wwb.showWindow();
                       wwb.animate();
                       wwb.setComment("εκκίνηση");
	               thread = null;
	          }
	          });
              thread.start();   	  	
     
              // thread for backup
             thread = new Thread(new Runnable() {
	          public void run()
	          {        
       retrieveTodayString();
       
       
       
       
       
       
      
      try
      {
        writer = new PrintWriter(strSaveDir+fileSql, "UTF-8");
        
         writer.println("\n/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;");
         writer.println("\n/*!40101 SET NAMES utf8 */;");
         writer.println("\n/*!50503 SET NAMES utf8mb4 */;");
         writer.println("\n/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;");
         writer.println("\n/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;");        
        
 
//      setQueryCreateObjectExceptTable("SHOW CREATE DATABASE "+strDatabase);
//      writer.println("\n\nUSE "+db.getConnection().getCatalog()+";");
      writer.println("\n\nSET @@foreign_key_checks = 0;");

      
      String[] tableNames = db.retrieveDBObjects("TABLE","TABLE_NAME");
      //txtareaLog.append("\n tables:"+tableNames.length);   
       //setQuery("SHOW CREATE DATABASE `servicesales`");
       int tablelen = tableNames.length;
       for(int t = 0;t<tablelen;t++)
       {
           wwb.setComment("πίνακας:"+t+"     "+(t*100/tablelen)+"%");
            writer.println("\n\nDROP TABLE IF EXISTS `"+tableNames[t]+"`;");
           setQueryCreateTable("SHOW CREATE TABLE "+tableNames[t]);
         //txtareaLog.append("\nSELECT * FROM "+tableNames[t]);	
           setQueryCreateData("SELECT * FROM "+tableNames[t], tableNames[t]);
       }
      // txtareaLog.setText(txtareaLog.getText()+"\n tables:"+(tableNames.length));
      
       
       String[] viewNames = db.retrieveDBObjects("VIEW","TABLE_NAME");
       for(int t = 0;t<viewNames.length;t++)
       {
           wwb.setComment("view:"+t+" από:"+viewNames.length);
           writer.println("\n");
           setQueryCreateObjectExceptTable("SHOW CREATE VIEW "+viewNames[t]);   
       }       
       // txtareaLog.setText(txtareaLog.getText()+"\n views:"+(viewNames.length));
      

       String[] nameFunctions = db.retrieveDBObjects("FUNCTION","TABLE_NAME");
       for(int t = 0;t<nameFunctions.length;t++)
       {
           wwb.setComment("functions:"+t+" από:"+nameFunctions.length);
           writer.println("\n");
           setQueryCreateObjectExceptTable("SHOW CREATE FUNCTION "+nameFunctions[t]);   
       }       
       // txtareaLog.setText(txtareaLog.getText()+"\n functions:"+(viewFunctions.length));
 
       
        String[] viewProcedures = db.retrieveDBObjects("PROCEDURE","TABLE_NAME"); // PROCEDURE_NAME
       for(int t = 0;t<viewProcedures.length;t++)
       {
            wwb.setComment("procedures:"+t+" από:"+viewProcedures.length);
           writer.println("\n");
           setQueryCreateObjectExceptTable("SHOW CREATE PROCEDURE "+viewProcedures[t]);   
       }       
        //txtareaLog.setText(txtareaLog.getText()+"\n procedures:"+(viewProcedures.length));
    //txtareaLog.setText(txtareaLog.getText()+"\n close");         
        writer.println("\n\nSET @@foreign_key_checks = 1;");
        
         writer.println("\n/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;");
         writer.println("\n/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;");
         writer.println("\n/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;");

           writer.close();
           wwb.setComment("συμπίεση");
           String strDatabase = getDatabaseName();
         	            if(zip(strDatabase,strSaveDir))
	                    {
	                        txtareaLog.append("\nΤο back-up της "+strDatabase+" ολοκληρώθηκε επιτυχώς.");	
	                    }
	                    else
	                    {
	                    	txtareaLog.append("\nΤο back-up της "+strDatabase+" δεν ολοκληρώθηκε.");	
	                    }  
                wwb.close();
       thread = null;   
       
       db.releaseConnectionRs(); 
       
      // db.setTransactionAutoCommit(false);
      // db.setTransactionAutoCommit(true);

      }
      catch(FileNotFoundException fnfe)  // java.io.FileNotFoundException
      {
          System.out.println("error: PanelBackup.backup() FileNotFoundException:"+fnfe.getMessage());
          wwb.close();
          thread = null;   
          
          //txtareaLog.setText(txtareaLog.getText()+"\n"+fnfe.getMessage());
      }
      catch(IOException  e)
      {
          wwb.close();
         thread = null;   
          System.out.println("error: PanelBackup.backup() e:"+e.getMessage());
          
          e.printStackTrace();
      }  
                          
                  }   
                   });
              
                   thread.start();               
              
                  
     /*  wwb.close();
         thread1 = null;
         thread2 = null;*/

       
   }    

   private boolean checkDbNameToRestore(boolean isFromDialogMain)
   {
       boolean doContinue = false;
        String strDatabase = getDatabaseName();
       if(bckFileDatabase.equalsIgnoreCase(strDatabase) || isFromDialogMain)
       {
           doContinue = true;
       } 
       else
       {
                   int YES = 0;
                     int NO = 1;
                    int CANCEL = 3;
              int answer =  utilsGui.showConfirmYesOrNo(this, "Η ονομασία της βάσης του backup ("+bckFileDatabase+") είναι διαφορετική με αυτή στην οποία είστε συνδεδεμένοι("+strDatabase+")."
                      + "Θέλετε να συνεχίσετε;");
              if(answer ==YES)
              {
                  doContinue = true;
              }
              else
              {
                  doContinue = false;
              }            
       
       }
       
       return doContinue;
   }
  
   
   private boolean checkDbVersionToRestore()//boolean isFromDialogMain)
   {
        boolean doContinue = false;
         
                   Database db = new Database();
            db.retrieveDBDataFromQuery("SELECT dbleadversion, dbsubversion FROM dbsystem WHERE dbsystemid = 1","DialogBackUp.restore");
            ResultSet rs = db.getRS();
            String strDbLeadVer = "1";
             String strDbSubVer = STR_VERSIONSUB_START;//"0.2510";
            try
            {
                rs.first();
               strDbLeadVer = rs.getString("dbleadversion");
               strDbSubVer= rs.getString("dbsubversion");
            }
            catch (SQLException sqle)
            {
                      System.out.println("DialogBackUp.restore "+sqle.getMessage());  
            }
            finally
            {
                db.releaseConnectionRs();
            }       
       
      //      if(isFromDialogMain)
      //      {
      //          bckFileDatabaseLeadVersion = VariablesGlobal.appLeadVersion;
       //         bckFileDatabaseSubVersion = VariablesGlobal.appSubVersion;
       //     }
            
       String bckFileDatabaseVersion = bckFileDatabaseLeadVersion+"."+bckFileDatabaseSubVersion;
       String strDbVer = strDbLeadVer+"."+strDbSubVer;
       if(bckFileDatabaseVersion.equalsIgnoreCase(strDbVer))
       {
            doContinue = true;
       }
       else 
       {
           if(Double.parseDouble(bckFileDatabaseSubVersion)<Double.parseDouble(strDbSubVer))
           {
               // bckfile is smaller than the db
               
                   int YES = 0;
                     int NO = 1;
                    int CANCEL = 3;
              int answer = utilsGui.showConfirmYesOrNo(this, "Η έκδοση του backup ("+bckFileDatabaseSubVersion+") έιναι παλαιότερη από την έκδοση της βάσης ("+strDbSubVer+")."
                      + "Δεν συνήσταται επαναφορά. Θέλετε να συνεχίσετε;");
              if(answer ==YES)
              {
                  doContinue = true;
              }
              else
              {
                  doContinue = false;
              }
              
              
              
           }
           else if(Double.parseDouble(bckFileDatabaseSubVersion)>Double.parseDouble(strDbSubVer))
           {
               // bckfile greater than the db, continue
                   int YES = 0;
                     int NO = 1;
                    int CANCEL = 3;
              int answer =  utilsGui.showConfirmYesOrNo(this, "Η έκδοση του backup ("+bckFileDatabaseSubVersion+") έιναι νεότερη από την έκδοση της βάσης ("+strDbSubVer+")."
                      + "Θα ακολουθήσει επαναφορά. Θέλετε να συνεχίσετε;");
              if(answer ==YES)
              {
                  doContinue = true;
              }
              else
              {
                  doContinue = false;
              }              
              
           }
           else
           {
               // // bckfile equal with the db, continue
               doContinue =true;
           }
           
       } 
        
        
        
        return doContinue;
   }
   
   public boolean restore(String pathNFile,String fileSql,String strVersion,String strTitleOfWindowWait, boolean isFromDialogMain)throws IOException
   { //  if   isFromDialogMain: means on init. is needed to wait when an update for db is finished.
       

       boolean checkdbname = true;
       boolean checkdbversion = true;
       
       if(!isFromDialogMain)
       {
       checkdbname = checkDbNameToRestore(isFromDialogMain);
       checkdbversion = checkDbVersionToRestore();
       }
       
        if( checkdbname && checkdbversion )
        {

       
          final	WindowWait wwr = new WindowWait(strTitleOfWindowWait,WINDOW_LOCATION_CENTER,ICO_RELOAD16, ICO_RELOADB16);
         
                 wwr.animate();
                 

   		          // thread for show window wait
	        thread1 = new Thread(new Runnable() {
	          public void run()
	          {
	          
           	       wwr.showWindow();
	               wwr.setComment("εκκίνηση");
	               thread1 = null;
	          }
	          });
              thread1.start(); 	  	
     
              // thread for backup
             thread2 = new Thread(new Runnable() {
	          public void run()
	          {   	  	

        Connection con = null;
       
                  // strDatabase =db.getConnection().getCatalog();                  
      try
      {
          
                txtareaLog.append("\nΤο restore  ξεκίνησε.");        
                            con = DbConnection.getConnectionFromFile();
                
                           con.setAutoCommit(false); 
      }
      catch ( SQLException sqlex)
      {
          if(isFromDialogMain)
          {
              
                      System.out.println("error: PanelBackup.restore() sqlex:"+sqlex.getErrorCode()+"  "+sqlex.getMessage());
                      
                      // sqlex.printStackTrace(); 
          }
          else
          {
            txtareaLog.append("\nΤο restore  δεν ολοκληρώθηκε.");	
//          db.transactionRollback();
                  System.out.println("error: PanelBackup.restore() sqlex:"+sqlex.getMessage());
                  sqlex.printStackTrace();             
              
          }

      //}
      //finally
      //{
       //   db.releaseConnectionRs();
       //   db.releaseConnectionRsmd();
         // db.transactionClose();
      }
            try
            {
     System.out.println("PanelBackup.restore()  pathNFile:"+pathNFile+"  fileSql:"+fileSql);
      ZipFile zf = new ZipFile(pathNFile);
      Enumeration entries = zf.entries();

      //BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
      while (entries.hasMoreElements())
      {
        ZipEntry ze = (ZipEntry) entries.nextElement();
        //System.out.println("Read " + ze.getName() + "?");
        //String inputLine = input.readLine();
        if (ze.getName().equalsIgnoreCase(fileSql))
        {                //inputLine.equalsIgnoreCase("yes")) {
          long size = ze.getSize();
           StringBuffer command = null;

          if (size > 0)
          {
            //System.out.println("Length is " + size);
            BufferedReader br = null;

            BufferedReader brl = new BufferedReader(new InputStreamReader(zf.getInputStream(ze),"utf-8"));
            String line = null;
            int countTotal =1;
            while ((line = brl.readLine()) != null )//&& !line.trim().equalsIgnoreCase(""))
            {
                countTotal++;
            }
       
            br = new BufferedReader(new InputStreamReader(zf.getInputStream(ze),"utf-8"));
          int countQuery = 1;
          //  LineNumberReader lineReader = new LineNumberReader(reader);
           boolean doNotExecuteOnlyAppend = true;
            while ((line = br.readLine()) != null )//&& !line.trim().equalsIgnoreCase(""))
            {
                if(isFromDialogMain)
                {
                  wwr.setComment("έκδοση:"+strVersion+"   εντολή:"+countQuery+"  "+(countQuery*100/countTotal)+"%");
                }
                else
                {
                    wwr.setComment("εντολή:"+countQuery +"      "+(countQuery*100/countTotal)+"%");
                }
                
                if (command == null)
                {
                    command = new StringBuffer();
                }
                String trimmedLine = line.trim();
                //String trimmedLine = line.trim();
                if (trimmedLine.startsWith("--") || trimmedLine.startsWith("//") || trimmedLine.startsWith("#") ||  trimmedLine.startsWith("/*"))
                {
                    //out.flush();
                }
                else if (trimmedLine.endsWith(delimiter) )
                {

                   if(command.toString().indexOf(delimiter)>1)// or not -1
                   {
                    command = new StringBuffer(trimmedLine.substring(0,trimmedLine.length()));
                    //command.insert(0, trimmedLine.substring(0,trimmedLine.indexOf(delimiter)+1));
                    //System.out.println(" command A  "+trimmedLine.indexOf(delimiter)+"  "+trimmedLine.length()+"  :"+command);
                     doNotExecuteOnlyAppend = false;
                    }
                   else
                    {
                    command.append(trimmedLine.substring(0,trimmedLine.length()));//trimmedLine.indexOf(delimiter)+1));
                    doNotExecuteOnlyAppend = false;
                    //System.out.println(" command B  "+trimmedLine.indexOf(delimiter)+"  "+trimmedLine.length()+"  :"+command);
                    }
                }
                else if(!trimmedLine.endsWith(delimiter))
               // else if (!fullLineDelimiter  && trimmedLine.endsWith(delimiter)  || fullLineDelimiter      && trimmedLine.equals(delimiter))
                {
                    if(command.toString().indexOf(delimiter)>1 && !trimmedLine.endsWith("//") && !trimmedLine.endsWith("\">") && !trimmedLine.startsWith("<td>") && !trimmedLine.startsWith("<TD>") &&  !trimmedLine.startsWith("<p>") &&  !trimmedLine.startsWith("<P>") &&  !trimmedLine.startsWith("<td") &&  !trimmedLine.startsWith("</div>"))
                    {
                     command = new StringBuffer(trimmedLine.substring(0,trimmedLine.indexOf(delimiter)+1));
                     doNotExecuteOnlyAppend = false;
                    //command.insert(0, trimmedLine.substring(0,trimmedLine.indexOf(delimiter)+1));
                     //System.out.println(" command C:"+command);                        
                    }
                    else
                    {
                   command.append(trimmedLine.substring(0, trimmedLine.length()));    
                   doNotExecuteOnlyAppend=true;
                 // System.out.println(" command   doNotExecuteOnlyAppend:"+doNotExecuteOnlyAppend+"  D:"+command);
                    }
                }
              //  try
             //  {
                   if(command.toString().endsWith(delimiter) && !doNotExecuteOnlyAppend)
                   {
                       
                       try
                       {
                    
                    //System.out.println("DialogBackUp  command :"+command);
                    restoreCommand(con, command);
                    wasRestoreSuccessful=true;
                  
                       } 
                       catch (SQLException sqle)
                       {
                         wasRestoreSuccessful=false;
                   	wwr.close();
                        
	                //thread = null;     
                       System.out.println("DialogBackup.restore  ERROR  restore "+sqle.getMessage()+"  command:"+command);
                       txtareaLog.append("\n restore error command: command:"+command);                   
     //                  utilsGui.showMessageError("DialogBackup.restore  ERROR  restoreCommand \n"+sqle.getMessage()+"     \nquery:"+command);
                   // System.out.println("DialogBackup.restore close  ERROR "+sqle.getMessage());
                   //  txtareaLog.append("\nΤο restore  error "+sqle.getMessage());
                     // break; // avoid because shows only one error
                      }                     
                   }

             countQuery++;
            }
                 br.close();
            }

           wwr.setComment("τερματισμός");
           
           try
           {
            //System.out.println("DialogBackup restore  commit.");
             con.commit();
             con.setAutoCommit(true); 
             con.close();
              } 
               catch (SQLException sqle)
               {
                   wasRestoreSuccessful=false;
                  wwr.close();
	           //thread = null;     
                   System.out.println("DialogBackup.restore  ERROR commit command:  "+sqle.getMessage());
                    txtareaLog.append("\n restore error command: command:"+command);                   
               }             
             System.out.println("DialogBackup Το restore  ολοκληρώθηκε."+fileSql);

             txtareaLog.append("\nΤο restore  ολοκληρώθηκε."+fileSql);	             
        
              //txtareaRestoreComments.append(line);
              //System.out.println(line);
         

        }  
        else
        {
            System.out.println("DialogBackup.restore    ze.getName():"+ze.getName()+"   fileSql:"+fileSql);
        }
      }
             }
            catch (IOException e)
            {
                wasRestoreSuccessful=false;
                System.out.println("DialogBackup.restore   IOException   fileSql:"+fileSql+"   "+e.getMessage());
              e.printStackTrace();
            }         
            
                   wwr.close();
                   thread2 = null; 
                    }
                   });
              thread2.start();
    	
       
      // wwr.close();
       if(isFromDialogMain)
       {
       try
       {
       thread2.join();
       }
       catch(InterruptedException ie)
       {
           System.out.println("DialogBackup.restore      "+ie.getMessage());
           ie.printStackTrace();
       }
       }
       //thread1 = null;
       //thread2 = null;
       }
        return wasRestoreSuccessful;
   }
  
  
private void restoreCommand(Connection con,StringBuffer command)throws SQLException
 {
 
     String strDatabase = "";
     
             //try
            // {
            
                     Statement statement = con.createStatement();//(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
                    strDatabase = con.getCatalog();
                   // txtareaLog.append("\n"+command);
                   // System.out.println("   restore:"+command);
                    if(command.toString().startsWith("USE ") || command.toString().startsWith("SET "))
                    {
                        //db.retrieveDBDataFromQuery(trimmedLine.toString(),"DialogBackUp.restore read "+pathNFile);  // command
                       // System.out.println("PanelBackup.restore() execute:"+command.toString());
                        statement.execute(command.toString());
                    }
                    else
                    {
                       // System.out.println("PanelBackup.restore() executeUpdate:"+command.toString());
                        statement.executeUpdate(command.toString());
                        //db.transactionUpdateQuery(command.toString(),"DialogBackUp.restore write "+pathNFile, true);
                    }
             /*}
             catch ( SQLException sqlex)
      {
          
           txtareaLog.append("\nΤο restore της '"+strDatabase+"' δεν ολοκληρώθηκε. "+sqlex.getMessage());	
//          db.transactionRollback();
                  System.out.println("error: PanelBackup.restore()  sqlex:"+sqlex.getMessage());
                  sqlex.printStackTrace();
      }
      finally
      {
          
 
       }*/
 
 
 
 }


 
 
   
   private void setQueryCreateTable(String sql)
   {
       
       db.retrieveDBDataFromQuery(sql, "PanelBackup.setQueryCreateTable");
       ResultSet rs = db.getRS();
       
       try
       {       
           rs.first();
       
           //System.out.println(""+rs.getString(2));
           String createOrig = rs.getString(2);
           String createRepl = createOrig.replaceAll("CREATE TABLE", "CREATE TABLE IF NOT EXISTS");
           writer.println("\n\n"+createRepl+";");

          rs.close();
       }
       catch ( SQLException sqlex)
      {
         System.out.println("error: PanelBackup.setQueryCreateTable() "+ "error code: " +sqlex.getErrorCode()+" " + sqlex.getMessage());
      }       
   }    
    
   private void setQueryCreateObjectExceptTable(String sql)
   {
       
       db.retrieveDBDataFromQuery(sql, "PanelBackup.setQueryCreateObjectExceptTable");
       ResultSet rs = db.getRS();
       
       try
       {       
           rs.first();
       
           //System.out.println(""+rs.getString(2));
          
           String createOrig = rs.getString(2);
           String createRepl = createOrig.replaceAll("CREATE DATABASE", "CREATE DATABASE IF NOT EXISTS");      // when database, change accordingly      
           writer.println("\n\n"+createRepl+";");

          rs.close();
       }
       catch ( SQLException sqlex)
      {
         System.out.println("error: PanelBackup.setQueryCreateTable() "+ "error code: " +sqlex.getErrorCode()+" " + sqlex.getMessage());
      }       
   }
   
   private void setQueryCreateData(String sql,String table)
   {
       
       db.retrieveDBDataFromQuery(sql, "PanelBackup.setQueryCreateData");
     //txtareaLog.append("\n"+sql);	
       ResultSet rs = db.getRS();
       ResultSetMetaData rsmd = db.getRSMetaData();
       
       try
       {   
           rs.beforeFirst();
           writer.println("");
           
           
           int colCount = rsmd.getColumnCount();
           String strColName = "";
           for(int col =1;col<=colCount;col++)
           {
               strColName = strColName  +rsmd.getColumnName(col);
              
                   if(col==colCount)
                   {
                       
                   }
                   else
                   {
                       strColName = strColName+", ";
                   }                       
           }
           
           
           
           while(rs.next())
           {     writer.print("\nREPLACE INTO "+table+" ("+strColName+") VALUES (");
               for(int c=1;c<=rsmd.getColumnCount();c++)
               {
                   if(rs.getString(c)==null)
                   {
                       writer.print(" "+rs.getString(c)+" ");
                   }
                   else if(rsmd.getColumnType(c)==4 || rsmd.getColumnType(c)==8) // integer 4 and double 8
                   {
                       writer.print(" "+rs.getString(c)+" ");
                   }
                   else
                   {
                       String data = rs.getString(c);


//String text = String.format(data.replaceAll("\n","%n"/*"\\n"*/));   //      "\\n"                       
                    String text = data.replaceAll("\n","\\\\n");   //      "\\n"                       
                  //  System.out.printf(data.replaceAll("\n","%n"),data);
                    //System.out.print("  ("+c+"  "+text+")");  
                       
                       writer.print(" '"+text+"'");
                      
                   }
                   
                   if(c==colCount)
                   {
                       
                   }
                   else
                   {
                       writer.print(", ");
                   }
               }
               writer.print(" );");
           }
           //System.out.println(""+rs.getString(2));
           //writer.println(rs.getString(2));

          rs.close();
          
       }
       catch ( SQLException sqlex)
      {
         System.out.println("error: PanelBackup.setQueryCreateData() "+ "error code: " +sqlex.getErrorCode()+" " + sqlex.getMessage());
      }       
       
       
   }



 /*private void retrieveSettings()
 {
 	
 	System.out.println("DialogBackUp.retrieve settings");
 	
 	   dirInstalled="C:\\Program Files\\MySQL\\MySQL Server 5.1\\bin\\";
       saveDir= System.getProperty("user.dir")+"\\backup\\";
 	   host="127.0.0.1";
       user="root";
       pass="root";
 	
 	   filePre="backup-";
 	   commentsFile="comments.txt";
 	   dbFile="db.txt";
        
        
        GregorianCalendar cal = new GregorianCalendar();
 	   String [] allowedPatterns = new String[1];
 	   utilsDate = new UtilsDate();
 	   utilsDate.readFromFileDateFormats();
 	   allowedPatterns[0]=utilsDate.getDateFormatEditing();         
        
        //int dayInt = cal.get(Calendar.DAY_OF_WEEK);
        int d = cal.get(Calendar.DAY_OF_MONTH);
        int m = cal.get(Calendar.MONTH)+1;  // plus 1. dont know why
        int y = cal.get(Calendar.YEAR);
        //String day = dayArray[dayInt-1];//dayArray starts from 0
        int ampm =  cal.get(Calendar.AM_PM );
        String strAmpm="";
        if (ampm==1)
        {
        	strAmpm="pm";
        }
        else
        {
        	strAmpm="am";
        }
        int hour =  cal.get(Calendar.HOUR );
        int min =  cal.get(Calendar.MINUTE);
        
        //today = "("+y+"-"+m+"-"+d+"_"+strAmpm+"_"+hour+"-"+min+")";
        	   
       //today=utilsDate.reformatDateString()
       
 	
 	txtRestoreToDB.setText("farmersvat");
 	txtDirInstalled.setText(dirInstalled);
 	txtDirSave.setText(saveDir);
 	txtHost.setText(host);
 	txtUser.setText(user);
 	txtPass.setText(pass);

 //retrieveDatabases();
 retrieveBackUpFiles();
 	
 }*/

 /*private void showDirCatalog()
 {  
    DialogDirectoryTree.initialize((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this));
    //DialogDirectoryTree dialogDirectoryTree = new DialogDirectoryTree();
    DialogDirectoryTree.showDialog(txtDirSave.getText());
    txtDirSave.setText(DialogDirectoryTree.getSelectedDir());
    
 }*/

   public static void main(String[] args)
   {
    	
         DialogBackUp bckp = new DialogBackUp();
         //bckp.createGui();
         //bckp.retrieveSettings();
         
         //bckp.locateOnCenterOfTheScreen();
         bckp.setVisible(true);
         
         //bckp.backupDB("farmersvat2"); 
         //bckp.zip();
         //bckp.unzip();
         //bckp.restoreDB("FarmersVat",);

   }


}
