//created 2011-07-17

//ConfigDialog

// created 25/09/2006

package com.tool.guicomps;

import com.tool.jdbc.*;
import com.tool.gui.*;
import com.tool.utils.*;

import javax.swing.table.AbstractTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.sql.Clob;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Vector;
import java.util.ArrayList;


import java.awt.Color;
import java.awt.SystemColor;

public class TableModelResultSetReadOnlyExceptAColumn extends AbstractTableModel
{

  // private ResultSetTableModel model;
  // private Database db;
   //private Statement stmt;
   //private ResultSet rs;
   //private ResultSetMetaData rsmd;
   private int colCount;
        
   private Vector dataVector;
   private String[] headers;
  
  private ArrayList tableCols;
  private RecColumn recColumn;
  private boolean tableModelQuery=true;
    private UtilsDouble utilsDouble;
//abstract class ResultSetTableModel extends AbstractTableModel
//{
   public TableModelResultSetReadOnlyExceptAColumn()
   {  
     //rs = aResultSet;
     dataVector = new Vector();
     tableCols = new ArrayList();
     utilsDouble = new UtilsDouble();
     utilsDouble = VariablesGlobal.globalUtilsDouble;
    // db= new Database();
  /*    try
      {  rsmd = rs.getMetaData();

      ResultSetMetaData rsmd = rs.getMetaData();
     int colCount = rsmd.getColumnCount();
     while (rs.next())
     {
        String[] record = new String[colCount];
        for (int i = 0; i < colCount; i++) {
          record[i] = rs.getString(i + 1);
        }
        dataVector.addElement(record);
     
     }
     
      }
      catch(SQLException e)
      {  System.out.println("TableModelResultSet Error " + e);
      }*/
   }
  
  public void addRow(String text, String[] headersIn)
  {
  	  tableModelQuery=false;
  	  //headers=headersIn;
  	  colCount=headersIn.length;
  	  headers = new String[colCount];
      for (int h = 1; h <= colCount; h++)
      {
        headers[h - 1] = headersIn[h - 1];//.getColumnName(h);
        
        tableCols.add(recColumn = new RecColumn(headersIn[h - 1],"","",Types.LONGVARCHAR/*type*/,"java.lang.String",headersIn[h - 1],100/*size of col*/));
      }

      // and file the data with the records from our query. This would
      // not be
      // practical if we were expecting a few million records in response
      // to our
      // query, but we aren't, so we can do this.
     
        String[] record = new String[colCount];
        for (int i = 0; i < colCount; i++) // for each field
        {
          record[i] = text;
        }
        dataVector.addElement(record);
  
       fireTableChanged(null);
  	
  }
 
  /*public void setQueryForMetaData(String query)
  {
  	tableModelQuery=true;
    dataVector = new Vector();
    try {
      // Execute the query and store the result set and its metadata
      if (VariablesGlobal.globalShowSQL)
       {  System.out.println("TableModelRSReadOnly.setQueryForMetaData: "+query);  }
      
       //System.out.println("TableModelRSReadOnly.setQueryForMetaData: "+query);

   	    db.retrieveDBDataFromQuery(query,"TableModelRSReadOnly.setQueryForMetaData");
   	    //rs=db.getRS();
   	    rsmd=db.getRSMetaData();      
      
      //rs = db.retrieveResultSet(query);
      //rsmd = db.retrieveRSMetaData(query);
      colCount = 4;//rsmd.getColumnCount();
      
      headers = new String[colCount];
      String strDbFields = "db field";
      String strFields = "πεδίο";
      String strTypes = "τύπος";
      String strLength = "μήκος";
      headers[0] = strDbFields;
      headers[0] = strFields;
      headers[0] = strTypes;
//      headers[0] = strLength;
//      tableCols.add(recColumn = new RecColumn(strDbFields,"","",12 /*varchar*///,strDbFields,10/*length*/));
//      tableCols.add(recColumn = new RecColumn(strFields,"","",12 /*varchar*///,strFields,10/*length*/));
//      tableCols.add(recColumn = new RecColumn(strTypes,"","",12 /*varchar*///,strTypes,10/*length*/));
//      tableCols.add(recColumn = new RecColumn(strLength,"","",12 /*varchar*///,strLength,10/*length*/));
      // and file the data with the records from our query. This would
      // not be
      // practical if we were expecting a few million records in response
      // to our
      // query, but we aren't, so we can do this.
  /*    int rowCount = rsmd.getColumnCount();
      
      for (int h = 1; h <= rowCount; h++)
      {
        Object[] record = new Object[colCount];
         /*for (int c= 0;c<colCount;c++)
         {*/
/*            record[0] = rsmd.getColumnName(h);         	        	
            record[1] = rsmd.getColumnLabel(h); 
            record[2] = rsmd.getColumnTypeName(h); 
            record[3] = rsmd.getColumnDisplaySize(h); 
         //}

      
         dataVector.addElement(record);
      }
      fireTableChanged(null); // notify everyone that we have a new table.
      

   //  releaseConnection();
      
    } catch (SQLException e) {
      dataVector = new Vector(); // blank it out and keep going
      System.out.println("TableModelRSReadOnly.setQueryForMetaData "+e.getMessage());//e.printStackTrace();
    }
    /*
    
    //closeDB();
  }  
  
/*  public int setQuery(String query)
  {
  	tableModelQuery=true;
    dataVector = new Vector();
    try {
      // Execute the query and store the result set and its metadata
      if (VariablesGlobal.globalShowSQL)
       {  System.out.println("TableModelRSReadOnly.setQuery: "+query);  }

   	    db.retrieveDBDataFromQuery(query,"TableModelRSReadOnly.setQueryForMetaData");
   	    rs=db.getRS();
   	    rsmd=db.getRSMetaData();
      
      //rs = db.retrieveResultSet(query);
      //rsmd = db.retrieveRSMetaData(query);
      colCount = rsmd.getColumnCount();

      // Now we must rebuild the headers array with the new column names
      headers = new String[colCount];
      for (int h = 1; h <= colCount; h++)
      {
        headers[h - 1] = rsmd.getColumnLabel(h);//.getColumnName(h);
        
        tableCols.add(recColumn = new RecColumn(rsmd.getColumnName(h),"","",rsmd.getColumnType(h),rsmd.getColumnLabel(h),rsmd.getColumnDisplaySize(h)));
      }

      // and file the data with the records from our query. This would
      // not be
      // practical if we were expecting a few million records in response
      // to our
      // query, but we aren't, so we can do this.
      while (rs.next())
      {
        Object[] record = new Object[colCount];
        for (int i = 0; i < colCount; i++) // for each field
        {
          record[i] = rs.getObject(i + 1);
        }
        dataVector.addElement(record);
      }
      fireTableChanged(null); // notify everyone that we have a new table.
      

   //  releaseConnection();
      
    } catch (SQLException e) {
      dataVector = new Vector(); // blank it out and keep going
      System.out.println("TableModelRSReadOnly.setQuery "+e);//e.printStackTrace();
    }
    
    closeDB();
    
    return 0;
  }*/

