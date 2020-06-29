/** 
Connects and releases database connections
@author
*/
package com.tool.jdbc;

import com.tool.guicomps.*;
import com.tool.utils.*;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Properties;

public class DbConnection implements Constants
{
	private static String systemDirectorySymbol=System.getProperty("file.separator");
    private static UtilsOS utilsOS;
    private static UtilsString utilsString;
    
//	private static String dbDriver = "com.mysql.jdbc.Driver";
	/*private static String dbId = "jdbc:mysql://localhost/mc02ngdb";//name of database
	private static String dbUser = "mc02ng_web";//only select, insert, update, delete
	private static String dbPassword = "grasP+92";*/
	
	// Ensure the DB driver is loaded when this class is first loaded
	
/*	{
		String dbDriver = "com.mysql.jdbc.Driver";
		try 
		{
			Class.forName(dbDriver);
		}
		catch (ClassNotFoundException cnfe)
		{
			System.err.println("ConnectionFactory failed to initialise. ClassNotFoundException: " + cnfe.getMessage());
		
	}}*/
	
	// Return a Connection according to this factory's db, user and password settings
/*	public static Connection getConnection() throws SQLException
	{	
		return DriverManager.getConnection(dbId, dbUser, dbPassword);
	}*/
	
   //from dbtests 
    
   public static int getDBEngineOfConnectionFromFile()
   {
       utilsString =  new UtilsString();
       int dbEngine=0;
     try
     {
        Properties props = new Properties(); //properties to get from file
      
      String   curDir="";
      if(VariablesGlobal.globalDirConfiguration==null)
      {
          if(utilsOS.isOSWindows())
          {
      	       curDir = System.getProperty("user.dir"); // get current dir
          }
          else
          {
      	      String classPath = System.getProperty("java.class.path");
      	      curDir = classPath.substring(0,classPath.lastIndexOf(systemDirectorySymbol));	
          }      	
      	
      }
      else
      {
      	  curDir = VariablesGlobal.globalDirConfiguration ;
      }
         
        
        FileInputStream in = new FileInputStream(curDir+systemDirectorySymbol+FILE_DB_CONFIG);
        props.load(in);

        dbEngine=Integer.valueOf(props.getProperty("dbengine"));
       }
      catch (IOException ex)
      {
          System.err.println("DbConnection.getDBEngineOfConnectionFromFile  IOException:Cannot find text file. "+VariablesGlobal.globalDirConfiguration+systemDirectorySymbol+FILE_DB_CONFIG+"  "+ex);
          //System.err.println(ex);
      }      
       return dbEngine;
   }
    
    
   public static Connection getConnectionFromFile()throws SQLException
   {
       utilsString =  new UtilsString();
   	utilsOS = new UtilsOS();
   	
        Properties p = new Properties(); // properties to be used for connection
   	    String url="";
   	  try
   	  {
     	Properties props = new Properties(); //properties to get from file
       // String fileName = "ViewDB.txt"; //get properties from file

       //System.out.println("DbConnection curDir "+curDir);
      	//curDir = System.getProperty("user.dir"); // get current dir

      String   curDir="";
      if(VariablesGlobal.globalDirConfiguration==null)
      {
          if(utilsOS.isOSWindows())
          {
      	       curDir = System.getProperty("user.dir"); // get current dir
          }
          else
          {
      	      String classPath = System.getProperty("java.class.path");
      	      curDir = classPath.substring(0,classPath.lastIndexOf(systemDirectorySymbol));	
          }      	
      	
      }
      else
      {
      	  curDir = VariablesGlobal.globalDirConfiguration ;
      }

        
        

       
        FileInputStream in = new FileInputStream(curDir+systemDirectorySymbol+FILE_DB_CONFIG);
        props.load(in);

        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null)
           System.setProperty("jdbc.drivers", drivers);

        String dbname = props.getProperty("jdbc.dbname");
        url = props.getProperty("jdbc.url")+dbname;

        String dbDir = props.getProperty("derby.system.home") ;
        if(dbDir !=null )  //if there is in text file
        {           System.setProperty("derby.system.home", dbDir);  } // load it

        p.put("user", props.getProperty("jdbc.username"));// username);
        p.put("password", VariablesGlobal.globalFilePassForDb);//password);
        
        p.put("useUnicode", "true");
        p.put("characterEncoding", "utf8");//iso-8859-7
      
      //System.out.println("DbConnection url"+url);
      //System.out.println("DbConnection user"+p.getProperty("user"));
      }
      catch (IOException ex)
      {
          String error = "Cannot find text file. "+VariablesGlobal.globalDirConfiguration+systemDirectorySymbol+FILE_DB_CONFIG;
          System.err.println("DbConnection.getConnectionFromFile  IOException:"+error+"    "+ex);
          //System.err.println(ex);
       //   UtilsGui utilsGui = new UtilsGui();
        //  utilsGui.showMessageError(error);
       //    System.exit(0);
      }
       return DriverManager.getConnection(url, p);
   }


	// Take back a connection and close it to free up system resources 
	// (Should really recycle it with connection pooling)
	public static void releaseConnection(Connection argConnection) throws SQLException
	{
		if (argConnection != null) 
			argConnection.close(); 
	}
}

