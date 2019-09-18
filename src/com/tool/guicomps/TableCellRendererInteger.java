  //created 14-09-2006

  package com.tool.guicomps;
  
  import com.tool.utils.*;
import com.tool.gui.*;


  import java.awt.Component;
  import java.awt.Color;
  import java.awt.SystemColor;
  import javax.swing.JTable;
  import javax.swing.table.DefaultTableCellRenderer;
  import javax.swing.table.TableCellRenderer; 
  import java.text.NumberFormat;
  import javax.swing.SwingConstants;
  
  public class TableCellRendererInteger extends DefaultTableCellRenderer implements Constants
  {

    public TableCellRendererInteger()
    {
      super();
      setHorizontalAlignment(SwingConstants.RIGHT);
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

    public Component getTableCellRendererComponent(JTable table, Object value,
     boolean isSelected, boolean hasFocus, int row, int column) 
    {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
     //System.out.println("getTableCellRendererComponent column:"+column+" "+table+" "+value);
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
             {   setBackground(c);    }      	     
      	 }
      	 else
      	 {
      	 //	highlight = this.getForeground();
      	 }
      }*/

      if(hasFocus )
      {
      	setBackground(CLR_ROW_SELECTED_CELL);
      	setForeground(table.getForeground());
      }
     
     return cell;
    }

  } 