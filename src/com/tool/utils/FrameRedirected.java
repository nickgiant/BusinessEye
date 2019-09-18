// created in 28-10-2009
// from  http://www.rgagnon.com/javadetails/java-0435.html

package com.tool.utils;

import com.tool.guicomps.*;
import static com.tool.guicomps.Constants.CLR_LBL_ROLL;
import static com.tool.guicomps.Constants.FILE_DB_CONFIG;
import com.tool.jdbc.DbConnection;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


/**
 * http://tanksoftware.com/juk/developer/src/com/
     tanksoftware/util/FrameRedirected.java
 A Java Swing class that captures output to the command line 
 (eg, System.out.println)
 FrameRedirected
 * <p>
 * This class was downloaded from:
 * Java CodeGuru (http://codeguru.earthweb.com/java/articles/382.shtml) <br>
 * The origional author was Real Gagnon (real.gagnon@tactika.com);
 * William Denniss has edited the code, improving its customizability
 *
 * In breif, this class captures all output to the system and prints it in
 * a frame. You can choose weither or not you want to catch errors, log 
 * them to a file and more.
 * For more details, read the constructor method description
 */


public class FrameRedirected extends JFrame implements Constants
{

    // Class information
    /*public static final String PROGRAM_NAME = "Redirect Frame";
    public static final String VERSION_NUMBER = "1.1";
    public static final String DATE_UPDATED = "13 April 2001";
    public static final String AUTHOR = 
       "Real Gagnon - edited by William Denniss";*/


    
   // private   TableModelReadOnly tableModelReadOnly;
   // private   JTableDec table ;    
    
    
    private boolean catchErrors;
    private boolean logFile;
    private String fileName;
    private int width;
    private int height;
    private int closeOperation;
    
    private JLabel lblCountOfDBProcesses;

/* DO_NOTHING_ON_CLOSE (defined in WindowConstants): Don't do anything; require the program to handle the operation in the windowClosing method of a registered WindowListener object. 
   HIDE_ON_CLOSE (defined in WindowConstants): Automatically hide the frame after invoking any registered WindowListener objects. 
   DISPOSE_ON_CLOSE (defined in WindowConstants): Automatically hide and dispose the frame after invoking any registered WindowListener objects. 
   EXIT_ON_CLOSE (defined in JFrame): Exit the application using the System exit method. Use this only in applications. 
   */


    TextArea aTextArea = new TextArea();
    PrintStream aPrintStream  =
       new PrintStream(
         new FilteredStream(
           new ByteArrayOutputStream()));

    /** Creates a new RedirectFrame.
     *  From the moment it is created,
     *  all System.out messages and error messages (if requested)
     *  are diverted to this frame and appended to the log file 
     *  (if requested)
     *
     * for example:
     *  RedirectedFrame outputFrame =
     *       new RedirectedFrame
                (false, false, null, 700, 600, JFrame.DO_NOTHING_ON_CLOSE);
     * this will create a new RedirectedFrame that doesn't catch errors,
     * nor logs to the file, with the dimentions 700x600 and it doesn't 
     * close this frame can be toggled to visible, hidden by a controlling 
     * class by(using the example) outputFrame.setVisible(true|false)
     *  @param catchErrors set this to true if you want the errors to 
     *         also be caught
     *  @param logFile set this to true if you want the output logged
     *  @param fileName the name of the file it is to be logged to
     *  @param width the width of the frame
     *  @param height the height of the frame
     *  @param closeOperation the default close operation
     *        (this must be one of the WindowConstants)
     */
    public FrameRedirected(boolean catchErrors, boolean logFile, String fileName, int width,
         int height, int closeOperation)
    {

        this.catchErrors = catchErrors;
        this.logFile = logFile;
        this.fileName = fileName;
        this.width = width;
        this.height = height;
        this.closeOperation = closeOperation;
        int tableHeight = 0;
        //UtilsTable utilsTable = new UtilsTable();
        
       /*  tableModelReadOnly = new TableModelReadOnly();
         table = new JTableDec();
        table.setModel(tableModelReadOnly);
        //table.setSize(width, tableHeight);
        table.setGridColor(CLR_TABLE_GRID);
        JScrollPane tableAggregate = new JScrollPane();
        tableAggregate.setSize(new Dimension(width, tableHeight));
        tableAggregate.setViewportView(table);*/

        /* table.addMouseListener(new MouseAdapter()
                {       	      
                 public void mouseEntered(MouseEvent e)
                 {
                    //System.out.println("mouse entered ");
                 }
                 
                 public void mouseExited(MouseEvent e)
                 {
                                 
                 }

                 public void mouseClicked(MouseEvent e)
                 {
                     /*SwingUtilities.isLeftMouseButton(MouseEvent anEvent) 
                     SwingUtilities.isRightMouseButton(MouseEvent anEvent)
                     SwingUtilities.isMiddleMouseButton(MouseEvent anEvent)                       
                     */
          /*       }                 
                }); 
                          
            */
        
        
        Container c = getContentPane();

        setTitle("Output Frame");
        this.setSize(new Dimension(width,height+tableHeight));//setSize(width,height+tableHeight);
        lblCountOfDBProcesses =new JLabel();
        JLabel lbl=new JLabel();
        Font f = lbl.getFont();
        aTextArea.setFont(f);        
        aTextArea.setBackground(Color.lightGray);
        aTextArea.setForeground(Color.darkGray);
        aTextArea.setEditable(false);
        aTextArea.setSize(new Dimension(width,height));
        c.setLayout(new BorderLayout());
        c.add(aTextArea, BorderLayout.CENTER);
        c.add(lblCountOfDBProcesses, BorderLayout.PAGE_END);
       // c.add(tableAggregate,BorderLayout.PAGE_END);
        displayLog();

        this.logFile = logFile;

        System.setOut(aPrintStream); // catches System.out messages
        if (catchErrors)
            System.setErr(aPrintStream); // catches error messages

        // set the default closing operation to the one given
        setDefaultCloseOperation(closeOperation);
        //this.pack();
        //Toolkit tk = Toolkit.getDefaultToolkit();
        //Image im = tk.getImage("myicon.gif");
        setIconImage(IMG_BOOKMARK);
        
        
        
        //fetchQueryProcessList();
 /*       RunnableFetchQuery myRunnable= new RunnableFetchQuery();
         Thread thread = new Thread(myRunnable);
         thread.start();
 */       
        
    }
  
