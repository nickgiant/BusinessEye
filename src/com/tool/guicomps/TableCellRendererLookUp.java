 //created 29-05-2009
 //    look at   java.text.DecimalFormat

  package com.tool.guicomps;
 
import com.tool.model.LookUpMgt;
  import com.tool.model.*;
 import com.tool.utils.*;
 import com.tool.gui.*;
 import com.tool.jdbc.*;
 
 import java.sql.*;
 
  import java.awt.Component;
  import java.awt.Color;
  import java.awt.SystemColor;
  import javax.swing.JTable;
  import javax.swing.table.DefaultTableCellRenderer;
  import javax.swing.table.TableCellRenderer; 
  import java.text.NumberFormat;
  import javax.swing.SwingConstants;
  import java.text.DecimalFormat;
  import java.text.DecimalFormatSymbols;
  
  
  public class TableCellRendererLookUp extends DefaultTableCellRenderer implements Constants
  {
    private LookUpMgt lookUp;
    private String strTable;
    //private ResultSetMetaData rsmd; 
    //private ResultSet rsForeign;
    //private Database db;    
        protected int row, column;
        EntityDBFields[] dbFields;
        private UtilsPanelReport utilsPanelReport;
    private String entity;
    private String luname="";
   // private String formGlobalTableToApply1;
        //private String formGlobalField1;
        //private String formGlobalVariable1;
    private String fieldVariableFromPreField ="";
        
    public TableCellRendererLookUp(String lunameIn,EntityDBFields[] dbFieldsIn,String entityIn) ///  EntityDBFields[] dbFieldsIn,
    {
      super();
      lookUp= new LookUpMgt();
      strTable = lookUp.getFromTheNameTheForeignTable(luname);
      //db = new Database();
       luname =  lunameIn;
     // foreignTable=foreignTableIn;
     dbFields=dbFieldsIn;
     entity = entityIn;
    // formGlobalTableToApply1 = formGlobalTableToApply1In;
    
    int intNoOfFields =  dbFields.length;
    for(int i = 0;i<intNoOfFields;i++)
    {
        String flf ="";
        String colName = dbFields[i].getDbField();
        flf = dbFields[i].getFormVariableFromField();
   //      System.out.println("TableCellRendererLookUp   entity:"+entity+"   i:"+i+"  colName:"+colName+"   getFormVariableFromField:"+flf+"     intNoOfFields:"+intNoOfFields);
        if(flf!=null && !flf.equalsIgnoreCase(""))
        {
                    fieldVariableFromPreField = flf;
                    break;
        }
        else
        {
            fieldVariableFromPreField = "";
        }
    }
    
    //fieldVariableFromPreField = fieldVariableFromPreFieldIn;
     entity=entityIn;
     //formGlobalTable1= formGlobalTable1In;
    // formGlobalField1 =formGlobalField1In;
    // formGlobalVariable1=formGlobalVariable1In;
     utilsPanelReport = new UtilsPanelReport();
      //System.out.println("TableCellRendererLookUp "+lookUp.getLookUpField(foreignTable)+" "+lookUp.getLookUpIntNoOfColsWhenInTable(foreignTable));
      
   //   System.out.println("TableCellRendererLookUp   entity:"+entity);
      
      
      if ((dbFields[column].getLookupEntityName()!= null) && (lookUp.getLookUpIntNoOfColsWhenInTable(luname))==2)//(dbFields[column].getLookupEntityName())==2))//(!rsmd.getTableName(i).equalsIgnoreCase(entity)))
      {
      	setHorizontalAlignment(SwingConstants.RIGHT);
      }
      else if ((dbFields[column].getLookupEntityName()!= null) && (lookUp.getLookUpIntNoOfColsWhenInTable(luname))==1)//(dbFields[column].getLookupEntityName())==1))//(!rsmd.getTableName(i).equalsIgnoreCase(entity)))
      {
      	 setHorizontalAlignment(SwingConstants.CENTER);
    	 
      }
      else
      {
      	
      }
      
      
    }

  /*  public void setValue(Object value)
    {
      if ((value != null) && (value instanceof Number))
      {
        Number numberValue = (Number) value;
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        value = formatter.format(numberValue.doubleValue());
      } 
      super.setValue(value);
    } */

/*  public void getDoubleSettingsFromFile()
  {
  	utilsd.getSettingsFromFile();
  }*/

    public Component getTableCellRendererComponent(JTable table, Object value,
     boolean isSelected, boolean hasFocus, int row, int column) 
    {
    	
        this.row = row;
        this.column = column;      	
    	
    	Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    	
    	
    //	 System.out.println("TableCellRendererLookUp.getTableCellRendererComponent for col "+column+" val"+value);
    	
    /*  error
     *   if(value!=null)
       {
       	
       //double dbl;
       String v="";

     	//dbl= new Double((String)value.toString().trim());
     	v = utilsd.getDoubleReading(0.0);        	
       	
       	System.out.println(value.toString()+" "+v+" "+column);
       	
    	 if(utilsd.getDoubleReading(value).equalsIgnoreCase(v))
    	 {
    	   cell = super.getTableCellRendererComponent(table, "", isSelected, hasFocus, row, column);	
    		
    	 }
         else
         {
        	cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);	
         }
       }*/
       
   /*   UtilsOS uOS = new UtilsOS();
      
      if( !isSelected )
      {
      	 Color highlight = null;
      	 if(uOS.isOSWindows())
      	 {
      	     highlight = VariablesGlobal.globalColorWindowsHighlight;//.activeCaption;	
      	 //ac.brighter().brighter();
             Color c = table.getBackground();
             if( (row%2)==0)
             {	  
                    setBackground(highlight); 
             }
             else
             {   setBackground(c);    }      	     
      	 }
      	 else
      	 {
      	 	//highlight = this.getForeground();
      	 	
      	 }
      }*/
      
      if(hasFocus )
      {
      	//table.getCellEditor(row,column).getCellEditorValue();
      	setBackground(CLR_ROW_SELECTED_CELL);
      	setForeground(table.getForeground());
      }
     
     return cell;
    }

 /*public void setBackground(Color color)
 {
 	setBackground(color);
 }*/
   

    @Override
  public void setValue(Object value)
  {
     //System.out.println("TableCellRendererLookUp.setValue "+value);

      strTable = lookUp.getFromTheNameTheForeignTable(luname);

    // double dbl;
      String v="";
     if(value!=null && !value.toString().trim().equals(""))
     {
 
         String retValue = "";
//       	       String  foreignQuery = "SELECT * FROM "+foreignTable+" WHERE "+lookUp.getLookUpKey(foreignTable)+" = "+value;//+rs.getInt(dbColNo);     
        //     System.out.println("TableCellRendererLookUp.setValue     foreignTable:"+foreignTable+"      value:"+value+"      entity:"+entity);
             
             //   utilsPanelReport.getLookupValue(foreignTable,  value+"", int intField, boolean isTypedOrSaved,String subqueryWhereForAPreviousFieldValue);
              
            
            
            retValue =  utilsPanelReport.getLookupValue(luname,strTable,  value+"", 1, /*true*/false,fieldVariableFromPreField,"",entity,null,null);  
   
       //System.out.println("TableCellRendererLookUp.setValue     luname:"+luname+"      value:"+value+"    retValue:"+retValue+"    entity:"+entity);
           
          //lookupText=value.toString();
                  // }
        if ((dbFields[column].getLookupEntityName()!= null) && (lookUp.getLookUpIntNoOfColsWhenInTable(luname))==2)//dbFields[column].getLookupEntityName())==2))//(!rsmd.getTableName(i).equalsIgnoreCase(entity)))
        {
        	v = (String)value.toString().trim();
        }
        else if ((dbFields[column].getLookupEntityName()!= null) && (lookUp.getLookUpIntNoOfColsWhenInTable(luname))==1)//(dbFields[column].getLookupEntityName())==1))//(!rsmd.getTableName(i).equalsIgnoreCase(entity)))
        {
        	v = (String)retValue.toString().trim() ;// lookupText.toString().trim();
        }
        else
        {
        	v = (String)value.toString().trim();
        }
     }

     	     super.setValue(v);

  
    }  

}
