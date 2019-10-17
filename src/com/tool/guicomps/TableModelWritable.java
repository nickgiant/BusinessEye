/*  cloned
 * 
 * // Complete Code Example:  http://www2.sys-con.com/itsg/virtualcd/Java/archives/0702/hendry/index.html
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.guicomps;


import com.tool.model.LookUpMgt;
import com.tool.model.EntityMessage;
import javax.swing.table.*;
import java.sql.*;
import java.util.Vector;
import java.util.Properties;

import com.tool.model.*;
import com.tool.gui.*;
import com.tool.jdbc.*;
import com.tool.utils.*;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.sql.Clob;
import java.sql.Blob;
import java.sql.SQLException;
import javax.swing.table.*;

import java.util.*;


/**
 *
 * @author sun
 */

/**
 *
 * @author sun
 */
public class TableModelWritable extends AbstractTableModel  implements Constants
{
Connection con;
Statement stat;
ResultSet rs;
int li_cols = 0;
Vector allRows;   // data from example
int row;
Vector newRow;
Vector colNames;
//String dbColNames[];
String headers[];
String pkValues[];
String tableName;
ResultSetMetaData rsmd;
String pKeyCol;
Vector deletedKeys;
Vector newRows;
boolean ibRowNew = false;
boolean ibRowInserted = false;

public String primKeyValue="";
public String query="";
private Database db;
private UtilsDouble utilsDouble;
// private ArrayList tableCols;
    private RecColumn recColumn;
    private RecColumnMgt recColumnMgt;
private Vector dataVector; //data from my code
private ArrayList tableCols;
private LookUpMgt lookUp;
private DatabaseTableMeta databaseTableMeta;
private EntityDBFields[] dbFieldsMany;
private boolean hasDataChanged=false;

 private int colCount;
    // perhaps these 3 need delete
    public String globalYear;   
    public String globalDeliveryId;
    public String globalCompanyId; 

    private int columnLookupTwoB=-1;
    private String[] primKeysMany;
    private String[] primKeysManyTran;
    private String[]      sql2WhereField;
    private String[]   sql2WhereValue; 
    private UtilsString utilsString;
    
   public TableModelWritable()
   {
       
            db= new Database();
     utilsDouble = new UtilsDouble();
     recColumnMgt = new  RecColumnMgt();
     //db= new Database();
     dataVector = new Vector();
     //dbColNames = new String[li_cols];
     allRows= new Vector();
     lookUp= new LookUpMgt();
     databaseTableMeta = new DatabaseTableMeta(); 
     recColumnMgt = new  RecColumnMgt();
     tableCols = new ArrayList();
        utilsString = new UtilsString();
       
     /* try{
        Class.forName("org.h2.Driver");
      }
      catch (ClassNotFoundException e){
            System.out.println("Cannot Load Driver!");
      }
        * 
        */
/*      try{
      	
     db= new Database();
     utilsDouble = new UtilsDouble();
     recColumnMgt = new  RecColumnMgt();
     //db= new Database();
     dataVector = new Vector();
     dbColNames = new String[li_cols];
     allRows= new Vector();
     lookUp= new LookUp();
     databaseTableMeta = new DatabaseTableMeta(); 
     recColumnMgt = new  RecColumnMgt();
     tableCols = new ArrayList();
     

      	
      	
      	
         
         con = DbConnection.getConnectionFromFile();//DriverManager.getConnection(url);
         stat = con.createStatement();
         rs = stat.executeQuery("SELECT * FROM bank");
         deletedKeys = new Vector();
         newRows = new Vector();
         rsmd = rs.getMetaData();
         tableName = rsmd.getTableName(1);
         li_cols = rsmd.getColumnCount();
         headers = new String[li_cols];
         for(int col = 0; col < li_cols; col ++){
             dbColNames[col]= rsmd.getColumnName(col + 1);
             headers[col] = rsmd.getColumnName(col + 1);
         }
         allRows = new Vector();
         System.out.println("---> rs "+rs);
         while(rs.next()){
            newRow = new Vector();
            for(int i = 1; i <= li_cols; i++){
               newRow.addElement(rs.getObject(i));
            } // for
            allRows.addElement(newRow);
         } // while
      } 
      catch(SQLException e){
         System.out.println(e.getMessage()+ " -----> allRows "+allRows);
      } */
   }
   
   
       
    public String executeUpdate(String query)
    {
    	String ret ="Update ";
    	int intRet =0;
    	try
    	{
            
         con = DbConnection.getConnectionFromFile();//DriverManager.getConnection(url);
         stat = con.createStatement();
        
            
    	intRet = stat.executeUpdate(query);
    	ret=ret + intRet;
    	}
        catch (SQLException ex) {
            System.out.println("DialogSetupDb.TableModelWritable.executeUpdate "+ex);
            ret=ex.toString();
        }
           
        return ret;
    }
   
   public String setQuery(String query)
   {
      
       try{  	
           
           
                 
          db.retrieveDBDataFromQuery(query,"TableModelWritable.setQuery");
   	  rs=db.getRS();
   	  rsmd=db.getRSMetaData();    
          
          tableName = rsmd.getTableName(1);
          

           
           
         
         //con = DbConnection.getConnectionFromFile();//DriverManager.getConnection(url);
         //stat = con.createStatement();
         //rs = stat.executeQuery(query);
         deletedKeys = new Vector();
         newRows = new Vector();
         //rsmd = rs.getMetaData();
         //System.out.println("---> rs "+rs+" rsmd "+rsmd);
         //tableName = rsmd.getTableName(1);
         colCount = rsmd.getColumnCount();
        tableCols.clear();
         headers = new String[li_cols];
         //System.out.println("---> cols "+li_cols);
         headers = new String[colCount];
         for(int col = 0; col < colCount; col ++)
         {
            //headers[col] = rsmd.getColumnName(col + 1);
            //System.out.println("---> cols "+col+" headers "+headers[col]);
            headers[col]= rsmd.getColumnName(col + 1);
            
                String columnName =rsmd.getColumnName(col + 1);
                String columnTable = rsmd.getTableName(col + 1);
                String colCaption=rsmd.getColumnLabel(col + 1);
                int columnLength=rsmd.getColumnDisplaySize(col + 1);
                int type = rsmd.getColumnType(col + 1);
                String className = rsmd.getColumnClassName(col+1);

            tableCols.add(recColumn = new RecColumn(columnName,columnTable,"",type,className,colCaption,columnLength));
         }
         
    
         int k=0;
 //        rs.beforeFirst() ;
        allRows.clear();
         while(rs.next())
         {
             k++;
             Object[] record = new Object[colCount];
             
            //newRow = new Vector();
            for(int i = 1; i <= colCount; i++)
            {
               //System.out.println("---> k"+k+" i"+i);
                newRow.addElement(rs.getObject(i));
                record[i-1]=rs.getObject(i);
            } // for
            allRows.addElement(newRow);
            dataVector.addElement(record);
            System.out.println("TableModelWritable.setQuery allRows "+allRows.size());
         } // while
         
             
        fireTableChanged(null); // notify everyone that we have a new table.
         
      } 
      catch(SQLException e){
         System.out.println("error TableModelWritable.setQuery "+e.getMessage()+ "---> allRows "+allRows);
      } 
       closeDB();
       return query;
   }
   
   
   
