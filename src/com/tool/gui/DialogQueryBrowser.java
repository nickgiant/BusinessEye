/* // created 5-2-2012
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.gui;


import com.tool.jdbc.*;
  import com.tool.guicomps.*;
  import com.tool.utils.*;

import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
  import java.io.IOException;
  import java.io.FileInputStream;  


/**
 *
 * @author sun
 */
public class DialogQueryBrowser extends JDialog implements Constants
{
      private Connection con;
  private Connection conRs;
  private Connection conRsmd;
  public ResultSet rs;
  private ResultSetMetaData rsmd;
  private Statement stmnt;
  private DatabaseMetaData md;
    private int dbEngine=0; 
    private String database ;
    private String databaseVersion;
    private static String systemDirectorySymbol;
    private JFrame frame;
    private JTextArea textAreaMetaData;
    private JTextArea queryTextArea;
    private TableModelReadOnly tableModelReadOnly;
    private TableModelReadOnly tableModelReadOnlyProcesses;
    private JPanel panelButtons;
    JTextArea txtareaLog;
    private ListSelectionModel lsm;
    JPanel panelData;
    private static UtilsOS utilsOS;
    private static UtilsGui utilsGui;
    private JTableDec table;
    private JTabbedPane tabPanel;
    private String strTabProcessTitle = "db processes: ";
    private JTableDec tableProcesses;
    private String [] strTables = null;
      //String [] tables = null;//getTablesWithPKsNImpKs();//retrieveTables();// getTablesWithPKsNImpKs();
    private JListDec lstTables= new JListDec(strTables);
        public DialogQueryBrowser()//(Frame parent, boolean modal)
    {
        super();
        //this.setTitle("QueryBrowser");
     
        initComponents(null);
    }
    
    /** This method is called from within the constructor to initialize the form.*/

    public DialogQueryBrowser(JFrame frame)//(Frame parent, boolean modal)
    {
        super(frame ,"", true);//LANGUAGE.getString("AboutDialog.title"));
        initComponents(frame);
    }
         
    private void initComponents(JFrame frameIn) 
    {
    	frame=frameIn;
        this.setTitle("QueryBrowser");
        utilsOS = new UtilsOS();  
      // JFrame.setDefaultLookAndFeelDecorated(true);
      // JDialog.setDefaultLookAndFeelDecorated(true);  // setted in start
       systemDirectorySymbol=System.getProperty("file.separator");
       
       utilsGui = new UtilsGui(); 
        
      loadConfigFromFile();  
              tableModelReadOnly= new TableModelReadOnly();  
              tableModelReadOnlyProcesses = new TableModelReadOnly();  
    //Database db= new Database();
    if(dbEngine!=0)
	{

        panelData = new JPanel();
        tabPanel = new JTabbedPane();
        JPanel panelMetaData = new JPanel();
        panelMetaData.setLayout(new BorderLayout());
        textAreaMetaData = new JTextArea(20,10);// int rows, int columns) 
        JLabel lbl = new JLabel();
        textAreaMetaData.setFont(lbl.getFont());
        JScrollPane scrollMetaData = new JScrollPane();
        scrollMetaData.setViewportView(textAreaMetaData);
        panelMetaData.add(scrollMetaData, BorderLayout.CENTER);
      

       JScrollPane scrollPaneTables = new JScrollPane(lstTables);
       //scrollPaneTables.setMinimumSize(new Dimension(100,20));
     
     // Create the panel for the connection information

       
        // Create the table.
       //JScrollPane tableAggregate = createTable();
               table = new JTableDec(); 
        table.setGridColor(CLR_TABLE_GRID);
        JScrollPane tableAggregate = new JScrollPane();
        tableAggregate.setViewportView(table);
        table.setModel(tableModelReadOnly);
        
        // Use a scrollbar, in case there are many columns. 
	    //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 

        // Install a mouse listener in the TableHeader as the sorter UI.
      //sorter.addMouseListenerToHeaderInTable(table);
        
       
       
       
        //tableAggregate.setBorder(new BevelBorder(BevelBorder.LOWERED));       
       
       
	// Create the buttons.

	JButton fetchButton = new JButton("fetch");
        fetchButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	           fetch(queryTextArea.getText());
	        }
	    }
	);
	
		JButton btnUpdate = new JButton("update");
        btnUpdate.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	           updateDB(queryTextArea.getText());
	        }
	    }
	);
	
    	// Create the query text area and label.
       queryTextArea = new JTextArea(12, 90);// int rows, int columns) 
       queryTextArea.setLineWrap(true);
       queryTextArea.setFont(lbl.getFont());//new Font(Font.SANS_SERIF,Font.PLAIN,11));
	    JScrollPane scrlQueryAggregate = new JScrollPane(queryTextArea);
        //scrlQueryAggregate.setBorder(new BevelBorder(BevelBorder.LOWERED));
        


        
       JxPanel panelWritableTable = new JxPanel();
       panelWritableTable.setLayout(new BorderLayout());
       
