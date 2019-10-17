// created 23-09-2006

package com.tool.guicomps;

import com.tool.jdbc.*;
import com.tool.gui.*;
import com.tool.utils.*;

import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.sql.SQLException;

import java.util.Vector;
//import java.util.ArrayList;

import java.awt.Color;
import java.awt.SystemColor;

public class TableModelResultSetCheckBoxes extends AbstractTableModel
{

     //String[] headers ={"mm","ss"}; //new String[1];
     //Object[][] data={{"dd","ff"},{"dd","ff"}};  //new String[1][1];
   private Database db;
   private String query;
   //private Statement stmt;
   private ResultSet rs;
   private ResultSetMetaData rsmd;
   String primKeyCaption;
   
   
   private Vector dataVectorChk;
  int colCount;
  String[] headersChk;
  boolean isQuery = false;
     private UtilsDouble utilsDouble;
     
   public TableModelResultSetCheckBoxes()
   {  
       db= new Database();
     dataVectorChk = new Vector();
     utilsDouble = new UtilsDouble();
     utilsDouble = VariablesGlobal.globalUtilsDouble; 
   /*  String[] headers ={"mm","ss"}; //new String[1];
     String[][] data={{"dd","ff"},{"dd","ff"}};  //new String[1][1];*/
   }

  /*public void setTitleAndData(String[] headersIn, Object[][] dataIn)
  {

      headers = headersIn;
      data=dataIn;
   
      fireTableChanged(null); // notify everyone that we have a new table.
  }*/
   
  /*
   * 
   * called by PanelDataViewNOrder
   */
  public void setData(String[] headersChkIn, Vector dataVectorChkIn)
  {
  	isQuery=false;
  	   headersChk=headersChkIn;
  	   colCount=headersChk.length;
  	   dataVectorChk=dataVectorChkIn;
       
        fireTableChanged(null); 
  }

  /*
   * called by PanelDataViewNOrder
   * set primary key captio of column 1 disabled and true in column 0
   */
  public void setRowWithCaptionDisabledAndTrue(String primKeyCaptionIn)
  {
      primKeyCaption=primKeyCaptionIn;
      
      for(int r = 0 ;r<getRowCount();r++)
      {
          String strRowCaption = getValueAt(r,2)+"";
          //System.out.println("TablrModelResultSetCheckBoxes.setRowWithCaptionDisabledAndTrue  "+r+" "+strRowCaption+" "+primKeyCaptionIn);
          if(strRowCaption.equalsIgnoreCase(primKeyCaptionIn))
          {
              setValueAt(true,r,0);
              
          }
          
      }
      
      
      
      
  }
  
  public void setQuery(String queryIn)
  {
  	isQuery=true;
  	query=queryIn;
     Vector dataVector = new Vector();
     
     //System.out.println("TableModelResultSetCheckBoxes.setQuery: "+query); 
     
      
      // Execute the query and store the result set and its metadata
      if (VariablesGlobal.globalShowSQL)
       {  System.out.println("TableModelResultSetCheckBoxes.setQuery: "+query);  }

   	    db.retrieveDBDataFromQuery(query,"TableModelResultSetCheckBoxes.setQuery");
   	    rs=db.getRS();
   	    rsmd=db.getRSMetaData();
        String[] headers = null;  
      try {    
      //rs = db.retrieveResultSet(query);
      //rsmd = db.retrieveRSMetaData(query);
      colCount = rsmd.getColumnCount();

      // Now we must rebuild the headers array with the new column names
     headers = new String[colCount];
      
      for (int h = 1; h <= colCount; h++)
      {
        headers[h-1] = rsmd.getColumnLabel(h);
      }
  //    headers[0]="check";
     
      // and file the data with the records from our query. This would
      // not be
      // practical if we were expecting a few million records in response
      // to our
      // query, but we aren't, so we can do this.
      dataVectorChk.clear();

      while (rs.next())
      {
      	
        String[] record = new String[colCount+1];
        for (int i = 1; i <= colCount; i++) // for each field
        {
          record[i-1] = rs.getString(i);
        }
        dataVector.addElement(record);
      }
    } catch (SQLException e)
    {
      dataVector = new Vector(); // blank it out and keep going
      System.out.println("TableModelResultSetCheckBoxes.setQuery "+e);//e.printStackTrace();
    }      
      
      // add checkbox column
       headersChk = new String[colCount+1];
      headersChk[0]="τσέκ";
      //System.out.println("headers 0"+headersChk[0]);
      for(int h =1; h<=headers.length;h++)
      {
      	headersChk[h]=headers[h-1];
      	//System.out.println("headers "+h+" "+headersChk[h]);
      }
      //colCount= colCount+1;
      // add checkbox column data
      for (int r=0; r<dataVector.size(); r++)
      {
        Object[] record = new Object[colCount+1];
        record[0] = Boolean.valueOf(false);
        //System.out.println("vector c 0 "+r);
        for (int c = 1; c <= colCount; c++) // for each field
        {
          record[c] =  ((Object[]) dataVector.elementAt(r))[c-1]; // to change data from rows to columns just change r and c between them
          //System.out.println("vector c"+(c)+" "+r);
        }
        dataVectorChk.addElement(record);
      }
    
    // is needed because according to the transform of headers and vector 1 col is added  
    //(get columnCount gets data from variable colCount)
      colCount=colCount+1; 
    

      fireTableChanged(null); // notify everyone that we have a new table.

      

     closeDB();
   
      fireTableChanged(null); // notify everyone that we have a new table.
  }



