package com.tool.jdbc;

import com.tool.utils.*;

import java.util.logging.*;

import java.sql.*;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseMeta
{

  private Connection con;
  private ResultSet rs;
  private DatabaseMetaData md;

  private int tableCount;
  private int fieldCount;
  private int pkCount;
  private int impkCount;
  
  private String[] tableNames ;
  private String[] fieldNames ;
  private String[] pkFieldNames;
  private String[] impkFieldNames;
  private String[] impkImpNames;// the PK table.col that is imported

  private String[] mdFieldNames = {"name", "index", "type","size","isPK","is ImpK"};//   };//
  private Object[][] mdFieldObjects ;//=  new Object[4][4];
 
 private UtilsGui utilsGui;
 // private Column column;

   Logger logger = Logger.getLogger("mine");


	public DatabaseMeta()
	{    
	   utilsGui = new UtilsGui(); 
	}
	
  public void retrieveTables()
  { 
     //logger.info("");

  	//System.out.println("retrieve tables");
    try
    {
      Connection con = DbConnection.getConnectionFromFile();//get from connection factory
      DatabaseMetaData md = con.getMetaData();

      ResultSet rs = md.getTables( null, null,  "%", null );//  getTableTypes
      tableCount = 0;
      while ( rs.next() )
      {  
      	  tableCount++; 
      	  //System.out.println(tableCount+rs.getString("TABLE_NAME"));
      }
        DbConnection.releaseConnection(con);
       rs.first();
       tableNames = new String[tableCount];
        
  	    for (int i=0; i<tableCount; i++)
        {
           tableNames[i] = rs.getString("TABLE_NAME");//  REMARKS explanation
           //System.out.println(tableNames[i]);
           //System.out.println(rs.getString("COMMENT"));
          // tableCaptions[i] = rs.getString("REMARKS");
           rs.next();
        } 
       //rs.close();
       DbConnection.releaseConnection(con);
      //logger.info("");

    }
    catch ( SQLException sqlex)
    {
    	//utilsGui.showInfoMessage(sqlex.getMessage(), "Προσοχή");
        System.out.println("error:DatabaseMeta.retTables:"+sqlex.getMessage());
    }
  }
 
    public void retrieveFields(String tableName)//table
   {
    try
    {
       con = DbConnection.getConnectionFromFile();//get from connection factory
      DatabaseMetaData md = con.getMetaData();

      String name; int index; String typeName;
     
      ResultSet rs = md.getColumns( null, null, tableName, "%" );
       fieldCount= 0;
      while ( rs.next() )
      {  
      	  fieldCount++; 
      }
     
       rs.first();
       fieldNames = new String[fieldCount];
         
         String mdName; int mdIndex; String mdTypeName; String mdSize;
    	  mdFieldObjects =  new Object[this.fieldCount][mdFieldNames.length];//        mdFieldObjects init
  	    
  	 //   System.out.println("-- "+tableName+" fields "+fieldCount);
  	    for (int i=0; i<fieldCount; i++)
        {
           fieldNames[i] = rs.getString("COLUMN_NAME");
           //for mdFields
          mdName= rs.getString("COLUMN_NAME");
          mdIndex=i;
          mdTypeName=rs.getString("TYPE_NAME");
          mdSize = rs.getString("COLUMN_SIZE");
          //for object
          	mdFieldObjects[i][0]=mdName;
          	mdFieldObjects[i][1]=Integer.valueOf(mdIndex);
          	mdFieldObjects[i][2]=mdTypeName;
          	mdFieldObjects[i][3]=mdSize;
         System.out.println(mdIndex+" "+mdName+" ("+mdTypeName+" "+mdSize+")");
          //comparePKsWithField(mdName, mdIndex);
           rs.next();
        } 

       //rs.close();
       DbConnection.releaseConnection(con);
    }
    catch ( SQLException sqlex)
    {
        System.out.println("error:DatabaseMeta.retFields:"+sqlex.getMessage());
    }
   }
 
  public void retrievePrimKs(String tableName)
   {
    try
    {
      con = DbConnection.getConnectionFromFile();//get from connection factory
      DatabaseMetaData md = con.getMetaData();

     // String name; int index; String typeName;
      ResultSet rs = md.getPrimaryKeys( null, null, tableName);
       pkCount= 0;
      while ( rs.next() )
      {  
      	  pkCount++; 
      }
     
       rs.first();
       pkFieldNames = new String[pkCount];
         
        // String pkFName; int mdIndex; String mdTypeName;
    	 // mdFieldObjects =  new Object[this.fieldCount][3];
  	    System.out.println("-- PKs "+pkCount);
  	    for (int i=0; i<pkCount; i++)
        {
          pkFieldNames[i] = rs.getString("COLUMN_NAME");
          
          System.out.println(i+" "+pkFieldNames[i]+" seq "+ rs.getString("KEY_SEQ"));
          //mdIndex=i;
          //mdTypeName=rs.getString("TYPE_NAME");
          //compareFieldsWithPK(pkFieldNames[i]);
          
           rs.next();
        } 
        assignPKsToFields();
       //rs.close();
       DbConnection.releaseConnection(con);
    }
    catch ( SQLException sqlex)
    {
        System.out.println("error:DatabaseMeta.retPK:"+sqlex.getMessage());
    }
  }

  private void assignPKsToFields()
  {    
     if (pkCount >0)
     {
  	    for (int i=0; i<fieldCount; i++)
        {   //System.out.println("assign"+'-'+pkFieldNames[i]);
           String isPK = "-";
            isPK = comparePKsWithField(fieldNames[i]);
            mdFieldObjects[i][4]= isPK;
        }
      }
      else
      { // when there is a table without PKs
      	for (int i=0; i<fieldCount; i++)
      	{
      	   mdFieldObjects[i][4]= "";
      	}
      }
  }  

  private String comparePKsWithField(String fld)
  {
  	     String isPK = "-" ;
  	    
  	    for (int i=0; i<pkCount; i++)
        {   //System.out.println("comp "+pkFieldNames[i]+'-'+fld);
        	if (pkFieldNames[i].equalsIgnoreCase(fld))
        	{
        		isPK = "true";
        		//System.out.println(" is PK ["+i+"]"+pkFieldNames[i]+'-'+fld);
        		break; // important
        	}
        	else 
        	{
        		isPK = "false";
                // not a break here    	    
        	}
        }
     return isPK;
  }


  /*public void retrieveImpKsFromQuery(String query)
   {
    try
    {
      
        con = DbConnection.getConnectionFromFile();//get from connection factory
        Statement stmnt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
        //System.out.println("execute q ");      
        ResultSet rs = stmnt.executeQuery(query); 
        ResultSetMetaData rsmd = rs.getMetaData();     
         
         int colCount= rsmd.getColumnCount();
         for(int c=1;c<=colCount;c++)
         {
            rsmd.getTableName(c) ;
         	
         }
        
      /////////////////////////////////////////////////////////////////////////////////////////
      
      //con = DbConnection.getConnectionFromFile();//get from connection factory
      //DatabaseMetaData md = con.getMetaData();
     
      //ResultSet rs = md.getImportedKeys( null, null, tableName);
       impkCount= 0;
      while ( rs.next() )
      {  
      	  impkCount++; 
      }
     
       rs.first();
       impkFieldNames = new String[impkCount];
       impkImpNames = new String[impkCount];
       
  	    System.out.println("-- ImpKs "+impkCount);
  	    for (int i=0; i<impkCount; i++)
        {
          impkFieldNames[i] = rs.getString(8);
          System.out.println("DatabaseMeta impk "+rs.getString("FKCOLUMN_NAME")+'.'+rs.getString("KEY_SEQ"));// 3 PKTABLE_NAME 4 PKCOLUMN_NAME 7 FKTABLE_NAME 8 FKCOLUMN_NAME 
          
          impkImpNames[i]= rs.getString(3)+'.'+rs.getString(4);
       //   System.out.println(i+" "+rs.getString(3)+'.'+rs.getString(4)+' '+rs.getString(7)+'.'+rs.getString(8));
        //   compareFieldsWithImpK(impkFieldNames[i]);
           rs.next();
        } 
       assignImpKsToFields(impkCount);
       DbConnection.releaseConnection(con);
    }
    catch ( SQLException sqlex)
    {
        System.out.println("error:DatabaseMeta.retImpKs:"+sqlex.getMessage());
    }
   
  }*/


  public void retrieveImpKs(String tableName)
   {
    try
    {
      con = DbConnection.getConnectionFromFile();//get from connection factory
      DatabaseMetaData md = con.getMetaData();
     
      ResultSet rs = md.getImportedKeys( null, null, tableName);
       impkCount= 0;
      while ( rs.next() )
      {  
      	  impkCount++; 
      }
     
       rs.first();
       impkFieldNames = new String[impkCount];
       impkImpNames = new String[impkCount];
       
  	    System.out.println("-- ImpKs "+impkCount);
  	    for (int i=0; i<impkCount; i++)
        {
          impkFieldNames[i] = rs.getString(8);
          System.out.println("DatabaseMeta impk "+rs.getString("FKCOLUMN_NAME")+'.'+rs.getString("KEY_SEQ"));// 3 PKTABLE_NAME 4 PKCOLUMN_NAME 7 FKTABLE_NAME 8 FKCOLUMN_NAME 
          
          impkImpNames[i]= rs.getString(3)+'.'+rs.getString(4);
       //   System.out.println(i+" "+rs.getString(3)+'.'+rs.getString(4)+' '+rs.getString(7)+'.'+rs.getString(8));
        //   compareFieldsWithImpK(impkFieldNames[i]);
           rs.next();
        } 
       assignImpKsToFields(impkCount);
       DbConnection.releaseConnection(con);
    }
    catch ( SQLException sqlex)
    {
        System.out.println("error:DatabaseMeta.retImpKs:"+sqlex.getMessage());
    }
   
  }
  private void assignImpKsToFields(int impkCount)
  {    
     if (impkCount >0)
     {
  	    //System.out.println("fieldCount "+ fieldCount);
  	    for (int i=0; i<impkCount; i++)
        {   
           //System.out.println("assign field: "+impkFieldNames[i]);
           String isImpK = "-";
            isImpK = compareImpKsWithField(impkFieldNames[i]);
            System.out.println("isImpK "+isImpK);
            mdFieldObjects[i][5]= isImpK;
        }
      }
      else
      { // when there is a table without PKs
      	for (int i=0; i<fieldCount; i++)
      	{
      	   mdFieldObjects[i][5]= "";
      	}
      }
  }  

  private String compareImpKsWithField(String fld)
  {
  	     String isImpK = "-" ;
  	    
  	    for (int i=0; i<impkCount; i++)
        {   System.out.println("compareImpK - field "+impkFieldNames[i]+'-'+fld);
        	if (impkFieldNames[i].equalsIgnoreCase(fld))
        	{
        		//isImpK = "true";
        		isImpK = impkImpNames[i];
        		//System.out.println(" is ImpK ["+i+"]"+impkFieldNames[i]+'-'+fld);
        		break; // important
        	}
        	else 
        	{
        		//System.out.println("field "+fld+" is not an imp key");
        		isImpK = "false";
                // not a break here    	    
        	}
        }
     return isImpK;
  }



/*  private void assignImpKeysToFields()
  {    
     if (impkCount >0)
     {
  	    for (int i=0; i<impkCount; i++)
        {   //System.out.println("assign"+'-'+impkFieldNames[i]);
            compareFieldsWithImpKs(impkFieldNames[i]);
        }
      }
      else
      { // when there is a table without ImpKs
      	for (int i=0; i<fieldCount; i++)
      	{
      	   mdFieldObjects[i][4]= "no ImpK";
      	   //System.out.println(" null ImpK");
      	}
      }
  }  
  private void compareFieldsWithImpKs(String impK)
  {
  	    boolean isImpK = false ;
  	    for (int i=0; i<fieldCount; i++)
        {   //System.out.print("comp ["+i+"]"+fieldNames[i]+'-'+impK);
        	if (fieldNames[i].equalsIgnoreCase(impK))
        	{
        		isImpK = true;
        		mdFieldObjects[i][4]= "true";
        		System.out.println(" is impK["+i+"]"+fieldNames[i]+'-'+impK);
        	}
        	else
        	{
        		isImpK = false;
         		mdFieldObjects[i][4]= "false";
        		//System.out.println(" is not impK");
        	}
        }
  	  
  }*/

    public String isDBrunning()
    {
       String ret="";
        try
        {
        Connection conn = DbConnection.getConnectionFromFile();
        //DriverManager.getConnection(dbUrl, dbUser, dbPass);

      //  DatabaseMetaData metaData = conn.getMetaData();
      //  String driverVersion = metaData.getDriverName()+" "+metaData.getDriverVersion();
           //System.out.println("drivers loaded"+System.getProperty("jdbc.drivers"));
          //running=true;
        }
        catch ( SQLException sqlex)
        {
               System.out.println("error:DatabaseMeta.isDBrunning:"+sqlex.getErrorCode()+" "+sqlex.getMessage());
               ret=sqlex.getErrorCode()+" "+sqlex.getMessage();
               //utilsGui.showMessageError(sqlex.getMessage());
        }
        
        return ret;
    
    }


 

  public int getFieldCount()     {  return fieldCount;  }

  public String[] getFieldNames()   { 	return fieldNames;  }
  
  public String getFieldName(int i)    { 	return fieldNames[i];  }
 
  public int getTableCount()    {  	return tableCount;  }
  
  public String[] getTableNames()    { 	return tableNames;  }
  
  public String getTableName(int i)    { 	return tableNames[i];  }
  
  public String[] getMdFieldNames()   {  	return mdFieldNames;  }

  public Object[][] getMdFieldObjects()   {    return  mdFieldObjects;  }

  
  
  public static void main(String[] args)
  {  //test
    DatabaseMeta dbMeta = new DatabaseMeta();
    String tbl = "invoice";
   /* db.retrieveTables();
    System.out.println(db.getTableName(1));
    System.out.println(db.getTableName(7));*/
     dbMeta.retrieveTables();
     dbMeta.retrieveFields(tbl);
     dbMeta.retrievePrimKs(tbl);
    dbMeta.retrieveImpKs(tbl);
  //  System.out.println(db.getFieldName(2));
  //  System.out.println(db.getMdFieldObjects());
    
    
    
  }
}