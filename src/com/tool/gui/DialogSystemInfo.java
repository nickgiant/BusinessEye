/*  look also   http://www.java2s.com/Code/Java/Database-SQL-JDBC/DatabaseInfo.htm
 */ 

package com.tool.gui;

import com.tool.guicomps.*;
import com.tool.jdbc.*;
import com.tool.utils.*;
//import java.awt.Color;
import java.sql.DatabaseMetaData;
import java.sql.Connection;
import java.sql.SQLException;


import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
//import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

//import javax.swing.JInternalFrame;
import javax.swing.*;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.JTabbedPane;



public class DialogSystemInfo extends JDialog implements Constants 
{
   private	JDesktopPane desktop;
   //private JTabbedPane tabbedPane;
   private JPanel panelCenterSystInfo = new JPanel();
   private JPanel panelCenterAbout = new JPanel();
   private JPanel panelBottom = new JPanel();
   private JButton btnClose;
   //private JButton btnClose;
   private String database,driver,driverVersion, dbUrl, databaseVersion;

   private JPanel panelAboutNorth = new JPanel();
   private JPanel panelAboutCenter = new JPanel();
   private JLabel applicationLabel = new JLabel();
//    JLabel copyrightLabel = new JLabel();
   private JLabel versionTagLabel = new JLabel();
   //private JFrame parent ;
   private JLabel versionLabel = new JLabel();

    public DialogSystemInfo(JFrame parent)
    {
        super(parent, "Πληροφορίες", true);
        try {
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        pack();
    }

    private void initialize() throws Exception
    {
   /* setClosable(true); 
 	setMaximizable(false); 
 	setIconifiable(false); 
 	setResizable(true); */
  
 	//setBounds(20*(1), 20*(1), 50, 50); 
 	//setContentPane(panelCenterSystInfo); 

  	//windowCount++; 
 	 
 	//desktop.add(jif, layer);   
        //Make the big window be indented 50 pixels from each edge 
        //of the screen.
  /*      int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset, 
                  screenSize.width - inset*2, 
                  screenSize.height-inset*2);

        //Quit this app when the big window closes.
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //Set up the GUI.
        desktop = new JDesktopPane(); //a specialized layered pane
//        createFrame(); //Create first window
        setContentPane(desktop);
//        setJMenuBar(createMenuBar());

        //Make dragging faster:
        desktop.putClientProperty("JDesktopPane.dragMode", "outline");

        */
        
        // north panel -------------------------------------------------------------
        // center panel -------------------------------------------------------------
        //tabbedPane = new JTabbedPane();

        //tabbedPane.add(panelCenterSystInfo,"πληροφορίες συστήματος");
        //tabbedPane.add(panelCenterAbout,"σχετικά με το πρόγραμμα");
        
       
        
       // about panel -------------------------------------------------------------
       
       panelCenterAbout.setLayout(new BorderLayout());
       applicationLabel.setIcon(APP_LOGO);
        //applicationLabel.setBackground(Color.white);
        panelAboutNorth.setBackground(Color.WHITE);
        panelAboutNorth.setLayout(new GridBagLayout());
        versionLabel.setText(VariablesGlobal.appName+" "+VariablesGlobal.appLeadVersion+"."+VariablesGlobal.appSubVersion+"  (c) "+VariablesGlobal.appVersionYear);// + Constants.GENERAL.getString("Version"));
        panelAboutNorth.add(
            applicationLabel,
            new GridBagConstraints(
                0,
                0,
                1,
                1,
                1.0,
                0.0,
                GridBagConstraints.CENTER,
                GridBagConstraints.CENTER,
                new Insets(18, 18, 17, 17),
                0,
                0));

        versionTagLabel.setText("");//LANGUAGE.getString("AboutDialog.version"));
        
        
        panelAboutCenter.setLayout(new GridBagLayout());
        panelAboutCenter.add(
            versionTagLabel,
            new GridBagConstraints(
                0,
                0,
                1,
                1,
                0.0,
                0.0,
                GridBagConstraints.WEST,
                GridBagConstraints.NONE,
                new Insets(11, 12, 0, 0),
                0,
                0));
        panelAboutCenter.add(
            versionLabel,
            new GridBagConstraints(
                1,
                0,
                1,
                1,
                0.0,
                0.0,
                GridBagConstraints.WEST,
                GridBagConstraints.NONE,
                new Insets(11, 0, 0, 0),
                0,
                0));
        
        panelCenterAbout.add(panelAboutNorth, BorderLayout.CENTER);
        panelCenterAbout.add(panelAboutCenter, BorderLayout.PAGE_END);
        //  panel systinfo ---------------------------------------------------------------------
        
        getDBinfo();

        //panelCenterSystInfo.setLayout(new GridLayout(0,2));
        panelCenterSystInfo.setLayout(new BoxLayout(panelCenterSystInfo, BoxLayout.PAGE_AXIS));

        JTextArea txtInfo = new JTextArea(

        VariablesGlobal.appName+" "+VariablesGlobal.appLeadVersion+"."+VariablesGlobal.appSubVersion+"  (c) "+VariablesGlobal.appVersionYear+"\n"
                +VariablesGlobal.appUseName+"\n"
        +"--------------------- Εφαρμογή ---------------------\n"
        +"κατάλογος εφαρμογής: "+VariablesGlobal.globalDirConfiguration+"\n"
        +"υποκατάλογος αναβαθμίσεων: "+VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+DIR_DATAUPDATES+"\n"
        +"υποκατάλογος backup: "+VariablesGlobal.globalDirConfiguration+VariablesGlobal.globalSystemDirectorySymbol+DIR_DATABACKUP+"\n"        
        +"------------- Λειτουργικό σύστημα -------------\n"
        +"Operating System: "+System.getProperty("os.name")+" έκδοση: "+System.getProperty("os.version")+"\n"
        +"Patch level: "+System.getProperty("sun.os.patch.level")+"\n"
        +"system file encoding: "+System.getProperty("file.encoding")+"\n"
        +"get encoding:  "+getEncoding()+"\n"      // if is not utf-8   do this:   String xml =  new String(bout.toByteArray(), "UTF-8");  
        +"σύμβολο καταλόγου: "+VariablesGlobal.globalSystemDirectorySymbol+"\n"
        +"-------------------- Χρήστης --------------------\n"
        +"Όνομα χρήστη: "+System.getProperty("user.name")+"\n"
        +"User's current working directory: "+System.getProperty("user.dir")+"\n"
        +"User's home: "+System.getProperty("user.home")+"\n"
        +"---------------------- Java ----------------------\n"
        +"Java : "+System.getProperty("java.runtime.name")+"\n"
        +"Java έκδοση: "+System.getProperty("java.runtime.version")+"\n"
        +"Java installation directory: "+System.getProperty("java.home")+"\n"
 //       +"Java class path: "+System.getProperty("java.class.path")+"\n"
        
        +"--------------- Βάση δεδομένων ---------------\n");
        
        if (database == null && driver==null && driverVersion==null)
        {
            txtInfo.append("Δεν τρέχει η βάση δεδομένων ή δεν έχει εγκατασταθεί jdbc driver.\n");        	
        }
        else
        { 
            txtInfo.append("Βάση δεδομένων: "+ database+" έκδοση: "+ databaseVersion+"\n"
            +"Όνομα Driver: "+ driver+" έκδοση: "+ driverVersion+"\n"
            +"db url: "+dbUrl+"\n");
        }
       
    Font f = this.getFont();
    txtInfo.setFont(f);       
    txtInfo.setEditable(false);
    txtInfo.setBackground(this.getBackground());
       
       panelCenterSystInfo.add(txtInfo);
/*      values [4] = String.valueOf( metaData.getDriverMajorVersion() );
      values [5] = String.valueOf( metaData.getDriverMinorVersion() );*/
        
        panelCenterSystInfo.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // south panel -------------------------------------------------------------




        btnClose= new JButton();
        //btnClose = new JButton();
        btnClose.setText("<html>κλείσιμο <b>esc</b></html>");
        btnClose.setIcon(ICO_CANCEL16);
        btnClose.requestFocus();
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            { 
                //dialogMain.setCompanyYearUserDate("nel","2000","user89","date" );
                dispose();
            }
        });
        Action actionClose = new ActionClose();
        btnClose.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE ,0), "close"); //where the constant is JComponent.WHEN_FOCUSED, you can just use getInputMap with no arguments
        btnClose.getActionMap().put("close", actionClose);
        
        panelBottom.add(btnClose);

        // this --------------------------------------------------------------------