/*       JButton btnUpdateTableData = new JButton("save changes");
       btnUpdateTableData.addActionListener(new ActionListener()
        {
	        public void actionPerformed(ActionEvent e) 
	        {     	
//	           tableModelReadOnly.updateDB();    
	        }
	    });               
 */              
       JPanel panelWritableTableButtons = new JPanel();
//       panelWritableTableButtons.add(btnUpdateTableData);
       panelWritableTableButtons.setLayout(new GridLayout(0,1));
       
       panelWritableTable.add(panelWritableTableButtons,BorderLayout.PAGE_START);
       panelWritableTable.add(tableAggregate, BorderLayout.CENTER );
       
        panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(0,1));
        panelButtons.add(fetchButton);
        panelButtons.add(btnUpdate);
        
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new BorderLayout());
        panelTop.add(panelButtons, BorderLayout.LINE_END);
  //      panelTop.add(scrollPaneTables, BorderLayout.LINE_START);
	    panelTop.add(scrlQueryAggregate,BorderLayout.CENTER);
	    // Add all the components to the main panel.
             
       JxPanel panelTableProcesses = new JxPanel();
       panelTableProcesses.setLayout(new BorderLayout());            
        tableProcesses = new JTableDec(); 
        tableProcesses.setGridColor(CLR_TABLE_GRID);
        JScrollPane tableAggregateProcesses = new JScrollPane();
        tableAggregateProcesses.setViewportView(tableProcesses);
        tableProcesses.setModel(tableModelReadOnlyProcesses);            
        panelTableProcesses.add(tableAggregateProcesses);
            
            
            
           txtareaLog = new JTextArea(12, 30);// int rows, int columns) 
	   txtareaLog.setEditable(false);
	   txtareaLog.setLineWrap(true);
	   txtareaLog.setFont(lbl.getFont());
       JScrollPane scrollTextAreaLog = new JScrollPane(txtareaLog);


       final JListDec lstTbl = lstTables;
       //lstTbl.setSize(40,lstTbl.getHeight());
           lsm = lstTables.getSelectionModel();
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
                        System.out.println("No rows are selected.");
                    } else
                    {   
                       queryTextArea.setText("SELECT * FROM "+lstTbl.getSelectedValue().toString());
                    }
                }
            });
  
          lstTables.addMouseListener(new MouseAdapter()
          {
            public void mouseClicked(MouseEvent e)
            {
            	JListDec lstTbls = (JListDec)e.getSource();
                if (e.getClickCount() == 2) // make it 2 for doubleclick
                {   
                    //retrievePrimKeyValue( query, selectedTableRow, primKey);
                    //System.out.println("panelODMRData.selection table double clicked. selectedTableRow "+selectedTableRow+" PKvalue "+getPrimKeyValue());
                   queryTextArea.setText("SELECT * FROM "+lstTbls.getSelectedValue().toString());
                   fetch("SELECT * FROM "+lstTbls.getSelectedValue().toString()); 
                    
                }
            } 
           });
        
        tabPanel.add("data table",panelWritableTable);
        tabPanel.add("MetaData",panelMetaData);
        tabPanel.add(strTabProcessTitle,panelTableProcesses);
        
        panelData.setLayout(new BorderLayout());
        panelData.add(panelTop,BorderLayout.PAGE_START);
     // panelData.add(scrollPaneTables,BorderLayout.LINE_START);
        panelData.add(tabPanel,BorderLayout.CENTER);
        panelData.add(scrollTextAreaLog,BorderLayout.PAGE_END);
        
        
        //this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout());
        this.add(scrollPaneTables,BorderLayout.LINE_START);
        this.add(panelData,BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(1045,690));

/*        // Create a Frame and put the main panel in it.
        frameQueryBrowser = new JFrame("Query Browser ("+database+") "+dbUrl);
       // frameQueryBrowser.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {System.exit(0);}});
        frameQueryBrowser.setBackground(Color.lightGray);
        frameQueryBrowser.getContentPane().add(panelData);
        frameQueryBrowser.pack();
        //frameQueryBrowser.setVisible(false);
        //frameQueryBrowser.setBounds(200, 200, 800, 700);
        
        locateOnCenterOfTheScreen(frameQueryBrowser);
        
        // jdk6    
 //       RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
  //      table.setRowSorter(sorter);
        //jdk6
        
        
        
        frameQueryBrowser.setVisible(true);
        
   */     
        
          // Create a Frame and put the main panel in it.
        //frameQueryBrowser = new JFrame("Query Browser ("+database+") "+dbUrl);
       // frameQueryBrowser.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {System.exit(0);}});
