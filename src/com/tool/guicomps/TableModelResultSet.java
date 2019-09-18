
package com.tool.guicomps;

import com.tool.model.LookUpMgt;
import com.tool.model.LookupTableConstantsMgt;
import com.tool.model.EntityMessage;
import com.tool.model.*;
import com.tool.gui.*;
import com.tool.guicomps.*;
import com.tool.jdbc.*;
import com.tool.utils.*;


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

import java.awt.Color;
import java.awt.SystemColor;

import java.lang.reflect.Field;
import java.lang.ArrayStoreException;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.text.JTextComponent;

public class TableModelResultSet extends AbstractTableModel implements Constants
{
  // private ResultSetTableModel model;
  private Database db;
  private Statement stmt;
  private ResultSet rs;
  private ResultSetMetaData rsmd;

  private DatabaseTableMeta databaseTableMeta;
  private LookUpMgt lookUp;
  private Vector dataVector;
  private Vector initialTableDataVector; // to save changes
  private Vector initialDataVector; // for filter
  private int colCount;
  private String[] headers;
  private String entity;
  private String query;

  private boolean isEditable;
  private boolean hasDataChanged=false;
  private boolean isNewRec=false;
  //private String[] fieldsMany;
  //private String[] fieldsManyTranslation;
  //private String[] fieldsManyOnInsert;
  //private String[] fieldsManyTranslationOnInsert;
  //private String[] primKeysMany;
  //private String[] primKeysManyTran;
   // private String[] sql2WhereField;
    //private String[] sql2WhereValue;
  private String primKeyDb;
  // must be public so the getValueForVariable to work
  
    public String primKeyValue;
    public String globalYear;   
    public String globalDeliveryId;
    public String globalCompanyId; 
    
    private ArrayList listTableColumns;
  //private RecColumn tableColumn;    
    private RecColumn recColumn;
    private RecColumnMgt recColumnMgt;
    private boolean showDialogOnError=true;
    
    private ArrayList<Integer> checksums;
    private ArrayList listRecsHashToBeRemoved;
    private ArrayList listRecsHashToBeInserted;
    
    //private final int ROW_CHANGE_NOT=0;
    //private final int ROW_CHANGE_NEW=1;
    //private final int ROW_CHANGE_UPDATE=2;
    //private final int ROW_CHANGE_DELETE=3;
    
    private UtilsDouble utilsDouble;
    private UtilsGui utilsGui;
    private UtilsString utilsString;
    //ArrayList rowsToBeDeleted;
   // ArrayList rowsToBeUpdatedQuery;  // query
   // ArrayList listRowsToBeUpdated;  // list
    private static Integer INTEGER_ONE = new Integer(1);    
   
    private EntityDBFields[] dbFieldsParent;
   private EntityDBFields[] dbFieldsMany; 	
   private UtilsPanelReport utilsPanelReport;
   private PanelOneDataOneRecData panelODORData;
    	
    	private int columnLookupTwoB=-1;
        
        private String strPkValue="";
        private int countOfJTableNewRowsBeforeSavedInDB=0;
        
//abstract class ResultSetTableModel extends AbstractTableModel
//{  
   public TableModelResultSet()//    only writable table model
   {  
     //rs = aResultSet;
     db= new Database();
     utilsDouble = new UtilsDouble();
     utilsDouble = VariablesGlobal.globalUtilsDouble;
     utilsGui=new UtilsGui();
     utilsString = new UtilsString();
     utilsPanelReport = new UtilsPanelReport();
     recColumnMgt = new  RecColumnMgt();
     listTableColumns = new ArrayList();
     dataVector = new Vector();
     lookUp= new LookUpMgt();
     databaseTableMeta = new DatabaseTableMeta();

   }
 
   /*
    * 
    * called by PanelOneDataManyRecData.setPrimKeyValueInTableModelResultSet which called by PanelOneDataOneRecData. Reason to have new key when insetring a new record in PanelOneDataOneRecData
    */
   public void setPrimKeyValueFromPanelOneData(String primKeyValueIn)
   {
       primKeyValue=primKeyValueIn;
       //System.out.println("TableModelResultSet.setPrimKeyValue         primKeyValue:"+primKeyValue);
      
     if (isEditable)
     {

      for (int c = 0; c < colCount; c++)
      {
                //System.out.println("TableModelRS. h "+h+" dbHeadNo "+dbHeadNo);
                String columnName =dbFieldsMany[c].getDbField(); //rsmd.getColumnName(dbHeadNo);
                String colClass =dbFieldsMany[c].getColClassName();
                //String columnTable =dbFieldsMany[c].getTableName(); //  smd.getTableName(dbHeadNo);
                //String colCaption=dbFieldsMany[c].getCaption(); //  rsmd.getColumnLabel(dbHeadNo);
         //    System.out.println("tableModelResultSet.setPrimKeyValueFromPanelOneData =======8888====  ("+c+")  primKeyDb:"+primKeyDb+"= columnName:"+columnName+"  colClass:"+colClass);   
             if(columnName.equalsIgnoreCase(primKeyDb))
             {
                for(int r=0;r<getRowCount();r++)
                {
                    
                 setValueAt(primKeyValue,r,c);
                }
             }
      }
     }
               
               
               
               
   }
   
   /*
    * 
    * called by PanelODMRData.retrieveDataFromWritableTable 
    */
  public void setQuery(String queryIn, String entity,EntityDBFields[] dbFieldsParentIn, EntityDBFields[] dbFieldsManyIn,boolean isNewRecIn,/*String[]fieldsManyOnInsertIn,*/
    /*String[] fieldsManyTranslationOnInsertIn,String[] primKeysManyIn,String[] primKeysManyTranIn,String[] sql2WhereFieldIn, String[] sql2WhereValueIn,*/
    String primKeyDbIn, String primKeyValueIn, boolean isEditableIn,PanelOneDataOneRecData panelODORDataIn)
  {
      
    dataVector = new Vector();
    isEditable=isEditableIn;
    dbFieldsParent=dbFieldsParentIn;
    dbFieldsMany = dbFieldsManyIn;
    isNewRec=isNewRecIn;
    //fieldsManyTranslation=fieldsManyTranslationIn;
    //fieldsManyOnInsert=fieldsManyOnInsertIn;
    //fieldsManyTranslationOnInsert=fieldsManyTranslationOnInsertIn;    
    //primKeysMany=primKeysManyIn;
    //primKeysManyTran=primKeysManyTranIn;
    //sql2WhereField=sql2WhereFieldIn;
    //sql2WhereValue=sql2WhereValueIn;
    primKeyDb=primKeyDbIn;
    primKeyValue=primKeyValueIn;
    query=queryIn;
    panelODORData=panelODORDataIn;
    
    listTableColumns.clear();
    
    try {
      // Execute the query and store the result set and its metadata
      this.entity=entity;
      
     if (VariablesGlobal.globalShowSQL)     
     {  
      System.out.println("                           tableModelResultSet.setQuery: "+query);   
     }

      //String entity = query.charAt() query.indexOf()
      
      //System.out.println("TableModelRS.setQuery notShowFieldsFromThisInQuery2 "+notShowFieldsFromThisInQuery2);
      


      // headers = new String[colCount];
      // int dbHeadNo = 1;
      int noOfColumns = 0;
   if (isEditable && dbFieldsMany!=null)
   {
      for (int h = 0; h < dbFieldsMany.length; h++)
      {
                //System.out.println("h "+h+" dbHeadNo "+dbHeadNo);
                String columnName =dbFieldsMany[h].getDbField();//rsmd.getColumnName(h+1);
                //System.out.println("tableModelRS.setQuery columnName --- "+columnName);
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
                  	//System.out.println("tableModelRS.setQuery else ft for "+dbFieldsMany[h].getLookupEntityName());
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
                	//System.out.println("tableModelRS.setQuery 2 "+columnName+" "+ft+" for "+dbFieldsMany[h].getLookupEntityName());
                }
                if(lookUp.getLookUpIntNoOfColsWhenInTable(dbFieldsMany[h].getLookupEntityName())==1)
                {
                	//noOfColumns =noOfColumns+1;
                	//System.out.println("tableModelRS.setQuery 1 "+columnName);
                }
                else
                {
                	//noOfColumns =noOfColumns+1;
                	//System.out.println("tableModelRS.setQuery getLookUpIntNoOfColsWhenInTable "+lookUp.getLookUpIntNoOfColsWhenInTable(ft));
                }
              }
                //String foreignTable = ft; 

               }       
                
      }
            query=utilsString.removeCaptionsFromQuerySubStringSelect(query);
   	    db.retrieveDBDataFromQuery(query,"TableModelResultSet.setQuery");
   	    rs=db.getRS();
   	    rsmd=db.getRSMetaData();      //  rsmd only for type int
        
         
      


    //System.out.println("tableModelRS.setQuery noOfColumns+rsmd.getColumnCount() "+(noOfColumns+rsmd.getColumnCount()));

          colCount = dbFieldsMany.length;////noOfColumns+rsmd.getColumnCount();//+databaseTableMeta.getCountOfImpKs();

     //System.out.println("TableModelResultSet.setQuery   ----------------- colCount:"+colCount+"   query:"+query);
      
               //databaseTableMeta.retrievePrimKs(entity);


