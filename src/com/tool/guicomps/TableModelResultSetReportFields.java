// created 27-02-2010

package com.tool.guicomps;

import com.tool.jdbc.*;
import com.tool.gui.*;

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

public class TableModelResultSetReportFields extends AbstractTableModel
{

  // private ResultSetTableModel model;
   //private Database db= new Database();
   //private Statement stmt;
   //private ResultSet rs;
   //private ResultSetMetaData rsmd;
   
   private Vector dataVector;
  private int colCount;
  private String[] headers;
  private ArrayList tableCols;
  private RecColumn recColumn;
  private boolean tableModelQuery=true;
//abstract class ResultSetTableModel extends AbstractTableModel
//{  
   public TableModelResultSetReportFields()
   {  
     //rs = aResultSet;
     dataVector = new Vector();
     tableCols = new ArrayList();
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
  
  public void removeRow(int row)
  {   
      dataVector.remove(row);
      fireTableChanged(null);
  	
  }  

  public void removeAllRows()
  {

      dataVector.removeAllElements();
      fireTableChanged(null);
  	
  }  
  
  
  /*
  * called by 
  */
   public void setValueAt(Object value, int row, int col)
   {
   	 
   	 ((Object[])dataVector.elementAt(row))[col] = value;
      fireTableCellUpdated(row, col);
   	 
   	 //setValueAt(value,row,col);
   }  	
   
   // called by PanelReportFormDesign
   public void setColumns(String[] headersIn)
   {

  	  colCount=headersIn.length;
  	  headers = new String[colCount];
      for (int h = 1; h <= colCount; h++)
      {
        headers[h - 1] = headersIn[h - 1];//.getColumnName(h);
        
        tableCols.add(recColumn = new RecColumn(headersIn[h - 1],"","",Types.LONGVARCHAR/*type*/,"java.lang.String",headersIn[h - 1],100/*size of col*/));
      }   	
   	   //System.out.println("TableModelResultSetReportFields.setColumns "+headersIn[0]+" "+headersIn[1]+" "+headersIn[2]);
   	   fireTableChanged(null);
   }
  
   public void addRow(Object[] dataRow)
   {
  	 
     
        Object[] record = new Object[colCount];
        for (int i = 0; i < colCount; i++) // for each field
        {
        	
          record[i] = dataRow[i];
        }
        
        //System.out.println("TableModelResultSetReportFields.addRow "+record[0]+" "+record[1]+" "+record[2]);
        
        dataVector.addElement(record);
  
        fireTableChanged(null);
      	
   }  	
  
   /*
    * Don't need to implement this method unless your table's
    * editable.
    */
  public boolean isCellEditable(int row, int column)
  {
     //Note that the data/cell address is constant,
     //no matter where the cell appears onscreen.
     if (column < 2) {
        return false;
     } else {
        return true;
     }
  }   
   
   ////////////////////////////
   public Object getValueAt(int r, int c)
   {  
        //Vector row = (Vector)dataVector.elementAt(r);
        //return row.elementAt(c);
           
      return ((Object[]) dataVector.elementAt(r))[c];   
   }
  
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
   	   
   
   	
}

