package com.tool.jdbc;

import java.util.logging.*;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DatabaseTableMeta
{

  private Connection con;
  private ResultSet rs;
  private DatabaseMetaData md;
  private ResultSetMetaData rsmd;  
  private ArrayList primKs;
  private ArrayList impKs;

  private DbImpKey dbImpKey;
  private DbPrimKey dbPrimKey;

   //Logger logger = Logger.getLogger("mine");


  public DatabaseTableMeta()
 { 
	   impKs = new ArrayList();
	   primKs = new ArrayList();    
 }

  public void retrievePrimKs(String tableName)
  {	
    //System.out.println(" suggestion  DatabaseTableMeta.retrievePrimKs '"+tableName+"'. Use dbFields property PK instead of this");
    try
    {
      primKs.clear();
      con = DbConnection.getConnectionFromFile();//get from connection factory
      DatabaseMetaData md = con.getMetaData();


      ResultSet rs;
      rs = md.getPrimaryKeys( null, null, tableName);
      int recs = 0;
      while(rs.next())
      {
           primKs.add(dbPrimKey = new DbPrimKey(rs.getString(4)));// 7 PKTABLE_NAME, 4 PKCOLUMN_NAME, 3 FKTABLE_NAME, 8 FKCOLUMN_NAME   
      	   recs++;
      }

      if(recs==0)
      {
      	//System.out.println("retrievePrimKs tableName "+tableName+" recs = 0");
      	
        rs = md.getPrimaryKeys( null, null, tableName.toUpperCase());
      //int recs = 0;
      while(rs.next())
      {
           primKs.add(dbPrimKey = new DbPrimKey(rs.getString(4)));// 7 PKTABLE_NAME, 4 PKCOLUMN_NAME, 3 FKTABLE_NAME, 8 FKCOLUMN_NAME   
      	   //recs++;
      }
      }
      
      /*if(!rs.next())// if nothing is returned try with caps
      {
      	System.out.println("retrievePrimKs tableName "+tableName+" TO UPPER CASE");
      	rs = md.getPrimaryKeys( null, null, tableName.toUpperCase());
      }*/

      while ( rs.next() )
      {  
           primKs.add(dbPrimKey = new DbPrimKey(rs.getString(4)));// 7 PKTABLE_NAME, 4 PKCOLUMN_NAME, 3 FKTABLE_NAME, 8 FKCOLUMN_NAME   
      }
       DbConnection.releaseConnection(con);
       
       
       System.out.println(" NOT TO BE USED DatabaseTableMeta.retrievePrimKs  primKs.size():"+primKs.size());
    }
    catch ( SQLException sqlex)
    {
        System.out.println(" error:DatabaseTableMeta.retPK "+sqlex.getMessage());
    }
  } 
  
  public String[] getPrimKeysNameArray()
  {
      String[] pk = new String[getCountOfPrimKeys()];
      for(int p=0;p<getCountOfPrimKeys();p++)
      {
          pk[p] = (getPrimKeyName(p));
      }
      
      return pk;
  }
  public boolean isColumnPrimKey(String columnName)
  {
  	boolean isPrimKey=false;	
     for(int c=0; c<getCountOfPrimKeys(); c++)
     {
     	if(columnName.toUpperCase().equalsIgnoreCase(getPrimKeyName(c).toUpperCase()))
     	{
     		isPrimKey=true;
     	}
     	
     }
    return isPrimKey;
  }
  
  public String getPrimKeyName(int i) // i starts from 0
  {
   	     DbPrimKey dbpk = (DbPrimKey)primKs.get(i);
   	     //System.out.println("DatabaseTableMeta.getPrimKeyName "+dbpk.fieldName);
   	     return dbpk.fieldName;
  }
    
  public int getCountOfPrimKeys()
  {
    return primKs.size();
  }
 
  public void retrieveImpKsOnQuery(String tableName,String query)
  {
   	tableName =tableName.toUpperCase();
    try
    {
      String[] queryColumns;
      String[] queryCols ={"",""};
      if (query!=null)
      {
        
         Database db = new Database();
   	     db.retrieveDBDataFromQuery(query,"DatabaseTableMeta.retrieveImpKsOnQuery");
   	     //rs=db.getRS();
   	     rsmd=db.getRSMetaData();
           // rsmd = db.retrieveRSMetaData(query);
           //System.out.println("databaseTablemeta.retrieveImpKsOnQuery "+rsmd.getColumnCount());
           queryCols = new String[rsmd.getColumnCount()];
           //System.out.println("databaseTablemeta.retrieveImpKsOnQuery "+queryCols);
           for (int c=1; c<= rsmd.getColumnCount();c++ )
           {
                queryCols[c-1] = rsmd.getColumnName(c);
           }	
      }
      
      queryColumns=queryCols;

      impKs.clear();	
      con = DbConnection.getConnectionFromFile();//get from connection factory
      DatabaseMetaData md = con.getMetaData();

      ResultSet rs = md.getImportedKeys( null, null, tableName);

//      if(!rs.next())// if nothing is returned try with caps
//      {
//      	rs = md.getImportedKeys( null, null, tableName.toUpperCase());
//      }

//      rs.beforeFirst();      
      //queryColNo=0
      
     //System.out.println("DatabaseTableMeta "+rs);     
      
      while ( rs.next() )
      { 
       //System.out.println("DatabaseTableMeta impKs "+rs.getString(4)+" "+rs.getString(3)+" "+rs.getString(4)); 
       if(query!=null)	
       {
        for (int qc=0; qc<queryColumns.length; qc++)
        {               
          //System.out.println("DatabaseTableMeta  impKs "+rs.getString(4));       	
            if (rs.getString(8).toUpperCase().equals(queryColumns[qc].toUpperCase()))
            {
             //System.out.println("DatabaseTableMeta.retrieveImpKsOnQuery for"+tableName+"."+rs.getString(4)+"-"+rs.getString(3)+"."+rs.getString(8));
             impKs.add(dbImpKey = new DbImpKey(rs.getString(4),rs.getString(3),rs.getString(8)));// 7 PKTABLE_NAME, 4 PKCOLUMN_NAME, 3 FKTABLE_NAME, 8 FKCOLUMN_NAME
            }
        }
       }
       else
       {   impKs.add(dbImpKey = new DbImpKey(rs.getString(4),rs.getString(3),rs.getString(4)));        }
      }

 /*      rs.first();
       impkFieldNames = new String[impkCount];
       impkImpNames = new String[impkCount];
       
  	    System.out.println("-- ImpKs "+impkCount);
  	    for (int i=0; i<impkCount; i++)
        {
          impkFieldNames[i] = rs.getString(8);
         // System.out.println("impk "+rs.getString("FKCOLUMN_NAME")+'.'+rs.getString("KEY_SEQ"));// 3 PKTABLE_NAME 4 PKCOLUMN_NAME 7 FKTABLE_NAME 8 FKCOLUMN_NAME 
          
          impkImpNames[i]= rs.getString(3)+'.'+rs.getString(4);
          System.out.println(i+" "+rs.getString(3)+'.'+rs.getString(4)+' '+rs.getString(7)+'.'+rs.getString(8));
        //   compareFieldsWithImpK(impkFieldNames[i]);
           rs.next();
        } */
 //      assignImpKsToFields(impkCount);
       DbConnection.releaseConnection(con);
    }
    catch ( SQLException sqlex)
    {
        System.out.println("error:DatabaseTableMeta.retrieveImpKsOnQuery:"+sqlex.getMessage());
    }
   
  }
   
   public int getCountOfImpKs()
   {
        return impKs.size();
   }
   
   
   
   public String findForeignTable(String field)
   {  // find fotreign table of field
   	  //System.out.println("size "+impKs.size());
   	  for(int i =0; i<impKs.size(); i++)
   	  {  
   	     DbImpKey dbik = (DbImpKey)impKs.get(i);
   	     
   	     /*if(field.equalsIgnoreCase("farmerIdForReceipt") || field.equalsIgnoreCase("farmerId"))
   	     {
   	     	System.out.println("DatabaseTableMeta.findForeignTable "+dbik.foreignTable+"."+dbik.foreignField+" for "+dbik.fieldName+"="+field);
   	     }*/
   	     
   	     if (dbik.foreignField.toUpperCase().equals(field.toUpperCase()))// non case sensitive search
   	     {
   	     	
   	     	return dbik.foreignTable;
   	     }     
   	  }
      return null;
   }


  
/*  private void assignImpKsToFields(int impkCount)
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
  }*/


    private class DbImpKey
    {
        public String fieldName;
        public String foreignTable;
        public String foreignField;

        public DbImpKey(String fieldNameIn, String foreignTableIn, String foreignFieldIn)
        {
          fieldName = fieldNameIn;
          foreignTable = foreignTableIn;
          foreignField = foreignFieldIn;
        }
           
        public String toString()
        {      return fieldName;     }
    }

    private class DbPrimKey
    {
        public String fieldName;

        public DbPrimKey(String fieldNameIn)
        {
          fieldName = fieldNameIn;
        }
           
        public String toString()
        {      return fieldName;     }
    }

  public static void main(String[] args)
  {  //test
    DatabaseTableMeta dbMeta = new DatabaseTableMeta();
   // String tbl = "invoice";
    String tbl = "BUYER";
//     dbMeta.retrieveFields(tbl);
  //   dbMeta.retrievePrimKs(tbl);
  //   System.out.println("no of pks "+dbMeta.getCountOfPrimKeys());
  //   System.out.println("pk 0"+ dbMeta.getPrimKeyName(0));
     
  //   System.out.println("is PrimKey "+dbMeta.isColumnPrimKey("buyerid"));

  //   for(int c=0; c<dbMeta.getCountOfPrimKeys(); c++)
  //   {
  //   	System.out.println("pimkey "+c+" "+dbMeta.getPrimKeyName(c));
  //   }
     
     
     
     //dbMeta.retrieveImpKsOnQuery(tbl,"SELECT buyerId,invoiceTypeId,invoiceNo,date,productId,value FROM invoice WHERE farmerId = 1 AND deliveryId = 1 AND year = 2005"); 
      dbMeta.retrieveImpKsOnQuery(tbl,"SELECT buyerId,buyerTitle, doyId FROM buyer"); 
     
     System.out.println("foreign table "+dbMeta.findForeignTable("doyId"));
        
    //System.out.println("no of pks "+dbMeta.getCountOfPrimKeys());
     //System.out.println("pk 0"+ dbMeta.getPrimKey(0));
 //   System.out.println(dbMeta.findForeignTable("buyerId"));
//    DbField fld = (DbField)fld;  
  }
}