      // Now we must rebuild the headers array with the new column names
      headers = new String[colCount];
      int dbHeadNo = 1;
     if (isEditable)
     {
          if(rsmd.getColumnCount()!=dbFieldsMany.length)
          {
              System.out.println("  error  TableModelResultSet.setEntity  rsmd.getColumnCount():"+rsmd.getColumnCount()+" != dbFieldsMany.length:"+dbFieldsMany.length);
          }
      for (int h = 0; h < colCount; h++)
      {

                //System.out.println("TableModelRS. h "+h+" dbHeadNo "+dbHeadNo);
                String columnName =dbFieldsMany[h].getDbField(); //rsmd.getColumnName(dbHeadNo);
                String columnTable =dbFieldsMany[h].getTableName(); //  smd.getTableName(dbHeadNo);
                String colCaption=dbFieldsMany[h].getCaption(); //  rsmd.getColumnLabel(dbHeadNo);
                //System.out.println("tableModelRS.setQuery columnName "+columnName+" "+h+" "+dbHeadNo);
                int columnLength=dbFieldsMany[h].getColWidth();  //  rsmd.getColumnDisplaySize(dbHeadNo);
                String colClassName = dbFieldsMany[h].getColClassName();// 
                int type = rsmd.getColumnType(dbHeadNo); // type neede for column object,rsmd only for type
                //System.out.println("TableModelResultSet.setEntity h:("+h+") or dbHeadNo:"+dbHeadNo+" columnName:"+columnName+" colClassName:"+colClassName+" type:"+type);
                String ft;   //if foreign table = null assign
                if (dbFieldsMany[h].getLookupEntityName()==null) //&&(!rsmd.getTableName(i).equalsIgnoreCase(entity)))
                {
                  ft =entity; 
                  //System.out.println("panelODORData.setEntity if ft :"+ft);
                }
                else
                {
                	ft =dbFieldsMany[h].getLookupEntityName() ; 
                	//System.out.println("tableModelRS.setQuery else ft :"+ft);
                }
                // if has foreign key then calculate

              //  if (!isEditable)
              //  {   ft=entity;       }
                
                final String foreignTable = ft; 
                //System.out.println("tableModelRS.setQuery "+columnName+" "+foreignTable+" "+notShowFieldsFromThisInQuery2+" "+isEditable);
                 
                 // if has 2 columns as lookup
                if ((foreignTable!= null) && (!foreignTable.toUpperCase().equals(entity.toUpperCase()))&& (lookUp.getLookUpIntNoOfColsWhenInTable(foreignTable)==2))//(!rsmd.getTableName(i).equalsIgnoreCase(entity)))
                {

                   //System.out.println("tableModelRS.setQuery columnName show "+columnName);

                    colCaption = lookUp.getLookUpKeyTranslation(foreignTable);//columnName;
                    headers[h]= colCaption;
                    
                    String colNameForeign = lookUp.getLookUpLabel(foreignTable);//lookUp.getLookUpLabel(foreignTable);
                    String colCaptionForeign = colNameForeign;
                    headers[h+1] = colCaptionForeign;


                   
                   listTableColumns.add(recColumn = new RecColumn(columnName,columnTable,foreignTable,type,colClassName,colCaption,columnLength));
                   listTableColumns.add(recColumn = new RecColumn(colNameForeign,foreignTable,"",0,"java.lang.Object",colCaptionForeign,40));
                   //System.out.println("tableModelRS.setQuery  ++2"+columnName+" "+h);
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
                    
                  //  System.out.println("tableModelRS.setQuery columnName 1 "+columnName+" "+dbHeadNo);
                  // String colCaption = columnName;
                   
                  // int type = rsmd.getColumnType(dbHeadNo);
                   listTableColumns.add(recColumn = new RecColumn(columnName,entity,foreignTable,type,colClassName,colCaptionForeign,columnLength));
                   //listTableColumns.add(recColumn = new RecColumn(columnName,entity,"",type,colCaption,columnLength));
                   //System.out.println("tableModelRS.setQuery  ++1"+columnName+" "+h);
                  //  dbHeadNo=dbHeadNo+1;                	
                }
                /*else if((foreignTable!= null) && (columnTable.toUpperCase().equals(entity.toUpperCase())))
                {
                	System.out.println("tableModelRS.setQuery columnTable else if"+columnTable+" "+columnName);
                	
                    headers[h - 1] = columnName;
                  // String colCaption = columnName;
                   
                   int type = rsmd.getColumnType(dbHeadNo);
                   listTableColumns.add(recColumn = new RecColumn(columnName,columnTable,"",type,colCaption,columnLength));
                	
                }*/
                else
                {
                	//System.out.println("tableModelRS.setQuery columnTable else "+columnTable+" "+columnName);
                   //System.out.println("tableModelRS.setQuery columnName "+columnName+rsmd.getColumnLabel(dbHeadNo)+rsmd.getColumnName(dbHeadNo));
                   headers[h] = columnName;
                  // String colCaption = columnName;
                   
//                   int type = rsmd.getColumnType(dbHeadNo);
                   listTableColumns.add(recColumn = new RecColumn(columnName,columnTable,"",type,colClassName,colCaption,columnLength));
                         
               //     dbHeadNo=dbHeadNo+1;
                   //System.out.println("tableModelRS.setQuery  ++else"+columnName+" "+h);
                
                }
                
                dbHeadNo=dbHeadNo+1;
                
      }
     }
     else// read only
     {
        //for (int h = 1; h <= colCount; h++)
        for (int h = 0; h < colCount; h++)
        {
                String columnName =dbFieldsMany[h].getDbField();  //rsmd.getColumnName(h);
                String columnTable = dbFieldsMany[h].getTableName();// rsmd.getTableName(h);
                String colCaption=dbFieldsMany[h].getCaption(); // rsmd.getColumnLabel(h);    
                int columnLength=dbFieldsMany[h].getColWidth(); //rsmd.getColumnDisplaySize(h); 
                String colClassName = dbFieldsMany[h].getColClassName(); 
                int type = rsmd.getColumnType(h+1);
                	//System.out.println("tableModelRS.setQuery columnTable else "+columnTable+" "+columnName);
                   //System.out.println("tableModelRS.setQuery columnName "+columnName+rsmd.getColumnLabel(dbHeadNo)+rsmd.getColumnName(dbHeadNo));
                //headers[h-1] = columnName;   
                headers[h] = columnName;
                  // String colCaption = columnName;
                   
                   
                   listTableColumns.add(recColumn = new RecColumn(columnName,columnTable,"",type,colClassName,colCaption,columnLength));
                         
               //     dbHeadNo=dbHeadNo+1;
                   //System.out.println("tableModelRS.setQuery  ++else"+columnName+" "+h);
                //dbHeadNo=dbHeadNo+1;
        }              	
     }
        //closeDB();  // not because there is a rs below
    }
    catch (SQLException e)
    {
      dataVector = new Vector(); // blank it out and keep going.
      System.out.println("TableModelRS.setQuery (header) "+e.getMessage());//e.printStackTrace();
      closeDB();
    }
    
    //System.out.println("TableModelRS.setQuery header col count ="+colCount+" "+listTableColumns.size());
    
    try
    {  
      // and file the data with the records from our query. This would
      // not be
      // practical if we were expecting a few million records in response
      // to our
      // query, but we aren't, so we can do this.
      int row =1;
      initialDataVector = new Vector();
      while (rs.next())
      {
      	//int dbColNo = 1;// dbColNo
        Object[] record = new Object[colCount];
     //   int lookUpKeysLength=0;
    //    int repeatedKey=0;
        for (int i = 0; i < colCount; i++) // for each field
        {
             //System.out.println("i "+i+" dbHeadNo "+dbColNo);
             String columnName =dbFieldsMany[i].getDbField();//rsmd.getColumnName(dbColNo);
             //System.out.println("i "+i+" columnName:"+columnName);
             Object columnData =rs.getObject(columnName);//dbColNo);
             //System.out.println("i "+i+" dbHeadNo "+dbColNo+" "+columnData);
             
             
             if(columnName.toUpperCase().equals(VariablesGlobal.columnNameInc.toUpperCase()) && (columnData==null || columnData.equals("")))
             {
             	int v =getRowCount()+1;
             	
             	columnData = v+"";
             }
             
             String ft;   //if foreign table = null assign
             
             
             
                if (dbFieldsMany[i].getLookupEntityName()==null)// && (!entity.toUpperCase().equalsIgnoreCase(dbFieldsIn[i].getLookupEntityName().toUpperCase())))
                {
                     ft =entity; //rsmd.getTableName(i);
                }
                else
                {
                     ft = lookUp.getTable(dbFieldsMany[i].getLookupEntityName());
                }                     
                     
                     

                
             // if has foreign key then calculate
                if (!isEditable)
                {   ft=entity;       }
             final String foreignTable = ft;  

             
             // System.out.println("foreignTable ["+foreignTable+"] "+dbColNo+" - r "+row);            
             if ((foreignTable!= null) && (!foreignTable.toUpperCase().equalsIgnoreCase(entity.toUpperCase())))//(!rsmd.getTableName(i).equalsIgnoreCase(entity)))
             {

                ResultSet rsForeign;
                String foreignQuery="";
                String lookupText="";
                
                //System.out.println("TableModelResultSet "+lookUp.getLookUpKey(foreignTable).toString());
                int k = rs.getInt(lookUp.getLookUpKey(foreignTable).toString()); //dbColNo); 
                if (k!=0)
                {

                 
                   foreignQuery = "SELECT * FROM "+foreignTable+" WHERE "+lookUp.getLookUpKey(foreignTable)+" = "+rs.getInt(lookUp.getLookUpKey(foreignTable));//dbColNo);     
               //System.out.println("tableModelRS.foreignQuery "+row+"  "+lookUp.getLookUpKey(foreignTable)+" "+rs.getInt(lookUp.getLookUpKey(foreignTable))+" "+foreignQuery);
   	               db.retrieveDBDataFromQuery(foreignQuery,"TableModelResultSet.setQuery");
   	               rsForeign=db.getRS();
   	                              
                   
                   //rsForeign = db.retrieveResultSet(foreignQuery);
                   rsForeign = db.retrieveRow(rsForeign, 1);// go to the only row 
                   //System.out.println("tableModelRS.setQuery "+columnData+" - "+columnName+" - r "+row+" foreignTable "+foreignTable);
               //    System.out.println("tableModelRS.setQuery "+columnName+" "+lookUp.getLookUpFieldIndex(foreignTable)+" "+foreignQuery);
                   //System.out.println("tableModelRS.setQuery entity:"+entity+" foreignTable:"+foreignTable+" ["+lookUp.getLookUpFieldIndex(foreignTable)+"]  foreignQuery:"+foreignQuery);
                   if (/*notShowFieldsFromThisInQuery2!=null &&*/ foreignTable!=null /*&& !foreignTable.equalsIgnoreCase(notShowFieldsFromThisInQuery2)*/ && isEditable)
                   {
                       //System.out.println("TableModelRS.setQuery     foreignTable:"+foreignTable+"   llokypIndex:"+lookUp.getLookUpFieldIndex(foreignTable));
                       lookupText = rsForeign.getString(lookUp.getLookUpFieldIndex(foreignTable)) ;// get field data
                   }
                     
                   //System.out.println("tableModelRS.setQuery "+lookupText);
                 }// if (k!=0)
              
                 record[i]=columnData;
                 
                 if(lookUp.getLookUpIntNoOfColsWhenInTable(foreignTable)==2)
                 {
                     record[i+1]=lookupText;
                     //System.out.println("tableModelRS.setQuery 2  -"+columnData+" "+lookupText+" - r "+row+" i"+i);
                     i=i+1;
                     //dbColNo=dbColNo+1;                 	
                 }
                 else if(lookUp.getLookUpIntNoOfColsWhenInTable(foreignTable)==1)
                 {
                 	//System.out.println("tableModelRS.setQuery 1  -"+columnData+" - r "+row+" i"+i);
                 	//dbColNo=dbColNo+1;
                 }
                 else
                 {
                 	
                 	//System.out.println("tableModelRS.setQuery else  -"+columnData+" - r "+row+" i"+i);
                 	//dbColNo=dbColNo+1;
                 }
           //     dbColNo=dbColNo+1; 
                
             }
             else
             {
                    record[i]=columnData;    
                    
                    //System.out.println("tableModelRS.setQuery else  "+columnName+"-"+columnData+" - r"+row+" i"+i+" "+rs.getString(columnName));
             //   dbColNo=dbColNo+1;
             }

        }
        //System.out.println("tableModelRS.setQuery record.length  "+record.length);
        dataVector.addElement(record);
        row=row+1;  
        initialDataVector.addElement(record);
      }
      fireTableChanged(null); // notify everyone that we have a new table.
      
           closeDB();

    }
    catch (SQLException e)
    {
      dataVector = new Vector(); // blank it out and keep going.
      System.out.println("TableModelRS.setQuery (data)"+e);//e.printStackTrace();
      e.printStackTrace();
      closeDB();
    }
    
 
 //     closeDB();
      
    //if(isEditable)
    //{
       setTableInitialState();	
    //}
    
    
  }

  // called by TableCellEditorLookupTwoB.disolayDialogLookUp
  public void setColumnLookUpTwoB(int col)
  {
  	columnLookupTwoB = col;
  }

   public void closeDB()
   {
	      
   	  db.releaseConnectionRs();
          db.releaseConnectionRsmd();
   	
   }
  
  // needed for getQuery2
  /*public void setWHereFieldAndValue(String[]sql2WhereFieldIn,String[]sql2WhereValueIn,String globalYearIn,String globalCompanyIdIn,String globalDeliveryIdIn)
  {
  	/*sql2WhereField=sql2WhereFieldIn;
  	sql2WhereValue=sql2WhereValueIn;
  	globalYear=globalYearIn;
  	globalCompanyId=globalCompanyIdIn;
  	globalDeliveryId=globalDeliveryIdIn;*/
  	
  //}
  
   /*public String getQueryMany(String sql2, boolean isNewRec, String PKForPanelOneRecData)
   {     
         String query2="";
         String sqlWhere=" WHERE";  
           	 for(int i=0;i<sql2WhereField.length;i++)
             {    
                  if(isNewRec)
                  {
                  	sqlWhere=sqlWhere+" "+sql2WhereField[i]+" = '0'";
                  }
                  else
                  {
                     if (sql2WhereValue[i].equalsIgnoreCase("primKeyValue") && PKForPanelOneRecData!=null )
                     {
                        sqlWhere=sqlWhere+" "+sql2WhereField[i]+" = "+PKForPanelOneRecData;
                     }
                     else
                     {
                     	sqlWhere=sqlWhere+" "+sql2WhereField[i]+" = "+getValueForVariable(sql2WhereValue[i]);
                     }
      	          }
      	          if(i<sql2WhereField.length-1)
      	          {
      	          	sqlWhere=sqlWhere+" AND";
      	          }
             }  
        if (isNewRec)
        {
        	 query2 = sql2+sqlWhere+" ORDER BY date";
   	         //panelCenter.setPreferredSize(new Dimension(700, 350));
            
        }
        else
        {    
             query2 = sql2+sqlWhere+" ORDER BY date";
             //System.out.println("panelTwoDataOneRec.setEntity query2 "+query2);	
        }   
        
        return query2;	
   }*/

  
  private void setLookupText(String key, String foreignTable,int row,int column)
  {   // called from setValue when a lookup column changed
  	     try
  	     {
                 
                 if(foreignTable==null || foreignTable.equalsIgnoreCase(""))
                 {
                     System.out.println(" error  tableModelRS.setLookupText  EMPTY foreignTable:"+foreignTable );
                 }
                 
                 ResultSet rsForeign;
                String foreignQuery="";
                String lookupText="";
                
                //foreignQuery = "SELECT * FROM "+foreignTable+" WHERE "+lookUp.getLookUpKey(foreignTable)+" = "+key; 
                String queryLookUp = lookUp.getQuery(foreignTable);
                foreignQuery = queryLookUp+" WHERE "+lookUp.getLookUpKey(foreignTable)+" = "+key; 

              //System.out.println("tableModelRS.setLookupText query"+foreignQuery+" "+lookUp.getLookUpLabel(foreignTable)+" "+lookUp.getLookUpKey(foreignTable));   

                 if (VariablesGlobal.globalShowSQL)
                 {
                     System.out.println("tableModelRS.setLookupText query"+foreignQuery);                 	
                 }
                  
   	            db.retrieveDBDataFromQuery(foreignQuery,"TableModelResultSet.setLookupText  foreignTable:"+foreignTable);
   	            rsForeign=db.getRS();
   	                           
                  
                // = db.retrieveResultSet(foreignQuery);
                if (rsForeign.next())// if key there is the record with the typed key
                {
                   //System.out.println("tableModelRS.setLookupText null rs");
                   rsForeign = db.retrieveRow(rsForeign, 1);// go to the only row               
                   //System.out.println("tableModelRS.setLookupText - r "+row+" foreignTable "+foreignTable);
                   lookupText = rsForeign.getString(lookUp.getLookUpFieldIndex(foreignTable)) ;// get field data

                   setValueAt(lookupText, row, column+1);
                 }
                 else
                 {
                     lookupText="";
                   setValueAt(lookupText, row, column+1);
                 }
                
                //System.out.println("TableModelRS.setLookupText lookupText:"+lookupText);
         }
         catch (SQLException e)
         {
             System.out.println("TableModelRS.setLookupText "+e);//e.printStackTrace();
         }
         
         closeDB();
  }

 
  /* private boolean isColumnTableEqualsEntity(int c)
   {
   	    String table = recColumnMgt.getColumnTable(c);
        if (table.toUpperCase().equalsIgnoreCase(entity.toUpperCase()) )
        { return true ; }
        else
        {  return false;  }
   }*/
   
   
   public int getColumnLength(int c)
   {  
        // return recColumnMgt.getColumnLength(c); // doesnt work
       RecColumn col = (RecColumn)listTableColumns.get(c);
       return col.getColumnLength();
       
       
   }

    @Override
   public String getColumnName(int c)
   {  
   
   // return recColumnMgt.getColumnCaption(c);  // doesnt work
      RecColumn col = (RecColumn)listTableColumns.get(c);
      return col.getColumnCaption();       
   }

   public String getColumnDBName(int c)
   {  
   
   // return recColumnMgt.getColumnCaption(c);  // doesnt work
      RecColumn col = (RecColumn)listTableColumns.get(c);
      return col.getColumnName();       
   }


   public String getColumnTable(int c)
   {  
   
   // return recColumnMgt.getColumnCaption(c);  // doesnt work
      RecColumn col = (RecColumn)listTableColumns.get(c);
      return col.getColumnTable();
       
       
   }

   
   @Override
   public int getColumnCount()
   {  

       return listTableColumns.size();
      //return colCount;

   }
   
   @Override
   public int getRowCount()
   { 
       
      return dataVector.size();
      /*try
      {  ResultSet rs = getResultSet();
         rs.last();
         return rs.getRow();
      }
      catch(SQLException e)
      {  System.out.println("TableModelResultSet.getRowCount Error " + e);
         return 0;
      }*/
   }
      @Override
    public boolean isCellEditable(int row, int column)
    {
    	if (isEditable)
    	{   
    	   if(getColumnDBName(column).toUpperCase().equals(VariablesGlobal.columnNameInc.toUpperCase()))
    	   {
    	   	    return false;
    	   }
           else if(dbFieldsMany[column].getIsVisibleOrEditable()==FIELD_VISIBLE_NOT_EDITABLE_ALWAYS)
           {
               return false;
           }
           else if(dbFieldsMany[column].getIsVisibleOrEditable()==FIELD_NOT_VISIBLE)
           {
               return false;
           }           
           else // FIELD_VISIBLE_AND_EDITABLE
    	   {
    	        String tableName;
                
                RecColumn tableCol = (RecColumn)listTableColumns.get(column);
                tableName = tableCol.getColumnTable();
                  
                return true; 
       		    
       		    /*if (tableName.toUpperCase().equalsIgnoreCase(entity.toUpperCase()))
    		    {	      }
    		    else
    		    {      return false;         }*/
            }
        }
    	else
    	{  return false;  }
    	
          
    }

      @Override
   public Object getValueAt(int r, int c)
   {
        //Vector row = (Vector)dataVector.elementAt(r);
        //return row.elementAt(c);
      //System.out.println("TableModelResultSet.getValueAt "+r+" "+c+" size "+dataVector.size());
      
     Object ret = null;
     if(dataVector.size()==0)
     {
     	ret = null;
     }
     else if(r>this.getRowCount()-1 || c<=-1)
     {
         ret = null;
     }
     else
     {
     	
         //System.out.println("TableModelResultSet.getValueAt r"+r+" c"+c+" size "+dataVector.size());
      	ret =  ((Object[]) dataVector.elementAt(r))[c];
        
     	
     }
     
     
     String strObject = "";
     if(ret!=null)
     {
     	strObject = ret.toString();
     }
     else
     {
         ret="";
     }
     
     if(strObject.equalsIgnoreCase("true"))
     {
     	//System.out.println("true");
     	ret =  new Boolean(true);
     }
     else if(strObject.equalsIgnoreCase("false"))
     {
     	ret = new Boolean(false);
     }
     else
     {
     	//ret = 
     }
     
     
      return ret;
      
   /*   try
      {  ResultSet rs = getResultSet();
         rs.absolute(r + 1);
         return rs.getObject(c + 1);
      }
      catch(SQLException e)
      {  System.out.println("TableModelResultSet.getValueAt Error " + e);
         return null;
      }*/
   }

    private Object getValueFromDBTable(int row, String columnName, ResultSet rsGetValFromDb)
    {
    	


    	Object columnData="";

       try
       {        
        rsGetValFromDb.first();
      //System.out.println("TableModelRS.getValueFromDBTable  --> row="+row+" column"+columnName+" query "+query);
       rsGetValFromDb = db.retrieveRow(rsGetValFromDb, row);
       

 
          columnData =rsGetValFromDb.getObject(columnName).toString();
           //System.out.println("TableModelRS.getValueFromDBTable row="+row+" columnName="+columnName+" data="+columnData+"     query="+query);      
         
       }//try
       catch ( SQLException sqlex)
       {
           System.out.println("error:TableModelRS.getValueFromDBTable(): "+sqlex.getMessage());
           
       }
    	
    	 
      // closeDB();
      return columnData+"";
    }

    /*
    * get class as declared int entitydata.java
    */
    public String getColumnClassString(int column)
    {
    	RecColumn tableColumn = (RecColumn)listTableColumns.get(column);
        String colClass = tableColumn.getColumnClass();
        return colClass;
    }
    
    
    @Override
    public Class getColumnClass(int column)
    {
    	//System.out.println("column "+column);
    	RecColumn tableColumn = (RecColumn)listTableColumns.get(column);
        String colClass = tableColumn.getColumnClass();
        int type = tableColumn.getColumnType();

        
        //System.out.println("TableModelResultSet.getColumnClass col"+column+" type "+type+" "+tableColumn.getColumnName()+" "+listTableColumns.size());
        
        Object value = getValueAt(0,column);

        
        if(colClass.equalsIgnoreCase("java.lang.String"))
        {
            return String.class;
        }
        else if(colClass.equalsIgnoreCase("java.lang.Integer"))
        {
            return Integer.class;
        }
        else if(colClass.equalsIgnoreCase("java.lang.Double"))
        {
            return Double.class;  
        }
        else if(colClass.equalsIgnoreCase("java.util.Date") || colClass.equalsIgnoreCase("java.sql.Date"))
        {
            return java.util.Date.class;
        }
        else if(colClass.equalsIgnoreCase("java.lang.Boolean"))
        {   
            return Boolean.class;
        }
        else
        {
            System.out.println("  error  TableModelResultSet.getColumnClass() column:("+column+")  UNKNOWN  colClass:"+colClass);
            return Object.class;
        }
        
        //Boolean boolValue = Boolean.parseBoolean(value);
        
        /*switch(type)
        {
        	
        // case 12:
        //   return value.toString().equalsIgnoreCase("t") ? true : false ;
           //return Boolean.class;
        	
        case Types.CHAR:  // 1
        //case Types.VARCHAR:  // 12
        case Types.LONGVARCHAR:
            return String.class;
 
        case Types.VARCHAR:  // 12
        {
        	Class ret = String.class;
        	String val = null;
        	if(value!=null)
        	{
        		val = value.toString(); 	
        	  if(val.equalsIgnoreCase("true") || val.equalsIgnoreCase("false"))
        	  {
        		ret = Boolean.class;
        	  }
        	  else
        	  {
        		 ret = String.class;
        	  }
        	}
        	        	
        	return ret;
        }
        
        case Types.TINYINT:
        case Types.BIT:
        case Types.BOOLEAN:        	
            //return value.class;
            //return ((Boolean)value).booleanValue() ? "1" : "0";
            return Boolean.class;
        
        
        case Types.SMALLINT:
        case Types.INTEGER:
        {    
          return Integer.class;
        }
            //return Number.class;

        case Types.BIGINT:
            return Long.class;

        case Types.FLOAT:
           return Float.class;
        
        case Types.DOUBLE:
        case Types.DECIMAL: //    added by me
        {    
             //System.out.println("tableModelRS.getColumnClass Double col "+column);
             return Double.class;        
        }

        case Types.DATE: //91
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
        }*/
        //return Object.class;

    }

 
    
   public void setValuesAtRow(Object[] value, int row, String[] colName)
   {
       
       for(int i = 0;i<listTableColumns.size();i++)
       {
           RecColumn rc = (RecColumn)listTableColumns.get(i);
            String clnm =  "";
             clnm = rc.getColumnCaption();
             for(int k=0;k<colName.length;k++)
             {  
                 
               if(colName!=null && !clnm.equalsIgnoreCase(""))
               {
                   //System.out.println("TableModelResultSet.setValuesAtRow         i:"+i+"    listTableColumns:"+listTableColumns.size()+"   clnm:"+clnm+"   colName:"+colName[k]+"  value:"+value[k]);
                 if(colName[k].trim().toUpperCase().equalsIgnoreCase(clnm.trim().toUpperCase()))    
                 {
                 // System.out.println("TableModelResultSet.setValuesAtRow         i:"+i+"    listTableColumns:"+listTableColumns.size()+"   clnm:"+clnm+"  =  colName:"+colName[k]+"  value:"+value[k]);
                  setValueAt(value[k], row, i);
                 }
               }
             }
       }
   } 
    
    
   public void setValuesAtRow(Object[] value, int row, int[] col)
   { 
       for(int i = 0;i<value.length;i++)
       {
          setValueAt(value[i], row, col[i]);
       }
   }    
    
    /*
   * called by 
   */
   public void setDBFieldsValueAt(EntityDBFields[] dbFieldsManyIn)
   {
       dbFieldsMany  = dbFieldsManyIn;
       
   }   
   
   
   
     @Override
   public void setValueAt(Object value, int row, int col)
   {
         /*if(value.toString().trim().equalsIgnoreCase(getValueAt(row,col).toString().trim()))
         {*/
   	    hasDataChanged=true;
        /* }
         else
         {
              hasDataChanged=false;   
         }*/
   	//System.out.println("tableModelResultSet.setValueAt Set " + row + "," +col+ " to " + value + " ("/*an instance of "*/ + value.getClass() + ")   hasDataChanged"+hasDataChanged);
          //   class "+((Object[]) dataVector.elementAt(row))[col].getClass()+"
       //System.out.println("    o   tableModelRS.setValueAt     row:"+row+"  <  rowCount:"+this.getRowCount());
       if(value!=null && !value.equals("") && row<this.getRowCount()) // do not make it !value.toString().equalsIgnoreCase(""), it will not update cell. - rowcount row so it will call the 'else if'. row must not be equal or more than rowcount
       {
          
          
            if( value.getClass() == Integer.class)
            {
                //If we don't do something like this, the column
                //switches to contain Strings.

              	

                //System.out.println("TableModelRS.setValueAt: is integer");
                try
                {    // new Integer((String)value.toString().trim());
                    ((Object[])dataVector.elementAt(row))[col] = new Integer((String)value.toString().trim());
                    
                    fireTableCellUpdated(row, col);
                } catch (NumberFormatException e)
                {
                	System.out.println("TableModelRS.setValueAt: "+getColumnName(col)+" column accepts only integer values.");
                }

            }
            else if( value.getClass() == Double.class)
            {
                //If we don't do something like this, the column
                //switches to contain Strings.
                //System.out.println("TableModelRS.setValueAt: is double");
                try
                {    // convert string to double
                    //double d = Double.parseDouble(value.toString().trim());
                    double dbl= new Double((String)value.toString().trim());
                    
                   // System.out.println("TableModelRS.setValueAt: double "+dbl);

                    ((Object[])dataVector.elementAt(row))[col] = dbl;
                             
                    fireTableCellUpdated(row, col);
                }
                catch (NumberFormatException e)
                {
                	System.out.println("TableModelRS.setValueAt: "+getColumnName(col)+" column accepts only double values.");
                }
                /*catch(ArrayStoreException ase)
                {
                	//System.out.println("TableModelRS.setValueAt: "+ase.getStackTrace());
                	ase.printStackTrace();
                }*/ 
            }
            else if( value.getClass() == Boolean.class)
            {
                //If we don't do something like this, the column
                //switches to contain Strings.
                //System.out.println("TableModelRS.setValueAt: is Boolean");
                    ((Object[])dataVector.elementAt(row))[col] = new Boolean((String)value.toString().trim());
               fireTableCellUpdated(row, col);
            }
            else if(value.getClass() == String.class && value.toString().equalsIgnoreCase("0"))
            {
                System.out.println("  -0-  TableModelResultSet.setValueAt ELSE 0  value.getClass():"+value.getClass()+"  row:"+row+" col:"+col+" value:"+value+"  rowCount:"+this.getRowCount());
                ((Object[])dataVector.elementAt(row))[col] = "0";
                fireTableCellUpdated(row, col);                
            }
            else if(value.getClass() == String.class)
            {
                    ((Object[])dataVector.elementAt(row))[col] = value.toString().trim();
                    
                    fireTableCellUpdated(row, col);                        
            }            
            else  // value.getClass()
            {
                System.out.println("  -.-  TableModelResultSet.setValueAt ELSE A value.getClass():"+value.getClass()+"  row:"+row+" col:"+col+" value:"+value+"  rowCount:"+this.getRowCount());
                ((Object[])dataVector.elementAt(row))[col] = value;
                fireTableCellUpdated(row, col);
            }
            //System.out.println("TableModelResultSet.setValueAt  AFTER value.getClass():"+value.getClass()+" row:"+row+" col:"+col+" value:"+value+"  rowCount:"+this.getRowCount());
       }
       else if(value==null || value.equals("") && row<this.getRowCount())//                           // when a value in a cell is deleted it must update the cell. when there is additionaly one row that is empty and the user goes up and down the rows.
       {
           
       	 System.out.println("TableModelResultSet.setValueAt ELSE B row:"+row+" col:"+col+" value:"+value+"    getValueAt:"+getValueAt(row,col)+"   rowCount:"+this.getRowCount());
         ((Object[])dataVector.elementAt(row))[col] = value;
         fireTableCellUpdated(row, col);

       }
       else
       {
           System.out.println("TableModelResultSet.setValueAt ELSE C row:"+row+" col:"+col+" value:"+value+"    getValueAt:"+getValueAt(row,col)+"   rowCount:"+this.getRowCount());
         fireTableCellUpdated(row, col);  
       }
       
       // System.out.println(" +-+-+ TableModelResultSet.setValueAt   col:"+col+"   row:"+row+"   value:"+value+" getValueAt:"+getValueAt(row,col)+"   rowCount:"+this.getRowCount()); 
          // if data from lookup changed then change lookup text(the next cell)             
         RecColumn reccol = (RecColumn)listTableColumns.get(col);
         String foreignTable = reccol.getColumnForeignTable();

      // System.out.println(" +-+-+ TableModelResultSet.setValueAt   col:"+col+"   row:"+row+"   value:"+value+"    foreignTable:"+foreignTable+"     entityname:"+dbFieldsMany[col].getLookupEntityName());
       //System.out.println("TableModelResultSet.setValueAt    entityname:"+dbFieldsMany[col].getLookupEntityName()+"   integer:"+lookUp.getLookUpIntNoOfColsWhenInTable(dbFieldsMany[col].getLookupEntityName()));
        if(dbFieldsMany[col].getLookupEntityName()!=null)  
        {
           if(lookUp.getLookUpIntNoOfColsWhenInTable(dbFieldsMany[col].getLookupEntityName())==2)
           {          
         //if(isEditable && !foreignTable.equalsIgnoreCase("") && lookUp.getLookUpIntNoOfColsWhenInTable(foreignTable)==2)
         //{ 
           if (col!=columnLookupTwoB)
           {
             String strValue = value.toString();
             //int key = Integer.parseInt(strValue);
             //System.out.println("TableModelResultSet.setValueAt IF row:"+row+" col:"+col+" foreignTable:"+foreignTable+" strValue:"+strValue);
             setLookupText(strValue,foreignTable,row, col);
            }
           else
           {
             //System.out.println("TableModelResultSet.setValueAt ELSE row:"+row+" col:"+col+" foreignTable:"+foreignTable);  
           }
          }
        }
      // if(value!=null && !value.toString().equalsIgnoreCase(""))
       //{        
         //System.out.println(" -+- TableModelResultSet.setValue   col:"+col+"   row:"+row+"   value:"+value+" getValueAt:"+getValueAt(row,col));

                /*         String valueFromTable = getValueAt(row,col).toString(); 
                         if(dbFieldsMany[col].getColClassName().equalsIgnoreCase("java.lang.Double")) 
                         {
                             valueFromTable=utilsDouble.getDoubleSaving(valueFromTable);
                             if(utilsDouble.getDoubleSaving(value.toString()).toString().equalsIgnoreCase(valueFromTable))
                             {
                                 dbFieldsCalculateSet(dbFieldsMany,row,col);
                             }
                             else
                             {
                                // dbFieldsCalculateSet(dbFieldsMany,row,col);
                             }                             
                             
                         }
                         else
                         {
                            dbFieldsCalculateSet(dbFieldsMany,row,col);
                         } 
                   */

       //  System.out.println(" -+- TableModelResultSet.setValue   col:"+col+"   row:"+row+"   value:"+value+" getValueAt:"+getValueAt(row,col));
       
         
         
         
         /*for(int dc = 0 ; dc<dbFieldsMany.length; dc ++)
         {
             if(dc == col)
             {
                 for( int dr = 0 ; dr<this.getRowCount(); dr++)
                 {
                     if(dr==row)
                     {
                         dbFieldsCalculateSet(dbFieldsMany,row,col);
                     }
                 }
             }
         } */
         
         
         
         dbFieldsCalculateSet(dbFieldsMany,row,col);
         
       //  }

       //}
          
   }


   
   
   /*
   * exists in tableModelResultSet.dbFieldsCalculateSet (tableModelWritable.setValue) and PanelODORData.dbFieldsCalculateSet 
   * called by this.setValueAt
   */
   private void dbFieldsCalculateSet(EntityDBFields[] dbFieldsIn,int row,int col)
   {
        EntityDBFields dbFieldMany = null;
        //EntityDBFields[] dbFieldChild = null;
        
          String value ="";
            dbFieldMany = dbFieldsIn[col];
          String colName =   dbFieldsIn[col].getDbField();
          int columnWidth = dbFieldsIn[col].getColWidth();
          value = getValueAt(row,col).toString();
      	// System.out.println("++++++++++  TableModelResultSet.dbFieldsCalculateSet RUN col:"+col+" row:"+row+"   value:"+value+"   colName:"+colName+" class:"+dbFieldsIn[col].getColClassName());
         /* String classtype = dbFieldsIn[col].getColClassName();       
       String value ="";
               if(classtype.equalsIgnoreCase("java.sql.Date") || classtype.equalsIgnoreCase("java.lang.Date"))
              {

           	  JTextBoxWithEditButtons textEditFormatedDate = (JTextBoxWithEditButtons)fieldTxts.get(col);

                  
                  JTextComponent ta = (JTextComponent)textEditFormatedDate.getTextComp();
                  value = ta.getText();
           	 
              }
              else if (classtype.equalsIgnoreCase("java.lang.Boolean"))
              {
          	  	   //System.out.println("PanelOneDataOneRecData.showRow boolean "+field);
              	   JCheckBox chk = (JCheckBox) fieldTxts.get(col);//i-1);


              	   if(chk.isSelected())
              	   {
              	   	  value = "true";
              	   }
              	   else if(!chk.isSelected())
              	   {
              	   	  value="false";
              	   }
              	   else
              	   {
              	   	  System.out.println("PanelOneDataOneRecData.dbFieldsCalculateSet  boolean unknown value col:"+col+"  colName:"+colName+"     "+chk.isSelected());
              	   }
              	   
              	           	  	
              }
              else if(dbFieldsIn[col].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)             
              {
 
                   JComboBox cmbBox =  (JComboBox) fieldTxts.get(col);
                    value =  cmbBox.getSelectedItem().toString();
              }
              else
              {
                  //System.out.println("PanelOneDataOneRecData.dbFieldsCalculateSet  ("+col+")  colName:"+colName+"   classtype:"+classtype);
              	   JTextComponent tb = (JTextComponent) fieldTxts.get(col);
              	   value = tb.getText();
              }*/
               
               
               
               
        EntityDBFieldsCalculation[] fieldsCalculationSelect = dbFieldMany.getFieldsCalculationSelect();//.getFieldsCalculation();
        //EntityDBFields[] dbFieldChild = dbFieldsIn[noOfChildTableField].getDbChildFields();
        //fieldsCalculation = dbFieldMany.getFieldsCalculation();
        if(dbFieldMany!=null && fieldsCalculationSelect!=null && value!=null && !value.toString().equalsIgnoreCase(""))
        {
//        try
//        {
            
            for(int fc=0; fc<fieldsCalculationSelect.length;fc++)
            {
                int calculateCategoryField =  fieldsCalculationSelect[fc].getWhenSetThenCalculateCategoryField();
                int calculateField =  fieldsCalculationSelect[fc].getWhenSetThenCalculateField();
                int[] cellsCategoryInput = fieldsCalculationSelect[fc].getCalculationInputCategoryFields();
        	int[] cellsInput = fieldsCalculationSelect[fc].getCalculationInputFields();
        	String calculation =  fieldsCalculationSelect[fc].getCalculation();
        	String val = "";    
             
                /*if(calculateCategoryField!=FIELDSCALCULATION_CATEGORY_SAME) // if not FIELDSCALCULATION_CATEGORY_SAME
                {
                    System.out.println("error TableModelResultSet.dbFieldsCalculateSet   NOT = FIELDSCALCULATION_CATEGORY_SAME");
                }
                else
                {
                
                }*/
                
                
                     //System.out.println("TableModelResultSet.dbFieldsCalculateSet  FIELDSCALCULATION_CATEGORY_SAME  col:"+col+"    fc:"+fc+"    calculation:"+calculation);     
                //calculation= calculation+value;
               
             // int lengthOfArrayInputCells = 0;       
             // if is null then insert rubish data. If there is no character # in calulation there is no problem 
             if(cellsInput==null || cellsInput.length==0)
             {
                 //System.out.println("PanelODORData.dbFieldsCalculateSet textsInput:"+textsInput+"   for  fc:"+fc+"   calculation:"+calculation);
                 cellsInput = new int[1];
                 cellsInput[0] = 1;
                cellsCategoryInput = new int[1];
                cellsCategoryInput[0]  = FIELDSCALCULATION_CATEGORY_SAME;
             }
             
               /* for(int c=0;c<cellsInput.length;c++)
                {
                    
                  if(cellsCategoryInput[c]  == FIELDSCALCULATION_CATEGORY_SAME || cellsCategoryInput[c]  == FIELDSCALCULATION_CATEGORY_BACKWARD)
                  {                 
                    lengthOfArrayInputCells++;
                  
                  }
                }*/                      
                     
                ArrayList listTextStringHasTablesContainer = new ArrayList();
                ArrayList listTextString = new ArrayList();
                String[] cellString = new String[cellsInput.length];
                ArrayList fieldTxts = panelODORData.getFieldTxts();
                EntityDBFields[] dbFieldsBackwards = panelODORData.getDbFieldsRootAndChilds();
                for(int c=0;c<cellsInput.length;c++)
                {      
                      //System.out.println("TableModelResultSet.dbFieldsCalculateSet   (fc:"+fc+"  "+fieldsCalculation.length+")    c:"+c+"      cellsInput.length:"+cellsInput.length+"   cellsCategoryInput[c]:"+cellsCategoryInput[c]+"  getLookupType:"+dbFieldsBackwards[cellsInput[c]].getLookupType());
                      if( cellsCategoryInput[c]  ==FIELDSCALCULATION_CATEGORY_BACKWARD)
                      {
                       if(dbFieldsBackwards[cellsInput[c]].getLookupType()==LOOKUPTYPE_ONLYONE_THISFIELD)
                       {
                           
                          JTextComponent tbToGet = (JTextComponent)fieldTxts.get(cellsInput[c]);
                          cellString[c] = tbToGet.getText();
                          listTextString.add(cellString[c]);
                       }
                       else if (dbFieldsBackwards[cellsInput[c]].getLookupType()==LOOKUPTYPE_TABLECONSTANTS)
                       {  // showRow
                         LookupTableConstantsMgt lookUpTableConstants = new LookupTableConstantsMgt();
                         EntityLookupTableConstantsData[] elutcData = lookUpTableConstants.getEntityLookupTableConstantsData(dbFieldsBackwards[cellsInput[c]].getLookupEntityName());
              
              	            JComboBox cb = (JComboBox) fieldTxts.get(cellsInput[c]);
                            

                          cellString[c] = elutcData[cb.getSelectedIndex()].getPk();
                          listTextString.add(cellString[c]);
                               // }
                          //System.out.println("TableModelResultSet.dbFieldsCalculateSet   -selection-   index:"+cb.getSelectedIndex()+"  textsInput[c]:"+cellString[c]);
                            
  
                       }
                       else 
                       {
                           //System.out.println("  - error TableModelResultSet.dbFieldsCalculateSet    textsInput[c]:"+cellsInput[c]+" UNKNOWN:  getLookupType:"+dbFieldsBackwards[cellsInput[c]].getLookupType()+"  colName:"+colName);
                          JTextComponent tbToGet = (JTextComponent)fieldTxts.get(cellsInput[c]);
                          cellString[c] = tbToGet.getText();
                          listTextString.add(cellString[c]);                          
                            //System.out.println("  - error TableModelResultSet.dbFieldsCalculateSet    textsInput[c]:"+cellsInput[c]+" UNKNOWN:  getLookupType:"+dbFieldsIn[cellsInput[c]].getLookupType()+"  colName:"+colName);
                       } 
                      }
                      else if(cellsCategoryInput[c]  == FIELDSCALCULATION_CATEGORY_SAME)
                      {
                         //PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(textsCategoryInput[c]);
                          ArrayList listTextStringTableRow = new ArrayList();

                             String valueFromTable = getValueAt(row, cellsInput[c]).toString();
              //           System.out.println("TableModelResultSet.dbFieldsCalculateSet FIELDSCALCULATION_CATEGORY_SAME  table row("+row+")  cellsInput[c]:"+cellsInput[c]+"   calculateField:"+calculateField+"   colName:"+colName+"   =   "+valueFromTable);
                         // when is double it might be a double with comma not with dot which is what database handles, so we convert it to double
                         if(dbFieldsIn[cellsInput[c]].getColClassName().equalsIgnoreCase("java.lang.Double")) 
                         {
                             valueFromTable=utilsDouble.getDoubleSaving(valueFromTable);
                         }
                         else
                         {
                            
                         }                                 
                            listTextStringTableRow.add(valueFromTable);
                            
                            //System.out.println("PanelODORData.dbFieldsCalculateSet  table r("+r+")  c:"+c+"         textStringTable[c]:"+textStringTable[r][c]+"         textsInput[c]:"+ textsInput[c] +" for category:"+ textsCategoryInput[c]);
                         //}
                         //System.out.println("PanelOneDataOneRecData.dbFieldsCalculateSet add  listTextStringHasTablesContainer  textsCategoryInput[c]:"+textsCategoryInput[c]);
                         listTextStringHasTablesContainer.add(listTextStringTableRow);

                     }
                     else
                     {
                        System.out.println("TableModelResultSet.dbFieldsCalculateSet UNKNOWN cellsCategoryInput[c]:"+cellsCategoryInput[c] +" col:"+col+"  cellsInput[c]:"+cellsInput[c]+"="+cellString[c]+"  fc:"+fc+"    calculation:"+calculation);          
                     }
                      
                }
          
                
               
               
             if(calculateCategoryField!=FIELDSCALCULATION_CATEGORY_SAME) // if not FIELDSCALCULATION_CATEGORY_SAME
             {  // untested
                 
                  
                  ArrayList listRowTextStringAll = new ArrayList();

                         //System.out.println("PanelODORData.dbFieldsCalculateSet table col:"+col+"   listTextStringHasTablesContainer.size"+listTextStringHasTablesContainer.size());
                         for(int t = 0;t<listTextStringHasTablesContainer.size();t++)
                         {
                             ArrayList listTextStringTableRow = (ArrayList)listTextStringHasTablesContainer.get(t);
                             //PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(calculateCategoryField);
                        
                           System.out.println("TableModelResultSet.dbFieldsCalculateSet   t:"+t+"    "+calculateCategoryField+"        listTextString:"+listTextString.size()+"  listTextStringHasTablesContainer:"+listTextStringHasTablesContainer.size());
                             

                             //for(int r= 0 ;r< listTextStringTableRow.size();r++)
                             //{                                   
                             //  System.out.println("PanelODORData.dbFieldsCalculateSet c:"+c+" r:"+r+"   "+listTextStringTableRow.get(r).toString());
                                //textStringAll[c]= listTextStringTableRow.get(r).toString();
                             
                             //if(pnlODMRData.getRowCount()>0)
                             //{
                               //for(int r = 0 ;r<pnlODMRData.getRowCount();r++)
                               //{
                                String[] textStringAll = new String[cellsInput.length];
                                for(int c2 = 0;c2<cellsInput.length;c2++)
                                {
 
                                    if(cellsCategoryInput[c2]  == FIELDSCALCULATION_CATEGORY_SAME || cellsCategoryInput[c2]  ==FIELDSCALCULATION_CATEGORY_BACKWARD)
                                    {
                                        for(int tr = 0 ; tr<listTextString.size(); tr++)
                                        {
                                             
                                           textStringAll[c2]= listTextString.get(tr).toString();
                                           System.out.println("TableModelResultSet.dbFieldsCalculateSet (c2:"+c2+"   t:"+t+"    tr:"+tr+"    row:"+row+"     FIELDSCALCULATION_CATEGORY_SAME  textStringAll[c2]:"+textStringAll[c2]);
                                        }
                                    }
                                    else
                                    {
                                        //for(int tr =0 ;tr<listTextStringTableRow.size();tr++)
                                        //{   

                                            textStringAll[c2]= listTextStringTableRow.get(row).toString(); 
                                            System.out.println("TableModelResultSet.dbFieldsCalculateSet (c2:"+c2+")  t:"+t+"    r:"+row+"   ELSE  textStringAll[c2]:"+textStringAll[c2]);
                                             
                                        //}  

                                    }
                                   // System.out.println("PanelODORData.dbFieldsCalculateSet table  -  c2:"+c2+"  t:"+t+"  r:"+r+"  textStringAll[c2]:"+textStringAll[c2]);
                                    
                                }
                                  
                                  listRowTextStringAll.add(textStringAll); 
                                  //System.out.println("PanelODORData.dbFieldsCalculateSet table ADD     textStringAll:"+textStringAll);
                               //} 

                           // }
                         }

                             //PanelOneDataManyRecData pnlODMRData = (PanelOneDataManyRecData)fieldTxts.get(calculateCategoryField);                             
                             //ArrayList listTextStringTableRow = (ArrayList)listTextStringHasTablesContainer.get(t); 
                            // if(pnlODMRData.getRowCount()>0)
                            // {                             
                            // for(int r= 0 ;r< pnlODMRData.getRowCount();r++)
                             //{
                                 

                               //System.out.println("PanelODORData.dbFieldsCalculateSet -->     r:"+r+"   ");
                                //textStringAll[c]= listTextStringTableRow.get(r).toString(); 
                         
                         
                         
                                 String[]  textStringAll = (String[])listRowTextStringAll.get(row);
                               //for(int s =0;s<textStringAll.length;s++)
                               //{
                               //    System.out.println("TableModelResultSet.dbFieldsCalculateSet table  s:"+s+"    textStringAll:"+textStringAll[s]);
                               //}                     
                                String calculationEachRow = "";
                                int indexOfHashChar = calculation.indexOf("#");
                               
                               if(indexOfHashChar!=-1)
                               {    
                                calculationEachRow = utilsString.replaceTextOfAStringWithText("#", calculation, textStringAll, null);
                               }

                               //replaceTextOfAStringWithText(String textToBeReplacedBy, String str, String[] textToReplace)
                         System.out.println(" -o-o- PanelODORData.dbFieldsCalculateSet table col:"+col+" table row=("+row+")  calculateField:"+calculateField+"   textStringAll:"+textStringAll.length+"       calculationEachRow:"+calculationEachRow);                                   
                         
                                db.retrieveDBDataFromQuery(calculationEachRow,"TableModelResultSet.dbFieldsCalculateSet ");
   	                        rs=db.getRS();
  	          
                try
                {
                                if (rs!=null && rs.first())
                                {
                                       rs = db.retrieveRow(rs, 1);// go to the only row  
                                        //System.out.println(PanelODORData.dbFieldsCalculateSet   calculation "+calculation);
                                       val = rs.getString(1);// get field data	         	
                                }
                }//try
                catch ( SQLException sqlex)
                {
                   System.out.println("error:TableModelResultSet.dbFieldsCalculateSet: A "+sqlex.getMessage());
                   sqlex.printStackTrace();
                   closeDB(); 
                }            
                          // System.out.println("TableModelResultSet.dbFieldsCalculateSet table col:"+col+" table row=("+row+")  calculateField:"+calculateField+"  val:"+val+"  calculationEachRow:"+calculationEachRow);
                closeDB();               
                               setValueAt(val,row,calculateField);

                            // }      // for each r                    
                             
                        // }
             
                     
                
             }            
             else  // is FIELDSCALCULATION_CATEGORY_SAME true
             {       
                      /* String[]  textStringAll = (String[])listRowTextStringAll.get(row);
                       for(int s =0;s<textStringAll.length;s++)
                       {
                                   System.out.println("TableModelResultSet.dbFieldsCalculateSet table  s:"+s+"    textStringAll:"+textStringAll[s]);
                       } */    
                 
               for(int c=0;c<cellsInput.length;c++)
                {
                    
                    if(cellsCategoryInput[c]==FIELDSCALCULATION_CATEGORY_SAME)
                    {
                                          
                        cellString[c]= getValueAt(row,cellsInput[c]).toString();
                    }
                    else if(cellsCategoryInput[c]==FIELDSCALCULATION_CATEGORY_BACKWARD)
                    {
                        cellString[c] = listTextString.get(c).toString();
                    }
                    else
                    {
                        System.out.println("TableModelResultSet.dbFieldsCalculateSet ELSE UNKNOWN cellsCategoryInput[c]:"+cellsCategoryInput[c]);
                    }
                 //System.out.println("TableModelResultSet.dbFieldsCalculateSet table  c:"+c+"    textStringAll:"+cellString[c]);
                }                 
                 
                       
                 
                       String calculationEachRow = "";
                       int indexOfHashChar = calculation.indexOf("#");
                               
                       if(indexOfHashChar!=-1)
                       {  
                              calculation = utilsString.replaceTextOfAStringWithText("#", calculation, cellString, null);
                        //replaceTextOfAStringWithText(String textToBeReplacedBy, String str, String[] textToReplace)
                       } 
                        
    //    	System.out.println("TableModelResultSet.dbFieldsCalculateSet   col:"+col+"      calculateField:"+calculateField+"  fc:"+fc+"  calculation:"+calculation);
        	  //ResultSet rs;
                 db.retrieveDBDataFromQuery(calculation,"TableModelResultSet.dbFieldsCalculateSet");
   	          rs=db.getRS();
   	          
         try
         {
              if (rs.first())
              {
                 rs = db.retrieveRow(rs, 1);// go to the only row  
                 
                 val = rs.getString(1);// get field data	         	
              }                        
         }//try
         catch ( SQLException sqlex)
         {
             System.out.println("error:TableModelResultSet.dbFieldsCalculateSet: B "+sqlex.getMessage());
             sqlex.printStackTrace();
              
         }              
          closeDB();
          /*if(calculateField<col)// when a field has a calculation backwards
          {
                System.out.println(" ----- TableModelResultSet.dbFieldsCalculateSet calculateField:"+calculateField+"  col:"+col+" val:"+val);
       
          }  
          else
          {  */
          String valThisCell="1";
            String v = getValueAt(row,calculateField).toString();
           // if(col==this.getColumnCount()-1 && isCellEditable(row,col))
           // {
            valThisCell = getValueAt(row,col).toString();
           // }
        System.out.println(" --+--- TableModelResultSet.dbFieldsCalculateSet  col:"+col+"  row:"+row+"  colName:"+colName+"   calculateField:"+calculateField+"   val:"+val+"   v:"+v+"  valThisCell:"+valThisCell);
            if(val!= null && !val.trim().equalsIgnoreCase("") && !val.equalsIgnoreCase("0"))
            {
                         //String valueFromTable = getValueAt(row,calculateField).toString(); 
                         if(dbFieldsIn[col].getColClassName().equalsIgnoreCase("java.lang.Double")) 
                         {
                             v=utilsDouble.getDoubleSaving(v);
                             val=utilsDouble.getDoubleSaving(val);
                             if(val.equalsIgnoreCase(v))
                             {
                                 if(calculateCategoryField==FIELDSCALCULATION_CATEGORY_SAME)//0 for current fields
                                 {                                 
                                     
                                 }
                                 else
                                 {
                             //        System.out.println("  -  TableModelResultSet.dbFieldsCalculateSet NOT DEFINED   "+calculateCategoryField+"   colName:"+colName+"   calculateField:"+calculateField+"  col:"+col+"  val:"+val+"  v:"+v);
                                 }
                                 //System.out.println("  -  TableModelResultSet.dbFieldsCalculateSet  calculateField:"+calculateField+"  col:"+col+"  val:"+val+"  v:"+v);
                             }
                             else
                             {
                                 if(calculateCategoryField==FIELDSCALCULATION_CATEGORY_SAME)//0 for current fields
                                 {                                 
                                  System.out.println("  -  TableModelResultSet.dbFieldsCalculateSet "+calculateCategoryField+" colName:"+colName+"   calculateField:"+calculateField+"  col:"+col+"  val:"+val+"  v:"+v+"  value:"+value+"   valThisCell:"+valThisCell);
                                       //  if(utilsDouble.getDoubleSaving(value.toString()).toString().equalsIgnoreCase(valueFromTable))
                                     //if(col==this.getColumnCount() && isCellEditable(row,col))
                                     //{
                                     //    setValueAt(valThisCell, row, calculateField);
                                     //}
                                    // else
                                    // {
                                    
                                        setValueAt(val, row, calculateField);                                     
                                     //}
                                 } 
                                 else
                                 {
                            //         System.out.println("  -  TableModelResultSet.dbFieldsCalculateSet NOT DEFINED "+calculateCategoryField+"  colName:"+colName+"   calculateField:"+calculateField+"  col:"+col+"  val:"+val+"  v:"+v);
                                 }                                 

                             }                             
                             
                         }
                         else
                         {
                                 if(calculateCategoryField==FIELDSCALCULATION_CATEGORY_SAME)//0 for current fields
                                 {                              
                        //             System.out.println("  +  TableModelResultSet.dbFieldsCalculateSet IF "+calculateCategoryField+"  colName:"+colName+"    class:"+dbFieldsIn[col].getColClassName()+" calculateField:"+calculateField+"  col:"+col+"  val:"+val+"  v:"+v);
                                     
                                        setValueAt(val, row, calculateField);
                                 }
                         }

            }
            else
            {
                System.out.println(" O TableModelResultSet.dbFieldsCalculateSet ELSE "+calculateCategoryField+"  colName:"+colName+"    calculateField:"+calculateField+"  col:"+col+" val:"+val+"=v:"+v);
            }
          //}
             }
             
         //   } // is FIELDSCALCULATION_CATEGORY_SAME
             
            } // for fieldsCalculation.length
/*            closeDB(); 
         }//try
         catch ( SQLException sqlex)
         {
             System.out.println("error:TableModelResultSet.dbFieldsCalculateSet: "+sqlex.getMessage());
             closeDB(); 
         }
  */               		
        }
        else
        {
            
            System.out.println("TableModelResultSet.dbFieldsCalculateSet ELSE NOT DEFINED row:"+row+" col:"+col+"  fieldsCalculationSelect:"+fieldsCalculationSelect+"   dbField:"+dbFieldMany+"    value:"+value);  
            //    if(dbFieldMany!=null && fieldsCalculation!=null && value!=null && !value.toString().equalsIgnoreCase(""))
        }
//          closeDB();       
       
   }
  
  
  // set table initial state so later can be checked if something changed
  private void setTableInitialState()
  {
  	    // http://www.dreamincode.net/forums/index.php?showtopic=70005&st=0
  	    // Store the checksums of each row of the DefaultTableModel
        
      
      listRecsHashToBeRemoved =new ArrayList();
      listRecsHashToBeInserted =new ArrayList();
      
      //ArrayList<Integer> 
        checksums = new ArrayList<Integer>(this.getRowCount());
        // Get the data vector of the DefaultTableModel
        //for (Object row : this.getTableDataVector())
        Vector v =this.getTableDataVector();
        
        for(int r =0;r<v.size();r++)
        {    
            Object orow = v.get(r);
            checksums.add(orow.hashCode());
            //System.out.println("tableModelRS.setTableInitialState "+row.hashCode());
        }
        
        initialTableDataVector = new Vector();
        
                for(int ro = 0; ro<dataVector.size();ro++)
              	{
              		Object[] rec = new Object[listTableColumns.size()];
              		for(int co = 0 ;co<listTableColumns.size(); co++)
              		{
                         rec[co]=getValueAt(ro,co);
                         //System.out.println("tableModelRS.setTableInitialState "+rec[co]+" ro"+ro+" co"+co+" listTableColumns.size()"+listTableColumns.size());
                       }
                        
              	   initialTableDataVector.add(rec);
              	}  
  	  
  	  //rowsToBeDeleted = new ArrayList();
          //rowsToBeUpdatedQuery = new ArrayList();
                countOfJTableNewRowsBeforeSavedInDB=0;
  	
  }
 
  
  /*
   * 
   * exists in PanelOneDataOneRecData, PanelOneDataManyRecData, TableModelResultSet and PanelDataFilter
   */
   public ArrayList getListOfFieldsUncompleted()
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

   	     String columnName = getColumnDBName(c); 	   
              Class columnType = getColumnClass(c); 
              if(dbFieldsMany == null)
              {
                  System.out.println("error    null dbFieldsParent  TableModelResultSet.getListOfFieldsUncompleted");
              }
              /*else if(dbFieldsMany.length>1)
              {
                  System.out.println("error    more than 1 parent dbfield  TableModelResultSet.getListOfFieldsUncompleted  ("+c+") columnType:"+columnType+"  columnName:"+columnName+"   getTableName:"+dbFieldsMany[c].getTableName()+"    length   dbFieldsParent:"+dbFieldsParent.length+" dbFieldsMany:"+dbFieldsMany.length);
              }*/
              else if(dbFieldsMany.length > 0)//dbFieldsMany.length == 1 )
              {
              String fieldParentCaption = dbFieldsMany[c].getCaption();
   	      //System.out.println("TableModelResultSet.getListOfFieldsUncompleted  columnType =---> ("+c+") "+columnType+"     getTableName:"+dbFieldsMany[c].getTableName()+"     0 of parent field "+dbFieldsParent[0].getCaption());

               if(dbFieldsMany[c].getFieldObligatoryOrSuggest()==FIELD_OBLIGATORY)
               {
               	//System.out.println("TableModelRS.getListOfFieldsUncompleted obl "+columnType+" c"+c+" r"+r+" "+getValueAt(r,c).toString().trim());
                 String text = getValueAt(r,c).toString().trim();
                 if (columnType== Integer.class)
                 {  
               	  	   if(text.equals("0"))        	
               	  	   { 
               	  	   	 EntityMessage em =new EntityMessage(dbFieldsMany[c].getCaption(),FIELD_OBLIGATORY,r+1,c,fieldParentCaption);
               	  	   	 listMessages.add(em);               	  	   	  
                         // outputObligatory=outputObligatory+outputObligatoryFields+" "+dbFieldsMany[c].getCaption()+" στη γραμμή "+(r+1)+outputObligatoryFieldsEnd+"\n";               	  	   	
               	         // System.out.println("TableModelResultSet.getListOfFieldsUncompleted obligatory r"+r+" c"+c+" "+dbFieldsMany[c].getDbField()+" is empty ");
               	  	   }                 	
                 }           
                 else if (columnType== Double.class)
                 {
               	  	   if(utilsDouble.getDoubleReading(text,false).equals(utilsDouble.getDoubleReading("0",false)))        	
               	  	   { 
               	  	   	 EntityMessage em =new EntityMessage(dbFieldsMany[c].getCaption(),FIELD_OBLIGATORY,r+1,c,fieldParentCaption);
               	  	   	 listMessages.add(em);                	  	   	
               	  	   	 //outputObligatory=outputObligatory+outputObligatoryFields+" "+dbFieldsMany[c].getCaption()+" στη γραμμή "+(r+1)+outputObligatoryFieldsEnd+"\n";
               	         // System.out.println("TableModelResultSet.getListOfFieldsUncompleted obligatory r"+r+" c"+c+" "+dbFieldsMany[c].getDbField()+" is empty ");
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
               	  	   	 EntityMessage em =new EntityMessage(dbFieldsMany[c].getCaption(),FIELD_OBLIGATORY,r+1,c,fieldParentCaption);
               	  	   	 listMessages.add(em);                	  	   		
                         // outputObligatory=outputObligatory+outputObligatoryFields+" "+dbFieldsMany[c].getCaption()+" στη γραμμή "+(r+1)+outputObligatoryFieldsEnd+"\n";               	  	   			     
               	         // System.out.println("TableModelResultSet.getListOfFieldsUncompleted obligatory r"+r+" c"+c+" "+dbFieldsMany[c].getDbField()+" is empty ");
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
               	  	   	 EntityMessage em =new EntityMessage(dbFieldsMany[c].getCaption(),FIELD_SUGGEST,r+1,c,fieldParentCaption);
               	  	   	 listMessages.add(em);                	  	   	
               	  	   	//outputSuggest=outputSuggest+outputSuggestFields+" "+dbFieldsMany[c].getCaption()+" στη γραμμή "+(r+1)+outputSuggestFieldsEnd+"\n";	  	   	
               	  	   	// System.out.println("TableModelResultSet.getListOfFieldsUncompleted suggested r"+r+" c"+c+" "+dbFieldsMany[c].getDbField()+" is empty ");
               	  	   	
               	  	   }                 	
                 }           
                 else if (columnType== Double.class)
                 {
               	  	   if(utilsDouble.getDoubleReading(text,false).equals(utilsDouble.getDoubleReading("0",false)))      	
               	  	   { 
               	  	   	 EntityMessage em =new EntityMessage(dbFieldsMany[c].getCaption(),FIELD_SUGGEST,r+1,c,fieldParentCaption);
               	  	   	 listMessages.add(em);                  	  	   	
               	  	   	//outputSuggest=outputSuggest+outputSuggestFields+" "+dbFieldsMany[c].getCaption()+" στη γραμμή "+(r+1)+outputSuggestFieldsEnd+"\n";	  	   	
               	  	   	 //System.out.println("TableModelResultSet.getListOfFieldsUncompleted suggested r"+r+" c"+c+" "+dbFieldsMany[c].getDbField()+" is empty ");
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
               	  	   	 EntityMessage em =new EntityMessage(dbFieldsMany[c].getCaption(),FIELD_SUGGEST,r+1,c,fieldParentCaption);
               	  	   	 listMessages.add(em);   	                    	
	                     //outputSuggest=outputSuggest+outputSuggestFields+" "+dbFieldsMany[c].getCaption()+" στη γραμμή "+(r+1)+outputSuggestFieldsEnd+"\n";	  	   	       	  	   	
               	  	   	 //System.out.println("TableModelResultSet.getListOfFieldsUncompleted suggested r"+r+" c"+c+" "+dbFieldsMany[c].getDbField()+" is empty ");
               	  	   }
               	   
               	  }
               } 
               else if(dbFieldsMany[c].getFieldObligatoryOrSuggest()==FIELD_NOCOMPLETION)
               {
                   
               }
               else
   	       {
   	 	System.out.println("TableModelResultSet.checkIfFieldsAreUncompleted UNKNOWN dbFieldsMany[c].getFieldObligatoryOrSuggest()="+dbFieldsMany[c].getFieldObligatoryOrSuggest());
   	       }                   
               	
              }
              else
              {
                  System.out.println("TableModelResultSet.checkIfFieldsAreUncompleted UNKNOWN ELSE    dbFieldsParent");
              }
   	 	
   	    }// columns
   	   }// rows
   	   
        
        
       // System.out.println("PanelODORData.getListOfFieldsUncompleted \n"+output);   	   
   	 }
   	 else
   	 {
   	 	System.out.println("error  TableModelResultSet.checkIfFieldsAreUncompleted getColumnCount and dbFieldsMany NOT have equal length");
   	 }   	 
   	 
   	 return listMessages;
   }
 
  public boolean checkIfThereAreAnyChanges()
  {
      boolean ret =false;
      
   
   if(this.getTableDataVector().size()== 0 &&  checksums.size()== 0)
   {// not called from PanelODORData.rowUpdateTables. It checks there
      //utilsGui.showMessageInfo("Error. You should insert a record in the table.");
      ret =false;
   }
   else
   {
       if(this.getTableDataVector().size()!=checksums.size())
       {
           ret =true;
       }
       else
       {
          ret =false; 
        for(int r =0 ;r<this.getTableDataVector().size();r++)
  	{
  		Object row = this.getTableDataVector().get(r);
  	   if(checksums.size()>0)	
           {    
                //check each checksum row
  		for(int ch=0;ch<checksums.size();ch++)
  		{

            //System.out.println("tableModelRS.saveChanges compare -----> r"+r+" ch"+ch+" "+row.hashCode());
  		   if (row.hashCode() == checksums.get(ch))
    	           {
              	       for(int co = 0 ;co<listTableColumns.size(); co++)
              	       {
              		  if(((Object[])dataVector.elementAt(r))[co]!=null && ((Object[])initialTableDataVector.elementAt(ch))[co]!=null)
              		  {		
              //System.out.println("tableModelRS.saveChanges change "+co);
              		if(((Object[])dataVector.elementAt(r))[co].equals(((Object[])initialTableDataVector.elementAt(ch))[co]))
              	       {
              		     //System.out.println("tableModelRS.saveChanges no change "+((Object[])dataVector.elementAt(r))[co]);
              	       }
              	       else
              	       {
              
              	       
              	       	  //rowChange=ROW_CHANGE_UPDATE;
                           ret =true;
                           //System.out.println("tableModelRS.checkIfThereAreAnyChanges update r "+r+" "+ch+" change:"+ret);
              	       	  break;
              	       }
              		  }
                          else
                          {
                              //System.out.println(" -- tableModelRS.checkIfThereAreAnyChanges NEW r "+r+" "+ch+" ");
                          }
                      }// for
  		   }
  		   else //if different ie new
  		   {
                       //System.out.println(" -- tableModelRS.checkIfThereAreAnyChanges NEW r "+r+" "+ch+" ");
  		   }
  		}// for checksums
           }
           else // if (checksums.size()==0)
           {
           }
  	}// for this.getTableDataVector().size()
       }
   }
      //System.out.println("TableModelResultSet.checkIfThereAreAnyChanges   ===   this.getTableDataVector().size()"+this.getTableDataVector().size()+"  checksums.size()"+checksums.size()+"  are there any edit changes:"+ret);
      return ret;
  }
  
  /**
   * 
   * Checks if there is already a record with the same data in line.
   * @param line the line to be checked.
   */
  private boolean checkIfRecordsOfPrimaryKeysInLinesAreIdentical(int lineA, int lineB)
  {
      boolean ret=false;

       ArrayList listLine = new ArrayList();
             	    for(int i=0;i<dbFieldsMany.length;i++)// primKeysMany.length;i++)
             	    {

                                 int col = getColIntFromColName(dbFieldsMany[i].getCaption());
                               //System.out.println(" ----- TableModelResultSet.checkIfRecordsOfPrimaryKeysInLinesAreIdentical "+dbFieldsMany[i].getCaption()+" col:"+col);                
                                String strValueA = getValueAt(lineA,col)+"";
                                String strValueB = getValueAt(lineB,col)+"";
                             //System.out.println(" ----- TableModelResultSet.checkIfRecordsOfPrimaryKeysInLinesAreIdentical "+primKeysMany[i].toUpperCase()+" "+lineA +":"+strValueA+" "+lineB+":"+strValueB);                
                                if(strValueA.equalsIgnoreCase(strValueB))
                                {
                                   //System.out.println(" ----- TableModelResultSet.checkIfRecordsOfPrimaryKeysInLinesAreIdentical "+primKeysMany[i].toUpperCase()+" "+lineA +":"+strValueA+" "+lineB+":"+strValueB);
                                   listLine.add(col);
                                }

             	     }         
                    
                    if(listLine.size()==dbFieldsMany.length)
                    {
                        ret= true;
                    }
                    else
                    {
                        ret=false;
                    }

      return ret;
      
  }
  
  private boolean checkIfThereAreDoubleEntries()
  {
      boolean ret = false;
      ArrayList listR= new ArrayList();
      ArrayList listR2= new ArrayList();
      
    for(int r =0 ;r<this.getTableDataVector().size();r++)
    {
       for(int r2 =0 ;r2<this.getTableDataVector().size();r2++)
       {
          boolean isIdentical = checkIfRecordsOfPrimaryKeysInLinesAreIdentical(r,r2);
          if(r!=r2 && isIdentical)
          {
              listR.add(r);
              //listR.add(r2);
              listR2.add(r2);
              ret=true;
             //System.out.println("TableModelResultSet.checkIfThereAreDoubleEntries  ===   "+r+"   "+r2+"  "+isIdentical);    
          }
          else
          {

          }
       }
    }
    String strLine = "";
    //int int (Integer)listR.size();
   for(int i=0;i<listR.size();i++)
   {

       if(i==listR.size()-1)// show the last two records that are equal
       {
            strLine=strLine+"Line "+((Integer)listR.get(i)+1)+" is the same with line "+((Integer)listR2.get(i)+1)+". ";  
       }
       else
       {
           //System.out.println("TableModelResultSet.checkIfThereAreDoubleEntries  ===   Line ("+i+") "+listR.get(i)+"  is the same with line  "+listR2.get(i)+". ");
       }
   }
   if(ret)
   {
       
       utilsGui.showMessageError("There are double entries. Please delete or change one of the lines below.\n"+strLine);
    
   }
         
    
    
    return ret;
  }
  
  // called by PanelOneDataManyRecData.saveChanges
  public boolean saveChanges(Database dbTransaction, boolean insertAllRecs) throws SQLException  // insertAllRecs ie copy and template
  {
  	boolean hasBeenSaved=true;
       // boolean hasBeenInserted = false;
       // boolean hasBeenUpdated = false;
        //boolean hasBeenDeleted = false;
  	//System.out.println("tableModelRS.saveChanges "+getValueAt(1,2)+" "+((Object[])this.getTableDataVector().elementAt(1))[2]);


    
       deleteEmptyRowIfThereIsOne();
     ArrayList listSaveChangesQueries = new ArrayList();

   
     //System.out.println("TableModelResultSet.saveChanges  ===   this.getTableDataVector().size()"+this.getTableDataVector().size()+"  checksums.size()"+checksums.size());

  if(!checkIfThereAreAnyChanges())
  {
      

    
  }
  else
  {    
      if(checkIfThereAreDoubleEntries())
      {
          System.out.println("TableModelResultSet.saveChanges  ThereAreDoubleEntries:"+checkIfThereAreDoubleEntries() );
      }
      else
      {
        for(int r =0 ;r<this.getTableDataVector().size();r++)
  	{
  		Object row = this.getTableDataVector().get(r);
  	   if(checksums.size()>0)	
           {    
                //check each checksum row
  		for(int ch=0;ch<checksums.size();ch++)
  		{
  		   if (row.hashCode() == checksums.get(ch) && r==ch)
    	           {
                       System.out.println("tableModelRS.saveChanges compare -----> NO CHANGE r"+r+"=ch"+ch+" "+row.hashCode()+"     row.hashCode() == checksums.get(ch)");
              		for(int co = 0 ;co<listTableColumns.size(); co++)
              		{
              		  if(((Object[])dataVector.elementAt(r))[co]!=null && ((Object[])initialTableDataVector.elementAt(ch))[co]!=null)
              		  {		
                                //System.out.println("tableModelRS.saveChanges change "+co);
              	       if(((Object[])dataVector.elementAt(r))[co].equals(((Object[])initialTableDataVector.elementAt(ch))[co]))
              	       {
              		     //System.out.println("tableModelRS.saveChanges no change "+((Object[])dataVector.elementAt(r))[co]);

              	       }
              	       else
              	       {
                            System.out.println("  ........ tableModelRS.saveChanges UPDATE r:"+r+" ch:"+ch+"    row.hashCode():"+row.hashCode()+"  checksums.get(ch):"+checksums.get(ch));
              	       	  db.retrieveDBDataFromQuery(query,"TableModelResultSet.saveChanges");
                          ResultSet rsSaveChanges=db.getRS();
                          listSaveChangesQueries.add(rowDbToUpdateQuery(r,r+1,rsSaveChanges));  //  +1: db row
              	       	  break;
              	       }
              		  }
              		}// for

  		   }
                   else if (row.hashCode() == checksums.get(ch) && r!=ch)
                   {  
                       //change the rows that are below delete or insert
                             System.out.println("tableModelRS.saveChanges --compare -----> CHANGE (update row below new or delete r)    r:"+r+"!=ch:"+ch+"    row.hashCode():"+row.hashCode()+" == checksums.get(ch):"+checksums.get(ch)+"   listRecsHashToBeInserted.size():"+listRecsHashToBeInserted.size()+"   vector size:"+this.getTableDataVector().size()+"    checksums size:"+checksums.size()+"  query:"+query);
                             db.retrieveDBDataFromQuery(query,"TableModelResultSet.saveChanges");
                             ResultSet rsSaveChanges=db.getRS();
                             listSaveChangesQueries.add(rowDbToUpdateQuery(r,ch+1,rsSaveChanges));// r : current jtable row, ch : old row, +1: db row
                             //hasBeenUpdated=true;                        
                   }
                   else if(row.hashCode() != checksums.get(ch) && r==ch)
                   {//see bellow,deletes and inserts later than updates
                                  
                   }
                   else if(row.hashCode() != checksums.get(ch) && r>ch && r==this.getTableDataVector().size()-1 && ch==checksums.size()-1)
                   {
                       System.out.println("tableModelRS.saveChanges   --compare -----> CHANGE (is new  r) r:"+r+">ch:"+ch+"    row.hashCode() != checksums.get(ch) size:"+this.getTableDataVector().size());
//                       listSaveChangesQueries.add(rowDbToInsertQuery(r));
                       //hasBeenInserted=true;
                   }                   
  		   else //if different ie new
  		   {
                       // System.out.println("tableModelRS.saveChanges compare --else-->   r:"+r+" ch:"+ch+"    row.hashCode() != checksums.get(ch)");
  		   }
  		   	   
  		}// for checksums
           }
           else // if (checksums.size()==0)
           {

                   System.out.println(" OOOOO  TableModelRS.saveChanges change NEW  ("+r+")");
                        
//                       listSaveChangesQueries.add(rowDbToInsertQuery(r));
                       //hasBeenInserted=true;                   
                   
        
        
           }
  	}// for this.getTableDataVector().size()
  	
        
        
    System.out.println("tableModelRS.saveChanges  --->>>> checksums.size:"+checksums.size());  
   if(checksums.size()>0)
   {        
                //ArrayList listRowToBeDeleted = new ArrayList();
                ArrayList listRowDescedingToBeDeleted = new ArrayList();
 		for(int ch=0;ch<checksums.size();ch++)
  		{         
                       // first get max to min order and then delete
                          for (int rem=0; rem<listRecsHashToBeRemoved.size();rem++)
                          {
                //System.out.println("tableModelRS.saveChanges  --compare -----> CHANGE (delete ch+1) "+ listRecsHashToBeRemoved.get(rem)+"=="+checksums.get(ch)+"    ch:"+ch+" "+rem);                              
                                     //Object orow = v.get(row);
                             int intListHashDel = Integer.parseInt(listRecsHashToBeRemoved.get(rem).toString());
                             int intCheckSums = Integer.parseInt(checksums.get(ch).toString());

                             if(  intListHashDel == intCheckSums)
                             { 
                               listRowDescedingToBeDeleted.add(ch+1);                                 
                               //System.out.println("tableModelRS.saveChanges  --compare -----> CHANGE (delete) ("+listRecsHashToBeRemoved.get(rem)+")   ch:"+ch+"    listRecsHashToBeRemoved() == checksums.get(ch)   "+listRecsHashToBeRemoved.size());
                             }
                             else
                             {
                               //System.out.println("tableModelRS.saveChanges  --compare -----> CHANGE (delete ) ("+listRecsHashToBeRemoved.get(rem)+")   ch:"+ch+"    listRecsHashToBeRemoved() != checksums.get(ch)   "+listRecsHashToBeRemoved.size());
                             }
                          }        
                }               
                  Collections.sort(listRowDescedingToBeDeleted, Collections.reverseOrder()); 
            System.out.println("      TableModelRS.saveChanges.listRowDescedingToBeDeleted.size():"+listRowDescedingToBeDeleted.size());
                  for(int l=0;l<listRowDescedingToBeDeleted.size();l++)
                  {
                      int rowDel =  Integer.parseInt(listRowDescedingToBeDeleted.get(l).toString());

                      listSaveChangesQueries.add(rowDbToDeleteQuery(rowDel));
                     //hasBeenDeleted=true;                
                  }
                
   }
   
   if(this.getTableDataVector().size()>0)
   {
        for(int r =0 ;r<this.getTableDataVector().size();r++)
  	{
    	       Object row = this.getTableDataVector().get(r);

               for(int l=0;l<listRecsHashToBeInserted.size();l++)
               {
                   int rowIns =  Integer.parseInt(listRecsHashToBeInserted.get(l).toString());
                  
  	           if (row.hashCode() == rowIns ) 
                   {
                       listSaveChangesQueries.add(rowDbToInsertQuery(r));
                       System.out.println("     oooooo    TODO  TableModelRS.saveChanges change NEW  ("+r+") "+rowIns);
                   }
                      
                     
                     //listSaveChangesQueries.add(rowDbToInsertQuery(r));
                     // listSaveChangesQueries.add(rowDbToInsertQuery(rowIns));
                     //hasBeenDeleted=true;                
               }       
         }         
   }
   else
   {
       // remove all rows in jtable
   }
   
   
   System.out.println("     oooooo    TableModelRS.saveChanges   insertAllRecs:"+insertAllRecs+"    this.getTableDataVector().size():"+this.getTableDataVector().size());
   if(insertAllRecs)
   {
       listSaveChangesQueries.clear();
       if(this.getTableDataVector().size()>0)
       {
                for(int l=0;l<getTableDataVector().size();l++)
               {
                   System.out.println("     oooooo    TableModelRS.saveChanges   insertAllRecs:"+insertAllRecs+"    "+l+"    rowDbToInsertQuery(l):"+rowDbToInsertQuery(l));
                       listSaveChangesQueries.add(rowDbToInsertQuery(l));
               }
                            
       }
   }
   
        //when a row is added last then areThereAnyNewAndDeleteRowChanges = true;
/*        if ((this.getTableDataVector().size()!= checksums.size()) &&  checksums.size()>0) // the second part: if only one new row
        {
            areThereAnyNewAndDeleteRowChanges = true;
        }
  	
  	for(int r =0 ;r<this.checksums.size();r++)
  	{
  	   listRowAll.add(r);
  	}
  	// get not in all not updt = new
  	Collection collectionRowNew = disjunction(listRowAll,listRowUpdOrNotUpdt);

       Iterator itr = collectionRowNew.iterator();
       while( itr.hasNext() )
       {
         int integ = (Integer)itr.next();
         listRowNew.add(integ);
         listRowsChanged.add(integ,ROW_CHANGE_NEW);
       }
*/
//        db.retrieveDBDataFromQuery(query,"TableModelResultSet.saveChanges");
//        ResultSet rsSaveChanges=db.getRS();
   	    
/*        int deletedRowOfChecksumsList = 0;

    
        //System.out.println("TableModelRS.saveChanges   ===   listRowsChanged:"+listRowsChanged.size()+"   checksums.size():"+checksums.size());
  	for (int rc=0;rc<listRowsChanged.size();rc++)
  	{
  		//System.out.println("tableModelRS.saveChanges NEW  ---B- "+rc+" "+listRowsChanged.get(rc));

                if((Integer)listRowsChanged.get(rc)==ROW_CHANGE_NEW)  //insert rows
  		{
  		        //System.out.println("  ---    TableModelRS.saveChanges ROW_CHANGE_NEW rc"+rc+" count "+listRowsChanged.size()+" checksums.size()"+checksums.size());
                        areThereAnyNewAndDeleteRowChanges = true;
  		}
                else if ((Integer)listRowsChanged.get(rc)==ROW_CHANGE_DELETE)
  		{
                    areThereAnyNewAndDeleteRowChanges = true;
                    //System.out.println("TableModelRS listRowsChanged ROW_CHANGE_DELETE rc"+rc+" "+(Integer)listRowsChanged.get(rc));
  		}
                else if((Integer)listRowsChanged.get(rc)==ROW_CHANGE_UPDATE)// update rows
  		{
                    //System.out.println("tableModelRS.saveChanges ROW_CHANGE_UPDATE rc"+rc+" count "+listRowsChanged.size()+" checksums.size()"+checksums.size());
  		}
  		else if((Integer)listRowsChanged.get(rc)==ROW_CHANGE_NOT)  // resave all
  		{
                    //System.out.println("tableModelRS.saveChanges ROW_CHANGE_NOT rc"+rc+" count "+listRowsChanged.size()+" checksums.size()"+checksums.size());
  		}
                else
                {
                    System.out.println("TableModelRS.saveChanges -UNKNOWN- rc"+rc+" count "+listRowsChanged.size()+" checksums.size()"+checksums.size());
                }
  		//System.out.println("TableModelRS.saveChanges - "+rc+" "+listRowsChanged.get(rc)+" areThereAnyNewAndDeleteRowChanges="+areThereAnyNewAndDeleteRowChanges);	
  	//System.out.println("  ---    TableModelRS.saveChanges  ("+rc+") count "+listRowsChanged.size()+" checksums.size()"+checksums.size()+"   "+(Integer)listRowsChanged.get(rc));
        } 
*/
           //  //delete all db rows --------------------------------------- re view
       // System.out.println("--- TableModelResultSet.saveChanges before all del areThereAnyNewAndDeleteRowChanges="+areThereAnyNewAndDeleteRowChanges); 
   
/*   if(checksums.size()>0)
   {
        if(areThereAnyNewAndDeleteRowChanges)
         {
                
             // TO DO if there are any new at the end just add them
             
                 String queryDeleteAll= rowDbAllToDelete();  	
  	    if (VariablesGlobal.globalShowSQLEdit)
            {  System.out.println("tableModelResultSet.saveChanges DELETE query: "+queryDeleteAll);  }
            
             //System.out.println("tableModelResultSet.saveChanges DELETE query: r="+r+" query="+q);
             db.updateQuery(queryDeleteAll,"tableModelResultSet.saveChanges", showDialogOnError);  
            

         }
         else
         {

         }
   }     
*/        
        //make all db changes bellow
/*         boolean hasBeenUpdated = true;
           for (int rc=0;rc<listRowsChanged.size();rc++)
           {
                if((Integer)listRowsChanged.get(rc)==ROW_CHANGE_UPDATE)// update rows
  		{
                    //System.out.println("tableModelRS.saveChanges ROW_CHANGE_UPDATE rc"+rc+" count "+listRowsChanged.size()+" checksums.size()"+checksums.size());
                    
                    if(areThereAnyNewAndDeleteRowChanges)
                    {
  
                    }
                    else
                    {
                        //System.out.println("TableModelResultSet.saveChanges update rc ("+rc+") areThereAnyNewAndDeleteRowChanges="+areThereAnyNewAndDeleteRowChanges);
                       if(rowDbToUpdate(rc,rsSaveChanges))
                       {
                           hasBeenUpdated=true;
                       }
                       else
                       {
                           hasBeenUpdated=false;
                           break;
                       }
                    }
  		}         
           }
*/         
         // insert all the jtable rows  --------------------------------------- re view
/*           boolean hasBeenInserted = true;
         if(areThereAnyNewAndDeleteRowChanges)
         {
              for(int r =0 ;r<this.getTableDataVector().size();r++)
  	      {
                if(rowDbToInsert(r))
                {
                    hasBeenInserted=true;
                }
                else
                {
                    hasBeenInserted=false;
                    break;
                }
              }     
         }        
*/
        //System.out.println("tableModelRS.saveChanges  count "+listRowsChanged.size()+" checksums.size()"+checksums.size()+" areThereAnyNewAndDeleteRowChanges="+areThereAnyNewAndDeleteRowChanges);
 
/*        boolean isThereAnyChange =false;        
       //  System.out.println("TableModelResultSet.saveChanges  checksums.size()"+checksums.size());          

         
       if(checksums.size()!=0)
       {
             checksums.remove(deletedRowOfChecksumsList);       

  	// for (int rc=0;rc<checksumsRem.size();rc++)
           for (int rc=0;rc<checksums.size();rc++)
  	   {
		if((Integer)listRowsChanged.get(rc)==ROW_CHANGE_NOT)  // resave all
		{
                     isThereAnyChange=false;
		}
		else
		{
		   isThereAnyChange =true;
		   break;
		}
           }
       }*/
    System.out.println("TableModelResultSet.saveChanges  === dbTransaction:"+dbTransaction+"        listSaveChangesQueries.size():"+listSaveChangesQueries.size());
     if(listSaveChangesQueries.size()>0)
     {
         for (int lq =0;lq<listSaveChangesQueries.size();lq++)
         {
             
            int intRet = 0;

            if (VariablesGlobal.globalShowSQLEdit)
            {  System.out.println("tableModelResultSet.rowDbToUpdate -SAVE CHANGES- recs  "+intRet+"      listSaveChangesQueries.size():"+listSaveChangesQueries.size()+"  query: "+listSaveChangesQueries.get(lq).toString());  }            
            
               intRet = dbTransaction.transactionUpdateQuery(listSaveChangesQueries.get(lq).toString(),"tableModelResultSet.saveChanges",showDialogOnError); 

            
         }
         hasBeenSaved=true;
  	 hasDataChanged =false;         
         setTableInitialState();//  countOfJTableNewRowsBeforeSavedInDB=0;
     }
     else
     {
         hasBeenSaved=false;
     }
   

      }// if  !checkIfThereAreDoubleEntries()
  } // if checkIfThereAreAnyChanges
  
  
     closeDB();
     return hasBeenSaved;
  }

  private String rowDbToInsertQuery(int currentTableRow)
  {
     // boolean ret =false;
      System.out.println("tableModelRS.rowDbToInsertQuery(int) "+currentTableRow);
  	/*   globalYear =VariablesGlobal.globalYear;
       globalDeliveryId =VariablesGlobal.globalDeliveryId;
       globalCompanyId=VariablesGlobal.globalCompanyId;*/
    
    String [] columnName;
  	colCount = getColumnCount();
  	columnName = new String[dbFieldsMany.length];

         String subqueryFields="(";
         String subqueryValues="(";

         
         //             utilsPanelReport.retrievePrimKeyValueForOnePK( query, currentTableRow, null,dbFieldsMany,true,/*primKeyIn,intColumnOfDescriptionIn,
         //              sql2WhereField, sql2WhereValue,*/ entity, /*tableModelReadOnly,*/ null);    
                       
         /*               String[] primKeys = utilsPanelReport.getPrimKeys();
                        int primKeysCount = primKeys.length;
                        String[] primKeysValue = utilsPanelReport.getPrimKeysValue();         
         */
        for (int f=0;f<dbFieldsMany.length;f++)  
        {
                  String colName = dbFieldsMany[f].getDbField();//rsmd.getColumnLabel(i); //get colunm name  
                  String colCaption = dbFieldsMany[f].getCaption();//rsmd.getColumnLabel(i); //get colunm name 
                  
             	 	int col = getColIntFromColName(colCaption);// from jtable
             	 	
             	 	if(col==-1)
             	 	{
             	 		System.out.println("tableModelRS.rowDbToInsertQuery(int) column "+dbFieldsMany[f].getDbField()+" doesn't have an equal col name");
             	 	}                  
                  
             if(dbFieldsMany[f].getLookupType()==LOOKUPTYPE_TWO_SECONDFIELD)
             {
                 
                 
             }
             else if(dbFieldsMany[f].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY_FROM_PARENTTABLE)
             {
                      //System.out.println("TableModelResultSet.addEmptyRow getColumnDBName(i):"+getColumnDBName(i)+"   "+dbFieldsMany[i].getPrimaryKeyIntegerAutoInc()+"=="+FIELD_PRIMARY_KEY_FROM_PARENTTABLE+"  assign value:"+primKeyValue);
           //         record[i]=primKeyValue;
                  if(dbFieldsMany.length>1 && f<dbFieldsMany.length-1   )
                  {
                          subqueryFields=subqueryFields+colName+", ";
                          subqueryValues=subqueryValues+primKeyValue+", ";
                  }
                  else
                  {
                  	  subqueryFields=subqueryFields+colName;
                          subqueryValues=subqueryValues+primKeyValue;                          
                  }                 
                 
                 
             }              
             else
             {

                if(subqueryFields.indexOf(colName)== -1) //when there is a column name two times. For example in farmersvat in applicationline dbfields thne column buyerid(when we have two columns for buyerid and buyername)
                {
                  //System.out.println("tableModelRS.rowDbToInsertQuery  IF  colName"+ colName+"  index:"+subqueryFields.indexOf(colName));
                  if(dbFieldsMany.length>1 && f<dbFieldsMany.length-1   )
                  {
                          subqueryFields=subqueryFields+colName+", ";
                          subqueryValues=subqueryValues+formatValueForDb(col, getValueAt(currentTableRow, col))+", ";
                  }
                  else
                  {
                  	  subqueryFields=subqueryFields+colName;
                          subqueryValues=subqueryValues+formatValueForDb(col, getValueAt(currentTableRow, col));                          
                  }
                }
                else
                {
                    //System.out.println("tableModelRS.rowDbToInsertQuery ELSE  colName:"+colName+" index:"+subqueryFields.indexOf(colName));
                }
/*          	     String whereValueName = getWhereValueNameThatMatchesColumn(columnLabel);
                 //System.out.println("tableModelRS.rowDbToInsert(int) "+i+" "+columnLabel);
                     
          	   if(!whereValueName.equalsIgnoreCase("-"))// from global variables
          	   {   
                     if(fieldsManyTranslationOnInsert.length>1 && i<fieldsManyTranslationOnInsert.length-1   )
                     {
            	         subqueryValues=subqueryValues+getValueForVariable(whereValueName)+", ";
            	     }
            	     else
            	     {
            	     	subqueryValues=subqueryValues+getValueForVariable(whereValueName);
            	     }
     //       	     System.out.println("tableModelRS.rowInsert(int) i"+i+" "+columnLabel+" "+whereValueName);                     
                   } 
             	  else
             	  {*/


             	 	//System.out.println("tableModelRS.rowInsert(int) i"+i+" "+columnLabel+" fieldsM "+fieldsManyOnInsert[i]+" col"+col);
                    
              /*       if(dbFieldsMany.length>1 && f<dbFieldsMany.length-1  )
                     {
             	 	     subqueryValues=subqueryValues+formatValueForDb(col, getValueAt(currentTableRow, col))+", ";
             	 	     //System.out.println("tableModelRS.rowInsert(int) "+columnLabel+" "+col+" ("+getValueAt(currentTableRow, col)+") "+"i"+i);
             	     }
             	     else
             	     {
             	 	    subqueryValues=subqueryValues+formatValueForDb(col, getValueAt(currentTableRow, col));
             	     }*/
             } //else

        }
           subqueryFields=subqueryFields+")";
           subqueryValues=subqueryValues+")";
                     
        //}
     /*  try
       {
       	 System.out.println("tableModelRS.rowDbToInsert(int) for length:"+fieldsManyTranslationOnInsert.length+" "+fieldsManyOnInsert.length);
          for (int i = 0; i < fieldsManyTranslationOnInsert.length; i++)//  i = fields
          //for (int i = 0; i < listTableColumns.size(); i++)//  i = fields
          {
          	  // RecColumn tableColumn = (RecColumn)listTableColumns.get(i);
              // System.out.println("tableModelRS.rowInsert "+tableColumn.getColumnName() );
                  
            	  String columnLabel = fieldsManyOnInsert[i];//rsmd.getColumnLabel(i); //get colunm name  
                  //System.out.println("tableModelRS.rowInsert(int) columnLabel"+ columnLabel+" "+i);
                  if(fieldsManyTranslationOnInsert.length>1 && i<fieldsManyTranslationOnInsert.length-1   )
                  {
                     subqueryFields=subqueryFields+columnLabel+", ";
                  }
                  else
                  {
                  	  subqueryFields=subqueryFields+columnLabel;
                  }
                  
          	     String whereValueName = getWhereValueNameThatMatchesColumn(columnLabel);
                 //System.out.println("tableModelRS.rowDbToInsert(int) "+i+" "+columnLabel);		  
          	   if(!whereValueName.equalsIgnoreCase("-"))// from global variables
          	   {   
                     if(fieldsManyTranslationOnInsert.length>1 && i<fieldsManyTranslationOnInsert.length-1   )
                     {
            	         subqueryValues=subqueryValues+getValueForVariable(whereValueName)+", ";
            	     }
            	     else
            	     {
            	     	subqueryValues=subqueryValues+getValueForVariable(whereValueName);
            	     }
     //       	     System.out.println("tableModelRS.rowInsert(int) i"+i+" "+columnLabel+" "+whereValueName);
             	  }
             	  else
             	  {
             	 	//System.out.println("tableModelRS.rowInsert i"+i);
             	 	//int col = getColIntFromColName(columnLabel);//fieldsManyTranslation[i-1]);// from jtable
             	 	int col = getColIntFromColName(fieldsManyTranslationOnInsert[i]);// from jtable
             	 	
             	 	if(col==-1)
             	 	{
             	 		System.out.println("tableModelRS.rowDbToInsert(int) column "+fieldsManyTranslationOnInsert[i]+" doesn't have an equal col name");
             	 	}
             	 	//System.out.println("tableModelRS.rowInsert(int) i"+i+" "+columnLabel+" fieldsM "+fieldsManyOnInsert[i]+" col"+col);
                    
                     if(fieldsManyTranslationOnInsert.length>1 && i<fieldsManyTranslationOnInsert.length-1  )
                     {
             	 	     subqueryValues=subqueryValues+formatValueForDb(col, getValueAt(currentTableRow, col))+", ";
             	 	     //System.out.println("tableModelRS.rowInsert(int) "+columnLabel+" "+col+" ("+getValueAt(currentTableRow, col)+") "+"i"+i);
             	 	 }
             	 	 else
             	 	 {
             	 	 	subqueryValues=subqueryValues+formatValueForDb(col, getValueAt(currentTableRow, col));
             	 	 }
             	 }  
             	 	
           }
           subqueryFields=subqueryFields+")";
           subqueryValues=subqueryValues+")";

       }
       catch(Exception e)
       {  System.out.println("Error tableModelRS.rowDbToInsert(int)" + e);
       }*/

      String insertQuery = "INSERT INTO "+entity+" "+subqueryFields+
          " VALUES "+ subqueryValues;
       
      /* if (VariablesGlobal.globalShowSQLEdit)
       { System.out.println("tableModelRS.rowDbToInsert(int) -->"+currentTableRow+" insertQuery:"+insertQuery);}
       
      int intRet = db.updateQuery(insertQuery,"tableModelRS.rowDbToInsert(int)",showDialogOnError); // if row is only one, but may be many so develop
      if(intRet==1)
      {
          ret=true;
      }*/
      
      return insertQuery;
  }  
  

  // copy from rowDbToUpdateAfterDelete 
  private String rowDbToUpdateQuery(int jtablerow, int dbTableRow, ResultSet rsRowDbToUpd)
  {

            String setClause ="";
            for(int c=0;c<listTableColumns.size();c++)
            {
    	       RecColumn tableColumn = (RecColumn)listTableColumns.get(c);
                 //System.out.println("OOOO err TableModelResultSet "+c+listTableColumns.get(c)+"  "+listTableColumns.size());
                 //if table has a lookup column from an other table do not add
                 if(!tableColumn.getColumnTable().toUpperCase().equals(entity.toUpperCase()))
                 {
            	    System.out.println("tableModelRS.rowDbToUpdateQuery different table for col:"+c+"("+tableColumn.getColumnName()+") tableColumn:"+tableColumn.getColumnTable()+" entity:"+entity+" NOT EQUAL tableColumn.getColumnTable()"+tableColumn.getColumnTable());
                 }
            	 else
            	 {
            	   String colDbName = tableColumn.getColumnName();
            	   if(c!=0)// && formatValueForDb(c, getValueAt(jtablerow,c)).toString().equalsIgnoreCase(""))
            	   {
            	  	//System.out.println("----- err TableModelResultSet IF "+colDbName+" "+c+"  "+getValueAt(jtablerow,c)+"      value:"+formatValueForDb(c, getValueAt(jtablerow,c)));//formatValueForDb(c, getValueAt(currentTableRow,c)));  
                       
                        if(!getValueAt(jtablerow,c).toString().equalsIgnoreCase(""))
                        {
                           setClause = setClause+ "," + colDbName +" = "+formatValueForDb(c, getValueAt(jtablerow,c));
                        }
                         
            	   }
                   else
            	   {   
            	      //System.out.println("----- err TableModelResultSet ELSE "+colDbName+" "+c+"  "+getValueAt(jtablerow,c)+"   value:"+formatValueForDb(c, getValueAt(jtablerow,c)));   //formatValueForDb(c, getValueAt(currentTableRow,c)));
                      if(!getValueAt(jtablerow,c).toString().equalsIgnoreCase(""))
                      { 
                        setClause = colDbName +" = "+formatValueForDb(c, getValueAt(jtablerow,c));	
                      }
                 //      System.out.println("-----TableModelResultSet.rowDbToUpdate "+colDbName+ " "+getValueAt(currentTableRow,c)+" currentTableRow="+currentTableRow+" initialTableRow="+initialTableRow+" c="+c);
            	   }
            	 }               
            }
            
            //System.out.println("tableModelRS.rowDbToUpdate               setClause:"+setClause);
             String whereClause = "";
            
             

             
 
                       utilsPanelReport.retrievePrimKeyValueForOnePK( query, jtablerow+1, null,dbFieldsMany,true,/*primKeyIn,intColumnOfDescriptionIn,
                       sql2WhereField, sql2WhereValue,*/ entity, /*tableModelReadOnly,*/ primKeyDb);    
                       
                        String[] primKeys = utilsPanelReport.getPrimKeys();
                        String[] primKeysCaption = utilsPanelReport.getPrimKeysCaption();
                        //System.out.println("-->  tableModelResultSet.rowDbToUpdate '"+entity+"' primKeys:"+primKeys.length); 
                        int primKeysCount = primKeys.length;
                        //String[] primKeysValue = utilsPanelReport.getPrimKeysValue(); // not working here because gets values from 
                       
                

                       
                       for (int pk=0;pk<primKeysCount;pk++)  
                       {
                            //System.out.println("tableModelResultSet.rowDbToUpdateQuery pk:"+pk+"  primKeysCount:"+primKeysCount);
                            
                           if (pk!=0)
             	           {  whereClause = whereClause + " AND ";  }
                           
                                String pkName = primKeys[pk];
                                String pkNameCaption = primKeysCaption[pk];
             	   	    // System.out.println("---->  tableModelResultSet.rowDbToUpdateQuery    '"+entity+"'      pk:"+pk+"  pkName"+pkName+"  dbTableRow:"+dbTableRow);         
             	 	         int col = getColIntFromColName(pkNameCaption);
                           boolean isThereCollumnAA = false;
                           for(int l =0;l<listTableColumns.size();l++)
                           {
                              boolean hasAA =  listTableColumns.get(l).toString().equalsIgnoreCase(VariablesGlobal.columnNameInc);
                               if(hasAA)
                               {
                                   isThereCollumnAA=hasAA;
                                   break;
                               }
                               else
                               {
                                   isThereCollumnAA=hasAA;
                               }
                           }
                           
             
                        //System.out.println("---------------tableModelResultSet.rowDbToUpdateQuery if condition hasDataChanged:"+hasDataChanged +" table cols AA:"+ listTableColumns.get(0).toString().equalsIgnoreCase(VariablesGlobal.columnNameInc)+" listTableColumns.get(0) "+listTableColumns.get(0).toString()+" listTableColumns.get(pk) "+listTableColumns.get(pk).toString()+"  pkName:"+pkName+" rows:"+this.getRowCount()); 
                           
                                 if(!hasDataChanged && !isThereCollumnAA )// when data in cell has not been updated
             	 	         {
                                     //System.out.println("---------------tableModelResultSet.rowDbToUpdateQuery if condition hasDataChanged:"+hasDataChanged +" table cols AA:"+ listTableColumns.get(0).toString().equalsIgnoreCase(VariablesGlobal.columnNameInc)+" listTableColumns.get(0) "+listTableColumns.get(0).toString()+" rows:"+this.getRowCount()+"  col:"+col+"   dbTableRow:"+dbTableRow+"      jtablerow:"+jtablerow+"       getValueAt(jtablerow,col):"+getValueAt(jtablerow,col)); 
             	 	         	whereClause = whereClause + pkName +" LIKE "+formatValueForDb(col,getValueAt(jtablerow,col))+"";//getValueFromDBTable(initialTableRow,primKeysManyTran[i]));
                                       // System.out.println("--------------- 1 tableModelResultSet.rowDbToUpdateQuery if  whereClause:"+whereClause);
             	 	         }
                                 else if(!hasDataChanged && isThereCollumnAA )
                                 {
                                     System.out.println("--------ELSE IF - NOT DEFINED-------tableModelResultSet.rowDbToUpdateQuery if condition hasDataChanged:"+hasDataChanged +" table cols AA:"+ listTableColumns.get(0).toString().equalsIgnoreCase(VariablesGlobal.columnNameInc)+" listTableColumns.get(0) "+listTableColumns.get(0).toString()+" rows:"+this.getRowCount()); 
                                 }
             	                 else if(hasDataChanged &&  isThereCollumnAA)// when a cell has been changed
             	                 {

             	                        //System.out.println("----   tableModelResultSet.rowDbToUpdateQuery ELSE IF A  "+pkName+" ("+(dbTableRow)+")  rsRowDbToUpd:"+rsRowDbToUpd );
                                        whereClause = whereClause + pkName +" LIKE '"+ getValueFromDBTable(dbTableRow,pkName,rsRowDbToUpd)+"'";   //getValueFromDBTable(initialTableRow+1,primKeysManyTran[i])+"'"; //getValueAt(initialTableRow,col);//getValueFromDBTable(initialTableRow,primKeysManyTran[i]);//getValueFromDBTable(initialTableRow,primKeysManyTran[i]));
                                       // System.out.println("----  2 tableModelResultSet.rowDbToUpdateQuery ELSE IF A  "+pkName+" ("+(dbTableRow)+") value="+getValueFromDBTable(dbTableRow,pkName,rsRowDbToUpd));         
                                 }
                                 else if(hasDataChanged && !isThereCollumnAA)
                                 {// for example in PanelMDMR
                                     whereClause = whereClause + pkName +" LIKE "+formatValueForDb(col,getValueAt(jtablerow,col))+""; // copied from  'if(!hasDataChanged )' -> except jtablerow and ' ' 
                                     //System.out.println("error ----  tableModelResultSet.rowDbToUpdateQuery there is NOT column AA pk"+pk+" pkName:"+pkName+"   primKeysCount:"+primKeysCount+"    whereClause:"+whereClause);
             	             	      
                                      //System.out.println("---- 3 tableModelResultSet.rowDbToUpdateQuery ELSE IF B  pk:"+pk+" "+pkName+" ("+(jtablerow)+") ");         
                                      
                                 }
                                 else
                                 {
                                       System.out.println("error ---- ELSE -- 4 TableModelResultSet.rowDbToUpdateQuery hasDataChanged:"+hasDataChanged+" isColNameAA:"+isThereCollumnAA+" dbTableRow="+dbTableRow+" pkName:"+pkName);
                                 }
                       }

             // System.out.println("tableModelRS.rowDbToUpdate               whereClause:"+whereClause);                    
           /*  databaseTableMeta.retrievePrimKs(entity);
            for (int pk=0;pk<databaseTableMeta.getCountOfPrimKeys();pk++)  
             {
             	 String pkName=databaseTableMeta.getPrimKeyName(pk);
             	 //System.out.println("tableModelResultSet.rowDbToUpdateAddToList pkName "+pkName);
             	 if (pk!=0)
             	 {  whereClause = whereClause + " AND ";  }
             	 
          	 String whereValueName = getWhereValueNameThatMatchesColumn(pkName);
          	 if(!whereValueName.equalsIgnoreCase("-"))
          	 {   // for the ones that are global variables                  
             	     whereClause = whereClause + pkName +" LIKE '"+getValueForVariable(whereValueName)+"'";
          //   	     System.out.println("tableModelResultSet.rowDbToUpdateAfterDelete IF pkName "+pkName+" getValueForVariable "+getValueForVariable(whereValueName)+" whereValueName "+whereValueName);
             	 }
                 else  
             	 {  // for the ones pks that exist in the jtable
                    //System.out.println("tableModelResultSet.rowDbToUpdateAddToList initialTableRow"+initialTableRow+" currentTableRow"+currentTableRow+"  hasDataChanged"+ hasDataChanged);//+formatValueForDb(initialTableRow, getValueFromDBTable(initialTableRow,primKeysManyTran[i])));
             	    for(int i=0;i<primKeysMany.length;i++)
             	    {
             	   	    if (pkName.toUpperCase().equalsIgnoreCase(primKeysMany[i].toUpperCase()))
             	   	    {
             	   	      //System.out.println("----tableModelResultSet.rowDbToUpdate IF i "+i+" pkName"+pkName+" tablerow "+(tablerow+1));         
             	 	         int col = getColIntFromColNameForPk(pkName);
                           boolean isThereCollumnAA = listTableColumns.get(0).toString().equalsIgnoreCase(VariablesGlobal.columnNameInc);
              //             System.out.println("-----tableModelResultSet.rowDbToUpdate if condition "+hasDataChanged +" table cols AA"+ listTableColumns.get(0).toString().equalsIgnoreCase(VariablesGlobal.columnNameInc)+" listTableColumns.get(0) "+listTableColumns.get(0).toString()+" rows:"+this.getRowCount()); 
                           
                                 if(!hasDataChanged )// when data in cell has not been updated
             	 	         {
             	 	         	whereClause = whereClause + pkName +" LIKE '"+formatValueForDb(col,getValueAt(jtablerow+1,col))+"'";//getValueFromDBTable(initialTableRow,primKeysManyTran[i]));
                              //  System.out.println("----- tableModelResultSet.rowDbToUpdate ONE IF i "+i+" "+pkName+" ("+(jtablerow+1)+") value="+getValueAt(jtablerow+1,col));         
                                        //                          System.out.println("tableModelResultSet.rowDbToUpdateAddToList ONE IF i"+i+" ("+pkName+")  pk "+primKeysMany[i]+" "+formatValueForDb(col,getValueAt(initialTableRow,col))+" getvalueAt "+getValueAt(initialTableRow,col)+" hasDataChanged "+hasDataChanged+" listTableColumns.get 0 "+listTableColumns.get(0));//+" listTableColumns.get(0) "+listTableColumns.get(0).toString());
             	 	         }
             	                 else if(hasDataChanged &&  isThereCollumnAA)// when a cell has been changed
             	                 {
                                       //System.out.println("tableModelResultSet.rowDbToUpdate AfterDelete TWO IF i"+i+" ("+pkName+")  pk "+primKeysMany[i]+" "+formatValueForDb(col,getValueAt(initialTableRow,col))+" getvalueAt "+getValueAt(initialTableRow,col)+" hasDataChanged "+hasDataChanged+" listTableColumns.get 0 "+listTableColumns.get(0));
                                       //System.out.println("tableModelResultSet.rowDbToUpdateAfterDelete TWO IF i"+i+" ("+pkName+")  pk "+primKeysMany[i]+" "+getValueFromDBTable(initialTableRow,primKeysManyTran[i]));
             	             	    //   whereClause = whereClause + pkName +" LIKE "+ formatValueForDb(col,getValueAt(tablerow,col)+""); //getValueFromDBTable(initialTableRow+1,primKeysManyTran[i])+"'"; //getValueAt(initialTableRow,col);//getValueFromDBTable(initialTableRow,primKeysManyTran[i]);//getValueFromDBTable(initialTableRow,primKeysManyTran[i]));
                                     //  System.out.println("tableModelResultSet.rowDbToUpdate AA i"+i+" "+whereClause);
             	                 
       //                                 whereClause = whereClause + pkName +" LIKE '"+ getValueFromDBTable(jtablerow+1,primKeysMany[i],rsRowDbToUpd)+"'"; //getValueFromDBTable(initialTableRow+1,primKeysManyTran[i])+"'"; //getValueAt(initialTableRow,col);//getValueFromDBTable(initialTableRow,primKeysManyTran[i]);//getValueFromDBTable(initialTableRow,primKeysManyTran[i]));
      //                                  System.out.println("----   tableModelResultSet.rowDbToUpdate ELSE IF A i "+i+" "+pkName+" ("+(jtablerow+1)+"-"+primKeysManyTran[i]+") value="+getValueFromDBTable(jtablerow+1,primKeysManyTran[i]));         
     /*                            }
                                 else if(hasDataChanged && !isThereCollumnAA)
                                 {
                                     System.out.println("error ----  tableModelResultSet.rowDbToUpdate there is NOT column AA i"+i);
             	             	     //  whereClause = whereClause + pkName +" LIKE '"+ getValueFromDBTable(tablerow,primKeysManyTran[i])+"'"; //getValueAt(initialTableRow,col);//getValueFromDBTable(initialTableRow,primKeysManyTran[i]);//getValueFromDBTable(initialTableRow,primKeysManyTran[i]));
                                      //System.out.println("----tableModelResultSet.rowDbToUpdate ELSE IF B i "+i+" "+pkName+" ("+(jtablerow)+") value"+getValueFromDBTable(jtablerow,primKeysManyTran[i]));         
                                       //                             System.out.println("tableModelResultSet.rowDbToUpdateAfterDeleteAddToList THREE IF i"+i+" ("+pkName+")  pk "+primKeysMany[i]+" "+formatValueForDb(col,getValueAt(row,col))+" getvalueAt "+getValueAt(row,col)+" hasDataChanged "+hasDataChanged+" listTableColumns.get 0 "+listTableColumns.get(0)+" ... "+whereClause);
                                 }
                                 else
                                 {
                                       System.out.println("error -- TableModelResultSet.rowDbToUpdate hasDataChanged:"+hasDataChanged+" isColNameAA:"+isThereCollumnAA+" jtablerow="+jtablerow+" primKeysMany[i]"+primKeysMany[i]);
                                 }
                            }
              /*             else if (pkName.equalsIgnoreCase(primKeysMany[i]))
                            {
                                
              	 	         int col = getColIntFromColName(pkName);
             	 	        // System.out.println("tableModelResultSet.rowUpdate FOUR "+pkName+" col"+col+primKeysMany[i]+" "+primKeysMany[i]);
             	               whereClause = whereClause + pkName +" LIKE "+formatValueForDb(col, getValueFromDBTable(tablerow,primKeysManyTran[i]));
                                  System.out.println("----tableModelResultSet.rowDbToUpdate else if i "+i+" "+pkName+" tablerow"+(tablerow)+" getValueFromDBTable(tablerow,primKeysManyTran[i])"+getValueFromDBTable(tablerow+1,primKeysManyTran[i]));                               
                            }*/
     /*                       else
                            {
                                //System.out.println("----tableModelResultSet.rowDbToUpdate else i "+i+" pkName"+pkName+" jtablerow"+(jtablerow+1));
                                //int col = getColIntFromColName(pkName);
                                //System.out.println("tableModelResultSet.rowDbToUpdateAfterDelete ELSE i"+i+" ("+pkName+")  pk "+primKeysMany[i]+":"+col+" ");//+formatValueForDb(col,getValueAt(initialTableRow,col)));
                            }
             	     }
                    //System.out.println("----tableModelResultSet.rowDbToUpdateAfterDelete whereClause "+whereClause); 
             	 }           	 
             }
             */
                String qUpd =
                "UPDATE "+entity+
                " SET "+setClause+
                " WHERE "+whereClause;

      return qUpd;    
  }
 
  private String rowDbToDeleteQuery(int dbTableRow)
  {
       //boolean ret=false;

        System.out.println("tableModelRS.rowDbToDeleteQuery ---DEL--   dbTableRow:"+dbTableRow+"   ----  listTableColumns.size():"+listTableColumns.size()+"     "+dbFieldsParent+"    query:"+query);

            String whereClause = "";

                       utilsPanelReport.retrievePrimKeyValueForOnePK( query, dbTableRow, null,dbFieldsMany,true,/*primKeyIn,intColumnOfDescriptionIn,
                       sql2WhereField, sql2WhereValue,*/ entity, /*tableModelReadOnly,*/null);  // primKeyDb);    
                       
                        String[] primKeys = utilsPanelReport.getPrimKeys();
                        String[] primKeysCaption = utilsPanelReport.getPrimKeysCaption();
                        //System.out.println("-->  tableModelResultSet.rowDbToDelete '"+entity+"' primKeys:"+primKeys.length); 
                        int primKeysCount = primKeys.length;
                        String[] primKeysValue = utilsPanelReport.getPrimKeysValue();

      //System.out.println("-----tableModelResultSet.rowDbToDelete    dbTableRow:"+dbTableRow +"   getRowCount:"+this.getRowCount()+"     primKeysCount:"+primKeysCount);
                        
                       for (int pk=0;pk<primKeysCount;pk++)  
                       {
                           
                           if (pk!=0)
             	           {  whereClause = whereClause + " AND ";  }
                           
                                String pkName = primKeys[pk];
                                String pkNameCaption = primKeysCaption[pk];
             	   	//System.out.println("----tableModelResultSet.rowDbToDelete IF pk "+pk+" pkName"+pkName+" tablerow "+(dbTableRow+1));         
       
             
          //System.out.println("-----tableModelResultSet.rowDbToDelete    dbTableRow:"+dbTableRow +"   getRowCount:"+this.getRowCount()+"   "+pkName +" LIKE '"+primKeysValue[pk]); 
                           whereClause = whereClause + pkName +" LIKE '"+primKeysValue[pk]+"'";

                       }      
    
               String delQ =
                "DELETE FROM "+entity+
                " WHERE "+whereClause;
            //System.out.println("-----tableModelResultSet.rowDbToDelete    dbTableRow:"+dbTableRow +"     delQ:"+delQ);    

      return delQ;  
      
      
  }
  
  
  
  
  
  
  
 private String rowDbAllToDelete()
 {
     
    /*   globalYear =VariablesGlobal.globalYear;
       globalDeliveryId =VariablesGlobal.globalDeliveryId;
       globalCompanyId=VariablesGlobal.globalCompanyId;     
     
     String whereClause ="";

          System.out.println("TableModelResultSet.rowDbAllToDelete sql2WhereField="+sql2WhereField);
                    
                    for(int i=0;i<sql2WhereField.length;i++)
             	    {
                        String pkName=sql2WhereField[i];
                        String pkValue = getValueForVariable(sql2WhereValue[i]);
                        //System.out.println("TableModelResultSet.rowDbAllToDelete "+i+" pkName="+pkName+" pkValue="+pkValue);
                        if(i==0)
                        {
                            whereClause = whereClause+pkName +" LIKE '"+pkValue+"'";
                        }
                        else
                        {
                             whereClause = whereClause +" AND " +pkName +" LIKE '"+pkValue+"'";
                        }
             	 	
             	    }  // for 
     
     
     
     String query = "DELETE FROM "+entity+" WHERE "+whereClause; 
     
     System.out.println("DEL TableModelResultSet.rowDbAllToDelete query:"+query);
     
     //rowsToBeDeleted.add(query);
     */
     return query;
 }



   /*
   * 
   * exists in UtilsPanelReport.getValueForVariable,  in PanelOneDataOneRecData.getValueForVariable
   * and TableModelResultSet.getValueForVariable
   */
  /*private String getValueForVariable(String variableName)
  {
      

      String valueStr="";
      if(variableName.equalsIgnoreCase("globalYear"))
      {
          valueStr=globalYear;
      }
      else if(variableName.equalsIgnoreCase("globalCompanyId"))
      {
          valueStr=globalCompanyId;
      }
      else if(variableName.equalsIgnoreCase("globalDeliveryId"))
      {
          valueStr=globalDeliveryId;
      }    
      else if(variableName.equalsIgnoreCase("primKeyValue"))
      {
          valueStr=primKeyValue;
      }
      else
      {
          valueStr="";
          System.out.println("error TableModelResultSet.getValueForVariable UNKNOWN "+ variableName+"  "+valueStr);
      }      
            
      
   /*   
  	String valueStr="";
  	double valueDoub;
  	int valueInt;
  	Object valueObj;
    try
    {
      //System.out.println("panelTwoDataOneRec.getValueForVariable "+ PanelTwoDataOneRec.class.getName());
      Field thisField = TableModelResultSet.class.getField(variableName);

      Class thisClassType = thisField.getType();
      //System.out.println("panelTwoDataOneRec.getValueForVariable "+ thisField+" "+thisClassType);
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
           { System.out.println("TableModelRS.getValueForVariable "+variableName+" valueObj="+valueObj);  }
      }

    }
       catch(Exception e)
       {  
           System.out.println("TableModelRS.getValueForVariable "+variableName+" "+e.getMessage());
           e.printStackTrace();
       }
*/
/*    return valueStr;
  }*/

  /* private boolean checkIfNameMatchesWhereValueName(String columnLabel)
   { 
     boolean is=false;
     String word="global";
     if(sql2WhereField!=null)
     {
        for(int i=0;i<sql2WhereField.length;i++)
        {
           //System.out.println(".panelOneDataOneRecData.checkIfNameIsWhereField "+sql2WhereField[i]+" matches global");
           if(sql2WhereValue[i].regionMatches(true,0,word,0,word.length()))   	          
           {
           	   //System.out.println(".panelOneDataOneRecData.checkIfNameIsWhereField "+sql2WhereValue[i]+" matches global");
      	       if(sql2WhereField[i].equalsIgnoreCase(columnLabel))
      	       {
      	       	//System.out.println(".panelOneDataOneRecData.checkIfNameIsWhereField "+sql2WhereValue[i]+" matches global");
      	          is=true	;
      	       }
      	   }
        }
      }
   	   return is;
   }*/
   