   /*public void setQuery(String queryIn,String entity,EntityDBFields[] dbFieldsManyIn,String[] primKeysManyIn,String[] primKeysManyTranIn, String[] sql2WhereFieldIn, String[] sql2WhereValueIn,  String primKeyValueIn)
   {
       query=queryIn;
       primKeyValue=primKeyValueIn;
       dbFieldsMany=dbFieldsManyIn;
       primKeysMany=primKeysManyIn;
       primKeysManyTran=primKeysManyTranIn;
       sql2WhereField=sql2WhereFieldIn;
       sql2WhereValue=sql2WhereValueIn;
         
       deletedKeys = new Vector();
         newRows = new Vector();
             
     if (VariablesGlobal.globalShowSQL)
     {  
      System.out.println("tableModelWritable.setQuery: "+query);   
     }

                    
     System.out.println(" tableModelWritable.setQuery(more than one param) "+allRows+" dbFieldsMany.length="+dbFieldsMany.length+" entity:"+entity+"  query:"+query+". primKeyValue " +primKeyValue);
   
          
      int noOfColumns = 0;
      
      for (int h = 0; h < dbFieldsMany.length; h++)
      {
                //System.out.println("h "+h+" dbHeadNo "+dbHeadNo);
                String columnName =dbFieldsMany[h].getDbField();//rsmd.getColumnName(h+1);
                //System.out.println("tableModelWritable.setQuery columnName --- "+columnName);
                //String colCaption=rsmd.getColumnLabel(dbHeadNo);
                String ft =null;
                //int columnLength=rsmd.getColumnDisplaySize(dbHeadNo);
                   //if foreign table = null assign



                  if (dbFieldsMany[h].getLookupEntityName()==null) //&&(!rsmd.getTableName(i).equalsIgnoreCase(entity)))
                  {
                    ft =entity; //rsmd.getTableName(i);
                  //System.out.println("panelODORData.setEntity if ft :"+ft);
                  }
                  else
                  {
                  	//System.out.println("tableModelWritable.setQuery else ft for "+dbFieldsMany[h].getLookupEntityName());
                  	ft = lookUp.getTable(dbFieldsMany[h].getLookupEntityName());
                	//ft =databaseTableMeta.findForeignTable(columnName) ; 
                	
                  }
                // if has foreign key then calculate

               //  ft=entity;

                
              if(dbFieldsMany[h].getLookupEntityName()!=null)
              {
                if(lookUp.getLookUpIntNoOfColsWhenInTable(dbFieldsMany[h].getLookupEntityName())==2)
                {
                	noOfColumns =noOfColumns+1;
                	//System.out.println("tableModelWritable.setQuery 2 "+columnName+" "+ft+" for "+dbFieldsMany[h].getLookupEntityName());
                }
                else if(lookUp.getLookUpIntNoOfColsWhenInTable(dbFieldsMany[h].getLookupEntityName())==1)
                {
                	//noOfColumns =noOfColumns+1;
                	//System.out.println("tableModelWritable.setQuery 1 "+columnName);
                }
                else
                {
                	//noOfColumns =noOfColumns+1;
                	//System.out.println("tableModelWritable.setQuery getLookUpIntNoOfColsWhenInTable "+lookUp.getLookUpIntNoOfColsWhenInTable(ft));
                }
              }
                //String foreignTable = ft; 
         }       
                
      
      
          db.retrieveDBDataFromQuery(query,"TableModelWritable.setQuery");
   	  rs=db.getRS();
   	  rsmd=db.getRSMetaData();    

         
      databaseTableMeta.retrieveImpKsOnQuery(entity,query);
      databaseTableMeta.retrievePrimKs(entity);
      
      //colCount = dbFieldsMany.length; ////noOfColumns+rsmd.getColumnCount();//+databaseTableMeta.getCountOfImpKs();

      
      try
      {
      
      tableName = dbFieldsMany[0].getTableName(); //rsmd.getTableName(1);
      colCount = dbFieldsMany.length; //rsmd.getColumnCount();
      tableCols.clear();
      headers =new String[colCount];

          
      // rebuild the headers array with the new column names
      headers = new String[colCount];
      int dbHeadNo = 1;

      
      
      for (int h = 0; h < colCount; h++)
      {
                // System.out.println("tableModelWritable. h "+h+" dbHeadNo "+dbHeadNo+" col count"+colCount);
                String columnName =dbFieldsMany[h].getDbField();// rsmd.getColumnName(dbHeadNo);
                String columnTable = dbFieldsMany[h].getTableName();  //rsmd.getTableName(dbHeadNo);
                String colCaption=dbFieldsMany[h].getCaption();// rsmd.getColumnLabel(dbHeadNo);
            //System.out.println("tableModelWritable.setQuery h("+h+") ("+dbHeadNo+") columnName "+columnName+"   colCount:"+colCount);
                int type = rsmd.getColumnType(dbHeadNo);
                String colClassName = dbFieldsMany[h].getColClassName();
                int columnLength=dbFieldsMany[h].getColWidth();// rsmd.getColumnDisplaySize(dbHeadNo);
                String ft;   //if foreign table = null assign
                if (dbFieldsMany[h].getLookupEntityName()==null)    //&&(!rsmd.getTableName(i).equalsIgnoreCase(entity)))
                {
                  ft =entity; //rsmd.getTableName(i);
                  //System.out.println("panelODORData.setEntity if ft :"+ft);
                }
                else
                {
                	ft =dbFieldsMany[h].getLookupEntityName() ; 
                	//System.out.println("tableModelWritable.setQuery else ft :"+ft);
                }
                // if has foreign key then calculate

               
            //    if (!isEditable)
            //    {   ft=entity;       }
                
                final String foreignTable = ft; 
          
                 
                 // if has 2 columns as lookup
                if ((foreignTable!= null) && (!foreignTable.toUpperCase().equals(entity.toUpperCase()))&& (lookUp.getLookUpIntNoOfColsWhenInTable(foreignTable)==2))//(!rsmd.getTableName(i).equalsIgnoreCase(entity)))
                {

                   //System.out.println("tableModelWritable.setQuery columnName show "+columnName);

                    colCaption = lookUp.getLookUpKeyTranslation(foreignTable);//columnName;
                    headers[h]= colCaption;
                    
                    String colNameForeign = lookUp.getLookUpLabel(foreignTable);//lookUp.getLookUpLabel(foreignTable);
                    String colCaptionForeign = colNameForeign;
                    headers[h+1] = colCaptionForeign;


                   
           tableCols.add(recColumn = new RecColumn(columnName,columnTable,foreignTable,type,colClassName,colCaption,columnLength));
           tableCols.add(recColumn = new RecColumn(colNameForeign,foreignTable,"",0,"java.lang.Object",colCaptionForeign,40));
                 //System.out.println("tableModelWritable.setQuery 2cols------> h"+h+" columnName"+columnName+" foreignTable"+foreignTable);
                   //System.out.println("tableModelWritable.setQuery  ++2"+columnName+" "+h);
                   h=h+1;
                   //dbHeadNo=dbHeadNo+1;
       
                }  // if has 1 column as lookup
                else if ((foreignTable!= null) && (!foreignTable.toUpperCase().equals(entity.toUpperCase())) && lookUp.getLookUpIntNoOfColsWhenInTable(foreignTable)==1)//(!rsmd.getTableName(i).equalsIgnoreCase(entity)))
                {
                    colCaption = lookUp.getLookUpKeyTranslation(foreignTable);//columnName;
                    headers[h-1]= colCaption;
                    
                    String colNameForeign = lookUp.getLookUpLabel(foreignTable);//lookUp.getLookUpLabel(foreignTable);
                    String colCaptionForeign = colNameForeign;
                    headers[h] = colCaptionForeign;
                    
                  //  System.out.println("tableModelWritable.setQuery columnName 1 "+columnName+" "+dbHeadNo);
                  // String colCaption = columnName;
                   
                   //int type = rsmd.getColumnType(dbHeadNo);
       tableCols.add(recColumn = new RecColumn(columnName,entity,foreignTable,type,colClassName,colCaptionForeign,columnLength));
                  //System.out.println("tableModelWritable.setQuery 1col------> h"+h+" columnName"+columnName+" foreignTable"+foreignTable);
                   //tableCols.add(recColumn = new RecColumn(columnName,entity,"",type,colCaption,columnLength));
                   //System.out.println("tableModelWritable.setQuery  ++1"+columnName+" "+h);
                  //  dbHeadNo=dbHeadNo+1;                	
                }
                else
                {
                	//System.out.println("tableModelWritable.setQuery columnTable else "+columnTable+" "+columnName);
                   //System.out.println("tableModelWritable.setQuery columnName "+columnName+rsmd.getColumnLabel(dbHeadNo)+rsmd.getColumnName(dbHeadNo));
                   headers[h] = columnName;
                  // String colCaption = columnName;
                   
                  // int type = rsmd.getColumnType(dbHeadNo);
        tableCols.add(recColumn = new RecColumn(columnName,columnTable,"",type,colClassName,colCaption,columnLength));
        //System.out.println("tableModelWritable.setQuery ooooo> h"+h+" columnName"+columnName+" foreignTable"+foreignTable);                 
               //     dbHeadNo=dbHeadNo+1;
                   //System.out.println("tableModelWritable.setQuery  ++else"+columnName+" "+h);
                
                }
                
                dbHeadNo++;
                
                System.out.println("tableModelWritable.setQuery  SIZE "+tableCols.size());
                
      }
      System.out.println("tableModelWritable.setQuery  SIZE "+tableCols.size()+" colCount"+colCount);
     } // try
      catch(SQLException e)
      {
         System.out.println("error  TableModelWritable.setQuery (columns) "+e.getMessage());
         e.printStackTrace();
      } 

      
  try
    {  
      // and file the data with the records from our query. This would
      // not be
      // practical if we were expecting a few million records in response
      // to our
      // query, but we aren't, so we can do this.
      int row =1;
//old      initialDataVector = new Vector();
      
      allRows.clear(); 
      dataVector.clear();
      while (rs.next())
      {
      	int dbColNo = 1;
        Object[] record = new Object[colCount];

        newRow = new Vector();
        for (int i = 0; i < colCount; i++) // for each field
        {
             //System.out.println("h "+h+" dbHeadNo "+dbHeadNo);
             String columnName =dbFieldsMany[i].getDbField();//rsmd.getColumnName(dbColNo);
             Object columnData =rs.getObject(dbColNo);
             
              
             
      //      System.out.println("tableModelWritable.setQuery i"+i+"  dbColNo:"+dbColNo+" column:"+columnName+":"+columnData+"   colCount"+colCount);
             if(columnName.toUpperCase().equals(VariablesGlobal.columnNameInc.toUpperCase()) && (columnData==null || columnData.equals("")))
             {
             	int v =getRowCount()+1;
             	
             	columnData = v+"";
             }
             
             String ft;   //if foreign table = null assign
             if ((databaseTableMeta.findForeignTable(columnName)==null) )//&&(!rsmd.getTableName(i).equalsIgnoreCase(entity)))
             {
                  ft =entity; //rsmd.getTableName(i);
                  //System.out.println("panelODORData.setEntity if ft :"+ft);
             }
             else
             {
               	ft =databaseTableMeta.findForeignTable(columnName) ; 
                	//System.out.println("panelODORData.setEntity else ft :"+ft);
             }
             // if has foreign key then calculate

             final String foreignTable = ft;  

             
             // System.out.println("foreignTable ["+foreignTable+"] "+dbColNo+" - r "+row);            
             
             if ((foreignTable!= null) && (!foreignTable.toUpperCase().equalsIgnoreCase(entity.toUpperCase())))//(!rsmd.getTableName(i).equalsIgnoreCase(entity)))
             {
                	

                ResultSet rsForeign;
                String foreignQuery="";
                String lookupText="";
                
                
                int k = rs.getInt(dbColNo);
                if (k!=0)
                {
                  	//System.out.println("3 - -col "+i);

                 
                   foreignQuery = "SELECT * FROM "+foreignTable+" WHERE "+lookUp.getLookUpKey(foreignTable)+" = "+rs.getInt(dbColNo);  //   --------    is it to make it getString ?   
                   //System.out.println("tableModelWritable.foreignQuery "+row+" "+foreignQuery);
   	               db.retrieveDBDataFromQuery(foreignQuery,"TableModelWritable.setQuery");
   	               rsForeign=db.getRS();
   	                                
                   
              
                   rsForeign = db.retrieveRow(rsForeign, 1);// go to the only row 
                   //System.out.println("tableModelWritable.setQuery "+columnData+" - "+columnName+" - r "+row+" foreignTable "+foreignTable);
               //    System.out.println("tableModelWritable.setQuery "+columnName+" "+lookUp.getLookUpFieldIndex(foreignTable)+" "+foreignQuery);
               //    System.out.println("tableModelWritable.setQuery "+foreignTable+" "+notShowFieldsFromThisInQuery2);
                   if ( foreignTable!=null )
                   {
                       //System.out.println("tableModelWritable.setQuery foreignTable"+foreignTable);
                       lookupText = rsForeign.getString(lookUp.getLookUpFieldIndex(foreignTable)) ;// get field data
                   }
                     
                   //System.out.println("tableModelWritable.setQuery "+lookupText);
                 }// if (k!=0)
              
                record[i]=columnData;
                
                newRow.addElement(columnData);
               // System.out.println("tableModelWritable.setQuery A"+i+" "+columnName+":"+columnData);
                
                 if(lookUp.getLookUpIntNoOfColsWhenInTable(foreignTable)==2)  // for lookup column
                 {
                   record[i+1]=lookupText;
                    newRow.addElement(lookupText);
                    //System.out.println("tableModelWritable.setQuery B"+i+" "+columnName+":"+columnData);
                    
           
                   i=i+1;
                     //dbColNo=dbColNo+1;                 	
                 }
                 else if(lookUp.getLookUpIntNoOfColsWhenInTable(foreignTable)==1)  // for lookup column
                 {
                     newRow.addElement(columnData);
                     //System.out.println("tableModelWritable.setQuery C"+i+" "+columnName+":"+columnData);
                
                 	//dbColNo=dbColNo+1;
                 }
                 else
                 {
                 	
                 	//System.out.println("tableModelWritable.setQuery else  -"+columnData+" - r "+row+" i"+i);
                 	//dbColNo=dbColNo+1;
                 }
                dbColNo++; 
                
             }  // if has foreign table
             else
             {
                    record[i]=columnData; 
                   newRow.addElement(columnData);
                  // System.out.println("tableModelWritable.setQuery D"+i+" "+columnName+":"+columnData);
                    
          //         System.out.println("tableModelWritable.setQuery else  -"+columnData+" - r "+row+" i"+i);
                dbColNo++;
             }
          
         //record[i] = rs.getString(i + 1);
        }
        //System.out.println("tableModelWritable.setQuery record.length  "+record.length);
       
        dataVector.addElement(record);
        allRows.addElement(newRow);
        //System.out.println("tableModelWritable.setQuery(more than one params) allRows.size:"+ allRows.size()+" record.length:"+record.length+" dataVector.size:"+dataVector.size());
        
        //dataVector.addElement(record);
        row=row+1;  
//old        initialDataVector.addElement(record);
      }
     fireTableChanged(null); // notify everyone that we have a new table.
     // look for tblModelListenerGeneric in PanelODMRData(currently disabled) line 1356
      //not needed because fireTableCellUpdated is called in setValueAt
      
  

    }
    catch (SQLException e)
    {
      allRows = new Vector(); // blank it out and keep going.
       dataVector = new Vector();
      System.out.println("TableModelWritable.setQuery (data)"+e);
      e.printStackTrace();
      closeDB();
    }
    
    
 
      

//       setTableInitialState();	

      
   
      
      
      

      
      

/*  
      
       allRows.clear();
        try{  	
         
         con = DbConnection.getConnectionFromFile();//DriverManager.getConnection(url);
         stat = con.createStatement();
         rs = stat.executeQuery(query);
         deletedKeys = new Vector();
         newRows = new Vector();
         rsmd = rs.getMetaData();
         //System.out.println("tableModelWritable.setQuery rs "+rs+" rsmd "+rsmd);
         tableName = rsmd.getTableName(1);
         li_cols = rsmd.getColumnCount();
         headers = new String[li_cols];
         //System.out.println("---> cols "+li_cols);
         for(int col = 0; col < li_cols; col ++){
            headers[col] = rsmd.getColumnName(col + 1);
            //System.out.println("---> cols "+col+" headers "+headers[col]);
         }
         
         
         int k=0;
 //        rs.beforeFirst() ;
         //System.out.println("tableModelWritable.setQuery +rs "+rs+"  allRows "+allRows);
         
         while(rs.next()) // k:row i:column
         {
             k++;
             
            newRow = new Vector();
            for(int i = 1; i <= li_cols; i++)
            {
               System.out.println("tableModelWritable.setQuery k row"+k+" i col"+i);
                newRow.addElement(rs.getObject(i));
            } // for
            allRows.addElement(newRow);
         } // while
         
         
 //         fireTableChanged(null); // notify everyone that we have a new table.
         
      } 
      catch(SQLException e)
      {
         System.out.println("error  tableModelWritable.setQuery "+e.getMessage()+ "---> allRows "+allRows);
      } 

         //   call releaseConnectionRsAndRsmd
         //db.releaseConnectionRs();
         //   db.releaseConnectionRsmd();
         
//          fireTableChanged(null); // notify everyone that we have a new table.
         

       
       
       
       
       
   }*/
   
   
     // called by PanelOneDataManyRecData.retrieveDataFromWritableTable
  public void setColumnLookUpTwoB(int col)
  {
  	columnLookupTwoB = col;
  }
   