   public String getColumnName(int c)
   {
   	 
   	 /* try
      {  return rsmd.getColumnName(c+1);
      }
      catch(SQLException e)
      {  System.out.println("TableModelResultSetCheckBoxes.getColumnName Error " + e);
         return "";
      }  */ 	  
   	  
   	  return headersChk[c];
   	  
   }

   public int getColumnCount()
   {  
     return colCount;
      //return  headers.length ;
      
   }

   public int getRowCount()
   {  
      return dataVectorChk.size();
      //return data.length ;
   }

    public boolean isCellEditable(int row, int column)
    {
    	if (column==0)
    	{
                // if is primary key check do not be editable
                String strRowCaption = getValueAt(row,2)+"";
                //System.out.println("TablrModelResultSetCheckBoxes.isCellEditable  "+row+" "+strRowCaption+" "+primKeyCaption);
                if(strRowCaption.equalsIgnoreCase(primKeyCaption))
                {
                        return false;
              
                }
                else
                {
                        return true;
                }
          
             
                  
           
               
    	}
    	else
    	{
            return false;
    	}
       
    }

   /*private void setCellIsEditable(boolean isEditable,int row, int column)
   {
       if(isEditable)
       {
           
       }
           
       
   }*/
    
   public Object getValueAt(int r, int c)
   {  
       Object ret = null;
       Class columnType = getColumnClass(c); 
       
       if(columnType== Double.class)
       {    
           String text = ((Object[]) dataVectorChk.elementAt(r))[c]+"";
           if(text!=null && !text.equalsIgnoreCase("") && !text.equalsIgnoreCase("null"))
           {
               //System.out.println("+   getValueAt r:"+r+" c:"+c+" text:"+text);
               // getDoubleSaving(text);     getDoubleReading(text,false);  getDoubleEditing
               //System.out.println("-   getValueAt r:"+r+" c:"+c+" text:"+text);
               ret = utilsDouble.getDoubleReading(text,false);
           }
           else
           {
               ret="";
           }
       }
       else
       {
           ret =  ((Object[]) dataVectorChk.elementAt(r))[c];
           //((Object[]) dataVectorChk.elementAt(r))[c];
       }
      return ret;
      //return  data[r][c];
   }

   public void setValueAt(Object value, int row, int col)
   {
   	  	
   	/*System.out.println("tableModelRSCB.setValueAt Set " + row + "," + col
                                   + " to " + value
                                   + " ("//an instance of " 
                                   + value.getClass() + ")");*/
          
          
            if( value.getClass() == Boolean.class)
            {
                //If we don't do something like this, the column
                //switches to contain Strings.
                //System.out.println("TableModelRS.setValueAt: is Boolean");
                ((Object[])dataVectorChk.elementAt(row))[col] = Boolean.valueOf((String)value.toString().trim());
                    
            }
                          
                fireTableCellUpdated(row, col);
            
            
   }
    
    
   public void closeDB()
   {
   	     //System.out.println("TableModelResultSetReadOnlyCheckBoxes.closeDB");
   	  db.releaseConnectionRs();
          db.releaseConnectionRsmd();
   	
   }    
    
      /* public Class getColumnClass(int column)
    {
          if(column==0)
          {     return Boolean.class; }
          else
          {     return String.class;  }
          
          //return Object.class;
    }*/
   
   
   
   
    public Class getColumnClass(int column)
    {
    	        // System.out.println("col");                          show
    	        //    return getValueAt(0, column).getClass();



        //System.out.println("TableModelResultSetReadOnlyCheckBoxes.getColumnClass col"+column+" type "+type);
      if(column==0)
      {     return Boolean.class; }
      else
      {

        
        int type;
        if(isQuery)
        {
           try
           {
        	   //System.out.println("TableModelResultSetReadOnlyCheckBoxes.getColumnClass col "+column+" tm"+getColumnCount()+" "+rsmd);//+" rsmd"+rsmd.getColumnCount());
               type = rsmd.getColumnType(column); // +1 because of array-rsmd, -1 because there is an addittional checkbox in front
              // System.out.print(rsmd.getColumnClassName(column+1));
               //System.out.print(rsmd.getColumnType(column+1));
              // System.out.println(' ');
           }
           catch (SQLException e)
           {
        	   System.out.println("error TableModelResultSetCheckBoxes.getColumnClass "+e);
               return super.getColumnClass(column);
           }
           
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
        {    
          return Integer.class;
        }
            //return Number.class;

        case Types.BIGINT:
            return Long.class;

        case Types.FLOAT:
        case Types.DOUBLE:
        case Types.DECIMAL: //    added by me
        {    
             //System.out.println("Double");
             return Double.class;        
        }

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
        else
        {
        	return String.class; 
        	
        }
      }	

    }


}