//        getContentPane().add(panelAboutNorth, BorderLayout.NORTH);
        getContentPane().add(panelCenterSystInfo, BorderLayout.CENTER);
        getContentPane().add(panelBottom, BorderLayout.PAGE_END);
        
        this.pack();
/*        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {          dispose();        }
        });*/
    }
  
    ///https://dzone.com/articles/java-may-use-utf-8-as-its-default-charset
   private String getEncoding()
   {

      final byte [] bytes = {'D'};

      final InputStream inputStream = new ByteArrayInputStream(bytes);

      final InputStreamReader reader = new InputStreamReader(inputStream);

      final String encoding = reader.getEncoding();

      return encoding;

   }
    
    private void getDBinfo()
    {
     try
     {
        Connection conn = DbConnection.getConnectionFromFile();//get from connection factory
        DatabaseMetaData metaData = conn.getMetaData();
        database = metaData.getDatabaseProductName();
        databaseVersion = metaData.getDatabaseProductVersion();
        driver = metaData.getDriverName();
        driverVersion = metaData.getDriverVersion();
        dbUrl = metaData.getURL();

        
        DbConnection.releaseConnection(conn);
     }
     catch ( SQLException sqlex)
     {
          System.out.println("error:DialogSystemInfo:"+sqlex.getMessage());
     }
    }

    public void locateOnCenterOfTheScreen()
    {
    	Dimension paneSize   = this.getSize();
    	Dimension screenSize = this.getToolkit().getScreenSize();
    	this.setLocation(
            (screenSize.width - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
    }
    
    class  ActionClose extends AbstractAction                 
    {       
        public ActionClose()
        {        }
    	public void actionPerformed(ActionEvent e)
      	{           btnClose.doClick();        }    	
    }                    

    public static void main(String args[])
    {
       DialogSystemInfo sysinfo = new DialogSystemInfo(null);
       //showDialog();
       sysinfo.setVisible(true);
    
    }    

}