   // called in PanelODMRData
   public void closeDB()
   {
            db.releaseConnectionRs();
            db.releaseConnectionRsmd();
   }


   
     public void addEmptyRow(int row)
     {
       int colCount= getColumnCount();
        String[] record = new String[colCount];
        for (int i = 0; i < colCount; i++)// for each field
        {  
           //System.out.println("tableModelWritable.addEmptyRow Integer i"+i+" colCount"+colCount);
           if (getColumnClass(i)== Integer.class)
           {  
              //System.out.println("tableModelWritable.addEmptyRow Integer i"+i);
              record[i] = "0";
              
             if(getColumnDBName(i).toUpperCase().equals(VariablesGlobal.columnNameInc.toUpperCase()) )
             {
             	
             	if(row!=getRowCount()) // if inserted in between
             	{
    //                renumberColumnAA(row,+2);
             		
             		
             		int v =row+1;
             		record[i] = v+"";              		
             		
             		
             	}
             	else
             	{
             		int v =getRowCount()+1; // if inserted last
             		record[i] = v+""; 
             	}
             	
             	    	
             }
              
           }           
           else if (getColumnClass(i)== Double.class)
           {  
              //System.out.println("tableModelWritable.addEmptyRow Double i"+i);
              record[i] = "0.0";
           }
           else
           {  record[i] = "";}
        }
        dataVector.insertElementAt(record,row);
        
        fireTableRowsInserted(row, row);
        
     }   
   
       
     public void deleteTableRow(int row)
    {
    	//String[] record = new String[colCount];
    	dataVector.remove(row);
    	fireTableRowsDeleted(row,row);
//    	renumberColumnAA(row,1);
    }
    