  /*public void releaseConnection()
  { // NOT CALLED
	
	      db.releaseConnectionRsmd();
          db.releaseConnectionRs();
	
  }*/

   public String getColumnName(int c)
   {
   	        //System.out.println("TableModelResultSetReadOnly.getColumnName "+c+" "+tableCols.size());
   	        
            RecColumn col = (RecColumn)tableCols.get(c);
            
            return col.getColumnCaption();
            //rsmd.getColumnName(c + 1);
   }

   public int getColumnCount()
   {  
      return colCount;
      /*try
      {  return rsmd.getColumnCount();
      }
      catch(SQLException e)
      {  System.out.println("TableModelResultSet.getColumnCount Error " + e);
         return 0;
      }*/
   }

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


    
        public boolean isCellEditable(int row, int column)
    {
    	if (column==4)
    	{
    	  return true;
    	}
    	else
    	{
    		return false;
    	}
       
    }

   public Object getValueAt(int r, int c)
   {  
        //Vector row = (Vector)dataVector.elementAt(r);
        //return row.elementAt(c);
           
      return ((Object[])dataVector.elementAt(r))[c];
            
   }   


   /*public void closeDB()
   {
   	
   	      db.releaseConnectionRs();
          db.releaseConnectionRsmd();
   	
   }*/

    public Class getColumnClass(int column)
    {


        int type;

                RecColumn col = (RecColumn)tableCols.get(column);
                type =  col.getColumnType();            	
            	//type = rsmd.getColumnType(column+1); 


        //System.out.println("TableModelResultSetReadOnly.getColumnClass col"+column+" type "+type+" int"+Types.INTEGER+" varchar"+Types.VARCHAR);

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
        

    }

     public String[] getTableColumnRealNames()
     {
          String[] columns=new String[colCount];
         for(int c=0; c<colCount; c++)
         {
         	columns[c]=getColumnName(c);
         }	
       return columns;
     }

     public Class[] getTableColumnsClass()
     {
     	Class[] columnsCl = new Class[colCount];
     	
         for(int c=0; c<colCount; c++)
         {
              columnsCl[c]=this.getColumnClass(c);
         }	
       return columnsCl;
     }
     
     public Vector getTableDataVector()
     {
     
        return dataVector;
     }
 
 
   /*protected ResultSet getResultSet()
   {  return rs;
   }*/
    
    
    // used by utilsTable.copyTableToTable  reportform 
   public void setDataVector(Vector data, ArrayList listColumnsIn)
   {
   	
	
   	
   	tableModelQuery= false;
   	//dataVector = new Vector();
   	//tableCols = listColumnsIn; 
   	colCount = listColumnsIn.size();
   	tableCols.clear();
     dataVector = data;
     
     //System.out.println("TableModelResultSetReadOnly.setDataVector "+colCount+" "+listColumnsIn.size());  
      
     
     for(int c=0;c<colCount;c++)
     {     
       //System.out.println("TableModelResultSetReadOnly.setDataVector "+c+" "+tableCols.size()+" "+colCount);
       tableCols.add(recColumn = new RecColumn(listColumnsIn.get(c).toString(),"","",12 /*varchar*/,"java.lang.String",listColumnsIn.get(c).toString(),10/*length*/));	
     }
     
     
     
       	
   	 fireTableChanged(null);
   }
   
   

   
   public void setValueAt(Object value, int row, int col)
   {

                ((Object[])dataVector.elementAt(row))[col] = value;
                fireTableCellUpdated(row, col);
            
   }
   
//  public Vector getColumnIdentifiers() { return columnIdentifiers; } 

   /*
    * Don't need to implement this method unless your table's
    * editable.
    */
  /*public boolean isCellEditable(int row, int column)
  { 
     //Note that the data/cell address is constant,
     //no matter where the cell appears onscreen.
     if (column < 1) {
        return false;
     } else {
        return true;
     }
  }   */

}