/*        frameQueryBrowser.setBackground(Color.lightGray);
        frameQueryBrowser.getContentPane().add(panelData);
        frameQueryBrowser.pack();
        //frameQueryBrowser.setVisible(false);
        //frameQueryBrowser.setBounds(200, 200, 800, 700);
        
        locateOnCenterOfTheScreen(frameQueryBrowser);
        */
        
        
       }
	   else
	   {
	   		showMessageErrorSelectDatabase();
	   }	

        //fetchQueryProcessList();
        RunnableFetchQuery myRunnable= new RunnableFetchQuery();
         Thread thread = new Thread(myRunnable);
         thread.start();
        
        
    }
    
    private  void fetchQueryProcessList()
   { 
       tableModelReadOnlyProcesses.setQuery(STR_SQL_SHOW_PROCESSLIST); 
       tabPanel.setTitleAt(2,strTabProcessTitle+tableModelReadOnlyProcesses.getRowCount());
       
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
      //code to run
    /*
    * exists in DialogQueryBrowser and FrameRedirected
    */      
//        fetchQueryProcessList();
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
   
        
    private void showMessageErrorSelectDatabase()
    {
    	
    	utilsGui.showMessageError(this,"Please select database.");
    	
    }
    
  
    private void loadConfigFromFile()
	{
	  try
	  {
     	Properties props = new Properties(); //properties to get from file
//     String curDir ="";

      
        FileInputStream in = new FileInputStream(VariablesGlobal.globalDirConfiguration+systemDirectorySymbol+FILE_DB_CONFIG);
        props.load(in);
/*        dbUrl = props.getProperty("jdbc.url");
        dbDriver = props.getProperty("jdbc.drivers");
        dbUser = props.getProperty("jdbc.username");
        dbPass = props.getProperty("jdbc.password");
        dbPath=props.getProperty("derby.system.home");
        dbEngineDir=props.getProperty("backup.dbenginedir");
        //backUpsDir=props.getProperty("backup.filesdir");*/
        dbEngine=Integer.valueOf(props.getProperty("dbengine"));
  
          /*txtDbPath.setText(dbPath);
          txtDbDriver.setText(dbDriver);                 
          txtDbUrl.setText(dbUrl);
          txtDbUser.setText(dbUser);
          txtDbPass.setText(dbPass);
	      txtDbEngineDir.setText(dbEngineDir);*/
	      //txtBackupDir.setText(backUpsDir);      
  
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
          System.out.println("error DialogQueryBrowser.IOException:Cannot find text file:"+FILE_CONFIG);
          //System.err.println(ex);
      }
      
   }
   

    
    
    public void displayDialogQueryBrowser()
    {

        

         strTables = getTables();//getTablesWithPKsNImpKs();
        lstTables.setListData(strTables);        
        
        this.pack();

        
        locateOnCenterOfTheScreen();
        this.setVisible(true);
       
        
       
        
        
    }
    
     private Connection getConnectionToDB()
    {
        Connection con = null;
      try
       {
       con = DbConnection.getConnectionFromFile();
       }
       catch ( SQLException sqlex)
      {
         System.out.println("error: DialogQueryBrowser.getConnectionToDB() "+ "error code: " +sqlex.getErrorCode()+" " + sqlex.getMessage()); 
      
      }
       return con;
    }
   
   private String[] getTables()
   {
 //  	txtareaLog.setText("");
   	ArrayList listTables = new ArrayList();
   	//databaseTableMeta = new DatabaseTableMeta();
      try
      { 
        
        // SQLite only supports TYPE_FORWARD_ONLY cursors and only CONCUR_READ_ONLY 
        Connection con = getConnectionToDB();
        Statement stmnt = con.createStatement();//ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        DatabaseMetaData dbm = con.getMetaData();
        String[] types = {"TABLE"};
        ResultSet rs = dbm.getTables(null,null,"%",types);
        //ResultSet rsPK;
        
        //int queryColumns=0;
        
        //System.out.println("Table name:");
        int countTables=0;
        while (rs.next()) // for each table
        {
        	countTables++;
         String strTable = rs.getString("TABLE_NAME");
         textAreaMetaData.append(countTables+". ["+strTable+"]\n");
         listTables.add(strTable);
         //String primKs =
        }
      
        con.close();
      }
      catch (SQLException e)
      {
        System.out.println("DialogQueryBrowser.getTables "+e);
      }
      finally
      {
                  
      }
         String[] tables = new String[listTables.size()];
   	 for(int i=0;i<listTables.size();i++)
   	 {
   	 	tables[i]=listTables.get(i).toString();
   	 	//System.out.println(tables[i]);
   	 }
   	 return tables;
   }      
    
    
    
   private String[] getTablesWithPKsNImpKs()
   {
 //  	txtareaLog.setText("");
   	ArrayList listTables = new ArrayList();
   	//databaseTableMeta = new DatabaseTableMeta();
       Connection con = getConnectionToDB();
      try
      { 
        
        // SQLite only supports TYPE_FORWARD_ONLY cursors and only CONCUR_READ_ONLY 
       
        Statement stmnt = con.createStatement();//ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        DatabaseMetaData dbm = con.getMetaData();
        String[] types = {"TABLE"};
        ResultSet rs = dbm.getTables(null,null,"%",types);
        ResultSet rsPK;
        
        int queryColumns=0;
        
        //System.out.println("Table name:");
        int countTables=0;
        while (rs.next()) // for each table
        {
        	countTables++;
         String strTable = rs.getString("TABLE_NAME");
         textAreaMetaData.append(countTables+". ["+strTable+"]\n");
         listTables.add(strTable);
         //String primKs =
         
 // SQLite does not support it        
         rsPK = dbm.getPrimaryKeys( null, null, strTable);
         //System.out.println("DialogQueryBrowser.getTables PrimKs count "+table+"->"+databaseTableMeta.getNumberOfPrimKeys());
         //txtareaLog.append("PrimKs count "+table+"->"+dbm+"\n") ;
       
 
       
       int countPKs=0;
       while(rsPK.next() )
       {
       	countPKs++;
        //rsPK.
                
          textAreaMetaData.append("primary key ("+countPKs+") "+strTable+"."+rsPK.getString(4)+"\n") ;
       }
       
      DatabaseMetaData md = con.getMetaData();     
      ResultSet rsImpKeys = md.getImportedKeys( null, null, strTable);
          
          
         //System.out.println("Database.retrieveRSMetaData query: "+query);
        ResultSet rsE = stmnt.executeQuery("SELECT * FROM "+strTable);
        ResultSetMetaData rsmd = rsE.getMetaData();          
          
          queryColumns = rsmd.getColumnCount();
      int intImpKeys=0;
      while ( rsImpKeys.next() )
      { 
        intImpKeys ++;
           //System.out.println("DialogQueryBrowser.getTables Imp Keys "+table+"-"+rsImpKeys.getString(4)+" "+rsImpKeys.getString(3)+" "+rsImpKeys.getString(4));// 7 PKTABLE_NAME, 4 PKCOLUMN_NAME, 3 FKTABLE_NAME, 8 FKCOLUMN_NAME
           textAreaMetaData.append("imported Key ("+intImpKeys+") "+strTable+"."+rsImpKeys.getString(8)+"   "+rsImpKeys.getString(3)+"."+rsImpKeys.getString(4)+"\n") ;
      }
          
        //table=table+" (ImpK "+intImpKeys+")";
          
          
          //System.out.println(table);
          
        }
        con.close();
      }
      catch (SQLException e)
      {
        System.out.println("DialogQueryBrowser.getTablesWithPKsNImpKs "+e);
      }
      finally
      {
                  
      }
   	 
   	 String[] tables = new String[listTables.size()];
   	 for(int i=0;i<listTables.size();i++)
   	 {
   	 	tables[i]=listTables.get(i).toString();
   	 	//System.out.println(tables[i]);
   	 }
   	 return tables;
   }    
    
    
    
   private void fetch(String query)
   {
   	String [] qmd = getQueryMetaData(query);
   	textAreaMetaData.append("\n"+"meta data for :"+query+"\n") ;
    if(qmd != null && qmd.length>0)
    {
        for(int d =0;d<qmd.length;d++)
        {
        	textAreaMetaData.append(qmd[d]+"\n") ;
        }   	   	
    }

    String s = tableModelReadOnly.setQuery(query);
    //System.out.println("DialogQueryBrowser.fetch query "+q);
        textAreaMetaData.append("\n"+s+"\n"+"\n") ;
   	txtareaLog.append(s+"\n") ;
       table.revalidate();
	//txtareaLog.setCaretPosition(txtareaLog.getLineCount());
   }

   private void updateDB(String query)
   {
       int ret=0;
      try
     {
         con = DbConnection.getConnectionFromFile();
        //con = getConnection();
        //stmnt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
       
        // System.out.println("Database.updateQuery "+query);      
       
         // exists in DialogQueryBrowser.updateDB  and Database.retrieveDBDataFromQuery
        if (DbConnection.getDBEngineOfConnectionFromFile()==DBENGINE_SQLITE)
        {  // SQLite only supports TYPE_FORWARD_ONLY cursors    SQLite only CONCUR_READ_ONLY
            
             stmnt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY); 
        }
        else
        {
            stmnt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); 
        } 
        
        
        
        
        	 ret = stmnt.executeUpdate(query);
     }
     catch ( SQLException sqlex)
     {       
         System.out.println("error:DialogQueryBrowser.updateDB() "+ret+"error code: " +sqlex.getErrorCode()+" " + sqlex.getMessage());
         txtareaLog.append("error in = "+query+"\n"+sqlex.getMessage()+"\n") ;
     }
       
       
   	 // Database db = new Database();
   	  txtareaLog.append(query+"\n") ;
   	  txtareaLog.setCaretPosition(txtareaLog.getLineCount());
   }
   
   /* private JScrollPane createTable() 
    {
        
        table = new JTableDec(); 
        table.setGridColor(CLR_TABLE_GRID);
        
        //tableModel= new TableModelReadOnly();
        tableModel= new TableModelResultSet();
        
        table.setModel(tableModel);
        
        // Use a scrollbar, in case there are many columns. 
	    //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 

        // Install a mouse listener in the TableHeader as the sorter UI.
      //sorter.addMouseListenerToHeaderInTable(table);
        JScrollPane scrollpane = new JScrollPane(table);
        return scrollpane;
    }   */ 
    
    
   private String[] getQueryMetaData(String query)
   {
   	String[] ret=null;
   	
   	      try
      { 
        Connection con = getConnectionToDB();//get from connection factory
        Statement statement = con.createStatement(); 
         //System.out.println("Database.retrieveRSMetaData query: "+query);
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
/*
  int getColumnDisplaySize(int column) 
          Indicates the designated column's normal maximum width in characters. 
 String getColumnLabel(int column) 
          Gets the designated column's suggested title for use in printouts and displays. 
 String getColumnName(int column) 
          Get the designated column's name. 
 int getColumnType(int column) 
          Retrieves the designated column's SQL type. 
 String getColumnTypeName(int column) 
          Retrieves the designated column's database-specific type name. 
 int getPrecision(int column) 
          Get the designated column's specified column size. 
 int getScale(int column) 
          Gets the designated column's number of digits to right of the decimal point. 
 String getSchemaName(int column) 
          Get the designated column's table's schema. 
 String getTableName(int column) 
          Gets the designated column's table name. 
 boolean isAutoIncrement(int column) 
          Indicates whether the designated column is automatically numbered. 

*/        
        ret = new String[rsmd.getColumnCount()];
        for(int c= 1;c<=rsmd.getColumnCount();c++)
        {
        	//System.out.println("DialogQueryBrowser.getQueryMetaData "+c+" "+rsmd.getTableName(c)+" "+rsmd.getColumnName(c));
        	ret[c-1]=" "+c+" "+rsmd.getTableName(c)+"."+rsmd.getColumnName(c)+" "+rsmd.getColumnLabel(c)+" ("+rsmd.getColumnDisplaySize(c)+" "+rsmd.getScale(c)+") (type"+rsmd.getColumnType(c)+"-"+rsmd.getColumnTypeName(c)+" isAutoInc("+rsmd.isAutoIncrement(c)+"))";
        }
        
        con.close();
      }
      catch (SQLException e)
      {
        System.out.println("DialogQueryBrowser.getQueryMetaData "+e);
      }   	
   	return ret;
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

    private void locateOnCenterOfTheScreen(JFrame frame)//on the center
    {
    	Dimension paneSize   = frame.getSize();
    	Dimension screenSize = this.getToolkit().getScreenSize();
    	//System.out.println(getSize()+" - "+getToolkit().getScreenSize());
    	frame.setLocation(
            (screenSize.width  - paneSize.width)  / 2,
            (screenSize.height - paneSize.height) / 2);
    }
    
    
    public static void main(String args[])
    {
    	DialogQueryBrowser dialogQueryBrowser = new DialogQueryBrowser();
       	//Dimension paneSize = this.getSize();
        dialogQueryBrowser.displayDialogQueryBrowser();
       	dialogQueryBrowser.locateOnCenterOfTheScreen();
        dialogQueryBrowser.setVisible(true);
    } 
    
    
    
}