    public void deleteEmptyRow(int row)
    {
    	//String[] record = new String[colCount];
    	dataVector.remove(row);
    	fireTableRowsDeleted(row,row);
//        renumberColumnAA(row,1);
    }
   
   
       
    public int getFindEmptyRow()
    {
    	int row = -1;
    	for (int r=0;r<this.getRowCount();r++)
    	{
    		if(isRowEmpty(r))
    		{
    			row = r;
    		}
    	}
    	
    	return row;
    }

   
      public boolean isRowEmpty(int row)
     {
     	
         if (dataVector.size() == 0) return false;
         boolean is = false;
         boolean[] hasArray = new boolean[getColumnCount()];
         String[] record = new String[getColumnCount()];
         for (int c =0; c<getColumnCount();c++)
         {
         	//System.out.println("tableModelWritable.hasEmptyRow "+getColumnCount()+" "+c+getColumnClass(c));
         	 
         	 
         	 Object value = getValueAt(row, c);
         	 if (value !=null)
         	 {
         	 	
         	 //	Double.valueOf(value.toString()).doubleValue();
         	 	
         	 	


                 //System.out.println("tableModelWritable.hasEmptyRow values '"+value+"'");
                 if (value.toString().trim().equals("") && getColumnClass(c)== String.class)
                 { 
                     //System.out.println("tableModelWritable.hasEmptyRow values String '"+value+"'");
                     is =  true  ;
                 }
/* --------                else if ((value.toString().trim().equals("0") || getColumnDBName(c).toUpperCase().equals(VariablesGlobal.columnNameInc.toUpperCase()) )&& getColumnClass(c)== Integer.class)
 comment getColumnDBName                {  
                    //System.out.println("tableModelWritable.hasEmptyRow values Integer '"+value+"'");
                    is = true ;
                }    */  
                 else if (utilsDouble.getDoubleReading(value,false).equals(utilsDouble.getDoubleReading("0",false)) && getColumnClass(c)== Double.class)
                 {  
                    //System.out.println("tableModelWritable.hasEmptyRow values Double '"+value+"'");
                    is = true ;
                 }                 
                 else if (value.toString().trim().equals("") && getColumnClass(c)== Object.class)
                 {  
                    //System.out.println("tableModelWritable.hasEmptyRow values Object '"+value+"'");
                    is = true ;
                 }                 
                 else if (value.toString().trim().equals("") && getColumnClass(c)== java.util.Date.class)
                 {  
                    //System.out.println("tableModelWritable.hasEmptyRow values Date '"+value+"'");
                    is = true ;
                 }             
                 else
                 { 
                    is = false ;
                    //System.out.println("tableModelWritable.hasEmptyRow values else '"+value+"'"+"getColumnClass(c)"+getColumnClass(c));
                    //System.out.println("tableModelWritable.hasEmptyRow value"+value.toString());
                    //System.out.println("tableModelWritable.hasEmptyRow has empty row false");     
                    break;
                 }
                 
             }
         } 
         return is;
     }
   
   
    public boolean hasEmptyRow()
    {
        System.out.println("TableModelWritable.hasEmptyRow allRows"+allRows);
    	boolean has =false;
    	for(int i =0;i<dataVector.size();i++)
    	{
    		if(isRowEmpty(i))
    		{
    			has=true;
    			break;
    		}
    		else
    		{
    		    has=false;	
    		}
    	}
    	
    	return has;
    }
   
   
    public void deleteEmptyRowIfThereIsOne()
    {
    	if(hasEmptyRow() && getRowCount()!=1) // if there is only one not delete
    	{
    		deleteEmptyRow(getFindEmptyRow());
    	}
    	
    }
   
   
   public ArrayList checkIfFieldsAreUncompleted()
   {
   	
   	deleteEmptyRowIfThereIsOne();
   	ArrayList listMessages = new ArrayList();
   	 boolean ret = false;
   	 String output="";
   	 //if(fieldTxts!=null && dbFieldsMany!=null && fieldTxts.size()==dbFieldsMany.length )
   	 if(dbFieldsMany!=null && dbFieldsMany.length==getColumnCount())
   	 {

        

   	   for(int r =0;r<getRowCount();r++)  // rows
   	   {
   	   	
   	   
   	    for(int c=0;c<getColumnCount();c++) // columns
   	    {

   	    	   //RecColumn rCol = (RecColumn)tableCols.get(c);
              Class columnType = getColumnClass(c); 
   	    	    

               if(dbFieldsMany[c].getFieldObligatoryOrSuggest()==FIELD_OBLIGATORY)
               {
               	//System.out.println("tableModelWritable.checkIfFieldsAreUncompleted obl "+columnType+" c"+c+" r"+r+" "+getValueAt(r,c).toString().trim());
                 String text = getValueAt(r,c).toString().trim();
                 if (columnType== Integer.class)
                 {  
               	  	   if(text.equals("0"))        	
               	  	   { 
               	  	   	 EntityMessage em =new EntityMessage(dbFieldsMany[c].getCaption(),FIELD_OBLIGATORY,r+1,c,dbFieldsMany[c].getChildTable());
               	  	   	 listMessages.add(em);               	  	   	  
                         // outputObligatory=outputObligatory+outputObligatoryFields+" "+dbFieldsMany[c].getCaption()+" στη γραμμή "+(r+1)+outputObligatoryFieldsEnd+"\n";               	  	   	
               	         // System.out.println("tableModelWritable.checkIfFieldsAreUncompleted obligatory r"+r+" c"+c+" "+dbFieldsMany[c].getDbField()+" is empty ");
               	  	   }                 	
                 }           
                 else if (columnType== Double.class)
                 {
               	  	   if(utilsDouble.getDoubleReading(text,false).equals(utilsDouble.getDoubleReading("0",false)))        	
               	  	   { 
               	  	   	 EntityMessage em =new EntityMessage(dbFieldsMany[c].getCaption(),FIELD_OBLIGATORY,r+1,c,dbFieldsMany[c].getChildTable());
               	  	   	 listMessages.add(em);                	  	   	
               	  	   	 //outputObligatory=outputObligatory+outputObligatoryFields+" "+dbFieldsMany[c].getCaption()+" στη γραμμή "+(r+1)+outputObligatoryFieldsEnd+"\n";
               	         // System.out.println("tableModelWritable.checkIfFieldsAreUncompleted obligatory r"+r+" c"+c+" "+dbFieldsMany[c].getDbField()+" is empty ");
               	  	   }                        	
                 }
                 else
                 {
                 	
       
               	  	   if(text.equals(""))        	
               	  	   {               
 	   	
	                   /* //final int x = jt.getWidth()  - 18;
                        //jt.setMargin(new Insets(0,0, 0,  0));
                        lblIcoAttention = new JLabel(ICO_FIELDATTENTION);
	                    lblIcoAttention.setBounds(2,2,18,18);
	                    ta.add(lblIcoAttention);
               	  	   	ta.revalidate();  
               	  	   	this.revalidate();
               	  	   	lblIcoAttention.revalidate(); */ 
               	  	   	 EntityMessage em =new EntityMessage(dbFieldsMany[c].getCaption(),FIELD_OBLIGATORY,r+1,c,dbFieldsMany[c].getChildTable());
               	  	   	 listMessages.add(em);                	  	   		
                         // outputObligatory=outputObligatory+outputObligatoryFields+" "+dbFieldsMany[c].getCaption()+" στη γραμμή "+(r+1)+outputObligatoryFieldsEnd+"\n";               	  	   			     
               	         // System.out.println("tableModelWritable.checkIfFieldsAreUncompleted obligatory r"+r+" c"+c+" "+dbFieldsMany[c].getDbField()+" is empty ");
               	  	   }
               	 }
               	  
               }
               else if(dbFieldsMany[c].getFieldObligatoryOrSuggest()==FIELD_SUGGEST)
               {
               	 String text = getValueAt(r,c).toString().trim();
                 if (columnType== Integer.class)
                 {  
               	  	   if(text.equals("0"))        	
               	  	   { 
               	  	   	 EntityMessage em =new EntityMessage(dbFieldsMany[c].getCaption(),FIELD_SUGGEST,r+1,c,dbFieldsMany[c].getChildTable());
               	  	   	 listMessages.add(em);                	  	   
               	  	   	//outputSuggest=outputSuggest+outputSuggestFields+" "+dbFieldsMany[c].getCaption()+" στη γραμμή "+(r+1)+outputSuggestFieldsEnd+"\n";	  	   	
               	  	   	// System.out.println("tableModelWritable.checkIfFieldsAreUncompleted suggested r"+r+" c"+c+" "+dbFieldsMany[c].getDbField()+" is empty ");
               	  	   	
               	  	   }                 	
                 }           
                 else if (columnType== Double.class)
                 {
               	  	   if(utilsDouble.getDoubleReading(text,false).equals(utilsDouble.getDoubleReading("0",false)))      	
               	  	   { 
               	  	   	 EntityMessage em =new EntityMessage(dbFieldsMany[c].getCaption(),FIELD_SUGGEST,r+1,c,dbFieldsMany[c].getChildTable());
               	  	   	 listMessages.add(em);                  	  	   	
               	  	   	//outputSuggest=outputSuggest+outputSuggestFields+" "+dbFieldsMany[c].getCaption()+" στη γραμμή "+(r+1)+outputSuggestFieldsEnd+"\n";	  	   	
               	  	   	 //System.out.println("tableModelWritable.checkIfFieldsAreUncompleted suggested r"+r+" c"+c+" "+dbFieldsMany[c].getDbField()+" is empty ");
               	  	   }                 	
                 }
                 else
                 {

               	  	   if(text.equals(""))        	
               	  	   {
               	  	   	/*lblIcoAttention = new JLabel(ICO_FIELDATTENTION);
	                    lblIcoAttention.setBounds(2,2,18,18);
	                    ta.add(lblIcoAttention); 
	                    ta.revalidate();   
	                    this.revalidate();  
	                    lblIcoAttention.revalidate(); */ 
               	  	   	 EntityMessage em =new EntityMessage(dbFieldsMany[c].getCaption(),FIELD_SUGGEST,r+1,c,dbFieldsMany[c].getChildTable());
               	  	   	 listMessages.add(em);   	                    	
	                     //outputSuggest=outputSuggest+outputSuggestFields+" "+dbFieldsMany[c].getCaption()+" στη γραμμή "+(r+1)+outputSuggestFieldsEnd+"\n";	  	   	       	  	   	
               	  	   	 //System.out.println("tableModelWritable.checkIfFieldsAreUncompleted suggested r"+r+" c"+c+" "+dbFieldsMany[c].getDbField()+" is empty ");
               	  	   }
               	   
               	  }
               }               
               	
   	    	
   	 	
   	    }// columns
   	   }// rows
   	   
        
        
       // System.out.println("PanelODORData.checkIfFieldsAreUncompleted \n"+output);   	   
   	 }
   	 else
   	 {
   	 	System.out.println("TableModelWritable.checkIfFieldsAreUncompleted getColumnCount and dbFieldsMany not have equal length");
   	 }   	 
   	 
   	 return listMessages;
   }
   
   
      public String getColumnTable(int c)
   {  
   
   // return recColumnMgt.getColumnCaption(c);  // doesnt work
      RecColumn col = (RecColumn)tableCols.get(c);  //
       
    
       
      return col.getColumnTable();
       
       
   }
   
   
    public String getColumnDBName(int col)  
    {
      //return headers[col];
      
      RecColumn rcol = (RecColumn)tableCols.get(col); 
       
    
       
      return rcol.getColumnName();      
      
      
   }  
   