        /*
    * exists in DialogQueryBrowser and FrameRedirected
    */
   private  void fetchQueryProcessList()
   { 
       int dbEngine=0;
       String systemDirectorySymbol=System.getProperty("file.separator");
	  try
	  {
     	Properties props = new Properties(); //properties to get from file
//     String curDir ="";

      
        FileInputStream in = new FileInputStream(VariablesGlobal.globalDirConfiguration+systemDirectorySymbol+FILE_DB_CONFIG);
        props.load(in);
        /*dbName=props.getProperty("jdbc.dbname");
        dbUrl = props.getProperty("jdbc.url");
        dbDriver = props.getProperty("jdbc.drivers");
        dbUser = props.getProperty("jdbc.username");
        dbPass = props.getProperty("jdbc.password");
        dbPath=props.getProperty("derby.system.home");
        dbEngineDir=props.getProperty("backup.dbenginedir");
        //backUpsDir=props.getProperty("backup.filesdir");*/
        dbEngine=Integer.valueOf(props.getProperty("dbengine"));
          

	      //txtBackupDir.setText(backUpsDir);      
        
      }
      catch (IOException ex)
      {
          System.out.println("FrameRedirected.fetchQueryProcessList  :Cannot find text file:"+FILE_CONFIG);
          //System.err.println(ex);
      }         
       
       if (dbEngine==DBENGINE_MYSQL)
       {
        	//System.out.println("mysql "+dbDriver+" "+dbUrl);
       //tableModelReadOnly.setQuery(STR_SQL_SHOW_PROCESSLIST); 
       //lblCountOfDBProcesses.setText("processes: "+tableModelReadOnly.getRowCount());    
           
       }
       else
       {
           
       }
       
   }
    

    /*
    * exists in DialogQueryBrowser and FrameRedirected
    */   
   private  void fetchQueryProcessListEverySeconds()
   {
      int seconds = INT_SQL_SHOW_PROCESSLIST_SECONDS;
      try
      {
      
          
          
     while(true)
     {
      long millis = System.currentTimeMillis();
      

    /*
    * exists in DialogQueryBrowser and FrameRedirected
    */
//code to run
        fetchQueryProcessList(); //                      to reenable
         Thread.sleep(seconds - millis % seconds);
     }//While          

      }
      catch(Exception e)
      {
          
      }
       
       
   }    
    


    
   public class RunnableFetchQuery implements Runnable {

    public void run()
    {
      fetchQueryProcessListEverySeconds();       
    }
  }



   
   
    
    

    class FilteredStream extends FilterOutputStream {
        public FilteredStream(OutputStream aStream) {
            super(aStream);
          }

        public void write(byte b[]) throws IOException {
            String aString = new String(b);
            aTextArea.append(aString);
        }

        public void write(byte b[], int off, int len) throws IOException {
            String aString = new String(b , off , len);
            aTextArea.append(aString);
            if (logFile) {
                FileWriter aWriter = new FileWriter(fileName, true);
                aWriter.write(aString);
                aWriter.close();
            }
        }
    }

    private void displayLog() {
        Dimension dim = getToolkit().getScreenSize();
        Rectangle abounds = getBounds();
        Dimension dd = getSize();
        //setLocation((dim.width - abounds.width) / 2,(dim.height - abounds.height) / 2);
        setLocation(2,(dim.height - abounds.height) / 2);
        setVisible(true);
        requestFocus();
    }

}