/*   private String getWhereValueNameThatMatchesColumn(String columnLabel)
   {*/ 
     /*String whereValueName="-"; 
     String word="global";
     if(sql2WhereField!=null)
     {
        for(int i=0;i<sql2WhereField.length;i++)
        {
           //System.out.println(".panelOneDataOneRecData.checkIfNameIsWhereField "+sql2WhereField[i]);
           /*if(sql2WhereValue[i].regionMatches(true,0,word,0,word.length()))   	          
           {
           	  */ //System.out.println(".panelOneDataOneRecData.checkIfNameIsWhereField "+sql2WhereValue[i]+" matches global");
/*      	       if(sql2WhereField[i].equalsIgnoreCase(columnLabel))
      	       {
      	       	//System.out.println(".panelOneDataOneRecData.checkIfNameIsWhereField "+sql2WhereValue[i]+" matches global");
      	          whereValueName=sql2WhereValue[i]	;
      	       }
      	  /* }*/
 /*       }
      }
   	   return whereValueName;*/
/*       return "";
   }*/

 
   private int getColIntFromColName(String columnName)
   {
   	int colNo=-1;
            for(int col = 0; col<getColumnCount(); col++)
            {
                String colName = getColumnName(col);
                
                //System.out.println("TableModelResultSet.getColIntFromColName    "+col+"    colName:"+colName+"    columnName:"+columnName+" ");
                if (colName.toUpperCase().equals(columnName.toUpperCase()))
                {
                	colNo=col;
                }
            }
            
            if(colNo==-1)
            {
                System.out.println("error TableModelResultSet.getColIntFromColName colNo = "+colNo+"        for columnName:"+columnName+" column caption not found in table.");
            }
   	  return colNo;
   }

   /*private int getColIntFromColNameForPk(String columnName)
   {
   	int colNo=-1;
            for(int col = 0; col<getColumnCount(); col++)
            {
                String colName = dbFieldsMany[col].getDbField().toUpperCase();//fieldsMany[col];
                
                System.out.println("TableModelResultSet.getColIntFromColNameForPk "+col+" "+colName+" "+columnName+"  ");
                if (colName.toUpperCase().equals(columnName.toUpperCase()))
                {
                	colNo=col;
                }
            }
            
             if(colNo==-1)
            {
                System.out.println("error TableModelResultSet.getColIntFromColNameForPk colNo ==== "+colNo+"  for "+columnName);
            } 
            
   	  return colNo;
   }*/
  
    private String formatValueForDb(int column, Object value)
    {
        //String colClass="";
        if (value == null)
        {       return "null";       }
        	
              /*       //  does not work, be carefull
              RecColumn tableCol = (RecColumn)listTableColumns.get(column);
           
             
              /*colClass=tableCol.getColumnClass();

              if(colClass.equalsIgnoreCase("java.lang.String") || colClass.equalsIgnoreCase("java.lang.Integer") || colClass.equalsIgnoreCase("java.lang.Double"))
              {
                  return "'"+value.toString().trim()+"'";
              }
              else if(colClass.equalsIgnoreCase("java.lang.Boolean"))
              {
                  System.out.println("boolean "+column+"");
                  return ((Boolean)value).booleanValue() ? "1" : "0";
              }
              else if(colClass.equalsIgnoreCase("java.sql.Date") || colClass.equalsIgnoreCase("java.lang.Date"))
              {
                  return "'"+value.toString().trim()+"'"; // This will need some conversion.
              }
              else
              {
                  return "'"+value.toString().trim()+"'";
              }*/
                  

                  
        int type;

        if (value == null)
        {       return "null";       }
        RecColumn tableCol = (RecColumn)listTableColumns.get(column);
        type = tableCol.getColumnType();
        switch(type) {
        case Types.INTEGER:
        case Types.DOUBLE:
        case Types.FLOAT:
            return "'"+value.toString().trim()+"'";
        
        case Types.BIT:
        case Types.TINYINT:
        //case Types.BOOLEAN:
            return ((Boolean)value).booleanValue() ? "1" : "0";
        case Types.DATE:
            return "'"+value.toString().trim()+"'"; // This will need some conversion.
        default:
            return "'"+value.toString().trim()+"'";
        } 
        
  }

    
    /*
     * 
     * called by panelODMRData.addNewRowIfThereIsnt
     */
     public void addEmptyRow(int row, JTable table)
     {
       if(row==-1)
       {
           
       }
       else
       {
        String[] record = new String[table.getColumnCount()]; 
        
        for (int i = 0; i < table.getColumnCount(); i++)// for each field
        {
           //System.out.println("tableModelRS.addEmptyRow Integer i"+i+" colCount"+colCount);
           //System.out.println("tableModelRS.addEmptyRow Integer ("+i+") columnname:"+getColumnDBName(i)+" class:"+getColumnClass(i) +" "+dbFieldsMany[i].getColClassName());
           
           if (getColumnClassString(i).equalsIgnoreCase("java.lang.Integer"))//if (getColumnClass(i)== Integer.class)
           {
              //System.out.println("tableModelRS.addEmptyRow Integer i"+i);
              record[i] = "0";
              //System.out.println("tableModelRS.addEmptyRow Integer ("+i+")  columnname:"+getColumnDBName(i)+"   pk"+dbFieldsMany[i].getPrimaryKeyIntegerAutoInc());

             
             //RecColumn tableColumn = (RecColumn)listTableColumns.get(i);
             //String colClass = tableColumn.getColumnClass();
              String pkValueFromParentTable = "";
              //boolean isPkFromParentTable = dbFieldsMany[i].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY_FROM_PARENTTABLE;
              /*boolean isCalledFromListPanelOrFromEditRecPanel= true;// fromlist panel
              if(isCalledFromListPanelOrFromEditRecPanel)
              {
                  pkValueFromParentTable="0";
              }
              else
              {    */
              
                //if(primKeyValue!=null && !primKeyValue.equalsIgnoreCase("") )
/*             if(isNewRec)
             {
                  pkValueFromParentTable="0";
             }
             else
             {*/
                  pkValueFromParentTable= primKeyValue;
             //}   

              

              //}
              
             boolean isPkAutoInc = dbFieldsMany[i].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY_AUTOINC;
             if(isPkAutoInc)
             {
                  //String strPkValue="";
                 
                 countOfJTableNewRowsBeforeSavedInDB++;
                 
 
                  strPkValue = utilsPanelReport.getNoOfPKAutoIncOfNewRecord(false, dbFieldsMany,entity,table,pkValueFromParentTable,countOfJTableNewRowsBeforeSavedInDB); // countOfJTableNewRowsBeforeSavedInDB becomes 0 in setTableInitialState()
                                  // also -1 in deleteTableRow(),  deleteEmptyRow()
    
              //System.out.println("tableModelRS.addEmptyRow    i:"+i+"    isNewRec:"+isNewRec+"    pkValueFromParentTable:"+pkValueFromParentTable+"     strPkValue:"+strPkValue+"      countOfJTableNewRowsBeforeSavedInDB:"+countOfJTableNewRowsBeforeSavedInDB);    
                
               record[i] = strPkValue+"";
                  
               
             }
  
             
             
           }           
           else if (getColumnClassString(i).equalsIgnoreCase("java.lang.Double"))//getColumnClass(i)== Double.class)
           {  
              //System.out.println("tableModelRS.addEmptyRow Double i"+i);
              record[i] = "0.0";
           }
           else
           {  record[i] = "";   }
           
           
           if(getColumnDBName(i).toUpperCase().equalsIgnoreCase(VariablesGlobal.columnNameInc.toUpperCase()) )
           {
             	
             	if(row!=getRowCount()) // if inserted in between
             	{
                    renumberColumnAA(row,+2);
             		
             		
             		int v =row+1;
             		record[i] = v+"";              		

             	}
             	else
             	{
             		int v =getRowCount()+1; // if inserted last
             		record[i] = v+""; 
             	}
   	
           }           
           
           
           
           if(dbFieldsMany[i].getDefaultValue()!=null)// if is global variable complete the field
           {
               String colCaption =  dbFieldsMany[i].getCaption(); 
               String defaultValue = dbFieldsMany[i].getDefaultValue();
               String classtype = dbFieldsMany[i].getColClassName();
               
               
               
   	       if(classtype.equalsIgnoreCase("java.lang.Boolean"))
   	       {

                                if(defaultValue==null)
                                {
                                   record[i] = "false";    
                                }
                                else if(defaultValue.equalsIgnoreCase("true"))
                                {
                                     record[i] = "true";  
                                }
                                else if(defaultValue.equalsIgnoreCase("false"))
                                {
                                     record[i] = "false";  
                                } 
                                else
                                {
                                    System.out.println("   error  TableModelResultSet.addEmptyRow UNKNOWN default VALUE   defaultValue:"+defaultValue+"  ("+i+")  colCaption:"+colCaption);
                                }
          	                    	   	
          	}   
                else
                {
                              record[i] = defaultValue;
                }
               
               
           }
           
           /*if(dbFieldsMany[i].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY_AUTOINC)
           {
               String pkAutoInc = utilsPanelReport.getNoOfPKOfNewRecord(false, dbFieldsMany, entity);
               //  
               //System.out.println("TableModelResultSet.addEmptyRow    FIELD_PRIMARY_KEY_AUTOINC    getColumnDBName(i):"+getColumnDBName(i)+"   "+dbFieldsMany[i].getPrimaryKeyIntegerAutoInc()+"=="+FIELD_PRIMARY_KEY_AUTOINC+" pkAutoInc:"+pkAutoInc);
               
           }*/
           
           
           if(dbFieldsMany[i].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY_FROM_PARENTTABLE)
           {
             //System.out.println("      ---OOOO---  TableModelResultSet.addEmptyRow      i:"+i+"    getColumnDBName(i):"+getColumnDBName(i)+"   "+dbFieldsMany[i].getPrimaryKeyIntegerAutoInc()+"=="+FIELD_PRIMARY_KEY_FROM_PARENTTABLE+"  primKeyValue:"+primKeyValue);
               if(primKeyValue.trim().equalsIgnoreCase("") || isNewRec)// if is new rec show 'FIELD_PRIMARY_KEY_FROM_PARENTTABLE' as 0
               {
                   record[i]="0";
               }
               else
               {
                   record[i]=primKeyValue;
               }
           }
           // primKeyValueIn  for example farmer
        }
       
        dataVector.insertElementAt(record,row);

        
        if(listRecsHashToBeInserted == null)
        {
            setTableInitialState();
        }      
        
        
        Vector v =this.getTableDataVector();            
        Object orow = v.get(row);
        

        
        listRecsHashToBeInserted.add(orow.hashCode());        
      //System.out.println("   --_--  tableModelRS.addEmptyRow   hash:"+orow.hashCode()+"     row:"+row+"    size:"+listRecsHashToBeInserted.size());
      //System.out.println("   --_--  tableModelRS.addEmptyRow  colCount:"+table.getColumnCount()+"  listRecsHashToBeInserted:"+listRecsHashToBeInserted+"     dataVector:"+dataVector);


       fireTableRowsInserted(row, row);
       checkIfThereAreDoubleEntries();
       }
        
        
        
     }
    
    public void deleteEmptyRowIfThereIsOne()
    {
    	if(hasEmptyRow())// && getRowCount()!=1)    // if there is only one not delete
    	{
    		deleteEmptyRow();
    	}
    	
    }
 
    /*
    * cleartablerows
    */
    public void deleteAllTableRows()
    { 
       int tblrowcount = getRowCount();
        for(int r =tblrowcount-1;r>=0;r--)
        {
            deleteTableRow(r);
        }
    }
    
    /*
     * 
     * called by PanelODMRData.rowSelectedDelete
     */
    public void deleteTableRow(int row)
    {

        Vector v =this.getTableDataVector();            
        Object orow = v.get(row);

        listRecsHashToBeRemoved.add(orow.hashCode());
        dataVector.remove(row);
        countOfJTableNewRowsBeforeSavedInDB--;
        //strPkValue--;
        //checksums.remove(row);
    	//System.out.println("  TableModelRS.deleteTableRow           -          row:"+row+"  orow.hashCode():"+orow.hashCode());
        fireTableRowsDeleted(row,row);
    	renumberColumnAA(row,1);
  
    }
    
    private void deleteEmptyRow()
    {
    	//String[] record = new String[colCount];
        int row = getFindEmptyRow();
    	dataVector.remove(row);

        //Vector v =this.getTableDataVector();            
        //Object orow = v.get(row);
        System.out.println("   -----    tableModelRS.deleteEmptyRow     row:"+row+"     size:"+listRecsHashToBeInserted.size());
        listRecsHashToBeInserted.remove(0);  // there is only one empty row
        countOfJTableNewRowsBeforeSavedInDB--;
        //strPkValue--;
        

        //listRecsHashToBeRemoved.add(row);
        //checksums.remove(row);
    	fireTableRowsDeleted(row,row);
        renumberColumnAA(row,1);
        
    }
 
    private void renumberColumnAA(int rowBelow, int rowDifference)
    {
      if(getRowCount()>rowBelow)// if last row then nothing to change
      {
      	
        for (int i = 0; i < colCount; i++)// for each field
        { 	   
    	   if(getColumnDBName(i).toUpperCase().equals(VariablesGlobal.columnNameInc.toUpperCase()))
           {

             		for(int c =rowBelow; c<getRowCount();c++)// for each row apears below change aa
             		{
             			int va=(c+rowDifference);
             			//System.out.println("TableModelResultSet  "+c+" "+va);
             			setValueAt(va+"",c,i);
             			
             		}
            }
        }  
        
        
        fireTableRowsUpdated(rowBelow+1, getRowCount()-1) ; 
       } 
    }
    
    public boolean hasEmptyRow()
    {
    	boolean has =false;
    	for(int i =0;i<dataVector.size();i++)
    	{
            boolean irempty = isRowEmpty(i);
            //System.out.println("TableModelResultSet.hasEmptyRow   "+i+"   irempty:"+irempty);
    		if(irempty)
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
    
    public int getFindEmptyRow()
    {
    	int row = -1;
    	for (int r=0;r<this.getRowCount();r++)
    	{
    		if(isRowEmpty(r))
    		{
    			row = r;
    		}
                else
                {
                    // is no empty row
                }
    	}
    	
        if(row==-1)
        {
              System.out.println("**** TableModelResultSet.getFindEmptyRow No empty row:"+row);    
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
         	
         	 
         	 
         	 Object value = getValueAt(row, c);
         	 if (value !=null)
         	 {
         	 	
         	 //	Double.valueOf(value.toString()).doubleValue();
                //System.out.println("tablemodelRS.hasEmptyRow values                for row:"+row+"                '"+value+"'");
                 if (getColumnClassString(c).equalsIgnoreCase("java.lang.String") && value.toString().trim().equals(""))//getColumnClass(c)== String.class && value.toString().trim().equals(""))
                 { 
                     //System.out.println("tablemodelRS.hasEmptyRow values String '"+value+"'");
                     is =  true  ;
                 }
                 else if ((value.toString().trim().equals("0") || getColumnDBName(c).toUpperCase().equals(VariablesGlobal.columnNameInc.toUpperCase()) )&& getColumnClassString(c).equalsIgnoreCase("java.lang.Integer"))//getColumnClass(c)== Integer.class)
                 {  
                    //System.out.println("tablemodelRS.hasEmptyRow   values    Integer '"+value+"'");
                    is = true ;
                 }
                 else if ((getColumnClassString(c).equalsIgnoreCase("java.util.Date") || getColumnClassString(c).equalsIgnoreCase("java.sql.Date") )&& value.toString().trim().equals(""))//getColumnClass(c)== java.util.Date.class && value.toString().trim().equals(""))
                 {  
                    //System.out.println("tablemodelRS.hasEmptyRow values Date '"+value+"'");
                    is = true ;
                 }                     
                 else if ( getColumnClassString(c).equalsIgnoreCase("java.lang.Double")  && utilsDouble.getDoubleReading(value+"",true).equals(utilsDouble.getDoubleReading("0",true)))//getColumnClass(c)== Double.class && utilsD.getDoubleReading(value,false).equals(utilsD.getDoubleReading("0",false)))
                 {  
                    //System.out.println("tablemodelRS.hasEmptyRow values Double '"+value+"'");
                    is = true ;
                 }                 
                 else if (value.toString().trim().equals("") && getColumnClassString(c).equalsIgnoreCase("java.lang.Object"))//getColumnClass(c)== Object.class)
                 {  
                    //System.out.println("tablemodelRS.hasEmptyRow values Object '"+value+"'");
                    is = true ;
                 }   
                 else if(dbFieldsMany[c].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY_AUTOINC || dbFieldsMany[c].getPrimaryKeyIntegerAutoInc()==FIELD_PRIMARY_KEY_FROM_PARENTTABLE || dbFieldsMany[c].getDefaultValue()!=null)
                 {
                    is = true ; 
                    //System.out.println("tablemodelRS.isRowEmpty values FIELD_PRIMARY_KEY_AUTOINC || FIELD_PRIMARY_KEY_FROM_PARENTTABLE || .getDefaultValue()!=null ("+c+") getColumnName(c)"+getColumnName(c)+" is:"+is);
                 }
                 else
                 { 
                    is = false ;
                   //System.out.println(" tablemodelRS.isRowEmpty values else ----("+c+"  "+row+")--- =----- value:"+value+"   getColumnName(c)"+getColumnName(c)+" is:"+is+"  getColumnClassString(c):"+getColumnClassString(c));
                    //System.out.println("TableModelResultSet.hasEmptyRow value"+value.toString());
                    //System.out.println("tablemodelRS.hasEmptyRow has empty row false");     
                    break;
                 }
                 
             }
                 
             //System.out.println("tableModelRS.isRowEmpty "+getColumnCount()+" ("+c+")  "+getColumnName(c)+"  is:"+is);    
         } 
         return is;
     }
     
     public Vector getTableDataVector()
     {
     	/*if( hasEmptyRow())
     	{
     		deleteEmptyRow();
     	}*/
     
        return dataVector;
     }
     
     
     // get captions
     public String[] getTableColumnRealNames()
     {
        RecColumn tableColumn;
     	String[] columns = new String[colCount]; 
     	

     	   	
         for(int c=0; c<colCount; c++)
         {
         	//tableColumn = (RecColumn)listTableColumns.get(c);
              //columns[c]=tableColumn.colCaption;
              
              RecColumn reccol = (RecColumn)listTableColumns.get(c);
              columns[c] = reccol.getColumnCaption();
              
              
              //columns[c]=recColumnMgt.getColumnCaption(c);
              //System.out.println("getTableColumnRealNames"+columns[c]);
         }	
       return columns;
     }
     
     // needed to panelODMRData for export
     public Class[] getTableColumnsClass()
     {
     	Class[] columnsCl = new Class[colCount];
     	
         for(int c=0; c<colCount; c++)
         {
         	  //System.out.println("TableModelResultSet.getTableColumnsClass"+c);
              columnsCl[c]=this.getColumnClass(c);
         }	
       return columnsCl;
     }
   
  public boolean getHasDataChanged()
  {
        //System.out.println("TableModelResultSet.getHasDataChanged   o  hasDataChanged: "+hasDataChanged+"   entity:"+entity); 
  	return hasDataChanged;
  }
  
  public void setDataHasChanged(boolean hasChanged)
  {
        hasDataChanged=hasChanged;
  }
  // filter with sql
  /*public void filter(String[] strSearchField, String[] strSearch) 
  {  Vector filteredDataVector = new Vector();
     String text1="",text2="",text3="";
  	//for(int r=0;r<getRowCount();r++)
  	for(int r=0;r<initialDataVector.size();r++)
  	{
  		if (strSearchField.length==1)
  		{
  			Object obj1 = ((Object[]) initialDataVector.elementAt(r))[getColIntFromColName(strSearchField[0])];
  			text1=obj1.toString();
  		}
  		/*if (strSearchField.length==1)
  		{
  		  text1 = getValueAt(r,getColIntFromColName(strSearchField[0])).toString();
  	    }*/
  	 /*   else if (strSearchField.length==2)
  	    {
  			Object obj1 = ((Object[]) initialDataVector.elementAt(r))[getColIntFromColName(strSearchField[0])];
  			text1=obj1.toString();
  			Object obj2 = ((Object[]) initialDataVector.elementAt(r))[getColIntFromColName(strSearchField[1])];
  			text2=obj2.toString();
  	    }
  	    else if (strSearchField.length==3)
  	    {
  			Object obj1 = ((Object[]) initialDataVector.elementAt(r))[getColIntFromColName(strSearchField[0])];
  			text1=obj1.toString();
  			Object obj2 = ((Object[]) initialDataVector.elementAt(r))[getColIntFromColName(strSearchField[1])];
  			text2=obj2.toString();
  			Object obj3 = ((Object[]) initialDataVector.elementAt(r))[getColIntFromColName(strSearchField[2])];
  			text3=obj3.toString();
  	    }
  	    else
  	    { System.out.println("tableModelRS.filter error"+strSearchField.length); }
  	    
  	    if (strSearchField.length==1 && text1.indexOf(strSearch[0],0)!=-1 )
  	    { 
  	        filteredDataVector.addElement(initialDataVector.elementAt(r));
  	    }
  	    else if (strSearchField.length==2 && text1.indexOf(strSearch[0],0)!=-1  && text2.indexOf(strSearch[1],0)!=-1 )
  	    {
  	        filteredDataVector.addElement(initialDataVector.elementAt(r));
  	    }
   	    else if (strSearchField.length==3 && text1.indexOf(strSearch[0],0)!=-1  && text2.indexOf(strSearch[1],0)!=-1  && text3.indexOf(strSearch[2],0)!=-1 )
        {
  	        filteredDataVector.addElement(initialDataVector.elementAt(r));
        }
  	    
  	}   
  	     //System.out.println("tableModelRS.filter "+initialDataVector.size()    );
  	     dataVector=filteredDataVector;

  }*/
 
  // filter without sql
/*  public void filter(String[] strSearchField, String[] strSearch) 
  {  Vector filteredDataVector = new Vector();
     String text1="",text2="",text3="",text4="";
  	
  	//System.out.println("TableModelRS.filter"+query);
  	
  	for(int r=0;r<initialDataVector.size();r++)
  	{
  		// search
  		if (strSearchField.length==1)
  		{
  			Object obj1 = ((Object[]) initialDataVector.elementAt(r))[getColIntFromColName(strSearchField[0])];
  			if(obj1!=null)
  			{  			
  			  text1=obj1.toString();
  			}
  		}
  	    else if (strSearchField.length==2)
  	    {
  			Object obj1 = ((Object[]) initialDataVector.elementAt(r))[getColIntFromColName(strSearchField[0])];
  			if(obj1!=null)
  			{  			
  			  text1=obj1.toString();
  			}
  			Object obj2 = ((Object[]) initialDataVector.elementAt(r))[getColIntFromColName(strSearchField[1])];
  			if(obj2!=null)
  			{
  			   text2=obj2.toString();
  			}
  	    }
  	    else if (strSearchField.length==3)
  	    {
  			Object obj1 = ((Object[]) initialDataVector.elementAt(r))[getColIntFromColName(strSearchField[0])];
  			if(obj1!=null)
  			{  			
  			  text1=obj1.toString();
  			}
  			Object obj2 = ((Object[]) initialDataVector.elementAt(r))[getColIntFromColName(strSearchField[1])];
  			if(obj2!=null)
  			{
  			   text2=obj2.toString();
  			}
  			Object obj3 = ((Object[]) initialDataVector.elementAt(r))[getColIntFromColName(strSearchField[2])];
  			if(obj3!=null)
  			{  			
  			  text3=obj3.toString();
  			}
  	    }
  	    else if (strSearchField.length==4)
  	    {
  			Object obj1 = ((Object[]) initialDataVector.elementAt(r))[getColIntFromColName(strSearchField[0])];
  			if(obj1!=null)
  			{  			
  			  text1=obj1.toString();
  			}
  			Object obj2 = ((Object[]) initialDataVector.elementAt(r))[getColIntFromColName(strSearchField[1])];
  			if(obj2!=null)
  			{
  			   text2=obj2.toString();
  			}
  			Object obj3 = ((Object[]) initialDataVector.elementAt(r))[getColIntFromColName(strSearchField[2])];
  			if(obj3!=null)
  			{  			
  			  text3=obj3.toString();
  			}
  			Object obj4 = ((Object[]) initialDataVector.elementAt(r))[getColIntFromColName(strSearchField[3])];
  			if(obj4!=null)
  			{
  			  text4=obj4.toString();
  			}
  			
  	    }

  	    else
  	    { System.out.println("tableModelRS.filter error"+strSearchField.length); }
  	    
  	    //
  	    if (strSearchField.length==1 && text1.toUpperCase().indexOf(strSearch[0].toUpperCase(),0)!=-1 )
  	    { 
  	        filteredDataVector.addElement(initialDataVector.elementAt(r));
  	    }
  	    else if (strSearchField.length==2 && text1.toUpperCase().indexOf(strSearch[0].toUpperCase(),0)!=-1  && text2.toUpperCase().indexOf(strSearch[1].toUpperCase(),0)!=-1 )
  	    {
  	        filteredDataVector.addElement(initialDataVector.elementAt(r));
  	    }
   	    else if (strSearchField.length==3 && text1.toUpperCase().indexOf(strSearch[0].toUpperCase(),0)!=-1  && text2.toUpperCase().indexOf(strSearch[1].toUpperCase(),0)!=-1  && text3.toUpperCase().indexOf(strSearch[2].toUpperCase(),0)!=-1 )
        {
  	        filteredDataVector.addElement(initialDataVector.elementAt(r));
        }
   	    else if (strSearchField.length==4 && text1.toUpperCase().indexOf(strSearch[0].toUpperCase(),0)!=-1  && text2.toUpperCase().indexOf(strSearch[1].toUpperCase(),0)!=-1  && text3.toUpperCase().indexOf(strSearch[2].toUpperCase(),0)!=-1 && text4.toUpperCase().indexOf(strSearch[3].toUpperCase(),0)!=-1 )
        {
  	        filteredDataVector.addElement(initialDataVector.elementAt(r));
        }
        
  	    
  	}   
  	     //System.out.println("tableModelRS.filter "+initialDataVector.size()    );
  	     dataVector=filteredDataVector;

  }*/



    //from   package org.apache.commons.collections;
    
    /**
     * Returns a {@link Collection} containing the exclusive disjunction
     * (symmetric difference) of the given {@link Collection}s.
     * <p>
     * The cardinality of each element <i>e</i> in the returned {@link Collection}
     * will be equal to
     * <tt>max(cardinality(<i>e</i>,<i>a</i>),cardinality(<i>e</i>,<i>b</i>)) - min(cardinality(<i>e</i>,<i>a</i>),cardinality(<i>e</i>,<i>b</i>))</tt>.
     * <p>
     * This is equivalent to
     * <tt>{@link #subtract subtract}({@link #union union(a,b)},{@link #intersection intersection(a,b)})</tt>
     * or
     * <tt>{@link #union union}({@link #subtract subtract(a,b)},{@link #subtract subtract(b,a)})</tt>.
     *
     * @param a  the first collection, must not be null
     * @param b  the second collection, must not be null
     * @return the symmetric difference of the two collections
     */
    public static Collection disjunction(final Collection a, final Collection b) {
        ArrayList list = new ArrayList();
        Map mapa = getCardinalityMap(a);
        Map mapb = getCardinalityMap(b);
        Set elts = new HashSet(a);
        elts.addAll(b);
        Iterator it = elts.iterator();
        while(it.hasNext()) {
            Object obj = it.next();
            for(int i=0,m=((Math.max(getFreq(obj,mapa),getFreq(obj,mapb)))-(Math.min(getFreq(obj,mapa),getFreq(obj,mapb))));i<m;i++) {
                list.add(obj);
            }
        }
        return list;
    }


    /**
     * Returns a {@link Map} mapping each unique element in the given
     * {@link Collection} to an {@link Integer} representing the number
     * of occurrences of that element in the {@link Collection}.
     * <p>
     * Only those elements present in the collection will appear as
     * keys in the map.
     * 
     * @param coll  the collection to get the cardinality map for, must not be null
     * @return the populated cardinality map
     */
    public static Map getCardinalityMap(final Collection coll) {
        Map count = new HashMap();
        for (Iterator it = coll.iterator(); it.hasNext();) {
            Object obj = it.next();
            Integer c = (Integer) (count.get(obj));
            if (c == null) {
                count.put(obj,INTEGER_ONE);
            } else {
                count.put(obj,new Integer(c.intValue() + 1));
            }
        }
        return count;
    }
    
    private static final int getFreq(final Object obj, final Map freqMap) {
        Integer count = (Integer) freqMap.get(obj);
        if (count != null) {
            return count.intValue();
        }
        return 0;
    }
    
    
    
           
   public static void main (String[]args)
   {
   	  String query = "SELECT name, surname, afm FROM farmer WHERE surname='nik'";
   	  int indexOfEndOfFrom = query.indexOf("FROM")+5;
   	  System.out.println(query.substring(indexOfEndOfFrom,indexOfEndOfFrom+6));
   	  
   	  
   	  
   }


}