   public int getColumnLength(int c)
   {
        // return recColumnMgt.getColumnLength(c); // doesnt work
       RecColumn col = (RecColumn)tableCols.get(c);
       return col.getColumnLength();
       
       //return headers[c].length();
   }
   
   
   public boolean getHasDataChanged()
  {
  	return hasDataChanged;
  }
 
     // needed for getQuery2
  public void setWHereFieldAndValue(String[]sql2WhereFieldIn,String[]sql2WhereValueIn,String globalYearIn,String globalCompanyIdIn,String globalDeliveryIdIn)
  {
//-----  	sql2WhereField=sql2WhereFieldIn;
//----  	sql2WhereValue=sql2WhereValueIn;
        
  	globalYear=globalYearIn;
  	globalCompanyId=globalCompanyIdIn;
  	globalDeliveryId=globalDeliveryIdIn;
  	
  }
   
   @Override 
   public Class getColumnClass(int column){
   //   return getValueAt(0,col).getClass();
  int type;

                RecColumn col = (RecColumn)tableCols.get(column);
                type =  col.getColumnType();            	


        //System.out.println("TableModelReadOnly.getColumnClass col"+column+" type "+type+" int"+Types.INTEGER+" varchar"+Types.VARCHAR);

        switch(type) {
        case Types.CHAR:
        case Types.VARCHAR:
        case Types.LONGVARCHAR:
            return String.class;

        case Types.BIT:
        case Types.BOOLEAN:
            return Boolean.class;

        case Types.TINYINT:
        case Types.SMALLINT:
        case Types.INTEGER:   
          return Integer.class;
        
            //return Number.class;

        case Types.BIGINT:
            return Long.class;

        case Types.FLOAT:
        case Types.DOUBLE:
        case Types.DECIMAL: //    added by me
            
             //System.out.println("Double");
             return Double.class;        
        

        case Types.DATE:
             //return java.sql.Date.class; //there has also to be a table renderer inpanelODMRData
             return java.util.Date.class;
            //return String.class;
                    
        // LONGVARBINARY for longblob

       case Types.LONGVARBINARY :
	   case Types.VARBINARY :
	        return  byte[].class;   //  Blob.class   byte[].class
	        
	        
	   	case Types.BLOB :
			return Blob.class  ;
				

		case Types.CLOB :
			return Clob.class  ;

        
        default:
            return Object.class;
        }
        
       //return Object.class;
   }
   
   @Override
   public boolean isCellEditable(int row, int col){
      if (ibRowNew){
         return true;
      }
      if (col == 0){
         return  false;
      } else {
         return true;
      }
   }

    @Override
   public String getColumnName(int c)
   {  
   
   // return recColumnMgt.getColumnCaption(c);  // doesnt work
      RecColumn col = (RecColumn)tableCols.get(c);
      return col.getColumnCaption();       
   }


   
   @Override
   public int getRowCount(){
      return dataVector.size();
   } 
   
   @Override
   public int getColumnCount()
   {
      //return li_cols;
       return colCount;
   }
   
   
   
   @Override
   public Object getValueAt(int r, int c)
   {
        //Vector row = (Vector)dataVector.elementAt(r);
        //return row.elementAt(c);
      //System.out.println("tableModelWritable.getValueAt "+r+" "+c+" size "+dataVector.size());
      
     Object ret = null;
     if(dataVector.size()==0)
     {
     	ret = null;
     }
     else
     {
     	//System.out.println("tableModelWritable.getValueAt r"+r+" c"+c+" size "+dataVector.size());
      	ret =  ((Object[]) dataVector.elementAt(r))[c];	
     	
     }
     
     
     String strObject = "";
     if(ret!=null)
     {
     	strObject = ret.toString();
     }
     
     if(strObject.equalsIgnoreCase("true"))
     {
     	//System.out.println("true");
     	ret =  Boolean.valueOf(true);
     }
     else if(strObject.equalsIgnoreCase("false"))
     {
     	ret = Boolean.valueOf(false);
     }
     else
     {
     	//ret = 
     }
     
     
      return ret;
      
    //  row = (Vector) allRows.elementAt(arow);
    //  return row.elementAt(col);
   }
   
