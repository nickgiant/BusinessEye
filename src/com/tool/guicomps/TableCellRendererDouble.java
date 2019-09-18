 //created 14-09-2006
 //    look at   java.text.DecimalFormat

  package com.tool.guicomps;
  
 import com.tool.utils.*;
import com.tool.gui.*;
import com.tool.jdbc.*;
import com.tool.model.*;
 
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
  
  
  import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

  
  
  public class TableCellRendererDouble extends DefaultTableCellRenderer implements Constants
  {
    UtilsDouble utilsd;
    int row, column;
    //private EntityDBFields dbFieldMany;
   //private Database db= new Database();  
   	private JTable table;
   	
    public TableCellRendererDouble(UtilsDouble uDouble)
    {
      super();
       utilsd = uDouble;//new UtilsDouble();
       //utilsd.getSettingsFromDb();// already called before when TableCellRendererDouble(UtilsDouble uDouble) is called

      setHorizontalAlignment(SwingConstants.RIGHT);
      //dbFieldMany.getWhenSetThenCalculateField()!=-1
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

  
 /*public void getDoubleSettingsFromFile()
  {
      System.out.println("TableCellRendererDouble.getDoubleSettingsFromFile");
  	utilsd.getSettingsFromDb();
  }*/

    public Component getTableCellRendererComponent(JTable tableIn, Object value,
     boolean isSelected, boolean hasFocus, int rowIn, int columnIn) 
    {
    	
   	
    	table= tableIn;
    	Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, rowIn, columnIn);
    	row=rowIn;
    	columnIn = columnIn;
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
       
       
     /* UtilsOS uOS = new UtilsOS();
      
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
             {   setBackground(c);    }        	 }
      	 else
      	 {
      	 //	highlight = this.getBackground();
      	 }

      }*/
      
      if(hasFocus )
      {
      	setBackground(CLR_ROW_SELECTED_CELL);
      	setForeground(table.getForeground());
      	//TableCellEditorDouble tceDouble = new TableCellEditorDouble();
        //tceDouble.requestFocus();
        //tableIn.requestFocus();
        
      }
     
     return cell;
    }
    
    
  public void setValue(Object value)
  {
     

     double dbl = 0;
     String v="";
     if(value!=null && !value.toString().trim().equalsIgnoreCase(""))
     {
         String val = value.toString().trim();
         val = utilsd.getDoubleSaving(val);
         //System.out.println("TableCellRendererDouble "+row+" "+column+" "+value+"   val:"+val);
     	if(val != null && !val.equalsIgnoreCase(""))
        {
         dbl= new Double(val);
        }
         else
         {
                 
         }
     	v = utilsd.getDoubleReading(dbl,false);

     }

     	     super.setValue(v);
 
       //System.out.println("TableCellRendererDouble "+row+" "+column+" "+value+"   v:"+v);
     
     
    } 

  } 