   @Override
   public void setValueAt(Object value, int row, int col)
   {
     
	hasDataChanged=true;
   	
   	  	
   /*	System.out.println("tableModelWritable.setValueAt Set " + row + "," + col
                                   + " to " + value
                                   + " ("//an instance of " 
                                   + value.getClass() + ")");
   */       
   //         System.out.println("tableModelWritable.setValueAt class "+((Object[]) dataVector.elementAt(row))[col].getClass());
       if(value!=null && !value.equals(""))     
       {
            if( value.getClass() == Integer.class)
            {
                //If we don't do something like this, the column
                //switches to contain Strings.

              	

                System.out.println("TableModelWritable.setValueAt is integer");
                try
                {    // new Integer((String)value.toString().trim());
                    ((Object[])dataVector.elementAt(row))[col] = Integer.valueOf((String)value.toString().trim());
                    
                    fireTableCellUpdated(row, col);
                } catch (NumberFormatException e)
                {
                	System.out.println("TableModelWritable.setValueAt: "+getColumnName(col)+" column accepts only integer values.");
                }

            }
            else if( value.getClass() == Double.class)
            {
                //If we don't do something like this, the column
                //switches to contain Strings.
                //System.out.println("tableModelWritable.setValueAt: is double");
                try
                {    // convert string to double
                    //double d = Double.parseDouble(value.toString().trim());
                    double dbl= Double.valueOf((String)value.toString().trim());
                    
                    System.out.println("TableModelWritable.setValueAt: double "+dbl);

                    ((Object[])dataVector.elementAt(row))[col] = dbl;
                             
                    fireTableCellUpdated(row, col);
                }
                catch (NumberFormatException e)
                {
                	System.out.println("TableModelWritable.setValueAt: "+getColumnName(col)+" column accepts only double values.");
                }
                /*catch(ArrayStoreException ase)
                {
                	//System.out.println("tableModelWritable.setValueAt: "+ase.getStackTrace());
                	ase.printStackTrace();
                }*/ 
            }
            else if( value.getClass() == Boolean.class)
            {
                //If we don't do something like this, the column
                //switches to contain Strings.
                //System.out.println("tableModelWritable.setValueAt: is Boolean");
                    ((Object[])dataVector.elementAt(row))[col] = Boolean.valueOf((String)value.toString().trim());
               fireTableCellUpdated(row, col);
            }
            else
            {
                ((Object[])dataVector.elementAt(row))[col] = value;
                fireTableCellUpdated(row, col);
            }
       }
       else
       {
       	 System.out.println("TableModelWritable.setValueAt row"+row+" col"+col+" value = null or ''");
       }     
         
          // if data from lookup changed then change lookup text(the next cell)             
         RecColumn reccol = (RecColumn)tableCols.get(col);
         String foreignTable = reccol.getColumnForeignTable();
        // System.out.println("tableModelWritable.setValueAt >>>> col"+col+" foreignTable'"+foreignTable+"' "+reccol.getColumnTable()+"."+reccol.getColumnCaption());

        if(dbFieldsMany!=null && dbFieldsMany[col].getLookupEntityName()!=null)  
        {
           if(lookUp.getLookUpIntNoOfColsWhenInTable(dbFieldsMany[col].getLookupEntityName())==2)
           {          
         //if(isEditable && !foreignTable.equalsIgnoreCase("") && lookUp.getLookUpIntNoOfColsWhenInTable(foreignTable)==2)
         //{ 
           if (col!=columnLookupTwoB)
           {
             String strValue = value.toString();
             //int key = Integer.parseInt(strValue);
              //System.out.println("tableModelWritable.setValueAt ---} "+col+" '"+foreignTable+"' "+strValue+" columnLookupTwoB "+columnLookupTwoB);
             setLookupText(strValue,foreignTable,row, col);           
            }
          }
        }

        // calculation
        if (dbFieldsMany!=null)
        {
        // calculation -------
        
        
        EntityDBFields dbFieldMany = dbFieldsMany[col];
        EntityDBFieldsCalculation[] fieldsCalculationSelect = dbFieldMany.getFieldsCalculationSelect();
        if(dbFieldMany!=null && fieldsCalculationSelect!=null && value!=null && !value.toString().equalsIgnoreCase(""))
        {
        try
        {	
           for(int fc=0; fc<fieldsCalculationSelect.length;fc++)
            {
                int calculateCategoryField =  fieldsCalculationSelect[fc].getWhenSetThenCalculateCategoryField();
                int calculateField =  fieldsCalculationSelect[fc].getWhenSetThenCalculateField();
        	int[] cellsInput = fieldsCalculationSelect[fc].getCalculationInputFields();
        	String calculation =  fieldsCalculationSelect[fc].getCalculation();
        	String val = "";    
                ResultSet rs;
                //calculation= calculation+value;
                
                String[] cellString = new String[cellsInput.length];
                for(int c=0;c<cellsInput.length;c++)
                {
                     cellString[c]= getValueAt(row,cellsInput[c]).toString();
                
                }
                calculation = utilsString.replaceTextOfAStringWithText("#", calculation, cellString, null);
                        //replaceTextOfAStringWithText(String textToBeReplacedBy, String str, String[] textToReplace)
                        
                        
        	 System.out.println("TableModelResultSet.setValue   col:"+col+"  calculation:"+calculation);
        	  
                 db.retrieveDBDataFromQuery(calculation,"TableModelResultSet.setValueAt");
   	          rs=db.getRS();
   	          
            
              if (rs.first())
              {
                 rs = db.retrieveRow(rs, 1);// go to the only row  
                 //System.out.println("TableCellEditorDouble.getCellEditorValue  calculation "+calculation);
                 val = rs.getString(1);// get field data	         	
              }                        
              
             setValueAt(val, row, calculateField); 
           
             System.out.println("TableModelWritable.setValue val === "+val+" row"+row+" col"+calculateField+" calculation "+calculation);
            }
           
             closeDB();
           //db.releaseConnectionRs();

         }//try
         catch ( SQLException sqlex)
         {
             System.out.println("error:TableModelWritable.setValue: "+sqlex.getMessage());
         }
                 		
        }
        }  // if (dbFieldsMany!=null)
    
       
      
       
       
     
       
       
     // Vector dataRow = (Vector) dataVector.elementAt(aRow);
     // dataRow.setElementAt(aValue, aCol);
      fireTableCellUpdated(row, col);
      
      //hasDataChanged=true;
   }
   
     private void setLookupText(String key, String foreignTable,int row,int column)
  {   // called from setValue when a lookup column changed
  	     try
  	     {
                 ResultSet rsForeign;
                String foreignQuery="";
                String lookupText="";
                
                
                
                
                foreignQuery = "SELECT * FROM "+foreignTable+" WHERE "+lookUp.getLookUpKey(foreignTable)+" = "+key;     

            //System.out.println("tableModelWritable.setLookupText ||||| query"+foreignQuery+" "+lookUp.getLookUpLabel(foreignTable)+" "+lookUp.getLookUpKey(foreignTable)+" "+row+" "+column);   

                 if (VariablesGlobal.globalShowSQL)
                 {
                     System.out.println("TableModelWritable.setLookupText query"+foreignQuery);                 	
                 }
                  
   	            db.retrieveDBDataFromQuery(foreignQuery,"TableModelWritable.setLookupText");
   	            rsForeign=db.getRS();                
                  
                // = db.retrieveResultSet(foreignQuery);
                if (rsForeign.next())// if key there is the record with the typed key
                {
                   //System.out.println("tableModelWritable.setLookupText null rs");
                   rsForeign = db.retrieveRow(rsForeign, 1);// go to the only row               
                   //System.out.println("tableModelWritable.setLookupText - r "+row+" foreignTable "+foreignTable);
                   lookupText = rsForeign.getString(lookUp.getLookUpFieldIndex(foreignTable)) ;// get field data

                   setValueAt(lookupText, row, column+1);
                 }
                 else
                 {
                   setValueAt("", row, column+1);
                 }
         }
         catch (SQLException e)
         {
             System.out.println("TableModelWritable.setLookupText "+e);//e.printStackTrace();
         }
         
         closeDB();
  }
   

   private String getWhereValueNameThatMatchesColumn(String columnLabel)
   { 
     String whereValueName="-"; 
     String word="global";
     if(sql2WhereField!=null)
     {
        for(int i=0;i<sql2WhereField.length;i++)
        {
           //System.out.println(".panelOneDataOneRecData.checkIfNameIsWhereField "+sql2WhereField[i]);
           /*if(sql2WhereValue[i].regionMatches(true,0,word,0,word.length()))   	          
           {
           	  */ //System.out.println(".panelOneDataOneRecData.checkIfNameIsWhereField "+sql2WhereValue[i]+" matches global");
      	       if(sql2WhereField[i].equalsIgnoreCase(columnLabel))
      	       {
      	       	//System.out.println(".panelOneDataOneRecData.checkIfNameIsWhereField "+sql2WhereValue[i]+" matches global");
      	          whereValueName=sql2WhereValue[i]	;
      	       }
      	  /* }*/
        }
      }
   	   return whereValueName;
   }     
     

     private String getValueForVariable(String variableName)
  {
  	String valueStr="";
  	double valueDoub;
  	int valueInt;
  	Object valueObj;
    try
    {
      //System.out.println("TableModelWritable.getValueForVariable variableName: "+ variableName);
      Field thisField = TableModelWritable.class.getField(variableName);

      Class thisClassType = thisField.getType();
      //System.out.println("TableModelWritable.getValueForVariable "+ thisField+" "+thisClassType);
      //System.out.print("The variable '"+lookingForValue+"' contains ");
      if (thisClassType.toString().equals("double"))
      { 
          valueDoub=thisField.getDouble(this);
           valueStr=Double.toString(valueDoub);   
      }
      else if (thisClassType.toString().equals("int"))
      {
      	   valueInt=thisField.getInt(this);
              valueStr=Integer.toString(valueInt);
      }
      else if (thisClassType.toString().equals("class java.lang.String"))
      {
           valueObj=thisField.get(this);
           if (valueObj!=null)
           {      valueStr=valueObj.toString();   }
           else
           { System.out.println("TableModelWritable.getValueForVariable "+variableName+" valueObj="+valueObj);  }
      }

    }
       catch(Exception e)
       {   e.printStackTrace();   }

    return valueStr;
  }
   
   private int getColIntFromColNameForPk(String columnName)
   {
   	int colNo=-1;
            for(int col = 0; col<getColumnCount(); col++)
            {
                String colName = dbFieldsMany[col].getDbField();//fieldsMany[col];
                
                //System.out.println("TableModelWritable.getColIntFromColNameForPk "+col+" "+colName+" "+columnName+" "+fieldsMany[col]+" ");
                if (colName.toUpperCase().equals(columnName.toUpperCase()))
                {
                	colNo=col;
                }
            }
   	  return colNo;
   }

   private int getColIntFromColName(String columnName)
   {
   	int colNo=-1;
            for(int col = 0; col<getColumnCount(); col++)
            {
                String colName = getColumnName(col);
                
                //System.out.println("TableModelWritable.getColIntFromColName "+col+" "+colName+" "+columnName+" "+fieldsMany[col]+" ");
                if (colName.toUpperCase().equals(columnName.toUpperCase()))
                {
                	colNo=col;
                }
            }
   	  return colNo;
   }
   
   
       private Object getValueFromDBTable(int row, String columnName)
    {
    	
    	db.retrieveDBDataFromQuery(query,"TableModelWritable.getValueFromDBTable");
   	    rs=db.getRS();
   	    //rsmd=db.getRSMetaData();

    	Object columnData="";
      
      //System.out.println("TableModelWritable.getValueFromDBTable "+query);
      //row++;
       rs = db.retrieveRow(rs, row);
       
       try
       {
           System.out.println("TableModelWritable.getValueFromDBTable oooo row "+row+" columnName "+columnName+" data "+columnData);
          columnData =rs.getObject(columnName);
       
         
       }//try
       catch ( SQLException sqlex)
       {
           System.out.println("error: TableModelWritable.getValueFromDBTable(): "+sqlex.getMessage());
           
           sqlex.printStackTrace();
       }
    	
    	 
       closeDB();
      return columnData;
    }
  
    private String formatValueForDb(int column, Object value)
    {
        int type;

        if (value == null)
        {       return "null";       }
        	  RecColumn tableCol = (RecColumn)tableCols.get(column);
              type = tableCol.getColumnType();
        switch(type) {
        case Types.INTEGER:
        case Types.DOUBLE:
        case Types.FLOAT:
            return value.toString().trim();
        
        case Types.BIT:
        case Types.TINYINT:
            return ((Boolean)value).booleanValue() ? "1" : "0";
        case Types.DATE:
            return "'"+value.toString().trim()+"'"; // This will need some conversion.
        default:
            return "'"+value.toString().trim()+"'";
        }  	
  }   
       
   public void updateDB()
   {
  String updateLine[] = new String[headers.length];
     
 String q="";  
 String query ="";
 try{
 
     System.out.println("tableModelWritable.updateDB  headers:"+headers.length+" allRows"+allRows.size()+" dataVector:"+dataVector.size());
                      

     
         // con = db.getConnection(); 

         con = DbConnection.getConnectionFromFile();//DriverManager.getConnection(url);
              
         DatabaseMetaData dbData = con.getMetaData();
         String catalog;
         // Get the name of all of the columns for this table
         String curCol;
         colNames = new Vector();
         ResultSet rset1 = dbData.getColumns(null,null,tableName,null);
         while (rset1.next())
         {
            curCol = rset1.getString(4);
            colNames.addElement(curCol);
         }
         rset1.close();
         pKeyCol = colNames.firstElement().toString();
        
         
         
       //  String[] primKey = new String[colCount];
     //    databaseTableMeta.retrievePrimKs(tableName); //retrieveImpKsOnQuery(tableName,null);
         // databaseTableMeta.getCountOfPrimKeys());

     //    String[] primKeysName= databaseTableMeta.getPrimKeysNameArray();
         
         
         
       
         String colDbNames="";
         
         
         
         
         
         
         // Go through the rows and perform INSERTS/UPDATES/DELETES
         int totalrows;
         totalrows = allRows.size();
         String dbValues[];
         Vector currentRow = new Vector();
        // Object[] record = new Object[colCount];
         pkValues = new String[allRows.size()];

         // Get column names and values
         for(int i=0;i < totalrows;i++)   // i is row
         {
             
            currentRow = (Vector) allRows.elementAt(i);
             //System.out.println("tableModelWritable.updateDB  headers:"+headers+" dataVector"+dataVector+" allRows "+allRows);
            int numElements = currentRow.size();
            dbValues = new String[numElements];
             //System.out.println("tableModelWritable.updateDB  headers:"+headers+" dataVector"+dataVector+" allRows "+allRows+" numElements"+numElements);

            for(int x = 0; x < numElements; x++) // x is column
            {

               //System.out.println("tableModelWritable.updateDB +++ "+x+" "+numElements); 
                 Object ob = currentRow.elementAt(x);

 //             RecColumn tableCol = (RecColumn)tableCols.get(x);
 //             String colTable = tableCol.getColumnTable();
 //             String colForeignTable = tableCol.getColumnForeignTable();
              
              //colDbNames = tableCol..getColumnName();
//              if( colForeignTable!=null && colForeignTable.equalsIgnoreCase(tableName))
 //             {
              
 //                System.out.println("tableModelWritable.updateDB ------ "+x+" "+colForeignTable+"-"+colTable+"."+colDbNames+" tableName"+tableName);
                  //(columnName,columnTable,foreignTable,type,colCaption,columnLength));
   //              RecColumn recCol = (RecColumn)tableCols.get(x);
                 
                 //int classtype= recCol.getColumnType();
                 //System.out.println("TableModelWritable.updateDB "+x+" classType"+classtype); //4 int 12 string
             
               
               String classType = currentRow.elementAt(x).getClass().toString();
               int pos = classType.indexOf("String");
               if(pos > 0){ // we have a String

                  dbValues[x] = "'" + currentRow.elementAt(x) + "'";
                  updateLine[x] = headers[x] + " = " + "'" + currentRow.elementAt(x) + "',";
                  //System.out.println("TableModelWritable.updateDB string "+x+"  "+headers[x]+" "+pKeyCol);
                  if (headers[x].toUpperCase().equals(pKeyCol.toUpperCase())){
                    pkValues[i] = currentRow.elementAt(x).toString() ;
                  }
               }
               pos = classType.indexOf("Integer");
               if(pos > 0){ // we have an Integer
                  dbValues[x] = currentRow.elementAt(x).toString();
                  //System.out.println("TableModelWritable.updateDB integer "+x+"  "+headers[x]+" "+pKeyCol);
                  if (headers[x].toUpperCase().equals(pKeyCol.toUpperCase())){
                     pkValues[i] = currentRow.elementAt(x).toString();
                  }
                  else{
                     updateLine[x] = headers[x] + " = " + currentRow.elementAt(x).toString() + ",";
                  }
               }
               pos = classType.indexOf("Boolean");
               if(pos > 0){ // we have a Boolean
                  dbValues[x] = currentRow.elementAt(x).toString();
                  updateLine[x] = headers[x] + " = " + currentRow.elementAt(x).toString() + ",";
                  if (headers[x].toUpperCase().equals(pKeyCol.toUpperCase())){
                     pkValues[i] = currentRow.elementAt(x).toString() ;
                  }
               }
               
//              }//  colTable.equalsIgnoreCase(tableName))
               
               
            } // For x Loop
            

          

            
            // If we are here, we have read one entire row of data. Do an UPDATE or an INSERT
            int numNewRows = newRows.size();
            int insertRow = 0;
            boolean newRowFound;
         
            for (int z = 0;z < numNewRows;z++)
            {
               insertRow = ((Integer) newRows.get(z)).intValue();
               if(insertRow == i+1){
                  StringBuffer InsertSQL = new StringBuffer();
                  InsertSQL.append("INSERT INTO " + tableName + " ("); 
                  for(int zz=0;zz<=headers.length-1;zz++){
                     if (headers[zz] != null){
                        InsertSQL.append(headers[zz] + ",");
                     }
                  }
                  // Strip out last comma
                  InsertSQL.replace(InsertSQL.length()-1,InsertSQL.length(),")");
                  InsertSQL.append(" VALUES(" + pkValues[i] + ",");
                  for(int c=1;c < dbValues.length;c++)
                  {
                     InsertSQL.append(dbValues[c] + ",");
                  }
                  InsertSQL.replace(InsertSQL.length()-1,InsertSQL.length(),")"); 
                 q=InsertSQL.toString();
                  //stat.executeUpdate(InsertSQL.toString());
 //                 db.updateQuery(q,false);
          
                  ibRowInserted=true;
               }
            } // End of INSERT Logic
           
            // If row has not been INSERTED perform an UPDATE
           
            if(ibRowInserted == false)
            {
               StringBuffer updateSQL = new StringBuffer();
               updateSQL.append("UPDATE " + tableName + " SET ");
               for(int z=0;z<=updateLine.length-1;z++)
               {
                  if (updateLine[z] != null)
                  {
                      //System.out.println("tableModelWritable.updateDB  z "+z+" "+i+" "+primKeysName[z] + " = " + pkValues[i]);  
                      System.out.println("tableModelWritable.updateDB  z "+z+" "+i+" "+updateLine[z]+" colDbNames"+colDbNames);
                      //System.out.println("tableModelWritable.updateDB  z "+z+" "+i+" "+primKeysName[z]);  
                      //updateSQL.append(updateLine[z]);
                      updateSQL.append(colDbNames);
                  }
               }
               // Replace the last ',' in the SQL statement with a blank. Then add WHERE clause
               updateSQL.replace(updateSQL.length()-1,updateSQL.length()," ");
//               updateSQL.append(" WHERE " + pKeyCol + " = " + pkValues[i] ); // original for 1 pk
              
            String where = "";
               
             //primKeyMany
            
            
             for (int pk=0;pk<databaseTableMeta.getCountOfPrimKeys();pk++)
             {
             	 String pkName=databaseTableMeta.getPrimKeyName(pk);
             	 
             	 if (pk!=0)
             	 {  	 where = where + " AND ";    }
             	 
          	     String whereValueName = getWhereValueNameThatMatchesColumn(pkName);
                      //System.out.println("tableModelWritable.updateDB ++"+pk+" pkName"+pkName+" whereValueName "+whereValueName+" primKeyValue"+primKeyValue);
          	    if(!whereValueName.equalsIgnoreCase("-"))
          	    {   // for the ones that are global variables      
                         
                        // System.out.println("tableModelWritable.updateDB "+pkName+" whereValueName:"+whereValueName+" getValueForVariable(whereValueName):"+getValueForVariable(whereValueName));
                         
             	       where = where + pkName +" LIKE '"+getValueForVariable(whereValueName)+"'";
             	     //System.out.println("tableModelWritable.rowDbUpdate "+pkName+"getValueForVariable"+getValueForVariable(whereValueName)+" whereValueName "+whereValueName);
             	    }
             	    else
             	    {  // for the ones that exist in the jtable
             	    
             	      for(int pkm=0;pkm<primKeysMany.length;pkm++)
             	      {
             	   	    if (pkName.toUpperCase().equalsIgnoreCase(primKeysMany[pkm].toUpperCase()))
             	   	    {
             	   	         
             	 	         int col = getColIntFromColNameForPk(pkName);
             	 	         //System.out.println(" oooo tableModelWritable.rowDbUpdate "+pkName+" col"+col+" pk "+primKeysMany[i]+" initialTableRow"+initialTableRow+" currentTableRow"+currentTableRow+"   "+ getValueAt(initialTableRow,col)+" - "+getValueFromDBTable(initialTableRow,primKeysManyTran[i]) +" hasDataChanged"+ hasDataChanged);//+formatValueForDb(initialTableRow, getValueFromDBTable(initialTableRow,primKeysManyTran[i])));
             	 	         
             	 	         if(!hasDataChanged)// when data has not been updated
             	 	         {  
                                       
             	 	         	where = where + pkName +" LIKE "+formatValueForDb(col,getValueAt(row,col));   //(initialTableRow,col)); //getValueFromDBTable(initialTableRow,primKeysManyTran[i]));
             	 	         }
             	             else// when acell has been changed
             	             {
                                  //   System.out.println(" oooo tableModelWritable.rowDbUpdate "+pkName+" col"+col+" pk "+primKeysMany[i]+"    "+" hasDataChanged"+ hasDataChanged);
             	             	where = where + pkName +" LIKE '"+ getValueFromDBTable(/*updateSQL.toString(),*/row,primKeysManyTran[pkm])+"'"; //  initialTableRow,primKeysManyTran[pkm])+"'"; //getValueAt(initialTableRow,col);//getValueFromDBTable(initialTableRow,primKeysManyTran[i]);//getValueFromDBTable(initialTableRow,primKeysManyTran[i]));
             	             }
             	             
             	             
                           }

                        
                          else if (pkName.equalsIgnoreCase(primKeysMany[i]))
                          {
              	 	         int col = getColIntFromColName(pkName);
             	 	System.out.println("tableModelWritable.updateDB 2 "+pkName+" col"+col+primKeysMany[i]+" "+primKeysMany[i]);
                                  where = where + pkName +" LIKE "+formatValueForDb(col, getValueFromDBTable(row,primKeysManyTran[i]));  //currentTableRow,primKeysManyTran[i]));
                       	
                          }
             	 	
             	      } // for
             	   }   //else for the ones that exist in the jtable
               }    //for 
               
                
    /*           for(int pk= 0;pk<primKeysMany.length;pk++)
               {
                    //for(int c = 0; c < colCount; c++)//  x is column
                    // System.out.println("tableModelWritable.updateDB  pk "+pk+" "+primKeysMany[pk]);
                    System.out.println("TableModelWritable.updateDB  pk "+pk+"   "+primKeysMany[pk]+ " = " + pkValues[i] );
                    where = where+" "+primKeysMany[pk] + " = " + pkValues[i];
               }
              
      */         
               
               
               
              query = updateSQL.toString()+" WHERE "+where;    
               System.out.println("TableModelWritable.updateDB SQL UPDATE  query=' "+ query+" '  " );
//               db.updateQuery(q,false); 
               
               //stat.executeUpdate(q);
            }  // ibRowInserted == false)

            }// for i

         
         
            //    System.out.println("tableModelWritable.updateDB query=' "+ q+" '  ");
         }
         catch(Exception ex){
            System.out.println("TableModelWritable.updateDB SQL Error! Cannot perform SQL UPDATE  query=' "+ q+" '  " + ex.getMessage());
            ex.printStackTrace();
         }
         // Delete records from the DB
         try{
            int numDeletes = deletedKeys.size();
            String deleteSQL;
            for(int i = 0; i < numDeletes;i++){
               deleteSQL = "DELETE FROM " + tableName + " WHERE " + pKeyCol + " = " +((Integer) deletedKeys.get(i)).toString();
            System.out.println(deleteSQL);
               //stat.executeUpdate(deleteSQL);
//            db.updateQuery(deleteSQL,false); 
            }
            // Assume deletes where successful. Recreate Vector holding PK Keys
            deletedKeys = new Vector();
         }
         catch(Exception ex){
            System.out.println(ex.getMessage());
             ex.printStackTrace();
         }
         
         closeDB();
         
         hasDataChanged =false;
         
      }
   
    
   
   
      public void deleteRow(int rowToDelete){
         // Mark row for a SQL DELETE from the Database
         Vector deletedRow = (Vector) allRows.get(rowToDelete);
         Integer pkKey = (Integer) deletedRow.get(0);
         deletedKeys.add(pkKey);
         allRows.remove(rowToDelete);
         fireTableRowsDeleted(rowToDelete,rowToDelete);
      }
      public void addRow()
      {
         // Mark the row for a SQL INSERT in the Database
         newRows.add(Integer.valueOf(allRows.size() +1));
         // Get the total number of rows in the Vector
         int rowNumber = allRows.size();
         int pos;
      
         // Get what a row looks like
         int colCount = newRow.size();
         Vector newRowVect = new Vector();
         for(int i = 0; i < colCount; i++){
            String classType = newRow.elementAt(i).getClass().toString();
            pos = classType.indexOf("String");
            if(pos > 0){ // we have a String
               String blankString = new String();
               newRowVect.addElement(blankString);
            }
            pos = classType.indexOf("Integer");
            if(pos > 0){ // we have an Integer
               Integer blankInt = Integer.valueOf(0);
               newRowVect.addElement(blankInt);
            }
            pos = classType.indexOf("Boolean");
            if(pos > 0){ // we have a Boolean
               Boolean blankBool = Boolean.valueOf(false);
               newRowVect.addElement(blankBool);
            }
         }
         allRows.addElement(newRowVect);
         ibRowNew = true;
         this.isCellEditable(allRows.size(),0);
         System.out.println(allRows.size());
         fireTableRowsInserted(rowNumber,rowNumber);
      }
      
                 
   public static void main (String[]args)
   {
   	  String query = "SELECT * FROM farmer";
   	  //int indexOfEndOfFrom = query.indexOf("FROM")+5;
   	  //System.out.println(query.substring(indexOfEndOfFrom,indexOfEndOfFrom+6));
   	  
          TableModelWritable tm = new TableModelWritable();
          
   	  
   	  
   }
      
      
      
      
      
      
      
      
   }